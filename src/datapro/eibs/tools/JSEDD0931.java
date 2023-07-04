package datapro.eibs.tools;

import java.io.IOException;
import java.io.OutputStream;
import java.math.BigDecimal;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.jdom.Comment;
import org.jdom.Document;
import org.jdom.Element;
import org.jdom.output.XMLOutputter;

import com.datapro.eibs.exception.FacadeException;
import com.datapro.eibs.images.facade.FAProcess;

import datapro.eibs.beans.ELEERRMessage;
import datapro.eibs.beans.ESS0030DSMessage;
import datapro.eibs.master.JSEIBSProp;
import datapro.eibs.master.JSEIBSServlet;
import datapro.eibs.master.Util;

/**
 * @author erodriguez
 *
 * To change the template for this generated type comment go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
public class JSEDD0931 extends JSEIBSServlet {

	private static final int R_CHECK_LIST			= 1;
	private static final int A_CHECK_LIST			= 2;
	private static final int A_REFRESH_PAGE			= 3;
	
	//private static final String separator 			= ",";
	
	
	protected void processRequest(ESS0030DSMessage user,
			HttpServletRequest req, HttpServletResponse res,
			HttpSession session, int screen) throws ServletException,
			IOException {

		switch (screen) {
			case R_CHECK_LIST :
				procReqCheckList(user, req, res, session);
				break;
			case A_CHECK_LIST :
				procActCheckList(user, req, res, session);
				break;
			case A_REFRESH_PAGE :
				procActRefreshPage(user, req, res, session);
				break;
			default :
				forward("MISC_not_available.jsp", req, res);
				break;
		}
	}

	private void procActRefreshPage(ESS0030DSMessage user, HttpServletRequest req, HttpServletResponse res, HttpSession session) throws ServletException, IOException {
		FAProcess process = new FAProcess();
		String uid = req.getParameter("PROCESS") == null ? "0" : req.getParameter("PROCESS").trim();
		try {
			if (process.isRunning(uid)) {
				forward("EDD0931_check_reader_viewer.jsp?PROCESS=" + uid, req, res);
			} else {
				String result = process.result(uid);
				if (process.hasError(uid)) {
					ELEERRMessage msgError = new ELEERRMessage();
					msgError.setERRNUM(new BigDecimal("1"));
					msgError.setERNU01(result.substring(0, result.indexOf(':')));
					msgError.setERDS01(result.substring(result.indexOf(':') + 1, result.length()));
					session.setAttribute("error", msgError);
				}
				process.kill(uid);
				//res.sendRedirect(webAppPath + bgPage);
				forward("EDD0931_check_reader_viewer.jsp?PROCESS=0&CHECK=" + result, req, res);
			}
		} catch (Exception e) {
			flexLog(e.getClass().getName() + " Exception ocurred. Error = " + e);
			throw new ServletException(e);
		}
	}

	private void procReqCheckList(ESS0030DSMessage user, HttpServletRequest req, HttpServletResponse res, HttpSession session) throws ServletException, IOException {
		
		FAProcess process = new FAProcess();
		String uid = user.getH01TIM();
		try {
			if (!process.isAlive(user.getH01USR(), uid)) {
				process.register(user.getH01USR(), uid, "Check Reader");
			}
			forward("EDD0931_check_reader_viewer.jsp?ACTION=SCAN&PROCESS=" + uid, req, res);
		} catch (FacadeException e) {
			flexLog("FacadeException ocurred. Exception = " + e);
			throw new ServletException(e);
		}
	}

	private void procActCheckList(ESS0030DSMessage user, HttpServletRequest req, HttpServletResponse res, HttpSession session) {
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
				res.setContentType("eibs/crt");
				OutputStream out = (OutputStream) res.getOutputStream();
				saveXMLOutput(user, out, req);
				out.close();
			} catch (Exception e) {
				flexLog("Exception ocurred. Exception = " + e);
			}
		}
	}

	private void saveXMLOutput(ESS0030DSMessage user, OutputStream out, HttpServletRequest req) {
		// Create the root element
		Element root = new Element("eIBSCheckReader");
		root.addContent(new Comment("Config for eIBS Check Reader Tool"));
		Document doc = new Document(root);
		
		Element config = new Element("eIBSConfig");
		config.setAttribute("Server", JSEIBSProp.getCheckReaderServer());
		config.setAttribute("Database", JSEIBSProp.getCheckReaderDatabase());
		if (!JSEIBSProp.getCheckReaderWinAuth().equals("")) {
			config.setAttribute("WinAuth", JSEIBSProp.getCheckReaderWinAuth());
		} else {
			config.setAttribute("WinAuth", "Y");
		}
		config.setAttribute("User", JSEIBSProp.getCheckReaderUser());
		config.setAttribute("Password", JSEIBSProp.getCheckReaderPassword());
		if (!JSEIBSProp.getCheckReaderWorkDir().equals(""))
			config.setAttribute("WorkDir", JSEIBSProp.getCheckReaderWorkDir());
		if (!JSEIBSProp.getCheckReaderScanner().equals("")) {
			config.setAttribute("FirewallType", JSEIBSProp.getCheckReaderScanner());
		} else {
			config.setAttribute("FirewallType", "150");
		}
		String url = getServerRoot(req) + webAppPath + "/servlet/datapro.eibs.tools.JSCheckReaderDone";
		if (url.indexOf("http://") != 0)
			url = "http://" + url.substring(6);
		flexLog("URL -> " + url);
		config.setAttribute("URL", url);
		config.setAttribute("UserID", user.getH01USR());

		String rundate = "";
		rundate = Util.formatDate(user.getE01RDY(), user.getE01RDM(), user.getE01RDD());
		config.setAttribute("RunDate", rundate);
		String process = req.getParameter("PROCESS").trim();
		config.setAttribute("Process", process);
		config.setAttribute("Micr", JSEIBSProp.getCheckReaderMicr());
		
		config.addContent(new Comment("Database Configuration"));
		root.addContent(config);
		
		// Output the document, use standard formatter
		try {
			XMLOutputter fmt = new XMLOutputter();
			fmt.setEncoding("ISO-8859-1");
			fmt.output(doc, out);
		} catch (Exception e) {
			flexLog("Exception ocurred sending xml. Exception = " + e);
		}
	}

}
