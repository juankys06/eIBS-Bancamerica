package datapro.eibs.beans;

import datapro.eibs.sockets.*;
import java.io.*;
import java.math.*;

import java.util.*;

/**
* Class generated by AS/400 CRTCLASS command from ESD008701 physical file definition.
* 
* File level identifier is 1121101171644.
* Record format level identifier is 3DDADD8D47992.
*/

public class ESD008701Message extends MessageRecord
{
  final static String fldnames[] = {
                                     "H01USR",
                                     "H01PGM",
                                     "H01TIM",
                                     "H01SCR",
                                     "H01OPE",
                                     "H01MAS",
                                     "H01WK1",
                                     "H01WK2",
                                     "H01WK3",
                                     "E01LN3",
                                     "E01IDN",
                                     "E01TID",
                                     "E01PID",
                                     "E01IDF",
                                     "E01TIF",
                                     "E01PIF",
                                     "E01LGT",
                                     "E01NA1",
                                     "E01NAS",
                                     "E01LN1",
                                     "E01LN2",
                                     "E01SEX",
                                     "E01BDM",
                                     "E01BDD",
                                     "E01BDY",
                                     "E01CCA",
                                     "E01CCS",
                                     "E01RNC",
                                     "E01PAS",
                                     "E01BUC",
                                     "E01OTR",
                                     "E01NA2",
                                     "E01NA4",
                                     "E01EDI",
                                     "E01NA3",
                                     "E01UC7",
                                     "E01UC8",
                                     "E01CTY",
                                     "E01EST",
                                     "E01STE",
                                     "E01HPN",
                                     "E01PHN",
                                     "E01PH1",
                                     "E01COM",
                                     "E01CO1",
                                     "E01CO2"
                                   };
  final static String tnames[] = {
                                   "H01USR",
                                   "H01PGM",
                                   "H01TIM",
                                   "H01SCR",
                                   "H01OPE",
                                   "H01MAS",
                                   "H01WK1",
                                   "H01WK2",
                                   "H01WK3",
                                   "E01LN3",
                                   "E01IDN",
                                   "E01TID",
                                   "E01PID",
                                   "E01IDF",
                                   "E01TIF",
                                   "E01PIF",
                                   "E01LGT",
                                   "E01NA1",
                                   "E01NAS",
                                   "E01LN1",
                                   "E01LN2",
                                   "E01SEX",
                                   "E01BDM",
                                   "E01BDD",
                                   "E01BDY",
                                   "E01CCA",
                                   "E01CCS",
                                   "E01RNC",
                                   "E01PAS",
                                   "E01BUC",
                                   "E01OTR",
                                   "E01NA2",
                                   "E01NA4",
                                   "E01EDI",
                                   "E01NA3",
                                   "E01UC7",
                                   "E01UC8",
                                   "E01CTY",
                                   "E01EST",
                                   "E01STE",
                                   "E01HPN",
                                   "E01PHN",
                                   "E01PH1",
                                   "E01COM",
                                   "E01CO1",
                                   "E01CO2"
                                 };
  final static String fid = "1121101171644";
  final static String rid = "3DDADD8D47992";
  final static String fmtname = "ESD008701";
  final int FIELDCOUNT = 46;
  private static Hashtable tlookup = new Hashtable();
  private CharacterField fieldH01USR = null;
  private CharacterField fieldH01PGM = null;
  private CharacterField fieldH01TIM = null;
  private CharacterField fieldH01SCR = null;
  private CharacterField fieldH01OPE = null;
  private CharacterField fieldH01MAS = null;
  private CharacterField fieldH01WK1 = null;
  private CharacterField fieldH01WK2 = null;
  private CharacterField fieldH01WK3 = null;
  private CharacterField fieldE01LN3 = null;
  private CharacterField fieldE01IDN = null;
  private CharacterField fieldE01TID = null;
  private CharacterField fieldE01PID = null;
  private CharacterField fieldE01IDF = null;
  private CharacterField fieldE01TIF = null;
  private CharacterField fieldE01PIF = null;
  private CharacterField fieldE01LGT = null;
  private CharacterField fieldE01NA1 = null;
  private CharacterField fieldE01NAS = null;
  private CharacterField fieldE01LN1 = null;
  private CharacterField fieldE01LN2 = null;
  private CharacterField fieldE01SEX = null;
  private DecimalField fieldE01BDM = null;
  private DecimalField fieldE01BDD = null;
  private DecimalField fieldE01BDY = null;
  private CharacterField fieldE01CCA = null;
  private CharacterField fieldE01CCS = null;
  private CharacterField fieldE01RNC = null;
  private CharacterField fieldE01PAS = null;
  private CharacterField fieldE01BUC = null;
  private CharacterField fieldE01OTR = null;
  private CharacterField fieldE01NA2 = null;
  private CharacterField fieldE01NA4 = null;
  private CharacterField fieldE01EDI = null;
  private CharacterField fieldE01NA3 = null;
  private CharacterField fieldE01UC7 = null;
  private CharacterField fieldE01UC8 = null;
  private CharacterField fieldE01CTY = null;
  private CharacterField fieldE01EST = null;
  private CharacterField fieldE01STE = null;
  private DecimalField fieldE01HPN = null;
  private DecimalField fieldE01PHN = null;
  private DecimalField fieldE01PH1 = null;
  private CharacterField fieldE01COM = null;
  private CharacterField fieldE01CO1 = null;
  private CharacterField fieldE01CO2 = null;

