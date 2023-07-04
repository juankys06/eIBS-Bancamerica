package datapro.eibs.beans;

import java.util.*;
import java.math.*;

public class JBListRec {
	
	private int row;
	private int currentRow;

	private Vector flag;
	private Vector record[];

	private int col;
	
	private boolean noResult;
public JBListRec() {

}
public void addRow(String newFlag, String newRecord[]) {

	flag.addElement(newFlag);
	for (int i=0; i<col; i++) {
		record[i].addElement(newRecord[i]);
	}
	
	lastRec = firstRec + row;
	row++;

	noResult = false;
	
}

public void removeRow(int srow) {
	
	flag.remove(srow);
	for (int i=0; i<col; i++) {
		record[i].removeElementAt(srow);
	}
	
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
 * Creation date: (5/23/2000 4:30:53 PM)
 * @return String
 */
public String getFlag() {
	return (String)flag.elementAt(currentRow);
}
/**
 * Insert the method's description here.
 * Creation date: (7/28/2000 11:38:33 AM)
 * @return int
 */
public int getLastRow() {
	return (row - 1);
}
public boolean getNextRow() {

	currentRow++;
	return (currentRow < row);

}
/**
 * Insert the method's description here.
 * Creation date: (11/6/2000 4:16:31 PM)
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
public String getRecord(int i) {
	return (String)record[i].elementAt(currentRow);
}
/**
 * Insert the method's description here.
 * Creation date: (7/28/2000 11:38:33 AM)
 * @return int
 */
public int getRow() {
	return row;
}
public void init(int num) {	  

	col = num;
	
	flag = new java.util.Vector();
	
	record = new java.util.Vector[col];
	for (int i=0; i<col; i++) {
		record[i] = new java.util.Vector();
	}

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
public void setFlag(String value, int srow) {
	  
	flag.setElementAt(value, srow);

}
/**
 * Insert the method's description here.
 * Creation date: (7/28/2000 11:38:33 AM)
 * @param newCurrentRow int
 */
public void setLastRow() {
	currentRow = row - 1;
}
/**
 * Insert the method's description here.
 * Creation date: (11/6/2000 4:16:31 PM)
 * @param newNoResult boolean
 */
public void setNoResult(boolean newNoResult) {
	noResult = newNoResult;
}
public void setRecord(String value, int i, int srow) {
	  
	record[i].setElementAt(value, srow);

}

	private int firstRec;
	private int lastRec;
	private boolean showNext;
	private boolean showPrev;

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
 * Creation date: (5/23/2000 4:30:07 PM)
 * @return int
 */
public int getLastRec() {
	return lastRec;
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

/**
 * Insert the method's description here.
 * Creation date: (5/23/2000 4:29:46 PM)
 * @param newFirstRec int
 */
public void setFirstRec(int newFirstRec) {
	
	firstRec = newFirstRec;
	showPrev = (newFirstRec > 1);
	
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