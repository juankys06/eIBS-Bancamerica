package datapro.eibs.beans;

import datapro.eibs.sockets.*;
import java.io.*;
import java.math.*;

import java.util.*;

/**
* Class generated by AS/400 CRTCLASS command from EWD0010DS physical file definition.
* 
* File level identifier is 1030121192016.
* Record format level identifier is 2F2C2C3DA528A.
*/

public class EWD0010DSMessage extends MessageRecord
{
  final static String fldnames[] = {
                                     "EWDSBK",
                                     "EWDSCY",
                                     "EWDSGL",
                                     "EWDSCA",
                                     "EWDSTY",
                                     "EWDBNK",
                                     "EWDCCY",
                                     "EWDGLN",
                                     "EWDDSC",
                                     "EWDCLS",
                                     "EWDATY",
                                     "EWDACD",
                                     "EWDREC",
                                     "EWDOPE"
                                   };
  final static String tnames[] = {
                                   "EWDSBK",
                                   "EWDSCY",
                                   "EWDSGL",
                                   "EWDSCA",
                                   "EWDSTY",
                                   "EWDBNK",
                                   "EWDCCY",
                                   "EWDGLN",
                                   "EWDDSC",
                                   "EWDCLS",
                                   "EWDATY",
                                   "EWDACD",
                                   "EWDREC",
                                   "EWDOPE"
                                 };
  final static String fid = "1030121192016";
  final static String rid = "2F2C2C3DA528A";
  final static String fmtname = "EWD0010DS";
  final int FIELDCOUNT = 14;
  private static Hashtable tlookup = new Hashtable();
  private CharacterField fieldEWDSBK = null;
  private CharacterField fieldEWDSCY = null;
  private DecimalField fieldEWDSGL = null;
  private CharacterField fieldEWDSCA = null;
  private CharacterField fieldEWDSTY = null;
  private CharacterField fieldEWDBNK = null;
  private CharacterField fieldEWDCCY = null;
  private DecimalField fieldEWDGLN = null;
  private CharacterField fieldEWDDSC = null;
  private DecimalField fieldEWDCLS = null;
  private CharacterField fieldEWDATY = null;
  private CharacterField fieldEWDACD = null;
  private DecimalField fieldEWDREC = null;
  private CharacterField fieldEWDOPE = null;

  /**
  * Constructor for EWD0010DSMessage.
  */
  public EWD0010DSMessage()
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
    recordsize = 103;
    fileid = fid;
    recordid = rid;
    message = new byte[getByteLength()];
    formatname = fmtname;
    fieldnames = fldnames;
    tagnames = tnames;
    fields = new MessageField[FIELDCOUNT];

    fields[0] = fieldEWDSBK =
    new CharacterField(message, HEADERSIZE + 0, 2, "EWDSBK");
    fields[1] = fieldEWDSCY =
    new CharacterField(message, HEADERSIZE + 2, 3, "EWDSCY");
    fields[2] = fieldEWDSGL =
    new DecimalField(message, HEADERSIZE + 5, 17, 0, "EWDSGL");
    fields[3] = fieldEWDSCA =
    new CharacterField(message, HEADERSIZE + 22, 2, "EWDSCA");
    fields[4] = fieldEWDSTY =
    new CharacterField(message, HEADERSIZE + 24, 4, "EWDSTY");
    fields[5] = fieldEWDBNK =
    new CharacterField(message, HEADERSIZE + 28, 2, "EWDBNK");
    fields[6] = fieldEWDCCY =
    new CharacterField(message, HEADERSIZE + 30, 3, "EWDCCY");
    fields[7] = fieldEWDGLN =
    new DecimalField(message, HEADERSIZE + 33, 17, 0, "EWDGLN");
    fields[8] = fieldEWDDSC =
    new CharacterField(message, HEADERSIZE + 50, 35, "EWDDSC");
    fields[9] = fieldEWDCLS =
    new DecimalField(message, HEADERSIZE + 85, 3, 0, "EWDCLS");
    fields[10] = fieldEWDATY =
    new CharacterField(message, HEADERSIZE + 88, 4, "EWDATY");
    fields[11] = fieldEWDACD =
    new CharacterField(message, HEADERSIZE + 92, 2, "EWDACD");
    fields[12] = fieldEWDREC =
    new DecimalField(message, HEADERSIZE + 94, 8, 0, "EWDREC");
    fields[13] = fieldEWDOPE =
    new CharacterField(message, HEADERSIZE + 102, 1, "EWDOPE");

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
  * Set field EWDSBK using a String value.
  */
  public void setEWDSBK(String newvalue)
  {
    fieldEWDSBK.setString(newvalue);
  }

  /**
  * Get value of field EWDSBK as a String.
  */
  public String getEWDSBK()
  {
    return fieldEWDSBK.getString();
  }

