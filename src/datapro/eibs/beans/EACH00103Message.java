package datapro.eibs.beans;

import datapro.eibs.sockets.*;
import java.io.*;
import java.math.*;

import java.util.*;

/**
* Class generated by AS/400 CRTCLASS command from EACH00103 physical file definition.
* 
* File level identifier is 1131002085358.
* Record format level identifier is 3C2D48B03F7A5.
*/

public class EACH00103Message extends MessageRecord
{
  final static String fldnames[] = {
                                     "ACH3USERID",
                                     "ACH3PROGRM",
                                     "ACH3TIMSYS",
                                     "ACH3SCRCOD",
                                     "ACH3OPECOD",
                                     "ACH3FLGMAS",
                                     "ACH3FLGWK1",
                                     "ACH3FLGWK2",
                                     "ACH3FLGWK3",
                                     "ACH3TIPTRN",
                                     "ACH3SELFTR",
                                     "ACH3SELSEC",
                                     "ACH3SELREF",
                                     "ACH3STATUS",
                                     "ACH3FTR",
                                     "ACH3SEC",
                                     "ACH3REF",
                                     "ACH3IDA",
                                     "ACH3TTC",
                                     "ACH3NMI",
                                     "ACH3TTR",
                                     "ACH3NUC",
                                     "ACH3IDC",
                                     "ACH3TIC",
                                     "ACH3MNT",
                                     "ACH3DSC",
                                     "ACH3ADN",
                                     "ACH3CCY",
                                     "ACH3CDR",
                                     "ACH3STS",
                                     "ACH3RDP",
                                     "ACH3MOD",
                                     "ACH3APR",
                                     "ACH3REC",
                                     "ACH3ACC",
                                     "ACH3CUN",
                                     "ACH3SHN",
                                     "ACH3USR",
                                     "ACH3FCP",
                                     "ACH3HRP",
                                     "ACH3INDOPE"
                                   };
  final static String tnames[] = {
                                   "ACH3USERID",
                                   "ACH3PROGRM",
                                   "ACH3TIMSYS",
                                   "ACH3SCRCOD",
                                   "ACH3OPECOD",
                                   "ACH3FLGMAS",
                                   "ACH3FLGWK1",
                                   "ACH3FLGWK2",
                                   "ACH3FLGWK3",
                                   "ACH3TIPTRN",
                                   "ACH3SELFTR",
                                   "ACH3SELSEC",
                                   "ACH3SELREF",
                                   "ACH3STATUS",
                                   "ACH3FTR",
                                   "ACH3SEC",
                                   "ACH3REF",
                                   "ACH3IDA",
                                   "ACH3TTC",
                                   "ACH3NMI",
                                   "ACH3TTR",
                                   "ACH3NUC",
                                   "ACH3IDC",
                                   "ACH3TIC",
                                   "ACH3MNT",
                                   "ACH3DSC",
                                   "ACH3ADN",
                                   "ACH3CCY",
                                   "ACH3CDR",
                                   "ACH3STS",
                                   "ACH3RDP",
                                   "ACH3MOD",
                                   "ACH3APR",
                                   "ACH3REC",
                                   "ACH3ACC",
                                   "ACH3CUN",
                                   "ACH3SHN",
                                   "ACH3USR",
                                   "ACH3FCP",
                                   "ACH3HRP",
                                   "ACH3INDOPE"
                                 };
  final static String fid = "1131002085358";
  final static String rid = "3C2D48B03F7A5";
  final static String fmtname = "EACH00103";
  final int FIELDCOUNT = 41;
  private static Hashtable tlookup = new Hashtable();
  private CharacterField fieldACH3USERID = null;
  private CharacterField fieldACH3PROGRM = null;
  private CharacterField fieldACH3TIMSYS = null;
  private CharacterField fieldACH3SCRCOD = null;
  private CharacterField fieldACH3OPECOD = null;
  private CharacterField fieldACH3FLGMAS = null;
  private CharacterField fieldACH3FLGWK1 = null;
  private CharacterField fieldACH3FLGWK2 = null;
  private CharacterField fieldACH3FLGWK3 = null;
  private CharacterField fieldACH3TIPTRN = null;
  private CharacterField fieldACH3SELFTR = null;
  private DecimalField fieldACH3SELSEC = null;
  private CharacterField fieldACH3SELREF = null;
  private CharacterField fieldACH3STATUS = null;
  private CharacterField fieldACH3FTR = null;
  private DecimalField fieldACH3SEC = null;
  private CharacterField fieldACH3REF = null;
  private CharacterField fieldACH3IDA = null;
  private DecimalField fieldACH3TTC = null;
  private CharacterField fieldACH3NMI = null;
  private CharacterField fieldACH3TTR = null;
  private CharacterField fieldACH3NUC = null;
  private CharacterField fieldACH3IDC = null;
  private CharacterField fieldACH3TIC = null;
  private DecimalField fieldACH3MNT = null;
  private CharacterField fieldACH3DSC = null;
  private CharacterField fieldACH3ADN = null;
  private CharacterField fieldACH3CCY = null;
  private CharacterField fieldACH3CDR = null;
  private CharacterField fieldACH3STS = null;
  private CharacterField fieldACH3RDP = null;
  private CharacterField fieldACH3MOD = null;
  private CharacterField fieldACH3APR = null;
  private CharacterField fieldACH3REC = null;
  private DecimalField fieldACH3ACC = null;
  private DecimalField fieldACH3CUN = null;
  private CharacterField fieldACH3SHN = null;
  private CharacterField fieldACH3USR = null;
  private DecimalField fieldACH3FCP = null;
  private DecimalField fieldACH3HRP = null;
  private CharacterField fieldACH3INDOPE = null;

