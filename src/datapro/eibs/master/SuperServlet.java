package datapro.eibs.master;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.util.Calendar;
import java.util.Properties;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.datapro.exception.ServiceLocatorException;

import datapro.eibs.sockets.MessageField;
import datapro.eibs.sockets.MessageHandler;
import datapro.eibs.sockets.MessageRecord;
import datapro.eibs.sockets.routers.TOSocketMessageRouter;

public abstract class SuperServlet extends HttpServlet {
	
	public static final int NONE = 0;
	public static final int LOG = 1;
	public static final int SYSTEM = 2;

	public static int logType = NONE; // default is not to log messages
	public static int iniSocket = -1;
	public static int sckTimeOut = 15000;
	public static String hostIP = null;

	public static boolean formActive = false;
	public static boolean scanActive = false;

	public static String signOnMsg = "Sign On Again!!!";
	// Pages
	public static String webAppPath = "/eIBS";
	public static String rootPath = "/pages/";
	public static String bgPage = "/pages/background.jsp";
	public static String busyPage = "MISC_busy.jsp";
	public static String devPage = "MISC_under_construction.jsp";
	public static String sckNotOpenPage = "MISC_socket_not_open.jsp";
	public static String sckNotRespondPage = "MISC_socket_not_responding.jsp";
	public static String srctx = "";
	
	// eIBS Constants
	public static final int COLL_REF_A = 13;
	public static final int COLL_REF_B = 12;

	public static final int ACC_INQUIRY = 1;
	public static final int ACC_STATEMENT = 2;
	public static final int ACC_AVERAGE = 3;
	public static final int ACC_APPROVAL_INQ = 4;
	public static final int ACC_MAINTENANCE = 5;
	public static final int ACC_APPROVAL = 6;

	public static int CollFiller = 1;
	
	public SuperServlet() {
		super();
		setConfig(-1);
	}
	/**
	 * This method was created in VisualAge.
	 * @param logType int
	 */
	public SuperServlet(int log) {
		super();
		setConfig(log);
	}
	
	/**
	 * Checks if the session was created in the authentication process.
	 * If not prints a dialog box warning the user to login again.
	 * @param req
	 * @param res
	 * @return true - If the session was created. false - If no session it's available. 
	 */
	protected HttpSession getSession(HttpServletRequest req, HttpServletResponse res){
		HttpSession session = null;

		session = (HttpSession)req.getSession(false); 

		if (session == null) {
			try {
				res.setContentType("text/html");
				printLogInAgain(res.getWriter());
			} catch (Exception e) {
				flexLog("Exception ocurred. Exception = " + e);
			}
		}
		return session;
	}
	
	/** Redirects to a page or servlet.
	 * @param s
	 * @param response
	 * @throws IOException
	 */
	protected void redirectToPage(String s, HttpServletResponse response) throws IOException {
		try {
			if ( s.indexOf("/servlet/") == -1  ) {
				response.sendRedirect(SuperServlet.srctx + s);
			} else {
				response.sendRedirect(SuperServlet.srctx + s.substring( s.indexOf("/servlet/") ));
			}						
		} catch (Exception e) {
			flexLog("Exception redirecting to target " + e);
		}
	}
	
