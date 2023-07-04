package datapro.eibs.beans;

import datapro.eibs.sockets.*;
import java.io.*;
import java.math.*;

import java.util.*;

/**
* Class generated by AS/400 CRTCLASS command from EDL015210 physical file definition.
* 
* File level identifier is 1070413191825.
* Record format level identifier is 3EECEBCA93E15.
*/

public class EDL015210Message extends MessageRecord
{
  final static String fldnames[] = {
                                     "H10USERID",
                                     "H10PROGRM",
                                     "H10TIMSYS",
                                     "H10SCRCOD",
                                     "H10OPECOD",
                                     "H10FLGMAS",
                                     "H10FLGWK1",
                                     "H10FLGWK2",
                                     "H10FLGWK3",
                                     "E10DEACUN",
                                     "E10CUSNA1",
                                     "E10DEAPRO",
                                     "E10DEACCY",
                                     "E10DEAACC",
                                     "E10DLASDM",
                                     "E10DLASDD",
                                     "E10DLASDY",
                                     "E10DLAMAM",
                                     "E10DLAMAD",
                                     "E10DLAMAY",
                                     "E10DLAMO1",
                                     "E10DLAMO2",
                                     "E10DLASTS",
                                     "E10DLACNL",
                                     "E10DLATYP",
                                     "E10DLAUSR",
                                     "E10DLALDM",
                                     "E10DLALDD",
                                     "E10DLALDY",
                                     "E10DLALDT"
                                   };
  final static String tnames[] = {
                                   "H10USERID",
                                   "H10PROGRM",
                                   "H10TIMSYS",
                                   "H10SCRCOD",
                                   "H10OPECOD",
                                   "H10FLGMAS",
                                   "H10FLGWK1",
                                   "H10FLGWK2",
                                   "H10FLGWK3",
                                   "E10DEACUN",
                                   "E10CUSNA1",
                                   "E10DEAPRO",
                                   "E10DEACCY",
                                   "E10DEAACC",
                                   "E10DLASDM",
                                   "E10DLASDD",
                                   "E10DLASDY",
                                   "E10DLAMAM",
                                   "E10DLAMAD",
                                   "E10DLAMAY",
                                   "E10DLAMO1",
                                   "E10DLAMO2",
                                   "E10DLASTS",
                                   "E10DLACNL",
                                   "E10DLATYP",
                                   "E10DLAUSR",
                                   "E10DLALDM",
                                   "E10DLALDD",
                                   "E10DLALDY",
                                   "E10DLALDT"
                                 };
  final static String fid = "1070413191825";
  final static String rid = "3EECEBCA93E15";
  final static String fmtname = "EDL015210";
  final int FIELDCOUNT = 30;
  private static Hashtable tlookup = new Hashtable();
  private CharacterField fieldH10USERID = null;
  private CharacterField fieldH10PROGRM = null;
  private CharacterField fieldH10TIMSYS = null;
  private CharacterField fieldH10SCRCOD = null;
  private CharacterField fieldH10OPECOD = null;
  private CharacterField fieldH10FLGMAS = null;
  private CharacterField fieldH10FLGWK1 = null;
  private CharacterField fieldH10FLGWK2 = null;
  private CharacterField fieldH10FLGWK3 = null;
  private DecimalField fieldE10DEACUN = null;
  private CharacterField fieldE10CUSNA1 = null;
  private CharacterField fieldE10DEAPRO = null;
  private CharacterField fieldE10DEACCY = null;
  private DecimalField fieldE10DEAACC = null;
  private DecimalField fieldE10DLASDM = null;
  private DecimalField fieldE10DLASDD = null;
  private DecimalField fieldE10DLASDY = null;
  private DecimalField fieldE10DLAMAM = null;
  private DecimalField fieldE10DLAMAD = null;
  private DecimalField fieldE10DLAMAY = null;
  private CharacterField fieldE10DLAMO1 = null;
  private CharacterField fieldE10DLAMO2 = null;
  private CharacterField fieldE10DLASTS = null;
  private CharacterField fieldE10DLACNL = null;
  private CharacterField fieldE10DLATYP = null;
  private CharacterField fieldE10DLAUSR = null;
  private DecimalField fieldE10DLALDM = null;
  private DecimalField fieldE10DLALDD = null;
  private DecimalField fieldE10DLALDY = null;
  private DecimalField fieldE10DLALDT = null;

