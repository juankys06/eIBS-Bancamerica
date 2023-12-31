package datapro.eibs.beans;

import datapro.eibs.sockets.*;
import java.io.*;
import java.math.*;

import java.util.*;

/**
* Class generated by AS/400 CRTCLASS command from ESD009501 physical file definition.
* 
* File level identifier is 1041006102423.
* Record format level identifier is 2D67E33731F1E.
*/

public class ESD009501Message extends MessageRecord
{
  final static String fldnames[] = {
                                     "H01USERID",
                                     "H01PROGRM",
                                     "H01TIMSYS",
                                     "H01SCRCOD",
                                     "H01OPECOD",
                                     "H01FLGMAS",
                                     "H01FLGWK1",
                                     "H01FLGWK2",
                                     "H01FLGWK3",
                                     "E01SPITYP",
                                     "E01SPIACC",
                                     "E01ACMACD",
                                     "E01ACMPRO",
                                     "E01ACMCUN",
                                     "E01CUSNA1",
                                     "E01ACMBNK",
                                     "E01ACMBRN",
                                     "E01ACMCCY",
                                     "E01ACMGLN",
                                     "E01ACMCCN",
                                     "E01SPI010",
                                     "E01SPI011",
                                     "E01SPI012",
                                     "E01SPI013",
                                     "E01SPI020",
                                     "E01SPI021",
                                     "E01SPI022",
                                     "E01SPI023",
                                     "E01SPI030",
                                     "E01SPI031",
                                     "E01SPI032",
                                     "E01SPI033",
                                     "E01SPI040",
                                     "E01SPI041",
                                     "E01SPI042",
                                     "E01SPI043",
                                     "E01SPI050",
                                     "E01SPI051",
                                     "E01SPI052",
                                     "E01SPI053",
                                     "E01SPI060",
                                     "E01SPI061",
                                     "E01SPI062",
                                     "E01SPI063",
                                     "E01SPI070",
                                     "E01SPI071",
                                     "E01SPI072",
                                     "E01SPI073",
                                     "E01SPI080",
                                     "E01SPI081",
                                     "E01SPI082",
                                     "E01SPI083",
                                     "E01SPI090",
                                     "E01SPI091",
                                     "E01SPI092",
                                     "E01SPI093",
                                     "E01SPI100",
                                     "E01SPI101",
                                     "E01SPI102",
                                     "E01SPI103"
                                   };
  final static String tnames[] = {
                                   "H01USERID",
                                   "H01PROGRM",
                                   "H01TIMSYS",
                                   "H01SCRCOD",
                                   "H01OPECOD",
                                   "H01FLGMAS",
                                   "H01FLGWK1",
                                   "H01FLGWK2",
                                   "H01FLGWK3",
                                   "E01SPITYP",
                                   "E01SPIACC",
                                   "E01ACMACD",
                                   "E01ACMPRO",
                                   "E01ACMCUN",
                                   "E01CUSNA1",
                                   "E01ACMBNK",
                                   "E01ACMBRN",
                                   "E01ACMCCY",
                                   "E01ACMGLN",
                                   "E01ACMCCN",
                                   "E01SPI010",
                                   "E01SPI011",
                                   "E01SPI012",
                                   "E01SPI013",
                                   "E01SPI020",
                                   "E01SPI021",
                                   "E01SPI022",
                                   "E01SPI023",
                                   "E01SPI030",
                                   "E01SPI031",
                                   "E01SPI032",
                                   "E01SPI033",
                                   "E01SPI040",
                                   "E01SPI041",
                                   "E01SPI042",
                                   "E01SPI043",
                                   "E01SPI050",
                                   "E01SPI051",
                                   "E01SPI052",
                                   "E01SPI053",
                                   "E01SPI060",
                                   "E01SPI061",
                                   "E01SPI062",
                                   "E01SPI063",
                                   "E01SPI070",
                                   "E01SPI071",
                                   "E01SPI072",
                                   "E01SPI073",
                                   "E01SPI080",
                                   "E01SPI081",
                                   "E01SPI082",
                                   "E01SPI083",
                                   "E01SPI090",
                                   "E01SPI091",
                                   "E01SPI092",
                                   "E01SPI093",
                                   "E01SPI100",
                                   "E01SPI101",
                                   "E01SPI102",
                                   "E01SPI103"
                                 };
  final static String fid = "1041006102423";
  final static String rid = "2D67E33731F1E";
  final static String fmtname = "ESD009501";
  final int FIELDCOUNT = 60;
  private static Hashtable tlookup = new Hashtable();
  private CharacterField fieldH01USERID = null;
  private CharacterField fieldH01PROGRM = null;
  private CharacterField fieldH01TIMSYS = null;
  private CharacterField fieldH01SCRCOD = null;
  private CharacterField fieldH01OPECOD = null;
  private CharacterField fieldH01FLGMAS = null;
  private CharacterField fieldH01FLGWK1 = null;
  private CharacterField fieldH01FLGWK2 = null;
  private CharacterField fieldH01FLGWK3 = null;
  private CharacterField fieldE01SPITYP = null;
  private DecimalField fieldE01SPIACC = null;
  private CharacterField fieldE01ACMACD = null;
  private CharacterField fieldE01ACMPRO = null;
  private DecimalField fieldE01ACMCUN = null;
  private CharacterField fieldE01CUSNA1 = null;
  private CharacterField fieldE01ACMBNK = null;
  private DecimalField fieldE01ACMBRN = null;
  private CharacterField fieldE01ACMCCY = null;
  private DecimalField fieldE01ACMGLN = null;
  private DecimalField fieldE01ACMCCN = null;
  private CharacterField fieldE01SPI010 = null;
  private CharacterField fieldE01SPI011 = null;
  private CharacterField fieldE01SPI012 = null;
  private CharacterField fieldE01SPI013 = null;
  private CharacterField fieldE01SPI020 = null;
  private CharacterField fieldE01SPI021 = null;
  private CharacterField fieldE01SPI022 = null;
  private CharacterField fieldE01SPI023 = null;
  private CharacterField fieldE01SPI030 = null;
  private CharacterField fieldE01SPI031 = null;
  private CharacterField fieldE01SPI032 = null;
  private CharacterField fieldE01SPI033 = null;
  private CharacterField fieldE01SPI040 = null;
  private CharacterField fieldE01SPI041 = null;
  private CharacterField fieldE01SPI042 = null;
  private CharacterField fieldE01SPI043 = null;
  private CharacterField fieldE01SPI050 = null;
  private CharacterField fieldE01SPI051 = null;
  private CharacterField fieldE01SPI052 = null;
  private CharacterField fieldE01SPI053 = null;
  private CharacterField fieldE01SPI060 = null;
  private CharacterField fieldE01SPI061 = null;
  private CharacterField fieldE01SPI062 = null;
  private CharacterField fieldE01SPI063 = null;
  private CharacterField fieldE01SPI070 = null;
  private CharacterField fieldE01SPI071 = null;
  private CharacterField fieldE01SPI072 = null;
  private CharacterField fieldE01SPI073 = null;
  private CharacterField fieldE01SPI080 = null;
  private CharacterField fieldE01SPI081 = null;
  private CharacterField fieldE01SPI082 = null;
  private CharacterField fieldE01SPI083 = null;
  private CharacterField fieldE01SPI090 = null;
  private CharacterField fieldE01SPI091 = null;
  private CharacterField fieldE01SPI092 = null;
  private CharacterField fieldE01SPI093 = null;
  private CharacterField fieldE01SPI100 = null;
  private CharacterField fieldE01SPI101 = null;
  private CharacterField fieldE01SPI102 = null;
  private CharacterField fieldE01SPI103 = null;

