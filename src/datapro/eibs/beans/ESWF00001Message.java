package datapro.eibs.beans;

import datapro.eibs.sockets.*;
import java.io.*;
import java.math.*;

import java.util.*;

/**
* Class generated by AS/400 CRTCLASS command from ESWF00001 physical file definition.
* 
* File level identifier is 1030130204853.
* Record format level identifier is 40B1E48B09FEE.
*/

public class ESWF00001Message extends MessageRecord
{
  final static String fldnames[] = {
                                     "H01USR",
                                     "H01PGM",
                                     "H01TIM",
                                     "H01SCR",
                                     "H01OPE",
                                     "H01MAS",
                                     "H01WK1",
                                     "H01WK2",
                                     "H01WK3",
                                     "ESWFBNK",
                                     "ESWFREF",
                                     "ESWFRLR",
                                     "ESWFSWI",
                                     "ESWFFMT",
                                     "ESWF01",
                                     "ESWF02",
                                     "ESWF03",
                                     "ESWF04",
                                     "ESWF05",
                                     "ESWF06",
                                     "ESWF07",
                                     "ESWF08",
                                     "ESWF09",
                                     "ESWF10",
                                     "ESWF11",
                                     "ESWF12",
                                     "ESWF13",
                                     "ESWF14",
                                     "ESWF15",
                                     "ESWF16",
                                     "ESWF17",
                                     "ESWF18",
                                     "ESWF19",
                                     "ESWF20",
                                     "ESWF21",
                                     "ESWF22",
                                     "ESWF23",
                                     "ESWF24",
                                     "ESWF25",
                                     "ESWF26",
                                     "ESWF27",
                                     "ESWF28",
                                     "ESWF29",
                                     "ESWF30",
                                     "ESWF31",
                                     "ESWF32",
                                     "ESWF33",
                                     "ESWF34",
                                     "ESWF35"
                                   };
  final static String tnames[] = {
                                   "H01USR",
                                   "H01PGM",
                                   "H01TIM",
                                   "H01SCR",
                                   "H01OPE",
                                   "H01MAS",
                                   "H01WK1",
                                   "H01WK2",
                                   "H01WK3",
                                   "ESWFBNK",
                                   "ESWFREF",
                                   "ESWFRLR",
                                   "ESWFSWI",
                                   "ESWFFMT",
                                   "ESWF01",
                                   "ESWF02",
                                   "ESWF03",
                                   "ESWF04",
                                   "ESWF05",
                                   "ESWF06",
                                   "ESWF07",
                                   "ESWF08",
                                   "ESWF09",
                                   "ESWF10",
                                   "ESWF11",
                                   "ESWF12",
                                   "ESWF13",
                                   "ESWF14",
                                   "ESWF15",
                                   "ESWF16",
                                   "ESWF17",
                                   "ESWF18",
                                   "ESWF19",
                                   "ESWF20",
                                   "ESWF21",
                                   "ESWF22",
                                   "ESWF23",
                                   "ESWF24",
                                   "ESWF25",
                                   "ESWF26",
                                   "ESWF27",
                                   "ESWF28",
                                   "ESWF29",
                                   "ESWF30",
                                   "ESWF31",
                                   "ESWF32",
                                   "ESWF33",
                                   "ESWF34",
                                   "ESWF35"
                                 };
  final static String fid = "1030130204853";
  final static String rid = "40B1E48B09FEE";
  final static String fmtname = "ESWF00001";
  final int FIELDCOUNT = 49;
  private static Hashtable tlookup = new Hashtable();
  private CharacterField fieldH01USR = null;
  private CharacterField fieldH01PGM = null;
  private CharacterField fieldH01TIM = null;
  private CharacterField fieldH01SCR = null;
  private CharacterField fieldH01OPE = null;
  private CharacterField fieldH01MAS = null;
  private CharacterField fieldH01WK1 = null;
  private CharacterField fieldH01WK2 = null;
  private CharacterField fieldH01WK3 = null;
  private CharacterField fieldESWFBNK = null;
  private CharacterField fieldESWFREF = null;
  private CharacterField fieldESWFRLR = null;
  private CharacterField fieldESWFSWI = null;
  private DecimalField fieldESWFFMT = null;
  private CharacterField fieldESWF01 = null;
  private CharacterField fieldESWF02 = null;
  private CharacterField fieldESWF03 = null;
  private CharacterField fieldESWF04 = null;
  private CharacterField fieldESWF05 = null;
  private CharacterField fieldESWF06 = null;
  private CharacterField fieldESWF07 = null;
  private CharacterField fieldESWF08 = null;
  private CharacterField fieldESWF09 = null;
  private CharacterField fieldESWF10 = null;
  private CharacterField fieldESWF11 = null;
  private CharacterField fieldESWF12 = null;
  private CharacterField fieldESWF13 = null;
  private CharacterField fieldESWF14 = null;
  private CharacterField fieldESWF15 = null;
  private CharacterField fieldESWF16 = null;
  private CharacterField fieldESWF17 = null;
  private CharacterField fieldESWF18 = null;
  private CharacterField fieldESWF19 = null;
  private CharacterField fieldESWF20 = null;
  private CharacterField fieldESWF21 = null;
  private CharacterField fieldESWF22 = null;
  private CharacterField fieldESWF23 = null;
  private CharacterField fieldESWF24 = null;
  private CharacterField fieldESWF25 = null;
  private CharacterField fieldESWF26 = null;
  private CharacterField fieldESWF27 = null;
  private CharacterField fieldESWF28 = null;
  private CharacterField fieldESWF29 = null;
  private CharacterField fieldESWF30 = null;
  private CharacterField fieldESWF31 = null;
  private CharacterField fieldESWF32 = null;
  private CharacterField fieldESWF33 = null;
  private CharacterField fieldESWF34 = null;
  private CharacterField fieldESWF35 = null;

