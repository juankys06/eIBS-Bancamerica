package datapro.splf2pdf.pj.object;

/*
  
  Copyright (C) 1998 Etymon Systems, Inc. <info@etymon.com>

  This program is free software; you can redistribute it and/or modify
  it under the terms of version 2 of the GNU General Public License as
  published by the Free Software Foundation.

  This program is distributed in the hope that it will be useful, but
  WITHOUT ANY WARRANTY; without even the implied warranty of
  MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the GNU
  General Public License for more details.

  You should have received a copy of the GNU General Public License
  along with this program; if not, write to the Free Software
  Foundation, Inc., 59 Temple Place - Suite 330, Boston, MA
  02111-1307, USA.

*/

import java.util.*;

/**
   A representation of the PDF Rectangle type.
   @author Nassib Nassar
*/
public class PjRectangle
	extends PjArray {

	/**
	   Creates a Rectangle object.
	*/
	public PjRectangle() {
		super();
		_v.setSize(4);
	}
	/**
	   Creates a Rectangle as a wrapper around a Vector.
	   @param v the Vector to use for this Rectangle.
	*/
	public PjRectangle(Vector v) {
		super(v);
		if (_v.size() < 4) {
			_v.setSize(4);
		}
	}
	/**
	   Returns a deep copy of this object.
	   @return a deep copy of this object.
	   @exception CloneNotSupportedException if the instance can not be cloned.
	*/
	public Object clone() throws CloneNotSupportedException {
		return new PjRectangle(cloneVector());
	}
		public PjNumber getLowerLeftX() {
				return (PjNumber)(_v.elementAt(0));
		}
		public PjNumber getLowerLeftY() {
				return (PjNumber)(_v.elementAt(1));
		}
		public PjNumber getUpperRightX() {
				return (PjNumber)(_v.elementAt(2));
		}
		public PjNumber getUpperRightY() {
				return (PjNumber)(_v.elementAt(3));
		}
	/**
		   Examines an array to see if it is a PDF Rectangle object.
		   @param array the array to examine.
		   @return true if the array could be interpreted as a
		   valid PjRectangle object.
		*/
		public static boolean isLike(PjArray array) {
		if (array._v.size() != 4) {
			return false;
		}
		// see if all the elements are PjNumbers
		Enumeration m = array._v.elements();
		while (m.hasMoreElements()) {
			if ( ! (m.nextElement() instanceof PjNumber) ) {
				return false;
			}
		}
		return true;
		}
		public void setLowerLeftX(PjNumber number) {
		_v.setElementAt(number, 0);
		}
		public void setLowerLeftY(PjNumber number) {
		_v.setElementAt(number, 1);
		}
		public void setUpperRightX(PjNumber number) {
		_v.setElementAt(number, 2);
		}
		public void setUpperRightY(PjNumber number) {
		_v.setElementAt(number, 3);
		}
}