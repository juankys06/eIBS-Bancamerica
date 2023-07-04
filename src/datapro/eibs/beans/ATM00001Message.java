package datapro.eibs.beans;

import datapro.eibs.sockets.*;
import java.io.*;
import java.math.*;

import java.util.*;

/**
* Class generated by AS/400 CRTCLASS command from ATM00001 physical file definition.
* 
* File level identifier is 1041110143334.
* Record format level identifier is 2EEA62DB9BE68.
*/

public class ATM00001Message extends MessageRecord
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
                                     "H01FLGONL",
                                     "H01FLGREV",
                                     "H01PRCTYP",
                                     "H01ERRC01",
                                     "H01ERRC02",
                                     "H01LOTIME",
                                     "H01CRDACC",
                                     "H01TERSQN",
                                     "E01DAT"
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
                                   "H01FLGONL",
                                   "H01FLGREV",
                                   "H01PRCTYP",
                                   "H01ERRC01",
                                   "H01ERRC02",
                                   "H01LOTIME",
                                   "H01CRDACC",
                                   "H01TERSQN",
                                   "E01DAT"
                                 };
  final static String fid = "1041110143334";
  final static String rid = "2EEA62DB9BE68";
  final static String fmtname = "ATM00001";
  final int FIELDCOUNT = 18;
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
  private CharacterField fieldH01FLGONL = null;
  private CharacterField fieldH01FLGREV = null;
  private CharacterField fieldH01PRCTYP = null;
  private DecimalField fieldH01ERRC01 = null;
  private DecimalField fieldH01ERRC02 = null;
  private DecimalField fieldH01LOTIME = null;
  private DecimalField fieldH01CRDACC = null;
  private DecimalField fieldH01TERSQN = null;
  private CharacterField fieldE01DAT = null;

  /**
  * Constructor for ATM00001Message.
  */
  public ATM00001Message()
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
    recordsize = 1114;
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
    fields[9] = fieldH01FLGONL =
    new CharacterField(message, HEADERSIZE + 42, 1, "H01FLGONL");
    fields[10] = fieldH01FLGREV =
    new CharacterField(message, HEADERSIZE + 43, 1, "H01FLGREV");
    fields[11] = fieldH01PRCTYP =
    new CharacterField(message, HEADERSIZE + 44, 6, "H01PRCTYP");
    fields[12] = fieldH01ERRC01 =
    new DecimalField(message, HEADERSIZE + 50, 5, 0, "H01ERRC01");
    fields[13] = fieldH01ERRC02 =
    new DecimalField(message, HEADERSIZE + 55, 5, 0, "H01ERRC02");
    fields[14] = fieldH01LOTIME =
    new DecimalField(message, HEADERSIZE + 60, 7, 0, "H01LOTIME");
    fields[15] = fieldH01CRDACC =
    new DecimalField(message, HEADERSIZE + 67, 16, 0, "H01CRDACC");
    fields[16] = fieldH01TERSQN =
    new DecimalField(message, HEADERSIZE + 83, 7, 0, "H01TERSQN");
    fields[17] = fieldE01DAT =
    new CharacterField(message, HEADERSIZE + 90, 1024, "E01DAT");

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
  * Set field H01FLGONL using a String value.
  */
  public void setH01FLGONL(String newvalue)
  {
    fieldH01FLGONL.setString(newvalue);
  }

  /**
  * Get value of field H01FLGONL as a String.
  */
  public String getH01FLGONL()
  {
    return fieldH01FLGONL.getString();
  }

  /**
  * Set field H01FLGREV using a String value.
  */
  public void setH01FLGREV(String newvalue)
  {
    fieldH01FLGREV.setString(newvalue);
  }

  /**
  * Get value of field H01FLGREV as a String.
  */
  public String getH01FLGREV()
  {
    return fieldH01FLGREV.getString();
  }

  /**
  * Set field H01PRCTYP using a String value.
  */
  public void setH01PRCTYP(String newvalue)
  {
    fieldH01PRCTYP.setString(newvalue);
  }

  /**
  * Get value of field H01PRCTYP as a String.
  */
  public String getH01PRCTYP()
  {
    return fieldH01PRCTYP.getString();
  }

  /**
  * Set field H01ERRC01 using a String value.
  */
  public void setH01ERRC01(String newvalue)
  {
    fieldH01ERRC01.setString(newvalue);
  }

  /**
  * Get value of field H01ERRC01 as a String.
  */
  public String getH01ERRC01()
  {
    return fieldH01ERRC01.getString();
  }

  /**
  * Set numeric field H01ERRC01 using a BigDecimal value.
  */
  public void setH01ERRC01(BigDecimal newvalue)
  {
    fieldH01ERRC01.setBigDecimal(newvalue);
  }

  /**
  * Return value of numeric field H01ERRC01 as a BigDecimal.
  */
  public BigDecimal getBigDecimalH01ERRC01()
  {
    return fieldH01ERRC01.getBigDecimal();
  }

  /**
  * Set field H01ERRC02 using a String value.
  */
  public void setH01ERRC02(String newvalue)
  {
    fieldH01ERRC02.setString(newvalue);
  }

  /**
  * Get value of field H01ERRC02 as a String.
  */
  public String getH01ERRC02()
  {
    return fieldH01ERRC02.getString();
  }

  /**
  * Set numeric field H01ERRC02 using a BigDecimal value.
  */
  public void setH01ERRC02(BigDecimal newvalue)
  {
    fieldH01ERRC02.setBigDecimal(newvalue);
  }

  /**
  * Return value of numeric field H01ERRC02 as a BigDecimal.
  */
  public BigDecimal getBigDecimalH01ERRC02()
  {
    return fieldH01ERRC02.getBigDecimal();
  }

  /**
  * Set field H01LOTIME using a String value.
  */
  public void setH01LOTIME(String newvalue)
  {
    fieldH01LOTIME.setString(newvalue);
  }

  /**
  * Get value of field H01LOTIME as a String.
  */
  public String getH01LOTIME()
  {
    return fieldH01LOTIME.getString();
  }

  /**
  * Set numeric field H01LOTIME using a BigDecimal value.
  */
  public void setH01LOTIME(BigDecimal newvalue)
  {
    fieldH01LOTIME.setBigDecimal(newvalue);
  }

  /**
  * Return value of numeric field H01LOTIME as a BigDecimal.
  */
  public BigDecimal getBigDecimalH01LOTIME()
  {
    return fieldH01LOTIME.getBigDecimal();
  }

  /**
  * Set field H01CRDACC using a String value.
  */
  public void setH01CRDACC(String newvalue)
  {
    fieldH01CRDACC.setString(newvalue);
  }

  /**
  * Get value of field H01CRDACC as a String.
  */
  public String getH01CRDACC()
  {
    return fieldH01CRDACC.getString();
  }

  /**
  * Set numeric field H01CRDACC using a BigDecimal value.
  */
  public void setH01CRDACC(BigDecimal newvalue)
  {
    fieldH01CRDACC.setBigDecimal(newvalue);
  }

  /**
  * Return value of numeric field H01CRDACC as a BigDecimal.
  */
  public BigDecimal getBigDecimalH01CRDACC()
  {
    return fieldH01CRDACC.getBigDecimal();
  }

  /**
  * Set field H01TERSQN using a String value.
  */
  public void setH01TERSQN(String newvalue)
  {
    fieldH01TERSQN.setString(newvalue);
  }

  /**
  * Get value of field H01TERSQN as a String.
  */
  public String getH01TERSQN()
  {
    return fieldH01TERSQN.getString();
  }

  /**
  * Set numeric field H01TERSQN using a BigDecimal value.
  */
  public void setH01TERSQN(BigDecimal newvalue)
  {
    fieldH01TERSQN.setBigDecimal(newvalue);
  }

  /**
  * Return value of numeric field H01TERSQN as a BigDecimal.
  */
  public BigDecimal getBigDecimalH01TERSQN()
  {
    return fieldH01TERSQN.getBigDecimal();
  }

  /**
  * Set field E01DAT using a String value.
  */
  public void setE01DAT(String newvalue)
  {
    fieldE01DAT.setString(newvalue);
  }

  /**
  * Get value of field E01DAT as a String.
  */
  public String getE01DAT()
  {
    return fieldE01DAT.getString();
  }

}
