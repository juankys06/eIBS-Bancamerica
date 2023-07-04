package datapro.eibs.beans;

import datapro.eibs.sockets.*;
import java.io.*;
import java.math.*;

import java.util.*;

/**
* Class generated by AS/400 CRTCLASS command from EWD0365DS physical file definition.
* 
* File level identifier is 1080603173831.
* Record format level identifier is 2B98608E2280B.
*/

public class EWD0365DSMessage extends MessageRecord
{
  final static String fldnames[] = {
                                     "RWDUSR",
                                     "RWDTYP",
                                     "RWDOCD",
                                     "RWDCUN",
                                     "RWDACC",
                                     "RWDBTH",
                                     "RWDFD1",
                                     "RWDFD2",
                                     "RWDFD3",
                                     "RWDSTS",
                                     "RWDNUM",
                                     "RWDFRC",
                                     "SWDNUM",
                                     "SWDCUN",
                                     "SWDACC",
                                     "SWDNA1",
                                     "SWDOCD",
                                     "SWDVD1",
                                     "SWDVD2",
                                     "SWDVD3",
                                     "SWDBTH",
                                     "SWDECD",
                                     "SWDROU",
                                     "SWDDAC",
                                     "SWDCCY",
                                     "SWDAMT",
                                     "SWDSTS",
                                     "SWDREC",
                                     "SWDOPE"
                                   };
  final static String tnames[] = {
                                   "RWDUSR",
                                   "RWDTYP",
                                   "RWDOCD",
                                   "RWDCUN",
                                   "RWDACC",
                                   "RWDBTH",
                                   "RWDFD1",
                                   "RWDFD2",
                                   "RWDFD3",
                                   "RWDSTS",
                                   "RWDNUM",
                                   "RWDFRC",
                                   "SWDNUM",
                                   "SWDCUN",
                                   "SWDACC",
                                   "SWDNA1",
                                   "SWDOCD",
                                   "SWDVD1",
                                   "SWDVD2",
                                   "SWDVD3",
                                   "SWDBTH",
                                   "SWDECD",
                                   "SWDROU",
                                   "SWDDAC",
                                   "SWDCCY",
                                   "SWDAMT",
                                   "SWDSTS",
                                   "SWDREC",
                                   "SWDOPE"
                                 };
  final static String fid = "1080603173831";
  final static String rid = "2B98608E2280B";
  final static String fmtname = "EWD0365DS";
  final int FIELDCOUNT = 29;
  private static Hashtable tlookup = new Hashtable();
  private CharacterField fieldRWDUSR = null;
  private CharacterField fieldRWDTYP = null;
  private CharacterField fieldRWDOCD = null;
  private DecimalField fieldRWDCUN = null;
  private DecimalField fieldRWDACC = null;
  private DecimalField fieldRWDBTH = null;
  private DecimalField fieldRWDFD1 = null;
  private DecimalField fieldRWDFD2 = null;
  private DecimalField fieldRWDFD3 = null;
  private CharacterField fieldRWDSTS = null;
  private DecimalField fieldRWDNUM = null;
  private DecimalField fieldRWDFRC = null;
  private DecimalField fieldSWDNUM = null;
  private DecimalField fieldSWDCUN = null;
  private DecimalField fieldSWDACC = null;
  private CharacterField fieldSWDNA1 = null;
  private CharacterField fieldSWDOCD = null;
  private DecimalField fieldSWDVD1 = null;
  private DecimalField fieldSWDVD2 = null;
  private DecimalField fieldSWDVD3 = null;
  private DecimalField fieldSWDBTH = null;
  private CharacterField fieldSWDECD = null;
  private CharacterField fieldSWDROU = null;
  private CharacterField fieldSWDDAC = null;
  private CharacterField fieldSWDCCY = null;
  private DecimalField fieldSWDAMT = null;
  private CharacterField fieldSWDSTS = null;
  private DecimalField fieldSWDREC = null;
  private CharacterField fieldSWDOPE = null;

