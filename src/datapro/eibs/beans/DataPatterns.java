package datapro.eibs.beans;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.poi.hssf.record.formula.functions.Char;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRichTextString;
import org.apache.poi.hssf.usermodel.HSSFRow;

import com.datapro.generic.tool.Util;

/**
 * @author csepulveda
 */
public class DataPatterns {

	private int columnCount = 0;

	private List columnLengths;

	private List words;

	private String lineData = "";

	private Map pattern;

	public static final String COLUMN = "column_";

	//indexes needed in the iteration
	int i = 0;

	int j = 0;

	int k = 0;

	int lastIndex = 0;
	
	int test = 0;
	
	private List columnsAsociated = new ArrayList();  

	/**
	 * @return Returns the columnCount.
	 */
	public int getColumnCount() {
		return columnCount;
	}

	/**
	 * @param columnCount
	 *            The columnCount to set.
	 */
	public void setColumnCount(int columnCount) {
		this.columnCount = columnCount;
	}

	/**
	 * @return Returns the columnLengths.
	 */
	public List getColumnLengths() {
		return columnLengths;
	}

	/**
	 * @param columnLengths
	 *            The columnLengths to set.
	 */
	public void setColumnLengths(List columnLengths) {
		this.columnLengths = columnLengths;
	}

	/**
	 * @return Returns the lineData.
	 */
	public String getLineData() {
		return lineData;
	}

	/**
	 * @param lineData
	 *            The lineData to set.
	 */
	public void setLineData(String lineData) {
		this.lineData = lineData;
	}

	/**
	 * @return Returns the pattern.
	 */
	public Map getPattern() {
		if (pattern == null) {
			setWords();
		}
		return pattern;
	}

	/**
	 * @param pattern
	 *            The pattern to set.
	 */
	public void setPattern(Map pattern) {
		this.pattern.clear();
		this.pattern.putAll(pattern);
		setColumnCount(pattern.size());
	}
	
	/**
	 * @return Returns the columnsAsociated.
	 */
	public List getColumnsAsociated() {
		return columnsAsociated;
	}
	/**
	 * @param columnsAsociated The columnsAsociated to set.
	 */
	public void setColumnsAsociated(List columnsAsociated) {
		this.columnsAsociated = columnsAsociated;
	}

	/**
	 * 
	 *  
	 */
	public void setWords() {
		pattern = new HashMap();
		words = new ArrayList();
		int lastIndex = 0;
		int xy[];
		for (int i = 0; i < columnCount; i++) {
			int index = lastIndex
					+ Integer.parseInt(columnLengths.get(i).toString());
			words.add(lineData.subSequence(lastIndex, index));
			xy = new int[2];
			xy[0] = lastIndex;
			xy[1] = index;
			pattern.put(COLUMN + i, xy);
			lastIndex = index;
		}
	}

	/**
	 * 
	 *  
	 */
	public void formatLine(HSSFRow row) {

		int myPatternSize = this.pattern.size();

		for (int i = 0; i < myPatternSize; i++) {
			int myXY[] = (int[]) pattern.get(COLUMN + i);
			CharSequence temp ;
			if(myXY[1] > lineData.length()){
				temp = lineData.subSequence(myXY[0], lineData.length());
				HSSFCell cell = row.createCell(i);
				HSSFRichTextString dataWithoutComas = new HSSFRichTextString(
						removeComas(temp.toString()));
				cell.setCellValue(dataWithoutComas);
				break;
			}else{
				temp = lineData.subSequence(myXY[0], myXY[1]);
			}			
			HSSFCell cell = row.createCell(i);
			HSSFRichTextString dataWithoutComas = new HSSFRichTextString(
					removeComas(temp.toString()));
			cell.setCellValue(dataWithoutComas);
		}
	}

