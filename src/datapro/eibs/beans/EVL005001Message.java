package datapro.eibs.beans; 

import datapro.eibs.sockets.*;
import java.io.*;
import java.math.*;

import java.util.*;

/**
* Class generated by AS/400 CRTCLASS command from EVL005001 physical file definition.
* 
* File level identifier is 1060815162402.
* Record format level identifier is 4BC16682E9609.
*/

public class EVL005001Message extends MessageRecord
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
                                     "E01LVLBNK",
                                     "E01LVLBNN",
                                     "E01LVLBRN",
                                     "E01LVLBRM",
                                     "E01LVLTIP",
                                     "E01LVLTIN",
                                     "E01LVLSUB",
                                     "E01LVLSUN"
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
                                   "E01LVLBNK",
                                   "E01LVLBNN",
                                   "E01LVLBRN",
                                   "E01LVLBRM",
                                   "E01LVLTIP",
                                   "E01LVLTIN",
                                   "E01LVLSUB",
                                   "E01LVLSUN"
                                 };
  final static String fid = "1060815162402";
  final static String rid = "4BC16682E9609";
  final static String fmtname = "EVL005001";
  final int FIELDCOUNT = 17;
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
  private CharacterField fieldE01LVLBNK = null;
  private CharacterField fieldE01LVLBNN = null;
  private DecimalField fieldE01LVLBRN = null;
  private CharacterField fieldE01LVLBRM = null;
  private CharacterField fieldE01LVLTIP = null;
  private CharacterField fieldE01LVLTIN = null;
  private CharacterField fieldE01LVLSUB = null;
  private CharacterField fieldE01LVLSUN = null;

  /**
  * Constructor for EVL005001Message.
  */
  public EVL005001Message()
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
    recordsize = 196;
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
    fields[9] = fieldE01LVLBNK =
    new CharacterField(message, HEADERSIZE + 42, 2, "E01LVLBNK");
    fields[10] = fieldE01LVLBNN =
    new CharacterField(message, HEADERSIZE + 44, 35, "E01LVLBNN");
    fields[11] = fieldE01LVLBRN =
    new DecimalField(message, HEADERSIZE + 79, 4, 0, "E01LVLBRN");
    fields[12] = fieldE01LVLBRM =
    new CharacterField(message, HEADERSIZE + 83, 35, "E01LVLBRM");
    fields[13] = fieldE01LVLTIP =
    new CharacterField(message, HEADERSIZE + 118, 4, "E01LVLTIP");
    fields[14] = fieldE01LVLTIN =
    new CharacterField(message, HEADERSIZE + 122, 35, "E01LVLTIN");
    fields[15] = fieldE01LVLSUB =
    new CharacterField(message, HEADERSIZE + 157, 4, "E01LVLSUB");
    fields[16] = fieldE01LVLSUN =
    new CharacterField(message, HEADERSIZE + 161, 35, "E01LVLSUN");

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
  * Set field E01LVLBNK using a String value.
  */
  public void setE01LVLBNK(String newvalue)
  {
    fieldE01LVLBNK.setString(newvalue);
  }

  /**
  * Get value of field E01LVLBNK as a String.
  */
  public String getE01LVLBNK()
  {
    return fieldE01LVLBNK.getString();
  }

  /**
  * Set field E01LVLBNN using a String value.
  */
  public void setE01LVLBNN(String newvalue)
  {
    fieldE01LVLBNN.setString(newvalue);
  }

  /**
  * Get value of field E01LVLBNN as a String.
  */
  public String getE01LVLBNN()
  {
    return fieldE01LVLBNN.getString();
  }

  /**
  * Set field E01LVLBRN using a String value.
  */
  public void setE01LVLBRN(String newvalue)
  {
    fieldE01LVLBRN.setString(newvalue);
  }

  /**
  * Get value of field E01LVLBRN as a String.
  */
  public String getE01LVLBRN()
  {
    return fieldE01LVLBRN.getString();
  }

  /**
  * Set numeric field E01LVLBRN using a BigDecimal value.
  */
  public void setE01LVLBRN(BigDecimal newvalue)
  {
    fieldE01LVLBRN.setBigDecimal(newvalue);
  }

  /**
  * Return value of numeric field E01LVLBRN as a BigDecimal.
  */
  public BigDecimal getBigDecimalE01LVLBRN()
  {
    return fieldE01LVLBRN.getBigDecimal();
  }

  /**
  * Set field E01LVLBRM using a String value.
  */
  public void setE01LVLBRM(String newvalue)
  {
    fieldE01LVLBRM.setString(newvalue);
  }

  /**
  * Get value of field E01LVLBRM as a String.
  */
  public String getE01LVLBRM()
  {
    return fieldE01LVLBRM.getString();
  }

  /**
  * Set field E01LVLTIP using a String value.
  */
  public void setE01LVLTIP(String newvalue)
  {
    fieldE01LVLTIP.setString(newvalue);
  }

  /**
  * Get value of field E01LVLTIP as a String.
  */
  public String getE01LVLTIP()
  {
    return fieldE01LVLTIP.getString();
  }

  /**
  * Set field E01LVLTIN using a String value.
  */
  public void setE01LVLTIN(String newvalue)
  {
    fieldE01LVLTIN.setString(newvalue);
  }

  /**
  * Get value of field E01LVLTIN as a String.
  */
  public String getE01LVLTIN()
  {
    return fieldE01LVLTIN.getString();
  }

  /**
  * Set field E01LVLSUB using a String value.
  */
  public void setE01LVLSUB(String newvalue)
  {
    fieldE01LVLSUB.setString(newvalue);
  }

  /**
  * Get value of field E01LVLSUB as a String.
  */
  public String getE01LVLSUB()
  {
    return fieldE01LVLSUB.getString();
  }

  /**
  * Set field E01LVLSUN using a String value.
  */
  public void setE01LVLSUN(String newvalue)
  {
    fieldE01LVLSUN.setString(newvalue);
  }

  /**
  * Get value of field E01LVLSUN as a String.
  */
  public String getE01LVLSUN()
  {
    return fieldE01LVLSUN.getString();
  }

}
