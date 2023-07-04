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
   A representation of a PDF stream dictionary.  It is normally used
   in constructing a PjStream object.
   @author Nassib Nassar
*/
public class PjStreamDictionary
	extends PjDictionary {

	/**
	   Creates a new stream dictionary.
	*/
	public PjStreamDictionary() {
		super();
	}
	/**
	   Creates a stream dictionary as a wrapper around an Hashtable.
	   @param h the Hashtable to use for this dictionary.
	*/
	public PjStreamDictionary(Hashtable h) {
		super(h);
	}
	/**
	   Returns a deep copy of this object.
	   @return a deep copy of this object.
	   @exception CloneNotSupportedException if the instance can not be cloned.
	*/
	public Object clone() throws CloneNotSupportedException {
		return new PjStreamDictionary(cloneHt());
	}
	public PjObject getDecodeParms() throws InvalidPdfObjectException {
		return hget(PjName.DECODEPARMS);
	}
	// we need something like this that takes a PjFileSpec instead of PjString
	/*
	public void setF(PjString f) {
		_h.put(PjName.F, f);
	}
	*/

	public PjObject getF() throws InvalidPdfObjectException {
		return hget(PjName.F);
	}
	public PjObject getFDecodeParms() throws InvalidPdfObjectException {
		return hget(PjName.FDECODEPARMS);
	}
	public PjObject getFFilter() throws InvalidPdfObjectException {
		return hget(PjName.FFILTER);
	}
	public PjObject getFilter() throws InvalidPdfObjectException {
		return hget(PjName.FILTER);
	}
	public PjObject getLength() throws InvalidPdfObjectException {
		return hget(PjName.LENGTH);
	}
	public void setDecodeParms(PjObject decodeParms) {
		_h.put(PjName.DECODEPARMS, decodeParms);
	}
	public void setFDecodeParms(PjObject fDecodeParms) {
		_h.put(PjName.FDECODEPARMS, fDecodeParms);
	}
	public void setFFilter(PjArray fFilter) {
		_h.put(PjName.FFILTER, fFilter);
	}
	public void setFFilter(PjName fFilter) {
		_h.put(PjName.FFILTER, fFilter);
	}
	public void setFFilter(PjReference fFilter) {
		_h.put(PjName.FFILTER, fFilter);
	}
	public void setFilter(PjArray filter) {
		_h.put(PjName.FILTER, filter);
	}
	public void setFilter(PjName filter) {
		_h.put(PjName.FILTER, filter);
	}
	public void setFilter(PjReference filter) {
		_h.put(PjName.FILTER, filter);
	}
	public void setLength(PjNumber length) {
		_h.put(PjName.LENGTH, length);
	}
	public void setLength(PjReference length) {
		_h.put(PjName.LENGTH, length);
	}
}