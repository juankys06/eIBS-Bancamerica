package datapro.eibs.menu;

/**
 * This type was created by Orestes Garcia.
 */
import java.io.*;
import java.net.*;
import java.beans.Beans;
import javax.servlet.*;
import javax.servlet.http.*;
 
public class JSLogOff extends datapro.eibs.master.SuperServlet {

	private HttpSession session;

/**
 * Insert the method's description here.
 * Creation date: (1/14/00 12:29:44 PM)
 */
public JSLogOff() {
	super();	
}
/**
 * This method was created by Orestes Garcia.
 */
public void destroy() {

	flexLog("free resources used by JSESS0040");
	
}
/**
 * This method was created by Orestes Garcia.
 */
public void init(ServletConfig config) throws ServletException {
	super.init(config);

}
/**
 * This method was created by Orestes Garcia.
 * @param request HttpServletRequest
 * @param response HttpServletResponse
 */
public void service(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {

	session = (HttpSession)req.getSession(false);
	
	if (session != null) {
		session.invalidate();
	}

	// set MINE type for HTTP Header
	res.setContentType("text/html");
	// get a handle to the output stream
	PrintWriter out = res.getWriter();


	out.println("<HTML>");
	out.println("<HEAD><TITLE>IBS Menu</TITLE></HEAD>");
	out.println("<BODY>");
	out.println("<SCRIPT>");
	out.println("	top.close();");
	out.println("</SCRIPT>");
	out.println("</BODY>");
	out.println("</HTML>");

	out.close();
	    
}
}