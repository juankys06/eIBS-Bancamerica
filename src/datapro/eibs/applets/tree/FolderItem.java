package datapro.eibs.applets.tree;

// Processed by NMI's Java Code Viewer 4.8.0 © 1997-1999 B. Lemaire
// Website: http://njcv.htmlplanet.com	E-mail: info@njcv.htmlplanet.com
// Copy registered to Evaluation Copy
// Source File Name:   FolderItem.java

import java.applet.Applet;
import java.awt.*;
import java.net.URL;
import java.util.Enumeration;
import java.util.Vector;

public class FolderItem extends Item {

	int TABSTOP;
	int INDENT;
	public int m_spacing;
	Parser m_treejit;
	Rectangle m_rect;
	boolean m_expanded;
	public Vector m_list;
	public FolderItem m_parent;

	public FolderItem(FolderItem folderitem) {
		super(folderitem.getTitle());
		TABSTOP = 20;
		INDENT = 4;
		copy(folderitem);
	}
	public FolderItem(Item item) {
		super(item);
		TABSTOP = 20;
		INDENT = 4;
		m_expanded = false;
		m_rect = new Rectangle();
		m_list = new Vector();
	}
	public FolderItem(String s) {
		super(s);
		TABSTOP = 20;
		INDENT = 4;
		m_expanded = false;
		m_rect = new Rectangle();
		m_list = new Vector();
	}
	public boolean activate(int i, Event event, Applet applet) {
		if(i == 1) {
			if(m_treejit != null) {
				m_treejit.start();
				m_treejit = null;
			}
			expand(!m_expanded);
			return true;
		}
		if(event != null) {
			if(super.getRect().inside(event.x, event.y)) {
				super.activate(i, event, applet);
			} else {
				if(m_treejit != null) {
					m_treejit.start();
					m_treejit = null;
				}
				expand(!m_expanded);
				return true;
			}
		} else {
			super.activate(i, event, applet);
		}
		return false;
	}
	public final void addElement(Object obj) {
		m_list.addElement(obj);
		if(obj instanceof FolderItem)
			((FolderItem)obj).m_parent = this;
	}
	public void copy(FolderItem folderitem) {
		super.copy((Item)folderitem);
		m_expanded = folderitem.isExpanded();
		m_rect = new Rectangle();
		m_list = new Vector();
		Enumeration enumeration = folderitem.elements();
		m_list.removeAllElements();
		for(; enumeration.hasMoreElements(); m_list.addElement(enumeration.nextElement()));
		m_list.trimToSize();
	}
	public void draw(Graphics g, TreeApp treeapp, int i) {
		super.draw(g, treeapp, i);
		if(!m_expanded || (i & 0x1) == 0)
			return;
		int j = INDENT + m_rect.x;
		int k = super.getRect().height + m_rect.y;
		for(Enumeration enumeration = elements(); enumeration.hasMoreElements();) {
			Item item = (Item)enumeration.nextElement();
			item.draw(g, treeapp, i);
			g.setColor(treeapp.m_detailColour);
			if(item instanceof FolderItem) {
				FolderItem folderitem = (FolderItem)item;
				int i1 = (folderitem.getRect().y + folderitem.getTitleRect().height / 2) - 4;
				int j1 = j - 4;
				g.drawRect(j1, i1, 8, 8);
				g.setColor(treeapp.m_plusColour);
				g.drawLine(j1 + 2, i1 + 4, j1 + 6, i1 + 4);
				if(!folderitem.isExpanded())
					g.drawLine(j1 + 4, i1 + 2, j1 + 4, i1 + 6);
				g.setColor(treeapp.m_detailColour);
				drawLine(g, j1 + 8, i1 + 4, j + (TABSTOP - INDENT), i1 + 4);
				drawLine(g, j, k, j, i1);
				k = i1 + 8;
			} else {
				int l = item.getRect().y + item.getRect().height / 2;
				drawLine(g, j, k, j, l);
				drawLine(g, j, l, j + (TABSTOP - INDENT), l);
				k = l;
			}
		}

	}
	private final void drawLine(Graphics g, int i, int j, int k, int l) {
		while(i < k || j < l)  {
			g.drawLine(i, j, i, j);
			if(i != k)
				i += 2;
			if(j != l)
				j += 2;
		}

	}
	public final Enumeration elements() {
		return m_list.elements();
	}
	public final void expand(boolean flag) {
		m_expanded = flag;
	}
	public Item findItem(URL url, boolean flag) {
		if(url == null)
			return null;
		for(Enumeration enumeration = elements(); enumeration.hasMoreElements();) {
			Item item = (Item)enumeration.nextElement();
			if(url.sameFile(item.m_doc)) {
				if(flag)
					expand(true);
				return item;
			}
			if((item instanceof FolderItem) && (item = ((FolderItem)item).findItem(url, flag)) != null) {
				if(flag)
					expand(true);
				return item;
			}
		}

		return null;
	}
	public Item getFirstItem(boolean flag) {
		if(!isExpanded() && !flag || isEmpty())
			return this;
		if(flag)
			expand(flag);
		return (Item)m_list.firstElement();
	}
	public Item getLastItem(boolean flag) {
		if(!isExpanded() && !flag || isEmpty())
			return this;
		if(flag)
			expand(flag);
		Item item = (Item)m_list.lastElement();
		if(item instanceof FolderItem)
			return ((FolderItem)item).getLastItem(flag);
		else
			return item;
	}
	protected Item getNext(int i, boolean flag) {
		if(flag)
			expand(flag);
		Item item;
		try {
			item = (Item)m_list.elementAt(i);
		}
		catch(ArrayIndexOutOfBoundsException _ex) {
			item = null;
		}
		if(item instanceof FolderItem) {
			FolderItem folderitem = (FolderItem)item;
			Item item2 = folderitem.getNext(item, flag);
			if(item2 != item)
				return item2;
		}
		if(m_list.size() > i + 1) {
			Item item1;
			try {
				item1 = (Item)m_list.elementAt(i + 1);
			}
			catch(ArrayIndexOutOfBoundsException _ex) {
				item1 = null;
			}
			return item1;
		} else {
			return null;
		}
	}
	public Item getNext(Item item, boolean flag) {
		if(item == this)
			return getFirstItem(flag);
		if(!isExpanded() && !flag || isEmpty())
			return null;
		int i = 0;
		if((i = m_list.indexOf(item)) != -1) {
			Item item1 = getNext(i, flag);
			if(item1 == null)
				item1 = item;
			return item1;
		}
		for(Enumeration enumeration = elements(); enumeration.hasMoreElements();) {
			Item item2 = (Item)enumeration.nextElement();
			if(item2 instanceof FolderItem) {
				FolderItem folderitem = (FolderItem)item2;
				Item item3 = folderitem.getNext(item, flag);
				if(item3 != null && flag)
					expand(flag);
				if(item3 == item)
					if(enumeration.hasMoreElements())
						return (Item)enumeration.nextElement();
					else
						return item;
				if(item3 != null)
					return item3;
			}
		}

		return null;
	}
	protected Item getPrev(int i, boolean flag) {
		if(i <= 0)
			return this;
		if(i - 1 >= 0) {
			Item item;
			try {
				item = (Item)m_list.elementAt(i - 1);
			}
			catch(ArrayIndexOutOfBoundsException _ex) {
				item = null;
			}
			if(item != null && (item instanceof FolderItem))
				item = ((FolderItem)item).getLastItem(flag);
			return item;
		} else {
			return null;
		}
	}
	public Item getPrev(Item item, boolean flag) {
		if(item == this)
			return item;
		if(!isExpanded() && !flag || isEmpty())
			return null;
		int i = m_list.indexOf(item);
		if(i != -1)
			return getPrev(i, flag);
		for(Enumeration enumeration = elements(); enumeration.hasMoreElements();) {
			Item item1 = (Item)enumeration.nextElement();
			if(item1 instanceof FolderItem) {
				FolderItem folderitem = (FolderItem)item1;
				Item item2 = folderitem.getPrev(item, flag);
				if(item2 == item)
					return getPrev(m_list.indexOf(folderitem), flag);
				if(item2 != null) {
					if(flag)
						expand(flag);
					return item2;
				}
			}
		}

		return null;
	}
	public Rectangle getRect() {
		return m_rect;
	}
	public Rectangle getTitleRect() {
		return super.getRect();
	}
	public Item hitTest(int i, int j) {
		if(!m_rect.inside(i, j))
			return null;
		if(super.hitTest(i, j) != null)
			return this;
		Rectangle rectangle = new Rectangle(10, 10);
		rectangle.x = (m_rect.x + INDENT) - 4;
		for(Enumeration enumeration = elements(); enumeration.hasMoreElements();) {
			Item item = (Item)enumeration.nextElement();
			Item item1;
			if((item1 = item.hitTest(i, j)) != null)
				return item1;
			if(item instanceof FolderItem) {
				FolderItem folderitem = (FolderItem)item;
				folderitem.getTitleRect();
				rectangle.y = (folderitem.getRect().y + folderitem.getTitleRect().height / 2) - 4;
				if(rectangle.inside(i, j))
					return item;
			}
		}

		return null;
	}
	public void initalise(Applet applet) {
		super.initalise(applet);
		if(m_icon != null) {
			INDENT = m_icon.getWidth(applet) / 2;
			if(INDENT < 1)
				INDENT = 4;
			if(INDENT > 15)
				INDENT = 15;
			TABSTOP = INDENT + 10;
		}
		java.awt.FontMetrics fontmetrics;
		if(m_font != null)
			fontmetrics = applet.getFontMetrics(m_font);
		else
			fontmetrics = applet.getFontMetrics(applet.getFont());
		Item item;
		for(Enumeration enumeration = elements(); enumeration.hasMoreElements(); item.initalise(applet))
			item = (Item)enumeration.nextElement();

	}
	public final boolean isEmpty() {
		return m_list.isEmpty();
	}
	public final boolean isExpanded() {
		return m_expanded;
	}
	public Rectangle layout(int i, int j) {
		Rectangle rectangle = super.layout(i, j);
		m_rect.x = i;
		m_rect.y = j;
		int k = rectangle.width;
		int l = rectangle.height;
		if(m_expanded && m_list != null) {
			i += TABSTOP;
			for(Enumeration enumeration = elements(); enumeration.hasMoreElements();) {
				Item item = (Item)enumeration.nextElement();
				Rectangle rectangle1 = item.layout(i, j + l);
				if(rectangle1.width > k)
					k = rectangle1.width;
				l += rectangle1.height;
			}

			k += TABSTOP;
		}
		m_rect.width = k;
		m_rect.height = l + m_spacing;
		return m_rect;
	}
	public boolean replaceItem(Item item, Item item1) {
		int i;
		if((i = m_list.indexOf(item)) != -1) {
			m_list.setElementAt(item1, i);
			if(item1 instanceof FolderItem)
				((FolderItem)item1).m_parent = this;
			return true;
		}
		for(Enumeration enumeration = elements(); enumeration.hasMoreElements();) {
			Item item2 = (Item)enumeration.nextElement();
			if((item2 instanceof FolderItem) && ((FolderItem)item2).replaceItem(item, item1))
				return true;
		}

		return false;
	}
}