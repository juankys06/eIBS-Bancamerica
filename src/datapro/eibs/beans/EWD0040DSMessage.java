package datapro.eibs.beans;

import datapro.eibs.sockets.*;
import java.io.*;
import java.math.*;

import java.util.*;

/**
* Class generated by AS/400 CRTCLASS command from EWD0040DS physical file definition.
* 
* File level identifier is 1030121192022.
* Record format level identifier is 30F10495A450A.
*/

public class EWD0040DSMessage extends MessageRecord
{
  final static String fldnames[] = {
                                     "EWDDRR",
                                     "EWDSEQ",
                                     "EWDNAR",
                                     "EWDOPE"
                                   };
  final static String tnames[] = {
                                   "EWDDRR",
                                   "EWDSEQ",
                                   "EWDNAR",
                                   "EWDOPE"
                                 };
  final static String fid = "1030121192022";
  final static String rid = "30F10495A450A";
  final static String fmtname = "EWD0040DS";
  final int FIELDCOUNT = 4;
  private static Hashtable tlookup = new Hashtable();
  private DecimalField fieldEWDDRR = null;
  private DecimalField fieldEWDSEQ = null;
  private CharacterField fieldEWDNAR = null;
  private CharacterField fieldEWDOPE = null;

  /**
  * Constructor for EWD0040DSMessage.
  */
  public EWD0040DSMessage()
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
    recordsize = 42;
    fileid = fid;
    recordid = rid;
    message = new byte[getByteLength()];
    formatname = fmtname;
    fieldnames = fldnames;
    tagnames = tnames;
    fields = new MessageField[FIELDCOUNT];

    fields[0] = fieldEWDDRR =
    new DecimalField(message, HEADERSIZE + 0, 8, 0, "EWDDRR");
    fields[1] = fieldEWDSEQ =
    new DecimalField(message, HEADERSIZE + 8, 3, 0, "EWDSEQ");
    fields[2] = fieldEWDNAR =
    new CharacterField(message, HEADERSIZE + 11, 30, "EWDNAR");
    fields[3] = fieldEWDOPE =
    new CharacterField(message, HEADERSIZE + 41, 1, "EWDOPE");

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
  * Set field EWDDRR using a String value.
  */
  public void setEWDDRR(String newvalue)
  {
    fieldEWDDRR.setString(newvalue);
  }

  /**
  * Get value of field EWDDRR as a String.
  */
  public String getEWDDRR()
  {
    return fieldEWDDRR.getString();
  }

  /**
  * Set numeric field EWDDRR using a BigDecimal value.
  */
  public void setEWDDRR(BigDecimal newvalue)
  {
    fieldEWDDRR.setBigDecimal(newvalue);
  }

  /**
  * Return value of numeric field EWDDRR as a BigDecimal.
  */
  public BigDecimal getBigDecimalEWDDRR()
  {
    return fieldEWDDRR.getBigDecimal();
  }

  /**
  * Set field EWDSEQ using a String value.
  */
  public void setEWDSEQ(String newvalue)
  {
    fieldEWDSEQ.setString(newvalue);
  }

  /**
  * Get value of field EWDSEQ as a String.
  */
  public String getEWDSEQ()
  {
    return fieldEWDSEQ.getString();
  }

  /**
  * Set numeric field EWDSEQ using a BigDecimal value.
  */
  public void setEWDSEQ(BigDecimal newvalue)
  {
    fieldEWDSEQ.setBigDecimal(newvalue);
  }

  /**
  * Return value of numeric field EWDSEQ as a BigDecimal.
  */
  public BigDecimal getBigDecimalEWDSEQ()
  {
    return fieldEWDSEQ.getBigDecimal();
  }

  /**
  * Set field EWDNAR using a String value.
  */
  public void setEWDNAR(String newvalue)
  {
    fieldEWDNAR.setString(newvalue);
  }

  /**
  * Get value of field EWDNAR as a String.
  */
  public String getEWDNAR()
  {
    return fieldEWDNAR.getString();
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
