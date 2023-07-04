package datapro.eibs.applets.tree;

// Processed by NMI's Java Code Viewer 4.8.0 © 1997-1999 B. Lemaire
// Website: http://njcv.htmlplanet.com	E-mail: info@njcv.htmlplanet.com
// Copy registered to Evaluation Copy
// Source File Name:   TreeApp.java

import java.applet.Applet;
import java.awt.*;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.*;

public class TreeApp extends Applet {

	private LightScrollbar m_vbar;
	private LightScrollbar m_hbar;
	boolean has_vbar;
	boolean has_hbar;
	public int m_offsetx;
	public int m_offsety;
	private int m_image_width;
	private int m_image_height;
	private Image m_image;
	private Image m_bgimage;
	private Image m_cachebgimage;
	private Image m_bimage;
	boolean m_tileBackground;
	boolean m_init_size;
	public FolderItem m_folder;
	private Item m_selected;
	private Item m_mouseHit;
	private int m_activation_type;
	private popup m_popup;
	public Color m_detailColour;
	public Color m_highColour;
	public Color m_textColour;
	public Color m_highTextColour;
	public Color m_bgColour;
	public Color m_mouseOverColour;
	public Color m_underColour;
	public Color m_plusColour;
	private Vector m_folderlist;
	private int m_foldernumber;
	public Hashtable m_imageList;
	private boolean m_block;
	private boolean m_autoclose;

