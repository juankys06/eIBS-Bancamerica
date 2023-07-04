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
import datapro.eibs.beans.EDD570001Message;

import datapro.eibs.beans.JBObjList;
import datapro.eibs.beans.UserPos;
import datapro.eibs.master.JSEIBSProp;
import datapro.eibs.master.Util;
import datapro.eibs.sockets.MessageContext;
import datapro.eibs.sockets.MessageRecord;
import datapro.eibs.sockets.MessageField;

public class JSEDD5700 extends datapro.eibs.master.SuperServlet {
	
	// RETAIL ACCOUNTS
	
	protected static final int R_RT_SIGN_RULES = 1;
	protected static final int A_RT_SIGN_RULES = 2;
	protected static final int I_RT_SIGN_RULES = 3;
	protected static final int R_RT_SIGNERS = 19;
	protected static final int A_RT_SIGNERS = 20;
	protected static final int A_RT_SIGNERS_IMAGES = 30;
	
	
	protected String LangPath = "S";
	
	/**
	 * JSReportManager constructor comment.
	 */
	public JSEDD5700() {
		super();
	}
	/**
	 * JSReportManager constructor comment.
	 * @param logType int
	 */
	public JSEDD5700(int logType) {
		super(logType);
	}
	
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
						break;					
					case A_RT_SIGN_RULES :
						procActSignRules(msgUser, req, res, session);
						break;
					case I_RT_SIGN_RULES :
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
					case A_RT_SIGNERS_IMAGES :
						procReqSignImages(msgUser, req, res, session);
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

