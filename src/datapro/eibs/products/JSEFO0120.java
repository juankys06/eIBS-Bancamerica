package datapro.eibs.products;
/**
 * Front Office Operations
 * Creation date: (07/18/07)
 * @author: Carlos Castillo
 */ 
import datapro.eibs.beans.*;
import datapro.eibs.master.SuperServlet;
import datapro.eibs.sockets.MessageContext;
import datapro.eibs.sockets.MessageRecord;
import datapro.eibs.master.Util;
import java.io.*;
import java.net.Socket;
import javax.servlet.*;
import javax.servlet.http.*;

public class JSEFO0120 extends SuperServlet {
	
	protected String LangPath;
	
	public JSEFO0120() {
		LangPath = "S";
	}
	
	public void destroy() {
		flexLog("free resources used by JSEFO0120");
	}
	
	public void init(ServletConfig config) throws ServletException {
		super.init(config);
	}
	
	//
	//  Menu
	//
	public void service(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
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
					flexLog("Opennig Socket Connection");
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
					
					case 1: // Send Customer Info + Account List
						procActionInfoBasicFront(mc, msgUser, req, res, session);
						break;
						
					case 2: // Send Front Office Enter screen according with selection
					case 3: // Validate and send error or activate SUBMIT for 30 seconds
					case 4: // Validate, Generates de Transfer, and Shows Confirmation	
						procActionEnterFront(mc, msgUser, req, res, session);
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
	
	// SCREEN = 1
	// Send Customer Basic Information + Account List
	//
	protected void procActionInfoBasicFront(MessageContext mc, ESS0030DSMessage user, HttpServletRequest req, HttpServletResponse res, HttpSession ses)
	throws ServletException, IOException {
		
		MessageRecord newmessage = null;
		ELEERRMessage msgError = null;
		EFO012001Message msgCust = null;
		EFO012006Message msgCustA = null;
		JBList beanList = null;
		UserPos	userPO = null;
		String num = "";
		try {
			msgError = new ELEERRMessage();
		} 
		catch (Exception ex) {
			flexLog("Error: " + ex); 
		}
		
		userPO = (datapro.eibs.beans.UserPos)ses.getAttribute("userPO");
		// Send Initial data
		try {
			msgCust = (EFO012001Message)mc.getMessageRecord("EFO012001");
			msgCust.setH01USERID(user.getH01USR());
			msgCust.setH01OPECOD("0001"); // Customer Basic Info
			msgCust.setH01PROGRM("EFO0120");
			msgCust.setH01TIMSYS(SuperServlet.getTimeStamp());
			msgCust.setH01SCRCOD("01");
			try {
				msgCust.setH01SCRCOD(req.getParameter("OPTION"));
			} catch(Exception e) {
				flexLog("H01SRCCOD set to default value");
			}
			try {
				num = req.getParameter("CUSTOMER");
			}
			catch(Exception ex) {
				num = "0";
			}
			msgCust.setE01CUSCUN(num);
			msgCust.send();	
			msgCust.destroy();
			flexLog("EFO012001 Message Sent");
		}		
		catch (Exception e) {
			e.printStackTrace();
			flexLog("Error: " + e);
			res.sendRedirect(super.srctx + LangPath + super.sckNotRespondPage);
			return;
		}
		
		// Receive Message
		try {
			newmessage = mc.receiveMessage();
			
			if (newmessage.getFormatName().equals("ELEERR")) {
				
				msgError = (ELEERRMessage)newmessage;
				flexLog("Putting java beans into the session");
				ses.setAttribute("error", msgError);
				
				try {
					flexLog("About to call Page: " + LangPath + "error_viewer.jsp");
					callPage(LangPath + "error_viewer.jsp", req, res);
				}
				catch (Exception e) {
					flexLog("Exception calling page " + e);
				}
			} else if (newmessage.getFormatName().equals("EFO012001")) {
				
				msgCust= (EFO012001Message)newmessage;
				flexLog("Putting java beans into the session");
				ses.setAttribute("error", msgError);
				ses.setAttribute("msgCust", msgCust);			
			}
			
			newmessage = mc.receiveMessage();
			
			if (newmessage.getFormatName().equals("EFO012006")) {
				
				try {
					beanList = new JBList();
				} 
				catch (Exception ex) {
					flexLog("Error: " + ex); 
				}
				
				boolean firstTime = true;
				String marker = "";
				String myFlag = "";
				StringBuffer myRow;
				String dark = "Y";
				
				while (true) {
					
					msgCustA = (EFO012006Message)newmessage;
					marker = msgCustA.getE06INDOPE();
					
					if (marker.equals("*")) {
						beanList.setShowNext(false);
						break;
					} else {
						if (firstTime) { 
							firstTime = false;
							beanList.setFirstRec(Integer.parseInt(msgCustA.getE06NUMREC()));
						} 
						myRow = new StringBuffer();
						if (dark == "Y") {
							myRow.append("<TR id=\"trdark\">");
							dark = "N";
						} else {
							myRow.append("<TR id=\"trclear\">");
							dark = "Y";
						}
						myRow.append("<TD NOWRAP align=center>" + Util.formatCell(msgCustA.getE06SELCUN()) + "</TD>");
						myRow.append("<TD NOWRAP>" + Util.formatCell(msgCustA.getE06CUSNA1()) + "</TD>");
						myRow.append("<TD NOWRAP align=center>" + Util.formatCell(msgCustA.getE06OFFICR()) + "</TD>");
						myRow.append("<TD NOWRAP align=center>" + Util.formatCell(msgCustA.getE06BRNNUM()) + "</TD>");
						myRow.append("<TD NOWRAP  align=center><A HREF=\"javascript:showInqAcc('" + msgCustA.getE06ACCNUM() + "')\">" + Util.formatCell(msgCustA.getE06ACCNUM()) + "</TD>");
						myRow.append("<TD NOWRAP align=center>" + Util.formatCell(msgCustA.getE06CCYCDE()) + "</TD>");
						myRow.append("<TD NOWRAP align=center>" + Util.formatCell(msgCustA.getE06ACCTYP()) + "</TD>");
						myRow.append("<TD NOWRAP align=center>" + Util.formatCell(msgCustA.getE06PROCDE()) + "</TD>");
						myRow.append("<TD NOWRAP align=right>" + Util.fcolorCCY(msgCustA.getE06PRIAMN()) + "</TD>");
						myRow.append("<TD NOWRAP align=right>" + Util.fcolorCCY(msgCustA.getE06EQVPRI()) + "</TD>");
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
				ses.setAttribute("fieldList", beanList);
				ses.setAttribute("userPO", userPO);
				
				try {
					flexLog("About to call Page: " + LangPath + "EFO0120_front_office_enter.jsp");
					callPage(LangPath + "EFO0120_front_office_enter.jsp", req, res);
				}
				catch (Exception e) {
					flexLog("Exception calling page " + e);
				}
			}
		}
		catch (Exception e) {
			e.printStackTrace();
			flexLog("Error: " + e);
			res.sendRedirect(super.srctx + LangPath + super.sckNotRespondPage);
		}	
	}	
	
	// SCREEN = 2 or 3 or 4 or 5
	// Send Front Office Screen According with selection
	//
	protected void procActionEnterFront(MessageContext mc, ESS0030DSMessage user, HttpServletRequest req, HttpServletResponse res, HttpSession ses)
	throws ServletException, IOException {
		
		MessageRecord newmessage = null;
		ELEERRMessage msgError = null;
		EFO012002Message msgCust = null;
		UserPos	userPO = null;
		String num = "";
		String page = "";
		int screen = 2;
		int option = 1;
		try {
			msgError = new ELEERRMessage();
		} 
		catch (Exception ex) {
			flexLog("Error: " + ex); 
		}
		
		userPO = (datapro.eibs.beans.UserPos)ses.getAttribute("userPO");
		// Send Initial data
		try {
			msgCust = (EFO012002Message)mc.getMessageRecord("EFO012002");
			msgCust.setH02USERID(user.getH01USR());
			msgCust.setH02PROGRM("EFO0120");
			msgCust.setH02TIMSYS(SuperServlet.getTimeStamp());
			msgCust.setH02SCRCOD("01");
			try {
				screen = Integer.parseInt(req.getParameter("SCREEN"));
			} catch(Exception e) {
				flexLog("Screen set to default value");
			}
			try {
				msgCust.setH02SCRCOD(req.getParameter("OPTION"));
				option = Integer.parseInt(req.getParameter("OPTION"));
			} catch(Exception e) {
				flexLog("H02SRCCOD set to default value");
			}
			try {
				num = req.getParameter("CUSTOMER");
			} catch(Exception e) {
			try {
				num = req.getParameter("E02FESCUN");
			} catch(Exception ex) {
				num = "0";
			}
			}
			msgCust.setE02FESCUN(num);
			try {
				msgCust.setE02FESREM(req.getParameter("E02FESREM"));
			} catch(Exception ex) {}	
			//
			switch(screen) {
			
			case 2: // Send Front Office Enter screen according with selection
					msgCust.setH02OPECOD("0001"); // Customer Basic Info + RT Acc List + ccy list
					if (msgCust.getH02SCRCOD().equals("02")) {     	// Time Deposit
						try {
							msgCust.setE02FESTCY(req.getParameter("E02FESTCY"));
						} catch(Exception ex) {}
						try {
							msgCust.setE02FESPRO(req.getParameter("E02FESPRO"));
						} catch(Exception ex) {}
						try {
							msgCust.setE02FESROC(req.getParameter("E02FESROC"));
						} catch(Exception ex) {}
						try {
							msgCust.setE02FESROY(req.getParameter("E02FESROY"));
						} catch(Exception ex) {}
					}
					if (msgCust.getH02SCRCOD().equals("03")) {     	// Time Deposit Renewal
						try {
							msgCust.setE02FESREF(req.getParameter("E02FESREF"));
						} catch(Exception ex) {}
						try {
							msgCust.setE02FESROC(req.getParameter("E02FESROC"));
						} catch(Exception ex) {}
					}	
					break;
					
			case 3: // Validate and send error or activate SUBMIT for 30 seconds 
					msgCust.setH02OPECOD("0002"); // Rates + Acc List + ccy list
					break;
			
			case 4: // Validate, Generates Transaction
				msgCust.setH02OPECOD("0005"); 
				break;
			
			default:
				msgCust.setH02OPECOD("0001");
			break;
			}
			
			if (screen != 2) {
				msgCust.setE02FESTCY(req.getParameter("E02FESTCY"));
				msgCust.setE02FESSBT(req.getParameter("E02FESSBT"));
				msgCust.setE02FESAMN(req.getParameter("E02FESAMN"));
				msgCust.setE02FESREF(req.getParameter("E02FESREF"));
				msgCust.setE02FESDCY(req.getParameter("E02FESDCY"));
				msgCust.setE02FESCCY(req.getParameter("E02FESCCY"));
				msgCust.setE02FESDXR(req.getParameter("E02FESDXR"));
				msgCust.setE02FESCXR(req.getParameter("E02FESCXR"));
				msgCust.setE02FESDEX(req.getParameter("E02FESDEX"));
				msgCust.setE02FESDAM(req.getParameter("E02FESDAM"));
				msgCust.setE02FESEAM(req.getParameter("E02FESEAM"));
				msgCust.setE02FESDAD(req.getParameter("E02FESDAD"));
				msgCust.setE02FESCAD(req.getParameter("E02FESCAD"));
				msgCust.setE02FESDAP(req.getParameter("E02FESDAP"));
				msgCust.setE02FESCAP(req.getParameter("E02FESCAP"));
				msgCust.setE02FESPRO(req.getParameter("E02FESPRO"));
				msgCust.setE02FESROC(req.getParameter("E02FESROC"));
				msgCust.setE02FESROY(req.getParameter("E02FESROY"));
				msgCust.setE02FESREF(req.getParameter("E02FESREF"));
				try {
					msgCust.setE02FESDAC(req.getParameter("DEBITACC"));
				} catch(Exception ex) {
					msgCust.setE02FESDAC("0");
				}
				try {
					msgCust.setE02FESCAC(req.getParameter("CREDITACC"));
				} catch(Exception ex) {
					msgCust.setE02FESCAC("0");
				}
				if (msgCust.getH02SCRCOD().equals("02")) {     	// New Time Deposit
					msgCust.setE02DTP1(req.getParameter("E02DTP1"));		
				}
				if (msgCust.getH02SCRCOD().equals("05")) {     	// External Transfers
					msgCust.setE02FFEE(req.getParameter("E02FFEE"));
					msgCust.setE02COMTBL(req.getParameter("E02COMTBL"));
					msgCust.setE02BNFACC(req.getParameter("E02BNFACC"));
					msgCust.setE02BNFAD1(req.getParameter("E02BNFAD1"));
					msgCust.setE02BNFAD2(req.getParameter("E02BNFAD2"));
					msgCust.setE02BNFAD3(req.getParameter("E02BNFAD3")); 
					msgCust.setE02BNKID(req.getParameter("E02BNKID"));
					msgCust.setE02BNKAD1(req.getParameter("E02BNKAD1"));
					msgCust.setE02BNKAD2(req.getParameter("E02BNKAD2"));
					msgCust.setE02BNKAD3(req.getParameter("E02BNKAD3"));
					msgCust.setE02BNKAD4(req.getParameter("E02BNKAD4"));
					msgCust.setE02IBNKID(req.getParameter("E02IBNKID"));
					msgCust.setE02INTAD1(req.getParameter("E02INTAD1"));
					msgCust.setE02INTAD2(req.getParameter("E02INTAD2"));
					msgCust.setE02INTAD3(req.getParameter("E02INTAD3"));
					msgCust.setE02INTAD4(req.getParameter("E02INTAD4"));
					msgCust.setE02DTP1(req.getParameter("E02DTP1"));
					msgCust.setE02DTP2(req.getParameter("E02DTP2"));
					msgCust.setE02DTP3(req.getParameter("E02DTP3"));
				}
			}
			
			msgCust.send();	
			msgCust.destroy();
			flexLog("EFO012002 Message Sent");
		}		
		catch (Exception e) {
			e.printStackTrace();
			flexLog("Error: " + e);
			res.sendRedirect(super.srctx + LangPath + super.sckNotRespondPage);
			return;
		}
		
		// Receive Message
		try {
			newmessage = mc.receiveMessage();
			
			if (newmessage.getFormatName().equals("ELEERR")) {
				
				msgError = (ELEERRMessage)newmessage;
				flexLog("Putting java beans into the session");
				ses.setAttribute("error", msgError);
				newmessage = mc.receiveMessage();
			}	
			if (newmessage.getFormatName().equals("EFO012002")) {
				msgCust= (EFO012002Message)newmessage;
				flexLog("Putting java beans into the session");
				ses.setAttribute("error", msgError);
				ses.setAttribute("msgCust", msgCust);	
				// Send Aditional Info Lists
				if (screen != 4) {
					switch(option) {
					case 1:  // Foreign Exchange
						getRTAccountsObj(mc, ses);
						getCcyList(mc, ses);
						userPO.setHeader1("Foreign Exchange");
						break;
					case 2:	 // New Time Deposits
						getRTAccountsObj(mc, ses);
						getProductsList(mc, ses);
						getCDRatesList(mc, ses);
						userPO.setHeader1("Certificado de Depósito");
						break;
					case 3:  // Renew Time deposits
						getRTAccountsObj(mc, ses);
						userPO.setHeader1("Renovación de Certificado");
						break;
					case 4:  // Internal Transfers
						getRTAccountsList(mc, ses);
						userPO.setHeader1("Transferencia Interna");
						break;
					case 5:  // External Transfers
						getRTAccountsList(mc, ses);
						userPO.setHeader1("Transferencia Externa");
						break;
					}
				}
			}	
			flexLog("Putting java beans into the session");
			ses.setAttribute("userPO", userPO);
			if (screen != 4) {
				switch(option) {
				case 1:  // Foreign Exchange
					page = "EFO0120_front_office_FX.jsp";
					break;
				case 2:	 // New Time Deposits
					page = "EFO0120_front_office_TD.jsp";
					break;
				case 3:  // Renew Time deposits
					page = "EFO0120_front_office_TD_renew.jsp";
					break;
				case 4:  // Internal Transfers
					page = "EFO0120_front_office_TR.jsp";
					break;
				case 5:  // External Transfers
					page = "EFO0120_front_office_TR_External.jsp";
					break;
				}
									
			} else {
				page = "EFO0120_front_office_FX_conf.jsp";
				switch(option) {
				case 1:  // Foreign Exchange
					page = "EFO0120_front_office_FX_conf.jsp";
					break;
				case 2:	 // New Time Deposits
					page = "EFO0120_front_office_TD_conf.jsp";
					break;
				case 3:  // Renew Time deposits
					page = "EFO0120_front_office_TD_renew_conf.jsp";
					break;
				case 4:  // Internal Transfers
					page = "EFO0120_front_office_FX_conf.jsp";
					break;
				case 5:  // External Transfers
					page = "EFO0120_front_office_TR_External_conf.jsp";
					break;	
				}
			}
			// Show Page
			try {
				flexLog("About to call Page: " + LangPath + page);
				callPage(LangPath + page, req, res);
			}catch (Exception e) {
				flexLog("Exception calling page " + e);
			}
		} catch (Exception e) {
			e.printStackTrace();
			flexLog("Error: " + e);
			res.sendRedirect(super.srctx + LangPath + super.sckNotRespondPage);
		}
	}	
 

	protected void getRTAccountsObj(MessageContext mc, HttpSession ses)
	throws ServletException, IOException {	
		
		MessageRecord newmessage = null;
		EFO012006Message accList = null;
		JBObjList ObjList = null;
		newmessage = mc.receiveMessage();
		if (newmessage.getFormatName().equals("EFO012006")) {
			try {
				ObjList = new JBObjList();
			}
			catch(Exception ex) {
				System.out.println("Error: " + ex);
			}
			try {
				StringBuffer myRow = null;
				for(accList = (EFO012006Message)newmessage; !accList.getE06INDOPE().equals("*"); accList = (EFO012006Message)newmessage) {
					ObjList.addRow(accList);
					newmessage = mc.receiveMessage();
				}
				ses.setAttribute("accList", ObjList);
			}
			catch(Exception e) {
				e.printStackTrace();
				System.out.println("Error: " + e);
				throw new RuntimeException("Socket Communication Error");
			}
		}

	}

	protected void getCcyList(MessageContext mc, HttpSession ses)
	throws ServletException, IOException {	
		
		MessageRecord newmessage = null;
		EFO012004Message ccyList = null; 
		JBList beanList = null;
		String colorP = "";
		String colorS = "";
		
		newmessage = mc.receiveMessage();
		if (newmessage.getFormatName().equals("EFO012004")) {
			try {
				beanList = new JBList();
			} 
			catch (Exception ex) {
				flexLog("Error: " + ex); 
			}
			
			boolean firstTime = true;
			String marker = "";
			String myFlag = "";
			StringBuffer myRow;
			String dark = "Y";
			String readyn = "";
			
			while (true) {
				
				ccyList = (EFO012004Message)newmessage;
				marker = ccyList.getE04INDOPE();
				
				if (marker.equals("*")) {
					beanList.setShowNext(false);
					break;
				} else {
					if (firstTime) { 
						firstTime = false;
						beanList.setFirstRec(Integer.parseInt(ccyList.getE04NUMREC()));
					} 
					myRow = new StringBuffer();
					if (dark == "Y") {
						myRow.append("<TR id=\"trdark\">");
						dark = "N";
					} else {
						myRow.append("<TR id=\"trclear\">");
						dark = "Y";
					}
					if(ccyList.getE04READON().equals("Y")) {  
						readyn = "disabled"; 
					} else {
						readyn = "onClick=\"changeCcy(\'" + ccyList.getE04RATCCY() + "\',\'" + ccyList.getE04RATDSC() + "\')\"";
						if(ccyList.getE04READON().equals("C")) {
							readyn = readyn.trim() + " checked";
						}
					}
					if(ccyList.getE04RATSTP().equals("OPEN")) {  
						colorP = "";
					} else {
						colorP = " style=\"color: red\"";
					}
					if(ccyList.getE04RATSTS().equals("OPEN")) {  
						colorS = "";
					} else {
						colorS = " style=\"color: red\"";
					}
					myRow.append("<TD NOWRAP><input type=\"radio\" name=\"CURRENCY\" value=\"" + ccyList.getE04RATCCY() + "\" " + readyn + "></TD>");
					myRow.append("<TD NOWRAP align=center>" + Util.formatCell(ccyList.getE04RATCCY()) + "</TD>");
					myRow.append("<TD NOWRAP align=left>" + Util.formatCell(ccyList.getE04RATDSC()) + "</TD>");
					myRow.append("<TD NOWRAP align=right>" + Util.formatCell(ccyList.getE04RATFPR()) + "</TD>");
					myRow.append("<TD NOWRAP align=right>" + Util.formatCell(ccyList.getE04RATAMP()) + "</TD>");
					myRow.append("<TD NOWRAP align=center" + colorP + ">" + Util.formatCell(ccyList.getE04RATSTP()) + "</TD>");
					myRow.append("<TD NOWRAP align=right>" + Util.formatCell(ccyList.getE04RATFSR()) + "</TD>");
					myRow.append("<TD NOWRAP align=right>" + Util.formatCell(ccyList.getE04RATAMS()) + "</TD>");
					myRow.append("<TD NOWRAP align=center" + colorS + ">" + Util.formatCell(ccyList.getE04RATSTS()) + "</TD>");
					myRow.append("</TR>");
					beanList.addRow(myFlag, myRow.toString());
					
					if (marker.equals("+")) {
						beanList.setShowNext(true);
						break;
					}
					newmessage = mc.receiveMessage();
				}
			}
			ses.setAttribute("ccyList", beanList);
		}
	}

	protected void getRTAccountsList(MessageContext mc, HttpSession ses)
	throws ServletException, IOException {	
		
		MessageRecord newmessage = null;
		EFO012006Message msgCustA = null;
		JBList beanList = null;
		
		newmessage = mc.receiveMessage();
		
		if (newmessage.getFormatName().equals("EFO012006")) {
			
			try {
				beanList = new JBList();
			} 
			catch (Exception ex) {
				flexLog("Error: " + ex); 
			}
			
			boolean firstTime = true;
			String marker = "";
			String myFlag = "";
			StringBuffer myRow;
			String dark = "Y";
			String fromAcc = "";
			String toAcc = "";
			
			while (true) {
				
				msgCustA = (EFO012006Message)newmessage;
				marker = msgCustA.getE06INDOPE();
				
				if (marker.equals("*")) {
					beanList.setShowNext(false);
					break;
				} else {
					if (firstTime) { 
						firstTime = false;
						beanList.setFirstRec(Integer.parseInt(msgCustA.getE06NUMREC()));
					} 
					myRow = new StringBuffer();
					if (dark == "Y") {
						myRow.append("<TR id=\"trdark\">");
						dark = "N";
					} else {
						myRow.append("<TR id=\"trclear\">");
						dark = "Y";
					}
					String selectedF = "";
					if (msgCustA.getE06SELFLG().equals("F")) { 
						selectedF = " checked";
					}
					String selectedT = "";
					if (msgCustA.getE06SELFLG().equals("T")) {
						selectedT = " checked";
					}
					fromAcc = "onClick=\"fromAcc(\'" + msgCustA.getE06ACCNUM() + "\',\'" + msgCustA.getE06CCYCDE() + "\',\'" + msgCustA.getE06PRODSC() + "\')\" style=\"color: red\"";
					toAcc = "onClick=\"toAcc(\'" + msgCustA.getE06ACCNUM() + "\',\'" + msgCustA.getE06CCYCDE() + "\',\'" + msgCustA.getE06PRODSC() + "\')\"";
						
					myRow.append("<TD NOWRAP align=center><input type=\"radio\" name=\"DEBITACC\" value=\"" + msgCustA.getE06ACCNUM() + "\" " + fromAcc + selectedF + "></TD>");
					if (!msgCustA.getH06SCRCOD().equals("05")) {          // Not for External Transfers
						myRow.append("<TD NOWRAP align=center><input type=\"radio\" name=\"CREDITACC\" value=\"" + msgCustA.getE06ACCNUM() + "\" " + toAcc + selectedT + "></TD>");
					}	
					myRow.append("<TD NOWRAP>" + Util.formatCell(msgCustA.getE06PRODSC()) + "</TD>");
					myRow.append("<TD NOWRAP align=center>" + Util.formatCell(msgCustA.getE06ACCNUM()) + "</TD>");
					myRow.append("<TD NOWRAP align=center>" + Util.formatCell(msgCustA.getE06SELCUN()) + "</TD>");
					myRow.append("<TD NOWRAP>" + Util.formatCell(msgCustA.getE06CUSNA1()) + "</TD>");
					myRow.append("<TD NOWRAP align=center>" + Util.formatCell(msgCustA.getE06CCYCDE()) + "</TD>");
					myRow.append("<TD NOWRAP align=right>" + Util.fcolorCCY(msgCustA.getE06PRIAMN()) + "</TD>");
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
			ses.setAttribute("msgCustA", beanList);
			
		}
	}
	
	protected void getProductsList(MessageContext mc, HttpSession ses)
	throws ServletException, IOException {	
		
		MessageRecord newmessage = null;
		EFO012003Message prdList = null; 
		JBList beanList = null;
		
		newmessage = mc.receiveMessage();
		if (newmessage.getFormatName().equals("EFO012003")) {
			try {
				beanList = new JBList();
			} 
			catch (Exception ex) {
				flexLog("Error: " + ex); 
			}
			
			boolean firstTime = true;
			String marker = "";
			String myFlag = "";
			StringBuffer myRow;
			String dark = "Y";
			String click = "";
			String selected = "";
			
			while (true) {
				
				prdList = (EFO012003Message)newmessage;
				marker = prdList.getE03INDOPE();
				
				if (marker.equals("*")) {
					beanList.setShowNext(false);
					break;
				} else {
					if (firstTime) { 
						firstTime = false;
						beanList.setFirstRec(Integer.parseInt(prdList.getE03NUMREC()));
					} 
					
					myRow = new StringBuffer();
					if (dark == "Y") {
						myRow.append("<TR id=\"trdark\">");
						dark = "N";
					} else {
						myRow.append("<TR id=\"trclear\">");
						dark = "Y";
					}
					click = "onClick=\"changePrd(\'" + prdList.getE03APCCDE() + "\',\'" + prdList.getE03APCCCY() + "\')\"";
					if(prdList.getE03APCCDT().equals("*")) {  
						selected = "checked ";
					} else
						selected = "";
					} 					
					myRow.append("<TD NOWRAP><input type=\"radio\" name=\"PRODUCT\" value=\"" + prdList.getE03APCCDE() + "\" " + selected + click + "></TD>");
					myRow.append("<TD NOWRAP align=center>" + Util.formatCell(prdList.getE03APCCDE()) + "</TD>");
					myRow.append("<TD NOWRAP align=center>" + Util.formatCell(prdList.getE03APCCCY()) + "</TD>");
					myRow.append("<TD NOWRAP align=left>" + Util.formatCell(prdList.getE03APCDSC()) + "</TD>");
					myRow.append("</TR>");
					beanList.addRow(myFlag, myRow.toString());
					
					if (marker.equals("+")) {
						beanList.setShowNext(true);
						break;
					}
					newmessage = mc.receiveMessage();
				}
			}
			ses.setAttribute("prdList", beanList);
		}
	
	
	protected void getCDRatesList(MessageContext mc, HttpSession ses)
	throws ServletException, IOException {	
		
		MessageRecord newmessage = null;
		EFO012005Message ratList = null; 
		JBList beanList = null;
		
		newmessage = mc.receiveMessage();
		if (newmessage.getFormatName().equals("EFO012005")) {
			try {
				beanList = new JBList();
			} 
			catch (Exception ex) {
				flexLog("Error: " + ex); 
			}
			
			boolean firstTime = true;
			String marker = "";
			String myFlag = "";
			StringBuffer myRow;
			String dark = "Y";
			String click = "";
			
			while (true) {
				
				ratList = (EFO012005Message)newmessage;
				marker = ratList.getE05INDOPE();
				
				if (marker.equals("*")) {
					beanList.setShowNext(false);
					break;
				} else {
					if (firstTime) { 
						firstTime = false;
						beanList.setFirstRec(Integer.parseInt(ratList.getE05NUMREC()));
						
						myRow = new StringBuffer();
						myRow.append("<TR id=\"trdark\">");
						myRow.append("<TH ALIGN=CENTER NOWRAP></TH>");
						myRow.append("<TH ALIGN=CENTER NOWRAP>Term<BR>(Days)</TH>");
						if (Integer.parseInt(ratList.getE05CDRCOL()) > 0) {
							myRow.append("<TH ALIGN=CENTER NOWRAP>" + Util.formatCell(ratList.getE05CDRHD1()) + "</TH>");
						}	
						if (Integer.parseInt(ratList.getE05CDRCOL()) > 1) {
							myRow.append("<TH ALIGN=CENTER NOWRAP>" + Util.formatCell(ratList.getE05CDRHD2()) + "</TH>");
						}
						if (Integer.parseInt(ratList.getE05CDRCOL()) > 2) {
							myRow.append("<TH ALIGN=CENTER NOWRAP>" + Util.formatCell(ratList.getE05CDRHD3()) + "</TH>");
						}
						if (Integer.parseInt(ratList.getE05CDRCOL()) > 3) {
							myRow.append("<TH ALIGN=CENTER NOWRAP>" + Util.formatCell(ratList.getE05CDRHD4()) + "</TH>");
						}
						if (Integer.parseInt(ratList.getE05CDRCOL()) > 4) {
							myRow.append("<TH ALIGN=CENTER NOWRAP>" + Util.formatCell(ratList.getE05CDRHD5()) + "</TH>");
						}
						if (Integer.parseInt(ratList.getE05CDRCOL()) > 5) {
							myRow.append("<TH ALIGN=CENTER NOWRAP>" + Util.formatCell(ratList.getE05CDRHD6()) + "</TH>");
						}
						if (Integer.parseInt(ratList.getE05CDRCOL()) > 6) {
							myRow.append("<TH ALIGN=CENTER NOWRAP>" + Util.formatCell(ratList.getE05CDRHD7()) + "</TH>");
						}
						myRow.append("</TR>");
						beanList.addRow(myFlag, myRow.toString());
					} 
					
					myRow = new StringBuffer();
					if (dark == "Y") {
						myRow.append("<TR id=\"trdark\">");
						dark = "N";
					} else {
						myRow.append("<TR id=\"trclear\">");
						dark = "Y";
					}
					click = "onClick=\"changeDays(\'" + ratList.getE05CDRDAY().trim() + "\')\"";
					
					myRow.append("<TD NOWRAP><input type=\"radio\" name=\"DAYS\" value=\"" + ratList.getE05CDRDAY() + "\"" + click + "></TD>");
					myRow.append("<TD NOWRAP align=center>" + Util.formatCell(ratList.getE05CDRDAY()) + "</TD>");
					if (Integer.parseInt(ratList.getE05CDRCOL()) > 0) {
						myRow.append("<TD NOWRAP align=center>" + Util.formatCell(ratList.getE05CDRRA1()) + "</TD>");
					}
					if (Integer.parseInt(ratList.getE05CDRCOL()) > 1) {
						myRow.append("<TD NOWRAP align=center>" + Util.formatCell(ratList.getE05CDRRA2()) + "</TD>");
					}
					if (Integer.parseInt(ratList.getE05CDRCOL()) > 2) {
						myRow.append("<TD NOWRAP align=center>" + Util.formatCell(ratList.getE05CDRRA3()) + "</TD>");
					}
					if (Integer.parseInt(ratList.getE05CDRCOL()) > 3) {
						myRow.append("<TD NOWRAP align=center>" + Util.formatCell(ratList.getE05CDRRA4()) + "</TD>");
					}
					if (Integer.parseInt(ratList.getE05CDRCOL()) > 4) {
						myRow.append("<TD NOWRAP align=center>" + Util.formatCell(ratList.getE05CDRRA5()) + "</TD>");
					}
					if (Integer.parseInt(ratList.getE05CDRCOL()) > 5) {
						myRow.append("<TD NOWRAP align=center>" + Util.formatCell(ratList.getE05CDRRA6()) + "</TD>");
					}
					if (Integer.parseInt(ratList.getE05CDRCOL()) > 6) {
						myRow.append("<TD NOWRAP align=center>" + Util.formatCell(ratList.getE05CDRRA7()) + "</TD>");
					}
					myRow.append("</TR>");
					beanList.addRow(myFlag, myRow.toString());
					
					if (marker.equals("+")) {
						beanList.setShowNext(true);
						break;
					}
					newmessage = mc.receiveMessage();
				}
			}
			ses.setAttribute("ratList", beanList);
		}
	}

} // End 
		
	
