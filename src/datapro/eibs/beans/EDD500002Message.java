package datapro.eibs.beans;

import datapro.eibs.sockets.*;
import java.io.*;
import java.math.*;

import java.util.*;

/**
* Class generated by AS/400 CRTCLASS command from EDD500002 physical file definition.
* 
* File level identifier is 1040329124741.
* Record format level identifier is 54922C7BA58D3.
*/

public class EDD500002Message extends MessageRecord
{
  final static String fldnames[] = {
                                     "H02USERID",
                                     "H02PROGRM",
                                     "H02TIMSYS",
                                     "H02SCRCOD",
                                     "H02OPECOD",
                                     "H02FLGMAS",
                                     "H02FLGWK1",
                                     "H02FLGWK2",
                                     "H02FLGWK3",
                                     "H02CANALV",
                                     "H02MODEAC",
                                     "E02ERRC01",
                                     "E02ERRC02",
                                     "E02ERRC03",
                                     "E02ERRC04",
                                     "E02ERRC05",
                                     "E02ERRD01",
                                     "E02ERRD02",
                                     "E02ERRD03",
                                     "E02ERRD04",
                                     "E02ERRD05",
                                     "E02ACMACC",
                                     "E02ACMAST",
                                     "E02ACMUIN",
                                     "E02ACMSDC",
                                     "E02CODCAN"
                                   };
  final static String tnames[] = {
                                   "H02USERID",
                                   "H02PROGRM",
                                   "H02TIMSYS",
                                   "H02SCRCOD",
                                   "H02OPECOD",
                                   "H02FLGMAS",
                                   "H02FLGWK1",
                                   "H02FLGWK2",
                                   "H02FLGWK3",
                                   "H02CANALV",
                                   "H02MODEAC",
                                   "E02ERRC01",
                                   "E02ERRC02",
                                   "E02ERRC03",
                                   "E02ERRC04",
                                   "E02ERRC05",
                                   "E02ERRD01",
                                   "E02ERRD02",
                                   "E02ERRD03",
                                   "E02ERRD04",
                                   "E02ERRD05",
                                   "E02ACMACC",
                                   "E02ACMAST",
                                   "E02ACMUIN",
                                   "E02ACMSDC",
                                   "E02CODCAN"
                                 };
  final static String fid = "1040329124741";
  final static String rid = "54922C7BA58D3";
  final static String fmtname = "EDD500002";
  final int FIELDCOUNT = 26;
  private static Hashtable tlookup = new Hashtable();
  private CharacterField fieldH02USERID = null;
  private CharacterField fieldH02PROGRM = null;
  private CharacterField fieldH02TIMSYS = null;
  private CharacterField fieldH02SCRCOD = null;
  private CharacterField fieldH02OPECOD = null;
  private CharacterField fieldH02FLGMAS = null;
  private CharacterField fieldH02FLGWK1 = null;
  private CharacterField fieldH02FLGWK2 = null;
  private CharacterField fieldH02FLGWK3 = null;
  private CharacterField fieldH02CANALV = null;
  private CharacterField fieldH02MODEAC = null;
  private DecimalField fieldE02ERRC01 = null;
  private DecimalField fieldE02ERRC02 = null;
  private DecimalField fieldE02ERRC03 = null;
  private DecimalField fieldE02ERRC04 = null;
  private DecimalField fieldE02ERRC05 = null;
  private CharacterField fieldE02ERRD01 = null;
  private CharacterField fieldE02ERRD02 = null;
  private CharacterField fieldE02ERRD03 = null;
  private CharacterField fieldE02ERRD04 = null;
  private CharacterField fieldE02ERRD05 = null;
  private DecimalField fieldE02ACMACC = null;
  private CharacterField fieldE02ACMAST = null;
  private CharacterField fieldE02ACMUIN = null;
  private DecimalField fieldE02ACMSDC = null;
  private CharacterField fieldE02CODCAN = null;