  /**
  * Constructor for ESD008701Message.
  */
  public ESD008701Message()
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
    recordsize = 691;
    fileid = fid;
    recordid = rid;
    message = new byte[getByteLength()];
    formatname = fmtname;
    fieldnames = fldnames;
    tagnames = tnames;
    fields = new MessageField[FIELDCOUNT];

    fields[0] = fieldH01USR =
    new CharacterField(message, HEADERSIZE + 0, 10, "H01USR");
    fields[1] = fieldH01PGM =
    new CharacterField(message, HEADERSIZE + 10, 10, "H01PGM");
    fields[2] = fieldH01TIM =
    new CharacterField(message, HEADERSIZE + 20, 12, "H01TIM");
    fields[3] = fieldH01SCR =
    new CharacterField(message, HEADERSIZE + 32, 2, "H01SCR");
    fields[4] = fieldH01OPE =
    new CharacterField(message, HEADERSIZE + 34, 4, "H01OPE");
    fields[5] = fieldH01MAS =
    new CharacterField(message, HEADERSIZE + 38, 1, "H01MAS");
    fields[6] = fieldH01WK1 =
    new CharacterField(message, HEADERSIZE + 39, 1, "H01WK1");
    fields[7] = fieldH01WK2 =
    new CharacterField(message, HEADERSIZE + 40, 1, "H01WK2");
    fields[8] = fieldH01WK3 =
    new CharacterField(message, HEADERSIZE + 41, 1, "H01WK3");
    fields[9] = fieldE01LN3 =
    new CharacterField(message, HEADERSIZE + 42, 30, "E01LN3");
    fields[10] = fieldE01IDN =
    new CharacterField(message, HEADERSIZE + 72, 15, "E01IDN");
    fields[11] = fieldE01TID =
    new CharacterField(message, HEADERSIZE + 87, 4, "E01TID");
    fields[12] = fieldE01PID =
    new CharacterField(message, HEADERSIZE + 91, 4, "E01PID");
    fields[13] = fieldE01IDF =
    new CharacterField(message, HEADERSIZE + 95, 15, "E01IDF");
    fields[14] = fieldE01TIF =
    new CharacterField(message, HEADERSIZE + 110, 4, "E01TIF");
    fields[15] = fieldE01PIF =
    new CharacterField(message, HEADERSIZE + 114, 4, "E01PIF");
    fields[16] = fieldE01LGT =
    new CharacterField(message, HEADERSIZE + 118, 1, "E01LGT");
    fields[17] = fieldE01NA1 =
    new CharacterField(message, HEADERSIZE + 119, 30, "E01NA1");
    fields[18] = fieldE01NAS =
    new CharacterField(message, HEADERSIZE + 149, 30, "E01NAS");
    fields[19] = fieldE01LN1 =
    new CharacterField(message, HEADERSIZE + 179, 30, "E01LN1");
    fields[20] = fieldE01LN2 =
    new CharacterField(message, HEADERSIZE + 209, 30, "E01LN2");
    fields[21] = fieldE01SEX =
    new CharacterField(message, HEADERSIZE + 239, 1, "E01SEX");
    fields[22] = fieldE01BDM =
    new DecimalField(message, HEADERSIZE + 240, 3, 0, "E01BDM");
    fields[23] = fieldE01BDD =
    new DecimalField(message, HEADERSIZE + 243, 3, 0, "E01BDD");
    fields[24] = fieldE01BDY =
    new DecimalField(message, HEADERSIZE + 246, 5, 0, "E01BDY");
    fields[25] = fieldE01CCA =
    new CharacterField(message, HEADERSIZE + 251, 4, "E01CCA");
    fields[26] = fieldE01CCS =
    new CharacterField(message, HEADERSIZE + 255, 4, "E01CCS");
    fields[27] = fieldE01RNC =
    new CharacterField(message, HEADERSIZE + 259, 30, "E01RNC");
    fields[28] = fieldE01PAS =
    new CharacterField(message, HEADERSIZE + 289, 15, "E01PAS");
    fields[29] = fieldE01BUC =
    new CharacterField(message, HEADERSIZE + 304, 4, "E01BUC");
    fields[30] = fieldE01OTR =
    new CharacterField(message, HEADERSIZE + 308, 30, "E01OTR");
    fields[31] = fieldE01NA2 =
    new CharacterField(message, HEADERSIZE + 338, 35, "E01NA2");
    fields[32] = fieldE01NA4 =
    new CharacterField(message, HEADERSIZE + 373, 35, "E01NA4");
    fields[33] = fieldE01EDI =
    new CharacterField(message, HEADERSIZE + 408, 10, "E01EDI");
    fields[34] = fieldE01NA3 =
    new CharacterField(message, HEADERSIZE + 418, 30, "E01NA3");
    fields[35] = fieldE01UC7 =
    new CharacterField(message, HEADERSIZE + 448, 4, "E01UC7");
    fields[36] = fieldE01UC8 =
    new CharacterField(message, HEADERSIZE + 452, 4, "E01UC8");
    fields[37] = fieldE01CTY =
    new CharacterField(message, HEADERSIZE + 456, 30, "E01CTY");
    fields[38] = fieldE01EST =
    new CharacterField(message, HEADERSIZE + 486, 30, "E01EST");
    fields[39] = fieldE01STE =
    new CharacterField(message, HEADERSIZE + 516, 4, "E01STE");
    fields[40] = fieldE01HPN =
    new DecimalField(message, HEADERSIZE + 520, 12, 0, "E01HPN");
    fields[41] = fieldE01PHN =
    new DecimalField(message, HEADERSIZE + 532, 12, 0, "E01PHN");
    fields[42] = fieldE01PH1 =
    new DecimalField(message, HEADERSIZE + 544, 12, 0, "E01PH1");
    fields[43] = fieldE01COM =
    new CharacterField(message, HEADERSIZE + 556, 45, "E01COM");
    fields[44] = fieldE01CO1 =
    new CharacterField(message, HEADERSIZE + 601, 45, "E01CO1");
    fields[45] = fieldE01CO2 =
    new CharacterField(message, HEADERSIZE + 646, 45, "E01CO2");

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
  * Set field H01USR using a String value.
  */
  public void setH01USR(String newvalue)
  {
    fieldH01USR.setString(newvalue);
  }

