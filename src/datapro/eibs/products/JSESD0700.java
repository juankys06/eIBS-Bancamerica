package datapro.eibs.products; 

/**
 * This type was created by Orestes Garcia.
 */
import java.io.*;
import java.net.*;
import java.beans.Beans;
import javax.servlet.*;
import javax.servlet.http.*;
import datapro.eibs.beans.*;

import datapro.eibs.sockets.*;

import datapro.eibs.generic.JBParseTree;

public class JSESD0700 extends datapro.eibs.master.SuperServlet {

	protected static final int R_P_NEW 			= 2;
	protected static final int R_P_MAINT 			= 4;
	protected static final int R_P_DEL 			= 6;
	protected static final int A_P_NEW_MAINT 		= 1;

	protected static final int R_P_SHOW = 100;

	protected String LangPath = "S";

	/**
	 * Insert the method's description here.
	 * Creation date: (1/14/00 12:29:44 PM)
	 */
	public JSESD0700() {
		super();
	}
	/**
	 * This method was created by Orestes Garcia.
	 */
	public void destroy() {

		flexLog("Destroying servlet JSESD0700");

	}
	/**
	 * This method was created by Orestes Garcia.
	 */
	public void init(ServletConfig config) throws ServletException {
		super.init(config);
	}
	/**
	 * This method was created by Orestes Garcia.
	 * @param request HttpServletRequest
	 * @param response HttpServletResponse
	 */
	protected void procActionNewMaint(
		MessageContext mc,
		ESS0030DSMessage user,
		HttpServletRequest req,
		HttpServletResponse res,
		HttpSession ses) {
		MessageRecord newmessage = null;
		ESD070001Message msgProd = null;
		ELEERRMessage msgError = null;
		UserPos userPO = null;
		boolean IsNotError = false;

		try {
			msgError = new datapro.eibs.beans.ELEERRMessage();
			userPO = new datapro.eibs.beans.UserPos();
		} catch (Exception ex) {
			flexLog("Error: " + ex);
		}

		int appCode = 0;
		try {
			appCode = Integer.parseInt(req.getParameter("E01APCACD"));
		} catch (Exception e) {
		}

		String opCode = "0005";  // New

		// Send Initial data
		try {
			msgProd = (ESD070001Message) mc.getMessageRecord("ESD070001");
			msgProd.setH01USERID(user.getH01USR());
			msgProd.setH01PROGRM("ESD0711");
			msgProd.setH01TIMSYS(getTimeStamp());
			msgProd.setH01SCRCOD("01");
			msgProd.setH01OPECOD(opCode);
			// all the fields here
		 	java.util.Enumeration enu = msgProd.fieldEnumeration();
			MessageField field = null;
			String value = null;
			while (enu.hasMoreElements()) {
				field = (MessageField)enu.nextElement();
				try {
					value = req.getParameter(field.getTag()).toUpperCase();
					if (value != null) {
						field.setString(value);
					}
				}
				catch (Exception e) {
				}	
			}
	
		 	mc.sendMessage(msgProd);
			msgProd.destroy();
		} catch (Exception e) {
			e.printStackTrace();
			flexLog("Error: " + e);
			throw new RuntimeException("Socket Communication Error");
		}

		// Receive Error Message
		try {
			newmessage = mc.receiveMessage();

			if (newmessage.getFormatName().equals("ELEERR")) {
				msgError = (ELEERRMessage) newmessage;
				IsNotError = msgError.getERRNUM().equals("0");
				flexLog("IsNotError = " + IsNotError);

			} else
				flexLog("Message " + newmessage.getFormatName() + " received.");
			
			newmessage = mc.receiveMessage();

			if (newmessage.getFormatName().equals("ESD070001")) {
				try {
					msgProd = new datapro.eibs.beans.ESD070001Message();
				} catch (Exception ex) {
					flexLog("Error: " + ex);
				}
				String nextPage = "";
				msgProd = (ESD070001Message) newmessage;
				flexLog("Putting java beans into the session");
				ses.setAttribute("error", msgError);
				ses.setAttribute("prd", msgProd);

				if ( IsNotError ) {
					
					try {
						res.setContentType("text/html");
						printCloseAndRefreshOpener(res.getWriter());
					} catch (Exception e) {
						e.printStackTrace();
						flexLog("Exception ocurred. Exception = " + e);
					}
					
				} else {
									
					switch (appCode) {
						case 11 :
						case 12 : // CD
						case 14 :
						case 15 :
						    nextPage = "ESD0700_products_tds.jsp";
							break;
						case 1 : // DDA Accounts
						case 2 :
						case 3 :
						case 4 :
							nextPage = "ESD0700_products_dda.jsp";
							break;
						case 10 :
							nextPage = "ESD0700_products_loans.jsp";
							break;
						case 50 :
						case 51 : // Collections
						nextPage = "ESD0700_products_collections.jsp";
							break;
						case 13 : // Inv 
						    nextPage = "ESD0700_products_investments.jsp";
							break;
						case 40 :
						case 41 : // LC
						case 42 :
						nextPage = "ESD0700_products_lettersofcredit.jsp";
							break;
						case 19 : // Proy 
							break;
						default :
							}
					try {
						flexLog("About to call Page: " + LangPath + nextPage);
						callPage(LangPath + nextPage, req, res);	
					}
					catch (Exception e) {
						flexLog("Exception calling page " + e);
					}

				}
					
				} else
					flexLog("Message " + newmessage.getFormatName() + " received.");

		} catch (Exception e) {
			e.printStackTrace();
			flexLog("Error: " + e);
			throw new RuntimeException("Socket Communication Error");
		}

	}
	/**
	 * This method was created by Orestes Garcia.
	 * @param request HttpServletRequest
	 * @param response HttpServletResponse
	 */
	protected void procReqNew(
		MessageContext mc,
		ESS0030DSMessage user,
		HttpServletRequest req,
		HttpServletResponse res,
		HttpSession ses) {
		MessageRecord newmessage = null;
		ESD070001Message msgProd = null;
		ELEERRMessage msgError = null;
		UserPos userPO = null;

		try {
			msgError = new datapro.eibs.beans.ELEERRMessage();
			userPO = new datapro.eibs.beans.UserPos();
		} catch (Exception ex) {
			flexLog("Error: " + ex);
		}


		int appCode = 0;
		try {
			appCode = Integer.parseInt(req.getParameter("appcode"));
		} catch (Exception e) {
		}
		String bank = req.getParameter("bank");
		String prodCode = req.getParameter("newprod");
		String typeCode = req.getParameter("typecode");
		String generic = req.getParameter("generic");
		String title = req.getParameter("title");
		String accnum = req.getParameter("accnum");


		String opCode = "0001";  // New


		// Send Initial data
		try {
			msgProd = (ESD070001Message) mc.getMessageRecord("ESD070001");
			msgProd.setH01USERID(user.getH01USR());
			msgProd.setH01PROGRM("ESD0711");
			msgProd.setH01TIMSYS(getTimeStamp());
			msgProd.setH01SCRCOD("01");
			msgProd.setH01OPECOD(opCode);
			msgProd.setE01APCTYP(typeCode);
			msgProd.setE01APCCDE(prodCode);
			msgProd.setE01APCBNK(bank);
			msgProd.send();
			msgProd.destroy();
		} catch (Exception e) {
			e.printStackTrace();
			flexLog("Error: " + e);
			throw new RuntimeException("Socket Communication Error");
		}


		// Receive Error Message
		try {
			newmessage = mc.receiveMessage();


			if (newmessage.getFormatName().equals("ELEERR")) {
				msgError = (ELEERRMessage) newmessage;
				showERROR(msgError);


				flexLog("Putting java beans into the session");
				ses.setAttribute("error", msgError);


				procReqProductDetails(req, res);
			} else
				if (newmessage.getFormatName().equals("ESD070001")) {
					try {
						msgProd = new datapro.eibs.beans.ESD070001Message();
					} catch (Exception ex) {
						flexLog("Error: " + ex);
					}


					msgProd = (ESD070001Message) newmessage;


					userPO.setOption("PRODUCT");
					userPO.setPurpose("NEW");
					userPO.setHeader2("DO_NEW");


					flexLog("Putting java beans into the session");
					ses.setAttribute("prd", msgProd);


					try {


						switch (appCode) {
							case 11 :
							case 12 : // CD
							case 14 :
							case 15 :
								try {
									userPO.setHeader3(super.webAppPath + LangPath + "ESD0700_products_tds.jsp");
									flexLog("New LN Product");
								} catch (Exception ex) {
									flexLog("Error: " + ex);
								}
							
								break;
							case 1 : // DDA Accounts
							case 2 :
							case 3 :
								try {
									userPO.setHeader3(super.webAppPath + LangPath + "ESD0700_products_dda.jsp");
									flexLog("New LN Product");
								} catch (Exception ex) {
									flexLog("Error: " + ex);
								}

								break;
							case 4 : //Savings
								try {
									userPO.setHeader3(super.webAppPath + LangPath + "ESD0700_products_savingacct.jsp");
									flexLog("New LN Product");
								} catch (Exception ex) {
									flexLog("Error: " + ex);
								}
							
								break;
							case 10 :
								try {
									userPO.setHeader3(super.webAppPath + LangPath + "ESD0700_products_loans.jsp");
									flexLog("New LN Product");
								} catch (Exception ex) {
									flexLog("Error: " + ex);
								}
								break;
							case 50 :
							case 51 : // Collections
								try {
									userPO.setHeader3(super.webAppPath + LangPath + "ESD0700_products_collections.jsp");
									flexLog("New LN Product");
								} catch (Exception ex) {
									flexLog("Error: " + ex);
								}
							
								break;
							case 13 : // Inv 
								try {
									userPO.setHeader3(super.webAppPath + LangPath + "ESD0700_products_investments.jsp");
									flexLog("New LN Product");
								} catch (Exception ex) {
									flexLog("Error: " + ex);
								}
							
								break;
							case 40 :
							case 41 : // LC
							case 42 :
							case 43 :
								try {
									userPO.setHeader3(super.webAppPath + LangPath + "ESD0700_products_lettersofcredit.jsp");
									flexLog("New LN Product");
								} catch (Exception ex) {
									flexLog("Error: " + ex);
								}
							
								break;
							case 19 : // Proy 
								try {
									userPO.setHeader3(super.webAppPath + LangPath + "ESD0700_products_controlproyects.jsp");
									flexLog("New LN Product");
								} catch (Exception ex) {
									flexLog("Error: " + ex);
								}
							
								break;
							case 94 : // Credit Cards
								try {
									userPO.setHeader3(super.webAppPath + LangPath + "ESD0700_products_creditcards.jsp");
									flexLog("New LN Product");
								} catch (Exception ex) {
									flexLog("Error: " + ex);
								}
							
								break;
							case 96 : // Bonds
							case 97 : 
							case 98 : 
							case 99 : 
								try {
									userPO.setHeader3(super.webAppPath + LangPath + "ESD0700_products_portfolio.jsp");
									flexLog("New LN Product");
								} catch (Exception ex) {
									flexLog("Error: " + ex);
								}
							
								break;
							default :
								}


						ses.setAttribute("userPO", userPO);
						procReqProductDetails(req, res);


					} catch (Exception e) {
						flexLog("Exception calling page " + e);
					}
				} else
					flexLog("Message " + newmessage.getFormatName() + " received.");


		} catch (Exception e) {
			e.printStackTrace();
			flexLog("Error: " + e);
			throw new RuntimeException("Socket Communication Error");
		}


	}
	/**
	 * This method was created by Orestes Garcia.
	 * @param request HttpServletRequest
	 * @param response HttpServletResponse
	 */
	protected void procReqMaint(
		MessageContext mc,
		ESS0030DSMessage user,
		HttpServletRequest req,
		HttpServletResponse res,
		HttpSession ses) {
		MessageRecord newmessage = null;
		ESD070001Message msgProd = null;
		ELEERRMessage msgError = null;
		UserPos userPO = null;


		try {
			msgError = new datapro.eibs.beans.ELEERRMessage();
			userPO = new datapro.eibs.beans.UserPos();
		} catch (Exception ex) {
			flexLog("Error: " + ex);
		}


		int appCode = 0;
		try {
			appCode = Integer.parseInt(req.getParameter("appcode"));
		} catch (Exception e) {
		}
		String bank = req.getParameter("bank");
		String prodCode = req.getParameter("PROD_CODE");
		String typeCode = req.getParameter("typecode");
		String generic = req.getParameter("generic");
		String title = req.getParameter("title");
		String accnum = req.getParameter("accnum");


		String opCode = "0002";  // Read


		// Send Initial data
		try {
			msgProd = (ESD070001Message) mc.getMessageRecord("ESD070001");
			msgProd.setH01USERID(user.getH01USR());
			msgProd.setH01PROGRM("ESD0711");
			msgProd.setH01TIMSYS(getTimeStamp());
			msgProd.setH01SCRCOD("01");
			msgProd.setH01OPECOD(opCode);
			msgProd.setE01APCTYP(typeCode);
			msgProd.setE01APCCDE(prodCode);
			msgProd.setE01APCBNK(bank);
			msgProd.send();
			msgProd.destroy();
		} catch (Exception e) {
			e.printStackTrace();
			flexLog("Error: " + e);
			throw new RuntimeException("Socket Communication Error");
		}


		// Receive Error Message
		try {
			newmessage = mc.receiveMessage();


			if (newmessage.getFormatName().equals("ELEERR")) {
				msgError = (ELEERRMessage) newmessage;
				showERROR(msgError);


				flexLog("Putting java beans into the session");
				ses.setAttribute("error", msgError);


				procReqProductDetails(req, res);
			} else
				if (newmessage.getFormatName().equals("ESD070001")) {
					try {
						msgProd = new datapro.eibs.beans.ESD070001Message();
					} catch (Exception ex) {
						flexLog("Error: " + ex);
					}


					msgProd = (ESD070001Message) newmessage;


					userPO.setOption("PRODUCT");
					userPO.setPurpose("MAINTENANCE");
					userPO.setHeader2("DO_MAINT");


					flexLog("Putting java beans into the session");
					ses.setAttribute("prd", msgProd);


					try {


						switch (appCode) {
							case 11 :
							case 12 : // CD
							case 14 :
							case 15 :
								try {
									userPO.setHeader3(super.webAppPath + LangPath + "ESD0700_products_tds.jsp");
									flexLog("New LN Product");
								} catch (Exception ex) {
									flexLog("Error: " + ex);
								}
							
								break;
							case 1 : // DDA Accounts
							case 2 :
							case 3 :
								try {
									userPO.setHeader3(super.webAppPath + LangPath + "ESD0700_products_dda.jsp");
									flexLog("New LN Product");
								} catch (Exception ex) {
									flexLog("Error: " + ex);
								}
								break;
							case 4 : // Savings
								try {
									userPO.setHeader3(super.webAppPath + LangPath + "ESD0700_products_savingacct.jsp");
									flexLog("New LN Product");
								} catch (Exception ex) {
									flexLog("Error: " + ex);
								}
								break;
							case 10 :
								try {
									userPO.setHeader3(super.webAppPath + LangPath + "ESD0700_products_loans.jsp");
									flexLog("New LN Product");
								} catch (Exception ex) {
									flexLog("Error: " + ex);
								}
								break;
							case 50 :
							case 51 : // Collections
								try {
									userPO.setHeader3(super.webAppPath + LangPath + "ESD0700_products_collections.jsp");
									flexLog("New LN Product");
								} catch (Exception ex) {
									flexLog("Error: " + ex);
								}
								
								break;
							case 13 : // Inv 
								try {
									userPO.setHeader3(super.webAppPath + LangPath + "ESD0700_products_investments.jsp");
									flexLog("New LN Product");
								} catch (Exception ex) {
									flexLog("Error: " + ex);
								}
							
								break;
							case 40 :
							case 41 : // LC
							case 42 :
							case 43 :
								try {
									userPO.setHeader3(super.webAppPath + LangPath + "ESD0700_products_lettersofcredit.jsp");
									flexLog("New LN Product");
								} catch (Exception ex) {
									flexLog("Error: " + ex);
								}
							
								break;
							case 19 : // Proy 
								try {
									userPO.setHeader3(super.webAppPath + LangPath + "ESD0700_products_controlproyects.jsp");
									flexLog("New LN Product");
								} catch (Exception ex) {
									flexLog("Error: " + ex);
								}
								
								break;
							case 94 : // credit cards 
								try {
									userPO.setHeader3(super.webAppPath + LangPath + "ESD0700_products_creditcards.jsp");
									flexLog("New LN Product");
								} catch (Exception ex) {
									flexLog("Error: " + ex);
								}
							
								break;
							case 96 : // Bonds
							case 97 : 
							case 98 : 
							case 99 : 
								try {
									userPO.setHeader3(super.webAppPath + LangPath + "ESD0700_products_portfolio.jsp");
									flexLog("New LN Product");
								} catch (Exception ex) {
									flexLog("Error: " + ex);
								}
							
								break;
																
							default :
								}


						ses.setAttribute("userPO", userPO);
						procReqProductDetails(req, res);


					} catch (Exception e) {
						flexLog("Exception calling page " + e);
					}
				} else
					flexLog("Message " + newmessage.getFormatName() + " received.");


		} catch (Exception e) {
			e.printStackTrace();
			flexLog("Error: " + e);
			throw new RuntimeException("Socket Communication Error");
		}


	}
	/**
	 * This method was created by Orestes Garcia.
	 * @param request HttpServletRequest
	 * @param response HttpServletResponse
	 */
	protected void procReqDel(
		MessageContext mc,
		ESS0030DSMessage user,
		HttpServletRequest req,
		HttpServletResponse res,
		HttpSession ses) {
		MessageRecord newmessage = null;
		ESD070001Message msgProd = null;
		ELEERRMessage msgError = null;
		UserPos userPO = null;


		try {
			msgError = new datapro.eibs.beans.ELEERRMessage();
			userPO = new datapro.eibs.beans.UserPos();
		} catch (Exception ex) {
			flexLog("Error: " + ex);
		}


		int appCode = 0;
		try {
			appCode = Integer.parseInt(req.getParameter("appcode"));
		} catch (Exception e) {
		}
		String bank = req.getParameter("bank");
		String prodCode = req.getParameter("PROD_CODE");
		String typeCode = req.getParameter("typecode");
		String generic = req.getParameter("generic");
		String title = req.getParameter("title");
		String accnum = req.getParameter("accnum");


		String opCode = "0009";  // Delete


		// Send Initial data
		try {
			msgProd = (ESD070001Message) mc.getMessageRecord("ESD070001");
			msgProd.setH01USERID(user.getH01USR());
			msgProd.setH01PROGRM("ESD0711");
			msgProd.setH01TIMSYS(getTimeStamp());
			msgProd.setH01SCRCOD("01");
			msgProd.setH01OPECOD(opCode);
			msgProd.setE01APCTYP(typeCode);
			msgProd.setE01APCCDE(prodCode);
			msgProd.setE01APCBNK(bank);
			msgProd.send();
			msgProd.destroy();
		} catch (Exception e) {
			e.printStackTrace();
			flexLog("Error: " + e);
			throw new RuntimeException("Socket Communication Error");
		}


		// Receive Error Message
		try {

			newmessage = mc.receiveMessage();

			if (newmessage.getFormatName().equals("ELEERR")) {
				msgError = (ELEERRMessage) newmessage;
				flexLog("Putting java beans into the session");
				ses.setAttribute("error", msgError);
			} else
				flexLog("Message " + newmessage.getFormatName() + " received.");

			procReqProductDetails(req, res);

		} catch (Exception e) {
			e.printStackTrace();
			flexLog("Error: " + e);
			throw new RuntimeException("Socket Communication Error");
		}


	}
	/**
	 * This method was created by Orestes Garcia.
	 * @param request HttpServletRequest
	 * @param response HttpServletResponse
	 */
	protected void procReqProductDetails(
		HttpServletRequest req,
		HttpServletResponse res) {

		try {

			String appCode = req.getParameter("appcode");
			String bank = req.getParameter("bank");
			String prodCode = req.getParameter("PROD_CODE");
			String typeCode = req.getParameter("typecode");
			String generic = req.getParameter("generic");
			String title = req.getParameter("title");

			String firstLink =
				LangPath
					+ "ESD0700_products_detail.jsp?bank="
					+ bank
					+ "&appcode="
					+ appCode
					+ "&typecode="
					+ typeCode
					+ "&generic="
					+ generic
					+ "&title="
					+ title
					+ "&sel="
					+ prodCode;
			flexLog("About to call Page: " + firstLink);
			res.sendRedirect(super.srctx + firstLink);

		} catch (Exception e) {
			e.printStackTrace();
			flexLog("Error: " + e);
			throw new RuntimeException("Socket Communication Error");
		}

	}
	/**
	 * This method was created by Orestes Garcia.
	 * @param request HttpServletRequest
	 * @param response HttpServletResponse
	 */
	protected void procReqProducts(
		MessageContext mc,
		ESS0030DSMessage user,
		HttpServletRequest req,
		HttpServletResponse res,
		HttpSession ses) {

		MessageRecord newmessage = null;
	ESD071101Message msgTree = null;
	ELEERRMessage msgError = null;
	UserPos userPO = null;

	//JBParseTree dataTree = new JBParseTree();
	JBObjList prodList = null;
	JBObjList subProdList = null;
	JBObjList elemList = null;	
	try {
		msgError = new datapro.eibs.beans.ELEERRMessage();
		userPO = new datapro.eibs.beans.UserPos();
  	} 
	catch (Exception ex) {
		flexLog("Error: " + ex); 
  	}

	String outParams = "";
	String firstLink = "";

	String appCode = "";
	String genCode = "";
	try {
		String code = req.getParameter("TYPE");
		appCode = code.equals("ALL") ? "" : code;
		//mod EMAT 11/16/2001
		//parameter GENERIC = 'U' debts
		genCode = req.getParameter("GENERIC");
		if (genCode == null) genCode = "";
	}
	catch (Exception e) {
		flexLog("Error getting requesting parameters");
	}
	
	// Send Request
  	try {
		msgTree = (ESD071101Message)mc.getMessageRecord("ESD071101");
	 	msgTree.setE01USERID(user.getH01USR());
	 	msgTree.setE01SELACD(appCode);
	 	//mod EMAT 11/16/2001
	 	//parameter GENERIC = 'U' debts
	 	msgTree.setE01GENERI(genCode);
		msgTree.send();	
	 	msgTree.destroy();
	}		
	catch (Exception e)	{
		e.printStackTrace();
		flexLog("Error: " + e);
	  	throw new RuntimeException("Socket Communication Error");
	}
		
	// Receiving
	try
	{
	  flexLog("About to receive data");
	  newmessage = mc.receiveMessage();
	  
	  if (newmessage.getFormatName().equals("ESD071101")) {
		msgTree = (ESD071101Message)newmessage; 
		
		if (msgTree.getE01RECTYP().equals("0")) {

			String titleFolder = msgTree.getE01DESCRI().trim();
			prodList = new JBObjList();
			subProdList = new JBObjList();
			elemList = new JBObjList();
			
			ses.setAttribute("title",titleFolder);
			boolean firstTime = false;
			boolean firstfolder = true;
			while (true) {
				newmessage = mc.receiveMessage();
				msgTree = (ESD071101Message)newmessage; 
				
				if ( msgTree.getE01RECTYP().equals("1") ) {
					
					prodList.addRow(msgTree);
/*					if (!elemList.getNoResult()) {
					  subProdList.addRow(elemList);
					  elemList = new JBObjList();
					}
*/
					if (firstfolder) {
					   firstTime = true;
					   firstfolder = false;
					}else {
						  subProdList.addRow(elemList);
						  elemList = new JBObjList();
					}
					
				}
				else if ( msgTree.getE01RECTYP().equals("2") ) {
					if (firstTime) {
						firstLink = super.webAppPath + LangPath + "ESD0700_products_detail.jsp?appcode=" + msgTree.getE01APCACD().trim() + "&typecode=" + msgTree.getE01APCTYP().trim() + "&generic=" + msgTree.getE01GENERI().trim() + "&title=" + msgTree.getE01DESCRI().trim() + "&bank=" + msgTree.getE01APCBNK();
						firstTime = false;
					}
					elemList.addRow(msgTree);

				}
				else {
					if (!elemList.getNoResult()) {
					  subProdList.addRow(elemList);
					  elemList = new JBObjList();
					}
					if ( msgTree.getE01RECTYP().equals("*") ) break;
				}
			}
			
			
		  }
	  }
	  else
		flexLog("Message " + newmessage.getFormatName() + " received.");

  	
	  msgError.setERRNUM("0");
	  userPO.setHeader2("");
	  userPO.setType(genCode);
	  userPO.setPurpose("MAINTENANCE");
	  //outParams = dataTree.getTree();
	  ses.setAttribute("error", msgError);
  	  ses.setAttribute("userPO", userPO);
	  ses.setAttribute("prodList", prodList);
	  ses.setAttribute("subProdList", subProdList);

	  res.setContentType("text/html"); 
	  res.setHeader("Pragma", "No-cache");
	  res.setDateHeader ("Expires", 0);
	  res.setHeader("Cache-Control", "no-cache");
	  PrintWriter out = res.getWriter();
	  printProdFrame(out, firstLink, LangPath);

	}
	catch (Exception e)	{
		e.printStackTrace();
		flexLog("Error: " + e);
	  	throw new RuntimeException("Socket Communication Error");
	}

	}
	/**
	 * This method was created by Orestes Garcia.
	 * @param request HttpServletRequest
	 * @param response HttpServletResponse
	 */
	public void service(HttpServletRequest req, HttpServletResponse res)
		throws ServletException, IOException {

		Socket s = null;
		MessageContext mc = null;

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

			int screen = R_P_SHOW;

			try {
				msgUser = (datapro.eibs.beans.ESS0030DSMessage) session.getAttribute("currUser");

				// Here we should get the path from the user profile
				LangPath = super.rootPath + msgUser.getE01LAN() + "/";

				try {
					flexLog("Opennig Socket Connection ");
					s = new Socket(super.hostIP, getInitSocket(req) + 1);
					s.setSoTimeout(super.sckTimeOut);
					mc =
						new MessageContext(
							new DataInputStream(new BufferedInputStream(s.getInputStream())),
							new DataOutputStream(new BufferedOutputStream(s.getOutputStream())),
							"datapro.eibs.beans");
				
				try {
					screen = Integer.parseInt(req.getParameter("SCREEN"));
				} catch (Exception e) {
					flexLog("Screen set to default value");
				}

				switch (screen) {
					case R_P_SHOW :
						procReqProducts(mc, msgUser, req, res, session);
						break;
					case R_P_NEW :
						procReqNew(mc, msgUser, req, res, session);
						break;
					case R_P_MAINT :
						procReqMaint(mc, msgUser, req, res, session);
						break;
					case R_P_DEL :
						procReqDel(mc, msgUser, req, res, session);
						break;
					case A_P_NEW_MAINT :
						procActionNewMaint(mc, msgUser, req, res, session);
						break;
					default :
						res.sendRedirect(super.srctx + LangPath + super.devPage);
						break;
				}

				} catch (Exception e) {
					e.printStackTrace();
					int sck = getInitSocket(req) + 1;
					flexLog("Socket not Open(Port " + sck + "). Error: " + e);
					res.sendRedirect(super.srctx + LangPath + super.sckNotOpenPage);
					//return;
			} finally {
					s.close();
			}

			} catch (Exception e) {
				flexLog("Error: " + e);
				res.sendRedirect(super.srctx + LangPath + super.sckNotRespondPage);
			}

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
	protected void showESD071101(ESD071101Message m) {
		if (logType != NONE) {

			flexLog("ESD071101 received.");

			flexLog("User ID 		= " + m.getE01USERID());
			flexLog("Product Code 	= " + m.getE01SELACD());
			flexLog("Type of Record = " + m.getE01RECTYP());
			flexLog("Generic 		= " + m.getE01GENERI());
			flexLog("Products Code  = " + m.getE01APCACD());
			flexLog("Produts Type   = " + m.getE01APCTYP());
			flexLog("Description 	= " + m.getE01DESCRI());

		}
	}

	
}