package datapro.eibs.beans;

import datapro.eibs.sockets.*;
import java.io.*;
import java.math.*;

import java.util.*;

/**
* Class generated by AS/400 CRTCLASS command from EWD0026DS physical file definition.
* 
* File level identifier is 1030121192020.
* Record format level identifier is 59228EA584268.
*/

public class EWD0026DSMessage extends MessageRecord
{
  final static String fldnames[] = {
                                     "EWDACC",
                                     "EWDMAN",
                                     "EWDMA1",
                                     "EWDMA2",
                                     "EWDMA3",
                                     "EWDMA4",
                                     "EWDCTY",
                                     "EWDSTE",
                                     "EWDCTR",
                                     "EWDZPC",
                                     "EWDPOB",
                                     "EWDTID",
                                     "EWDPID",
                                     "EWDDSC",
                                     "EWDHPN",
                                     "EWDBSX",
                                     "EWDFL1",
                                     "EWDFL2",
                                     "EWDFL3",
                                     "EWDDT1",
                                     "EWDDT2",
                                     "EWDDT3",
                                     "EWDD21",
                                     "EWDD22",
                                     "EWDD23",
                                     "EWDOPE"
                                   };
  final static String tnames[] = {
                                   "EWDACC",
                                   "EWDMAN",
                                   "EWDMA1",
                                   "EWDMA2",
                                   "EWDMA3",
                                   "EWDMA4",
                                   "EWDCTY",
                                   "EWDSTE",
                                   "EWDCTR",
                                   "EWDZPC",
                                   "EWDPOB",
                                   "EWDTID",
                                   "EWDPID",
                                   "EWDDSC",
                                   "EWDHPN",
                                   "EWDBSX",
                                   "EWDFL1",
                                   "EWDFL2",
                                   "EWDFL3",
                                   "EWDDT1",
                                   "EWDDT2",
                                   "EWDDT3",
                                   "EWDD21",
                                   "EWDD22",
                                   "EWDD23",
                                   "EWDOPE"
                                 };
  final static String fid = "1030121192020";
  final static String rid = "59228EA584268";
  final static String fmtname = "EWD0026DS";
  final int FIELDCOUNT = 26;
  private static Hashtable tlookup = new Hashtable();
  private DecimalField fieldEWDACC = null;
  private CharacterField fieldEWDMAN = null;
  private CharacterField fieldEWDMA1 = null;
  private CharacterField fieldEWDMA2 = null;
  private CharacterField fieldEWDMA3 = null;
  private CharacterField fieldEWDMA4 = null;
  private CharacterField fieldEWDCTY = null;
  private CharacterField fieldEWDSTE = null;
  private CharacterField fieldEWDCTR = null;
  private CharacterField fieldEWDZPC = null;
  private CharacterField fieldEWDPOB = null;
  private CharacterField fieldEWDTID = null;
  private CharacterField fieldEWDPID = null;
  private CharacterField fieldEWDDSC = null;
  private DecimalField fieldEWDHPN = null;
  private CharacterField fieldEWDBSX = null;
  private CharacterField fieldEWDFL1 = null;
  private CharacterField fieldEWDFL2 = null;
  private CharacterField fieldEWDFL3 = null;
  private DecimalField fieldEWDDT1 = null;
  private DecimalField fieldEWDDT2 = null;
  private DecimalField fieldEWDDT3 = null;
  private DecimalField fieldEWDD21 = null;
  private DecimalField fieldEWDD22 = null;
  private DecimalField fieldEWDD23 = null;
  private CharacterField fieldEWDOPE = null;

  /**
  * Constructor for EWD0026DSMessage.
  */
  public EWD0026DSMessage()
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
    recordsize = 311;
    fileid = fid;
    recordid = rid;
    message = new byte[getByteLength()];
    formatname = fmtname;
    fieldnames = fldnames;
    tagnames = tnames;
    fields = new MessageField[FIELDCOUNT];

