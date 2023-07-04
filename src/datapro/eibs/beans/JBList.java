package datapro.eibs.beans;

import java.util.*;
import java.math.*;

public class JBList {
	
	private int row;
	private int currentRow;

	private Vector flag;
	private Vector record;
	
	private int firstRec;
	private int lastRec;
	private boolean showPrev;
	private boolean showNext;
	private boolean noResult;
	
	private java.lang.String searchText;
	private java.lang.String searchType;
public JBList() {

	init();
	
}
public void addRow(String newFlag, String newRecord) {

	flag.addElement(newFlag);
	record.addElement(newRecord);
	
	lastRec = firstRec + row;
	row++;

	noResult = false;
}

public void removeRow(int srow) {
	
		flag.remove(srow);
		record.remove(srow);
		row--;		
		lastRec--;
		
		if (lastRec < 0) noResult = true;
	}
/**
 * Insert the method's description here.
 * Creation date: (7/28/2000 11:38:33 AM)
 * @return int
 */
public int getCurrentRow() {
	return currentRow;
}
/**
 * Insert the method's description here.
 * Creation date: (5/23/2000 4:29:46 PM)
 * @return int
 */
public int getFirstRec() {
	return firstRec;
}
/**
 * Insert the method's description here.
 * Creation date: (5/23/2000 4:30:53 PM)
 * @return String
 */
public String getFlag() {
	return (String)flag.elementAt(currentRow);
}
/**
 * Insert the method's description here.
 * Creation date: (5/23/2000 4:30:07 PM)
 * @return int
 */
public int getLastRec() {
	return lastRec;
}
public boolean getNextRow() {

	currentRow++;
	return (currentRow < row);

}
/**
 * Insert the method's description here.
 * Creation date: (5/23/2000 4:32:34 PM)
 * @return boolean
 */
public boolean getNoResult() {
	return noResult;
}
/**
 * Insert the method's description here.
 * Creation date: (5/23/2000 4:30:53 PM)
 * @return String
 */
public String getRecord() {
	return (String)record.elementAt(currentRow);
}
/**
 * Insert the method's description here.
 * Creation date: (5/26/2000 5:24:29 PM)
 * @return java.lang.String
 */
public java.lang.String getSearchText() {
	return searchText;
}
/**
 * Insert the method's description here.
 * Creation date: (5/26/2000 5:24:47 PM)
 * @return java.lang.String
 */
public java.lang.String getSearchType() {
	return searchType;
}
/**
 * Insert the method's description here.
 * Creation date: (5/23/2000 4:31:11 PM)
 * @return boolean
 */
public boolean getShowNext() {
	return showNext;
}
/**
 * Insert the method's description here.
 * Creation date: (5/23/2000 4:30:53 PM)
 * @return boolean
 */
public boolean getShowPrev() {
	return showPrev;
}
public void init() {	  

	flag = new java.util.Vector();
	record = new java.util.Vector();

	row = 0;	// Initialize internal counter

	initRow();  // Initialize cuurent row variable

	firstRec = 0;
	lastRec = 0;
	showPrev = false;
	showNext = false;
	noResult = true;
}
public void initRow() {	

	currentRow = -1;

}
/**
 * Insert the method's description here.
 * Creation date: (7/28/2000 11:38:33 AM)
 * @param newCurrentRow int
 */
public void setCurrentRow(int newCurrentRow) {
	currentRow = newCurrentRow;
}
/**
 * Insert the method's description here.
 * Creation date: (5/23/2000 4:29:46 PM)
 * @param newFirstRec int
 */
public void setFirstRec(int newFirstRec) {
	
	firstRec = newFirstRec;
	showPrev = (newFirstRec > 1);
	
}
public void setFlag(String value, int srow) {
	  
	flag.setElementAt(value, srow);

}
/**
 * Insert the method's description here.
 * Creation date: (5/23/2000 4:30:07 PM)
 * @param newLastRec int
 */
public void setLastRec(int newLastRec) {
	lastRec = newLastRec;
}
/**
 * Insert the method's description here.
 * Creation date: (5/23/2000 4:32:34 PM)
 * @param newNoResult boolean
 */
public void setNoResult(boolean newNoResult) {
	noResult = newNoResult;
}
public void setRecord(String value, int srow) {
	  
	record.setElementAt(value, srow);

}
/**
 * Insert the method's description here.
 * Creation date: (5/26/2000 5:24:29 PM)
 * @param newSearchText java.lang.String
 */
public void setSearchText(java.lang.String newSearchText) {
	searchText = newSearchText;
}
/**
 * Insert the method's description here.
 * Creation date: (5/26/2000 5:24:47 PM)
 * @param newSearchType java.lang.String
 */
public void setSearchType(java.lang.String newSearchType) {
	searchType = newSearchType;
}
/**
 * Insert the method's description here.
 * Creation date: (5/23/2000 4:31:11 PM)
 * @param newShowNext boolean
 */
public void setShowNext(boolean newShowNext) {
	showNext = newShowNext;
}
/**
 * Insert the method's description here.
 * Creation date: (5/23/2000 4:30:53 PM)
 * @param newShowPrev boolean
 */
public void setShowPrev(boolean newShowPrev) {
	showPrev = newShowPrev;
}
}