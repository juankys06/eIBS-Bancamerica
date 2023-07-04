package datapro.eibs.master;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;
import org.xml.sax.SAXParseException;

import java.io.File;

public class XMLWrapper {
	
	private String serverConfPath = "";
	private Document doc = null;
	
	public XMLWrapper(String fileName) {
		serverConfPath = new LocateMe().getClassesRoot();
		try {
			DocumentBuilderFactory docBuilderFactory = DocumentBuilderFactory.newInstance();
			DocumentBuilder docBuilder = docBuilderFactory.newDocumentBuilder();

			doc = docBuilder.parse(new File(serverConfPath + fileName));
			if (doc == null) {
				System.out.println("Failed to load xml file. Be sure " + fileName +
							" is located correctly.");
			} else {
				System.out.println("xml file loaded correctly.");
				doc.getDocumentElement().normalize();
			}
			
		} catch(SAXParseException err) {
			System.out.println("Parsing error, line " + err.getLineNumber() + ", uri " + err.getSystemId());
			System.out.println("Exception " + err.getMessage());
		}
		catch(SAXException e) {
			Exception x = e.getException();
			((Throwable) (x != null ? x : e)).printStackTrace();
		}
		catch(Throwable t) {
			t.printStackTrace();
		}
	}
	
	public Element getDocumentElement() {

		Element elem = doc.getDocumentElement();
		return elem;
				
	}
	
	public Element getElementById(String id) {

		return doc.getElementById(id);
	}
	
	public Element getElementByAttributeValue(String elementName, String attributeName, String attributeValue) {

		Element elem = null;
		NodeList nodeList = doc.getElementsByTagName(elementName);
		for (int i = 0; i < nodeList.getLength(); i++) {
			Node node = nodeList.item(i);
			if (node.getNodeType() == Node.ELEMENT_NODE){
				elem = (Element) node;
				if (elem.getAttribute(attributeName).equals(attributeValue)){
					return elem;
				}
			}
		}
		return null;
	}
	
	public Element getElementByAttributeValue(Element parentElem, String attributeName, String attributeValue) {

		Element elem = null;
		NodeList nodeList = parentElem.getChildNodes();
		for (int i = 0; i < nodeList.getLength(); i++) {
			Node node = nodeList.item(i);
			if (node.getNodeType() == Node.ELEMENT_NODE){
				elem = (Element) node;
				if (elem.getAttribute(attributeName).equals(attributeValue)){
					return elem;
				}
			}
		}
		return null;
	}
	
	public Element getElementByTagName(String name, int index) {

		NodeList nodeList = doc.getElementsByTagName(name);
		Node node = nodeList.item(index) ;
		Element elem = (Element)node;
		return elem;
	}
	
	public String getAttributeValueFromElement(String elementTagName, String attributeName) {

		Element elem = getElementByTagName(elementTagName, 0);
		return elem.getAttribute(attributeName);
				
	}
	
	public Element getParentElement(Element elem) {

		Node node = elem.getParentNode();
		if (node.getNodeType() == Node.ELEMENT_NODE){
			return (Element) node;
		}
		return null;
				
	}
	
	public String getTextNode(String name, Element ele) {
		String retcad = "";
		try {
			NodeList NameList = ele.getElementsByTagName(name);
			Element NameElement = (Element) NameList.item(0);
			if (NameElement.getChildNodes() != null) {
				NodeList textNameList = NameElement.getChildNodes();
				retcad =
					((Node) textNameList.item(0)).getNodeValue().trim();
			} else {
				retcad = ((Node) NameList.item(0)).getNodeValue().trim();
			}
		} catch (Exception e) {
			return "";
		}
		return retcad;
	}

	/**
	 * @return
	 */
	public Document getDocument() {
		return doc;
	}

}