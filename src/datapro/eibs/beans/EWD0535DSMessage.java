package datapro.eibs.beans;

import datapro.eibs.sockets.*;
import java.io.*;
import java.math.*;

import java.util.*;

/**
* Class generated by AS/400 CRTCLASS command from EWD0535DS physical file definition.
* 
* File level identifier is 1060613140904.
* Record format level identifier is 57508FA26D8B7.
*/

public class EWD0535DSMessage extends MessageRecord
{
  final static String fldnames[] = {
                                     "EWDBNK",
                                     "EWDBRN",
                                     "EWDTIP",
                                     "EWDSUB",
                                     "EWDLOT",
                                     "EWDINI",
                                     "EWDFIN",
                                     "EWDBAL",
                                     "EWDCHK",
                                     "EWDOPE"
                                   };
  final static String tnames[] = {
                                   "EWDBNK",
                                   "EWDBRN",
                                   "EWDTIP",
                                   "EWDSUB",
                                   "EWDLOT",
                                   "EWDINI",
                                   "EWDFIN",
                                   "EWDBAL",
                                   "EWDCHK",
                                   "EWDOPE"
                                 };
  final static String fid = "1060613140904";
  final static String rid = "57508FA26D8B7";
  final static String fmtname = "EWD0535DS";
  final int FIELDCOUNT = 10;
  private static Hashtable tlookup = new Hashtable();
  private CharacterField fieldEWDBNK = null;
  private DecimalField fieldEWDBRN = null;
  private CharacterField fieldEWDTIP = null;
  private CharacterField fieldEWDSUB = null;
  private DecimalField fieldEWDLOT = null;
  private DecimalField fieldEWDINI = null;
  private DecimalField fieldEWDFIN = null;
  private DecimalField fieldEWDBAL = null;
  private DecimalField fieldEWDCHK = null;
  private CharacterField fieldEWDOPE = null;

  /**
  * Constructor for EWD0535DSMessage.
  */
  public EWD0535DSMessage()
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
    recordsize = 75;
    fileid = fid;
    recordid = rid;
    message = new byte[getByteLength()];
    formatname = fmtname;
    fieldnames = fldnames;
    tagnames = tnames;
    fields = new MessageField[FIELDCOUNT];

    fields[0] = fieldEWDBNK =
    new CharacterField(message, HEADERSIZE + 0, 2, "EWDBNK");
    fields[1] = fieldEWDBRN =
    new DecimalField(message, HEADERSIZE + 2, 4, 0, "EWDBRN");
    fields[2] = fieldEWDTIP =
    new CharacterField(message, HEADERSIZE + 6, 4, "EWDTIP");
    fields[3] = fieldEWDSUB =
    new CharacterField(message, HEADERSIZE + 10, 4, "EWDSUB");
    fields[4] = fieldEWDLOT =
    new DecimalField(message, HEADERSIZE + 14, 13, 0, "EWDLOT");
    fields[5] = fieldEWDINI =
    new DecimalField(message, HEADERSIZE + 27, 13, 0, "EWDINI");
    fields[6] = fieldEWDFIN =
    new DecimalField(message, HEADERSIZE + 40, 13, 0, "EWDFIN");
    fields[7] = fieldEWDBAL =
    new DecimalField(message, HEADERSIZE + 53, 8, 0, "EWDBAL");
    fields[8] = fieldEWDCHK =
    new DecimalField(message, HEADERSIZE + 61, 13, 0, "EWDCHK");
    fields[9] = fieldEWDOPE =
    new CharacterField(message, HEADERSIZE + 74, 1, "EWDOPE");

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
  * Set field EWDBNK using a String value.
  */
  public void setEWDBNK(String newvalue)
  {
    fieldEWDBNK.setString(newvalue);
  }

  /**
  * Get value of field EWDBNK as a String.
  */
  public String getEWDBNK()
  {
    return fieldEWDBNK.getString();
  }

  /**
  * Set field EWDBRN using a String value.
  */
  public void setEWDBRN(String newvalue)
  {
    fieldEWDBRN.setString(newvalue);
  }

  /**
  * Get value of field EWDBRN as a String.
  */
  public String getEWDBRN()
  {
    return fieldEWDBRN.getString();
  }

  /**
  * Set numeric field EWDBRN using a BigDecimal value.
  */
  public void setEWDBRN(BigDecimal newvalue)
  {
    fieldEWDBRN.setBigDecimal(newvalue);
  }

  /**
  * Return value of numeric field EWDBRN as a BigDecimal.
  */
  public BigDecimal getBigDecimalEWDBRN()
  {
    return fieldEWDBRN.getBigDecimal();
  }

