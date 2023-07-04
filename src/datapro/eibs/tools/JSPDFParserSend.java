package datapro.eibs.tools;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.net.URL;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import datapro.eibs.beans.ELEERRMessage;
import datapro.eibs.beans.ESS0030DSMessage;
import datapro.eibs.beans.EWD0900DSMessage;
import datapro.eibs.beans.JBObjList;
import datapro.eibs.beans.UserPos;
import datapro.eibs.master.JSEIBSProp;
import datapro.eibs.master.SuperServlet;
import datapro.eibs.services.ExcelMacroModifier;
import datapro.eibs.services.PDFTextParser;
import datapro.eibs.sockets.MessageContext;
import datapro.eibs.sockets.MessageRecord;

/**
 * Takes the PDF report file and parses it in a CSV file. Creation date:
 * (24/02/2010 06:32:32 PM)
 * 
 * @author: Catalina Sepulveda
 */
public class JSPDFParserSend extends datapro.eibs.master.SuperServlet {

	private String LangPath;

	protected void sendCSV(String fileName, OutputStream out)
			throws java.io.IOException {
		InputStream in = null;

		// Output the document, use standard formatter
		try {
			flexLog("about to send file " + JSEIBSProp.getEODPDFPath() + "/"
					+ fileName);

			URL url = new URL(fileName);
			in = url.openStream();

			PDFTextParser a = new PDFTextParser();
			a.pdfToCSV(in, out);

		} catch (Exception e) {
			flexLog("Exception ocurred. Exception = " + e);
		} finally {
			in.close();
		}
	}
	
	protected void sendExcel(String user, String fileName, String simpleReportName, OutputStream out, String absoluteURL, String absolutePath)
			throws java.io.IOException {
		InputStream in = null;
		

		// Output the document, use standard formatter
		try {
			URL url = new URL(fileName);
			in = url.openStream();
			
			//Creates a TXT file on a temporal directory
			PDFTextParser parser = new PDFTextParser();
			//String outPath = this.getServletContext().getRealPath(arg0) + "/reports/";
			parser.pdfToTXT(user, simpleReportName, absolutePath, in);
			
			URL macroUrl = this.getServletContext().getResource("/reports/Macro.xls");
			
			ExcelMacroModifier m = new ExcelMacroModifier();
			//m.pdfToExcel(macroUrl.getPath(), out, simpleReportName);
			m.pdfToExcel(user, macroUrl.openStream(), out, simpleReportName,absoluteURL );
			out.flush();

		} catch (Exception e) {
			flexLog("Exception ocurred. Exception = " + e);
		} finally {
			in.close();
		}
	}

	protected void sendCSV(String fileName, FileOutputStream out)
			throws java.io.IOException {
		InputStream in = null;

		// Output the document, use standard formatter
		try {
			flexLog("about to send file " + JSEIBSProp.getEODPDFPath() + "/"
					+ fileName);

			URL url = new URL(fileName);
			in = url.openStream();

			PDFTextParser a = new PDFTextParser();
			a.pdfToCSV(in, out);

		} catch (Exception e) {
			flexLog("Exception ocurred. Exception = " + e);
		} finally {
			in.close();
		}
	}