  /**
  * Constructor for ESWF00001Message.
  */
  public ESWF00001Message()
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
    recordsize = 1842;
    fileid = fid;
    recordid = rid;
    message = new byte[getByteLength()];
    formatname = fmtname;
    fieldnames = fldnames;
    tagnames = tnames;
    fields = new MessageField[FIELDCOUNT];

    fields[0] = fieldH01USR =
    new CharacterField(message, HEADERSIZE + 0, 10, "H01USR");
    fields[1] = fieldH01PGM =
    new CharacterField(message, HEADERSIZE + 10, 10, "H01PGM");
    fields[2] = fieldH01TIM =
    new CharacterField(message, HEADERSIZE + 20, 12, "H01TIM");
    fields[3] = fieldH01SCR =
    new CharacterField(message, HEADERSIZE + 32, 2, "H01SCR");
    fields[4] = fieldH01OPE =
    new CharacterField(message, HEADERSIZE + 34, 4, "H01OPE");
    fields[5] = fieldH01MAS =
    new CharacterField(message, HEADERSIZE + 38, 1, "H01MAS");
    fields[6] = fieldH01WK1 =
    new CharacterField(message, HEADERSIZE + 39, 1, "H01WK1");
    fields[7] = fieldH01WK2 =
    new CharacterField(message, HEADERSIZE + 40, 1, "H01WK2");
    fields[8] = fieldH01WK3 =
    new CharacterField(message, HEADERSIZE + 41, 1, "H01WK3");
    fields[9] = fieldESWFBNK =
    new CharacterField(message, HEADERSIZE + 42, 2, "ESWFBNK");
    fields[10] = fieldESWFREF =
    new CharacterField(message, HEADERSIZE + 44, 16, "ESWFREF");
    fields[11] = fieldESWFRLR =
    new CharacterField(message, HEADERSIZE + 60, 16, "ESWFRLR");
    fields[12] = fieldESWFSWI =
    new CharacterField(message, HEADERSIZE + 76, 12, "ESWFSWI");
    fields[13] = fieldESWFFMT =
    new DecimalField(message, HEADERSIZE + 88, 4, 0, "ESWFFMT");
    fields[14] = fieldESWF01 =
    new CharacterField(message, HEADERSIZE + 92, 50, "ESWF01");
    fields[15] = fieldESWF02 =
    new CharacterField(message, HEADERSIZE + 142, 50, "ESWF02");
    fields[16] = fieldESWF03 =
    new CharacterField(message, HEADERSIZE + 192, 50, "ESWF03");
    fields[17] = fieldESWF04 =
    new CharacterField(message, HEADERSIZE + 242, 50, "ESWF04");
    fields[18] = fieldESWF05 =
    new CharacterField(message, HEADERSIZE + 292, 50, "ESWF05");
    fields[19] = fieldESWF06 =
    new CharacterField(message, HEADERSIZE + 342, 50, "ESWF06");
    fields[20] = fieldESWF07 =
    new CharacterField(message, HEADERSIZE + 392, 50, "ESWF07");
    fields[21] = fieldESWF08 =
    new CharacterField(message, HEADERSIZE + 442, 50, "ESWF08");
    fields[22] = fieldESWF09 =
    new CharacterField(message, HEADERSIZE + 492, 50, "ESWF09");
    fields[23] = fieldESWF10 =
    new CharacterField(message, HEADERSIZE + 542, 50, "ESWF10");
    fields[24] = fieldESWF11 =
    new CharacterField(message, HEADERSIZE + 592, 50, "ESWF11");
    fields[25] = fieldESWF12 =
    new CharacterField(message, HEADERSIZE + 642, 50, "ESWF12");
    fields[26] = fieldESWF13 =
    new CharacterField(message, HEADERSIZE + 692, 50, "ESWF13");
    fields[27] = fieldESWF14 =
    new CharacterField(message, HEADERSIZE + 742, 50, "ESWF14");
    fields[28] = fieldESWF15 =
    new CharacterField(message, HEADERSIZE + 792, 50, "ESWF15");
    fields[29] = fieldESWF16 =
    new CharacterField(message, HEADERSIZE + 842, 50, "ESWF16");
    fields[30] = fieldESWF17 =
    new CharacterField(message, HEADERSIZE + 892, 50, "ESWF17");
    fields[31] = fieldESWF18 =
    new CharacterField(message, HEADERSIZE + 942, 50, "ESWF18");
    fields[32] = fieldESWF19 =
    new CharacterField(message, HEADERSIZE + 992, 50, "ESWF19");
    fields[33] = fieldESWF20 =
    new CharacterField(message, HEADERSIZE + 1042, 50, "ESWF20");
    fields[34] = fieldESWF21 =
    new CharacterField(message, HEADERSIZE + 1092, 50, "ESWF21");
    fields[35] = fieldESWF22 =
    new CharacterField(message, HEADERSIZE + 1142, 50, "ESWF22");
    fields[36] = fieldESWF23 =
    new CharacterField(message, HEADERSIZE + 1192, 50, "ESWF23");
    fields[37] = fieldESWF24 =
    new CharacterField(message, HEADERSIZE + 1242, 50, "ESWF24");
    fields[38] = fieldESWF25 =
    new CharacterField(message, HEADERSIZE + 1292, 50, "ESWF25");
    fields[39] = fieldESWF26 =
    new CharacterField(message, HEADERSIZE + 1342, 50, "ESWF26");
    fields[40] = fieldESWF27 =
    new CharacterField(message, HEADERSIZE + 1392, 50, "ESWF27");
    fields[41] = fieldESWF28 =
    new CharacterField(message, HEADERSIZE + 1442, 50, "ESWF28");
    fields[42] = fieldESWF29 =
    new CharacterField(message, HEADERSIZE + 1492, 50, "ESWF29");
    fields[43] = fieldESWF30 =
    new CharacterField(message, HEADERSIZE + 1542, 50, "ESWF30");
    fields[44] = fieldESWF31 =
    new CharacterField(message, HEADERSIZE + 1592, 50, "ESWF31");
    fields[45] = fieldESWF32 =
    new CharacterField(message, HEADERSIZE + 1642, 50, "ESWF32");
    fields[46] = fieldESWF33 =
    new CharacterField(message, HEADERSIZE + 1692, 50, "ESWF33");
    fields[47] = fieldESWF34 =
    new CharacterField(message, HEADERSIZE + 1742, 50, "ESWF34");
    fields[48] = fieldESWF35 =
    new CharacterField(message, HEADERSIZE + 1792, 50, "ESWF35");

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
  * Set field H01USR using a String value.
  */
  public void setH01USR(String newvalue)
  {
    fieldH01USR.setString(newvalue);
  }

