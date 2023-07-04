package datapro.eibs.approval;

/**
 * Insert the type's description here.
 * Creation date: (1/19/00 6:08:55 PM)
 * @author: Orestes Garcia
 */
import java.io.*;
import java.math.BigDecimal;
import java.net.*;
import java.beans.Beans;
import javax.servlet.*;
import javax.servlet.http.*;
import datapro.eibs.beans.*;

import datapro.eibs.master.Util;
import datapro.eibs.master.JSEIBSProp;
import datapro.eibs.sockets.*;

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

public class JSERPTSTD extends datapro.eibs.master.SuperServlet {

	
	protected static final int R_REPORT_DEMAND = 25;
	protected static final int A_REPORT_DEMAND = 26;
	protected static final int A_REPORT_PARAM  = 27;
	
	// entering options
	protected static final int R_ENTER = 100;
	protected static final int A_ENTER = 400;

	protected String LangPath = "S";

	/**
	 * JSECLI001 constructor comment.
	 */
	public JSERPTSTD() {
		super();
	}
	/**
	 * This method was created by Orestes Garcia.
	 */
	public void destroy() {

		flexLog("free resources used by JSESD0080");

	}
	/**
	 * This method was created by Orestes Garcia.
	 */
	public void init(ServletConfig config) throws ServletException {
		super.init(config);
	}

	/**
	 * This method was created by Orestes Garcia.
	 * @param request HttpServletRequest
	 * @param response HttpServletResponse
	 */

