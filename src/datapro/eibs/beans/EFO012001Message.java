package datapro.eibs.beans;

import datapro.eibs.sockets.*;
import java.io.*;
import java.math.*;

import java.util.*;

/**
* Class generated by AS/400 CRTCLASS command from EFO012001 physical file definition.
* 
* File level identifier is 1070813114252.
* Record format level identifier is 35304B257D0B0.
*/

public class EFO012001Message extends MessageRecord
{
  final static String fldnames[] = {
                                     "H01USERID",
                                     "H01PROGRM",
                                     "H01TIMSYS",
                                     "H01SCRCOD",
                                     "H01OPECOD",
                                     "H01FLGMAS",
                                     "H01FLGWK1",
                                     "H01FLGWK2",
                                     "H01FLGWK3",
                                     "E01CUSLGT",
                                     "E01CUSSTS",
                                     "E01CUSCUN",
                                     "E01CUSCCL",
                                     "E01CUSNA1",
                                     "E01CUSFNA",
                                     "E01CUSFN2",
                                     "E01CUSLN1",
                                     "E01CUSLN2",
                                     "E01CUSSHN",
                                     "E01CUSNA2",
                                     "E01CUSNA3",
                                     "E01CUSNA4",
                                     "E01CUSCTY",
                                     "E01CUSSTE",
                                     "E01CUSCTR",
                                     "E01CUSSEX",
                                     "E01CUSMST",
                                     "E01CUSNSO",
                                     "E01CUSBDM",
                                     "E01CUSBDD",
                                     "E01CUSBDY",
                                     "E01CUSIDM",
                                     "E01CUSIDD",
                                     "E01CUSIDY",
                                     "E01CUSHPN",
                                     "E01CUSPHN",
                                     "E01CUSPH1",
                                     "E01CUSFAX",
                                     "E01CUSIAD",
                                     "E01CUSIDN",
                                     "E01CUSTID",
                                     "E01CUSPID",
                                     "E01CUSOFC",
                                     "D01CUSCCS",
                                     "D01CUSSTE",
                                     "D01CUSTID",
                                     "D01CUSPID",
                                     "D01CUSOFC"
                                   };
  final static String tnames[] = {
                                   "H01USERID",
                                   "H01PROGRM",
                                   "H01TIMSYS",
                                   "H01SCRCOD",
                                   "H01OPECOD",
                                   "H01FLGMAS",
                                   "H01FLGWK1",
                                   "H01FLGWK2",
                                   "H01FLGWK3",
                                   "E01CUSLGT",
                                   "E01CUSSTS",
                                   "E01CUSCUN",
                                   "E01CUSCCL",
                                   "E01CUSNA1",
                                   "E01CUSFNA",
                                   "E01CUSFN2",
                                   "E01CUSLN1",
                                   "E01CUSLN2",
                                   "E01CUSSHN",
                                   "E01CUSNA2",
                                   "E01CUSNA3",
                                   "E01CUSNA4",
                                   "E01CUSCTY",
                                   "E01CUSSTE",
                                   "E01CUSCTR",
                                   "E01CUSSEX",
                                   "E01CUSMST",
                                   "E01CUSNSO",
                                   "E01CUSBDM",
                                   "E01CUSBDD",
                                   "E01CUSBDY",
                                   "E01CUSIDM",
                                   "E01CUSIDD",
                                   "E01CUSIDY",
                                   "E01CUSHPN",
                                   "E01CUSPHN",
                                   "E01CUSPH1",
                                   "E01CUSFAX",
                                   "E01CUSIAD",
                                   "E01CUSIDN",
                                   "E01CUSTID",
                                   "E01CUSPID",
                                   "E01CUSOFC",
                                   "D01CUSCCS",
                                   "D01CUSSTE",
                                   "D01CUSTID",
                                   "D01CUSPID",
                                   "D01CUSOFC"
                                 };
  final static String fid = "1070813114252";
  final static String rid = "35304B257D0B0";
  final static String fmtname = "EFO012001";
  final int FIELDCOUNT = 48;
  private static Hashtable tlookup = new Hashtable();
  private CharacterField fieldH01USERID = null;
  private CharacterField fieldH01PROGRM = null;
  private CharacterField fieldH01TIMSYS = null;
  private CharacterField fieldH01SCRCOD = null;
  private CharacterField fieldH01OPECOD = null;
  private CharacterField fieldH01FLGMAS = null;
  private CharacterField fieldH01FLGWK1 = null;
  private CharacterField fieldH01FLGWK2 = null;
  private CharacterField fieldH01FLGWK3 = null;
  private CharacterField fieldE01CUSLGT = null;
  private CharacterField fieldE01CUSSTS = null;
  private DecimalField fieldE01CUSCUN = null;
  private CharacterField fieldE01CUSCCL = null;
  private CharacterField fieldE01CUSNA1 = null;
  private CharacterField fieldE01CUSFNA = null;
  private CharacterField fieldE01CUSFN2 = null;
  private CharacterField fieldE01CUSLN1 = null;
  private CharacterField fieldE01CUSLN2 = null;
  private CharacterField fieldE01CUSSHN = null;
  private CharacterField fieldE01CUSNA2 = null;
  private CharacterField fieldE01CUSNA3 = null;
  private CharacterField fieldE01CUSNA4 = null;
  private CharacterField fieldE01CUSCTY = null;
  private CharacterField fieldE01CUSSTE = null;
  private CharacterField fieldE01CUSCTR = null;
  private CharacterField fieldE01CUSSEX = null;
  private CharacterField fieldE01CUSMST = null;
  private DecimalField fieldE01CUSNSO = null;
  private DecimalField fieldE01CUSBDM = null;
  private DecimalField fieldE01CUSBDD = null;
  private DecimalField fieldE01CUSBDY = null;
  private DecimalField fieldE01CUSIDM = null;
  private DecimalField fieldE01CUSIDD = null;
  private DecimalField fieldE01CUSIDY = null;
  private DecimalField fieldE01CUSHPN = null;
  private DecimalField fieldE01CUSPHN = null;
  private DecimalField fieldE01CUSPH1 = null;
  private DecimalField fieldE01CUSFAX = null;
  private CharacterField fieldE01CUSIAD = null;
  private CharacterField fieldE01CUSIDN = null;
  private CharacterField fieldE01CUSTID = null;
  private CharacterField fieldE01CUSPID = null;
  private CharacterField fieldE01CUSOFC = null;
  private CharacterField fieldD01CUSCCS = null;
  private CharacterField fieldD01CUSSTE = null;
  private CharacterField fieldD01CUSTID = null;
  private CharacterField fieldD01CUSPID = null;
  private CharacterField fieldD01CUSOFC = null;

