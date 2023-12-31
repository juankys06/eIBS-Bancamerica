package datapro.eibs.beans;

import datapro.eibs.sockets.*;
import java.io.*;
import java.math.*;

import java.util.*;

/**
* Class generated by AS/400 CRTCLASS command from EWD0113DS physical file definition.
* 
* File level identifier is 1030121192026.
* Record format level identifier is 31AF5AA1C2DCC.
*/

public class EWD0113DSMessage extends MessageRecord
{
  final static String fldnames[] = {
                                     "EWDTBL",
                                     "EWDCLS",
                                     "EWDDSC",
                                     "EWDOPE"
                                   };
  final static String tnames[] = {
                                   "EWDTBL",
                                   "EWDCLS",
                                   "EWDDSC",
                                   "EWDOPE"
                                 };
  final static String fid = "1030121192026";
  final static String rid = "31AF5AA1C2DCC";
  final static String fmtname = "EWD0113DS";
  final int FIELDCOUNT = 4;
  private static Hashtable tlookup = new Hashtable();
  private CharacterField fieldEWDTBL = null;
  private CharacterField fieldEWDCLS = null;
  private CharacterField fieldEWDDSC = null;
  private CharacterField fieldEWDOPE = null;

  /**
  * Constructor for EWD0113DSMessage.
  */
  public EWD0113DSMessage()
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

    fields[0] = fieldEWDTBL =
    new CharacterField(message, HEADERSIZE + 0, 2, "EWDTBL");
    fields[1] = fieldEWDCLS =
    new CharacterField(message, HEADERSIZE + 2, 1, "EWDCLS");
    fields[2] = fieldEWDDSC =
    new CharacterField(message, HEADERSIZE + 3, 35, "EWDDSC");
    fields[3] = fieldEWDOPE =
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
  * Set field EWDTBL using a String value.
  */
  public void setEWDTBL(String newvalue)
  {
    fieldEWDTBL.setString(newvalue);
  }

  /**
  * Get value of field EWDTBL as a String.
  */
  public String getEWDTBL()
  {
    return fieldEWDTBL.getString();
  }

  /**
  * Set field EWDCLS using a String value.
  */
  public void setEWDCLS(String newvalue)
  {
    fieldEWDCLS.setString(newvalue);
  }

  /**
  * Get value of field EWDCLS as a String.
  */
  public String getEWDCLS()
  {
    return fieldEWDCLS.getString();
  }

  /**
  * Set field EWDDSC using a String value.
  */
  public void setEWDDSC(String newvalue)
  {
    fieldEWDDSC.setString(newvalue);
  }

  /**
  * Get value of field EWDDSC as a String.
  */
  public String getEWDDSC()
  {
    return fieldEWDDSC.getString();
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
