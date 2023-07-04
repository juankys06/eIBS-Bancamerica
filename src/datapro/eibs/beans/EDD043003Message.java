package datapro.eibs.beans;

import datapro.eibs.sockets.*;
import java.io.*;
import java.math.*;

import java.util.*;

/**
* Class generated by AS/400 CRTCLASS command from EDD043003 physical file definition.
* 
* File level identifier is 1030121191845.
* Record format level identifier is 42A46DA6417AF.
*/

public class EDD043003Message extends MessageRecord
{
  final static String fldnames[] = {
                                     "H03USERID",
                                     "H03PROGRM",
                                     "H03TIMSYS",
                                     "H03SCRCOD",
                                     "H03OPECOD",
                                     "H03FLGMAS",
                                     "H03FLGWK1",
                                     "H03FLGWK2",
                                     "H03FLGWK3",
                                     "E03ACMACC",
                                     "E03ACMPRO",
                                     "E03ACMCUN",
                                     "E03CUSNA1",
                                     "E03INVRBK",
                                     "E03INVRBR",
                                     "E03INVRCY",
                                     "E03INVRGL",
                                     "E03INVRAC",
                                     "E03INVSD1",
                                     "E03INVSD2",
                                     "E03INVSD3",
                                     "E03INVMD1",
                                     "E03INVMD2",
                                     "E03INVMD3"
                                   };
  final static String tnames[] = {
                                   "H03USERID",
                                   "H03PROGRM",
                                   "H03TIMSYS",
                                   "H03SCRCOD",
                                   "H03OPECOD",
                                   "H03FLGMAS",
                                   "H03FLGWK1",
                                   "H03FLGWK2",
                                   "H03FLGWK3",
                                   "E03ACMACC",
                                   "E03ACMPRO",
                                   "E03ACMCUN",
                                   "E03CUSNA1",
                                   "E03INVRBK",
                                   "E03INVRBR",
                                   "E03INVRCY",
                                   "E03INVRGL",
                                   "E03INVRAC",
                                   "E03INVSD1",
                                   "E03INVSD2",
                                   "E03INVSD3",
                                   "E03INVMD1",
                                   "E03INVMD2",
                                   "E03INVMD3"
                                 };
  final static String fid = "1030121191845";
  final static String rid = "42A46DA6417AF";
  final static String fmtname = "EDD043003";
  final int FIELDCOUNT = 24;
  private static Hashtable tlookup = new Hashtable();
  private CharacterField fieldH03USERID = null;
  private CharacterField fieldH03PROGRM = null;
  private CharacterField fieldH03TIMSYS = null;
  private CharacterField fieldH03SCRCOD = null;
  private CharacterField fieldH03OPECOD = null;
  private CharacterField fieldH03FLGMAS = null;
  private CharacterField fieldH03FLGWK1 = null;
  private CharacterField fieldH03FLGWK2 = null;
  private CharacterField fieldH03FLGWK3 = null;
  private DecimalField fieldE03ACMACC = null;
  private CharacterField fieldE03ACMPRO = null;
  private DecimalField fieldE03ACMCUN = null;
  private CharacterField fieldE03CUSNA1 = null;
  private CharacterField fieldE03INVRBK = null;
  private DecimalField fieldE03INVRBR = null;
  private CharacterField fieldE03INVRCY = null;
  private DecimalField fieldE03INVRGL = null;
  private DecimalField fieldE03INVRAC = null;
  private DecimalField fieldE03INVSD1 = null;
  private DecimalField fieldE03INVSD2 = null;
  private DecimalField fieldE03INVSD3 = null;
  private DecimalField fieldE03INVMD1 = null;
  private DecimalField fieldE03INVMD2 = null;
  private DecimalField fieldE03INVMD3 = null;

  /**
  * Constructor for EDD043003Message.
  */
  public EDD043003Message()
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
    recordsize = 171;
    fileid = fid;
    recordid = rid;
    message = new byte[getByteLength()];
    formatname = fmtname;
    fieldnames = fldnames;
    tagnames = tnames;
    fields = new MessageField[FIELDCOUNT];

