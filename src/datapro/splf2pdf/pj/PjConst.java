package datapro.splf2pdf.pj;

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



import datapro.splf2pdf.pj.object.*;

/**
   General constants used by the pj classes.
   @author Nassib Nassar
 */
public class PjConst {

	/**
	   The pj version number.
	*/
	public static final String VERSION = "0.22";

	/**
	   The pj copyright notice, which is inserted into the
	   Producer (and sometimes Creator) field of the Info
	   dictionary in all PDF files output by pj; you may not
	   remove this copyright notice.
	*/
	public static final PjString COPYRIGHT_IN_INFO =
	new PjString("pj " + VERSION + ", Copyright (C) 1998 Etymon Systems, Inc. <info@etymon.com>");
	
	/**
	   The pj version number in PDF format, which is inserted into
	   all PDF files output by pj.
	*/
	public static final String VERSION_IN_PDF = "%pj-" + VERSION;

	/**
	   The pj copyright notice, which is inserted into all PDF
	   files output by pj; you may not remove this copyright
	   notice.
	*/
	public static final String COPYRIGHT_IN_PDF =
	"%Generated with pj " + VERSION + ", Copyright (C) 1998 Etymon Systems, Inc. <info@etymon.com>";

	/**
	   The PDF version output by this version of pj.
	*/
	public static final String PDF_VERSION = "1.2";
	
	/**
	   The number of bytes from the end of a PDF file at which to
	   start scanning for startxref.
	*/
	public static final int SCAN_STARTXREF = 40;

	/**
	   The size of the byte[] used for flate compression and
	   decompression.
	*/
	public static final int FLATE_BUFFER_SIZE = 16384;
	
	/**
	   The end-of-line sequence to use when writing a PDF file to
	   disk.
	*/
	public static final String PDF_EOL = "\n";

	/**
	   The string length of PDF_EOL.
	*/
	public static final int PDF_EOL_LEN = PDF_EOL.length();

}