  /**
  * Constructor for EACH00103Message.
  */
  public EACH00103Message()
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
    recordsize = 451;
    fileid = fid;
    recordid = rid;
    message = new byte[getByteLength()];
    formatname = fmtname;
    fieldnames = fldnames;
    tagnames = tnames;
    fields = new MessageField[FIELDCOUNT];

    fields[0] = fieldACH3USERID =
    new CharacterField(message, HEADERSIZE + 0, 10, "ACH3USERID");
    fields[1] = fieldACH3PROGRM =
    new CharacterField(message, HEADERSIZE + 10, 10, "ACH3PROGRM");
    fields[2] = fieldACH3TIMSYS =
    new CharacterField(message, HEADERSIZE + 20, 12, "ACH3TIMSYS");
    fields[3] = fieldACH3SCRCOD =
    new CharacterField(message, HEADERSIZE + 32, 2, "ACH3SCRCOD");
    fields[4] = fieldACH3OPECOD =
    new CharacterField(message, HEADERSIZE + 34, 4, "ACH3OPECOD");
    fields[5] = fieldACH3FLGMAS =
    new CharacterField(message, HEADERSIZE + 38, 1, "ACH3FLGMAS");
    fields[6] = fieldACH3FLGWK1 =
    new CharacterField(message, HEADERSIZE + 39, 1, "ACH3FLGWK1");
    fields[7] = fieldACH3FLGWK2 =
    new CharacterField(message, HEADERSIZE + 40, 1, "ACH3FLGWK2");
    fields[8] = fieldACH3FLGWK3 =
    new CharacterField(message, HEADERSIZE + 41, 1, "ACH3FLGWK3");
    fields[9] = fieldACH3TIPTRN =
    new CharacterField(message, HEADERSIZE + 42, 3, "ACH3TIPTRN");
    fields[10] = fieldACH3SELFTR =
    new CharacterField(message, HEADERSIZE + 45, 10, "ACH3SELFTR");
    fields[11] = fieldACH3SELSEC =
    new DecimalField(message, HEADERSIZE + 55, 7, 0, "ACH3SELSEC");
    fields[12] = fieldACH3SELREF =
    new CharacterField(message, HEADERSIZE + 62, 15, "ACH3SELREF");
    fields[13] = fieldACH3STATUS =
    new CharacterField(message, HEADERSIZE + 77, 1, "ACH3STATUS");
    fields[14] = fieldACH3FTR =
    new CharacterField(message, HEADERSIZE + 78, 10, "ACH3FTR");
    fields[15] = fieldACH3SEC =
    new DecimalField(message, HEADERSIZE + 88, 7, 0, "ACH3SEC");
    fields[16] = fieldACH3REF =
    new CharacterField(message, HEADERSIZE + 95, 15, "ACH3REF");
    fields[17] = fieldACH3IDA =
    new CharacterField(message, HEADERSIZE + 110, 1, "ACH3IDA");
    fields[18] = fieldACH3TTC =
    new DecimalField(message, HEADERSIZE + 111, 11, 0, "ACH3TTC");
    fields[19] = fieldACH3NMI =
    new CharacterField(message, HEADERSIZE + 122, 10, "ACH3NMI");
    fields[20] = fieldACH3TTR =
    new CharacterField(message, HEADERSIZE + 132, 2, "ACH3TTR");
    fields[21] = fieldACH3NUC =
    new CharacterField(message, HEADERSIZE + 134, 19, "ACH3NUC");
    fields[22] = fieldACH3IDC =
    new CharacterField(message, HEADERSIZE + 153, 15, "ACH3IDC");
    fields[23] = fieldACH3TIC =
    new CharacterField(message, HEADERSIZE + 168, 2, "ACH3TIC");
    fields[24] = fieldACH3MNT =
    new DecimalField(message, HEADERSIZE + 170, 14, 2, "ACH3MNT");
    fields[25] = fieldACH3DSC =
    new CharacterField(message, HEADERSIZE + 184, 80, "ACH3DSC");
    fields[26] = fieldACH3ADN =
    new CharacterField(message, HEADERSIZE + 264, 80, "ACH3ADN");
    fields[27] = fieldACH3CCY =
    new CharacterField(message, HEADERSIZE + 344, 3, "ACH3CCY");
    fields[28] = fieldACH3CDR =
    new CharacterField(message, HEADERSIZE + 347, 2, "ACH3CDR");
    fields[29] = fieldACH3STS =
    new CharacterField(message, HEADERSIZE + 349, 1, "ACH3STS");
    fields[30] = fieldACH3RDP =
    new CharacterField(message, HEADERSIZE + 350, 3, "ACH3RDP");
    fields[31] = fieldACH3MOD =
    new CharacterField(message, HEADERSIZE + 353, 1, "ACH3MOD");
    fields[32] = fieldACH3APR =
    new CharacterField(message, HEADERSIZE + 354, 1, "ACH3APR");
    fields[33] = fieldACH3REC =
    new CharacterField(message, HEADERSIZE + 355, 1, "ACH3REC");
    fields[34] = fieldACH3ACC =
    new DecimalField(message, HEADERSIZE + 356, 13, 0, "ACH3ACC");
    fields[35] = fieldACH3CUN =
    new DecimalField(message, HEADERSIZE + 369, 10, 0, "ACH3CUN");
    fields[36] = fieldACH3SHN =
    new CharacterField(message, HEADERSIZE + 379, 45, "ACH3SHN");
    fields[37] = fieldACH3USR =
    new CharacterField(message, HEADERSIZE + 424, 10, "ACH3USR");
    fields[38] = fieldACH3FCP =
    new DecimalField(message, HEADERSIZE + 434, 9, 0, "ACH3FCP");
    fields[39] = fieldACH3HRP =
    new DecimalField(message, HEADERSIZE + 443, 7, 0, "ACH3HRP");
    fields[40] = fieldACH3INDOPE =
    new CharacterField(message, HEADERSIZE + 450, 1, "ACH3INDOPE");

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
  * Set field ACH3USERID using a String value.
  */
  public void setACH3USERID(String newvalue)
  {
    fieldACH3USERID.setString(newvalue);
  }

