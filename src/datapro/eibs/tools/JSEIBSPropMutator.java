package datapro.eibs.tools;

/**
 * Insert the type's description here.
 * Creation date: (1/19/00 6:08:55 PM)
 * @author: Leydis Perez
 */

import java.io.*;
import java.net.*;
import java.beans.Beans;
import javax.servlet.*;
import javax.servlet.http.*;



 
import datapro.eibs.master.JSEIBSProp;
import java.util.*;

public class JSEIBSPropMutator extends datapro.eibs.master.SuperServlet {
	
	private String LangPath = "S";

/**
 * JSECLI001 constructor comment.
 */
public JSEIBSPropMutator() {
	super();
}
/**
 * This method was created by Orestes Garcia.
 */
public void destroy() {

	flexLog("free resources used by JSELC0450");
	
}
/**
 * This method was created by David Mavilla.
 */
public void init(ServletConfig config) throws ServletException {
	super.init(config);
	path = config.getInitParameter("eibsPath");
}




	



public void service(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
   
	int screen = R_EIBS_PROP;
	
	try {
	
		LangPath = super.rootPath + "E/";
		
		try {
			screen = Integer.parseInt(req.getParameter("SCREEN"));
		}
		catch (Exception e) {
			flexLog("Screen set to default value");
		}
	
		switch (screen) {
			case R_EIBS_PROP :
				procReqEIBSProp(req, res);
				break;									
			case A_EIBS_PROP : 
				procActionEIBSProp(req, res);
				break;

			default :
				// res.sendRedirect(super.srctx + "");
				break;
		}

	}
	catch (Exception e) {
		flexLog("Error: " + e);
		// res.sendRedirect(super.srctx + "");
	}
	
	
}
			


	static final int A_EIBS_PROP		= 2;
	static final int R_EIBS_PROP		= 1;

/**
 * This method was created in VisualAge.
 */
private synchronized void procActionEIBSProp(HttpServletRequest req, HttpServletResponse res)
			throws ServletException, IOException {

	Properties eIBSProperties = new Properties();
	FileInputStream fin = null;
	FileOutputStream fout = null;
	try {
		fin = new FileInputStream(path + JSEIBSProp.getPropertyFileName() + ".properties");
	}
	catch (FileNotFoundException e) {
		System.out.println("Failed to load Properties file.   Be sure '" + path + JSEIBSProp.getPropertyFileName() +
			"' is located correctly.");
	}

	try {
		if (fin != null) {
			eIBSProperties.load(fin);
			fin.close();
		}
	}
	catch (IOException e) {
		System.out.println("Error reading file");
	}
	
	// Update property file
	Enumeration fields = req.getParameterNames();
	String fieldName = null;
	String value = null;
	while (fields.hasMoreElements()) {
		fieldName = (String)fields.nextElement();
		try {
			value = req.getParameter(fieldName).toUpperCase();
			if (value != null) {
				eIBSProperties.put(fieldName, value);
			}
		}
		catch (Exception e) {
		}	
	}

	try {
		fout = new FileOutputStream(path + JSEIBSProp.getPropertyFileName() + ".properties");

		eIBSProperties.store(fout, "");
		fout.close();

		JSEIBSProp.initProperties();
	}
	catch (IOException e) {
		System.out.println("Error writing file");
	}
		
	try {
		// flexLog("About to call Page: " + LangPath + "eIBS_properties_confirm.jsp");
		// callPage(LangPath + "eIBS_properties_confirm.jsp", req, res);
		res.setContentType("text/html");
		PrintWriter  out = res.getWriter();
		eIBSProperties.list(out);
		out.close();

	}
	catch (Exception e) {
		flexLog("Exception calling page " + e);
	}

}

/**
 * This method was created in VisualAge.
 */
private synchronized void procReqEIBSProp(HttpServletRequest req, HttpServletResponse res)
			throws ServletException, IOException {

	try {
		flexLog("About to call Page: " + LangPath + "eIBS_properties_change.jsp");
		callPage(LangPath + "eIBS_properties_change.jsp", req, res);	
	}
	catch (Exception e) {
		flexLog("Exception calling page " + e);
	}

}

	private String path = "";
}