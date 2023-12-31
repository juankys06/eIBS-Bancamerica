package datapro.eibs.beans;

import datapro.eibs.sockets.*;
import java.io.*;
import java.math.*;

import java.util.*;

/**
* Class generated by AS/400 CRTCLASS command from MSGOUT physical file definition.
* 
* File level identifier is 1050422122941.
* Record format level identifier is 461AF4C09E3C8.
*/

public class MSGOUTMessage extends MessageRecord
{
  final static String fldnames[] = {
                                     "ERRORFLAG",
                                     "SUPERFLAG",
                                     "MESSAGE",
                                     "MSGDESC",
                                     "SEQUENCE"
                                   };
  final static String tnames[] = {
                                   "ERRORFLAG",
                                   "SUPERFLAG",
                                   "MESSAGE",
                                   "MSGDESC",
                                   "SEQUENCE"
                                 };
  final static String fid = "1050422122941";
  final static String rid = "461AF4C09E3C8";
  final static String fmtname = "MSGOUT";
  final int FIELDCOUNT = 5;
  private static Hashtable tlookup = new Hashtable();
  private CharacterField fieldERRORFLAG = null;
  private CharacterField fieldSUPERFLAG = null;
  private CharacterField fieldMESSAGE = null;
  private CharacterField fieldMSGDESC = null;
  private DecimalField fieldSEQUENCE = null;

  /**
  * Constructor for MSGOUTMessage.
  */
  public MSGOUTMessage()
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
    recordsize = 378;
    fileid = fid;
    recordid = rid;
    message = new byte[getByteLength()];
    formatname = fmtname;
    fieldnames = fldnames;
    tagnames = tnames;
    fields = new MessageField[FIELDCOUNT];

    fields[0] = fieldERRORFLAG =
    new CharacterField(message, HEADERSIZE + 0, 1, "ERRORFLAG");
    fields[1] = fieldSUPERFLAG =
    new CharacterField(message, HEADERSIZE + 1, 1, "SUPERFLAG");
    fields[2] = fieldMESSAGE =
    new CharacterField(message, HEADERSIZE + 2, 15, "MESSAGE");
    fields[3] = fieldMSGDESC =
    new CharacterField(message, HEADERSIZE + 17, 350, "MSGDESC");
    fields[4] = fieldSEQUENCE =
    new DecimalField(message, HEADERSIZE + 367, 11, 0, "SEQUENCE");

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
  * Set field ERRORFLAG using a String value.
  */
  public void setERRORFLAG(String newvalue)
  {
    fieldERRORFLAG.setString(newvalue);
  }

  /**
  * Get value of field ERRORFLAG as a String.
  */
  public String getERRORFLAG()
  {
    return fieldERRORFLAG.getString();
  }

  /**
  * Set field SUPERFLAG using a String value.
  */
  public void setSUPERFLAG(String newvalue)
  {
    fieldSUPERFLAG.setString(newvalue);
  }

  /**
  * Get value of field SUPERFLAG as a String.
  */
  public String getSUPERFLAG()
  {
    return fieldSUPERFLAG.getString();
  }

  /**
  * Set field MESSAGE using a String value.
  */
  public void setMESSAGE(String newvalue)
  {
    fieldMESSAGE.setString(newvalue);
  }

  /**
  * Get value of field MESSAGE as a String.
  */
  public String getMESSAGE()
  {
    return fieldMESSAGE.getString();
  }

  /**
  * Set field MSGDESC using a String value.
  */
  public void setMSGDESC(String newvalue)
  {
    fieldMSGDESC.setString(newvalue);
  }

  /**
  * Get value of field MSGDESC as a String.
  */
  public String getMSGDESC()
  {
    return fieldMSGDESC.getString();
  }

  /**
  * Set field SEQUENCE using a String value.
  */
  public void setSEQUENCE(String newvalue)
  {
    fieldSEQUENCE.setString(newvalue);
  }

  /**
  * Get value of field SEQUENCE as a String.
  */
  public String getSEQUENCE()
  {
    return fieldSEQUENCE.getString();
  }

  /**
  * Set numeric field SEQUENCE using a BigDecimal value.
  */
  public void setSEQUENCE(BigDecimal newvalue)
  {
    fieldSEQUENCE.setBigDecimal(newvalue);
  }

  /**
  * Return value of numeric field SEQUENCE as a BigDecimal.
  */
  public BigDecimal getBigDecimalSEQUENCE()
  {
    return fieldSEQUENCE.getBigDecimal();
  }

}
