package datapro.eibs.beans;

import java.util.StringTokenizer;


/**
 * This type was created in VisualAge.
 */
public class IdentificationData {

	//Define variables request
	private String country = "";  
	private String countryDescription = "";
	private String identification = "";
	private String checkDigit = "";
	private String legalType = "";
	private String matrix[];

	/**
	 * 
	 */
	public IdentificationData(String identification, String country) {
		super();
		setCountry(country);
		setIdentification(identification);
	}	
	
	public IdentificationData() {
		super();
	}

	/**
	 * @param string
	 */
	public void setCheckDigit(String string) {
		checkDigit = string;
	}
		
	/**
	 * @return
	 */
	public String getCheckDigit() {
		return checkDigit;
	}
		
	/**
	 * @return
	 */
	public String getCheckDigit(int size) {
		String identification = getIdentification(); 
		if (identification.length() > size) {
			return identification.substring(identification.length() - size);
		}
		return "";
	}

	/**
	 * @return
	 */
	public String getCountry() {
		return country.toUpperCase();
	}

	/**
	 * @return
	 */
	public String getCountryDescription() {
		return countryDescription;
	}
	/**
	 * @param string
	 */
	public void setCountry(String string) {
		country = string;
	}

	/**
	 * @param string
	 */
	public void setCountryDescription(String string) {
		countryDescription = string;
	}
	
	private static String[] split(String str, String token) {
		StringTokenizer st = new StringTokenizer(str, token);
		int i = 0;
		String[] ret = new String[st.countTokens()];
		for (; st.hasMoreElements();) {
			String element = (String) st.nextElement();
			ret[i++] = element.trim();
		}
		return ret;
	}

	/**
	 * @param string
	 */
	public void setIdentification(String string) {
		identification = string;
		matrix = split(identification, "-");
		if (matrix != null
			&& matrix.length != 0 
			&& matrix[matrix.length - 1] != null) {
			String last = matrix[matrix.length - 1].trim();
			int spacePos = last.indexOf(' ');
			if (spacePos > 0) {
				matrix[matrix.length - 1] = last.substring(0, spacePos);
				setCheckDigit(last.substring(spacePos + 1, last.length()));
			}
		}
	}

	/**
	 * @return
	 */
	public String getIdentification() {
		return identification;
	}
	
	public String getIdentification(int offset) {
		String identification = "";
		if (matrix != null 
			&& matrix.length > offset 
			&& matrix[offset] != null) {
			identification = matrix[offset];
		}
		return identification;
	}
	
	public String getIdentification(int offset, int len) {
		String identification = "";
		if (!getIdentification(offset).equals("")){
			identification = getIdentification(offset);
		} else if (!getIdentification().equals("")){
			identification = getIdentification();
		}
		return identification.length() > len ? "" : identification;
	}

	/**
	 * @return
	 */
	public String getLegalType() {
		return legalType;
	}
	
	private String substring(String string, int from, int to) {
		if (string.length() > to) {
			return string.substring(from, to);
		} else {
			return "";
		}
	}

	/**
	 * @param string
	 */
	public void setLegalType(String type) {
		legalType =
			type.startsWith("C") || type.endsWith("C") ? "1" :  
				type.startsWith("P") || type.endsWith("P") ? "2" : 
					type.startsWith("O") || type.endsWith("O") ? "3" : type;
		if (matrix == null 
			|| matrix.length < 2) {
			//If matrix is not set then the identification does not have a token

			String identification = getIdentification();
			
			if (getCountry().equals("PA")){ // Panama
				if (legalType.equals("2")){
					// Personal Customer
					//			09-PI-00001-001277 70
					// 	Provincia Lt Folio Asient DV
					matrix = new String[4];
					matrix[0] = substring(identification, 0, 2);
					if (identification.length() > 13) {
						matrix[1] = substring(identification, 2, 4);
						matrix[2] = substring(identification, 4, 9);
						matrix[3] = substring(identification, 9, 15);
					} else {
						matrix[1] = substring(identification, 2, 2);//Is not saved in DB
						matrix[2] = substring(identification, 2, 7);
						matrix[3] = substring(identification, 7, 13);
					}
				} else if (legalType.equals("1")
					|| legalType.equals("3")){
					// Corporate Customer
					//		0055496-0012-00304533 50
					//		Rollo  Folio  Asiento DV
					matrix = new String[3];					
					matrix[0] = substring(identification, 0, 7);
					matrix[1] = substring(identification, 7, 12);
					matrix[2] = substring(identification, 12, 20);
				}	
			} else if (getCountry().equals("DR")){ // Dominicana
				if (legalType.equals("2")){
					// Personal Customer
					//	001-1234567-1
					matrix = new String[2];
					matrix[0] = substring(identification, 0, 3);
					matrix[1] = substring(identification, 3, 10);
				} else if (legalType.equals("1")
					|| legalType.equals("3")){
					// Corporate Customer
					//	1-12-12345-1
					matrix = new String[3];
					matrix[0] = substring(identification, 0, 1);
					matrix[1] = substring(identification, 1, 3);
					matrix[2] = substring(identification, 3, 8);
				}
			}
		}
	}
	
	public boolean isLegalType(String type) {
		String tempType = 
			type.startsWith("P") || type.endsWith("P") ? "2" : 
				type.startsWith("C") || type.endsWith("C") ? "1" : 
					type.startsWith("O") || type.endsWith("O") ? "3" : type;
		return legalType.equals(tempType);
	}

	/**
	 * @param sets the identification split matrix depending of the legal type
	 */
	public void setMatrix(String[] matrix) {
		this.matrix = matrix;
	}

}