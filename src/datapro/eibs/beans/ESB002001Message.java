package datapro.eibs.beans;

import datapro.eibs.sockets.*;
import java.io.*;
import java.math.*;

import java.util.*;

/**
* Class generated by AS/400 CRTCLASS command from ESB002001 physical file definition.
* 
* File level identifier is 1041108185223.
* Record format level identifier is 36D2AD3F571E2.
*/

public class ESB002001Message extends MessageRecord
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
                                     "E01SBLLOG",
                                     "E01SBLDEP",
                                     "E01SBLRM1",
                                     "E01SBLRM2",
                                     "E01SBLRM3",
                                     "E01SBLRM4",
                                     "E01SBLRM5",
                                     "E01SBLVDC",
                                     "E01SBLVD1",
                                     "E01SBLVD2",
                                     "E01SBLVD3",
                                     "E01SBLITH",
                                     "E01SBLITM",
                                     "E01SBLITS",
                                     "E01SBLOTH",
                                     "E01SBLOTM",
                                     "E01SBLOTS",
                                     "E01SBLOFC",
                                     "S01FRDT1",
                                     "S01FRDT2",
                                     "S01FRDT3",
                                     "S01TODT1",
                                     "S01TODT2",
                                     "S01TODT3",
                                     "S01SBLDEP",
                                     "S01SBLCUN",
                                     "D01SBMTYP",
                                     "D01SBTDSC",
                                     "D01SBMLOC",
                                     "D01LOCNME",
                                     "D01SBMCUN",
                                     "D01CUSNA1"
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
                                   "E01SBLLOG",
                                   "E01SBLDEP",
                                   "E01SBLRM1",
                                   "E01SBLRM2",
                                   "E01SBLRM3",
                                   "E01SBLRM4",
                                   "E01SBLRM5",
                                   "E01SBLVDC",
                                   "E01SBLVD1",
                                   "E01SBLVD2",
                                   "E01SBLVD3",
                                   "E01SBLITH",
                                   "E01SBLITM",
                                   "E01SBLITS",
                                   "E01SBLOTH",
                                   "E01SBLOTM",
                                   "E01SBLOTS",
                                   "E01SBLOFC",
                                   "S01FRDT1",
                                   "S01FRDT2",
                                   "S01FRDT3",
                                   "S01TODT1",
                                   "S01TODT2",
                                   "S01TODT3",
                                   "S01SBLDEP",
                                   "S01SBLCUN",
                                   "D01SBMTYP",
                                   "D01SBTDSC",
                                   "D01SBMLOC",
                                   "D01LOCNME",
                                   "D01SBMCUN",
                                   "D01CUSNA1"
                                 };
  final static String fid = "1041108185223";
  final static String rid = "36D2AD3F571E2";
  final static String fmtname = "ESB002001";
  final int FIELDCOUNT = 41;
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
  private CharacterField fieldE01SBLLOG = null;
  private DecimalField fieldE01SBLDEP = null;
  private CharacterField fieldE01SBLRM1 = null;
  private CharacterField fieldE01SBLRM2 = null;
  private CharacterField fieldE01SBLRM3 = null;
  private CharacterField fieldE01SBLRM4 = null;
  private CharacterField fieldE01SBLRM5 = null;
  private DecimalField fieldE01SBLVDC = null;
  private DecimalField fieldE01SBLVD1 = null;
  private DecimalField fieldE01SBLVD2 = null;
  private DecimalField fieldE01SBLVD3 = null;
  private DecimalField fieldE01SBLITH = null;
  private DecimalField fieldE01SBLITM = null;
  private DecimalField fieldE01SBLITS = null;
  private DecimalField fieldE01SBLOTH = null;
  private DecimalField fieldE01SBLOTM = null;
  private DecimalField fieldE01SBLOTS = null;
  private CharacterField fieldE01SBLOFC = null;
  private DecimalField fieldS01FRDT1 = null;
  private DecimalField fieldS01FRDT2 = null;
  private DecimalField fieldS01FRDT3 = null;
  private DecimalField fieldS01TODT1 = null;
  private DecimalField fieldS01TODT2 = null;
  private DecimalField fieldS01TODT3 = null;
  private DecimalField fieldS01SBLDEP = null;
  private DecimalField fieldS01SBLCUN = null;
  private DecimalField fieldD01SBMTYP = null;
  private CharacterField fieldD01SBTDSC = null;
  private CharacterField fieldD01SBMLOC = null;
  private CharacterField fieldD01LOCNME = null;
  private DecimalField fieldD01SBMCUN = null;
  private CharacterField fieldD01CUSNA1 = null;

  /**
  * Constructor for ESB002001Message.
  */
  public ESB002001Message()
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
    recordsize = 533;
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
    fields[9] = fieldE01SBLLOG =
    new CharacterField(message, HEADERSIZE + 42, 26, "E01SBLLOG");
    fields[10] = fieldE01SBLDEP =
    new DecimalField(message, HEADERSIZE + 68, 10, 0, "E01SBLDEP");
    fields[11] = fieldE01SBLRM1 =
    new CharacterField(message, HEADERSIZE + 78, 50, "E01SBLRM1");
    fields[12] = fieldE01SBLRM2 =
    new CharacterField(message, HEADERSIZE + 128, 50, "E01SBLRM2");
    fields[13] = fieldE01SBLRM3 =
    new CharacterField(message, HEADERSIZE + 178, 50, "E01SBLRM3");
    fields[14] = fieldE01SBLRM4 =
    new CharacterField(message, HEADERSIZE + 228, 50, "E01SBLRM4");
    fields[15] = fieldE01SBLRM5 =
    new CharacterField(message, HEADERSIZE + 278, 50, "E01SBLRM5");
    fields[16] = fieldE01SBLVDC =
    new DecimalField(message, HEADERSIZE + 328, 2, 0, "E01SBLVDC");
    fields[17] = fieldE01SBLVD1 =
    new DecimalField(message, HEADERSIZE + 330, 3, 0, "E01SBLVD1");
    fields[18] = fieldE01SBLVD2 =
    new DecimalField(message, HEADERSIZE + 333, 3, 0, "E01SBLVD2");
    fields[19] = fieldE01SBLVD3 =
    new DecimalField(message, HEADERSIZE + 336, 3, 0, "E01SBLVD3");
    fields[20] = fieldE01SBLITH =
    new DecimalField(message, HEADERSIZE + 339, 3, 0, "E01SBLITH");
    fields[21] = fieldE01SBLITM =
    new DecimalField(message, HEADERSIZE + 342, 3, 0, "E01SBLITM");
    fields[22] = fieldE01SBLITS =
    new DecimalField(message, HEADERSIZE + 345, 3, 0, "E01SBLITS");
    fields[23] = fieldE01SBLOTH =
    new DecimalField(message, HEADERSIZE + 348, 3, 0, "E01SBLOTH");
    fields[24] = fieldE01SBLOTM =
    new DecimalField(message, HEADERSIZE + 351, 3, 0, "E01SBLOTM");
    fields[25] = fieldE01SBLOTS =
    new DecimalField(message, HEADERSIZE + 354, 3, 0, "E01SBLOTS");
    fields[26] = fieldE01SBLOFC =
    new CharacterField(message, HEADERSIZE + 357, 5, "E01SBLOFC");
    fields[27] = fieldS01FRDT1 =
    new DecimalField(message, HEADERSIZE + 362, 3, 0, "S01FRDT1");
    fields[28] = fieldS01FRDT2 =
    new DecimalField(message, HEADERSIZE + 365, 3, 0, "S01FRDT2");
    fields[29] = fieldS01FRDT3 =
    new DecimalField(message, HEADERSIZE + 368, 3, 0, "S01FRDT3");
    fields[30] = fieldS01TODT1 =
    new DecimalField(message, HEADERSIZE + 371, 3, 0, "S01TODT1");
    fields[31] = fieldS01TODT2 =
    new DecimalField(message, HEADERSIZE + 374, 3, 0, "S01TODT2");
    fields[32] = fieldS01TODT3 =
    new DecimalField(message, HEADERSIZE + 377, 3, 0, "S01TODT3");
    fields[33] = fieldS01SBLDEP =
    new DecimalField(message, HEADERSIZE + 380, 10, 0, "S01SBLDEP");
    fields[34] = fieldS01SBLCUN =
    new DecimalField(message, HEADERSIZE + 390, 10, 0, "S01SBLCUN");
    fields[35] = fieldD01SBMTYP =
    new DecimalField(message, HEADERSIZE + 400, 5, 0, "D01SBMTYP");
    fields[36] = fieldD01SBTDSC =
    new CharacterField(message, HEADERSIZE + 405, 50, "D01SBTDSC");
    fields[37] = fieldD01SBMLOC =
    new CharacterField(message, HEADERSIZE + 455, 3, "D01SBMLOC");
    fields[38] = fieldD01LOCNME =
    new CharacterField(message, HEADERSIZE + 458, 20, "D01LOCNME");
    fields[39] = fieldD01SBMCUN =
    new DecimalField(message, HEADERSIZE + 478, 10, 0, "D01SBMCUN");
    fields[40] = fieldD01CUSNA1 =
    new CharacterField(message, HEADERSIZE + 488, 45, "D01CUSNA1");

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
  * Set field E01SBLLOG using a String value.
  */
  public void setE01SBLLOG(String newvalue)
  {
    fieldE01SBLLOG.setString(newvalue);
  }

  /**
  * Get value of field E01SBLLOG as a String.
  */
  public String getE01SBLLOG()
  {
    return fieldE01SBLLOG.getString();
  }

  /**
  * Set field E01SBLDEP using a String value.
  */
  public void setE01SBLDEP(String newvalue)
  {
    fieldE01SBLDEP.setString(newvalue);
  }

  /**
  * Get value of field E01SBLDEP as a String.
  */
  public String getE01SBLDEP()
  {
    return fieldE01SBLDEP.getString();
  }

  /**
  * Set numeric field E01SBLDEP using a BigDecimal value.
  */
  public void setE01SBLDEP(BigDecimal newvalue)
  {
    fieldE01SBLDEP.setBigDecimal(newvalue);
  }

  /**
  * Return value of numeric field E01SBLDEP as a BigDecimal.
  */
  public BigDecimal getBigDecimalE01SBLDEP()
  {
    return fieldE01SBLDEP.getBigDecimal();
  }

  /**
  * Set field E01SBLRM1 using a String value.
  */
  public void setE01SBLRM1(String newvalue)
  {
    fieldE01SBLRM1.setString(newvalue);
  }

  /**
  * Get value of field E01SBLRM1 as a String.
  */
  public String getE01SBLRM1()
  {
    return fieldE01SBLRM1.getString();
  }

  /**
  * Set field E01SBLRM2 using a String value.
  */
  public void setE01SBLRM2(String newvalue)
  {
    fieldE01SBLRM2.setString(newvalue);
  }

  /**
  * Get value of field E01SBLRM2 as a String.
  */
  public String getE01SBLRM2()
  {
    return fieldE01SBLRM2.getString();
  }

  /**
  * Set field E01SBLRM3 using a String value.
  */
  public void setE01SBLRM3(String newvalue)
  {
    fieldE01SBLRM3.setString(newvalue);
  }

  /**
  * Get value of field E01SBLRM3 as a String.
  */
  public String getE01SBLRM3()
  {
    return fieldE01SBLRM3.getString();
  }

  /**
  * Set field E01SBLRM4 using a String value.
  */
  public void setE01SBLRM4(String newvalue)
  {
    fieldE01SBLRM4.setString(newvalue);
  }

  /**
  * Get value of field E01SBLRM4 as a String.
  */
  public String getE01SBLRM4()
  {
    return fieldE01SBLRM4.getString();
  }

  /**
  * Set field E01SBLRM5 using a String value.
  */
  public void setE01SBLRM5(String newvalue)
  {
    fieldE01SBLRM5.setString(newvalue);
  }

  /**
  * Get value of field E01SBLRM5 as a String.
  */
  public String getE01SBLRM5()
  {
    return fieldE01SBLRM5.getString();
  }

  /**
  * Set field E01SBLVDC using a String value.
  */
  public void setE01SBLVDC(String newvalue)
  {
    fieldE01SBLVDC.setString(newvalue);
  }

  /**
  * Get value of field E01SBLVDC as a String.
  */
  public String getE01SBLVDC()
  {
    return fieldE01SBLVDC.getString();
  }

  /**
  * Set numeric field E01SBLVDC using a BigDecimal value.
  */
  public void setE01SBLVDC(BigDecimal newvalue)
  {
    fieldE01SBLVDC.setBigDecimal(newvalue);
  }

  /**
  * Return value of numeric field E01SBLVDC as a BigDecimal.
  */
  public BigDecimal getBigDecimalE01SBLVDC()
  {
    return fieldE01SBLVDC.getBigDecimal();
  }

  /**
  * Set field E01SBLVD1 using a String value.
  */
  public void setE01SBLVD1(String newvalue)
  {
    fieldE01SBLVD1.setString(newvalue);
  }

  /**
  * Get value of field E01SBLVD1 as a String.
  */
  public String getE01SBLVD1()
  {
    return fieldE01SBLVD1.getString();
  }

  /**
  * Set numeric field E01SBLVD1 using a BigDecimal value.
  */
  public void setE01SBLVD1(BigDecimal newvalue)
  {
    fieldE01SBLVD1.setBigDecimal(newvalue);
  }

  /**
  * Return value of numeric field E01SBLVD1 as a BigDecimal.
  */
  public BigDecimal getBigDecimalE01SBLVD1()
  {
    return fieldE01SBLVD1.getBigDecimal();
  }

  /**
  * Set field E01SBLVD2 using a String value.
  */
  public void setE01SBLVD2(String newvalue)
  {
    fieldE01SBLVD2.setString(newvalue);
  }

  /**
  * Get value of field E01SBLVD2 as a String.
  */
  public String getE01SBLVD2()
  {
    return fieldE01SBLVD2.getString();
  }

  /**
  * Set numeric field E01SBLVD2 using a BigDecimal value.
  */
  public void setE01SBLVD2(BigDecimal newvalue)
  {
    fieldE01SBLVD2.setBigDecimal(newvalue);
  }

  /**
  * Return value of numeric field E01SBLVD2 as a BigDecimal.
  */
  public BigDecimal getBigDecimalE01SBLVD2()
  {
    return fieldE01SBLVD2.getBigDecimal();
  }

  /**
  * Set field E01SBLVD3 using a String value.
  */
  public void setE01SBLVD3(String newvalue)
  {
    fieldE01SBLVD3.setString(newvalue);
  }

  /**
  * Get value of field E01SBLVD3 as a String.
  */
  public String getE01SBLVD3()
  {
    return fieldE01SBLVD3.getString();
  }

  /**
  * Set numeric field E01SBLVD3 using a BigDecimal value.
  */
  public void setE01SBLVD3(BigDecimal newvalue)
  {
    fieldE01SBLVD3.setBigDecimal(newvalue);
  }

  /**
  * Return value of numeric field E01SBLVD3 as a BigDecimal.
  */
  public BigDecimal getBigDecimalE01SBLVD3()
  {
    return fieldE01SBLVD3.getBigDecimal();
  }

  /**
  * Set field E01SBLITH using a String value.
  */
  public void setE01SBLITH(String newvalue)
  {
    fieldE01SBLITH.setString(newvalue);
  }

  /**
  * Get value of field E01SBLITH as a String.
  */
  public String getE01SBLITH()
  {
    return fieldE01SBLITH.getString();
  }

  /**
  * Set numeric field E01SBLITH using a BigDecimal value.
  */
  public void setE01SBLITH(BigDecimal newvalue)
  {
    fieldE01SBLITH.setBigDecimal(newvalue);
  }

  /**
  * Return value of numeric field E01SBLITH as a BigDecimal.
  */
  public BigDecimal getBigDecimalE01SBLITH()
  {
    return fieldE01SBLITH.getBigDecimal();
  }

  /**
  * Set field E01SBLITM using a String value.
  */
  public void setE01SBLITM(String newvalue)
  {
    fieldE01SBLITM.setString(newvalue);
  }

  /**
  * Get value of field E01SBLITM as a String.
  */
  public String getE01SBLITM()
  {
    return fieldE01SBLITM.getString();
  }

  /**
  * Set numeric field E01SBLITM using a BigDecimal value.
  */
  public void setE01SBLITM(BigDecimal newvalue)
  {
    fieldE01SBLITM.setBigDecimal(newvalue);
  }

  /**
  * Return value of numeric field E01SBLITM as a BigDecimal.
  */
  public BigDecimal getBigDecimalE01SBLITM()
  {
    return fieldE01SBLITM.getBigDecimal();
  }

  /**
  * Set field E01SBLITS using a String value.
  */
  public void setE01SBLITS(String newvalue)
  {
    fieldE01SBLITS.setString(newvalue);
  }

  /**
  * Get value of field E01SBLITS as a String.
  */
  public String getE01SBLITS()
  {
    return fieldE01SBLITS.getString();
  }

  /**
  * Set numeric field E01SBLITS using a BigDecimal value.
  */
  public void setE01SBLITS(BigDecimal newvalue)
  {
    fieldE01SBLITS.setBigDecimal(newvalue);
  }

  /**
  * Return value of numeric field E01SBLITS as a BigDecimal.
  */
  public BigDecimal getBigDecimalE01SBLITS()
  {
    return fieldE01SBLITS.getBigDecimal();
  }

  /**
  * Set field E01SBLOTH using a String value.
  */
  public void setE01SBLOTH(String newvalue)
  {
    fieldE01SBLOTH.setString(newvalue);
  }

  /**
  * Get value of field E01SBLOTH as a String.
  */
  public String getE01SBLOTH()
  {
    return fieldE01SBLOTH.getString();
  }

  /**
  * Set numeric field E01SBLOTH using a BigDecimal value.
  */
  public void setE01SBLOTH(BigDecimal newvalue)
  {
    fieldE01SBLOTH.setBigDecimal(newvalue);
  }

  /**
  * Return value of numeric field E01SBLOTH as a BigDecimal.
  */
  public BigDecimal getBigDecimalE01SBLOTH()
  {
    return fieldE01SBLOTH.getBigDecimal();
  }

  /**
  * Set field E01SBLOTM using a String value.
  */
  public void setE01SBLOTM(String newvalue)
  {
    fieldE01SBLOTM.setString(newvalue);
  }

  /**
  * Get value of field E01SBLOTM as a String.
  */
  public String getE01SBLOTM()
  {
    return fieldE01SBLOTM.getString();
  }

  /**
  * Set numeric field E01SBLOTM using a BigDecimal value.
  */
  public void setE01SBLOTM(BigDecimal newvalue)
  {
    fieldE01SBLOTM.setBigDecimal(newvalue);
  }

  /**
  * Return value of numeric field E01SBLOTM as a BigDecimal.
  */
  public BigDecimal getBigDecimalE01SBLOTM()
  {
    return fieldE01SBLOTM.getBigDecimal();
  }

  /**
  * Set field E01SBLOTS using a String value.
  */
  public void setE01SBLOTS(String newvalue)
  {
    fieldE01SBLOTS.setString(newvalue);
  }

  /**
  * Get value of field E01SBLOTS as a String.
  */
  public String getE01SBLOTS()
  {
    return fieldE01SBLOTS.getString();
  }

  /**
  * Set numeric field E01SBLOTS using a BigDecimal value.
  */
  public void setE01SBLOTS(BigDecimal newvalue)
  {
    fieldE01SBLOTS.setBigDecimal(newvalue);
  }

  /**
  * Return value of numeric field E01SBLOTS as a BigDecimal.
  */
  public BigDecimal getBigDecimalE01SBLOTS()
  {
    return fieldE01SBLOTS.getBigDecimal();
  }

  /**
  * Set field E01SBLOFC using a String value.
  */
  public void setE01SBLOFC(String newvalue)
  {
    fieldE01SBLOFC.setString(newvalue);
  }

  /**
  * Get value of field E01SBLOFC as a String.
  */
  public String getE01SBLOFC()
  {
    return fieldE01SBLOFC.getString();
  }

  /**
  * Set field S01FRDT1 using a String value.
  */
  public void setS01FRDT1(String newvalue)
  {
    fieldS01FRDT1.setString(newvalue);
  }

  /**
  * Get value of field S01FRDT1 as a String.
  */
  public String getS01FRDT1()
  {
    return fieldS01FRDT1.getString();
  }

  /**
  * Set numeric field S01FRDT1 using a BigDecimal value.
  */
  public void setS01FRDT1(BigDecimal newvalue)
  {
    fieldS01FRDT1.setBigDecimal(newvalue);
  }

  /**
  * Return value of numeric field S01FRDT1 as a BigDecimal.
  */
  public BigDecimal getBigDecimalS01FRDT1()
  {
    return fieldS01FRDT1.getBigDecimal();
  }

  /**
  * Set field S01FRDT2 using a String value.
  */
  public void setS01FRDT2(String newvalue)
  {
    fieldS01FRDT2.setString(newvalue);
  }

  /**
  * Get value of field S01FRDT2 as a String.
  */
  public String getS01FRDT2()
  {
    return fieldS01FRDT2.getString();
  }

  /**
  * Set numeric field S01FRDT2 using a BigDecimal value.
  */
  public void setS01FRDT2(BigDecimal newvalue)
  {
    fieldS01FRDT2.setBigDecimal(newvalue);
  }

  /**
  * Return value of numeric field S01FRDT2 as a BigDecimal.
  */
  public BigDecimal getBigDecimalS01FRDT2()
  {
    return fieldS01FRDT2.getBigDecimal();
  }

  /**
  * Set field S01FRDT3 using a String value.
  */
  public void setS01FRDT3(String newvalue)
  {
    fieldS01FRDT3.setString(newvalue);
  }

  /**
  * Get value of field S01FRDT3 as a String.
  */
  public String getS01FRDT3()
  {
    return fieldS01FRDT3.getString();
  }

  /**
  * Set numeric field S01FRDT3 using a BigDecimal value.
  */
  public void setS01FRDT3(BigDecimal newvalue)
  {
    fieldS01FRDT3.setBigDecimal(newvalue);
  }

  /**
  * Return value of numeric field S01FRDT3 as a BigDecimal.
  */
  public BigDecimal getBigDecimalS01FRDT3()
  {
    return fieldS01FRDT3.getBigDecimal();
  }

  /**
  * Set field S01TODT1 using a String value.
  */
  public void setS01TODT1(String newvalue)
  {
    fieldS01TODT1.setString(newvalue);
  }

  /**
  * Get value of field S01TODT1 as a String.
  */
  public String getS01TODT1()
  {
    return fieldS01TODT1.getString();
  }

  /**
  * Set numeric field S01TODT1 using a BigDecimal value.
  */
  public void setS01TODT1(BigDecimal newvalue)
  {
    fieldS01TODT1.setBigDecimal(newvalue);
  }

  /**
  * Return value of numeric field S01TODT1 as a BigDecimal.
  */
  public BigDecimal getBigDecimalS01TODT1()
  {
    return fieldS01TODT1.getBigDecimal();
  }

  /**
  * Set field S01TODT2 using a String value.
  */
  public void setS01TODT2(String newvalue)
  {
    fieldS01TODT2.setString(newvalue);
  }

  /**
  * Get value of field S01TODT2 as a String.
  */
  public String getS01TODT2()
  {
    return fieldS01TODT2.getString();
  }

  /**
  * Set numeric field S01TODT2 using a BigDecimal value.
  */
  public void setS01TODT2(BigDecimal newvalue)
  {
    fieldS01TODT2.setBigDecimal(newvalue);
  }

  /**
  * Return value of numeric field S01TODT2 as a BigDecimal.
  */
  public BigDecimal getBigDecimalS01TODT2()
  {
    return fieldS01TODT2.getBigDecimal();
  }

  /**
  * Set field S01TODT3 using a String value.
  */
  public void setS01TODT3(String newvalue)
  {
    fieldS01TODT3.setString(newvalue);
  }

  /**
  * Get value of field S01TODT3 as a String.
  */
  public String getS01TODT3()
  {
    return fieldS01TODT3.getString();
  }

  /**
  * Set numeric field S01TODT3 using a BigDecimal value.
  */
  public void setS01TODT3(BigDecimal newvalue)
  {
    fieldS01TODT3.setBigDecimal(newvalue);
  }

  /**
  * Return value of numeric field S01TODT3 as a BigDecimal.
  */
  public BigDecimal getBigDecimalS01TODT3()
  {
    return fieldS01TODT3.getBigDecimal();
  }

  /**
  * Set field S01SBLDEP using a String value.
  */
  public void setS01SBLDEP(String newvalue)
  {
    fieldS01SBLDEP.setString(newvalue);
  }

  /**
  * Get value of field S01SBLDEP as a String.
  */
  public String getS01SBLDEP()
  {
    return fieldS01SBLDEP.getString();
  }

  /**
  * Set numeric field S01SBLDEP using a BigDecimal value.
  */
  public void setS01SBLDEP(BigDecimal newvalue)
  {
    fieldS01SBLDEP.setBigDecimal(newvalue);
  }

  /**
  * Return value of numeric field S01SBLDEP as a BigDecimal.
  */
  public BigDecimal getBigDecimalS01SBLDEP()
  {
    return fieldS01SBLDEP.getBigDecimal();
  }

  /**
  * Set field S01SBLCUN using a String value.
  */
  public void setS01SBLCUN(String newvalue)
  {
    fieldS01SBLCUN.setString(newvalue);
  }

  /**
  * Get value of field S01SBLCUN as a String.
  */
  public String getS01SBLCUN()
  {
    return fieldS01SBLCUN.getString();
  }

  /**
  * Set numeric field S01SBLCUN using a BigDecimal value.
  */
  public void setS01SBLCUN(BigDecimal newvalue)
  {
    fieldS01SBLCUN.setBigDecimal(newvalue);
  }

  /**
  * Return value of numeric field S01SBLCUN as a BigDecimal.
  */
  public BigDecimal getBigDecimalS01SBLCUN()
  {
    return fieldS01SBLCUN.getBigDecimal();
  }

  /**
  * Set field D01SBMTYP using a String value.
  */
  public void setD01SBMTYP(String newvalue)
  {
    fieldD01SBMTYP.setString(newvalue);
  }

  /**
  * Get value of field D01SBMTYP as a String.
  */
  public String getD01SBMTYP()
  {
    return fieldD01SBMTYP.getString();
  }

  /**
  * Set numeric field D01SBMTYP using a BigDecimal value.
  */
  public void setD01SBMTYP(BigDecimal newvalue)
  {
    fieldD01SBMTYP.setBigDecimal(newvalue);
  }

  /**
  * Return value of numeric field D01SBMTYP as a BigDecimal.
  */
  public BigDecimal getBigDecimalD01SBMTYP()
  {
    return fieldD01SBMTYP.getBigDecimal();
  }

  /**
  * Set field D01SBTDSC using a String value.
  */
  public void setD01SBTDSC(String newvalue)
  {
    fieldD01SBTDSC.setString(newvalue);
  }

  /**
  * Get value of field D01SBTDSC as a String.
  */
  public String getD01SBTDSC()
  {
    return fieldD01SBTDSC.getString();
  }

  /**
  * Set field D01SBMLOC using a String value.
  */
  public void setD01SBMLOC(String newvalue)
  {
    fieldD01SBMLOC.setString(newvalue);
  }

  /**
  * Get value of field D01SBMLOC as a String.
  */
  public String getD01SBMLOC()
  {
    return fieldD01SBMLOC.getString();
  }

  /**
  * Set field D01LOCNME using a String value.
  */
  public void setD01LOCNME(String newvalue)
  {
    fieldD01LOCNME.setString(newvalue);
  }

  /**
  * Get value of field D01LOCNME as a String.
  */
  public String getD01LOCNME()
  {
    return fieldD01LOCNME.getString();
  }

  /**
  * Set field D01SBMCUN using a String value.
  */
  public void setD01SBMCUN(String newvalue)
  {
    fieldD01SBMCUN.setString(newvalue);
  }

  /**
  * Get value of field D01SBMCUN as a String.
  */
  public String getD01SBMCUN()
  {
    return fieldD01SBMCUN.getString();
  }

  /**
  * Set numeric field D01SBMCUN using a BigDecimal value.
  */
  public void setD01SBMCUN(BigDecimal newvalue)
  {
    fieldD01SBMCUN.setBigDecimal(newvalue);
  }

  /**
  * Return value of numeric field D01SBMCUN as a BigDecimal.
  */
  public BigDecimal getBigDecimalD01SBMCUN()
  {
    return fieldD01SBMCUN.getBigDecimal();
  }

  /**
  * Set field D01CUSNA1 using a String value.
  */
  public void setD01CUSNA1(String newvalue)
  {
    fieldD01CUSNA1.setString(newvalue);
  }

  /**
  * Get value of field D01CUSNA1 as a String.
  */
  public String getD01CUSNA1()
  {
    return fieldD01CUSNA1.getString();
  }

}
