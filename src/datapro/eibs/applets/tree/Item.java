package datapro.eibs.applets.tree;

// Processed by NMI's Java Code Viewer 4.8.0 © 1997-1999 B. Lemaire
// Website: http://njcv.htmlplanet.com	E-mail: info@njcv.htmlplanet.com
// Copy registered to Evaluation Copy
// Source File Name:   Item.java

import java.applet.Applet;
import java.applet.AppletContext;
import java.awt.*;
import java.net.URL;

public class Item {

	static final int ACT_DBCLICK = 1;
	static final int ACT_CLICK = 2;
	static final int ACT_HIGHLIGHT = 3;
	static final int DRAW_FULL = 1;
	static final int DRAW_HIGHLIGHT = 2;
	static final int DRAW_CLEAR = 4;
	static final int DRAW_MOUSEOVER = 8;
	String m_title;
	public Image m_icon;
	public Image m_highlightIcon;
	public Rectangle m_rect;
	public String m_status;
	public URL m_doc;
	public URL m_rightdoc;
	public String m_docTarget;
	public String m_rightdocTarget;
	public Font m_font;
	public static boolean m_showURL = true;

	public Item(Item item) {
		copy(item);
	}
	public Item(String s) {
		m_title = s;
		m_rect = new Rectangle();
	}
	public boolean activate(int i, Event event, Applet applet) {
		if(m_status != null && applet != null)
			applet.showStatus(m_status);
		if(i == 2 || i == 1) {
			URL url = m_doc;
			String s = m_docTarget;
			boolean flag = event != null && event.metaDown();
			if(flag && m_rightdoc != null)
				url = m_rightdoc;
			if(flag && m_rightdocTarget != null)
				s = m_rightdocTarget;
			if(url != null && applet != null) {
				AppletContext appletcontext = applet.getAppletContext();
				if(s != null)
					appletcontext.showDocument(url, s);
				else
					appletcontext.showDocument(url);
			}
		}
		return false;
	}
	public void copy(Item item) {
		m_title = new String(item.getTitle());
		m_icon = item.m_icon;
		m_status = item.m_status;
		m_doc = item.m_doc;
		m_rightdoc = item.m_rightdoc;
		m_docTarget = item.m_docTarget;
		m_rightdocTarget = item.m_rightdocTarget;
		Rectangle rectangle = item.getRect();
		m_rect = new Rectangle();
		m_rect.x = rectangle.x;
		m_rect.x = rectangle.y;
		m_rect.width = rectangle.width;
		m_rect.height = rectangle.height;
		m_highlightIcon = item.m_highlightIcon;
		m_font = item.m_font;
	}
	public void draw(Graphics g, TreeApp treeapp, int i) {
		boolean flag = (i & 0x2) > 0;
		boolean flag1 = (i & 0x8) > 0;
		int j = m_rect.x;
		int k = m_rect.y;
		if(m_font != null && g.getFont() != m_font)
			g.setFont(m_font);
		if(flag || (i & 0x4) > 0)
			treeapp.clearRect(g, m_rect);
		if(m_icon != null) {
			Image image;
			if(m_highlightIcon != null && flag)
				image = m_highlightIcon;
			else
				image = m_icon;
			int l = image.getWidth(treeapp);
			int i1 = image.getHeight(treeapp);
			if(l > 0 && i1 > 0) {
				k += (m_rect.height - i1) / 2;
				g.drawImage(image, j, k, treeapp);
				j += l;
			}
		}
		java.awt.Color color = treeapp.m_textColour;
		if(flag && m_title.length() != 0) {
			if(treeapp.m_highColour != null && !flag1) {
				g.setColor(treeapp.m_highColour);
				g.fillRect(j, m_rect.y, m_rect.width - (j - m_rect.x), m_rect.height);
			}
			color = flag1 ? treeapp.m_mouseOverColour : treeapp.m_highTextColour;
		}
		g.setColor(color);
		k = m_rect.y;
		j += 2;
		FontMetrics fontmetrics = g.getFontMetrics();
		k += (m_rect.height - fontmetrics.getHeight()) / 2;
		k += fontmetrics.getAscent();
		g.drawString(m_title, j, k);
		if(flag1) {
			if((k += 2) >= m_rect.y + m_rect.height)
				k = (m_rect.y + m_rect.height) - 1;
			g.setColor(treeapp.m_underColour);
			g.drawLine(j, k, j + fontmetrics.stringWidth(m_title), k);
		}
	}
	public Rectangle getRect() {
		return m_rect;
	}
	public String getTitle() {
		return m_title;
	}
	public Item hitTest(int i, int j) {
		if(m_rect.inside(i, j))
			return this;
		else
			return null;
	}
	public void initalise(Applet applet) {
		int i = 4;
		int j = 10;
		if(m_icon != null) {
			int k = m_icon.getWidth(applet);
			int l = m_icon.getHeight(applet);
			if(m_highlightIcon != null) {
				if(k < m_highlightIcon.getWidth(applet))
					k = m_highlightIcon.getWidth(applet);
				if(l < m_highlightIcon.getHeight(applet))
					l = m_highlightIcon.getHeight(applet);
			}
			i += k;
			j = l;
		}
		FontMetrics fontmetrics;
		if(m_font != null)
			fontmetrics = applet.getFontMetrics(m_font);
		else
			fontmetrics = applet.getFontMetrics(applet.getFont());
		i += fontmetrics.stringWidth(m_title);
		if(j < fontmetrics.getHeight())
			j = fontmetrics.getHeight();
		if(i > m_rect.width)
			m_rect.width = i;
		if(j > m_rect.height)
			m_rect.height = j;
	}
	public Rectangle layout(int i, int j) {
		m_rect.x = i;
		m_rect.y = j;
		return m_rect;
	}
	public final void setStatus(String s) {
		if(s != null)
			m_status = new String(s);
		if(m_doc != null && m_showURL) {
			if(m_status == null)
				m_status = new String();
			else
				m_status = m_status.concat(" ");
			m_status = m_status.concat("(" + m_doc.getFile() + ")");
		}
	}
	public void setTitle(String s) {
		m_title = s;
	}
}