  /**
  * Constructor for EWD0365DSMessage.
  */
  public EWD0365DSMessage()
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
    recordsize = 244;
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
    fields[2] = fieldRWDOCD =
    new CharacterField(message, HEADERSIZE + 11, 10, "RWDOCD");
    fields[3] = fieldRWDCUN =
    new DecimalField(message, HEADERSIZE + 21, 10, 0, "RWDCUN");
    fields[4] = fieldRWDACC =
    new DecimalField(message, HEADERSIZE + 31, 13, 0, "RWDACC");
    fields[5] = fieldRWDBTH =
    new DecimalField(message, HEADERSIZE + 44, 5, 0, "RWDBTH");
    fields[6] = fieldRWDFD1 =
    new DecimalField(message, HEADERSIZE + 49, 3, 0, "RWDFD1");
    fields[7] = fieldRWDFD2 =
    new DecimalField(message, HEADERSIZE + 52, 3, 0, "RWDFD2");
    fields[8] = fieldRWDFD3 =
    new DecimalField(message, HEADERSIZE + 55, 3, 0, "RWDFD3");
    fields[9] = fieldRWDSTS =
    new CharacterField(message, HEADERSIZE + 58, 1, "RWDSTS");
    fields[10] = fieldRWDNUM =
    new DecimalField(message, HEADERSIZE + 59, 13, 0, "RWDNUM");
    fields[11] = fieldRWDFRC =
    new DecimalField(message, HEADERSIZE + 72, 8, 0, "RWDFRC");
    fields[12] = fieldSWDNUM =
    new DecimalField(message, HEADERSIZE + 80, 13, 0, "SWDNUM");
    fields[13] = fieldSWDCUN =
    new DecimalField(message, HEADERSIZE + 93, 10, 0, "SWDCUN");
    fields[14] = fieldSWDACC =
    new DecimalField(message, HEADERSIZE + 103, 13, 0, "SWDACC");
    fields[15] = fieldSWDNA1 =
    new CharacterField(message, HEADERSIZE + 116, 45, "SWDNA1");
    fields[16] = fieldSWDOCD =
    new CharacterField(message, HEADERSIZE + 161, 10, "SWDOCD");
    fields[17] = fieldSWDVD1 =
    new DecimalField(message, HEADERSIZE + 171, 3, 0, "SWDVD1");
    fields[18] = fieldSWDVD2 =
    new DecimalField(message, HEADERSIZE + 174, 3, 0, "SWDVD2");
    fields[19] = fieldSWDVD3 =
    new DecimalField(message, HEADERSIZE + 177, 3, 0, "SWDVD3");
    fields[20] = fieldSWDBTH =
    new DecimalField(message, HEADERSIZE + 180, 5, 0, "SWDBTH");
    fields[21] = fieldSWDECD =
    new CharacterField(message, HEADERSIZE + 185, 3, "SWDECD");
    fields[22] = fieldSWDROU =
    new CharacterField(message, HEADERSIZE + 188, 9, "SWDROU");
    fields[23] = fieldSWDDAC =
    new CharacterField(message, HEADERSIZE + 197, 17, "SWDDAC");
    fields[24] = fieldSWDCCY =
    new CharacterField(message, HEADERSIZE + 214, 3, "SWDCCY");
    fields[25] = fieldSWDAMT =
    new DecimalField(message, HEADERSIZE + 217, 17, 2, "SWDAMT");
    fields[26] = fieldSWDSTS =
    new CharacterField(message, HEADERSIZE + 234, 1, "SWDSTS");
    fields[27] = fieldSWDREC =
    new DecimalField(message, HEADERSIZE + 235, 8, 0, "SWDREC");
    fields[28] = fieldSWDOPE =
    new CharacterField(message, HEADERSIZE + 243, 1, "SWDOPE");

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
  * Set field RWDOCD using a String value.
  */
  public void setRWDOCD(String newvalue)
  {
    fieldRWDOCD.setString(newvalue);
  }

