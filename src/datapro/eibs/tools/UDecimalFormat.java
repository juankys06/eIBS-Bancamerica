package datapro.eibs.tools;

import java.math.BigDecimal;
import java.text.*;
import java.util.Locale;

public class UDecimalFormat {
	public 	final static String INTEGER_DECIMAL_FORMAT_MASK = "#,###,###,###,##0.";
	public 	final static String DEFAULT_DECIMAL_FORMAT_MASK = "#,###,###,###,##0.00";
	
	public static String getDecimalFormatMask(int decimals) {
		
		String decimalPart = "";
		for (int i = 0; i < decimals; i++) {
			decimalPart += "0";
		}	
		return INTEGER_DECIMAL_FORMAT_MASK + decimalPart;
	}
	
	public static DecimalFormat getDecimalFormatNumber(int decimals) {
		DecimalFormat decimalFormatNumber = getDecimalFormatNumber();
		decimalFormatNumber.applyPattern(getDecimalFormatMask(decimals));
		return decimalFormatNumber;
	}
	
	/**
	 * DecimalFormat Object of the current JVM locale Number format
	 * @param  .
	 */
	public static DecimalFormat getDecimalFormatNumber() {
		NumberFormat nf = NumberFormat.getNumberInstance();
		DecimalFormat decimalFormatNumber = null;
		
		if (nf instanceof DecimalFormat) {
			decimalFormatNumber = (DecimalFormat) nf;
			decimalFormatNumber.applyPattern(DEFAULT_DECIMAL_FORMAT_MASK);
		} else {
			decimalFormatNumber = new DecimalFormat(DEFAULT_DECIMAL_FORMAT_MASK);
		}
		
		return decimalFormatNumber;
	}
	
	/**
	 * DecimalFormat Object of the given locale Number format
	 * @param locale - Locale Object
	 */
	public static DecimalFormat getDecimalFormatNumber(Locale locale, int decimals) {
		DecimalFormat decimalFormatNumber = getDecimalFormatNumber(locale);
		decimalFormatNumber.applyPattern(getDecimalFormatMask(decimals));
		return decimalFormatNumber;
	}
	
	/**
	 * DecimalFormat Object of the given locale Number format
	 * @param locale - Locale Object
	 */
	public static DecimalFormat getDecimalFormatNumber(Locale locale) {
		NumberFormat nf = NumberFormat.getNumberInstance(locale);
		DecimalFormat decimalFormatNumber = null;
		
		if (nf instanceof DecimalFormat) {
			decimalFormatNumber = (DecimalFormat) nf;
			decimalFormatNumber.applyPattern(DEFAULT_DECIMAL_FORMAT_MASK);
		} else {
			decimalFormatNumber = new DecimalFormat(DEFAULT_DECIMAL_FORMAT_MASK);
		}
		return decimalFormatNumber;
	}

	/**
	 * Format value of this field from a String.
	 *
	 * @param     num the value for the field.
	 * 
	 * @return String
	 */
	public static String formatCCY(String value) {
		double numDouble = 0.0;
		
		try {
			numDouble = parseDouble(value);
		} catch (Exception e) {
			return value;
		}
		return formatCCY(numDouble);
	}

	/**
	 * Method formatCCY
	 * @param value BigDecimal
	 * @return String
	 */
	public static String formatCCY(BigDecimal value) {
		double numDouble = 0.0;
		if (value != null) numDouble = Double.parseDouble(value.toString());
		return formatCCY(numDouble);
	}
	
	/**
	 * Method formatCCY
	 * @param value BigDecimal
	 * @return String
	 */
	public static String formatCCY(BigDecimal value, int decimals) {
		double numDouble = 0.0;
		if (value != null) numDouble = Double.parseDouble(value.toString());
		return formatCCY(numDouble, decimals);
	}
	
	public static String formatCCY(double value) {
		return getDecimalFormatNumber().format(value);
	}
	
	public static String formatCCY(double value, int decimals) {
		return getDecimalFormatNumber(decimals).format(value);
	}
	
	/**
	 * Parsing a String value into a double value.
	 * @param num - The String to be parse into a double
	 * @return - A double value
	 * @throws NumberFormatException
	 */
	public static double parseDouble(String num) throws NumberFormatException {
		double numDouble = 0.0;
		try {
			numDouble = getDecimalFormatNumber().parse(num).doubleValue();
		} catch(Exception e) {
			throw new NumberFormatException();
		}
		return(numDouble);
	}
	
	/**
	 * Parsing a String value into a double value.
	 * @param num - The String to be parse into a double
	 * @param decimals The decimal count
	 * @return
	 * @throws NumberFormatException
	 */
	public static double parseDouble(String num, int decimals) throws NumberFormatException {
		double numDouble = 0.0;
		try {
			numDouble = getDecimalFormatNumber(decimals).parse(num).doubleValue();
		} catch(Exception e) {
			throw new NumberFormatException();
		}
		return(numDouble);
	}
	
	
	/**
	 * Parsing a String value into a double value. 
	 * This method must be used in some cases where parseDouble does not work.
	 * @param num - The String to be parse into a double
	 * @return - A double value
	 */
	public static double parseCCYtoDouble(String num) {
		double numDouble = 0.0;
		char spt = getDecimalSeparator();
  	
		num = takeChar(num, getGroupingSeparator());
		if (spt != '.') num = num.replace(spt, '.');
		numDouble = Double.parseDouble(num);
		return(numDouble);
	}
	
	/**
	 * Parsing the Integer part by the current JVM locale Number format
	 * @param  .
	 */
	public static int parseIntegerPart(String num) throws NumberFormatException {
		int result = 0;
		double numDouble = parseDouble(num);
		result = (int) numDouble;
		return result;
	}

	/**
	 * This method was created in VisualAge.
	 */
	public static DecimalFormatSymbols getDecimalSymbols() {
	  return getDecimalFormatNumber().getDecimalFormatSymbols();
	}

	/**
	 * @return
	 */
	public static char getDecimalSeparator() {
		return getDecimalSymbols().getDecimalSeparator();
	}

	/**
	 * @return
	 */
	public static char getGroupingSeparator() {
		return getDecimalSymbols().getGroupingSeparator();
	}
	
	/**
	 * @param value
	 * @param ch
	 * @return
	 */
	public static String takeChar(String value, char ch) {
		String result = null;
		int posi = value.indexOf(ch);
		if (posi > -1) {
			while (posi > -1) {
				result = value.substring(0, posi);
				result = result + value.substring(posi + 1);
				posi = result.indexOf(ch);
				value = result;
			}
		} else {
			result = value;
		}
		return (result);
	}

}