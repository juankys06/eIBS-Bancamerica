package datapro.eibs.master;

import java.util.*;

/**
 * Encapsulate the key properties associated with eIBS.
 */
public class JSEIBSProp {
	private static String fieldDriver, 
						  fieldOwner, 
						  fieldUserid, 
						  fieldPassword, 
						  fieldDbURL, 
						  fieldHostIP, 
						  fieldIniSocket, 
						  fieldWebAppPath, 
						  fieldLog, 
						  fieldChkMsgPeriod, 
						  fieldSocketTimeOut,
						  fieldRootPath,
						  fieldSendRedirectCtx,
						  fieldBgPage,
						  fieldBusyPage,
						  fieldDevPage,
						  fieldSckNotOpenPage,
						  fieldSckNotRespondPage,
						  fieldVoiceActive,
						  fieldFtpPathFormConfig,
						  fieldFtpPathFormData,
						  fieldFtpPathScanData,
						  fieldImgExt,
						  fieldFormActive,
						  fieldScanActive,
						  fieldImageRemote,
						  fieldSignOn,
						  fieldMaxIterations,
						  fieldPopUpTimeOut,
						  fieldShowCollateralList,
						  fieldShowDeductionList,
						  fieldRelease,
						  fieldScanURL,
						  fieldScanDataURL,
						  fieldImgToIFS,
						  fieldImgTempPath,
						  fieldDbSchema,
						  fieldDefaultDataScheme,
						  fieldMinimumTextSize,
						  fieldIBSSchema,
						  fieldEODPDFPath,
						  fieldDataSource,
						  fieldEODDirectUrl,
						  fieldAutoNumCD,
						  fieldAutoNumRT,
						  fieldAutoNumSV,
						  fieldAutoNumLN,
						  fieldAutoNumCOLL,
						  fieldAutoNumOCK,
						  fieldCustomerInfoInputFormat,
						  fieldProtectedBNKBRN,
						  fieldEODPDFURL,
						  fieldEODDataSource,
						  fieldPYURL,
						  fieldFORMPDFURL,
						  fieldSessionPluginClass,
						  fieldICheck,
						  fieldICheckURL,
						  fieldICheckBankID,
						  fieldLogoPathName;

	// FTP Parameters for eIBSForms
	private static String 	fieldFtpFormHost,
							fieldFtpFormPort,
							fieldFtpFormUserID,
							fieldFtpFormPassword,
							fieldFtpImgPassive,
							fieldFtpFormFirewallType,
							fieldFtpFormFWAuthenticate,
							fieldFtpFormFWUserID,
							fieldFtpFormFWPassword,
							fieldBankerPDFURL,
							fieldBankerPDFURL1;

	// FTP Parameters for eIBSImage
	private static String 	fieldFtpImgHost,
							fieldFtpImgPort,
							fieldFtpImgUserID,
							fieldFtpImgPassword,
							fieldFtpImgFirewallType,
							fieldFtpImgFWAuthenticate,
							fieldFtpImgFWUserID,
							fieldFtpImgFWPassword;

	// PARAMETERS FOR IMAGE EXCHANGE - AZERTIA - BANCO TESORO
	private static String fieldImgExportPath,
						  fieldImgImportPath ;
	
	// Parameters For Check Reader Tools
	private static String 	fieldCrtServer,
							fieldCrtDatabase,
							fieldCrtWinAuth,
							fieldCrtUser,
							fieldCrtPassword,
							fieldCrtWorkDir,
							fieldCrtScanner,
							fieldCrtMicr;
	
