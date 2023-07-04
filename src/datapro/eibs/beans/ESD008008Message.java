package datapro.eibs.beans;

import datapro.eibs.sockets.*;
import java.io.*;
import java.math.*;

import java.util.*;

/**
* Class generated by AS/400 CRTCLASS command from ESD008008 physical file definition.
* 
* File level identifier is 1030121192001.
* Record format level identifier is 4BA5F3BE18A9B.
*/

public class ESD008008Message extends MessageRecord
{
  final static String fldnames[] = {
                                     "H08USR",
                                     "H08PGM",
                                     "H08TIM",
                                     "H08SCR",
                                     "H08OPE",
                                     "H08MAS",
                                     "H08WK1",
                                     "H08WK2",
                                     "H08WK3",
                                     "E08CUN",
                                     "E08A01",
                                     "E08A02",
                                     "E08A03",
                                     "E08A04",
                                     "E08A05",
                                     "E08A06",
                                     "E08A07",
                                     "E08A08",
                                     "E08A09",
                                     "E08A10",
                                     "E08A11",
                                     "E08L01",
                                     "E08L02",
                                     "E08L03",
                                     "E08L04",
                                     "E08L05",
                                     "E08L06",
                                     "E08L07",
                                     "E08L08",
                                     "E08L09",
                                     "E08L10",
                                     "E08L11"
                                   };
  final static String tnames[] = {
                                   "H08USR",
                                   "H08PGM",
                                   "H08TIM",
                                   "H08SCR",
                                   "H08OPE",
                                   "H08MAS",
                                   "H08WK1",
                                   "H08WK2",
                                   "H08WK3",
                                   "E08CUN",
                                   "E08A01",
                                   "E08A02",
                                   "E08A03",
                                   "E08A04",
                                   "E08A05",
                                   "E08A06",
                                   "E08A07",
                                   "E08A08",
                                   "E08A09",
                                   "E08A10",
                                   "E08A11",
                                   "E08L01",
                                   "E08L02",
                                   "E08L03",
                                   "E08L04",
                                   "E08L05",
                                   "E08L06",
                                   "E08L07",
                                   "E08L08",
                                   "E08L09",
                                   "E08L10",
                                   "E08L11"
                                 };
  final static String fid = "1030121192001";
  final static String rid = "4BA5F3BE18A9B";
  final static String fmtname = "ESD008008";
  final int FIELDCOUNT = 32;
  private static Hashtable tlookup = new Hashtable();
  private CharacterField fieldH08USR = null;
  private CharacterField fieldH08PGM = null;
  private CharacterField fieldH08TIM = null;
  private CharacterField fieldH08SCR = null;
  private CharacterField fieldH08OPE = null;
  private CharacterField fieldH08MAS = null;
  private CharacterField fieldH08WK1 = null;
  private CharacterField fieldH08WK2 = null;
  private CharacterField fieldH08WK3 = null;
  private DecimalField fieldE08CUN = null;
  private DecimalField fieldE08A01 = null;
  private DecimalField fieldE08A02 = null;
  private DecimalField fieldE08A03 = null;
  private DecimalField fieldE08A04 = null;
  private DecimalField fieldE08A05 = null;
  private DecimalField fieldE08A06 = null;
  private DecimalField fieldE08A07 = null;
  private DecimalField fieldE08A08 = null;
  private DecimalField fieldE08A09 = null;
  private DecimalField fieldE08A10 = null;
  private DecimalField fieldE08A11 = null;
  private DecimalField fieldE08L01 = null;
  private DecimalField fieldE08L02 = null;
  private DecimalField fieldE08L03 = null;
  private DecimalField fieldE08L04 = null;
  private DecimalField fieldE08L05 = null;
  private DecimalField fieldE08L06 = null;
  private DecimalField fieldE08L07 = null;
  private DecimalField fieldE08L08 = null;
  private DecimalField fieldE08L09 = null;
  private DecimalField fieldE08L10 = null;
  private DecimalField fieldE08L11 = null;

