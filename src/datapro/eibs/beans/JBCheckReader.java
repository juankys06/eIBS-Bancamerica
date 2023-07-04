package datapro.eibs.beans;

import javax.servlet.http.HttpServletRequest;

import datapro.eibs.master.JSEIBSProp;
import datapro.eibs.master.Util;

public class JBCheckReader {
	
	private String Server = "";
	private String Database = "";
	private String WinAuth = "";
	private String User = "";
	private String Password = "";
	private String WorkDir = "";
	private String Scanner = "";
	private String Micr = "";
	private String Process = "";
	private String URL = "";
	private String UserID = "";
	private String RunDate = "";
	
	public void init(ESS0030DSMessage user, HttpServletRequest req, String process) {
		Server = JSEIBSProp.getCheckReaderServer();
		Database = JSEIBSProp.getCheckReaderDatabase();
		WinAuth = JSEIBSProp.getCheckReaderWinAuth().equals("") ? "Y" : JSEIBSProp.getCheckReaderWinAuth(); 
		User = JSEIBSProp.getCheckReaderUser();
		Password = JSEIBSProp.getCheckReaderPassword();
		WorkDir = JSEIBSProp.getCheckReaderWorkDir();
		Scanner = JSEIBSProp.getCheckReaderScanner().equals("") ? "150" : JSEIBSProp.getCheckReaderScanner();
		URL = "http://" + req.getServerName() + ":" + Integer.toString(req.getServerPort()) + "/eIBS/servlet/datapro.eibs.tools.JSCheckReaderDone";
		UserID = user.getH01USR();
		RunDate = user.getE01RDY().trim() +	Util.addLeftChar('0', 2, user.getE01RDM().trim()) +	Util.addLeftChar('0', 2, user.getE01RDD().trim());
		Micr = JSEIBSProp.getCheckReaderMicr();
		Process = process;
	}
	
	public String getDatabase() {
		return Database;
	}
	public void setDatabase(String database) {
		Database = database;
	}
	public String getMicr() {
		return Micr;
	}
	public void setMicr(String micr) {
		Micr = micr;
	}
	public String getPassword() {
		return Password;
	}
	public void setPassword(String password) {
		Password = password;
	}
	public String getProcess() {
		return Process;
	}
	public void setProcess(String process) {
		Process = process;
	}
	public String getRunDate() {
		return RunDate;
	}
	public void setRunDate(String runDate) {
		RunDate = runDate;
	}
	public String getScanner() {
		return Scanner;
	}
	public void setScanner(String scanner) {
		Scanner = scanner;
	}
	public String getServer() {
		return Server;
	}
	public void setServer(String server) {
		Server = server;
	}
	public String getURL() {
		return URL;
	}
	public void setURL(String url) {
		URL = url;
	}
	public String getUser() {
		return User;
	}
	public void setUser(String user) {
		User = user;
	}
	public String getUserID() {
		return UserID;
	}
	public void setUserID(String userID) {
		UserID = userID;
	}
	public String getWinAuth() {
		return WinAuth;
	}
	public void setWinAuth(String winAuth) {
		WinAuth = winAuth;
	}
	public String getWorkDir() {
		return WorkDir;
	}
	public void setWorkDir(String workDir) {
		WorkDir = workDir;
	}

}