  /**
  * Constructor for EFO012001Message.
  */
  public EFO012001Message()
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
    recordsize = 709;
    fileid = fid;
    recordid = rid;
    message = new byte[getByteLength()];
    formatname = fmtname;
    fieldnames = fldnames;
    tagnames = tnames;
    fields = new MessageField[FIELDCOUNT];

    fields[0] = fieldH01USERID =
    new CharacterField(message, HEADERSIZE + 0, 10, "H01USERID");
    fields[1] = fieldH01PROGRM =
    new CharacterField(message, HEADERSIZE + 10, 10, "H01PROGRM");
    fields[2] = fieldH01TIMSYS =
    new CharacterField(message, HEADERSIZE + 20, 12, "H01TIMSYS");
    fields[3] = fieldH01SCRCOD =
    new CharacterField(message, HEADERSIZE + 32, 2, "H01SCRCOD");
    fields[4] = fieldH01OPECOD =
    new CharacterField(message, HEADERSIZE + 34, 4, "H01OPECOD");
    fields[5] = fieldH01FLGMAS =
    new CharacterField(message, HEADERSIZE + 38, 1, "H01FLGMAS");
    fields[6] = fieldH01FLGWK1 =
    new CharacterField(message, HEADERSIZE + 39, 1, "H01FLGWK1");
    fields[7] = fieldH01FLGWK2 =
    new CharacterField(message, HEADERSIZE + 40, 1, "H01FLGWK2");
    fields[8] = fieldH01FLGWK3 =
    new CharacterField(message, HEADERSIZE + 41, 1, "H01FLGWK3");
    fields[9] = fieldE01CUSLGT =
    new CharacterField(message, HEADERSIZE + 42, 1, "E01CUSLGT");
    fields[10] = fieldE01CUSSTS =
    new CharacterField(message, HEADERSIZE + 43, 1, "E01CUSSTS");
    fields[11] = fieldE01CUSCUN =
    new DecimalField(message, HEADERSIZE + 44, 10, 0, "E01CUSCUN");
    fields[12] = fieldE01CUSCCL =
    new CharacterField(message, HEADERSIZE + 54, 1, "E01CUSCCL");
    fields[13] = fieldE01CUSNA1 =
    new CharacterField(message, HEADERSIZE + 55, 45, "E01CUSNA1");
    fields[14] = fieldE01CUSFNA =
    new CharacterField(message, HEADERSIZE + 100, 30, "E01CUSFNA");
    fields[15] = fieldE01CUSFN2 =
    new CharacterField(message, HEADERSIZE + 130, 30, "E01CUSFN2");
    fields[16] = fieldE01CUSLN1 =
    new CharacterField(message, HEADERSIZE + 160, 30, "E01CUSLN1");
    fields[17] = fieldE01CUSLN2 =
    new CharacterField(message, HEADERSIZE + 190, 30, "E01CUSLN2");
    fields[18] = fieldE01CUSSHN =
    new CharacterField(message, HEADERSIZE + 220, 15, "E01CUSSHN");
    fields[19] = fieldE01CUSNA2 =
    new CharacterField(message, HEADERSIZE + 235, 35, "E01CUSNA2");
    fields[20] = fieldE01CUSNA3 =
    new CharacterField(message, HEADERSIZE + 270, 35, "E01CUSNA3");
    fields[21] = fieldE01CUSNA4 =
    new CharacterField(message, HEADERSIZE + 305, 35, "E01CUSNA4");
    fields[22] = fieldE01CUSCTY =
    new CharacterField(message, HEADERSIZE + 340, 30, "E01CUSCTY");
    fields[23] = fieldE01CUSSTE =
    new CharacterField(message, HEADERSIZE + 370, 4, "E01CUSSTE");
    fields[24] = fieldE01CUSCTR =
    new CharacterField(message, HEADERSIZE + 374, 20, "E01CUSCTR");
    fields[25] = fieldE01CUSSEX =
    new CharacterField(message, HEADERSIZE + 394, 1, "E01CUSSEX");
    fields[26] = fieldE01CUSMST =
    new CharacterField(message, HEADERSIZE + 395, 1, "E01CUSMST");
    fields[27] = fieldE01CUSNSO =
    new DecimalField(message, HEADERSIZE + 396, 3, 0, "E01CUSNSO");
    fields[28] = fieldE01CUSBDM =
    new DecimalField(message, HEADERSIZE + 399, 3, 0, "E01CUSBDM");
    fields[29] = fieldE01CUSBDD =
    new DecimalField(message, HEADERSIZE + 402, 3, 0, "E01CUSBDD");
    fields[30] = fieldE01CUSBDY =
    new DecimalField(message, HEADERSIZE + 405, 5, 0, "E01CUSBDY");
    fields[31] = fieldE01CUSIDM =
    new DecimalField(message, HEADERSIZE + 410, 3, 0, "E01CUSIDM");
    fields[32] = fieldE01CUSIDD =
    new DecimalField(message, HEADERSIZE + 413, 3, 0, "E01CUSIDD");
    fields[33] = fieldE01CUSIDY =
    new DecimalField(message, HEADERSIZE + 416, 3, 0, "E01CUSIDY");
    fields[34] = fieldE01CUSHPN =
    new DecimalField(message, HEADERSIZE + 419, 12, 0, "E01CUSHPN");
    fields[35] = fieldE01CUSPHN =
    new DecimalField(message, HEADERSIZE + 431, 12, 0, "E01CUSPHN");
    fields[36] = fieldE01CUSPH1 =
    new DecimalField(message, HEADERSIZE + 443, 12, 0, "E01CUSPH1");
    fields[37] = fieldE01CUSFAX =
    new DecimalField(message, HEADERSIZE + 455, 12, 0, "E01CUSFAX");
    fields[38] = fieldE01CUSIAD =
    new CharacterField(message, HEADERSIZE + 467, 40, "E01CUSIAD");
    fields[39] = fieldE01CUSIDN =
    new CharacterField(message, HEADERSIZE + 507, 15, "E01CUSIDN");
    fields[40] = fieldE01CUSTID =
    new CharacterField(message, HEADERSIZE + 522, 4, "E01CUSTID");
    fields[41] = fieldE01CUSPID =
    new CharacterField(message, HEADERSIZE + 526, 4, "E01CUSPID");
    fields[42] = fieldE01CUSOFC =
    new CharacterField(message, HEADERSIZE + 530, 4, "E01CUSOFC");
    fields[43] = fieldD01CUSCCS =
    new CharacterField(message, HEADERSIZE + 534, 35, "D01CUSCCS");
    fields[44] = fieldD01CUSSTE =
    new CharacterField(message, HEADERSIZE + 569, 35, "D01CUSSTE");
    fields[45] = fieldD01CUSTID =
    new CharacterField(message, HEADERSIZE + 604, 35, "D01CUSTID");
    fields[46] = fieldD01CUSPID =
    new CharacterField(message, HEADERSIZE + 639, 35, "D01CUSPID");
    fields[47] = fieldD01CUSOFC =
    new CharacterField(message, HEADERSIZE + 674, 35, "D01CUSOFC");

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
  * Set field H01USERID using a String value.
  */
  public void setH01USERID(String newvalue)
  {
    fieldH01USERID.setString(newvalue);
  }