  /**
  * Constructor for ESD009501Message.
  */
  public ESD009501Message()
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
    recordsize = 2552;
    fileid = fid;
    recordid = rid;
    message = new byte[getByteLength()];
    formatname = fmtname;
    fieldnames = fldnames;
    tagnames = tnames;
    fields = new MessageField[FIELDCOUNT];

    fields[0] = fieldH01USERID =
    new CharacterField(message, HEADERSIZE + 0, 10, "H01USERID");
    fields[1] = fieldH01PROGRM =
    new CharacterField(message, HEADERSIZE + 10, 10, "H01PROGRM");
    fields[2] = fieldH01TIMSYS =
    new CharacterField(message, HEADERSIZE + 20, 12, "H01TIMSYS");
    fields[3] = fieldH01SCRCOD =
    new CharacterField(message, HEADERSIZE + 32, 2, "H01SCRCOD");
    fields[4] = fieldH01OPECOD =
    new CharacterField(message, HEADERSIZE + 34, 4, "H01OPECOD");
    fields[5] = fieldH01FLGMAS =
    new CharacterField(message, HEADERSIZE + 38, 1, "H01FLGMAS");
    fields[6] = fieldH01FLGWK1 =
    new CharacterField(message, HEADERSIZE + 39, 1, "H01FLGWK1");
    fields[7] = fieldH01FLGWK2 =
    new CharacterField(message, HEADERSIZE + 40, 1, "H01FLGWK2");
    fields[8] = fieldH01FLGWK3 =
    new CharacterField(message, HEADERSIZE + 41, 1, "H01FLGWK3");
    fields[9] = fieldE01SPITYP =
    new CharacterField(message, HEADERSIZE + 42, 1, "E01SPITYP");
    fields[10] = fieldE01SPIACC =
    new DecimalField(message, HEADERSIZE + 43, 13, 0, "E01SPIACC");
    fields[11] = fieldE01ACMACD =
    new CharacterField(message, HEADERSIZE + 56, 2, "E01ACMACD");
    fields[12] = fieldE01ACMPRO =
    new CharacterField(message, HEADERSIZE + 58, 4, "E01ACMPRO");
    fields[13] = fieldE01ACMCUN =
    new DecimalField(message, HEADERSIZE + 62, 10, 0, "E01ACMCUN");
    fields[14] = fieldE01CUSNA1 =
    new CharacterField(message, HEADERSIZE + 72, 45, "E01CUSNA1");
    fields[15] = fieldE01ACMBNK =
    new CharacterField(message, HEADERSIZE + 117, 2, "E01ACMBNK");
    fields[16] = fieldE01ACMBRN =
    new DecimalField(message, HEADERSIZE + 119, 4, 0, "E01ACMBRN");
    fields[17] = fieldE01ACMCCY =
    new CharacterField(message, HEADERSIZE + 123, 3, "E01ACMCCY");
    fields[18] = fieldE01ACMGLN =
    new DecimalField(message, HEADERSIZE + 126, 17, 0, "E01ACMGLN");
    fields[19] = fieldE01ACMCCN =
    new DecimalField(message, HEADERSIZE + 143, 9, 0, "E01ACMCCN");
    fields[20] = fieldE01SPI010 =
    new CharacterField(message, HEADERSIZE + 152, 60, "E01SPI010");
    fields[21] = fieldE01SPI011 =
    new CharacterField(message, HEADERSIZE + 212, 60, "E01SPI011");
    fields[22] = fieldE01SPI012 =
    new CharacterField(message, HEADERSIZE + 272, 60, "E01SPI012");
    fields[23] = fieldE01SPI013 =
    new CharacterField(message, HEADERSIZE + 332, 60, "E01SPI013");
    fields[24] = fieldE01SPI020 =
    new CharacterField(message, HEADERSIZE + 392, 60, "E01SPI020");
    fields[25] = fieldE01SPI021 =
    new CharacterField(message, HEADERSIZE + 452, 60, "E01SPI021");
    fields[26] = fieldE01SPI022 =
    new CharacterField(message, HEADERSIZE + 512, 60, "E01SPI022");
    fields[27] = fieldE01SPI023 =
    new CharacterField(message, HEADERSIZE + 572, 60, "E01SPI023");
    fields[28] = fieldE01SPI030 =
    new CharacterField(message, HEADERSIZE + 632, 60, "E01SPI030");
    fields[29] = fieldE01SPI031 =
    new CharacterField(message, HEADERSIZE + 692, 60, "E01SPI031");
    fields[30] = fieldE01SPI032 =
    new CharacterField(message, HEADERSIZE + 752, 60, "E01SPI032");
    fields[31] = fieldE01SPI033 =
    new CharacterField(message, HEADERSIZE + 812, 60, "E01SPI033");
    fields[32] = fieldE01SPI040 =
    new CharacterField(message, HEADERSIZE + 872, 60, "E01SPI040");
    fields[33] = fieldE01SPI041 =
    new CharacterField(message, HEADERSIZE + 932, 60, "E01SPI041");
    fields[34] = fieldE01SPI042 =
    new CharacterField(message, HEADERSIZE + 992, 60, "E01SPI042");
    fields[35] = fieldE01SPI043 =
    new CharacterField(message, HEADERSIZE + 1052, 60, "E01SPI043");
    fields[36] = fieldE01SPI050 =
    new CharacterField(message, HEADERSIZE + 1112, 60, "E01SPI050");
    fields[37] = fieldE01SPI051 =
    new CharacterField(message, HEADERSIZE + 1172, 60, "E01SPI051");
    fields[38] = fieldE01SPI052 =
    new CharacterField(message, HEADERSIZE + 1232, 60, "E01SPI052");
    fields[39] = fieldE01SPI053 =
    new CharacterField(message, HEADERSIZE + 1292, 60, "E01SPI053");
    fields[40] = fieldE01SPI060 =
    new CharacterField(message, HEADERSIZE + 1352, 60, "E01SPI060");
    fields[41] = fieldE01SPI061 =
    new CharacterField(message, HEADERSIZE + 1412, 60, "E01SPI061");
    fields[42] = fieldE01SPI062 =
    new CharacterField(message, HEADERSIZE + 1472, 60, "E01SPI062");
    fields[43] = fieldE01SPI063 =
    new CharacterField(message, HEADERSIZE + 1532, 60, "E01SPI063");
    fields[44] = fieldE01SPI070 =
    new CharacterField(message, HEADERSIZE + 1592, 60, "E01SPI070");
    fields[45] = fieldE01SPI071 =
    new CharacterField(message, HEADERSIZE + 1652, 60, "E01SPI071");
    fields[46] = fieldE01SPI072 =
    new CharacterField(message, HEADERSIZE + 1712, 60, "E01SPI072");
    fields[47] = fieldE01SPI073 =
    new CharacterField(message, HEADERSIZE + 1772, 60, "E01SPI073");
    fields[48] = fieldE01SPI080 =
    new CharacterField(message, HEADERSIZE + 1832, 60, "E01SPI080");
    fields[49] = fieldE01SPI081 =
    new CharacterField(message, HEADERSIZE + 1892, 60, "E01SPI081");
    fields[50] = fieldE01SPI082 =
    new CharacterField(message, HEADERSIZE + 1952, 60, "E01SPI082");
    fields[51] = fieldE01SPI083 =
    new CharacterField(message, HEADERSIZE + 2012, 60, "E01SPI083");
    fields[52] = fieldE01SPI090 =
    new CharacterField(message, HEADERSIZE + 2072, 60, "E01SPI090");
    fields[53] = fieldE01SPI091 =
    new CharacterField(message, HEADERSIZE + 2132, 60, "E01SPI091");
    fields[54] = fieldE01SPI092 =
    new CharacterField(message, HEADERSIZE + 2192, 60, "E01SPI092");
    fields[55] = fieldE01SPI093 =
    new CharacterField(message, HEADERSIZE + 2252, 60, "E01SPI093");
    fields[56] = fieldE01SPI100 =
    new CharacterField(message, HEADERSIZE + 2312, 60, "E01SPI100");
    fields[57] = fieldE01SPI101 =
    new CharacterField(message, HEADERSIZE + 2372, 60, "E01SPI101");
    fields[58] = fieldE01SPI102 =
    new CharacterField(message, HEADERSIZE + 2432, 60, "E01SPI102");
    fields[59] = fieldE01SPI103 =
    new CharacterField(message, HEADERSIZE + 2492, 60, "E01SPI103");

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
  * Set field H01USERID using a String value.
  */
  public void setH01USERID(String newvalue)
  {
    fieldH01USERID.setString(newvalue);
  }

