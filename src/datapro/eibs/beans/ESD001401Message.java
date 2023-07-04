package datapro.eibs.beans;

import datapro.eibs.sockets.*;
import java.io.*;
import java.math.*;

import java.util.*;

/**
* Class generated by AS/400 CRTCLASS command from ESD001401 physical file definition.
* 
* File level identifier is 1040629193537.
* Record format level identifier is 454C1D4460578.
*/

public class ESD001401Message extends MessageRecord
{
  final static String fldnames[] = {
                                     "H14USR",
                                     "H14PGM",
                                     "H14TIM",
                                     "H14SCR",
                                     "H14OPE",
                                     "H14MAS",
                                     "H14WK1",
                                     "H14WK2",
                                     "H14WK3",
                                     "E14ACC",
                                     "E14UC0",
                                     "E14UC1",
                                     "E14UC2",
                                     "E14UC3",
                                     "E14UC4",
                                     "E14UC5",
                                     "E14UC6",
                                     "E14UC7",
                                     "E14UC8",
                                     "E14UC9",
                                     "D14UC0",
                                     "D14UC1",
                                     "D14UC2",
                                     "D14UC3",
                                     "D14UC4",
                                     "D14UC5",
                                     "D14UC6",
                                     "D14UC7",
                                     "D14UC8",
                                     "D14UC9"
                                   };
  final static String tnames[] = {
                                   "H14USR",
                                   "H14PGM",
                                   "H14TIM",
                                   "H14SCR",
                                   "H14OPE",
                                   "H14MAS",
                                   "H14WK1",
                                   "H14WK2",
                                   "H14WK3",
                                   "E14ACC",
                                   "E14UC0",
                                   "E14UC1",
                                   "E14UC2",
                                   "E14UC3",
                                   "E14UC4",
                                   "E14UC5",
                                   "E14UC6",
                                   "E14UC7",
                                   "E14UC8",
                                   "E14UC9",
                                   "D14UC0",
                                   "D14UC1",
                                   "D14UC2",
                                   "D14UC3",
                                   "D14UC4",
                                   "D14UC5",
                                   "D14UC6",
                                   "D14UC7",
                                   "D14UC8",
                                   "D14UC9"
                                 };
  final static String fid = "1040629193537";
  final static String rid = "454C1D4460578";
  final static String fmtname = "ESD001401";
  final int FIELDCOUNT = 30;
  private static Hashtable tlookup = new Hashtable();
  private CharacterField fieldH14USR = null;
  private CharacterField fieldH14PGM = null;
  private CharacterField fieldH14TIM = null;
  private CharacterField fieldH14SCR = null;
  private CharacterField fieldH14OPE = null;
  private CharacterField fieldH14MAS = null;
  private CharacterField fieldH14WK1 = null;
  private CharacterField fieldH14WK2 = null;
  private CharacterField fieldH14WK3 = null;
  private DecimalField fieldE14ACC = null;
  private CharacterField fieldE14UC0 = null;
  private CharacterField fieldE14UC1 = null;
  private CharacterField fieldE14UC2 = null;
  private CharacterField fieldE14UC3 = null;
  private CharacterField fieldE14UC4 = null;
  private CharacterField fieldE14UC5 = null;
  private CharacterField fieldE14UC6 = null;
  private CharacterField fieldE14UC7 = null;
  private CharacterField fieldE14UC8 = null;
  private CharacterField fieldE14UC9 = null;
  private CharacterField fieldD14UC0 = null;
  private CharacterField fieldD14UC1 = null;
  private CharacterField fieldD14UC2 = null;
  private CharacterField fieldD14UC3 = null;
  private CharacterField fieldD14UC4 = null;
  private CharacterField fieldD14UC5 = null;
  private CharacterField fieldD14UC6 = null;
  private CharacterField fieldD14UC7 = null;
  private CharacterField fieldD14UC8 = null;
  private CharacterField fieldD14UC9 = null;

  /**
  * Constructor for ESD001401Message.
  */
  public ESD001401Message()
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
    recordsize = 445;
    fileid = fid;
    recordid = rid;
    message = new byte[getByteLength()];
    formatname = fmtname;
    fieldnames = fldnames;
    tagnames = tnames;
    fields = new MessageField[FIELDCOUNT];

