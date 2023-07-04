package datapro.eibs.products;

/**
 * Insert the type's description here.
 * Creation date: (1/8/06 8:05:47 AM)
 * @author: Gustavo Adolfo Villarroel
 */
import java.beans.Beans;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Calendar;
import java.util.Enumeration;
import java.util.StringTokenizer;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import datapro.eibs.beans.*;
import datapro.eibs.master.SuperServlet;
import datapro.eibs.master.Util;
import datapro.eibs.sockets.MessageContext;
import datapro.eibs.sockets.MessageField;
import datapro.eibs.sockets.MessageRecord;

public class JSECC0090 extends datapro.eibs.master.SuperServlet
{
	// Entering
	static final int R_CARD_ASSIGN_ENTER = 100;
	static final int R_CARD_ASSIGN_LIST = 200;

	// Holder Card
	static final int R_CARD_ASSIGN_NEW = 1;
	static final int A_CARD_ASSIGN_NEW = 2;
	static final int A_CARD_ASSIGN_MAINT = 3;
	static final int R_CARD_ASSIGN_INQ_MAINT = 4;

	// Additional Card
	static final int R_CARD_ASSIGN_ADDITIONAL_NEW = 5;
	static final int A_CARD_ASSIGN_ADDITIONAL_NEW = 6;
	static final int A_CARD_ASSIGN_ADDITIONAL_MAINT = 7;
	static final int R_CARD_ASSIGN_ADDITIONAL_INQ_MAINT = 8;
	
	// Status Change
	static final int R_CARD_STATUS_ENTER = 300;
	static final int R_CARD_STATUS_ENTER_INQ = 400;
	static final int A_CARD_STATUS = 500;

	// Card Replace
	static final int R_CARD_ASSIGN_REPLACE = 9;
	static final int A_CARD_ASSIGN_REPLACE = 10;

	// Card Print
	static final int R_CARD_ASSIGN_PRINT = 600;

	// Assignment Inquiry
	static final int R_ASSIGNMENT_INQ_ENTER = 1000;
	static final int A_ASSIGNMENT_INQ_ID = 1100;
	static final int A_ASSIGNMENT_INQ_CARD = 1200;
	static final int A_ASSIGNMENT_INQ_CARD_ADDITIONAL = 1300;

	protected String LangPath = "S";

	/**
	 * JSECC0090 constructor comment.
	 */
	public JSECC0090()
	{
		super();
	}
	/**
	 * This method was created by Gustavo Adolfo Villarroel.
	 */
	public void destroy()
	{
		flexLog("free resources used by JSECC0090");
	}
	/**
	 * This method was created by Gustavo Adolfo Villarroel.
	 */
	public void init(ServletConfig config) throws ServletException
	{
		super.init(config);
	}

	protected void procReqCardAssignEnter(MessageContext mc, ESS0030DSMessage user, HttpServletRequest req, HttpServletResponse res, HttpSession ses) throws ServletException, IOException
	{
		ELEERRMessage msgError = null;
		UserPos userPO = null;

		try
		{
			msgError = new datapro.eibs.beans.ELEERRMessage();
			userPO = new datapro.eibs.beans.UserPos();

			ses.setAttribute("error", msgError);
			ses.setAttribute("userPO", userPO);
		}
		catch (Exception ex)
		{
			flexLog("Error: " + ex);
		}

		try
		{
			flexLog("About to call Page: " + LangPath + "ECC0090_card_assign_enter.jsp");
			callPage(LangPath + "ECC0090_card_assign_enter.jsp", req, res);
		}
		catch (Exception e)
		{
			flexLog("Exception calling page " + e);
		}
	}