    fields[0] = fieldH03USERID =
    new CharacterField(message, HEADERSIZE + 0, 10, "H03USERID");
    fields[1] = fieldH03PROGRM =
    new CharacterField(message, HEADERSIZE + 10, 10, "H03PROGRM");
    fields[2] = fieldH03TIMSYS =
    new CharacterField(message, HEADERSIZE + 20, 12, "H03TIMSYS");
    fields[3] = fieldH03SCRCOD =
    new CharacterField(message, HEADERSIZE + 32, 2, "H03SCRCOD");
    fields[4] = fieldH03OPECOD =
    new CharacterField(message, HEADERSIZE + 34, 4, "H03OPECOD");
    fields[5] = fieldH03FLGMAS =
    new CharacterField(message, HEADERSIZE + 38, 1, "H03FLGMAS");
    fields[6] = fieldH03FLGWK1 =
    new CharacterField(message, HEADERSIZE + 39, 1, "H03FLGWK1");
    fields[7] = fieldH03FLGWK2 =
    new CharacterField(message, HEADERSIZE + 40, 1, "H03FLGWK2");
    fields[8] = fieldH03FLGWK3 =
    new CharacterField(message, HEADERSIZE + 41, 1, "H03FLGWK3");
    fields[9] = fieldE03ACMACC =
    new DecimalField(message, HEADERSIZE + 42, 13, 0, "E03ACMACC");
    fields[10] = fieldE03ACMPRO =
    new CharacterField(message, HEADERSIZE + 55, 4, "E03ACMPRO");
    fields[11] = fieldE03ACMCUN =
    new DecimalField(message, HEADERSIZE + 59, 10, 0, "E03ACMCUN");
    fields[12] = fieldE03CUSNA1 =
    new CharacterField(message, HEADERSIZE + 69, 45, "E03CUSNA1");
    fields[13] = fieldE03INVRBK =
    new CharacterField(message, HEADERSIZE + 114, 2, "E03INVRBK");
    fields[14] = fieldE03INVRBR =
    new DecimalField(message, HEADERSIZE + 116, 4, 0, "E03INVRBR");
    fields[15] = fieldE03INVRCY =
    new CharacterField(message, HEADERSIZE + 120, 3, "E03INVRCY");
    fields[16] = fieldE03INVRGL =
    new DecimalField(message, HEADERSIZE + 123, 17, 0, "E03INVRGL");
    fields[17] = fieldE03INVRAC =
    new DecimalField(message, HEADERSIZE + 140, 13, 0, "E03INVRAC");
    fields[18] = fieldE03INVSD1 =
    new DecimalField(message, HEADERSIZE + 153, 3, 0, "E03INVSD1");
    fields[19] = fieldE03INVSD2 =
    new DecimalField(message, HEADERSIZE + 156, 3, 0, "E03INVSD2");
    fields[20] = fieldE03INVSD3 =
    new DecimalField(message, HEADERSIZE + 159, 3, 0, "E03INVSD3");
    fields[21] = fieldE03INVMD1 =
    new DecimalField(message, HEADERSIZE + 162, 3, 0, "E03INVMD1");
    fields[22] = fieldE03INVMD2 =
    new DecimalField(message, HEADERSIZE + 165, 3, 0, "E03INVMD2");
    fields[23] = fieldE03INVMD3 =
    new DecimalField(message, HEADERSIZE + 168, 3, 0, "E03INVMD3");

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
  * Set field H03USERID using a String value.
  */
  public void setH03USERID(String newvalue)
  {
    fieldH03USERID.setString(newvalue);
  }

  /**
  * Get value of field H03USERID as a String.
  */
  public String getH03USERID()
  {
    return fieldH03USERID.getString();
  }

  /**
  * Set field H03PROGRM using a String value.
  */
  public void setH03PROGRM(String newvalue)
  {
    fieldH03PROGRM.setString(newvalue);
  }

  /**
  * Get value of field H03PROGRM as a String.
  */
  public String getH03PROGRM()
  {
    return fieldH03PROGRM.getString();
  }

  /**
  * Set field H03TIMSYS using a String value.
  */
  public void setH03TIMSYS(String newvalue)
  {
    fieldH03TIMSYS.setString(newvalue);
  }