  /**
  * Get value of field H01USERID as a String.
  */
  public String getH01USERID()
  {
    return fieldH01USERID.getString();
  }

  /**
  * Set field H01PROGRM using a String value.
  */
  public void setH01PROGRM(String newvalue)
  {
    fieldH01PROGRM.setString(newvalue);
  }

  /**
  * Get value of field H01PROGRM as a String.
  */
  public String getH01PROGRM()
  {
    return fieldH01PROGRM.getString();
  }

  /**
  * Set field H01TIMSYS using a String value.
  */
  public void setH01TIMSYS(String newvalue)
  {
    fieldH01TIMSYS.setString(newvalue);
  }

  /**
  * Get value of field H01TIMSYS as a String.
  */
  public String getH01TIMSYS()
  {
    return fieldH01TIMSYS.getString();
  }

  /**
  * Set field H01SCRCOD using a String value.
  */
  public void setH01SCRCOD(String newvalue)
  {
    fieldH01SCRCOD.setString(newvalue);
  }

  /**
  * Get value of field H01SCRCOD as a String.
  */
  public String getH01SCRCOD()
  {
    return fieldH01SCRCOD.getString();
  }

  /**
  * Set field H01OPECOD using a String value.
  */
  public void setH01OPECOD(String newvalue)
  {
    fieldH01OPECOD.setString(newvalue);
  }

