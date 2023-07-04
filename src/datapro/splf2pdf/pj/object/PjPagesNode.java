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
   A representation of a dictionary node in a PDF Pages tree (abstract base class).
   @author Nassib Nassar
*/
public abstract class PjPagesNode
	extends PjDictionary {

	/**
	   Creates a new Pages dictionary node.
	*/
	public PjPagesNode() {
		super();
	}
	/**
	   Creates a Pages dictionary node as a wrapper around a Hashtable.
	   @param h the Hashtable to use for this dictionary node.
	*/
	public PjPagesNode(Hashtable h) {
		super(h);
	}
	public PjObject getAA() throws InvalidPdfObjectException {
		return hget(PjName.AA);
	}
	public PjObject getCropBox() throws InvalidPdfObjectException {
		return hget(PjName.CROPBOX);
	}
	public PjObject getDur() throws InvalidPdfObjectException {
		return hget(PjName.DUR);
	}
	public PjObject getHid() throws InvalidPdfObjectException {
		return hget(PjName.HID);
	}
	public PjObject getMediaBox() throws InvalidPdfObjectException {
		return hget(PjName.MEDIABOX);
	}
	public PjReference getParent() throws InvalidPdfObjectException {
		return hgetReference(PjName.PARENT);
	}
	public PjObject getResources() throws InvalidPdfObjectException {
		return hget(PjName.RESOURCES);
	}
	public PjObject getRotate() throws InvalidPdfObjectException {
		return hget(PjName.ROTATE);
	}
	public PjObject getTrans() throws InvalidPdfObjectException {
		return hget(PjName.TRANS);
	}
	public void setAA(PjDictionary aA) {
		_h.put(PjName.AA, aA);
	}
	public void setAA(PjReference aA) {
		_h.put(PjName.AA, aA);
	}
	public void setCropBox(PjRectangle cropBox) {
		_h.put(PjName.CROPBOX, cropBox);
	}
	public void setCropBox(PjReference cropBox) {
		_h.put(PjName.CROPBOX, cropBox);
	}
	public void setDur(PjNumber dur) {
		_h.put(PjName.DUR, dur);
	}
	public void setDur(PjReference dur) {
		_h.put(PjName.DUR, dur);
	}
	public void setHid(PjBoolean hid) {
		_h.put(PjName.HID, hid);
	}
	public void setHid(PjReference hid) {
		_h.put(PjName.HID, hid);
	}
	public void setMediaBox(PjRectangle mediaBox) {
		_h.put(PjName.MEDIABOX, mediaBox);
	}
	public void setMediaBox(PjReference mediaBox) {
		_h.put(PjName.MEDIABOX, mediaBox);
	}
	public void setParent(PjReference parent) {
		_h.put(PjName.PARENT, parent);
	}
	public void setResources(PjReference resources) {
		_h.put(PjName.RESOURCES, resources);
	}
	public void setResources(PjResources resources) {
		_h.put(PjName.RESOURCES, resources);
	}
	public void setRotate(PjNumber rotate) {
		_h.put(PjName.ROTATE, rotate);
	}
	public void setRotate(PjReference rotate) {
		_h.put(PjName.ROTATE, rotate);
	}
	public void setTrans(PjDictionary trans) {
		_h.put(PjName.TRANS, trans);
	}
	public void setTrans(PjReference trans) {
		_h.put(PjName.TRANS, trans);
	}
}