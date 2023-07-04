package datapro.eibs.applets.graph;

// Processed by NMI's Java Code Viewer 4.8.0 © 1997-1999 B. Lemaire
// Website: http://njcv.htmlplanet.com	E-mail: info@njcv.htmlplanet.com
// Copy registered to Luis Velando - United States
// Source File Name:   Chart.java

import java.applet.Applet;
import java.awt.*;

public class Chart extends Applet {

	static final int VERTICAL = 0;
	static final int HORIZONTAL = 1;
	static final int SOLID = 0;
	static final int STRIPED = 1;
	int orientation;
	String title;
	Font titleFont;
	FontMetrics titleFontMetrics;
	Font valueFont;
	FontMetrics valueFontMetrics;
	int titleHeight;
	int columns;
	int values[];
	String vf[];
	Object colors[];
	Object labels[];
	int styles[];
	int scale;
	int maxLabelWidth;
	int maxValueWidth;
	int barWidth;
	int barSpacing;
	int max;

	public Chart() {
		titleHeight = 15;
		scale = 10;
		barSpacing = 10;
	}
	
	/** This method was created by Frank Hernandez to provide the 
	 * 	Applet Info 
	*/	
	public String[][] getParameterInfo() {
		String[][] info = {
		  // Parameter Name     Kind of Value   Description
			{"title",			"String",       "graphic title"},
			{"columns",			"int",          "total number of columns"},
			{"scale",    		"int",          "scale"},
			{"orientation",     "String",       "vertical or horizontal"},			
			{"cX_value", 		"Number",       "column X value"}, 
			{"cX_valab", 		"String", 		"column X value label"}, 
			{"cX_label",     	"String",       "column X label"},
			{"cX_style",        "String",       "column X style, solid or striped"},
			{"cX_color",        "Color",        "column X color"},
		};
		return info;
	}   	
	
	public synchronized void init() {
		titleFont = new Font("Courier", 1, 12);
		titleFontMetrics = getFontMetrics(titleFont);
		valueFont = new Font("Small", 0, 10);
		valueFontMetrics = getFontMetrics(valueFont);
		
		title = getParameter("title");
		if(title == null)
			title = "";
		
		String rs = getParameter("columns");
		if(rs == null)
			columns = 5;
		else
			columns = Integer.parseInt(rs);
		
		rs = getParameter("scale");
		if(rs == null)
			scale = 1;
		else
			scale = Integer.parseInt(rs);
		
		rs = getParameter("orientation");
		if(rs == null)
			orientation = 0;
		else
		if(rs.toLowerCase().equals("vertical"))
			orientation = 0;
		else
		if(rs.toLowerCase().equals("horizontal"))
			orientation = 1;
		else
			orientation = 0;

		values = new int[columns];
		vf = new String[columns];
		colors = new Color[columns];
		labels = new String[columns];
		styles = new int[columns];

		for(int i = 0; i < columns; i++) {
			//
			rs = getParameter("C" + (i + 1));
			if(rs != null)
				try {
                    vf[i] = getParameter("C" + (i + 1) + "_valab");
                    if(vf[i] == null)
                        vf[i] = formatCCY(rs);
                    rs = takeDot(takeComma(rs));
                    values[i] = Integer.parseInt(rs);
					if(values[i] < 0)
						values[i] = 0;
				}
				catch(NumberFormatException _ex) {
					values[i] = 0x7fffffff;
                    vf[i] = "number error";
                }
			if(values[i] > max)
				max = values[i];
			//
			rs = getParameter("C" + (i + 1) + "_label");
			labels[i] = rs == null ? "" : ((Object) (rs));
			maxLabelWidth = Math.max(titleFontMetrics.stringWidth((String)labels[i]), maxLabelWidth);
			maxValueWidth = Math.max(valueFontMetrics.stringWidth(vf[i]), maxValueWidth);
			//
			rs = getParameter("C" + (i + 1) + "_style");
			if(rs == null || rs.toLowerCase().equals("solid"))
				styles[i] = 0;
			else
			if(rs.toLowerCase().equals("striped"))
				styles[i] = 1;
			else
				styles[i] = 0;
			//
			rs = getParameter("C" + (i + 1) + "_color");
			if(rs != null) {
				if(rs.equals("red"))
					colors[i] = Color.red;
				else
				if(rs.equals("green"))
					colors[i] = Color.green;
				else
				if(rs.equals("blue"))
					colors[i] = Color.blue;
				else
				if(rs.equals("pink"))
					colors[i] = Color.pink;
				else
				if(rs.equals("orange"))
					colors[i] = Color.orange;
				else
				if(rs.equals("magenta"))
					colors[i] = Color.magenta;
				else
				if(rs.equals("cyan"))
					colors[i] = Color.cyan;
				else
				if(rs.equals("white"))
					colors[i] = Color.white;
				else
				if(rs.equals("yellow"))
					colors[i] = Color.yellow;
				else
				if(rs.equals("gray"))
					colors[i] = Color.gray;
				else
				if(rs.equals("darkGray"))
					colors[i] = Color.darkGray;
				else
					colors[i] = Color.decode(rs);
			} else {
				colors[i] = Color.gray;
			}
		}

		int lg = Integer.toString(max).length();
		String aux = "1";
		if(lg > 3) {
			lg -= 3;
			for(int i = 0; i < lg; i++)
				aux = aux + "0";

			scale = Integer.parseInt(aux);
		}
		switch(orientation) {
		case 0: // '\0'
		default:
			barWidth = maxLabelWidth <= maxValueWidth ? maxValueWidth : maxLabelWidth;
			resize(Math.max(columns * (barWidth + barSpacing), titleFontMetrics.stringWidth(title)) + titleFont.getSize() + 5, max / scale + 2 * titleFont.getSize() + 5 + titleFont.getSize());
			return;

		case 1: // '\001'
			barWidth = titleFont.getSize();
			break;

		}
		resize(Math.max(max / scale + titleFontMetrics.stringWidth("" + max), titleFontMetrics.stringWidth(title)) + maxLabelWidth + 5, columns * (barWidth + barSpacing) + titleFont.getSize() + 10);
	}
	
