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
   A representation of a PDF font dictionary (abstract base class).
   @author Nassib Nassar
*/
public abstract class PjFont
	extends PjDictionary {

	/**
	  Creates a new font dictionary.
	*/
	public PjFont() {
		super();
		_h.put(PjName.TYPE, PjName.FONT);
	}
	/**
	   Creates a font dictionary as a wrapper around a Hashtable.
	   @param h the Hashtable to use for this dictionary.
	*/
	public PjFont(Hashtable h) {
		super(h);
	}
	public PjObject getName() throws InvalidPdfObjectException {
		return hget(PjName.NAME);
	}
	public void setName(PjName name) {
		_h.put(PjName.NAME, name);
	}
}