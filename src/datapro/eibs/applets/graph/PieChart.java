package datapro.eibs.applets.graph;

// Processed by NMI's Java Code Viewer 4.8.0 © 1997-1999 B. Lemaire
// Website: http://njcv.htmlplanet.com	E-mail: info@njcv.htmlplanet.com
// Copy registered to Luis Velando - United States
// Source File Name:   PieChart.java

import java.applet.Applet;
import java.awt.*;

public class PieChart extends Applet {

	String title;
	Font font;
	FontMetrics fontMetrics;
	int titleHeight;
	int columns;
	int values[];
	Color colors[];
	String labels[];
	float percent[];
	float angle[];
	int maxLabelWidth;
	int maxValueWidth;
	int max;
	int strWidth;
	boolean showLabel;
	boolean showPercent;
	int lx;
	int ly;
	int cx;
	int cy;

	public PieChart() {
		titleHeight = 15;
		maxLabelWidth = 0;
		maxValueWidth = 0;
		max = 0;
		strWidth = 0;
		showLabel = true;
		showPercent = true;
		lx = 0;
		ly = 0;
		cx = 0;
		cy = 0;
	}
	private void adjustLabel(int i) {
		if(lx > cx && ly < cy) {
			lx += 5;
			ly -= 5;
		}
		if(lx > cx && ly > cy) {
			lx += 5;
			ly += 10;
		}
		if(lx < cx && ly > cy) {
			strWidth = fontMetrics.stringWidth(labels[i]);
			lx -= strWidth + 5;
			if(lx < 0)
				lx = 0;
		}
		if(lx < cx && ly < cy) {
			strWidth = fontMetrics.stringWidth(labels[i]);
			lx -= strWidth + 5;
			if(lx < 0)
				lx = 0;
		}
	}
	public synchronized void init() {
		font = new Font("Sanserif", 0, 10);
		fontMetrics = getFontMetrics(font);
		String bgColor = getParameter("bgcolor");
		if(bgColor == null)
			setBackground(Color.white);
		else
		if(bgColor.equals("red"))
			setBackground(Color.red);
		else
		if(bgColor.equals("green"))
			setBackground(Color.green);
		else
		if(bgColor.equals("blue"))
			setBackground(Color.blue);
		else
		if(bgColor.equals("pink"))
			setBackground(Color.pink);
		else
		if(bgColor.equals("orange"))
			setBackground(Color.orange);
		else
		if(bgColor.equals("magenta"))
			setBackground(Color.magenta);
		else
		if(bgColor.equals("cyan"))
			setBackground(Color.cyan);
		else
		if(bgColor.equals("white"))
			setBackground(Color.white);
		else
		if(bgColor.equals("yellow"))
			setBackground(Color.yellow);
		else
		if(bgColor.equals("gray"))
			setBackground(Color.gray);
		else
		if(bgColor.equals("darkGray"))
			setBackground(Color.darkGray);
		else
			setBackground(Color.decode(bgColor));
		title = getParameter("title");
		if(title == null)
			title = "Pie Chart";
		String temp = getParameter("columns");
		if(temp == null)
			columns = 5;
		else
			columns = Integer.parseInt(temp);
		temp = getParameter("showlabel");
		if(temp == null)
			showLabel = true;
		else
		if(temp.equalsIgnoreCase("YES"))
			showLabel = true;
		else
		if(temp.equalsIgnoreCase("NO"))
			showLabel = false;
		else
			showLabel = false;
		temp = getParameter("showpercent");
		if(temp == null)
			showPercent = true;
		else
		if(temp.equalsIgnoreCase("YES"))
			showPercent = true;
		else
		if(temp.equalsIgnoreCase("NO"))
			showPercent = false;
		else
			showPercent = false;
		values = new int[columns];
		colors = new Color[columns];
		labels = new String[columns];
		percent = new float[columns];
		angle = new float[columns];
		float totalValue = 0.0F;
		for(int i = 0; i < columns; i++) {
			temp = takeDot(getParameter("Pvalue" + (i + 1)));
			if(temp != null)
				try {
					values[i] = Integer.parseInt(takeComma(temp));
				}
				catch(NumberFormatException _ex) {
					values[i] = 0;
				}
			totalValue += values[i];
			if(values[i] > max)
				max = values[i];
			temp = getParameter("Plabel" + (i + 1));
			labels[i] = temp != null ? temp : "";
			maxLabelWidth = Math.max(fontMetrics.stringWidth(labels[i]), maxLabelWidth);
			temp = getParameter("Pcolor" + (i + 1));
			if(temp != null) {
				if(temp.equals("red"))
					colors[i] = Color.red;
				else
				if(temp.equals("green"))
					colors[i] = Color.green;
				else
				if(temp.equals("blue"))
					colors[i] = Color.blue;
				else
				if(temp.equals("pink"))
					colors[i] = Color.pink;
				else
				if(temp.equals("orange"))
					colors[i] = Color.orange;
				else
				if(temp.equals("magenta"))
					colors[i] = Color.magenta;
				else
				if(temp.equals("cyan"))
					colors[i] = Color.cyan;
				else
				if(temp.equals("white"))
					colors[i] = Color.white;
				else
				if(temp.equals("yellow"))
					colors[i] = Color.yellow;
				else
				if(temp.equals("gray"))
					colors[i] = Color.gray;
				else
				if(temp.equals("darkGray"))
					colors[i] = Color.darkGray;
				else
					colors[i] = Color.decode(temp);
			} else {
				colors[i] = Color.gray;
			}
		}

		float multiFactor = 100F / totalValue;
		for(int i = 0; i < columns; i++) {
			percent[i] = (float)values[i] * multiFactor;
			angle[i] = (float)((double)percent[i] * 3.6000000000000001D);
		}

	}
	public synchronized void paint(Graphics g) {
		int x = 0;
		int y = 0;
		int width = 0;
		int height = 0;
		int ax = 0;
		int ay = 0;
		int px = 0;
		int py = 0;
		int radius = 0;
		width = height = Math.min(getSize().width - 100, getSize().height - 100);
		x = y = 50;
		Color borderColor = Color.black;
		if(getSize().width > width)
			x = (getSize().width - width) / 2;
		cx = x + width / 2;
		cy = y + height / 2;
		radius = width / 2;
		strWidth = fontMetrics.stringWidth(title);
		Font fnt = new Font("Sanserif", 1, 16);
		g.setFont(fnt);
		g.setColor(Color.red);
		g.drawString(title, (getSize().width - strWidth) / 2, 15);
		g.setFont(font);
		int initAngle = 90;
		int sweepAngle = 0;
		int incSweepAngle = 0;
		int incLabelAngle = (int)(angle[0] / 2.0F);
		for(int i = 0; i < columns; i++) {
			sweepAngle = Math.round(angle[i]);
			g.setColor(colors[i]);
			if(i == columns - 1) {
				sweepAngle = 360 - incSweepAngle;
				g.fillArc(x, y, width, height, initAngle, -sweepAngle);
				g.setColor(borderColor);
				g.drawArc(x, y, width, height, initAngle, -sweepAngle);
				if(showLabel && percent[i] >= 1.0F) {
					lx = (int)((double)cx + (double)radius * Math.cos(((float)incLabelAngle * 3.14F) / 180F - 1.57F));
					ly = (int)((double)cy + (double)radius * Math.sin(((float)incLabelAngle * 3.14F) / 180F - 1.57F));
					adjustLabel(i);
					g.drawString(labels[i], lx, ly);
				}
				if(showPercent && percent[i] >= 1.0F) {
					px = (int)((double)cx + (double)((radius * 2) / 3) * Math.cos(((float)incLabelAngle * 3.14F) / 180F - 1.57F));
					py = (int)((double)cy + (double)((radius * 2) / 3) * Math.sin(((float)incLabelAngle * 3.14F) / 180F - 1.57F));
					g.drawString(String.valueOf(Math.round(percent[i])) + "%", px, py);
				}
				break;
			}
			g.fillArc(x, y, width, height, initAngle, -sweepAngle);
			g.setColor(borderColor);
			g.drawArc(x, y, width, height, initAngle, -sweepAngle);
			incSweepAngle += sweepAngle;
			ax = (int)((double)cx + (double)radius * Math.cos(((float)incSweepAngle * 3.14F) / 180F - 1.57F));
			ay = (int)((double)cy + (double)radius * Math.sin(((float)incSweepAngle * 3.14F) / 180F - 1.57F));
			g.drawLine(cx, cy, ax, ay);
			if(showLabel && percent[i] >= 1.0F) {
				lx = (int)((double)cx + (double)radius * Math.cos(((float)incLabelAngle * 3.14F) / 180F - 1.57F));
				ly = (int)((double)cy + (double)radius * Math.sin(((float)incLabelAngle * 3.14F) / 180F - 1.57F));
				adjustLabel(i);
				g.drawString(labels[i], lx, ly);
			}
			if(showPercent && percent[i] >= 1.0F) {
				px = (int)((double)cx + (double)((radius * 2) / 3) * Math.cos(((float)incLabelAngle * 3.14F) / 180F - 1.57F));
				py = (int)((double)cy + (double)((radius * 2) / 3) * Math.sin(((float)incLabelAngle * 3.14F) / 180F - 1.57F));
				strWidth = fontMetrics.stringWidth(Math.round(percent[i]) + "%");
				g.drawString(String.valueOf(Math.round(percent[i])) + "%", px - strWidth / 2, py);
			}
			incLabelAngle += (int)(angle[i] / 2.0F + angle[i + 1] / 2.0F);
			initAngle += -sweepAngle;
		}

		g.setColor(borderColor);
		g.drawLine(cx, cy, cx, cy - radius);
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