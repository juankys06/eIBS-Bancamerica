package datapro.eibs.beans;

import datapro.eibs.sockets.*;
import java.io.*;
import java.math.*;

import java.util.*;

/**
* Class generated by AS/400 CRTCLASS command from ESD071101 physical file definition.
* 
* File level identifier is 1030121192004.
* Record format level identifier is 51B5B1A8A796D.
*/

public class ESD071101Message extends MessageRecord
{
  final static String fldnames[] = {
                                     "E01USERID",
                                     "E01SELACD",
                                     "E01RECTYP",
                                     "E01GENERI",
                                     "E01APCBNK",
                                     "E01APCACD",
                                     "E01APCTYP",
                                     "E01DESCRI"
                                   };
  final static String tnames[] = {
                                   "E01USERID",
                                   "E01SELACD",
                                   "E01RECTYP",
                                   "E01GENERI",
                                   "E01APCBNK",
                                   "E01APCACD",
                                   "E01APCTYP",
                                   "E01DESCRI"
                                 };
  final static String fid = "1030121192004";
  final static String rid = "51B5B1A8A796D";
  final static String fmtname = "ESD071101";
  final int FIELDCOUNT = 8;
  private static Hashtable tlookup = new Hashtable();
  private CharacterField fieldE01USERID = null;
  private CharacterField fieldE01SELACD = null;
  private CharacterField fieldE01RECTYP = null;
  private CharacterField fieldE01GENERI = null;
  private CharacterField fieldE01APCBNK = null;
  private CharacterField fieldE01APCACD = null;
  private CharacterField fieldE01APCTYP = null;
  private CharacterField fieldE01DESCRI = null;

  /**
  * Constructor for ESD071101Message.
  */
  public ESD071101Message()
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
    recordsize = 82;
    fileid = fid;
    recordid = rid;
    message = new byte[getByteLength()];
    formatname = fmtname;
    fieldnames = fldnames;
    tagnames = tnames;
    fields = new MessageField[FIELDCOUNT];

    fields[0] = fieldE01USERID =
    new CharacterField(message, HEADERSIZE + 0, 10, "E01USERID");
    fields[1] = fieldE01SELACD =
    new CharacterField(message, HEADERSIZE + 10, 2, "E01SELACD");
    fields[2] = fieldE01RECTYP =
    new CharacterField(message, HEADERSIZE + 12, 1, "E01RECTYP");
    fields[3] = fieldE01GENERI =
    new CharacterField(message, HEADERSIZE + 13, 1, "E01GENERI");
    fields[4] = fieldE01APCBNK =
    new CharacterField(message, HEADERSIZE + 14, 2, "E01APCBNK");
    fields[5] = fieldE01APCACD =
    new CharacterField(message, HEADERSIZE + 16, 2, "E01APCACD");
    fields[6] = fieldE01APCTYP =
    new CharacterField(message, HEADERSIZE + 18, 4, "E01APCTYP");
    fields[7] = fieldE01DESCRI =
    new CharacterField(message, HEADERSIZE + 22, 60, "E01DESCRI");

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
  * Set field E01USERID using a String value.
  */
  public void setE01USERID(String newvalue)
  {
    fieldE01USERID.setString(newvalue);
  }

  /**
  * Get value of field E01USERID as a String.
  */
  public String getE01USERID()
  {
    return fieldE01USERID.getString();
  }

  /**
  * Set field E01SELACD using a String value.
  */
  public void setE01SELACD(String newvalue)
  {
    fieldE01SELACD.setString(newvalue);
  }

  /**
  * Get value of field E01SELACD as a String.
  */
  public String getE01SELACD()
  {
    return fieldE01SELACD.getString();
  }

  /**
  * Set field E01RECTYP using a String value.
  */
  public void setE01RECTYP(String newvalue)
  {
    fieldE01RECTYP.setString(newvalue);
  }

  /**
  * Get value of field E01RECTYP as a String.
  */
  public String getE01RECTYP()
  {
    return fieldE01RECTYP.getString();
  }

  /**
  * Set field E01GENERI using a String value.
  */
  public void setE01GENERI(String newvalue)
  {
    fieldE01GENERI.setString(newvalue);
  }

  /**
  * Get value of field E01GENERI as a String.
  */
  public String getE01GENERI()
  {
    return fieldE01GENERI.getString();
  }

  /**
  * Set field E01APCBNK using a String value.
  */
  public void setE01APCBNK(String newvalue)
  {
    fieldE01APCBNK.setString(newvalue);
  }

  /**
  * Get value of field E01APCBNK as a String.
  */
  public String getE01APCBNK()
  {
    return fieldE01APCBNK.getString();
  }

  /**
  * Set field E01APCACD using a String value.
  */
  public void setE01APCACD(String newvalue)
  {
    fieldE01APCACD.setString(newvalue);
  }

  /**
  * Get value of field E01APCACD as a String.
  */
  public String getE01APCACD()
  {
    return fieldE01APCACD.getString();
  }

  /**
  * Set field E01APCTYP using a String value.
  */
  public void setE01APCTYP(String newvalue)
  {
    fieldE01APCTYP.setString(newvalue);
  }

  /**
  * Get value of field E01APCTYP as a String.
  */
  public String getE01APCTYP()
  {
    return fieldE01APCTYP.getString();
  }

  /**
  * Set field E01DESCRI using a String value.
  */
  public void setE01DESCRI(String newvalue)
  {
    fieldE01DESCRI.setString(newvalue);
  }

  /**
  * Get value of field E01DESCRI as a String.
  */
  public String getE01DESCRI()
  {
    return fieldE01DESCRI.getString();
  }

}