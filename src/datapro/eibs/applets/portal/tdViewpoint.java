package datapro.eibs.applets.portal;

// Processed by NMI's Java Code Viewer 4.8.0 © 1997-1999 B. Lemaire
// Website: http://njcv.htmlplanet.com	E-mail: info@njcv.htmlplanet.com
// Copy registered to Evaluation Copy
// Source File Name:   C:\JavaApplets\Orbiter\Orbiter.java

import java.awt.*;

class tdViewpoint {

	public tdMatrix m_matrix;
	public tdVector m_vector;
	public double m_dAmbient;
	public tdMatrix m_matrixLight;
	double m_dFratio;
	int m_nViewplaneX;
	int m_nViewplaneY;
	int m_nViewplaneWidth;
	int m_nViewplaneHeight;
	public Image m_imageViewplane;
	public Image m_imageBackgrnd;
	public Color m_colorBackgrnd;
	public Image m_imagePlanet;
	public int m_nPlanetX;
	public int m_nPlanetY;
	double m_dViewplaneMult;
	double m_dXoffset;
	double m_dYoffset;
	final double Z_CLIP = 0.10000000000000001D;
	final int MAX_POLYGONS = 300;
	int m_nPolygons;
	tdPolygon m_polygon[];
	double m_dDistanceMax;
	double m_dDistanceMin;

