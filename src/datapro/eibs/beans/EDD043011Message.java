package datapro.eibs.beans;

import datapro.eibs.sockets.*;
import java.io.*;
import java.math.*;

import java.util.*;

/**
* Class generated by AS/400 CRTCLASS command from EDD043011 physical file definition.
* 
* File level identifier is 1040916175428.
* Record format level identifier is 40CAF10FE7E33.
*/

public class EDD043011Message extends MessageRecord
{
  final static String fldnames[] = {
                                     "H11USERID",
                                     "H11PROGRM",
                                     "H11TIMSYS",
                                     "H11SCRCOD",
                                     "H11OPECOD",
                                     "H11FLGMAS",
                                     "H11FLGWK1",
                                     "H11FLGWK2",
                                     "H11FLGWK3",
                                     "E11ACMACC",
                                     "E11ACMPRO",
                                     "E11ACMCUN",
                                     "E11CUSNA1",
                                     "E11INVPRD",
                                     "E11INVRBK",
                                     "E11INVRBR",
                                     "E11INVRCY",
                                     "E11INVRGL",
                                     "E11INVRAC",
                                     "E11INVOAM",
                                     "E11INVRTB",
                                     "E11INVPMT",
                                     "E11INVPIF",
                                     "E11INVIIP",
                                     "E11INVMIN",
                                     "E11INVMUL",
                                     "E11INVMAX",
                                     "E11INVBLS",
                                     "E11INVGLT",
                                     "E11INVSD1",
                                     "E11INVSD2",
                                     "E11INVSD3",
                                     "E11INVMD1",
                                     "E11INVMD2",
                                     "E11INVMD3",
                                     "E11INVRD1",
                                     "E11INVRD2",
                                     "E11INVRD3",
                                     "E11INVRPD",
                                     "E11INVRFL",
                                     "E11INVSTS",
                                     "E11DEAFTB",
                                     "E11DEAFTY",
                                     "E11FLTRTE",
                                     "E11DEARTE",
                                     "E11DLSRTE",
                                     "E11DEAIPD",
                                     "E11DEAPPD",
                                     "E11INTDAY",
                                     "E11PRIDAY",
                                     "E11PRDNME",
                                     "D11ACMPRO"
                                   };
  final static String tnames[] = {
                                   "H11USERID",
                                   "H11PROGRM",
                                   "H11TIMSYS",
                                   "H11SCRCOD",
                                   "H11OPECOD",
                                   "H11FLGMAS",
                                   "H11FLGWK1",
                                   "H11FLGWK2",
                                   "H11FLGWK3",
                                   "E11ACMACC",
                                   "E11ACMPRO",
                                   "E11ACMCUN",
                                   "E11CUSNA1",
                                   "E11INVPRD",
                                   "E11INVRBK",
                                   "E11INVRBR",
                                   "E11INVRCY",
                                   "E11INVRGL",
                                   "E11INVRAC",
                                   "E11INVOAM",
                                   "E11INVRTB",
                                   "E11INVPMT",
                                   "E11INVPIF",
                                   "E11INVIIP",
                                   "E11INVMIN",
                                   "E11INVMUL",
                                   "E11INVMAX",
                                   "E11INVBLS",
                                   "E11INVGLT",
                                   "E11INVSD1",
                                   "E11INVSD2",
                                   "E11INVSD3",
                                   "E11INVMD1",
                                   "E11INVMD2",
                                   "E11INVMD3",
                                   "E11INVRD1",
                                   "E11INVRD2",
                                   "E11INVRD3",
                                   "E11INVRPD",
                                   "E11INVRFL",
                                   "E11INVSTS",
                                   "E11DEAFTB",
                                   "E11DEAFTY",
                                   "E11FLTRTE",
                                   "E11DEARTE",
                                   "E11DLSRTE",
                                   "E11DEAIPD",
                                   "E11DEAPPD",
                                   "E11INTDAY",
                                   "E11PRIDAY",
                                   "E11PRDNME",
                                   "D11ACMPRO"
                                 };
  final static String fid = "1040916175428";
  final static String rid = "40CAF10FE7E33";
  final static String fmtname = "EDD043011";
  final int FIELDCOUNT = 52;
  private static Hashtable tlookup = new Hashtable();
  private CharacterField fieldH11USERID = null;
  private CharacterField fieldH11PROGRM = null;
  private CharacterField fieldH11TIMSYS = null;
  private CharacterField fieldH11SCRCOD = null;
  private CharacterField fieldH11OPECOD = null;
  private CharacterField fieldH11FLGMAS = null;
  private CharacterField fieldH11FLGWK1 = null;
  private CharacterField fieldH11FLGWK2 = null;
  private CharacterField fieldH11FLGWK3 = null;
  private DecimalField fieldE11ACMACC = null;
  private CharacterField fieldE11ACMPRO = null;
  private DecimalField fieldE11ACMCUN = null;
  private CharacterField fieldE11CUSNA1 = null;
  private CharacterField fieldE11INVPRD = null;
  private CharacterField fieldE11INVRBK = null;
  private DecimalField fieldE11INVRBR = null;
  private CharacterField fieldE11INVRCY = null;
  private DecimalField fieldE11INVRGL = null;
  private DecimalField fieldE11INVRAC = null;
  private DecimalField fieldE11INVOAM = null;
  private CharacterField fieldE11INVRTB = null;
  private DecimalField fieldE11INVPMT = null;
  private CharacterField fieldE11INVPIF = null;
  private CharacterField fieldE11INVIIP = null;
  private DecimalField fieldE11INVMIN = null;
  private DecimalField fieldE11INVMUL = null;
  private DecimalField fieldE11INVMAX = null;
  private DecimalField fieldE11INVBLS = null;
  private CharacterField fieldE11INVGLT = null;
  private DecimalField fieldE11INVSD1 = null;
  private DecimalField fieldE11INVSD2 = null;
  private DecimalField fieldE11INVSD3 = null;
  private DecimalField fieldE11INVMD1 = null;
  private DecimalField fieldE11INVMD2 = null;
  private DecimalField fieldE11INVMD3 = null;
  private DecimalField fieldE11INVRD1 = null;
  private DecimalField fieldE11INVRD2 = null;
  private DecimalField fieldE11INVRD3 = null;
  private CharacterField fieldE11INVRPD = null;
  private CharacterField fieldE11INVRFL = null;
  private CharacterField fieldE11INVSTS = null;
  private CharacterField fieldE11DEAFTB = null;
  private CharacterField fieldE11DEAFTY = null;
  private DecimalField fieldE11FLTRTE = null;
  private DecimalField fieldE11DEARTE = null;
  private DecimalField fieldE11DLSRTE = null;
  private CharacterField fieldE11DEAIPD = null;
  private CharacterField fieldE11DEAPPD = null;
  private DecimalField fieldE11INTDAY = null;
  private DecimalField fieldE11PRIDAY = null;
  private CharacterField fieldE11PRDNME = null;
  private CharacterField fieldD11ACMPRO = null;

