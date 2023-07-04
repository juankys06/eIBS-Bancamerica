package datapro.eibs.products;
 
/**
 * Insert the type's description here.
 * Creation date: (1/19/00 6:08:55 PM)
 * @author: David Mavilla
 */
import java.io.*;
import java.net.*;
import java.beans.Beans;
import javax.servlet.*;
import javax.servlet.http.*;
import datapro.eibs.beans.*;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Enumeration;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;

import datapro.eibs.master.Util;

import datapro.eibs.sockets.*;

public class JSERC0000 extends datapro.eibs.master.SuperServlet {

	// Action 
	protected static final int R_ENTER = 1;
	protected static final int R_LIST = 100; //procReqList
	protected static final int R_NEW = 200; //procReqNew
	protected static final int R_VAL = 300; //procReqVal
	protected static final int R_DETAIL = 400;
	protected static final int R_MANT = 500; //procReqMant
	protected static final int R_MANT_VAL = 600; //procReqMantVal

	protected String LangPath = "S";

	/**
	 * JSEIE00000 constructor comment.
	 */
	public JSERC0000() {
		super();
	}

	/**
	 * This method was created by Orestes Garcia.
	 */
	public void destroy() {

		flexLog("free resources used by JSERC0000");

	}

	public void init(ServletConfig config) throws ServletException {
		super.init(config);
	}

	protected void procReqEnter(
		ESS0030DSMessage user,
		HttpServletRequest req,
		HttpServletResponse res,
		HttpSession ses)
		throws ServletException, IOException {

		ELEERRMessage msgError = null;
		UserPos userPO = null;

		try {

			msgError = new ELEERRMessage();
			userPO = new UserPos();
			ses.setAttribute("error", msgError);
			ses.setAttribute("userPO", userPO);

		} catch (Exception ex) {
			flexLog("Error: " + ex);
		}

		try {
			flexLog(
				"About to call Page: "
					+ LangPath
					+ "ERC0000_reconc_new_ent.jsp");
			callPage(LangPath + "ERC0000_reconc_new_ent.jsp", req, res);
		} catch (Exception e) {
			flexLog("Exception calling page " + e);
		}

	}

	// Lista Informacion

	protected void procReqList(
		MessageContext mc,
		ESS0030DSMessage user,
		HttpServletRequest req,
		HttpServletResponse res,
		HttpSession ses)
		throws ServletException, IOException {

		MessageRecord newmessage = null;
		ERC000001Message msgList = null;
		ELEERRMessage msgError = null;
		UserPos userPO = null;
		boolean IsNotError = false;

		try {
			msgError =
				(datapro.eibs.beans.ELEERRMessage) Beans.instantiate(
					getClass().getClassLoader(),
					"datapro.eibs.beans.ELEERRMessage");
		} catch (Exception ex) {
			flexLog("Error: " + ex);
		}

		userPO = (datapro.eibs.beans.UserPos) ses.getAttribute("userPO");

		if (req.getParameter("E01RCIBNK") != null) {
			userPO.setBank(req.getParameter("E01RCIBNK"));
		}

		// Send Initial data
		try {
			msgList = (ERC000001Message) mc.getMessageRecord("ERC000001");
			msgList.setH01USERID(user.getH01USR());
			msgList.setH01PROGRM("ERC0000");
			msgList.setH01TIMSYS(getTimeStamp());
			msgList.setH01SCRCOD("01");
			msgList.setH01OPECOD("0015");
			msgList.setE01RCIBNK(userPO.getBank());

			msgList.send();
			msgList.destroy();
			flexLog("ERC000001 Message Sent");
		} catch (Exception e) {
			e.printStackTrace();
			flexLog("Error: " + e);
			throw new RuntimeException("Socket Communication Error");
		}

		// Receive Data
		try {
			newmessage = mc.receiveMessage();

			if (newmessage.getFormatName().equals("ERC000001")) {

				JBObjList beanList = new JBObjList();

				boolean firstTime = true;
				String marker = "";
				String myFlag = "";
				StringBuffer myRow = null;
				String chk = "";
				String TableTyp = "";
				String chkOfac = "";
				String chkWarn = "";
				int compar = 0;
				int indexRow = 0;
				while (true) {

					msgList = (ERC000001Message) newmessage;

					marker = msgList.getH01FLGMAS();

					if (firstTime) {
						firstTime = false;
						chk = "checked";
					} else {
						chk = "";
					}

					if (marker.equals("*")) {
						beanList.setShowNext(false);
						break;
					} else {

						beanList.addRow(msgList);

						if (marker.equals("+")) {
							beanList.setShowNext(true);
							break;
						}
					}
					newmessage = mc.receiveMessage();
				}

				flexLog("Putting java beans into the session");
				ses.setAttribute("glList", beanList);
				ses.setAttribute("userPO", userPO);

				try {
					flexLog(
						"About to call Page: "
							+ LangPath
							+ "ERC0000_reconc_list_ent.jsp");
					callPage(
						LangPath + "ERC0000_reconc_list_ent.jsp",
						req,
						res);
				} catch (Exception e) {
					flexLog("Exception calling page " + e);
				}

			} else
				flexLog("Message " + newmessage.getFormatName() + " received.");

		} catch (Exception e) {
			e.printStackTrace();
			flexLog("Error: " + e);
			throw new RuntimeException("Socket Communication Data Receiving");
		}

	}

