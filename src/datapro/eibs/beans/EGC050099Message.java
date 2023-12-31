package datapro.eibs.beans;

import datapro.eibs.sockets.*;
import java.io.*;
import java.math.*;

import java.util.*;

/**
* Class generated by AS/400 CRTCLASS command from EGC050099 physical file definition.
* 
* File level identifier is 1030121191928.
* Record format level identifier is 4B3FDA8CADDA9.
*/

public class EGC050099Message extends MessageRecord
{
  final static String fldnames[] = {
                                     "H99USERID",
                                     "H99PROGRM",
                                     "H99TIMSYS",
                                     "H99SCRCOD",
                                     "H99OPECOD",
                                     "H99FLGMAS",
                                     "H99FLGWK1",
                                     "H99FLGWK2",
                                     "H99FLGWK3",
                                     "H99CODACC",
                                     "E99COPROD",
                                     "E99NMPROD",
                                     "E99CODAPC",
                                     "E99INDOPE"
                                   };
  final static String tnames[] = {
                                   "H99USERID",
                                   "H99PROGRM",
                                   "H99TIMSYS",
                                   "H99SCRCOD",
                                   "H99OPECOD",
                                   "H99FLGMAS",
                                   "H99FLGWK1",
                                   "H99FLGWK2",
                                   "H99FLGWK3",
                                   "H99CODACC",
                                   "E99COPROD",
                                   "E99NMPROD",
                                   "E99CODAPC",
                                   "E99INDOPE"
                                 };
  final static String fid = "1030121191928";
  final static String rid = "4B3FDA8CADDA9";
  final static String fmtname = "EGC050099";
  final int FIELDCOUNT = 14;
  private static Hashtable tlookup = new Hashtable();
  private CharacterField fieldH99USERID = null;
  private CharacterField fieldH99PROGRM = null;
  private CharacterField fieldH99TIMSYS = null;
  private CharacterField fieldH99SCRCOD = null;
  private CharacterField fieldH99OPECOD = null;
  private CharacterField fieldH99FLGMAS = null;
  private CharacterField fieldH99FLGWK1 = null;
  private CharacterField fieldH99FLGWK2 = null;
  private CharacterField fieldH99FLGWK3 = null;
  private CharacterField fieldH99CODACC = null;
  private CharacterField fieldE99COPROD = null;
  private CharacterField fieldE99NMPROD = null;
  private CharacterField fieldE99CODAPC = null;
  private CharacterField fieldE99INDOPE = null;

  /**
  * Constructor for EGC050099Message.
  */
  public EGC050099Message()
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
    recordsize = 133;
    fileid = fid;
    recordid = rid;
    message = new byte[getByteLength()];
    formatname = fmtname;
    fieldnames = fldnames;
    tagnames = tnames;
    fields = new MessageField[FIELDCOUNT];

    fields[0] = fieldH99USERID =
    new CharacterField(message, HEADERSIZE + 0, 10, "H99USERID");
    fields[1] = fieldH99PROGRM =
    new CharacterField(message, HEADERSIZE + 10, 10, "H99PROGRM");
    fields[2] = fieldH99TIMSYS =
    new CharacterField(message, HEADERSIZE + 20, 12, "H99TIMSYS");
    fields[3] = fieldH99SCRCOD =
    new CharacterField(message, HEADERSIZE + 32, 2, "H99SCRCOD");
    fields[4] = fieldH99OPECOD =
    new CharacterField(message, HEADERSIZE + 34, 4, "H99OPECOD");
    fields[5] = fieldH99FLGMAS =
    new CharacterField(message, HEADERSIZE + 38, 1, "H99FLGMAS");
    fields[6] = fieldH99FLGWK1 =
    new CharacterField(message, HEADERSIZE + 39, 1, "H99FLGWK1");
    fields[7] = fieldH99FLGWK2 =
    new CharacterField(message, HEADERSIZE + 40, 1, "H99FLGWK2");
    fields[8] = fieldH99FLGWK3 =
    new CharacterField(message, HEADERSIZE + 41, 1, "H99FLGWK3");
    fields[9] = fieldH99CODACC =
    new CharacterField(message, HEADERSIZE + 42, 4, "H99CODACC");
    fields[10] = fieldE99COPROD =
    new CharacterField(message, HEADERSIZE + 46, 4, "E99COPROD");
    fields[11] = fieldE99NMPROD =
    new CharacterField(message, HEADERSIZE + 50, 78, "E99NMPROD");
    fields[12] = fieldE99CODAPC =
    new CharacterField(message, HEADERSIZE + 128, 4, "E99CODAPC");
    fields[13] = fieldE99INDOPE =
    new CharacterField(message, HEADERSIZE + 132, 1, "E99INDOPE");

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
  * Set field H99USERID using a String value.
  */
  public void setH99USERID(String newvalue)
  {
    fieldH99USERID.setString(newvalue);
  }

