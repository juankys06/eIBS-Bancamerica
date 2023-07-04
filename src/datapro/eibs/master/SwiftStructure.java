/*
 * Created on May 15, 2008
 *
 * To change the template for this generated file go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
package datapro.eibs.master;

import java.io.File;
import java.io.FileNotFoundException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import com.datapro.exception.DocumentException;
import com.datapro.xml.DOMUtil;
import com.datapro.xml.DOMWrapper;

/**
 * @author erodriguez
 *
 * To change the template for this generated type comment go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
public class SwiftStructure {
	
	private static DOMWrapper xmlfile = null;
	private Element eibsFieldElement = null;
	
	static {
		String serverConfPath = new LocateMe().getClassesRoot();
		try {
			xmlfile = new DOMWrapper(new File(serverConfPath, "swift.xml"));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (DocumentException e) {
			e.printStackTrace();
		}		
	}
	
	public boolean locateField(String field) {
		String id = "id" + field;
		eibsFieldElement = xmlfile.getDocument().getElementById(id);
		return (eibsFieldElement != null);
	}
	
	public String getLabel(String lang) {
		return DOMUtil.getTextNode(lang, eibsFieldElement);
	}
	
	public int getLines() {
		return Integer.parseInt(eibsFieldElement.getAttribute("Lines"));
	}
	
	public int getChars() {
		return Integer.parseInt(eibsFieldElement.getAttribute("Chars"));
	}

}