  /**
  * Get value of field H01USR as a String.
  */
  public String getH01USR()
  {
    return fieldH01USR.getString();
  }

  /**
  * Set field H01PGM using a String value.
  */
  public void setH01PGM(String newvalue)
  {
    fieldH01PGM.setString(newvalue);
  }

  /**
  * Get value of field H01PGM as a String.
  */
  public String getH01PGM()
  {
    return fieldH01PGM.getString();
  }

  /**
  * Set field H01TIM using a String value.
  */
  public void setH01TIM(String newvalue)
  {
    fieldH01TIM.setString(newvalue);
  }

  /**
  * Get value of field H01TIM as a String.
  */
  public String getH01TIM()
  {
    return fieldH01TIM.getString();
  }

  /**
  * Set field H01SCR using a String value.
  */
  public void setH01SCR(String newvalue)
  {
    fieldH01SCR.setString(newvalue);
  }

  /**
  * Get value of field H01SCR as a String.
  */
  public String getH01SCR()
  {
    return fieldH01SCR.getString();
  }

  /**
  * Set field H01OPE using a String value.
  */
  public void setH01OPE(String newvalue)
  {
    fieldH01OPE.setString(newvalue);
  }

  /**
  * Get value of field H01OPE as a String.
  */
  public String getH01OPE()
  {
    return fieldH01OPE.getString();
  }

