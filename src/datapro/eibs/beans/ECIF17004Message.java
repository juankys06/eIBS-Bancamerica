package datapro.eibs.beans;

import datapro.eibs.sockets.*;
import java.io.*;
import java.math.*;

import java.util.*;

/**
* Class generated by AS/400 CRTCLASS command from ECIF17004 physical file definition.
* 
* File level identifier is 1050726182718.
* Record format level identifier is 4983E2C604243.
*/

public class ECIF17004Message extends MessageRecord
{
  final static String fldnames[] = {
                                     "H04USERID",
                                     "H04PROGRM",
                                     "H04TIMSYS",
                                     "H04SCRCOD",
                                     "H04OPECOD",
                                     "H04FLGMAS",
                                     "H04FLGWK1",
                                     "H04FLGWK2",
                                     "H04FLGWK3",
                                     "E04OFICDE",
                                     "E04OFINME",
                                     "E04OFIEML",
                                     "E04OFIPHN",
                                     "E04OFIPXT",
                                     "E04OFICCN",
                                     "E04CUSCUN",
                                     "E04CUSNA1",
                                     "E04CUSSTS",
                                     "E04APLCDE",
                                     "E04FLGOPE",
                                     "E04CLSACC",
                                     "E04BNKNUM",
                                     "E04CCYCDE",
                                     "E04ACCTYP",
                                     "E04TYPDSC",
                                     "E04PROCDE",
                                     "E04PRODSC",
                                     "E04NUMOPE",
                                     "E04BSEPRI",
                                     "E04BSEINT",
                                     "E04BSEOTH",
                                     "E04BSETOT",
                                     "E04INDOPE"
                                   };
  final static String tnames[] = {
                                   "H04USERID",
                                   "H04PROGRM",
                                   "H04TIMSYS",
                                   "H04SCRCOD",
                                   "H04OPECOD",
                                   "H04FLGMAS",
                                   "H04FLGWK1",
                                   "H04FLGWK2",
                                   "H04FLGWK3",
                                   "E04OFICDE",
                                   "E04OFINME",
                                   "E04OFIEML",
                                   "E04OFIPHN",
                                   "E04OFIPXT",
                                   "E04OFICCN",
                                   "E04CUSCUN",
                                   "E04CUSNA1",
                                   "E04CUSSTS",
                                   "E04APLCDE",
                                   "E04FLGOPE",
                                   "E04CLSACC",
                                   "E04BNKNUM",
                                   "E04CCYCDE",
                                   "E04ACCTYP",
                                   "E04TYPDSC",
                                   "E04PROCDE",
                                   "E04PRODSC",
                                   "E04NUMOPE",
                                   "E04BSEPRI",
                                   "E04BSEINT",
                                   "E04BSEOTH",
                                   "E04BSETOT",
                                   "E04INDOPE"
                                 };
  final static String fid = "1050726182718";
  final static String rid = "4983E2C604243";
  final static String fmtname = "ECIF17004";
  final int FIELDCOUNT = 33;
  private static Hashtable tlookup = new Hashtable();
  private CharacterField fieldH04USERID = null;
  private CharacterField fieldH04PROGRM = null;
  private CharacterField fieldH04TIMSYS = null;
  private CharacterField fieldH04SCRCOD = null;
  private CharacterField fieldH04OPECOD = null;
  private CharacterField fieldH04FLGMAS = null;
  private CharacterField fieldH04FLGWK1 = null;
  private CharacterField fieldH04FLGWK2 = null;
  private CharacterField fieldH04FLGWK3 = null;
  private CharacterField fieldE04OFICDE = null;
  private CharacterField fieldE04OFINME = null;
  private CharacterField fieldE04OFIEML = null;
  private DecimalField fieldE04OFIPHN = null;
  private DecimalField fieldE04OFIPXT = null;
  private DecimalField fieldE04OFICCN = null;
  private DecimalField fieldE04CUSCUN = null;
  private CharacterField fieldE04CUSNA1 = null;
  private CharacterField fieldE04CUSSTS = null;
  private CharacterField fieldE04APLCDE = null;
  private CharacterField fieldE04FLGOPE = null;
  private DecimalField fieldE04CLSACC = null;
  private CharacterField fieldE04BNKNUM = null;
  private CharacterField fieldE04CCYCDE = null;
  private CharacterField fieldE04ACCTYP = null;
  private CharacterField fieldE04TYPDSC = null;
  private CharacterField fieldE04PROCDE = null;
  private CharacterField fieldE04PRODSC = null;
  private DecimalField fieldE04NUMOPE = null;
  private DecimalField fieldE04BSEPRI = null;
  private DecimalField fieldE04BSEINT = null;
  private DecimalField fieldE04BSEOTH = null;
  private DecimalField fieldE04BSETOT = null;
  private CharacterField fieldE04INDOPE = null;

