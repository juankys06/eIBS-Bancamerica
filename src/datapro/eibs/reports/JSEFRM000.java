

package datapro.eibs.reports;

import datapro.eibs.beans.*;
import datapro.eibs.master.*;
import datapro.eibs.sockets.DecimalField;
import datapro.eibs.sockets.MessageContext;
import datapro.eibs.sockets.MessageField;
import datapro.eibs.sockets.MessageRecord;
import java.beans.Beans;
import java.io.*;
import java.math.BigDecimal;
import java.net.Socket;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.*;
import javax.servlet.http.*;
import org.jdom.Comment;
import org.jdom.Document;
import org.jdom.Element;
import org.jdom.output.XMLOutputter;
import org.w3c.dom.Attr;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

public class JSEFRM000 extends SuperServlet
{

    public static final String FinalBR = "<BR />";
    public static final String BR = "*13*";
    static final int R_FORM = 1;
    static final int R_OFFICIAL_CHECK = 3;
    static final int R_APPLICATION = 5;
    static final int R_ENTER = 100;
    static final int A_ENTER = 200;
    protected String LangPath;
    protected String ftmEibs;
    protected String userLanguage;
    protected String eIBSFormsNL;
    protected String descNumbers[] = {
        "Cero", "Una", "Dos", "Tres", "Cuatro", "Cinco", "Seis", "Siete", "Ocho", "Nueve", 
        "Diez", "Once", "Doce", "Trece", "Catorce", "Quince"
    };
    protected Map formatNames = new HashMap();
    
    public JSEFRM000()
    {
        LangPath = "S";
        eIBSFormsNL = "$#NL#$";
        initFormatNames();
    }
    
    private void initFormatNames(){
        formatNames.put("EFRM00005", "RetailAccount");
        formatNames.put("EFRM00006", "StopPayments");
        formatNames.put("EFRM00007", "RetailAdditional");
        formatNames.put("EFRM00008", "MoneyLaundering");
        formatNames.put("EFRM00010", "Certificate");
        formatNames.put("EFRM00040", "LetterOfCredit");
        formatNames.put("EFRM00041", "RemittanceLetter");
        formatNames.put("EFRM00042", "ProceedsAssignment");
        formatNames.put("EFRM00043", "GuaranteeTickets");//Boletas de Garantia
        formatNames.put("EFRM00050", "Collections");
        formatNames.put("EFRM00070", "OfficialCheck");
        formatNames.put("EFRM00071", "DetailOfGuarantee");
        formatNames.put("EFRM00072", "SpecialCodes");
        formatNames.put("EFRM00073", "Entities");
        formatNames.put("EFRM00074", "SpecialInstructions");
        formatNames.put("EFRM00075", "AccountHolders");
        formatNames.put("EFRM00076", "PaymentSchedule");
        formatNames.put("EFRM00080", "Loans");
        formatNames.put("EFRM00095", "Unknown");
        formatNames.put("EFRM00098", "Customer");
        formatNames.put("EFRM00301", "CreditProposal");
    }

    public void addDataNode(Object data, String name, String dataItem)
    {
        try
        {
            if(!dataItem.trim().equals("") && !dataItem.trim().equalsIgnoreCase("&nbsp;"))
            {
                boolean isOK = true;
                try
                {
                    BigDecimal dataNum = new BigDecimal(dataItem);
                    if(dataNum.compareTo(new BigDecimal("0.0")) == 0)
                        isOK = false;
                    else
                        isOK = true;
                }
                catch(Exception ex)
                {
                    isOK = true;
                }
                if(isOK)
                    ((Element)data).addContent((new Element("IRDataItem")).setText(dataItem).setAttribute("DataType", "String").setAttribute("Protected", "False").setAttribute("PublicName", name));
            }
        }
        catch(Exception exception) { }
    }
    
	
	public void addDataNode(Object data, String name, MessageField field){
		if (field.getTag().toLowerCase().indexOf("time") > -1){
			addDataNode(data, name + field.getTag(), Util.formatTime(field.getString()));
		} else if (field.getDecimals() > 0){
			addDataNode(data, name + field.getTag(), Util.formatCCY(field.getString()));
		} else {
			addDataNode(data, name + field.getTag(), field.getString());
		}
	}
	
	protected void addDateDataNode(Object data, String name, 
			MessageField field, String year, String month, String day){
		if (Integer.parseInt(month) == 0 && Integer.parseInt(day) == 0) {
			return;
		}
		if (field.getTag().indexOf("Long") > 0
				|| field.getTag().indexOf("LL") > 0) {
			addDataNode(data, name, Util.formatCustomDate(
					"DMY", (!day.equals("") ? "dd 'de' " : "")
							+ (!month.equals("") ? "MMM " : "")
							+ (!year.equals("") ? "'del' yyyy" : ""), "es",
					day, month, year));
		} else {
			addDataNode(data, name, Util.formatCustomDate(
					"DMY", (!day.equals("") ? "dd" : "")
							+ (!month.equals("") ? "/MM" : "")
							+ (!year.equals("") ? "/yyyy" : ""), "es",
					day, month, year));
		}
	}
	
	private void addCommonDataNode(Object data, String name, 
		MessageRecord message, MessageField field, int index){
		addDataNode(data, name + getFieldName(field.getTag(), index), field);
		if (field.getTag().indexOf("RolloverCode") > -1){
			addDataNode(data, name + getFieldName("", index) + "RolloverDescription", 
				getRolloverInstructions(message));
		} else if (field.getDecimals() > 1) {
			DecimalField dField = (DecimalField) field; 
			addDataNode(data, name + getFieldName(field.getTag(), index) + field.getTag()
				+ "Cifras", toCifras(dField.getBigDecimal()));
		}
	}
	
	private String getFieldName(String name, int index){
		int dotPos = name.indexOf(".");
		return (index == 0 || dotPos == 1 || dotPos == 2 
			? "" : index + ".");
	}
    
	protected void getFormData(Object data, String name, MessageRecord message){
		getFormData(data, name, 0, message);
	}
	
    protected void getFormData(Object data, String name, int index, MessageRecord message)
    {    	
		// all the fields here
		java.util.Enumeration enu = message.fieldEnumeration();
		MessageField field = null;
		while (enu.hasMoreElements()) {
			field = (MessageField) enu.nextElement();
			try {
				if (field.getTag().indexOf("Year") > 0
						|| field.getTag().indexOf("Month") > 0
						|| field.getTag().indexOf("Day") > 0){
					String dateName = "";
					String year = "";
					String month = "";
					String day = "";
					MessageField field1 = field;
					if (field1.getTag().indexOf("Year") > 0){
						year = field1.getString();
					} else if (field1.getTag().indexOf("Month") > 0){
						month = field1.getString();
						dateName = field1.getTag().substring(
								0, field1.getTag().indexOf("Month"));
					} else if (field1.getTag().indexOf("Day") > 0){
						day = field1.getString();
					}
					addDataNode(data, name + getFieldName(field1.getTag(), index), field1);
					
					MessageField field2 = (MessageField) enu.nextElement();
					if (field2.getTag().indexOf("Year") > 0){
						year = field2.getString();
					} else if (field2.getTag().indexOf("Month") > 0){
						month = field2.getString();
						dateName = field2.getTag().substring(
								0, field2.getTag().indexOf("Month"));
					} else if (field2.getTag().indexOf("Day") > 0){
						day = field2.getString();
					} else {
						addCommonDataNode(data, name, message, field2, index);
						continue;
					}					
					addDataNode(data, name + getFieldName(field2.getTag(), index), field2);
					
					MessageField field3 = (MessageField) enu.nextElement();
					if (field3.getTag().indexOf("Year") > 0){
						year = field3.getString();
					} else if (field3.getTag().indexOf("Month") > 0){
						month = field3.getString();
						dateName = field3.getTag().substring(
								0, field3.getTag().indexOf("Month"));
					} else if (field3.getTag().indexOf("Day") > 0){
						day = field3.getString();
					} else {
						addCommonDataNode(data, name, message, field2, index);
						continue;
					}
					addDataNode(data, name + getFieldName(field3.getTag(), index), field3);
					
					addDateDataNode(data, name + getFieldName(dateName, index) + dateName, 
						field, year, month, day);
				} else if (field.getTag().indexOf("MMDDYY") > 0
							|| field.getTag().indexOf("DDMMYY") > 0
							|| field.getTag().indexOf("YYMMDD") > 0){
					try {
						int size = field.getLength() > 6 ? 8 : 6;
						int yearPos = field.getTag().indexOf("YY");
						int monthPos = field.getTag().indexOf("MM");
						int dayPos = field.getTag().indexOf("DD");
						String date = Util.addLeftChar('0', size, field.getString());
						int pos = date.length() - (size - 
								((yearPos/monthPos*2) + (yearPos/monthPos*2)));
						String year = date.substring(pos, pos + (size > 6 ? 4 : 2));
						pos = date.length() - (size - ((monthPos/yearPos*2) + (monthPos/dayPos*2)));
						String month = date.substring(pos, pos + 2);
						pos = date.length() - (size - ((dayPos/yearPos*2) + (dayPos/monthPos*2)));
						String day = date.substring(pos, pos + 2);
						addDateDataNode(data, name + getFieldName(field.getTag(), index) + field.getTag(), 
							field, year, month, day);
					} catch (Exception e) {
						flexLog("FORM Date Format Error on : " + name + field.getTag());
					}
				} else if (field.getTag().indexOf("Date") > 0
					|| field.getTag().indexOf("date") == 0){
					//Default Date format is MMDDYY
					int size = field.getLength() > 6 ? 8 : 6;
					String date = Util.addLeftChar('0', size, field.getString());
					String month = date.substring(0, 2);
					String day = date.substring(2, 4);
					String year = date.substring(4, 4 + (size > 6 ? 4 : 2));
					year = (size > 6 ? "" : (Integer.parseInt(year) > 50 ? "19" : "20")) + year;
					addDateDataNode(data, name + getFieldName(field.getTag(), index) + field.getTag(), 
						field, year, month, day);
				} else {
					addCommonDataNode(data, name, message, field, index);
				}
			} catch (Exception e) {
			}
		}
    }
    
    protected void buildFormList(MessageContext mc, Object data, MessageRecord newmessage, String prefix, int index) 
    	throws ServletException, IOException {
    	String ddsName = newmessage.getFormatName();
    	getFormData(data, (String) formatNames.get(ddsName) + prefix, index, newmessage);
	}

    private void procReqEnter(ESS0030DSMessage user, HttpServletRequest req, HttpServletResponse res, HttpSession ses)
        throws ServletException, IOException
    {
        ELEERRMessage msgError = null;
        UserPos userPO = null;
        try
        {
            msgError = (ELEERRMessage)Beans.instantiate(getClass().getClassLoader(), "datapro.eibs.beans.ELEERRMessage");
            userPO = (UserPos)Beans.instantiate(getClass().getClassLoader(), "datapro.eibs.beans.UserPos");
            userPO.setOption("FORMS");
            userPO.setPurpose("");
            ses.setAttribute("error", msgError);
            ses.setAttribute("userPO", userPO);
        }
        catch(Exception ex)
        {
            flexLog("Error: " + ex);
        }
        try
        {
            flexLog("About to call Page: " + LangPath + "EFRM000_forms_enter.jsp");
            callPage(LangPath + "EFRM000_forms_enter.jsp", req, res);
        }
        catch(Exception e)
        {
            flexLog("Exception calling page " + e);
        }
    }

    private void procReqForm(MessageContext mc, ESS0030DSMessage user, HttpServletRequest req, HttpServletResponse res, HttpSession ses)
        throws ServletException, IOException
    {
        MessageRecord newmessage = null;
        EFRM00001Message msgHeader = null;
        ELEERRMessage msgError = null;
        String docType = null;
        String appCode = null;
        String dataScheme = null;
        String minTextSize = null;
        UserPos userPO = null;
        try
        {
            msgError = (ELEERRMessage)Beans.instantiate(getClass().getClassLoader(), "datapro.eibs.beans.ELEERRMessage");
        }
        catch(Exception ex)
        {
            flexLog("Error: " + ex);
        }
        userPO = (UserPos)ses.getAttribute("userPO");
        try
        {
            msgHeader = (EFRM00001Message)mc.getMessageRecord("EFRM00001");
            msgHeader.setH01USERID(user.getH01USR());
            msgHeader.setH01PROGRM("EFRM000");
            msgHeader.setH01TIMSYS(SuperServlet.getTimeStamp());
            try
            {
                msgHeader.setH01FLGWK1(req.getParameter("INTERFACE"));
            }
            catch(Exception exception) { }
            try
            {
                msgHeader.setE01SELFTY(req.getParameter("OPE_CODE"));
            }
            catch(Exception exception1) { }
            try
            {
                appCode = req.getParameter("APP_CODE");
                if(appCode == null)
                    appCode = "";
            }
            catch(Exception e)
            {
                appCode = "";
            }
            if(appCode.equalsIgnoreCase("XX"))
            {
                try
                {
                    msgHeader.setH01OPECOD(req.getParameter("ACCOUNT").toUpperCase());
                }
                catch(Exception e) { }
            } else
            {
                msgHeader.setE01SELACD(appCode);
                try
                {
                    msgHeader.setE01SELACC(req.getParameter("ACCOUNT"));
                }
                catch(Exception e)
                {
                    if(appCode.equals("00"))
                        msgHeader.setE01SELACC(userPO.getCusNum());
                    else
                        msgHeader.setE01SELACC(userPO.getIdentifier());
                }
                try
                {
                    msgHeader.setH01OPECOD(Util.justifyRight(req.getParameter("SEQ"), 4));
                }
                catch(Exception exception2) { }
            }
            try
            {
                msgHeader.setH01FLGWK3(req.getParameter("H01FLGWK3"));
            }
            catch(Exception e)
            {
                msgHeader.setH01FLGWK3("");
            }
            try
            {
                dataScheme = JSEIBSProp.getDefaultDataScheme();
                if(dataScheme.equals(""))
                    dataScheme = "512";
            }
            catch(Exception e)
            {
                dataScheme = "512";
            }
            try
            {
                minTextSize = JSEIBSProp.getMinimumTextSize();
                if(minTextSize.equals(""))
                    minTextSize = "8";
            }
            catch(Exception e)
            {
                minTextSize = "8";
            }
            msgHeader.send();
            msgHeader.destroy();
        }
        catch(Exception e)
        {
            e.printStackTrace();
            flexLog("Error: " + e);
            throw new RuntimeException("Socket Communication Error");
        }
        try
        {
            newmessage = mc.receiveMessage();
            if(newmessage.getFormatName().equals("ELEERR"))
            {
                msgError = (ELEERRMessage)newmessage;
                flexLog("Putting java beans into the session");
                String err = msgError.getERNU01() + " - " + msgError.getERDS01();
                ses.setAttribute("error_msg", err);
                try
                {
                    flexLog("About to call Page: " + LangPath + "EFRM000_forms_req_error.jsp");
                    callPage(LangPath + "EFRM000_forms_req_error.jsp", req, res);
                }
                catch(Exception e)
                {
                    flexLog("Exception calling page " + e);
                }
            } else
            if(newmessage.getFormatName().equals("EFRM00001"))
            {
                Element root = new Element("IRDocumentList");
                root.setAttribute("id", "");
                root.setAttribute("MinimumTextSize", minTextSize);
                root.setAttribute("DefaultDataScheme", dataScheme);
                root.setAttribute("CheckBoxMark", "XX");
                Document doc = new Document(root);
                Element ftp = new Element("eIBSFTP");
                ftp.setAttribute("Language", user.getE01LAN().toLowerCase());
                ftp.setAttribute("Host", JSEIBSProp.getFtpFormHost());
                ftp.setAttribute("Port", JSEIBSProp.getFtpFormPort());
                ftp.setAttribute("UserID", JSEIBSProp.getFtpFormUserID());
                ftp.setAttribute("Password", JSEIBSProp.getFtpFormPassword());
                if(!JSEIBSProp.getFtpPathFormData().equals(""))
                    ftp.setAttribute("DataPath", JSEIBSProp.getFtpPathFormData());
                if(!JSEIBSProp.getFtpPathFormConfig().equals(""))
                    ftp.setAttribute("ConfigPath", JSEIBSProp.getFtpPathFormConfig());
                if(!JSEIBSProp.getFtpFormFirewallType().equals(""))
                    ftp.setAttribute("FirewallType", JSEIBSProp.getFtpFormFirewallType());
                if(!JSEIBSProp.getFtpFormFWAuthenticate().equals(""))
                    ftp.setAttribute("FWAuthenticate", JSEIBSProp.getFtpFormFWAuthenticate());
                if(!JSEIBSProp.getFtpFormFWUserID().equals(""))
                    ftp.setAttribute("FWUserID", JSEIBSProp.getFtpFormFWUserID());
                if(!JSEIBSProp.getFtpFormFWPassword().equals(""))
                    ftp.setAttribute("FWPassword", JSEIBSProp.getFtpFormFWPassword());
                if(!JSEIBSProp.getBankerPDFURL().equals(""))
                    ftp.setAttribute("BankerPDFURL", JSEIBSProp.getBankerPDFURL());
                if(!JSEIBSProp.getBankerPDFURL1().equals(""))
                    ftp.setAttribute("BankerPDFURL1", JSEIBSProp.getBankerPDFURL1());
                root.addContent(ftp);
                String operation = null;
                boolean nothing = true;
                do
                {
                    msgHeader = (EFRM00001Message)newmessage;
                    if(msgHeader.getE01MORFRM().equals("*"))
                        break;
                    if(nothing)
                        nothing = false;
                    Element form = new Element("IRDocument");
                    if(msgHeader.getE01APFIFS().equals("B"))
                        docType = "BFO";
                    else
                        docType = "eIBSForm";
                    form.setAttribute("type", docType);
                    form.setAttribute("BFOName", msgHeader.getE01APFPTH().trim());
                    form.setAttribute("Copies", msgHeader.getE01APFCPI().trim());
                    form.setAttribute("FileName", "");
                    form.setAttribute("MinimumTextSize", "10");
                    form.setAttribute("DefaultDataScheme", "0");
                    switch(Integer.parseInt(msgHeader.getE01APFOPE()))
                    {
                    case 1: // '\001'
                        operation = "Preview";
                        break;

                    case 2: // '\002'
                        operation = "Print";
                        break;

                    case 3: // '\003'
                        operation = "Prepare";
                        break;

                    default:
                        operation = "Preview";
                        break;
                    }
                    form.setAttribute("Action", operation);
                    form.addContent((new Element("BFODoc")).setAttribute("DocumentName", msgHeader.getE01APFPTH().trim()).setAttribute("DocumentDuplex", "-3"));
                    form.addContent((new Element("IRInstance")).setAttribute("InstanceID", "").addContent((new Element("IRDataRequirement")).addContent(new Comment("Created: " + SuperServlet.getTimeStamp()))).addContent((new Element("IRFields")).addContent(new Comment("Added Fields"))).addContent((new Element("IRLogos")).addContent(new Comment("Added Logos"))));
                    root.addContent(form);
                    newmessage = mc.receiveMessage();
                } while(true);
                if(!nothing)
                {
                    Element data = new Element("IRDataRequirement");
                    data.addContent(new Comment("Created: " + SuperServlet.getTimeStamp()));
                    if(!appCode.equalsIgnoreCase("XX"))
                    {
                        newmessage = mc.receiveMessage();
                        int titularCount = 1;
                        int signerCount = 1;
                        int repLegalCount = 1;
                        int clientBoard = 1;
                        int clientLegalRep = 1;
                        do
                        {
                            String ddsName = newmessage.getFormatName();
                            if(ddsName.equals("EFRM00010"))
                            {
                                if(!docType.equalsIgnoreCase("BFO"))
                                    getCDDataForDataproForms(data, (EFRM00010Message)newmessage);
                            } else
                            if(ddsName.equals("EFRM00080"))
                            {
                                if(docType.equalsIgnoreCase("BFO"))
                                    getLNDataForBankersForms(data, (EFRM00080Message)newmessage);
                                else
                                    getLNDataForDataproForms(data, (EFRM00080Message)newmessage);
                            } else
                            if(ddsName.equals("ESD000006"))
                                getAccHoldersForDataproForms(data, (ESD000006Message)newmessage);
                            else
                            if(ddsName.equals("ESD000002"))
                                getAccSpecialCodesForDataproForms(data, (ESD000002Message)newmessage);
                            else
                            if(ddsName.equals("ESD000005"))
                                getAccSpecialInstForDataproForms(data, (ESD000005Message)newmessage);
                            else
                            if(ddsName.equals("ERA000001"))
                                getAccCollateralsForDataproForms(mc, data, (ERA000001Message)newmessage);
                            else
                            if(ddsName.equals("EDL090002"))
                                getAccPayScheduleForDataproForms(mc, data, (EDL090002Message)newmessage);
                            else
                            if(ddsName.equals("EFRM00005"))
                            {
                                if(!docType.equalsIgnoreCase("BFO"))
                                    getRTDataForDataproForms(data, (EFRM00005Message)newmessage);
                            } else
                            if(ddsName.equals("EFRM00007"))
                            {
                                EFRM00007Message msg = (EFRM00007Message)newmessage;
                                if(msg.getE07CUMRTP().equals("H"))
                                    getRTTitulars(data, msg, "Cuenta.Titular" + titularCount++);
                                else
                                if(msg.getE07CUMRTP().equals("S"))
                                    getRTSigners(data, msg, "Cuenta.Firmante" + signerCount++);
                                else
                                if(msg.getE07CUMRTP().equals("I"))
                                    getRTRepLegals(data, msg, "Cuenta.RepLegal" + repLegalCount++);
                                else
                                if(msg.getE07CUMRTP().equals("3"))
                                    getClientAddInfo(data, msg, "Board", clientBoard++);
                                else
                                if(msg.getE07CUMRTP().equals("5"))
                                    getClientAddInfo(data, msg, "LegalRep", clientLegalRep++);
                            } else
                            if(ddsName.equals("EFRM00008"))
                                getRTLDInfo(data, (EFRM00008Message)newmessage);
                            else
                            if(ddsName.equals("EFRM00098"))
                                getCUSTDataForDataproForms(data, (EFRM00098Message)newmessage);
                            else
                            if(ddsName.equals("ESD000004"))
                                getAddCUSTDataForDataproForms(data, (ESD000004Message)newmessage);
                            else
                            if(ddsName.equals("EFRM00006"))
                            {
                                if(docType.equalsIgnoreCase("BFO"))
                                    getSPDataForBankersForms(data, (EFRM00006Message)newmessage);
                            } else
                            if(ddsName.equals("EFRM00043"))
                                getLCStandByDataForDataproForms(data, (EFRM00043Message)newmessage);
                            else
                            if(ddsName.equals("EFRM00095"))
                                getDDADataForBankersForms(data, (EFRM00095Message)newmessage);
                            else
                            if(ddsName.equals("EFRM00001"))
                                break;
                            newmessage = mc.receiveMessage();
                        } while(true);
                    }
                    root.addContent(data);
                    try
                    {
                        if(SuperServlet.formActive)
                        {
                            if(docType.equalsIgnoreCase("BFO"))
                                res.setContentType("eibs/bfo");
                            else
                                res.setContentType("eibs/form");
                        } else
                        {
                            res.setContentType("text/html");
                        }
                        ServletOutputStream out = res.getOutputStream();
                        ByteArrayOutputStream outTmp = new ByteArrayOutputStream();
                        XMLOutputter fmt = new XMLOutputter();
                        fmt.setEncoding("ISO-8859-1");
                        fmt.output(doc, outTmp);
                        String sXMLReplaced = replace(outTmp.toString(), "*13*", "<BR />");
                        sXMLReplaced = replace(sXMLReplaced, "&amp;#39;", "'");
                        out.println(sXMLReplaced);
                        out.close();
                    }
                    catch(Exception e)
                    {
                        flexLog("Error: " + e);
                    }
                } else
                {
                    flexLog("Putting java beans into the session");
                    String err = "";
                    ses.setAttribute("error_msg", err);
                    try
                    {
                        flexLog("About to call Page: " + LangPath + "EFRM000_forms_req_error.jsp");
                        callPage(LangPath + "EFRM000_forms_req_error.jsp", req, res);
                    }
                    catch(Exception e)
                    {
                        flexLog("Exception calling page " + e);
                    }
                }
            } else
            {
                flexLog("Message " + newmessage.getFormatName() + " received.");
            }
        }
        catch(Exception e)
        {
            e.printStackTrace();
            flexLog("Error: " + e);
            throw new RuntimeException("Socket Communication Error");
        }
    }

