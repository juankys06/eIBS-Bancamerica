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

import java.io.*;
import java.util.*;





import datapro.splf2pdf.pj.object.*;
import datapro.splf2pdf.pj.exception.*;
import datapro.splf2pdf.pj.util.*;
import datapro.splf2pdf.util.*;

/**
   A document representation of a PDF file.
   @author Nassib Nassar
 */
public class Pdf {

	protected PjObjectVector _objects;
	protected Hashtable _trailer;

	/**
	   Creates an empty PDF document.
	*/
	public Pdf() {
		init();
		createEmpty();
	}
	/**
	   Creates a PDF document from an existing PDF file.
	   @param filename the name of the PDF file to read.
	   @exception IOException if an I/O error occurs.
	   @exception PjException if a PDF error occurs.
	*/
	public Pdf(String filename) throws IOException, PjException {

		readFromFile(filename);

		// set the Producer in the Info dictionary to pj
		// get the Info dictionary
		PjReference infoRef;
		try {
			infoRef = getInfoDictionary();
		}
		catch (InvalidPdfObjectException e) {
			infoRef = null;
		}
		PjInfo info;
		if (infoRef == null) {
			// create a new Info dictionary and add it
			info = new PjInfo();
			int infoId = registerObject(info);
			infoRef = new PjReference(new PjNumber(infoId));
			setInfoDictionary(infoRef);
		} else {
			PjDictionary d = (PjDictionary)(getObject(infoRef.getObjNumber().getInt()));
			info = new PjInfo(d.getHashtable());
		}
		// set the Producer field
		// PjInfo.setProducer(PjObject) automatically includes pj in the string
		info.setProducer(new PjString(""));
		}
	/**
	   Adds a PjObject to a page in this PDF document.
	   @param page the page object to add to.
	   @param objectNumber the object number of the PjObject to add.
	   @exception InvalidPdfObjectException if an invalid object
	   type is encountered.
	 */
	public void addToPage(PjPage page, int objectNumber) throws InvalidPdfObjectException {
		PjReference objectToAdd = new PjReference(new PjNumber(objectNumber));
		// we handle four cases of /Contents:
		// 1) does not exist
		// 2) reference to a stream object
		// 3) reference to an array of references to stream objects
		// 4) array of references to stream objects
		// the last of these appears not to be supported by the PDF spec,
		// however we will accept it just in case
		PjObject contents = page.getContents();
		if (contents == null) {
			// set the page Contents to reference the new object
			page.setContents(objectToAdd);
		}
		else if (contents instanceof PjReference) {
			// find out whether the reference is to a stream or array
			PjObject indirectContents =
				getObject(((PjReference)contents).getObjNumber().getInt());
			if (indirectContents instanceof PjArray) {
				// add the new object to the existing array
				((PjArray)indirectContents).getVector().addElement(objectToAdd);
			}
			else if (indirectContents instanceof PjStream) {
				// create a new array that includes
				// the existing reference to the
				// stream as well as the new object
				// reference
				Vector v = new Vector();
				v.addElement(contents);
				v.addElement(objectToAdd);
				PjArray array = new PjArray(v);
				// add the new array to the document
				int arrayId = registerObject(array);
				// set the page Contents to reference this new array
				page.setContents(new PjReference(new PjNumber(arrayId)));
			}
			else {
				throw new InvalidPdfObjectException(
					"Contents reference in page does not reference a stream or array.");
			}
		}
		else if (contents instanceof PjArray) {
			// add the new object to the existing array
			((PjArray)contents).getVector().addElement(objectToAdd);
		}
		else {
			throw new InvalidPdfObjectException("Contents object in page is not a reference or array.");
		}
	}
	// we should split this out so that once we find the parent
	// node, we call a method to add the new page; we'll need to
	// use it in insertPage() also.
	/**
	   Appends a PjPage to the end of this PDF document.
	   @param objectNumber the object number of the PjPage to append.
	   @return the new object number of the appended PjPage.  */
	public int appendPage(int objectNumber) {
		// we do this the quickest way: go to the root Pages
		// node and add a link to the page at the top level.
		// this ignores the issue of maintaining a balanced
		// tree; we probably need some tree algorithms to deal
		// with general functions to manipulate the page tree.
		PjReference catalogRef = (PjReference)(_trailer.get(PjName.ROOT));
		PjDictionary catalog = (PjDictionary)getObject(catalogRef.getObjNumber().getInt());
		PjReference pagesRef = (PjReference)(catalog.getHashtable().get(PjName.PAGES));
		PjDictionary pages = (PjDictionary)getObject(pagesRef.getObjNumber().getInt());
		// we want to add the new page to the Kids array
		PjArray kids = (PjArray)(pages.getHashtable().get(PjName.KIDS));
		if (kids == null) {
			kids = new PjArray();
			pages.getHashtable().put(PjName.KIDS, kids);
		}
		kids.getVector().addElement( new PjReference(new PjNumber(objectNumber)) );
		// also need to set the parent
		PjPage page = (PjPage)getObject(objectNumber);
		page.setParent(pagesRef);
		// while we're here we need to increment the page count
		PjObject countObj = (PjObject)(pages.getHashtable().get(PjName.COUNT));
		PjNumber count = (PjNumber)resolve(countObj);
		int newCount = count.getInt() + 1;
		pages.getHashtable().put(PjName.COUNT, new PjNumber(newCount));
		return newCount;
	}
	/**
	   Appends the pages of a PDF document to this document.  Note
	   that this does not clone the other document but simply
	   includes references to its objects.  Therefore the other
	   document should be discarded immediately after a call to
	   this method, otherwise you could get very strange results.
	   @param pdf the PDF document to append.
	   @exception InvalidPdfObjectException if an invalid object
	   type is encountered in either document.  */
	public void appendPdfDocument(Pdf pdf) throws InvalidPdfObjectException {
		// first gather some information

		// locate the root Pages node in the other document
		int pagesId = pdf.getRootPages();
		PjDictionary d;
		try {
			d = (PjDictionary)(pdf.getObject(pagesId));
		}
		catch (ClassCastException e) {
			throw new InvalidPdfObjectException("Root pages object is not a dictionary.");
		}
		PjPages pages = new PjPages(d.getHashtable());

		// get the page count of the other document
		int pageCount = pdf.getPageCount();

		// locate the root Pages node in this document
		int thisPagesId = getRootPages();
		try {
			d = (PjDictionary)(getObject(thisPagesId));
		}
		catch (ClassCastException e) {
			throw new InvalidPdfObjectException("Root pages object is not a dictionary.");
		}
		PjPages thisPages = new PjPages(d.getHashtable());

		// at this point we haven't changed anything

		// register all the objects with this document,
		// building a mapping table as we go along
		int id;
		PjObject obj;
		int pagesIdNew = -1;
		int size = pdf._objects.size();
		Hashtable map = new Hashtable(size);
		for (int x = 1; x < size; x++) {
			obj = pdf._objects.objectAt(x);
			if (obj != null) {
				id = registerObject(obj);
				// new object number for the root Pages node
				if (x == pagesId) {
					pagesIdNew = id;
				}
				// add mapping
				map.put(new PjNumber(x),
					new PjReference(new PjNumber(id)));
			}
		}

		// renumber objects
		// enumerate map as a way of enumerating the objects we added
		for (Enumeration m = map.keys(); m.hasMoreElements();) {
			// get the object number of an object we added
			id = ((PjReference)(map.get(m.nextElement()))).getObjNumber().getInt();
			obj = _objects.objectAt(id);
			if (obj instanceof PjReference) {
				registerObject((PjReference)(map.get(((PjReference)obj).getObjNumber())), id);
			} else {
				obj.renumber(map);
			}
		}
		
		// create a new root Pages node that includes the root nodes from the two documents
		PjPages newPages = new PjPages();
		int newPagesId = registerObject(newPages);
		Vector v = new Vector();
		v.addElement(new PjReference(new PjNumber(thisPagesId)));
		v.addElement(new PjReference(new PjNumber(pagesIdNew)));
		newPages.setKids(new PjArray(v));
		newPages.setCount(new PjNumber(getPageCount() + pageCount));
		// set the old root nodes' Parent to point to the new root node
		PjReference newPagesRef = new PjReference(new PjNumber(newPagesId));
		thisPages.setParent(newPagesRef);
		pages.setParent(newPagesRef);

		// update the catalog to point to the new root Pages node
		int catalogId = getCatalog();
		try {
			d = (PjDictionary)(getObject(catalogId));
		}
		catch (ClassCastException e) {
			throw new InvalidPdfObjectException("Catalog object is not a dictionary.");
		}
		PjCatalog catalog = new PjCatalog(d.getHashtable());
		catalog.setPages(newPagesRef);
	}
	// this creates the minimal data structures for an empty Pdf object
	// (a single blank page)
	private void createEmpty() {
		// make a ProcSet
		Vector v = new Vector();
		v.addElement(PjName.PDF);
		v.addElement(PjName.TEXT);
		PjProcSet procSet = new PjProcSet(v);
		int procSetId = registerObject(procSet);
		// make a Resources dictionary
		PjResources resources = new PjResources();
		resources.setProcSet(new PjReference(new PjNumber(procSetId)));
		int resourcesId = registerObject(resources);
		// make a MediaBox rectangle
		PjRectangle mediaBox = new PjRectangle();
		mediaBox.setLowerLeftX(PjNumber.ZERO);
		mediaBox.setLowerLeftY(PjNumber.ZERO);
		mediaBox.setUpperRightX(new PjNumber(612));
		mediaBox.setUpperRightY(new PjNumber(792));
		// make a blank Page
		PjPage page = new PjPage();
		int pageId = registerObject(page);
		// make the kids array
		v = new Vector();
		v.addElement(new PjReference(new PjNumber(pageId)));
		PjArray kids = new PjArray(v);
		// make the root Pages node
		PjPages root = new PjPages();
		root.setResources(new PjReference(new PjNumber(resourcesId)));
		root.setMediaBox(mediaBox);
		root.setCount(PjNumber.ONE);
		root.setKids(kids);
		int rootId = registerObject(root);
		// we have to go back and set the blank page's parent to root
		page.setParent(new PjReference(new PjNumber(rootId)));
		// make the Catalog
		PjCatalog catalog = new PjCatalog();
		catalog.setPages(new PjReference(new PjNumber(rootId)));
		int catalogId = registerObject(catalog);
		// set Root in the trailer to point to the Catalog
		_trailer.put(PjName.ROOT, new PjReference(new PjNumber(catalogId)));
		// create an Info dictionary with default fields
		PjInfo info = new PjInfo();
		info.setCreator(PjConst.COPYRIGHT_IN_INFO);
		// need to add CreationDate and ModDate here, once we implement PjDate(Date)
		int infoId = registerObject(info);
		_trailer.put(PjName.INFO, new PjReference(new PjNumber(infoId)));
	}
	/**
	   Deletes a page in this document by page number.  The page
	   is deleted by removing the reference to it from the page
	   tree; however, no objects are actually deleted from the
	   document.
	   @param pageNumber the page number.  Pages are numbered
	   starting with 1.
	   @return the object number of the deleted Page object.
	   @exception IndexOutOfBoundsException if an invalid page
	   number was given.
	   @exception InvalidPdfObjectException if an invalid object
	   type is encountered.
	*/
	public int deletePage(int pageNumber) throws IndexOutOfBoundsException, InvalidPdfObjectException {
		if (pageNumber < 1) {
			throw new IndexOutOfBoundsException("Page number " + pageNumber + " is not >= 1.");
		}
		Counter counter = new Counter(0);
		int found = findPage(pageNumber, getRootPages(), null, counter, true);
		if (found == -1) {
			if (pageNumber > getPageCount()) {
				throw new IndexOutOfBoundsException("Page number " + pageNumber + " is not <= " +
								    getPageCount() + ".");
			} else {
				throw new InvalidPdfObjectException("Page number " + pageNumber +
								   " not found; ran out of pages.");
			}
		} else {
			return found;
		}
	}
	private int findPage(int pageNumber, int objectNumber, PjPages parentPages, Counter counter, boolean delete)
		throws InvalidPdfObjectException {
		PjDictionary node;
		try {
			node = (PjDictionary)getObject(objectNumber);
		}
		catch (ClassCastException e) {
			throw new InvalidPdfObjectException("Object in page tree is not a dictionary.");
		}
		// figure out whether node is a Page or Pages object
		PjName type;
		try {
			type = (PjName)(node.getHashtable().get(PjName.TYPE));
		}
		catch (ClassCastException e) {
			throw new InvalidPdfObjectException(
				"Type field in dictionary in page tree is not a name object.");
		}
		if (type.equals(PjName.PAGES)) {
			PjPages pages = new PjPages(node.getHashtable());
			PjArray kids;
			try {
				kids = (PjArray)(resolve((PjObject)(pages.getKids())));
			}
			catch (ClassCastException e) {
				throw new InvalidPdfObjectException("Kids field in pages object is not an array.");
			}
			if (kids != null) {
				Vector v = kids.getVector();
				int size = v.size();
				PjReference nodeRef;
				int found;
				for (int x = 0; x < size; x++) {
					try {
						nodeRef = (PjReference)(v.elementAt(x));
					}
					catch (ClassCastException e) {
						throw new InvalidPdfObjectException(
							"Object is kids array in pages object is not an indirect reference.");
					}
					found = findPage(pageNumber, nodeRef.getObjNumber().getInt(),
							 pages, counter, delete);
					if (found != -1) {
						if (delete) {
							// decrement the page count in this Pages node
							PjNumber count;
							try {
								count = (PjNumber)(resolve((PjObject)(pages.getCount())));
								if (count.isInteger() == false) {
									throw new ClassCastException();
								}
							}
							catch (ClassCastException e) {
								throw new InvalidPdfObjectException(
									"Count field in pages object is not an integer.");
							}
							pages.setCount(new PjNumber(count.getInt() - 1));
						}
						return found;
					}
				}
			}
			return -1;
		}
		if (type.equals(PjName.PAGE)) {
			counter.increment();
			if (counter.intValue() == pageNumber) {
				if (delete) {
					// remove the page from the kids array
					((PjArray)(parentPages.getKids())).getVector().removeElement(
						new PjReference(new PjNumber(objectNumber)));
				}
				return objectNumber;
			} else {
				return -1;
			}
		}
		return -1;
	}
	/**
	   Looks up the Catalog object in this document.
	   @return the object number of the Catalog object.
	   @exception InvalidPdfObjectException if an invalid object
	   type is encountered.
	 */
	public int getCatalog() throws InvalidPdfObjectException {
		PjReference catalogRef;
		try {
			catalogRef = (PjReference)(_trailer.get(PjName.ROOT));
		}
		catch (ClassCastException e) {
			throw new InvalidPdfObjectException("Root field in trailer is not an indirect reference.");
		}
		return catalogRef.getObjNumber().getInt();
	}
	/**
	   Looks up the Info dictionary within this document's trailer.
	   The Info dictionary contains general information about the
	   document.
	   @return a reference to the Info dictionary, or null if no
	   Info field is present in the trailer.
	   @exception InvalidPdfObjectException if the Info field in
	   the trailer is not a reference (PjReference) object.
	*/
	public PjReference getInfoDictionary() throws InvalidPdfObjectException {
		PjReference r;
		try {
			r = (PjReference)(_trailer.get(PjName.INFO));
		}
		catch (ClassCastException e) {
			throw new InvalidPdfObjectException("Info field is not an indirect reference.");
		}
		return r;
	}
	/**
	   Returns the largest object number in the list of registered
	   PjObjects.  This is useful mainly for functions that need
	   to run through the list and process each object, because
	   this provides the maximum object number they need to
	   examine.  The object number may not currently be assigned
	   to an object, but probably was at some point in the past.
	   @return the size of the object list.
	*/
	public int getMaxObjectNumber() {
		return Math.max(_objects.size() - 1, 0);
	}
	/**
	   Looks up a PjObject by its object number.
	   @param objectNumber the object number of the PjObject to retrieve.
	   @return the requested PjObject.
	 */
	public PjObject getObject(int objectNumber) {
		return _objects.objectAt(objectNumber);
	}
	/**
	   Looks up a page in this document by page number.
	   @param pageNumber the page number.  Pages are numbered
	   starting with 1.
	   @return the object number of the identified Page object.
	   @exception IndexOutOfBoundsException if an invalid page
	   number was given.
	   @exception InvalidPdfObjectException if an invalid object
	   type is encountered.
	*/	   
	public int getPage(int pageNumber) throws IndexOutOfBoundsException, InvalidPdfObjectException {
		if (pageNumber < 1) {
			throw new IndexOutOfBoundsException("Page number " + pageNumber + " is not >= 1.");
		}
		Counter counter = new Counter(0);
		int found = findPage(pageNumber, getRootPages(), null, counter, false);
		if (found == -1) {
			if (pageNumber > getPageCount()) {
				throw new IndexOutOfBoundsException("Page number " + pageNumber + " is not <= " +
								    getPageCount() + ".");
			} else {
				throw new InvalidPdfObjectException("Page number " + pageNumber +
								   " not found; ran out of pages.");
			}
		} else {
			return found;
		}
	}
	/**
	   Determines the number of pages in this PDF document.
	   @returns the number of pages in this PDF document.
	   @exception InvalidPdfObjectException if an invalid object
	   type is encountered.
	 */
	public int getPageCount() throws InvalidPdfObjectException {
		// the total number of pages should always be stored
		// in the root Pages node
		int pagesId = getRootPages();
		PjDictionary d;
		try {
			d = (PjDictionary)getObject(pagesId);
		}
		catch (ClassCastException e) {
			throw new InvalidPdfObjectException("Root pages object is not a dictionary.");
		}
		PjPages pages = new PjPages(d.getHashtable());

		PjObject countObj = pages.getCount();
		PjNumber count;
		try {
			count = (PjNumber)(resolve(countObj));
			if (count.isInteger() == false) {
				throw new ClassCastException();
			}
		}
		catch (ClassCastException e) {
			throw new InvalidPdfObjectException("Count field in root pages object is not an integer.");
		}
		return count.getInt();
	}
	/**
	   Looks up the root Pages object of this document's Pages tree.
	   @return the object number of the root Pages object.
	   @exception InvalidPdfObjectException if an invalid object
	   type is encountered.
	 */
	public int getRootPages() throws InvalidPdfObjectException {
		// we find the root Pages node via the Catalog object
		int catalogId = getCatalog();
		PjDictionary catalog;
		try {
			catalog = (PjDictionary)getObject(catalogId);
		}
		catch (ClassCastException e) {
			throw new InvalidPdfObjectException("Catalog is not a dictionary.");
		}
		PjReference pagesRef;
		try {
			pagesRef = (PjReference)(catalog.getHashtable().get(PjName.PAGES));
		}
		catch (ClassCastException e) {
			throw new InvalidPdfObjectException("Pages field in catalog is not an indirect reference.");
		}
		return pagesRef.getObjNumber().getInt();
	}
	/**
	   Returns a clone of a pages node such that all inherited
	   attributes of the given pages node are made explicit.  For
	   example, if MediaBox is not defined in the given pages
	   node, this method ascends the pages tree (via the Parent
	   reference) looking for an ancestor node that does contain a
	   value for MediaBox; if it finds one, it assigns that value
	   in the cloned (returned) pages node.  This is done for all
	   inheritable attributes.
	   @param node a pages node for which inherited attributes are
	   to be retrieved.
	   @return a cloned copy of the given pages node with actual
	   values substituted for all inherited attributes.
	   @exception InvalidPdfObjectException if an invalid object
	   type is encountered.
	*/
	public PjPagesNode inheritPageAttributes(PjPagesNode node) throws InvalidPdfObjectException {
		PjPagesNode newNode;
		try {
			newNode = (PjPagesNode)(node.clone());
		}
		catch (CloneNotSupportedException e) {
			throw new InvalidPdfObjectException(e.getMessage());
		}
		Hashtable ht = newNode.getHashtable();
		PjObject parentRef = newNode.getParent();
		while (parentRef != null) {
			PjObject parentObj = resolve(parentRef);
			if ( ! (parentObj instanceof PjPagesNode) ) {
				throw new InvalidPdfObjectException("Ancestor of pages node is not a pages node.");
			}
			PjPagesNode parent = (PjPagesNode)parentObj;
			inheritPageAttributesCollapse(PjName.MEDIABOX, ht, newNode, parent);
			inheritPageAttributesCollapse(PjName.RESOURCES, ht, newNode, parent);
			inheritPageAttributesCollapse(PjName.CROPBOX, ht, newNode, parent);
			inheritPageAttributesCollapse(PjName.ROTATE, ht, newNode, parent);
			inheritPageAttributesCollapse(PjName.DUR, ht, newNode, parent);
			inheritPageAttributesCollapse(PjName.HID, ht, newNode, parent);
			inheritPageAttributesCollapse(PjName.TRANS, ht, newNode, parent);
			inheritPageAttributesCollapse(PjName.AA, ht, newNode, parent);
			parentRef = parent.getParent();
		}
		return newNode;
	}
	private void inheritPageAttributesCollapse(PjName name, Hashtable ht, PjPagesNode newNode, PjPagesNode parent) {
		if (ht.get(name) == null) {
			Object obj = parent.getHashtable().get(name);
			if (obj != null) {
				ht.put(name, obj);
			}
		}
	}
	private void init() {
		_objects = new PjObjectVector();
		_trailer = new Hashtable();
	}
	private void readFromFile(String filename) throws IOException,
		PjException {
		init();
		RandomAccessFile raf = new RandomAccessFile(filename,
							    "r");
		try {
			PdfParser.getObjects(this, raf);
		}
		finally {
			// make an attempt to close the file
			try {
				raf.close();
			}
			catch (IOException e) {
			}
		}
	}
	/**
	   Registers a PjObject within this PDF document.
	   @param obj the PjObject to register.
	   @return the new object number of the registered PjObject.
	 */
	public int registerObject(PjObject obj) {
		int n = _objects.getFirstFree();
		_objects.setObjectAt(obj, n);
		return n;
	}
	/**
	   Registers a PjObject within this PDF document using a
	   specified object number.
	   @param obj the PjObject to register.
	   @param objectNumber the object number to register obj under.
	*/
	public void registerObject(PjObject obj, int objectNumber) {
		_objects.setObjectAt(obj, objectNumber);
	}
	/**
	   Dereferences a PjObject if it is a PjReference.
	   @param obj the PjObject to dereference.
	   @return the referenced PjObject if obj is a PjReference, or obj otherwise.
	 */
	public PjObject resolve(PjObject obj) {
		if (obj == null) {
			return null;
		} else {
			if (obj instanceof PjReference) {
				return resolve( getObject( ((PjReference)obj).getObjNumber().getInt() ) );
			} else {
				return obj;
			}
		}
	}
	/**
	   Sets the Info dictionary within this document's trailer.
	   @param ref a reference to the Info dictionary.
	*/
	public void setInfoDictionary(PjReference ref) {
		_trailer.put(PjName.INFO, ref);
	}
	/**
	   Writes this PDF document to a file in PDF format.
	   @param filename the name of the PDF file to create.
	   @exception IOException if an I/O error occurs.
	 */
	public void writeToFile(String filename) throws IOException {
		File file = new File(filename);
		file.delete();
		FileOutputStream fos = new FileOutputStream(file);
		writeToStream(fos);
		fos.close();
	}
	/**
	   Writes this PDF document to a stream in PDF format.
	   @param os the stream to write to.
	   @exception IOException if an I/O error occurs.
	 */
	public void writeToStream(OutputStream os) throws IOException {
		// first make sure to remove the Prev field from the
		// trailer if it is left over from having read a
		// multi-part xref!
		_trailer.remove(PjName.PREV);
		// remove the ID (if there is one) from the trailer
		_trailer.remove(PjName.ID);
		// ok, go ahead
		long z = 0;
		z = z + PjObject.writeln(os, "%PDF-" + PjConst.PDF_VERSION);
		z = z + PjObject.writeln(os, PjConst.VERSION_IN_PDF);
		// The pj copyright notice is inserted into all PDF
		// files output by pj; you may not remove this
		// copyright notice.
		z = z + PjObject.writeln(os, PjConst.COPYRIGHT_IN_PDF);
		z = z + PjObject.writeln(os, "%\323\343\317\342");
		PjObject obj;
		Integer objnum;
		int highest = 0;
		int size = _objects.size();
		long[] position = new long[size];
		for (int x = 1; x < size; x++) {
			if (x > highest) {
				highest = x;
			}
			obj = _objects.objectAt(x);
			position[x] = z;
			z = z + PjObject.writeln(os, x + " 0 obj");
			if (obj != null) {
				z = z + obj.writePdf(os);
			} else {
				// this is a small hack to avoid having to create "f" entries in the xref table
				z = z + PjNumber.ZERO.writePdf(os);
			}
			z = z + PjObject.writeln(os, "");
			z = z + PjObject.writeln(os, "endobj");
		}
		// write out xref
		long startxref = z;
		z = z + PjObject.writeln(os, "xref");
		int p = 0;
		int r;
		Long g;
		String s;
		position[0] = -1;
		int count = 0;
		while (p <= highest) {
			while ( (p <= highest) && (position[p] == 0) ) {
				p++;
			}
			r = p;
			while ( (r <= highest) && (position[r] != 0) ) {
				r++;
			}
			z = z + PjObject.write(os, p + " ");
			z = z + PjObject.writeln(os, new Integer(r - p));
			for (int x = p; x < r; x++) {
				count++;
				if (x == 0) {
					z = z + PjObject.write(os,
								 "0000000000 65535 f \n");
				} else {
					s = new Long(position[x]).toString();
					for (int w = 1;
					     (w + s.length()) <= 10; w++) {
						z = z + PjObject.write(os, "0");
					}
					z = z + PjObject.write(os, s);
					z = z + PjObject.write(os, " 00000 n \n");
				}
			}
			p = r;
		}
		// write out trailer
		z = z + PjObject.writeln(os, "trailer");
		_trailer.put(new PjName("Size"), new PjNumber(count));
		PjDictionary trailer = new PjDictionary(_trailer);
		z = z + trailer.writePdf(os);
		z = z + PjObject.writeln(os, "");
		z = z + PjObject.writeln(os, "startxref");
		z = z + PjObject.writeln(os, new Long(startxref));
		z = z + PjObject.writeln(os, "%%EOF");
	}
}