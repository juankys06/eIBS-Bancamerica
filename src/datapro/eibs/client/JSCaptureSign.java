package datapro.eibs.client;

/**
 * Insert the type's description here.
 * Creation date: (1/19/00 6:08:55 PM)
 * @author: Orestes Garcia
 */
import java.io.*;
import java.net.*;
import java.beans.Beans;
import javax.servlet.*;
import javax.servlet.http.*;
import datapro.eibs.beans.*;
 
public class JSCaptureSign extends datapro.eibs.master.SuperServlet {

  	private HttpSession session;

/**
 * JSECLI001 constructor comment.
 */
public JSCaptureSign() {
	super();
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
public void service(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
   
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

		String ID = req.getParameter("ID");
		
		res.setContentType("application/eIBSsignature");
		PrintWriter out = res.getWriter();
		// out.println("12345678,Orestes Garcia/99999999, David Mavilla");
		out.println(ID);
		out.close();
		
	}
		
}
}