  /**
  * Get value of field ACH3USERID as a String.
  */
  public String getACH3USERID()
  {
    return fieldACH3USERID.getString();
  }

  /**
  * Set field ACH3PROGRM using a String value.
  */
  public void setACH3PROGRM(String newvalue)
  {
    fieldACH3PROGRM.setString(newvalue);
  }

  /**
  * Get value of field ACH3PROGRM as a String.
  */
  public String getACH3PROGRM()
  {
    return fieldACH3PROGRM.getString();
  }

  /**
  * Set field ACH3TIMSYS using a String value.
  */
  public void setACH3TIMSYS(String newvalue)
  {
    fieldACH3TIMSYS.setString(newvalue);
  }

  /**
  * Get value of field ACH3TIMSYS as a String.
  */
  public String getACH3TIMSYS()
  {
    return fieldACH3TIMSYS.getString();
  }

  /**
  * Set field ACH3SCRCOD using a String value.
  */
  public void setACH3SCRCOD(String newvalue)
  {
    fieldACH3SCRCOD.setString(newvalue);
  }

  /**
  * Get value of field ACH3SCRCOD as a String.
  */
  public String getACH3SCRCOD()
  {
    return fieldACH3SCRCOD.getString();
  }

  /**
  * Set field ACH3OPECOD using a String value.
  */
  public void setACH3OPECOD(String newvalue)
  {
    fieldACH3OPECOD.setString(newvalue);
  }

