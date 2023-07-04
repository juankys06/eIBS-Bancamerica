 package datapro.eibs.products;

/**
 * Insert the type's description here.
 * Creation date: (2/20/07 6:53:55 PM)
 * @author: Ramses Amaro
 */
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.math.BigDecimal;
import java.net.Socket;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.datapro.exception.ServiceLocatorException;
import com.datapro.services.ServiceLocator;

import datapro.eibs.beans.DataSignRule;
import datapro.eibs.beans.ELEERRMessage;
import datapro.eibs.beans.ESS0030DSMessage;
import datapro.eibs.beans.EWD0002DSMessage;
import datapro.eibs.beans.EDD550001Message;
import datapro.eibs.beans.EDD570001Message;

import datapro.eibs.beans.JBObjList;
import datapro.eibs.beans.UserPos;
import datapro.eibs.master.JSEIBSProp;
import datapro.eibs.master.Util;
import datapro.eibs.sockets.MessageContext;
import datapro.eibs.sockets.MessageRecord;
import datapro.eibs.sockets.MessageField;

public class JSEDD0000C extends datapro.eibs.master.SuperServlet {
	
	// RETAIL ACCOUNTS
	
	protected static final int R_RT_SIGN_RULES = 1;
	protected static final int A_RT_SIGN_RULES = 2;
	protected static final int I_RT_SIGN_RULES = 3;
	protected static final int R_RT_SIGNERS = 19;
	protected static final int A_RT_SIGNERS = 20;
	
	
	protected String LangPath = "S";

