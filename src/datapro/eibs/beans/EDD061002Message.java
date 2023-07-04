package datapro.eibs.beans;

import datapro.eibs.sockets.*;
import java.io.*;
import java.math.*;

import java.util.*;

/**
* Class generated by AS/400 CRTCLASS command from EDD061002 physical file definition.
* 
* File level identifier is 1030121191847.
* Record format level identifier is 3B87691A615A2.
*/

public class EDD061002Message extends MessageRecord
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
                                     "E02SCHBNK",
                                     "E02SCHTYA",
                                     "E02SCHNUM",
                                     "E02INSDT1",
                                     "E02INSDT2",
                                     "E02INSDT3",
                                     "E02FEDREF",
                                     "E02FEDSAB",
                                     "E02FEDSNM",
                                     "E02FEDRAB",
                                     "E02FEDRNM",
                                     "E02FEDTS1",
                                     "E02FEDTS2",
                                     "E02WRTCAM",
                                     "E02FEDORG",
                                     "E02FEDOR1",
                                     "E02FEDOR2",
                                     "E02FEDORB",
                                     "E02FEDPRC",
                                     "E02FEDIBK",
                                     "E02FEDBBK",
                                     "E02FEDBNF",
                                     "E02FEDBN1",
                                     "E02FEDBRF",
                                     "E02FEDOBI",
                                     "E02FEDBBI",
                                     "E02FEDINS",
                                     "E02NUMREC",
                                     "E02INDOPE"
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
                                   "E02SCHBNK",
                                   "E02SCHTYA",
                                   "E02SCHNUM",
                                   "E02INSDT1",
                                   "E02INSDT2",
                                   "E02INSDT3",
                                   "E02FEDREF",
                                   "E02FEDSAB",
                                   "E02FEDSNM",
                                   "E02FEDRAB",
                                   "E02FEDRNM",
                                   "E02FEDTS1",
                                   "E02FEDTS2",
                                   "E02WRTCAM",
                                   "E02FEDORG",
                                   "E02FEDOR1",
                                   "E02FEDOR2",
                                   "E02FEDORB",
                                   "E02FEDPRC",
                                   "E02FEDIBK",
                                   "E02FEDBBK",
                                   "E02FEDBNF",
                                   "E02FEDBN1",
                                   "E02FEDBRF",
                                   "E02FEDOBI",
                                   "E02FEDBBI",
                                   "E02FEDINS",
                                   "E02NUMREC",
                                   "E02INDOPE"
                                 };
  final static String fid = "1030121191847";
  final static String rid = "3B87691A615A2";
  final static String fmtname = "EDD061002";
  final int FIELDCOUNT = 38;
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
  private CharacterField fieldE02SCHBNK = null;
  private CharacterField fieldE02SCHTYA = null;
  private CharacterField fieldE02SCHNUM = null;
  private DecimalField fieldE02INSDT1 = null;
  private DecimalField fieldE02INSDT2 = null;
  private DecimalField fieldE02INSDT3 = null;
  private CharacterField fieldE02FEDREF = null;
  private CharacterField fieldE02FEDSAB = null;
  private CharacterField fieldE02FEDSNM = null;
  private DecimalField fieldE02FEDRAB = null;
  private CharacterField fieldE02FEDRNM = null;
  private DecimalField fieldE02FEDTS1 = null;
  private DecimalField fieldE02FEDTS2 = null;
  private DecimalField fieldE02WRTCAM = null;
  private CharacterField fieldE02FEDORG = null;
  private CharacterField fieldE02FEDOR1 = null;
  private CharacterField fieldE02FEDOR2 = null;
  private CharacterField fieldE02FEDORB = null;
  private CharacterField fieldE02FEDPRC = null;
  private CharacterField fieldE02FEDIBK = null;
  private CharacterField fieldE02FEDBBK = null;
  private CharacterField fieldE02FEDBNF = null;
  private CharacterField fieldE02FEDBN1 = null;
  private CharacterField fieldE02FEDBRF = null;
  private CharacterField fieldE02FEDOBI = null;
  private CharacterField fieldE02FEDBBI = null;
  private CharacterField fieldE02FEDINS = null;
  private DecimalField fieldE02NUMREC = null;
  private CharacterField fieldE02INDOPE = null;

  /**
  * Constructor for EDD061002Message.
  */
  public EDD061002Message()
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
    recordsize = 745;
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
    fields[9] = fieldE02SCHBNK =
    new CharacterField(message, HEADERSIZE + 42, 2, "E02SCHBNK");
    fields[10] = fieldE02SCHTYA =
    new CharacterField(message, HEADERSIZE + 44, 1, "E02SCHTYA");
    fields[11] = fieldE02SCHNUM =
    new CharacterField(message, HEADERSIZE + 45, 7, "E02SCHNUM");
    fields[12] = fieldE02INSDT1 =
    new DecimalField(message, HEADERSIZE + 52, 3, 0, "E02INSDT1");
    fields[13] = fieldE02INSDT2 =
    new DecimalField(message, HEADERSIZE + 55, 3, 0, "E02INSDT2");
    fields[14] = fieldE02INSDT3 =
    new DecimalField(message, HEADERSIZE + 58, 3, 0, "E02INSDT3");
    fields[15] = fieldE02FEDREF =
    new CharacterField(message, HEADERSIZE + 61, 16, "E02FEDREF");
    fields[16] = fieldE02FEDSAB =
    new CharacterField(message, HEADERSIZE + 77, 9, "E02FEDSAB");
    fields[17] = fieldE02FEDSNM =
    new CharacterField(message, HEADERSIZE + 86, 18, "E02FEDSNM");
    fields[18] = fieldE02FEDRAB =
    new DecimalField(message, HEADERSIZE + 104, 10, 0, "E02FEDRAB");
    fields[19] = fieldE02FEDRNM =
    new CharacterField(message, HEADERSIZE + 114, 18, "E02FEDRNM");
    fields[20] = fieldE02FEDTS1 =
    new DecimalField(message, HEADERSIZE + 132, 3, 0, "E02FEDTS1");
    fields[21] = fieldE02FEDTS2 =
    new DecimalField(message, HEADERSIZE + 135, 3, 0, "E02FEDTS2");
    fields[22] = fieldE02WRTCAM =
    new DecimalField(message, HEADERSIZE + 138, 17, 2, "E02WRTCAM");
    fields[23] = fieldE02FEDORG =
    new CharacterField(message, HEADERSIZE + 155, 34, "E02FEDORG");
    fields[24] = fieldE02FEDOR1 =
    new CharacterField(message, HEADERSIZE + 189, 34, "E02FEDOR1");
    fields[25] = fieldE02FEDOR2 =
    new CharacterField(message, HEADERSIZE + 223, 34, "E02FEDOR2");
    fields[26] = fieldE02FEDORB =
    new CharacterField(message, HEADERSIZE + 257, 34, "E02FEDORB");
    fields[27] = fieldE02FEDPRC =
    new CharacterField(message, HEADERSIZE + 291, 3, "E02FEDPRC");
    fields[28] = fieldE02FEDIBK =
    new CharacterField(message, HEADERSIZE + 294, 51, "E02FEDIBK");
    fields[29] = fieldE02FEDBBK =
    new CharacterField(message, HEADERSIZE + 345, 51, "E02FEDBBK");
    fields[30] = fieldE02FEDBNF =
    new CharacterField(message, HEADERSIZE + 396, 51, "E02FEDBNF");
    fields[31] = fieldE02FEDBN1 =
    new CharacterField(message, HEADERSIZE + 447, 51, "E02FEDBN1");
    fields[32] = fieldE02FEDBRF =
    new CharacterField(message, HEADERSIZE + 498, 16, "E02FEDBRF");
    fields[33] = fieldE02FEDOBI =
    new CharacterField(message, HEADERSIZE + 514, 74, "E02FEDOBI");
    fields[34] = fieldE02FEDBBI =
    new CharacterField(message, HEADERSIZE + 588, 74, "E02FEDBBI");
    fields[35] = fieldE02FEDINS =
    new CharacterField(message, HEADERSIZE + 662, 74, "E02FEDINS");
    fields[36] = fieldE02NUMREC =
    new DecimalField(message, HEADERSIZE + 736, 8, 0, "E02NUMREC");
    fields[37] = fieldE02INDOPE =
    new CharacterField(message, HEADERSIZE + 744, 1, "E02INDOPE");

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
  * Set field E02SCHBNK using a String value.
  */
  public void setE02SCHBNK(String newvalue)
  {
    fieldE02SCHBNK.setString(newvalue);
  }

  /**
  * Get value of field E02SCHBNK as a String.
  */
  public String getE02SCHBNK()
  {
    return fieldE02SCHBNK.getString();
  }

  /**
  * Set field E02SCHTYA using a String value.
  */
  public void setE02SCHTYA(String newvalue)
  {
    fieldE02SCHTYA.setString(newvalue);
  }

  /**
  * Get value of field E02SCHTYA as a String.
  */
  public String getE02SCHTYA()
  {
    return fieldE02SCHTYA.getString();
  }

  /**
  * Set field E02SCHNUM using a String value.
  */
  public void setE02SCHNUM(String newvalue)
  {
    fieldE02SCHNUM.setString(newvalue);
  }

  /**
  * Get value of field E02SCHNUM as a String.
  */
  public String getE02SCHNUM()
  {
    return fieldE02SCHNUM.getString();
  }

  /**
  * Set field E02INSDT1 using a String value.
  */
  public void setE02INSDT1(String newvalue)
  {
    fieldE02INSDT1.setString(newvalue);
  }

  /**
  * Get value of field E02INSDT1 as a String.
  */
  public String getE02INSDT1()
  {
    return fieldE02INSDT1.getString();
  }

  /**
  * Set numeric field E02INSDT1 using a BigDecimal value.
  */
  public void setE02INSDT1(BigDecimal newvalue)
  {
    fieldE02INSDT1.setBigDecimal(newvalue);
  }

  /**
  * Return value of numeric field E02INSDT1 as a BigDecimal.
  */
  public BigDecimal getBigDecimalE02INSDT1()
  {
    return fieldE02INSDT1.getBigDecimal();
  }

  /**
  * Set field E02INSDT2 using a String value.
  */
  public void setE02INSDT2(String newvalue)
  {
    fieldE02INSDT2.setString(newvalue);
  }

  /**
  * Get value of field E02INSDT2 as a String.
  */
  public String getE02INSDT2()
  {
    return fieldE02INSDT2.getString();
  }

  /**
  * Set numeric field E02INSDT2 using a BigDecimal value.
  */
  public void setE02INSDT2(BigDecimal newvalue)
  {
    fieldE02INSDT2.setBigDecimal(newvalue);
  }

  /**
  * Return value of numeric field E02INSDT2 as a BigDecimal.
  */
  public BigDecimal getBigDecimalE02INSDT2()
  {
    return fieldE02INSDT2.getBigDecimal();
  }

  /**
  * Set field E02INSDT3 using a String value.
  */
  public void setE02INSDT3(String newvalue)
  {
    fieldE02INSDT3.setString(newvalue);
  }

  /**
  * Get value of field E02INSDT3 as a String.
  */
  public String getE02INSDT3()
  {
    return fieldE02INSDT3.getString();
  }

  /**
  * Set numeric field E02INSDT3 using a BigDecimal value.
  */
  public void setE02INSDT3(BigDecimal newvalue)
  {
    fieldE02INSDT3.setBigDecimal(newvalue);
  }

  /**
  * Return value of numeric field E02INSDT3 as a BigDecimal.
  */
  public BigDecimal getBigDecimalE02INSDT3()
  {
    return fieldE02INSDT3.getBigDecimal();
  }

  /**
  * Set field E02FEDREF using a String value.
  */
  public void setE02FEDREF(String newvalue)
  {
    fieldE02FEDREF.setString(newvalue);
  }

  /**
  * Get value of field E02FEDREF as a String.
  */
  public String getE02FEDREF()
  {
    return fieldE02FEDREF.getString();
  }

  /**
  * Set field E02FEDSAB using a String value.
  */
  public void setE02FEDSAB(String newvalue)
  {
    fieldE02FEDSAB.setString(newvalue);
  }

  /**
  * Get value of field E02FEDSAB as a String.
  */
  public String getE02FEDSAB()
  {
    return fieldE02FEDSAB.getString();
  }

  /**
  * Set field E02FEDSNM using a String value.
  */
  public void setE02FEDSNM(String newvalue)
  {
    fieldE02FEDSNM.setString(newvalue);
  }

  /**
  * Get value of field E02FEDSNM as a String.
  */
  public String getE02FEDSNM()
  {
    return fieldE02FEDSNM.getString();
  }

  /**
  * Set field E02FEDRAB using a String value.
  */
  public void setE02FEDRAB(String newvalue)
  {
    fieldE02FEDRAB.setString(newvalue);
  }

  /**
  * Get value of field E02FEDRAB as a String.
  */
  public String getE02FEDRAB()
  {
    return fieldE02FEDRAB.getString();
  }

  /**
  * Set numeric field E02FEDRAB using a BigDecimal value.
  */
  public void setE02FEDRAB(BigDecimal newvalue)
  {
    fieldE02FEDRAB.setBigDecimal(newvalue);
  }

  /**
  * Return value of numeric field E02FEDRAB as a BigDecimal.
  */
  public BigDecimal getBigDecimalE02FEDRAB()
  {
    return fieldE02FEDRAB.getBigDecimal();
  }

  /**
  * Set field E02FEDRNM using a String value.
  */
  public void setE02FEDRNM(String newvalue)
  {
    fieldE02FEDRNM.setString(newvalue);
  }

  /**
  * Get value of field E02FEDRNM as a String.
  */
  public String getE02FEDRNM()
  {
    return fieldE02FEDRNM.getString();
  }

  /**
  * Set field E02FEDTS1 using a String value.
  */
  public void setE02FEDTS1(String newvalue)
  {
    fieldE02FEDTS1.setString(newvalue);
  }

  /**
  * Get value of field E02FEDTS1 as a String.
  */
  public String getE02FEDTS1()
  {
    return fieldE02FEDTS1.getString();
  }

  /**
  * Set numeric field E02FEDTS1 using a BigDecimal value.
  */
  public void setE02FEDTS1(BigDecimal newvalue)
  {
    fieldE02FEDTS1.setBigDecimal(newvalue);
  }

  /**
  * Return value of numeric field E02FEDTS1 as a BigDecimal.
  */
  public BigDecimal getBigDecimalE02FEDTS1()
  {
    return fieldE02FEDTS1.getBigDecimal();
  }

  /**
  * Set field E02FEDTS2 using a String value.
  */
  public void setE02FEDTS2(String newvalue)
  {
    fieldE02FEDTS2.setString(newvalue);
  }

  /**
  * Get value of field E02FEDTS2 as a String.
  */
  public String getE02FEDTS2()
  {
    return fieldE02FEDTS2.getString();
  }

  /**
  * Set numeric field E02FEDTS2 using a BigDecimal value.
  */
  public void setE02FEDTS2(BigDecimal newvalue)
  {
    fieldE02FEDTS2.setBigDecimal(newvalue);
  }

  /**
  * Return value of numeric field E02FEDTS2 as a BigDecimal.
  */
  public BigDecimal getBigDecimalE02FEDTS2()
  {
    return fieldE02FEDTS2.getBigDecimal();
  }

  /**
  * Set field E02WRTCAM using a String value.
  */
  public void setE02WRTCAM(String newvalue)
  {
    fieldE02WRTCAM.setString(newvalue);
  }

  /**
  * Get value of field E02WRTCAM as a String.
  */
  public String getE02WRTCAM()
  {
    return fieldE02WRTCAM.getString();
  }

  /**
  * Set numeric field E02WRTCAM using a BigDecimal value.
  */
  public void setE02WRTCAM(BigDecimal newvalue)
  {
    fieldE02WRTCAM.setBigDecimal(newvalue);
  }

  /**
  * Return value of numeric field E02WRTCAM as a BigDecimal.
  */
  public BigDecimal getBigDecimalE02WRTCAM()
  {
    return fieldE02WRTCAM.getBigDecimal();
  }

  /**
  * Set field E02FEDORG using a String value.
  */
  public void setE02FEDORG(String newvalue)
  {
    fieldE02FEDORG.setString(newvalue);
  }

  /**
  * Get value of field E02FEDORG as a String.
  */
  public String getE02FEDORG()
  {
    return fieldE02FEDORG.getString();
  }

  /**
  * Set field E02FEDOR1 using a String value.
  */
  public void setE02FEDOR1(String newvalue)
  {
    fieldE02FEDOR1.setString(newvalue);
  }

  /**
  * Get value of field E02FEDOR1 as a String.
  */
  public String getE02FEDOR1()
  {
    return fieldE02FEDOR1.getString();
  }

  /**
  * Set field E02FEDOR2 using a String value.
  */
  public void setE02FEDOR2(String newvalue)
  {
    fieldE02FEDOR2.setString(newvalue);
  }

  /**
  * Get value of field E02FEDOR2 as a String.
  */
  public String getE02FEDOR2()
  {
    return fieldE02FEDOR2.getString();
  }

  /**
  * Set field E02FEDORB using a String value.
  */
  public void setE02FEDORB(String newvalue)
  {
    fieldE02FEDORB.setString(newvalue);
  }

  /**
  * Get value of field E02FEDORB as a String.
  */
  public String getE02FEDORB()
  {
    return fieldE02FEDORB.getString();
  }

  /**
  * Set field E02FEDPRC using a String value.
  */
  public void setE02FEDPRC(String newvalue)
  {
    fieldE02FEDPRC.setString(newvalue);
  }

  /**
  * Get value of field E02FEDPRC as a String.
  */
  public String getE02FEDPRC()
  {
    return fieldE02FEDPRC.getString();
  }

  /**
  * Set field E02FEDIBK using a String value.
  */
  public void setE02FEDIBK(String newvalue)
  {
    fieldE02FEDIBK.setString(newvalue);
  }

  /**
  * Get value of field E02FEDIBK as a String.
  */
  public String getE02FEDIBK()
  {
    return fieldE02FEDIBK.getString();
  }

  /**
  * Set field E02FEDBBK using a String value.
  */
  public void setE02FEDBBK(String newvalue)
  {
    fieldE02FEDBBK.setString(newvalue);
  }

  /**
  * Get value of field E02FEDBBK as a String.
  */
  public String getE02FEDBBK()
  {
    return fieldE02FEDBBK.getString();
  }

  /**
  * Set field E02FEDBNF using a String value.
  */
  public void setE02FEDBNF(String newvalue)
  {
    fieldE02FEDBNF.setString(newvalue);
  }

  /**
  * Get value of field E02FEDBNF as a String.
  */
  public String getE02FEDBNF()
  {
    return fieldE02FEDBNF.getString();
  }

  /**
  * Set field E02FEDBN1 using a String value.
  */
  public void setE02FEDBN1(String newvalue)
  {
    fieldE02FEDBN1.setString(newvalue);
  }

  /**
  * Get value of field E02FEDBN1 as a String.
  */
  public String getE02FEDBN1()
  {
    return fieldE02FEDBN1.getString();
  }

  /**
  * Set field E02FEDBRF using a String value.
  */
  public void setE02FEDBRF(String newvalue)
  {
    fieldE02FEDBRF.setString(newvalue);
  }

  /**
  * Get value of field E02FEDBRF as a String.
  */
  public String getE02FEDBRF()
  {
    return fieldE02FEDBRF.getString();
  }

  /**
  * Set field E02FEDOBI using a String value.
  */
  public void setE02FEDOBI(String newvalue)
  {
    fieldE02FEDOBI.setString(newvalue);
  }

  /**
  * Get value of field E02FEDOBI as a String.
  */
  public String getE02FEDOBI()
  {
    return fieldE02FEDOBI.getString();
  }

  /**
  * Set field E02FEDBBI using a String value.
  */
  public void setE02FEDBBI(String newvalue)
  {
    fieldE02FEDBBI.setString(newvalue);
  }

  /**
  * Get value of field E02FEDBBI as a String.
  */
  public String getE02FEDBBI()
  {
    return fieldE02FEDBBI.getString();
  }

  /**
  * Set field E02FEDINS using a String value.
  */
  public void setE02FEDINS(String newvalue)
  {
    fieldE02FEDINS.setString(newvalue);
  }

  /**
  * Get value of field E02FEDINS as a String.
  */
  public String getE02FEDINS()
  {
    return fieldE02FEDINS.getString();
  }

  /**
  * Set field E02NUMREC using a String value.
  */
  public void setE02NUMREC(String newvalue)
  {
    fieldE02NUMREC.setString(newvalue);
  }

  /**
  * Get value of field E02NUMREC as a String.
  */
  public String getE02NUMREC()
  {
    return fieldE02NUMREC.getString();
  }

  /**
  * Set numeric field E02NUMREC using a BigDecimal value.
  */
  public void setE02NUMREC(BigDecimal newvalue)
  {
    fieldE02NUMREC.setBigDecimal(newvalue);
  }

  /**
  * Return value of numeric field E02NUMREC as a BigDecimal.
  */
  public BigDecimal getBigDecimalE02NUMREC()
  {
    return fieldE02NUMREC.getBigDecimal();
  }

  /**
  * Set field E02INDOPE using a String value.
  */
  public void setE02INDOPE(String newvalue)
  {
    fieldE02INDOPE.setString(newvalue);
  }

  /**
  * Get value of field E02INDOPE as a String.
  */
  public String getE02INDOPE()
  {
    return fieldE02INDOPE.getString();
  }

}
