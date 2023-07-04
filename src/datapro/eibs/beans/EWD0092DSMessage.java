package datapro.eibs.beans;

import datapro.eibs.sockets.*;
import java.io.*;
import java.math.*;

import java.util.*;

/**
* Class generated by AS/400 CRTCLASS command from EWD0092DS physical file definition.
* 
* File level identifier is 1030121192023.
* Record format level identifier is 34A3AD488EDA9.
*/

public class EWD0092DSMessage extends MessageRecord
{
  final static String fldnames[] = {
                                     "EWDACN",
                                     "EWDWTR",
                                     "EWDFMT",
                                     "EWDSCA",
                                     "EWDMFN",
                                     "EWDUSR",
                                     "EWDPRO",
                                     "EWDTIM",
                                     "EWDDT1",
                                     "EWDDT2",
                                     "EWDDT3",
                                     "EWDREC",
                                     "EWDOPE"
                                   };
  final static String tnames[] = {
                                   "EWDACN",
                                   "EWDWTR",
                                   "EWDFMT",
                                   "EWDSCA",
                                   "EWDMFN",
                                   "EWDUSR",
                                   "EWDPRO",
                                   "EWDTIM",
                                   "EWDDT1",
                                   "EWDDT2",
                                   "EWDDT3",
                                   "EWDREC",
                                   "EWDOPE"
                                 };
  final static String fid = "1030121192023";
  final static String rid = "34A3AD488EDA9";
  final static String fmtname = "EWD0092DS";
  final int FIELDCOUNT = 13;
  private static Hashtable tlookup = new Hashtable();
  private DecimalField fieldEWDACN = null;
  private CharacterField fieldEWDWTR = null;
  private DecimalField fieldEWDFMT = null;
  private CharacterField fieldEWDSCA = null;
  private CharacterField fieldEWDMFN = null;
  private CharacterField fieldEWDUSR = null;
  private CharacterField fieldEWDPRO = null;
  private DecimalField fieldEWDTIM = null;
  private DecimalField fieldEWDDT1 = null;
  private DecimalField fieldEWDDT2 = null;
  private DecimalField fieldEWDDT3 = null;
  private DecimalField fieldEWDREC = null;
  private CharacterField fieldEWDOPE = null;

  /**
  * Constructor for EWD0092DSMessage.
  */
  public EWD0092DSMessage()
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
    recordsize = 289;
    fileid = fid;
    recordid = rid;
    message = new byte[getByteLength()];
    formatname = fmtname;
    fieldnames = fldnames;
    tagnames = tnames;
    fields = new MessageField[FIELDCOUNT];

    fields[0] = fieldEWDACN =
    new DecimalField(message, HEADERSIZE + 0, 13, 0, "EWDACN");
    fields[1] = fieldEWDWTR =
    new CharacterField(message, HEADERSIZE + 13, 7, "EWDWTR");
    fields[2] = fieldEWDFMT =
    new DecimalField(message, HEADERSIZE + 20, 4, 0, "EWDFMT");
    fields[3] = fieldEWDSCA =
    new CharacterField(message, HEADERSIZE + 24, 100, "EWDSCA");
    fields[4] = fieldEWDMFN =
    new CharacterField(message, HEADERSIZE + 124, 100, "EWDMFN");
    fields[5] = fieldEWDUSR =
    new CharacterField(message, HEADERSIZE + 224, 10, "EWDUSR");
    fields[6] = fieldEWDPRO =
    new CharacterField(message, HEADERSIZE + 234, 30, "EWDPRO");
    fields[7] = fieldEWDTIM =
    new DecimalField(message, HEADERSIZE + 264, 7, 0, "EWDTIM");
    fields[8] = fieldEWDDT1 =
    new DecimalField(message, HEADERSIZE + 271, 3, 0, "EWDDT1");
    fields[9] = fieldEWDDT2 =
    new DecimalField(message, HEADERSIZE + 274, 3, 0, "EWDDT2");
    fields[10] = fieldEWDDT3 =
    new DecimalField(message, HEADERSIZE + 277, 3, 0, "EWDDT3");
    fields[11] = fieldEWDREC =
    new DecimalField(message, HEADERSIZE + 280, 8, 0, "EWDREC");
    fields[12] = fieldEWDOPE =
    new CharacterField(message, HEADERSIZE + 288, 1, "EWDOPE");

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
  * Set field EWDACN using a String value.
  */
  public void setEWDACN(String newvalue)
  {
    fieldEWDACN.setString(newvalue);
  }

  /**
  * Get value of field EWDACN as a String.
  */
  public String getEWDACN()
  {
    return fieldEWDACN.getString();
  }

  /**
  * Set numeric field EWDACN using a BigDecimal value.
  */
  public void setEWDACN(BigDecimal newvalue)
  {
    fieldEWDACN.setBigDecimal(newvalue);
  }

  /**
  * Return value of numeric field EWDACN as a BigDecimal.
  */
  public BigDecimal getBigDecimalEWDACN()
  {
    return fieldEWDACN.getBigDecimal();
  }

