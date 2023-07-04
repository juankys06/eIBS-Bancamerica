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
   PDF data.
   @author Nassib Nassar
*/
public class PdfFormatException
	extends PjException {

	private int _errorOffset = -1;
	
	/**
	   Creates a PdfFormatException with a detailed message.
	   @param s the detailed message.
	*/
	public PdfFormatException(String s) {
		super(s);
	}
	/**
	   Creates a PdfFormatException with a detailed message and
	   offset.  A detailed message is a String that describes this
	   particular exception.
	   @param s the detailed message.
	   @param errorOffset the position where the error is found
	   while parsing.
	*/
	public PdfFormatException(String s, int errorOffset) {
		super(s);
		_errorOffset = errorOffset;
	}
	/**
	   Returns the position where the error was found.
	   @return the position where the error was found or -1 if no
	   position information is available.
	*/
	public int getErrorOffset() {
		return _errorOffset;
	}
}