package datapro.eibs.beans;

import datapro.eibs.sockets.*;
import java.io.*;
import java.math.*;

import java.util.*;

/**
* Class generated by AS/400 CRTCLASS command from ERA008001 physical file definition.
* 
* File level identifier is 1030121191953.
* Record format level identifier is 447709B2C5422.
*/

public class ERA008001Message extends MessageRecord
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
                                     "E01ROCCUN",
                                     "E01ROCREF",
                                     "E01CUSNA1",
                                     "E01REMARK",
                                     "E01ROCBNK",
                                     "E01ROCBRN",
                                     "E01ROCCCY",
                                     "E01ROCAMN",
                                     "E01ROCTYP",
                                     "E01ROCACD"
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
                                   "E01ROCCUN",
                                   "E01ROCREF",
                                   "E01CUSNA1",
                                   "E01REMARK",
                                   "E01ROCBNK",
                                   "E01ROCBRN",
                                   "E01ROCCCY",
                                   "E01ROCAMN",
                                   "E01ROCTYP",
                                   "E01ROCACD"
                                 };
  final static String fid = "1030121191953";
  final static String rid = "447709B2C5422";
  final static String fmtname = "ERA008001";
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
  private DecimalField fieldE01ROCCUN = null;
  private DecimalField fieldE01ROCREF = null;
  private CharacterField fieldE01CUSNA1 = null;
  private CharacterField fieldE01REMARK = null;
  private CharacterField fieldE01ROCBNK = null;
  private DecimalField fieldE01ROCBRN = null;
  private CharacterField fieldE01ROCCCY = null;
  private DecimalField fieldE01ROCAMN = null;
  private CharacterField fieldE01ROCTYP = null;
  private CharacterField fieldE01ROCACD = null;

  /**
  * Constructor for ERA008001Message.
  */
  public ERA008001Message()
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
    recordsize = 162;
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
    fields[9] = fieldE01ROCCUN =
    new DecimalField(message, HEADERSIZE + 42, 10, 0, "E01ROCCUN");
    fields[10] = fieldE01ROCREF =
    new DecimalField(message, HEADERSIZE + 52, 13, 0, "E01ROCREF");
    fields[11] = fieldE01CUSNA1 =
    new CharacterField(message, HEADERSIZE + 65, 45, "E01CUSNA1");
    fields[12] = fieldE01REMARK =
    new CharacterField(message, HEADERSIZE + 110, 20, "E01REMARK");
    fields[13] = fieldE01ROCBNK =
    new CharacterField(message, HEADERSIZE + 130, 2, "E01ROCBNK");
    fields[14] = fieldE01ROCBRN =
    new DecimalField(message, HEADERSIZE + 132, 4, 0, "E01ROCBRN");
    fields[15] = fieldE01ROCCCY =
    new CharacterField(message, HEADERSIZE + 136, 3, "E01ROCCCY");
    fields[16] = fieldE01ROCAMN =
    new DecimalField(message, HEADERSIZE + 139, 17, 2, "E01ROCAMN");
    fields[17] = fieldE01ROCTYP =
    new CharacterField(message, HEADERSIZE + 156, 4, "E01ROCTYP");
    fields[18] = fieldE01ROCACD =
    new CharacterField(message, HEADERSIZE + 160, 2, "E01ROCACD");

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
  * Set field E01ROCCUN using a String value.
  */
  public void setE01ROCCUN(String newvalue)
  {
    fieldE01ROCCUN.setString(newvalue);
  }

  /**
  * Get value of field E01ROCCUN as a String.
  */
  public String getE01ROCCUN()
  {
    return fieldE01ROCCUN.getString();
  }

  /**
  * Set numeric field E01ROCCUN using a BigDecimal value.
  */
  public void setE01ROCCUN(BigDecimal newvalue)
  {
    fieldE01ROCCUN.setBigDecimal(newvalue);
  }

  /**
  * Return value of numeric field E01ROCCUN as a BigDecimal.
  */
  public BigDecimal getBigDecimalE01ROCCUN()
  {
    return fieldE01ROCCUN.getBigDecimal();
  }

  /**
  * Set field E01ROCREF using a String value.
  */
  public void setE01ROCREF(String newvalue)
  {
    fieldE01ROCREF.setString(newvalue);
  }

  /**
  * Get value of field E01ROCREF as a String.
  */
  public String getE01ROCREF()
  {
    return fieldE01ROCREF.getString();
  }

  /**
  * Set numeric field E01ROCREF using a BigDecimal value.
  */
  public void setE01ROCREF(BigDecimal newvalue)
  {
    fieldE01ROCREF.setBigDecimal(newvalue);
  }

  /**
  * Return value of numeric field E01ROCREF as a BigDecimal.
  */
  public BigDecimal getBigDecimalE01ROCREF()
  {
    return fieldE01ROCREF.getBigDecimal();
  }

  /**
  * Set field E01CUSNA1 using a String value.
  */
  public void setE01CUSNA1(String newvalue)
  {
    fieldE01CUSNA1.setString(newvalue);
  }

  /**
  * Get value of field E01CUSNA1 as a String.
  */
  public String getE01CUSNA1()
  {
    return fieldE01CUSNA1.getString();
  }

  /**
  * Set field E01REMARK using a String value.
  */
  public void setE01REMARK(String newvalue)
  {
    fieldE01REMARK.setString(newvalue);
  }

  /**
  * Get value of field E01REMARK as a String.
  */
  public String getE01REMARK()
  {
    return fieldE01REMARK.getString();
  }

  /**
  * Set field E01ROCBNK using a String value.
  */
  public void setE01ROCBNK(String newvalue)
  {
    fieldE01ROCBNK.setString(newvalue);
  }

  /**
  * Get value of field E01ROCBNK as a String.
  */
  public String getE01ROCBNK()
  {
    return fieldE01ROCBNK.getString();
  }

  /**
  * Set field E01ROCBRN using a String value.
  */
  public void setE01ROCBRN(String newvalue)
  {
    fieldE01ROCBRN.setString(newvalue);
  }

  /**
  * Get value of field E01ROCBRN as a String.
  */
  public String getE01ROCBRN()
  {
    return fieldE01ROCBRN.getString();
  }

  /**
  * Set numeric field E01ROCBRN using a BigDecimal value.
  */
  public void setE01ROCBRN(BigDecimal newvalue)
  {
    fieldE01ROCBRN.setBigDecimal(newvalue);
  }

  /**
  * Return value of numeric field E01ROCBRN as a BigDecimal.
  */
  public BigDecimal getBigDecimalE01ROCBRN()
  {
    return fieldE01ROCBRN.getBigDecimal();
  }

  /**
  * Set field E01ROCCCY using a String value.
  */
  public void setE01ROCCCY(String newvalue)
  {
    fieldE01ROCCCY.setString(newvalue);
  }

  /**
  * Get value of field E01ROCCCY as a String.
  */
  public String getE01ROCCCY()
  {
    return fieldE01ROCCCY.getString();
  }

  /**
  * Set field E01ROCAMN using a String value.
  */
  public void setE01ROCAMN(String newvalue)
  {
    fieldE01ROCAMN.setString(newvalue);
  }

  /**
  * Get value of field E01ROCAMN as a String.
  */
  public String getE01ROCAMN()
  {
    return fieldE01ROCAMN.getString();
  }

  /**
  * Set numeric field E01ROCAMN using a BigDecimal value.
  */
  public void setE01ROCAMN(BigDecimal newvalue)
  {
    fieldE01ROCAMN.setBigDecimal(newvalue);
  }

  /**
  * Return value of numeric field E01ROCAMN as a BigDecimal.
  */
  public BigDecimal getBigDecimalE01ROCAMN()
  {
    return fieldE01ROCAMN.getBigDecimal();
  }

  /**
  * Set field E01ROCTYP using a String value.
  */
  public void setE01ROCTYP(String newvalue)
  {
    fieldE01ROCTYP.setString(newvalue);
  }

  /**
  * Get value of field E01ROCTYP as a String.
  */
  public String getE01ROCTYP()
  {
    return fieldE01ROCTYP.getString();
  }

  /**
  * Set field E01ROCACD using a String value.
  */
  public void setE01ROCACD(String newvalue)
  {
    fieldE01ROCACD.setString(newvalue);
  }

  /**
  * Get value of field E01ROCACD as a String.
  */
  public String getE01ROCACD()
  {
    return fieldE01ROCACD.getString();
  }

}
