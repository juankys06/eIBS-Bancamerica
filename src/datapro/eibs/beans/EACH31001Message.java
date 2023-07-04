package datapro.eibs.beans;

import datapro.eibs.sockets.*;
import java.io.*;
import java.math.*;

import java.util.*;

/**
* Class generated by AS/400 CRTCLASS command from EACH31001 physical file definition.
* 
* File level identifier is 1080619143815.
* Record format level identifier is 4CD1F6150F5EE.
*/

public class EACH31001Message extends MessageRecord
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
                                     "E01ACBBTH",
                                     "E01ACBDSC",
                                     "E01ACBVDY",
                                     "E01ACBVDM",
                                     "E01ACBVDD",
                                     "E01ACBECD",
                                     "E01ACBDEB",
                                     "E01ACBCRE",
                                     "E01ACBTRN",
                                     "E01ACBRDY",
                                     "E01ACBRDM",
                                     "E01ACBRDD",
                                     "E01ACBUSR",
                                     "E01ACBAUS",
                                     "E01ACBSTS",
                                     "E01ACBRTS",
                                     "E01ACBPTS",
                                     "E01ECDDSC",
                                     "E01ACT",
                                     "E01NUMREC",
                                     "E01INDOPE"
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
                                   "E01ACBBTH",
                                   "E01ACBDSC",
                                   "E01ACBVDY",
                                   "E01ACBVDM",
                                   "E01ACBVDD",
                                   "E01ACBECD",
                                   "E01ACBDEB",
                                   "E01ACBCRE",
                                   "E01ACBTRN",
                                   "E01ACBRDY",
                                   "E01ACBRDM",
                                   "E01ACBRDD",
                                   "E01ACBUSR",
                                   "E01ACBAUS",
                                   "E01ACBSTS",
                                   "E01ACBRTS",
                                   "E01ACBPTS",
                                   "E01ECDDSC",
                                   "E01ACT",
                                   "E01NUMREC",
                                   "E01INDOPE"
                                 };
  final static String fid = "1080619143815";
  final static String rid = "4CD1F6150F5EE";
  final static String fmtname = "EACH31001";
  final int FIELDCOUNT = 30;
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
  private DecimalField fieldE01ACBBTH = null;
  private CharacterField fieldE01ACBDSC = null;
  private DecimalField fieldE01ACBVDY = null;
  private DecimalField fieldE01ACBVDM = null;
  private DecimalField fieldE01ACBVDD = null;
  private CharacterField fieldE01ACBECD = null;
  private DecimalField fieldE01ACBDEB = null;
  private DecimalField fieldE01ACBCRE = null;
  private DecimalField fieldE01ACBTRN = null;
  private DecimalField fieldE01ACBRDY = null;
  private DecimalField fieldE01ACBRDM = null;
  private DecimalField fieldE01ACBRDD = null;
  private CharacterField fieldE01ACBUSR = null;
  private CharacterField fieldE01ACBAUS = null;
  private CharacterField fieldE01ACBSTS = null;
  private DecimalField fieldE01ACBRTS = null;
  private DecimalField fieldE01ACBPTS = null;
  private CharacterField fieldE01ECDDSC = null;
  private CharacterField fieldE01ACT = null;
  private DecimalField fieldE01NUMREC = null;
  private CharacterField fieldE01INDOPE = null;

  /**
  * Constructor for EACH31001Message.
  */
  public EACH31001Message()
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
    recordsize = 221;
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
    fields[9] = fieldE01ACBBTH =
    new DecimalField(message, HEADERSIZE + 42, 5, 0, "E01ACBBTH");
    fields[10] = fieldE01ACBDSC =
    new CharacterField(message, HEADERSIZE + 47, 20, "E01ACBDSC");
    fields[11] = fieldE01ACBVDY =
    new DecimalField(message, HEADERSIZE + 67, 3, 0, "E01ACBVDY");
    fields[12] = fieldE01ACBVDM =
    new DecimalField(message, HEADERSIZE + 70, 3, 0, "E01ACBVDM");
    fields[13] = fieldE01ACBVDD =
    new DecimalField(message, HEADERSIZE + 73, 3, 0, "E01ACBVDD");
    fields[14] = fieldE01ACBECD =
    new CharacterField(message, HEADERSIZE + 76, 3, "E01ACBECD");
    fields[15] = fieldE01ACBDEB =
    new DecimalField(message, HEADERSIZE + 79, 17, 2, "E01ACBDEB");
    fields[16] = fieldE01ACBCRE =
    new DecimalField(message, HEADERSIZE + 96, 17, 2, "E01ACBCRE");
    fields[17] = fieldE01ACBTRN =
    new DecimalField(message, HEADERSIZE + 113, 7, 0, "E01ACBTRN");
    fields[18] = fieldE01ACBRDY =
    new DecimalField(message, HEADERSIZE + 120, 3, 0, "E01ACBRDY");
    fields[19] = fieldE01ACBRDM =
    new DecimalField(message, HEADERSIZE + 123, 3, 0, "E01ACBRDM");
    fields[20] = fieldE01ACBRDD =
    new DecimalField(message, HEADERSIZE + 126, 3, 0, "E01ACBRDD");
    fields[21] = fieldE01ACBUSR =
    new CharacterField(message, HEADERSIZE + 129, 10, "E01ACBUSR");
    fields[22] = fieldE01ACBAUS =
    new CharacterField(message, HEADERSIZE + 139, 10, "E01ACBAUS");
    fields[23] = fieldE01ACBSTS =
    new CharacterField(message, HEADERSIZE + 149, 1, "E01ACBSTS");
    fields[24] = fieldE01ACBRTS =
    new DecimalField(message, HEADERSIZE + 150, 13, 0, "E01ACBRTS");
    fields[25] = fieldE01ACBPTS =
    new DecimalField(message, HEADERSIZE + 163, 13, 0, "E01ACBPTS");
    fields[26] = fieldE01ECDDSC =
    new CharacterField(message, HEADERSIZE + 176, 35, "E01ECDDSC");
    fields[27] = fieldE01ACT =
    new CharacterField(message, HEADERSIZE + 211, 1, "E01ACT");
    fields[28] = fieldE01NUMREC =
    new DecimalField(message, HEADERSIZE + 212, 8, 0, "E01NUMREC");
    fields[29] = fieldE01INDOPE =
    new CharacterField(message, HEADERSIZE + 220, 1, "E01INDOPE");

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
  * Set field E01ACBBTH using a String value.
  */
  public void setE01ACBBTH(String newvalue)
  {
    fieldE01ACBBTH.setString(newvalue);
  }

  /**
  * Get value of field E01ACBBTH as a String.
  */
  public String getE01ACBBTH()
  {
    return fieldE01ACBBTH.getString();
  }

  /**
  * Set numeric field E01ACBBTH using a BigDecimal value.
  */
  public void setE01ACBBTH(BigDecimal newvalue)
  {
    fieldE01ACBBTH.setBigDecimal(newvalue);
  }

  /**
  * Return value of numeric field E01ACBBTH as a BigDecimal.
  */
  public BigDecimal getBigDecimalE01ACBBTH()
  {
    return fieldE01ACBBTH.getBigDecimal();
  }

  /**
  * Set field E01ACBDSC using a String value.
  */
  public void setE01ACBDSC(String newvalue)
  {
    fieldE01ACBDSC.setString(newvalue);
  }

  /**
  * Get value of field E01ACBDSC as a String.
  */
  public String getE01ACBDSC()
  {
    return fieldE01ACBDSC.getString();
  }

  /**
  * Set field E01ACBVDY using a String value.
  */
  public void setE01ACBVDY(String newvalue)
  {
    fieldE01ACBVDY.setString(newvalue);
  }

  /**
  * Get value of field E01ACBVDY as a String.
  */
  public String getE01ACBVDY()
  {
    return fieldE01ACBVDY.getString();
  }

  /**
  * Set numeric field E01ACBVDY using a BigDecimal value.
  */
  public void setE01ACBVDY(BigDecimal newvalue)
  {
    fieldE01ACBVDY.setBigDecimal(newvalue);
  }

  /**
  * Return value of numeric field E01ACBVDY as a BigDecimal.
  */
  public BigDecimal getBigDecimalE01ACBVDY()
  {
    return fieldE01ACBVDY.getBigDecimal();
  }

  /**
  * Set field E01ACBVDM using a String value.
  */
  public void setE01ACBVDM(String newvalue)
  {
    fieldE01ACBVDM.setString(newvalue);
  }

  /**
  * Get value of field E01ACBVDM as a String.
  */
  public String getE01ACBVDM()
  {
    return fieldE01ACBVDM.getString();
  }

  /**
  * Set numeric field E01ACBVDM using a BigDecimal value.
  */
  public void setE01ACBVDM(BigDecimal newvalue)
  {
    fieldE01ACBVDM.setBigDecimal(newvalue);
  }

  /**
  * Return value of numeric field E01ACBVDM as a BigDecimal.
  */
  public BigDecimal getBigDecimalE01ACBVDM()
  {
    return fieldE01ACBVDM.getBigDecimal();
  }

  /**
  * Set field E01ACBVDD using a String value.
  */
  public void setE01ACBVDD(String newvalue)
  {
    fieldE01ACBVDD.setString(newvalue);
  }

  /**
  * Get value of field E01ACBVDD as a String.
  */
  public String getE01ACBVDD()
  {
    return fieldE01ACBVDD.getString();
  }

  /**
  * Set numeric field E01ACBVDD using a BigDecimal value.
  */
  public void setE01ACBVDD(BigDecimal newvalue)
  {
    fieldE01ACBVDD.setBigDecimal(newvalue);
  }

  /**
  * Return value of numeric field E01ACBVDD as a BigDecimal.
  */
  public BigDecimal getBigDecimalE01ACBVDD()
  {
    return fieldE01ACBVDD.getBigDecimal();
  }

  /**
  * Set field E01ACBECD using a String value.
  */
  public void setE01ACBECD(String newvalue)
  {
    fieldE01ACBECD.setString(newvalue);
  }

  /**
  * Get value of field E01ACBECD as a String.
  */
  public String getE01ACBECD()
  {
    return fieldE01ACBECD.getString();
  }

  /**
  * Set field E01ACBDEB using a String value.
  */
  public void setE01ACBDEB(String newvalue)
  {
    fieldE01ACBDEB.setString(newvalue);
  }

  /**
  * Get value of field E01ACBDEB as a String.
  */
  public String getE01ACBDEB()
  {
    return fieldE01ACBDEB.getString();
  }

  /**
  * Set numeric field E01ACBDEB using a BigDecimal value.
  */
  public void setE01ACBDEB(BigDecimal newvalue)
  {
    fieldE01ACBDEB.setBigDecimal(newvalue);
  }

  /**
  * Return value of numeric field E01ACBDEB as a BigDecimal.
  */
  public BigDecimal getBigDecimalE01ACBDEB()
  {
    return fieldE01ACBDEB.getBigDecimal();
  }

  /**
  * Set field E01ACBCRE using a String value.
  */
  public void setE01ACBCRE(String newvalue)
  {
    fieldE01ACBCRE.setString(newvalue);
  }

  /**
  * Get value of field E01ACBCRE as a String.
  */
  public String getE01ACBCRE()
  {
    return fieldE01ACBCRE.getString();
  }

  /**
  * Set numeric field E01ACBCRE using a BigDecimal value.
  */
  public void setE01ACBCRE(BigDecimal newvalue)
  {
    fieldE01ACBCRE.setBigDecimal(newvalue);
  }

  /**
  * Return value of numeric field E01ACBCRE as a BigDecimal.
  */
  public BigDecimal getBigDecimalE01ACBCRE()
  {
    return fieldE01ACBCRE.getBigDecimal();
  }

  /**
  * Set field E01ACBTRN using a String value.
  */
  public void setE01ACBTRN(String newvalue)
  {
    fieldE01ACBTRN.setString(newvalue);
  }

  /**
  * Get value of field E01ACBTRN as a String.
  */
  public String getE01ACBTRN()
  {
    return fieldE01ACBTRN.getString();
  }

  /**
  * Set numeric field E01ACBTRN using a BigDecimal value.
  */
  public void setE01ACBTRN(BigDecimal newvalue)
  {
    fieldE01ACBTRN.setBigDecimal(newvalue);
  }

  /**
  * Return value of numeric field E01ACBTRN as a BigDecimal.
  */
  public BigDecimal getBigDecimalE01ACBTRN()
  {
    return fieldE01ACBTRN.getBigDecimal();
  }

  /**
  * Set field E01ACBRDY using a String value.
  */
  public void setE01ACBRDY(String newvalue)
  {
    fieldE01ACBRDY.setString(newvalue);
  }

  /**
  * Get value of field E01ACBRDY as a String.
  */
  public String getE01ACBRDY()
  {
    return fieldE01ACBRDY.getString();
  }

  /**
  * Set numeric field E01ACBRDY using a BigDecimal value.
  */
  public void setE01ACBRDY(BigDecimal newvalue)
  {
    fieldE01ACBRDY.setBigDecimal(newvalue);
  }

  /**
  * Return value of numeric field E01ACBRDY as a BigDecimal.
  */
  public BigDecimal getBigDecimalE01ACBRDY()
  {
    return fieldE01ACBRDY.getBigDecimal();
  }

  /**
  * Set field E01ACBRDM using a String value.
  */
  public void setE01ACBRDM(String newvalue)
  {
    fieldE01ACBRDM.setString(newvalue);
  }

  /**
  * Get value of field E01ACBRDM as a String.
  */
  public String getE01ACBRDM()
  {
    return fieldE01ACBRDM.getString();
  }

  /**
  * Set numeric field E01ACBRDM using a BigDecimal value.
  */
  public void setE01ACBRDM(BigDecimal newvalue)
  {
    fieldE01ACBRDM.setBigDecimal(newvalue);
  }

  /**
  * Return value of numeric field E01ACBRDM as a BigDecimal.
  */
  public BigDecimal getBigDecimalE01ACBRDM()
  {
    return fieldE01ACBRDM.getBigDecimal();
  }

  /**
  * Set field E01ACBRDD using a String value.
  */
  public void setE01ACBRDD(String newvalue)
  {
    fieldE01ACBRDD.setString(newvalue);
  }

  /**
  * Get value of field E01ACBRDD as a String.
  */
  public String getE01ACBRDD()
  {
    return fieldE01ACBRDD.getString();
  }

  /**
  * Set numeric field E01ACBRDD using a BigDecimal value.
  */
  public void setE01ACBRDD(BigDecimal newvalue)
  {
    fieldE01ACBRDD.setBigDecimal(newvalue);
  }

  /**
  * Return value of numeric field E01ACBRDD as a BigDecimal.
  */
  public BigDecimal getBigDecimalE01ACBRDD()
  {
    return fieldE01ACBRDD.getBigDecimal();
  }

  /**
  * Set field E01ACBUSR using a String value.
  */
  public void setE01ACBUSR(String newvalue)
  {
    fieldE01ACBUSR.setString(newvalue);
  }

  /**
  * Get value of field E01ACBUSR as a String.
  */
  public String getE01ACBUSR()
  {
    return fieldE01ACBUSR.getString();
  }

  /**
  * Set field E01ACBAUS using a String value.
  */
  public void setE01ACBAUS(String newvalue)
  {
    fieldE01ACBAUS.setString(newvalue);
  }

  /**
  * Get value of field E01ACBAUS as a String.
  */
  public String getE01ACBAUS()
  {
    return fieldE01ACBAUS.getString();
  }

  /**
  * Set field E01ACBSTS using a String value.
  */
  public void setE01ACBSTS(String newvalue)
  {
    fieldE01ACBSTS.setString(newvalue);
  }

  /**
  * Get value of field E01ACBSTS as a String.
  */
  public String getE01ACBSTS()
  {
    return fieldE01ACBSTS.getString();
  }

  /**
  * Set field E01ACBRTS using a String value.
  */
  public void setE01ACBRTS(String newvalue)
  {
    fieldE01ACBRTS.setString(newvalue);
  }

  /**
  * Get value of field E01ACBRTS as a String.
  */
  public String getE01ACBRTS()
  {
    return fieldE01ACBRTS.getString();
  }

  /**
  * Set numeric field E01ACBRTS using a BigDecimal value.
  */
  public void setE01ACBRTS(BigDecimal newvalue)
  {
    fieldE01ACBRTS.setBigDecimal(newvalue);
  }

  /**
  * Return value of numeric field E01ACBRTS as a BigDecimal.
  */
  public BigDecimal getBigDecimalE01ACBRTS()
  {
    return fieldE01ACBRTS.getBigDecimal();
  }

  /**
  * Set field E01ACBPTS using a String value.
  */
  public void setE01ACBPTS(String newvalue)
  {
    fieldE01ACBPTS.setString(newvalue);
  }

  /**
  * Get value of field E01ACBPTS as a String.
  */
  public String getE01ACBPTS()
  {
    return fieldE01ACBPTS.getString();
  }

  /**
  * Set numeric field E01ACBPTS using a BigDecimal value.
  */
  public void setE01ACBPTS(BigDecimal newvalue)
  {
    fieldE01ACBPTS.setBigDecimal(newvalue);
  }

  /**
  * Return value of numeric field E01ACBPTS as a BigDecimal.
  */
  public BigDecimal getBigDecimalE01ACBPTS()
  {
    return fieldE01ACBPTS.getBigDecimal();
  }

  /**
  * Set field E01ECDDSC using a String value.
  */
  public void setE01ECDDSC(String newvalue)
  {
    fieldE01ECDDSC.setString(newvalue);
  }

  /**
  * Get value of field E01ECDDSC as a String.
  */
  public String getE01ECDDSC()
  {
    return fieldE01ECDDSC.getString();
  }

  /**
  * Set field E01ACT using a String value.
  */
  public void setE01ACT(String newvalue)
  {
    fieldE01ACT.setString(newvalue);
  }

  /**
  * Get value of field E01ACT as a String.
  */
  public String getE01ACT()
  {
    return fieldE01ACT.getString();
  }

  /**
  * Set field E01NUMREC using a String value.
  */
  public void setE01NUMREC(String newvalue)
  {
    fieldE01NUMREC.setString(newvalue);
  }

  /**
  * Get value of field E01NUMREC as a String.
  */
  public String getE01NUMREC()
  {
    return fieldE01NUMREC.getString();
  }

  /**
  * Set numeric field E01NUMREC using a BigDecimal value.
  */
  public void setE01NUMREC(BigDecimal newvalue)
  {
    fieldE01NUMREC.setBigDecimal(newvalue);
  }

  /**
  * Return value of numeric field E01NUMREC as a BigDecimal.
  */
  public BigDecimal getBigDecimalE01NUMREC()
  {
    return fieldE01NUMREC.getBigDecimal();
  }

  /**
  * Set field E01INDOPE using a String value.
  */
  public void setE01INDOPE(String newvalue)
  {
    fieldE01INDOPE.setString(newvalue);
  }

  /**
  * Get value of field E01INDOPE as a String.
  */
  public String getE01INDOPE()
  {
    return fieldE01INDOPE.getString();
  }

}