  /**
  * Get value of field H01USR as a String.
  */
  public String getH01USR()
  {
    return fieldH01USR.getString();
  }

  /**
  * Set field H01PGM using a String value.
  */
  public void setH01PGM(String newvalue)
  {
    fieldH01PGM.setString(newvalue);
  }

  /**
  * Get value of field H01PGM as a String.
  */
  public String getH01PGM()
  {
    return fieldH01PGM.getString();
  }

  /**
  * Set field H01TIM using a String value.
  */
  public void setH01TIM(String newvalue)
  {
    fieldH01TIM.setString(newvalue);
  }

  /**
  * Get value of field H01TIM as a String.
  */
  public String getH01TIM()
  {
    return fieldH01TIM.getString();
  }

  /**
  * Set field H01SCR using a String value.
  */
  public void setH01SCR(String newvalue)
  {
    fieldH01SCR.setString(newvalue);
  }

  /**
  * Get value of field H01SCR as a String.
  */
  public String getH01SCR()
  {
    return fieldH01SCR.getString();
  }

  /**
  * Set field H01OPE using a String value.
  */
  public void setH01OPE(String newvalue)
  {
    fieldH01OPE.setString(newvalue);
  }

  /**
  * Get value of field H01OPE as a String.
  */
  public String getH01OPE()
  {
    return fieldH01OPE.getString();
  }

