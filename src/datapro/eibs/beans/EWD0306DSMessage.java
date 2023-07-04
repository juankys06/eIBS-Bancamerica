package datapro.eibs.beans;

import datapro.eibs.sockets.*;
import java.io.*;
import java.math.*;

import java.util.*;

/**
* Class generated by AS/400 CRTCLASS command from EWD0306DS physical file definition.
* 
* File level identifier is 1060822183949.
* Record format level identifier is 5E25C35B5626C.
*/

public class EWD0306DSMessage extends MessageRecord
{
  final static String fldnames[] = {
                                     "RWDUSR",
                                     "RWDTYP",
                                     "RWDFRC",
                                     "SWDCOD",
                                     "SWDNME",
                                     "SWDTYP",
                                     "SWDNM2",
                                     "SWDNM3",
                                     "SWDNM4",
                                     "SWDCTY",
                                     "SWDSTE",
                                     "SWDZIP",
                                     "SWDPH1",
                                     "SWDPH2",
                                     "SWDFA1",
                                     "SWDREC",
                                     "SWDOPE"
                                   };
  final static String tnames[] = {
                                   "RWDUSR",
                                   "RWDTYP",
                                   "RWDFRC",
                                   "SWDCOD",
                                   "SWDNME",
                                   "SWDTYP",
                                   "SWDNM2",
                                   "SWDNM3",
                                   "SWDNM4",
                                   "SWDCTY",
                                   "SWDSTE",
                                   "SWDZIP",
                                   "SWDPH1",
                                   "SWDPH2",
                                   "SWDFA1",
                                   "SWDREC",
                                   "SWDOPE"
                                 };
  final static String fid = "1060822183949";
  final static String rid = "5E25C35B5626C";
  final static String fmtname = "EWD0306DS";
  final int FIELDCOUNT = 17;
  private static Hashtable tlookup = new Hashtable();
  private CharacterField fieldRWDUSR = null;
  private CharacterField fieldRWDTYP = null;
  private DecimalField fieldRWDFRC = null;
  private CharacterField fieldSWDCOD = null;
  private CharacterField fieldSWDNME = null;
  private CharacterField fieldSWDTYP = null;
  private CharacterField fieldSWDNM2 = null;
  private CharacterField fieldSWDNM3 = null;
  private CharacterField fieldSWDNM4 = null;
  private CharacterField fieldSWDCTY = null;
  private CharacterField fieldSWDSTE = null;
  private CharacterField fieldSWDZIP = null;
  private CharacterField fieldSWDPH1 = null;
  private CharacterField fieldSWDPH2 = null;
  private CharacterField fieldSWDFA1 = null;
  private DecimalField fieldSWDREC = null;
  private CharacterField fieldSWDOPE = null;

  /**
  * Constructor for EWD0306DSMessage.
  */
  public EWD0306DSMessage()
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
    recordsize = 266;
    fileid = fid;
    recordid = rid;
    message = new byte[getByteLength()];
    formatname = fmtname;
    fieldnames = fldnames;
    tagnames = tnames;
    fields = new MessageField[FIELDCOUNT];

    fields[0] = fieldRWDUSR =
    new CharacterField(message, HEADERSIZE + 0, 10, "RWDUSR");
    fields[1] = fieldRWDTYP =
    new CharacterField(message, HEADERSIZE + 10, 1, "RWDTYP");
    fields[2] = fieldRWDFRC =
    new DecimalField(message, HEADERSIZE + 11, 8, 0, "RWDFRC");
    fields[3] = fieldSWDCOD =
    new CharacterField(message, HEADERSIZE + 19, 3, "SWDCOD");
    fields[4] = fieldSWDNME =
    new CharacterField(message, HEADERSIZE + 22, 35, "SWDNME");
    fields[5] = fieldSWDTYP =
    new CharacterField(message, HEADERSIZE + 57, 1, "SWDTYP");
    fields[6] = fieldSWDNM2 =
    new CharacterField(message, HEADERSIZE + 58, 35, "SWDNM2");
    fields[7] = fieldSWDNM3 =
    new CharacterField(message, HEADERSIZE + 93, 35, "SWDNM3");
    fields[8] = fieldSWDNM4 =
    new CharacterField(message, HEADERSIZE + 128, 35, "SWDNM4");
    fields[9] = fieldSWDCTY =
    new CharacterField(message, HEADERSIZE + 163, 30, "SWDCTY");
    fields[10] = fieldSWDSTE =
    new CharacterField(message, HEADERSIZE + 193, 4, "SWDSTE");
    fields[11] = fieldSWDZIP =
    new CharacterField(message, HEADERSIZE + 197, 15, "SWDZIP");
    fields[12] = fieldSWDPH1 =
    new CharacterField(message, HEADERSIZE + 212, 15, "SWDPH1");
    fields[13] = fieldSWDPH2 =
    new CharacterField(message, HEADERSIZE + 227, 15, "SWDPH2");
    fields[14] = fieldSWDFA1 =
    new CharacterField(message, HEADERSIZE + 242, 15, "SWDFA1");
    fields[15] = fieldSWDREC =
    new DecimalField(message, HEADERSIZE + 257, 8, 0, "SWDREC");
    fields[16] = fieldSWDOPE =
    new CharacterField(message, HEADERSIZE + 265, 1, "SWDOPE");

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
  * Set field RWDTYP using a String value.
  */
  public void setRWDTYP(String newvalue)
  {
    fieldRWDTYP.setString(newvalue);
  }

