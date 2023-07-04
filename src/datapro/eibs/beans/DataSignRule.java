package datapro.eibs.beans;

import java.util.Calendar;

import datapro.eibs.master.Util;

/**
 * Insert the type's description here.
 * Creation date: (10/24/2001 11:32:05 AM)
 * @author: Ramaro
 */
public class DataSignRule {
	
	private java.lang.String AccUid;
	private java.lang.String SigRule;
	private java.lang.String Amount;
	private java.lang.String CCYCode;
	private java.lang.String DayFrom;
	private java.lang.String MonthFrom;
	private java.lang.String YearFrom;
	private java.lang.String YearTo;
	private java.lang.String MonthTo;
	private java.lang.String DayTo;
	private java.lang.String Status;
	private java.lang.String Branch;
	private java.lang.String DocNum;
	

/**
 * Transaction constructor comment.
 */
	public DataSignRule() {
		
		Calendar calendar = Calendar.getInstance();
					
		AccUid = "0";
		SigRule = "";
		Amount = "0";
		DayTo = "";
		MonthTo = "";
		YearTo = "";
		DayFrom = Util.justifyRight("" + calendar.get(Calendar.DAY_OF_MONTH),2);
		MonthFrom = Util.justifyRight("" + (calendar.get(Calendar.MONTH) + 1),2);
		YearFrom = Util.formatYear(calendar.get(Calendar.YEAR));
		Branch = "";
		DocNum = "";
		Status = "1";
		CCYCode = "";
		
	}


	/**
	 * @return
	 */
	public java.lang.String getAccUid() {
		return AccUid;
	}

	/**
	 * @return
	 */
	public java.lang.String getAmount() {
		return Amount;
	}

	/**
	 * @return
	 */
	public java.lang.String getSigRule() {
		return SigRule;
	}

	/**
	 * @param decimal
	 */
	public void setAccUid(java.lang.String string) {
		AccUid = string;
	}

	/**
	 * @param decimal
	 */
	public void setAmount(java.lang.String string) {
		Amount = string;
	}

	/**
	 * @param string
	 */
	public void setSigRule(java.lang.String string) {
		SigRule = string;
	}

	/**
	 * @return
	 */
	public java.lang.String getCCYCode() {
		return CCYCode;
	}

	/**
	 * @return
	 */
	public java.lang.String getDocNum() {
		return DocNum;
	}

	/**
	 * @return
	 */
	public java.lang.String getStatus() {
		return Status;
	}

	/**
	 * @param string
	 */
	public void setCCYCode(java.lang.String string) {
		CCYCode = string;
	}

	/**
	 * @param string
	 */
	public void setDocNum(java.lang.String string) {
		DocNum = string;
	}

	/**
	 * @param string
	 */
	public void setStatus(java.lang.String string) {
		Status = string;
	}

	/**
	 * @return
	 */
	public java.lang.String getDayTo() {
		return DayTo;
	}

	/**
	 * @return
	 */
	public java.lang.String getMonthFrom() {
		return MonthFrom;
	}

	/**
	 * @return
	 */
	public java.lang.String getMonthTo() {
		return MonthTo;
	}

	/**
	 * @return
	 */
	public java.lang.String getYearFrom() {
		return YearFrom;
	}

	/**
	 * @return
	 */
	public java.lang.String getYearTo() {
		return YearTo;
	}

	/**
	 * @param string
	 */
	public void setDayTo(java.lang.String string) {
		DayTo = string;
	}

	/**
	 * @param string
	 */
	public void setMonthFrom(java.lang.String string) {
		MonthFrom = string;
	}

	/**
	 * @param string
	 */
	public void setMonthTo(java.lang.String string) {
		MonthTo = string;
	}

	/**
	 * @param string
	 */
	public void setYearFrom(java.lang.String string) {
		YearFrom = string;
	}

	/**
	 * @param string
	 */
	public void setYearTo(java.lang.String string) {
		YearTo = string;
	}

	/**
	 * @return
	 */
	public java.lang.String getBranch() {
		return Branch;
	}

	/**
	 * @return
	 */
	public java.lang.String getDayFrom() {
		return DayFrom;
	}

	/**
	 * @param string
	 */
	public void setBranch(java.lang.String string) {
		Branch = string;
	}

	/**
	 * @param string
	 */
	public void setDayFrom(java.lang.String string) {
		DayFrom = string;
	}

}