	public void service(HttpServletRequest req, HttpServletResponse res)
		throws ServletException, IOException {

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

			int screen = R_REPORT_DEMAND;

			try {

				msgUser =
					(datapro.eibs.beans.ESS0030DSMessage) session.getAttribute(
						"currUser");

				// Here we should get the path from the user profile
				LangPath = super.rootPath + msgUser.getE01LAN() + "/";

				try {
					flexLog("Opennig Socket Connection");
					mc = new MessageContext(getMessageHandler("ERPTSTD", req));

					try {
						screen = Integer.parseInt(req.getParameter("SCREEN"));
					} catch (Exception e) {
						flexLog("Screen set to default value");
					}

					switch (screen) {
						// Requests
						case R_REPORT_DEMAND :
							procReqReportDemand(mc, msgUser, req, res, session);
							break;
				
													
							// Actions
						case A_REPORT_DEMAND :
							procActionReportDemand(mc, msgUser, req, res, session);
							break;
						case A_REPORT_PARAM :
							procActionParameters(mc, msgUser, req, res, session);
							break;
							
						default :
							res.sendRedirect(super.srctx + LangPath + super.devPage);
							break;
					}
				} catch (Exception e) {
					e.printStackTrace();
					flexLog("Socket not Open. Error: " + e);
					res.sendRedirect(super.srctx + LangPath + super.sckNotOpenPage);
					//return;
				} finally {
					if(mc != null) mc.close();
				}

			} catch (Exception e) {
				flexLog("Error: " + e);
				res.sendRedirect(super.srctx + LangPath + super.sckNotRespondPage);
			}

		}

	}
	
	
	protected void showERROR(ELEERRMessage m)
	{
		if (logType != NONE) {
		
			flexLog("ERROR received.");
		
			flexLog("ERROR number:" + m.getERRNUM());
			flexLog("ERR001 = " + m.getERNU01() + " desc: " + m.getERDS01() + " code : " + m.getERDF01());
			flexLog("ERR002 = " + m.getERNU02() + " desc: " + m.getERDS02() + " code : " + m.getERDF02());
			flexLog("ERR003 = " + m.getERNU03() + " desc: " + m.getERDS03() + " code : " + m.getERDF03());
			flexLog("ERR004 = " + m.getERNU04() + " desc: " + m.getERDS04() + " code : " + m.getERDF04());
			flexLog("ERR005 = " + m.getERNU05() + " desc: " + m.getERDS05() + " code : " + m.getERDF05());
			flexLog("ERR006 = " + m.getERNU06() + " desc: " + m.getERDS06() + " code : " + m.getERDF06());
			flexLog("ERR007 = " + m.getERNU07() + " desc: " + m.getERDS07() + " code : " + m.getERDF07());
			flexLog("ERR008 = " + m.getERNU08() + " desc: " + m.getERDS08() + " code : " + m.getERDF08());
			flexLog("ERR009 = " + m.getERNU09() + " desc: " + m.getERDS09() + " code : " + m.getERDF09());
			flexLog("ERR010 = " + m.getERNU10() + " desc: " + m.getERDS10() + " code : " + m.getERDF10());
		
		}
	}	
	

	protected void procReqReportDemand(
		MessageContext mc,
		ESS0030DSMessage user,
		HttpServletRequest req,
		HttpServletResponse res,
		HttpSession ses)
		throws ServletException, IOException {

		MessageRecord newmessage = null;
		EREPORTSTDMessage msgCC = null;
		ELEERRMessage msgError = null;
		UserPos userPO = null;
		boolean IsNotError = false;
		JBObjList beanList = null;

		try {
			msgError = new ELEERRMessage();
		} catch (Exception ex) {
			flexLog("Error: " + ex);
		}

		userPO = (datapro.eibs.beans.UserPos) ses.getAttribute("userPO");

		// Send Initial data
		try {
			msgCC = (EREPORTSTDMessage) mc.getMessageRecord("EREPORTSTD");
			msgCC.setH01USERID(user.getH01USR());
			msgCC.setH01PROGRM("DD0943P");
			msgCC.setH01TIMSYS(getTimeStamp());
			msgCC.setH01SCRCOD("01");
			msgCC.setH01OPECOD("0010");
			msgCC.setE01REPNME("");
			try {
				msgCC.setE01APLCOD(req.getParameter("TYPE"));		
			} catch (Exception e) {}
			
			msgCC.send();
			msgCC.destroy();
			flexLog("EREPORTSTD Message Sent");
		} catch (Exception e) {
			e.printStackTrace();
			flexLog("Error: " + e);
			throw new RuntimeException("Socket Communication Error");
		}
		
		// Receive Error Message
		try {
			newmessage = mc.receiveMessage();

			if (newmessage.getFormatName().equals("ELEERR")) {
				msgError = (ELEERRMessage) newmessage;

				flexLog("Putting java beans into the session");
				ses.setAttribute("error", msgError);
				ses.setAttribute("userPO", userPO);

				try {
					flexLog("About to call Page: " + LangPath + "error_viewer.jsp");
					callPage(LangPath + "error_viewer.jsp", req, res);						
				} catch (Exception e) {
					flexLog("Exception calling page " + e);
				}
			
			} else if (newmessage.getFormatName().equals("EREPORTSTD")) {
				beanList = new JBObjList();
				String marker = "";
				msgCC = null;

				while (true) {
					msgCC = (EREPORTSTDMessage) newmessage;
					marker = msgCC.getH01FLGMAS();

					if (marker.equals("*")) {
						beanList.setShowNext(false);
						break;
					} else {
						beanList.addRow(msgCC);
						if (marker.equals("+")) {
							beanList.setShowNext(true);
							break;
						}
					}
					newmessage = mc.receiveMessage();
				}
				
				flexLog("Putting java beans into the session");
				ses.setAttribute("dvList", beanList);
				ses.setAttribute("userPO", userPO);	

				try {
					flexLog("About to call Page: " + LangPath + "EDD0924_report_demand.jsp");
					callPage(LangPath + "EDD0924_report_demand.jsp", req, res);						
				} catch (Exception e) {
					flexLog("Exception calling page " + e);
				}

			} else
				flexLog("Message " + newmessage.getFormatName() + " received.");

		} catch (Exception e) {
			e.printStackTrace();
			flexLog("Error: " + e);
			throw new RuntimeException("Socket Communication Error");
		}		

	}
	
	protected void procActionReportDemand(
		MessageContext mc,
		ESS0030DSMessage user,
		HttpServletRequest req,
		HttpServletResponse res,
		HttpSession ses)
		throws ServletException, IOException {

		MessageRecord newmessage = null;
		ELEERRMessage msgError = null;
		EREPORTSTDMessage msgList = null;
		EREPORTSTDMessage msgSearch = null;
		JBObjList beanList = null;
		boolean IsNotError = false;
		UserPos userPO = null;
		
		userPO = (datapro.eibs.beans.UserPos) ses.getAttribute("userPO");		

		try {
			msgError = new datapro.eibs.beans.ELEERRMessage();
		} catch (Exception ex) {
			flexLog("Error: " + ex);
		}

		int row = 0;
		row = Integer.parseInt(req.getParameter("ROW"));
		beanList = (JBObjList) ses.getAttribute("dvList");
		beanList.setCurrentRow(row);	 
		msgList = (EREPORTSTDMessage) beanList.getRecord();				

		if (msgList.getE01REPPRM().equals("Y")) {
			//	Report with parameters
			procReqParameters( msgList, mc, user, req, res, ses);
					
		} else {
			//Report without parameters
			
			// Send Initial data
			try {
				flexLog("Send Initial Data");
				msgSearch = (EREPORTSTDMessage) mc.getMessageRecord("EREPORTSTD");
				msgSearch.setH01USERID(user.getH01USR());
				msgSearch.setH01PROGRM("EDD0924");
				msgSearch.setH01TIMSYS(getTimeStamp());
				msgSearch.setH01SCRCOD("01");
				msgSearch.setH01OPECOD("0015");
				
				try {
					msgSearch.setE01PRGNME(msgList.getE01PRGNME());
				} catch (Exception e) {
				}
				try {
					msgSearch.setE01REPNME(msgList.getE01REPNME());
				} catch (Exception e) {
				}
				try {
					msgSearch.setE01APLCOD(msgList.getE01APLCOD());
				} catch (Exception e) {
				}
	
				msgSearch.send();
				msgSearch.destroy();
				flexLog("EREPORTSTD Message Sent");
	
			} catch (Exception e) {
				e.printStackTrace();
				flexLog("Error: " + e);
				throw new RuntimeException("Socket Communication Error");
			}
	
			// Receive Data
			try {
				newmessage = mc.receiveMessage();
	
				if (newmessage.getFormatName().equals("ELEERR")) {
					
					msgError = (ELEERRMessage) newmessage;
					IsNotError = msgError.getERRNUM().equals("0");
					ses.setAttribute("error", msgError);
								
					try {
						flexLog("About to call Page: " + LangPath + "EDD0924_report_demand_confirm.jsp");
						callPage(LangPath + "EDD0924_report_demand_confirm.jsp", req, res);						
					} catch (Exception e) {
						flexLog("Exception calling page " + e);
					}
	
				} else
					flexLog("Message " + newmessage.getFormatName() + " received.");
	
			} catch (Exception e) {
				e.printStackTrace();
				flexLog("Error: " + e);
				throw new RuntimeException("Socket Communication Error");
			}
		}
	}		

	protected void procReqParameters(
		EREPORTSTDMessage msgList,
		MessageContext mc,
		ESS0030DSMessage user,
		HttpServletRequest req,
		HttpServletResponse res,
		HttpSession ses)
		throws ServletException, IOException {

		MessageRecord newmessage = null;
		ERP001001Message msgCC = null;
		ELEERRMessage msgError = null;
		UserPos userPO = null;
		boolean IsNotError = false;
		JBObjList beanList = null;

		try {
			msgError = new ELEERRMessage();
		} catch (Exception ex) {
			flexLog("Error: " + ex);
		}

		userPO = (datapro.eibs.beans.UserPos) ses.getAttribute("userPO");

		// Send Initial data
		try {
			msgCC = (ERP001001Message) mc.getMessageRecord("ERP001001");
			msgCC.setH01USERID(user.getH01USR());
			msgCC.setH01PROGRM("ERP0010");
			msgCC.setH01TIMSYS(getTimeStamp());
			msgCC.setH01SCRCOD("01");
			msgCC.setH01OPECOD("0010");
			try {
				msgCC.setE01RPTNME(msgList.getE01REPNME());
			} catch (Exception e) {
			}
			try {
				msgCC.setE01RPTPRG(msgList.getE01PRGNME());
			} catch (Exception e) {
			}
			try {
				msgCC.setE01RPTCOD(msgList.getE01APLCOD());
			} catch (Exception e) {
			}
		
			msgCC.send();
			msgCC.destroy();
			flexLog("ERP001001 Message Sent");
		} catch (Exception e) {
			e.printStackTrace();
			flexLog("Error: " + e);
			throw new RuntimeException("Socket Communication Error");
		}
		
		// Receive Error Message
		try {
			newmessage = mc.receiveMessage();

			if (newmessage.getFormatName().equals("ELEERR")) {
				msgError = (ELEERRMessage) newmessage;

				flexLog("Putting java beans into the session");
				ses.setAttribute("error", msgError);
				ses.setAttribute("userPO", userPO);

				try {
					flexLog("About to call Page: " + LangPath + "error_viewer.jsp");
					callPage(LangPath + "error_viewer.jsp", req, res);						
				} catch (Exception e) {
					flexLog("Exception calling page " + e);
				}
			
			} else if (newmessage.getFormatName().equals("ERP001001")) {
				beanList = new JBObjList();
				String marker = "";
				msgCC = null;

				while (true) {
					msgCC = (ERP001001Message) newmessage;
					marker = msgCC.getE01INDOPE();

					if (marker.equals("*")) {
						beanList.setShowNext(false);
						break;
					} else {
						beanList.addRow(msgCC);
						if (marker.equals("+")) {
							beanList.setShowNext(true);
							break;
						}
					}
					newmessage = mc.receiveMessage();
				}
				
				flexLog("Putting java beans into the session");
				ses.setAttribute("prList", beanList);
				ses.setAttribute("userPO", userPO);	

				try {
					flexLog("About to call Page: " + LangPath + "EDD0924_report_parameters.jsp");
					callPage(LangPath + "EDD0924_report_parameters.jsp", req, res);						
				} catch (Exception e) {
					flexLog("Exception calling page " + e);
				}

			} else
				flexLog("Message " + newmessage.getFormatName() + " received.");

		} catch (Exception e) {
			e.printStackTrace();
			flexLog("Error: " + e);
			throw new RuntimeException("Socket Communication Error");
		}		

	}

	protected void procActionParameters(
		MessageContext mc,
		ESS0030DSMessage user,
		HttpServletRequest req,
		HttpServletResponse res,
		HttpSession ses)
		throws ServletException, IOException {

		MessageRecord newmessage = null;
		ERP001001Message msgCC = null;
		ELEERRMessage msgError = null;
		UserPos userPO = null;
		boolean IsNotError = false;
		JBObjList beanList = null;

		try {
			msgError = new ELEERRMessage();
		} catch (Exception ex) {
			flexLog("Error: " + ex);
		}

		userPO = (datapro.eibs.beans.UserPos) ses.getAttribute("userPO");
		
		int totRows = Integer.parseInt(req.getParameter("TOTROWS"));
		
		String rptnme = "";
		String rptprg = "";
		
		// Send Initial data
		try {
			
			for (int row = 0; row < totRows; row++) {

				msgCC = (ERP001001Message) mc.getMessageRecord("ERP001001");
				msgCC.setH01USERID(user.getH01USR());
				msgCC.setH01PROGRM("ERP0010");
				msgCC.setH01TIMSYS(getTimeStamp());
				msgCC.setH01SCRCOD("01");
				msgCC.setH01OPECOD("0015");
				
				rptnme = req.getParameter("E01RPTNME_" + row);
				rptprg = req.getParameter("E01RPTPRG_" + row);
				
				msgCC.setE01RPTNME(req.getParameter("E01RPTNME_" + row));
				msgCC.setE01RPTPRG(req.getParameter("E01RPTPRG_" + row));
				msgCC.setE01RPTSEQ(req.getParameter("E01RPTSEQ_" + row));
				msgCC.setE01RPTCOD(req.getParameter("E01RPTCOD_" + row));
				msgCC.setE01RPTINS(req.getParameter("E01RPTINS_" + row));
				msgCC.setE01PMSPLN(req.getParameter("E01PMSPLN_" + row));
				msgCC.setE01PMSPTY(req.getParameter("E01PMSPTY_" + row));
				msgCC.setE01PMSNDC(req.getParameter("E01PMSNDC_" + row));
				msgCC.setE01PMSVAL(req.getParameter("E01PMSVAL_" + row));
				msgCC.setE01INDOPE("");
			
				msgCC.send();
				msgCC.destroy();
				flexLog("ERP001001 Message Sent");
			
			}

			msgCC = (ERP001001Message) mc.getMessageRecord("ERP001001");
			msgCC.setH01USERID(user.getH01USR());
			msgCC.setH01PROGRM("ERP0010");
			msgCC.setH01TIMSYS(getTimeStamp());
			msgCC.setH01SCRCOD("01");
			msgCC.setH01OPECOD("0015");
				
			msgCC.setE01RPTNME(rptnme);
			msgCC.setE01RPTPRG(rptprg);
			msgCC.setE01INDOPE("*");
			
			msgCC.send();
			msgCC.destroy();
			flexLog("ERP001001 Message Sent");
		
		} catch (Exception e) {
			e.printStackTrace();
			flexLog("Error: " + e);
			throw new RuntimeException("Socket Communication Error");
		}
		
		// Receive Error Message
		try {
			newmessage = mc.receiveMessage();

			if (newmessage.getFormatName().equals("ELEERR")) {
				msgError = (ELEERRMessage) newmessage;
				IsNotError = msgError.getERRNUM().equals("0");
				flexLog("IsNotError = " + IsNotError);

				try {
					flexLog("About to call Page: " + LangPath + "EDD0924_report_demand_confirm.jsp");
					callPage(LangPath + "EDD0924_report_demand_confirm.jsp", req, res);						
				} catch (Exception e) {
					flexLog("Exception calling page " + e);
				}
				
			} else
				flexLog("Message " + newmessage.getFormatName() + " received.");

		} catch (Exception e) {
			e.printStackTrace();
			flexLog("Error: " + e);
			throw new RuntimeException("Socket Communication Error");
		}
			
	}

}