	/**
	 * 
	 *  
	 */
	public String formatLine(StringBuffer resultLine) {

		int myPatternSize = this.pattern.size();

		for (int i = 0; i < myPatternSize; i++) {
			int myXY[] = (int[]) pattern.get(COLUMN + i);
			CharSequence temp = lineData.subSequence(myXY[0], myXY[1]);
			resultLine.append(removeComas(temp.toString()));
			if (i == myPatternSize - 1) {
				resultLine.append("\n");
			} else {
				resultLine.append(",");
			}

		}
		return resultLine.toString();
	}

	/**
	 * 
	 *  
	 */
	public String removeComas(String column) {
		StringBuffer resultColumn = new StringBuffer();
		String[] sp = column.split(",");
		for (int j = 0; j < sp.length; j++) {
			resultColumn.append(sp[j]);
		}

		return resultColumn.toString();
	}

	/**
	 * 
	 *  
	 */
	public String formatLine() {
		StringBuffer resultLine = new StringBuffer();
		return formatLine(resultLine);
	}

	/**
	 * 
	 * @param index
	 * @return
	 */
	public String getWordAtIndex(int index) {
		if (words == null)
			setWords();
		return (String) words.get(index);
	}

	/**
	 * 
	 * @param dataSample
	 * @param resultingPattern
	 * @return
	 */
	public boolean matchPattern(DataPatterns dataSample, Map resultingPattern) {
		
		i=0; j=0; k=0; lastIndex = 0;
		Map data = dataSample.getPattern();
		if (data == null) {
			dataSample.setWords();
			data = dataSample.getPattern();
		}
		Map myPattern = new HashMap();
		myPattern.putAll(pattern);
		
		Map dataPattern = new HashMap();
		dataPattern.putAll(data);

		//boolean result = false;
		boolean result = true;
		int myPatternSize = this.getPattern().size();
		int dataPatternSize = data.size();
		int lastIndex = 0;		
		if (Math.abs(myPatternSize - dataPatternSize) <= 2) {
			test = 0;
			boolean condition;
			
			while (i < myPatternSize && j < dataPatternSize) {					
				int myXY[] = (int[]) myPattern.get(COLUMN + i);
				int xy[] = (int[]) dataPattern.get(COLUMN + j);
				int newArray[] = new int[2];
				try{
					if (xy[0] == myXY[0]) {
						if (xy[1] == myXY[1]) {
							addCouple(resultingPattern, xy[0], xy[1]);
							i = i + 1;
							j = j + 1;
							test = 0;
						} else {
							whenEqualBegginingAndDifferentEnding(xy, myXY, resultingPattern, myPatternSize, dataPatternSize, dataSample);
						}
					} else {
						if (xy[1] == myXY[1]) {							
							whenDifferentBegginingAndEqualEnding (xy, myXY, resultingPattern, myPatternSize, dataPatternSize, dataSample);							
						}else{
							whenDifferentBegginingAndDifferentEnding (xy, myXY, resultingPattern, myPatternSize, dataPatternSize, dataSample, data);
						}						
					}
					
					if (test > 2){
						result = false;
						break;
					}
				}catch (StringIndexOutOfBoundsException e){
					System.out.println("Pattern Size:" + myPatternSize + ":" + dataPatternSize);
					System.out.println(e);
					result = false;
					break;
				}catch (NullPointerException e){
					System.out.println("Pattern Size:" + myPatternSize + ":" + dataPatternSize);
					System.out.println(e);
					result = false;
					break;
				}
			}
			

		}else{
			result = false;
		}
		if(result){
			this.pattern.clear();
			this.pattern.putAll(resultingPattern);		
			this.columnCount = resultingPattern.size();
			addColumnInAsociatedArray(dataSample.getColumnCount());
			
		}
		return result;

	}

	/**
	 * 
	 * @param i
	 * @param j
	 * @param k
	 * @param test
	 * @param xy
	 * @param myXY
	 * @param lastIndex
	 * @param resultingPattern
	 * @param dataSample
	 */
	private void matchingAlike(int i, int j, int k, int test, int xy[],
			int myXY[], int lastIndex, Map resultingPattern,
			DataPatterns dataSample) {

		int newArray[] = new int[2];

	}
	