    fields[0] = fieldEWDACC =
    new DecimalField(message, HEADERSIZE + 0, 13, 0, "EWDACC");
    fields[1] = fieldEWDMAN =
    new CharacterField(message, HEADERSIZE + 13, 1, "EWDMAN");
    fields[2] = fieldEWDMA1 =
    new CharacterField(message, HEADERSIZE + 14, 35, "EWDMA1");
    fields[3] = fieldEWDMA2 =
    new CharacterField(message, HEADERSIZE + 49, 35, "EWDMA2");
    fields[4] = fieldEWDMA3 =
    new CharacterField(message, HEADERSIZE + 84, 35, "EWDMA3");
    fields[5] = fieldEWDMA4 =
    new CharacterField(message, HEADERSIZE + 119, 35, "EWDMA4");
    fields[6] = fieldEWDCTY =
    new CharacterField(message, HEADERSIZE + 154, 30, "EWDCTY");
    fields[7] = fieldEWDSTE =
    new CharacterField(message, HEADERSIZE + 184, 4, "EWDSTE");
    fields[8] = fieldEWDCTR =
    new CharacterField(message, HEADERSIZE + 188, 20, "EWDCTR");
    fields[9] = fieldEWDZPC =
    new CharacterField(message, HEADERSIZE + 208, 15, "EWDZPC");
    fields[10] = fieldEWDPOB =
    new CharacterField(message, HEADERSIZE + 223, 10, "EWDPOB");
    fields[11] = fieldEWDTID =
    new CharacterField(message, HEADERSIZE + 233, 4, "EWDTID");
    fields[12] = fieldEWDPID =
    new CharacterField(message, HEADERSIZE + 237, 4, "EWDPID");
    fields[13] = fieldEWDDSC =
    new CharacterField(message, HEADERSIZE + 241, 35, "EWDDSC");
    fields[14] = fieldEWDHPN =
    new DecimalField(message, HEADERSIZE + 276, 12, 0, "EWDHPN");
    fields[15] = fieldEWDBSX =
    new CharacterField(message, HEADERSIZE + 288, 1, "EWDBSX");
    fields[16] = fieldEWDFL1 =
    new CharacterField(message, HEADERSIZE + 289, 1, "EWDFL1");
    fields[17] = fieldEWDFL2 =
    new CharacterField(message, HEADERSIZE + 290, 1, "EWDFL2");
    fields[18] = fieldEWDFL3 =
    new CharacterField(message, HEADERSIZE + 291, 1, "EWDFL3");
    fields[19] = fieldEWDDT1 =
    new DecimalField(message, HEADERSIZE + 292, 3, 0, "EWDDT1");
    fields[20] = fieldEWDDT2 =
    new DecimalField(message, HEADERSIZE + 295, 3, 0, "EWDDT2");
    fields[21] = fieldEWDDT3 =
    new DecimalField(message, HEADERSIZE + 298, 3, 0, "EWDDT3");
    fields[22] = fieldEWDD21 =
    new DecimalField(message, HEADERSIZE + 301, 3, 0, "EWDD21");
    fields[23] = fieldEWDD22 =
    new DecimalField(message, HEADERSIZE + 304, 3, 0, "EWDD22");
    fields[24] = fieldEWDD23 =
    new DecimalField(message, HEADERSIZE + 307, 3, 0, "EWDD23");
    fields[25] = fieldEWDOPE =
    new CharacterField(message, HEADERSIZE + 310, 1, "EWDOPE");

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
  * Set field EWDACC using a String value.
  */
  public void setEWDACC(String newvalue)
  {
    fieldEWDACC.setString(newvalue);
  }

  /**
  * Get value of field EWDACC as a String.
  */
  public String getEWDACC()
  {
    return fieldEWDACC.getString();
  }

  /**
  * Set numeric field EWDACC using a BigDecimal value.
  */
  public void setEWDACC(BigDecimal newvalue)
  {
    fieldEWDACC.setBigDecimal(newvalue);
  }

  /**
  * Return value of numeric field EWDACC as a BigDecimal.
  */
  public BigDecimal getBigDecimalEWDACC()
  {
    return fieldEWDACC.getBigDecimal();
  }

  /**
  * Set field EWDMAN using a String value.
  */
  public void setEWDMAN(String newvalue)
  {
    fieldEWDMAN.setString(newvalue);
  }

  /**
  * Get value of field EWDMAN as a String.
  */
  public String getEWDMAN()
  {
    return fieldEWDMAN.getString();
  }

  /**
  * Set field EWDMA1 using a String value.
  */
  public void setEWDMA1(String newvalue)
  {
    fieldEWDMA1.setString(newvalue);
  }

  /**
  * Get value of field EWDMA1 as a String.
  */
  public String getEWDMA1()
  {
    return fieldEWDMA1.getString();
  }

  /**
  * Set field EWDMA2 using a String value.
  */
  public void setEWDMA2(String newvalue)
  {
    fieldEWDMA2.setString(newvalue);
  }

  /**
  * Get value of field EWDMA2 as a String.
  */
  public String getEWDMA2()
  {
    return fieldEWDMA2.getString();
  }

  /**
  * Set field EWDMA3 using a String value.
  */
  public void setEWDMA3(String newvalue)
  {
    fieldEWDMA3.setString(newvalue);
  }