  /**
  * Constructor for ECIF17004Message.
  */
  public ECIF17004Message()
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
    recordsize = 384;
    fileid = fid;
    recordid = rid;
    message = new byte[getByteLength()];
    formatname = fmtname;
    fieldnames = fldnames;
    tagnames = tnames;
    fields = new MessageField[FIELDCOUNT];

    fields[0] = fieldH04USERID =
    new CharacterField(message, HEADERSIZE + 0, 10, "H04USERID");
    fields[1] = fieldH04PROGRM =
    new CharacterField(message, HEADERSIZE + 10, 10, "H04PROGRM");
    fields[2] = fieldH04TIMSYS =
    new CharacterField(message, HEADERSIZE + 20, 12, "H04TIMSYS");
    fields[3] = fieldH04SCRCOD =
    new CharacterField(message, HEADERSIZE + 32, 2, "H04SCRCOD");
    fields[4] = fieldH04OPECOD =
    new CharacterField(message, HEADERSIZE + 34, 4, "H04OPECOD");
    fields[5] = fieldH04FLGMAS =
    new CharacterField(message, HEADERSIZE + 38, 1, "H04FLGMAS");
    fields[6] = fieldH04FLGWK1 =
    new CharacterField(message, HEADERSIZE + 39, 1, "H04FLGWK1");
    fields[7] = fieldH04FLGWK2 =
    new CharacterField(message, HEADERSIZE + 40, 1, "H04FLGWK2");
    fields[8] = fieldH04FLGWK3 =
    new CharacterField(message, HEADERSIZE + 41, 1, "H04FLGWK3");
    fields[9] = fieldE04OFICDE =
    new CharacterField(message, HEADERSIZE + 42, 4, "E04OFICDE");
    fields[10] = fieldE04OFINME =
    new CharacterField(message, HEADERSIZE + 46, 35, "E04OFINME");
    fields[11] = fieldE04OFIEML =
    new CharacterField(message, HEADERSIZE + 81, 40, "E04OFIEML");
    fields[12] = fieldE04OFIPHN =
    new DecimalField(message, HEADERSIZE + 121, 12, 0, "E04OFIPHN");
    fields[13] = fieldE04OFIPXT =
    new DecimalField(message, HEADERSIZE + 133, 5, 0, "E04OFIPXT");
    fields[14] = fieldE04OFICCN =
    new DecimalField(message, HEADERSIZE + 138, 9, 0, "E04OFICCN");
    fields[15] = fieldE04CUSCUN =
    new DecimalField(message, HEADERSIZE + 147, 10, 0, "E04CUSCUN");
    fields[16] = fieldE04CUSNA1 =
    new CharacterField(message, HEADERSIZE + 157, 45, "E04CUSNA1");
    fields[17] = fieldE04CUSSTS =
    new CharacterField(message, HEADERSIZE + 202, 20, "E04CUSSTS");
    fields[18] = fieldE04APLCDE =
    new CharacterField(message, HEADERSIZE + 222, 2, "E04APLCDE");
    fields[19] = fieldE04FLGOPE =
    new CharacterField(message, HEADERSIZE + 224, 1, "E04FLGOPE");
    fields[20] = fieldE04CLSACC =
    new DecimalField(message, HEADERSIZE + 225, 3, 0, "E04CLSACC");
    fields[21] = fieldE04BNKNUM =
    new CharacterField(message, HEADERSIZE + 228, 2, "E04BNKNUM");
    fields[22] = fieldE04CCYCDE =
    new CharacterField(message, HEADERSIZE + 230, 3, "E04CCYCDE");
    fields[23] = fieldE04ACCTYP =
    new CharacterField(message, HEADERSIZE + 233, 4, "E04ACCTYP");
    fields[24] = fieldE04TYPDSC =
    new CharacterField(message, HEADERSIZE + 237, 35, "E04TYPDSC");
    fields[25] = fieldE04PROCDE =
    new CharacterField(message, HEADERSIZE + 272, 4, "E04PROCDE");
    fields[26] = fieldE04PRODSC =
    new CharacterField(message, HEADERSIZE + 276, 35, "E04PRODSC");
    fields[27] = fieldE04NUMOPE =
    new DecimalField(message, HEADERSIZE + 311, 4, 0, "E04NUMOPE");
    fields[28] = fieldE04BSEPRI =
    new DecimalField(message, HEADERSIZE + 315, 17, 2, "E04BSEPRI");
    fields[29] = fieldE04BSEINT =
    new DecimalField(message, HEADERSIZE + 332, 17, 2, "E04BSEINT");
    fields[30] = fieldE04BSEOTH =
    new DecimalField(message, HEADERSIZE + 349, 17, 2, "E04BSEOTH");
    fields[31] = fieldE04BSETOT =
    new DecimalField(message, HEADERSIZE + 366, 17, 2, "E04BSETOT");
    fields[32] = fieldE04INDOPE =
    new CharacterField(message, HEADERSIZE + 383, 1, "E04INDOPE");

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
  * Set field H04USERID using a String value.
  */
  public void setH04USERID(String newvalue)
  {
    fieldH04USERID.setString(newvalue);
  }