  /**
  * Constructor for ESD008008Message.
  */
  public ESD008008Message()
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
    recordsize = 426;
    fileid = fid;
    recordid = rid;
    message = new byte[getByteLength()];
    formatname = fmtname;
    fieldnames = fldnames;
    tagnames = tnames;
    fields = new MessageField[FIELDCOUNT];

    fields[0] = fieldH08USR =
    new CharacterField(message, HEADERSIZE + 0, 10, "H08USR");
    fields[1] = fieldH08PGM =
    new CharacterField(message, HEADERSIZE + 10, 10, "H08PGM");
    fields[2] = fieldH08TIM =
    new CharacterField(message, HEADERSIZE + 20, 12, "H08TIM");
    fields[3] = fieldH08SCR =
    new CharacterField(message, HEADERSIZE + 32, 2, "H08SCR");
    fields[4] = fieldH08OPE =
    new CharacterField(message, HEADERSIZE + 34, 4, "H08OPE");
    fields[5] = fieldH08MAS =
    new CharacterField(message, HEADERSIZE + 38, 1, "H08MAS");
    fields[6] = fieldH08WK1 =
    new CharacterField(message, HEADERSIZE + 39, 1, "H08WK1");
    fields[7] = fieldH08WK2 =
    new CharacterField(message, HEADERSIZE + 40, 1, "H08WK2");
    fields[8] = fieldH08WK3 =
    new CharacterField(message, HEADERSIZE + 41, 1, "H08WK3");
    fields[9] = fieldE08CUN =
    new DecimalField(message, HEADERSIZE + 42, 10, 0, "E08CUN");
    fields[10] = fieldE08A01 =
    new DecimalField(message, HEADERSIZE + 52, 17, 2, "E08A01");
    fields[11] = fieldE08A02 =
    new DecimalField(message, HEADERSIZE + 69, 17, 2, "E08A02");
    fields[12] = fieldE08A03 =
    new DecimalField(message, HEADERSIZE + 86, 17, 2, "E08A03");
    fields[13] = fieldE08A04 =
    new DecimalField(message, HEADERSIZE + 103, 17, 2, "E08A04");
    fields[14] = fieldE08A05 =
    new DecimalField(message, HEADERSIZE + 120, 17, 2, "E08A05");
    fields[15] = fieldE08A06 =
    new DecimalField(message, HEADERSIZE + 137, 17, 2, "E08A06");
    fields[16] = fieldE08A07 =
    new DecimalField(message, HEADERSIZE + 154, 17, 2, "E08A07");
    fields[17] = fieldE08A08 =
    new DecimalField(message, HEADERSIZE + 171, 17, 2, "E08A08");
    fields[18] = fieldE08A09 =
    new DecimalField(message, HEADERSIZE + 188, 17, 2, "E08A09");
    fields[19] = fieldE08A10 =
    new DecimalField(message, HEADERSIZE + 205, 17, 2, "E08A10");
    fields[20] = fieldE08A11 =
    new DecimalField(message, HEADERSIZE + 222, 17, 2, "E08A11");
    fields[21] = fieldE08L01 =
    new DecimalField(message, HEADERSIZE + 239, 17, 2, "E08L01");
    fields[22] = fieldE08L02 =
    new DecimalField(message, HEADERSIZE + 256, 17, 2, "E08L02");
    fields[23] = fieldE08L03 =
    new DecimalField(message, HEADERSIZE + 273, 17, 2, "E08L03");
    fields[24] = fieldE08L04 =
    new DecimalField(message, HEADERSIZE + 290, 17, 2, "E08L04");
    fields[25] = fieldE08L05 =
    new DecimalField(message, HEADERSIZE + 307, 17, 2, "E08L05");
    fields[26] = fieldE08L06 =
    new DecimalField(message, HEADERSIZE + 324, 17, 2, "E08L06");
    fields[27] = fieldE08L07 =
    new DecimalField(message, HEADERSIZE + 341, 17, 2, "E08L07");
    fields[28] = fieldE08L08 =
    new DecimalField(message, HEADERSIZE + 358, 17, 2, "E08L08");
    fields[29] = fieldE08L09 =
    new DecimalField(message, HEADERSIZE + 375, 17, 2, "E08L09");
    fields[30] = fieldE08L10 =
    new DecimalField(message, HEADERSIZE + 392, 17, 2, "E08L10");
    fields[31] = fieldE08L11 =
    new DecimalField(message, HEADERSIZE + 409, 17, 2, "E08L11");

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
  * Set field H08USR using a String value.
  */
  public void setH08USR(String newvalue)
  {
    fieldH08USR.setString(newvalue);
  }