    private void procReqOfficialCheck(MessageContext mc, ESS0030DSMessage user, HttpServletRequest req, HttpServletResponse res, HttpSession ses)
        throws ServletException, IOException
    {
        MessageRecord newmessage = null;
        EOF011501Message msgHeader = null;
        EWD0120DSMessage msgTr = null;
        ELEERRMessage msgError = null;
        UserPos userPO = null;
        boolean IsNotError = false;
        try
        {
            msgError = (ELEERRMessage)Beans.instantiate(getClass().getClassLoader(), "datapro.eibs.beans.ELEERRMessage");
        }
        catch(Exception ex)
        {
            flexLog("Error: " + ex);
        }
        userPO = (UserPos)ses.getAttribute("userPO");
        try
        {
            msgHeader = (EOF011501Message)mc.getMessageRecord("EOF011501");
            msgHeader.setH01USERID(user.getH01USR());
            msgHeader.setH01FLGWK1("P");
            msgHeader.setH01OPECOD("0002");
            try
            {
                if(req.getParameter("E01OFMCKN") != null)
                    msgHeader.setE01OFMCKN(req.getParameter("E01OFMCKN"));
                flexLog("Product Sent");
            }
            catch(Exception e)
            {
                msgHeader.setE01OFMCKN("0");
                flexLog(" error " + e);
                flexLog(" Error Sent");
            }
            try
            {
                if(req.getParameter("E01OFMCCY") != null)
                    msgHeader.setE01OFMCCY(req.getParameter("E01OFMCCY").toUpperCase());
                flexLog("Product Sent");
            }
            catch(Exception e)
            {
                msgHeader.setE01OFMCCY("0");
                flexLog(" error " + e);
                flexLog(" Error Sent");
            }
            msgHeader.send();
            msgHeader.destroy();
        }
        catch(Exception e)
        {
            e.printStackTrace();
            flexLog("Error: " + e);
            throw new RuntimeException("Socket Communication Error");
        }
        try
        {
            newmessage = mc.receiveMessage();
            if(newmessage.getFormatName().equals("ELEERR"))
            {
                msgError = (ELEERRMessage)newmessage;
                flexLog("Putting java beans into the session");
                String err = msgError.getERNU01() + " - " + msgError.getERDS01();
                ses.setAttribute("error_msg", err);
                try
                {
                    flexLog("About to call Page: " + LangPath + "EFRM000_forms_req_error.jsp");
                    callPage(LangPath + "EFRM000_forms_req_error.jsp", req, res);
                }
                catch(Exception e)
                {
                    flexLog("Exception calling page " + e);
                }
            } else
            if(newmessage.getFormatName().equals("EOF011501"))
            {
                Element root = new Element("IRDocumentList");
                root.setAttribute("id", "");
                root.setAttribute("MinimumTextSize", "10");
                root.setAttribute("DefaultDataScheme", "0");
                root.setAttribute("CheckBoxMark", "XX");
                root.addContent(new Comment("Created: " + SuperServlet.getTimeStamp()));
                Document doc = new Document(root);
                Element ftp = new Element("eIBSFTP");
                ftp.setAttribute("Language", user.getE01LAN().toLowerCase());
                ftp.setAttribute("Host", JSEIBSProp.getFtpFormHost());
                ftp.setAttribute("Port", JSEIBSProp.getFtpFormPort());
                ftp.setAttribute("UserID", JSEIBSProp.getFtpFormUserID());
                ftp.setAttribute("Password", JSEIBSProp.getFtpFormPassword());
                if(!JSEIBSProp.getFtpPathFormData().equals(""))
                    ftp.setAttribute("DataPath", JSEIBSProp.getFtpPathFormData());
                if(!JSEIBSProp.getFtpPathFormConfig().equals(""))
                    ftp.setAttribute("ConfigPath", JSEIBSProp.getFtpPathFormConfig());
                if(!JSEIBSProp.getFtpFormFirewallType().equals(""))
                    ftp.setAttribute("FirewallType", JSEIBSProp.getFtpFormFirewallType());
                if(!JSEIBSProp.getFtpFormFWAuthenticate().equals(""))
                    ftp.setAttribute("FWAuthenticate", JSEIBSProp.getFtpFormFWAuthenticate());
                if(!JSEIBSProp.getFtpFormFWUserID().equals(""))
                    ftp.setAttribute("FWUserID", JSEIBSProp.getFtpFormFWUserID());
                if(!JSEIBSProp.getFtpFormFWPassword().equals(""))
                    ftp.setAttribute("FWPassword", JSEIBSProp.getFtpFormFWPassword());
                ftp.addContent(new Comment("FTP and Firewall Configuration"));
                root.addContent(ftp);
                String operation = null;
                boolean nothing = true;
                msgHeader = (EOF011501Message)newmessage;
                Element form = new Element("IRDocument");
                form.setAttribute("type", "eIBSForm");
                form.setAttribute("BFOName", "CHK_OFF_CLIENTE.DOC");
                form.setAttribute("Copies", "1");
                form.setAttribute("FileName", "");
                form.setAttribute("MinimumTextSize", "10");
                form.setAttribute("DefaultDataScheme", "0");
                switch(1)
                {
                case 1: // '\001'
                    operation = "Preview";
                    break;

                case 2: // '\002'
                    operation = "Print";
                    break;

                case 3: // '\003'
                    operation = "Prepare";
                    break;

                default:
                    operation = "Preview";
                    break;
                }
                form.setAttribute("Action", operation);
                form.addContent((new Element("BFODoc")).setAttribute("DocumentName", "CHK_OFF_CLIENTE.DOC").setAttribute("DocumentDuplex", "-3"));
                form.addContent((new Element("IRInstance")).setAttribute("InstanceID", "").addContent((new Element("IRDataRequirement")).addContent(new Comment("Created: " + SuperServlet.getTimeStamp()))).addContent((new Element("IRFields")).addContent(new Comment("Added Fields"))).addContent((new Element("IRLogos")).addContent(new Comment("Added Logos"))));
                root.addContent(form);
                newmessage = mc.receiveMessage();
                Element data = new Element("IRDataRequirement");
                data.addContent(new Comment("Created: " + SuperServlet.getTimeStamp()));
                addDataNode(data, "OfficialCheck.CheckNumber", msgHeader.getE01OFMCKN());
                addDataNode(data, "OfficialCheck.DateFull", Util.formatDateFull(user.getE01DTF(), user.getE01LAN(), msgHeader.getE01OFMEM1(), msgHeader.getE01OFMEM2(), msgHeader.getE01OFMEM3()));
                addDataNode(data, "OfficialCheck.Date", Util.formatDate(msgHeader.getE01OFMEM1(), msgHeader.getE01OFMEM2(), msgHeader.getE01OFMEM3()));
                String lang = user.getE01LAN().equals("s") ? "es" : "en";
                String fecha_Dia_Mes = Util.formatCustomDate("DMY", "dd 'de' MMM", lang, msgHeader.getE01OFMEM1(), msgHeader.getE01OFMEM2(), msgHeader.getE01OFMEM3());
                addDataNode(data, "OfficialCheck.DateMonthDay", fecha_Dia_Mes);
                addDataNode(data, "OfficialCheck.DateYear", Util.formatYear(msgHeader.getE01OFMEM3()));
                addDataNode(data, "OfficialCheck.Beneficiary", msgHeader.getE01OFMBNF() + msgHeader.getE01OFMBN1() + msgHeader.getE01OFMBN2());
                addDataNode(data, "OfficialCheck.BeneficiaryID", msgHeader.getE01OFMBID());
                addDataNode(data, "OfficialCheck.Applicant", msgHeader.getE01OFMAPL() + " " + msgHeader.getE01OFMAP1());
                addDataNode(data, "OfficialCheck.ApplicantID", msgHeader.getE01OFMAID());
                addDataNode(data, "OfficialCheck.ApplicantAddress", msgHeader.getE01OFMAD1() + " " + msgHeader.getE01OFMAD2() + " " + msgHeader.getE01OFMAD3());
                addDataNode(data, "OfficialCheck.ApplicantPhoneNumber", msgHeader.getE01OFMHPN());
                addDataNode(data, "OfficialCheck.Amount", Util.addLeftChar('*', 15, Util.fcolorCCY(msgHeader.getE01OFMAMT())));
                addDataNode(data, "OfficialCheck.AmountText", msgHeader.getE01LETAMT());
                addDataNode(data, "OfficialCheck.Comision", Util.addLeftChar('*', 15, Util.fcolorCCY(msgHeader.getE01OFMCOM())));
                addDataNode(data, "OfficialCheck.Total", Util.formatCCY(Util.parseCCYtoDouble(msgHeader.getE01OFMCOM()) + Util.parseCCYtoDouble(msgHeader.getE01OFMAMT())));
                addDataNode(data, "OfficialCheck.PaymentMode", msgHeader.getE01DEBCON());
                addDataNode(data, "OfficialCheck.Oficial", user.getH01USR());
                addDataNode(data, "OfficialCheck.PaymentAccount", msgHeader.getE01DEBACC());
                addDataNode(data, "OfficialCheck.Concept", msgHeader.getE01OFMCO1() + " " + msgHeader.getE01OFMCO2() + " " + msgHeader.getE01OFMCO3());
                addDataNode(data, "OfficialCheck.Ciudad", msgHeader.getD01OFMCTY());
                int pos = 0;
                root.addContent(data);
                try
                {
                    if(SuperServlet.formActive)
                        res.setContentType("eibs/form");
                    else
                        res.setContentType("text/html");
                    ServletOutputStream out = res.getOutputStream();
                    ByteArrayOutputStream outTmp = new ByteArrayOutputStream();
                    XMLOutputter fmt = new XMLOutputter();
                    fmt.output(doc, outTmp);
                    String sXMLReplaced = replace(outTmp.toString(), "*13*", "<BR />");
                    sXMLReplaced = replace(sXMLReplaced, "&amp;#39;", "'");
                    out.print(sXMLReplaced);
                    out.close();
                }
                catch(Exception e)
                {
                    flexLog("Error: " + e);
                }
            } else
            {
                flexLog("Message " + newmessage.getFormatName() + " received.");
            }
        }
        catch(Exception e)
        {
            e.printStackTrace();
            flexLog("Error: " + e);
            throw new RuntimeException("Socket Communication Error");
        }
    }

    public static void ReadXML(Node node, String tagFrom, String tagTo, String value)
    {
        if(node == null)
            return;
        int type = node.getNodeType();
        switch(type)
        {
        case 1: // '\001'
            if(tagTo.equals(""))
                tagTo = node.getNodeName();
            else
                tagTo = node.getNodeName();
            int length = node.getAttributes() == null ? 0 : node.getAttributes().getLength();
            Attr attrs[] = new Attr[length];
            for(int loopIndex = 0; loopIndex < length; loopIndex++)
                attrs[loopIndex] = (Attr)node.getAttributes().item(loopIndex);

            for(int loopIndex = 0; loopIndex < attrs.length; loopIndex++)
            {
                Attr attr = attrs[loopIndex];
                String tempTag = tagTo;
                tagTo = attr.getNodeName();
                value = attr.getNodeValue();
                if(value.indexOf("\r") != -1)
                {
                    String value2 = replace(value, "\r", "<br>");
                    node.setNodeValue(value2);
                }
                tagTo = tempTag;
            }

            NodeList childNodes = node.getChildNodes();
            if(childNodes != null)
            {
                length = childNodes.getLength();
                for(int loopIndex = 0; loopIndex < length; loopIndex++)
                    ReadXML(childNodes.item(loopIndex), tagFrom, tagTo, value);

            }
            break;

        case 4: // '\004'
            tagTo = node.getNodeName();
            value = node.getNodeValue();
            break;

        case 3: // '\003'
            if(node.getNodeName().equals("#text"))
            {
                tagTo = "" + tagTo;
                value = node.getNodeValue();
                if(value.indexOf("\r") != -1)
                {
                    String value2 = replace(value, "\r", "<br>");
                    node.setNodeValue(value2);
                }
            }
            break;

        case 7: // '\007'
            tagTo = tagTo + "-" + node.getNodeName();
            value = node.getNodeValue();
            break;
        }
        if(type == 1)
            node.getNodeName();
    }

    public static String replace(String Text, String Old, String New)
    {
        if(Old.length() == 0)
            return Text;
        StringBuffer buf = new StringBuffer();
        int i = 0;
        int j;
        for(j = 0; (i = Text.indexOf(Old, j)) > -1; j = i + Old.length())
            buf.append(Text.substring(j, i) + New);

        if(j < Text.length())
            buf.append(Text.substring(j));
        return buf.toString();
    }

    public void service(HttpServletRequest req, HttpServletResponse res)
        throws ServletException, IOException
    {
        Socket s = null;
        MessageContext mc = null;
        ESS0030DSMessage msgUser = null;
        HttpSession session = null;
        session = req.getSession(false);
        if(session == null)
            try
            {
                res.setContentType("text/html");
                printLogInAgain(res.getWriter());
            }
            catch(Exception e)
            {
                e.printStackTrace();
                flexLog("Exception ocurred. Exception = " + e);
            }
        else
            try
            {
                int screen = 1;
                msgUser = (ESS0030DSMessage)session.getAttribute("currUser");
                LangPath = SuperServlet.rootPath + msgUser.getE01LAN() + "/";
                userLanguage = msgUser.getE01LAN().equals("s") ? "es" : "en";
                ftmEibs = msgUser.getE01DTF();
                try
                {
                    flexLog("Opennig Socket Connection");
                    s = new Socket(SuperServlet.hostIP, SuperServlet.getInitSocket(req) + 1);
                    s.setSoTimeout(SuperServlet.sckTimeOut);
                    mc = new MessageContext(new DataInputStream(new BufferedInputStream(s.getInputStream())), new DataOutputStream(new BufferedOutputStream(s.getOutputStream())), "datapro.eibs.beans");
                }
                catch(Exception e)
                {
                    e.printStackTrace();
                    int sck = SuperServlet.getInitSocket(req) + 1;
                    flexLog("Socket not Open(Port " + sck + "). Error: " + e);
                    res.sendRedirect(SuperServlet.srctx + LangPath + SuperServlet.sckNotOpenPage);
                    return;
                }
                try
                {
                    screen = Integer.parseInt(req.getParameter("SCREEN"));
                }
                catch(Exception e)
                {
                    flexLog("Screen set to default value");
                }
                switch(screen)
                {
                case 1: // '\001'
                    procReqForm(mc, msgUser, req, res, session);
                    break;

                case 3: // '\003'
                    procReqOfficialCheck(mc, msgUser, req, res, session);
                    break;

                case 100: // 'd'
                    procReqEnter(msgUser, req, res, session);
                    break;

                case 5: // '\005'
                    procReqApplication(msgUser, req, res, session);
                    break;

                default:
                    res.sendRedirect(SuperServlet.srctx + LangPath + SuperServlet.devPage);
                    break;
                }
                try
                {
                    s.close();
                }
                catch(Exception e)
                {
                    flexLog("Error closing socket connection " + e);
                }
            }
            catch(Exception e)
            {
                flexLog("Error: " + e);
                res.sendRedirect(SuperServlet.srctx + LangPath + SuperServlet.sckNotRespondPage);
            }
    }
    
    protected String getPrefix(MessageRecord msg) {
    	MessageField field = null;
    	try {
    		field = msg.getField("RecordType");
		} catch (Exception e) {
			try {
				field = msg.getField("E04RTP");
			} catch (Exception e1) {
				try {
					field = msg.getField("E07CUMRTP");					
				} catch (Exception e2) {
				}
			}
		}
		if (field != null) {
	        try {
				int type = Integer.parseInt(field.getString());
				return getPrefix(type);
			} catch (Exception exception) {
				return getPrefix(field.getString());
			}			
		}
		return "";
	}
    
    protected String getPrefix(String type) {
        String prefix = "";
        if (type.equals("H")) {
        	prefix = "Titular.";
		} else if (type.equals("S")) {
			prefix = "Signer.";
		} else if (type.equals("I")) {
			prefix = "LegalRep.";
		} else if (type.equals("1")) {
			prefix = "Address.";
		} else if (type.equals("3")) {
			prefix = "Board.";
		} else if (type.equals("5")) {
			prefix = "LegalRep.";
		}
        return prefix;
    }

    protected String getPrefix(int type) {
        String prefix = null;
        switch(type)
        {
        case 2: // '\002'
            prefix = "StockHolder.";
            break;

        case 3: // '\003'
            prefix = "Board.";
            break;

        case 4: // '\004'
            prefix = "Beneficiary.";
            break;

        case 5: // '\005'
            prefix = "LegalRep.";
            break;

        case 6: // '\006'
            prefix = "BankRef.";
            break;

        case 7: // '\007'
            prefix = "CommercialRef.";
            break;

        case 8: // '\b'
            prefix = "PersonalRef.";
            break;

        default:
            prefix = "";
            break;
        }
        return prefix;
	}
    
