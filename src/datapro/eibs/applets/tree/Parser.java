package datapro.eibs.applets.tree;

// Processed by NMI's Java Code Viewer 4.8.0 © 1997-1999 B. Lemaire
// Website: http://njcv.htmlplanet.com	E-mail: info@njcv.htmlplanet.com
// Copy registered to Evaluation Copy
// Source File Name:   Parser.java

import java.applet.Applet;
import java.awt.*;
import java.io.IOException;
import java.io.StreamTokenizer;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.*;

public class Parser
	implements Runnable {

	private static final String XT_treeJIT = "treecontrol";
	private static final String XT_folder = "folder";
	private static final String XT_jitfolder = "jitfolder";
	private static final String XT_item = "item";
	private static final String XT_baseitem = "idefault";
	private static final String XT_font = "font";
	private static final String XT_rootfolder = "rootfolder";
	private static final String XT_TreeJITClose = "/treecontrol";
	private static final String XT_folderClose = "/folder";
	private static final String XT_fontClose = "/font";
	private static final String XT_baseitemClose = "/idefault";
	private static final String XI_title = "title";
	private static final String XI_image = "image";
	private static final String XI_highImage = "imagetwo";
	private static final String XI_link = "link";
	private static final String XI_rlink = "rlink";
	private static final String XI_frame = "target";
	private static final String XI_rframe = "rtarget";
	private static final String XI_info = "info";
	private static final String XI_width = "width";
	private static final String XI_height = "height";
	private static final String XF_jit = "jit";
	private static final String XF_expand = "expand";
	private static final String XFont_face = "face";
	private static final String XFont_size = "size";
	private static final String XFont_style = "style";
	private static final String XJ_linkbaseURL = "linkurl";
	private static final String XJ_imagebaseURL = "imageurl";
	private static final char XML_opentag = 60;
	private static final char XML_closetag = 62;
	private static final char XML_equal = 61;
	private URL m_baseImageURL;
	private URL m_baseLinkURL;
	private MediaTracker m_tracker;
	private Hashtable m_imageList;
	private TreeApp m_applet;
	public static final boolean m_as = false;
	URL m_jitFile;
	FolderItem m_parent;
	Thread m_thread;

	Parser(URL url, FolderItem folderitem, URL url1, URL url2, TreeApp treeapp) {
		m_imageList = treeapp.m_imageList;
		m_parent = folderitem;
		m_jitFile = url;
		m_baseImageURL = url1;
		m_baseLinkURL = url2;
		m_applet = treeapp;
	}
	private final Image addImage(String s) {
		if(s.length() == 0)
			return null;
		if(m_imageList.containsKey(s))
			return (Image)m_imageList.get(s);
		try {
			URL url = new URL(m_baseImageURL, s);
			Image image = m_applet.getImage(url);
			m_imageList.put(s, image);
			m_tracker.addImage(image, 0);
			m_tracker.checkAll(true);
			return image;
		}
		catch(Exception _ex) {
			return null;
		}
	}
	private void processFolderTag(FolderItem folderitem, Hashtable hashtable) {
		folderitem.m_expanded = hashtable.containsKey("expand");
		processItemTag(folderitem, hashtable);
	}
	private Font processFontTag(Font font, Hashtable hashtable) {
		String s = null;
		int i = font.getSize();
		int j = font.getStyle();
		if(hashtable.containsKey("face"))
			s = (String)hashtable.get("face");
		if(hashtable.containsKey("size") && (hashtable.get("size") instanceof Double))
			i = ((Double)hashtable.get("size")).intValue();
		if(hashtable.containsKey("style")) {
			String s1 = (String)hashtable.get("style");
			s1 = s1.toLowerCase();
			if(s1.compareTo("plain") == 0)
				j = 0;
			if(s1.compareTo("italic") == 0)
				j = 2;
			if(s1.compareTo("bold") == 0)
				j = 1;
			if(s1.compareTo("bolditalic") == 0)
				j = 3;
		}
		if(s == null)
			s = font.getName();
		return new Font(s, j, i);
	}
	private void processItemTag(Item item, Hashtable hashtable) {
		if(hashtable.containsKey("title"))
			item.m_title = (String)hashtable.get("title");
		if(hashtable.containsKey("image"))
			item.m_icon = addImage((String)hashtable.get("image"));
		if(hashtable.containsKey("imagetwo"))
			item.m_highlightIcon = addImage((String)hashtable.get("imagetwo"));
		if(hashtable.containsKey("target"))
			item.m_docTarget = (String)hashtable.get("target");
		if(hashtable.containsKey("rtarget"))
			item.m_rightdocTarget = (String)hashtable.get("rtarget");
		if(hashtable.containsKey("width") && (hashtable.get("width") instanceof Double)) {
			int i = ((Double)hashtable.get("width")).intValue();
			if(item instanceof FolderItem)
				((FolderItem)item).getTitleRect().width = i;
			else
				item.getRect().width = i;
		}
		if(hashtable.containsKey("height") && (hashtable.get("height") instanceof Double)) {
			int j = ((Double)hashtable.get("height")).intValue();
			if(item instanceof FolderItem)
				((FolderItem)item).getTitleRect().height = j;
			else
				item.getRect().height = j;
		}
		try {
			if(hashtable.containsKey("link"))
				item.m_doc = new URL(m_baseLinkURL, (String)hashtable.get("link"));
			if(hashtable.containsKey("rlink"))
				item.m_rightdoc = new URL(m_baseLinkURL, (String)hashtable.get("rlink"));
			if(hashtable.containsKey("info"))
				item.setStatus((String)hashtable.get("info"));
		}
		catch(MalformedURLException malformedurlexception) {
			item.setStatus((String)hashtable.get("URL error: " + malformedurlexception.getMessage()));
		}
		if(item.m_status == null)
			item.setStatus("");
	}
	private void processJitFolderTag(FolderItem folderitem, Hashtable hashtable) {
		if(hashtable.containsKey("jit"))
			try {
				URL url = new URL(m_baseLinkURL, (String)hashtable.get("jit"));
				folderitem.m_treejit = new Parser(url, folderitem, m_applet.getDocumentBase(), m_applet.getDocumentBase(), m_applet);
			}
			catch(Exception exception) {
				folderitem.setStatus((String)hashtable.get("Jit error: " + exception.getMessage()));
			}
		processItemTag(folderitem, hashtable);
	}
	void ProcessTags(StreamTokenizer streamtokenizer, FolderItem folderitem) {
		streamtokenizer.ordinaryChar(60);
		streamtokenizer.ordinaryChar(62);
		streamtokenizer.ordinaryChar(61);
		streamtokenizer.quoteChar(34);
		streamtokenizer.wordChars(47, 47);
		streamtokenizer.wordChars(92, 92);
		Hashtable hashtable = new Hashtable();
		Stack stack = new Stack();
		Font font = folderitem.m_font;
		Item item = new Item("default");
		Stack stack1 = new Stack();
		if(font == null)
			font = new Font("Dialog", 0, 10);
		Stack stack2 = new Stack();
		boolean flag = false;
		try {
			while(readTag(streamtokenizer, hashtable)) 
				if(hashtable.containsKey("treecontrol")) {
					processTreeJITTag(hashtable);
					flag = true;
				} else
				if(flag)
					if(hashtable.containsKey("item")) {
						Item item1 = new Item(item);
						item1.m_font = font;
						if(item1 != null) {
							processItemTag(item1, hashtable);
							folderitem.addElement(item1);
						}
					} else
					if(hashtable.containsKey("folder")) {
						FolderItem folderitem1 = new FolderItem(item);
						folderitem1.m_font = font;
						if(folderitem1 != null) {
							processFolderTag(folderitem1, hashtable);
							folderitem.addElement(folderitem1);
							stack.push(folderitem);
							folderitem = folderitem1;
						}
					} else
					if(hashtable.containsKey("jitfolder")) {
						FolderItem folderitem2 = new FolderItem(item);
						folderitem2.m_font = font;
						if(folderitem2 != null) {
							processJitFolderTag(folderitem2, hashtable);
							folderitem.addElement(folderitem2);
						}
					} else
					if(hashtable.containsKey("font")) {
						Font font1 = processFontTag(font, hashtable);
						if(font1 != null) {
							stack2.push(font);
							font = font1;
						}
					} else
					if(hashtable.containsKey("rootfolder")) {
						FolderItem folderitem3;
						if(!stack.isEmpty())
							folderitem3 = (FolderItem)stack.firstElement();
						else
							folderitem3 = folderitem;
						folderitem3.m_font = font;
						processItemTag(folderitem3, hashtable);
					} else
					if(hashtable.containsKey("idefault")) {
						Item item2 = new Item(item);
						stack1.push(item);
						item = item2;
						processItemTag(item, hashtable);
					} else
					if(hashtable.containsKey("/treecontrol"))
						flag = false;
					else
					if(hashtable.containsKey("/folder")) {
						if(!stack.empty())
							folderitem = (FolderItem)stack.pop();
					} else
					if(hashtable.containsKey("/font")) {
						if(!stack2.empty())
							font = (Font)stack2.pop();
					} else
					if(hashtable.containsKey("/idefault") && !stack1.empty())
						item = (Item)stack1.pop();

			return;
		}
		catch(IOException _ex) {
			return;
		}
	}
	private void processTreeJITTag(Hashtable hashtable) {
		URL url = m_baseLinkURL;
		URL url1 = m_baseImageURL;
		try {
			if(hashtable.containsKey("imageurl"))
				m_baseImageURL = new URL(m_baseImageURL, (String)hashtable.get("imageurl"));
			if(hashtable.containsKey("linkurl")) {
				m_baseLinkURL = new URL(m_baseLinkURL, (String)hashtable.get("linkurl"));
				return;
			}
		}
		catch(Exception _ex) {
			m_baseLinkURL = url;
			m_baseImageURL = url1;
		}
	}
	boolean readTag(StreamTokenizer streamtokenizer, Hashtable hashtable) throws IOException {
		hashtable.clear();
		int i;
		while((i = streamtokenizer.nextToken()) != 60) 
			if(i == -1)
				return false;

		Object obj = null;
		boolean flag = false;
		while((i = streamtokenizer.nextToken()) != 62)  {
			if(i == -1)
				return false;
			int j;
			if(i == 60)
				while((j = streamtokenizer.nextToken()) != 62) 
					if(j == -1)
						break;

			if(i == 61) {
				flag = true;
			} else {
				Object obj1 = null;
				if(i == -3 || i == 34)
					obj1 = new String(streamtokenizer.sval);
				if(i == -2)
					obj1 = new Double(streamtokenizer.nval);
				if(obj1 != null)
					if(flag && obj != null) {
						hashtable.put(obj, obj1);
						flag = false;
						obj = null;
					} else {
						if(obj != null)
							hashtable.put(obj, "");
						if(obj1 instanceof String)
							obj1 = ((String)obj1).toLowerCase();
						obj = obj1;
					}
			}
		}

		if(obj != null)
			hashtable.put(obj, "");
		return true;
	}
	public void run() {
		FolderItem folderitem = new FolderItem(m_parent);
		folderitem.getTitleRect().width = 0;
		folderitem.getTitleRect().height = 0;
		m_tracker = new MediaTracker(m_applet);
		try {
			for(Enumeration enumeration = m_imageList.elements(); enumeration.hasMoreElements(); m_tracker.addImage((Image)enumeration.nextElement(), 0));
		}
		catch(Exception _ex) { }
		Item item = new Item("Loading items..");
		item.m_font = m_parent.m_font;
		m_parent.addElement(item);
		m_applet.initalise();
		try {
			java.io.InputStream inputstream = m_jitFile.openStream();
			ProcessTags(new StreamTokenizer(inputstream), folderitem);
		}
		catch(Exception exception) {
			item.setTitle("Error loading file");
			item.setStatus("Error: " + exception);
			m_applet.initalise();
			return;
		}
		item.setTitle("Loading images...");
		m_applet.initalise();
		try {
			m_tracker.waitForAll();
		}
		catch(Exception exception1) {
			item.setTitle("Error loading images...");
			item.setStatus("Error: " + exception1);
			m_applet.initalise();
		}
		folderitem.initalise(m_applet);
		if(!m_applet.replaceItem(m_parent, folderitem))
			m_parent.copy(folderitem);
	}
	public void start() {
		m_thread = new Thread(this);
		m_thread.start();
	}
}