	protected void procReqSignImages(
		ESS0030DSMessage user,
		HttpServletRequest req,
		HttpServletResponse res,
		HttpSession ses) 
		throws ServletException, IOException {
		 
			String CUN = req.getParameter("CUN");    
			String NAM = req.getParameter("NAM") ;    
			String CUN2 = req.getParameter("CUN2") ;    
			String NAM3 = req.getParameter("NAM3") ;    
			String LIST = req.getParameter("LIST") ;    
			String NAMES = req.getParameter("NAMES") ;    

			try {
				flexLog("About to call Page: /servlet/datapro.eibs.tools.JSEDI0010?SCREEN=6+&Type=C&CUN="+CUN+"&NAM="+NAM+"&E02CUN="+CUN2+"&E02NA1="+NAM3+"&LIST="+LIST+"&NAMES="+NAMES);
				res.sendRedirect(super.srctx + 
					"/servlet/datapro.eibs.tools.JSEDI0010?SCREEN=6&Type=C&CUN="+CUN+"&NAM="+NAM+"&E02CUN="+CUN2+"&E02NA1="+NAM3+"&LIST="+LIST+"&NAMES="+NAMES);
			} catch (Exception e) {
				flexLog("Exception calling page " + e);
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
		
	protected void procActSignRules(
			ESS0030DSMessage user,
			HttpServletRequest req,
			HttpServletResponse res,
			HttpSession ses)
			throws ServletException, IOException {

			JBObjList rulesList = (datapro.eibs.beans.JBObjList) ses.getAttribute("rulesList");

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
			String sequence = "";
			String idsigner1 = "";
			String namesigner1 = "";
			String catsigner1 = "";
			String idsigner2 = "";
			String namesigner2 = "";
			String catsigner2 = "";
			String idsigner3 = "";
			String namesigner3 = "";
			String catsigner3 = "";
			String modapproved = "";
			String typemaint = "";
		
			int action = 0;
			int idx = 0;
			int error = 0;
			DataSignRule dsr;
			
			try {
				action = Integer.parseInt(req.getParameter("action"));
			} catch (Exception e) {
				if(req.getParameter("OPTION")!=null){
					action = Integer.parseInt(req.getParameter("OPTION"));
				} 
			}
			try {
				idx = Integer.parseInt(req.getParameter("ROW"));
			} catch (Exception e) {
				idx = 0;
			}			
			if (action == 1 || action == 2) {
				try {
					acc = req.getParameter("E01ACC");
					rule = req.getParameter("E01CS1")+req.getParameter("E01CS2")+req.getParameter("E01CS3");
					amount = req.getParameter("E01AMN");
					ccycode = req.getParameter("E01CCY");
					yearto = Util.formatYear(req.getParameter("E01TDY"));
					monthto = "" + Integer.parseInt(req.getParameter("E01TDM"));
					dayto = "" + Integer.parseInt(req.getParameter("E01TDD"));					
					yearfrom = Util.formatYear(req.getParameter("E01FDY"));
					monthfrom = "" + Integer.parseInt(req.getParameter("E01FDM"));
					dayfrom = "" + Integer.parseInt(req.getParameter("E01FDD"));					
					branch = req.getParameter("E01BRN");
					status = req.getParameter("E01STS");
					docnum = req.getParameter("E01DOC");
					sequence = "" + req.getParameter("E01SQN");
					idsigner1 = "" + req.getParameter("E01SG1");
					namesigner1 = req.getParameter("E01NM1");
					catsigner1 = req.getParameter("E01CS1");
					idsigner2 = "" + req.getParameter("E01SG2");
					namesigner2 = req.getParameter("E01NM2");
					catsigner2 = req.getParameter("E01CS2");
					idsigner3 = "" + req.getParameter("E01SG3");
					namesigner3 = req.getParameter("E01NM3");
					catsigner3 = req.getParameter("E01CS3");

					double damt = Util.parseCCYtoDouble(amount);
					amount = new BigDecimal(damt).setScale(2,BigDecimal.ROUND_CEILING).toString();
					
				} catch (Exception e) {
					amount ="0";
				}
			}

			try {
										
				cnx = ServiceLocator.getInstance().getDBConn("image-server");
				 
				switch (action){
					case 1: // New
					      if (verifyDuplicateKeyJDBC(cnx,acc,sequence)) error = 1;

					      else {
							sql = "INSERT INTO SCNSIGRULES (ACCUID, SEQUENCE, SIGRULE, CCYCODE, AMOUNT, DAYFROM, MONTHFROM, YEARFROM, DAYTO, MONTHTO, YEARTO, STATUS, BRANCH, DOCNUM, IDSIGNER1, NAMESIGNER1, CATSIGNER1,IDSIGNER2, NAMESIGNER2, CATSIGNER2,IDSIGNER3, NAMESIGNER3, CATSIGNER3, MODAPPROVED, TYPEMAINT ) " +
							"VALUES (" + acc + "," + sequence + ",'" + rule + "','" + ccycode + "'," + amount + "," + dayfrom + "," + monthfrom + "," + yearfrom + "," + dayto + "," + monthto + "," + yearto + ",'" + status + "','" + branch + "'," + docnum + "," + idsigner1 + ",'" + namesigner1 + "','" + catsigner1 + "'," + idsigner2 + ",'" + namesigner2 + "','" + catsigner2 + "'," + idsigner3 + ",'" + namesigner3 + "','" + catsigner3 + "','0','0'"+")" ;
							ps = cnx.prepareStatement(sql);
							ps.executeUpdate();
					      }
					      break;
					case 2: // Maintenance 
							if (verifyDuplicateKeyJDBC(cnx,acc,sequence)) {
								sql = "DELETE FROM SCNSIGRULES WHERE ACCUID =" + acc + " AND SEQUENCE = " + sequence ;
								ps = cnx.prepareStatement(sql);
								ps.executeUpdate();
							}
								// Insert 
								sql = "INSERT INTO SCNSIGRULES (ACCUID, SEQUENCE, SIGRULE, CCYCODE, AMOUNT, DAYFROM, MONTHFROM, YEARFROM, DAYTO, MONTHTO, YEARTO, STATUS, BRANCH, DOCNUM, IDSIGNER1, NAMESIGNER1, CATSIGNER1,IDSIGNER2, NAMESIGNER2, CATSIGNER2,IDSIGNER3, NAMESIGNER3, CATSIGNER3, MODAPPROVED, TYPEMAINT ) " +
								"VALUES (" + acc + "," + sequence + ",'" + rule + "','" + ccycode + "'," + amount + "," + dayfrom + "," + monthfrom + "," + yearfrom + "," + dayto + "," + monthto + "," + yearto + ",'" + status + "','" + branch + "'," + docnum + "," + idsigner1 + ",'" + namesigner1 + "','" + catsigner1 + "'," + idsigner2 + ",'" + namesigner2 + "','" + catsigner2 + "'," + idsigner3 + ",'" + namesigner3 + "','" + catsigner3 + "','0','0'"+")" ;
								ps = cnx.prepareStatement(sql);
								ps.executeUpdate();
						 break;
					case 9: // Delete
						acc = req.getParameter("E01ACC");
						sequence = "" + req.getParameter("SQN");
						if (verifyDuplicateKeyJDBC(cnx,acc,sequence)) {
							sql = "DELETE FROM SCNSIGRULES WHERE ACCUID =" + acc + " AND SEQUENCE = " + sequence ;
							ps = cnx.prepareStatement(sql);
							ps.executeUpdate();
						}
						 break;
					default :
						res.sendRedirect(super.srctx + LangPath + super.devPage);
						break;
				}
				return;
								
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
	
	protected boolean verifyDuplicateKeyJDBC(Connection cnx, String acc, String sequence) {
		boolean duplicate = false;
		try{			
			Statement st = cnx.createStatement();
			String sql = "SELECT * FROM SCNSIGRULES WHERE ACCUID = " + acc + " AND SEQUENCE = " + sequence ;
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
			EDD570001Message msgRT = null;
			ELEERRMessage msgError = null;
			UserPos userPO = null;
			boolean IsNotError = false;

			
			msgError = new datapro.eibs.beans.ELEERRMessage();
			
			userPO = (datapro.eibs.beans.UserPos) ses.getAttribute("userPO");

			String opCode = null;
			opCode = "0015";
			String opt = null;
			opt = "";

			if(req.getParameter("opt")!=null){
				opt = req.getParameter("opt").trim(); 
			} 

			if(req.getParameter("E01ACMACC")!=null){
				userPO.setIdentifier(req.getParameter("E01ACMACC"));
			} 

			if(userPO.getIdentifier().equals("0")){
				if(req.getParameter("NameSearch")!=null){
					userPO.setIdentifier(req.getParameter("NameSearch"));
				} 
			} 

			// Send Initial data
			try {
				msgRT = (EDD570001Message) mc.getMessageRecord("EDD570001");
				msgRT.setH01USR(user.getH01USR());
				msgRT.setH01PGM("EDD5700");
				msgRT.setH01TIM(getTimeStamp());
				msgRT.setH01SCR("01");
				msgRT.setH01OPE(opCode);
				msgRT.setE01ACC(userPO.getIdentifier());
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
				else if (newmessage.getFormatName().equals("EDD570001")) {

					boolean firstTime = true;				
					JBObjList beanList = new datapro.eibs.beans.JBObjList();
				
					//boolean firstTime;
					String marker = "";
	
					while (true) {
						
						msgRT = (EDD570001Message) newmessage;
										
						marker = msgRT.getH01MAS();

						if (firstTime) {
							firstTime = false;
							userPO.setHeader2(msgRT.getE01CUN());
							userPO.setHeader3(msgRT.getE01MA1());
							userPO.setCurrency(msgRT.getE01CCY());
						}
		
						if (marker.equals("*")) {
							beanList.setShowNext(false);
							break;
						} else {
							
							beanList.addRow(msgRT);
						}
						newmessage = mc.receiveMessage();
					}
	


					try {
						msgRT = new datapro.eibs.beans.EDD570001Message();
						flexLog("EDD570001 Message Received");
					} catch (Exception ex) {
						flexLog("Error: " + ex);
					}
					
					flexLog("Putting java beans into the session");
					ses.setAttribute("userPO", userPO);
					ses.setAttribute("signersList", beanList);
					ses.setAttribute("error", msgError);
					ses.setAttribute("rtCond", msgRT);

					if (opt.equals("5")) {

//						try {
//							flexLog(
//								"About to call PageInq: "
//									+ LangPath
//									+ "EDD0000_rt_inq_condit_signers_list.jsp");
//							callPage(LangPath + "EDD0000_rt_inq_condit_signers_list.jsp", req, res);
//						} catch (Exception e) {
//							flexLog("Exception calling page " + e);
//						}
						try {
							flexLog(
								"About to call PageInq: "
									+ LangPath
									+ "EDD0000_rt_inq_condit_signers_list.jsp");
							callPage(LangPath + "EDD0000_rt_inq_condit_signers_list.jsp", req, res);						} catch (Exception e) {
							flexLog("Exception calling page " + e);
						}

					}else{

						try {
							flexLog(
								"About to call Page3: "
									+ LangPath
									+ "EDD0000_rt_condit_signers_list1.jsp");
							callPage(LangPath + "EDD0000_rt_condit_signers_list1.jsp", req, res);
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
			EDD570001Message msgRT = null;
			ELEERRMessage msgError = null;
			HttpSession session = null;
			UserPos userPO = null;
			boolean IsNotError = false;

			JBObjList signersList = (datapro.eibs.beans.JBObjList) ses.getAttribute("signersList");
			msgError = new datapro.eibs.beans.ELEERRMessage();			
			userPO = (datapro.eibs.beans.UserPos) ses.getAttribute("userPO");
			
			String opt = req.getParameter("OPTION").trim(); 
			String opecode = "000" + opt; 
			String sqn = ""; 
			int row = 0;
			if(req.getParameter("ROW")!=null){
				row = Integer.parseInt(req.getParameter("ROW")); 
			}
			String prevStatus = "";
			if(req.getParameter("SQN")!=null){
				sqn = req.getParameter("SQN").trim(); 
			}else{
				sqn = ""; 
			}

			// Send Initial data
			// 0009 - Delete
			// 0001 - New
			// 0002 - Maintenance
			
			try {
				if (opecode.equals("0009")) {
					
					signersList.setCurrentRow(row);
					msgRT = (datapro.eibs.beans.EDD570001Message ) signersList.getRecord(); 
					msgRT.setH01USR(user.getH01USR());
					msgRT.setH01PGM("EDD5700");
					msgRT.setH01TIM(getTimeStamp());
					msgRT.setH01SCR("01");
					msgRT.setH01OPE(opecode);
					msgRT.setE01ACC(userPO.getIdentifier());
					msgRT.setE01SQN(sqn);

					prevStatus = msgRT.getH01WK1();
					msgRT.setH01WK1("D"); //Delete
					
				} else {
				
					msgRT = (EDD570001Message) mc.getMessageRecord("EDD570001");
					msgRT.setH01USR(user.getH01USR());
					msgRT.setH01PGM("EDD5700");
					msgRT.setH01TIM(getTimeStamp());
					msgRT.setH01SCR("01");
					msgRT.setH01OPE(opecode);
					msgRT.setE01ACC(userPO.getIdentifier());
					
					prevStatus = req.getParameter("H01WK1");
					
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
					if (!prevStatus.equals("N")) msgRT.setH01WK1("M"); //Maintenance  
				}

				//msgRT.send();
				mc.sendMessage(msgRT);
				msgRT.destroy();
				flexLog("EDD570001 Message Sent");
			

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

				if (newmessage.getFormatName().equals("EDD570001")) {
					
					if (IsNotError) { // There are no errors
//habilitar cuando la base de datos de banesco este disponible con la nueva tabla sql SCNSIGRULES
//						procActSignRules(user, req, res, ses);

						procReqSignerList(mc, user, req, res, ses);
							  } else { // There are errors
						try {
							msgRT = new datapro.eibs.beans.EDD570001Message();
							flexLog("EDD570001 Message Received");
						} catch (Exception ex) {
							flexLog("Error: " + ex);
						}

						msgRT = (EDD570001Message) newmessage;
								    
								flexLog("Putting java beans into the session");
								ses.setAttribute("rtCond", msgRT);
								ses.setAttribute("userPO", userPO);
								ses.setAttribute("error", msgError);

								try {
									
									flexLog(
										"About to call PageInq: "
											+ LangPath
											+ "EDD0000_rt_condit_signers_list1.jsp");
									callPage(LangPath + "EDD0000_rt_condit_signers_list1.jsp", req, res);
									
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