  /**
  * Get value of field H01USERID as a String.
  */
  public String getH01USERID()
  {
    return fieldH01USERID.getString();
  }

  /**
  * Set field H01PROGRM using a String value.
  */
  public void setH01PROGRM(String newvalue)
  {
    fieldH01PROGRM.setString(newvalue);
  }

  /**
  * Get value of field H01PROGRM as a String.
  */
  public String getH01PROGRM()
  {
    return fieldH01PROGRM.getString();
  }

  /**
  * Set field H01TIMSYS using a String value.
  */
  public void setH01TIMSYS(String newvalue)
  {
    fieldH01TIMSYS.setString(newvalue);
  }

  /**
  * Get value of field H01TIMSYS as a String.
  */
  public String getH01TIMSYS()
  {
    return fieldH01TIMSYS.getString();
  }

  /**
  * Set field H01SCRCOD using a String value.
  */
  public void setH01SCRCOD(String newvalue)
  {
    fieldH01SCRCOD.setString(newvalue);
  }

  /**
  * Get value of field H01SCRCOD as a String.
  */
  public String getH01SCRCOD()
  {
    return fieldH01SCRCOD.getString();
  }

  /**
  * Set field H01OPECOD using a String value.
  */
  public void setH01OPECOD(String newvalue)
  {
    fieldH01OPECOD.setString(newvalue);
  }