  /**
  * Set field H01MAS using a String value.
  */
  public void setH01MAS(String newvalue)
  {
    fieldH01MAS.setString(newvalue);
  }

  /**
  * Get value of field H01MAS as a String.
  */
  public String getH01MAS()
  {
    return fieldH01MAS.getString();
  }

  /**
  * Set field H01WK1 using a String value.
  */
  public void setH01WK1(String newvalue)
  {
    fieldH01WK1.setString(newvalue);
  }

  /**
  * Get value of field H01WK1 as a String.
  */
  public String getH01WK1()
  {
    return fieldH01WK1.getString();
  }

  /**
  * Set field H01WK2 using a String value.
  */
  public void setH01WK2(String newvalue)
  {
    fieldH01WK2.setString(newvalue);
  }

  /**
  * Get value of field H01WK2 as a String.
  */
  public String getH01WK2()
  {
    return fieldH01WK2.getString();
  }

  /**
  * Set field H01WK3 using a String value.
  */
  public void setH01WK3(String newvalue)
  {
    fieldH01WK3.setString(newvalue);
  }

  /**
  * Get value of field H01WK3 as a String.
  */
  public String getH01WK3()
  {
    return fieldH01WK3.getString();
  }

  /**
  * Set field E01LN3 using a String value.
  */
  public void setE01LN3(String newvalue)
  {
    fieldE01LN3.setString(newvalue);
  }

  /**
  * Get value of field E01LN3 as a String.
  */
  public String getE01LN3()
  {
    return fieldE01LN3.getString();
  }

  /**
  * Set field E01IDN using a String value.
  */
  public void setE01IDN(String newvalue)
  {
    fieldE01IDN.setString(newvalue);
  }

  /**
  * Get value of field E01IDN as a String.
  */
  public String getE01IDN()
  {
    return fieldE01IDN.getString();
  }

  /**
  * Set field E01TID using a String value.
  */
  public void setE01TID(String newvalue)
  {
    fieldE01TID.setString(newvalue);
  }

  /**
  * Get value of field E01TID as a String.
  */
  public String getE01TID()
  {
    return fieldE01TID.getString();
  }

  /**
  * Set field E01PID using a String value.
  */
  public void setE01PID(String newvalue)
  {
    fieldE01PID.setString(newvalue);
  }

  /**
  * Get value of field E01PID as a String.
  */
  public String getE01PID()
  {
    return fieldE01PID.getString();
  }

  /**
  * Set field E01IDF using a String value.
  */
  public void setE01IDF(String newvalue)
  {
    fieldE01IDF.setString(newvalue);
  }

  /**
  * Get value of field E01IDF as a String.
  */
  public String getE01IDF()
  {
    return fieldE01IDF.getString();
  }

  /**
  * Set field E01TIF using a String value.
  */
  public void setE01TIF(String newvalue)
  {
    fieldE01TIF.setString(newvalue);
  }