    protected void getAddCUSTDataForDataproForms(Object data, ESD000004Message msg)
    {
    	String prefix = getPrefix(msg);
        addDataNode(data, prefix + "MailAddressFirst.One", msg.getE14MA1());
        addDataNode(data, prefix + "MailAddressFirst.Two", msg.getE24MA1());
        addDataNode(data, prefix + "MailAddressFirst.Three", msg.getE34MA1());
        addDataNode(data, prefix + "MailAddressFirst.Four", msg.getE44MA1());
        addDataNode(data, prefix + "MailAddressFirst.Five", msg.getE54MA1());
        addDataNode(data, prefix + "MailAddressFirst.Six", msg.getE64MA1());
        addDataNode(data, prefix + "MailAddressFirst.Seven", msg.getE74MA1());
        addDataNode(data, prefix + "MailAddressFirst.Eight", msg.getE84MA1());
        addDataNode(data, prefix + "MailAddressFirst.Nine", msg.getE94MA1());
        addDataNode(data, prefix + "MailAddressFirst.Ten", msg.getEA4MA1());
        addDataNode(data, prefix + "MailAddressSecond.One", msg.getE14MA2());
        addDataNode(data, prefix + "MailAddressSecond.Two", msg.getE24MA2());
        addDataNode(data, prefix + "MailAddressSecond.Three", msg.getE34MA2());
        addDataNode(data, prefix + "MailAddressSecond.Four", msg.getE44MA2());
        addDataNode(data, prefix + "MailAddressSecond.Five", msg.getE54MA2());
        addDataNode(data, prefix + "MailAddressSecond.Six", msg.getE64MA2());
        addDataNode(data, prefix + "MailAddressSecond.Seven", msg.getE74MA2());
        addDataNode(data, prefix + "MailAddressSecond.Eight", msg.getE84MA2());
        addDataNode(data, prefix + "MailAddressSecond.Nine", msg.getE94MA2());
        addDataNode(data, prefix + "MailAddressSecond.Ten", msg.getEA4MA2());
        if(prefix.equals("BankRef."))
        {
            addDataNode(data, prefix + "MailAddressThird.One", msg.getE14MA3());
            addDataNode(data, prefix + "MailAddressThird.Two", msg.getE24MA3());
            addDataNode(data, prefix + "MailAddressThird.Three", msg.getE34MA3());
            addDataNode(data, prefix + "MailAddressThird.Four", msg.getE44MA3());
            addDataNode(data, prefix + "MailAddressThird.Five", msg.getE54MA3());
            addDataNode(data, prefix + "MailAddressThird.Six", msg.getE64MA3());
            addDataNode(data, prefix + "MailAddressThird.Seven", msg.getE74MA3());
            addDataNode(data, prefix + "MailAddressThird.Eight", msg.getE84MA3());
            addDataNode(data, prefix + "MailAddressThird.Nine", msg.getE94MA3());
            addDataNode(data, prefix + "MailAddressThird.Ten", msg.getEA4MA3());
        } else
        if(prefix.equals("Board."))
        {
            addDataNode(data, prefix + "MailAddressThird.One", msg.getE14MA2() + " " + msg.getE14MA3());
            addDataNode(data, prefix + "MailAddressThird.Two", msg.getE24MA2() + " " + msg.getE24MA3());
            addDataNode(data, prefix + "MailAddressThird.Three", msg.getE34MA2() + " " + msg.getE34MA3());
            addDataNode(data, prefix + "MailAddressThird.Four", msg.getE44MA2() + " " + msg.getE44MA3());
            addDataNode(data, prefix + "MailAddressThird.Five", msg.getE54MA2() + " " + msg.getE54MA3());
            addDataNode(data, prefix + "MailAddressThird.Six", msg.getE64MA2() + " " + msg.getE64MA3());
            addDataNode(data, prefix + "MailAddressThird.Seven", msg.getE74MA2() + " " + msg.getE74MA3());
            addDataNode(data, prefix + "MailAddressThird.Eight", msg.getE84MA2() + " " + msg.getE84MA3());
            addDataNode(data, prefix + "MailAddressThird.Nine", msg.getE94MA2() + " " + msg.getE94MA3());
            addDataNode(data, prefix + "MailAddressThird.Ten", msg.getEA4MA2() + " " + msg.getEA4MA3());
        } else
        {
            addDataNode(data, prefix + "MailAddressThird.One", msg.getE14MA2() + " " + msg.getE14MA3() + " " + msg.getE14MA4());
            addDataNode(data, prefix + "MailAddressThird.Two", msg.getE24MA2() + " " + msg.getE24MA3() + " " + msg.getE24MA3());
            addDataNode(data, prefix + "MailAddressThird.Three", msg.getE34MA2() + " " + msg.getE34MA3() + " " + msg.getE34MA4());
            addDataNode(data, prefix + "MailAddressThird.Four", msg.getE44MA2() + " " + msg.getE44MA3() + " " + msg.getE44MA4());
            addDataNode(data, prefix + "MailAddressThird.Five", msg.getE54MA2() + " " + msg.getE54MA3() + " " + msg.getE54MA4());
            addDataNode(data, prefix + "MailAddressThird.Six", msg.getE64MA2() + " " + msg.getE64MA3() + " " + msg.getE64MA4());
            addDataNode(data, prefix + "MailAddressThird.Seven", msg.getE74MA2() + " " + msg.getE74MA3() + " " + msg.getE74MA4());
            addDataNode(data, prefix + "MailAddressThird.Eight", msg.getE84MA2() + " " + msg.getE84MA3() + " " + msg.getE84MA4());
            addDataNode(data, prefix + "MailAddressThird.Nine", msg.getE94MA2() + " " + msg.getE94MA3() + " " + msg.getE94MA4());
            addDataNode(data, prefix + "MailAddressThird.Ten", msg.getEA4MA2() + " " + msg.getEA4MA3() + " " + msg.getEA4MA4());
        }
        addDataNode(data, prefix + "MailAddressFourth.One", msg.getE14MA4());
        addDataNode(data, prefix + "MailAddressFourth.Two", msg.getE24MA4());
        addDataNode(data, prefix + "MailAddressFourth.Three", msg.getE34MA4());
        addDataNode(data, prefix + "MailAddressFourth.Four", msg.getE44MA4());
        addDataNode(data, prefix + "MailAddressFourth.Five", msg.getE54MA4());
        addDataNode(data, prefix + "MailAddressFourth.Six", msg.getE64MA4());
        addDataNode(data, prefix + "MailAddressFourth.Seven", msg.getE74MA4());
        addDataNode(data, prefix + "MailAddressFourth.Eight", msg.getE84MA4());
        addDataNode(data, prefix + "MailAddressFourth.Nine", msg.getE94MA4());
        addDataNode(data, prefix + "MailAddressFourth.Ten", msg.getEA4MA4());
        if(msg.getE14CTY().length() > 4)
            addDataNode(data, prefix + "City.One", msg.getE14CTY().substring(4, msg.getE14CTY().length()));
        else
            addDataNode(data, prefix + "City.One", msg.getE14CTY());
        if(msg.getE24CTY().length() > 4)
            addDataNode(data, prefix + "City.Two", msg.getE24CTY().substring(4, msg.getE24CTY().length()));
        else
            addDataNode(data, prefix + "City.Two", msg.getE24CTY());
        if(msg.getE34CTY().length() > 4)
            addDataNode(data, prefix + "City.Three", msg.getE34CTY().substring(4, msg.getE34CTY().length()));
        else
            addDataNode(data, prefix + "City.Three", msg.getE34CTY());
        if(msg.getE44CTY().length() > 4)
            addDataNode(data, prefix + "City.Four", msg.getE44CTY().substring(4, msg.getE44CTY().length()));
        else
            addDataNode(data, prefix + "City.Four", msg.getE44CTY());
        if(msg.getE54CTY().length() > 4)
            addDataNode(data, prefix + "City.Five", msg.getE54CTY().substring(4, msg.getE54CTY().length()));
        else
            addDataNode(data, prefix + "City.Five", msg.getE54CTY());
        if(msg.getE64CTY().length() > 4)
            addDataNode(data, prefix + "City.Six", msg.getE64CTY().substring(4, msg.getE64CTY().length()));
        else
            addDataNode(data, prefix + "City.Six", msg.getE64CTY());
        if(msg.getE74CTY().length() > 4)
            addDataNode(data, prefix + "City.Seven", msg.getE74CTY().substring(4, msg.getE74CTY().length()));
        else
            addDataNode(data, prefix + "City.Seven", msg.getE74CTY());
        if(msg.getE84CTY().length() > 4)
            addDataNode(data, prefix + "City.Eight", msg.getE84CTY().substring(4, msg.getE84CTY().length()));
        else
            addDataNode(data, prefix + "City.Eight", msg.getE84CTY());
        if(msg.getE94CTY().length() > 4)
            addDataNode(data, prefix + "City.Nine", msg.getE94CTY().substring(4, msg.getE94CTY().length()));
        else
            addDataNode(data, prefix + "City.Nine", msg.getE94CTY());
        if(msg.getEA4CTY().length() > 4)
            addDataNode(data, prefix + "City.Ten", msg.getEA4CTY().substring(4, msg.getEA4CTY().length()));
        else
            addDataNode(data, prefix + "City.Ten", msg.getEA4CTY());
        addDataNode(data, prefix + "Region.One", msg.getD14UC2());
        addDataNode(data, prefix + "Region.Two", msg.getD24UC2());
        addDataNode(data, prefix + "Region.Three", msg.getD34UC2());
        addDataNode(data, prefix + "Region.Four", msg.getD44UC2());
        addDataNode(data, prefix + "Region.Five", msg.getD54UC2());
        addDataNode(data, prefix + "Region.Six", msg.getD64UC2());
        addDataNode(data, prefix + "Region.Seven", msg.getD74UC2());
        addDataNode(data, prefix + "Region.Eight", msg.getD84UC2());
        addDataNode(data, prefix + "Region.Nine", msg.getD94UC2());
        addDataNode(data, prefix + "Region.Ten", msg.getDA4UC2());
        addDataNode(data, prefix + "State.One", msg.getD14STE());
        addDataNode(data, prefix + "State.Two", msg.getD24STE());
        addDataNode(data, prefix + "State.Three", msg.getD34STE());
        addDataNode(data, prefix + "State.Four", msg.getD44STE());
        addDataNode(data, prefix + "State.Five", msg.getD54STE());
        addDataNode(data, prefix + "State.Six", msg.getD64STE());
        addDataNode(data, prefix + "State.Seven", msg.getD74STE());
        addDataNode(data, prefix + "State.Eight", msg.getD84STE());
        addDataNode(data, prefix + "State.Nine", msg.getD94STE());
        addDataNode(data, prefix + "State.Ten", msg.getDA4STE());
        addDataNode(data, prefix + "ZipCode.One", msg.getE14ZPC());
        addDataNode(data, prefix + "ZipCode.Two", msg.getE24ZPC());
        addDataNode(data, prefix + "ZipCode.Three", msg.getE34ZPC());
        addDataNode(data, prefix + "ZipCode.Four", msg.getE44ZPC());
        addDataNode(data, prefix + "ZipCode.Five", msg.getE54ZPC());
        addDataNode(data, prefix + "ZipCode.Six", msg.getE64ZPC());
        addDataNode(data, prefix + "ZipCode.Seven", msg.getE74ZPC());
        addDataNode(data, prefix + "ZipCode.Eight", msg.getE84ZPC());
        addDataNode(data, prefix + "ZipCode.Nine", msg.getE94ZPC());
        addDataNode(data, prefix + "ZipCode.Ten", msg.getEA4ZPC());
        addDataNode(data, prefix + "POBox.One", msg.getE14POB());
        addDataNode(data, prefix + "POBox.Two", msg.getE24POB());
        addDataNode(data, prefix + "POBox.Three", msg.getE34POB());
        addDataNode(data, prefix + "POBox.Four", msg.getE44POB());
        addDataNode(data, prefix + "POBox.Five", msg.getE54POB());
        addDataNode(data, prefix + "POBox.Six", msg.getE64POB());
        addDataNode(data, prefix + "POBox.Seven", msg.getE74POB());
        addDataNode(data, prefix + "POBox.Eight", msg.getE84POB());
        addDataNode(data, prefix + "POBox.Nine", msg.getE94POB());
        addDataNode(data, prefix + "POBox.Ten", msg.getEA4POB());
        addDataNode(data, prefix + "Country.One", msg.getE14CTR());
        addDataNode(data, prefix + "Country.Two", msg.getE24CTR());
        addDataNode(data, prefix + "Country.Three", msg.getE34CTR());
        addDataNode(data, prefix + "Country.Four", msg.getE44CTR());
        addDataNode(data, prefix + "Country.Five", msg.getE54CTR());
        addDataNode(data, prefix + "Country.Six", msg.getE64CTR());
        addDataNode(data, prefix + "Country.Seven", msg.getE74CTR());
        addDataNode(data, prefix + "Country.Eight", msg.getE84CTR());
        addDataNode(data, prefix + "Country.Nine", msg.getE94CTR());
        addDataNode(data, prefix + "Country.Ten", msg.getEA4CTR());
        addDataNode(data, prefix + "MailCode.One", msg.getE14MLC());
        addDataNode(data, prefix + "MailCode.Two", msg.getE24MLC());
        addDataNode(data, prefix + "MailCode.Three", msg.getE34MLC());
        addDataNode(data, prefix + "MailCode.Four", msg.getE44MLC());
        addDataNode(data, prefix + "MailCode.Five", msg.getE54MLC());
        addDataNode(data, prefix + "MailCode.Six", msg.getE64MLC());
        addDataNode(data, prefix + "MailCode.Seven", msg.getE74MLC());
        addDataNode(data, prefix + "MailCode.Eight", msg.getE84MLC());
        addDataNode(data, prefix + "MailCode.Nine", msg.getE94MLC());
        addDataNode(data, prefix + "MailCode.Ten", msg.getEA4MLC());
        addDataNode(data, prefix + "HomePhone.One", msg.getE14HPN());
        addDataNode(data, prefix + "HomePhone.Two", msg.getE24HPN());
        addDataNode(data, prefix + "HomePhone.Three", msg.getE34HPN());
        addDataNode(data, prefix + "HomePhone.Four", msg.getE44HPN());
        addDataNode(data, prefix + "HomePhone.Five", msg.getE54HPN());
        addDataNode(data, prefix + "HomePhone.Six", msg.getE64HPN());
        addDataNode(data, prefix + "HomePhone.Seven", msg.getE74HPN());
        addDataNode(data, prefix + "HomePhone.Eight", msg.getE84HPN());
        addDataNode(data, prefix + "HomePhone.Nine", msg.getE94HPN());
        addDataNode(data, prefix + "HomePhone.Ten", msg.getEA4HPN());
        addDataNode(data, prefix + "Citizenship.One", msg.getE14BNC());
        addDataNode(data, prefix + "Citizenship.Two", msg.getE24BNC());
        addDataNode(data, prefix + "Citizenship.Three", msg.getE34BNC());
        addDataNode(data, prefix + "Citizenship.Four", msg.getE44BNC());
        addDataNode(data, prefix + "Citizenship.Five", msg.getE54BNC());
        addDataNode(data, prefix + "Citizenship.Six", msg.getE64BNC());
        addDataNode(data, prefix + "Citizenship.Seven", msg.getE74BNC());
        addDataNode(data, prefix + "Citizenship.Eight", msg.getE84BNC());
        addDataNode(data, prefix + "Citizenship.Nine", msg.getE94BNC());
        addDataNode(data, prefix + "Citizenship.Ten", msg.getEA4BNC());
        addDataNode(data, prefix + "Identification.One", msg.getE14BNI());
        addDataNode(data, prefix + "Identification.Two", msg.getE24BNI());
        addDataNode(data, prefix + "Identification.Three", msg.getE34BNI());
        addDataNode(data, prefix + "Identification.Four", msg.getE44BNI());
        addDataNode(data, prefix + "Identification.Five", msg.getE54BNI());
        addDataNode(data, prefix + "Identification.Six", msg.getE64BNI());
        addDataNode(data, prefix + "Identification.Seven", msg.getE74BNI());
        addDataNode(data, prefix + "Identification.Eight", msg.getE84BNI());
        addDataNode(data, prefix + "Identification.Nine", msg.getE94BNI());
        addDataNode(data, prefix + "Identification.Ten", msg.getEA4BNI());
        addDataNode(data, prefix + "Position.One", msg.getE14INC());
        addDataNode(data, prefix + "Position.Two", msg.getE24INC());
        addDataNode(data, prefix + "Position.Three", msg.getE34INC());
        addDataNode(data, prefix + "Position.Four", msg.getE44INC());
        addDataNode(data, prefix + "Position.Five", msg.getE54INC());
        addDataNode(data, prefix + "Position.Six", msg.getE64INC());
        addDataNode(data, prefix + "Position.Seven", msg.getE74INC());
        addDataNode(data, prefix + "Position.Eight", msg.getE84INC());
        addDataNode(data, prefix + "Position.Nine", msg.getE94INC());
        addDataNode(data, prefix + "Position.Ten", msg.getEA4INC());
        addDataNode(data, prefix + "Position.Description.One", msg.getD14INC());
        addDataNode(data, prefix + "Position.Description.Two", msg.getD24INC());
        addDataNode(data, prefix + "Position.Description.Three", msg.getD34INC());
        addDataNode(data, prefix + "Position.Description.Four", msg.getD44INC());
        addDataNode(data, prefix + "Position.Description.Five", msg.getD54INC());
        addDataNode(data, prefix + "Position.Description.Six", msg.getD64INC());
        addDataNode(data, prefix + "Position.Description.Seven", msg.getD74INC());
        addDataNode(data, prefix + "Position.Description.Eight", msg.getD84INC());
        addDataNode(data, prefix + "Position.Description.Nine", msg.getD94INC());
        addDataNode(data, prefix + "Position.Description.Ten", msg.getDA4INC());
        if(prefix.equals("BankRef."))
        {
            addDataNode(data, prefix + "Gender.One", msg.getE14MA2());
            addDataNode(data, prefix + "Gender.Two", msg.getE24MA2());
            addDataNode(data, prefix + "Gender.Three", msg.getE34MA2());
            addDataNode(data, prefix + "Gender.Four", msg.getE44MA2());
            addDataNode(data, prefix + "Gender.Five", msg.getE54MA2());
            addDataNode(data, prefix + "Gender.Six", msg.getE64MA2());
            addDataNode(data, prefix + "Gender.Seven", msg.getE74MA2());
            addDataNode(data, prefix + "Gender.Eight", msg.getE84MA2());
            addDataNode(data, prefix + "Gender.Nine", msg.getE94MA2());
            addDataNode(data, prefix + "Gender.Ten", msg.getEA4MA2());
        } else
        {
            addDataNode(data, prefix + "Gender.One", msg.getE14BSX());
            addDataNode(data, prefix + "Gender.Two", msg.getE24BSX());
            addDataNode(data, prefix + "Gender.Three", msg.getE34BSX());
            addDataNode(data, prefix + "Gender.Four", msg.getE44BSX());
            addDataNode(data, prefix + "Gender.Five", msg.getE54BSX());
            addDataNode(data, prefix + "Gender.Six", msg.getE64BSX());
            addDataNode(data, prefix + "Gender.Seven", msg.getE74BSX());
            addDataNode(data, prefix + "Gender.Eight", msg.getE84BSX());
            addDataNode(data, prefix + "Gender.Nine", msg.getE94BSX());
            addDataNode(data, prefix + "Gender.Ten", msg.getEA4BSX());
        }
        addDataNode(data, prefix + "MaritalStatus.One", msg.getE14BMS());
        addDataNode(data, prefix + "MaritalStatus.Two", msg.getE24BMS());
        addDataNode(data, prefix + "MaritalStatus.Three", msg.getE34BMS());
        addDataNode(data, prefix + "MaritalStatus.Four", msg.getE44BMS());
        addDataNode(data, prefix + "MaritalStatus.Five", msg.getE54BMS());
        addDataNode(data, prefix + "MaritalStatus.Six", msg.getE64BMS());
        addDataNode(data, prefix + "MaritalStatus.Seven", msg.getE74BMS());
        addDataNode(data, prefix + "MaritalStatus.Eight", msg.getE84BMS());
        addDataNode(data, prefix + "MaritalStatus.Nine", msg.getE94BMS());
        addDataNode(data, prefix + "MaritalStatus.Ten", msg.getEA4BMS());
        addDataNode(data, prefix + "NumberOfStocks.One", msg.getE14NST());
        addDataNode(data, prefix + "NumberOfStocks.Two", msg.getE24NST());
        addDataNode(data, prefix + "NumberOfStocks.Three", msg.getE34NST());
        addDataNode(data, prefix + "NumberOfStocks.Four", msg.getE44NST());
        addDataNode(data, prefix + "NumberOfStocks.Five", msg.getE54NST());
        addDataNode(data, prefix + "NumberOfStocks.Six", msg.getE64NST());
        addDataNode(data, prefix + "NumberOfStocks.Seven", msg.getE74NST());
        addDataNode(data, prefix + "NumberOfStocks.Eight", msg.getE84NST());
        addDataNode(data, prefix + "NumberOfStocks.Nine", msg.getE94NST());
        addDataNode(data, prefix + "NumberOfStocks.Ten", msg.getEA4NST());
        addDataNode(data, prefix + "RelatedCustomerNumber.One", msg.getE14RCN());
        addDataNode(data, prefix + "RelatedCustomerNumber.Two", msg.getE24RCN());
        addDataNode(data, prefix + "RelatedCustomerNumber.Three", msg.getE34RCN());
        addDataNode(data, prefix + "RelatedCustomerNumber.Four", msg.getE44RCN());
        addDataNode(data, prefix + "RelatedCustomerNumber.Five", msg.getE54RCN());
        addDataNode(data, prefix + "RelatedCustomerNumber.Six", msg.getE64RCN());
        addDataNode(data, prefix + "RelatedCustomerNumber.Seven", msg.getE74RCN());
        addDataNode(data, prefix + "RelatedCustomerNumber.Eight", msg.getE84RCN());
        addDataNode(data, prefix + "RelatedCustomerNumber.Nine", msg.getE94RCN());
        addDataNode(data, prefix + "RelatedCustomerNumber.Ten", msg.getEA4RCN());
        addDataNode(data, prefix + "IdentificationType.One", msg.getE14TID());
        addDataNode(data, prefix + "IdentificationType.Two", msg.getE24TID());
        addDataNode(data, prefix + "IdentificationType.Three", msg.getE34TID());
        addDataNode(data, prefix + "IdentificationType.Four", msg.getE44TID());
        addDataNode(data, prefix + "IdentificationType.Five", msg.getE54TID());
        addDataNode(data, prefix + "IdentificationType.Six", msg.getE64TID());
        addDataNode(data, prefix + "IdentificationType.Seven", msg.getE74TID());
        addDataNode(data, prefix + "IdentificationType.Eight", msg.getE84TID());
        addDataNode(data, prefix + "IdentificationType.Nine", msg.getE94TID());
        addDataNode(data, prefix + "IdentificationType.Ten", msg.getEA4TID());
        addDataNode(data, prefix + "IdentificationType.Description.One", msg.getD14TID());
        addDataNode(data, prefix + "IdentificationType.Description.Two", msg.getD24TID());
        addDataNode(data, prefix + "IdentificationType.Description.Three", msg.getD34TID());
        addDataNode(data, prefix + "IdentificationType.Description.Four", msg.getD44TID());
        addDataNode(data, prefix + "IdentificationType.Description.Five", msg.getD54TID());
        addDataNode(data, prefix + "IdentificationType.Description.Six", msg.getD64TID());
        addDataNode(data, prefix + "IdentificationType.Description.Seven", msg.getD74TID());
        addDataNode(data, prefix + "IdentificationType.Description.Eight", msg.getD84TID());
        addDataNode(data, prefix + "IdentificationType.Description.Nine", msg.getD94TID());
        addDataNode(data, prefix + "IdentificationType.Description.Ten", msg.getDA4TID());
        addDataNode(data, prefix + "IdentificationCountry.One", msg.getE14PID());
        addDataNode(data, prefix + "IdentificationCountry.Two", msg.getE24PID());
        addDataNode(data, prefix + "IdentificationCountry.Three", msg.getE34PID());
        addDataNode(data, prefix + "IdentificationCountry.Four", msg.getE44PID());
        addDataNode(data, prefix + "IdentificationCountry.Five", msg.getE54PID());
        addDataNode(data, prefix + "IdentificationCountry.Six", msg.getE64PID());
        addDataNode(data, prefix + "IdentificationCountry.Seven", msg.getE74PID());
        addDataNode(data, prefix + "IdentificationCountry.Eight", msg.getE84PID());
        addDataNode(data, prefix + "IdentificationCountry.Nine", msg.getE94PID());
        addDataNode(data, prefix + "IdentificationCountry.Ten", msg.getEA4PID());
        addDataNode(data, prefix + "IdentificationCountry.Description.One", msg.getD14PID());
        addDataNode(data, prefix + "IdentificationCountry.Description.Two", msg.getD24PID());
        addDataNode(data, prefix + "IdentificationCountry.Description.Three", msg.getD34PID());
        addDataNode(data, prefix + "IdentificationCountry.Description.Four", msg.getD44PID());
        addDataNode(data, prefix + "IdentificationCountry.Description.Five", msg.getD54PID());
        addDataNode(data, prefix + "IdentificationCountry.Description.Six", msg.getD64PID());
        addDataNode(data, prefix + "IdentificationCountry.Description.Seven", msg.getD74PID());
        addDataNode(data, prefix + "IdentificationCountry.Description.Eight", msg.getD84PID());
        addDataNode(data, prefix + "IdentificationCountry.Description.Nine", msg.getD94PID());
        addDataNode(data, prefix + "IdentificationCountry.Description.Ten", msg.getDA4PID());
        if(prefix.equals("BankRef."))
        {
            String prefixes[] = {
                "AmountFirst.One", "AmountFirst.Two", "AmountFirst.Three", "AmountFirst.Four", "AmountFirst.Five", "AmountFirst.Six", "AmountFirst.Seven", "AmountFirst.Eight", "AmountFirst.Nine", "AmountFirst.Ten"
            };
            String cifras[] = {
                msg.getE14BSX(), msg.getE24BSX(), msg.getE34BSX(), msg.getE44BSX(), msg.getE54BSX(), msg.getE64BSX(), msg.getE74BSX(), msg.getE84BSX(), msg.getE94BSX(), msg.getEA4BSX()
            };
            String altaBaja[] = {
                msg.getE14BMS(), msg.getE24BMS(), msg.getE34BMS(), msg.getE44BMS(), msg.getE54BMS(), msg.getE64BMS(), msg.getE74BMS(), msg.getE84BMS(), msg.getE94BMS(), msg.getEA4BMS()
            };
            for(int i = 0; i < 10; i++)
            {
                String value = "";
                if(cifras[i].length() > 0)
                    value = cifras[i] + " cifras ";
                if(altaBaja[i].length() > 0)
                    if(altaBaja[i].toUpperCase().equals("A"))
                        value = value + "altas";
                    else
                    if(altaBaja[i].toUpperCase().equals("M"))
                        value = value + "medias";
                    else
                        value = value + "bajas";
                addDataNode(data, prefix + prefixes[i], value);
            }

        } else
        {
            addDataNode(data, prefix + "AmountFirst.One", msg.getE14AM1());
            addDataNode(data, prefix + "AmountFirst.Two", msg.getE24AM1());
            addDataNode(data, prefix + "AmountFirst.Three", msg.getE34AM1());
            addDataNode(data, prefix + "AmountFirst.Four", msg.getE44AM1());
            addDataNode(data, prefix + "AmountFirst.Five", msg.getE54AM1());
            addDataNode(data, prefix + "AmountFirst.Six", msg.getE64AM1());
            addDataNode(data, prefix + "AmountFirst.Seven", msg.getE74AM1());
            addDataNode(data, prefix + "AmountFirst.Eight", msg.getE84AM1());
            addDataNode(data, prefix + "AmountFirst.Nine", msg.getE94AM1());
            addDataNode(data, prefix + "AmountFirst.Ten", msg.getEA4AM1());
        }
        addDataNode(data, prefix + "AmountSecond.One", msg.getE14AM2());
        addDataNode(data, prefix + "AmountSecond.Two", msg.getE24AM2());
        addDataNode(data, prefix + "AmountSecond.Three", msg.getE34AM2());
        addDataNode(data, prefix + "AmountSecond.Four", msg.getE44AM2());
        addDataNode(data, prefix + "AmountSecond.Five", msg.getE54AM2());
        addDataNode(data, prefix + "AmountSecond.Six", msg.getE64AM2());
        addDataNode(data, prefix + "AmountSecond.Seven", msg.getE74AM2());
        addDataNode(data, prefix + "AmountSecond.Eight", msg.getE84AM2());
        addDataNode(data, prefix + "AmountSecond.Nine", msg.getE94AM2());
        addDataNode(data, prefix + "AmountSecond.Ten", msg.getEA4AM2());
        addDataNode(data, prefix + "Date.One", Util.formatDate(msg.getE14DT1(), msg.getE14DT2(), msg.getE14DT3()));
        addDataNode(data, prefix + "Date.Two", Util.formatDate(msg.getE24DT1(), msg.getE24DT2(), msg.getE24DT3()));
        addDataNode(data, prefix + "Date.Three", Util.formatDate(msg.getE34DT1(), msg.getE34DT2(), msg.getE34DT3()));
        addDataNode(data, prefix + "Date.Four", Util.formatDate(msg.getE44DT1(), msg.getE44DT2(), msg.getE44DT3()));
        addDataNode(data, prefix + "Date.Five", Util.formatDate(msg.getE54DT1(), msg.getE54DT2(), msg.getE54DT3()));
        addDataNode(data, prefix + "Date.Six", Util.formatDate(msg.getE64DT1(), msg.getE64DT2(), msg.getE64DT3()));
        addDataNode(data, prefix + "Date.Seven", Util.formatDate(msg.getE74DT1(), msg.getE74DT2(), msg.getE74DT3()));
        addDataNode(data, prefix + "Date.Eight", Util.formatDate(msg.getE84DT1(), msg.getE84DT2(), msg.getE84DT3()));
        addDataNode(data, prefix + "Date.Nine", Util.formatDate(msg.getE94DT1(), msg.getE94DT2(), msg.getE94DT3()));
        addDataNode(data, prefix + "Date.Ten", Util.formatDate(msg.getEA4DT1(), msg.getEA4DT2(), msg.getEA4DT3()));
        addDataNode(data, prefix + "RelationType.One", msg.getE14RTY());
        addDataNode(data, prefix + "RelationType.Two", msg.getE24RTY());
        addDataNode(data, prefix + "RelationType.Three", msg.getE34RTY());
        addDataNode(data, prefix + "RelationType.Four", msg.getE44RTY());
        addDataNode(data, prefix + "RelationType.Five", msg.getE54RTY());
        addDataNode(data, prefix + "RelationType.Six", msg.getE64RTY());
        addDataNode(data, prefix + "RelationType.Seven", msg.getE74RTY());
        addDataNode(data, prefix + "RelationType.Eight", msg.getE84RTY());
        addDataNode(data, prefix + "RelationType.Nine", msg.getE94RTY());
        addDataNode(data, prefix + "RelationType.Ten", msg.getEA4RTY());
        addDataNode(data, prefix + "FlagFirst.One", msg.getE14FL1());
        addDataNode(data, prefix + "FlagFirst.Two", msg.getE24FL1());
        addDataNode(data, prefix + "FlagFirst.Three", msg.getE34FL1());
        addDataNode(data, prefix + "FlagFirst.Four", msg.getE44FL1());
        addDataNode(data, prefix + "FlagFirst.Five", msg.getE54FL1());
        addDataNode(data, prefix + "FlagFirst.Six", msg.getE64FL1());
        addDataNode(data, prefix + "FlagFirst.Seven", msg.getE74FL1());
        addDataNode(data, prefix + "FlagFirst.Eight", msg.getE84FL1());
        addDataNode(data, prefix + "FlagFirst.Nine", msg.getE94FL1());
        addDataNode(data, prefix + "FlagFirst.Ten", msg.getEA4FL1());
        addDataNode(data, prefix + "FlagSecond.One", msg.getE14FL2());
        addDataNode(data, prefix + "FlagSecond.Two", msg.getE24FL2());
        addDataNode(data, prefix + "FlagSecond.Three", msg.getE34FL2());
        addDataNode(data, prefix + "FlagSecond.Four", msg.getE44FL2());
        addDataNode(data, prefix + "FlagSecond.Five", msg.getE54FL2());
        addDataNode(data, prefix + "FlagSecond.Six", msg.getE64FL2());
        addDataNode(data, prefix + "FlagSecond.Seven", msg.getE74FL2());
        addDataNode(data, prefix + "FlagSecond.Eight", msg.getE84FL2());
        addDataNode(data, prefix + "FlagSecond.Nine", msg.getE94FL2());
        addDataNode(data, prefix + "FlagSecond.Ten", msg.getEA4FL2());
        addDataNode(data, prefix + "FlagThird.One", msg.getE14FL3());
        addDataNode(data, prefix + "FlagThird.Two", msg.getE24FL3());
        addDataNode(data, prefix + "FlagThird.Three", msg.getE34FL3());
        addDataNode(data, prefix + "FlagThird.Four", msg.getE44FL3());
        addDataNode(data, prefix + "FlagThird.Five", msg.getE54FL3());
        addDataNode(data, prefix + "FlagThird.Six", msg.getE64FL3());
        addDataNode(data, prefix + "FlagThird.Seven", msg.getE74FL3());
        addDataNode(data, prefix + "FlagThird.Eight", msg.getE84FL3());
        addDataNode(data, prefix + "FlagThird.Nine", msg.getE94FL3());
        addDataNode(data, prefix + "FlagThird.Ten", msg.getEA4FL3());
    }
    
