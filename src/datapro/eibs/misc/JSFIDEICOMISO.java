package datapro.eibs.misc;

import java.io.IOException;
import java.net.Socket;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import datapro.eibs.beans.ESS0030DSMessage;
import datapro.eibs.master.SuperServlet;
import datapro.eibs.sockets.MessageContext;

/**
 * Esta clase se encarga de direccionar las opciones de FIDEICOMISO a la
 * aplicacion de ALENET.
 * 
 * Se incluye en el URL un parametro con el usuario de la aplicacion.
 */
public class JSFIDEICOMISO extends SuperServlet {

	private ServletConfig config = null;

	protected String LangPath = "S";

	public void init(ServletConfig config) throws ServletException {
		super.init(config);
		this.config = config;
	}

	public void service(HttpServletRequest req, HttpServletResponse res)
			throws ServletException, IOException {

		Socket s = null;
		MessageContext mc = null;

		ESS0030DSMessage msgUser = null;
		HttpSession session = null;
		String dest = null ;

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

				msgUser = (datapro.eibs.beans.ESS0030DSMessage) session
						.getAttribute("currUser");

				// Here we should get the path from the user profile
				LangPath = super.rootPath + msgUser.getE01LAN() + "/";
				
				dest = req.getParameter("DEST") ;
				dest = dest.replace( ':' , '=' ) ;
				dest = dest.replace( '|' , '&' ) ;
				dest = "http:" + dest.substring(5) ;
				
				flexLog("About to call:" + dest + "&user=" + msgUser.getH01USR() ) ;
				res.sendRedirect( dest + "&user=" + msgUser.getH01USR() ) ;

			} catch (Exception e) {
				flexLog("Error: " + e);
				res.sendRedirect(super.srctx + LangPath
						+ super.sckNotRespondPage);
			}

		}

	}

}
