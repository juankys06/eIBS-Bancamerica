package datapro.eibs.beans;

import datapro.eibs.sockets.*;
import java.io.*;
import java.math.*;

import java.util.*;

/**
* Class generated by AS/400 CRTCLASS command from EWD0045DS physical file definition.
* 
* File level identifier is 1030121192022.
* Record format level identifier is 45B8F8C3EE98C.
*/

public class EWD0045DSMessage extends MessageRecord
{
  final static String fldnames[] = {
                                     "EWDBNK",
                                     "EWDTBL",
                                     "EWDCCY",
                                     "EWDDT1",
                                     "EWDDT2",
                                     "EWDDT3",
                                     "EWDRT1",
                                     "EWDRT2",
                                     "EWDRT3",
                                     "EWDRT4",
                                     "EWDRT5",
                                     "EWDAM1",
                                     "EWDAM2",
                                     "EWDAM3",
                                     "EWDAM4",
                                     "EWDAM5",
                                     "EWDOPE"
                                   };
  final static String tnames[] = {
                                   "EWDBNK",
                                   "EWDTBL",
                                   "EWDCCY",
                                   "EWDDT1",
                                   "EWDDT2",
                                   "EWDDT3",
                                   "EWDRT1",
                                   "EWDRT2",
                                   "EWDRT3",
                                   "EWDRT4",
                                   "EWDRT5",
                                   "EWDAM1",
                                   "EWDAM2",
                                   "EWDAM3",
                                   "EWDAM4",
                                   "EWDAM5",
                                   "EWDOPE"
                                 };
  final static String fid = "1030121192022";
  final static String rid = "45B8F8C3EE98C";
  final static String fmtname = "EWD0045DS";
  final int FIELDCOUNT = 17;
  private static Hashtable tlookup = new Hashtable();
  private CharacterField fieldEWDBNK = null;
  private CharacterField fieldEWDTBL = null;
  private CharacterField fieldEWDCCY = null;
  private DecimalField fieldEWDDT1 = null;
  private DecimalField fieldEWDDT2 = null;
  private DecimalField fieldEWDDT3 = null;
  private DecimalField fieldEWDRT1 = null;
  private DecimalField fieldEWDRT2 = null;
  private DecimalField fieldEWDRT3 = null;
  private DecimalField fieldEWDRT4 = null;
  private DecimalField fieldEWDRT5 = null;
  private DecimalField fieldEWDAM1 = null;
  private DecimalField fieldEWDAM2 = null;
  private DecimalField fieldEWDAM3 = null;
  private DecimalField fieldEWDAM4 = null;
  private DecimalField fieldEWDAM5 = null;
  private CharacterField fieldEWDOPE = null;

  /**
  * Constructor for EWD0045DSMessage.
  */
  public EWD0045DSMessage()
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
    recordsize = 156;
    fileid = fid;
    recordid = rid;
    message = new byte[getByteLength()];
    formatname = fmtname;
    fieldnames = fldnames;
    tagnames = tnames;
    fields = new MessageField[FIELDCOUNT];

    fields[0] = fieldEWDBNK =
    new CharacterField(message, HEADERSIZE + 0, 2, "EWDBNK");
    fields[1] = fieldEWDTBL =
    new CharacterField(message, HEADERSIZE + 2, 1, "EWDTBL");
    fields[2] = fieldEWDCCY =
    new CharacterField(message, HEADERSIZE + 3, 3, "EWDCCY");
    fields[3] = fieldEWDDT1 =
    new DecimalField(message, HEADERSIZE + 6, 3, 0, "EWDDT1");
    fields[4] = fieldEWDDT2 =
    new DecimalField(message, HEADERSIZE + 9, 3, 0, "EWDDT2");
    fields[5] = fieldEWDDT3 =
    new DecimalField(message, HEADERSIZE + 12, 3, 0, "EWDDT3");
    fields[6] = fieldEWDRT1 =
    new DecimalField(message, HEADERSIZE + 15, 11, 6, "EWDRT1");
    fields[7] = fieldEWDRT2 =
    new DecimalField(message, HEADERSIZE + 26, 11, 6, "EWDRT2");
    fields[8] = fieldEWDRT3 =
    new DecimalField(message, HEADERSIZE + 37, 11, 6, "EWDRT3");
    fields[9] = fieldEWDRT4 =
    new DecimalField(message, HEADERSIZE + 48, 11, 6, "EWDRT4");
    fields[10] = fieldEWDRT5 =
    new DecimalField(message, HEADERSIZE + 59, 11, 6, "EWDRT5");
    fields[11] = fieldEWDAM1 =
    new DecimalField(message, HEADERSIZE + 70, 17, 2, "EWDAM1");
    fields[12] = fieldEWDAM2 =
    new DecimalField(message, HEADERSIZE + 87, 17, 2, "EWDAM2");
    fields[13] = fieldEWDAM3 =
    new DecimalField(message, HEADERSIZE + 104, 17, 2, "EWDAM3");
    fields[14] = fieldEWDAM4 =
    new DecimalField(message, HEADERSIZE + 121, 17, 2, "EWDAM4");
    fields[15] = fieldEWDAM5 =
    new DecimalField(message, HEADERSIZE + 138, 17, 2, "EWDAM5");
    fields[16] = fieldEWDOPE =
    new CharacterField(message, HEADERSIZE + 155, 1, "EWDOPE");

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
  * Set field EWDTBL using a String value.
  */
  public void setEWDTBL(String newvalue)
  {
    fieldEWDTBL.setString(newvalue);
  }

