package datapro.eibs.beans;

import datapro.eibs.sockets.*;
import java.io.*;
import java.math.*;

import java.util.*;

/**
* Class generated by AS/400 CRTCLASS command from EDD700001 physical file definition.
* 
* File level identifier is 1090424183935.
* Record format level identifier is 471C158CAD0EF.
*/

public class EDD700001Message extends MessageRecord
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
                                     "E01CUSACC",
                                     "E01CUSNA1",
                                     "E01CUSNA2",
                                     "E01CUSNA3",
                                     "E01CUSNA4",
                                     "E01CUSIDN",
                                     "E01CUSTI1",
                                     "E01CUSTI2",
                                     "E01CUSTI3",
                                     "E01CUSTI4",
                                     "E01CUSID1",
                                     "E01CUSID2",
                                     "E01CUSID3",
                                     "E01CUSID4",
                                     "E01CUSBRN",
                                     "E01CUSRDM",
                                     "E01CUSRDD",
                                     "E01CUSRDY",
                                     "E01CUSF01",
                                     "E01CUSPBN",
                                     "E01CUSMOT",
                                     "E01FMTBCC",
                                     "E01BRNNUM",
                                     "E01BRNNME"
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
                                   "E01CUSACC",
                                   "E01CUSNA1",
                                   "E01CUSNA2",
                                   "E01CUSNA3",
                                   "E01CUSNA4",
                                   "E01CUSIDN",
                                   "E01CUSTI1",
                                   "E01CUSTI2",
                                   "E01CUSTI3",
                                   "E01CUSTI4",
                                   "E01CUSID1",
                                   "E01CUSID2",
                                   "E01CUSID3",
                                   "E01CUSID4",
                                   "E01CUSBRN",
                                   "E01CUSRDM",
                                   "E01CUSRDD",
                                   "E01CUSRDY",
                                   "E01CUSF01",
                                   "E01CUSPBN",
                                   "E01CUSMOT",
                                   "E01FMTBCC",
                                   "E01BRNNUM",
                                   "E01BRNNME"
                                 };
  final static String fid = "1090424183935";
  final static String rid = "471C158CAD0EF";
  final static String fmtname = "EDD700001";
  final int FIELDCOUNT = 33;
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
  private DecimalField fieldE01CUSACC = null;
  private CharacterField fieldE01CUSNA1 = null;
  private CharacterField fieldE01CUSNA2 = null;
  private CharacterField fieldE01CUSNA3 = null;
  private CharacterField fieldE01CUSNA4 = null;
  private CharacterField fieldE01CUSIDN = null;
  private CharacterField fieldE01CUSTI1 = null;
  private CharacterField fieldE01CUSTI2 = null;
  private CharacterField fieldE01CUSTI3 = null;
  private CharacterField fieldE01CUSTI4 = null;
  private CharacterField fieldE01CUSID1 = null;
  private CharacterField fieldE01CUSID2 = null;
  private CharacterField fieldE01CUSID3 = null;
  private CharacterField fieldE01CUSID4 = null;
  private DecimalField fieldE01CUSBRN = null;
  private DecimalField fieldE01CUSRDM = null;
  private DecimalField fieldE01CUSRDD = null;
  private DecimalField fieldE01CUSRDY = null;
  private CharacterField fieldE01CUSF01 = null;
  private DecimalField fieldE01CUSPBN = null;
  private CharacterField fieldE01CUSMOT = null;
  private CharacterField fieldE01FMTBCC = null;
  private DecimalField fieldE01BRNNUM = null;
  private CharacterField fieldE01BRNNME = null;

  /**
  * Constructor for EDD700001Message.
  */
  public EDD700001Message()
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
    recordsize = 543;
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
    fields[9] = fieldE01CUSACC =
    new DecimalField(message, HEADERSIZE + 42, 13, 0, "E01CUSACC");
    fields[10] = fieldE01CUSNA1 =
    new CharacterField(message, HEADERSIZE + 55, 45, "E01CUSNA1");
    fields[11] = fieldE01CUSNA2 =
    new CharacterField(message, HEADERSIZE + 100, 35, "E01CUSNA2");
    fields[12] = fieldE01CUSNA3 =
    new CharacterField(message, HEADERSIZE + 135, 35, "E01CUSNA3");
    fields[13] = fieldE01CUSNA4 =
    new CharacterField(message, HEADERSIZE + 170, 35, "E01CUSNA4");
    fields[14] = fieldE01CUSIDN =
    new CharacterField(message, HEADERSIZE + 205, 15, "E01CUSIDN");
    fields[15] = fieldE01CUSTI1 =
    new CharacterField(message, HEADERSIZE + 220, 45, "E01CUSTI1");
    fields[16] = fieldE01CUSTI2 =
    new CharacterField(message, HEADERSIZE + 265, 45, "E01CUSTI2");
    fields[17] = fieldE01CUSTI3 =
    new CharacterField(message, HEADERSIZE + 310, 45, "E01CUSTI3");
    fields[18] = fieldE01CUSTI4 =
    new CharacterField(message, HEADERSIZE + 355, 45, "E01CUSTI4");
    fields[19] = fieldE01CUSID1 =
    new CharacterField(message, HEADERSIZE + 400, 15, "E01CUSID1");
    fields[20] = fieldE01CUSID2 =
    new CharacterField(message, HEADERSIZE + 415, 15, "E01CUSID2");
    fields[21] = fieldE01CUSID3 =
    new CharacterField(message, HEADERSIZE + 430, 15, "E01CUSID3");
    fields[22] = fieldE01CUSID4 =
    new CharacterField(message, HEADERSIZE + 445, 15, "E01CUSID4");
    fields[23] = fieldE01CUSBRN =
    new DecimalField(message, HEADERSIZE + 460, 4, 0, "E01CUSBRN");
    fields[24] = fieldE01CUSRDM =
    new DecimalField(message, HEADERSIZE + 464, 3, 0, "E01CUSRDM");
    fields[25] = fieldE01CUSRDD =
    new DecimalField(message, HEADERSIZE + 467, 3, 0, "E01CUSRDD");
    fields[26] = fieldE01CUSRDY =
    new DecimalField(message, HEADERSIZE + 470, 3, 0, "E01CUSRDY");
    fields[27] = fieldE01CUSF01 =
    new CharacterField(message, HEADERSIZE + 473, 1, "E01CUSF01");
    fields[28] = fieldE01CUSPBN =
    new DecimalField(message, HEADERSIZE + 474, 9, 0, "E01CUSPBN");
    fields[29] = fieldE01CUSMOT =
    new CharacterField(message, HEADERSIZE + 483, 1, "E01CUSMOT");
    fields[30] = fieldE01FMTBCC =
    new CharacterField(message, HEADERSIZE + 484, 20, "E01FMTBCC");
    fields[31] = fieldE01BRNNUM =
    new DecimalField(message, HEADERSIZE + 504, 4, 0, "E01BRNNUM");
    fields[32] = fieldE01BRNNME =
    new CharacterField(message, HEADERSIZE + 508, 35, "E01BRNNME");

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
  * Set field E01CUSACC using a String value.
  */
  public void setE01CUSACC(String newvalue)
  {
    fieldE01CUSACC.setString(newvalue);
  }

  /**
  * Get value of field E01CUSACC as a String.
  */
  public String getE01CUSACC()
  {
    return fieldE01CUSACC.getString();
  }

  /**
  * Set numeric field E01CUSACC using a BigDecimal value.
  */
  public void setE01CUSACC(BigDecimal newvalue)
  {
    fieldE01CUSACC.setBigDecimal(newvalue);
  }

  /**
  * Return value of numeric field E01CUSACC as a BigDecimal.
  */
  public BigDecimal getBigDecimalE01CUSACC()
  {
    return fieldE01CUSACC.getBigDecimal();
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
  * Set field E01CUSTI1 using a String value.
  */
  public void setE01CUSTI1(String newvalue)
  {
    fieldE01CUSTI1.setString(newvalue);
  }

  /**
  * Get value of field E01CUSTI1 as a String.
  */
  public String getE01CUSTI1()
  {
    return fieldE01CUSTI1.getString();
  }

  /**
  * Set field E01CUSTI2 using a String value.
  */
  public void setE01CUSTI2(String newvalue)
  {
    fieldE01CUSTI2.setString(newvalue);
  }

  /**
  * Get value of field E01CUSTI2 as a String.
  */
  public String getE01CUSTI2()
  {
    return fieldE01CUSTI2.getString();
  }

  /**
  * Set field E01CUSTI3 using a String value.
  */
  public void setE01CUSTI3(String newvalue)
  {
    fieldE01CUSTI3.setString(newvalue);
  }

  /**
  * Get value of field E01CUSTI3 as a String.
  */
  public String getE01CUSTI3()
  {
    return fieldE01CUSTI3.getString();
  }

  /**
  * Set field E01CUSTI4 using a String value.
  */
  public void setE01CUSTI4(String newvalue)
  {
    fieldE01CUSTI4.setString(newvalue);
  }

  /**
  * Get value of field E01CUSTI4 as a String.
  */
  public String getE01CUSTI4()
  {
    return fieldE01CUSTI4.getString();
  }

  /**
  * Set field E01CUSID1 using a String value.
  */
  public void setE01CUSID1(String newvalue)
  {
    fieldE01CUSID1.setString(newvalue);
  }

  /**
  * Get value of field E01CUSID1 as a String.
  */
  public String getE01CUSID1()
  {
    return fieldE01CUSID1.getString();
  }

  /**
  * Set field E01CUSID2 using a String value.
  */
  public void setE01CUSID2(String newvalue)
  {
    fieldE01CUSID2.setString(newvalue);
  }

  /**
  * Get value of field E01CUSID2 as a String.
  */
  public String getE01CUSID2()
  {
    return fieldE01CUSID2.getString();
  }

  /**
  * Set field E01CUSID3 using a String value.
  */
  public void setE01CUSID3(String newvalue)
  {
    fieldE01CUSID3.setString(newvalue);
  }

  /**
  * Get value of field E01CUSID3 as a String.
  */
  public String getE01CUSID3()
  {
    return fieldE01CUSID3.getString();
  }

  /**
  * Set field E01CUSID4 using a String value.
  */
  public void setE01CUSID4(String newvalue)
  {
    fieldE01CUSID4.setString(newvalue);
  }

  /**
  * Get value of field E01CUSID4 as a String.
  */
  public String getE01CUSID4()
  {
    return fieldE01CUSID4.getString();
  }

  /**
  * Set field E01CUSBRN using a String value.
  */
  public void setE01CUSBRN(String newvalue)
  {
    fieldE01CUSBRN.setString(newvalue);
  }

  /**
  * Get value of field E01CUSBRN as a String.
  */
  public String getE01CUSBRN()
  {
    return fieldE01CUSBRN.getString();
  }

  /**
  * Set numeric field E01CUSBRN using a BigDecimal value.
  */
  public void setE01CUSBRN(BigDecimal newvalue)
  {
    fieldE01CUSBRN.setBigDecimal(newvalue);
  }

  /**
  * Return value of numeric field E01CUSBRN as a BigDecimal.
  */
  public BigDecimal getBigDecimalE01CUSBRN()
  {
    return fieldE01CUSBRN.getBigDecimal();
  }

  /**
  * Set field E01CUSRDM using a String value.
  */
  public void setE01CUSRDM(String newvalue)
  {
    fieldE01CUSRDM.setString(newvalue);
  }

  /**
  * Get value of field E01CUSRDM as a String.
  */
  public String getE01CUSRDM()
  {
    return fieldE01CUSRDM.getString();
  }

  /**
  * Set numeric field E01CUSRDM using a BigDecimal value.
  */
  public void setE01CUSRDM(BigDecimal newvalue)
  {
    fieldE01CUSRDM.setBigDecimal(newvalue);
  }

  /**
  * Return value of numeric field E01CUSRDM as a BigDecimal.
  */
  public BigDecimal getBigDecimalE01CUSRDM()
  {
    return fieldE01CUSRDM.getBigDecimal();
  }

  /**
  * Set field E01CUSRDD using a String value.
  */
  public void setE01CUSRDD(String newvalue)
  {
    fieldE01CUSRDD.setString(newvalue);
  }

  /**
  * Get value of field E01CUSRDD as a String.
  */
  public String getE01CUSRDD()
  {
    return fieldE01CUSRDD.getString();
  }

  /**
  * Set numeric field E01CUSRDD using a BigDecimal value.
  */
  public void setE01CUSRDD(BigDecimal newvalue)
  {
    fieldE01CUSRDD.setBigDecimal(newvalue);
  }

  /**
  * Return value of numeric field E01CUSRDD as a BigDecimal.
  */
  public BigDecimal getBigDecimalE01CUSRDD()
  {
    return fieldE01CUSRDD.getBigDecimal();
  }

  /**
  * Set field E01CUSRDY using a String value.
  */
  public void setE01CUSRDY(String newvalue)
  {
    fieldE01CUSRDY.setString(newvalue);
  }

  /**
  * Get value of field E01CUSRDY as a String.
  */
  public String getE01CUSRDY()
  {
    return fieldE01CUSRDY.getString();
  }

  /**
  * Set numeric field E01CUSRDY using a BigDecimal value.
  */
  public void setE01CUSRDY(BigDecimal newvalue)
  {
    fieldE01CUSRDY.setBigDecimal(newvalue);
  }

  /**
  * Return value of numeric field E01CUSRDY as a BigDecimal.
  */
  public BigDecimal getBigDecimalE01CUSRDY()
  {
    return fieldE01CUSRDY.getBigDecimal();
  }

  /**
  * Set field E01CUSF01 using a String value.
  */
  public void setE01CUSF01(String newvalue)
  {
    fieldE01CUSF01.setString(newvalue);
  }

  /**
  * Get value of field E01CUSF01 as a String.
  */
  public String getE01CUSF01()
  {
    return fieldE01CUSF01.getString();
  }

  /**
  * Set field E01CUSPBN using a String value.
  */
  public void setE01CUSPBN(String newvalue)
  {
    fieldE01CUSPBN.setString(newvalue);
  }

  /**
  * Get value of field E01CUSPBN as a String.
  */
  public String getE01CUSPBN()
  {
    return fieldE01CUSPBN.getString();
  }

  /**
  * Set numeric field E01CUSPBN using a BigDecimal value.
  */
  public void setE01CUSPBN(BigDecimal newvalue)
  {
    fieldE01CUSPBN.setBigDecimal(newvalue);
  }

  /**
  * Return value of numeric field E01CUSPBN as a BigDecimal.
  */
  public BigDecimal getBigDecimalE01CUSPBN()
  {
    return fieldE01CUSPBN.getBigDecimal();
  }

  /**
  * Set field E01CUSMOT using a String value.
  */
  public void setE01CUSMOT(String newvalue)
  {
    fieldE01CUSMOT.setString(newvalue);
  }

  /**
  * Get value of field E01CUSMOT as a String.
  */
  public String getE01CUSMOT()
  {
    return fieldE01CUSMOT.getString();
  }

  /**
  * Set field E01FMTBCC using a String value.
  */
  public void setE01FMTBCC(String newvalue)
  {
    fieldE01FMTBCC.setString(newvalue);
  }

  /**
  * Get value of field E01FMTBCC as a String.
  */
  public String getE01FMTBCC()
  {
    return fieldE01FMTBCC.getString();
  }

  /**
  * Set field E01BRNNUM using a String value.
  */
  public void setE01BRNNUM(String newvalue)
  {
    fieldE01BRNNUM.setString(newvalue);
  }

  /**
  * Get value of field E01BRNNUM as a String.
  */
  public String getE01BRNNUM()
  {
    return fieldE01BRNNUM.getString();
  }

  /**
  * Set numeric field E01BRNNUM using a BigDecimal value.
  */
  public void setE01BRNNUM(BigDecimal newvalue)
  {
    fieldE01BRNNUM.setBigDecimal(newvalue);
  }

  /**
  * Return value of numeric field E01BRNNUM as a BigDecimal.
  */
  public BigDecimal getBigDecimalE01BRNNUM()
  {
    return fieldE01BRNNUM.getBigDecimal();
  }

  /**
  * Set field E01BRNNME using a String value.
  */
  public void setE01BRNNME(String newvalue)
  {
    fieldE01BRNNME.setString(newvalue);
  }

  /**
  * Get value of field E01BRNNME as a String.
  */
  public String getE01BRNNME()
  {
    return fieldE01BRNNME.getString();
  }

}
