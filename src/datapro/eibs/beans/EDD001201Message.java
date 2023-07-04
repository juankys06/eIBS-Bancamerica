package datapro.eibs.beans;

import datapro.eibs.sockets.*;
import java.io.*;
import java.math.*;

import java.util.*;

/**
* Class generated by AS/400 CRTCLASS command from EDD001201 physical file definition.
* 
* File level identifier is 1040628192503.
* Record format level identifier is 494D1C5359B0E.
*/

public class EDD001201Message extends MessageRecord
{
  final static String fldnames[] = {
                                     "H12USERID",
                                     "H12PROGRM",
                                     "H12TIMSYS",
                                     "H12SCRCOD",
                                     "H12OPECOD",
                                     "H12FLGMAS",
                                     "H12FLGWK1",
                                     "H12FLGWK2",
                                     "H12FLGWK3",
                                     "E12ACMACC",
                                     "E12ACMPRO",
                                     "E12ACMCUN",
                                     "E12CUSNA1",
                                     "E12ACMACD",
                                     "E12ACMATY",
                                     "E12LNKTYP",
                                     "E12LNKACC",
                                     "E12LNKATY",
                                     "E12LNKPRO",
                                     "E12LNKAMT",
                                     "E12LNKCUN",
                                     "E12LNKNA1",
                                     "E12LNKOP1",
                                     "E12LNKOP2",
                                     "E12LNKOP3",
                                     "E12LNKEX1",
                                     "E12LNKEX2",
                                     "E12LNKEX3"
                                   };
  final static String tnames[] = {
                                   "H12USERID",
                                   "H12PROGRM",
                                   "H12TIMSYS",
                                   "H12SCRCOD",
                                   "H12OPECOD",
                                   "H12FLGMAS",
                                   "H12FLGWK1",
                                   "H12FLGWK2",
                                   "H12FLGWK3",
                                   "E12ACMACC",
                                   "E12ACMPRO",
                                   "E12ACMCUN",
                                   "E12CUSNA1",
                                   "E12ACMACD",
                                   "E12ACMATY",
                                   "E12LNKTYP",
                                   "E12LNKACC",
                                   "E12LNKATY",
                                   "E12LNKPRO",
                                   "E12LNKAMT",
                                   "E12LNKCUN",
                                   "E12LNKNA1",
                                   "E12LNKOP1",
                                   "E12LNKOP2",
                                   "E12LNKOP3",
                                   "E12LNKEX1",
                                   "E12LNKEX2",
                                   "E12LNKEX3"
                                 };
  final static String fid = "1040628192503";
  final static String rid = "494D1C5359B0E";
  final static String fmtname = "EDD001201";
  final int FIELDCOUNT = 28;
  private static Hashtable tlookup = new Hashtable();
  private CharacterField fieldH12USERID = null;
  private CharacterField fieldH12PROGRM = null;
  private CharacterField fieldH12TIMSYS = null;
  private CharacterField fieldH12SCRCOD = null;
  private CharacterField fieldH12OPECOD = null;
  private CharacterField fieldH12FLGMAS = null;
  private CharacterField fieldH12FLGWK1 = null;
  private CharacterField fieldH12FLGWK2 = null;
  private CharacterField fieldH12FLGWK3 = null;
  private DecimalField fieldE12ACMACC = null;
  private CharacterField fieldE12ACMPRO = null;
  private DecimalField fieldE12ACMCUN = null;
  private CharacterField fieldE12CUSNA1 = null;
  private CharacterField fieldE12ACMACD = null;
  private CharacterField fieldE12ACMATY = null;
  private CharacterField fieldE12LNKTYP = null;
  private DecimalField fieldE12LNKACC = null;
  private CharacterField fieldE12LNKATY = null;
  private CharacterField fieldE12LNKPRO = null;
  private DecimalField fieldE12LNKAMT = null;
  private DecimalField fieldE12LNKCUN = null;
  private CharacterField fieldE12LNKNA1 = null;
  private DecimalField fieldE12LNKOP1 = null;
  private DecimalField fieldE12LNKOP2 = null;
  private DecimalField fieldE12LNKOP3 = null;
  private DecimalField fieldE12LNKEX1 = null;
  private DecimalField fieldE12LNKEX2 = null;
  private DecimalField fieldE12LNKEX3 = null;

