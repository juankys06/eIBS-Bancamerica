package datapro.eibs.beans;

import datapro.eibs.sockets.*;
import java.io.*;
import java.math.*;

import java.util.*;

/**
* Class generated by AS/400 CRTCLASS command from ERA000003 physical file definition.
* 
* File level identifier is 1030121191951.
* Record format level identifier is 2EBFA0E25B106.
*/

public class ERA000003Message extends MessageRecord
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
                                     "E03ROCSEL",
                                     "E03ROCCUN",
                                     "E03CIFACD",
                                     "E03CUSNA1",
                                     "E03ROCREF",
                                     "E03ROCTYP",
                                     "E03ROCCCY",
                                     "E03ROCAMT",
                                     "E03ROCUSE",
                                     "E03ROCBAL",
                                     "E03ROCOD1",
                                     "E03ROCOD2",
                                     "E03ROCOD3",
                                     "E03ROCMD1",
                                     "E03ROCMD2",
                                     "E03ROCMD3",
                                     "E03ENDFLD"
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
                                   "E03ROCSEL",
                                   "E03ROCCUN",
                                   "E03CIFACD",
                                   "E03CUSNA1",
                                   "E03ROCREF",
                                   "E03ROCTYP",
                                   "E03ROCCCY",
                                   "E03ROCAMT",
                                   "E03ROCUSE",
                                   "E03ROCBAL",
                                   "E03ROCOD1",
                                   "E03ROCOD2",
                                   "E03ROCOD3",
                                   "E03ROCMD1",
                                   "E03ROCMD2",
                                   "E03ROCMD3",
                                   "E03ENDFLD"
                                 };
  final static String fid = "1030121191951";
  final static String rid = "2EBFA0E25B106";
  final static String fmtname = "ERA000003";
  final int FIELDCOUNT = 26;
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
  private CharacterField fieldE03ROCSEL = null;
  private DecimalField fieldE03ROCCUN = null;
  private CharacterField fieldE03CIFACD = null;
  private CharacterField fieldE03CUSNA1 = null;
  private DecimalField fieldE03ROCREF = null;
  private CharacterField fieldE03ROCTYP = null;
  private CharacterField fieldE03ROCCCY = null;
  private DecimalField fieldE03ROCAMT = null;
  private DecimalField fieldE03ROCUSE = null;
  private DecimalField fieldE03ROCBAL = null;
  private DecimalField fieldE03ROCOD1 = null;
  private DecimalField fieldE03ROCOD2 = null;
  private DecimalField fieldE03ROCOD3 = null;
  private DecimalField fieldE03ROCMD1 = null;
  private DecimalField fieldE03ROCMD2 = null;
  private DecimalField fieldE03ROCMD3 = null;
  private CharacterField fieldE03ENDFLD = null;

  /**
  * Constructor for ERA000003Message.
  */
  public ERA000003Message()
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
    recordsize = 190;
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
    fields[9] = fieldE03ROCSEL =
    new CharacterField(message, HEADERSIZE + 42, 1, "E03ROCSEL");
    fields[10] = fieldE03ROCCUN =
    new DecimalField(message, HEADERSIZE + 43, 10, 0, "E03ROCCUN");
    fields[11] = fieldE03CIFACD =
    new CharacterField(message, HEADERSIZE + 53, 2, "E03CIFACD");
    fields[12] = fieldE03CUSNA1 =
    new CharacterField(message, HEADERSIZE + 55, 45, "E03CUSNA1");
    fields[13] = fieldE03ROCREF =
    new DecimalField(message, HEADERSIZE + 100, 13, 0, "E03ROCREF");
    fields[14] = fieldE03ROCTYP =
    new CharacterField(message, HEADERSIZE + 113, 4, "E03ROCTYP");
    fields[15] = fieldE03ROCCCY =
    new CharacterField(message, HEADERSIZE + 117, 3, "E03ROCCCY");
    fields[16] = fieldE03ROCAMT =
    new DecimalField(message, HEADERSIZE + 120, 17, 2, "E03ROCAMT");
    fields[17] = fieldE03ROCUSE =
    new DecimalField(message, HEADERSIZE + 137, 17, 2, "E03ROCUSE");
    fields[18] = fieldE03ROCBAL =
    new DecimalField(message, HEADERSIZE + 154, 17, 2, "E03ROCBAL");
    fields[19] = fieldE03ROCOD1 =
    new DecimalField(message, HEADERSIZE + 171, 3, 0, "E03ROCOD1");
    fields[20] = fieldE03ROCOD2 =
    new DecimalField(message, HEADERSIZE + 174, 3, 0, "E03ROCOD2");
    fields[21] = fieldE03ROCOD3 =
    new DecimalField(message, HEADERSIZE + 177, 3, 0, "E03ROCOD3");
    fields[22] = fieldE03ROCMD1 =
    new DecimalField(message, HEADERSIZE + 180, 3, 0, "E03ROCMD1");
    fields[23] = fieldE03ROCMD2 =
    new DecimalField(message, HEADERSIZE + 183, 3, 0, "E03ROCMD2");
    fields[24] = fieldE03ROCMD3 =
    new DecimalField(message, HEADERSIZE + 186, 3, 0, "E03ROCMD3");
    fields[25] = fieldE03ENDFLD =
    new CharacterField(message, HEADERSIZE + 189, 1, "E03ENDFLD");

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
  * Set field E03ROCSEL using a String value.
  */
  public void setE03ROCSEL(String newvalue)
  {
    fieldE03ROCSEL.setString(newvalue);
  }

  /**
  * Get value of field E03ROCSEL as a String.
  */
  public String getE03ROCSEL()
  {
    return fieldE03ROCSEL.getString();
  }

  /**
  * Set field E03ROCCUN using a String value.
  */
  public void setE03ROCCUN(String newvalue)
  {
    fieldE03ROCCUN.setString(newvalue);
  }

  /**
  * Get value of field E03ROCCUN as a String.
  */
  public String getE03ROCCUN()
  {
    return fieldE03ROCCUN.getString();
  }

  /**
  * Set numeric field E03ROCCUN using a BigDecimal value.
  */
  public void setE03ROCCUN(BigDecimal newvalue)
  {
    fieldE03ROCCUN.setBigDecimal(newvalue);
  }

  /**
  * Return value of numeric field E03ROCCUN as a BigDecimal.
  */
  public BigDecimal getBigDecimalE03ROCCUN()
  {
    return fieldE03ROCCUN.getBigDecimal();
  }

  /**
  * Set field E03CIFACD using a String value.
  */
  public void setE03CIFACD(String newvalue)
  {
    fieldE03CIFACD.setString(newvalue);
  }

  /**
  * Get value of field E03CIFACD as a String.
  */
  public String getE03CIFACD()
  {
    return fieldE03CIFACD.getString();
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
  * Set field E03ROCREF using a String value.
  */
  public void setE03ROCREF(String newvalue)
  {
    fieldE03ROCREF.setString(newvalue);
  }

  /**
  * Get value of field E03ROCREF as a String.
  */
  public String getE03ROCREF()
  {
    return fieldE03ROCREF.getString();
  }

  /**
  * Set numeric field E03ROCREF using a BigDecimal value.
  */
  public void setE03ROCREF(BigDecimal newvalue)
  {
    fieldE03ROCREF.setBigDecimal(newvalue);
  }

  /**
  * Return value of numeric field E03ROCREF as a BigDecimal.
  */
  public BigDecimal getBigDecimalE03ROCREF()
  {
    return fieldE03ROCREF.getBigDecimal();
  }

  /**
  * Set field E03ROCTYP using a String value.
  */
  public void setE03ROCTYP(String newvalue)
  {
    fieldE03ROCTYP.setString(newvalue);
  }

  /**
  * Get value of field E03ROCTYP as a String.
  */
  public String getE03ROCTYP()
  {
    return fieldE03ROCTYP.getString();
  }

  /**
  * Set field E03ROCCCY using a String value.
  */
  public void setE03ROCCCY(String newvalue)
  {
    fieldE03ROCCCY.setString(newvalue);
  }

  /**
  * Get value of field E03ROCCCY as a String.
  */
  public String getE03ROCCCY()
  {
    return fieldE03ROCCCY.getString();
  }

  /**
  * Set field E03ROCAMT using a String value.
  */
  public void setE03ROCAMT(String newvalue)
  {
    fieldE03ROCAMT.setString(newvalue);
  }

  /**
  * Get value of field E03ROCAMT as a String.
  */
  public String getE03ROCAMT()
  {
    return fieldE03ROCAMT.getString();
  }

  /**
  * Set numeric field E03ROCAMT using a BigDecimal value.
  */
  public void setE03ROCAMT(BigDecimal newvalue)
  {
    fieldE03ROCAMT.setBigDecimal(newvalue);
  }

  /**
  * Return value of numeric field E03ROCAMT as a BigDecimal.
  */
  public BigDecimal getBigDecimalE03ROCAMT()
  {
    return fieldE03ROCAMT.getBigDecimal();
  }

  /**
  * Set field E03ROCUSE using a String value.
  */
  public void setE03ROCUSE(String newvalue)
  {
    fieldE03ROCUSE.setString(newvalue);
  }

  /**
  * Get value of field E03ROCUSE as a String.
  */
  public String getE03ROCUSE()
  {
    return fieldE03ROCUSE.getString();
  }

  /**
  * Set numeric field E03ROCUSE using a BigDecimal value.
  */
  public void setE03ROCUSE(BigDecimal newvalue)
  {
    fieldE03ROCUSE.setBigDecimal(newvalue);
  }

  /**
  * Return value of numeric field E03ROCUSE as a BigDecimal.
  */
  public BigDecimal getBigDecimalE03ROCUSE()
  {
    return fieldE03ROCUSE.getBigDecimal();
  }

  /**
  * Set field E03ROCBAL using a String value.
  */
  public void setE03ROCBAL(String newvalue)
  {
    fieldE03ROCBAL.setString(newvalue);
  }

  /**
  * Get value of field E03ROCBAL as a String.
  */
  public String getE03ROCBAL()
  {
    return fieldE03ROCBAL.getString();
  }

  /**
  * Set numeric field E03ROCBAL using a BigDecimal value.
  */
  public void setE03ROCBAL(BigDecimal newvalue)
  {
    fieldE03ROCBAL.setBigDecimal(newvalue);
  }

  /**
  * Return value of numeric field E03ROCBAL as a BigDecimal.
  */
  public BigDecimal getBigDecimalE03ROCBAL()
  {
    return fieldE03ROCBAL.getBigDecimal();
  }

  /**
  * Set field E03ROCOD1 using a String value.
  */
  public void setE03ROCOD1(String newvalue)
  {
    fieldE03ROCOD1.setString(newvalue);
  }

  /**
  * Get value of field E03ROCOD1 as a String.
  */
  public String getE03ROCOD1()
  {
    return fieldE03ROCOD1.getString();
  }

  /**
  * Set numeric field E03ROCOD1 using a BigDecimal value.
  */
  public void setE03ROCOD1(BigDecimal newvalue)
  {
    fieldE03ROCOD1.setBigDecimal(newvalue);
  }

  /**
  * Return value of numeric field E03ROCOD1 as a BigDecimal.
  */
  public BigDecimal getBigDecimalE03ROCOD1()
  {
    return fieldE03ROCOD1.getBigDecimal();
  }

  /**
  * Set field E03ROCOD2 using a String value.
  */
  public void setE03ROCOD2(String newvalue)
  {
    fieldE03ROCOD2.setString(newvalue);
  }

  /**
  * Get value of field E03ROCOD2 as a String.
  */
  public String getE03ROCOD2()
  {
    return fieldE03ROCOD2.getString();
  }

  /**
  * Set numeric field E03ROCOD2 using a BigDecimal value.
  */
  public void setE03ROCOD2(BigDecimal newvalue)
  {
    fieldE03ROCOD2.setBigDecimal(newvalue);
  }

  /**
  * Return value of numeric field E03ROCOD2 as a BigDecimal.
  */
  public BigDecimal getBigDecimalE03ROCOD2()
  {
    return fieldE03ROCOD2.getBigDecimal();
  }

  /**
  * Set field E03ROCOD3 using a String value.
  */
  public void setE03ROCOD3(String newvalue)
  {
    fieldE03ROCOD3.setString(newvalue);
  }

  /**
  * Get value of field E03ROCOD3 as a String.
  */
  public String getE03ROCOD3()
  {
    return fieldE03ROCOD3.getString();
  }

  /**
  * Set numeric field E03ROCOD3 using a BigDecimal value.
  */
  public void setE03ROCOD3(BigDecimal newvalue)
  {
    fieldE03ROCOD3.setBigDecimal(newvalue);
  }

  /**
  * Return value of numeric field E03ROCOD3 as a BigDecimal.
  */
  public BigDecimal getBigDecimalE03ROCOD3()
  {
    return fieldE03ROCOD3.getBigDecimal();
  }

  /**
  * Set field E03ROCMD1 using a String value.
  */
  public void setE03ROCMD1(String newvalue)
  {
    fieldE03ROCMD1.setString(newvalue);
  }

  /**
  * Get value of field E03ROCMD1 as a String.
  */
  public String getE03ROCMD1()
  {
    return fieldE03ROCMD1.getString();
  }

  /**
  * Set numeric field E03ROCMD1 using a BigDecimal value.
  */
  public void setE03ROCMD1(BigDecimal newvalue)
  {
    fieldE03ROCMD1.setBigDecimal(newvalue);
  }

  /**
  * Return value of numeric field E03ROCMD1 as a BigDecimal.
  */
  public BigDecimal getBigDecimalE03ROCMD1()
  {
    return fieldE03ROCMD1.getBigDecimal();
  }

  /**
  * Set field E03ROCMD2 using a String value.
  */
  public void setE03ROCMD2(String newvalue)
  {
    fieldE03ROCMD2.setString(newvalue);
  }

  /**
  * Get value of field E03ROCMD2 as a String.
  */
  public String getE03ROCMD2()
  {
    return fieldE03ROCMD2.getString();
  }

  /**
  * Set numeric field E03ROCMD2 using a BigDecimal value.
  */
  public void setE03ROCMD2(BigDecimal newvalue)
  {
    fieldE03ROCMD2.setBigDecimal(newvalue);
  }

  /**
  * Return value of numeric field E03ROCMD2 as a BigDecimal.
  */
  public BigDecimal getBigDecimalE03ROCMD2()
  {
    return fieldE03ROCMD2.getBigDecimal();
  }

  /**
  * Set field E03ROCMD3 using a String value.
  */
  public void setE03ROCMD3(String newvalue)
  {
    fieldE03ROCMD3.setString(newvalue);
  }

  /**
  * Get value of field E03ROCMD3 as a String.
  */
  public String getE03ROCMD3()
  {
    return fieldE03ROCMD3.getString();
  }

  /**
  * Set numeric field E03ROCMD3 using a BigDecimal value.
  */
  public void setE03ROCMD3(BigDecimal newvalue)
  {
    fieldE03ROCMD3.setBigDecimal(newvalue);
  }

  /**
  * Return value of numeric field E03ROCMD3 as a BigDecimal.
  */
  public BigDecimal getBigDecimalE03ROCMD3()
  {
    return fieldE03ROCMD3.getBigDecimal();
  }

  /**
  * Set field E03ENDFLD using a String value.
  */
  public void setE03ENDFLD(String newvalue)
  {
    fieldE03ENDFLD.setString(newvalue);
  }

  /**
  * Get value of field E03ENDFLD as a String.
  */
  public String getE03ENDFLD()
  {
    return fieldE03ENDFLD.getString();
  }

}