  /**
  * Constructor for EDD043011Message.
  */
  public EDD043011Message()
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
    recordsize = 414;
    fileid = fid;
    recordid = rid;
    message = new byte[getByteLength()];
    formatname = fmtname;
    fieldnames = fldnames;
    tagnames = tnames;
    fields = new MessageField[FIELDCOUNT];

    fields[0] = fieldH11USERID =
    new CharacterField(message, HEADERSIZE + 0, 10, "H11USERID");
    fields[1] = fieldH11PROGRM =
    new CharacterField(message, HEADERSIZE + 10, 10, "H11PROGRM");
    fields[2] = fieldH11TIMSYS =
    new CharacterField(message, HEADERSIZE + 20, 12, "H11TIMSYS");
    fields[3] = fieldH11SCRCOD =
    new CharacterField(message, HEADERSIZE + 32, 2, "H11SCRCOD");
    fields[4] = fieldH11OPECOD =
    new CharacterField(message, HEADERSIZE + 34, 4, "H11OPECOD");
    fields[5] = fieldH11FLGMAS =
    new CharacterField(message, HEADERSIZE + 38, 1, "H11FLGMAS");
    fields[6] = fieldH11FLGWK1 =
    new CharacterField(message, HEADERSIZE + 39, 1, "H11FLGWK1");
    fields[7] = fieldH11FLGWK2 =
    new CharacterField(message, HEADERSIZE + 40, 1, "H11FLGWK2");
    fields[8] = fieldH11FLGWK3 =
    new CharacterField(message, HEADERSIZE + 41, 1, "H11FLGWK3");
    fields[9] = fieldE11ACMACC =
    new DecimalField(message, HEADERSIZE + 42, 13, 0, "E11ACMACC");
    fields[10] = fieldE11ACMPRO =
    new CharacterField(message, HEADERSIZE + 55, 4, "E11ACMPRO");
    fields[11] = fieldE11ACMCUN =
    new DecimalField(message, HEADERSIZE + 59, 10, 0, "E11ACMCUN");
    fields[12] = fieldE11CUSNA1 =
    new CharacterField(message, HEADERSIZE + 69, 45, "E11CUSNA1");
    fields[13] = fieldE11INVPRD =
    new CharacterField(message, HEADERSIZE + 114, 4, "E11INVPRD");
    fields[14] = fieldE11INVRBK =
    new CharacterField(message, HEADERSIZE + 118, 2, "E11INVRBK");
    fields[15] = fieldE11INVRBR =
    new DecimalField(message, HEADERSIZE + 120, 4, 0, "E11INVRBR");
    fields[16] = fieldE11INVRCY =
    new CharacterField(message, HEADERSIZE + 124, 3, "E11INVRCY");
    fields[17] = fieldE11INVRGL =
    new DecimalField(message, HEADERSIZE + 127, 17, 0, "E11INVRGL");
    fields[18] = fieldE11INVRAC =
    new DecimalField(message, HEADERSIZE + 144, 13, 0, "E11INVRAC");
    fields[19] = fieldE11INVOAM =
    new DecimalField(message, HEADERSIZE + 157, 17, 2, "E11INVOAM");
    fields[20] = fieldE11INVRTB =
    new CharacterField(message, HEADERSIZE + 174, 1, "E11INVRTB");
    fields[21] = fieldE11INVPMT =
    new DecimalField(message, HEADERSIZE + 175, 17, 2, "E11INVPMT");
    fields[22] = fieldE11INVPIF =
    new CharacterField(message, HEADERSIZE + 192, 1, "E11INVPIF");
    fields[23] = fieldE11INVIIP =
    new CharacterField(message, HEADERSIZE + 193, 1, "E11INVIIP");
    fields[24] = fieldE11INVMIN =
    new DecimalField(message, HEADERSIZE + 194, 17, 2, "E11INVMIN");
    fields[25] = fieldE11INVMUL =
    new DecimalField(message, HEADERSIZE + 211, 17, 2, "E11INVMUL");
    fields[26] = fieldE11INVMAX =
    new DecimalField(message, HEADERSIZE + 228, 17, 2, "E11INVMAX");
    fields[27] = fieldE11INVBLS =
    new DecimalField(message, HEADERSIZE + 245, 17, 2, "E11INVBLS");
    fields[28] = fieldE11INVGLT =
    new CharacterField(message, HEADERSIZE + 262, 1, "E11INVGLT");
    fields[29] = fieldE11INVSD1 =
    new DecimalField(message, HEADERSIZE + 263, 3, 0, "E11INVSD1");
    fields[30] = fieldE11INVSD2 =
    new DecimalField(message, HEADERSIZE + 266, 3, 0, "E11INVSD2");
    fields[31] = fieldE11INVSD3 =
    new DecimalField(message, HEADERSIZE + 269, 3, 0, "E11INVSD3");
    fields[32] = fieldE11INVMD1 =
    new DecimalField(message, HEADERSIZE + 272, 3, 0, "E11INVMD1");
    fields[33] = fieldE11INVMD2 =
    new DecimalField(message, HEADERSIZE + 275, 3, 0, "E11INVMD2");
    fields[34] = fieldE11INVMD3 =
    new DecimalField(message, HEADERSIZE + 278, 3, 0, "E11INVMD3");
    fields[35] = fieldE11INVRD1 =
    new DecimalField(message, HEADERSIZE + 281, 3, 0, "E11INVRD1");
    fields[36] = fieldE11INVRD2 =
    new DecimalField(message, HEADERSIZE + 284, 3, 0, "E11INVRD2");
    fields[37] = fieldE11INVRD3 =
    new DecimalField(message, HEADERSIZE + 287, 3, 0, "E11INVRD3");
    fields[38] = fieldE11INVRPD =
    new CharacterField(message, HEADERSIZE + 290, 3, "E11INVRPD");
    fields[39] = fieldE11INVRFL =
    new CharacterField(message, HEADERSIZE + 293, 1, "E11INVRFL");
    fields[40] = fieldE11INVSTS =
    new CharacterField(message, HEADERSIZE + 294, 1, "E11INVSTS");
    fields[41] = fieldE11DEAFTB =
    new CharacterField(message, HEADERSIZE + 295, 2, "E11DEAFTB");
    fields[42] = fieldE11DEAFTY =
    new CharacterField(message, HEADERSIZE + 297, 2, "E11DEAFTY");
    fields[43] = fieldE11FLTRTE =
    new DecimalField(message, HEADERSIZE + 299, 11, 6, "E11FLTRTE");
    fields[44] = fieldE11DEARTE =
    new DecimalField(message, HEADERSIZE + 310, 11, 6, "E11DEARTE");
    fields[45] = fieldE11DLSRTE =
    new DecimalField(message, HEADERSIZE + 321, 11, 6, "E11DLSRTE");
    fields[46] = fieldE11DEAIPD =
    new CharacterField(message, HEADERSIZE + 332, 3, "E11DEAIPD");
    fields[47] = fieldE11DEAPPD =
    new CharacterField(message, HEADERSIZE + 335, 3, "E11DEAPPD");
    fields[48] = fieldE11INTDAY =
    new DecimalField(message, HEADERSIZE + 338, 3, 0, "E11INTDAY");
    fields[49] = fieldE11PRIDAY =
    new DecimalField(message, HEADERSIZE + 341, 3, 0, "E11PRIDAY");
    fields[50] = fieldE11PRDNME =
    new CharacterField(message, HEADERSIZE + 344, 35, "E11PRDNME");
    fields[51] = fieldD11ACMPRO =
    new CharacterField(message, HEADERSIZE + 379, 35, "D11ACMPRO");

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
  * Set field H11USERID using a String value.
  */
  public void setH11USERID(String newvalue)
  {
    fieldH11USERID.setString(newvalue);
  }

