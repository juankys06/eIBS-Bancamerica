package datapro.eibs.master;

import java.util.*;
import java.text.*;

import datapro.eibs.beans.JBObjList;
import datapro.eibs.sockets.*;
//import datapro.eibs.beans.*;
import javax.servlet.http.*;

/**
 * This type was created in VisualAge.
 */
public class Util {
/**
 * Util constructor comment.
 */
public Util() {
	super();
}
/**
 * This method was created in VisualAge.
 */
public static String addDot(String number) {
  String result = "";
  
  if(number.length() == 1){number = number + "00";}
  result = number.substring(0, number.length()-2) + "." + number.substring(number.length()-2);
  return(result);
}
/**
 * This method was created in VisualAge.
 */
public static String addLeftChar(char ch, int len, String str) {

	for (int i = str.length(); i < len; i++){
		str = ch + str;
	}
  
	return(str);
}
/**
 * This method was created in VisualAge.
 */
public static String fcolorCCY(String num) {
 
	num = formatCCY(num);
 	if(num.startsWith("-")) 
  		num = "<FONT COLOR=RED>" + num + "</FONT>";
  	
  	return (num);
  	
}
/**
 * Insert the method's description here.
 * Creation date: (6/19/2000 3:13:46 PM)
 * @return java.lang.String
 */
public static String formatAdd(String s1, String s2, String s3) {
	String r = "";
	
	if (!s1.trim().equals("")) {
		r += s1.trim() + "<br>";
	}
	if (!s2.trim().equals("")) {
		r += s2.trim() + "<br>";
	}
	if (!s3.trim().equals("")) {
		r += s3.trim() + "<br>";
	}
	if (r.trim().equals("")) {
		r = "&nbsp;";
	}
	return r;
}
/**
 * Insert the method's description here.
 * Creation date: (6/19/2000 3:13:46 PM)
 * @return java.lang.String
 */
public static String formatBlank(String s) {
	String r = null;
	
	if (s.trim().equals("&nbsp;")) {
		r = "";
	}
	else {
		r = s.trim();
	}
	return r;
}

/**
 * This method was created in VisualAge.
 */
public static String formatCCY(double value) {
	return DecimalField.formatCCY(value);
}

/**
 * This method was created in VisualAge.
 */
public static String formatCCY(String value) {
	double numDouble = 0.0;

	try {
		numDouble = Double.parseDouble(value);
	} catch (NumberFormatException e1) {
		try {
			numDouble = DecimalField.parseDouble(value);
		} catch (NumberFormatException e2) {
			return value;
		} 
	}
	return DecimalField.formatCCY(numDouble);
}

/**
 * This method was created in VisualAge.
 */
public static String formatCCY(double value, String CCYS) {
	return CCYS + formatCCY(value);
}

/**
 * This method was created in VisualAge.
 */
public static String formatCCY(String value, String CCYS) {
  	return CCYS + formatCCY(value);
}

/**
 * This method was created in VisualAge.
 */
public static double parseCCYtoDouble(String value) {
  	double numDouble = 0.0;
  	char spt = DecimalField.getDecimalSeparator();
  	
	value = takeCharacter(value, DecimalField.getGroupingSeparator());
  	if (spt != '.') value = value.replace(spt, '.');
	numDouble = Double.parseDouble(value);
  	return(numDouble);
}

/**
 * This method was created in VisualAge.
 */
public static String takeCharacter(String s, char ch) {
  String result = null;
  int posi = s.indexOf(ch);
  if(posi > -1){
	  while(posi > -1){
		  result = s.substring(0,posi);
		  result = result + s.substring(posi+1);
		  posi = result.indexOf(ch);
		  s = result;
	  }    
  }
  else{
	   result = s;
  }
  return(result);
}

/**
 * Insert the method's description here.
 * Creation date: (6/19/2000 3:13:46 PM)
 * @return java.lang.String
 */
public static String formatCell(String s) {
	String r = null;
	String rs = null;
	String ls = null;
	int pos = 0;
	if (s.trim().equals("")) {
		r = "&nbsp;";
	}
	else {
		s = s.trim();
		while (s.indexOf("'") != -1) {
			pos = s.indexOf("'");
			ls = s.substring(0,pos);
			rs = s.substring(pos + 1,s.length());
			s = ls + "&#39;" + rs;
		}
		
		while (s.indexOf("\"") != -1) {
			pos = s.indexOf("\"");
			ls = s.substring(0,pos);
			rs = s.substring(pos + 1,s.length());
			s = ls + "&#34;" + rs;
		}
		r = s;
	}
	return r;
}
/**
 * Insert the method's description here.
 * Creation date: (6/19/2000 3:38:43 PM)
 * @return java.lang.String
 * @param d1 java.lang.String
 * @param d2 java.lang.String
 * @param d3 java.lang.String
 */
public static String formatDate(String d1, String d2, String d3) {

	if (d1.length() == 0) d1 = "0";
	if (d2.length() == 0) d2 = "0";
	if (d3.length() == 0) d3 = "0";

	d1 	=  d1.length() == 1 ? "0" + d1  : d1;
	d2 	=  d2.length() == 1 ? "0" + d2  : d2;
	d3 	=  d3.length() == 1 ? "0" + d3  : d3;

	if (d1.equals("00") && d2.equals("00") && d3.equals("00"))
		return "&nbsp;";
	else
		return (d1 + "/" + d2 + "/" + d3);
	
}
/**
 * Insert the method's description here.
 * Creation date: (6/19/2000 3:13:46 PM)
 * @return java.lang.String
 */
public static String formatID(String c, String l) {
	String fc = "";
	for (int i = 0; i < (9 - c.length()); i++){
	  	fc += "0";
	}
	String fl = "";
	for (int i = 0; i < (4 - l.length()); i++){
		fl += "0";
	}
	return (fc + c + fl + l);
}
/**
 * Insert the method's description here.
 * Creation date: (6/19/2000 3:38:43 PM)
 * @return java.lang.String
 * @param d1 java.lang.String
 * @param d2 java.lang.String
 * @param d3 java.lang.String
 */
public static String formatTime(String t) {

	int len = t.length();
	String time = (len < 6) ? addLeftChar('0', 6, t) : t;	
	String hh = addLeftChar('0', 2, time.substring(0, 2));
	String mm = addLeftChar('0', 2, time.substring(2, 4));
	String ss = addLeftChar('0', 2, time.substring(4, time.length()));
	
	return hh + ":" + mm + ":" + ss;
}

/**
 * Insert the method's description here.
 * Creation date: (6/19/2000 3:38:43 PM)
 * @return java.lang.String
 * @param d1 java.lang.String
 * @param d2 java.lang.String
 * @param d3 java.lang.String
 */
public static String formatYear(int y) {

	return formatYear(Integer.toString(y));
		
}
/**
 * Insert the method's description here.
 * Creation date: (6/19/2000 3:38:43 PM)
 * @return java.lang.String
 * @param d1 java.lang.String
 * @param d2 java.lang.String
 * @param d3 java.lang.String
 */
public static String formatYear(String y) {

	if (y.length() < 4) {
		try {
			int aux = Integer.parseInt(y);
			if (aux == 0) {
				y = "2000";
			}
			else if (aux > 52) {
				y = "19" + y;
			}
			else {
				y = "20" + justifyRight(y,2);
			}
		}
		catch (Exception e) {
			y = "";
		}
	}
		
	return y;
	
}
/**
 * Insert the method's description here.
 * Creation date: (6/19/2000 3:13:46 PM)
 * @return java.lang.String
 */
public static String justifyLeft(String c, int l) {
	String fc = "";
	for (int i = 0; i < (l - c.length()); i++){
	  	fc += "0";
	}
	return (c + fc);
}
/**
 * Insert the method's description here.
 * Creation date: (6/19/2000 3:13:46 PM)
 * @return java.lang.String
 */
public static String justifyRight(String c, int l) {
	String fc = "";
	for (int i = 0; i < (l - c.length()); i++){
	  	fc += "0";
	}
	return (fc + c);
}
/**
 * Insert the method's description here.
 * Creation date: (7/13/2000 5:18:52 PM)
 * @return java.lang.String
 * @param s java.lang.String
 */
public static String leftValue(String s) {
  	
  	int pos = 0;
  	for (pos = 0; pos < s.length(); pos++){
  		if (s.charAt(pos) == '_')
  			break;
  	}
  	return (s.substring(0, pos));
	
}
/**
 * Insert the method's description here.
 * Creation date: (7/13/2000 5:18:52 PM)
 * @return java.lang.String
 * @param s java.lang.String
 */
public static String rightValue(String s) {
  	
  	int pos = 0;
  	for (pos = 0; pos < s.length(); pos++){
  		if (s.charAt(pos) == '_')
  			break;
  	}
  	if (pos == s.length()) 
	  	return ("0");
  	else 
  		return (s.substring(pos + 1));
  		
	
}
/**
 * This method was created in VisualAge.
 */
public static String[] splitField(String field, int row, int col ) {
  String[] result = new String[row];
  for (int i = 0; i < row; i++){
  	result[i] = "";	
  }
  if (field.length()>0) {
	  if (field.length()<= col) {
		  result[0] = field;
		  }
	  else {
		  int enter = field.length() / col;
		  double rest = field.length() % col;
		  if (rest > 0) {
			  enter++; 
			  }
		  for (int i = 0; i < enter; i++) {
			  if (i == enter -1) {
				  result[i] = field.substring(i*col,field.length());
				  }
			  else {
				  result[i] = field.substring(i*col,i*col + col);
				  }
			  }
		  }
	  }
  
  return(result);
}
/**
 * This method was created in VisualAge.
 */
public static String takeComa(String number) {
  String result = "";
  int posi = number.indexOf(",");
  if(posi > -1){
	  while(posi > -1){
		  result = number.substring(0,posi);
	      result = result + number.substring(posi+1);
	      posi = result.indexOf(",");
	      number = result;
	  }    
  }
  else{
	   result = number;
  }
  return(result);
}
/**
 * This method was created in VisualAge.
 */
public static String takeDot(String number) {
  String result = "";
  int posi = number.indexOf(".");
  if(posi > -1){
	   result = number.substring(0,posi);
	   result = result + number.substring(posi+1);
  }
  else{
	   result = number + "00";
  }
  return(result);
}

/**
 * This method was created in VisualAge.
 */
public static String addRightChar(char ch, int len, String str) {

	for (int i = str.length(); i < len; i++){
		str = str + ch;
	}
  
	return(str);
}

/**
 * Insert the method's description here.
 * Creation date: (6/19/2000 3:13:46 PM)
 * @return java.lang.String
 */
public static String concat(String s[], String ch) {
	String r = "";
	
	int count = 0;
	while (true) {
		try {
			if (!r.equals("") && !s[count].trim().equals("")) 
				r += ch + s[count].trim();
			else
				r += s[count].trim();
			count++;
		}
		catch (Exception e) {
			break;
		}
	}
	return r;
}

/**
 * Insert the method's description here.
 * Creation date: (6/19/2000 3:13:46 PM)
 * @return java.lang.String
 */
public static String concatBR(String s[]) {
	String r = "";
	
	int count = 0;
	while (true) {
		try {
			if (!r.equals("") && !s[count].trim().equals("")) 
				r += "<BR>" + s[count].trim();
			else
				r += s[count].trim();
			count++;
		}
		catch (Exception e) {
			break;
		}
	}
	return r;
}

/**
 * Insert the method's description here.
 * Creation date: (6/19/2000 3:38:43 PM)
 * @return java.lang.String
 * @param d1 java.lang.String
 * @param d2 java.lang.String
 * @param d3 java.lang.String
 */
public static String fcolorDate(String d1, String d2, String d3, String mature) {

	d1 	=  d1.length() == 1 ? "0" + d1  : d1;
	d2 	=  d2.length() == 1 ? "0" + d2  : d2;
	d3 	=  d3.length() == 1 ? "0" + d3  : d3;
	
	String Month = "";
	String Day ="";
	String Year = "";
	String Dat = "";
	String fcolor = mature;
	String date = "";
	String fdate= "";
	
	if (d1.equals("00") && d2.equals("00") && d3.equals("00"))
		return "&nbsp;";
	else

	
	    date = d1 + "/" + d2 + "/" + d3;
		if(mature == "R"){
		fdate = "<FONT COLOR=RED>" + date + "</FONT>";
		}
		else if(mature == "O"){
		fdate = "<FONT COLOR=ORANGE>" + date + "</FONT>";
		}
		else {
		fdate = d1 + "/" + d2 + "/" + d3;
		}
		
		return (fdate);
	
}

/**
 * Insert the method's description here.
 * Creation date: (6/19/2000 3:38:43 PM)
 * @return java.lang.String
 * @param d1 java.lang.String
 * @param d2 java.lang.String
 * @param d3 java.lang.String
 */
public static String fcolorDateFull(String fmt, String lg, String d1, String d2, String d3, String mature) {

	d1 	=  d1.length() == 1 ? "0" + d1  : d1;
	d2 	=  d2.length() == 1 ? "0" + d2  : d2;
	d3 	=  d3.length() == 1 ? "0" + d3  : d3;
	
	String fcolor = mature;
	String date = "";
	String fdate= "";
	String Month = "";
	String Day ="";
	String Year = "";
	String Dat = "";
	


	if (d1.equals("00") && d2.equals("00") && d3.equals("00")) {
		return "&nbsp;";
	}

	if(fmt.equals("MDY")){
		Month = d1;
		Day = d2;
		Year = d3;
	}
	else if(fmt.equals("DMY")){
		Month = d2;
		Day = d1;
		Year = d3;
	}
	else if(fmt.equals("YMD")){
		Month = d2;
		Day = d3;
		Year = d1;
	}
	else if(fmt.equals("YDM")){
		Month = d3;
		Day = d2;
		Year = d1;
	}
	
	
	JSEIBSMSGProp.setPropertyFileLang(lg);
	switch (Integer.parseInt(Month)) {
		case 1 : {
			Month = JSEIBSMSGProp.getMSG0005();	
			break;	
		}
		case 2 : {
			Month = JSEIBSMSGProp.getMSG0006();			
			break;	
		}
		case 3 : {
			Month = JSEIBSMSGProp.getMSG0007();			
			break;	
		}
		case 4 : {
			Month = JSEIBSMSGProp.getMSG0008();			
			break;	
		}
		case 5 : {
			Month = JSEIBSMSGProp.getMSG0009();			
			break;	
		}
		case 6 : {
			Month = JSEIBSMSGProp.getMSG0010();			
			break;	
		}
		case 7 : {
			Month = JSEIBSMSGProp.getMSG0011();			
			break;	
		}
		case 8 : {
			Month = JSEIBSMSGProp.getMSG0012();			
			break;	
		}
		case 9 : {
			Month = JSEIBSMSGProp.getMSG0013();			
			break;	
		}
		case 10 : {
			Month = JSEIBSMSGProp.getMSG0014();			
			break;	
		}
		case 11 : {
			Month = JSEIBSMSGProp.getMSG0015();			
			break;	
		}
		case 12 : {
			Month = JSEIBSMSGProp.getMSG0016();			
			break;	
		}
	}

	if(fmt.equals("MDY")){
		Dat = Month + "-" + Day + "-" + Year;
	}
	else if(fmt.equals("DMY")){
		Dat = Day + "-" + Month + "-" + Year;	
	}
	else if(fmt.equals("YMD")){
  		Dat = Year + "-" + Month + "-" + Day;
	}
	else if(fmt.equals("YDM")){
  	  	Dat = Year + "-" + Day + "-" + Month;
	}

		if(mature == "R"){
		fdate = "<FONT COLOR=RED>" + Dat + "</FONT>";
		}
		else if(mature == "O"){
		fdate = "<FONT COLOR=ORANGE>" + Dat + "</FONT>";
		}
		else {
		fdate = Dat;
		}
		
		return (fdate);
	
}

/**
 * Insert the method's description here.
 * Creation date: (6/19/2000 3:38:43 PM)
 * @return java.lang.String
 * @param d1 java.lang.String
 * @param d2 java.lang.String
 * @param d3 java.lang.String
 */
public static String formatDate(String d) {

	d 	=  d.length() == 5 ? "0" + d  : d;
	if (d.length() == 6) {
		String d1 = d.substring(0, 2);
		String d2 = d.substring(2, 4);
		String d3 = d.substring(4, 6);
		return (d1 + "/" + d2 + "/" + d3);
	}
	else {
		return "";
	}	
}

/**
 * Insert the method's description here.
 * Creation date: (6/19/2000 3:38:43 PM)
 * @return java.lang.String
 * @param d1 java.lang.String
 * @param d2 java.lang.String
 * @param d3 java.lang.String
 */
public static String formatDateFull(String fmt, String lg, String d1, String d2, String d3) {

	String Month = "";
	String Day ="";
	String Year = "";
	String Dat = "";
	
	if (d1.length() == 0) d1 = "0";
	if (d2.length() == 0) d2 = "0";
	if (d3.length() == 0) d3 = "0";
	
	d1 	=  d1.length() == 1 ? "0" + d1  : d1;
	d2 	=  d2.length() == 1 ? "0" + d2  : d2;
	d3 	=  d3.length() == 1 ? "0" + d3  : d3;
	

	if (d1.equals("00") && d2.equals("00") && d3.equals("00")) {
		return "&nbsp;";
	}

	if(fmt.equals("MDY")){
		Month = d1;
		Day = d2;
		Year = d3;
	}
	else if(fmt.equals("DMY")){
		Month = d2;
		Day = d1;
		Year = d3;
	}
	else if(fmt.equals("YMD")){
		Month = d2;
		Day = d3;
		Year = d1;
	}
	else if(fmt.equals("YDM")){
		Month = d3;
		Day = d2;
		Year = d1;
	}
	
	
	JSEIBSMSGProp.setPropertyFileLang(lg);
	switch (Integer.parseInt(Month)) {
		case 1 : {
			Month = JSEIBSMSGProp.getMSG0005();	
			break;	
		}
		case 2 : {
			Month = JSEIBSMSGProp.getMSG0006();			
			break;	
		}
		case 3 : {
			Month = JSEIBSMSGProp.getMSG0007();			
			break;	
		}
		case 4 : {
			Month = JSEIBSMSGProp.getMSG0008();			
			break;	
		}
		case 5 : {
			Month = JSEIBSMSGProp.getMSG0009();			
			break;	
		}
		case 6 : {
			Month = JSEIBSMSGProp.getMSG0010();			
			break;	
		}
		case 7 : {
			Month = JSEIBSMSGProp.getMSG0011();			
			break;	
		}
		case 8 : {
			Month = JSEIBSMSGProp.getMSG0012();			
			break;	
		}
		case 9 : {
			Month = JSEIBSMSGProp.getMSG0013();			
			break;	
		}
		case 10 : {
			Month = JSEIBSMSGProp.getMSG0014();			
			break;	
		}
		case 11 : {
			Month = JSEIBSMSGProp.getMSG0015();			
			break;	
		}
		case 12 : {
			Month = JSEIBSMSGProp.getMSG0016();			
			break;	
		}
	}

	if(fmt.equals("MDY")){
		Dat = Month + "-" + Day + "-" + Year;
	}
	else if(fmt.equals("DMY")){
		Dat = Day + "-" + Month + "-" + Year;	
	}
	else if(fmt.equals("YMD")){
  		Dat = Year + "-" + Month + "-" + Day;
	}
	else if(fmt.equals("YDM")){
  	  	Dat = Year + "-" + Day + "-" + Month;
	}
	    
  	return(Dat);
}

/**
 * Insert the method's description here.
 * Creation date: (6/19/2000 3:38:43 PM)
 * @return java.lang.String
 * @param d1 java.lang.String
 * @param d2 java.lang.String
 * @param d3 java.lang.String
 */
public static String formatCustomDate(String fmt_eibs, String mask, String lang, String d1, String d2, String d3)
{
	String Month = "";
	String Day ="";
	String Year = "";
	String Dat = "";
	DateFormat myDateFormat = new SimpleDateFormat(mask, new Locale(lang,"",""));
	
	if (d1.length() == 0) d1 = "0";
	if (d2.length() == 0) d2 = "0";
	if (d3.length() == 0) d3 = "0";
	
	d1 	=  d1.length() == 1 ? "0" + d1  : d1;
	d2 	=  d2.length() == 1 ? "0" + d2  : d2;
	d3 	=  d3.length() == 1 ? "0" + d3  : d3;

	if (d1.equals("00") && d2.equals("00") && d3.equals("00")) {
		return "&nbsp;";
	}

	if(fmt_eibs.equals("MDY")){
		Month = d1;
		Day = d2;
		Year = d3;
	}
	else if(fmt_eibs.equals("DMY")){
		Month = d2;
		Day = d1;
		Year = d3;
	}
	else if(fmt_eibs.equals("YMD")){
		Month = d2;
		Day = d3;
		Year = d1;
	}
	else if(fmt_eibs.equals("YDM")){
		Month = d3;
		Day = d2;
		Year = d1;
	}
	
	Calendar myCalendar = Calendar.getInstance();
	myCalendar.set(Integer.parseInt(formatYear(Year)), Integer.parseInt(Month) - 1, Integer.parseInt(Day));
	Date myDate = myCalendar.getTime();
	
	String myReturn = myDateFormat.format(myDate);
	return myReturn;	
}

/**
 * Insert the method's description here.
 * Creation date: (6/19/2000 3:13:46 PM)
 * @return java.lang.String
 */
public static String trim(String s) {
	return s.trim();
}
/**
 * metodo para verificar si un archivo existe
 * 
 * */
public static boolean existFile(String filename){
	boolean exists = false;
	try {
		java.io.File f = new java.io.File(filename);
		exists = f.exists();
	} catch (Exception e) {
		exists = false;
	}
	return exists;
}

public static String unformatHTML(String s) {

	String rs = null;
	String ls = null;
	int pos = 0;
	s = s.trim();
	if (!s.equals("")) {
		while (s.indexOf("&#39;") != -1) {
			pos = s.indexOf("&#39;");
			ls = s.substring(0, pos);
			rs = s.substring(pos + 5, s.length());
			s = ls + "'" + rs;
		}

		while (s.indexOf("&#34;") != -1) {
			pos = s.indexOf("&#34;");
			ls = s.substring(0, pos);
			rs = s.substring(pos + 5, s.length());
			s = ls + "\"" + rs;
		}
	}
	return s;

}

public static String replace(String Text, String Old, String New){
		if (Old.length() == 0) return Text;
		StringBuffer buf = new StringBuffer();
		int i=0, j=0;
		while((i = Text.indexOf(Old, j)) > -1){
			buf.append(Text.substring(j,i) + New);
			j = i + Old.length();
		}
		if (j < Text.length())
			buf.append(Text.substring(j));
		return buf.toString();
}

public static HashMap getPropertiesKeysValues(String file) throws MissingResourceException {
	HashMap keys_values = new HashMap();
		
	try {
		PropertyResourceBundle props = (PropertyResourceBundle)PropertyResourceBundle.getBundle(file);

		Enumeration enum = props.getKeys();
		while(enum.hasMoreElements()) {
			String strKey = (String)enum.nextElement();
			keys_values.put(strKey, props.getString(strKey));
		}	
	} catch (Exception e) {	
		e.printStackTrace();
	}
	return keys_values;
}

public static JBObjList getYearList(int begin, int ending) {
	JBObjList result = new JBObjList();
	
	while (begin <= ending) {
		result.addRow(String.valueOf(begin));
		begin++;
	}
	
	return result;
}

/**
 * Format Date and Time XX/XX/XX HH:MM:SS
 * Creation date: (7/3/2008 3:38:43 PM)
 * Format and convert from MDY to fmt
 * @return java.lang.String
 * @param d1 java.lang.String  (12)
 * @param fmt java.lang.String  (3) Original format
 */
public static String formatDateTime(String f,  String t) {

	String d = " ";
	t 	=  t.length() == 11 ? "0" + t  : t;
	
	if (t.length() == 12) {
		String d1 = t.substring(0, 2);
		String d2 = t.substring(2, 4);
		String d3 = t.substring(4, 6);
		String t1 = t.substring(6, 8);
		String t2 = t.substring(8, 10);
		String t3 = t.substring(10, 12);
		
		if(f.equals("MDY")){
			d = (d1 + "/" + d2 + "/" + d3 + " " + t1 + ":" + t2 + ":" + t3);
		}
		else if(f.equals("DMY")){
			d = (d2 + "/" + d1 + "/" + d3 + " " + t1 + ":" + t2 + ":" + t3);
		}
		else if(f.equals("YMD")){
			d = (d3 + "/" + d1 + "/" + d2 + " " + t1 + ":" + t2 + ":" + t3);
		}
		else if(f.equals("YDM")){
			d =  (d3 + "/" + d2 + "/" + d1 + " " + t1 + ":" + t2 + ":" + t3);
		}
	}
	
	return (d);
	
}
}