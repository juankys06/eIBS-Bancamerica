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
   A representation of the PDF Boolean type.
   @author Nassib Nassar
*/
public class PjBoolean
	extends PjObject {

		public static final PjBoolean TRUE = new PjBoolean(true);
		public static final PjBoolean FALSE = new PjBoolean(false);

	private boolean _b;
	
	/**
	   Creates a Boolean object.
	   @param b the Boolean value to initialize this object to.
	*/
	public PjBoolean(boolean b) {
		_b = b;
	}
	/**
	   Returns a string representation of this Boolean in PDF format.
	   @return the string representation.
	   public String toString() {
		if (_b) {
			return "true";
		} else {
			return "false";
		}
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
	   Compares two PjBoolean objects for equality.
	   @param obj the reference object to compare to.
	   @return true if this object is the same as obj, false
	   otherwise.
	*/
	public boolean equals(Object obj) {
		if (obj == null) {
			return false;
		}
		if (obj instanceof PjBoolean) {
			return (_b == ((PjBoolean)obj)._b);
		} else {
			return false;
		}
	}
	/**
	   Returns the Boolean value of this object.
	   @return the Boolean value of this object.
	*/
	public boolean getBoolean() {
		return _b;
	}
	/**
	   Writes this Boolean to a stream in PDF format.
	   @param os the stream to write to.
	   @return the number of bytes written.
	   @exception IOException if an I/O error occurs.
	 */
	public long writePdf(OutputStream os) throws IOException {
		if (_b) {
			return write(os, "true");
		} else {
			return write(os, "false");
		}
	}
	/**
	   Writes this Boolean to a file in PDF format.
	   @param raf the file to write to.
	   @return the number of bytes written.
	   @exception IOException if an I/O error occurs.
	   @deprecated
	 */
	public long writePdf(RandomAccessFile raf) throws IOException {
		if (_b) {
			return write(raf, "true");
		} else {
			return write(raf, "false");
		}
	}
}