	//Presenta Pantalla de Nuevo

	protected void procReqNew(
		MessageContext mc,
		ESS0030DSMessage user,
		HttpServletRequest req,
		HttpServletResponse res,
		HttpSession ses)
		throws ServletException, IOException {

		MessageRecord newmessage = null;
		ERC000001Message msgList = null;
		ELEERRMessage msgError = null;
		UserPos userPO = null;
		boolean IsNotError = false;

		try {
			msgError = new ELEERRMessage();
		} catch (Exception ex) {
			flexLog("Error: " + ex);
		}
		userPO = (datapro.eibs.beans.UserPos) ses.getAttribute("userPO");

		String rows = null;
		// Send Initial data
		try {
			msgList = (ERC000001Message) mc.getMessageRecord("ERC000001");
			msgList.setH01USERID(user.getH01USR());
			msgList.setH01PROGRM("ERC0000");
			msgList.setH01TIMSYS(getTimeStamp());
			msgList.setH01SCRCOD("01");
			// msgList.setE02ACTION(req.getParameter("action"));
			msgList.setH01OPECOD("0001");

			msgList.send();
			msgList.destroy();
		} catch (Exception e) {
			e.printStackTrace();
			flexLog("Error: " + e);
			throw new RuntimeException("Socket Communication Error");
		}

		// Receive Error Message
		/*try {
			   newmessage = mc.receiveMessage();
		
			   if (newmessage.getFormatName().equals("ELEERR")) {
				   msgError = (ELEERRMessage) newmessage;
				   IsNotError = msgError.getERRNUM().equals("0");
				   flexLog("IsNotError = " + IsNotError);
				   showERROR(msgError);
			   } else
				   flexLog("Message " + newmessage.getFormatName() + " 	.");
		
		} catch (Exception e) {
			   e.printStackTrace();
			   flexLog("Error: " + e);
			   throw new RuntimeException("Socket Communication Error");
		} */  
		
		

		// Receive Data
		try {
			newmessage = mc.receiveMessage();

			if (newmessage.getFormatName().equals("ERC000001")) {
				try {
					msgList = new ERC000001Message();
				} catch (Exception ex) {
					flexLog("Error: " + ex);
				}

				msgList = (ERC000001Message) newmessage;
				userPO.setHeader1(msgList.getE01RCIBNK());
				ArrayList variableParameters = new ArrayList();

				if (req.getParameter("TEMP_ROW") != null
					&& !"".equals(req.getParameter("TEMP_ROW"))) {
					rows = req.getParameter("TEMP_ROW");
					msgList.setE01RCICCY(req.getParameter("E01RCICCY"));
					msgList.setE01RCIGLN(req.getParameter("E01RCIGLN"));
					msgList.setE01RCIACC(req.getParameter("E01RCIACC"));
					msgList.setE01RCISD1(req.getParameter("E01RCISD1"));
					msgList.setE01RCISD2(req.getParameter("E01RCISD2"));
					msgList.setE01RCISD3(req.getParameter("E01RCISD3"));
					msgList.setE01RCISTI(req.getParameter("E01RCISTI"));
					msgList.setE01RCISTF(req.getParameter("E01RCISTF"));
					int total =
						Integer.parseInt(rows)
							+ Integer.parseInt(req.getParameter("total"));
					//req.setAttribute("total",total+"");
					ses.setAttribute("total", total + "");
					for (int i = 0;
						i < (total - Integer.parseInt(rows));
						i++) {
						variableParameters.add(
							req.getParameter("E01RCIBD1" + i + "1"));
						variableParameters.add(
							req.getParameter("E01RCIBD2" + i + "2"));
						variableParameters.add(
							req.getParameter("E01RCIBD3" + i + "3"));
						variableParameters.add(
							req.getParameter("E01RCICKN" + i + "4"));
						variableParameters.add(
							req.getParameter("E01RCIAMD" + i + "5"));
						variableParameters.add(
							req.getParameter("E01RCIAMC" + i + "6"));
						variableParameters.add(
							req.getParameter("E01RCISAT" + i + "7"));
					}
					ses.setAttribute("variableParameters", variableParameters);
				}
				flexLog("Putting java beans into the session");
				ses.setAttribute("error", msgError);
				ses.setAttribute("rows", rows);
				ses.setAttribute("msgList", msgList);

				//if (IsNotError) { // There are no errors
				String value="";
				if(req.getParameter("option")!=null)
					value=req.getParameter("option");
				try {
					if(value.equals("N") || value.equals("")){
						flexLog(
							"About to call Page: "
								+ LangPath
								+ "ERC0000_reconc_new_ent.jsp");
						callPage(LangPath + "ERC0000_reconc_new_ent.jsp", req, res);
					}
					else if (value.equals("M")){
						flexLog(
							"About to call Page: "
								+ LangPath
								+ "ERC0000_reconc_mant_ent.jsp");
						callPage(LangPath + "ERC0000_reconc_mant_ent.jsp", req, res);
					}
				} catch (Exception e) {
					flexLog("Exception calling page " + e);
				}
				/*} else { // There are errors
					   try {
							userPO = (UserPos) ses.getAttribute("userPO");									 
							ses.setAttribute("userPO", userPO);
							procReqList(mc, user, req, res, ses);
					   } catch (Exception e) {
						   flexLog("Exception calling page " + e);
					   }
				}*/
			} else
				flexLog("Message " + newmessage.getFormatName() + " received.");

		} catch (Exception e) {
			e.printStackTrace();
			flexLog("Error: " + e);
			throw new RuntimeException("Socket Communication Error");
		}

	}