  /**
  * Get value of field H08USR as a String.
  */
  public String getH08USR()
  {
    return fieldH08USR.getString();
  }

  /**
  * Set field H08PGM using a String value.
  */
  public void setH08PGM(String newvalue)
  {
    fieldH08PGM.setString(newvalue);
  }

  /**
  * Get value of field H08PGM as a String.
  */
  public String getH08PGM()
  {
    return fieldH08PGM.getString();
  }

  /**
  * Set field H08TIM using a String value.
  */
  public void setH08TIM(String newvalue)
  {
    fieldH08TIM.setString(newvalue);
  }

  /**
  * Get value of field H08TIM as a String.
  */
  public String getH08TIM()
  {
    return fieldH08TIM.getString();
  }

  /**
  * Set field H08SCR using a String value.
  */
  public void setH08SCR(String newvalue)
  {
    fieldH08SCR.setString(newvalue);
  }

  /**
  * Get value of field H08SCR as a String.
  */
  public String getH08SCR()
  {
    return fieldH08SCR.getString();
  }

  /**
  * Set field H08OPE using a String value.
  */
  public void setH08OPE(String newvalue)
  {
    fieldH08OPE.setString(newvalue);
  }

  /**
  * Get value of field H08OPE as a String.
  */
  public String getH08OPE()
  {
    return fieldH08OPE.getString();
  }

  /**
  * Set field H08MAS using a String value.
  */
  public void setH08MAS(String newvalue)
  {
    fieldH08MAS.setString(newvalue);
  }

  /**
  * Get value of field H08MAS as a String.
  */
  public String getH08MAS()
  {
    return fieldH08MAS.getString();
  }

  /**
  * Set field H08WK1 using a String value.
  */
  public void setH08WK1(String newvalue)
  {
    fieldH08WK1.setString(newvalue);
  }

  /**
  * Get value of field H08WK1 as a String.
  */
  public String getH08WK1()
  {
    return fieldH08WK1.getString();
  }

  /**
  * Set field H08WK2 using a String value.
  */
  public void setH08WK2(String newvalue)
  {
    fieldH08WK2.setString(newvalue);
  }

  /**
  * Get value of field H08WK2 as a String.
  */
  public String getH08WK2()
  {
    return fieldH08WK2.getString();
  }

  /**
  * Set field H08WK3 using a String value.
  */
  public void setH08WK3(String newvalue)
  {
    fieldH08WK3.setString(newvalue);
  }

  /**
  * Get value of field H08WK3 as a String.
  */
  public String getH08WK3()
  {
    return fieldH08WK3.getString();
  }

  /**
  * Set field E08CUN using a String value.
  */
  public void setE08CUN(String newvalue)
  {
    fieldE08CUN.setString(newvalue);
  }

  /**
  * Get value of field E08CUN as a String.
  */
  public String getE08CUN()
  {
    return fieldE08CUN.getString();
  }

  /**
  * Set numeric field E08CUN using a BigDecimal value.
  */
  public void setE08CUN(BigDecimal newvalue)
  {
    fieldE08CUN.setBigDecimal(newvalue);
  }

