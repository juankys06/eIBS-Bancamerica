package datapro.eibs.beans;

import datapro.eibs.sockets.*;
import java.io.*;
import java.math.*;

import java.util.*;

/**
* Class generated by AS/400 CRTCLASS command from EATM01002 physical file definition.
* 
* File level identifier is 1031118172328.
* Record format level identifier is 3687848E59AE9.
*/

public class EATM01002Message extends MessageRecord
{
  final static String fldnames[] = {
                                     "H02USERID",
                                     "H02PROGRM",
                                     "H02TIMSYS",
                                     "H02SCRCOD",
                                     "H02OPECOD",
                                     "H02FLGMAS",
                                     "H02FLGWK1",
                                     "H02FLGWK2",
                                     "H02FLGWK3",
                                     "E02ATMCUN",
                                     "E02ATMPAN",
                                     "E02ATMTYP",
                                     "E02ATMODM",
                                     "E02ATMODD",
                                     "E02ATMODY",
                                     "E02ATMEXM",
                                     "E02ATMEXY",
                                     "E02ATMNAM"
                                   };
  final static String tnames[] = {
                                   "H02USERID",
                                   "H02PROGRM",
                                   "H02TIMSYS",
                                   "H02SCRCOD",
                                   "H02OPECOD",
                                   "H02FLGMAS",
                                   "H02FLGWK1",
                                   "H02FLGWK2",
                                   "H02FLGWK3",
                                   "E02ATMCUN",
                                   "E02ATMPAN",
                                   "E02ATMTYP",
                                   "E02ATMODM",
                                   "E02ATMODD",
                                   "E02ATMODY",
                                   "E02ATMEXM",
                                   "E02ATMEXY",
                                   "E02ATMNAM"
                                 };
  final static String fid = "1031118172328";
  final static String rid = "3687848E59AE9";
  final static String fmtname = "EATM01002";
  final int FIELDCOUNT = 18;
  private static Hashtable tlookup = new Hashtable();
  private CharacterField fieldH02USERID = null;
  private CharacterField fieldH02PROGRM = null;
  private CharacterField fieldH02TIMSYS = null;
  private CharacterField fieldH02SCRCOD = null;
  private CharacterField fieldH02OPECOD = null;
  private CharacterField fieldH02FLGMAS = null;
  private CharacterField fieldH02FLGWK1 = null;
  private CharacterField fieldH02FLGWK2 = null;
  private CharacterField fieldH02FLGWK3 = null;
  private DecimalField fieldE02ATMCUN = null;
  private DecimalField fieldE02ATMPAN = null;
  private CharacterField fieldE02ATMTYP = null;
  private DecimalField fieldE02ATMODM = null;
  private DecimalField fieldE02ATMODD = null;
  private DecimalField fieldE02ATMODY = null;
  private DecimalField fieldE02ATMEXM = null;
  private DecimalField fieldE02ATMEXY = null;
  private CharacterField fieldE02ATMNAM = null;

  /**
  * Constructor for EATM01002Message.
  */
  public EATM01002Message()
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
    recordsize = 137;
    fileid = fid;
    recordid = rid;
    message = new byte[getByteLength()];
    formatname = fmtname;
    fieldnames = fldnames;
    tagnames = tnames;
    fields = new MessageField[FIELDCOUNT];

    fields[0] = fieldH02USERID =
    new CharacterField(message, HEADERSIZE + 0, 10, "H02USERID");
    fields[1] = fieldH02PROGRM =
    new CharacterField(message, HEADERSIZE + 10, 10, "H02PROGRM");
    fields[2] = fieldH02TIMSYS =
    new CharacterField(message, HEADERSIZE + 20, 12, "H02TIMSYS");
    fields[3] = fieldH02SCRCOD =
    new CharacterField(message, HEADERSIZE + 32, 2, "H02SCRCOD");
    fields[4] = fieldH02OPECOD =
    new CharacterField(message, HEADERSIZE + 34, 4, "H02OPECOD");
    fields[5] = fieldH02FLGMAS =
    new CharacterField(message, HEADERSIZE + 38, 1, "H02FLGMAS");
    fields[6] = fieldH02FLGWK1 =
    new CharacterField(message, HEADERSIZE + 39, 1, "H02FLGWK1");
    fields[7] = fieldH02FLGWK2 =
    new CharacterField(message, HEADERSIZE + 40, 1, "H02FLGWK2");
    fields[8] = fieldH02FLGWK3 =
    new CharacterField(message, HEADERSIZE + 41, 1, "H02FLGWK3");
    fields[9] = fieldE02ATMCUN =
    new DecimalField(message, HEADERSIZE + 42, 10, 0, "E02ATMCUN");
    fields[10] = fieldE02ATMPAN =
    new DecimalField(message, HEADERSIZE + 52, 20, 0, "E02ATMPAN");
    fields[11] = fieldE02ATMTYP =
    new CharacterField(message, HEADERSIZE + 72, 2, "E02ATMTYP");
    fields[12] = fieldE02ATMODM =
    new DecimalField(message, HEADERSIZE + 74, 20, 0, "E02ATMODM");
    fields[13] = fieldE02ATMODD =
    new DecimalField(message, HEADERSIZE + 94, 3, 0, "E02ATMODD");
    fields[14] = fieldE02ATMODY =
    new DecimalField(message, HEADERSIZE + 97, 3, 0, "E02ATMODY");
    fields[15] = fieldE02ATMEXM =
    new DecimalField(message, HEADERSIZE + 100, 3, 0, "E02ATMEXM");
    fields[16] = fieldE02ATMEXY =
    new DecimalField(message, HEADERSIZE + 103, 3, 0, "E02ATMEXY");
    fields[17] = fieldE02ATMNAM =
    new CharacterField(message, HEADERSIZE + 106, 31, "E02ATMNAM");

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
  * Set field H02USERID using a String value.
  */
  public void setH02USERID(String newvalue)
  {
    fieldH02USERID.setString(newvalue);
  }