	private static String propertyFileName = "eIBS";
/**
 * JSEIBSProp constructor comment.
 */
public JSEIBSProp() {
	super();
}
public static String getPropertyFileName() {
	return propertyFileName;
}
public static void setPropertyFileName(String name) {
	propertyFileName = name;
}
/**
 * Gets the background page property (java.lang.String) value.
 * @return The bgPage property value.
 * @see #setBgPage
 */
public static String getBgPage() {
	if (fieldBgPage == null) initProperties();
	return fieldBgPage;
}
/**
 * Sets the background page property (java.lang.String) value.
 * @param bgPage The new value for the property.
 * @see #getBgPage
 */
public static void setBgPage(String bgPage) {
	fieldBgPage = bgPage;
}
public static String getBusyPage() {
	if (fieldBusyPage == null) initProperties();
	return fieldBusyPage;
}
public static void setBusyPage(String busyPage) {
	fieldBusyPage = busyPage;
}
public static String getChkMsgPeriod() {
	if (fieldChkMsgPeriod == null) initProperties();
	return fieldChkMsgPeriod;
}
public static String getDbURL() {
	if (fieldDbURL == null) initProperties();
	return fieldDbURL;
}
public static String getDevPage() {
	if (fieldDevPage == null) initProperties();
	return fieldDevPage;
}
public static String getDriver() {
	if (fieldDriver == null) initProperties();
	return fieldDriver;
}
public static String getFtpFormFirewallType() {
	if (fieldFtpFormFirewallType == null) initProperties();
	return fieldFtpFormFirewallType;
}
public static String getFtpImgFirewallType() {
	if (fieldFtpImgFirewallType == null) initProperties();
	return fieldFtpImgFirewallType;
}
public static boolean getFormActive() {
	if (fieldFormActive == null) initProperties();
	if (fieldFormActive.equalsIgnoreCase("TRUE"))
		return true;
	else
		return false;
}
public static String getFtpFormHost() {
	if (fieldFtpFormHost == null) initProperties();
	return fieldFtpFormHost;
}
public static String getFtpImgHost() {
	if (fieldFtpImgHost == null) initProperties();
	return fieldFtpImgHost;
}
public static String getFtpFormPassword() {
	if (fieldFtpFormPassword == null) initProperties();
	return fieldFtpFormPassword;
}
public static String getFtpImgPassword() {
	if (fieldFtpImgPassword == null) initProperties();
	return fieldFtpImgPassword;
}
public static String getFtpImgPassive() {
	if (fieldFtpImgPassive == null) initProperties();
	return fieldFtpImgPassive;
}
public static String getFtpPathFormConfig() {
	if (fieldFtpPathFormConfig == null) initProperties();
	return fieldFtpPathFormConfig;
}
public static String getFtpPathFormData() {
	if (fieldFtpPathFormData == null) initProperties();
	return fieldFtpPathFormData;
}
public static String getFtpPathScanData() {
	if (fieldFtpPathScanData == null) initProperties();
	return fieldFtpPathScanData;
}
public static String getFtpFormPort() {
	if (fieldFtpFormPort == null) initProperties();
	return fieldFtpFormPort;
}
public static String getFtpImgPort() {
	if (fieldFtpImgPort == null) initProperties();
	return fieldFtpImgPort;
}
public static String getFtpFormUserID() {
	if (fieldFtpFormUserID == null) initProperties();
	return fieldFtpFormUserID;
}
public static String getFtpImgUserID() {
	if (fieldFtpImgUserID == null) initProperties();
	return fieldFtpImgUserID;
}
public static String getFtpFormFWAuthenticate() {
	if (fieldFtpFormFWAuthenticate == null) initProperties();
	return fieldFtpFormFWAuthenticate;
}
public static String getFtpImgFWAuthenticate() {
	if (fieldFtpImgFWAuthenticate == null) initProperties();
	return fieldFtpImgFWAuthenticate;
}
public static String getFtpFormFWPassword() {
	if (fieldFtpFormFWPassword == null) initProperties();
	return fieldFtpFormFWPassword;
}
public static String getFtpImgFWPassword() {
	if (fieldFtpImgFWPassword == null) initProperties();
	return fieldFtpImgFWPassword;
}
public static String getFtpFormFWUserID() {
	if (fieldFtpFormFWUserID == null) initProperties();
	return fieldFtpFormFWUserID;
}
public static String getFtpImgFWUserID() {
	if (fieldFtpImgFWUserID == null) initProperties();
	return fieldFtpImgFWUserID;
}
public static String getHostIP() {
	if (fieldHostIP == null) initProperties();
	return fieldHostIP;
}
public static String getImgExt() {
	if (fieldImgExt == null) initProperties();
	return fieldImgExt;
}
public static int getIniSocket() {
	if (fieldIniSocket == null) initProperties();
	return Integer.parseInt(fieldIniSocket);
}
public static int getLog() {
	if (fieldLog == null) initProperties();
	return Integer.parseInt(fieldLog);
}
public static String getOwner() {
	if (fieldOwner == null) initProperties();
	return fieldOwner;
}
public static String getPassword() {
	if (fieldPassword == null) initProperties();
	return fieldPassword;
}
public static String getPopUpTimeOut() {
	if (fieldPopUpTimeOut == null) initProperties();
	return fieldPopUpTimeOut;
}
public static String getRootPath() {
	if (fieldRootPath == null) initProperties();
	return fieldRootPath;
}
public static String getSendRedirectCtx() {
	if (fieldSendRedirectCtx == null) initProperties();
	return fieldSendRedirectCtx;
}
public static boolean getScanActive() {
	if (fieldScanActive == null) initProperties();
	if (fieldScanActive.equalsIgnoreCase("TRUE"))
		return true;
	else
		return false;
}
public static boolean getImageRemote() {
	if (fieldImageRemote == null) initProperties();
	if (fieldImageRemote.equalsIgnoreCase("TRUE"))
		return true;
	else
		return false;
}
public static String getSckNotOpenPage() {
	if (fieldSckNotOpenPage == null) initProperties();
	return fieldSckNotOpenPage;
}
public static String getSckNotRespPage() {
	if (fieldSckNotRespondPage == null) initProperties();
	return fieldSckNotRespondPage;
}
public static boolean getShowCollateralList() {
	if (fieldShowCollateralList == null) initProperties();
	if (fieldShowCollateralList.equalsIgnoreCase("TRUE"))
		return true;
	else
		return false;
}
public static boolean getShowDeductionList() {
	if (fieldShowDeductionList == null) initProperties();
	if (fieldShowDeductionList.equalsIgnoreCase("TRUE"))
		return true;
	else
		return false;
}
public static String getSignOn() {
	if (fieldSignOn == null) initProperties();
	return (fieldSignOn);
}
public static int getSocketTimeOut() {
	if (fieldSocketTimeOut == null) initProperties();
	return Integer.parseInt(fieldSocketTimeOut);
}
public static String getUserid() {
	if (fieldUserid == null) initProperties();
	return fieldUserid;
}
public static boolean getVoiceActive() {
	if (fieldVoiceActive == null) initProperties();
	if (fieldVoiceActive.equalsIgnoreCase("TRUE"))
		return true;
	else
		return false;
}
public static String getWebAppPath() {
	if (fieldWebAppPath == null) initProperties();
	return fieldWebAppPath;
}
// Lofo File (Added by HG 11/02/2006)
public static String getLogoPathName() {
	if (fieldLogoPathName == null) initProperties();
	return fieldLogoPathName;
}
public static int getMaxIterations() {
	if (fieldMaxIterations == null) initProperties();
	return Integer.parseInt(fieldMaxIterations);
}


public static String getCheckReaderServer() {
	if (fieldCrtServer == null) initProperties();
	return fieldCrtServer;	
}

public static String getCheckReaderDatabase() {
	if (fieldCrtDatabase == null) initProperties();
	return fieldCrtDatabase;	
}

public static String getCheckReaderWinAuth() {
	if (fieldCrtWinAuth == null) initProperties();
	return fieldCrtWinAuth;	
}

public static String getCheckReaderUser() {
	if (fieldCrtUser == null) initProperties();
	return fieldCrtUser;	
}

public static String getCheckReaderPassword() {
	if (fieldCrtPassword == null) initProperties();
	return fieldCrtPassword;	
}

public static String getCheckReaderWorkDir() {
	if (fieldCrtWorkDir == null) initProperties();
	return fieldCrtWorkDir;	
}

public static String getCheckReaderScanner() {
	if (fieldCrtScanner == null) initProperties();
	return fieldCrtScanner;	
}

public static String getCheckReaderMicr() {
	if (fieldCrtMicr == null) initProperties();
	return fieldCrtMicr;	
}

/**
 * This method was created in VisualAge.
 */
public static void initProperties() throws MissingResourceException {
	PropertyResourceBundle eIBSProperties = null;
	try {
		eIBSProperties = (PropertyResourceBundle)PropertyResourceBundle.getBundle(propertyFileName);
		try {
			fieldHostIP = eIBSProperties.getString("eIBS.HostIP");
		}
		catch (Exception e) {
			fieldHostIP = "134.177.251.98";
		}
		try {
			fieldIniSocket = eIBSProperties.getString("eIBS.IniSocket");
		}
		catch (Exception e) {
			fieldIniSocket = "60000";
		}
		try {
			fieldSocketTimeOut = eIBSProperties.getString("eIBS.SocketTimeOut");
		}
		catch (Exception e) {
			fieldSocketTimeOut = "15000";
		}
		try {
			fieldWebAppPath = eIBSProperties.getString("eIBS.WebAppPath");
		}
		catch (Exception e) {
			fieldWebAppPath = "/eIBS_R04M07";
		}
		try {
			fieldRootPath = eIBSProperties.getString("eIBS.rootPath");
		}
		catch (Exception e) {
			fieldRootPath = "/pages/";
		}
		try {
			fieldSendRedirectCtx = eIBSProperties.getString("eIBS.sendRedirectCtx");
		}
		catch (Exception e) {
			fieldSendRedirectCtx = "";
		}
		try {
			fieldBgPage = eIBSProperties.getString("eIBS.bgPage");
		}
		catch (Exception e) {
			fieldBgPage = "/pages/background.jsp";
		}
		try {
			fieldBusyPage = eIBSProperties.getString("eIBS.busyPage");
		}
		catch (Exception e) {
			fieldBusyPage = "MISC_busy.jsp";
		}
		try {
			fieldDevPage = eIBSProperties.getString("eIBS.devPage");
		}
		catch (Exception e) {
			fieldDevPage = "MISC_under_construction.jsp";
		}
		try {
			fieldSckNotOpenPage = eIBSProperties.getString("eIBS.sckNotOpenPage");
		}
		catch (Exception e) {
			fieldSckNotOpenPage = "MISC_socket_not_open.jsp";
		}
		try {
			fieldSckNotRespondPage = eIBSProperties.getString("eIBS.sckNotRespondPage");
		}
		catch (Exception e) {
			fieldSckNotRespondPage = "MISC_socket_not_responding.jsp";
		}
		try {
			fieldVoiceActive = eIBSProperties.getString("eIBS.VoiceActive");
		}
		catch (Exception e) {
			fieldVoiceActive = "false";
		}
		try {
			fieldMaxIterations = eIBSProperties.getString("eIBS.MaxIterations");
		}
		catch (Exception e) {
			fieldMaxIterations = "2000";
		}
		try {
			fieldFormActive = eIBSProperties.getString("eIBS.FormActive");
		}
		catch (Exception e) {
			fieldFormActive = "false";
		}
		try {
			fieldScanActive = eIBSProperties.getString("eIBS.ScanActive");
		}
		catch (Exception e) {
			fieldScanActive = "false";
		}
		try {
			fieldImageRemote = eIBSProperties.getString("eIBS.ImageRemote");
		}
		catch (Exception e) {
			fieldImageRemote = "false";
		}
		try {
			fieldChkMsgPeriod = eIBSProperties.getString("eIBS.ChkMsgPeriod");
		}
		catch (Exception e) {
			fieldChkMsgPeriod = "60000";
		}
		try {
			fieldLog = eIBSProperties.getString("eIBS.Log");
		}
		catch (Exception e) {
			fieldLog = "0";
		}
		try {
			fieldFtpPathFormConfig = eIBSProperties.getString("eIBS.ftpPath.eIBSForm.config");
		}
		catch (Exception e) {
			fieldFtpPathFormConfig = "/eibsform/config/";
		}
		try {
			fieldFtpPathFormData = eIBSProperties.getString("eIBS.ftpPath.eIBSForm.data");
		}
		catch (Exception e) {
			fieldFtpPathFormData = "/eibsform/data/";
		}
		try {
			fieldFtpPathScanData = eIBSProperties.getString("eIBS.ftpPath.eIBSScan.data");
		}
		catch (Exception e) {
			fieldFtpPathScanData = "/eibsscan/data/";
		}
		try {
			fieldOwner = eIBSProperties.getString("eIBS.dbOwner");
		}
		catch (Exception e) {
			fieldOwner = "";
		}
		try {
			fieldUserid = eIBSProperties.getString("eIBS.dbUserid");
		}
		catch (Exception e) {
			fieldUserid = "";
		}
		try {
			fieldPassword = eIBSProperties.getString("eIBS.dbPassword");
		}
		catch (Exception e) {
			fieldPassword = "";
		}
		try {
			fieldDbURL = eIBSProperties.getString("eIBS.dbURL");
		}
		catch (Exception e) {
			fieldDbURL = "";
		}
		try {
			fieldDriver = eIBSProperties.getString("eIBS.dbDriver");
		}
		catch (Exception e) {
			fieldDriver = "";
		}
		try {
			fieldImgExt = eIBSProperties.getString("eIBS.imgExt");
		}
		catch (Exception e) {
			fieldImgExt = ".gif";
		}
		try {
			fieldSignOn = eIBSProperties.getString("eIBS.SignOn");
		}
		catch (Exception e) {
			fieldSignOn = "Sign On Again!!!";
		}
		try {
			fieldPopUpTimeOut = eIBSProperties.getString("eIBS.PopUpTimeOut");
		}
		catch (Exception e) {
			fieldPopUpTimeOut = "60000";
		}
		try {
			fieldShowCollateralList = eIBSProperties.getString("eIBS.showCollateralList");
		}
		catch (Exception e) {
			fieldShowCollateralList = "false";
		}
		try {
			fieldShowDeductionList = eIBSProperties.getString("eIBS.showDeductionList");
		}
		catch (Exception e) {
			fieldShowDeductionList = "false";
		}
		try {
			fieldRelease = eIBSProperties.getString("eIBS.release");
		}
		catch (Exception e) {
			fieldRelease = "47";
		}
		try {
			fieldScanURL = eIBSProperties.getString("eIBS.eIBSScanURL");
		}
		catch (Exception e) {
			fieldScanURL = "";
		}
		try {
			fieldScanDataURL = eIBSProperties.getString("eIBS.eIBSScanDataURL");
		}
		catch (Exception e) {
			fieldScanDataURL = "http://134.177.251.40/eIBS_R04M07/ftp/eibsimage/data/";
		}
		try {
			fieldImgToIFS = eIBSProperties.getString("eIBS.imgToIFS");
		}
		catch (Exception e) {
			fieldImgToIFS = "false";
		}
		try {
			fieldImgTempPath = eIBSProperties.getString("eIBS.imgTempPath");
		}
		catch (Exception e) {
			fieldImgTempPath = "/QIBM/UserData/WebASAdv/default/hosts/default_host/eIBS_R04M03/web/ftp/eibsimage/data/";
		}
		try {
			fieldDbSchema = eIBSProperties.getString("eIBS.dbSchema");
		}
		catch (Exception e) {
			fieldDbSchema = "";
		}
		try {
			fieldDefaultDataScheme = eIBSProperties.getString("eIBS.DefaultDataScheme");
		}
		catch (Exception e) {
			fieldDefaultDataScheme = "512";
		}
		try {
			fieldMinimumTextSize = eIBSProperties.getString("eIBS.MinimumTextSize");
		}
		catch (Exception e) {
			fieldMinimumTextSize = "8";
		}
		try {
			fieldIBSSchema = eIBSProperties.getString("eIBS.ibsSchema");
		}
		catch (Exception e) {
			fieldIBSSchema ="";
		}
		try {
			fieldEODPDFPath = eIBSProperties.getString("eIBS.EODPDFPath");
		}
		catch (Exception e) {
			fieldEODPDFPath ="";
		}
		try {
			fieldDataSource = eIBSProperties.getString("eIBS.DataSource");
		}
		catch (Exception e) {
			fieldDataSource ="";
		}
		try {
			fieldEODDirectUrl = eIBSProperties.getString("eIBS.EODDirectUrl");
		}
		catch (Exception e) {
			fieldEODDirectUrl ="false";
		}
		try {
			fieldAutoNumCD = eIBSProperties.getString("eIBS.AutoNumCD");
		}
		catch (Exception e) {
			fieldAutoNumCD = "false";
		}
		try {
			fieldAutoNumRT = eIBSProperties.getString("eIBS.AutoNumRT");
		}
		catch (Exception e) {
			fieldAutoNumRT = "false";
		}
		try {
			fieldAutoNumSV = eIBSProperties.getString("eIBS.AutoNumSV");
		}
		catch (Exception e) {
			fieldAutoNumSV = "false";
		}
		try {
			fieldAutoNumLN = eIBSProperties.getString("eIBS.AutoNumLN");
		}
		catch (Exception e) {
			fieldAutoNumLN = "false";
		}
		try {
			fieldAutoNumCOLL = eIBSProperties.getString("eIBS.AutoNumCOLL");
		}
		catch (Exception e) {
			fieldAutoNumCOLL = "false";
		}
		try {
			fieldAutoNumOCK = eIBSProperties.getString("eIBS.AutoNumOCK");
		}
		catch (Exception e) {
			fieldAutoNumOCK = "false";
		}
		try {
			fieldProtectedBNKBRN = eIBSProperties.getString("eIBS.ProtectedBNKBRN");
		}
		catch (Exception e) {
			fieldProtectedBNKBRN = "false";
		}
		try {
			fieldEODPDFURL = eIBSProperties.getString("eIBS.EODPDFURL");
		}
		catch (Exception e) {
			fieldEODPDFURL ="";
		}
		try {
			fieldEODDataSource = eIBSProperties.getString("eIBS.EODDataSource");
		}
		catch (Exception e) {
			fieldEODDataSource ="false";
		}		
		try {
			fieldPYURL = eIBSProperties.getString("eIBS.PYURL");
		}
		catch (Exception e) {
			fieldPYURL ="";
		}
		try {
			fieldFORMPDFURL = eIBSProperties.getString("eIBS.FORMPDFURL");
		}
		catch (Exception e) {
			fieldFORMPDFURL ="";
		}
		try {
			fieldSessionPluginClass = eIBSProperties.getString("eIBS.SessionPluginClass");
		}
		catch (Exception e) {
			fieldSessionPluginClass = "";
		}
		//Interface type
		try {
			fieldICheck = eIBSProperties.getString("eIBS.ICheck");
		}
		catch (Exception e) {
			fieldICheck = "1";
		}
		//Check Image Interface URL 
		try {
			fieldICheckURL = eIBSProperties.getString("eIBS.ICheckURL");
		}
		catch (Exception e) {
			fieldICheckURL = "http://icheck.erasjv.com/ibs/imagereq.exe?";
		}
		//Bank ID
		try {
			fieldICheckBankID = eIBSProperties.getString("eIBS.ICheckBankID");
		}
		catch (Exception e) {
			fieldICheckBankID = "";
		}
		//Logo File (Added by HG 11/02/2006)
		try {
			fieldLogoPathName = eIBSProperties.getString("eIBS.LogoPathName");
		}
		catch (Exception e) {
			fieldLogoPathName = "";			
		}	
	
		//Customer Format
		try {
			fieldCustomerInfoInputFormat = eIBSProperties.getString("customer.info.input.format");
		}
		catch (Exception e) {
			fieldCustomerInfoInputFormat = "short";
		}
		
		// eIBS Forms
		try {
			fieldFtpFormHost = eIBSProperties.getString("eIBS.form.ftpHost");
		}
		catch (Exception e) {
			fieldFtpFormHost = "";
		}
		try {
			fieldFtpFormPort = eIBSProperties.getString("eIBS.form.ftpPort");
			if (fieldFtpFormPort.equals("")) fieldFtpFormPort = "-1";
		}
		catch (Exception e) {
			fieldFtpFormPort = "-1";
		}
		try {
			fieldFtpFormUserID = eIBSProperties.getString("eIBS.form.ftpUserID");
		}
		catch (Exception e) {
			fieldFtpFormUserID = "";
		}
		try {
			fieldFtpFormPassword = eIBSProperties.getString("eIBS.form.ftpPassword");
		}
		catch (Exception e) {
			fieldFtpFormPassword = "";
		}
		try {
			fieldFtpFormFirewallType = eIBSProperties.getString("eIBS.form.ftpFirewallType");
		}
		catch (Exception e) {
			fieldFtpFormFirewallType = "";
		}
		try {
			fieldFtpFormFWAuthenticate = eIBSProperties.getString("eIBS.form.ftpFWAuthenticate");
		}
		catch (Exception e) {
			fieldFtpFormFWAuthenticate = "";
		}
		try {
			fieldFtpFormFWUserID = eIBSProperties.getString("eIBS.form.ftp.FWUserID");
		}
		catch (Exception e) {
			fieldFtpFormFWUserID = "";
		}
		try {
			fieldFtpFormFWPassword = eIBSProperties.getString("eIBS.form.ftpFWPassword");
		}
		catch (Exception e) {
			fieldFtpFormFWPassword = "";
		}
		try {
			fieldBankerPDFURL = eIBSProperties.getString("eIBS.form.BankerPDFURL");
		}
		catch (Exception e) {
			fieldBankerPDFURL = "";
		}	
		try {
			fieldBankerPDFURL1 = eIBSProperties.getString("eIBS.form.BankerPDFURL1");
		}
		catch (Exception e) {
			fieldBankerPDFURL1 = "";
		}		
			
		// eIBS Images
		try {
			fieldFtpImgHost = eIBSProperties.getString("eIBS.img.ftpHost");
		}
		catch (Exception e) {
			fieldFtpImgHost = "";
		}
		try {
			fieldFtpImgPort = eIBSProperties.getString("eIBS.img.ftpPort");
			if (fieldFtpImgPort.equals("")) fieldFtpImgPort = "-1";
		}
		catch (Exception e) {
			fieldFtpImgPort = "-1";
		}
		try {
			fieldFtpImgUserID = eIBSProperties.getString("eIBS.img.ftpUserID");
		}
		catch (Exception e) {
			fieldFtpImgUserID = "";
		}
		try {
			fieldFtpImgPassword = eIBSProperties.getString("eIBS.img.ftpPassword");
		}
		catch (Exception e) {
			fieldFtpImgPassword = "";
		}
		try {
			fieldFtpImgPassive = eIBSProperties.getString("eIBS.img.ftpPassive");
		}
		catch (Exception e) {
			fieldFtpImgPassive = "";
		}
		try {
			fieldFtpImgFirewallType = eIBSProperties.getString("eIBS.img.ftpFirewallType");
		}
		catch (Exception e) {
			fieldFtpImgFirewallType = "";
		}
		try {
			fieldFtpImgFWAuthenticate = eIBSProperties.getString("eIBS.img.ftpFWAuthenticate");
		}
		catch (Exception e) {
			fieldFtpImgFWAuthenticate = "";
		}
		try {
			fieldFtpImgFWUserID = eIBSProperties.getString("eIBS.img.ftp.FWUserID");
		}
		catch (Exception e) {
			fieldFtpImgFWUserID = "";
		}
		try {
			fieldFtpImgFWPassword = eIBSProperties.getString("eIBS.img.ftpFWPassword");
		}
		catch (Exception e) {
			fieldFtpImgFWPassword = "";
		}
		
		
		try {
			fieldImgExportPath = eIBSProperties.getString("eIBS.image.ex.export.path");
		} catch (Exception e) {
			fieldImgExportPath = "" ;
		}
		
		try {
			fieldImgImportPath = eIBSProperties.getString("eIBS.image.ex.import.path");
		} catch (Exception e) {
			fieldImgImportPath = "" ;
		}
		
		// Check Reader Tool Properties
		try {
			fieldCrtServer = eIBSProperties.getString("eIBS.crt.Server");
		} catch (Exception e) {
			fieldCrtServer = "localhost" ;
		}
		
		try {
			fieldCrtDatabase = eIBSProperties.getString("eIBS.crt.Database");
		} catch (Exception e) {
			fieldCrtDatabase = "EIBSIMG" ;
		}
		
		try {
			fieldCrtWinAuth = eIBSProperties.getString("eIBS.crt.WinAuth");
		} catch (Exception e) {
			fieldCrtServer = "Y" ;
		}
		
		try {
			fieldCrtUser = eIBSProperties.getString("eIBS.crt.User");
		} catch (Exception e) {
			fieldCrtServer = "" ;
		}

		try {
			fieldCrtPassword = eIBSProperties.getString("eIBS.crt.Password");
		} catch (Exception e) {
			fieldCrtPassword = "" ;
		}
		
		try {
			fieldCrtWorkDir = eIBSProperties.getString("eIBS.crt.WorkDir");
		} catch (Exception e) {
			fieldCrtWorkDir = "" ;
		}
		
		try {
			fieldCrtScanner = eIBSProperties.getString("eIBS.crt.Scanner");
		} catch (Exception e) {
			fieldCrtScanner = "150" ;
		}
		
		try {
			fieldCrtMicr = eIBSProperties.getString("eIBS.crt.Micr");
		} catch (Exception e) {
			fieldCrtMicr = "0" ;
		}
		
	}
	catch (MissingResourceException e) {
		System.out.println("Failed to load Properties file.   Be sure " + propertyFileName +
			" is located correctly.");
		throw e;
	}
}
public static void setChkMsgPeriod(String chkMsgPeriod) {
	fieldChkMsgPeriod = chkMsgPeriod;
}
public static void setDbURL(String dbURL) {
	fieldDbURL = dbURL;
}
public static void setDevPage(String devPage) {
	fieldDevPage = devPage;
}
public static void setDriver(String driver) {
	fieldDriver = driver;
}
public static void setFtpFormFirewallType(String firewallType) {
	fieldFtpFormFirewallType = firewallType;
}
public static void setFtpImgFirewallType(String firewallType) {
	fieldFtpImgFirewallType = firewallType;
}
public static void setFormActive(String formActive) {
	fieldFormActive = formActive;
}
public static void setFtpImgHost(String ftpHost) {
	fieldFtpImgHost = ftpHost;
}
public static void setFtpformHost(String ftpHost) {
	fieldFtpFormHost = ftpHost;
}
public static void setFtpFormPassword(String ftpPassword) {
	fieldFtpFormPassword = ftpPassword;
}
public static void setFtpImgPassword(String ftpPassword) {
	fieldFtpImgPassword = ftpPassword;
}
public static void setFtpImgPassive(String ftpPassive) {
	fieldFtpImgPassive = ftpPassive;
}
public static void setFtpPathFormConfig(String ftpPathFormConfig) {
	fieldFtpPathFormConfig = ftpPathFormConfig;
}
public static void setFtpPathFormData(String ftpPathFormData) {
	fieldFtpPathFormData = ftpPathFormData;
}
public static void setFtpPathScanData(String ftpPathScanData) {
	fieldFtpPathScanData = ftpPathScanData;
}
public static void setFtpFormUserID(String ftpUserID) {
	fieldFtpFormUserID = ftpUserID;
}
public static void setFtpImgUserID(String ftpUserID) {
	fieldFtpImgUserID = ftpUserID;
}
public static void setFtpFormFWAuthenticate(String fwAuthenticate) {
	fieldFtpFormFWAuthenticate = fwAuthenticate;
}
public static void setFtpImgFWAuthenticate(String fwAuthenticate) {
	fieldFtpImgFWAuthenticate = fwAuthenticate;
}
public static void setFtpFormFWPassword(String fwPassword) {
	fieldFtpFormFWPassword = fwPassword;
}
public static void setFtpImgFWPassword(String fwPassword) {
	fieldFtpImgFWPassword = fwPassword;
}
public static void setFtpFormFWUserID(String fwUserID) {
	fieldFtpFormFWUserID = fwUserID;
}
public static void setFtpImgFWUserID(String fwUserID) {
	fieldFtpImgFWUserID = fwUserID;
}
public static void setHostIP(String hostIP) {
	fieldHostIP = hostIP;
}
public static void setImgExt(String imgExt) {
	fieldImgExt = imgExt;
}
public static void setIniSocket(String iniSocket) {
	fieldIniSocket = iniSocket;
}
public static void setLog(String log) {
	fieldLog = log;
}
public static void setOwner(String owner) {
	fieldOwner = owner;
}
public static void setPassword(String password) {
	fieldPassword = password;
}
public static void setPopUpTimeOut(String popUpTimeOut) {
	fieldPopUpTimeOut = popUpTimeOut;
}
public static void setRootPath(String rootPath) {
	fieldRootPath = rootPath;
}
public static void setSendRedirectCtx(String sendRedirectCtx) {
	fieldSendRedirectCtx = sendRedirectCtx;
}
public static void setScanActive(String scanActive) {
	fieldScanActive = scanActive;
}
public static void setSckNotOpenPage(String sckNotOpenPage) {
	fieldSckNotOpenPage = sckNotOpenPage;
}
public static void setSckNotRespondPage(String sckNotRespondPage) {
	fieldSckNotRespondPage = sckNotRespondPage;
}
public static void setShowCollateralList(String showCollateralList) {
	fieldShowCollateralList = showCollateralList;
}
public static void setShowDeductionList(String showDeductionList) {
	fieldShowDeductionList = showDeductionList;
}
public static void setSignOn(String signOn) {
	fieldSignOn = signOn;
}
public static void setSocketTimeOut(String socketTimeOut) {
	fieldSocketTimeOut = socketTimeOut;
}
public static void setUserid(String userid) {
	fieldUserid = userid;
}
public static void setVoiceActive(String voiceActive) {
	fieldVoiceActive = voiceActive;
}
public static void setWebAppPath(String webAppPath) {
	fieldWebAppPath = webAppPath;
}
public static String getDbSchema() {
	if (fieldDbSchema == null) initProperties();
	return fieldDbSchema;
}
public static String getDefaultDataScheme() {
	if (fieldDefaultDataScheme == null) initProperties();
	return fieldDefaultDataScheme;
}
public static String getImgTempPath() {
	if (fieldImgTempPath == null) initProperties();
	return fieldImgTempPath;
}
public static boolean getImgToIFS() {
	if (fieldImgToIFS == null) initProperties();
	if (fieldImgToIFS.equalsIgnoreCase("TRUE"))
		return true;
	else
		return false;
}
public static String getMinimumTextSize() {
	if (fieldMinimumTextSize == null) initProperties();
	return fieldMinimumTextSize;
}
public static String getRelease() {
	if (fieldRelease == null) initProperties();
	return fieldRelease;
}
public static String getScanURL() {
	if (fieldScanURL == null) initProperties();
	return fieldScanURL;
}
public static String getScanDataURL() {
	if (fieldScanDataURL == null) initProperties();
	return fieldScanDataURL;
}
public static void setDbSchema(String dbSchema) {
	fieldDbSchema = dbSchema;
}
public static void setDefaultDataScheme(String defaultDataScheme) {
	fieldDefaultDataScheme = defaultDataScheme;
}
public static void setImgTempPath(String imgTempPath) {
	fieldImgTempPath = imgTempPath;
}
public static void setImgToIFS(String imgToIFS) {
	fieldImgToIFS = imgToIFS;
}
public static void setMinimumTextSize(String minimumTextSize) {
	fieldMinimumTextSize = minimumTextSize;
}
public static void setRelease(String realease) {
	fieldRelease = realease;
}
public static void setScanDataURL(String scanDataURL) {
	fieldScanDataURL = scanDataURL;
}
public static String getIBSSchema() {
	if (fieldIBSSchema == null) initProperties();
	return fieldIBSSchema;
}
public static void setIBSSchema(String IBSSchema) {
	fieldIBSSchema = IBSSchema;
}
/**
 * gets the EODPDFPath property (java.lang.String) value.
 * @param 
 * @see #getEODPDFPath
 */
public static String getEODPDFPath() {
	if (fieldEODPDFPath == null) initProperties();
	return fieldEODPDFPath;
}
/**
 * Sets the EODPDFPath property (java.lang.String) value.
 * @param EODPDFPath The new value for the property.
 * @see #getEODPDFPath
 */
public static void setEODPDFPath(String EODPDFPath) {
	fieldEODPDFPath = EODPDFPath;
}
/**
 * gets the DataSource property (java.lang.String) value.
 * @param 
 * @see #setDataSource
 */
public static String getDataSource() {
	if (fieldDataSource == null) initProperties();
	return fieldDataSource;
}
/**
 * Sets the DataSource property (java.lang.String) value.
 * @param DataSource The new value for the property.
 * @see #getDataSource
 */
public static void setDataSource(String pDataSource) {
	fieldDataSource = pDataSource;
}
/**
 * gets the EODPDFURL property (java.lang.String) value.
 * @param 
 * @see #setEODPDFURL
 */
public static String getEODPDFURL() {
	if (fieldEODPDFURL == null) initProperties();
	return fieldEODPDFURL;
}

public static void setEODPDFURL(String EODPDFURL) {
	fieldEODPDFURL = EODPDFURL;
}


public static boolean getEODDataSource() {
	if (fieldEODDataSource == null) initProperties();
	if (fieldEODDataSource.equalsIgnoreCase("TRUE"))
		return true;
	else
		return false;
}

public static boolean getEODDirectUrl() {
	if (fieldEODDirectUrl == null) initProperties();
	if (fieldEODDirectUrl.equalsIgnoreCase("TRUE"))
		return true;
	else
		return false;
}

public static boolean getAutoNumCD() {
	if (fieldAutoNumCD == null) initProperties();
	if (fieldAutoNumCD.equalsIgnoreCase("TRUE"))
		return true;
	else
		return false;
}

public static boolean getAutoNumLN() {
	if (fieldAutoNumLN == null) initProperties();
	if (fieldAutoNumLN.equalsIgnoreCase("TRUE"))
		return true;
	else
		return false;
}

public static boolean getAutoNumRT() {
	if (fieldAutoNumRT == null) initProperties();
	if (fieldAutoNumRT.equalsIgnoreCase("TRUE"))
		return true;
	else
		return false;
}

public static boolean getAutoNumSV() {
	if (fieldAutoNumSV == null) initProperties();
	if (fieldAutoNumSV.equalsIgnoreCase("TRUE"))
		return true;
	else
		return false;
}

public static boolean getAutoNumCOLL() {
	if (fieldAutoNumCOLL == null) initProperties();
	if (fieldAutoNumCOLL.equalsIgnoreCase("TRUE"))
		return true;
	else
		return false;
}

public static boolean getAutoNumOCK() {
	if (fieldAutoNumOCK == null) initProperties();
	if (fieldAutoNumOCK.equalsIgnoreCase("TRUE"))
		return true;
	else
		return false;
}

public static boolean getProtectedBNKBRN() {
	if (fieldProtectedBNKBRN == null) initProperties();
	if (fieldProtectedBNKBRN.equalsIgnoreCase("TRUE"))
		return true;
	else
		return false;
}

public static void setAutoNumCD(String autoNumCD) {
	fieldAutoNumCD = autoNumCD;
}

public static void setAutoNumLN(String autoNumLN) {
	fieldAutoNumLN = autoNumLN;
}

public static void setAutoNumRT(String autoNumRT) {
	fieldAutoNumRT = autoNumRT;
}

public static void setAutoNumSV(String autoNumSV) {
	fieldAutoNumSV = autoNumSV;
}

public static void setAutoNumCOLL(String autoNumCOLL) {
	fieldAutoNumCOLL = autoNumCOLL;
}

public static void setAutoNumOCK(String autoNumOCK) {
	fieldAutoNumOCK = autoNumOCK;
}

public static void setProtectedBNKBRN(String ProtectedBNKBRN) {
	fieldProtectedBNKBRN = ProtectedBNKBRN;
}

public static String getPYURL() {
	if (fieldPYURL == null) initProperties();
	return fieldPYURL;
}

public static void setPYURL(String PYURL) {
	fieldPYURL = PYURL;
}

public static int getICheck() {
	if (fieldICheck == null) initProperties();
	return Integer.parseInt(fieldICheck);
}

public static void setICheck(String newICheck) {
	fieldICheck = newICheck;
}

public static String getICheckURL() {
	if (fieldICheckURL == null) initProperties();
	return fieldICheckURL;
}

public static void setICheckURL(String newICheckURL) {
	fieldICheckURL = newICheckURL;
}

public static String getICheckBankID() {
	if (fieldICheckBankID == null) initProperties();
	return fieldICheckBankID;
}

public static void setICheckBankID(String newICheckBankID) {
	fieldICheckBankID = newICheckBankID;
}
//----------- Logo File (Added by HG 11/02/2006) -------------
public static void setLogoPathName(String LogoPathName) {
	fieldLogoPathName = LogoPathName;
}
//------------------------------------------------------------

public static String getBankerPDFURL() {
	if (fieldBankerPDFURL == null) initProperties();
	return fieldBankerPDFURL;
}

public static void setBankerPDFURL(String newBankerPDFURL) {
	fieldBankerPDFURL = newBankerPDFURL;
}

public static String getBankerPDFURL1() {
	if (fieldBankerPDFURL1 == null) initProperties();
	return fieldBankerPDFURL1;
}

public static void setBankerPDFURL1(String newBankerPDFURL1) {
	fieldBankerPDFURL1 = newBankerPDFURL1;
}
/**
 * gets the FORMPDFURL property (java.lang.String) value.
 * @param 
 * @see #setFORMPDFURL
 */
public static String getFORMPDFURL() {
	if (fieldFORMPDFURL == null) initProperties();
	return fieldFORMPDFURL;
}
/**
 * Sets the FORMPDFURL property (java.lang.String) value.
 * @param FORMPDFURL The new value for the property.
 * @see #getFORMPDFURL
 */
public static void setFORMPDFURL(String FORMPDFURL) {
	fieldFORMPDFURL = FORMPDFURL;
}
public static String getSessionPluginClass() {
	if (fieldSessionPluginClass == null) initProperties();
	return fieldSessionPluginClass;
}
public static void setSessionPluginClass(String sessionPluginClass) {
	fieldSessionPluginClass = sessionPluginClass;
}

