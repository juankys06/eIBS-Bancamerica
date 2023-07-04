package datapro.eibs.beans;

/**
 * Insert the type's description here.
 * Creation date: (3/26/2001 3:38:05 PM)
 * @author: Ramses Amaro
 */
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.*;

public class DataNavTotals {

	public int index;
	public int row;

	private Vector debit;
	private Vector credit;
	private Vector transprow;
/**
 * Transaction constructor comment.
 */
public DataNavTotals() {
	init();
}
public void addRow(String newDebit, String newCredit, String countTrans) {

	debit.addElement(newDebit);
	credit.addElement(newCredit);
	transprow.addElement(countTrans);
	row++;
	index= row - 1;

	//noResult = false;
}
/**
 * Insert the method's description here.
 * Creation date: (3/26/2001 3:57:09 PM)
 * @return java.lang.String
 */
public java.lang.String getCredit() {
	return (String)credit.elementAt(index);
}
/**
 * Insert the method's description here.
 * Creation date: (3/26/2001 3:57:09 PM)
 * @return java.lang.String
 */
public java.lang.String getDebit() {
	return (String)debit.elementAt(index);
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
public java.lang.String getTotalCredit() {
	String total="";
	BigDecimal sumCredit= new BigDecimal("0.00");
	  for( int i=0; i < row; i++) {
		  sumCredit= sumCredit.add(new BigDecimal(credit.elementAt(i).toString()));
	  }
	total= sumCredit.toString();
	return total;
}
/**
 * Insert the method's description here.
 * Creation date: (3/26/2001 3:57:09 PM)
 * @return java.lang.String
 */
public java.lang.String getTotalDebit() {
	String total="";
	BigDecimal sumDebit= new BigDecimal("0.00");
	  for( int i=0; i < row; i++) {
		  sumDebit= sumDebit.add(new BigDecimal(debit.elementAt(i).toString()));
	  }
	total= sumDebit.toString();
	return total;
}
/**
 * Insert the method's description here.
 * Creation date: (3/26/2001 3:57:09 PM)
 * @return java.lang.String
 */
public java.lang.String getTotalTrans() {
	String total="";
	BigInteger sumTrans= new BigInteger("0");
	  for( int i=0; i < row; i++) {
		  sumTrans = sumTrans.add(new BigInteger(transprow.elementAt(i).toString()));
	  }
	total="" + sumTrans;
	return total;
}
public void init() {	  

	debit = new java.util.Vector();
	credit = new java.util.Vector();
	transprow = new java.util.Vector();
	
	// Initialize internal counter
	row = 0;
	// Initialize cuurent row variable
	index = -1;
	
}
/**
 * Insert the method's description here.
 * Creation date: (3/26/2001 3:55:42 PM)
 * @param newPosition java.lang.String
 */
public void setIndex(int newIndex) {
	index = newIndex;
}
}
