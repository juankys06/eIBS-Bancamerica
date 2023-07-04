package datapro.eibs.products;
                  
/**             
 * Insert the type's description here.
 * Creation date: (11/13/06 6:08:55 PM)
  */
import java.io.*;
import java.net.*;
import java.beans.Beans;
import javax.servlet.*;
import javax.servlet.http.*;
import datapro.eibs.beans.*;

import datapro.eibs.master.Util;

import datapro.eibs.sockets.*;

public class JSEGL6007 extends datapro.eibs.master.SuperServlet {

	
	protected static final int A_SEL = 0001;
	protected static final int A_VAL = 0002;
		
	protected String LangPath = "S";

	/**
	 * JSECLI001 constructor comment.
	 */
	public JSEGL6007() {
		super();
	}
	/**
	 * This method was created by Orestes Garcia.
	 */
	public void destroy() {

		flexLog("free resources used by JSEGL6007");

	}
	/**
	 * This method was created by Orestes Garcia.
	 */
	public void init(ServletConfig config) throws ServletException {
		super.init(config);
	}
	
	protected void procActionSel(
				MessageContext mc,
				ESS0030DSMessage user,
				HttpServletRequest req,
				HttpServletResponse res,
				HttpSession ses)
				throws ServletException, IOException {
	
				EGL600701Message msgMT = new EGL600701Message();
				MessageRecord newmessage = null;
				boolean IsNotError = false;
				ELEERRMessage msgError = null;
			
			
				try {
		
					flexLog("Send Initial Data");
					msgMT = (EGL600701Message)mc.getMessageRecord("EGL600701");
					msgMT.setH01USERID(user.getH01USR());
					msgMT.setH01PROGRM("EGL6007");
					msgMT.setH01TIMSYS(getTimeStamp());
					msgMT.setH01OPECOD("0001");
					msgMT.setE01TIPROC("M"); 
				
						if (IsNotError) {
							res.setContentType("text/html");
							PrintWriter out = res.getWriter();
							String href = req.getContextPath() + "/pages/s/MISC_search_wait.jsp?URL='" + req.getContextPath() + "/servlet/datapro.eibs.products.JSEGL6007?SCREEN=0001'";
							out.println("<HTML>");
							out.println("<HEAD>");
							out.println("<TITLE>Close</TITLE>");
							out.println("</HEAD>");
							out.println("<BODY>");
							out.println("<SCRIPT LANGUAGE=\"JavaScript\">");
							out.println("		top.opener.location.href = \""+ href + "\";");
							out.println("		top.close();");
							out.println("</SCRIPT>");
							out.println("<P>Close it!!!</P>");
							out.println("</BODY>");
							out.println("</HTML>");
							out.close();
						} else {
							flexLog("Putting java beans into the session");
							ses.setAttribute("error", msgError);
							ses.setAttribute("msgMT", msgMT);
							try {
								flexLog("About to call Page: " + LangPath + "EGL6007_contable_sel.jsp");
								callPage(LangPath + "EGL6007_contable_sel.jsp", req, res);
								
							} catch (Exception e) {
								flexLog("Exception calling page " + e);
							}
						}			
		
				} catch (Exception e) {
					e.printStackTrace();
					flexLog("Exception calling page " + e);
				}

			}	