  /**
  * Get value of field E01TIF as a String.
  */
  public String getE01TIF()
  {
    return fieldE01TIF.getString();
  }

  /**
  * Set field E01PIF using a String value.
  */
  public void setE01PIF(String newvalue)
  {
    fieldE01PIF.setString(newvalue);
  }

  /**
  * Get value of field E01PIF as a String.
  */
  public String getE01PIF()
  {
    return fieldE01PIF.getString();
  }

  /**
  * Set field E01LGT using a String value.
  */
  public void setE01LGT(String newvalue)
  {
    fieldE01LGT.setString(newvalue);
  }

  /**
  * Get value of field E01LGT as a String.
  */
  public String getE01LGT()
  {
    return fieldE01LGT.getString();
  }

  /**
  * Set field E01NA1 using a String value.
  */
  public void setE01NA1(String newvalue)
  {
    fieldE01NA1.setString(newvalue);
  }

  /**
  * Get value of field E01NA1 as a String.
  */
  public String getE01NA1()
  {
    return fieldE01NA1.getString();
  }

  /**
  * Set field E01NAS using a String value.
  */
  public void setE01NAS(String newvalue)
  {
    fieldE01NAS.setString(newvalue);
  }

  /**
  * Get value of field E01NAS as a String.
  */
  public String getE01NAS()
  {
    return fieldE01NAS.getString();
  }

  /**
  * Set field E01LN1 using a String value.
  */
  public void setE01LN1(String newvalue)
  {
    fieldE01LN1.setString(newvalue);
  }

  /**
  * Get value of field E01LN1 as a String.
  */
  public String getE01LN1()
  {
    return fieldE01LN1.getString();
  }

  /**
  * Set field E01LN2 using a String value.
  */
  public void setE01LN2(String newvalue)
  {
    fieldE01LN2.setString(newvalue);
  }

  /**
  * Get value of field E01LN2 as a String.
  */
  public String getE01LN2()
  {
    return fieldE01LN2.getString();
  }

  /**
  * Set field E01SEX using a String value.
  */
  public void setE01SEX(String newvalue)
  {
    fieldE01SEX.setString(newvalue);
  }

  /**
  * Get value of field E01SEX as a String.
  */
  public String getE01SEX()
  {
    return fieldE01SEX.getString();
  }

  /**
  * Set field E01BDM using a String value.
  */
  public void setE01BDM(String newvalue)
  {
    fieldE01BDM.setString(newvalue);
  }

  /**
  * Get value of field E01BDM as a String.
  */
  public String getE01BDM()
  {
    return fieldE01BDM.getString();
  }

  /**
  * Set numeric field E01BDM using a BigDecimal value.
  */
  public void setE01BDM(BigDecimal newvalue)
  {
    fieldE01BDM.setBigDecimal(newvalue);
  }

  /**
  * Return value of numeric field E01BDM as a BigDecimal.
  */
  public BigDecimal getBigDecimalE01BDM()
  {
    return fieldE01BDM.getBigDecimal();
  }

  /**
  * Set field E01BDD using a String value.
  */
  public void setE01BDD(String newvalue)
  {
    fieldE01BDD.setString(newvalue);
  }

  /**
  * Get value of field E01BDD as a String.
  */
  public String getE01BDD()
  {
    return fieldE01BDD.getString();
  }

  /**
  * Set numeric field E01BDD using a BigDecimal value.
  */
  public void setE01BDD(BigDecimal newvalue)
  {
    fieldE01BDD.setBigDecimal(newvalue);
  }

  /**
  * Return value of numeric field E01BDD as a BigDecimal.
  */
  public BigDecimal getBigDecimalE01BDD()
  {
    return fieldE01BDD.getBigDecimal();
  }

  /**
  * Set field E01BDY using a String value.
  */
  public void setE01BDY(String newvalue)
  {
    fieldE01BDY.setString(newvalue);
  }

  /**
  * Get value of field E01BDY as a String.
  */
  public String getE01BDY()
  {
    return fieldE01BDY.getString();
  }