  /**
  * Constructor for EDD500002Message.
  */
  public EDD500002Message()
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
    recordsize = 351;
    fileid = fid;
    recordid = rid;
    message = new byte[getByteLength()];
    formatname = fmtname;
    fieldnames = fldnames;
    tagnames = tnames;
    fields = new MessageField[FIELDCOUNT];

    fields[0] = fieldH02USERID =
    new CharacterField(message, HEADERSIZE + 0, 10, "H02USERID");
    fields[1] = fieldH02PROGRM =
    new CharacterField(message, HEADERSIZE + 10, 10, "H02PROGRM");
    fields[2] = fieldH02TIMSYS =
    new CharacterField(message, HEADERSIZE + 20, 12, "H02TIMSYS");
    fields[3] = fieldH02SCRCOD =
    new CharacterField(message, HEADERSIZE + 32, 2, "H02SCRCOD");
    fields[4] = fieldH02OPECOD =
    new CharacterField(message, HEADERSIZE + 34, 4, "H02OPECOD");
    fields[5] = fieldH02FLGMAS =
    new CharacterField(message, HEADERSIZE + 38, 1, "H02FLGMAS");
    fields[6] = fieldH02FLGWK1 =
    new CharacterField(message, HEADERSIZE + 39, 1, "H02FLGWK1");
    fields[7] = fieldH02FLGWK2 =
    new CharacterField(message, HEADERSIZE + 40, 1, "H02FLGWK2");
    fields[8] = fieldH02FLGWK3 =
    new CharacterField(message, HEADERSIZE + 41, 1, "H02FLGWK3");
    fields[9] = fieldH02CANALV =
    new CharacterField(message, HEADERSIZE + 42, 4, "H02CANALV");
    fields[10] = fieldH02MODEAC =
    new CharacterField(message, HEADERSIZE + 46, 1, "H02MODEAC");
    fields[11] = fieldE02ERRC01 =
    new DecimalField(message, HEADERSIZE + 47, 5, 0, "E02ERRC01");
    fields[12] = fieldE02ERRC02 =
    new DecimalField(message, HEADERSIZE + 52, 5, 0, "E02ERRC02");
    fields[13] = fieldE02ERRC03 =
    new DecimalField(message, HEADERSIZE + 57, 5, 0, "E02ERRC03");
    fields[14] = fieldE02ERRC04 =
    new DecimalField(message, HEADERSIZE + 62, 5, 0, "E02ERRC04");
    fields[15] = fieldE02ERRC05 =
    new DecimalField(message, HEADERSIZE + 67, 5, 0, "E02ERRC05");
    fields[16] = fieldE02ERRD01 =
    new CharacterField(message, HEADERSIZE + 72, 50, "E02ERRD01");
    fields[17] = fieldE02ERRD02 =
    new CharacterField(message, HEADERSIZE + 122, 50, "E02ERRD02");
    fields[18] = fieldE02ERRD03 =
    new CharacterField(message, HEADERSIZE + 172, 50, "E02ERRD03");
    fields[19] = fieldE02ERRD04 =
    new CharacterField(message, HEADERSIZE + 222, 50, "E02ERRD04");
    fields[20] = fieldE02ERRD05 =
    new CharacterField(message, HEADERSIZE + 272, 50, "E02ERRD05");
    fields[21] = fieldE02ACMACC =
    new DecimalField(message, HEADERSIZE + 322, 13, 0, "E02ACMACC");
    fields[22] = fieldE02ACMAST =
    new CharacterField(message, HEADERSIZE + 335, 1, "E02ACMAST");
    fields[23] = fieldE02ACMUIN =
    new CharacterField(message, HEADERSIZE + 336, 4, "E02ACMUIN");
    fields[24] = fieldE02ACMSDC =
    new DecimalField(message, HEADERSIZE + 340, 7, 0, "E02ACMSDC");
    fields[25] = fieldE02CODCAN =
    new CharacterField(message, HEADERSIZE + 347, 4, "E02CODCAN");

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
  * Set field H02USERID using a String value.
  */
  public void setH02USERID(String newvalue)
  {
    fieldH02USERID.setString(newvalue);
  }

