package datapro.eibs.tools.pdf;

import java.io.*;
import java.net.*;
import java.util.*;
import java.text.DecimalFormat;

import org.w3c.dom.*;

import datapro.eibs.sockets.*;
import datapro.eibs.master.*;

import datapro.eibs.beans.*;
import com.lowagie.text.*;
import com.lowagie.text.pdf.*;

import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.DocumentBuilder;
import org.xml.sax.SAXException;
import org.xml.sax.SAXParseException;

import com.lowagie.text.Document;
import com.lowagie.text.DocumentException;
import com.lowagie.text.Element;
import com.lowagie.text.Font;
import com.lowagie.text.FontFactory;
import com.lowagie.text.PageSize;
import com.lowagie.text.Paragraph;
import com.lowagie.text.pdf.BaseFont;
import com.lowagie.text.pdf.PdfContentByte;
import com.lowagie.text.pdf.PdfPTable;
import com.lowagie.text.pdf.PdfPCell;
import com.lowagie.text.pdf.PdfWriter;

/**
 * Class to hold the Document to be print in a PDF format from an XML document.
 */
	public class XMLPDFDocument {
		
		private static String sClassesRoot = "";
		
		private static org.w3c.dom.Document myXMLDoc = null;  
		private static Document myPDFDoc = null;
		private static PdfContentByte cb = null;

		float pagewidth = 0;
		private BaseFont bf = null;

		public XMLPDFDocument() throws IOException, DocumentException{
			sClassesRoot = new LocateMe().getClassesRoot();
			
			myPDFDoc = new Document(PageSize.A4, 20, 20, 20, 20);
			myPDFDoc.newPage();
			pagewidth = myPDFDoc.getPageSize().width();
			bf = BaseFont.createFont(BaseFont.HELVETICA, BaseFont.CP1252, BaseFont.NOT_EMBEDDED);
		}

		public XMLPDFDocument(String ClassesRoot) throws IOException, DocumentException {
			if (ClassesRoot.lastIndexOf('/') != (ClassesRoot.length() - 1)) ClassesRoot = ClassesRoot + "/"; 	
			sClassesRoot = ClassesRoot;
			
			myPDFDoc = new Document(PageSize.A4, 20, 20, 20, 20);
			pagewidth = myPDFDoc.getPageSize().width();
			bf = BaseFont.createFont(BaseFont.TIMES_ROMAN, BaseFont.CP1252, BaseFont.NOT_EMBEDDED);			
		}
		
		public void readXMLDoc(String FilePath, String FileName) {
				org.w3c.dom.Document doc = null;
				if (FilePath.equals("")) {
					System.out.println("XMLPDFDocument no file Path");
				} else {
					if (FilePath.lastIndexOf('/') != (FilePath.length() - 1)) FilePath = FilePath + "/";
				} 	
				if (!sClassesRoot.equals("")) FilePath = sClassesRoot + FilePath;
				
				setXMLDoc(FilePath + FileName);
		} //end of function
			
		public void readXMLDoc(String FilePath) {
				org.w3c.dom.Document doc = null;

				if (FilePath.equals("")) {
					System.out.println("XMLPDFDocument no file Path");
				} else {
					System.out.println("FilePath - " + FilePath);
				}
				if (!sClassesRoot.equals("")) FilePath = sClassesRoot + FilePath;

				setXMLDoc(FilePath);	
		} //end of function
		
		private static void setXMLDoc (String FullFileName){
			org.w3c.dom.Document doc = null;
			try {
				DocumentBuilderFactory docBuilderFactory = DocumentBuilderFactory.newInstance();
				DocumentBuilder docBuilder = docBuilderFactory.newDocumentBuilder();
				myXMLDoc = docBuilder.parse(new File(FullFileName));

				// normalize text representation
				myXMLDoc.getDocumentElement().normalize();

			} catch (SAXParseException err) {
				System.out.println(
						"** Parsing error"
						+ ", line "
						+ err.getLineNumber()
						+ ", uri "
						+ err.getSystemId());
				System.out.println(" " + err.getMessage());

			} catch (SAXException e) {
				Exception x = e.getException();
				((x == null) ? e : x).printStackTrace();

			} catch (Throwable t) {
				t.printStackTrace();
			}
		} //end of function

		public Document getPDFDoc(){
			return myPDFDoc;			
		}

		private boolean ifElementProcess(
			org.w3c.dom.Node ifNode, 
			ESS0030DSMessage user, 
			MessageRecord msg) {

			String param1Text = "";
			String param2Text = "";	
			String operandText = "";	 

			org.w3c.dom.Node param1 = ((org.w3c.dom.Element)ifNode).getElementsByTagName("param1").item(0);
			if (getTextNode((org.w3c.dom.Element)param1).equals("field")) {
				org.w3c.dom.Node field1 = ((org.w3c.dom.Element)ifNode).getElementsByTagName("text").item(0);
				param1Text = readXMLElementText(field1, user, msg).getText();
			} else {
				param1Text = getTextNode((org.w3c.dom.Element)param1);
			}
		
			org.w3c.dom.Node operand = ((org.w3c.dom.Element)ifNode).getElementsByTagName("operand").item(0);
			operandText = getTextNode((org.w3c.dom.Element)operand);
		
			org.w3c.dom.Node param2 = ((org.w3c.dom.Element)ifNode).getElementsByTagName("param2").item(0);
			if (getTextNode((org.w3c.dom.Element)param2).equals("field")) {
				org.w3c.dom.Node field2 = ((org.w3c.dom.Element)ifNode).getElementsByTagName("text").item(1);
				param2Text = readXMLElementText(field2, user, msg).getText();
			} else {
				param2Text = getTextNode((org.w3c.dom.Element)param2);
			}
		
			if (operandText.equals("==")){
				if (param1Text.equals(param2Text)) return true;
			} else if (operandText.equals("!=")){
				if (!param1Text.equals(param2Text)) return true;			
			} else if (operandText.equals(">")){
				if (Util.parseCCYtoDouble(param1Text) > Util.parseCCYtoDouble(param2Text)) return true;
			} else if (operandText.equals(">=")){
				if (Util.parseCCYtoDouble(param1Text) >= Util.parseCCYtoDouble(param2Text)) return true;
			} else if (operandText.equals("<")){
				if (Util.parseCCYtoDouble(param1Text) < Util.parseCCYtoDouble(param2Text)) return true;
			} else if (operandText.equals("<=")){
				if (Util.parseCCYtoDouble(param1Text) <= Util.parseCCYtoDouble(param2Text)) return true;
			}
			return false;	
		}
		
		private String opElementProcess(
			org.w3c.dom.Node opNode,
			ESS0030DSMessage user, 
			MessageRecord msg){

			org.w3c.dom.Element childElement = null;

			String operand1Text = "";
			String operand2Text = "";	
			String operatorText = "";

			if (opNode.getNodeType() == Node.ELEMENT_NODE) {
				org.w3c.dom.Element operationElement = (org.w3c.dom.Element) opNode;
				org.w3c.dom.NodeList operationElementChildList = operationElement.getChildNodes();
				for (int i = 0; i < operationElementChildList.getLength(); i++) {
					org.w3c.dom.Node childNode = operationElementChildList.item(i);
					String temp = "";
					if (childNode.getNodeType() == Node.ELEMENT_NODE){
						childElement = (org.w3c.dom.Element) childNode;
						if (childElement.getTagName().equals("operand")){
							operand1Text = readXMLElementText(childElement, user, msg).getValue();
							do {
								i++;
								try {
									childNode = operationElementChildList.item(i);
									if (childNode.getNodeType() == Node.ELEMENT_NODE)
										childElement = (org.w3c.dom.Element) childNode;
								} catch (Exception e){
								}
							} while (!childElement.getTagName().equals("operator")) ;
							operatorText = getTextNode(childElement);
							if (operationElementChildList.getLength() > i){
								do {
									i++;
									try {
										childNode = operationElementChildList.item(i);
										if (childNode.getNodeType() == Node.ELEMENT_NODE)
											childElement = (org.w3c.dom.Element) childNode;
									} catch (Exception e){
									}
								} while (!childElement.getTagName().equals("operand")) ;
								operand2Text = readXMLElementText(childElement, user, msg).getValue();
							}
						}
						if (operatorText.equals("==")){
							if (operand1Text.trim().equals(operand2Text.trim())) operand2Text = "true"; else operand2Text = "false";
						} else if (operatorText.equals("!=")){
							if (!operand1Text.trim().equals(operand2Text.trim())) operand2Text = "true"; else operand2Text = "false";			
						} else if (operatorText.equals(">")){
							if (Util.parseCCYtoDouble(operand1Text) > Util.parseCCYtoDouble(operand2Text)) operand2Text = "true"; else operand2Text = "false";
						} else if (operatorText.equals(">=")){
							if (Util.parseCCYtoDouble(operand1Text) >= Util.parseCCYtoDouble(operand2Text)) operand2Text = "true"; else operand2Text = "false";
						} else if (operatorText.equals("<")){
							if (Util.parseCCYtoDouble(operand1Text) < Util.parseCCYtoDouble(operand2Text)) operand2Text = "true"; else operand2Text = "false";
						} else if (operatorText.equals("<=")){
							if (Util.parseCCYtoDouble(operand1Text) <= Util.parseCCYtoDouble(operand2Text)) operand2Text = "true"; else operand2Text = "false";
						}
						if (!operand1Text.equals("") && !operand2Text.equals("")) {
							if (operatorText.equals("+")){
								operand2Text = Util.formatCCY(
									Util.parseCCYtoDouble(operand1Text) +
									Util.parseCCYtoDouble(operand2Text));
							} else if (operatorText.equals("-")){
								operand2Text = Util.formatCCY(
									Util.parseCCYtoDouble(operand1Text) -
									Util.parseCCYtoDouble(operand2Text));
							} else if (operatorText.equals("*")){
								operand2Text = Util.formatCCY(
									Util.parseCCYtoDouble(operand1Text) *
									Util.parseCCYtoDouble(operand2Text));
							} else if (operatorText.equals("/")){
								operand2Text = Util.formatCCY(
									Util.parseCCYtoDouble(operand1Text) /
									Util.parseCCYtoDouble(operand2Text));
							} else if (operatorText.equals("%")){
								operand2Text = Util.formatCCY(
									Util.parseCCYtoDouble(operand1Text) %
									Util.parseCCYtoDouble(operand2Text));
							}
						} 
					}
				}
			}
			return operand2Text;	
		}		

		public org.w3c.dom.NodeList readXMLElementList(String elementTagName, String items) {

			org.w3c.dom.NodeList elementNodeList = myXMLDoc.getElementsByTagName(elementTagName);
			org.w3c.dom.Element elementNode = (org.w3c.dom.Element)elementNodeList.item(0);
			return elementNode.getElementsByTagName(items);
		} //end of function

		public String getTextNode(org.w3c.dom.Element ele) {
			String retText = "";
			try {
				if(ele.getChildNodes() != null) {
					NodeList textNameList = ele.getChildNodes();
					retText = textNameList.item(0).getNodeValue();
				}

			}
			catch(Exception e) {
				if (ele.getTagName().equals("literal")){ 
					retText = " ";
					System.out.println("Node text is \""+retText+ "\"" );
					return retText;
				}
				else return "";
			}
			return retText;
		}

		public XMLPDFElement readXMLElementText(
			org.w3c.dom.Node textNode, 
			ESS0030DSMessage user, 
			MessageRecord msg) {

			org.w3c.dom.Element childElement = null;
			XMLPDFElement elementText = new XMLPDFElement();
			String elementField = "";
			MessageField field = null;

			try {
				if (textNode.getNodeType() == Node.ELEMENT_NODE) {
					org.w3c.dom.Element textElement = (org.w3c.dom.Element) textNode;
					org.w3c.dom.NodeList textElementChildList = textElement.getChildNodes();
					elementText.setType(textElement.getAttribute("type"));
					if (elementText.getType().equals("date")){
							String d1 = "";
							String d2 = "";
							String d3 = "";
							for (int i = 0; i < textElementChildList.getLength(); i++) {
								org.w3c.dom.Node childNode = textElementChildList.item(i);
								String temp = "";
								if (childNode.getNodeType() == Node.ELEMENT_NODE){
									childElement = (org.w3c.dom.Element) childNode;
									if (childElement.getTagName().equals("if")){
										if (opElementProcess(childNode, user, msg).equals("false")){
											do {
												i++;
												try {
													childNode = textElementChildList.item(i);
													if (childNode.getNodeType() == Node.ELEMENT_NODE)
														childElement = (org.w3c.dom.Element) childNode;
												} catch (Exception e){
												}
											} while (!childElement.getTagName().equals("endif")) ;
										}
									} else if (childElement.getTagName().equals("format")){
										elementText.setDateFormat(getTextNode(childElement));										
									} else if (childElement.getTagName().equals("field")){
										try {
											elementField = getTextNode(childElement);
											field = msg.getField(elementField);
											temp = field.getString();
										} catch (Exception e){
											System.out.println("Error getting field : " + elementField);
										}
									} else if (childElement.getTagName().equals("format")){
										temp = getTextNode(childElement);
									}								
									if (!d1.equals("") && !d2.equals("") && d3.equals("") ) d3 = temp ;
									if (!d1.equals("") && d2.equals("")) d2 = temp;
									if (d1.equals("")) d1 = temp;
								}
							}
							elementText.setDate(d1,d2,d3);
					} else if (elementText.getType().equals("amount") || elementText.getType().equals("fullamount")){
							for (int i = 0; i < textElementChildList.getLength(); i++) {
								org.w3c.dom.Node childNode = textElementChildList.item(i);
								if (childNode.getNodeType() == Node.ELEMENT_NODE){
									childElement = (org.w3c.dom.Element) childNode;
									if (childElement.getTagName().equals("operation")){
										elementText.setText(elementText.getText() + opElementProcess(childNode, user, msg));
									} else if (childElement.getTagName().equals("if")){
										if (opElementProcess(childNode, user, msg).equals("false")){
											do {
												i++;
												try {
													childNode = textElementChildList.item(i);
													if (childNode.getNodeType() == Node.ELEMENT_NODE)
														childElement = (org.w3c.dom.Element) childNode;
												} catch (Exception e){
												}										
											} while (!childElement.getTagName().equals("endif")) ;
										}
									} else if (childElement.getTagName().equals("literal")){
										elementText.setText(elementText.getText() + getTextNode(childElement));										
									} else if (childElement.getTagName().equals("symbol")){
										elementText.setAmountSymbol(getTextNode(childElement));
									} else if (childElement.getTagName().equals("length")){
										elementText.setLength(Integer.parseInt(getTextNode(childElement)));										
									} else if (childElement.getTagName().equals("field")){
										try {
											elementField = getTextNode(childElement);
											field = msg.getField(elementField);
											elementText.setText(field.getString());
										} catch (Exception e){
											System.out.println("Error getting field : " + elementField);
										}
									}
								}
							}
					} else {
						for (int i = 0; i < textElementChildList.getLength(); i++) {
							org.w3c.dom.Node childNode = textElementChildList.item(i);
							if (childNode.getNodeType() == Node.ELEMENT_NODE){
								childElement = (org.w3c.dom.Element) childNode;
								if (childElement.getTagName().equals("operation")){
									elementText.setText(elementText.getText() + opElementProcess(childNode, user, msg));
								} else if (childElement.getTagName().equals("if")){
									if (opElementProcess(childNode, user, msg).equals("false")){
										do {
											i++;
											try {
												childNode = textElementChildList.item(i);
												if (childNode.getNodeType() == Node.ELEMENT_NODE)
													childElement = (org.w3c.dom.Element) childNode;
											} catch (Exception e){
											}									
										} while (!childElement.getTagName().equals("endif")) ;
									}
								} else if (childElement.getTagName().equals("literal")){
									elementText.setText(elementText.getText() + getTextNode(childElement));
								} else if (childElement.getTagName().equals("length")){
									elementText.setLength(Integer.parseInt(getTextNode(childElement)));										
								} else if (childElement.getTagName().equals("offset")){
									elementText.setOffset(Integer.parseInt(getTextNode(childElement)));									
								} else if (childElement.getTagName().equals("field")){
	 							    try {
								    	elementField = getTextNode(childElement);
										field = msg.getField(elementField);
										String fieldValue=field.getString();
										if (childElement.getAttribute("nochar")!=null && !childElement.getAttribute("nochar").equals("")){
									        char caracter = childElement.getAttribute("nochar").charAt(0);
											fieldValue=fieldValue.replace(caracter,' ');
										}
										elementText.setText(elementText.getText() +  fieldValue);	
									} catch (Exception e){
										System.out.println("Error getting field : " + elementField);
									}
								}
							}
						}
					} 					
					if (textElement.getAttribute("size") != null && !textElement.getAttribute("size").equals("")) 
						elementText.setSize(Float.parseFloat(textElement.getAttribute("size")));
					if (textElement.getAttribute("x") != null && !textElement.getAttribute("x").equals("")) 
						elementText.setX(Float.parseFloat(textElement.getAttribute("x")));
					if (textElement.getAttribute("y") != null && !textElement.getAttribute("y").equals("")) 
						elementText.setY(Float.parseFloat(textElement.getAttribute("y")));
					if (textElement.getAttribute("align") != null && !textElement.getAttribute("align").equals("")){
						try {
							elementText.setAlign(Integer.parseInt(textElement.getAttribute("align")));	
						} catch (Exception e){
							elementText.setAlign(textElement.getAttribute("align"));
						}
					}
					if (textElement.getAttribute("length") != null && !textElement.getAttribute("length").equals("")) 
						elementText.setLength(Integer.parseInt(textElement.getAttribute("length")));
					if (textElement.getAttribute("offset") != null && !textElement.getAttribute("offset").equals("")) 
						elementText.setOffset(Integer.parseInt(textElement.getAttribute("offset")));
					if (textElement.getAttribute("fill") != null && !textElement.getAttribute("fill").equals("")) 
						elementText.setToFill(Boolean.valueOf(textElement.getAttribute("fill")).booleanValue());
				}
			} catch (Exception e){
				e.printStackTrace();
				throw new RuntimeException("Error Mapping XML file");
			}
			return elementText;
	}
	
	public org.w3c.dom.Element getPDFPage (
		int numPage){
		
		org.w3c.dom.Element elementPage = null;
		org.w3c.dom.NodeList elementPageList = myXMLDoc.getElementsByTagName("page");
		elementPage = (org.w3c.dom.Element) elementPageList.item(numPage);
		
		return elementPage;
	}	

	public void setPDFDocumentHeader (){

		org.w3c.dom.NodeList elementTitleList = myXMLDoc.getElementsByTagName("title");
		org.w3c.dom.Element elementTitle = (org.w3c.dom.Element) elementTitleList.item(0);

		myPDFDoc.addAuthor("eIBS");
		myPDFDoc.addCreationDate();
		myPDFDoc.addProducer();
		myPDFDoc.addTitle(getTextNode(elementTitle));
		myPDFDoc.addKeywords("pdf, itext, Java, open source, http");

	}	

	public void setPDFcb(
		PdfWriter docWriter, 
		JBObjList origObjTable,
		ESS0030DSMessage user, 
		MessageRecord msg){
			
		cb = docWriter.getDirectContent();
		
		String lang = (user.getE01LAN().equals("s")) ? "es" : "en";
		org.w3c.dom.NodeList contentList = readXMLElementList("content", "text");
		
		try {
			cb.beginText();
			for (int i = 0; i < contentList.getLength(); i++) {
				XMLPDFElement myElement = readXMLElementText(contentList.item(i), user, msg);

				cb.setFontAndSize(bf, myElement.getSize());
				if (myElement.getAlign() != -1)	{
					if (myElement.getType().equals("date")) 
					cb.showTextAligned(myElement.getAlign(), myElement.getCustomFormatDate(user, lang), pagewidth / 2, myElement.getY(), 0);
					else if (myElement.getType().equals("amount")) 
						cb.showTextAligned(myElement.getAlign(), myElement.getFullAmount(), pagewidth / 2, myElement.getY(), 0);
					else cb.showTextAligned(myElement.getAlign(), myElement.getText(), pagewidth / 2, myElement.getY(), 0); 	
				} else {
					cb.setTextMatrix(myElement.getX(), myElement.getY());
					if (myElement.getType().equals("date")) cb.showText(myElement.getCustomFormatDate(user, lang));
					else if (myElement.getType().equals("amount")) cb.showText(myElement.getFullAmount());
					else cb.showText(myElement.getText());
				}
			}
			cb.endText();

			//org.w3c.dom.NodeList elementTableList = readXMLElementList("tables");
			org.w3c.dom.NodeList elementTableList = myXMLDoc.getElementsByTagName("table");
			for (int i=0; i < elementTableList.getLength(); i++){
			  org.w3c.dom.Element elementTable = (org.w3c.dom.Element) elementTableList.item(i);
			  setPDFTable(1, elementTable, origObjTable, user, msg);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void setPDFTable (
		int numPage,
		org.w3c.dom.Element elementTable,
		JBObjList origObjTable,
		ESS0030DSMessage user, 
		MessageRecord msg) 
		throws DocumentException {
		
		boolean hasHeader = false;
		int iRow = 0;
		Font normalFont = FontFactory.getFont(FontFactory.HELVETICA, 7, Font.NORMAL);
		Font normalBoldFont = FontFactory.getFont(FontFactory.HELVETICA, 8, Font.BOLD);
		Font normalBoldUnderFont = FontFactory.getFont(FontFactory.HELVETICA, 8, Font.BOLD | Font.UNDERLINE);
		Font headerFont = FontFactory.getFont(FontFactory.HELVETICA, 10, Font.NORMAL);
		Font headerBoldFont = FontFactory.getFont(FontFactory.HELVETICA, 8, Font.BOLD);
		Font headerBoldUnderFont = FontFactory.getFont(FontFactory.HELVETICA, 10, Font.BOLD | Font.UNDERLINE);
		String lang = (user.getE01LAN().equals("s")) ? "es" : "en";
		try {
			int NumColumns = Integer.parseInt(elementTable.getAttribute("columns"));
			float headerwidths[] = new float[NumColumns];
			PdfPTable datatable = new PdfPTable(NumColumns);
		
			org.w3c.dom.NodeList rowElementList = elementTable.getElementsByTagName("row");
			org.w3c.dom.Element elementRow = (org.w3c.dom.Element) rowElementList.item(0);		
			org.w3c.dom.NodeList rowElementCellsList = elementRow.getElementsByTagName("cell");
		
			// table header
			if (elementRow.getAttribute("type").equals("header"))
			{ 
				hasHeader = true;
				datatable.getDefaultCell().setPadding(3);
				datatable.getDefaultCell().setBorderWidth(1);
				datatable.getDefaultCell().setHorizontalAlignment(Element.ALIGN_CENTER);
				datatable.getDefaultCell().setGrayFill(0.9f);
	
				// add header Cells
				for (int i = 0; i < rowElementCellsList.getLength(); i++) {
					org.w3c.dom.Node headerNode = rowElementCellsList.item(i);
					org.w3c.dom.Element headerElement = (org.w3c.dom.Element) headerNode;				
					headerwidths[i] = Integer.parseInt(headerElement.getAttribute("width"));

					XMLPDFElement header = readXMLElementText(headerNode, user, msg);
				
					if (header != null) {
						headerFont.setSize(header.getSize());
						datatable.addCell(new Paragraph(header.getText(), headerBoldFont));
					} 
				}
				datatable.setHeaderRows(1);

				iRow++;
				elementRow = (org.w3c.dom.Element) rowElementList.item(iRow);
				rowElementCellsList = elementRow.getElementsByTagName("cell");
			}
			//	this is the end of the table header

			datatable.getDefaultCell().setBorderWidth(0);
			datatable.getDefaultCell().setHorizontalAlignment(Element.ALIGN_CENTER);		
			datatable.getDefaultCell().setGrayFill(0.0f);
	
			origObjTable.initRow();

			for (int i = 0; i < rowElementCellsList.getLength(); i++) {
				// add row Cells
				org.w3c.dom.Node cellNode = rowElementCellsList.item(i);
				if (elementRow.getAttribute("type").equals("dynamic")) {
					while (origObjTable.getNextRow()) {
						MessageRecord msgList = (MessageRecord) origObjTable.getRecord();
						
						XMLPDFElement detail = readXMLElementText(cellNode, user, msgList);
		
						if (detail != null) {
							datatable.getDefaultCell().setHorizontalAlignment(detail.getAlign());
							normalFont.setSize(detail.getSize());
							datatable.addCell(new Paragraph(detail.getText(), normalFont));
						} 					
					}								
				}
				if (elementRow.getAttribute("type").equals("static")) {
					XMLPDFElement detail = readXMLElementText(cellNode, user, msg);
		
					if (detail != null) {
						datatable.getDefaultCell().setHorizontalAlignment(detail.getAlign());
						normalFont.setSize(detail.getSize());
						//if (myElement.getType().equals("date")) cb.showText(myElement.getCustomFormatDate(user, lang));
						String texto ="";
						if (detail.getType().equals("date"))
						  texto=detail.getCustomFormatDate(user, lang);
						else
						  texto=detail.getText(); 
						//cb.showTextAligned(myElement.getAlign(), myElement.getCustomFormatDate(user, lang), pagewidth / 2, myElement.getY(), 0);
					    PdfPCell celda = new PdfPCell(new Phrase(texto.trim(),normalFont));
					    if (detail.getAlign()== 0)
						  celda.setHorizontalAlignment(Element.ALIGN_LEFT);
						else if (detail.getAlign()== 2)
						  celda.setHorizontalAlignment(Element.ALIGN_RIGHT);
						else
						  celda.setHorizontalAlignment(Element.ALIGN_CENTER);  
						    
					    celda.setBorderWidth(0f);
					    celda.setNoWrap(false);
						//datatable.addCell(new Paragraph(texto.trim(), normalFont));
						datatable.addCell(celda);
						
					} 
				}
			}

			float tableWidth = 0;
			try {
				String sWidth = elementTable.getAttribute("width");
				if (sWidth.endsWith("%")){  // percentage	
					sWidth = sWidth.substring(0, sWidth.length() - 1);
					tableWidth = Integer.parseInt(sWidth);
					if (tableWidth == 0) {
						datatable.setWidthPercentage(headerwidths, myPDFDoc.getPageSize());
					} else {
						datatable.setWidthPercentage(tableWidth);
					}					
				} else {
					if (hasHeader == true){
						datatable.setWidths(headerwidths);
						datatable.setTotalWidth(headerwidths);
					} else {
						tableWidth = Integer.parseInt(sWidth);
						datatable.setTotalWidth(tableWidth);
					}
				}
			} catch (Exception e) {
				datatable.setWidthPercentage(100);
			}
			int xPos = Integer.parseInt(elementTable.getAttribute("x"));
			int yPos = Integer.parseInt(elementTable.getAttribute("y"));
			datatable.writeSelectedRows(0, -1, xPos, yPos, cb);
			/**
			public float writeSelectedRows(int rowStart, int rowEnd, float xPos, float yPos, PdfContentByte canvas); 
			The parameter rowStart is the number of the row you want to start with, 
			rowEnd is the last row you want to show (if you want to show all the rows, use -1).
			xPos and yPos are the coordinates of the table.
			**/
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}