  /**
  * Set field EWDSCY using a String value.
  */
  public void setEWDSCY(String newvalue)
  {
    fieldEWDSCY.setString(newvalue);
  }

  /**
  * Get value of field EWDSCY as a String.
  */
  public String getEWDSCY()
  {
    return fieldEWDSCY.getString();
  }

  /**
  * Set field EWDSGL using a String value.
  */
  public void setEWDSGL(String newvalue)
  {
    fieldEWDSGL.setString(newvalue);
  }

  /**
  * Get value of field EWDSGL as a String.
  */
  public String getEWDSGL()
  {
    return fieldEWDSGL.getString();
  }

  /**
  * Set numeric field EWDSGL using a BigDecimal value.
  */
  public void setEWDSGL(BigDecimal newvalue)
  {
    fieldEWDSGL.setBigDecimal(newvalue);
  }

  /**
  * Return value of numeric field EWDSGL as a BigDecimal.
  */
  public BigDecimal getBigDecimalEWDSGL()
  {
    return fieldEWDSGL.getBigDecimal();
  }

  /**
  * Set field EWDSCA using a String value.
  */
  public void setEWDSCA(String newvalue)
  {
    fieldEWDSCA.setString(newvalue);
  }

  /**
  * Get value of field EWDSCA as a String.
  */
  public String getEWDSCA()
  {
    return fieldEWDSCA.getString();
  }

  /**
  * Set field EWDSTY using a String value.
  */
  public void setEWDSTY(String newvalue)
  {
    fieldEWDSTY.setString(newvalue);
  }

  /**
  * Get value of field EWDSTY as a String.
  */
  public String getEWDSTY()
  {
    return fieldEWDSTY.getString();
  }

  /**
  * Set field EWDBNK using a String value.
  */
  public void setEWDBNK(String newvalue)
  {
    fieldEWDBNK.setString(newvalue);
  }

  /**
  * Get value of field EWDBNK as a String.
  */
  public String getEWDBNK()
  {
    return fieldEWDBNK.getString();
  }

  /**
  * Set field EWDCCY using a String value.
  */
  public void setEWDCCY(String newvalue)
  {
    fieldEWDCCY.setString(newvalue);
  }

  /**
  * Get value of field EWDCCY as a String.
  */
  public String getEWDCCY()
  {
    return fieldEWDCCY.getString();
  }

  /**
  * Set field EWDGLN using a String value.
  */
  public void setEWDGLN(String newvalue)
  {
    fieldEWDGLN.setString(newvalue);
  }

  /**
  * Get value of field EWDGLN as a String.
  */
  public String getEWDGLN()
  {
    return fieldEWDGLN.getString();
  }

  /**
  * Set numeric field EWDGLN using a BigDecimal value.
  */
  public void setEWDGLN(BigDecimal newvalue)
  {
    fieldEWDGLN.setBigDecimal(newvalue);
  }

  /**
  * Return value of numeric field EWDGLN as a BigDecimal.
  */
  public BigDecimal getBigDecimalEWDGLN()
  {
    return fieldEWDGLN.getBigDecimal();
  }

  /**
  * Set field EWDDSC using a String value.
  */
  public void setEWDDSC(String newvalue)
  {
    fieldEWDDSC.setString(newvalue);
  }

  /**
  * Get value of field EWDDSC as a String.
  */
  public String getEWDDSC()
  {
    return fieldEWDDSC.getString();
  }

  /**
  * Set field EWDCLS using a String value.
  */
  public void setEWDCLS(String newvalue)
  {
    fieldEWDCLS.setString(newvalue);
  }

  /**
  * Get value of field EWDCLS as a String.
  */
  public String getEWDCLS()
  {
    return fieldEWDCLS.getString();
  }

  /**
  * Set numeric field EWDCLS using a BigDecimal value.
  */
  public void setEWDCLS(BigDecimal newvalue)
  {
    fieldEWDCLS.setBigDecimal(newvalue);
  }

  /**
  * Return value of numeric field EWDCLS as a BigDecimal.
  */
  public BigDecimal getBigDecimalEWDCLS()
  {
    return fieldEWDCLS.getBigDecimal();
  }

  /**
  * Set field EWDATY using a String value.
  */
  public void setEWDATY(String newvalue)
  {
    fieldEWDATY.setString(newvalue);
  }

  /**
  * Get value of field EWDATY as a String.
  */
  public String getEWDATY()
  {
    return fieldEWDATY.getString();
  }

  /**
  * Set field EWDACD using a String value.
  */
  public void setEWDACD(String newvalue)
  {
    fieldEWDACD.setString(newvalue);
  }

  /**
  * Get value of field EWDACD as a String.
  */
  public String getEWDACD()
  {
    return fieldEWDACD.getString();
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