	public TreeApp() {
		m_init_size = true;
		m_detailColour = Color.black;
		m_textColour = Color.black;
		m_highTextColour = Color.lightGray;
		m_bgColour = Color.white;
		m_mouseOverColour = Color.blue;
		m_underColour = Color.blue;
		m_plusColour = Color.black;
	}
	public void clearRect(Graphics g, Rectangle rectangle) {
		g.setColor(m_bgColour);
		g.fillRect(rectangle.x, rectangle.y, rectangle.width, rectangle.height);
		if(m_cachebgimage != null) {
			g.clipRect(rectangle.x, rectangle.y, rectangle.width, rectangle.height);
			g.drawImage(m_cachebgimage, 0, 0, this);
		}
	}
	private void closeAll(FolderItem folderitem) {
		if(folderitem == null || folderitem.m_parent == null)
			return;
		for(Enumeration enumeration = folderitem.m_parent.elements(); enumeration.hasMoreElements();) {
			Item item = (Item)enumeration.nextElement();
			if(item instanceof FolderItem) {
				FolderItem folderitem1 = (FolderItem)item;
				if(folderitem1.isExpanded() && folderitem1 != folderitem)
					folderitem1.expand(false);
			}
		}

	}
	public void destroy() {
		m_popup.stop();
		m_popup = null;
	}
	public void draw() {
		Rectangle rectangle = m_folder.layout(2, 2);
		setSize(rectangle.x + rectangle.width, rectangle.y + rectangle.height);
		Graphics g = getCanvas();
		if(m_cachebgimage != null) {
			g.drawImage(m_cachebgimage, 0, 0, m_bgColour, this);
		} else {
			g.setColor(m_bgColour);
			g.fillRect(0, 0, m_image_width, m_image_height);
		}
		m_folder.draw(g, this, 1);
		g.dispose();
		refresh();
	}
	public Graphics getCanvas() {
		return m_image.getGraphics();
	}
	private Color getParamColor(String s) {
		Color color = Color.white;
		s.trim();
		String s1;
		if((s1 = getParameter(s)) != null) {
			if(s1.compareTo("black") == 0)
				color = Color.black;
			else
			if(s1.compareTo("blue") == 0)
				color = Color.blue;
			else
			if(s1.compareTo("cyan") == 0)
				color = Color.cyan;
			else
			if(s1.compareTo("darkgray") == 0)
				color = Color.darkGray;
			else
			if(s1.compareTo("gray") == 0)
				color = Color.gray;
			else
			if(s1.compareTo("green") == 0)
				color = Color.green;
			else
			if(s1.compareTo("lightgray") == 0)
				color = Color.lightGray;
			else
			if(s1.compareTo("magenta") == 0)
				color = Color.magenta;
			else
			if(s1.compareTo("orange") == 0)
				color = Color.orange;
			else
			if(s1.compareTo("pink") == 0)
				color = Color.pink;
			else
			if(s1.compareTo("red") == 0)
				color = Color.red;
			else
			if(s1.compareTo("white") == 0)
				color = Color.white;
			else
			if(s1.compareTo("yellow") == 0)
				color = Color.yellow;
			else
			if(s1.compareTo("rose") == 0)
				color = new Color(153, 0, 66);
			else
				try {
					Integer integer = Integer.valueOf(s1, 16);
					if(integer != null)
						color = new Color(integer.intValue());
				}
				catch(NumberFormatException _ex) {
					return null;
				}
			return color;
		} else {
			return null;
		}
	}
	public Rectangle getRect() {
		Rectangle rectangle = new Rectangle(size());
		rectangle.x = m_offsetx;
		rectangle.y = m_offsety;
		if(has_vbar)
			rectangle.width -= m_vbar.size().width;
		if(has_hbar)
			rectangle.height -= m_hbar.size().height;
		return rectangle;
	}
	public boolean handleEvent(Event event) {
		if(m_block)
			return true;
		if(event.target == m_hbar) {
			switch(event.id) {
			case 601: // Event.SCROLL_LINE_UP
			case 602: // Event.SCROLL_LINE_DOWN
			case 603: // Event.SCROLL_PAGE_UP
			case 604: // Event.SCROLL_PAGE_DOWN
			case 605: // Event.SCROLL_ABSOLUTE
				m_offsetx = ((Integer)event.arg).intValue();
				update(getGraphics());
				break;

			}
			return true;
		}
		if(event.target == m_vbar) {
			switch(event.id) {
			case 601: // Event.SCROLL_LINE_UP
			case 602: // Event.SCROLL_LINE_DOWN
			case 603: // Event.SCROLL_PAGE_UP
			case 604: // Event.SCROLL_PAGE_DOWN
			case 605: // Event.SCROLL_ABSOLUTE
				m_offsety = ((Integer)event.arg).intValue();
				update(getGraphics());
				break;

			}
			return true;
		}
		event.translate(m_offsetx, m_offsety);
		int i = 2;
		switch(event.id) {
		case 503: // Event.MOUSE_MOVE
			i |= 0x8;
			// fall through

		case 501: // Event.MOUSE_DOWN
			if(m_popup.clear(false))
				refresh();
			Item item;
			if((item = m_folder.hitTest(event.x, event.y)) != null) {
				if(item != m_mouseHit) {
					if(item != m_selected) {
						Graphics g = getGraphics();
						g.translate(-m_offsetx, -m_offsety);
						item.draw(g, this, i);
						g.dispose();
					}
					Graphics g1 = getGraphics();
					g1.translate(-m_offsetx, -m_offsety);
					if(m_mouseHit != null && m_mouseHit != m_selected)
						m_mouseHit.draw(g1, this, 4);
					m_mouseHit = item;
					m_mouseHit.activate(3, null, this);
					m_popup.setText(item.m_status, event);
					g1.dispose();
				}
			} else {
				if(m_mouseHit != null) {
					Graphics g2 = getGraphics();
					g2.translate(-m_offsetx, -m_offsety);
					if(m_mouseHit != m_selected)
						m_mouseHit.draw(g2, this, 4);
					g2.dispose();
					m_mouseHit = null;
				}
				m_popup.clear(true);
			}
			return true;

		case 502: // Event.MOUSE_UP
			Item item1;
			if(m_mouseHit != null)
				if((item1 = m_folder.hitTest(event.x, event.y)) != m_mouseHit) {
					Graphics g3 = getGraphics();
					g3.translate(-m_offsetx, -m_offsety);
					m_mouseHit.draw(g3, this, 4);
					g3.dispose();
				} else {
					if(event.clickCount > 1)
						m_activation_type = 1;
					else
						m_activation_type = 2;
					m_selected = item1;
					if(item1.activate(m_activation_type, event, this) && (item1 instanceof FolderItem)) {
						FolderItem folderitem = (FolderItem)m_selected;
						scroll(folderitem, folderitem.isExpanded());
					}
					m_mouseHit = null;
					refresh();
				}
			return true;

		case 505: // Event.MOUSE_EXIT
			if(m_popup.clear(true) || m_mouseHit != null)
				refresh();
			m_mouseHit = null;
			// showStatus("JExplorer - www.jexplorer.com");
			return true;

		case 504: // Event.MOUSE_EVENT
		default:
			return super.handleEvent(event);

		}
	}
	public void init() {
		m_tileBackground = getParameter("tile") != null;
		Item.m_showURL = getParameter("showURL") != null;
		m_autoclose = getParameter("autoclose") != null;
		m_imageList = new Hashtable();
		m_popup = new popup(this);
		String s;
		if((s = getParameter("bgImage")) != null)
			try {
				m_bgimage = getImage(new URL(getCodeBase(), s));
			}
			catch(MalformedURLException _ex) { }
		Color color;
		if((color = getParamColor("bgColor")) != null)
			m_bgColour = color;
		setBackground(m_bgColour);
		if((color = getParamColor("bgHighlight")) != null)
			m_highColour = color;
		if((color = getParamColor("fgColor")) != null)
			m_detailColour = color;
		if((color = getParamColor("textColor")) != null)
			m_textColour = color;
		if((color = getParamColor("textHighlight")) != null)
			m_highTextColour = color;
		if((color = getParamColor("mouseOver")) != null)
			m_mouseOverColour = color;
		if((color = getParamColor("mouseunder")) != null)
			m_underColour = color;
		else
			m_underColour = m_mouseOverColour;
		if((color = getParamColor("tooltext")) != null)
			m_popup.m_textCol = color;
		if((color = getParamColor("toolback")) != null)
			m_popup.m_backCol = color;
		if((color = getParamColor("plusColor")) != null)
			m_plusColour = color;
		else
			m_plusColour = m_detailColour;
		setFont(new Font("Dialog", 0, 10));
		m_vbar = new LightScrollbar(1);
		m_hbar = new LightScrollbar(0);
		setLayout(new BorderLayout(0, 0));
		add("East", m_vbar);
		add("South", m_hbar);
		has_vbar = true;
		has_hbar = true;
		setSize(1, 1);
		m_selected = null;
		m_activation_type = 0;
		m_mouseHit = null;
		m_folderlist = new Vector();
		String s1 = getParameter("title");
		if(s1 == null)
			s1 = "Products Tree View"; //"www.jexplorer.com v1.02";
		int i = 1;
		while((s = getParameter("file" + i)) != null)  {
			FolderItem folderitem = new FolderItem(s1);
			folderitem.m_font = getFont();
			m_folderlist.addElement(folderitem);
			i++;
			try {
				URL url = new URL(getDocumentBase(), s);
				folderitem.m_treejit = new Parser(url, folderitem, getDocumentBase(), getDocumentBase(), this);
			}
			catch(Exception exception) {
				folderitem.setStatus("Error: " + exception);
			}
		}

		if(m_folderlist.isEmpty())
			m_folder = new FolderItem("Error no file items");
		else
			m_folder = (FolderItem)m_folderlist.firstElement();
		m_foldernumber = 0;
		m_folder.expand(true);
		if(m_folder.m_treejit != null)
			m_folder.m_treejit.start();
		m_folder.m_treejit = null;
		initalise();
	}
	public void initalise() {
		m_folder.initalise(this);
		draw();
	}
	public boolean keyDown(Event event, int i) {
		if(m_block)
			return true;
		if(m_popup.clear(true))
			refresh();
		switch(i) {
		case 1004: 
			prev(false);
			break;

		case 1005: 
			next(false);
			break;

		case 10: // '\n'
			if(!m_selected.activate(1, null, this))
				break;
			if(m_selected instanceof FolderItem) {
				FolderItem folderitem = (FolderItem)m_selected;
				scroll(folderitem, folderitem.isExpanded());
				break;
			}
			draw();
			if(makeVisible(m_selected.getRect()))
				refresh();
			break;

		case 1007: 
			if(has_hbar) {
				m_hbar.setValue(m_hbar.value + 5);
				m_offsetx = m_hbar.value;
				refresh();
			}
			break;

		case 1006: 
			m_hbar.setValue(m_hbar.value - 5);
			m_offsetx = m_hbar.value;
			refresh();
			break;

		default:
			return false;

		}
		return true;
	}
	public boolean makeVisible(Rectangle rectangle) {
		Rectangle rectangle1 = getRect();
		int i = rectangle1.y + rectangle1.height;
		int j = rectangle.y + rectangle.height;
		int k = 0;
		if(rectangle1.y < rectangle.y && i < j) {
			if(rectangle1.height < rectangle.height)
				j -= rectangle.height - rectangle1.height;
			k = j - i;
		} else
		if(rectangle1.y > rectangle.y)
			k = rectangle.y - rectangle1.y;
		if(k != 0) {
			m_offsety += k;
			if(m_vbar != null)
				m_vbar.setValue(m_offsety);
			return true;
		}
		if(m_folder.getRect().height < rectangle1.height && m_offsety != 0) {
			m_offsety = 0;
			if(m_vbar != null)
				m_vbar.setValue(0);
			return true;
		} else {
			return false;
		}
	}
	public void next(boolean flag) {
		Item item;
		if((item = m_folder.getNext(m_selected, flag)) != null) {
			if(flag)
				draw();
			setSelected(item);
		}
	}
	public void paint(Graphics g) {
		if(m_image != null) {
			g.drawImage(m_image, -m_offsetx, -m_offsety, m_bgColour, this);
			g.translate(-m_offsetx, -m_offsety);
			if(m_selected != null)
				m_selected.draw(g, this, 2);
		}
	}
	public void prev(boolean flag) {
		Item item;
		if((item = m_folder.getPrev(m_selected, flag)) != null) {
			if(flag)
				draw();
			setSelected(item);
		}
	}
	public void refresh() {
		Graphics g = getGraphics();
		update(g);
		g.dispose();
	}
	public synchronized boolean replaceItem(FolderItem folderitem, FolderItem folderitem1) {
		m_block = true;
		m_popup.clear(true);
		refresh();
		if(m_folder == folderitem)
			m_folder = folderitem1;
		else
		if(!m_folder.replaceItem(folderitem, folderitem1)) {
			m_block = false;
			return false;
		}
		m_selected = null;
		m_mouseHit = null;
		m_activation_type = 0;
		initalise();
		m_block = false;
		return true;
	}
	public synchronized void reshape(int i, int j, int k, int l) {
		super.reshape(i, j, k, l);
		if(m_folder != null) {
			draw();
			refresh();
		}
	}
	private void scroll(FolderItem folderitem, boolean flag) {
		m_popup.clear(true);
		folderitem.expand(true);
		if(flag) {
			if(m_autoclose && flag)
				closeAll(folderitem);
			Rectangle rectangle = m_folder.layout(2, 2);
			setSize(rectangle.x + rectangle.width, rectangle.y + rectangle.height);
			makeVisible(folderitem.getRect());
		}
		Item item = m_selected;
		m_selected = null;
		try {
			if(m_bimage != null) {
				Graphics g = getCanvas();
				g.setColor(m_bgColour);
				g.fillRect(0, 0, m_image_width, m_image_height);
				if(m_cachebgimage != null)
					g.drawImage(m_cachebgimage, 0, 0, m_bgColour, this);
				m_folder.draw(g, this, 1);
				g.dispose();
				Graphics g1 = m_bimage.getGraphics();
				Graphics g2 = getGraphics();
				Rectangle rectangle1 = folderitem.getRect();
				Rectangle rectangle2 = folderitem.getTitleRect();
				int i = rectangle2.y + rectangle2.height;
				int j = rectangle1.height - rectangle2.height;
				g1.drawImage(m_image, 0, 0, m_bgColour, this);
				g1.clipRect(0, i, m_image_width, m_image_height);
				g1.setColor(m_bgColour);
				int k = 15;
				for(int l = j; l > 0;) {
					boolean flag2 = false;
					for(int i1 = 0; ++i1 < 10000;);
					if(l < k)
						l = 0;
					if(m_cachebgimage != null)
						g1.drawImage(m_cachebgimage, 0, 0, m_bgColour, this);
					else
						g1.fillRect(0, i, m_image_width, m_image_height);
					if(flag)
						g1.drawImage(m_image, 0, -l, m_bgColour, this);
					else
						g1.drawImage(m_image, 0, -(j - l), m_bgColour, this);
					g2.drawImage(m_bimage, -m_offsetx, -m_offsety, m_bgColour, this);
					l -= k;
					k += 30;
				}

				boolean flag1 = false;
				if(m_cachebgimage != null)
					g1.drawImage(m_cachebgimage, 0, 0, m_bgColour, this);
				else
					g1.fillRect(0, i, m_image_width, m_image_height);
				if(flag)
					g1.drawImage(m_image, 0, 0, m_bgColour, this);
				else
					g1.drawImage(m_image, 0, -j, m_bgColour, this);
				g2.drawImage(m_bimage, -m_offsetx, -m_offsety, m_bgColour, this);
				g2.dispose();
				g1.dispose();
			}
		}
		catch(Exception _ex) { }
		m_selected = item;
		folderitem.expand(flag);
		if(!flag) {
			draw();
			return;
		} else {
			refresh();
			return;
		}
	}
	public void select(String s) {
		try {
			URL url = new URL(getDocumentBase(), s);
			Item item = m_folder.findItem(url, true);
			if(item != null) {
				draw();
				setSelected(item);
				return;
			}
		}
		catch(Exception _ex) { }
	}
	public void setFile(int i) {
		i--;
		try {
			if(!m_folderlist.isEmpty() && i != m_foldernumber) {
				m_folderlist.setElementAt(m_folder, m_foldernumber);
				if(replaceItem(m_folder, (FolderItem)m_folderlist.elementAt(i))) {
					m_folder.expand(true);
					if(m_folder.m_treejit != null)
						m_folder.m_treejit.start();
					m_folder.m_treejit = null;
					m_foldernumber = i;
					draw();
					return;
				}
			}
		}
		catch(Exception _ex) { }
	}
	public void setSelected(Item item) {
		if(m_selected != item) {
			m_selected = item;
			m_selected.activate(3, null, this);
			makeVisible(item.getRect());
			refresh();
		}
	}
	public void setSize(int i, int j) {
		Dimension dimension = size();
		if(dimension.width <= 0)
			dimension.width = 1;
		if(dimension.height <= 0)
			dimension.height = 1;
		if(i <= 0)
			i = 1;
		if(j <= 0)
			j = 1;
		if(i != m_image_width || j != m_image_height) {
			m_image_width = i;
			m_image_height = j;
			if(i < dimension.width)
				m_image_width = dimension.width;
			if(j < dimension.height)
				m_image_height = dimension.height;
			if(m_image != null)
				m_image.flush();
			if(m_cachebgimage != null)
				m_cachebgimage.flush();
			if(m_bimage != null)
				m_bimage.flush();
			m_image = createImage(m_image_width, m_image_height);
			m_bimage = createImage(m_image_width, m_image_height);
			if(m_bgimage != null)
				m_cachebgimage = createImage(m_image_width, m_image_height);
		}
		if(m_cachebgimage != null) {
			Graphics g = m_cachebgimage.getGraphics();
			if(g == null)
				return;
			g.setColor(m_bgColour);
			g.fillRect(0, 0, m_image_width, m_image_height);
			if(m_bgimage != null) {
				if(m_tileBackground) {
					int k = m_bgimage.getWidth(this);
					int l = m_bgimage.getHeight(this);
					if(k > 0 && l > 0) {
						int i1 = 0;
						int j1 = 0;
						for(; i1 < m_image_width; i1 += k) {
							g.drawImage(m_bgimage, i1, j1, m_bgColour, this);
							while(j1 + l < m_image_height)  {
								j1 += l;
								g.drawImage(m_bgimage, i1, j1, m_bgColour, this);
							}

							j1 = 0;
						}

					}
				} else {
					g.drawImage(m_bgimage, 0, 0, m_bgColour, this);
				}
				g.dispose();
			}
		}
		if(getRect().height > j - m_offsety)
			m_offsety = j - getRect().height;
		if(getRect().width > i - m_offsetx)
			m_offsetx = i - getRect().width;
		boolean flag = false;
		if(m_hbar == null || m_vbar == null)
			return;
		boolean flag2 = false;
		boolean flag3 = false;
		boolean flag1;
		do {
			flag1 = false;
			int k1 = dimension.width;
			int l1 = dimension.height;
			if(flag3)
				l1 -= 11;
			if(flag2)
				k1 -= 11;
			if(flag2 != (j > l1)) {
				flag2 = j > l1;
				flag1 = true;
			}
			if(flag3 != (i > k1)) {
				flag1 = true;
				flag3 = i > k1;
			}
		}
		while(flag1);
		if(!has_vbar)
			m_offsety = 0;
		if(has_vbar != flag2) {
			m_vbar.setVisible(flag2);
			flag = true;
			has_vbar = flag2;
			if(!flag2)
				m_offsety = 0;
		}
		if(flag2) {
			if(flag3)
				dimension.height -= 11;
			m_vbar.setValues(m_offsety, dimension.height, 0, m_image_height - dimension.height);
			m_vbar.setLineIncrement(15);
		} else {
			m_offsety = 0;
		}
		if(!has_hbar)
			m_offsetx = 0;
		if(has_hbar != flag3) {
			flag = true;
			m_hbar.setVisible(flag3);
			has_hbar = flag3;
		}
		if(flag3) {
			if(flag2)
				dimension.width -= 11;
			m_hbar.setValues(m_offsetx, dimension.width, 0, m_image_width - dimension.width);
			m_hbar.setLineIncrement(15);
			m_hbar.setLeft(has_vbar ? 12 : 0);
		} else {
			m_offsetx = 0;
		}
		if(flag)
			layout();
	}
	public void start() {
		initalise();
		m_popup.start();
	}
	public void stop() {
		m_popup.stop();
		m_selected = null;
		m_mouseHit = null;
		m_activation_type = 0;
	}
	public void update(Graphics g) {
		if(m_init_size) {
			m_init_size = false;
			draw();
			return;
		} else {
			paint(g);
			return;
		}
	}
}