  /**
  * Get value of field H04USERID as a String.
  */
  public String getH04USERID()
  {
    return fieldH04USERID.getString();
  }

  /**
  * Set field H04PROGRM using a String value.
  */
  public void setH04PROGRM(String newvalue)
  {
    fieldH04PROGRM.setString(newvalue);
  }

  /**
  * Get value of field H04PROGRM as a String.
  */
  public String getH04PROGRM()
  {
    return fieldH04PROGRM.getString();
  }

  /**
  * Set field H04TIMSYS using a String value.
  */
  public void setH04TIMSYS(String newvalue)
  {
    fieldH04TIMSYS.setString(newvalue);
  }

  /**
  * Get value of field H04TIMSYS as a String.
  */
  public String getH04TIMSYS()
  {
    return fieldH04TIMSYS.getString();
  }

  /**
  * Set field H04SCRCOD using a String value.
  */
  public void setH04SCRCOD(String newvalue)
  {
    fieldH04SCRCOD.setString(newvalue);
  }

  /**
  * Get value of field H04SCRCOD as a String.
  */
  public String getH04SCRCOD()
  {
    return fieldH04SCRCOD.getString();
  }

  /**
  * Set field H04OPECOD using a String value.
  */
  public void setH04OPECOD(String newvalue)
  {
    fieldH04OPECOD.setString(newvalue);
  }

  /**
  * Get value of field H04OPECOD as a String.
  */
  public String getH04OPECOD()
  {
    return fieldH04OPECOD.getString();
  }

  /**
  * Set field H04FLGMAS using a String value.
  */
  public void setH04FLGMAS(String newvalue)
  {
    fieldH04FLGMAS.setString(newvalue);
  }

  /**
  * Get value of field H04FLGMAS as a String.
  */
  public String getH04FLGMAS()
  {
    return fieldH04FLGMAS.getString();
  }

  /**
  * Set field H04FLGWK1 using a String value.
  */
  public void setH04FLGWK1(String newvalue)
  {
    fieldH04FLGWK1.setString(newvalue);
  }

  /**
  * Get value of field H04FLGWK1 as a String.
  */
  public String getH04FLGWK1()
  {
    return fieldH04FLGWK1.getString();
  }

  /**
  * Set field H04FLGWK2 using a String value.
  */
  public void setH04FLGWK2(String newvalue)
  {
    fieldH04FLGWK2.setString(newvalue);
  }

  /**
  * Get value of field H04FLGWK2 as a String.
  */
  public String getH04FLGWK2()
  {
    return fieldH04FLGWK2.getString();
  }

  /**
  * Set field H04FLGWK3 using a String value.
  */
  public void setH04FLGWK3(String newvalue)
  {
    fieldH04FLGWK3.setString(newvalue);
  }

