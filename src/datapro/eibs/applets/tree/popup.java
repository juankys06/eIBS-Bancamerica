package datapro.eibs.applets.tree;

// Processed by NMI's Java Code Viewer 4.8.0 © 1997-1999 B. Lemaire
// Website: http://njcv.htmlplanet.com	E-mail: info@njcv.htmlplanet.com
// Copy registered to Evaluation Copy
// Source File Name:   popup.java

import java.awt.*;

public class popup
	implements Runnable {

	TreeApp m_applet;
	private Thread m_popup;
	private boolean m_displayed;
	private long m_when;
	int m_x;
	int m_y;
	String m_text;
	public Color m_backCol;
	public Color m_textCol;

	public popup(TreeApp treeapp) {
		m_backCol = Color.yellow;
		m_textCol = Color.black;
		try {
			m_applet = treeapp;
			m_popup = new Thread(this);
			m_displayed = false;
			m_text = null;
			return;
		}
		catch(Exception _ex) {
			return;
		}
	}
	private final String breakString(String s, int i, FontMetrics fontmetrics) {
		if(s.length() == 0)
			return null;
		if(fontmetrics.stringWidth(s) < i)
			return s;
		String s1 = s;
		for(int j = 0; (j = s1.lastIndexOf(32)) != -1;) {
			s1 = s1.substring(0, j);
			int l = fontmetrics.stringWidth(s1);
			if(l < i && l > i / 2)
				return s1;
		}

		for(int k = s.length(); fontmetrics.stringWidth(s) > i && --k > 0; s = s.substring(0, k));
		return s;
	}
	public boolean clear(boolean flag) {
		boolean flag1 = m_displayed;
		if(flag) {
			m_text = null;
			m_displayed = false;
		}
		return flag1;
	}
	private final void drawRect(Graphics g, String s, Rectangle rectangle, FontMetrics fontmetrics) {
		g.setColor(m_backCol);
		g.fillRect(rectangle.x, rectangle.y, rectangle.width, rectangle.height);
		g.setColor(Color.black);
		g.drawRect(rectangle.x, rectangle.y, rectangle.width, rectangle.height);
		g.setColor(m_textCol);
		int i = rectangle.y + 2;
		rectangle.x += 2;
		String s1;
		while((s1 = breakString(s, rectangle.width, fontmetrics)) != null)  {
			s = s.substring(s1.length());
			s = s.trim();
			g.drawString(s1, rectangle.x, i + fontmetrics.getAscent());
			i += fontmetrics.getHeight();
		}

	}
	private final Rectangle getRect(String s, int i, FontMetrics fontmetrics) {
		int j = 0;
		int k = 0;
		String s1;
		while((s1 = breakString(s, i, fontmetrics)) != null)  {
			s = s.substring(s1.length());
			s = s.trim();
			int l = fontmetrics.stringWidth(s1);
			if(l > k)
				k = l;
			j += fontmetrics.getHeight();
		}

		return new Rectangle(0, 0, k + 4, j + 4);
	}
	public void run() {
		do
			try {
				if(m_text != null && !m_displayed && System.currentTimeMillis() > m_when && m_text.length() > 0) {
					Graphics g = m_applet.getGraphics();
					FontMetrics fontmetrics = g.getFontMetrics();
					Rectangle rectangle = m_applet.getRect();
					Rectangle rectangle1 = getRect(m_text, rectangle.width - 6, fontmetrics);
					rectangle1.x = m_x - m_applet.m_offsetx;
					rectangle1.y = m_y - m_applet.m_offsety;
					rectangle1.y -= rectangle1.height;
					if(rectangle1.y < 1)
						rectangle1.y = 1;
					if(rectangle1.x < 1)
						rectangle1.x = 1;
					int i = rectangle1.height + rectangle1.y;
					if(i > rectangle.height)
						rectangle1.y -= i - rectangle.height;
					int j = rectangle1.width + rectangle1.x;
					if(j > rectangle.width)
						rectangle1.x -= (j - rectangle.width) + 2;
					drawRect(g, m_text, rectangle1, fontmetrics);
					m_displayed = true;
					g.dispose();
				}
				Thread.sleep(1000L);
			}
			catch(Exception _ex) { }
		while(true);
	}
	public void setText(String s, Event event) {
		m_text = s;
		m_x = event.x;
		m_y = event.y;
		m_when = event.when + 1000L;
		m_displayed = false;
	}
	public void start() {
		if(m_popup != null)
			m_popup.start();
	}
	public void stop() {
		m_text = null;
		m_displayed = false;
		m_popup.stop();
	}
}