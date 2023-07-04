package datapro.eibs.tools;

/**
 * Insert the type's description here.
 * Creation date: (1/19/00 6:08:55 PM)
 * @author: David Mavilla
 */
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.ByteArrayOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;


import java.math.BigDecimal;

import java.beans.Beans;
import java.io.*;
import java.net.Socket;
import java.util.*;

import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import datapro.eibs.beans.EDEN02001Message;
import datapro.eibs.beans.ESS0030DSMessage;
import datapro.eibs.beans.ELEERRMessage;
import datapro.eibs.beans.UserPos;
import datapro.eibs.beans.JBObjList;

import datapro.eibs.master.*;
import datapro.eibs.master.SuperServlet;
import datapro.eibs.master.Util;

import datapro.eibs.sockets.*;
import datapro.eibs.sockets.MessageField;
import datapro.eibs.sockets.MessageRecord;

import com.datapro.generic.beanutil.BeanList;
import com.lowagie.text.Cell;
import com.lowagie.text.Document;
import com.lowagie.text.DocumentException;
import com.lowagie.text.Element;
import com.lowagie.text.Font;
import com.lowagie.text.FontFactory;
import com.lowagie.text.HeaderFooter;
import com.lowagie.text.PageSize;
import com.lowagie.text.Paragraph;
import com.lowagie.text.Phrase;
import com.lowagie.text.Rectangle;
import com.lowagie.text.Table;
import com.lowagie.text.pdf.PdfPTable;
import com.lowagie.text.pdf.PdfWriter;
import com.lowagie.text.Image;

public class JSEDEN020 extends datapro.eibs.master.SuperServlet {

	// certificate of deposit 

	// entering options
	protected static final int R_SEARCH_BY_NAME = 100;
	protected static final int A_SEARCH_BY_NAME = 200;
	
	protected static final int A_LIST = 300;
	
	protected static final int R_PRINT_BY_NAME = 700;
	
	protected static final int R_NEW = 1;
	protected static final int A_NEW = 5;
	
	protected static final int A_MAINTENANCE = 2;
	protected static final int A_DELETE = 3;
	protected static final int A_COPY = 4;
	
	protected String LangPath = "S";

	/**
	 * JSECLI001 constructor comment.
	 */
	public JSEDEN020() {
		super();
	}
	/**
	 * This method was created by Orestes Garcia.
	 */
	public void destroy() {

		flexLog("free resources used by JSEDL0130");

	}
	/**
	 * This method was created by David Mavilla.
	 */
	public void init(ServletConfig config) throws ServletException {
		super.init(config);
	}

	/**
	 * This method was created in VisualAge.
	 */

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

	
		int screen = R_SEARCH_BY_NAME;

