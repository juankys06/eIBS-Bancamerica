package datapro.eibs.beans;

import java.util.*;

import datapro.eibs.beans.ELEERRMessage;
import datapro.eibs.sockets.MessageRecord;
import datapro.eibs.sockets.MessageField;

public class sortList {

	public static final int ASCENDENTE = 1;
	public static final int DESCENDENTE = -1;

	public static final int PAGESIZE = 20;	// Tamaño por default de la página

	private int orden;
	private String type;
	private String rutkey;
	private String sYear;
	private String sMonth;
	private String sDay;
	java.util.Vector data = null;
	
	private int pageSize;
	private int baseIdx;
	private int idx;

	public sortList() {
		this.data = new java.util.Vector();
		this.orden = ASCENDENTE;
		this.type = "";
		this.rutkey = "";
		this.sYear = "";
		this.sMonth = "";
		this.sDay = "";
		this.pageSize = PAGESIZE;
		this.baseIdx = 0;
	}

	public boolean showNext() {
		return data.size() > pageSize && baseIdx + pageSize < data.size();
	}
	
	public boolean showPrev() {
		return baseIdx > 0;
	}
	
	public void removeAt(int index) {
		data.removeElementAt(index);
	}

	public int getBaseIdx() {
		return baseIdx;
	}
	
	public void setNextPage() {
		baseIdx += pageSize;
		idx = 0;
	}
	
	public void setPrevPage() {
		baseIdx -= pageSize;
		if (baseIdx < 0)
			baseIdx = 0;
		idx = 0;
	}

	public boolean getNextRecord(MessageRecord msg) {
		if (idx < pageSize)
		msg = this.getData(idx);
		return true;
	}
	
	public MessageRecord getPageRecord(int index) {
		if (index + baseIdx < this.data.size())
			return getData(index + baseIdx);
		else
			return null;
	}
	
	/**
	 * Obtiene el i-esimo elemento de la lista
	 * @param index Indice del elemento a rescatar
	 * @return Elemento tipo MessageRecord, el cual se puede castear posteriormente, a lo que se necesite
	 */
	public MessageRecord getData(int index) {
		return ((sortElem) this.data.elementAt(index)).getData();
	}

	public void setDisplaySize(int newSize) {
		pageSize = newSize;
	}
	
	public int getDisplaySize() {
		return pageSize;
	}
	
	/**
	 * Inserta un elemento a la lista
	 * @param element Mensaje a insertar, tal cual como se recibió desde el socket
	 */
	public void add(MessageRecord element) {
		if (element == null)
			throw new NullPointerException();
		this.data.add(new sortElem(element));
	}

	/**
	 * Determina si una lista de respuestas contiene algun error. Si es asi, lo
	 * devuelve
	 * @return Elemento tipo ELEERRMessage, o nulo si no hay error
	 */
	public boolean IsError() {
		if (data.size() == 0)
			return false;
		else {
			for (int i = 0; i < this.data.size(); i++) {
				MessageRecord data = getData(i);
				if (data.getFormatName().equals("ELEERR")) {
					ELEERRMessage error = (ELEERRMessage)data;
					return !error.getERRNUM().equals("0");
				}
			}
			return false;
		}
	}
	
	public ELEERRMessage getError() {
		if (data.size() == 0)
			return null;
		else {
			for (int i = 0; i < this.data.size(); i++) {
				MessageRecord data = getData(i);
				if (data.getFormatName().equals("ELEERR")) {
					ELEERRMessage error = (ELEERRMessage)data;
					return error;
				}
			}
			return null;
		}
	}
	
	/**
	 * Setea el campo por el cual se ordenará la lista
	 * @param colNum Número del campo (relativo al mensaje), por el cual se ordenará
	 */
	public void setSortKey(int[] colNum) {
		for (int i = 0; i < this.data.size(); i++) {
			sortElem element = (sortElem) this.data.elementAt(i);
			element.setKey(colNum);
		}
	}