  /**
  * Get value of field H03TIMSYS as a String.
  */
  public String getH03TIMSYS()
  {
    return fieldH03TIMSYS.getString();
  }

  /**
  * Set field H03SCRCOD using a String value.
  */
  public void setH03SCRCOD(String newvalue)
  {
    fieldH03SCRCOD.setString(newvalue);
  }

  /**
  * Get value of field H03SCRCOD as a String.
  */
  public String getH03SCRCOD()
  {
    return fieldH03SCRCOD.getString();
  }

  /**
  * Set field H03OPECOD using a String value.
  */
  public void setH03OPECOD(String newvalue)
  {
    fieldH03OPECOD.setString(newvalue);
  }

  /**
  * Get value of field H03OPECOD as a String.
  */
  public String getH03OPECOD()
  {
    return fieldH03OPECOD.getString();
  }

  /**
  * Set field H03FLGMAS using a String value.
  */
  public void setH03FLGMAS(String newvalue)
  {
    fieldH03FLGMAS.setString(newvalue);
  }

  /**
  * Get value of field H03FLGMAS as a String.
  */
  public String getH03FLGMAS()
  {
    return fieldH03FLGMAS.getString();
  }

  /**
  * Set field H03FLGWK1 using a String value.
  */
  public void setH03FLGWK1(String newvalue)
  {
    fieldH03FLGWK1.setString(newvalue);
  }

  /**
  * Get value of field H03FLGWK1 as a String.
  */
  public String getH03FLGWK1()
  {
    return fieldH03FLGWK1.getString();
  }

  /**
  * Set field H03FLGWK2 using a String value.
  */
  public void setH03FLGWK2(String newvalue)
  {
    fieldH03FLGWK2.setString(newvalue);
  }

  /**
  * Get value of field H03FLGWK2 as a String.
  */
  public String getH03FLGWK2()
  {
    return fieldH03FLGWK2.getString();
  }

  /**
  * Set field H03FLGWK3 using a String value.
  */
  public void setH03FLGWK3(String newvalue)
  {
    fieldH03FLGWK3.setString(newvalue);
  }

  /**
  * Get value of field H03FLGWK3 as a String.
  */
  public String getH03FLGWK3()
  {
    return fieldH03FLGWK3.getString();
  }

  /**
  * Set field E03ACMACC using a String value.
  */
  public void setE03ACMACC(String newvalue)
  {
    fieldE03ACMACC.setString(newvalue);
  }

  /**
  * Get value of field E03ACMACC as a String.
  */
  public String getE03ACMACC()
  {
    return fieldE03ACMACC.getString();
  }

  /**
  * Set numeric field E03ACMACC using a BigDecimal value.
  */
  public void setE03ACMACC(BigDecimal newvalue)
  {
    fieldE03ACMACC.setBigDecimal(newvalue);
  }

  /**
  * Return value of numeric field E03ACMACC as a BigDecimal.
  */
  public BigDecimal getBigDecimalE03ACMACC()
  {
    return fieldE03ACMACC.getBigDecimal();
  }

  /**
  * Set field E03ACMPRO using a String value.
  */
  public void setE03ACMPRO(String newvalue)
  {
    fieldE03ACMPRO.setString(newvalue);
  }

  /**
  * Get value of field E03ACMPRO as a String.
  */
  public String getE03ACMPRO()
  {
    return fieldE03ACMPRO.getString();
  }

  /**
  * Set field E03ACMCUN using a String value.
  */
  public void setE03ACMCUN(String newvalue)
  {
    fieldE03ACMCUN.setString(newvalue);
  }

  /**
  * Get value of field E03ACMCUN as a String.
  */
  public String getE03ACMCUN()
  {
    return fieldE03ACMCUN.getString();
  }

  /**
  * Set numeric field E03ACMCUN using a BigDecimal value.
  */
  public void setE03ACMCUN(BigDecimal newvalue)
  {
    fieldE03ACMCUN.setBigDecimal(newvalue);
  }

