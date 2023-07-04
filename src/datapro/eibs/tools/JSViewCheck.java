/**
*  This Class was created By Luis Velando
*  Copyright Datapro Inc. 1999
**/

package com.datapro.dibs.servlets;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.datapro.dibs.beans.JBImagesList;
import com.datapro.dibs.generics.SuperServlet;
import com.datapro.dibs.generics.Util;
import com.datapro.exception.ServiceLocatorException;
import com.datapro.services.ServiceLocator;

public class JSViewCheck extends SuperServlet {

	public JSViewCheck() {
		super();
	}

	public void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		HttpSession session;

		JBImagesList imglst = new JBImagesList();
		imglst.initC();
		imglst.initRowC();

		String fileNameF = null;
		String fileNameB = null;

		session = req.getSession(false);

		String UserIdJSP = (String) session.getAttribute("UserIdJSP");
		if (UserIdJSP == null) {
			resp.sendRedirect(super.WebAppCTR + Path + "timeout_error.jsp");
			return;
		}

		String PageToCall = "body_check_viewer.jsp";

		String ACCNUM = Util.takeCero(Util.getVALCHARS(req.getParameter("ACCNUM")));
		String CHKNUM = Util.getVALCHARS(req.getParameter("CHKNUM"));

		imglst.setACCNUM(ACCNUM);
		imglst.setCHKNUM(CHKNUM);

		//		while (ACCNUM.length() < 15) {
		//			ACCNUM = "0" + ACCNUM;
		//		}

		//		while (CHKNUM.length() < 18) {
		//			CHKNUM = "0" + CHKNUM;
		//		}

		fileNameF = ACCNUM + CHKNUM + "F.jpg";
		fileNameB = ACCNUM + CHKNUM + "B.jpg";

		Connection cnx = null;

		try {

			cnx = ServiceLocator.getInstance().getDBConn("MSS");
			Statement st = cnx.createStatement();

			String STM = "SELECT * from  DC_CHECK_DATA where ACCNUMBER = '" + ACCNUM + "' and CHKNUM = '" + CHKNUM + "'";

			ResultSet rs = st.executeQuery(STM);

			if (rs.next()) {
				//if (!ACCNUM.equals("2100000165") && !CHKNUM.equals("3986")) {
					byte buf11[] = rs.getBytes("FRONT");
					byte buf12[] = rs.getBytes("BACK");

					OutputStream fo = new FileOutputStream(DFPath + fileNameF);
					fo.write(buf11);
					fo.close();

					OutputStream fo1 = new FileOutputStream(DFPath + fileNameB);
					fo1.write(buf12);
					fo1.close();

				//}

				imglst.addRowC("<IMG SRC=" + super.WebAppPath + DPath + fileNameF + " WIDTH=550 HEIGHT=225 BORDER=0 >");
				imglst.addRowC("<IMG SRC=" + super.WebAppPath + DPath + fileNameB + " WIDTH=550 HEIGHT=225 BORDER=0 >");

			}

			rs.close();
		} catch (SQLException e) {
			//System.out.println("Exception e : " + e);
		} catch (ServiceLocatorException e) {
			//System.out.println("Exception e : " + e);
		} finally {
			try {
				cnx.close();
			} catch (SQLException e) {
				//System.out.println("Exception e : " + e);
			}
		}

		session.setAttribute("imglst", imglst);

		resp.sendRedirect(super.WebAppCTR + Path + PageToCall);

		return;

	}
}