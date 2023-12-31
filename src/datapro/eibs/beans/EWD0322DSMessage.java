package datapro.eibs.beans;

import datapro.eibs.sockets.*;
import java.io.*;
import java.math.*;

import java.util.*;

/**
* Class generated by AS/400 CRTCLASS command from EWD0322DS physical file definition.
* 
* File level identifier is 1060822183951.
* Record format level identifier is 5240CB98FFCC9.
*/

public class EWD0322DSMessage extends MessageRecord
{
  final static String fldnames[] = {
                                     "RWDUSR",
                                     "RWDTYP",
                                     "RWDFRC",
                                     "SWDCUN",
                                     "SWDDSC",
                                     "SWDVA1",
                                     "SWDVA2",
                                     "SWDVA3",
                                     "SWDCCY",
                                     "SWDTYL",
                                     "SWDAMN",
                                     "SWDAMU",
                                     "SWDAMA",
                                     "SWDTYP",
                                     "SWDDID",
                                     "SWDREF",
                                     "SWDEQV",
                                     "SWDSTS",
                                     "SWDREC",
                                     "SWDOPE"
                                   };
  final static String tnames[] = {
                                   "RWDUSR",
                                   "RWDTYP",
                                   "RWDFRC",
                                   "SWDCUN",
                                   "SWDDSC",
                                   "SWDVA1",
                                   "SWDVA2",
                                   "SWDVA3",
                                   "SWDCCY",
                                   "SWDTYL",
                                   "SWDAMN",
                                   "SWDAMU",
                                   "SWDAMA",
                                   "SWDTYP",
                                   "SWDDID",
                                   "SWDREF",
                                   "SWDEQV",
                                   "SWDSTS",
                                   "SWDREC",
                                   "SWDOPE"
                                 };
  final static String fid = "1060822183951";
  final static String rid = "5240CB98FFCC9";
  final static String fmtname = "EWD0322DS";
  final int FIELDCOUNT = 20;
  private static Hashtable tlookup = new Hashtable();
  private CharacterField fieldRWDUSR = null;
  private CharacterField fieldRWDTYP = null;
  private DecimalField fieldRWDFRC = null;
  private DecimalField fieldSWDCUN = null;
  private CharacterField fieldSWDDSC = null;
  private DecimalField fieldSWDVA1 = null;
  private DecimalField fieldSWDVA2 = null;
  private DecimalField fieldSWDVA3 = null;
  private CharacterField fieldSWDCCY = null;
  private CharacterField fieldSWDTYL = null;
  private DecimalField fieldSWDAMN = null;
  private DecimalField fieldSWDAMU = null;
  private DecimalField fieldSWDAMA = null;
  private CharacterField fieldSWDTYP = null;
  private CharacterField fieldSWDDID = null;
  private DecimalField fieldSWDREF = null;
  private DecimalField fieldSWDEQV = null;
  private CharacterField fieldSWDSTS = null;
  private DecimalField fieldSWDREC = null;
  private CharacterField fieldSWDOPE = null;

  /**
  * Constructor for EWD0322DSMessage.
  */
  public EWD0322DSMessage()
  {
    createFields();
    initialize();
  }

