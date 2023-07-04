package datapro.eibs.products;

import java.io.*;
import java.net.*;
import datapro.eibs.beans.*;
import datapro.eibs.sockets.*;
import datapro.eibs.master.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.*;
import javax.servlet.http.*;
import java.util.Enumeration;

import org.apache.log4j.Logger;

public class JSEDL0813 extends SuperServlet {
	static final int R_MENU = 100;
	static final int A_SEARCH = 200;
	static final int A_EXECUTE = 300;
	
	private static final int PREVPAGE = 500;
	private static final int NEXTPAGE = 501;

	private static final int A_SORT = 502;
	private static final int A_SORT_DATE = 503;	
	
	static final int R_MAIN_CONSULTA = 400;
	static final int A_CONSULTA = 600;
	
 

	private String LangPath = "S";
	static final String color = "brown";
	/**
	 * JSNormaliz constructor comment.
	 */
	public JSEDL0813() {
		super();
	}
	/**
	 * service method comment.
	 */
	public void doTask(
		ESS0030DSMessage user,
		MessageContext msgManager,
		javax.servlet.http.HttpServletRequest request,
		javax.servlet.http.HttpServletResponse response)
		throws Exception {
		int screen = 0;
		HttpSession ses = null;
		ses = request.getSession();

		try {
			screen = Integer.parseInt(request.getParameter("SCREEN"));
		} catch (Exception e) {
		}
		switch (screen) {
					case R_MENU :
						procActionInicio(request, response, ses);
						break;

					case A_SEARCH  :
						procActionSearch(msgManager, user,request, response, ses);
						break;

					case A_EXECUTE  :
						procActionExecute(msgManager, user,request, response, ses);
						break;

					/*
					case PREVPAGE :
						procPrevPage(request, response, ses);
						break;

					case NEXTPAGE :
						procNextPage(request, response, ses);
						break;

					case A_SORT :
						sort(request, response);
						break;

					case A_SORT_DATE :
						sortDate(request, response);
						break;
					*/

					case R_MAIN_CONSULTA : 
						procActionInicioConsulta(request, response, ses);

					case A_CONSULTA : 
						procActionConsulta(msgManager, user, request, response, ses);
						break;



					default :
						response.sendRedirect(LangPath + super.devPage);
						break;


		}
	}

private void procActionInicioConsulta(HttpServletRequest req, HttpServletResponse res, HttpSession ses)
	throws Exception
{
	ses.setAttribute("opt", "");
	callPage(LangPath + "EDL0813_enter_list_consulta.jsp", req, res);
}

private void procActionInicio(HttpServletRequest req, HttpServletResponse res, HttpSession ses) throws Exception {
	ses.setAttribute("opt", "");
	callPage(LangPath + "EDL0813_enter_list.jsp", req, res);
}

private void procActionConsulta(MessageContext mc, ESS0030DSMessage user, HttpServletRequest req, HttpServletResponse res, HttpSession ses)
	throws Exception
{
	MessageRecord newmessage = null;
	EDL081301Message msgEDL = null;
	ELEERRMessage msgError = null;
	boolean IsNotError = true;
	UserPos userPO = null;
	String page = "";
	JBObjList beanList = null;
	
	msgError = new ELEERRMessage();
	userPO = (UserPos)ses.getAttribute("userPO");
	
	// Send Initial data
	try {
		msgEDL = (EDL081301Message) mc.getMessageRecord("EDL081301");
		msgEDL.setH01USERID(user.getH01USR());
		msgEDL.setH01PROGRM("EDL0813");
		msgEDL.setH01TIMSYS(getTimeStamp());
		msgEDL.setH01SCRCOD("IQ");
		msgEDL.setH01OPECOD("0015");

		// all the fields here
		java.util.Enumeration enu = msgEDL.fieldEnumeration();
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

		msgEDL.send();
		msgEDL.destroy();
		flexLog("EDL081301 Message Sent");
	} catch (Exception e) {
		e.printStackTrace();
		flexLog("Error: " + e);
		throw new RuntimeException("Socket Communication Error");
	}
	
	newmessage = mc.receiveMessage();
	if (newmessage.getFormatName().equals("EDL081301")) {
		beanList = new JBObjList();
		String marker = "";

		while (true) {
			msgEDL = (EDL081301Message) newmessage;
			flexLog("EDL081301 Message Received");					
			marker = msgEDL.getH01FLGMAS();

			if (marker.equals("*")) {
				beanList.setShowNext(false);
				break;
			} else {
				beanList.addRow(msgEDL);
				if (marker.equals("+")) {
					beanList.setShowNext(true);
					break;
				}
			}
			newmessage = mc.receiveMessage();
		}
								
		flexLog("Putting java beans into the session");
		ses.setAttribute("error", msgError);
		ses.setAttribute("jlista", beanList);
		ses.setAttribute("userPO", userPO);
				
		try {
			flexLog("About to call Page: " + LangPath + "EDL0813_consulta_list.jsp");
			callPage(LangPath + "EDL0813_consulta_list.jsp", req, res);						
		} catch (Exception e) {
			flexLog("Exception calling page " + e);
		}

	}
}


private void procActionSearch(MessageContext mc, ESS0030DSMessage user, HttpServletRequest req, HttpServletResponse res, HttpSession ses)
		throws Exception {

		MessageRecord newmessage = null;
		EDL081301Message msgEDL = null;
		ELEERRMessage msgError = null;
		boolean IsNotError = true;
		UserPos userPO = null;
		String page = "";
		JBObjList beanList = null;

		msgError = new datapro.eibs.beans.ELEERRMessage();
		userPO = (datapro.eibs.beans.UserPos) ses.getAttribute("userPO");
		String accion="";
		try {
			accion = req.getParameter("opt");
		} catch (Exception e) {
			flexLog("Default value");
		}		
	
		// Send Initial data
		try {
			msgEDL = (EDL081301Message) mc.getMessageRecord("EDL081301");
			msgEDL.setH01USERID(user.getH01USR());
			msgEDL.setH01PROGRM("EDL0813");
			msgEDL.setH01TIMSYS(getTimeStamp());
			msgEDL.setH01OPECOD("0015");
			msgEDL.setH01FLGWK1(accion);
			
			try {
				//msgEDL.setE01RECPOS(req.getParameter("pos"));
			} catch (Exception e) {
				//msgEDL.setE01RECPOS("0");
			}
			
			msgEDL.send();
			msgEDL.destroy();
			flexLog("EDL081301 Message Sent");
		} catch (Exception e) {
			e.printStackTrace();
			flexLog("Error: " + e);
			throw new RuntimeException("Socket Communication Error");
		}
		
		newmessage = mc.receiveMessage();
		if (newmessage.getFormatName().equals("EDL081301")) {
			beanList = new JBObjList();
			boolean firstTime = true;
			String marker = "";

			while (true) {
				msgEDL = (EDL081301Message) newmessage;
				flexLog("EDL081301 Message Received");					
				marker = msgEDL.getH01FLGMAS();

				if (firstTime) {
					firstTime = false;
					//beanList.setFirstRec(Integer.parseInt(msgEDL.getE01RECPOS()));
				}
				if (marker.equals("*")) {
					beanList.setShowNext(false);
					break;
				} else {
					beanList.addRow(msgEDL);
					if (marker.equals("+")) {
						beanList.setShowNext(true);
						//beanList.setLastRec(Integer.parseInt(msgEDL.getE01RECPOS()));
						break;
					}
				}
				newmessage = mc.receiveMessage();
			}
								
			flexLog("Putting java beans into the session");
			ses.setAttribute("error", msgError);
			ses.setAttribute("jlista", beanList);
			ses.setAttribute("userPO", userPO);
			ses.setAttribute("opt", accion);
				
			try {
				flexLog("About to call Page: " + LangPath + "EDL0813_enter_list.jsp");
				callPage(LangPath + "EDL0813_enter_list.jsp", req, res);						
			} catch (Exception e) {
				flexLog("Exception calling page " + e);
			}

		}
	}