  /**
  * Set field H01MAS using a String value.
  */
  public void setH01MAS(String newvalue)
  {
    fieldH01MAS.setString(newvalue);
  }

  /**
  * Get value of field H01MAS as a String.
  */
  public String getH01MAS()
  {
    return fieldH01MAS.getString();
  }

  /**
  * Set field H01WK1 using a String value.
  */
  public void setH01WK1(String newvalue)
  {
    fieldH01WK1.setString(newvalue);
  }

  /**
  * Get value of field H01WK1 as a String.
  */
  public String getH01WK1()
  {
    return fieldH01WK1.getString();
  }

  /**
  * Set field H01WK2 using a String value.
  */
  public void setH01WK2(String newvalue)
  {
    fieldH01WK2.setString(newvalue);
  }

  /**
  * Get value of field H01WK2 as a String.
  */
  public String getH01WK2()
  {
    return fieldH01WK2.getString();
  }

  /**
  * Set field H01WK3 using a String value.
  */
  public void setH01WK3(String newvalue)
  {
    fieldH01WK3.setString(newvalue);
  }

  /**
  * Get value of field H01WK3 as a String.
  */
  public String getH01WK3()
  {
    return fieldH01WK3.getString();
  }

  /**
  * Set field ESWFBNK using a String value.
  */
  public void setESWFBNK(String newvalue)
  {
    fieldESWFBNK.setString(newvalue);
  }

  /**
  * Get value of field ESWFBNK as a String.
  */
  public String getESWFBNK()
  {
    return fieldESWFBNK.getString();
  }

  /**
  * Set field ESWFREF using a String value.
  */
  public void setESWFREF(String newvalue)
  {
    fieldESWFREF.setString(newvalue);
  }

  /**
  * Get value of field ESWFREF as a String.
  */
  public String getESWFREF()
  {
    return fieldESWFREF.getString();
  }

