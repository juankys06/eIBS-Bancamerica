package datapro.eibs.reports;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.adobe.fdf.FDFDoc;

import datapro.eibs.beans.EFRM03001Message;
import datapro.eibs.beans.ELEERRMessage;
import datapro.eibs.beans.ESS0030DSMessage;
import datapro.eibs.beans.JBObjList;
import datapro.eibs.master.SuperServlet;
import datapro.eibs.sockets.MessageContext;
import datapro.eibs.sockets.MessageRecord;

public class JSEFRM030 extends JSEFRM000PDF {
	
	public void initDataStructure() {
	}
	
	private void printError(HttpServletRequest req, HttpServletResponse res, ELEERRMessage msgError) throws IOException {
		String err = msgError.getERNU01() + " - "
			+ msgError.getERDS01();
		res.setContentType("text/html");
		PrintWriter out = res.getWriter();
		out.println("<HTML>");
		out.println("<HEAD>");
		out.println("<TITLE>PDF Error</TITLE>");
		out.println("</HEAD>");
		out.println("<BODY>");
		out.println("PDF ERROR : " + err);
		out.println("</BODY>");
		out.println("</HTML>");
	}
	protected void procReqForm(HttpServletRequest req, HttpServletResponse res, HttpSession session) 
		throws ServletException, IOException {
		
		MessageContext mc = null;
		try {
			flexLog("Opennig Socket Connection");
			mc = new MessageContext(getMessageHandler("EFRM030"));
			
			EFRM03001Message msgHeader = (EFRM03001Message) 
				mc.getMessageRecord("EFRM03001");
			
			String user = req.getParameter("USERID");
			if (user == null && session != null) {
				ESS0030DSMessage msgUser = (ESS0030DSMessage)session.getAttribute("currUser");
				if (msgUser != null) {
					user = msgUser.getH01USR();
				}
			} 
			if(user != null){
				msgHeader.setH01USERID(user);				
			}
			
			String interfaceType = req.getParameter("INTERFACE");
			if (interfaceType == null) {
				interfaceType = "P";
			}
			msgHeader.setH01FLGWK1(interfaceType);
			msgHeader.setH01TIMSYS(SuperServlet.getTimeStamp());

			String id = req.getParameter("IDNUM");
			if (id != null) 
				msgHeader.setE01SELLN3(id);
			String customer = req.getParameter("CUSNUM");
			if (customer != null) 
				msgHeader.setE01SELCUN(customer);
			String account = req.getParameter("ACCOUNT");
			if (account != null) 
				msgHeader.setE01SELACC(account);
			
			msgHeader.send();
			msgHeader.destroy();
			
			MessageRecord newmessage = mc.receiveMessage();
			if (newmessage.getFormatName().equals("ELEERR")) {
				ELEERRMessage msgError = (ELEERRMessage) newmessage;
				printError(req, res, msgError);
			} else if (newmessage.getFormatName().equals("EFRM03001")){
				msgHeader = (EFRM03001Message)newmessage;

				boolean nothing = true;
				JBObjList beanList = new JBObjList();

				String marker = "";

				while (true) {
					msgHeader = (EFRM03001Message) newmessage;
					marker = msgHeader.getE01MORFRM();

					if (marker.equals("*")) {
						beanList.setShowNext(false);
						break;
					} else {
						if (nothing) nothing = false;
						beanList.addRow(msgHeader);
						if (marker.equals("+")) {
							beanList.setShowNext(true);
							break;
						}
					}
					newmessage = mc.receiveMessage();
				}
				if (!nothing) {
					FDFDoc outputFDF = new FDFDoc();
					//Receive Data
					boolean isError = false;
					while (true) {
						newmessage = mc.receiveMessage();
						String ddsName = newmessage.getFormatName();
						if(ddsName.equals("EFRM03001")){
							break;
						} else if (ddsName.equals("ELEERR")) {
							ELEERRMessage msgError = (ELEERRMessage) newmessage;
							isError = true;
							printError(req, res, msgError);
							break;
						} else {
							getFormData(outputFDF, "PrevensionLavadoDeActivos.", newmessage);
							break;
						}
					}
				
					if(!isError){
						// Output the document, use standard formatter
						String urlPDF = datapro.eibs.master.JSEIBSProp.getFORMPDFURL()
							+ msgHeader.getFieldString("E01APFPTH");
						sendPdf(req, res, urlPDF, outputFDF);						
					}
				}	

			}
			
		} catch (Exception e) {
			e.printStackTrace();
			flexLog("Error: " + e);
			throw new RuntimeException("Socket Communication Error");
		} finally {
			if(mc != null) mc.close();
		}
	}

	public void service(HttpServletRequest req, HttpServletResponse res)
			throws ServletException, IOException {
		try {
			HttpSession session = req.getSession(false);
			procReqForm(req, res, session);
			
		} catch (Exception e) {
			e.printStackTrace();
			flexLog("Error: " + e);
			LangPath = SuperServlet.rootPath + "s/";
			res.sendRedirect(SuperServlet.srctx + LangPath
					+ SuperServlet.sckNotOpenPage);
		} 

	}

}