  /**
  * Set numeric field E01BDY using a BigDecimal value.
  */
  public void setE01BDY(BigDecimal newvalue)
  {
    fieldE01BDY.setBigDecimal(newvalue);
  }

  /**
  * Return value of numeric field E01BDY as a BigDecimal.
  */
  public BigDecimal getBigDecimalE01BDY()
  {
    return fieldE01BDY.getBigDecimal();
  }

  /**
  * Set field E01CCA using a String value.
  */
  public void setE01CCA(String newvalue)
  {
    fieldE01CCA.setString(newvalue);
  }

  /**
  * Get value of field E01CCA as a String.
  */
  public String getE01CCA()
  {
    return fieldE01CCA.getString();
  }

  /**
  * Set field E01CCS using a String value.
  */
  public void setE01CCS(String newvalue)
  {
    fieldE01CCS.setString(newvalue);
  }

  /**
  * Get value of field E01CCS as a String.
  */
  public String getE01CCS()
  {
    return fieldE01CCS.getString();
  }

  /**
  * Set field E01RNC using a String value.
  */
  public void setE01RNC(String newvalue)
  {
    fieldE01RNC.setString(newvalue);
  }

  /**
  * Get value of field E01RNC as a String.
  */
  public String getE01RNC()
  {
    return fieldE01RNC.getString();
  }

  /**
  * Set field E01PAS using a String value.
  */
  public void setE01PAS(String newvalue)
  {
    fieldE01PAS.setString(newvalue);
  }

  /**
  * Get value of field E01PAS as a String.
  */
  public String getE01PAS()
  {
    return fieldE01PAS.getString();
  }

  /**
  * Set field E01BUC using a String value.
  */
  public void setE01BUC(String newvalue)
  {
    fieldE01BUC.setString(newvalue);
  }

  /**
  * Get value of field E01BUC as a String.
  */
  public String getE01BUC()
  {
    return fieldE01BUC.getString();
  }

  /**
  * Set field E01OTR using a String value.
  */
  public void setE01OTR(String newvalue)
  {
    fieldE01OTR.setString(newvalue);
  }

  /**
  * Get value of field E01OTR as a String.
  */
  public String getE01OTR()
  {
    return fieldE01OTR.getString();
  }

  /**
  * Set field E01NA2 using a String value.
  */
  public void setE01NA2(String newvalue)
  {
    fieldE01NA2.setString(newvalue);
  }

  /**
  * Get value of field E01NA2 as a String.
  */
  public String getE01NA2()
  {
    return fieldE01NA2.getString();
  }

  /**
  * Set field E01NA4 using a String value.
  */
  public void setE01NA4(String newvalue)
  {
    fieldE01NA4.setString(newvalue);
  }

  /**
  * Get value of field E01NA4 as a String.
  */
  public String getE01NA4()
  {
    return fieldE01NA4.getString();
  }

  /**
  * Set field E01EDI using a String value.
  */
  public void setE01EDI(String newvalue)
  {
    fieldE01EDI.setString(newvalue);
  }

  /**
  * Get value of field E01EDI as a String.
  */
  public String getE01EDI()
  {
    return fieldE01EDI.getString();
  }

  /**
  * Set field E01NA3 using a String value.
  */
  public void setE01NA3(String newvalue)
  {
    fieldE01NA3.setString(newvalue);
  }

  /**
  * Get value of field E01NA3 as a String.
  */
  public String getE01NA3()
  {
    return fieldE01NA3.getString();
  }

  /**
  * Set field E01UC7 using a String value.
  */
  public void setE01UC7(String newvalue)
  {
    fieldE01UC7.setString(newvalue);
  }

  /**
  * Get value of field E01UC7 as a String.
  */
  public String getE01UC7()
  {
    return fieldE01UC7.getString();
  }

  /**
  * Set field E01UC8 using a String value.
  */
  public void setE01UC8(String newvalue)
  {
    fieldE01UC8.setString(newvalue);
  }

  /**
  * Get value of field E01UC8 as a String.
  */
  public String getE01UC8()
  {
    return fieldE01UC8.getString();
  }

