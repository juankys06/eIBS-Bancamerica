package datapro.eibs.products;

/**
 * Insert the type's description here.
 * Creation date: (1/19/00 6:08:55 PM)
 * @author: David Mavilla
 */
import java.beans.Beans;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import datapro.eibs.beans.DataTransaction;
import datapro.eibs.beans.EDL013001Message;
import datapro.eibs.beans.EDL013003Message;
import datapro.eibs.beans.EDL013004Message;
import datapro.eibs.beans.EDL013006Message;
import datapro.eibs.beans.EDL013007Message;
import datapro.eibs.beans.EDL013008Message;
import datapro.eibs.beans.EDL013009Message;
import datapro.eibs.beans.ELD000001Message;
import datapro.eibs.beans.ELEERRMessage;
import datapro.eibs.beans.ESD000002Message;
import datapro.eibs.beans.ESD000004Message;
import datapro.eibs.beans.ESD000005Message;
import datapro.eibs.beans.ESD000006Message;
import datapro.eibs.beans.ESD000008Message;
import datapro.eibs.beans.ESS0030DSMessage;
import datapro.eibs.beans.ESWF10001Message;
import datapro.eibs.beans.ESWF10002Message;
import datapro.eibs.beans.JBListRec;
import datapro.eibs.beans.UserPos;
import datapro.eibs.master.SuperServlet;
import datapro.eibs.master.Util;
import datapro.eibs.sockets.MessageContext;
import datapro.eibs.sockets.MessageRecord;

public class JSEDL0130A extends datapro.eibs.master.SuperServlet
{

	// certificate of deposit 
	protected static final int R_NEW = 1;
	protected static final int A_NEW = 2;
	protected static final int R_MAINTENANCE = 3;
	protected static final int A_MAINTENANCE = 4;
	protected static final int R_TRANSACTION = 5;
	protected static final int A_TRANSACTION = 6;
	protected static final int R_TITULARES = 7;
	protected static final int A_TITULARES = 8;
	protected static final int R_PAYMENT_PLAN = 9;
	protected static final int A_PAYMENT_PLAN = 10;
	protected static final int R_PRECANCEL = 11;
	protected static final int A_PRECANCEL = 12;
	protected static final int R_SPECIAL_INST = 15;
	protected static final int A_SPECIAL_INST = 16;
	protected static final int R_BENEFICIARY = 17;
	protected static final int A_BENEFICIARY = 18;
	protected static final int R_EXCHANGE = 19;
	protected static final int A_EXCHANGE = 20;
	protected static final int R_CODES = 21;
	protected static final int A_CODES = 22;
	protected static final int R_RENOV_MANT = 33;
	protected static final int A_RENOV_MANT = 34;
	protected static final int R_RENOV_OPEN = 35;
	protected static final int A_RENOV_OPEN = 36;
	protected static final int R_FINISH = 37;
	protected static final int A_FINISH = 38;
	protected static final int R_INT_PREP = 39;
	protected static final int A_INT_PREP = 40;
	protected static final int R_DISBUSERMENT = 48;
	protected static final int R_MONEY = 41;
	protected static final int R_OTHERS_TRANS = 47;
	protected static final int R_CD_SIGNERS = 55;

	//inquiry options
	protected static final int R_INQUIRY = 13;
	protected static final int R_CODES_INQ = 27;
	protected static final int R_TITULARES_INQ = 29;
	protected static final int R_SPECIAL_INST_INQ = 31;
	protected static final int R_BASIC_INQ = 41;
	protected static final int R_BASIC_CONTR = 42;
	protected static final int R_PROD_INQ = 43;
	protected static final int R_BENE_INQ = 44;
	protected static final int R_CALC_INQ = 45;
	protected static final int R_GARANT_INQ = 46;

	protected static final int R_ACCOUNT_TITLE = 57;

	//SWIFT
	protected static final int R_SWIFT100 = 61;
	protected static final int R_SWIFT320 = 63;

	// entering options
	protected static final int R_ENTER_NEW = 100;
	protected static final int R_ENTER_MAINT = 200;
	protected static final int R_ENTER_INQUIRY = 300;
	protected static final int R_ENTER_CANCEL = 700;
	protected static final int R_ENTER_TRANSAC = 900;
	protected static final int R_ENTER_PRINT = 1100;

	protected static final int A_ENTER_NEW = 400;
	protected static final int A_ENTER_MAINT = 500;
	protected static final int A_ENTER_INQUIRY = 600;
	protected static final int A_ENTER_CANCEL = 800;
	protected static final int A_ENTER_TRANSAC = 1000;
	protected static final int A_ENTER_PRINT = 1200;

	protected String LangPath = "S";

