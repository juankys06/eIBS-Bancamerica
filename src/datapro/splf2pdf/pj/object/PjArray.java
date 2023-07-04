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

import java.io.*;
import java.util.*;


import datapro.splf2pdf.pj.*;

/**
   A representation of the PDF array type.
   @author Nassib Nassar
*/
public class PjArray
	extends PjObject {

	protected Vector _v;
	
	/**
	   Creates an empty array.
	*/
	public PjArray() {
		_v = new Vector();
	}
	/**
	   Creates an array as a wrapper around a Vector.
	   @param v the Vector to use for this array.
	*/
	public PjArray(Vector v) {
		_v = v;
	}
	/**
	   Returns a string representation of this array in PDF format.
	   @return the string representation.
	public String toString() {
		StringBuffer sb = new StringBuffer("[");
		sb.append(PjConst.PDF_EOL);
		int size = _v.size();
		for (int x = 0; x < size; x++) {
			sb.append(((PjObject)(_v.elementAt(x))).toString() + PjConst.PDF_EOL);
		}
		sb.append("]");
		return sb.toString();
	}
	 */

	/**
	   Returns a deep copy of this object.
	   @return a deep copy of this object.
	   @exception CloneNotSupportedException if the instance can not be cloned.
	*/
	public Object clone() throws CloneNotSupportedException {
		return new PjArray(cloneVector());
	}
	protected Vector cloneVector() throws CloneNotSupportedException {
		Vector v = new Vector(_v.size());
		Enumeration m = _v.elements();
		while (m.hasMoreElements()) {
			Object value = m.nextElement();
			if (value instanceof PjObject) {
				v.addElement(((PjObject)value).clone());
			} else {
				throw new CloneNotSupportedException("Object in array is not a PjObject.");
			}
		}
		return v;
	}
	/**
	   Returns the Vector used to represent this array.
	   @return the Vector used to represent this array.
	*/
	public Vector getVector() {
		return _v;
	}
	/**
	   Renumbers object references within this object.  This
	   method calls itself recursively to comprehensively renumber
	   all objects contained within this object.
	   @param map the table of object number mappings.  Each
	   object number is looked up by key in the hash table, and
	   the associated value is assigned as the new object number.
	   The map hash table should consist of PjNumber keys and
	   PjReference values.
	*/
	public void renumber(Hashtable map) {
		PjObject obj;
		Object r;
		int size = _v.size();
		for (int x = 0; x < size; x++) {
			try {
				obj = (PjObject)(_v.elementAt(x));
				if (obj instanceof PjReference) {
					r = map.get(((PjReference)obj).getObjNumber());
					if (r != null) {
						_v.setElementAt(r, x);
					}
				} else {
					obj.renumber(map);
				}
			}
			catch (ClassCastException e) {
				// ignore bad objects
			}
		}
	}
	/**
	   Writes this array to a stream in PDF format.
	   @param os the stream to write to.
	   @return the number of bytes written.
	   @exception IOException if an I/O error occurs.
	 */
	public long writePdf(OutputStream os) throws IOException {
		long z = 0;
		z = z + writeln(os, "[");
		int size = _v.size();
		for (int x = 0; x < size; x++) {
			z = z + ((PjObject)(_v.elementAt(x))).writePdf(os);
			z = z + writeln(os, "");
		}
		z = z + write(os, "]");
		return z;
	}
	/**
	   Writes this array to a file in PDF format.
	   @param raf the file to write to.
	   @return the number of bytes written.
	   @exception IOException if an I/O error occurs.
	   @deprecated
	 */
	public long writePdf(RandomAccessFile raf) throws IOException {
		long z = 0;
		z = z + writeln(raf, "[");
		int size = _v.size();
		for (int x = 0; x < size; x++) {
			z = z + ((PjObject)(_v.elementAt(x))).writePdf(raf);
			z = z + writeln(raf, "");
		}
		z = z + write(raf, "]");
		return z;
	}
}