package datapro.eibs.products;

/**
 * Insert the type's description here.
 * Creation date: (1/19/00 6:08:55 PM)
 * @author: Orestes Garcia
 */
import java.io.*;
import java.net.*;
import java.beans.Beans;
import javax.servlet.*;
import javax.servlet.http.*;
import datapro.eibs.beans.*;

import datapro.eibs.master.Util;

import datapro.eibs.sockets.*;

public class JSEDD1200 extends datapro.eibs.master.SuperServlet {

	// options
	static final int R_SUSP 			= 1;
	static final int A_SUSP 			= 2;
	static final int R_SUSP_DET 		= 3;
	static final int A_SUSP_DET 		= 4;
	static final int R_SUSP_ACLARAR 	= 5;
	static final int A_SUSP_ACLARAR 	= 6;
	static final int R_OF_SUSP 			= 7;
	static final int A_OF_SUSP 			= 8;
	static final int A_OF_SUSP_DET 		= 9;
	static final int A_OF_SUSP_ACLARAR 	= 10;
	static final int A_CD_SUSP_DET 		= 11;
	
	// entering options
	static final int R_ENTER 			= 100;
	static final int A_ENTER 			= 200;
	static final int R_OF_ENTER 		= 300;
	static final int A_OF_ENTER 		= 400;
	static final int R_CD_ENTER 		= 500;
	static final int A_CD_ENTER 		= 600;
	static final int R_CD_SUSP_INQ 		= 700;
	
	private String LangPath = "S";

