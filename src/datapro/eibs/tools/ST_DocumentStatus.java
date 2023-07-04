package datapro.eibs.tools;

import java.util.LinkedHashMap;
import java.util.Map;

public class ST_DocumentStatus {
    private String language = "es";
    private Map documentStatus_ES_Map = null;
    private Map documentStatus_EN_Map = null;
    
    public ST_DocumentStatus() {
	init();
    }
    
    public ST_DocumentStatus(String language)
    {
	this.language = language;
	init();
    }
    
    public void init()
    {
	documentStatus_ES_Map = new LinkedHashMap();
	documentStatus_ES_Map.put("CO", "ESCANEADO");
	documentStatus_ES_Map.put("NP", "NO PRESENTADO");
	documentStatus_ES_Map.put("", "INCOMPLETO");
	documentStatus_ES_Map.put("PD", "VENCIDO");
	documentStatus_ES_Map.put("NA", "NO APLICABLE");
	documentStatus_ES_Map.put("EN", "ENTREGADO POR CLIENTE");	
    }   
    
    public String get(String key)
    {
	if (this.language.equals("es"))
	    return (String) documentStatus_ES_Map.get(key);
	else
	    return (String) documentStatus_EN_Map.get(key);
    }
    
    public Map getMap()
    {
	return documentStatus_ES_Map;
    }
}