  /**
  * Get value of field H04FLGWK3 as a String.
  */
  public String getH04FLGWK3()
  {
    return fieldH04FLGWK3.getString();
  }

  /**
  * Set field E04OFICDE using a String value.
  */
  public void setE04OFICDE(String newvalue)
  {
    fieldE04OFICDE.setString(newvalue);
  }

  /**
  * Get value of field E04OFICDE as a String.
  */
  public String getE04OFICDE()
  {
    return fieldE04OFICDE.getString();
  }

  /**
  * Set field E04OFINME using a String value.
  */
  public void setE04OFINME(String newvalue)
  {
    fieldE04OFINME.setString(newvalue);
  }

  /**
  * Get value of field E04OFINME as a String.
  */
  public String getE04OFINME()
  {
    return fieldE04OFINME.getString();
  }

  /**
  * Set field E04OFIEML using a String value.
  */
  public void setE04OFIEML(String newvalue)
  {
    fieldE04OFIEML.setString(newvalue);
  }

  /**
  * Get value of field E04OFIEML as a String.
  */
  public String getE04OFIEML()
  {
    return fieldE04OFIEML.getString();
  }

  /**
  * Set field E04OFIPHN using a String value.
  */
  public void setE04OFIPHN(String newvalue)
  {
    fieldE04OFIPHN.setString(newvalue);
  }

  /**
  * Get value of field E04OFIPHN as a String.
  */
  public String getE04OFIPHN()
  {
    return fieldE04OFIPHN.getString();
  }

  /**
  * Set numeric field E04OFIPHN using a BigDecimal value.
  */
  public void setE04OFIPHN(BigDecimal newvalue)
  {
    fieldE04OFIPHN.setBigDecimal(newvalue);
  }

  /**
  * Return value of numeric field E04OFIPHN as a BigDecimal.
  */
  public BigDecimal getBigDecimalE04OFIPHN()
  {
    return fieldE04OFIPHN.getBigDecimal();
  }

  /**
  * Set field E04OFIPXT using a String value.
  */
  public void setE04OFIPXT(String newvalue)
  {
    fieldE04OFIPXT.setString(newvalue);
  }

  /**
  * Get value of field E04OFIPXT as a String.
  */
  public String getE04OFIPXT()
  {
    return fieldE04OFIPXT.getString();
  }

  /**
  * Set numeric field E04OFIPXT using a BigDecimal value.
  */
  public void setE04OFIPXT(BigDecimal newvalue)
  {
    fieldE04OFIPXT.setBigDecimal(newvalue);
  }

  /**
  * Return value of numeric field E04OFIPXT as a BigDecimal.
  */
  public BigDecimal getBigDecimalE04OFIPXT()
  {
    return fieldE04OFIPXT.getBigDecimal();
  }

  /**
  * Set field E04OFICCN using a String value.
  */
  public void setE04OFICCN(String newvalue)
  {
    fieldE04OFICCN.setString(newvalue);
  }

  /**
  * Get value of field E04OFICCN as a String.
  */
  public String getE04OFICCN()
  {
    return fieldE04OFICCN.getString();
  }

  /**
  * Set numeric field E04OFICCN using a BigDecimal value.
  */
  public void setE04OFICCN(BigDecimal newvalue)
  {
    fieldE04OFICCN.setBigDecimal(newvalue);
  }

  /**
  * Return value of numeric field E04OFICCN as a BigDecimal.
  */
  public BigDecimal getBigDecimalE04OFICCN()
  {
    return fieldE04OFICCN.getBigDecimal();
  }

  /**
  * Set field E04CUSCUN using a String value.
  */
  public void setE04CUSCUN(String newvalue)
  {
    fieldE04CUSCUN.setString(newvalue);
  }

  /**
  * Get value of field E04CUSCUN as a String.
  */
  public String getE04CUSCUN()
  {
    return fieldE04CUSCUN.getString();
  }

  /**
  * Set numeric field E04CUSCUN using a BigDecimal value.
  */
  public void setE04CUSCUN(BigDecimal newvalue)
  {
    fieldE04CUSCUN.setBigDecimal(newvalue);
  }

  /**
  * Return value of numeric field E04CUSCUN as a BigDecimal.
  */
  public BigDecimal getBigDecimalE04CUSCUN()
  {
    return fieldE04CUSCUN.getBigDecimal();
  }

