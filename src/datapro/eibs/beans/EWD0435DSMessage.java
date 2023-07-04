package datapro.eibs.beans;

import datapro.eibs.sockets.*;
import java.io.*;
import java.math.*;

import java.util.*;

/**
* Class generated by AS/400 CRTCLASS command from EWD0435DS physical file definition.
* 
* File level identifier is 1060803182758.
* Record format level identifier is 33A9C888BE7C5.
*/

public class EWD0435DSMessage extends MessageRecord
{
  final static String fldnames[] = {
                                     "RWDUSR",
                                     "RWDFRC",
                                     "SWDGRP",
                                     "SWDNME",
                                     "SWDREC",
                                     "SWDOPE"
                                   };
  final static String tnames[] = {
                                   "RWDUSR",
                                   "RWDFRC",
                                   "SWDGRP",
                                   "SWDNME",
                                   "SWDREC",
                                   "SWDOPE"
                                 };
  final static String fid = "1060803182758";
  final static String rid = "33A9C888BE7C5";
  final static String fmtname = "EWD0435DS";
  final int FIELDCOUNT = 6;
  private static Hashtable tlookup = new Hashtable();
  private CharacterField fieldRWDUSR = null;
  private DecimalField fieldRWDFRC = null;
  private CharacterField fieldSWDGRP = null;
  private CharacterField fieldSWDNME = null;
  private DecimalField fieldSWDREC = null;
  private CharacterField fieldSWDOPE = null;

  /**
  * Constructor for EWD0435DSMessage.
  */
  public EWD0435DSMessage()
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

    fields[0] = fieldRWDUSR =
    new CharacterField(message, HEADERSIZE + 0, 10, "RWDUSR");
    fields[1] = fieldRWDFRC =
    new DecimalField(message, HEADERSIZE + 10, 8, 0, "RWDFRC");
    fields[2] = fieldSWDGRP =
    new CharacterField(message, HEADERSIZE + 18, 10, "SWDGRP");
    fields[3] = fieldSWDNME =
    new CharacterField(message, HEADERSIZE + 28, 45, "SWDNME");
    fields[4] = fieldSWDREC =
    new DecimalField(message, HEADERSIZE + 73, 8, 0, "SWDREC");
    fields[5] = fieldSWDOPE =
    new CharacterField(message, HEADERSIZE + 81, 1, "SWDOPE");

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
  * Set field RWDUSR using a String value.
  */
  public void setRWDUSR(String newvalue)
  {
    fieldRWDUSR.setString(newvalue);
  }

  /**
  * Get value of field RWDUSR as a String.
  */
  public String getRWDUSR()
  {
    return fieldRWDUSR.getString();
  }

  /**
  * Set field RWDFRC using a String value.
  */
  public void setRWDFRC(String newvalue)
  {
    fieldRWDFRC.setString(newvalue);
  }

  /**
  * Get value of field RWDFRC as a String.
  */
  public String getRWDFRC()
  {
    return fieldRWDFRC.getString();
  }

  /**
  * Set numeric field RWDFRC using a BigDecimal value.
  */
  public void setRWDFRC(BigDecimal newvalue)
  {
    fieldRWDFRC.setBigDecimal(newvalue);
  }

  /**
  * Return value of numeric field RWDFRC as a BigDecimal.
  */
  public BigDecimal getBigDecimalRWDFRC()
  {
    return fieldRWDFRC.getBigDecimal();
  }

  /**
  * Set field SWDGRP using a String value.
  */
  public void setSWDGRP(String newvalue)
  {
    fieldSWDGRP.setString(newvalue);
  }

  /**
  * Get value of field SWDGRP as a String.
  */
  public String getSWDGRP()
  {
    return fieldSWDGRP.getString();
  }

  /**
  * Set field SWDNME using a String value.
  */
  public void setSWDNME(String newvalue)
  {
    fieldSWDNME.setString(newvalue);
  }

  /**
  * Get value of field SWDNME as a String.
  */
  public String getSWDNME()
  {
    return fieldSWDNME.getString();
  }

  /**
  * Set field SWDREC using a String value.
  */
  public void setSWDREC(String newvalue)
  {
    fieldSWDREC.setString(newvalue);
  }

  /**
  * Get value of field SWDREC as a String.
  */
  public String getSWDREC()
  {
    return fieldSWDREC.getString();
  }

  /**
  * Set numeric field SWDREC using a BigDecimal value.
  */
  public void setSWDREC(BigDecimal newvalue)
  {
    fieldSWDREC.setBigDecimal(newvalue);
  }

  /**
  * Return value of numeric field SWDREC as a BigDecimal.
  */
  public BigDecimal getBigDecimalSWDREC()
  {
    return fieldSWDREC.getBigDecimal();
  }

  /**
  * Set field SWDOPE using a String value.
  */
  public void setSWDOPE(String newvalue)
  {
    fieldSWDOPE.setString(newvalue);
  }

  /**
  * Get value of field SWDOPE as a String.
  */
  public String getSWDOPE()
  {
    return fieldSWDOPE.getString();
  }

}