  /**
  * Get value of field H01OPECOD as a String.
  */
  public String getH01OPECOD()
  {
    return fieldH01OPECOD.getString();
  }

  /**
  * Set field H01FLGMAS using a String value.
  */
  public void setH01FLGMAS(String newvalue)
  {
    fieldH01FLGMAS.setString(newvalue);
  }

  /**
  * Get value of field H01FLGMAS as a String.
  */
  public String getH01FLGMAS()
  {
    return fieldH01FLGMAS.getString();
  }

  /**
  * Set field H01FLGWK1 using a String value.
  */
  public void setH01FLGWK1(String newvalue)
  {
    fieldH01FLGWK1.setString(newvalue);
  }

  /**
  * Get value of field H01FLGWK1 as a String.
  */
  public String getH01FLGWK1()
  {
    return fieldH01FLGWK1.getString();
  }

  /**
  * Set field H01FLGWK2 using a String value.
  */
  public void setH01FLGWK2(String newvalue)
  {
    fieldH01FLGWK2.setString(newvalue);
  }

  /**
  * Get value of field H01FLGWK2 as a String.
  */
  public String getH01FLGWK2()
  {
    return fieldH01FLGWK2.getString();
  }

  /**
  * Set field H01FLGWK3 using a String value.
  */
  public void setH01FLGWK3(String newvalue)
  {
    fieldH01FLGWK3.setString(newvalue);
  }

  /**
  * Get value of field H01FLGWK3 as a String.
  */
  public String getH01FLGWK3()
  {
    return fieldH01FLGWK3.getString();
  }

  /**
  * Set field E01CUSLGT using a String value.
  */
  public void setE01CUSLGT(String newvalue)
  {
    fieldE01CUSLGT.setString(newvalue);
  }

  /**
  * Get value of field E01CUSLGT as a String.
  */
  public String getE01CUSLGT()
  {
    return fieldE01CUSLGT.getString();
  }

  /**
  * Set field E01CUSSTS using a String value.
  */
  public void setE01CUSSTS(String newvalue)
  {
    fieldE01CUSSTS.setString(newvalue);
  }

  /**
  * Get value of field E01CUSSTS as a String.
  */
  public String getE01CUSSTS()
  {
    return fieldE01CUSSTS.getString();
  }

  /**
  * Set field E01CUSCUN using a String value.
  */
  public void setE01CUSCUN(String newvalue)
  {
    fieldE01CUSCUN.setString(newvalue);
  }

  /**
  * Get value of field E01CUSCUN as a String.
  */
  public String getE01CUSCUN()
  {
    return fieldE01CUSCUN.getString();
  }