	/**
	 * JSECLI001 constructor comment.
	 */
	public JSEDL0130A()
	{
		super();
	}
	/**
	 * This method was created by Orestes Garcia.
	 */
	public void destroy()
	{

		flexLog("free resources used by JSEDL0130");

	}
	/**
	 * This method was created by David Mavilla.
	 */
	public void init(ServletConfig config) throws ServletException
	{
		super.init(config);
	}
	/**
	 * This method was created in VisualAge.
	 */
	protected void procActionEnterMaint(
		MessageContext mc,
		ESS0030DSMessage user,
		HttpServletRequest req,
		HttpServletResponse res,
		HttpSession ses)
		throws ServletException, IOException
	{

		MessageRecord newmessage = null;
		EDL013001Message msgCD = null;
		ELEERRMessage msgError = null;
		UserPos userPO = null;
		boolean IsNotError = false;

		try
		{
			msgError = new datapro.eibs.beans.ELEERRMessage();
		}
		catch (Exception ex)
		{
			flexLog("Error: " + ex);
		}

		userPO = (datapro.eibs.beans.UserPos) ses.getAttribute("userPO");

		// Send Initial data
		try
		{
			msgCD = (EDL013001Message) mc.getMessageRecord("EDL013001");
			msgCD.setH01USERID(user.getH01USR());
			msgCD.setH01PROGRM("EDL0130");
			msgCD.setH01TIMSYS(getTimeStamp());
			msgCD.setH01SCRCOD("01");
			msgCD.setH01FLGWK1("A");
			msgCD.setH01OPECOD("0004");
			try
			{
				msgCD.setE01DEAACC(req.getParameter("E01DEAACC"));
			}
			catch (Exception e)
			{
				msgCD.setE01DEAACC("0");
			}

			msgCD.send();
			msgCD.destroy();
		}
		catch (Exception e)
		{
			e.printStackTrace();
			flexLog("Error: " + e);
			throw new RuntimeException("Socket Communication Error");
		}

		// Receive Error Message
		try
		{
			newmessage = mc.receiveMessage();

			if (newmessage.getFormatName().equals("ELEERR"))
			{
				msgError = (ELEERRMessage) newmessage;
				IsNotError = msgError.getERRNUM().equals("0");
				flexLog("IsNotError = " + IsNotError);
				showERROR(msgError);
			}
			else
				flexLog("Message " + newmessage.getFormatName() + " received.");

		}
		catch (Exception e)
		{
			e.printStackTrace();
			flexLog("Error: " + e);
			throw new RuntimeException("Socket Communication Error");
		}

		// Receive Data
		try
		{
			newmessage = mc.receiveMessage();

			if (newmessage.getFormatName().equals("EDL013001"))
			{
				try
				{
					msgCD = new datapro.eibs.beans.EDL013001Message();
				}
				catch (Exception ex)
				{
					flexLog("Error: " + ex);
				}

				msgCD = (EDL013001Message) newmessage;

				userPO.setIdentifier(msgCD.getE01DEAACC());
				userPO.setHeader1(msgCD.getE01DEAPRO());
				userPO.setHeader2(msgCD.getE01DEACUN());
				userPO.setHeader3(msgCD.getE01CUSNA1());
				userPO.setCurrency(msgCD.getE01DEACCY());
				userPO.setHeader16(msgCD.getE01DEAPVI());

				flexLog("Putting java beans into the session");
				ses.setAttribute("error", msgError);
				ses.setAttribute("userPO", userPO);
				ses.setAttribute("cdMant", msgCD);

				if (IsNotError)
				{ // There are no errors
					try
					{
						flexLog("About to call Page: " + LangPath + "EDL0140_cd_ap_maint.jsp");
						callPage(LangPath + "EDL0140_cd_ap_maint.jsp", req, res);
					}
					catch (Exception e)
					{
						flexLog("Exception calling page " + e);
					}
				}
				else
				{ // There are errors
					try
					{
						flexLog("About to call Page: " + LangPath + "error_viewer.jsp");
						callPage(LangPath + "error_viewer.jsp", req, res);
					}
					catch (Exception e)
					{
						flexLog("Exception calling page " + e);
					}
				}
			}
			else
				flexLog("Message " + newmessage.getFormatName() + " received.");

		}
		catch (Exception e)
		{
			e.printStackTrace();
			flexLog("Error: " + e);
			throw new RuntimeException("Socket Communication Error");
		}

	}
	/**
	 * This method was created in VisualAge.
	 */
	protected void procReqBene(
		MessageContext mc,
		ESS0030DSMessage user,
		HttpServletRequest req,
		HttpServletResponse res,
		HttpSession ses)
		throws ServletException, IOException
	{

		MessageRecord newmessage = null;
		ESD000004Message msgBene = null;
		ELEERRMessage msgError = null;
		UserPos userPO = null;
		boolean IsNotError = false;

		try
		{
			msgError = new datapro.eibs.beans.ELEERRMessage();
		}
		catch (Exception ex)
		{
			flexLog("Error: " + ex);
		}

		userPO = (datapro.eibs.beans.UserPos) ses.getAttribute("userPO");

		String opCode = null;

		// Send Initial data
		try
		{
			msgBene = (ESD000004Message) mc.getMessageRecord("ESD000004");
			msgBene.setH04USR(user.getH01USR());
			msgBene.setH04PGM("EDL0140");
			msgBene.setH04TIM(getTimeStamp());
			msgBene.setH04SCR("01");
			msgBene.setH04OPE("0004");
			msgBene.setE04CUN(userPO.getIdentifier());
			msgBene.setE04RTP("J");
			msgBene.send();
			msgBene.destroy();
		}
		catch (Exception e)
		{
			e.printStackTrace();
			flexLog("Error: " + e);
			throw new RuntimeException("Socket Communication Error");
		}

		// Receive Error Message
		try
		{
			newmessage = mc.receiveMessage();

			if (newmessage.getFormatName().equals("ELEERR"))
			{
				msgError = (ELEERRMessage) newmessage;
				IsNotError = msgError.getERRNUM().equals("0");
				flexLog("IsNotError = " + IsNotError);
				showERROR(msgError);
			}
			else
				flexLog("Message " + newmessage.getFormatName() + " received.");

		}
		catch (Exception e)
		{
			e.printStackTrace();
			flexLog("Error: " + e);
			throw new RuntimeException("Socket Communication Error");
		}

		// Receive Data
		try
		{
			newmessage = mc.receiveMessage();

			if (newmessage.getFormatName().equals("ESD000004"))
			{
				try
				{
					msgBene = new datapro.eibs.beans.ESD000004Message();
				}
				catch (Exception ex)
				{
					flexLog("Error: " + ex);
				}

				msgBene = (ESD000004Message) newmessage;
				// showESD000004(msgBene);

				flexLog("Putting java beans into the session");
				ses.setAttribute("error", msgError);
				ses.setAttribute("bene", msgBene);

				if (IsNotError)
				{ // There are no errors 
					try
					{
						flexLog(
							"About to call Page: "
								+ LangPath
								+ "EDL0140_cd_ap_bene.jsp");
						callPage(LangPath + "EDL0140_cd_ap_bene.jsp", req, res);
					}
					catch (Exception e)
					{
						flexLog("Exception calling page " + e);
					}
				}
				else
				{ // There are errors
					try
					{
						flexLog(
							"About to call Page: "
								+ LangPath
								+ "EDL0140_cd_ap_maint.jsp");
						callPage(
							LangPath + "EDL0140_cd_ap_maint.jsp",
							req,
							res);
					}
					catch (Exception e)
					{
						flexLog("Exception calling page " + e);
					}
				}
			}
			else
				flexLog("Message " + newmessage.getFormatName() + " received.");

		}
		catch (Exception e)
		{
			e.printStackTrace();
			flexLog("Error: " + e);
			throw new RuntimeException("Socket Communication Error");
		}

	}
	/**
	 * This method was created in VisualAge.
	 */
	protected void procReqEnterMaint(
		ESS0030DSMessage user,
		HttpServletRequest req,
		HttpServletResponse res,
		HttpSession ses)
		throws ServletException, IOException
	{

		ELEERRMessage msgError = null;
		UserPos userPO = null;

		try
		{

			msgError = new datapro.eibs.beans.ELEERRMessage();
			userPO = new datapro.eibs.beans.UserPos();
			userPO.setOption("CD");
			userPO.setPurpose("APPROVAL");
			ses.setAttribute("error", msgError);
			ses.setAttribute("userPO", userPO);

		}
		catch (Exception ex)
		{
			flexLog("Error: " + ex);
		}

		try
		{
			flexLog(
				"About to call Page: "
					+ LangPath
					+ "EDL0130_cd_enter_maint.jsp");
			callPage(LangPath + "EDL0130_cd_enter_maint.jsp", req, res);
		}
		catch (Exception e)
		{
			flexLog("Exception calling page " + e);
		}

	}
	/**
	 * This method was created in VisualAge.
	 */
	protected void procReqEspInst(
		MessageContext mc,
		ESS0030DSMessage user,
		HttpServletRequest req,
		HttpServletResponse res,
		HttpSession ses)
		throws ServletException, IOException
	{

		MessageRecord newmessage = null;
		ESD000005Message msgCD = null;
		ELEERRMessage msgError = null;
		UserPos userPO = null;
		boolean IsNotError = false;

		try
		{
			msgError = new datapro.eibs.beans.ELEERRMessage();
		}
		catch (Exception ex)
		{
			flexLog("Error: " + ex);
		}

		userPO = (datapro.eibs.beans.UserPos) ses.getAttribute("userPO");

		String opCode = "0004";

		// Send Initial data
		try
		{
			msgCD = (ESD000005Message) mc.getMessageRecord("ESD000005");
			msgCD.setH05USR(user.getH01USR());
			msgCD.setH05PGM("EDL0130");
			msgCD.setH05TIM(getTimeStamp());
			msgCD.setH05SCR("01");
			msgCD.setH05OPE(opCode);
			msgCD.setE05ACC(userPO.getIdentifier());
			msgCD.setE05TYP("2");
			msgCD.send();
			msgCD.destroy();
		}
		catch (Exception e)
		{
			e.printStackTrace();
			flexLog("Error: " + e);
			throw new RuntimeException("Socket Communication Error");
		}

		// Receive Error Message
		try
		{
			newmessage = mc.receiveMessage();

			if (newmessage.getFormatName().equals("ELEERR"))
			{
				msgError = (ELEERRMessage) newmessage;
				IsNotError = msgError.getERRNUM().equals("0");
				flexLog("IsNotError = " + IsNotError);
				showERROR(msgError);
			}
			else
				flexLog("Message " + newmessage.getFormatName() + " received.");

		}
		catch (Exception e)
		{
			e.printStackTrace();
			flexLog("Error: " + e);
			throw new RuntimeException("Socket Communication Error");
		}

		// Receive Data
		try
		{
			newmessage = mc.receiveMessage();

			if (newmessage.getFormatName().equals("ESD000005"))
			{
				try
				{
					msgCD = new datapro.eibs.beans.ESD000005Message();
				}
				catch (Exception ex)
				{
					flexLog("Error: " + ex);
				}

				msgCD = (ESD000005Message) newmessage;
				// showESD008004(msgCD);

				flexLog("Putting java beans into the session");
				ses.setAttribute("error", msgError);
				ses.setAttribute("cdInst", msgCD);

				if (IsNotError)
				{ // There are no errors 
					try
					{
						flexLog(
							"About to call Page: "
								+ LangPath
								+ "EDL0140_cd_ap_special_inst.jsp");
						callPage(
							LangPath + "EDL0140_cd_ap_special_inst.jsp",
							req,
							res);
					}
					catch (Exception e)
					{
						flexLog("Exception calling page " + e);
					}
				}
				else
				{ // There are errors
					try
					{
						flexLog(
							"About to call Page: "
								+ LangPath
								+ "EDL0140_cd_ap_maint.jsp");
						callPage(
							LangPath + "EDL0140_cd_ap_maint.jsp",
							req,
							res);
					}
					catch (Exception e)
					{
						flexLog("Exception calling page " + e);
					}
				}
			}
			else
				flexLog("Message " + newmessage.getFormatName() + " received.");

		}
		catch (Exception e)
		{
			e.printStackTrace();
			flexLog("Error: " + e);
			throw new RuntimeException("Socket Communication Error");
		}

	}
	/**
	 * This method was created in VisualAge.
	 */
	protected void procReqExchangeRate(
		MessageContext mc,
		ESS0030DSMessage user,
		HttpServletRequest req,
		HttpServletResponse res,
		HttpSession ses)
		throws ServletException, IOException
	{

		MessageRecord newmessage = null;
		EDL013006Message msgCD = null;
		ELEERRMessage msgError = null;
		UserPos userPO = null;
		boolean IsNotError = false;

		try
		{
			msgError = new datapro.eibs.beans.ELEERRMessage();
		}
		catch (Exception ex)
		{
			flexLog("Error: " + ex);
		}

		userPO = (datapro.eibs.beans.UserPos) ses.getAttribute("userPO");

		String opCode = "0004";

		// Send Initial data
		try
		{
			msgCD = (EDL013006Message) mc.getMessageRecord("EDL013006");
			msgCD.setH06USERID(user.getH01USR());
			msgCD.setH06PROGRM("EDL0130");
			msgCD.setH06TIMSYS(getTimeStamp());
			msgCD.setH06SCRCOD("01");
			msgCD.setH06OPECOD(opCode);
			msgCD.setE06DEAACC(userPO.getIdentifier());
			msgCD.send();
			msgCD.destroy();
		}
		catch (Exception e)
		{
			e.printStackTrace();
			flexLog("Error: " + e);
			throw new RuntimeException("Socket Communication Error");
		}

		// Receive Error Message
		try
		{
			newmessage = mc.receiveMessage();

			if (newmessage.getFormatName().equals("ELEERR"))
			{
				msgError = (ELEERRMessage) newmessage;
				IsNotError = msgError.getERRNUM().equals("0");
				flexLog("IsNotError = " + IsNotError);
				showERROR(msgError);
			}
			else
				flexLog("Message " + newmessage.getFormatName() + " received.");

		}
		catch (Exception e)
		{
			e.printStackTrace();
			flexLog("Error: " + e);
			throw new RuntimeException("Socket Communication Error");
		}

		// Receive Data
		try
		{
			newmessage = mc.receiveMessage();

			if (newmessage.getFormatName().equals("EDL013006"))
			{
				try
				{
					msgCD = new datapro.eibs.beans.EDL013006Message();
				}
				catch (Exception ex)
				{
					flexLog("Error: " + ex);
				}

				msgCD = (EDL013006Message) newmessage;
				// showESD008004(msgCD);

				flexLog("Putting java beans into the session");
				ses.setAttribute("error", msgError);
				ses.setAttribute("cdRate", msgCD);

				if (IsNotError)
				{ // There are no errors
					try
					{
						flexLog(
							"About to call Page: "
								+ LangPath
								+ "EDL0140_cd_ap_xchg_rate.jsp");
						callPage(
							LangPath + "EDL0140_cd_ap_xchg_rate.jsp",
							req,
							res);
					}
					catch (Exception e)
					{
						flexLog("Exception calling page " + e);
					}
				}
				else
				{ // There are errors
					try
					{
						flexLog(
							"About to call Page: "
								+ LangPath
								+ "EDL0140_cd_ap_maint.jsp");
						callPage(
							LangPath + "EDL0140_cd_ap_maint.jsp",
							req,
							res);
					}
					catch (Exception e)
					{
						flexLog("Exception calling page " + e);
					}
				}
			}
			else
				flexLog("Message " + newmessage.getFormatName() + " received.");

		}
		catch (Exception e)
		{
			e.printStackTrace();
			flexLog("Error: " + e);
			throw new RuntimeException("Socket Communication Error");
		}

	}
	/**
	 * This method was created in VisualAge.
	 */
	protected void procReqIntPrep(
		MessageContext mc,
		ESS0030DSMessage user,
		HttpServletRequest req,
		HttpServletResponse res,
		HttpSession ses)
		throws ServletException, IOException
	{
		MessageRecord newmessage = null;
		EDL013009Message msgCD = null;
		ELEERRMessage msgError = null;
		UserPos userPO = null;
		boolean IsNotError = false;
		try
		{
			msgError = new datapro.eibs.beans.ELEERRMessage();
		}
		catch (Exception ex)
		{
			flexLog("Error: " + ex);
		}
		userPO = (datapro.eibs.beans.UserPos) ses.getAttribute("userPO");
		String opCode = "0004";

		// Send Initial data
		try
		{
			msgCD = (EDL013009Message) mc.getMessageRecord("EDL013009");
			msgCD.setH09USERID(user.getH01USR());
			msgCD.setH09PROGRM("EDL0130");
			msgCD.setH09TIMSYS(getTimeStamp());
			msgCD.setH09SCRCOD("01");
			msgCD.setH09OPECOD(opCode);
			msgCD.setE09DEAACC(userPO.getIdentifier());
			msgCD.send();
			msgCD.destroy();
		}
		catch (Exception e)
		{
			e.printStackTrace();
			flexLog("Error: " + e);
			throw new RuntimeException("Socket Communication Error");
		}

		// Receive Error Message
		try
		{
			newmessage = mc.receiveMessage();
			if (newmessage.getFormatName().equals("ELEERR"))
			{
				msgError = (ELEERRMessage) newmessage;
				IsNotError = msgError.getERRNUM().equals("0");
				flexLog("IsNotError = " + IsNotError);
				showERROR(msgError);
			}
			else
				flexLog("Message " + newmessage.getFormatName() + " received.");
		}
		catch (Exception e)
		{
			e.printStackTrace();
			flexLog("Error: " + e);
			throw new RuntimeException("Socket Communication Error");
		}

		// Receive Data
		try
		{
			newmessage = mc.receiveMessage();
			if (newmessage.getFormatName().equals("EDL013009"))
			{
				try
				{
					msgCD = new datapro.eibs.beans.EDL013009Message();
				}
				catch (Exception ex)
				{
					flexLog("Error: " + ex);
				}
				msgCD = (EDL013009Message) newmessage;
				// showESD008004(msgCD);

				flexLog("Putting java beans into the session");
				ses.setAttribute("error", msgError);
				ses.setAttribute("cdIntPrep", msgCD);
				if (IsNotError)
				{ // There are no errors
					try
					{
						flexLog(
							"About to call Page: "
								+ LangPath
								+ "EDL0130_cd_prep_int.jsp");
						callPage(
							LangPath + "EDL0130_cd_prep_int.jsp",
							req,
							res);
					}
					catch (Exception e)
					{
						flexLog("Exception calling page " + e);
					}
				}
				else
				{ // There are errors
					try
					{
						flexLog(
							"About to call Page: "
								+ LangPath
								+ "EDL0130_cd_finish.jsp");
						callPage(LangPath + "EDL0130_cd_finish.jsp", req, res);
					}
					catch (Exception e)
					{
						flexLog("Exception calling page " + e);
					}
				}
			}
			else
				flexLog("Message " + newmessage.getFormatName() + " received.");
		}
		catch (Exception e)
		{
			e.printStackTrace();
			flexLog("Error: " + e);
			throw new RuntimeException("Socket Communication Error");
		}
	}
	/**
	 * This method was created in VisualAge.
	 */
	protected void procReqMaintenance(
		MessageContext mc,
		ESS0030DSMessage user,
		HttpServletRequest req,
		HttpServletResponse res,
		HttpSession ses)
		throws ServletException, IOException
	{

		MessageRecord newmessage = null;
		EDL013001Message msgCD = null;
		ELEERRMessage msgError = null;
		UserPos userPO = null;
		boolean IsNotError = false;

		try
		{
			msgError = new datapro.eibs.beans.ELEERRMessage();
		}
		catch (Exception ex)
		{
			flexLog("Error: " + ex);
		}

		userPO = (datapro.eibs.beans.UserPos) ses.getAttribute("userPO");

		String opCode = null;
		opCode = "0004";

		// Send Initial data
		try
		{
			msgCD = (EDL013001Message) mc.getMessageRecord("EDL013001");
			msgCD.setH01USERID(user.getH01USR());
			msgCD.setH01PROGRM("EDL0130");
			msgCD.setH01TIMSYS(getTimeStamp());
			msgCD.setH01SCRCOD("01");
			msgCD.setH01OPECOD(opCode);
			msgCD.setH01FLGWK1("A");
			msgCD.setE01DEAACC(userPO.getIdentifier());
			msgCD.send();
			msgCD.destroy();
		}
		catch (Exception e)
		{
			e.printStackTrace();
			flexLog("Error: " + e);
			throw new RuntimeException("Socket Communication Error");
		}

		// Receive Error Message
		try
		{
			newmessage = mc.receiveMessage();

			if (newmessage.getFormatName().equals("ELEERR"))
			{
				msgError = (ELEERRMessage) newmessage;
				IsNotError = msgError.getERRNUM().equals("0");
				flexLog("IsNotError = " + IsNotError);
				showERROR(msgError);
			}
			else
				flexLog("Message " + newmessage.getFormatName() + " received.");

		}
		catch (Exception e)
		{
			e.printStackTrace();
			flexLog("Error: " + e);
			throw new RuntimeException("Socket Communication Error");
		}

		// Receive Data
		try
		{
			newmessage = mc.receiveMessage();

			if (newmessage.getFormatName().equals("EDL013001"))
			{
				try
				{
					msgCD = new datapro.eibs.beans.EDL013001Message();
				}
				catch (Exception ex)
				{
					flexLog("Error: " + ex);
				}

				msgCD = (EDL013001Message) newmessage;
				// showESD008004(msgCD);

				flexLog("Putting java beans into the session");
				ses.setAttribute("error", msgError);
				ses.setAttribute("cdMant", msgCD);

				if (IsNotError)
				{ // There are no errors
					try
					{
						flexLog(
							"About to call Page3: "
								+ LangPath
								+ "EDL0140_cd_ap_maint.jsp");
						callPage(
							LangPath + "EDL0140_cd_ap_maint.jsp",
							req,
							res);
					}
					catch (Exception e)
					{
						flexLog("Exception calling page " + e);
					}
				}
				else
				{ // There are errors
					try
					{
						flexLog(
							"About to call Page: "
								+ LangPath
								+ "error_viewer.jsp");
						callPage(LangPath + "error_viewer.jsp", req, res);
					}
					catch (Exception e)
					{
						flexLog("Exception calling page " + e);
					}
				}
			}
			else
				flexLog("Message " + newmessage.getFormatName() + " received.");

		}
		catch (Exception e)
		{
			e.printStackTrace();
			flexLog("Error: " + e);
			throw new RuntimeException("Socket Communication Error");
		}

	}
	/**
	 * This method was created in VisualAge.
	 */
	protected void procReqPreCancel(
		MessageContext mc,
		ESS0030DSMessage user,
		HttpServletRequest req,
		HttpServletResponse res,
		HttpSession ses)
		throws ServletException, IOException
	{

		MessageRecord newmessage = null;
		EDL013007Message msgCD = null;
		ELEERRMessage msgError = null;
		UserPos userPO = null;
		boolean IsNotError = false;

		try
		{
			msgError = new datapro.eibs.beans.ELEERRMessage();
		}
		catch (Exception ex)
		{
			flexLog("Error: " + ex);
		}

		userPO = (datapro.eibs.beans.UserPos) ses.getAttribute("userPO");

		String opCode = null;
		opCode = "0004";

		// Send Initial data
		try
		{
			msgCD = (EDL013007Message) mc.getMessageRecord("EDL013007");
			msgCD.setH07USERID(user.getH01USR());
			msgCD.setH07PROGRM("EDL0130");
			msgCD.setH07TIMSYS(getTimeStamp());
			msgCD.setH07SCRCOD("01");
			msgCD.setH07OPECOD(opCode);
			msgCD.setH07FLGWK1("A");
			msgCD.setE07DEAACC(req.getParameter("E01DEAACC"));
			//userPO.getIdentifier());
			msgCD.send();
			msgCD.destroy();
		}
		catch (Exception e)
		{
			e.printStackTrace();
			flexLog("Error: " + e);
			throw new RuntimeException("Socket Communication Error");
		}

		// Receive Error Message
		try
		{
			newmessage = mc.receiveMessage();

			if (newmessage.getFormatName().equals("ELEERR"))
			{
				msgError = (ELEERRMessage) newmessage;
				IsNotError = msgError.getERRNUM().equals("0");
				flexLog("IsNotError = " + IsNotError);
				showERROR(msgError);
			}
			else
				flexLog("Message " + newmessage.getFormatName() + " received.");

		}
		catch (Exception e)
		{
			e.printStackTrace();
			flexLog("Error: " + e);
			throw new RuntimeException("Socket Communication Error");
		}

		// Receive Data
		try
		{
			newmessage = mc.receiveMessage();

			if (newmessage.getFormatName().equals("EDL013007"))
			{
				try
				{
					msgCD = new datapro.eibs.beans.EDL013007Message();
				}
				catch (Exception ex)
				{
					flexLog("Error: " + ex);
				}

				msgCD = (EDL013007Message) newmessage;
				// showESD008004(msgCD);

				flexLog("Putting java beans into the session");
				ses.setAttribute("error", msgError);
				ses.setAttribute("cdCancel", msgCD);

				if (IsNotError)
				{ // There are no errors
					try
					{
						flexLog(
							"About to call Page3: "
								+ LangPath
								+ "EDL0140_cd_ap_cancel.jsp");
						callPage(
							LangPath + "EDL0140_cd_ap_cancel.jsp",
							req,
							res);
					}
					catch (Exception e)
					{
						flexLog("Exception calling page " + e);
					}
				}
				else
				{ // There are errors
					try
					{
						flexLog(
							"About to call Page4: "
								+ LangPath
								+ "error_viewer.jsp");
						callPage(LangPath + "error_viewer.jsp", req, res);

					}
					catch (Exception e)
					{
						flexLog("Exception calling page " + e);
					}
				}
			}
			else
				flexLog("Message " + newmessage.getFormatName() + " received.");

		}
		catch (Exception e)
		{
			e.printStackTrace();
			flexLog("Error: " + e);
			throw new RuntimeException("Socket Communication Error");
		}

	}
	/**
	 * This method was created in VisualAge.
	 */
	protected void procReqRenovations(
		MessageContext mc,
		ESS0030DSMessage user,
		HttpServletRequest req,
		HttpServletResponse res,
		HttpSession ses)
		throws ServletException, IOException
	{

		MessageRecord newmessage = null;
		EDL013008Message msgCD = null;
		ELEERRMessage msgError = null;
		UserPos userPO = null;
		boolean IsNotError = false;

		try
		{
			msgError = new datapro.eibs.beans.ELEERRMessage();
		}
		catch (Exception ex)
		{
			flexLog("Error: " + ex);
		}

		userPO = (datapro.eibs.beans.UserPos) ses.getAttribute("userPO");

		String opCode = null;
		opCode = "0004";

		// Send Initial data
		try
		{
			msgCD = (EDL013008Message) mc.getMessageRecord("EDL013008");
			msgCD.setH08USERID(user.getH01USR());
			msgCD.setH08PROGRM("EDL0130");
			msgCD.setH08TIMSYS(getTimeStamp());
			msgCD.setH08SCRCOD("01");
			msgCD.setH08OPECOD(opCode);
			msgCD.setH08FLGWK1("A");
			msgCD.setE08DEAACC(userPO.getIdentifier());
			msgCD.send();
			msgCD.destroy();
		}
		catch (Exception e)
		{
			e.printStackTrace();
			flexLog("Error: " + e);
			throw new RuntimeException("Socket Communication Error");
		}

		// Receive Error Message
		try
		{
			newmessage = mc.receiveMessage();

			if (newmessage.getFormatName().equals("ELEERR"))
			{
				msgError = (ELEERRMessage) newmessage;
				IsNotError = msgError.getERRNUM().equals("0");
				flexLog("IsNotError = " + IsNotError);
				showERROR(msgError);
			}
			else
				flexLog("Message " + newmessage.getFormatName() + " received.");

		}
		catch (Exception e)
		{
			e.printStackTrace();
			flexLog("Error: " + e);
			throw new RuntimeException("Socket Communication Error");
		}

		// Receive Data
		try
		{
			newmessage = mc.receiveMessage();

			if (newmessage.getFormatName().equals("EDL013008"))
			{
				try
				{
					msgCD = new datapro.eibs.beans.EDL013008Message();
				}
				catch (Exception ex)
				{
					flexLog("Error: " + ex);
				}

				msgCD = (EDL013008Message) newmessage;
				// showESD008004(msgCD);

				flexLog("Putting java beans into the session");
				ses.setAttribute("error", msgError);
				ses.setAttribute("cdRenov", msgCD);

				procReqMaintPay(mc, user, req, res, ses);

				if (IsNotError)
				{ // There are no errors
					if (userPO.getOption().equals("TREASURY"))
					{
						try
						{
							flexLog("About to call Page3: " + LangPath + "EDL0140_cd_ap_renov_options_t.jsp");
							callPage(LangPath + "EDL0140_cd_ap_renov_options_t.jsp", req, res);
						}
						catch (Exception e)
						{
							flexLog("Exception calling page " + e);
						}
					}
					else
					{
						try
						{
							flexLog("About to call Page3: " + LangPath + "EDL0140_cd_ap_renov_options.jsp");
							callPage(LangPath + "EDL0140_cd_ap_renov_options.jsp", req, res);
						}
						catch (Exception e)
						{
							flexLog("Exception calling page " + e);
						}
					}
				}
				else
				{ // There are errors
					try
					{
						flexLog(
							"About to call Page4: "
								+ LangPath
								+ "EDL0140_cd_ap_maint.jsp");
						callPage(
							LangPath + "EDL0140_cd_ap_maint.jsp",
							req,
							res);
					}
					catch (Exception e)
					{
						flexLog("Exception calling page " + e);
					}
				}
			}
			else
				flexLog("Message " + newmessage.getFormatName() + " received.");

		}
		catch (Exception e)
		{
			e.printStackTrace();
			flexLog("Error: " + e);
			throw new RuntimeException("Socket Communication Error");
		}

	}
	/**
	 * This method was created in VisualAge.
	 */
	protected void procReqSpecialCodes(
		MessageContext mc,
		ESS0030DSMessage user,
		HttpServletRequest req,
		HttpServletResponse res,
		HttpSession ses)
		throws ServletException, IOException
	{

		MessageRecord newmessage = null;
		ESD000002Message msgCD = null;
		ELEERRMessage msgError = null;
		UserPos userPO = null;
		boolean IsNotError = false;

		try
		{
			msgError = new datapro.eibs.beans.ELEERRMessage();
		}
		catch (Exception ex)
		{
			flexLog("Error: " + ex);
		}

		userPO = (datapro.eibs.beans.UserPos) ses.getAttribute("userPO");

		String opCode = "";

		opCode = "0004";

		// Send Initial data
		try
		{
			msgCD = (ESD000002Message) mc.getMessageRecord("ESD000002");
			msgCD.setH02USR(user.getH01USR());
			msgCD.setH02PGM("EDL0130");
			msgCD.setH02TIM(getTimeStamp());
			msgCD.setH02SCR("01");
			msgCD.setH02OPE(opCode);
			msgCD.setE02ACC(userPO.getIdentifier());
			msgCD.send();
			msgCD.destroy();
		}
		catch (Exception e)
		{
			e.printStackTrace();
			flexLog("Error: " + e);
			throw new RuntimeException("Socket Communication Error");
		}

		// Receive Error Message
		try
		{
			newmessage = mc.receiveMessage();

			if (newmessage.getFormatName().equals("ELEERR"))
			{
				msgError = (ELEERRMessage) newmessage;
				IsNotError = msgError.getERRNUM().equals("0");
				flexLog("IsNotError = " + IsNotError);
				showERROR(msgError);
			}
			else
				flexLog("Message " + newmessage.getFormatName() + " received.");

		}
		catch (Exception e)
		{
			e.printStackTrace();
			flexLog("Error: " + e);
			throw new RuntimeException("Socket Communication Error");
		}

		// Receive Data
		try
		{
			newmessage = mc.receiveMessage();

			if (newmessage.getFormatName().equals("ESD000002"))
			{
				try
				{
					msgCD = new datapro.eibs.beans.ESD000002Message();
				}
				catch (Exception ex)
				{
					flexLog("Error: " + ex);
				}

				msgCD = (ESD000002Message) newmessage;
				// showESD008004(msgCD);

				flexLog("Putting java beans into the session");
				ses.setAttribute("error", msgError);
				ses.setAttribute("cdCodes", msgCD);

				if (IsNotError)
				{ // There are no errors
					try
					{
						flexLog(
							"About to call Page: "
								+ LangPath
								+ "EDL0140_cd_ap_codes.jsp");
						callPage(
							LangPath + "EDL0140_cd_ap_codes.jsp",
							req,
							res);
					}
					catch (Exception e)
					{
						flexLog("Exception calling page " + e);
					}
				}
				else
				{ // There are errors
					try
					{
						flexLog(
							"About to call Page4: "
								+ LangPath
								+ "EDL0140_cd_ap_maint.jsp");
						callPage(
							LangPath + "EDL0140_cd_ap_maint.jsp",
							req,
							res);
					}
					catch (Exception e)
					{
						flexLog("Exception calling page " + e);
					}
				}
			}
			else
				flexLog("Message " + newmessage.getFormatName() + " received.");

		}
		catch (Exception e)
		{
			e.printStackTrace();
			flexLog("Error: " + e);
			throw new RuntimeException("Socket Communication Error");
		}

	}
	/**
	 * This method was created in VisualAge.
	 */
	protected void procReqTit(
		MessageContext mc,
		ESS0030DSMessage user,
		HttpServletRequest req,
		HttpServletResponse res,
		HttpSession ses)
		throws ServletException, IOException
	{

		MessageRecord newmessage = null;
		ESD000006Message msgCD = null;
		ELEERRMessage msgError = null;
		UserPos userPO = null;
		boolean IsNotError = false;

		try
		{
			msgError = new datapro.eibs.beans.ELEERRMessage();
		}
		catch (Exception ex)
		{
			flexLog("Error: " + ex);
		}

		userPO = (datapro.eibs.beans.UserPos) ses.getAttribute("userPO");

		String opCode = null;
		opCode = "0004";

		// Send Initial data
		try
		{
			msgCD = (ESD000006Message) mc.getMessageRecord("ESD000006");
			msgCD.setH06USR(user.getH01USR());
			msgCD.setH06PGM("EDL0130");
			msgCD.setH06TIM(getTimeStamp());
			msgCD.setH06SCR("01");
			msgCD.setH06OPE(opCode);
			msgCD.setE06ACC(userPO.getIdentifier());
			msgCD.setE06RTP("H");
			msgCD.send();
			msgCD.destroy();
		}
		catch (Exception e)
		{
			e.printStackTrace();
			flexLog("Error: " + e);
			throw new RuntimeException("Socket Communication Error");
		}

		// Receive Error Message
		try
		{
			newmessage = mc.receiveMessage();

			if (newmessage.getFormatName().equals("ELEERR"))
			{
				msgError = (ELEERRMessage) newmessage;
				IsNotError = msgError.getERRNUM().equals("0");
				flexLog("IsNotError = " + IsNotError);
				showERROR(msgError);
			}
			else
				flexLog("Message " + newmessage.getFormatName() + " received.");

		}
		catch (Exception e)
		{
			e.printStackTrace();
			flexLog("Error: " + e);
			throw new RuntimeException("Socket Communication Error");
		}

		// Receive Data
		try
		{
			newmessage = mc.receiveMessage();

			if (newmessage.getFormatName().equals("ESD000006"))
			{
				try
				{
					msgCD = new datapro.eibs.beans.ESD000006Message();
				}
				catch (Exception ex)
				{
					flexLog("Error: " + ex);
				}

				msgCD = (ESD000006Message) newmessage;
				// showESD008004(msgCD);

				flexLog("Putting java beans into the session");
				ses.setAttribute("error", msgError);
				ses.setAttribute("cdTit", msgCD);

				if (IsNotError)
				{ // There are no errors
					try
					{
						flexLog(
							"About to call Page3: "
								+ LangPath
								+ "EDL0140_cd_ap_tit.jsp");
						callPage(LangPath + "EDL0140_cd_ap_tit.jsp", req, res);
					}
					catch (Exception e)
					{
						flexLog("Exception calling page " + e);
					}
				}
				else
				{ // There are errors
					try
					{
						flexLog(
							"About to call Page4: "
								+ LangPath
								+ "EDL0140_cd_ap_maint.jsp");
						callPage(
							LangPath + "EDL0140_cd_ap_maint.jsp",
							req,
							res);
					}
					catch (Exception e)
					{
						flexLog("Exception calling page " + e);
					}
				}
			}
			else
				flexLog("Message " + newmessage.getFormatName() + " received.");

		}
		catch (Exception e)
		{
			e.printStackTrace();
			flexLog("Error: " + e);
			throw new RuntimeException("Socket Communication Error");
		}

	}
	/**
	 * This method was created in VisualAge.
	 */
	protected void procReqTransac(
		MessageContext mc,
		ESS0030DSMessage user,
		HttpServletRequest req,
		HttpServletResponse res,
		HttpSession ses)
		throws ServletException, IOException
	{

		MessageRecord newmessage = null;
		EDL013003Message msgCD = null;
		ELEERRMessage msgError = null;
		UserPos userPO = null;
		boolean IsNotError = false;

		try
		{
			msgError = new datapro.eibs.beans.ELEERRMessage();
		}
		catch (Exception ex)
		{
			flexLog("Error: " + ex);
		}

		userPO = (datapro.eibs.beans.UserPos) ses.getAttribute("userPO");

		String opCode = null;
		opCode = "0004";

		// Send Initial data
		try
		{
			msgCD = (EDL013003Message) mc.getMessageRecord("EDL013003");
			msgCD.setH03USERID(user.getH01USR());
			msgCD.setH03PROGRM("EDL0130");
			msgCD.setH03TIMSYS(getTimeStamp());
			msgCD.setH03SCRCOD("01");
			msgCD.setH03OPECOD(opCode);
			msgCD.setH03FLGWK1("A");
			try
			{
				msgCD.setE03DEAACC(req.getParameter("E01DEAACC"));
				userPO.setIdentifier(req.getParameter("E01DEAACC"));
			}
			catch (Exception e)
			{
				msgCD.setE03DEAACC(userPO.getIdentifier());
			}
			msgCD.send();
			msgCD.destroy();
		}
		catch (Exception e)
		{
			e.printStackTrace();
			flexLog("Error: " + e);
			throw new RuntimeException("Socket Communication Error");
		}

		// Receive Error Message
		try
		{
			newmessage = mc.receiveMessage();

			if (newmessage.getFormatName().equals("ELEERR"))
			{
				msgError = (ELEERRMessage) newmessage;
				IsNotError = msgError.getERRNUM().equals("0");
				flexLog("IsNotError = " + IsNotError);
				showERROR(msgError);
			}
			else
				flexLog("Message " + newmessage.getFormatName() + " received.");

		}
		catch (Exception e)
		{
			e.printStackTrace();
			flexLog("Error: " + e);
			throw new RuntimeException("Socket Communication Error");
		}

		// Receive Data
		try
		{
			newmessage = mc.receiveMessage();

			if (newmessage.getFormatName().equals("EDL013003"))
			{
				try
				{
					msgCD = new datapro.eibs.beans.EDL013003Message();
				}
				catch (Exception ex)
				{
					flexLog("Error: " + ex);
				}

				msgCD = (EDL013003Message) newmessage;
				// showESD008004(msgCD);

				userPO.setIdentifier(msgCD.getE03DEAACC());
				userPO.setOption("CD_TRANSACTION");

				flexLog("Putting java beans into the session");
				ses.setAttribute("error", msgError);
				ses.setAttribute("cdTransac", msgCD);
				ses.setAttribute("userPO", userPO);

				if (IsNotError)
				{ // There are no errors
					try
					{
						flexLog(
							"About to call Page3: "
								+ LangPath
								+ "EDL0140_cd_ap_transac.jsp");
						callPage(
							LangPath + "EDL0140_cd_ap_transac.jsp",
							req,
							res);
					}
					catch (Exception e)
					{
						flexLog("Exception calling page " + e);
					}
				}
				else
				{ // There are errors
					try
					{
						flexLog(
							"About to call Page4: "
								+ LangPath
								+ "error_viewer.jsp");
						callPage(LangPath + "error_viewer.jsp", req, res);
					}
					catch (Exception e)
					{
						flexLog("Exception calling page " + e);
					}
				}
			}
			else
				flexLog("Message " + newmessage.getFormatName() + " received.");

		}
		catch (Exception e)
		{
			e.printStackTrace();
			flexLog("Error: " + e);
			throw new RuntimeException("Socket Communication Error");
		}

	}