	//Validate information 

	protected void procReqVal(
		MessageContext mc,
		ESS0030DSMessage user,
		HttpServletRequest req,
		HttpServletResponse res,
		HttpSession ses)
		throws ServletException, IOException {

		MessageRecord newmessage = null;
		ELEERRMessage msgError = null;
		ERC000001Message msgList = null;
		UserPos userPO = null;
		boolean IsNotError = false;
		userPO = (datapro.eibs.beans.UserPos) ses.getAttribute("userPO");
		//		Send Initial data
		try {
			flexLog("Send Initial Data");
			msgList = (ERC000001Message) mc.getMessageRecord("ERC000001");
			msgList.setH01USERID(user.getH01USR());
			msgList.setH01PROGRM("ERC0000");
			msgList.setH01TIMSYS(getTimeStamp());
			msgList.setH01SCRCOD("01");
			msgList.setH01OPECOD("0005");
			msgList.setH01FLGWK2("N");
			msgList.setE01RCICCY(req.getParameter("E01RCICCY"));
			msgList.setE01RCIGLN(req.getParameter("E01RCIGLN"));
			msgList.setE01RCIACC(req.getParameter("E01RCIACC"));
			msgList.setE01RCISD1(req.getParameter("E01RCISD1"));
			msgList.setE01RCISD2(req.getParameter("E01RCISD2"));
			msgList.setE01RCISD3(req.getParameter("E01RCISD3"));
			msgList.setE01RCISTI(req.getParameter("E01RCISTI"));
			msgList.setE01RCISTF(req.getParameter("E01RCISTF"));
			msgList.setE01RCISAF(req.getParameter("E01RCISAF"));
			ses.setAttribute("msgList",msgList);
			int amount = Integer.parseInt(req.getParameter("total"));
			ArrayList variableParameters = new ArrayList();
			for (int i = 0; i < amount; i++) {
				String e01rcibd1 = req.getParameter("E01RCIBD1" + i + "1");
				String e01rcibd2 = req.getParameter("E01RCIBD2" + i + "2");
				String e01rcibd3 = req.getParameter("E01RCIBD3" + i + "3");
				String e01rcickn4 = req.getParameter("E01RCICKN" + i + "4");
				String e01rciamd5 = req.getParameter("E01RCIAMD" + i + "5");
				String e01rciamc6 = req.getParameter("E01RCIAMC" + i + "6");
				String e01rcisat7 = req.getParameter("E01RCISAT" + i + "7");
				msgList.setE01RCIBD1(e01rcibd1);
				msgList.setE01RCIBD2(e01rcibd2);
				msgList.setE01RCIBD3(e01rcibd3);
				msgList.setE01RCICKN(e01rcickn4);
				msgList.setE01RCIAMD(e01rciamd5);
				msgList.setE01RCIAMC(e01rciamc6);
				msgList.setE01RCISAT(e01rcisat7);
				variableParameters.add(e01rcibd1);
				variableParameters.add(e01rcibd2);
				variableParameters.add(e01rcibd3);
				variableParameters.add(e01rcickn4);
				variableParameters.add(e01rciamd5);
				variableParameters.add(e01rciamc6);
				variableParameters.add(e01rcisat7);
				msgList.setH01FLGMAS(" ");
				msgList.send();
			}
			ses.setAttribute("total",amount+"");
			ses.setAttribute("rows","0");
			ses.setAttribute("variableParameters", variableParameters);
			msgList.setH01FLGMAS("*");
			msgList.send();
			//msgList.destroy();
		} catch (Exception e) {
			e.printStackTrace();
			flexLog("Error: " + e);
			throw new RuntimeException("Socket Communication Error");
		}

		//		 Receive Message
		newmessage = mc.receiveMessage();
		if (newmessage.getFormatName().equals("ELEERR")) {
			msgError = (ELEERRMessage) newmessage;
			IsNotError = msgError.getERRNUM().equals("0");
			flexLog("IsNotError = " + IsNotError);

			if (!IsNotError) { //There is Error
				showERROR(msgError);

				flexLog("Putting java beans into the session");
				ses.setAttribute("error", msgError);
				ses.setAttribute("userPO", userPO);
				
				callPage(LangPath + "ERC0000_reconc_new_ent.jsp", req, res);
			} else {
				ses.removeAttribute("variableParameters");
				this.procReqList(mc, user, req, res, ses);
			}
		}
	}