	/**
	 * 
	 * @param word
	 * @return
	 */
	private boolean isLine(String word) {
		String test = "";

		for (int h = 0; h < word.length(); h++) {
			test = test + "-";
		}

		return word.equals(test);

	}

	/**
	 * 
	 * @param xy
	 * @param myXY
	 * @param resultingPattern
	 * @param test
	 * @param myPatternSize
	 * @param dataPatternSize
	 */
	private void whenEqualBegginingAndDifferentEnding(int[] xy, int[] myXY,
			Map resultingPattern, int myPatternSize,
			int dataPatternSize, DataPatterns dataSample) {

		String myWord = this.lineData.substring(myXY[0], myXY[1]);
		String otherWord = dataSample.lineData.substring(xy[0], xy[1]);

		if (myWord.length() > otherWord.length()) {
			String a = this.lineData.substring(xy[0], xy[1]);
			if (a.trim().equals("") || isLine(a)) {
				addCouple(resultingPattern, xy[0], xy[1]);
				if (xy[1] < myXY[1]) {
					myXY[0] = xy[1];
					j = j + 1;
				} else {
					i = i + 1;
					j = j + 1;
				}
				test = 0;
				//test = test + 1;
			} else {
				int[] newArray = new int[2];
				newArray[0] = xy[0];
				newArray[1] = xy[1];
				String b = dataSample.lineData.substring(xy[1], myXY[1]);
				if (b.trim().equals("")) {
					newArray[1] = myXY[1];
					i = i + 1;
					j = j + 1;
					//test = test + 1;
					test = 0;
				} else {
					//TODO:reproducir
					newArray[1] = myXY[1];
					j = j + 1;
					test = test + 1;
					if(isNumber(myWord)){
						if(!isNumber(otherWord))
							test = test + 1;
					}else if(isNumber(otherWord)){
						if(!isNumber(myWord))
							test = test + 1;
					}
				}
				addCouple(resultingPattern, newArray[0], newArray[1]);

			}
		} else {
			String a = dataSample.lineData.substring(myXY[0], myXY[1]);
			if (a.trim().equals("") || isLine(a)) {
				addCouple(resultingPattern, myXY[0], myXY[1]);
				
				if (myXY[1] < xy[1]) {
					i = i + 1;
				} else {
					i = i + 1;
					j = j + 1;
				}
				//test = test + 1;
				test = 0;
			} else {
				int[] newArray = new int[2];
				newArray[0] = xy[0];
				newArray[1] = xy[1];
				String b = this.lineData.substring(myXY[1], xy[1]);
				if (b.trim().equals("") || isLine(b)) {
					newArray[1] = xy[1];
					i = i + 1;
					j = j + 1;
					test = 0;
				} else {
					i = i + 1;
					test = test + 1;
					if(isNumber(myWord)){
						if(!isNumber(otherWord))
							test = test + 1;
					}else if(isNumber(otherWord)){
						if(!isNumber(myWord))
							test = test + 1;
					}
				}
				
				addCouple(resultingPattern,newArray[0],newArray[1]);
			}
		}
	}
	
	/**
	 * 
	 * @param xy
	 * @param myXY
	 * @param resultingPattern
	 * @param test
	 * @param myPatternSize
	 * @param dataPatternSize
	 */
	private void whenDifferentBegginingAndEqualEnding(int[] xy, int[] myXY,
			Map resultingPattern, int myPatternSize,
			int dataPatternSize, DataPatterns dataSample) {
		
		if ( xy[0] == lastIndex ) {
			if(lastIndex < myXY[1]  ){
				addCouple(resultingPattern, lastIndex, myXY[1]);
				i = i + 1;
				j = j + 1;
				//TODO:DEPRONTO ME FALTA UNA CONDICION
				test = 0;
			} else {				
				test = test + 1;
				i = i + 1;				
			}	
			//test = test + 1;
			
		} else if ( myXY[0] == lastIndex ) {
			if (xy[1] > lastIndex) {
				addCouple(resultingPattern, lastIndex, xy[1]);
				i = i + 1;
				j = j + 1;
				//TODO:DEPRONTO ME FALTA UNA CONDICION
				test = 0;
			} else {
				test = test + 1;
				j = j + 1;
			}
			//test = test + 1;			
		} else {
			test = test + 1;
			lastIndexDoNotMatchWithBegining(xy, myXY, resultingPattern, myPatternSize, dataPatternSize, dataSample);
		}
		
		
	}
	
