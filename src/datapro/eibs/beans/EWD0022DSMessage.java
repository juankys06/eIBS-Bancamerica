package datapro.eibs.beans;

import datapro.eibs.sockets.*;
import java.io.*;
import java.math.*;

import java.util.*;

/**
* Class generated by AS/400 CRTCLASS command from EWD0022DS physical file definition.
* 
* File level identifier is 1030121192019.
* Record format level identifier is 3923BA172411B.
*/

public class EWD0022DSMessage extends MessageRecord
{
  final static String fldnames[] = {
                                     "EWDSLV",
                                     "EWDCDC",
                                     "EWDNTX",
                                     "EWDDTS",
                                     "EWDOPE"
                                   };
  final static String tnames[] = {
                                   "EWDSLV",
                                   "EWDCDC",
                                   "EWDNTX",
                                   "EWDDTS",
                                   "EWDOPE"
                                 };
  final static String fid = "1030121192019";
  final static String rid = "3923BA172411B";
  final static String fmtname = "EWD0022DS";
  final int FIELDCOUNT = 5;
  private static Hashtable tlookup = new Hashtable();
  private CharacterField fieldEWDSLV = null;
  private CharacterField fieldEWDCDC = null;
  private DecimalField fieldEWDNTX = null;
  private CharacterField fieldEWDDTS = null;
  private CharacterField fieldEWDOPE = null;

  /**
  * Constructor for EWD0022DSMessage.
  */
  public EWD0022DSMessage()
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
    recordsize = 58;
    fileid = fid;
    recordid = rid;
    message = new byte[getByteLength()];
    formatname = fmtname;
    fieldnames = fldnames;
    tagnames = tnames;
    fields = new MessageField[FIELDCOUNT];

    fields[0] = fieldEWDSLV =
    new CharacterField(message, HEADERSIZE + 0, 1, "EWDSLV");
    fields[1] = fieldEWDCDC =
    new CharacterField(message, HEADERSIZE + 1, 8, "EWDCDC");
    fields[2] = fieldEWDNTX =
    new DecimalField(message, HEADERSIZE + 9, 3, 0, "EWDNTX");
    fields[3] = fieldEWDDTS =
    new CharacterField(message, HEADERSIZE + 12, 45, "EWDDTS");
    fields[4] = fieldEWDOPE =
    new CharacterField(message, HEADERSIZE + 57, 1, "EWDOPE");

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
  * Set field EWDSLV using a String value.
  */
  public void setEWDSLV(String newvalue)
  {
    fieldEWDSLV.setString(newvalue);
  }

  /**
  * Get value of field EWDSLV as a String.
  */
  public String getEWDSLV()
  {
    return fieldEWDSLV.getString();
  }

  /**
  * Set field EWDCDC using a String value.
  */
  public void setEWDCDC(String newvalue)
  {
    fieldEWDCDC.setString(newvalue);
  }

  /**
  * Get value of field EWDCDC as a String.
  */
  public String getEWDCDC()
  {
    return fieldEWDCDC.getString();
  }

  /**
  * Set field EWDNTX using a String value.
  */
  public void setEWDNTX(String newvalue)
  {
    fieldEWDNTX.setString(newvalue);
  }

  /**
  * Get value of field EWDNTX as a String.
  */
  public String getEWDNTX()
  {
    return fieldEWDNTX.getString();
  }

  /**
  * Set numeric field EWDNTX using a BigDecimal value.
  */
  public void setEWDNTX(BigDecimal newvalue)
  {
    fieldEWDNTX.setBigDecimal(newvalue);
  }

  /**
  * Return value of numeric field EWDNTX as a BigDecimal.
  */
  public BigDecimal getBigDecimalEWDNTX()
  {
    return fieldEWDNTX.getBigDecimal();
  }

  /**
  * Set field EWDDTS using a String value.
  */
  public void setEWDDTS(String newvalue)
  {
    fieldEWDDTS.setString(newvalue);
  }

  /**
  * Get value of field EWDDTS as a String.
  */
  public String getEWDDTS()
  {
    return fieldEWDDTS.getString();
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