    protected String getRolloverInstructions(MessageRecord message){
		EFRM00010Message msg = (EFRM00010Message) message;
		String RenovationInst = "";
		if(msg.getE10DEAROC().equals("A"))
			RenovationInst = "Principal mas intereses seran renovados por el mismo periodo";
		else
		if(msg.getE10DEAROC().equals("B"))
			RenovationInst = "Interes sera acreditado a la cuenta " + msg.getE10DEARAC() + ". El principal sera renovado por el mismo periodo.";
		else
		if(msg.getE10DEAROC().equals("C"))
			RenovationInst = "Interes sera acreditado a la cuenta contable " + msg.getE10DEARAC() + ",generando un cheque de gerencia o por transferencia de fondos." + " El principal sera renovado por el mismo periodo de tiempo. ";
		else
		if(msg.getE10DEAROC().equals("D"))
			RenovationInst = " Al vencimiento cancelar el Deposito mas los intereses acreditando a la cuenta contable " + msg.getE10DEARAC() + ", emitiendo un cheque de gerencia o por transferencia de fondos.";
		else
		if(msg.getE10DEAROC().equals("E"))
			RenovationInst = "Al vencimiento cancelar Deposito mas los intereses  acreditandolos a la cuenta numero " + msg.getE10DEARAC();
		else
		if(msg.getE10DEAROC().equals("F"))
			RenovationInst = "Al vencimiento renovar el Deposito y los intereses incrementando/disminuyendo los intereses  contra la cuenta numero " + msg.getE10DEARAC();
		else
		if(msg.getE10DEAROC().equals("G"))
			RenovationInst = "Al vencimiento renovar el Deposito y los intereses incrementando/disminuyendo los intereses  contra la cuenta numero " + msg.getE10DEARAC();
		else
		if(msg.getE10DEAROC().equals("H"))
			RenovationInst = "Interes sera pagado periodicamente a la cuenta " + msg.getE10DEARAC() + ". El principal sera renovado por el mismo periodo.";
		else
		if(msg.getE10DEAROC().equals("I"))
			RenovationInst = "Interes sera pagado periodicamente a la cuenta contable numero " + msg.getE10DEARAC() + " , emitiendo un cheque de gerencia o por transferencia de fondos" + ". El principal sera renovado por el mismo periodo.";
		else
		if(msg.getE10DEAROC().equals("J"))
			RenovationInst = "El interes y el principal seran pagados al  certificado numero " + msg.getE10DEARAC() + " . No hay renovacion en esta opcion.";
		else
		if(msg.getE10DEAROC().equals("K"))
			RenovationInst = "Interes sera pagado periodicamente a la cuenta  numero " + msg.getE10DEARAC();
		else
		if(msg.getE10DEAROC().equals("P"))
			RenovationInst = "El deposito no tiene instruccion de renovacion, sera renovado  automaticamente despues de un periodo de espera.";
		else
		if(msg.getE10DEAROC().equals("S"))
			RenovationInst = "Interes y principal seran pagados basados en un plan de pagos definido previamente";
		else
			RenovationInst = "Sin instruccion de renovacion";
		return RenovationInst;
    }

    protected void getCDDataForDataproForms(Object data, EFRM00010Message msg)
    {
        String dataItem = null;
        String nl = eIBSFormsNL;
        addDataNode(data, "CertificateOfDeposit.Basic.DEAACC", msg.getE10DEAACC());
        addDataNode(data, "CertificateOfDeposit.Basic.DEABNK", msg.getE10DEABNK());
        addDataNode(data, "CertificateOfDeposit.Basic.DEABRN", msg.getE10DEABRN());
        addDataNode(data, "CertificateOfDeposit.Basic.DEACCY", msg.getE10DEACCY());
        addDataNode(data, "CertificateOfDeposit.Basic.DEAGLN", msg.getE10DEAGLN());
        addDataNode(data, "CertificateOfDeposit.Basic.DEAACD", msg.getE10DEAACD());
        addDataNode(data, "CertificateOfDeposit.Basic.DEAATY", msg.getE10DEAATY());
        addDataNode(data, "CertificateOfDeposit.Basic.DEAPRO", msg.getE10DEAPRO());
        addDataNode(data, "CertificateOfDeposit.Basic.DEACUN", msg.getE10DEACUN());
        String initialDate = Util.formatDate(Util.addLeftChar('0', 6, msg.getE10OPNDTE()));
        String fechaEmision = Util.formatCustomDate(
        	ftmEibs, "dd 'de' MMMM 'del' yyyy", userLanguage, 
        	initialDate.substring(0, 2), initialDate.substring(3, 5), initialDate.substring(6, 8));
        addDataNode(data, "CertificateOfDeposit.Basic.OPNDTE", fechaEmision);
        addDataNode(data, "CertificateOfDeposit.Basic.DEATRM", msg.getE10DEATRM() + " " + msg.getE10DEATRC());
        addDataNode(data, "CertificateOfDeposit.Basic.NUMDEP", msg.getE10NUMDEP());
        addDataNode(data, "CertificateOfDeposit.Basic.DEPAMT", Util.formatCCY(msg.getE10DEPAMT()));
        addDataNode(data, "CertificateOfDeposit.Basic.DEPLET", msg.getE10DEPLET());
        addDataNode(data, "CertificateOfDeposit.Basic.MATDTE", Util.formatDate(msg.getE10MATDTE()));
        addDataNode(data, "CertificateOfDeposit.Basic.DEPMIN", Util.formatCCY(msg.getE10MATDTE()));
        addDataNode(data, "CertificateOfDeposit.Basic.FLGMIN", msg.getE10FLGMIN());
        addDataNode(data, "CertificateOfDeposit.Basic.WDINYS", msg.getE10WDINYS());
        addDataNode(data, "CertificateOfDeposit.Basic.WDINCR", msg.getE10WDINCR());
        addDataNode(data, "CertificateOfDeposit.Basic.INTTRM", msg.getE10INTTRM());
        addDataNode(data, "CertificateOfDeposit.Basic.INTRTE", msg.getE10INTRTE());
        addDataNode(data, "CertificateOfDeposit.Basic.DEAMEP", msg.getE10DEAMEP());
        addDataNode(data, "CertificateOfDeposit.Basic.MATINT", msg.getE10MATINT());
        addDataNode(data, "CertificateOfDeposit.Basic.DEAIPL", msg.getE10DEAIPL());
        addDataNode(data, "CertificateOfDeposit.Basic.TOTVENC", msg.getE10TOTSAL());
        addDataNode(data, "CertificateOfDeposit.Basic.YELRTE", msg.getE10YELRTE());
        addDataNode(data, "CertificateOfDeposit.Basic.CTACPA", msg.getE10CTACPA());
        addDataNode(data, "CertificateOfDeposit.Basic.DEAROC", getRolloverInstructions(msg));
        addDataNode(data, "CertificateOfDeposit.Basic.DEARAC", msg.getE10DEARAC());
        addDataNode(data, "CertificateOfDeposit.Basic.DEAOFA", msg.getE10DEAOFA());
        addDataNode(data, "CertificateOfDeposit.Basic.DEAROY", msg.getE10DEAROY());
        addDataNode(data, "CertificateOfDeposit.Basic.DEAODA", msg.getE10DEAODA());
        addDataNode(data, "CertificateOfDeposit.Basic.CTACPT", msg.getE10CTACPT());
        addDataNode(data, "CertificateOfDeposit.Basic.PENAMT", Util.formatCCY(msg.getE10PENAMT()));
        addDataNode(data, "CertificateOfDeposit.Basic.PENDYS", msg.getE10PENDYS());
        addDataNode(data, "CertificateOfDeposit.Basic.INRDYL", msg.getE10INRDYL());
        addDataNode(data, "CertificateOfDeposit.Basic.INNORV", msg.getE10INNORV());
        addDataNode(data, "CertificateOfDeposit.Basic.RNPLF1", msg.getE10RNPLF1());
        addDataNode(data, "CertificateOfDeposit.Basic.RNPLF2", msg.getE10RNPLF2());
        addDataNode(data, "CertificateOfDeposit.Basic.RNPLF3", msg.getE10RNPLF3());
        addDataNode(data, "CertificateOfDeposit.Basic.RNPLF4", msg.getE10RNPLF4());
        addDataNode(data, "CertificateOfDeposit.Basic.RNPLF5", msg.getE10RNPLF5());
        addDataNode(data, "CertificateOfDeposit.Basic.RNPLF6", msg.getE10RNPLF6());
        addDataNode(data, "CertificateOfDeposit.Basic.ENDNUM", msg.getE10ENDNUM());
        addDataNode(data, "CertificateOfDeposit.Basic.OWNRF1", msg.getE10OWNRF1());
        addDataNode(data, "CertificateOfDeposit.Basic.OWNRF2", msg.getE10OWNRF2());
        addDataNode(data, "CertificateOfDeposit.Basic.OWNRF3", msg.getE10OWNRF3());
        addDataNode(data, "CertificateOfDeposit.Basic.OWNRF4", msg.getE10OWNRF4());
        addDataNode(data, "CertificateOfDeposit.Basic.OWNRF5", msg.getE10OWNRF5());
        addDataNode(data, "CertificateOfDeposit.Basic.OWNRDT", msg.getE10OWNRDT());
        addDataNode(data, "CertificateOfDeposit.Basic.OWNRTX", msg.getE10OWNRTX());
        addDataNode(data, "CertificateOfDeposit.Basic.OWNRF6", msg.getE10OWNRF6());
        addDataNode(data, "CertificateOfDeposit.Basic.OWNRF7", msg.getE10OWNRF7());
        addDataNode(data, "CertificateOfDeposit.Basic.OWNRF8", msg.getE10OWNRF8());
        addDataNode(data, "CertificateOfDeposit.Basic.RDEAF1", msg.getE10RDEAF1());
        addDataNode(data, "CertificateOfDeposit.Basic.RDEAF2", msg.getE10RDEAF2());
        addDataNode(data, "CertificateOfDeposit.Basic.RDEAF3", msg.getE10RDEAF3());
        addDataNode(data, "CertificateOfDeposit.Basic.RDEAF4", msg.getE10RDEAF4());
        addDataNode(data, "CertificateOfDeposit.Basic.RDEAF5", msg.getE10RDEAF5());
        addDataNode(data, "CertificateOfDeposit.Basic.TAXES1", msg.getE10TAXES1());
        addDataNode(data, "CertificateOfDeposit.Basic.TAXES2", msg.getE10TAXES2());
        addDataNode(data, "CertificateOfDeposit.Basic.TAXES3", msg.getE10TAXES3());
        addDataNode(data, "CertificateOfDeposit.Basic.LGRNR1", msg.getE10LGRNR1());
        addDataNode(data, "CertificateOfDeposit.Basic.LGRBD1", msg.getE10LGRBD1());
        addDataNode(data, "CertificateOfDeposit.Basic.LGRID1", msg.getE10LGRID1());
        addDataNode(data, "CertificateOfDeposit.Basic.LGRFL1", msg.getE10LGRFL1());
        addDataNode(data, "CertificateOfDeposit.Basic.CDTFLG", msg.getE10CDTFLG());
        addDataNode(data, "CertificateOfDeposit.Basic.PRODUC", msg.getE10PRODUC());
        addDataNode(data, "CertificateOfDeposit.Basic.BUSINE", msg.getE10BUSINE());
        addDataNode(data, "CertificateOfDeposit.Basic.INFLRT", msg.getE10INFLRT());
        addDataNode(data, "CertificateOfDeposit.Basic.INFIXE", msg.getE10INFIXE());
        addDataNode(data, "CertificateOfDeposit.Basic.REVTRM", msg.getE10REVTRM());
        addDataNode(data, "CertificateOfDeposit.Basic.REVTRC", msg.getE10REVTRC());
        addDataNode(data, "CertificateOfDeposit.Basic.FLTRTE", msg.getE10FLTRTE());
        addDataNode(data, "CertificateOfDeposit.Basic.FLTRTF", msg.getE10FLTRTF());
        addDataNode(data, "CertificateOfDeposit.Basic.FLTYES", msg.getE10FLTYES());
        addDataNode(data, "CertificateOfDeposit.Basic.OFFCDE", msg.getE10OFFCDE());
        addDataNode(data, "CertificateOfDeposit.Basic.OFFNME", msg.getE10OFFNME());
        addDataNode(data, "CertificateOfDeposit.Basic.CUSLGT", msg.getE10CUSLGT());
        addDataNode(data, "CertificateOfDeposit.Basic.CUSNA1", msg.getE10CUSNA1());
        addDataNode(data, "CertificateOfDeposit.Basic.CUSNA2", msg.getE10CUSNA2());
        addDataNode(data, "CertificateOfDeposit.Basic.CUSNA3", msg.getE10CUSNA3());
        addDataNode(data, "CertificateOfDeposit.Basic.CUSNA4", msg.getE10CUSNA4());
        addDataNode(data, "CertificateOfDeposit.Basic.CUSNA5", msg.getE10CUSNA5());
        addDataNode(data, "CertificateOfDeposit.Basic.CUSCTR", msg.getE10CUSCTR());
        addDataNode(data, "CertificateOfDeposit.Basic.CUSCTY", msg.getE10CUSCTY());
        addDataNode(data, "CertificateOfDeposit.Basic.CUSSTE", msg.getE10CUSSTE());
        addDataNode(data, "CertificateOfDeposit.Basic.CUSZPC", msg.getE10CUSZPC());
        addDataNode(data, "CertificateOfDeposit.Basic.CUSPOB", msg.getE10CUSPOB());
        addDataNode(data, "CertificateOfDeposit.Basic.CUSIDN", msg.getE10CUSIDN());
        addDataNode(data, "CertificateOfDeposit.Basic.CUSIDF", msg.getE10CUSIDF());
        addDataNode(data, "CertificateOfDeposit.Basic.TAXIDE", msg.getE10TAXIDE());
        addDataNode(data, "CertificateOfDeposit.Basic.CUSHPN", msg.getE10CUSHPN());
        addDataNode(data, "CertificateOfDeposit.Basic.CUSPHN", msg.getE10CUSPHN());
        addDataNode(data, "CertificateOfDeposit.Basic.CUSIAD", msg.getE10CUSIAD());
        addDataNode(data, "CertificateOfDeposit.Basic.CUSCP1", msg.getE10CUSCP1());
        addDataNode(data, "CertificateOfDeposit.Basic.CUSCP2", msg.getE10CUSCP2());
        addDataNode(data, "CertificateOfDeposit.Basic.CUSCP3", msg.getE10CUSCP3());
        addDataNode(data, "CertificateOfDeposit.Basic.CUSTIM", msg.getE10CUSTIM());
        addDataNode(data, "CertificateOfDeposit.Basic.CUSFNA", msg.getE10CUSFNA());
        addDataNode(data, "CertificateOfDeposit.Basic.CUSACA", msg.getE10CUSACA());
        addDataNode(data, "CertificateOfDeposit.Basic.CUSFN2", msg.getE10CUSFN2());
        addDataNode(data, "CertificateOfDeposit.Basic.CUSLN1", msg.getE10CUSLN1());
        addDataNode(data, "CertificateOfDeposit.Basic.CUSLN2", msg.getE10CUSLN2());
        addDataNode(data, "CertificateOfDeposit.Basic.CUSBDM", Util.formatDate(msg.getE10CUSBDM(), msg.getE10CUSBDD(), msg.getE10CUSBDY()));
        addDataNode(data, "CertificateOfDeposit.Basic.CUSSEX", msg.getE10CUSSEX());
        addDataNode(data, "CertificateOfDeposit.Basic.CUSMST", msg.getE10CUSMST());
        addDataNode(data, "CertificateOfDeposit.Basic.INSNA1", msg.getE10INSNA1());
        addDataNode(data, "CertificateOfDeposit.Basic.INSNA2", msg.getE10INSNA2());
        addDataNode(data, "CertificateOfDeposit.Basic.INSNA3", msg.getE10INSNA3());
        addDataNode(data, "CertificateOfDeposit.Basic.INSNA4", msg.getE10INSNA4());
        addDataNode(data, "CertificateOfDeposit.Basic.INSIDE", msg.getE10INSIDE());
        addDataNode(data, "CertificateOfDeposit.Basic.INSPHN", msg.getE10INSPHN());
        addDataNode(data, "CertificateOfDeposit.Basic.BRNNA1", msg.getE10BRNNA1());
        addDataNode(data, "CertificateOfDeposit.Basic.BRNNA2", msg.getE10BRNNA2());
        addDataNode(data, "CertificateOfDeposit.Basic.BRNNA3", msg.getE10BRNNA3());
        addDataNode(data, "CertificateOfDeposit.Basic.BRNNA4", msg.getE10BRNNA4());
        addDataNode(data, "CertificateOfDeposit.Basic.BRNPHN", msg.getE10BRNPHN());
        addDataNode(data, "CertificateOfDeposit.Basic.BRNIDE", msg.getE10BRNIDE());
        addDataNode(data, "CertificateOfDeposit.Basic.RUNDTE", Util.formatDate(msg.getE10RUNDTE()));
        addDataNode(data, "CertificateOfDeposit.Basic.LGNA01", msg.getE10LGNA01());
        addDataNode(data, "CertificateOfDeposit.Basic.LGNA02", msg.getE10LGNA02());
        addDataNode(data, "CertificateOfDeposit.Basic.LGNA03", msg.getE10LGNA03());
        addDataNode(data, "CertificateOfDeposit.Basic.LGNA04", msg.getE10LGNA04());
        addDataNode(data, "CertificateOfDeposit.Basic.LGNA05", msg.getE10LGNA05());
        addDataNode(data, "CertificateOfDeposit.Basic.LGNA06", msg.getE10LGNA06());
        addDataNode(data, "CertificateOfDeposit.Basic.LGNA07", msg.getE10LGNA07());
        addDataNode(data, "CertificateOfDeposit.Basic.LGNA08", msg.getE10LGNA08());
        addDataNode(data, "CertificateOfDeposit.Basic.LGNA09", msg.getE10LGNA09());
        addDataNode(data, "CertificateOfDeposit.Basic.LGNA10", msg.getE10LGNA10());
        addDataNode(data, "CertificateOfDeposit.Basic.MLANA1", msg.getE10MLANA1());
        addDataNode(data, "CertificateOfDeposit.Basic.MLANA2", msg.getE10MLANA2());
        addDataNode(data, "CertificateOfDeposit.Basic.MLANA3", msg.getE10MLANA3());
        addDataNode(data, "CertificateOfDeposit.Basic.MLANA4", msg.getE10MLANA4());
        addDataNode(data, "CertificateOfDeposit.Basic.MLANA5", msg.getE10MLANA5());
        addDataNode(data, "CertificateOfDeposit.Basic.MLACTR", msg.getE10MLACTR());
        addDataNode(data, "CertificateOfDeposit.Basic.MLACTY", msg.getE10MLACTY());
        addDataNode(data, "CertificateOfDeposit.Basic.MLASTE", msg.getE10MLASTE());
        addDataNode(data, "CertificateOfDeposit.Basic.MLAZPC", msg.getE10MLAZPC());
        addDataNode(data, "CertificateOfDeposit.Basic.MLAPOB", msg.getE10MLAPOB());
        addDataNode(data, "CertificateOfDeposit.Basic.SGNME1", msg.getE10SGNME1());        
        addDataNode(data, "CertificateOfDeposit.Basic.SGCUN1", msg.getE10SGCUN1()); 
        addDataNode(data, "CertificateOfDeposit.Basic.SGCUN2", msg.getE10SGCUN2()); 
        addDataNode(data, "CertificateOfDeposit.Basic.SGCUN3", msg.getE10SGCUN3()); 
        addDataNode(data, "CertificateOfDeposit.Basic.SGCUN4", msg.getE10SGCUN4()); 
        addDataNode(data, "CertificateOfDeposit.Basic.SGCUN5", msg.getE10SGCUN5());        
        addDataNode(data, "CertificateOfDeposit.Basic.SGAD11", msg.getE10SGAD11());
        addDataNode(data, "CertificateOfDeposit.Basic.SGAD21", msg.getE10SGAD21());
        addDataNode(data, "CertificateOfDeposit.Basic.SGAD31", msg.getE10SGAD31());
        addDataNode(data, "CertificateOfDeposit.Basic.SGAD41", msg.getE10SGAD41());
        addDataNode(data, "CertificateOfDeposit.Basic.SGAD51", msg.getE10SGAD51());
        addDataNode(data, "CertificateOfDeposit.Basic.SGIDE1", msg.getE10SGIDE1());
        addDataNode(data, "CertificateOfDeposit.Basic.SGBOD1", Util.formatDate(msg.getE10SGBOD1()));
        addDataNode(data, "CertificateOfDeposit.Basic.SGNME2", msg.getE10SGNME2());
        addDataNode(data, "CertificateOfDeposit.Basic.SGAD12", msg.getE10SGAD12());
        addDataNode(data, "CertificateOfDeposit.Basic.SGAD22", msg.getE10SGAD22());
        addDataNode(data, "CertificateOfDeposit.Basic.SGAD32", msg.getE10SGAD32());
        addDataNode(data, "CertificateOfDeposit.Basic.SGAD42", msg.getE10SGAD42());
        addDataNode(data, "CertificateOfDeposit.Basic.SGAD52", msg.getE10SGAD52());
        addDataNode(data, "CertificateOfDeposit.Basic.SGIDE2", msg.getE10SGIDE2());
        addDataNode(data, "CertificateOfDeposit.Basic.SGBOD2", Util.formatDate(msg.getE10SGBOD2()));
        addDataNode(data, "CertificateOfDeposit.Basic.SGNME3", msg.getE10SGNME3());
        addDataNode(data, "CertificateOfDeposit.Basic.SGAD13", msg.getE10SGAD13());
        addDataNode(data, "CertificateOfDeposit.Basic.SGAD23", msg.getE10SGAD23());
        addDataNode(data, "CertificateOfDeposit.Basic.SGAD33", msg.getE10SGAD33());
        addDataNode(data, "CertificateOfDeposit.Basic.SGAD43", msg.getE10SGAD43());
        addDataNode(data, "CertificateOfDeposit.Basic.SGAD53", msg.getE10SGAD53());
        addDataNode(data, "CertificateOfDeposit.Basic.SGIDE3", msg.getE10SGIDE3());
        addDataNode(data, "CertificateOfDeposit.Basic.SGBOD3", Util.formatDate(msg.getE10SGBOD3()));
        addDataNode(data, "CertificateOfDeposit.Basic.SGNME4", msg.getE10SGNME4());
        addDataNode(data, "CertificateOfDeposit.Basic.SGAD14", msg.getE10SGAD14());
        addDataNode(data, "CertificateOfDeposit.Basic.SGAD24", msg.getE10SGAD24());
        addDataNode(data, "CertificateOfDeposit.Basic.SGAD34", msg.getE10SGAD34());
        addDataNode(data, "CertificateOfDeposit.Basic.SGAD44", msg.getE10SGAD44());
        addDataNode(data, "CertificateOfDeposit.Basic.SGAD54", msg.getE10SGAD54());
        addDataNode(data, "CertificateOfDeposit.Basic.SGIDE4", msg.getE10SGIDE4());
        addDataNode(data, "CertificateOfDeposit.Basic.SGBOD4", Util.formatDate(msg.getE10SGBOD4()));
        addDataNode(data, "CertificateOfDeposit.Basic.SGNME5", msg.getE10SGNME5());
        addDataNode(data, "CertificateOfDeposit.Basic.SGAD15", msg.getE10SGAD15());
        addDataNode(data, "CertificateOfDeposit.Basic.SGAD25", msg.getE10SGAD25());
        addDataNode(data, "CertificateOfDeposit.Basic.SGAD35", msg.getE10SGAD35());
        addDataNode(data, "CertificateOfDeposit.Basic.SGAD45", msg.getE10SGAD45());
        addDataNode(data, "CertificateOfDeposit.Basic.SGAD55", msg.getE10SGAD55());
        addDataNode(data, "CertificateOfDeposit.Basic.SGIDE5", msg.getE10SGIDE5());
        addDataNode(data, "CertificateOfDeposit.Basic.SGBOD5", Util.formatDate(msg.getE10SGBOD5()));
        addDataNode(data, "CertificateOfDeposit.Basic.BNNME1", msg.getE10BNNME1());
        addDataNode(data, "CertificateOfDeposit.Basic.BNAD11", msg.getE10BNAD11());
        addDataNode(data, "CertificateOfDeposit.Basic.BNAD21", msg.getE10BNAD21());
        addDataNode(data, "CertificateOfDeposit.Basic.BNAD31", msg.getE10BNAD31());
        addDataNode(data, "CertificateOfDeposit.Basic.BNAD41", msg.getE10BNAD41());
        addDataNode(data, "CertificateOfDeposit.Basic.BNAD51", msg.getE10BNAD51());
        addDataNode(data, "CertificateOfDeposit.Basic.BNIDE1", msg.getE10BNIDE1());
        addDataNode(data, "CertificateOfDeposit.Basic.BNBOD1", Util.formatDate(msg.getE10BNBOD1()));
        addDataNode(data, "CertificateOfDeposit.Basic.BNNME2", msg.getE10BNNME2());
        addDataNode(data, "CertificateOfDeposit.Basic.BNAD12", msg.getE10BNAD12());
        addDataNode(data, "CertificateOfDeposit.Basic.BNAD22", msg.getE10BNAD22());
        addDataNode(data, "CertificateOfDeposit.Basic.BNAD32", msg.getE10BNAD32());
        addDataNode(data, "CertificateOfDeposit.Basic.BNAD42", msg.getE10BNAD42());
        addDataNode(data, "CertificateOfDeposit.Basic.BNAD52", msg.getE10BNAD52());
        addDataNode(data, "CertificateOfDeposit.Basic.BNIDE2", msg.getE10BNIDE2());
        addDataNode(data, "CertificateOfDeposit.Basic.BNBOD2", Util.formatDate(msg.getE10BNBOD2()));
        addDataNode(data, "CertificateOfDeposit.Basic.BNNME3", msg.getE10BNNME3());
        addDataNode(data, "CertificateOfDeposit.Basic.BNAD13", msg.getE10BNAD13());
        addDataNode(data, "CertificateOfDeposit.Basic.BNAD23", msg.getE10BNAD23());
        addDataNode(data, "CertificateOfDeposit.Basic.BNAD33", msg.getE10BNAD33());
        addDataNode(data, "CertificateOfDeposit.Basic.BNAD43", msg.getE10BNAD43());
        addDataNode(data, "CertificateOfDeposit.Basic.BNAD53", msg.getE10BNAD53());
        addDataNode(data, "CertificateOfDeposit.Basic.BNIDE3", msg.getE10BNIDE3());
        addDataNode(data, "CertificateOfDeposit.Basic.BNBOD3", Util.formatDate(msg.getE10BNBOD3()));
        addDataNode(data, "CertificateOfDeposit.Basic.BNNME4", msg.getE10BNNME4());
        addDataNode(data, "CertificateOfDeposit.Basic.BNAD14", msg.getE10BNAD14());
        addDataNode(data, "CertificateOfDeposit.Basic.BNAD24", msg.getE10BNAD24());
        addDataNode(data, "CertificateOfDeposit.Basic.BNAD34", msg.getE10BNAD34());
        addDataNode(data, "CertificateOfDeposit.Basic.BNAD44", msg.getE10BNAD44());
        addDataNode(data, "CertificateOfDeposit.Basic.BNAD54", msg.getE10BNAD54());
        addDataNode(data, "CertificateOfDeposit.Basic.BNIDE4", msg.getE10BNIDE4());
        addDataNode(data, "CertificateOfDeposit.Basic.BNBOD4", Util.formatDate(msg.getE10BNBOD4()));
        addDataNode(data, "CertificateOfDeposit.Basic.BNNME5", msg.getE10BNNME5());
        addDataNode(data, "CertificateOfDeposit.Basic.BNAD15", msg.getE10BNAD15());
        addDataNode(data, "CertificateOfDeposit.Basic.BNAD25", msg.getE10BNAD25());
        addDataNode(data, "CertificateOfDeposit.Basic.BNAD35", msg.getE10BNAD35());
        addDataNode(data, "CertificateOfDeposit.Basic.BNAD45", msg.getE10BNAD45());
        addDataNode(data, "CertificateOfDeposit.Basic.BNAD55", msg.getE10BNAD55());
        addDataNode(data, "CertificateOfDeposit.Basic.BNIDE5", msg.getE10BNIDE5());
        addDataNode(data, "CertificateOfDeposit.Basic.BNBOD5", Util.formatDate(msg.getE10BNBOD4()));
        addDataNode(data, "CertificateOfDeposit.Basic.DLYINT", msg.getE10DLYINT());
        addDataNode(data, "CertificateOfDeposit.Basic.YESINT", msg.getE10YESINT());
        addDataNode(data, "CertificateOfDeposit.Basic.DEAPRI", Util.formatCCY(msg.getE10DEAPRI()));
        addDataNode(data, "CertificateOfDeposit.Basic.PRODUC", msg.getE10PRODUC());
        addDataNode(data, "CertificateOfDeposit.Basic.BUSINE", msg.getE10BUSINE());
    }