	private void procActionExecute(MessageContext mc, ESS0030DSMessage user, HttpServletRequest req, HttpServletResponse res, HttpSession ses)
			throws Exception {

			MessageRecord newmessage = null;
			EDL081301Message msgEDL = null;
			ELEERRMessage msgError = null;
			boolean IsNotError = true;
			UserPos userPO = null;
			String page = "";
			
			msgError = new datapro.eibs.beans.ELEERRMessage();
			userPO = (datapro.eibs.beans.UserPos) ses.getAttribute("userPO");
			String accion="";
			try {
				accion = req.getParameter("accion");
			} catch (Exception e) {
				flexLog("Default value");
			}
			
			JBObjList beanList = (JBObjList) ses.getAttribute("jlista");
			String checked = "";
			
			// Send Initial data
			try {
				for (int i = beanList.getLastRec()-1; i >= 0; i--) {
					checked = req.getParameter("NUMSEQ" + i);
					if (checked != null) {
						beanList.setCurrentRow(i);
						msgEDL = (EDL081301Message) beanList.getRecord();
						mc.sendMessage(msgEDL);
					}
				}
			
				msgEDL = (EDL081301Message) mc.getMessageRecord("EDL081301");
				msgEDL.setH01USERID(user.getH01USR());
				msgEDL.setH01PROGRM("EDL0813");
				msgEDL.setH01TIMSYS(getTimeStamp());
				msgEDL.setH01FLGMAS("*");
			
				msgEDL.send();
				msgEDL.destroy();
				flexLog("EDL081301 Message Sent");
			} catch (Exception e) {
				e.printStackTrace();
				flexLog("Error: " + e);
				throw new RuntimeException("Socket Communication Error");
			}
			
			// Receive Error Message
			try {
				newmessage = mc.receiveMessage();

				if (newmessage.getFormatName().equals("ELEERR")) {
					msgError = (ELEERRMessage) newmessage;
					IsNotError = msgError.getERRNUM().equals("0");
					flexLog("IsNotError = " + IsNotError);
					showERROR(msgError);
				} else
					flexLog("Message " + newmessage.getFormatName() + " received.");

			} catch (Exception e) {
				e.printStackTrace();
				flexLog("Error: " + e);
				throw new RuntimeException("Socket Communication Error");
			}
		
			newmessage = mc.receiveMessage();
			if (newmessage.getFormatName().equals("EDL081301")) {
				beanList = new JBObjList();
				String marker = "";

				while (true) {
					msgEDL = (EDL081301Message) newmessage;
					flexLog("EDL081301 Message Received");					
					marker = msgEDL.getH01FLGMAS();

					if (marker.equals("*")) {
						beanList.setShowNext(false);
						break;
					} else {
						beanList.addRow(msgEDL);
						if (marker.equals("+")) {
							beanList.setShowNext(true);
							break;
						}
					}
					newmessage = mc.receiveMessage();
				}
								
				flexLog("Putting java beans into the session");
				ses.setAttribute("error", msgError);
				ses.setAttribute("jlista", beanList);
				ses.setAttribute("userPO", userPO);
				ses.setAttribute("opt", accion);
				
				try {
					flexLog("About to call Page: " + LangPath + "EDL0813_enter_list.jsp");
					callPage(LangPath + "EDL0813_enter_list.jsp", req, res);						
				} catch (Exception e) {
					flexLog("Exception calling page " + e);
				}

			}
			
			ses.setAttribute("error", msgError);
			ses.setAttribute("userPO", userPO);
			ses.setAttribute("jlista", beanList);
			ses.setAttribute("opt", accion);
			
			try {
				res.sendRedirect(super.srctx + 
					"/servlet/datapro.eibs.products.JSEDL0813?SCREEN=200&opt="+accion);
			} catch (Exception e) {
				flexLog("Exception calling page " + e);
			}
			
		}

