package datapro.eibs.beans; 

import datapro.eibs.sockets.*;
import java.io.*;
import java.math.*;

import java.util.*;

/**
* Class generated by AS/400 CRTCLASS command from EVL001001 physical file definition.
* 
* File level identifier is 1060815162353.
* Record format level identifier is 45336750D7DDE.
*/

public class EVL001001Message extends MessageRecord
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
                                     "E01REQBNK",
                                     "E01REQBNN",
                                     "E01REQBRN",
                                     "E01REQBRM",
                                     "E01REQTIP",
                                     "E01REQTIN",
                                     "E01REQSUB",
                                     "E01REQSUN",
                                     "E01REQREF",
                                     "E01REQRQT",
                                     "E01REQCD1",
                                     "E01REQCD2",
                                     "E01REQCD3",
                                     "E01REQCRT",
                                     "E01REQCRU"
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
                                   "E01REQBNK",
                                   "E01REQBNN",
                                   "E01REQBRN",
                                   "E01REQBRM",
                                   "E01REQTIP",
                                   "E01REQTIN",
                                   "E01REQSUB",
                                   "E01REQSUN",
                                   "E01REQREF",
                                   "E01REQRQT",
                                   "E01REQCD1",
                                   "E01REQCD2",
                                   "E01REQCD3",
                                   "E01REQCRT",
                                   "E01REQCRU"
                                 };
  final static String fid = "1060815162353";
  final static String rid = "45336750D7DDE";
  final static String fmtname = "EVL001001";
  final int FIELDCOUNT = 24;
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
  private CharacterField fieldE01REQBNK = null;
  private CharacterField fieldE01REQBNN = null;
  private DecimalField fieldE01REQBRN = null;
  private CharacterField fieldE01REQBRM = null;
  private CharacterField fieldE01REQTIP = null;
  private CharacterField fieldE01REQTIN = null;
  private CharacterField fieldE01REQSUB = null;
  private CharacterField fieldE01REQSUN = null;
  private DecimalField fieldE01REQREF = null;
  private DecimalField fieldE01REQRQT = null;
  private DecimalField fieldE01REQCD1 = null;
  private DecimalField fieldE01REQCD2 = null;
  private DecimalField fieldE01REQCD3 = null;
  private DecimalField fieldE01REQCRT = null;
  private CharacterField fieldE01REQCRU = null;

  /**
  * Constructor for EVL001001Message.
  */
  public EVL001001Message()
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
    recordsize = 243;
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
    fields[9] = fieldE01REQBNK =
    new CharacterField(message, HEADERSIZE + 42, 2, "E01REQBNK");
    fields[10] = fieldE01REQBNN =
    new CharacterField(message, HEADERSIZE + 44, 35, "E01REQBNN");
    fields[11] = fieldE01REQBRN =
    new DecimalField(message, HEADERSIZE + 79, 4, 0, "E01REQBRN");
    fields[12] = fieldE01REQBRM =
    new CharacterField(message, HEADERSIZE + 83, 35, "E01REQBRM");
    fields[13] = fieldE01REQTIP =
    new CharacterField(message, HEADERSIZE + 118, 4, "E01REQTIP");
    fields[14] = fieldE01REQTIN =
    new CharacterField(message, HEADERSIZE + 122, 35, "E01REQTIN");
    fields[15] = fieldE01REQSUB =
    new CharacterField(message, HEADERSIZE + 157, 4, "E01REQSUB");
    fields[16] = fieldE01REQSUN =
    new CharacterField(message, HEADERSIZE + 161, 35, "E01REQSUN");
    fields[17] = fieldE01REQREF =
    new DecimalField(message, HEADERSIZE + 196, 13, 0, "E01REQREF");
    fields[18] = fieldE01REQRQT =
    new DecimalField(message, HEADERSIZE + 209, 8, 0, "E01REQRQT");
    fields[19] = fieldE01REQCD1 =
    new DecimalField(message, HEADERSIZE + 217, 3, 0, "E01REQCD1");
    fields[20] = fieldE01REQCD2 =
    new DecimalField(message, HEADERSIZE + 220, 3, 0, "E01REQCD2");
    fields[21] = fieldE01REQCD3 =
    new DecimalField(message, HEADERSIZE + 223, 3, 0, "E01REQCD3");
    fields[22] = fieldE01REQCRT =
    new DecimalField(message, HEADERSIZE + 226, 7, 0, "E01REQCRT");
    fields[23] = fieldE01REQCRU =
    new CharacterField(message, HEADERSIZE + 233, 10, "E01REQCRU");

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
  * Set field E01REQBNK using a String value.
  */
  public void setE01REQBNK(String newvalue)
  {
    fieldE01REQBNK.setString(newvalue);
  }

  /**
  * Get value of field E01REQBNK as a String.
  */
  public String getE01REQBNK()
  {
    return fieldE01REQBNK.getString();
  }

  /**
  * Set field E01REQBNN using a String value.
  */
  public void setE01REQBNN(String newvalue)
  {
    fieldE01REQBNN.setString(newvalue);
  }

  /**
  * Get value of field E01REQBNN as a String.
  */
  public String getE01REQBNN()
  {
    return fieldE01REQBNN.getString();
  }

  /**
  * Set field E01REQBRN using a String value.
  */
  public void setE01REQBRN(String newvalue)
  {
    fieldE01REQBRN.setString(newvalue);
  }

  /**
  * Get value of field E01REQBRN as a String.
  */
  public String getE01REQBRN()
  {
    return fieldE01REQBRN.getString();
  }

  /**
  * Set numeric field E01REQBRN using a BigDecimal value.
  */
  public void setE01REQBRN(BigDecimal newvalue)
  {
    fieldE01REQBRN.setBigDecimal(newvalue);
  }

  /**
  * Return value of numeric field E01REQBRN as a BigDecimal.
  */
  public BigDecimal getBigDecimalE01REQBRN()
  {
    return fieldE01REQBRN.getBigDecimal();
  }

  /**
  * Set field E01REQBRM using a String value.
  */
  public void setE01REQBRM(String newvalue)
  {
    fieldE01REQBRM.setString(newvalue);
  }

  /**
  * Get value of field E01REQBRM as a String.
  */
  public String getE01REQBRM()
  {
    return fieldE01REQBRM.getString();
  }

  /**
  * Set field E01REQTIP using a String value.
  */
  public void setE01REQTIP(String newvalue)
  {
    fieldE01REQTIP.setString(newvalue);
  }

  /**
  * Get value of field E01REQTIP as a String.
  */
  public String getE01REQTIP()
  {
    return fieldE01REQTIP.getString();
  }

  /**
  * Set field E01REQTIN using a String value.
  */
  public void setE01REQTIN(String newvalue)
  {
    fieldE01REQTIN.setString(newvalue);
  }

  /**
  * Get value of field E01REQTIN as a String.
  */
  public String getE01REQTIN()
  {
    return fieldE01REQTIN.getString();
  }

  /**
  * Set field E01REQSUB using a String value.
  */
  public void setE01REQSUB(String newvalue)
  {
    fieldE01REQSUB.setString(newvalue);
  }

  /**
  * Get value of field E01REQSUB as a String.
  */
  public String getE01REQSUB()
  {
    return fieldE01REQSUB.getString();
  }

  /**
  * Set field E01REQSUN using a String value.
  */
  public void setE01REQSUN(String newvalue)
  {
    fieldE01REQSUN.setString(newvalue);
  }

  /**
  * Get value of field E01REQSUN as a String.
  */
  public String getE01REQSUN()
  {
    return fieldE01REQSUN.getString();
  }

  /**
  * Set field E01REQREF using a String value.
  */
  public void setE01REQREF(String newvalue)
  {
    fieldE01REQREF.setString(newvalue);
  }

  /**
  * Get value of field E01REQREF as a String.
  */
  public String getE01REQREF()
  {
    return fieldE01REQREF.getString();
  }

  /**
  * Set numeric field E01REQREF using a BigDecimal value.
  */
  public void setE01REQREF(BigDecimal newvalue)
  {
    fieldE01REQREF.setBigDecimal(newvalue);
  }

  /**
  * Return value of numeric field E01REQREF as a BigDecimal.
  */
  public BigDecimal getBigDecimalE01REQREF()
  {
    return fieldE01REQREF.getBigDecimal();
  }

  /**
  * Set field E01REQRQT using a String value.
  */
  public void setE01REQRQT(String newvalue)
  {
    fieldE01REQRQT.setString(newvalue);
  }

  /**
  * Get value of field E01REQRQT as a String.
  */
  public String getE01REQRQT()
  {
    return fieldE01REQRQT.getString();
  }

  /**
  * Set numeric field E01REQRQT using a BigDecimal value.
  */
  public void setE01REQRQT(BigDecimal newvalue)
  {
    fieldE01REQRQT.setBigDecimal(newvalue);
  }

  /**
  * Return value of numeric field E01REQRQT as a BigDecimal.
  */
  public BigDecimal getBigDecimalE01REQRQT()
  {
    return fieldE01REQRQT.getBigDecimal();
  }

  /**
  * Set field E01REQCD1 using a String value.
  */
  public void setE01REQCD1(String newvalue)
  {
    fieldE01REQCD1.setString(newvalue);
  }

  /**
  * Get value of field E01REQCD1 as a String.
  */
  public String getE01REQCD1()
  {
    return fieldE01REQCD1.getString();
  }

  /**
  * Set numeric field E01REQCD1 using a BigDecimal value.
  */
  public void setE01REQCD1(BigDecimal newvalue)
  {
    fieldE01REQCD1.setBigDecimal(newvalue);
  }

  /**
  * Return value of numeric field E01REQCD1 as a BigDecimal.
  */
  public BigDecimal getBigDecimalE01REQCD1()
  {
    return fieldE01REQCD1.getBigDecimal();
  }

  /**
  * Set field E01REQCD2 using a String value.
  */
  public void setE01REQCD2(String newvalue)
  {
    fieldE01REQCD2.setString(newvalue);
  }

  /**
  * Get value of field E01REQCD2 as a String.
  */
  public String getE01REQCD2()
  {
    return fieldE01REQCD2.getString();
  }

  /**
  * Set numeric field E01REQCD2 using a BigDecimal value.
  */
  public void setE01REQCD2(BigDecimal newvalue)
  {
    fieldE01REQCD2.setBigDecimal(newvalue);
  }

  /**
  * Return value of numeric field E01REQCD2 as a BigDecimal.
  */
  public BigDecimal getBigDecimalE01REQCD2()
  {
    return fieldE01REQCD2.getBigDecimal();
  }

  /**
  * Set field E01REQCD3 using a String value.
  */
  public void setE01REQCD3(String newvalue)
  {
    fieldE01REQCD3.setString(newvalue);
  }

  /**
  * Get value of field E01REQCD3 as a String.
  */
  public String getE01REQCD3()
  {
    return fieldE01REQCD3.getString();
  }

  /**
  * Set numeric field E01REQCD3 using a BigDecimal value.
  */
  public void setE01REQCD3(BigDecimal newvalue)
  {
    fieldE01REQCD3.setBigDecimal(newvalue);
  }

  /**
  * Return value of numeric field E01REQCD3 as a BigDecimal.
  */
  public BigDecimal getBigDecimalE01REQCD3()
  {
    return fieldE01REQCD3.getBigDecimal();
  }

  /**
  * Set field E01REQCRT using a String value.
  */
  public void setE01REQCRT(String newvalue)
  {
    fieldE01REQCRT.setString(newvalue);
  }

  /**
  * Get value of field E01REQCRT as a String.
  */
  public String getE01REQCRT()
  {
    return fieldE01REQCRT.getString();
  }

  /**
  * Set numeric field E01REQCRT using a BigDecimal value.
  */
  public void setE01REQCRT(BigDecimal newvalue)
  {
    fieldE01REQCRT.setBigDecimal(newvalue);
  }

  /**
  * Return value of numeric field E01REQCRT as a BigDecimal.
  */
  public BigDecimal getBigDecimalE01REQCRT()
  {
    return fieldE01REQCRT.getBigDecimal();
  }

  /**
  * Set field E01REQCRU using a String value.
  */
  public void setE01REQCRU(String newvalue)
  {
    fieldE01REQCRU.setString(newvalue);
  }

  /**
  * Get value of field E01REQCRU as a String.
  */
  public String getE01REQCRU()
  {
    return fieldE01REQCRU.getString();
  }

}