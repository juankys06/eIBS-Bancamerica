package datapro.eibs.beans;

import datapro.eibs.sockets.*;
import java.io.*;
import java.math.*;

import java.util.*;

/**
* Class generated by AS/400 CRTCLASS command from EWD0336DS physical file definition.
* 
* File level identifier is 1040423110730.
* Record format level identifier is 43B1CDC9D0849.
*/

public class EWD0336DSMessage extends MessageRecord
{
  final static String fldnames[] = {
                                     "RWDUSR",
                                     "RWDTYP",
                                     "RWDTYD",
                                     "RWDCUN",
                                     "RWDSHR",
                                     "RWDFD1",
                                     "RWDFD2",
                                     "RWDFD3",
                                     "RWDTD1",
                                     "RWDTD2",
                                     "RWDTD3",
                                     "RWDFDL",
                                     "RWDTDL",
                                     "RWDFRC",
                                     "SWDBNK",
                                     "SWDBRN",
                                     "SWDCCY",
                                     "SWDGLN",
                                     "SWDACC",
                                     "SWDTYP",
                                     "SWDSBT",
                                     "SWDPRO",
                                     "SWDCUN",
                                     "SWDDSC",
                                     "SWDAMT",
                                     "SWDOAM",
                                     "SWDASD",
                                     "SWDRTE",
                                     "SWDPAC",
                                     "SWDMD1",
                                     "SWDMD2",
                                     "SWDMD3",
                                     "SWDSTS",
                                     "SWDREC",
                                     "SWDOPE"
                                   };
  final static String tnames[] = {
                                   "RWDUSR",
                                   "RWDTYP",
                                   "RWDTYD",
                                   "RWDCUN",
                                   "RWDSHR",
                                   "RWDFD1",
                                   "RWDFD2",
                                   "RWDFD3",
                                   "RWDTD1",
                                   "RWDTD2",
                                   "RWDTD3",
                                   "RWDFDL",
                                   "RWDTDL",
                                   "RWDFRC",
                                   "SWDBNK",
                                   "SWDBRN",
                                   "SWDCCY",
                                   "SWDGLN",
                                   "SWDACC",
                                   "SWDTYP",
                                   "SWDSBT",
                                   "SWDPRO",
                                   "SWDCUN",
                                   "SWDDSC",
                                   "SWDAMT",
                                   "SWDOAM",
                                   "SWDASD",
                                   "SWDRTE",
                                   "SWDPAC",
                                   "SWDMD1",
                                   "SWDMD2",
                                   "SWDMD3",
                                   "SWDSTS",
                                   "SWDREC",
                                   "SWDOPE"
                                 };
  final static String fid = "1040423110730";
  final static String rid = "43B1CDC9D0849";
  final static String fmtname = "EWD0336DS";
  final int FIELDCOUNT = 35;
  private static Hashtable tlookup = new Hashtable();
  private CharacterField fieldRWDUSR = null;
  private CharacterField fieldRWDTYP = null;
  private CharacterField fieldRWDTYD = null;
  private DecimalField fieldRWDCUN = null;
  private CharacterField fieldRWDSHR = null;
  private DecimalField fieldRWDFD1 = null;
  private DecimalField fieldRWDFD2 = null;
  private DecimalField fieldRWDFD3 = null;
  private DecimalField fieldRWDTD1 = null;
  private DecimalField fieldRWDTD2 = null;
  private DecimalField fieldRWDTD3 = null;
  private DecimalField fieldRWDFDL = null;
  private DecimalField fieldRWDTDL = null;
  private DecimalField fieldRWDFRC = null;
  private CharacterField fieldSWDBNK = null;
  private DecimalField fieldSWDBRN = null;
  private CharacterField fieldSWDCCY = null;
  private DecimalField fieldSWDGLN = null;
  private DecimalField fieldSWDACC = null;
  private CharacterField fieldSWDTYP = null;
  private CharacterField fieldSWDSBT = null;
  private CharacterField fieldSWDPRO = null;
  private DecimalField fieldSWDCUN = null;
  private CharacterField fieldSWDDSC = null;
  private DecimalField fieldSWDAMT = null;
  private DecimalField fieldSWDOAM = null;
  private DecimalField fieldSWDASD = null;
  private DecimalField fieldSWDRTE = null;
  private DecimalField fieldSWDPAC = null;
  private DecimalField fieldSWDMD1 = null;
  private DecimalField fieldSWDMD2 = null;
  private DecimalField fieldSWDMD3 = null;
  private CharacterField fieldSWDSTS = null;
  private DecimalField fieldSWDREC = null;
  private CharacterField fieldSWDOPE = null;