	/*
	private void procPrevPage(HttpServletRequest req, HttpServletResponse res, HttpSession ses) 
			throws Exception {
		sortList jlista = null;
		String pagina = "";

		jlista = (sortList) ses.getAttribute("jlista");
		try
		{
			pagina = req.getParameter("pagina");
		}
		catch(Exception e)
		{
			pagina = "EDL0813_enter_list.jsp";
		}

		try
		{
			String paginaactual = req.getParameter("PaginaActual");
			ses.setAttribute("paginaactual", paginaactual);
		}
		catch(Exception exception) { }

		jlista.setPrevPage();
		callPage(LangPath + pagina, req, res);

	}

	private void procNextPage(HttpServletRequest req,HttpServletResponse res, HttpSession ses) 
			throws Exception {
		sortList jlista = null;
		String pagina = "";
		try
		{
			pagina = req.getParameter("pagina");
		}
		catch(Exception e)
		{
			pagina = "EDL0813_enter_list.jsp";
		}
		try
		{
			String paginaactual = req.getParameter("PaginaActual");
			ses.setAttribute("paginaactual", paginaactual);
		}
		catch(Exception exception) { }
		jlista = (sortList)ses.getAttribute("jlista");
		jlista.setNextPage();
		callPage(LangPath + pagina, req, res);

	}

	public void sort(javax.servlet.http.HttpServletRequest req, javax.servlet.http.HttpServletResponse res) throws Exception {
		HttpSession ses = req.getSession();
		sortList lista = (sortList) ses.getAttribute("jlista");

		String fieldName = "";
		String pagina = "";
		try{
			pagina = req.getParameter("pagina");
		}catch(Exception e){
			pagina = "EDL0813_enter_list.jsp";
		}
		try {
			fieldName = (String) req.getParameter("FIELD");
			if (fieldName == null)
				fieldName = "";
		} catch (Exception e) {
			fieldName = "";
		}
		if (!fieldName.equals("")) {
			String[] sortFlds = { "" };
			sortFlds[0] = fieldName;
			lista.setSortKey(sortFlds);
			int orden = lista.getSortOrder();
			if (orden == lista.ASCENDENTE)
				lista.setSortOrder(sortList.DESCENDENTE);
			else
				lista.setSortOrder(sortList.ASCENDENTE);
			lista.sort();
		}
		ses.setAttribute("jlista", lista); 

		ses.setAttribute("paginaactual", "1");
		callPage(LangPath + pagina, req, res);

	}

	public void sortDate(javax.servlet.http.HttpServletRequest req, javax.servlet.http.HttpServletResponse res) throws Exception {
		HttpSession ses = req.getSession();

		sortList lista = (sortList) ses.getAttribute("jlista");
		String pagina = "";
		try
		{
			pagina = req.getParameter("pagina");
		}
		catch(Exception e)
		{
			pagina = "EDL0813_enter_list.jsp";
		}

		String dd = "";
		String mm = "";
		String yy = "";		
		try {
			dd = (String) req.getParameter("DAY");
			mm = (String) req.getParameter("MONTH");
			yy = (String) req.getParameter("YEAR");
			
			if (dd == null)
				dd = "";
		} catch (Exception e) {
			dd = "";
		}
		if (!dd.equals("")) {
			lista.setDateKey(dd,mm,yy);
			int orden = lista.getSortOrder();
			if (orden == lista.ASCENDENTE)
				lista.setSortOrder(sortList.DESCENDENTE);
			else
				lista.setSortOrder(sortList.ASCENDENTE);
			lista.sort();
		}

		ses.setAttribute("jlista", lista); 
		ses.setAttribute("paginaactual", "1");
		callPage(LangPath + pagina, req, res);

	}
	*/

	private void showERROR(ELEERRMessage m) {
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