  /**
  * Set field EWDTIP using a String value.
  */
  public void setEWDTIP(String newvalue)
  {
    fieldEWDTIP.setString(newvalue);
  }

  /**
  * Get value of field EWDTIP as a String.
  */
  public String getEWDTIP()
  {
    return fieldEWDTIP.getString();
  }

  /**
  * Set field EWDSUB using a String value.
  */
  public void setEWDSUB(String newvalue)
  {
    fieldEWDSUB.setString(newvalue);
  }

  /**
  * Get value of field EWDSUB as a String.
  */
  public String getEWDSUB()
  {
    return fieldEWDSUB.getString();
  }

  /**
  * Set field EWDLOT using a String value.
  */
  public void setEWDLOT(String newvalue)
  {
    fieldEWDLOT.setString(newvalue);
  }

  /**
  * Get value of field EWDLOT as a String.
  */
  public String getEWDLOT()
  {
    return fieldEWDLOT.getString();
  }

  /**
  * Set numeric field EWDLOT using a BigDecimal value.
  */
  public void setEWDLOT(BigDecimal newvalue)
  {
    fieldEWDLOT.setBigDecimal(newvalue);
  }

  /**
  * Return value of numeric field EWDLOT as a BigDecimal.
  */
  public BigDecimal getBigDecimalEWDLOT()
  {
    return fieldEWDLOT.getBigDecimal();
  }

  /**
  * Set field EWDINI using a String value.
  */
  public void setEWDINI(String newvalue)
  {
    fieldEWDINI.setString(newvalue);
  }

  /**
  * Get value of field EWDINI as a String.
  */
  public String getEWDINI()
  {
    return fieldEWDINI.getString();
  }

  /**
  * Set numeric field EWDINI using a BigDecimal value.
  */
  public void setEWDINI(BigDecimal newvalue)
  {
    fieldEWDINI.setBigDecimal(newvalue);
  }

  /**
  * Return value of numeric field EWDINI as a BigDecimal.
  */
  public BigDecimal getBigDecimalEWDINI()
  {
    return fieldEWDINI.getBigDecimal();
  }

  /**
  * Set field EWDFIN using a String value.
  */
  public void setEWDFIN(String newvalue)
  {
    fieldEWDFIN.setString(newvalue);
  }

  /**
  * Get value of field EWDFIN as a String.
  */
  public String getEWDFIN()
  {
    return fieldEWDFIN.getString();
  }

  /**
  * Set numeric field EWDFIN using a BigDecimal value.
  */
  public void setEWDFIN(BigDecimal newvalue)
  {
    fieldEWDFIN.setBigDecimal(newvalue);
  }

  /**
  * Return value of numeric field EWDFIN as a BigDecimal.
  */
  public BigDecimal getBigDecimalEWDFIN()
  {
    return fieldEWDFIN.getBigDecimal();
  }

  /**
  * Set field EWDBAL using a String value.
  */
  public void setEWDBAL(String newvalue)
  {
    fieldEWDBAL.setString(newvalue);
  }

  /**
  * Get value of field EWDBAL as a String.
  */
  public String getEWDBAL()
  {
    return fieldEWDBAL.getString();
  }

  /**
  * Set numeric field EWDBAL using a BigDecimal value.
  */
  public void setEWDBAL(BigDecimal newvalue)
  {
    fieldEWDBAL.setBigDecimal(newvalue);
  }

  /**
  * Return value of numeric field EWDBAL as a BigDecimal.
  */
  public BigDecimal getBigDecimalEWDBAL()
  {
    return fieldEWDBAL.getBigDecimal();
  }

  /**
  * Set field EWDCHK using a String value.
  */
  public void setEWDCHK(String newvalue)
  {
    fieldEWDCHK.setString(newvalue);
  }

  /**
  * Get value of field EWDCHK as a String.
  */
  public String getEWDCHK()
  {
    return fieldEWDCHK.getString();
  }

  /**
  * Set numeric field EWDCHK using a BigDecimal value.
  */
  public void setEWDCHK(BigDecimal newvalue)
  {
    fieldEWDCHK.setBigDecimal(newvalue);
  }

  /**
  * Return value of numeric field EWDCHK as a BigDecimal.
  */
  public BigDecimal getBigDecimalEWDCHK()
  {
    return fieldEWDCHK.getBigDecimal();
  }

  /**
  * Set field EWDOPE using a String value.
  */
  public void setEWDOPE(String newvalue)
  {
    fieldEWDOPE.setString(newvalue);
  }

  /**
  * Get value of field EWDOPE as a String.
  */
  public String getEWDOPE()
  {
    return fieldEWDOPE.getString();
  }

}