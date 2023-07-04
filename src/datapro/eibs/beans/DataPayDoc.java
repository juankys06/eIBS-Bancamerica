package datapro.eibs.beans;

/**
 * Insert the type's description here.
 * Creation date: (3/26/2001 3:38:05 PM)
 * @author: Ramses Amaro
 */
public class DataPayDoc {
	public java.lang.String pagocy;
	public java.lang.String pagobk;
	public java.lang.String pagobr;
	public java.lang.String pagogl;
	public java.lang.String pagcon;
	public java.lang.String pagopc;
	public java.lang.String pagoac;
	public java.lang.String pagvd1;
	public java.lang.String pagvd2;
	public java.lang.String pagvd3;
	public java.lang.String deanr1;
	public java.lang.String deanr2;
	public boolean flagPrint;
/**
 * Transaction constructor comment.
 */
public DataPayDoc() {
	super();
}
/**
 * Insert the method's description here.
 * Creation date: (3/26/2001 3:57:52 PM)
 * @return java.lang.String
 */
public java.lang.String getDEANR1() {
	return deanr1;
}
/**
 * Insert the method's description here.
 * Creation date: (3/26/2001 3:54:42 PM)
 * @return java.lang.String
 */
public java.lang.String getDEANR2() {
	return deanr2;
}
/**
 * Insert the method's description here.
 * Creation date: (3/26/2001 4:00:15 PM)
 * @return java.lang.String
 */
public java.lang.String getPAGCON() {
	return pagcon;
}
/**
 * Insert the method's description here.
 * Creation date: (3/26/2001 3:55:42 PM)
 * @return java.lang.String
 */
public java.lang.String getPAGOAC() {
	return pagoac;
}
/**
 * Insert the method's description here.
 * Creation date: (3/26/2001 3:52:45 PM)
 * @return java.lang.String
 */
public java.lang.String getPAGOBK() {
	return pagobk;
}
/**
 * Insert the method's description here.
 * Creation date: (3/26/2001 3:53:21 PM)
 * @return java.lang.String
 */
public java.lang.String getPAGOBR() {
	return pagobr;
}
/**
 * Insert the method's description here.
 * Creation date: (3/26/2001 3:50:06 PM)
 * @return java.lang.String
 */
public java.lang.String getPAGOCY() {
	return pagocy;
}
/**
 * Insert the method's description here.
 * Creation date: (3/26/2001 3:56:29 PM)
 * @return java.lang.String
 */
public java.lang.String getPAGOGL() {
	return pagogl;
}
/**
 * Insert the method's description here.
 * Creation date: (3/26/2001 3:59:51 PM)
 * @return java.lang.String
 */
public java.lang.String getPAGOPC() {
	return pagopc;
}
/**
 * Insert the method's description here.
 * Creation date: (3/26/2001 3:58:16 PM)
 * @return java.lang.String
 */
public java.lang.String getPAGVD1() {
	return pagvd1;
}
/**
 * Insert the method's description here.
 * Creation date: (3/26/2001 3:58:51 PM)
 * @return java.lang.String
 */
public java.lang.String getPAGVD2() {
	return pagvd2;
}
/**
 * Insert the method's description here.
 * Creation date: (3/26/2001 3:59:09 PM)
 * @return java.lang.String
 */
public java.lang.String getPAGVD3() {
	return pagvd3;
}
/**
 * Insert the method's description here.
 * Creation date: (3/29/2001 3:08:05 PM)
 * @return boolean
 */
public boolean isFlagPrint() {
	return flagPrint;
}
/**
 * Insert the method's description here.
 * Creation date: (3/26/2001 4:00:15 PM)
 * @param newCreditAmt java.lang.String
 */
public void setDEANR1(java.lang.String newDEANR1) {
	deanr1 = newDEANR1;
}
/**
 * Insert the method's description here.
 * Creation date: (3/26/2001 3:56:29 PM)
 * @param newChkNum java.lang.String
 */
public void setDEANR2(java.lang.String newDEANR2) {
	deanr2 = newDEANR2;
}
/**
 * Insert the method's description here.
 * Creation date: (3/29/2001 3:08:05 PM)
 * @param newFlagPrint boolean
 */
public void setFlagPrint(boolean newFlagPrint) {
	flagPrint = newFlagPrint;
}
/**
 * Insert the method's description here.
 * Creation date: (3/26/2001 3:54:03 PM)
 * @param newTrNum java.lang.String
 */
public void setPAGCON(java.lang.String newPAGCON) {
	pagcon = newPAGCON;
}
/**
 * Insert the method's description here.
 * Creation date: (3/26/2001 3:57:52 PM)
 * @param newDescription java.lang.String
 */
public void setPAGOAC(java.lang.String newPAGOAC) {
	pagoac = newPAGOAC;
}
/**
 * Insert the method's description here.
 * Creation date: (3/26/2001 3:59:51 PM)
 * @param newDebitAmt java.lang.String
 */
public void setPAGOBK(java.lang.String newPAGOBK) {
	pagobk = newPAGOBK;
}
/**
 * Insert the method's description here.
 * Creation date: (3/26/2001 3:53:21 PM)
 * @param newBranch java.lang.String
 */
public void setPAGOBR(java.lang.String newPAGOBR) {
	pagobr = newPAGOBR;
}
/**
 * Insert the method's description here.
 * Creation date: (3/26/2001 3:55:42 PM)
 * @param newAccNum java.lang.String
 */
public void setPAGOCY(java.lang.String newPAGOCY) {
	pagocy = newPAGOCY;
}
/**
 * Insert the method's description here.
 * Creation date: (3/26/2001 3:57:09 PM)
 * @param newRefNum java.lang.String
 */
public void setPAGOGL(java.lang.String newPAGOGL) {
	pagogl = newPAGOGL;
}
/**
 * Insert the method's description here.
 * Creation date: (3/26/2001 3:54:42 PM)
 * @param newWrkFile java.lang.String
 */
public void setPAGOPC(java.lang.String newPAGOPC) {
	pagopc = newPAGOPC;
}
/**
 * Insert the method's description here.
 * Creation date: (3/26/2001 3:58:16 PM)
 * @param newDate1 java.lang.String
 */
public void setPAGVD1(java.lang.String newPAGVD1) {
	pagvd1 = newPAGVD1;
}
/**
 * Insert the method's description here.
 * Creation date: (3/26/2001 3:58:51 PM)
 * @param newDate2 java.lang.String
 */
public void setPAGVD2(java.lang.String newPAGVD2) {
	pagvd2 = newPAGVD2;
}
/**
 * Insert the method's description here.
 * Creation date: (3/26/2001 3:59:09 PM)
 * @param newDate3 java.lang.String
 */
public void setPAGVD3(java.lang.String newPAGVD3) {
	pagvd3 = newPAGVD3;
}
}