  /**
  * Return value of numeric field E08CUN as a BigDecimal.
  */
  public BigDecimal getBigDecimalE08CUN()
  {
    return fieldE08CUN.getBigDecimal();
  }

  /**
  * Set field E08A01 using a String value.
  */
  public void setE08A01(String newvalue)
  {
    fieldE08A01.setString(newvalue);
  }

  /**
  * Get value of field E08A01 as a String.
  */
  public String getE08A01()
  {
    return fieldE08A01.getString();
  }

  /**
  * Set numeric field E08A01 using a BigDecimal value.
  */
  public void setE08A01(BigDecimal newvalue)
  {
    fieldE08A01.setBigDecimal(newvalue);
  }

  /**
  * Return value of numeric field E08A01 as a BigDecimal.
  */
  public BigDecimal getBigDecimalE08A01()
  {
    return fieldE08A01.getBigDecimal();
  }

  /**
  * Set field E08A02 using a String value.
  */
  public void setE08A02(String newvalue)
  {
    fieldE08A02.setString(newvalue);
  }

  /**
  * Get value of field E08A02 as a String.
  */
  public String getE08A02()
  {
    return fieldE08A02.getString();
  }

  /**
  * Set numeric field E08A02 using a BigDecimal value.
  */
  public void setE08A02(BigDecimal newvalue)
  {
    fieldE08A02.setBigDecimal(newvalue);
  }

  /**
  * Return value of numeric field E08A02 as a BigDecimal.
  */
  public BigDecimal getBigDecimalE08A02()
  {
    return fieldE08A02.getBigDecimal();
  }

  /**
  * Set field E08A03 using a String value.
  */
  public void setE08A03(String newvalue)
  {
    fieldE08A03.setString(newvalue);
  }

  /**
  * Get value of field E08A03 as a String.
  */
  public String getE08A03()
  {
    return fieldE08A03.getString();
  }

  /**
  * Set numeric field E08A03 using a BigDecimal value.
  */
  public void setE08A03(BigDecimal newvalue)
  {
    fieldE08A03.setBigDecimal(newvalue);
  }

  /**
  * Return value of numeric field E08A03 as a BigDecimal.
  */
  public BigDecimal getBigDecimalE08A03()
  {
    return fieldE08A03.getBigDecimal();
  }

  /**
  * Set field E08A04 using a String value.
  */
  public void setE08A04(String newvalue)
  {
    fieldE08A04.setString(newvalue);
  }

  /**
  * Get value of field E08A04 as a String.
  */
  public String getE08A04()
  {
    return fieldE08A04.getString();
  }

  /**
  * Set numeric field E08A04 using a BigDecimal value.
  */
  public void setE08A04(BigDecimal newvalue)
  {
    fieldE08A04.setBigDecimal(newvalue);
  }

  /**
  * Return value of numeric field E08A04 as a BigDecimal.
  */
  public BigDecimal getBigDecimalE08A04()
  {
    return fieldE08A04.getBigDecimal();
  }

  /**
  * Set field E08A05 using a String value.
  */
  public void setE08A05(String newvalue)
  {
    fieldE08A05.setString(newvalue);
  }

  /**
  * Get value of field E08A05 as a String.
  */
  public String getE08A05()
  {
    return fieldE08A05.getString();
  }

  /**
  * Set numeric field E08A05 using a BigDecimal value.
  */
  public void setE08A05(BigDecimal newvalue)
  {
    fieldE08A05.setBigDecimal(newvalue);
  }

  /**
  * Return value of numeric field E08A05 as a BigDecimal.
  */
  public BigDecimal getBigDecimalE08A05()
  {
    return fieldE08A05.getBigDecimal();
  }

  /**
  * Set field E08A06 using a String value.
  */
  public void setE08A06(String newvalue)
  {
    fieldE08A06.setString(newvalue);
  }

