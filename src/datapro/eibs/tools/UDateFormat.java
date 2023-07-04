package datapro.eibs.tools;

import java.util.Date;
import java.util.Locale;
import java.text.*;

public class UDateFormat {
	
	public final static int FULL = DateFormat.FULL;
	public final static int LONG = DateFormat.LONG;
	public final static int MEDIUM = DateFormat.MEDIUM;
	public final static int SHORT = DateFormat.SHORT;
	
	public final static String FORMAT_DATE_DMY = "DMY";
	public final static String FORMAT_DATE_MDY = "MDY";
	public final static String FORMAT_DATE_YMD = "YMD";
	
	public final static String PATTERN_FULL_DMY = "dd MMMM yyyy hh:mm:ss.SS";
	public final static String PATTERN_FULL_MDY = "MMMM dd yyyy hh:mm:ss.SS";
	public final static String PATTERN_FULL_YMD = "yyyy MMMM dd hh:mm:ss.SS";
	public final static String PATTERN_LONG_DMY = "dd MMMM yyyy";
	public final static String PATTERN_LONG_MDY = "MMMM dd yyyy";
	public final static String PATTERN_LONG_YMD = "yyyy MMMM dd";
	public final static String PATTERN_SHORT_DMY = "dd/MM/yy";
	public final static String PATTERN_SHORT_MDY = "MM/dd/yy";
	public final static String PATTERN_SHORT_YMD = "yy/MM/dd";
	public final static String PATTERN_MEDIUM_DMY = "dd/MM/yyyy";
	public final static String PATTERN_MEDIUM_MDY = "MM/dd/yyyy";
	public final static String PATTERN_MEDIUM_YMD = "yyyy/MM/dd";
	
	public final static String STAMP_PATTERN_FULL_DMY = "ddMMyyyyhhmmssSS";
	public final static String STAMP_PATTERN_FULL_MDY = "MMddyyyyhhmmssSS";
	public final static String STAMP_PATTERN_FULL_YMD = "yyyyMMddhhmmssSS";
	public final static String STAMP_PATTERN_LONG_DMY = "ddMMMMyyyy";
	public final static String STAMP_PATTERN_LONG_MDY = "MMMMddyyyy";
	public final static String STAMP_PATTERN_LONG_YMD = "yyyyMMMMdd";
	public final static String STAMP_PATTERN_SHORT_DMY = "ddMMyy";
	public final static String STAMP_PATTERN_SHORT_MDY = "MMddyy";
	public final static String STAMP_PATTERN_SHORT_YMD = "yyMMdd";
	public final static String STAMP_PATTERN_MEDIUM_DMY = "ddMMyyyy";
	public final static String STAMP_PATTERN_MEDIUM_MDY = "MMddyyyy";
	public final static String STAMP_PATTERN_MEDIUM_YMD = "yyyyMMdd";


	
	public static String format(Date date) {
		SimpleDateFormat localDateFormat = new SimpleDateFormat();
		return localDateFormat.format(date);
	}
	
	public static String format(Date date, int style, Locale locale) {
		
		//you can't use SimpleDateFormat because you need to read the date format style from ?
		DateFormat localDateFormat = DateFormat.getDateInstance(style, locale);
		return localDateFormat.format(date);
	}
	
	public static String format(Date date, int style, String lang) {
		
		DateFormat localDateFormat = DateFormat.getDateInstance(style, new Locale(lang,""));
		return localDateFormat.format(date);
	}
	
	public static String format(Date date, int style, String lang, String country) {
		
		DateFormat localDateFormat = DateFormat.getDateInstance(style, new Locale(lang, country));
		return localDateFormat.format(date);
	}
	
	public static String format(Date date, String mask) {
		SimpleDateFormat localDateFormat = new SimpleDateFormat(mask);
		return localDateFormat.format(date);
	}
	
	public static String format(Date date, String mask, Locale locale) {
		SimpleDateFormat localDateFormat = new SimpleDateFormat(mask, locale);
		return localDateFormat.format(date);
	}

	public static String format(Date date, String mask, String lang) {
		SimpleDateFormat localDateFormat = new SimpleDateFormat(mask, new Locale(lang,""));
		return localDateFormat.format(date);
	}
	