    protected void getCUSTDataForDataproForms(Object data, EFRM00098Message msg)
    {
        String dataItem = null;
        addDataNode(data, "Customer.Number", msg.getE98DEACUN());
        addDataNode(data, "Customer.Identification.Number", msg.getE98CUSIDN());
        addDataNode(data, "Customer.Identification.Type", msg.getE98CUSTID());
        addDataNode(data, "Customer.Identification.PanamenianID", msg.getE98CUSLN3());
        addDataNode(data, "Customer.FirstName", msg.getE98CUSFNA());
        addDataNode(data, "Customer.SecondName", msg.getE98CUSFN2());
        addDataNode(data, "Customer.LastNameOne", msg.getE98CUSLN1());
        addDataNode(data, "Customer.LastNameTwo", msg.getE98CUSLN2());
        addDataNode(data, "Customer.PreviousName", msg.getE98CUSCP1());
        addDataNode(data, "Customer.Identification.Type.Description", msg.getD98CUSTID());
        addDataNode(data, "Customer.Identification.Country", msg.getE98CUSPID());
        addDataNode(data, "Customer.Identification.Country.Description", msg.getD98CUSPID());
        addDataNode(data, "Customer.Identification.Second.Number", msg.getE98CUSIDF());
        addDataNode(data, "Customer.Identification.Second.Type", msg.getE98CUSTIF());
        addDataNode(data, "Customer.Identification.Second.Type.Description", msg.getD98CUSTIF());
        addDataNode(data, "Customer.Identification.Second.Country", msg.getE98CUSPIF());
        addDataNode(data, "Customer.Identification.Second.Country.Description", msg.getD98CUSPIF());
        addDataNode(data, "Customer.Identification.Third.Number", msg.getE98CUSID3());
        addDataNode(data, "Customer.Identification.Third.Type", msg.getE98CUSTI3());
        addDataNode(data, "Customer.Identification.Third.Type.Description", msg.getD98CUSTI3());
        addDataNode(data, "Customer.Identification.Third.Country", msg.getE98CUSPI3());
        addDataNode(data, "Customer.Identification.Third.Country.Description", msg.getD98CUSPI3());
        addDataNode(data, "Customer.Identification.Fourth.Number", msg.getE98CUSID4());
        addDataNode(data, "Customer.Identification.Fourth.Type", msg.getE98CUSTI4());
        addDataNode(data, "Customer.Identification.Fourth.Type.Description", msg.getD98CUSTI4());
        addDataNode(data, "Customer.Identification.Fourth.Country", msg.getE98CUSPI4());
        addDataNode(data, "Customer.Identification.Fourth.Country.Description", msg.getD98CUSPI4());
        addDataNode(data, "Customer.LegalType", msg.getE98CUSLGT());
        int relacion = 0;
        try
        {
            relacion = Integer.parseInt(msg.getE98CUSSTF());
        }
        catch(Exception exception) { }
        switch(relacion)
        {
        case 1: // '\001'
            addDataNode(data, "Customer.StaffMember", "CLIENTE REGULAR");
            break;

        case 2: // '\002'
            addDataNode(data, "Customer.StaffMember", "ESPECIAL");
            break;

        case 3: // '\003'
            addDataNode(data, "Customer.StaffMember", "EMPLEADO");
            break;

        case 4: // '\004'
            addDataNode(data, "Customer.StaffMember", "FUNCIONARIO");
            break;

        case 5: // '\005'
            addDataNode(data, "Customer.StaffMember", "DIRECTOR");
            break;

        case 6: // '\006'
            addDataNode(data, "Customer.StaffMember", "PARIENTE");
            break;

        case 7: // '\007'
            addDataNode(data, "Customer.StaffMember", "EMPRESA AFILIADA");
            break;

        case 8: // '\b'
            addDataNode(data, "Customer.StaffMember", "JUNTA DIRECTIVA");
            break;

        case 9: // '\t'
            addDataNode(data, "Customer.StaffMember", "BANCO CORRESPONSAL");
            break;

        default:
            addDataNode(data, "Customer.StaffMember", "CLIENTE REGULAR");
            break;
        }
        addDataNode(data, "Customer.ShortName", msg.getE98CUSSHN());
        addDataNode(data, "Customer.Status", msg.getE98CUSSTS());
        addDataNode(data, "Customer.Name.LineOne", msg.getE98CUSNA1());
        addDataNode(data, "Customer.Name.Legal", msg.getE98CUSNA1());
        addDataNode(data, "Customer.Name.LineTwo", msg.getE98CUSNA2());
        addDataNode(data, "Customer.Name.LineThree", msg.getE98CUSNA3());
        addDataNode(data, "Customer.Name.LineFour", msg.getE98CUSNA4());
        addDataNode(data, "Customer.Address", msg.getE98CUSNA2() + " " + msg.getE98CUSNA3() + " " + msg.getE98CUSNA4());
        addDataNode(data, "Customer.RegionDsc", msg.getD98CUSUC8());
        addDataNode(data, "Customer.MunicipioDsc", msg.getD98CUSMUN());
        addDataNode(data, "Customer.City", msg.getE98CUSCTY());
        addDataNode(data, "Customer.State", msg.getE98CUSSTE());
        addDataNode(data, "Customer.State.Description", msg.getD98CUSSTE());
        addDataNode(data, "Customer.Home", msg.getE98CUSBLD());
        addDataNode(data, "Customer.Floor", msg.getE98CUSFLR());
        addDataNode(data, "Customer.Apt", msg.getE98CUSAPT());
        
        addDataNode(data, "Customer.ZipCode", msg.getE98CUSZPC());
        addDataNode(data, "Customer.POBox", msg.getE98CUSPOB());
        addDataNode(data, "Customer.Country", msg.getE98CUSCTR());
        addDataNode(data, "Customer.Citizenship", msg.getE98CUSCCS());
        addDataNode(data, "Customer.Citizenship.Description", msg.getD98CUSCCS());
        addDataNode(data, "Customer.Gender", msg.getE98CUSSEX());
        if(msg.getE98CUSMST().equals("1"))
            dataItem = "Soltero(a)";
        if(msg.getE98CUSMST().equals("2"))
            dataItem = "Casado(a)";
        if(msg.getE98CUSMST().equals("3"))
            dataItem = "Divorciado(a)";
        if(msg.getE98CUSMST().equals("4"))
            dataItem = "Viudo(a)";
        if(msg.getE98CUSMST().equals("5"))
            dataItem = "Concubino(a)";
        addDataNode(data, "Customer.MaritalStatusDsc", dataItem);
        addDataNode(data, "Customer.BirthDate", Util.formatDate(msg.getE98CUSBDM(), msg.getE98CUSBDD(), msg.getE98CUSBDY()));
        addDataNode(data, "Customer.BirthPlace", msg.getD98CUSCOB());
        addDataNode(data, "Customer.CountryOfBirth", msg.getE98CUSCOB());
        addDataNode(data, "Customer.NumberOfDependents", msg.getE98CUSNSO());
        addDataNode(data, "Customer.InquiryLevel", msg.getE98CUSILV());
        addDataNode(data, "Customer.RollOverVolumen", msg.getE98CUSRLV());
        addDataNode(data, "Customer.Language", msg.getE98CUSLIF());
        addDataNode(data, "Customer.ATM.Issued", msg.getE98CUSATM());
        addDataNode(data, "Customer.Resident", msg.getE98CUSFL1());
        addDataNode(data, "Customer.Classification", msg.getE98CUSCCL());
        addDataNode(data, "Customer.ChargeTax", msg.getE98CUSTAX());
        addDataNode(data, "Customer.InitialDate", Util.formatDate(msg.getE98CUSIDM(), msg.getE98CUSIDD(), msg.getE98CUSIDY()));
        addDataNode(data, "Customer.LastActivityDate", Util.formatDate(msg.getE98CUSLDM(), msg.getE98CUSLDD(), msg.getE98CUSLDY()));
        addDataNode(data, "Customer.MaturityDate", Util.formatDate(msg.getE98CUSMAM(), msg.getE98CUSMAD(), msg.getE98CUSMAY()));
        addDataNode(data, "Customer.ReferredBy.Code", msg.getE98CUSRBY());
        addDataNode(data, "Customer.ReferredBy.Code.Description", msg.getD98CUSRBY());
        addDataNode(data, "Customer.ReferredBy.Name", msg.getE98CUSRBN());
        addDataNode(data, "Customer.EducationLevel", msg.getE98CUSEDL());
        addDataNode(data, "Customer.EducationLevel.Description", msg.getD98CUSEDL());
        addDataNode(data, "Customer.Industry.Code", msg.getE98CUSINC());
        addDataNode(data, "Customer.Industry.Code.Description", msg.getD98CUSINC());
        addDataNode(data, "Customer.Business.Code", msg.getE98CUSBUC());
        addDataNode(data, "Customer.Business.Code.Description", msg.getD98CUSBUC());
        addDataNode(data, "Customer.IncomeLevel", msg.getE98CUSINL());
        addDataNode(data, "Customer.SourceOfIncome", msg.getE98CUSSOI());
        addDataNode(data, "Customer.SourceOfIncome.Description", msg.getD98CUSSOI());
        addDataNode(data, "Customer.RiskLevel", msg.getE98CUSRSL());
        addDataNode(data, "Customer.RiskLevel.Description", msg.getD98CUSRSL());
        addDataNode(data, "Customer.Geografic.Code", msg.getE98CUSGEC());
        addDataNode(data, "Customer.Geografic.Code.Description", msg.getD98CUSGEC());
        addDataNode(data, "Customer.PrimaryOfficer.Code", msg.getE98CUSOFC());
        addDataNode(data, "Customer.PrimaryOfficer.Code.Description", msg.getD98CUSOFC());
        addDataNode(data, "Customer.SecondaryOfficer.Code", msg.getE98CUSOF2());
        addDataNode(data, "Customer.SecondaryOfficer.Code.Description", msg.getD98CUSOF2());
        addDataNode(data, "Customer.UserOne.Code", msg.getE98CUSUC1());
        addDataNode(data, "Customer.UserOne.Code.Description", msg.getD98CUSUC1());
        addDataNode(data, "Customer.UserTwo.Code", msg.getE98CUSUC2());
        addDataNode(data, "Customer.UserTwo.Code.Description", msg.getD98CUSUC2());
        addDataNode(data, "Customer.UserThree.Code", msg.getE98CUSUC3());
        addDataNode(data, "Customer.UserThree.Code.Description", msg.getD98CUSUC3());
        addDataNode(data, "Customer.UserFour.Code", msg.getE98CUSUC4());
        addDataNode(data, "Customer.UserFour.Code.Description", msg.getD98CUSUC4());
        addDataNode(data, "Customer.UserFive.Code", msg.getE98CUSUC5());
        addDataNode(data, "Customer.UserFive.Code.Description", msg.getD98CUSUC5());
        addDataNode(data, "Customer.UserSix.Code", msg.getE98CUSUC6());
        addDataNode(data, "Customer.UserSix.Code.Description", msg.getD98CUSUC6());
        addDataNode(data, "Customer.UserSeven.Code", msg.getE98CUSUC7());
        addDataNode(data, "Customer.UserSeven.Code.Description", msg.getD98CUSUC7());
        addDataNode(data, "Customer.UserEight.Code", msg.getE98CUSUC8());
        addDataNode(data, "Customer.UserEight.Code.Description", msg.getD98CUSUC8());
        addDataNode(data, "Customer.UserNine.Code", msg.getE98CUSUC9());
        addDataNode(data, "Customer.UserNine.Code.Description", msg.getD98CUSUC9());
        addDataNode(data, "Customer.Type", msg.getE98CUSTYP());
        addDataNode(data, "Customer.Group.Code", msg.getE98CUSGRP());
        addDataNode(data, "Customer.Mailing.Code", msg.getE98CUSMLC());
        addDataNode(data, "Customer.Mailing.Code.Description", msg.getD98CUSMLC());
        addDataNode(data, "Customer.HomePhone", msg.getE98CUSHPN());
        addDataNode(data, "Customer.BussinesPhone", msg.getE98CUSPHN());
        addDataNode(data, "Customer.OtherPhone", msg.getE98CUSPH1());
        addDataNode(data, "Customer.Fax", msg.getE98CUSFAX());
        addDataNode(data, "Customer.NumberOfShares", msg.getE98CUSNST());
        addDataNode(data, "Customer.NumberOfStockHolders", msg.getE98CUSNST());
        addDataNode(data, "Customer.RelationWork", msg.getE98CUSRWT());
        addDataNode(data, "Customer.Job.CompanyName", msg.getE98CUSCP1());
        addDataNode(data, "Customer.Job.CompanyAddress", msg.getE98CUSCP2());
        addDataNode(data, "Customer.Job.BusinessType", msg.getE98CUSUC5());
        addDataNode(data, "Customer.Job.TimeWorking", msg.getE98CUSTIM());
        addDataNode(data, "Customer.Job.Position", msg.getD98CUFUC3());
        addDataNode(data, "Customer.Job.StartDate", Util.formatDate(msg.getE98CUSSWM(), msg.getE98CUSSWD(), msg.getE98CUSSWY()));
        if(msg.getE98CUSNEM().equals("1"))
            dataItem = "Fijo";
        if(msg.getE98CUSNEM().equals("2"))
            dataItem = "Indefinido";
        addDataNode(data, "Customer.Job.ContractType", dataItem);
        if(msg.getE98CUSSAT().equals("W"))
            dataItem = "Semanal";
        if(msg.getE98CUSSAT().equals("B"))
            dataItem = "Quincenal";
        if(msg.getE98CUSSAT().equals("M"))
            dataItem = "Mensual";
        if(msg.getE98CUSSAT().equals("S"))
            dataItem = "Semestral";
        if(msg.getE98CUSSAT().equals("Y"))
            dataItem = "Anual";
        addDataNode(data, "Customer.Job.SalaryType", dataItem);
        addDataNode(data, "Customer.Job.Salary", msg.getE98CUSAMT());
        addDataNode(data, "Customer.PaidCapital", msg.getE98CUSCAP());
        addDataNode(data, "Customer.InitialCapital", msg.getE98CUSCAI());
		addDataNode(data, "Customer.GrossIncome", msg.getE98CUSCAI());
        addDataNode(data, "Customer.OtherIncome", msg.getE98CUSCAS());
		addDataNode(data, "Customer.SubcriptCapital", msg.getE98CUSCAS());
        addDataNode(data, "Customer.OtherAmount", msg.getE98CUSAMT());
        addDataNode(data, "Customer.NumberOfEmployees", msg.getE98CUSNEM());
        addDataNode(data, "Customer.SourceOfIncome", msg.getE98CUSORI());
        addDataNode(data, "Customer.eMail", msg.getE98CUSIAD());
        addDataNode(data, "Customer.MarriedName", msg.getE98CUSACA());
        addDataNode(data, "Customer.Subjective.Calification", msg.getE98CUSSUC());
        addDataNode(data, "Customer.Objective.Calification", msg.getE98CUSOBC());
        addDataNode(data, "Customer.Prevision.Table", msg.getE98CUSPRV());
        addDataNode(data, "Customer.Spread.Rate", msg.getE98CUSSPR());
        addDataNode(data, "Customer.Spread.Flag", msg.getE98CUSSPF());
        addDataNode(data, "Customer.Spread.StartDate", Util.formatDate(msg.getE98CUSSTM(), msg.getE98CUSSTD(), msg.getE98CUSSTY()));
        addDataNode(data, "Customer.Spread.RevDate", Util.formatDate(msg.getE98CUSSPM(), msg.getE98CUSSPD(), msg.getE98CUSSPY()));
        addDataNode(data, "Customer.StartActDate", Util.formatDate(msg.getE98CUSIEM(), msg.getE98CUSIED(), msg.getE98CUSIEY()));
        addDataNode(data, "Customer.RegistrationDate", Util.formatDate(msg.getE98CUSREM(), msg.getE98CUSRED(), msg.getE98CUSREY()));
        addDataNode(data, "Customer.RegistrationNumber", msg.getE98CUSREN());
        addDataNode(data, "Customer.ForTax", msg.getE98CUSTTX());
        addDataNode(data, "Customer.ExceptTaxPercent", msg.getE98CUSETX());
        addDataNode(data, "Customer.DueTaxPercentDate", Util.formatDate(msg.getE98CUSETM(), msg.getE98CUSETD(), msg.getE98CUSETY()));
        addDataNode(data, "Customer.DailyInputBatch", msg.getE98CUSDIB());
        addDataNode(data, "Customer.LastMaintenanceUser", msg.getE98CUSUSR());
    }