  /**
  * Constructor for EDL015210Message.
  */
  public EDL015210Message()
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
    recordsize = 234;
    fileid = fid;
    recordid = rid;
    message = new byte[getByteLength()];
    formatname = fmtname;
    fieldnames = fldnames;
    tagnames = tnames;
    fields = new MessageField[FIELDCOUNT];

    fields[0] = fieldH10USERID =
    new CharacterField(message, HEADERSIZE + 0, 10, "H10USERID");
    fields[1] = fieldH10PROGRM =
    new CharacterField(message, HEADERSIZE + 10, 10, "H10PROGRM");
    fields[2] = fieldH10TIMSYS =
    new CharacterField(message, HEADERSIZE + 20, 12, "H10TIMSYS");
    fields[3] = fieldH10SCRCOD =
    new CharacterField(message, HEADERSIZE + 32, 2, "H10SCRCOD");
    fields[4] = fieldH10OPECOD =
    new CharacterField(message, HEADERSIZE + 34, 4, "H10OPECOD");
    fields[5] = fieldH10FLGMAS =
    new CharacterField(message, HEADERSIZE + 38, 1, "H10FLGMAS");
    fields[6] = fieldH10FLGWK1 =
    new CharacterField(message, HEADERSIZE + 39, 1, "H10FLGWK1");
    fields[7] = fieldH10FLGWK2 =
    new CharacterField(message, HEADERSIZE + 40, 1, "H10FLGWK2");
    fields[8] = fieldH10FLGWK3 =
    new CharacterField(message, HEADERSIZE + 41, 1, "H10FLGWK3");
    fields[9] = fieldE10DEACUN =
    new DecimalField(message, HEADERSIZE + 42, 10, 0, "E10DEACUN");
    fields[10] = fieldE10CUSNA1 =
    new CharacterField(message, HEADERSIZE + 52, 45, "E10CUSNA1");
    fields[11] = fieldE10DEAPRO =
    new CharacterField(message, HEADERSIZE + 97, 4, "E10DEAPRO");
    fields[12] = fieldE10DEACCY =
    new CharacterField(message, HEADERSIZE + 101, 3, "E10DEACCY");
    fields[13] = fieldE10DEAACC =
    new DecimalField(message, HEADERSIZE + 104, 13, 0, "E10DEAACC");
    fields[14] = fieldE10DLASDM =
    new DecimalField(message, HEADERSIZE + 117, 3, 0, "E10DLASDM");
    fields[15] = fieldE10DLASDD =
    new DecimalField(message, HEADERSIZE + 120, 3, 0, "E10DLASDD");
    fields[16] = fieldE10DLASDY =
    new DecimalField(message, HEADERSIZE + 123, 3, 0, "E10DLASDY");
    fields[17] = fieldE10DLAMAM =
    new DecimalField(message, HEADERSIZE + 126, 3, 0, "E10DLAMAM");
    fields[18] = fieldE10DLAMAD =
    new DecimalField(message, HEADERSIZE + 129, 3, 0, "E10DLAMAD");
    fields[19] = fieldE10DLAMAY =
    new DecimalField(message, HEADERSIZE + 132, 3, 0, "E10DLAMAY");
    fields[20] = fieldE10DLAMO1 =
    new CharacterField(message, HEADERSIZE + 135, 35, "E10DLAMO1");
    fields[21] = fieldE10DLAMO2 =
    new CharacterField(message, HEADERSIZE + 170, 35, "E10DLAMO2");
    fields[22] = fieldE10DLASTS =
    new CharacterField(message, HEADERSIZE + 205, 1, "E10DLASTS");
    fields[23] = fieldE10DLACNL =
    new CharacterField(message, HEADERSIZE + 206, 1, "E10DLACNL");
    fields[24] = fieldE10DLATYP =
    new CharacterField(message, HEADERSIZE + 207, 1, "E10DLATYP");
    fields[25] = fieldE10DLAUSR =
    new CharacterField(message, HEADERSIZE + 208, 10, "E10DLAUSR");
    fields[26] = fieldE10DLALDM =
    new DecimalField(message, HEADERSIZE + 218, 3, 0, "E10DLALDM");
    fields[27] = fieldE10DLALDD =
    new DecimalField(message, HEADERSIZE + 221, 3, 0, "E10DLALDD");
    fields[28] = fieldE10DLALDY =
    new DecimalField(message, HEADERSIZE + 224, 3, 0, "E10DLALDY");
    fields[29] = fieldE10DLALDT =
    new DecimalField(message, HEADERSIZE + 227, 7, 0, "E10DLALDT");

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
  * Set field H10USERID using a String value.
  */
  public void setH10USERID(String newvalue)
  {
    fieldH10USERID.setString(newvalue);
  }