			try {

				msgUser = (datapro.eibs.beans.ESS0030DSMessage) session.getAttribute("currUser");

				// Here we should get the path from the user profile
				LangPath = super.rootPath + msgUser.getE01LAN() + "/";

				try {
					flexLog("Opennig Socket Connection");
					s = new Socket(super.hostIP, getInitSocket(req) + 1);
					s.setSoTimeout(super.sckTimeOut);
					mc =
						new MessageContext(
							new DataInputStream(new BufferedInputStream(s.getInputStream())),
							new DataOutputStream(new BufferedOutputStream(s.getOutputStream())),
							"datapro.eibs.beans");
				

				try {
					screen = Integer.parseInt(req.getParameter("SCREEN"));
				} catch (Exception e) {
					flexLog("Screen set to default value");
				}

				switch (screen) {
					// BEGIN CD
					// Request
					case R_SEARCH_BY_NAME :
						procReqSearchByname( msgUser, req, res, session);
						break;
					case R_NEW :
						procReqNew(mc, msgUser, req, res, session);
						break;

						// Action
					case A_SEARCH_BY_NAME :
						procActionSearchByname(mc, msgUser, req, res, session);
						break;
					case A_LIST :
						procActionList(mc, msgUser, req, res, session);
						break;
					case A_NEW :
						procActionNew(mc, msgUser, req, res, session);
						break;
					case A_MAINTENANCE :
						procActionMaintenance(mc, msgUser, req, res, session);
						break;
					case A_COPY :
						procActionCopy(mc, msgUser, req, res, session);
						break;
					case A_DELETE :
						procActionDelete(mc, msgUser, req, res, session);
						break;

						// END Entering

					default :
						res.sendRedirect(super.srctx + LangPath + super.devPage);
						break;
				}

				} catch (Exception e) {
					e.printStackTrace();
					int sck = getInitSocket(req) + 3;
					flexLog("Socket not Open(Port " + sck + "). Error: " + e);
					res.sendRedirect(super.srctx + LangPath + super.sckNotOpenPage);
					//return;
				} finally {
					s.close();
				}

			} catch (Exception e) {
				flexLog("Error: " + e);
				res.sendRedirect(super.srctx + LangPath + super.sckNotRespondPage);
			}

		}

	}
	
	protected void showERROR(ELEERRMessage m) {
		if (logType != NONE) {

			flexLog("ERROR received.");

			flexLog("ERROR number:" + m.getERRNUM());
			flexLog("ERR001 = " + m.getERNU01() + " desc: " + m.getERDS01());
			flexLog("ERR002 = " + m.getERNU02() + " desc: " + m.getERDS02());
			flexLog("ERR003 = " + m.getERNU03() + " desc: " + m.getERDS03());
			flexLog("ERR004 = " + m.getERNU04() + " desc: " + m.getERDS04());
			flexLog("ERR005 = " + m.getERNU05() + " desc: " + m.getERDS05());
			flexLog("ERR006 = " + m.getERNU06() + " desc: " + m.getERDS06());
			flexLog("ERR007 = " + m.getERNU07() + " desc: " + m.getERDS07());
			flexLog("ERR008 = " + m.getERNU08() + " desc: " + m.getERDS08());
			flexLog("ERR009 = " + m.getERNU09() + " desc: " + m.getERDS09());
			flexLog("ERR010 = " + m.getERNU10() + " desc: " + m.getERDS10());

		}
	}

	/**
	 * This method was created in VisualAge.
	 */
	protected void procReqSearchByname(
		ESS0030DSMessage user,
		HttpServletRequest req,
		HttpServletResponse res,
		HttpSession ses)
		throws ServletException, IOException {

		ELEERRMessage msgError = null;
		UserPos userPO = null;

		try {

			msgError = new datapro.eibs.beans.ELEERRMessage();
			userPO = new datapro.eibs.beans.UserPos();		
			ses.setAttribute("error", msgError);
			ses.setAttribute("userPO", userPO);
			

		} catch (Exception ex) {
			flexLog("Error: " + ex);
		}

		try {
			String OPT = (req.getParameter("OPT") == null) ? "" : req.getParameter("OPT");
			userPO.setAccOpt(OPT);
			flexLog("About to call Page: " + LangPath + "EDEN020_our_denial_search_by_name_enter.jsp");
			callPage(LangPath + "EDEN020_our_denial_search_by_name_enter.jsp", req, res);
		} catch (Exception e) {
			flexLog("Exception calling page " + e);
		}

	}


	protected void procActionSearchByname(
		MessageContext mc,
		ESS0030DSMessage user,
		HttpServletRequest req,
		HttpServletResponse res,
		HttpSession ses)
		throws ServletException, IOException {

		MessageRecord newmessage = null;
		ELEERRMessage msgError = null;
		EDEN02001Message msgList = null;
		JBObjList beanList = null;
		UserPos userPO = null;
		boolean IsNotError = false;

		try {
			msgError = new ELEERRMessage();
		} catch (Exception ex) {
			flexLog("Error: " + ex);
		}

		userPO = (datapro.eibs.beans.UserPos) ses.getAttribute("userPO");
		// Send Initial data
		try {
			flexLog("Send Initial Data");
			msgList = (EDEN02001Message) mc.getMessageRecord("EDEN02001");
			msgList.setH01USERID(user.getH01USR());
			msgList.setH01OPECOD("0015");
			
			try {
				msgList.setE01DOAME(req.getParameter("E01DOAME"));
				userPO.setIdentifier(req.getParameter("E01DOAME"));
			} catch (Exception e) {
				msgList.setE01DOAME("");
			}
			try {
				msgList.setE01RWDFRC(req.getParameter("FromRecord"));
			} catch (Exception e) {
				msgList.setE01RWDFRC("");
			}
			
			msgList.send();
			msgList.destroy();
		} catch (Exception e) {
			e.printStackTrace();
			flexLog("Error: " + e);
			throw new RuntimeException("Socket Communication Error");
		}

		// Receive Message
		try {
			newmessage = mc.receiveMessage();
			
			 if (newmessage.getFormatName().equals("EDEN02001")) {

				try {
					beanList = new JBObjList();
				} catch (Exception ex) {
					flexLog("Error: " + ex);
				}

				String marker = "";
				boolean firstTime = true;

				while (true) {

					msgList = (EDEN02001Message) newmessage;
					msgList.setHandler(null);
					marker = msgList.getE01SWDOPE();

					if (marker.equals("*")) {
						beanList.setShowNext(false);
						break;
					} else {
						if (firstTime) {
							firstTime = false;
							beanList.setFirstRec(Integer.parseInt(msgList.getE01SWDREC()));
							userPO.setHeader1(msgList.getE01SWDREC());
						}						
						beanList.addRow(msgList);
						
						if (marker.equals("+")) {
							beanList.setShowNext(true);
							break;
						}						
					}
					newmessage = mc.receiveMessage();
				}
				//
				flexLog("Putting java beans into the session");
				ses.setAttribute("userPO", userPO);
				ses.setAttribute("shrList", beanList);
				ses.setAttribute("error", msgError);

				try {
					flexLog("About to call Page: " + LangPath + "EDEN020_our_denial_search_by_name_list.jsp");
					callPage(LangPath + "EDEN020_our_denial_search_by_name_list.jsp",req,res);
				} catch (Exception e) {
					flexLog("Exception calling page " + e);
				}
				
			}
		} catch (Exception e) {
			e.printStackTrace();
			flexLog("Error: " + e);
			throw new RuntimeException("Socket Communication Error");
		}

	}

	protected void procActionList(
			MessageContext mc,
			ESS0030DSMessage user,
			HttpServletRequest req,
			HttpServletResponse res,
			HttpSession ses)
			throws ServletException, IOException {
	
			try {
				int option = Integer.parseInt(req.getParameter("OPT"));
  				int row = Integer.parseInt(req.getParameter("ROW"));
	
				JBObjList beanList = null;
				beanList = (JBObjList) ses.getAttribute("shrList");
				
				beanList.setCurrentRow(row);
				EDEN02001Message msgPart = (EDEN02001Message) beanList.getRecord();	
				
				if (option == 4) {
					msgPart.setE01DOAME("");
					msgPart.setE01DOCOU("");
				}
				
				ses.setAttribute("msgPart", msgPart);
				
				switch (option) {
					case 1 : // New
				    	try {
							flexLog("About to call Page: " + LangPath + "EDEN020_our_denial_new.jsp");
							callPage(LangPath + "EDEN020_our_denial_new.jsp", req, res);
						} catch (Exception e) {
							flexLog("Exception calling page " + e);
						}
						break;
					case 2 : // Maintenance
						try {
							flexLog("About to call Page: " + LangPath + "EDEN020_our_denial_maint.jsp");
							callPage(LangPath + "EDEN020_our_denial_maint.jsp", req, res);
						} catch (Exception e) {
							flexLog("Exception calling page " + e);
						}
						break;
					case 4 : // Copy
						try {
							flexLog("About to call Page: " + LangPath + "EDEN020_our_denial_copy.jsp");
							callPage(LangPath + "EDEN020_our_denial_copy.jsp", req, res);
						} catch (Exception e) {
							flexLog("Exception calling page " + e);
						}
						break;
					
						
				}
				
			} catch (Exception e) {
				e.printStackTrace();
				flexLog("Error: " + e);
				throw new RuntimeException("Socket Communication Error");
			}
	
	}

	protected void procReqNew(
			MessageContext mc,
			ESS0030DSMessage user,
			HttpServletRequest req,
			HttpServletResponse res,
			HttpSession ses)
			throws ServletException, IOException {
	
			MessageRecord newmessage = null;
			EDEN02001Message msgPart = null;
			ELEERRMessage msgError = null;
			UserPos userPO = null;
			boolean IsNotError = false;
		
			String opCode = "";
			userPO = (datapro.eibs.beans.UserPos) ses.getAttribute("userPO");
		
			// Send Initial data
			try {
				flexLog("Send Initial Data");
				msgPart = (EDEN02001Message)mc.getMessageRecord("EDEN02001");
				msgPart.setH01USERID(user.getH01USR());
				msgPart.setH01PROGRM("EDEN02001");
				msgPart.setH01TIMSYS(getTimeStamp());
				msgPart.setH01SCRCOD("01");
				msgPart.setH01OPECOD("0001");
	
				try {
					msgPart.setE01DOAME(req.getParameter("NEWNAME"));	
				} catch (Exception e) {
					msgPart.setE01DOAME("");
				}
				try {
					msgPart.setE01DOCOU(req.getParameter("NEWCOUNT"));	
				} catch (Exception e) {
					msgPart.setE01DOCOU("");
				}
				
				//msgPart.send();
				mc.sendMessage(msgPart);
				msgPart.destroy();
				flexLog("EDEN02001 Message Sent");
	
				// Receive Error Message
				newmessage = mc.receiveMessage();
				if (newmessage.getFormatName().equals("ELEERR")) {
					msgError = new ELEERRMessage();
					msgError = (ELEERRMessage) newmessage;
					IsNotError = msgError.getERRNUM().equals("0");
					flexLog("IsNotError = " + IsNotError);
					showERROR(msgError);
				} else 
					flexLog("Message " + newmessage.getFormatName() + " received.");
	
				// Receive Data
				newmessage = mc.receiveMessage();
				if (newmessage.getFormatName().equals("EDEN02001")) {
					msgPart = new datapro.eibs.beans.EDEN02001Message();
					msgPart = (EDEN02001Message) newmessage;
					flexLog("EDEN02001 Message Received");
								
					flexLog("Putting java beans into the session");
					ses.setAttribute("error", msgError);
					ses.setAttribute("msgPart", msgPart);
					ses.setAttribute("userPO", userPO);
	
					if (IsNotError) { // There are no errors
						try {
							flexLog("About to call Page: " + LangPath + "EDEN020_our_denial_new.jsp");
							callPage(LangPath + "EDEN020_our_denial_new.jsp", req, res);
						} catch (Exception e) {
							flexLog("Exception calling page " + e);
						}
					} else { // There are errors
						res.sendRedirect(super.srctx + "/servlet/datapro.eibs.tools.JSEDEN020?SCREEN=100&E01DOAME="
										+ userPO.getIdentifier()
										+ "&FromRecord="
										+ userPO.getHeader1());
	
					}
				} else
					flexLog("Message " + newmessage.getFormatName() + " received.");
	
			} catch (Exception e) {
				e.printStackTrace();
				flexLog("Error: " + e);
				throw new RuntimeException("Socket Communication Error");
			}
	
		}

	protected void procActionNew(
			MessageContext mc,
			ESS0030DSMessage user,
			HttpServletRequest req,
			HttpServletResponse res,
			HttpSession ses)
			throws ServletException, IOException {
	
			MessageRecord newmessage = null;
			EDEN02001Message msgPart = null;
			ELEERRMessage msgError = null;
			UserPos userPO = null;
			boolean IsNotError = false;
		
			String opCode = "";
			userPO = (datapro.eibs.beans.UserPos) ses.getAttribute("userPO");
		
			// Send Initial data
			try {
				flexLog("Send Initial Data");
				msgPart = (EDEN02001Message)mc.getMessageRecord("EDEN02001");
				msgPart.setH01USERID(user.getH01USR());
				msgPart.setH01PROGRM("EDEN02001");
				msgPart.setH01TIMSYS(getTimeStamp());
				msgPart.setH01SCRCOD("01");
				msgPart.setH01OPECOD("0005");
				msgPart.setH01FLGWK1("");
				
				// all the fields here
				java.util.Enumeration enu = msgPart.fieldEnumeration();
				MessageField field = null;
				String value = null;
				while (enu.hasMoreElements()) {
					field = (MessageField) enu.nextElement();
					try {
						value = req.getParameter(field.getTag()).toUpperCase();
						if (value != null) {
							field.setString(value);
						}
					} catch (Exception e) {
					}
				}
	
				//msgPart.send();
				mc.sendMessage(msgPart);
				msgPart.destroy();
				flexLog("EDEN02001 Message Sent");
	
				// Receive Error Message
				newmessage = mc.receiveMessage();
				if (newmessage.getFormatName().equals("ELEERR")) {
					msgError = new ELEERRMessage();
					msgError = (ELEERRMessage) newmessage;
					IsNotError = msgError.getERRNUM().equals("0");
					flexLog("IsNotError = " + IsNotError);
					showERROR(msgError);
				} else 
					flexLog("Message " + newmessage.getFormatName() + " received.");
	
				// Receive Data
				newmessage = mc.receiveMessage();
				if (newmessage.getFormatName().equals("EDEN02001")) {
					msgPart = new datapro.eibs.beans.EDEN02001Message();
					msgPart = (EDEN02001Message) newmessage;
					flexLog("EDEN02001 Message Received");
								
					flexLog("Putting java beans into the session");
					ses.setAttribute("error", msgError);
					ses.setAttribute("msgPart", msgPart);
					ses.setAttribute("userPO", userPO);
	
					if (IsNotError) { // There are no errors

res.sendRedirect(super.srctx + "/servlet/datapro.eibs.tools.JSEDEN020?SCREEN=1&OPT="
											  				
											                +  userPO.getAccOpt());
						              

						              

					} else { // There are errors
							try {
								flexLog("About to call Page: " + LangPath + "EDEN020_our_denial_new.jsp");
								callPage(LangPath + "EDEN020_our_denial_new.jsp", req, res);
							} catch (Exception e) {
								flexLog("Exception calling page " + e);
							}
	
					}
				} else
					flexLog("Message " + newmessage.getFormatName() + " received.");
	
			} catch (Exception e) {
				e.printStackTrace();
				flexLog("Error: " + e);
				throw new RuntimeException("Socket Communication Error");
			}
	
		}
			
	protected void procActionMaintenance(
			MessageContext mc,
			ESS0030DSMessage user,
			HttpServletRequest req,
			HttpServletResponse res,
			HttpSession ses)
			throws ServletException, IOException {
	
			MessageRecord newmessage = null;
			EDEN02001Message msgPart = null;
			ELEERRMessage msgError = null;
			UserPos userPO = null;
			boolean IsNotError = false;
		
			String opCode = "";
			userPO = (datapro.eibs.beans.UserPos) ses.getAttribute("userPO");
		
			// Send Initial data
			try {
				flexLog("Send Initial Data");
				msgPart = (EDEN02001Message)mc.getMessageRecord("EDEN02001");
				msgPart.setH01USERID(user.getH01USR());
				msgPart.setH01PROGRM("EDEN02001");
				msgPart.setH01TIMSYS(getTimeStamp());
				msgPart.setH01SCRCOD("01");
				msgPart.setH01OPECOD("0005");
				msgPart.setH01FLGWK1("");
	
				// all the fields here
				java.util.Enumeration enu = msgPart.fieldEnumeration();
				MessageField field = null;
				String value = null;
				while (enu.hasMoreElements()) {
					field = (MessageField) enu.nextElement();
					try {
						value = req.getParameter(field.getTag()).toUpperCase();
						if (value != null) {
							field.setString(value);
						}
					} catch (Exception e) {
					}
				}
	
				//msgPart.send();
				mc.sendMessage(msgPart);
				msgPart.destroy();
				flexLog("EDEN02001 Message Sent");
	
			    // Receive Error Message
				newmessage = mc.receiveMessage();
				if (newmessage.getFormatName().equals("ELEERR")) {
					msgError = new ELEERRMessage();
					msgError = (ELEERRMessage) newmessage;
					IsNotError = msgError.getERRNUM().equals("0");
					flexLog("IsNotError = " + IsNotError);
					showERROR(msgError);
				} else 
					flexLog("Message " + newmessage.getFormatName() + " received.");
	
			    // Receive Data
				newmessage = mc.receiveMessage();
				if (newmessage.getFormatName().equals("EDEN02001")) {
					msgPart = new datapro.eibs.beans.EDEN02001Message();
					msgPart = (EDEN02001Message) newmessage;
					flexLog("EDEN02001 Message Received");
								
					flexLog("Putting java beans into the session");
					ses.setAttribute("error", msgError);
					ses.setAttribute("msgPart", msgPart);
					ses.setAttribute("userPO", userPO);
	
					if (IsNotError) { // There are no errors

						res.sendRedirect(super.srctx + "/servlet/datapro.eibs.tools.JSEDEN020?SCREEN=100&E01DOAME="
										+ userPO.getIdentifier()
										+ "&FromRecord="
										+ userPO.getHeader1()
										+ "&OPT=" + userPO.getAccOpt());
										

					} else { // There are errors
							try {
								flexLog("About to call Page: " + LangPath + "EDEN020_our_denial_maint.jsp");
								callPage(LangPath + "EDEN020_our_denial_maint.jsp", req, res);
							} catch (Exception e) {
								flexLog("Exception calling page " + e);
							}
	
					}
				} else
					flexLog("Message " + newmessage.getFormatName() + " received.");
	
			} catch (Exception e) {
				e.printStackTrace();
				flexLog("Error: " + e);
				throw new RuntimeException("Socket Communication Error");
			}
	
		}

	protected void procActionCopy(
			MessageContext mc,
			ESS0030DSMessage user,
			HttpServletRequest req,
			HttpServletResponse res,
			HttpSession ses)
			throws ServletException, IOException {
	
			MessageRecord newmessage = null;
			EDEN02001Message msgPart = null;
			ELEERRMessage msgError = null;
			UserPos userPO = null;
			boolean IsNotError = false;
		
			String opCode = "";
			userPO = (datapro.eibs.beans.UserPos) ses.getAttribute("userPO");
		
			// Send Initial data
			try {
				flexLog("Send Initial Data");
				msgPart = (EDEN02001Message)mc.getMessageRecord("EDEN02001");
				msgPart.setH01USERID(user.getH01USR());
				msgPart.setH01PROGRM("EDEN02001");
				msgPart.setH01TIMSYS(getTimeStamp());
				msgPart.setH01SCRCOD("01");
				msgPart.setH01OPECOD("0005");
				msgPart.setH01FLGWK1("N");
	
				// all the fields here
				java.util.Enumeration enu = msgPart.fieldEnumeration();
				MessageField field = null;
				String value = null;
				while (enu.hasMoreElements()) {
					field = (MessageField) enu.nextElement();
					try {
						value = req.getParameter(field.getTag()).toUpperCase();
						if (value != null) {
							field.setString(value);
						}
					} catch (Exception e) {
					}
				}
	
				//msgPart.send();
				mc.sendMessage(msgPart);
				msgPart.destroy();
				flexLog("EDEN02001 Message Sent");
	
				// Receive Error Message
				newmessage = mc.receiveMessage();
				if (newmessage.getFormatName().equals("ELEERR")) {
					msgError = new ELEERRMessage();
					msgError = (ELEERRMessage) newmessage;
					IsNotError = msgError.getERRNUM().equals("0");
					flexLog("IsNotError = " + IsNotError);
					showERROR(msgError);
				} else 
					flexLog("Message " + newmessage.getFormatName() + " received.");
	
				// Receive Data
				newmessage = mc.receiveMessage();
				if (newmessage.getFormatName().equals("EDEN02001")) {
					msgPart = new datapro.eibs.beans.EDEN02001Message();
					msgPart = (EDEN02001Message) newmessage;
					flexLog("EDEN02001 Message Received");
								
					flexLog("Putting java beans into the session");
					ses.setAttribute("error", msgError);
					ses.setAttribute("msgPart", msgPart);
					ses.setAttribute("userPO", userPO);
	
					if (IsNotError) { // There are no errors

						res.sendRedirect(super.srctx + "/servlet/datapro.eibs.tools.JSEDEN020?SCREEN=100&E01DOAME="
										+ userPO.getIdentifier()
										+ "&FromRecord="
										+ userPO.getHeader1()
						                + "&OPT=" + userPO.getAccOpt());

					} else { // There are errors
							try {
								flexLog("About to call Page: " + LangPath + "EDEN020_our_denial_copy.jsp");
								callPage(LangPath + "EDEN020_our_denial_copy.jsp", req, res);
							} catch (Exception e) {
								flexLog("Exception calling page " + e);
							}
	
					}
				} else
					flexLog("Message " + newmessage.getFormatName() + " received.");
	
			} catch (Exception e) {
				e.printStackTrace();
				flexLog("Error: " + e);
				throw new RuntimeException("Socket Communication Error");
			}
	
		}

	protected void procActionDelete(
			MessageContext mc,
			ESS0030DSMessage user,
			HttpServletRequest req,
			HttpServletResponse res,
			HttpSession ses)
			throws ServletException, IOException {
	
			MessageRecord newmessage = null;
			EDEN02001Message msgPart = null;
			EDEN02001Message msgDel = null;
			ELEERRMessage msgError = null;
			UserPos userPO = null;
			boolean IsNotError = false;
	
			userPO = (datapro.eibs.beans.UserPos) ses.getAttribute("userPO");

	
			// Send Initial data
			try {
				int row = Integer.parseInt(req.getParameter("ROW"));
	
				JBObjList beanList = null;
				beanList = (JBObjList) ses.getAttribute("shrList");
				
				beanList.setCurrentRow(row);
				msgDel = (EDEN02001Message) beanList.getRecord();	
				
				flexLog("Send Initial Data");
				msgPart = (EDEN02001Message)mc.getMessageRecord("EDEN02001");
				msgPart.setH01USERID(user.getH01USR());
				msgPart.setH01PROGRM("EDEN02001");
				msgPart.setH01TIMSYS(getTimeStamp());
				msgPart.setH01SCRCOD("01");
				msgPart.setH01OPECOD("0004");

				try {
					msgPart.setE01DOAME(msgDel.getE01DOAME());	
				} catch (Exception e) {
					msgPart.setE01DOAME("");
				}
				try {
					msgPart.setE01DOCOU(msgDel.getE01DOCOU());	
				} catch (Exception e) {
					msgPart.setE01DOCOU("");
				}
			
				//msgPart.send();
				mc.sendMessage(msgPart);
				msgPart.destroy();
				flexLog("EDEN02001 Message Sent");

				// Receive Error Message
				newmessage = mc.receiveMessage();
				if (newmessage.getFormatName().equals("ELEERR")) {
					msgError = new ELEERRMessage();
					msgError = (ELEERRMessage) newmessage;
					IsNotError = msgError.getERRNUM().equals("0");
					flexLog("IsNotError = " + IsNotError);
					showERROR(msgError);
					
					ses.setAttribute("error", msgError);
					
					res.sendRedirect(super.srctx + "/servlet/datapro.eibs.tools.JSEDEN020?SCREEN=100&E01DOAME="
									+ userPO.getIdentifier()
									+ "&FromRecord="
									+ userPO.getHeader1()
					                + "&OPT=" + userPO.getAccOpt());
					
				} else 
					flexLog("Message " + newmessage.getFormatName() + " received.");


			} catch (Exception e) {
				e.printStackTrace();
				flexLog("Error: " + e);
				throw new RuntimeException("Socket Communication Error");
			}

	}

}