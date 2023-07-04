package datapro.eibs.beans;

import datapro.eibs.sockets.*;
import java.io.*;
import java.math.*;

import java.util.*;

/**
* Class generated by AS/400 CRTCLASS command from EWD0701DS physical file definition.
* 
* File level identifier is 1080530191337.
* Record format level identifier is 4B7928AF2CD28.
*/

public class EWD0701DSMessage extends MessageRecord
{
  final static String fldnames[] = {
                                     "RWDUSR",
                                     "RWDTYP",
                                     "RWDACC",
                                     "RWDFRC",
                                     "EWDTYP",
                                     "EWDACC",
                                     "EWDDSC",
                                     "EWDF01",
                                     "EWDRCN",
                                     "EWDDTE",
                                     "EWDNA1",
                                     "EWDCCY",
                                     "EWDPRO",
                                     "EWDOFC",
                                     "EWDREC",
                                     "EWDOPE"
                                   };
  final static String tnames[] = {
                                   "RWDUSR",
                                   "RWDTYP",
                                   "RWDACC",
                                   "RWDFRC",
                                   "EWDTYP",
                                   "EWDACC",
                                   "EWDDSC",
                                   "EWDF01",
                                   "EWDRCN",
                                   "EWDDTE",
                                   "EWDNA1",
                                   "EWDCCY",
                                   "EWDPRO",
                                   "EWDOFC",
                                   "EWDREC",
                                   "EWDOPE"
                                 };
  final static String fid = "1080530191337";
  final static String rid = "4B7928AF2CD28";
  final static String fmtname = "EWD0701DS";
  final int FIELDCOUNT = 16;
  private static Hashtable tlookup = new Hashtable();
  private CharacterField fieldRWDUSR = null;
  private CharacterField fieldRWDTYP = null;
  private DecimalField fieldRWDACC = null;
  private DecimalField fieldRWDFRC = null;
  private CharacterField fieldEWDTYP = null;
  private DecimalField fieldEWDACC = null;
  private CharacterField fieldEWDDSC = null;
  private CharacterField fieldEWDF01 = null;
  private DecimalField fieldEWDRCN = null;
  private CharacterField fieldEWDDTE = null;
  private CharacterField fieldEWDNA1 = null;
  private CharacterField fieldEWDCCY = null;
  private CharacterField fieldEWDPRO = null;
  private CharacterField fieldEWDOFC = null;
  private DecimalField fieldEWDREC = null;
  private CharacterField fieldEWDOPE = null;

  /**
  * Constructor for EWD0701DSMessage.
  */
  public EWD0701DSMessage()
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
    recordsize = 251;
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
    fields[2] = fieldRWDACC =
    new DecimalField(message, HEADERSIZE + 11, 13, 0, "RWDACC");
    fields[3] = fieldRWDFRC =
    new DecimalField(message, HEADERSIZE + 24, 8, 0, "RWDFRC");
    fields[4] = fieldEWDTYP =
    new CharacterField(message, HEADERSIZE + 32, 1, "EWDTYP");
    fields[5] = fieldEWDACC =
    new DecimalField(message, HEADERSIZE + 33, 13, 0, "EWDACC");
    fields[6] = fieldEWDDSC =
    new CharacterField(message, HEADERSIZE + 46, 80, "EWDDSC");
    fields[7] = fieldEWDF01 =
    new CharacterField(message, HEADERSIZE + 126, 3, "EWDF01");
    fields[8] = fieldEWDRCN =
    new DecimalField(message, HEADERSIZE + 129, 10, 0, "EWDRCN");
    fields[9] = fieldEWDDTE =
    new CharacterField(message, HEADERSIZE + 139, 26, "EWDDTE");
    fields[10] = fieldEWDNA1 =
    new CharacterField(message, HEADERSIZE + 165, 35, "EWDNA1");
    fields[11] = fieldEWDCCY =
    new CharacterField(message, HEADERSIZE + 200, 3, "EWDCCY");
    fields[12] = fieldEWDPRO =
    new CharacterField(message, HEADERSIZE + 203, 4, "EWDPRO");
    fields[13] = fieldEWDOFC =
    new CharacterField(message, HEADERSIZE + 207, 35, "EWDOFC");
    fields[14] = fieldEWDREC =
    new DecimalField(message, HEADERSIZE + 242, 8, 0, "EWDREC");
    fields[15] = fieldEWDOPE =
    new CharacterField(message, HEADERSIZE + 250, 1, "EWDOPE");

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
  * Set field RWDACC using a String value.
  */
  public void setRWDACC(String newvalue)
  {
    fieldRWDACC.setString(newvalue);
  }