  /**
  * Get value of field H10USERID as a String.
  */
  public String getH10USERID()
  {
    return fieldH10USERID.getString();
  }

  /**
  * Set field H10PROGRM using a String value.
  */
  public void setH10PROGRM(String newvalue)
  {
    fieldH10PROGRM.setString(newvalue);
  }

  /**
  * Get value of field H10PROGRM as a String.
  */
  public String getH10PROGRM()
  {
    return fieldH10PROGRM.getString();
  }

  /**
  * Set field H10TIMSYS using a String value.
  */
  public void setH10TIMSYS(String newvalue)
  {
    fieldH10TIMSYS.setString(newvalue);
  }

  /**
  * Get value of field H10TIMSYS as a String.
  */
  public String getH10TIMSYS()
  {
    return fieldH10TIMSYS.getString();
  }

  /**
  * Set field H10SCRCOD using a String value.
  */
  public void setH10SCRCOD(String newvalue)
  {
    fieldH10SCRCOD.setString(newvalue);
  }

  /**
  * Get value of field H10SCRCOD as a String.
  */
  public String getH10SCRCOD()
  {
    return fieldH10SCRCOD.getString();
  }

  /**
  * Set field H10OPECOD using a String value.
  */
  public void setH10OPECOD(String newvalue)
  {
    fieldH10OPECOD.setString(newvalue);
  }

  /**
  * Get value of field H10OPECOD as a String.
  */
  public String getH10OPECOD()
  {
    return fieldH10OPECOD.getString();
  }

  /**
  * Set field H10FLGMAS using a String value.
  */
  public void setH10FLGMAS(String newvalue)
  {
    fieldH10FLGMAS.setString(newvalue);
  }

  /**
  * Get value of field H10FLGMAS as a String.
  */
  public String getH10FLGMAS()
  {
    return fieldH10FLGMAS.getString();
  }

  /**
  * Set field H10FLGWK1 using a String value.
  */
  public void setH10FLGWK1(String newvalue)
  {
    fieldH10FLGWK1.setString(newvalue);
  }

  /**
  * Get value of field H10FLGWK1 as a String.
  */
  public String getH10FLGWK1()
  {
    return fieldH10FLGWK1.getString();
  }

  /**
  * Set field H10FLGWK2 using a String value.
  */
  public void setH10FLGWK2(String newvalue)
  {
    fieldH10FLGWK2.setString(newvalue);
  }

  /**
  * Get value of field H10FLGWK2 as a String.
  */
  public String getH10FLGWK2()
  {
    return fieldH10FLGWK2.getString();
  }

  /**
  * Set field H10FLGWK3 using a String value.
  */
  public void setH10FLGWK3(String newvalue)
  {
    fieldH10FLGWK3.setString(newvalue);
  }

  /**
  * Get value of field H10FLGWK3 as a String.
  */
  public String getH10FLGWK3()
  {
    return fieldH10FLGWK3.getString();
  }

  /**
  * Set field E10DEACUN using a String value.
  */
  public void setE10DEACUN(String newvalue)
  {
    fieldE10DEACUN.setString(newvalue);
  }

