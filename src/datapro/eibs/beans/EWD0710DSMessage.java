package datapro.eibs.beans;

import datapro.eibs.sockets.*;
import java.io.*;
import java.math.*;

import java.util.*;

/**
* Class generated by AS/400 CRTCLASS command from EWD0710DS physical file definition.
* 
* File level identifier is 1091027172318.
* Record format level identifier is 32634244C120D.
*/

public class EWD0710DSMessage extends MessageRecord
{
  final static String fldnames[] = {
                                     "EWDSEL",
                                     "EWDTCN",
                                     "EWDDCN",
                                     "EWDREC",
                                     "EWDOPE"
                                   };
  final static String tnames[] = {
                                   "EWDSEL",
                                   "EWDTCN",
                                   "EWDDCN",
                                   "EWDREC",
                                   "EWDOPE"
                                 };
  final static String fid = "1091027172318";
  final static String rid = "32634244C120D";
  final static String fmtname = "EWD0710DS";
  final int FIELDCOUNT = 5;
  private static Hashtable tlookup = new Hashtable();
  private CharacterField fieldEWDSEL = null;
  private CharacterField fieldEWDTCN = null;
  private CharacterField fieldEWDDCN = null;
  private DecimalField fieldEWDREC = null;
  private CharacterField fieldEWDOPE = null;

  /**
  * Constructor for EWD0710DSMessage.
  */
  public EWD0710DSMessage()
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
    recordsize = 47;
    fileid = fid;
    recordid = rid;
    message = new byte[getByteLength()];
    formatname = fmtname;
    fieldnames = fldnames;
    tagnames = tnames;
    fields = new MessageField[FIELDCOUNT];

    fields[0] = fieldEWDSEL =
    new CharacterField(message, HEADERSIZE + 0, 1, "EWDSEL");
    fields[1] = fieldEWDTCN =
    new CharacterField(message, HEADERSIZE + 1, 2, "EWDTCN");
    fields[2] = fieldEWDDCN =
    new CharacterField(message, HEADERSIZE + 3, 35, "EWDDCN");
    fields[3] = fieldEWDREC =
    new DecimalField(message, HEADERSIZE + 38, 8, 0, "EWDREC");
    fields[4] = fieldEWDOPE =
    new CharacterField(message, HEADERSIZE + 46, 1, "EWDOPE");

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
  * Set field EWDSEL using a String value.
  */
  public void setEWDSEL(String newvalue)
  {
    fieldEWDSEL.setString(newvalue);
  }

  /**
  * Get value of field EWDSEL as a String.
  */
  public String getEWDSEL()
  {
    return fieldEWDSEL.getString();
  }

  /**
  * Set field EWDTCN using a String value.
  */
  public void setEWDTCN(String newvalue)
  {
    fieldEWDTCN.setString(newvalue);
  }

  /**
  * Get value of field EWDTCN as a String.
  */
  public String getEWDTCN()
  {
    return fieldEWDTCN.getString();
  }

  /**
  * Set field EWDDCN using a String value.
  */
  public void setEWDDCN(String newvalue)
  {
    fieldEWDDCN.setString(newvalue);
  }

  /**
  * Get value of field EWDDCN as a String.
  */
  public String getEWDDCN()
  {
    return fieldEWDDCN.getString();
  }

  /**
  * Set field EWDREC using a String value.
  */
  public void setEWDREC(String newvalue)
  {
    fieldEWDREC.setString(newvalue);
  }

  /**
  * Get value of field EWDREC as a String.
  */
  public String getEWDREC()
  {
    return fieldEWDREC.getString();
  }

  /**
  * Set numeric field EWDREC using a BigDecimal value.
  */
  public void setEWDREC(BigDecimal newvalue)
  {
    fieldEWDREC.setBigDecimal(newvalue);
  }

  /**
  * Return value of numeric field EWDREC as a BigDecimal.
  */
  public BigDecimal getBigDecimalEWDREC()
  {
    return fieldEWDREC.getBigDecimal();
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