  /**
  * Get value of field E08A06 as a String.
  */
  public String getE08A06()
  {
    return fieldE08A06.getString();
  }

  /**
  * Set numeric field E08A06 using a BigDecimal value.
  */
  public void setE08A06(BigDecimal newvalue)
  {
    fieldE08A06.setBigDecimal(newvalue);
  }

  /**
  * Return value of numeric field E08A06 as a BigDecimal.
  */
  public BigDecimal getBigDecimalE08A06()
  {
    return fieldE08A06.getBigDecimal();
  }

  /**
  * Set field E08A07 using a String value.
  */
  public void setE08A07(String newvalue)
  {
    fieldE08A07.setString(newvalue);
  }

  /**
  * Get value of field E08A07 as a String.
  */
  public String getE08A07()
  {
    return fieldE08A07.getString();
  }

  /**
  * Set numeric field E08A07 using a BigDecimal value.
  */
  public void setE08A07(BigDecimal newvalue)
  {
    fieldE08A07.setBigDecimal(newvalue);
  }

  /**
  * Return value of numeric field E08A07 as a BigDecimal.
  */
  public BigDecimal getBigDecimalE08A07()
  {
    return fieldE08A07.getBigDecimal();
  }

  /**
  * Set field E08A08 using a String value.
  */
  public void setE08A08(String newvalue)
  {
    fieldE08A08.setString(newvalue);
  }

  /**
  * Get value of field E08A08 as a String.
  */
  public String getE08A08()
  {
    return fieldE08A08.getString();
  }

  /**
  * Set numeric field E08A08 using a BigDecimal value.
  */
  public void setE08A08(BigDecimal newvalue)
  {
    fieldE08A08.setBigDecimal(newvalue);
  }

  /**
  * Return value of numeric field E08A08 as a BigDecimal.
  */
  public BigDecimal getBigDecimalE08A08()
  {
    return fieldE08A08.getBigDecimal();
  }

  /**
  * Set field E08A09 using a String value.
  */
  public void setE08A09(String newvalue)
  {
    fieldE08A09.setString(newvalue);
  }

  /**
  * Get value of field E08A09 as a String.
  */
  public String getE08A09()
  {
    return fieldE08A09.getString();
  }

  /**
  * Set numeric field E08A09 using a BigDecimal value.
  */
  public void setE08A09(BigDecimal newvalue)
  {
    fieldE08A09.setBigDecimal(newvalue);
  }

  /**
  * Return value of numeric field E08A09 as a BigDecimal.
  */
  public BigDecimal getBigDecimalE08A09()
  {
    return fieldE08A09.getBigDecimal();
  }

  /**
  * Set field E08A10 using a String value.
  */
  public void setE08A10(String newvalue)
  {
    fieldE08A10.setString(newvalue);
  }

  /**
  * Get value of field E08A10 as a String.
  */
  public String getE08A10()
  {
    return fieldE08A10.getString();
  }

  /**
  * Set numeric field E08A10 using a BigDecimal value.
  */
  public void setE08A10(BigDecimal newvalue)
  {
    fieldE08A10.setBigDecimal(newvalue);
  }

  /**
  * Return value of numeric field E08A10 as a BigDecimal.
  */
  public BigDecimal getBigDecimalE08A10()
  {
    return fieldE08A10.getBigDecimal();
  }

  /**
  * Set field E08A11 using a String value.
  */
  public void setE08A11(String newvalue)
  {
    fieldE08A11.setString(newvalue);
  }

  /**
  * Get value of field E08A11 as a String.
  */
  public String getE08A11()
  {
    return fieldE08A11.getString();
  }

  /**
  * Set numeric field E08A11 using a BigDecimal value.
  */
  public void setE08A11(BigDecimal newvalue)
  {
    fieldE08A11.setBigDecimal(newvalue);
  }

