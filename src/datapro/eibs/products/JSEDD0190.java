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

import java.util.Calendar;
import java.util.*;

public class JSEDD0190 extends datapro.eibs.master.SuperServlet {

	
	// options
	protected static final int R_SUSP 			= 1;
	protected static final int A_SUSP 			= 2;
	protected static final int R_SUSP_DET 		= 3;
	protected static final int A_SUSP_DET 		= 4;
	protected static final int R_SUSP_ACLARAR 	= 5;
	protected static final int A_SUSP_ACLARAR 	= 6;
	protected static final int R_OF_SUSP 			= 7;
	protected static final int A_OF_SUSP 			= 8;
	protected static final int A_OF_SUSP_DET 		= 9;
	protected static final int A_OF_SUSP_ACLARAR 	= 10;
	protected static final int A_CD_SUSP_DET 		= 11;
	
	// entering options
	protected static final int R_ENTER 			= 100;
	protected static final int A_ENTER 			= 200;
	protected static final int R_OF_ENTER 		= 300;
	protected static final int A_OF_ENTER 		= 400;
	protected static final int R_CD_ENTER 		= 500;
	protected static final int A_CD_ENTER 		= 600;
	protected static final int R_CD_SUSP_INQ 		= 700;
	
	protected String LangPath = "S";

