package datapro.eibs.beans;

import datapro.eibs.sockets.*;
import java.io.*;
import java.math.*;

import java.util.*;

/**
* Class generated by AS/400 CRTCLASS command from EAPP00801 physical file definition.
* 
* File level identifier is 1040315173954.
* Record format level identifier is 5E3A4173BFDC4.
*/

public class EAPP00801Message extends MessageRecord
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
                                     "H01CANALV",
                                     "H01MODEAC",
                                     "E01ERRC01",
                                     "E01ERRC02",
                                     "E01ERRC03",
                                     "E01ERRC04",
                                     "E01ERRC05",
                                     "E01ERRD01",
                                     "E01ERRD02",
                                     "E01ERRD03",
                                     "E01ERRD04",
                                     "E01ERRD05",
                                     "E01ITIPCON",
                                     "E01IRUT",
                                     "E01INUMTAR",
                                     "E01IKEYTAR",
                                     "E01SQPRC",
                                     "E01SCPRC1",
                                     "E01SNPRC1",
                                     "E01SCPRC2",
                                     "E01SNPRC2",
                                     "E01SCPRC3",
                                     "E01SNPRC3",
                                     "E01SCPRC4",
                                     "E01SNPRC4",
                                     "E01SCPRC5",
                                     "E01SNPRC5",
                                     "E01SCPRC6",
                                     "E01SNPRC6",
                                     "E01SCPRC7",
                                     "E01SNPRC7",
                                     "E01SCPRC8",
                                     "E01SNPRC8",
                                     "E01SCPRC9",
                                     "E01SNPRC9",
                                     "E01SCPRC0",
                                     "E01SNPRC0",
                                     "E01SCPRCA",
                                     "E01SNPRCA",
                                     "E01SCPRCB",
                                     "E01SNPRCB"
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
                                   "H01CANALV",
                                   "H01MODEAC",
                                   "E01ERRC01",
                                   "E01ERRC02",
                                   "E01ERRC03",
                                   "E01ERRC04",
                                   "E01ERRC05",
                                   "E01ERRD01",
                                   "E01ERRD02",
                                   "E01ERRD03",
                                   "E01ERRD04",
                                   "E01ERRD05",
                                   "E01ITIPCON",
                                   "E01IRUT",
                                   "E01INUMTAR",
                                   "E01IKEYTAR",
                                   "E01SQPRC",
                                   "E01SCPRC1",
                                   "E01SNPRC1",
                                   "E01SCPRC2",
                                   "E01SNPRC2",
                                   "E01SCPRC3",
                                   "E01SNPRC3",
                                   "E01SCPRC4",
                                   "E01SNPRC4",
                                   "E01SCPRC5",
                                   "E01SNPRC5",
                                   "E01SCPRC6",
                                   "E01SNPRC6",
                                   "E01SCPRC7",
                                   "E01SNPRC7",
                                   "E01SCPRC8",
                                   "E01SNPRC8",
                                   "E01SCPRC9",
                                   "E01SNPRC9",
                                   "E01SCPRC0",
                                   "E01SNPRC0",
                                   "E01SCPRCA",
                                   "E01SNPRCA",
                                   "E01SCPRCB",
                                   "E01SNPRCB"
                                 };
  final static String fid = "1040315173954";
  final static String rid = "5E3A4173BFDC4";
  final static String fmtname = "EAPP00801";
  final int FIELDCOUNT = 50;
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
  private CharacterField fieldH01CANALV = null;
  private CharacterField fieldH01MODEAC = null;
  private DecimalField fieldE01ERRC01 = null;
  private DecimalField fieldE01ERRC02 = null;
  private DecimalField fieldE01ERRC03 = null;
  private DecimalField fieldE01ERRC04 = null;
  private DecimalField fieldE01ERRC05 = null;
  private CharacterField fieldE01ERRD01 = null;
  private CharacterField fieldE01ERRD02 = null;
  private CharacterField fieldE01ERRD03 = null;
  private CharacterField fieldE01ERRD04 = null;
  private CharacterField fieldE01ERRD05 = null;
  private CharacterField fieldE01ITIPCON = null;
  private DecimalField fieldE01IRUT = null;
  private CharacterField fieldE01INUMTAR = null;
  private CharacterField fieldE01IKEYTAR = null;
  private DecimalField fieldE01SQPRC = null;
  private CharacterField fieldE01SCPRC1 = null;
  private DecimalField fieldE01SNPRC1 = null;
  private CharacterField fieldE01SCPRC2 = null;
  private DecimalField fieldE01SNPRC2 = null;
  private CharacterField fieldE01SCPRC3 = null;
  private DecimalField fieldE01SNPRC3 = null;
  private CharacterField fieldE01SCPRC4 = null;
  private DecimalField fieldE01SNPRC4 = null;
  private CharacterField fieldE01SCPRC5 = null;
  private DecimalField fieldE01SNPRC5 = null;
  private CharacterField fieldE01SCPRC6 = null;
  private DecimalField fieldE01SNPRC6 = null;
  private CharacterField fieldE01SCPRC7 = null;
  private DecimalField fieldE01SNPRC7 = null;
  private CharacterField fieldE01SCPRC8 = null;
  private DecimalField fieldE01SNPRC8 = null;
  private CharacterField fieldE01SCPRC9 = null;
  private DecimalField fieldE01SNPRC9 = null;
  private CharacterField fieldE01SCPRC0 = null;
  private DecimalField fieldE01SNPRC0 = null;
  private CharacterField fieldE01SCPRCA = null;
  private DecimalField fieldE01SNPRCA = null;
  private CharacterField fieldE01SCPRCB = null;
  private DecimalField fieldE01SNPRCB = null;

  /**
  * Constructor for EAPP00801Message.
  */
  public EAPP00801Message()
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
    recordsize = 571;
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
    fields[9] = fieldH01CANALV =
    new CharacterField(message, HEADERSIZE + 42, 4, "H01CANALV");
    fields[10] = fieldH01MODEAC =
    new CharacterField(message, HEADERSIZE + 46, 1, "H01MODEAC");
    fields[11] = fieldE01ERRC01 =
    new DecimalField(message, HEADERSIZE + 47, 5, 0, "E01ERRC01");
    fields[12] = fieldE01ERRC02 =
    new DecimalField(message, HEADERSIZE + 52, 5, 0, "E01ERRC02");
    fields[13] = fieldE01ERRC03 =
    new DecimalField(message, HEADERSIZE + 57, 5, 0, "E01ERRC03");
    fields[14] = fieldE01ERRC04 =
    new DecimalField(message, HEADERSIZE + 62, 5, 0, "E01ERRC04");
    fields[15] = fieldE01ERRC05 =
    new DecimalField(message, HEADERSIZE + 67, 5, 0, "E01ERRC05");
    fields[16] = fieldE01ERRD01 =
    new CharacterField(message, HEADERSIZE + 72, 50, "E01ERRD01");
    fields[17] = fieldE01ERRD02 =
    new CharacterField(message, HEADERSIZE + 122, 50, "E01ERRD02");
    fields[18] = fieldE01ERRD03 =
    new CharacterField(message, HEADERSIZE + 172, 50, "E01ERRD03");
    fields[19] = fieldE01ERRD04 =
    new CharacterField(message, HEADERSIZE + 222, 50, "E01ERRD04");
    fields[20] = fieldE01ERRD05 =
    new CharacterField(message, HEADERSIZE + 272, 50, "E01ERRD05");
    fields[21] = fieldE01ITIPCON =
    new CharacterField(message, HEADERSIZE + 322, 1, "E01ITIPCON");
    fields[22] = fieldE01IRUT =
    new DecimalField(message, HEADERSIZE + 323, 10, 0, "E01IRUT");
    fields[23] = fieldE01INUMTAR =
    new CharacterField(message, HEADERSIZE + 333, 19, "E01INUMTAR");
    fields[24] = fieldE01IKEYTAR =
    new CharacterField(message, HEADERSIZE + 352, 12, "E01IKEYTAR");
    fields[25] = fieldE01SQPRC =
    new DecimalField(message, HEADERSIZE + 364, 3, 0, "E01SQPRC");
    fields[26] = fieldE01SCPRC1 =
    new CharacterField(message, HEADERSIZE + 367, 4, "E01SCPRC1");
    fields[27] = fieldE01SNPRC1 =
    new DecimalField(message, HEADERSIZE + 371, 13, 0, "E01SNPRC1");
    fields[28] = fieldE01SCPRC2 =
    new CharacterField(message, HEADERSIZE + 384, 4, "E01SCPRC2");
    fields[29] = fieldE01SNPRC2 =
    new DecimalField(message, HEADERSIZE + 388, 13, 0, "E01SNPRC2");
    fields[30] = fieldE01SCPRC3 =
    new CharacterField(message, HEADERSIZE + 401, 4, "E01SCPRC3");
    fields[31] = fieldE01SNPRC3 =
    new DecimalField(message, HEADERSIZE + 405, 13, 0, "E01SNPRC3");
    fields[32] = fieldE01SCPRC4 =
    new CharacterField(message, HEADERSIZE + 418, 4, "E01SCPRC4");
    fields[33] = fieldE01SNPRC4 =
    new DecimalField(message, HEADERSIZE + 422, 13, 0, "E01SNPRC4");
    fields[34] = fieldE01SCPRC5 =
    new CharacterField(message, HEADERSIZE + 435, 4, "E01SCPRC5");
    fields[35] = fieldE01SNPRC5 =
    new DecimalField(message, HEADERSIZE + 439, 13, 0, "E01SNPRC5");
    fields[36] = fieldE01SCPRC6 =
    new CharacterField(message, HEADERSIZE + 452, 4, "E01SCPRC6");
    fields[37] = fieldE01SNPRC6 =
    new DecimalField(message, HEADERSIZE + 456, 13, 0, "E01SNPRC6");
    fields[38] = fieldE01SCPRC7 =
    new CharacterField(message, HEADERSIZE + 469, 4, "E01SCPRC7");
    fields[39] = fieldE01SNPRC7 =
    new DecimalField(message, HEADERSIZE + 473, 13, 0, "E01SNPRC7");
    fields[40] = fieldE01SCPRC8 =
    new CharacterField(message, HEADERSIZE + 486, 4, "E01SCPRC8");
    fields[41] = fieldE01SNPRC8 =
    new DecimalField(message, HEADERSIZE + 490, 13, 0, "E01SNPRC8");
    fields[42] = fieldE01SCPRC9 =
    new CharacterField(message, HEADERSIZE + 503, 4, "E01SCPRC9");
    fields[43] = fieldE01SNPRC9 =
    new DecimalField(message, HEADERSIZE + 507, 13, 0, "E01SNPRC9");
    fields[44] = fieldE01SCPRC0 =
    new CharacterField(message, HEADERSIZE + 520, 4, "E01SCPRC0");
    fields[45] = fieldE01SNPRC0 =
    new DecimalField(message, HEADERSIZE + 524, 13, 0, "E01SNPRC0");
    fields[46] = fieldE01SCPRCA =
    new CharacterField(message, HEADERSIZE + 537, 4, "E01SCPRCA");
    fields[47] = fieldE01SNPRCA =
    new DecimalField(message, HEADERSIZE + 541, 13, 0, "E01SNPRCA");
    fields[48] = fieldE01SCPRCB =
    new CharacterField(message, HEADERSIZE + 554, 4, "E01SCPRCB");
    fields[49] = fieldE01SNPRCB =
    new DecimalField(message, HEADERSIZE + 558, 13, 0, "E01SNPRCB");

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
  * Set field H01CANALV using a String value.
  */
  public void setH01CANALV(String newvalue)
  {
    fieldH01CANALV.setString(newvalue);
  }

  /**
  * Get value of field H01CANALV as a String.
  */
  public String getH01CANALV()
  {
    return fieldH01CANALV.getString();
  }

  /**
  * Set field H01MODEAC using a String value.
  */
  public void setH01MODEAC(String newvalue)
  {
    fieldH01MODEAC.setString(newvalue);
  }

  /**
  * Get value of field H01MODEAC as a String.
  */
  public String getH01MODEAC()
  {
    return fieldH01MODEAC.getString();
  }

  /**
  * Set field E01ERRC01 using a String value.
  */
  public void setE01ERRC01(String newvalue)
  {
    fieldE01ERRC01.setString(newvalue);
  }

  /**
  * Get value of field E01ERRC01 as a String.
  */
  public String getE01ERRC01()
  {
    return fieldE01ERRC01.getString();
  }

  /**
  * Set numeric field E01ERRC01 using a BigDecimal value.
  */
  public void setE01ERRC01(BigDecimal newvalue)
  {
    fieldE01ERRC01.setBigDecimal(newvalue);
  }

  /**
  * Return value of numeric field E01ERRC01 as a BigDecimal.
  */
  public BigDecimal getBigDecimalE01ERRC01()
  {
    return fieldE01ERRC01.getBigDecimal();
  }

  /**
  * Set field E01ERRC02 using a String value.
  */
  public void setE01ERRC02(String newvalue)
  {
    fieldE01ERRC02.setString(newvalue);
  }

  /**
  * Get value of field E01ERRC02 as a String.
  */
  public String getE01ERRC02()
  {
    return fieldE01ERRC02.getString();
  }

  /**
  * Set numeric field E01ERRC02 using a BigDecimal value.
  */
  public void setE01ERRC02(BigDecimal newvalue)
  {
    fieldE01ERRC02.setBigDecimal(newvalue);
  }

  /**
  * Return value of numeric field E01ERRC02 as a BigDecimal.
  */
  public BigDecimal getBigDecimalE01ERRC02()
  {
    return fieldE01ERRC02.getBigDecimal();
  }

  /**
  * Set field E01ERRC03 using a String value.
  */
  public void setE01ERRC03(String newvalue)
  {
    fieldE01ERRC03.setString(newvalue);
  }

  /**
  * Get value of field E01ERRC03 as a String.
  */
  public String getE01ERRC03()
  {
    return fieldE01ERRC03.getString();
  }

  /**
  * Set numeric field E01ERRC03 using a BigDecimal value.
  */
  public void setE01ERRC03(BigDecimal newvalue)
  {
    fieldE01ERRC03.setBigDecimal(newvalue);
  }

  /**
  * Return value of numeric field E01ERRC03 as a BigDecimal.
  */
  public BigDecimal getBigDecimalE01ERRC03()
  {
    return fieldE01ERRC03.getBigDecimal();
  }

  /**
  * Set field E01ERRC04 using a String value.
  */
  public void setE01ERRC04(String newvalue)
  {
    fieldE01ERRC04.setString(newvalue);
  }

  /**
  * Get value of field E01ERRC04 as a String.
  */
  public String getE01ERRC04()
  {
    return fieldE01ERRC04.getString();
  }

  /**
  * Set numeric field E01ERRC04 using a BigDecimal value.
  */
  public void setE01ERRC04(BigDecimal newvalue)
  {
    fieldE01ERRC04.setBigDecimal(newvalue);
  }

  /**
  * Return value of numeric field E01ERRC04 as a BigDecimal.
  */
  public BigDecimal getBigDecimalE01ERRC04()
  {
    return fieldE01ERRC04.getBigDecimal();
  }

  /**
  * Set field E01ERRC05 using a String value.
  */
  public void setE01ERRC05(String newvalue)
  {
    fieldE01ERRC05.setString(newvalue);
  }

  /**
  * Get value of field E01ERRC05 as a String.
  */
  public String getE01ERRC05()
  {
    return fieldE01ERRC05.getString();
  }

  /**
  * Set numeric field E01ERRC05 using a BigDecimal value.
  */
  public void setE01ERRC05(BigDecimal newvalue)
  {
    fieldE01ERRC05.setBigDecimal(newvalue);
  }

  /**
  * Return value of numeric field E01ERRC05 as a BigDecimal.
  */
  public BigDecimal getBigDecimalE01ERRC05()
  {
    return fieldE01ERRC05.getBigDecimal();
  }

  /**
  * Set field E01ERRD01 using a String value.
  */
  public void setE01ERRD01(String newvalue)
  {
    fieldE01ERRD01.setString(newvalue);
  }

  /**
  * Get value of field E01ERRD01 as a String.
  */
  public String getE01ERRD01()
  {
    return fieldE01ERRD01.getString();
  }

  /**
  * Set field E01ERRD02 using a String value.
  */
  public void setE01ERRD02(String newvalue)
  {
    fieldE01ERRD02.setString(newvalue);
  }

  /**
  * Get value of field E01ERRD02 as a String.
  */
  public String getE01ERRD02()
  {
    return fieldE01ERRD02.getString();
  }

  /**
  * Set field E01ERRD03 using a String value.
  */
  public void setE01ERRD03(String newvalue)
  {
    fieldE01ERRD03.setString(newvalue);
  }

  /**
  * Get value of field E01ERRD03 as a String.
  */
  public String getE01ERRD03()
  {
    return fieldE01ERRD03.getString();
  }

  /**
  * Set field E01ERRD04 using a String value.
  */
  public void setE01ERRD04(String newvalue)
  {
    fieldE01ERRD04.setString(newvalue);
  }

  /**
  * Get value of field E01ERRD04 as a String.
  */
  public String getE01ERRD04()
  {
    return fieldE01ERRD04.getString();
  }

  /**
  * Set field E01ERRD05 using a String value.
  */
  public void setE01ERRD05(String newvalue)
  {
    fieldE01ERRD05.setString(newvalue);
  }

  /**
  * Get value of field E01ERRD05 as a String.
  */
  public String getE01ERRD05()
  {
    return fieldE01ERRD05.getString();
  }

  /**
  * Set field E01ITIPCON using a String value.
  */
  public void setE01ITIPCON(String newvalue)
  {
    fieldE01ITIPCON.setString(newvalue);
  }

  /**
  * Get value of field E01ITIPCON as a String.
  */
  public String getE01ITIPCON()
  {
    return fieldE01ITIPCON.getString();
  }

  /**
  * Set field E01IRUT using a String value.
  */
  public void setE01IRUT(String newvalue)
  {
    fieldE01IRUT.setString(newvalue);
  }

  /**
  * Get value of field E01IRUT as a String.
  */
  public String getE01IRUT()
  {
    return fieldE01IRUT.getString();
  }

  /**
  * Set numeric field E01IRUT using a BigDecimal value.
  */
  public void setE01IRUT(BigDecimal newvalue)
  {
    fieldE01IRUT.setBigDecimal(newvalue);
  }

  /**
  * Return value of numeric field E01IRUT as a BigDecimal.
  */
  public BigDecimal getBigDecimalE01IRUT()
  {
    return fieldE01IRUT.getBigDecimal();
  }

  /**
  * Set field E01INUMTAR using a String value.
  */
  public void setE01INUMTAR(String newvalue)
  {
    fieldE01INUMTAR.setString(newvalue);
  }

  /**
  * Get value of field E01INUMTAR as a String.
  */
  public String getE01INUMTAR()
  {
    return fieldE01INUMTAR.getString();
  }

  /**
  * Set field E01IKEYTAR using a String value.
  */
  public void setE01IKEYTAR(String newvalue)
  {
    fieldE01IKEYTAR.setString(newvalue);
  }

  /**
  * Get value of field E01IKEYTAR as a String.
  */
  public String getE01IKEYTAR()
  {
    return fieldE01IKEYTAR.getString();
  }

  /**
  * Set field E01SQPRC using a String value.
  */
  public void setE01SQPRC(String newvalue)
  {
    fieldE01SQPRC.setString(newvalue);
  }

  /**
  * Get value of field E01SQPRC as a String.
  */
  public String getE01SQPRC()
  {
    return fieldE01SQPRC.getString();
  }

  /**
  * Set numeric field E01SQPRC using a BigDecimal value.
  */
  public void setE01SQPRC(BigDecimal newvalue)
  {
    fieldE01SQPRC.setBigDecimal(newvalue);
  }

  /**
  * Return value of numeric field E01SQPRC as a BigDecimal.
  */
  public BigDecimal getBigDecimalE01SQPRC()
  {
    return fieldE01SQPRC.getBigDecimal();
  }

  /**
  * Set field E01SCPRC1 using a String value.
  */
  public void setE01SCPRC1(String newvalue)
  {
    fieldE01SCPRC1.setString(newvalue);
  }

  /**
  * Get value of field E01SCPRC1 as a String.
  */
  public String getE01SCPRC1()
  {
    return fieldE01SCPRC1.getString();
  }

  /**
  * Set field E01SNPRC1 using a String value.
  */
  public void setE01SNPRC1(String newvalue)
  {
    fieldE01SNPRC1.setString(newvalue);
  }

  /**
  * Get value of field E01SNPRC1 as a String.
  */
  public String getE01SNPRC1()
  {
    return fieldE01SNPRC1.getString();
  }

  /**
  * Set numeric field E01SNPRC1 using a BigDecimal value.
  */
  public void setE01SNPRC1(BigDecimal newvalue)
  {
    fieldE01SNPRC1.setBigDecimal(newvalue);
  }

  /**
  * Return value of numeric field E01SNPRC1 as a BigDecimal.
  */
  public BigDecimal getBigDecimalE01SNPRC1()
  {
    return fieldE01SNPRC1.getBigDecimal();
  }

  /**
  * Set field E01SCPRC2 using a String value.
  */
  public void setE01SCPRC2(String newvalue)
  {
    fieldE01SCPRC2.setString(newvalue);
  }

  /**
  * Get value of field E01SCPRC2 as a String.
  */
  public String getE01SCPRC2()
  {
    return fieldE01SCPRC2.getString();
  }

  /**
  * Set field E01SNPRC2 using a String value.
  */
  public void setE01SNPRC2(String newvalue)
  {
    fieldE01SNPRC2.setString(newvalue);
  }

  /**
  * Get value of field E01SNPRC2 as a String.
  */
  public String getE01SNPRC2()
  {
    return fieldE01SNPRC2.getString();
  }

  /**
  * Set numeric field E01SNPRC2 using a BigDecimal value.
  */
  public void setE01SNPRC2(BigDecimal newvalue)
  {
    fieldE01SNPRC2.setBigDecimal(newvalue);
  }

  /**
  * Return value of numeric field E01SNPRC2 as a BigDecimal.
  */
  public BigDecimal getBigDecimalE01SNPRC2()
  {
    return fieldE01SNPRC2.getBigDecimal();
  }

  /**
  * Set field E01SCPRC3 using a String value.
  */
  public void setE01SCPRC3(String newvalue)
  {
    fieldE01SCPRC3.setString(newvalue);
  }

  /**
  * Get value of field E01SCPRC3 as a String.
  */
  public String getE01SCPRC3()
  {
    return fieldE01SCPRC3.getString();
  }

  /**
  * Set field E01SNPRC3 using a String value.
  */
  public void setE01SNPRC3(String newvalue)
  {
    fieldE01SNPRC3.setString(newvalue);
  }

  /**
  * Get value of field E01SNPRC3 as a String.
  */
  public String getE01SNPRC3()
  {
    return fieldE01SNPRC3.getString();
  }

  /**
  * Set numeric field E01SNPRC3 using a BigDecimal value.
  */
  public void setE01SNPRC3(BigDecimal newvalue)
  {
    fieldE01SNPRC3.setBigDecimal(newvalue);
  }

  /**
  * Return value of numeric field E01SNPRC3 as a BigDecimal.
  */
  public BigDecimal getBigDecimalE01SNPRC3()
  {
    return fieldE01SNPRC3.getBigDecimal();
  }

  /**
  * Set field E01SCPRC4 using a String value.
  */
  public void setE01SCPRC4(String newvalue)
  {
    fieldE01SCPRC4.setString(newvalue);
  }

  /**
  * Get value of field E01SCPRC4 as a String.
  */
  public String getE01SCPRC4()
  {
    return fieldE01SCPRC4.getString();
  }

  /**
  * Set field E01SNPRC4 using a String value.
  */
  public void setE01SNPRC4(String newvalue)
  {
    fieldE01SNPRC4.setString(newvalue);
  }

  /**
  * Get value of field E01SNPRC4 as a String.
  */
  public String getE01SNPRC4()
  {
    return fieldE01SNPRC4.getString();
  }

  /**
  * Set numeric field E01SNPRC4 using a BigDecimal value.
  */
  public void setE01SNPRC4(BigDecimal newvalue)
  {
    fieldE01SNPRC4.setBigDecimal(newvalue);
  }

  /**
  * Return value of numeric field E01SNPRC4 as a BigDecimal.
  */
  public BigDecimal getBigDecimalE01SNPRC4()
  {
    return fieldE01SNPRC4.getBigDecimal();
  }

  /**
  * Set field E01SCPRC5 using a String value.
  */
  public void setE01SCPRC5(String newvalue)
  {
    fieldE01SCPRC5.setString(newvalue);
  }

  /**
  * Get value of field E01SCPRC5 as a String.
  */
  public String getE01SCPRC5()
  {
    return fieldE01SCPRC5.getString();
  }

  /**
  * Set field E01SNPRC5 using a String value.
  */
  public void setE01SNPRC5(String newvalue)
  {
    fieldE01SNPRC5.setString(newvalue);
  }

  /**
  * Get value of field E01SNPRC5 as a String.
  */
  public String getE01SNPRC5()
  {
    return fieldE01SNPRC5.getString();
  }

  /**
  * Set numeric field E01SNPRC5 using a BigDecimal value.
  */
  public void setE01SNPRC5(BigDecimal newvalue)
  {
    fieldE01SNPRC5.setBigDecimal(newvalue);
  }

  /**
  * Return value of numeric field E01SNPRC5 as a BigDecimal.
  */
  public BigDecimal getBigDecimalE01SNPRC5()
  {
    return fieldE01SNPRC5.getBigDecimal();
  }

  /**
  * Set field E01SCPRC6 using a String value.
  */
  public void setE01SCPRC6(String newvalue)
  {
    fieldE01SCPRC6.setString(newvalue);
  }

  /**
  * Get value of field E01SCPRC6 as a String.
  */
  public String getE01SCPRC6()
  {
    return fieldE01SCPRC6.getString();
  }

  /**
  * Set field E01SNPRC6 using a String value.
  */
  public void setE01SNPRC6(String newvalue)
  {
    fieldE01SNPRC6.setString(newvalue);
  }

  /**
  * Get value of field E01SNPRC6 as a String.
  */
  public String getE01SNPRC6()
  {
    return fieldE01SNPRC6.getString();
  }

  /**
  * Set numeric field E01SNPRC6 using a BigDecimal value.
  */
  public void setE01SNPRC6(BigDecimal newvalue)
  {
    fieldE01SNPRC6.setBigDecimal(newvalue);
  }

  /**
  * Return value of numeric field E01SNPRC6 as a BigDecimal.
  */
  public BigDecimal getBigDecimalE01SNPRC6()
  {
    return fieldE01SNPRC6.getBigDecimal();
  }

  /**
  * Set field E01SCPRC7 using a String value.
  */
  public void setE01SCPRC7(String newvalue)
  {
    fieldE01SCPRC7.setString(newvalue);
  }

  /**
  * Get value of field E01SCPRC7 as a String.
  */
  public String getE01SCPRC7()
  {
    return fieldE01SCPRC7.getString();
  }

  /**
  * Set field E01SNPRC7 using a String value.
  */
  public void setE01SNPRC7(String newvalue)
  {
    fieldE01SNPRC7.setString(newvalue);
  }

  /**
  * Get value of field E01SNPRC7 as a String.
  */
  public String getE01SNPRC7()
  {
    return fieldE01SNPRC7.getString();
  }

  /**
  * Set numeric field E01SNPRC7 using a BigDecimal value.
  */
  public void setE01SNPRC7(BigDecimal newvalue)
  {
    fieldE01SNPRC7.setBigDecimal(newvalue);
  }

  /**
  * Return value of numeric field E01SNPRC7 as a BigDecimal.
  */
  public BigDecimal getBigDecimalE01SNPRC7()
  {
    return fieldE01SNPRC7.getBigDecimal();
  }

  /**
  * Set field E01SCPRC8 using a String value.
  */
  public void setE01SCPRC8(String newvalue)
  {
    fieldE01SCPRC8.setString(newvalue);
  }

  /**
  * Get value of field E01SCPRC8 as a String.
  */
  public String getE01SCPRC8()
  {
    return fieldE01SCPRC8.getString();
  }

  /**
  * Set field E01SNPRC8 using a String value.
  */
  public void setE01SNPRC8(String newvalue)
  {
    fieldE01SNPRC8.setString(newvalue);
  }

  /**
  * Get value of field E01SNPRC8 as a String.
  */
  public String getE01SNPRC8()
  {
    return fieldE01SNPRC8.getString();
  }

  /**
  * Set numeric field E01SNPRC8 using a BigDecimal value.
  */
  public void setE01SNPRC8(BigDecimal newvalue)
  {
    fieldE01SNPRC8.setBigDecimal(newvalue);
  }

  /**
  * Return value of numeric field E01SNPRC8 as a BigDecimal.
  */
  public BigDecimal getBigDecimalE01SNPRC8()
  {
    return fieldE01SNPRC8.getBigDecimal();
  }

  /**
  * Set field E01SCPRC9 using a String value.
  */
  public void setE01SCPRC9(String newvalue)
  {
    fieldE01SCPRC9.setString(newvalue);
  }

  /**
  * Get value of field E01SCPRC9 as a String.
  */
  public String getE01SCPRC9()
  {
    return fieldE01SCPRC9.getString();
  }

  /**
  * Set field E01SNPRC9 using a String value.
  */
  public void setE01SNPRC9(String newvalue)
  {
    fieldE01SNPRC9.setString(newvalue);
  }

  /**
  * Get value of field E01SNPRC9 as a String.
  */
  public String getE01SNPRC9()
  {
    return fieldE01SNPRC9.getString();
  }

  /**
  * Set numeric field E01SNPRC9 using a BigDecimal value.
  */
  public void setE01SNPRC9(BigDecimal newvalue)
  {
    fieldE01SNPRC9.setBigDecimal(newvalue);
  }

  /**
  * Return value of numeric field E01SNPRC9 as a BigDecimal.
  */
  public BigDecimal getBigDecimalE01SNPRC9()
  {
    return fieldE01SNPRC9.getBigDecimal();
  }

  /**
  * Set field E01SCPRC0 using a String value.
  */
  public void setE01SCPRC0(String newvalue)
  {
    fieldE01SCPRC0.setString(newvalue);
  }

  /**
  * Get value of field E01SCPRC0 as a String.
  */
  public String getE01SCPRC0()
  {
    return fieldE01SCPRC0.getString();
  }

  /**
  * Set field E01SNPRC0 using a String value.
  */
  public void setE01SNPRC0(String newvalue)
  {
    fieldE01SNPRC0.setString(newvalue);
  }

  /**
  * Get value of field E01SNPRC0 as a String.
  */
  public String getE01SNPRC0()
  {
    return fieldE01SNPRC0.getString();
  }

  /**
  * Set numeric field E01SNPRC0 using a BigDecimal value.
  */
  public void setE01SNPRC0(BigDecimal newvalue)
  {
    fieldE01SNPRC0.setBigDecimal(newvalue);
  }

  /**
  * Return value of numeric field E01SNPRC0 as a BigDecimal.
  */
  public BigDecimal getBigDecimalE01SNPRC0()
  {
    return fieldE01SNPRC0.getBigDecimal();
  }

  /**
  * Set field E01SCPRCA using a String value.
  */
  public void setE01SCPRCA(String newvalue)
  {
    fieldE01SCPRCA.setString(newvalue);
  }

  /**
  * Get value of field E01SCPRCA as a String.
  */
  public String getE01SCPRCA()
  {
    return fieldE01SCPRCA.getString();
  }

  /**
  * Set field E01SNPRCA using a String value.
  */
  public void setE01SNPRCA(String newvalue)
  {
    fieldE01SNPRCA.setString(newvalue);
  }

  /**
  * Get value of field E01SNPRCA as a String.
  */
  public String getE01SNPRCA()
  {
    return fieldE01SNPRCA.getString();
  }

  /**
  * Set numeric field E01SNPRCA using a BigDecimal value.
  */
  public void setE01SNPRCA(BigDecimal newvalue)
  {
    fieldE01SNPRCA.setBigDecimal(newvalue);
  }

  /**
  * Return value of numeric field E01SNPRCA as a BigDecimal.
  */
  public BigDecimal getBigDecimalE01SNPRCA()
  {
    return fieldE01SNPRCA.getBigDecimal();
  }

  /**
  * Set field E01SCPRCB using a String value.
  */
  public void setE01SCPRCB(String newvalue)
  {
    fieldE01SCPRCB.setString(newvalue);
  }

  /**
  * Get value of field E01SCPRCB as a String.
  */
  public String getE01SCPRCB()
  {
    return fieldE01SCPRCB.getString();
  }

  /**
  * Set field E01SNPRCB using a String value.
  */
  public void setE01SNPRCB(String newvalue)
  {
    fieldE01SNPRCB.setString(newvalue);
  }

  /**
  * Get value of field E01SNPRCB as a String.
  */
  public String getE01SNPRCB()
  {
    return fieldE01SNPRCB.getString();
  }

  /**
  * Set numeric field E01SNPRCB using a BigDecimal value.
  */
  public void setE01SNPRCB(BigDecimal newvalue)
  {
    fieldE01SNPRCB.setBigDecimal(newvalue);
  }

  /**
  * Return value of numeric field E01SNPRCB as a BigDecimal.
  */
  public BigDecimal getBigDecimalE01SNPRCB()
  {
    return fieldE01SNPRCB.getBigDecimal();
  }

}
