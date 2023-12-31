package datapro.eibs.beans;


import datapro.eibs.sockets.*;
import java.io.*;
import java.math.*;

import java.util.*;

/**
* Class generated by AS/400 CRTCLASS command from EDP071001 physical file definition.
* 
* File level identifier is 1060403113459.
* Record format level identifier is 4A56618142FAA.
*/

public class EDP071001Message extends MessageRecord
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
                                     "E01CNTLAN",
                                     "E01DPIIND",
                                     "E01DPIDSC",
                                     "E01DPILGT",
                                     "E01XXXLGT",
                                     "E01DPIFOR",
                                     "E01OPECDE"
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
                                   "E01CNTLAN",
                                   "E01DPIIND",
                                   "E01DPIDSC",
                                   "E01DPILGT",
                                   "E01XXXLGT",
                                   "E01DPIFOR",
                                   "E01OPECDE"
                                 };
  final static String fid = "1060403113459";
  final static String rid = "4A56618142FAA";
  final static String fmtname = "EDP071001";
  final int FIELDCOUNT = 16;
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
  private CharacterField fieldE01CNTLAN = null;
  private CharacterField fieldE01DPIIND = null;
  private CharacterField fieldE01DPIDSC = null;
  private CharacterField fieldE01DPILGT = null;
  private CharacterField fieldE01XXXLGT = null;
  private CharacterField fieldE01DPIFOR = null;
  private CharacterField fieldE01OPECDE = null;

  /**
  * Constructor for EDP071001Message.
  */
  public EDP071001Message()
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
    recordsize = 419;
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
    fields[9] = fieldE01CNTLAN =
    new CharacterField(message, HEADERSIZE + 42, 1, "E01CNTLAN");
    fields[10] = fieldE01DPIIND =
    new CharacterField(message, HEADERSIZE + 43, 4, "E01DPIIND");
    fields[11] = fieldE01DPIDSC =
    new CharacterField(message, HEADERSIZE + 47, 35, "E01DPIDSC");
    fields[12] = fieldE01DPILGT =
    new CharacterField(message, HEADERSIZE + 82, 1, "E01DPILGT");
    fields[13] = fieldE01XXXLGT =
    new CharacterField(message, HEADERSIZE + 83, 35, "E01XXXLGT");
    fields[14] = fieldE01DPIFOR =
    new CharacterField(message, HEADERSIZE + 118, 300, "E01DPIFOR");
    fields[15] = fieldE01OPECDE =
    new CharacterField(message, HEADERSIZE + 418, 1, "E01OPECDE");

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
  * Set field E01CNTLAN using a String value.
  */
  public void setE01CNTLAN(String newvalue)
  {
    fieldE01CNTLAN.setString(newvalue);
  }

  /**
  * Get value of field E01CNTLAN as a String.
  */
  public String getE01CNTLAN()
  {
    return fieldE01CNTLAN.getString();
  }

  /**
  * Set field E01DPIIND using a String value.
  */
  public void setE01DPIIND(String newvalue)
  {
    fieldE01DPIIND.setString(newvalue);
  }

  /**
  * Get value of field E01DPIIND as a String.
  */
  public String getE01DPIIND()
  {
    return fieldE01DPIIND.getString();
  }

  /**
  * Set field E01DPIDSC using a String value.
  */
  public void setE01DPIDSC(String newvalue)
  {
    fieldE01DPIDSC.setString(newvalue);
  }

  /**
  * Get value of field E01DPIDSC as a String.
  */
  public String getE01DPIDSC()
  {
    return fieldE01DPIDSC.getString();
  }

  /**
  * Set field E01DPILGT using a String value.
  */
  public void setE01DPILGT(String newvalue)
  {
    fieldE01DPILGT.setString(newvalue);
  }

  /**
  * Get value of field E01DPILGT as a String.
  */
  public String getE01DPILGT()
  {
    return fieldE01DPILGT.getString();
  }

  /**
  * Set field E01XXXLGT using a String value.
  */
  public void setE01XXXLGT(String newvalue)
  {
    fieldE01XXXLGT.setString(newvalue);
  }

  /**
  * Get value of field E01XXXLGT as a String.
  */
  public String getE01XXXLGT()
  {
    return fieldE01XXXLGT.getString();
  }

  /**
  * Set field E01DPIFOR using a String value.
  */
  public void setE01DPIFOR(String newvalue)
  {
    fieldE01DPIFOR.setString(newvalue);
  }

  /**
  * Get value of field E01DPIFOR as a String.
  */
  public String getE01DPIFOR()
  {
    return fieldE01DPIFOR.getString();
  }

  /**
  * Set field E01OPECDE using a String value.
  */
  public void setE01OPECDE(String newvalue)
  {
    fieldE01OPECDE.setString(newvalue);
  }

  /**
  * Get value of field E01OPECDE as a String.
  */
  public String getE01OPECDE()
  {
    return fieldE01OPECDE.getString();
  }

}