	/**
	 * JSEDD0190 constructor comment.
	 */
	public JSEDD0190() {
		super();
	}
	/**
	 * This method was created by Orestes Garcia.
	 */
	public void destroy() {

		flexLog("free resources used by JSEDD0190");

	}
	/**
	 * This method was created by Orestes Garcia.
	 */
	public void init(ServletConfig config) throws ServletException {
		super.init(config);
	}
	/**
	 * This method was created in VisualAge.
	 * by David Mavilla.
	 * on 5/17/00.
	 */
	protected void  procActionEnterStopPay(
		MessageContext mc,
		ESS0030DSMessage user,
		HttpServletRequest req,
		HttpServletResponse res,
		HttpSession ses)
		throws ServletException, IOException {

		MessageRecord newmessage = null;
		EDD019001Message msgRT = null;
		ELEERRMessage msgError = null;
		UserPos userPO = null;
		boolean IsNotError = false;
		
		
		try {

			userPO = (datapro.eibs.beans.UserPos) ses.getAttribute("userPO");

			userPO.setIdentifier(req.getParameter("E01DHSACC"));

			flexLog("Putting java beans into the session");
			ses.setAttribute("userPO", userPO);

			flexLog("Calling Request");
			procReqStopPayList(mc, user, req, res, ses);

		} catch (Exception e) {
			e.printStackTrace();
			flexLog("Error: " + e);
			throw new RuntimeException("Socket Communication Error");
		}

	}
	/**
	 * This method was created in VisualAge.
	 */
	protected void  procActionStopPayDet(
		MessageContext mc,
		ESS0030DSMessage user,
		HttpServletRequest req,
		HttpServletResponse res,
		HttpSession ses)
		throws ServletException, IOException {

		MessageRecord newmessage = null;
		EDD019001Message msgRT = null;
		ELEERRMessage msgError = null;
		UserPos userPO = null;
		boolean IsNotError = false;
		int option;

		
		userPO = (datapro.eibs.beans.UserPos) ses.getAttribute("userPO");

		// Send Initial data
		try {
			option = Integer.parseInt(req.getParameter("opt"));

			flexLog("Send Initial Data");
			msgRT = (EDD019001Message) mc.getMessageRecord("EDD019001");
			msgRT.setH01USERID(user.getH01USR());
//			msgRT.setH01USERID("DESHMPR");
			msgRT.setH01PROGRM("EDD0190");
			msgRT.setH01TIMSYS(getTimeStamp());
			msgRT.setH01SCRCOD("01");

			msgRT.setDHSACC(userPO.getIdentifier());
			msgRT.setDHSCCY(userPO.getCurrency());
			msgRT.setDHSCUN(userPO.getHeader1());
			msgRT.setCUSNA1(userPO.getHeader5());
			msgRT.setACMPRO(userPO.getHeader6());
			msgRT.setDHSBNK(userPO.getHeader10());
			msgRT.setDHSBRN(userPO.getHeader11());
			
			if (option == 1)
				msgRT.setH01OPECOD("0020");
			else
				msgRT.setH01OPECOD("0002");

			// all the fields here
			java.util.Enumeration enu = msgRT.fieldEnumeration();
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

		//msgRT.send();
			try {
				msgRT.setDHSTDS(req.getParameter("RUTGIR1"));
				} catch (Exception e) {
			    }
			try {
				msgRT.setNOMEJE(req.getParameter("NOMGIR1"));
				} catch (Exception e) {
				}
				
			mc.sendMessage(msgRT);
			msgRT.destroy();
			
		

		// Receive Error Message
		
			newmessage = mc.receiveMessage();

			if (newmessage.getFormatName().equals("ELEERR")) {
				msgError = (ELEERRMessage) newmessage;
				IsNotError = msgError.getERRNUM().equals("0");
				flexLog("IsNotError = " + IsNotError);
				showERROR(msgError);
			} else
				flexLog("Message " + newmessage.getFormatName() + " received.");

		

		// Receive Data
		
			newmessage = mc.receiveMessage();

			if (newmessage.getFormatName().equals("EDD019001")) {
				
				msgRT = (EDD019001Message) newmessage;
				// showESD008004(msgRT);

				if (IsNotError) { // There are no errors

						
					res.setContentType("text/html");
					PrintWriter out = res.getWriter();
					
					out.println("<HTML>");
					out.println("<HEAD>");
					out.println("<TITLE>Close</TITLE>");
					out.println("</HEAD>");
					out.println("<BODY>");
					out.println("<SCRIPT LANGUAGE=\"JavaScript\">");
					out.println(
						"		top.opener.window.location.href='"
							+ super.webAppPath
							+ "/servlet/datapro.eibs.products.JSEDD0190?SCREEN=1'");
					out.println("		top.close();");
					out.println("</SCRIPT>");
					out.println("<P>Close it!!!</P>");
					out.println("</BODY>");
					out.println("</HTML>");
					out.close();

					try {
						msgRT.setDHSTDS(req.getParameter("RUTGIR1"));
						} catch (Exception e) {
						}
						try {
							msgRT.setNOMEJE(req.getParameter("NOMGIR1"));
						} catch (Exception e) {
					}
						
					int row = 1;
					int sel = 1;
					ses.setAttribute("rtStop", msgRT);	
					ses.setAttribute("userPO", userPO);
				
//					res.sendRedirect(super.srctx +LangPath + "EDD0190_ptt_stop_pay_print.jsp?SEL=" + row);
				
				} else { // There are errors
					flexLog("Putting java beans into the session");
					ses.setAttribute("error", msgError);
					ses.setAttribute("rtStop", msgRT);
					ses.setAttribute("userPO", userPO);
					if (option == 1) {
						try {
							flexLog("About to call Page: " + LangPath + "EDD0190_ptt_stop_pay_det_new.jsp");
							callPage(LangPath + "EDD0190_ptt_stop_pay_det_new.jsp", req, res);
						} catch (Exception e) {
							flexLog("Exception calling page " + e);
						}
					} else {
						try {
							flexLog("About to call Page: " + LangPath + "EDD0190_ptt_stop_pay_det.jsp");
							callPage(LangPath + "EDD0190_ptt_stop_pay_det.jsp", req, res);
						} catch (Exception e) {
							flexLog("Exception calling page " + e);
						}
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
	/**
	 * This method was created in VisualAge.
	 */
	protected void  procActionStopPayList(
		MessageContext mc,
		ESS0030DSMessage user,
		HttpServletRequest req,
		HttpServletResponse res,
		HttpSession ses)
		throws ServletException, IOException {
			
		EDD019001Message msgRT = null;
		MessageRecord newmessage = null;
		ELEERRMessage msgError = null;
		UserPos userPO = null;
		ESS0030DSMessage msgUser = null;
		JBListRec stpList = null;

		stpList = (JBListRec) ses.getAttribute("stop");
		userPO = (datapro.eibs.beans.UserPos) ses.getAttribute("userPO");
		userPO.setHeader20("");
		userPO.setHeader21("");
		try {
			int option = Integer.parseInt(req.getParameter("opt"));
			int row = -1;
			try {
				row = Integer.parseInt(req.getParameter("ROW"));
			} catch (Exception e) {
			}

			switch (option) {
				case 1 : // New
					int seq = 0;
					seq = 2;
					msgRT = (EDD019001Message) mc.getMessageRecord("EDD019001");
					userPO.setHeader19(seq + "");
					userPO.setHeader20("DO_NEW");
					userPO.setHeader21(
						super.webAppPath + LangPath + "EDD0190_ptt_stop_pay_det_new.jsp?seq=" + seq);
					ses.setAttribute("userPO", userPO);
					ses.setAttribute("rtStop", msgRT);
					res.sendRedirect(super.srctx +LangPath + "EDD0190_ptt_stop_pay.jsp?SEL=" + row);
					break;
				case 2 : // Maintenance
					userPO.setHeader20("DO_MAINT");
					userPO.setHeader21(
						super.webAppPath + LangPath + "EDD0190_ptt_stop_pay_det.jsp?ROW=" + row);
					ses.setAttribute("userPO", userPO);
					res.sendRedirect(super.srctx +LangPath + "EDD0190_ptt_stop_pay.jsp?SEL=" + row);
					break;
				case 3 : // Delete
					boolean IsNotError = true;
					try {
						msgRT = (EDD019001Message) mc.getMessageRecord("EDD019001");
						msgRT.setH01USERID(user.getH01USR());
						msgRT.setH01PROGRM("EDD0190");
						msgRT.setH01TIMSYS(getTimeStamp());
						msgRT.setH01SCRCOD("01");
						msgRT.setH01OPECOD("0009");
						try {
							msgRT.setDHSACC(userPO.getIdentifier());
						} catch (Exception e) {
						}
						try {
							msgRT.setDHSCCY(userPO.getCurrency());
						} catch (Exception e) {
						}
						try {
							msgRT.setDHSBNK(userPO.getHeader10());
						} catch (Exception e) {
						}
						try {
							msgRT.setDHSBRN(userPO.getHeader11());
						} catch (Exception e) {
						}

						msgRT.send();
						msgRT.destroy();
					} catch (Exception e) {
						e.printStackTrace();
						flexLog("Error: " + e);
						throw new RuntimeException("Socket Communication Error");
					}
					// Receive Confirmation
					try {
						newmessage = mc.receiveMessage();
						if (newmessage.getFormatName().equals("ELEERR")) {
							msgError = (ELEERRMessage) newmessage;
							IsNotError = msgError.getERRNUM().equals("0");
							flexLog("IsNotError = " + IsNotError);

							if (IsNotError) { // There are no errors
								procReqStopPayList(mc, user, req, res, ses);
							} else {
								try {
									flexLog("Putting java beans into the session");
									ses.setAttribute("error", msgError);
									res.sendRedirect(super.srctx +LangPath + "EDD0190_ptt_stop_pay.jsp?ROW=" + row);
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
					break;
				case 5 : // Clear
				case 9 :
					stpList.setCurrentRow(row);
					msgRT = (EDD019001Message) mc.getMessageRecord("EDD019001");
					try {
						msgRT.setDHSCHK(stpList.getRecord(1));
					} catch (Exception e) {
					}
					try {
						msgRT.setDHSAMT(stpList.getRecord(7));
					} catch (Exception e) {
					}
					try {
						msgRT.setDHSOFC(stpList.getRecord(2));
					} catch (Exception e) {
					}
					try {
						msgRT.setDHSORG(stpList.getRecord(9));
					} catch (Exception e) {
					}
					try {
						msgRT.setDHSOBR(stpList.getRecord(0));
					} catch (Exception e) {
					}
					try {
						msgRT.setDHSRES(stpList.getRecord(10));
					} catch (Exception e) {
					}
					ses.setAttribute("rtStop", msgRT);	
					ses.setAttribute("stpList",stpList);
					
					if (option==9) userPO.setHeader20("DO_DELIVERY"); else userPO.setHeader20("DO_CLEAR");					
					userPO.setHeader21(super.webAppPath + LangPath + "EDD0190_ptt_stop_pay_clear.jsp?ROW=" + row);
					ses.setAttribute("userPO", userPO);
					res.sendRedirect(super.srctx +LangPath + "EDD0190_ptt_stop_pay.jsp?SEL=" + row);
					break;
					
				case 6 : // Print
				
					stpList.setCurrentRow(row);
					msgRT = (EDD019001Message) mc.getMessageRecord("EDD019001");
					try {
						msgRT.setDHSCHK(stpList.getRecord(1));
					} catch (Exception e) {
					}
					try {
						msgRT.setDHSAMT(stpList.getRecord(7));
					} catch (Exception e) {
					}
					try {
						msgRT.setDHSOFC(stpList.getRecord(2));
					} catch (Exception e) {
					}
					try {
						msgRT.setDHSORG(stpList.getRecord(9));
					} catch (Exception e) {
					}
					try {
						msgRT.setDHSOBR(stpList.getRecord(0));
					} catch (Exception e) {
					}
					try {
						msgRT.setDHSRES(stpList.getRecord(10));
					} catch (Exception e) {
					}
					ses.setAttribute("rtStop", msgRT);	
					ses.setAttribute("stpList",stpList);
					
					userPO.setHeader20("DO_PRINT");
					userPO.setHeader21(
						super.webAppPath + LangPath + "EDD0190_ptt_stop_pay_print_data.jsp?ROW=" + row);
					ses.setAttribute("userPO", userPO);
					res.sendRedirect(super.srctx +LangPath + "EDD0190_ptt_stop_pay.jsp?SEL=" + row);
					break;
				case 7 : // Print data
					IsNotError = true;
					stpList.setCurrentRow(row);
					try {
						msgRT = (EDD019001Message) mc.getMessageRecord("EDD019001");
	  					msgRT.setH01USERID(user.getH01USR());
//	  					msgRT.setH01USERID("DESHMPR");
						msgRT.setH01PROGRM("EDD0190");
	  					msgRT.setH01TIMSYS(getTimeStamp());
						msgRT.setH01SCRCOD("01");
						msgRT.setH01OPECOD("0030");
						try {
							msgRT.setDHSACC(userPO.getIdentifier());
						} catch (Exception e) {
						}
						try {
							msgRT.setDHSCCY(userPO.getCurrency());
						} catch (Exception e) {
						}
						try {
							msgRT.setDHSBNK(userPO.getHeader10());
						} catch (Exception e) {
						}
						try {
							msgRT.setDHSBRN(stpList.getRecord(0));
						} catch (Exception e) {
						}
						try {
							msgRT.setDHSCHK(stpList.getRecord(1));
						} catch (Exception e) {
						}
						try {
							msgRT.setDHSAMT(stpList.getRecord(7));
						} catch (Exception e) {
						}
						try {
							msgRT.setDHSRES(stpList.getRecord(33));
						} catch (Exception e) {
						}
						try {
							msgRT.setDHSOBR(stpList.getRecord(0));
						} catch (Exception e) {
						}
						try {
							msgRT.setDHSORG(stpList.getRecord(9));
						} catch (Exception e) {
						}
						try {
							String rutgir11 = req.getParameter("RUTGIR11");
							String rutgir12 = req.getParameter("RUTGIR12");
							String rutgir1 = rutgir11 + "-" + rutgir12;
							msgRT.setRUTGI1(rutgir1);
							} catch (Exception e) {
						}
						try {
							msgRT.setNOGIR1(req.getParameter("NOMGIR1"));
						} catch (Exception e) {
						}
						
						try {
							String rutgir21 = req.getParameter("RUTGIR21");
							String rutgir22 = req.getParameter("RUTGIR22");
							String rutgir2 = rutgir21 + "-" + rutgir22;
							msgRT.setRUTGI2(rutgir2);
							} catch (Exception e) {
						}
						try {
							msgRT.setNOGIR2(req.getParameter("NOMGIR2"));
						} catch (Exception e) {
						}
						
						try {
							msgRT.setDHSADM(stpList.getRecord(4));
						} catch (Exception e) {}
						try {
							msgRT.setDHSADD(stpList.getRecord(3));
						} catch (Exception e) {}
	 					try {
							msgRT.setDHSADY(stpList.getRecord(5));
						} catch (Exception e) {}
																
  						
						userPO.setHeader19(stpList.getRecord(6));
					
						msgRT.send();
						msgRT.destroy();
					} catch (Exception e) {
						e.printStackTrace();
						flexLog("Error: " + e);
						throw new RuntimeException("Socket Communication Error");
					}
					// Receive Confirmation
					try {
						newmessage = mc.receiveMessage();
						if (newmessage.getFormatName().equals("ELEERR")) {
							msgError = (ELEERRMessage) newmessage;
							IsNotError = msgError.getERRNUM().equals("0");
							flexLog("IsNotError = " + IsNotError);

							if (IsNotError) { // There are no errors
								newmessage = mc.receiveMessage();
								if (newmessage.getFormatName().equals("EDD019001")) {
									
									msgRT = (EDD019001Message) newmessage;
									
									try {
										userPO.setHeader17(req.getParameter("TIPFIR"));		
									} catch (Exception e) { }
										
									String tip_firma = req.getParameter("TIPFIR");
									if (tip_firma.equals ("1")) {
										try {
											msgRT.setRUTGI2("   ");
											String rut = msgRT.getNRORUT();
											rut = rut + "-" + msgRT.getDIGRUT();  
											msgRT.setRUTGI1(rut);
										} catch (Exception e) { }
										try {
											msgRT.setNOGIR2("    ");
											msgRT.setNOGIR1(userPO.getHeader5());
										} catch (Exception e) {	}
									}	
									ses.setAttribute("rtStop", msgRT);	
									ses.setAttribute("stpList",stpList);
									ses.setAttribute("userPO", userPO);
									userPO.setHeader20("DO_PRINT");
									res.sendRedirect(super.srctx +LangPath + "EDD0190_ptt_stop_pay_print.jsp?SEL=" + row);
								}	
//								procReqStopPayList(mc, user, req, res, ses);
							} else {
								try {
									flexLog("Putting java beans into the session");
									ses.setAttribute("error", msgError);
									res.sendRedirect(super.srctx +LangPath + "EDD0190_ptt_stop_pay.jsp?ROW=" + row);
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

			
					break;
					
			case 8: // Consulta Protesto
				
					stpList.setCurrentRow(row);
					msgRT = (EDD019001Message) mc.getMessageRecord("EDD019001");
					try {
						msgRT.setDHSCHK(stpList.getRecord(1));
					} catch (Exception e) {
					}
					try {
						msgRT.setDHSAMT(stpList.getRecord(7));
					} catch (Exception e) {
					}
					try {
						msgRT.setDHSOFC(stpList.getRecord(2));
					} catch (Exception e) {
					}
					try {
						msgRT.setDHSORG(stpList.getRecord(9));
					} catch (Exception e) {
					}
					try {
						msgRT.setDHSOBR(stpList.getRecord(0));
					} catch (Exception e) {
					}
					try {
						msgRT.setDHSRES(stpList.getRecord(10));
					} catch (Exception e) {
					}
					ses.setAttribute("rtStop", msgRT);	
					ses.setAttribute("stop",stpList);					
					userPO.setHeader20("DO_INQUIRY");
					userPO.setHeader21(
						super.webAppPath + LangPath + "EDD0190_ptt_stop_pay_consulta.jsp?ROW=" + row);
					ses.setAttribute("userPO", userPO);
					res.sendRedirect(super.srctx +LangPath + "EDD0190_ptt_stop_pay.jsp?SEL=" + row);
					break;
			}

		} catch (Exception e) {
			e.printStackTrace();
			flexLog("Error: " + e);
			throw new RuntimeException("Socket Communication Error");
		}

		// Send Initial data

	}
	/**
	 * This method was created in VisualAge.
	 */
	protected void  procReqEnterStopPay(
		ESS0030DSMessage user,
		HttpServletRequest req,
		HttpServletResponse res,
		HttpSession ses)
		throws ServletException, IOException {

		ELEERRMessage msgError = null;
		UserPos userPO = null;

		try {

			msgError =new datapro.eibs.beans.ELEERRMessage();
			userPO =new datapro.eibs.beans.UserPos();
			userPO.setOption("STOP_PAYMENT");
			userPO.setPurpose("MAINTENANCE");
			ses.setAttribute("error", msgError);
			ses.setAttribute("userPO", userPO);

		} catch (Exception ex) {
			flexLog("Error: " + ex);
		}

		try {
			flexLog("About to call Page: " + LangPath + "EDD0190_ptt_enter_stop_pay.jsp");
			callPage(LangPath + "EDD0190_ptt_enter_stop_pay.jsp", req, res);
		} catch (Exception e) {
			flexLog("Exception calling page " + e);
		}

	}
	/**
	 * This method was created in VisualAge.
	 */
	protected void  procReqStopPayList(
		MessageContext mc,
		ESS0030DSMessage user,
		HttpServletRequest req,
		HttpServletResponse res,
		HttpSession ses)
		throws ServletException, IOException {

		MessageRecord newmessage = null;
		EDD019001Message msgList = null;
		JBListRec stpList = null;
		ELEERRMessage msgError = null;
		UserPos userPO = null;
		boolean IsNotError = false;
		
		userPO = (datapro.eibs.beans.UserPos) ses.getAttribute("userPO");

		
		// Send Initial data
		try {
			msgList = (EDD019001Message) mc.getMessageRecord("EDD019001");
			msgList.setH01USERID(user.getH01USR());
// 			msgList.setH01USERID("DESHMPR");
			msgList.setH01PROGRM("EDD0190");
			msgList.setH01TIMSYS(getTimeStamp());
			msgList.setH01SCRCOD("01");
			msgList.setH01OPECOD("0015");
			try {
				msgList.setDHSACC(userPO.getIdentifier().trim());
			} catch (Exception e) {
			}

			msgList.send();
			msgList.destroy();
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
		int colNum = 95;
		try {
			stpList =
				(datapro.eibs.beans.JBListRec) Beans.instantiate(
					getClass().getClassLoader(),
					"datapro.eibs.beans.JBListRec");
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
			flexLog("IsNotError = " + IsNotError);
			showERROR(msgError);
		} else
			flexLog("Message " + newmessage.getFormatName() + " received.");
	} catch (Exception e) {
		e.printStackTrace();
		flexLog("Error newmessage: " + e);
		throw new RuntimeException("Socket Communication Error");
	}

			
	// Receive Data Message
		try {
				newmessage = mc.receiveMessage();

				if ((newmessage.getFormatName().equals("EDD019001")) && (IsNotError)) {

					boolean firstTime = true;
					String marker = "";
					String myFlag = "";
					String myRow[] = new String[colNum];
					for (int i = 0; i < colNum; i++) {
						myRow[i] = "";
					}

					int ct = 0;
					while (true) {
						msgList = (EDD019001Message) newmessage;

						marker = msgList.getH01FLGMAS();
					
						if (firstTime) {
							firstTime = false;
					//		stpList.setFirstRec(Integer.parseInt(msgList.getE01STPSEQ()));
							userPO.setIdentifier(msgList.getDHSACC()); //Cuenta
							userPO.setCurrency(msgList.getDHSCCY()); // Moneda
							userPO.setHeader1(msgList.getDHSCUN()); // N° Cliente
							userPO.setHeader5(msgList.getCUSNA1()); // Nombre cliente
				        	userPO.setHeader6(msgList.getACMPRO()); // Producto
							userPO.setHeader10(msgList.getDHSBNK()); // Banco
							userPO.setHeader11(msgList.getDHSBRN()); // Sucursal
							userPO.setHeader15(msgList.getSDOCON()); // Sdo. Contable
							userPO.setHeader16(msgList.getSDODIS()); // Sdo. Disponible
							userPO.setHeader17(msgList.getMONRET()); // Sdo. Retenido
							
							userPO.setHeader20("");
							userPO.setHeader21("");
						}

						if (marker.equals("*")) {
					//		userPO.setHeader2(msgList.getE01STPSEQ());
							userPO.setHeader12(msgList.getDHSDDD()); // Fecha Contable
							userPO.setHeader13(msgList.getDHSDMM());
							userPO.setHeader14(msgList.getDHSDYY());
							
							userPO.setHeader18(msgList.getDHSOFC()); // Código del oficial
							userPO.setHeader19(msgList.getNOMEJE()); // Nombre del oficial
							stpList.setShowNext(false);
							break;
						} else {
							if (marker.equals("+")) {
								stpList.setShowNext(true);
								break;
							}
							// Quote List
					//		myRow[0] = msgList.getE01STPSEQ(); // Sequence
							myRow[0] = msgList.getDHSOBR(); // Sucursal
							myRow[1] = msgList.getDHSCHK(); // From Check
							myRow[2] = msgList.getDHSOFC(); // Oficial
							myRow[3] = msgList.getDHSDDD(); // Date 1
							myRow[4] = msgList.getDHSDMM(); // Date 2
							myRow[5] = msgList.getDHSDYY(); // Date 3
							myRow[6] = msgList.getDHSUTM(); // Time
							myRow[7] = msgList.getDHSAMT(); // Amount
							
							myRow[8] = msgList.getDHSFLG(); //Status
							
							myRow[9]  = msgList.getDHSORG(); 
							myRow[10] = msgList.getDHSRES();
							
					//Se agrego para la consulta
							myRow[20] = msgList.getDHSBNK(); 
							myRow[21] = msgList.getDHSBRN();
							myRow[22] = msgList.getDHSCCY(); 
							myRow[23] = msgList.getDHSACC();
							myRow[24] = msgList.getDHSCHK(); 
							myRow[25] = msgList.getDHSAMT();
							myRow[26] = msgList.getDHSTCD(); 
							myRow[27] = msgList.getDHSTDS();
							myRow[28] = msgList.getDHSDMM(); 
							myRow[29] = msgList.getDHSDDD();
							myRow[30] = msgList.getDHSDYY(); 
							myRow[31] = msgList.getDHSEMI();
							
							myRow[32] = msgList.getDHSTRA(); 
							myRow[33] = msgList.getDHSRES();
							myRow[34] = msgList.getDHSOFC(); 
							myRow[35] = msgList.getDHSACC();
							myRow[36] = msgList.getDHSBTH(); 
							myRow[37] = msgList.getDHSFLG();
							myRow[38] = msgList.getDHSUID(); 
							myRow[39] = msgList.getDHSUDM();
							myRow[40] = msgList.getDHSUDD(); 
							myRow[41] = msgList.getDHSUDY();
							myRow[42] = msgList.getDHSUTM(); 
							myRow[43] = msgList.getDHSORG();	

							myRow[44] = msgList.getDHSFGL(); 
							myRow[45] = msgList.getDHSDAC();
							myRow[46] = msgList.getDHSUC1(); 
							myRow[47] = msgList.getDHSOBK();
							myRow[48] = msgList.getDHSOBR(); 
							myRow[49] = msgList.getDHSDOC();
							myRow[50] = msgList.getDHSREO(); 
							myRow[51] = msgList.getDHSRO2();
							myRow[52] = msgList.getDHSRO3(); 
							myRow[53] = msgList.getDHSRO4();
							myRow[54] = msgList.getDHSAM1(); 
							myRow[55] = msgList.getDHSAM2();

							myRow[56] = msgList.getDHSAM3(); 
							myRow[57] = msgList.getDHSAM4();
							myRow[58] = msgList.getDHSBNI(); 
							myRow[59] = msgList.getDHSCUN();
							myRow[60] = msgList.getDHSEDM(); 
							myRow[61] = msgList.getDHSEDD();
							myRow[62] = msgList.getDHSEDY(); 
							myRow[63] = msgList.getDHSETM();
							myRow[64] = msgList.getDHSAST(); 
							myRow[65] = msgList.getDHSAUS();
							myRow[66] = msgList.getDHSADM(); 
							myRow[67] = msgList.getDHSADD();
							
							myRow[68] = msgList.getDHSADY(); 
							myRow[69] = msgList.getDHSATM();
							myRow[70] = msgList.getNOMEJE(); 
							myRow[71] = msgList.getACMPRO();
							myRow[72] = msgList.getDHSCHK(); 
							myRow[73] = msgList.getNRORUT();
							myRow[74] = msgList.getDIGRUT(); 
							myRow[75] = msgList.getCUSNA1();
							myRow[76] = msgList.getCUSNA2(); 
							myRow[77] = msgList.getCUSNA3();
							myRow[78] = msgList.getCUSNA4(); 
							myRow[79] = msgList.getCDVDSC();
							
							myRow[80] = msgList.getGLBNK1(); 
							myRow[81] = msgList.getGLBRN1();
							myRow[82] = msgList.getGLBANC(); 
							myRow[83] = msgList.getGLOFIC();
							myRow[84] = msgList.getGLORIP(); 
							myRow[85] = msgList.getGLMOTP();
							myRow[86] = msgList.getGLEST1(); 
							myRow[87] = msgList.getGLBNK2();
							myRow[88] = msgList.getGLBRN2(); 
							myRow[89] = msgList.getGLDOCU();
							myRow[90] = msgList.getGLMOTO(); 
							myRow[91] = msgList.getGLEST2();
							
							stpList.addRow(myFlag, myRow);

						}
						newmessage = mc.receiveMessage();
					}

				}	
		} catch (Exception e) {
			e.printStackTrace();
			flexLog("Error: " + e);
			throw new RuntimeException("Socket Communication Error");
		}



		 flexLog("Putting java beans into the session");
		 ses.setAttribute("stop", stpList);
		 ses.setAttribute("error", msgError);
		 ses.setAttribute("userPO", userPO);
		 
		 if (IsNotError) { // There are no errors
		 try {
			flexLog("About to call Page: " + LangPath + "EDD0190_ptt_stop_pay.jsp");
				callPage(LangPath + "EDD0190_ptt_stop_pay.jsp", req, res);
					
		} catch (Exception e) {
			flexLog("Exception calling page " + e); }
			
		}else {
			try {
			flexLog("About to call Page: " + LangPath + "EDD0190_ptt_enter_stop_pay.jsp");
				callPage(LangPath + "EDD0190_ptt_enter_stop_pay.jsp", req, res);
					
			} catch (Exception e) {
				flexLog("Exception calling page " + e); }
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

			int screen = R_ENTER;

			try {

				msgUser = (datapro.eibs.beans.ESS0030DSMessage) session.getAttribute("currUser");

				// Here we should get the path from the user profile
 	             	LangPath = super.rootPath + msgUser.getE01LAN() + "/";
 	             	
//	  				LangPath = "/pages/s/";
				
				try {
					flexLog("Opennig Socket Connection");
	 		     	s = new Socket(super.hostIP, super.iniSocket + 29);
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
						procReqStopPayList(mc, msgUser, req, res, session);
						break;
					case A_SUSP :
						procActionStopPayList(mc, msgUser, req, res, session);
						break;
					case A_SUSP_DET :
						procActionStopPayDet(mc, msgUser, req, res, session);
						break;
					case A_SUSP_ACLARAR :
						procActionStopPayClear(mc, msgUser, req, res, session);
						break;
					
					case R_ENTER :
						procReqEnterStopPay(msgUser, req, res, session);
						break;
					case A_ENTER :
						procActionEnterStopPay(mc, msgUser, req, res, session);
						break;
					
					default :
						res.sendRedirect(super.srctx +LangPath + super.devPage);
						break;
				}
				} catch (Exception e) {
					e.printStackTrace();
					int sck = super.iniSocket + 29;
					flexLog("Socket not Open(Port " + sck + "). Error: " + e);
					res.sendRedirect(super.srctx +LangPath + super.sckNotOpenPage);
					//return;
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
	
	protected void  showERROR(ELEERRMessage m) {
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
	protected void  procActionStopPayClear(
		MessageContext mc,
		ESS0030DSMessage user,
		HttpServletRequest req,
		HttpServletResponse res,
		HttpSession ses)
		throws ServletException, IOException {

		MessageRecord newmessage = null;
		EDD019001Message msgRT = null;
		EDD019001Message rtStop = null;
		ELEERRMessage msgError = null;
		UserPos userPO = null;
		boolean IsNotError = false;
		int option;

		
		userPO = (datapro.eibs.beans.UserPos) ses.getAttribute("userPO");
		rtStop = (EDD019001Message) ses.getAttribute("rtStop");
		
		// Send Initial data
		try {
			msgRT = (EDD019001Message) mc.getMessageRecord("EDD019001");
			msgRT.setH01USERID(user.getH01USR());
//			msgRT.setH01USERID("DESHMPR");
			msgRT.setH01PROGRM("EDD0190");
			msgRT.setH01TIMSYS(getTimeStamp());
			msgRT.setH01SCRCOD("01");
			if (userPO.getHeader20().equals("DO_CLEAR"))
				msgRT.setH01OPECOD("0025");
			else msgRT.setH01OPECOD("0026"); //Delivery
			try {
				msgRT.setDHSACC(userPO.getIdentifier());
			} catch (Exception e) {
			}
			try {
				msgRT.setDHSCCY(userPO.getCurrency());
			} catch (Exception e) {
			}
			try {
				msgRT.setDHSBNK(userPO.getHeader10());
			} catch (Exception e) {
			}
			try {
				msgRT.setDHSBRN(userPO.getHeader11());
			} catch (Exception e) {
			}
			try {
				msgRT.setDHSCHK(rtStop.getDHSCHK());
			} catch (Exception e) {
			}

			msgRT.send();
			msgRT.destroy();
		
		// Receive Error Message
		
			newmessage = mc.receiveMessage();

			if (newmessage.getFormatName().equals("ELEERR")) {
				msgError = (ELEERRMessage) newmessage;
				IsNotError = msgError.getERRNUM().equals("0");
				flexLog("IsNotError = " + IsNotError);
				showERROR(msgError);
				
		// Receive Error Message
			try {		
				newmessage = mc.receiveMessage();
			} catch (Exception e) {	}
			
				if (IsNotError) { // There are no errors
					res.setContentType("text/html");
					PrintWriter out = res.getWriter();
					out.println("<HTML>");
					out.println("<HEAD>");
					out.println("<TITLE>Close</TITLE>");
					out.println("</HEAD>");
					out.println("<BODY>");
					out.println("<SCRIPT LANGUAGE=\"JavaScript\">");
					out.println(
						"		top.opener.window.location.href='"
							+ super.webAppPath
							+ "/servlet/datapro.eibs.products.JSEDD0190?SCREEN=1'");
					out.println("		top.close();");
					out.println("</SCRIPT>");
					out.println("<P>Close it!!!</P>");
					out.println("</BODY>");
					out.println("</HTML>");
					out.close();

				} else { // There are errors
					flexLog("Putting java beans into the session");
					ses.setAttribute("error", msgError);
					try {
						flexLog("About to call Page: " + LangPath + "EDD0190_ptt_stop_pay_clear.jsp");
						callPage(LangPath + "EDD0190_ptt_stop_pay_clear.jsp", req, res);
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