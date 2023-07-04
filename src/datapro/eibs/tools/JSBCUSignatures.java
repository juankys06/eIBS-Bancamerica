package datapro.eibs.tools;

/**
 * Insert the type's description here.
 * Creation date: (8/28/2000 4:02:17 PM)
 * @author: Orestes Garcia
**/

import java.io.*;
import java.util.*;

import java.net.*;
import java.beans.Beans;
import javax.servlet.*;
import javax.servlet.http.*;
import datapro.eibs.beans.*;

import datapro.eibs.master.JSEIBSProp;









 
import datapro.org.jdom.DocType;
import datapro.org.jdom.Element;
import datapro.org.jdom.Attribute;
import datapro.org.jdom.JDOMException;
import datapro.org.jdom.Comment;
import datapro.org.jdom.Document;
import datapro.org.jdom.Namespace;
import datapro.eibs.sockets.*;
import datapro.org.jdom.output.XMLOutputter;

public class JSBCUSignatures extends datapro.eibs.master.SuperServlet {

/**
 * JSReportManager constructor comment.
 */
public JSBCUSignatures() {
	super();
}

/**
 * service method comment.
 */
public void service(HttpServletRequest req, HttpServletResponse res) throws javax.servlet.ServletException, java.io.IOException {

	ESS0030DSMessage msgUser = null;
	ESD008001Message msgClientPersonal = null;
	ESD008002Message msgClientEntity = null;
	UserPos userPO = null;
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

		int screen = R_SIGN_CARD_PERSONAL;

		try {

			userPO = (UserPos)session.getValue("userPO");
			msgUser = (ESS0030DSMessage)session.getValue("currUser");
			String ID = "";
			if (userPO.getOption().equals("CLIENT_P")) {
				msgClientPersonal = (ESD008001Message) session.getValue("client");
				ID = msgClientPersonal.getE01IDN();
			}
			else if (userPO.getOption().equals("CLIENT_C")) {
				msgClientEntity = (ESD008002Message) session.getValue("client");
				ID = msgClientEntity.getE02IDN();
			}

			// Here we should get the path from the user profile
			LangPath = super.rootPath + msgUser.getE01LAN() + "/";
			
			Element root = new Element("BCUSignature");

			root.addContent(new Comment("Interface Program for BCUSignature Tool"));
			
			Document doc = new Document(root);

			Element i = new Element("Interface");
			
			try {
				screen = Integer.parseInt(req.getParameter("SCREEN"));
			}
			catch (Exception e) {
				flexLog("Screen set to default value");
			}
		
			switch (screen) {
				case R_SIGN_CARD_PERSONAL :
					i.addAttribute("Program", "C:\\BIN\\S\\FIRMACLI.EXE");
					i.addAttribute("Param1", ID.trim());
					break;
				case R_FICHA_CUSTOMER :
					i.addAttribute("Program", "C:\\BIN\\S\\FICHAEMP.EXE");
					i.addAttribute("Param1", ID.trim() + "~0");
					break;
				case R_SIGN_CARD_CORP :
				default :
					res.sendRedirect(super.srctx + LangPath + super.devPage);
					return;
			}

			root.addContent(i);

			res.setContentType("eibs/bcs");
			OutputStream out = (OutputStream) res.getOutputStream();
			XMLOutputter fmt = new XMLOutputter();
			fmt.output(doc, out);
			out.close();

		}
		catch (Exception e) {
			flexLog("Error: " + e);
			res.sendRedirect(super.srctx + LangPath + super.devPage);
		}
	}
		
}

	private String LangPath = "S";
	static final int R_FICHA_CUSTOMER		= 3;
	static final int R_SIGN_CARD_CORP		= 5;
	static final int R_SIGN_CARD_PERSONAL	= 1;
}