	/**
	 * Setea el campo por el cual se ordenará la lista
	 * @param colName Nombre del campo (relativo al mensaje), por el cual se ordenará
	 */
	public void setSortKey(String[] colName) {
		for (int i = 0; i < this.data.size(); i++) {
			sortElem element = (sortElem) this.data.elementAt(i);
			element.setKey(colName);
		}
	}

	/**
	 * Setea el campo Rut para ordenar
	 * @param newOrder Tipo de ordenamiento (sortList.ASCENDENTE o sortList.DESCENDENTE)
	 */
	public void setRutKey(int rutkey) {
		for (int i = 0; i < this.data.size(); i++) {
			sortElem element = (sortElem) this.data.elementAt(i);
			element.setRutKey(rutkey);
		}
	}

	public void setRutKey(String rutkey) {
		for (int i = 0; i < this.data.size(); i++) {
			sortElem element = (sortElem) this.data.elementAt(i);
			element.setRutKey(rutkey);
		}
	}

	/**
	 * Setea los campos Fecha por lo que se va a ordenar.
	 * @param newOrder Tipo de ordenamiento (sortList.ASCENDENTE o sortList.DESCENDENTE)
	 */
	public void setDateKey(String sYear, String sMonth, String sDay) {
		for (int i = 0; i < this.data.size(); i++) {
			sortElem element = (sortElem) this.data.elementAt(i);
			element.setDateKey(sYear, sMonth, sDay);
		}
	}

	public void setDateKey(int sYear, int sMonth, int sDay) {
		for (int i = 0; i < this.data.size(); i++) {
			sortElem element = (sortElem) this.data.elementAt(i);
			element.setDateKey(String.valueOf(sYear), String.valueOf(sMonth), String.valueOf(sDay));
		}
	}

	/**
	 * Setea el tipo de ordenamiento (ASCENDENTE o DESCENDENTE)
	 * @param newOrder Tipo de ordenamiento (sortList.ASCENDENTE o sortList.DESCENDENTE)
	 */
	public void setSortOrder(int newOrder) {
		this.orden = newOrder;
	}

	public int getSortOrder() {
		return this.orden;
	}
	
	/**
	 * Setea el tipo de dato para el ordenamiento, para aquellos campos distintos a String
	 * y numéricos. Ej, tipo RUT
	 * @param newType Tipo de dato
	 */
	public void setSortType(String newType) {
		this.type = newType;
	}

	/**
	 * Realiza el ordenamiento
	 */
	public void sort() {
		if (orden == ASCENDENTE)
			Collections.sort(this.data, sortList.newAscOrden);
		else
			Collections.sort(this.data, sortList.newDescOrden);
		this.type = "";
		this.baseIdx = 0;
	}

	private static Comparator newAscOrden = new Comparator() {
		public int compare(Object obj1, Object obj2) {
			sortElem elem1 = (sortElem) obj1;
			sortElem elem2 = (sortElem) obj2;
			return elem1.getKey().toLowerCase().compareTo(elem2.getKey().toLowerCase());
		}
	};

	private static Comparator newDescOrden = new Comparator() {
		public int compare(Object obj1, Object obj2) {
			sortElem elem1 = (sortElem) obj1;
			sortElem elem2 = (sortElem) obj2;
			return elem2.getKey().toLowerCase().compareTo(elem1.getKey().toLowerCase());
		}
	};

	/**
	 * Obtiene el tamaño de la lista
	 * @return Cantidad de elementos de la lista
	 */
	public int getSize() {
		return this.data.size();
	}

	/**
	 * Genera un vector (arreglo), con toda la data de la lista
	 * @return Objeto tipo Vector, con elementos tipo MessageRecord
	 */
	public java.util.Vector getVector() {
		java.util.Vector vector = new java.util.Vector();
		for (int i = 0; i < this.getSize(); i++) {
			vector.add(this.getData(i));
		}
		return vector;
	}

	/**
	 * La clase sortElem es la clase con la cual se forman las listas
	 * ordenadas tipo sortList
	 */
	private class sortElem {

		/** Campo con el cual se va a ordenar la lista */
		String keyValue = null;