	/**
	 * Insert the method's description here.
	 * Creation date: (4/10/2000 6:16:35 PM)
	 */
	public void callPage(
		String s,
		HttpServletRequest httpservletrequest,
		HttpServletResponse httpservletresponse)
		throws IOException, ServletException {
			
		flexLog("About to call Page: " + s);
		try {
			// First Variant
			/*
			ServletContext servletcontext;
		
			servletcontext = getServletContext();
		
			RequestDispatcher requestdispatcher = servletcontext.getRequestDispatcher(s);
			requestdispatcher.forward(httpservletrequest, httpservletresponse);
			*/

			// Second Variant
			/*
			httpservletresponse.sendRedirect(super.srctx + super.srctx + s);
			*/

			// This is for preventing cache from the browser
			httpservletresponse.setHeader("Pragma", "no-cache");
			httpservletresponse.setDateHeader("Expires", -1);
			// httpservletresponse.setHeader("Cache-Control", "private");
			httpservletresponse.setHeader("Cache-Control", "no-cache");
			httpservletresponse.addHeader("Cache-Control", "max-age=0");
			httpservletresponse.addHeader("Cache-Control", "s-maxage=0");
			// This is the HTTP Meta tag equivalent
			// <META HTTP-EQUIV="Pragma" CONTENT="No-cache">

			// Third Variant
			getServletConfig().getServletContext().getRequestDispatcher(s).forward(
				httpservletrequest,
				httpservletresponse);
		} catch (IOException e) {
			flexLog("Exception calling page " + s + "Error : " + e.getMessage());
			throw e;
		} catch (ServletException e) {
			flexLog("Exception calling page " + s + "Error : " + e.getMessage());
			throw e;
		}
	}
	/**
	 * This method was created in VisualAge.
	 * @param line java.lang.String
	 */
	public final void flexLog(String line) {
		flexLog(line, logType);
	}
	public final synchronized void flexLog(String line, int inLogType) {
		switch (inLogType) {
			case LOG :
				{
					log(line);
					break;
				}
			case SYSTEM :
				{
					System.out.println(line);
					break;
				}
			default :
				{
					// do nothing
				}
		}
	}
	public final static int getDay() {
		Calendar calendar = Calendar.getInstance();
		int day = calendar.get(Calendar.DAY_OF_MONTH);
		return (day);
	}
	public final static int getHour() {
		Calendar calendar = Calendar.getInstance();
		int hour = calendar.get(Calendar.HOUR);
		return (hour);
	}
	public final static String getFullTime() {
		//return calendar.toString();
		
		Calendar calendar = Calendar.getInstance();
		int hourmil =  calendar.get(Calendar.HOUR_OF_DAY);
		String meridian = "";
		
		if(hourmil > 12){
			hourmil = hourmil - 12 ;
			meridian= "PM";
		}
		else if(hourmil < 12){
			//hourmil = hourmil;
			meridian= "AM";
		}
		else if(hourmil == 12){
			meridian= "PM";
		}
		
		String hour = "" + hourmil;
		String minute = "" + calendar.get(Calendar.MINUTE);
						
		hour = hour.length() == 1 ? "0" + hour : hour;
		minute = minute.length() == 1 ? "0" + minute : minute;
		
		String timereal = hour + ":" + minute  + " " + meridian;

		return (timereal);

	}

