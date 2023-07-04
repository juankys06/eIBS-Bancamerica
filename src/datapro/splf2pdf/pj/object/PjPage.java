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
   A representation of a PDF page dictionary.
   @author Nassib Nassar
*/
public class PjPage
	extends PjPagesNode {

	/**
	   Creates a new page dictionary.
	*/
	public PjPage() {
		super();
		_h.put(PjName.TYPE, PjName.PAGE);
	}
	/**
	   Creates a page dictionary as a wrapper around a Hashtable.
	   @param h the Hashtable to use for this dictionary.
	*/
	public PjPage(Hashtable h) {
		super(h);
	}
	/**
	   Returns a deep copy of this object.
	   @return a deep copy of this object.
	   @exception CloneNotSupportedException if the instance can not be cloned.
	*/
	public Object clone() throws CloneNotSupportedException {
		return new PjPage(cloneHt());
	}
	public PjObject getAnnots() throws InvalidPdfObjectException {
		return hget(PjName.ANNOTS);
	}
	public PjObject getB() throws InvalidPdfObjectException {
		return hget(PjName.B);
	}
	public PjObject getContents() throws InvalidPdfObjectException {
		return hget(PjName.CONTENTS);
	}
	public PjReference getThumb() throws InvalidPdfObjectException {
		return hgetReference(PjName.THUMB);
	}
	/**
	   Examines a dictionary to see if it is a PDF page.
	   @param dictionary the dictionary to examine.
	   @return true if the dictionary could be interpreted as a
	   valid PjPage object.
	*/
	public static boolean isLike(PjDictionary dictionary) {
		Hashtable h = dictionary.getHashtable();
		// check if the Type is Page
		try {
			PjName type = (PjName)(h.get(PjName.TYPE));
			if (type == null) {
				return false;
			}
			if ( ! type.equals(PjName.PAGE) ) {
				return false;
			}
		}
		catch (ClassCastException e) {
			return false;
		}
		return true;
	}
	public void setAnnots(PjArray annots) {
		_h.put(PjName.ANNOTS, annots);
	}
	public void setAnnots(PjReference annots) {
		_h.put(PjName.ANNOTS, annots);
	}
	public void setB(PjArray b) {
		_h.put(PjName.B, b);
	}
	public void setB(PjReference b) {
		_h.put(PjName.B, b);
	}
	public void setContents(PjArray contents) {
		_h.put(PjName.CONTENTS, contents);
	}
	public void setContents(PjReference contents) {
		_h.put(PjName.CONTENTS, contents);
	}
	public void setThumb(PjReference thumb) {
		_h.put(PjName.THUMB, thumb);
	}
}