  /**
  * Get value of field RWDOCD as a String.
  */
  public String getRWDOCD()
  {
    return fieldRWDOCD.getString();
  }

  /**
  * Set field RWDCUN using a String value.
  */
  public void setRWDCUN(String newvalue)
  {
    fieldRWDCUN.setString(newvalue);
  }

  /**
  * Get value of field RWDCUN as a String.
  */
  public String getRWDCUN()
  {
    return fieldRWDCUN.getString();
  }

  /**
  * Set numeric field RWDCUN using a BigDecimal value.
  */
  public void setRWDCUN(BigDecimal newvalue)
  {
    fieldRWDCUN.setBigDecimal(newvalue);
  }

  /**
  * Return value of numeric field RWDCUN as a BigDecimal.
  */
  public BigDecimal getBigDecimalRWDCUN()
  {
    return fieldRWDCUN.getBigDecimal();
  }

  /**
  * Set field RWDACC using a String value.
  */
  public void setRWDACC(String newvalue)
  {
    fieldRWDACC.setString(newvalue);
  }

  /**
  * Get value of field RWDACC as a String.
  */
  public String getRWDACC()
  {
    return fieldRWDACC.getString();
  }

  /**
  * Set numeric field RWDACC using a BigDecimal value.
  */
  public void setRWDACC(BigDecimal newvalue)
  {
    fieldRWDACC.setBigDecimal(newvalue);
  }

  /**
  * Return value of numeric field RWDACC as a BigDecimal.
  */
  public BigDecimal getBigDecimalRWDACC()
  {
    return fieldRWDACC.getBigDecimal();
  }

  /**
  * Set field RWDBTH using a String value.
  */
  public void setRWDBTH(String newvalue)
  {
    fieldRWDBTH.setString(newvalue);
  }

  /**
  * Get value of field RWDBTH as a String.
  */
  public String getRWDBTH()
  {
    return fieldRWDBTH.getString();
  }

  /**
  * Set numeric field RWDBTH using a BigDecimal value.
  */
  public void setRWDBTH(BigDecimal newvalue)
  {
    fieldRWDBTH.setBigDecimal(newvalue);
  }

  /**
  * Return value of numeric field RWDBTH as a BigDecimal.
  */
  public BigDecimal getBigDecimalRWDBTH()
  {
    return fieldRWDBTH.getBigDecimal();
  }

  /**
  * Set field RWDFD1 using a String value.
  */
  public void setRWDFD1(String newvalue)
  {
    fieldRWDFD1.setString(newvalue);
  }

  /**
  * Get value of field RWDFD1 as a String.
  */
  public String getRWDFD1()
  {
    return fieldRWDFD1.getString();
  }

  /**
  * Set numeric field RWDFD1 using a BigDecimal value.
  */
  public void setRWDFD1(BigDecimal newvalue)
  {
    fieldRWDFD1.setBigDecimal(newvalue);
  }

  /**
  * Return value of numeric field RWDFD1 as a BigDecimal.
  */
  public BigDecimal getBigDecimalRWDFD1()
  {
    return fieldRWDFD1.getBigDecimal();
  }

  /**
  * Set field RWDFD2 using a String value.
  */
  public void setRWDFD2(String newvalue)
  {
    fieldRWDFD2.setString(newvalue);
  }

  /**
  * Get value of field RWDFD2 as a String.
  */
  public String getRWDFD2()
  {
    return fieldRWDFD2.getString();
  }

  /**
  * Set numeric field RWDFD2 using a BigDecimal value.
  */
  public void setRWDFD2(BigDecimal newvalue)
  {
    fieldRWDFD2.setBigDecimal(newvalue);
  }

  /**
  * Return value of numeric field RWDFD2 as a BigDecimal.
  */
  public BigDecimal getBigDecimalRWDFD2()
  {
    return fieldRWDFD2.getBigDecimal();
  }

  /**
  * Set field RWDFD3 using a String value.
  */
  public void setRWDFD3(String newvalue)
  {
    fieldRWDFD3.setString(newvalue);
  }

