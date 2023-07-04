package datapro.eibs.beans;

import datapro.eibs.sockets.*;
import java.io.*;
import java.math.*;

import java.util.*;

/**
* Class generated by AS/400 CRTCLASS command from EACH36001 physical file definition.
* 
* File level identifier is 1080624124921.
* Record format level identifier is 48FB029DC3F91.
*/

public class EACH36001Message extends MessageRecord
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
                                     "E01ACRNUM",
                                     "E01ACRCUN",
                                     "E01ACRACC",
                                     "E01ACRVDY",
                                     "E01ACRVDM",
                                     "E01ACRVDD",
                                     "E01ACRECD",
                                     "E01ACRROU",
                                     "E01ACRDAC",
                                     "E01ACRCCY",
                                     "E01ACRAMT",
                                     "E01ACRCDE",
                                     "E01ACRADD",
                                     "E01ACRRTR",
                                     "E01ACRSTS",
                                     "E01ACRUSR",
                                     "E01ACRRTS",
                                     "E01ACRPTS",
                                     "E01CUNDSC",
                                     "E01CDEDSC",
                                     "E01RTRDSC",
                                     "E01ACT",
                                     "E01NUMREC",
                                     "E01INDOPE"
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
                                   "E01ACRNUM",
                                   "E01ACRCUN",
                                   "E01ACRACC",
                                   "E01ACRVDY",
                                   "E01ACRVDM",
                                   "E01ACRVDD",
                                   "E01ACRECD",
                                   "E01ACRROU",
                                   "E01ACRDAC",
                                   "E01ACRCCY",
                                   "E01ACRAMT",
                                   "E01ACRCDE",
                                   "E01ACRADD",
                                   "E01ACRRTR",
                                   "E01ACRSTS",
                                   "E01ACRUSR",
                                   "E01ACRRTS",
                                   "E01ACRPTS",
                                   "E01CUNDSC",
                                   "E01CDEDSC",
                                   "E01RTRDSC",
                                   "E01ACT",
                                   "E01NUMREC",
                                   "E01INDOPE"
                                 };
  final static String fid = "1080624124921";
  final static String rid = "48FB029DC3F91";
  final static String fmtname = "EACH36001";
  final int FIELDCOUNT = 33;
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
  private DecimalField fieldE01ACRNUM = null;
  private DecimalField fieldE01ACRCUN = null;
  private DecimalField fieldE01ACRACC = null;
  private DecimalField fieldE01ACRVDY = null;
  private DecimalField fieldE01ACRVDM = null;
  private DecimalField fieldE01ACRVDD = null;
  private CharacterField fieldE01ACRECD = null;
  private CharacterField fieldE01ACRROU = null;
  private CharacterField fieldE01ACRDAC = null;
  private CharacterField fieldE01ACRCCY = null;
  private DecimalField fieldE01ACRAMT = null;
  private CharacterField fieldE01ACRCDE = null;
  private CharacterField fieldE01ACRADD = null;
  private CharacterField fieldE01ACRRTR = null;
  private CharacterField fieldE01ACRSTS = null;
  private CharacterField fieldE01ACRUSR = null;
  private DecimalField fieldE01ACRRTS = null;
  private DecimalField fieldE01ACRPTS = null;
  private CharacterField fieldE01CUNDSC = null;
  private CharacterField fieldE01CDEDSC = null;
  private CharacterField fieldE01RTRDSC = null;
  private CharacterField fieldE01ACT = null;
  private DecimalField fieldE01NUMREC = null;
  private CharacterField fieldE01INDOPE = null;

  /**
  * Constructor for EACH36001Message.
  */
  public EACH36001Message()
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
    recordsize = 397;
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
    fields[9] = fieldE01ACRNUM =
    new DecimalField(message, HEADERSIZE + 42, 13, 0, "E01ACRNUM");
    fields[10] = fieldE01ACRCUN =
    new DecimalField(message, HEADERSIZE + 55, 10, 0, "E01ACRCUN");
    fields[11] = fieldE01ACRACC =
    new DecimalField(message, HEADERSIZE + 65, 13, 0, "E01ACRACC");
    fields[12] = fieldE01ACRVDY =
    new DecimalField(message, HEADERSIZE + 78, 3, 0, "E01ACRVDY");
    fields[13] = fieldE01ACRVDM =
    new DecimalField(message, HEADERSIZE + 81, 3, 0, "E01ACRVDM");
    fields[14] = fieldE01ACRVDD =
    new DecimalField(message, HEADERSIZE + 84, 3, 0, "E01ACRVDD");
    fields[15] = fieldE01ACRECD =
    new CharacterField(message, HEADERSIZE + 87, 3, "E01ACRECD");
    fields[16] = fieldE01ACRROU =
    new CharacterField(message, HEADERSIZE + 90, 9, "E01ACRROU");
    fields[17] = fieldE01ACRDAC =
    new CharacterField(message, HEADERSIZE + 99, 17, "E01ACRDAC");
    fields[18] = fieldE01ACRCCY =
    new CharacterField(message, HEADERSIZE + 116, 3, "E01ACRCCY");
    fields[19] = fieldE01ACRAMT =
    new DecimalField(message, HEADERSIZE + 119, 17, 2, "E01ACRAMT");
    fields[20] = fieldE01ACRCDE =
    new CharacterField(message, HEADERSIZE + 136, 2, "E01ACRCDE");
    fields[21] = fieldE01ACRADD =
    new CharacterField(message, HEADERSIZE + 138, 94, "E01ACRADD");
    fields[22] = fieldE01ACRRTR =
    new CharacterField(message, HEADERSIZE + 232, 3, "E01ACRRTR");
    fields[23] = fieldE01ACRSTS =
    new CharacterField(message, HEADERSIZE + 235, 1, "E01ACRSTS");
    fields[24] = fieldE01ACRUSR =
    new CharacterField(message, HEADERSIZE + 236, 10, "E01ACRUSR");
    fields[25] = fieldE01ACRRTS =
    new DecimalField(message, HEADERSIZE + 246, 13, 0, "E01ACRRTS");
    fields[26] = fieldE01ACRPTS =
    new DecimalField(message, HEADERSIZE + 259, 13, 0, "E01ACRPTS");
    fields[27] = fieldE01CUNDSC =
    new CharacterField(message, HEADERSIZE + 272, 35, "E01CUNDSC");
    fields[28] = fieldE01CDEDSC =
    new CharacterField(message, HEADERSIZE + 307, 35, "E01CDEDSC");
    fields[29] = fieldE01RTRDSC =
    new CharacterField(message, HEADERSIZE + 342, 45, "E01RTRDSC");
    fields[30] = fieldE01ACT =
    new CharacterField(message, HEADERSIZE + 387, 1, "E01ACT");
    fields[31] = fieldE01NUMREC =
    new DecimalField(message, HEADERSIZE + 388, 8, 0, "E01NUMREC");
    fields[32] = fieldE01INDOPE =
    new CharacterField(message, HEADERSIZE + 396, 1, "E01INDOPE");

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
  * Set field E01ACRNUM using a String value.
  */
  public void setE01ACRNUM(String newvalue)
  {
    fieldE01ACRNUM.setString(newvalue);
  }

  /**
  * Get value of field E01ACRNUM as a String.
  */
  public String getE01ACRNUM()
  {
    return fieldE01ACRNUM.getString();
  }

  /**
  * Set numeric field E01ACRNUM using a BigDecimal value.
  */
  public void setE01ACRNUM(BigDecimal newvalue)
  {
    fieldE01ACRNUM.setBigDecimal(newvalue);
  }

  /**
  * Return value of numeric field E01ACRNUM as a BigDecimal.
  */
  public BigDecimal getBigDecimalE01ACRNUM()
  {
    return fieldE01ACRNUM.getBigDecimal();
  }

  /**
  * Set field E01ACRCUN using a String value.
  */
  public void setE01ACRCUN(String newvalue)
  {
    fieldE01ACRCUN.setString(newvalue);
  }

  /**
  * Get value of field E01ACRCUN as a String.
  */
  public String getE01ACRCUN()
  {
    return fieldE01ACRCUN.getString();
  }

  /**
  * Set numeric field E01ACRCUN using a BigDecimal value.
  */
  public void setE01ACRCUN(BigDecimal newvalue)
  {
    fieldE01ACRCUN.setBigDecimal(newvalue);
  }

  /**
  * Return value of numeric field E01ACRCUN as a BigDecimal.
  */
  public BigDecimal getBigDecimalE01ACRCUN()
  {
    return fieldE01ACRCUN.getBigDecimal();
  }

  /**
  * Set field E01ACRACC using a String value.
  */
  public void setE01ACRACC(String newvalue)
  {
    fieldE01ACRACC.setString(newvalue);
  }

  /**
  * Get value of field E01ACRACC as a String.
  */
  public String getE01ACRACC()
  {
    return fieldE01ACRACC.getString();
  }

  /**
  * Set numeric field E01ACRACC using a BigDecimal value.
  */
  public void setE01ACRACC(BigDecimal newvalue)
  {
    fieldE01ACRACC.setBigDecimal(newvalue);
  }

  /**
  * Return value of numeric field E01ACRACC as a BigDecimal.
  */
  public BigDecimal getBigDecimalE01ACRACC()
  {
    return fieldE01ACRACC.getBigDecimal();
  }

  /**
  * Set field E01ACRVDY using a String value.
  */
  public void setE01ACRVDY(String newvalue)
  {
    fieldE01ACRVDY.setString(newvalue);
  }

  /**
  * Get value of field E01ACRVDY as a String.
  */
  public String getE01ACRVDY()
  {
    return fieldE01ACRVDY.getString();
  }

  /**
  * Set numeric field E01ACRVDY using a BigDecimal value.
  */
  public void setE01ACRVDY(BigDecimal newvalue)
  {
    fieldE01ACRVDY.setBigDecimal(newvalue);
  }

  /**
  * Return value of numeric field E01ACRVDY as a BigDecimal.
  */
  public BigDecimal getBigDecimalE01ACRVDY()
  {
    return fieldE01ACRVDY.getBigDecimal();
  }

  /**
  * Set field E01ACRVDM using a String value.
  */
  public void setE01ACRVDM(String newvalue)
  {
    fieldE01ACRVDM.setString(newvalue);
  }

  /**
  * Get value of field E01ACRVDM as a String.
  */
  public String getE01ACRVDM()
  {
    return fieldE01ACRVDM.getString();
  }

  /**
  * Set numeric field E01ACRVDM using a BigDecimal value.
  */
  public void setE01ACRVDM(BigDecimal newvalue)
  {
    fieldE01ACRVDM.setBigDecimal(newvalue);
  }

  /**
  * Return value of numeric field E01ACRVDM as a BigDecimal.
  */
  public BigDecimal getBigDecimalE01ACRVDM()
  {
    return fieldE01ACRVDM.getBigDecimal();
  }

  /**
  * Set field E01ACRVDD using a String value.
  */
  public void setE01ACRVDD(String newvalue)
  {
    fieldE01ACRVDD.setString(newvalue);
  }

  /**
  * Get value of field E01ACRVDD as a String.
  */
  public String getE01ACRVDD()
  {
    return fieldE01ACRVDD.getString();
  }

  /**
  * Set numeric field E01ACRVDD using a BigDecimal value.
  */
  public void setE01ACRVDD(BigDecimal newvalue)
  {
    fieldE01ACRVDD.setBigDecimal(newvalue);
  }

  /**
  * Return value of numeric field E01ACRVDD as a BigDecimal.
  */
  public BigDecimal getBigDecimalE01ACRVDD()
  {
    return fieldE01ACRVDD.getBigDecimal();
  }

  /**
  * Set field E01ACRECD using a String value.
  */
  public void setE01ACRECD(String newvalue)
  {
    fieldE01ACRECD.setString(newvalue);
  }

  /**
  * Get value of field E01ACRECD as a String.
  */
  public String getE01ACRECD()
  {
    return fieldE01ACRECD.getString();
  }

  /**
  * Set field E01ACRROU using a String value.
  */
  public void setE01ACRROU(String newvalue)
  {
    fieldE01ACRROU.setString(newvalue);
  }

  /**
  * Get value of field E01ACRROU as a String.
  */
  public String getE01ACRROU()
  {
    return fieldE01ACRROU.getString();
  }

  /**
  * Set field E01ACRDAC using a String value.
  */
  public void setE01ACRDAC(String newvalue)
  {
    fieldE01ACRDAC.setString(newvalue);
  }

  /**
  * Get value of field E01ACRDAC as a String.
  */
  public String getE01ACRDAC()
  {
    return fieldE01ACRDAC.getString();
  }

  /**
  * Set field E01ACRCCY using a String value.
  */
  public void setE01ACRCCY(String newvalue)
  {
    fieldE01ACRCCY.setString(newvalue);
  }

  /**
  * Get value of field E01ACRCCY as a String.
  */
  public String getE01ACRCCY()
  {
    return fieldE01ACRCCY.getString();
  }

  /**
  * Set field E01ACRAMT using a String value.
  */
  public void setE01ACRAMT(String newvalue)
  {
    fieldE01ACRAMT.setString(newvalue);
  }

  /**
  * Get value of field E01ACRAMT as a String.
  */
  public String getE01ACRAMT()
  {
    return fieldE01ACRAMT.getString();
  }

  /**
  * Set numeric field E01ACRAMT using a BigDecimal value.
  */
  public void setE01ACRAMT(BigDecimal newvalue)
  {
    fieldE01ACRAMT.setBigDecimal(newvalue);
  }

  /**
  * Return value of numeric field E01ACRAMT as a BigDecimal.
  */
  public BigDecimal getBigDecimalE01ACRAMT()
  {
    return fieldE01ACRAMT.getBigDecimal();
  }

  /**
  * Set field E01ACRCDE using a String value.
  */
  public void setE01ACRCDE(String newvalue)
  {
    fieldE01ACRCDE.setString(newvalue);
  }

  /**
  * Get value of field E01ACRCDE as a String.
  */
  public String getE01ACRCDE()
  {
    return fieldE01ACRCDE.getString();
  }

  /**
  * Set field E01ACRADD using a String value.
  */
  public void setE01ACRADD(String newvalue)
  {
    fieldE01ACRADD.setString(newvalue);
  }

  /**
  * Get value of field E01ACRADD as a String.
  */
  public String getE01ACRADD()
  {
    return fieldE01ACRADD.getString();
  }

  /**
  * Set field E01ACRRTR using a String value.
  */
  public void setE01ACRRTR(String newvalue)
  {
    fieldE01ACRRTR.setString(newvalue);
  }

  /**
  * Get value of field E01ACRRTR as a String.
  */
  public String getE01ACRRTR()
  {
    return fieldE01ACRRTR.getString();
  }

  /**
  * Set field E01ACRSTS using a String value.
  */
  public void setE01ACRSTS(String newvalue)
  {
    fieldE01ACRSTS.setString(newvalue);
  }

  /**
  * Get value of field E01ACRSTS as a String.
  */
  public String getE01ACRSTS()
  {
    return fieldE01ACRSTS.getString();
  }

  /**
  * Set field E01ACRUSR using a String value.
  */
  public void setE01ACRUSR(String newvalue)
  {
    fieldE01ACRUSR.setString(newvalue);
  }

  /**
  * Get value of field E01ACRUSR as a String.
  */
  public String getE01ACRUSR()
  {
    return fieldE01ACRUSR.getString();
  }

  /**
  * Set field E01ACRRTS using a String value.
  */
  public void setE01ACRRTS(String newvalue)
  {
    fieldE01ACRRTS.setString(newvalue);
  }

  /**
  * Get value of field E01ACRRTS as a String.
  */
  public String getE01ACRRTS()
  {
    return fieldE01ACRRTS.getString();
  }

  /**
  * Set numeric field E01ACRRTS using a BigDecimal value.
  */
  public void setE01ACRRTS(BigDecimal newvalue)
  {
    fieldE01ACRRTS.setBigDecimal(newvalue);
  }

  /**
  * Return value of numeric field E01ACRRTS as a BigDecimal.
  */
  public BigDecimal getBigDecimalE01ACRRTS()
  {
    return fieldE01ACRRTS.getBigDecimal();
  }

  /**
  * Set field E01ACRPTS using a String value.
  */
  public void setE01ACRPTS(String newvalue)
  {
    fieldE01ACRPTS.setString(newvalue);
  }

  /**
  * Get value of field E01ACRPTS as a String.
  */
  public String getE01ACRPTS()
  {
    return fieldE01ACRPTS.getString();
  }

  /**
  * Set numeric field E01ACRPTS using a BigDecimal value.
  */
  public void setE01ACRPTS(BigDecimal newvalue)
  {
    fieldE01ACRPTS.setBigDecimal(newvalue);
  }

  /**
  * Return value of numeric field E01ACRPTS as a BigDecimal.
  */
  public BigDecimal getBigDecimalE01ACRPTS()
  {
    return fieldE01ACRPTS.getBigDecimal();
  }

  /**
  * Set field E01CUNDSC using a String value.
  */
  public void setE01CUNDSC(String newvalue)
  {
    fieldE01CUNDSC.setString(newvalue);
  }

  /**
  * Get value of field E01CUNDSC as a String.
  */
  public String getE01CUNDSC()
  {
    return fieldE01CUNDSC.getString();
  }

  /**
  * Set field E01CDEDSC using a String value.
  */
  public void setE01CDEDSC(String newvalue)
  {
    fieldE01CDEDSC.setString(newvalue);
  }

  /**
  * Get value of field E01CDEDSC as a String.
  */
  public String getE01CDEDSC()
  {
    return fieldE01CDEDSC.getString();
  }

  /**
  * Set field E01RTRDSC using a String value.
  */
  public void setE01RTRDSC(String newvalue)
  {
    fieldE01RTRDSC.setString(newvalue);
  }

  /**
  * Get value of field E01RTRDSC as a String.
  */
  public String getE01RTRDSC()
  {
    return fieldE01RTRDSC.getString();
  }

  /**
  * Set field E01ACT using a String value.
  */
  public void setE01ACT(String newvalue)
  {
    fieldE01ACT.setString(newvalue);
  }

  /**
  * Get value of field E01ACT as a String.
  */
  public String getE01ACT()
  {
    return fieldE01ACT.getString();
  }

  /**
  * Set field E01NUMREC using a String value.
  */
  public void setE01NUMREC(String newvalue)
  {
    fieldE01NUMREC.setString(newvalue);
  }

  /**
  * Get value of field E01NUMREC as a String.
  */
  public String getE01NUMREC()
  {
    return fieldE01NUMREC.getString();
  }

  /**
  * Set numeric field E01NUMREC using a BigDecimal value.
  */
  public void setE01NUMREC(BigDecimal newvalue)
  {
    fieldE01NUMREC.setBigDecimal(newvalue);
  }

  /**
  * Return value of numeric field E01NUMREC as a BigDecimal.
  */
  public BigDecimal getBigDecimalE01NUMREC()
  {
    return fieldE01NUMREC.getBigDecimal();
  }

  /**
  * Set field E01INDOPE using a String value.
  */
  public void setE01INDOPE(String newvalue)
  {
    fieldE01INDOPE.setString(newvalue);
  }

  /**
  * Get value of field E01INDOPE as a String.
  */
  public String getE01INDOPE()
  {
    return fieldE01INDOPE.getString();
  }

}
