package datapro.eibs.beans;

import datapro.eibs.sockets.*;
import java.io.*;
import java.math.*;

import java.util.*;

/**
* Class generated by AS/400 CRTCLASS command from EOFNUMDS physical file definition.
* 
* File level identifier is 1030122171220.
* Record format level identifier is 42BA3E1D3C9B9.
*/

public class EOFNUMDSMessage extends MessageRecord
{
  final static String fldnames[] = {
                                     "EOFNNUM",
                                     "EOFBNK",
                                     "EOFBRN",
                                     "EOFCCY",
                                     "EOFFORN",
                                     "EOFPARM"
                                   };
  final static String tnames[] = {
                                   "EOFNNUM",
                                   "EOFBNK",
                                   "EOFBRN",
                                   "EOFCCY",
                                   "EOFFORN",
                                   "EOFPARM"
                                 };
  final static String fid = "1030122171220";
  final static String rid = "42BA3E1D3C9B9";
  final static String fmtname = "EOFNUMDS";
  final int FIELDCOUNT = 6;
  private static Hashtable tlookup = new Hashtable();
  private DecimalField fieldEOFNNUM = null;
  private CharacterField fieldEOFBNK = null;
  private DecimalField fieldEOFBRN = null;
  private CharacterField fieldEOFCCY = null;
  private CharacterField fieldEOFFORN = null;
  private CharacterField fieldEOFPARM = null;

  /**
  * Constructor for EOFNUMDSMessage.
  */
  public EOFNUMDSMessage()
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
    recordsize = 21;
    fileid = fid;
    recordid = rid;
    message = new byte[getByteLength()];
    formatname = fmtname;
    fieldnames = fldnames;
    tagnames = tnames;
    fields = new MessageField[FIELDCOUNT];

    fields[0] = fieldEOFNNUM =
    new DecimalField(message, HEADERSIZE + 0, 10, 0, "EOFNNUM");
    fields[1] = fieldEOFBNK =
    new CharacterField(message, HEADERSIZE + 10, 2, "EOFBNK");
    fields[2] = fieldEOFBRN =
    new DecimalField(message, HEADERSIZE + 12, 4, 0, "EOFBRN");
    fields[3] = fieldEOFCCY =
    new CharacterField(message, HEADERSIZE + 16, 3, "EOFCCY");
    fields[4] = fieldEOFFORN =
    new CharacterField(message, HEADERSIZE + 19, 1, "EOFFORN");
    fields[5] = fieldEOFPARM =
    new CharacterField(message, HEADERSIZE + 20, 1, "EOFPARM");

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
  * Set field EOFNNUM using a String value.
  */
  public void setEOFNNUM(String newvalue)
  {
    fieldEOFNNUM.setString(newvalue);
  }

  /**
  * Get value of field EOFNNUM as a String.
  */
  public String getEOFNNUM()
  {
    return fieldEOFNNUM.getString();
  }

  /**
  * Set numeric field EOFNNUM using a BigDecimal value.
  */
  public void setEOFNNUM(BigDecimal newvalue)
  {
    fieldEOFNNUM.setBigDecimal(newvalue);
  }

  /**
  * Return value of numeric field EOFNNUM as a BigDecimal.
  */
  public BigDecimal getBigDecimalEOFNNUM()
  {
    return fieldEOFNNUM.getBigDecimal();
  }

  /**
  * Set field EOFBNK using a String value.
  */
  public void setEOFBNK(String newvalue)
  {
    fieldEOFBNK.setString(newvalue);
  }

  /**
  * Get value of field EOFBNK as a String.
  */
  public String getEOFBNK()
  {
    return fieldEOFBNK.getString();
  }

  /**
  * Set field EOFBRN using a String value.
  */
  public void setEOFBRN(String newvalue)
  {
    fieldEOFBRN.setString(newvalue);
  }

  /**
  * Get value of field EOFBRN as a String.
  */
  public String getEOFBRN()
  {
    return fieldEOFBRN.getString();
  }

  /**
  * Set numeric field EOFBRN using a BigDecimal value.
  */
  public void setEOFBRN(BigDecimal newvalue)
  {
    fieldEOFBRN.setBigDecimal(newvalue);
  }

  /**
  * Return value of numeric field EOFBRN as a BigDecimal.
  */
  public BigDecimal getBigDecimalEOFBRN()
  {
    return fieldEOFBRN.getBigDecimal();
  }

  /**
  * Set field EOFCCY using a String value.
  */
  public void setEOFCCY(String newvalue)
  {
    fieldEOFCCY.setString(newvalue);
  }

  /**
  * Get value of field EOFCCY as a String.
  */
  public String getEOFCCY()
  {
    return fieldEOFCCY.getString();
  }

  /**
  * Set field EOFFORN using a String value.
  */
  public void setEOFFORN(String newvalue)
  {
    fieldEOFFORN.setString(newvalue);
  }

  /**
  * Get value of field EOFFORN as a String.
  */
  public String getEOFFORN()
  {
    return fieldEOFFORN.getString();
  }

  /**
  * Set field EOFPARM using a String value.
  */
  public void setEOFPARM(String newvalue)
  {
    fieldEOFPARM.setString(newvalue);
  }

  /**
  * Get value of field EOFPARM as a String.
  */
  public String getEOFPARM()
  {
    return fieldEOFPARM.getString();
  }

}