  /**
  * Get value of field ACH3OPECOD as a String.
  */
  public String getACH3OPECOD()
  {
    return fieldACH3OPECOD.getString();
  }

  /**
  * Set field ACH3FLGMAS using a String value.
  */
  public void setACH3FLGMAS(String newvalue)
  {
    fieldACH3FLGMAS.setString(newvalue);
  }

  /**
  * Get value of field ACH3FLGMAS as a String.
  */
  public String getACH3FLGMAS()
  {
    return fieldACH3FLGMAS.getString();
  }

  /**
  * Set field ACH3FLGWK1 using a String value.
  */
  public void setACH3FLGWK1(String newvalue)
  {
    fieldACH3FLGWK1.setString(newvalue);
  }

  /**
  * Get value of field ACH3FLGWK1 as a String.
  */
  public String getACH3FLGWK1()
  {
    return fieldACH3FLGWK1.getString();
  }

  /**
  * Set field ACH3FLGWK2 using a String value.
  */
  public void setACH3FLGWK2(String newvalue)
  {
    fieldACH3FLGWK2.setString(newvalue);
  }

  /**
  * Get value of field ACH3FLGWK2 as a String.
  */
  public String getACH3FLGWK2()
  {
    return fieldACH3FLGWK2.getString();
  }

  /**
  * Set field ACH3FLGWK3 using a String value.
  */
  public void setACH3FLGWK3(String newvalue)
  {
    fieldACH3FLGWK3.setString(newvalue);
  }

  /**
  * Get value of field ACH3FLGWK3 as a String.
  */
  public String getACH3FLGWK3()
  {
    return fieldACH3FLGWK3.getString();
  }

  /**
  * Set field ACH3TIPTRN using a String value.
  */
  public void setACH3TIPTRN(String newvalue)
  {
    fieldACH3TIPTRN.setString(newvalue);
  }

  /**
  * Get value of field ACH3TIPTRN as a String.
  */
  public String getACH3TIPTRN()
  {
    return fieldACH3TIPTRN.getString();
  }

  /**
  * Set field ACH3SELFTR using a String value.
  */
  public void setACH3SELFTR(String newvalue)
  {
    fieldACH3SELFTR.setString(newvalue);
  }

  /**
  * Get value of field ACH3SELFTR as a String.
  */
  public String getACH3SELFTR()
  {
    return fieldACH3SELFTR.getString();
  }

  /**
  * Set field ACH3SELSEC using a String value.
  */
  public void setACH3SELSEC(String newvalue)
  {
    fieldACH3SELSEC.setString(newvalue);
  }

  /**
  * Get value of field ACH3SELSEC as a String.
  */
  public String getACH3SELSEC()
  {
    return fieldACH3SELSEC.getString();
  }

  /**
  * Set numeric field ACH3SELSEC using a BigDecimal value.
  */
  public void setACH3SELSEC(BigDecimal newvalue)
  {
    fieldACH3SELSEC.setBigDecimal(newvalue);
  }

  /**
  * Return value of numeric field ACH3SELSEC as a BigDecimal.
  */
  public BigDecimal getBigDecimalACH3SELSEC()
  {
    return fieldACH3SELSEC.getBigDecimal();
  }