	protected void procReqSwift100CDS(
		MessageContext mc,
		ESS0030DSMessage user,
		HttpServletRequest req,
		HttpServletResponse res,
		HttpSession ses)
		throws ServletException, IOException
	{

		MessageRecord newmessage = null;
		ESWF10001Message msgSwift = null;
		ELEERRMessage msgError = null;
		UserPos userPO = null;
		boolean IsNotError = false;

		try
		{
			msgError = new datapro.eibs.beans.ELEERRMessage();
		}
		catch (Exception ex)
		{
			flexLog("Error: " + ex);
		}

		userPO = (datapro.eibs.beans.UserPos) ses.getAttribute("userPO");

		String opCode = null;

		opCode = "0001";

		// Send Initial data
		try
		{
			msgSwift = (ESWF10001Message) mc.getMessageRecord("ESWF10001");
			msgSwift.setH01USR(user.getH01USR());
			msgSwift.setH01PGM("ESWF10001");
			msgSwift.setH01TIM(getTimeStamp());
			msgSwift.setH01SCR("01");
			msgSwift.setH01OPE(opCode);
			msgSwift.setH01WK1("1");

			try
			{
				msgSwift.setE01SWFACC(userPO.getIdentifier());
			}
			catch (Exception e)
			{
				msgSwift.setE01SWFACC("0");
			}

			try
			{
				msgSwift.setE01SWFBNK(userPO.getBank());
			}
			catch (Exception e)
			{
				msgSwift.setE01SWFBNK("0");
			}

			try
			{
				msgSwift.setE01SWFCUN(userPO.getCusNum());
			}
			catch (Exception e)
			{
				msgSwift.setE01SWFCUN("0");
			}

			try
			{
				msgSwift.setE01SWFAMT(userPO.getHeader1());
			}
			catch (Exception e)
			{
				msgSwift.setE01SWFCUN("0");
			}

			msgSwift.send();
			msgSwift.destroy();
			flexLog("ESWF10001 Message Sent");
		}
		catch (Exception e)
		{
			e.printStackTrace();
			flexLog("Error: " + e);
			throw new RuntimeException("Socket Communication Error");
		}

		// Receive Error Message
		try
		{
			newmessage = mc.receiveMessage();

			if (newmessage.getFormatName().equals("ELEERR"))
			{
				msgError = (ELEERRMessage) newmessage;
				IsNotError = msgError.getERRNUM().equals("0");
				flexLog("IsNotError = " + IsNotError);
				showERROR(msgError);
			}
			else
				flexLog("Message " + newmessage.getFormatName() + " received.");

		}
		catch (Exception e)
		{
			e.printStackTrace();
			flexLog("Error: " + e);
			throw new RuntimeException("Socket Communication Error");
		}

		// Receive Data
		try
		{
			newmessage = mc.receiveMessage();

			if (newmessage.getFormatName().equals("ESWF10001"))
			{
				try
				{
					msgSwift = new datapro.eibs.beans.ESWF10001Message();
				}
				catch (Exception ex)
				{
					flexLog("Error: " + ex);
				}

				msgSwift = (ESWF10001Message) newmessage;

				flexLog("Putting java beans into the session");
				ses.setAttribute("error", msgError);
				ses.setAttribute("swift", msgSwift);
				ses.setAttribute("userPO", userPO);

				if (IsNotError)
				{ // There are no errors
					try
					{
						flexLog(
							"About to call Page: "
								+ LangPath
								+ "ESWF100A_fe_cd_format.jsp");
						callPage(
							LangPath + "ESWF100A_fe_cd_format.jsp",
							req,
							res);
					}
					catch (Exception e)
					{
						flexLog("Exception calling page " + e);
					}

				}
				else
				{ // There are errors
					try
					{
						flexLog(
							"About to call Page: "
								+ LangPath
								+ "EDL0140_cd_ap_maint.jsp");
						callPage(
							LangPath + "EDL0140_cd_ap_maint.jsp",
							req,
							res);
					}
					catch (Exception e)
					{
						flexLog("Exception calling page " + e);
					}

				}
			}
			else
				flexLog("Message " + newmessage.getFormatName() + " received.");

		}
		catch (Exception e)
		{
			e.printStackTrace();
			flexLog("Error: " + e);
			throw new RuntimeException("Socket Communication Error");
		}

	}

