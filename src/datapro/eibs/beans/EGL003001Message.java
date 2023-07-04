package datapro.eibs.beans;

import datapro.eibs.sockets.*;
import java.io.*;
import java.math.*;

import java.util.*;

/**
* Class generated by AS/400 CRTCLASS command from EGL003001 physical file definition.
* 
* File level identifier is 1101005190524.
* Record format level identifier is 4E09907B15CBD.
*/

public class EGL003001Message extends MessageRecord
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
                                     "E01ORGBNK",
                                     "E01BTHNUM",
                                     "E01RUNDT1",
                                     "E01RUNDT2",
                                     "E01RUNDT3",
                                     "E01TOTDEB",
                                     "E01TOTCRE",
                                     "E01TOTTRN",
                                     "E01ACTION",
                                     "E01USERID"
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
                                   "E01ORGBNK",
                                   "E01BTHNUM",
                                   "E01RUNDT1",
                                   "E01RUNDT2",
                                   "E01RUNDT3",
                                   "E01TOTDEB",
                                   "E01TOTCRE",
                                   "E01TOTTRN",
                                   "E01ACTION",
                                   "E01USERID"
                                 };
  final static String fid = "1101005190524";
  final static String rid = "4E09907B15CBD";
  final static String fmtname = "EGL003001";
  final int FIELDCOUNT = 19;
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
  private CharacterField fieldE01ORGBNK = null;
  private DecimalField fieldE01BTHNUM = null;
  private DecimalField fieldE01RUNDT1 = null;
  private DecimalField fieldE01RUNDT2 = null;
  private DecimalField fieldE01RUNDT3 = null;
  private DecimalField fieldE01TOTDEB = null;
  private DecimalField fieldE01TOTCRE = null;
  private DecimalField fieldE01TOTTRN = null;
  private CharacterField fieldE01ACTION = null;
  private CharacterField fieldE01USERID = null;

  /**
  * Constructor for EGL003001Message.
  */
  public EGL003001Message()
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
    recordsize = 109;
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
    fields[9] = fieldE01ORGBNK =
    new CharacterField(message, HEADERSIZE + 42, 2, "E01ORGBNK");
    fields[10] = fieldE01BTHNUM =
    new DecimalField(message, HEADERSIZE + 44, 5, 0, "E01BTHNUM");
    fields[11] = fieldE01RUNDT1 =
    new DecimalField(message, HEADERSIZE + 49, 3, 0, "E01RUNDT1");
    fields[12] = fieldE01RUNDT2 =
    new DecimalField(message, HEADERSIZE + 52, 3, 0, "E01RUNDT2");
    fields[13] = fieldE01RUNDT3 =
    new DecimalField(message, HEADERSIZE + 55, 3, 0, "E01RUNDT3");
    fields[14] = fieldE01TOTDEB =
    new DecimalField(message, HEADERSIZE + 58, 17, 2, "E01TOTDEB");
    fields[15] = fieldE01TOTCRE =
    new DecimalField(message, HEADERSIZE + 75, 17, 2, "E01TOTCRE");
    fields[16] = fieldE01TOTTRN =
    new DecimalField(message, HEADERSIZE + 92, 6, 0, "E01TOTTRN");
    fields[17] = fieldE01ACTION =
    new CharacterField(message, HEADERSIZE + 98, 1, "E01ACTION");
    fields[18] = fieldE01USERID =
    new CharacterField(message, HEADERSIZE + 99, 10, "E01USERID");

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
  * Set field E01ORGBNK using a String value.
  */
  public void setE01ORGBNK(String newvalue)
  {
    fieldE01ORGBNK.setString(newvalue);
  }

  /**
  * Get value of field E01ORGBNK as a String.
  */
  public String getE01ORGBNK()
  {
    return fieldE01ORGBNK.getString();
  }

  /**
  * Set field E01BTHNUM using a String value.
  */
  public void setE01BTHNUM(String newvalue)
  {
    fieldE01BTHNUM.setString(newvalue);
  }

  /**
  * Get value of field E01BTHNUM as a String.
  */
  public String getE01BTHNUM()
  {
    return fieldE01BTHNUM.getString();
  }

  /**
  * Set numeric field E01BTHNUM using a BigDecimal value.
  */
  public void setE01BTHNUM(BigDecimal newvalue)
  {
    fieldE01BTHNUM.setBigDecimal(newvalue);
  }

  /**
  * Return value of numeric field E01BTHNUM as a BigDecimal.
  */
  public BigDecimal getBigDecimalE01BTHNUM()
  {
    return fieldE01BTHNUM.getBigDecimal();
  }

  /**
  * Set field E01RUNDT1 using a String value.
  */
  public void setE01RUNDT1(String newvalue)
  {
    fieldE01RUNDT1.setString(newvalue);
  }

  /**
  * Get value of field E01RUNDT1 as a String.
  */
  public String getE01RUNDT1()
  {
    return fieldE01RUNDT1.getString();
  }

  /**
  * Set numeric field E01RUNDT1 using a BigDecimal value.
  */
  public void setE01RUNDT1(BigDecimal newvalue)
  {
    fieldE01RUNDT1.setBigDecimal(newvalue);
  }

  /**
  * Return value of numeric field E01RUNDT1 as a BigDecimal.
  */
  public BigDecimal getBigDecimalE01RUNDT1()
  {
    return fieldE01RUNDT1.getBigDecimal();
  }

  /**
  * Set field E01RUNDT2 using a String value.
  */
  public void setE01RUNDT2(String newvalue)
  {
    fieldE01RUNDT2.setString(newvalue);
  }

  /**
  * Get value of field E01RUNDT2 as a String.
  */
  public String getE01RUNDT2()
  {
    return fieldE01RUNDT2.getString();
  }

  /**
  * Set numeric field E01RUNDT2 using a BigDecimal value.
  */
  public void setE01RUNDT2(BigDecimal newvalue)
  {
    fieldE01RUNDT2.setBigDecimal(newvalue);
  }

  /**
  * Return value of numeric field E01RUNDT2 as a BigDecimal.
  */
  public BigDecimal getBigDecimalE01RUNDT2()
  {
    return fieldE01RUNDT2.getBigDecimal();
  }

  /**
  * Set field E01RUNDT3 using a String value.
  */
  public void setE01RUNDT3(String newvalue)
  {
    fieldE01RUNDT3.setString(newvalue);
  }

  /**
  * Get value of field E01RUNDT3 as a String.
  */
  public String getE01RUNDT3()
  {
    return fieldE01RUNDT3.getString();
  }

  /**
  * Set numeric field E01RUNDT3 using a BigDecimal value.
  */
  public void setE01RUNDT3(BigDecimal newvalue)
  {
    fieldE01RUNDT3.setBigDecimal(newvalue);
  }

  /**
  * Return value of numeric field E01RUNDT3 as a BigDecimal.
  */
  public BigDecimal getBigDecimalE01RUNDT3()
  {
    return fieldE01RUNDT3.getBigDecimal();
  }

  /**
  * Set field E01TOTDEB using a String value.
  */
  public void setE01TOTDEB(String newvalue)
  {
    fieldE01TOTDEB.setString(newvalue);
  }

  /**
  * Get value of field E01TOTDEB as a String.
  */
  public String getE01TOTDEB()
  {
    return fieldE01TOTDEB.getString();
  }

  /**
  * Set numeric field E01TOTDEB using a BigDecimal value.
  */
  public void setE01TOTDEB(BigDecimal newvalue)
  {
    fieldE01TOTDEB.setBigDecimal(newvalue);
  }

  /**
  * Return value of numeric field E01TOTDEB as a BigDecimal.
  */
  public BigDecimal getBigDecimalE01TOTDEB()
  {
    return fieldE01TOTDEB.getBigDecimal();
  }

  /**
  * Set field E01TOTCRE using a String value.
  */
  public void setE01TOTCRE(String newvalue)
  {
    fieldE01TOTCRE.setString(newvalue);
  }

  /**
  * Get value of field E01TOTCRE as a String.
  */
  public String getE01TOTCRE()
  {
    return fieldE01TOTCRE.getString();
  }

  /**
  * Set numeric field E01TOTCRE using a BigDecimal value.
  */
  public void setE01TOTCRE(BigDecimal newvalue)
  {
    fieldE01TOTCRE.setBigDecimal(newvalue);
  }

  /**
  * Return value of numeric field E01TOTCRE as a BigDecimal.
  */
  public BigDecimal getBigDecimalE01TOTCRE()
  {
    return fieldE01TOTCRE.getBigDecimal();
  }

  /**
  * Set field E01TOTTRN using a String value.
  */
  public void setE01TOTTRN(String newvalue)
  {
    fieldE01TOTTRN.setString(newvalue);
  }

  /**
  * Get value of field E01TOTTRN as a String.
  */
  public String getE01TOTTRN()
  {
    return fieldE01TOTTRN.getString();
  }

  /**
  * Set numeric field E01TOTTRN using a BigDecimal value.
  */
  public void setE01TOTTRN(BigDecimal newvalue)
  {
    fieldE01TOTTRN.setBigDecimal(newvalue);
  }

  /**
  * Return value of numeric field E01TOTTRN as a BigDecimal.
  */
  public BigDecimal getBigDecimalE01TOTTRN()
  {
    return fieldE01TOTTRN.getBigDecimal();
  }

  /**
  * Set field E01ACTION using a String value.
  */
  public void setE01ACTION(String newvalue)
  {
    fieldE01ACTION.setString(newvalue);
  }

  /**
  * Get value of field E01ACTION as a String.
  */
  public String getE01ACTION()
  {
    return fieldE01ACTION.getString();
  }

  /**
  * Set field E01USERID using a String value.
  */
  public void setE01USERID(String newvalue)
  {
    fieldE01USERID.setString(newvalue);
  }

  /**
  * Get value of field E01USERID as a String.
  */
  public String getE01USERID()
  {
    return fieldE01USERID.getString();
  }

}