  /**
  * Get value of field H01OPECOD as a String.
  */
  public String getH01OPECOD()
  {
    return fieldH01OPECOD.getString();
  }

  /**
  * Set field H01FLGMAS using a String value.
  */
  public void setH01FLGMAS(String newvalue)
  {
    fieldH01FLGMAS.setString(newvalue);
  }

  /**
  * Get value of field H01FLGMAS as a String.
  */
  public String getH01FLGMAS()
  {
    return fieldH01FLGMAS.getString();
  }

  /**
  * Set field H01FLGWK1 using a String value.
  */
  public void setH01FLGWK1(String newvalue)
  {
    fieldH01FLGWK1.setString(newvalue);
  }

  /**
  * Get value of field H01FLGWK1 as a String.
  */
  public String getH01FLGWK1()
  {
    return fieldH01FLGWK1.getString();
  }

  /**
  * Set field H01FLGWK2 using a String value.
  */
  public void setH01FLGWK2(String newvalue)
  {
    fieldH01FLGWK2.setString(newvalue);
  }

  /**
  * Get value of field H01FLGWK2 as a String.
  */
  public String getH01FLGWK2()
  {
    return fieldH01FLGWK2.getString();
  }

  /**
  * Set field H01FLGWK3 using a String value.
  */
  public void setH01FLGWK3(String newvalue)
  {
    fieldH01FLGWK3.setString(newvalue);
  }