  /**
  * Get value of field H02USERID as a String.
  */
  public String getH02USERID()
  {
    return fieldH02USERID.getString();
  }

  /**
  * Set field H02PROGRM using a String value.
  */
  public void setH02PROGRM(String newvalue)
  {
    fieldH02PROGRM.setString(newvalue);
  }

  /**
  * Get value of field H02PROGRM as a String.
  */
  public String getH02PROGRM()
  {
    return fieldH02PROGRM.getString();
  }

  /**
  * Set field H02TIMSYS using a String value.
  */
  public void setH02TIMSYS(String newvalue)
  {
    fieldH02TIMSYS.setString(newvalue);
  }

  /**
  * Get value of field H02TIMSYS as a String.
  */
  public String getH02TIMSYS()
  {
    return fieldH02TIMSYS.getString();
  }

  /**
  * Set field H02SCRCOD using a String value.
  */
  public void setH02SCRCOD(String newvalue)
  {
    fieldH02SCRCOD.setString(newvalue);
  }

  /**
  * Get value of field H02SCRCOD as a String.
  */
  public String getH02SCRCOD()
  {
    return fieldH02SCRCOD.getString();
  }

  /**
  * Set field H02OPECOD using a String value.
  */
  public void setH02OPECOD(String newvalue)
  {
    fieldH02OPECOD.setString(newvalue);
  }

  /**
  * Get value of field H02OPECOD as a String.
  */
  public String getH02OPECOD()
  {
    return fieldH02OPECOD.getString();
  }

  /**
  * Set field H02FLGMAS using a String value.
  */
  public void setH02FLGMAS(String newvalue)
  {
    fieldH02FLGMAS.setString(newvalue);
  }

  /**
  * Get value of field H02FLGMAS as a String.
  */
  public String getH02FLGMAS()
  {
    return fieldH02FLGMAS.getString();
  }

  /**
  * Set field H02FLGWK1 using a String value.
  */
  public void setH02FLGWK1(String newvalue)
  {
    fieldH02FLGWK1.setString(newvalue);
  }

  /**
  * Get value of field H02FLGWK1 as a String.
  */
  public String getH02FLGWK1()
  {
    return fieldH02FLGWK1.getString();
  }

  /**
  * Set field H02FLGWK2 using a String value.
  */
  public void setH02FLGWK2(String newvalue)
  {
    fieldH02FLGWK2.setString(newvalue);
  }

  /**
  * Get value of field H02FLGWK2 as a String.
  */
  public String getH02FLGWK2()
  {
    return fieldH02FLGWK2.getString();
  }

  /**
  * Set field H02FLGWK3 using a String value.
  */
  public void setH02FLGWK3(String newvalue)
  {
    fieldH02FLGWK3.setString(newvalue);
  }

  /**
  * Get value of field H02FLGWK3 as a String.
  */
  public String getH02FLGWK3()
  {
    return fieldH02FLGWK3.getString();
  }

  /**
  * Set field E02ATMCUN using a String value.
  */
  public void setE02ATMCUN(String newvalue)
  {
    fieldE02ATMCUN.setString(newvalue);
  }

  /**
  * Get value of field E02ATMCUN as a String.
  */
  public String getE02ATMCUN()
  {
    return fieldE02ATMCUN.getString();
  }

  /**
  * Set numeric field E02ATMCUN using a BigDecimal value.
  */
  public void setE02ATMCUN(BigDecimal newvalue)
  {
    fieldE02ATMCUN.setBigDecimal(newvalue);
  }

  /**
  * Return value of numeric field E02ATMCUN as a BigDecimal.
  */
  public BigDecimal getBigDecimalE02ATMCUN()
  {
    return fieldE02ATMCUN.getBigDecimal();
  }

  /**
  * Set field E02ATMPAN using a String value.
  */
  public void setE02ATMPAN(String newvalue)
  {
    fieldE02ATMPAN.setString(newvalue);
  }

  /**
  * Get value of field E02ATMPAN as a String.
  */
  public String getE02ATMPAN()
  {
    return fieldE02ATMPAN.getString();
  }