  /**
  * Constructor for EWD0336DSMessage.
  */
  public EWD0336DSMessage()
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
    recordsize = 269;
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
    fields[2] = fieldRWDTYD =
    new CharacterField(message, HEADERSIZE + 11, 1, "RWDTYD");
    fields[3] = fieldRWDCUN =
    new DecimalField(message, HEADERSIZE + 12, 10, 0, "RWDCUN");
    fields[4] = fieldRWDSHR =
    new CharacterField(message, HEADERSIZE + 22, 20, "RWDSHR");
    fields[5] = fieldRWDFD1 =
    new DecimalField(message, HEADERSIZE + 42, 3, 0, "RWDFD1");
    fields[6] = fieldRWDFD2 =
    new DecimalField(message, HEADERSIZE + 45, 3, 0, "RWDFD2");
    fields[7] = fieldRWDFD3 =
    new DecimalField(message, HEADERSIZE + 48, 3, 0, "RWDFD3");
    fields[8] = fieldRWDTD1 =
    new DecimalField(message, HEADERSIZE + 51, 3, 0, "RWDTD1");
    fields[9] = fieldRWDTD2 =
    new DecimalField(message, HEADERSIZE + 54, 3, 0, "RWDTD2");
    fields[10] = fieldRWDTD3 =
    new DecimalField(message, HEADERSIZE + 57, 3, 0, "RWDTD3");
    fields[11] = fieldRWDFDL =
    new DecimalField(message, HEADERSIZE + 60, 10, 0, "RWDFDL");
    fields[12] = fieldRWDTDL =
    new DecimalField(message, HEADERSIZE + 70, 10, 0, "RWDTDL");
    fields[13] = fieldRWDFRC =
    new DecimalField(message, HEADERSIZE + 80, 8, 0, "RWDFRC");
    fields[14] = fieldSWDBNK =
    new CharacterField(message, HEADERSIZE + 88, 2, "SWDBNK");
    fields[15] = fieldSWDBRN =
    new DecimalField(message, HEADERSIZE + 90, 4, 0, "SWDBRN");
    fields[16] = fieldSWDCCY =
    new CharacterField(message, HEADERSIZE + 94, 3, "SWDCCY");
    fields[17] = fieldSWDGLN =
    new DecimalField(message, HEADERSIZE + 97, 13, 0, "SWDGLN");
    fields[18] = fieldSWDACC =
    new DecimalField(message, HEADERSIZE + 110, 10, 0, "SWDACC");
    fields[19] = fieldSWDTYP =
    new CharacterField(message, HEADERSIZE + 120, 3, "SWDTYP");
    fields[20] = fieldSWDSBT =
    new CharacterField(message, HEADERSIZE + 123, 3, "SWDSBT");
    fields[21] = fieldSWDPRO =
    new CharacterField(message, HEADERSIZE + 126, 4, "SWDPRO");
    fields[22] = fieldSWDCUN =
    new DecimalField(message, HEADERSIZE + 130, 10, 0, "SWDCUN");
    fields[23] = fieldSWDDSC =
    new CharacterField(message, HEADERSIZE + 140, 30, "SWDDSC");
    fields[24] = fieldSWDAMT =
    new DecimalField(message, HEADERSIZE + 170, 15, 2, "SWDAMT");
    fields[25] = fieldSWDOAM =
    new DecimalField(message, HEADERSIZE + 185, 15, 2, "SWDOAM");
    fields[26] = fieldSWDASD =
    new DecimalField(message, HEADERSIZE + 200, 15, 2, "SWDASD");
    fields[27] = fieldSWDRTE =
    new DecimalField(message, HEADERSIZE + 215, 11, 6, "SWDRTE");
    fields[28] = fieldSWDPAC =
    new DecimalField(message, HEADERSIZE + 226, 10, 0, "SWDPAC");
    fields[29] = fieldSWDMD1 =
    new DecimalField(message, HEADERSIZE + 236, 3, 0, "SWDMD1");
    fields[30] = fieldSWDMD2 =
    new DecimalField(message, HEADERSIZE + 239, 3, 0, "SWDMD2");
    fields[31] = fieldSWDMD3 =
    new DecimalField(message, HEADERSIZE + 242, 3, 0, "SWDMD3");
    fields[32] = fieldSWDSTS =
    new CharacterField(message, HEADERSIZE + 245, 15, "SWDSTS");
    fields[33] = fieldSWDREC =
    new DecimalField(message, HEADERSIZE + 260, 8, 0, "SWDREC");
    fields[34] = fieldSWDOPE =
    new CharacterField(message, HEADERSIZE + 268, 1, "SWDOPE");

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
  * Set field RWDTYD using a String value.
  */
  public void setRWDTYD(String newvalue)
  {
    fieldRWDTYD.setString(newvalue);
  }