  /**
  * Set field E01CTY using a String value.
  */
  public void setE01CTY(String newvalue)
  {
    fieldE01CTY.setString(newvalue);
  }

  /**
  * Get value of field E01CTY as a String.
  */
  public String getE01CTY()
  {
    return fieldE01CTY.getString();
  }

  /**
  * Set field E01EST using a String value.
  */
  public void setE01EST(String newvalue)
  {
    fieldE01EST.setString(newvalue);
  }

  /**
  * Get value of field E01EST as a String.
  */
  public String getE01EST()
  {
    return fieldE01EST.getString();
  }

  /**
  * Set field E01STE using a String value.
  */
  public void setE01STE(String newvalue)
  {
    fieldE01STE.setString(newvalue);
  }

  /**
  * Get value of field E01STE as a String.
  */
  public String getE01STE()
  {
    return fieldE01STE.getString();
  }

  /**
  * Set field E01HPN using a String value.
  */
  public void setE01HPN(String newvalue)
  {
    fieldE01HPN.setString(newvalue);
  }

  /**
  * Get value of field E01HPN as a String.
  */
  public String getE01HPN()
  {
    return fieldE01HPN.getString();
  }

  /**
  * Set numeric field E01HPN using a BigDecimal value.
  */
  public void setE01HPN(BigDecimal newvalue)
  {
    fieldE01HPN.setBigDecimal(newvalue);
  }

  /**
  * Return value of numeric field E01HPN as a BigDecimal.
  */
  public BigDecimal getBigDecimalE01HPN()
  {
    return fieldE01HPN.getBigDecimal();
  }

  /**
  * Set field E01PHN using a String value.
  */
  public void setE01PHN(String newvalue)
  {
    fieldE01PHN.setString(newvalue);
  }

  /**
  * Get value of field E01PHN as a String.
  */
  public String getE01PHN()
  {
    return fieldE01PHN.getString();
  }

  /**
  * Set numeric field E01PHN using a BigDecimal value.
  */
  public void setE01PHN(BigDecimal newvalue)
  {
    fieldE01PHN.setBigDecimal(newvalue);
  }

  /**
  * Return value of numeric field E01PHN as a BigDecimal.
  */
  public BigDecimal getBigDecimalE01PHN()
  {
    return fieldE01PHN.getBigDecimal();
  }

  /**
  * Set field E01PH1 using a String value.
  */
  public void setE01PH1(String newvalue)
  {
    fieldE01PH1.setString(newvalue);
  }

  /**
  * Get value of field E01PH1 as a String.
  */
  public String getE01PH1()
  {
    return fieldE01PH1.getString();
  }

  /**
  * Set numeric field E01PH1 using a BigDecimal value.
  */
  public void setE01PH1(BigDecimal newvalue)
  {
    fieldE01PH1.setBigDecimal(newvalue);
  }

  /**
  * Return value of numeric field E01PH1 as a BigDecimal.
  */
  public BigDecimal getBigDecimalE01PH1()
  {
    return fieldE01PH1.getBigDecimal();
  }

  /**
  * Set field E01COM using a String value.
  */
  public void setE01COM(String newvalue)
  {
    fieldE01COM.setString(newvalue);
  }

  /**
  * Get value of field E01COM as a String.
  */
  public String getE01COM()
  {
    return fieldE01COM.getString();
  }

  /**
  * Set field E01CO1 using a String value.
  */
  public void setE01CO1(String newvalue)
  {
    fieldE01CO1.setString(newvalue);
  }

  /**
  * Get value of field E01CO1 as a String.
  */
  public String getE01CO1()
  {
    return fieldE01CO1.getString();
  }

  /**
  * Set field E01CO2 using a String value.
  */
  public void setE01CO2(String newvalue)
  {
    fieldE01CO2.setString(newvalue);
  }

  /**
  * Get value of field E01CO2 as a String.
  */
  public String getE01CO2()
  {
    return fieldE01CO2.getString();
  }

}
