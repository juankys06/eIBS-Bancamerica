package datapro.eibs.tools.pdf;

import datapro.eibs.master.Util;
import datapro.eibs.beans.*;
import com.lowagie.text.*;
import com.lowagie.text.pdf.*;

/**
 * Class to hold the Element to be print in a PDF document.
 */
	public class XMLPDFElement {
		
		final public static int ALIGN_LEFT = 0;
		final public static int ALIGN_CENTER = 1;
		final public static int ALIGN_RIGHT = 2;
		
		private String sType = "";
		private String sValue = "";
		private String sAmount = "";	
		private String sD1 = "";	
		private String sD2 = "";
		private String sD3 = "";
		private String sASymbol = "$";
		private String sDFormat = "dd 'de' MMMM yyyy";
		private String sDLang = "es";
		private int iLength = 0;
		private int iOffset = 0;
		private boolean bFill = true;
		private float fSize = 10;
		private float fX = 20;
		private float fY = 20;
		private float fWidth = 0;
		private int iAlign = -1;
		private Font fFont = FontFactory.getFont(FontFactory.HELVETICA, 7, Font.NORMAL);
		
		public void setType(String type){
			sType = type;
		}
		public String getType(){
			return sType;
		}
		public void setText(String text){
			sValue = Util.unformatHTML(text);
			/*
			String rs = null;
			String ls = null;
			int pos = 0;
			sValue = sValue.trim();
			if (!sValue.equals("")) {
				while (sValue.indexOf("&#39;") != -1) {
					pos = sValue.indexOf("&#39;");
					ls = sValue.substring(0, pos);
					rs = sValue.substring(pos + 5, sValue.length());
					sValue = ls + "'" + rs;
				}

				while (sValue.indexOf("&#34;") != -1) {
					pos = sValue.indexOf("&#34;");
					ls = sValue.substring(0, pos);
					rs = sValue.substring(pos + 5, sValue.length());
					sValue = ls + "\"" + rs;
				}
			}
			*/
			if (sType.equals("amount")) sAmount = Util.formatCCY(sValue);
		}
		public String getText(){
			String temp = ""; 
			int iEnd = 0; // end of substring
			if (sValue.length() != 0) {
				iEnd = iOffset + iLength;
				iEnd = sValue.length() < iEnd || iLength == 0 ? sValue.length() : iEnd;
			   /*
			    if (sValue.length() < iEnd){
					StringBuffer sb = new StringBuffer(sValue);
					while (sb.length() <= iEnd)
						sb.append(' ');
					sValue=sb.toString();
				}
				*/
				if (iLength > 0 && bFill == true) {
					if (sType.equals("")){
						temp = Util.addRightChar(' ', iLength - sValue.length(), sValue.substring(iOffset, iEnd));
					} else if (sType.equals("amount")){
						temp = Util.addLeftChar('0', iLength, sValue);
					} else if (sType.equals("number")){
						temp = Util.addLeftChar('0', iLength, sValue);
					}					
				} else {
					if (sType.equals("")){
						temp = sValue.substring(iOffset, iEnd);
					} else if (sType.equals("date")){
						temp = Util.formatDate(sD1,sD2,sD3);
					} else {
						temp = sValue;
					}
				}
			}
			return temp;
		}
		public String getValue(){
			return sValue;
		}		
		public void setDate(String d1, String d2, String d3){
			sD1 = d1;
			sD2 = d2;
			sD3 = d3;
			sType = "date";
			sValue = Util.formatDate(sD1,sD2,sD3);
		}
		public String getDate(){
			return Util.formatDate(sD1,sD2,sD3);
		}
		public String getCustomFormatDate(ESS0030DSMessage user, String lang){
			return Util.formatCustomDate(user.getE01DTF(), sDFormat , lang , sD1, sD2, sD3);
		}
		public void setDateFormat(String format){
			sDFormat= format;
		}
		public String getDateFormat(){
			return sDFormat;
		}
		public void setLang(String lang){
			sDLang = lang;
		}
		public String getLang(){
			return sDLang;
		}		
		public void setAmountSymbol(String symbol){
			sASymbol = symbol;
		}
		public String getAmountSymbol(){
			return sASymbol;
		}
		public String getFullAmount(){
			return sASymbol + Util.addLeftChar('*', iLength, sAmount);
		}
		public void setLength(int len){
			iLength = len;
		}
		public float getLength(){
			return iLength;
		}
		public void setOffset(int offset){
			if (offset > sValue.length()) offset = sValue.length(); 
			iOffset = offset;
		}
		public int getOffset(){
			return iOffset;
		}
		public void setToFill(boolean fill){ 
			bFill = fill;
		}
		public boolean getToFill(){
			return bFill;
		}		
		public void setSize(float size){
			fSize = size;
		}
		public float getSize(){
			return fSize;
		}
		public void setX(float x){
			fX = x;
		}
		public float getX(){
			return fX;
		}
		public void setY(float y){
			fY = y;
		}
		public float getY(){
			return fY;
		}
		public void setWidth(float width){
			fWidth = width;
		}
		public float getWidth(){
			return fWidth;
		}
		public void setAlign(String align){
			if (align.equals("left")){
				iAlign = ALIGN_LEFT;
			} else if (align.equals("right")){
				iAlign = ALIGN_RIGHT;
			}
		}
		
		public void setAlign(int align){
			if (align == 0 || align == 2) iAlign = align;
		}		
		public int getAlign(){
			return iAlign;
		}
		public void setFont(String font, int size, int type){
			fFont = FontFactory.getFont(font, size, type);
		}
		public void setFont(Font font){
				fFont= font;
		}			
	}