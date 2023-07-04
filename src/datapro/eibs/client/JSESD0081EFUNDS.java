package datapro.eibs.client;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.io.StringReader;
import java.io.StringWriter;
import java.net.HttpURLConnection;
import java.net.Socket;
import java.net.URL;
import java.security.Security;
import java.text.CharacterIterator;
import java.text.StringCharacterIterator;
import java.util.Calendar;
import java.util.Date;
import java.util.MissingResourceException;
import java.util.Properties;
import java.util.ResourceBundle;
import java.util.StringTokenizer;
import java.util.Vector;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Source;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.TransformerFactoryConfigurationError;
import javax.xml.transform.stream.StreamResult;
import javax.xml.transform.stream.StreamSource;

import org.apache.soap.Envelope;
import org.apache.soap.encoding.SOAPMappingRegistry;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;

import com.datapro.generic.tool.Util;
import com.netegrity.jsaml.assertion.SAML_AssertionList;
import com.netegrity.jsaml.assertion.SAML_Attribute;
import com.netegrity.jsaml.assertion.SAML_AttributeStatement;
import com.netegrity.jsaml.assertion.SAML_Conditions;
import com.netegrity.jsaml.assertion.SAML_NameIdentifier;
import com.netegrity.jsaml.assertion.SAML_Statement;
import com.netegrity.jsaml.assertion.SAML_Subject;
import com.netegrity.jsaml.assertion.SAML_SubjectConfirmation;
import com.netegrity.jsaml.sign.DsigProvider;
import com.netegrity.jsaml.sign.DsigProviderImpl;
import com.netegrity.jsaml.sign.JKSImplementation;
import com.netegrity.jsaml.sign.KeyLookupAndVerify;
import com.netegrity.jsaml.sign.SAML_SignedAssertionList;

import datapro.eibs.beans.Consumer;
import datapro.eibs.beans.ELEERRMessage;
import datapro.eibs.beans.ESD000508Message;
import datapro.eibs.beans.ESS0030DSMessage;
import datapro.eibs.beans.JBQualiFile;
import datapro.eibs.generic.SoapEnvComposer;
import datapro.eibs.master.SuperServlet;
import datapro.eibs.sockets.MessageContext;
import datapro.eibs.sockets.MessageRecord;

public class JSESD0081EFUNDS extends SuperServlet {

	//handle to the properties
	ResourceBundle _ResourceBundle = null;

	//URL to which POs are send.
	String _PurchaseRequestURL = null;

	//our local document builder
	DocumentBuilder _documentBuilder;

	//handle to the keystore interface
	KeyLookupAndVerify _klv = null;

	//handle to the digital signatures provider
	DsigProvider _dsig = null;

	//which alias is signing
	String _signAlias = null;

	//what is the keypass of the signer
	String _signKeyPass = null;

	//are we signing?
	boolean _sign = false;

	//are we logging?
	boolean _megalog = false;

	//validity lifetime settings
	int _preSkew;
	int _validity;

	String _customerId = null;
	String _accountChexVersion = null;
	String _qualiFileVersion = null;
	String _secUserId = null;
	String _secPassword = null;
	String _issuer = null;

	//all the one-time initialization of the servlet happens in here. We set up the DocumentBuilder to re-use on each call and
	// read in the relevant configuration properties from the property file.
	public void init(ServletConfig config) throws ServletException {
		super.init(config);
		//make sure the damn context exists so I can ask for it in this function!

		//build and store a DocumentBuilder so that I can keep using it whenever
		// this servlet is called.
		DocumentBuilderFactory fac = DocumentBuilderFactory.newInstance();
		fac.setNamespaceAware(true);
		try {
			_documentBuilder = fac.newDocumentBuilder();
		} catch (ParserConfigurationException e) {
			this.getServletContext().log(
				"Error during the _documentBuilder init",
				e);
		}

		//get the location of the property file and read some properties...
		try {
			//where to look for the properties file
			_ResourceBundle = ResourceBundle.getBundle("efunds");

			//lookup URL to send requests to
			_PurchaseRequestURL =
				_ResourceBundle.getString("property.researchurl");
		} catch (Exception e) {
			flexLog("Exception during the property initialization:" + e);
		} finally {
			//Make a note of where we are sending requests
			flexLog("PO Processing URL: " + _PurchaseRequestURL);
		}

		//set up digital signing, if required
		initSigning();

		//set up the information on how long assertions live
		initAssertionLifetime();

		//set up SSL 
		initSSL();

		//
		//eFunds params.
		try {
			String id = _ResourceBundle.getString("customer.id");
			_customerId = id;
		} catch (MissingResourceException e) {
			_customerId = "10000000";
		}
		try {
			String id = _ResourceBundle.getString("account.chex.version");
			_accountChexVersion = id;
		} catch (MissingResourceException e) {
			_accountChexVersion = "V006";
		}
		try {
			String id = _ResourceBundle.getString("quali.file.version");
			_qualiFileVersion = id;
		} catch (MissingResourceException e) {
			_qualiFileVersion = "N001";
		}

		//determine if detailed logging is required.
		try {
			String Megalog = _ResourceBundle.getString("extra.logging");
			_megalog = true;
		} catch (MissingResourceException e) {
			_megalog = false;
		}

	}