  /**
  * Get value of field H11USERID as a String.
  */
  public String getH11USERID()
  {
    return fieldH11USERID.getString();
  }

  /**
  * Set field H11PROGRM using a String value.
  */
  public void setH11PROGRM(String newvalue)
  {
    fieldH11PROGRM.setString(newvalue);
  }

  /**
  * Get value of field H11PROGRM as a String.
  */
  public String getH11PROGRM()
  {
    return fieldH11PROGRM.getString();
  }

  /**
  * Set field H11TIMSYS using a String value.
  */
  public void setH11TIMSYS(String newvalue)
  {
    fieldH11TIMSYS.setString(newvalue);
  }

  /**
  * Get value of field H11TIMSYS as a String.
  */
  public String getH11TIMSYS()
  {
    return fieldH11TIMSYS.getString();
  }

  /**
  * Set field H11SCRCOD using a String value.
  */
  public void setH11SCRCOD(String newvalue)
  {
    fieldH11SCRCOD.setString(newvalue);
  }

  /**
  * Get value of field H11SCRCOD as a String.
  */
  public String getH11SCRCOD()
  {
    return fieldH11SCRCOD.getString();
  }

  /**
  * Set field H11OPECOD using a String value.
  */
  public void setH11OPECOD(String newvalue)
  {
    fieldH11OPECOD.setString(newvalue);
  }