  /**
  * Return value of numeric field E03ACMCUN as a BigDecimal.
  */
  public BigDecimal getBigDecimalE03ACMCUN()
  {
    return fieldE03ACMCUN.getBigDecimal();
  }

  /**
  * Set field E03CUSNA1 using a String value.
  */
  public void setE03CUSNA1(String newvalue)
  {
    fieldE03CUSNA1.setString(newvalue);
  }

  /**
  * Get value of field E03CUSNA1 as a String.
  */
  public String getE03CUSNA1()
  {
    return fieldE03CUSNA1.getString();
  }

  /**
  * Set field E03INVRBK using a String value.
  */
  public void setE03INVRBK(String newvalue)
  {
    fieldE03INVRBK.setString(newvalue);
  }

  /**
  * Get value of field E03INVRBK as a String.
  */
  public String getE03INVRBK()
  {
    return fieldE03INVRBK.getString();
  }

  /**
  * Set field E03INVRBR using a String value.
  */
  public void setE03INVRBR(String newvalue)
  {
    fieldE03INVRBR.setString(newvalue);
  }

  /**
  * Get value of field E03INVRBR as a String.
  */
  public String getE03INVRBR()
  {
    return fieldE03INVRBR.getString();
  }

  /**
  * Set numeric field E03INVRBR using a BigDecimal value.
  */
  public void setE03INVRBR(BigDecimal newvalue)
  {
    fieldE03INVRBR.setBigDecimal(newvalue);
  }

  /**
  * Return value of numeric field E03INVRBR as a BigDecimal.
  */
  public BigDecimal getBigDecimalE03INVRBR()
  {
    return fieldE03INVRBR.getBigDecimal();
  }

  /**
  * Set field E03INVRCY using a String value.
  */
  public void setE03INVRCY(String newvalue)
  {
    fieldE03INVRCY.setString(newvalue);
  }

  /**
  * Get value of field E03INVRCY as a String.
  */
  public String getE03INVRCY()
  {
    return fieldE03INVRCY.getString();
  }

  /**
  * Set field E03INVRGL using a String value.
  */
  public void setE03INVRGL(String newvalue)
  {
    fieldE03INVRGL.setString(newvalue);
  }

  /**
  * Get value of field E03INVRGL as a String.
  */
  public String getE03INVRGL()
  {
    return fieldE03INVRGL.getString();
  }

  /**
  * Set numeric field E03INVRGL using a BigDecimal value.
  */
  public void setE03INVRGL(BigDecimal newvalue)
  {
    fieldE03INVRGL.setBigDecimal(newvalue);
  }

  /**
  * Return value of numeric field E03INVRGL as a BigDecimal.
  */
  public BigDecimal getBigDecimalE03INVRGL()
  {
    return fieldE03INVRGL.getBigDecimal();
  }

  /**
  * Set field E03INVRAC using a String value.
  */
  public void setE03INVRAC(String newvalue)
  {
    fieldE03INVRAC.setString(newvalue);
  }

  /**
  * Get value of field E03INVRAC as a String.
  */
  public String getE03INVRAC()
  {
    return fieldE03INVRAC.getString();
  }

  /**
  * Set numeric field E03INVRAC using a BigDecimal value.
  */
  public void setE03INVRAC(BigDecimal newvalue)
  {
    fieldE03INVRAC.setBigDecimal(newvalue);
  }

  /**
  * Return value of numeric field E03INVRAC as a BigDecimal.
  */
  public BigDecimal getBigDecimalE03INVRAC()
  {
    return fieldE03INVRAC.getBigDecimal();
  }

  /**
  * Set field E03INVSD1 using a String value.
  */
  public void setE03INVSD1(String newvalue)
  {
    fieldE03INVSD1.setString(newvalue);
  }

  /**
  * Get value of field E03INVSD1 as a String.
  */
  public String getE03INVSD1()
  {
    return fieldE03INVSD1.getString();
  }

  /**
  * Set numeric field E03INVSD1 using a BigDecimal value.
  */
  public void setE03INVSD1(BigDecimal newvalue)
  {
    fieldE03INVSD1.setBigDecimal(newvalue);
  }

