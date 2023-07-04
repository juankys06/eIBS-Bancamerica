package datapro.eibs.products;

/**
 * Insert the type's description here.
 * Creation date: (8/31/2000 11:28:15 AM)
 * @author: Orestes Garcia
 */
import javax.servlet.http.HttpSession;

import datapro.eibs.beans.UserPos;
import datapro.eibs.master.Util;

public class JOActionRedirect {
	private int option;
	private int appCode;
	HttpSession session = null;
	private String account;
	private String number;
	private String path;
	/**
	 * JOActionRedirect constructor comment.
	 */
	public JOActionRedirect(
		int opt,
		int app,
		String acc,
		String lp,
		HttpSession ses) {
		super();
		option = opt;
		appCode = app;
		account = acc;
		number = "0";
		session = ses;
		path = lp;
	}
	/**
	 * JOActionRedirect constructor comment.
	 */
	public JOActionRedirect(int app, String acc, String lp, HttpSession ses) {
		super();
		option = 1; // Account Inquiry by default
		appCode = app;
		account = acc;
		number = "0";
		session = ses;
		path = lp;
	}
	/**
	 * JOActionRedirect constructor comment.
	 */
	public JOActionRedirect(
		String num,
		int opt,
		int app,
		String acc,
		String lp,
		HttpSession ses) {
		super();
		option = opt;
		appCode = app;
		account = acc;
		number = num;
		session = ses;
		path = lp;
	}
	/**
	 * JOActionRedirect constructor comment.
	 */
	public JOActionRedirect(
		String num,
		int app,
		String acc,
		String lp,
		HttpSession ses) {
		super();
		option = 1; // Account Inquiry by default
		appCode = app;
		account = acc;
		number = num;
		session = ses;
		path = lp;
	}

    
	/**
	 * Insert the method's description here.
	 * Creation date: (8/31/2000 11:39:40 AM)
	 * @return java.lang.String
	 */
	public String action() {

		String ret = "";
		UserPos userPO = (UserPos) session.getAttribute("userPO");

		if (number == null) {
			number = "Z";
		}

		switch (option) {
			case 1 : // Account Inquiry
				switch (appCode) {
					case 11 :
					case 12 :
					case 13 :
					case 14 :
					case 15 :
					case 95 : // CD
						try {
							userPO.setOption("CD");
							userPO.setPurpose("INQUIRY");
							session.setAttribute("userPO", userPO);
							ret =
								"/servlet/datapro.eibs.products.JSEDL0130I?SCREEN=600&E01DEAACC=" + account;
						} catch (Exception ex) {
							System.out.println("Error: " + ex);
						}
						break;
					case 1 : // Retail Accounts
					case 2 :
					case 3 :
					case 4 : // Saving Accounts
					case 5 : //Call Account
						try {
							userPO.setOption("RT");
							userPO.setPurpose("INQUIRY");
							session.setAttribute("userPO", userPO);
							ret =
								"/servlet/datapro.eibs.products.JSEDD0000I?SCREEN=1400&E01ACMACC=" + account;
						} catch (Exception ex) {
							System.out.println("Error: " + ex);
						}
						break;
					case 10 : // Loans Accounts
						try {
							userPO.setOption("LN");
							userPO.setPurpose("INQUIRY");
							session.setAttribute("userPO", userPO);
							ret =
								"/servlet/datapro.eibs.products.JSEDL0160?SCREEN=200&E01DEAACC=" + account;
						} catch (Exception ex) {
							System.out.println("Error: " + ex);
						}
						break;
					case 40 :
					case 41 :
					case 42 : // Letter of Credit
						try {
							userPO.setOption("LC");
							userPO.setPurpose("INQUIRY");
							session.setAttribute("userPO", userPO);
							ret = "/servlet/datapro.eibs.trade.JSELC0450?SCREEN=200&E01LCMACC=" + account;
						} catch (Exception ex) {
							System.out.println("Error: " + ex);
						}
						break;
					case 31 :
					case 32 :
					case 33 : // Foreign Exchange
					case 34 : 	
						try {
							userPO.setOption("FX");
							userPO.setPurpose("INQUIRY");
							session.setAttribute("userPO", userPO);
							ret = "/servlet/datapro.eibs.forex.JSEFE0160?SCREEN=40&E01FEMACC=" + account;
						} catch (Exception ex) {
							System.out.println("Error: " + ex);
						}
						break;
					case 96 : // Trade Tickets
					case 97 :
					case 98 :
					case 99 :
						try {
							userPO.setOption("IV");
							userPO.setPurpose("INQUIRY");
							session.setAttribute("userPO", userPO);
							ret = "/servlet/datapro.eibs.invest.JSEIE0130I?SCREEN=400&CODE=" + account;
						} catch (Exception ex) {
							System.out.println("Error: " + ex);
						}
						break;
					case 91 : // None Account Collateral
						try {
							userPO.setOption("COL");
							userPO.setPurpose("INQUIRY");
							session.setAttribute("userPO", userPO);
							ret = "/servlet/datapro.eibs.client.JSERA0100?SCREEN=200&REF=" + account;
						} catch (Exception ex) {
							System.out.println("Error: " + ex);
						}
						break;
		
					case 90 : // Credit Line
						try {
							userPO.setOption("CL");
							userPO.setPurpose("INQUIRY");
							String id = Util.formatID(account, number);
							userPO.setCusNum(account);
							userPO.setCreditLineNum(number);
							if (userPO.getCusName() == null)
								userPO.setCusName("");
							if (userPO.getHeader1() == null)
								userPO.setHeader1("");
							userPO.setIdentifier(id);
							session.setAttribute("userPO", userPO);
							ret = "/servlet/datapro.eibs.credit.JSELN0060?SCREEN=1";
						} catch (Exception ex) {
						}
						break;
					default :
						ret = path + "Under_construction.jsp";
				}
				break;
			case 2 : // Account Statement
				switch (appCode) {
					case 1 : // Retail Accounts
					case 2 :
					case 3 :
					case 4 : // Saving Accounts
					case 5 : //Call Account
						try {
							userPO.setOption("RT");
							userPO.setPurpose("STATEMENT");
							session.setAttribute("userPO", userPO);
							ret = "/servlet/datapro.eibs.client.JSECIF030?SCREEN=200&E01ACMACC=" + account;
						} catch (Exception ex) {
							System.out.println("Error: " + ex);
						}
						break;
					case 10 : // Loans
						try {
							userPO.setOption("LN");
							userPO.setPurpose("STATEMENT");
							session.setAttribute("userPO", userPO);
							ret = "/servlet/datapro.eibs.client.JSEDL0300L?SCREEN=200&E01DEAACC=" + account;
						} catch (Exception ex) {
							System.out.println("Error: " + ex);
						}
						break;
					case 11 :
					case 12 :
					case 13 :
					case 15 : // CD
					case 95 :
						try {
							userPO.setOption("CD");
							userPO.setPurpose("STATEMENT");
							session.setAttribute("userPO", userPO);
							ret = "/servlet/datapro.eibs.client.JSEDL0300?SCREEN=200&E01DEAACC=" + account;
						} catch (Exception ex) {
							System.out.println("Error: " + ex);
						}
						break;
					default :
						ret = path + "Under_construction.jsp";
				}
				break;
			case 3 : // Averages
				switch (appCode) {
					case 1 : // Retail Accounts
					case 2 :
					case 3 :
					case 4 : // Saving Accounts
					case 5 : //Call Account
					case 10 : // Loans
					case 11 :
					case 12 :
					case 13 :
					case 15 : // CD
					case 95 : //Financial Blocks
					case 40 :
					case 41 :
					case 42 : // Letter of Credit
					case 31 : //Spot
					case 32 : //Forward
					case 33 : //Option
				    case 34 : //NDF
						try {
							userPO.setOption("AVERAGE");
							userPO.setPurpose("");
							session.setAttribute("userPO", userPO);
							ret = "/servlet/datapro.eibs.client.JSECIF040?SCREEN=200&E01ACCNUM=" + account;
						} catch (Exception ex) {
							System.out.println("Error: " + ex);
						}
						break;
					default :
						ret = path + "Under_construction.jsp";
				}
				break;
			case 4 : // Approval Inquiry
				switch (appCode) {
					case 0 : // Clients
						try {
							userPO.setOption("CLIENT");
							userPO.setPurpose("APPROVAL_INQ");
							session.setAttribute("userPO", userPO);
							ret = "/servlet/datapro.eibs.client.JSESD0080A?SCREEN=600&E01CUN=" + account;
						} catch (Exception ex) {
							System.out.println("Error: " + ex);
						}
						break;
					case 6 : //Paying & Receiving
						try {
							userPO.setOption("PR");
							userPO.setPurpose("APPROVAL_INQ");
							session.setAttribute("userPO", userPO);
							ret = "/servlet/datapro.eibs.approval.JSEPR1080A?SCREEN=100&REFNUM=" + account;
						} catch (Exception ex) {
							System.out.println("Error: " + ex);
						}
						break;
					case 13 : // CP
						try {
							if (number.equalsIgnoreCase("") || number.equalsIgnoreCase("1")) {
								userPO.setOption("CP");
								userPO.setPurpose("APPROVAL_INQ");
								session.setAttribute("userPO", userPO);
								ret = "/servlet/datapro.eibs.products.JSEDL0105A?SCREEN=400&E01DEAACC=" + account;								
							} else {
								userPO.setOption("CP");
								userPO.setPurpose("APPROVAL_INQ");
								session.setAttribute("userPO", userPO);
								ret = "/servlet/datapro.eibs.forex.JSEDL0108B?SCREEN=200&E02DEAACC=" + account;								
							}
						} catch (Exception ex) {
							System.out.println("Error: " + ex);
						}					
						break;
						
					case 11 :
					case 12 :
					case 14 :
					case 15 : // CD
						try {
							if (number.equalsIgnoreCase("P")) {
								userPO.setOption("CD_PAYMENT");
								userPO.setPurpose("APPROVAL_INQ");
								session.setAttribute("userPO", userPO);
								ret =
									"/servlet/datapro.eibs.products.JSEDL0130A?SCREEN=11&E01DEAACC=" + account;
							} else
								if (number.equalsIgnoreCase("T")) {
									userPO.setOption("CD_TRANSACTION");
									userPO.setPurpose("APPROVAL_INQ");
									session.setAttribute("userPO", userPO);
									ret = "/servlet/datapro.eibs.products.JSEDL0130A?SCREEN=5&E01DEAACC=" + account;
								} else
									if (number.equalsIgnoreCase("K")) {
										userPO.setOption("TREASURY");
										userPO.setPurpose("APPROVAL_INQ");
										session.setAttribute("userPO", userPO);
										ret =
											"/servlet/datapro.eibs.products.JSEDL0130A?SCREEN=500&E01DEAACC=" + account;
									} else {
										userPO.setOption("CD");
										userPO.setPurpose("APPROVAL_INQ");
										session.setAttribute("userPO", userPO);
										ret =
											"/servlet/datapro.eibs.products.JSEDL0130A?SCREEN=500&E01DEAACC=" + account;
									}
						} catch (Exception ex) {
							System.out.println("Error: " + ex);
						}
						break;

					case 1 : // Retail Accounts
					case 2 :
					case 3 :
					case 5 : //Call Account
						try {
							if (number.equalsIgnoreCase("C")) {
								userPO.setOption("RT_CANCEL");
								userPO.setPurpose("APPROVAL_INQ");
								session.setAttribute("userPO", userPO);
								ret = "/servlet/datapro.eibs.products.JSEDD1150A?SCREEN=1&E01ACMACC=" + account;
							} else if (number.equals("1")
									|| number.equals("2")
									|| number.equals("3")
									|| number.equals("4")
									|| number.equals("5")) {
									userPO.setOption("RT");
									userPO.setPurpose("APPROVAL_INQ");
									session.setAttribute("userPO", userPO);
									ret =
										"/servlet/datapro.eibs.products.JSEDD0430?SCREEN=400&ACCNUM="
											+ account
											+ "&opt="
											+ number;
							} else {
									userPO.setOption("RT");
									userPO.setPurpose("APPROVAL_INQ");
									session.setAttribute("userPO", userPO);
									ret =
										"/servlet/datapro.eibs.products.JSEDD0000A?SCREEN=400&E01ACMACC=" + account;
							}
						} catch (Exception ex) {
							System.out.println("Error: " + ex);
						}
						break;
					case 4 : // Saving Accounts
						try {
							if (number.equalsIgnoreCase("C")) {
								userPO.setOption("SV_CANCEL");
								userPO.setPurpose("APPROVAL_INQ");
								session.setAttribute("userPO", userPO);
								ret = "/servlet/datapro.eibs.products.JSEDD1150A?SCREEN=1&E01ACMACC=" + account;
							} else {
								userPO.setOption("SV");
								userPO.setPurpose("APPROVAL_INQ");
								session.setAttribute("userPO", userPO);
								ret =
									"/servlet/datapro.eibs.products.JSEDD0000A?SCREEN=400&E01ACMACC=" + account;
							}
						} catch (Exception ex) {
							System.out.println("Error: " + ex);
						}
						break;
					case 10 : // Loans Accounts
						try {
							if (number.equalsIgnoreCase("P")) {
								userPO.setOption("LN_PAYMENT");
								userPO.setPurpose("APPROVAL_INQ");
								session.setAttribute("userPO", userPO);
								ret = "/servlet/datapro.eibs.products.JSEDL0950?SCREEN=27&E01DEAACC=" + account;
							} else
								if (number.equalsIgnoreCase("T")) {
									userPO.setOption("LN_TRANSACTION");
									userPO.setPurpose("APPROVAL_INQ");
									session.setAttribute("userPO", userPO);
									ret =
										"/servlet/datapro.eibs.products.JSEDL0152A?SCREEN=800&E01DEAACC=" + account;
								} else
									if (number.equalsIgnoreCase("Q")) {
										userPO.setOption("LN_RENEWAL");
										userPO.setPurpose("APPROVAL_INQ");
										session.setAttribute("userPO", userPO);
										ret =
											"/servlet/datapro.eibs.products.JSEDL0910?SCREEN=200&E01DEAACC=" + account;
									} else
										if (number.equalsIgnoreCase("X")) {
											userPO.setOption("LN_STATUS");
											userPO.setPurpose("APPROVAL_INQ");
											session.setAttribute("userPO", userPO);
											ret =
												"/servlet/datapro.eibs.products.JSEDL0135?SCREEN=300&E01DEAACC=" + account;
										} else {
											userPO.setOption("LN");
											userPO.setPurpose("APPROVAL_INQ");
											session.setAttribute("userPO", userPO);
											//emat (drafts)
											if (number.equals("G")) {
												ret =
													"/servlet/datapro.eibs.products.JSEDL0800?SCREEN=1200&E01DEAACC="
														+ account
														+ "&H01FLGWK2="
														+ number;
											} else {
												ret =
													"/servlet/datapro.eibs.products.JSEDL0150A?SCREEN=400&E01DEAACC=" + account;
											}
										}
						} catch (Exception ex) {
							System.out.println("Error: " + ex);
						}
						break;
					case 30 : // Foreign Exchange by Teller
						try {
							userPO.setOption("FE");
							userPO.setPurpose("APPROVAL_INQ");
							session.setAttribute("userPO", userPO);
							ret = "/servlet/datapro.eibs.forex.JSEFE0010?SCREEN=1600&E01EFEACC=" + account;
						} catch (Exception ex) {
							System.out.println("Error: " + ex);
						}
						break;
					case 31 : // Spot,Forward,Swap,Option,NDF
					case 32 :
					case 33 :
					case 34 :
						try {
							userPO.setOption("FX");
							userPO.setPurpose("APPROVAL_INQ");
							session.setAttribute("userPO", userPO);
							ret = "/servlet/datapro.eibs.forex.JSEFE0140A?SCREEN=40&E01WKFACC=" + account;
						} catch (Exception ex) {
							System.out.println("Error: " + ex);
						}
						break;

					case 40 :
					case 41 :
					case 42 : // Letter of Credit
						try {
							userPO.setOption("LC");
							userPO.setPurpose("APPROVAL_INQ");
							session.setAttribute("userPO", userPO);
							ret = path + "Under_construction.jsp";
						} catch (Exception ex) {
							System.out.println("Error: " + ex);
						}
						break;
					case 43 :
						try {
							if (number.equalsIgnoreCase("P") || number.equalsIgnoreCase("C")) {
							String bgOpt = "";
							if (number.equalsIgnoreCase("P")) bgOpt="1"; else bgOpt="2";
							userPO.setOption(bgOpt);
							userPO.setPurpose("APPROVAL_INQ");
							session.setAttribute("userPO", userPO);
							ret = "/servlet/datapro.eibs.bolgaran.JSELC5560?SCREEN=2&E01LCMACC=" + account + "&OPT=" + bgOpt;
							}
							else {
								userPO.setOption("BG");
								userPO.setPurpose("APPROVAL_INQ");
								session.setAttribute("userPO", userPO);
								ret = "/servlet/datapro.eibs.bolgaran.JSELC5000?SCREEN=7&E01LCMACC=" + account;
							}		
				  		} 
						catch (Exception ex) {
							System.out.println("Error: " + ex); 
				  		}
						break;
					case 87 : // Official Check
						try {
							userPO.setOption("OC");
							userPO.setPurpose("APPROVAL_INQ");
							session.setAttribute("userPO", userPO);
							ret =
								"/servlet/datapro.eibs.products.JSEOF0115IA?SCREEN=400&ACCNUM="
									+ account
									+ "&Currency="
									+ number;
						} catch (Exception ex) {
							System.out.println("Error: " + ex);
						}
						break;

					case 90 : // Line of Credit
						try {
							userPO.setOption("CL");
							userPO.setPurpose("APPROVAL_INQ");
							session.setAttribute("userPO", userPO);
							ret =
								"/servlet/datapro.eibs.credit.JSELN0000A?SCREEN=200&CUSNUM="
									+ account
									+ "&LNENUM="
									+ number;
						} catch (Exception ex) {
							System.out.println("Error: " + ex);
						}
						break;
					case 91 : // Collaterals
						try {
							userPO.setOption("CO");
							userPO.setPurpose("APPROVAL_INQ");
							session.setAttribute("userPO", userPO);
							ret = "/servlet/datapro.eibs.client.JSERA0080A?SCREEN=200&REFNUM=" + account;
						} catch (Exception ex) {
							System.out.println("Error: " + ex);
						}
						break;
					case 93 : // Internal & External Transaction
						try {
							userPO.setOption("PR");
							userPO.setPurpose("APPROVAL_INQ");
							session.setAttribute("userPO", userPO);
							ret = "/servlet/datapro.eibs.approval.JSEPR1080A?SCREEN=1&REFNUM=" + account;
						} catch (Exception ex) {
							System.out.println("Error: " + ex);
						}
						break;
						
					// This is a shortcut to the card, the products for :
					// 1- Debit Cards are the Retail Accounts
					// 2- Credit Cards are the Credit Products
					case 84 : // Debit Cards
					case 92 : // 92 only for Banvalor, should be change.
						try {
							userPO.setOption("DC");
							userPO.setPurpose("APPROVAL_INQ");
							userPO.setAccNum(account);
							session.setAttribute("userPO", userPO);
							ret = "/servlet/datapro.eibs.products.JSECC0060A?SCREEN=3";
						} catch (Exception ex) {
							System.out.println("Error: " + ex);
						}
						break;
						
					// This is actually the Approval Inquiry of the Credit Product
					case 94 : // Credit Cards
						try {
							userPO.setOption("CC");
							userPO.setPurpose("APPROVAL_INQ");
							userPO.setAccNum(account);
							//userPO.setIdentifier(account);
							session.setAttribute("userPO", userPO);
							ret = "/servlet/datapro.eibs.products.JSECC0010A?SCREEN=3";
						} catch (Exception ex) {
							System.out.println("Error: " + ex);
						}
						break;
					case 95 : // Financial Blocks
						try {
							userPO.setOption("CD");
							userPO.setPurpose("APPROVAL_INQ");
							session.setAttribute("userPO", userPO);
							ret =
								"/servlet/datapro.eibs.products.JSEDL0130A?SCREEN=500&E01DEAACC=" + account;
						} catch (Exception ex) {
							System.out.println("Error: " + ex);
						}
						break;
					case 96 : // Trade Tickets
					case 97 :
					case 98 :
					case 99 :
						try {
							userPO.setOption("IV");
							userPO.setPurpose("APPROVAL_INQ");
							session.setAttribute("userPO", userPO);
							ret = "/servlet/datapro.eibs.invest.JSEIE0130A?SCREEN=400&CODE=" + account;
						} catch (Exception ex) {
							System.out.println("Error: " + ex);
						}
						break;
					case 102 : // FRA
						try {
							userPO.setOption("FRA");
							userPO.setPurpose("APPROVAL_INQ");
							session.setAttribute("userPO", userPO);
							ret = "/servlet/datapro.eibs.forex.JSEFE0140A?SCREEN=80&E01FRAACC=" + account;
						} catch (Exception ex) {
							System.out.println("Error: " + ex);
						}
						break;
					
					default :
						ret = path + "Under_construction.jsp";
				}
				break;

			case 5 : // Maintenance
				switch (appCode) {
					case 0 : // Client
						try {
							userPO.setOption("CLIENT");
							userPO.setPurpose("MAINTENANCE");
							session.setAttribute("userPO", userPO);
							ret = "/servlet/datapro.eibs.client.JSESD0080?SCREEN=500&E01CUN=" + account;
						} catch (Exception ex) {
							System.out.println("Error: " + ex);
						}
						break;
					case 1 : // Retail Accounts
					case 2 :
					case 3 :
					case 5 : //Call Account
						try {
							userPO.setOption("RT");
							userPO.setPurpose("MAINTENANCE");
							session.setAttribute("userPO", userPO);
							ret =
								"/servlet/datapro.eibs.products.JSEDD0000?SCREEN=400&E01ACMACC=" + account;
						} catch (Exception ex) {
							System.out.println("Error: " + ex);
						}
						break;
					case 4 : // Saving Accounts
						try {
							userPO.setOption("SV");
							userPO.setPurpose("MAINTENANCE");
							session.setAttribute("userPO", userPO);
							ret =
								"/servlet/datapro.eibs.products.JSEDD0000?SCREEN=800&E01ACMACC=" + account;
						} catch (Exception ex) {
							System.out.println("Error: " + ex);
						}
						break;
					case 10 : // Loans
						try {
							userPO.setOption("LN");
							userPO.setPurpose("MAINTENANCE");
							session.setAttribute("userPO", userPO);
							ret =
								"/servlet/datapro.eibs.products.JSEDL0150?SCREEN=400&E01DEAACC=" + account;
						} catch (Exception ex) {
							System.out.println("Error: " + ex);
						}
						break;
					case 11 :
					case 12 :
					case 15 : // CD
						try {
							userPO.setOption("CD");
							userPO.setPurpose("MAINTENANCE");
							session.setAttribute("userPO", userPO);
							ret =
								"/servlet/datapro.eibs.products.JSEDL0130?SCREEN=500&E01DEAACC=" + account;
						} catch (Exception ex) {
							System.out.println("Error: " + ex);
						}
						break;
					case 13 :
						try {
							userPO.setOption("13");
							userPO.setPurpose("MAINTENANCE");
							session.setAttribute("userPO", userPO);
							ret =
								"/servlet/datapro.eibs.products.JSEDL0105?SCREEN=400&E01DEAACC=" + account;
						} catch (Exception ex) {
							System.out.println("Error: " + ex);
						}
						break;
					case 30 : // Foreign Exchange by Teller
						try {
							userPO.setOption("FE");
							userPO.setPurpose("APPROVAL_INQ");
							session.setAttribute("userPO", userPO);
							ret = "/servlet/datapro.eibs.products.JSETL0510H?SCREEN=3&E01EFEACC=" + account;
						} catch (Exception ex) {
							System.out.println("Error: " + ex);
						}
						break;
					case 31 : // Spot,Forward,Swap,Option,NDF
					case 32 :
					case 33 :
					case 34 :
						try {
							userPO.setOption("FX");
							userPO.setPurpose("MAINTENANCE");
							session.setAttribute("userPO", userPO);
							ret = "/servlet/datapro.eibs.forex.JSEFE0140?SCREEN=40&E01WKFACC=" + account;
						} catch (Exception ex) {
							System.out.println("Error: " + ex);
						}
						break;

					case 90 : // Line of Credit
						try {
							userPO.setOption("CL");
							userPO.setPurpose("MAINTENANCE");
							session.setAttribute("userPO", userPO);
							ret =
								"/servlet/datapro.eibs.credit.JSELN0000?SCREEN=200&CUSNUM="
									+ account
									+ "&LNENUM="
									+ number;
						} catch (Exception ex) {
							System.out.println("Error: " + ex);
						}
						break;
					case 91 : // Garantia.
						try{							
							userPO.setPurpose("MAINTENANCE");
							session.setAttribute("userPO", userPO);
							ret = "/servlet/datapro.eibs.client.JSERA0011?SCREEN=200&REF=" + account;
						}catch(Exception e){
							System.out.println("Error: "+ e);
						}
						break;
					case 93 : //Internal & External Transaction
						try {
							userPO.setOption("PR");
							userPO.setPurpose("MAINTENANCE");
							session.setAttribute("userPO", userPO);
							ret = "/servlet/datapro.eibs.products.JSEPR0000?SCREEN=3&REFNUM=" + account;
						} catch (Exception ex) {
							System.out.println("Error: " + ex);
						}
						break;
					case 95 :
						try {
							userPO.setOption("BLK");
							userPO.setPurpose("MAINTENANCE");
							session.setAttribute("userPO", userPO);
							ret = "/servlet/datapro.eibs.forex.JSEDL130B?SCREEN=2000&E01DLSREF=" + account;
						} catch (Exception ex) {
							System.out.println("Error: " + ex);
						}
						break;
					case 80 : //Instruments
						try {
							userPO.setOption("IV");
							userPO.setPurpose("MAINTENANCE");
							session.setAttribute("userPO", userPO);
							ret = "/servlet/datapro.eibs.invest.JSEIE0050?SCREEN=400&CODE=" + account;
						} catch (Exception ex) {
							System.out.println("Error: " + ex);
						}
						break;	
					case 81 : //Portfolio
						try {
							userPO.setOption("IV");
							userPO.setPurpose("MAINTENANCE");
							session.setAttribute("userPO", userPO);
							ret = "/servlet/datapro.eibs.invest.JSEIE0010?SCREEN=200&E01PRFNUM=" + account;
						} catch (Exception ex) {
							System.out.println("Error: " + ex);
						}
						break;	
					case 88 : // Accounting Lots
						try {
							userPO.setOption("GL");
							userPO.setPurpose("MAINTENANCE");
							session.setAttribute("userPO", userPO);
							ret = "/servlet/datapro.eibs.products.JSEGL0035?SCREEN=200&E01BTHNUM=" + account;
						} catch (Exception ex) {
							System.out.println("Error: " + ex);
						}
						break;	
					case 89 : //Swift Messages
						try {
							userPO.setOption("SW");
							userPO.setPurpose("MAINTENANCE");
							session.setAttribute("userPO", userPO);
							ret = "/servlet/datapro.eibs.transfer.JSEWD0341F?SCREEN=3&REFNUM=" + account;
						} catch (Exception ex) {
							System.out.println("Error: " + ex);
						}
						break;
					case 96 : // Trade Tickets
					case 97 :
					case 98 :
					case 99 :
						try {
							userPO.setOption("IV");
							userPO.setPurpose("MAINTENANCE");
							session.setAttribute("userPO", userPO);
							ret = "/servlet/datapro.eibs.invest.JSEIE0130?SCREEN=400&CODE=" + account;
						} catch (Exception ex) {
							System.out.println("Error: " + ex);
						}
						break;
						
					default :
						ret = path + "Under_construction.jsp";
				}
				break;
			case 6 : // Approval
				switch (appCode) {
					case 0 : // Client
						try {
							userPO.setOption("CLIENT");
							userPO.setPurpose("APPROVAL");
							session.setAttribute("userPO", userPO);
							ret = "/servlet/datapro.eibs.client.JSESD0100?SCREEN=1&ACCNUM=" + account;
						} catch (Exception ex) {
							System.out.println("Error: " + ex);
						}
						break;
					case 11 :
					case 12 :
					case 15 : // CD
						try {
							userPO.setOption("CD");
							userPO.setPurpose("APPROVAL");
							session.setAttribute("userPO", userPO);
							ret =
								"/servlet/datapro.eibs.products.JSEDL0140?SCREEN=1&appCode=CD&ACCNUM="
									+ account;
						} catch (Exception ex) {
							System.out.println("Error: " + ex);
						}
						break;
					case 13 :
						try {
							userPO.setOption("13");
							userPO.setPurpose("APPROVAL");
							session.setAttribute("userPO", userPO);
							ret =
								"/servlet/datapro.eibs.products.JSEDL0105A?SCREEN=400&E01DEAACC="
									+ account;
						} catch (Exception ex) {
							System.out.println("Error: " + ex);
						}
						break;
					case 1 : // Retail Accounts
					case 2 :
					case 3 :
					case 5 : //Call Account
						try {
							userPO.setOption("RT");
							userPO.setPurpose("APPROVAL");
							session.setAttribute("userPO", userPO);
							ret =
								"/servlet/datapro.eibs.products.JSEDD1000?SCREEN=1&appCode=RT&ACCNUM="
									+ account;
						} catch (Exception ex) {
							System.out.println("Error: " + ex);
						}
						break;
					case 4 : // Saving Accounts
						try {
							userPO.setOption("SV");
							userPO.setPurpose("APPROVAL");
							session.setAttribute("userPO", userPO);
							ret =
								"/servlet/datapro.eibs.products.JSEDD1000?SCREEN=1&appCode=04&ACCNUM="
									+ account;
						} catch (Exception ex) {
							System.out.println("Error: " + ex);
						}
						break;
					case 10 : // Loans Accounts
						try {
							userPO.setOption("LN");
							userPO.setPurpose("APPROVAL");
							session.setAttribute("userPO", userPO);
							ret =
								"/servlet/datapro.eibs.products.JSEDL0140?SCREEN=1&appCode=10&ACCNUM="
									+ account;
						} catch (Exception ex) {
							System.out.println("Error: " + ex);
						}
						break;
					case 40 :
					case 41 :
					case 42 : // Letter of Credit
						try {
							userPO.setOption("LC");
							userPO.setPurpose("APPROVAL");
							session.setAttribute("userPO", userPO);
							ret = path + "Under_construction.jsp";
						} catch (Exception ex) {
							System.out.println("Error: " + ex);
						}
						break;
					case 84 : // Debit Cards
						try {
							userPO.setOption("DC");
							userPO.setPurpose("APPROVAL");
							//userPO.setIdentifier(account);
							userPO.setAccNum(account);
							session.setAttribute("userPO", userPO);
							ret =
								"/servlet/datapro.eibs.products.JSEDD1000?SCREEN=1&appCode=84&ACCNUM="
									+ account;					
						} catch (Exception ex) {
							System.out.println("Error: " + ex);
						}
						break;
					case 90 : // Line of Credit
						try {
							userPO.setOption("CL");
							userPO.setPurpose("APPROVAL");
							session.setAttribute("userPO", userPO);
							ret =
								"/servlet/datapro.eibs.credit.JSELN0040?SCREEN=1&ACCNUM="
									+ account
									+ "_"
									+ number;
						} catch (Exception ex) {
							System.out.println("Error: " + ex);
						}
						break;
					case 93 : // Internal & External Transaction
						try {
							userPO.setOption("PR");
							userPO.setPurpose("APPROVAL");
							session.setAttribute("userPO", userPO);
							ret =
								"/servlet/datapro.eibs.approval.JSEPR1080A?SCREEN=1&REFNUM="
									+ account
									+ "_"
									+ number;
						} catch (Exception ex) {
							System.out.println("Error: " + ex);
						}
						break;
					default :
						ret = path + "Under_construction.jsp";
				}
				break;

			default :
				ret = path + "Under_construction.jsp";
		}
		return ret;
	}
	/**
	 * Insert the method's description here.
	 * Creation date: (8/31/2000 12:25:41 PM)
	 * @return int
	 */
	public String getNumber() {
		return number;
	}
	/**
	 * Insert the method's description here.
	 * Creation date: (8/31/2000 12:25:41 PM)
	 * @return int
	 */
	public int getOption() {
		return option;
	}
	/**
	 * Insert the method's description here.
	 * Creation date: (8/31/2000 12:25:41 PM)
	 * @param newOption int
	 */
	public void setNumber(String newNumber) {
		number = newNumber;
	}
	/**
	 * Insert the method's description here.
	 * Creation date: (8/31/2000 12:25:41 PM)
	 * @param newOption int
	 */
	public void setOption(int newOption) {
		option = newOption;
	}
}