    protected void getLNDataForBankersForms(Object obj, EFRM00080Message efrm00080message)
    {
    }

    protected void getAccHoldersForDataproForms(Object data, ESD000006Message msg)
    {
        addDataNode(data, "Account.AccountHolders.E06MA1", msg.getE06MA1());
        addDataNode(data, "Account.AccountHolders.E06MA2", msg.getE06MA2());
        addDataNode(data, "Account.AccountHolders.E06MA3", msg.getE06MA3());
        addDataNode(data, "Account.AccountHolders.E06MA4", msg.getE06MA4());
        addDataNode(data, "Account.AccountHolders.E06MA5", msg.getE06MA5());
        addDataNode(data, "Account.AccountHolders.E06MA6", msg.getE06MA6());
        addDataNode(data, "Account.AccountHolders.E06MA7", msg.getE06MA7());
        addDataNode(data, "Account.AccountHolders.E06MA8", msg.getE06MA8());
        addDataNode(data, "Account.AccountHolders.E06MA9", msg.getE06MA9());
        addDataNode(data, "Account.AccountHolders.E06MA0", msg.getE06MA0());
        addDataNode(data, "Account.AccountHolders.E06CU1", msg.getE06CU1());
        addDataNode(data, "Account.AccountHolders.E06CU2", msg.getE06CU2());
        addDataNode(data, "Account.AccountHolders.E06CU3", msg.getE06CU3());
        addDataNode(data, "Account.AccountHolders.E06CU4", msg.getE06CU4());
        addDataNode(data, "Account.AccountHolders.E06CU5", msg.getE06CU5());
        addDataNode(data, "Account.AccountHolders.E06CU6", msg.getE06CU6());
        addDataNode(data, "Account.AccountHolders.E06CU7", msg.getE06CU7());
        addDataNode(data, "Account.AccountHolders.E06CU8", msg.getE06CU8());
        addDataNode(data, "Account.AccountHolders.E06CU9", msg.getE06CU9());
        addDataNode(data, "Account.AccountHolders.E06CU0", msg.getE06CU0());
        addDataNode(data, "Account.AccountHolders.E06ID1", msg.getE06ID1());
        addDataNode(data, "Account.AccountHolders.E06ID2", msg.getE06ID2());
        addDataNode(data, "Account.AccountHolders.E06ID3", msg.getE06ID3());
        addDataNode(data, "Account.AccountHolders.E06ID4", msg.getE06ID4());
        addDataNode(data, "Account.AccountHolders.E06ID5", msg.getE06ID5());
        addDataNode(data, "Account.AccountHolders.E06ID6", msg.getE06ID6());
        addDataNode(data, "Account.AccountHolders.E06ID7", msg.getE06ID7());
        addDataNode(data, "Account.AccountHolders.E06ID8", msg.getE06ID8());
        addDataNode(data, "Account.AccountHolders.E06ID9", msg.getE06ID9());
        addDataNode(data, "Account.AccountHolders.E06ID0", msg.getE06ID0());
        addDataNode(data, "Account.AccountHolders.E06TY1", msg.getE06TY1());
        addDataNode(data, "Account.AccountHolders.E06TY2", msg.getE06TY2());
        addDataNode(data, "Account.AccountHolders.E06TY3", msg.getE06TY3());
        addDataNode(data, "Account.AccountHolders.E06TY4", msg.getE06TY4());
        addDataNode(data, "Account.AccountHolders.E06TY5", msg.getE06TY5());
        addDataNode(data, "Account.AccountHolders.E06TY6", msg.getE06TY6());
        addDataNode(data, "Account.AccountHolders.E06TY7", msg.getE06TY7());
        addDataNode(data, "Account.AccountHolders.E06TY8", msg.getE06TY8());
        addDataNode(data, "Account.AccountHolders.E06TY9", msg.getE06TY9());
        addDataNode(data, "Account.AccountHolders.E06TY0", msg.getE06TY0());
    }

    protected void getAccSpecialCodesForDataproForms(Object data, ESD000002Message msg)
    {
        addDataNode(data, "Account.SpecialCodes.E02ACC", msg.getE02ACC());
        addDataNode(data, "Account.SpecialCodes.E02LNE", msg.getE02LNE());
        addDataNode(data, "Account.SpecialCodes.E02ACD", msg.getE02ACD());
        addDataNode(data, "Account.SpecialCodes.E02OFC", msg.getE02OFC());
        addDataNode(data, "Account.SpecialCodes.E02OF2", msg.getE02OF2());
        addDataNode(data, "Account.SpecialCodes.E02INC", msg.getE02INC());
        addDataNode(data, "Account.SpecialCodes.E02BUC", msg.getE02BUC());
        addDataNode(data, "Account.SpecialCodes.E02GEC", msg.getE02GEC());
        addDataNode(data, "Account.SpecialCodes.E02GRC", msg.getE02GRC());
        addDataNode(data, "Account.SpecialCodes.E02UC1", msg.getE02UC1());
        addDataNode(data, "Account.SpecialCodes.E02UC2", msg.getE02UC2());
        addDataNode(data, "Account.SpecialCodes.E02UC3", msg.getE02UC3());
        addDataNode(data, "Account.SpecialCodes.E02UC4", msg.getE02UC4());
        addDataNode(data, "Account.SpecialCodes.E02UC5", msg.getE02UC5());
        addDataNode(data, "Account.SpecialCodes.E02UC6", msg.getE02UC6());
        addDataNode(data, "Account.SpecialCodes.E02UC7", msg.getE02UC7());
        addDataNode(data, "Account.SpecialCodes.E02UC8", msg.getE02UC8());
        addDataNode(data, "Account.SpecialCodes.E02UC9", msg.getE02UC9());
        addDataNode(data, "Account.SpecialCodes.E02ORG", msg.getE02ORG());
        addDataNode(data, "Account.SpecialCodes.E02DST", msg.getE02DST());
        addDataNode(data, "Account.SpecialCodes.E02SCH", msg.getE02SCH());
        addDataNode(data, "Account.SpecialCodes.E02SST", msg.getE02SST());
        addDataNode(data, "Account.SpecialCodes.D02OFC", msg.getD02OFC());
        addDataNode(data, "Account.SpecialCodes.D02OF2", msg.getD02OF2());
        addDataNode(data, "Account.SpecialCodes.D02INC", msg.getD02INC());
        addDataNode(data, "Account.SpecialCodes.D02BUC", msg.getD02BUC());
        addDataNode(data, "Account.SpecialCodes.D02GEC", msg.getD02GEC());
        addDataNode(data, "Account.SpecialCodes.D02GRC", msg.getD02GRC());
        addDataNode(data, "Account.SpecialCodes.D02UC1", msg.getD02UC1());
        addDataNode(data, "Account.SpecialCodes.D02UC2", msg.getD02UC2());
        addDataNode(data, "Account.SpecialCodes.D02UC3", msg.getD02UC3());
        addDataNode(data, "Account.SpecialCodes.D02UC4", msg.getD02UC4());
        addDataNode(data, "Account.SpecialCodes.D02UC5", msg.getD02UC5());
        addDataNode(data, "Account.SpecialCodes.D02UC6", msg.getD02UC6());
        addDataNode(data, "Account.SpecialCodes.D02UC7", msg.getD02UC7());
        addDataNode(data, "Account.SpecialCodes.D02UC8", msg.getD02UC8());
        addDataNode(data, "Account.SpecialCodes.D02UC9", msg.getD02UC9());
        addDataNode(data, "Account.SpecialCodes.D02ORG", msg.getD02ORG());
        addDataNode(data, "Account.SpecialCodes.D02DST", msg.getD02DST());
        addDataNode(data, "Account.SpecialCodes.D02SCH", msg.getD02SCH());
        addDataNode(data, "Account.SpecialCodes.D02SST", msg.getD02SST());
        addDataNode(data, "Account.SpecialCodes.E02CF1", msg.getE02CF1());
        addDataNode(data, "Account.SpecialCodes.E02CF2", msg.getE02CF2());
        addDataNode(data, "Account.SpecialCodes.E02CF3", msg.getE02CF3());
        addDataNode(data, "Account.SpecialCodes.E02CF4", msg.getE02CF4());
        addDataNode(data, "Account.SpecialCodes.E02CF5", msg.getE02CF5());
        addDataNode(data, "Account.SpecialCodes.E02CF6", msg.getE02CF6());
        addDataNode(data, "Account.SpecialCodes.E02CF7", msg.getE02CF7());
        addDataNode(data, "Account.SpecialCodes.E02CF8", msg.getE02CF8());
        addDataNode(data, "Account.SpecialCodes.E02CF9", msg.getE02CF9());
        addDataNode(data, "Account.SpecialCodes.E02RC1", msg.getE02RC1());
        addDataNode(data, "Account.SpecialCodes.E02RC2", msg.getE02RC2());
        addDataNode(data, "Account.SpecialCodes.E02RC3", msg.getE02RC3());
        addDataNode(data, "Account.SpecialCodes.E02RC4", msg.getE02RC4());
        addDataNode(data, "Account.SpecialCodes.E02RC5", msg.getE02RC5());
        addDataNode(data, "Account.SpecialCodes.E02RC6", msg.getE02RC6());
        addDataNode(data, "Account.SpecialCodes.E02RC7", msg.getE02RC7());
        addDataNode(data, "Account.SpecialCodes.E02RC8", msg.getE02RC8());
        addDataNode(data, "Account.SpecialCodes.E02RC9", msg.getE02RC9());
        addDataNode(data, "Account.SpecialCodes.D02DS1", msg.getD02DS1());
        addDataNode(data, "Account.SpecialCodes.D02DS2", msg.getD02DS2());
        addDataNode(data, "Account.SpecialCodes.D02DS3", msg.getD02DS3());
        addDataNode(data, "Account.SpecialCodes.D02DS4", msg.getD02DS4());
        addDataNode(data, "Account.SpecialCodes.D02DS5", msg.getD02DS5());
        addDataNode(data, "Account.SpecialCodes.D02DS6", msg.getD02DS6());
        addDataNode(data, "Account.SpecialCodes.D02DS7", msg.getD02DS7());
        addDataNode(data, "Account.SpecialCodes.D02DS8", msg.getD02DS8());
        addDataNode(data, "Account.SpecialCodes.D02DS9", msg.getD02DS9());
        addDataNode(data, "Account.SpecialCodes.D02LA1", msg.getD02LA1());
        addDataNode(data, "Account.SpecialCodes.D02LA2", msg.getD02LA2());
        addDataNode(data, "Account.SpecialCodes.D02LA3", msg.getD02LA3());
        addDataNode(data, "Account.SpecialCodes.D02LA4", msg.getD02LA4());
        addDataNode(data, "Account.SpecialCodes.D02LA5", msg.getD02LA5());
        addDataNode(data, "Account.SpecialCodes.D02LA6", msg.getD02LA6());
        addDataNode(data, "Account.SpecialCodes.D02LA7", msg.getD02LA7());
        addDataNode(data, "Account.SpecialCodes.D02LA8", msg.getD02LA8());
        addDataNode(data, "Account.SpecialCodes.D02LA9", msg.getD02LA9());
        addDataNode(data, "Account.SpecialCodes.D02RQ1", msg.getD02RQ1());
        addDataNode(data, "Account.SpecialCodes.D02RQ2", msg.getD02RQ2());
        addDataNode(data, "Account.SpecialCodes.D02RQ3", msg.getD02RQ3());
        addDataNode(data, "Account.SpecialCodes.D02RQ4", msg.getD02RQ4());
        addDataNode(data, "Account.SpecialCodes.D02RQ5", msg.getD02RQ5());
        addDataNode(data, "Account.SpecialCodes.D02RQ6", msg.getD02RQ6());
        addDataNode(data, "Account.SpecialCodes.D02RQ7", msg.getD02RQ7());
        addDataNode(data, "Account.SpecialCodes.D02RQ8", msg.getD02RQ8());
        addDataNode(data, "Account.SpecialCodes.D02RQ9", msg.getD02RQ9());
    }

    protected void getAccSpecialInstForDataproForms(Object data, ESD000005Message msg)
    {
        addDataNode(data, "Account.SpecialInstructions.Line.01", msg.getE15DSC());
        addDataNode(data, "Account.SpecialInstructions.Line.02", msg.getE25DSC());
        addDataNode(data, "Account.SpecialInstructions.Line.03", msg.getE35DSC());
        addDataNode(data, "Account.SpecialInstructions.Line.04", msg.getE45DSC());
        addDataNode(data, "Account.SpecialInstructions.Line.05", msg.getE55DSC());
        addDataNode(data, "Account.SpecialInstructions.Line.06", msg.getE65DSC());
        addDataNode(data, "Account.SpecialInstructions.Line.07", msg.getE75DSC());
        addDataNode(data, "Account.SpecialInstructions.Line.08", msg.getE85DSC());
        addDataNode(data, "Account.SpecialInstructions.Line.09", msg.getE95DSC());
        addDataNode(data, "Account.SpecialInstructions.Line.10", msg.getE05DSC());
        addDataNode(data, "Account.SpecialInstructions.Line.11", msg.getEA5DSC());
        addDataNode(data, "Account.SpecialInstructions.Line.12", msg.getEB5DSC());
        addDataNode(data, "Account.SpecialInstructions.Line.13", msg.getEC5DSC());
        addDataNode(data, "Account.SpecialInstructions.Line.14", msg.getED5DSC());
        addDataNode(data, "Account.SpecialInstructions.Line.15", msg.getEE5DSC());
        addDataNode(data, "Account.SpecialInstructions.Line.16", msg.getEF5DSC());
        addDataNode(data, "Account.SpecialInstructions.Line.17", msg.getEG5DSC());
        addDataNode(data, "Account.SpecialInstructions.Line.18", msg.getEH5DSC());
        addDataNode(data, "Account.SpecialInstructions.Line.19", msg.getEI5DSC());
        addDataNode(data, "Account.SpecialInstructions.Line.20", msg.getEJ5DSC());
    }

    protected void getAccCollateralsForDataproForms(MessageContext mc, Object data, ERA000001Message msg)
        throws ServletException, IOException
    {
        MessageRecord newmessage = null;
        String nl = eIBSFormsNL;
        String marker = "";
        boolean firstTime = true;
        String account = "";
        String type = "";
        String amount = "";
        String thisAccCollateral = "";
        String otherCollaterals = "";
        String remainingBalance = "";
        try
        {
            do
            {
                marker = msg.getE01ENDFLD();
                if(marker.equals("*"))
                    break;
                if(firstTime)
                {
                    firstTime = false;
                } else
                {
                    account = account + nl;
                    type = type + nl;
                    amount = amount + nl;
                    thisAccCollateral = thisAccCollateral + nl;
                    otherCollaterals = otherCollaterals + nl;
                    remainingBalance = remainingBalance + nl;
                }
                account = account + msg.getE01RCLACB();
                type = type + msg.getE01RCLBTYP();
                amount = amount + Util.formatCCY(msg.getE01RCLBBAL());
                thisAccCollateral = thisAccCollateral + Util.formatCCY(msg.getE01RCLBCOL());
                otherCollaterals = otherCollaterals + Util.formatCCY(msg.getE01RCLOCOL());
                remainingBalance = remainingBalance + Util.formatCCY(msg.getE01RCLBDIS());
                if(marker.equals("+"))
                    break;
                newmessage = mc.receiveMessage();
                msg = (ERA000001Message)newmessage;
            } while(true);
            addDataNode(data, "Loan.Collaterals.Column.Account", account);
            addDataNode(data, "Loan.Collaterals.Column.Type", type);
            addDataNode(data, "Loan.Collaterals.Column.Amount", amount);
            addDataNode(data, "Loan.Collaterals.Column.ThisAccountCollateralAmount", thisAccCollateral);
            addDataNode(data, "Loan.Collaterals.Column.OtherCollateralsAmount", otherCollaterals);
            addDataNode(data, "Loan.Collaterals.Column.RemainingBalanceAmount", remainingBalance);
        }
        catch(Exception e)
        {
            e.printStackTrace();
            flexLog("Error: " + e);
            throw new RuntimeException("Socket Communication Error");
        }
    }

    protected void getAccPayScheduleForDataproForms(MessageContext mc, Object data, EDL090002Message msg)
        throws ServletException, IOException
    {
        MessageRecord newmessage = null;
        String nl = eIBSFormsNL;
        String marker = "";
        boolean firstTime = true;
        String number = "";
        String paymentDate = "";
        String principal = "";
        String interest = "";
        String otherCharges = "";
        String totalPayments = "";
        String balance = "";
        String status = "";
        String maturity = "";
        String datePaid = "";
        String amountPaid = "";
        try
        {
            do
            {
                marker = msg.getE02ENDFLD();
                if(marker.equals("*"))
                    break;
                if(firstTime)
                {
                    firstTime = false;
                } else
                {
                    number = number + nl;
                    paymentDate = paymentDate + nl;
                    principal = principal + nl;
                    interest = interest + nl;
                    otherCharges = otherCharges + nl;
                    totalPayments = totalPayments + nl;
                    balance = balance + nl;
                    status = status + nl;
                    maturity = maturity + nl;
                    datePaid = datePaid + nl;
                    amountPaid = amountPaid + nl;
                }
                number = number + msg.getE02DLPPNU();
                paymentDate = paymentDate + Util.formatDate(msg.getE02DLPPD1(), msg.getE02DLPPD2(), msg.getE02DLPPD3());
                principal = principal + Util.formatCCY(msg.getE02DLPPRN());
                interest = interest + Util.formatCCY(msg.getE02DLPINT());
                otherCharges = otherCharges + Util.formatCCY(msg.getE02DLPOTH());
                totalPayments = totalPayments + msg.getE02DLPTOT();
                balance = balance + msg.getE02DLPBAL();
                status = status + msg.getE02DLPSTS();
                maturity = maturity + msg.getE02DLPVEN();
                datePaid = datePaid + Util.formatDate(msg.getE02DLPDT1(), msg.getE02DLPDT2(), msg.getE02DLPDT3());
                amountPaid = amountPaid + msg.getE02DLPPAG();
                newmessage = mc.receiveMessage();
                msg = (EDL090002Message)newmessage;
            } while(true);
            addDataNode(data, "Loan.PaymentSchedule.Column.Number", number);
            addDataNode(data, "Loan.PaymentSchedule.Column.PaymentDate", paymentDate);
            addDataNode(data, "Loan.PaymentSchedule.Column.Principal", principal);
            addDataNode(data, "Loan.PaymentSchedule.Column.Interest", interest);
            addDataNode(data, "Loan.PaymentSchedule.Column.OtherCharges", otherCharges);
            addDataNode(data, "Loan.PaymentSchedule.Column.TotalPayment", totalPayments);
            addDataNode(data, "Loan.PaymentSchedule.Column.Balance", balance);
            addDataNode(data, "Loan.PaymentSchedule.Column.Status", status);
            addDataNode(data, "Loan.PaymentSchedule.Column.Maturity", maturity);
            addDataNode(data, "Loan.PaymentSchedule.Column.DatePaid", datePaid);
            addDataNode(data, "Loan.PaymentSchedule.Column.AmountPaid", amountPaid);
        }
        catch(Exception e)
        {
            e.printStackTrace();
            flexLog("Error: " + e);
            throw new RuntimeException("Socket Communication Error");
        }
    }

