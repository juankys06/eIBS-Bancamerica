package datapro.eibs.beans;

import datapro.eibs.sockets.*;
import java.io.*;
import java.math.*;

import java.util.*;

/**
* Class generated by AS/400 CRTCLASS command from EIE006001 physical file definition.
* 
* File level identifier is 1030512124623.
* Record format level identifier is 37789544216D9.
*/

public class EIE006001Message extends MessageRecord
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
                                     "E01QUOIIC",
                                     "E01QUOTE1",
                                     "E01QUOTE2",
                                     "E01QUOTE3",
                                     "E01QUOTET",
                                     "E01QUOOPP",
                                     "E01QUOASK",
                                     "E01QUOBID",
                                     "E01QUOMID",
                                     "E01QUOTPX",
                                     "E01QUOASP",
                                     "E01QUOBIP",
                                     "E01QUOMIP",
                                     "E01QUOHIP",
                                     "E01QUOLOP",
                                     "E01QUOMKP",
                                     "E01QUOCLP",
                                     "E01QUOAVS",
                                     "E01QUOAVP",
                                     "E01QUOUSR",
                                     "E01QUOUP1",
                                     "E01QUOUP2",
                                     "E01QUOUP3",
                                     "E01QUOUPT",
                                     "E01QUOSTS",
                                     "E01QUOFL0",
                                     "E01QUOFL1",
                                     "E01QUOFL2",
                                     "E01QUOFL3",
                                     "E01QUOFL4",
                                     "E01QUOFL5",
                                     "E01QUOFL6",
                                     "E01QUOFL7",
                                     "E01QUOFL8",
                                     "E01QUOFL9",
                                     "D01ISIDSC",
                                     "D01ISIPTY",
                                     "D01ISINUM",
                                     "D01ISISER",
                                     "D01ISICUS",
                                     "D01ISISYM",
                                     "D01ISICCY"
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
                                   "E01QUOIIC",
                                   "E01QUOTE1",
                                   "E01QUOTE2",
                                   "E01QUOTE3",
                                   "E01QUOTET",
                                   "E01QUOOPP",
                                   "E01QUOASK",
                                   "E01QUOBID",
                                   "E01QUOMID",
                                   "E01QUOTPX",
                                   "E01QUOASP",
                                   "E01QUOBIP",
                                   "E01QUOMIP",
                                   "E01QUOHIP",
                                   "E01QUOLOP",
                                   "E01QUOMKP",
                                   "E01QUOCLP",
                                   "E01QUOAVS",
                                   "E01QUOAVP",
                                   "E01QUOUSR",
                                   "E01QUOUP1",
                                   "E01QUOUP2",
                                   "E01QUOUP3",
                                   "E01QUOUPT",
                                   "E01QUOSTS",
                                   "E01QUOFL0",
                                   "E01QUOFL1",
                                   "E01QUOFL2",
                                   "E01QUOFL3",
                                   "E01QUOFL4",
                                   "E01QUOFL5",
                                   "E01QUOFL6",
                                   "E01QUOFL7",
                                   "E01QUOFL8",
                                   "E01QUOFL9",
                                   "D01ISIDSC",
                                   "D01ISIPTY",
                                   "D01ISINUM",
                                   "D01ISISER",
                                   "D01ISICUS",
                                   "D01ISISYM",
                                   "D01ISICCY"
                                 };
  final static String fid = "1030512124623";
  final static String rid = "37789544216D9";
  final static String fmtname = "EIE006001";
  final int FIELDCOUNT = 51;
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
  private DecimalField fieldE01QUOIIC = null;
  private DecimalField fieldE01QUOTE1 = null;
  private DecimalField fieldE01QUOTE2 = null;
  private DecimalField fieldE01QUOTE3 = null;
  private DecimalField fieldE01QUOTET = null;
  private DecimalField fieldE01QUOOPP = null;
  private DecimalField fieldE01QUOASK = null;
  private DecimalField fieldE01QUOBID = null;
  private DecimalField fieldE01QUOMID = null;
  private DecimalField fieldE01QUOTPX = null;
  private DecimalField fieldE01QUOASP = null;
  private DecimalField fieldE01QUOBIP = null;
  private DecimalField fieldE01QUOMIP = null;
  private DecimalField fieldE01QUOHIP = null;
  private DecimalField fieldE01QUOLOP = null;
  private DecimalField fieldE01QUOMKP = null;
  private DecimalField fieldE01QUOCLP = null;
  private DecimalField fieldE01QUOAVS = null;
  private DecimalField fieldE01QUOAVP = null;
  private CharacterField fieldE01QUOUSR = null;
  private DecimalField fieldE01QUOUP1 = null;
  private DecimalField fieldE01QUOUP2 = null;
  private DecimalField fieldE01QUOUP3 = null;
  private DecimalField fieldE01QUOUPT = null;
  private CharacterField fieldE01QUOSTS = null;
  private CharacterField fieldE01QUOFL0 = null;
  private CharacterField fieldE01QUOFL1 = null;
  private CharacterField fieldE01QUOFL2 = null;
  private CharacterField fieldE01QUOFL3 = null;
  private CharacterField fieldE01QUOFL4 = null;
  private CharacterField fieldE01QUOFL5 = null;
  private CharacterField fieldE01QUOFL6 = null;
  private DecimalField fieldE01QUOFL7 = null;
  private DecimalField fieldE01QUOFL8 = null;
  private DecimalField fieldE01QUOFL9 = null;
  private CharacterField fieldD01ISIDSC = null;
  private CharacterField fieldD01ISIPTY = null;
  private CharacterField fieldD01ISINUM = null;
  private CharacterField fieldD01ISISER = null;
  private CharacterField fieldD01ISICUS = null;
  private CharacterField fieldD01ISISYM = null;
  private CharacterField fieldD01ISICCY = null;

  /**
  * Constructor for EIE006001Message.
  */
  public EIE006001Message()
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
    recordsize = 550;
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
    fields[9] = fieldE01QUOIIC =
    new DecimalField(message, HEADERSIZE + 42, 10, 0, "E01QUOIIC");
    fields[10] = fieldE01QUOTE1 =
    new DecimalField(message, HEADERSIZE + 52, 3, 0, "E01QUOTE1");
    fields[11] = fieldE01QUOTE2 =
    new DecimalField(message, HEADERSIZE + 55, 3, 0, "E01QUOTE2");
    fields[12] = fieldE01QUOTE3 =
    new DecimalField(message, HEADERSIZE + 58, 3, 0, "E01QUOTE3");
    fields[13] = fieldE01QUOTET =
    new DecimalField(message, HEADERSIZE + 61, 7, 0, "E01QUOTET");
    fields[14] = fieldE01QUOOPP =
    new DecimalField(message, HEADERSIZE + 68, 17, 6, "E01QUOOPP");
    fields[15] = fieldE01QUOASK =
    new DecimalField(message, HEADERSIZE + 85, 17, 6, "E01QUOASK");
    fields[16] = fieldE01QUOBID =
    new DecimalField(message, HEADERSIZE + 102, 17, 6, "E01QUOBID");
    fields[17] = fieldE01QUOMID =
    new DecimalField(message, HEADERSIZE + 119, 17, 6, "E01QUOMID");
    fields[18] = fieldE01QUOTPX =
    new DecimalField(message, HEADERSIZE + 136, 17, 6, "E01QUOTPX");
    fields[19] = fieldE01QUOASP =
    new DecimalField(message, HEADERSIZE + 153, 17, 6, "E01QUOASP");
    fields[20] = fieldE01QUOBIP =
    new DecimalField(message, HEADERSIZE + 170, 17, 6, "E01QUOBIP");
    fields[21] = fieldE01QUOMIP =
    new DecimalField(message, HEADERSIZE + 187, 17, 6, "E01QUOMIP");
    fields[22] = fieldE01QUOHIP =
    new DecimalField(message, HEADERSIZE + 204, 17, 6, "E01QUOHIP");
    fields[23] = fieldE01QUOLOP =
    new DecimalField(message, HEADERSIZE + 221, 17, 6, "E01QUOLOP");
    fields[24] = fieldE01QUOMKP =
    new DecimalField(message, HEADERSIZE + 238, 17, 6, "E01QUOMKP");
    fields[25] = fieldE01QUOCLP =
    new DecimalField(message, HEADERSIZE + 255, 17, 6, "E01QUOCLP");
    fields[26] = fieldE01QUOAVS =
    new DecimalField(message, HEADERSIZE + 272, 17, 6, "E01QUOAVS");
    fields[27] = fieldE01QUOAVP =
    new DecimalField(message, HEADERSIZE + 289, 17, 6, "E01QUOAVP");
    fields[28] = fieldE01QUOUSR =
    new CharacterField(message, HEADERSIZE + 306, 10, "E01QUOUSR");
    fields[29] = fieldE01QUOUP1 =
    new DecimalField(message, HEADERSIZE + 316, 3, 0, "E01QUOUP1");
    fields[30] = fieldE01QUOUP2 =
    new DecimalField(message, HEADERSIZE + 319, 3, 0, "E01QUOUP2");
    fields[31] = fieldE01QUOUP3 =
    new DecimalField(message, HEADERSIZE + 322, 3, 0, "E01QUOUP3");
    fields[32] = fieldE01QUOUPT =
    new DecimalField(message, HEADERSIZE + 325, 7, 0, "E01QUOUPT");
    fields[33] = fieldE01QUOSTS =
    new CharacterField(message, HEADERSIZE + 332, 1, "E01QUOSTS");
    fields[34] = fieldE01QUOFL0 =
    new CharacterField(message, HEADERSIZE + 333, 1, "E01QUOFL0");
    fields[35] = fieldE01QUOFL1 =
    new CharacterField(message, HEADERSIZE + 334, 1, "E01QUOFL1");
    fields[36] = fieldE01QUOFL2 =
    new CharacterField(message, HEADERSIZE + 335, 1, "E01QUOFL2");
    fields[37] = fieldE01QUOFL3 =
    new CharacterField(message, HEADERSIZE + 336, 1, "E01QUOFL3");
    fields[38] = fieldE01QUOFL4 =
    new CharacterField(message, HEADERSIZE + 337, 15, "E01QUOFL4");
    fields[39] = fieldE01QUOFL5 =
    new CharacterField(message, HEADERSIZE + 352, 15, "E01QUOFL5");
    fields[40] = fieldE01QUOFL6 =
    new CharacterField(message, HEADERSIZE + 367, 15, "E01QUOFL6");
    fields[41] = fieldE01QUOFL7 =
    new DecimalField(message, HEADERSIZE + 382, 23, 6, "E01QUOFL7");
    fields[42] = fieldE01QUOFL8 =
    new DecimalField(message, HEADERSIZE + 405, 23, 6, "E01QUOFL8");
    fields[43] = fieldE01QUOFL9 =
    new DecimalField(message, HEADERSIZE + 428, 23, 6, "E01QUOFL9");
    fields[44] = fieldD01ISIDSC =
    new CharacterField(message, HEADERSIZE + 451, 50, "D01ISIDSC");
    fields[45] = fieldD01ISIPTY =
    new CharacterField(message, HEADERSIZE + 501, 3, "D01ISIPTY");
    fields[46] = fieldD01ISINUM =
    new CharacterField(message, HEADERSIZE + 504, 12, "D01ISINUM");
    fields[47] = fieldD01ISISER =
    new CharacterField(message, HEADERSIZE + 516, 4, "D01ISISER");
    fields[48] = fieldD01ISICUS =
    new CharacterField(message, HEADERSIZE + 520, 12, "D01ISICUS");
    fields[49] = fieldD01ISISYM =
    new CharacterField(message, HEADERSIZE + 532, 15, "D01ISISYM");
    fields[50] = fieldD01ISICCY =
    new CharacterField(message, HEADERSIZE + 547, 3, "D01ISICCY");

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
  * Set field E01QUOIIC using a String value.
  */
  public void setE01QUOIIC(String newvalue)
  {
    fieldE01QUOIIC.setString(newvalue);
  }

  /**
  * Get value of field E01QUOIIC as a String.
  */
  public String getE01QUOIIC()
  {
    return fieldE01QUOIIC.getString();
  }

  /**
  * Set numeric field E01QUOIIC using a BigDecimal value.
  */
  public void setE01QUOIIC(BigDecimal newvalue)
  {
    fieldE01QUOIIC.setBigDecimal(newvalue);
  }

  /**
  * Return value of numeric field E01QUOIIC as a BigDecimal.
  */
  public BigDecimal getBigDecimalE01QUOIIC()
  {
    return fieldE01QUOIIC.getBigDecimal();
  }

  /**
  * Set field E01QUOTE1 using a String value.
  */
  public void setE01QUOTE1(String newvalue)
  {
    fieldE01QUOTE1.setString(newvalue);
  }

  /**
  * Get value of field E01QUOTE1 as a String.
  */
  public String getE01QUOTE1()
  {
    return fieldE01QUOTE1.getString();
  }

  /**
  * Set numeric field E01QUOTE1 using a BigDecimal value.
  */
  public void setE01QUOTE1(BigDecimal newvalue)
  {
    fieldE01QUOTE1.setBigDecimal(newvalue);
  }

  /**
  * Return value of numeric field E01QUOTE1 as a BigDecimal.
  */
  public BigDecimal getBigDecimalE01QUOTE1()
  {
    return fieldE01QUOTE1.getBigDecimal();
  }

  /**
  * Set field E01QUOTE2 using a String value.
  */
  public void setE01QUOTE2(String newvalue)
  {
    fieldE01QUOTE2.setString(newvalue);
  }

  /**
  * Get value of field E01QUOTE2 as a String.
  */
  public String getE01QUOTE2()
  {
    return fieldE01QUOTE2.getString();
  }

  /**
  * Set numeric field E01QUOTE2 using a BigDecimal value.
  */
  public void setE01QUOTE2(BigDecimal newvalue)
  {
    fieldE01QUOTE2.setBigDecimal(newvalue);
  }

  /**
  * Return value of numeric field E01QUOTE2 as a BigDecimal.
  */
  public BigDecimal getBigDecimalE01QUOTE2()
  {
    return fieldE01QUOTE2.getBigDecimal();
  }

  /**
  * Set field E01QUOTE3 using a String value.
  */
  public void setE01QUOTE3(String newvalue)
  {
    fieldE01QUOTE3.setString(newvalue);
  }

  /**
  * Get value of field E01QUOTE3 as a String.
  */
  public String getE01QUOTE3()
  {
    return fieldE01QUOTE3.getString();
  }

  /**
  * Set numeric field E01QUOTE3 using a BigDecimal value.
  */
  public void setE01QUOTE3(BigDecimal newvalue)
  {
    fieldE01QUOTE3.setBigDecimal(newvalue);
  }

  /**
  * Return value of numeric field E01QUOTE3 as a BigDecimal.
  */
  public BigDecimal getBigDecimalE01QUOTE3()
  {
    return fieldE01QUOTE3.getBigDecimal();
  }

  /**
  * Set field E01QUOTET using a String value.
  */
  public void setE01QUOTET(String newvalue)
  {
    fieldE01QUOTET.setString(newvalue);
  }

  /**
  * Get value of field E01QUOTET as a String.
  */
  public String getE01QUOTET()
  {
    return fieldE01QUOTET.getString();
  }

  /**
  * Set numeric field E01QUOTET using a BigDecimal value.
  */
  public void setE01QUOTET(BigDecimal newvalue)
  {
    fieldE01QUOTET.setBigDecimal(newvalue);
  }

  /**
  * Return value of numeric field E01QUOTET as a BigDecimal.
  */
  public BigDecimal getBigDecimalE01QUOTET()
  {
    return fieldE01QUOTET.getBigDecimal();
  }

  /**
  * Set field E01QUOOPP using a String value.
  */
  public void setE01QUOOPP(String newvalue)
  {
    fieldE01QUOOPP.setString(newvalue);
  }

  /**
  * Get value of field E01QUOOPP as a String.
  */
  public String getE01QUOOPP()
  {
    return fieldE01QUOOPP.getString();
  }

  /**
  * Set numeric field E01QUOOPP using a BigDecimal value.
  */
  public void setE01QUOOPP(BigDecimal newvalue)
  {
    fieldE01QUOOPP.setBigDecimal(newvalue);
  }

  /**
  * Return value of numeric field E01QUOOPP as a BigDecimal.
  */
  public BigDecimal getBigDecimalE01QUOOPP()
  {
    return fieldE01QUOOPP.getBigDecimal();
  }

  /**
  * Set field E01QUOASK using a String value.
  */
  public void setE01QUOASK(String newvalue)
  {
    fieldE01QUOASK.setString(newvalue);
  }

  /**
  * Get value of field E01QUOASK as a String.
  */
  public String getE01QUOASK()
  {
    return fieldE01QUOASK.getString();
  }

  /**
  * Set numeric field E01QUOASK using a BigDecimal value.
  */
  public void setE01QUOASK(BigDecimal newvalue)
  {
    fieldE01QUOASK.setBigDecimal(newvalue);
  }

  /**
  * Return value of numeric field E01QUOASK as a BigDecimal.
  */
  public BigDecimal getBigDecimalE01QUOASK()
  {
    return fieldE01QUOASK.getBigDecimal();
  }

  /**
  * Set field E01QUOBID using a String value.
  */
  public void setE01QUOBID(String newvalue)
  {
    fieldE01QUOBID.setString(newvalue);
  }

  /**
  * Get value of field E01QUOBID as a String.
  */
  public String getE01QUOBID()
  {
    return fieldE01QUOBID.getString();
  }

  /**
  * Set numeric field E01QUOBID using a BigDecimal value.
  */
  public void setE01QUOBID(BigDecimal newvalue)
  {
    fieldE01QUOBID.setBigDecimal(newvalue);
  }

  /**
  * Return value of numeric field E01QUOBID as a BigDecimal.
  */
  public BigDecimal getBigDecimalE01QUOBID()
  {
    return fieldE01QUOBID.getBigDecimal();
  }

  /**
  * Set field E01QUOMID using a String value.
  */
  public void setE01QUOMID(String newvalue)
  {
    fieldE01QUOMID.setString(newvalue);
  }

  /**
  * Get value of field E01QUOMID as a String.
  */
  public String getE01QUOMID()
  {
    return fieldE01QUOMID.getString();
  }

  /**
  * Set numeric field E01QUOMID using a BigDecimal value.
  */
  public void setE01QUOMID(BigDecimal newvalue)
  {
    fieldE01QUOMID.setBigDecimal(newvalue);
  }

  /**
  * Return value of numeric field E01QUOMID as a BigDecimal.
  */
  public BigDecimal getBigDecimalE01QUOMID()
  {
    return fieldE01QUOMID.getBigDecimal();
  }

  /**
  * Set field E01QUOTPX using a String value.
  */
  public void setE01QUOTPX(String newvalue)
  {
    fieldE01QUOTPX.setString(newvalue);
  }

  /**
  * Get value of field E01QUOTPX as a String.
  */
  public String getE01QUOTPX()
  {
    return fieldE01QUOTPX.getString();
  }

  /**
  * Set numeric field E01QUOTPX using a BigDecimal value.
  */
  public void setE01QUOTPX(BigDecimal newvalue)
  {
    fieldE01QUOTPX.setBigDecimal(newvalue);
  }

  /**
  * Return value of numeric field E01QUOTPX as a BigDecimal.
  */
  public BigDecimal getBigDecimalE01QUOTPX()
  {
    return fieldE01QUOTPX.getBigDecimal();
  }

  /**
  * Set field E01QUOASP using a String value.
  */
  public void setE01QUOASP(String newvalue)
  {
    fieldE01QUOASP.setString(newvalue);
  }

  /**
  * Get value of field E01QUOASP as a String.
  */
  public String getE01QUOASP()
  {
    return fieldE01QUOASP.getString();
  }

  /**
  * Set numeric field E01QUOASP using a BigDecimal value.
  */
  public void setE01QUOASP(BigDecimal newvalue)
  {
    fieldE01QUOASP.setBigDecimal(newvalue);
  }

  /**
  * Return value of numeric field E01QUOASP as a BigDecimal.
  */
  public BigDecimal getBigDecimalE01QUOASP()
  {
    return fieldE01QUOASP.getBigDecimal();
  }

  /**
  * Set field E01QUOBIP using a String value.
  */
  public void setE01QUOBIP(String newvalue)
  {
    fieldE01QUOBIP.setString(newvalue);
  }

  /**
  * Get value of field E01QUOBIP as a String.
  */
  public String getE01QUOBIP()
  {
    return fieldE01QUOBIP.getString();
  }

  /**
  * Set numeric field E01QUOBIP using a BigDecimal value.
  */
  public void setE01QUOBIP(BigDecimal newvalue)
  {
    fieldE01QUOBIP.setBigDecimal(newvalue);
  }

  /**
  * Return value of numeric field E01QUOBIP as a BigDecimal.
  */
  public BigDecimal getBigDecimalE01QUOBIP()
  {
    return fieldE01QUOBIP.getBigDecimal();
  }

  /**
  * Set field E01QUOMIP using a String value.
  */
  public void setE01QUOMIP(String newvalue)
  {
    fieldE01QUOMIP.setString(newvalue);
  }

  /**
  * Get value of field E01QUOMIP as a String.
  */
  public String getE01QUOMIP()
  {
    return fieldE01QUOMIP.getString();
  }

  /**
  * Set numeric field E01QUOMIP using a BigDecimal value.
  */
  public void setE01QUOMIP(BigDecimal newvalue)
  {
    fieldE01QUOMIP.setBigDecimal(newvalue);
  }

  /**
  * Return value of numeric field E01QUOMIP as a BigDecimal.
  */
  public BigDecimal getBigDecimalE01QUOMIP()
  {
    return fieldE01QUOMIP.getBigDecimal();
  }

  /**
  * Set field E01QUOHIP using a String value.
  */
  public void setE01QUOHIP(String newvalue)
  {
    fieldE01QUOHIP.setString(newvalue);
  }

  /**
  * Get value of field E01QUOHIP as a String.
  */
  public String getE01QUOHIP()
  {
    return fieldE01QUOHIP.getString();
  }

  /**
  * Set numeric field E01QUOHIP using a BigDecimal value.
  */
  public void setE01QUOHIP(BigDecimal newvalue)
  {
    fieldE01QUOHIP.setBigDecimal(newvalue);
  }

  /**
  * Return value of numeric field E01QUOHIP as a BigDecimal.
  */
  public BigDecimal getBigDecimalE01QUOHIP()
  {
    return fieldE01QUOHIP.getBigDecimal();
  }

  /**
  * Set field E01QUOLOP using a String value.
  */
  public void setE01QUOLOP(String newvalue)
  {
    fieldE01QUOLOP.setString(newvalue);
  }

  /**
  * Get value of field E01QUOLOP as a String.
  */
  public String getE01QUOLOP()
  {
    return fieldE01QUOLOP.getString();
  }

  /**
  * Set numeric field E01QUOLOP using a BigDecimal value.
  */
  public void setE01QUOLOP(BigDecimal newvalue)
  {
    fieldE01QUOLOP.setBigDecimal(newvalue);
  }

  /**
  * Return value of numeric field E01QUOLOP as a BigDecimal.
  */
  public BigDecimal getBigDecimalE01QUOLOP()
  {
    return fieldE01QUOLOP.getBigDecimal();
  }

  /**
  * Set field E01QUOMKP using a String value.
  */
  public void setE01QUOMKP(String newvalue)
  {
    fieldE01QUOMKP.setString(newvalue);
  }

  /**
  * Get value of field E01QUOMKP as a String.
  */
  public String getE01QUOMKP()
  {
    return fieldE01QUOMKP.getString();
  }

  /**
  * Set numeric field E01QUOMKP using a BigDecimal value.
  */
  public void setE01QUOMKP(BigDecimal newvalue)
  {
    fieldE01QUOMKP.setBigDecimal(newvalue);
  }

  /**
  * Return value of numeric field E01QUOMKP as a BigDecimal.
  */
  public BigDecimal getBigDecimalE01QUOMKP()
  {
    return fieldE01QUOMKP.getBigDecimal();
  }

  /**
  * Set field E01QUOCLP using a String value.
  */
  public void setE01QUOCLP(String newvalue)
  {
    fieldE01QUOCLP.setString(newvalue);
  }

  /**
  * Get value of field E01QUOCLP as a String.
  */
  public String getE01QUOCLP()
  {
    return fieldE01QUOCLP.getString();
  }

  /**
  * Set numeric field E01QUOCLP using a BigDecimal value.
  */
  public void setE01QUOCLP(BigDecimal newvalue)
  {
    fieldE01QUOCLP.setBigDecimal(newvalue);
  }

  /**
  * Return value of numeric field E01QUOCLP as a BigDecimal.
  */
  public BigDecimal getBigDecimalE01QUOCLP()
  {
    return fieldE01QUOCLP.getBigDecimal();
  }

  /**
  * Set field E01QUOAVS using a String value.
  */
  public void setE01QUOAVS(String newvalue)
  {
    fieldE01QUOAVS.setString(newvalue);
  }

  /**
  * Get value of field E01QUOAVS as a String.
  */
  public String getE01QUOAVS()
  {
    return fieldE01QUOAVS.getString();
  }

  /**
  * Set numeric field E01QUOAVS using a BigDecimal value.
  */
  public void setE01QUOAVS(BigDecimal newvalue)
  {
    fieldE01QUOAVS.setBigDecimal(newvalue);
  }

  /**
  * Return value of numeric field E01QUOAVS as a BigDecimal.
  */
  public BigDecimal getBigDecimalE01QUOAVS()
  {
    return fieldE01QUOAVS.getBigDecimal();
  }

  /**
  * Set field E01QUOAVP using a String value.
  */
  public void setE01QUOAVP(String newvalue)
  {
    fieldE01QUOAVP.setString(newvalue);
  }

  /**
  * Get value of field E01QUOAVP as a String.
  */
  public String getE01QUOAVP()
  {
    return fieldE01QUOAVP.getString();
  }

  /**
  * Set numeric field E01QUOAVP using a BigDecimal value.
  */
  public void setE01QUOAVP(BigDecimal newvalue)
  {
    fieldE01QUOAVP.setBigDecimal(newvalue);
  }

  /**
  * Return value of numeric field E01QUOAVP as a BigDecimal.
  */
  public BigDecimal getBigDecimalE01QUOAVP()
  {
    return fieldE01QUOAVP.getBigDecimal();
  }

  /**
  * Set field E01QUOUSR using a String value.
  */
  public void setE01QUOUSR(String newvalue)
  {
    fieldE01QUOUSR.setString(newvalue);
  }

  /**
  * Get value of field E01QUOUSR as a String.
  */
  public String getE01QUOUSR()
  {
    return fieldE01QUOUSR.getString();
  }

  /**
  * Set field E01QUOUP1 using a String value.
  */
  public void setE01QUOUP1(String newvalue)
  {
    fieldE01QUOUP1.setString(newvalue);
  }

  /**
  * Get value of field E01QUOUP1 as a String.
  */
  public String getE01QUOUP1()
  {
    return fieldE01QUOUP1.getString();
  }

  /**
  * Set numeric field E01QUOUP1 using a BigDecimal value.
  */
  public void setE01QUOUP1(BigDecimal newvalue)
  {
    fieldE01QUOUP1.setBigDecimal(newvalue);
  }

  /**
  * Return value of numeric field E01QUOUP1 as a BigDecimal.
  */
  public BigDecimal getBigDecimalE01QUOUP1()
  {
    return fieldE01QUOUP1.getBigDecimal();
  }

  /**
  * Set field E01QUOUP2 using a String value.
  */
  public void setE01QUOUP2(String newvalue)
  {
    fieldE01QUOUP2.setString(newvalue);
  }

  /**
  * Get value of field E01QUOUP2 as a String.
  */
  public String getE01QUOUP2()
  {
    return fieldE01QUOUP2.getString();
  }

  /**
  * Set numeric field E01QUOUP2 using a BigDecimal value.
  */
  public void setE01QUOUP2(BigDecimal newvalue)
  {
    fieldE01QUOUP2.setBigDecimal(newvalue);
  }

  /**
  * Return value of numeric field E01QUOUP2 as a BigDecimal.
  */
  public BigDecimal getBigDecimalE01QUOUP2()
  {
    return fieldE01QUOUP2.getBigDecimal();
  }

  /**
  * Set field E01QUOUP3 using a String value.
  */
  public void setE01QUOUP3(String newvalue)
  {
    fieldE01QUOUP3.setString(newvalue);
  }

  /**
  * Get value of field E01QUOUP3 as a String.
  */
  public String getE01QUOUP3()
  {
    return fieldE01QUOUP3.getString();
  }

  /**
  * Set numeric field E01QUOUP3 using a BigDecimal value.
  */
  public void setE01QUOUP3(BigDecimal newvalue)
  {
    fieldE01QUOUP3.setBigDecimal(newvalue);
  }

  /**
  * Return value of numeric field E01QUOUP3 as a BigDecimal.
  */
  public BigDecimal getBigDecimalE01QUOUP3()
  {
    return fieldE01QUOUP3.getBigDecimal();
  }

  /**
  * Set field E01QUOUPT using a String value.
  */
  public void setE01QUOUPT(String newvalue)
  {
    fieldE01QUOUPT.setString(newvalue);
  }

  /**
  * Get value of field E01QUOUPT as a String.
  */
  public String getE01QUOUPT()
  {
    return fieldE01QUOUPT.getString();
  }

  /**
  * Set numeric field E01QUOUPT using a BigDecimal value.
  */
  public void setE01QUOUPT(BigDecimal newvalue)
  {
    fieldE01QUOUPT.setBigDecimal(newvalue);
  }

  /**
  * Return value of numeric field E01QUOUPT as a BigDecimal.
  */
  public BigDecimal getBigDecimalE01QUOUPT()
  {
    return fieldE01QUOUPT.getBigDecimal();
  }

  /**
  * Set field E01QUOSTS using a String value.
  */
  public void setE01QUOSTS(String newvalue)
  {
    fieldE01QUOSTS.setString(newvalue);
  }

  /**
  * Get value of field E01QUOSTS as a String.
  */
  public String getE01QUOSTS()
  {
    return fieldE01QUOSTS.getString();
  }

  /**
  * Set field E01QUOFL0 using a String value.
  */
  public void setE01QUOFL0(String newvalue)
  {
    fieldE01QUOFL0.setString(newvalue);
  }

  /**
  * Get value of field E01QUOFL0 as a String.
  */
  public String getE01QUOFL0()
  {
    return fieldE01QUOFL0.getString();
  }

  /**
  * Set field E01QUOFL1 using a String value.
  */
  public void setE01QUOFL1(String newvalue)
  {
    fieldE01QUOFL1.setString(newvalue);
  }

  /**
  * Get value of field E01QUOFL1 as a String.
  */
  public String getE01QUOFL1()
  {
    return fieldE01QUOFL1.getString();
  }

  /**
  * Set field E01QUOFL2 using a String value.
  */
  public void setE01QUOFL2(String newvalue)
  {
    fieldE01QUOFL2.setString(newvalue);
  }

  /**
  * Get value of field E01QUOFL2 as a String.
  */
  public String getE01QUOFL2()
  {
    return fieldE01QUOFL2.getString();
  }

  /**
  * Set field E01QUOFL3 using a String value.
  */
  public void setE01QUOFL3(String newvalue)
  {
    fieldE01QUOFL3.setString(newvalue);
  }

  /**
  * Get value of field E01QUOFL3 as a String.
  */
  public String getE01QUOFL3()
  {
    return fieldE01QUOFL3.getString();
  }

  /**
  * Set field E01QUOFL4 using a String value.
  */
  public void setE01QUOFL4(String newvalue)
  {
    fieldE01QUOFL4.setString(newvalue);
  }

  /**
  * Get value of field E01QUOFL4 as a String.
  */
  public String getE01QUOFL4()
  {
    return fieldE01QUOFL4.getString();
  }

  /**
  * Set field E01QUOFL5 using a String value.
  */
  public void setE01QUOFL5(String newvalue)
  {
    fieldE01QUOFL5.setString(newvalue);
  }

  /**
  * Get value of field E01QUOFL5 as a String.
  */
  public String getE01QUOFL5()
  {
    return fieldE01QUOFL5.getString();
  }

  /**
  * Set field E01QUOFL6 using a String value.
  */
  public void setE01QUOFL6(String newvalue)
  {
    fieldE01QUOFL6.setString(newvalue);
  }

  /**
  * Get value of field E01QUOFL6 as a String.
  */
  public String getE01QUOFL6()
  {
    return fieldE01QUOFL6.getString();
  }

  /**
  * Set field E01QUOFL7 using a String value.
  */
  public void setE01QUOFL7(String newvalue)
  {
    fieldE01QUOFL7.setString(newvalue);
  }

  /**
  * Get value of field E01QUOFL7 as a String.
  */
  public String getE01QUOFL7()
  {
    return fieldE01QUOFL7.getString();
  }

  /**
  * Set numeric field E01QUOFL7 using a BigDecimal value.
  */
  public void setE01QUOFL7(BigDecimal newvalue)
  {
    fieldE01QUOFL7.setBigDecimal(newvalue);
  }

  /**
  * Return value of numeric field E01QUOFL7 as a BigDecimal.
  */
  public BigDecimal getBigDecimalE01QUOFL7()
  {
    return fieldE01QUOFL7.getBigDecimal();
  }

  /**
  * Set field E01QUOFL8 using a String value.
  */
  public void setE01QUOFL8(String newvalue)
  {
    fieldE01QUOFL8.setString(newvalue);
  }

  /**
  * Get value of field E01QUOFL8 as a String.
  */
  public String getE01QUOFL8()
  {
    return fieldE01QUOFL8.getString();
  }

  /**
  * Set numeric field E01QUOFL8 using a BigDecimal value.
  */
  public void setE01QUOFL8(BigDecimal newvalue)
  {
    fieldE01QUOFL8.setBigDecimal(newvalue);
  }

  /**
  * Return value of numeric field E01QUOFL8 as a BigDecimal.
  */
  public BigDecimal getBigDecimalE01QUOFL8()
  {
    return fieldE01QUOFL8.getBigDecimal();
  }

  /**
  * Set field E01QUOFL9 using a String value.
  */
  public void setE01QUOFL9(String newvalue)
  {
    fieldE01QUOFL9.setString(newvalue);
  }

  /**
  * Get value of field E01QUOFL9 as a String.
  */
  public String getE01QUOFL9()
  {
    return fieldE01QUOFL9.getString();
  }

  /**
  * Set numeric field E01QUOFL9 using a BigDecimal value.
  */
  public void setE01QUOFL9(BigDecimal newvalue)
  {
    fieldE01QUOFL9.setBigDecimal(newvalue);
  }

  /**
  * Return value of numeric field E01QUOFL9 as a BigDecimal.
  */
  public BigDecimal getBigDecimalE01QUOFL9()
  {
    return fieldE01QUOFL9.getBigDecimal();
  }

  /**
  * Set field D01ISIDSC using a String value.
  */
  public void setD01ISIDSC(String newvalue)
  {
    fieldD01ISIDSC.setString(newvalue);
  }

  /**
  * Get value of field D01ISIDSC as a String.
  */
  public String getD01ISIDSC()
  {
    return fieldD01ISIDSC.getString();
  }

  /**
  * Set field D01ISIPTY using a String value.
  */
  public void setD01ISIPTY(String newvalue)
  {
    fieldD01ISIPTY.setString(newvalue);
  }

  /**
  * Get value of field D01ISIPTY as a String.
  */
  public String getD01ISIPTY()
  {
    return fieldD01ISIPTY.getString();
  }

  /**
  * Set field D01ISINUM using a String value.
  */
  public void setD01ISINUM(String newvalue)
  {
    fieldD01ISINUM.setString(newvalue);
  }

  /**
  * Get value of field D01ISINUM as a String.
  */
  public String getD01ISINUM()
  {
    return fieldD01ISINUM.getString();
  }

  /**
  * Set field D01ISISER using a String value.
  */
  public void setD01ISISER(String newvalue)
  {
    fieldD01ISISER.setString(newvalue);
  }

  /**
  * Get value of field D01ISISER as a String.
  */
  public String getD01ISISER()
  {
    return fieldD01ISISER.getString();
  }

  /**
  * Set field D01ISICUS using a String value.
  */
  public void setD01ISICUS(String newvalue)
  {
    fieldD01ISICUS.setString(newvalue);
  }

  /**
  * Get value of field D01ISICUS as a String.
  */
  public String getD01ISICUS()
  {
    return fieldD01ISICUS.getString();
  }

  /**
  * Set field D01ISISYM using a String value.
  */
  public void setD01ISISYM(String newvalue)
  {
    fieldD01ISISYM.setString(newvalue);
  }

  /**
  * Get value of field D01ISISYM as a String.
  */
  public String getD01ISISYM()
  {
    return fieldD01ISISYM.getString();
  }

  /**
  * Set field D01ISICCY using a String value.
  */
  public void setD01ISICCY(String newvalue)
  {
    fieldD01ISICCY.setString(newvalue);
  }

  /**
  * Get value of field D01ISICCY as a String.
  */
  public String getD01ISICCY()
  {
    return fieldD01ISICCY.getString();
  }

}