  /**
  * Set numeric field E01CUSCUN using a BigDecimal value.
  */
  public void setE01CUSCUN(BigDecimal newvalue)
  {
    fieldE01CUSCUN.setBigDecimal(newvalue);
  }

  /**
  * Return value of numeric field E01CUSCUN as a BigDecimal.
  */
  public BigDecimal getBigDecimalE01CUSCUN()
  {
    return fieldE01CUSCUN.getBigDecimal();
  }

  /**
  * Set field E01CUSCCL using a String value.
  */
  public void setE01CUSCCL(String newvalue)
  {
    fieldE01CUSCCL.setString(newvalue);
  }

  /**
  * Get value of field E01CUSCCL as a String.
  */
  public String getE01CUSCCL()
  {
    return fieldE01CUSCCL.getString();
  }

  /**
  * Set field E01CUSNA1 using a String value.
  */
  public void setE01CUSNA1(String newvalue)
  {
    fieldE01CUSNA1.setString(newvalue);
  }

  /**
  * Get value of field E01CUSNA1 as a String.
  */
  public String getE01CUSNA1()
  {
    return fieldE01CUSNA1.getString();
  }

  /**
  * Set field E01CUSFNA using a String value.
  */
  public void setE01CUSFNA(String newvalue)
  {
    fieldE01CUSFNA.setString(newvalue);
  }

  /**
  * Get value of field E01CUSFNA as a String.
  */
  public String getE01CUSFNA()
  {
    return fieldE01CUSFNA.getString();
  }

  /**
  * Set field E01CUSFN2 using a String value.
  */
  public void setE01CUSFN2(String newvalue)
  {
    fieldE01CUSFN2.setString(newvalue);
  }

  /**
  * Get value of field E01CUSFN2 as a String.
  */
  public String getE01CUSFN2()
  {
    return fieldE01CUSFN2.getString();
  }

  /**
  * Set field E01CUSLN1 using a String value.
  */
  public void setE01CUSLN1(String newvalue)
  {
    fieldE01CUSLN1.setString(newvalue);
  }

  /**
  * Get value of field E01CUSLN1 as a String.
  */
  public String getE01CUSLN1()
  {
    return fieldE01CUSLN1.getString();
  }

  /**
  * Set field E01CUSLN2 using a String value.
  */
  public void setE01CUSLN2(String newvalue)
  {
    fieldE01CUSLN2.setString(newvalue);
  }

  /**
  * Get value of field E01CUSLN2 as a String.
  */
  public String getE01CUSLN2()
  {
    return fieldE01CUSLN2.getString();
  }

  /**
  * Set field E01CUSSHN using a String value.
  */
  public void setE01CUSSHN(String newvalue)
  {
    fieldE01CUSSHN.setString(newvalue);
  }

  /**
  * Get value of field E01CUSSHN as a String.
  */
  public String getE01CUSSHN()
  {
    return fieldE01CUSSHN.getString();
  }

  /**
  * Set field E01CUSNA2 using a String value.
  */
  public void setE01CUSNA2(String newvalue)
  {
    fieldE01CUSNA2.setString(newvalue);
  }

  /**
  * Get value of field E01CUSNA2 as a String.
  */
  public String getE01CUSNA2()
  {
    return fieldE01CUSNA2.getString();
  }

  /**
  * Set field E01CUSNA3 using a String value.
  */
  public void setE01CUSNA3(String newvalue)
  {
    fieldE01CUSNA3.setString(newvalue);
  }

  /**
  * Get value of field E01CUSNA3 as a String.
  */
  public String getE01CUSNA3()
  {
    return fieldE01CUSNA3.getString();
  }

  /**
  * Set field E01CUSNA4 using a String value.
  */
  public void setE01CUSNA4(String newvalue)
  {
    fieldE01CUSNA4.setString(newvalue);
  }

  /**
  * Get value of field E01CUSNA4 as a String.
  */
  public String getE01CUSNA4()
  {
    return fieldE01CUSNA4.getString();
  }

  /**
  * Set field E01CUSCTY using a String value.
  */
  public void setE01CUSCTY(String newvalue)
  {
    fieldE01CUSCTY.setString(newvalue);
  }

  /**
  * Get value of field E01CUSCTY as a String.
  */
  public String getE01CUSCTY()
  {
    return fieldE01CUSCTY.getString();
  }

  /**
  * Set field E01CUSSTE using a String value.
  */
  public void setE01CUSSTE(String newvalue)
  {
    fieldE01CUSSTE.setString(newvalue);
  }

