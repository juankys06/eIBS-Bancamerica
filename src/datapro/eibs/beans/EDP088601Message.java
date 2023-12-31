package datapro.eibs.beans;

import datapro.eibs.sockets.*;
import java.io.*;
import java.math.*;

import java.util.*;

/**
* Class generated by AS/400 CRTCLASS command from EDP088601 physical file definition.
* 
* File level identifier is 1090114181748.
* Record format level identifier is 47F72436F80D3.
*/

public class EDP088601Message extends MessageRecord
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
                                     "E01CNTLAN",
                                     "E01PLPNPR",
                                     "E01CUSCUN",
                                     "E01CUSNA1",
                                     "E01CUSLGT",
                                     "E01CUSIDN",
                                     "E01SUMAGE",
                                     "E01SUMGEN",
                                     "E01SUMYYF",
                                     "E01SUMMMF",
                                     "E01SUMDDF",
                                     "E01EVAFAY",
                                     "E01EVAFAM",
                                     "E01EVAFAD",
                                     "E01PLPBRN",
                                     "E01PLPBRD",
                                     "E01PLPEJE",
                                     "E01PLPEJD",
                                     "E01PLTPRO",
                                     "E01PLTTYP",
                                     "E01PLTPRD",
                                     "E01PLTAMN",
                                     "E01PLTPLZ",
                                     "E01PLTPLD",
                                     "E01PLTRTE",
                                     "E01PLTCOL",
                                     "E01PLTCOD",
                                     "E01EVAING",
                                     "E01DEBMON",
                                     "E01DEBACT",
                                     "E01MAXODF",
                                     "E01MAXOPL",
                                     "E01MINODF",
                                     "E01MINOPL",
                                     "E01TOTPNT",
                                     "E01CALRIS",
                                     "E01FAIPRB",
                                     "E01SPEPRO",
                                     "E01SCOREC",
                                     "E01AMNMXI",
                                     "E01AMNMXC",
                                     "E01EVAOBS",
                                     "E01IDANAL",
                                     "E01DSANAL",
                                     "E01IDMANA",
                                     "E01DSMANA",
                                     "E01RECPOS",
                                     "E01OPECDE"
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
                                   "E01CNTLAN",
                                   "E01PLPNPR",
                                   "E01CUSCUN",
                                   "E01CUSNA1",
                                   "E01CUSLGT",
                                   "E01CUSIDN",
                                   "E01SUMAGE",
                                   "E01SUMGEN",
                                   "E01SUMYYF",
                                   "E01SUMMMF",
                                   "E01SUMDDF",
                                   "E01EVAFAY",
                                   "E01EVAFAM",
                                   "E01EVAFAD",
                                   "E01PLPBRN",
                                   "E01PLPBRD",
                                   "E01PLPEJE",
                                   "E01PLPEJD",
                                   "E01PLTPRO",
                                   "E01PLTTYP",
                                   "E01PLTPRD",
                                   "E01PLTAMN",
                                   "E01PLTPLZ",
                                   "E01PLTPLD",
                                   "E01PLTRTE",
                                   "E01PLTCOL",
                                   "E01PLTCOD",
                                   "E01EVAING",
                                   "E01DEBMON",
                                   "E01DEBACT",
                                   "E01MAXODF",
                                   "E01MAXOPL",
                                   "E01MINODF",
                                   "E01MINOPL",
                                   "E01TOTPNT",
                                   "E01CALRIS",
                                   "E01FAIPRB",
                                   "E01SPEPRO",
                                   "E01SCOREC",
                                   "E01AMNMXI",
                                   "E01AMNMXC",
                                   "E01EVAOBS",
                                   "E01IDANAL",
                                   "E01DSANAL",
                                   "E01IDMANA",
                                   "E01DSMANA",
                                   "E01RECPOS",
                                   "E01OPECDE"
                                 };
  final static String fid = "1090114181748";
  final static String rid = "47F72436F80D3";
  final static String fmtname = "EDP088601";
  final int FIELDCOUNT = 57;
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
  private CharacterField fieldE01CNTLAN = null;
  private DecimalField fieldE01PLPNPR = null;
  private DecimalField fieldE01CUSCUN = null;
  private CharacterField fieldE01CUSNA1 = null;
  private CharacterField fieldE01CUSLGT = null;
  private CharacterField fieldE01CUSIDN = null;
  private DecimalField fieldE01SUMAGE = null;
  private CharacterField fieldE01SUMGEN = null;
  private CharacterField fieldE01SUMYYF = null;
  private CharacterField fieldE01SUMMMF = null;
  private CharacterField fieldE01SUMDDF = null;
  private CharacterField fieldE01EVAFAY = null;
  private CharacterField fieldE01EVAFAM = null;
  private CharacterField fieldE01EVAFAD = null;
  private DecimalField fieldE01PLPBRN = null;
  private CharacterField fieldE01PLPBRD = null;
  private CharacterField fieldE01PLPEJE = null;
  private CharacterField fieldE01PLPEJD = null;
  private CharacterField fieldE01PLTPRO = null;
  private CharacterField fieldE01PLTTYP = null;
  private CharacterField fieldE01PLTPRD = null;
  private DecimalField fieldE01PLTAMN = null;
  private DecimalField fieldE01PLTPLZ = null;
  private CharacterField fieldE01PLTPLD = null;
  private DecimalField fieldE01PLTRTE = null;
  private CharacterField fieldE01PLTCOL = null;
  private CharacterField fieldE01PLTCOD = null;
  private DecimalField fieldE01EVAING = null;
  private DecimalField fieldE01DEBMON = null;
  private DecimalField fieldE01DEBACT = null;
  private DecimalField fieldE01MAXODF = null;
  private DecimalField fieldE01MAXOPL = null;
  private DecimalField fieldE01MINODF = null;
  private DecimalField fieldE01MINOPL = null;
  private DecimalField fieldE01TOTPNT = null;
  private CharacterField fieldE01CALRIS = null;
  private DecimalField fieldE01FAIPRB = null;
  private DecimalField fieldE01SPEPRO = null;
  private CharacterField fieldE01SCOREC = null;
  private DecimalField fieldE01AMNMXI = null;
  private DecimalField fieldE01AMNMXC = null;
  private CharacterField fieldE01EVAOBS = null;
  private CharacterField fieldE01IDANAL = null;
  private CharacterField fieldE01DSANAL = null;
  private CharacterField fieldE01IDMANA = null;
  private CharacterField fieldE01DSMANA = null;
  private DecimalField fieldE01RECPOS = null;
  private CharacterField fieldE01OPECDE = null;

  /**
  * Constructor for EDP088601Message.
  */
  public EDP088601Message()
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
    recordsize = 1467;
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
    fields[9] = fieldE01CNTLAN =
    new CharacterField(message, HEADERSIZE + 42, 1, "E01CNTLAN");
    fields[10] = fieldE01PLPNPR =
    new DecimalField(message, HEADERSIZE + 43, 13, 0, "E01PLPNPR");
    fields[11] = fieldE01CUSCUN =
    new DecimalField(message, HEADERSIZE + 56, 10, 0, "E01CUSCUN");
    fields[12] = fieldE01CUSNA1 =
    new CharacterField(message, HEADERSIZE + 66, 45, "E01CUSNA1");
    fields[13] = fieldE01CUSLGT =
    new CharacterField(message, HEADERSIZE + 111, 1, "E01CUSLGT");
    fields[14] = fieldE01CUSIDN =
    new CharacterField(message, HEADERSIZE + 112, 15, "E01CUSIDN");
    fields[15] = fieldE01SUMAGE =
    new DecimalField(message, HEADERSIZE + 127, 4, 0, "E01SUMAGE");
    fields[16] = fieldE01SUMGEN =
    new CharacterField(message, HEADERSIZE + 131, 10, "E01SUMGEN");
    fields[17] = fieldE01SUMYYF =
    new CharacterField(message, HEADERSIZE + 141, 2, "E01SUMYYF");
    fields[18] = fieldE01SUMMMF =
    new CharacterField(message, HEADERSIZE + 143, 2, "E01SUMMMF");
    fields[19] = fieldE01SUMDDF =
    new CharacterField(message, HEADERSIZE + 145, 2, "E01SUMDDF");
    fields[20] = fieldE01EVAFAY =
    new CharacterField(message, HEADERSIZE + 147, 2, "E01EVAFAY");
    fields[21] = fieldE01EVAFAM =
    new CharacterField(message, HEADERSIZE + 149, 2, "E01EVAFAM");
    fields[22] = fieldE01EVAFAD =
    new CharacterField(message, HEADERSIZE + 151, 2, "E01EVAFAD");
    fields[23] = fieldE01PLPBRN =
    new DecimalField(message, HEADERSIZE + 153, 4, 0, "E01PLPBRN");
    fields[24] = fieldE01PLPBRD =
    new CharacterField(message, HEADERSIZE + 157, 35, "E01PLPBRD");
    fields[25] = fieldE01PLPEJE =
    new CharacterField(message, HEADERSIZE + 192, 4, "E01PLPEJE");
    fields[26] = fieldE01PLPEJD =
    new CharacterField(message, HEADERSIZE + 196, 35, "E01PLPEJD");
    fields[27] = fieldE01PLTPRO =
    new CharacterField(message, HEADERSIZE + 231, 4, "E01PLTPRO");
    fields[28] = fieldE01PLTTYP =
    new CharacterField(message, HEADERSIZE + 235, 4, "E01PLTTYP");
    fields[29] = fieldE01PLTPRD =
    new CharacterField(message, HEADERSIZE + 239, 35, "E01PLTPRD");
    fields[30] = fieldE01PLTAMN =
    new DecimalField(message, HEADERSIZE + 274, 17, 2, "E01PLTAMN");
    fields[31] = fieldE01PLTPLZ =
    new DecimalField(message, HEADERSIZE + 291, 5, 0, "E01PLTPLZ");
    fields[32] = fieldE01PLTPLD =
    new CharacterField(message, HEADERSIZE + 296, 15, "E01PLTPLD");
    fields[33] = fieldE01PLTRTE =
    new DecimalField(message, HEADERSIZE + 311, 11, 6, "E01PLTRTE");
    fields[34] = fieldE01PLTCOL =
    new CharacterField(message, HEADERSIZE + 322, 4, "E01PLTCOL");
    fields[35] = fieldE01PLTCOD =
    new CharacterField(message, HEADERSIZE + 326, 35, "E01PLTCOD");
    fields[36] = fieldE01EVAING =
    new DecimalField(message, HEADERSIZE + 361, 17, 2, "E01EVAING");
    fields[37] = fieldE01DEBMON =
    new DecimalField(message, HEADERSIZE + 378, 17, 2, "E01DEBMON");
    fields[38] = fieldE01DEBACT =
    new DecimalField(message, HEADERSIZE + 395, 17, 2, "E01DEBACT");
    fields[39] = fieldE01MAXODF =
    new DecimalField(message, HEADERSIZE + 412, 17, 2, "E01MAXODF");
    fields[40] = fieldE01MAXOPL =
    new DecimalField(message, HEADERSIZE + 429, 17, 2, "E01MAXOPL");
    fields[41] = fieldE01MINODF =
    new DecimalField(message, HEADERSIZE + 446, 17, 2, "E01MINODF");
    fields[42] = fieldE01MINOPL =
    new DecimalField(message, HEADERSIZE + 463, 17, 2, "E01MINOPL");
    fields[43] = fieldE01TOTPNT =
    new DecimalField(message, HEADERSIZE + 480, 9, 2, "E01TOTPNT");
    fields[44] = fieldE01CALRIS =
    new CharacterField(message, HEADERSIZE + 489, 4, "E01CALRIS");
    fields[45] = fieldE01FAIPRB =
    new DecimalField(message, HEADERSIZE + 493, 9, 2, "E01FAIPRB");
    fields[46] = fieldE01SPEPRO =
    new DecimalField(message, HEADERSIZE + 502, 17, 2, "E01SPEPRO");
    fields[47] = fieldE01SCOREC =
    new CharacterField(message, HEADERSIZE + 519, 15, "E01SCOREC");
    fields[48] = fieldE01AMNMXI =
    new DecimalField(message, HEADERSIZE + 534, 17, 2, "E01AMNMXI");
    fields[49] = fieldE01AMNMXC =
    new DecimalField(message, HEADERSIZE + 551, 17, 2, "E01AMNMXC");
    fields[50] = fieldE01EVAOBS =
    new CharacterField(message, HEADERSIZE + 568, 800, "E01EVAOBS");
    fields[51] = fieldE01IDANAL =
    new CharacterField(message, HEADERSIZE + 1368, 10, "E01IDANAL");
    fields[52] = fieldE01DSANAL =
    new CharacterField(message, HEADERSIZE + 1378, 35, "E01DSANAL");
    fields[53] = fieldE01IDMANA =
    new CharacterField(message, HEADERSIZE + 1413, 10, "E01IDMANA");
    fields[54] = fieldE01DSMANA =
    new CharacterField(message, HEADERSIZE + 1423, 35, "E01DSMANA");
    fields[55] = fieldE01RECPOS =
    new DecimalField(message, HEADERSIZE + 1458, 8, 0, "E01RECPOS");
    fields[56] = fieldE01OPECDE =
    new CharacterField(message, HEADERSIZE + 1466, 1, "E01OPECDE");

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
  * Set field E01CNTLAN using a String value.
  */
  public void setE01CNTLAN(String newvalue)
  {
    fieldE01CNTLAN.setString(newvalue);
  }

  /**
  * Get value of field E01CNTLAN as a String.
  */
  public String getE01CNTLAN()
  {
    return fieldE01CNTLAN.getString();
  }

  /**
  * Set field E01PLPNPR using a String value.
  */
  public void setE01PLPNPR(String newvalue)
  {
    fieldE01PLPNPR.setString(newvalue);
  }

  /**
  * Get value of field E01PLPNPR as a String.
  */
  public String getE01PLPNPR()
  {
    return fieldE01PLPNPR.getString();
  }

  /**
  * Set numeric field E01PLPNPR using a BigDecimal value.
  */
  public void setE01PLPNPR(BigDecimal newvalue)
  {
    fieldE01PLPNPR.setBigDecimal(newvalue);
  }

  /**
  * Return value of numeric field E01PLPNPR as a BigDecimal.
  */
  public BigDecimal getBigDecimalE01PLPNPR()
  {
    return fieldE01PLPNPR.getBigDecimal();
  }

  /**
  * Set field E01CUSCUN using a String value.
  */
  public void setE01CUSCUN(String newvalue)
  {
    fieldE01CUSCUN.setString(newvalue);
  }

  /**
  * Get value of field E01CUSCUN as a String.
  */
  public String getE01CUSCUN()
  {
    return fieldE01CUSCUN.getString();
  }

  /**
  * Set numeric field E01CUSCUN using a BigDecimal value.
  */
  public void setE01CUSCUN(BigDecimal newvalue)
  {
    fieldE01CUSCUN.setBigDecimal(newvalue);
  }

  /**
  * Return value of numeric field E01CUSCUN as a BigDecimal.
  */
  public BigDecimal getBigDecimalE01CUSCUN()
  {
    return fieldE01CUSCUN.getBigDecimal();
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
  * Set field E01CUSLGT using a String value.
  */
  public void setE01CUSLGT(String newvalue)
  {
    fieldE01CUSLGT.setString(newvalue);
  }

  /**
  * Get value of field E01CUSLGT as a String.
  */
  public String getE01CUSLGT()
  {
    return fieldE01CUSLGT.getString();
  }

  /**
  * Set field E01CUSIDN using a String value.
  */
  public void setE01CUSIDN(String newvalue)
  {
    fieldE01CUSIDN.setString(newvalue);
  }

  /**
  * Get value of field E01CUSIDN as a String.
  */
  public String getE01CUSIDN()
  {
    return fieldE01CUSIDN.getString();
  }

  /**
  * Set field E01SUMAGE using a String value.
  */
  public void setE01SUMAGE(String newvalue)
  {
    fieldE01SUMAGE.setString(newvalue);
  }

  /**
  * Get value of field E01SUMAGE as a String.
  */
  public String getE01SUMAGE()
  {
    return fieldE01SUMAGE.getString();
  }

  /**
  * Set numeric field E01SUMAGE using a BigDecimal value.
  */
  public void setE01SUMAGE(BigDecimal newvalue)
  {
    fieldE01SUMAGE.setBigDecimal(newvalue);
  }

  /**
  * Return value of numeric field E01SUMAGE as a BigDecimal.
  */
  public BigDecimal getBigDecimalE01SUMAGE()
  {
    return fieldE01SUMAGE.getBigDecimal();
  }

  /**
  * Set field E01SUMGEN using a String value.
  */
  public void setE01SUMGEN(String newvalue)
  {
    fieldE01SUMGEN.setString(newvalue);
  }

  /**
  * Get value of field E01SUMGEN as a String.
  */
  public String getE01SUMGEN()
  {
    return fieldE01SUMGEN.getString();
  }

  /**
  * Set field E01SUMYYF using a String value.
  */
  public void setE01SUMYYF(String newvalue)
  {
    fieldE01SUMYYF.setString(newvalue);
  }

  /**
  * Get value of field E01SUMYYF as a String.
  */
  public String getE01SUMYYF()
  {
    return fieldE01SUMYYF.getString();
  }

  /**
  * Set field E01SUMMMF using a String value.
  */
  public void setE01SUMMMF(String newvalue)
  {
    fieldE01SUMMMF.setString(newvalue);
  }

  /**
  * Get value of field E01SUMMMF as a String.
  */
  public String getE01SUMMMF()
  {
    return fieldE01SUMMMF.getString();
  }

  /**
  * Set field E01SUMDDF using a String value.
  */
  public void setE01SUMDDF(String newvalue)
  {
    fieldE01SUMDDF.setString(newvalue);
  }

  /**
  * Get value of field E01SUMDDF as a String.
  */
  public String getE01SUMDDF()
  {
    return fieldE01SUMDDF.getString();
  }

  /**
  * Set field E01EVAFAY using a String value.
  */
  public void setE01EVAFAY(String newvalue)
  {
    fieldE01EVAFAY.setString(newvalue);
  }

  /**
  * Get value of field E01EVAFAY as a String.
  */
  public String getE01EVAFAY()
  {
    return fieldE01EVAFAY.getString();
  }

  /**
  * Set field E01EVAFAM using a String value.
  */
  public void setE01EVAFAM(String newvalue)
  {
    fieldE01EVAFAM.setString(newvalue);
  }

  /**
  * Get value of field E01EVAFAM as a String.
  */
  public String getE01EVAFAM()
  {
    return fieldE01EVAFAM.getString();
  }

  /**
  * Set field E01EVAFAD using a String value.
  */
  public void setE01EVAFAD(String newvalue)
  {
    fieldE01EVAFAD.setString(newvalue);
  }

  /**
  * Get value of field E01EVAFAD as a String.
  */
  public String getE01EVAFAD()
  {
    return fieldE01EVAFAD.getString();
  }

  /**
  * Set field E01PLPBRN using a String value.
  */
  public void setE01PLPBRN(String newvalue)
  {
    fieldE01PLPBRN.setString(newvalue);
  }

  /**
  * Get value of field E01PLPBRN as a String.
  */
  public String getE01PLPBRN()
  {
    return fieldE01PLPBRN.getString();
  }

  /**
  * Set numeric field E01PLPBRN using a BigDecimal value.
  */
  public void setE01PLPBRN(BigDecimal newvalue)
  {
    fieldE01PLPBRN.setBigDecimal(newvalue);
  }

  /**
  * Return value of numeric field E01PLPBRN as a BigDecimal.
  */
  public BigDecimal getBigDecimalE01PLPBRN()
  {
    return fieldE01PLPBRN.getBigDecimal();
  }

  /**
  * Set field E01PLPBRD using a String value.
  */
  public void setE01PLPBRD(String newvalue)
  {
    fieldE01PLPBRD.setString(newvalue);
  }

  /**
  * Get value of field E01PLPBRD as a String.
  */
  public String getE01PLPBRD()
  {
    return fieldE01PLPBRD.getString();
  }

  /**
  * Set field E01PLPEJE using a String value.
  */
  public void setE01PLPEJE(String newvalue)
  {
    fieldE01PLPEJE.setString(newvalue);
  }

  /**
  * Get value of field E01PLPEJE as a String.
  */
  public String getE01PLPEJE()
  {
    return fieldE01PLPEJE.getString();
  }

  /**
  * Set field E01PLPEJD using a String value.
  */
  public void setE01PLPEJD(String newvalue)
  {
    fieldE01PLPEJD.setString(newvalue);
  }

  /**
  * Get value of field E01PLPEJD as a String.
  */
  public String getE01PLPEJD()
  {
    return fieldE01PLPEJD.getString();
  }

  /**
  * Set field E01PLTPRO using a String value.
  */
  public void setE01PLTPRO(String newvalue)
  {
    fieldE01PLTPRO.setString(newvalue);
  }

  /**
  * Get value of field E01PLTPRO as a String.
  */
  public String getE01PLTPRO()
  {
    return fieldE01PLTPRO.getString();
  }

  /**
  * Set field E01PLTTYP using a String value.
  */
  public void setE01PLTTYP(String newvalue)
  {
    fieldE01PLTTYP.setString(newvalue);
  }

  /**
  * Get value of field E01PLTTYP as a String.
  */
  public String getE01PLTTYP()
  {
    return fieldE01PLTTYP.getString();
  }

  /**
  * Set field E01PLTPRD using a String value.
  */
  public void setE01PLTPRD(String newvalue)
  {
    fieldE01PLTPRD.setString(newvalue);
  }

  /**
  * Get value of field E01PLTPRD as a String.
  */
  public String getE01PLTPRD()
  {
    return fieldE01PLTPRD.getString();
  }

  /**
  * Set field E01PLTAMN using a String value.
  */
  public void setE01PLTAMN(String newvalue)
  {
    fieldE01PLTAMN.setString(newvalue);
  }

  /**
  * Get value of field E01PLTAMN as a String.
  */
  public String getE01PLTAMN()
  {
    return fieldE01PLTAMN.getString();
  }

  /**
  * Set numeric field E01PLTAMN using a BigDecimal value.
  */
  public void setE01PLTAMN(BigDecimal newvalue)
  {
    fieldE01PLTAMN.setBigDecimal(newvalue);
  }

  /**
  * Return value of numeric field E01PLTAMN as a BigDecimal.
  */
  public BigDecimal getBigDecimalE01PLTAMN()
  {
    return fieldE01PLTAMN.getBigDecimal();
  }

  /**
  * Set field E01PLTPLZ using a String value.
  */
  public void setE01PLTPLZ(String newvalue)
  {
    fieldE01PLTPLZ.setString(newvalue);
  }

  /**
  * Get value of field E01PLTPLZ as a String.
  */
  public String getE01PLTPLZ()
  {
    return fieldE01PLTPLZ.getString();
  }

  /**
  * Set numeric field E01PLTPLZ using a BigDecimal value.
  */
  public void setE01PLTPLZ(BigDecimal newvalue)
  {
    fieldE01PLTPLZ.setBigDecimal(newvalue);
  }

  /**
  * Return value of numeric field E01PLTPLZ as a BigDecimal.
  */
  public BigDecimal getBigDecimalE01PLTPLZ()
  {
    return fieldE01PLTPLZ.getBigDecimal();
  }

  /**
  * Set field E01PLTPLD using a String value.
  */
  public void setE01PLTPLD(String newvalue)
  {
    fieldE01PLTPLD.setString(newvalue);
  }

  /**
  * Get value of field E01PLTPLD as a String.
  */
  public String getE01PLTPLD()
  {
    return fieldE01PLTPLD.getString();
  }

  /**
  * Set field E01PLTRTE using a String value.
  */
  public void setE01PLTRTE(String newvalue)
  {
    fieldE01PLTRTE.setString(newvalue);
  }

  /**
  * Get value of field E01PLTRTE as a String.
  */
  public String getE01PLTRTE()
  {
    return fieldE01PLTRTE.getString();
  }

  /**
  * Set numeric field E01PLTRTE using a BigDecimal value.
  */
  public void setE01PLTRTE(BigDecimal newvalue)
  {
    fieldE01PLTRTE.setBigDecimal(newvalue);
  }

  /**
  * Return value of numeric field E01PLTRTE as a BigDecimal.
  */
  public BigDecimal getBigDecimalE01PLTRTE()
  {
    return fieldE01PLTRTE.getBigDecimal();
  }

  /**
  * Set field E01PLTCOL using a String value.
  */
  public void setE01PLTCOL(String newvalue)
  {
    fieldE01PLTCOL.setString(newvalue);
  }

  /**
  * Get value of field E01PLTCOL as a String.
  */
  public String getE01PLTCOL()
  {
    return fieldE01PLTCOL.getString();
  }

  /**
  * Set field E01PLTCOD using a String value.
  */
  public void setE01PLTCOD(String newvalue)
  {
    fieldE01PLTCOD.setString(newvalue);
  }

  /**
  * Get value of field E01PLTCOD as a String.
  */
  public String getE01PLTCOD()
  {
    return fieldE01PLTCOD.getString();
  }

  /**
  * Set field E01EVAING using a String value.
  */
  public void setE01EVAING(String newvalue)
  {
    fieldE01EVAING.setString(newvalue);
  }

  /**
  * Get value of field E01EVAING as a String.
  */
  public String getE01EVAING()
  {
    return fieldE01EVAING.getString();
  }

  /**
  * Set numeric field E01EVAING using a BigDecimal value.
  */
  public void setE01EVAING(BigDecimal newvalue)
  {
    fieldE01EVAING.setBigDecimal(newvalue);
  }

  /**
  * Return value of numeric field E01EVAING as a BigDecimal.
  */
  public BigDecimal getBigDecimalE01EVAING()
  {
    return fieldE01EVAING.getBigDecimal();
  }

  /**
  * Set field E01DEBMON using a String value.
  */
  public void setE01DEBMON(String newvalue)
  {
    fieldE01DEBMON.setString(newvalue);
  }

  /**
  * Get value of field E01DEBMON as a String.
  */
  public String getE01DEBMON()
  {
    return fieldE01DEBMON.getString();
  }

  /**
  * Set numeric field E01DEBMON using a BigDecimal value.
  */
  public void setE01DEBMON(BigDecimal newvalue)
  {
    fieldE01DEBMON.setBigDecimal(newvalue);
  }

  /**
  * Return value of numeric field E01DEBMON as a BigDecimal.
  */
  public BigDecimal getBigDecimalE01DEBMON()
  {
    return fieldE01DEBMON.getBigDecimal();
  }

  /**
  * Set field E01DEBACT using a String value.
  */
  public void setE01DEBACT(String newvalue)
  {
    fieldE01DEBACT.setString(newvalue);
  }

  /**
  * Get value of field E01DEBACT as a String.
  */
  public String getE01DEBACT()
  {
    return fieldE01DEBACT.getString();
  }

  /**
  * Set numeric field E01DEBACT using a BigDecimal value.
  */
  public void setE01DEBACT(BigDecimal newvalue)
  {
    fieldE01DEBACT.setBigDecimal(newvalue);
  }

  /**
  * Return value of numeric field E01DEBACT as a BigDecimal.
  */
  public BigDecimal getBigDecimalE01DEBACT()
  {
    return fieldE01DEBACT.getBigDecimal();
  }

  /**
  * Set field E01MAXODF using a String value.
  */
  public void setE01MAXODF(String newvalue)
  {
    fieldE01MAXODF.setString(newvalue);
  }

  /**
  * Get value of field E01MAXODF as a String.
  */
  public String getE01MAXODF()
  {
    return fieldE01MAXODF.getString();
  }

  /**
  * Set numeric field E01MAXODF using a BigDecimal value.
  */
  public void setE01MAXODF(BigDecimal newvalue)
  {
    fieldE01MAXODF.setBigDecimal(newvalue);
  }

  /**
  * Return value of numeric field E01MAXODF as a BigDecimal.
  */
  public BigDecimal getBigDecimalE01MAXODF()
  {
    return fieldE01MAXODF.getBigDecimal();
  }

  /**
  * Set field E01MAXOPL using a String value.
  */
  public void setE01MAXOPL(String newvalue)
  {
    fieldE01MAXOPL.setString(newvalue);
  }

  /**
  * Get value of field E01MAXOPL as a String.
  */
  public String getE01MAXOPL()
  {
    return fieldE01MAXOPL.getString();
  }

  /**
  * Set numeric field E01MAXOPL using a BigDecimal value.
  */
  public void setE01MAXOPL(BigDecimal newvalue)
  {
    fieldE01MAXOPL.setBigDecimal(newvalue);
  }

  /**
  * Return value of numeric field E01MAXOPL as a BigDecimal.
  */
  public BigDecimal getBigDecimalE01MAXOPL()
  {
    return fieldE01MAXOPL.getBigDecimal();
  }

  /**
  * Set field E01MINODF using a String value.
  */
  public void setE01MINODF(String newvalue)
  {
    fieldE01MINODF.setString(newvalue);
  }

  /**
  * Get value of field E01MINODF as a String.
  */
  public String getE01MINODF()
  {
    return fieldE01MINODF.getString();
  }

  /**
  * Set numeric field E01MINODF using a BigDecimal value.
  */
  public void setE01MINODF(BigDecimal newvalue)
  {
    fieldE01MINODF.setBigDecimal(newvalue);
  }

  /**
  * Return value of numeric field E01MINODF as a BigDecimal.
  */
  public BigDecimal getBigDecimalE01MINODF()
  {
    return fieldE01MINODF.getBigDecimal();
  }

  /**
  * Set field E01MINOPL using a String value.
  */
  public void setE01MINOPL(String newvalue)
  {
    fieldE01MINOPL.setString(newvalue);
  }

  /**
  * Get value of field E01MINOPL as a String.
  */
  public String getE01MINOPL()
  {
    return fieldE01MINOPL.getString();
  }

  /**
  * Set numeric field E01MINOPL using a BigDecimal value.
  */
  public void setE01MINOPL(BigDecimal newvalue)
  {
    fieldE01MINOPL.setBigDecimal(newvalue);
  }

  /**
  * Return value of numeric field E01MINOPL as a BigDecimal.
  */
  public BigDecimal getBigDecimalE01MINOPL()
  {
    return fieldE01MINOPL.getBigDecimal();
  }

  /**
  * Set field E01TOTPNT using a String value.
  */
  public void setE01TOTPNT(String newvalue)
  {
    fieldE01TOTPNT.setString(newvalue);
  }

  /**
  * Get value of field E01TOTPNT as a String.
  */
  public String getE01TOTPNT()
  {
    return fieldE01TOTPNT.getString();
  }

  /**
  * Set numeric field E01TOTPNT using a BigDecimal value.
  */
  public void setE01TOTPNT(BigDecimal newvalue)
  {
    fieldE01TOTPNT.setBigDecimal(newvalue);
  }

  /**
  * Return value of numeric field E01TOTPNT as a BigDecimal.
  */
  public BigDecimal getBigDecimalE01TOTPNT()
  {
    return fieldE01TOTPNT.getBigDecimal();
  }

  /**
  * Set field E01CALRIS using a String value.
  */
  public void setE01CALRIS(String newvalue)
  {
    fieldE01CALRIS.setString(newvalue);
  }

  /**
  * Get value of field E01CALRIS as a String.
  */
  public String getE01CALRIS()
  {
    return fieldE01CALRIS.getString();
  }

  /**
  * Set field E01FAIPRB using a String value.
  */
  public void setE01FAIPRB(String newvalue)
  {
    fieldE01FAIPRB.setString(newvalue);
  }

  /**
  * Get value of field E01FAIPRB as a String.
  */
  public String getE01FAIPRB()
  {
    return fieldE01FAIPRB.getString();
  }

  /**
  * Set numeric field E01FAIPRB using a BigDecimal value.
  */
  public void setE01FAIPRB(BigDecimal newvalue)
  {
    fieldE01FAIPRB.setBigDecimal(newvalue);
  }

  /**
  * Return value of numeric field E01FAIPRB as a BigDecimal.
  */
  public BigDecimal getBigDecimalE01FAIPRB()
  {
    return fieldE01FAIPRB.getBigDecimal();
  }

  /**
  * Set field E01SPEPRO using a String value.
  */
  public void setE01SPEPRO(String newvalue)
  {
    fieldE01SPEPRO.setString(newvalue);
  }

  /**
  * Get value of field E01SPEPRO as a String.
  */
  public String getE01SPEPRO()
  {
    return fieldE01SPEPRO.getString();
  }

  /**
  * Set numeric field E01SPEPRO using a BigDecimal value.
  */
  public void setE01SPEPRO(BigDecimal newvalue)
  {
    fieldE01SPEPRO.setBigDecimal(newvalue);
  }

  /**
  * Return value of numeric field E01SPEPRO as a BigDecimal.
  */
  public BigDecimal getBigDecimalE01SPEPRO()
  {
    return fieldE01SPEPRO.getBigDecimal();
  }

  /**
  * Set field E01SCOREC using a String value.
  */
  public void setE01SCOREC(String newvalue)
  {
    fieldE01SCOREC.setString(newvalue);
  }

  /**
  * Get value of field E01SCOREC as a String.
  */
  public String getE01SCOREC()
  {
    return fieldE01SCOREC.getString();
  }

  /**
  * Set field E01AMNMXI using a String value.
  */
  public void setE01AMNMXI(String newvalue)
  {
    fieldE01AMNMXI.setString(newvalue);
  }

  /**
  * Get value of field E01AMNMXI as a String.
  */
  public String getE01AMNMXI()
  {
    return fieldE01AMNMXI.getString();
  }

  /**
  * Set numeric field E01AMNMXI using a BigDecimal value.
  */
  public void setE01AMNMXI(BigDecimal newvalue)
  {
    fieldE01AMNMXI.setBigDecimal(newvalue);
  }

  /**
  * Return value of numeric field E01AMNMXI as a BigDecimal.
  */
  public BigDecimal getBigDecimalE01AMNMXI()
  {
    return fieldE01AMNMXI.getBigDecimal();
  }

  /**
  * Set field E01AMNMXC using a String value.
  */
  public void setE01AMNMXC(String newvalue)
  {
    fieldE01AMNMXC.setString(newvalue);
  }

  /**
  * Get value of field E01AMNMXC as a String.
  */
  public String getE01AMNMXC()
  {
    return fieldE01AMNMXC.getString();
  }

  /**
  * Set numeric field E01AMNMXC using a BigDecimal value.
  */
  public void setE01AMNMXC(BigDecimal newvalue)
  {
    fieldE01AMNMXC.setBigDecimal(newvalue);
  }

  /**
  * Return value of numeric field E01AMNMXC as a BigDecimal.
  */
  public BigDecimal getBigDecimalE01AMNMXC()
  {
    return fieldE01AMNMXC.getBigDecimal();
  }

  /**
  * Set field E01EVAOBS using a String value.
  */
  public void setE01EVAOBS(String newvalue)
  {
    fieldE01EVAOBS.setString(newvalue);
  }

  /**
  * Get value of field E01EVAOBS as a String.
  */
  public String getE01EVAOBS()
  {
    return fieldE01EVAOBS.getString();
  }

  /**
  * Set field E01IDANAL using a String value.
  */
  public void setE01IDANAL(String newvalue)
  {
    fieldE01IDANAL.setString(newvalue);
  }

  /**
  * Get value of field E01IDANAL as a String.
  */
  public String getE01IDANAL()
  {
    return fieldE01IDANAL.getString();
  }

  /**
  * Set field E01DSANAL using a String value.
  */
  public void setE01DSANAL(String newvalue)
  {
    fieldE01DSANAL.setString(newvalue);
  }

  /**
  * Get value of field E01DSANAL as a String.
  */
  public String getE01DSANAL()
  {
    return fieldE01DSANAL.getString();
  }

  /**
  * Set field E01IDMANA using a String value.
  */
  public void setE01IDMANA(String newvalue)
  {
    fieldE01IDMANA.setString(newvalue);
  }

  /**
  * Get value of field E01IDMANA as a String.
  */
  public String getE01IDMANA()
  {
    return fieldE01IDMANA.getString();
  }

  /**
  * Set field E01DSMANA using a String value.
  */
  public void setE01DSMANA(String newvalue)
  {
    fieldE01DSMANA.setString(newvalue);
  }

  /**
  * Get value of field E01DSMANA as a String.
  */
  public String getE01DSMANA()
  {
    return fieldE01DSMANA.getString();
  }

  /**
  * Set field E01RECPOS using a String value.
  */
  public void setE01RECPOS(String newvalue)
  {
    fieldE01RECPOS.setString(newvalue);
  }

  /**
  * Get value of field E01RECPOS as a String.
  */
  public String getE01RECPOS()
  {
    return fieldE01RECPOS.getString();
  }

  /**
  * Set numeric field E01RECPOS using a BigDecimal value.
  */
  public void setE01RECPOS(BigDecimal newvalue)
  {
    fieldE01RECPOS.setBigDecimal(newvalue);
  }

  /**
  * Return value of numeric field E01RECPOS as a BigDecimal.
  */
  public BigDecimal getBigDecimalE01RECPOS()
  {
    return fieldE01RECPOS.getBigDecimal();
  }

  /**
  * Set field E01OPECDE using a String value.
  */
  public void setE01OPECDE(String newvalue)
  {
    fieldE01OPECDE.setString(newvalue);
  }

  /**
  * Get value of field E01OPECDE as a String.
  */
  public String getE01OPECDE()
  {
    return fieldE01OPECDE.getString();
  }

}
