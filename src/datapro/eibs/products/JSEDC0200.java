package datapro.eibs.products;

/**
 * Creation date: 03/26/06
 * @author: Saif Mazhar
 */

import java.io.*;
import java.net.*;
import java.beans.Beans;
import javax.servlet.*;
import javax.servlet.http.*;
import datapro.eibs.beans.*;
import datapro.eibs.sockets.*;

public class JSEDC0200 extends datapro.eibs.master.SuperServlet
{

	// Letters of Credit
	HttpServletRequest req;
	HttpServletResponse res;
	HttpSession ses;
	Socket s;

	MessageContext mc;
	MessageRecord newmessage = null;
	ESS0030DSMessage user;
	ELEERRMessage msgError = null;
	EDC020001Message msg01 = null;
	UserPos userPO = null;
	JBObjList jbList = null;

	boolean isNotError = false;
	int screen;
	String prevPage = "", nextPage = "", optMenu = "", opCode = "";
	boolean displayMessages = false;

	protected String LangPath = "S";

	public JSEDC0200()
	{
		super();
	}

	public void init(ServletConfig config) throws ServletException
	{
		super.init(config);
	}

	public void service(HttpServletRequest _req, HttpServletResponse _res) throws ServletException, IOException
	{
		req = _req;
		res = _res;
		s = null;
		user = null;
		ses = null;
		prevPage = nextPage = optMenu = opCode = "";
		ses = (HttpSession) req.getSession(false);

		if (ses == null)
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
			screen = -1;
			try
			{

				user = (datapro.eibs.beans.ESS0030DSMessage) ses.getAttribute("currUser");

				// Here we should get the path from the user profile
				LangPath = super.rootPath + user.getE01LAN() + "/";

				try
				{

					s = new Socket(super.hostIP, getInitSocket(req) + 1);
					s.setSoTimeout(super.sckTimeOut);
					mc =
						new MessageContext(
							new DataInputStream(new BufferedInputStream(s.getInputStream())),
							new DataOutputStream(new BufferedOutputStream(s.getOutputStream())),
							"datapro.eibs.beans");
					try
					{
						String sss = req.getParameter("SCREEN");
						screen = Integer.parseInt(req.getParameter("SCREEN"));
					}
					catch (Exception e)
					{
						flexLog("Screen set to default value");
					}
					flexLog("Screen  Number: " + screen);

					mainLogic();

				}
				catch (Exception e)
				{
					e.printStackTrace();
					int sck = getInitSocket(req) + 1;
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

	private void mainLogic() throws ServletException, IOException
	{
		try
		{

			initTransaction();

			switch (screen)
			{
				// Enter (Control de Documentos Recibidos)
				case 1 :
					callPage("EDC0200_coll_doc_enter.jsp");
					break;
				case 2 : // Call list (submit 1st page)
					nextPage = "EDC0200_coll_doc_list.jsp";
					opCode = "0015";
					init01Message();
					try
					{
						msg01.setE01DCMACC(req.getParameter("E01DCMACC"));
						userPO.setAccNum(msg01.getE01DCMACC());
					}
					catch (Exception e)
					{
						flexLog("E01DCMACC not available from page");
					}
					try
					{
						msg01.setE01DCIDNO(req.getParameter("E01DCIDNO"));
					}
					catch (Exception e)
					{
						flexLog("E01DCIDNO not available from page");
					}
					send01Message();
					receiveList();
					ses.setAttribute("jbList", jbList);
					putBeansInSession();
					flexLog("Calling " + nextPage);
					callPage(nextPage);
					break;

					// CALL DC DOCUMENTS NEW
				case 3 :
					nextPage = prevPage = "EDC0200_coll_doc_info.jsp";
					opCode = "0001";
					init01Message();
					msg01.setE01DCMACC(userPO.getAccNum());
					//transaction01();
					send01Message();
					//receiveList();
					//ses.setAttribute("jbList", jbList);
					receiveError();
					receive01Message();
					putBeansInSession();
					flexLog("Calling " + nextPage);
				    callPage(nextPage);
					break;
				case 4 : // CALL DC DOCUMENTS MAINT
					nextPage = prevPage = "EDC0200_coll_doc_info.jsp";
					opCode = "0002";
					init01Message();
					try
					{
						msg01.setE01DCMACC(userPO.getAccNum());
						msg01.setE01DCIDNO(req.getParameter("E01DCIDNO"));
					}
					catch (Exception e)
					{
						flexLog("E01DCIDNO not available from page");
					}
					transaction01();
					break;
				case 5 :
					prevPage = "EDC0200_coll_doc_info.jsp";
					nextPage = "EDC0200_coll_doc_list.jsp";
					opCode = "0005";
					init01Message();
					get01FieldsFromPage();
					send01Message();
					receiveError();
					receive01Message();
					putBeansInSession();
					if (isNotError)
					{
						screen = 2;
						mainLogic();
						return;
					}
					else
						callPage(prevPage);
					break;

				default :
					res.sendRedirect(super.srctx + LangPath + super.devPage);
					break;
			}
		}
		catch (Exception e)
		{
			flexLog("Error: " + e);
			res.sendRedirect(super.srctx + LangPath + super.sckNotRespondPage);
		}
	}

	protected void transaction01() throws ServletException, IOException
	{
		send01Message();
		receiveError();
		receive01Message();
		putBeansInSession();
		callPage(((isNotError) ? nextPage : prevPage));
	}

	// Created by Saif Mazhar
	// Initializes variables
	protected void initTransaction()
	{
		newmessage = null;
		msg01 = null;
		msgError = null;
		userPO = null;
		isNotError = false;

		try
		{
			msgError = new datapro.eibs.beans.ELEERRMessage();
			userPO = (datapro.eibs.beans.UserPos) ses.getAttribute("userPO");
			userPO.setOption(optMenu);
		}
		catch (Exception ex)
		{
			flexLog("Error: " + ex);
		}
		try
		{}
		catch (Exception ex)
		{
			flexLog("Error getting userPO from session: " + ex);
		}
	}

	// Created by Saif Mazhar
	// Initializeds msg01 and sets standard feilds
	protected void init01Message()
	{
		try
		{
			msg01 = (EDC020001Message) mc.getMessageRecord("EDC020001");
			msg01.setH01USERID(user.getH01USR());
			msg01.setH01PROGRM("EDC0200");
			msg01.setH01TIMSYS(getTimeStamp());
			msg01.setH01OPECOD(opCode);
		}
		catch (Exception e)
		{
			e.printStackTrace();
			flexLog("Error at init01Message(): " + e);
			throw new RuntimeException("Socket Communication Error" + opCode);
		}

	}

	protected void get01FieldsFromPage()
	{
		// all the fields here
		java.util.Enumeration enu = msg01.fieldEnumeration();
		MessageField field = null;
		String value = null;
		while (enu.hasMoreElements())
		{
			field = (MessageField) enu.nextElement();
			try
			{
				value = req.getParameter(field.getTag()).toUpperCase();
				if (value != null)
				{
					field.setString(value);
				}
			}
			catch (Exception e)
			{}
		}
	}

	// Created by Saif Mazhar
	// Sends msg01
	protected void send01Message()
	{
		try
		{
			msg01.send();
			if (displayMessages)
			{
				userPO.setHeader18("SENT  " + msg01.toString());
				flexLog("\n" + userPO.getHeader18());
			}
			msg01.destroy();

			flexLog("EDC020001 Message Sent");
		}
		catch (Exception e)
		{
			e.printStackTrace();
			flexLog("Error at send01Message(): " + e);
			throw new RuntimeException("Socket Communication Error");
		}
	}

	// Created by Saif Mazhar
	// receives error, sets msgError, sets isNotError
	protected void receiveError()
	{
		try
		{
			newmessage = mc.receiveMessage();

			if (newmessage.getFormatName().equals("ELEERR"))
			{
				msgError = (ELEERRMessage) newmessage;
				if (displayMessages)
				{
					userPO.setHeader19("ERROR " + msg01.toString());
					flexLog("\n" + userPO.getHeader19());
				}
				if (isNotError = msgError.getERRNUM().equals("0"))
					flexLog("Error Message Received: NO ERROR");
				else
				{
					flexLog("Error Message Received: ERROR");
					showERROR();
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

	// Created by Saif Mazhar
	public void receive01Message()
	{
		try
		{
			newmessage = mc.receiveMessage();

			if (newmessage.getFormatName().equals("EDC020001"))
			{
				try
				{
					msg01 = new datapro.eibs.beans.EDC020001Message();
					flexLog("EDC020001 Message Received");
				}
				catch (Exception ex)
				{
					flexLog("Error Receiving EDC020001 Message: " + ex);
				}

				msg01 = (EDC020001Message) newmessage;
				if (displayMessages)
				{
					userPO.setHeader20("RECV  " + msg01.toString());
					flexLog("/n" + userPO.getHeader20());
				}
			}
		}
		catch (Exception e)
		{
			e.printStackTrace();
			flexLog("Error at receive01Message(): " + e);
			throw new RuntimeException("Socket Communication Error");
		}
	}

	public void receiveList()
	{
		jbList = new JBObjList();
		try
		{
			MessageRecord mr = mc.receiveMessage();
			if (mr.getFormatName().equals("EDC020001"))
			{
				int li_counter = 0;
				while (li_counter++ < datapro.eibs.master.JSEIBSProp.getMaxIterations())
				{
					if (li_counter == datapro.eibs.master.JSEIBSProp.getMaxIterations())
					{
						System.out.println("MAX_ITERATION_REACHED_ERROR class:" + this.getClass().getName());
						break;
					}

					EDC020001Message lm_current = (EDC020001Message) mr;
					if (lm_current.getH01FLGMAS().equals("*"))
					{
						jbList.setShowNext(false);
						break;
					}
					else
					{
						if (lm_current.getH01FLGMAS().equals("+"))
						{
							jbList.setShowNext(true);
							jbList.addRow(lm_current);
							break;
						}
						else
						{
							jbList.addRow(lm_current);
						}

						mr = mc.receiveMessage();
					}
				}
			}
			else
			{
				flexLog("Message " + mr.getFormatName() + " received.");
			}
			//
		}
		catch (Exception e)
		{
			e.printStackTrace();
			flexLog("Error: " + e);
			throw new RuntimeException("Socket Communication Error");
		}
	}

	// Created by Saif Mazhar
	public void putBeansInSession()
	{
		try
		{
			flexLog("Putting java beans into the session");

			ses.setAttribute("error", msgError);
			ses.setAttribute("userPO", userPO);
			ses.setAttribute("msg01", msg01);
		}
		catch (Exception e)
		{
			e.printStackTrace();
			flexLog("Error at putBeansInSession(): " + e);
			throw new RuntimeException("Socket Communication Error");
		}
	}

	protected void showERROR()
	{
		if (logType != NONE)
		{
			flexLog("  ERROR number:" + msgError.getERRNUM());
			flexLog("  ERR001 = " + msgError.getERNU01() + " desc: " + msgError.getERDS01());
			flexLog("  ERR002 = " + msgError.getERNU02() + " desc: " + msgError.getERDS02());
			flexLog("  ERR003 = " + msgError.getERNU03() + " desc: " + msgError.getERDS03());
			flexLog("  ERR004 = " + msgError.getERNU04() + " desc: " + msgError.getERDS04());
			flexLog("  ERR005 = " + msgError.getERNU05() + " desc: " + msgError.getERDS05());
			flexLog("  ERR006 = " + msgError.getERNU06() + " desc: " + msgError.getERDS06());
			flexLog("  ERR007 = " + msgError.getERNU07() + " desc: " + msgError.getERDS07());
			flexLog("  ERR008 = " + msgError.getERNU08() + " desc: " + msgError.getERDS08());
			flexLog("  ERR009 = " + msgError.getERNU09() + " desc: " + msgError.getERDS09());
			flexLog("  ERR010 = " + msgError.getERNU10() + " desc: " + msgError.getERDS10());

		}
	}

	// Created by Saif Mazhar
	protected void callPage(String page)
	{
		try
		{
			flexLog("About to call Page: " + LangPath + page);
			callPage(LangPath + page, req, res);
		}
		catch (Exception e)
		{
			flexLog("Exception calling page " + e.toString() + e.getMessage());
		}
		if (displayMessages || !isNotError)
		{
			flexLog(
				"\nSUMMARY\n"
					+ userPO.getHeader18()
					+ "\n"
					+ userPO.getHeader20()
					+ "\n"
					+ userPO.getHeader19()
					+ "\n"
					+ "SCREEN = "
					+ screen);
		}
	}
}