  /**
  * Set numeric field E02ATMPAN using a BigDecimal value.
  */
  public void setE02ATMPAN(BigDecimal newvalue)
  {
    fieldE02ATMPAN.setBigDecimal(newvalue);
  }

  /**
  * Return value of numeric field E02ATMPAN as a BigDecimal.
  */
  public BigDecimal getBigDecimalE02ATMPAN()
  {
    return fieldE02ATMPAN.getBigDecimal();
  }

  /**
  * Set field E02ATMTYP using a String value.
  */
  public void setE02ATMTYP(String newvalue)
  {
    fieldE02ATMTYP.setString(newvalue);
  }

  /**
  * Get value of field E02ATMTYP as a String.
  */
  public String getE02ATMTYP()
  {
    return fieldE02ATMTYP.getString();
  }

  /**
  * Set field E02ATMODM using a String value.
  */
  public void setE02ATMODM(String newvalue)
  {
    fieldE02ATMODM.setString(newvalue);
  }

  /**
  * Get value of field E02ATMODM as a String.
  */
  public String getE02ATMODM()
  {
    return fieldE02ATMODM.getString();
  }

  /**
  * Set numeric field E02ATMODM using a BigDecimal value.
  */
  public void setE02ATMODM(BigDecimal newvalue)
  {
    fieldE02ATMODM.setBigDecimal(newvalue);
  }

  /**
  * Return value of numeric field E02ATMODM as a BigDecimal.
  */
  public BigDecimal getBigDecimalE02ATMODM()
  {
    return fieldE02ATMODM.getBigDecimal();
  }

  /**
  * Set field E02ATMODD using a String value.
  */
  public void setE02ATMODD(String newvalue)
  {
    fieldE02ATMODD.setString(newvalue);
  }

  /**
  * Get value of field E02ATMODD as a String.
  */
  public String getE02ATMODD()
  {
    return fieldE02ATMODD.getString();
  }

  /**
  * Set numeric field E02ATMODD using a BigDecimal value.
  */
  public void setE02ATMODD(BigDecimal newvalue)
  {
    fieldE02ATMODD.setBigDecimal(newvalue);
  }

  /**
  * Return value of numeric field E02ATMODD as a BigDecimal.
  */
  public BigDecimal getBigDecimalE02ATMODD()
  {
    return fieldE02ATMODD.getBigDecimal();
  }

  /**
  * Set field E02ATMODY using a String value.
  */
  public void setE02ATMODY(String newvalue)
  {
    fieldE02ATMODY.setString(newvalue);
  }

  /**
  * Get value of field E02ATMODY as a String.
  */
  public String getE02ATMODY()
  {
    return fieldE02ATMODY.getString();
  }

  /**
  * Set numeric field E02ATMODY using a BigDecimal value.
  */
  public void setE02ATMODY(BigDecimal newvalue)
  {
    fieldE02ATMODY.setBigDecimal(newvalue);
  }

  /**
  * Return value of numeric field E02ATMODY as a BigDecimal.
  */
  public BigDecimal getBigDecimalE02ATMODY()
  {
    return fieldE02ATMODY.getBigDecimal();
  }

  /**
  * Set field E02ATMEXM using a String value.
  */
  public void setE02ATMEXM(String newvalue)
  {
    fieldE02ATMEXM.setString(newvalue);
  }

  /**
  * Get value of field E02ATMEXM as a String.
  */
  public String getE02ATMEXM()
  {
    return fieldE02ATMEXM.getString();
  }

  /**
  * Set numeric field E02ATMEXM using a BigDecimal value.
  */
  public void setE02ATMEXM(BigDecimal newvalue)
  {
    fieldE02ATMEXM.setBigDecimal(newvalue);
  }

  /**
  * Return value of numeric field E02ATMEXM as a BigDecimal.
  */
  public BigDecimal getBigDecimalE02ATMEXM()
  {
    return fieldE02ATMEXM.getBigDecimal();
  }

  /**
  * Set field E02ATMEXY using a String value.
  */
  public void setE02ATMEXY(String newvalue)
  {
    fieldE02ATMEXY.setString(newvalue);
  }

  /**
  * Get value of field E02ATMEXY as a String.
  */
  public String getE02ATMEXY()
  {
    return fieldE02ATMEXY.getString();
  }

  /**
  * Set numeric field E02ATMEXY using a BigDecimal value.
  */
  public void setE02ATMEXY(BigDecimal newvalue)
  {
    fieldE02ATMEXY.setBigDecimal(newvalue);
  }

  /**
  * Return value of numeric field E02ATMEXY as a BigDecimal.
  */
  public BigDecimal getBigDecimalE02ATMEXY()
  {
    return fieldE02ATMEXY.getBigDecimal();
  }

  /**
  * Set field E02ATMNAM using a String value.
  */
  public void setE02ATMNAM(String newvalue)
  {
    fieldE02ATMNAM.setString(newvalue);
  }

  /**
  * Get value of field E02ATMNAM as a String.
  */
  public String getE02ATMNAM()
  {
    return fieldE02ATMNAM.getString();
  }

}