  /**
  * Get value of field EWDMA3 as a String.
  */
  public String getEWDMA3()
  {
    return fieldEWDMA3.getString();
  }

  /**
  * Set field EWDMA4 using a String value.
  */
  public void setEWDMA4(String newvalue)
  {
    fieldEWDMA4.setString(newvalue);
  }

  /**
  * Get value of field EWDMA4 as a String.
  */
  public String getEWDMA4()
  {
    return fieldEWDMA4.getString();
  }

  /**
  * Set field EWDCTY using a String value.
  */
  public void setEWDCTY(String newvalue)
  {
    fieldEWDCTY.setString(newvalue);
  }

  /**
  * Get value of field EWDCTY as a String.
  */
  public String getEWDCTY()
  {
    return fieldEWDCTY.getString();
  }

  /**
  * Set field EWDSTE using a String value.
  */
  public void setEWDSTE(String newvalue)
  {
    fieldEWDSTE.setString(newvalue);
  }

  /**
  * Get value of field EWDSTE as a String.
  */
  public String getEWDSTE()
  {
    return fieldEWDSTE.getString();
  }

  /**
  * Set field EWDCTR using a String value.
  */
  public void setEWDCTR(String newvalue)
  {
    fieldEWDCTR.setString(newvalue);
  }

  /**
  * Get value of field EWDCTR as a String.
  */
  public String getEWDCTR()
  {
    return fieldEWDCTR.getString();
  }

  /**
  * Set field EWDZPC using a String value.
  */
  public void setEWDZPC(String newvalue)
  {
    fieldEWDZPC.setString(newvalue);
  }

  /**
  * Get value of field EWDZPC as a String.
  */
  public String getEWDZPC()
  {
    return fieldEWDZPC.getString();
  }

  /**
  * Set field EWDPOB using a String value.
  */
  public void setEWDPOB(String newvalue)
  {
    fieldEWDPOB.setString(newvalue);
  }

  /**
  * Get value of field EWDPOB as a String.
  */
  public String getEWDPOB()
  {
    return fieldEWDPOB.getString();
  }

  /**
  * Set field EWDTID using a String value.
  */
  public void setEWDTID(String newvalue)
  {
    fieldEWDTID.setString(newvalue);
  }

  /**
  * Get value of field EWDTID as a String.
  */
  public String getEWDTID()
  {
    return fieldEWDTID.getString();
  }

  /**
  * Set field EWDPID using a String value.
  */
  public void setEWDPID(String newvalue)
  {
    fieldEWDPID.setString(newvalue);
  }

