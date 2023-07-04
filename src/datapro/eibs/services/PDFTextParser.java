/*
 * Created on Feb 23, 2010
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package datapro.eibs.services;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.pdfbox.cos.COSDocument;
import org.apache.pdfbox.pdfparser.PDFParser;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDDocumentInformation;
import org.apache.pdfbox.util.PDFTextStripper;
import org.apache.pdfbox.util.Splitter;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRichTextString;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;

import com.datapro.generic.tool.UDecimalFormat;

import datapro.eibs.beans.DataPatterns;

/**
 * @author Catalina
 * 
 * TODO To change the template for this generated type comment go to Window -
 * Preferences - Java - Code Style - Code Templates
 */
public class PDFTextParser {

	private static final String CURRENCY_NAME = "MDA";

	private static final char SPACE = '0';

	private static final char SYMBOL = '1';

	private static final char LETTER = '2';

	private static final char DIGIT = '3';

	private static final char DOT_COLON = '4';
	
	private int[][] automata = new int[21][5];
	
	String firstCountedLine = "";

	PDFParser parser;

	String parsedText;

	PDFTextStripper pdfStripper;

	PDDocument pdDoc;

	PDDocument page;

	COSDocument cosDoc;

	PDDocumentInformation pdDocInfo;
	
	Map resultingPatterns;
	
	


	/**
	 * PDFTextParser Constructor
	 *  
	 */
	public PDFTextParser() {
		for (int i = 0; i < 20; i++) {
			for (int j = 0; j < 5; j++) {
				automata[i][j] = -1;
			}
		}

		automata[0][0] = 0;
		automata[0][1] = 9;
		automata[0][2] = 14;
		automata[0][3] = 1;
		automata[0][4] = 9;
		automata[1][0] = 12;
		automata[1][1] = 9;
		automata[1][2] = 2;
		automata[1][3] = 7;
		automata[1][4] = 7;
		automata[2][0] = 3;
		automata[4][0] = 5;
		automata[4][1] = 18;
		automata[4][2] = 4;
		automata[4][4] = 4;
		automata[5][0] = 6;
		automata[5][2] = 4;
		automata[7][0] = 3;
		automata[7][1] = 9;
		automata[7][3] = 7;
		automata[7][4] = 19;
		automata[8][0] = 3;
		automata[8][2] = 4;
		automata[9][0] = 10;
		automata[9][1] = 9;
		automata[9][2] = 17;
		automata[9][3] = 11;
		automata[11][0] = 13;
		automata[11][1] = 9;
		automata[11][3] = 11;
		automata[14][0] = 8;
		automata[14][1] = 9;
		automata[14][2] = 4;
		automata[14][3] = 15;
		automata[14][4] = 9;
		automata[15][0] = 16;
		automata[15][3] = 14;
		automata[17][0] = 5;
		automata[17][1] = 9;		
		automata[17][2] = 4;
		automata[17][4] = 9;
		automata[18][0] = 6;
		automata[18][1] = 18;
		automata[18][2] = 4;
		automata[18][3] = 20;
		automata[18][4] = 18;
		automata[19][3] = 7;
		automata[20][0] = 5;
		automata[20][1] = 18;
		automata[20][2] = 4;
		automata[20][3] = 20;
		
		
	}
	

	private long copy(InputStream source, OutputStream destination)
			throws IOException {
		byte[] buf = new byte[4096];
		int numRead;
		int numberOfBytesCopied = 0;
		while (-1 != (numRead = source.read(buf))) {
			destination.write(buf, 0, numRead);
			destination.flush();
			numberOfBytesCopied += numRead;
		}
		return numberOfBytesCopied;
	}