  /**
  * Get value of field H01FLGWK3 as a String.
  */
  public String getH01FLGWK3()
  {
    return fieldH01FLGWK3.getString();
  }

  /**
  * Set field E01SPITYP using a String value.
  */
  public void setE01SPITYP(String newvalue)
  {
    fieldE01SPITYP.setString(newvalue);
  }

  /**
  * Get value of field E01SPITYP as a String.
  */
  public String getE01SPITYP()
  {
    return fieldE01SPITYP.getString();
  }

  /**
  * Set field E01SPIACC using a String value.
  */
  public void setE01SPIACC(String newvalue)
  {
    fieldE01SPIACC.setString(newvalue);
  }

  /**
  * Get value of field E01SPIACC as a String.
  */
  public String getE01SPIACC()
  {
    return fieldE01SPIACC.getString();
  }

  /**
  * Set numeric field E01SPIACC using a BigDecimal value.
  */
  public void setE01SPIACC(BigDecimal newvalue)
  {
    fieldE01SPIACC.setBigDecimal(newvalue);
  }

  /**
  * Return value of numeric field E01SPIACC as a BigDecimal.
  */
  public BigDecimal getBigDecimalE01SPIACC()
  {
    return fieldE01SPIACC.getBigDecimal();
  }

  /**
  * Set field E01ACMACD using a String value.
  */
  public void setE01ACMACD(String newvalue)
  {
    fieldE01ACMACD.setString(newvalue);
  }

  /**
  * Get value of field E01ACMACD as a String.
  */
  public String getE01ACMACD()
  {
    return fieldE01ACMACD.getString();
  }

  /**
  * Set field E01ACMPRO using a String value.
  */
  public void setE01ACMPRO(String newvalue)
  {
    fieldE01ACMPRO.setString(newvalue);
  }

  /**
  * Get value of field E01ACMPRO as a String.
  */
  public String getE01ACMPRO()
  {
    return fieldE01ACMPRO.getString();
  }

  /**
  * Set field E01ACMCUN using a String value.
  */
  public void setE01ACMCUN(String newvalue)
  {
    fieldE01ACMCUN.setString(newvalue);
  }

  /**
  * Get value of field E01ACMCUN as a String.
  */
  public String getE01ACMCUN()
  {
    return fieldE01ACMCUN.getString();
  }

  /**
  * Set numeric field E01ACMCUN using a BigDecimal value.
  */
  public void setE01ACMCUN(BigDecimal newvalue)
  {
    fieldE01ACMCUN.setBigDecimal(newvalue);
  }

  /**
  * Return value of numeric field E01ACMCUN as a BigDecimal.
  */
  public BigDecimal getBigDecimalE01ACMCUN()
  {
    return fieldE01ACMCUN.getBigDecimal();
  }

  /**
  * Set field E01CUSNA1 using a String value.
  */
  public void setE01CUSNA1(String newvalue)
  {
    fieldE01CUSNA1.setString(newvalue);
  }

  /**
  * Get value of field E01CUSNA1 as a String.
  */
  public String getE01CUSNA1()
  {
    return fieldE01CUSNA1.getString();
  }

  /**
  * Set field E01ACMBNK using a String value.
  */
  public void setE01ACMBNK(String newvalue)
  {
    fieldE01ACMBNK.setString(newvalue);
  }

  /**
  * Get value of field E01ACMBNK as a String.
  */
  public String getE01ACMBNK()
  {
    return fieldE01ACMBNK.getString();
  }

  /**
  * Set field E01ACMBRN using a String value.
  */
  public void setE01ACMBRN(String newvalue)
  {
    fieldE01ACMBRN.setString(newvalue);
  }

  /**
  * Get value of field E01ACMBRN as a String.
  */
  public String getE01ACMBRN()
  {
    return fieldE01ACMBRN.getString();
  }

  /**
  * Set numeric field E01ACMBRN using a BigDecimal value.
  */
  public void setE01ACMBRN(BigDecimal newvalue)
  {
    fieldE01ACMBRN.setBigDecimal(newvalue);
  }

  /**
  * Return value of numeric field E01ACMBRN as a BigDecimal.
  */
  public BigDecimal getBigDecimalE01ACMBRN()
  {
    return fieldE01ACMBRN.getBigDecimal();
  }

  /**
  * Set field E01ACMCCY using a String value.
  */
  public void setE01ACMCCY(String newvalue)
  {
    fieldE01ACMCCY.setString(newvalue);
  }

  /**
  * Get value of field E01ACMCCY as a String.
  */
  public String getE01ACMCCY()
  {
    return fieldE01ACMCCY.getString();
  }

  /**
  * Set field E01ACMGLN using a String value.
  */
  public void setE01ACMGLN(String newvalue)
  {
    fieldE01ACMGLN.setString(newvalue);
  }