  /**
  * Get value of field H11OPECOD as a String.
  */
  public String getH11OPECOD()
  {
    return fieldH11OPECOD.getString();
  }

  /**
  * Set field H11FLGMAS using a String value.
  */
  public void setH11FLGMAS(String newvalue)
  {
    fieldH11FLGMAS.setString(newvalue);
  }

  /**
  * Get value of field H11FLGMAS as a String.
  */
  public String getH11FLGMAS()
  {
    return fieldH11FLGMAS.getString();
  }

  /**
  * Set field H11FLGWK1 using a String value.
  */
  public void setH11FLGWK1(String newvalue)
  {
    fieldH11FLGWK1.setString(newvalue);
  }

  /**
  * Get value of field H11FLGWK1 as a String.
  */
  public String getH11FLGWK1()
  {
    return fieldH11FLGWK1.getString();
  }

  /**
  * Set field H11FLGWK2 using a String value.
  */
  public void setH11FLGWK2(String newvalue)
  {
    fieldH11FLGWK2.setString(newvalue);
  }

  /**
  * Get value of field H11FLGWK2 as a String.
  */
  public String getH11FLGWK2()
  {
    return fieldH11FLGWK2.getString();
  }

  /**
  * Set field H11FLGWK3 using a String value.
  */
  public void setH11FLGWK3(String newvalue)
  {
    fieldH11FLGWK3.setString(newvalue);
  }

  /**
  * Get value of field H11FLGWK3 as a String.
  */
  public String getH11FLGWK3()
  {
    return fieldH11FLGWK3.getString();
  }

  /**
  * Set field E11ACMACC using a String value.
  */
  public void setE11ACMACC(String newvalue)
  {
    fieldE11ACMACC.setString(newvalue);
  }

  /**
  * Get value of field E11ACMACC as a String.
  */
  public String getE11ACMACC()
  {
    return fieldE11ACMACC.getString();
  }

  /**
  * Set numeric field E11ACMACC using a BigDecimal value.
  */
  public void setE11ACMACC(BigDecimal newvalue)
  {
    fieldE11ACMACC.setBigDecimal(newvalue);
  }

  /**
  * Return value of numeric field E11ACMACC as a BigDecimal.
  */
  public BigDecimal getBigDecimalE11ACMACC()
  {
    return fieldE11ACMACC.getBigDecimal();
  }

  /**
  * Set field E11ACMPRO using a String value.
  */
  public void setE11ACMPRO(String newvalue)
  {
    fieldE11ACMPRO.setString(newvalue);
  }

  /**
  * Get value of field E11ACMPRO as a String.
  */
  public String getE11ACMPRO()
  {
    return fieldE11ACMPRO.getString();
  }

  /**
  * Set field E11ACMCUN using a String value.
  */
  public void setE11ACMCUN(String newvalue)
  {
    fieldE11ACMCUN.setString(newvalue);
  }

  /**
  * Get value of field E11ACMCUN as a String.
  */
  public String getE11ACMCUN()
  {
    return fieldE11ACMCUN.getString();
  }

  /**
  * Set numeric field E11ACMCUN using a BigDecimal value.
  */
  public void setE11ACMCUN(BigDecimal newvalue)
  {
    fieldE11ACMCUN.setBigDecimal(newvalue);
  }

  /**
  * Return value of numeric field E11ACMCUN as a BigDecimal.
  */
  public BigDecimal getBigDecimalE11ACMCUN()
  {
    return fieldE11ACMCUN.getBigDecimal();
  }

  /**
  * Set field E11CUSNA1 using a String value.
  */
  public void setE11CUSNA1(String newvalue)
  {
    fieldE11CUSNA1.setString(newvalue);
  }

  /**
  * Get value of field E11CUSNA1 as a String.
  */
  public String getE11CUSNA1()
  {
    return fieldE11CUSNA1.getString();
  }

  /**
  * Set field E11INVPRD using a String value.
  */
  public void setE11INVPRD(String newvalue)
  {
    fieldE11INVPRD.setString(newvalue);
  }

  /**
  * Get value of field E11INVPRD as a String.
  */
  public String getE11INVPRD()
  {
    return fieldE11INVPRD.getString();
  }

  /**
  * Set field E11INVRBK using a String value.
  */
  public void setE11INVRBK(String newvalue)
  {
    fieldE11INVRBK.setString(newvalue);
  }

  /**
  * Get value of field E11INVRBK as a String.
  */
  public String getE11INVRBK()
  {
    return fieldE11INVRBK.getString();
  }

  /**
  * Set field E11INVRBR using a String value.
  */
  public void setE11INVRBR(String newvalue)
  {
    fieldE11INVRBR.setString(newvalue);
  }

  /**
  * Get value of field E11INVRBR as a String.
  */
  public String getE11INVRBR()
  {
    return fieldE11INVRBR.getString();
  }

  /**
  * Set numeric field E11INVRBR using a BigDecimal value.
  */
  public void setE11INVRBR(BigDecimal newvalue)
  {
    fieldE11INVRBR.setBigDecimal(newvalue);
  }

  /**
  * Return value of numeric field E11INVRBR as a BigDecimal.
  */
  public BigDecimal getBigDecimalE11INVRBR()
  {
    return fieldE11INVRBR.getBigDecimal();
  }

  /**
  * Set field E11INVRCY using a String value.
  */
  public void setE11INVRCY(String newvalue)
  {
    fieldE11INVRCY.setString(newvalue);
  }

