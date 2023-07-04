package datapro.eibs.tools;

/**
 * Insert the type's description here.
 * Creation date: (7/19/00 6:55:55 PM)
 * @author: Enrique Almonte
 */
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Vector;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.datapro.exception.ServiceLocatorException;
import com.datapro.services.ServiceLocator;

import datapro.eibs.beans.ELEERRMessage;
import datapro.eibs.beans.ESS0030DSMessage;
import datapro.eibs.beans.JBList;
import datapro.eibs.beans.JBObjList;
import datapro.eibs.generic.JBParseTree;
import datapro.eibs.master.JSEIBSMSGProp;
import datapro.eibs.master.JSEIBSProp;
import datapro.eibs.master.Util;

public class JSEODPDF extends datapro.eibs.master.SuperServlet {

	boolean isDataSource = false;
	
	// CIF options

	static final int R_INQ_GROUP = 1;
	static final int R_INQ_USERS = 2;
	static final int R_INQ_USERS_OLD = 7;
	static final int R_INQ_REPXUSER = 5;
	static final int R_INQ_REPSEARCH = 3;
	static final int A_DEL_REPXUSER = 4;
	static final int A_MOD_REPXUSER = 6;
	static final int A_NEW_REPXUSER = 8;
	static final int R_INQ_REPORTS = 9;
	static final int R_INQ_NEW_REPXUSER = 10;
	static final int R_INQ_REPORTSDB = 11;

	private String LangPath = "S";
	private String Lang = "S";

	private static final int records = 50;
	