	public void initSSL() {
		// rel:/conf/client.keystore
		String KeyStore = _ResourceBundle.getString("keystore.filelocation");
		;
		// password
		String KeyStorePW = _ResourceBundle.getString("keystore.storepass");
		try {
			//if the string starts with "rel:" treat it as a relative path otherwise treat as an 
			// absolute path
			StringTokenizer checkRel = new StringTokenizer(KeyStore, ":");
			if (checkRel.hasMoreTokens()) {
				String rel = checkRel.nextToken();
				if (rel.compareTo("rel") == 0) {
					KeyStore =
						this.getServletContext().getRealPath(
							checkRel.nextToken());
				}
			}

			// Now either we can rely on the user to have added us to
			// the security.provider list in java.security, or we can
			// dynamically add ourselves here.  We'll set it up here.

			Security.addProvider(new com.sun.net.ssl.internal.ssl.Provider());

			Properties properties = System.getProperties();

			String handlers = System.getProperty("java.protocol.handler.pkgs");
			if (handlers == null) {
				// nothing specified yet (expected case)
				properties.put(
					"java.protocol.handler.pkgs",
					"com.sun.net.ssl.internal.www.protocol");
			} else {
				// something already there, put ourselves out front
				properties.put(
					"java.protocol.handler.pkgs",
					"com.sun.net.ssl.internal.www.protocol|".concat(handlers));
			}
			properties.put("javax.net.ssl.keyStore", KeyStore);
			properties.put("javax.net.ssl.keyStorePassword", KeyStorePW);

			System.setProperties(properties);
			// put the updated properties back in System

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	//look up the information about whether or not to sign. If we are signing, look up the other required information
	public void initSigning() {
		try {
			String doSigning = _ResourceBundle.getString("sign");

			String keyStoreFileName = null;
			String keyStorePass = null;

			try {
				//where to look for the keystore
				keyStoreFileName =
					_ResourceBundle.getString("keystore.filelocation");

				//if the string starts with "rel:" treat it as a relative path otherwise treat as an absolute path
				StringTokenizer checkRel =
					new StringTokenizer(keyStoreFileName, ":");
				if (checkRel.hasMoreTokens()) {
					String rel = checkRel.nextToken();
					if (rel.compareTo("rel") == 0) {
						keyStoreFileName =
							this.getServletContext().getRealPath(
								checkRel.nextToken());
					}
				}

				keyStorePass = _ResourceBundle.getString("keystore.storepass");
				_signAlias = _ResourceBundle.getString("keystore.signer.alias");
				_signKeyPass =
					_ResourceBundle.getString("keystore.signer.password");

				//open keystore and create KLV interface
				_klv = new JKSImplementation(keyStoreFileName, keyStorePass);

				_dsig = new DsigProviderImpl(false);
				_dsig.initialize(_klv);

				//if we got this far without an exception, then we can assume we found everything we need
				// and set the flag for signing
				if (_ResourceBundle
					.getString("sign")
					.equalsIgnoreCase("TRUE")) {
					_sign = true;
				}
			} catch (Exception e) {
				flexLog("Error during the signing initialization:" + e);
			} finally {
				//add information on signing to the log
				flexLog("Sign assertions: " + doSigning);
				flexLog("keystore Filename: " + keyStoreFileName);
				flexLog("keystore Password: " + keyStorePass);
				flexLog("keystore Signer Alias: " + _signAlias);
				flexLog(
					"keystore Signer Private Key Password: " + _signKeyPass);
			}

		} catch (MissingResourceException me) {
			//if there is no requestreport.sign property then we don't need to init the signing stuff
			// so do nothing
		}
	}

	public void initAssertionLifetime() {
		//how long before NOW in minutes do assertion lifetimes start
		try {
			_preSkew =
				Integer.parseInt(_ResourceBundle.getString("validity.preskew"));
		} catch (MissingResourceException e) {
			_preSkew = 0;
		}

		//how long do assertions live
		try {
			_validity =
				Integer.parseInt(
					_ResourceBundle.getString("validity.lifetime"));
		} catch (MissingResourceException e) {
			_validity = 5;
		}
	}

	//All the work happens in here.
	public void service(HttpServletRequest req, HttpServletResponse res)
		throws IOException, ServletException {

		ESS0030DSMessage msgUser = null;
		HttpSession session = null;

		String LangPath = "";

		session = (HttpSession) req.getSession(false);

		if (session == null) {
			try {
				res.setContentType("text/html");
				printLogInAgain(res.getWriter());
			} catch (Exception e) {
				e.printStackTrace();
				flexLog("Exception ocurred. Exception = " + e);
			}
		} else {

			try {

				msgUser =
					(datapro.eibs.beans.ESS0030DSMessage) session.getAttribute(
						"currUser");

				// Here we should get the path from the user profile
				LangPath = super.rootPath + msgUser.getE01LAN() + "/";

				//////////////////////////////////////////////////////////////
				String user = msgUser.getH01USR();
				Consumer consumer = new Consumer();
				consumer.setPhoneNbrHome(req.getParameter("E81HPN"));
				consumer.setPhoneNbrWork(req.getParameter("E81PHN"));

				String legalType = req.getParameter("E81LGT");
				if (legalType.equals("2")) {
					consumer.setType("P");
					consumer.setFirstNm(req.getParameter("E81FRN"));
					consumer.setMiddleNm(req.getParameter("E81MDI"));
					consumer.setLastNm(req.getParameter("E81LSN"));
					consumer.setGovernmentNbr(req.getParameter("E81SSN"));
					consumer.setIdentificationStateNbr(
						req.getParameter("E81DRL"));
					consumer.setIdentificationStateCd(
						req.getParameter("E81DRS"));
					String dte =
						Util.addLeftChar('0', 2, req.getParameter("E81BMM"))
							+ Util.addLeftChar(
								'0',
								2,
								req.getParameter("E81BDD"))
							+ Util.addLeftChar(
								'0',
								4,
								req.getParameter("E81BYY"));
					consumer.setBirthDt(dte);
				} else {
					consumer.setType("B");
					consumer.setBusinessNm(req.getParameter(""));
					consumer.setBusinessFederalIdentificationNbr(
						req.getParameter(""));
				}
				consumer.setStreetAddressTxt(req.getParameter("E81ADR"));
				consumer.setCityNm(req.getParameter("E81CTY"));
				consumer.setStateCd(req.getParameter("E81STE"));
				consumer.setPostalPlusFourCd(req.getParameter("E81ZPC"));
				consumer.setAddressYearsNbr(req.getParameter("E81ADY"));
				consumer.setAddressMonthsNbr(req.getParameter("E81ADM"));
				consumer.setCountryCd(req.getParameter("E81CCS"));

				if (false) {
					consumer.setPrevStreetAddressTxt(
						req.getParameter("E81PAD"));
					consumer.setPrevCityNm(req.getParameter("E81PCT"));
					consumer.setPrevStateCd(req.getParameter("E81PST"));
					consumer.setPrevPostalPlusFourCd(
						req.getParameter("E81PZP"));
					consumer.setPrevAddressYearsNbr(req.getParameter(""));
					consumer.setPrevAddressMonthsNbr(req.getParameter(""));
					consumer.setPrevCountryCd(req.getParameter(""));
				}

				//////////////////////////////////////////////////////////////
				procSetEFundsParams(msgUser);
				procReqToEFunds(res, user, consumer, session);

			} catch (Exception e) {
				flexLog("Error: " + e);
				res.sendRedirect(
					super.srctx + LangPath + super.sckNotRespondPage);
			}

		}

	}

	/**
	 * This method was created in VisualAge.
	 */

	public JBQualiFile extractQualiFile(String xmlSource)
		throws ServletException, IOException {
		JBQualiFile bean = new JBQualiFile();
		DocumentBuilderFactory docBuilderFactory = null;
		DocumentBuilder docBuilder = null;
		Document doc = null;

		String custDetRefNbr = "";
		String creditBureauRefNbr = "";
		String creditBureauId = "";
		String transTrackingId = "";
		String accAcceptanceTxt = "ERROR";
		String creditBureauScoreNbr = "";
		String scoreNbr = "0";
		String action = "";

		try {
			docBuilderFactory = DocumentBuilderFactory.newInstance();
			docBuilder = docBuilderFactory.newDocumentBuilder();
			doc =
				docBuilder.parse(
					new java.io.ByteArrayInputStream(xmlSource.getBytes()));
			doc.getDocumentElement().normalize();

			NodeList transList = doc.getElementsByTagName("Transaction");
			if (transList.getLength() > 0) {
				Node trans = transList.item(0);
				NodeList errorT =
					((Element) trans).getElementsByTagName("Error");
				if (errorT.getLength() == 0) {

					Element trackingId =
						(Element) ((Element) trans).getElementsByTagName(
							"TransactionTrackingId").item(
							0);
					transTrackingId =
						trackingId.getChildNodes().item(0).getNodeValue();

					NodeList listOfConsumer =
						doc.getElementsByTagName("Consumer");
					Node consumer = listOfConsumer.item(0);
					NodeList errorC =
						((Element) consumer).getElementsByTagName("Error");
					if (errorC.getLength() == 0) {
						Element chexSystemsResp =
							(Element)
								((Element) consumer).getElementsByTagName(
								"ChexSystemsResponse").item(
								0);
						NodeList errorCSR =
							chexSystemsResp.getElementsByTagName("Error");
						NodeList pleaseCallCd =
							chexSystemsResp.getElementsByTagName(
								"PleaseCallCd");

						if (errorCSR.getLength() == 0
							&& pleaseCallCd.getLength() == 0) {

							NodeList closureInfo =
								chexSystemsResp.getElementsByTagName(
									"ClosureInformation");
							//TODO get info of ReportedClosure (
							NodeList qualiFileRespList =
								((Element) consumer).getElementsByTagName(
									"QualiFileResponse");
							if (qualiFileRespList.getLength() > 0) {
								Node qualiFileResp = qualiFileRespList.item(0);
								NodeList errorQFR =
									(
										(
											Element) qualiFileResp)
												.getElementsByTagName(
										"Error");
								NodeList qualiFileNotPerformedCd =
									(
										(
											Element) qualiFileResp)
												.getElementsByTagName(
										"QualiFileNotPerformedCd");
								if (errorQFR.getLength() == 0
									&& qualiFileNotPerformedCd.getLength() == 0) {
									//	Get value of ReferenceInformation elements
									NodeList referenceInfo =
										(
											(
												Element) qualiFileResp)
													.getElementsByTagName(
											"ReferenceInformation");
									Element referenceInfoElem =
										(Element) referenceInfo.item(0);
									try {	
									custDetRefNbr =
										((Element) referenceInfoElem
											.getElementsByTagName("ConsumerDetailReferenceNbr")
											.item(0))
											.getChildNodes()
											.item(0)
											.getNodeValue();
									} catch (Exception e) {custDetRefNbr = "";}
									try{		
										creditBureauRefNbr =
											((Element) referenceInfoElem
												.getElementsByTagName("CreditBureauReferenceNbr")
												.item(0))
												.getChildNodes()
												.item(0)
												.getNodeValue();
									} catch (Exception e) {creditBureauRefNbr = "";}	
									try {											
									creditBureauId =
										((Element) referenceInfoElem
											.getElementsByTagName("CreditBureauId")
											.item(0))
											.getChildNodes()
											.item(0)
											.getNodeValue();
									} catch (Exception e) { creditBureauId = "";}		
									//	Get value of AccountActionInformation elements
									NodeList accActionInfo =
										(
											(
												Element) qualiFileResp)
													.getElementsByTagName(
											"AccountActionInformation");
											
									Element accActionInfoElem =
										(Element) accActionInfo.item(0);
									try {
									accAcceptanceTxt =
										((Element) accActionInfoElem
											.getElementsByTagName("AccountAcceptanceTxt")
											.item(0))
											.getChildNodes()
											.item(0)
											.getNodeValue();
									} catch (Exception e) { accAcceptanceTxt = "";}
									//  Get value of QualiFileInformation elements
									NodeList qualiFileInfo =
										(
											(
												Element) qualiFileResp)
													.getElementsByTagName(
											"QualiFileInformation");
									Element qualiFileInfoElem =
										(Element) qualiFileInfo.item(0);
									try {
									scoreNbr =
										((Element) qualiFileInfoElem
											.getElementsByTagName("ScoreNbr")
											.item(0))
											.getChildNodes()
											.item(0)
											.getNodeValue();
									} catch (Exception e) {scoreNbr = "";}
									//  Get value of CreditBureauInformation elements
									
									NodeList creditBureauInfo =
										((Element) qualiFileResp)
											.getElementsByTagName("CreditBureauInformation");
									Element creditBureauInfoElem =
										(Element) creditBureauInfo.item(0);
									try {
									creditBureauScoreNbr =
										((Element) creditBureauInfoElem
											.getElementsByTagName("CreditBureauScoreNbr")
											.item(0))
											.getChildNodes()
											.item(0)
											.getNodeValue();
									} catch (Exception e) {creditBureauScoreNbr = "";}
								}
							}

						}
					} else {
						bean.setFinalAction("ERROR");
					}
				}

			}

			bean.setTransactionTrackingId(transTrackingId);
			bean.setConsumerDetailReferenceNbr(custDetRefNbr);
			bean.setCreditBureauId(creditBureauId);
			bean.setCreditBureauReferenceNbr(creditBureauRefNbr);
			bean.setAccountAcceptanceText(accAcceptanceTxt);
			bean.setCreditBureauScoreNbr(creditBureauScoreNbr);
			bean.setScoreNbr(scoreNbr);
			bean.setFinalAction(accAcceptanceTxt);
			bean.setXmlSource(xmlSource);

		} catch (Exception e) {
			bean = null;
		}
		return bean;
	}
	/**
	 * This method was created in VisualAge.
	 */
	protected void procSetEFundsParams(ESS0030DSMessage user)
		throws ServletException, IOException {

		MessageRecord newmessage = null;
		ESD000508Message msgEF = null;
		ELEERRMessage msgError = null;
		Socket s = null;
		MessageContext mc = null;
		boolean IsNotError = false;

		String opCode = "0002";

		// Send Initial data
		try {

			s = new Socket(super.hostIP, super.iniSocket + 1);
			s.setSoTimeout(super.sckTimeOut);
			mc =
				new MessageContext(
					new DataInputStream(
						new BufferedInputStream(s.getInputStream())),
					new DataOutputStream(
						new BufferedOutputStream(s.getOutputStream())),
					"datapro.eibs.beans");

			msgEF = (ESD000508Message) mc.getMessageRecord("ESD000508");
			msgEF.setH08USERID(user.getH01USR());
			msgEF.setH08PROGRM("ESD0005");
			msgEF.setH08TIMSYS(getTimeStamp());
			msgEF.setH08SCRCOD("01");
			msgEF.setH08OPECOD(opCode);
			try {
				msgEF.setE08EFNBNK(user.getE01UBK());
			} catch (Exception e) {
			}
			msgEF.send();
			msgEF.destroy();

			// Receive Error Message
			newmessage = mc.receiveMessage();

			if (newmessage.getFormatName().equals("ELEERR")) {
				msgError = (ELEERRMessage) newmessage;
				IsNotError = msgError.getERRNUM().equals("0");
				flexLog("IsNotError = " + IsNotError);

			} else
				flexLog("Message " + newmessage.getFormatName() + " received.");

			// Receive Data

			newmessage = mc.receiveMessage();

			if (newmessage.getFormatName().equals("ESD000508")) {

				msgEF = (ESD000508Message) newmessage;

				_customerId = msgEF.getE08EFNCID();
				_secUserId = msgEF.getE08EFNUID();
				_issuer = _secUserId;
				_secPassword = msgEF.getE08EFNPWD();

			} else
				flexLog("Message " + newmessage.getFormatName() + " received.");

		} catch (Exception e) {
			_customerId = _ResourceBundle.getString("customer.id");
			_secUserId = _ResourceBundle.getString("property.securityuserid");
			_issuer = _ResourceBundle.getString("property.issuer");
			_secPassword =
				_ResourceBundle.getString("property.securitypassword");
		} finally {
			s.close();
		}

	}
	/**
		 * This method was created in VisualAge.
		 */
	protected void procReqToEFunds(
		HttpServletResponse response,
		String User,
		Consumer consumer,
		HttpSession ses)
		throws TransformerFactoryConfigurationError {

		try {
			URL url = new URL(_PurchaseRequestURL);
			HttpURLConnection conn = (HttpURLConnection) url.openConnection();
			conn.setRequestMethod("POST");
			conn.setRequestProperty("Content-Type", "application/soap");
			conn.setDoInput(true);
			conn.setDoOutput(true);
			conn.setUseCaches(false);
			OutputStream os = conn.getOutputStream();
			PrintWriter urlWriter = new PrintWriter(new OutputStreamWriter(os));

			String req = getRequest(consumer);
			String saml = getAssertionStr(generateAssertion());
			Envelope env = SoapEnvComposer.createSoapEnvelope(saml, req);
			String envStr = SoapEnvComposer.soapEnvToString(env);
			int ix = envStr.indexOf("<AssertionType/>");
			if (ix > 0) {
				envStr =
					envStr.substring(0, ix)
						+ "<AssertionType>InquiryAuthentication</AssertionType>"
						+ envStr.substring(ix + 16);
			}
			urlWriter.println(envStr);
			flexLog(envStr);

			urlWriter.close();

			// response
			if (conn.getResponseCode() == 200) {
				InputStream is = conn.getInputStream();
				BufferedReader br =
					new BufferedReader(new InputStreamReader(is));
				StringBuffer rpStrBuf = new StringBuffer();
				String tempStr = null;
				while ((tempStr = br.readLine()) != null) {
					rpStrBuf.append(tempStr);
				}
				flexLog("The Output is : " + rpStrBuf);
				// TODO Save xml response to disk for future inquiry

				// Extract JBQualiFile data and put it into session
				JBQualiFile beanQF = extractQualiFile(rpStrBuf.toString());

				if (beanQF != null) {
					beanQF.setFirstNm(consumer.getFirstNm());
					beanQF.setMiddleNm(consumer.getMiddleNm());
					beanQF.setLastNm(consumer.getLastNm());
					beanQF.setIdentificationStateCd(
						consumer.getIdentificationStateCd());
					beanQF.setIdentificationStateNbr(
						consumer.getIdentificationStateNbr());
					if (!beanQF.getFinalAction().equals("ERROR"))
						ses.setAttribute("qualiFile", beanQF);
				}
				// The servlet returns HTML.
				response.setContentType("text/html; charset=UTF-8");
				// Output goes in the response stream.
				PrintWriter out = response.getWriter();
				try {
					TransformerFactory tFactory =
						TransformerFactory.newInstance();
					//get the real path for xml and xsl files.
					String ctx =
						getServletContext().getRealPath("") + "/efunds/xml/";
					// Get the XML input document and the stylesheet.
					Source xmlSource =
						new StreamSource(
							new ByteArrayInputStream(
								rpStrBuf.toString().getBytes()));
					Source xslSource =
						new StreamSource(
							new URL("file", "", ctx + "wcp_qf_response.xsl")
								.openStream());
					// Generate the transformer.
					Transformer transformer =
						tFactory.newTransformer(xslSource);
					// Perform the transformation, sending the output to the response.
					transformer.transform(xmlSource, new StreamResult(out));
				} catch (Exception e) {
					out.write(e.getMessage());
					e.printStackTrace(out);
				}
				out.close();
			}
		} catch (Exception e) {
			flexLog("Error during the Message send:" + e);
		}

		// if (_megalog) startResponseLogging();

		//We are basically just forwarding the response from the remote server back to the client's browser.
		// In a real B2B application the response would probably be in the form of a SOAP (or other XML) document and we would
		// have to parse it and then build a relevant HTML page to send back to the browser.
		/*
		SOAPTransport trans = msg.getSOAPTransport();
		BufferedReader in = trans.receive();
		try
		{
			String line = null;
			while((line = in.readLine()) != null)
			{
				out.println(line);
				if (_megalog) logResponseLine(line);
			}         
		}      
		catch(Exception e)      
		{         
			flexLog("Error during the Response Forwarding:" + e);
		}
		*/
	}

	public String getSAML() {
		StringBuffer samlStrBuf = new StringBuffer();
		String userid = _ResourceBundle.getString("property.securityuserid");
		String password =
			_ResourceBundle.getString("property.securitypassword");

		samlStrBuf.append(
			"		<saml:AssertionList AssertionID=\"168.135.140.106.4637825435234\"");
		samlStrBuf.append(
			"		IssueInstant=\"2004-05-22T15:33:00Z\" Issuer=\""
				+ userid
				+ "\"");
		samlStrBuf.append("		MajorVersion=\"1\" MinorVersion=\"0\"");
		samlStrBuf.append(
			"		xmlns:saml=\"http://www/oasis-open.org/committees/security/docs/draft-sstc-schema-assertion-16.xsd\">");
		samlStrBuf.append(
			"			<saml:Conditions  NotBefore=\"2002-05-13T22-:40:31Z\" NotOnAfter=\"2003-05-13T22:4031Z\">");
		samlStrBuf.append(
			"				<saml:AbstractCondition xmlns:xsi=\"http://www.w3.org/2001/SMLSchema-instance\" xsi:type=\"AudienceRestrictionConditionType\">");
		samlStrBuf.append(
			"					<saml:Audience>www.e-funds.com/service/message/processMessage</saml:Audience>");
		samlStrBuf.append("				</saml:AbstractCondition>");
		samlStrBuf.append("			</saml:Conditions>");
		samlStrBuf.append("			<saml:AttributeStatement>");
		samlStrBuf.append("				<saml:Subject>");
		samlStrBuf.append(
			"					<saml:NameIdentifier Name=\""
				+ userid
				+ "\" SecurityDomain=\"www.efunds-client.com\"/>");
		samlStrBuf.append("					<saml:SubjectConfirmation>");
		samlStrBuf.append(
			"						<saml:ConfirmationMethod>http://www.oasis-open.org/committees/security/docs/draft-sstc-core-25/password</saml:ConfirmationMethod>");
		samlStrBuf.append(
			"						<saml:SubjectConfirmationData>"
				+ password
				+ "</saml:SubjectConfirmationData>");
		samlStrBuf.append("					</saml:SubjectConfirmation>");
		samlStrBuf.append("				</saml:Subject>");
		samlStrBuf.append(
			"				<saml:Attribute AttributeName=\"AssertionType\" AttributeNamespace=\"http://www.efunds.com/message/assertion/type.xsd\">");
		samlStrBuf.append("					<saml:AttributeValue>");
		samlStrBuf.append(
			"						<AssertionType>InquiryAuthentication</AssertionType>");
		samlStrBuf.append("					</saml:AttributeValue>");
		samlStrBuf.append("				</saml:Attribute>");
		samlStrBuf.append("			</saml:AttributeStatement>");
		samlStrBuf.append("		</saml:AssertionList>");

		return samlStrBuf.toString();
	}

	//create the assertion that will be attached to the SOAP message
	public SAML_AssertionList generateAssertion() {
		// String[] perms = {Limit};
		String AudienceRestriction =
			_ResourceBundle.getString("inquiry.audiencerestriction");
		String[] auds = { AudienceRestriction };

		//create the conditions
		Calendar RightNow = Calendar.getInstance();
		//adjust for _preSkew
		RightNow.add(Calendar.MINUTE, -1 * _preSkew);
		Date StartDate = RightNow.getTime();
		RightNow.add(Calendar.MINUTE, _validity);
		Date EndDate = RightNow.getTime();
		SAML_Conditions Conditions =
			new SAML_Conditions(StartDate, EndDate, auds);

		//create the subject
		String SecurityDomain =
			_ResourceBundle.getString("property.securitydomain");
		String SecurityUserID = _secUserId;
		//_ResourceBundle.getString("property.securityuserid");
		String SecurityPassword = _secPassword;
		//_ResourceBundle.getString("property.securitypassword");
		SAML_NameIdentifier[] NameIdentifier =
			{ new SAML_NameIdentifier(SecurityDomain, SecurityUserID)};
		SAML_Subject Subject = new SAML_Subject(NameIdentifier);

		String[] methods =
			{ "http://www.oasis-open.org/committees/security/docs/draft-sstc-core-25/password" };
		SAML_SubjectConfirmation SubjectConfirmation =
			new SAML_SubjectConfirmation(methods, SecurityPassword, null);
		Subject.addSubjectConfirmation(SubjectConfirmation);

		//make an Attribute Statement
		SAML_AttributeStatement attributeStatement = null;
		try {
			Document doc = _documentBuilder.newDocument();
			Element elem = doc.createElement("AssertionType");
			elem.setNodeValue("InquiryAuthentication");
			Element[] elements = { elem };
			SAML_Attribute att =
				new SAML_Attribute(
					"AssertionType",
					"http://www.efunds.com/message/assertion/type.xsd",
					elements);
			SAML_Attribute[] attributes = { att };

			attributeStatement =
				new SAML_AttributeStatement(attributes, Subject);
		} catch (Exception e) {
			e.printStackTrace();
		}

		//now build the AssertionList that wraps statements
		SAML_Statement[] Statements = { attributeStatement };
		SAML_AssertionList AssertionList =
			new SAML_AssertionList(null, _issuer, Conditions, Statements);

		return AssertionList;
	}

	//this will return a DOM Element representing the assertion passed in.
	// if the signing option is on the Element returned will be a to a signed Assertion
	public Element getAssertionElement(SAML_AssertionList AssertionList) {
		Element ReturnElement = null;
		try {
			if (_sign) {
				SAML_SignedAssertionList signedAssertion =
					new SAML_SignedAssertionList(
						AssertionList,
						_dsig,
						_signAlias,
						_signKeyPass);
				ReturnElement =
					signedAssertion
						.getAssertionListDocument()
						.getDocumentElement();
			} else {
				Document doc = AssertionList.toDocument();
				ReturnElement = doc.getDocumentElement();
			}
		} catch (Exception e) {
			flexLog("Error during the getAssertionElement:" + e);
		}

		return ReturnElement;
	}

	public String getAssertionStr(SAML_AssertionList AssertionList) {
		String rt = null;
		try {
			if (_sign) {
				SAML_SignedAssertionList signedAssertion =
					new SAML_SignedAssertionList(
						AssertionList,
						_dsig,
						_signAlias,
						_signKeyPass);
				rt = signedAssertion.getAssertionListDocument().toString();
			} else {
				rt = AssertionList.toString();
			}
		} catch (Exception e) {
			flexLog("Error during the getAssertionElement:" + e);
		}

		return rt;
	}

	public String getRequest(Consumer consumer) {
		StringBuffer rqStrBuf = new StringBuffer();

		rqStrBuf.append(
			"<Request xmlns:xsi='http://www.w3.org/2001/XMLSchema-instance'");
		rqStrBuf.append(" xsi:noNamespaceSchemaLocation='ecs_request.xsd'>");
		// Transaction
		rqStrBuf.append("<Transaction>");
		rqStrBuf.append("<CustomerId>" + _customerId + "</CustomerId>");
		rqStrBuf.append(
			"<ChexSystemsVersionNbr>"
				+ _accountChexVersion
				+ "</ChexSystemsVersionNbr>");
		rqStrBuf.append(
			"<QualiFileVersionNbr>"
				+ _qualiFileVersion
				+ "</QualiFileVersionNbr>");
		rqStrBuf.append("</Transaction>");
		// Consumer
		rqStrBuf.append("<Consumer>");
		if (consumer.getType().equals("P")) {
			// Person consumer
			rqStrBuf.append("<PersonConsumer personConsumerType=\"First\">");
			rqStrBuf.append("<LastNm>" + consumer.getLastNm() + "</LastNm>");
			rqStrBuf.append("<FirstNm>" + consumer.getFirstNm() + "</FirstNm>");
			rqStrBuf.append(
				"<MiddleNm>" + consumer.getMiddleNm() + "</MiddleNm>");
			if (!consumer.getBirthDt().equals("")) {
				rqStrBuf.append("<Demographics>");
				rqStrBuf.append(
					"<BirthDt>" + consumer.getBirthDt() + "</BirthDt>");
				rqStrBuf.append("</Demographics>");
			}
			rqStrBuf.append("<PersonIdentifiers>");
			rqStrBuf.append(
				"<GovernmentNbr>"
					+ consumer.getGovernmentNbr()
					+ "</GovernmentNbr>");
			if (!consumer.getIdentificationStateNbr().equals("")) {
				rqStrBuf.append(
					"<IdentificationStateNbr>"
						+ consumer.getIdentificationStateNbr()
						+ "</IdentificationStateNbr>");
				rqStrBuf.append(
					"<IdentificationStateCd>"
						+ consumer.getIdentificationStateCd()
						+ "</IdentificationStateCd>");
			}
			rqStrBuf.append("</PersonIdentifiers>");
			rqStrBuf.append("</PersonConsumer>");
		} else {
			// Business consumer
			rqStrBuf.append("<BusinessConsumer personConsumerType=\"First\">");
			rqStrBuf.append(
				"<BusinessNm>" + consumer.getBusinessNm() + "</BusinessNm>");
			rqStrBuf.append("<BusinessIdentifiers>");
			rqStrBuf.append(
				"<BusinessFederalIdentificationNbr>"
					+ consumer.getBusinessFederalIdentificationNbr()
					+ "</BusinessFederalIdentificationNbr>");
			rqStrBuf.append("</BusinessIdentifiers>");
			rqStrBuf.append("</BusinessConsumer>");
		}
		// Telecommunication Info
		if (!consumer.getPhoneNbrHome().equals("")
			|| !consumer.getPhoneNbrWork().equals("")) {
			rqStrBuf.append("<TeleCommInfo>");
			if (!consumer.getPhoneNbrHome().equals("")) {
				rqStrBuf.append("<PhoneNbr phoneRoleCd=\"Home\">");
				rqStrBuf.append(
					"<CompositePhoneNbr>"
						+ consumer.getPhoneNbrHome()
						+ "</CompositePhoneNbr>");
				rqStrBuf.append("</PhoneNbr>");
			}
			if (!consumer.getPhoneNbrWork().equals("")) {
				rqStrBuf.append("<PhoneNbr phoneRoleCd=\"Work\">");
				rqStrBuf.append(
					"<CompositePhoneNbr>"
						+ consumer.getPhoneNbrWork()
						+ "</CompositePhoneNbr>");
				rqStrBuf.append("</PhoneNbr>");
			}
			rqStrBuf.append("</TeleCommInfo>");
		}
		// Current Address
		rqStrBuf.append("<CurrentAddress>");
		if (!consumer.getStreetAddressTxt().equals("")) {
			rqStrBuf.append(
				"<StreetAddressTxt>"
					+ consumer.getStreetAddressTxt()
					+ "</StreetAddressTxt>");
		}
		if (!consumer.getCityNm().equals("")) {
			rqStrBuf.append("<CityNm>" + consumer.getCityNm() + "</CityNm>");
		}
		rqStrBuf.append("<StateCd>" + consumer.getStateCd() + "</StateCd>");
		if (!consumer.getPostalPlusFourCd().equals("")) {
			rqStrBuf.append(
				"<PostalPlusFourCd>"
					+ consumer.getPostalPlusFourCd()
					+ "</PostalPlusFourCd>");
		}
		if (!consumer.getAddressYearsNbr().equals("")) {
			rqStrBuf.append(
				"<AddressYearsNbr>"
					+ consumer.getAddressYearsNbr()
					+ "</AddressYearsNbr>");
		}
		if (!consumer.getAddressMonthsNbr().equals("")) {
			rqStrBuf.append(
				"<AddressMonthsNbr>"
					+ consumer.getAddressMonthsNbr()
					+ "</AddressMonthsNbr>");
		}
		if (!consumer.getCountryCd().equals("")) {
			rqStrBuf.append(
				"<CountryCd>" + consumer.getCountryCd() + "</CountryCd>");
		}
		rqStrBuf.append("</CurrentAddress>");
		// Previous Address
		if (!consumer.getPrevStateCd().equals("")) {
			rqStrBuf.append("<PreviousAddress sequenceNbr=\"1\">");
			if (!consumer.getPrevStreetAddressTxt().equals("")) {
				rqStrBuf.append(
					"<StreetAddressTxt>"
						+ consumer.getPrevStreetAddressTxt()
						+ "</StreetAddressTxt>");
			}
			if (!consumer.getPrevCityNm().equals("")) {
				rqStrBuf.append(
					"<CityNm>" + consumer.getPrevCityNm() + "</CityNm>");
			}
			rqStrBuf.append(
				"<StateCd>" + consumer.getPrevStateCd() + "</StateCd>");
			if (!consumer.getPrevPostalPlusFourCd().equals("")) {
				rqStrBuf.append(
					"<PostalPlusFourCd>"
						+ consumer.getPrevPostalPlusFourCd()
						+ "</PostalPlusFourCd>");
			}
			if (!consumer.getPrevAddressYearsNbr().equals("")) {
				rqStrBuf.append(
					"<AddressYearsNbr>"
						+ consumer.getPrevAddressYearsNbr()
						+ "</AddressYearsNbr>");
			}
			if (!consumer.getPrevAddressMonthsNbr().equals("")) {
				rqStrBuf.append(
					"<AddressMonthsNbr>"
						+ consumer.getPrevAddressMonthsNbr()
						+ "</AddressMonthsNbr>");
			}
			if (!consumer.getPrevCountryCd().equals("")) {
				rqStrBuf.append(
					"<CountryCd>"
						+ consumer.getPrevCountryCd()
						+ "</CountryCd>");
			}
			rqStrBuf.append("</PreviousAddress>");
		}
		rqStrBuf.append("</Consumer>");
		rqStrBuf.append("</Request>");

		return rqStrBuf.toString();

	}
	//this function builds a request XML document representing the body of the SOAP envelope. This is used to 
	// compliant with efunds specs.
	public Element getBodyElement(Consumer consumer) {
		String NameSpace =
			getServletContext()
				+ _ResourceBundle.getString("property.attributenamespace");
		Document doc = _documentBuilder.newDocument();
		Element root = doc.createElementNS(NameSpace, "Request");

		Element l1 = null;
		Element l2 = null;
		Element l3 = null;
		Element l4 = null;
		// Transaction element
		l1 = doc.createElement("Transaction");
		l2 = doc.createElement("CustomerId");
		l2.setNodeValue(_customerId);
		l1.appendChild(l2);
		l2 = doc.createElement("ChexSystemVersionNbr");
		l2.setNodeValue("V006");
		l1.appendChild(l2);
		l2 = doc.createElement("QualiFileVersionNbr");
		l2.setNodeValue("N001");
		l1.appendChild(l2);
		root.appendChild(l1);
		// Consumer Element
		l1 = doc.createElement("Consumer");
		l2 = doc.createElement("PersonConsumer");
		l2.setAttribute("personConsumerType", "First");
		l3 = doc.createElement("LastNm");
		l3.setNodeValue(consumer.getLastNm());
		l2.appendChild(l3);
		l3 = doc.createElement("FirstNm");
		l3.setNodeValue(consumer.getFirstNm());
		l2.appendChild(l3);
		l3 = doc.createElement("MiddleNm");
		l3.setNodeValue(consumer.getMiddleNm());
		l2.appendChild(l3);
		l3 = doc.createElement("Demographics");
		l4 = doc.createElement("BirthDt");
		l4.setNodeValue(consumer.getBirthDt());
		l3.appendChild(l4);
		l3 = doc.createElement("PersonIdentifiers");
		l4 = doc.createElement("GovernmentNbr");
		l4.setNodeValue(consumer.getGovernmentNbr());
		l3.appendChild(l4);
		l4 = doc.createElement("IdentificationStateNbr");
		l4.setNodeValue(consumer.getIdentificationStateNbr());
		l3.appendChild(l4);
		l4 = doc.createElement("IdentificationStateCd");
		l4.setNodeValue(consumer.getIdentificationStateCd());
		l3.appendChild(l4);
		l2.appendChild(l3);
		l1.appendChild(l2);
		l2 = doc.createElement("TeleCommInfo");
		l3 = doc.createElement("PhoneNbr");
		l3.setAttribute("phoneRoleCd", "Home");
		l4 = doc.createElement("CompositePhoneNbr");
		l4.setNodeValue(consumer.getPhoneNbrHome());
		l3.appendChild(l4);
		l2.appendChild(l3);
		l1.appendChild(l2);
		root.appendChild(l1);

		return root;
	}

	//this is a bit of a hack to deal with the fact that we want an enveloped signature, but we are working with a Envelope
	// object. So we need to convert the Envelope to a string, do an enveloped signature on the string, then pull the signature
	// out of the signed string and put it into the body of the Envelope.
	//
	// Note that this is just a sample and you could get around this protection by including a Signature element in body before 
	// calling this function...
	public void signEnvelope(Envelope env) {
		String SignedEnvelope = null;
		Element SignatureElement = null;
		Document SignedDoc = null;
		boolean FoundOne = false;

		SOAPMappingRegistry smr = new SOAPMappingRegistry();
		StringWriter payloadSW = new StringWriter();

		try {
			env.marshall(payloadSW, smr);

			//now sign this thing
			// Note that we have to pass the final boolean in
			// When we hack around the SOAP envelope by manually inserting the signature as an envelope element
			// we would gain a CR after the </Signature> when the Envelope marshalls itself since it inserts one between
			// each enevelop element. Thus we need to put one in there when we calculate the signature to make sure
			// the digests will match.
			SignedEnvelope =
				_dsig.signXML(
					payloadSW.toString(),
					_signAlias,
					_signKeyPass,
					true);

			InputSource is = new InputSource(new StringReader(SignedEnvelope));
			SignedDoc = _documentBuilder.parse(is);

			NodeList Nodes = SignedDoc.getDocumentElement().getChildNodes();
			int NodeCount = Nodes.getLength();

			if (NodeCount > 0) {
				for (int i = 0; i < NodeCount; i++) {
					Node CurrentNode = Nodes.item(i);

					if (CurrentNode.getNodeType() == Node.ELEMENT_NODE) {
						String TagName = ((Element) CurrentNode).getLocalName();

						if (TagName.compareTo("Signature") == 0) {
							SignatureElement = (Element) CurrentNode;
							i = NodeCount;
							FoundOne = true;
						}
					}
				}
			}
		} catch (Exception e) {
			flexLog("Error during SOAP envelope signing\n:" + e);
		}

		if (!FoundOne) {
			flexLog("Failed to find the signature in the signed envelope\n");
		} else {
			Vector EnvelopeElements = env.getEnvelopeEntries();
			if (EnvelopeElements == null)
				EnvelopeElements = new Vector();
			EnvelopeElements.add(SignatureElement);
			env.setEnvelopeEntries(EnvelopeElements);
		}

	}

	//this is a utility function used to hack up an HTML display of an XML string.
	public String displayConvert(String input) {
		StringCharacterIterator iter = new StringCharacterIterator(input);
		StringBuffer buf = new StringBuffer();

		for (char c = iter.first();
			c != CharacterIterator.DONE;
			c = iter.next()) {
			if (c == '>') {
				buf.append("&gt;");
			} else if (c == '<') {
				buf.append("&lt;");
			} else if (c == '\n') {
				buf.append("<BR>\n");
			} else {
				buf.append(c);
			}
		}

		return buf.toString();
	}

}