  /**
  * Get value of field E01CUSSTE as a String.
  */
  public String getE01CUSSTE()
  {
    return fieldE01CUSSTE.getString();
  }

  /**
  * Set field E01CUSCTR using a String value.
  */
  public void setE01CUSCTR(String newvalue)
  {
    fieldE01CUSCTR.setString(newvalue);
  }

  /**
  * Get value of field E01CUSCTR as a String.
  */
  public String getE01CUSCTR()
  {
    return fieldE01CUSCTR.getString();
  }

  /**
  * Set field E01CUSSEX using a String value.
  */
  public void setE01CUSSEX(String newvalue)
  {
    fieldE01CUSSEX.setString(newvalue);
  }

  /**
  * Get value of field E01CUSSEX as a String.
  */
  public String getE01CUSSEX()
  {
    return fieldE01CUSSEX.getString();
  }

  /**
  * Set field E01CUSMST using a String value.
  */
  public void setE01CUSMST(String newvalue)
  {
    fieldE01CUSMST.setString(newvalue);
  }

  /**
  * Get value of field E01CUSMST as a String.
  */
  public String getE01CUSMST()
  {
    return fieldE01CUSMST.getString();
  }

  /**
  * Set field E01CUSNSO using a String value.
  */
  public void setE01CUSNSO(String newvalue)
  {
    fieldE01CUSNSO.setString(newvalue);
  }

  /**
  * Get value of field E01CUSNSO as a String.
  */
  public String getE01CUSNSO()
  {
    return fieldE01CUSNSO.getString();
  }

  /**
  * Set numeric field E01CUSNSO using a BigDecimal value.
  */
  public void setE01CUSNSO(BigDecimal newvalue)
  {
    fieldE01CUSNSO.setBigDecimal(newvalue);
  }

  /**
  * Return value of numeric field E01CUSNSO as a BigDecimal.
  */
  public BigDecimal getBigDecimalE01CUSNSO()
  {
    return fieldE01CUSNSO.getBigDecimal();
  }

  /**
  * Set field E01CUSBDM using a String value.
  */
  public void setE01CUSBDM(String newvalue)
  {
    fieldE01CUSBDM.setString(newvalue);
  }

  /**
  * Get value of field E01CUSBDM as a String.
  */
  public String getE01CUSBDM()
  {
    return fieldE01CUSBDM.getString();
  }

  /**
  * Set numeric field E01CUSBDM using a BigDecimal value.
  */
  public void setE01CUSBDM(BigDecimal newvalue)
  {
    fieldE01CUSBDM.setBigDecimal(newvalue);
  }

  /**
  * Return value of numeric field E01CUSBDM as a BigDecimal.
  */
  public BigDecimal getBigDecimalE01CUSBDM()
  {
    return fieldE01CUSBDM.getBigDecimal();
  }

  /**
  * Set field E01CUSBDD using a String value.
  */
  public void setE01CUSBDD(String newvalue)
  {
    fieldE01CUSBDD.setString(newvalue);
  }

  /**
  * Get value of field E01CUSBDD as a String.
  */
  public String getE01CUSBDD()
  {
    return fieldE01CUSBDD.getString();
  }

  /**
  * Set numeric field E01CUSBDD using a BigDecimal value.
  */
  public void setE01CUSBDD(BigDecimal newvalue)
  {
    fieldE01CUSBDD.setBigDecimal(newvalue);
  }

  /**
  * Return value of numeric field E01CUSBDD as a BigDecimal.
  */
  public BigDecimal getBigDecimalE01CUSBDD()
  {
    return fieldE01CUSBDD.getBigDecimal();
  }

  /**
  * Set field E01CUSBDY using a String value.
  */
  public void setE01CUSBDY(String newvalue)
  {
    fieldE01CUSBDY.setString(newvalue);
  }

  /**
  * Get value of field E01CUSBDY as a String.
  */
  public String getE01CUSBDY()
  {
    return fieldE01CUSBDY.getString();
  }

  /**
  * Set numeric field E01CUSBDY using a BigDecimal value.
  */
  public void setE01CUSBDY(BigDecimal newvalue)
  {
    fieldE01CUSBDY.setBigDecimal(newvalue);
  }

  /**
  * Return value of numeric field E01CUSBDY as a BigDecimal.
  */
  public BigDecimal getBigDecimalE01CUSBDY()
  {
    return fieldE01CUSBDY.getBigDecimal();
  }

