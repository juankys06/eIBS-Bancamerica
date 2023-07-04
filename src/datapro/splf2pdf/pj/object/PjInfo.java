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
   A representation of a PDF Info dictionary.
   @author Nassib Nassar
*/
public class PjInfo
	extends PjDictionary {

	/**
	   Creates a new Info dictionary.
	*/
	public PjInfo() {
		super();
		addDefaults();
	}
	/**
	   Creates an Info dictionary as a wrapper around a Hashtable.
	   @param h the Hashtable to use for this dictionary.
	*/
	public PjInfo(Hashtable h) {
		super(h);
		addDefaults();
	}
	private void addDefaults() {
		// Here we insert the PjConst.COPYRIGHT_IN_INFO
		// constant, which contains the pj copyright notice.
		// You may not remove this copyright notice.
		_h.put(PjName.PRODUCER, PjConst.COPYRIGHT_IN_INFO);
		// also need to add CreationDate and ModDate
	}
	/**
	   Returns a deep copy of this object.
	   @return a deep copy of this object.
	   @exception CloneNotSupportedException if the instance can not be cloned.
	*/
	public Object clone() throws CloneNotSupportedException {
		return new PjInfo(cloneHt());
	}
	public PjObject getAuthor() throws InvalidPdfObjectException {
		return hget(PjName.AUTHOR);
	}
	public PjObject getCreationDate() throws InvalidPdfObjectException {
		return hget(PjName.CREATIONDATE);
	}
	public PjObject getCreator() throws InvalidPdfObjectException {
		return hget(PjName.CREATOR);
	}
	public PjObject getKeywords() throws InvalidPdfObjectException {
		return hget(PjName.KEYWORDS);
	}
	public PjObject getModDate() throws InvalidPdfObjectException {
		return hget(PjName.MODDATE);
	}
	public PjObject getProducer() throws InvalidPdfObjectException {
		return hget(PjName.PRODUCER);
	}
	public PjObject getSubject() throws InvalidPdfObjectException {
		return hget(PjName.SUBJECT);
	}
	public PjObject getTitle() throws InvalidPdfObjectException {
		return hget(PjName.TITLE);
	}
	/**
	   Examines a dictionary to see if it is a PDF Info
	   dictionary.
	   @param dictionary the dictionary to examine.
	   @return true if the dictionary could be interpreted as a
	   valid PjInfo object.
	*/
	public static boolean isLike(PjDictionary dictionary) {
		// run through the keys and see if they are all valid,
		// and make sure there is at least one valid key
		boolean atLeastOneValid = false;
		Hashtable h = dictionary.getHashtable();
		PjName key;
		for (Enumeration enum = h.keys(); enum.hasMoreElements();) {
			key = (PjName)(enum.nextElement());
			if ( ( ! key.equals(PjName.AUTHOR) ) &&
			     ( ! key.equals(PjName.CREATIONDATE) ) &&
			     ( ! key.equals(PjName.MODDATE) ) &&
			     ( ! key.equals(PjName.CREATOR) ) &&
			     ( ! key.equals(PjName.PRODUCER) ) &&
			     ( ! key.equals(PjName.TITLE) ) &&
			     ( ! key.equals(PjName.SUBJECT) ) &&
			     ( ! key.equals(PjName.KEYWORDS) ) ) {
				return false;
			} else {
				if ( ! atLeastOneValid ) {
					atLeastOneValid = true;
				}
			}
		}
		return atLeastOneValid;
	}
	public void setAuthor(PjReference author) {
		_h.put(PjName.AUTHOR, author);
	}
	public void setAuthor(PjString author) {
		_h.put(PjName.AUTHOR, author);
	}
	public void setCreationDate(PjDate creationDate) {
		_h.put(PjName.CREATIONDATE, creationDate);
	}
	public void setCreationDate(PjReference creationDate) {
		_h.put(PjName.CREATIONDATE, creationDate);
	}
	public void setCreator(PjReference creator) {
		_h.put(PjName.CREATOR, creator);
	}
	public void setCreator(PjString creator) {
		_h.put(PjName.CREATOR, creator);
	}
	public void setKeywords(PjReference keywords) {
		_h.put(PjName.KEYWORDS, keywords);
	}
	public void setKeywords(PjString keywords) {
		_h.put(PjName.KEYWORDS, keywords);
	}
	public void setModDate(PjDate modDate) {
		_h.put(PjName.MODDATE, modDate);
	}
	public void setModDate(PjReference modDate) {
		_h.put(PjName.MODDATE, modDate);
	}
	public void setProducer(PjString producer) {
		_h.put(PjName.PRODUCER,
		       new PjString(PjConst.COPYRIGHT_IN_INFO.getString() +
		       ". " + producer.getString()));
	}
	public void setSubject(PjReference subject) {
		_h.put(PjName.SUBJECT, subject);
	}
	public void setSubject(PjString subject) {
		_h.put(PjName.SUBJECT, subject);
	}
	public void setTitle(PjReference title) {
		_h.put(PjName.TITLE, title);
	}
	public void setTitle(PjString title) {
		_h.put(PjName.TITLE, title);
	}
}