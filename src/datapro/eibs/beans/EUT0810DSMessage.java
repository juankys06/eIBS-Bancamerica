package datapro.eibs.beans;

import datapro.eibs.sockets.*;
import java.io.*;
import java.math.*;

import java.util.*;

/**
* Class generated by AS/400 CRTCLASS command from EUT0810DS physical file definition.
* 
* File level identifier is 1030121192014.
* Record format level identifier is 461E0A1AA4A62.
*/

public class EUT0810DSMessage extends MessageRecord
{
  final static String fldnames[] = {
                                     "EUTFLE",
                                     "EUTNME",
                                     "EUTDSC",
                                     "EUTFRP",
                                     "EUTTOP",
                                     "EUTBYT",
                                     "EUTLNG",
                                     "EUTDEC",
                                     "EUTTYP",
                                     "EUTOPE"
                                   };
  final static String tnames[] = {
                                   "EUTFLE",
                                   "EUTNME",
                                   "EUTDSC",
                                   "EUTFRP",
                                   "EUTTOP",
                                   "EUTBYT",
                                   "EUTLNG",
                                   "EUTDEC",
                                   "EUTTYP",
                                   "EUTOPE"
                                 };
  final static String fid = "1030121192014";
  final static String rid = "461E0A1AA4A62";
  final static String fmtname = "EUT0810DS";
  final int FIELDCOUNT = 10;
  private static Hashtable tlookup = new Hashtable();
  private CharacterField fieldEUTFLE = null;
  private CharacterField fieldEUTNME = null;
  private CharacterField fieldEUTDSC = null;
  private DecimalField fieldEUTFRP = null;
  private DecimalField fieldEUTTOP = null;
  private DecimalField fieldEUTBYT = null;
  private DecimalField fieldEUTLNG = null;
  private DecimalField fieldEUTDEC = null;
  private CharacterField fieldEUTTYP = null;
  private CharacterField fieldEUTOPE = null;

  /**
  * Constructor for EUT0810DSMessage.
  */
  public EUT0810DSMessage()
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
    recordsize = 80;
    fileid = fid;
    recordid = rid;
    message = new byte[getByteLength()];
    formatname = fmtname;
    fieldnames = fldnames;
    tagnames = tnames;
    fields = new MessageField[FIELDCOUNT];

    fields[0] = fieldEUTFLE =
    new CharacterField(message, HEADERSIZE + 0, 10, "EUTFLE");
    fields[1] = fieldEUTNME =
    new CharacterField(message, HEADERSIZE + 10, 10, "EUTNME");
    fields[2] = fieldEUTDSC =
    new CharacterField(message, HEADERSIZE + 20, 35, "EUTDSC");
    fields[3] = fieldEUTFRP =
    new DecimalField(message, HEADERSIZE + 55, 5, 0, "EUTFRP");
    fields[4] = fieldEUTTOP =
    new DecimalField(message, HEADERSIZE + 60, 5, 0, "EUTTOP");
    fields[5] = fieldEUTBYT =
    new DecimalField(message, HEADERSIZE + 65, 5, 0, "EUTBYT");
    fields[6] = fieldEUTLNG =
    new DecimalField(message, HEADERSIZE + 70, 5, 0, "EUTLNG");
    fields[7] = fieldEUTDEC =
    new DecimalField(message, HEADERSIZE + 75, 3, 0, "EUTDEC");
    fields[8] = fieldEUTTYP =
    new CharacterField(message, HEADERSIZE + 78, 1, "EUTTYP");
    fields[9] = fieldEUTOPE =
    new CharacterField(message, HEADERSIZE + 79, 1, "EUTOPE");

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
  * Set field EUTFLE using a String value.
  */
  public void setEUTFLE(String newvalue)
  {
    fieldEUTFLE.setString(newvalue);
  }

  /**
  * Get value of field EUTFLE as a String.
  */
  public String getEUTFLE()
  {
    return fieldEUTFLE.getString();
  }

  /**
  * Set field EUTNME using a String value.
  */
  public void setEUTNME(String newvalue)
  {
    fieldEUTNME.setString(newvalue);
  }

  /**
  * Get value of field EUTNME as a String.
  */
  public String getEUTNME()
  {
    return fieldEUTNME.getString();
  }

  /**
  * Set field EUTDSC using a String value.
  */
  public void setEUTDSC(String newvalue)
  {
    fieldEUTDSC.setString(newvalue);
  }

