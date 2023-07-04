package datapro.eibs.beans;

import datapro.eibs.sockets.*;
import java.io.*;
import java.math.*;

import java.util.*;

/**
* Class generated by AS/400 CRTCLASS command from EWD0308DS physical file definition.
* 
* File level identifier is 1030403172905.
* Record format level identifier is 3B52D582743C4.
*/

public class EWD0308DSMessage extends MessageRecord
{
  final static String fldnames[] = {
                                     "RWDUSR",
                                     "RWDTYP",
                                     "RWDFRC",
                                     "SWDIIC",
                                     "SWDDSC",
                                     "SWDPTY",
                                     "SWDNUM",
                                     "SWDSER",
                                     "SWDCUS",
                                     "SWDSYM",
                                     "SWDEMI",
                                     "SWDENM",
                                     "SWDSD1",
                                     "SWDSD2",
                                     "SWDSD3",
                                     "SWDFR1",
                                     "SWDFR2",
                                     "SWDFR3",
                                     "SWDTO1",
                                     "SWDTO2",
                                     "SWDTO3",
                                     "SWDMA1",
                                     "SWDMA2",
                                     "SWDMA3",
                                     "SWDMF1",
                                     "SWDMF2",
                                     "SWDMF3",
                                     "SWDMT1",
                                     "SWDMT2",
                                     "SWDMT3",
                                     "SWDSTS",
                                     "SWDSDS",
                                     "SWDMKS",
                                     "SWDMDS",
                                     "SWDPRE",
                                     "SWDBCY",
                                     "SWDCCY",
                                     "SWDRTE",
                                     "SWDCTR",
                                     "SWDCTC",
                                     "SWDREC",
                                     "SWDOPE",
                                     "SWDISIOPE",
                                     "SWDOPEDSC",
                                     "SWDISIDLT",
                                     "SWDISIREJ",
                                     "SWDISIRMK",
                                     "SWDISIFL0",
                                     "SWDISIFL1",
                                     "SWDISIFL2",
                                     "SWDISIFL3",
                                     "SWDISIFL4",
                                     "SWDISIFL5",
                                     "SWDISIFL6",
                                     "SWDISIFL7",
                                     "SWDISIFL8",
                                     "SWDISIFL9",
                                     "SWDISIRSP",
                                     "SWDISIRMD"
                                   };
  final static String tnames[] = {
                                   "RWDUSR",
                                   "RWDTYP",
                                   "RWDFRC",
                                   "SWDIIC",
                                   "SWDDSC",
                                   "SWDPTY",
                                   "SWDNUM",
                                   "SWDSER",
                                   "SWDCUS",
                                   "SWDSYM",
                                   "SWDEMI",
                                   "SWDENM",
                                   "SWDSD1",
                                   "SWDSD2",
                                   "SWDSD3",
                                   "SWDFR1",
                                   "SWDFR2",
                                   "SWDFR3",
                                   "SWDTO1",
                                   "SWDTO2",
                                   "SWDTO3",
                                   "SWDMA1",
                                   "SWDMA2",
                                   "SWDMA3",
                                   "SWDMF1",
                                   "SWDMF2",
                                   "SWDMF3",
                                   "SWDMT1",
                                   "SWDMT2",
                                   "SWDMT3",
                                   "SWDSTS",
                                   "SWDSDS",
                                   "SWDMKS",
                                   "SWDMDS",
                                   "SWDPRE",
                                   "SWDBCY",
                                   "SWDCCY",
                                   "SWDRTE",
                                   "SWDCTR",
                                   "SWDCTC",
                                   "SWDREC",
                                   "SWDOPE",
                                   "SWDISIOPE",
                                   "SWDOPEDSC",
                                   "SWDISIDLT",
                                   "SWDISIREJ",
                                   "SWDISIRMK",
                                   "SWDISIFL0",
                                   "SWDISIFL1",
                                   "SWDISIFL2",
                                   "SWDISIFL3",
                                   "SWDISIFL4",
                                   "SWDISIFL5",
                                   "SWDISIFL6",
                                   "SWDISIFL7",
                                   "SWDISIFL8",
                                   "SWDISIFL9",
                                   "SWDISIRSP",
                                   "SWDISIRMD"
                                 };
  final static String fid = "1030403172905";
  final static String rid = "3B52D582743C4";
  final static String fmtname = "EWD0308DS";
  final int FIELDCOUNT = 59;
  private static Hashtable tlookup = new Hashtable();
  private CharacterField fieldRWDUSR = null;
  private CharacterField fieldRWDTYP = null;
  private DecimalField fieldRWDFRC = null;
  private DecimalField fieldSWDIIC = null;
  private CharacterField fieldSWDDSC = null;
  private CharacterField fieldSWDPTY = null;
  private CharacterField fieldSWDNUM = null;
  private CharacterField fieldSWDSER = null;
  private CharacterField fieldSWDCUS = null;
  private CharacterField fieldSWDSYM = null;
  private CharacterField fieldSWDEMI = null;
  private CharacterField fieldSWDENM = null;
  private DecimalField fieldSWDSD1 = null;
  private DecimalField fieldSWDSD2 = null;
  private DecimalField fieldSWDSD3 = null;
  private DecimalField fieldSWDFR1 = null;
  private DecimalField fieldSWDFR2 = null;
  private DecimalField fieldSWDFR3 = null;
  private DecimalField fieldSWDTO1 = null;
  private DecimalField fieldSWDTO2 = null;
  private DecimalField fieldSWDTO3 = null;
  private DecimalField fieldSWDMA1 = null;
  private DecimalField fieldSWDMA2 = null;
  private DecimalField fieldSWDMA3 = null;
  private DecimalField fieldSWDMF1 = null;
  private DecimalField fieldSWDMF2 = null;
  private DecimalField fieldSWDMF3 = null;
  private DecimalField fieldSWDMT1 = null;
  private DecimalField fieldSWDMT2 = null;
  private DecimalField fieldSWDMT3 = null;
  private CharacterField fieldSWDSTS = null;
  private CharacterField fieldSWDSDS = null;
  private CharacterField fieldSWDMKS = null;
  private CharacterField fieldSWDMDS = null;
  private CharacterField fieldSWDPRE = null;
  private CharacterField fieldSWDBCY = null;
  private CharacterField fieldSWDCCY = null;
  private DecimalField fieldSWDRTE = null;
  private CharacterField fieldSWDCTR = null;
  private CharacterField fieldSWDCTC = null;
  private DecimalField fieldSWDREC = null;
  private CharacterField fieldSWDOPE = null;
  private CharacterField fieldSWDISIOPE = null;
  private CharacterField fieldSWDOPEDSC = null;
  private CharacterField fieldSWDISIDLT = null;
  private CharacterField fieldSWDISIREJ = null;
  private CharacterField fieldSWDISIRMK = null;
  private CharacterField fieldSWDISIFL0 = null;
  private CharacterField fieldSWDISIFL1 = null;
  private CharacterField fieldSWDISIFL2 = null;
  private CharacterField fieldSWDISIFL3 = null;
  private CharacterField fieldSWDISIFL4 = null;
  private CharacterField fieldSWDISIFL5 = null;
  private CharacterField fieldSWDISIFL6 = null;
  private DecimalField fieldSWDISIFL7 = null;
  private DecimalField fieldSWDISIFL8 = null;
  private DecimalField fieldSWDISIFL9 = null;
  private CharacterField fieldSWDISIRSP = null;
  private CharacterField fieldSWDISIRMD = null;

