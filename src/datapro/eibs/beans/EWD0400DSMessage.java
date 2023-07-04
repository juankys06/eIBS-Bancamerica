package datapro.eibs.beans;

import datapro.eibs.sockets.*;
import java.io.*;
import java.math.*;

import java.util.*;

/**
* Class generated by AS/400 CRTCLASS command from EWD0400DS physical file definition.
* 
* File level identifier is 1040622190342.
* Record format level identifier is 53A68A8A2A9A2.
*/

public class EWD0400DSMessage extends MessageRecord
{
  final static String fldnames[] = {
                                     "SELBNK",
                                     "SELBRN",
                                     "EWDBNK",
                                     "EWDBRN",
                                     "EWDASN",
                                     "EWDMAR",
                                     "EWDMOD",
                                     "EWDCLS",
                                     "EWDLOC",
                                     "EWDEST",
                                     "EWDREC",
                                     "EWDOPE"
                                   };
  final static String tnames[] = {
                                   "SELBNK",
                                   "SELBRN",
                                   "EWDBNK",
                                   "EWDBRN",
                                   "EWDASN",
                                   "EWDMAR",
                                   "EWDMOD",
                                   "EWDCLS",
                                   "EWDLOC",
                                   "EWDEST",
                                   "EWDREC",
                                   "EWDOPE"
                                 };
  final static String fid = "1040622190342";
  final static String rid = "53A68A8A2A9A2";
  final static String fmtname = "EWD0400DS";
  final int FIELDCOUNT = 12;
  private static Hashtable tlookup = new Hashtable();
  private CharacterField fieldSELBNK = null;
  private DecimalField fieldSELBRN = null;
  private CharacterField fieldEWDBNK = null;
  private DecimalField fieldEWDBRN = null;
  private DecimalField fieldEWDASN = null;
  private CharacterField fieldEWDMAR = null;
  private CharacterField fieldEWDMOD = null;
  private DecimalField fieldEWDCLS = null;
  private CharacterField fieldEWDLOC = null;
  private CharacterField fieldEWDEST = null;
  private DecimalField fieldEWDREC = null;
  private CharacterField fieldEWDOPE = null;

  /**
  * Constructor for EWD0400DSMessage.
  */
  public EWD0400DSMessage()
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
    recordsize = 78;
    fileid = fid;
    recordid = rid;
    message = new byte[getByteLength()];
    formatname = fmtname;
    fieldnames = fldnames;
    tagnames = tnames;
    fields = new MessageField[FIELDCOUNT];

    fields[0] = fieldSELBNK =
    new CharacterField(message, HEADERSIZE + 0, 2, "SELBNK");
    fields[1] = fieldSELBRN =
    new DecimalField(message, HEADERSIZE + 2, 4, 0, "SELBRN");
    fields[2] = fieldEWDBNK =
    new CharacterField(message, HEADERSIZE + 6, 2, "EWDBNK");
    fields[3] = fieldEWDBRN =
    new DecimalField(message, HEADERSIZE + 8, 4, 0, "EWDBRN");
    fields[4] = fieldEWDASN =
    new DecimalField(message, HEADERSIZE + 12, 13, 0, "EWDASN");
    fields[5] = fieldEWDMAR =
    new CharacterField(message, HEADERSIZE + 25, 20, "EWDMAR");
    fields[6] = fieldEWDMOD =
    new CharacterField(message, HEADERSIZE + 45, 15, "EWDMOD");
    fields[7] = fieldEWDCLS =
    new DecimalField(message, HEADERSIZE + 60, 5, 0, "EWDCLS");
    fields[8] = fieldEWDLOC =
    new CharacterField(message, HEADERSIZE + 65, 3, "EWDLOC");
    fields[9] = fieldEWDEST =
    new CharacterField(message, HEADERSIZE + 68, 1, "EWDEST");
    fields[10] = fieldEWDREC =
    new DecimalField(message, HEADERSIZE + 69, 8, 0, "EWDREC");
    fields[11] = fieldEWDOPE =
    new CharacterField(message, HEADERSIZE + 77, 1, "EWDOPE");

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
  * Set field SELBNK using a String value.
  */
  public void setSELBNK(String newvalue)
  {
    fieldSELBNK.setString(newvalue);
  }