  /**
  * Return value of numeric field E08A11 as a BigDecimal.
  */
  public BigDecimal getBigDecimalE08A11()
  {
    return fieldE08A11.getBigDecimal();
  }

  /**
  * Set field E08L01 using a String value.
  */
  public void setE08L01(String newvalue)
  {
    fieldE08L01.setString(newvalue);
  }

  /**
  * Get value of field E08L01 as a String.
  */
  public String getE08L01()
  {
    return fieldE08L01.getString();
  }

  /**
  * Set numeric field E08L01 using a BigDecimal value.
  */
  public void setE08L01(BigDecimal newvalue)
  {
    fieldE08L01.setBigDecimal(newvalue);
  }

  /**
  * Return value of numeric field E08L01 as a BigDecimal.
  */
  public BigDecimal getBigDecimalE08L01()
  {
    return fieldE08L01.getBigDecimal();
  }

  /**
  * Set field E08L02 using a String value.
  */
  public void setE08L02(String newvalue)
  {
    fieldE08L02.setString(newvalue);
  }

  /**
  * Get value of field E08L02 as a String.
  */
  public String getE08L02()
  {
    return fieldE08L02.getString();
  }

  /**
  * Set numeric field E08L02 using a BigDecimal value.
  */
  public void setE08L02(BigDecimal newvalue)
  {
    fieldE08L02.setBigDecimal(newvalue);
  }

  /**
  * Return value of numeric field E08L02 as a BigDecimal.
  */
  public BigDecimal getBigDecimalE08L02()
  {
    return fieldE08L02.getBigDecimal();
  }

  /**
  * Set field E08L03 using a String value.
  */
  public void setE08L03(String newvalue)
  {
    fieldE08L03.setString(newvalue);
  }

  /**
  * Get value of field E08L03 as a String.
  */
  public String getE08L03()
  {
    return fieldE08L03.getString();
  }

  /**
  * Set numeric field E08L03 using a BigDecimal value.
  */
  public void setE08L03(BigDecimal newvalue)
  {
    fieldE08L03.setBigDecimal(newvalue);
  }

  /**
  * Return value of numeric field E08L03 as a BigDecimal.
  */
  public BigDecimal getBigDecimalE08L03()
  {
    return fieldE08L03.getBigDecimal();
  }

  /**
  * Set field E08L04 using a String value.
  */
  public void setE08L04(String newvalue)
  {
    fieldE08L04.setString(newvalue);
  }

  /**
  * Get value of field E08L04 as a String.
  */
  public String getE08L04()
  {
    return fieldE08L04.getString();
  }

  /**
  * Set numeric field E08L04 using a BigDecimal value.
  */
  public void setE08L04(BigDecimal newvalue)
  {
    fieldE08L04.setBigDecimal(newvalue);
  }

  /**
  * Return value of numeric field E08L04 as a BigDecimal.
  */
  public BigDecimal getBigDecimalE08L04()
  {
    return fieldE08L04.getBigDecimal();
  }

  /**
  * Set field E08L05 using a String value.
  */
  public void setE08L05(String newvalue)
  {
    fieldE08L05.setString(newvalue);
  }

  /**
  * Get value of field E08L05 as a String.
  */
  public String getE08L05()
  {
    return fieldE08L05.getString();
  }

  /**
  * Set numeric field E08L05 using a BigDecimal value.
  */
  public void setE08L05(BigDecimal newvalue)
  {
    fieldE08L05.setBigDecimal(newvalue);
  }

  /**
  * Return value of numeric field E08L05 as a BigDecimal.
  */
  public BigDecimal getBigDecimalE08L05()
  {
    return fieldE08L05.getBigDecimal();
  }

  /**
  * Set field E08L06 using a String value.
  */
  public void setE08L06(String newvalue)
  {
    fieldE08L06.setString(newvalue);
  }

  /**
  * Get value of field E08L06 as a String.
  */
  public String getE08L06()
  {
    return fieldE08L06.getString();
  }

