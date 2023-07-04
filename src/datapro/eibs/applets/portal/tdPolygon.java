package datapro.eibs.applets.portal;

// Processed by NMI's Java Code Viewer 4.8.0 © 1997-1999 B. Lemaire
// Website: http://njcv.htmlplanet.com	E-mail: info@njcv.htmlplanet.com
// Copy registered to Evaluation Copy
// Source File Name:   C:\JavaApplets\Orbiter\Orbiter.java

import java.awt.Color;
import java.awt.Graphics;

class tdPolygon {

	public int m_nVertices;
	public int m_nX[];
	public int m_nY[];
	public Color m_color;
	public double m_dDistance;

	public tdPolygon(tdFace tdface) {
		m_nVertices = tdface.m_nVertices;
		m_nX = new int[m_nVertices];
		m_nY = new int[m_nVertices];
	}
	public void draw(Graphics g) {
		g.setColor(m_color);
		g.fillPolygon(m_nX, m_nY, m_nVertices);
	}
}