	/**
	 * service method comment.
	 */
	public void service(HttpServletRequest req, HttpServletResponse res)
			throws javax.servlet.ServletException, java.io.IOException {

		HttpSession session = null;

		session = (HttpSession) req.getSession(false);
		ServletOutputStream out = (ServletOutputStream) res.getOutputStream();
		

		ESS0030DSMessage msgUser = (ESS0030DSMessage)session.getAttribute("currUser");

		try {
			String fileName = "";
			String source = req.getParameter("source");
			if (source != null) {
				fileName = getPDFPathForUserSpool(req, source);
			} else {
				fileName = req.getParameter("REPNAME");
			}

			int firstIndex = fileName.lastIndexOf("/");
			int lastIndex = fileName.length();

			String reportName = "attachment; filename=\"export_"
					+ fileName.substring(firstIndex + 1, lastIndex - 4)
					+ ".txt\"";
			
			String simpleReportName = fileName.substring(firstIndex + 1, lastIndex - 4)	+ ".txt";
			
			

			try {
				// set MIME type for xls
				reportName = "attachment; filename=\""+fileName.substring(firstIndex + 1, lastIndex - 4)+".xls\"";
				res.setContentType("application/vnd.ms-excel");			
				res.setHeader("Content-disposition", reportName);
				
				String target = req.getRequestURL().toString();
				String [] a = req.getRequestURI().split("/");
				String absoluteURL = target.substring(0,target.indexOf(a[2]));			
				
				String absolutePath = req.getRealPath("reports")+"/";
				
				sendExcel(msgUser.getH01USR(), fileName, simpleReportName, out, absoluteURL, absolutePath);
				//sendPDF(fileName, out, res);
				out.close();
				// sendCSV(fileName, out);

			} catch (Exception e) {
				flexLog("Error: " + e);
			} finally {
				out.close();
			}

		} catch (Exception e) {
			flexLog("Exception ocurred. Exception = " + e);
		}
		/* } */

	}

	protected void sendPDF(String fileName, OutputStream out,
			HttpServletResponse response) throws java.io.IOException {

		// Output the document, use standard formatter
		try {
			// flexLog("about to send file " + fileName);

			URL url = new URL(fileName);
			InputStream in = url.openStream();
			int size = 0;
			
			byte[] data = new byte[2048];
			BufferedInputStream bin = new BufferedInputStream(in);
			while ((size = bin.read(data, 0, data.length)) != -1) {
				out.write(data, 0, size);
			}
			response.setContentLength(size);
			out.flush();

		} catch (Exception e) {
			flexLog("Exception ocurred. Exception = " + e);
		}
	}

	private String getPDFPathForUserSpool(HttpServletRequest req, String source) {

		HttpSession session = req.getSession(false);
		Socket s = null;
		String pdfPath = "";

		MessageRecord newmessage = null;
		EWD0900DSMessage msgList = null;
		ESS0030DSMessage msgUser = null;
		ELEERRMessage msgError = null;
		JBObjList beanList = null;
		UserPos userPO = null;
		boolean IsNotError = false;

		MessageContext mc = null;
		userPO = (UserPos) session.getAttribute("userPO");
		beanList = (JBObjList) session.getAttribute("EWD0900Help");
		int row = 0;
		try {
			row = Integer.parseInt(req.getParameter("ROW"));
		} catch (Exception exception) {
		}
		beanList.setCurrentRow(row);

		try {
			msgUser = (ESS0030DSMessage) session.getAttribute("currUser");
			LangPath = SuperServlet.rootPath + msgUser.getE01LAN() + "/";
			flexLog("Opennig Socket Connection");
			s = new Socket(SuperServlet.hostIP,
					SuperServlet.getInitSocket(req) + 1);
			s.setSoTimeout(SuperServlet.sckTimeOut);

			mc = new MessageContext(new DataInputStream(
					new BufferedInputStream(s.getInputStream())),
					new DataOutputStream(new BufferedOutputStream(s
							.getOutputStream())), "datapro.eibs.beans");

			msgList = (EWD0900DSMessage) beanList.getRecord();
			msgList.setRWDTYP("I");

			mc.sendMessage(msgList);
			flexLog("EWD0900DS Message Sent");

			newmessage = mc.receiveMessage();
			if (newmessage.getFormatName().equals("ELEERR")) {
				msgError = (ELEERRMessage) newmessage;
				IsNotError = msgError.getERRNUM().equals("0");
				flexLog("IsNotError = " + IsNotError);
				session.setAttribute("error", msgError);
			}
			newmessage = mc.receiveMessage();
			if (newmessage.getFormatName().equals("EWD0900DS")) {
				msgList = (EWD0900DSMessage) newmessage;
				pdfPath = JSEIBSProp.getEODPDFURL() + "/" + msgList.getSWDPTH();
			} else {
				flexLog("Message " + newmessage.getFormatName() + " received.");
			}
		} catch (Exception e) {
			e.printStackTrace();
			flexLog("Error: " + e);
			throw new RuntimeException("Socket Communication Error");
		}

		return pdfPath;

	}

}