  /**
  * Get value of field E11INVRCY as a String.
  */
  public String getE11INVRCY()
  {
    return fieldE11INVRCY.getString();
  }

  /**
  * Set field E11INVRGL using a String value.
  */
  public void setE11INVRGL(String newvalue)
  {
    fieldE11INVRGL.setString(newvalue);
  }

  /**
  * Get value of field E11INVRGL as a String.
  */
  public String getE11INVRGL()
  {
    return fieldE11INVRGL.getString();
  }

  /**
  * Set numeric field E11INVRGL using a BigDecimal value.
  */
  public void setE11INVRGL(BigDecimal newvalue)
  {
    fieldE11INVRGL.setBigDecimal(newvalue);
  }

  /**
  * Return value of numeric field E11INVRGL as a BigDecimal.
  */
  public BigDecimal getBigDecimalE11INVRGL()
  {
    return fieldE11INVRGL.getBigDecimal();
  }

  /**
  * Set field E11INVRAC using a String value.
  */
  public void setE11INVRAC(String newvalue)
  {
    fieldE11INVRAC.setString(newvalue);
  }

  /**
  * Get value of field E11INVRAC as a String.
  */
  public String getE11INVRAC()
  {
    return fieldE11INVRAC.getString();
  }

  /**
  * Set numeric field E11INVRAC using a BigDecimal value.
  */
  public void setE11INVRAC(BigDecimal newvalue)
  {
    fieldE11INVRAC.setBigDecimal(newvalue);
  }

  /**
  * Return value of numeric field E11INVRAC as a BigDecimal.
  */
  public BigDecimal getBigDecimalE11INVRAC()
  {
    return fieldE11INVRAC.getBigDecimal();
  }

  /**
  * Set field E11INVOAM using a String value.
  */
  public void setE11INVOAM(String newvalue)
  {
    fieldE11INVOAM.setString(newvalue);
  }

  /**
  * Get value of field E11INVOAM as a String.
  */
  public String getE11INVOAM()
  {
    return fieldE11INVOAM.getString();
  }

  /**
  * Set numeric field E11INVOAM using a BigDecimal value.
  */
  public void setE11INVOAM(BigDecimal newvalue)
  {
    fieldE11INVOAM.setBigDecimal(newvalue);
  }

  /**
  * Return value of numeric field E11INVOAM as a BigDecimal.
  */
  public BigDecimal getBigDecimalE11INVOAM()
  {
    return fieldE11INVOAM.getBigDecimal();
  }

  /**
  * Set field E11INVRTB using a String value.
  */
  public void setE11INVRTB(String newvalue)
  {
    fieldE11INVRTB.setString(newvalue);
  }

  /**
  * Get value of field E11INVRTB as a String.
  */
  public String getE11INVRTB()
  {
    return fieldE11INVRTB.getString();
  }

  /**
  * Set field E11INVPMT using a String value.
  */
  public void setE11INVPMT(String newvalue)
  {
    fieldE11INVPMT.setString(newvalue);
  }

  /**
  * Get value of field E11INVPMT as a String.
  */
  public String getE11INVPMT()
  {
    return fieldE11INVPMT.getString();
  }

  /**
  * Set numeric field E11INVPMT using a BigDecimal value.
  */
  public void setE11INVPMT(BigDecimal newvalue)
  {
    fieldE11INVPMT.setBigDecimal(newvalue);
  }

  /**
  * Return value of numeric field E11INVPMT as a BigDecimal.
  */
  public BigDecimal getBigDecimalE11INVPMT()
  {
    return fieldE11INVPMT.getBigDecimal();
  }

  /**
  * Set field E11INVPIF using a String value.
  */
  public void setE11INVPIF(String newvalue)
  {
    fieldE11INVPIF.setString(newvalue);
  }

  /**
  * Get value of field E11INVPIF as a String.
  */
  public String getE11INVPIF()
  {
    return fieldE11INVPIF.getString();
  }

  /**
  * Set field E11INVIIP using a String value.
  */
  public void setE11INVIIP(String newvalue)
  {
    fieldE11INVIIP.setString(newvalue);
  }

  /**
  * Get value of field E11INVIIP as a String.
  */
  public String getE11INVIIP()
  {
    return fieldE11INVIIP.getString();
  }

  /**
  * Set field E11INVMIN using a String value.
  */
  public void setE11INVMIN(String newvalue)
  {
    fieldE11INVMIN.setString(newvalue);
  }

  /**
  * Get value of field E11INVMIN as a String.
  */
  public String getE11INVMIN()
  {
    return fieldE11INVMIN.getString();
  }

  /**
  * Set numeric field E11INVMIN using a BigDecimal value.
  */
  public void setE11INVMIN(BigDecimal newvalue)
  {
    fieldE11INVMIN.setBigDecimal(newvalue);
  }

  /**
  * Return value of numeric field E11INVMIN as a BigDecimal.
  */
  public BigDecimal getBigDecimalE11INVMIN()
  {
    return fieldE11INVMIN.getBigDecimal();
  }

  /**
  * Set field E11INVMUL using a String value.
  */
  public void setE11INVMUL(String newvalue)
  {
    fieldE11INVMUL.setString(newvalue);
  }

  /**
  * Get value of field E11INVMUL as a String.
  */
  public String getE11INVMUL()
  {
    return fieldE11INVMUL.getString();
  }