	public String pdfToCSV(String fileName) {
		String parsedText = "";
		System.out.println("Parsing text from PDF file " + fileName + "....");
		File f = new File(fileName);
		if (!f.isFile()) {
			System.out.println("File " + fileName + " does not exist.");
			return null;
		}
		try {
			OutputStream out = new ByteArrayOutputStream();
			pdfToCSV(new FileInputStream(f), out);
			parsedText = out.toString();
		} catch (java.io.FileNotFoundException e) {
			System.out.println("File not found");
		}
		return parsedText;

	}
	
	/**
	 * 
	 * @param user
	 * @param fileName
	 * @param path
	 * @param file
	 */
	public void pdfToTXT(String user, String fileName, String path, InputStream file) {
		
		String parsedText = "";
		System.out.println("Parsing text from PDF file " + fileName + "....");
		

		try {
			System.out.println("Building text file in:" + path + user + ".txt");
			FileOutputStream fileOut = new FileOutputStream(path + user + ".txt");
						
			System.out.println("File created...");
			parser = new PDFParser(file);			

			parser.parse();
			cosDoc = parser.getDocument();
			pdfStripper = new PDFTextStripper();
			pdDoc = new PDDocument(cosDoc);
			String lineSepa = pdfStripper.getLineSeparator();
			int pagesIndex = 0;
			int headerLines = 0;
			int linesIndex = 0;
			boolean firstPage = true;

			Splitter s = new Splitter();
			List l = s.split(pdDoc);

			while (pagesIndex < l.size()) {
				page = (PDDocument) l.get(pagesIndex);
				parsedText = pdfStripper.getText(page);
				String[] lines = parsedText.split(lineSepa);
				if(pagesIndex == 0){
					headerLines = setHeaderLines(lines);
				}	
				List list2 = Arrays.asList(lines);
				int numberOfLines = list2.size();
				while (linesIndex < numberOfLines) {
					String currentLine = (String) list2.get(linesIndex);	
					if( linesIndex > headerLines ){
						fileOut.write(currentLine.getBytes());			
						fileOut.write(lineSepa.getBytes());						
					} else if (firstPage){
						fileOut.write(currentLine.getBytes());			
						fileOut.write(lineSepa.getBytes());
						firstPage= linesIndex==headerLines?false:true;
					}
					linesIndex++;
					
				}
				linesIndex = 0;
				pagesIndex++;			
			}						
			fileOut.close();
			
		} catch (java.io.FileNotFoundException e) {
			System.out.println("File not found");
		} catch (Exception e) {
			System.out.println("Unable to open PDF Parser.");
			try {
				if (cosDoc != null)
					cosDoc.close();
				if (pdDoc != null)
					pdDoc.close();
				if (page != null)
					page.close();
				if (file != null)
					file.close();

			} catch (Exception e1) {
				e1.printStackTrace();
			}
		} finally {
			try {
				if (cosDoc != null)
					cosDoc.close();
				if (pdDoc != null)
					pdDoc.close();
				if (page != null)
					page.close();
				if (file != null)
					file.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

	}
	

	
		
	/**
	 * pdfToCSV
	 * 
	 * @param file
	 * @param writer
	 */
	public void pdfToCSV(InputStream file, OutputStream fileWriter) {

		try {
			parser = new PDFParser(file);
		} catch (Exception e) {
			System.out.println("Unable to open PDF Parser.");
			return;
		}

		try {
			parser.parse();
			cosDoc = parser.getDocument();
			pdfStripper = new PDFTextStripper();
			pdDoc = new PDDocument(cosDoc);
			StringBuffer buff = new StringBuffer();
			
			List selectedPatterns = findPatters(pdDoc);
			searchColumnPatterns(selectedPatterns);
			
			HSSFWorkbook workBook = new HSSFWorkbook();
			// create a new sheet
			HSSFSheet sheet = workBook.createSheet();
			// declare a row object reference
			HSSFRow row = null;
			// declare a cell object reference
			HSSFCell cell = null;

			int numberOfPages = pdDoc.getNumberOfPages();
			String lineSepa = pdfStripper.getLineSeparator();

			int pagesIndex = 0;
			int linesIndex = 0;
			int wordsIndex = 0;
			boolean takingSamples = true;

			Splitter s = new Splitter();
			List l = s.split(pdDoc);
			//int countedLines = 0;
			
			int columnsHeader1 = 0;
			int columnsHeader2 = 0;
			List columnsSamplesLengths;
			Map sampleData = new Hashtable();
			int sampleCount = 0;
			int headerLines = 0;
			int totalLines = 0;
			while (pagesIndex < l.size()) {
				page = (PDDocument) l.get(pagesIndex);
				parsedText = pdfStripper.getText(page);
				String[] lines = parsedText.split(lineSepa);
				if(pagesIndex == 0){
					headerLines = setHeaderLines(lines);
				}								
				List list2 = Arrays.asList(lines);
				int numberOfLines = list2.size();
				int [] countedSamples = new int[numberOfLines * l.size()];
				while (linesIndex < numberOfLines) {
					//create a row
					row = sheet.createRow(totalLines);
					buff.delete(0, buff.length());
					String currentLine = (String) list2.get(linesIndex);
					if(pagesIndex != 0 && currentLine.equals(firstCountedLine)){
						linesIndex = linesIndex + headerLines;
						continue;						
					}
					//Header lines: Printed without formating
					if (linesIndex <=  headerLines  ) {
						if( linesIndex < headerLines - 2 || linesIndex == headerLines){
							cell = row.createCell(0);
							HSSFRichTextString header = new HSSFRichTextString(currentLine);
							cell.setCellValue(header);							
						} else {
							formatHeaderData(currentLine, row);							
						} 
						
					} else {
						//The body Lines
						int columnNumber = 0;
						DataPatterns pt = new DataPatterns();
						formatBodyData(currentLine, pt);					
						if(pt.getColumnCount() == 0){
							cell = row.createCell(0);
							HSSFRichTextString dataWithoutComas = new HSSFRichTextString(" ");
							cell.setCellValue(dataWithoutComas);							
						}else{
							int index = findPatternInListWithIndex(pt);
							
							if (index != -1){
								DataPatterns definitivePattern = (DataPatterns)resultingPatterns.get(String.valueOf(index));
								pt.setPattern(definitivePattern.getPattern());
								pt.formatLine(row);
							} else {
								pt.setWords();
								pt.formatLine(row);
							}	
							
						}
										
					}
					linesIndex++;
					totalLines++;
				}
				linesIndex = 0;
				pagesIndex++;
			}
			linesIndex = 0;
			workBook.write(fileWriter);

		} catch (Exception e) {
			System.out
					.println("An exception occured in parsing the PDF Document.");
			e.printStackTrace();
			try {
				if (cosDoc != null)
					cosDoc.close();
				if (pdDoc != null)
					pdDoc.close();
				if (page != null)
					page.close();

			} catch (Exception e1) {
				e1.printStackTrace();
			}
		} finally {
			try {
				if (cosDoc != null)
					cosDoc.close();
				if (pdDoc != null)
					pdDoc.close();
				if (page != null)
					page.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		System.out.println("Done.");
	}

	/**
	 * pdfToCSV
	 * 
	 * @param file
	 * @param writer
	 */
	public List findPatters(PDDocument pdDoc) throws Exception {
		List selectedPatterns = new ArrayList();
		try {
			int numberOfPages = pdDoc.getNumberOfPages();
			String lineSepa = pdfStripper.getLineSeparator();
		
			int pagesIndex = 0;
			int linesIndex = 0;
			int wordsIndex = 0;

			Splitter s = new Splitter();
			List l = s.split(pdDoc);
			
			Map sampleData = new Hashtable();
			int sampleCount = 0;
			int headerLines = 0;
			int totalLines = 0;
			int [] countedSamples = null;
			int selectedIndex = 0;
			while (pagesIndex < l.size()) {
				if (pagesIndex > 5){
					break;
				}
				page = (PDDocument) l.get(pagesIndex);
				parsedText = pdfStripper.getText(page);
				String[] lines = parsedText.split(lineSepa);
				if(pagesIndex == 0){
					headerLines = setHeaderLines(lines);
				}								
				List list2 = Arrays.asList(lines);
				int numberOfLines = list2.size();
				while (linesIndex < numberOfLines) {
					String currentLine = (String) list2.get(linesIndex);
					if(pagesIndex != 0 && currentLine.equals(firstCountedLine)){
						linesIndex = linesIndex + headerLines;
						continue;						
					}
					//Header lines: Printed without formating
					if (linesIndex >  headerLines) {
						
						//The body Lines					
						String bodyLine = "";
						DataPatterns data = new DataPatterns();
						formatBodyData(currentLine, data);	
						
						if (data.getColumnCount() != 0){
							selectedPatterns.add(selectedIndex, data);		
							selectedIndex ++;
						}					
					}
					linesIndex++;
				}
				linesIndex = 0;
				pagesIndex++;
			}
			linesIndex = 0;

		} catch (Exception e) {
			System.out
					.println("An exception occured in parsing the PDF Document.");
			e.printStackTrace();
			throw e;
		}
		
		return selectedPatterns;
	}

	//Extracts text from a PDF Document and writes it to a text file
	public static void main(String args[]) {

		PDFTextParser a = new PDFTextParser();
		//a.pdfToTXT("C:\\SD8000.pdf");
		System.out.println("done...");
	
	}

	/**
	 * isNumber
	 * 
	 * @author csepulveda
	 * @param String
	 *            test
	 * 
	 * Returns true if the tested string is a number, false if it's not
	 */
	public static boolean isNumber(String test) {
		boolean rst = true;
		try {
			UDecimalFormat.parse(test);
		} catch (NumberFormatException e) {
			rst = false;
		}
		return rst;
	}

	/**
	 * isNumber
	 * 
	 * @author csepulveda
	 * @param String
	 *            test
	 * 
	 * Returns true if the tested string is a number, false if it's not
	 */
	public int setHeaderLines(String [] lines) {

		List list2 = Arrays.asList(lines);
		int numberOfLines = list2.size();
		boolean firstCountedLineFlag = false;
		boolean firstLine = true;
		String compare = "*";
		int headerIndex = 0;
		int i = 0;
		while (i < numberOfLines) {
			String currentLine = (String) list2.get(i);
			if ( i == 0 || !firstCountedLineFlag){
				if (!currentLine.trim().equals(""))
				{
					firstCountedLine = currentLine;
					firstCountedLineFlag = true;
				}
			}
			
			if (!currentLine.trim().equals(""))
			{
				compare =  setLine(currentLine.length());
			}
			
			if (currentLine.equals(compare)) {
				if(firstLine){
					firstLine = false;
					headerIndex = i;
				}else{
					headerIndex = i;
					break;
				}
			} else {
				if ( i>=12 ) {
					break;
				} else {
					if (!firstLine && currentLine.equals(firstCountedLine)) {
						break;
					}
				}
			}
			i++;
		}
		
		return headerIndex ;
	}
	
	private String setLine(int size){
		char [] line = new char[size];
		for (int j =0; j<size; j++){
			line[j] = '-';			
		}
		return new String(line);
	}

	/**
	 * 
	 * @return
	 */
	public String formatHeaderData(String headerLine, HSSFRow row) {

		
		//char previusChar = data[0];
		int countBlanks = 0;
		StringBuffer buff = new StringBuffer();
		char [] lineChars = headerLine.toCharArray();
		char[] word = new char[headerLine.length()];
		
		
		int temp = 0;//lex();
		int estado = 0;
		int i = 0;
		int columnCount = 0;
		while (i < lineChars.length) {
			
			temp = Integer.parseInt(Character.toString(classifyToken(lineChars[i])));		
			if (automata[estado][temp] != -1) {
				estado = automata[estado][temp];
				word[i] = lineChars[i];				
			}else {
				HSSFCell cell = row.createCell(columnCount);
				HSSFRichTextString dataWithoutComas = new HSSFRichTextString(new String(word).trim());
				cell.setCellValue(dataWithoutComas);
				word = new char[headerLine.length()];
				estado = 0;
				columnCount++;
				i--;
			}
			i++;
			
		}
		buff.append('\n');

		return buff.toString();
	}
	
	/**
	 * 
	 * @return
	 */
	public String formatBodyData(String currentLine, DataPatterns pattern) {

		List columnsLength = new ArrayList();
		//char previusChar = data[0];
		int countChars = 0;
		StringBuffer buff = new StringBuffer();
		char [] lineChars = currentLine.toCharArray();
		char[] word = new char[currentLine.length()+1];
		//word[0] = '\'';
				
		int temp = 0;//lex();
		int estado = 0;
		int i = 0;
		
		int columnCount = 0;
		while (i < lineChars.length) {
			
			temp = Integer.parseInt(Character.toString(classifyToken(lineChars[i])));		
			if (automata[estado][temp] != -1) {
				estado = automata[estado][temp];
				word[i] = lineChars[i];
				countChars++;
								
				//last Column
				if(i == lineChars.length - 1){
					String newWord = new String(word);
					if(columnsLength != null){					
						columnsLength.add(Integer.toString(countChars));					
					}
					buff.append(newWord);
					columnCount ++;
					
					
				}
			} else {
				//word[i] = lineChars[i];
				String newWord = new String(word);
				
				if(isNumber(newWord.trim())){
					Number num = UDecimalFormat.parse(newWord.trim());
					newWord = String.valueOf(num);
				}			
				if(columnsLength != null){					
					columnsLength.add(Integer.toString(countChars));					
				}
				buff.append(newWord);
				buff.append(',');
				word = new char[currentLine.length()+1];
				word[0] = '\'';
				estado = 0;
				columnCount ++;
				countChars = 0;
				i--;

			}
			i++;
			
		}
		buff.append('\n');
		
		pattern.setColumnCount(columnCount);
		pattern.setColumnLengths(columnsLength);
		pattern.setLineData(currentLine);
		pattern.setWords();

		return buff.toString();
	}
	
	
	/**
	 * 
	 * @return
	 */
	/*public String formatSamples(int sampleSize, Map sampleData) {

		StringBuffer buff = new StringBuffer();	
		for (int i = 0; i < sampleData.size(); i++){
			DataPatterns pt = (DataPatterns)sampleData.get("S"+i);
			int index = findPatternInListWithIndex(selectedPatterns, pt.getPattern());
			if(index != -1){
				pt.setPattern((Map)selectedPatterns.get(index));
				pt.formatLine(buff);
			}else{
				pt.setWords();
				pt.formatLine(buff);
			}
		}
		return buff.toString();
	}*/
	

	
	/**
	 * classifyToken: Classifies the character o either 4 of the categories
	 * considered in the tockenizer
	 * 
	 * @param a
	 * @return ' ' = The current character is and space. '0' = The current
	 *         character is a letter '1' = The current character is a number '2' =
	 *         The current character is a symbol '3' = The current character is
	 *         a dot or a colon
	 */
	private char classifyToken(char a) {
		if (Character.isDigit(a)) {
			return DIGIT;
		} else if (a == ' ') {
			return SPACE;
		} else if (Character.isLetter(a)) {
			return LETTER;
		} else if (a == '.' || a == ',') {
			return DOT_COLON;
		} else {
			return SYMBOL;
		}

	}
	

	/**
	 * searchColumnPatterns
	 * 
	 * @param filteredData
	 * @return
	 */
	private void searchColumnPatterns(List filteredData) {
		
		int sampleSize = filteredData.size();
		if(resultingPatterns == null)
			resultingPatterns = new HashMap();
		int i = 0;		
		
		Map resultingMap = new HashMap();
		while (i < sampleSize){
			DataPatterns currentData = (DataPatterns)filteredData.get(i);
			List columns = currentData.getColumnLengths();
			int j = i + 1;
			while ( j < sampleSize ){
				DataPatterns nextData = (DataPatterns)filteredData.get(j);
				resultingMap = new HashMap();
				if(currentData.matchPattern(nextData, resultingMap)){
					if(!findPatternInList(resultingPatterns, resultingMap)){
						currentData.setPattern(resultingMap);
						if(!matchesAnyIncluded(currentData)){
							resultingPatterns.put(String.valueOf(currentData.getColumnCount()), currentData);
						}						
					}					
				}
				j++;
			}			
			i++;
		}
	}
	
		
	/**
	 * 
	 * @param array
	 * @param value
	 * @param size
	 * @return
	 */
	private boolean findValueInArray(int [] array, int value, int size){
		boolean result = false;
		for(int i =0; i<size; i++){
			if(array[i] == value){
				result =  true;
				break;
			}
		}
		return result;
		
	}
	
	/**
	 * 
	 * @param list
	 * @param pattern
	 * @return
	 */
	private boolean findPatternInList(Map list, Map pattern){
		boolean result = false;
		if(list.containsKey(String.valueOf(pattern.size()))){
			DataPatterns defData = (DataPatterns)list.get(String.valueOf(pattern.size()));
			Map currentPattern = defData.getPattern();
			if(currentPattern.size() == pattern.size()){
				int j=0;
				int [] xy = (int [])currentPattern.get(DataPatterns.COLUMN + 0);
				int [] xyCompare = (int [])pattern.get(DataPatterns.COLUMN + 0);
				while ((xy[0] == xyCompare[0])&&(xy[1] == xyCompare[1]) && j < currentPattern.size()){
					j++;
				}				
				
				if(j >= currentPattern.size()){
					result =  true;					
				}
			}
			
		}
		return result;		
	}
	
	/**
	 * 
	 * @param list
	 * @param pattern
	 * @return
	 */
	private boolean matchesAnyIncluded(DataPatterns pattern){
		boolean result = false;
		Map looking = pattern.getPattern();
		
		Set keySet = resultingPatterns.keySet();
		int i = 0;
		Map resultingPattern;
		for(Iterator iter = keySet.iterator(); iter.hasNext();){			
			int key = Integer.parseInt((String)iter.next());
		
			if(Math.abs(key - pattern.getColumnCount()) < 2){
				DataPatterns defData = (DataPatterns)resultingPatterns.get(String.valueOf(key));
				resultingPattern = new HashMap();
				if(defData.matchPattern(pattern, resultingPattern)){
					resultingPatterns.remove(String.valueOf(key));
					resultingPatterns.put(String.valueOf(resultingPattern.size()), defData);
					result = true;
					break;
				}
			}
		}
		return result;		
	}
	
	/**
	 * 
	 * @param list
	 * @param pattern
	 * @return
	 */
	private int findPatternInListWithIndex(DataPatterns pattern){
		int result = -1;
		Map looking = pattern.getPattern();
		Set keySet = resultingPatterns.keySet();
		for(Iterator iter = keySet.iterator(); iter.hasNext();){
			int key = Integer.parseInt((String)iter.next());
			if(key == pattern.getColumnCount()){
				result =  key;
			} else {
				if(Math.abs(key - pattern.getColumnCount()) <= 2){
					DataPatterns definitivePattern = (DataPatterns)resultingPatterns.get(String.valueOf(key));
					if (pattern.matchPattern(definitivePattern, new HashMap())){
						result = key;
					}
				}
			}
		}
		return result;
		
	}
		
}