package datapro.eibs.generic;

import java.io.IOException;
import java.util.Iterator;
import java.util.List;

import org.jdom.Document;
import org.jdom.Element;
import org.jdom.JDOMException;
import org.jdom.input.SAXBuilder;

//  A Simple JDOM Application
public class JOJDOM {

	private Document document = null;
	
	public JOJDOM(String xmlFile) {

		try {
			SAXBuilder parser = new SAXBuilder();
			document = parser.build(xmlFile);

		} catch (VerifyError e) {
			System.err.println(e);
		} catch (IOException e) {
			System.err.println(e);
		} catch (JDOMException e) {
			System.err.println(e);
		}
	}
	
	public String getElementCode(String name) {
		List tbl = document.getRootElement().getChildren();
	
		String tblCode = "";
		String fldCode = "";
		if (tbl != null) {			
			Iterator iterTbl = tbl.iterator();
			while (iterTbl.hasNext()) {
				Element t = (Element) iterTbl.next();
				List fld = t.getChildren();
				if (fld != null) {
					Iterator iterFld = fld.iterator();
					while (iterFld.hasNext()) {
						Element f = (Element) iterFld.next();
						if (f.getName().equals("eIBSDataField")) {
							if (f.getAttributeValue("PublicName").equals(name)) {
								fldCode = f.getAttributeValue("eIBSCode");
								tblCode = t.getAttributeValue("eIBSCode");
								return tblCode + "_" + fldCode;
							}
						}
					}
				}
			}
		}
		return "";
	}

}