package datapro.eibs.beans;

import datapro.eibs.sockets.*;
import java.io.*;
import java.math.*;

import java.util.*;

/**
* Class generated by AS/400 CRTCLASS command from EGC049007 physical file definition.
* 
* File level identifier is 1030121191925.
* Record format level identifier is 3A9EAA5EF9673.
*/

public class EGC049007Message extends MessageRecord
{
  final static String fldnames[] = {
                                     "H07USERID",
                                     "H07PROGRM",
                                     "H07TIMSYS",
                                     "H07SCRCOD",
                                     "H07OPECOD",
                                     "H07FLGMAS",
                                     "H07FLGWK1",
                                     "H07FLGWK2",
                                     "H07FLGWK3",
                                     "E07ACCNUM",
                                     "E07BNKNUM",
                                     "E07BRNNUM",
                                     "E07APCCDE",
                                     "E07CUSCUN",
                                     "E07CCYCDE",
                                     "E07ACCTYP",
                                     "E07PROCDE",
                                     "E07CUSNA1"
                                   };
  final static String tnames[] = {
                                   "H07USERID",
                                   "H07PROGRM",
                                   "H07TIMSYS",
                                   "H07SCRCOD",
                                   "H07OPECOD",
                                   "H07FLGMAS",
                                   "H07FLGWK1",
                                   "H07FLGWK2",
                                   "H07FLGWK3",
                                   "E07ACCNUM",
                                   "E07BNKNUM",
                                   "E07BRNNUM",
                                   "E07APCCDE",
                                   "E07CUSCUN",
                                   "E07CCYCDE",
                                   "E07ACCTYP",
                                   "E07PROCDE",
                                   "E07CUSNA1"
                                 };
  final static String fid = "1030121191925";
  final static String rid = "3A9EAA5EF9673";
  final static String fmtname = "EGC049007";
  final int FIELDCOUNT = 18;
  private static Hashtable tlookup = new Hashtable();
  private CharacterField fieldH07USERID = null;
  private CharacterField fieldH07PROGRM = null;
  private CharacterField fieldH07TIMSYS = null;
  private CharacterField fieldH07SCRCOD = null;
  private CharacterField fieldH07OPECOD = null;
  private CharacterField fieldH07FLGMAS = null;
  private CharacterField fieldH07FLGWK1 = null;
  private CharacterField fieldH07FLGWK2 = null;
  private CharacterField fieldH07FLGWK3 = null;
  private DecimalField fieldE07ACCNUM = null;
  private CharacterField fieldE07BNKNUM = null;
  private DecimalField fieldE07BRNNUM = null;
  private CharacterField fieldE07APCCDE = null;
  private DecimalField fieldE07CUSCUN = null;
  private CharacterField fieldE07CCYCDE = null;
  private CharacterField fieldE07ACCTYP = null;
  private CharacterField fieldE07PROCDE = null;
  private CharacterField fieldE07CUSNA1 = null;

  /**
  * Constructor for EGC049007Message.
  */
  public EGC049007Message()
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
    recordsize = 129;
    fileid = fid;
    recordid = rid;
    message = new byte[getByteLength()];
    formatname = fmtname;
    fieldnames = fldnames;
    tagnames = tnames;
    fields = new MessageField[FIELDCOUNT];

