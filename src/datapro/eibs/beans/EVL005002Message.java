package datapro.eibs.beans; 

import datapro.eibs.sockets.*;
import java.io.*;
import java.math.*;

import java.util.*;

/**
* Class generated by AS/400 CRTCLASS command from EVL005002 physical file definition.
* 
* File level identifier is 1060815162403.
* Record format level identifier is 40DAF4D377B66.
*/

public class EVL005002Message extends MessageRecord
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
                                     "E02LVLBNK",
                                     "E02LVLBRN",
                                     "E02LVLTIP",
                                     "E02LVLTIN",
                                     "E02LVLSUB",
                                     "E02LVLSUN",
                                     "E02LVLMIN",
                                     "E02LVLBAL",
                                     "E02DOCBAL",
                                     "E02TOTBAL",
                                     "E02REQRQT",
                                     "E02REQAQT"
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
                                   "E02LVLBNK",
                                   "E02LVLBRN",
                                   "E02LVLTIP",
                                   "E02LVLTIN",
                                   "E02LVLSUB",
                                   "E02LVLSUN",
                                   "E02LVLMIN",
                                   "E02LVLBAL",
                                   "E02DOCBAL",
                                   "E02TOTBAL",
                                   "E02REQRQT",
                                   "E02REQAQT"
                                 };
  final static String fid = "1060815162403";
  final static String rid = "40DAF4D377B66";
  final static String fmtname = "EVL005002";
  final int FIELDCOUNT = 21;
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
  private CharacterField fieldE02LVLBNK = null;
  private DecimalField fieldE02LVLBRN = null;
  private CharacterField fieldE02LVLTIP = null;
  private CharacterField fieldE02LVLTIN = null;
  private CharacterField fieldE02LVLSUB = null;
  private CharacterField fieldE02LVLSUN = null;
  private DecimalField fieldE02LVLMIN = null;
  private DecimalField fieldE02LVLBAL = null;
  private DecimalField fieldE02DOCBAL = null;
  private DecimalField fieldE02TOTBAL = null;
  private DecimalField fieldE02REQRQT = null;
  private DecimalField fieldE02REQAQT = null;

  /**
  * Constructor for EVL005002Message.
  */
  public EVL005002Message()
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
    recordsize = 174;
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
    fields[9] = fieldE02LVLBNK =
    new CharacterField(message, HEADERSIZE + 42, 2, "E02LVLBNK");
    fields[10] = fieldE02LVLBRN =
    new DecimalField(message, HEADERSIZE + 44, 4, 0, "E02LVLBRN");
    fields[11] = fieldE02LVLTIP =
    new CharacterField(message, HEADERSIZE + 48, 4, "E02LVLTIP");
    fields[12] = fieldE02LVLTIN =
    new CharacterField(message, HEADERSIZE + 52, 35, "E02LVLTIN");
    fields[13] = fieldE02LVLSUB =
    new CharacterField(message, HEADERSIZE + 87, 4, "E02LVLSUB");
    fields[14] = fieldE02LVLSUN =
    new CharacterField(message, HEADERSIZE + 91, 35, "E02LVLSUN");
    fields[15] = fieldE02LVLMIN =
    new DecimalField(message, HEADERSIZE + 126, 8, 0, "E02LVLMIN");
    fields[16] = fieldE02LVLBAL =
    new DecimalField(message, HEADERSIZE + 134, 8, 0, "E02LVLBAL");
    fields[17] = fieldE02DOCBAL =
    new DecimalField(message, HEADERSIZE + 142, 8, 0, "E02DOCBAL");
    fields[18] = fieldE02TOTBAL =
    new DecimalField(message, HEADERSIZE + 150, 8, 0, "E02TOTBAL");
    fields[19] = fieldE02REQRQT =
    new DecimalField(message, HEADERSIZE + 158, 8, 0, "E02REQRQT");
    fields[20] = fieldE02REQAQT =
    new DecimalField(message, HEADERSIZE + 166, 8, 0, "E02REQAQT");

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
  * Set field E02LVLBNK using a String value.
  */
  public void setE02LVLBNK(String newvalue)
  {
    fieldE02LVLBNK.setString(newvalue);
  }

  /**
  * Get value of field E02LVLBNK as a String.
  */
  public String getE02LVLBNK()
  {
    return fieldE02LVLBNK.getString();
  }

  /**
  * Set field E02LVLBRN using a String value.
  */
  public void setE02LVLBRN(String newvalue)
  {
    fieldE02LVLBRN.setString(newvalue);
  }

  /**
  * Get value of field E02LVLBRN as a String.
  */
  public String getE02LVLBRN()
  {
    return fieldE02LVLBRN.getString();
  }

  /**
  * Set numeric field E02LVLBRN using a BigDecimal value.
  */
  public void setE02LVLBRN(BigDecimal newvalue)
  {
    fieldE02LVLBRN.setBigDecimal(newvalue);
  }

  /**
  * Return value of numeric field E02LVLBRN as a BigDecimal.
  */
  public BigDecimal getBigDecimalE02LVLBRN()
  {
    return fieldE02LVLBRN.getBigDecimal();
  }

  /**
  * Set field E02LVLTIP using a String value.
  */
  public void setE02LVLTIP(String newvalue)
  {
    fieldE02LVLTIP.setString(newvalue);
  }

  /**
  * Get value of field E02LVLTIP as a String.
  */
  public String getE02LVLTIP()
  {
    return fieldE02LVLTIP.getString();
  }

  /**
  * Set field E02LVLTIN using a String value.
  */
  public void setE02LVLTIN(String newvalue)
  {
    fieldE02LVLTIN.setString(newvalue);
  }

  /**
  * Get value of field E02LVLTIN as a String.
  */
  public String getE02LVLTIN()
  {
    return fieldE02LVLTIN.getString();
  }

  /**
  * Set field E02LVLSUB using a String value.
  */
  public void setE02LVLSUB(String newvalue)
  {
    fieldE02LVLSUB.setString(newvalue);
  }

  /**
  * Get value of field E02LVLSUB as a String.
  */
  public String getE02LVLSUB()
  {
    return fieldE02LVLSUB.getString();
  }

  /**
  * Set field E02LVLSUN using a String value.
  */
  public void setE02LVLSUN(String newvalue)
  {
    fieldE02LVLSUN.setString(newvalue);
  }

  /**
  * Get value of field E02LVLSUN as a String.
  */
  public String getE02LVLSUN()
  {
    return fieldE02LVLSUN.getString();
  }

  /**
  * Set field E02LVLMIN using a String value.
  */
  public void setE02LVLMIN(String newvalue)
  {
    fieldE02LVLMIN.setString(newvalue);
  }

  /**
  * Get value of field E02LVLMIN as a String.
  */
  public String getE02LVLMIN()
  {
    return fieldE02LVLMIN.getString();
  }

  /**
  * Set numeric field E02LVLMIN using a BigDecimal value.
  */
  public void setE02LVLMIN(BigDecimal newvalue)
  {
    fieldE02LVLMIN.setBigDecimal(newvalue);
  }

  /**
  * Return value of numeric field E02LVLMIN as a BigDecimal.
  */
  public BigDecimal getBigDecimalE02LVLMIN()
  {
    return fieldE02LVLMIN.getBigDecimal();
  }

  /**
  * Set field E02LVLBAL using a String value.
  */
  public void setE02LVLBAL(String newvalue)
  {
    fieldE02LVLBAL.setString(newvalue);
  }

  /**
  * Get value of field E02LVLBAL as a String.
  */
  public String getE02LVLBAL()
  {
    return fieldE02LVLBAL.getString();
  }

  /**
  * Set numeric field E02LVLBAL using a BigDecimal value.
  */
  public void setE02LVLBAL(BigDecimal newvalue)
  {
    fieldE02LVLBAL.setBigDecimal(newvalue);
  }

  /**
  * Return value of numeric field E02LVLBAL as a BigDecimal.
  */
  public BigDecimal getBigDecimalE02LVLBAL()
  {
    return fieldE02LVLBAL.getBigDecimal();
  }

  /**
  * Set field E02DOCBAL using a String value.
  */
  public void setE02DOCBAL(String newvalue)
  {
    fieldE02DOCBAL.setString(newvalue);
  }

  /**
  * Get value of field E02DOCBAL as a String.
  */
  public String getE02DOCBAL()
  {
    return fieldE02DOCBAL.getString();
  }

  /**
  * Set numeric field E02DOCBAL using a BigDecimal value.
  */
  public void setE02DOCBAL(BigDecimal newvalue)
  {
    fieldE02DOCBAL.setBigDecimal(newvalue);
  }

  /**
  * Return value of numeric field E02DOCBAL as a BigDecimal.
  */
  public BigDecimal getBigDecimalE02DOCBAL()
  {
    return fieldE02DOCBAL.getBigDecimal();
  }

  /**
  * Set field E02TOTBAL using a String value.
  */
  public void setE02TOTBAL(String newvalue)
  {
    fieldE02TOTBAL.setString(newvalue);
  }

  /**
  * Get value of field E02TOTBAL as a String.
  */
  public String getE02TOTBAL()
  {
    return fieldE02TOTBAL.getString();
  }

  /**
  * Set numeric field E02TOTBAL using a BigDecimal value.
  */
  public void setE02TOTBAL(BigDecimal newvalue)
  {
    fieldE02TOTBAL.setBigDecimal(newvalue);
  }

  /**
  * Return value of numeric field E02TOTBAL as a BigDecimal.
  */
  public BigDecimal getBigDecimalE02TOTBAL()
  {
    return fieldE02TOTBAL.getBigDecimal();
  }

  /**
  * Set field E02REQRQT using a String value.
  */
  public void setE02REQRQT(String newvalue)
  {
    fieldE02REQRQT.setString(newvalue);
  }

  /**
  * Get value of field E02REQRQT as a String.
  */
  public String getE02REQRQT()
  {
    return fieldE02REQRQT.getString();
  }

  /**
  * Set numeric field E02REQRQT using a BigDecimal value.
  */
  public void setE02REQRQT(BigDecimal newvalue)
  {
    fieldE02REQRQT.setBigDecimal(newvalue);
  }

  /**
  * Return value of numeric field E02REQRQT as a BigDecimal.
  */
  public BigDecimal getBigDecimalE02REQRQT()
  {
    return fieldE02REQRQT.getBigDecimal();
  }

  /**
  * Set field E02REQAQT using a String value.
  */
  public void setE02REQAQT(String newvalue)
  {
    fieldE02REQAQT.setString(newvalue);
  }

  /**
  * Get value of field E02REQAQT as a String.
  */
  public String getE02REQAQT()
  {
    return fieldE02REQAQT.getString();
  }

  /**
  * Set numeric field E02REQAQT using a BigDecimal value.
  */
  public void setE02REQAQT(BigDecimal newvalue)
  {
    fieldE02REQAQT.setBigDecimal(newvalue);
  }

  /**
  * Return value of numeric field E02REQAQT as a BigDecimal.
  */
  public BigDecimal getBigDecimalE02REQAQT()
  {
    return fieldE02REQAQT.getBigDecimal();
  }

}