  /**
  * Get value of field E01ACMGLN as a String.
  */
  public String getE01ACMGLN()
  {
    return fieldE01ACMGLN.getString();
  }

  /**
  * Set numeric field E01ACMGLN using a BigDecimal value.
  */
  public void setE01ACMGLN(BigDecimal newvalue)
  {
    fieldE01ACMGLN.setBigDecimal(newvalue);
  }

  /**
  * Return value of numeric field E01ACMGLN as a BigDecimal.
  */
  public BigDecimal getBigDecimalE01ACMGLN()
  {
    return fieldE01ACMGLN.getBigDecimal();
  }

  /**
  * Set field E01ACMCCN using a String value.
  */
  public void setE01ACMCCN(String newvalue)
  {
    fieldE01ACMCCN.setString(newvalue);
  }

  /**
  * Get value of field E01ACMCCN as a String.
  */
  public String getE01ACMCCN()
  {
    return fieldE01ACMCCN.getString();
  }

  /**
  * Set numeric field E01ACMCCN using a BigDecimal value.
  */
  public void setE01ACMCCN(BigDecimal newvalue)
  {
    fieldE01ACMCCN.setBigDecimal(newvalue);
  }

  /**
  * Return value of numeric field E01ACMCCN as a BigDecimal.
  */
  public BigDecimal getBigDecimalE01ACMCCN()
  {
    return fieldE01ACMCCN.getBigDecimal();
  }

  /**
  * Set field E01SPI010 using a String value.
  */
  public void setE01SPI010(String newvalue)
  {
    fieldE01SPI010.setString(newvalue);
  }

  /**
  * Get value of field E01SPI010 as a String.
  */
  public String getE01SPI010()
  {
    return fieldE01SPI010.getString();
  }

  /**
  * Set field E01SPI011 using a String value.
  */
  public void setE01SPI011(String newvalue)
  {
    fieldE01SPI011.setString(newvalue);
  }

  /**
  * Get value of field E01SPI011 as a String.
  */
  public String getE01SPI011()
  {
    return fieldE01SPI011.getString();
  }

  /**
  * Set field E01SPI012 using a String value.
  */
  public void setE01SPI012(String newvalue)
  {
    fieldE01SPI012.setString(newvalue);
  }

  /**
  * Get value of field E01SPI012 as a String.
  */
  public String getE01SPI012()
  {
    return fieldE01SPI012.getString();
  }

  /**
  * Set field E01SPI013 using a String value.
  */
  public void setE01SPI013(String newvalue)
  {
    fieldE01SPI013.setString(newvalue);
  }

  /**
  * Get value of field E01SPI013 as a String.
  */
  public String getE01SPI013()
  {
    return fieldE01SPI013.getString();
  }

  /**
  * Set field E01SPI020 using a String value.
  */
  public void setE01SPI020(String newvalue)
  {
    fieldE01SPI020.setString(newvalue);
  }

  /**
  * Get value of field E01SPI020 as a String.
  */
  public String getE01SPI020()
  {
    return fieldE01SPI020.getString();
  }

  /**
  * Set field E01SPI021 using a String value.
  */
  public void setE01SPI021(String newvalue)
  {
    fieldE01SPI021.setString(newvalue);
  }

  /**
  * Get value of field E01SPI021 as a String.
  */
  public String getE01SPI021()
  {
    return fieldE01SPI021.getString();
  }

  /**
  * Set field E01SPI022 using a String value.
  */
  public void setE01SPI022(String newvalue)
  {
    fieldE01SPI022.setString(newvalue);
  }

  /**
  * Get value of field E01SPI022 as a String.
  */
  public String getE01SPI022()
  {
    return fieldE01SPI022.getString();
  }

  /**
  * Set field E01SPI023 using a String value.
  */
  public void setE01SPI023(String newvalue)
  {
    fieldE01SPI023.setString(newvalue);
  }

  /**
  * Get value of field E01SPI023 as a String.
  */
  public String getE01SPI023()
  {
    return fieldE01SPI023.getString();
  }

  /**
  * Set field E01SPI030 using a String value.
  */
  public void setE01SPI030(String newvalue)
  {
    fieldE01SPI030.setString(newvalue);
  }

  /**
  * Get value of field E01SPI030 as a String.
  */
  public String getE01SPI030()
  {
    return fieldE01SPI030.getString();
  }

  /**
  * Set field E01SPI031 using a String value.
  */
  public void setE01SPI031(String newvalue)
  {
    fieldE01SPI031.setString(newvalue);
  }

  /**
  * Get value of field E01SPI031 as a String.
  */
  public String getE01SPI031()
  {
    return fieldE01SPI031.getString();
  }

  /**
  * Set field E01SPI032 using a String value.
  */
  public void setE01SPI032(String newvalue)
  {
    fieldE01SPI032.setString(newvalue);
  }

