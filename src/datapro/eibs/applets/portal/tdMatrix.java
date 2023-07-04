package datapro.eibs.applets.portal;

// Processed by NMI's Java Code Viewer 4.8.0 © 1997-1999 B. Lemaire
// Website: http://njcv.htmlplanet.com	E-mail: info@njcv.htmlplanet.com
// Copy registered to Evaluation Copy
// Source File Name:   C:\JavaApplets\Orbiter\Orbiter.java


class tdMatrix {

	public double m_dXx;
	public double m_dXy;
	public double m_dXz;
	public double m_dYx;
	public double m_dYy;
	public double m_dYz;
	public double m_dZx;
	public double m_dZy;
	public double m_dZz;

	public tdMatrix() {
		m_dXx = 1.0D;
		m_dXy = 0.0D;
		m_dXz = 0.0D;
		m_dYx = 0.0D;
		m_dYy = 1.0D;
		m_dYz = 0.0D;
		m_dZx = 0.0D;
		m_dZy = 0.0D;
		m_dZz = 1.0D;
	}
	public tdMatrix(double d, double d1, double d2, double d3, double d4, double d5, double d6, 
			double d7, double d8) {
		m_dXx = d;
		m_dXy = d1;
		m_dXz = d2;
		m_dYx = d3;
		m_dYy = d4;
		m_dYz = d5;
		m_dZx = d6;
		m_dZy = d7;
		m_dZz = d8;
	}
	public void normalize() {
		m_dZx = m_dXy * m_dYz - m_dXz * m_dYy;
		m_dZy = m_dXz * m_dYx - m_dXx * m_dYz;
		m_dZz = m_dXx * m_dYy - m_dXy * m_dYx;
		m_dYx = m_dZy * m_dXz - m_dZz * m_dXy;
		m_dYy = m_dZz * m_dXx - m_dZx * m_dXz;
		m_dYz = m_dZx * m_dXy - m_dZy * m_dXx;
		double d = Math.sqrt(m_dXx * m_dXx + m_dXy * m_dXy + m_dXz * m_dXz);
		if(d == 0.0D)
			return;
		m_dXx /= d;
		m_dXy /= d;
		m_dXz /= d;
		d = Math.sqrt(m_dYx * m_dYx + m_dYy * m_dYy + m_dYz * m_dYz);
		if(d == 0.0D)
			return;
		m_dYx /= d;
		m_dYy /= d;
		m_dYz /= d;
		d = Math.sqrt(m_dZx * m_dZx + m_dZy * m_dZy + m_dZz * m_dZz);
		if(d == 0.0D) {
			return;
		} else {
			m_dZx /= d;
			m_dZy /= d;
			m_dZz /= d;
			return;
		}
	}
	public void rotateSelf(double d, double d1, double d2) {
		double d3 = 0.0D;
		double d7 = 0.0D;
		double d11 = 0.0D;
		double d14 = 0.0D;
		double d17 = 0.0D;
		double d20 = 0.0D;
		double d23 = 0.0D;
		double d26 = 0.0D;
		double d29 = 0.0D;
		double d32 = 0.0D;
		double d35 = 0.0D;
		if(d != 0.0D) {
			double d4 = Math.cos(d);
			double d8 = Math.sin(d);
			double d21 = m_dYx * d4 + m_dZx * d8;
			double d24 = m_dYy * d4 + m_dZy * d8;
			double d27 = m_dYz * d4 + m_dZz * d8;
			double d30 = m_dZx * d4 - m_dYx * d8;
			double d33 = m_dZy * d4 - m_dYy * d8;
			double d36 = m_dZz * d4 - m_dYz * d8;
			m_dYx = d21;
			m_dYy = d24;
			m_dYz = d27;
			m_dZx = d30;
			m_dZy = d33;
			m_dZz = d36;
		}
		if(d1 != 0.0D) {
			double d5 = Math.cos(d1);
			double d9 = Math.sin(d1);
			double d12 = m_dXx * d5 + m_dZx * d9;
			double d15 = m_dXy * d5 + m_dZy * d9;
			double d18 = m_dXz * d5 + m_dZz * d9;
			double d31 = m_dZx * d5 - m_dXx * d9;
			double d34 = m_dZy * d5 - m_dXy * d9;
			double d37 = m_dZz * d5 - m_dXz * d9;
			m_dXx = d12;
			m_dXy = d15;
			m_dXz = d18;
			m_dZx = d31;
			m_dZy = d34;
			m_dZz = d37;
		}
		if(d2 != 0.0D) {
			double d6 = Math.cos(d2);
			double d10 = Math.sin(d2);
			double d13 = m_dXx * d6 + m_dYx * d10;
			double d16 = m_dXy * d6 + m_dYy * d10;
			double d19 = m_dXz * d6 + m_dYz * d10;
			double d22 = m_dYx * d6 - m_dXx * d10;
			double d25 = m_dYy * d6 - m_dXy * d10;
			double d28 = m_dYz * d6 - m_dXz * d10;
			m_dXx = d13;
			m_dXy = d16;
			m_dXz = d19;
			m_dYx = d22;
			m_dYy = d25;
			m_dYz = d28;
		}
	}
	public void rotateWorld(double d, double d1, double d2) {
		double d3 = 0.0D;
		double d7 = 0.0D;
		double d11 = 0.0D;
		double d14 = 0.0D;
		double d17 = 0.0D;
		double d20 = 0.0D;
		double d23 = 0.0D;
		double d26 = 0.0D;
		double d29 = 0.0D;
		double d32 = 0.0D;
		double d35 = 0.0D;
		if(d != 0.0D) {
			double d4 = Math.cos(d);
			double d8 = Math.sin(d);
			double d15 = m_dXy * d4 - m_dXz * d8;
			double d18 = m_dXz * d4 + m_dXy * d8;
			double d24 = m_dYy * d4 - m_dYz * d8;
			double d27 = m_dYz * d4 + m_dYy * d8;
			double d33 = m_dZy * d4 - m_dZz * d8;
			double d36 = m_dZz * d4 + m_dZy * d8;
			m_dXy = d15;
			m_dXz = d18;
			m_dYy = d24;
			m_dYz = d27;
			m_dZy = d33;
			m_dZz = d36;
		}
		if(d1 != 0.0D) {
			double d5 = Math.cos(d1);
			double d9 = Math.sin(d1);
			double d12 = m_dXx * d5 + m_dXz * d9;
			double d19 = m_dXz * d5 - m_dXx * d9;
			double d21 = m_dYx * d5 + m_dYz * d9;
			double d28 = m_dYz * d5 - m_dYx * d9;
			double d30 = m_dZx * d5 + m_dZz * d9;
			double d37 = m_dZz * d5 - m_dZx * d9;
			m_dXx = d12;
			m_dXz = d19;
			m_dYx = d21;
			m_dYz = d28;
			m_dZx = d30;
			m_dZz = d37;
		}
		if(d2 != 0.0D) {
			double d6 = Math.cos(d2);
			double d10 = Math.sin(d2);
			double d13 = m_dXx * d6 - m_dXy * d10;
			double d16 = m_dXy * d6 + m_dXx * d10;
			double d22 = m_dYx * d6 - m_dYy * d10;
			double d25 = m_dYy * d6 + m_dYx * d10;
			double d31 = m_dZx * d6 - m_dZy * d10;
			double d34 = m_dZy * d6 + m_dZx * d10;
			m_dXx = d13;
			m_dXy = d16;
			m_dYx = d22;
			m_dYy = d25;
			m_dZx = d31;
			m_dZy = d34;
		}
	}
}