  /**
  * Get value of field RWDTYP as a String.
  */
  public String getRWDTYP()
  {
    return fieldRWDTYP.getString();
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
  * Set field SWDCOD using a String value.
  */
  public void setSWDCOD(String newvalue)
  {
    fieldSWDCOD.setString(newvalue);
  }

  /**
  * Get value of field SWDCOD as a String.
  */
  public String getSWDCOD()
  {
    return fieldSWDCOD.getString();
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
  * Set field SWDTYP using a String value.
  */
  public void setSWDTYP(String newvalue)
  {
    fieldSWDTYP.setString(newvalue);
  }

  /**
  * Get value of field SWDTYP as a String.
  */
  public String getSWDTYP()
  {
    return fieldSWDTYP.getString();
  }

  /**
  * Set field SWDNM2 using a String value.
  */
  public void setSWDNM2(String newvalue)
  {
    fieldSWDNM2.setString(newvalue);
  }

  /**
  * Get value of field SWDNM2 as a String.
  */
  public String getSWDNM2()
  {
    return fieldSWDNM2.getString();
  }

  /**
  * Set field SWDNM3 using a String value.
  */
  public void setSWDNM3(String newvalue)
  {
    fieldSWDNM3.setString(newvalue);
  }

  /**
  * Get value of field SWDNM3 as a String.
  */
  public String getSWDNM3()
  {
    return fieldSWDNM3.getString();
  }

  /**
  * Set field SWDNM4 using a String value.
  */
  public void setSWDNM4(String newvalue)
  {
    fieldSWDNM4.setString(newvalue);
  }

  /**
  * Get value of field SWDNM4 as a String.
  */
  public String getSWDNM4()
  {
    return fieldSWDNM4.getString();
  }

  /**
  * Set field SWDCTY using a String value.
  */
  public void setSWDCTY(String newvalue)
  {
    fieldSWDCTY.setString(newvalue);
  }

  /**
  * Get value of field SWDCTY as a String.
  */
  public String getSWDCTY()
  {
    return fieldSWDCTY.getString();
  }

  /**
  * Set field SWDSTE using a String value.
  */
  public void setSWDSTE(String newvalue)
  {
    fieldSWDSTE.setString(newvalue);
  }

  /**
  * Get value of field SWDSTE as a String.
  */
  public String getSWDSTE()
  {
    return fieldSWDSTE.getString();
  }

  /**
  * Set field SWDZIP using a String value.
  */
  public void setSWDZIP(String newvalue)
  {
    fieldSWDZIP.setString(newvalue);
  }

  /**
  * Get value of field SWDZIP as a String.
  */
  public String getSWDZIP()
  {
    return fieldSWDZIP.getString();
  }

  /**
  * Set field SWDPH1 using a String value.
  */
  public void setSWDPH1(String newvalue)
  {
    fieldSWDPH1.setString(newvalue);
  }

  /**
  * Get value of field SWDPH1 as a String.
  */
  public String getSWDPH1()
  {
    return fieldSWDPH1.getString();
  }

  /**
  * Set field SWDPH2 using a String value.
  */
  public void setSWDPH2(String newvalue)
  {
    fieldSWDPH2.setString(newvalue);
  }

  /**
  * Get value of field SWDPH2 as a String.
  */
  public String getSWDPH2()
  {
    return fieldSWDPH2.getString();
  }

  /**
  * Set field SWDFA1 using a String value.
  */
  public void setSWDFA1(String newvalue)
  {
    fieldSWDFA1.setString(newvalue);
  }

  /**
  * Get value of field SWDFA1 as a String.
  */
  public String getSWDFA1()
  {
    return fieldSWDFA1.getString();
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