  /**
  * Set field ESWFRLR using a String value.
  */
  public void setESWFRLR(String newvalue)
  {
    fieldESWFRLR.setString(newvalue);
  }

  /**
  * Get value of field ESWFRLR as a String.
  */
  public String getESWFRLR()
  {
    return fieldESWFRLR.getString();
  }

  /**
  * Set field ESWFSWI using a String value.
  */
  public void setESWFSWI(String newvalue)
  {
    fieldESWFSWI.setString(newvalue);
  }

  /**
  * Get value of field ESWFSWI as a String.
  */
  public String getESWFSWI()
  {
    return fieldESWFSWI.getString();
  }

  /**
  * Set field ESWFFMT using a String value.
  */
  public void setESWFFMT(String newvalue)
  {
    fieldESWFFMT.setString(newvalue);
  }

  /**
  * Get value of field ESWFFMT as a String.
  */
  public String getESWFFMT()
  {
    return fieldESWFFMT.getString();
  }

  /**
  * Set numeric field ESWFFMT using a BigDecimal value.
  */
  public void setESWFFMT(BigDecimal newvalue)
  {
    fieldESWFFMT.setBigDecimal(newvalue);
  }

  /**
  * Return value of numeric field ESWFFMT as a BigDecimal.
  */
  public BigDecimal getBigDecimalESWFFMT()
  {
    return fieldESWFFMT.getBigDecimal();
  }

  /**
  * Set field ESWF01 using a String value.
  */
  public void setESWF01(String newvalue)
  {
    fieldESWF01.setString(newvalue);
  }

  /**
  * Get value of field ESWF01 as a String.
  */
  public String getESWF01()
  {
    return fieldESWF01.getString();
  }

  /**
  * Set field ESWF02 using a String value.
  */
  public void setESWF02(String newvalue)
  {
    fieldESWF02.setString(newvalue);
  }

  /**
  * Get value of field ESWF02 as a String.
  */
  public String getESWF02()
  {
    return fieldESWF02.getString();
  }

  /**
  * Set field ESWF03 using a String value.
  */
  public void setESWF03(String newvalue)
  {
    fieldESWF03.setString(newvalue);
  }

  /**
  * Get value of field ESWF03 as a String.
  */
  public String getESWF03()
  {
    return fieldESWF03.getString();
  }

  /**
  * Set field ESWF04 using a String value.
  */
  public void setESWF04(String newvalue)
  {
    fieldESWF04.setString(newvalue);
  }

  /**
  * Get value of field ESWF04 as a String.
  */
  public String getESWF04()
  {
    return fieldESWF04.getString();
  }

  /**
  * Set field ESWF05 using a String value.
  */
  public void setESWF05(String newvalue)
  {
    fieldESWF05.setString(newvalue);
  }

  /**
  * Get value of field ESWF05 as a String.
  */
  public String getESWF05()
  {
    return fieldESWF05.getString();
  }

  /**
  * Set field ESWF06 using a String value.
  */
  public void setESWF06(String newvalue)
  {
    fieldESWF06.setString(newvalue);
  }

  /**
  * Get value of field ESWF06 as a String.
  */
  public String getESWF06()
  {
    return fieldESWF06.getString();
  }

  /**
  * Set field ESWF07 using a String value.
  */
  public void setESWF07(String newvalue)
  {
    fieldESWF07.setString(newvalue);
  }

  /**
  * Get value of field ESWF07 as a String.
  */
  public String getESWF07()
  {
    return fieldESWF07.getString();
  }

  /**
  * Set field ESWF08 using a String value.
  */
  public void setESWF08(String newvalue)
  {
    fieldESWF08.setString(newvalue);
  }

  /**
  * Get value of field ESWF08 as a String.
  */
  public String getESWF08()
  {
    return fieldESWF08.getString();
  }

  /**
  * Set field ESWF09 using a String value.
  */
  public void setESWF09(String newvalue)
  {
    fieldESWF09.setString(newvalue);
  }