    fields[0] = fieldH14USR =
    new CharacterField(message, HEADERSIZE + 0, 10, "H14USR");
    fields[1] = fieldH14PGM =
    new CharacterField(message, HEADERSIZE + 10, 10, "H14PGM");
    fields[2] = fieldH14TIM =
    new CharacterField(message, HEADERSIZE + 20, 12, "H14TIM");
    fields[3] = fieldH14SCR =
    new CharacterField(message, HEADERSIZE + 32, 2, "H14SCR");
    fields[4] = fieldH14OPE =
    new CharacterField(message, HEADERSIZE + 34, 4, "H14OPE");
    fields[5] = fieldH14MAS =
    new CharacterField(message, HEADERSIZE + 38, 1, "H14MAS");
    fields[6] = fieldH14WK1 =
    new CharacterField(message, HEADERSIZE + 39, 1, "H14WK1");
    fields[7] = fieldH14WK2 =
    new CharacterField(message, HEADERSIZE + 40, 1, "H14WK2");
    fields[8] = fieldH14WK3 =
    new CharacterField(message, HEADERSIZE + 41, 1, "H14WK3");
    fields[9] = fieldE14ACC =
    new DecimalField(message, HEADERSIZE + 42, 13, 0, "E14ACC");
    fields[10] = fieldE14UC0 =
    new CharacterField(message, HEADERSIZE + 55, 4, "E14UC0");
    fields[11] = fieldE14UC1 =
    new CharacterField(message, HEADERSIZE + 59, 4, "E14UC1");
    fields[12] = fieldE14UC2 =
    new CharacterField(message, HEADERSIZE + 63, 4, "E14UC2");
    fields[13] = fieldE14UC3 =
    new CharacterField(message, HEADERSIZE + 67, 4, "E14UC3");
    fields[14] = fieldE14UC4 =
    new CharacterField(message, HEADERSIZE + 71, 4, "E14UC4");
    fields[15] = fieldE14UC5 =
    new CharacterField(message, HEADERSIZE + 75, 4, "E14UC5");
    fields[16] = fieldE14UC6 =
    new CharacterField(message, HEADERSIZE + 79, 4, "E14UC6");
    fields[17] = fieldE14UC7 =
    new CharacterField(message, HEADERSIZE + 83, 4, "E14UC7");
    fields[18] = fieldE14UC8 =
    new CharacterField(message, HEADERSIZE + 87, 4, "E14UC8");
    fields[19] = fieldE14UC9 =
    new CharacterField(message, HEADERSIZE + 91, 4, "E14UC9");
    fields[20] = fieldD14UC0 =
    new CharacterField(message, HEADERSIZE + 95, 35, "D14UC0");
    fields[21] = fieldD14UC1 =
    new CharacterField(message, HEADERSIZE + 130, 35, "D14UC1");
    fields[22] = fieldD14UC2 =
    new CharacterField(message, HEADERSIZE + 165, 35, "D14UC2");
    fields[23] = fieldD14UC3 =
    new CharacterField(message, HEADERSIZE + 200, 35, "D14UC3");
    fields[24] = fieldD14UC4 =
    new CharacterField(message, HEADERSIZE + 235, 35, "D14UC4");
    fields[25] = fieldD14UC5 =
    new CharacterField(message, HEADERSIZE + 270, 35, "D14UC5");
    fields[26] = fieldD14UC6 =
    new CharacterField(message, HEADERSIZE + 305, 35, "D14UC6");
    fields[27] = fieldD14UC7 =
    new CharacterField(message, HEADERSIZE + 340, 35, "D14UC7");
    fields[28] = fieldD14UC8 =
    new CharacterField(message, HEADERSIZE + 375, 35, "D14UC8");
    fields[29] = fieldD14UC9 =
    new CharacterField(message, HEADERSIZE + 410, 35, "D14UC9");

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
  * Set field H14USR using a String value.
  */
  public void setH14USR(String newvalue)
  {
    fieldH14USR.setString(newvalue);
  }

  /**
  * Get value of field H14USR as a String.
  */
  public String getH14USR()
  {
    return fieldH14USR.getString();
  }

  /**
  * Set field H14PGM using a String value.
  */
  public void setH14PGM(String newvalue)
  {
    fieldH14PGM.setString(newvalue);
  }

  /**
  * Get value of field H14PGM as a String.
  */
  public String getH14PGM()
  {
    return fieldH14PGM.getString();
  }

  /**
  * Set field H14TIM using a String value.
  */
  public void setH14TIM(String newvalue)
  {
    fieldH14TIM.setString(newvalue);
  }