  /**
  * Set field E01CUSIDM using a String value.
  */
  public void setE01CUSIDM(String newvalue)
  {
    fieldE01CUSIDM.setString(newvalue);
  }

  /**
  * Get value of field E01CUSIDM as a String.
  */
  public String getE01CUSIDM()
  {
    return fieldE01CUSIDM.getString();
  }

  /**
  * Set numeric field E01CUSIDM using a BigDecimal value.
  */
  public void setE01CUSIDM(BigDecimal newvalue)
  {
    fieldE01CUSIDM.setBigDecimal(newvalue);
  }

  /**
  * Return value of numeric field E01CUSIDM as a BigDecimal.
  */
  public BigDecimal getBigDecimalE01CUSIDM()
  {
    return fieldE01CUSIDM.getBigDecimal();
  }

  /**
  * Set field E01CUSIDD using a String value.
  */
  public void setE01CUSIDD(String newvalue)
  {
    fieldE01CUSIDD.setString(newvalue);
  }

  /**
  * Get value of field E01CUSIDD as a String.
  */
  public String getE01CUSIDD()
  {
    return fieldE01CUSIDD.getString();
  }

  /**
  * Set numeric field E01CUSIDD using a BigDecimal value.
  */
  public void setE01CUSIDD(BigDecimal newvalue)
  {
    fieldE01CUSIDD.setBigDecimal(newvalue);
  }

  /**
  * Return value of numeric field E01CUSIDD as a BigDecimal.
  */
  public BigDecimal getBigDecimalE01CUSIDD()
  {
    return fieldE01CUSIDD.getBigDecimal();
  }

  /**
  * Set field E01CUSIDY using a String value.
  */
  public void setE01CUSIDY(String newvalue)
  {
    fieldE01CUSIDY.setString(newvalue);
  }

  /**
  * Get value of field E01CUSIDY as a String.
  */
  public String getE01CUSIDY()
  {
    return fieldE01CUSIDY.getString();
  }

  /**
  * Set numeric field E01CUSIDY using a BigDecimal value.
  */
  public void setE01CUSIDY(BigDecimal newvalue)
  {
    fieldE01CUSIDY.setBigDecimal(newvalue);
  }

  /**
  * Return value of numeric field E01CUSIDY as a BigDecimal.
  */
  public BigDecimal getBigDecimalE01CUSIDY()
  {
    return fieldE01CUSIDY.getBigDecimal();
  }

  /**
  * Set field E01CUSHPN using a String value.
  */
  public void setE01CUSHPN(String newvalue)
  {
    fieldE01CUSHPN.setString(newvalue);
  }

  /**
  * Get value of field E01CUSHPN as a String.
  */
  public String getE01CUSHPN()
  {
    return fieldE01CUSHPN.getString();
  }

  /**
  * Set numeric field E01CUSHPN using a BigDecimal value.
  */
  public void setE01CUSHPN(BigDecimal newvalue)
  {
    fieldE01CUSHPN.setBigDecimal(newvalue);
  }

  /**
  * Return value of numeric field E01CUSHPN as a BigDecimal.
  */
  public BigDecimal getBigDecimalE01CUSHPN()
  {
    return fieldE01CUSHPN.getBigDecimal();
  }

  /**
  * Set field E01CUSPHN using a String value.
  */
  public void setE01CUSPHN(String newvalue)
  {
    fieldE01CUSPHN.setString(newvalue);
  }

  /**
  * Get value of field E01CUSPHN as a String.
  */
  public String getE01CUSPHN()
  {
    return fieldE01CUSPHN.getString();
  }

  /**
  * Set numeric field E01CUSPHN using a BigDecimal value.
  */
  public void setE01CUSPHN(BigDecimal newvalue)
  {
    fieldE01CUSPHN.setBigDecimal(newvalue);
  }

  /**
  * Return value of numeric field E01CUSPHN as a BigDecimal.
  */
  public BigDecimal getBigDecimalE01CUSPHN()
  {
    return fieldE01CUSPHN.getBigDecimal();
  }

  /**
  * Set field E01CUSPH1 using a String value.
  */
  public void setE01CUSPH1(String newvalue)
  {
    fieldE01CUSPH1.setString(newvalue);
  }

  /**
  * Get value of field E01CUSPH1 as a String.
  */
  public String getE01CUSPH1()
  {
    return fieldE01CUSPH1.getString();
  }

  /**
  * Set numeric field E01CUSPH1 using a BigDecimal value.
  */
  public void setE01CUSPH1(BigDecimal newvalue)
  {
    fieldE01CUSPH1.setBigDecimal(newvalue);
  }