  /**
  * Constructor for EDD001201Message.
  */
  public EDD001201Message()
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
    recordsize = 230;
    fileid = fid;
    recordid = rid;
    message = new byte[getByteLength()];
    formatname = fmtname;
    fieldnames = fldnames;
    tagnames = tnames;
    fields = new MessageField[FIELDCOUNT];

    fields[0] = fieldH12USERID =
    new CharacterField(message, HEADERSIZE + 0, 10, "H12USERID");
    fields[1] = fieldH12PROGRM =
    new CharacterField(message, HEADERSIZE + 10, 10, "H12PROGRM");
    fields[2] = fieldH12TIMSYS =
    new CharacterField(message, HEADERSIZE + 20, 12, "H12TIMSYS");
    fields[3] = fieldH12SCRCOD =
    new CharacterField(message, HEADERSIZE + 32, 2, "H12SCRCOD");
    fields[4] = fieldH12OPECOD =
    new CharacterField(message, HEADERSIZE + 34, 4, "H12OPECOD");
    fields[5] = fieldH12FLGMAS =
    new CharacterField(message, HEADERSIZE + 38, 1, "H12FLGMAS");
    fields[6] = fieldH12FLGWK1 =
    new CharacterField(message, HEADERSIZE + 39, 1, "H12FLGWK1");
    fields[7] = fieldH12FLGWK2 =
    new CharacterField(message, HEADERSIZE + 40, 1, "H12FLGWK2");
    fields[8] = fieldH12FLGWK3 =
    new CharacterField(message, HEADERSIZE + 41, 1, "H12FLGWK3");
    fields[9] = fieldE12ACMACC =
    new DecimalField(message, HEADERSIZE + 42, 13, 0, "E12ACMACC");
    fields[10] = fieldE12ACMPRO =
    new CharacterField(message, HEADERSIZE + 55, 4, "E12ACMPRO");
    fields[11] = fieldE12ACMCUN =
    new DecimalField(message, HEADERSIZE + 59, 10, 0, "E12ACMCUN");
    fields[12] = fieldE12CUSNA1 =
    new CharacterField(message, HEADERSIZE + 69, 45, "E12CUSNA1");
    fields[13] = fieldE12ACMACD =
    new CharacterField(message, HEADERSIZE + 114, 2, "E12ACMACD");
    fields[14] = fieldE12ACMATY =
    new CharacterField(message, HEADERSIZE + 116, 3, "E12ACMATY");
    fields[15] = fieldE12LNKTYP =
    new CharacterField(message, HEADERSIZE + 119, 1, "E12LNKTYP");
    fields[16] = fieldE12LNKACC =
    new DecimalField(message, HEADERSIZE + 120, 13, 0, "E12LNKACC");
    fields[17] = fieldE12LNKATY =
    new CharacterField(message, HEADERSIZE + 133, 3, "E12LNKATY");
    fields[18] = fieldE12LNKPRO =
    new CharacterField(message, HEADERSIZE + 136, 4, "E12LNKPRO");
    fields[19] = fieldE12LNKAMT =
    new DecimalField(message, HEADERSIZE + 140, 17, 2, "E12LNKAMT");
    fields[20] = fieldE12LNKCUN =
    new DecimalField(message, HEADERSIZE + 157, 10, 0, "E12LNKCUN");
    fields[21] = fieldE12LNKNA1 =
    new CharacterField(message, HEADERSIZE + 167, 45, "E12LNKNA1");
    fields[22] = fieldE12LNKOP1 =
    new DecimalField(message, HEADERSIZE + 212, 3, 0, "E12LNKOP1");
    fields[23] = fieldE12LNKOP2 =
    new DecimalField(message, HEADERSIZE + 215, 3, 0, "E12LNKOP2");
    fields[24] = fieldE12LNKOP3 =
    new DecimalField(message, HEADERSIZE + 218, 3, 0, "E12LNKOP3");
    fields[25] = fieldE12LNKEX1 =
    new DecimalField(message, HEADERSIZE + 221, 3, 0, "E12LNKEX1");
    fields[26] = fieldE12LNKEX2 =
    new DecimalField(message, HEADERSIZE + 224, 3, 0, "E12LNKEX2");
    fields[27] = fieldE12LNKEX3 =
    new DecimalField(message, HEADERSIZE + 227, 3, 0, "E12LNKEX3");

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
  * Set field H12USERID using a String value.
  */
  public void setH12USERID(String newvalue)
  {
    fieldH12USERID.setString(newvalue);
  }