  /**
  * Set field ACH3SELREF using a String value.
  */
  public void setACH3SELREF(String newvalue)
  {
    fieldACH3SELREF.setString(newvalue);
  }

  /**
  * Get value of field ACH3SELREF as a String.
  */
  public String getACH3SELREF()
  {
    return fieldACH3SELREF.getString();
  }

  /**
  * Set field ACH3STATUS using a String value.
  */
  public void setACH3STATUS(String newvalue)
  {
    fieldACH3STATUS.setString(newvalue);
  }

  /**
  * Get value of field ACH3STATUS as a String.
  */
  public String getACH3STATUS()
  {
    return fieldACH3STATUS.getString();
  }

  /**
  * Set field ACH3FTR using a String value.
  */
  public void setACH3FTR(String newvalue)
  {
    fieldACH3FTR.setString(newvalue);
  }

  /**
  * Get value of field ACH3FTR as a String.
  */
  public String getACH3FTR()
  {
    return fieldACH3FTR.getString();
  }

  /**
  * Set field ACH3SEC using a String value.
  */
  public void setACH3SEC(String newvalue)
  {
    fieldACH3SEC.setString(newvalue);
  }

  /**
  * Get value of field ACH3SEC as a String.
  */
  public String getACH3SEC()
  {
    return fieldACH3SEC.getString();
  }

  /**
  * Set numeric field ACH3SEC using a BigDecimal value.
  */
  public void setACH3SEC(BigDecimal newvalue)
  {
    fieldACH3SEC.setBigDecimal(newvalue);
  }

  /**
  * Return value of numeric field ACH3SEC as a BigDecimal.
  */
  public BigDecimal getBigDecimalACH3SEC()
  {
    return fieldACH3SEC.getBigDecimal();
  }

  /**
  * Set field ACH3REF using a String value.
  */
  public void setACH3REF(String newvalue)
  {
    fieldACH3REF.setString(newvalue);
  }

  /**
  * Get value of field ACH3REF as a String.
  */
  public String getACH3REF()
  {
    return fieldACH3REF.getString();
  }

  /**
  * Set field ACH3IDA using a String value.
  */
  public void setACH3IDA(String newvalue)
  {
    fieldACH3IDA.setString(newvalue);
  }

  /**
  * Get value of field ACH3IDA as a String.
  */
  public String getACH3IDA()
  {
    return fieldACH3IDA.getString();
  }

  /**
  * Set field ACH3TTC using a String value.
  */
  public void setACH3TTC(String newvalue)
  {
    fieldACH3TTC.setString(newvalue);
  }

  /**
  * Get value of field ACH3TTC as a String.
  */
  public String getACH3TTC()
  {
    return fieldACH3TTC.getString();
  }

  /**
  * Set numeric field ACH3TTC using a BigDecimal value.
  */
  public void setACH3TTC(BigDecimal newvalue)
  {
    fieldACH3TTC.setBigDecimal(newvalue);
  }

  /**
  * Return value of numeric field ACH3TTC as a BigDecimal.
  */
  public BigDecimal getBigDecimalACH3TTC()
  {
    return fieldACH3TTC.getBigDecimal();
  }

  /**
  * Set field ACH3NMI using a String value.
  */
  public void setACH3NMI(String newvalue)
  {
    fieldACH3NMI.setString(newvalue);
  }

  /**
  * Get value of field ACH3NMI as a String.
  */
  public String getACH3NMI()
  {
    return fieldACH3NMI.getString();
  }

  /**
  * Set field ACH3TTR using a String value.
  */
  public void setACH3TTR(String newvalue)
  {
    fieldACH3TTR.setString(newvalue);
  }

  /**
  * Get value of field ACH3TTR as a String.
  */
  public String getACH3TTR()
  {
    return fieldACH3TTR.getString();
  }

  /**
  * Set field ACH3NUC using a String value.
  */
  public void setACH3NUC(String newvalue)
  {
    fieldACH3NUC.setString(newvalue);
  }

  /**
  * Get value of field ACH3NUC as a String.
  */
  public String getACH3NUC()
  {
    return fieldACH3NUC.getString();
  }