  /**
  * Get value of field RWDFD3 as a String.
  */
  public String getRWDFD3()
  {
    return fieldRWDFD3.getString();
  }

  /**
  * Set numeric field RWDFD3 using a BigDecimal value.
  */
  public void setRWDFD3(BigDecimal newvalue)
  {
    fieldRWDFD3.setBigDecimal(newvalue);
  }

  /**
  * Return value of numeric field RWDFD3 as a BigDecimal.
  */
  public BigDecimal getBigDecimalRWDFD3()
  {
    return fieldRWDFD3.getBigDecimal();
  }

  /**
  * Set field RWDSTS using a String value.
  */
  public void setRWDSTS(String newvalue)
  {
    fieldRWDSTS.setString(newvalue);
  }

  /**
  * Get value of field RWDSTS as a String.
  */
  public String getRWDSTS()
  {
    return fieldRWDSTS.getString();
  }

  /**
  * Set field RWDNUM using a String value.
  */
  public void setRWDNUM(String newvalue)
  {
    fieldRWDNUM.setString(newvalue);
  }

  /**
  * Get value of field RWDNUM as a String.
  */
  public String getRWDNUM()
  {
    return fieldRWDNUM.getString();
  }

  /**
  * Set numeric field RWDNUM using a BigDecimal value.
  */
  public void setRWDNUM(BigDecimal newvalue)
  {
    fieldRWDNUM.setBigDecimal(newvalue);
  }