  /**
  * Get value of field H12USERID as a String.
  */
  public String getH12USERID()
  {
    return fieldH12USERID.getString();
  }

  /**
  * Set field H12PROGRM using a String value.
  */
  public void setH12PROGRM(String newvalue)
  {
    fieldH12PROGRM.setString(newvalue);
  }

  /**
  * Get value of field H12PROGRM as a String.
  */
  public String getH12PROGRM()
  {
    return fieldH12PROGRM.getString();
  }

  /**
  * Set field H12TIMSYS using a String value.
  */
  public void setH12TIMSYS(String newvalue)
  {
    fieldH12TIMSYS.setString(newvalue);
  }

  /**
  * Get value of field H12TIMSYS as a String.
  */
  public String getH12TIMSYS()
  {
    return fieldH12TIMSYS.getString();
  }

  /**
  * Set field H12SCRCOD using a String value.
  */
  public void setH12SCRCOD(String newvalue)
  {
    fieldH12SCRCOD.setString(newvalue);
  }

  /**
  * Get value of field H12SCRCOD as a String.
  */
  public String getH12SCRCOD()
  {
    return fieldH12SCRCOD.getString();
  }

  /**
  * Set field H12OPECOD using a String value.
  */
  public void setH12OPECOD(String newvalue)
  {
    fieldH12OPECOD.setString(newvalue);
  }

  /**
  * Get value of field H12OPECOD as a String.
  */
  public String getH12OPECOD()
  {
    return fieldH12OPECOD.getString();
  }

  /**
  * Set field H12FLGMAS using a String value.
  */
  public void setH12FLGMAS(String newvalue)
  {
    fieldH12FLGMAS.setString(newvalue);
  }

  /**
  * Get value of field H12FLGMAS as a String.
  */
  public String getH12FLGMAS()
  {
    return fieldH12FLGMAS.getString();
  }

  /**
  * Set field H12FLGWK1 using a String value.
  */
  public void setH12FLGWK1(String newvalue)
  {
    fieldH12FLGWK1.setString(newvalue);
  }

  /**
  * Get value of field H12FLGWK1 as a String.
  */
  public String getH12FLGWK1()
  {
    return fieldH12FLGWK1.getString();
  }

  /**
  * Set field H12FLGWK2 using a String value.
  */
  public void setH12FLGWK2(String newvalue)
  {
    fieldH12FLGWK2.setString(newvalue);
  }

  /**
  * Get value of field H12FLGWK2 as a String.
  */
  public String getH12FLGWK2()
  {
    return fieldH12FLGWK2.getString();
  }

  /**
  * Set field H12FLGWK3 using a String value.
  */
  public void setH12FLGWK3(String newvalue)
  {
    fieldH12FLGWK3.setString(newvalue);
  }

  /**
  * Get value of field H12FLGWK3 as a String.
  */
  public String getH12FLGWK3()
  {
    return fieldH12FLGWK3.getString();
  }

