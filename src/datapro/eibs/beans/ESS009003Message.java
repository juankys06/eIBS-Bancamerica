package datapro.eibs.beans;

import datapro.eibs.sockets.*;
import java.io.*;
import java.math.*;

import java.util.*;

/**
* Class generated by AS/400 CRTCLASS command from ESS009003 physical file definition.
* 
* File level identifier is 1030121192012.
* Record format level identifier is 590BB485510EA.
*/

public class ESS009003Message extends MessageRecord
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
                                     "E03MSUSRM",
                                     "E03MSGACC",
                                     "E03MSGACD",
                                     "E03MSGTXT",
                                     "E03MSGCUN",
                                     "E03MSGNUM",
                                     "E03ACTION"
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
                                   "E03MSUSRM",
                                   "E03MSGACC",
                                   "E03MSGACD",
                                   "E03MSGTXT",
                                   "E03MSGCUN",
                                   "E03MSGNUM",
                                   "E03ACTION"
                                 };
  final static String fid = "1030121192012";
  final static String rid = "590BB485510EA";
  final static String fmtname = "ESS009003";
  final int FIELDCOUNT = 16;
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
  private CharacterField fieldE03MSUSRM = null;
  private DecimalField fieldE03MSGACC = null;
  private CharacterField fieldE03MSGACD = null;
  private CharacterField fieldE03MSGTXT = null;
  private DecimalField fieldE03MSGCUN = null;
  private DecimalField fieldE03MSGNUM = null;
  private CharacterField fieldE03ACTION = null;

  /**
  * Constructor for ESS009003Message.
  */
  public ESS009003Message()
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
    recordsize = 1083;
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
    fields[9] = fieldE03MSUSRM =
    new CharacterField(message, HEADERSIZE + 42, 10, "E03MSUSRM");
    fields[10] = fieldE03MSGACC =
    new DecimalField(message, HEADERSIZE + 52, 13, 0, "E03MSGACC");
    fields[11] = fieldE03MSGACD =
    new CharacterField(message, HEADERSIZE + 65, 2, "E03MSGACD");
    fields[12] = fieldE03MSGTXT =
    new CharacterField(message, HEADERSIZE + 67, 1000, "E03MSGTXT");
    fields[13] = fieldE03MSGCUN =
    new DecimalField(message, HEADERSIZE + 1067, 10, 0, "E03MSGCUN");
    fields[14] = fieldE03MSGNUM =
    new DecimalField(message, HEADERSIZE + 1077, 5, 0, "E03MSGNUM");
    fields[15] = fieldE03ACTION =
    new CharacterField(message, HEADERSIZE + 1082, 1, "E03ACTION");

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
  * Set field E03MSUSRM using a String value.
  */
  public void setE03MSUSRM(String newvalue)
  {
    fieldE03MSUSRM.setString(newvalue);
  }

  /**
  * Get value of field E03MSUSRM as a String.
  */
  public String getE03MSUSRM()
  {
    return fieldE03MSUSRM.getString();
  }

  /**
  * Set field E03MSGACC using a String value.
  */
  public void setE03MSGACC(String newvalue)
  {
    fieldE03MSGACC.setString(newvalue);
  }

  /**
  * Get value of field E03MSGACC as a String.
  */
  public String getE03MSGACC()
  {
    return fieldE03MSGACC.getString();
  }

  /**
  * Set numeric field E03MSGACC using a BigDecimal value.
  */
  public void setE03MSGACC(BigDecimal newvalue)
  {
    fieldE03MSGACC.setBigDecimal(newvalue);
  }

  /**
  * Return value of numeric field E03MSGACC as a BigDecimal.
  */
  public BigDecimal getBigDecimalE03MSGACC()
  {
    return fieldE03MSGACC.getBigDecimal();
  }

  /**
  * Set field E03MSGACD using a String value.
  */
  public void setE03MSGACD(String newvalue)
  {
    fieldE03MSGACD.setString(newvalue);
  }

  /**
  * Get value of field E03MSGACD as a String.
  */
  public String getE03MSGACD()
  {
    return fieldE03MSGACD.getString();
  }

  /**
  * Set field E03MSGTXT using a String value.
  */
  public void setE03MSGTXT(String newvalue)
  {
    fieldE03MSGTXT.setString(newvalue);
  }

  /**
  * Get value of field E03MSGTXT as a String.
  */
  public String getE03MSGTXT()
  {
    return fieldE03MSGTXT.getString();
  }

  /**
  * Set field E03MSGCUN using a String value.
  */
  public void setE03MSGCUN(String newvalue)
  {
    fieldE03MSGCUN.setString(newvalue);
  }

  /**
  * Get value of field E03MSGCUN as a String.
  */
  public String getE03MSGCUN()
  {
    return fieldE03MSGCUN.getString();
  }

  /**
  * Set numeric field E03MSGCUN using a BigDecimal value.
  */
  public void setE03MSGCUN(BigDecimal newvalue)
  {
    fieldE03MSGCUN.setBigDecimal(newvalue);
  }

  /**
  * Return value of numeric field E03MSGCUN as a BigDecimal.
  */
  public BigDecimal getBigDecimalE03MSGCUN()
  {
    return fieldE03MSGCUN.getBigDecimal();
  }

  /**
  * Set field E03MSGNUM using a String value.
  */
  public void setE03MSGNUM(String newvalue)
  {
    fieldE03MSGNUM.setString(newvalue);
  }

  /**
  * Get value of field E03MSGNUM as a String.
  */
  public String getE03MSGNUM()
  {
    return fieldE03MSGNUM.getString();
  }

  /**
  * Set numeric field E03MSGNUM using a BigDecimal value.
  */
  public void setE03MSGNUM(BigDecimal newvalue)
  {
    fieldE03MSGNUM.setBigDecimal(newvalue);
  }

  /**
  * Return value of numeric field E03MSGNUM as a BigDecimal.
  */
  public BigDecimal getBigDecimalE03MSGNUM()
  {
    return fieldE03MSGNUM.getBigDecimal();
  }

  /**
  * Set field E03ACTION using a String value.
  */
  public void setE03ACTION(String newvalue)
  {
    fieldE03ACTION.setString(newvalue);
  }

  /**
  * Get value of field E03ACTION as a String.
  */
  public String getE03ACTION()
  {
    return fieldE03ACTION.getString();
  }

}
