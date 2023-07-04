package datapro.eibs.products;
/**
 * Pre-Cancel Apply
 * Creation date: (08/29/07)
 * @author: Gustavo Adolfo Villarroel
 */ 
import datapro.eibs.beans.*;
import datapro.eibs.master.SuperServlet;
import datapro.eibs.sockets.MessageContext;
import datapro.eibs.sockets.MessageField;
import datapro.eibs.sockets.MessageRecord;
import datapro.eibs.master.Util;
import java.io.*;
import java.net.Socket;
import javax.servlet.*;
import javax.servlet.http.*;

public class JSESO0010 extends SuperServlet {

    protected static final int R_ENTER = 300;
    protected static final int R_LIST = 100;
    protected static final int A_LIST = 200;
    
    protected static final int R_COMPLETE = 1;
    protected static final int R_INQUIRY = 2;
    protected static final int A_DELETE = 4;
    
    protected String LangPath;

    public JSESO0010() {
        LangPath = "S";
    }

    public void destroy() {
        flexLog("free resources used by JSESO0010");
    }

    public void init(ServletConfig config) throws ServletException {
        super.init(config);
    }
    //
    // Option Menu
    //
    public void service(HttpServletRequest req, HttpServletResponse res)
    	throws ServletException, IOException {
    		
        Socket s = null;
        MessageContext mc = null;
        ESS0030DSMessage msgUser = null;
        HttpSession session = null;
        
        session = req.getSession(false);
        
        if(session == null) {
            try {
                res.setContentType("text/html");
                printLogInAgain(res.getWriter());
            }
            catch(Exception e) {
                e.printStackTrace();
                flexLog("Exception ocurred. Exception = " + e);
            }
        } else {
            int screen = 1;
            try {
                msgUser = (ESS0030DSMessage)session.getAttribute("currUser");
                LangPath = SuperServlet.rootPath + msgUser.getE01LAN() + "/";
                
                try {
                    flexLog("Opening Socket Connection");
                    s = new Socket(SuperServlet.hostIP, SuperServlet.getInitSocket(req) + 1);
                    s.setSoTimeout(SuperServlet.sckTimeOut);
                    mc = new MessageContext(new DataInputStream(new BufferedInputStream(s.getInputStream())), new DataOutputStream(new BufferedOutputStream(s.getOutputStream())), "datapro.eibs.beans");
                    try {
                        screen = Integer.parseInt(req.getParameter("SCREEN"));
                    }
                    catch(Exception e) {
                        flexLog("Screen set to default value");
                    }
                    switch(screen) {
                    
                    case R_LIST :
                        procReqList(mc, msgUser, req, res, session);
                        break;
                    case A_LIST :
                        procActionList(mc, msgUser, req, res, session);
                        break;
                    case R_ENTER :
						procReqEnter(mc, msgUser, req, res, session);
						break;
                    
					case R_COMPLETE :
						procReqComplete(mc, msgUser, req, res, session);
						break;
                    case R_INQUIRY :
						procReqInquiry(mc, msgUser, req, res, session);
						break;
					case A_DELETE :
						procActionDelete(mc, msgUser, req, res, session);
						break;

                    default:
                        res.sendRedirect(SuperServlet.srctx + LangPath + SuperServlet.devPage);
                        break;

                    }
                }
                catch(Exception e) {
                    e.printStackTrace();
                    int sck = SuperServlet.getInitSocket(req) + 1;
                    flexLog("Socket not Open(Port " + sck + "). Error: " + e);
                    res.sendRedirect(SuperServlet.srctx + LangPath + SuperServlet.sckNotOpenPage);
                }
                finally {
                    s.close();
                }
            }
            catch(Exception e) {
                flexLog("Error: " + e);
                res.sendRedirect(SuperServlet.srctx + LangPath + SuperServlet.sckNotRespondPage);
            }
        }
    }