  /**
  * Set field E12ACMACC using a String value.
  */
  public void setE12ACMACC(String newvalue)
  {
    fieldE12ACMACC.setString(newvalue);
  }

  /**
  * Get value of field E12ACMACC as a String.
  */
  public String getE12ACMACC()
  {
    return fieldE12ACMACC.getString();
  }

  /**
  * Set numeric field E12ACMACC using a BigDecimal value.
  */
  public void setE12ACMACC(BigDecimal newvalue)
  {
    fieldE12ACMACC.setBigDecimal(newvalue);
  }

  /**
  * Return value of numeric field E12ACMACC as a BigDecimal.
  */
  public BigDecimal getBigDecimalE12ACMACC()
  {
    return fieldE12ACMACC.getBigDecimal();
  }

  /**
  * Set field E12ACMPRO using a String value.
  */
  public void setE12ACMPRO(String newvalue)
  {
    fieldE12ACMPRO.setString(newvalue);
  }

  /**
  * Get value of field E12ACMPRO as a String.
  */
  public String getE12ACMPRO()
  {
    return fieldE12ACMPRO.getString();
  }

  /**
  * Set field E12ACMCUN using a String value.
  */
  public void setE12ACMCUN(String newvalue)
  {
    fieldE12ACMCUN.setString(newvalue);
  }

  /**
  * Get value of field E12ACMCUN as a String.
  */
  public String getE12ACMCUN()
  {
    return fieldE12ACMCUN.getString();
  }

  /**
  * Set numeric field E12ACMCUN using a BigDecimal value.
  */
  public void setE12ACMCUN(BigDecimal newvalue)
  {
    fieldE12ACMCUN.setBigDecimal(newvalue);
  }

  /**
  * Return value of numeric field E12ACMCUN as a BigDecimal.
  */
  public BigDecimal getBigDecimalE12ACMCUN()
  {
    return fieldE12ACMCUN.getBigDecimal();
  }

  /**
  * Set field E12CUSNA1 using a String value.
  */
  public void setE12CUSNA1(String newvalue)
  {
    fieldE12CUSNA1.setString(newvalue);
  }

  /**
  * Get value of field E12CUSNA1 as a String.
  */
  public String getE12CUSNA1()
  {
    return fieldE12CUSNA1.getString();
  }

  /**
  * Set field E12ACMACD using a String value.
  */
  public void setE12ACMACD(String newvalue)
  {
    fieldE12ACMACD.setString(newvalue);
  }

  /**
  * Get value of field E12ACMACD as a String.
  */
  public String getE12ACMACD()
  {
    return fieldE12ACMACD.getString();
  }

  /**
  * Set field E12ACMATY using a String value.
  */
  public void setE12ACMATY(String newvalue)
  {
    fieldE12ACMATY.setString(newvalue);
  }

  /**
  * Get value of field E12ACMATY as a String.
  */
  public String getE12ACMATY()
  {
    return fieldE12ACMATY.getString();
  }

  /**
  * Set field E12LNKTYP using a String value.
  */
  public void setE12LNKTYP(String newvalue)
  {
    fieldE12LNKTYP.setString(newvalue);
  }

  /**
  * Get value of field E12LNKTYP as a String.
  */
  public String getE12LNKTYP()
  {
    return fieldE12LNKTYP.getString();
  }

  /**
  * Set field E12LNKACC using a String value.
  */
  public void setE12LNKACC(String newvalue)
  {
    fieldE12LNKACC.setString(newvalue);
  }

  /**
  * Get value of field E12LNKACC as a String.
  */
  public String getE12LNKACC()
  {
    return fieldE12LNKACC.getString();
  }

  /**
  * Set numeric field E12LNKACC using a BigDecimal value.
  */
  public void setE12LNKACC(BigDecimal newvalue)
  {
    fieldE12LNKACC.setBigDecimal(newvalue);
  }