	protected void procReqCardAssignList(MessageContext mc, ESS0030DSMessage user, HttpServletRequest req, HttpServletResponse res, HttpSession ses) throws ServletException, IOException
	{
		MessageRecord newmessage = null;
		ECC009001Message msgCD = null;
		ELEERRMessage msgError = null;
		JBObjList appList = null;
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
	
		// Send Initial data
		try
		{
			flexLog("Send Initial Data");
			msgCD = (ECC009001Message) mc.getMessageRecord("ECC009001");
			msgCD.setH01USERID(user.getH01USR());
			msgCD.setH01PROGRM("ECC0090");
			msgCD.setH01TIMSYS(getTimeStamp());
			msgCD.setH01SCRCOD("01");
			msgCD.setH01OPECOD("0002");
			
			try
			{
				msgCD.setE01PRICUN(req.getParameter("E01PRICUN"));
			}
			catch (Exception e)
			{
				if (userPO.getOption().equals("T"))
					if (req.getParameter("E03PRICUN") != null)
						msgCD.setE01PRICUN(req.getParameter("E03PRICUN"));
				
				if (userPO.getOption().equals("A"))
					if (req.getParameter("E03PRICUNA") != null)
						msgCD.setE01PRICUN(req.getParameter("E03PRICUNA"));				
			}
	
			msgCD.send();
			msgCD.destroy();
			flexLog("ECC009001 Message Sent");
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
	
			if (newmessage.getFormatName().equals("ECC009001"))
			{
				appList = new JBObjList();
				boolean firstTime = true;
				String marker = "";
	
				while (true)
				{
					msgCD = (ECC009001Message) newmessage;
					marker = msgCD.getH01FLGMAS();
	
					if (marker.equals("*"))
					{
						appList.setShowNext(false);
						break;
					}
					else
					{
						appList.addRow(msgCD);
						if (firstTime)
						{
							firstTime = false;
							if (msgCD.getE01CCRTPI().equals("T"))
								userPO.setOption("T");
							else if (msgCD.getE01CCRTPI().equals("A"))
								userPO.setOption("A");
						}
						if (msgCD.getE01CCRTPI().equals("T"))
						{
							userPO.setIdentifier(msgCD.getE01CCRCID());
							userPO.setCusNum(msgCD.getE01PRICUN());
							userPO.setCusName(msgCD.getE01PRINA1());
							userPO.setCusType(msgCD.getE01CCRTYP());
							userPO.setHeader1(msgCD.getE01CCRNUM());
						}
						else if (msgCD.getE01CCRTPI().equals("A"))
						{
							userPO.setHeader9(msgCD.getE01CCRCID());
							userPO.setHeader10(msgCD.getE01PRICUN());
							userPO.setHeader11(msgCD.getE01PRINA1());
							userPO.setHeader12(msgCD.getE01CCRTYP());
							userPO.setHeader13(msgCD.getE01CCRNUM());
							userPO.setHeader14(msgCD.getE01CCRNEW());
						}
						else
						{
							userPO.setIdentifier(msgCD.getE01CCRCID());
							userPO.setCusNum(msgCD.getE01PRICUN());		
							userPO.setCusName(msgCD.getE01PRINA1());
							userPO.setCusType(msgCD.getE01CCRTYP());
							userPO.setHeader14(msgCD.getE01CCRNEW());
						}
						if (marker.equals("+"))
						{
							appList.setShowNext(true);
							break;
						}
					}
					newmessage = mc.receiveMessage();
				}
	
				flexLog("Putting java beans into the session");
				ses.setAttribute("cardList", appList);
				ses.setAttribute("error", msgError);
				ses.setAttribute("userPO", userPO);
	
				if (IsNotError)
				{ // There are no errors
					try
					{
						flexLog("About to call Page: " + LangPath + "ECC0090_card_assign_list.jsp");
						callPage(LangPath + "ECC0090_card_assign_list.jsp", req, res);
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
						flexLog("About to call Page: " + LangPath + "ECC0090_card_assign_enter.jsp");
						callPage(LangPath + "ECC0090_card_assign_enter.jsp", req, res);
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

	protected void procReqCardAssignNew(MessageContext mc, ESS0030DSMessage user, HttpServletRequest req, HttpServletResponse res, HttpSession ses) throws ServletException, IOException
	{
		ELEERRMessage msgError = null;
		UserPos userPO = null;
	
		try
		{
			msgError = new datapro.eibs.beans.ELEERRMessage();
			userPO = (datapro.eibs.beans.UserPos) ses.getAttribute("userPO");
	
			ses.setAttribute("error", msgError);
			ses.setAttribute("userPO", userPO);
		}
		catch (Exception ex)
		{
			flexLog("Error: " + ex);
		}
	
		try
		{
			flexLog("About to call Page: " + LangPath + "ECC0090_card_assign_new.jsp");
			callPage(LangPath + "ECC0090_card_assign_new.jsp", req, res);
		}
		catch (Exception e)
		{
			flexLog("Exception calling page " + e);
		}
	}

	protected void procActionCardAssignNew(MessageContext mc, ESS0030DSMessage user, HttpServletRequest req, HttpServletResponse res, HttpSession ses) throws ServletException, IOException
	{
		MessageRecord newmessage = null;
		ECC009001Message msgCD = null;
		ECC009002Message msgAcc = null;
		ELEERRMessage msgError = null;
		UserPos userPO = null;
		JBObjList accList = null;
		boolean IsNotError = false;
	
		try
		{
			msgError = new ELEERRMessage();
		}
		catch (Exception ex)
		{
			flexLog("Error: " + ex);
		}
	
		msgCD = (datapro.eibs.beans.ECC009001Message) ses.getAttribute("msgCD");
		msgCD.setE01CCRNUM(req.getParameter("E01CCRNUM"));
		userPO = (datapro.eibs.beans.UserPos) ses.getAttribute("userPO");
	
		// Send Initial data
		try
		{
			flexLog("Send Initial Data");
			msgAcc = (ECC009002Message) mc.getMessageRecord("ECC009002");
			msgAcc.setH02USERID(user.getH01USR());
			msgAcc.setH02PROGRM("ECC0090");
			msgAcc.setH02TIMSYS(getTimeStamp());
			msgAcc.setH02SCRCOD("01");
			msgAcc.setH02OPECOD("0015");
	
			// all the fields here
			try
			{
				if (req.getParameter("E01CCRCID") != null)
					msgAcc.setE02CCRCID(req.getParameter("E01CCRCID"));
				if (req.getParameter("E01PRICUN") != null)
					msgAcc.setE02PRICUN(req.getParameter("E01PRICUN"));
				if (req.getParameter("E01CCRTYP") != null)
					msgAcc.setE02CCRTYP(req.getParameter("E01CCRTYP"));
				if (req.getParameter("E01CCRNUM") != null)
					msgAcc.setE02CCRNUM(req.getParameter("E01CCRNUM"));
				if (req.getParameter("E01TARTYP") != null)
					msgAcc.setE02TARTYP(req.getParameter("E01TARTYP"));
			}
			catch (Exception e)
			{
				flexLog(" error " + e);
			}
	
			msgAcc.send();
			msgAcc.destroy();
			flexLog("ECC009002 Message Sent");
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
			if (newmessage.getFormatName().equals("ECC009002"))
			{
				accList = new JBObjList();
				boolean firstTime = true;
				String marker = "";
	
				while (true)
				{
					msgAcc = (ECC009002Message) newmessage;
					marker = msgAcc.getH02FLGMAS();
	
					if (marker.equals("*"))
					{
						userPO.setAccNum(msgAcc.getE02CCRCRA());
						userPO.setHeader1(msgAcc.getE02CCRNUM());
						userPO.setHeader2(msgAcc.getE02TARTYP());
						userPO.setHeader3(msgAcc.getE02CCRBRN());
						userPO.setHeader4(msgAcc.getE02CCRSTS());
						userPO.setHeader5(msgAcc.getE02CCRDSC());
						userPO.setHeader6(msgAcc.getE02CCRASD());
						userPO.setHeader7(msgAcc.getE02CCRASM());
						userPO.setHeader8(msgAcc.getE02CCRASY());
						userPO.setHeader21("true");
						accList.setShowNext(false);
						break;
					}
					else
					{
						accList.addRow(msgAcc);
						if (firstTime)
						{
							firstTime = false;
							userPO.setAccNum(msgAcc.getE02CCRCRA());
							userPO.setHeader1(msgAcc.getE02CCRNUM());
							userPO.setHeader2(msgAcc.getE02TARTYP());
							userPO.setHeader3(msgAcc.getE02CCRBRN());
							userPO.setHeader4(msgAcc.getE02CCRSTS());
							userPO.setHeader5(msgAcc.getE02CCRDSC());
							userPO.setHeader6(msgAcc.getE02CCRASD());
							userPO.setHeader7(msgAcc.getE02CCRASM());
							userPO.setHeader8(msgAcc.getE02CCRASY());
							userPO.setHeader20("");
							userPO.setHeader21("");
						}
						if (marker.equals("+"))
						{
							accList.setShowNext(true);
							break;
						}
					}
					newmessage = mc.receiveMessage();
				}
	
				flexLog("Putting java beans into the session");
				ses.setAttribute("msgCD", msgCD);
				ses.setAttribute("accList", accList);
				ses.setAttribute("error", msgError);
				ses.setAttribute("userPO", userPO);
	
				try
				{
					flexLog("About to call Page: " + LangPath + "ECC0090_card_assign_maint.jsp");
					callPage(LangPath + "ECC0090_card_assign_maint.jsp", req, res);
				}
				catch (Exception e)
				{
					flexLog("Exception calling page " + e);
				}
			}
			else
			{
				flexLog("Message " + newmessage.getFormatName() + " received.");
	
				ses.setAttribute("error", msgError);
	
				try
				{
					flexLog("About to call Page: " + LangPath + "ECC0090_card_assign_new.jsp");
					callPage(LangPath + "ECC0090_card_assign_new.jsp", req, res);
				}
				catch (Exception e)
				{
					flexLog("Exception calling page " + e);
				}
			}
	
		}
		catch (Exception e)
		{
			e.printStackTrace();
			flexLog("Error: " + e);
			throw new RuntimeException("Socket Communication Error");
		}
	}

	protected void procActionCardAssignMaint(MessageContext mc, ESS0030DSMessage user, HttpServletRequest req, HttpServletResponse res, HttpSession ses) throws ServletException, IOException
	{
		MessageRecord newmessage = null;
		ECC009001Message msgCD = null;
		ECC009002Message msgAcc = null;
		JBObjList accList = null;
		ELEERRMessage msgError = null;
		boolean IsNotError = false;
		UserPos userPO = null;
		String action = null;

		userPO = (datapro.eibs.beans.UserPos) ses.getAttribute("userPO");

		// Send Initial data
		try
		{
			msgAcc = (ECC009002Message) mc.getMessageRecord("ECC009002");
			msgAcc.setH02USERID(user.getH01USR());
			msgAcc.setH02PROGRM("ECC0090");
			msgAcc.setH02TIMSYS(getTimeStamp());
			msgAcc.setH02SCRCOD("01");
			if (req.getParameter("new").equals("true"))
				msgAcc.setH02OPECOD("0001");
			else
				msgAcc.setH02OPECOD("0005");

			Calendar cal = new java.util.GregorianCalendar();

			// Send Unassigned Accounts
			String[] accounts = req.getParameterValues("AccList");
			if (accounts != null)
			{
				for (int i = 0; i < accounts.length; i++)
				{
					StringTokenizer acc = new StringTokenizer(accounts[i], "-");
					msgAcc.setE02TARTYP(req.getParameter("E02TARTYP"));
					msgAcc.setE02CCRNUM(req.getParameter("E02CCRNUM"));
					msgAcc.setE02CCRBRN(req.getParameter("E02CCRBRN"));
					msgAcc.setE02PRICUN(req.getParameter("E02PRICUN"));
					msgAcc.setE02CCRCID(req.getParameter("E02CCRCID"));
					msgAcc.setE02CCRBRN(req.getParameter("E02CCRBRN"));
					msgAcc.setE02CCRCRA(acc.nextToken());
					msgAcc.setE02CCRASG("");
					msgAcc.setE02CCRASD("" + cal.get(Calendar.DAY_OF_MONTH));
					msgAcc.setE02CCRASM("" + (cal.get(Calendar.MONTH) + 1));
					msgAcc.setE02CCRASY("" + cal.get(Calendar.YEAR));
					msgAcc.send();
				}
			}

			// Send Main Accounts
			if (!req.getParameter("CCP").equals(""))
			{
				msgAcc.setE02TARTYP(req.getParameter("E02TARTYP"));
				msgAcc.setE02CCRNUM(req.getParameter("E02CCRNUM"));
				msgAcc.setE02CCRBRN(req.getParameter("E02CCRBRN"));
				msgAcc.setE02PRICUN(req.getParameter("E02PRICUN"));
				msgAcc.setE02CCRCID(req.getParameter("E02CCRCID"));
				msgAcc.setE02CCRBRN(req.getParameter("E02CCRBRN"));
				msgAcc.setE02CCRCRA(req.getParameter("CCP"));
				msgAcc.setE02CCRASG("P");
				if (req.getParameter("CC_Main").equals(req.getParameter("CCP")))
				{
					msgAcc.setE02CCRPRI("*");
				}
				else
				{
					msgAcc.setE02CCRPRI("");
				}
				msgAcc.setE02CCRASD("" + cal.get(Calendar.DAY_OF_MONTH));
				msgAcc.setE02CCRASM("" + (cal.get(Calendar.MONTH) + 1));
				msgAcc.setE02CCRASY("" + cal.get(Calendar.YEAR));
				msgAcc.send();
			}
			if (!req.getParameter("CAP").equals(""))
			{
				msgAcc.setE02TARTYP(req.getParameter("E02TARTYP"));
				msgAcc.setE02CCRNUM(req.getParameter("E02CCRNUM"));
				msgAcc.setE02CCRBRN(req.getParameter("E02CCRBRN"));
				msgAcc.setE02PRICUN(req.getParameter("E02PRICUN"));
				msgAcc.setE02CCRCID(req.getParameter("E02CCRCID"));
				msgAcc.setE02CCRBRN(req.getParameter("E02CCRBRN"));
				msgAcc.setE02CCRCRA(req.getParameter("CAP"));
				msgAcc.setE02CCRASG("P");
				if (req.getParameter("CC_Main").equals(req.getParameter("CAP")))
				{
					msgAcc.setE02CCRPRI("*");
				}
				else
				{
					msgAcc.setE02CCRPRI("");
				}
				msgAcc.setE02CCRASD("" + cal.get(Calendar.DAY_OF_MONTH));
				msgAcc.setE02CCRASM("" + (cal.get(Calendar.MONTH) + 1));
				msgAcc.setE02CCRASY("" + cal.get(Calendar.YEAR));
				msgAcc.send();
			}
			if (!req.getParameter("CFP").equals(""))
			{
				msgAcc.setE02TARTYP(req.getParameter("E02TARTYP"));
				msgAcc.setE02CCRNUM(req.getParameter("E02CCRNUM"));
				msgAcc.setE02CCRBRN(req.getParameter("E02CCRBRN"));
				msgAcc.setE02PRICUN(req.getParameter("E02PRICUN"));
				msgAcc.setE02CCRCID(req.getParameter("E02CCRCID"));
				msgAcc.setE02CCRBRN(req.getParameter("E02CCRBRN"));
				msgAcc.setE02CCRCRA(req.getParameter("CFP"));
				msgAcc.setE02CCRASG("P");
				if (req.getParameter("CC_Main").equals(req.getParameter("CFP")))
				{
					msgAcc.setE02CCRPRI("*");
				}
				else
				{
					msgAcc.setE02CCRPRI("");
				}
				msgAcc.setE02CCRASD("" + cal.get(Calendar.DAY_OF_MONTH));
				msgAcc.setE02CCRASM("" + (cal.get(Calendar.MONTH) + 1));
				msgAcc.setE02CCRASY("" + cal.get(Calendar.YEAR));
				msgAcc.send();
			}

			// Send Secondary Accounts
			String[] accounts2 = req.getParameterValues("AccSList");
			if (accounts2 != null)
			{
				for (int j = 0; j < accounts2.length; j++)
				{
					StringTokenizer accs = new StringTokenizer(accounts2[j], "-");
					msgAcc.setE02TARTYP(req.getParameter("E02TARTYP"));
					msgAcc.setE02CCRNUM(req.getParameter("E02CCRNUM"));
					msgAcc.setE02CCRBRN(req.getParameter("E02CCRBRN"));
					msgAcc.setE02PRICUN(req.getParameter("E02PRICUN"));
					msgAcc.setE02CCRCID(req.getParameter("E02CCRCID"));
					msgAcc.setE02CCRBRN(req.getParameter("E02CCRBRN"));
					msgAcc.setE02CCRCRA(accs.nextToken());
					msgAcc.setE02CCRASG("S");
					msgAcc.setE02CCRPRI("");
					msgAcc.setE02CCRASD("" + cal.get(Calendar.DAY_OF_MONTH));
					msgAcc.setE02CCRASM("" + (cal.get(Calendar.MONTH) + 1));
					msgAcc.setE02CCRASY("" + cal.get(Calendar.YEAR));
					msgAcc.send();
				}
			}

			// Send Final Flag
			msgAcc.setH02FLGMAS("*");
			msgAcc.send();
			msgAcc.destroy();
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

			flexLog("Putting java beans into the session");
			ses.setAttribute("error", msgError);
			ses.setAttribute("userPO", userPO);

			if (IsNotError) {
				flexLog("About to call: /servlet/datapro.eibs.products.JSECC0090?SCREEN=200&E01PRICUN=" + userPO.getCusNum());
				res.sendRedirect(super.srctx + "/servlet/datapro.eibs.products.JSECC0090?SCREEN=200&E01PRICUN=" + userPO.getCusNum());
			}
			else
			{
				flexLog("About to call Page: " + LangPath + "ECC0090_card_assign_maint.jsp");
				callPage(LangPath + "ECC0090_card_assign_maint.jsp", req, res);
			}

		}
		catch (Exception e)
		{
			e.printStackTrace();
			flexLog("Error: " + e);
			throw new RuntimeException("Socket Communication Error");
		}
	}

	protected void procReqCardAssignInquiryMaint(MessageContext mc, ESS0030DSMessage user, HttpServletRequest req, HttpServletResponse res, HttpSession ses) throws ServletException, IOException
	{
		MessageRecord newmessage = null;
		ECC009001Message msgCD = null;
		ECC009002Message msgAcc = null;
		ELEERRMessage msgError = null;
		UserPos userPO = null;
		JBObjList accList = null;
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

		// Send Initial data
		try
		{
			flexLog("Send Initial Data");
			msgAcc = (ECC009002Message) mc.getMessageRecord("ECC009002");
			msgAcc.setH02USERID(user.getH01USR());
			msgAcc.setH02PROGRM("ECC0090");
			msgAcc.setH02TIMSYS(getTimeStamp());
			msgAcc.setH02SCRCOD("01");
			msgAcc.setH02OPECOD("0015");

			// all the fields here
			try
			{
				if (req.getParameter("E01CCRCID") != null)
					msgAcc.setE02CCRCID(req.getParameter("E01CCRCID"));
				if (req.getParameter("E01PRICUN") != null)
					msgAcc.setE02PRICUN(req.getParameter("E01PRICUN"));
				if (req.getParameter("E01CCRTYP") != null)
					msgAcc.setE02CCRTYP(req.getParameter("E01CCRTYP"));
				if (req.getParameter("E01CCRNUM") != null)
					msgAcc.setE02CCRNUM(req.getParameter("E01CCRNUM"));
				if (req.getParameter("E01TARTYP") != null)
					msgAcc.setE02TARTYP(req.getParameter("E01TARTYP"));
			}
			catch (Exception e)
			{
				flexLog(" error " + e);
			}

			msgAcc.send();
			msgAcc.destroy();
			flexLog("ECC009002 Message Sent");
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
			if (newmessage.getFormatName().equals("ECC009002"))
			{
				accList = new JBObjList();
				boolean firstTime = true;
				String marker = "";

				while (true)
				{
					msgAcc = (ECC009002Message) newmessage;
					marker = msgAcc.getH02FLGMAS();

					if (marker.equals("*"))
					{
						userPO.setAccNum(msgAcc.getE02CCRCRA());
						userPO.setHeader2(msgAcc.getE02TARTYP());
						userPO.setHeader3(msgAcc.getE02CCRBRN());
						userPO.setHeader4(msgAcc.getE02CCRSTS());
						userPO.setHeader5(msgAcc.getE02CCRDSC());
						accList.setShowNext(false);
						break;
					}
					else
					{
						accList.addRow(msgAcc);
						if (firstTime)
						{
							firstTime = false;
							userPO.setAccNum(msgAcc.getE02CCRCRA());
							userPO.setHeader2(msgAcc.getE02TARTYP());
							userPO.setHeader3(msgAcc.getE02CCRBRN());
							userPO.setHeader4(msgAcc.getE02CCRSTS());
							userPO.setHeader5(msgAcc.getE02CCRDSC());
							userPO.setHeader6(msgAcc.getE02CCRASD());
							userPO.setHeader7(msgAcc.getE02CCRASM());
							userPO.setHeader8(msgAcc.getE02CCRASY());
							userPO.setHeader20("");
							userPO.setHeader21("");
						}
						if (marker.equals("+"))
						{
							accList.setShowNext(true);
							break;
						}
					}
					newmessage = mc.receiveMessage();
				}

				flexLog("Putting java beans into the session");
				ses.setAttribute("accList", accList);
				ses.setAttribute("error", msgError);
				ses.setAttribute("userPO", userPO);

				String option = req.getParameter("opt");

				try
				{
					if (option.equals("2"))
					{ // Inquiry
						flexLog("About to call Page: " + LangPath + "ECC0090_card_assign_inq.jsp");
						callPage(LangPath + "ECC0090_card_assign_inq.jsp", req, res);
					}
					else
					{ // Maintenance
						flexLog("About to call Page: " + LangPath + "ECC0090_card_assign_maint.jsp");
						callPage(LangPath + "ECC0090_card_assign_maint.jsp", req, res);
					}
				}
				catch (Exception e)
				{
					flexLog("Exception calling page " + e);
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

	protected void procReqCardAssignAdditionalNew(MessageContext mc, ESS0030DSMessage user, HttpServletRequest req, HttpServletResponse res, HttpSession ses) throws ServletException, IOException
	{
	
		ECC009001Message msgCD = null;
		ELEERRMessage msgError = null;
		UserPos userPO = null;
	
		msgCD = new datapro.eibs.beans.ECC009001Message();
	
		try
		{
			msgCD.setE01CCRCID(req.getParameter("E01CCRCID"));
			msgCD.setE01PRINA1(req.getParameter("E01PRINA1"));
			msgCD.setE01PRICUN(req.getParameter("E01PRICUN"));
			msgCD.setE01CCRNUM(req.getParameter("E01CCRNUM"));
	
			msgError = new datapro.eibs.beans.ELEERRMessage();
			userPO = (datapro.eibs.beans.UserPos) ses.getAttribute("userPO");
	
			ses.setAttribute("error", msgError);
			ses.setAttribute("msgCD", msgCD);
			ses.setAttribute("userPO", userPO);
		}
		catch (Exception ex)
		{
			flexLog("Error: " + ex);
		}
	
		try
		{
			flexLog("About to call Page: " + LangPath + "ECC0090_card_assign_new_add.jsp");
			callPage(LangPath + "ECC0090_card_assign_new_add.jsp", req, res);
		}
		catch (Exception e)
		{
			flexLog("Exception calling page " + e);
		}
	}

	protected void procActionCardAssignAdditionalNew(MessageContext mc, ESS0030DSMessage user, HttpServletRequest req, HttpServletResponse res, HttpSession ses) throws ServletException, IOException
	{
		MessageRecord newmessage = null;
		ECC009003Message msgCD = null;
		ECC009004Message msgAcc = null;
		ELEERRMessage msgError = null;
		UserPos userPO = null;
		JBObjList accList = null;
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
	
		// Send Initial data for ECC009003
		try
		{
			flexLog("Send Initial Data");
			msgCD = (ECC009003Message) mc.getMessageRecord("ECC009003");
			msgCD.setH03USERID(user.getH01USR());
			msgCD.setH03PROGRM("ECC0090");
			msgCD.setH03TIMSYS(getTimeStamp());
			msgCD.setH03SCRCOD("01");
			msgCD.setH03OPECOD("0010");
			try
			{
				if (req.getParameter("E03CCRCID") != null)
					msgCD.setE03CCRCID(req.getParameter("E03CCRCID"));
				if (req.getParameter("E03PRICUN") != null)
					msgCD.setE03PRICUN(req.getParameter("E03PRICUN"));
				if (req.getParameter("E03CCRNUM") != null)
					msgCD.setE03CCRNUM(req.getParameter("E03CCRNUM"));
				if (req.getParameter("E03PRICUNA") != null)
					msgCD.setE03PRICUNA(req.getParameter("E03PRICUNA"));
				if (req.getParameter("E03CCRNUMA") != null)
					msgCD.setE03CCRNUMA(req.getParameter("E03CCRNUMA"));
			}
			catch (Exception e)
			{
				flexLog(" error " + e);
			}
			msgCD.send();
			msgCD.destroy();
			flexLog("ECC009003 Message Sent");
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
	
		// Receive Data for ECC009003
		try
		{
			newmessage = mc.receiveMessage();
	
			if (newmessage.getFormatName().equals("ECC009003"))
			{
				try
				{
					msgCD = new ECC009003Message();
					flexLog("ECC009003 Message Received");
				}
				catch (Exception ex)
				{
					flexLog("Error: " + ex);
				}
	
				msgCD = (ECC009003Message) newmessage;
	
				flexLog("Putting java beans into the session");
				ses.setAttribute("error", msgError);
				ses.setAttribute("msgCD", msgCD);
				ses.setAttribute("userPO", userPO);
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
	
		// Send Initial data for ECC009004
		try
		{
			flexLog("Send Initial Data");
			msgAcc = (ECC009004Message) mc.getMessageRecord("ECC009004");
			msgAcc.setH04USERID(user.getH01USR());
			msgAcc.setH04PROGRM("ECC0090");
			msgAcc.setH04TIMSYS(getTimeStamp());
			msgAcc.setH04SCRCOD("01");
			msgAcc.setH04OPECOD("0015");
	
			// all the fields here
			try
			{
				if (req.getParameter("E03CCRCID") != null)
					msgAcc.setE04CCRCID(req.getParameter("E03CCRCID"));
				if (req.getParameter("E03PRICUN") != null)
					msgAcc.setE04PRICUN(req.getParameter("E03PRICUN"));
				if (req.getParameter("E03CCRNUM") != null)
					msgAcc.setE04CCRNUM(req.getParameter("E03CCRNUM"));
				if (req.getParameter("E03PRICUNA") != null)
					msgAcc.setE04PRICUNA(req.getParameter("E03PRICUNA"));
				if (req.getParameter("E03CCRNUMA") != null)
					msgAcc.setE04CCRNUMA(req.getParameter("E03CCRNUMA"));
			}
			catch (Exception e)
			{
				flexLog(" error " + e);
			}
	
			msgAcc.send();
			msgAcc.destroy();
			flexLog("ECC009004 Message Sent");
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
	
		// Receive Data for ECC009004
		try
		{
			if (newmessage.getFormatName().equals("ECC009004"))
			{
				accList = new JBObjList();
				boolean firstTime = true;
				String marker = "";
	
				while (true)
				{
					msgAcc = (ECC009004Message) newmessage;
					marker = msgAcc.getH04FLGMAS();
	
					if (marker.equals("*"))
					{
						userPO.setAccNum(msgAcc.getE04CCRCRA());
						userPO.setHeader1(msgAcc.getE04CCRNUM());
						userPO.setHeader2(msgAcc.getE04TARTYP());
						userPO.setHeader3(msgAcc.getE04CCRBRN());
						userPO.setHeader4(msgAcc.getE04CCRSTS());
						userPO.setHeader5(msgAcc.getE04CCRDSC());
						userPO.setHeader6(msgAcc.getE04CCRSTD());
						userPO.setHeader7(msgAcc.getE04CCRSTM());
						userPO.setHeader8(msgAcc.getE04CCRSTY());
						userPO.setHeader21("true");
						accList.setShowNext(false);
						break;
					}
					else
					{
						accList.addRow(msgAcc);
						if (firstTime)
						{
							firstTime = false;
							userPO.setAccNum(msgAcc.getE04CCRCRA());
							userPO.setHeader1(msgAcc.getE04CCRNUMA());
							userPO.setHeader2(msgAcc.getE04TARTYP());
							userPO.setHeader3(msgAcc.getE04CCRBRN());
							userPO.setHeader4(msgAcc.getE04CCRSTS());
							userPO.setHeader5(msgAcc.getE04CCRDSC());
							userPO.setHeader6(msgAcc.getE04CCRSTD());
							userPO.setHeader7(msgAcc.getE04CCRSTM());
							userPO.setHeader8(msgAcc.getE04CCRSTY());
							userPO.setHeader20("");
							userPO.setHeader21("");
						}
						if (marker.equals("+"))
						{
							accList.setShowNext(true);
							break;
						}
					}
					newmessage = mc.receiveMessage();
				}
	
				flexLog("Putting java beans into the session");
				ses.setAttribute("accList", accList);
				ses.setAttribute("error", msgError);
				ses.setAttribute("userPO", userPO);
	
				try
				{
					flexLog("About to call Page: " + LangPath + "ECC0090_card_assign_maint_add.jsp");
					callPage(LangPath + "ECC0090_card_assign_maint_add.jsp", req, res);
				}
				catch (Exception e)
				{
					flexLog("Exception calling page " + e);
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

	protected void procActionCardAssignAdditionalMaint(MessageContext mc, ESS0030DSMessage user, HttpServletRequest req, HttpServletResponse res, HttpSession ses) throws ServletException, IOException
	{
		MessageRecord newmessage = null;
		ECC009003Message msgCD = null;
		ECC009004Message msgAcc = null;
		JBObjList accList = null;
		ELEERRMessage msgError = null;
		boolean IsNotError = false;
		UserPos userPO = null;
		String action = null;
	
		userPO = (datapro.eibs.beans.UserPos) ses.getAttribute("userPO");
		msgCD = (datapro.eibs.beans.ECC009003Message) ses.getAttribute("msgCD");
	
		// Send Initial data
		try
		{
			msgAcc = (ECC009004Message) mc.getMessageRecord("ECC009004");
			msgAcc.setH04USERID(user.getH01USR());
			msgAcc.setH04PROGRM("ECC0090");
			msgAcc.setH04TIMSYS(getTimeStamp());
			msgAcc.setH04SCRCOD("01");
			if (req.getParameter("new").equals("true"))
				msgAcc.setH04OPECOD("0001");
			else
				msgAcc.setH04OPECOD("0005");
	
			Calendar cal = new java.util.GregorianCalendar();
	
			// Send Unassigned Accounts
			String[] accounts = req.getParameterValues("AccList");
			if (accounts != null)
			{
				for (int i = 0; i < accounts.length; i++)
				{
					StringTokenizer acc = new StringTokenizer(accounts[i], "-");
					msgAcc.setE04TARTYP(req.getParameter("E04TARTYP"));
					msgAcc.setE04CCRNUM(req.getParameter("E03CCRNUM"));
					msgAcc.setE04CCRNUMA(req.getParameter("E04CCRNUMA"));
					msgAcc.setE04PRICUN(req.getParameter("E03PRICUN"));
					msgAcc.setE04PRICUNA(req.getParameter("E03PRICUNA"));
					msgAcc.setE04CCRCID(req.getParameter("E03CCRCID"));
					msgAcc.setE04CCRCIDA(req.getParameter("E03CCRCIDA"));
					msgAcc.setE04CCRBRN(req.getParameter("E04CCRBRN"));
					msgAcc.setE04CCRCRA(acc.nextToken());
					msgAcc.setE04CCRASG("");
					msgAcc.setE04CCRASD("" + cal.get(Calendar.DAY_OF_MONTH));
					msgAcc.setE04CCRASM("" + (cal.get(Calendar.MONTH) + 1));
					msgAcc.setE04CCRASY("" + cal.get(Calendar.YEAR));
					msgAcc.send();
				}
			}
	
			// Send Main Accounts
			if (!req.getParameter("CCP").equals(""))
			{
				msgAcc.setE04TARTYP(req.getParameter("E04TARTYP"));
				msgAcc.setE04CCRNUM(req.getParameter("E03CCRNUM"));
				msgAcc.setE04CCRNUMA(req.getParameter("E04CCRNUMA"));
				msgAcc.setE04PRICUN(req.getParameter("E03PRICUN"));
				msgAcc.setE04PRICUNA(req.getParameter("E03PRICUNA"));
				msgAcc.setE04CCRCID(req.getParameter("E03CCRCID"));
				msgAcc.setE04CCRCIDA(req.getParameter("E03CCRCIDA"));
				msgAcc.setE04CCRBRN(req.getParameter("E04CCRBRN"));
				msgAcc.setE04CCRCRA(req.getParameter("CCP"));
				msgAcc.setE04CCRASG("P");
				if (req.getParameter("CC_Main").equals(req.getParameter("CCP")))
				{
					msgAcc.setE04CCRPRI("*");
				}
				else
				{
					msgAcc.setE04CCRPRI("");
				}
				msgAcc.setE04CCRASD("" + cal.get(Calendar.DAY_OF_MONTH));
				msgAcc.setE04CCRASM("" + (cal.get(Calendar.MONTH) + 1));
				msgAcc.setE04CCRASY("" + cal.get(Calendar.YEAR));
				msgAcc.send();
			}
			if (!req.getParameter("CAP").equals(""))
			{
				msgAcc.setE04TARTYP(req.getParameter("E04TARTYP"));
				msgAcc.setE04CCRNUM(req.getParameter("E03CCRNUM"));
				msgAcc.setE04CCRNUMA(req.getParameter("E04CCRNUMA"));
				msgAcc.setE04PRICUN(req.getParameter("E03PRICUN"));
				msgAcc.setE04PRICUNA(req.getParameter("E03PRICUNA"));
				msgAcc.setE04CCRCID(req.getParameter("E03CCRCID"));
				msgAcc.setE04CCRCIDA(req.getParameter("E03CCRCIDA"));
				msgAcc.setE04CCRBRN(req.getParameter("E04CCRBRN"));
				msgAcc.setE04CCRCRA(req.getParameter("CAP"));
				msgAcc.setE04CCRASG("P");
				if (req.getParameter("CC_Main").equals(req.getParameter("CAP")))
				{
					msgAcc.setE04CCRPRI("*");
				}
				else
				{
					msgAcc.setE04CCRPRI("");
				}
				msgAcc.setE04CCRASD("" + cal.get(Calendar.DAY_OF_MONTH));
				msgAcc.setE04CCRASM("" + (cal.get(Calendar.MONTH) + 1));
				msgAcc.setE04CCRASY("" + cal.get(Calendar.YEAR));
				msgAcc.send();
			}
			if (!req.getParameter("CFP").equals(""))
			{
				msgAcc.setE04TARTYP(req.getParameter("E04TARTYP"));
				msgAcc.setE04CCRNUM(req.getParameter("E03CCRNUM"));
				msgAcc.setE04CCRNUMA(req.getParameter("E04CCRNUMA"));
				msgAcc.setE04PRICUN(req.getParameter("E03PRICUN"));
				msgAcc.setE04PRICUNA(req.getParameter("E03PRICUNA"));
				msgAcc.setE04CCRCID(req.getParameter("E03CCRCID"));
				msgAcc.setE04CCRCIDA(req.getParameter("E03CCRCIDA"));
				msgAcc.setE04CCRBRN(req.getParameter("E04CCRBRN"));
				msgAcc.setE04CCRCRA(req.getParameter("CFP"));
				msgAcc.setE04CCRASG("P");
				if (req.getParameter("CC_Main").equals(req.getParameter("CFP")))
				{
					msgAcc.setE04CCRPRI("*");
				}
				else
				{
					msgAcc.setE04CCRPRI("");
				}
				msgAcc.setE04CCRASD("" + cal.get(Calendar.DAY_OF_MONTH));
				msgAcc.setE04CCRASM("" + (cal.get(Calendar.MONTH) + 1));
				msgAcc.setE04CCRASY("" + cal.get(Calendar.YEAR));
				msgAcc.send();
			}
	
			// Send Secondary Accounts
			String[] accounts2 = req.getParameterValues("AccSList");
			if (accounts2 != null)
			{
				for (int j = 0; j < accounts2.length; j++)
				{
					StringTokenizer accs = new StringTokenizer(accounts2[j], "-");
					msgAcc.setE04TARTYP(req.getParameter("E04TARTYP"));
					msgAcc.setE04CCRNUM(req.getParameter("E03CCRNUM"));
					msgAcc.setE04CCRNUMA(req.getParameter("E04CCRNUMA"));
					msgAcc.setE04PRICUN(req.getParameter("E03PRICUN"));
					msgAcc.setE04PRICUNA(req.getParameter("E03PRICUNA"));
					msgAcc.setE04CCRCID(req.getParameter("E03CCRCID"));
					msgAcc.setE04CCRCIDA(req.getParameter("E03CCRCIDA"));
					msgAcc.setE04CCRBRN(req.getParameter("E04CCRBRN"));
					msgAcc.setE04CCRCRA(accs.nextToken());
					msgAcc.setE04CCRASG("S");
					msgAcc.setE04CCRPRI("");
					msgAcc.setE04CCRASD("" + cal.get(Calendar.DAY_OF_MONTH));
					msgAcc.setE04CCRASM("" + (cal.get(Calendar.MONTH) + 1));
					msgAcc.setE04CCRASY("" + cal.get(Calendar.YEAR));
					msgAcc.send();
				}
			}
	
			// Send Final Flag
			msgAcc.setH04FLGMAS("*");
			msgAcc.send();
			msgAcc.destroy();
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
	
			flexLog("Putting java beans into the session");
			ses.setAttribute("msgCD", msgCD);
			ses.setAttribute("error", msgError);
			ses.setAttribute("userPO", userPO);
	
			if (IsNotError) {
				if (userPO.getOption().equals("T")) {
					flexLog("About to call: /servlet/datapro.eibs.products.JSECC0090?SCREEN=200&E03PRICUN=" + userPO.getCusNum());
					res.sendRedirect(super.srctx + "/servlet/datapro.eibs.products.JSECC0090?SCREEN=200&E03PRICUN=" + userPO.getCusNum());
				}
				else {
					flexLog("About to call: /servlet/datapro.eibs.products.JSECC0090?SCREEN=200&E03PRICUNA=" + userPO.getHeader10());
					res.sendRedirect(super.srctx + "/servlet/datapro.eibs.products.JSECC0090?SCREEN=200&E03PRICUNA=" + userPO.getHeader10());
				}
			}
			else
			{
				flexLog("About to call Page: " + LangPath + "ECC0090_card_assign_maint_add.jsp");
				callPage(LangPath + "ECC0090_card_assign_maint_add.jsp", req, res);
			}
	
		}
		catch (Exception e)
		{
			e.printStackTrace();
			flexLog("Error: " + e);
			throw new RuntimeException("Socket Communication Error");
		}
	}

	protected void procReqCardAssignAdditionalInquiryMaint(MessageContext mc, ESS0030DSMessage user, HttpServletRequest req, HttpServletResponse res, HttpSession ses) throws ServletException, IOException
	{
		MessageRecord newmessage = null;
		ECC009003Message msgCD = null;
		ECC009004Message msgAcc = null;
		ELEERRMessage msgError = null;
		UserPos userPO = null;
		JBObjList accList = null;
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
	
		// Send Initial data for ECC009003
		try
		{
			flexLog("Send Initial Data");
			msgCD = (ECC009003Message) mc.getMessageRecord("ECC009003");
			msgCD.setH03USERID(user.getH01USR());
			msgCD.setH03PROGRM("ECC0090");
			msgCD.setH03TIMSYS(getTimeStamp());
			msgCD.setH03SCRCOD("01");
			try
			{
				if (req.getParameter("new").equals("true"))
					msgCD.setH03OPECOD("0010");
			}
			catch (Exception e)
			{
				msgCD.setH03OPECOD("0002");
			}
	
			try
			{
				msgCD.setE03CCRCID(req.getParameter("E03CCRCID"));
			}
			catch (Exception e)
			{}
			try
			{
				msgCD.setE03PRICUN(req.getParameter("E03PRICUN"));
			}
			catch (Exception e)
			{}
			try
			{
				msgCD.setE03CCRNUM(req.getParameter("E03CCRNUM"));
			}
			catch (Exception e)
			{}
			try
			{
				msgCD.setE03CCRCIDA(req.getParameter("E03CCRCIDA"));
			}
			catch (Exception e)
			{
				msgCD.setE03CCRCIDA(userPO.getHeader9());
			}
			try
			{
				msgCD.setE03PRICUNA(req.getParameter("E03PRICUNA"));
			}
			catch (Exception e)
			{
				msgCD.setE03PRICUNA(userPO.getHeader10());
			}
			try
			{
				msgCD.setE03CCRNUMA(req.getParameter("E03CCRNUMA"));
			}
			catch (Exception e)
			{
				msgCD.setE03CCRNUMA(userPO.getHeader13());
			}
	
			msgCD.send();
			msgCD.destroy();
			flexLog("ECC009003 Message Sent");
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
	
		// Receive Data for ECC009003
		try
		{
			newmessage = mc.receiveMessage();
	
			if (newmessage.getFormatName().equals("ECC009003"))
			{
				try
				{
					msgCD = new ECC009003Message();
					flexLog("ECC009003 Message Received");
				}
				catch (Exception ex)
				{
					flexLog("Error: " + ex);
				}
	
				msgCD = (ECC009003Message) newmessage;
	
				flexLog("Putting java beans into the session");
				ses.setAttribute("error", msgError);
				ses.setAttribute("msgCD", msgCD);
				ses.setAttribute("userPO", userPO);
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
	
		// Send Initial data for ECC009004
		try
		{
			flexLog("Send Initial Data");
			msgAcc = (ECC009004Message) mc.getMessageRecord("ECC009004");
			msgAcc.setH04USERID(user.getH01USR());
			msgAcc.setH04PROGRM("ECC0090");
			msgAcc.setH04TIMSYS(getTimeStamp());
			msgAcc.setH04SCRCOD("01");
			try
			{
				if (req.getParameter("new").equals("true"))
					msgAcc.setH04OPECOD("0015");
			}
			catch (Exception e)
			{
				msgAcc.setH04OPECOD("0016");
			}
	
			// all the fields here
			try
			{
				msgAcc.setE04CCRCID(req.getParameter("E03CCRCID"));
			}
			catch (Exception e)
			{
				msgAcc.setE04CCRCID(msgCD.getE03CCRCID());
			}
			try
			{
				msgAcc.setE04PRICUN(req.getParameter("E03PRICUN"));
			}
			catch (Exception e)
			{
				msgAcc.setE04PRICUN(msgCD.getE03PRICUN());
			}
			try
			{
				msgAcc.setE04CCRNUM(req.getParameter("E03CCRNUM"));
			}
			catch (Exception e)
			{
				msgAcc.setE04CCRNUM(msgCD.getE03CCRNUM());
			}
			try
			{
				msgAcc.setE04CCRCIDA(req.getParameter("E03CCRCIDA"));
			}
			catch (Exception e)
			{
				msgAcc.setE04CCRCIDA(userPO.getHeader9());
			}
			try
			{
				msgAcc.setE04PRICUNA(req.getParameter("E03PRICUNA"));
			}
			catch (Exception e)
			{
				msgAcc.setE04PRICUNA(userPO.getHeader10());
			}
			try
			{
				msgAcc.setE04CCRNUMA(req.getParameter("E03CCRNUMA"));
			}
			catch (Exception e)
			{
				msgAcc.setE04CCRNUMA(userPO.getHeader13());
			}
	
			msgAcc.send();
			msgAcc.destroy();
			flexLog("ECC009004 Message Sent");
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
	
		// Receive Data for ECC009004
		try
		{
			if (newmessage.getFormatName().equals("ECC009004"))
			{
				accList = new JBObjList();
				boolean firstTime = true;
				String marker = "";
	
				while (true)
				{
					msgAcc = (ECC009004Message) newmessage;
					marker = msgAcc.getH04FLGMAS();
	
					if (marker.equals("*"))
					{
						userPO.setIdentifier(msgAcc.getE04CCRCID());
						userPO.setAccNum(msgAcc.getE04CCRCRA());
						userPO.setCusNum(msgAcc.getE04PRICUN());
						userPO.setHeader1(msgAcc.getE04CCRNUM());
						userPO.setHeader2(msgAcc.getE04TARTYP());
						userPO.setHeader3(msgAcc.getE04CCRBRN());
						userPO.setHeader4(msgAcc.getE04CCRSTS());
						userPO.setHeader5(msgAcc.getE04CCRDSC());
						accList.setShowNext(false);
						break;
					}
					else
					{
						accList.addRow(msgAcc);
						if (firstTime)
						{
							firstTime = false;
							userPO.setIdentifier(msgAcc.getE04CCRCIDA());
							userPO.setAccNum(msgAcc.getE04CCRCRA());
							userPO.setCusNum(msgAcc.getE04PRICUNA());
							userPO.setHeader1(msgAcc.getE04CCRNUMA());
							userPO.setHeader2(msgAcc.getE04TARTYP());
							userPO.setHeader3(msgAcc.getE04CCRBRN());
							userPO.setHeader4(msgAcc.getE04CCRSTS());
							userPO.setHeader5(msgAcc.getE04CCRDSC());
							userPO.setHeader6(msgAcc.getE04CCRASD());
							userPO.setHeader7(msgAcc.getE04CCRASM());
							userPO.setHeader8(msgAcc.getE04CCRASY());
							userPO.setHeader20("");
							userPO.setHeader21("");
						}
						if (marker.equals("+"))
						{
							accList.setShowNext(true);
							break;
						}
					}
					newmessage = mc.receiveMessage();
				}
	
				flexLog("Putting java beans into the session");
				ses.setAttribute("accList", accList);
				ses.setAttribute("error", msgError);
				ses.setAttribute("userPO", userPO);
	
				String option = req.getParameter("opt");
	
				try
				{
					if (option.equals("2"))
					{ // Inquiry
						flexLog("About to call Page: " + LangPath + "ECC0090_card_assign_inq_add.jsp");
						callPage(LangPath + "ECC0090_card_assign_inq_add.jsp", req, res);
					}
					else
					{ // Maintenance
						flexLog("About to call Page: " + LangPath + "ECC0090_card_assign_maint_add.jsp");
						callPage(LangPath + "ECC0090_card_assign_maint_add.jsp", req, res);
					}
				}
				catch (Exception e)
				{
					flexLog("Exception calling page " + e);
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

	protected void procReqCardStatusEnter(MessageContext mc, ESS0030DSMessage user, HttpServletRequest req, HttpServletResponse res, HttpSession ses) throws ServletException, IOException
	{
		ELEERRMessage msgError = null;
		UserPos userPO = null;
	
		try
		{
			msgError = new datapro.eibs.beans.ELEERRMessage();
			userPO = new datapro.eibs.beans.UserPos();
	
			ses.setAttribute("error", msgError);
			ses.setAttribute("userPO", userPO);
		}
		catch (Exception ex)
		{
			flexLog("Error: " + ex);
		}
	
		try
		{
			flexLog("About to call Page: " + LangPath + "ECC0090_card_status_enter.jsp");
			callPage(LangPath + "ECC0090_card_status_enter.jsp", req, res);
		}
		catch (Exception e)
		{
			flexLog("Exception calling page " + e);
		}
	}

	protected void procReqCardStatusEnterInq(MessageContext mc, ESS0030DSMessage user, HttpServletRequest req, HttpServletResponse res, HttpSession ses) throws ServletException, IOException
	{
		MessageRecord newmessage = null;
		ECC009001Message msgCD = null;
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
	
		if (req.getParameter("opt") != null)
			userPO.setOption(req.getParameter("opt"));
	
		// Send Initial data
		try
		{
			flexLog("Send Initial Data");
			msgCD = (ECC009001Message) mc.getMessageRecord("ECC009001");
			msgCD.setH01USERID(user.getH01USR());
			msgCD.setH01PROGRM("ECC0090");
			msgCD.setH01TIMSYS(getTimeStamp());
			msgCD.setH01SCRCOD("01");
			msgCD.setH01OPECOD("0002");
			try {
				msgCD.setE01PRICUN(req.getParameter("E01PRICUN"));
			}
			catch (Exception e)	{
				msgCD.setE01PRICUN(req.getParameter("E02PRICUN"));
			}
			try
			{
				msgCD.setE01CCRNUM(req.getParameter("E01CCRNUM"));
			}
			catch (Exception e)
			{
				if (req.getParameter("E02CCRNUM") != null)
					msgCD.setE01CCRNUM(req.getParameter("E02CCRNUM"));
			}
	
			msgCD.send();
			msgCD.destroy();
			flexLog("ECC009001 Message Sent");
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
	
			if (newmessage.getFormatName().equals("ECC009001"))
			{
				try
				{
					msgCD = new ECC009001Message();
					flexLog("ECC009001 Message Received");
				}
				catch (Exception ex)
				{
					flexLog("Error: " + ex);
				}
	
				msgCD = (ECC009001Message) newmessage;
	
				flexLog("Putting java beans into the session");
				ses.setAttribute("error", msgError);
				ses.setAttribute("msgCD", msgCD);
				ses.setAttribute("userPO", userPO);
	
				if (IsNotError)
				{ // There are no errors
					try
					{
						flexLog("About to call Page: " + LangPath + "ECC0090_card_status_info.jsp");
						callPage(LangPath + "ECC0090_card_status_info.jsp", req, res);
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
						flexLog("About to call Page: " + LangPath + "ECC0090_card_status_enter.jsp");
						callPage(LangPath + "ECC0090_card_status_enter.jsp", req, res);
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

	protected void procActionCardStatus(MessageContext mc, ESS0030DSMessage user, HttpServletRequest req, HttpServletResponse res, HttpSession ses) throws ServletException, IOException
	{
		MessageRecord newmessage = null;
		ECC009001Message msgCD = null;
		ELEERRMessage msgError = null;
		UserPos userPO = null;
		JBObjList accList = null;
		boolean IsNotError = false;

		try
		{
			msgError = new ELEERRMessage();
		}
		catch (Exception ex)
		{
			flexLog("Error: " + ex);
		}

		// Send Initial data
		try
		{
			flexLog("Send Initial Data");
			msgCD = (ECC009001Message) mc.getMessageRecord("ECC009001");
			msgCD.setH01USERID(user.getH01USR());
			msgCD.setH01PROGRM("ECC0090");
			msgCD.setH01TIMSYS(getTimeStamp());
			msgCD.setH01SCRCOD("01");
			msgCD.setH01OPECOD("0009");

			// all the fields here
			try
			{
				if (req.getParameter("E01CCRCID") != null)
					msgCD.setE01CCRCID(req.getParameter("E01CCRCID"));
				if (req.getParameter("E01PRICUN") != null)
					msgCD.setE01PRICUN(req.getParameter("E01PRICUN"));
				if (req.getParameter("E01PRINA1") != null)
					msgCD.setE01PRINA1(req.getParameter("E01PRINA1"));
				if (req.getParameter("E01CCRTYP") != null)
					msgCD.setE01CCRTYP(req.getParameter("E01CCRTYP"));
				if (req.getParameter("E01CCRNUM") != null)
					msgCD.setE01CCRNUM(req.getParameter("E01CCRNUM"));
				if (req.getParameter("E01TARTYP") != null)
					msgCD.setE01TARTYP(req.getParameter("E01TARTYP"));
				if (req.getParameter("E01CCRSTS") != null)
				{
					msgCD.setE01CCRSTS(req.getParameter("E01CCRSTS"));
					msgCD.setE01CCRDSC(req.getParameter("E01CCRDSC"));
				}
			}
			catch (Exception e)
			{
				flexLog(" error " + e);
			}

			msgCD.send();
			msgCD.destroy();
			flexLog("ECC009001 Message Sent");
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

			if (newmessage.getFormatName().equals("ECC009001"))
			{
				try
				{
					msgCD = new ECC009001Message();
					flexLog("ECC009001 Message Received");
				}
				catch (Exception ex)
				{
					flexLog("Error: " + ex);
				}

				msgCD = (ECC009001Message) newmessage;

				flexLog("Putting java beans into the session");
				ses.setAttribute("error", msgError);
				ses.setAttribute("msgCD", msgCD);
				ses.setAttribute("userPO", userPO);

				if (IsNotError)
				{ // There are no errors
					try
					{
						if (req.getParameter("opt").equals("S"))
						{
							flexLog("About to call: /servlet/datapro.eibs.products.JSECC0090?SCREEN=300");
							res.sendRedirect(super.srctx + "/servlet/datapro.eibs.products.JSECC0090?SCREEN=300");
						}
						else
						{
							flexLog("About to call: /servlet/datapro.eibs.products.JSECC0090?SCREEN=100");
							res.sendRedirect(super.srctx + "/servlet/datapro.eibs.products.JSECC0090?SCREEN=100");
						}
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
						flexLog("About to call Page: " + LangPath + "ECC0090_card_status_info.jsp");
						callPage(LangPath + "ECC0090_card_status_info.jsp", req, res);
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

	protected void procReqCardAssignReplace(MessageContext mc, ESS0030DSMessage user, HttpServletRequest req, HttpServletResponse res, HttpSession ses) throws ServletException, IOException
	{
		MessageRecord newmessage = null;
		ECC009001Message msgCD = null;
		ELEERRMessage msgError = null;
		UserPos userPO = null;
		boolean IsNotError = false;
	
		userPO = (datapro.eibs.beans.UserPos) ses.getAttribute("userPO");
	
		try
		{
			msgError = new datapro.eibs.beans.ELEERRMessage();
	
			userPO.setType(req.getParameter("E01CCRTPI"));
	
			if (userPO.getType().equals("T"))
			{
				userPO.setHeader1(req.getParameter("E01CCRNUM"));
			}
			else
			{
				userPO.setHeader13(req.getParameter("E01CCRNUM"));
			}
	
			ses.setAttribute("error", msgError);
			ses.setAttribute("userPO", userPO);
		}
		catch (Exception ex)
		{
			flexLog("Error: " + ex);
		}
	
		if (userPO.getType().equals("T"))
		{
			try
			{
				flexLog("About to call Page: " + LangPath + "ECC0090_card_assign_replace.jsp");
				callPage(LangPath + "ECC0090_card_assign_replace.jsp", req, res);
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
				flexLog("About to call Page: " + LangPath + "ECC0090_card_assign_replace_add.jsp");
				callPage(LangPath + "ECC0090_card_assign_replace_add.jsp", req, res);
			}
			catch (Exception e)
			{
				flexLog("Exception calling page " + e);
			}
		}
	}

	protected void procActionCardAssignReplace(MessageContext mc, ESS0030DSMessage user, HttpServletRequest req, HttpServletResponse res, HttpSession ses) throws ServletException, IOException
	{
		MessageRecord newmessage = null;
		ECC009001Message msgCD = null;
		ECC009003Message msgCD2 = null;
		ELEERRMessage msgError = null;
		UserPos userPO = null;
		JBObjList accList = null;
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
	
		if (userPO.getType().equals("T"))
		{
			// Send Initial data for ECC009001
			try
			{
				flexLog("Send Initial Data");
				msgCD = (ECC009001Message) mc.getMessageRecord("ECC009001");
				msgCD.setH01USERID(user.getH01USR());
				msgCD.setH01PROGRM("ECC0090");
				msgCD.setH01TIMSYS(getTimeStamp());
				msgCD.setH01SCRCOD("01");
				msgCD.setH01OPECOD("0007");
	
				// all the fields here
				try
				{
					if (req.getParameter("E01CCRCID") != null)
						msgCD.setE01CCRCID(req.getParameter("E01CCRCID"));
					if (req.getParameter("E01PRICUN") != null)
						msgCD.setE01PRICUN(req.getParameter("E01PRICUN"));
					if (req.getParameter("E01PRINA1") != null)
						msgCD.setE01PRINA1(req.getParameter("E01PRINA1"));
					if (req.getParameter("E01CCRTYP") != null)
						msgCD.setE01CCRTYP(req.getParameter("E01CCRTYP"));
					if (req.getParameter("E01CCRNUM") != null)
						msgCD.setE01CCRNUM(req.getParameter("E01CCRNUM"));
					if (req.getParameter("E01CCRNEW") != null)
						msgCD.setE01CCRNEW(req.getParameter("E01CCRNEW"));
					if (req.getParameter("E01CCRSTS") != null)
					{
						msgCD.setE01CCRSTS(req.getParameter("E01CCRSTS"));
						msgCD.setE01CCRDSC(req.getParameter("E01CCRDSC"));
					}
				}
				catch (Exception e)
				{
					flexLog(" error " + e);
				}
	
				msgCD.send();
				msgCD.destroy();
				flexLog("ECC009001 Message Sent");
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
	
			// Receive Data for ECC009001
			try
			{
				newmessage = mc.receiveMessage();
	
				if (newmessage.getFormatName().equals("ECC009001"))
				{
					try
					{
						msgCD = new ECC009001Message();
						flexLog("ECC009001 Message Received");
					}
					catch (Exception ex)
					{
						flexLog("Error: " + ex);
					}
	
					msgCD = (ECC009001Message) newmessage;
	
					flexLog("Putting java beans into the session");
					ses.setAttribute("error", msgError);
					ses.setAttribute("userPO", userPO);
		
					if (IsNotError) { // There are no errors
						try {
							flexLog("About to call: /servlet/datapro.eibs.products.JSECC0090?SCREEN=100");
							res.sendRedirect(super.srctx + "/servlet/datapro.eibs.products.JSECC0090?SCREEN=100");
						} catch (Exception e) {
							flexLog("Exception calling page " + e);
						}
					}
					else
					{ // There are errors
						try
						{
							flexLog("About to call Page: " + LangPath + "ECC0090_card_assign_replace.jsp");
							callPage(LangPath + "ECC0090_card_assign_replace.jsp", req, res);
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
		else
		{
			// Send Initial data for ECC009003
			try
			{
				flexLog("Send Initial Data");
				msgCD2 = (ECC009003Message) mc.getMessageRecord("ECC009003");
				msgCD2.setH03USERID(user.getH01USR());
				msgCD2.setH03PROGRM("ECC0090");
				msgCD2.setH03TIMSYS(getTimeStamp());
				msgCD2.setH03SCRCOD("01");
				msgCD2.setH03OPECOD("0007");
	
				// all the fields here
				try
				{
					if (req.getParameter("E01CCRCID") != null)
						msgCD2.setE03CCRCID(req.getParameter("E01CCRCID"));
					if (req.getParameter("E01PRICUN") != null)
						msgCD2.setE03PRICUN(req.getParameter("E01PRICUN"));
					if (req.getParameter("E01PRINA1") != null)
						msgCD2.setE03PRINA1(req.getParameter("E01PRINA1"));
					if (req.getParameter("E01CCRTYP") != null)
						msgCD2.setE03CCRTYP(req.getParameter("E01CCRTYP"));
					if (req.getParameter("E01CCRNUM") != null)
						msgCD2.setE03CCRNUM(req.getParameter("E01CCRNUM"));
					if (req.getParameter("E01CCRNUMA") != null)
						msgCD2.setE03CCRNUMA(req.getParameter("E01CCRNUMA"));
					if (req.getParameter("E01CCRNEW") != null)
						msgCD2.setE03CCRNEW(req.getParameter("E01CCRNEW"));
					if (req.getParameter("E01CCRSTS") != null)
					{
						msgCD2.setE03CCRSTS(req.getParameter("E01CCRSTS"));
						msgCD2.setE03CCRDSC(req.getParameter("E01CCRDSC"));
					}
				}
				catch (Exception e)
				{
					flexLog(" error " + e);
				}
	
				msgCD2.send();
				msgCD2.destroy();
				flexLog("ECC009003 Message Sent");
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
	
			// Receive Data for ECC009003
			try
			{
				newmessage = mc.receiveMessage();
	
				if (newmessage.getFormatName().equals("ECC009003"))
				{
					try
					{
						msgCD2 = new ECC009003Message();
						flexLog("ECC009003 Message Received");
					}
					catch (Exception ex)
					{
						flexLog("Error: " + ex);
					}
	
					msgCD2 = (ECC009003Message) newmessage;
	
					flexLog("Putting java beans into the session");
					ses.setAttribute("error", msgError);
					ses.setAttribute("userPO", userPO);
	
					if (IsNotError) { // There are no errors
						try {
							flexLog("About to call: /servlet/datapro.eibs.products.JSECC0090?SCREEN=100");
							res.sendRedirect(super.srctx + "/servlet/datapro.eibs.products.JSECC0090?SCREEN=100");
						} catch (Exception e) {
							flexLog("Exception calling page " + e);
						}
					}
					else
					{ // There are errors
						try
						{
							flexLog("About to call Page: " + LangPath + "ECC0090_card_assign_replace_add.jsp");
							callPage(LangPath + "ECC0090_card_assign_replace_add.jsp", req, res);
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

	protected void procReqCardAssignPrint(MessageContext mc, ESS0030DSMessage user, HttpServletRequest req, HttpServletResponse res, HttpSession ses) throws ServletException, IOException
	{
		ELEERRMessage msgError = null;
		UserPos userPO = (datapro.eibs.beans.UserPos) ses.getAttribute("userPO");
	
		try
		{
			msgError = new datapro.eibs.beans.ELEERRMessage();
	
			userPO.setType(req.getParameter("E01CCRTPI"));
	
			if (userPO.getType().equals("T"))
			{
				userPO.setHeader1(req.getParameter("E01CCRNUM"));
			}
			else
			{
				userPO.setHeader13(req.getParameter("E01CCRNUM"));
			}
	
			ses.setAttribute("error", msgError);
			ses.setAttribute("userPO", userPO);
		}
		catch (Exception ex)
		{
			flexLog("Error: " + ex);
		}
	
		try
		{
			flexLog("About to call Page: " + LangPath + "ECC0090_card_print.jsp");
			callPage(LangPath + "ECC0090_card_print.jsp", req, res);
		}
		catch (Exception e)
		{
			flexLog("Exception calling page " + e);
		}
	}

	protected void procReqAssignmentInquiryEnter(MessageContext mc, ESS0030DSMessage user, HttpServletRequest req, HttpServletResponse res, HttpSession ses) throws ServletException, IOException
	{
		ELEERRMessage msgError = null;
		UserPos userPO = null;

		try
		{
			msgError = new datapro.eibs.beans.ELEERRMessage();
			userPO = new datapro.eibs.beans.UserPos();

			ses.setAttribute("error", msgError);
			ses.setAttribute("userPO", userPO);
		}
		catch (Exception ex)
		{
			flexLog("Error: " + ex);
		}

		try
		{
			flexLog("About to call Page: " + LangPath + "ECC0090_assignment_inq_enter.jsp");
			callPage(LangPath + "ECC0090_assignment_inq_enter.jsp", req, res);
		}
		catch (Exception e)
		{
			flexLog("Exception calling page " + e);
		}
	}

	protected void procActionAssignmentInquiryID(MessageContext mc, ESS0030DSMessage user, HttpServletRequest req, HttpServletResponse res, HttpSession ses) throws ServletException, IOException
	{
		MessageRecord newmessage = null;
		ECC009001Message msgCD = null;
		ECC009002Message msgAcc = null;
		ELEERRMessage msgError = null;
		UserPos userPO = null;
		JBObjList cardList = null;
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

		// Send Initial data for ECC009001
		try
		{
			flexLog("Send Initial Data");
			msgCD = (ECC009001Message) mc.getMessageRecord("ECC009001");
			msgCD.setH01USERID(user.getH01USR());
			msgCD.setH01PROGRM("ECC0090");
			msgCD.setH01TIMSYS(getTimeStamp());
			msgCD.setH01SCRCOD("01");
			msgCD.setH01OPECOD("0003");
			try {
				if (req.getParameter("E01PRICUN") != null)
					msgCD.setE01PRICUN(req.getParameter("E01PRICUN"));
			}
			catch (Exception e)	{
				msgCD.setE01PRICUN("0");
			}
			try
			{
				if (req.getParameter("E01CCRCID") != null)
					msgCD.setE01CCRCID(req.getParameter("E01CCRCID"));
			}
			catch (Exception e)
			{
				msgCD.setE01CCRCID("0");
				flexLog(" error " + e);
			}
			msgCD.send();
			msgCD.destroy();
			flexLog("ECC009001 Message Sent");
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

		// Receive Data for ECC009001
		try
		{
			newmessage = mc.receiveMessage();

			if (newmessage.getFormatName().equals("ECC009001"))
			{
				try
				{
					msgCD = new ECC009001Message();
					flexLog("ECC009001 Message Received");
				}
				catch (Exception ex)
				{
					flexLog("Error: " + ex);
				}

				msgCD = (ECC009001Message) newmessage;

				flexLog("Putting java beans into the session");
				ses.setAttribute("error", msgError);
				ses.setAttribute("msgCD", msgCD);
				ses.setAttribute("userPO", userPO);
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

		// Send Initial data for ECC009002
		try
		{
			flexLog("Send Initial Data");
			msgAcc = (ECC009002Message) mc.getMessageRecord("ECC009002");
			msgAcc.setH02USERID(user.getH01USR());
			msgAcc.setH02PROGRM("ECC0090");
			msgAcc.setH02TIMSYS(getTimeStamp());
			msgAcc.setH02SCRCOD("01");
			msgAcc.setH02OPECOD("0002");

			// all the fields here
			try {
				if (req.getParameter("E01PRICUN") != null)
				  msgAcc.setE02PRICUN(req.getParameter("E01PRICUN"));
			}
			catch (Exception e)
			{
				flexLog(" error " + e);
			}

			msgAcc.send();
			msgAcc.destroy();
			flexLog("ECC009002 Message Sent");
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

		// Receive Data for ECC009002
		try
		{
			if (newmessage.getFormatName().equals("ECC009002"))
			{
				cardList = new JBObjList();
				boolean firstTime = true;
				String marker = "";

				while (true)
				{
					msgAcc = (ECC009002Message) newmessage;
					marker = msgAcc.getH02FLGMAS();

					if (marker.equals("*"))
					{
						userPO.setIdentifier(msgAcc.getE02CCRCID());
						userPO.setAccNum(msgAcc.getE02CCRCRA());
						userPO.setCusNum(msgAcc.getE02PRICUN());
						userPO.setHeader1(msgAcc.getE02CCRNUM());
						userPO.setHeader2(msgAcc.getE02TARTYP());
						userPO.setHeader3(msgAcc.getE02CCRBRN());
						userPO.setHeader4(msgAcc.getE02CCRSTS());
						userPO.setHeader5(msgAcc.getE02CCRDSC());
						userPO.setHeader6(msgAcc.getE02CCRSTD());
						userPO.setHeader7(msgAcc.getE02CCRSTM());
						userPO.setHeader8(msgAcc.getE02CCRSTY());
						cardList.setShowNext(false);
						break;
					}
					else
					{
						cardList.addRow(msgAcc);
						if (firstTime)
						{
							firstTime = false;
							userPO.setIdentifier(msgAcc.getE02CCRCID());
							userPO.setAccNum(msgAcc.getE02CCRCRA());
							userPO.setCusNum(msgAcc.getE02PRICUN());
							userPO.setHeader1(msgAcc.getE02CCRNUM());
							userPO.setHeader2(msgAcc.getE02TARTYP());
							userPO.setHeader3(msgAcc.getE02CCRBRN());
							userPO.setHeader4(msgAcc.getE02CCRSTS());
							userPO.setHeader5(msgAcc.getE02CCRDSC());
							userPO.setHeader6(msgAcc.getE02CCRSTD());
							userPO.setHeader7(msgAcc.getE02CCRSTM());
							userPO.setHeader8(msgAcc.getE02CCRSTY());
							userPO.setHeader20("");
							userPO.setHeader21("");
						}
						if (marker.equals("+"))
						{
							cardList.setShowNext(true);
							break;
						}
					}
					newmessage = mc.receiveMessage();
				}

				flexLog("Putting java beans into the session");
				ses.setAttribute("cardList", cardList);
				ses.setAttribute("error", msgError);
				ses.setAttribute("userPO", userPO);

				try
				{
					flexLog("About to call Page: " + LangPath + "ECC0090_assignment_inq_id.jsp");
					callPage(LangPath + "ECC0090_assignment_inq_id.jsp", req, res);
				}
				catch (Exception e)
				{
					flexLog("Exception calling page " + e);
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

	protected void procActionAssignmentInquiryCard(MessageContext mc, ESS0030DSMessage user, HttpServletRequest req, HttpServletResponse res, HttpSession ses) throws ServletException, IOException
	{
		MessageRecord newmessage = null;
		ECC009001Message msgCD = null;
		ECC009002Message msgAcc = null;
		ECC009003Message msgCD2 = null;
		ECC009004Message msgAcc2 = null;
		ELEERRMessage msgError = null;
		UserPos userPO = null;
		JBObjList accList = null;
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

		// Send Initial data for ECC009001
		try
		{
			flexLog("Send Initial Data");
			msgCD = (ECC009001Message) mc.getMessageRecord("ECC009001");
			msgCD.setH01USERID(user.getH01USR());
			msgCD.setH01PROGRM("ECC0090");
			msgCD.setH01TIMSYS(getTimeStamp());
			msgCD.setH01SCRCOD("01");
			msgCD.setH01OPECOD("0003");
			try
			{
				if (req.getParameter("E01CCRNUM") != null)
					msgCD.setE01CCRNUM(req.getParameter("E01CCRNUM"));
			}
			catch (Exception e)
			{
				msgCD.setE01CCRNUM("0");
				flexLog(" error " + e);
			}
			msgCD.send();
			msgCD.destroy();
			flexLog("ECC009001 Message Sent");
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

		// Receive Data for ECC009001
		try
		{
			newmessage = mc.receiveMessage();

			if (newmessage.getFormatName().equals("ECC009001"))
			{
				try
				{
					msgCD = new ECC009001Message();
					flexLog("ECC009001 Message Received");
				}
				catch (Exception ex)
				{
					flexLog("Error: " + ex);
				}

				msgCD = (ECC009001Message) newmessage;

				flexLog("Putting java beans into the session");
				ses.setAttribute("error", msgError);
				ses.setAttribute("msgCD", msgCD);
				ses.setAttribute("userPO", userPO);

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

		if (msgCD.getE01CCRTPI().equals("T"))
		{ // Holder Card

			// Send Initial data for ECC009002
			try
			{
				flexLog("Send Initial Data");
				msgAcc = (ECC009002Message) mc.getMessageRecord("ECC009002");
				msgAcc.setH02USERID(user.getH01USR());
				msgAcc.setH02PROGRM("ECC0090");
				msgAcc.setH02TIMSYS(getTimeStamp());
				msgAcc.setH02SCRCOD("01");
				msgAcc.setH02OPECOD("0002");

				// all the fields here
				try
				{
					if (req.getParameter("E01CCRNUM") != null)
						msgAcc.setE02CCRNUM(req.getParameter("E01CCRNUM"));
				}
				catch (Exception e)
				{
					flexLog(" error " + e);
				}

				msgAcc.send();
				msgAcc.destroy();
				flexLog("ECC009002 Message Sent");
			}
			catch (Exception e)
			{
				e.printStackTrace();
				flexLog("Error: " + e);
				throw new RuntimeException("Socket Communication Error");
			}

			// Receive Data for ECC009002
			try
			{
				newmessage = mc.receiveMessage();

				if (newmessage.getFormatName().equals("ECC009002"))
				{
					accList = new JBObjList();
					boolean firstTime = true;
					String marker = "";

					while (true)
					{
						msgAcc = (ECC009002Message) newmessage;
						marker = msgAcc.getH02FLGMAS();

						if (marker.equals("*"))
						{
							accList.setShowNext(false);
							break;
						}
						else
						{
							accList.addRow(msgAcc);
							if (firstTime)
							{
								firstTime = false;
								userPO.setIdentifier(msgAcc.getE02CCRCID());
								userPO.setAccNum(msgAcc.getE02CCRCRA());
								userPO.setCusNum(msgAcc.getE02PRICUN());
								userPO.setHeader1(msgAcc.getE02CCRNUM());
								userPO.setHeader2(msgAcc.getE02TARTYP());
								userPO.setHeader3(msgAcc.getE02CCRBRN());
								userPO.setHeader4(msgAcc.getE02CCRSTS());
								userPO.setHeader5(msgAcc.getE02CCRDSC());
								userPO.setHeader6(msgAcc.getE02CCRSTD());
								userPO.setHeader7(msgAcc.getE02CCRSTM());
								userPO.setHeader8(msgAcc.getE02CCRSTY());
								userPO.setHeader20("");
								userPO.setHeader21("");
							}
							if (marker.equals("+"))
							{
								accList.setShowNext(true);
								break;
							}
						}
						newmessage = mc.receiveMessage();
					}

					flexLog("Putting java beans into the session");
					ses.setAttribute("accList", accList);
					ses.setAttribute("error", msgError);
					ses.setAttribute("userPO", userPO);

				}
				else
					flexLog("Message " + newmessage.getFormatName() + " received.");

				if (IsNotError)
				{ // There are no errors
					try
					{
						flexLog("About to call Page: " + LangPath + "ECC0090_assignment_inq_card.jsp");
						callPage(LangPath + "ECC0090_assignment_inq_card.jsp", req, res);
					}
					catch (Exception e)
					{
						flexLog("Exception calling page " + e);
					}
				}
				else
				{ // There are errors
					ses.setAttribute("error", msgError);

					try
					{
						flexLog("About to call Page: " + LangPath + "ECC0090_assignment_inq_enter.jsp");
						callPage(LangPath + "ECC0090_assignment_inq_enter.jsp", req, res);
					}
					catch (Exception e)
					{
						flexLog("Exception calling page " + e);
					}
				}

			}
			catch (Exception e)
			{
				e.printStackTrace();
				flexLog("Error: " + e);
				throw new RuntimeException("Socket Communication Error");
			}

		}
		else
		{ // Additional Card
			flexLog("About to call: /servlet/datapro.eibs.products.JSECC0090?SCREEN=1300&E03CCRNUM=" + req.getParameter("E01CCRNUM"));
			res.sendRedirect(super.srctx + "/servlet/datapro.eibs.products.JSECC0090?SCREEN=1300&E03CCRNUM=" + req.getParameter("E01CCRNUM"));
		}
	}

	protected void procActionAssignmentInquiryCardAdditional(MessageContext mc, ESS0030DSMessage user, HttpServletRequest req, HttpServletResponse res, HttpSession ses) throws ServletException, IOException
	{
		MessageRecord newmessage = null;
		ECC009003Message msgCD = null;
		ECC009004Message msgAcc = null;
		ELEERRMessage msgError = null;
		UserPos userPO = null;
		JBObjList accList = null;
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
	
		// Send Initial data for ECC009003
		try
		{
			flexLog("Send Initial Data");
			msgCD = (ECC009003Message) mc.getMessageRecord("ECC009003");
			msgCD.setH03USERID(user.getH01USR());
			msgCD.setH03PROGRM("ECC0090");
			msgCD.setH03TIMSYS(getTimeStamp());
			msgCD.setH03SCRCOD("01");
			msgCD.setH03OPECOD("0002");
	
			try
			{
				msgCD.setE03CCRNUMA(req.getParameter("E03CCRNUM"));
			}
			catch (Exception e)
			{}
	
			msgCD.send();
			msgCD.destroy();
			flexLog("ECC009003 Message Sent");
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
	
		// Receive Data for ECC009003
		try
		{
			newmessage = mc.receiveMessage();
	
			if (newmessage.getFormatName().equals("ECC009003"))
			{
				try
				{
					msgCD = new ECC009003Message();
					flexLog("ECC009003 Message Received");
				}
				catch (Exception ex)
				{
					flexLog("Error: " + ex);
				}
	
				msgCD = (ECC009003Message) newmessage;
	
				flexLog("Putting java beans into the session");
				ses.setAttribute("error", msgError);
				ses.setAttribute("msgCD", msgCD);
				ses.setAttribute("userPO", userPO);
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
	
		// Send Initial data for ECC009004
		try
		{
			flexLog("Send Initial Data");
			msgAcc = (ECC009004Message) mc.getMessageRecord("ECC009004");
			msgAcc.setH04USERID(user.getH01USR());
			msgAcc.setH04PROGRM("ECC0090");
			msgAcc.setH04TIMSYS(getTimeStamp());
			msgAcc.setH04SCRCOD("01");
			msgAcc.setH04OPECOD("0016");
	
			// all the fields here
			try
			{
				msgAcc.setE04CCRCID(req.getParameter("E03CCRCID"));
			}
			catch (Exception e)
			{
				msgAcc.setE04CCRCID(msgCD.getE03CCRCID());
			}
			try
			{
				msgAcc.setE04PRICUN(req.getParameter("E03PRICUN"));
			}
			catch (Exception e)
			{
				msgAcc.setE04PRICUN(msgCD.getE03PRICUN());
			}
			try
			{
				msgAcc.setE04CCRNUM(req.getParameter("E03CCRNUM"));
			}
			catch (Exception e)
			{
				msgAcc.setE04CCRNUM(msgCD.getE03CCRNUM());
			}
			try
			{
				msgAcc.setE04CCRCIDA(req.getParameter("E03CCRCIDA"));
			}
			catch (Exception e)
			{
				msgAcc.setE04CCRCIDA(msgCD.getE03CCRCIDA());
			}
			try
			{
				msgAcc.setE04PRICUNA(req.getParameter("E03PRICUNA"));
			}
			catch (Exception e)
			{
				msgAcc.setE04PRICUNA(msgCD.getE03PRICUNA());
			}
			try
			{
				msgAcc.setE04CCRNUMA(req.getParameter("E03CCRNUMA"));
			}
			catch (Exception e)
			{
				msgAcc.setE04CCRNUMA(msgCD.getE03CCRNUMA());
			}
	
			msgAcc.send();
			msgAcc.destroy();
			flexLog("ECC009004 Message Sent");
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
	
		// Receive Data for ECC009004
		try
		{
			if (newmessage.getFormatName().equals("ECC009004"))
			{
				accList = new JBObjList();
				boolean firstTime = true;
				String marker = "";
	
				while (true)
				{
					msgAcc = (ECC009004Message) newmessage;
					marker = msgAcc.getH04FLGMAS();
	
					if (marker.equals("*"))
					{
						accList.setShowNext(false);
						break;
					}
					else
					{
						accList.addRow(msgAcc);
						if (firstTime)
						{
							firstTime = false;
							userPO.setIdentifier(msgAcc.getE04CCRCIDA());
							userPO.setAccNum(msgAcc.getE04CCRCRA());
							userPO.setCusNum(msgAcc.getE04PRICUNA());
							userPO.setHeader1(msgAcc.getE04CCRNUMA());
							userPO.setHeader2(msgAcc.getE04TARTYP());
							userPO.setHeader3(msgAcc.getE04CCRBRN());
							userPO.setHeader4(msgAcc.getE04CCRSTS());
							userPO.setHeader5(msgAcc.getE04CCRDSC());
							userPO.setHeader6(msgAcc.getE04CCRSTD());
							userPO.setHeader7(msgAcc.getE04CCRSTM());
							userPO.setHeader8(msgAcc.getE04CCRSTY());
							userPO.setHeader20("");
							userPO.setHeader21("");
						}
						if (marker.equals("+"))
						{
							accList.setShowNext(true);
							break;
						}
					}
					newmessage = mc.receiveMessage();
				}
	
				flexLog("Putting java beans into the session");
				ses.setAttribute("accList", accList);
				ses.setAttribute("error", msgError);
				ses.setAttribute("userPO", userPO);
	
			}
			else
				flexLog("Message " + newmessage.getFormatName() + " received.");
	
			if (IsNotError)
			{ // There are no errors
				try
				{
					flexLog("About to call Page: " + LangPath + "ECC0090_assignment_inq_card_add.jsp");
					callPage(LangPath + "ECC0090_assignment_inq_card_add.jsp", req, res);
				}
				catch (Exception e)
				{
					flexLog("Exception calling page " + e);
				}
			}
			else
			{ // There are errors
				ses.setAttribute("error", msgError);
	
				try
				{
					flexLog("About to call Page: " + LangPath + "ECC0090_assignment_inq_enter.jsp");
					callPage(LangPath + "ECC0090_assignment_inq_enter.jsp", req, res);
				}
				catch (Exception e)
				{
					flexLog("Exception calling page " + e);
				}
			}
	
		}
		catch (Exception e)
		{
			e.printStackTrace();
			flexLog("Error: " + e);
			throw new RuntimeException("Socket Communication Error");
		}
	}

	public void service(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException
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

			int screen = R_CARD_ASSIGN_ENTER;

			try
			{
				msgUser = (datapro.eibs.beans.ESS0030DSMessage) session.getAttribute("currUser");

				// Here we should get the path from the user profile
				LangPath = super.rootPath + msgUser.getE01LAN() + "/";

				try
				{
					flexLog("Opening Socket Connection");
					s = new Socket(super.hostIP, getInitSocket(req) + 1);
					s.setSoTimeout(super.sckTimeOut);
					mc = new MessageContext(new DataInputStream(new BufferedInputStream(s.getInputStream())), new DataOutputStream(new BufferedOutputStream(s.getOutputStream())), "datapro.eibs.beans");

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
						case R_CARD_ASSIGN_ENTER :
							procReqCardAssignEnter(mc, msgUser, req, res, session);
							break;
						case R_CARD_ASSIGN_LIST :
							procReqCardAssignList(mc, msgUser, req, res, session);
							break;

						case R_CARD_ASSIGN_NEW :
							procReqCardAssignNew(mc, msgUser, req, res, session);
							break;
						case A_CARD_ASSIGN_NEW :
							procActionCardAssignNew(mc, msgUser, req, res, session);
							break;
						case A_CARD_ASSIGN_MAINT :
							procActionCardAssignMaint(mc, msgUser, req, res, session);
							break;
						case R_CARD_ASSIGN_INQ_MAINT :
							procReqCardAssignInquiryMaint(mc, msgUser, req, res, session);
							break;

						case R_CARD_ASSIGN_ADDITIONAL_NEW :
							procReqCardAssignAdditionalNew(mc, msgUser, req, res, session);
							break;
						case A_CARD_ASSIGN_ADDITIONAL_NEW :
							procActionCardAssignAdditionalNew(mc, msgUser, req, res, session);
							break;
						case A_CARD_ASSIGN_ADDITIONAL_MAINT :
							procActionCardAssignAdditionalMaint(mc, msgUser, req, res, session);
							break;
						case R_CARD_ASSIGN_ADDITIONAL_INQ_MAINT :
							procReqCardAssignAdditionalInquiryMaint(mc, msgUser, req, res, session);
							break;
							
						case R_CARD_STATUS_ENTER :
							procReqCardStatusEnter(mc, msgUser, req, res, session);
							break;
						case R_CARD_STATUS_ENTER_INQ :
							procReqCardStatusEnterInq(mc, msgUser, req, res, session);
							break;
						case A_CARD_STATUS :
							procActionCardStatus(mc, msgUser, req, res, session);
							break;

						case R_CARD_ASSIGN_REPLACE :
							procReqCardAssignReplace(mc, msgUser, req, res, session);
							break;
						case A_CARD_ASSIGN_REPLACE :
							procActionCardAssignReplace(mc, msgUser, req, res, session);
							break;

						case R_CARD_ASSIGN_PRINT :
							procReqCardAssignPrint(mc, msgUser, req, res, session);
							break;

						case R_ASSIGNMENT_INQ_ENTER :
							procReqAssignmentInquiryEnter(mc, msgUser, req, res, session);
							break;
						case A_ASSIGNMENT_INQ_ID :
							procActionAssignmentInquiryID(mc, msgUser, req, res, session);
							break;
						case A_ASSIGNMENT_INQ_CARD :
							procActionAssignmentInquiryCard(mc, msgUser, req, res, session);
							break;
						case A_ASSIGNMENT_INQ_CARD_ADDITIONAL :
							procActionAssignmentInquiryCardAdditional(mc, msgUser, req, res, session);
							break;

						default :
							res.sendRedirect(super.srctx + LangPath + super.devPage);
							break;
					}

				}
				catch (Exception e)
				{
					e.printStackTrace();
					int sck = getInitSocket(req) + 3;
					flexLog("Socket not Open(Port " + sck + "). Error: " + e);
					res.sendRedirect(super.srctx + LangPath + super.sckNotOpenPage);
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
				res.sendRedirect(super.srctx + LangPath + super.sckNotRespondPage);
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
}