    fields[0] = fieldH07USERID =
    new CharacterField(message, HEADERSIZE + 0, 10, "H07USERID");
    fields[1] = fieldH07PROGRM =
    new CharacterField(message, HEADERSIZE + 10, 10, "H07PROGRM");
    fields[2] = fieldH07TIMSYS =
    new CharacterField(message, HEADERSIZE + 20, 12, "H07TIMSYS");
    fields[3] = fieldH07SCRCOD =
    new CharacterField(message, HEADERSIZE + 32, 2, "H07SCRCOD");
    fields[4] = fieldH07OPECOD =
    new CharacterField(message, HEADERSIZE + 34, 4, "H07OPECOD");
    fields[5] = fieldH07FLGMAS =
    new CharacterField(message, HEADERSIZE + 38, 1, "H07FLGMAS");
    fields[6] = fieldH07FLGWK1 =
    new CharacterField(message, HEADERSIZE + 39, 1, "H07FLGWK1");
    fields[7] = fieldH07FLGWK2 =
    new CharacterField(message, HEADERSIZE + 40, 1, "H07FLGWK2");
    fields[8] = fieldH07FLGWK3 =
    new CharacterField(message, HEADERSIZE + 41, 1, "H07FLGWK3");
    fields[9] = fieldE07ACCNUM =
    new DecimalField(message, HEADERSIZE + 42, 13, 0, "E07ACCNUM");
    fields[10] = fieldE07BNKNUM =
    new CharacterField(message, HEADERSIZE + 55, 2, "E07BNKNUM");
    fields[11] = fieldE07BRNNUM =
    new DecimalField(message, HEADERSIZE + 57, 4, 0, "E07BRNNUM");
    fields[12] = fieldE07APCCDE =
    new CharacterField(message, HEADERSIZE + 61, 2, "E07APCCDE");
    fields[13] = fieldE07CUSCUN =
    new DecimalField(message, HEADERSIZE + 63, 10, 0, "E07CUSCUN");
    fields[14] = fieldE07CCYCDE =
    new CharacterField(message, HEADERSIZE + 73, 3, "E07CCYCDE");
    fields[15] = fieldE07ACCTYP =
    new CharacterField(message, HEADERSIZE + 76, 4, "E07ACCTYP");
    fields[16] = fieldE07PROCDE =
    new CharacterField(message, HEADERSIZE + 80, 4, "E07PROCDE");
    fields[17] = fieldE07CUSNA1 =
    new CharacterField(message, HEADERSIZE + 84, 45, "E07CUSNA1");

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
  * Set field H07USERID using a String value.
  */
  public void setH07USERID(String newvalue)
  {
    fieldH07USERID.setString(newvalue);
  }

  /**
  * Get value of field H07USERID as a String.
  */
  public String getH07USERID()
  {
    return fieldH07USERID.getString();
  }

  /**
  * Set field H07PROGRM using a String value.
  */
  public void setH07PROGRM(String newvalue)
  {
    fieldH07PROGRM.setString(newvalue);
  }

  /**
  * Get value of field H07PROGRM as a String.
  */
  public String getH07PROGRM()
  {
    return fieldH07PROGRM.getString();
  }

  /**
  * Set field H07TIMSYS using a String value.
  */
  public void setH07TIMSYS(String newvalue)
  {
    fieldH07TIMSYS.setString(newvalue);
  }

  /**
  * Get value of field H07TIMSYS as a String.
  */
  public String getH07TIMSYS()
  {
    return fieldH07TIMSYS.getString();
  }

  /**
  * Set field H07SCRCOD using a String value.
  */
  public void setH07SCRCOD(String newvalue)
  {
    fieldH07SCRCOD.setString(newvalue);
  }

  /**
  * Get value of field H07SCRCOD as a String.
  */
  public String getH07SCRCOD()
  {
    return fieldH07SCRCOD.getString();
  }

  /**
  * Set field H07OPECOD using a String value.
  */
  public void setH07OPECOD(String newvalue)
  {
    fieldH07OPECOD.setString(newvalue);
  }

  /**
  * Get value of field H07OPECOD as a String.
  */
  public String getH07OPECOD()
  {
    return fieldH07OPECOD.getString();
  }

  /**
  * Set field H07FLGMAS using a String value.
  */
  public void setH07FLGMAS(String newvalue)
  {
    fieldH07FLGMAS.setString(newvalue);
  }

  /**
  * Get value of field H07FLGMAS as a String.
  */
  public String getH07FLGMAS()
  {
    return fieldH07FLGMAS.getString();
  }

  /**
  * Set field H07FLGWK1 using a String value.
  */
  public void setH07FLGWK1(String newvalue)
  {
    fieldH07FLGWK1.setString(newvalue);
  }

  /**
  * Get value of field H07FLGWK1 as a String.
  */
  public String getH07FLGWK1()
  {
    return fieldH07FLGWK1.getString();
  }

  /**
  * Set field H07FLGWK2 using a String value.
  */
  public void setH07FLGWK2(String newvalue)
  {
    fieldH07FLGWK2.setString(newvalue);
  }

  /**
  * Get value of field H07FLGWK2 as a String.
  */
  public String getH07FLGWK2()
  {
    return fieldH07FLGWK2.getString();
  }

  /**
  * Set field H07FLGWK3 using a String value.
  */
  public void setH07FLGWK3(String newvalue)
  {
    fieldH07FLGWK3.setString(newvalue);
  }

  /**
  * Get value of field H07FLGWK3 as a String.
  */
  public String getH07FLGWK3()
  {
    return fieldH07FLGWK3.getString();
  }