	/**
	 * JSECLI001 constructor comment.
	 */
	public JSEDD1200() {
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

			int screen = R_ENTER;

			try {

				
				msgUser = (datapro.eibs.beans.ESS0030DSMessage) session.getAttribute("currUser");

				// Here we should get the path from the user profile
				LangPath = super.rootPath + msgUser.getE01LAN() + "/";

//				LangPath = "/pages/s/";
				
				try {
					flexLog("Opennig Socket Connection");
					s = new Socket(super.hostIP, getInitSocket(req) + 29);
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
					case R_SUSP :
						procReqDevDocumentosList(mc, msgUser, req, res, session);
						break;
					case A_SUSP :
						procActionDevDocumentosList(mc, msgUser, req, res, session);
						break;
					case R_ENTER :
						procReqEnterDevDocumentos(msgUser, req, res, session);
						break;
					case A_ENTER :
						procActionEnterDevDocumentos(mc, msgUser, req, res, session);
						break;
					default :
						res.sendRedirect(super.srctx +LangPath + super.devPage);
						break;
				}
				} catch (Exception e) {
					e.printStackTrace();
					int sck = getInitSocket(req) + 29;
					flexLog("Socket not Open(Port " + sck + "). Error: " + e);
					res.sendRedirect(super.srctx +LangPath + super.sckNotOpenPage);
//					return;
				}

				finally {
					s.close();
				}

			} catch (Exception e) {
				flexLog("Error: " + e);
				res.sendRedirect(super.srctx +LangPath + super.sckNotRespondPage);
			}
		}

	}
	
	/**
	 * This method was created in VisualAge.
	 */
	 protected void  procActionDevDocumentosList(
		MessageContext mc,
		ESS0030DSMessage user,
		HttpServletRequest req,
		HttpServletResponse res,
		HttpSession ses)
		throws ServletException, IOException {

		MessageRecord newmessage = null;
		ELEERRMessage msgError = null;
		EDD120001Message msgList = null;
		UserPos userPO = null;
		ESS0030DSMessage msgUser = null;
		JBListRec stpList = null;

		stpList = (JBListRec) ses.getAttribute("stop");
		userPO = (datapro.eibs.beans.UserPos) ses.getAttribute("userPO");

				boolean IsNotError = true;
					try {
						EDD120001Message msgRT = (EDD120001Message) mc.getMessageRecord("EDD120001");
						msgRT = (EDD120001Message) mc.getMessageRecord("EDD120001");
		 				msgRT.setH01USERID(user.getH01USR());
//						msgRT.setH01USERID("DESHMPR");
						msgRT.setH01PROGRM("EDD1200");
						msgRT.setH01TIMSYS(getTimeStamp());
						msgRT.setH01OPECOD("0005");

						try {
							msgRT.setE01ACMCUN(userPO.getHeader1().trim());
						} catch (Exception e) {
						}
						try {
							msgRT.setE01ACMACC(userPO.getIdentifier().trim());
						} catch (Exception e) {
						}
						try {
							msgRT.setE01ACMCCY(userPO.getCurrency().trim());
						} catch (Exception e) {
						}
						try {
							msgRT.setE01ACMPRO(userPO.getHeader6().trim());
						} catch (Exception e) {
						}
						
						try {
							msgRT.setE01RUNDT1(req.getParameter("E01RUNDT1"));
							msgRT.setE01RUNDT2(req.getParameter("E01RUNDT2"));
							msgRT.setE01RUNDT3(req.getParameter("E01RUNDT3"));
						} catch (Exception e) {
						}
						try {
							msgRT.setE01NRODIAS(req.getParameter("E01NRODIAS"));
						} catch (Exception e) {
						}
						try {
							msgRT.setE01TIPODO(req.getParameter("E01TIPODO"));
						} catch (Exception e) {
						}
						try {
							msgRT.setE01NRODOC(req.getParameter("E01NRODOC"));
						} catch (Exception e) {
						}
						try {
							msgRT.setE01MONTO(req.getParameter("E01MONTO"));
						} catch (Exception e) {
						}
						try {
							String CmoBrm = req.getParameter("E01NROBCO");
							if (CmoBrm == ""){
								msgRT.setE01NROBCO(req.getParameter("E01NROBCO"));
							}else{	
								String str1 = "";
								for (int i = 1; i <= (4 - CmoBrm.length()) ; i++  ){str1 = str1 + "0";}
								CmoBrm = str1 + CmoBrm;
								msgRT.setE01NROBCO(CmoBrm);
							}	
						} catch (Exception e) {
						}
						try {
							msgRT.setE01MOTIVO(req.getParameter("E01MOTIVO"));
						} catch (Exception e) {
						}
						try {
							msgRT.setE01CREOBR(req.getParameter("E01CREOBR"));
						} catch (Exception e) {
						}

						msgRT.send();
						msgRT.destroy();
						
					} catch (Exception e) {
						e.printStackTrace();
						flexLog("Error: " + e);
						throw new RuntimeException("Socket Communication Error");
					}
	
		flexLog("Initializing java beans into the session");
		try {
			msgError =
				(datapro.eibs.beans.ELEERRMessage) Beans.instantiate(
					getClass().getClassLoader(),
					"datapro.eibs.beans.ELEERRMessage");
		} catch (Exception ex) {
			flexLog("Error: " + ex);
		}
		int colNum = 19;
		try {
			stpList = new datapro.eibs.beans.JBListRec();
			stpList.init(colNum);
		} catch (Exception ex) {
			flexLog("Error: " + ex);
		}
	
	// Receive Error Message
		try {
			newmessage = mc.receiveMessage();

			if (newmessage.getFormatName().equals("ELEERR")) {
				msgError = (ELEERRMessage) newmessage;
				
				IsNotError = msgError.getERRNUM().equals("0");
				flexLog("Putting java beans into the session");
				ses.setAttribute("error", msgError);

			} 
		} catch (Exception e) {
			e.printStackTrace();
			flexLog("Error: " + e);
			throw new RuntimeException("Socket Communication Error");
		}

		
	    try {
		   	newmessage = mc.receiveMessage();

	// Receive Data Message
				if (newmessage.getFormatName().equals("EDD120001")) {

					boolean firstTime = true;
					String marker = "";
					String myFlag = "";
					String myRow[] = new String[colNum];
					for (int i = 0; i < colNum; i++) {
						myRow[i] = "";
					}

					msgList = (EDD120001Message) newmessage;

					marker = msgList.getH01FLGMAS();

		//			stpList.setFirstRec(Integer.parseInt(msgList.getE01STPSEQ()));
					userPO.setIdentifier(msgList.getE01ACMACC());
					userPO.setCurrency(msgList.getE01ACMCCY());
					userPO.setHeader1(msgList.getE01ACMCUN());
					userPO.setHeader5(msgList.getE01CUSNA1());
					userPO.setHeader6(msgList.getE01ACMPRO());
					
					userPO.setHeader10(msgList.getE01ACMOFC());
					userPO.setHeader11(msgList.getE01DSCOFC());
					
					userPO.setHeader12(msgList.getE01GRSBAL());  //Saldo Contable
					userPO.setHeader13(msgList.getE01NETBAL());  //Saldo Disponible
					userPO.setHeader14(msgList.getE01UNCBAL());  //Retención
					
					userPO.setHeader20("");
					userPO.setHeader21("");
					

					flexLog("Putting java beans into the session");
					ses.setAttribute("stop", stpList);
					ses.setAttribute("error", msgError);
					ses.setAttribute("userPO", userPO);
					ses.setAttribute("msgList", msgList);

					try {
					if (IsNotError) {  // There are no errors
						flexLog("About to call Page: " + LangPath + "EDD1200_ptt_enter_dev_documentos.jsp");
						callPage(LangPath + "EDD1200_ptt_enter_dev_documentos.jsp", req, res);
					}else {
						flexLog("About to call Page: " + LangPath + "EDD1200_ptt_dev_documentos.jsp");
						callPage(LangPath + "EDD1200_ptt_dev_documentos.jsp", req, res);
					}
				
						
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

		// Send Initial data

	}

	/**
	 * This method was created in VisualAge.
	 * by David Mavilla.
	 * on 5/17/00.
	 */
	 
	 
	protected void  procActionEnterDevDocumentos(
		MessageContext mc,
		ESS0030DSMessage user,
		HttpServletRequest req,
		HttpServletResponse res,
		HttpSession ses)
		throws ServletException, IOException {

		MessageRecord newmessage = null;
		EDD018001Message msgRT = null;
		ELEERRMessage msgError = null;
		UserPos userPO = null;
		boolean IsNotError = false;

		
		try {

			userPO = (datapro.eibs.beans.UserPos) ses.getAttribute("userPO");

			userPO.setIdentifier(req.getParameter("E01ACMACC"));

			flexLog("Putting java beans into the session");
			ses.setAttribute("userPO", userPO);

			flexLog("Calling Request");
			procReqDevDocumentosList(mc, user, req, res, ses);

		} catch (Exception e) {
			e.printStackTrace();
			flexLog("Error: " + e);
			throw new RuntimeException("Socket Communication Error");
		}

	}

	/**
	 * This method was created in VisualAge.
	 */
	 
	protected void  procReqDevDocumentosList(
		MessageContext mc,
		ESS0030DSMessage user,
		HttpServletRequest req,
		HttpServletResponse res,
		HttpSession ses)
		throws ServletException, IOException {

		MessageRecord newmessage = null;
		EDD120001Message msgList = null;
		ELEERRMessage msgError = null;
		UserPos userPO = null;
		boolean IsNotError = false;

		userPO = (datapro.eibs.beans.UserPos) ses.getAttribute("userPO");

		// Send Initial data
		try {
			msgList = (EDD120001Message) mc.getMessageRecord("EDD120001");
 			msgList.setH01USERID(user.getH01USR());
//			msgList.setH01USERID("DESHMPR");
			msgList.setH01PROGRM("EDD1200");
			msgList.setH01TIMSYS(getTimeStamp());
			msgList.setH01OPECOD("0002");
			try {
				msgList.setE01ACMACC(userPO.getIdentifier());
			} catch (Exception e) {
			}

			msgList.send();
			msgList.destroy();
		} catch (Exception e) {
			e.printStackTrace();
			flexLog("Error: " + e);
			throw new RuntimeException("Socket Communication Error");
		}

		
		int colNum = 19;
	
		// Receive Error Message
		try {
			newmessage = mc.receiveMessage();

			if (newmessage.getFormatName().equals("ELEERR")) {
				msgError = (ELEERRMessage) newmessage;
				
				IsNotError = msgError.getERRNUM().equals("0");
				flexLog("Putting java beans into the session");
				ses.setAttribute("error", msgError);

			} 
		} catch (Exception e) {
			e.printStackTrace();
			flexLog("Error: " + e);
			throw new RuntimeException("Socket Communication Error");
		}

		
	    try {
		   	newmessage = mc.receiveMessage();

	// Receive Data Message
				if (newmessage.getFormatName().equals("EDD120001")) {

					boolean firstTime = true;
					String marker = "";
					String myFlag = "";
					String myRow[] = new String[colNum];
					for (int i = 0; i < colNum; i++) {
						myRow[i] = "";
					}

					msgList = (EDD120001Message) newmessage;

					marker = msgList.getH01FLGMAS();

					userPO.setIdentifier(msgList.getE01ACMACC());
					userPO.setCurrency(msgList.getE01ACMCCY());
					userPO.setHeader1(msgList.getE01ACMCUN());
					userPO.setHeader5(msgList.getE01CUSNA1());
					userPO.setHeader6(msgList.getE01ACMPRO());
					
					userPO.setHeader10(msgList.getE01ACMOFC());
					userPO.setHeader11(msgList.getE01DSCOFC());
					
					userPO.setHeader12(msgList.getE01GRSBAL());  //Saldo Contable
					userPO.setHeader13(msgList.getE01NETBAL());  //Saldo Disponible
					userPO.setHeader14(msgList.getE01UNCBAL());  //Retención
					
					userPO.setHeader20("");
					userPO.setHeader21("");
					msgList.setE01NRODIAS("1");
				
					flexLog("Putting java beans into the session");
					ses.setAttribute("error", msgError);
					ses.setAttribute("userPO", userPO);
					ses.setAttribute("msgList", msgList);

					try {
					if (IsNotError) {  // There are no errors
						flexLog("About to call Page: " + LangPath + "EDD1200_ptt_dev_documentos.jsp");
						callPage(LangPath + "EDD1200_ptt_dev_documentos.jsp", req, res);
					}else {
						flexLog("About to call Page: " + LangPath + "EDD1200_ptt_enter_dev_documentos.jsp");
						callPage(LangPath + "EDD1200_ptt_enter_dev_documentos.jsp", req, res);
					}
				
						
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

	/**
	 * This method was created in VisualAge.
	 */
	 
	protected void  procReqEnterDevDocumentos(
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
			userPO.setOption("DEV_DOCUMENTOS");
			userPO.setPurpose("MAINTENANCE");
			ses.setAttribute("error", msgError);
			ses.setAttribute("userPO", userPO);

		} catch (Exception ex) {
			flexLog("Error: " + ex);
		}

		try {
			flexLog("About to call Page: " + LangPath + "EDD1200_ptt_enter_dev_documentos.jsp");
			callPage(LangPath + "EDD1200_ptt_enter_dev_documentos.jsp", req, res);
		} catch (Exception e) {
			flexLog("Exception calling page " + e);
		}

	}
}