	public synchronized void paint(Graphics g) {
		int cx = 0;
		int cy = 0;
		g.setColor(Color.black);
		int i = titleFontMetrics.stringWidth(title);
		g.setFont(titleFont);
		g.drawString(title, Math.max((size().width - i) / 2, 0), size().height - titleFontMetrics.getDescent());
		for(i = 0; i < columns; i++)
			switch(orientation) {
			case 0: // '\0'
			default:
				cx = Math.max(barWidth + barSpacing, maxLabelWidth) * i + barSpacing;
				cx += Math.max((size().width - columns * (barWidth + 2 * barSpacing)) / 2, 0);
				cy = size().height - values[i] / scale - 2 - 2 * titleFont.getSize();
				g.setColor(Color.black);
				g.drawString((String)labels[i], cx, size().height - titleFont.getSize() - titleFontMetrics.getDescent());
				if(colors[i] == Color.black)
					g.setColor(Color.gray);
				g.fillRect(cx + 5, cy - 3, barWidth, values[i] / scale);
				g.setColor((Color)colors[i]);
				switch(styles[i]) {
				case 0: // '\0'
				default:
					g.fillRect(cx, cy, barWidth, values[i] / scale);
					break;

				case 1: // '\001'
					int steps = values[i] / scale / 2;
					for(int j = 0; j < steps; j++) {
						int ys = cy + 2 * j;
						g.drawLine(cx, ys, cx + barWidth, ys);
					}

					break;

				}
				g.setFont(valueFont);
				g.drawString(vf[i], cx, cy - valueFontMetrics.getDescent());
				g.setFont(titleFont);
				break;

			case 1: // '\001'
				cy = (barWidth + barSpacing) * i + barSpacing;
				cx = maxLabelWidth + 1;
				cx += Math.max((size().width - (maxLabelWidth + 1 + titleFontMetrics.stringWidth("" + max) + max / scale)) / 2, 0);
				g.setColor(Color.black);
				g.drawString((String)labels[i], cx - maxLabelWidth - 1, cy + titleFontMetrics.getAscent());
				if(colors[i] == Color.black)
					g.setColor(Color.gray);
				g.fillRect(cx + 3, cy + 5, values[i] / scale, barWidth);
				g.setColor((Color)colors[i]);
				switch(styles[i]) {
				case 0: // '\0'
				default:
					g.fillRect(cx, cy, values[i] / scale, barWidth);
					break;

				case 1: // '\001'
					int steps = values[i] / scale / 2;
					for(int j = 0; j < steps; j++) {
						int ys = cx + 2 * j;
						g.drawLine(ys, cy, ys, cy + barWidth);
					}

					break;

				}
				g.setFont(valueFont);
				g.drawString(vf[i], cx + values[i] / scale + 3, cy + valueFontMetrics.getAscent());
				g.setFont(titleFont);
				break;

			}

	}
	
	/** This method was fixed by Frank Hernandez to prevent repeating the comma
		in the result variable when formatting the number
	*/	
	public static String formatCCY(String num) {
		String num2 = "";
		String result = "";
		String sign = "";
		if(num.startsWith("-")) {
			sign = num.substring(0, 1);
			num = num.substring(1);
		}
		int posi = num.indexOf(".");
		if(posi > -1) {
			int x = num.length();
			num2 = num.substring(x - 3, x);
			num = num.substring(0, x - 3);
		} else {
			num2 = ".00";
		}
		int count = 0;
		int y = num.length() - 1;
		for(int x = y; x > -1; x--) {
			char nx = num.charAt(x);
			if (nx != ','){
				result = nx + result; 
				if((++count == 3 || count == 6 || count == 9 || count == 12) && x > 0)
					result = "," + result;
			}
		}

		result = sign + result + num2;
		return result;
	}
	
	public static String takeDot(String number) {
		String result = "";
		int posi = number.indexOf(".");
		if(posi > -1)
			result = number.substring(0, posi);
		else
			result = number;
		return result;
	}
	
	/** This method was created by Frank Hernandez
	*/	
	public static String takeComma(String number) {
		String result = "";
		int y = number.length() - 1;
		for(int x = y; x > -1; x--) {
			char nx = number.charAt(x);
			if (nx != ',') result = nx + result;			
		}
		return result;
	}
	
}