  /**
  * Return value of numeric field E12LNKACC as a BigDecimal.
  */
  public BigDecimal getBigDecimalE12LNKACC()
  {
    return fieldE12LNKACC.getBigDecimal();
  }

  /**
  * Set field E12LNKATY using a String value.
  */
  public void setE12LNKATY(String newvalue)
  {
    fieldE12LNKATY.setString(newvalue);
  }

  /**
  * Get value of field E12LNKATY as a String.
  */
  public String getE12LNKATY()
  {
    return fieldE12LNKATY.getString();
  }

  /**
  * Set field E12LNKPRO using a String value.
  */
  public void setE12LNKPRO(String newvalue)
  {
    fieldE12LNKPRO.setString(newvalue);
  }

  /**
  * Get value of field E12LNKPRO as a String.
  */
  public String getE12LNKPRO()
  {
    return fieldE12LNKPRO.getString();
  }

  /**
  * Set field E12LNKAMT using a String value.
  */
  public void setE12LNKAMT(String newvalue)
  {
    fieldE12LNKAMT.setString(newvalue);
  }

  /**
  * Get value of field E12LNKAMT as a String.
  */
  public String getE12LNKAMT()
  {
    return fieldE12LNKAMT.getString();
  }

  /**
  * Set numeric field E12LNKAMT using a BigDecimal value.
  */
  public void setE12LNKAMT(BigDecimal newvalue)
  {
    fieldE12LNKAMT.setBigDecimal(newvalue);
  }

  /**
  * Return value of numeric field E12LNKAMT as a BigDecimal.
  */
  public BigDecimal getBigDecimalE12LNKAMT()
  {
    return fieldE12LNKAMT.getBigDecimal();
  }

  /**
  * Set field E12LNKCUN using a String value.
  */
  public void setE12LNKCUN(String newvalue)
  {
    fieldE12LNKCUN.setString(newvalue);
  }

  /**
  * Get value of field E12LNKCUN as a String.
  */
  public String getE12LNKCUN()
  {
    return fieldE12LNKCUN.getString();
  }

  /**
  * Set numeric field E12LNKCUN using a BigDecimal value.
  */
  public void setE12LNKCUN(BigDecimal newvalue)
  {
    fieldE12LNKCUN.setBigDecimal(newvalue);
  }

  /**
  * Return value of numeric field E12LNKCUN as a BigDecimal.
  */
  public BigDecimal getBigDecimalE12LNKCUN()
  {
    return fieldE12LNKCUN.getBigDecimal();
  }

  /**
  * Set field E12LNKNA1 using a String value.
  */
  public void setE12LNKNA1(String newvalue)
  {
    fieldE12LNKNA1.setString(newvalue);
  }

  /**
  * Get value of field E12LNKNA1 as a String.
  */
  public String getE12LNKNA1()
  {
    return fieldE12LNKNA1.getString();
  }

  /**
  * Set field E12LNKOP1 using a String value.
  */
  public void setE12LNKOP1(String newvalue)
  {
    fieldE12LNKOP1.setString(newvalue);
  }

  /**
  * Get value of field E12LNKOP1 as a String.
  */
  public String getE12LNKOP1()
  {
    return fieldE12LNKOP1.getString();
  }

  /**
  * Set numeric field E12LNKOP1 using a BigDecimal value.
  */
  public void setE12LNKOP1(BigDecimal newvalue)
  {
    fieldE12LNKOP1.setBigDecimal(newvalue);
  }

  /**
  * Return value of numeric field E12LNKOP1 as a BigDecimal.
  */
  public BigDecimal getBigDecimalE12LNKOP1()
  {
    return fieldE12LNKOP1.getBigDecimal();
  }

  /**
  * Set field E12LNKOP2 using a String value.
  */
  public void setE12LNKOP2(String newvalue)
  {
    fieldE12LNKOP2.setString(newvalue);
  }

