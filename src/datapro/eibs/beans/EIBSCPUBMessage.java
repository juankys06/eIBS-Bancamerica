package datapro.eibs.beans;

import datapro.eibs.sockets.*;
import java.io.*;
import java.math.*;

import java.util.*;

/**
* Class generated by AS/400 CRTCLASS command from EIBSCPUB physical file definition.
* 
* File level identifier is 1030121191932.
* Record format level identifier is 4C17B456A93A0.
*/

public class EIBSCPUBMessage extends MessageRecord
{
  final static String fldnames[] = {
                                     "CPIDEN",
                                     "CPCAW1",
                                     "CPCAW2",
                                     "CPCAW3",
                                     "CPCAW4",
                                     "CPEEE1",
                                     "CPEEE2",
                                     "CPEEE3",
                                     "CPEEE4",
                                     "CPIDA1",
                                     "CPIDA2",
                                     "CPIDA3",
                                     "CPIDA4",
                                     "CPVIS1",
                                     "CPVIS2",
                                     "CPVIS3",
                                     "CPVIS4"
                                   };
  final static String tnames[] = {
                                   "CPIDEN",
                                   "CPCAW1",
                                   "CPCAW2",
                                   "CPCAW3",
                                   "CPCAW4",
                                   "CPEEE1",
                                   "CPEEE2",
                                   "CPEEE3",
                                   "CPEEE4",
                                   "CPIDA1",
                                   "CPIDA2",
                                   "CPIDA3",
                                   "CPIDA4",
                                   "CPVIS1",
                                   "CPVIS2",
                                   "CPVIS3",
                                   "CPVIS4"
                                 };
  final static String fid = "1030121191932";
  final static String rid = "4C17B456A93A0";
  final static String fmtname = "EIBSCPUB";
  final int FIELDCOUNT = 17;
  private static Hashtable tlookup = new Hashtable();
  private CharacterField fieldCPIDEN = null;
  private CharacterField fieldCPCAW1 = null;
  private CharacterField fieldCPCAW2 = null;
  private CharacterField fieldCPCAW3 = null;
  private CharacterField fieldCPCAW4 = null;
  private CharacterField fieldCPEEE1 = null;
  private CharacterField fieldCPEEE2 = null;
  private CharacterField fieldCPEEE3 = null;
  private CharacterField fieldCPEEE4 = null;
  private CharacterField fieldCPIDA1 = null;
  private CharacterField fieldCPIDA2 = null;
  private CharacterField fieldCPIDA3 = null;
  private CharacterField fieldCPIDA4 = null;
  private CharacterField fieldCPVIS1 = null;
  private CharacterField fieldCPVIS2 = null;
  private CharacterField fieldCPVIS3 = null;
  private CharacterField fieldCPVIS4 = null;

  /**
  * Constructor for EIBSCPUBMessage.
  */
  public EIBSCPUBMessage()
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
    recordsize = 295;
    fileid = fid;
    recordid = rid;
    message = new byte[getByteLength()];
    formatname = fmtname;
    fieldnames = fldnames;
    tagnames = tnames;
    fields = new MessageField[FIELDCOUNT];

    fields[0] = fieldCPIDEN =
    new CharacterField(message, HEADERSIZE + 0, 15, "CPIDEN");
    fields[1] = fieldCPCAW1 =
    new CharacterField(message, HEADERSIZE + 15, 18, "CPCAW1");
    fields[2] = fieldCPCAW2 =
    new CharacterField(message, HEADERSIZE + 33, 18, "CPCAW2");
    fields[3] = fieldCPCAW3 =
    new CharacterField(message, HEADERSIZE + 51, 18, "CPCAW3");
    fields[4] = fieldCPCAW4 =
    new CharacterField(message, HEADERSIZE + 69, 18, "CPCAW4");
    fields[5] = fieldCPEEE1 =
    new CharacterField(message, HEADERSIZE + 87, 18, "CPEEE1");
    fields[6] = fieldCPEEE2 =
    new CharacterField(message, HEADERSIZE + 105, 18, "CPEEE2");
    fields[7] = fieldCPEEE3 =
    new CharacterField(message, HEADERSIZE + 123, 18, "CPEEE3");
    fields[8] = fieldCPEEE4 =
    new CharacterField(message, HEADERSIZE + 141, 18, "CPEEE4");
    fields[9] = fieldCPIDA1 =
    new CharacterField(message, HEADERSIZE + 159, 18, "CPIDA1");
    fields[10] = fieldCPIDA2 =
    new CharacterField(message, HEADERSIZE + 177, 18, "CPIDA2");
    fields[11] = fieldCPIDA3 =
    new CharacterField(message, HEADERSIZE + 195, 18, "CPIDA3");
    fields[12] = fieldCPIDA4 =
    new CharacterField(message, HEADERSIZE + 213, 18, "CPIDA4");
    fields[13] = fieldCPVIS1 =
    new CharacterField(message, HEADERSIZE + 231, 16, "CPVIS1");
    fields[14] = fieldCPVIS2 =
    new CharacterField(message, HEADERSIZE + 247, 16, "CPVIS2");
    fields[15] = fieldCPVIS3 =
    new CharacterField(message, HEADERSIZE + 263, 16, "CPVIS3");
    fields[16] = fieldCPVIS4 =
    new CharacterField(message, HEADERSIZE + 279, 16, "CPVIS4");

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
  * Set field CPIDEN using a String value.
  */
  public void setCPIDEN(String newvalue)
  {
    fieldCPIDEN.setString(newvalue);
  }

