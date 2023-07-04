package datapro.eibs.applets.portal;

// Processed by NMI's Java Code Viewer 4.8.0 © 1997-1999 B. Lemaire
// Website: http://njcv.htmlplanet.com	E-mail: info@njcv.htmlplanet.com
// Copy registered to Evaluation Copy
// Source File Name:   C:\JavaApplets\Orbiter\Orbiter.java

import java.applet.Applet;
import java.applet.AppletContext;
import java.awt.*;
import java.net.URL;

public class Orbiter extends Applet
	implements Runnable {

	tdViewpoint m_viewpoint;
	tdFont m_font;
	tdModel m_modelComposite;
	tdObject m_object;
	int m_nWindowWidth;
	int m_nWindowHeight;
	Thread m_threadAnim;
	long m_lStartTime;
	long m_lPrevTime;
	double m_dRadiansPerSecond;
	boolean m_bInitialized;
	MediaTracker m_mediaTracker;
	String m_stringRollover;
	String m_stringURL;
	String m_stringTarget;

	public Orbiter() {
		m_dRadiansPerSecond = 1.0D;
		m_mediaTracker = new MediaTracker(this);
	}
	public void init() {
		start();
	}
	private void initialization() {
		Dimension dimension = size();
		m_nWindowWidth = dimension.width;
		m_nWindowHeight = dimension.height;
		String s = null;
		Color color = Color.yellow;
		s = getParameter("text_color_front");
		if(s != null)
			color = new Color(Integer.parseInt(s, 16));
		Color color1 = Color.red;
		s = getParameter("text_color_back");
		if(s != null)
			if(s.equalsIgnoreCase("transparent"))
				color1 = null;
			else
				color1 = new Color(Integer.parseInt(s, 16));
		m_font = new tdFont(color, color1);
		String s1 = getParameter("string");
		if(s1 == null)
			s1 = new String("No string parameter");
		char ac[] = s1.toCharArray();
		int i = ac.length;
		double d = 0.0D;
		double d1 = 1.5D;
		double d2 = 0.20000000000000001D;
		double d3 = 48D;
		int j = 0;
		for(int k = 0; k < i; k++)
			if(m_font.m_model[ac[k]] == null) {
				d += d1;
			} else {
				d += m_font.m_dWidth[ac[k]] + d2;
				j++;
			}

		if(d > d3 * 0.75D)
			d3 = d / 0.75D;
		double d4 = d3 / 6.2831853071795862D;
		double d5 = 0.0D;
		int l = 0;
		tdObject atdobject[] = new tdObject[j];
		for(int i1 = 0; i1 < i; i1++)
			if(m_font.m_model[ac[i1]] == null) {
				d5 += d1;
			} else {
				d5 += (m_font.m_dWidth[ac[i1]] + d2) / 2D;
				atdobject[l] = new tdObject(m_font.m_model[ac[i1]]);
				double d6 = (d5 / d3) * 2D * 3.1415926535897931D;
				atdobject[l].m_vector.m_dZ = d4 * Math.cos(d6);
				atdobject[l].m_vector.m_dX = d4 * Math.sin(d6);
				atdobject[l].m_matrix.rotateWorld(0.0D, d6, 0.0D);
				d5 += (m_font.m_dWidth[ac[i1]] + d2) / 2D;
				l++;
			}

		m_modelComposite = new tdModel(atdobject, l);
		m_object = new tdObject(m_modelComposite);
		s = getParameter("dip");
		if(s != null) {
			Double double1;
			try {
				double1 = new Double(s);
			}
			catch(NumberFormatException _ex) {
				double1 = new Double(0.0D);
			}
			m_object.m_matrix.rotateWorld((double1.doubleValue() * 3.1415926535897931D) / 180D, 0.0D, 0.0D);
		} else {
			m_object.m_matrix.rotateWorld(0.20000000000000001D, 0.0D, 0.0D);
		}
		s = getParameter("tilt");
		if(s != null) {
			Double double2;
			try {
				double2 = new Double(s);
			}
			catch(NumberFormatException _ex) {
				double2 = new Double(0.0D);
			}
			m_object.m_matrix.rotateWorld(0.0D, 0.0D, (double2.doubleValue() * 3.1415926535897931D) / 180D);
		} else {
			m_object.m_matrix.rotateWorld(0.0D, 0.0D, 0.20000000000000001D);
		}
		m_object.transform();
		s = getParameter("rpm");
		if(s != null) {
			Double double3;
			try {
				double3 = new Double(s);
			}
			catch(NumberFormatException _ex) {
				double3 = new Double(10D);
			}
			m_dRadiansPerSecond = (double3.doubleValue() / 60D) * 2D * 3.1415926535897931D;
		}
		m_viewpoint = new tdViewpoint(0, 0, m_nWindowWidth, m_nWindowHeight, this);
		m_viewpoint.m_vector.m_dZ = 2.4140000000000001D * d4;
		m_viewpoint.m_matrix.rotateWorld(0.0D, 3.1415926535897931D, 0.0D);
		s = getParameter("bgcolor");
		if(s != null)
			m_viewpoint.m_colorBackgrnd = new Color(Integer.parseInt(s, 16));
		s = getParameter("planet");
		if(s != null) {
			m_viewpoint.m_imagePlanet = getImage(getDocumentBase(), s);
			m_mediaTracker.addImage(m_viewpoint.m_imagePlanet, 0);
			while(!m_mediaTracker.checkID(0, true)) 
				try {
					Thread.sleep(100L);
				}
				catch(InterruptedException _ex) { }

			m_viewpoint.m_nPlanetX = (m_nWindowWidth - m_viewpoint.m_imagePlanet.getWidth(null)) / 2;
			m_viewpoint.m_nPlanetY = (m_nWindowHeight - m_viewpoint.m_imagePlanet.getHeight(null)) / 2;
		}
		s = getParameter("background");
		if(s != null) {
			Image image = getImage(getDocumentBase(), s);
			m_mediaTracker.addImage(image, 1);
			while(!m_mediaTracker.checkID(1, true)) 
				try {
					Thread.sleep(100L);
				}
				catch(InterruptedException _ex) { }

			int j1 = image.getWidth(null);
			int k1 = image.getHeight(null);
			m_viewpoint.m_imageBackgrnd = createImage(m_nWindowWidth, m_nWindowHeight);
			Graphics g = m_viewpoint.m_imageBackgrnd.getGraphics();
			for(int l1 = 0; l1 < m_nWindowWidth; l1 += j1) {
				for(int i2 = 0; i2 < m_nWindowHeight; i2 += k1)
					g.drawImage(image, l1, i2, null);

			}

			g.dispose();
		}
		m_stringRollover = getParameter("rollover");
		m_stringURL = getParameter("URL");
		m_stringTarget = getParameter("target");
		m_lStartTime = System.currentTimeMillis();
		m_lPrevTime = m_lStartTime;
		m_bInitialized = true;
	}
	public boolean mouseDown(Event event, int i, int j) {
		if(m_stringURL != null) {
			URL url;
			try {
				url = new URL(m_stringURL);
			}
			catch(Exception _ex) {
				showStatus("Bad URL");
				return true;
			}
			if(m_stringTarget == null)
				getAppletContext().showDocument(url);
			else
				getAppletContext().showDocument(url, m_stringTarget);
		}
		return true;
	}
	public boolean mouseEnter(Event event, int i, int j) {
		if(m_stringRollover != null)
			showStatus(m_stringRollover);
		return true;
	}
	public boolean mouseExit(Event event, int i, int j) {
		if(m_stringRollover != null)
			showStatus(" ");
		return true;
	}
	public void run() {
		if(!m_bInitialized)
			initialization();
		tdObject atdobject[] = new tdObject[1];
		atdobject[0] = m_object;
		do {
			long l = System.currentTimeMillis();
			double d = (double)(l - m_lPrevTime) / 1000D;
			m_lPrevTime = l;
			atdobject[0].m_matrix.rotateSelf(0.0D, m_dRadiansPerSecond * d, 0.0D);
			atdobject[0].transform();
			m_viewpoint.render(atdobject, 1, this);
			System.gc();
			try {
				Thread.sleep(0L);
			}
			catch(InterruptedException _ex) {
				stop();
			}
		}
		while(true);
	}
	public void start() {
		if(m_threadAnim == null) {
			m_threadAnim = new Thread(this);
			m_threadAnim.start();
		}
	}
	public void stop() {
		if(m_threadAnim != null) {
			m_threadAnim.stop();
			m_threadAnim = null;
		}
	}
}