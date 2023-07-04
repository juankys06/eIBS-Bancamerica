package datapro.splf2pdf.pbd;

import java.io.*;
import java.text.*;
import java.util.*;




import datapro.splf2pdf.pj.*;
import datapro.splf2pdf.pj.object.*;
import datapro.splf2pdf.pj.exception.*;

public class Spl2Pdf {

	InputStream inStream = null;
	OutputStream outStream = null;
	String fontSize = null;
	
	Pdf pdf = new Pdf();
	int lineNumber = 0;
	boolean good;
	Hashtable vars = new Hashtable();
	Vector texts = new Vector();
	Hashtable fonts = new Hashtable();
	String source = "Internal";
	String mediaBox = "letter-landscape";

	int curLineNumber;
			
/**
 * This method was created in VisualAge.
 * @param in java.io.InputStream
 * @param out java.io.OutputStream
 */
public Spl2Pdf(InputStream initInStream, OutputStream initOutStream) {
	this(initInStream, initOutStream, "7");
}
/**
 * This method was created in VisualAge.
 * @param in java.io.InputStream
 * @param out java.io.OutputStream
 */
public Spl2Pdf(InputStream initInStream, OutputStream initOutStream, String initFontSize) {
	inStream = initInStream;
	outStream = initOutStream;
	fontSize = initFontSize;
}
public Spl2Pdf(String initInFile, String initOutFile) {
	this(initInFile, initOutFile, "7");
}
public Spl2Pdf(String initInFile, String initOutFile, String initFontSize) {
	try {
		inStream = new FileInputStream(initInFile);
		outStream = new FileOutputStream(initOutFile);
	}
	catch (Exception e) {
		System.out.println(e);
	}
	fontSize = initFontSize;
}
	// returns page number of new page
	private static int appendPage(Pdf pdf, Vector texts, Hashtable vars) throws PjException {
		// create a font object
		PjFontType1 font = new PjFontType1();
		font.setBaseFont(new PjName("Helvetica-Bold"));
		font.setEncoding(new PjName("PDFDocEncoding"));
		
		// create a resources dictionary
		PjResources resources = new PjResources();
		// add ProcSet
		Vector procsetVector = new Vector();
		procsetVector.addElement(new PjName("PDF"));
		procsetVector.addElement(new PjName("Text"));
		resources.setProcSet(new PjProcSet(procsetVector));
		int resourcesId = pdf.registerObject(resources);
		
		// create a new page
		PjPage page = new PjPage();
		page.setResources( new PjReference(
			new PjNumber(resourcesId),
			PjNumber.ZERO ) );
		int pageId = pdf.registerObject(page);
		
		pdf.appendPage(pageId);
		int pageCount;
		pageCount = pdf.getPageCount();
		texts.setSize(pageCount + 1);
		vars.put("page", new Integer(pageCount).toString());
		return pageCount;
	}
/**
 * This method was created in VisualAge.
 */
public void convert() {
	String line = null;
	
	initVars();
	try {
		initPage(1);
		LineNumberReader reader = new LineNumberReader(
								  new InputStreamReader(inStream));
		while ((line = reader.readLine()) != null) {
			doDrawSplCtl(line);
		}
		doWritePdf(outStream);
	}
	catch (Exception e) {
		System.out.println("Error: " + e);
		System.out.println(" Data: " + line);
	}
}
/**
 * This method was created in VisualAge.
 * @return java.lang.String
 * @param text java.lang.String
 */
public String doAddEscapes(String text) {
	StringBuffer sb = new StringBuffer(text);
	int i = 0;
	while (i < sb.length()) {
		switch (sb.charAt(i)) {
			case '(':
			case ')':	sb.insert(i, '\\');
						i++;
		}
		i++;
	}
	return sb.toString();
}
	public void doAddPage() throws PjScriptException {
		good = true;
		int pageNumber;
		try {
			pageNumber = appendPage(pdf, texts, vars);
		}
		catch (PjException e) {
			throw new PjScriptException("PDF error: " + e.getMessage(),
						    lineNumber, source, 3);
		}
		initPage(pageNumber);
	}
	void doDrawSplCtl(String string) throws PjScriptException {
	
	 	int skip = 0;
		String skipString = string.substring(0,3);
		if (!skipString.equals("   "))
			skip = Integer.parseInt(skipString);
		if (skip != 0) {
		  if (skip < curLineNumber)
				doAddPage();
		  int count = skip - curLineNumber;
			for (int i = 0; i < count; i++) doNewline();
			curLineNumber = skip;
		}
		
	 	int space = 0;
		String spaceString = string.substring(3,4);
		if (!spaceString.equals(" "))
	  	space = Integer.parseInt(spaceString);
		if (space != 0) {
			for (int i = 0; i < space; i++) {
				doNewline();
				curLineNumber++;
			}
		}

		doDrawText(string.substring(4));
		
	}
  public void doDrawText(String text) throws PjScriptException {
		good = true;
		String page = (String)(vars.get("page"));
		String x = (String)(vars.get("x"));
		String y = (String)(vars.get("y"));
		String font = (String)(vars.get("font"));
		String fontsize = (String)(vars.get("fontsize"));
		int pageNumber = Integer.parseInt(page);
		StringBuffer sb = (StringBuffer)(texts.elementAt(pageNumber));
		if (sb == null) {
			sb = new StringBuffer();
			texts.setElementAt(sb, pageNumber);
		}
		sb.append("BT\n/Pj" + font + " " + fontsize + " Tf\n" + x + " " + y +
			  " Td\n(" + doAddEscapes(text) + ") Tj\nET\n");
		if (fonts.get(font) == null) {
			fonts.put(font, font);
		}
	}
public void doNewline() throws PjScriptException {
	good = true;
	vars.put("x", vars.get("xinit"));
	float y = new Float((String)(vars.get("y"))).floatValue() -
		new Float((String)(vars.get("fontsize"))).floatValue();
	if (y < new Float((String)(vars.get("ylimit"))).floatValue()) {
		doAddPage();
		vars.put("y", vars.get("yinit"));
	} else {
		vars.put("y", new PjNumber(y).toString());
	}
}
/**
 * This method was created in VisualAge.
 * @return 
 * @param out java.io.OutputStream
 */
public void doWritePdf(java.io.OutputStream out) throws PjScriptException {
	good = true;

	// draw texts on the PDF file
	int pageCount;
	try {
		pageCount = pdf.getPageCount();
	}
	catch (InvalidPdfObjectException e) {
		throw new PjScriptException("PDF error: " + e.getMessage(),
					    lineNumber, source, 3);
	}
	for (int x = 1; x <= pageCount; x++) {
		StringBuffer sb = (StringBuffer)(texts.elementAt(x));
		if (sb != null) {
			PjPage page;
			PjResources resources;
			try {
				page = (PjPage)(pdf.getObject(pdf.getPage(x)));
				
				// create a resources dictionary
				resources = (PjResources)(pdf.resolve(
					page.getResources()));
				if (resources == null) {
					Vector v = new Vector();
					v.addElement(PjName.PDF);
					v.addElement(PjName.TEXT);
					PjProcSet procSet = new PjProcSet(v);
					resources = new PjResources();
					resources.setProcSet(procSet);
					page.setResources(resources);
				}
			}
			catch (InvalidPdfObjectException e) {
				throw new PjScriptException("PDF error: " +
							    e.getMessage(),
							    lineNumber, source, 3);
			}
			
			// add Font
			PjDictionary fontDictionary;
			try {
				fontDictionary = (PjDictionary)(pdf.resolve(
					resources.getFont()));
			}
			catch (InvalidPdfObjectException e) {
				throw new PjScriptException("PDF error: " +
							    e.getMessage(),
							    lineNumber, source, 3);
			}
			if (fontDictionary == null) {
				fontDictionary = new PjDictionary();
				resources.setFont(fontDictionary);
			}
			Hashtable fontResHt = fontDictionary.getHashtable();
			// create font objects
			// I know this is not efficient, but we're not
			// talking about very much data
			for (Enumeration m = fonts.keys(); m.hasMoreElements();) {
				String name = (String)(m.nextElement());
				PjFontType1 font = new PjFontType1();
				font.setBaseFont(new PjName(name));
				font.setEncoding(new PjName("PDFDocEncoding"));
				int fontId = pdf.registerObject(font);
				fontResHt.put(new PjName("Pj" + name),
								      new PjReference(
								      new PjNumber(fontId)));
			}
			int resourcesId = pdf.registerObject(resources);
				// add text
			byte[] data = sb.toString().getBytes();
			try {
				PjStream stream =
					new PjStream(data).flateCompress();
				int streamId = pdf.registerObject(stream);
				pdf.addToPage(page, streamId);
			}
			catch (InvalidPdfObjectException e) {
				throw new PjScriptException(
					"PDF error: " + e.getMessage(),
					lineNumber, source, 3);
			}
		}
	}
	// write it out
	try {
		pdf.writeToStream(out);
	}
	catch (IOException ioe) {
		throw new PjScriptException("Unable to write PDF file.",
					    lineNumber, source, 3);
	}
}
	private static PjRectangle getMediaBoxArray(String mediaBox) {
		Vector v = new Vector();
		if (mediaBox.equalsIgnoreCase("legal-portrait")) {
			v.addElement(PjNumber.ZERO);
			v.addElement(PjNumber.ZERO);
			v.addElement(new PjNumber(612));
			v.addElement(new PjNumber(1008));
			return new PjRectangle(v);
		}
		if (mediaBox.equalsIgnoreCase("legal-landscape")) {
			v.addElement(PjNumber.ZERO);
			v.addElement(PjNumber.ZERO);
			v.addElement(new PjNumber(1008));
			v.addElement(new PjNumber(612));
			return new PjRectangle(v);
		}
		if (mediaBox.equalsIgnoreCase("letter-landscape")) {
			v.addElement(PjNumber.ZERO);
			v.addElement(PjNumber.ZERO);
			v.addElement(new PjNumber(792));
			v.addElement(new PjNumber(612));
			return new PjRectangle(v);
		}
		// default to letter-portrait
		v.addElement(PjNumber.ZERO);
		v.addElement(PjNumber.ZERO);
		v.addElement(new PjNumber(612));
		v.addElement(new PjNumber(792));
		return new PjRectangle(v);
	}
	void initPage(int pageNumber) throws PjScriptException {
		float yinit = setMediaBox(pdf, pageNumber, mediaBox, source, lineNumber);
		vars.put("yinit", new PjNumber(yinit).toString());
		vars.put("y", vars.get("yinit"));
		curLineNumber = 1;
	}
	public void initVars() {
	
		texts.setSize(2);
		vars.put("fontsize", fontSize);
		vars.put("font", "Courier");
		vars.put("linewidth", "1");
		vars.put("mediabox", "letter-portrait");
		vars.put("page", "1");
		vars.put("xinit", "72");
		vars.put("x", vars.get("xinit"));
		vars.put("x0", "0");
		vars.put("x1", "0");
		vars.put("yinit", "720");
		vars.put("y", vars.get("yinit"));
		vars.put("y0", "0");
		vars.put("y1", "0");
		vars.put("ylimit", "72");
	}
	public static void main(String[] argv) {
		//(new Spl2Pdf(argv[0], argv[1], argv[2])).convert();
		try {
		FileInputStream in = new FileInputStream("v:\\ddsclasses\\sd0120.txt");
		FileOutputStream out = new FileOutputStream("c:\\tmp\\GL0120pluta.pdf");
		(new Spl2Pdf(in, out)).convert();
		} catch (Exception e) {
			System.out.println("Exception e: "+e);
		}
	}
	// returns starting y value to be used as yinit
	private static float setMediaBox(Pdf pdf, int pageNumber, String mediaBox,
					String source, int lineNumber) throws PjScriptException {
		int pageId;
		try {
			pageId = pdf.getPage(pageNumber);
		}
		catch (IndexOutOfBoundsException a) {
			// pageNumber is assumed to be in bounds
			return -1;
		}
		catch (InvalidPdfObjectException b) {
			throw new PjScriptException("PDF error: " + b.getMessage(),
						    lineNumber, source, 3);
		}
		PjPage page = (PjPage)(pdf.getObject(pageId));
		PjRectangle rect = getMediaBoxArray(mediaBox);
		page.setMediaBox(rect);
		return ((PjNumber)(rect.getVector().lastElement())).getFloat() - 72;
	}
}