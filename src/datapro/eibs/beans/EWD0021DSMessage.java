package datapro.eibs.beans;

import datapro.eibs.sockets.*;
import java.io.*;
import java.math.*;

import java.util.*;

/**
* Class generated by AS/400 CRTCLASS command from EWD0021DS physical file definition.
* 
* File level identifier is 1030121192018.
* Record format level identifier is 38ECECD1472BA.
*/

public class EWD0021DSMessage extends MessageRecord
{
  final static String fldnames[] = {
                                     "EWDNUM",
                                     "EWDNME",
                                     "EWDOPE"
                                   };
  final static String tnames[] = {
                                   "EWDNUM",
                                   "EWDNME",
                                   "EWDOPE"
                                 };
  final static String fid = "1030121192018";
  final static String rid = "38ECECD1472BA";
  final static String fmtname = "EWD0021DS";
  final int FIELDCOUNT = 3;
  private static Hashtable tlookup = new Hashtable();
  private CharacterField fieldEWDNUM = null;
  private CharacterField fieldEWDNME = null;
  private CharacterField fieldEWDOPE = null;

  /**
  * Constructor for EWD0021DSMessage.
  */
  public EWD0021DSMessage()
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
    recordsize = 39;
    fileid = fid;
    recordid = rid;
    message = new byte[getByteLength()];
    formatname = fmtname;
    fieldnames = fldnames;
    tagnames = tnames;
    fields = new MessageField[FIELDCOUNT];

    fields[0] = fieldEWDNUM =
    new CharacterField(message, HEADERSIZE + 0, 3, "EWDNUM");
    fields[1] = fieldEWDNME =
    new CharacterField(message, HEADERSIZE + 3, 35, "EWDNME");
    fields[2] = fieldEWDOPE =
    new CharacterField(message, HEADERSIZE + 38, 1, "EWDOPE");

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
  * Set field EWDNUM using a String value.
  */
  public void setEWDNUM(String newvalue)
  {
    fieldEWDNUM.setString(newvalue);
  }

  /**
  * Get value of field EWDNUM as a String.
  */
  public String getEWDNUM()
  {
    return fieldEWDNUM.getString();
  }

  /**
  * Set field EWDNME using a String value.
  */
  public void setEWDNME(String newvalue)
  {
    fieldEWDNME.setString(newvalue);
  }

  /**
  * Get value of field EWDNME as a String.
  */
  public String getEWDNME()
  {
    return fieldEWDNME.getString();
  }

  /**
  * Set field EWDOPE using a String value.
  */
  public void setEWDOPE(String newvalue)
  {
    fieldEWDOPE.setString(newvalue);
  }

  /**
  * Get value of field EWDOPE as a String.
  */
  public String getEWDOPE()
  {
    return fieldEWDOPE.getString();
  }

}