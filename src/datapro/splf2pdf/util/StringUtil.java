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

public final class StringUtil {

	public static String sprintf(String template, String[] args) {
		if (args == null) {
			return template;
		}
		StringBuffer sb = new StringBuffer();
		int start = 0;
		int index;
		int x = 0;
		while ( (x < args.length) && ((index =
					       template.indexOf("%s",
								start)) != -1) ) {
			// allow \%s to escape
			if ( (index == 0) || (template.charAt(index -
							      1) !=
					      '\\') ) {
				sb.append(template.substring(start,
							     index));
				sb.append(args[x]);
				start = index + 2;
			}
		}
		sb.append(template.substring(start));
		return sb.toString();
	}
}