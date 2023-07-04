package datapro.eibs.tools;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URL;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.ibm.ws.http.HttpResponse;

import datapro.eibs.master.JSEIBSProp;
import datapro.eibs.services.ExcelMacroModifier;
import datapro.eibs.services.PDFTextParser;

/**
 * Insert the type's description here. Creation date: (8/3/2001 12:28:32 PM)
 * 
 * @author: Enrique M. Almonte
 */
public class JSEODPDFSend extends datapro.eibs.master.SuperServlet {

	protected void sendPDF(String fileName, OutputStream out, HttpServletResponse response)
			throws java.io.IOException {

		// Output the document, use standard formatter
		try {
			//flexLog("about to send file " + fileName);

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
	
	protected void sendExcel(String user, String fileName,
			String simpleReportName, OutputStream out, String absoluteURL)
			throws java.io.IOException {
		InputStream in = null;

		// Output the document, use standard formatter
		try {
			URL url = new URL(fileName);
			in = url.openStream();

			// Creates a TXT file on a temporal directory
			PDFTextParser parser = new PDFTextParser();
			String outPath = absoluteURL + "/reports/";
			parser.pdfToTXT(user, simpleReportName, outPath, in);

			URL macroUrl = this.getServletContext().getResource(
					"/reports/Macro.xls");

			ExcelMacroModifier m = new ExcelMacroModifier();
			// m.pdfToExcel(macroUrl.getPath(), out, simpleReportName);
			m.pdfToExcel(user, macroUrl.openStream(), out, simpleReportName,
					absoluteURL);
			out.flush();

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

		try {

			String fileName = req.getParameter("REPNAME");
			if(fileName == null){
				fileName = (String)req.getAttribute("REPNAME");
			}
			
			res.setContentType("application/pdf");

			OutputStream out = (OutputStream) res.getOutputStream();
			setResponseHeader(res, fileName);
			int firstIndex = fileName.lastIndexOf("/");
			int lastIndex = fileName.length();
			
			String reportName = fileName.substring( firstIndex+1, lastIndex - 4 );
			sendPDF(fileName, out, res);
			//sendExcel(fileName, reportName  , out);
			out.close();

		} catch (Exception e) {
			flexLog("Exception ocurred. Exception = " + e);
		}

	}

	protected void setResponseHeader(HttpServletResponse response, String name)
			throws IOException {
		StringBuffer sbContentDispValue = new StringBuffer();
		response.setHeader("Pragma", "no-cache");
		response.setDateHeader("Expires", -1);
		//response.setHeader("Cache-Control", "no-cache");
		response.addHeader("Cache-Control", "max-age=0");
		response.addHeader("Cache-Control", "s-maxage=0");
		int firstIndex = name.lastIndexOf("/");
		int lastIndex = name.length();
		
		String reportName = "inline; filename=\""+name.substring( firstIndex+1, lastIndex - 4 )+".pdf\"";

		String ext = name.substring(name.length() - 3).toLowerCase();
		if (ext.equals("pdf")) {
			response.setContentType("application/pdf");
			//sbContentDispValue.append("inline; filename=");
			//sbContentDispValue.append(name);
			response.setHeader("Content-disposition", reportName);
		} else if (ext.equals("xml")) {
			response.setContentType("text/xml");
		} else if (ext.equals("xls")) {
			reportName = "attachment; filename=\""+name.substring( firstIndex+1, lastIndex - 4 )+".xls\"";
			response.setContentType("application/vnd.ms-excel");			
			response.setHeader("Content-disposition", reportName);
		} else if (ext.equals("jpg") || ext.equals("jpeg")) {
			response.setContentType("image/jpeg");
		} else if (ext.equals("tif") || ext.equals("tiff")) {
			response.setContentType("image/tiff");
		} else {
			response.setContentType("image/" + ext);
		}
	}
}