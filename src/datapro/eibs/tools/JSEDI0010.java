/*
 * Created on Jan 23, 2009
 *
 * To change the template for this generated file go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
package datapro.eibs.tools;

import java.io.BufferedOutputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.math.BigDecimal;
import java.util.StringTokenizer;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.xml.sax.SAXException;

import com.datapro.eibs.exception.FacadeException;
import com.datapro.eibs.exception.ItemNotFoundException;
import com.datapro.eibs.images.facade.FAImage;
import com.datapro.eibs.images.vo.SCNIMGView;
import com.datapro.generic.beanutil.BeanList;

import datapro.eibs.beans.EDI001001Message;
import datapro.eibs.beans.EDI001002Message;
import datapro.eibs.beans.EDI001003Message;
import datapro.eibs.beans.ELEERRMessage;
import datapro.eibs.beans.ESS0030DSMessage;
import datapro.eibs.beans.JBList;
import datapro.eibs.beans.JBListRec;
import datapro.eibs.beans.UserPos;
import datapro.eibs.generic.JBParseTree;
import datapro.eibs.master.JSEIBSProp;
import datapro.eibs.master.JSEIBSServlet;
import datapro.eibs.master.MessageProcessor;
import datapro.eibs.master.Util;
import datapro.eibs.sockets.MessageRecord;

/**
 * @author erodriguez
 *
 * To change the template for this generated type comment go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
public class JSEDI0010 extends JSEIBSServlet {

	// Options
	protected static final int R_DOCUMENTS = 1;
	protected static final int A_DOCUMENTS = 2;
	protected static final int R_INQ_DOCS = 3;
	protected static final int A_INQ_DOCS = 4;
	protected static final int R_DOC_DELETE = 5;
	protected static final int R_DELETE_PAGE = 10;
	protected static final int R_IMAGE = 11;
	protected static final int R_ENTER = 100;
	protected static final int A_ENTER = 200;
	protected static final int R_ENTER_MANT = 300;
	protected static final int A_ENTER_MANT = 400;
	
	/* (non-Javadoc)
	 * @see datapro.eibs.master.JSEIBSServlet#processRequest(datapro.eibs.beans.ESS0030DSMessage, javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse, javax.servlet.http.HttpSession, int)
	 */
	protected void processRequest(
		ESS0030DSMessage user,
		HttpServletRequest req,
		HttpServletResponse res,
		HttpSession session,
		int screen)
		throws ServletException, IOException {

			switch (screen) {
				case R_DOCUMENTS :
					procReqDocList(user, req, res, session);
					break;
				case A_DOCUMENTS :
					procActionDocList(user, req, res, session);
					break;
				case R_INQ_DOCS :
					procReqInqDocList(user, req, res, session);
					break;
				case A_INQ_DOCS :
					procActionInqDocList(user, req, res, session);
					break;
				case R_ENTER :
					procReqEnter(user, req, res, session);
					break;
				case A_ENTER :
					procActionEnter(user, req, res, session);
					break;
				case R_DOC_DELETE :
					procReqDocDeleteJDBC(req.getParameter("CODE"), res);
					break;
				case R_DELETE_PAGE :
					procActionDocDeletePage(user, req, res, session);
					break;					
				case R_IMAGE :
					procGetImageJDBC(user, req, res, session);
					break;
				//Only Banesco
				case R_ENTER_MANT :
					procReqEnterMant(user, req, res, session);
					break;
				case A_ENTER_MANT :
					procActionEnterMant(user, req, res, session);
					break;
				default :
					forward("MISC_not_available.jsp", req, res);
					break;
			}
	}
	
	/**
	 * @param user
	 * @param req
	 * @param res
	 * @param session
	 */
	private void procActionEnterMant(ESS0030DSMessage user, HttpServletRequest req, HttpServletResponse res, HttpSession session) throws ServletException, IOException {
		UserPos userPO = (UserPos) session.getAttribute("userPO");
		try {
			userPO.setIdentifier(req.getParameter("E01DCIACC"));
		} catch (Exception e) {
			userPO.setIdentifier("0");
		}	
		session.setAttribute("userPO", userPO);
		procReqDocList(user, req, res, session);
	}

	/**
	 * @param user
	 * @param req
	 * @param res
	 * @param session
	 */
	private void procReqEnterMant(ESS0030DSMessage user, HttpServletRequest req, HttpServletResponse res, HttpSession session) throws ServletException, IOException {
		UserPos userPO = (UserPos) session.getAttribute("userPO");
		userPO.setOption("DOCUMENTATION");
		userPO.setPurpose("MAINTENANCE");
		try {
			userPO.setOption(req.getParameter("Type"));
		} catch (Exception e) {
			userPO.setOption("RT");
		}	
		userPO.setRedirect("/servlet/datapro.eibs.tools.JSEDI0010?SCREEN=400&Type=A");
		userPO.setHeader1("E01DCIACC");
		userPO.setHeader2("H01FLGWK2");
		userPO.setHeader3("");
		userPO.setHeader22("A");
		
		session.setAttribute("userPO", userPO);
		ELEERRMessage msgError = new ELEERRMessage();
		session.setAttribute("error", msgError);
		forward("GENERIC_account_enter.jsp", req, res);
	}

	/**
	 * @param user
	 * @param req
	 * @param res
	 * @param session
	 */
	private void procActionDocDeletePage(ESS0030DSMessage user, HttpServletRequest req, HttpServletResponse res, HttpSession session) throws IOException, ServletException {
		String infoFileName = "";
		try {
			infoFileName = req.getParameter("DOC_NAME");
			if (infoFileName == null) infoFileName = "";
		} catch (Exception e) {
			infoFileName = "";
		}
		String sPage = "";
		try {
			sPage = req.getParameter("PAGE_NAME");	
			if (sPage == null) sPage = "";
		} catch (Exception e) {
			sPage = "";
		}
		BigDecimal uniqueId = null;
		try {
			uniqueId = new BigDecimal(req.getParameter("UID"));
		} catch (Exception e) {
			uniqueId = new BigDecimal("0");
		}
		if (!sPage.equals("")) {
			int pos1 = sPage.lastIndexOf("/");
			sPage = sPage.substring(pos1 + 1);
			if (!JSEIBSProp.getImgToIFS()) {
				FAImage facade = new FAImage();
				try {
					//SCNDOCTBL vo = facade.getDocTable(sPage);
					//facade.deleteImage(vo.getTBLUID());
					facade.deleteImage(uniqueId);
					forward("EDI0010_doc_viewer_deleted.jsp", req, res);
					//redirectToPage("datapro.eibs.tools.JSEDI0010?SCREEN=2&opt=3&FLGDEL=1", res);
				} catch (FacadeException e) {
					e.printStackTrace();
					flexLog("FacadeException ocurred. Exception = " + e);
					throw new ServletException(e);
				}

			} else {
				if (JSEIBSProp.getImgToIFS() && !infoFileName.equals("")) {
					try {
						String xmlFileName = infoFileName + ".xml";
						//String xmlFile = JSEIBSProp.getImgTempPath() + xmlFileName;
						File xmlFile = new File(JSEIBSProp.getImgTempPath(), xmlFileName);
						
						DocumentBuilderFactory builderFactory = DocumentBuilderFactory.newInstance();
						DocumentBuilder parser = builderFactory.newDocumentBuilder();
						org.w3c.dom.Document doc = parser.parse(xmlFile);

						org.w3c.dom.Node root = doc.getFirstChild();
						org.w3c.dom.NodeList list = root.getChildNodes();
						
						for (int i = 0; i < list.getLength(); i++) {
							org.w3c.dom.Node n = list.item(i);
							if (n.getNodeName().equals("Page")) {
								org.w3c.dom.NamedNodeMap m = n.getAttributes();
								org.w3c.dom.Node c = m.getNamedItem("Name");
								if (c.getNodeValue().equals(sPage)) {
									try {
										File fImage = new File(JSEIBSProp.getImgTempPath(), sPage);
										if (!fImage.delete()) {
											flexLog("Error in delete process for : " + sPage);	 
										}
									} catch (Exception ex) {
										flexLog("Exception = " + ex);
									}
									root.removeChild(n);
								}	
							}	
						}
						
						if (list.getLength() == 0) {
							xmlFile.delete();
						} else {
							TransformerFactory tFactory = TransformerFactory.newInstance();
							Transformer transformer = tFactory.newTransformer();
							StreamResult result = new StreamResult(xmlFile);
							DOMSource source = new DOMSource(doc);
							transformer.transform(source, result);
						}
						forward("EDI0010_doc_viewer_deleted.jsp?ROW=" + list.getLength(), req, res);
					} catch (Exception e) {
						flexLog("Exception = " + e);
					}
				}
			}
		}
	}

	/**
	 * @param string
	 * @param res
	 */
	private void procReqDocDeleteJDBC(String infoFileName, HttpServletResponse res) throws ServletException {
		if (!JSEIBSProp.getImgToIFS()) {
			FAImage facade = new FAImage();
			String params[] = com.datapro.generic.tool.Util.split(infoFileName, "_");
			try {
				facade.deleteImage(params[0].substring(params[0].length()-1), params[1], params[2], params[3]);
			} catch (FacadeException e) {
				e.printStackTrace();
				flexLog("FacadeException ocurred. Exception = " + e);
				throw new ServletException(e);
			}
		}
	}

	/**
	 * @param user
	 * @param req
	 * @param res
	 * @param session
	 */
	private void procActionEnter(ESS0030DSMessage user, HttpServletRequest req, HttpServletResponse res, HttpSession session) throws ServletException, IOException {
		// Type (either Account or Customer)
		String type = "";
		try {
			type = req.getParameter("Type");
			if (type == null) type = "";
		} catch (Exception e) {
			type = "";
		}
		if (type.equalsIgnoreCase("C")) {
			procReqDocsTree(user, req, res, session);
		} else if (type.equalsIgnoreCase("A")) {
			procReqInqDocList(user, req, res, session);
		} else {
			procReqEnter(user, req, res, session);
		}
	}

	/**
	 * @param user
	 * @param req
	 * @param res
	 * @param session
	 */
	private void procReqDocsTree(ESS0030DSMessage user, HttpServletRequest req, HttpServletResponse res, HttpSession session) throws ServletException, IOException {
		String outParams = "";
		String firstLink = "";
		boolean firstTime = true;
		JBParseTree dataTree = new JBParseTree();
		MessageProcessor mp = null;
		try {
			mp = getMessageProcessor("EDI0010", req);
			EDI001002Message msg = (EDI001002Message) mp.getMessageRecord("EDI001002", user.getH01USR(), "");
			msg.setH02FLGWK2("C");
			// Number
			String imgNumber = "";
			try {
				imgNumber = req.getParameter("NUMBER");
				if (imgNumber == null ) imgNumber = "";
			} catch (Exception e) {
				imgNumber = "";
			}
			msg.setE02DCICUN(imgNumber);
			// Doc Type
			String imgTyp = "";
			try {
				imgTyp = req.getParameter("TYP");
				if (imgTyp == null ) imgTyp = "";
			} catch (Exception e) {
				imgTyp = "";
			}
			msg.setE02DCITYP(imgTyp);
			
			mp.sendMessage(msg);
			
			MessageRecord newmessage = mp.receiveMessageRecord();
			
			if (newmessage.getFormatName().equals("EDI001002")) {
				msg = (EDI001002Message) newmessage;
				
				if (msg.getE02DCINIV().equals("0")) {
					String imageURL = getServerRoot(req) + super.webAppPath + "/images/";

					dataTree.init();

					dataTree.setRootFont("Dialog", "bold", "12", "0D23B5");
					dataTree.setFolderFont("Dialog", "plain", "10", "000033");
					dataTree.setItemFont("Small", "plain", "10", "000033");
					dataTree.setImageUrl(imageURL);

					dataTree.setRootImage("cube.gif", "cubeover.gif");
					dataTree.setFolderImage("cone.gif", "coneover.gif");
					dataTree.setItemImage("ball.gif", "ballover.gif");

					String titleFolder = msg.getE02DCICUN() + " - " + msg.getE02DCIDSC().trim();
					String titleDescription = msg.getE02DCICUN() + " - " + msg.getE02DCIDSC().trim();

					dataTree.setRootTitle(titleFolder, titleDescription);
					dataTree.setTargetLink("detail");

					String item = "";
					String itemLink = "";
					String folder = "";
					String folderLink = "";
					boolean shutIt = false;
					String thisLink = "";

					while (true) {
						msg = (EDI001002Message) mp.receiveMessageRecord();

						if (msg.getE02DCINIV().equals("1")) {
							if (shutIt) {
								folder = "";
							} else {
								shutIt = true;
							}
							folder =
								msg.getE02DCIATY().equalsIgnoreCase("*cus")
									? msg.getE02DCIDSC().trim()
									: msg.getE02DCIATY() + " - " + msg.getE02DCIDSC().trim();

							dataTree.addRow(folder, folder, folderLink, folderLink);

						} else if (msg.getE02DCINIV().equals("2")) {
							if (firstTime) {
								firstTime = false;
								firstLink =
									super.webAppPath
										+ "/servlet/datapro.eibs.tools.JSEDI0010?SCREEN=3&Type="
										+ msg.getH02FLGWK2().trim()
										+ "&Number="
										+ msg.getE02DCIACC().trim()
										+ "&DocType="
										+ msg.getE02DCITYP().trim();
							}
							itemLink =
								getServerRoot(req)
									+ super.webAppPath
									+ "/servlet/datapro.eibs.tools.JSEDI0010?SCREEN=3&Type="
									+ msg.getH02FLGWK2().trim()
									+ "&Number="
									+ msg.getE02DCIACC().trim()
									+ "&DocType="
									+ msg.getE02DCITYP().trim();
							item =
								msg.getE02DCIPRO().equalsIgnoreCase("*cus")
									? msg.getE02DCIDSC().trim()
									: msg.getE02DCIACC().trim()
										+ " - "
										+ msg.getE02DCIPRO()
										+ " - "
										+ msg.getE02DCIDSC().trim();
										
							dataTree.addRow(item, folder, itemLink, folderLink);

						} else if (msg.getE02DCINIV().equals("*")) {
							break;
						}
					}
				}
			} else {
				flexLog("Message " + newmessage.getFormatName() + " received.");
			}

			if (firstTime) {
				forward("MISC_no_result.jsp", req, res);
			} else {
				ELEERRMessage msgError = new ELEERRMessage();
				session.setAttribute("error", msgError);
				outParams = dataTree.getTree();
				session.setAttribute("docsParams", outParams);

				res.setContentType("text/html");
				res.setHeader("Pragma", "No-cache");
				res.setDateHeader("Expires", 0);
				res.setHeader("Cache-Control", "no-cache");
				PrintWriter out = res.getWriter();
				out.println("<!-- frames -->");
				out.println("<frameset  cols=\"28%,*\">");
				out.println(
					"<frame name=\"list\" src=\""
						+ super.webAppPath
						+ super.getLangPath(user)
						+ "EDI0010_doc_inq_tree_view.jsp\" marginwidth=\"10\" marginheight=\"10\" scrolling=\"no\" frameborder=\"NO\">");
				out.println(
					"<frame name=\"detail\" src=\""
						+ firstLink
						+ "\" marginwidth=\"10\" marginheight=\"10\" scrolling=\"auto\" frameborder=\"NO\">");
				out.println("</frameset>");
				out.close();
			}
			
		} finally {
			if (mp != null)	mp.close();
		}
	}

	/**
	 * @param user
	 * @param req
	 * @param res
	 * @param session
	 */
	private void procReqEnter(ESS0030DSMessage user, HttpServletRequest req, HttpServletResponse res, HttpSession session) throws ServletException, IOException {
		UserPos userPO = (UserPos) session.getAttribute("userPO");
		userPO.setOption("DOCUMENTATION");
		userPO.setPurpose("INQUIRY");
		session.setAttribute("userPO", userPO);
		ELEERRMessage msgError = new ELEERRMessage();
		session.setAttribute("error", msgError);
		forward("EDI0010_doc_general_inq_enter.jsp", req, res);
	}

	/**
	 * @param user
	 * @param req
	 * @param res
	 * @param session
	 */
	private void procActionInqDocList(ESS0030DSMessage user, HttpServletRequest req, HttpServletResponse res, HttpSession session) throws ServletException, IOException {
		UserPos userPO = (UserPos) session.getAttribute("userPO");
		JBListRec beanList = (JBListRec) session.getAttribute("docList");
		int row = 0;
		try {
			row = Integer.parseInt(req.getParameter("ROW"));
		} catch (Exception e) {
			row = 0;
		}
		beanList.setCurrentRow(row);
		if (JSEIBSProp.getImgToIFS()) {
			procReqDocInfoXML(user, beanList.getRecord(0), beanList.getRecord(3), session);
		} else {
			//procReqDocInfoJDBC(user, beanList.getRecord(0), beanList.getRecord(3), session);
			procReqDocListJDBC(user, beanList.getRecord(0), beanList.getRecord(3), session);
		}
		forward("EDI0010_doc_inq_list.jsp?ROW=" + row, req, res);
	}
	
	/**
	 * @param user
	 * @param req
	 * @param res
	 * @param session
	 */
	protected void procReqInqDocList(ESS0030DSMessage user, HttpServletRequest req, HttpServletResponse res, HttpSession session) throws ServletException, IOException {
		UserPos userPO = (UserPos) session.getAttribute("userPO");
		
		//This is for Signature Card.
		/*
		if (req.getParameter("E01CUN") != null) {
			userPO.setCusNum(req.getParameter("E01CUN"));
		}
		*/
		//************************************************
		
		int colNum = 12;
		JBListRec beanList = new JBListRec();
		beanList.init(colNum);
		
		MessageProcessor mp = null;
		try {
			mp = getMessageProcessor("EDI0010", req);
			EDI001001Message msg = (EDI001001Message) mp.getMessageRecord("EDI001001", user.getH01USR(), "0004");
			msg.setH01SCRCOD("01");
			msg.setH01FLGWK1("R"); // Operation (either Send or Receive)
			// Type
			String type = "";
			try {
				type = req.getParameter("Type");
				if (type == null) type = "";
			} catch (Exception e) {
				type = "";
			}
			if (type.equals("R")) {
				msg.setH01FLGWK2("C");
			} else {
				msg.setH01FLGWK2(type);
			}
			// Number
			String imgNumber = "";
			try {
				imgNumber = req.getParameter("NUMBER");
				if (imgNumber == null ) imgNumber = "";
			} catch (Exception e) {
				imgNumber = "";
			}
			if (!imgNumber.equals("")) {
				msg.setE01DCIACC(imgNumber);
			} else {
				if (type.equals("C")) {
					msg.setE01DCIACC(userPO.getCusNum());
				} else if (type.equals("R")) {
					msg.setE01DCIACC(userPO.getHeader2());
				} else {
					msg.setE01DCIACC(userPO.getIdentifier());
				}
			}
			// Doc Type
			String imgTyp = "";
			try {
				imgTyp = req.getParameter("TYP");
				if (imgTyp == null ) imgTyp = "";
			} catch (Exception e) {
				imgTyp = "";
			}
			msg.setE01DCITYP(imgTyp);
			
			mp.sendMessage(msg);
			
			MessageRecord newmessage = mp.receiveMessageRecord();
			
			if (mp.hasError(newmessage)) {
				ELEERRMessage msgError = (ELEERRMessage) newmessage;
				beanList.setNoResult(true);
				flexLog("Putting java beans into the session");
				session.setAttribute("error", msgError);
				session.setAttribute("docList", beanList);
				session.setAttribute("userPO", userPO);
				forward("EDI0010_doc_inq_list.jsp", req, res);
			} else {
				boolean firstTime = true;
				String marker = "";
				String myFlag = "";
				if (newmessage.getFormatName().equals("EDI001001")) {
					String myRow[] = new String[colNum];
					for (int i = 0; i < colNum; i++) {
						myRow[i] = "";
					}
					JBListRec jbList = new JBListRec(); 
					while (true) {
						msg = (EDI001001Message) newmessage;
						marker = msg.getH01FLGMAS();
						if (marker.equals("*")) {
							break;
						} else {
							if (firstTime) {
								firstTime = false;
								userPO.setHeader18(Util.formatCell(msg.getE01DCITNU()));
								userPO.setHeader19(Util.formatCell(msg.getE01DCIDES()));
								userPO.setHeader20("");
								userPO.setHeader21("");
								userPO.setHeader22(type);
							} else {
								myRow[0] = type
										+ "_"
										+ msg.getE01DCIACC().trim()
										+ "_"
										+ msg.getE01DCITNU().trim()
										+ "_"
										+ msg.getE01DCISEQ().trim();
								/*		
								//SI EL TIPO DE DOCUMENTO ES "SC", ASUME TARJETA DE FIRMAS POR IDENTIFICACION
								if (msg.getE01DCITYP().equals("SC")) {
									myRow[0] =
										req.getParameter("Type")
											+ "_"
											+ userPO.getCusNum().trim()
											+ "_"
											+ msg.getE01DCITNU().trim()
											+ "_"
											+ msg.getE01DCISEQ().trim();
								}
								*/
								myRow[1] = msg.getE01DCITNU();
								myRow[2] = msg.getE01DCISEQ();
								myRow[3] = msg.getE01DCIDES();
								myRow[4] = msg.getE01DCIFRE();
								myRow[5] = msg.getE01DCISTA();
								myRow[6] = msg.getE01DCIPAG();
								myRow[7] = msg.getE01DCITYP();
								myRow[8] = msg.getE01DCIDSQ();
								myRow[9] = msg.getE01DCIEX1();
								myRow[10] = msg.getE01DCIEX2();
								myRow[11] = msg.getE01DCIEX3();
								beanList.addRow(myFlag, myRow);
							}
						}
						newmessage = mp.receiveMessageRecord();	
					}
					flexLog("Putting java beans into the session");
					session.setAttribute("docList", beanList);
					session.setAttribute("userPO", userPO);
					forward("EDI0010_doc_inq_list.jsp", req, res);
				} else {
					flexLog("Message " + newmessage.getFormatName() + " received.");	
				}
			}
		
		} finally {
			if (mp != null)	mp.close();
		}
	}
	/**
	 * @param user
	 * @param req
	 * @param res
	 * @param session
	 */
	private void procActionDocList(ESS0030DSMessage user, HttpServletRequest req, HttpServletResponse res, HttpSession session) throws ServletException, IOException {
		UserPos userPO = (UserPos) session.getAttribute("userPO");
		int option = 0;
		try {
			option = Integer.parseInt(req.getParameter("opt"));
		} catch (Exception e) {
			option = 0;
		}
		int row = 0;
		try {
			row = Integer.parseInt(req.getParameter("ROW"));
		} catch (Exception e) {
			row = 0;
		}
		boolean getParams = true;
		try {
			getParams = (req.getParameter("FLGDEL") == null);
		}catch (Exception e) {
			getParams = true;
		}
		JBListRec beanList  = (JBListRec) session.getAttribute("docList");
		
		if (getParams) {
			int numrow = 0;
			beanList.initRow();
			while (beanList.getNextRow()) {
				numrow++;
				try {				
					beanList.setRecord(
						req.getParameter("E01DCIFRE_" + beanList.getCurrentRow()).toUpperCase(),
						4,
						beanList.getCurrentRow());
					beanList.setRecord(
						req.getParameter("E01DCISTA_" + beanList.getCurrentRow()).toUpperCase(),
						5,
						beanList.getCurrentRow());
					//Maturity date
				   beanList.setRecord(
						req.getParameter("E01DCIEX1_" + beanList.getCurrentRow()).toUpperCase(),
						9,
						beanList.getCurrentRow());
				   beanList.setRecord(
						req.getParameter("E01DCIEX2_" + beanList.getCurrentRow()).toUpperCase(),
						10,
						beanList.getCurrentRow());
					beanList.setRecord(
						req.getParameter("E01DCIEX3_" + beanList.getCurrentRow()).toUpperCase(),
						11,
						beanList.getCurrentRow());
				} catch (Exception e) {
				}
			}
			int totrow = 0;
			try {
				totrow = Integer.parseInt(req.getParameter("TOTALROW"));
			} catch (Exception e) {
				totrow = 0;
			}
			int k;
			if (numrow != totrow) {
				// add new documents
				String myRow[] = new String[12];
				for (int i = 0; i < 12; i++) {
					myRow[i] = "";
				}
				String Number = "";
				String myFlag = "";
				if (userPO.getHeader22().equals("C")) {
					Number = userPO.getCusNum();
				} else {
					Number = userPO.getIdentifier();
				}
				while (numrow < totrow) {
					k = numrow;
					myRow[0] = req.getParameter("Type")
							+ "_"
							+ Number
							+ "_"
							+ userPO.getHeader18()
							+ "_"
							+ req.getParameter("E01DCISEQ_" + k).toUpperCase();
					myRow[1] = userPO.getHeader18();
					myRow[2] = req.getParameter("E01DCISEQ_" + k).toUpperCase();
					myRow[3] = req.getParameter("E01DCIDES_" + k).toUpperCase();
					myRow[4] = req.getParameter("E01DCIFRE_" + k).toUpperCase();
					myRow[5] = req.getParameter("E01DCISTA_" + k).toUpperCase();
					myRow[9] = req.getParameter("E01DCIEX1_" + k).toUpperCase();
					myRow[10] = req.getParameter("E01DCIEX2_" + k).toUpperCase();
					myRow[11] = req.getParameter("E01DCIEX3_" + k).toUpperCase();	
					beanList.addRow(myFlag, myRow);						
					numrow++;
				}
			}	
			session.setAttribute("docList", beanList);
		} else {
			// Si habia una sola imagen cambiar status
			boolean uptStatus = false;
			JBListRec imgList = (JBListRec) session.getAttribute("listImg");
			int numImg = 0;
			imgList.initRow();
			while (imgList.getNextRow()) {
				numImg++;
			}
			if (numImg <= 1) {
				beanList.setCurrentRow(row);
				beanList.setRecord(	"",
									5,
									beanList.getCurrentRow());
			}
		}
		// Update for all options
		//procActionDocListUpdate(user, req, res, session);
		
		beanList.setCurrentRow(row);
		session.setAttribute("ROW", String.valueOf(row));
		
		switch (option) {
			case 1 : // Update
				procActionDocListUpdate(user, req, res, session);
				forward("EDI0010_doc_list.jsp?ROW=" + row, req, res);
				break;
			case 2 : // Scan
				if (super.scanActive) {
					procActionDocListUpdate(user, req, res, session);
					String Number = "";
					if (userPO.getHeader22().equals("C")) {
						Number = userPO.getCusNum();
					} else {
						Number = userPO.getIdentifier();
					}
					String url = "";
					if (JSEIBSProp.getImageRemote()) {
						url = JSEIBSProp.getScanURL();
					} else {
						url = getServerRoot(req) + super.webAppPath + "/servlet/datapro.eibs.tools.JSScanDocDone";
					}
					res.sendRedirect(super.srctx + 
						"/servlet/datapro.eibs.tools.JSScan?Name="
							+ beanList.getRecord(0)
							+ "&Desc="
							+ beanList.getRecord(3)
							+ "&URL="
							+ url
							+ "?USERID="
							+ user.getH01USR()
							+ "@NUMBER="
							+ Number
							+ "@TYPE="
							+ userPO.getHeader22()
							+ "@ADD="
							+ beanList.getRecord(0)
							+ "@TNU="
							+ beanList.getRecord(1)
							+ "@SEQ="
							+ beanList.getRecord(2)
							+ "@DES="
							+ beanList.getRecord(3).trim().replace(' ', '^')
							+ "@FRE="
							+ beanList.getRecord(4)
							+ "@TYP="
							+ beanList.getRecord(7));
				} else {
					forward("EDI0010_doc_list.jsp?ROW=" + row, req, res);
				}
				break;
			case 3 : // View
				if (beanList.getRecord(5).equalsIgnoreCase("CO")) {
					if (JSEIBSProp.getImgToIFS())
						procReqDocInfoXML(user, beanList.getRecord(0), beanList.getRecord(3), session);
					else
						//procReqDocInfoJDBC(user, beanList.getRecord(0), beanList.getRecord(3), session);
						procReqDocListJDBC(user, beanList.getRecord(0), beanList.getRecord(3), session);
				}
				forward("EDI0010_doc_list.jsp?ROW=" + row, req, res);
				break;
			case 4 : // Delete
				procActionDocListUpdate(user, req, res, session);
				procActionDocDelete(user, req, res, session, beanList);
				forward("EDI0010_doc_list.jsp?ROW=" + row, req, res);
				break;
			case 5 : // Add
				procActionDocListUpdate(user, req, res, session);
				forward("EDI0010_doc_list.jsp?ROW=" + row, req, res);
				break;
			default :
				forward(super.bgPage, req, res);
		}
		
	}
	
	
	/**
	 * @param user
	 * @param req
	 * @param res
	 * @param session
	 * @param beanList
	 */
	private void procActionDocDelete(ESS0030DSMessage user, HttpServletRequest req, HttpServletResponse res, HttpSession session, JBListRec beanList) {
		String infoFileName = beanList.getRecord(0);
		UserPos userPO = (UserPos) session.getAttribute("userPO");
		
		if (!JSEIBSProp.getImgToIFS()) {
			try {
				String number = "";
				if (userPO.getHeader22().equals("C")) {
					number = userPO.getCusNum();
				} else {
					number = userPO.getIdentifier();
				}	
				String table = userPO.getHeader18();
				String type = userPO.getHeader22();
					
				FAImage facade = new FAImage();
				facade.deleteImage(type, number, table, beanList.getRecord(2));
				
			} catch (FacadeException e) {
				e.printStackTrace();
				flexLog("FacadeException ocurred. Exception = " + e);
			}
		} else {
			if (JSEIBSProp.getImgToIFS() && infoFileName != null) {
				try {
					String xmlFileName = infoFileName + ".xml";
					//String xmlFile = JSEIBSProp.getImgTempPath() + xmlFileName;
					File xmlFile = new File(JSEIBSProp.getImgTempPath(), xmlFileName);
						
					DocumentBuilderFactory builderFactory = DocumentBuilderFactory.newInstance();
					DocumentBuilder parser = builderFactory.newDocumentBuilder();
					org.w3c.dom.Document doc = parser.parse(xmlFile);

					org.w3c.dom.Node root = doc.getFirstChild();
					org.w3c.dom.NodeList list = root.getChildNodes();
						
					for (int i = 0; i < list.getLength(); i++) {
						org.w3c.dom.Node n = list.item(i);
						if (n.getNodeName().equals("Page")) {
							org.w3c.dom.NamedNodeMap m = n.getAttributes();
							org.w3c.dom.Node c = m.getNamedItem("Name");
							String sPage = c.getNodeValue();
							try {
								File fImage = new File(JSEIBSProp.getImgTempPath(), sPage);
								if (!fImage.delete()) {
									flexLog("Error in delete process for : " + sPage);	 
								}
							} catch (Exception ex) {
								flexLog("Exception = " + ex);
							}
						}	
					}
					
					xmlFile.delete();
					
				} catch (Exception e) {
					flexLog("Exception = " + e);
				}
			}	
		}
	}
	
	private void procReqDocListJDBC(ESS0030DSMessage user, String infoFileName, String docName, HttpSession session) throws ServletException, IOException {
		int i = 0; 
		String[] element = new String[4];
		for (StringTokenizer st = new StringTokenizer(infoFileName, "_"); st.hasMoreElements();) {
			element[i++] = (String) st.nextElement();
		}
		
		flexLog("FileName is: TBLTYP=" + element[0] + " TBLNUM=" + element[1] + " TBLTBN=" + element[2] + " TBLSEQ" + element[3]);
		
		FAImage facade = new FAImage();
		try {
			BeanList list = facade.getDocTableList(element[0], element[1], element[2], element[3]);
			list.initRow();
			JBListRec beanImg = new JBListRec();
			int colNum = 6;
			beanImg.init(colNum);
			String myFlag = "";
			String myRow[] = new String[colNum];
			for (int k=0; k<colNum; k++) {
				myRow[k] = "";
			}
			while (list.getNextRow()) {
				SCNIMGView vo = (SCNIMGView) list.getRecord();
				myRow[0] = vo.getTBLDSC().trim();
				String extTemp = myRow[0].substring(myRow[0].lastIndexOf(".")).toLowerCase();
				if (extTemp.equals(".jpg") || extTemp.equals(".gif")) {
					myFlag = "I";
				} else {
					myFlag = "";
				}					
				myRow[1] = docName;
				myRow[2] = myRow[0].substring(myRow[0].lastIndexOf(".") + 1);
				myRow[3] = vo.getTBLLMM() + "/" + vo.getTBLLMD() + "/" + vo.getTBLLMY();
				myRow[4] = vo.getTBLSSQ().toString();
				myRow[5] = vo.getTBLUID().toString();
				beanImg.addRow(myFlag, myRow);
			}
			if (!list.isEmpty()) {
				UserPos userPO = (UserPos) session.getAttribute("userPO");
				userPO.setHeader20("DO_INQ");
				userPO.setHeader21(super.webAppPath + super.getLangPath(user) + "EDI0010_doc_viewer_container.jsp?DOC_NAME=" + infoFileName);
				session.setAttribute("userPO", userPO);
				//Descending sort
				beanImg = sortList(beanImg, 4, colNum);
				session.setAttribute("listImg", beanImg);
			}
		} catch (FacadeException e) {
			e.printStackTrace();
			flexLog("FacadeException ocurred. Exception = " + e);
			throw new ServletException(e);
		}
	}
	
	private void procGetImageFile(ESS0030DSMessage user, HttpServletRequest req, HttpServletResponse res, HttpSession session) throws ServletException, IOException {
		String uid = (req.getParameter("DOC_UID") == null) ? "" : req.getParameter("DOC_UID").trim();
		String typ = (req.getParameter("TYP") == null) ? "" : req.getParameter("TYP").trim();
		String doc = (req.getParameter("DOC_NAME") == null) ? "" : req.getParameter("DOC_NAME").trim();
		String PageToCall = "";
		FAImage facade = new FAImage();
		try {
			flexLog("Image to View: " + uid);
			SCNIMGView vo = facade.getImage(uid);
			byte buf[] = vo.getIMGBIN();
			OutputStream osImage = new FileOutputStream(JSEIBSProp.getImgTempPath() + vo.getTBLDSC().trim());
			osImage.write(buf);
			osImage.close();
			
			String imgFileName = JSEIBSProp.getScanDataURL() + vo.getTBLDSC().trim();
			if (typ.equals("I")) {
				PageToCall = "EDI0010_doc_viewer_img_container.jsp?DOC_NAME=" + doc + "&PAGE_NAME=" + imgFileName + "&IMG_SIZE=370";
			} else {
				PageToCall = "EDI0010_doc_viewer_blank.jsp?PAGE_NAME=" + imgFileName;
			}
			forward(PageToCall, req, res);
					 
		} catch (FacadeException e) {
			e.printStackTrace();
			flexLog("FacadeException ocurred. Exception = " + e);
			throw new ServletException(e);
		} catch (ItemNotFoundException e) {
			e.printStackTrace();
			flexLog("ItemNotFoundException ocurred. Exception = " + e);
			throw new ServletException(e);
		}
	}
	
	private long copy(InputStream source, OutputStream destination) throws IOException {
		byte[] buf = new byte[4096];
		int numRead;
		int numberOfBytesCopied = 0;
		while(-1!= (numRead = source.read(buf))) {
			destination.write(buf, 0, numRead);
			destination.flush();
			numberOfBytesCopied+=numRead;
		}		
		return numberOfBytesCopied;
	}
		
	private  void procGetImageJDBC(ESS0030DSMessage user, HttpServletRequest req, HttpServletResponse res, HttpSession session) throws ServletException, IOException {
		try {
			String uid = 
				req.getParameter("DOC_UID") == null ? "" : req.getParameter("DOC_UID").trim();
			String imgFileName = 
				req.getParameter("DOC_NAME") == null ? "" : req.getParameter("DOC_NAME").trim();
			
			byte buf[] = null;
			if (JSEIBSProp.getImgToIFS()) {
				if (imgFileName != null) {
					InputStream inImage = null;
					try {
						File file = new File(JSEIBSProp.getImgTempPath(), imgFileName);
						inImage = new FileInputStream(file);
						ByteArrayOutputStream out = new ByteArrayOutputStream();
						copy(inImage, out);
						buf = out.toByteArray();						
					} finally {
						if (inImage != null) 
							inImage.close();
					}
				}
			} else {
				if (uid != null) {
					FAImage facade = new FAImage();
					uid = imgFileName.substring(0, imgFileName.indexOf('_'));
					SCNIMGView vo = facade.getImage(uid);
					imgFileName = vo.getTBLDSC().trim();
					buf = vo.getIMGBIN();
				}
			}
			
			res.reset();
			String ext = imgFileName.substring(imgFileName.lastIndexOf(".") + 1).toLowerCase();
			if (ext.equals("pdf")) {
				res.setContentType("application/pdf");
			} else {
				if (ext.equals("jpg") || ext.equals("jpeg")) {
					res.setContentType("image/jpeg");
				} else {
					if (ext.equals("doc")) {
						res.setContentType("eibs/form");
						res.setHeader("Content-disposition", "attachment; filename=\"" + imgFileName + "\"");
					} else {	
						if (ext.equals("tif") || ext.equals("tiff")) {
							res.setContentType("image/tiff");
						} else {	
							res.setContentType("image/" + ext);
						}
					}
				}
			}
			res.setContentLength(buf.length);
			
			BufferedOutputStream output = null;
			try {
				output = new BufferedOutputStream(res.getOutputStream());
				output.write(buf);
				output.flush();
			} finally {
				output.close();
			}
					 
		} catch (FacadeException e) {
			e.printStackTrace();
			flexLog("FacadeException ocurred. Exception = " + e);
			throw new ServletException(e);
		} catch (ItemNotFoundException e) {
			e.printStackTrace();
			flexLog("ItemNotFoundException ocurred. Exception = " + e);
			throw new ServletException(e);
		}
	}	
	
	/**
	 * @param string
	 * @param string2
	 * @param session
	 */
	/*
	 private void procReqDocInfoJDBC(ESS0030DSMessage user, String infoFileName, String docName, HttpSession session) throws ServletException, IOException {
		int i = 0; 
		String[] element = new String[4];
		for (StringTokenizer st = new StringTokenizer(infoFileName, "_"); st.hasMoreElements();) {
			element[i++] = (String) st.nextElement();
		}
		FAImage facade = new FAImage();
		try {
			BeanList list = facade.getDocImageList(element[0], element[1], element[2], element[3]);
			list.initRow();
			JBListRec beanImg = new JBListRec();
			int colNum = 5;
			beanImg.init(colNum);
			String myFlag = "";
			String myRow[] = new String[colNum];
			for (int k=0; k<colNum; k++) {
				myRow[k] = "";
			}
			while (list.getNextRow()) {
				SCNDOCView vo = (SCNDOCView) list.getRecord();
				String imgFileName = vo.getTBLDSC().trim();
				myRow[0] = JSEIBSProp.getScanDataURL() + imgFileName;
				String extTemp = myRow[0].substring(myRow[0].lastIndexOf(".")).toLowerCase();
				if (extTemp.equals(".jpg") || extTemp.equals(".gif")) {
					myFlag = "I";
				} else {
					myFlag = "";
				}					
				myRow[1] = docName;
				myRow[2] = myRow[0].substring(myRow[0].lastIndexOf(".") + 1);
				myRow[3] = vo.getTBLLMM() + "/" + vo.getTBLLMD() + "/" + vo.getTBLLMY();
				myRow[4] = vo.getTBLSSQ().toString();
				beanImg.addRow(myFlag, myRow);
				
				byte buf[] = vo.getIMGBIN();
				OutputStream osImage = new FileOutputStream(JSEIBSProp.getImgTempPath() + imgFileName);
				osImage.write(buf);
				osImage.close();
			}	

			if (!list.isEmpty()) {
				UserPos userPO = (UserPos) session.getAttribute("userPO");
				userPO.setHeader20("DO_INQ");
				userPO.setHeader21(super.webAppPath + super.getLangPath(user) + "EDI0010_doc_viewer_container.jsp?DOC_NAME=" + infoFileName);
				session.setAttribute("userPO", userPO);
				//Descending sort
				beanImg = sortList(beanImg, 4, colNum);
				session.setAttribute("listImg", beanImg);
			}
			
		} catch (FacadeException e) {
			e.printStackTrace();
			flexLog("FacadeException ocurred. Exception = " + e);
			throw new ServletException(e);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
			flexLog("FileNotFoundException ocurred. Exception = " + e);
			throw new ServletException(e);
		}
	}
	*/
	
	/**
	 * This method was created by Orestes Garcia.
	 */
	private JBListRec sortList(JBListRec list,int idx,int col)  {
		JBListRec slist = new JBListRec();
		int maxrow = list.getLastRec();
		list.setCurrentRow(maxrow);
		String flag = list.getRecord(idx);
		String myRow[] = new String[col];
		int count = 0;
		slist.init(col);	
		boolean oneElement = true;	
			
		for (int i = maxrow - 1 ; i > -2; i--) {
			
			if (!flag.equals(list.getRecord(idx))) {
				int pos = 0;
				flag = list.getRecord(idx);
				oneElement = false;
				if (i==-1) list.initRow();
				while (list.getNextRow() && pos < count) {
					for (int j = 0; j < col; j++) {
						myRow[j] = list.getRecord(j);
					}
					slist.addRow(list.getFlag(),myRow);
					pos++;
				}
				count = 0;
				if (i == -1) break;
				i++;
			} else {
				count++;
			}
			list.setCurrentRow(i);
			if (i==0) {
				flag = "@@@";
				count++;
			} 
		}
		
		if (oneElement) return list;
		else return slist;
	}

	/**
	 * @param string
	 * @param string2
	 * @param session
	 */
	private void procReqDocInfoXML(ESS0030DSMessage user, String infoFileName, String docName, HttpSession session) throws ServletException, IOException {
		try {
			DocumentBuilderFactory builderFactory = DocumentBuilderFactory.newInstance();
			DocumentBuilder parser = builderFactory.newDocumentBuilder();
			
			File file = new File(JSEIBSProp.getImgTempPath(), infoFileName + ".xml");
			org.w3c.dom.Document doc = parser.parse(file);
			JBListRec beanImg = new JBListRec();
			int colNum = 6;
			beanImg.init(colNum);
			String myFlag = "";
			String myRow[] = new String[colNum];
			for (int i = 0; i < colNum; i++) {
				myRow[i] = "";
			}
			
			org.w3c.dom.Node docPages = doc.getFirstChild();
			org.w3c.dom.NodeList list = docPages.getChildNodes();
			for (int i = 0; i < list.getLength(); i++) {
				org.w3c.dom.Node n = list.item(i);
				if (n.getNodeName().equals("Page")) {
					org.w3c.dom.NamedNodeMap m = n.getAttributes();
					org.w3c.dom.Node c = m.getNamedItem("Name");
					myRow[0] = JSEIBSProp.getScanDataURL() + c.getNodeValue();
					String extTemp = myRow[0].substring(myRow[0].lastIndexOf(".")).toLowerCase();
					if (extTemp.equals(".jpg") || extTemp.equals(".gif")) {
						myFlag = "I";
					} else {
						myFlag = "";
					}
					myRow[1] = docName;
					myRow[2] = myRow[0].substring(myRow[0].lastIndexOf(".") + 1);
					c = m.getNamedItem("Date");
					if (c == null)
						myRow[3] = "";
					else
						myRow[3] = c.getNodeValue();
					c = m.getNamedItem("Seq");
					if (c == null)
						myRow[4] = "1";
					else
						myRow[4] = c.getNodeValue();
					myRow[5] = infoFileName; 
					beanImg.addRow(myFlag, myRow);
				}
			}

			UserPos userPO = (UserPos) session.getAttribute("userPO");
			userPO.setHeader20("DO_INQ");
			userPO.setHeader21(super.webAppPath + super.getLangPath(user) + "EDI0010_doc_viewer_container.jsp?DOC_NAME=" + infoFileName);
			session.setAttribute("userPO", userPO);
			//Descending sort
			beanImg = sortList(beanImg, 4, colNum);
			session.setAttribute("listImg", beanImg);
			
		} catch (ParserConfigurationException e) {
			e.printStackTrace();
			flexLog("ParserConfigurationException ocurred. Exception = " + e);
			throw new ServletException(e);
		} catch (SAXException e) {
			e.printStackTrace();
			flexLog("SAXException ocurred. Exception = " + e);
			throw new ServletException(e);
		}
		
	}
	
	
	
	/**
	 * @param user
	 * @param req
	 * @param res
	 * @param session
	 */
	private void procActionDocListUpdate(ESS0030DSMessage user, HttpServletRequest req, HttpServletResponse res, HttpSession session) throws ServletException, IOException {
		UserPos userPO = (UserPos) session.getAttribute("userPO");
		JBListRec beanList = (JBListRec) session.getAttribute("docList");
		MessageProcessor mp = null;
		EDI001001Message msg = null;
		ELEERRMessage msgError = null;
		
		int option = 0;
		try {
			option = Integer.parseInt(req.getParameter("opt"));
		} catch (Exception e) {
			option = 0;
		}
		int row = 0;
		try {
			row = Integer.parseInt(req.getParameter("ROW"));
		} catch (Exception e) {
			row = -1;
		}
		
		
		try {
			mp = getMessageProcessor("EDI0010", req);
			beanList.initRow();
			if (option == 1) { //Update all items.
				while (beanList.getNextRow()) {
					msg = (EDI001001Message) mp.getMessageRecord("EDI001001", user.getH01USR(), "");
					msg.setH01SCRCOD("01");
					msg.setH01FLGWK1("S");
					msg.setH01FLGWK2(userPO.getHeader22());
					if (userPO.getHeader22().equals("C")) {
						msg.setE01DCIACC(userPO.getCusNum());
					} else {
						msg.setE01DCIACC(userPO.getIdentifier());
					}
					msg.setE01DCIADD(beanList.getRecord(0));
					msg.setE01DCITNU(beanList.getRecord(1));
					msg.setE01DCISEQ(beanList.getRecord(2));
					msg.setE01DCIDES(beanList.getRecord(3));
					msg.setE01DCIFRE(beanList.getRecord(4));
					msg.setE01DCISTA(beanList.getRecord(5));
					msg.setE01DCIPAG(beanList.getRecord(6));
					msg.setE01DCITYP(beanList.getRecord(7));
					msg.setE01DCIEX1(beanList.getRecord(9));
					msg.setE01DCIEX2(beanList.getRecord(10));
					msg.setE01DCIEX3(beanList.getRecord(11));
				
					mp.sendMessage(msg);
					msgError = (ELEERRMessage) mp.receiveMessageRecord("ELEERR");
				}
				// send end of list
				msg = (EDI001001Message) mp.getMessageRecord("EDI001001", user.getH01USR(), "");
				msg.setH01FLGMAS("*");
				mp.sendMessage(msg);
			} else { //Update only selected item.
				beanList.setCurrentRow(row);
				msg = (EDI001001Message) mp.getMessageRecord("EDI001001", user.getH01USR(), "");
				msg.setH01SCRCOD("01");
				msg.setH01FLGWK1("1");
				msg.setH01FLGWK2(userPO.getHeader22());
				if (userPO.getHeader22().equals("C")) {
					msg.setE01DCIACC(userPO.getCusNum());
				} else {
					msg.setE01DCIACC(userPO.getIdentifier());
				}
				msg.setE01DCIADD(beanList.getRecord(0));
				msg.setE01DCITNU(beanList.getRecord(1));
				msg.setE01DCISEQ(beanList.getRecord(2));
				msg.setE01DCIDES(beanList.getRecord(3));
				msg.setE01DCIFRE(beanList.getRecord(4));
				msg.setE01DCISTA(beanList.getRecord(5));
				msg.setE01DCIPAG(beanList.getRecord(6));
				msg.setE01DCITYP(beanList.getRecord(7));
				msg.setE01DCIEX1(beanList.getRecord(9));
				msg.setE01DCIEX2(beanList.getRecord(10));
				msg.setE01DCIEX3(beanList.getRecord(11));
				
				mp.sendMessage(msg);
			}
			
			msgError = (ELEERRMessage) mp.receiveMessageRecord("ELEERR");
			if (mp.hasError(msgError)) {
				beanList.setNoResult(true);
				flexLog("Putting java beans into the session");
				session.setAttribute("error", msgError);
				session.setAttribute("docList", beanList);
				session.setAttribute("userPO", userPO);
				forward("EDI0010_doc_list.jsp", req, res);
				return;
			}
			
		
		} finally {
			if (mp != null)	mp.close();
		}
	}
	/**
	 * @param user
	 * @param req
	 * @param res
	 * @param session
	 */
	private void procReqDocList(ESS0030DSMessage user, HttpServletRequest req, HttpServletResponse res, HttpSession session) throws ServletException, IOException {
		UserPos userPO = (UserPos) session.getAttribute("userPO");
		MessageProcessor mp = null;
		String PageToCall = "";
		try {
			mp = getMessageProcessor("EDI0010", req);
			EDI001001Message msg = (EDI001001Message) mp.getMessageRecord("EDI001001", user.getH01USR(), "0002");
			msg.setH01SCRCOD("01");
			msg.setH01FLGWK1("R"); // Operation (either Send or Receive)
			/*
			//This is for Signature Card.
			if (req.getParameter("CUN") != null) {
				msg.setE01DCICUN(req.getParameter("CUN"));
				msg.setE01DCIACC(userPO.getIdentifier());
				userPO.setCusNum(req.getParameter("CUN"));
			}
			if (req.getParameter("NAM") != null) {
				userPO.setCusName(req.getParameter("NAM"));
			}
			if (req.getParameter("E02CUN") != null) {
				userPO.setHeader2(req.getParameter("E02CUN"));
			}
			if (req.getParameter("E02NA1") != null) {
				userPO.setHeader3(req.getParameter("E02NA1"));
			}
			//****************************
			*/

			String type = "";
			try {
				type = req.getParameter("Type");
				if (type == null) type = "";
			} catch (Exception e) {
				type = "";
			}
			msg.setH01FLGWK2(type);
			if (type.equals("C") && !userPO.getCusNum().trim().equals("")) {
				msg.setE01DCIACC(userPO.getCusNum());
			} else if (type.equals("A") || type.equals("F")) {
				msg.setE01DCIACC(userPO.getIdentifier());
			} 
			try {
				msg.setE01DCITNU(req.getParameter("TABLE_NUM"));
			} catch (Exception e) {
				msg.setE01DCITNU("");
			}
			mp.sendMessage(msg);
			
			ELEERRMessage msgError = new ELEERRMessage();
			
			MessageRecord newmessage = mp.receiveMessageRecord();
			if (mp.hasError(newmessage)) {
				msgError = (ELEERRMessage) newmessage;
				PageToCall = "EDI0010_doc_list.jsp";
			} else {
				boolean firstTime = true;
				String marker = "";
				String myFlag = "";
				if (newmessage.getFormatName().equals("EDI001001")) {
					int colNum = 12;
					String myRow[] = new String[colNum];
					for (int i = 0; i < colNum; i++) {
						myRow[i] = "";
					}
					JBListRec jbList = new JBListRec(); 
					jbList.init(colNum);
					while (true) {
						msg = (EDI001001Message) newmessage;
						marker = msg.getH01FLGMAS();
						if (marker.equals("*")) {
							break;
						} else {
							if (firstTime) {
								firstTime = false;
								userPO.setHeader18(Util.formatCell(msg.getE01DCITNU()));
								userPO.setHeader19(Util.formatCell(msg.getE01DCIDES()));
								userPO.setHeader20("");
								userPO.setHeader21("");
								userPO.setHeader22(type);
							} else {
								myRow[0] = type
										+ "_"
										+ msg.getE01DCIACC().trim()
										+ "_"
										+ msg.getE01DCITNU().trim()
										+ "_"
										+ msg.getE01DCISEQ().trim();
								/*		
								//SI EL TIPO DE DOCUMENTO ES "SC", ASUME TARJETA DE FIRMAS UNICA POR ID
								if (msg.getE01DCITYP().equals("SC")) {
									myRow[0] =
										req.getParameter("Type")
											+ "_"
											+ userPO.getCusNum().trim()
											+ "_"
											+ msg.getE01DCITNU().trim()
											+ "_"
											+ msg.getE01DCISEQ().trim();
								}
								*/		
								myRow[1] = msg.getE01DCITNU();
								myRow[2] = msg.getE01DCISEQ();
								myRow[3] = msg.getE01DCIDES();
								myRow[4] = msg.getE01DCIFRE();
								myRow[5] = msg.getE01DCISTA();
								myRow[6] = msg.getE01DCIPAG();
								myRow[7] = msg.getE01DCITYP();		
								myRow[8] = msg.getE01DCIDSQ();
								myRow[9] = msg.getE01DCIEX1();
								myRow[10] = msg.getE01DCIEX2();
								myRow[11] = msg.getE01DCIEX3();
								
								jbList.addRow(myFlag, myRow);
							}
						}
						newmessage = mp.receiveMessageRecord();	
					}	
					session.setAttribute("docList", jbList);
					PageToCall = "EDI0010_doc_list.jsp";
				} else {
					if (newmessage.getFormatName().equals("EDI001003")) {
						StringBuffer myRow = null;
						String chk = "";
						JBList tableList = new JBList();
						while (true) {
							EDI001003Message msgTables = (EDI001003Message) newmessage;
							marker = msgTables.getH03FLGMAS();
							if (marker.equals("*")) {
								break;
							} else {
								if (firstTime) {
									firstTime = false;
									chk = "checked";
									userPO.setHeader22(type);
								} else {
									chk = "";	
								}
								myRow = new StringBuffer("<TR>");
								myRow.append("<TD NOWRAP><input type=\"radio\" name=\"TABLE_NUM\" value=\""
									+ msgTables.getE03DCITNU()
									+ "\" "
									+ chk
									+ "></TD>");
								myRow.append("<TD NOWRAP>" + Util.formatCell(msgTables.getE03DCITNU()) + "</TD>");
								myRow.append("<TD NOWRAP>" + Util.formatCell(msgTables.getE03DCIDSC()) + "</TD>");
								myRow.append("</TR>");

								tableList.addRow("", myRow.toString());
							}
							newmessage = mp.receiveMessageRecord();
						}	
						session.setAttribute("tblList", tableList);
						PageToCall = "EDI0010_doc_table_selection_list.jsp";
					} else {
						flexLog("Message " + newmessage.getFormatName() + " received.");
					}
				}
			}			
			flexLog("Putting java beans into the session");
			session.setAttribute("error", msgError);
			session.setAttribute("userPO", userPO);
			
			forward(PageToCall, req, res);

		} finally {
			if (mp != null)	mp.close();
		}
	}

}