  /**
  * Return value of numeric field E01CUSPH1 as a BigDecimal.
  */
  public BigDecimal getBigDecimalE01CUSPH1()
  {
    return fieldE01CUSPH1.getBigDecimal();
  }

  /**
  * Set field E01CUSFAX using a String value.
  */
  public void setE01CUSFAX(String newvalue)
  {
    fieldE01CUSFAX.setString(newvalue);
  }

  /**
  * Get value of field E01CUSFAX as a String.
  */
  public String getE01CUSFAX()
  {
    return fieldE01CUSFAX.getString();
  }

  /**
  * Set numeric field E01CUSFAX using a BigDecimal value.
  */
  public void setE01CUSFAX(BigDecimal newvalue)
  {
    fieldE01CUSFAX.setBigDecimal(newvalue);
  }

  /**
  * Return value of numeric field E01CUSFAX as a BigDecimal.
  */
  public BigDecimal getBigDecimalE01CUSFAX()
  {
    return fieldE01CUSFAX.getBigDecimal();
  }

  /**
  * Set field E01CUSIAD using a String value.
  */
  public void setE01CUSIAD(String newvalue)
  {
    fieldE01CUSIAD.setString(newvalue);
  }

  /**
  * Get value of field E01CUSIAD as a String.
  */
  public String getE01CUSIAD()
  {
    return fieldE01CUSIAD.getString();
  }

  /**
  * Set field E01CUSIDN using a String value.
  */
  public void setE01CUSIDN(String newvalue)
  {
    fieldE01CUSIDN.setString(newvalue);
  }

  /**
  * Get value of field E01CUSIDN as a String.
  */
  public String getE01CUSIDN()
  {
    return fieldE01CUSIDN.getString();
  }

  /**
  * Set field E01CUSTID using a String value.
  */
  public void setE01CUSTID(String newvalue)
  {
    fieldE01CUSTID.setString(newvalue);
  }

  /**
  * Get value of field E01CUSTID as a String.
  */
  public String getE01CUSTID()
  {
    return fieldE01CUSTID.getString();
  }

  /**
  * Set field E01CUSPID using a String value.
  */
  public void setE01CUSPID(String newvalue)
  {
    fieldE01CUSPID.setString(newvalue);
  }

  /**
  * Get value of field E01CUSPID as a String.
  */
  public String getE01CUSPID()
  {
    return fieldE01CUSPID.getString();
  }

  /**
  * Set field E01CUSOFC using a String value.
  */
  public void setE01CUSOFC(String newvalue)
  {
    fieldE01CUSOFC.setString(newvalue);
  }

  /**
  * Get value of field E01CUSOFC as a String.
  */
  public String getE01CUSOFC()
  {
    return fieldE01CUSOFC.getString();
  }

  /**
  * Set field D01CUSCCS using a String value.
  */
  public void setD01CUSCCS(String newvalue)
  {
    fieldD01CUSCCS.setString(newvalue);
  }

  /**
  * Get value of field D01CUSCCS as a String.
  */
  public String getD01CUSCCS()
  {
    return fieldD01CUSCCS.getString();
  }

  /**
  * Set field D01CUSSTE using a String value.
  */
  public void setD01CUSSTE(String newvalue)
  {
    fieldD01CUSSTE.setString(newvalue);
  }

  /**
  * Get value of field D01CUSSTE as a String.
  */
  public String getD01CUSSTE()
  {
    return fieldD01CUSSTE.getString();
  }

  /**
  * Set field D01CUSTID using a String value.
  */
  public void setD01CUSTID(String newvalue)
  {
    fieldD01CUSTID.setString(newvalue);
  }

  /**
  * Get value of field D01CUSTID as a String.
  */
  public String getD01CUSTID()
  {
    return fieldD01CUSTID.getString();
  }

  /**
  * Set field D01CUSPID using a String value.
  */
  public void setD01CUSPID(String newvalue)
  {
    fieldD01CUSPID.setString(newvalue);
  }

  /**
  * Get value of field D01CUSPID as a String.
  */
  public String getD01CUSPID()
  {
    return fieldD01CUSPID.getString();
  }

  /**
  * Set field D01CUSOFC using a String value.
  */
  public void setD01CUSOFC(String newvalue)
  {
    fieldD01CUSOFC.setString(newvalue);
  }

  /**
  * Get value of field D01CUSOFC as a String.
  */
  public String getD01CUSOFC()
  {
    return fieldD01CUSOFC.getString();
  }

}