	public tdViewpoint(int i, int j, int k, int l, Component component) {
		m_matrix = new tdMatrix();
		m_vector = new tdVector();
		m_dAmbient = 0.20000000000000001D;
		m_matrixLight = new tdMatrix();
		m_matrixLight.rotateWorld(-0.39269908169872414D, -0.39269908169872414D, 0.0D);
		m_nViewplaneX = i;
		m_nViewplaneY = j;
		m_nViewplaneWidth = k;
		m_nViewplaneHeight = l;
		m_imageViewplane = component.createImage(k, l);
		m_imageBackgrnd = null;
		m_colorBackgrnd = Color.black;
		m_imagePlanet = null;
		m_polygon = new tdPolygon[300];
		setFratio(1.0D);
	}
	private void binSort(int i, int j, double d, double d1, boolean flag) {
		if(j - i == 1) {
			if(m_polygon[i].m_dDistance > m_polygon[j].m_dDistance) {
				tdPolygon tdpolygon = m_polygon[i];
				m_polygon[i] = m_polygon[j];
				m_polygon[j] = tdpolygon;
			}
			return;
		}
		if(flag) {
			d1 = m_polygon[i].m_dDistance;
			d = d1;
			for(int k = i + 1; k <= j; k++) {
				d1 = Math.max(d1, m_polygon[k].m_dDistance);
				d = Math.min(d, m_polygon[k].m_dDistance);
			}

		}
		double d2 = (d1 + d) / 2D;
		if(d2 - d < 0.001D)
			return;
		int l = i;
		int i1;
		for(i1 = j; l < i1;) {
			while(l < i1 && m_polygon[l].m_dDistance <= d2) 
				l++;

			for(; l < i1 && m_polygon[i1].m_dDistance > d2; i1--);
			if(l < i1) {
				tdPolygon tdpolygon1 = m_polygon[l];
				m_polygon[l] = m_polygon[i1];
				m_polygon[i1] = tdpolygon1;
			}
		}

		if(l == j && m_polygon[l].m_dDistance <= d2 || i1 == i && m_polygon[i1].m_dDistance > d2) {
			binSort(i, j, d, d1, true);
			return;
		}
		if(m_polygon[i1].m_dDistance <= d2)
			i1++;
		if(m_polygon[l].m_dDistance > d2)
			l--;
		if(l > i)
			binSort(i, l, d, d2, false);
		if(i1 < j)
			binSort(i1, j, d, d2, false);
	}
	public void render(tdObject atdobject[], int i, Component component) {
		m_nPolygons = 0;
		m_dDistanceMax = -1E+020D;
		m_dDistanceMin = 1E+020D;
		for(int j = 0; j < i; j++)
			renderObject(atdobject[j]);

		if(m_nPolygons > 1)
			binSort(0, m_nPolygons - 1, m_dDistanceMin, m_dDistanceMax, false);
		Graphics g = m_imageViewplane.getGraphics();
		g.setColor(m_colorBackgrnd);
		g.fillRect(0, 0, m_nViewplaneWidth, m_nViewplaneHeight);
		if(m_imageBackgrnd != null)
			g.drawImage(m_imageBackgrnd, 0, 0, null);
		boolean flag = false;
		if(m_imagePlanet != null)
			flag = true;
		for(int k = m_nPolygons - 1; k >= 0; k--) {
			if(flag && m_polygon[k].m_dDistance < m_vector.m_dZ) {
				g.drawImage(m_imagePlanet, m_nPlanetX, m_nPlanetY, null);
				flag = false;
			}
			m_polygon[k].draw(g);
		}

		if(flag) {
			g.drawImage(m_imagePlanet, m_nPlanetX, m_nPlanetY, null);
			boolean flag1 = false;
		}
		g.dispose();
		Graphics g1 = component.getGraphics();
		g1.drawImage(m_imageViewplane, m_nViewplaneX, m_nViewplaneY, null);
		g1.dispose();
	}
	private void renderObject(tdObject tdobject) {
		for(int i = 0; i < tdobject.m_nVertices; i++) {
			double d = tdobject.m_vectorWorld[i].m_dX - m_vector.m_dX;
			double d1 = tdobject.m_vectorWorld[i].m_dY - m_vector.m_dY;
			double d3 = tdobject.m_vectorWorld[i].m_dZ - m_vector.m_dZ;
			tdVector tdvector = tdobject.m_vectorView[i];
			tdvector.m_dX = d * m_matrix.m_dXx + d1 * m_matrix.m_dXy + d3 * m_matrix.m_dXz;
			tdvector.m_dY = d * m_matrix.m_dYx + d1 * m_matrix.m_dYy + d3 * m_matrix.m_dYz;
			tdvector.m_dZ = d * m_matrix.m_dZx + d1 * m_matrix.m_dZy + d3 * m_matrix.m_dZz;
			if(tdvector.m_dZ <= 0.10000000000000001D) {
				tdobject.m_bVisible[i] = false;
			} else {
				tdobject.m_bVisible[i] = true;
				tdobject.m_nXview[i] = (int)((-tdvector.m_dX / tdvector.m_dZ) * m_dViewplaneMult + m_dXoffset);
				tdobject.m_nYview[i] = (int)((-tdvector.m_dY / tdvector.m_dZ) * m_dViewplaneMult + m_dYoffset);
			}
		}

		for(int j = 0; j < tdobject.m_nPolygons; j++) {
			tdFace tdface = tdobject.m_model.m_face[j];
			tdPolygon tdpolygon = tdobject.m_polygon[j];
			tdpolygon.m_dDistance = 0.0D;
			boolean flag = true;
			for(int k = 0; k < tdface.m_nVertices; k++) {
				int l = tdface.m_ndxVertex[k];
				if(!tdobject.m_bVisible[l]) {
					flag = false;
					break;
				}
				tdpolygon.m_nX[k] = tdobject.m_nXview[l];
				tdpolygon.m_nY[k] = tdobject.m_nYview[l];
				tdpolygon.m_dDistance = Math.max(tdpolygon.m_dDistance, tdobject.m_vectorView[l].m_dZ);
			}

			tdVector tdvector1 = tdobject.m_vectorView[tdface.m_ndxVertex[0]];
			tdVector tdvector2 = tdobject.m_vectorView[tdface.m_ndxVertex[1]];
			tdVector tdvector3 = tdobject.m_vectorView[tdface.m_ndxVertex[2]];
			if(flag) {
				double d2 = tdvector2.m_dX - tdvector1.m_dX;
				double d4 = tdvector2.m_dY - tdvector1.m_dY;
				double d5 = tdvector2.m_dZ - tdvector1.m_dZ;
				double d6 = tdvector3.m_dX - tdvector2.m_dX;
				double d7 = tdvector3.m_dY - tdvector2.m_dY;
				double d8 = tdvector3.m_dZ - tdvector2.m_dZ;
				double d9 = d4 * d8 - d5 * d7;
				double d10 = d5 * d6 - d2 * d8;
				double d11 = d2 * d7 - d4 * d6;
				double d12 = tdvector2.m_dX * d9 + tdvector2.m_dY * d10 + tdvector2.m_dZ * d11;
				boolean flag1 = true;
				if(d12 >= 0.0D) {
					tdpolygon.m_color = tdface.m_colorFront;
					flag1 = true;
				} else {
					tdpolygon.m_color = tdface.m_colorBack;
					flag1 = false;
				}
				if(tdpolygon.m_color == null) {
					flag = false;
				} else {
					double d14 = Math.sqrt(d9 * d9 + d10 * d10 + d11 * d11);
					double d13 = (d9 * m_matrixLight.m_dZx + d10 * m_matrixLight.m_dZy + d11 * m_matrixLight.m_dZz) / d14;
					if(!flag1)
						d13 *= -1D;
					if(d13 < 0.0D)
						d13 = 0.0D;
					if(d13 < 1.0D) {
						double d15 = m_dAmbient + (1.0D - m_dAmbient) * d13;
						int i1 = tdpolygon.m_color.getRGB();
						i1 = ((int)((double)(i1 & 0xff0000) * d15) & 0xff0000) + ((int)((double)(i1 & 0xff00) * d15) & 0xff00) + (int)((double)(i1 & 0xff) * d15);
						tdpolygon.m_color = new Color(i1);
					}
				}
			}
			if(flag && m_nPolygons < 300) {
				m_polygon[m_nPolygons] = tdpolygon;
				m_nPolygons++;
				m_dDistanceMax = Math.max(m_dDistanceMax, tdpolygon.m_dDistance);
				m_dDistanceMin = Math.min(m_dDistanceMin, tdpolygon.m_dDistance);
			}
		}

	}
	public void setFratio(double d) {
		m_dFratio = d;
		m_dViewplaneMult = Math.max(m_nViewplaneWidth, m_nViewplaneHeight) * d;
		m_dXoffset = (double)m_nViewplaneWidth / 2D;
		m_dYoffset = (double)m_nViewplaneHeight / 2D;
	}
}