  /**
  * Get value of field EWDPID as a String.
  */
  public String getEWDPID()
  {
    return fieldEWDPID.getString();
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
  * Set field EWDHPN using a String value.
  */
  public void setEWDHPN(String newvalue)
  {
    fieldEWDHPN.setString(newvalue);
  }

  /**
  * Get value of field EWDHPN as a String.
  */
  public String getEWDHPN()
  {
    return fieldEWDHPN.getString();
  }

  /**
  * Set numeric field EWDHPN using a BigDecimal value.
  */
  public void setEWDHPN(BigDecimal newvalue)
  {
    fieldEWDHPN.setBigDecimal(newvalue);
  }

  /**
  * Return value of numeric field EWDHPN as a BigDecimal.
  */
  public BigDecimal getBigDecimalEWDHPN()
  {
    return fieldEWDHPN.getBigDecimal();
  }

  /**
  * Set field EWDBSX using a String value.
  */
  public void setEWDBSX(String newvalue)
  {
    fieldEWDBSX.setString(newvalue);
  }

  /**
  * Get value of field EWDBSX as a String.
  */
  public String getEWDBSX()
  {
    return fieldEWDBSX.getString();
  }

  /**
  * Set field EWDFL1 using a String value.
  */
  public void setEWDFL1(String newvalue)
  {
    fieldEWDFL1.setString(newvalue);
  }

  /**
  * Get value of field EWDFL1 as a String.
  */
  public String getEWDFL1()
  {
    return fieldEWDFL1.getString();
  }

  /**
  * Set field EWDFL2 using a String value.
  */
  public void setEWDFL2(String newvalue)
  {
    fieldEWDFL2.setString(newvalue);
  }

  /**
  * Get value of field EWDFL2 as a String.
  */
  public String getEWDFL2()
  {
    return fieldEWDFL2.getString();
  }

  /**
  * Set field EWDFL3 using a String value.
  */
  public void setEWDFL3(String newvalue)
  {
    fieldEWDFL3.setString(newvalue);
  }

  /**
  * Get value of field EWDFL3 as a String.
  */
  public String getEWDFL3()
  {
    return fieldEWDFL3.getString();
  }

  /**
  * Set field EWDDT1 using a String value.
  */
  public void setEWDDT1(String newvalue)
  {
    fieldEWDDT1.setString(newvalue);
  }

  /**
  * Get value of field EWDDT1 as a String.
  */
  public String getEWDDT1()
  {
    return fieldEWDDT1.getString();
  }

  /**
  * Set numeric field EWDDT1 using a BigDecimal value.
  */
  public void setEWDDT1(BigDecimal newvalue)
  {
    fieldEWDDT1.setBigDecimal(newvalue);
  }

  /**
  * Return value of numeric field EWDDT1 as a BigDecimal.
  */
  public BigDecimal getBigDecimalEWDDT1()
  {
    return fieldEWDDT1.getBigDecimal();
  }

  /**
  * Set field EWDDT2 using a String value.
  */
  public void setEWDDT2(String newvalue)
  {
    fieldEWDDT2.setString(newvalue);
  }

  /**
  * Get value of field EWDDT2 as a String.
  */
  public String getEWDDT2()
  {
    return fieldEWDDT2.getString();
  }

  /**
  * Set numeric field EWDDT2 using a BigDecimal value.
  */
  public void setEWDDT2(BigDecimal newvalue)
  {
    fieldEWDDT2.setBigDecimal(newvalue);
  }

  /**
  * Return value of numeric field EWDDT2 as a BigDecimal.
  */
  public BigDecimal getBigDecimalEWDDT2()
  {
    return fieldEWDDT2.getBigDecimal();
  }

  /**
  * Set field EWDDT3 using a String value.
  */
  public void setEWDDT3(String newvalue)
  {
    fieldEWDDT3.setString(newvalue);
  }

  /**
  * Get value of field EWDDT3 as a String.
  */
  public String getEWDDT3()
  {
    return fieldEWDDT3.getString();
  }

  /**
  * Set numeric field EWDDT3 using a BigDecimal value.
  */
  public void setEWDDT3(BigDecimal newvalue)
  {
    fieldEWDDT3.setBigDecimal(newvalue);
  }

  /**
  * Return value of numeric field EWDDT3 as a BigDecimal.
  */
  public BigDecimal getBigDecimalEWDDT3()
  {
    return fieldEWDDT3.getBigDecimal();
  }

  /**
  * Set field EWDD21 using a String value.
  */
  public void setEWDD21(String newvalue)
  {
    fieldEWDD21.setString(newvalue);
  }

  /**
  * Get value of field EWDD21 as a String.
  */
  public String getEWDD21()
  {
    return fieldEWDD21.getString();
  }

  /**
  * Set numeric field EWDD21 using a BigDecimal value.
  */
  public void setEWDD21(BigDecimal newvalue)
  {
    fieldEWDD21.setBigDecimal(newvalue);
  }

  /**
  * Return value of numeric field EWDD21 as a BigDecimal.
  */
  public BigDecimal getBigDecimalEWDD21()
  {
    return fieldEWDD21.getBigDecimal();
  }

  /**
  * Set field EWDD22 using a String value.
  */
  public void setEWDD22(String newvalue)
  {
    fieldEWDD22.setString(newvalue);
  }

  /**
  * Get value of field EWDD22 as a String.
  */
  public String getEWDD22()
  {
    return fieldEWDD22.getString();
  }

  /**
  * Set numeric field EWDD22 using a BigDecimal value.
  */
  public void setEWDD22(BigDecimal newvalue)
  {
    fieldEWDD22.setBigDecimal(newvalue);
  }

  /**
  * Return value of numeric field EWDD22 as a BigDecimal.
  */
  public BigDecimal getBigDecimalEWDD22()
  {
    return fieldEWDD22.getBigDecimal();
  }

  /**
  * Set field EWDD23 using a String value.
  */
  public void setEWDD23(String newvalue)
  {
    fieldEWDD23.setString(newvalue);
  }

  /**
  * Get value of field EWDD23 as a String.
  */
  public String getEWDD23()
  {
    return fieldEWDD23.getString();
  }

  /**
  * Set numeric field EWDD23 using a BigDecimal value.
  */
  public void setEWDD23(BigDecimal newvalue)
  {
    fieldEWDD23.setBigDecimal(newvalue);
  }

  /**
  * Return value of numeric field EWDD23 as a BigDecimal.
  */
  public BigDecimal getBigDecimalEWDD23()
  {
    return fieldEWDD23.getBigDecimal();
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