	//	Shows information 

	protected void procReqMant(
		MessageContext mc,
		ESS0030DSMessage user,
		HttpServletRequest req,
		HttpServletResponse res,
		HttpSession ses)
		throws ServletException, IOException {

		MessageRecord newmessage = null;
		ERC000001Message msgList = null;
		ERC000001Message msgListSend = new ERC000001Message();
		ELEERRMessage msgError = null;
		UserPos userPO = null;
		boolean IsNotError = false;

		try {
			msgError =
				(datapro.eibs.beans.ELEERRMessage) Beans.instantiate(
					getClass().getClassLoader(),
					"datapro.eibs.beans.ELEERRMessage");
		} catch (Exception ex) {
			flexLog("Error: " + ex);
		}
		
		userPO = (datapro.eibs.beans.UserPos) ses.getAttribute("userPO");
		
		// Send Initial data
		try {
			
			String row = null;
			
			if (req.getParameter("CURRCODE") != null)
			{
				row = req.getParameter("CURRCODE");
				userPO.setHeader10(req.getParameter("CURRCODE"));
			}
			else
			{
				row = userPO.getHeader10();
			 }
			 
			msgList = (ERC000001Message) mc.getMessageRecord("ERC000001");
			msgList.setH01USERID(user.getH01USR());
			msgList.setH01PROGRM("ERC0000");
			msgList.setH01TIMSYS(getTimeStamp());
			msgList.setH01SCRCOD("01");
			msgList.setH01OPECOD("0002");
			msgList.setH01FLGWK2("M");
			
			if (req.getParameter("E01RCIBNK"+row) != null)
			{
				userPO.setBank(req.getParameter("E01RCIBNK"+row));
				msgList.setE01RCIBNK(req.getParameter("E01RCIBNK"+row));
				msgList.setE01RCICCY(req.getParameter("E01RCICCY"+row));
				msgList.setE01RCIGLN(req.getParameter("E01RCIGLN"+row));
				msgList.setE01RCIACC(req.getParameter("E01RCIACC"+row));
				msgList.setE01RCISD1(req.getParameter("E01RCISD1"+row));
				msgList.setE01RCISD2(req.getParameter("E01RCISD2"+row));
				msgList.setE01RCISD3(req.getParameter("E01RCISD3"+row));
				msgList.setE01RCISTI(req.getParameter("E01RCISTI"+row));
				msgList.setE01RCISTF(req.getParameter("E01RCISTF"+row));
			
				msgListSend.setE01RCIBNK(req.getParameter("E01RCIBNK"+row));
				msgListSend.setE01RCICCY(req.getParameter("E01RCICCY"+row));
				msgListSend.setE01RCIGLN(req.getParameter("E01RCIGLN"+row));
				msgListSend.setE01RCIACC(req.getParameter("E01RCIACC"+row));
				msgListSend.setE01RCISD1(req.getParameter("E01RCISD1"+row));
				msgListSend.setE01RCISD2(req.getParameter("E01RCISD2"+row));
				msgListSend.setE01RCISD3(req.getParameter("E01RCISD3"+row));
				msgListSend.setE01RCISTI(req.getParameter("E01RCISTI"+row));
				msgListSend.setE01RCISTF(req.getParameter("E01RCISTF"+row));
					
				userPO.setHeader11(req.getParameter("E01RCICCY"+row));
				userPO.setHeader12(req.getParameter("E01RCIGLN"+row));
				userPO.setHeader13(req.getParameter("E01RCIACC"+row));
				userPO.setHeader14(req.getParameter("E01RCISD1"+row));
				userPO.setHeader15(req.getParameter("E01RCISD2"+row));
				userPO.setHeader16(req.getParameter("E01RCISD3"+row));
				userPO.setHeader17(req.getParameter("E01RCISTI"+row));
				userPO.setHeader18(req.getParameter("E01RCISTF"+row));
				


			}
			else
			{

				msgList.setE01RCIBNK(userPO.getBank());
				msgList.setE01RCICCY(userPO.getHeader11());
				msgList.setE01RCIGLN(userPO.getHeader12());
				msgList.setE01RCIACC(userPO.getHeader13());
				msgList.setE01RCISD1(userPO.getHeader14());
				msgList.setE01RCISD2(userPO.getHeader15());
				msgList.setE01RCISD3(userPO.getHeader16());
				msgList.setE01RCISTI(userPO.getHeader17());
				msgList.setE01RCISTF(userPO.getHeader18());
			
				msgListSend.setE01RCIBNK(userPO.getBank());
				msgListSend.setE01RCICCY(userPO.getHeader11());
				msgListSend.setE01RCIGLN(userPO.getHeader12());
				msgListSend.setE01RCIACC(userPO.getHeader13());
				msgListSend.setE01RCISD1(userPO.getHeader14());
				msgListSend.setE01RCISD2(userPO.getHeader15());
				msgListSend.setE01RCISD3(userPO.getHeader16());
				msgListSend.setE01RCISTI(userPO.getHeader17());
				msgListSend.setE01RCISTF(userPO.getHeader18());	

			 }

			msgList.send();
			
			//msgList.destroy();
			flexLog("ERC000001 Message Sent");
		} catch (Exception e) {
			e.printStackTrace();
			flexLog("Error: " + e);
			throw new RuntimeException("Socket Communication Error");
		}

		// Receive Data
		try {
			newmessage = mc.receiveMessage();
			
			if (newmessage.getFormatName().equals("ERC000001")) {
				JBObjList beanList = new JBObjList();

				boolean firstTime = true;
				String marker = "";
				ArrayList variableParameters = new ArrayList();
				int total=0;
				String sFinalTotal="";
				while (true) {
					
					msgList = (ERC000001Message) newmessage;
					sFinalTotal=msgList.getE01RCISAF();
					marker = msgList.getH01FLGMAS();
					
					if (marker.equals("*")) {
						
						break;
					} else {
						
						variableParameters.add(msgList.getE01RCIBD1());
						variableParameters.add(msgList.getE01RCIBD2());
						variableParameters.add(msgList.getE01RCIBD3());
						variableParameters.add(msgList.getE01RCICKN());
						variableParameters.add(msgList.getE01RCIAMD());
						variableParameters.add(msgList.getE01RCIAMC());
						variableParameters.add(msgList.getE01RCISAT());
					}
					newmessage = mc.receiveMessage();
					total++;
				}
				msgListSend.setE01RCISAF(sFinalTotal);
				
				ses.setAttribute("msgList",msgListSend);
				ses.setAttribute("variableParameters", variableParameters);
				
				ses.setAttribute("userPO", userPO);
				flexLog("Putting java beans into the session");

				try {
					ses.setAttribute("total", total + "");
					flexLog(
						"About to call Page: "
							+ LangPath
							+ "ERC0000_reconc_mant_ent.jsp");
					callPage(
						LangPath + "ERC0000_reconc_mant_ent.jsp",
						req,
						res);
				} catch (Exception e) {
					flexLog("Exception calling page " + e);
				}

			} else
				flexLog("Message " + newmessage.getFormatName() + " received.");

		} catch (Exception e) {
			e.printStackTrace();
			flexLog("Error: " + e);
			throw new RuntimeException("Socket Communication Data Receiving");
		}
	}
	