  /**
  * Get value of field H02USERID as a String.
  */
  public String getH02USERID()
  {
    return fieldH02USERID.getString();
  }

  /**
  * Set field H02PROGRM using a String value.
  */
  public void setH02PROGRM(String newvalue)
  {
    fieldH02PROGRM.setString(newvalue);
  }

  /**
  * Get value of field H02PROGRM as a String.
  */
  public String getH02PROGRM()
  {
    return fieldH02PROGRM.getString();
  }

  /**
  * Set field H02TIMSYS using a String value.
  */
  public void setH02TIMSYS(String newvalue)
  {
    fieldH02TIMSYS.setString(newvalue);
  }

  /**
  * Get value of field H02TIMSYS as a String.
  */
  public String getH02TIMSYS()
  {
    return fieldH02TIMSYS.getString();
  }

  /**
  * Set field H02SCRCOD using a String value.
  */
  public void setH02SCRCOD(String newvalue)
  {
    fieldH02SCRCOD.setString(newvalue);
  }

  /**
  * Get value of field H02SCRCOD as a String.
  */
  public String getH02SCRCOD()
  {
    return fieldH02SCRCOD.getString();
  }

  /**
  * Set field H02OPECOD using a String value.
  */
  public void setH02OPECOD(String newvalue)
  {
    fieldH02OPECOD.setString(newvalue);
  }

  /**
  * Get value of field H02OPECOD as a String.
  */
  public String getH02OPECOD()
  {
    return fieldH02OPECOD.getString();
  }

  /**
  * Set field H02FLGMAS using a String value.
  */
  public void setH02FLGMAS(String newvalue)
  {
    fieldH02FLGMAS.setString(newvalue);
  }

  /**
  * Get value of field H02FLGMAS as a String.
  */
  public String getH02FLGMAS()
  {
    return fieldH02FLGMAS.getString();
  }

  /**
  * Set field H02FLGWK1 using a String value.
  */
  public void setH02FLGWK1(String newvalue)
  {
    fieldH02FLGWK1.setString(newvalue);
  }

  /**
  * Get value of field H02FLGWK1 as a String.
  */
  public String getH02FLGWK1()
  {
    return fieldH02FLGWK1.getString();
  }

  /**
  * Set field H02FLGWK2 using a String value.
  */
  public void setH02FLGWK2(String newvalue)
  {
    fieldH02FLGWK2.setString(newvalue);
  }

  /**
  * Get value of field H02FLGWK2 as a String.
  */
  public String getH02FLGWK2()
  {
    return fieldH02FLGWK2.getString();
  }

  /**
  * Set field H02FLGWK3 using a String value.
  */
  public void setH02FLGWK3(String newvalue)
  {
    fieldH02FLGWK3.setString(newvalue);
  }

  /**
  * Get value of field H02FLGWK3 as a String.
  */
  public String getH02FLGWK3()
  {
    return fieldH02FLGWK3.getString();
  }

  /**
  * Set field H02CANALV using a String value.
  */
  public void setH02CANALV(String newvalue)
  {
    fieldH02CANALV.setString(newvalue);
  }

  /**
  * Get value of field H02CANALV as a String.
  */
  public String getH02CANALV()
  {
    return fieldH02CANALV.getString();
  }

  /**
  * Set field H02MODEAC using a String value.
  */
  public void setH02MODEAC(String newvalue)
  {
    fieldH02MODEAC.setString(newvalue);
  }

  /**
  * Get value of field H02MODEAC as a String.
  */
  public String getH02MODEAC()
  {
    return fieldH02MODEAC.getString();
  }

  /**
  * Set field E02ERRC01 using a String value.
  */
  public void setE02ERRC01(String newvalue)
  {
    fieldE02ERRC01.setString(newvalue);
  }

  /**
  * Get value of field E02ERRC01 as a String.
  */
  public String getE02ERRC01()
  {
    return fieldE02ERRC01.getString();
  }

