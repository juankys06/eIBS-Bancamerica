/*
 * Created on Jan 4, 2011
 *
 * To change the template for this generated file go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
package datapro.eibs.tools;

import java.io.BufferedOutputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.datapro.eibs.exception.ItemNotFoundException;
import com.datapro.eibs.images.facade.FAImage;
import com.datapro.eibs.images.vo.ATVCHECKViewImage;

import datapro.eibs.beans.ESS0030DSMessage;
import datapro.eibs.master.JSEIBSServlet;

/**
 * @author erodriguez
 *
 * To change the template for this generated type comment go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
public class JSScanCheckView extends JSEIBSServlet {

	/* (non-Javadoc)
	 * @see datapro.eibs.master.JSEIBSServlet#processRequest(datapro.eibs.beans.ESS0030DSMessage, javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse, javax.servlet.http.HttpSession, int)
	 */
	protected void processRequest(
		ESS0030DSMessage user,
		HttpServletRequest req,
		HttpServletResponse res,
		HttpSession session,
		int screen)
		throws ServletException, IOException {
			
			main(user, req, res, session, screen);
	}

	private long copy(InputStream source, OutputStream destination) throws IOException {
		byte[] buf = new byte[4096];
		int numRead;
		int numberOfBytesCopied = 0;
		while(-1!= (numRead = source.read(buf))) {
			destination.write(buf, 0, numRead);
			destination.flush();
			numberOfBytesCopied+=numRead;
		}		
		return numberOfBytesCopied;
	}
		
	/**
	 * @param user
	 * @param req
	 * @param res
	 * @param session
	 */
	private void main(ESS0030DSMessage user, HttpServletRequest req, HttpServletResponse res, HttpSession session, int option) throws ServletException {
		String account = 
			req.getParameter("ACCOUNT") == null ? "0" : req.getParameter("ACCOUNT").trim();
		
		String serie = 
			req.getParameter("SERIE") == null ? "0" : req.getParameter("SERIE").trim();
		
		boolean back = (option == 2);
			
		byte buffer[] = null;
		BufferedOutputStream output = null;
		
		FAImage facade = new FAImage();
		try {
			ATVCHECKViewImage vo;
			
			try {
				vo = facade.getCheckImage(account, serie);
				if (back) {
					buffer = vo.getIMAGEBACK();
				} else {
					buffer = vo.getIMAGEFRONT();
				}
				res.reset();
				res.setContentType("image/jpeg");
				int size = buffer.length;
				res.setContentLength(size);
				try {
					output = new BufferedOutputStream(res.getOutputStream());
					output.write(buffer);
					output.flush();
				} finally {
					output.close();
				}
			} catch (ItemNotFoundException e1) {
				File file = new File(getServletContext().getRealPath("/") + "/images",  "check_not_found.jpg");
				InputStream inImage = new FileInputStream(file);
				ByteArrayOutputStream out = new ByteArrayOutputStream();
				copy(inImage, out);
				buffer = out.toByteArray();						
				res.reset();
				res.setContentType("image/jpeg");
				int size = buffer.length;
				res.setContentLength(size);
				try {
					output = new BufferedOutputStream(res.getOutputStream());
					output.write(buffer);
					output.flush();
				} finally {
					output.close();
				}
			}
			
			
		} catch (Exception e) {
			e.printStackTrace();
			flexLog(e.getClass().getName() + " ocurred. Exception = " + e);
			throw new ServletException(e);
		}	
	}

}