  /**
  * Get value of field ESWF09 as a String.
  */
  public String getESWF09()
  {
    return fieldESWF09.getString();
  }

  /**
  * Set field ESWF10 using a String value.
  */
  public void setESWF10(String newvalue)
  {
    fieldESWF10.setString(newvalue);
  }

  /**
  * Get value of field ESWF10 as a String.
  */
  public String getESWF10()
  {
    return fieldESWF10.getString();
  }

  /**
  * Set field ESWF11 using a String value.
  */
  public void setESWF11(String newvalue)
  {
    fieldESWF11.setString(newvalue);
  }

  /**
  * Get value of field ESWF11 as a String.
  */
  public String getESWF11()
  {
    return fieldESWF11.getString();
  }

  /**
  * Set field ESWF12 using a String value.
  */
  public void setESWF12(String newvalue)
  {
    fieldESWF12.setString(newvalue);
  }

  /**
  * Get value of field ESWF12 as a String.
  */
  public String getESWF12()
  {
    return fieldESWF12.getString();
  }

  /**
  * Set field ESWF13 using a String value.
  */
  public void setESWF13(String newvalue)
  {
    fieldESWF13.setString(newvalue);
  }

  /**
  * Get value of field ESWF13 as a String.
  */
  public String getESWF13()
  {
    return fieldESWF13.getString();
  }

  /**
  * Set field ESWF14 using a String value.
  */
  public void setESWF14(String newvalue)
  {
    fieldESWF14.setString(newvalue);
  }

  /**
  * Get value of field ESWF14 as a String.
  */
  public String getESWF14()
  {
    return fieldESWF14.getString();
  }

  /**
  * Set field ESWF15 using a String value.
  */
  public void setESWF15(String newvalue)
  {
    fieldESWF15.setString(newvalue);
  }

  /**
  * Get value of field ESWF15 as a String.
  */
  public String getESWF15()
  {
    return fieldESWF15.getString();
  }

  /**
  * Set field ESWF16 using a String value.
  */
  public void setESWF16(String newvalue)
  {
    fieldESWF16.setString(newvalue);
  }

  /**
  * Get value of field ESWF16 as a String.
  */
  public String getESWF16()
  {
    return fieldESWF16.getString();
  }

  /**
  * Set field ESWF17 using a String value.
  */
  public void setESWF17(String newvalue)
  {
    fieldESWF17.setString(newvalue);
  }

  /**
  * Get value of field ESWF17 as a String.
  */
  public String getESWF17()
  {
    return fieldESWF17.getString();
  }

  /**
  * Set field ESWF18 using a String value.
  */
  public void setESWF18(String newvalue)
  {
    fieldESWF18.setString(newvalue);
  }

  /**
  * Get value of field ESWF18 as a String.
  */
  public String getESWF18()
  {
    return fieldESWF18.getString();
  }

  /**
  * Set field ESWF19 using a String value.
  */
  public void setESWF19(String newvalue)
  {
    fieldESWF19.setString(newvalue);
  }

  /**
  * Get value of field ESWF19 as a String.
  */
  public String getESWF19()
  {
    return fieldESWF19.getString();
  }

  /**
  * Set field ESWF20 using a String value.
  */
  public void setESWF20(String newvalue)
  {
    fieldESWF20.setString(newvalue);
  }

  /**
  * Get value of field ESWF20 as a String.
  */
  public String getESWF20()
  {
    return fieldESWF20.getString();
  }

  /**
  * Set field ESWF21 using a String value.
  */
  public void setESWF21(String newvalue)
  {
    fieldESWF21.setString(newvalue);
  }

  /**
  * Get value of field ESWF21 as a String.
  */
  public String getESWF21()
  {
    return fieldESWF21.getString();
  }

  /**
  * Set field ESWF22 using a String value.
  */
  public void setESWF22(String newvalue)
  {
    fieldESWF22.setString(newvalue);
  }

  /**
  * Get value of field ESWF22 as a String.
  */
  public String getESWF22()
  {
    return fieldESWF22.getString();
  }