  /**
  * Set field E04CUSNA1 using a String value.
  */
  public void setE04CUSNA1(String newvalue)
  {
    fieldE04CUSNA1.setString(newvalue);
  }

  /**
  * Get value of field E04CUSNA1 as a String.
  */
  public String getE04CUSNA1()
  {
    return fieldE04CUSNA1.getString();
  }

  /**
  * Set field E04CUSSTS using a String value.
  */
  public void setE04CUSSTS(String newvalue)
  {
    fieldE04CUSSTS.setString(newvalue);
  }

  /**
  * Get value of field E04CUSSTS as a String.
  */
  public String getE04CUSSTS()
  {
    return fieldE04CUSSTS.getString();
  }

  /**
  * Set field E04APLCDE using a String value.
  */
  public void setE04APLCDE(String newvalue)
  {
    fieldE04APLCDE.setString(newvalue);
  }

  /**
  * Get value of field E04APLCDE as a String.
  */
  public String getE04APLCDE()
  {
    return fieldE04APLCDE.getString();
  }

  /**
  * Set field E04FLGOPE using a String value.
  */
  public void setE04FLGOPE(String newvalue)
  {
    fieldE04FLGOPE.setString(newvalue);
  }

  /**
  * Get value of field E04FLGOPE as a String.
  */
  public String getE04FLGOPE()
  {
    return fieldE04FLGOPE.getString();
  }

  /**
  * Set field E04CLSACC using a String value.
  */
  public void setE04CLSACC(String newvalue)
  {
    fieldE04CLSACC.setString(newvalue);
  }

  /**
  * Get value of field E04CLSACC as a String.
  */
  public String getE04CLSACC()
  {
    return fieldE04CLSACC.getString();
  }

  /**
  * Set numeric field E04CLSACC using a BigDecimal value.
  */
  public void setE04CLSACC(BigDecimal newvalue)
  {
    fieldE04CLSACC.setBigDecimal(newvalue);
  }

  /**
  * Return value of numeric field E04CLSACC as a BigDecimal.
  */
  public BigDecimal getBigDecimalE04CLSACC()
  {
    return fieldE04CLSACC.getBigDecimal();
  }

  /**
  * Set field E04BNKNUM using a String value.
  */
  public void setE04BNKNUM(String newvalue)
  {
    fieldE04BNKNUM.setString(newvalue);
  }

  /**
  * Get value of field E04BNKNUM as a String.
  */
  public String getE04BNKNUM()
  {
    return fieldE04BNKNUM.getString();
  }

  /**
  * Set field E04CCYCDE using a String value.
  */
  public void setE04CCYCDE(String newvalue)
  {
    fieldE04CCYCDE.setString(newvalue);
  }

  /**
  * Get value of field E04CCYCDE as a String.
  */
  public String getE04CCYCDE()
  {
    return fieldE04CCYCDE.getString();
  }

  /**
  * Set field E04ACCTYP using a String value.
  */
  public void setE04ACCTYP(String newvalue)
  {
    fieldE04ACCTYP.setString(newvalue);
  }

  /**
  * Get value of field E04ACCTYP as a String.
  */
  public String getE04ACCTYP()
  {
    return fieldE04ACCTYP.getString();
  }

  /**
  * Set field E04TYPDSC using a String value.
  */
  public void setE04TYPDSC(String newvalue)
  {
    fieldE04TYPDSC.setString(newvalue);
  }

  /**
  * Get value of field E04TYPDSC as a String.
  */
  public String getE04TYPDSC()
  {
    return fieldE04TYPDSC.getString();
  }

  /**
  * Set field E04PROCDE using a String value.
  */
  public void setE04PROCDE(String newvalue)
  {
    fieldE04PROCDE.setString(newvalue);
  }

  /**
  * Get value of field E04PROCDE as a String.
  */
  public String getE04PROCDE()
  {
    return fieldE04PROCDE.getString();
  }

  /**
  * Set field E04PRODSC using a String value.
  */
  public void setE04PRODSC(String newvalue)
  {
    fieldE04PRODSC.setString(newvalue);
  }

  /**
  * Get value of field E04PRODSC as a String.
  */
  public String getE04PRODSC()
  {
    return fieldE04PRODSC.getString();
  }