  /**
  * Get value of field E10DEACUN as a String.
  */
  public String getE10DEACUN()
  {
    return fieldE10DEACUN.getString();
  }

  /**
  * Set numeric field E10DEACUN using a BigDecimal value.
  */
  public void setE10DEACUN(BigDecimal newvalue)
  {
    fieldE10DEACUN.setBigDecimal(newvalue);
  }

  /**
  * Return value of numeric field E10DEACUN as a BigDecimal.
  */
  public BigDecimal getBigDecimalE10DEACUN()
  {
    return fieldE10DEACUN.getBigDecimal();
  }

  /**
  * Set field E10CUSNA1 using a String value.
  */
  public void setE10CUSNA1(String newvalue)
  {
    fieldE10CUSNA1.setString(newvalue);
  }

  /**
  * Get value of field E10CUSNA1 as a String.
  */
  public String getE10CUSNA1()
  {
    return fieldE10CUSNA1.getString();
  }

  /**
  * Set field E10DEAPRO using a String value.
  */
  public void setE10DEAPRO(String newvalue)
  {
    fieldE10DEAPRO.setString(newvalue);
  }

  /**
  * Get value of field E10DEAPRO as a String.
  */
  public String getE10DEAPRO()
  {
    return fieldE10DEAPRO.getString();
  }

  /**
  * Set field E10DEACCY using a String value.
  */
  public void setE10DEACCY(String newvalue)
  {
    fieldE10DEACCY.setString(newvalue);
  }

  /**
  * Get value of field E10DEACCY as a String.
  */
  public String getE10DEACCY()
  {
    return fieldE10DEACCY.getString();
  }

  /**
  * Set field E10DEAACC using a String value.
  */
  public void setE10DEAACC(String newvalue)
  {
    fieldE10DEAACC.setString(newvalue);
  }

  /**
  * Get value of field E10DEAACC as a String.
  */
  public String getE10DEAACC()
  {
    return fieldE10DEAACC.getString();
  }

  /**
  * Set numeric field E10DEAACC using a BigDecimal value.
  */
  public void setE10DEAACC(BigDecimal newvalue)
  {
    fieldE10DEAACC.setBigDecimal(newvalue);
  }

  /**
  * Return value of numeric field E10DEAACC as a BigDecimal.
  */
  public BigDecimal getBigDecimalE10DEAACC()
  {
    return fieldE10DEAACC.getBigDecimal();
  }

  /**
  * Set field E10DLASDM using a String value.
  */
  public void setE10DLASDM(String newvalue)
  {
    fieldE10DLASDM.setString(newvalue);
  }

  /**
  * Get value of field E10DLASDM as a String.
  */
  public String getE10DLASDM()
  {
    return fieldE10DLASDM.getString();
  }

  /**
  * Set numeric field E10DLASDM using a BigDecimal value.
  */
  public void setE10DLASDM(BigDecimal newvalue)
  {
    fieldE10DLASDM.setBigDecimal(newvalue);
  }

  /**
  * Return value of numeric field E10DLASDM as a BigDecimal.
  */
  public BigDecimal getBigDecimalE10DLASDM()
  {
    return fieldE10DLASDM.getBigDecimal();
  }

  /**
  * Set field E10DLASDD using a String value.
  */
  public void setE10DLASDD(String newvalue)
  {
    fieldE10DLASDD.setString(newvalue);
  }

  /**
  * Get value of field E10DLASDD as a String.
  */
  public String getE10DLASDD()
  {
    return fieldE10DLASDD.getString();
  }

  /**
  * Set numeric field E10DLASDD using a BigDecimal value.
  */
  public void setE10DLASDD(BigDecimal newvalue)
  {
    fieldE10DLASDD.setBigDecimal(newvalue);
  }

  /**
  * Return value of numeric field E10DLASDD as a BigDecimal.
  */
  public BigDecimal getBigDecimalE10DLASDD()
  {
    return fieldE10DLASDD.getBigDecimal();
  }

  /**
  * Set field E10DLASDY using a String value.
  */
  public void setE10DLASDY(String newvalue)
  {
    fieldE10DLASDY.setString(newvalue);
  }