  /**
  * Get value of field SELBNK as a String.
  */
  public String getSELBNK()
  {
    return fieldSELBNK.getString();
  }

  /**
  * Set field SELBRN using a String value.
  */
  public void setSELBRN(String newvalue)
  {
    fieldSELBRN.setString(newvalue);
  }

  /**
  * Get value of field SELBRN as a String.
  */
  public String getSELBRN()
  {
    return fieldSELBRN.getString();
  }

  /**
  * Set numeric field SELBRN using a BigDecimal value.
  */
  public void setSELBRN(BigDecimal newvalue)
  {
    fieldSELBRN.setBigDecimal(newvalue);
  }

  /**
  * Return value of numeric field SELBRN as a BigDecimal.
  */
  public BigDecimal getBigDecimalSELBRN()
  {
    return fieldSELBRN.getBigDecimal();
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
  * Set field EWDASN using a String value.
  */
  public void setEWDASN(String newvalue)
  {
    fieldEWDASN.setString(newvalue);
  }

  /**
  * Get value of field EWDASN as a String.
  */
  public String getEWDASN()
  {
    return fieldEWDASN.getString();
  }

  /**
  * Set numeric field EWDASN using a BigDecimal value.
  */
  public void setEWDASN(BigDecimal newvalue)
  {
    fieldEWDASN.setBigDecimal(newvalue);
  }

  /**
  * Return value of numeric field EWDASN as a BigDecimal.
  */
  public BigDecimal getBigDecimalEWDASN()
  {
    return fieldEWDASN.getBigDecimal();
  }

  /**
  * Set field EWDMAR using a String value.
  */
  public void setEWDMAR(String newvalue)
  {
    fieldEWDMAR.setString(newvalue);
  }

  /**
  * Get value of field EWDMAR as a String.
  */
  public String getEWDMAR()
  {
    return fieldEWDMAR.getString();
  }

  /**
  * Set field EWDMOD using a String value.
  */
  public void setEWDMOD(String newvalue)
  {
    fieldEWDMOD.setString(newvalue);
  }

  /**
  * Get value of field EWDMOD as a String.
  */
  public String getEWDMOD()
  {
    return fieldEWDMOD.getString();
  }

  /**
  * Set field EWDCLS using a String value.
  */
  public void setEWDCLS(String newvalue)
  {
    fieldEWDCLS.setString(newvalue);
  }

  /**
  * Get value of field EWDCLS as a String.
  */
  public String getEWDCLS()
  {
    return fieldEWDCLS.getString();
  }

  /**
  * Set numeric field EWDCLS using a BigDecimal value.
  */
  public void setEWDCLS(BigDecimal newvalue)
  {
    fieldEWDCLS.setBigDecimal(newvalue);
  }

  /**
  * Return value of numeric field EWDCLS as a BigDecimal.
  */
  public BigDecimal getBigDecimalEWDCLS()
  {
    return fieldEWDCLS.getBigDecimal();
  }

  /**
  * Set field EWDLOC using a String value.
  */
  public void setEWDLOC(String newvalue)
  {
    fieldEWDLOC.setString(newvalue);
  }

  /**
  * Get value of field EWDLOC as a String.
  */
  public String getEWDLOC()
  {
    return fieldEWDLOC.getString();
  }

  /**
  * Set field EWDEST using a String value.
  */
  public void setEWDEST(String newvalue)
  {
    fieldEWDEST.setString(newvalue);
  }

  /**
  * Get value of field EWDEST as a String.
  */
  public String getEWDEST()
  {
    return fieldEWDEST.getString();
  }

  /**
  * Set field EWDREC using a String value.
  */
  public void setEWDREC(String newvalue)
  {
    fieldEWDREC.setString(newvalue);
  }

  /**
  * Get value of field EWDREC as a String.
  */
  public String getEWDREC()
  {
    return fieldEWDREC.getString();
  }

  /**
  * Set numeric field EWDREC using a BigDecimal value.
  */
  public void setEWDREC(BigDecimal newvalue)
  {
    fieldEWDREC.setBigDecimal(newvalue);
  }

  /**
  * Return value of numeric field EWDREC as a BigDecimal.
  */
  public BigDecimal getBigDecimalEWDREC()
  {
    return fieldEWDREC.getBigDecimal();
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