	/**
	 * @return
	 */
	public static String getCustomerInfoInputFormat() {
		return fieldCustomerInfoInputFormat;
	}

	/**
	 * @param string
	 */
	public static void setCustomerInfoInputFormat(String string) {
		fieldCustomerInfoInputFormat = string;
	}



	// FIELDS FOR IMAGE EXCHANGE - AZERTIA - BANCO TESORO
	public static String getImageExportPath() {
		return fieldImgExportPath ;
	}

	public static void setImageExportPath(String exportPath) {
		fieldImgExportPath = exportPath;
	}

	public static String getImageImportPath() {
		return fieldImgImportPath ;
	}

	public static void setImageImportPath(String importPath) {
		fieldImgImportPath = importPath;
	}
	
	//Check Reader Tool Properties
	public static void setCheckReaderDatabase(String fieldCrtDatabase) {
		JSEIBSProp.fieldCrtDatabase = fieldCrtDatabase;
	}
	public static void setCheckReaderPassword(String fieldCrtPassword) {
		JSEIBSProp.fieldCrtPassword = fieldCrtPassword;
	}
	public static void setCheckReaderScanner(String fieldCrtScanner) {
		JSEIBSProp.fieldCrtScanner = fieldCrtScanner;
	}
	public static void setCheckReaderServer(String fieldCrtServer) {
		JSEIBSProp.fieldCrtServer = fieldCrtServer;
	}
	public static void setCheckReaderUser(String fieldCrtUser) {
		JSEIBSProp.fieldCrtUser = fieldCrtUser;
	}
	public static void setCheckReaderWinAuth(String fieldCrtWinAuth) {
		JSEIBSProp.fieldCrtWinAuth = fieldCrtWinAuth;
	}
	public static void setCheckReaderWorkDir(String fieldCrtWorkDir) {
		JSEIBSProp.fieldCrtWorkDir = fieldCrtWorkDir;
	}
	public static void setCheckReaderMicr(String fieldCrtMicr) {
		JSEIBSProp.fieldCrtMicr = fieldCrtMicr;
	}




}