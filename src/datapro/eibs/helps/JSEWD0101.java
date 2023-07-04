package datapro.eibs.helps;

/**
 * This type was created by Orestes Garcia.
 */
import java.io.*;
import java.net.*;
import java.beans.Beans;
import javax.servlet.*;
import javax.servlet.http.*;
import datapro.eibs.beans.*;

import datapro.eibs.master.Util;

import datapro.eibs.sockets.*;

public class JSEWD0101 extends datapro.eibs.master.SuperServlet
{

	/**
	 * Insert the method's description here.
	 * Creation date: (1/14/00 12:29:44 PM)
	 */
	public JSEWD0101()
	{
		super();
	}
	/**
	 * This method was created by Orestes Garcia.
	 */
	public void destroy()
	{

		flexLog("free resources used by JSESS0040");

	}
	/**
	 * This method was created by Orestes Garcia.
	 */
	public void init(ServletConfig config) throws ServletException
	{
		super.init(config);

	}
	/**
	 * This method was created by Orestes Garcia.
	 * @param request HttpServletRequest
	 * @param response HttpServletResponse
	 */
	public void service(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException
	{

		Socket s = null;
		MessageContext mc = null;
		HttpSession session;

		MessageRecord newmessage = null;
		EWD0101DSMessage msgHelp = null;
		ESS0030DSMessage msgUser = null;
		JBList beanList = null;

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

			msgUser = (datapro.eibs.beans.ESS0030DSMessage) session.getValue("currUser");
			String Language = msgUser.getE01LAN();
			String LangPath = super.rootPath + Language + "/";

			try
			{
				flexLog("Opennig Socket Connection");
				//s = new Socket(super.hostIP, super.iniSocket + 31);
				s = new Socket(super.hostIP, getInitSocket(req) + 1);
				s.setSoTimeout(super.sckTimeOut);
				mc =
					new MessageContext(
						new DataInputStream(new BufferedInputStream(s.getInputStream())),
						new DataOutputStream(new BufferedOutputStream(s.getOutputStream())),
						"datapro.eibs.beans");
				//		}

				//	  	try {
				msgHelp = (EWD0101DSMessage) mc.getMessageRecord("EWD0101DS");
				msgHelp.setEWDSTY(req.getParameter("Search").toUpperCase());
				try
				{
					msgHelp.setEWDCUN(req.getParameter("EWDCUN"));
				}
				catch (Exception e)
				{
					flexLog("couldn't retrieve EWDCUN");
				}
				try
				{
					msgHelp.setEWDCRF(req.getParameter("EWDCRF"));
				}
				catch (Exception e)
				{
					flexLog("couldn't retrieve EWDCRF");
				}
				msgHelp.send();
				msgHelp.destroy();
				flexLog("EWD0101DS Message Sent");
				//		}		
				//		catch (Exception e) {
				//			e.printStackTrace();
				//			flexLog("Error: " + e);
				//			res.sendRedirect(super.srctx +LangPath + super.sckNotRespondPage);
				//			return;
				//		}

				// Receiving
				try
				{
					newmessage = mc.receiveMessage();

					if (newmessage.getFormatName().equals("EWD0101DS"))
					{

						beanList = new JBList();

						String marker = "";
						String myFlag = "";
						String myRow = "";

						int ct = 0;
						while (ct++ < datapro.eibs.master.JSEIBSProp.getMaxIterations())
						{
							if (ct == datapro.eibs.master.JSEIBSProp.getMaxIterations())
							{
								System.out.println("MAX_ITERATION_REACHED_ERROR class:" + this.getClass().getName());
							}

							msgHelp = (EWD0101DSMessage) newmessage;

							marker = msgHelp.getEWDOPE();

							if (marker.equals("*"))
							{
								beanList.setShowNext(false);
								break;
							}
							else
							{
								myRow = "<TR>";
								myRow += "<td nowrap><a href=\"javascript:enter('"
									+ msgHelp.getEWDTBL()
									+ "')\">"
									+ Util.formatCell(msgHelp.getEWDTBL())
									+ "</a></td>";
								myRow += "<td nowrap><a href=\"javascript:enter('"
									+ msgHelp.getEWDTBL()
									+ "')\">"
									+ Util.formatCell(msgHelp.getEWDTBK())
									+ "</a></td>";
								myRow += "<td nowrap><a href=\"javascript:enter('"
									+ msgHelp.getEWDTBL()
									+ "')\">"
									+ msgHelp.getEWDTCY()
									+ "</a></td>";
								myRow += "<td nowrap><a href=\"javascript:enter('"
									+ msgHelp.getEWDTBL()
									+ "')\">"
									+ msgHelp.getEWDTYP()
									+ "</a></td>";
								myRow += "<td nowrap><a href=\"javascript:enter('"
									+ msgHelp.getEWDTBL()
									+ "')\">"
									+ msgHelp.getEWDDSC()
									+ "</a></td>";
								myRow += "</TR>";
								beanList.addRow(myFlag, myRow);

								if (marker.equals("+"))
								{
									beanList.setShowNext(true);
									break;
								}
							}
							newmessage = mc.receiveMessage();
						}

						flexLog("Putting java beans into the session");
						session.putValue("ewd0101Help", beanList);

						try
						{
							flexLog("About to call Page: " + LangPath + "EWD0101_tariff_lc_helpmessage.jsp");
							callPage(LangPath + "EWD0101_tariff_lc_helpmessage.jsp", req, res);
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
					res.sendRedirect(super.srctx + LangPath + super.sckNotRespondPage);
				}

			}
			catch (Exception e)
			{
				e.printStackTrace();
				int sck = super.iniSocket + 31;
				flexLog("Socket not Open(Port " + sck + "). Error: " + e);
				res.sendRedirect(super.srctx + LangPath + super.sckNotOpenPage);
				//	return;
			}
			finally
			{
				s.close();
			}
		}
	}
}
