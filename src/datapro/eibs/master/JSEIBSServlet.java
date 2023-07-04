/*
 * Created on Feb 28, 2007
 * Created by Saif Mazhar
 */
package datapro.eibs.master;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.datapro.exception.ServiceLocatorException;

import datapro.eibs.beans.ESS0030DSMessage;
import datapro.eibs.beans.UserPos;

public abstract class JSEIBSServlet extends SuperServlet {
	
	public MessageProcessor getMessageProcessor(String targetProgram, HttpServletRequest request) 
		throws IOException {
		try {
			return new MessageProcessor(
				getMessageHandler(targetProgram, request));
		} catch (ServiceLocatorException e) {
			throw new IOException(e.getMessage());
		}
	}
	
	/**
	 * Gets the Language Path for pages.
	 * @param msgUser
	 * @return
	 */
	protected String getLangPath(ESS0030DSMessage msgUser) {
		return SuperServlet.rootPath + msgUser.getE01LAN() + "/";
	}
	
	/**
	 * Gets the Language Path for pages.
	 * @param req
	 * @return
	 */
	protected String getLangPath(HttpServletRequest req) {
		ESS0030DSMessage msgUser =
			(ESS0030DSMessage) req.getSession(false).getAttribute("currUser");
		return SuperServlet.rootPath + msgUser.getE01LAN() + "/";
	}

	public void service(HttpServletRequest req, HttpServletResponse res)
		throws ServletException, IOException {

		ESS0030DSMessage msgUser = null;
		HttpSession session = null;

		session = getSession(req, res);
		if (session != null) {
			msgUser = (ESS0030DSMessage) session.getAttribute("currUser");
			// Here we should get the path from the user profile
			try {
				int screen ;
				try {
					screen = Integer.parseInt(req.getParameter("SCREEN"));
				} catch (Exception e) {
					screen = 1;
				}					
				processRequest(msgUser, req, res, session, screen);
			} catch (Exception e) {
				flexLog("Error: " + e);
				forward(super.sckNotOpenPage, req, res);
			}
		}
	}

	/**
	 * Gets the UserPos object from session.
	 * @param session
	 * @return
	 */
	protected UserPos getUserPos(HttpSession session) {
		return (datapro.eibs.beans.UserPos) session.getAttribute("userPO");
	}
	
	/**
	 * Forwards to the given page the request.
	 * This method adds to the <code>forward</code> method that 
	 * will be removing the error object from the session so no error it's shown.
	 * @param pageName
	 * @param req
	 * @param res
	 * @throws ServletException
	 * @throws IOException
	 */
	protected void forwardOnSuccess(String pageName,
		HttpServletRequest req, HttpServletResponse res)
		throws ServletException, IOException {
		HttpSession session = req.getSession(false);
		if (session != null)
			session.removeAttribute("error");
		callPage(getLangPath(req) + pageName, req, res);
	}
	
	/**
	 * Forwards to the given page the request
	 * @param pageName
	 * @param req
	 * @param res
	 * @throws ServletException
	 * @throws IOException
	 */
	protected void forward(String pageName,
		HttpServletRequest req, HttpServletResponse res)
		throws ServletException, IOException {
		callPage(getLangPath(req) + pageName, req, res);
	}
	
	/**
	 * A servlet must implement this method in order to process a request.
	 * The JSEIBSServlet will be reading from the request 
	 * and providing to this method the current arguments.
	 * The default provided screen has the value 1. 
	 * @param user
	 * @param req
	 * @param res
	 * @param session
	 * @param screen
	 * @throws ServletException
	 * @throws IOException
	 */
	abstract protected void processRequest(
		ESS0030DSMessage user, HttpServletRequest req, HttpServletResponse res, 
		HttpSession session, int screen)
		throws ServletException, IOException;
		
	protected void redirect(String servletName, HttpServletResponse res) throws IOException {
		flexLog("Redirect to servlet: " + servletName);
		if (servletName.indexOf("/servlet/") == -1) {
			res.sendRedirect(super.srctx + "/servlet/" + servletName);
		} else {
			res.sendRedirect(super.srctx + servletName);	
		}
	}

}