	public Properties getMainPropertiesFile() throws IOException {

		Properties props = new Properties();

		// props.load(new BufferedInputStream(getClass().getResourceAsStream("config.properties")));

		InputStream is = getClass().getResourceAsStream("config.properties");
		BufferedInputStream bis = new BufferedInputStream(is);
		props.load(bis);

		return props;

	}
	public final static int getMinute() {
		Calendar calendar = Calendar.getInstance();
		int minute = calendar.get(Calendar.MINUTE);
		return (minute);
	}
	public final static int getMonth() {
		Calendar calendar = Calendar.getInstance();
		int month = calendar.get(Calendar.MONTH);
		return (month);
	}
	/*****************************************************************************
	* Returns the requested parameter
	* 
	* @param request Object that encapsulates the request to the servlet
	* @param parameterName The name of the parameter value to return
	* @param checkRequestParameters when true, the request parameters are searched
	* @param checkInitParameters when true, the servlet init parameters are searched
	* @param isParameterRequired when true, an exception is thrown when the parameter cannot be found
	* @param defaultValue The default value to return when the parameter is not found
	* @return The parameter value
	* @exception java.lang.Exception Thrown when the parameter is not found
	*/
	public java.lang.String getParameter(
		HttpServletRequest request,
		String parameterName,
		boolean checkRequestParameters,
		boolean checkInitParameters,
		boolean isParameterRequired,
		String defaultValue)
		throws Exception {
		String[] parameterValues = null;
		String paramValue = null;

		// Get the parameter from the request object if necessary.
		if (checkRequestParameters) {
			parameterValues = request.getParameterValues(parameterName);
			if (parameterValues != null)
				paramValue = parameterValues[0];
		}

		// Get the parameter from the servlet init parameters if
		// it was not in the request parameter.
		if ((checkInitParameters) && (paramValue == null))
			paramValue = getServletConfig().getInitParameter(parameterName);

		// Throw an exception if the parameter was not found and it was required.
		// The exception will be caught by error processing and can be
		// displayed in the error page.
		if ((isParameterRequired) && (paramValue == null))
			throw new Exception("Parameter" + parameterName + "was not specified.");

		// Set the return to the default value if the parameter was not found
		if (paramValue == null)
			paramValue = defaultValue;

		return paramValue;
	}
	public final static int getSecond() {
		Calendar calendar = Calendar.getInstance();
		int second = calendar.get(Calendar.SECOND);
		return (second);
	}
	/**
	 * This method returns the base URL of the server upon which this
	 * servlet is running.  This makes it easy to dynamically build URLs
	 * (for example, when specifying the ACTION tag on an HTML <FORM>.
	 *
	 * @param req The HTTP servlet request as defined in the servlet architecture.
	 *
	 * @return URL, as a String, server running this servlet.
	 *
	 */
	public final static String getServerRoot(HttpServletRequest req) {
		return (
			"http://"
				+ req.getServerName()
				+ ":"
				+ Integer.toString(req.getServerPort())
				+ "");
	}
	/**
	 * This method returns the Time Stamp String used by the Header Message
	 * in the sockets protocol.
	 *
	 * @return TimeStamp, as a String, in this very moment.
	 *
	 */
	public final static String getTimeStamp() {

		Calendar calendar = Calendar.getInstance();
		String year = "" + calendar.get(Calendar.YEAR);
		String month = "" + (calendar.get(Calendar.MONTH) + 1);
		String day = "" + calendar.get(Calendar.DAY_OF_MONTH);
		String hour = "" + calendar.get(Calendar.HOUR_OF_DAY);
		String minute = "" + calendar.get(Calendar.MINUTE);
		String second = "" + calendar.get(Calendar.SECOND);

		year = year.substring(2);
		month = month.length() == 1 ? "0" + month : month;
		day = day.length() == 1 ? "0" + day : day;
		hour = hour.length() == 1 ? "0" + hour : hour;
		minute = minute.length() == 1 ? "0" + minute : minute;
		second = second.length() == 1 ? "0" + second : second;

		return (year + month + day + hour + minute + second);

	}
	public final static int getYear() {
		Calendar calendar = Calendar.getInstance();
		int year = calendar.get(Calendar.YEAR);
		return (year);
	}
	public void init(ServletConfig config, int log) throws ServletException {
		super.init(config);
		setConfig(log);
	}
	protected void outputHeader(PrintWriter out, String title) throws IOException {
		flexLog(title + ": outputHeader()...");
		out.print("<HTML><HEAD><TITLE>" + title + "</TITLE>");
		out.println("</HEAD><BODY>");
		flexLog(title + ": outputHeader() executed.");
	} // end outputHeader()
	/**
	 * This method prints the frame for tree of products and details.
	 * @param printStream PrintStream
	 * @param link String
	 * @param language String
	 * @param e Throwable
	 */
	protected void printProdFrame(PrintWriter out, String lnk, String langPath)
		throws IOException {
		out.println("<!-- frames -->");
		out.println("<frameset  rows=\"30%,*\">");
		out.println(
			"<frame name=\"list\" src=\""
				+ webAppPath
				+ langPath
				+ "ESD0711_products_tree_view.jsp\" marginwidth=\"10\" marginheight=\"10\" scrolling=\"no\" frameborder=\"NO\">");
		out.println(
			"<frame name=\"detail\" src=\""
				+ lnk
				+ "\" marginwidth=\"10\" marginheight=\"10\" scrolling=\"auto\" frameborder=\"NO\">");
		out.println("</frameset>");
		out.close();
	}
	/**
	 * This method prints the error information.
	 * @param printStream PrintStream
	 * @param e Throwable
	 */
	protected void printClose(PrintWriter out) throws IOException {
		out.println("<HTML>");
		out.println("<HEAD>");
		out.println("<TITLE>Close</TITLE>");
		out.println("</HEAD>");
		out.println("<BODY>");
		out.println("<SCRIPT LANGUAGE=\"JavaScript\">");
		out.println("		top.close()");
		out.println("</SCRIPT>");
		out.println("<P>Close it!!!</P>");
		out.println("</BODY>");
		out.println("</HTML>");
		out.close();
		return;
	}
	/**
	 * This method close the current window and refresh the opener.
	 * @param printStream PrintStream
	 * @param e Throwable
	 */
	protected void printCloseAndRefreshOpener(PrintWriter out) throws IOException {
		out.println("<HTML>");
		out.println("<HEAD>");
		out.println("<TITLE>Close</TITLE>");
		out.println("</HEAD>");
		out.println("<BODY>");
		out.println("<SCRIPT LANGUAGE=\"JavaScript\">");
		out.println("		top.opener.document.location.reload()");
		out.println("		top.close()");
		out.println("</SCRIPT>");
		out.println("<P>Close it!!!</P>");
		out.println("</BODY>");
		out.println("</HTML>");
		out.close();
		return;
	}
	/**
	 * This method prints the error information.
	 * @param printStream PrintStream
	 * @param e Throwable
	 */
	protected void printError(PrintWriter out, String e) throws IOException {
		flexLog(e); // print error to log file
		out.print("<HTML><HEAD><TITLE>eIBS: Error</TITLE>");
		out.println("</HEAD><BODY>");
		out.print("<FONT SIZE=6 FACE=Arial COLOR=#ff0000>");
		out.println(e);
		out.println("</FONT></BODY></HTML>");
		out.close();
		return;
	} // end printError() 
	/**
	 * This method prints the error information.
	 * @param printStream PrintStream
	 * @param e Throwable
	 */
	protected void printError(PrintWriter out, Throwable e) throws IOException {
		flexLog(e.toString()); // print error to log file
		out.print("<HTML><HEAD><TITLE>Item Retrieval: Error</TITLE>");
		out.println("</HEAD><BODY>");
		out.print("<FONT SIZE=6 FACE=Arial COLOR=#ff0000>");
		out.println(e.toString());
		out.println("</FONT></BODY></HTML>");
		out.close();
		return;
	} // end printError() 
	/**
	 * This method prints the error information.
	 * @param printStream PrintStream
	 * @param e Throwable
	 */
	protected void printLogInAgain(PrintWriter out) throws IOException {
		out.println("<HTML>");
		out.println("<HEAD>");
		out.println("<TITLE>Close</TITLE>");
		out.println("</HEAD>");
		out.println("<SCRIPT LANGUAGE=\"JavaScript\">");
		out.println("  function LogIn() {");
		out.println("		alert('eIBS Message\\n" + signOnMsg + "');");
		out.println("		top.close()");
		out.println("  }");
		out.println("</SCRIPT>");
		out.println("<BODY onLoad=\"LogIn()\">");
		out.println("</BODY>");
		out.println("</HTML>");
		out.close();
		return;
	}
	public void setConfig(int log) {

		if (log != -1)
			SuperServlet.logType = log;
		else
			SuperServlet.logType = JSEIBSProp.getLog();

		SuperServlet.iniSocket = JSEIBSProp.getIniSocket();
		SuperServlet.sckTimeOut = JSEIBSProp.getSocketTimeOut();
		SuperServlet.hostIP = JSEIBSProp.getHostIP();
		SuperServlet.webAppPath = JSEIBSProp.getWebAppPath();
		SuperServlet.srctx = JSEIBSProp.getSendRedirectCtx();
		SuperServlet.rootPath = JSEIBSProp.getRootPath();
		SuperServlet.bgPage = JSEIBSProp.getBgPage();
		SuperServlet.busyPage = JSEIBSProp.getBusyPage();
		SuperServlet.devPage = JSEIBSProp.getDevPage();
		SuperServlet.sckNotOpenPage = JSEIBSProp.getSckNotOpenPage();
		SuperServlet.sckNotRespondPage = JSEIBSProp.getSckNotRespPage();
		SuperServlet.formActive = JSEIBSProp.getFormActive();
		SuperServlet.scanActive = JSEIBSProp.getScanActive();
		SuperServlet.signOnMsg = JSEIBSProp.getSignOn();
		if (JSEIBSProp.getRelease().equals("47"))
			SuperServlet.CollFiller = 1;
		else
			if (JSEIBSProp.getRelease().equals("43"))
				SuperServlet.CollFiller = 4;

	}
	