  /**
  * Get value of field E10DLASDY as a String.
  */
  public String getE10DLASDY()
  {
    return fieldE10DLASDY.getString();
  }

  /**
  * Set numeric field E10DLASDY using a BigDecimal value.
  */
  public void setE10DLASDY(BigDecimal newvalue)
  {
    fieldE10DLASDY.setBigDecimal(newvalue);
  }

  /**
  * Return value of numeric field E10DLASDY as a BigDecimal.
  */
  public BigDecimal getBigDecimalE10DLASDY()
  {
    return fieldE10DLASDY.getBigDecimal();
  }

  /**
  * Set field E10DLAMAM using a String value.
  */
  public void setE10DLAMAM(String newvalue)
  {
    fieldE10DLAMAM.setString(newvalue);
  }

  /**
  * Get value of field E10DLAMAM as a String.
  */
  public String getE10DLAMAM()
  {
    return fieldE10DLAMAM.getString();
  }

  /**
  * Set numeric field E10DLAMAM using a BigDecimal value.
  */
  public void setE10DLAMAM(BigDecimal newvalue)
  {
    fieldE10DLAMAM.setBigDecimal(newvalue);
  }

  /**
  * Return value of numeric field E10DLAMAM as a BigDecimal.
  */
  public BigDecimal getBigDecimalE10DLAMAM()
  {
    return fieldE10DLAMAM.getBigDecimal();
  }

  /**
  * Set field E10DLAMAD using a String value.
  */
  public void setE10DLAMAD(String newvalue)
  {
    fieldE10DLAMAD.setString(newvalue);
  }

  /**
  * Get value of field E10DLAMAD as a String.
  */
  public String getE10DLAMAD()
  {
    return fieldE10DLAMAD.getString();
  }

  /**
  * Set numeric field E10DLAMAD using a BigDecimal value.
  */
  public void setE10DLAMAD(BigDecimal newvalue)
  {
    fieldE10DLAMAD.setBigDecimal(newvalue);
  }

  /**
  * Return value of numeric field E10DLAMAD as a BigDecimal.
  */
  public BigDecimal getBigDecimalE10DLAMAD()
  {
    return fieldE10DLAMAD.getBigDecimal();
  }

  /**
  * Set field E10DLAMAY using a String value.
  */
  public void setE10DLAMAY(String newvalue)
  {
    fieldE10DLAMAY.setString(newvalue);
  }

  /**
  * Get value of field E10DLAMAY as a String.
  */
  public String getE10DLAMAY()
  {
    return fieldE10DLAMAY.getString();
  }

  /**
  * Set numeric field E10DLAMAY using a BigDecimal value.
  */
  public void setE10DLAMAY(BigDecimal newvalue)
  {
    fieldE10DLAMAY.setBigDecimal(newvalue);
  }

  /**
  * Return value of numeric field E10DLAMAY as a BigDecimal.
  */
  public BigDecimal getBigDecimalE10DLAMAY()
  {
    return fieldE10DLAMAY.getBigDecimal();
  }

  /**
  * Set field E10DLAMO1 using a String value.
  */
  public void setE10DLAMO1(String newvalue)
  {
    fieldE10DLAMO1.setString(newvalue);
  }

  /**
  * Get value of field E10DLAMO1 as a String.
  */
  public String getE10DLAMO1()
  {
    return fieldE10DLAMO1.getString();
  }

  /**
  * Set field E10DLAMO2 using a String value.
  */
  public void setE10DLAMO2(String newvalue)
  {
    fieldE10DLAMO2.setString(newvalue);
  }

  /**
  * Get value of field E10DLAMO2 as a String.
  */
  public String getE10DLAMO2()
  {
    return fieldE10DLAMO2.getString();
  }

  /**
  * Set field E10DLASTS using a String value.
  */
  public void setE10DLASTS(String newvalue)
  {
    fieldE10DLASTS.setString(newvalue);
  }

  /**
  * Get value of field E10DLASTS as a String.
  */
  public String getE10DLASTS()
  {
    return fieldE10DLASTS.getString();
  }