    protected void showERROR(ELEERRMessage m) {
        if(SuperServlet.logType != 0) {
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
	protected void procReqEnter(
		MessageContext mc,
		ESS0030DSMessage user,
		HttpServletRequest req,
		HttpServletResponse res,
		HttpSession ses)
		throws ServletException, IOException {
	
		ELEERRMessage msgError = null;
		UserPos userPO = null;
		
		try {
			flexLog("About to call Page: " + LangPath + "ESO0010_cd_precancel_enter.jsp");
			callPage(LangPath + "ESO0010_cd_precancel_enter.jsp", req, res);
		} catch (Exception e) {
			flexLog("Exception calling page " + e);
		}
	
	}

    // 
    // Request Parameters for Search
    //
    protected void procReqList(
    	MessageContext mc,
    	ESS0030DSMessage user,
    	HttpServletRequest req,
    	HttpServletResponse res,
    	HttpSession ses)
    	throws ServletException, IOException {
        
		MessageRecord newmessage = null;
		ESO001001Message msgCD = null;
		ELEERRMessage msgError = null;
		UserPos userPO = null;
		JBObjList cdList = null;
		boolean IsNotError = false;

		try {
			msgError = new ELEERRMessage();
		} catch (Exception ex) {
			flexLog("Error: " + ex);
		}

		userPO = (datapro.eibs.beans.UserPos) ses.getAttribute("userPO");

		String opCode = null;

		// Send Initial data
		try {
			msgCD = (ESO001001Message) mc.getMessageRecord("ESO001001");
			msgCD.setH01USERID(user.getH01USR());
			msgCD.setH01PROGRM("ESO0010");
			msgCD.setH01TIMSYS(getTimeStamp());
			msgCD.setH01SCRCOD("01");
			msgCD.setH01OPECOD("0015");
			try {
				msgCD.setE01SELUSR(req.getParameter("E01SELUSR"));
			}
			catch (Exception e) {
				msgCD.setE01SELUSR("");
			}
			msgCD.send();
			msgCD.destroy();
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
				showERROR(msgError);
			} else
				flexLog("Message " + newmessage.getFormatName() + " received.");

		} catch (Exception e) {
			e.printStackTrace();
			flexLog("Error: " + e);
			throw new RuntimeException("Socket Communication Error");
		}

		// Receive Data
		try {
			newmessage = mc.receiveMessage();

			if (newmessage.getFormatName().equals("ESO001001")) {

				cdList = new JBObjList();
				boolean firstTime = true;
				String marker = "";

				while (true)
				{
					msgCD = (ESO001001Message) newmessage;
					marker = msgCD.getE01FINDAT();

					if (marker.equals("*"))
					{
						cdList.setShowNext(false);
						break;
					}
					else
					{
						cdList.addRow(msgCD);
						if (firstTime)
						{
							firstTime = false;
						}
						if (marker.equals("+"))
						{
							cdList.setShowNext(true);
							break;
						}
					}
					newmessage = mc.receiveMessage();
				}

				flexLog("Putting java beans into the session");
				ses.setAttribute("error", msgError);
				ses.setAttribute("cdList", cdList);
				ses.setAttribute("userPO", userPO);

				try {
					flexLog("About to call Page: " + LangPath + "ESO0010_cd_precancel_list.jsp");
					callPage(LangPath + "ESO0010_cd_precancel_list.jsp", req, res);
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

	protected void procActionList(
		MessageContext mc,
		ESS0030DSMessage user,
		HttpServletRequest req,
		HttpServletResponse res,
		HttpSession ses)
		throws ServletException, IOException {
	
		MessageRecord newmessage = null;
		ELEERRMessage msgError = null;
		UserPos userPO = null;
		ESS0030DSMessage msgUser = null;
	
		userPO = (datapro.eibs.beans.UserPos) ses.getAttribute("userPO");
	
		try {
			int option = Integer.parseInt(req.getParameter("opt"));
	
			switch (option) {
				case 1 : // Complete
					procReqComplete(mc, user, req, res, ses);
					break;
				case 2 : // Inquiry
					procReqInquiry(mc, user, req, res, ses);
					break;
				case 4 : // Delete
					procActionDelete(mc, user, req, res, ses);
					break;
			}
	
		} catch (Exception e) {
			e.printStackTrace();
			flexLog("Error: " + e);
			throw new RuntimeException("Socket Communication Error");
		}
	
	}

	/**
	 * This method was created in VisualAge.
	 */
	protected void procReqComplete(
		MessageContext mc,
		ESS0030DSMessage user,
		HttpServletRequest req,
		HttpServletResponse res,
		HttpSession ses)
		throws ServletException, IOException {
	
		ELEERRMessage msgError = null;
		ESO001001Message msgCD = null;
		UserPos userPO = null;
		JBObjList cdList = null;
		String acc = "";

		userPO = (datapro.eibs.beans.UserPos) ses.getAttribute("userPO");
		cdList = (JBObjList) ses.getAttribute("cdList");
		
		int row = -1;
		try {
			row = Integer.parseInt(req.getParameter("ROW"));
		} catch (Exception e) {
		}
		cdList.setCurrentRow(row);		
		
		msgCD = (ESO001001Message) cdList.getRecord();
		
		acc = msgCD.getE01SOLACC();

		try {
			res.sendRedirect(super.srctx + "/servlet/datapro.eibs.products.JSEXEDL0130?SCREEN=800&E07DEAACC=" + acc);
		} catch (Exception e) {
			flexLog("Exception calling page " + e);
		}
	
	}

	protected void procReqInquiry(
		MessageContext mc,
		ESS0030DSMessage user,
		HttpServletRequest req,
		HttpServletResponse res,
		HttpSession ses)
		throws ServletException, IOException {
	
		MessageRecord newmessage = null;
		ESO001001Message msgCD = null;
		ELEERRMessage msgError = null;
		UserPos userPO = null;
		JBObjList cdList = null;
		boolean IsNotError = false;
		int option;

		userPO = (datapro.eibs.beans.UserPos) ses.getAttribute("userPO");
		userPO.setPurpose("MAINTENANCE");
		cdList = (JBObjList) ses.getAttribute("cdList");
	
		int row = -1;
		try {
			row = Integer.parseInt(req.getParameter("ROW"));
		} catch (Exception e) {
			row = -1;
		}
	
		if (row != -1)
			cdList.setCurrentRow(row);

		// Send Initial data
		try {
			flexLog("Send Initial Data");
			if (row != -1)
				msgCD = (ESO001001Message) cdList.getRecord();
			else
				msgCD = (ESO001001Message) mc.getMessageRecord("ESO001001");
			msgCD.setH01USERID(user.getH01USR());
			msgCD.setH01PROGRM("ESO0010");
			msgCD.setH01TIMSYS(getTimeStamp());
			msgCD.setH01SCRCOD("01");
			msgCD.setH01OPECOD("0002");
		
			if (row == -1)
				msgCD.setE01SELACC(req.getParameter("E01SELACC"));
		
			mc.sendMessage(msgCD);
			msgCD.destroy();
		
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
				showERROR(msgError);
			} else
				flexLog("Message " + newmessage.getFormatName() + " received.");

		} catch (Exception e) {
			e.printStackTrace();
			flexLog("Error: " + e);
			throw new RuntimeException("Socket Communication Error");
		}

		// Receive Data
		try {
			newmessage = mc.receiveMessage();

			if (newmessage.getFormatName().equals("ESO001001")) {
				msgCD = (ESO001001Message) newmessage;

				ses.setAttribute("error", msgError);
				ses.setAttribute("msgCD", msgCD);
				ses.setAttribute("userPO", userPO);

				if (IsNotError) { // There are no errors
					try {
						flexLog("About to call Page: " + LangPath + "ESO0010_cd_precancel_inq.jsp");
						callPage(LangPath + "ESO0010_cd_precancel_inq.jsp", req, res);
					} catch (Exception e) {
						flexLog("Exception calling page " + e);
					}
				} else { // There are errors
					flexLog("Putting java beans into the session");
					try {
						flexLog("About to call Page: " + LangPath + "ESO0010_cd_precancel_enter.jsp");
						callPage(LangPath + "ESO0010_cd_precancel_enter.jsp", req, res);
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
		ESO001001Message msgCD = null;
		ELEERRMessage msgError = null;
		JBObjList cdList = null;
		UserPos userPO = null;
		boolean IsNotError = false;
		int option;
	
		userPO = (datapro.eibs.beans.UserPos) ses.getAttribute("userPO");
		cdList = (JBObjList) ses.getAttribute("cdList");
		
		int row = -1;
		try {
			row = Integer.parseInt(req.getParameter("ROW"));
		} catch (Exception e) {
		}
		cdList.setCurrentRow(row);		
	
		// Send Initial data
		try {
			flexLog("Send Initial Data");
			msgCD = (ESO001001Message) cdList.getRecord();
			msgCD.setH01USERID(user.getH01USR());
			msgCD.setH01PROGRM("ESO0010");
			msgCD.setH01TIMSYS(getTimeStamp());
			msgCD.setH01SCRCOD("01");
			msgCD.setH01OPECOD("0004");
			
			mc.sendMessage(msgCD);
			//msgCD.destroy();
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
				showERROR(msgError);
			} else
				flexLog("Message " + newmessage.getFormatName() + " received.");
	
		} catch (Exception e) {
			e.printStackTrace();
			flexLog("Error: " + e);
			throw new RuntimeException("Socket Communication Error");
		}
	
		// Receive Data
		try {
			newmessage = mc.receiveMessage();
	
			if (newmessage.getFormatName().equals("ESO001001")) {
				msgCD = (ESO001001Message) newmessage;
	
				if (IsNotError) { // There are no errors
					try {
						res.sendRedirect(super.srctx + "/servlet/datapro.eibs.products.JSESO0010?SCREEN=100");
					} catch (Exception e) {
						flexLog("Exception calling page " + e);
					}
				} else { // There are errors
					flexLog("Putting java beans into the session");
					ses.setAttribute("error", msgError);
					ses.setAttribute("userPO", userPO);
					try {
						flexLog("About to call Page: " + LangPath + "ESO0010_cd_precancel_list.jsp");
						callPage(LangPath + "ESO0010_cd_precancel_list.jsp", req, res);
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

    
}