	protected void procReqSwift320CDS(
		MessageContext mc,
		ESS0030DSMessage user,
		HttpServletRequest req,
		HttpServletResponse res,
		HttpSession ses)
		throws ServletException, IOException
	{

		MessageRecord newmessage = null;
		ESWF10002Message msgSwift = null;
		ELEERRMessage msgError = null;
		UserPos userPO = null;
		boolean IsNotError = false;

		try
		{
			msgError = new datapro.eibs.beans.ELEERRMessage();
		}
		catch (Exception ex)
		{
			flexLog("Error: " + ex);
		}

		userPO = (datapro.eibs.beans.UserPos) ses.getAttribute("userPO");

		String opCode = null;

		opCode = "0001";

		// Send Initial data
		try
		{
			msgSwift = (ESWF10002Message) mc.getMessageRecord("ESWF10002");
			msgSwift.setH02USR(user.getH01USR());
			msgSwift.setH02PGM("ESWF10002");
			msgSwift.setH02TIM(getTimeStamp());
			msgSwift.setH02SCR("01");
			msgSwift.setH02OPE(opCode);

			try
			{
				msgSwift.setE02SWFACC(userPO.getIdentifier());
			}
			catch (Exception e)
			{
				msgSwift.setE02SWFACC("0");
			}

			try
			{
				msgSwift.setE02SWFBNK(userPO.getBank());
			}
			catch (Exception e)
			{
				msgSwift.setE02SWFBNK("0");
			}

			try
			{
				msgSwift.setE02SWFCUN(userPO.getCusNum());
			}
			catch (Exception e)
			{
				msgSwift.setE02SWFCUN("0");
			}

			msgSwift.send();
			msgSwift.destroy();
			flexLog("ESWF10002 Message Sent");
		}
		catch (Exception e)
		{
			e.printStackTrace();
			flexLog("Error: " + e);
			throw new RuntimeException("Socket Communication Error");
		}

		// Receive Error Message
		try
		{
			newmessage = mc.receiveMessage();

			if (newmessage.getFormatName().equals("ELEERR"))
			{
				msgError = (ELEERRMessage) newmessage;
				IsNotError = msgError.getERRNUM().equals("0");
				flexLog("IsNotError = " + IsNotError);
				showERROR(msgError);
			}
			else
				flexLog("Message " + newmessage.getFormatName() + " received.");

		}
		catch (Exception e)
		{
			e.printStackTrace();
			flexLog("Error: " + e);
			throw new RuntimeException("Socket Communication Error");
		}

		// Receive Data
		try
		{
			newmessage = mc.receiveMessage();

			if (newmessage.getFormatName().equals("ESWF10002"))
			{
				try
				{
					msgSwift = new datapro.eibs.beans.ESWF10002Message();
				}
				catch (Exception ex)
				{
					flexLog("Error: " + ex);
				}

				msgSwift = (ESWF10002Message) newmessage;

				flexLog("Putting java beans into the session");
				ses.setAttribute("error", msgError);
				ses.setAttribute("swift", msgSwift);
				ses.setAttribute("userPO", userPO);

				if (IsNotError)
				{ // There are no errors
					try
					{
						flexLog(
							"About to call Page: "
								+ LangPath
								+ "ESWF320A_fe_cd_format.jsp");
						callPage(
							LangPath + "ESWF320A_fe_cd_format.jsp",
							req,
							res);
					}
					catch (Exception e)
					{
						flexLog("Exception calling page " + e);
					}

				}
				else
				{ // There are errors
					try
					{
						flexLog(
							"About to call Page: "
								+ LangPath
								+ "EDL0140_cd_ap_maint.jsp");
						callPage(
							LangPath + "EDL0140_cd_ap_maint.jsp",
							req,
							res);
					}
					catch (Exception e)
					{
						flexLog("Exception calling page " + e);
					}

				}
			}
			else
				flexLog("Message " + newmessage.getFormatName() + " received.");

		}
		catch (Exception e)
		{
			e.printStackTrace();
			flexLog("Error: " + e);
			throw new RuntimeException("Socket Communication Error");
		}

	}

