package datapro.eibs.applets.portal;

// Processed by NMI's Java Code Viewer 4.8.0 © 1997-1999 B. Lemaire
// Website: http://njcv.htmlplanet.com	E-mail: info@njcv.htmlplanet.com
// Copy registered to Evaluation Copy
// Source File Name:   C:\JavaApplets\Orbiter\Orbiter.java

import java.awt.Color;

class tdModel {

	public int m_nVertices;
	public int m_nFaces;
	public tdVector m_vectorVertex[];
	public tdFace m_face[];

	public tdModel(tdObject atdobject[], int i) {
		m_nVertices = 0;
		m_nFaces = 0;
		for(int j = 0; j < i; j++) {
			atdobject[j].transform();
			m_nVertices += atdobject[j].m_nVertices;
			m_nFaces += atdobject[j].m_model.m_nFaces;
		}

		m_vectorVertex = new tdVector[m_nVertices];
		m_face = new tdFace[m_nFaces];
		int k = 0;
		int l = 0;
		for(int i1 = 0; i1 < i; i1++) {
			tdObject tdobject = atdobject[i1];
			tdModel tdmodel = tdobject.m_model;
			for(int j1 = 0; j1 < tdobject.m_nVertices; j1++) {
				m_vectorVertex[j1 + k] = new tdVector();
				m_vectorVertex[j1 + k].m_dX = tdobject.m_vectorWorld[j1].m_dX;
				m_vectorVertex[j1 + k].m_dY = tdobject.m_vectorWorld[j1].m_dY;
				m_vectorVertex[j1 + k].m_dZ = tdobject.m_vectorWorld[j1].m_dZ;
			}

			for(int k1 = 0; k1 < tdmodel.m_nFaces; k1++) {
				tdFace tdface = tdmodel.m_face[k1];
				tdFace tdface1 = new tdFace();
				tdface1.m_nVertices = tdface.m_nVertices;
				tdface1.m_ndxVertex = new int[tdface.m_nVertices];
				for(int l1 = 0; l1 < tdface.m_nVertices; l1++)
					tdface1.m_ndxVertex[l1] = tdface.m_ndxVertex[l1] + k;

				tdface1.m_colorFront = new Color(tdface.m_colorFront.getRGB());
				if(tdface.m_colorBack == null)
					tdface1.m_colorBack = null;
				else
					tdface1.m_colorBack = new Color(tdface.m_colorBack.getRGB());
				m_face[k1 + l] = tdface1;
			}

			k += tdobject.m_nVertices;
			l += tdmodel.m_nFaces;
		}

	}
	public tdModel(int i, int j) {
		m_nVertices = i;
		m_nFaces = j;
		m_vectorVertex = new tdVector[i];
		m_face = new tdFace[j];
	}
	public void specifyFace(int i, tdFace tdface) {
		m_face[i] = tdface;
	}
	public void specifyVertex(int i, tdVector tdvector) {
		m_vectorVertex[i] = tdvector;
	}
}