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
   A representation of the PDF ProcSet type.
   @author Nassib Nassar
*/
public class PjProcSet
	extends PjArray {

	/**
	   Creates a ProcSet object.
	*/
	public PjProcSet() {
		super();
	}
	/**
	   Creates a ProcSet as a wrapper around a Vector.
	   @param v the Vector to use for this ProcSet.
	*/
	public PjProcSet(Vector v) {
		super(v);
	}
	/**
	   Returns a deep copy of this object.
	   @return a deep copy of this object.
	   @exception CloneNotSupportedException if the instance can not be cloned.
	*/
	public Object clone() throws CloneNotSupportedException {
		return new PjProcSet(cloneVector());
	}
	/**
		   Examines an array to see if it is a PDF ProcSet object.
		   @param array the array to examine.
		   @return true if the array could be interpreted as a
		   valid PjProcSet object.
		*/
		public static boolean isLike(PjArray array) {
		// see if all the names are legal ProcSet names
		Enumeration m = array._v.elements();
		PjName name;
		Object obj;
		while (m.hasMoreElements()) {
			obj = m.nextElement();
			if ( ! (obj instanceof PjName) ) {
				return false;
			}
			name = (PjName)obj;
			if ( ( ! name.equals(PjName.PDF) ) &&
			     ( ! name.equals(PjName.TEXT) ) &&
			     ( ! name.equals(PjName.IMAGEB) ) &&
			     ( ! name.equals(PjName.IMAGEC) ) &&
			     ( ! name.equals(PjName.IMAGEI) ) ) {
				return false;
			}
		}
		return true;
		}
}