  /**
  * Get value of field H14TIM as a String.
  */
  public String getH14TIM()
  {
    return fieldH14TIM.getString();
  }

  /**
  * Set field H14SCR using a String value.
  */
  public void setH14SCR(String newvalue)
  {
    fieldH14SCR.setString(newvalue);
  }

  /**
  * Get value of field H14SCR as a String.
  */
  public String getH14SCR()
  {
    return fieldH14SCR.getString();
  }

  /**
  * Set field H14OPE using a String value.
  */
  public void setH14OPE(String newvalue)
  {
    fieldH14OPE.setString(newvalue);
  }

  /**
  * Get value of field H14OPE as a String.
  */
  public String getH14OPE()
  {
    return fieldH14OPE.getString();
  }

  /**
  * Set field H14MAS using a String value.
  */
  public void setH14MAS(String newvalue)
  {
    fieldH14MAS.setString(newvalue);
  }

  /**
  * Get value of field H14MAS as a String.
  */
  public String getH14MAS()
  {
    return fieldH14MAS.getString();
  }

  /**
  * Set field H14WK1 using a String value.
  */
  public void setH14WK1(String newvalue)
  {
    fieldH14WK1.setString(newvalue);
  }

  /**
  * Get value of field H14WK1 as a String.
  */
  public String getH14WK1()
  {
    return fieldH14WK1.getString();
  }

  /**
  * Set field H14WK2 using a String value.
  */
  public void setH14WK2(String newvalue)
  {
    fieldH14WK2.setString(newvalue);
  }

  /**
  * Get value of field H14WK2 as a String.
  */
  public String getH14WK2()
  {
    return fieldH14WK2.getString();
  }

  /**
  * Set field H14WK3 using a String value.
  */
  public void setH14WK3(String newvalue)
  {
    fieldH14WK3.setString(newvalue);
  }

  /**
  * Get value of field H14WK3 as a String.
  */
  public String getH14WK3()
  {
    return fieldH14WK3.getString();
  }

  /**
  * Set field E14ACC using a String value.
  */
  public void setE14ACC(String newvalue)
  {
    fieldE14ACC.setString(newvalue);
  }

  /**
  * Get value of field E14ACC as a String.
  */
  public String getE14ACC()
  {
    return fieldE14ACC.getString();
  }

  /**
  * Set numeric field E14ACC using a BigDecimal value.
  */
  public void setE14ACC(BigDecimal newvalue)
  {
    fieldE14ACC.setBigDecimal(newvalue);
  }

  /**
  * Return value of numeric field E14ACC as a BigDecimal.
  */
  public BigDecimal getBigDecimalE14ACC()
  {
    return fieldE14ACC.getBigDecimal();
  }

  /**
  * Set field E14UC0 using a String value.
  */
  public void setE14UC0(String newvalue)
  {
    fieldE14UC0.setString(newvalue);
  }

  /**
  * Get value of field E14UC0 as a String.
  */
  public String getE14UC0()
  {
    return fieldE14UC0.getString();
  }

  /**
  * Set field E14UC1 using a String value.
  */
  public void setE14UC1(String newvalue)
  {
    fieldE14UC1.setString(newvalue);
  }

  /**
  * Get value of field E14UC1 as a String.
  */
  public String getE14UC1()
  {
    return fieldE14UC1.getString();
  }

  /**
  * Set field E14UC2 using a String value.
  */
  public void setE14UC2(String newvalue)
  {
    fieldE14UC2.setString(newvalue);
  }

  /**
  * Get value of field E14UC2 as a String.
  */
  public String getE14UC2()
  {
    return fieldE14UC2.getString();
  }

  /**
  * Set field E14UC3 using a String value.
  */
  public void setE14UC3(String newvalue)
  {
    fieldE14UC3.setString(newvalue);
  }

  /**
  * Get value of field E14UC3 as a String.
  */
  public String getE14UC3()
  {
    return fieldE14UC3.getString();
  }

  /**
  * Set field E14UC4 using a String value.
  */
  public void setE14UC4(String newvalue)
  {
    fieldE14UC4.setString(newvalue);
  }

  /**
  * Get value of field E14UC4 as a String.
  */
  public String getE14UC4()
  {
    return fieldE14UC4.getString();
  }

  /**
  * Set field E14UC5 using a String value.
  */
  public void setE14UC5(String newvalue)
  {
    fieldE14UC5.setString(newvalue);
  }

