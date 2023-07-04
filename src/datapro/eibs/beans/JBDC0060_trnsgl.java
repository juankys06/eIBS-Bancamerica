package datapro.eibs.beans;

/**
 * Inserte aquí la descripción del tipo.
 * Fecha de creación: (02-05-2007 11:58:54)
 * @author: William Alfaro
 */
import java.util.*;
import java.math.*;
 
public class JBDC0060_trnsgl {
  private String TRNCCY;
  private String TRNGLN;
  private String TRNACC;
  private String TRNDSC;
  private double TRNAMT;
  private String TRNTYP;
  private String TRNTCD;  
  private double TRNEXR; 
  private String TRNBRN;
  private double TRNEQV;
  
public JBDC0060_trnsgl(String pData) {
  if (pData != null && pData.length() == 113) {	
	TRNCCY = pData.substring(0, 3);
    TRNGLN = pData.substring(3, 19);
	TRNACC = pData.substring(19, 31);
	TRNDSC = pData.substring(31, 61);
	try {
		TRNAMT = Double.valueOf(pData.substring(61, 76)).doubleValue() / 100;
	} catch (Exception e) {
		TRNAMT = 0.00; 
		e.printStackTrace();
	}
	TRNTYP = pData.substring(76, 77);
	TRNTCD = pData.substring(77, 79);
	try {
		TRNEXR = Double.valueOf(pData.substring(79, 94)).doubleValue() / 1000000;
	} catch (Exception e) {
		TRNEXR = 0.00000; 
		e.printStackTrace();
	}
	try {
		TRNEQV = Double.valueOf(pData.substring(94, 109)).doubleValue() / 100;
	} catch (Exception e) {
		TRNEQV = 0.00; 
		e.printStackTrace();
	}
	TRNBRN = pData.substring(110, 113);
  } else {
  	TRNAMT = 0.00; 
  	TRNEXR = 0.00000;
  	TRNEQV = 0.00;
  }
  			                   
}


  public String getTRNCCY() { 
	 return TRNCCY;
  }       
  public String getTRNGLN() { 
	 return TRNGLN;
  }       
  public String getTRNACC() { 
	 return TRNACC;
  }       
  public String getTRNDSC() { 
	 return TRNDSC;
  }       
  public double getTRNAMT() { 
	 return TRNAMT;
  }       
  public String getTRNTYP() { 
	 return TRNTYP;
  }     
  public String getTRNTCD() { 
	 return TRNTCD;
  }     
  public double getTRNEXR() { 
	 return TRNEXR;
  }     
  public String getTRNBRN() { 
	 return TRNBRN;
  }     
  public double getTRNEQV() { 
	 return TRNEQV;
  }     
  
}