  /**
  * Set numeric field E02ERRC01 using a BigDecimal value.
  */
  public void setE02ERRC01(BigDecimal newvalue)
  {
    fieldE02ERRC01.setBigDecimal(newvalue);
  }

  /**
  * Return value of numeric field E02ERRC01 as a BigDecimal.
  */
  public BigDecimal getBigDecimalE02ERRC01()
  {
    return fieldE02ERRC01.getBigDecimal();
  }

  /**
  * Set field E02ERRC02 using a String value.
  */
  public void setE02ERRC02(String newvalue)
  {
    fieldE02ERRC02.setString(newvalue);
  }

  /**
  * Get value of field E02ERRC02 as a String.
  */
  public String getE02ERRC02()
  {
    return fieldE02ERRC02.getString();
  }

  /**
  * Set numeric field E02ERRC02 using a BigDecimal value.
  */
  public void setE02ERRC02(BigDecimal newvalue)
  {
    fieldE02ERRC02.setBigDecimal(newvalue);
  }

  /**
  * Return value of numeric field E02ERRC02 as a BigDecimal.
  */
  public BigDecimal getBigDecimalE02ERRC02()
  {
    return fieldE02ERRC02.getBigDecimal();
  }

  /**
  * Set field E02ERRC03 using a String value.
  */
  public void setE02ERRC03(String newvalue)
  {
    fieldE02ERRC03.setString(newvalue);
  }

  /**
  * Get value of field E02ERRC03 as a String.
  */
  public String getE02ERRC03()
  {
    return fieldE02ERRC03.getString();
  }

  /**
  * Set numeric field E02ERRC03 using a BigDecimal value.
  */
  public void setE02ERRC03(BigDecimal newvalue)
  {
    fieldE02ERRC03.setBigDecimal(newvalue);
  }

  /**
  * Return value of numeric field E02ERRC03 as a BigDecimal.
  */
  public BigDecimal getBigDecimalE02ERRC03()
  {
    return fieldE02ERRC03.getBigDecimal();
  }

  /**
  * Set field E02ERRC04 using a String value.
  */
  public void setE02ERRC04(String newvalue)
  {
    fieldE02ERRC04.setString(newvalue);
  }

  /**
  * Get value of field E02ERRC04 as a String.
  */
  public String getE02ERRC04()
  {
    return fieldE02ERRC04.getString();
  }

  /**
  * Set numeric field E02ERRC04 using a BigDecimal value.
  */
  public void setE02ERRC04(BigDecimal newvalue)
  {
    fieldE02ERRC04.setBigDecimal(newvalue);
  }

  /**
  * Return value of numeric field E02ERRC04 as a BigDecimal.
  */
  public BigDecimal getBigDecimalE02ERRC04()
  {
    return fieldE02ERRC04.getBigDecimal();
  }

  /**
  * Set field E02ERRC05 using a String value.
  */
  public void setE02ERRC05(String newvalue)
  {
    fieldE02ERRC05.setString(newvalue);
  }

  /**
  * Get value of field E02ERRC05 as a String.
  */
  public String getE02ERRC05()
  {
    return fieldE02ERRC05.getString();
  }

  /**
  * Set numeric field E02ERRC05 using a BigDecimal value.
  */
  public void setE02ERRC05(BigDecimal newvalue)
  {
    fieldE02ERRC05.setBigDecimal(newvalue);
  }

  /**
  * Return value of numeric field E02ERRC05 as a BigDecimal.
  */
  public BigDecimal getBigDecimalE02ERRC05()
  {
    return fieldE02ERRC05.getBigDecimal();
  }

  /**
  * Set field E02ERRD01 using a String value.
  */
  public void setE02ERRD01(String newvalue)
  {
    fieldE02ERRD01.setString(newvalue);
  }

  /**
  * Get value of field E02ERRD01 as a String.
  */
  public String getE02ERRD01()
  {
    return fieldE02ERRD01.getString();
  }