	/**
	* This method returns true if you are running de Previous Year Inquiry.
	*
	* @return boolean.
	*
	*/
	public final static boolean hasPort(HttpServletRequest req) {

		String port = null;
		HttpSession session = (HttpSession) req.getSession(false);

		if (req.getParameter("PORT") != null) {
			port = (String) req.getParameter("PORT");
		} else if (session.getAttribute("PORT") != null) {
			port = (String) session.getAttribute("PORT");
		}
		session.setAttribute("PORT", port);
		
		return (port != null && !port.equals("")) ? true : false;
	}
	
	/**
	* This method returns true if you are running de Previous Year Inquiry.
	*
	* @return boolean.
	*
	*/
	public final static boolean isPrevYear(HttpServletRequest req) {

		boolean prevYear = false;
		String py = "false";
		HttpSession session = (HttpSession) req.getSession(false);

		String pyear = null;
		if (req.getParameter("PY") != null) {
			py = (String) req.getParameter("PY");
			pyear = req.getParameter("PYEAR");
		} else if (session.getAttribute("PY") != null) {
			py = (String) session.getAttribute("PY");
			pyear = (String) session.getAttribute("PYEAR");
		}
		session.setAttribute("PY", py);
		session.setAttribute("PYEAR", pyear);
		
		prevYear = (py.equalsIgnoreCase("true")) ? true : false;
		return prevYear;
	}