  /**
  * Set field ACH3IDC using a String value.
  */
  public void setACH3IDC(String newvalue)
  {
    fieldACH3IDC.setString(newvalue);
  }

  /**
  * Get value of field ACH3IDC as a String.
  */
  public String getACH3IDC()
  {
    return fieldACH3IDC.getString();
  }

  /**
  * Set field ACH3TIC using a String value.
  */
  public void setACH3TIC(String newvalue)
  {
    fieldACH3TIC.setString(newvalue);
  }

  /**
  * Get value of field ACH3TIC as a String.
  */
  public String getACH3TIC()
  {
    return fieldACH3TIC.getString();
  }

  /**
  * Set field ACH3MNT using a String value.
  */
  public void setACH3MNT(String newvalue)
  {
    fieldACH3MNT.setString(newvalue);
  }

  /**
  * Get value of field ACH3MNT as a String.
  */
  public String getACH3MNT()
  {
    return fieldACH3MNT.getString();
  }

  /**
  * Set numeric field ACH3MNT using a BigDecimal value.
  */
  public void setACH3MNT(BigDecimal newvalue)
  {
    fieldACH3MNT.setBigDecimal(newvalue);
  }

  /**
  * Return value of numeric field ACH3MNT as a BigDecimal.
  */
  public BigDecimal getBigDecimalACH3MNT()
  {
    return fieldACH3MNT.getBigDecimal();
  }

  /**
  * Set field ACH3DSC using a String value.
  */
  public void setACH3DSC(String newvalue)
  {
    fieldACH3DSC.setString(newvalue);
  }

  /**
  * Get value of field ACH3DSC as a String.
  */
  public String getACH3DSC()
  {
    return fieldACH3DSC.getString();
  }

  /**
  * Set field ACH3ADN using a String value.
  */
  public void setACH3ADN(String newvalue)
  {
    fieldACH3ADN.setString(newvalue);
  }

  /**
  * Get value of field ACH3ADN as a String.
  */
  public String getACH3ADN()
  {
    return fieldACH3ADN.getString();
  }

  /**
  * Set field ACH3CCY using a String value.
  */
  public void setACH3CCY(String newvalue)
  {
    fieldACH3CCY.setString(newvalue);
  }

  /**
  * Get value of field ACH3CCY as a String.
  */
  public String getACH3CCY()
  {
    return fieldACH3CCY.getString();
  }

  /**
  * Set field ACH3CDR using a String value.
  */
  public void setACH3CDR(String newvalue)
  {
    fieldACH3CDR.setString(newvalue);
  }

  /**
  * Get value of field ACH3CDR as a String.
  */
  public String getACH3CDR()
  {
    return fieldACH3CDR.getString();
  }

  /**
  * Set field ACH3STS using a String value.
  */
  public void setACH3STS(String newvalue)
  {
    fieldACH3STS.setString(newvalue);
  }

  /**
  * Get value of field ACH3STS as a String.
  */
  public String getACH3STS()
  {
    return fieldACH3STS.getString();
  }

  /**
  * Set field ACH3RDP using a String value.
  */
  public void setACH3RDP(String newvalue)
  {
    fieldACH3RDP.setString(newvalue);
  }

  /**
  * Get value of field ACH3RDP as a String.
  */
  public String getACH3RDP()
  {
    return fieldACH3RDP.getString();
  }

  /**
  * Set field ACH3MOD using a String value.
  */
  public void setACH3MOD(String newvalue)
  {
    fieldACH3MOD.setString(newvalue);
  }

  /**
  * Get value of field ACH3MOD as a String.
  */
  public String getACH3MOD()
  {
    return fieldACH3MOD.getString();
  }

  /**
  * Set field ACH3APR using a String value.
  */
  public void setACH3APR(String newvalue)
  {
    fieldACH3APR.setString(newvalue);
  }

  /**
  * Get value of field ACH3APR as a String.
  */
  public String getACH3APR()
  {
    return fieldACH3APR.getString();
  }

  /**
  * Set field ACH3REC using a String value.
  */
  public void setACH3REC(String newvalue)
  {
    fieldACH3REC.setString(newvalue);
  }