  /**
  * Return value of numeric field RWDNUM as a BigDecimal.
  */
  public BigDecimal getBigDecimalRWDNUM()
  {
    return fieldRWDNUM.getBigDecimal();
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
  * Set field SWDNUM using a String value.
  */
  public void setSWDNUM(String newvalue)
  {
    fieldSWDNUM.setString(newvalue);
  }

  /**
  * Get value of field SWDNUM as a String.
  */
  public String getSWDNUM()
  {
    return fieldSWDNUM.getString();
  }

  /**
  * Set numeric field SWDNUM using a BigDecimal value.
  */
  public void setSWDNUM(BigDecimal newvalue)
  {
    fieldSWDNUM.setBigDecimal(newvalue);
  }

  /**
  * Return value of numeric field SWDNUM as a BigDecimal.
  */
  public BigDecimal getBigDecimalSWDNUM()
  {
    return fieldSWDNUM.getBigDecimal();
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
  * Set field SWDOCD using a String value.
  */
  public void setSWDOCD(String newvalue)
  {
    fieldSWDOCD.setString(newvalue);
  }

  /**
  * Get value of field SWDOCD as a String.
  */
  public String getSWDOCD()
  {
    return fieldSWDOCD.getString();
  }

  /**
  * Set field SWDVD1 using a String value.
  */
  public void setSWDVD1(String newvalue)
  {
    fieldSWDVD1.setString(newvalue);
  }

  /**
  * Get value of field SWDVD1 as a String.
  */
  public String getSWDVD1()
  {
    return fieldSWDVD1.getString();
  }

  /**
  * Set numeric field SWDVD1 using a BigDecimal value.
  */
  public void setSWDVD1(BigDecimal newvalue)
  {
    fieldSWDVD1.setBigDecimal(newvalue);
  }

  /**
  * Return value of numeric field SWDVD1 as a BigDecimal.
  */
  public BigDecimal getBigDecimalSWDVD1()
  {
    return fieldSWDVD1.getBigDecimal();
  }

  /**
  * Set field SWDVD2 using a String value.
  */
  public void setSWDVD2(String newvalue)
  {
    fieldSWDVD2.setString(newvalue);
  }

  /**
  * Get value of field SWDVD2 as a String.
  */
  public String getSWDVD2()
  {
    return fieldSWDVD2.getString();
  }

  /**
  * Set numeric field SWDVD2 using a BigDecimal value.
  */
  public void setSWDVD2(BigDecimal newvalue)
  {
    fieldSWDVD2.setBigDecimal(newvalue);
  }

  /**
  * Return value of numeric field SWDVD2 as a BigDecimal.
  */
  public BigDecimal getBigDecimalSWDVD2()
  {
    return fieldSWDVD2.getBigDecimal();
  }

  /**
  * Set field SWDVD3 using a String value.
  */
  public void setSWDVD3(String newvalue)
  {
    fieldSWDVD3.setString(newvalue);
  }

  /**
  * Get value of field SWDVD3 as a String.
  */
  public String getSWDVD3()
  {
    return fieldSWDVD3.getString();
  }

  /**
  * Set numeric field SWDVD3 using a BigDecimal value.
  */
  public void setSWDVD3(BigDecimal newvalue)
  {
    fieldSWDVD3.setBigDecimal(newvalue);
  }

  /**
  * Return value of numeric field SWDVD3 as a BigDecimal.
  */
  public BigDecimal getBigDecimalSWDVD3()
  {
    return fieldSWDVD3.getBigDecimal();
  }

  /**
  * Set field SWDBTH using a String value.
  */
  public void setSWDBTH(String newvalue)
  {
    fieldSWDBTH.setString(newvalue);
  }

  /**
  * Get value of field SWDBTH as a String.
  */
  public String getSWDBTH()
  {
    return fieldSWDBTH.getString();
  }

  /**
  * Set numeric field SWDBTH using a BigDecimal value.
  */
  public void setSWDBTH(BigDecimal newvalue)
  {
    fieldSWDBTH.setBigDecimal(newvalue);
  }

  /**
  * Return value of numeric field SWDBTH as a BigDecimal.
  */
  public BigDecimal getBigDecimalSWDBTH()
  {
    return fieldSWDBTH.getBigDecimal();
  }

  /**
  * Set field SWDECD using a String value.
  */
  public void setSWDECD(String newvalue)
  {
    fieldSWDECD.setString(newvalue);
  }

  /**
  * Get value of field SWDECD as a String.
  */
  public String getSWDECD()
  {
    return fieldSWDECD.getString();
  }

  /**
  * Set field SWDROU using a String value.
  */
  public void setSWDROU(String newvalue)
  {
    fieldSWDROU.setString(newvalue);
  }

  /**
  * Get value of field SWDROU as a String.
  */
  public String getSWDROU()
  {
    return fieldSWDROU.getString();
  }

  /**
  * Set field SWDDAC using a String value.
  */
  public void setSWDDAC(String newvalue)
  {
    fieldSWDDAC.setString(newvalue);
  }

  /**
  * Get value of field SWDDAC as a String.
  */
  public String getSWDDAC()
  {
    return fieldSWDDAC.getString();
  }

  /**
  * Set field SWDCCY using a String value.
  */
  public void setSWDCCY(String newvalue)
  {
    fieldSWDCCY.setString(newvalue);
  }

  /**
  * Get value of field SWDCCY as a String.
  */
  public String getSWDCCY()
  {
    return fieldSWDCCY.getString();
  }

  /**
  * Set field SWDAMT using a String value.
  */
  public void setSWDAMT(String newvalue)
  {
    fieldSWDAMT.setString(newvalue);
  }

  /**
  * Get value of field SWDAMT as a String.
  */
  public String getSWDAMT()
  {
    return fieldSWDAMT.getString();
  }

  /**
  * Set numeric field SWDAMT using a BigDecimal value.
  */
  public void setSWDAMT(BigDecimal newvalue)
  {
    fieldSWDAMT.setBigDecimal(newvalue);
  }

  /**
  * Return value of numeric field SWDAMT as a BigDecimal.
  */
  public BigDecimal getBigDecimalSWDAMT()
  {
    return fieldSWDAMT.getBigDecimal();
  }

  /**
  * Set field SWDSTS using a String value.
  */
  public void setSWDSTS(String newvalue)
  {
    fieldSWDSTS.setString(newvalue);
  }

  /**
  * Get value of field SWDSTS as a String.
  */
  public String getSWDSTS()
  {
    return fieldSWDSTS.getString();
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