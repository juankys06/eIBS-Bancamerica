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
   A PDF object representation.  This is the base class for all
   classes within this package.
   @author Nassib Nassar
*/
public abstract class PjObject
	implements Cloneable {

	/**
	   Returns a deep copy of this object.
	   @return a deep copy of this object.
	   @exception CloneNotSupportedException if the instance can not be cloned.
	*/
	public abstract Object clone() throws CloneNotSupportedException;
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
	}
	/**
	   Returns a string representation of this object in PDF format.
	   @return the string representation.
	 */
	public String toString() {
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		try {
			writePdf(baos);
		}
		catch (IOException e) {
			return null;
		}
		return baos.toString();
	}
	/**
	   Writes a byte[] to a stream.
	   @param os the stream to write to.
	   @param b the byte[] to write.
	   @return the number of bytes written.
	   @exception IOException if an I/O error occurs.
	*/
	public static long write(OutputStream os, byte[] b)
		throws IOException {
		os.write(b);
		return b.length;
	}
	/**
	   Writes a char to a stream.
	   @param os the stream to write to.
	   @param c the character to write.
	   @return the number of bytes written.
	   @exception IOException if an I/O error occurs.
	*/
	public static long write(OutputStream os, char c)
		throws IOException {
		os.write((int)c);
		return 1;
	}
	/**
	   Writes an Object to a stream.
	   @param os the stream to write to.
	   @param obj the Object to write.
	   @return the number of bytes written.
	   @exception IOException if an I/O error occurs.
	*/
	public static long write(OutputStream os, Object obj)
		throws IOException {
		return write(os, obj.toString().getBytes());
	}
	/**
	   Writes a byte[] to a file.
	   @param raf the file to write to.
	   @param b the byte[] to write.
	   @return the number of bytes written.
	   @exception IOException if an I/O error occurs.
	   @deprecated
	*/
	public static long write(RandomAccessFile raf, byte[] b)
		throws IOException {
		raf.write(b);
		return b.length;
	}
	/**
	   Writes a char to a file.
	   @param raf the file to write to.
	   @param c the character to write.
	   @return the number of bytes written.
	   @exception IOException if an I/O error occurs.
	   @deprecated
	*/
	public static long write(RandomAccessFile raf, char c)
		throws IOException {
		raf.writeByte((int)c);
		return 1;
	}
	/**
	   Writes an Object to a file.
	   @param raf the file to write to.
	   @param obj the Object to write.
	   @return the number of bytes written.
	   @exception IOException if an I/O error occurs.
	   @deprecated
	*/
	public static long write(RandomAccessFile raf, Object obj)
		throws IOException {
		String s = obj.toString();
		raf.writeBytes(s);
		return s.length();
	}
	/**
	   Writes an Object to a stream followed by a carriage return.
	   @param os the stream to write to.
	   @param obj the Object to write.
	   @return the number of bytes written.
	   @exception IOException if an I/O error occurs.
	*/
	public static long writeln(OutputStream os, Object obj)
		throws IOException {
		return write(os, obj) + write(os, PjConst.PDF_EOL);
	}
	/**
	   Writes an Object to a file followed by a carriage return.
	   @param raf the file to write to.
	   @param obj the Object to write.
	   @return the number of bytes written.
	   @exception IOException if an I/O error occurs.
	   @deprecated
	*/
	public static long writeln(RandomAccessFile raf, Object obj)
		throws IOException {
		return write(raf, obj) + write(raf, PjConst.PDF_EOL);
	}
	/**
	   Writes this object to a file in PDF format.
	   @param raf the file to write to.
	   @return the number of bytes written.
	   @exception IOException if an I/O error occurs.
	 */
	public abstract long writePdf(OutputStream os) throws
		IOException;
	/**
	   Writes this object to a file in PDF format.
	   @param raf the file to write to.
	   @return the number of bytes written.
	   @exception IOException if an I/O error occurs.
	   @deprecated
	 */
	public abstract long writePdf(RandomAccessFile raf) throws
		IOException;
}