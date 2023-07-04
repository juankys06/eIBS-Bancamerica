/*
 * Created on Jun 11, 2008
 *
 * To change the template for this generated file go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
package datapro.eibs.products;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.datapro.generic.tool.Util;

import datapro.eibs.beans.DataTransaction;
import datapro.eibs.beans.EGL003501Message;
import datapro.eibs.beans.ELEERRMessage;
import datapro.eibs.beans.ESS0030DSMessage;
import datapro.eibs.beans.JBListRec;
import datapro.eibs.beans.JBObjList;
import datapro.eibs.beans.UserPos;
import datapro.eibs.master.JSEIBSServlet;
import datapro.eibs.master.MessageProcessor;

/**
 * @author erodriguez
 *
 * To change the template for this generated type comment go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
public class JSEGL0035 extends JSEIBSServlet {
	
	/* (non-Javadoc)
	 * @see datapro.eibs.master.JSEIBSServlet#processRequest(datapro.eibs.beans.ESS0030DSMessage, javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse, javax.servlet.http.HttpSession, int)
	 */
	protected void processRequest(ESS0030DSMessage user, HttpServletRequest req, HttpServletResponse res, HttpSession session, int screen)
		throws ServletException, IOException {
		
			//certificate of deposit 
			final int R_TRANSACTION = 1;
			final int A_TRANSACTION = 2;
			final int A_DELETE = 4;
	
			// entering options
			final int R_ENTER = 100;
			final int A_ENTER = 200;
		

			switch (screen) {
				// Request
				case R_TRANSACTION :
					procReqTr(user, req, res, session);
					break;
				// Action
				case A_TRANSACTION :
					procActionTr(user, req, res, session);
					break;
				case A_DELETE :
					procActionDeleteBatch(user, req, res, session);
					break;	
				// Request
				case R_ENTER :
					procReqEnter(user, req, res, session);
					break;
				case A_ENTER :
					procActionEnter(user, req, res, session);
					break;
				default :
					forward("MISC_not_available.jsp", req, res);
					break;
			}

	}

	/**
	 * @param user
	 * @param req
	 * @param res
	 * @param session
	 */
	private void procActionEnter(ESS0030DSMessage user, HttpServletRequest req, HttpServletResponse res, HttpSession session) throws ServletException, IOException {
		UserPos userPO = (UserPos) session.getAttribute("userPO");
		userPO.setOption("TRANSACTION");
		
		DataTransaction transData = new DataTransaction();
		transData.setWrkFile("2");
		transData.setBank(req.getParameter("E01WRKOBK"));
		if (transData.getBank() == null) {
			transData.setBank(user.getE01UBK());
		}
		transData.setBranch(req.getParameter("E01WRKOBR"));
		transData.setBthnum(req.getParameter("E01BTHNUM"));

		flexLog("Putting java beans into the session");
		session.setAttribute("transData", transData);
		session.setAttribute("userPO", userPO);
		
		procReqTr(user, req, res, session);
		
	}

	/**
	 * @param user
	 * @param req
	 * @param res
	 * @param session
	 */
	private void procReqEnter(ESS0030DSMessage user, HttpServletRequest req, HttpServletResponse res, HttpSession session) throws ServletException, IOException {
		UserPos userPO = (UserPos) session.getAttribute("userPO");
		
		MessageProcessor mp = null;
		try {
			mp = getMessageProcessor("EGL0035", req);
			EGL003501Message msg = (EGL003501Message) mp.getMessageRecord("EGL003501", user.getH01USR(), "0001");
			msg.setH01SCRCOD("01");
			msg.setE01WRKBNK(user.getE01UBK());
			msg.setE01WRKFIL("2");
			
			mp.sendMessage(msg);
			
			ELEERRMessage msgError = (ELEERRMessage) mp.receiveMessageRecord();
			msg = (EGL003501Message) mp.receiveMessageRecord();

			if (!mp.hasError(msgError)) {
				userPO.setOption("TRANSACTION");
				userPO.setPurpose("MAINTENANCE");
				userPO.setAccOpt("AC");
				flexLog("Putting java beans into the session");
				session.setAttribute("error", msgError);
				session.setAttribute("userPO", userPO);
				session.setAttribute("trMant", msg);
				forward("EGL0035_transaction_enter.jsp", req, res);
			} else {	
				session.setAttribute("error", msgError);
				forward("error_viewer.jsp", req, res);
			}
			
		} finally {
			if (mp != null)	mp.close();
		}
	}

	/**
	 * @param user
	 * @param req
	 * @param res
	 * @param session
	 */
	private void procActionTr(ESS0030DSMessage user, HttpServletRequest req, HttpServletResponse res, HttpSession session) throws ServletException, IOException {
		UserPos userPO = (UserPos) session.getAttribute("userPO");
		DataTransaction transData =	(DataTransaction) session.getAttribute("transData");

		int colNum = 50;
		JBListRec trList = new JBListRec();
		trList.init(colNum);
		JBListRec trListOld = (JBListRec) session.getAttribute("trans");
		trListOld.initRow();
		
		int maxRow = 0;
		try {
			maxRow = Integer.parseInt(req.getParameter("ROW"));
		} catch (Exception e) {
			maxRow = 500;
		}
		int realMaxRow = maxRow;
		int opt;
		try {
			opt = Integer.parseInt(req.getParameter("opt"));
		} catch (Exception e) {
			opt = 0;
		}

		
		String checked = "";
		String sel = "";
		String sel2 = "";
		String myFlag = "";
		
		for (int row = 0; row < maxRow; row++) {
			try {
				checked = req.getParameter("TRANSROW_" + row);
				if (!checked.equals("checked")) { checked = "";	}
			} catch (Exception e) {
				checked = "";
			}
			if ((opt == 1) & (checked.equals("checked"))) {
				realMaxRow--;
			} else {
				try {
					sel = req.getParameter("E01WRKAMT_" + row).trim();
					double sel1 = Double.parseDouble(Util.takeComa(sel));
				} catch (Exception e) {
					sel = ""; // Bad Amount 
				}
				try {
					sel2 =
						req.getParameter("E01WRKBNK_" + row)
							+ req.getParameter("E01WRKBRN_" + row)
							+ req.getParameter("E01WRKCCY_" + row)
							+ req.getParameter("E01WRKGLN_" + row)
							+ req.getParameter("E01WRKCCN_" + row)
							+ req.getParameter("E01WRKACC_" + row)
							//+ req.getParameter("E01WRKPYT_" + row)
							+ req.getParameter("E01WRKCDE_" + row)
							+ req.getParameter("E01WRKDCC_" + row);
					sel2 = sel2.trim();
				} catch (Exception e) {
					sel2 = "1";
				}
				if (sel2.equals("") || sel2.equals("nullnullnullnullnullnullnullnull")) sel2 = "0";
				int sel3 = 0;
				try {
					sel3 = Integer.parseInt(sel2);
				} catch (Exception e) {
					sel3 = 1;
				}
				if (sel3 == 0) { // from wrong, transaction data is not complete
					sel2 = ""; 
				} 
				if (sel.equals("") & sel2.equals("")) {
					continue;
				} else if (sel.equals("") & (opt == 6 | opt == 7)) {
					continue;
				}

				String myRow[] = new String[colNum];
				for (int i = 0; i < colNum; i++) myRow[i] = "";
				
				myRow[0] = req.getParameter("E01WRKBNK_" + row).toUpperCase();
				// Bank
				myRow[1] = req.getParameter("E01WRKBRN_" + row).toUpperCase();
				// Branch
				myRow[2] = req.getParameter("E01WRKCCY_" + row).toUpperCase();
				// Currency
				myRow[3] = req.getParameter("E01WRKGLN_" + row).toUpperCase();
				// General Ledger	
				myRow[4] = req.getParameter("E01WRKCCN_" + row).toUpperCase();
				// Cost Center
				myRow[5] = req.getParameter("E01WRKACC_" + row).toUpperCase();
				// Account	
				myRow[6] = req.getParameter("E01WRKCDE_" + row).toUpperCase();
				// Transaction Code
				myRow[7] = req.getParameter("E01WRKAMT_" + row).toUpperCase();
				// Amount
				myRow[8] = req.getParameter("E01WRKDCC_" + row).toUpperCase();
				// Debit or Credit
				try {
					myRow[13] =	req.getParameter("E01WRKEXR_" + row).toUpperCase();
					// Ex. Rate
					myRow[26] =	req.getParameter("E01WRKCUN_" + row).toUpperCase();
					// Customer
					myRow[37] = req.getParameter("E01WRKMD1_" + row).toUpperCase();
					myRow[38] = req.getParameter("E01WRKMD2_" + row).toUpperCase();
					myRow[39] = req.getParameter("E01WRKMD3_" + row).toUpperCase();
					myRow[27] = req.getParameter("E01WRKCKN_" + row).toUpperCase();
				} catch (Exception e) {
				}
				myRow[10] = req.getParameter("E01WRKVD1_" + row).toUpperCase();
				// Value Date 1
				if (myRow[10].equals("") || myRow[10].equals("0"))
					myRow[10] = req.getParameter("E01WRKVD1").toUpperCase();
				myRow[11] = req.getParameter("E01WRKVD2_" + row).toUpperCase();
				// Value Date 2
				if (myRow[11].equals("") || myRow[11].equals("0"))
					myRow[11] = req.getParameter("E01WRKVD2").toUpperCase();
				myRow[12] = req.getParameter("E01WRKVD3_" + row).toUpperCase();
				// Value Date 3
				if (myRow[12].equals("") || myRow[12].equals("0"))
					myRow[12] = req.getParameter("E01WRKVD3").toUpperCase();
				// ???
				try {													
				if (myRow[48].equals("") || myRow[48].equals("0") ||  myRow[48].equals("0") )
					myRow[48] = req.getParameter("E01WRKUN1_"+ row).toUpperCase();
				} catch (Exception e) {
				}
				// Credit Amount 2
				try {									
				if  (myRow[41].equals("") || myRow[41].equals("0") ||  myRow[41].equals("0.00") )
					myRow[41] = req.getParameter("E01WRKCR2_"+ row).toUpperCase();
				} catch (Exception e) {
				}
				// Uncollected 2
				try {													
				if (myRow[42].equals("") || myRow[42].equals("0") ||  myRow[42].equals("0.00") )
					myRow[42] = req.getParameter("E01WRKUN2_"+ row).toUpperCase();
				} catch (Exception e) {
				}
				// Credit Amount 3
				try {													
				if (myRow[43].equals("") || myRow[43].equals("0") ||  myRow[43].equals("0.00") )
					myRow[43] = req.getParameter("E01WRKCR3_"+ row).toUpperCase();
				} catch (Exception e) {
				}
				// Uncollected 3
				try {													
				if (myRow[44].equals("") || myRow[44].equals("0") ||  myRow[44].equals("0.00") )
					myRow[44] = req.getParameter("E01WRKUN3_"+ row).toUpperCase();
				} catch (Exception e) {
				}
				// pay/thru number
				try {													
					if (myRow[45].equals("") || myRow[45].equals("0") ||  myRow[45].equals("0.00") )
						myRow[45] = req.getParameter("E01WRKPYT_" + row).toUpperCase();
					}
				catch (Exception e) {
				}
				// Account Description only in Banesco Panama
				try {
					myRow[40] = req.getParameter("E01TITDSC_" + row).toUpperCase();
				} catch (Exception e) {
					myRow[40] = req.getParameter("E01TITDSC").toUpperCase();
				}	
				try {
					String[] myfield;
					if (req.getParameter("E01WRKTDS_" + row).equals("")) {
						myfield =
							datapro.eibs.master.Util.splitField(
								req.getParameter("E01WRKTDS").toUpperCase(),
								10,
								30);
					} else {
						myfield =
							datapro.eibs.master.Util.splitField(
								req
									.getParameter("E01WRKTDS_" + row)
									.toUpperCase(),
								10,
								30);
					}
					myRow[9] = myfield[0]; // Description	
					myRow[15] = myfield[1]; // Description 1
					myRow[16] = myfield[2]; // Description 2
					myRow[17] = myfield[3]; // Description 3
					myRow[18] = myfield[4]; //      "
					myRow[19] = myfield[5]; //      "
					myRow[20] = myfield[6]; //      "
					myRow[21] = myfield[7]; //      "
					myRow[22] = myfield[8]; //      "
					myRow[23] = myfield[9]; // Description 9
				} catch (Exception e) {
				}
				myRow[24] = req.getParameter("DUPLIC_" + row).toUpperCase();
				myRow[25] = req.getParameter("E01WRKTYP_" + row).toUpperCase();

				if (row <= trListOld.getLastRow()) {
					//Comments on fields than can be updated
					trListOld.setCurrentRow(row);
					//myRow[13] = trListOld.getRecord(13);
					myRow[14] = trListOld.getRecord(14);
					//myRow[26] = trListOld.getRecord(26);
					//myRow[27] = trListOld.getRecord(27);
					myRow[28] = trListOld.getRecord(28);
					myRow[29] = trListOld.getRecord(29);
					myRow[30] = trListOld.getRecord(30);
					myRow[31] = trListOld.getRecord(31);
					myRow[32] = trListOld.getRecord(32);
					myRow[33] = trListOld.getRecord(33);
					myRow[34] = trListOld.getRecord(34);
					myRow[35] = trListOld.getRecord(35);
					myRow[36] = trListOld.getRecord(36);
				} else {
					//myRow[13] = "0";
					myRow[14] = "";
					//myRow[26] = "0";
					//myRow[27] = "0";
					myRow[28] = "";
					myRow[29] = "0";
					myRow[30] = "0";
					myRow[31] = "0";
					myRow[32] = "0";
					myRow[33] = "0";
					myRow[34] = "0";
					myRow[35] = "0";			 
					myRow[36] = "";
				}

				trList.addRow(myFlag, myRow);
				
				//clear details about CR2,UN2,CR3,UN3				
				myRow[41] ="0.00";
				myRow[42] ="0";
				myRow[43] ="0.00";
				myRow[44] ="0";
			    
			    
				if ((opt == 2) & (checked.equals("checked"))) {
					myRow[24] = "*";
					trList.addRow(myFlag, myRow);
					realMaxRow++;
				}
			}
		}

		transData.setDescription(req.getParameter("E01WRKTDS"));
		transData.setDate1(req.getParameter("E01WRKVD1"));
		transData.setDate2(req.getParameter("E01WRKVD2"));
		transData.setDate3(req.getParameter("E01WRKVD3"));
		if (opt == 5) transData.setFlagPrint(true);

		session.setAttribute("trans", trList);
		session.setAttribute("transData", transData);
		
		switch (opt) {
			case 0 :
				break;
			case 4 :
				procActionDeleteBatch(user, req, res, session);
				break;
			case 5 :
			case 6 :		 
			case 7 :
				procActionTrBeansToSck(user, req, res, session, opt);
				break;
			default :
				maxRow = realMaxRow;
				forward("EGL0035_transaction_pay.jsp?ROW=" + maxRow, req, res);
				break;
		}
		
}

	/**
	 * @param user
	 * @param req
	 * @param res
	 * @param opt
	 */
	private void procActionTrBeansToSck(ESS0030DSMessage user, HttpServletRequest req, HttpServletResponse res, HttpSession session, int opt) throws ServletException, IOException {
		UserPos userPO = (UserPos) session.getAttribute("userPO");
		JBListRec trList = (JBListRec) session.getAttribute("trans");
		DataTransaction transData =	(DataTransaction) session.getAttribute("transData");
		MessageProcessor mp = null;
		
		int maxRow = 0;
		try {
			maxRow = Integer.parseInt(req.getParameter("ROW"));
		} catch (Exception e) {
			maxRow = 500;
		}
		try {
			mp = getMessageProcessor("EGL0035", req);

			trList.initRow();
			while (trList.getNextRow()) {
				
				EGL003501Message msg = (EGL003501Message) mp.getMessageRecord("EGL003501", user.getH01USR(), "0003");
				msg.setH01SCRCOD("01");
				
				switch (opt) {
					case 5 :
						msg.setH01FLGWK1("P");
						break;
					case 6 :		 
						msg.setH01FLGWK1("A");
						break;
					case 7 :
						msg.setH01FLGWK1("R");
						break;
				}
				
				try {
					msg.setE01NUMACC(transData.getAccNum());
				} catch (Exception e) {
				}
				try {
					msg.setE01NUMREF(transData.getRefNum());
				} catch (Exception e) {
				}
				try {
					msg.setE01CKNUMB(transData.getChkNum());
				} catch (Exception e) {
				}
				try {
					msg.setE01WRKFIL(transData.getWrkFile());
				} catch (Exception e) {
				}
				try {
					msg.setE01BTHNUM(transData.getBthnum());
				} catch (Exception e) {
				}
				try {
					msg.setE01WRKOBK(transData.getBank());
				} catch (Exception e) {
				}
				try {
					msg.setE01WRKOBR(transData.getBranch());
				} catch (Exception e) {
				}
				try {
					msg.setE01WRKBNK(trList.getRecord(0)); // Bank
				} catch (Exception e) {
				}
				try {
					msg.setE01WRKBRN(trList.getRecord(1)); // Branch
				} catch (Exception e) {
				}
				try {
					msg.setE01WRKCCY(trList.getRecord(2)); // Currency
				} catch (Exception e) {
				}
				try {
					msg.setE01WRKGLN(trList.getRecord(3)); // General Ledger
				} catch (Exception e) {
				}
				try {
					msg.setE01WRKCCN(trList.getRecord(4)); // Cost Center
				} catch (Exception e) {
				}
				try {
					msg.setE01WRKACC(trList.getRecord(5)); // Account
				} catch (Exception e) {
				}
				try {
					msg.setE01WRKPYT(trList.getRecord(45)); // Pay/Thru Number
				} catch (Exception e) {
				}
				try {
					msg.setE01WRKCDE(trList.getRecord(6)); // Transaction Code
				} catch (Exception e) {
				}
				try {
					msg.setE01WRKAMT(trList.getRecord(7)); // Amount
				} catch (Exception e) {
				}
				try {
					msg.setE01WRKDCC(trList.getRecord(8)); // Debit or Credit
				} catch (Exception e) {
				}
				msg.setE01WRKTDS(trList.getRecord(9)); // Description
				msg.setE01WRKVD1(trList.getRecord(10)); // Value Date 1
				msg.setE01WRKVD2(trList.getRecord(11)); // Value Date 2
				msg.setE01WRKVD3(trList.getRecord(12)); // Value Date 3
				msg.setE01WRKDS1(trList.getRecord(15)); // Description 1
				msg.setE01WRKDS2(trList.getRecord(16)); // Description 2
				msg.setE01WRKDS3(trList.getRecord(17)); // Description 3
				msg.setE01WRKDS4(trList.getRecord(18)); //      "
				msg.setE01WRKDS5(trList.getRecord(19)); //      "
				msg.setE01WRKDS6(trList.getRecord(20)); //      "
				msg.setE01WRKDS7(trList.getRecord(21)); //      "
				msg.setE01WRKDS8(trList.getRecord(22)); //      "
				msg.setE01WRKDS9(trList.getRecord(23)); // Description 9

				try {
					msg.setE01WRKTYP(trList.getRecord(25)); // Type
				} catch (Exception e) {
				}
				try {
					msg.setE01WRKEXR(trList.getRecord(13)); // Ex. Rate
				} catch (Exception e) {
				}
				try {
					msg.setE01WRKCUN(trList.getRecord(26)); // Customer
				} catch (Exception e) {
				}
				
				try {
					msg.setE01WRKMD1(trList.getRecord(37)); // Exp Date
				} catch (Exception e) {}		
	
				try {
					msg.setE01WRKMD2(trList.getRecord(38)); // Ex. Date
					} catch (Exception e) {}
				try {
					msg.setE01WRKMD3(trList.getRecord(39)); // Ex. Date
					} catch (Exception e) {}
					
				try {
					msg.setE01WRKCKN(trList.getRecord(27)); // Check Number
				} catch (Exception e) {
				}

				try {
					msg.setE01TITDSC(trList.getRecord(40)); // Account Description only to Banesco Panama  
				} catch (Exception e) {
				}

				try {
					msg.setE01WRKUN1(trList.getRecord(48)); 
				} catch (Exception e) {
				}
				
				try {
					msg.setE01WRKCR2(trList.getRecord(41)); 
				} catch (Exception e) {
				}

				try {
					msg.setE01WRKUN2(trList.getRecord(42)); 
				} catch (Exception e) {
				}

				try {
					msg.setE01WRKCR3(trList.getRecord(43)); 
				} catch (Exception e) {
				}

				try {
					msg.setE01WRKUN3(trList.getRecord(44)); 
				} catch (Exception e) {
				}

				msg.setE01WRKREF(trList.getRecord(14));
				msg.setE01WRKREM(trList.getRecord(28));
				msg.setE01WRKTDB(trList.getRecord(29));
				msg.setE01WRKTDC(trList.getRecord(30));
				msg.setE01WRKEDB(trList.getRecord(31));
				msg.setE01WRKEDC(trList.getRecord(32));
				msg.setE01WRKDRR(trList.getRecord(33));
				msg.setE01WRKNNR(trList.getRecord(34));
				msg.setE01NUMREC(trList.getRecord(35));
				msg.setE01INDOPE(trList.getRecord(36));
				
				mp.sendMessage(msg);				

			}
			EGL003501Message msg = (EGL003501Message) mp.getMessageRecord("EGL003501", user.getH01USR(), "0003");
			msg.setH01SCRCOD("01");
			switch (opt) {
				case 5 :
					msg.setH01FLGWK1("P");
					break;
				case 6 :		 
					msg.setH01FLGWK1("A");
					break;
				case 7 :
					msg.setH01FLGWK1("R");
					break;
			}
			msg.setH01FLGMAS("*");
			mp.sendMessage(msg);
			
			ELEERRMessage msgError = (ELEERRMessage) mp.receiveMessageRecord();
			boolean IsNotError = (!mp.hasError(msgError));
			
			JBObjList jbList = mp.receiveMessageRecordList("H01FLGMAS");
			
			int colNum = 50;
			trList.init(colNum);
			String myFlag = "";
			String myRow[] = new String[colNum];
			boolean myFirstRow = false;
			
			for ( int i = 0; i < colNum; i++ ) myRow[i] = "";
			transData.clear();
				
			jbList.initRow();
			while (jbList.getNextRow()) {
				msg = (EGL003501Message) jbList.getRecord();

				myRow[0] = msg.getE01WRKBNK(); // Bank
				myRow[1] = msg.getE01WRKBRN(); // Branch
				myRow[2] = msg.getE01WRKCCY(); // Currency
				myRow[3] = msg.getE01WRKGLN(); // General Ledger
				myRow[4] = msg.getE01WRKCCN(); // Cost Center
				myRow[5] = msg.getE01WRKACC(); // Account
				myRow[6] = msg.getE01WRKCDE(); // Transaction Code
				myRow[7] = msg.getE01WRKAMT(); // Amount
				myRow[8] = msg.getE01WRKDCC(); // Debit or Credit
				myRow[9] = msg.getE01WRKTDS().trim(); // Description
				myRow[10] = msg.getE01WRKVD1(); // Value Date 1
				myRow[11] = msg.getE01WRKVD2(); // Value Date 2
				myRow[12] = msg.getE01WRKVD3(); // Value Date 3
				myRow[13] = msg.getE01WRKEXR(); // Exchange Rate
				myRow[14] = msg.getE01WRKREF();
				// Reference -- nonvisible
				myRow[15] = msg.getE01WRKDS1().trim();
				// Description 1
				myRow[16] = msg.getE01WRKDS2().trim();
				// Description 2
				myRow[17] = msg.getE01WRKDS3().trim();
				// Description 3
				myRow[18] = msg.getE01WRKDS4().trim(); //      "
				myRow[19] = msg.getE01WRKDS5().trim(); //      "
				myRow[20] = msg.getE01WRKDS6().trim(); //      "
				myRow[21] = msg.getE01WRKDS7().trim(); //      "
				myRow[22] = msg.getE01WRKDS8().trim(); //      "
				myRow[23] = msg.getE01WRKDS9().trim();
				// Description 9
				myRow[25] = msg.getE01WRKTYP().trim(); // Type
				//fields nonvisibles
				myRow[26] = msg.getE01WRKCUN().trim(); // nonvisible
				myRow[27] = msg.getE01WRKCKN().trim(); // 	
				myRow[28] = msg.getE01WRKREM().trim(); //      "
				myRow[29] = msg.getE01WRKTDB().trim(); //      "
				myRow[30] = msg.getE01WRKTDC().trim(); //      "
				myRow[31] = msg.getE01WRKEDB().trim(); //      "
				myRow[32] = msg.getE01WRKEDC().trim(); //      "
				myRow[33] = msg.getE01WRKDRR().trim(); // 
				myRow[34] = msg.getE01WRKNNR().trim(); // 
				myRow[35] = msg.getE01NUMREC().trim(); // 
				myRow[36] = msg.getE01INDOPE().trim(); //
				myRow[37] = msg.getE01WRKMD1(); // Value Date 1
				myRow[38] = msg.getE01WRKMD2(); // Value Date 2
				myRow[39] = msg.getE01WRKMD3(); // Value Date 3
				myRow[40] = msg.getE01TITDSC(); // Account Description only to Banesco Panama
				myRow[48] = msg.getE01WRKUN1();		
				myRow[41] = msg.getE01WRKCR2();						
				myRow[42] = msg.getE01WRKUN2();
				myRow[43] = msg.getE01WRKCR3();
				myRow[44] = msg.getE01WRKUN3();
												
				myRow[45] = msg.getE01WRKPYT(); // Pay/Thru Number	
				myRow[46] = msg.getE01WRKDSC(); // Acct holder Name

				if (!myFirstRow) {
					String myDesc =
						myRow[9]
							+ myRow[15]
							+ myRow[16]
							+ myRow[17]
							+ myRow[18]
							+ myRow[19]
							+ myRow[20]
							+ myRow[21]
							+ myRow[22]
							+ myRow[23];
					transData.setDescription(myDesc.trim());
					transData.setDate1(msg.getE01WRKVD1());
					transData.setDate2(msg.getE01WRKVD2());
					transData.setDate3(msg.getE01WRKVD3());
					transData.setDebitAmt(msg.getE01WRKEDB());
					transData.setCreditAmt(msg.getE01WRKEDC());
					transData.setBank(msg.getE01WRKOBK());
					transData.setBranch(msg.getE01WRKOBR());
					myFirstRow = true;
				}

				trList.addRow(myFlag, myRow);
			}
			

			flexLog("Putting java beans into the session");
			session.setAttribute("error", msgError);
			session.setAttribute("trans", trList);
			
			if (IsNotError) { // There are no errors
				userPO.setAccOpt("");
				session.setAttribute("userPO", userPO);
				session.setAttribute("transData", transData);
				if (transData.isFlagPrint()) {
					forward("EGL0035_transaction_pay.jsp?ROW=" + maxRow, req, res);
				} else {
					if (userPO.getOption().equals("TRANSACTION")) {
						forward("EGL0035_transaction_enter.jsp", req, res);
					} else if (userPO.getOption().equals("CD")) {
						forward("EDL0130_cd_maint.jsp",	req, res);
					} else if (userPO.getOption().equals("LN")) {
						forward("EDL0150_ln_basic.jsp",	req, res);
					} else if (userPO.getOption().equals("PR")) {
						forward("EPR0000_pr_maint.jsp",	req, res);
					} else if (userPO.getOption().equals("OCK")) {
						forward("EOF0115_of_chk_enter_maint.jsp", req, res);
					} else if (userPO.getOption().equals("LN_TRANSACTION")) {
						forward("EDL0150_ln_transac.jsp", req, res);
					} else if (userPO.getOption().equals("DFT")) {
						forward("EDL0800_dft_basic.jsp", req, res);
					}
				}
			} else { // There are errors
				// NOW accOpt IS AC
				userPO.setAccOpt("AC");
				session.setAttribute("userPO", userPO);
				if (transData.isFlagPrint()) {
					transData.setFlagPrint(false);
				}
				session.setAttribute("transData", transData);
				forward("EGL0035_transaction_pay.jsp?ROW=" + maxRow, req, res);
			}
			
		} finally {
			if (mp != null)	mp.close();
		}
	}

	/**
	 * @param user
	 * @param req
	 * @param res
	 * @param opt
	 */
	private void procActionDeleteBatch(ESS0030DSMessage user, HttpServletRequest req, HttpServletResponse res, HttpSession session) throws ServletException, IOException {
		MessageProcessor mp = null;
		try {			
			mp = getMessageProcessor("EGL0035", req);
			EGL003501Message msg = (EGL003501Message) mp.getMessageRecord("EGL003501", user.getH01USR(), "0009");
			msg.setH01SCRCOD("01");
			msg.setE01WRKFIL("2");
			try {
				msg.setE01BTHNUM(req.getParameter("E01BTHNUM"));
			} catch (Exception e) {
			}			
			mp.sendMessage(msg);
			ELEERRMessage msgError = (ELEERRMessage) mp.receiveMessageRecord();
			
			if (!mp.hasError(msgError)) { // There are no errors
				forward("EGL0035_delete_conf.jsp", req, res);
			} else {	
				forward("EGL035_transaction_pay.jsp", req, res);
			}
		
		} finally {
			if (mp != null)	mp.close();
		}
	}

	/**
	 * @param req
	 * @param res
	 * @param session
	 */
	private void procReqTr(ESS0030DSMessage user, HttpServletRequest req, HttpServletResponse res, HttpSession session) throws ServletException, IOException {
		
		UserPos userPO = (UserPos) session.getAttribute("userPO");
		DataTransaction transData =	(DataTransaction) session.getAttribute("transData");
		MessageProcessor mp = null;
		String purpose = "";
		try {
			purpose = req.getParameter("PURPOSE");
			if (purpose == null) purpose = "";
		} catch (Exception e) {
			purpose = "";
		}

		try {
			mp = getMessageProcessor("EGL0035", req);
			EGL003501Message msg = (EGL003501Message) mp.getMessageRecord("EGL003501", user.getH01USR(), "0002");
			msg.setH01SCRCOD("01");
			// Get Parameters here	
			try {
				msg.setE01NUMACC(transData.getAccNum());
			} catch (Exception e) {
			}
			try {
				msg.setE01NUMREF(transData.getRefNum());
			} catch (Exception e) {
			}
			try {
				msg.setE01CKNUMB(transData.getChkNum());
			} catch (Exception e) {
			}
			try {
				msg.setE01WRKFIL(transData.getWrkFile());
			} catch (Exception e) {
			}
			try {
				msg.setE01BTHNUM(transData.getBthnum());
			} catch (Exception e) {
			}
			try {
				msg.setE01WRKOBK(transData.getBank());
			} catch (Exception e) {
			}
			try {
				msg.setE01WRKOBR(transData.getBranch());
			} catch (Exception e) {
			}
			try {
				msg.setE01WRKTYP(transData.getProdtype());
			} catch (Exception e) {
			}
			
			mp.sendMessage(msg);
			
			ELEERRMessage msgError = (ELEERRMessage) mp.receiveMessageRecord();
			boolean IsNotError = (!mp.hasError(msgError));
			
			JBObjList list = (JBObjList) mp.receiveMessageRecordList("H01FLGMAS");

			int colNum = 50;
			String myFlag = "";
			String myRow[] = new String[colNum];
			boolean myFirstRow = false;
			JBListRec trList = new JBListRec();
			//Clear
			trList.init(colNum);
			for (int i = 0; i < colNum; i++) myRow[i] = "";
			transData.clear();
			
			list.initRow();
			while (list.getNextRow()) {
				msg = (EGL003501Message) list.getRecord();
				
				myRow[0] = msg.getE01WRKBNK(); // Bank
				myRow[1] = msg.getE01WRKBRN(); // Branch
				myRow[2] = msg.getE01WRKCCY(); // Currency
				myRow[3] = msg.getE01WRKGLN(); // General Ledger
				myRow[4] = msg.getE01WRKCCN(); // Cost Center
				myRow[5] = msg.getE01WRKACC(); // Account
				myRow[6] = msg.getE01WRKCDE(); // Transaction Code
				myRow[7] = msg.getE01WRKAMT(); // Amount
				myRow[8] = msg.getE01WRKDCC(); // Debit or Credit
				myRow[9] = msg.getE01WRKTDS().trim(); // Description
				myRow[10] = msg.getE01WRKVD1(); // Value Date 1
				myRow[11] = msg.getE01WRKVD2(); // Value Date 2
				myRow[12] = msg.getE01WRKVD3(); // Value Date 3
				myRow[13] = msg.getE01WRKEXR(); // Exchange Rate
				myRow[14] = msg.getE01WRKREF();

				myRow[48] = msg.getE01WRKUN1();
				
				myRow[41] = msg.getE01WRKCR2(); //Credit Amount 2
				myRow[42] = msg.getE01WRKUN2(); //Uncollected Days 2	
				myRow[43] = msg.getE01WRKCR3(); //Credit Amount 3
				myRow[44] = msg.getE01WRKUN3(); //Uncollected Days 3		
																											
				// Reference -- nonvisible
				myRow[15] = msg.getE01WRKDS1().trim();
				// Description 1
				myRow[16] = msg.getE01WRKDS2().trim();
				// Description 2
				myRow[17] = msg.getE01WRKDS3().trim();
				// Description 3
				myRow[18] = msg.getE01WRKDS4().trim(); //      "
				myRow[19] = msg.getE01WRKDS5().trim(); //      "
				myRow[20] = msg.getE01WRKDS6().trim(); //      "
				myRow[21] = msg.getE01WRKDS7().trim(); //      "
				myRow[22] = msg.getE01WRKDS8().trim(); //      "
				myRow[23] = msg.getE01WRKDS9().trim();
				// Description 9
				myRow[25] = msg.getE01WRKTYP().trim(); // Type
				//fields nonvisibles
				myRow[26] = msg.getE01WRKCUN().trim(); // nonvisible
				myRow[27] = msg.getE01WRKCKN().trim(); // 	
				myRow[28] = msg.getE01WRKREM().trim(); //      "
				myRow[29] = msg.getE01WRKTDB().trim(); //      "
				myRow[30] = msg.getE01WRKTDC().trim(); //      "
				myRow[31] = msg.getE01WRKEDB().trim(); //      "
				myRow[32] = msg.getE01WRKEDC().trim(); //      "
				myRow[33] = msg.getE01WRKDRR().trim(); // 
				myRow[34] = msg.getE01WRKNNR().trim(); // 
				myRow[35] = msg.getE01NUMREC().trim(); // 
				myRow[36] = msg.getE01INDOPE().trim(); //
				myRow[37] = msg.getE01WRKMD1(); // Value Date 1
				myRow[38] = msg.getE01WRKMD2(); // Value Date 2
				myRow[39] = msg.getE01WRKMD3(); // Value Date 3
				myRow[40] = msg.getE01TITDSC(); // Account Description	only in Banesco Panama	
				myRow[45] = msg.getE01WRKPYT().trim();  //pay/thru number
						
				if (!myFirstRow) {
					String myDesc =
						myRow[9]
							+ myRow[15]
							+ myRow[16]
							+ myRow[17]
							+ myRow[18]
							+ myRow[19]
							+ myRow[20]
							+ myRow[21]
							+ myRow[22]
							+ myRow[23];
					transData.setDescription(myDesc.trim());
					transData.setDate1(msg.getE01WRKVD1());
					transData.setDate2(msg.getE01WRKVD2());
					transData.setDate3(msg.getE01WRKVD3());
					transData.setDebitAmt(msg.getE01WRKEDB());
					transData.setCreditAmt(msg.getE01WRKEDC());
					transData.setBank(msg.getE01WRKOBK());
					transData.setBranch(msg.getE01WRKOBR());
					myFirstRow = true;
				}

				trList.addRow(myFlag, myRow);
			}
			if (trList.getNoResult()) {
				transData.setTrNum("6");
			} else {
				transData.setTrNum("0");
			}

			transData.setFlagPrint(false);
			
			flexLog("Putting java beans into the session");
			session.setAttribute("error", msgError);
			session.setAttribute("trans", trList);
			session.setAttribute("transData", transData);
			session.setAttribute("userPO", userPO);

			if (IsNotError) { // There are no errors
				try {
					if (purpose.equals("READONLY")) {
						forward("EGL0035_inq_transaction_pay.jsp", req, res);
						req.setAttribute("PURPOSE", "READONLY");
					} else {
						forward("EGL0035_transaction_pay.jsp", req, res);
					}
				} catch (Exception e) {
					flexLog("Exception calling page " + e);
				}
			} else { // There are errors
				try {
					if (userPO.getOption().equals("TRANSACTION")) {
						forward("EGL0035_transaction_enter.jsp", req, res);
					} else if (userPO.getOption().equals("CD")) {
						forward("EDL0130_cd_maint.jsp",	req, res);
					} else if (userPO.getOption().equals("LN")) {
						forward("EDL0150_ln_basic.jsp",	req, res);
					} else if (userPO.getOption().equals("PR")) {
						forward("EPR0000_pr_maint.jsp", req, res);
					} else if (userPO.getOption().equals("OCK")) {
						forward("EOF0115_of_chk_basic.jsp",	req, res);
					} else if (
						userPO.getOption().equals("LN_TRANSACTION")) {
							forward("EDL0150_ln_transac.jsp", req, res);
					}
				} catch (Exception e) {
					flexLog("Exception calling page " + e);
				}
			}
		
		} finally {
			if (mp != null)	mp.close();
		}
		
	}
	
}