  /**
  * Get value of field ACH3REC as a String.
  */
  public String getACH3REC()
  {
    return fieldACH3REC.getString();
  }

  /**
  * Set field ACH3ACC using a String value.
  */
  public void setACH3ACC(String newvalue)
  {
    fieldACH3ACC.setString(newvalue);
  }

  /**
  * Get value of field ACH3ACC as a String.
  */
  public String getACH3ACC()
  {
    return fieldACH3ACC.getString();
  }

  /**
  * Set numeric field ACH3ACC using a BigDecimal value.
  */
  public void setACH3ACC(BigDecimal newvalue)
  {
    fieldACH3ACC.setBigDecimal(newvalue);
  }

  /**
  * Return value of numeric field ACH3ACC as a BigDecimal.
  */
  public BigDecimal getBigDecimalACH3ACC()
  {
    return fieldACH3ACC.getBigDecimal();
  }

  /**
  * Set field ACH3CUN using a String value.
  */
  public void setACH3CUN(String newvalue)
  {
    fieldACH3CUN.setString(newvalue);
  }

  /**
  * Get value of field ACH3CUN as a String.
  */
  public String getACH3CUN()
  {
    return fieldACH3CUN.getString();
  }

  /**
  * Set numeric field ACH3CUN using a BigDecimal value.
  */
  public void setACH3CUN(BigDecimal newvalue)
  {
    fieldACH3CUN.setBigDecimal(newvalue);
  }

  /**
  * Return value of numeric field ACH3CUN as a BigDecimal.
  */
  public BigDecimal getBigDecimalACH3CUN()
  {
    return fieldACH3CUN.getBigDecimal();
  }

  /**
  * Set field ACH3SHN using a String value.
  */
  public void setACH3SHN(String newvalue)
  {
    fieldACH3SHN.setString(newvalue);
  }

  /**
  * Get value of field ACH3SHN as a String.
  */
  public String getACH3SHN()
  {
    return fieldACH3SHN.getString();
  }

  /**
  * Set field ACH3USR using a String value.
  */
  public void setACH3USR(String newvalue)
  {
    fieldACH3USR.setString(newvalue);
  }

  /**
  * Get value of field ACH3USR as a String.
  */
  public String getACH3USR()
  {
    return fieldACH3USR.getString();
  }

  /**
  * Set field ACH3FCP using a String value.
  */
  public void setACH3FCP(String newvalue)
  {
    fieldACH3FCP.setString(newvalue);
  }

  /**
  * Get value of field ACH3FCP as a String.
  */
  public String getACH3FCP()
  {
    return fieldACH3FCP.getString();
  }

  /**
  * Set numeric field ACH3FCP using a BigDecimal value.
  */
  public void setACH3FCP(BigDecimal newvalue)
  {
    fieldACH3FCP.setBigDecimal(newvalue);
  }

  /**
  * Return value of numeric field ACH3FCP as a BigDecimal.
  */
  public BigDecimal getBigDecimalACH3FCP()
  {
    return fieldACH3FCP.getBigDecimal();
  }

  /**
  * Set field ACH3HRP using a String value.
  */
  public void setACH3HRP(String newvalue)
  {
    fieldACH3HRP.setString(newvalue);
  }

  /**
  * Get value of field ACH3HRP as a String.
  */
  public String getACH3HRP()
  {
    return fieldACH3HRP.getString();
  }

  /**
  * Set numeric field ACH3HRP using a BigDecimal value.
  */
  public void setACH3HRP(BigDecimal newvalue)
  {
    fieldACH3HRP.setBigDecimal(newvalue);
  }

  /**
  * Return value of numeric field ACH3HRP as a BigDecimal.
  */
  public BigDecimal getBigDecimalACH3HRP()
  {
    return fieldACH3HRP.getBigDecimal();
  }

  /**
  * Set field ACH3INDOPE using a String value.
  */
  public void setACH3INDOPE(String newvalue)
  {
    fieldACH3INDOPE.setString(newvalue);
  }

  /**
  * Get value of field ACH3INDOPE as a String.
  */
  public String getACH3INDOPE()
  {
    return fieldACH3INDOPE.getString();
  }

}