  /**
  * Set field E10DLACNL using a String value.
  */
  public void setE10DLACNL(String newvalue)
  {
    fieldE10DLACNL.setString(newvalue);
  }

  /**
  * Get value of field E10DLACNL as a String.
  */
  public String getE10DLACNL()
  {
    return fieldE10DLACNL.getString();
  }

  /**
  * Set field E10DLATYP using a String value.
  */
  public void setE10DLATYP(String newvalue)
  {
    fieldE10DLATYP.setString(newvalue);
  }

  /**
  * Get value of field E10DLATYP as a String.
  */
  public String getE10DLATYP()
  {
    return fieldE10DLATYP.getString();
  }

  /**
  * Set field E10DLAUSR using a String value.
  */
  public void setE10DLAUSR(String newvalue)
  {
    fieldE10DLAUSR.setString(newvalue);
  }

  /**
  * Get value of field E10DLAUSR as a String.
  */
  public String getE10DLAUSR()
  {
    return fieldE10DLAUSR.getString();
  }

  /**
  * Set field E10DLALDM using a String value.
  */
  public void setE10DLALDM(String newvalue)
  {
    fieldE10DLALDM.setString(newvalue);
  }

  /**
  * Get value of field E10DLALDM as a String.
  */
  public String getE10DLALDM()
  {
    return fieldE10DLALDM.getString();
  }

  /**
  * Set numeric field E10DLALDM using a BigDecimal value.
  */
  public void setE10DLALDM(BigDecimal newvalue)
  {
    fieldE10DLALDM.setBigDecimal(newvalue);
  }

  /**
  * Return value of numeric field E10DLALDM as a BigDecimal.
  */
  public BigDecimal getBigDecimalE10DLALDM()
  {
    return fieldE10DLALDM.getBigDecimal();
  }

  /**
  * Set field E10DLALDD using a String value.
  */
  public void setE10DLALDD(String newvalue)
  {
    fieldE10DLALDD.setString(newvalue);
  }

  /**
  * Get value of field E10DLALDD as a String.
  */
  public String getE10DLALDD()
  {
    return fieldE10DLALDD.getString();
  }

  /**
  * Set numeric field E10DLALDD using a BigDecimal value.
  */
  public void setE10DLALDD(BigDecimal newvalue)
  {
    fieldE10DLALDD.setBigDecimal(newvalue);
  }

  /**
  * Return value of numeric field E10DLALDD as a BigDecimal.
  */
  public BigDecimal getBigDecimalE10DLALDD()
  {
    return fieldE10DLALDD.getBigDecimal();
  }

  /**
  * Set field E10DLALDY using a String value.
  */
  public void setE10DLALDY(String newvalue)
  {
    fieldE10DLALDY.setString(newvalue);
  }

  /**
  * Get value of field E10DLALDY as a String.
  */
  public String getE10DLALDY()
  {
    return fieldE10DLALDY.getString();
  }

  /**
  * Set numeric field E10DLALDY using a BigDecimal value.
  */
  public void setE10DLALDY(BigDecimal newvalue)
  {
    fieldE10DLALDY.setBigDecimal(newvalue);
  }

  /**
  * Return value of numeric field E10DLALDY as a BigDecimal.
  */
  public BigDecimal getBigDecimalE10DLALDY()
  {
    return fieldE10DLALDY.getBigDecimal();
  }

  /**
  * Set field E10DLALDT using a String value.
  */
  public void setE10DLALDT(String newvalue)
  {
    fieldE10DLALDT.setString(newvalue);
  }

  /**
  * Get value of field E10DLALDT as a String.
  */
  public String getE10DLALDT()
  {
    return fieldE10DLALDT.getString();
  }

  /**
  * Set numeric field E10DLALDT using a BigDecimal value.
  */
  public void setE10DLALDT(BigDecimal newvalue)
  {
    fieldE10DLALDT.setBigDecimal(newvalue);
  }

  /**
  * Return value of numeric field E10DLALDT as a BigDecimal.
  */
  public BigDecimal getBigDecimalE10DLALDT()
  {
    return fieldE10DLALDT.getBigDecimal();
  }

}