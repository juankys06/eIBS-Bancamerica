package datapro.eibs.beans;

/**
 * Insert the type's description here.
 * Creation date: (6/22/2000 4:19:51 PM)
 * @author: Orestes Garcia
 */
public class JBAverage {
	private java.lang.String currYearMAve[] = new java.lang.String[12] ;
	private java.lang.String currYear3MAve[] = new java.lang.String[4] ;

	private java.lang.String prevYearMAve[] = new java.lang.String[12] ;
	private java.lang.String prevYear3MAve[] = new java.lang.String[4] ;

	private java.lang.String accNum;
	private java.lang.String graph;
	private java.lang.String currYearAve;
	private java.lang.String prevYearAve;
	private boolean noRecord;
	private java.lang.String currYear;
	private java.lang.String prevYear;
/**
 * JBAverage constructor comment.
 */
public JBAverage() {
	super();
	for (int i=0; i<12; i++) {
		currYearMAve[i]	= "0.00";
		prevYearMAve[i] = "0.00";
		if ( i < 4 ) {
			currYear3MAve[i] = "0.00";
			prevYear3MAve[i] = "0.00";
		}
	}
	noRecord = true;
}
/**
 * Insert the method's description here.
 * Creation date: (6/26/2000 10:36:52 AM)
 * @return java.lang.String
 */
public java.lang.String getAccNum() {
	return accNum;
}
/**
 * Insert the method's description here.
 * Creation date: (6/27/2000 12:11:32 PM)
 * @return java.lang.String
 */
public java.lang.String getCurrYear() {
	return currYear;
}
/**
 * Insert the method's description here.
 * Creation date: (6/22/2000 4:29:02 PM)
 * @return java.lang.String
 */
public java.lang.String getCurrYear3MAve(int i) {
	return currYear3MAve[i];
}
/**
 * Insert the method's description here.
 * Creation date: (6/26/2000 4:27:00 PM)
 * @return java.lang.String
 */
public java.lang.String getCurrYearAve() {
	return currYearAve;
}
/**
 * Insert the method's description here.
 * Creation date: (6/22/2000 4:29:02 PM)
 * @return java.lang.String
 */
public java.lang.String getCurrYearMAve(int i) {
	return currYearMAve[i];
}
/**
 * Insert the method's description here.
 * Creation date: (6/26/2000 10:59:41 AM)
 * @return java.lang.String
 */
public java.lang.String getGraph() {
	return graph;
}
/**
 * Insert the method's description here.
 * Creation date: (6/27/2000 12:11:45 PM)
 * @return java.lang.String
 */
public java.lang.String getPrevYear() {
	return prevYear;
}
/**
 * Insert the method's description here.
 * Creation date: (6/22/2000 4:29:02 PM)
 * @return java.lang.String
 */
public java.lang.String getPrevYear3MAve(int i) {
	return prevYear3MAve[i];
}
/**
 * Insert the method's description here.
 * Creation date: (6/26/2000 4:27:17 PM)
 * @return java.lang.String
 */
public java.lang.String getPrevYearAve() {
	return prevYearAve;
}
/**
 * Insert the method's description here.
 * Creation date: (6/22/2000 4:29:02 PM)
 * @return java.lang.String
 */
public java.lang.String getPrevYearMAve(int i) {
	return prevYearMAve[i];
}
/**
 * Insert the method's description here.
 * Creation date: (6/26/2000 4:46:55 PM)
 * @return boolean
 */
public boolean isNoRecord() {
	return noRecord;
}
/**
 * Insert the method's description here.
 * Creation date: (6/26/2000 10:36:52 AM)
 * @param newAccNum java.lang.String
 */
public void setAccNum(java.lang.String newAccNum) {
	accNum = newAccNum;
}
/**
 * Insert the method's description here.
 * Creation date: (6/27/2000 12:11:32 PM)
 * @param newCurrYear java.lang.String
 */
public void setCurrYear(java.lang.String newCurrYear) {
	currYear = newCurrYear;
}
/**
 * Insert the method's description here.
 * Creation date: (6/22/2000 4:29:02 PM)
 * @param newCurrY1M java.lang.String
 */
public void setCurrYear3MAve(java.lang.String newCurrYear3MAve, int i) {
	currYear3MAve[i] = newCurrYear3MAve;
}
/**
 * Insert the method's description here.
 * Creation date: (6/26/2000 4:27:00 PM)
 * @param newCurrYearAve java.lang.String
 */
public void setCurrYearAve(java.lang.String newCurrYearAve) {
	currYearAve = newCurrYearAve;
}
/**
 * Insert the method's description here.
 * Creation date: (6/22/2000 4:29:02 PM)
 * @param newCurrY1M java.lang.String
 */
public void setCurrYearMAve(java.lang.String newCurrYearMAve, int i) {
	currYearMAve[i] = newCurrYearMAve;
}
/**
 * Insert the method's description here.
 * Creation date: (6/26/2000 10:59:41 AM)
 * @param newGraph java.lang.String
 */
public void setGraph(java.lang.String newGraph) {
	graph = newGraph;
}
/**
 * Insert the method's description here.
 * Creation date: (6/26/2000 4:46:55 PM)
 * @param newNoRecord boolean
 */
public void setNoRecord(boolean newNoRecord) {
	noRecord = newNoRecord;
}
/**
 * Insert the method's description here.
 * Creation date: (6/27/2000 12:11:45 PM)
 * @param newPrevYear java.lang.String
 */
public void setPrevYear(java.lang.String newPrevYear) {
	prevYear = newPrevYear;
}
/**
 * Insert the method's description here.
 * Creation date: (6/22/2000 4:29:02 PM)
 * @param newCurrY1M java.lang.String
 */
public void setPrevYear3MAve(java.lang.String newPrevYear3MAve, int i) {
	prevYear3MAve[i] = newPrevYear3MAve;
}
/**
 * Insert the method's description here.
 * Creation date: (6/26/2000 4:27:17 PM)
 * @param newPrevYearAve java.lang.String
 */
public void setPrevYearAve(java.lang.String newPrevYearAve) {
	prevYearAve = newPrevYearAve;
}
/**
 * Insert the method's description here.
 * Creation date: (6/22/2000 4:29:02 PM)
 * @param newCurrY1M java.lang.String
 */
public void setPrevYearMAve(java.lang.String newPrevYearMAve, int i) {
	prevYearMAve[i] = newPrevYearMAve;
}
}