	/**
	 * 
	 * @param xy
	 * @param myXY
	 * @param resultingPattern
	 * @param myPatternSize
	 * @param dataPatternSize
	 * @param dataSample
	 * @param data
	 */
	private void whenDifferentBegginingAndDifferentEnding(int[] xy, int[] myXY,
			Map resultingPattern, int myPatternSize,
			int dataPatternSize, DataPatterns dataSample, Map data) {
				
				
		if ( lastIndex == xy[0] ) {
			if ( lastIndex < myXY[0] ) {
				if ( xy[1] <= myXY[0] ) {
					addCouple(resultingPattern, xy[0], xy[1]);
					j = j + 1;
					test = 0;
				} else {				
					String a = this.lineData.substring(xy[0],myXY[0]);
					int[] newXyArray = new int[2];
					int[] newMyXyArray = new int[2];
					if ( a.trim().equals("") ) {
						newMyXyArray[0] = lastIndex;
						newMyXyArray[1] = myXY[1];
						newXyArray[0] = xy[0];
						newXyArray[1] = xy[1];						
						
					} else {
						String b = dataSample.lineData.substring(xy[0],myXY[0]);
						if ( b.trim().equals("") ) {
							int[] newArray = new int[2];
							k = k - 1;
							newArray = (int[]) resultingPattern.get(COLUMN
									+ k);
							newArray[1] = myXY[0];
							addCouple(resultingPattern, newArray[0], newArray[1]);
													
							newMyXyArray[0] = myXY[0];
							newMyXyArray[1] = myXY[1];
							
							newXyArray[0] = xy[0];
							newXyArray[1] = xy[1];
							if (lastIndex < xy[1] )
								newXyArray[0] = lastIndex;							
						} else {
							test = test + 1;
							newMyXyArray[0] = lastIndex;
							newMyXyArray[1] = myXY[1];
							
							newXyArray[0] = xy[0];
							newXyArray[1] = xy[1];
						}
						
						
					}
					whenEqualBegginingAndDifferentEnding(newXyArray, newMyXyArray, resultingPattern, myPatternSize, dataPatternSize, dataSample);
				}
				
			} else {
				if ( myXY[1] <= lastIndex ) {
					i = i + 1;
					test = test + 1;
				} else {	
					String a = this.lineData.substring(myXY[0], xy[0]);
					int[] newXyArray = new int[2];
					int[] newMyXyArray = new int[2];
					if ( a.trim().equals("") ) {
						newXyArray[0] = xy[0];
						newXyArray[1] = xy[1];
						newMyXyArray[0] = lastIndex;
						newMyXyArray[1] = myXY[1];					
					} else {					
						String b = dataSample.lineData.substring(myXY[0],xy[0]);
						if ( b.trim().equals("") ) {
							int[] newArray = new int[2];
							k = k - 1;
							newArray = (int[]) resultingPattern.get(COLUMN
									+ k);
							newArray[1] = myXY[0];
							addCouple(resultingPattern,newArray[0], newArray[1] );
								
							newXyArray[0] = lastIndex;
							newXyArray[1] = xy[1];
						}else{
							test = test + 1;
							newMyXyArray[0] = lastIndex;
							newMyXyArray[1] = myXY[1];
							
							newXyArray[0] = lastIndex;
							newXyArray[1] = xy[1];
						}						
					}
					whenEqualBegginingAndDifferentEnding(newXyArray, newMyXyArray, resultingPattern, myPatternSize, dataPatternSize, dataSample);					
				}				
			}
		} else if ( lastIndex == myXY[0]) {					
			if ( lastIndex < xy[0] ) {
				if ( myXY[1] <= xy[0] ) {
					addCouple(resultingPattern, myXY[0], myXY[1]);
					i = i + 1;
					test = 0;
				} else {
					int[] newMyXYArray = new int[2];
					int[] newXyArray = new int[2];
					String a = dataSample.lineData.substring(myXY[0],xy[0]);
					if ( a.trim().equals("") || isLine(a) ) {
						newXyArray[0] = lastIndex;
						newXyArray[1] = xy[1];
						
						newMyXYArray[0] = myXY[0];
						newMyXYArray[1] = myXY[1];
					} else {
						String b = this.lineData.substring(myXY[0],xy[0]);
						if ( b.trim().equals("") || isLine(b) ) {
							
							newXyArray[0] = xy[0];
							newXyArray[1] = xy[1];
							newMyXYArray[0] = myXY[0];
							newMyXYArray[1] = myXY[1];
							
							int[] newArray = new int[2];
							k = k - 1;
							newArray = (int[]) resultingPattern.get(COLUMN
									+ k);
							newArray[1] = xy[0];
							addCouple(resultingPattern,newArray[0], newArray[1]);
								
							if ( lastIndex < xy[1])
								newMyXYArray[0] = lastIndex;
														
						}else{							
							newMyXYArray[0] = myXY[0];
							newMyXYArray[1] = myXY[1];
							
							newXyArray[0] = lastIndex;
							newXyArray[1] = xy[1];
							test = test + 1;
						}
					}
					whenEqualBegginingAndDifferentEnding(newXyArray, newMyXYArray, resultingPattern, myPatternSize, dataPatternSize, dataSample);					
				}				
			} else {
				if ( xy[1] <= lastIndex ) {
					j = j + 1;
					test = test + 1;
				} else {
					int[] newMyXyArray = new int[2];
					int[] newXyArray = new int[2];
					String a = dataSample.lineData.substring(xy[0], lastIndex);
					if ( a.trim().equals("") || isLine(a) ) {
						newXyArray[0] = lastIndex;
						newXyArray[1] = xy[1];
						
						newMyXyArray[0] = myXY[0];
						newMyXyArray[1] = myXY[1];
					} else {
						String b = this.lineData.substring(xy[0],myXY[0]);
						if ( b.trim().equals("") || isLine(b) ) {
							newXyArray[0] = xy[0];
							newXyArray[1] = xy[1];							
							newMyXyArray[0] = myXY[0];
							newMyXyArray[1] = myXY[1];
							
							int[] newArray = new int[2];
							k = k - 1;
							newArray = (int[]) resultingPattern.get(COLUMN
									+ k);
							newArray[1] = xy[0];
							addCouple(resultingPattern, newArray[0],newArray[1]);
							if ( lastIndex < xy[1])
								newMyXyArray[0] = lastIndex;
							
							newXyArray[1] = xy[1];
						}else{
							newMyXyArray[0] = myXY[0];
							newMyXyArray[1] = myXY[1];
							
							newXyArray[0] = lastIndex;
							newXyArray[1] = xy[1];
							test = test + 1;
						}
						
					}
					whenEqualBegginingAndDifferentEnding(newXyArray, newMyXyArray, resultingPattern, myPatternSize, dataPatternSize, dataSample);
					
				}										
			}
		} else {
			if ( lastIndex == xy[1] ) {
				if (myXY[1]<xy[1]) {
					i = i + 1;
					j = j + 1;
				}else{
					j = j + 1;
				}
				test = test + 1;
			}else{
				if (xy[1]< myXY[1]) {
					i = i + 1;
					j = j + 1;
				}else{
					i = i + 1;
				}
				test = test + 1;
			}
		}
		
	}
	