	public void service(HttpServletRequest req, HttpServletResponse res)
		throws ServletException, IOException
	{

		Socket s = null;
		MessageContext mc = null;

		ESS0030DSMessage msgUser = null;
		HttpSession session = null;

		session = (HttpSession) req.getSession(false);

		if (session == null)
		{
			try
			{
				res.setContentType("text/html");
				printLogInAgain(res.getWriter());
			}
			catch (Exception e)
			{
				e.printStackTrace();
				flexLog("Exception ocurred. Exception = " + e);
			}
		}
		else
		{

			int screen = R_ENTER_MAINT;

			try
			{

				msgUser =
					(datapro.eibs.beans.ESS0030DSMessage) session.getAttribute(
						"currUser");

				// Here we should get the path from the user profile
				LangPath = super.rootPath + msgUser.getE01LAN() + "/";

				try
				{
					flexLog("Opennig Socket Connection");
					s = new Socket(super.hostIP, getInitSocket(req) + 3);
					s.setSoTimeout(super.sckTimeOut);
					mc =
						new MessageContext(
							new DataInputStream(
								new BufferedInputStream(s.getInputStream())),
							new DataOutputStream(
								new BufferedOutputStream(s.getOutputStream())),
							"datapro.eibs.beans");

					try
					{
						screen = Integer.parseInt(req.getParameter("SCREEN"));
					}
					catch (Exception e)
					{
						flexLog("Screen set to default value");
					}

					switch (screen)
					{
						// BEGIN CD
						// Request
						case R_MAINTENANCE :
							procReqMaintenance(mc, msgUser, req, res, session);
							break;
						case R_BENEFICIARY :
							procReqBene(mc, msgUser, req, res, session);
							break;
						case R_SPECIAL_INST :
							procReqEspInst(mc, msgUser, req, res, session);
							break;
						case R_ACCOUNT_TITLE :
							procReqAccountTitle(mc, msgUser, req, res, session);
							break;
						case R_TITULARES :
							procReqTit(mc, msgUser, req, res, session);
							break;
						case R_EXCHANGE :
							procReqExchangeRate(mc, msgUser, req, res, session);
							break;
						case R_CODES :
							procReqSpecialCodes(mc, msgUser, req, res, session);
							break;
						case R_PRECANCEL :
							procReqPreCancel(mc, msgUser, req, res, session);
							break;
						case R_TRANSACTION :
							procReqTransac(mc, msgUser, req, res, session);
							break;
						case R_RENOV_MANT :
							procReqRenovations(mc, msgUser, req, res, session);
							break;
						case R_INT_PREP :
							procReqIntPrep(mc, msgUser, req, res, session);
							break;
						case R_OTHERS_TRANS :
							procReqOthersTransaction(
								mc,
								msgUser,
								req,
								res,
								session);
							break;
						case R_DISBUSERMENT :
							procReqDisbuserment(mc, msgUser, req, res, session);
							break;
						case R_MONEY :
							procReqCDMoney(mc, msgUser, req, res, session);
							break;
						case R_CD_SIGNERS :
							procReqSigners(mc, msgUser, req, res, session);
							break;
						case R_SWIFT320 :
							procReqSwift320CDS(mc, msgUser, req, res, session);
							break;
						case R_SWIFT100 :
							procReqSwift100CDS(mc, msgUser, req, res, session);
							break;

							//Reports	

							// Action
						case A_MAINTENANCE :

							break;
							// END CD

							// BEGIN Entering
							// Request
						case R_ENTER_MAINT :
							procReqEnterMaint(msgUser, req, res, session);
							break;
						case R_ENTER_CANCEL :

							break;
						case R_ENTER_TRANSAC :

							break;
						case R_ENTER_INQUIRY :

							break;
						case R_ENTER_PRINT :

							break;

							// Action 
						case A_ENTER_MAINT :
							procActionEnterMaint(
								mc,
								msgUser,
								req,
								res,
								session);
							break;
						case A_ENTER_CANCEL :

							break;
						case A_ENTER_TRANSAC :

							break;
							// END Entering

						default :
							res.sendRedirect(
								super.srctx + LangPath + super.devPage);
							break;
					}

				}
				catch (Exception e)
				{
					e.printStackTrace();
					int sck = getInitSocket(req) + 3;
					flexLog("Socket not Open(Port " + sck + "). Error: " + e);
					res.sendRedirect(
						super.srctx + LangPath + super.sckNotOpenPage);
					//return;
				}
				finally
				{
					s.close();
				}

			}
			catch (Exception e)
			{
				flexLog("Error: " + e);
				res.sendRedirect(
					super.srctx + LangPath + super.sckNotRespondPage);
			}

		}

	}