    protected void getLNDataForDataproForms(Object data, EFRM00080Message msg)
    {
        String dataItem = null;
        String nl = eIBSFormsNL;
        ESS0030DSMessage msgUser = null;
        HttpSession session = null;
        addDataNode(data, "Loan.Basic.DEACUN", msg.getE80DEACUN());
        addDataNode(data, "Loan.Basic.DEAACC", msg.getE80DEAACC());
        addDataNode(data, "Loan.Basic.CUSNA1", msg.getE80CUSNA1());
        addDataNode(data, "Loan.Basic.DEACCY", msg.getE80DEACCY());
        addDataNode(data, "Loan.Basic.DEAPRO", msg.getE80DEAPRO());
        addDataNode(data, "Loan.Basic.DEATYP", msg.getE80DEATYP());
        addDataNode(data, "Loan.Basic.DEAOD", Util.formatDate(msg.getE80DEAOD1(), msg.getE80DEAOD2(), msg.getE80DEAOD3()));
        addDataNode(data, "Loan.Basic.DEATRM", msg.getE80DEATRM());
        char lang = LangPath.charAt(LangPath.length() - 2);
        switch(lang)
        {
        default:
            break;

        case 69: // 'E'
            if(msg.getE80DEATRC().equals("D"))
            {
                dataItem = "Day(s)";
                break;
            }
            if(msg.getE80DEATRC().equals("M"))
            {
                dataItem = "Month(s)";
                break;
            }
            if(msg.getE80DEATRC().equals("Y"))
                dataItem = "Year(s)";
            else
                dataItem = "";
            break;

        case 83: // 'S'
            if(msg.getE80DEATRC().equals("D"))
            {
                dataItem = "D\355a(s)";
                break;
            }
            if(msg.getE80DEATRC().equals("M"))
            {
                dataItem = "Mes(es)";
                break;
            }
            if(msg.getE80DEATRC().equals("Y"))
                dataItem = "A\361o(s)";
            else
                dataItem = "";
            break;
        }
        addDataNode(data, "Loan.Basic.DEATRC", dataItem);
        addDataNode(data, "Loan.Basic.DEAMD", Util.formatDate(msg.getE80DEAMD1(), msg.getE80DEAMD2(), msg.getE80DEAMD3()));
        addDataNode(data, "Loan.Basic.DEAOAM", Util.formatCCY(msg.getE80DEAOAM()));
        addDataNode(data, "Loan.Basic.DEAMEP", Util.formatCCY(msg.getE80DEAMEP()));
        addDataNode(data, "Loan.Basic.DEAMEI", Util.formatCCY(msg.getE80DEAMEI()));
        addDataNode(data, "Loan.Basic.DEAMEM", Util.formatCCY(msg.getE80DEAMEM()));
        addDataNode(data, "Loan.Basic.DEAREA", Util.formatCCY(msg.getE80DEAREA()));
        addDataNode(data, "Loan.Basic.DEAXRL", Util.formatCCY(msg.getE80DEAXRL()));
        addDataNode(data, "Loan.Basic.TOTCOM", Util.formatCCY(msg.getE80TOTCOM()));
        addDataNode(data, "Loan.Basic.TOTDED", Util.formatCCY(msg.getE80TOTDED()));
        addDataNode(data, "Loan.Basic.TOTIMP", Util.formatCCY(msg.getE80TOTIMP()));
        addDataNode(data, "Loan.Basic.TOTIVA", Util.formatCCY(msg.getE80TOTIVA()));
        addDataNode(data, "Loan.Basic.BALANCE", Util.formatCCY(msg.getE80MEMBAL()));
        addDataNode(data, "Loan.Basic.BALLETTER", msg.getE80SALLET());
        addDataNode(data, "Loan.Basic.DEAFTB", msg.getE80DEAFTB());
        addDataNode(data, "Loan.Basic.DEAFTY", msg.getE80DEAFTY());
        addDataNode(data, "Loan.Basic.FLTRTE", msg.getE80FLTRTE());
        addDataNode(data, "Loan.Basic.DEAPEI", msg.getE80DEAPEI());
        addDataNode(data, "Loan.Basic.DEARTE", msg.getE80DEARTE());
        addDataNode(data, "Loan.Basic.DEABNK", msg.getE80DEABNK());
        addDataNode(data, "Loan.Basic.DEABRN", msg.getE80DEABRN());
        addDataNode(data, "Loan.Basic.DEAICT", msg.getE80DEAICT());
        addDataNode(data, "Loan.Basic.DEABAS", msg.getE80DEABAS());
        flexLog("End Block");
    }

    protected void getRTDataForDataproForms(Object data, EFRM00005Message msg)
    {
        String dataItem = null;
        String nl = eIBSFormsNL;
        addDataNode(data, "RetailAccount.Customer.Name.One", msg.getE05CUSNA1());
        addDataNode(data, "RetailAccount.Customer.ID.One", msg.getE05CUSIDN());
        addDataNode(data, "RetailAccount.AccountNumber", msg.getE05ACMACC());
        addDataNode(data, "RetailAccount.CodeCNOFC", msg.getE05ACMATY());
        addDataNode(data, "RetailAccount.ProdCode", msg.getE05ACMPRO());
        addDataNode(data, "RetailAccount.CustNumber", msg.getE05ACMCUN());
        addDataNode(data, "RetailAccount.BankNumber", msg.getE05ACMBNK());
        addDataNode(data, "RetailAccount.BranchNumber", msg.getE05ACMBRN());
        addDataNode(data, "RetailAccount.Currency", msg.getE05ACMCCY());
        addDataNode(data, "RetailAccount.BranchName", msg.getE05BRNNA1());
        addDataNode(data, "RetailAccount.OfficialDesc", msg.getE05DSCOFC());
        addDataNode(data, "RetailAccount.Initial.Date", Util.formatDate(msg.getE05OPNDTE()));
        addDataNode(data, "RetailAccount.MNB", msg.getE05ACMMNB());
        addDataNode(data, "RetailAccount.MNBCifras", toCifras(msg.getBigDecimalE05ACMMNB()));
        addDataNode(data, "RetailAccount.AVALBL", msg.getE05AVALBL());
        addDataNode(data, "RetailAccount.AVALBLCifras", toCifras(msg.getBigDecimalE05AVALBL()));
        addDataNode(data, "RetailAccount.NAV", msg.getE05ACMNAV());
        addDataNode(data, "RetailAccount.NAVCifras", toCifras(msg.getBigDecimalE05ACMNAV()));
    }

    protected String toCifras(BigDecimal dividendo)
    {
        int numCounter = 1;
        BigDecimal divisor = new BigDecimal(10D);
        String descNum = "";
        for(; dividendo.compareTo(divisor) == 1; dividendo = dividendo.divide(divisor, 6))
            numCounter++;

        if(numCounter > 0 && numCounter < 16)
            descNum = descNumbers[numCounter];
        if(dividendo.intValue() > 6)
            return descNum + " (" + numCounter + ") cifras altas";
        if(dividendo.intValue() > 3)
            return descNum + " (" + numCounter + ") cifras medias";
        else
            return descNum + " (" + numCounter + ") cifras bajas";
    }

    protected void getRTTitulars(Object data, EFRM00007Message msg, String titular)
    {
    	addDataNode(data, titular + ".ClientNumber", msg.getE07CUMRCN());
        addDataNode(data, titular + ".Nombre", msg.getE07CUMMA1());
        addDataNode(data, titular + ".Identificacion", msg.getE07CUMBNI());
        addDataNode(data, titular + ".TipoRel", msg.getE07CUMBSX());
        addDataNode(data, titular + ".Porcentaje", msg.getE07CUMMA1());
        addDataNode(data, titular + ".FechaNac", msg.getE07REPDT1() + "/" + msg.getE07REPDT2() + "/" + msg.getE07REPDT3());
        addDataNode(data, titular + ".Nacionalidad", msg.getE07REPNAC());
        addDataNode(data, titular + ".Direccion", msg.getE07CUMMA2() + " " + msg.getE07CUMMA3()+ " " + msg.getE07CUMMA4());
        addDataNode(data, titular + ".Ciudad", msg.getE07CUMCTY());
        addDataNode(data, titular + ".Pais", msg.getE07CUMCTR());
        
		addDataNode(data, titular + ".Calle", msg.getE07CUMNM4());
		addDataNode(data, titular + ".Edificio", msg.getE07CUMNM5());
		addDataNode(data, titular + ".Apto", msg.getE07CUSAPT());
		addDataNode(data, titular + ".Provincia", msg.getE07CUMSTE());
		addDataNode(data, titular + ".Distrito", msg.getE07CUSMUN());
		addDataNode(data, titular + ".Corregimiento", msg.getE07CUMCOR());
		
        addDataNode(data, titular + ".TelefonoRes", msg.getE07CUMHPN());
        addDataNode(data, titular + ".Celular", msg.getE07CUSPH1());
        addDataNode(data, titular + ".TelefonoOfi", msg.getE07CUSPHN());
        addDataNode(data, titular + ".Email", msg.getE07CUSIAD());
        addDataNode(data, titular + ".ApartadoPos", msg.getE07CUMPOB());
        addDataNode(data, titular + ".EstadoCiv", msg.getE07CUMMST());
        addDataNode(data, titular + ".Industria", msg.getE07CUSINC());
        addDataNode(data, titular + ".TipoNeg", msg.getE07CUSBUC());
        addDataNode(data, titular + ".RelacionBanco", msg.getE07CUSSTF());
        addDataNode(data, titular + ".ReferidoPor", msg.getE07CUSRBY());
        addDataNode(data, titular + ".Ocupacion", msg.getE07CUFUC3());
        addDataNode(data, titular + ".Empresa", msg.getE07CUSCP1());
        addDataNode(data, titular + ".NivelEdu", msg.getE07CUSEDL());
        addDataNode(data, titular + ".TiempoEmp", msg.getE07CUSTIM());
        addDataNode(data, titular + ".FuenteIng", msg.getE07CUSSOI());
    }

    protected void getRTSigners(Object data, EFRM00007Message msg, String signer)
    {
    	addDataNode(data, signer + ".ClientNumber", msg.getE07CUMRCN());
        addDataNode(data, signer + ".Nombre", msg.getE07CUMMA1());
        addDataNode(data, signer + ".Direccion", msg.getE07CUMMA2() + " " + msg.getE07CUMMA3()+ " " + msg.getE07CUMMA4());
        addDataNode(data, signer + ".Pais", msg.getE07CUMCTR());
		addDataNode(data, signer + ".Ciudad", msg.getE07CUMCTY());
		addDataNode(data, signer + ".Provincia", msg.getE07CUMSTE());
        addDataNode(data, signer + ".Calle", msg.getE07CUMNM4());
        addDataNode(data, signer + ".Edificio", msg.getE07CUMNM5());
        
        addDataNode(data, signer + ".Distrito", msg.getE07CUSMUN());
        addDataNode(data, signer + ".Corregimiento", msg.getE07CUMCOR());
		addDataNode(data, signer + ".Apto", msg.getE07CUSAPT());
        
        addDataNode(data, signer + ".ApartadoPos", msg.getE07CUMPOB());
        addDataNode(data, signer + ".CodigoPos", msg.getE07CUMZPC());
        addDataNode(data, signer + ".Identificacion", msg.getE07CUMBNI());
        addDataNode(data, signer + ".TelefonoRes", msg.getE07CUMHPN());
        addDataNode(data, signer + ".Celular", msg.getE07CUSPH1());
        addDataNode(data, signer + ".TelefonoOfi", msg.getE07CUSPHN());
        addDataNode(data, signer + ".Email", msg.getE07CUSIAD());
        addDataNode(data, signer + ".FirmaTipo", msg.getE07SIGTYP());
        addDataNode(data, signer + ".FirmaCat", msg.getE07SIGCAT());
        addDataNode(data, signer + ".FirmaFor", msg.getE07SIGFOR());
        addDataNode(data, signer + ".MontoLim", msg.getE07SIGLAM());
        addDataNode(data, signer + ".Comentarios", msg.getE07SIGCOM());
        addDataNode(data, signer + ".EstadoCiv", msg.getE07CUMMST());
        addDataNode(data, signer + ".Industria", msg.getE07CUSINC());
        addDataNode(data, signer + ".TipoNeg", msg.getE07CUSBUC());
        addDataNode(data, signer + ".RelacionBanco", msg.getE07CUSSTF());
        addDataNode(data, signer + ".ReferidoPor", msg.getE07CUSRBY());
        addDataNode(data, signer + ".Ocupacion", msg.getE07CUFUC3());
        addDataNode(data, signer + ".Empresa", msg.getE07CUSCP1());
        addDataNode(data, signer + ".DirEmpresa", msg.getE07CUSCP2());
        addDataNode(data, signer + ".NivelEdu", msg.getE07CUSEDL());
        addDataNode(data, signer + ".TiempoEmp", msg.getE07CUSTIM());
        addDataNode(data, signer + ".FuenteIng", msg.getE07CUSSOI());
        addDataNode(data, signer + ".FechaNac", msg.getE07REPDT1() + "/" + msg.getE07REPDT2() + "/" + msg.getE07REPDT3());
        addDataNode(data, signer + ".Nacionalidad", msg.getE07REPNAC());
    }

    protected void getRTRepLegals(Object data, EFRM00007Message msg, String repLegal)
    {
        addDataNode(data, repLegal + ".Nombre", msg.getE07CUMMA1());
        addDataNode(data, repLegal + ".Direccion", msg.getE07CUMMA2() + " " + msg.getE07CUMMA3()+ " " + msg.getE07CUMMA4());
        addDataNode(data, repLegal + ".Ciudad", msg.getE07CUMCTY());
        
		addDataNode(data, repLegal + ".Calle", msg.getE07CUMNM4());
		addDataNode(data, repLegal + ".Edificio", msg.getE07CUMNM5());
		addDataNode(data, repLegal + ".Apto", msg.getE07CUSAPT());
		addDataNode(data, repLegal + ".Provincia", msg.getE07CUMSTE());
		addDataNode(data, repLegal + ".Distrito", msg.getE07CUSMUN());
		addDataNode(data, repLegal + ".Corregimiento", msg.getE07CUMCOR());
		
        addDataNode(data, repLegal + ".Pais", msg.getE07CUMCTR());
        addDataNode(data, repLegal + ".ApartadoPos", msg.getE07CUMPOB());
        addDataNode(data, repLegal + ".CodigoPos", msg.getE07CUMZPC());
        addDataNode(data, repLegal + ".Nacionalidad", msg.getE07REPNAC());
        addDataNode(data, repLegal + ".Cargo", msg.getE07REPCAR());
        addDataNode(data, repLegal + ".FechaNac", msg.getE07REPDT1() + "/" + msg.getE07REPDT2() + "/" + msg.getE07REPDT3());
        addDataNode(data, repLegal + ".Profesion", msg.getE07REPPRO());
        addDataNode(data, repLegal + ".EnCalidad", " ");
        addDataNode(data, repLegal + ".EstadoCiv", msg.getE07CUMMST());
        addDataNode(data, repLegal + ".Sexo", msg.getE07CUMBSX());
        addDataNode(data, repLegal + ".Identificacion", msg.getE07CUMBNI());
        addDataNode(data, repLegal + ".TelefonoRes", msg.getE07CUMHPN());
        addDataNode(data, repLegal + ".Celular", msg.getE07CUSPH1());
        addDataNode(data, repLegal + ".TelefonoOfi", msg.getE07CUSPHN());
        addDataNode(data, repLegal + ".Email", msg.getE07CUSIAD());
        addDataNode(data, repLegal + ".Industria", msg.getE07CUSINC());
        addDataNode(data, repLegal + ".TipoNeg", msg.getE07CUSBUC());
        addDataNode(data, repLegal + ".RelacionBanco", msg.getE07CUSSTF());
        addDataNode(data, repLegal + ".ReferidoPor", msg.getE07CUSRBY());
        addDataNode(data, repLegal + ".Ocupacion", msg.getE07CUFUC3());
        addDataNode(data, repLegal + ".Empresa", msg.getE07CUSCP1());
        addDataNode(data, repLegal + ".NivelEdu", msg.getE07CUSEDL());
        addDataNode(data, repLegal + ".TiempoEmp", msg.getE07CUSTIM());
        addDataNode(data, repLegal + ".FuenteIng", msg.getE07CUSSOI());
    }

    protected void getRTLDInfo(Object data, EFRM00008Message msg)
    {
        addDataNode(data, "Cuenta.Lavado.DepositoEfecNum", msg.getE08LDMCDP());
        addDataNode(data, "Cuenta.Lavado.DepositoEfecMon", msg.getE08LDMCDA());
        addDataNode(data, "Cuenta.Lavado.DepositoOtrosNum", msg.getE08LDMKDP());
        addDataNode(data, "Cuenta.Lavado.DepositoOtrosMon", msg.getE08LDMKDA());
        addDataNode(data, "Cuenta.Lavado.DepositosNum", msg.getE08NUMDEP());
        addDataNode(data, "Cuenta.Lavado.DepositosMon", msg.getE08TOTDEP());
        addDataNode(data, "Cuenta.Lavado.RetiroEfecNum", msg.getE08LDMCWD());
        addDataNode(data, "Cuenta.Lavado.RetiroEfecMon", msg.getE08LDMCWA());
        addDataNode(data, "Cuenta.Lavado.PagoCheqNum", msg.getE08LDMCPY());
        addDataNode(data, "Cuenta.Lavado.PagoCheqMon", msg.getE08LDMCPA());
        addDataNode(data, "Cuenta.Lavado.RetiroNum", "" + msg.getE08NUMRET());
        addDataNode(data, "Cuenta.Lavado.RetiroMon", msg.getE08TOTRET());
        addDataNode(data, "Cuenta.Lavado.TransEmitNum", msg.getE08LDMTIN());
        addDataNode(data, "Cuenta.Lavado.TransEmitMon", msg.getE08LDMTIA());
        addDataNode(data, "Cuenta.Lavado.TransRecNum", msg.getE08LDMTRV());
        addDataNode(data, "Cuenta.Lavado.TransRecMon", msg.getE08LDMTRA());
        addDataNode(data, "Cuenta.Lavado.UsoCuenta", msg.getE08LDMUSO());
        addDataNode(data, "Cuenta.Lavado.ModoAper", msg.getE08LDMUC1());
        addDataNode(data, "Cuenta.Lavado.MontoAper", msg.getE08LDMAM1());
        addDataNode(data, "Cuenta.Lavado.PaisTransf1", msg.getE08LDMCN1());
        addDataNode(data, "Cuenta.Lavado.PaisTransf2", msg.getE08LDMCN2());
        addDataNode(data, "Cuenta.Lavado.PaisTransf3", msg.getE08LDMCN3());
        addDataNode(data, "Cuenta.Lavado.PaisTransf4", msg.getE08LDMCN4());
        if(msg.getE08LDMFG1().equals("1"))
        {
            addDataNode(data, "Cuenta.Lavado.Pais-Envio1", "S");
            addDataNode(data, "Cuenta.Lavado.Pais-Recibo1", "N");
        } else
        if(msg.getE08LDMFG1().equals("2"))
        {
            addDataNode(data, "Cuenta.Lavado.Pais-Envio1", "N");
            addDataNode(data, "Cuenta.Lavado.Pais-Recibo1", "S");
        } else
        if(msg.getE08LDMFG1().equals("B"))
        {
            addDataNode(data, "Cuenta.Lavado.Pais-Envio1", "S");
            addDataNode(data, "Cuenta.Lavado.Pais-Recibo1", "S");
        }
        if(msg.getE08LDMFG2().equals("1"))
        {
            addDataNode(data, "Cuenta.Lavado.Pais-Envio2", "S");
            addDataNode(data, "Cuenta.Lavado.Pais-Recibo2", "N");
        } else
        if(msg.getE08LDMFG2().equals("2"))
        {
            addDataNode(data, "Cuenta.Lavado.Pais-Envio2", "N");
            addDataNode(data, "Cuenta.Lavado.Pais-Recibo2", "S");
        } else
        if(msg.getE08LDMFG2().equals("B"))
        {
            addDataNode(data, "Cuenta.Lavado.Pais-Envio2", "S");
            addDataNode(data, "Cuenta.Lavado.Pais-Recibo2", "S");
        }
        if(msg.getE08LDMFG3().equals("1"))
        {
            addDataNode(data, "Cuenta.Lavado.Pais-Envio3", "S");
            addDataNode(data, "Cuenta.Lavado.Pais-Recibo3", "N");
        } else
        if(msg.getE08LDMFG3().equals("2"))
        {
            addDataNode(data, "Cuenta.Lavado.Pais-Envio3", "N");
            addDataNode(data, "Cuenta.Lavado.Pais-Recibo3", "S");
        } else
        if(msg.getE08LDMFG3().equals("B"))
        {
            addDataNode(data, "Cuenta.Lavado.Pais-Envio3", "S");
            addDataNode(data, "Cuenta.Lavado.Pais-Recibo3", "S");
        }
        if(msg.getE08LDMFG4().equals("1"))
        {
            addDataNode(data, "Cuenta.Lavado.Pais-Envio4", "S");
            addDataNode(data, "Cuenta.Lavado.Pais-Recibo4", "N");
        } else
        if(msg.getE08LDMFG4().equals("2"))
        {
            addDataNode(data, "Cuenta.Lavado.Pais-Envio4", "N");
            addDataNode(data, "Cuenta.Lavado.Pais-Recibo4", "S");
        } else
        if(msg.getE08LDMFG4().equals("B"))
        {
            addDataNode(data, "Cuenta.Lavado.Pais-Envio4", "S");
            addDataNode(data, "Cuenta.Lavado.Pais-Recibo4", "S");
        }
        addDataNode(data, "Cuenta.Lavado.SaldoPromLib", msg.getE08LDMGAV());
        addDataNode(data, "Cuenta.Lavado.SaldoNetLib", msg.getE08LDMNAV());
        
        addDataNode(data, "Cuenta.Lavado.OrigenFondos", msg.getE08LDMUC3());
        addDataNode(data, "Cuenta.Lavado.Motivo.Solicitud.Service", msg.getE08LDMMOT());
        addDataNode(data, "Cuenta.Lavado.Volumen.Mensual.Venta", msg.getE08LDMVOL());
    }

    protected void getClientAddInfo(Object data, EFRM00007Message msg, String prefix, int counter)
    {
        addDataNode(data, prefix + counter + ".MailAddress1", msg.getE07CUMMA1());
        addDataNode(data, prefix + counter + ".MailAddress2", msg.getE07CUMMA2());
        if(prefix.equals("BankRef"))
            addDataNode(data, prefix + counter + ".MailAddress3", msg.getE07CUMMA3());
        else
        if(prefix.equals("Board"))
            addDataNode(data, prefix + counter + ".MailAddress3", msg.getE07CUMMA2() + " " + msg.getE07CUMMA3());
        else
            addDataNode(data, prefix + counter + ".MailAddress3", msg.getE07CUMMA2() + " " + msg.getE07CUMMA3() + " " + msg.getE07CUMMA4());
        addDataNode(data, prefix + counter + ".MailAddress4", msg.getE07CUMMA4());
        if(msg.getE07CUMCTY().length() > 4)
            addDataNode(data, prefix + counter + ".City", msg.getE07CUMCTY().substring(4, msg.getE07CUMCTY().length()));
        else
            addDataNode(data, prefix + counter + ".City", msg.getE07CUMCTY());
        addDataNode(data, prefix + counter + ".State", msg.getE07CUMSTE());
        addDataNode(data, prefix + counter + ".ZipCode", msg.getE07CUMZPC());
        addDataNode(data, prefix + counter + ".POBox", msg.getE07CUMPOB());
        addDataNode(data, prefix + counter + ".Country", msg.getE07CUMCTR());
        addDataNode(data, prefix + counter + ".MailCode", msg.getE07CUMBNI());
        addDataNode(data, prefix + counter + ".HomePhone", msg.getE07CUMHPN());
        addDataNode(data, prefix + counter + ".Citizenship", msg.getE07REPNAC());
        addDataNode(data, prefix + counter + ".Identification", msg.getE07CUMBNI());
        addDataNode(data, prefix + counter + ".Position", msg.getE07CUMMA4());
        addDataNode(data, prefix + counter + ".Gender", msg.getE07CUMBSX());
        addDataNode(data, prefix + counter + ".MaritalStatus", msg.getE07CUMMST());
        addDataNode(data, prefix + counter + ".Date", Util.formatDate(msg.getE07REPDT1(), msg.getE07REPDT2(), msg.getE07REPDT3()));
        addDataNode(data, prefix + counter + ".Celular", msg.getE07CUSPH1());
        addDataNode(data, prefix + counter + ".Email", msg.getE07CUSIAD());
    }