  /**
  * Set numeric field E11INVMUL using a BigDecimal value.
  */
  public void setE11INVMUL(BigDecimal newvalue)
  {
    fieldE11INVMUL.setBigDecimal(newvalue);
  }

  /**
  * Return value of numeric field E11INVMUL as a BigDecimal.
  */
  public BigDecimal getBigDecimalE11INVMUL()
  {
    return fieldE11INVMUL.getBigDecimal();
  }

  /**
  * Set field E11INVMAX using a String value.
  */
  public void setE11INVMAX(String newvalue)
  {
    fieldE11INVMAX.setString(newvalue);
  }

  /**
  * Get value of field E11INVMAX as a String.
  */
  public String getE11INVMAX()
  {
    return fieldE11INVMAX.getString();
  }

  /**
  * Set numeric field E11INVMAX using a BigDecimal value.
  */
  public void setE11INVMAX(BigDecimal newvalue)
  {
    fieldE11INVMAX.setBigDecimal(newvalue);
  }

  /**
  * Return value of numeric field E11INVMAX as a BigDecimal.
  */
  public BigDecimal getBigDecimalE11INVMAX()
  {
    return fieldE11INVMAX.getBigDecimal();
  }

  /**
  * Set field E11INVBLS using a String value.
  */
  public void setE11INVBLS(String newvalue)
  {
    fieldE11INVBLS.setString(newvalue);
  }

  /**
  * Get value of field E11INVBLS as a String.
  */
  public String getE11INVBLS()
  {
    return fieldE11INVBLS.getString();
  }

  /**
  * Set numeric field E11INVBLS using a BigDecimal value.
  */
  public void setE11INVBLS(BigDecimal newvalue)
  {
    fieldE11INVBLS.setBigDecimal(newvalue);
  }

  /**
  * Return value of numeric field E11INVBLS as a BigDecimal.
  */
  public BigDecimal getBigDecimalE11INVBLS()
  {
    return fieldE11INVBLS.getBigDecimal();
  }

  /**
  * Set field E11INVGLT using a String value.
  */
  public void setE11INVGLT(String newvalue)
  {
    fieldE11INVGLT.setString(newvalue);
  }

  /**
  * Get value of field E11INVGLT as a String.
  */
  public String getE11INVGLT()
  {
    return fieldE11INVGLT.getString();
  }

  /**
  * Set field E11INVSD1 using a String value.
  */
  public void setE11INVSD1(String newvalue)
  {
    fieldE11INVSD1.setString(newvalue);
  }

  /**
  * Get value of field E11INVSD1 as a String.
  */
  public String getE11INVSD1()
  {
    return fieldE11INVSD1.getString();
  }

  /**
  * Set numeric field E11INVSD1 using a BigDecimal value.
  */
  public void setE11INVSD1(BigDecimal newvalue)
  {
    fieldE11INVSD1.setBigDecimal(newvalue);
  }

  /**
  * Return value of numeric field E11INVSD1 as a BigDecimal.
  */
  public BigDecimal getBigDecimalE11INVSD1()
  {
    return fieldE11INVSD1.getBigDecimal();
  }

  /**
  * Set field E11INVSD2 using a String value.
  */
  public void setE11INVSD2(String newvalue)
  {
    fieldE11INVSD2.setString(newvalue);
  }

  /**
  * Get value of field E11INVSD2 as a String.
  */
  public String getE11INVSD2()
  {
    return fieldE11INVSD2.getString();
  }

  /**
  * Set numeric field E11INVSD2 using a BigDecimal value.
  */
  public void setE11INVSD2(BigDecimal newvalue)
  {
    fieldE11INVSD2.setBigDecimal(newvalue);
  }

  /**
  * Return value of numeric field E11INVSD2 as a BigDecimal.
  */
  public BigDecimal getBigDecimalE11INVSD2()
  {
    return fieldE11INVSD2.getBigDecimal();
  }

  /**
  * Set field E11INVSD3 using a String value.
  */
  public void setE11INVSD3(String newvalue)
  {
    fieldE11INVSD3.setString(newvalue);
  }

  /**
  * Get value of field E11INVSD3 as a String.
  */
  public String getE11INVSD3()
  {
    return fieldE11INVSD3.getString();
  }

  /**
  * Set numeric field E11INVSD3 using a BigDecimal value.
  */
  public void setE11INVSD3(BigDecimal newvalue)
  {
    fieldE11INVSD3.setBigDecimal(newvalue);
  }

  /**
  * Return value of numeric field E11INVSD3 as a BigDecimal.
  */
  public BigDecimal getBigDecimalE11INVSD3()
  {
    return fieldE11INVSD3.getBigDecimal();
  }

  /**
  * Set field E11INVMD1 using a String value.
  */
  public void setE11INVMD1(String newvalue)
  {
    fieldE11INVMD1.setString(newvalue);
  }

  /**
  * Get value of field E11INVMD1 as a String.
  */
  public String getE11INVMD1()
  {
    return fieldE11INVMD1.getString();
  }

  /**
  * Set numeric field E11INVMD1 using a BigDecimal value.
  */
  public void setE11INVMD1(BigDecimal newvalue)
  {
    fieldE11INVMD1.setBigDecimal(newvalue);
  }

  /**
  * Return value of numeric field E11INVMD1 as a BigDecimal.
  */
  public BigDecimal getBigDecimalE11INVMD1()
  {
    return fieldE11INVMD1.getBigDecimal();
  }

  /**
  * Set field E11INVMD2 using a String value.
  */
  public void setE11INVMD2(String newvalue)
  {
    fieldE11INVMD2.setString(newvalue);
  }

