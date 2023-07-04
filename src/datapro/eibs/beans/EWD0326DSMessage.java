package datapro.eibs.beans;

import datapro.eibs.sockets.*;
import java.io.*;
import java.math.*;

import java.util.*;

/**
* Class generated by AS/400 CRTCLASS command from EWD0326DS physical file definition.
* 
* File level identifier is 1060822183951.
* Record format level identifier is 54A4B8E418928.
*/

public class EWD0326DSMessage extends MessageRecord
{
  final static String fldnames[] = {
                                     "RWDUSR",
                                     "RWDTYP",
                                     "RWDFRC",
                                     "SWDACC",
                                     "SWDDID",
                                     "SWDREF",
                                     "SWDCCY",
                                     "SWDACD",
                                     "SWDTYP",
                                     "SWDSBT",
                                     "SWDPRO",
                                     "SWDCUN",
                                     "SWDAMN",
                                     "SWDRA1",
                                     "SWDDSC",
                                     "SWDREC",
                                     "SWDOPE"
                                   };
  final static String tnames[] = {
                                   "RWDUSR",
                                   "RWDTYP",
                                   "RWDFRC",
                                   "SWDACC",
                                   "SWDDID",
                                   "SWDREF",
                                   "SWDCCY",
                                   "SWDACD",
                                   "SWDTYP",
                                   "SWDSBT",
                                   "SWDPRO",
                                   "SWDCUN",
                                   "SWDAMN",
                                   "SWDRA1",
                                   "SWDDSC",
                                   "SWDREC",
                                   "SWDOPE"
                                 };
  final static String fid = "1060822183951";
  final static String rid = "54A4B8E418928";
  final static String fmtname = "EWD0326DS";
  final int FIELDCOUNT = 17;
  private static Hashtable tlookup = new Hashtable();
  private CharacterField fieldRWDUSR = null;
  private CharacterField fieldRWDTYP = null;
  private DecimalField fieldRWDFRC = null;
  private DecimalField fieldSWDACC = null;
  private CharacterField fieldSWDDID = null;
  private CharacterField fieldSWDREF = null;
  private CharacterField fieldSWDCCY = null;
  private CharacterField fieldSWDACD = null;
  private CharacterField fieldSWDTYP = null;
  private CharacterField fieldSWDSBT = null;
  private CharacterField fieldSWDPRO = null;
  private DecimalField fieldSWDCUN = null;
  private DecimalField fieldSWDAMN = null;
  private DecimalField fieldSWDRA1 = null;
  private CharacterField fieldSWDDSC = null;
  private DecimalField fieldSWDREC = null;
  private CharacterField fieldSWDOPE = null;

  /**
  * Constructor for EWD0326DSMessage.
  */
  public EWD0326DSMessage()
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
    recordsize = 150;
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
    fields[3] = fieldSWDACC =
    new DecimalField(message, HEADERSIZE + 19, 13, 0, "SWDACC");
    fields[4] = fieldSWDDID =
    new CharacterField(message, HEADERSIZE + 32, 10, "SWDDID");
    fields[5] = fieldSWDREF =
    new CharacterField(message, HEADERSIZE + 42, 12, "SWDREF");
    fields[6] = fieldSWDCCY =
    new CharacterField(message, HEADERSIZE + 54, 3, "SWDCCY");
    fields[7] = fieldSWDACD =
    new CharacterField(message, HEADERSIZE + 57, 2, "SWDACD");
    fields[8] = fieldSWDTYP =
    new CharacterField(message, HEADERSIZE + 59, 4, "SWDTYP");
    fields[9] = fieldSWDSBT =
    new CharacterField(message, HEADERSIZE + 63, 4, "SWDSBT");
    fields[10] = fieldSWDPRO =
    new CharacterField(message, HEADERSIZE + 67, 4, "SWDPRO");
    fields[11] = fieldSWDCUN =
    new DecimalField(message, HEADERSIZE + 71, 10, 0, "SWDCUN");
    fields[12] = fieldSWDAMN =
    new DecimalField(message, HEADERSIZE + 81, 17, 2, "SWDAMN");
    fields[13] = fieldSWDRA1 =
    new DecimalField(message, HEADERSIZE + 98, 13, 6, "SWDRA1");
    fields[14] = fieldSWDDSC =
    new CharacterField(message, HEADERSIZE + 111, 30, "SWDDSC");
    fields[15] = fieldSWDREC =
    new DecimalField(message, HEADERSIZE + 141, 8, 0, "SWDREC");
    fields[16] = fieldSWDOPE =
    new CharacterField(message, HEADERSIZE + 149, 1, "SWDOPE");

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
  * Set field SWDACC using a String value.
  */
  public void setSWDACC(String newvalue)
  {
    fieldSWDACC.setString(newvalue);
  }

  /**
  * Get value of field SWDACC as a String.
  */
  public String getSWDACC()
  {
    return fieldSWDACC.getString();
  }

  /**
  * Set numeric field SWDACC using a BigDecimal value.
  */
  public void setSWDACC(BigDecimal newvalue)
  {
    fieldSWDACC.setBigDecimal(newvalue);
  }

  /**
  * Return value of numeric field SWDACC as a BigDecimal.
  */
  public BigDecimal getBigDecimalSWDACC()
  {
    return fieldSWDACC.getBigDecimal();
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
  * Set field SWDACD using a String value.
  */
  public void setSWDACD(String newvalue)
  {
    fieldSWDACD.setString(newvalue);
  }

  /**
  * Get value of field SWDACD as a String.
  */
  public String getSWDACD()
  {
    return fieldSWDACD.getString();
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
  * Set field SWDSBT using a String value.
  */
  public void setSWDSBT(String newvalue)
  {
    fieldSWDSBT.setString(newvalue);
  }

  /**
  * Get value of field SWDSBT as a String.
  */
  public String getSWDSBT()
  {
    return fieldSWDSBT.getString();
  }

  /**
  * Set field SWDPRO using a String value.
  */
  public void setSWDPRO(String newvalue)
  {
    fieldSWDPRO.setString(newvalue);
  }

  /**
  * Get value of field SWDPRO as a String.
  */
  public String getSWDPRO()
  {
    return fieldSWDPRO.getString();
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
  * Set field SWDRA1 using a String value.
  */
  public void setSWDRA1(String newvalue)
  {
    fieldSWDRA1.setString(newvalue);
  }

  /**
  * Get value of field SWDRA1 as a String.
  */
  public String getSWDRA1()
  {
    return fieldSWDRA1.getString();
  }

  /**
  * Set numeric field SWDRA1 using a BigDecimal value.
  */
  public void setSWDRA1(BigDecimal newvalue)
  {
    fieldSWDRA1.setBigDecimal(newvalue);
  }

  /**
  * Return value of numeric field SWDRA1 as a BigDecimal.
  */
  public BigDecimal getBigDecimalSWDRA1()
  {
    return fieldSWDRA1.getBigDecimal();
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
