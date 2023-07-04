/*
 * Created on Jan 6, 2009
 *
 * To change the template for this generated file go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
package datapro.eibs.tools;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.datapro.eibs.exception.FacadeException;
import com.datapro.eibs.images.facade.FACheckReader;
import com.datapro.eibs.images.facade.FAProcess;
import com.datapro.eibs.images.vo.ATVCHECKView;
import com.datapro.generic.beanutil.BeanList;

import datapro.eibs.beans.EDD093104Message;
import datapro.eibs.beans.ELEERRMessage;
import datapro.eibs.master.MessageProcessor;
import datapro.eibs.master.SuperServlet;
import datapro.eibs.master.Util;

/**
 * @author erodriguez
 *
 * To change the template for this generated type comment go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
public class JSCheckReaderDone extends SuperServlet {

	/* (non-Javadoc)
	 * @see datapro.eibs.master.JSEIBSServlet#processRequest(datapro.eibs.beans.ESS0030DSMessage, javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse, javax.servlet.http.HttpSession, int)
	 */

	public void service(HttpServletRequest req, HttpServletResponse res)
		throws ServletException, IOException {

		try {
			int screen ;
			try {
				screen = Integer.parseInt(req.getParameter("SCREEN"));
			} catch (Exception e) {
				screen = 9;
			}					
			processRequest(req, res, screen);
		} catch (Exception e) {
			flexLog("Error: " + e);
		}
	}

	protected void processRequest(
		HttpServletRequest req,
		HttpServletResponse res,
		int screen) throws ServletException, IOException {

			final int A_CHECK_LIST			= 2;
			final int A_EXIT_LIST			= 9;
			final int A_TEST_LIST			= 3;

			switch (screen) {
				case A_CHECK_LIST :
					procActCheckList(req, res);
					break;
				case A_EXIT_LIST :
					procActExitList(req, res);
					break;
				case A_TEST_LIST :
					procActTestList(req, res);
					break;
				default :
					callPage("MISC_not_available.jsp", req, res);
					break;
				}
	}
	
	private void response(HttpServletResponse res, String message) throws IOException {
		res.setHeader( "Cache-Control", "must-revalidate, post-check=0, pre-check=0" ); 
		res.setContentLength(message.length());
		res.setHeader( "Pragma", "public" ); 
		res.setContentType("eibs/crt");
		res.getWriter().print(message.toCharArray());
	}

	private void procActTestList(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		String scandate = req.getParameter("SCANDATE").trim();
		String doctypecode = req.getParameter("DOCTYPECODE");
		String uid = req.getParameter("PROCESS") == null ? "" : req.getParameter("PROCESS").trim();
		
		FAProcess process = new FAProcess();
		FACheckReader facade = new FACheckReader();
		try {
			BeanList list = facade.getCheckList(scandate, doctypecode);
			if (!list.isEmpty()) {
				list.initRow();
				int index = 0;
				while (list.getNextRow()) {
					ATVCHECKView bean = (ATVCHECKView) list.getRecord();
					index++;
				}
				process.setResult(uid, String.valueOf(index));
				if (!uid.equals("")) process.stop(uid);				
				String message = "Database Test Successful. Records Processed: " + index;
				response(res, message);
			}	
		} catch (Exception e) {
			res.sendError(99, e.getMessage());
			try {
				process.kill(uid);
			} catch (FacadeException e1) {
				flexLog("FacadeException ocurred. Exception = " + e1);
				throw new ServletException(e1);
			}
			String message = e.getClass().getName() + " Exception ocurred. Error = " + e.getMessage();
			response(res, message);
			flexLog(message);
		}
	
}

	private void procActExitList(HttpServletRequest req, HttpServletResponse res) throws ServletException {
		String uid = req.getParameter("PROCESS").trim();
		FAProcess process = new FAProcess();
		try {
			process.stop(uid);
			String message = "Process stopped. Application Terminate";
			response(res, message);
		} catch (Exception e) {
			flexLog(e.getClass().getName() + " Exception ocurred. Error = " + e);
			throw new ServletException(e);
		}
	}

	private String fixAmount(String amount) {
		int size = amount.trim().indexOf(".") + 3;
		if (amount.length() > size) {
			return amount.trim().substring(0, size);
		} else {
			return amount;
		}
	}

	private void procActCheckList(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		boolean error = false;
		
		String scandate = req.getParameter("SCANDATE").trim();
		String doctypecode = req.getParameter("DOCTYPECODE");
		String user = req.getParameter("USERID").trim(); 
		String uid = req.getParameter("PROCESS").trim();
		
		FACheckReader facade = new FACheckReader();
		FAProcess process = new FAProcess();
		
		MessageProcessor mp = null;
		try {
			ELEERRMessage msgError = new ELEERRMessage();
			mp = new MessageProcessor(getMessageHandler("EDD0931"));
			BeanList list = facade.getCheckList(scandate, doctypecode);
			if (!list.isEmpty()) {
				list.initRow();
				int index = 0;
				while (list.getNextRow()) {
					ATVCHECKView bean = (ATVCHECKView) list.getRecord();
					
					EDD093104Message msg = (EDD093104Message) mp.getMessageRecord("EDD093104", user, "");
					msg.setH04FLGWK1("");
					String amount = fixAmount(bean.getCHECKAMOUNT().toString().trim());
					String record = Util.addRightChar(' ', 4, bean.getPRESENTERBANK().trim()) + 
					 				bean.getCODEBRANCH().trim() +
					 				bean.getCCYCODE().trim() + 
									Util.addLeftChar('0', 12, bean.getABANUMBER().trim()) +
									Util.addLeftChar('0', 20, bean.getACCOUNT().trim()) +
									Util.addLeftChar('0', 16, bean.getCHECKNUM().trim()) +
									Util.addLeftChar('0', 17, Util.takeDot(amount)) +
									bean.getCHECKTYPE().trim() + 
									Util.addLeftChar('0', 8, String.valueOf(index++));
					msg.setE04DATA(record);
					mp.sendMessage(msg);
					msgError = (ELEERRMessage) mp.receiveMessageRecord("ELEERR");
					error = mp.hasError(msgError);
					if (error) {
						int code = Integer.parseInt(msgError.getERNU01());
						process.error(uid);
						process.setResult(uid, code + ": " +  msgError.getERDS01());
						//res.sendError(res.SC_CONFLICT, "Error Code " + code + ": " + msgError.getERDS01());
						String message = "Error Code " + code + ": " + msgError.getERDS01();
						response(res, message);
						flexLog(message);
						break;
					}

					//Thread.sleep(000);
					facade.setReadStatus(bean.getSCANDATE(), bean.getDOCTYPECODE(), bean.getSEQUENCEITEM(), bean.getCHECKNUM());
					process.setResult(uid, String.valueOf(index));
				}
				if (!error) {

					EDD093104Message msg = (EDD093104Message) mp.getMessageRecord("EDD093104", user, "");
					msg.setH04FLGWK1("9");
					msg.setE04DATA("");
					mp.sendMessage(msg);
					msgError = (ELEERRMessage) mp.receiveMessageRecord("ELEERR");
					error = mp.hasError(msgError);
					if (error) {
						int code = Integer.parseInt(msgError.getERRNUM());
						//res.sendError(code, msgError.getERDS01());
						String message = "Error Code " + code + ": " + msgError.getERDS01();
						response(res, message);
						flexLog(message);
					}
					if (!uid.equals("")) process.stop(uid);					
				}
			} else {
				
				EDD093104Message msg = (EDD093104Message) mp.getMessageRecord("EDD093104", user, "");
				msg.setH04FLGWK1("E");
				msg.setE04DATA("");
				mp.sendMessage(msg);
				msgError = (ELEERRMessage) mp.receiveMessageRecord("ELEERR");
				error = mp.hasError(msgError);
				if (error) {
					int code = Integer.parseInt(msgError.getERRNUM());
					//res.sendError(code, msgError.getERDS01());
					String message = "Error Code " + code + ": " + msgError.getERDS01();
					response(res, message);
					flexLog(message);
				}
				if (!uid.equals("")) process.stop(uid);				
			}
			
		} catch (Exception e) {
			try {
				process.kill(uid);
			} catch (FacadeException e1) {
				flexLog("FacadeException ocurred. Exception = " + e1);
				throw new ServletException(e);
			}
			flexLog(e.getClass().getName() + " Exception ocurred. Error = " + e);
			throw new ServletException(e);
		} finally {
			if (mp != null)	mp.close();
		}
	}

	
}