  /**
  * Get value of field E11INVMD2 as a String.
  */
  public String getE11INVMD2()
  {
    return fieldE11INVMD2.getString();
  }

  /**
  * Set numeric field E11INVMD2 using a BigDecimal value.
  */
  public void setE11INVMD2(BigDecimal newvalue)
  {
    fieldE11INVMD2.setBigDecimal(newvalue);
  }

  /**
  * Return value of numeric field E11INVMD2 as a BigDecimal.
  */
  public BigDecimal getBigDecimalE11INVMD2()
  {
    return fieldE11INVMD2.getBigDecimal();
  }

  /**
  * Set field E11INVMD3 using a String value.
  */
  public void setE11INVMD3(String newvalue)
  {
    fieldE11INVMD3.setString(newvalue);
  }

  /**
  * Get value of field E11INVMD3 as a String.
  */
  public String getE11INVMD3()
  {
    return fieldE11INVMD3.getString();
  }

  /**
  * Set numeric field E11INVMD3 using a BigDecimal value.
  */
  public void setE11INVMD3(BigDecimal newvalue)
  {
    fieldE11INVMD3.setBigDecimal(newvalue);
  }

  /**
  * Return value of numeric field E11INVMD3 as a BigDecimal.
  */
  public BigDecimal getBigDecimalE11INVMD3()
  {
    return fieldE11INVMD3.getBigDecimal();
  }

  /**
  * Set field E11INVRD1 using a String value.
  */
  public void setE11INVRD1(String newvalue)
  {
    fieldE11INVRD1.setString(newvalue);
  }

  /**
  * Get value of field E11INVRD1 as a String.
  */
  public String getE11INVRD1()
  {
    return fieldE11INVRD1.getString();
  }

  /**
  * Set numeric field E11INVRD1 using a BigDecimal value.
  */
  public void setE11INVRD1(BigDecimal newvalue)
  {
    fieldE11INVRD1.setBigDecimal(newvalue);
  }

  /**
  * Return value of numeric field E11INVRD1 as a BigDecimal.
  */
  public BigDecimal getBigDecimalE11INVRD1()
  {
    return fieldE11INVRD1.getBigDecimal();
  }

  /**
  * Set field E11INVRD2 using a String value.
  */
  public void setE11INVRD2(String newvalue)
  {
    fieldE11INVRD2.setString(newvalue);
  }

  /**
  * Get value of field E11INVRD2 as a String.
  */
  public String getE11INVRD2()
  {
    return fieldE11INVRD2.getString();
  }

  /**
  * Set numeric field E11INVRD2 using a BigDecimal value.
  */
  public void setE11INVRD2(BigDecimal newvalue)
  {
    fieldE11INVRD2.setBigDecimal(newvalue);
  }

  /**
  * Return value of numeric field E11INVRD2 as a BigDecimal.
  */
  public BigDecimal getBigDecimalE11INVRD2()
  {
    return fieldE11INVRD2.getBigDecimal();
  }

  /**
  * Set field E11INVRD3 using a String value.
  */
  public void setE11INVRD3(String newvalue)
  {
    fieldE11INVRD3.setString(newvalue);
  }

  /**
  * Get value of field E11INVRD3 as a String.
  */
  public String getE11INVRD3()
  {
    return fieldE11INVRD3.getString();
  }

  /**
  * Set numeric field E11INVRD3 using a BigDecimal value.
  */
  public void setE11INVRD3(BigDecimal newvalue)
  {
    fieldE11INVRD3.setBigDecimal(newvalue);
  }

  /**
  * Return value of numeric field E11INVRD3 as a BigDecimal.
  */
  public BigDecimal getBigDecimalE11INVRD3()
  {
    return fieldE11INVRD3.getBigDecimal();
  }

  /**
  * Set field E11INVRPD using a String value.
  */
  public void setE11INVRPD(String newvalue)
  {
    fieldE11INVRPD.setString(newvalue);
  }

  /**
  * Get value of field E11INVRPD as a String.
  */
  public String getE11INVRPD()
  {
    return fieldE11INVRPD.getString();
  }

  /**
  * Set field E11INVRFL using a String value.
  */
  public void setE11INVRFL(String newvalue)
  {
    fieldE11INVRFL.setString(newvalue);
  }

  /**
  * Get value of field E11INVRFL as a String.
  */
  public String getE11INVRFL()
  {
    return fieldE11INVRFL.getString();
  }

  /**
  * Set field E11INVSTS using a String value.
  */
  public void setE11INVSTS(String newvalue)
  {
    fieldE11INVSTS.setString(newvalue);
  }

  /**
  * Get value of field E11INVSTS as a String.
  */
  public String getE11INVSTS()
  {
    return fieldE11INVSTS.getString();
  }

  /**
  * Set field E11DEAFTB using a String value.
  */
  public void setE11DEAFTB(String newvalue)
  {
    fieldE11DEAFTB.setString(newvalue);
  }

  /**
  * Get value of field E11DEAFTB as a String.
  */
  public String getE11DEAFTB()
  {
    return fieldE11DEAFTB.getString();
  }

  /**
  * Set field E11DEAFTY using a String value.
  */
  public void setE11DEAFTY(String newvalue)
  {
    fieldE11DEAFTY.setString(newvalue);
  }