  /**
  * Set field EWDWTR using a String value.
  */
  public void setEWDWTR(String newvalue)
  {
    fieldEWDWTR.setString(newvalue);
  }

  /**
  * Get value of field EWDWTR as a String.
  */
  public String getEWDWTR()
  {
    return fieldEWDWTR.getString();
  }

  /**
  * Set field EWDFMT using a String value.
  */
  public void setEWDFMT(String newvalue)
  {
    fieldEWDFMT.setString(newvalue);
  }

  /**
  * Get value of field EWDFMT as a String.
  */
  public String getEWDFMT()
  {
    return fieldEWDFMT.getString();
  }

  /**
  * Set numeric field EWDFMT using a BigDecimal value.
  */
  public void setEWDFMT(BigDecimal newvalue)
  {
    fieldEWDFMT.setBigDecimal(newvalue);
  }

  /**
  * Return value of numeric field EWDFMT as a BigDecimal.
  */
  public BigDecimal getBigDecimalEWDFMT()
  {
    return fieldEWDFMT.getBigDecimal();
  }

  /**
  * Set field EWDSCA using a String value.
  */
  public void setEWDSCA(String newvalue)
  {
    fieldEWDSCA.setString(newvalue);
  }

  /**
  * Get value of field EWDSCA as a String.
  */
  public String getEWDSCA()
  {
    return fieldEWDSCA.getString();
  }

  /**
  * Set field EWDMFN using a String value.
  */
  public void setEWDMFN(String newvalue)
  {
    fieldEWDMFN.setString(newvalue);
  }

  /**
  * Get value of field EWDMFN as a String.
  */
  public String getEWDMFN()
  {
    return fieldEWDMFN.getString();
  }

  /**
  * Set field EWDUSR using a String value.
  */
  public void setEWDUSR(String newvalue)
  {
    fieldEWDUSR.setString(newvalue);
  }

  /**
  * Get value of field EWDUSR as a String.
  */
  public String getEWDUSR()
  {
    return fieldEWDUSR.getString();
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
  * Set field EWDTIM using a String value.
  */
  public void setEWDTIM(String newvalue)
  {
    fieldEWDTIM.setString(newvalue);
  }

  /**
  * Get value of field EWDTIM as a String.
  */
  public String getEWDTIM()
  {
    return fieldEWDTIM.getString();
  }

  /**
  * Set numeric field EWDTIM using a BigDecimal value.
  */
  public void setEWDTIM(BigDecimal newvalue)
  {
    fieldEWDTIM.setBigDecimal(newvalue);
  }

  /**
  * Return value of numeric field EWDTIM as a BigDecimal.
  */
  public BigDecimal getBigDecimalEWDTIM()
  {
    return fieldEWDTIM.getBigDecimal();
  }

  /**
  * Set field EWDDT1 using a String value.
  */
  public void setEWDDT1(String newvalue)
  {
    fieldEWDDT1.setString(newvalue);
  }

  /**
  * Get value of field EWDDT1 as a String.
  */
  public String getEWDDT1()
  {
    return fieldEWDDT1.getString();
  }

  /**
  * Set numeric field EWDDT1 using a BigDecimal value.
  */
  public void setEWDDT1(BigDecimal newvalue)
  {
    fieldEWDDT1.setBigDecimal(newvalue);
  }

  /**
  * Return value of numeric field EWDDT1 as a BigDecimal.
  */
  public BigDecimal getBigDecimalEWDDT1()
  {
    return fieldEWDDT1.getBigDecimal();
  }

  /**
  * Set field EWDDT2 using a String value.
  */
  public void setEWDDT2(String newvalue)
  {
    fieldEWDDT2.setString(newvalue);
  }

  /**
  * Get value of field EWDDT2 as a String.
  */
  public String getEWDDT2()
  {
    return fieldEWDDT2.getString();
  }

  /**
  * Set numeric field EWDDT2 using a BigDecimal value.
  */
  public void setEWDDT2(BigDecimal newvalue)
  {
    fieldEWDDT2.setBigDecimal(newvalue);
  }

  /**
  * Return value of numeric field EWDDT2 as a BigDecimal.
  */
  public BigDecimal getBigDecimalEWDDT2()
  {
    return fieldEWDDT2.getBigDecimal();
  }

  /**
  * Set field EWDDT3 using a String value.
  */
  public void setEWDDT3(String newvalue)
  {
    fieldEWDDT3.setString(newvalue);
  }

  /**
  * Get value of field EWDDT3 as a String.
  */
  public String getEWDDT3()
  {
    return fieldEWDDT3.getString();
  }

  /**
  * Set numeric field EWDDT3 using a BigDecimal value.
  */
  public void setEWDDT3(BigDecimal newvalue)
  {
    fieldEWDDT3.setBigDecimal(newvalue);
  }

  /**
  * Return value of numeric field EWDDT3 as a BigDecimal.
  */
  public BigDecimal getBigDecimalEWDDT3()
  {
    return fieldEWDDT3.getBigDecimal();
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