	public final static int getInitSocket(HttpServletRequest req) {
		int is = iniSocket;
		if (isPrevYear(req))
			is = iniSocket + 10;
		return (is);
	}
	
	/**
	 * Create a new instance of a MessageHandler with his associated MessageRouter
	 * @param key The key for the target
	 * @param request The request for reading some parameters that may change the resulting MessageHandler
	 * @return
	 * @throws ServiceLocatorException
	 */
	public MessageHandler getMessageHandler(String key, HttpServletRequest request) 
		throws ServiceLocatorException {
		if (hasPort(request)) {
			HttpSession session = (HttpSession) request.getSession(false);
			String port = (String) session.getAttribute("PORT");
			return getMessageHandler(port + "." + key);
		} else if (isPrevYear(request)) {
			HttpSession session = (HttpSession) request.getSession(false);
			String pyear = (String) session.getAttribute("PYEAR");
			if (pyear != null) key = pyear + "." + key;
			return getMessageHandler("py."+ key);
		} else {
			return getMessageHandler(key);
		}
	}
	
	/**
	 * Create a new instance of a MessageHandler with his associated MessageRouter
	 * @param key
	 * @return
	 * @throws ServiceLocatorException
	 */
	public MessageHandler getMessageHandler(String key) throws ServiceLocatorException {
		ServiceLocator serviceLocator = ServiceLocator.getInstance();
		MessageHandler mh = null;
		String host = serviceLocator.getSocketIp(key);
		int port = serviceLocator.getSocketPort(key);
		int timeout = serviceLocator.getSocketTimeOut(key);
		String packageName = serviceLocator.getSocketMsgPackage(key);
		try {
			mh =
				new MessageHandler(
					new TOSocketMessageRouter(
						host, 
						port,
						timeout), 
						packageName);
			return mh;
		} catch (Exception e) {
			if (mh == null) {
				throw new ServiceLocatorException("Socket not Open(Host = " + host + ", Port " + port + "). " +
					"Error: " + e.toString());				
			} else {
				throw new ServiceLocatorException(mh.toString() +
					"Error: " + e.toString());
			}
		}
	}
		
	/**
	 * Sets the MessageRecord fields reading the request, 
	 * MessageRecord Fields names and request parameters names must be the same  
	 * @param request
	 * @param message
	 * @throws Exception
	 */
	public void setMessageRecord(HttpServletRequest request, MessageRecord message) {
		// all the fields here
		java.util.Enumeration enu = message.fieldEnumeration();
		MessageField field = null;
		String value = null;
		while (enu.hasMoreElements()) {
			field = (MessageField) enu.nextElement();
			try {
				value = request.getParameter(field.getTag()).toUpperCase();
				if (value != null) {
					field.setString(value);
				}
			} catch (Exception e) {
			}
		}
	}

}