package datapro.eibs.params;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.BufferedReader;
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
import javax.xml.transform.TransformerFactoryConfigurationError;

import org.apache.soap.Envelope;
import org.apache.soap.encoding.SOAPMappingRegistry;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;

import com.netegrity.jsaml.assertion.SAML_AssertionList;
import com.netegrity.jsaml.assertion.SAML_Attribute;
import com.netegrity.jsaml.assertion.SAML_AttributeStatement;
import com.netegrity.jsaml.assertion.SAML_AuthorizationStatement;
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
import datapro.eibs.beans.UserPos;
import datapro.eibs.generic.SoapEnvComposer;
import datapro.eibs.master.SuperServlet;
import datapro.eibs.sockets.CharacterField;
import datapro.eibs.sockets.MessageContext;
import datapro.eibs.sockets.MessageField;
import datapro.eibs.sockets.MessageRecord;

public class JSESD0005EFUNDS extends SuperServlet {

	protected static final int A_INTERFACE = 5;
	protected static final int A_INTERFACE_PARAMS = 6;

	protected String LangPath = "S";

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
				_ResourceBundle.getString("property.adminhurl");
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

	public void service(HttpServletRequest req, HttpServletResponse res)
		throws ServletException, IOException {

		Socket s = null;
		MessageContext mc = null;

		ESS0030DSMessage msgUser = null;
		HttpSession session = null;

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

			int screen = A_INTERFACE;

			msgUser =
				(datapro.eibs.beans.ESS0030DSMessage) session.getAttribute(
					"currUser");

			// Here we should get the path from the user profile
			LangPath = super.rootPath + msgUser.getE01LAN() + "/";

			try {
				screen = Integer.parseInt(req.getParameter("SCREEN"));
			} catch (Exception e) {
				flexLog("Screen set to default value");
			}

			try {
				flexLog("Opennig Socket Connection");
				s = new Socket(super.hostIP, getInitSocket(req) + 1);
				s.setSoTimeout(super.sckTimeOut);
				mc =
					new MessageContext(
						new DataInputStream(
							new BufferedInputStream(s.getInputStream())),
						new DataOutputStream(
							new BufferedOutputStream(s.getOutputStream())),
						"datapro.eibs.beans");

				switch (screen) {
					case A_INTERFACE :
						procActionInterface(mc, msgUser, req,	res, session);
						break;
					//case A_INTERFACE_PARAMS :
					//	procActionInterfaceParams(mc, msgUser, req,	res, session);
					//	break;						
					default :
						res.sendRedirect(
							super.srctx + LangPath + super.devPage);
						break;
				}

			} catch (Exception e) {
				e.printStackTrace();
				int sck = getInitSocket(req) + 1;
				flexLog("Socket not Open(Port " + sck + "). Error: " + e);
				res.sendRedirect(super.srctx + LangPath + super.sckNotOpenPage);
				//return;
			} finally {
				s.close();
			}
		}

	}

	/**
		 * This method was created in VisualAge.
		 */
	protected boolean procReqToEFunds(HttpSession ses, String newPassword)
		throws TransformerFactoryConfigurationError {

		boolean ok = true;
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

			String req = getRequest();
			String saml = getAssertionStr(generateAssertion(newPassword));
			Envelope env = SoapEnvComposer.createSoapEnvelope(saml, req);
			String envStr = SoapEnvComposer.soapEnvToString(env);
			int ix = envStr.indexOf("<AssertionType/>");
			if (ix > 0) {
				envStr =
					envStr.substring(0, ix)
						+ "<AssertionType>PasswordChange</AssertionType>"
						+ envStr.substring(ix + 16);
			}
			ix = envStr.indexOf("<NewPassword/>");
			if (ix > 0) {
				envStr =
					envStr.substring(0, ix)
						+ "<NewPassword>"
						+ newPassword
						+ "</NewPassword>"
						+ envStr.substring(ix + 14);
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

				// TODO set ret if there is an error from eFunds
				ok = extractSecurityResponse(ses, rpStrBuf.toString());

			}
		} catch (Exception e) {
			flexLog("Error during the Message send:" + e);
		}

		return ok;

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
	public SAML_AssertionList generateAssertion(String newPassword) {
		// String[] perms = {Limit};
		String AudienceRestriction =
			_ResourceBundle.getString("admin.audiencerestriction");
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

		//make an Authorization Statement
		SAML_AuthorizationStatement authorizationStatement = null;
		try {
			String[] strs = { "ModifyPassword" };
			authorizationStatement =
				new SAML_AuthorizationStatement(
					"/security/userRepository",
					strs,
					"Permit",
					Subject);
		} catch (Exception e) {
			e.printStackTrace();
		}

		//make an Attribute Statement
		SAML_AttributeStatement attributeStatement = null;
		try {
			Document doc = _documentBuilder.newDocument();

			Element elem1 = doc.createElement("AssertionType");
			elem1.setNodeValue("PasswordChange");
			Element[] elements1 = { elem1 };
			SAML_Attribute att1 =
				new SAML_Attribute(
					"AssertionType",
					"http://www.efunds.com/message/assertion/type.xsd",
					elements1);

			Element elem2 = doc.createElement("NewPassword");
			elem2.setNodeValue(newPassword);
			Element[] elements2 = { elem2 };
			SAML_Attribute att2 =
				new SAML_Attribute(
					"NewPassword",
					"http://www.efunds.com/security/changepassword.xsd",
					elements2);

			SAML_Attribute[] attributes = { att1, att2 };
			attributeStatement =
				new SAML_AttributeStatement(attributes, Subject);
		} catch (Exception e) {
			e.printStackTrace();
		}

		//now build the AssertionList that wraps statements
		SAML_Statement[] Statements =
			{ authorizationStatement, attributeStatement };
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

	public String getRequest() {
		StringBuffer rqStrBuf = new StringBuffer();
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

	protected void procActionInterfaceParams(
		MessageContext mc,
		ESS0030DSMessage user,
		HttpServletRequest req,
		HttpServletResponse res,
		HttpSession ses)
		throws ServletException, IOException {

		MessageRecord newmessage = null;
		ESD000508Message msgEF = null;
		ELEERRMessage msgError = null;
		UserPos userPO = null;
		boolean IsNotError = false;

		userPO = (datapro.eibs.beans.UserPos) ses.getAttribute("userPO");

		String opCode = "0005";

		String prevPassword = req.getParameter("PREEFNPWD");
		String newPassword = req.getParameter("E08EFNPWD");
		boolean eFundsError = false;
		if (!newPassword.equals(prevPassword)) {
			// send change of password request to eFunds
			_customerId = req.getParameter("E08EFNCID");
			_secUserId = req.getParameter("E08EFNUID");
			_issuer = _secUserId;
			_secPassword = prevPassword;
			eFundsError = procReqToEFunds(ses, newPassword);
		}

		if (eFundsError) {
			try {
				flexLog(
					"About to call Page: "
						+ LangPath
						+ "ESD0005_efunds_parameters.jsp");
				callPage(LangPath + "ESD0005_efunds_parameters.jsp", req, res);

			} catch (Exception e) {
				flexLog("Exception calling page " + e);
			}
		} else {

			// Send Initial data
			try {
				flexLog("Send Initial Data");
				msgEF = (ESD000508Message) mc.getMessageRecord("ESD000508");
				msgEF.setH08USERID(user.getH01USR());
				msgEF.setH08PROGRM("ESD0005");
				msgEF.setH08TIMSYS(getTimeStamp());
				msgEF.setH08SCRCOD("01");
				msgEF.setH08OPECOD(opCode);

				// all the fields here
				java.util.Enumeration enu = msgEF.fieldEnumeration();
				MessageField field = null;
				String value = null;
				while (enu.hasMoreElements()) {
					field = (MessageField) enu.nextElement();
					try {
						value = req.getParameter(field.getTag()).toUpperCase();
						if (value != null) {
							if (field
								.getTag()
								.toUpperCase()
								.equals("E08EFNPWD")) {
								value = req.getParameter(field.getTag());
								CharacterField cf = (CharacterField) field;
								cf.setStringUD(value);
							} else
								field.setString(value);
						}
					} catch (Exception e) {
					}
				}

				msgEF.send();
				msgEF.destroy();
				flexLog("ESD000508 Message Sent");

				// Receive Error Message

				newmessage = mc.receiveMessage();

				if (newmessage.getFormatName().equals("ELEERR")) {
					msgError = (ELEERRMessage) newmessage;
					IsNotError = msgError.getERRNUM().equals("0");
					flexLog("IsNotError = " + IsNotError);
				} else
					flexLog(
						"Message " + newmessage.getFormatName() + " received.");

				// Receive Data

				newmessage = mc.receiveMessage();

				if (newmessage.getFormatName().equals("ESD000508")) {

					msgEF = (ESD000508Message) newmessage;

					flexLog("Putting java beans into the session");
					ses.setAttribute("error", msgError);
					ses.setAttribute("efunds", msgEF);

					if (IsNotError) { // There are no errors
						try {

							flexLog(
								"About to call Page: "
									+ LangPath
									+ "ESD0005_efunds_parameters_enter.jsp");
							callPage(
								LangPath
									+ "ESD0005_efunds_parameters_enter.jsp",
								req,
								res);
						} catch (Exception e) {
							flexLog("Exception calling page " + e);
						}
					} else { // There are errors
						try {

							flexLog(
								"About to call Page: "
									+ LangPath
									+ "ESD0005_efunds_parameters.jsp");
							callPage(
								LangPath + "ESD0005_efunds_parameters.jsp",
								req,
								res);

						} catch (Exception e) {
							flexLog("Exception calling page " + e);
						}
					}

				} else
					flexLog(
						"Message " + newmessage.getFormatName() + " received.");

			} catch (Exception e) {
				e.printStackTrace();
				flexLog("Error: " + e);
				throw new RuntimeException("Socket Communication Error");
			}
		}

	}

	public boolean extractSecurityResponse(HttpSession ses, String xmlSource)
		throws ServletException, IOException {
		DocumentBuilderFactory docBuilderFactory = null;
		DocumentBuilder docBuilder = null;
		Document doc = null;

		boolean ok = true;

		try {
			docBuilderFactory = DocumentBuilderFactory.newInstance();
			docBuilder = docBuilderFactory.newDocumentBuilder();
			doc =
				docBuilder.parse(
					new java.io.ByteArrayInputStream(xmlSource.getBytes()));
			doc.getDocumentElement().normalize();

			NodeList respList = doc.getElementsByTagName("SecurityResponse");
			if (respList.getLength() > 0) {
				Node resp = respList.item(0);
				NodeList actionList =
					((Element) resp).getElementsByTagName("Action");
				if (actionList.getLength() > 0) {
					Node action = respList.item(0);
					Element actionCode =
						(Element) ((Element) action).getElementsByTagName(
							"ActionCode").item(
							0);
					String errorCode = actionCode.getChildNodes().item(0).getNodeValue();
					Element actionDesc =
						(Element) ((Element) action).getElementsByTagName(
							"ActionDescription").item(
							0);
					String errorDesc = actionDesc.getChildNodes().item(0).getNodeValue();

					if (errorCode.equals("12001")) {
						ok = false;
					} else {
						ELEERRMessage msgError = new ELEERRMessage();
						msgError.setERRNUM("1");
						msgError.setERDF01("E08EFNPWD");
						// msgError.setERNU01(errorCode);
						msgError.setERDS01(errorCode + " - " + errorDesc);
						ses.setAttribute("error", msgError);
					}
				}

			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		return ok;

	}
	
	protected void procActionInterface(
		MessageContext mc,
		ESS0030DSMessage user,
		HttpServletRequest req,
		HttpServletResponse res,
		HttpSession ses)
		throws ServletException, IOException {

		String optSend =""; 

		try {
			optSend = req.getParameter("E02SEND");
			if (optSend == null) optSend = "";
		} catch (Exception e) {
			optSend = "";
		}		
		if (optSend.equals("X")){
			//res.sendRedirect(super.srctx + "/servlet/datapro.eibs.params.JSESD0005EFUNDS?SCREEN=6");
			procActionInterfaceParams(mc, user, req, res, ses);
		} else {
			String parE08EFNBNK =""; 
			try {
				parE08EFNBNK = req.getParameter("E08EFNBNK");
				if (parE08EFNBNK == null) parE08EFNBNK = "";
			} catch (Exception e) {
				parE08EFNBNK = "";
			}				
			String parPREEFNPWD =""; 
			try {
				parPREEFNPWD = req.getParameter("PREEFNPWD");
				if (parPREEFNPWD == null) parPREEFNPWD = "";
			} catch (Exception e) {
				parPREEFNPWD = "";
			}	
			String parH08FLGWK1 =""; 
			try {
				parH08FLGWK1 = req.getParameter("H08FLGWK1");
				if (parH08FLGWK1 == null) parH08FLGWK1 = "";
			} catch (Exception e) {
				parH08FLGWK1 = "";
			}	
			String parE08EFNUID =""; 
			try {
				parE08EFNUID = req.getParameter("E08EFNUID");
				if (parE08EFNUID == null) parE08EFNUID = "";
			} catch (Exception e) {
				parE08EFNUID = "";
			}	
			String parE08EFNPWD =""; 
			try {
				parE08EFNPWD = req.getParameter("E08EFNPWD");
				if (parE08EFNPWD == null) parE08EFNPWD = "";
			} catch (Exception e) {
				parE08EFNPWD = "";
			}
			String parE08EFNCID =""; 
			try {
				parE08EFNCID = req.getParameter("E08EFNCID");
				if (parE08EFNCID == null) parE08EFNCID = "";
			} catch (Exception e) {
				parE08EFNCID = "";
			}	
			String parE08EFNUIC =""; 
			try {
				parE08EFNUIC = req.getParameter("E08EFNUIC");
				if (parE08EFNUIC == null) parE08EFNUIC = "";
			} catch (Exception e) {
				parE08EFNUIC = "";
			}	
			String parE08EFNPWC =""; 
			try {
				parE08EFNPWC = req.getParameter("E08EFNPWC");
				if (parE08EFNPWC == null) parE08EFNPWC = "";
			} catch (Exception e) {
				parE08EFNPWC = "";
			}
			res.sendRedirect(super.srctx + "/servlet/datapro.eibs.params.JSESD0005?SCREEN=5&E08EFNBNK=" + parE08EFNBNK +
							"&PREEFNPWD=" + parPREEFNPWD +
							"&H08FLGWK1=" + parH08FLGWK1 +
							"&E08EFNUID=" + parE08EFNUID +
							"&E08EFNPWD=" + parE08EFNPWD +
							"&E08EFNCID=" + parE08EFNCID +
							"&E08EFNUIC=" + parE08EFNUIC + 
							"&E08EFNPWC");
		}
	}	
}
