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
   A representation of a PDF Pages dictionary.
   @author Nassib Nassar
*/
public class PjPages
	extends PjPagesNode {

	/**
	   Creates a new Pages dictionary.
	*/
	public PjPages() {
		super();
		_h.put(PjName.TYPE, PjName.PAGES);
	}
	/**
	   Creates a Pages dictionary as a wrapper around a Hashtable.
	   @param h the Hashtable to use for this dictionary.
	*/
	public PjPages(Hashtable h) {
		super(h);
	}
	/**
	   Returns a deep copy of this object.
	   @return a deep copy of this object.
	   @exception CloneNotSupportedException if the instance can not be cloned.
	*/
	public Object clone() throws CloneNotSupportedException {
		return new PjPages(cloneHt());
	}
	public PjObject getCount() throws InvalidPdfObjectException {
		return hget(PjName.COUNT);
	}
	public PjObject getKids() throws InvalidPdfObjectException {
		return hget(PjName.KIDS);
	}
	/**
	   Examines a dictionary to see if it is a PDF Pages object.
	   @param dictionary the dictionary to examine.
	   @return true if the dictionary could be interpreted as a
	   valid PjPages object.
	*/
	public static boolean isLike(PjDictionary dictionary) {
		Hashtable h = dictionary.getHashtable();
		// check if the Type is Pages
		try {
			PjName type = (PjName)(h.get(PjName.TYPE));
			if (type == null) {
				return false;
			}
			if ( ! type.equals(PjName.PAGES) ) {
				return false;
			}
		}
		catch (ClassCastException e) {
			return false;
		}
		return true;
	}
	public void setCount(PjNumber count) {
		_h.put(PjName.COUNT, count);
	}
	public void setCount(PjReference count) {
		_h.put(PjName.COUNT, count);
	}
	public void setKids(PjArray kids) {
		_h.put(PjName.KIDS, kids);
	}
	public void setKids(PjReference kids) {
		_h.put(PjName.KIDS, kids);
	}
}