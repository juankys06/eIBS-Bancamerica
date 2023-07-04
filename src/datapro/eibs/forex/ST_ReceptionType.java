package datapro.eibs.forex;

import java.util.Map;
import java.util.LinkedHashMap;

public class ST_ReceptionType {

    private String language = "es";
    private Map receptionType_ES_Map = null;
    
    public ST_ReceptionType()
    {
	init();
    }
    
    public ST_ReceptionType(String lang)
    {
	this.language = lang;
	init();
    }
    
    public void init()
    {
	receptionType_ES_Map = new LinkedHashMap();
	receptionType_ES_Map.put("E", "Efectivo");
	receptionType_ES_Map.put("P", "Cuentas Propias Cliente");
	receptionType_ES_Map.put("T", "Transferencias");
	receptionType_ES_Map.put("X", "Cuentas por Cobrar - Efectivo");
	receptionType_ES_Map.put("Y", "Cheque Otro Banco");
	receptionType_ES_Map.put("Z", "Cuentas por Cobrar - Transferencia");	
	receptionType_ES_Map.put("", "Ninguno");
    }
    
    public String get(String key)
    {
	if (this.language.equals("es"))
	    return (String) receptionType_ES_Map.get(key);
	else
	    return (String) receptionType_ES_Map.get(key);
    }
    
    public Map getMap()
    {
	return receptionType_ES_Map;
    }
}