	/**
	 * 
	 * @param xy
	 * @param myXY
	 * @param resultingPattern
	 * @param myPatternSize
	 * @param dataPatternSize
	 * @param dataSample
	 */
	private void lastIndexDoNotMatchWithBegining(int[] xy, int[] myXY,
			Map resultingPattern, int myPatternSize, int dataPatternSize,
			DataPatterns dataSample) {
		if (lastIndex == xy[1] && lastIndex == myXY[1]) {
			i = i + 1;
			j = j + 1;
		} else if (lastIndex == xy[1]) {
			j = j + 1;
		} else if (lastIndex == myXY[1]) {
			i = i + 1;
		} else {
			if (xy[0] < myXY[0]) {
				String a = dataSample.lineData.substring(xy[0], myXY[0]);
				if (a.trim().equals("") || isLine(a)) {
					addCouple(resultingPattern, myXY[0], myXY[1]);
					i = i + 1;
					j = j + 1;
					//test = test + 1;
					test = 0;
				} else {
					String b = this.lineData.substring(xy[0], myXY[0]);
					if (b.trim().equals("")) {
						int[] newArray = new int[2];
						k = k - 1;
						newArray = (int[]) resultingPattern.get(COLUMN + k);
						newArray[1] = myXY[0];
						addCouple(resultingPattern, newArray[0], newArray[1]);

						addCouple(resultingPattern, lastIndex, myXY[1]);
						i = i + 1;
						j = j + 1;
						test = 0;
					} else {
						//addCouple(resultingPattern, lastIndex, myXY[1]);
						i = i + 1;
						j = j + 1;
						test = test + 1;
					}
				}
			} else {
				String a = dataSample.lineData.substring(myXY[0], xy[0]);
				if (a.trim().equals("") || isLine(a)) {
					addCouple(resultingPattern, myXY[0], myXY[1]);
					i = i + 1;
					j = j + 1;
					//test = test + 1;
					test = 0;
				} else {
					String b = this.lineData.substring(myXY[0], xy[0]);
					if (b.trim().equals("")) {
						int[] newArray = new int[2];
						k = k - 1;
						newArray = (int[]) resultingPattern.get(COLUMN + k);
						newArray[1] = xy[0];
						addCouple(resultingPattern, newArray[0], newArray[1]);

						addCouple(resultingPattern, lastIndex, myXY[1]);
						i = i + 1;
						j = j + 1;
						test = 0;

					} else {
						//test this
						addCouple(resultingPattern, lastIndex, myXY[1]);
						i = i + 1;
						j = j + 1;
						test = test + 1;
					}
				}
			}
		}
	}
	
	/**
	 * 
	 * @param resultingPattern
	 * @param xy0
	 * @param xy1
	 */
	private void addCouple(Map resultingPattern, int xy0, int xy1){
		int[]newArray = new int[2];
		newArray[0] = xy0;
		newArray[1] = xy1;
		resultingPattern.put(COLUMN + k, newArray);
		lastIndex = newArray[1];
		k = k + 1;
	}
	
	/**
	 * findColumnInAsociatedArray
	 * @param columnNumber
	 */
	public boolean findColumnInAsociatedArray(int columnNumber){
		if(this.getColumnsAsociated().contains(String.valueOf(columnNumber))){
			return true;
		}else{
			return false;
		}
	}
	
	/**
	 * findColumnInAsociatedArray
	 * @param columnNumber
	 */
	public void addColumnInAsociatedArray(int columnNumber){
		if ( !findColumnInAsociatedArray(columnNumber) ){
			this.getColumnsAsociated().add(String.valueOf(columnNumber));
		}				
	}
	
	
	private boolean isNumber (String word){
		try{
			new BigDecimal(word.trim());
			return true;
		}catch(NumberFormatException e){
			return false;
		}catch (Exception e){
			return false;
		}
	}


}