  /**
  * Set field ESWF23 using a String value.
  */
  public void setESWF23(String newvalue)
  {
    fieldESWF23.setString(newvalue);
  }

  /**
  * Get value of field ESWF23 as a String.
  */
  public String getESWF23()
  {
    return fieldESWF23.getString();
  }

  /**
  * Set field ESWF24 using a String value.
  */
  public void setESWF24(String newvalue)
  {
    fieldESWF24.setString(newvalue);
  }

  /**
  * Get value of field ESWF24 as a String.
  */
  public String getESWF24()
  {
    return fieldESWF24.getString();
  }

  /**
  * Set field ESWF25 using a String value.
  */
  public void setESWF25(String newvalue)
  {
    fieldESWF25.setString(newvalue);
  }

  /**
  * Get value of field ESWF25 as a String.
  */
  public String getESWF25()
  {
    return fieldESWF25.getString();
  }

  /**
  * Set field ESWF26 using a String value.
  */
  public void setESWF26(String newvalue)
  {
    fieldESWF26.setString(newvalue);
  }

  /**
  * Get value of field ESWF26 as a String.
  */
  public String getESWF26()
  {
    return fieldESWF26.getString();
  }

  /**
  * Set field ESWF27 using a String value.
  */
  public void setESWF27(String newvalue)
  {
    fieldESWF27.setString(newvalue);
  }

  /**
  * Get value of field ESWF27 as a String.
  */
  public String getESWF27()
  {
    return fieldESWF27.getString();
  }

  /**
  * Set field ESWF28 using a String value.
  */
  public void setESWF28(String newvalue)
  {
    fieldESWF28.setString(newvalue);
  }

  /**
  * Get value of field ESWF28 as a String.
  */
  public String getESWF28()
  {
    return fieldESWF28.getString();
  }

  /**
  * Set field ESWF29 using a String value.
  */
  public void setESWF29(String newvalue)
  {
    fieldESWF29.setString(newvalue);
  }

  /**
  * Get value of field ESWF29 as a String.
  */
  public String getESWF29()
  {
    return fieldESWF29.getString();
  }

  /**
  * Set field ESWF30 using a String value.
  */
  public void setESWF30(String newvalue)
  {
    fieldESWF30.setString(newvalue);
  }

  /**
  * Get value of field ESWF30 as a String.
  */
  public String getESWF30()
  {
    return fieldESWF30.getString();
  }

  /**
  * Set field ESWF31 using a String value.
  */
  public void setESWF31(String newvalue)
  {
    fieldESWF31.setString(newvalue);
  }

  /**
  * Get value of field ESWF31 as a String.
  */
  public String getESWF31()
  {
    return fieldESWF31.getString();
  }

  /**
  * Set field ESWF32 using a String value.
  */
  public void setESWF32(String newvalue)
  {
    fieldESWF32.setString(newvalue);
  }

  /**
  * Get value of field ESWF32 as a String.
  */
  public String getESWF32()
  {
    return fieldESWF32.getString();
  }

  /**
  * Set field ESWF33 using a String value.
  */
  public void setESWF33(String newvalue)
  {
    fieldESWF33.setString(newvalue);
  }

  /**
  * Get value of field ESWF33 as a String.
  */
  public String getESWF33()
  {
    return fieldESWF33.getString();
  }

  /**
  * Set field ESWF34 using a String value.
  */
  public void setESWF34(String newvalue)
  {
    fieldESWF34.setString(newvalue);
  }

  /**
  * Get value of field ESWF34 as a String.
  */
  public String getESWF34()
  {
    return fieldESWF34.getString();
  }

  /**
  * Set field ESWF35 using a String value.
  */
  public void setESWF35(String newvalue)
  {
    fieldESWF35.setString(newvalue);
  }

  /**
  * Get value of field ESWF35 as a String.
  */
  public String getESWF35()
  {
    return fieldESWF35.getString();
  }

}
