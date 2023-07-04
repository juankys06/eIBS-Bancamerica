package datapro.eibs.tools;

/**
 * Insert the type's description here.
 * Creation date: (9/17/2001 1:57:32 PM)
 * @author: Ramses Amaro
 */
import java.io.*;
import java.net.*;
import java.beans.Beans;
import javax.servlet.*;
import javax.servlet.http.*;
import datapro.eibs.beans.*;
import datapro.eibs.master.Util;
 
public class JSSignatureImage extends datapro.eibs.master.SuperServlet{
/**
 * JSCheckImage constructor comment.
 */
public JSSignatureImage() {
	super();
}
/**
 * service method comment.
 */
public void service(HttpServletRequest req, HttpServletResponse res) throws javax.servlet.ServletException, java.io.IOException {

  	HttpSession session = null;
	JBListRec beanList = null;
	
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

		try {
			beanList = (datapro.eibs.beans.JBListRec)session.getAttribute("chkList");
			int row = Integer.parseInt(req.getParameter("ROW"));
			beanList.setCurrentRow(row);
				
			String ACCT = beanList.getRecord(0);
			String SRV;
			String PARAM;

			try {
				SRV = req.getParameter("SRV");
				PARAM = req.getParameter("PARAM");
				flexLog("Signature Address: " + SRV + "?" + PARAM + "=" + ACCT);
				res.sendRedirect(super.srctx + SRV + "?" + PARAM + "=" + ACCT);
			}
			catch (Exception e) {
				res.setContentType("text/html");
				printClose(res.getWriter());
			}
			
			}
		catch (Exception e) {
			e.printStackTrace();
			flexLog("Error: " + e);
		  	throw new RuntimeException("Socket Communication Error");
		}
		
	}
		
}
}