  /**
  * Constructor for EWD0308DSMessage.
  */
  public EWD0308DSMessage()
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
    recordsize = 544;
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
    fields[3] = fieldSWDIIC =
    new DecimalField(message, HEADERSIZE + 19, 10, 0, "SWDIIC");
    fields[4] = fieldSWDDSC =
    new CharacterField(message, HEADERSIZE + 29, 50, "SWDDSC");
    fields[5] = fieldSWDPTY =
    new CharacterField(message, HEADERSIZE + 79, 3, "SWDPTY");
    fields[6] = fieldSWDNUM =
    new CharacterField(message, HEADERSIZE + 82, 12, "SWDNUM");
    fields[7] = fieldSWDSER =
    new CharacterField(message, HEADERSIZE + 94, 4, "SWDSER");
    fields[8] = fieldSWDCUS =
    new CharacterField(message, HEADERSIZE + 98, 12, "SWDCUS");
    fields[9] = fieldSWDSYM =
    new CharacterField(message, HEADERSIZE + 110, 15, "SWDSYM");
    fields[10] = fieldSWDEMI =
    new CharacterField(message, HEADERSIZE + 125, 3, "SWDEMI");
    fields[11] = fieldSWDENM =
    new CharacterField(message, HEADERSIZE + 128, 45, "SWDENM");
    fields[12] = fieldSWDSD1 =
    new DecimalField(message, HEADERSIZE + 173, 3, 0, "SWDSD1");
    fields[13] = fieldSWDSD2 =
    new DecimalField(message, HEADERSIZE + 176, 3, 0, "SWDSD2");
    fields[14] = fieldSWDSD3 =
    new DecimalField(message, HEADERSIZE + 179, 3, 0, "SWDSD3");
    fields[15] = fieldSWDFR1 =
    new DecimalField(message, HEADERSIZE + 182, 3, 0, "SWDFR1");
    fields[16] = fieldSWDFR2 =
    new DecimalField(message, HEADERSIZE + 185, 3, 0, "SWDFR2");
    fields[17] = fieldSWDFR3 =
    new DecimalField(message, HEADERSIZE + 188, 3, 0, "SWDFR3");
    fields[18] = fieldSWDTO1 =
    new DecimalField(message, HEADERSIZE + 191, 3, 0, "SWDTO1");
    fields[19] = fieldSWDTO2 =
    new DecimalField(message, HEADERSIZE + 194, 3, 0, "SWDTO2");
    fields[20] = fieldSWDTO3 =
    new DecimalField(message, HEADERSIZE + 197, 3, 0, "SWDTO3");
    fields[21] = fieldSWDMA1 =
    new DecimalField(message, HEADERSIZE + 200, 3, 0, "SWDMA1");
    fields[22] = fieldSWDMA2 =
    new DecimalField(message, HEADERSIZE + 203, 3, 0, "SWDMA2");
    fields[23] = fieldSWDMA3 =
    new DecimalField(message, HEADERSIZE + 206, 3, 0, "SWDMA3");
    fields[24] = fieldSWDMF1 =
    new DecimalField(message, HEADERSIZE + 209, 3, 0, "SWDMF1");
    fields[25] = fieldSWDMF2 =
    new DecimalField(message, HEADERSIZE + 212, 3, 0, "SWDMF2");
    fields[26] = fieldSWDMF3 =
    new DecimalField(message, HEADERSIZE + 215, 3, 0, "SWDMF3");
    fields[27] = fieldSWDMT1 =
    new DecimalField(message, HEADERSIZE + 218, 3, 0, "SWDMT1");
    fields[28] = fieldSWDMT2 =
    new DecimalField(message, HEADERSIZE + 221, 3, 0, "SWDMT2");
    fields[29] = fieldSWDMT3 =
    new DecimalField(message, HEADERSIZE + 224, 3, 0, "SWDMT3");
    fields[30] = fieldSWDSTS =
    new CharacterField(message, HEADERSIZE + 227, 1, "SWDSTS");
    fields[31] = fieldSWDSDS =
    new CharacterField(message, HEADERSIZE + 228, 15, "SWDSDS");
    fields[32] = fieldSWDMKS =
    new CharacterField(message, HEADERSIZE + 243, 10, "SWDMKS");
    fields[33] = fieldSWDMDS =
    new CharacterField(message, HEADERSIZE + 253, 15, "SWDMDS");
    fields[34] = fieldSWDPRE =
    new CharacterField(message, HEADERSIZE + 268, 1, "SWDPRE");
    fields[35] = fieldSWDBCY =
    new CharacterField(message, HEADERSIZE + 269, 6, "SWDBCY");
    fields[36] = fieldSWDCCY =
    new CharacterField(message, HEADERSIZE + 275, 3, "SWDCCY");
    fields[37] = fieldSWDRTE =
    new DecimalField(message, HEADERSIZE + 278, 11, 6, "SWDRTE");
    fields[38] = fieldSWDCTR =
    new CharacterField(message, HEADERSIZE + 289, 30, "SWDCTR");
    fields[39] = fieldSWDCTC =
    new CharacterField(message, HEADERSIZE + 319, 4, "SWDCTC");
    fields[40] = fieldSWDREC =
    new DecimalField(message, HEADERSIZE + 323, 8, 0, "SWDREC");
    fields[41] = fieldSWDOPE =
    new CharacterField(message, HEADERSIZE + 331, 1, "SWDOPE");
    fields[42] = fieldSWDISIOPE =
    new CharacterField(message, HEADERSIZE + 332, 1, "SWDISIOPE");
    fields[43] = fieldSWDOPEDSC =
    new CharacterField(message, HEADERSIZE + 333, 15, "SWDOPEDSC");
    fields[44] = fieldSWDISIDLT =
    new CharacterField(message, HEADERSIZE + 348, 1, "SWDISIDLT");
    fields[45] = fieldSWDISIREJ =
    new CharacterField(message, HEADERSIZE + 349, 1, "SWDISIREJ");
    fields[46] = fieldSWDISIRMK =
    new CharacterField(message, HEADERSIZE + 350, 30, "SWDISIRMK");
    fields[47] = fieldSWDISIFL0 =
    new CharacterField(message, HEADERSIZE + 380, 1, "SWDISIFL0");
    fields[48] = fieldSWDISIFL1 =
    new CharacterField(message, HEADERSIZE + 381, 1, "SWDISIFL1");
    fields[49] = fieldSWDISIFL2 =
    new CharacterField(message, HEADERSIZE + 382, 1, "SWDISIFL2");
    fields[50] = fieldSWDISIFL3 =
    new CharacterField(message, HEADERSIZE + 383, 1, "SWDISIFL3");
    fields[51] = fieldSWDISIFL4 =
    new CharacterField(message, HEADERSIZE + 384, 15, "SWDISIFL4");
    fields[52] = fieldSWDISIFL5 =
    new CharacterField(message, HEADERSIZE + 399, 15, "SWDISIFL5");
    fields[53] = fieldSWDISIFL6 =
    new CharacterField(message, HEADERSIZE + 414, 15, "SWDISIFL6");
    fields[54] = fieldSWDISIFL7 =
    new DecimalField(message, HEADERSIZE + 429, 23, 6, "SWDISIFL7");
    fields[55] = fieldSWDISIFL8 =
    new DecimalField(message, HEADERSIZE + 452, 23, 6, "SWDISIFL8");
    fields[56] = fieldSWDISIFL9 =
    new DecimalField(message, HEADERSIZE + 475, 23, 6, "SWDISIFL9");
    fields[57] = fieldSWDISIRSP =
    new CharacterField(message, HEADERSIZE + 498, 23, "SWDISIRSP");
    fields[58] = fieldSWDISIRMD =
    new CharacterField(message, HEADERSIZE + 521, 23, "SWDISIRMD");

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
  * Set field SWDIIC using a String value.
  */
  public void setSWDIIC(String newvalue)
  {
    fieldSWDIIC.setString(newvalue);
  }