  /**
  * Get value of field E01SPI032 as a String.
  */
  public String getE01SPI032()
  {
    return fieldE01SPI032.getString();
  }

  /**
  * Set field E01SPI033 using a String value.
  */
  public void setE01SPI033(String newvalue)
  {
    fieldE01SPI033.setString(newvalue);
  }

  /**
  * Get value of field E01SPI033 as a String.
  */
  public String getE01SPI033()
  {
    return fieldE01SPI033.getString();
  }

  /**
  * Set field E01SPI040 using a String value.
  */
  public void setE01SPI040(String newvalue)
  {
    fieldE01SPI040.setString(newvalue);
  }

  /**
  * Get value of field E01SPI040 as a String.
  */
  public String getE01SPI040()
  {
    return fieldE01SPI040.getString();
  }

  /**
  * Set field E01SPI041 using a String value.
  */
  public void setE01SPI041(String newvalue)
  {
    fieldE01SPI041.setString(newvalue);
  }

  /**
  * Get value of field E01SPI041 as a String.
  */
  public String getE01SPI041()
  {
    return fieldE01SPI041.getString();
  }

  /**
  * Set field E01SPI042 using a String value.
  */
  public void setE01SPI042(String newvalue)
  {
    fieldE01SPI042.setString(newvalue);
  }

  /**
  * Get value of field E01SPI042 as a String.
  */
  public String getE01SPI042()
  {
    return fieldE01SPI042.getString();
  }

  /**
  * Set field E01SPI043 using a String value.
  */
  public void setE01SPI043(String newvalue)
  {
    fieldE01SPI043.setString(newvalue);
  }

  /**
  * Get value of field E01SPI043 as a String.
  */
  public String getE01SPI043()
  {
    return fieldE01SPI043.getString();
  }

  /**
  * Set field E01SPI050 using a String value.
  */
  public void setE01SPI050(String newvalue)
  {
    fieldE01SPI050.setString(newvalue);
  }

  /**
  * Get value of field E01SPI050 as a String.
  */
  public String getE01SPI050()
  {
    return fieldE01SPI050.getString();
  }

  /**
  * Set field E01SPI051 using a String value.
  */
  public void setE01SPI051(String newvalue)
  {
    fieldE01SPI051.setString(newvalue);
  }

  /**
  * Get value of field E01SPI051 as a String.
  */
  public String getE01SPI051()
  {
    return fieldE01SPI051.getString();
  }

  /**
  * Set field E01SPI052 using a String value.
  */
  public void setE01SPI052(String newvalue)
  {
    fieldE01SPI052.setString(newvalue);
  }

  /**
  * Get value of field E01SPI052 as a String.
  */
  public String getE01SPI052()
  {
    return fieldE01SPI052.getString();
  }

  /**
  * Set field E01SPI053 using a String value.
  */
  public void setE01SPI053(String newvalue)
  {
    fieldE01SPI053.setString(newvalue);
  }

  /**
  * Get value of field E01SPI053 as a String.
  */
  public String getE01SPI053()
  {
    return fieldE01SPI053.getString();
  }

  /**
  * Set field E01SPI060 using a String value.
  */
  public void setE01SPI060(String newvalue)
  {
    fieldE01SPI060.setString(newvalue);
  }

  /**
  * Get value of field E01SPI060 as a String.
  */
  public String getE01SPI060()
  {
    return fieldE01SPI060.getString();
  }

  /**
  * Set field E01SPI061 using a String value.
  */
  public void setE01SPI061(String newvalue)
  {
    fieldE01SPI061.setString(newvalue);
  }

  /**
  * Get value of field E01SPI061 as a String.
  */
  public String getE01SPI061()
  {
    return fieldE01SPI061.getString();
  }

  /**
  * Set field E01SPI062 using a String value.
  */
  public void setE01SPI062(String newvalue)
  {
    fieldE01SPI062.setString(newvalue);
  }

  /**
  * Get value of field E01SPI062 as a String.
  */
  public String getE01SPI062()
  {
    return fieldE01SPI062.getString();
  }

  /**
  * Set field E01SPI063 using a String value.
  */
  public void setE01SPI063(String newvalue)
  {
    fieldE01SPI063.setString(newvalue);
  }

  /**
  * Get value of field E01SPI063 as a String.
  */
  public String getE01SPI063()
  {
    return fieldE01SPI063.getString();
  }

  /**
  * Set field E01SPI070 using a String value.
  */
  public void setE01SPI070(String newvalue)
  {
    fieldE01SPI070.setString(newvalue);
  }

  /**
  * Get value of field E01SPI070 as a String.
  */
  public String getE01SPI070()
  {
    return fieldE01SPI070.getString();
  }

