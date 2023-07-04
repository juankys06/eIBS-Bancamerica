package datapro.splf2pdf.pj.util;

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

import java.util.*;


import datapro.splf2pdf.pj.object.*;

public class PjObjectVector {

	protected Vector _v;
	protected int _free;
	
	public PjObjectVector() {
		_v = new Vector();
		_free = 1;
	}
	public PjObjectVector(int initialCapacity) {
		_v = new Vector(initialCapacity);
		_free = 1;
	}
	protected int findFirstFree(int start) {
		synchronized (this) {
			int x = start;
			int size = _v.size();
			while ( (x < size) && (_v.elementAt(x) != null) ) {
				x++;
			}
			return x;
		}
	}
	public int getFirstFree() {
		synchronized (this) {
			return _free;
		}
	}
	public PjObject objectAt(int index) {
		synchronized (this) {
			if (index >= _v.size()) {
				return null;
			} else {
				return (PjObject)(_v.elementAt(index));
			}
		}
	}
	public void setObjectAt(PjObject obj, int index) {
		synchronized (this) {
			if (index >= _v.size()) {
				_v.setSize(index + 1);
			}
			_v.setElementAt(obj, index);
			if (index == _free) {
				_free = findFirstFree(index + 1);
			}
		}
	}
	public int size() {
		synchronized (this) {
			return _v.size();
		}
	}
}