package datapro.eibs.tools;

/**
 * Insert the type's description here.
 * Creation date: (9/17/2001 1:57:32 PM)
 * @author: Ramses Amaro
 */

import javax.servlet.http.*;
import datapro.eibs.beans.*;
import datapro.eibs.master.JSEIBSProp;
import datapro.eibs.master.Util;
 
public class JSCheckImage extends datapro.eibs.master.SuperServlet{
/**
 * JSCheckImage constructor comment.
 */

protected static final int I_ERAS 				= 1;
protected static final int I_CHECK21			= 2;
protected String LangPath = "S";

public JSCheckImage() {
	super();
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
			
			datapro.eibs.beans.ESS0030DSMessage msgUser = (datapro.eibs.beans.ESS0030DSMessage) session.getAttribute("currUser");
			LangPath = super.rootPath + msgUser.getE01LAN() + "/";
			String bankID = JSEIBSProp.getICheckBankID(); 
			String iChkUrl = JSEIBSProp.getICheckURL();
			int iChk = JSEIBSProp.getICheck();
			
			JBListRec beanList = null;
			DataCheckReject dataCR = null;
			
			String TDATE = "";
			String DATEM = "00";
			String DATED = "00";
			String DATEY = "0000";
			String ACCT = "0";
			String AMT = "0";
			String CKN = "0";
			String SIDE = "f";
			
			try {			
				beanList = (JBListRec) session.getAttribute("chkList");
				dataCR =   (DataCheckReject) session.getAttribute("dataCR");
			}
			catch (Exception e) {
				beanList = null;
			}
			
			
			try {
				CKN = req.getParameter("CKN");
			}catch (Exception e) {
				CKN = "";
			}
			try {
				if (req.getParameter("SIDE") != null) SIDE = req.getParameter("SIDE");
				if (!(SIDE.length() == 1)) SIDE= "f";
				
				if (beanList != null && dataCR != null && (CKN == null || CKN.equals(""))) {
					int row = Integer.parseInt(req.getParameter("ROW"));
					beanList.setCurrentRow(row);			
					DATEM = Util.justifyRight(beanList.getRecord(19).trim(),2);
					DATED = Util.justifyRight(beanList.getRecord(20).trim(),2);
					DATEY = Util.formatYear(beanList.getRecord(21).trim());
					//ACCT =beanList.getRecord(0);
					//CKN =beanList.getRecord(2);
					AMT =beanList.getRecord(3).trim();
					// Format of DEVIMG field: 'ACC: 999999999 CHK: 9999999999'
					try {
						ACCT = beanList.getRecord(27).substring(5,14).trim();
					} catch (Exception e) {
						ACCT = "";
					}
					try {
						CKN = beanList.getRecord(27).substring(20,30).trim();
					} catch (Exception e) {
						CKN = "";
					}					
				} else {
					DATEM = Util.justifyRight(req.getParameter("DATEM"),2);
					DATED = Util.justifyRight(req.getParameter("DATED"),2);
					DATEY = Util.formatYear(req.getParameter("DATEY"));
					ACCT = req.getParameter("ACC");
					AMT = req.getParameter("AMT");
					CKN = req.getParameter("CKN");
				}
			}
			catch (Exception e) {
			}
			
			
			String imgPath ="";
			switch (iChk)	{
				case I_ERAS :
						TDATE = DATEM + DATED + DATEY;
						AMT = Util.takeDot(AMT);
						imgPath =iChkUrl +"?<TDATE>"+TDATE+"<ACCT>"+ACCT+"<AMT>"+AMT+"<CKN>"+CKN+"<SIDE>"+SIDE;
						break;
				case I_CHECK21 :
						TDATE = DATEM + "/" + DATED + "/" + DATEY;
						AMT = Util.takeComa(AMT);
						if (SIDE.toUpperCase().equals("R")) SIDE="B";
						imgPath = iChkUrl + "?AC="+ACCT+"&AM="+AMT+"&DT="+TDATE+"&CH="+CKN+"&RQ="+SIDE.toUpperCase()+"&ID="+bankID;						
						break;
				default :
						//res.sendRedirect(super.srctx + LangPath + super.devPage);
						break;
			}
			session.setAttribute("imgPath",imgPath);
			flexLog(super.srctx + LangPath + "check_viewer_img.jsp");
			callPage(LangPath + "check_viewer_img.jsp",req,res);
		}
		catch (Exception e)
		{
		e.printStackTrace();
		flexLog("Error: " + e);
	  	throw new RuntimeException("Socket Communication Error");
		}
			
		
	}
		
}
}
