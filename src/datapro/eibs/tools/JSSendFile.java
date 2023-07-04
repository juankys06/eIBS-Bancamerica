package datapro.eibs.tools;

import java.io.IOException;
import java.io.OutputStream;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import datapro.eibs.beans.ESS0030DSMessage;
import datapro.eibs.generic.JOReadFile;
import datapro.eibs.master.SuperServlet;
import datapro.eibs.master.Util;

/**
 * Insert the type's description here. 
 * Creation date: (8/3/2001 12:28:32 PM)
 * @author: Enrique M. Almonte
 */
public class JSSendFile extends datapro.eibs.master.SuperServlet {
	
private String LangPath = "S";
private String fileName;
private String mimeType;

public synchronized void send(OutputStream out) throws java.io.IOException {
	try {
		JOReadFile.readFile(fileName, out);
	} catch (Exception e) {
		flexLog("Exception ocurred. Exception = " + e); 
	}
}
/**
 * service method comment.
 */
public void service(HttpServletRequest req, HttpServletResponse res) throws javax.servlet.ServletException, java.io.IOException {

  	HttpSession session = null;
	session = (HttpSession)req.getSession(false); 
	
	ESS0030DSMessage msgUser = null;
	if (session == null) {
			try {
				res.setContentType("text/html");
				printLogInAgain(res.getWriter());
			} catch (Exception e) {
				e.printStackTrace();
				flexLog("Exception ocurred. Exception = " + e);
			}

	} else {
		try {
			msgUser = (datapro.eibs.beans.ESS0030DSMessage) session.getValue("currUser");
			// Here we should get the path from the user profile
			LangPath = super.rootPath + msgUser.getE01LAN() + "/";
		} catch (Exception e) {
			flexLog("Error: " + e);
			res.sendRedirect(super.srctx + LangPath + super.sckNotRespondPage);
		}
	}
	
	try {
		fileName = req.getParameter("file");
		if ( datapro.eibs.master.Util.existFile(fileName)) {
			mimeType = "text/html";
			//res.setContentType(datapro.eibs.master.JSEIBSProp.getEXTFILEMIMETYPE());
			res.setHeader("Cache-Control","no-cache");
			OutputStream out = (OutputStream) res.getOutputStream();
			send(out);
			out.close();
		} else {
			res.sendRedirect(super.srctx + LangPath + "filenotfound.jspl");
		}
	}
	catch (Exception e) {
		flexLog("Exception ocurred. Exception = " + e);
	}
		
}

}