	protected void showERROR(ELEERRMessage m)
	{
		if (logType != NONE)
		{

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
	protected void procReqCDMoney(
		MessageContext mc,
		ESS0030DSMessage user,
		HttpServletRequest req,
		HttpServletResponse res,
		HttpSession ses)
		throws ServletException, IOException
	{

		MessageRecord newmessage = null;
		ELD000001Message msgLaunder = null;
		ELEERRMessage msgError = null;
		UserPos userPO = null;
		boolean IsNotError = false;

		try
		{
			msgError = new datapro.eibs.beans.ELEERRMessage();
		}
		catch (Exception ex)
		{
			flexLog("Error: " + ex);
		}

		userPO = (datapro.eibs.beans.UserPos) ses.getAttribute("userPO");

		// Send Initial data
		try
		{
			msgLaunder = (ELD000001Message) mc.getMessageRecord("ELD000001");
			msgLaunder.setH06USERID(user.getH01USR());
			msgLaunder.setH06PROGRM("EDL0130");
			msgLaunder.setH06TIMSYS(getTimeStamp());
			msgLaunder.setH06SCRCOD("01");
			msgLaunder.setH06OPECOD("0004");
			msgLaunder.setE06LDMACC(userPO.getIdentifier());
			msgLaunder.setH06FLGWK1("1");
			msgLaunder.send();
			msgLaunder.destroy();
		}
		catch (Exception e)
		{
			e.printStackTrace();
			flexLog("Error: " + e);
			throw new RuntimeException("Socket Communication Error");
		}

		// Receive Error Message
		try
		{
			newmessage = mc.receiveMessage();

			if (newmessage.getFormatName().equals("ELEERR"))
			{
				msgError = (ELEERRMessage) newmessage;
				IsNotError = msgError.getERRNUM().equals("0");
				flexLog("IsNotError = " + IsNotError);
				showERROR(msgError);
			}
			else
				flexLog("Message " + newmessage.getFormatName() + " received.");

		}
		catch (Exception e)
		{
			e.printStackTrace();
			flexLog("Error: " + e);
			throw new RuntimeException("Socket Communication Error");
		}

		// Receive Data
		try
		{
			newmessage = mc.receiveMessage();

			if (newmessage.getFormatName().equals("ELD000001"))
			{
				try
				{
					msgLaunder = new datapro.eibs.beans.ELD000001Message();
				}
				catch (Exception ex)
				{
					flexLog("Error: " + ex);
				}

				msgLaunder = (ELD000001Message) newmessage;
				// showESD008004(msgLaunder);

				flexLog("Putting java beans into the session");
				ses.setAttribute("error", msgError);
				ses.setAttribute("cdMoney", msgLaunder);

				if (IsNotError)
				{ // There are no errors
					try
					{
						flexLog(
							"About to call Page3: "
								+ LangPath
								+ "EDL0140_cd_ap_money.jsp");
						callPage(
							LangPath + "EDL0140_cd_ap_money.jsp",
							req,
							res);
					}
					catch (Exception e)
					{
						flexLog("Exception calling page " + e);
					}
				}
				else
				{ // There are errors
					try
					{
						flexLog(
							"About to call Page4: "
								+ LangPath
								+ "EDL0140_cd_ap_maint.jsp");
						callPage(
							LangPath + "EDL0140_cd_ap_maint.jsp",
							req,
							res);
					}
					catch (Exception e)
					{
						flexLog("Exception calling page " + e);
					}
				}
			}
			else
				flexLog("Message " + newmessage.getFormatName() + " received.");

		}
		catch (Exception e)
		{
			e.printStackTrace();
			flexLog("Error: " + e);
			throw new RuntimeException("Socket Communication Error");
		}

	}

	/**
	 * This method was created in VisualAge.
	 */
	protected void procReqDisbuserment(
		MessageContext mc,
		ESS0030DSMessage user,
		HttpServletRequest req,
		HttpServletResponse res,
		HttpSession ses)
		throws ServletException, IOException
	{

		MessageRecord newmessage = null;
		ESD000008Message msgCD = null;
		ELEERRMessage msgError = null;
		UserPos userPO = null;
		boolean IsNotError = false;

		try
		{
			msgError = new datapro.eibs.beans.ELEERRMessage();
		}
		catch (Exception ex)
		{
			flexLog("Error: " + ex);
		}

		userPO = (datapro.eibs.beans.UserPos) ses.getAttribute("userPO");

		String opCode = null;
		opCode = "0004";

		// Send Initial data
		try
		{
			msgCD = (ESD000008Message) mc.getMessageRecord("ESD000008");
			msgCD.setH08USR(user.getH01USR());
			msgCD.setH08PGM("PAYMTINST");
			msgCD.setH08TIM(getTimeStamp());
			msgCD.setH08SCR("01");
			msgCD.setH08OPE(opCode);
			msgCD.setE08ACC(userPO.getIdentifier());
			msgCD.send();
			msgCD.destroy();
		}
		catch (Exception e)
		{
			e.printStackTrace();
			flexLog("Error: " + e);
			throw new RuntimeException("Socket Communication Error");
		}

		// Receive Error Message
		try
		{
			newmessage = mc.receiveMessage();

			if (newmessage.getFormatName().equals("ELEERR"))
			{
				msgError = (ELEERRMessage) newmessage;
				IsNotError = msgError.getERRNUM().equals("0");
				flexLog("IsNotError = " + IsNotError);
				showERROR(msgError);
			}
			else
				flexLog("Message " + newmessage.getFormatName() + " received.");

		}
		catch (Exception e)
		{
			e.printStackTrace();
			flexLog("Error: " + e);
			throw new RuntimeException("Socket Communication Error");
		}

		// Receive Data
		try
		{
			newmessage = mc.receiveMessage();

			if (newmessage.getFormatName().equals("ESD000008"))
			{
				try
				{
					msgCD = new datapro.eibs.beans.ESD000008Message();
				}
				catch (Exception ex)
				{
					flexLog("Error: " + ex);
				}

				msgCD = (ESD000008Message) newmessage;

				flexLog("Putting java beans into the session");
				ses.setAttribute("error", msgError);
				ses.setAttribute("cdDisb", msgCD);

				if (IsNotError)
				{ // There are no errors
					try
					{
						flexLog(
							"About to call Page3: "
								+ LangPath
								+ "EDL0140_cd_ap_inq_disb_inst.jsp");
						callPage(
							LangPath + "EDL0140_cd_ap_inq_disb_inst.jsp",
							req,
							res);
					}
					catch (Exception e)
					{
						flexLog("Exception calling page " + e);
					}
				}
				else
				{ // There are errors
					try
					{
						flexLog(
							"About to call Page4: "
								+ LangPath
								+ "EDL0140_cd_ap_maint.jsp");
						callPage(
							LangPath + "EDL0140_cd_ap_maint.jsp",
							req,
							res);
					}
					catch (Exception e)
					{
						flexLog("Exception calling page " + e);
					}
				}
			}
			else
				flexLog("Message " + newmessage.getFormatName() + " received.");

		}
		catch (Exception e)
		{
			e.printStackTrace();
			flexLog("Error: " + e);
			throw new RuntimeException("Socket Communication Error");
		}

	}

	/**
	 * This method was created in VisualAge.
	 */
	protected void procReqOthersTransaction(
		MessageContext mc,
		ESS0030DSMessage user,
		HttpServletRequest req,
		HttpServletResponse res,
		HttpSession ses)
		throws ServletException, IOException
	{
		UserPos userPO = null;
		boolean IsNotError = false;
		DataTransaction transData = null;

		userPO = (datapro.eibs.beans.UserPos) ses.getAttribute("userPO");

		try
		{

			transData = new datapro.eibs.beans.DataTransaction();

			transData.setWrkFile("4");
			transData.setAccNum(userPO.getIdentifier());

			flexLog("Putting java beans into the session");
			ses.setAttribute("transData", transData);

			flexLog("About to redirect request to the procReqTr with specific parameters: ");
			res.sendRedirect(
				super.srctx
					+ "/servlet/datapro.eibs.products.JSEGL0035I?SCREEN=1");

		}
		catch (Exception e)
		{
			e.printStackTrace();
			flexLog("Error: " + e);
			throw new RuntimeException("Socket Communication Error");
		}

	}

	protected void procReqSigners(
		MessageContext mc,
		ESS0030DSMessage user,
		HttpServletRequest req,
		HttpServletResponse res,
		HttpSession ses)
		throws ServletException, IOException
	{

		MessageRecord newmessage = null;
		ESD000004Message msgBene = null;
		ELEERRMessage msgError = null;
		UserPos userPO = null;
		boolean IsNotError = false;

		try
		{
			msgError = new ELEERRMessage();
		}
		catch (Exception ex)
		{
			flexLog("Error: " + ex);
		}

		userPO = (datapro.eibs.beans.UserPos) ses.getAttribute("userPO");

		String opCode = null;

		opCode = "0002";

		// Send Initial data
		try
		{
			msgBene = (ESD000004Message) mc.getMessageRecord("ESD000004");
			msgBene.setH04USR(user.getH01USR());
			msgBene.setH04PGM("EDL0130");
			msgBene.setH04TIM(getTimeStamp());
			msgBene.setH04SCR("01");
			msgBene.setH04OPE(opCode);
			msgBene.setE04CUN(userPO.getIdentifier());
			msgBene.setE04RTP("S");
			msgBene.send();
			msgBene.destroy();
		}
		catch (Exception e)
		{
			e.printStackTrace();
			flexLog("Error: " + e);
			throw new RuntimeException("Socket Communication Error");
		}

		// Receive Error Message
		try
		{
			newmessage = mc.receiveMessage();

			if (newmessage.getFormatName().equals("ELEERR"))
			{
				msgError = (ELEERRMessage) newmessage;
				IsNotError = msgError.getERRNUM().equals("0");
				flexLog("IsNotError = " + IsNotError);
				showERROR(msgError);
			}
			else
				flexLog("Message " + newmessage.getFormatName() + " received.");

		}
		catch (Exception e)
		{
			e.printStackTrace();
			flexLog("Error: " + e);
			throw new RuntimeException("Socket Communication Error");
		}

		// Receive Data
		try
		{
			newmessage = mc.receiveMessage();

			if (newmessage.getFormatName().equals("ESD000004"))
			{
				try
				{
					msgBene = new ESD000004Message();
				}
				catch (Exception ex)
				{
					flexLog("Error: " + ex);
				}

				msgBene = (ESD000004Message) newmessage;
				// showESD000004(msgBene);

				flexLog("Putting java beans into the session");
				ses.setAttribute("error", msgError);
				ses.setAttribute("cdFirm", msgBene);

				if (IsNotError)
				{ // There are no errors 
					try
					{
						flexLog(
							"About to call Page: "
								+ LangPath
								+ "EDL0140_cd_ap_firm.jsp");
						callPage(LangPath + "EDL0140_cd_ap_firm.jsp", req, res);
					}
					catch (Exception e)
					{
						flexLog("Exception calling page " + e);
					}
				}
				else
				{ // There are errors
					try
					{
						flexLog(
							"About to call Page: "
								+ LangPath
								+ "EDL0130_cd_enter_maint.jsp");
						callPage(
							LangPath + "EDL0130_cd_enter_maint.jsp",
							req,
							res);
					}
					catch (Exception e)
					{
						flexLog("Exception calling page " + e);
					}
				}
			}
			else
				flexLog("Message " + newmessage.getFormatName() + " received.");

		}
		catch (Exception e)
		{
			e.printStackTrace();
			flexLog("Error: " + e);
			throw new RuntimeException("Socket Communication Error");
		}

	}

	protected void procReqMaintPay(
		MessageContext mc,
		ESS0030DSMessage user,
		HttpServletRequest req,
		HttpServletResponse res,
		HttpSession ses)
		throws ServletException, IOException
	{

		MessageRecord newmessage = null;
		EDL013004Message msgPmnt = null;
		JBListRec pmntList = null;
		ELEERRMessage msgError = null;
		UserPos userPO = null;
		boolean IsNotError = false;

		userPO = (datapro.eibs.beans.UserPos) ses.getAttribute("userPO");

		// Send Initial data
		try
		{
			msgPmnt = (EDL013004Message) mc.getMessageRecord("EDL013004");
			msgPmnt.setH04USERID(user.getH01USR());
			msgPmnt.setH04PROGRM("EDL0130");
			msgPmnt.setH04TIMSYS(getTimeStamp());
			msgPmnt.setH04SCRCOD("01");
			msgPmnt.setH04OPECOD("0002");
			try
			{
				msgPmnt.setE04DEAACC(userPO.getIdentifier());
			}
			catch (Exception e)
			{
				msgPmnt.setE04DEAACC("0");
			}

			msgPmnt.send();
			msgPmnt.destroy();
		}
		catch (Exception e)
		{
			e.printStackTrace();
			flexLog("Error: " + e);
			throw new RuntimeException("Socket Communication Error");
		}

		flexLog("Initializing java beans into the session");
		try
		{
			msgError = new datapro.eibs.beans.ELEERRMessage();
		}
		catch (Exception ex)
		{
			flexLog("Error: " + ex);
		}
		try
		{
			int colNum = 4;
			pmntList = new datapro.eibs.beans.JBListRec();
			pmntList.init(colNum);
		}
		catch (Exception ex)
		{
			flexLog("Error: " + ex);
		}

		// Receive Error Message
		try
		{
			newmessage = mc.receiveMessage();

			if (newmessage.getFormatName().equals("ELEERR"))
			{
				msgError = (ELEERRMessage) newmessage;
				IsNotError = msgError.getERRNUM().equals("0");
				flexLog("IsNotError = " + IsNotError);
				showERROR(msgError);
			}
			else
				flexLog("Message " + newmessage.getFormatName() + " received.");
		}
		catch (Exception e)
		{
			e.printStackTrace();
			flexLog("Error: " + e);
			throw new RuntimeException("Socket Communication Error");
		}

		// Receive Data
		try
		{
			newmessage = mc.receiveMessage();

			if (newmessage.getFormatName().equals("EDL013004"))
			{
				//Deductions List
				// Fill List bean

				int colNum = 4;

				char sel = ' ';
				String marker = "";
				String myFlag = "";
				String myRow[] = new String[colNum];
				for (int i = 0; i < colNum; i++)
				{
					myRow[i] = "";
				}

				while (true)
				{

					msgPmnt = (EDL013004Message) newmessage;

					marker = msgPmnt.getH04FLGMAS();

					if (marker.equals("*"))
					{
						break;
					}
					else
					{
						myRow[0] = Util.formatCell(msgPmnt.getE04DLPPNU());
						// Quote Number
						myRow[1] =
							Util.formatDate(
								msgPmnt.getE04DLPPD1(),
								msgPmnt.getE04DLPPD2(),
								msgPmnt.getE04DLPPD3());
						// Date
						myRow[2] = Util.formatCCY(msgPmnt.getE04DLPPRI());
						// Principal
						myRow[3] = Util.formatCell(msgPmnt.getE04DLPINT());
						// Interest

						pmntList.addRow(myFlag, myRow);

					}

					newmessage = mc.receiveMessage();

				}

				ses.setAttribute("error", msgError);
				ses.setAttribute("pmnt", pmntList);

			}
			else
				flexLog("Message " + newmessage.getFormatName() + " received.");

		}
		catch (Exception e)
		{
			e.printStackTrace();
			flexLog("Error: " + e);
			throw new RuntimeException("Socket Communication Error");
		}

	}
	protected void procReqAccountTitle(
		MessageContext mc,
		ESS0030DSMessage user,
		HttpServletRequest req,
		HttpServletResponse res,
		HttpSession ses)
		throws ServletException, IOException
	{

		MessageRecord newmessage = null;
		ESD000004Message msgMailA = null;
		ELEERRMessage msgError = null;
		UserPos userPO = null;
		boolean IsNotError = false;

		try
		{
			msgError = new datapro.eibs.beans.ELEERRMessage();
		}
		catch (Exception ex)
		{
			flexLog("Error: " + ex);
		}

		userPO = (datapro.eibs.beans.UserPos) ses.getAttribute("userPO");

		String opCode = null;
		opCode = "0004";

		// Send Initial data
		try
		{
			msgMailA = (ESD000004Message) mc.getMessageRecord("ESD000004");
			msgMailA.setH04USR(user.getH01USR());
			msgMailA.setH04PGM("ESD0080");
			msgMailA.setH04TIM(getTimeStamp());
			msgMailA.setH04SCR("01");
			msgMailA.setH04OPE(opCode);
			msgMailA.setE04CUN(userPO.getIdentifier());
			msgMailA.setE04RTP("1");
			msgMailA.setH04WK1("T");
			msgMailA.setH04WK3("2");
			msgMailA.send();
			msgMailA.destroy();
		}
		catch (Exception e)
		{
			e.printStackTrace();
			flexLog("Error: " + e);
			throw new RuntimeException("Socket Communication Error");
		}

		// Receive Error Message
		try
		{
			newmessage = mc.receiveMessage();

			if (newmessage.getFormatName().equals("ELEERR"))
			{
				msgError = (ELEERRMessage) newmessage;
				IsNotError = msgError.getERRNUM().equals("0");
				flexLog("IsNotError = " + IsNotError);
				showERROR(msgError);
			}
			else
				flexLog("Message " + newmessage.getFormatName() + " received.");

		}
		catch (Exception e)
		{
			e.printStackTrace();
			flexLog("Error: " + e);
			throw new RuntimeException("Socket Communication Error");
		}

		// Receive Data
		try
		{
			newmessage = mc.receiveMessage();

			if (newmessage.getFormatName().equals("ESD000004"))
			{
				try
				{
					msgMailA = new datapro.eibs.beans.ESD000004Message();
				}
				catch (Exception ex)
				{
					flexLog("Error: " + ex);
				}

				msgMailA = (ESD000004Message) newmessage;
				// showESD000004(msgMailA);

				flexLog("Putting java beans into the session");
				ses.setAttribute("error", msgError);
				ses.setAttribute("mailA", msgMailA);

				if (IsNotError)
				{ // There are no errors
					try
					{
						flexLog(
							"About to call Page: "
								+ LangPath
								+ "EDL0140_cd_ap_account_title.jsp");
						callPage(
							LangPath + "EDL0140_cd_ap_account_title.jsp",
							req,
							res);
					}
					catch (Exception e)
					{
						flexLog("Exception calling page " + e);
					}
				}
				else
				{ // There are errors
					try
					{
						flexLog(
							"About to call Page: "
								+ LangPath
								+ "EDL0140_cd_ap_basic.jsp");
						callPage(
							LangPath + "EDL0140_cd_ap_basic.jsp",
							req,
							res);
					}
					catch (Exception e)
					{
						flexLog("Exception calling page " + e);
					}
				}
			}
			else
				flexLog("Message " + newmessage.getFormatName() + " received.");

		}
		catch (Exception e)
		{
			e.printStackTrace();
			flexLog("Error: " + e);
			throw new RuntimeException("Socket Communication Error");
		}

	}
}