  /**
  * Get value of field RWDTYD as a String.
  */
  public String getRWDTYD()
  {
    return fieldRWDTYD.getString();
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
  * Set field RWDSHR using a String value.
  */
  public void setRWDSHR(String newvalue)
  {
    fieldRWDSHR.setString(newvalue);
  }

  /**
  * Get value of field RWDSHR as a String.
  */
  public String getRWDSHR()
  {
    return fieldRWDSHR.getString();
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
  * Set field RWDTD1 using a String value.
  */
  public void setRWDTD1(String newvalue)
  {
    fieldRWDTD1.setString(newvalue);
  }

  /**
  * Get value of field RWDTD1 as a String.
  */
  public String getRWDTD1()
  {
    return fieldRWDTD1.getString();
  }

  /**
  * Set numeric field RWDTD1 using a BigDecimal value.
  */
  public void setRWDTD1(BigDecimal newvalue)
  {
    fieldRWDTD1.setBigDecimal(newvalue);
  }

  /**
  * Return value of numeric field RWDTD1 as a BigDecimal.
  */
  public BigDecimal getBigDecimalRWDTD1()
  {
    return fieldRWDTD1.getBigDecimal();
  }

  /**
  * Set field RWDTD2 using a String value.
  */
  public void setRWDTD2(String newvalue)
  {
    fieldRWDTD2.setString(newvalue);
  }

  /**
  * Get value of field RWDTD2 as a String.
  */
  public String getRWDTD2()
  {
    return fieldRWDTD2.getString();
  }

  /**
  * Set numeric field RWDTD2 using a BigDecimal value.
  */
  public void setRWDTD2(BigDecimal newvalue)
  {
    fieldRWDTD2.setBigDecimal(newvalue);
  }

  /**
  * Return value of numeric field RWDTD2 as a BigDecimal.
  */
  public BigDecimal getBigDecimalRWDTD2()
  {
    return fieldRWDTD2.getBigDecimal();
  }

  /**
  * Set field RWDTD3 using a String value.
  */
  public void setRWDTD3(String newvalue)
  {
    fieldRWDTD3.setString(newvalue);
  }

  /**
  * Get value of field RWDTD3 as a String.
  */
  public String getRWDTD3()
  {
    return fieldRWDTD3.getString();
  }

  /**
  * Set numeric field RWDTD3 using a BigDecimal value.
  */
  public void setRWDTD3(BigDecimal newvalue)
  {
    fieldRWDTD3.setBigDecimal(newvalue);
  }

  /**
  * Return value of numeric field RWDTD3 as a BigDecimal.
  */
  public BigDecimal getBigDecimalRWDTD3()
  {
    return fieldRWDTD3.getBigDecimal();
  }

  /**
  * Set field RWDFDL using a String value.
  */
  public void setRWDFDL(String newvalue)
  {
    fieldRWDFDL.setString(newvalue);
  }

  /**
  * Get value of field RWDFDL as a String.
  */
  public String getRWDFDL()
  {
    return fieldRWDFDL.getString();
  }

  /**
  * Set numeric field RWDFDL using a BigDecimal value.
  */
  public void setRWDFDL(BigDecimal newvalue)
  {
    fieldRWDFDL.setBigDecimal(newvalue);
  }

  /**
  * Return value of numeric field RWDFDL as a BigDecimal.
  */
  public BigDecimal getBigDecimalRWDFDL()
  {
    return fieldRWDFDL.getBigDecimal();
  }

  /**
  * Set field RWDTDL using a String value.
  */
  public void setRWDTDL(String newvalue)
  {
    fieldRWDTDL.setString(newvalue);
  }

  /**
  * Get value of field RWDTDL as a String.
  */
  public String getRWDTDL()
  {
    return fieldRWDTDL.getString();
  }

  /**
  * Set numeric field RWDTDL using a BigDecimal value.
  */
  public void setRWDTDL(BigDecimal newvalue)
  {
    fieldRWDTDL.setBigDecimal(newvalue);
  }

  /**
  * Return value of numeric field RWDTDL as a BigDecimal.
  */
  public BigDecimal getBigDecimalRWDTDL()
  {
    return fieldRWDTDL.getBigDecimal();
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
  * Set field SWDBNK using a String value.
  */
  public void setSWDBNK(String newvalue)
  {
    fieldSWDBNK.setString(newvalue);
  }

  /**
  * Get value of field SWDBNK as a String.
  */
  public String getSWDBNK()
  {
    return fieldSWDBNK.getString();
  }

  /**
  * Set field SWDBRN using a String value.
  */
  public void setSWDBRN(String newvalue)
  {
    fieldSWDBRN.setString(newvalue);
  }

  /**
  * Get value of field SWDBRN as a String.
  */
  public String getSWDBRN()
  {
    return fieldSWDBRN.getString();
  }

  /**
  * Set numeric field SWDBRN using a BigDecimal value.
  */
  public void setSWDBRN(BigDecimal newvalue)
  {
    fieldSWDBRN.setBigDecimal(newvalue);
  }

  /**
  * Return value of numeric field SWDBRN as a BigDecimal.
  */
  public BigDecimal getBigDecimalSWDBRN()
  {
    return fieldSWDBRN.getBigDecimal();
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
  * Set field SWDGLN using a String value.
  */
  public void setSWDGLN(String newvalue)
  {
    fieldSWDGLN.setString(newvalue);
  }

  /**
  * Get value of field SWDGLN as a String.
  */
  public String getSWDGLN()
  {
    return fieldSWDGLN.getString();
  }

  /**
  * Set numeric field SWDGLN using a BigDecimal value.
  */
  public void setSWDGLN(BigDecimal newvalue)
  {
    fieldSWDGLN.setBigDecimal(newvalue);
  }

  /**
  * Return value of numeric field SWDGLN as a BigDecimal.
  */
  public BigDecimal getBigDecimalSWDGLN()
  {
    return fieldSWDGLN.getBigDecimal();
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
  * Set field SWDSBT using a String value.
  */
  public void setSWDSBT(String newvalue)
  {
    fieldSWDSBT.setString(newvalue);
  }

  /**
  * Get value of field SWDSBT as a String.
  */
  public String getSWDSBT()
  {
    return fieldSWDSBT.getString();
  }

  /**
  * Set field SWDPRO using a String value.
  */
  public void setSWDPRO(String newvalue)
  {
    fieldSWDPRO.setString(newvalue);
  }

  /**
  * Get value of field SWDPRO as a String.
  */
  public String getSWDPRO()
  {
    return fieldSWDPRO.getString();
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
  * Set field SWDDSC using a String value.
  */
  public void setSWDDSC(String newvalue)
  {
    fieldSWDDSC.setString(newvalue);
  }

  /**
  * Get value of field SWDDSC as a String.
  */
  public String getSWDDSC()
  {
    return fieldSWDDSC.getString();
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
  * Set field SWDOAM using a String value.
  */
  public void setSWDOAM(String newvalue)
  {
    fieldSWDOAM.setString(newvalue);
  }

  /**
  * Get value of field SWDOAM as a String.
  */
  public String getSWDOAM()
  {
    return fieldSWDOAM.getString();
  }

  /**
  * Set numeric field SWDOAM using a BigDecimal value.
  */
  public void setSWDOAM(BigDecimal newvalue)
  {
    fieldSWDOAM.setBigDecimal(newvalue);
  }

  /**
  * Return value of numeric field SWDOAM as a BigDecimal.
  */
  public BigDecimal getBigDecimalSWDOAM()
  {
    return fieldSWDOAM.getBigDecimal();
  }

  /**
  * Set field SWDASD using a String value.
  */
  public void setSWDASD(String newvalue)
  {
    fieldSWDASD.setString(newvalue);
  }

  /**
  * Get value of field SWDASD as a String.
  */
  public String getSWDASD()
  {
    return fieldSWDASD.getString();
  }

  /**
  * Set numeric field SWDASD using a BigDecimal value.
  */
  public void setSWDASD(BigDecimal newvalue)
  {
    fieldSWDASD.setBigDecimal(newvalue);
  }

  /**
  * Return value of numeric field SWDASD as a BigDecimal.
  */
  public BigDecimal getBigDecimalSWDASD()
  {
    return fieldSWDASD.getBigDecimal();
  }

  /**
  * Set field SWDRTE using a String value.
  */
  public void setSWDRTE(String newvalue)
  {
    fieldSWDRTE.setString(newvalue);
  }

  /**
  * Get value of field SWDRTE as a String.
  */
  public String getSWDRTE()
  {
    return fieldSWDRTE.getString();
  }

  /**
  * Set numeric field SWDRTE using a BigDecimal value.
  */
  public void setSWDRTE(BigDecimal newvalue)
  {
    fieldSWDRTE.setBigDecimal(newvalue);
  }

  /**
  * Return value of numeric field SWDRTE as a BigDecimal.
  */
  public BigDecimal getBigDecimalSWDRTE()
  {
    return fieldSWDRTE.getBigDecimal();
  }

  /**
  * Set field SWDPAC using a String value.
  */
  public void setSWDPAC(String newvalue)
  {
    fieldSWDPAC.setString(newvalue);
  }

  /**
  * Get value of field SWDPAC as a String.
  */
  public String getSWDPAC()
  {
    return fieldSWDPAC.getString();
  }

  /**
  * Set numeric field SWDPAC using a BigDecimal value.
  */
  public void setSWDPAC(BigDecimal newvalue)
  {
    fieldSWDPAC.setBigDecimal(newvalue);
  }

  /**
  * Return value of numeric field SWDPAC as a BigDecimal.
  */
  public BigDecimal getBigDecimalSWDPAC()
  {
    return fieldSWDPAC.getBigDecimal();
  }

  /**
  * Set field SWDMD1 using a String value.
  */
  public void setSWDMD1(String newvalue)
  {
    fieldSWDMD1.setString(newvalue);
  }

  /**
  * Get value of field SWDMD1 as a String.
  */
  public String getSWDMD1()
  {
    return fieldSWDMD1.getString();
  }

  /**
  * Set numeric field SWDMD1 using a BigDecimal value.
  */
  public void setSWDMD1(BigDecimal newvalue)
  {
    fieldSWDMD1.setBigDecimal(newvalue);
  }

  /**
  * Return value of numeric field SWDMD1 as a BigDecimal.
  */
  public BigDecimal getBigDecimalSWDMD1()
  {
    return fieldSWDMD1.getBigDecimal();
  }

  /**
  * Set field SWDMD2 using a String value.
  */
  public void setSWDMD2(String newvalue)
  {
    fieldSWDMD2.setString(newvalue);
  }

  /**
  * Get value of field SWDMD2 as a String.
  */
  public String getSWDMD2()
  {
    return fieldSWDMD2.getString();
  }

  /**
  * Set numeric field SWDMD2 using a BigDecimal value.
  */
  public void setSWDMD2(BigDecimal newvalue)
  {
    fieldSWDMD2.setBigDecimal(newvalue);
  }

  /**
  * Return value of numeric field SWDMD2 as a BigDecimal.
  */
  public BigDecimal getBigDecimalSWDMD2()
  {
    return fieldSWDMD2.getBigDecimal();
  }

  /**
  * Set field SWDMD3 using a String value.
  */
  public void setSWDMD3(String newvalue)
  {
    fieldSWDMD3.setString(newvalue);
  }

  /**
  * Get value of field SWDMD3 as a String.
  */
  public String getSWDMD3()
  {
    return fieldSWDMD3.getString();
  }

  /**
  * Set numeric field SWDMD3 using a BigDecimal value.
  */
  public void setSWDMD3(BigDecimal newvalue)
  {
    fieldSWDMD3.setBigDecimal(newvalue);
  }

  /**
  * Return value of numeric field SWDMD3 as a BigDecimal.
  */
  public BigDecimal getBigDecimalSWDMD3()
  {
    return fieldSWDMD3.getBigDecimal();
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
