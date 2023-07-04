package datapro.eibs.beans;

import datapro.eibs.sockets.*;
import java.io.*;
import java.math.*;

import java.util.*;

/**
* Class generated by AS/400 CRTCLASS command from EGL075001 physical file definition.
* 
* File level identifier is 1101005170552.
* Record format level identifier is 46E7A0E456A7A.
*/

public class EGL075001Message extends MessageRecord
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
                                     "E01GLBBNK",
                                     "E01GLBBRN",
                                     "E01GLBCCY",
                                     "E01GLBGLN",
                                     "E01GLBCLS",
                                     "E01GLBDSC",
                                     "E01GLBATY",
                                     "E01GLBCCN",
                                     "E01GLBNIV",
                                     "E01GLBBAL",
                                     "E01GLBINI",
                                     "E01GLBFIN",
                                     "E01GLBENE",
                                     "E01GLBFEB",
                                     "E01GLBMAR",
                                     "E01GLBABR",
                                     "E01GLBMAY",
                                     "E01GLBJUN",
                                     "E01GLBJUL",
                                     "E01GLBAGO",
                                     "E01GLBSET",
                                     "E01GLBOCT",
                                     "E01GLBNOV",
                                     "E01GLBDIC",
                                     "E01NUMREC",
                                     "E01GLBINV",
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
                                   "E01GLBBNK",
                                   "E01GLBBRN",
                                   "E01GLBCCY",
                                   "E01GLBGLN",
                                   "E01GLBCLS",
                                   "E01GLBDSC",
                                   "E01GLBATY",
                                   "E01GLBCCN",
                                   "E01GLBNIV",
                                   "E01GLBBAL",
                                   "E01GLBINI",
                                   "E01GLBFIN",
                                   "E01GLBENE",
                                   "E01GLBFEB",
                                   "E01GLBMAR",
                                   "E01GLBABR",
                                   "E01GLBMAY",
                                   "E01GLBJUN",
                                   "E01GLBJUL",
                                   "E01GLBAGO",
                                   "E01GLBSET",
                                   "E01GLBOCT",
                                   "E01GLBNOV",
                                   "E01GLBDIC",
                                   "E01NUMREC",
                                   "E01GLBINV",
                                   "E01INDOPE"
                                 };
  final static String fid = "1101005170552";
  final static String rid = "46E7A0E456A7A";
  final static String fmtname = "EGL075001";
  final int FIELDCOUNT = 36;
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
  private CharacterField fieldE01GLBBNK = null;
  private DecimalField fieldE01GLBBRN = null;
  private CharacterField fieldE01GLBCCY = null;
  private DecimalField fieldE01GLBGLN = null;
  private DecimalField fieldE01GLBCLS = null;
  private CharacterField fieldE01GLBDSC = null;
  private CharacterField fieldE01GLBATY = null;
  private DecimalField fieldE01GLBCCN = null;
  private CharacterField fieldE01GLBNIV = null;
  private DecimalField fieldE01GLBBAL = null;
  private DecimalField fieldE01GLBINI = null;
  private DecimalField fieldE01GLBFIN = null;
  private DecimalField fieldE01GLBENE = null;
  private DecimalField fieldE01GLBFEB = null;
  private DecimalField fieldE01GLBMAR = null;
  private DecimalField fieldE01GLBABR = null;
  private DecimalField fieldE01GLBMAY = null;
  private DecimalField fieldE01GLBJUN = null;
  private DecimalField fieldE01GLBJUL = null;
  private DecimalField fieldE01GLBAGO = null;
  private DecimalField fieldE01GLBSET = null;
  private DecimalField fieldE01GLBOCT = null;
  private DecimalField fieldE01GLBNOV = null;
  private DecimalField fieldE01GLBDIC = null;
  private DecimalField fieldE01NUMREC = null;
  private CharacterField fieldE01GLBINV = null;
  private CharacterField fieldE01INDOPE = null;

  /**
  * Constructor for EGL075001Message.
  */
  public EGL075001Message()
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
    recordsize = 385;
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
    fields[9] = fieldE01GLBBNK =
    new CharacterField(message, HEADERSIZE + 42, 2, "E01GLBBNK");
    fields[10] = fieldE01GLBBRN =
    new DecimalField(message, HEADERSIZE + 44, 4, 0, "E01GLBBRN");
    fields[11] = fieldE01GLBCCY =
    new CharacterField(message, HEADERSIZE + 48, 3, "E01GLBCCY");
    fields[12] = fieldE01GLBGLN =
    new DecimalField(message, HEADERSIZE + 51, 17, 0, "E01GLBGLN");
    fields[13] = fieldE01GLBCLS =
    new DecimalField(message, HEADERSIZE + 68, 3, 0, "E01GLBCLS");
    fields[14] = fieldE01GLBDSC =
    new CharacterField(message, HEADERSIZE + 71, 35, "E01GLBDSC");
    fields[15] = fieldE01GLBATY =
    new CharacterField(message, HEADERSIZE + 106, 4, "E01GLBATY");
    fields[16] = fieldE01GLBCCN =
    new DecimalField(message, HEADERSIZE + 110, 9, 0, "E01GLBCCN");
    fields[17] = fieldE01GLBNIV =
    new CharacterField(message, HEADERSIZE + 119, 1, "E01GLBNIV");
    fields[18] = fieldE01GLBBAL =
    new DecimalField(message, HEADERSIZE + 120, 17, 2, "E01GLBBAL");
    fields[19] = fieldE01GLBINI =
    new DecimalField(message, HEADERSIZE + 137, 17, 2, "E01GLBINI");
    fields[20] = fieldE01GLBFIN =
    new DecimalField(message, HEADERSIZE + 154, 17, 2, "E01GLBFIN");
    fields[21] = fieldE01GLBENE =
    new DecimalField(message, HEADERSIZE + 171, 17, 2, "E01GLBENE");
    fields[22] = fieldE01GLBFEB =
    new DecimalField(message, HEADERSIZE + 188, 17, 2, "E01GLBFEB");
    fields[23] = fieldE01GLBMAR =
    new DecimalField(message, HEADERSIZE + 205, 17, 2, "E01GLBMAR");
    fields[24] = fieldE01GLBABR =
    new DecimalField(message, HEADERSIZE + 222, 17, 2, "E01GLBABR");
    fields[25] = fieldE01GLBMAY =
    new DecimalField(message, HEADERSIZE + 239, 17, 2, "E01GLBMAY");
    fields[26] = fieldE01GLBJUN =
    new DecimalField(message, HEADERSIZE + 256, 17, 2, "E01GLBJUN");
    fields[27] = fieldE01GLBJUL =
    new DecimalField(message, HEADERSIZE + 273, 17, 2, "E01GLBJUL");
    fields[28] = fieldE01GLBAGO =
    new DecimalField(message, HEADERSIZE + 290, 17, 2, "E01GLBAGO");
    fields[29] = fieldE01GLBSET =
    new DecimalField(message, HEADERSIZE + 307, 17, 2, "E01GLBSET");
    fields[30] = fieldE01GLBOCT =
    new DecimalField(message, HEADERSIZE + 324, 17, 2, "E01GLBOCT");
    fields[31] = fieldE01GLBNOV =
    new DecimalField(message, HEADERSIZE + 341, 17, 2, "E01GLBNOV");
    fields[32] = fieldE01GLBDIC =
    new DecimalField(message, HEADERSIZE + 358, 17, 2, "E01GLBDIC");
    fields[33] = fieldE01NUMREC =
    new DecimalField(message, HEADERSIZE + 375, 8, 0, "E01NUMREC");
    fields[34] = fieldE01GLBINV =
    new CharacterField(message, HEADERSIZE + 383, 1, "E01GLBINV");
    fields[35] = fieldE01INDOPE =
    new CharacterField(message, HEADERSIZE + 384, 1, "E01INDOPE");

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
  * Set field E01GLBBNK using a String value.
  */
  public void setE01GLBBNK(String newvalue)
  {
    fieldE01GLBBNK.setString(newvalue);
  }

  /**
  * Get value of field E01GLBBNK as a String.
  */
  public String getE01GLBBNK()
  {
    return fieldE01GLBBNK.getString();
  }

  /**
  * Set field E01GLBBRN using a String value.
  */
  public void setE01GLBBRN(String newvalue)
  {
    fieldE01GLBBRN.setString(newvalue);
  }

  /**
  * Get value of field E01GLBBRN as a String.
  */
  public String getE01GLBBRN()
  {
    return fieldE01GLBBRN.getString();
  }

  /**
  * Set numeric field E01GLBBRN using a BigDecimal value.
  */
  public void setE01GLBBRN(BigDecimal newvalue)
  {
    fieldE01GLBBRN.setBigDecimal(newvalue);
  }

  /**
  * Return value of numeric field E01GLBBRN as a BigDecimal.
  */
  public BigDecimal getBigDecimalE01GLBBRN()
  {
    return fieldE01GLBBRN.getBigDecimal();
  }

  /**
  * Set field E01GLBCCY using a String value.
  */
  public void setE01GLBCCY(String newvalue)
  {
    fieldE01GLBCCY.setString(newvalue);
  }

  /**
  * Get value of field E01GLBCCY as a String.
  */
  public String getE01GLBCCY()
  {
    return fieldE01GLBCCY.getString();
  }

  /**
  * Set field E01GLBGLN using a String value.
  */
  public void setE01GLBGLN(String newvalue)
  {
    fieldE01GLBGLN.setString(newvalue);
  }

  /**
  * Get value of field E01GLBGLN as a String.
  */
  public String getE01GLBGLN()
  {
    return fieldE01GLBGLN.getString();
  }

  /**
  * Set numeric field E01GLBGLN using a BigDecimal value.
  */
  public void setE01GLBGLN(BigDecimal newvalue)
  {
    fieldE01GLBGLN.setBigDecimal(newvalue);
  }

  /**
  * Return value of numeric field E01GLBGLN as a BigDecimal.
  */
  public BigDecimal getBigDecimalE01GLBGLN()
  {
    return fieldE01GLBGLN.getBigDecimal();
  }

  /**
  * Set field E01GLBCLS using a String value.
  */
  public void setE01GLBCLS(String newvalue)
  {
    fieldE01GLBCLS.setString(newvalue);
  }

  /**
  * Get value of field E01GLBCLS as a String.
  */
  public String getE01GLBCLS()
  {
    return fieldE01GLBCLS.getString();
  }

  /**
  * Set numeric field E01GLBCLS using a BigDecimal value.
  */
  public void setE01GLBCLS(BigDecimal newvalue)
  {
    fieldE01GLBCLS.setBigDecimal(newvalue);
  }

  /**
  * Return value of numeric field E01GLBCLS as a BigDecimal.
  */
  public BigDecimal getBigDecimalE01GLBCLS()
  {
    return fieldE01GLBCLS.getBigDecimal();
  }

  /**
  * Set field E01GLBDSC using a String value.
  */
  public void setE01GLBDSC(String newvalue)
  {
    fieldE01GLBDSC.setString(newvalue);
  }

  /**
  * Get value of field E01GLBDSC as a String.
  */
  public String getE01GLBDSC()
  {
    return fieldE01GLBDSC.getString();
  }

  /**
  * Set field E01GLBATY using a String value.
  */
  public void setE01GLBATY(String newvalue)
  {
    fieldE01GLBATY.setString(newvalue);
  }

  /**
  * Get value of field E01GLBATY as a String.
  */
  public String getE01GLBATY()
  {
    return fieldE01GLBATY.getString();
  }

  /**
  * Set field E01GLBCCN using a String value.
  */
  public void setE01GLBCCN(String newvalue)
  {
    fieldE01GLBCCN.setString(newvalue);
  }

  /**
  * Get value of field E01GLBCCN as a String.
  */
  public String getE01GLBCCN()
  {
    return fieldE01GLBCCN.getString();
  }

  /**
  * Set numeric field E01GLBCCN using a BigDecimal value.
  */
  public void setE01GLBCCN(BigDecimal newvalue)
  {
    fieldE01GLBCCN.setBigDecimal(newvalue);
  }

  /**
  * Return value of numeric field E01GLBCCN as a BigDecimal.
  */
  public BigDecimal getBigDecimalE01GLBCCN()
  {
    return fieldE01GLBCCN.getBigDecimal();
  }

  /**
  * Set field E01GLBNIV using a String value.
  */
  public void setE01GLBNIV(String newvalue)
  {
    fieldE01GLBNIV.setString(newvalue);
  }

  /**
  * Get value of field E01GLBNIV as a String.
  */
  public String getE01GLBNIV()
  {
    return fieldE01GLBNIV.getString();
  }

  /**
  * Set field E01GLBBAL using a String value.
  */
  public void setE01GLBBAL(String newvalue)
  {
    fieldE01GLBBAL.setString(newvalue);
  }

  /**
  * Get value of field E01GLBBAL as a String.
  */
  public String getE01GLBBAL()
  {
    return fieldE01GLBBAL.getString();
  }

  /**
  * Set numeric field E01GLBBAL using a BigDecimal value.
  */
  public void setE01GLBBAL(BigDecimal newvalue)
  {
    fieldE01GLBBAL.setBigDecimal(newvalue);
  }

  /**
  * Return value of numeric field E01GLBBAL as a BigDecimal.
  */
  public BigDecimal getBigDecimalE01GLBBAL()
  {
    return fieldE01GLBBAL.getBigDecimal();
  }

  /**
  * Set field E01GLBINI using a String value.
  */
  public void setE01GLBINI(String newvalue)
  {
    fieldE01GLBINI.setString(newvalue);
  }

  /**
  * Get value of field E01GLBINI as a String.
  */
  public String getE01GLBINI()
  {
    return fieldE01GLBINI.getString();
  }

  /**
  * Set numeric field E01GLBINI using a BigDecimal value.
  */
  public void setE01GLBINI(BigDecimal newvalue)
  {
    fieldE01GLBINI.setBigDecimal(newvalue);
  }

  /**
  * Return value of numeric field E01GLBINI as a BigDecimal.
  */
  public BigDecimal getBigDecimalE01GLBINI()
  {
    return fieldE01GLBINI.getBigDecimal();
  }

  /**
  * Set field E01GLBFIN using a String value.
  */
  public void setE01GLBFIN(String newvalue)
  {
    fieldE01GLBFIN.setString(newvalue);
  }

  /**
  * Get value of field E01GLBFIN as a String.
  */
  public String getE01GLBFIN()
  {
    return fieldE01GLBFIN.getString();
  }

  /**
  * Set numeric field E01GLBFIN using a BigDecimal value.
  */
  public void setE01GLBFIN(BigDecimal newvalue)
  {
    fieldE01GLBFIN.setBigDecimal(newvalue);
  }

  /**
  * Return value of numeric field E01GLBFIN as a BigDecimal.
  */
  public BigDecimal getBigDecimalE01GLBFIN()
  {
    return fieldE01GLBFIN.getBigDecimal();
  }

  /**
  * Set field E01GLBENE using a String value.
  */
  public void setE01GLBENE(String newvalue)
  {
    fieldE01GLBENE.setString(newvalue);
  }

  /**
  * Get value of field E01GLBENE as a String.
  */
  public String getE01GLBENE()
  {
    return fieldE01GLBENE.getString();
  }

  /**
  * Set numeric field E01GLBENE using a BigDecimal value.
  */
  public void setE01GLBENE(BigDecimal newvalue)
  {
    fieldE01GLBENE.setBigDecimal(newvalue);
  }

  /**
  * Return value of numeric field E01GLBENE as a BigDecimal.
  */
  public BigDecimal getBigDecimalE01GLBENE()
  {
    return fieldE01GLBENE.getBigDecimal();
  }

  /**
  * Set field E01GLBFEB using a String value.
  */
  public void setE01GLBFEB(String newvalue)
  {
    fieldE01GLBFEB.setString(newvalue);
  }

  /**
  * Get value of field E01GLBFEB as a String.
  */
  public String getE01GLBFEB()
  {
    return fieldE01GLBFEB.getString();
  }

  /**
  * Set numeric field E01GLBFEB using a BigDecimal value.
  */
  public void setE01GLBFEB(BigDecimal newvalue)
  {
    fieldE01GLBFEB.setBigDecimal(newvalue);
  }

  /**
  * Return value of numeric field E01GLBFEB as a BigDecimal.
  */
  public BigDecimal getBigDecimalE01GLBFEB()
  {
    return fieldE01GLBFEB.getBigDecimal();
  }

  /**
  * Set field E01GLBMAR using a String value.
  */
  public void setE01GLBMAR(String newvalue)
  {
    fieldE01GLBMAR.setString(newvalue);
  }

  /**
  * Get value of field E01GLBMAR as a String.
  */
  public String getE01GLBMAR()
  {
    return fieldE01GLBMAR.getString();
  }

  /**
  * Set numeric field E01GLBMAR using a BigDecimal value.
  */
  public void setE01GLBMAR(BigDecimal newvalue)
  {
    fieldE01GLBMAR.setBigDecimal(newvalue);
  }

  /**
  * Return value of numeric field E01GLBMAR as a BigDecimal.
  */
  public BigDecimal getBigDecimalE01GLBMAR()
  {
    return fieldE01GLBMAR.getBigDecimal();
  }

  /**
  * Set field E01GLBABR using a String value.
  */
  public void setE01GLBABR(String newvalue)
  {
    fieldE01GLBABR.setString(newvalue);
  }

  /**
  * Get value of field E01GLBABR as a String.
  */
  public String getE01GLBABR()
  {
    return fieldE01GLBABR.getString();
  }

  /**
  * Set numeric field E01GLBABR using a BigDecimal value.
  */
  public void setE01GLBABR(BigDecimal newvalue)
  {
    fieldE01GLBABR.setBigDecimal(newvalue);
  }

  /**
  * Return value of numeric field E01GLBABR as a BigDecimal.
  */
  public BigDecimal getBigDecimalE01GLBABR()
  {
    return fieldE01GLBABR.getBigDecimal();
  }

  /**
  * Set field E01GLBMAY using a String value.
  */
  public void setE01GLBMAY(String newvalue)
  {
    fieldE01GLBMAY.setString(newvalue);
  }

  /**
  * Get value of field E01GLBMAY as a String.
  */
  public String getE01GLBMAY()
  {
    return fieldE01GLBMAY.getString();
  }

  /**
  * Set numeric field E01GLBMAY using a BigDecimal value.
  */
  public void setE01GLBMAY(BigDecimal newvalue)
  {
    fieldE01GLBMAY.setBigDecimal(newvalue);
  }

  /**
  * Return value of numeric field E01GLBMAY as a BigDecimal.
  */
  public BigDecimal getBigDecimalE01GLBMAY()
  {
    return fieldE01GLBMAY.getBigDecimal();
  }

  /**
  * Set field E01GLBJUN using a String value.
  */
  public void setE01GLBJUN(String newvalue)
  {
    fieldE01GLBJUN.setString(newvalue);
  }

  /**
  * Get value of field E01GLBJUN as a String.
  */
  public String getE01GLBJUN()
  {
    return fieldE01GLBJUN.getString();
  }

  /**
  * Set numeric field E01GLBJUN using a BigDecimal value.
  */
  public void setE01GLBJUN(BigDecimal newvalue)
  {
    fieldE01GLBJUN.setBigDecimal(newvalue);
  }

  /**
  * Return value of numeric field E01GLBJUN as a BigDecimal.
  */
  public BigDecimal getBigDecimalE01GLBJUN()
  {
    return fieldE01GLBJUN.getBigDecimal();
  }

  /**
  * Set field E01GLBJUL using a String value.
  */
  public void setE01GLBJUL(String newvalue)
  {
    fieldE01GLBJUL.setString(newvalue);
  }

  /**
  * Get value of field E01GLBJUL as a String.
  */
  public String getE01GLBJUL()
  {
    return fieldE01GLBJUL.getString();
  }

  /**
  * Set numeric field E01GLBJUL using a BigDecimal value.
  */
  public void setE01GLBJUL(BigDecimal newvalue)
  {
    fieldE01GLBJUL.setBigDecimal(newvalue);
  }

  /**
  * Return value of numeric field E01GLBJUL as a BigDecimal.
  */
  public BigDecimal getBigDecimalE01GLBJUL()
  {
    return fieldE01GLBJUL.getBigDecimal();
  }

  /**
  * Set field E01GLBAGO using a String value.
  */
  public void setE01GLBAGO(String newvalue)
  {
    fieldE01GLBAGO.setString(newvalue);
  }

  /**
  * Get value of field E01GLBAGO as a String.
  */
  public String getE01GLBAGO()
  {
    return fieldE01GLBAGO.getString();
  }

  /**
  * Set numeric field E01GLBAGO using a BigDecimal value.
  */
  public void setE01GLBAGO(BigDecimal newvalue)
  {
    fieldE01GLBAGO.setBigDecimal(newvalue);
  }

  /**
  * Return value of numeric field E01GLBAGO as a BigDecimal.
  */
  public BigDecimal getBigDecimalE01GLBAGO()
  {
    return fieldE01GLBAGO.getBigDecimal();
  }

  /**
  * Set field E01GLBSET using a String value.
  */
  public void setE01GLBSET(String newvalue)
  {
    fieldE01GLBSET.setString(newvalue);
  }

  /**
  * Get value of field E01GLBSET as a String.
  */
  public String getE01GLBSET()
  {
    return fieldE01GLBSET.getString();
  }

  /**
  * Set numeric field E01GLBSET using a BigDecimal value.
  */
  public void setE01GLBSET(BigDecimal newvalue)
  {
    fieldE01GLBSET.setBigDecimal(newvalue);
  }

  /**
  * Return value of numeric field E01GLBSET as a BigDecimal.
  */
  public BigDecimal getBigDecimalE01GLBSET()
  {
    return fieldE01GLBSET.getBigDecimal();
  }

  /**
  * Set field E01GLBOCT using a String value.
  */
  public void setE01GLBOCT(String newvalue)
  {
    fieldE01GLBOCT.setString(newvalue);
  }

  /**
  * Get value of field E01GLBOCT as a String.
  */
  public String getE01GLBOCT()
  {
    return fieldE01GLBOCT.getString();
  }

  /**
  * Set numeric field E01GLBOCT using a BigDecimal value.
  */
  public void setE01GLBOCT(BigDecimal newvalue)
  {
    fieldE01GLBOCT.setBigDecimal(newvalue);
  }

  /**
  * Return value of numeric field E01GLBOCT as a BigDecimal.
  */
  public BigDecimal getBigDecimalE01GLBOCT()
  {
    return fieldE01GLBOCT.getBigDecimal();
  }

  /**
  * Set field E01GLBNOV using a String value.
  */
  public void setE01GLBNOV(String newvalue)
  {
    fieldE01GLBNOV.setString(newvalue);
  }

  /**
  * Get value of field E01GLBNOV as a String.
  */
  public String getE01GLBNOV()
  {
    return fieldE01GLBNOV.getString();
  }

  /**
  * Set numeric field E01GLBNOV using a BigDecimal value.
  */
  public void setE01GLBNOV(BigDecimal newvalue)
  {
    fieldE01GLBNOV.setBigDecimal(newvalue);
  }

  /**
  * Return value of numeric field E01GLBNOV as a BigDecimal.
  */
  public BigDecimal getBigDecimalE01GLBNOV()
  {
    return fieldE01GLBNOV.getBigDecimal();
  }

  /**
  * Set field E01GLBDIC using a String value.
  */
  public void setE01GLBDIC(String newvalue)
  {
    fieldE01GLBDIC.setString(newvalue);
  }

  /**
  * Get value of field E01GLBDIC as a String.
  */
  public String getE01GLBDIC()
  {
    return fieldE01GLBDIC.getString();
  }

  /**
  * Set numeric field E01GLBDIC using a BigDecimal value.
  */
  public void setE01GLBDIC(BigDecimal newvalue)
  {
    fieldE01GLBDIC.setBigDecimal(newvalue);
  }

  /**
  * Return value of numeric field E01GLBDIC as a BigDecimal.
  */
  public BigDecimal getBigDecimalE01GLBDIC()
  {
    return fieldE01GLBDIC.getBigDecimal();
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
  * Set field E01GLBINV using a String value.
  */
  public void setE01GLBINV(String newvalue)
  {
    fieldE01GLBINV.setString(newvalue);
  }

  /**
  * Get value of field E01GLBINV as a String.
  */
  public String getE01GLBINV()
  {
    return fieldE01GLBINV.getString();
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