  /**
  * Get value of field CPIDEN as a String.
  */
  public String getCPIDEN()
  {
    return fieldCPIDEN.getString();
  }

  /**
  * Set field CPCAW1 using a String value.
  */
  public void setCPCAW1(String newvalue)
  {
    fieldCPCAW1.setString(newvalue);
  }

  /**
  * Get value of field CPCAW1 as a String.
  */
  public String getCPCAW1()
  {
    return fieldCPCAW1.getString();
  }

  /**
  * Set field CPCAW2 using a String value.
  */
  public void setCPCAW2(String newvalue)
  {
    fieldCPCAW2.setString(newvalue);
  }

  /**
  * Get value of field CPCAW2 as a String.
  */
  public String getCPCAW2()
  {
    return fieldCPCAW2.getString();
  }

  /**
  * Set field CPCAW3 using a String value.
  */
  public void setCPCAW3(String newvalue)
  {
    fieldCPCAW3.setString(newvalue);
  }

  /**
  * Get value of field CPCAW3 as a String.
  */
  public String getCPCAW3()
  {
    return fieldCPCAW3.getString();
  }

  /**
  * Set field CPCAW4 using a String value.
  */
  public void setCPCAW4(String newvalue)
  {
    fieldCPCAW4.setString(newvalue);
  }

  /**
  * Get value of field CPCAW4 as a String.
  */
  public String getCPCAW4()
  {
    return fieldCPCAW4.getString();
  }

  /**
  * Set field CPEEE1 using a String value.
  */
  public void setCPEEE1(String newvalue)
  {
    fieldCPEEE1.setString(newvalue);
  }

  /**
  * Get value of field CPEEE1 as a String.
  */
  public String getCPEEE1()
  {
    return fieldCPEEE1.getString();
  }

  /**
  * Set field CPEEE2 using a String value.
  */
  public void setCPEEE2(String newvalue)
  {
    fieldCPEEE2.setString(newvalue);
  }

  /**
  * Get value of field CPEEE2 as a String.
  */
  public String getCPEEE2()
  {
    return fieldCPEEE2.getString();
  }

  /**
  * Set field CPEEE3 using a String value.
  */
  public void setCPEEE3(String newvalue)
  {
    fieldCPEEE3.setString(newvalue);
  }

  /**
  * Get value of field CPEEE3 as a String.
  */
  public String getCPEEE3()
  {
    return fieldCPEEE3.getString();
  }

  /**
  * Set field CPEEE4 using a String value.
  */
  public void setCPEEE4(String newvalue)
  {
    fieldCPEEE4.setString(newvalue);
  }

  /**
  * Get value of field CPEEE4 as a String.
  */
  public String getCPEEE4()
  {
    return fieldCPEEE4.getString();
  }

  /**
  * Set field CPIDA1 using a String value.
  */
  public void setCPIDA1(String newvalue)
  {
    fieldCPIDA1.setString(newvalue);
  }

  /**
  * Get value of field CPIDA1 as a String.
  */
  public String getCPIDA1()
  {
    return fieldCPIDA1.getString();
  }

  /**
  * Set field CPIDA2 using a String value.
  */
  public void setCPIDA2(String newvalue)
  {
    fieldCPIDA2.setString(newvalue);
  }

  /**
  * Get value of field CPIDA2 as a String.
  */
  public String getCPIDA2()
  {
    return fieldCPIDA2.getString();
  }

  /**
  * Set field CPIDA3 using a String value.
  */
  public void setCPIDA3(String newvalue)
  {
    fieldCPIDA3.setString(newvalue);
  }

  /**
  * Get value of field CPIDA3 as a String.
  */
  public String getCPIDA3()
  {
    return fieldCPIDA3.getString();
  }

  /**
  * Set field CPIDA4 using a String value.
  */
  public void setCPIDA4(String newvalue)
  {
    fieldCPIDA4.setString(newvalue);
  }

  /**
  * Get value of field CPIDA4 as a String.
  */
  public String getCPIDA4()
  {
    return fieldCPIDA4.getString();
  }

  /**
  * Set field CPVIS1 using a String value.
  */
  public void setCPVIS1(String newvalue)
  {
    fieldCPVIS1.setString(newvalue);
  }

  /**
  * Get value of field CPVIS1 as a String.
  */
  public String getCPVIS1()
  {
    return fieldCPVIS1.getString();
  }

  /**
  * Set field CPVIS2 using a String value.
  */
  public void setCPVIS2(String newvalue)
  {
    fieldCPVIS2.setString(newvalue);
  }

  /**
  * Get value of field CPVIS2 as a String.
  */
  public String getCPVIS2()
  {
    return fieldCPVIS2.getString();
  }

  /**
  * Set field CPVIS3 using a String value.
  */
  public void setCPVIS3(String newvalue)
  {
    fieldCPVIS3.setString(newvalue);
  }

  /**
  * Get value of field CPVIS3 as a String.
  */
  public String getCPVIS3()
  {
    return fieldCPVIS3.getString();
  }

  /**
  * Set field CPVIS4 using a String value.
  */
  public void setCPVIS4(String newvalue)
  {
    fieldCPVIS4.setString(newvalue);
  }

  /**
  * Get value of field CPVIS4 as a String.
  */
  public String getCPVIS4()
  {
    return fieldCPVIS4.getString();
  }

}
