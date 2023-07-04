/*
 * Created on Mar 27, 2009
 *
 * To change the template for this generated file go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
package datapro.eibs.beans;

import java.math.BigDecimal;

/**
 * @author erodriguez
 *
 * To change the template for this generated type comment go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
public class JBImage {

	String USERID = "";
	// Type
	String TYPE = "";
	// Number
	BigDecimal NUMBER = null;
	// Table Number
	String TNU = "";
	// Table sequence
	BigDecimal SEQ = null;
	// Sequence
	BigDecimal SEQNUM = null;
	// Page Number
	BigDecimal PAGE = null;
	// Doc Type
	String TYP = "";
	// Description
	String DES = "";
	//File Name
	String FILENAME = "";
	//Add
	String ADD = "";
	//Image Size
	String SIZE = "0";
	//Frecuence
	String FRE = "";
	//Pages
	String PAGES = "";
	
	
	/**
	 * @return
	 */
	public String getADD() {
		return ADD;
	}

	/**
	 * @return
	 */
	public String getDES() {
		return DES;
	}

	/**
	 * @return
	 */
	public String getFILENAME() {
		return FILENAME;
	}

	/**
	 * @return
	 */
	public String getFRE() {
		return FRE;
	}

	/**
	 * @return
	 */
	public BigDecimal getNUMBER() {
		return NUMBER;
	}

	/**
	 * @return
	 */
	public BigDecimal getPAGE() {
		return PAGE;
	}

	/**
	 * @return
	 */
	public String getPAGES() {
		return PAGES;
	}

	/**
	 * @return
	 */
	public BigDecimal getSEQ() {
		return SEQ;
	}

	/**
	 * @return
	 */
	public BigDecimal getSEQNUM() {
		return SEQNUM;
	}

	/**
	 * @return
	 */
	public String getSIZE() {
		return SIZE;
	}

	/**
	 * @return
	 */
	public String getTNU() {
		return TNU;
	}

	/**
	 * @return
	 */
	public String getTYP() {
		return TYP;
	}

	/**
	 * @return
	 */
	public String getTYPE() {
		return TYPE;
	}

	/**
	 * @return
	 */
	public String getUSERID() {
		return USERID;
	}

	/**
	 * @param string
	 */
	public void setADD(String string) {
		ADD = string;
	}

	/**
	 * @param string
	 */
	public void setDES(String string) {
		DES = string;
	}

	/**
	 * @param string
	 */
	public void setFILENAME(String string) {
		FILENAME = string;
	}

	/**
	 * @param string
	 */
	public void setFRE(String string) {
		FRE = string;
	}

	/**
	 * @param decimal
	 */
	public void setNUMBER(BigDecimal decimal) {
		NUMBER = decimal;
	}

	/**
	 * @param decimal
	 */
	public void setPAGE(BigDecimal decimal) {
		PAGE = decimal;
	}

	/**
	 * @param string
	 */
	public void setPAGES(String string) {
		PAGES = string;
	}

	/**
	 * @param decimal
	 */
	public void setSEQ(BigDecimal decimal) {
		SEQ = decimal;
	}

	/**
	 * @param decimal
	 */
	public void setSEQNUM(BigDecimal decimal) {
		SEQNUM = decimal;
	}

	/**
	 * @param integer
	 */
	public void setSIZE(String integer) {
		SIZE = integer;
	}

	/**
	 * @param string
	 */
	public void setTNU(String string) {
		TNU = string;
	}

	/**
	 * @param string
	 */
	public void setTYP(String string) {
		TYP = string;
	}

	/**
	 * @param string
	 */
	public void setTYPE(String string) {
		TYPE = string;
	}

	/**
	 * @param string
	 */
	public void setUSERID(String string) {
		USERID = string;
	}

}