  /**
  * Get value of field E14UC5 as a String.
  */
  public String getE14UC5()
  {
    return fieldE14UC5.getString();
  }

  /**
  * Set field E14UC6 using a String value.
  */
  public void setE14UC6(String newvalue)
  {
    fieldE14UC6.setString(newvalue);
  }

  /**
  * Get value of field E14UC6 as a String.
  */
  public String getE14UC6()
  {
    return fieldE14UC6.getString();
  }

  /**
  * Set field E14UC7 using a String value.
  */
  public void setE14UC7(String newvalue)
  {
    fieldE14UC7.setString(newvalue);
  }

  /**
  * Get value of field E14UC7 as a String.
  */
  public String getE14UC7()
  {
    return fieldE14UC7.getString();
  }

  /**
  * Set field E14UC8 using a String value.
  */
  public void setE14UC8(String newvalue)
  {
    fieldE14UC8.setString(newvalue);
  }

  /**
  * Get value of field E14UC8 as a String.
  */
  public String getE14UC8()
  {
    return fieldE14UC8.getString();
  }

  /**
  * Set field E14UC9 using a String value.
  */
  public void setE14UC9(String newvalue)
  {
    fieldE14UC9.setString(newvalue);
  }

  /**
  * Get value of field E14UC9 as a String.
  */
  public String getE14UC9()
  {
    return fieldE14UC9.getString();
  }

  /**
  * Set field D14UC0 using a String value.
  */
  public void setD14UC0(String newvalue)
  {
    fieldD14UC0.setString(newvalue);
  }

  /**
  * Get value of field D14UC0 as a String.
  */
  public String getD14UC0()
  {
    return fieldD14UC0.getString();
  }

  /**
  * Set field D14UC1 using a String value.
  */
  public void setD14UC1(String newvalue)
  {
    fieldD14UC1.setString(newvalue);
  }

  /**
  * Get value of field D14UC1 as a String.
  */
  public String getD14UC1()
  {
    return fieldD14UC1.getString();
  }

  /**
  * Set field D14UC2 using a String value.
  */
  public void setD14UC2(String newvalue)
  {
    fieldD14UC2.setString(newvalue);
  }

  /**
  * Get value of field D14UC2 as a String.
  */
  public String getD14UC2()
  {
    return fieldD14UC2.getString();
  }

  /**
  * Set field D14UC3 using a String value.
  */
  public void setD14UC3(String newvalue)
  {
    fieldD14UC3.setString(newvalue);
  }

  /**
  * Get value of field D14UC3 as a String.
  */
  public String getD14UC3()
  {
    return fieldD14UC3.getString();
  }

  /**
  * Set field D14UC4 using a String value.
  */
  public void setD14UC4(String newvalue)
  {
    fieldD14UC4.setString(newvalue);
  }

  /**
  * Get value of field D14UC4 as a String.
  */
  public String getD14UC4()
  {
    return fieldD14UC4.getString();
  }

  /**
  * Set field D14UC5 using a String value.
  */
  public void setD14UC5(String newvalue)
  {
    fieldD14UC5.setString(newvalue);
  }

  /**
  * Get value of field D14UC5 as a String.
  */
  public String getD14UC5()
  {
    return fieldD14UC5.getString();
  }

  /**
  * Set field D14UC6 using a String value.
  */
  public void setD14UC6(String newvalue)
  {
    fieldD14UC6.setString(newvalue);
  }

  /**
  * Get value of field D14UC6 as a String.
  */
  public String getD14UC6()
  {
    return fieldD14UC6.getString();
  }

  /**
  * Set field D14UC7 using a String value.
  */
  public void setD14UC7(String newvalue)
  {
    fieldD14UC7.setString(newvalue);
  }

  /**
  * Get value of field D14UC7 as a String.
  */
  public String getD14UC7()
  {
    return fieldD14UC7.getString();
  }

  /**
  * Set field D14UC8 using a String value.
  */
  public void setD14UC8(String newvalue)
  {
    fieldD14UC8.setString(newvalue);
  }

  /**
  * Get value of field D14UC8 as a String.
  */
  public String getD14UC8()
  {
    return fieldD14UC8.getString();
  }

  /**
  * Set field D14UC9 using a String value.
  */
  public void setD14UC9(String newvalue)
  {
    fieldD14UC9.setString(newvalue);
  }

  /**
  * Get value of field D14UC9 as a String.
  */
  public String getD14UC9()
  {
    return fieldD14UC9.getString();
  }

}