  /**
  * Set field E04NUMOPE using a String value.
  */
  public void setE04NUMOPE(String newvalue)
  {
    fieldE04NUMOPE.setString(newvalue);
  }

  /**
  * Get value of field E04NUMOPE as a String.
  */
  public String getE04NUMOPE()
  {
    return fieldE04NUMOPE.getString();
  }

  /**
  * Set numeric field E04NUMOPE using a BigDecimal value.
  */
  public void setE04NUMOPE(BigDecimal newvalue)
  {
    fieldE04NUMOPE.setBigDecimal(newvalue);
  }

  /**
  * Return value of numeric field E04NUMOPE as a BigDecimal.
  */
  public BigDecimal getBigDecimalE04NUMOPE()
  {
    return fieldE04NUMOPE.getBigDecimal();
  }

  /**
  * Set field E04BSEPRI using a String value.
  */
  public void setE04BSEPRI(String newvalue)
  {
    fieldE04BSEPRI.setString(newvalue);
  }

  /**
  * Get value of field E04BSEPRI as a String.
  */
  public String getE04BSEPRI()
  {
    return fieldE04BSEPRI.getString();
  }

  /**
  * Set numeric field E04BSEPRI using a BigDecimal value.
  */
  public void setE04BSEPRI(BigDecimal newvalue)
  {
    fieldE04BSEPRI.setBigDecimal(newvalue);
  }

  /**
  * Return value of numeric field E04BSEPRI as a BigDecimal.
  */
  public BigDecimal getBigDecimalE04BSEPRI()
  {
    return fieldE04BSEPRI.getBigDecimal();
  }

  /**
  * Set field E04BSEINT using a String value.
  */
  public void setE04BSEINT(String newvalue)
  {
    fieldE04BSEINT.setString(newvalue);
  }

  /**
  * Get value of field E04BSEINT as a String.
  */
  public String getE04BSEINT()
  {
    return fieldE04BSEINT.getString();
  }

  /**
  * Set numeric field E04BSEINT using a BigDecimal value.
  */
  public void setE04BSEINT(BigDecimal newvalue)
  {
    fieldE04BSEINT.setBigDecimal(newvalue);
  }

  /**
  * Return value of numeric field E04BSEINT as a BigDecimal.
  */
  public BigDecimal getBigDecimalE04BSEINT()
  {
    return fieldE04BSEINT.getBigDecimal();
  }

  /**
  * Set field E04BSEOTH using a String value.
  */
  public void setE04BSEOTH(String newvalue)
  {
    fieldE04BSEOTH.setString(newvalue);
  }

  /**
  * Get value of field E04BSEOTH as a String.
  */
  public String getE04BSEOTH()
  {
    return fieldE04BSEOTH.getString();
  }

  /**
  * Set numeric field E04BSEOTH using a BigDecimal value.
  */
  public void setE04BSEOTH(BigDecimal newvalue)
  {
    fieldE04BSEOTH.setBigDecimal(newvalue);
  }

  /**
  * Return value of numeric field E04BSEOTH as a BigDecimal.
  */
  public BigDecimal getBigDecimalE04BSEOTH()
  {
    return fieldE04BSEOTH.getBigDecimal();
  }

  /**
  * Set field E04BSETOT using a String value.
  */
  public void setE04BSETOT(String newvalue)
  {
    fieldE04BSETOT.setString(newvalue);
  }

  /**
  * Get value of field E04BSETOT as a String.
  */
  public String getE04BSETOT()
  {
    return fieldE04BSETOT.getString();
  }

  /**
  * Set numeric field E04BSETOT using a BigDecimal value.
  */
  public void setE04BSETOT(BigDecimal newvalue)
  {
    fieldE04BSETOT.setBigDecimal(newvalue);
  }

  /**
  * Return value of numeric field E04BSETOT as a BigDecimal.
  */
  public BigDecimal getBigDecimalE04BSETOT()
  {
    return fieldE04BSETOT.getBigDecimal();
  }

  /**
  * Set field E04INDOPE using a String value.
  */
  public void setE04INDOPE(String newvalue)
  {
    fieldE04INDOPE.setString(newvalue);
  }

  /**
  * Get value of field E04INDOPE as a String.
  */
  public String getE04INDOPE()
  {
    return fieldE04INDOPE.getString();
  }

}
