package datapro.splf2pdf.pj.exception;

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

/**
   An exception that gets thrown when the parser encounters invalid
   data while trying to read the xref table.
   @author Nassib Nassar
*/
public class XrefFormatException
	extends PjException {

	/**
	   Creates an XrefFormatException with a detailed message.
	   @param s the detailed message.
	*/
	public XrefFormatException(String s) {
		super(s);
	}
}