	/**
	 * This method was created by Ramses Amaro.
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

			int screen = R_RT_SIGN_RULES ;

			try {

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

				switch (screen) {
					
					case R_RT_SIGN_RULES :
					    getSignCategories(msgUser, req, res, session);
						procReqSignRules(msgUser, req, res, session);
						break;					
					case A_RT_SIGN_RULES :
					
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
							
							procActSignRules(mc, msgUser, req, res, session);
							
						} catch (Exception e) {
							e.printStackTrace();
							int sck = getInitSocket(req) + 1;
							flexLog("Socket not Open(Port " + sck + "). Error: " + e);
							res.sendRedirect(super.srctx + LangPath + super.sckNotOpenPage);
							//return;
						} finally {
							s.close();
						}
						break;
					case I_RT_SIGN_RULES :
						procReqSignRules(msgUser, req, res, session);
						break;
					case R_RT_SIGNERS :
					case A_RT_SIGNERS :
					
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
							
							if (screen == R_RT_SIGNERS) procReqSignerList(mc, msgUser, req, res, session);
							else procActionSigners(mc, msgUser, req, res, session);
							
						} catch (Exception e) {
							e.printStackTrace();
							int sck = getInitSocket(req) + 1;
							flexLog("Socket not Open(Port " + sck + "). Error: " + e);
							res.sendRedirect(super.srctx + LangPath + super.sckNotOpenPage);
							//return;
						} finally {
							s.close();
						}
					    break;
						
					default :
						res.sendRedirect(super.srctx + LangPath + super.devPage);
						break;
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
	
	
	protected void getSignCategories(
		ESS0030DSMessage user,
		HttpServletRequest req,
		HttpServletResponse res,
		HttpSession ses) 
		throws ServletException, IOException {
			
		Socket s = null;
		MessageContext mc = null;
		String codeflag = "";
		
		codeflag = req.getParameter("Type");
		if ( codeflag == null) codeflag = "FI";
		
		String marker = "";
		String selNew = "";
		String selOld = "";
		String fromRec = "0";
		
		try {				
		
			s = new Socket(super.hostIP, getInitSocket(req) + 1);
			s.setSoTimeout(super.sckTimeOut);
			mc = new MessageContext(new DataInputStream(new BufferedInputStream(s.getInputStream())),
				new DataOutputStream(new BufferedOutputStream(s.getOutputStream())),"datapro.eibs.beans");
				
			MessageRecord newmessage = null;
			EWD0002DSMessage msgHelp = (EWD0002DSMessage)mc.getMessageRecord("EWD0002DS");
			msgHelp.setEWDTBL(codeflag);         	 	
			msgHelp.setEWDSHO(selOld);
			msgHelp.setEWDSHN(selNew);
			msgHelp.setEWDREC(fromRec);
			msgHelp.send();	
			msgHelp.destroy();
						
			newmessage = mc.receiveMessage();
							   
			if (newmessage.getFormatName().equals("EWD0002DS")) {
				
				JBObjList beanList = new JBObjList();
								
				while(true) {
													   
                  	 msgHelp =  (EWD0002DSMessage)newmessage;
					 marker = msgHelp.getEWDOPE();
                           
					 if ( marker.equals("*") ) break;
																			 
					 beanList.addRow(msgHelp);
															  
					 newmessage = mc.receiveMessage();
													
				}
				
				ses.setAttribute("categList", beanList);
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
	
		
	protected void procReqSignRules(
			ESS0030DSMessage user,
			HttpServletRequest req,
			HttpServletResponse res,
			HttpSession ses)
			throws ServletException, IOException {

			JBObjList beanList = new JBObjList();
			
				
			UserPos	userPO = null;
			Connection cnx = null;
			
			if (!JSEIBSProp.getImgToIFS()) {
				try {
										
					userPO = (datapro.eibs.beans.UserPos)ses.getAttribute("userPO");
					cnx = ServiceLocator.getInstance().getDBConn("image-server");
					
					Statement st = cnx.createStatement();
					ResultSet rs = st.executeQuery(
							"SELECT * FROM SCNSIGRULES WHERE ACCUID = " + userPO.getIdentifier());
					
					while (rs.next()) {
						DataSignRule dsr = new DataSignRule();
						dsr.setAccUid(rs.getString("ACCUID"));
						dsr.setSigRule(rs.getString("SIGRULE").trim());
						dsr.setAmount(rs.getString("AMOUNT"));
						dsr.setCCYCode(rs.getString("CCYCODE"));
						dsr.setDayTo(rs.getString("DAYTO"));
						dsr.setMonthTo(rs.getString("MONTHTO"));
						dsr.setYearTo(rs.getString("YEARTO"));
						dsr.setDayFrom(rs.getString("DAYFROM"));
						dsr.setMonthFrom(rs.getString("MONTHFROM"));
						dsr.setYearFrom(rs.getString("YEARFROM"));
						dsr.setStatus(rs.getString("STATUS"));
						dsr.setModApproved(rs.getString("MODAPPROVED"));
						dsr.setTypeMaint(rs.getString("TYPEMAINT"));
						dsr.setBranch((rs.getString("BRANCH") != null) ? rs.getString("BRANCH") : "");
						dsr.setDocNum((rs.getString("DOCNUM") != null) ? rs.getString("DOCNUM") : "");						
											
						beanList.addRow(dsr);
					}
					rs.close();
					rs = null;
					st.close();
					st = null;
	
					
					
				} catch (SQLException e) {
					flexLog("Error: " + e);
					res.sendRedirect(super.srctx + LangPath + super.sckNotRespondPage);
				} catch (ServiceLocatorException e) {
					flexLog("Error: " + e);
					res.sendRedirect(super.srctx + LangPath + super.sckNotRespondPage);
				} catch (Exception e) {
					flexLog("Error: " + e);
					//change to page of sql error
					res.sendRedirect(super.srctx + LangPath + super.sckNotRespondPage);
				} finally {
					try {
						cnx.close();
					} catch (SQLException e) {
						flexLog("Error: " + e);
					}
				}
	
				ses.setAttribute("rulesList", beanList);
				try {
					flexLog(
						"About to call Page: "
							+ LangPath
							+ "EDD0000_rt_sign_rules_list.jsp");
					callPage(LangPath + "EDD0000_rt_sign_rules_list.jsp", req, res);
				} catch (Exception e) {
					flexLog("Exception calling page " + e);
				}
			}

		}
		
	protected void procActSignRules(
			MessageContext mc,
			ESS0030DSMessage user,
			HttpServletRequest req,
			HttpServletResponse res,
			HttpSession ses)
			throws ServletException, IOException {

			MessageRecord newmessage = null;
			EDD570001Message msgRT = null;
			ELEERRMessage msgError = null;
			msgError = new datapro.eibs.beans.ELEERRMessage();			
			boolean IsNotError = false;

			JBObjList rulesList = (JBObjList)ses.getAttribute("rulesList");
			Connection cnx = null;
			String sql = "";
			PreparedStatement ps = null;
			String acc = "";
			String rule = "";
			String ccycode = "";
			String amount = "";
			String dayto = "";
			String monthto = "";
			String yearto = "";
			String yearfrom = "";
			String monthfrom = "";
			String dayfrom = "";
			String status = "";
			String branch = "";
			String docnum = "";
			String modapproved = "";
			String typemaint = "";
			
			int action = 0;
			int idx = 0;
			int error = 0;
			DataSignRule dsr;
			
			try {
				action = Integer.parseInt(req.getParameter("action"));
			} catch (Exception e) {
				action = 0;
			}
			try {
				idx = Integer.parseInt(req.getParameter("ROW"));
			} catch (Exception e) {
				idx = 0;
			}			
			if (action != 3) {
				try {
					acc = req.getParameter("ACCUID");
					rule = req.getParameter("SIGRULE");
					amount = req.getParameter("AMOUNT");
					ccycode = req.getParameter("CCYCODE");
					yearto = Util.formatYear(req.getParameter("YEARTO"));
					monthto = "" + Integer.parseInt(req.getParameter("MONTHTO"));
					dayto = "" + Integer.parseInt(req.getParameter("DAYTO"));					
					yearfrom = Util.formatYear(req.getParameter("YEARFROM"));
					monthfrom = "" + Integer.parseInt(req.getParameter("MONTHFROM"));
					dayfrom = "" + Integer.parseInt(req.getParameter("DAYFROM"));					
					branch = req.getParameter("BRANCH");
					status = req.getParameter("STATUS");
					docnum = req.getParameter("DOCNUM");
					 
					double damt = Util.parseCCYtoDouble(amount);
					amount = new BigDecimal(damt).setScale(2,BigDecimal.ROUND_CEILING).toString();

					msgRT = (datapro.eibs.beans.EDD570001Message ) mc.getMessageRecord("EDD560001"); 
					msgRT.setH01USERID(user.getH01USR());
					msgRT.setH01PROGRM("EDD5600");
					msgRT.setH01TIMSYS(getTimeStamp());
					msgRT.setH01SCRCOD("01");
					msgRT.setE01RLSACC(acc);
					msgRT.setE01RLSSGR(rule);
					msgRT.setE01RLSCCY(ccycode);
					msgRT.setE01RLSCUN("");
					msgRT.setE01RLSAMN(amount);
					msgRT.setE01RLSFDM(monthfrom);
					msgRT.setE01RLSFDD(dayfrom);
					msgRT.setE01RLSFDY(yearfrom.substring(2,4));
					msgRT.setE01RLSTDM(monthto);
					msgRT.setE01RLSTDD(dayto);
					msgRT.setE01RLSTDY(yearto.substring(2,4));
					msgRT.setE01RLSSTS(status);
					msgRT.setE01RLSBRN(branch);
					msgRT.setE01RLSDOC(docnum);

					
				} catch (Exception e) {
					amount ="0";
				}
			}
			
			try {
										
				cnx = ServiceLocator.getInstance().getDBConn("image-server");
				 
				switch (action){
					case 1: // New
					      if (verifyDuplicateKeyJDBC(cnx,acc,rule,ccycode)) error = 1;
					      else {
							String opecode = "0001" ; 
							// Send Initial data
							// 0009 - Delete
							// 0001 - New
							// 0002 - Maintenance
							msgRT.setH01OPECOD(opecode);
							msgRT.send();
							msgRT.destroy();
							// Receive Error Message
								newmessage = mc.receiveMessage();
								if (newmessage.getFormatName().equals("ELEERR")) {
									msgError = (ELEERRMessage) newmessage;
									IsNotError = msgError.getERRNUM().equals("0");
									flexLog("IsNotError = " + IsNotError);
									showERROR(msgError);
								}

							if (IsNotError) { // There are no errors

							sql = "INSERT INTO SCNSIGRULES (ACCUID, SIGRULE, CCYCODE, AMOUNT, DAYFROM, MONTHFROM, YEARFROM, DAYTO, MONTHTO, YEARTO, STATUS, BRANCH, DOCNUM, MODAPPROVED, TYPEMAINT) " +
							"VALUES (" + acc + ",'" + rule + "','" + ccycode + "'," + amount + "," + dayfrom + "," + monthfrom + "," + yearfrom + "," + dayto + "," + monthto + "," + yearto + "," + status + ",'" + branch + "'," + docnum + ",0"+ ",1" + ")" ;
							ps = cnx.prepareStatement(sql);
							ps.executeUpdate();

							} else {
								error = 3;
							}

					      }
					      break;
					case 2: // Maintenance 
					      rulesList.setCurrentRow(idx-1);
						  dsr = (DataSignRule) rulesList.getRecord();


							String opecode = "0002"; 
							// Send Initial data
							// 0009 - Delete
							// 0001 - New
							// 0002 - Maintenance
							msgRT.setH01OPECOD(opecode);
							msgRT.send();
							msgRT.destroy();
							// Receive Error Message
							newmessage = mc.receiveMessage();
							if (newmessage.getFormatName().equals("ELEERR")) {
								msgError = (ELEERRMessage) newmessage;
								IsNotError = msgError.getERRNUM().equals("0");
								flexLog("IsNotError = " + IsNotError);
								showERROR(msgError);
							}

						if (IsNotError) { // There are no errors

						  if (dsr.getSigRule().equals(rule) && dsr.getCCYCode().equals(ccycode)) { // Update rule
							sql = "UPDATE SCNSIGRULES SET Amount = " + amount + 
								  ", DayFrom = " + dayfrom + ", MonthFrom = " + monthfrom + ", YearFrom = " + yearfrom +
								  ", DayTo = " + dayto + ", MonthTo = " + monthto + ", YearTo = " + yearto +
								  ", Status = " + status +
								  ", Branch = '" + branch + "'" +
								  ", DocNum = " + docnum +
								  ", modApproved = 0" +
								  ", typeMaint = 2" +
								  " WHERE ACCUID =" + acc + " AND SIGRULE = '" + rule +"' AND CCYCODE = '" + ccycode +"' "; 
							ps = cnx.prepareStatement(sql);
							ps.executeUpdate(); 
						  } else { //Modifify rule
							if (verifyDuplicateKeyJDBC(cnx,acc,rule,ccycode)) error = 1;
							else {
								sql = "DELETE FROM SCNSIGRULES WHERE ACCUID =" + acc + " AND SIGRULE = '" + dsr.getSigRule() +"' AND CCYCODE = '" + dsr.getCCYCode().trim()+"'";
								ps = cnx.prepareStatement(sql);
								ps.executeUpdate();
								// Insert 
								sql = "INSERT INTO SCNSIGRULES (ACCUID, SIGRULE, CCYCODE, AMOUNT, DAYFROM, MONTHFROM, YEARFROM, DAYTO, MONTHTO, YEARTO, STATUS, BRANCH, DOCNUM, MODAPPROVED, TYPEMAINT) " +
								"VALUES (" + acc + ",'" + rule + "','" + ccycode + "'," + amount + "," + dayfrom + "," + monthfrom + "," + yearfrom + "," + dayto + "," + monthto + "," + yearto + "," + status + ",'" + branch + "'," + docnum+ ",0"+ ",2" + ")" ;
								ps = cnx.prepareStatement(sql);
								ps.executeUpdate();
							}
						  }
						} else {
							error = 3;
						}
						  					     
						 break;
					case 3: // Delete

						rulesList.setCurrentRow(idx);
						dsr = (DataSignRule) rulesList.getRecord();

						msgRT = (datapro.eibs.beans.EDD570001Message ) mc.getMessageRecord("EDD560001"); 
						msgRT.setH01USERID(user.getH01USR());
						msgRT.setH01PROGRM("EDD5600");
						msgRT.setH01TIMSYS(getTimeStamp());
						msgRT.setH01SCRCOD("01");
						msgRT.setE01RLSACC(dsr.getAccUid());
						msgRT.setE01RLSSGR(dsr.getSigRule());
						msgRT.setE01RLSCCY(dsr.getCCYCode());
	
						opecode = "0009"; 
						// Send Initial data
						// 0009 - Delete
						// 0001 - New
						// 0002 - Maintenance
						msgRT.setH01OPECOD(opecode);
						msgRT.send();
						msgRT.destroy();
						// Receive Error Message
							newmessage = mc.receiveMessage();
							if (newmessage.getFormatName().equals("ELEERR")) {
								msgError = (ELEERRMessage) newmessage;
								IsNotError = msgError.getERRNUM().equals("0");
								flexLog("IsNotError = " + IsNotError);
								showERROR(msgError);
							}

						if (IsNotError) { // There are no errors

							sql = "UPDATE SCNSIGRULES SET modApproved = 0 " +
								  ", typeMaint = 9" +
								  " WHERE ACCUID =" + dsr.getAccUid().trim() + " AND SIGRULE = '" + dsr.getSigRule().trim()+"' AND CCYCODE = '" + dsr.getCCYCode().trim()+"'"; 
							ps = cnx.prepareStatement(sql);
							ps.executeUpdate(); 

//							rulesList.setCurrentRow(idx);
//							dsr = (DataSignRule) rulesList.getRecord();
//							sql = "DELETE FROM SCNSIGRULES WHERE ACCUID =" + dsr.getAccUid().trim() + " AND SIGRULE = '" + dsr.getSigRule().trim()+"' AND CCYCODE = '" + dsr.getCCYCode().trim()+"'";
//							ps = cnx.prepareStatement(sql);
//							ps.executeUpdate();
						} else {
							error = 3;
						}

						 break;
					default :
						res.sendRedirect(super.srctx + LangPath + super.devPage);
						break;
				}
								
			} catch (SQLException e) {
				flexLog("Error: " + e);
				res.sendRedirect(super.srctx + LangPath + super.sckNotRespondPage);
				error = 2;
			} catch (ServiceLocatorException e) {
				flexLog("Error: " + e);
				res.sendRedirect(super.srctx + LangPath + super.sckNotRespondPage);
				error = 2;
			} catch (Exception e) {
				flexLog("Error: " + e);
				//change to page of sql error
				res.sendRedirect(super.srctx + LangPath + super.sckNotRespondPage);
				error = 2;
			} finally {
				try {
					cnx.close();
				} catch (SQLException e) {
					flexLog("Error: " + e);
				}
			}    
			
			switch (action) {
				case 1 :
				case 2 :
						if (error == 0 )	{
								res.setContentType("text/html");
								PrintWriter out = res.getWriter();
								out.println("<HTML>");
								out.println("<HEAD>");
								out.println("<TITLE>Close</TITLE>");
								out.println("</HEAD>");
								out.println("<BODY>");
								out.println("<SCRIPT LANGUAGE=\"JavaScript\">");
								out.println("		top.opener.document.forms[0].SCREEN.value = '3';");
								out.println("		top.opener.document.forms[0].submit();");
								out.println("		top.close();");
								out.println("</SCRIPT>");
								out.println("<P>Close it!!!</P>");
								out.println("</BODY>");
								out.println("</HTML>");
								out.close();		
						} else {
							msgError = (ELEERRMessage) newmessage;
							ses.setAttribute("errorMsg", msgError);

							flexLog("About to call Page: " + LangPath + "EDD0000_rt_sign_rules_maint.jsp");
							String urlpage = LangPath + "EDD0000_rt_sign_rules_maint.jsp?ERROR="+ error + 
							   				"&RULE=" + rule + "&AMOUNT=" + amount+ "&CCYCODE=" + ccycode;
							 if (action == 2) urlpage = urlpage + "&ROW=" + idx;
							callPage(urlpage, req, res);
						}
						break;
				case 3 :
						if (error != 0 ){
							flexLog("About to call Page: " + LangPath + "EDD0000_rt_sign_rules_list.jsp");
							String urlpage = LangPath + "EDD0000_rt_sign_rules_list.jsp?ERROR="+ error;
							callPage(urlpage, req, res);
						} else 
							procReqSignRules(user, req, res, ses);
						break;
			}
		}

	protected boolean verifyDuplicateKey(JBObjList list, String acc, String rule, String ccycode) {
		boolean duplicate = false;
		if (!list.getNoResult()) {
			list.initRow();
			while (list.getNextRow()) {
				DataSignRule dsr = (DataSignRule) list.getRecord();
				if (dsr.getAccUid().equals(acc) && dsr.getSigRule().equals(rule) && dsr.getCCYCode().equals(ccycode)) {
					duplicate =true;
					break;
				}
			}
		}
		return duplicate;
	}
	
	protected boolean verifyDuplicateKeyJDBC(Connection cnx, String acc, String rule, String ccycode) {
		boolean duplicate = false;
		try{			
			Statement st = cnx.createStatement();
			String sql = "SELECT * FROM SCNSIGRULES WHERE ACCUID = " + acc + " AND SIGRULE = '" + rule + "' AND CCYCODE = '" + ccycode + "'";
			ResultSet rs = st.executeQuery(sql);
			if (rs.next()) duplicate = true;
			rs.close();
			rs = null;
			st.close();
			st = null;
		} catch (SQLException e) {
			flexLog("Error: " + e);
			duplicate =true;
		}
		return duplicate;
	}
	
	/**
		 * This method was created in VisualAge.
		 */
		protected void procReqSignerList(
			MessageContext mc,
			ESS0030DSMessage user,
			HttpServletRequest req,
			HttpServletResponse res,
			HttpSession ses)
			throws ServletException, IOException {

			MessageRecord newmessage = null;
			EDD550001Message msgRT = null;
			ELEERRMessage msgError = null;
			UserPos userPO = null;
			boolean IsNotError = false;

			
			msgError = new datapro.eibs.beans.ELEERRMessage();
			
			userPO = (datapro.eibs.beans.UserPos) ses.getAttribute("userPO");

			String opCode = null;
			opCode = "0004";

			// Send Initial data
			try {
				msgRT = (EDD550001Message) mc.getMessageRecord("EDD550001");
				msgRT.setH01USR(user.getH01USR());
				msgRT.setH01PGM("EDD5500");
				msgRT.setH01TIM(getTimeStamp());
				msgRT.setH01SCR("01");
				msgRT.setH01OPE(opCode);
				msgRT.setE01CUN(userPO.getIdentifier());
				msgRT.setE01RTP("S");
				msgRT.send();
				msgRT.destroy();
			

				// Receive Error or Data Message
				
				newmessage = mc.receiveMessage();
	
				if (newmessage.getFormatName().equals("ELEERR")) {
	
					msgError = (ELEERRMessage) newmessage;
					ses.setAttribute("error", msgError);
					
					if (userPO.getOption().equals("RT")) {
						try {
							flexLog(
								"About to call Page4: "
									+ LangPath
									+ "EDD0000_rt_basic.jsp");
							callPage(
								LangPath + "EDD0000_rt_basic.jsp",
								req,
								res);
						} catch (Exception e) {
							flexLog("Exception calling page " + e);
						}
					} else if (userPO.getOption().equals("SV")) {
						try {
							flexLog(
								"About to call Page4: "
									+ LangPath
									+ "EDD0000_sv_basic.jsp");
							callPage(
								LangPath + "EDD0000_sv_basic.jsp",
								req,
								res);
						} catch (Exception e) {
							flexLog("Exception calling page " + e);
						}
					}
				} 
				else if (newmessage.getFormatName().equals("EDD550001")) {
	
				
					JBObjList beanList = new datapro.eibs.beans.JBObjList();
				
					//boolean firstTime;
					String marker = "";
	
					while (true) {
						
						msgRT = (EDD550001Message) newmessage;
										
						marker = msgRT.getH01MAS();
		
						if (marker.equals("*")) {
							beanList.setShowNext(false);
							break;
						} else {
							beanList.addRow(msgRT);
						}
						newmessage = mc.receiveMessage();
					}
	
				
					
					flexLog("Putting java beans into the session");
					//ses.setAttribute("userPO", userPO);
					ses.setAttribute("signersList", beanList);
					ses.setAttribute("error", msgError);
					
					try {
						flexLog(
							"About to call Page3: "
								+ LangPath
								+ "EDD0000_rt_signers_list.jsp");
						callPage(LangPath + "EDD0000_rt_signers_list.jsp", req, res);
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
		protected void procActionSigners(
			MessageContext mc,
			ESS0030DSMessage user,
			HttpServletRequest req,
			HttpServletResponse res,
			HttpSession ses)
			throws ServletException, IOException {

			MessageRecord newmessage = null;
			EDD550001Message msgRT = null;
			ELEERRMessage msgError = null;
			UserPos userPO = null;
			boolean IsNotError = false;

			JBObjList signersList = (datapro.eibs.beans.JBObjList) ses.getAttribute("signersList");
			msgError = new datapro.eibs.beans.ELEERRMessage();			
			userPO = (datapro.eibs.beans.UserPos) ses.getAttribute("userPO");
			
			String opt = req.getParameter("OPTION").trim(); 
			String opecode = "000" + opt; 
			int row = Integer.parseInt(req.getParameter("ROW")); 
			String prevStatus = "";
			// Send Initial data
			// 0009 - Delete
			// 0001 - New
			// 0002 - Maintenance
			
			try {
				if (opecode.equals("0009")) {
					
					signersList.setCurrentRow(row);
					msgRT = (datapro.eibs.beans.EDD550001Message ) signersList.getRecord(); 
					msgRT.setH01USR(user.getH01USR());
					msgRT.setH01PGM("EDD5500");
					msgRT.setH01TIM(getTimeStamp());
					msgRT.setH01SCR("01");
					msgRT.setH01OPE(opecode);
					msgRT.setE01RTP("S");
					prevStatus = msgRT.getE01RTY();
					msgRT.setE01RTY("D"); //Delete
					
				} else {
				
					msgRT = (EDD550001Message) mc.getMessageRecord("EDD550001");
					msgRT.setH01USR(user.getH01USR());
					msgRT.setH01PGM("EDD5500");
					msgRT.setH01TIM(getTimeStamp());
					msgRT.setH01SCR("01");
					msgRT.setH01OPE(opecode);
					msgRT.setE01RTP("S");
					
					prevStatus = req.getParameter("E01RTY");
					
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
					if (!prevStatus.equals("N")) msgRT.setE01RTY("M"); //Maintenance  
				}
				//msgRT.send();
				mc.sendMessage(msgRT);
				msgRT.destroy();
				flexLog("EDD550001 Message Sent");
			

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

				if (newmessage.getFormatName().equals("EDD550001")) {
					
					msgRT = (EDD550001Message) newmessage;
					
					flexLog("Putting java beans into the session");
					
					ses.setAttribute("userPO", userPO);
					ses.setAttribute("error", msgError);
					
					if (opecode.equals("0009")) {
						
						if (IsNotError) { // There are no errors
							signersList.setRecord(msgRT,row);
							ses.setAttribute("signersList", signersList);
						}
						
						try {	
								flexLog(
									"About to call Page: "
										+ LangPath
										+ "EDD0000_rt_signers_list.jsp");
								callPage(
									LangPath + "EDD0000_rt_signers_list.jsp",
									req,
									res);
	
						} catch (Exception e) {
							flexLog("Exception calling page " + e);
													}
						
					} else {
						
					      if (IsNotError) { // There are no errors
						
							if (opecode.equals("0001")) signersList.addRow(msgRT);
							else signersList.setRecord(msgRT,row);
							ses.setAttribute("signersList", signersList);
							
							res.setContentType("text/html");
							PrintWriter out = res.getWriter();
							out.println("<HTML>");
							out.println("<HEAD>");
							out.println("<TITLE>Close</TITLE>");
							out.println("</HEAD>");
							out.println("<BODY>");
							out.println("<SCRIPT LANGUAGE=\"JavaScript\">");
							out.println("		top.opener.document.location = '" + super.srctx + LangPath + "EDD0000_rt_signers_list.jsp'");
							//out.println("		top.opener.document.forms[0].submit();");
							out.println("		top.close();");
							out.println("</SCRIPT>");
							out.println("<P>Close it!!!</P>");
							out.println("</BODY>");
							out.println("</HTML>");
							out.close();
							
						  } else { // There are errors
								    
								ses.setAttribute("rtFirm", msgRT);
								try {
									flexLog(
										"About to call Page: "
											+ LangPath
											+ "EDD0000_rt_signers.jsp");
									callPage(LangPath + "EDD0000_rt_signers.jsp?OPTION=" + opt, req, res);
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
}