	//Mantenimiento de una transaccion
	protected void procReqMantVal(
			MessageContext mc,
			ESS0030DSMessage user,
			HttpServletRequest req,
			HttpServletResponse res,
			HttpSession ses)
			throws ServletException, IOException {

			MessageRecord newmessage = null;
			ELEERRMessage msgError = null;
			ERC000001Message msgList = null;
			UserPos userPO = null;
			boolean IsNotError = false;
			userPO = (datapro.eibs.beans.UserPos) ses.getAttribute("userPO");
			//		Send Initial data
			try {
				flexLog("Send Initial Data");
				msgList = (ERC000001Message) mc.getMessageRecord("ERC000001");
				msgList.setH01USERID(user.getH01USR());
				msgList.setH01PROGRM("ERC0000");
				msgList.setH01TIMSYS(getTimeStamp());
				msgList.setH01SCRCOD("01");
				msgList.setH01OPECOD("0005");
				msgList.setH01FLGWK2("M");
				msgList.setE01RCIBNK(req.getParameter("E01RCIBNK"));
				msgList.setE01RCICCY(req.getParameter("E01RCICCY"));
				msgList.setE01RCIGLN(req.getParameter("E01RCIGLN"));
				msgList.setE01RCIACC(req.getParameter("E01RCIACC"));
				msgList.setE01RCISD1(req.getParameter("E01RCISD1"));
				msgList.setE01RCISD2(req.getParameter("E01RCISD2"));
				msgList.setE01RCISD3(req.getParameter("E01RCISD3"));
				msgList.setE01RCISTI(req.getParameter("E01RCISTI"));
				msgList.setE01RCISTF(req.getParameter("E01RCISTF"));
				msgList.setE01RCISAF(req.getParameter("E01RCISAF"));
				ses.setAttribute("msgList",msgList);
				int amount = Integer.parseInt(req.getParameter("total"));
				ArrayList variableParameters = new ArrayList();
				for (int i = 0; i < amount; i++) {
					String e01rcibd1 = req.getParameter("E01RCIBD1" + i + "1");
					String e01rcibd2 = req.getParameter("E01RCIBD2" + i + "2");
					String e01rcibd3 = req.getParameter("E01RCIBD3" + i + "3");
					String e01rcickn4 = req.getParameter("E01RCICKN" + i + "4");
					String e01rciamd5 = req.getParameter("E01RCIAMD" + i + "5");
					String e01rciamc6 = req.getParameter("E01RCIAMC" + i + "6");
					String e01rcisat7 = req.getParameter("E01RCISAT" + i + "7");
					msgList.setE01RCIBD1(e01rcibd1);
					msgList.setE01RCIBD2(e01rcibd2);
					msgList.setE01RCIBD3(e01rcibd3);
					msgList.setE01RCICKN(e01rcickn4);
					msgList.setE01RCIAMD(e01rciamd5);
					msgList.setE01RCIAMC(e01rciamc6);
					msgList.setE01RCISAT(e01rcisat7);
					variableParameters.add(e01rcibd1);
					variableParameters.add(e01rcibd2);
					variableParameters.add(e01rcibd3);
					variableParameters.add(e01rcickn4);
					variableParameters.add(e01rciamd5);
					variableParameters.add(e01rciamc6);
					variableParameters.add(e01rcisat7);
					msgList.setH01FLGMAS(" ");
					msgList.send();
				}
				ses.setAttribute("total",amount+"");
				ses.setAttribute("rows","0");
				ses.setAttribute("variableParameters", variableParameters);
				msgList.setH01FLGMAS("*");
				msgList.send();
				//msgList.destroy();
			} catch (Exception e) {
				e.printStackTrace();
				flexLog("Error: " + e);
				throw new RuntimeException("Socket Communication Error");
			}

			//		 Receive Message
			newmessage = mc.receiveMessage();
			if (newmessage.getFormatName().equals("ELEERR")) {
				msgError = (ELEERRMessage) newmessage;
				IsNotError = msgError.getERRNUM().equals("0");
				flexLog("IsNotError = " + IsNotError);

				if (!IsNotError) { //There is Error
					showERROR(msgError);

					flexLog("Putting java beans into the session");
					ses.setAttribute("error", msgError);
					ses.setAttribute("userPO", userPO);
					if (msgError.getERNU01().equals("1208"))
					{
						//res.sendRedirect(super.srctx + "/servlet/datapro.eibs.products.JSERC0000?SCREEN=500");
						procReqMant(mc, user, req, res, ses);
						
					}
					else
					{	
						callPage(LangPath + "ERC0000_reconc_mant_ent.jsp", req, res);
					}
					

				} else {
					ses.removeAttribute("variableParameters");
					this.procReqList(mc, user, req, res, ses);
				}
			}
		}
	protected void procReqDetail(
		MessageContext mc,
		ESS0030DSMessage user,
		HttpServletRequest req,
		HttpServletResponse res,
		HttpSession ses)
		throws ServletException, IOException {

		ERC000001Message msgRow = null;
		MessageRecord newmessage = null;
		UserPos userPO = null;

		userPO = (datapro.eibs.beans.UserPos) ses.getAttribute("userPO");

		/* // Receive Record Data
		try {
			String opt = (String) req.getParameter("opt");
			
			JBObjList bl = (JBObjList) ses.getAttribute("glList");
			int idx = Integer.parseInt(req.getParameter("CURRCODE"));
			bl.setCurrentRow(idx);
		
			msgRow = (ERC000001Message) bl.getRecord();
		
			// Send Initial data
			try {
				msgDet = (ERC000002Message) mc.getMessageRecord("ERC000002");
				msgDet.setH02USERID(user.getH01USR());
				msgDet.setH02PROGRM("ERC0000");
				msgDet.setH02TIMSYS(getTimeStamp());
				msgDet.setH02SCRCOD("01");
				msgDet.setH02OPECOD("0015");
				
				userPO.setHeader10(msgRow.getE01GLMGLN());
				userPO.setHeader20(msgRow.getE01GLMBBL());
				
				msgDet.setE02GLMBNK(msgRow.getE01GLMBNK());
				msgDet.setE02GLMCCY(msgRow.getE01GLMCCY());
				msgDet.setE02GLMGLN(msgRow.getE01GLMGLN());
				msgDet.setE02GLMBBL(msgRow.getE01GLMBBL());
				msgDet.setE02FLGENV(opt);
				
				msgDet.send();
				msgDet.destroy();
				flexLog("ERC000002 Message Sent");
			} catch (Exception e) {
				e.printStackTrace();
				flexLog("Error: " + e);
				throw new RuntimeException("Socket Communication Error");
			}
		
			// Receive Data
			try {
				newmessage = mc.receiveMessage();
		
				if (newmessage.getFormatName().equals("ERC000002")) {
		
					JBObjList beanList = new JBObjList();
		
					boolean firstTime = true;
					String marker = "";
					String myFlag = "";
					StringBuffer myRow = null;
					String chk = "";
					String TableTyp = "";
					String chkOfac = "";
					String chkWarn = "";
					int compar = 0;
					int indexRow = 0;
					while (true) {
		
						msgList = (ERC000002Message) newmessage;
		
						marker = msgList.getH02FLGMAS();
		
						if (firstTime) {
							firstTime = false;
							chk = "checked";
						} else {
							chk = "";
						}
		
						if (marker.equals("*")) {
							beanList.setShowNext(false);
							break;
						} else {
		
							beanList.addRow(msgList);
		
							if (marker.equals("+")) {
								beanList.setShowNext(true);
								break;
							}
						}
						newmessage = mc.receiveMessage();
					}
		
					flexLog("Putting java beans into the session");
					ses.setAttribute("glDetail", beanList);
					ses.setAttribute("userPO", userPO);
		
					try {
						flexLog(
							"About to call Page: " + LangPath + "ERC0000_gl_detail.jsp");
						callPage(LangPath + "ERC0000_gl_detail.jsp", req, res);
					} catch (Exception e) {
						flexLog("Exception calling page " + e);
					}
		
				} else
					flexLog("Message " + newmessage.getFormatName() + " received.");
		
			} catch (Exception e) {
				e.printStackTrace();
				flexLog("Error: " + e);
				throw new RuntimeException("Socket Communication Data Receiving");
			}
		} catch (Exception e) {
			e.printStackTrace();
			flexLog("Error: " + e);
			throw new RuntimeException("Socket Communication Error");
		}  */
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

				msgUser =
					(datapro.eibs.beans.ESS0030DSMessage) session.getAttribute(
						"currUser");

				// Here we should get the path from the user profile
				LangPath = super.rootPath + msgUser.getE01LAN() + "/";

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
						screen = Integer.parseInt(req.getParameter("SCREEN"));
					} catch (Exception e) {
						flexLog("Screen set to default value");
					}
					switch (screen) {

						//Request
						case R_ENTER :
							procReqEnter(msgUser, req, res, session);
							break;
						case R_LIST :
							procReqList(mc, msgUser, req, res, session);
							break;
						case R_NEW :
							procReqNew(mc, msgUser, req, res, session);
							break;
						case R_VAL :
							procReqVal(mc, msgUser, req, res, session);
							break;
						case R_MANT :
							procReqMant(mc, msgUser, req, res, session);
							break;
						case R_MANT_VAL :
							procReqMantVal(mc, msgUser, req, res, session);
							break;
						case R_DETAIL :
							procReqDetail(mc, msgUser, req, res, session);
							break;
							// END Entering
						default :
							res.sendRedirect(
								super.srctx + LangPath + super.devPage);
							break;
					}

				} catch (Exception e) {
					e.printStackTrace();
					int sck = getInitSocket(req) + 1;
					flexLog("Socket not Open(Port " + sck + "). Error: " + e);
					res.sendRedirect(
						super.srctx + LangPath + super.sckNotOpenPage);
					//return;
				} finally {
					s.close();
				}
			} catch (Exception e) {
				flexLog("Error: " + e);
				res.sendRedirect(
					super.srctx + LangPath + super.sckNotRespondPage);
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

}
