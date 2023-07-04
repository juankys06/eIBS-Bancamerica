package datapro.eibs.beans;

import datapro.eibs.sockets.*;
import java.io.*;
import java.math.*;

import java.util.*;

/**
* Class generated by AS/400 CRTCLASS command from EWD0525DS physical file definition.
* 
* File level identifier is 1050328133640.
* Record format level identifier is 30000581D37DD.
*/

public class EWD0525DSMessage extends MessageRecord
{
  final static String fldnames[] = {
                                     "EWDCIA",
                                     "EWDSRV",
                                     "EWDSEQ",
                                     "EWDOPE"
                                   };
  final static String tnames[] = {
                                   "EWDCIA",
                                   "EWDSRV",
                                   "EWDSEQ",
                                   "EWDOPE"
                                 };
  final static String fid = "1050328133640";
  final static String rid = "30000581D37DD";
  final static String fmtname = "EWD0525DS";
  final int FIELDCOUNT = 4;
  private static Hashtable tlookup = new Hashtable();
  private CharacterField fieldEWDCIA = null;
  private CharacterField fieldEWDSRV = null;
  private DecimalField fieldEWDSEQ = null;
  private CharacterField fieldEWDOPE = null;

  /**
  * Constructor for EWD0525DSMessage.
  */
  public EWD0525DSMessage()
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
    recordsize = 15;
    fileid = fid;
    recordid = rid;
    message = new byte[getByteLength()];
    formatname = fmtname;
    fieldnames = fldnames;
    tagnames = tnames;
    fields = new MessageField[FIELDCOUNT];

    fields[0] = fieldEWDCIA =
    new CharacterField(message, HEADERSIZE + 0, 4, "EWDCIA");
    fields[1] = fieldEWDSRV =
    new CharacterField(message, HEADERSIZE + 4, 4, "EWDSRV");
    fields[2] = fieldEWDSEQ =
    new DecimalField(message, HEADERSIZE + 8, 6, 0, "EWDSEQ");
    fields[3] = fieldEWDOPE =
    new CharacterField(message, HEADERSIZE + 14, 1, "EWDOPE");

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
  * Set field EWDCIA using a String value.
  */
  public void setEWDCIA(String newvalue)
  {
    fieldEWDCIA.setString(newvalue);
  }

  /**
  * Get value of field EWDCIA as a String.
  */
  public String getEWDCIA()
  {
    return fieldEWDCIA.getString();
  }

  /**
  * Set field EWDSRV using a String value.
  */
  public void setEWDSRV(String newvalue)
  {
    fieldEWDSRV.setString(newvalue);
  }

  /**
  * Get value of field EWDSRV as a String.
  */
  public String getEWDSRV()
  {
    return fieldEWDSRV.getString();
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
