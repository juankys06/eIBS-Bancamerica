package datapro.eibs.generic;

import java.io.IOException;

import org.apache.xerces.parsers.DOMParser;
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

//  A Simple DOM Application
public class JODOM {

	private org.w3c.dom.Document document = null;
	// Constructor
	public JODOM(String xmlFile) {
		//  Create a Xerces DOM Parser
		//  Parse the Document		
		//  and traverse the DOM
		try {
			org.apache.xerces.parsers.DOMParser parser = new org.apache.xerces.parsers.DOMParser();
			parser.parse(xmlFile);
			document = parser.getDocument();
			// traverse (document);
		} catch (VerifyError e) {
			System.err.println(e);
		} catch (SAXException e) {
			System.err.println(e);
		} catch (IOException e) {
			System.err.println(e);
		}
	}

	public String getElementCode(String name) {
		NodeList doc = document.getChildNodes();
		Node d = doc.item(0);
		NodeList tbl = d.getChildNodes();
		// NodeList tbl = document.getChildNodes();
		String tblCode = "";
		String fldCode = "";
		if (tbl != null) {
			for (int i = 0; i < tbl.getLength(); i++) {
				Node t = tbl.item(i);
				NodeList fld = t.getChildNodes();
				if (fld != null) {
					for (int j = 0; j < fld.getLength(); j++) {
						Node f = fld.item(j);
						if (f.getNodeName().equals("eIBSDataField")) {
							if (f.getAttributes().getNamedItem("PublicName").getNodeValue().equals(name)) {
								fldCode = f.getAttributes().getNamedItem("eIBSCode").getNodeValue();
								tblCode = t.getAttributes().getNamedItem("eIBSCode").getNodeValue();
								//System.out.println(name + ": " + tblCode + "_" + fldCode);
								return tblCode + "_" + fldCode;
							}
						}
					}
				}
			}
		}
		return "";

	}

	//  Traverse DOM Tree.  Print out Element Names
	private void traverse(Node node) {
		int type = node.getNodeType();
		if (type == Node.ELEMENT_NODE)
			System.out.println(node.getNodeName());
		NodeList children = node.getChildNodes();
		if (children != null) {
			for (int i = 0; i < children.getLength(); i++)
				traverse(children.item(i));
		}
	}

	// Main Method
	public static void main(String[] args) {
		JODOM joDOM =
			new JODOM("http://nt_srvdev:43000/eIBS_R04M07/ftp/eibsform/config/eIBSForms_DST.xml");
		System.out.println(
			"OfficialCheck.Address3 - " + joDOM.getElementCode("OfficialCheck.Address3"));
		System.out.println(
			"BankRef.FlagThird.One - " + joDOM.getElementCode("BankRef.FlagThird.One"));

	}
}