  /**
  * Get value of field RWDACC as a String.
  */
  public String getRWDACC()
  {
    return fieldRWDACC.getString();
  }

  /**
  * Set numeric field RWDACC using a BigDecimal value.
  */
  public void setRWDACC(BigDecimal newvalue)
  {
    fieldRWDACC.setBigDecimal(newvalue);
  }

  /**
  * Return value of numeric field RWDACC as a BigDecimal.
  */
  public BigDecimal getBigDecimalRWDACC()
  {
    return fieldRWDACC.getBigDecimal();
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
  * Set field EWDTYP using a String value.
  */
  public void setEWDTYP(String newvalue)
  {
    fieldEWDTYP.setString(newvalue);
  }

  /**
  * Get value of field EWDTYP as a String.
  */
  public String getEWDTYP()
  {
    return fieldEWDTYP.getString();
  }

  /**
  * Set field EWDACC using a String value.
  */
  public void setEWDACC(String newvalue)
  {
    fieldEWDACC.setString(newvalue);
  }

  /**
  * Get value of field EWDACC as a String.
  */
  public String getEWDACC()
  {
    return fieldEWDACC.getString();
  }

  /**
  * Set numeric field EWDACC using a BigDecimal value.
  */
  public void setEWDACC(BigDecimal newvalue)
  {
    fieldEWDACC.setBigDecimal(newvalue);
  }

  /**
  * Return value of numeric field EWDACC as a BigDecimal.
  */
  public BigDecimal getBigDecimalEWDACC()
  {
    return fieldEWDACC.getBigDecimal();
  }

  /**
  * Set field EWDDSC using a String value.
  */
  public void setEWDDSC(String newvalue)
  {
    fieldEWDDSC.setString(newvalue);
  }

  /**
  * Get value of field EWDDSC as a String.
  */
  public String getEWDDSC()
  {
    return fieldEWDDSC.getString();
  }

  /**
  * Set field EWDF01 using a String value.
  */
  public void setEWDF01(String newvalue)
  {
    fieldEWDF01.setString(newvalue);
  }

  /**
  * Get value of field EWDF01 as a String.
  */
  public String getEWDF01()
  {
    return fieldEWDF01.getString();
  }

  /**
  * Set field EWDRCN using a String value.
  */
  public void setEWDRCN(String newvalue)
  {
    fieldEWDRCN.setString(newvalue);
  }

  /**
  * Get value of field EWDRCN as a String.
  */
  public String getEWDRCN()
  {
    return fieldEWDRCN.getString();
  }

  /**
  * Set numeric field EWDRCN using a BigDecimal value.
  */
  public void setEWDRCN(BigDecimal newvalue)
  {
    fieldEWDRCN.setBigDecimal(newvalue);
  }

  /**
  * Return value of numeric field EWDRCN as a BigDecimal.
  */
  public BigDecimal getBigDecimalEWDRCN()
  {
    return fieldEWDRCN.getBigDecimal();
  }

  /**
  * Set field EWDDTE using a String value.
  */
  public void setEWDDTE(String newvalue)
  {
    fieldEWDDTE.setString(newvalue);
  }

  /**
  * Get value of field EWDDTE as a String.
  */
  public String getEWDDTE()
  {
    return fieldEWDDTE.getString();
  }

  /**
  * Set field EWDNA1 using a String value.
  */
  public void setEWDNA1(String newvalue)
  {
    fieldEWDNA1.setString(newvalue);
  }

  /**
  * Get value of field EWDNA1 as a String.
  */
  public String getEWDNA1()
  {
    return fieldEWDNA1.getString();
  }

  /**
  * Set field EWDCCY using a String value.
  */
  public void setEWDCCY(String newvalue)
  {
    fieldEWDCCY.setString(newvalue);
  }

  /**
  * Get value of field EWDCCY as a String.
  */
  public String getEWDCCY()
  {
    return fieldEWDCCY.getString();
  }

  /**
  * Set field EWDPRO using a String value.
  */
  public void setEWDPRO(String newvalue)
  {
    fieldEWDPRO.setString(newvalue);
  }

  /**
  * Get value of field EWDPRO as a String.
  */
  public String getEWDPRO()
  {
    return fieldEWDPRO.getString();
  }

  /**
  * Set field EWDOFC using a String value.
  */
  public void setEWDOFC(String newvalue)
  {
    fieldEWDOFC.setString(newvalue);
  }

  /**
  * Get value of field EWDOFC as a String.
  */
  public String getEWDOFC()
  {
    return fieldEWDOFC.getString();
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
