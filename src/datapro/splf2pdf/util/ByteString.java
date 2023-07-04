package datapro.splf2pdf.util;

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

public class ByteString {

	public static int lastIndexOf(byte[] buffer, String str) {
		// for now, brute force
		if (str.length() == 0) {
			return buffer.length;
		}
		int length = str.length();
		int x, y;
		boolean match;
		for (x = (buffer.length - length); x >= 0; x--) {
			match = true;
			for (y = 0; y < length; y++) {
				if ((char)(buffer[x + y]) !=
				    str.charAt(y)) {
					match = false;
					break;
				}
			}
			if (match) {
				return x;
			}
		}
		return -1;
	}
}