  /**
  * Set numeric field E08L06 using a BigDecimal value.
  */
  public void setE08L06(BigDecimal newvalue)
  {
    fieldE08L06.setBigDecimal(newvalue);
  }

  /**
  * Return value of numeric field E08L06 as a BigDecimal.
  */
  public BigDecimal getBigDecimalE08L06()
  {
    return fieldE08L06.getBigDecimal();
  }

  /**
  * Set field E08L07 using a String value.
  */
  public void setE08L07(String newvalue)
  {
    fieldE08L07.setString(newvalue);
  }

  /**
  * Get value of field E08L07 as a String.
  */
  public String getE08L07()
  {
    return fieldE08L07.getString();
  }

  /**
  * Set numeric field E08L07 using a BigDecimal value.
  */
  public void setE08L07(BigDecimal newvalue)
  {
    fieldE08L07.setBigDecimal(newvalue);
  }

  /**
  * Return value of numeric field E08L07 as a BigDecimal.
  */
  public BigDecimal getBigDecimalE08L07()
  {
    return fieldE08L07.getBigDecimal();
  }

  /**
  * Set field E08L08 using a String value.
  */
  public void setE08L08(String newvalue)
  {
    fieldE08L08.setString(newvalue);
  }

  /**
  * Get value of field E08L08 as a String.
  */
  public String getE08L08()
  {
    return fieldE08L08.getString();
  }

  /**
  * Set numeric field E08L08 using a BigDecimal value.
  */
  public void setE08L08(BigDecimal newvalue)
  {
    fieldE08L08.setBigDecimal(newvalue);
  }

  /**
  * Return value of numeric field E08L08 as a BigDecimal.
  */
  public BigDecimal getBigDecimalE08L08()
  {
    return fieldE08L08.getBigDecimal();
  }

  /**
  * Set field E08L09 using a String value.
  */
  public void setE08L09(String newvalue)
  {
    fieldE08L09.setString(newvalue);
  }

  /**
  * Get value of field E08L09 as a String.
  */
  public String getE08L09()
  {
    return fieldE08L09.getString();
  }

  /**
  * Set numeric field E08L09 using a BigDecimal value.
  */
  public void setE08L09(BigDecimal newvalue)
  {
    fieldE08L09.setBigDecimal(newvalue);
  }

  /**
  * Return value of numeric field E08L09 as a BigDecimal.
  */
  public BigDecimal getBigDecimalE08L09()
  {
    return fieldE08L09.getBigDecimal();
  }

  /**
  * Set field E08L10 using a String value.
  */
  public void setE08L10(String newvalue)
  {
    fieldE08L10.setString(newvalue);
  }

  /**
  * Get value of field E08L10 as a String.
  */
  public String getE08L10()
  {
    return fieldE08L10.getString();
  }

  /**
  * Set numeric field E08L10 using a BigDecimal value.
  */
  public void setE08L10(BigDecimal newvalue)
  {
    fieldE08L10.setBigDecimal(newvalue);
  }

  /**
  * Return value of numeric field E08L10 as a BigDecimal.
  */
  public BigDecimal getBigDecimalE08L10()
  {
    return fieldE08L10.getBigDecimal();
  }

  /**
  * Set field E08L11 using a String value.
  */
  public void setE08L11(String newvalue)
  {
    fieldE08L11.setString(newvalue);
  }

  /**
  * Get value of field E08L11 as a String.
  */
  public String getE08L11()
  {
    return fieldE08L11.getString();
  }

  /**
  * Set numeric field E08L11 using a BigDecimal value.
  */
  public void setE08L11(BigDecimal newvalue)
  {
    fieldE08L11.setBigDecimal(newvalue);
  }

  /**
  * Return value of numeric field E08L11 as a BigDecimal.
  */
  public BigDecimal getBigDecimalE08L11()
  {
    return fieldE08L11.getBigDecimal();
  }

}
