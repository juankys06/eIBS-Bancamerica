package datapro.eibs.beans;

import java.io.*;
import java.net.*;
import java.beans.Beans;
import javax.servlet.*;
import javax.servlet.http.*;
import datapro.eibs.beans.*;

import datapro.eibs.master.Util;

import java.util.*;
import datapro.eibs.master.JSEIBSProp;
import java.sql.*;
import datapro.eibs.sockets.*;

/**
 * Insert the type's description here.
 * Creation date: (8/2/2001 5:18:52 PM)
 * @author: Enrique M. Almonte
 */
public class JBSQLError {
/**
 * JBSQLError constructor comment.
 */
 //cn Select conection
 private Connection cnSQLError ;
 private Statement stSQLError;
 private String msgNumber="";
 private boolean existsConnection=false;
 
 //Language : default E - English
 private String Lang="E";
 //
 Exception exceptionSQLError;
 
public JBSQLError() {
	init();
}
//
//initializes JDBC Connections
//
public void init()
{
	try {
		existsConnection = true;
		try {
		   Class.forName(JSEIBSProp.getDriver()).newInstance();
		} catch (Exception e) {
			//error
		}
		cnSQLError =	DriverManager.getConnection(
				JSEIBSProp.getDbURL(),
				JSEIBSProp.getUserid(),
				JSEIBSProp.getPassword());
	} catch (SQLException e){
		//error
		setExceptionSQLError(e);
		existsConnection = false;
	}

}
//
// Destroy of connections and statements
//
public void destroy()
{
	try {
	   stSQLError.close();
	   cnSQLError.close();
	} catch (SQLException e) {
		setExceptionSQLError(e);
		//error
	}
}
//
// true if the connection is active
//
public boolean getExistConnection()
{
	boolean existCn = false;
	try {
		existCn = (existsConnection && !(cnSQLError.isClosed()));
	} catch (SQLException e){
		existCn = false;
		setExceptionSQLError(e);
		//error
	}
	return existCn;
}
//
//Gets the message text from database
//
public String getMsgText(String msgNumber)
{
	String msgTxt = "";
	ResultSet rs = null;
	try {
	   stSQLError = null;
	   stSQLError = cnSQLError.createStatement();
	   rs = stSQLError.executeQuery("SELECT MSSTXT FROM "+ JSEIBSProp.getIBSSchema()+ ".MSSGS WHERE MSSLAN = \'" + Lang + "\' AND MSSNUM = "+ msgNumber);
	   rs.next();
	   msgTxt = rs.getString(1);
	   rs.close();
	} catch (SQLException e) {
		setExceptionSQLError(e);//error
	}
	return msgTxt;
}

//
// sets the language of the messages 
//
public boolean setLang(String Lang)
{
	if (Lang.trim().equals("")){
		this.Lang = "E";
		return false;
	} else {
		this.Lang = Lang;
		return true;
	}
}
//
// gets the language of the database
//
public String getLang()
{
	return Lang;
}

// Sets the exception as part of the bean

public void setExceptionSQLError(Exception e){
	exceptionSQLError =(Exception) e;
}

// gets the exception from the bean
public Exception getExceptionSQLError(){
	return exceptionSQLError;
}

}