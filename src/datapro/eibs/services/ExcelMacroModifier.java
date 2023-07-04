/*
 * Created on Feb 23, 2010
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package datapro.eibs.services;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRichTextString;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;

import datapro.eibs.master.JSEIBSProp;

/**
 * @author Catalina
 * 
 * TODO To change the template for this generated type comment go to Window -
 * Preferences - Java - Code Style - Code Templates
 */
public class ExcelMacroModifier {


	/**
	 * pdfToCSV
	 * @param fileName
	 * @return
	 */
	public OutputStream pdfToExcel(String user, String fileName, OutputStream fileOut, String simpleReportName, String absoluteURL) {
		File f = new File(fileName);
		//FileOutputStream fileOut = null;
		try {
		if (!f.isFile()) {
			System.out.println("File " + fileName + " does not exist.");
			return null;
		}		
			modifyMacro(user , new FileInputStream(f), fileOut, simpleReportName, absoluteURL);
			
		} catch (java.io.FileNotFoundException e) {
			System.out.println("File not found");
		}
		return fileOut;

	}
	
	public OutputStream pdfToExcel(String user, InputStream inputStream, OutputStream fileOut, String simpleReportName, String absoluteURL) {
		modifyMacro(user, inputStream, fileOut, simpleReportName, absoluteURL);
		return fileOut;

	}

	
		
	/**
	 * pdfToCSV
	 * 
	 * @param file
	 * @param writer
	 */
	public void modifyMacro(String user, InputStream file, OutputStream fileWriter, String simpleReportName, String absoluteURL) {


		try {
			
			HSSFWorkbook workBook = new HSSFWorkbook(file);

			HSSFSheet sheet = workBook.getSheetAt(0);       // first sheet
			HSSFRow row     = sheet.getRow(99);        // row 100
			HSSFCell cell   = row.getCell(26);  // cell AA
			
			HSSFRichTextString link = new HSSFRichTextString(absoluteURL+"reports/" + user + ".txt" );
			cell.setCellValue(link);
			
			workBook.write(fileWriter);

		} catch (Exception e) {
			System.out
					.println("An exception occured in parsing the PDF Document.");
			e.printStackTrace();
			try {
				if (file != null)
					file.close();
				if (fileWriter != null)
					fileWriter.close();				
			} catch (Exception e1) {
				e1.printStackTrace();
			}
		} finally {
			try {
				if (file != null)
					file.close();
				if (fileWriter != null)
					fileWriter.close();		
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		System.out.println("Done.");
	}

	

	//Extracts text from a PDF Document and writes it to a text file
	public static void main(String args[]) {

		ExcelMacroModifier a = new ExcelMacroModifier();
		//a.pdfToCSV("C:\\Macro.xls");
		
	
	}

		
}