  /**
  * Set field E01SPI071 using a String value.
  */
  public void setE01SPI071(String newvalue)
  {
    fieldE01SPI071.setString(newvalue);
  }

  /**
  * Get value of field E01SPI071 as a String.
  */
  public String getE01SPI071()
  {
    return fieldE01SPI071.getString();
  }

  /**
  * Set field E01SPI072 using a String value.
  */
  public void setE01SPI072(String newvalue)
  {
    fieldE01SPI072.setString(newvalue);
  }

  /**
  * Get value of field E01SPI072 as a String.
  */
  public String getE01SPI072()
  {
    return fieldE01SPI072.getString();
  }

  /**
  * Set field E01SPI073 using a String value.
  */
  public void setE01SPI073(String newvalue)
  {
    fieldE01SPI073.setString(newvalue);
  }

  /**
  * Get value of field E01SPI073 as a String.
  */
  public String getE01SPI073()
  {
    return fieldE01SPI073.getString();
  }

  /**
  * Set field E01SPI080 using a String value.
  */
  public void setE01SPI080(String newvalue)
  {
    fieldE01SPI080.setString(newvalue);
  }

  /**
  * Get value of field E01SPI080 as a String.
  */
  public String getE01SPI080()
  {
    return fieldE01SPI080.getString();
  }

  /**
  * Set field E01SPI081 using a String value.
  */
  public void setE01SPI081(String newvalue)
  {
    fieldE01SPI081.setString(newvalue);
  }

  /**
  * Get value of field E01SPI081 as a String.
  */
  public String getE01SPI081()
  {
    return fieldE01SPI081.getString();
  }

  /**
  * Set field E01SPI082 using a String value.
  */
  public void setE01SPI082(String newvalue)
  {
    fieldE01SPI082.setString(newvalue);
  }

  /**
  * Get value of field E01SPI082 as a String.
  */
  public String getE01SPI082()
  {
    return fieldE01SPI082.getString();
  }

  /**
  * Set field E01SPI083 using a String value.
  */
  public void setE01SPI083(String newvalue)
  {
    fieldE01SPI083.setString(newvalue);
  }

  /**
  * Get value of field E01SPI083 as a String.
  */
  public String getE01SPI083()
  {
    return fieldE01SPI083.getString();
  }

  /**
  * Set field E01SPI090 using a String value.
  */
  public void setE01SPI090(String newvalue)
  {
    fieldE01SPI090.setString(newvalue);
  }

  /**
  * Get value of field E01SPI090 as a String.
  */
  public String getE01SPI090()
  {
    return fieldE01SPI090.getString();
  }

  /**
  * Set field E01SPI091 using a String value.
  */
  public void setE01SPI091(String newvalue)
  {
    fieldE01SPI091.setString(newvalue);
  }

  /**
  * Get value of field E01SPI091 as a String.
  */
  public String getE01SPI091()
  {
    return fieldE01SPI091.getString();
  }

  /**
  * Set field E01SPI092 using a String value.
  */
  public void setE01SPI092(String newvalue)
  {
    fieldE01SPI092.setString(newvalue);
  }

  /**
  * Get value of field E01SPI092 as a String.
  */
  public String getE01SPI092()
  {
    return fieldE01SPI092.getString();
  }

  /**
  * Set field E01SPI093 using a String value.
  */
  public void setE01SPI093(String newvalue)
  {
    fieldE01SPI093.setString(newvalue);
  }

  /**
  * Get value of field E01SPI093 as a String.
  */
  public String getE01SPI093()
  {
    return fieldE01SPI093.getString();
  }

  /**
  * Set field E01SPI100 using a String value.
  */
  public void setE01SPI100(String newvalue)
  {
    fieldE01SPI100.setString(newvalue);
  }

  /**
  * Get value of field E01SPI100 as a String.
  */
  public String getE01SPI100()
  {
    return fieldE01SPI100.getString();
  }

  /**
  * Set field E01SPI101 using a String value.
  */
  public void setE01SPI101(String newvalue)
  {
    fieldE01SPI101.setString(newvalue);
  }

  /**
  * Get value of field E01SPI101 as a String.
  */
  public String getE01SPI101()
  {
    return fieldE01SPI101.getString();
  }

  /**
  * Set field E01SPI102 using a String value.
  */
  public void setE01SPI102(String newvalue)
  {
    fieldE01SPI102.setString(newvalue);
  }

  /**
  * Get value of field E01SPI102 as a String.
  */
  public String getE01SPI102()
  {
    return fieldE01SPI102.getString();
  }

  /**
  * Set field E01SPI103 using a String value.
  */
  public void setE01SPI103(String newvalue)
  {
    fieldE01SPI103.setString(newvalue);
  }

  /**
  * Get value of field E01SPI103 as a String.
  */
  public String getE01SPI103()
  {
    return fieldE01SPI103.getString();
  }

}
