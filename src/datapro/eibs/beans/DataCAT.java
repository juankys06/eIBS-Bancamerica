package datapro.eibs.beans;

/**
 * Insert the type's description here.
 * Creation date: (10/24/2001 11:32:05 AM)
 * @author: David Mavilla
 */
public class DataCAT {
	private java.lang.String User;
	private java.lang.String CusNum;
	private java.lang.String Date1;
	private java.lang.String Date2;
	private java.lang.String Date3;

/**
 * Transaction constructor comment.
 */
public DataCAT() {
	super();
	initStrCostCenter();
}
/**
 * Insert the method's description here.
 * Creation date: (3/26/2001 3:55:42 PM)
 * @return java.lang.String
 */
public java.lang.String getCusNum() {
	return CusNum;
}
/**
 * Insert the method's description here.
 * Creation date: (3/26/2001 3:55:42 PM)
 * @return java.lang.String
 */
public java.lang.String getDate1() {
	return Date1;
}
/**
 * Insert the method's description here.
 * Creation date: (3/26/2001 3:55:42 PM)
 * @return java.lang.String
 */
public java.lang.String getDate2() {
	return Date2;
}
/**
 * Insert the method's description here.
 * Creation date: (3/26/2001 3:55:42 PM)
 * @return java.lang.String
 */
public java.lang.String getDate3() {
	return Date3;
}
/**
 * Insert the method's description here.
 * Creation date: (3/26/2001 3:55:42 PM)
 * @return java.lang.String
 */
public java.lang.String getUser() {
	return User;
}
/**
 * Insert the method's description here.
 * Creation date: (3/26/2001 3:55:42 PM)
 * @param newAccNum java.lang.String
 */
public void setCusNum(java.lang.String newCusNum) {
	CusNum = newCusNum;
}
/**
 * Insert the method's description here.
 * Creation date: (3/26/2001 3:55:42 PM)
 * @param newAccNum java.lang.String
 */
public void setDate1(java.lang.String newDate1) {
	Date1 = newDate1;
}
/**
 * Insert the method's description here.
 * Creation date: (3/26/2001 3:55:42 PM)
 * @param newAccNum java.lang.String
 */
public void setDate2(java.lang.String newDate2) {
	Date2 = newDate2;
}
/**
 * Insert the method's description here.
 * Creation date: (3/26/2001 3:55:42 PM)
 * @param newAccNum java.lang.String
 */
public void setDate3(java.lang.String newDate3) {
	Date3 = newDate3;
}
/**
 * Insert the method's description here.
 * Creation date: (3/26/2001 3:55:42 PM)
 * @param newAccNum java.lang.String
 */
public void setUser(java.lang.String newUser) {
	User = newUser;
}

/**
 * Insert the method's description here.
 * Creation date: (1/9/2002 7:36:41 PM)
 * @param newStrCostCenter java.lang.StringBuffer
 */
public void addCostCenter(String newStrCostCenter) {
	strCostCenter.append(newStrCostCenter);
}

/**
 * Insert the method's description here.
 * Creation date: (1/9/2002 7:36:41 PM)
 * @return java.lang.StringBuffer
 */
public java.lang.StringBuffer getStrCostCenter() {
	return strCostCenter;
}

/**
 * Insert the method's description here.
 * Creation date: (1/9/2002 7:36:41 PM)
 * @param newStrCostCenter java.lang.StringBuffer
 */
public void initStrCostCenter() {
	strCostCenter = new StringBuffer("");
}

/**
 * Insert the method's description here.
 * Creation date: (1/9/2002 7:36:41 PM)
 * @param newStrCostCenter java.lang.StringBuffer
 */
public void setStrCostCenter(java.lang.StringBuffer newStrCostCenter) {
	strCostCenter = newStrCostCenter;
}

	private java.lang.StringBuffer strCostCenter;
}