  /**
  * Create fields for this message.
  * This method implements the abstract method in the MessageRecord superclass.
  */
  protected void createFields()
  {
    recordsize = 194;
    fileid = fid;
    recordid = rid;
    message = new byte[getByteLength()];
    formatname = fmtname;
    fieldnames = fldnames;
    tagnames = tnames;
    fields = new MessageField[FIELDCOUNT];

    fields[0] = fieldRWDUSR =
    new CharacterField(message, HEADERSIZE + 0, 10, "RWDUSR");
    fields[1] = fieldRWDTYP =
    new CharacterField(message, HEADERSIZE + 10, 1, "RWDTYP");
    fields[2] = fieldRWDFRC =
    new DecimalField(message, HEADERSIZE + 11, 8, 0, "RWDFRC");
    fields[3] = fieldSWDCUN =
    new DecimalField(message, HEADERSIZE + 19, 10, 0, "SWDCUN");
    fields[4] = fieldSWDDSC =
    new CharacterField(message, HEADERSIZE + 29, 30, "SWDDSC");
    fields[5] = fieldSWDVA1 =
    new DecimalField(message, HEADERSIZE + 59, 3, 0, "SWDVA1");
    fields[6] = fieldSWDVA2 =
    new DecimalField(message, HEADERSIZE + 62, 3, 0, "SWDVA2");
    fields[7] = fieldSWDVA3 =
    new DecimalField(message, HEADERSIZE + 65, 3, 0, "SWDVA3");
    fields[8] = fieldSWDCCY =
    new CharacterField(message, HEADERSIZE + 68, 3, "SWDCCY");
    fields[9] = fieldSWDTYL =
    new CharacterField(message, HEADERSIZE + 71, 4, "SWDTYL");
    fields[10] = fieldSWDAMN =
    new DecimalField(message, HEADERSIZE + 75, 17, 2, "SWDAMN");
    fields[11] = fieldSWDAMU =
    new DecimalField(message, HEADERSIZE + 92, 17, 2, "SWDAMU");
    fields[12] = fieldSWDAMA =
    new DecimalField(message, HEADERSIZE + 109, 17, 2, "SWDAMA");
    fields[13] = fieldSWDTYP =
    new CharacterField(message, HEADERSIZE + 126, 4, "SWDTYP");
    fields[14] = fieldSWDDID =
    new CharacterField(message, HEADERSIZE + 130, 10, "SWDDID");
    fields[15] = fieldSWDREF =
    new DecimalField(message, HEADERSIZE + 140, 13, 0, "SWDREF");
    fields[16] = fieldSWDEQV =
    new DecimalField(message, HEADERSIZE + 153, 17, 2, "SWDEQV");
    fields[17] = fieldSWDSTS =
    new CharacterField(message, HEADERSIZE + 170, 15, "SWDSTS");
    fields[18] = fieldSWDREC =
    new DecimalField(message, HEADERSIZE + 185, 8, 0, "SWDREC");
    fields[19] = fieldSWDOPE =
    new CharacterField(message, HEADERSIZE + 193, 1, "SWDOPE");

    synchronized (tlookup)
    {
      if (tlookup.isEmpty())
      {
        for (int i = 0; i < tnames.length; i++)
          tlookup.put(tnames[i], new Integer(i));
      }
    }

    taglookup = tlookup;
  }

  /**
  * Set field RWDUSR using a String value.
  */
  public void setRWDUSR(String newvalue)
  {
    fieldRWDUSR.setString(newvalue);
  }

  /**
  * Get value of field RWDUSR as a String.
  */
  public String getRWDUSR()
  {
    return fieldRWDUSR.getString();
  }

  /**
  * Set field RWDTYP using a String value.
  */
  public void setRWDTYP(String newvalue)
  {
    fieldRWDTYP.setString(newvalue);
  }

  /**
  * Get value of field RWDTYP as a String.
  */
  public String getRWDTYP()
  {
    return fieldRWDTYP.getString();
  }

  /**
  * Set field RWDFRC using a String value.
  */
  public void setRWDFRC(String newvalue)
  {
    fieldRWDFRC.setString(newvalue);
  }

  /**
  * Get value of field RWDFRC as a String.
  */
  public String getRWDFRC()
  {
    return fieldRWDFRC.getString();
  }

  /**
  * Set numeric field RWDFRC using a BigDecimal value.
  */
  public void setRWDFRC(BigDecimal newvalue)
  {
    fieldRWDFRC.setBigDecimal(newvalue);
  }

  /**
  * Return value of numeric field RWDFRC as a BigDecimal.
  */
  public BigDecimal getBigDecimalRWDFRC()
  {
    return fieldRWDFRC.getBigDecimal();
  }

  /**
  * Set field SWDCUN using a String value.
  */
  public void setSWDCUN(String newvalue)
  {
    fieldSWDCUN.setString(newvalue);
  }

  /**
  * Get value of field SWDCUN as a String.
  */
  public String getSWDCUN()
  {
    return fieldSWDCUN.getString();
  }

  /**
  * Set numeric field SWDCUN using a BigDecimal value.
  */
  public void setSWDCUN(BigDecimal newvalue)
  {
    fieldSWDCUN.setBigDecimal(newvalue);
  }

  /**
  * Return value of numeric field SWDCUN as a BigDecimal.
  */
  public BigDecimal getBigDecimalSWDCUN()
  {
    return fieldSWDCUN.getBigDecimal();
  }

  /**
  * Set field SWDDSC using a String value.
  */
  public void setSWDDSC(String newvalue)
  {
    fieldSWDDSC.setString(newvalue);
  }

