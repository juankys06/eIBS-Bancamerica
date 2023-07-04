package datapro.eibs.beans;

/**
 * This type was created in VisualAge.
 */
import java.io.*;
import java.util.*;

public class ConfParam {

	private String webPath = "";
	private String IP = "";
	private int socket = 0;
	private String comUserID = "";
	private String comPassword = "";
	private String driver = "";
	private String url = "";
	private Properties prop = new Properties();
	
/**
 * ConfParam constructor comment.
 */
public ConfParam() {
	super();

	Properties comProps = new Properties();
	
	try {

		System.out.println("Using prop file in new location");
		InputStream is = getClass().getResourceAsStream("eIBSConf.properties");
		System.out.println("inputStream = " + is);
		BufferedInputStream bis = new BufferedInputStream(is);
		System.out.println("bufInputStream = " + bis);
		comProps.load(bis);
		System.out.println("logProps = " + comProps.toString());

		prop = comProps;
		// Web Publishing Directory		
		String myWebPath = comProps.getProperty("webpath");
		if (myWebPath != null) webPath = myWebPath;
		// IP Address		
		String myIP = comProps.getProperty("ip");
		if (myIP != null) IP = myIP;
		// Socket Port Number
		String mySocket = comProps.getProperty("socket");
		if (mySocket != null) socket = Integer.parseInt(mySocket);
		// User ID for connections to DB
		String myComUserID = comProps.getProperty("user");
		if (myComUserID != null) comUserID = myComUserID;
		// Password for connections to DB
		String myPassword = comProps.getProperty("password");
		if (myPassword != null) comPassword = myPassword;
		// Driver for connections to DB
		String myDriver = comProps.getProperty("driver");
		if (myDriver != null) driver = myDriver;
		// URL for connections to DB
		String myURL = comProps.getProperty("url");
		if (myURL != null) url = myURL;
		

	} catch (FileNotFoundException fnf) {
		// do nothing if log.properties not found
		System.out.println("eIBS.properties file not found in bean's directory.");			
	} catch (Exception e) {
		// do nothing on unknown exception
		System.out.println("eIBS.properties unknown Exception.");		 
		webPath = "T:/web/";
		IP = "134.177.251.98";
		socket = 60000;
		comUserID = "R47ORESTES";
		comPassword = "orestes";
		// driver = myDriver;
		// url = myURL;
	}	

}
/**
 * This method was created in VisualAge.
 * @return java.lang.String
 */
public String getComPassword() {
	return comPassword;
}
/**
 * This method was created in VisualAge.
 * @return java.lang.String
 */
public String getComUserID() {
	return comUserID;
}
/**
 * This method was created in VisualAge.
 * @return java.lang.String
 */
public String getDriver() {
	return driver;
}
/**
 * This method was created in VisualAge.
 * @return java.lang.String
 */
public String getIP() {
	return IP;
}
/**
 * This method was created in VisualAge.
 * @return java.util.Properties
 */
public Properties getProp() {
	return prop;
}
/**
 * This method was created in VisualAge.
 * @return int
 */
public int getSocket() {
	return socket;
}
/**
 * This method was created in VisualAge.
 * @return java.lang.String
 */
public String getUrl() {
	return url;
}
/**
 * This method was created in VisualAge.
 * @return java.lang.String
 */
public String getWebPath() {
	return webPath;
}
/**
 * This method was created in VisualAge.
 * @param newValue java.lang.String
 */
public void setComPassword(String newValue) {
	this.comPassword = newValue;
}
/**
 * This method was created in VisualAge.
 * @param newValue java.lang.String
 */
public void setComUserID(String newValue) {
	this.comUserID = newValue;
}
/**
 * This method was created in VisualAge.
 * @param newValue java.lang.String
 */
public void setDriver(String newValue) {
	this.driver = newValue;
}
/**
 * This method was created in VisualAge.
 * @param newValue java.lang.String
 */
public void setIP(String newValue) {
	this.IP = newValue;
}
/**
 * This method was created in VisualAge.
 * @param newValue java.util.Properties
 */
public void setProp(Properties newValue) {
	this.prop = newValue;
}
/**
 * This method was created in VisualAge.
 * @param newValue int
 */
public void setSocket(int newValue) {
	this.socket = newValue;
}
/**
 * This method was created in VisualAge.
 * @param newValue java.lang.String
 */
public void setUrl(String newValue) {
	this.url = newValue;
}
/**
 * This method was created in VisualAge.
 * @param newValue java.lang.String
 */
public void setWebPath(String newValue) {
	this.webPath = newValue;
}
}