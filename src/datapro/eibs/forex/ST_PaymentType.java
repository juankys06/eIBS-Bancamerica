package datapro.eibs.forex;

import java.util.Map;
import java.util.LinkedHashMap;

public class ST_PaymentType {

    private String language = "es";
    private Map paymentType_ES_Map = null;
    
    public ST_PaymentType()
    {
	init();
    }
    
    public ST_PaymentType(String lang)
    {
	this.language = lang;
	init();
    }
    
    public void init()
    {
	paymentType_ES_Map = new LinkedHashMap();
	paymentType_ES_Map.put("E", "Efectivo");
	paymentType_ES_Map.put("P", "Cuentas Propias Cliente");
	paymentType_ES_Map.put("T", "Transferencias");
	paymentType_ES_Map.put("C", "Cheque de Gerencia");
	paymentType_ES_Map.put("X", "Cuentas por Cobrar - Efectivo");
	paymentType_ES_Map.put("Y", "Cuentas por Cobrar - Cheque");
	paymentType_ES_Map.put("Z", "Cuentas por Cobrar - Transferencia");	
	paymentType_ES_Map.put("", "Ninguno");
    }
    
    public String get(String key)
    {
	if (this.language.equals("es"))
	    return (String) paymentType_ES_Map.get(key);
	else
	    return (String) paymentType_ES_Map.get(key);
    }
    
    public Map getMap()
    {
	return paymentType_ES_Map;
    }
}