  /**
  * Get value of field SWDDSC as a String.
  */
  public String getSWDDSC()
  {
    return fieldSWDDSC.getString();
  }

  /**
  * Set field SWDVA1 using a String value.
  */
  public void setSWDVA1(String newvalue)
  {
    fieldSWDVA1.setString(newvalue);
  }

  /**
  * Get value of field SWDVA1 as a String.
  */
  public String getSWDVA1()
  {
    return fieldSWDVA1.getString();
  }

  /**
  * Set numeric field SWDVA1 using a BigDecimal value.
  */
  public void setSWDVA1(BigDecimal newvalue)
  {
    fieldSWDVA1.setBigDecimal(newvalue);
  }

  /**
  * Return value of numeric field SWDVA1 as a BigDecimal.
  */
  public BigDecimal getBigDecimalSWDVA1()
  {
    return fieldSWDVA1.getBigDecimal();
  }

  /**
  * Set field SWDVA2 using a String value.
  */
  public void setSWDVA2(String newvalue)
  {
    fieldSWDVA2.setString(newvalue);
  }

  /**
  * Get value of field SWDVA2 as a String.
  */
  public String getSWDVA2()
  {
    return fieldSWDVA2.getString();
  }

  /**
  * Set numeric field SWDVA2 using a BigDecimal value.
  */
  public void setSWDVA2(BigDecimal newvalue)
  {
    fieldSWDVA2.setBigDecimal(newvalue);
  }

  /**
  * Return value of numeric field SWDVA2 as a BigDecimal.
  */
  public BigDecimal getBigDecimalSWDVA2()
  {
    return fieldSWDVA2.getBigDecimal();
  }

  /**
  * Set field SWDVA3 using a String value.
  */
  public void setSWDVA3(String newvalue)
  {
    fieldSWDVA3.setString(newvalue);
  }

  /**
  * Get value of field SWDVA3 as a String.
  */
  public String getSWDVA3()
  {
    return fieldSWDVA3.getString();
  }

  /**
  * Set numeric field SWDVA3 using a BigDecimal value.
  */
  public void setSWDVA3(BigDecimal newvalue)
  {
    fieldSWDVA3.setBigDecimal(newvalue);
  }

  /**
  * Return value of numeric field SWDVA3 as a BigDecimal.
  */
  public BigDecimal getBigDecimalSWDVA3()
  {
    return fieldSWDVA3.getBigDecimal();
  }

  /**
  * Set field SWDCCY using a String value.
  */
  public void setSWDCCY(String newvalue)
  {
    fieldSWDCCY.setString(newvalue);
  }

  /**
  * Get value of field SWDCCY as a String.
  */
  public String getSWDCCY()
  {
    return fieldSWDCCY.getString();
  }

  /**
  * Set field SWDTYL using a String value.
  */
  public void setSWDTYL(String newvalue)
  {
    fieldSWDTYL.setString(newvalue);
  }

  /**
  * Get value of field SWDTYL as a String.
  */
  public String getSWDTYL()
  {
    return fieldSWDTYL.getString();
  }

  /**
  * Set field SWDAMN using a String value.
  */
  public void setSWDAMN(String newvalue)
  {
    fieldSWDAMN.setString(newvalue);
  }

  /**
  * Get value of field SWDAMN as a String.
  */
  public String getSWDAMN()
  {
    return fieldSWDAMN.getString();
  }

  /**
  * Set numeric field SWDAMN using a BigDecimal value.
  */
  public void setSWDAMN(BigDecimal newvalue)
  {
    fieldSWDAMN.setBigDecimal(newvalue);
  }

  /**
  * Return value of numeric field SWDAMN as a BigDecimal.
  */
  public BigDecimal getBigDecimalSWDAMN()
  {
    return fieldSWDAMN.getBigDecimal();
  }

  /**
  * Set field SWDAMU using a String value.
  */
  public void setSWDAMU(String newvalue)
  {
    fieldSWDAMU.setString(newvalue);
  }

  /**
  * Get value of field SWDAMU as a String.
  */
  public String getSWDAMU()
  {
    return fieldSWDAMU.getString();
  }

  /**
  * Set numeric field SWDAMU using a BigDecimal value.
  */
  public void setSWDAMU(BigDecimal newvalue)
  {
    fieldSWDAMU.setBigDecimal(newvalue);
  }

