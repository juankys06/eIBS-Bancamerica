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

/**
   A representation of the PDF null type.
   @author Nassib Nassar
*/
public class PjNull
	extends PjObject {

	/**
	   Creates a null object.
	*/
	public PjNull() {
	}
	/**
	   Returns a string representation of this null object in PDF format.
	   @return the string representation.
	public String toString() {
		return "null";
	}
	 */

	/**
	   Returns a deep copy of this object.
	   @return a deep copy of this object.
	*/
	public Object clone() {
		return this;
	}
	/**
	   Compares two PjNull objects for equality.  They are
	   automatically considered to be equal if both objects are
	   truly instances of PjNull.
	   @param obj the reference object to compare to.
	   @return true if this object is the same as obj, false
	   otherwise.
	*/
	public boolean equals(Object obj) {
		if (obj == null) {
			return false;
		}
		return (obj instanceof PjNull);
	}
		/**
		   Writes this object (null) to a stream in PDF format.
		   @param os the stream to write to.
		   @return the number of bytes written.
		   @exception IOException if an I/O error occurs.
		 */
		public long writePdf(OutputStream os) throws IOException {
				return write(os, "null");
		}
		/**
		   Writes this object (null) to a file in PDF format.
		   @param raf the file to write to.
		   @return the number of bytes written.
		   @exception IOException if an I/O error occurs.
	   @deprecated
		 */
		public long writePdf(RandomAccessFile raf) throws IOException {
				return write(raf, "null");
		}
}