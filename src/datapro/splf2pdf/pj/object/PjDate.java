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


import datapro.splf2pdf.pj.exception.*;

/**
   A representation of the PDF Date type.
   @author Nassib Nassar
*/
public class PjDate
	extends PjString {

	// this should be added (or something like this).
	// this would encode the date as a string.
	/*
	public PjDate(Date d) {
	}
	*/

	// this should be added, similar to isLike in other classes in this package.
	/*
	public static boolean isLike(PjString s) {
	}
	*/
	
	/**
	   Creates a Date object.
	   @param s the string value to initialize this object to.
	*/
	public PjDate(String s) {
		super(s);
	}
}