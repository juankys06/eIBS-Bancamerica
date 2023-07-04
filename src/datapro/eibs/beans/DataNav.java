package datapro.eibs.beans;

/**
 * Insert the type's description here.
 * Creation date: (3/26/2001 3:38:05 PM)
 * @author: Ramses Amaro
 */
import java.util.*;

public class DataNav {

	public int index;
	public int row;

	private Vector next;
	private Vector previous;
	
/**
 * Transaction constructor comment.
 */
public DataNav() {
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
public java.lang.String getPrevious() {
	return (String)previous.elementAt(index);
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
 * @param newPosition java.lang.String
 */
public void setIndex(int newIndex) {
	index = newIndex;
}
}