	public static String format(Date date, String mask, String lang, String country) {
		SimpleDateFormat localDateFormat = new SimpleDateFormat(mask, new Locale(lang, country));
		return localDateFormat.format(date);
	}
	
	public static String format(Date date, String format, int style) {
		return format(date, getPattern(style, format));
	}


	/**
	 * Method getCurrentDateStamp
	 * @param style One DateFormat style
	 * @param format One UDateFormat date format
	 * @return
	 */
	public static String getCurrentDateStamp(int style, String format) {
		SimpleDateFormat formatDate = new SimpleDateFormat(getStampPattern(style, format));
		Date currentTime = new Date();
		String dateString = formatDate.format(currentTime);
		return dateString;
	}
	
	/**
	 * Method getStampPattern
	 * @return String
	 */
	public static String getStampPattern(int style, String format) {
		
		String pattern;		
		switch (style) {
			case DateFormat.FULL :
				if (format.equals(FORMAT_DATE_MDY)) {
					pattern = STAMP_PATTERN_FULL_MDY;
				} else if (format.equals(FORMAT_DATE_DMY)) {
					pattern = STAMP_PATTERN_FULL_DMY;
				} else {
					pattern = STAMP_PATTERN_FULL_YMD;
				}
				break;
			case DateFormat.LONG :
				if (format.equals(FORMAT_DATE_MDY)) {
					pattern = STAMP_PATTERN_LONG_MDY;
				} else if (format.equals(FORMAT_DATE_DMY)) {
					pattern = STAMP_PATTERN_LONG_DMY;
				} else {
					pattern = STAMP_PATTERN_LONG_YMD;
				}				
				break;
			case DateFormat.SHORT :
				if (format.equals(FORMAT_DATE_MDY)) {
					pattern = STAMP_PATTERN_SHORT_MDY;
				} else if (format.equals(FORMAT_DATE_DMY)) {
					pattern = STAMP_PATTERN_SHORT_DMY;
				} else {
					pattern = STAMP_PATTERN_SHORT_YMD;
				}
				break;
			default :
				if (format.equals(FORMAT_DATE_MDY)) {
					pattern = STAMP_PATTERN_MEDIUM_MDY;
				} else if (format.equals(FORMAT_DATE_DMY)) {
					pattern = STAMP_PATTERN_MEDIUM_DMY;
				} else {
					pattern = STAMP_PATTERN_MEDIUM_YMD;
				}
				break;
		}
		return pattern;
	}
	
	/**
	 * Method getPattern
	 * @return String
	 */
	public static String getPattern(int style, String format) {
		
		String pattern;		
		switch (style) {
			case DateFormat.FULL :
				if (format.equals(FORMAT_DATE_YMD)) {
					pattern = PATTERN_FULL_YMD;
				} else if (format.equals(FORMAT_DATE_DMY)) {
					pattern = PATTERN_FULL_DMY;
				} else {
					pattern = PATTERN_FULL_MDY;
				}
				break;
			case DateFormat.LONG :
				if (format.equals(FORMAT_DATE_YMD)) {
					pattern = PATTERN_LONG_YMD;
				} else if (format.equals(FORMAT_DATE_DMY)) {
					pattern = PATTERN_LONG_DMY;
				} else {
					pattern = PATTERN_LONG_MDY;
				}				
				break;
			case DateFormat.SHORT :
				if (format.equals(FORMAT_DATE_YMD)) {
					pattern = PATTERN_SHORT_YMD;
				} else if (format.equals(FORMAT_DATE_DMY)) {
					pattern = PATTERN_SHORT_DMY;
				} else {
					pattern = PATTERN_SHORT_MDY;
				}
				break;
			default :
				if (format.equals(FORMAT_DATE_YMD)) {
					pattern = PATTERN_MEDIUM_YMD;
				} else if (format.equals(FORMAT_DATE_DMY)) {
					pattern = PATTERN_MEDIUM_DMY;
				} else {
					pattern = PATTERN_MEDIUM_MDY;
				}
				break;
		}
		return pattern;
	}

}