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

import datapro.eibs.master.JSEIBSProp;
import datapro.org.jdom.Comment;
import datapro.org.jdom.Document;
import datapro.org.jdom.Element;
import datapro.org.jdom.output.XMLOutputter;

public class JSUpload extends datapro.eibs.master.SuperServlet {
/**
 * JSReportManager constructor comment.
 */
public JSUpload() {
	super();
}
/**
 * Insert the method's description here.
 * Creation date: (9/5/2000 3:47:48 PM)
 * @param out java.io.OutputStream
 * @exception java.io.IOException The exception description.
 */
public synchronized void saveXMLOutput(OutputStream out) throws java.io.IOException {
	
	// Create the root element
	Element root = new Element("eIBSScanParams");

	root.addContent(new Comment("Documents and FTP Config for eIBSScan Tool"));
	
	Document doc = new Document(root);

	Element ftp = new Element("eIBSFTP");
	ftp.addAttribute("Host", JSEIBSProp.getFtpImgHost());
	ftp.addAttribute("Port", JSEIBSProp.getFtpImgPort());
	ftp.addAttribute("UserID", JSEIBSProp.getFtpImgUserID());
	ftp.addAttribute("Password", JSEIBSProp.getFtpImgPassword());

	if (!JSEIBSProp.getFtpPathScanData().equals(""))
		ftp.addAttribute("Path", JSEIBSProp.getFtpPathScanData());
	if (!JSEIBSProp.getFtpImgFirewallType().equals(""))
		ftp.addAttribute("FirewallType", JSEIBSProp.getFtpImgFirewallType());
	if (!JSEIBSProp.getFtpImgFWAuthenticate().equals(""))
		ftp.addAttribute("FWAuthenticate", JSEIBSProp.getFtpImgFWAuthenticate());
	if (!JSEIBSProp.getFtpImgFWUserID().equals(""))
		ftp.addAttribute("FWUserID", JSEIBSProp.getFtpImgFWUserID());
	if (!JSEIBSProp.getFtpImgFWPassword().equals(""))
		ftp.addAttribute("FWPassword", JSEIBSProp.getFtpImgFWPassword());

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

			res.setContentType("eibs/scan");

			OutputStream out = (OutputStream) res.getOutputStream();
			saveXMLOutput(out);
			out.close();

		}
		catch (Exception e) {
		}
	}
		
}
}