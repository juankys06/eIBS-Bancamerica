package datapro.eibs.helps;

/**
 * This type was created by Orestes Garcia.
 */
import java.io.*;
import java.net.*;
import java.beans.Beans;
import javax.servlet.*;
import javax.servlet.http.*;
import datapro.eibs.beans.*;

import datapro.eibs.master.Util;

import datapro.eibs.sockets.*;

public class JSEWD0094 extends datapro.eibs.master.SuperServlet{

	/**
	 * Insert the method's description here.
	 * Creation date: (1/14/00 12:29:44 PM)
	 */
	public JSEWD0094() {
		super();
	}
	/**
	 * This method was created by Orestes Garcia.
	 */
public void destroy() {

		flexLog("free resources used by JSEWD0094");

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
		Socket s = null;
		MessageContext mc = null;
		HttpSession session;
		MessageRecord newmessage = null;
		EWD0094DSMessage msgHelp = null;
		EWD0094DSMessage msgHelpS = null;
		ESS0030DSMessage msgUser = null;
		JBList beanList = null;

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

			msgUser =
				(datapro.eibs.beans.ESS0030DSMessage) session.getAttribute(
					"currUser");
			String Language = msgUser.getE01LAN();
			String LangPath = super.rootPath + Language + "/";
			String Custype = "";
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

				try {
					msgHelp = (EWD0094DSMessage) mc.getMessageRecord("EWD0094DS");
					String TypeSearch= req.getParameter("Type");
					if (TypeSearch.equals("A"))
					{
					   msgHelp.setEWDCOD("");
					   msgHelp.setEWDDSC("");
					}
					else
					{
					   if (TypeSearch.equals("C"))
					   {
					      msgHelp.setEWDCOD(req.getParameter("NameSearch").toUpperCase());
					      msgHelp.setEWDDSC("");
					   }
					   else
					     {
     					    msgHelp.setEWDCOD("");
					        msgHelp.setEWDDSC(req.getParameter("NameSearch").toUpperCase());
					     
					     }
					}
					 
					
					
					msgHelp.setEWDREC(req.getParameter("FromRecord"));
					msgHelp.setEWDTBL(req.getParameter("fieldFlag")); 
	//			    msgHelp.setEWDTDS(req.getParameter("nameTable"));
					 
					
					msgHelp.send();
					msgHelp.destroy();
					flexLog("EWD0094DS Message Sent");
				} catch (Exception e) {
					e.printStackTrace();
					flexLog("Error: " + e);
					res.sendRedirect(super.srctx + LangPath + super.sckNotRespondPage);
					return;
				}

				// Receiving
				try {
					newmessage = mc.receiveMessage();

					if (newmessage.getFormatName().equals("EWD0094DS")) {

						try {
							beanList = new JBList();
							flexLog("EWD0094DS Message Received");
						} catch (Exception ex) {
							flexLog("Error: " + ex);
						}

						boolean firstTime = true;
						String marker = "";
						String myFlag = "";
						StringBuffer myRow = null;
						String chk = "";
                        String titleTable= "";
						while (true) {

							msgHelpS = (EWD0094DSMessage) newmessage;
							titleTable = msgHelpS.getEWDTDS();
							marker = msgHelpS.getEWDOPE();

							if (marker.equals("*")) {
								beanList.setShowNext(false);
								break;
							} else {

								if (firstTime) {
									firstTime = false;
									beanList.setFirstRec(
										Integer.parseInt(msgHelpS.getEWDREC()));
									chk = "checked";
								} else {
									chk = "";
								}
								myRow = new StringBuffer("<TR>");
								myRow.append(
									"<td nowrap><a href=\"javascript:enter('"
										+ msgHelpS.getEWDCOD()
										+ "','"
										+ msgHelpS.getEWDDSC()
										+ "')\">"
										+ msgHelpS.getEWDCOD()										 
										+ "</a></td>");
								myRow.append(
									"<td nowrap><a href=\"javascript:enter('"
									    + msgHelpS.getEWDCOD()
										+ "','"
										+ msgHelpS.getEWDDSC()										 
										+ "')\">"										
										+ msgHelpS.getEWDDSC() 
										+ "</a></td>");										
							 	
							 
								myRow.append("</TR>");
								beanList.addRow(myFlag, myRow.toString());

								if (marker.equals("+")) {
									beanList.setShowNext(true);
									break;
								}
							}
							newmessage = mc.receiveMessage();
						}

						flexLog("Putting java beans into the session");
						session.setAttribute("ewd0094Help", beanList);

						try {
							req.setAttribute("NameSearch",req.getParameter("NameSearch"));					 		
							req.setAttribute("Type", req.getParameter("Type"));
							req.setAttribute("fieldFlag", req.getParameter("fieldFlag"));
							req.setAttribute("nameTable",titleTable);
         			 
							flexLog(
								"About to call Page: "
									+ LangPath
									+ "EWD0094_client_desc_id_help_helpmessage.jsp");
							callPage(
								LangPath
									+ "EWD0094_client_desc_id_help_helpmessage.jsp",
								req,
								res);
						} catch (Exception e) {
							flexLog("Exception calling page " + e);
						}
					}
				} catch (Exception e) {
					e.printStackTrace();
					flexLog("Error: " + e);
					res.sendRedirect(super.srctx + LangPath + super.sckNotRespondPage);
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
}	