  /**
  * Get value of field H99USERID as a String.
  */
  public String getH99USERID()
  {
    return fieldH99USERID.getString();
  }

  /**
  * Set field H99PROGRM using a String value.
  */
  public void setH99PROGRM(String newvalue)
  {
    fieldH99PROGRM.setString(newvalue);
  }

  /**
  * Get value of field H99PROGRM as a String.
  */
  public String getH99PROGRM()
  {
    return fieldH99PROGRM.getString();
  }

  /**
  * Set field H99TIMSYS using a String value.
  */
  public void setH99TIMSYS(String newvalue)
  {
    fieldH99TIMSYS.setString(newvalue);
  }

  /**
  * Get value of field H99TIMSYS as a String.
  */
  public String getH99TIMSYS()
  {
    return fieldH99TIMSYS.getString();
  }

  /**
  * Set field H99SCRCOD using a String value.
  */
  public void setH99SCRCOD(String newvalue)
  {
    fieldH99SCRCOD.setString(newvalue);
  }

  /**
  * Get value of field H99SCRCOD as a String.
  */
  public String getH99SCRCOD()
  {
    return fieldH99SCRCOD.getString();
  }

  /**
  * Set field H99OPECOD using a String value.
  */
  public void setH99OPECOD(String newvalue)
  {
    fieldH99OPECOD.setString(newvalue);
  }

  /**
  * Get value of field H99OPECOD as a String.
  */
  public String getH99OPECOD()
  {
    return fieldH99OPECOD.getString();
  }

  /**
  * Set field H99FLGMAS using a String value.
  */
  public void setH99FLGMAS(String newvalue)
  {
    fieldH99FLGMAS.setString(newvalue);
  }

  /**
  * Get value of field H99FLGMAS as a String.
  */
  public String getH99FLGMAS()
  {
    return fieldH99FLGMAS.getString();
  }

  /**
  * Set field H99FLGWK1 using a String value.
  */
  public void setH99FLGWK1(String newvalue)
  {
    fieldH99FLGWK1.setString(newvalue);
  }

  /**
  * Get value of field H99FLGWK1 as a String.
  */
  public String getH99FLGWK1()
  {
    return fieldH99FLGWK1.getString();
  }

  /**
  * Set field H99FLGWK2 using a String value.
  */
  public void setH99FLGWK2(String newvalue)
  {
    fieldH99FLGWK2.setString(newvalue);
  }

  /**
  * Get value of field H99FLGWK2 as a String.
  */
  public String getH99FLGWK2()
  {
    return fieldH99FLGWK2.getString();
  }

  /**
  * Set field H99FLGWK3 using a String value.
  */
  public void setH99FLGWK3(String newvalue)
  {
    fieldH99FLGWK3.setString(newvalue);
  }

  /**
  * Get value of field H99FLGWK3 as a String.
  */
  public String getH99FLGWK3()
  {
    return fieldH99FLGWK3.getString();
  }

  /**
  * Set field H99CODACC using a String value.
  */
  public void setH99CODACC(String newvalue)
  {
    fieldH99CODACC.setString(newvalue);
  }

  /**
  * Get value of field H99CODACC as a String.
  */
  public String getH99CODACC()
  {
    return fieldH99CODACC.getString();
  }

  /**
  * Set field E99COPROD using a String value.
  */
  public void setE99COPROD(String newvalue)
  {
    fieldE99COPROD.setString(newvalue);
  }

  /**
  * Get value of field E99COPROD as a String.
  */
  public String getE99COPROD()
  {
    return fieldE99COPROD.getString();
  }

  /**
  * Set field E99NMPROD using a String value.
  */
  public void setE99NMPROD(String newvalue)
  {
    fieldE99NMPROD.setString(newvalue);
  }

  /**
  * Get value of field E99NMPROD as a String.
  */
  public String getE99NMPROD()
  {
    return fieldE99NMPROD.getString();
  }

  /**
  * Set field E99CODAPC using a String value.
  */
  public void setE99CODAPC(String newvalue)
  {
    fieldE99CODAPC.setString(newvalue);
  }

  /**
  * Get value of field E99CODAPC as a String.
  */
  public String getE99CODAPC()
  {
    return fieldE99CODAPC.getString();
  }

  /**
  * Set field E99INDOPE using a String value.
  */
  public void setE99INDOPE(String newvalue)
  {
    fieldE99INDOPE.setString(newvalue);
  }

  /**
  * Get value of field E99INDOPE as a String.
  */
  public String getE99INDOPE()
  {
    return fieldE99INDOPE.getString();
  }

}