	protected void procActionVal(
					MessageContext mc,
					ESS0030DSMessage user,
					HttpServletRequest req,
					HttpServletResponse res,
					HttpSession ses)
					throws ServletException, IOException {
	
					EGL600701Message msgMT = new EGL600701Message();
					MessageRecord newmessage = null;
					boolean IsNotError = false;
					ELEERRMessage msgError = null;
				
					UserPos userPO = new UserPos();
					/*ses.setAttribute("userPO", userPO);
					userPO = (datapro.eibs.beans.UserPos) ses.getAttribute("userPO");
					userPO.setBank(user.getE01UBK());
					userPO.setBranch(user.getE01UBR());*/
					userPO = (datapro.eibs.beans.UserPos) ses.getAttribute("userPO");
		
					try {
		
						flexLog("Send Initial Data");
						msgMT = (EGL600701Message)mc.getMessageRecord("EGL600701");
						msgMT.setH01USERID(user.getH01USR());
						msgMT.setH01PROGRM("EGL6007");
						msgMT.setH01TIMSYS(getTimeStamp());
						msgMT.setH01OPECOD("0001");
						msgMT.setE01TIPROC("M"); 
						// all the fields here
						java.util.Enumeration enu = msgMT.fieldEnumeration();
						MessageField field = null;
						String value = null;
						while (enu.hasMoreElements()) {
							field = (MessageField) enu.nextElement();
							try {
								value = req.getParameter(field.getTag()).toUpperCase().trim();
								if (value != null) {
									field.setString(value);
								}
							} catch (Exception e) {
							}
						}

						msgMT.send(); 
						msgMT.destroy();
			
						// Receive Error Message
		
						newmessage = mc.receiveMessage();

						if (newmessage.getFormatName().equals("ELEERR")) {
							msgError = (ELEERRMessage) newmessage;
							IsNotError = msgError.getERRNUM().equals("0");
							flexLog("IsNotError = " + IsNotError);
				
						} else{
							flexLog("Message " + newmessage.getFormatName() + " received.");
						}
				
						// Receive Data
		
						newmessage = mc.receiveMessage();

						if (newmessage.getFormatName().equals("EGL600701")) {
				
							msgMT = (EGL600701Message) newmessage;
				
							if (IsNotError) {	
								flexLog("Send Initial Data");
								msgMT = (EGL600701Message)mc.getMessageRecord("EGL600701");
								msgMT.setH01USERID(user.getH01USR());
								msgMT.setH01PROGRM("EGL6007");
								msgMT.setH01TIMSYS(getTimeStamp());
								msgMT.setH01OPECOD("0001");
								flexLog("About " + "to call Page: " + LangPath + "EGL6007_contable_batch.jsp");
								callPage(LangPath + "EGL6007_contable_batch.jsp", req, res);						
								try {
									flexLog("About to call Page: " + LangPath + "EGL6007_contable_sel.jsp");
									callPage(LangPath + "EGL6007_contable_sel.jsp", req, res);						
								} catch (Exception e) {
									flexLog("Exception calling page " + e);
								}
										     	           
							} else {     
								flexLog("Putting java beans into the session");
								ses.setAttribute("error", msgError);
								ses.setAttribute("msgMT", msgMT);
								try {
									flexLog("About to call Page: " + LangPath + "EGL6007_contable_sel.jsp");
									callPage(LangPath + "EGL6007_contable_sel.jsp", req, res);						

								} catch (Exception e) {
									flexLog("Exception calling page " + e);
								}
							}
						}else{
							flexLog("Message " + newmessage.getFormatName() + " received.");
						}
				
		
					} catch (Exception e) {
						e.printStackTrace();
						flexLog("Exception calling page " + e);
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
 
			int screen = A_SEL;

			try {

				// msgUser =(ESS0030DSMessage) session.getAttribute("currUser");
				msgUser =(datapro.eibs.beans.ESS0030DSMessage) session.getAttribute("currUser");

				

				// Here we should get the path from the user profile
				LangPath = super.rootPath + msgUser.getE01LAN() + "/";

				try {
					flexLog("Opennig Socket Connection");
					s = new Socket(super.hostIP, super.iniSocket + 70);
					s.setSoTimeout(super.sckTimeOut);
					mc =
						new MessageContext(
							new DataInputStream(
								new BufferedInputStream(s.getInputStream())),
							new DataOutputStream(
								new BufferedOutputStream(s.getOutputStream())),
							"datapro.eibs.beans");

					try {
						screen = Integer.parseInt(req.getParameter("SCREEN"));
					} catch (Exception e) {
						flexLog("Screen set to default value");
					}
                     
					switch (screen) {
						
						case A_VAL :
							procActionVal(mc,msgUser, req, res, session);
							break;
						case A_SEL :
							procActionSel(mc,msgUser, req, res, session);
							break;						
						default :
							res.sendRedirect(super.srctx + LangPath + super.devPage);
							break;
							          
					}
				} catch (Exception e) {
					e.printStackTrace();
					int sck = super.iniSocket + 1;
					flexLog("About to call Page: " + sck + "). Error: " + e); 
					res.sendRedirect(super.srctx + LangPath + "MISC_not_available.jsp");
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
		
}