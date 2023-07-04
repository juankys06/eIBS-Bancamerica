package datapro.eibs.applets.portal;

// Processed by NMI's Java Code Viewer 4.8.0 © 1997-1999 B. Lemaire
// Website: http://njcv.htmlplanet.com	E-mail: info@njcv.htmlplanet.com
// Copy registered to Evaluation Copy
// Source File Name:   C:\JavaApplets\Orbiter\Orbiter.java


class tdObject {

	public tdModel m_model;
	public tdVector m_vector;
	public tdMatrix m_matrix;
	public int m_nVertices;
	public tdVector m_vectorWorld[];
	public int m_nXview[];
	public int m_nYview[];
	public tdVector m_vectorView[];
	public boolean m_bVisible[];
	public int m_nPolygons;
	public tdPolygon m_polygon[];

	public tdObject(tdModel tdmodel) {
		m_model = tdmodel;
		m_vector = new tdVector();
		m_matrix = new tdMatrix();
		m_nVertices = tdmodel.m_nVertices;
		m_vectorWorld = new tdVector[m_nVertices];
		m_vectorView = new tdVector[m_nVertices];
		for(int i = 0; i < m_nVertices; i++) {
			m_vectorWorld[i] = new tdVector();
			m_vectorView[i] = new tdVector();
		}

		m_nXview = new int[m_nVertices];
		m_nYview = new int[m_nVertices];
		m_bVisible = new boolean[m_nVertices];
		m_nPolygons = tdmodel.m_nFaces;
		m_polygon = new tdPolygon[m_nPolygons];
		for(int j = 0; j < m_nPolygons; j++)
			m_polygon[j] = new tdPolygon(tdmodel.m_face[j]);

	}
	public void rotate() {
		for(int i = 0; i < m_nVertices; i++) {
			double d = m_model.m_vectorVertex[i].m_dX;
			double d1 = m_model.m_vectorVertex[i].m_dY;
			double d2 = m_model.m_vectorVertex[i].m_dZ;
			m_vectorWorld[i].m_dX = d * m_matrix.m_dXx + d1 * m_matrix.m_dYx + d2 * m_matrix.m_dZx;
			m_vectorWorld[i].m_dY = d * m_matrix.m_dXy + d1 * m_matrix.m_dYy + d2 * m_matrix.m_dZy;
			m_vectorWorld[i].m_dZ = d * m_matrix.m_dXz + d1 * m_matrix.m_dYz + d2 * m_matrix.m_dZz;
		}

	}
	public void transform() {
		for(int i = 0; i < m_nVertices; i++) {
			double d = m_model.m_vectorVertex[i].m_dX;
			double d1 = m_model.m_vectorVertex[i].m_dY;
			double d2 = m_model.m_vectorVertex[i].m_dZ;
			m_vectorWorld[i].m_dX = d * m_matrix.m_dXx + d1 * m_matrix.m_dYx + d2 * m_matrix.m_dZx + m_vector.m_dX;
			m_vectorWorld[i].m_dY = d * m_matrix.m_dXy + d1 * m_matrix.m_dYy + d2 * m_matrix.m_dZy + m_vector.m_dY;
			m_vectorWorld[i].m_dZ = d * m_matrix.m_dXz + d1 * m_matrix.m_dYz + d2 * m_matrix.m_dZz + m_vector.m_dZ;
		}

	}
	public void translate() {
		for(int i = 0; i < m_nVertices; i++) {
			m_vectorWorld[i].m_dX = m_model.m_vectorVertex[i].m_dX + m_vector.m_dX;
			m_vectorWorld[i].m_dY = m_model.m_vectorVertex[i].m_dY + m_vector.m_dY;
			m_vectorWorld[i].m_dZ = m_model.m_vectorVertex[i].m_dZ + m_vector.m_dZ;
		}

	}
}