  /**
  * Return value of numeric field SWDAMU as a BigDecimal.
  */
  public BigDecimal getBigDecimalSWDAMU()
  {
    return fieldSWDAMU.getBigDecimal();
  }

  /**
  * Set field SWDAMA using a String value.
  */
  public void setSWDAMA(String newvalue)
  {
    fieldSWDAMA.setString(newvalue);
  }

  /**
  * Get value of field SWDAMA as a String.
  */
  public String getSWDAMA()
  {
    return fieldSWDAMA.getString();
  }

  /**
  * Set numeric field SWDAMA using a BigDecimal value.
  */
  public void setSWDAMA(BigDecimal newvalue)
  {
    fieldSWDAMA.setBigDecimal(newvalue);
  }

  /**
  * Return value of numeric field SWDAMA as a BigDecimal.
  */
  public BigDecimal getBigDecimalSWDAMA()
  {
    return fieldSWDAMA.getBigDecimal();
  }

  /**
  * Set field SWDTYP using a String value.
  */
  public void setSWDTYP(String newvalue)
  {
    fieldSWDTYP.setString(newvalue);
  }

  /**
  * Get value of field SWDTYP as a String.
  */
  public String getSWDTYP()
  {
    return fieldSWDTYP.getString();
  }

  /**
  * Set field SWDDID using a String value.
  */
  public void setSWDDID(String newvalue)
  {
    fieldSWDDID.setString(newvalue);
  }

  /**
  * Get value of field SWDDID as a String.
  */
  public String getSWDDID()
  {
    return fieldSWDDID.getString();
  }

  /**
  * Set field SWDREF using a String value.
  */
  public void setSWDREF(String newvalue)
  {
    fieldSWDREF.setString(newvalue);
  }

  /**
  * Get value of field SWDREF as a String.
  */
  public String getSWDREF()
  {
    return fieldSWDREF.getString();
  }

  /**
  * Set numeric field SWDREF using a BigDecimal value.
  */
  public void setSWDREF(BigDecimal newvalue)
  {
    fieldSWDREF.setBigDecimal(newvalue);
  }

  /**
  * Return value of numeric field SWDREF as a BigDecimal.
  */
  public BigDecimal getBigDecimalSWDREF()
  {
    return fieldSWDREF.getBigDecimal();
  }

  /**
  * Set field SWDEQV using a String value.
  */
  public void setSWDEQV(String newvalue)
  {
    fieldSWDEQV.setString(newvalue);
  }

  /**
  * Get value of field SWDEQV as a String.
  */
  public String getSWDEQV()
  {
    return fieldSWDEQV.getString();
  }

  /**
  * Set numeric field SWDEQV using a BigDecimal value.
  */
  public void setSWDEQV(BigDecimal newvalue)
  {
    fieldSWDEQV.setBigDecimal(newvalue);
  }

  /**
  * Return value of numeric field SWDEQV as a BigDecimal.
  */
  public BigDecimal getBigDecimalSWDEQV()
  {
    return fieldSWDEQV.getBigDecimal();
  }

  /**
  * Set field SWDSTS using a String value.
  */
  public void setSWDSTS(String newvalue)
  {
    fieldSWDSTS.setString(newvalue);
  }

  /**
  * Get value of field SWDSTS as a String.
  */
  public String getSWDSTS()
  {
    return fieldSWDSTS.getString();
  }

  /**
  * Set field SWDREC using a String value.
  */
  public void setSWDREC(String newvalue)
  {
    fieldSWDREC.setString(newvalue);
  }

  /**
  * Get value of field SWDREC as a String.
  */
  public String getSWDREC()
  {
    return fieldSWDREC.getString();
  }

  /**
  * Set numeric field SWDREC using a BigDecimal value.
  */
  public void setSWDREC(BigDecimal newvalue)
  {
    fieldSWDREC.setBigDecimal(newvalue);
  }

  /**
  * Return value of numeric field SWDREC as a BigDecimal.
  */
  public BigDecimal getBigDecimalSWDREC()
  {
    return fieldSWDREC.getBigDecimal();
  }

  /**
  * Set field SWDOPE using a String value.
  */
  public void setSWDOPE(String newvalue)
  {
    fieldSWDOPE.setString(newvalue);
  }

  /**
  * Get value of field SWDOPE as a String.
  */
  public String getSWDOPE()
  {
    return fieldSWDOPE.getString();
  }

}
