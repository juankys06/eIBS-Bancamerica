package datapro.eibs.beans;

import datapro.eibs.sockets.*;
import java.io.*;
import java.math.*;

import java.util.*;

/**
* Class generated by AS/400 CRTCLASS command from EWD0020DS physical file definition.
* 
* File level identifier is 1030121192018.
* Record format level identifier is 33FEE95709D12.
*/

public class EWD0020DSMessage extends MessageRecord
{
  final static String fldnames[] = {
                                     "EWDNUM",
                                     "EWDNME",
                                     "EWDGRP",
                                     "EWDOPE"
                                   };
  final static String tnames[] = {
                                   "EWDNUM",
                                   "EWDNME",
                                   "EWDGRP",
                                   "EWDOPE"
                                 };
  final static String fid = "1030121192018";
  final static String rid = "33FEE95709D12";
  final static String fmtname = "EWD0020DS";
  final int FIELDCOUNT = 4;
  private static Hashtable tlookup = new Hashtable();
  private DecimalField fieldEWDNUM = null;
  private CharacterField fieldEWDNME = null;
  private CharacterField fieldEWDGRP = null;
  private CharacterField fieldEWDOPE = null;

  /**
  * Constructor for EWD0020DSMessage.
  */
  public EWD0020DSMessage()
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
    recordsize = 43;
    fileid = fid;
    recordid = rid;
    message = new byte[getByteLength()];
    formatname = fmtname;
    fieldnames = fldnames;
    tagnames = tnames;
    fields = new MessageField[FIELDCOUNT];

    fields[0] = fieldEWDNUM =
    new DecimalField(message, HEADERSIZE + 0, 5, 0, "EWDNUM");
    fields[1] = fieldEWDNME =
    new CharacterField(message, HEADERSIZE + 5, 35, "EWDNME");
    fields[2] = fieldEWDGRP =
    new CharacterField(message, HEADERSIZE + 40, 2, "EWDGRP");
    fields[3] = fieldEWDOPE =
    new CharacterField(message, HEADERSIZE + 42, 1, "EWDOPE");

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
  * Set numeric field EWDNUM using a BigDecimal value.
  */
  public void setEWDNUM(BigDecimal newvalue)
  {
    fieldEWDNUM.setBigDecimal(newvalue);
  }

  /**
  * Return value of numeric field EWDNUM as a BigDecimal.
  */
  public BigDecimal getBigDecimalEWDNUM()
  {
    return fieldEWDNUM.getBigDecimal();
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
  * Set field EWDGRP using a String value.
  */
  public void setEWDGRP(String newvalue)
  {
    fieldEWDGRP.setString(newvalue);
  }

  /**
  * Get value of field EWDGRP as a String.
  */
  public String getEWDGRP()
  {
    return fieldEWDGRP.getString();
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