  /**
  * Get value of field EUTDSC as a String.
  */
  public String getEUTDSC()
  {
    return fieldEUTDSC.getString();
  }

  /**
  * Set field EUTFRP using a String value.
  */
  public void setEUTFRP(String newvalue)
  {
    fieldEUTFRP.setString(newvalue);
  }

  /**
  * Get value of field EUTFRP as a String.
  */
  public String getEUTFRP()
  {
    return fieldEUTFRP.getString();
  }

  /**
  * Set numeric field EUTFRP using a BigDecimal value.
  */
  public void setEUTFRP(BigDecimal newvalue)
  {
    fieldEUTFRP.setBigDecimal(newvalue);
  }

  /**
  * Return value of numeric field EUTFRP as a BigDecimal.
  */
  public BigDecimal getBigDecimalEUTFRP()
  {
    return fieldEUTFRP.getBigDecimal();
  }

  /**
  * Set field EUTTOP using a String value.
  */
  public void setEUTTOP(String newvalue)
  {
    fieldEUTTOP.setString(newvalue);
  }

  /**
  * Get value of field EUTTOP as a String.
  */
  public String getEUTTOP()
  {
    return fieldEUTTOP.getString();
  }

  /**
  * Set numeric field EUTTOP using a BigDecimal value.
  */
  public void setEUTTOP(BigDecimal newvalue)
  {
    fieldEUTTOP.setBigDecimal(newvalue);
  }

  /**
  * Return value of numeric field EUTTOP as a BigDecimal.
  */
  public BigDecimal getBigDecimalEUTTOP()
  {
    return fieldEUTTOP.getBigDecimal();
  }

  /**
  * Set field EUTBYT using a String value.
  */
  public void setEUTBYT(String newvalue)
  {
    fieldEUTBYT.setString(newvalue);
  }

  /**
  * Get value of field EUTBYT as a String.
  */
  public String getEUTBYT()
  {
    return fieldEUTBYT.getString();
  }

  /**
  * Set numeric field EUTBYT using a BigDecimal value.
  */
  public void setEUTBYT(BigDecimal newvalue)
  {
    fieldEUTBYT.setBigDecimal(newvalue);
  }

  /**
  * Return value of numeric field EUTBYT as a BigDecimal.
  */
  public BigDecimal getBigDecimalEUTBYT()
  {
    return fieldEUTBYT.getBigDecimal();
  }

  /**
  * Set field EUTLNG using a String value.
  */
  public void setEUTLNG(String newvalue)
  {
    fieldEUTLNG.setString(newvalue);
  }

  /**
  * Get value of field EUTLNG as a String.
  */
  public String getEUTLNG()
  {
    return fieldEUTLNG.getString();
  }

  /**
  * Set numeric field EUTLNG using a BigDecimal value.
  */
  public void setEUTLNG(BigDecimal newvalue)
  {
    fieldEUTLNG.setBigDecimal(newvalue);
  }

  /**
  * Return value of numeric field EUTLNG as a BigDecimal.
  */
  public BigDecimal getBigDecimalEUTLNG()
  {
    return fieldEUTLNG.getBigDecimal();
  }

  /**
  * Set field EUTDEC using a String value.
  */
  public void setEUTDEC(String newvalue)
  {
    fieldEUTDEC.setString(newvalue);
  }

  /**
  * Get value of field EUTDEC as a String.
  */
  public String getEUTDEC()
  {
    return fieldEUTDEC.getString();
  }

  /**
  * Set numeric field EUTDEC using a BigDecimal value.
  */
  public void setEUTDEC(BigDecimal newvalue)
  {
    fieldEUTDEC.setBigDecimal(newvalue);
  }

  /**
  * Return value of numeric field EUTDEC as a BigDecimal.
  */
  public BigDecimal getBigDecimalEUTDEC()
  {
    return fieldEUTDEC.getBigDecimal();
  }

  /**
  * Set field EUTTYP using a String value.
  */
  public void setEUTTYP(String newvalue)
  {
    fieldEUTTYP.setString(newvalue);
  }

  /**
  * Get value of field EUTTYP as a String.
  */
  public String getEUTTYP()
  {
    return fieldEUTTYP.getString();
  }

  /**
  * Set field EUTOPE using a String value.
  */
  public void setEUTOPE(String newvalue)
  {
    fieldEUTOPE.setString(newvalue);
  }

  /**
  * Get value of field EUTOPE as a String.
  */
  public String getEUTOPE()
  {
    return fieldEUTOPE.getString();
  }

}