  /**
  * Get value of field E11DEAFTY as a String.
  */
  public String getE11DEAFTY()
  {
    return fieldE11DEAFTY.getString();
  }

  /**
  * Set field E11FLTRTE using a String value.
  */
  public void setE11FLTRTE(String newvalue)
  {
    fieldE11FLTRTE.setString(newvalue);
  }

  /**
  * Get value of field E11FLTRTE as a String.
  */
  public String getE11FLTRTE()
  {
    return fieldE11FLTRTE.getString();
  }

  /**
  * Set numeric field E11FLTRTE using a BigDecimal value.
  */
  public void setE11FLTRTE(BigDecimal newvalue)
  {
    fieldE11FLTRTE.setBigDecimal(newvalue);
  }

  /**
  * Return value of numeric field E11FLTRTE as a BigDecimal.
  */
  public BigDecimal getBigDecimalE11FLTRTE()
  {
    return fieldE11FLTRTE.getBigDecimal();
  }

  /**
  * Set field E11DEARTE using a String value.
  */
  public void setE11DEARTE(String newvalue)
  {
    fieldE11DEARTE.setString(newvalue);
  }

  /**
  * Get value of field E11DEARTE as a String.
  */
  public String getE11DEARTE()
  {
    return fieldE11DEARTE.getString();
  }

  /**
  * Set numeric field E11DEARTE using a BigDecimal value.
  */
  public void setE11DEARTE(BigDecimal newvalue)
  {
    fieldE11DEARTE.setBigDecimal(newvalue);
  }

  /**
  * Return value of numeric field E11DEARTE as a BigDecimal.
  */
  public BigDecimal getBigDecimalE11DEARTE()
  {
    return fieldE11DEARTE.getBigDecimal();
  }

  /**
  * Set field E11DLSRTE using a String value.
  */
  public void setE11DLSRTE(String newvalue)
  {
    fieldE11DLSRTE.setString(newvalue);
  }

  /**
  * Get value of field E11DLSRTE as a String.
  */
  public String getE11DLSRTE()
  {
    return fieldE11DLSRTE.getString();
  }

  /**
  * Set numeric field E11DLSRTE using a BigDecimal value.
  */
  public void setE11DLSRTE(BigDecimal newvalue)
  {
    fieldE11DLSRTE.setBigDecimal(newvalue);
  }

  /**
  * Return value of numeric field E11DLSRTE as a BigDecimal.
  */
  public BigDecimal getBigDecimalE11DLSRTE()
  {
    return fieldE11DLSRTE.getBigDecimal();
  }

  /**
  * Set field E11DEAIPD using a String value.
  */
  public void setE11DEAIPD(String newvalue)
  {
    fieldE11DEAIPD.setString(newvalue);
  }

  /**
  * Get value of field E11DEAIPD as a String.
  */
  public String getE11DEAIPD()
  {
    return fieldE11DEAIPD.getString();
  }

  /**
  * Set field E11DEAPPD using a String value.
  */
  public void setE11DEAPPD(String newvalue)
  {
    fieldE11DEAPPD.setString(newvalue);
  }

  /**
  * Get value of field E11DEAPPD as a String.
  */
  public String getE11DEAPPD()
  {
    return fieldE11DEAPPD.getString();
  }

  /**
  * Set field E11INTDAY using a String value.
  */
  public void setE11INTDAY(String newvalue)
  {
    fieldE11INTDAY.setString(newvalue);
  }

  /**
  * Get value of field E11INTDAY as a String.
  */
  public String getE11INTDAY()
  {
    return fieldE11INTDAY.getString();
  }

  /**
  * Set numeric field E11INTDAY using a BigDecimal value.
  */
  public void setE11INTDAY(BigDecimal newvalue)
  {
    fieldE11INTDAY.setBigDecimal(newvalue);
  }

  /**
  * Return value of numeric field E11INTDAY as a BigDecimal.
  */
  public BigDecimal getBigDecimalE11INTDAY()
  {
    return fieldE11INTDAY.getBigDecimal();
  }

  /**
  * Set field E11PRIDAY using a String value.
  */
  public void setE11PRIDAY(String newvalue)
  {
    fieldE11PRIDAY.setString(newvalue);
  }

  /**
  * Get value of field E11PRIDAY as a String.
  */
  public String getE11PRIDAY()
  {
    return fieldE11PRIDAY.getString();
  }

  /**
  * Set numeric field E11PRIDAY using a BigDecimal value.
  */
  public void setE11PRIDAY(BigDecimal newvalue)
  {
    fieldE11PRIDAY.setBigDecimal(newvalue);
  }

  /**
  * Return value of numeric field E11PRIDAY as a BigDecimal.
  */
  public BigDecimal getBigDecimalE11PRIDAY()
  {
    return fieldE11PRIDAY.getBigDecimal();
  }

  /**
  * Set field E11PRDNME using a String value.
  */
  public void setE11PRDNME(String newvalue)
  {
    fieldE11PRDNME.setString(newvalue);
  }

  /**
  * Get value of field E11PRDNME as a String.
  */
  public String getE11PRDNME()
  {
    return fieldE11PRDNME.getString();
  }

  /**
  * Set field D11ACMPRO using a String value.
  */
  public void setD11ACMPRO(String newvalue)
  {
    fieldD11ACMPRO.setString(newvalue);
  }

  /**
  * Get value of field D11ACMPRO as a String.
  */
  public String getD11ACMPRO()
  {
    return fieldD11ACMPRO.getString();
  }

}
