package datapro.eibs.tools;

/**
 * Insert the type's description here.
 * Creation date: (8/28/2000 4:02:17 PM)
 * @author: Orestes Garcia
**/

import java.io.OutputStream;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import datapro.eibs.beans.ESS0030DSMessage;
import datapro.eibs.master.JSEIBSProp;
import datapro.org.jdom.Comment;
import datapro.org.jdom.Document;
import datapro.org.jdom.Element;
import datapro.org.jdom.output.XMLOutputter;

public class JSNewTemplate extends datapro.eibs.master.SuperServlet {
/**
 * JSReportManager constructor comment.
 */
public JSNewTemplate() {
	super();
}
/**
 * Insert the method's description here.
 * Creation date: (9/5/2000 3:47:48 PM)
 * @param out java.io.OutputStream
 * @exception java.io.IOException The exception description.
 */
public synchronized void saveXMLOutput(OutputStream out, HttpSession ses) throws java.io.IOException {
	
	ESS0030DSMessage user = null;
	user = (ESS0030DSMessage)ses.getAttribute("currUser");
	
	// Create the root element
	Element root = new Element("eIBSFormDesign");

	root.addContent(new Comment("Documents and FTP Config for eIBSForm Tool"));
	
	Document doc = new Document(root);

	Element ftp = new Element("eIBSFTP");
	ftp.addAttribute("Purpose", "New");
	ftp.addAttribute("Language", user.getE01LAN().toLowerCase());
	ftp.addAttribute("Host", JSEIBSProp.getFtpFormHost());
	ftp.addAttribute("Port", JSEIBSProp.getFtpFormPort());
	ftp.addAttribute("UserID", JSEIBSProp.getFtpFormUserID());
	ftp.addAttribute("Password", JSEIBSProp.getFtpFormPassword());
	if (!JSEIBSProp.getFtpPathFormData().equals(""))
		ftp.addAttribute("DataPath", JSEIBSProp.getFtpPathFormData());
	if (!JSEIBSProp.getFtpPathFormConfig().equals(""))
		ftp.addAttribute("ConfigPath", JSEIBSProp.getFtpPathFormConfig());
	if (!JSEIBSProp.getFtpFormFirewallType().equals(""))
		ftp.addAttribute("FirewallType", JSEIBSProp.getFtpFormFirewallType());
	if (!JSEIBSProp.getFtpFormFWAuthenticate().equals(""))
		ftp.addAttribute("FWAuthenticate", JSEIBSProp.getFtpFormFWAuthenticate());
	if (!JSEIBSProp.getFtpFormFWUserID().equals(""))
		ftp.addAttribute("FWUserID", JSEIBSProp.getFtpFormFWUserID());
	if (!JSEIBSProp.getFtpFormFWPassword().equals(""))
		ftp.addAttribute("FWPassword", JSEIBSProp.getFtpFormFWPassword());
	ftp.addContent(new Comment("FTP and Firewall Configuration"));

	root.addContent(ftp);

	// Output the document, use standard formatter
	try {
		XMLOutputter fmt = new XMLOutputter();
		fmt.output(doc, out);
	}
	catch (Exception e) {
		System.out.println("Error: " + e);
	}
}
/**
 * service method comment.
 */
public void service(HttpServletRequest req, HttpServletResponse res) throws javax.servlet.ServletException, java.io.IOException {

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

		try {

			res.setContentType("eibs/ftp");

			OutputStream out = (OutputStream) res.getOutputStream();
			saveXMLOutput(out, session);
			out.close();

		}
		catch (Exception e) {
		}
	}
		
}
}