  /**
  * Set field E07ACCNUM using a String value.
  */
  public void setE07ACCNUM(String newvalue)
  {
    fieldE07ACCNUM.setString(newvalue);
  }

  /**
  * Get value of field E07ACCNUM as a String.
  */
  public String getE07ACCNUM()
  {
    return fieldE07ACCNUM.getString();
  }

  /**
  * Set numeric field E07ACCNUM using a BigDecimal value.
  */
  public void setE07ACCNUM(BigDecimal newvalue)
  {
    fieldE07ACCNUM.setBigDecimal(newvalue);
  }

  /**
  * Return value of numeric field E07ACCNUM as a BigDecimal.
  */
  public BigDecimal getBigDecimalE07ACCNUM()
  {
    return fieldE07ACCNUM.getBigDecimal();
  }

  /**
  * Set field E07BNKNUM using a String value.
  */
  public void setE07BNKNUM(String newvalue)
  {
    fieldE07BNKNUM.setString(newvalue);
  }

  /**
  * Get value of field E07BNKNUM as a String.
  */
  public String getE07BNKNUM()
  {
    return fieldE07BNKNUM.getString();
  }

  /**
  * Set field E07BRNNUM using a String value.
  */
  public void setE07BRNNUM(String newvalue)
  {
    fieldE07BRNNUM.setString(newvalue);
  }

  /**
  * Get value of field E07BRNNUM as a String.
  */
  public String getE07BRNNUM()
  {
    return fieldE07BRNNUM.getString();
  }

  /**
  * Set numeric field E07BRNNUM using a BigDecimal value.
  */
  public void setE07BRNNUM(BigDecimal newvalue)
  {
    fieldE07BRNNUM.setBigDecimal(newvalue);
  }

  /**
  * Return value of numeric field E07BRNNUM as a BigDecimal.
  */
  public BigDecimal getBigDecimalE07BRNNUM()
  {
    return fieldE07BRNNUM.getBigDecimal();
  }

  /**
  * Set field E07APCCDE using a String value.
  */
  public void setE07APCCDE(String newvalue)
  {
    fieldE07APCCDE.setString(newvalue);
  }

  /**
  * Get value of field E07APCCDE as a String.
  */
  public String getE07APCCDE()
  {
    return fieldE07APCCDE.getString();
  }

  /**
  * Set field E07CUSCUN using a String value.
  */
  public void setE07CUSCUN(String newvalue)
  {
    fieldE07CUSCUN.setString(newvalue);
  }

  /**
  * Get value of field E07CUSCUN as a String.
  */
  public String getE07CUSCUN()
  {
    return fieldE07CUSCUN.getString();
  }

  /**
  * Set numeric field E07CUSCUN using a BigDecimal value.
  */
  public void setE07CUSCUN(BigDecimal newvalue)
  {
    fieldE07CUSCUN.setBigDecimal(newvalue);
  }

  /**
  * Return value of numeric field E07CUSCUN as a BigDecimal.
  */
  public BigDecimal getBigDecimalE07CUSCUN()
  {
    return fieldE07CUSCUN.getBigDecimal();
  }

  /**
  * Set field E07CCYCDE using a String value.
  */
  public void setE07CCYCDE(String newvalue)
  {
    fieldE07CCYCDE.setString(newvalue);
  }

  /**
  * Get value of field E07CCYCDE as a String.
  */
  public String getE07CCYCDE()
  {
    return fieldE07CCYCDE.getString();
  }

  /**
  * Set field E07ACCTYP using a String value.
  */
  public void setE07ACCTYP(String newvalue)
  {
    fieldE07ACCTYP.setString(newvalue);
  }

  /**
  * Get value of field E07ACCTYP as a String.
  */
  public String getE07ACCTYP()
  {
    return fieldE07ACCTYP.getString();
  }

  /**
  * Set field E07PROCDE using a String value.
  */
  public void setE07PROCDE(String newvalue)
  {
    fieldE07PROCDE.setString(newvalue);
  }

  /**
  * Get value of field E07PROCDE as a String.
  */
  public String getE07PROCDE()
  {
    return fieldE07PROCDE.getString();
  }

  /**
  * Set field E07CUSNA1 using a String value.
  */
  public void setE07CUSNA1(String newvalue)
  {
    fieldE07CUSNA1.setString(newvalue);
  }

  /**
  * Get value of field E07CUSNA1 as a String.
  */
  public String getE07CUSNA1()
  {
    return fieldE07CUSNA1.getString();
  }

}