    protected void getDDADataForBankersForms(Object data, EFRM00095Message msg)
    {
        String dataItem = null;
        String nl = "*13*";
        dataItem = msg.getE95CUSNA1() + nl + msg.getE95CUSNA2() + nl + msg.getE95CUSCTY() + ", " + msg.getE95CUSSTE() + " " + msg.getE95CUSZPC();
        addDataNode(data, "Owner.FullNameStreetCityStateZip", dataItem);
        addDataNode(data, "Owner.Secretary.FullName", msg.getE95SECNME());
        addDataNode(data, "Owner.Organization.State.Text", msg.getD95CUSSTE());
        addDataNode(data, "Owner.TaxIdentificationNumber", msg.getE95CUSIDF());
        addDataNode(data, "Owner.TradeName", msg.getE95CUSNA1());
        addDataNode(data, "Authorization.Date", "");
        addDataNode(data, "AnySigner.Agent.FullNameTitle", msg.getE95OFNPO1());
        addDataNode(data, "AnySigner.Agent2.FullNameTitle", msg.getE95OFNPO2());
        addDataNode(data, "AnySigner.Agent3.FullNameTitle", msg.getE95OFNPO3());
        addDataNode(data, "AnySigner.Agent4.FullNameTitle", msg.getE95OFNPO4());
        addDataNode(data, "AnySigner.Agent5.FullNameTitle", msg.getE95OFNPO5());
        addDataNode(data, "AnySigner.Agent6.FullNameTitle", msg.getE95OFNPO6());
        addDataNode(data, "Authorization.PowersGranted.AllPowers.SignerIdentification", "");
        addDataNode(data, "Authorization.PowersGranted.OpenDepositOrShareAccounts.SignerIdentification", "");
        addDataNode(data, "Authorization.PowersGranted.EndorseChecksAndOrders.SignerIdentification", "");
        addDataNode(data, "Authorization.PowersGranted.BorrowMoney.SignerIdentification", "");
        addDataNode(data, "Authorization.PowersGranted.EndorseAssignTransfer.SignerIdentification", "");
        addDataNode(data, "Authorization.PowersGranted.EnterIntoLease.SignerIdentification", "");
        addDataNode(data, "Authorization.PowersGranted.Other.SignerIdentification", "");
        addDataNode(data, "Authorization.PowersGranted.AllPowers.Number", "");
        addDataNode(data, "Authorization.PowersGranted.OpenDepositOrShareAccounts.Number", "");
        addDataNode(data, "Authorization.PowersGranted.EndorseChecksAndOrders.Number", "");
        addDataNode(data, "Authorization.PowersGranted.BorrowMoney.Number", "");
        addDataNode(data, "Authorization.PowersGranted.EndorseAssignTransfer.Number", "");
        addDataNode(data, "Authorization.PowersGranted.EnterIntoLease.Number", "");
        addDataNode(data, "Authorization.PowersGranted.Other.Number", "");
        addDataNode(data, "Form.Date", "");
        addDataNode(data, "Owner.Signer.FullNameTitle", msg.getE95OFNPO1());
        addDataNode(data, "Owner.Signer2.FullNameTitle", msg.getE95OFNPO2());
        addDataNode(data, "Owner.Signer3.FullNameTitle", msg.getE95OFNPO3());
        addDataNode(data, "Owner.Signer4.FullNameTitle", msg.getE95OFNPO4());
        addDataNode(data, "Owner.Signer5.FullNameTitle", msg.getE95OFNPO5());
        addDataNode(data, "Owner.Signer6.FullNameTitle", msg.getE95OFNPO6());
    }

    protected void getTRDataForDataproForms(Object data, EWD0120DSMessage msg, int pos)
    {
        String prefix = "OfficialCheck.";
        String sufix = "";
        String strDebit = "";
        String strCredit = "";
        BigDecimal debit = new BigDecimal(0.0D);
        BigDecimal credit = new BigDecimal(0.0D);
        switch(pos)
        {
        case 1: // '\001'
            sufix = ".One";
            break;

        case 2: // '\002'
            sufix = ".Two";
            break;

        case 3: // '\003'
            sufix = ".Three";
            break;

        case 4: // '\004'
            sufix = ".Four";
            break;

        case 5: // '\005'
            sufix = ".Five";
            break;

        case 6: // '\006'
            sufix = ".Six";
            break;

        case 7: // '\007'
            sufix = ".Seven";
            break;

        case 8: // '\b'
            sufix = ".Eight";
            break;

        case 9: // '\t'
            sufix = ".Nine";
            break;

        case 10: // '\n'
            sufix = ".Ten";
            break;

        case 11: // '\013'
            sufix = ".Eleven";
            break;

        default:
            prefix = "";
            break;
        }
        if(msg.getE01WRKDCC().equals("D"))
        {
            debit = debit.add(msg.getBigDecimalE01WRKAMT());
            strDebit = Util.fcolorCCY(msg.getE01WRKAMT());
            strCredit = "&nbsp;";
        } else
        if(msg.getE01WRKDCC().equals("C"))
        {
            credit = credit.add(msg.getBigDecimalE01WRKAMT());
            strDebit = "&nbsp;";
            strCredit = Util.fcolorCCY(msg.getE01WRKAMT());
        }
        addDataNode(data, prefix + "Bank" + sufix, msg.getE01WRKBNK());
        addDataNode(data, prefix + "Branch" + sufix, msg.getE01WRKBRN());
        addDataNode(data, prefix + "Currency" + sufix, msg.getE01WRKCCY());
        addDataNode(data, prefix + "Ledger" + sufix, msg.getE01WRKGLN());
        addDataNode(data, prefix + "Reference" + sufix, msg.getE01WRKACC());
        addDataNode(data, prefix + "CCost" + sufix, msg.getE01WRKCCN());
        addDataNode(data, prefix + "TrCode" + sufix, msg.getE01WRKCDE());
        addDataNode(data, prefix + "Debit" + sufix, strDebit);
        addDataNode(data, prefix + "Credit" + sufix, strCredit);
        addDataNode(data, prefix + "Description" + sufix, msg.getE01WRKTDS());
    }

    private void procReqApplication(ESS0030DSMessage user, HttpServletRequest req, HttpServletResponse res, HttpSession ses)
        throws ServletException, IOException
    {
        ELEERRMessage msgError = null;
        UserPos userPO = null;
        try
        {
            msgError = (ELEERRMessage)Beans.instantiate(getClass().getClassLoader(), "datapro.eibs.beans.ELEERRMessage");
            userPO = (UserPos)Beans.instantiate(getClass().getClassLoader(), "datapro.eibs.beans.UserPos");
            userPO.setOption("FORMS");
            userPO.setPurpose("APPLICATIONS");
            ses.setAttribute("error", msgError);
            ses.setAttribute("userPO", userPO);
        }
        catch(Exception ex)
        {
            flexLog("Error: " + ex);
        }
        try
        {
            flexLog("About to call Page: " + LangPath + "EFRM000_forms_applications.jsp");
            callPage(LangPath + "EFRM000_forms_applications.jsp", req, res);
        }
        catch(Exception e)
        {
            flexLog("Exception calling page " + e);
        }
    }

    protected void getSPDataForBankersForms(Object data, EFRM00006Message msg)
    {
        String dataItem = null;
        String nl = "*13*";
        dataItem = msg.getE06CUSNA1().trim() + nl + msg.getE06CUSNA2().trim() + nl + msg.getE06CUSNA3().trim() + nl + msg.getE06CUSNA4().trim();
        addDataNode(data, "AllDepositors.FullNameStreetCityStateZip", dataItem);
        addDataNode(data, "Deposit.AccountNumber", msg.getE06ACMACC());
        addDataNode(data, "Deposit.StopPayment.Fee.Amount", "");
        addDataNode(data, "Deposit.StopPayment.Item.Amount", Util.formatCCY(msg.getE06STPAMT()));
        addDataNode(data, "Deposit.StopPayment.Item.Date", Util.formatDate(msg.getE06STPCK1(), msg.getE06STPCK2(), msg.getE06STPCK3()));
        addDataNode(data, "Deposit.StopPayment.Item.Other.Text", msg.getE06STPRMK());
        addDataNode(data, "Deposit.StopPayment.Item.Replaced.Date", "");
        addDataNode(data, "Deposit.StopPayment.Item.Replacement.No", "");
        addDataNode(data, "Deposit.StopPayment.Item.Replacement.Yes", "");
        addDataNode(data, "Deposit.StopPayment.Item.ReplacementNumber", "");
        addDataNode(data, "Deposit.StopPayment.ItemNumber", msg.getE06STPFCK() + " - " + msg.getE06STPTCK());
        addDataNode(data, "Deposit.StopPayment.Reason.Text", msg.getE06STPF02());
        addDataNode(data, "Deposit.StopPayment.RequestReceived.InPerson.Yes", "");
        addDataNode(data, "Deposit.StopPayment.RequestReceived.Other.Text", "");
        addDataNode(data, "Deposit.StopPayment.RequestReceived.Other.Yes", "");
        addDataNode(data, "Deposit.StopPayment.RequestReceived.Phone.Yes", "");
        addDataNode(data, "Deposit.StopPayment.Revocation.Date", "");
        addDataNode(data, "DepositoryInstitution.FullName", msg.getE06INSNA1());
        addDataNode(data, "DepositoryInstitution.Officer.FullName", msg.getE06DSCOFC());
        addDataNode(data, "Form.Date", Util.formatDate(msg.getE06STPDT1(), msg.getE06STPDT2(), msg.getE06STPDT3()));
        addDataNode(data, "Form.TimeAMPM", Util.formatTime(msg.getE06STPTIM()));
        addDataNode(data, "Party.FullName", msg.getE06CUSNA1());
        addDataNode(data, "Payee.FullName", "");
    }

    protected void getLCStandByDataForDataproForms(Object data, EFRM00043Message msg)
    {
        addDataNode(data, "LetterOfCredit.StandBy.LCMACC", msg.getE43LCMACC());
        addDataNode(data, "LetterOfCredit.StandBy.LCMCUN", msg.getE43LCMCUN());
        addDataNode(data, "LetterOfCredit.StandBy.LCMPRO", msg.getE43LCMPRO());
        addDataNode(data, "LetterOfCredit.StandBy.LCMTYP", msg.getE43LCMTYP());
        addDataNode(data, "LetterOfCredit.StandBy.LCMACD", msg.getE43LCMACD());
        addDataNode(data, "LetterOfCredit.StandBy.LCMBNK", msg.getE43LCMBNK());
        addDataNode(data, "LetterOfCredit.StandBy.LCMBRN", msg.getE43LCMBRN());
        addDataNode(data, "LetterOfCredit.StandBy.LCMCCY", msg.getE43LCMCCY());
        addDataNode(data, "LetterOfCredit.StandBy.LCMGLN", msg.getE43LCMGLN());
        addDataNode(data, "LetterOfCredit.StandBy.DSCPRO", msg.getE43DSCPRO());
        addDataNode(data, "LetterOfCredit.StandBy.ISSDTE", Util.formatDate(msg.getE43LCMID1(), msg.getE43LCMID2(), msg.getE43LCMID3()));
        addDataNode(data, "LetterOfCredit.StandBy.EXPDTE", Util.formatDate(msg.getE43LCMEX1(), msg.getE43LCMEX2(), msg.getE43LCMEX3()));
        addDataNode(data, "LetterOfCredit.StandBy.LCMOAM", msg.getE43LCMOAM());
        addDataNode(data, "LetterOfCredit.StandBy.LETOAM", msg.getE43LETOAM());
        addDataNode(data, "LetterOfCredit.StandBy.STATUS", msg.getE43STATUS());
        addDataNode(data, "LetterOfCredit.StandBy.LCMCAM", msg.getE43LCMCAM());
        addDataNode(data, "LetterOfCredit.StandBy.LCMIRT", msg.getE43LCMIRT());
        addDataNode(data, "LetterOfCredit.StandBy.LCMC10", msg.getE43LCMC10());
        addDataNode(data, "LetterOfCredit.StandBy.LCMC11", msg.getE43LCMC11());
        addDataNode(data, "LetterOfCredit.StandBy.LCMC12", msg.getE43LCMC12());
        addDataNode(data, "LetterOfCredit.StandBy.LCMLNR", msg.getE43LCMLNR());
        addDataNode(data, "LetterOfCredit.StandBy.LCMCMN", msg.getE43LCMCMN());
        addDataNode(data, "LetterOfCredit.StandBy.LCMOFX", msg.getE43LCMOFX());
        addDataNode(data, "LetterOfCredit.StandBy.LCMAMT", msg.getE43LCMAMT());
        addDataNode(data, "LetterOfCredit.StandBy.LCMREA", msg.getE43LCMREA());
        addDataNode(data, "LetterOfCredit.StandBy.LCMINT", msg.getE43LCMINT());
        addDataNode(data, "LetterOfCredit.StandBy.LCMCND", Util.formatDate(msg.getE43LCMCN1(), msg.getE43LCMCN2(), msg.getE43LCMCN3()));
        addDataNode(data, "LetterOfCredit.StandBy.CUSLGT", msg.getE43CUSLGT());
        addDataNode(data, "LetterOfCredit.StandBy.CUSNA1", msg.getE43CUSNA1());
        addDataNode(data, "LetterOfCredit.StandBy.CUSNA2", msg.getE43CUSNA2());
        addDataNode(data, "LetterOfCredit.StandBy.CUSNA3", msg.getE43CUSNA3());
        addDataNode(data, "LetterOfCredit.StandBy.CUSNA4", msg.getE43CUSNA4());
        addDataNode(data, "LetterOfCredit.StandBy.CUSNA5", msg.getE43CUSNA5());
        addDataNode(data, "LetterOfCredit.StandBy.CUSCTR", msg.getE43CUSCTR());
        addDataNode(data, "LetterOfCredit.StandBy.CUSCTY", msg.getE43CUSCTY());
        addDataNode(data, "LetterOfCredit.StandBy.CUSSTE", msg.getE43CUSSTE());
        addDataNode(data, "LetterOfCredit.StandBy.CUSZPC", msg.getE43CUSZPC());
        addDataNode(data, "LetterOfCredit.StandBy.CUSPOB", msg.getE43CUSPOB());
        addDataNode(data, "LetterOfCredit.StandBy.CUSIDN", msg.getE43CUSIDN());
        addDataNode(data, "LetterOfCredit.StandBy.CUSIDF", msg.getE43CUSIDF());
        addDataNode(data, "LetterOfCredit.StandBy.TAXIDE", msg.getE43TAXIDE());
        addDataNode(data, "LetterOfCredit.StandBy.CUSHPN", msg.getE43CUSHPN());
        addDataNode(data, "LetterOfCredit.StandBy.CUSPHN", msg.getE43CUSPHN());
        addDataNode(data, "LetterOfCredit.StandBy.CUSIAD", msg.getE43CUSIAD());
        addDataNode(data, "LetterOfCredit.StandBy.CUSCP1", msg.getE43CUSCP1());
        addDataNode(data, "LetterOfCredit.StandBy.CUSCP2", msg.getE43CUSCP2());
        addDataNode(data, "LetterOfCredit.StandBy.CUSCP3", msg.getE43CUSCP3());
        addDataNode(data, "LetterOfCredit.StandBy.CUSTIM", msg.getE43CUSTIM());
        addDataNode(data, "LetterOfCredit.StandBy.CUSFNA", msg.getE43CUSFNA());
        addDataNode(data, "LetterOfCredit.StandBy.CUSACA", msg.getE43CUSACA());
        addDataNode(data, "LetterOfCredit.StandBy.CUSFN2", msg.getE43CUSFN2());
        addDataNode(data, "LetterOfCredit.StandBy.CUSLN1", msg.getE43CUSLN1());
        addDataNode(data, "LetterOfCredit.StandBy.CUSLN2", msg.getE43CUSLN2());
        addDataNode(data, "LetterOfCredit.StandBy.CUBDTE", Util.formatDate(msg.getE43CUSBDM(), msg.getE43CUSBDD(), msg.getE43CUSBDY()));
        addDataNode(data, "LetterOfCredit.StandBy.CUSSEX", msg.getE43CUSSEX());
        addDataNode(data, "LetterOfCredit.StandBy.CUSMST", msg.getE43CUSMST());
        addDataNode(data, "LetterOfCredit.StandBy.INSNA1", msg.getE43INSNA1());
        addDataNode(data, "LetterOfCredit.StandBy.INSNA2", msg.getE43INSNA2());
        addDataNode(data, "LetterOfCredit.StandBy.INSNA3", msg.getE43INSNA3());
        addDataNode(data, "LetterOfCredit.StandBy.INSNA4", msg.getE43INSNA4());
        addDataNode(data, "LetterOfCredit.StandBy.INSIDE", msg.getE43INSIDE());
        addDataNode(data, "LetterOfCredit.StandBy.INSPHN", msg.getE43INSPHN());
        addDataNode(data, "LetterOfCredit.StandBy.BRNNA1", msg.getE43BRNNA1());
        addDataNode(data, "LetterOfCredit.StandBy.BRNNA2", msg.getE43BRNNA2());
        addDataNode(data, "LetterOfCredit.StandBy.BRNNA3", msg.getE43BRNNA3());
        addDataNode(data, "LetterOfCredit.StandBy.BRNNA4", msg.getE43BRNNA4());
        addDataNode(data, "LetterOfCredit.StandBy.BRNPHN", msg.getE43BRNPHN());
        addDataNode(data, "LetterOfCredit.StandBy.BRNIDE", msg.getE43BRNIDE());
        addDataNode(data, "LetterOfCredit.StandBy.RUNDTE", msg.getE43RUNDTE());
        addDataNode(data, "LetterOfCredit.StandBy.LGNA01", msg.getE43LGNA01());
        addDataNode(data, "LetterOfCredit.StandBy.LGNA02", msg.getE43LGNA02());
        addDataNode(data, "LetterOfCredit.StandBy.LGNA03", msg.getE43LGNA03());
        addDataNode(data, "LetterOfCredit.StandBy.LGNA04", msg.getE43LGNA04());
        addDataNode(data, "LetterOfCredit.StandBy.LGNA05", msg.getE43LGNA05());
        addDataNode(data, "LetterOfCredit.StandBy.LGNA06", msg.getE43LGNA06());
        addDataNode(data, "LetterOfCredit.StandBy.LGNA07", msg.getE43LGNA07());
        addDataNode(data, "LetterOfCredit.StandBy.LGNA08", msg.getE43LGNA08());
        addDataNode(data, "LetterOfCredit.StandBy.LGNA09", msg.getE43LGNA09());
        addDataNode(data, "LetterOfCredit.StandBy.LGNA10", msg.getE43LGNA10());
        addDataNode(data, "LetterOfCredit.StandBy.MLANA1", msg.getE43MLANA1());
        addDataNode(data, "LetterOfCredit.StandBy.MLANA2", msg.getE43MLANA2());
        addDataNode(data, "LetterOfCredit.StandBy.MLANA3", msg.getE43MLANA3());
        addDataNode(data, "LetterOfCredit.StandBy.MLANA4", msg.getE43MLANA4());
        addDataNode(data, "LetterOfCredit.StandBy.MLANA5", msg.getE43MLANA5());
        addDataNode(data, "LetterOfCredit.StandBy.MLACTR", msg.getE43MLACTR());
        addDataNode(data, "LetterOfCredit.StandBy.MLACTY", msg.getE43MLACTY());
        addDataNode(data, "LetterOfCredit.StandBy.MLASTE", msg.getE43MLASTE());
        addDataNode(data, "LetterOfCredit.StandBy.MLAZPC", msg.getE43MLAZPC());
        addDataNode(data, "LetterOfCredit.StandBy.MLAPOB", msg.getE43MLAPOB());
        addDataNode(data, "LetterOfCredit.StandBy.BNFNME", msg.getE43BNFNME());
        addDataNode(data, "LetterOfCredit.StandBy.BNFNM2", msg.getE43BNFNM2());
        addDataNode(data, "LetterOfCredit.StandBy.BNFNM3", msg.getE43BNFNM3());
        addDataNode(data, "LetterOfCredit.StandBy.BNFNM4", msg.getE43BNFNM4());
        addDataNode(data, "LetterOfCredit.StandBy.BNFCTR", msg.getE43BNFCTR());
        addDataNode(data, "LetterOfCredit.StandBy.BNFCTY", msg.getE43BNFCTY());
        addDataNode(data, "LetterOfCredit.StandBy.BNFPOB", msg.getE43BNFPOB());
        addDataNode(data, "LetterOfCredit.StandBy.BNFZPC", msg.getE43BNFZPC());
        addDataNode(data, "LetterOfCredit.StandBy.BNFIDE", msg.getE43BNFIDE());
        addDataNode(data, "LetterOfCredit.StandBy.SPINS1", msg.getE01SPINS1());
        addDataNode(data, "LetterOfCredit.StandBy.SPINS2", msg.getE01SPINS2());
        addDataNode(data, "LetterOfCredit.StandBy.SPINS3", msg.getE01SPINS3());
        addDataNode(data, "LetterOfCredit.StandBy.SPINS4", msg.getE01SPINS4());
        addDataNode(data, "LetterOfCredit.StandBy.PAGFLG", msg.getE01PAGFLG());
        addDataNode(data, "LetterOfCredit.StandBy.CAPFLG", msg.getE01CAPFLG());
        addDataNode(data, "LetterOfCredit.StandBy.CBRFLG", msg.getE01CBRFLG());
        addDataNode(data, "LetterOfCredit.StandBy.GAREFE", msg.getE01GAREFE());
    }
    
    
    private String getMaritalStatusDesc(String s){
    	int code;
    	String desc="";
    	try{
    		code=Integer.parseInt(s);
    	}
    	catch(Exception e){
    		code=0;
    	}
    	
    	switch(code)
    	{
    		case 0:
    			break;
    		case 1:
    			desc="Soltero(a)";
    			break;
    		case 2:
    			desc="Casado(a)";
    			break;
    		case 3:
    			desc="Divorciado(a)";
    			break;
    		case 4:
    			desc="Viudo(a)";
    			break;
    		case 5:
    			desc="Otros";
    			break;
    		default:
    			break;
    	}
    	return desc;
    }
    
    

}