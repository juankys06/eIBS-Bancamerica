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
   A representation of a PDF resources dictionary.
   @author Nassib Nassar
*/
public class PjResources
	extends PjDictionary {

	/**
	   Creates a new resources dictionary.
	*/
	public PjResources() {
		super();
	}
	/**
	   Creates a resources dictionary as a wrapper around a Hashtable.
	   @param h the Hashtable to use for this dictionary.
	*/
	public PjResources(Hashtable h) {
		super(h);
	}
	/**
	   Returns a deep copy of this object.
	   @return a deep copy of this object.
	   @exception CloneNotSupportedException if the instance can not be cloned.
	*/
	public Object clone() throws CloneNotSupportedException {
		return new PjResources(cloneHt());
	}
	public PjObject getColorSpace() throws InvalidPdfObjectException {
		return hget(PjName.COLORSPACE);
	}
	public PjObject getExtGState() throws InvalidPdfObjectException {
		return hget(PjName.EXTGSTATE);
	}
	public PjObject getFont() throws InvalidPdfObjectException {
		return hget(PjName.FONT);
	}
	public PjObject getPattern() throws InvalidPdfObjectException {
		return hget(PjName.PATTERN);
	}
	public PjObject getProcSet() throws InvalidPdfObjectException {
		return hget(PjName.PROCSET);
	}
	public PjObject getProperties() throws InvalidPdfObjectException {
		return hget(PjName.PROPERTIES);
	}
	public PjObject getXObject() throws InvalidPdfObjectException {
		return hget(PjName.XOBJECT);
	}
	/**
	   Examines a dictionary to see if it is a PDF resources
	   dictionary.
	   @param dictionary the dictionary to examine.
	   @return true if the dictionary could be interpreted as a
	   valid PjResources object.
	*/
	public static boolean isLike(PjDictionary dictionary) {
		return (dictionary.getHashtable().get(PjName.PROCSET) != null);
	}
	public void setColorSpace(PjArray colorSpace) {
		_h.put(PjName.COLORSPACE, colorSpace);
	}
	public void setColorSpace(PjName colorSpace) {
		_h.put(PjName.COLORSPACE, colorSpace);
	}
	public void setColorSpace(PjReference colorSpace) {
		_h.put(PjName.COLORSPACE, colorSpace);
	}
	public void setExtGState(PjDictionary extGState) {
		_h.put(PjName.EXTGSTATE, extGState);
	}
	public void setExtGState(PjReference extGState) {
		_h.put(PjName.EXTGSTATE, extGState);
	}
	public void setFont(PjDictionary font) {
		_h.put(PjName.FONT, font);
	}
	public void setFont(PjReference font) {
		_h.put(PjName.FONT, font);
	}
	public void setPattern(PjReference pattern) {
		_h.put(PjName.PATTERN, pattern);
	}
	public void setPattern(PjStream pattern) {
		_h.put(PjName.PATTERN, pattern);
	}
	public void setProcSet(PjProcSet procSet) {
		_h.put(PjName.PROCSET, procSet);
	}
	public void setProcSet(PjReference procSet) {
		_h.put(PjName.PROCSET, procSet);
	}
	public void setProperties(PjDictionary properties) {
		_h.put(PjName.PROPERTIES, properties);
	}
	public void setProperties(PjReference properties) {
		_h.put(PjName.PROPERTIES, properties);
	}
	public void setXObject(PjDictionary xObject) {
		_h.put(PjName.XOBJECT, xObject);
	}
	public void setXObject(PjReference xObject) {
		_h.put(PjName.XOBJECT, xObject);
	}
}