  /**
  * Get value of field SWDIIC as a String.
  */
  public String getSWDIIC()
  {
    return fieldSWDIIC.getString();
  }

  /**
  * Set numeric field SWDIIC using a BigDecimal value.
  */
  public void setSWDIIC(BigDecimal newvalue)
  {
    fieldSWDIIC.setBigDecimal(newvalue);
  }

  /**
  * Return value of numeric field SWDIIC as a BigDecimal.
  */
  public BigDecimal getBigDecimalSWDIIC()
  {
    return fieldSWDIIC.getBigDecimal();
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
  * Set field SWDPTY using a String value.
  */
  public void setSWDPTY(String newvalue)
  {
    fieldSWDPTY.setString(newvalue);
  }

  /**
  * Get value of field SWDPTY as a String.
  */
  public String getSWDPTY()
  {
    return fieldSWDPTY.getString();
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
  * Set field SWDSER using a String value.
  */
  public void setSWDSER(String newvalue)
  {
    fieldSWDSER.setString(newvalue);
  }

  /**
  * Get value of field SWDSER as a String.
  */
  public String getSWDSER()
  {
    return fieldSWDSER.getString();
  }

  /**
  * Set field SWDCUS using a String value.
  */
  public void setSWDCUS(String newvalue)
  {
    fieldSWDCUS.setString(newvalue);
  }

  /**
  * Get value of field SWDCUS as a String.
  */
  public String getSWDCUS()
  {
    return fieldSWDCUS.getString();
  }

  /**
  * Set field SWDSYM using a String value.
  */
  public void setSWDSYM(String newvalue)
  {
    fieldSWDSYM.setString(newvalue);
  }

  /**
  * Get value of field SWDSYM as a String.
  */
  public String getSWDSYM()
  {
    return fieldSWDSYM.getString();
  }

  /**
  * Set field SWDEMI using a String value.
  */
  public void setSWDEMI(String newvalue)
  {
    fieldSWDEMI.setString(newvalue);
  }

  /**
  * Get value of field SWDEMI as a String.
  */
  public String getSWDEMI()
  {
    return fieldSWDEMI.getString();
  }

  /**
  * Set field SWDENM using a String value.
  */
  public void setSWDENM(String newvalue)
  {
    fieldSWDENM.setString(newvalue);
  }

  /**
  * Get value of field SWDENM as a String.
  */
  public String getSWDENM()
  {
    return fieldSWDENM.getString();
  }

  /**
  * Set field SWDSD1 using a String value.
  */
  public void setSWDSD1(String newvalue)
  {
    fieldSWDSD1.setString(newvalue);
  }

  /**
  * Get value of field SWDSD1 as a String.
  */
  public String getSWDSD1()
  {
    return fieldSWDSD1.getString();
  }

  /**
  * Set numeric field SWDSD1 using a BigDecimal value.
  */
  public void setSWDSD1(BigDecimal newvalue)
  {
    fieldSWDSD1.setBigDecimal(newvalue);
  }

  /**
  * Return value of numeric field SWDSD1 as a BigDecimal.
  */
  public BigDecimal getBigDecimalSWDSD1()
  {
    return fieldSWDSD1.getBigDecimal();
  }

  /**
  * Set field SWDSD2 using a String value.
  */
  public void setSWDSD2(String newvalue)
  {
    fieldSWDSD2.setString(newvalue);
  }

  /**
  * Get value of field SWDSD2 as a String.
  */
  public String getSWDSD2()
  {
    return fieldSWDSD2.getString();
  }

  /**
  * Set numeric field SWDSD2 using a BigDecimal value.
  */
  public void setSWDSD2(BigDecimal newvalue)
  {
    fieldSWDSD2.setBigDecimal(newvalue);
  }

  /**
  * Return value of numeric field SWDSD2 as a BigDecimal.
  */
  public BigDecimal getBigDecimalSWDSD2()
  {
    return fieldSWDSD2.getBigDecimal();
  }

  /**
  * Set field SWDSD3 using a String value.
  */
  public void setSWDSD3(String newvalue)
  {
    fieldSWDSD3.setString(newvalue);
  }

  /**
  * Get value of field SWDSD3 as a String.
  */
  public String getSWDSD3()
  {
    return fieldSWDSD3.getString();
  }

  /**
  * Set numeric field SWDSD3 using a BigDecimal value.
  */
  public void setSWDSD3(BigDecimal newvalue)
  {
    fieldSWDSD3.setBigDecimal(newvalue);
  }

  /**
  * Return value of numeric field SWDSD3 as a BigDecimal.
  */
  public BigDecimal getBigDecimalSWDSD3()
  {
    return fieldSWDSD3.getBigDecimal();
  }

  /**
  * Set field SWDFR1 using a String value.
  */
  public void setSWDFR1(String newvalue)
  {
    fieldSWDFR1.setString(newvalue);
  }

  /**
  * Get value of field SWDFR1 as a String.
  */
  public String getSWDFR1()
  {
    return fieldSWDFR1.getString();
  }

  /**
  * Set numeric field SWDFR1 using a BigDecimal value.
  */
  public void setSWDFR1(BigDecimal newvalue)
  {
    fieldSWDFR1.setBigDecimal(newvalue);
  }

  /**
  * Return value of numeric field SWDFR1 as a BigDecimal.
  */
  public BigDecimal getBigDecimalSWDFR1()
  {
    return fieldSWDFR1.getBigDecimal();
  }

  /**
  * Set field SWDFR2 using a String value.
  */
  public void setSWDFR2(String newvalue)
  {
    fieldSWDFR2.setString(newvalue);
  }

  /**
  * Get value of field SWDFR2 as a String.
  */
  public String getSWDFR2()
  {
    return fieldSWDFR2.getString();
  }

  /**
  * Set numeric field SWDFR2 using a BigDecimal value.
  */
  public void setSWDFR2(BigDecimal newvalue)
  {
    fieldSWDFR2.setBigDecimal(newvalue);
  }

  /**
  * Return value of numeric field SWDFR2 as a BigDecimal.
  */
  public BigDecimal getBigDecimalSWDFR2()
  {
    return fieldSWDFR2.getBigDecimal();
  }

  /**
  * Set field SWDFR3 using a String value.
  */
  public void setSWDFR3(String newvalue)
  {
    fieldSWDFR3.setString(newvalue);
  }

  /**
  * Get value of field SWDFR3 as a String.
  */
  public String getSWDFR3()
  {
    return fieldSWDFR3.getString();
  }

  /**
  * Set numeric field SWDFR3 using a BigDecimal value.
  */
  public void setSWDFR3(BigDecimal newvalue)
  {
    fieldSWDFR3.setBigDecimal(newvalue);
  }

  /**
  * Return value of numeric field SWDFR3 as a BigDecimal.
  */
  public BigDecimal getBigDecimalSWDFR3()
  {
    return fieldSWDFR3.getBigDecimal();
  }

  /**
  * Set field SWDTO1 using a String value.
  */
  public void setSWDTO1(String newvalue)
  {
    fieldSWDTO1.setString(newvalue);
  }

  /**
  * Get value of field SWDTO1 as a String.
  */
  public String getSWDTO1()
  {
    return fieldSWDTO1.getString();
  }

  /**
  * Set numeric field SWDTO1 using a BigDecimal value.
  */
  public void setSWDTO1(BigDecimal newvalue)
  {
    fieldSWDTO1.setBigDecimal(newvalue);
  }

  /**
  * Return value of numeric field SWDTO1 as a BigDecimal.
  */
  public BigDecimal getBigDecimalSWDTO1()
  {
    return fieldSWDTO1.getBigDecimal();
  }

  /**
  * Set field SWDTO2 using a String value.
  */
  public void setSWDTO2(String newvalue)
  {
    fieldSWDTO2.setString(newvalue);
  }

  /**
  * Get value of field SWDTO2 as a String.
  */
  public String getSWDTO2()
  {
    return fieldSWDTO2.getString();
  }

  /**
  * Set numeric field SWDTO2 using a BigDecimal value.
  */
  public void setSWDTO2(BigDecimal newvalue)
  {
    fieldSWDTO2.setBigDecimal(newvalue);
  }

  /**
  * Return value of numeric field SWDTO2 as a BigDecimal.
  */
  public BigDecimal getBigDecimalSWDTO2()
  {
    return fieldSWDTO2.getBigDecimal();
  }

  /**
  * Set field SWDTO3 using a String value.
  */
  public void setSWDTO3(String newvalue)
  {
    fieldSWDTO3.setString(newvalue);
  }

  /**
  * Get value of field SWDTO3 as a String.
  */
  public String getSWDTO3()
  {
    return fieldSWDTO3.getString();
  }

  /**
  * Set numeric field SWDTO3 using a BigDecimal value.
  */
  public void setSWDTO3(BigDecimal newvalue)
  {
    fieldSWDTO3.setBigDecimal(newvalue);
  }

  /**
  * Return value of numeric field SWDTO3 as a BigDecimal.
  */
  public BigDecimal getBigDecimalSWDTO3()
  {
    return fieldSWDTO3.getBigDecimal();
  }

  /**
  * Set field SWDMA1 using a String value.
  */
  public void setSWDMA1(String newvalue)
  {
    fieldSWDMA1.setString(newvalue);
  }

  /**
  * Get value of field SWDMA1 as a String.
  */
  public String getSWDMA1()
  {
    return fieldSWDMA1.getString();
  }

  /**
  * Set numeric field SWDMA1 using a BigDecimal value.
  */
  public void setSWDMA1(BigDecimal newvalue)
  {
    fieldSWDMA1.setBigDecimal(newvalue);
  }

  /**
  * Return value of numeric field SWDMA1 as a BigDecimal.
  */
  public BigDecimal getBigDecimalSWDMA1()
  {
    return fieldSWDMA1.getBigDecimal();
  }

  /**
  * Set field SWDMA2 using a String value.
  */
  public void setSWDMA2(String newvalue)
  {
    fieldSWDMA2.setString(newvalue);
  }

  /**
  * Get value of field SWDMA2 as a String.
  */
  public String getSWDMA2()
  {
    return fieldSWDMA2.getString();
  }

  /**
  * Set numeric field SWDMA2 using a BigDecimal value.
  */
  public void setSWDMA2(BigDecimal newvalue)
  {
    fieldSWDMA2.setBigDecimal(newvalue);
  }

  /**
  * Return value of numeric field SWDMA2 as a BigDecimal.
  */
  public BigDecimal getBigDecimalSWDMA2()
  {
    return fieldSWDMA2.getBigDecimal();
  }

  /**
  * Set field SWDMA3 using a String value.
  */
  public void setSWDMA3(String newvalue)
  {
    fieldSWDMA3.setString(newvalue);
  }

  /**
  * Get value of field SWDMA3 as a String.
  */
  public String getSWDMA3()
  {
    return fieldSWDMA3.getString();
  }

  /**
  * Set numeric field SWDMA3 using a BigDecimal value.
  */
  public void setSWDMA3(BigDecimal newvalue)
  {
    fieldSWDMA3.setBigDecimal(newvalue);
  }

  /**
  * Return value of numeric field SWDMA3 as a BigDecimal.
  */
  public BigDecimal getBigDecimalSWDMA3()
  {
    return fieldSWDMA3.getBigDecimal();
  }

  /**
  * Set field SWDMF1 using a String value.
  */
  public void setSWDMF1(String newvalue)
  {
    fieldSWDMF1.setString(newvalue);
  }

  /**
  * Get value of field SWDMF1 as a String.
  */
  public String getSWDMF1()
  {
    return fieldSWDMF1.getString();
  }

  /**
  * Set numeric field SWDMF1 using a BigDecimal value.
  */
  public void setSWDMF1(BigDecimal newvalue)
  {
    fieldSWDMF1.setBigDecimal(newvalue);
  }

  /**
  * Return value of numeric field SWDMF1 as a BigDecimal.
  */
  public BigDecimal getBigDecimalSWDMF1()
  {
    return fieldSWDMF1.getBigDecimal();
  }

  /**
  * Set field SWDMF2 using a String value.
  */
  public void setSWDMF2(String newvalue)
  {
    fieldSWDMF2.setString(newvalue);
  }

  /**
  * Get value of field SWDMF2 as a String.
  */
  public String getSWDMF2()
  {
    return fieldSWDMF2.getString();
  }

  /**
  * Set numeric field SWDMF2 using a BigDecimal value.
  */
  public void setSWDMF2(BigDecimal newvalue)
  {
    fieldSWDMF2.setBigDecimal(newvalue);
  }

  /**
  * Return value of numeric field SWDMF2 as a BigDecimal.
  */
  public BigDecimal getBigDecimalSWDMF2()
  {
    return fieldSWDMF2.getBigDecimal();
  }

  /**
  * Set field SWDMF3 using a String value.
  */
  public void setSWDMF3(String newvalue)
  {
    fieldSWDMF3.setString(newvalue);
  }

  /**
  * Get value of field SWDMF3 as a String.
  */
  public String getSWDMF3()
  {
    return fieldSWDMF3.getString();
  }

  /**
  * Set numeric field SWDMF3 using a BigDecimal value.
  */
  public void setSWDMF3(BigDecimal newvalue)
  {
    fieldSWDMF3.setBigDecimal(newvalue);
  }

  /**
  * Return value of numeric field SWDMF3 as a BigDecimal.
  */
  public BigDecimal getBigDecimalSWDMF3()
  {
    return fieldSWDMF3.getBigDecimal();
  }

  /**
  * Set field SWDMT1 using a String value.
  */
  public void setSWDMT1(String newvalue)
  {
    fieldSWDMT1.setString(newvalue);
  }

  /**
  * Get value of field SWDMT1 as a String.
  */
  public String getSWDMT1()
  {
    return fieldSWDMT1.getString();
  }

  /**
  * Set numeric field SWDMT1 using a BigDecimal value.
  */
  public void setSWDMT1(BigDecimal newvalue)
  {
    fieldSWDMT1.setBigDecimal(newvalue);
  }

  /**
  * Return value of numeric field SWDMT1 as a BigDecimal.
  */
  public BigDecimal getBigDecimalSWDMT1()
  {
    return fieldSWDMT1.getBigDecimal();
  }

  /**
  * Set field SWDMT2 using a String value.
  */
  public void setSWDMT2(String newvalue)
  {
    fieldSWDMT2.setString(newvalue);
  }

  /**
  * Get value of field SWDMT2 as a String.
  */
  public String getSWDMT2()
  {
    return fieldSWDMT2.getString();
  }

  /**
  * Set numeric field SWDMT2 using a BigDecimal value.
  */
  public void setSWDMT2(BigDecimal newvalue)
  {
    fieldSWDMT2.setBigDecimal(newvalue);
  }

  /**
  * Return value of numeric field SWDMT2 as a BigDecimal.
  */
  public BigDecimal getBigDecimalSWDMT2()
  {
    return fieldSWDMT2.getBigDecimal();
  }

  /**
  * Set field SWDMT3 using a String value.
  */
  public void setSWDMT3(String newvalue)
  {
    fieldSWDMT3.setString(newvalue);
  }

  /**
  * Get value of field SWDMT3 as a String.
  */
  public String getSWDMT3()
  {
    return fieldSWDMT3.getString();
  }

  /**
  * Set numeric field SWDMT3 using a BigDecimal value.
  */
  public void setSWDMT3(BigDecimal newvalue)
  {
    fieldSWDMT3.setBigDecimal(newvalue);
  }

  /**
  * Return value of numeric field SWDMT3 as a BigDecimal.
  */
  public BigDecimal getBigDecimalSWDMT3()
  {
    return fieldSWDMT3.getBigDecimal();
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
  * Set field SWDSDS using a String value.
  */
  public void setSWDSDS(String newvalue)
  {
    fieldSWDSDS.setString(newvalue);
  }

  /**
  * Get value of field SWDSDS as a String.
  */
  public String getSWDSDS()
  {
    return fieldSWDSDS.getString();
  }

  /**
  * Set field SWDMKS using a String value.
  */
  public void setSWDMKS(String newvalue)
  {
    fieldSWDMKS.setString(newvalue);
  }

  /**
  * Get value of field SWDMKS as a String.
  */
  public String getSWDMKS()
  {
    return fieldSWDMKS.getString();
  }

  /**
  * Set field SWDMDS using a String value.
  */
  public void setSWDMDS(String newvalue)
  {
    fieldSWDMDS.setString(newvalue);
  }

  /**
  * Get value of field SWDMDS as a String.
  */
  public String getSWDMDS()
  {
    return fieldSWDMDS.getString();
  }

  /**
  * Set field SWDPRE using a String value.
  */
  public void setSWDPRE(String newvalue)
  {
    fieldSWDPRE.setString(newvalue);
  }

  /**
  * Get value of field SWDPRE as a String.
  */
  public String getSWDPRE()
  {
    return fieldSWDPRE.getString();
  }

  /**
  * Set field SWDBCY using a String value.
  */
  public void setSWDBCY(String newvalue)
  {
    fieldSWDBCY.setString(newvalue);
  }

  /**
  * Get value of field SWDBCY as a String.
  */
  public String getSWDBCY()
  {
    return fieldSWDBCY.getString();
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
  * Set field SWDCTR using a String value.
  */
  public void setSWDCTR(String newvalue)
  {
    fieldSWDCTR.setString(newvalue);
  }

  /**
  * Get value of field SWDCTR as a String.
  */
  public String getSWDCTR()
  {
    return fieldSWDCTR.getString();
  }

  /**
  * Set field SWDCTC using a String value.
  */
  public void setSWDCTC(String newvalue)
  {
    fieldSWDCTC.setString(newvalue);
  }

  /**
  * Get value of field SWDCTC as a String.
  */
  public String getSWDCTC()
  {
    return fieldSWDCTC.getString();
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
  * Set field SWDISIOPE using a String value.
  */
  public void setSWDISIOPE(String newvalue)
  {
    fieldSWDISIOPE.setString(newvalue);
  }

  /**
  * Get value of field SWDISIOPE as a String.
  */
  public String getSWDISIOPE()
  {
    return fieldSWDISIOPE.getString();
  }

  /**
  * Set field SWDOPEDSC using a String value.
  */
  public void setSWDOPEDSC(String newvalue)
  {
    fieldSWDOPEDSC.setString(newvalue);
  }

  /**
  * Get value of field SWDOPEDSC as a String.
  */
  public String getSWDOPEDSC()
  {
    return fieldSWDOPEDSC.getString();
  }

  /**
  * Set field SWDISIDLT using a String value.
  */
  public void setSWDISIDLT(String newvalue)
  {
    fieldSWDISIDLT.setString(newvalue);
  }

  /**
  * Get value of field SWDISIDLT as a String.
  */
  public String getSWDISIDLT()
  {
    return fieldSWDISIDLT.getString();
  }

  /**
  * Set field SWDISIREJ using a String value.
  */
  public void setSWDISIREJ(String newvalue)
  {
    fieldSWDISIREJ.setString(newvalue);
  }

  /**
  * Get value of field SWDISIREJ as a String.
  */
  public String getSWDISIREJ()
  {
    return fieldSWDISIREJ.getString();
  }

  /**
  * Set field SWDISIRMK using a String value.
  */
  public void setSWDISIRMK(String newvalue)
  {
    fieldSWDISIRMK.setString(newvalue);
  }

  /**
  * Get value of field SWDISIRMK as a String.
  */
  public String getSWDISIRMK()
  {
    return fieldSWDISIRMK.getString();
  }

  /**
  * Set field SWDISIFL0 using a String value.
  */
  public void setSWDISIFL0(String newvalue)
  {
    fieldSWDISIFL0.setString(newvalue);
  }

  /**
  * Get value of field SWDISIFL0 as a String.
  */
  public String getSWDISIFL0()
  {
    return fieldSWDISIFL0.getString();
  }

  /**
  * Set field SWDISIFL1 using a String value.
  */
  public void setSWDISIFL1(String newvalue)
  {
    fieldSWDISIFL1.setString(newvalue);
  }

  /**
  * Get value of field SWDISIFL1 as a String.
  */
  public String getSWDISIFL1()
  {
    return fieldSWDISIFL1.getString();
  }

  /**
  * Set field SWDISIFL2 using a String value.
  */
  public void setSWDISIFL2(String newvalue)
  {
    fieldSWDISIFL2.setString(newvalue);
  }

  /**
  * Get value of field SWDISIFL2 as a String.
  */
  public String getSWDISIFL2()
  {
    return fieldSWDISIFL2.getString();
  }

  /**
  * Set field SWDISIFL3 using a String value.
  */
  public void setSWDISIFL3(String newvalue)
  {
    fieldSWDISIFL3.setString(newvalue);
  }

  /**
  * Get value of field SWDISIFL3 as a String.
  */
  public String getSWDISIFL3()
  {
    return fieldSWDISIFL3.getString();
  }

  /**
  * Set field SWDISIFL4 using a String value.
  */
  public void setSWDISIFL4(String newvalue)
  {
    fieldSWDISIFL4.setString(newvalue);
  }

  /**
  * Get value of field SWDISIFL4 as a String.
  */
  public String getSWDISIFL4()
  {
    return fieldSWDISIFL4.getString();
  }

  /**
  * Set field SWDISIFL5 using a String value.
  */
  public void setSWDISIFL5(String newvalue)
  {
    fieldSWDISIFL5.setString(newvalue);
  }

  /**
  * Get value of field SWDISIFL5 as a String.
  */
  public String getSWDISIFL5()
  {
    return fieldSWDISIFL5.getString();
  }

  /**
  * Set field SWDISIFL6 using a String value.
  */
  public void setSWDISIFL6(String newvalue)
  {
    fieldSWDISIFL6.setString(newvalue);
  }

  /**
  * Get value of field SWDISIFL6 as a String.
  */
  public String getSWDISIFL6()
  {
    return fieldSWDISIFL6.getString();
  }

  /**
  * Set field SWDISIFL7 using a String value.
  */
  public void setSWDISIFL7(String newvalue)
  {
    fieldSWDISIFL7.setString(newvalue);
  }

  /**
  * Get value of field SWDISIFL7 as a String.
  */
  public String getSWDISIFL7()
  {
    return fieldSWDISIFL7.getString();
  }

  /**
  * Set numeric field SWDISIFL7 using a BigDecimal value.
  */
  public void setSWDISIFL7(BigDecimal newvalue)
  {
    fieldSWDISIFL7.setBigDecimal(newvalue);
  }

  /**
  * Return value of numeric field SWDISIFL7 as a BigDecimal.
  */
  public BigDecimal getBigDecimalSWDISIFL7()
  {
    return fieldSWDISIFL7.getBigDecimal();
  }

  /**
  * Set field SWDISIFL8 using a String value.
  */
  public void setSWDISIFL8(String newvalue)
  {
    fieldSWDISIFL8.setString(newvalue);
  }

  /**
  * Get value of field SWDISIFL8 as a String.
  */
  public String getSWDISIFL8()
  {
    return fieldSWDISIFL8.getString();
  }

  /**
  * Set numeric field SWDISIFL8 using a BigDecimal value.
  */
  public void setSWDISIFL8(BigDecimal newvalue)
  {
    fieldSWDISIFL8.setBigDecimal(newvalue);
  }

  /**
  * Return value of numeric field SWDISIFL8 as a BigDecimal.
  */
  public BigDecimal getBigDecimalSWDISIFL8()
  {
    return fieldSWDISIFL8.getBigDecimal();
  }

  /**
  * Set field SWDISIFL9 using a String value.
  */
  public void setSWDISIFL9(String newvalue)
  {
    fieldSWDISIFL9.setString(newvalue);
  }

  /**
  * Get value of field SWDISIFL9 as a String.
  */
  public String getSWDISIFL9()
  {
    return fieldSWDISIFL9.getString();
  }

  /**
  * Set numeric field SWDISIFL9 using a BigDecimal value.
  */
  public void setSWDISIFL9(BigDecimal newvalue)
  {
    fieldSWDISIFL9.setBigDecimal(newvalue);
  }

  /**
  * Return value of numeric field SWDISIFL9 as a BigDecimal.
  */
  public BigDecimal getBigDecimalSWDISIFL9()
  {
    return fieldSWDISIFL9.getBigDecimal();
  }

  /**
  * Set field SWDISIRSP using a String value.
  */
  public void setSWDISIRSP(String newvalue)
  {
    fieldSWDISIRSP.setString(newvalue);
  }

  /**
  * Get value of field SWDISIRSP as a String.
  */
  public String getSWDISIRSP()
  {
    return fieldSWDISIRSP.getString();
  }

  /**
  * Set field SWDISIRMD using a String value.
  */
  public void setSWDISIRMD(String newvalue)
  {
    fieldSWDISIRMD.setString(newvalue);
  }

  /**
  * Get value of field SWDISIRMD as a String.
  */
  public String getSWDISIRMD()
  {
    return fieldSWDISIRMD.getString();
  }

}
