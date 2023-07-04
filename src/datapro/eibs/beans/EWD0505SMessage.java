package datapro.eibs.beans;

import datapro.eibs.sockets.*;
import java.io.*;
import java.math.*;

import java.util.*;

/**
* Class generated by AS/400 CRTCLASS command from EWD0505S physical file definition.
* 
* File level identifier is 1040721135820.
* Record format level identifier is 4AFC0E5C45944.
*/

public class EWD0505SMessage extends MessageRecord
{
  final static String fldnames[] = {
                                     "SWDCUN",
                                     "SWDNA1",
                                     "SWDSHN",
                                     "SWDIDN",
                                     "SWDTID",
                                     "SWDPID",
                                     "SWDREC",
                                     "SWDOPE",
                                     "SWDACC"
                                   };
  final static String tnames[] = {
                                   "SWDCUN",
                                   "SWDNA1",
                                   "SWDSHN",
                                   "SWDIDN",
                                   "SWDTID",
                                   "SWDPID",
                                   "SWDREC",
                                   "SWDOPE",
                                   "SWDACC"
                                 };
  final static String fid = "1040721135820";
  final static String rid = "4AFC0E5C45944";
  final static String fmtname = "EWD0505S";
  final int FIELDCOUNT = 9;
  private static Hashtable tlookup = new Hashtable();
  private DecimalField fieldSWDCUN = null;
  private CharacterField fieldSWDNA1 = null;
  private CharacterField fieldSWDSHN = null;
  private CharacterField fieldSWDIDN = null;
  private CharacterField fieldSWDTID = null;
  private CharacterField fieldSWDPID = null;
  private DecimalField fieldSWDREC = null;
  private CharacterField fieldSWDOPE = null;
  private DecimalField fieldSWDACC = null;

  /**
  * Constructor for EWD0505SMessage.
  */
  public EWD0505SMessage()
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
    recordsize = 115;
    fileid = fid;
    recordid = rid;
    message = new byte[getByteLength()];
    formatname = fmtname;
    fieldnames = fldnames;
    tagnames = tnames;
    fields = new MessageField[FIELDCOUNT];

    fields[0] = fieldSWDCUN =
    new DecimalField(message, HEADERSIZE + 0, 10, 0, "SWDCUN");
    fields[1] = fieldSWDNA1 =
    new CharacterField(message, HEADERSIZE + 10, 45, "SWDNA1");
    fields[2] = fieldSWDSHN =
    new CharacterField(message, HEADERSIZE + 55, 15, "SWDSHN");
    fields[3] = fieldSWDIDN =
    new CharacterField(message, HEADERSIZE + 70, 15, "SWDIDN");
    fields[4] = fieldSWDTID =
    new CharacterField(message, HEADERSIZE + 85, 4, "SWDTID");
    fields[5] = fieldSWDPID =
    new CharacterField(message, HEADERSIZE + 89, 4, "SWDPID");
    fields[6] = fieldSWDREC =
    new DecimalField(message, HEADERSIZE + 93, 8, 0, "SWDREC");
    fields[7] = fieldSWDOPE =
    new CharacterField(message, HEADERSIZE + 101, 1, "SWDOPE");
    fields[8] = fieldSWDACC =
    new DecimalField(message, HEADERSIZE + 102, 13, 0, "SWDACC");

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
  * Set field SWDCUN using a String value.
  */
  public void setSWDCUN(String newvalue)
  {
    fieldSWDCUN.setString(newvalue);
  }

  /**
  * Get value of field SWDCUN as a String.
  */
  public String getSWDCUN()
  {
    return fieldSWDCUN.getString();
  }

  /**
  * Set numeric field SWDCUN using a BigDecimal value.
  */
  public void setSWDCUN(BigDecimal newvalue)
  {
    fieldSWDCUN.setBigDecimal(newvalue);
  }

  /**
  * Return value of numeric field SWDCUN as a BigDecimal.
  */
  public BigDecimal getBigDecimalSWDCUN()
  {
    return fieldSWDCUN.getBigDecimal();
  }

  /**
  * Set field SWDNA1 using a String value.
  */
  public void setSWDNA1(String newvalue)
  {
    fieldSWDNA1.setString(newvalue);
  }

  /**
  * Get value of field SWDNA1 as a String.
  */
  public String getSWDNA1()
  {
    return fieldSWDNA1.getString();
  }

  /**
  * Set field SWDSHN using a String value.
  */
  public void setSWDSHN(String newvalue)
  {
    fieldSWDSHN.setString(newvalue);
  }

  /**
  * Get value of field SWDSHN as a String.
  */
  public String getSWDSHN()
  {
    return fieldSWDSHN.getString();
  }

  /**
  * Set field SWDIDN using a String value.
  */
  public void setSWDIDN(String newvalue)
  {
    fieldSWDIDN.setString(newvalue);
  }

  /**
  * Get value of field SWDIDN as a String.
  */
  public String getSWDIDN()
  {
    return fieldSWDIDN.getString();
  }

  /**
  * Set field SWDTID using a String value.
  */
  public void setSWDTID(String newvalue)
  {
    fieldSWDTID.setString(newvalue);
  }

  /**
  * Get value of field SWDTID as a String.
  */
  public String getSWDTID()
  {
    return fieldSWDTID.getString();
  }

  /**
  * Set field SWDPID using a String value.
  */
  public void setSWDPID(String newvalue)
  {
    fieldSWDPID.setString(newvalue);
  }

  /**
  * Get value of field SWDPID as a String.
  */
  public String getSWDPID()
  {
    return fieldSWDPID.getString();
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

  /**
  * Set field SWDACC using a String value.
  */
  public void setSWDACC(String newvalue)
  {
    fieldSWDACC.setString(newvalue);
  }

  /**
  * Get value of field SWDACC as a String.
  */
  public String getSWDACC()
  {
    return fieldSWDACC.getString();
  }

  /**
  * Set numeric field SWDACC using a BigDecimal value.
  */
  public void setSWDACC(BigDecimal newvalue)
  {
    fieldSWDACC.setBigDecimal(newvalue);
  }

  /**
  * Return value of numeric field SWDACC as a BigDecimal.
  */
  public BigDecimal getBigDecimalSWDACC()
  {
    return fieldSWDACC.getBigDecimal();
  }

}