  /**
  * Return value of numeric field E03INVSD1 as a BigDecimal.
  */
  public BigDecimal getBigDecimalE03INVSD1()
  {
    return fieldE03INVSD1.getBigDecimal();
  }

  /**
  * Set field E03INVSD2 using a String value.
  */
  public void setE03INVSD2(String newvalue)
  {
    fieldE03INVSD2.setString(newvalue);
  }

  /**
  * Get value of field E03INVSD2 as a String.
  */
  public String getE03INVSD2()
  {
    return fieldE03INVSD2.getString();
  }

  /**
  * Set numeric field E03INVSD2 using a BigDecimal value.
  */
  public void setE03INVSD2(BigDecimal newvalue)
  {
    fieldE03INVSD2.setBigDecimal(newvalue);
  }

  /**
  * Return value of numeric field E03INVSD2 as a BigDecimal.
  */
  public BigDecimal getBigDecimalE03INVSD2()
  {
    return fieldE03INVSD2.getBigDecimal();
  }

  /**
  * Set field E03INVSD3 using a String value.
  */
  public void setE03INVSD3(String newvalue)
  {
    fieldE03INVSD3.setString(newvalue);
  }

  /**
  * Get value of field E03INVSD3 as a String.
  */
  public String getE03INVSD3()
  {
    return fieldE03INVSD3.getString();
  }

  /**
  * Set numeric field E03INVSD3 using a BigDecimal value.
  */
  public void setE03INVSD3(BigDecimal newvalue)
  {
    fieldE03INVSD3.setBigDecimal(newvalue);
  }

  /**
  * Return value of numeric field E03INVSD3 as a BigDecimal.
  */
  public BigDecimal getBigDecimalE03INVSD3()
  {
    return fieldE03INVSD3.getBigDecimal();
  }

  /**
  * Set field E03INVMD1 using a String value.
  */
  public void setE03INVMD1(String newvalue)
  {
    fieldE03INVMD1.setString(newvalue);
  }

  /**
  * Get value of field E03INVMD1 as a String.
  */
  public String getE03INVMD1()
  {
    return fieldE03INVMD1.getString();
  }

  /**
  * Set numeric field E03INVMD1 using a BigDecimal value.
  */
  public void setE03INVMD1(BigDecimal newvalue)
  {
    fieldE03INVMD1.setBigDecimal(newvalue);
  }

  /**
  * Return value of numeric field E03INVMD1 as a BigDecimal.
  */
  public BigDecimal getBigDecimalE03INVMD1()
  {
    return fieldE03INVMD1.getBigDecimal();
  }

  /**
  * Set field E03INVMD2 using a String value.
  */
  public void setE03INVMD2(String newvalue)
  {
    fieldE03INVMD2.setString(newvalue);
  }

  /**
  * Get value of field E03INVMD2 as a String.
  */
  public String getE03INVMD2()
  {
    return fieldE03INVMD2.getString();
  }

  /**
  * Set numeric field E03INVMD2 using a BigDecimal value.
  */
  public void setE03INVMD2(BigDecimal newvalue)
  {
    fieldE03INVMD2.setBigDecimal(newvalue);
  }

  /**
  * Return value of numeric field E03INVMD2 as a BigDecimal.
  */
  public BigDecimal getBigDecimalE03INVMD2()
  {
    return fieldE03INVMD2.getBigDecimal();
  }

  /**
  * Set field E03INVMD3 using a String value.
  */
  public void setE03INVMD3(String newvalue)
  {
    fieldE03INVMD3.setString(newvalue);
  }

  /**
  * Get value of field E03INVMD3 as a String.
  */
  public String getE03INVMD3()
  {
    return fieldE03INVMD3.getString();
  }

  /**
  * Set numeric field E03INVMD3 using a BigDecimal value.
  */
  public void setE03INVMD3(BigDecimal newvalue)
  {
    fieldE03INVMD3.setBigDecimal(newvalue);
  }

  /**
  * Return value of numeric field E03INVMD3 as a BigDecimal.
  */
  public BigDecimal getBigDecimalE03INVMD3()
  {
    return fieldE03INVMD3.getBigDecimal();
  }

}