  /**
  * Set field E02ERRD02 using a String value.
  */
  public void setE02ERRD02(String newvalue)
  {
    fieldE02ERRD02.setString(newvalue);
  }

  /**
  * Get value of field E02ERRD02 as a String.
  */
  public String getE02ERRD02()
  {
    return fieldE02ERRD02.getString();
  }

  /**
  * Set field E02ERRD03 using a String value.
  */
  public void setE02ERRD03(String newvalue)
  {
    fieldE02ERRD03.setString(newvalue);
  }

  /**
  * Get value of field E02ERRD03 as a String.
  */
  public String getE02ERRD03()
  {
    return fieldE02ERRD03.getString();
  }

  /**
  * Set field E02ERRD04 using a String value.
  */
  public void setE02ERRD04(String newvalue)
  {
    fieldE02ERRD04.setString(newvalue);
  }

  /**
  * Get value of field E02ERRD04 as a String.
  */
  public String getE02ERRD04()
  {
    return fieldE02ERRD04.getString();
  }

  /**
  * Set field E02ERRD05 using a String value.
  */
  public void setE02ERRD05(String newvalue)
  {
    fieldE02ERRD05.setString(newvalue);
  }

  /**
  * Get value of field E02ERRD05 as a String.
  */
  public String getE02ERRD05()
  {
    return fieldE02ERRD05.getString();
  }

  /**
  * Set field E02ACMACC using a String value.
  */
  public void setE02ACMACC(String newvalue)
  {
    fieldE02ACMACC.setString(newvalue);
  }

  /**
  * Get value of field E02ACMACC as a String.
  */
  public String getE02ACMACC()
  {
    return fieldE02ACMACC.getString();
  }

  /**
  * Set numeric field E02ACMACC using a BigDecimal value.
  */
  public void setE02ACMACC(BigDecimal newvalue)
  {
    fieldE02ACMACC.setBigDecimal(newvalue);
  }

  /**
  * Return value of numeric field E02ACMACC as a BigDecimal.
  */
  public BigDecimal getBigDecimalE02ACMACC()
  {
    return fieldE02ACMACC.getBigDecimal();
  }

  /**
  * Set field E02ACMAST using a String value.
  */
  public void setE02ACMAST(String newvalue)
  {
    fieldE02ACMAST.setString(newvalue);
  }

  /**
  * Get value of field E02ACMAST as a String.
  */
  public String getE02ACMAST()
  {
    return fieldE02ACMAST.getString();
  }

  /**
  * Set field E02ACMUIN using a String value.
  */
  public void setE02ACMUIN(String newvalue)
  {
    fieldE02ACMUIN.setString(newvalue);
  }

  /**
  * Get value of field E02ACMUIN as a String.
  */
  public String getE02ACMUIN()
  {
    return fieldE02ACMUIN.getString();
  }

  /**
  * Set field E02ACMSDC using a String value.
  */
  public void setE02ACMSDC(String newvalue)
  {
    fieldE02ACMSDC.setString(newvalue);
  }

  /**
  * Get value of field E02ACMSDC as a String.
  */
  public String getE02ACMSDC()
  {
    return fieldE02ACMSDC.getString();
  }

  /**
  * Set numeric field E02ACMSDC using a BigDecimal value.
  */
  public void setE02ACMSDC(BigDecimal newvalue)
  {
    fieldE02ACMSDC.setBigDecimal(newvalue);
  }

  /**
  * Return value of numeric field E02ACMSDC as a BigDecimal.
  */
  public BigDecimal getBigDecimalE02ACMSDC()
  {
    return fieldE02ACMSDC.getBigDecimal();
  }

  /**
  * Set field E02CODCAN using a String value.
  */
  public void setE02CODCAN(String newvalue)
  {
    fieldE02CODCAN.setString(newvalue);
  }

  /**
  * Get value of field E02CODCAN as a String.
  */
  public String getE02CODCAN()
  {
    return fieldE02CODCAN.getString();
  }

}
