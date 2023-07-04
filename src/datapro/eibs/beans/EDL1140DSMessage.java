package datapro.eibs.beans;

import datapro.eibs.sockets.*;
import java.io.*;
import java.math.*;

import java.util.*;

/**
* Class generated by AS/400 CRTCLASS command from EDL1140DS physical file definition.
* 
* File level identifier is 1030121191915.
* Record format level identifier is 431E60D3D1A7E.
*/

public class EDL1140DSMessage extends MessageRecord
{
  final static String fldnames[] = {
                                     "PAWACD",
                                     "PAWTYP",
                                     "PAWARF",
                                     "PAWUSR",
                                     "PAWSAC"
                                   };
  final static String tnames[] = {
                                   "PAWACD",
                                   "PAWTYP",
                                   "PAWARF",
                                   "PAWUSR",
                                   "PAWSAC"
                                 };
  final static String fid = "1030121191915";
  final static String rid = "431E60D3D1A7E";
  final static String fmtname = "EDL1140DS";
  final int FIELDCOUNT = 5;
  private static Hashtable tlookup = new Hashtable();
  private CharacterField fieldPAWACD = null;
  private CharacterField fieldPAWTYP = null;
  private DecimalField fieldPAWARF = null;
  private CharacterField fieldPAWUSR = null;
  private DecimalField fieldPAWSAC = null;

  /**
  * Constructor for EDL1140DSMessage.
  */
  public EDL1140DSMessage()
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

    fields[0] = fieldPAWACD =
    new CharacterField(message, HEADERSIZE + 0, 2, "PAWACD");
    fields[1] = fieldPAWTYP =
    new CharacterField(message, HEADERSIZE + 2, 4, "PAWTYP");
    fields[2] = fieldPAWARF =
    new DecimalField(message, HEADERSIZE + 6, 13, 0, "PAWARF");
    fields[3] = fieldPAWUSR =
    new CharacterField(message, HEADERSIZE + 19, 10, "PAWUSR");
    fields[4] = fieldPAWSAC =
    new DecimalField(message, HEADERSIZE + 29, 10, 0, "PAWSAC");

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
  * Set field PAWACD using a String value.
  */
  public void setPAWACD(String newvalue)
  {
    fieldPAWACD.setString(newvalue);
  }

  /**
  * Get value of field PAWACD as a String.
  */
  public String getPAWACD()
  {
    return fieldPAWACD.getString();
  }

  /**
  * Set field PAWTYP using a String value.
  */
  public void setPAWTYP(String newvalue)
  {
    fieldPAWTYP.setString(newvalue);
  }

  /**
  * Get value of field PAWTYP as a String.
  */
  public String getPAWTYP()
  {
    return fieldPAWTYP.getString();
  }

  /**
  * Set field PAWARF using a String value.
  */
  public void setPAWARF(String newvalue)
  {
    fieldPAWARF.setString(newvalue);
  }

  /**
  * Get value of field PAWARF as a String.
  */
  public String getPAWARF()
  {
    return fieldPAWARF.getString();
  }

  /**
  * Set numeric field PAWARF using a BigDecimal value.
  */
  public void setPAWARF(BigDecimal newvalue)
  {
    fieldPAWARF.setBigDecimal(newvalue);
  }

  /**
  * Return value of numeric field PAWARF as a BigDecimal.
  */
  public BigDecimal getBigDecimalPAWARF()
  {
    return fieldPAWARF.getBigDecimal();
  }

  /**
  * Set field PAWUSR using a String value.
  */
  public void setPAWUSR(String newvalue)
  {
    fieldPAWUSR.setString(newvalue);
  }

  /**
  * Get value of field PAWUSR as a String.
  */
  public String getPAWUSR()
  {
    return fieldPAWUSR.getString();
  }

  /**
  * Set field PAWSAC using a String value.
  */
  public void setPAWSAC(String newvalue)
  {
    fieldPAWSAC.setString(newvalue);
  }

  /**
  * Get value of field PAWSAC as a String.
  */
  public String getPAWSAC()
  {
    return fieldPAWSAC.getString();
  }

  /**
  * Set numeric field PAWSAC using a BigDecimal value.
  */
  public void setPAWSAC(BigDecimal newvalue)
  {
    fieldPAWSAC.setBigDecimal(newvalue);
  }

  /**
  * Return value of numeric field PAWSAC as a BigDecimal.
  */
  public BigDecimal getBigDecimalPAWSAC()
  {
    return fieldPAWSAC.getBigDecimal();
  }

}
