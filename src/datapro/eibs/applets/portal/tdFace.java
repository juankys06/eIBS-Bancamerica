package datapro.eibs.applets.portal;

// Processed by NMI's Java Code Viewer 4.8.0 © 1997-1999 B. Lemaire
// Website: http://njcv.htmlplanet.com	E-mail: info@njcv.htmlplanet.com
// Copy registered to Evaluation Copy
// Source File Name:   C:\JavaApplets\Orbiter\Orbiter.java

import java.awt.Color;

class tdFace {

	public int m_nVertices;
	public int m_ndxVertex[];
	public Color m_colorFront;
	public Color m_colorBack;

	public tdFace() {
	}
	public tdFace(int i, int ai[], Color color, Color color1) {
		m_nVertices = i;
		m_ndxVertex = ai;
		m_colorFront = color;
		m_colorBack = color1;
	}
	public void specifyVertex(int i, int j) {
		m_ndxVertex[i] = j;
	}
}