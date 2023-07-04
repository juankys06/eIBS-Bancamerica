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
import java.util.zip.*;



import datapro.splf2pdf.pj.*;
import datapro.splf2pdf.pj.exception.*;

/**
   A representation of the PDF stream type.
   @author Nassib Nassar
*/
public class PjStream
	extends PjObject {

	protected PjStreamDictionary _d;
	protected byte[] _s;
	
	/**
	   Creates a stream as a wrapper around a byte array.
	   @param s the byte array to use for this stream.
	*/
	public PjStream(byte[] s) {
		_d = new PjStreamDictionary();
		_s = s;
	}
	/**
	   Creates a stream as a wrapper around a PjDictionary and
	   byte array.
	   @param d the dictionary to use for this stream.
	   @param s the byte array to use for this stream.
	   @deprecated
	*/
	public PjStream(PjDictionary d, byte[] s) {
		_d = new PjStreamDictionary(d.getHashtable());
		_s = s;
	}
	/**
	   Creates a stream as a wrapper around a PjStreamDictionary and
	   byte array.
	   @param d the dictionary to use for this stream.
	   @param s the byte array to use for this stream.
	*/
	public PjStream(PjStreamDictionary d, byte[] s) {
		_d = d;
		_s = s;
	}
	/**
	   Returns a string representation of this stream in PDF format.
	   @return the string representation.
	public String toString() {
		updateLength();
		return _d.toString() + PjConst.PDF_EOL + "stream\n" + new String(_s) + "endstream";
	}
	 */

	/**
	   Returns a deep copy of this object.
	   @return a deep copy of this object.
	   @exception CloneNotSupportedException if the instance can not be cloned.
	*/
	public Object clone() throws CloneNotSupportedException {
		return new PjStream((PjStreamDictionary)(_d.clone()), (byte[])(_s.clone()));
	}
	/**
	   Compress this stream with the Flate algorithm if it is not
	   already compressed.
	   @return a cloned, compressed version of this stream; or
	   this stream if it is already compressed.
	   @exception InvalidPdfObjectException if an invalid object
	   type is encountered.
	*/
	public PjStream flateCompress() throws InvalidPdfObjectException {
		// first check if any compression filters are turned on;
		// if so, return this (we don't need to compress).
		// if not, turn on the FlateDecode filter in the new dictionary
		Hashtable ht = _d.getHashtable();
		Object filter = ht.get(PjName.FILTER);
		if (filter != null) {
			if ( ( ! (filter instanceof PjName) ) && ( ! (filter instanceof PjArray) ) ) {
				throw new InvalidPdfObjectException("Stream filter is not a name or array.");
			}
			// get or create a vector with the list of filters
			Vector v = null;
			if (filter instanceof PjName) {
				v = new Vector();
				v.addElement(filter);
			}
			else if (filter instanceof PjArray) {
				v = ((PjArray)filter).getVector();
			}
			// see if any of the filters are compression filters
			PjName name;
			Enumeration m = v.elements();
			Object obj;
			while (m.hasMoreElements()) {
				obj = m.nextElement();
				if ( ! (obj instanceof PjName) ) {
					throw new InvalidPdfObjectException(
						"Stream filter array contins an object that is not a name.");
				}
				name = (PjName)obj;
				if ( (name.equals(PjName.LZWDECODE)) ||
				     (name.equals(PjName.RUNLENGTHDECODE)) ||
				     (name.equals(PjName.CCITTFAXDECODE)) ||
				     (name.equals(PjName.DCTDECODE)) ||
				     (name.equals(PjName.FLATEDECODE)) ) {
					return this;
				}
			}
		}
		// ok, clone the dictionary and add the FlateDecode filter
		PjStreamDictionary newPjd;
		try {
			newPjd = (PjStreamDictionary)(_d.clone());
		}
		catch (CloneNotSupportedException e) {
			throw new InvalidPdfObjectException(e.getMessage());
		}
		Hashtable newHt = newPjd.getHashtable();
		if (filter == null) {
			newHt.put(PjName.FILTER, PjName.FLATEDECODE);
		} else {
			if (filter instanceof PjArray) {
				Vector v = ((PjArray)filter).getVector();
				v.addElement(PjName.FLATEDECODE);
			} else {
				// filter must be a name, so make it into an array
				Vector v = new Vector();
				v.addElement(filter);
				v.addElement(PjName.FLATEDECODE);
				newHt.put(PjName.FILTER, new PjArray(v));
			}
		}
		// do the compression
		ByteArrayOutputStream byteArrayOut = new ByteArrayOutputStream();
		DeflaterOutputStream out = new DeflaterOutputStream(byteArrayOut);
		ByteArrayInputStream in = new ByteArrayInputStream(_s);
		int length;
		byte[] buffer = new byte[PjConst.FLATE_BUFFER_SIZE];
		try {
			while ((length = in.read(buffer, 0, buffer.length)) != -1) {
				out.write(buffer, 0, length);
			}
			out.close();
			in.close();
		}
		catch (IOException e) {
			// not sure what would cause this exception in this case
			return this;
		}
		return new PjStream(newPjd, byteArrayOut.toByteArray());
	}
	/**
	   Decompresses this stream if it is compressed with the Flate
	   algorithm.
	   @return a cloned, uncompressed version of this stream; or
	   this stream if it is not marked as being compressed with
	   Flate.
	   @exception InvalidPdfObjectException if an invalid object
	   type is encountered.
	*/
	public PjStream flateDecompress() throws InvalidPdfObjectException {
		// first check if the FlateDecode filter is turned on;
		// if not, return this (we don't need to decompress).
		// if so, turn off the filter in the new dictionary
		Hashtable ht = _d.getHashtable();
		Object obj = ht.get(PjName.FILTER);
		if (obj == null) {
			return this;
		}
		if ( (obj instanceof PjName) || (obj instanceof PjArray) ) {
			PjStreamDictionary newPjd = null;
			Hashtable newHt;
			if (obj instanceof PjName) {
				PjName pjn = (PjName)obj;
				if ( ! pjn.equals(PjName.FLATEDECODE) ) {
					return this;
				} else {
					// remove the element from the cloned dictionary
					try {
						newPjd = (PjStreamDictionary)(_d.clone());
					}
					catch (CloneNotSupportedException e) {
						throw new InvalidPdfObjectException(e.getMessage());
					}
					newHt = newPjd.getHashtable();
					newHt.remove(PjName.FILTER);
				}
			}
			else if (obj instanceof PjArray) {
				PjArray pja = (PjArray)obj;
				Vector v = pja.getVector();
				int x;
				if ( (x = v.indexOf(PjName.FLATEDECODE)) == -1) {
					return this;
				} else {
					// remove the element from the cloned dictionary
					try {
						newPjd = (PjStreamDictionary)(_d.clone());
					}
					catch (CloneNotSupportedException e) {
						throw new InvalidPdfObjectException(e.getMessage());
					}
					newHt = newPjd.getHashtable();
					pja = (PjArray)(newHt.get(PjName.FILTER));
					v = pja.getVector();
					v.removeElementAt(x);
				}
			}
			// do the decompression
			InflaterInputStream in = new InflaterInputStream(new ByteArrayInputStream(_s));
			ByteArrayOutputStream out = new ByteArrayOutputStream();
			int length;
			byte[] buffer = new byte[PjConst.FLATE_BUFFER_SIZE];
			try {
				while ((length = in.read(buffer, 0, buffer.length)) != -1) {
					out.write(buffer, 0, length);
				}
				out.close();
				in.close();
			}
			catch (IOException e) {
				// not sure what would cause this exception in this case
				return this;
			}
			return new PjStream(newPjd, out.toByteArray());
		} else {
			throw new InvalidPdfObjectException("Stream filter is not a name or array.");
		}
	}
	/**
	   Returns the byte array used in the representation of this
	   stream.
   	   @return the byte array used in the representation of this
   	   stream.
	*/
	public byte[] getBuffer() {
		return _s;
	}
	/**
	   Returns the PjDictionary used in the representation of this
	   stream.
   	   @return the PjDictionary used in the representation of this
   	   stream.
	   @deprecated
	*/
	public PjDictionary getDictionary() {
		updateLength();
		return _d;
	}
	/**
	   Returns the PjStreamDictionary used in the representation of this
	   stream.
   	   @return the PjStreamDictionary used in the representation of this
   	   stream.
	*/
	public PjStreamDictionary getStreamDictionary() {
		updateLength();
		return _d;
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
		_d.renumber(map);
	}
	private void updateLength() {
		_d.getHashtable().put(new PjName("Length"),
				      new PjNumber(_s.length));
	}
	/**
	   Writes this PDF stream to a stream in PDF format.
	   @param os the stream to write to.
	   @return the number of bytes written.
	   @exception IOException if an I/O error occurs.
	*/
	public long writePdf(OutputStream os) throws IOException {
		updateLength();
		long z = _d.writePdf(os);
		z = z + writeln(os, "");
		z = z + write(os, "stream\n");
		z = z + write(os, _s);
		z = z + write(os, "endstream");
		return z;
	}
	/**
	   Writes this stream to a file in PDF format.
	   @param raf the file to write to.
	   @return the number of bytes written.
	   @exception IOException if an I/O error occurs.
	   @deprecated
	*/
	public long writePdf(RandomAccessFile raf) throws IOException {
		updateLength();
		long z = _d.writePdf(raf);
		z = z + writeln(raf, "");
		z = z + write(raf, "stream\n");
		z = z + write(raf, _s);
		z = z + write(raf, "endstream");
		return z;
	}
}