  /**
  * Get value of field E12LNKOP2 as a String.
  */
  public String getE12LNKOP2()
  {
    return fieldE12LNKOP2.getString();
  }

  /**
  * Set numeric field E12LNKOP2 using a BigDecimal value.
  */
  public void setE12LNKOP2(BigDecimal newvalue)
  {
    fieldE12LNKOP2.setBigDecimal(newvalue);
  }

  /**
  * Return value of numeric field E12LNKOP2 as a BigDecimal.
  */
  public BigDecimal getBigDecimalE12LNKOP2()
  {
    return fieldE12LNKOP2.getBigDecimal();
  }

  /**
  * Set field E12LNKOP3 using a String value.
  */
  public void setE12LNKOP3(String newvalue)
  {
    fieldE12LNKOP3.setString(newvalue);
  }

  /**
  * Get value of field E12LNKOP3 as a String.
  */
  public String getE12LNKOP3()
  {
    return fieldE12LNKOP3.getString();
  }

  /**
  * Set numeric field E12LNKOP3 using a BigDecimal value.
  */
  public void setE12LNKOP3(BigDecimal newvalue)
  {
    fieldE12LNKOP3.setBigDecimal(newvalue);
  }

  /**
  * Return value of numeric field E12LNKOP3 as a BigDecimal.
  */
  public BigDecimal getBigDecimalE12LNKOP3()
  {
    return fieldE12LNKOP3.getBigDecimal();
  }

  /**
  * Set field E12LNKEX1 using a String value.
  */
  public void setE12LNKEX1(String newvalue)
  {
    fieldE12LNKEX1.setString(newvalue);
  }

  /**
  * Get value of field E12LNKEX1 as a String.
  */
  public String getE12LNKEX1()
  {
    return fieldE12LNKEX1.getString();
  }

  /**
  * Set numeric field E12LNKEX1 using a BigDecimal value.
  */
  public void setE12LNKEX1(BigDecimal newvalue)
  {
    fieldE12LNKEX1.setBigDecimal(newvalue);
  }

  /**
  * Return value of numeric field E12LNKEX1 as a BigDecimal.
  */
  public BigDecimal getBigDecimalE12LNKEX1()
  {
    return fieldE12LNKEX1.getBigDecimal();
  }

  /**
  * Set field E12LNKEX2 using a String value.
  */
  public void setE12LNKEX2(String newvalue)
  {
    fieldE12LNKEX2.setString(newvalue);
  }

  /**
  * Get value of field E12LNKEX2 as a String.
  */
  public String getE12LNKEX2()
  {
    return fieldE12LNKEX2.getString();
  }

  /**
  * Set numeric field E12LNKEX2 using a BigDecimal value.
  */
  public void setE12LNKEX2(BigDecimal newvalue)
  {
    fieldE12LNKEX2.setBigDecimal(newvalue);
  }

  /**
  * Return value of numeric field E12LNKEX2 as a BigDecimal.
  */
  public BigDecimal getBigDecimalE12LNKEX2()
  {
    return fieldE12LNKEX2.getBigDecimal();
  }

  /**
  * Set field E12LNKEX3 using a String value.
  */
  public void setE12LNKEX3(String newvalue)
  {
    fieldE12LNKEX3.setString(newvalue);
  }

  /**
  * Get value of field E12LNKEX3 as a String.
  */
  public String getE12LNKEX3()
  {
    return fieldE12LNKEX3.getString();
  }

  /**
  * Set numeric field E12LNKEX3 using a BigDecimal value.
  */
  public void setE12LNKEX3(BigDecimal newvalue)
  {
    fieldE12LNKEX3.setBigDecimal(newvalue);
  }

  /**
  * Return value of numeric field E12LNKEX3 as a BigDecimal.
  */
  public BigDecimal getBigDecimalE12LNKEX3()
  {
    return fieldE12LNKEX3.getBigDecimal();
  }

}
