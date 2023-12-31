package datapro.eibs.beans;

import datapro.eibs.sockets.*;
import java.io.*;
import java.math.*;

import java.util.*;

/**
* Class generated by AS/400 CRTCLASS command from ETCP00201 physical file definition.
* 
* File level identifier is 1040315165601.
* Record format level identifier is 3CF8E0C3390C5.
*/

public class ETCP00201Message extends MessageRecord
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
                                     "E01INUMTAR",
                                     "E01IMONAVA",
                                     "E01ICUOTA",
                                     "E01IPGRACI",
                                     "E01ONUMTAR",
                                     "E01OAPELPA",
                                     "E01OAPELMA",
                                     "E01ONOMBRE",
                                     "E01OOCODAU",
                                     "E01OFECFAC",
                                     "E01OFECVEN",
                                     "E01OMONAVA",
                                     "E01ONUMCUO",
                                     "E01OPERGRA",
                                     "E01OTASAIN",
                                     "E01OVALCUO"
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
                                   "E01INUMTAR",
                                   "E01IMONAVA",
                                   "E01ICUOTA",
                                   "E01IPGRACI",
                                   "E01ONUMTAR",
                                   "E01OAPELPA",
                                   "E01OAPELMA",
                                   "E01ONOMBRE",
                                   "E01OOCODAU",
                                   "E01OFECFAC",
                                   "E01OFECVEN",
                                   "E01OMONAVA",
                                   "E01ONUMCUO",
                                   "E01OPERGRA",
                                   "E01OTASAIN",
                                   "E01OVALCUO"
                                 };
  final static String fid = "1040315165601";
  final static String rid = "3CF8E0C3390C5";
  final static String fmtname = "ETCP00201";
  final int FIELDCOUNT = 37;
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
  private CharacterField fieldE01INUMTAR = null;
  private DecimalField fieldE01IMONAVA = null;
  private DecimalField fieldE01ICUOTA = null;
  private DecimalField fieldE01IPGRACI = null;
  private DecimalField fieldE01ONUMTAR = null;
  private CharacterField fieldE01OAPELPA = null;
  private CharacterField fieldE01OAPELMA = null;
  private CharacterField fieldE01ONOMBRE = null;
  private CharacterField fieldE01OOCODAU = null;
  private DecimalField fieldE01OFECFAC = null;
  private DecimalField fieldE01OFECVEN = null;
  private DecimalField fieldE01OMONAVA = null;
  private DecimalField fieldE01ONUMCUO = null;
  private DecimalField fieldE01OPERGRA = null;
  private DecimalField fieldE01OTASAIN = null;
  private DecimalField fieldE01OVALCUO = null;

  /**
  * Constructor for ETCP00201Message.
  */
  public ETCP00201Message()
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
    recordsize = 483;
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
    fields[21] = fieldE01INUMTAR =
    new CharacterField(message, HEADERSIZE + 322, 19, "E01INUMTAR");
    fields[22] = fieldE01IMONAVA =
    new DecimalField(message, HEADERSIZE + 341, 13, 0, "E01IMONAVA");
    fields[23] = fieldE01ICUOTA =
    new DecimalField(message, HEADERSIZE + 354, 4, 0, "E01ICUOTA");
    fields[24] = fieldE01IPGRACI =
    new DecimalField(message, HEADERSIZE + 358, 4, 0, "E01IPGRACI");
    fields[25] = fieldE01ONUMTAR =
    new DecimalField(message, HEADERSIZE + 362, 20, 0, "E01ONUMTAR");
    fields[26] = fieldE01OAPELPA =
    new CharacterField(message, HEADERSIZE + 382, 14, "E01OAPELPA");
    fields[27] = fieldE01OAPELMA =
    new CharacterField(message, HEADERSIZE + 396, 14, "E01OAPELMA");
    fields[28] = fieldE01ONOMBRE =
    new CharacterField(message, HEADERSIZE + 410, 13, "E01ONOMBRE");
    fields[29] = fieldE01OOCODAU =
    new CharacterField(message, HEADERSIZE + 423, 1, "E01OOCODAU");
    fields[30] = fieldE01OFECFAC =
    new DecimalField(message, HEADERSIZE + 424, 9, 0, "E01OFECFAC");
    fields[31] = fieldE01OFECVEN =
    new DecimalField(message, HEADERSIZE + 433, 9, 0, "E01OFECVEN");
    fields[32] = fieldE01OMONAVA =
    new DecimalField(message, HEADERSIZE + 442, 13, 0, "E01OMONAVA");
    fields[33] = fieldE01ONUMCUO =
    new DecimalField(message, HEADERSIZE + 455, 4, 0, "E01ONUMCUO");
    fields[34] = fieldE01OPERGRA =
    new DecimalField(message, HEADERSIZE + 459, 4, 0, "E01OPERGRA");
    fields[35] = fieldE01OTASAIN =
    new DecimalField(message, HEADERSIZE + 463, 7, 4, "E01OTASAIN");
    fields[36] = fieldE01OVALCUO =
    new DecimalField(message, HEADERSIZE + 470, 13, 0, "E01OVALCUO");

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
  * Set field E01IMONAVA using a String value.
  */
  public void setE01IMONAVA(String newvalue)
  {
    fieldE01IMONAVA.setString(newvalue);
  }

  /**
  * Get value of field E01IMONAVA as a String.
  */
  public String getE01IMONAVA()
  {
    return fieldE01IMONAVA.getString();
  }

  /**
  * Set numeric field E01IMONAVA using a BigDecimal value.
  */
  public void setE01IMONAVA(BigDecimal newvalue)
  {
    fieldE01IMONAVA.setBigDecimal(newvalue);
  }

  /**
  * Return value of numeric field E01IMONAVA as a BigDecimal.
  */
  public BigDecimal getBigDecimalE01IMONAVA()
  {
    return fieldE01IMONAVA.getBigDecimal();
  }

  /**
  * Set field E01ICUOTA using a String value.
  */
  public void setE01ICUOTA(String newvalue)
  {
    fieldE01ICUOTA.setString(newvalue);
  }

  /**
  * Get value of field E01ICUOTA as a String.
  */
  public String getE01ICUOTA()
  {
    return fieldE01ICUOTA.getString();
  }

  /**
  * Set numeric field E01ICUOTA using a BigDecimal value.
  */
  public void setE01ICUOTA(BigDecimal newvalue)
  {
    fieldE01ICUOTA.setBigDecimal(newvalue);
  }

  /**
  * Return value of numeric field E01ICUOTA as a BigDecimal.
  */
  public BigDecimal getBigDecimalE01ICUOTA()
  {
    return fieldE01ICUOTA.getBigDecimal();
  }

  /**
  * Set field E01IPGRACI using a String value.
  */
  public void setE01IPGRACI(String newvalue)
  {
    fieldE01IPGRACI.setString(newvalue);
  }

  /**
  * Get value of field E01IPGRACI as a String.
  */
  public String getE01IPGRACI()
  {
    return fieldE01IPGRACI.getString();
  }

  /**
  * Set numeric field E01IPGRACI using a BigDecimal value.
  */
  public void setE01IPGRACI(BigDecimal newvalue)
  {
    fieldE01IPGRACI.setBigDecimal(newvalue);
  }

  /**
  * Return value of numeric field E01IPGRACI as a BigDecimal.
  */
  public BigDecimal getBigDecimalE01IPGRACI()
  {
    return fieldE01IPGRACI.getBigDecimal();
  }

  /**
  * Set field E01ONUMTAR using a String value.
  */
  public void setE01ONUMTAR(String newvalue)
  {
    fieldE01ONUMTAR.setString(newvalue);
  }

  /**
  * Get value of field E01ONUMTAR as a String.
  */
  public String getE01ONUMTAR()
  {
    return fieldE01ONUMTAR.getString();
  }

  /**
  * Set numeric field E01ONUMTAR using a BigDecimal value.
  */
  public void setE01ONUMTAR(BigDecimal newvalue)
  {
    fieldE01ONUMTAR.setBigDecimal(newvalue);
  }

  /**
  * Return value of numeric field E01ONUMTAR as a BigDecimal.
  */
  public BigDecimal getBigDecimalE01ONUMTAR()
  {
    return fieldE01ONUMTAR.getBigDecimal();
  }

  /**
  * Set field E01OAPELPA using a String value.
  */
  public void setE01OAPELPA(String newvalue)
  {
    fieldE01OAPELPA.setString(newvalue);
  }

  /**
  * Get value of field E01OAPELPA as a String.
  */
  public String getE01OAPELPA()
  {
    return fieldE01OAPELPA.getString();
  }

  /**
  * Set field E01OAPELMA using a String value.
  */
  public void setE01OAPELMA(String newvalue)
  {
    fieldE01OAPELMA.setString(newvalue);
  }

  /**
  * Get value of field E01OAPELMA as a String.
  */
  public String getE01OAPELMA()
  {
    return fieldE01OAPELMA.getString();
  }

  /**
  * Set field E01ONOMBRE using a String value.
  */
  public void setE01ONOMBRE(String newvalue)
  {
    fieldE01ONOMBRE.setString(newvalue);
  }

  /**
  * Get value of field E01ONOMBRE as a String.
  */
  public String getE01ONOMBRE()
  {
    return fieldE01ONOMBRE.getString();
  }

  /**
  * Set field E01OOCODAU using a String value.
  */
  public void setE01OOCODAU(String newvalue)
  {
    fieldE01OOCODAU.setString(newvalue);
  }

  /**
  * Get value of field E01OOCODAU as a String.
  */
  public String getE01OOCODAU()
  {
    return fieldE01OOCODAU.getString();
  }

  /**
  * Set field E01OFECFAC using a String value.
  */
  public void setE01OFECFAC(String newvalue)
  {
    fieldE01OFECFAC.setString(newvalue);
  }

  /**
  * Get value of field E01OFECFAC as a String.
  */
  public String getE01OFECFAC()
  {
    return fieldE01OFECFAC.getString();
  }

  /**
  * Set numeric field E01OFECFAC using a BigDecimal value.
  */
  public void setE01OFECFAC(BigDecimal newvalue)
  {
    fieldE01OFECFAC.setBigDecimal(newvalue);
  }

  /**
  * Return value of numeric field E01OFECFAC as a BigDecimal.
  */
  public BigDecimal getBigDecimalE01OFECFAC()
  {
    return fieldE01OFECFAC.getBigDecimal();
  }

  /**
  * Set field E01OFECVEN using a String value.
  */
  public void setE01OFECVEN(String newvalue)
  {
    fieldE01OFECVEN.setString(newvalue);
  }

  /**
  * Get value of field E01OFECVEN as a String.
  */
  public String getE01OFECVEN()
  {
    return fieldE01OFECVEN.getString();
  }

  /**
  * Set numeric field E01OFECVEN using a BigDecimal value.
  */
  public void setE01OFECVEN(BigDecimal newvalue)
  {
    fieldE01OFECVEN.setBigDecimal(newvalue);
  }

  /**
  * Return value of numeric field E01OFECVEN as a BigDecimal.
  */
  public BigDecimal getBigDecimalE01OFECVEN()
  {
    return fieldE01OFECVEN.getBigDecimal();
  }

  /**
  * Set field E01OMONAVA using a String value.
  */
  public void setE01OMONAVA(String newvalue)
  {
    fieldE01OMONAVA.setString(newvalue);
  }

  /**
  * Get value of field E01OMONAVA as a String.
  */
  public String getE01OMONAVA()
  {
    return fieldE01OMONAVA.getString();
  }

  /**
  * Set numeric field E01OMONAVA using a BigDecimal value.
  */
  public void setE01OMONAVA(BigDecimal newvalue)
  {
    fieldE01OMONAVA.setBigDecimal(newvalue);
  }

  /**
  * Return value of numeric field E01OMONAVA as a BigDecimal.
  */
  public BigDecimal getBigDecimalE01OMONAVA()
  {
    return fieldE01OMONAVA.getBigDecimal();
  }

  /**
  * Set field E01ONUMCUO using a String value.
  */
  public void setE01ONUMCUO(String newvalue)
  {
    fieldE01ONUMCUO.setString(newvalue);
  }

  /**
  * Get value of field E01ONUMCUO as a String.
  */
  public String getE01ONUMCUO()
  {
    return fieldE01ONUMCUO.getString();
  }

  /**
  * Set numeric field E01ONUMCUO using a BigDecimal value.
  */
  public void setE01ONUMCUO(BigDecimal newvalue)
  {
    fieldE01ONUMCUO.setBigDecimal(newvalue);
  }

  /**
  * Return value of numeric field E01ONUMCUO as a BigDecimal.
  */
  public BigDecimal getBigDecimalE01ONUMCUO()
  {
    return fieldE01ONUMCUO.getBigDecimal();
  }

  /**
  * Set field E01OPERGRA using a String value.
  */
  public void setE01OPERGRA(String newvalue)
  {
    fieldE01OPERGRA.setString(newvalue);
  }

  /**
  * Get value of field E01OPERGRA as a String.
  */
  public String getE01OPERGRA()
  {
    return fieldE01OPERGRA.getString();
  }

  /**
  * Set numeric field E01OPERGRA using a BigDecimal value.
  */
  public void setE01OPERGRA(BigDecimal newvalue)
  {
    fieldE01OPERGRA.setBigDecimal(newvalue);
  }

  /**
  * Return value of numeric field E01OPERGRA as a BigDecimal.
  */
  public BigDecimal getBigDecimalE01OPERGRA()
  {
    return fieldE01OPERGRA.getBigDecimal();
  }

  /**
  * Set field E01OTASAIN using a String value.
  */
  public void setE01OTASAIN(String newvalue)
  {
    fieldE01OTASAIN.setString(newvalue);
  }

  /**
  * Get value of field E01OTASAIN as a String.
  */
  public String getE01OTASAIN()
  {
    return fieldE01OTASAIN.getString();
  }

  /**
  * Set numeric field E01OTASAIN using a BigDecimal value.
  */
  public void setE01OTASAIN(BigDecimal newvalue)
  {
    fieldE01OTASAIN.setBigDecimal(newvalue);
  }

  /**
  * Return value of numeric field E01OTASAIN as a BigDecimal.
  */
  public BigDecimal getBigDecimalE01OTASAIN()
  {
    return fieldE01OTASAIN.getBigDecimal();
  }

  /**
  * Set field E01OVALCUO using a String value.
  */
  public void setE01OVALCUO(String newvalue)
  {
    fieldE01OVALCUO.setString(newvalue);
  }

  /**
  * Get value of field E01OVALCUO as a String.
  */
  public String getE01OVALCUO()
  {
    return fieldE01OVALCUO.getString();
  }

  /**
  * Set numeric field E01OVALCUO using a BigDecimal value.
  */
  public void setE01OVALCUO(BigDecimal newvalue)
  {
    fieldE01OVALCUO.setBigDecimal(newvalue);
  }

  /**
  * Return value of numeric field E01OVALCUO as a BigDecimal.
  */
  public BigDecimal getBigDecimalE01OVALCUO()
  {
    return fieldE01OVALCUO.getBigDecimal();
  }

}
