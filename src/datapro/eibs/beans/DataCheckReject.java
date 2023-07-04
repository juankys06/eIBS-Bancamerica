package datapro.eibs.beans;

/**
 * Insert the type's description here.
 * Creation date: (3/26/2001 3:38:05 PM)
 * @author: Ramses Amaro
 */
import java.util.Vector;

public class DataCheckReject {
	public java.lang.String officer;
	public java.lang.String bank;
	public java.lang.String branch;
	public java.lang.String cause;
	public java.lang.String accNum;
	public java.lang.String chkNum;
	public java.lang.String option;
	public java.lang.String currency;
	public java.lang.String amount;
	public java.lang.String relation;
	
	public int index;
	public int row;

	private Vector next;
	private Vector previous;
/**
 * Transaction constructor comment.
 */
public DataCheckReject() {
	init();
}
public void addRow(String newNext, String newPrevious) {

	next.addElement(newNext);
	previous.addElement(newPrevious);
	row++;
	index= row - 1;

	//noResult = false;
}
/**
 * Insert the method's description here.
 * Creation date: (3/26/2001 3:55:42 PM)
 * @return java.lang.String
 */
public java.lang.String getAccNum() {
	return accNum;
}
/**
 * Insert the method's description here.
 * Creation date: (3/26/2001 3:55:42 PM)
 * @return java.lang.String
 */
public java.lang.String getAmount() {
	return amount;
}
/**
 * Insert the method's description here.
 * Creation date: (3/26/2001 3:52:45 PM)
 * @return java.lang.String
 */
public java.lang.String getBank() {
	return bank;
}

public java.lang.String getRelation() {
	return relation;
}
/**
 * Insert the method's description here.
 * Creation date: (3/26/2001 3:53:21 PM)
 * @return java.lang.String
 */
public java.lang.String getBranch() {
	return branch;
}
/**
 * Insert the method's description here.
 * Creation date: (3/26/2001 3:54:03 PM)
 * @return java.lang.String
 */
public java.lang.String getCause() {
	return cause;
}
/**
 * Insert the method's description here.
 * Creation date: (3/26/2001 3:56:29 PM)
 * @return java.lang.String
 */
public java.lang.String getChkNum() {
	return chkNum;
}
/**
 * Insert the method's description here.
 * Creation date: (9/17/2001 10:09:24 AM)
 * @return java.lang.String
 */
public String getCurrency() {
	return currency;
}
/**
 * Insert the method's description here.
 * Creation date: (3/26/2001 3:57:09 PM)
 * @return java.lang.String
 */
public int getIndex() {
	return index;
}
/**
 * Insert the method's description here.
 * Creation date: (3/26/2001 3:57:09 PM)
 * @return java.lang.String
 */
public int getMaxRow() {
	return row;
}
/**
 * Insert the method's description here.
 * Creation date: (3/26/2001 3:57:09 PM)
 * @return java.lang.String
 */
public java.lang.String getNext() {
	return (String)next.elementAt(index);
}
/**
 * Insert the method's description here.
 * Creation date: (3/26/2001 3:57:09 PM)
 * @return java.lang.String
 */
public java.lang.String getNext(int idx) {
	if (idx < 0) idx=0;
	if (idx > (getMaxRow()-1)) idx=getMaxRow() - 1; 
	return (String)next.elementAt(idx);
}
/**
 * Insert the method's description here.
 * Creation date: (3/26/2001 3:50:06 PM)
 * @return java.lang.String
 */
public java.lang.String getOfficer() {
	return officer;
}
/**
 * Insert the method's description here.
 * Creation date: (3/26/2001 3:57:09 PM)
 * @return java.lang.String
 */
public java.lang.String getOption() {
	return option;
}
/**
 * Insert the method's description here.
 * Creation date: (3/26/2001 3:57:09 PM)
 * @return java.lang.String
 */
public java.lang.String getPrevious() {
	return (String)previous.elementAt(index);
}
/**
 * Insert the method's description here.
 * Creation date: (3/26/2001 3:57:09 PM)
 * @return java.lang.String
 */
public java.lang.String getPrevious(int idx) {
	if (idx < 0) idx=0;
	if (idx > (getMaxRow()-1)) idx=getMaxRow() - 1;
	return (String)previous.elementAt(idx);
}
public void init() {	  

	next = new java.util.Vector();
	previous = new java.util.Vector();

	// Initialize internal counter
	row = 0;
	// Initialize cuurent row variable
	index = -1;
	
}
/**
 * Insert the method's description here.
 * Creation date: (3/26/2001 3:55:42 PM)
 * @param newAccNum java.lang.String
 */
public void setAccNum(java.lang.String newAccNum) {
	accNum = newAccNum;
}
/**
 * Insert the method's description here.
 * Creation date: (3/26/2001 3:55:42 PM)
 * @param newAmount java.lang.String
 */
public void setAmount(java.lang.String newAmount) {
	amount = newAmount;
}
/**
 * Insert the method's description here.
 * Creation date: (3/26/2001 3:52:45 PM)
 * @param newBank java.lang.String
 */
public void setBank(java.lang.String newBank) {
	bank = newBank;
}
/**
 * Insert the method's description here.
 * Creation date: (3/26/2001 3:53:21 PM)
 * @param newBranch java.lang.String
 */
public void setBranch(java.lang.String newBranch) {
	branch = newBranch;
}
/**
 * Insert the method's description here.
 * Creation date: (3/26/2001 3:57:09 PM)
 * @param newCause java.lang.String
 */
public void setCause(java.lang.String newCause) {
	cause = newCause;
}
/**
 * Insert the method's description here.
 * Creation date: (3/26/2001 3:56:29 PM)
 * @param newChkNum java.lang.String
 */
public void setChkNum(java.lang.String newChkNum) {
	chkNum = newChkNum;
}
/**
 * Insert the method's description here.
 * Creation date: (9/17/2001 10:19:09 AM)
 * @param newCurrency java.lang.String
 */
public void setCurrency(java.lang.String newCurrency) {
	currency = newCurrency;	
	}
/**
 * Insert the method's description here.
 * Creation date: (3/26/2001 3:55:42 PM)
 * @param newPosition java.lang.String
 */
public void setIndex(int newIndex) {
	index = newIndex;
}
/**
 * Insert the method's description here.
 * Creation date: (3/26/2001 3:57:09 PM)
 * @param newOfficer java.lang.String
 */
public void setOfficer(java.lang.String newOfficer) {
	officer = newOfficer;
}
/**
 * Insert the method's description here.
 * Creation date: (3/26/2001 3:50:06 PM)
 * @param newOption java.lang.String
 */
public void setOption(java.lang.String newOption) {
	option = newOption;
}

public void setRelation(java.lang.String newRelation) {
	relation = newRelation;
}
}
