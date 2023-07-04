package datapro.eibs.client;

/**
 * Insert the type's description here.
 * Creation date: (1/19/00 6:08:55 PM)
 * @author: R.Amaro
 */

import java.io.*;
import java.net.*;
import java.math.BigDecimal;
import java.beans.Beans;
import javax.servlet.*;
import javax.servlet.http.*;
import datapro.eibs.beans.*;

import datapro.eibs.master.Util;
 
import datapro.eibs.sockets.*;

public class JSECIF250 extends datapro.eibs.master.SuperServlet {

	// CIF options
	
	protected static final int R_ACCOUNT 			= 1;
	protected static final int R_AVERAGE	 			= 2;

    protected String LangPath = "S";

/**
 * JSECLI001 constructor comment.
 */
public JSECIF250() {
	super();
}
/**
 * This method was created by Orestes Garcia.
 */
public void destroy() {

	flexLog("free resources used by JSECIF250");
	
}
/**
 * This method was created by Orestes Garcia.
 */
public void init(ServletConfig config) throws ServletException {
	super.init(config);
}

/**
 * This method was created in VisualAge.
 */
protected void procReqAccount(MessageContext mc, ESS0030DSMessage user, HttpServletRequest req, HttpServletResponse res, HttpSession ses)
			throws ServletException, IOException {

	MessageRecord newmessage = null;
	ECIF25002Message msgList = null;
	ELEERRMessage msgError = null;
	JBObjList listAcc = null;
	JBAverage beanAve = null;
	UserPos	userPO = null;	
	boolean IsNotError = false;

	userPO = (datapro.eibs.beans.UserPos)ses.getAttribute("userPO");

	int type = 0;
 	String num = "";

	// Send Initial data
	try
	{
		flexLog("Send Initial Data");
		msgList = (ECIF25002Message)mc.getMessageRecord("ECIF25002");
		msgList.setH02USERID(user.getH01USR());
	 	msgList.setH02PROGRM("ECIF250");
	 	msgList.setH02TIMSYS(getTimeStamp());
	 	msgList.setH02SCRCOD("01");
	 	msgList.setE02CUSNUM(userPO.getCusNum());

	 	msgList.send();	
	 	msgList.destroy();
	 	flexLog("ECIF250 Message Sent");
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

		if (newmessage.getFormatName().equals("ELEERR")) {

			msgError = (ELEERRMessage)newmessage;

			// showERROR(msgError);

			flexLog("Putting java beans into the session");
			ses.setAttribute("error", msgError);

			try {
				flexLog("About to call Page: " + LangPath + "ECIF200_cr_basic.jsp");
				callPage(LangPath + "ECIF200_cr_basc.jsp", req, res);

			}
			catch (Exception e) {
				flexLog("Exception calling page " + e);
			}

		}
		else if (newmessage.getFormatName().equals("ECIF25002")) {
			
			boolean firstTime = true;
			String marker = "";
			listAcc = new JBObjList();
						
			while (true) {

				msgList = (ECIF25002Message)newmessage;
				msgList.setHandler(null);
				marker = msgList.getH02FLGMAS();

				if (marker.equals("*")) {
					break;
				}
				else {
					if(firstTime) {
						 userPO.setCusNum(msgList.getE02CUSNUM());
						 userPO.setCusName(msgList.getE02CUSNA1());
						 firstTime = false;
					}					
					listAcc.addRow(msgList);
				}
				
				newmessage = mc.receiveMessage();
			}
			
						
			flexLog("Putting java beans into the session");
			ses.setAttribute("listAcc", listAcc);
			ses.setAttribute("userPO", userPO);

			try {
				flexLog("About to call Page: " + LangPath + "ECIF250_averages_rt.jsp");
				callPage(LangPath + "ECIF250_averages_rt.jsp", req, res);

			}
			catch (Exception e) {
				flexLog("Exception calling page " + e);
			}
			
		}
		else
			flexLog("Message " + newmessage.getFormatName() + " received.");

	}
	catch (Exception e)	{
		e.printStackTrace();
		flexLog("Error: " + e);
	  	throw new RuntimeException("Socket Communication Error");
	}	

}

/**
 * This method was created in VisualAge.
 */
protected void procReqAverage(MessageContext mc, ESS0030DSMessage user, HttpServletRequest req, HttpServletResponse res, HttpSession ses)
			throws ServletException, IOException {

	MessageRecord newmessage = null;
	ECIF25001Message msgList = null;
	ELEERRMessage msgError = null;
	JBAverage beanAve = null;
	UserPos	userPO = null;	
	boolean IsNotError = false;

	userPO = (datapro.eibs.beans.UserPos)ses.getAttribute("userPO");

	int type = 0;
 	String num = "";

	// Send Initial data
	try
	{
		flexLog("Send Initial Data");
		msgList = (ECIF25001Message)mc.getMessageRecord("ECIF25001");
		msgList.setH01USERID(user.getH01USR());
	 	msgList.setH01PROGRM("ECIF250");
	 	msgList.setH01TIMSYS(getTimeStamp());
	 	msgList.setH01SCRCOD("01");
	 	msgList.setH01OPECOD("0004");
	 	msgList.setE01ACCNUM(req.getParameter("accList"));

	 	msgList.send();	
	 	msgList.destroy();
	 	flexLog("ECIF250 Message Sent");
	
		
	// Receive Data
	
	    newmessage = mc.receiveMessage();

		if (newmessage.getFormatName().equals("ELEERR")) {

			try {
				msgError = new datapro.eibs.beans.ELEERRMessage();
			} 
			catch (Exception ex) {
				flexLog("Error: " + ex); 
			  	}

			msgError = (ELEERRMessage)newmessage;

			// showERROR(msgError);

			flexLog("Putting java beans into the session");
			ses.setAttribute("error", msgError);

			try {
				flexLog("About to call Page: " + LangPath + "ECIF250_averages_enter.jsp");
				callPage(LangPath + "ECIF250_averages_enter.jsp", req, res);

			}
			catch (Exception e) {
				flexLog("Exception calling page " + e);
			}

		}
		else if (newmessage.getFormatName().equals("ECIF25001")) {
			
			beanAve = new datapro.eibs.beans.JBAverage();
			
			boolean firstTime = true;
			String marker = "";

			java.math.BigDecimal ave[] = new java.math.BigDecimal[24];
			int year[] = new int[24];
			int month[] = new int[24];
			for ( int i = 0; i < 24; i++ ) {
				ave[i] = new java.math.BigDecimal(0);
				year[i] = -1;
				month[i] = -1;
			}
			
			java.math.BigDecimal factor_3 = new java.math.BigDecimal(3);
			java.math.BigDecimal factor_12 = new java.math.BigDecimal(12);
			java.math.BigDecimal aveTrimestral = new java.math.BigDecimal(0);
			java.math.BigDecimal currAnnualAve = new java.math.BigDecimal(0);
			java.math.BigDecimal prevAnnualAve = new java.math.BigDecimal(0);
			int currYear = -1;
			int prevYear = -1;
			String graph = "";
			int col = 0;
			
			while (true) {
								
				msgList = (ECIF25001Message)newmessage;

				marker = msgList.getE01INDOPE();
				flexLog("marker = " + marker);
				
				if (marker.equals("*")) {
					break;
				}
				else {

					// totalAssets = totalAssets.add(msgList.getBigDecimalE04BSETOT());
					col ++;
					graph += "<param name=c" + col + "_label value=\"" + msgList.getE01MTHLET() + " " + Util.formatYear(msgList.getE01YEARNU()) + "\">";
	            	graph += "<param name=c" + col + " value=\"" + Util.parseCCYtoDouble(msgList.getE01AVERAG()) + "\">";
					graph += "<param name=c" + col + "_valab value=\"" + msgList.getE01AVERAG() + "\">";
	            	graph += "<param name=c" + col + "_color value=\"" + getColor(col) + "\">";
	            	graph += "<param name=c" + col + "_style value=\"solid\">";

					month[col-1] = Integer.parseInt(msgList.getE01MTHNUM());
					year[col-1] = Integer.parseInt(msgList.getE01YEARNU());
					ave[col-1] = msgList.getBigDecimalE01AVERAG();
						
				}
				newmessage = mc.receiveMessage();
			}

			if (col > 0) {
				beanAve.setNoRecord(false);
				currYear = year[col-1];
				if (currYear == 0) {
					prevYear = 99;
				}
				else {
					prevYear = currYear - 1;
				}
				beanAve.setCurrYear(Util.formatYear(currYear));
				beanAve.setPrevYear(Util.formatYear(prevYear));

				try {
					for (int i = (col - 1); i > -1; i-- ) {
						if ( year[i] == currYear ) {
							beanAve.setCurrYearMAve(ave[i].toString(), (month[i] - 1));
							currAnnualAve = currAnnualAve.add(ave[i]);
							if ( (month[i] % 3) == 0) {
								aveTrimestral = aveTrimestral.add(ave[i]);
								if (i == 0) {
									aveTrimestral = aveTrimestral.divide(factor_3, BigDecimal.ROUND_UP);
									beanAve.setCurrYear3MAve(aveTrimestral.toString(), ((month[i] + 2)/3) - 1);
								}
							}
							else if ( ((month[i] + 1) % 3) == 0) {
								aveTrimestral = aveTrimestral.add(ave[i]);
								if (i == 0) {
									aveTrimestral = aveTrimestral.divide(factor_3, BigDecimal.ROUND_UP);
									beanAve.setCurrYear3MAve(aveTrimestral.toString(), ((month[i] + 2)/3) - 1);
								}
							}
							else if ( ((month[i] + 2) % 3) == 0) {
								aveTrimestral = aveTrimestral.add(ave[i]);
								aveTrimestral = aveTrimestral.divide(factor_3, BigDecimal.ROUND_UP);
								beanAve.setCurrYear3MAve(aveTrimestral.toString(), ((month[i] + 2)/3) - 1);
								aveTrimestral = new java.math.BigDecimal(0);
							}
						}
						else if ( year[i] == prevYear ) {
							beanAve.setPrevYearMAve(ave[i].toString(), (month[i] - 1));
							prevAnnualAve = prevAnnualAve.add(ave[i]);
							if ( (month[i] % 3) == 0) {
								aveTrimestral = aveTrimestral.add(ave[i]);
								if (i == 0) {
									aveTrimestral = aveTrimestral.divide(factor_3, BigDecimal.ROUND_UP);
									beanAve.setPrevYear3MAve(aveTrimestral.toString(), ((month[i] + 2)/3) - 1);
								}
							}
							else if ( ((month[i] + 1) % 3) == 0) {
								aveTrimestral = aveTrimestral.add(ave[i]);
								if (i == 0) {
									aveTrimestral = aveTrimestral.divide(factor_3, BigDecimal.ROUND_UP);
									beanAve.setPrevYear3MAve(aveTrimestral.toString(), ((month[i] + 2)/3) - 1);
								}
							}
							else if ( ((month[i] + 2) % 3) == 0) {
								aveTrimestral = aveTrimestral.add(ave[i]);
								aveTrimestral = aveTrimestral.divide(factor_3, BigDecimal.ROUND_UP);
								beanAve.setPrevYear3MAve(aveTrimestral.toString(), ((month[i] + 2)/3) - 1);
								aveTrimestral = new java.math.BigDecimal(0);
							}
						}
						else {
							break;
						}	
					}
				}
				catch (Exception e) {
					flexLog("Read error " + e);
				}
			}
				
			currAnnualAve = currAnnualAve.divide(factor_12, BigDecimal.ROUND_UP);
			prevAnnualAve = prevAnnualAve.divide(factor_12, BigDecimal.ROUND_UP);
			if ( !graph.equals("")) {
		    	String appHeader = "<applet archive=\"eibs_applets.zip\" code=\"datapro.eibs.applets.graph.Chart.class\" width=100% height=350 align=\"absmiddle\"  codebase=\"" + super.webAppPath + LangPath + "\">";   
		    	graph = appHeader + "<param name=title value=\"\"><param name=columns value=" + col + "><param name=orientation value=\"vertical\"><param name=scale value=\"1\">" + graph + "</applet>";
			}

		
			beanAve.setCurrYearAve(currAnnualAve.toString());
			beanAve.setPrevYearAve(prevAnnualAve.toString());
			beanAve.setGraph(graph);
			
			flexLog("Putting java beans into the session");
			ses.setAttribute("aveBean", beanAve);
			ses.setAttribute("userPO", userPO);

			try {
				flexLog("About to call Page: " + LangPath + "ECIF250_averages_rt_det.jsp");
				callPage(LangPath + "ECIF250_averages_rt_det.jsp", req, res);
			}
			catch (Exception e) {
				flexLog("Exception calling page " + e);
			}
			
		}
		else
			flexLog("Message " + newmessage.getFormatName() + " received.");

	}
	catch (Exception e)	{
		e.printStackTrace();
		flexLog("Error: " + e);
	  	throw new RuntimeException("Socket Communication Error");
	}	

}



public void service(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
   
	Socket s = null;
	MessageContext mc = null;

	ESS0030DSMessage msgUser = null;
  	HttpSession session = null;

	session = (HttpSession)req.getSession(false); 
	
	if (session == null) {
		try {
			res.setContentType("text/html");
			printLogInAgain(res.getWriter());
		}
		catch (Exception e) {
			e.printStackTrace();
			flexLog("Exception ocurred. Exception = " + e);
		}
	}
	else {

		int screen = R_ACCOUNT;
		
		try {
		
			msgUser = (datapro.eibs.beans.ESS0030DSMessage)session.getAttribute("currUser");

			// Here we should get the path from the user profile
			LangPath = super.rootPath + msgUser.getE01LAN() + "/";

			try
			{
				flexLog("Opennig Socket Connection");
				s = new Socket(super.hostIP, getInitSocket(req) + 1);
				s.setSoTimeout(super.sckTimeOut);
			  	mc = new MessageContext(new DataInputStream(new BufferedInputStream(s.getInputStream())),
							      	    new DataOutputStream(new BufferedOutputStream(s.getOutputStream())),
									    "datapro.eibs.beans");
			
			
			try {
				screen = Integer.parseInt(req.getParameter("SCREEN"));
			}
			catch (Exception e) {
				flexLog("Screen set to default value");
			}
		
			switch (	screen) {
				case R_AVERAGE :
					procReqAverage(mc, msgUser, req, res, session);
					break;
				case R_ACCOUNT :
					procReqAccount(mc, msgUser, req, res, session);
					break;
				default :
					res.sendRedirect(super.srctx + LangPath + super.devPage);
					break;
			}
			}
			catch (Exception e) {
				e.printStackTrace();
				int sck = getInitSocket(req) + 1;
				flexLog("Socket not Open(Port " + sck + "). Error: " + e);
				res.sendRedirect(super.srctx + LangPath + super.sckNotOpenPage);
				//return;
			}
			finally {
				s.close();
			}
			

		}
		catch (Exception e) {
			flexLog("Error: " + e);
			res.sendRedirect(super.srctx + LangPath + super.sckNotRespondPage);
		}
		
	}

}

private String getColor(int color){
	String myColor = "red";
	
	switch (color) {
		case 1:
			myColor = "red";
			break;
		case 2:
			myColor = "green";
			break;
		case 3:
			myColor = "blue";
			break;
		case 4:
			myColor = "pink";
			break;
		case 5:
			myColor = "orange";
			break;
		case 6:
			myColor = "magenta";
			break;
		case 7:
			myColor = "cyan";
			break;
		case 8:
			myColor = "white";
			break;
		case 9:
			myColor = "yellow";
			break;
		case 10:
			myColor = "gray";
			break;
		case 11:
			myColor = "darkGray";
			break;		
		case 12:
			myColor = "red";
			break;
	}
		
	return myColor;
}

}