	/**
	 * JSEODPDF constructor comment.
	 */
	public JSEODPDF() {
		super();
	}
	/**
	 * This method was created by Orestes Garcia.
	 */
	public void destroy() {

	}
	/**
	 * This method was created by Orestes Garcia.
	 */
	public void init(ServletConfig config) throws ServletException {
		super.init(config);
		isDataSource = JSEIBSProp.getEODDataSource();
	}
	/**
	 * This method was created by Orestes Garcia.
	 */
	public void service(HttpServletRequest req, HttpServletResponse res)
		throws ServletException, IOException {

		ESS0030DSMessage msgUser = null;
		HttpSession session = null;

		session = (HttpSession) req.getSession(false);

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

				msgUser =
					(datapro.eibs.beans.ESS0030DSMessage) session.getAttribute(
						"currUser");

				// Here we should get the path from the user profile
				LangPath = super.rootPath + msgUser.getE01LAN() + "/";
				//*------> check value of Lang variable , must be E or S
				Lang = msgUser.getE01LAN().toUpperCase();
				//
				int screen = R_INQ_GROUP;
				try {
					screen = Integer.parseInt(req.getParameter("SCREEN"));
				} catch (Exception e) {
					flexLog("Screen set to default value");
				}

				switch (screen) {
					case R_INQ_USERS_OLD :
						procReqUsers(msgUser, req, res, session);
						break;
					case R_INQ_GROUP :
						int index = 0; 
					    try {
							index = Integer.parseInt(req.getParameter("FromRecord")); 
					    } catch (Exception e) {
					    	index = 0;
					    }
						procReqGroups(req, res, session, index); 
						break;
					case R_INQ_USERS :
						String group = req.getParameter("usercode");
						procReqUsers(req, res, session, group);
						break;
					case R_INQ_REPXUSER :
						// TODO true or false depends on version 
						if (true) {
							procReqReportsxUserDB(msgUser, req, res, session);
						} else {
							procReqReportsxUserFS(msgUser, req, res, session);
						}
						break;
					case R_INQ_REPORTSDB :
						procReqReportsxUser(msgUser, req, res, session);
						break;
						
					case R_INQ_NEW_REPXUSER :
						procReqReportsxUserFS(msgUser, req, res, session);
						break;
					case R_INQ_REPORTS :
						procReqReportsAll(msgUser, req, res, session);
						break;
					case R_INQ_REPSEARCH :
						procReqReportsSearch(msgUser, req, res, session);
						break;
					case A_DEL_REPXUSER :
					case A_MOD_REPXUSER :
					case A_NEW_REPXUSER :
						procActMantData(msgUser, screen, req, res, session);
						break;

					default :
						res.sendRedirect(
							super.srctx + LangPath + super.devPage);
						break;
				}

			} catch (Exception e) {
				flexLog("Error: " + e);
				res.sendRedirect(
					super.srctx + LangPath + super.sckNotRespondPage);
			}
		}

	}

	/**
	 * This method was created by Enrique Almonte.
	 */
	protected void  procReqUsers(
		ESS0030DSMessage user,
		HttpServletRequest req,
		HttpServletResponse res,
		HttpSession ses)
		throws ServletException, IOException {

		String thisLink = "";
		String thisLinkFolder = "";
		String firstLink = "";
		String item = "";
		String folder = "";
		String imageUrl = "";
		boolean firstTime = true;
		Connection cnx = null;
		try {
			if (isDataSource) {
				cnx = ServiceLocator.getInstance().getDBConn("eibs-server");
			} else {
				cnx = ServiceLocator.getInstance().newJDBCCon("eibs-server");
			}
//			Context ctx = new InitialContext();
//			if (ctx == null)
//				throw new Exception("No Context");
//
//			DataSource ds = (DataSource) ctx.lookup("java:comp/env/jdbc/dsr04");
//
//			if (ds != null)
//				cnx = ds.getConnection();
			Statement st = cnx.createStatement();
			ResultSet rs =
				st.executeQuery(
					"SELECT CASE WHEN TRIM(bthf03) = '' THEN 'ITEMFOLDER' " +
					"		     ELSE bthf03 " +
					"		END as ITEMFOLDER, " +
					"		bthkey " +
					"FROM cntrlbth " +
					"ORDER BY bthf03, bthkey"
				);
			// Receiving
			flexLog("About to receive data");
			imageUrl = getServerRoot(req) + super.webAppPath + "/images/";
			//Language : E = Users , S = Usuarios
			//Root Folder Title
			String titleFolder = "";
			String titleDescription = "";

			JSEIBSMSGProp.setPropertyFileLang(Lang);
			titleFolder = JSEIBSMSGProp.getMSG0002();
			titleDescription = JSEIBSMSGProp.getMSG0003();

			//create tree structure
			JBParseTree dataTree = new JBParseTree();
			dataTree.init();
			dataTree.setRootFont("Dialog", "bold", "12", "0D23B5");
			dataTree.setFolderFont("Dialog", "plain", "10", "000033");
			dataTree.setItemFont("Small", "plain", "10", "000033");
			dataTree.setImageUrl(imageUrl);

			dataTree.setRootImage("cube.gif", "cubeover.gif");
			dataTree.setFolderImage("cone.gif", "coneover.gif");
			dataTree.setItemImage("ball.gif", "ballover.gif");

			dataTree.setRootTitle(titleFolder, titleDescription);
			dataTree.setTargetLink("detail");

			while (rs.next()) {
				// link
				thisLink =
					getServerRoot(req)
						+ super.webAppPath
						+ "/servlet/datapro.eibs.tools.JSEODPDF?usercode="
						+ rs.getString(2).trim()
						+ "&SCREEN=10";
				item = rs.getString(2).trim();
				folder =
					(rs.getString(1).trim().equals("ITEMFOLDER")
						? ""
						: rs.getString(1).trim());
				if (!folder.trim().equals("")) {
					thisLinkFolder =
						getServerRoot(req)
							+ super.webAppPath
							+ "/servlet/datapro.eibs.tools.JSEODPDF?usercode="
							+ rs.getString(1).trim()
							+ "&SCREEN=10";

				} else {
					thisLinkFolder = "";
				}
				if (firstTime) {
					firstTime = false;
					firstLink = thisLinkFolder;
				}
				dataTree.addRow(item, folder, thisLink, thisLinkFolder);
			}
			//close folder
			rs.close();
			//st.close();
			String outParams = dataTree.getTree();
			ses.setAttribute("offerParams", outParams);

			res.setContentType("text/html");
			res.setHeader("Pragma", "No-cache");
			res.setDateHeader("Expires", 0);
			res.setHeader("Cache-Control", "no-cache");
			PrintWriter out = res.getWriter();
			out.println("<!-- frames -->");
			out.println("<frameset  cols=\"28%,*\">");
			out.println(
				"<frame name=\"list\" src=\""
					+ super.webAppPath
					+ LangPath
					+ "EODPDF_users_tree_view.jsp\" marginwidth=\"10\" marginheight=\"10\" scrolling=\"no\" frameborder=\"NO\">");
			out.println(
				"<frame name=\"detail\" src=\""
					+ firstLink
					+ "\" marginwidth=\"10\" marginheight=\"10\" scrolling=\"auto\" frameborder=\"NO\">");
			out.println("</frameset>");
			out.close();
			//close 

		} catch (ServiceLocatorException e) {
			flexLog("Error: " + e);
			//change to page of sql error
			res.sendRedirect(super.srctx + LangPath + super.sckNotRespondPage);
		} catch (SQLException e) {
			flexLog("Error: " + e);
			//change to page of sql error
			res.sendRedirect(super.srctx + LangPath + super.sckNotRespondPage);
		} catch (Exception e) {
			flexLog("Error: " + e);
			//change to page of sql error
			res.sendRedirect(super.srctx + LangPath + super.sckNotRespondPage);

		} finally {
			try {
				cnx.close();
			} catch (SQLException e) {
				flexLog("Error: " + e);
			}
		}

	}
	
	protected void procReqUsers(HttpServletRequest req,
								HttpServletResponse res,
								HttpSession ses,
								String group) throws IOException, ServletException {
		String item = "<INPUT TYPE=RADIO  NAME=\"usercode\" CHECKED VALUE='" + group + "' onclick=\"goAction('Grupo ID')\"> " + group;	
		JBObjList beanList = null;
		try {
			beanList = getUsersList(group);
		} catch (ServiceLocatorException e) {
			flexLog("Error: " + e);
			res.sendRedirect(super.srctx + LangPath + super.sckNotRespondPage);
		} catch (SQLException e) {
			flexLog("Error: " + e);
			res.sendRedirect(super.srctx + LangPath + super.sckNotRespondPage);
		}
		flexLog("Putting java beans into the session");
		ses.setAttribute("groupID", item);
		ses.setAttribute("userList", beanList);
		try 
		{
			String PageToCall = LangPath + "EODPDF_users_reports_view.jsp";
			flexLog("About to call Page: " + PageToCall);
			callPage(PageToCall, req, res);	
		} catch (Exception e) {
			flexLog("Exception calling page " + e);
		}
	}
	
	protected void procReqGroups(HttpServletRequest req,
								 HttpServletResponse res,
								 HttpSession ses) throws IOException, ServletException {
		JBObjList beanList = null;
		try {
			beanList = getGroupList();
		} catch (ServiceLocatorException e) {
			flexLog("Error: " + e);
			res.sendRedirect(super.srctx + LangPath + super.sckNotRespondPage);
		} catch (SQLException e) {
			flexLog("Error: " + e);
			res.sendRedirect(super.srctx + LangPath + super.sckNotRespondPage);
		}
		flexLog("Putting java beans into the session");
		ses.setAttribute("groupList", beanList);
		try 
		{
			String PageToCall = LangPath + "EODPDF_groups_list_view.jsp";
			flexLog("About to call Page: " + PageToCall);
			callPage(PageToCall, req, res);	
		} catch (Exception e) {
			flexLog("Exception calling page " + e);
		}
	}
	
	protected void procReqGroups(HttpServletRequest req,
								 HttpServletResponse res,
								 HttpSession ses,
								 int FromRecord) throws IOException, ServletException {
		JBObjList beanList = null;
		try {
			beanList = getGroupList(FromRecord);
		} catch (ServiceLocatorException e) {
			flexLog("Error: " + e);
			res.sendRedirect(super.srctx + LangPath + super.sckNotRespondPage);
		} catch (SQLException e) {
			flexLog("Error: " + e);
			res.sendRedirect(super.srctx + LangPath + super.sckNotRespondPage);
		}
		flexLog("Putting java beans into the session");
		ses.setAttribute("groupList", beanList);
		try 
		{
			String PageToCall = LangPath + "EODPDF_groups_list_view.jsp";
			flexLog("About to call Page: " + PageToCall);
			callPage(PageToCall, req, res);	
		} catch (Exception e) {
			flexLog("Exception calling page " + e);
		}
	}
	
	/**
	 * This method was created by Eloy Rodriguez.
	 * return Groups List
	*/
	private JBObjList getGroupList() throws ServiceLocatorException, SQLException {
		JBObjList beanList = new JBObjList();
		Connection cnx = null;
		try {
			if (isDataSource) {
				cnx = ServiceLocator.getInstance().getDBConn("eibs-server");
			} else {
				cnx = ServiceLocator.getInstance().newJDBCCon("eibs-server");
			}
			Statement st = cnx.createStatement();
			ResultSet rs =
				st.executeQuery(
					"SELECT DISTINCT TRIM(bthf03) FROM cntrlbth"
				);
			while (rs.next()) {
				String item = rs.getString(1).trim();
				beanList.addRow(item);	
			}
			rs.close();
			st.close();
		
		} finally {
			try {
				cnx.close();
			} catch (SQLException e) {
				flexLog("Error: " + e);
			}
		}
		return beanList;
	}
	
	/**
	 * This method was created by Eloy Rodriguez.
	 * return Groups List
	*/
	private JBObjList getGroupList(int index) throws ServiceLocatorException, SQLException {
		JBObjList beanList = new JBObjList();
		Connection cnx = null;
		try {
			if (isDataSource) {
				cnx = ServiceLocator.getInstance().getDBConn("eibs-server");
			} else {
				cnx = ServiceLocator.getInstance().newJDBCCon("eibs-server");
			}
			String SQL = "SELECT DISTINCT TRIM(bthf03) FROM cntrlbth";
			PreparedStatement st = cnx.prepareStatement(SQL, ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY);
			ResultSet rs = st.executeQuery();
			if (index > 0) {
				rs.absolute(index);
				rs.last();
				int size = rs.getRow();
				rs.absolute(index);
				boolean firstTime = true;
				int loop = 0;
				while (rs.next() && (loop < records)) {
					if (firstTime) {
						firstTime = false;
						beanList.setFirstRec(index);
					}
					loop++; 
					String name = rs.getString(1).trim();
					if (name.length() > 10) name = name.substring(0, 10);
					String item = "<TR><TD ALIGN=CENTER><INPUT TYPE=RADIO  NAME=\"usercode\" CHECKED VALUE='" + name + "'></TD>" +
								  "<TD>" + name + "</TD></TR>";	
					beanList.addRow(item);
				}		
				beanList.setLastRec(index + loop);
				if (size > index + records) {
					beanList.setShowNext(true);
				}	
			} else {
				while (rs.next()) {
					String name = rs.getString(1).trim();
					if (name.length() > 10) name = name.substring(0, 10);
					String item = "<TR><TD ALIGN=CENTER><INPUT TYPE=RADIO  NAME=\"usercode\" CHECKED VALUE='" + name + "'></TD>" +
								  "<TD>" + name + "</TD></TR>";	
					beanList.addRow(item);	
				}
			}	
			rs.close();
			st.close();
		
		} finally {
			try {
				cnx.close();
			} catch (SQLException e) {
				flexLog("Error: " + e);
			}
		}
		return beanList;
	}
	
	/**
	 * This method was created by Eloy Rodriguez.
	 * return Users List
	*/
	private JBObjList getUsersList(String group) throws ServiceLocatorException, SQLException {
		JBObjList beanList = new JBObjList();
		Connection cnx = null;
		try {
			if (isDataSource) {
				cnx = ServiceLocator.getInstance().getDBConn("eibs-server");
			} else {
				cnx = ServiceLocator.getInstance().newJDBCCon("eibs-server");
			}
			Statement st = cnx.createStatement();
			ResultSet rs =
				st.executeQuery(
					"SELECT TRIM(bthf03), TRIM(bthkey) FROM cntrlbth WHERE TRIM(bthf03) = '" + group + "'"
				);
			while (rs.next()) {
				String item = rs.getString(2).trim();
				beanList.addRow(item);	
			}
			rs.close();
			st.close();
		
		} finally {
			try {
				cnx.close();
			} catch (SQLException e) {
				flexLog("Error: " + e);
			}
		}
		return beanList;
	}

	/**
	 * This method was created by Enrique Almonte.
	 */
	protected void procReqReportsxUserFS(
		ESS0030DSMessage user,
		HttpServletRequest req,
		HttpServletResponse res,
		HttpSession ses)
		throws ServletException, IOException {

		JBList beanList = null;
		JBList beanListRepGrp = null;
		String[] filesList = null;

		String rowHTML = "";
		String usercode = req.getParameter("usercode");
		String dateFrom = req.getParameter("DATEFROM");
		String dateTo = req.getParameter("DATETO");
		String reportName = req.getParameter("REPNAME");
		
		if (dateFrom == null)
			dateFrom = "";
		if (dateTo == null)
			dateTo = "";
		try {
			beanList = new JBList();
			beanListRepGrp = new JBList();
		} catch (Exception ex) {
			flexLog("Error: " + ex);
		}

		if (usercode == null)
			usercode = user.getH01USR();
		Connection cnx = null;
		try {
			if (isDataSource) {
				cnx = ServiceLocator.getInstance().getDBConn("eibs-server");
			} else {
				cnx = ServiceLocator.getInstance().newJDBCCon("eibs-server");
			}
//			Context ctx = new InitialContext();
//			if (ctx == null)
//				throw new Exception("No Context");
//
//			DataSource ds = (DataSource) ctx.lookup("java:comp/env/jdbc/dsr04");
//
//			if (ds != null)
//				cnx = ds.getConnection();

			Statement st = cnx.createStatement();
			ResultSet rs =
				st.executeQuery(
					"select case when trim(bthf03) = '' then 'ITEMFOLDER' else bthf03 end as ITEMFOLDER, bthkey from cntrlbth order by bthf03,bthkey");
			JBParseTree dataTree = new JBParseTree();
			dataTree.init();
			String item = "";
			String folder = "";
			while (rs.next()) {
				item = rs.getString(2).trim();
				folder =
					(rs.getString(1).trim().equals("ITEMFOLDER")
						? ""
						: rs.getString(1).trim());
				dataTree.addRow(item, folder, "", "");
			}
			rs.close();
			rs = null;
			st.close();
			st = null;

			Vector vectorOfGroups = new java.util.Vector();
			vectorOfGroups = dataTree.getParents(usercode);
			String listOfGroups = "";
			for (int i = 0; i < vectorOfGroups.size(); i++) {
				listOfGroups += "'"
					+ ((String) vectorOfGroups.elementAt(i)).trim()
					+ "',";
			}

			listOfGroups =
				(listOfGroups.length() > 0
					? listOfGroups.substring(0, listOfGroups.length() - 1)
					: "");

			//capture all reports description from database....

			// Table names : USRRPT , IBSRPT
			st = cnx.createStatement();
			rs =
				st.executeQuery(
					"SELECT DISTINCT A.USRRPN, B.IBSDSC, A.USRUID FROM "
						+ "USRRPT A, "
						+ "IBSRPT B WHERE A.USRUID IN (\'"
						+ usercode
						+ (listOfGroups.length() > 0
							? "\'," + listOfGroups
							: "'")
						+ ") "
						+ "AND A.USRRPN = B.IBSRPN AND B.IBSLIF = \'"
						+ Lang
						+ "\'");

			// Receiving
			flexLog("About to receive data");
			String linkDownload = "";
			//ini ListRec
			String myFlag = "";
			String colData = "";

			File dir = new File(JSEIBSProp.getEODPDFPath());
			boolean dirExist = true;

			//if (!dir.isDirectory()) {
			//	flexLog("property EODPDFPath :" + JSEIBSProp.getEODPDFPath() + " is not a valid path ");
			//	dirExist = false;
			//}
			//else 
			filesList = dir.list();

			while (rs.next()) {
				//Radio button generated in JSP
				if (dateFrom.equals("")) {
					colData = rs.getString(1).trim();
					if (colData.equals(""))
						colData = "&nbsp;";
					rowHTML =
						"<td nowrap>"
							+ colData.concat("          ").substring(0, 10)
							+ "</td>";
					// Report name
					colData = rs.getString(2).trim();
					if (colData.equals(""))
						colData = "&nbsp;";
					rowHTML += "<td nowrap><div nowrap>"
						+ colData
						+ "</div></td>";
					// Description
					beanList.addRow(myFlag, rowHTML);
					beanListRepGrp.addRow(myFlag, rs.getString(3).trim());
				} else {

					try {
						for (int i = 0; i < filesList.length; i++) {
							colData = rs.getString(1).trim();
							if (isFileInRange(filesList[i].trim(),
								dateFrom,
								dateTo,
								user.getE01DTF(),
								reportName,
								colData)) {
								rowHTML =
									"<td nowrap>"
										+ "<a href=\"javascript:openFile('"
										+ filesList[i].trim()
										+ "')\" ALT=\""
										+ filesList[i].trim()
										+ "\" >"
										+ filesList[i].trim()
										+ "</a></td>";
								//rowHTML =  "<td nowrap>" + "<a href=\""+JSEIBSProp.getEODPDFURL()+"/" + filesList[i].trim() + "\" ALT=\"" + filesList[i].trim() +"\" >"+filesList[i].trim()+"</a></td>";
								colData = rs.getString(2).trim();
								if (colData.equals(""))
									colData = "&nbsp;";
								rowHTML += "<td nowrap><div nowrap>"
									+ colData
									+ "</div></td>";
								// Description
								beanList.addRow(myFlag, rowHTML);
							}

						}
					} catch (Exception e) {
						flexLog("Exception : " + e);
					}

				}

			}
			rs.close();
		} catch (SQLException e) {
			flexLog("Error: " + e);
			res.sendRedirect(super.srctx + LangPath + super.sckNotRespondPage);
		} catch (ServiceLocatorException e) {
			flexLog("Error: " + e);
			res.sendRedirect(super.srctx + LangPath + super.sckNotRespondPage);
		} catch (Exception e) {
			flexLog("Error: " + e);
			//change to page of sql error
			res.sendRedirect(super.srctx + LangPath + super.sckNotRespondPage);
		} finally {
			try {
				cnx.close();
			} catch (SQLException e) {
				flexLog("Error: " + e);
			}
		}

		ses.setAttribute("beanList", beanList);
		ses.setAttribute("beanListRepGrp", beanListRepGrp);
		try {
			procReqReportsDetails(req, res);
		} catch (Exception e) {
			flexLog("Exception calling page " + e);
		}

	}
	/**
	 * This method was created by Enrique Almonte.
	 */
	protected void procReqReportsAll(
		ESS0030DSMessage user,
		HttpServletRequest req,
		HttpServletResponse res,
		HttpSession ses)
		throws ServletException, IOException {

		JBList beanListRep = null;
		String myRow = "";

		beanListRep = new JBList();
		Connection cnx = null;
		try {
			if (isDataSource) {
				cnx = ServiceLocator.getInstance().getDBConn("eibs-server");
			} else {
				cnx = ServiceLocator.getInstance().newJDBCCon("eibs-server");
			}
//			Context ctx = new InitialContext();
//			if (ctx == null)
//				throw new Exception("No Context");
//
//			DataSource ds = (DataSource) ctx.lookup("java:comp/env/jdbc/dsr04");
//
//			if (ds != null)
//				cnx = ds.getConnection();

			// Table names : IBSRPT
			Statement st = cnx.createStatement();
			ResultSet rs =
				st.executeQuery(
					"SELECT IBSRPN, IBSDSC FROM IBSRPT WHERE IBSLIF = \'"
						+ Lang
						+ "\' ORDER BY IBSRPN");
			// Receiving
			flexLog("About to receive data");
			String myFlag = "";
			while (rs.next()) {
				myRow =
					rs.getString(1).trim().concat("          ").substring(
						0,
						10)
						+ " - "
						+ rs.getString(2).trim();
				beanListRep.addRow(myFlag, myRow);
			}
			rs.close();
			ses.setAttribute("beanListRep", beanListRep);

			try {

				String usercode = req.getParameter("usercode");
				String reportName = req.getParameter("REPNAME");
				String screenAct = req.getParameter("SCREENACT");
				String firstLink =
					LangPath
						+ "EODPDF_report_sel.jsp?usercode="
						+ usercode
						+ "&SCREEN="
						+ screenAct
						+ "&REPNAME="
						+ reportName;
				flexLog("About to call Page: " + firstLink);
				res.sendRedirect(super.srctx + firstLink);
			} catch (Exception e) {
				flexLog("Exception calling page " + e);
			}
			//close 
		} catch (SQLException e) {
			flexLog("Error: " + e);
			res.sendRedirect(super.srctx + LangPath + super.sckNotRespondPage);
		} catch (ServiceLocatorException e) {
			flexLog("Error: " + e);
			res.sendRedirect(super.srctx + LangPath + super.sckNotRespondPage);
		} catch (Exception e) {
			flexLog("Error: " + e);
			//change to page of sql error
			res.sendRedirect(super.srctx + LangPath + super.sckNotRespondPage);
		} finally {
			try {
				cnx.close();
			} catch (SQLException e) {
				flexLog("Error: " + e);
			}
		}
	}

	/**
	 * This method was created by Enrique Almonte.
	 */
	protected void procReqReportsDetails(
		HttpServletRequest req,
		HttpServletResponse res)
		throws ServletException, IOException {

		try {
			String usercode = req.getParameter("usercode");
			String screen = req.getParameter("SCREEN");
			String reportName = req.getParameter("REPNAME");
			String type = req.getParameter("TYPE");
			if(type == null) type = "";
			String firstLink = "";
			if (screen.equals("4")
				|| screen.equals("6")
				|| screen.equals("8")) {
				firstLink =
					"/servlet/datapro.eibs.tools.JSEODPDF?usercode="
						+ usercode
						+ "&SCREEN=10"
						+ "&REPNAME="
						+ reportName;
			} else {
				firstLink =
					LangPath
						+ "EODPDF_user_reports_detail.jsp?usercode="
						+ usercode
						+ "&SCREEN="
						+ screen
						+ "&REPNAME="
						+ reportName
					    + "&TYPE="
					    + type;
			}
			flexLog("About to call Page: " + firstLink);
			res.sendRedirect(super.srctx + firstLink);

		} catch (Exception e) {
			flexLog("Error: " + e);
			throw new RuntimeException("Socket Communication Error");
		}

	}
	/**
	 * This method was created by Enrique Almonte.
	 */
	protected void procActMantData(
		ESS0030DSMessage user,
		int screen,
		HttpServletRequest req,
		HttpServletResponse res,
		HttpSession ses)
		throws ServletException, IOException {
		ELEERRMessage msgError = null;

		try {
			msgError = new ELEERRMessage();
		} catch (Exception ex) {
			flexLog("Error: " + ex);
		}
		String userCode = "";
		String reportName = "";
		String reportNameAnt = "";
		String[] reportArrayNames = null;
		switch (screen) {
			case A_DEL_REPXUSER :
			case A_MOD_REPXUSER :
			case A_NEW_REPXUSER :
				reportArrayNames = req.getParameterValues("REPNAME");
				userCode = req.getParameter("usercode");
				reportName = req.getParameter("REPNAME");
				reportNameAnt = req.getParameter("REPNAMEANT");
				break;
			default :
				res.sendRedirect(super.srctx + LangPath + super.devPage);
				break;
		}

		//Statement stUpdate = null;
		//capture all users groups
		// capture all users groups
		String listOfGroups = "";
		//st = (dbConn1.getDBConnection()).createStatement();

		Connection cnx = null;
		try {
			if (isDataSource) {
				cnx = ServiceLocator.getInstance().getDBConn("eibs-server");
			} else {
				cnx = ServiceLocator.getInstance().newJDBCCon("eibs-server");
			}
//			Context ctx = new InitialContext();
//			if (ctx == null)
//				throw new Exception("No Context");
//
//			DataSource ds = (DataSource) ctx.lookup("java:comp/env/jdbc/dsr04");
//
//			if (ds != null)
//				cnx = ds.getConnection();

			Statement st = cnx.createStatement();
			ResultSet rs =
				st.executeQuery(
					"select case when trim(bthf03) = '' then 'ITEMFOLDER' else bthf03 end as ITEMFOLDER, bthkey from cntrlbth order by bthf03,bthkey");

			JBParseTree dataTree = new JBParseTree();
			dataTree.init();
			//rs =st.executeQuery("select case when trim(bthf03) = '' then 'ITEMFOLDER' else bthf03 end as ITEMFOLDER, bthkey from " + JSEIBSProp.getDbSchema() + ".cntrlbth order by ITEMFOLDER");
			String item = "";
			String folder = "";
			while (rs.next()) {
				item = rs.getString(2).trim();
				folder =
					(rs.getString(1).trim().equals("ITEMFOLDER")
						? ""
						: rs.getString(1).trim());
				dataTree.addRow(item, folder, "", "");
			}
			rs.close();

			Vector vectorOfGroups = new java.util.Vector();
			vectorOfGroups = dataTree.getParents(userCode);

			for (int i = 0; i < vectorOfGroups.size(); i++) {
				listOfGroups += "'"
					+ ((String) vectorOfGroups.elementAt(i)).trim()
					+ "',";
			}
			listOfGroups =
				(listOfGroups.length() > 0
					? listOfGroups.substring(0, listOfGroups.length() - 1)
					: "");

			// 
			String sqlTxt = "";
			String linkSuccess = "";
			// check names of tables and fields ...
			int result = 0;
			msgError.setERRNUM("0");
			switch (screen) {
				case A_DEL_REPXUSER :
					{
						String sql =
							"DELETE FROM USRRPT WHERE USRUID = ? AND USRRPN = ?";
						PreparedStatement ps = cnx.prepareStatement(sql);
						ps.setString(1, userCode.trim());
						ps.setString(2, reportNameAnt.trim());
						ps.executeUpdate();
						//result = stUpdate.executeUpdate("DELETE FROM "+JSEIBSProp.getDbSchema()+".USRRPT WHERE USRUID = \'" + userCode.trim() + "\' AND USRRPN = \'" + reportNameAnt.trim() + "\'");
						// link to page if execute sql was successfull ..
						linkSuccess = "";
					}
					break;
				case A_MOD_REPXUSER :
					{
						String sql =
							"UPDATE USRRPT SET USRRPN = ? WHERE USRUID = ? AND USRRPN = ?";
						PreparedStatement ps = cnx.prepareStatement(sql);
						ps.setString(1, reportName.trim());
						ps.setString(2, userCode.trim());
						ps.setString(3, reportNameAnt.trim());

						ps.executeUpdate();

						//result = stUpdate.executeUpdate("UPDATE "+JSEIBSProp.getDbSchema()+".USRRPT SET USRRPN = \'" + reportName.trim() + "\' WHERE USRUID = \'" + userCode.trim() + "\' AND USRRPN = \'"+ reportNameAnt.trim() + "\'");
						linkSuccess = "";
					}
					break;
				case A_NEW_REPXUSER :
					{
						String sql =
							"SELECT COUNT(*) FROM USRRPT WHERE USRUID IN ? AND USRRPN = ?";
						PreparedStatement ps1 = cnx.prepareStatement(sql);
						sql = "INSERT INTO USRRPT (USRUID,USRRPN) VALUES (?,?)";
						PreparedStatement ps2 = cnx.prepareStatement(sql);
						for (int i = 0; i < reportArrayNames.length; i++) {
							ps1.clearParameters();
							ps1.setString(1, userCode.trim());
							ps1.setString(2, reportArrayNames[i].trim());
							rs = ps1.executeQuery();
							rs.next();
							if (rs.getInt(1) > 0) {
								getSQLError(
									msgError,
									reportArrayNames[i].trim());
								showERROR(msgError);
								break;
							}
							rs.close();

							ps2.clearParameters();
							ps2.setString(1, userCode.trim());
							ps2.setString(2, reportArrayNames[i].trim());
							ps2.executeUpdate();

							//result = stUpdate.executeUpdate("INSERT INTO "+JSEIBSProp.getDbSchema()+".USRRPT (USRUID,USRRPN) VALUES (\'" + userCode.trim() + "\',\'" + reportArrayNames[i].trim() + "\')");
						}
						linkSuccess = "";
					}
					break;
			}

		} catch (ServiceLocatorException e) {
			flexLog("Error: " + e);
			//change to page of sql error
			res.sendRedirect(super.srctx + LangPath + super.sckNotRespondPage);
		} catch (SQLException e) {
			flexLog("Error: " + e);
			//change to page of sql error
			res.sendRedirect(super.srctx + LangPath + super.sckNotRespondPage);
		} catch (Exception e) {
			flexLog("Error: " + e);
			//change to page of sql error
			res.sendRedirect(super.srctx + LangPath + super.sckNotRespondPage);
		} finally {
			try {
				cnx.close();
			} catch (SQLException e) {
				flexLog("Error: " + e);
			}
		}
		ses.setAttribute("error", msgError);
		procReqReportsDetails(req, res);
	}

	protected void procReqReportsSearch(
		ESS0030DSMessage user,
		HttpServletRequest req,
		HttpServletResponse res,
		HttpSession ses)
		throws ServletException, IOException {
		ESS0030DSMessage msgUser = null;
		JBList beanListRepUser = null;
		String rowHTML = "";
		String usercode = req.getParameter("usercode");

		try {
			beanListRepUser = new JBList();
			msgUser =
				(datapro.eibs.beans.ESS0030DSMessage) ses.getAttribute(
					"currUser");
		} catch (Exception ex) {
			flexLog("Error: " + ex);
		}
		if (usercode == null)
			usercode = msgUser.getH01USR();
		Connection cnx = null;
		try {
			if (isDataSource) {
				cnx = ServiceLocator.getInstance().getDBConn("eibs-server");
			} else {
				cnx = ServiceLocator.getInstance().newJDBCCon("eibs-server");
			}
//			Context ctx = new InitialContext();
//			if (ctx == null)
//				throw new Exception("No Context");
//
//			DataSource ds = (DataSource) ctx.lookup("java:comp/env/jdbc/dsr04");
//
//			if (ds != null)
//				cnx = ds.getConnection();

			Statement st = cnx.createStatement();
			ResultSet rs = st.executeQuery("SELECT bthf03 FROM cntrlbth WHERE bthkey = \'" + usercode + "\' ");		
			String groupUsr = "";
			while (rs.next()) {
				groupUsr = rs.getString(1).trim();
			}
			rs.close();
			rs = null;
			st.close();
			st = null;

			st = cnx.createStatement();
			//capture all reports description from database....
			//st = (dbConn1.getDBConnection()).createStatement();
			// Table names : USRRPT , IBSRPT
			rs = st.executeQuery(
								"SELECT DISTINCT A.USRRPN, B.IBSDSC FROM "
									+ "USRRPT A, "
									+ "IBSRPT B WHERE (A.USRUID = \'"
									+ usercode
									+ "\' " 
									+ " OR A.USRUID = \'"
									+ groupUsr
									+ "\' )" 									
									+ " AND A.USRRPN = B.IBSRPN AND B.IBSLIF = \'"
									+ Lang
									+ "\' ORDER BY A.USRRPN");

			// Receiving
			flexLog("About to receive data");
			String linkDownload = "";
			//ini ListRec
			String myFlag = "";
			String colData = "";
			while (rs.next()) {
				//Radio button generated in JSP
				colData =
					rs.getString(1).concat("          ").substring(0, 10)
						+ " - "
						+ rs.getString(2).trim();
				beanListRepUser.addRow(myFlag, colData);
			}
			rs.close();
		} catch (ServiceLocatorException e) {
			flexLog("Error: " + e);
			//change to page of sql error
			res.sendRedirect(super.srctx + LangPath + super.sckNotRespondPage);
		} catch (SQLException e) {
			flexLog("Error: " + e);
			//change to page of sql error
			res.sendRedirect(super.srctx + LangPath + super.sckNotRespondPage);
		} catch (Exception e) {
			flexLog("Error: " + e);
			//change to page of sql error
			res.sendRedirect(super.srctx + LangPath + super.sckNotRespondPage);
		}
		ses.setAttribute("beanListRepUser", beanListRepUser);
		try {
			String firstLink = LangPath + "EODPDF_report_inq.jsp";
			flexLog("About to call Page: " + firstLink);
			res.sendRedirect(super.srctx + firstLink);
		} catch (Exception e) {
			flexLog("Exception calling page " + e);
		}

	}

	protected void showERROR(ELEERRMessage m) {
		if (logType != NONE) {

			flexLog("ERROR received.");

			flexLog("ERROR number:" + m.getERRNUM());
			flexLog("ERR001 = " + m.getERNU01() + " desc: " + m.getERDS01());
			flexLog("ERR002 = " + m.getERNU02() + " desc: " + m.getERDS02());
			flexLog("ERR003 = " + m.getERNU03() + " desc: " + m.getERDS03());
			flexLog("ERR004 = " + m.getERNU04() + " desc: " + m.getERDS04());
			flexLog("ERR005 = " + m.getERNU05() + " desc: " + m.getERDS05());
			flexLog("ERR006 = " + m.getERNU06() + " desc: " + m.getERDS06());
			flexLog("ERR007 = " + m.getERNU07() + " desc: " + m.getERDS07());
			flexLog("ERR008 = " + m.getERNU08() + " desc: " + m.getERDS08());
			flexLog("ERR009 = " + m.getERNU09() + " desc: " + m.getERDS09());
			flexLog("ERR010 = " + m.getERNU10() + " desc: " + m.getERDS10());

		}
	}

	protected void getSQLError(ELEERRMessage m) {
		getSQLError(m, "");
	}

	protected void getSQLError(ELEERRMessage m, String msgReport) {
		try {
			m.setERRNUM("1");
			m.setERDF01("REPNUM");
			m.setERDR01("0");
			m.setERNU01("9400");
			m.setERDS01("Report already assign to this user: " + msgReport);
		} catch (Exception e) {
			flexLog("Error: " + e);
		}

	}

	protected boolean isFileInRange(
		String reportFile,
		String dateFrom,
		String dateTo,
		String DTFMT,
		String reportName,
		String reportActual) {
		boolean valRet = false;
		try {
			// range of dates
			String ddFrom = "";
			String ddTo = "";
			String mmFrom = "";
			String mmTo = "";
			String yyFrom = "";
			String yyTo = "";

			int intDTFMT =
				((DTFMT.equals("DMY"))
					? 1
					: ((DTFMT.equals("MDY"))
						? 2
						: ((DTFMT.equals("YMD")) ? 3 : 0)));
			switch (intDTFMT) {
				case 1 :
					ddFrom = dateFrom.substring(0, 2);
					mmFrom = dateFrom.substring(3, 5);
					yyFrom = dateFrom.substring(6, 10);
					ddTo = dateTo.substring(0, 2);
					mmTo = dateTo.substring(3, 5);
					yyTo = dateTo.substring(6, 10);
					break;
				case 2 :
					ddFrom = dateFrom.substring(3, 5);
					mmFrom = dateFrom.substring(0, 2);
					yyFrom = dateFrom.substring(6, 10);
					ddTo = dateTo.substring(3, 5);
					mmTo = dateTo.substring(0, 2);
					yyTo = dateTo.substring(6, 10);
					break;
				case 3 :
					ddFrom = dateFrom.substring(8, 10);
					mmFrom = dateFrom.substring(5, 7);
					yyFrom = dateFrom.substring(0, 4);
					ddTo = dateTo.substring(8, 10);
					mmTo = dateTo.substring(5, 7);
					yyTo = dateTo.substring(0, 4);
					break;
			}
			//
			// filename = REPSMMDDYY.REPNAME
			//
			// calculate century

			String century = "";
			int year = Integer.parseInt(reportFile.substring(7, 9), 10);
			if (year > 50)
				century = "19";
			else
				century = "20";

			String dateOfFile =
				century
					+ reportFile.substring(7, 9)
					+ reportFile.substring(3, 5)
					+ reportFile.substring(5, 7);
			boolean ok = true;
			if (reportName.trim().equals("")
				&& reportActual.trim().equals(
					reportFile.substring(10, reportFile.length()).trim()))
				ok = true;
			else {
				if (!reportName.equals("")
					&& reportName.trim().equals(
						reportFile.substring(10, reportFile.length()).trim())
					&& reportName.trim().equals(reportActual.trim()))
					ok = true;
				else
					ok = false;
			}

			if (Integer.parseInt(dateOfFile, 10)
				>= Integer.parseInt(yyFrom + mmFrom + ddFrom, 10)
				&& Integer.parseInt(dateOfFile, 10)
					<= Integer.parseInt(yyTo + mmTo + ddTo, 10)
				&& ok)
				valRet = true;
			else
				valRet = false;
		} catch (Exception e) {
			flexLog("Error : " + e);
		}
		return valRet;
	}

	protected void procReqReportsxUserDB(
		ESS0030DSMessage user,
		HttpServletRequest req,
		HttpServletResponse res,
		HttpSession ses)
		throws ServletException, IOException {

		JBList beanList = null;
		JBList beanListRepGrp = null;
		String[] filesList = null;

		String usercode = req.getParameter("usercode");
		String dateFrom = req.getParameter("DATEFROM");
		String dateTo = req.getParameter("DATETO");
		if (dateFrom == null)
			dateFrom = "";
		if (dateTo == null)
			dateTo = "";
		String reportName = req.getParameter("REPNAME");
		
		int intDTFMT =
			((user.getE01DTF().equals("DMY"))
				? 1
				: ((user.getE01DTF().equals("MDY"))
					? 2
					: ((user.getE01DTF().equals("YMD")) ? 3 : 0)));
		int from = 0;
		int to = 0;
		if (dateFrom != null && !dateFrom.trim().equals("")) {
			// range of dates
			switch (intDTFMT) {
				case 1 :
					from =
						Integer.parseInt(
							dateFrom.substring(6, 10)
								+ dateFrom.substring(3, 5)
								+ dateFrom.substring(0, 2));
					break;
				case 2 :
					from =
						Integer.parseInt(
							dateFrom.substring(6, 10)
								+ dateFrom.substring(0, 2)
								+ dateFrom.substring(3, 5));
					break;
				case 3 :
					from =
						Integer.parseInt(
							dateFrom.substring(0, 4)
								+ dateFrom.substring(5, 7)
								+ dateFrom.substring(8, 10));
					break;
			}
		} else {
			from = 0;
		}
		if (dateTo != null && !dateTo.trim().equals("")) {
			// range of dates
			switch (intDTFMT) {
				case 1 :
					to =
						Integer.parseInt(
							dateTo.substring(6, 10)
								+ dateTo.substring(3, 5)
								+ dateTo.substring(0, 2));
					break;
				case 2 :
					to =
						Integer.parseInt(
							dateTo.substring(6, 10)
								+ dateTo.substring(0, 2)
								+ dateTo.substring(3, 5));
					break;
				case 3 :
					to =
						Integer.parseInt(
							dateTo.substring(0, 4)
								+ dateTo.substring(5, 7)
								+ dateTo.substring(8, 10));
					break;
			}
		} else {
			to = 99999999;
		}

		try {
			beanList = new JBList();
			beanListRepGrp = new JBList();
		} catch (Exception ex) {
			flexLog("Error: " + ex);
		}

		if (usercode == null)
			usercode = user.getH01USR();
		Connection cnx = null;
		try {
			if (isDataSource) {
				cnx = ServiceLocator.getInstance().getDBConn("eibs-server");
			} else {
				cnx = ServiceLocator.getInstance().newJDBCCon("eibs-server");
			}
//			Context ctx = new InitialContext();
//			if (ctx == null)
//				throw new Exception("No Context");
//
//			DataSource ds = (DataSource) ctx.lookup("java:comp/env/jdbc/dsr04");
//
//			if (ds != null)
//				cnx = ds.getConnection();

			Statement st = cnx.createStatement();
			ResultSet rs =
				st.executeQuery(
					"select case when trim(bthf03) = '' then 'ITEMFOLDER' else bthf03 end as ITEMFOLDER, bthkey from cntrlbth order by bthf03,bthkey");
			JBParseTree dataTree = new JBParseTree();
			dataTree.init();
			String item = "";
			String folder = "";
			while (rs.next()) {
				item = rs.getString(2).trim();
				folder =
					(rs.getString(1).trim().equals("ITEMFOLDER")
						? ""
						: rs.getString(1).trim());
				dataTree.addRow(item, folder, "", "");
			}
			rs.close();
			rs = null;
			st.close();
			st = null;

			Vector vectorOfGroups = new java.util.Vector();
			vectorOfGroups = dataTree.getParents(usercode);
			String listOfGroups = "";
			for (int i = 0; i < vectorOfGroups.size(); i++) {
				listOfGroups += "'"
					+ ((String) vectorOfGroups.elementAt(i)).trim()
					+ "',";
			}

			listOfGroups =
				(listOfGroups.length() > 0
					? listOfGroups.substring(0, listOfGroups.length() - 1)
					: "");

			//capture all reports description from database....

			// Table names : USRRPT , IBSRPT
			st = cnx.createStatement();
			rs =
				st.executeQuery(
					"SELECT E.EDPDTY, E.EDPDTM, E.EDPDTD, "
						+ "A.USRRPN, E.EDPPTH, B.IBSDSC, A.USRUID FROM "
						+ "EODPDF E, "
						+ "USRRPT A, "
						+ "IBSRPT B WHERE A.USRUID IN (\'"
						+ usercode
						+ (listOfGroups.length() > 0
							? "\'," + listOfGroups
							: "'")
						+ ") "
						+ "AND A.USRRPN = B.IBSRPN " 
						+ "AND A.USRRPN LIKE '" + (reportName.trim().equals("") ? "%" : 
								Util.addRightChar(' ', 10, reportName)) + "' " 
						+ "AND B.IBSLIF = \'"
						+ Lang
						+ "\' "
						+ "AND ((10000*(E.EDPDTY+2000))+(100*E.EDPDTM)+(E.EDPDTD) >= "
						+ from
						+ ") "
						+ "AND ((10000*(E.EDPDTY+2000))+(100*E.EDPDTM)+(E.EDPDTD) <= "
						+ to
						+ ") AND EDPRPN = B.IBSRPN ORDER BY A.USRRPN");

			// Receiving
			flexLog("About to receive data");
			String linkDownload = "";
			//ini ListRec
			String myFlag = "";
			String colData = "";
			String rowHTML = "";
			String prevColData = "";
			while (rs.next()) {

				if (dateFrom.equals("")) {
					colData = rs.getString(4).trim();

					if (colData.equals(""))
						colData = "&nbsp;";
					rowHTML =
						"<td nowrap>"
							+ colData.concat("          ").substring(0, 10)
							+ "</td>";
					// Report name
					colData = rs.getString(6).trim();
					if (colData.equals(""))
						colData = "&nbsp;";
					rowHTML += "<td nowrap><div nowrap>"
						+ colData
						+ "</div></td>";
					// Description
					if (prevColData.trim().equals("")
						|| !prevColData.trim().equals(colData.trim())) {
						prevColData = colData;
						beanList.addRow(myFlag, rowHTML);
						beanListRepGrp.addRow(myFlag, rs.getString(7).trim());
					}

				} else {
					
					colData =
						rs.getString(4).trim()
							+ "("
							+ (rs.getString(1).trim().length() < 2
								? "0" + rs.getString(1).trim()
								: rs.getString(1).trim())
							+ "/"
							+ (rs.getString(2).trim().length() < 2
								? "0" + rs.getString(2).trim()
								: rs.getString(2).trim())
							+ "/"
							+ (rs.getString(3).trim().length() < 2
								? "0" + rs.getString(3).trim()
								: rs.getString(3).trim())
							+ ")";

					//					"REP"
					//						+ (rs.getString(1).trim().length() < 2 ? "0"+rs.getString(1).trim():rs.getString(1).trim())
					//						+ (rs.getString(2).trim().length() < 2 ? "0"+rs.getString(2).trim():rs.getString(2).trim())
					//						+ (rs.getString(3).trim().length() < 2 ? "0"+rs.getString(3).trim():rs.getString(3).trim());

					String url =
						JSEIBSProp.getEODPDFURL()
							+ "/"
							+ rs.getString(5).trim();
					
					String agency="";
					int agIdx =	url.indexOf('_');
					if (agIdx > 0 && url.substring(agIdx+1).indexOf('_') > 0 
						&& (url.trim().endsWith("pdf") || url.trim().endsWith("PDF")))
				      agency= url.substring(agIdx+1, agIdx+4);
				    
				    rowHTML = "<td nowrap>"+ agency + "</td>";	
				    		
					rowHTML +=
						"<td nowrap>"
							+ "<a href=\"javascript:openFile('"
							+ url
							+ "')"
							+ "\" alt=\"" + colData	+ "\"" +
					        " title=\"" + agency	+ "\"" +
							">"+
							colData +
							 "</a></td>";
					colData = rs.getString(6).trim();
					if (colData.equals(""))
						colData = "&nbsp;";
					rowHTML += "<td nowrap><div nowrap>"
						+ colData
						+ "</div></td>";
					// Description
					if (reportName.trim().equals("")) {
						beanListRepGrp.addRow(myFlag, rs.getString(7).trim());
						beanList.addRow(myFlag, rowHTML);
					} else {
						if (reportName.trim().equals(rs.getString(4).trim())) {
							beanListRepGrp.addRow(myFlag, rs.getString(7).trim());
							beanList.addRow(myFlag, rowHTML);
						}
					}
				}
			}
			rs.close();
		} catch (ServiceLocatorException e) {
			flexLog("Error: " + e);
			//change to page of sql error
			res.sendRedirect(super.srctx + LangPath + super.sckNotRespondPage);
		} catch (SQLException e) {
			flexLog("Error: " + e);
			//change to page of sql error
			res.sendRedirect(super.srctx + LangPath + super.sckNotRespondPage);
		} catch (Exception e) {
			flexLog("Error: " + e);
			//change to page of sql error
			res.sendRedirect(super.srctx + LangPath + super.sckNotRespondPage);
		} finally {
			try {
				cnx.close();
			} catch (SQLException e) {
				flexLog("Error: " + e);
			}
		}

		ses.setAttribute("beanList", beanList);
		ses.setAttribute("beanListRepGrp", beanListRepGrp);
		try {
			procReqReportsDetails(req, res);
		} catch (Exception e) {
			flexLog("Exception calling page " + e);
		}
	}
	
	protected void procReqReportsxUser(
			ESS0030DSMessage user,
			HttpServletRequest req,
			HttpServletResponse res,
			HttpSession ses)
			throws ServletException, IOException {

			JBList beanList = null;
			JBList beanListRepGrp = null;
			String[] filesList = null;

			String usercode = req.getParameter("usercode");
			String dateFrom = req.getParameter("DATEFROM");
			String dateTo = req.getParameter("DATETO");
			if (dateFrom == null)
				dateFrom = "";
			if (dateTo == null)
				dateTo = "";
			String reportName = req.getParameter("REPNAME");

			int intDTFMT =
				((user.getE01DTF().equals("DMY"))
					? 1
					: ((user.getE01DTF().equals("MDY"))
						? 2
						: ((user.getE01DTF().equals("YMD")) ? 3 : 0)));
			int from = 0;
			int to = 0;
			if (dateFrom != null && !dateFrom.trim().equals("")) {
				// range of dates
				switch (intDTFMT) {
					case 1 :
						from =
							Integer.parseInt(
								dateFrom.substring(6, 10)
									+ dateFrom.substring(3, 5)
									+ dateFrom.substring(0, 2));
						break;
					case 2 :
						from =
							Integer.parseInt(
								dateFrom.substring(6, 10)
									+ dateFrom.substring(0, 2)
									+ dateFrom.substring(3, 5));
						break;
					case 3 :
						from =
							Integer.parseInt(
								dateFrom.substring(0, 4)
									+ dateFrom.substring(5, 7)
									+ dateFrom.substring(8, 10));
						break;
				}
			} else {
				from = 0;
			}
			if (dateTo != null && !dateTo.trim().equals("")) {
				// range of dates
				switch (intDTFMT) {
					case 1 :
						to =
							Integer.parseInt(
								dateTo.substring(6, 10)
									+ dateTo.substring(3, 5)
									+ dateTo.substring(0, 2));
						break;
					case 2 :
						to =
							Integer.parseInt(
								dateTo.substring(6, 10)
									+ dateTo.substring(0, 2)
									+ dateTo.substring(3, 5));
						break;
					case 3 :
						to =
							Integer.parseInt(
								dateTo.substring(0, 4)
									+ dateTo.substring(5, 7)
									+ dateTo.substring(8, 10));
						break;
				}
			} else {
				to = 99999999;
			}

			try {
				beanList = new JBList();
				beanListRepGrp = new JBList();
			} catch (Exception ex) {
				flexLog("Error: " + ex);
			}

			if (usercode == null)
				usercode = user.getH01USR();
			Connection cnx = null;
			try {
				if (isDataSource) {
					cnx = ServiceLocator.getInstance().getDBConn("eibs-server");
				} else {
					cnx = ServiceLocator.getInstance().newJDBCCon("eibs-server");
				}
//				Context ctx = new InitialContext();
//				if (ctx == null)
//					throw new Exception("No Context");
//
//				DataSource ds = (DataSource) ctx.lookup("java:comp/env/jdbc/dsr04");
//
//				if (ds != null)
//					cnx = ds.getConnection();

				Statement st = cnx.createStatement();
				/*ResultSet rs =
					st.executeQuery(
						"select case when trim(bthf03) = '' then 'ITEMFOLDER' else bthf03 end as ITEMFOLDER, bthkey from cntrlbth order by bthf03,bthkey");*/
				ResultSet rs = st.executeQuery("SELECT bthf03 FROM cntrlbth WHERE bthkey = \'" + usercode + "\' ");
						
				
				String group = "";
				while (rs.next()) {
					group =
						(rs.getString(1).trim().equals("ITEMFOLDER")
							? ""
							: rs.getString(1).trim());

				}
				rs.close();
				rs = null;
				st.close();
				st = null;


				// Table names : USRRPT , IBSRPT
				st = cnx.createStatement();
				rs =
					st.executeQuery(
						"SELECT E.EDPDTY, E.EDPDTM, E.EDPDTD, "
							+ "A.USRRPN, E.EDPPTH, B.IBSDSC, A.USRUID FROM "
							+ "EODPDF E, "
							+ "USRRPT A, "
							+ "IBSRPT B WHERE A.USRUID IN (\'"
							+ usercode
							+ (group.length() > 0
								? "\',\'" + group + "\'"
								: "\'")
							+ ") "
							+ "AND A.USRRPN = B.IBSRPN AND B.IBSLIF = \'"
							+ Lang
							+ "\' "
							+ "AND ((10000*(E.EDPDTY+2000))+(100*E.EDPDTM)+(E.EDPDTD) >= "
							+ from
							+ ") "
							+ "AND ((10000*(E.EDPDTY+2000))+(100*E.EDPDTM)+(E.EDPDTD) <= "
							+ to
							+ ") AND EDPRPN = B.IBSRPN ORDER BY A.USRRPN");

				// Receiving
				flexLog("About to receive data");
				String linkDownload = "";
				//ini ListRec
				String myFlag = "";
				String colData = "";
				String rowHTML = "";
				String prevColData = "";
				while (rs.next()) {

					if (dateFrom.equals("")) {
						colData = rs.getString(4).trim();

						if (colData.equals(""))
							colData = "&nbsp;";
						rowHTML =
							"<td nowrap>"
								+ colData.concat("          ").substring(0, 10)
								+ "</td>";
						// Report name
						colData = rs.getString(6).trim();
						if (colData.equals(""))
							colData = "&nbsp;";
						rowHTML += "<td nowrap><div nowrap>"
							+ colData
							+ "</div></td>";
						// Description
						if (prevColData.trim().equals("")
							|| !prevColData.trim().equals(colData.trim())) {
							prevColData = colData;
							beanList.addRow(myFlag, rowHTML);
							beanListRepGrp.addRow(myFlag, rs.getString(7).trim());
						}

					} else {
					
						colData =
							rs.getString(4).trim()
								+ "("
								+ (rs.getString(1).trim().length() < 2
									? "0" + rs.getString(1).trim()
									: rs.getString(1).trim())
								+ "/"
								+ (rs.getString(2).trim().length() < 2
									? "0" + rs.getString(2).trim()
									: rs.getString(2).trim())
								+ "/"
								+ (rs.getString(3).trim().length() < 2
									? "0" + rs.getString(3).trim()
									: rs.getString(3).trim())
								+ ")";

						//					"REP"
						//						+ (rs.getString(1).trim().length() < 2 ? "0"+rs.getString(1).trim():rs.getString(1).trim())
						//						+ (rs.getString(2).trim().length() < 2 ? "0"+rs.getString(2).trim():rs.getString(2).trim())
						//						+ (rs.getString(3).trim().length() < 2 ? "0"+rs.getString(3).trim():rs.getString(3).trim());

						String url =
							JSEIBSProp.getEODPDFURL()
								+ "/"
								+ rs.getString(5).trim();
						rowHTML =
							"<td nowrap>"
								+ "<a href=\"javascript:CenterWindow('"
								+ url
								+ "',600,500,2)"
								+ "\" ALT=\""
								+ colData
								+ "\" >"
								+ colData
								+ "</a></td>";
						colData = rs.getString(6).trim();
						if (colData.equals(""))
							colData = "&nbsp;";
						rowHTML += "<td nowrap><div nowrap>"
							+ colData
							+ "</div></td>";
						// Description
						if (reportName.trim().equals("")) {
					
						beanListRepGrp.addRow(myFlag, rs.getString(7).trim());
						beanList.addRow(myFlag, rowHTML);
						} else {
							if (reportName.trim().equals(rs.getString(4).trim())) {
								beanListRepGrp.addRow(myFlag, rs.getString(7).trim());
								beanList.addRow(myFlag, rowHTML);
							}
						}
					}
				}
				rs.close();
			} catch (ServiceLocatorException e) {
				flexLog("Error: " + e);
				//change to page of sql error
				res.sendRedirect(super.srctx + LangPath + super.sckNotRespondPage);
			} catch (SQLException e) {
				flexLog("Error: " + e);
				//change to page of sql error
				res.sendRedirect(super.srctx + LangPath + super.sckNotRespondPage);
			} catch (Exception e) {
				flexLog("Error: " + e);
				//change to page of sql error
				res.sendRedirect(super.srctx + LangPath + super.sckNotRespondPage);
			} finally {
				try {
					cnx.close();
				} catch (SQLException e) {
					flexLog("Error: " + e);
				}
			}

			ses.setAttribute("beanList", beanList);
			ses.setAttribute("beanListRepGrp", beanListRepGrp);
			try {
				procReqReportsDetails(req, res);
			} catch (Exception e) {
				flexLog("Exception calling page " + e);
			}
		}	
}