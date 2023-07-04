/*
 * Created on Jan 6, 2009
 *
 * To change the template for this generated file go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
package datapro.eibs.tools;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.math.BigDecimal;
import java.net.Socket;
import java.net.URL;
import java.net.URLConnection;

import java.sql.Blob;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Calendar;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import com.datapro.eibs.exception.FacadeException;
import com.datapro.eibs.images.facade.FAImage;
import com.datapro.eibs.images.vo.SCNDOCIMG;
import com.datapro.eibs.images.vo.SCNDOCTBL;
import com.datapro.generic.beanutil.DynamicForm;
import com.datapro.generic.tool.Util;
import com.datapro.services.ServiceLocator;
import org.xml.sax.SAXException;

import datapro.eibs.beans.EDI001001Message;
import datapro.eibs.beans.ELEERRMessage;
import datapro.eibs.beans.ESS0030DSMessage;
import datapro.eibs.beans.JBImage;
import datapro.eibs.beans.UserPos;
import datapro.eibs.master.JSEIBSProp;
import datapro.eibs.master.MessageProcessor;
import datapro.eibs.master.SuperServlet;
import datapro.eibs.sockets.MessageContext;

/**
 * @author erodriguez
 *
 * To change the template for this generated type comment go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
public class JSScanDocDone extends SuperServlet {

	/* (non-Javadoc)
	 * @see datapro.eibs.master.JSEIBSServlet#processRequest(datapro.eibs.beans.ESS0030DSMessage, javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse, javax.servlet.http.HttpSession, int)
	 */
	private String LangPath = "s";

	public void service(HttpServletRequest req, HttpServletResponse res)
		throws ServletException, IOException {

		try {
			int screen ;
			try {
				screen = Integer.parseInt(req.getParameter("SCREEN"));
			} catch (Exception e) {
				screen = 1;
			}					
			processRequest(req, res, screen);
		} catch (Exception e) {
			flexLog("Error: " + e);
			redirectToPage(getLangPath(req)+super.sckNotOpenPage, res);
		}
	}

	protected String getLangPath(HttpServletRequest req) {
		return SuperServlet.rootPath + LangPath + "/";
	}

	private MessageContext getMessageContext(int port)
		throws IOException {

		Socket s = new Socket(super.hostIP, super.iniSocket + port);
		s.setSoTimeout(super.sckTimeOut);
		MessageContext mc =
			new MessageContext(
				new DataInputStream(
					new BufferedInputStream(s.getInputStream())),
				new DataOutputStream(
					new BufferedOutputStream(s.getOutputStream())),
				"datapro.eibs.beans");
		return mc;
	}

	
	protected void forward(String pageName,
		HttpServletRequest req, HttpServletResponse res)
		throws ServletException, IOException {
		callPage(getLangPath(req) + pageName, req, res);
	}
	
	protected void processRequest(
		HttpServletRequest req,
		HttpServletResponse res,
		int screen) throws ServletException, IOException {

			final int A_SCAN_AS400 = 1;
			final int A_SCAN_IMAGE = 2;
			final int A_SCAN_DIMST = 3;

			switch (screen) {
				case A_SCAN_AS400 :
					procInsertAS400(req, res);
					break;
				case A_SCAN_IMAGE :
					procInsertImage(req, res);
					break;
				case A_SCAN_DIMST :
					procInsertDIMST(req, res);
					break;
				default :
					forward("MISC_not_available.jsp", req, res);
					break;
				}
	}

	/**
	 * @param req
	 * @param res
	 */
	private void procInsertDIMST(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		MessageProcessor mp = null;

		try {
			JBImage bImage = new JBImage();
			DynamicForm form = new DynamicForm();
			form.setBeanFromPage(req, bImage);
	
			mp = new MessageProcessor(getMessageContext(1));
			EDI001001Message msg = (EDI001001Message) mp.getMessageRecord("EDI001001", bImage.getUSERID(), "");
			msg.setH01SCRCOD("01");
			msg.setH01FLGWK1("1");
			msg.setH01FLGWK2(bImage.getTYPE());
			msg.setE01DCIADD(bImage.getADD());
			msg.setE01DCIACC(bImage.getNUMBER());
			msg.setE01DCITNU(bImage.getTNU());
			msg.setE01DCISEQ(bImage.getSEQ());
			msg.setE01DCITYP(bImage.getTYP());
			msg.setE01DCIDES(bImage.getDES().replace('^', ' '));
			msg.setE01DCIFRE(bImage.getFRE());
			msg.setE01DCIPAG(bImage.getPAGES());
			msg.setE01DCISTA("CO");
			mp.sendMessage(msg);
			ELEERRMessage msgError = (ELEERRMessage) mp.receiveMessageRecord();
			if (mp.hasError(msgError)) {
				flexLog("msgError" + "*" + msgError + "*");
			}
		
		} catch (Exception e) {
			e.printStackTrace();
			flexLog("FacadeException ocurred. Exception = " + e);
			throw new ServletException(e);
		} finally {
			if (mp != null)	mp.close();
		}
		
	}

	/**
	 * @param user
	 * @param req
	 * @param res
	 * @param session
	 */
	private void procInsertAS400(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		MessageProcessor mp = null;

		try {
			JBImage bImage = new JBImage();
			DynamicForm form = new DynamicForm();
			form.setBeanFromPage(req, bImage);
	
			mp = new MessageProcessor(getMessageContext(1));
			EDI001001Message msg = (EDI001001Message) mp.getMessageRecord("EDI001001", bImage.getUSERID(), "");
			msg.setH01SCRCOD("01");
			msg.setH01FLGWK1("1");
			msg.setH01FLGWK2(bImage.getTYPE());
			msg.setE01DCIADD(bImage.getADD());
			msg.setE01DCIACC(bImage.getNUMBER());
			msg.setE01DCITNU(bImage.getTNU());
			msg.setE01DCISEQ(bImage.getSEQ());
			msg.setE01DCITYP(bImage.getTYP());
			msg.setE01DCIDES(bImage.getDES().replace('^', ' '));
			msg.setE01DCIFRE(bImage.getFRE());
			msg.setE01DCIPAG(bImage.getPAGES());
			msg.setE01DCISTA("CO");
			mp.sendMessage(msg);
			ELEERRMessage msgError = (ELEERRMessage) mp.receiveMessageRecord();
			if (mp.hasError(msgError)) {
				flexLog("msgError" + "*" + msgError + "*");
			} else {
				if (JSEIBSProp.getImageRemote()) {
					procInsertImageRemote(req, res);
				} else {	
					if (!JSEIBSProp.getImgToIFS()) {	
						procInsertImageFTP(req, res);
					}
				}
			}
		
		} catch (Exception e) {
			e.printStackTrace();
			flexLog("FacadeException ocurred. Exception = " + e);
			throw new ServletException(e);
		} finally {
			if (mp != null)	mp.close();
		}
		
	}

	/**
	 * @param user
	 * @param req
	 * @param res
	 * @param session
	 */
	private void procInsertImage(HttpServletRequest req, HttpServletResponse res) throws ServletException {

		SCNDOCTBL tbl = new SCNDOCTBL();
		SCNDOCIMG img = new SCNDOCIMG();
		
		try {
			JBImage bImage = new JBImage();
			DynamicForm form = new DynamicForm();
			form.setBeanFromPage(req, bImage);
			
			// Send Image Binary
			//byte[] isImage = req.getParameter("IMAGE").getBytes();
			// Image Size
			int size = Integer.parseInt(bImage.getSIZE());
			byte[] isImage = new byte[size];
			req.getInputStream().read(isImage);
			img.setIMGBIN(isImage);
			// Image Lenght
			img.setIMGLEN(isImage.length);
			
			if (JSEIBSProp.getImgToIFS()) {
				FileOutputStream oImage = null;
				File fImage = new File(JSEIBSProp.getImgTempPath(), bImage.getFILENAME());
				oImage = new FileOutputStream(fImage);
				oImage.write(isImage);
			} else {
				tbl.setTBLTYP(bImage.getTYPE());
				tbl.setTBLNUM(bImage.getNUMBER());
				tbl.setTBLTBN(bImage.getTNU());
				tbl.setTBLSEQ(bImage.getSEQ());
				tbl.setTBLSSQ(bImage.getSEQNUM());
				tbl.setTBLPAG(bImage.getPAGE());
				tbl.setTBLDTY(bImage.getTYP());
				tbl.setTBLDSC(bImage.getDES());
				// UID
				BigDecimal uid = null;
				try {
					uid = new BigDecimal(Util.getNewReference().substring(0, 15));
				} catch (Exception e) {
					uid = new BigDecimal(0);
				}
				tbl.setTBLUID(uid);
				img.setIMGUID(uid);
				img.setIMGBIN(isImage);
				// Image Lenght
				img.setIMGLEN(isImage.length);
				FAImage facade = new FAImage();
				facade.insertImage(tbl, img);
			}
		} catch (Exception e) {
			e.printStackTrace();
			flexLog("FacadeException ocurred. Exception = " + e);
			throw new ServletException(e);
		}
	}
	
	private void procInsertImageFTP(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {

		FAImage facade = new FAImage();
		
		// Description
		String imgFileName = "";
		try {
			imgFileName = req.getParameter("ADD");
			if (imgFileName == null) imgFileName = "";
		} catch (Exception e) {
			imgFileName = "";
		} 
		String xmlFile = JSEIBSProp.getScanDataURL() + imgFileName + ".xml";
		try {
			DocumentBuilderFactory builderFactory = DocumentBuilderFactory.newInstance();
			DocumentBuilder parser = builderFactory.newDocumentBuilder();
			
			org.w3c.dom.Document doc = parser.parse(new File(xmlFile));
			org.w3c.dom.Node docPages = doc.getFirstChild();
			org.w3c.dom.NodeList list = docPages.getChildNodes();
			for (int i = 0; i < list.getLength(); i++) {
				org.w3c.dom.Node node = list.item(i);
				if (node.getNodeName().equals("Page")) {
					org.w3c.dom.NamedNodeMap map = node.getAttributes();
					org.w3c.dom.Node item = null;
					
					SCNDOCTBL tbl = new SCNDOCTBL();
					SCNDOCIMG img = new SCNDOCIMG();

					JBImage bImage = new JBImage();
					DynamicForm form = new DynamicForm();
					form.setBeanFromPage(req, bImage);

					// Type
					tbl.setTBLTYP(bImage.getTYPE());
					// Number
					tbl.setTBLNUM(bImage.getNUMBER());
					// Table Number
					tbl.setTBLTBN(bImage.getTNU());
					// Table sequence
					tbl.setTBLSEQ(bImage.getSEQ());
					// Sequence
					BigDecimal ssq = null;
					try {
						ssq = new BigDecimal(map.getNamedItem("Seq").getNodeValue());
					} catch (Exception e) {
						ssq = new BigDecimal(0);
					}
					tbl.setTBLSSQ(ssq);
					// Page Number
					BigDecimal pag = new BigDecimal(i + 1);
					tbl.setTBLPAG(pag);
					// Doc Type
					tbl.setTBLDTY(bImage.getTYP());
					//	Description
					try {
						imgFileName = map.getNamedItem("Name").getNodeValue();
						if (imgFileName == null) imgFileName = "";
					} catch (Exception e) {
						imgFileName = "";
					} 
					tbl.setTBLDSC(imgFileName);
					// UID
					BigDecimal uid = null;
					try {
						uid = new BigDecimal(Util.getNewReference().substring(0, 15));
					} catch (Exception e) {
						uid = new BigDecimal(0);
					}
					tbl.setTBLUID(uid);
					img.setIMGUID(uid);
					// Image File Name
					FileInputStream inImage = null;
					File fImage = new File(JSEIBSProp.getImgTempPath() + imgFileName);
					inImage = new FileInputStream(fImage);
					int lenImage = (int) fImage.length();
					byte[] isImage = new byte[lenImage];
					inImage.read(isImage);
					img.setIMGBIN(isImage);
					// Image Lenght
					img.setIMGLEN(lenImage);
					
					facade.insertImage(tbl, img);
					fImage.delete();
				}
			}
			File fXML = new File(xmlFile);
			fXML.delete();
					
		} catch (SAXException e) {
			e.printStackTrace();
			flexLog("SAXException ocurred. Exception = " + e);
			throw new ServletException(e);
		} catch (FacadeException e) {
			e.printStackTrace();
			flexLog("FacadeException ocurred. Exception = " + e);
			throw new ServletException(e);
		} catch (Exception e) {
			e.printStackTrace();
			flexLog("FacadeException ocurred. Exception = " + e);
			throw new ServletException(e);
		}

	}
	
	private void procInsertImageRemote(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {

		try {
			JBImage bImage = new JBImage();
			DynamicForm form = new DynamicForm();
			form.setBeanFromPage(req, bImage);

			StringBuffer data = new StringBuffer();
			data.append(	  "USERID" + "=" + bImage.getUSERID());
			data.append("&" + "NUMBER" + "=" + bImage.getNUMBER());
			data.append("&" + "TYPE"   + "=" + bImage.getTYPE());
			data.append("&" + "TNU"    + "=" + bImage.getTNU());
			data.append("&" + "SEQ"    + "=" + bImage.getSEQ());
			data.append("&" + "TYP"    + "=" + bImage.getTYP());
			data.append("&" + "ADD"    + "=" + bImage.getADD());
			
			// Send data
			URL url = new URL(JSEIBSProp.getScanURL());
			URLConnection conn = url.openConnection();
			conn.setDoOutput(true);
			OutputStreamWriter wr = new OutputStreamWriter(conn.getOutputStream());
			wr.write(data.toString());
			wr.flush();
		    
			// Get the response
			BufferedReader rd = new BufferedReader(new InputStreamReader(conn.getInputStream()));
			String line;
			StringBuffer response = new StringBuffer();
			while ((line = rd.readLine()) != null) {
				response.append(line);
			}
			wr.close();
			rd.close();
		} catch (Exception e) {
			flexLog("Error: " + e);
			throw new RuntimeException("HTTP Communication Error");
		}
	}
	
}
