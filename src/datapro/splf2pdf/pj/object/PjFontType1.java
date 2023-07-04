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
import datapro.splf2pdf.pj.exception.*;

/**
   A representation of a PDF type 1 font dictionary.
   @author Nassib Nassar
*/
public class PjFontType1
	extends PjFont {

	/**
	  Creates a new type 1 font dictionary.
	*/
	public PjFontType1() {
		super();
		_h.put(PjName.SUBTYPE, PjName.TYPE1);
	}
	/**
	   Creates a type 1 font dictionary as a wrapper around a Hashtable.
	   @param h the Hashtable to use for this dictionary.
	*/
	public PjFontType1(Hashtable h) {
		super(h);
	}
	/**
	   Returns a deep copy of this object.
	   @return a deep copy of this object.
	   @exception CloneNotSupportedException if the instance can not be cloned.
	*/
	public Object clone() throws CloneNotSupportedException {
		return new PjFontType1(cloneHt());
	}
	public PjObject getBaseFont() throws InvalidPdfObjectException {
		return hget(PjName.BASEFONT);
	}
	public PjObject getEncoding() throws InvalidPdfObjectException {
		return hget(PjName.ENCODING);
	}
	public PjObject getFirstChar() throws InvalidPdfObjectException {
		return hget(PjName.FIRSTCHAR);
	}
	public PjObject getFontDescriptor() throws InvalidPdfObjectException {
		return hget(PjName.FONTDESCRIPTOR);
	}
	public PjObject getLastChar() throws InvalidPdfObjectException {
		return hget(PjName.LASTCHAR);
	}
	public PjObject getWidths() throws InvalidPdfObjectException {
		return hget(PjName.WIDTHS);
	}
	/**
	   Examines a dictionary to see if it is a PDF type 1 font.
	   @param dictionary the dictionary to examine.
	   @return true if the dictionary could be interpreted as a
	   valid PjFontType1 object.
	*/
	public static boolean isLike(PjDictionary dictionary) {
		Hashtable h = dictionary.getHashtable();
		// check if the Type is Font and Subtype is Type1
		try {
			PjName type = (PjName)(h.get(PjName.TYPE));
			if (type == null) {
				return false;
			}
			if ( ! type.equals(PjName.FONT) ) {
				return false;
			}
			PjName subtype = (PjName)(h.get(PjName.SUBTYPE));
			if (subtype == null) {
				return false;
			}
			if ( ! subtype.equals(PjName.TYPE1) ) {
				return false;
			}
		}
		catch (ClassCastException e) {
			return false;
		}
		return true;
	}
	public void setBaseFont(PjName baseFont) {
		_h.put(PjName.BASEFONT, baseFont);
	}
	public void setBaseFont(PjReference baseFont) {
		_h.put(PjName.BASEFONT, baseFont);
	}
	public void setEncoding(PjDictionary encoding) {
		_h.put(PjName.ENCODING, encoding);
	}
	public void setEncoding(PjName encoding) {
		_h.put(PjName.ENCODING, encoding);
	}
	public void setEncoding(PjReference encoding) {
		_h.put(PjName.ENCODING, encoding);
	}
	public void setFirstChar(PjNumber firstChar) {
		_h.put(PjName.FIRSTCHAR, firstChar);
	}
	public void setFirstChar(PjReference firstChar) {
		_h.put(PjName.FIRSTCHAR, firstChar);
	}
	public void setFontDescriptor(PjReference fontDescriptor) {
		_h.put(PjName.FONTDESCRIPTOR, fontDescriptor);
	}
	public void setLastChar(PjNumber lastChar) {
		_h.put(PjName.LASTCHAR, lastChar);
	}
	public void setLastChar(PjReference lastChar) {
		_h.put(PjName.LASTCHAR, lastChar);
	}
	public void setWidths(PjReference widths) {
		_h.put(PjName.WIDTHS, widths);
	}
}