		/**
		 * Es un elemento de datos a ordenar, y es la clase madre de todos
		 * los beans usados por los sockets. Por lo tanto, una vez recibido
		 * un mensaje desde el socket, se agrega a la lista
		 */
		MessageRecord data = null;

		/**
		 * Constructor de la clase
		 * 
		 * @param data El mensaje, tal cual como se recibe desde el socket
		 */
		public sortElem(MessageRecord data) {
			this.data = data;
			this.keyValue = "";
		}

		/**
		 * Setea el campo de llave, por el cual se ordenará posteriormente
		 * 
		 * @param colNum[] Arreglo con los números de campo (dentro del mensaje), por los cuales se ordenará
		 */
		public void setKey(int[] colNum) {
			Vector campos = new Vector();
			for (int i = 0; i < colNum.length; i++) {
				MessageField field = data.getField(colNum[i]);
				campos.add(field);
			}
			setKey(campos);
		}

		/**
		 * Setea un campo fecha como llave
		 * 
		 * @param iYear Número del campo año
		 * @param iMonth Número del campo mes
		 * @param iDay Número del campo dia
		 */
		public void setDateKey(int iYear, int iMonth, int iDay) {
			String dia, mes, ano;
			int year = Integer.parseInt(data.getField(iYear).getString());
			if (year < 50) {
				year += 2000;
			} else if (year <= 99) {
				year += 1900;
			}
			ano = String.valueOf(year);
			mes = "0" + data.getField(iMonth).getString();
			dia = "0" + data.getField(iDay).getString();
			mes = mes.substring(mes.length() - 2);
			dia = dia.substring(dia.length() - 2);
			keyValue = ano + mes + dia;
		}

		/**
		 * Setea un campo fecha como llave
		 * 
		 * @param sYear Nombre del campo año
		 * @param sMonth Nombre del campo mes
		 * @param sDay Nombre del campo dia
		 */
		public void setDateKey(String sYear, String sMonth, String sDay) {
			String dia, mes, ano;
			int year = Integer.parseInt(data.getField(sYear).getString());
			if (year < 50) {
				year += 2000;
			} else if (year <= 99) {
				year += 1900;
			}
			ano = String.valueOf(year);
			mes = "0" + data.getField(sMonth).getString();
			dia = "0" + data.getField(sDay).getString();
			mes = mes.substring(mes.length() - 2);
			dia = dia.substring(dia.length() - 2);
			keyValue = ano + mes + dia;
		}

		/**
		 * Setea un campo RUT como llave
		 * 
		 * @param iRut Número del campo
		 */
		public void setRutKey(int iRut) {
			keyValue = data.getField(iRut).getString();
			keyValue = "00000000000000000000" + keyValue;
			keyValue = keyValue.substring(keyValue.length() - 20);
		}

		/**
		 * Setea un campo RUT como llave
		 * 
		 * @param sRut Nombre del campo
		 */
		public void setRutKey(String sRut) {
			keyValue = data.getField(sRut).getString();
			keyValue = "00000000000000000000" + keyValue;
			keyValue = keyValue.substring(keyValue.length() - 20);
		}

		/**
		 * Setea el campo de llave, con la información de "n" campos
		 * 
		 * @param colName[] Arreglo con los nombres de los campos
		 */
		public void setKey(String[] colName) {
			Vector campos = new Vector();
			for (int i = 0; i < colName.length; i++) {
				MessageField field = data.getField(colName[i]);
				campos.add(field);
			}
			setKey(campos);
		}

		private void setKey(Vector fields) {
			MessageField campo;
			keyValue = "";
			for (int i = 0; i < fields.size(); i++) {
				campo = (MessageField) fields.elementAt(i);
				String key = campo.getString();
				if (campo.getType() == MessageField.DECIMALFIELD) {
					for (int j = 0; j < campo.getLength() - campo.getString().trim().length(); j++)
						key = "0" + key;
				}
				keyValue += key;
			}
		}

		/**
		 * Obtiene la data (mensaje) 
		 */
		public MessageRecord getData() {
			return data;
		}

		public String getKey() {
			return keyValue;
		}
	};
}