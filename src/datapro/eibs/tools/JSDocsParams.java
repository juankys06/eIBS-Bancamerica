package datapro.eibs.tools;

/**
 * This type was created by Orestes Garcia.
 */
import java.io.*;
import java.net.*;
import java.beans.Beans;
import javax.servlet.*;
import javax.servlet.http.*;
 
public class JSDocsParams extends datapro.eibs.master.SuperServlet {
  	private HttpSession session;
/**
 * Insert the method's description here.
 * Creation date: (1/14/00 12:29:44 PM)
 */
public JSDocsParams() {
	super(SYSTEM);	
}
/**
 * This method was created by Orestes Garcia.
 */
public void destroy() {

	    
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

	String outParams ="";
	
	res.setContentType("text/html");
	res.setHeader("Pragma", "No-cache");
	res.setDateHeader ("Expires", 0);
	res.setHeader("Cache-Control", "no-cache");
	
	PrintWriter  out = res.getWriter();

	session = (HttpSession)req.getSession(false);
	
	if (session != null) {

		outParams = (String)session.getValue("docsParams");
		out.println(outParams);
		
	}

	out.close();

}
}