  /**
  * Get value of field EWDTBL as a String.
  */
  public String getEWDTBL()
  {
    return fieldEWDTBL.getString();
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
  * Set field EWDRT1 using a String value.
  */
  public void setEWDRT1(String newvalue)
  {
    fieldEWDRT1.setString(newvalue);
  }

  /**
  * Get value of field EWDRT1 as a String.
  */
  public String getEWDRT1()
  {
    return fieldEWDRT1.getString();
  }

  /**
  * Set numeric field EWDRT1 using a BigDecimal value.
  */
  public void setEWDRT1(BigDecimal newvalue)
  {
    fieldEWDRT1.setBigDecimal(newvalue);
  }

  /**
  * Return value of numeric field EWDRT1 as a BigDecimal.
  */
  public BigDecimal getBigDecimalEWDRT1()
  {
    return fieldEWDRT1.getBigDecimal();
  }

  /**
  * Set field EWDRT2 using a String value.
  */
  public void setEWDRT2(String newvalue)
  {
    fieldEWDRT2.setString(newvalue);
  }

  /**
  * Get value of field EWDRT2 as a String.
  */
  public String getEWDRT2()
  {
    return fieldEWDRT2.getString();
  }

  /**
  * Set numeric field EWDRT2 using a BigDecimal value.
  */
  public void setEWDRT2(BigDecimal newvalue)
  {
    fieldEWDRT2.setBigDecimal(newvalue);
  }

  /**
  * Return value of numeric field EWDRT2 as a BigDecimal.
  */
  public BigDecimal getBigDecimalEWDRT2()
  {
    return fieldEWDRT2.getBigDecimal();
  }

  /**
  * Set field EWDRT3 using a String value.
  */
  public void setEWDRT3(String newvalue)
  {
    fieldEWDRT3.setString(newvalue);
  }

  /**
  * Get value of field EWDRT3 as a String.
  */
  public String getEWDRT3()
  {
    return fieldEWDRT3.getString();
  }

  /**
  * Set numeric field EWDRT3 using a BigDecimal value.
  */
  public void setEWDRT3(BigDecimal newvalue)
  {
    fieldEWDRT3.setBigDecimal(newvalue);
  }

  /**
  * Return value of numeric field EWDRT3 as a BigDecimal.
  */
  public BigDecimal getBigDecimalEWDRT3()
  {
    return fieldEWDRT3.getBigDecimal();
  }

  /**
  * Set field EWDRT4 using a String value.
  */
  public void setEWDRT4(String newvalue)
  {
    fieldEWDRT4.setString(newvalue);
  }

  /**
  * Get value of field EWDRT4 as a String.
  */
  public String getEWDRT4()
  {
    return fieldEWDRT4.getString();
  }

  /**
  * Set numeric field EWDRT4 using a BigDecimal value.
  */
  public void setEWDRT4(BigDecimal newvalue)
  {
    fieldEWDRT4.setBigDecimal(newvalue);
  }

  /**
  * Return value of numeric field EWDRT4 as a BigDecimal.
  */
  public BigDecimal getBigDecimalEWDRT4()
  {
    return fieldEWDRT4.getBigDecimal();
  }

  /**
  * Set field EWDRT5 using a String value.
  */
  public void setEWDRT5(String newvalue)
  {
    fieldEWDRT5.setString(newvalue);
  }

  /**
  * Get value of field EWDRT5 as a String.
  */
  public String getEWDRT5()
  {
    return fieldEWDRT5.getString();
  }

  /**
  * Set numeric field EWDRT5 using a BigDecimal value.
  */
  public void setEWDRT5(BigDecimal newvalue)
  {
    fieldEWDRT5.setBigDecimal(newvalue);
  }

  /**
  * Return value of numeric field EWDRT5 as a BigDecimal.
  */
  public BigDecimal getBigDecimalEWDRT5()
  {
    return fieldEWDRT5.getBigDecimal();
  }

  /**
  * Set field EWDAM1 using a String value.
  */
  public void setEWDAM1(String newvalue)
  {
    fieldEWDAM1.setString(newvalue);
  }

  /**
  * Get value of field EWDAM1 as a String.
  */
  public String getEWDAM1()
  {
    return fieldEWDAM1.getString();
  }

  /**
  * Set numeric field EWDAM1 using a BigDecimal value.
  */
  public void setEWDAM1(BigDecimal newvalue)
  {
    fieldEWDAM1.setBigDecimal(newvalue);
  }

  /**
  * Return value of numeric field EWDAM1 as a BigDecimal.
  */
  public BigDecimal getBigDecimalEWDAM1()
  {
    return fieldEWDAM1.getBigDecimal();
  }

  /**
  * Set field EWDAM2 using a String value.
  */
  public void setEWDAM2(String newvalue)
  {
    fieldEWDAM2.setString(newvalue);
  }

  /**
  * Get value of field EWDAM2 as a String.
  */
  public String getEWDAM2()
  {
    return fieldEWDAM2.getString();
  }

  /**
  * Set numeric field EWDAM2 using a BigDecimal value.
  */
  public void setEWDAM2(BigDecimal newvalue)
  {
    fieldEWDAM2.setBigDecimal(newvalue);
  }

  /**
  * Return value of numeric field EWDAM2 as a BigDecimal.
  */
  public BigDecimal getBigDecimalEWDAM2()
  {
    return fieldEWDAM2.getBigDecimal();
  }

  /**
  * Set field EWDAM3 using a String value.
  */
  public void setEWDAM3(String newvalue)
  {
    fieldEWDAM3.setString(newvalue);
  }

  /**
  * Get value of field EWDAM3 as a String.
  */
  public String getEWDAM3()
  {
    return fieldEWDAM3.getString();
  }

  /**
  * Set numeric field EWDAM3 using a BigDecimal value.
  */
  public void setEWDAM3(BigDecimal newvalue)
  {
    fieldEWDAM3.setBigDecimal(newvalue);
  }

  /**
  * Return value of numeric field EWDAM3 as a BigDecimal.
  */
  public BigDecimal getBigDecimalEWDAM3()
  {
    return fieldEWDAM3.getBigDecimal();
  }

  /**
  * Set field EWDAM4 using a String value.
  */
  public void setEWDAM4(String newvalue)
  {
    fieldEWDAM4.setString(newvalue);
  }

  /**
  * Get value of field EWDAM4 as a String.
  */
  public String getEWDAM4()
  {
    return fieldEWDAM4.getString();
  }

  /**
  * Set numeric field EWDAM4 using a BigDecimal value.
  */
  public void setEWDAM4(BigDecimal newvalue)
  {
    fieldEWDAM4.setBigDecimal(newvalue);
  }

  /**
  * Return value of numeric field EWDAM4 as a BigDecimal.
  */
  public BigDecimal getBigDecimalEWDAM4()
  {
    return fieldEWDAM4.getBigDecimal();
  }

  /**
  * Set field EWDAM5 using a String value.
  */
  public void setEWDAM5(String newvalue)
  {
    fieldEWDAM5.setString(newvalue);
  }

  /**
  * Get value of field EWDAM5 as a String.
  */
  public String getEWDAM5()
  {
    return fieldEWDAM5.getString();
  }

  /**
  * Set numeric field EWDAM5 using a BigDecimal value.
  */
  public void setEWDAM5(BigDecimal newvalue)
  {
    fieldEWDAM5.setBigDecimal(newvalue);
  }

  /**
  * Return value of numeric field EWDAM5 as a BigDecimal.
  */
  public BigDecimal getBigDecimalEWDAM5()
  {
    return fieldEWDAM5.getBigDecimal();
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
