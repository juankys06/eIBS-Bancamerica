package datapro.eibs.beans;

import datapro.eibs.sockets.*;
import java.io.*;
import java.math.*;

import java.util.*;

/**
* Class generated by AS/400 CRTCLASS command from EFO012006 physical file definition.
* 
* File level identifier is 1070813114252.
* Record format level identifier is 45050FC41781B.
*/

public class EFO012006Message extends MessageRecord
{
  final static String fldnames[] = {
                                     "H06USERID",
                                     "H06PROGRM",
                                     "H06TIMSYS",
                                     "H06SCRCOD",
                                     "H06OPECOD",
                                     "H06FLGMAS",
                                     "H06FLGWK1",
                                     "H06FLGWK2",
                                     "H06FLGWK3",
                                     "E06SELCUN",
                                     "E06CUSNA1",
                                     "E06SELACD",
                                     "E06SELFLG",
                                     "E06SELTYP",
                                     "E06SELPRO",
                                     "E06ACCNUM",
                                     "E06BNKNUM",
                                     "E06BRNNUM",
                                     "E06CCYCDE",
                                     "E06ACCTYP",
                                     "E06PROCDE",
                                     "E06PRIAMN",
                                     "E06INTAMN",
                                     "E06OTHAMN",
                                     "E06TOTAMN",
                                     "E06EQVPRI",
                                     "E06EQVINT",
                                     "E06EQVOTH",
                                     "E06EQVAMN",
                                     "E06ACCSTS",
                                     "E06OPEDT1",
                                     "E06OPEDT2",
                                     "E06OPEDT3",
                                     "E06OFFICR",
                                     "E06PRODSC",
                                     "E06MATDT1",
                                     "E06MATDT2",
                                     "E06MATDT3",
                                     "E06DEAROC",
                                     "E06DEARAC",
                                     "E06DEATRM",
                                     "E06DEATRC",
                                     "E06INTRTE",
                                     "E06NUMREC",
                                     "E06INDOPE"
                                   };
  final static String tnames[] = {
                                   "H06USERID",
                                   "H06PROGRM",
                                   "H06TIMSYS",
                                   "H06SCRCOD",
                                   "H06OPECOD",
                                   "H06FLGMAS",
                                   "H06FLGWK1",
                                   "H06FLGWK2",
                                   "H06FLGWK3",
                                   "E06SELCUN",
                                   "E06CUSNA1",
                                   "E06SELACD",
                                   "E06SELFLG",
                                   "E06SELTYP",
                                   "E06SELPRO",
                                   "E06ACCNUM",
                                   "E06BNKNUM",
                                   "E06BRNNUM",
                                   "E06CCYCDE",
                                   "E06ACCTYP",
                                   "E06PROCDE",
                                   "E06PRIAMN",
                                   "E06INTAMN",
                                   "E06OTHAMN",
                                   "E06TOTAMN",
                                   "E06EQVPRI",
                                   "E06EQVINT",
                                   "E06EQVOTH",
                                   "E06EQVAMN",
                                   "E06ACCSTS",
                                   "E06OPEDT1",
                                   "E06OPEDT2",
                                   "E06OPEDT3",
                                   "E06OFFICR",
                                   "E06PRODSC",
                                   "E06MATDT1",
                                   "E06MATDT2",
                                   "E06MATDT3",
                                   "E06DEAROC",
                                   "E06DEARAC",
                                   "E06DEATRM",
                                   "E06DEATRC",
                                   "E06INTRTE",
                                   "E06NUMREC",
                                   "E06INDOPE"
                                 };
  final static String fid = "1070813114252";
  final static String rid = "45050FC41781B";
  final static String fmtname = "EFO012006";
  final int FIELDCOUNT = 45;
  private static Hashtable tlookup = new Hashtable();
  private CharacterField fieldH06USERID = null;
  private CharacterField fieldH06PROGRM = null;
  private CharacterField fieldH06TIMSYS = null;
  private CharacterField fieldH06SCRCOD = null;
  private CharacterField fieldH06OPECOD = null;
  private CharacterField fieldH06FLGMAS = null;
  private CharacterField fieldH06FLGWK1 = null;
  private CharacterField fieldH06FLGWK2 = null;
  private CharacterField fieldH06FLGWK3 = null;
  private DecimalField fieldE06SELCUN = null;
  private CharacterField fieldE06CUSNA1 = null;
  private CharacterField fieldE06SELACD = null;
  private CharacterField fieldE06SELFLG = null;
  private CharacterField fieldE06SELTYP = null;
  private CharacterField fieldE06SELPRO = null;
  private DecimalField fieldE06ACCNUM = null;
  private CharacterField fieldE06BNKNUM = null;
  private DecimalField fieldE06BRNNUM = null;
  private CharacterField fieldE06CCYCDE = null;
  private CharacterField fieldE06ACCTYP = null;
  private CharacterField fieldE06PROCDE = null;
  private DecimalField fieldE06PRIAMN = null;
  private DecimalField fieldE06INTAMN = null;
  private DecimalField fieldE06OTHAMN = null;
  private DecimalField fieldE06TOTAMN = null;
  private DecimalField fieldE06EQVPRI = null;
  private DecimalField fieldE06EQVINT = null;
  private DecimalField fieldE06EQVOTH = null;
  private DecimalField fieldE06EQVAMN = null;
  private CharacterField fieldE06ACCSTS = null;
  private DecimalField fieldE06OPEDT1 = null;
  private DecimalField fieldE06OPEDT2 = null;
  private DecimalField fieldE06OPEDT3 = null;
  private CharacterField fieldE06OFFICR = null;
  private CharacterField fieldE06PRODSC = null;
  private DecimalField fieldE06MATDT1 = null;
  private DecimalField fieldE06MATDT2 = null;
  private DecimalField fieldE06MATDT3 = null;
  private CharacterField fieldE06DEAROC = null;
  private DecimalField fieldE06DEARAC = null;
  private DecimalField fieldE06DEATRM = null;
  private CharacterField fieldE06DEATRC = null;
  private DecimalField fieldE06INTRTE = null;
  private DecimalField fieldE06NUMREC = null;
  private CharacterField fieldE06INDOPE = null;

  /**
  * Constructor for EFO012006Message.
  */
  public EFO012006Message()
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
    recordsize = 376;
    fileid = fid;
    recordid = rid;
    message = new byte[getByteLength()];
    formatname = fmtname;
    fieldnames = fldnames;
    tagnames = tnames;
    fields = new MessageField[FIELDCOUNT];

    fields[0] = fieldH06USERID =
    new CharacterField(message, HEADERSIZE + 0, 10, "H06USERID");
    fields[1] = fieldH06PROGRM =
    new CharacterField(message, HEADERSIZE + 10, 10, "H06PROGRM");
    fields[2] = fieldH06TIMSYS =
    new CharacterField(message, HEADERSIZE + 20, 12, "H06TIMSYS");
    fields[3] = fieldH06SCRCOD =
    new CharacterField(message, HEADERSIZE + 32, 2, "H06SCRCOD");
    fields[4] = fieldH06OPECOD =
    new CharacterField(message, HEADERSIZE + 34, 4, "H06OPECOD");
    fields[5] = fieldH06FLGMAS =
    new CharacterField(message, HEADERSIZE + 38, 1, "H06FLGMAS");
    fields[6] = fieldH06FLGWK1 =
    new CharacterField(message, HEADERSIZE + 39, 1, "H06FLGWK1");
    fields[7] = fieldH06FLGWK2 =
    new CharacterField(message, HEADERSIZE + 40, 1, "H06FLGWK2");
    fields[8] = fieldH06FLGWK3 =
    new CharacterField(message, HEADERSIZE + 41, 1, "H06FLGWK3");
    fields[9] = fieldE06SELCUN =
    new DecimalField(message, HEADERSIZE + 42, 10, 0, "E06SELCUN");
    fields[10] = fieldE06CUSNA1 =
    new CharacterField(message, HEADERSIZE + 52, 35, "E06CUSNA1");
    fields[11] = fieldE06SELACD =
    new CharacterField(message, HEADERSIZE + 87, 2, "E06SELACD");
    fields[12] = fieldE06SELFLG =
    new CharacterField(message, HEADERSIZE + 89, 1, "E06SELFLG");
    fields[13] = fieldE06SELTYP =
    new CharacterField(message, HEADERSIZE + 90, 4, "E06SELTYP");
    fields[14] = fieldE06SELPRO =
    new CharacterField(message, HEADERSIZE + 94, 4, "E06SELPRO");
    fields[15] = fieldE06ACCNUM =
    new DecimalField(message, HEADERSIZE + 98, 13, 0, "E06ACCNUM");
    fields[16] = fieldE06BNKNUM =
    new CharacterField(message, HEADERSIZE + 111, 2, "E06BNKNUM");
    fields[17] = fieldE06BRNNUM =
    new DecimalField(message, HEADERSIZE + 113, 4, 0, "E06BRNNUM");
    fields[18] = fieldE06CCYCDE =
    new CharacterField(message, HEADERSIZE + 117, 3, "E06CCYCDE");
    fields[19] = fieldE06ACCTYP =
    new CharacterField(message, HEADERSIZE + 120, 4, "E06ACCTYP");
    fields[20] = fieldE06PROCDE =
    new CharacterField(message, HEADERSIZE + 124, 4, "E06PROCDE");
    fields[21] = fieldE06PRIAMN =
    new DecimalField(message, HEADERSIZE + 128, 17, 2, "E06PRIAMN");
    fields[22] = fieldE06INTAMN =
    new DecimalField(message, HEADERSIZE + 145, 17, 2, "E06INTAMN");
    fields[23] = fieldE06OTHAMN =
    new DecimalField(message, HEADERSIZE + 162, 17, 2, "E06OTHAMN");
    fields[24] = fieldE06TOTAMN =
    new DecimalField(message, HEADERSIZE + 179, 17, 2, "E06TOTAMN");
    fields[25] = fieldE06EQVPRI =
    new DecimalField(message, HEADERSIZE + 196, 17, 2, "E06EQVPRI");
    fields[26] = fieldE06EQVINT =
    new DecimalField(message, HEADERSIZE + 213, 17, 2, "E06EQVINT");
    fields[27] = fieldE06EQVOTH =
    new DecimalField(message, HEADERSIZE + 230, 17, 2, "E06EQVOTH");
    fields[28] = fieldE06EQVAMN =
    new DecimalField(message, HEADERSIZE + 247, 17, 2, "E06EQVAMN");
    fields[29] = fieldE06ACCSTS =
    new CharacterField(message, HEADERSIZE + 264, 15, "E06ACCSTS");
    fields[30] = fieldE06OPEDT1 =
    new DecimalField(message, HEADERSIZE + 279, 3, 0, "E06OPEDT1");
    fields[31] = fieldE06OPEDT2 =
    new DecimalField(message, HEADERSIZE + 282, 3, 0, "E06OPEDT2");
    fields[32] = fieldE06OPEDT3 =
    new DecimalField(message, HEADERSIZE + 285, 3, 0, "E06OPEDT3");
    fields[33] = fieldE06OFFICR =
    new CharacterField(message, HEADERSIZE + 288, 4, "E06OFFICR");
    fields[34] = fieldE06PRODSC =
    new CharacterField(message, HEADERSIZE + 292, 35, "E06PRODSC");
    fields[35] = fieldE06MATDT1 =
    new DecimalField(message, HEADERSIZE + 327, 3, 0, "E06MATDT1");
    fields[36] = fieldE06MATDT2 =
    new DecimalField(message, HEADERSIZE + 330, 3, 0, "E06MATDT2");
    fields[37] = fieldE06MATDT3 =
    new DecimalField(message, HEADERSIZE + 333, 3, 0, "E06MATDT3");
    fields[38] = fieldE06DEAROC =
    new CharacterField(message, HEADERSIZE + 336, 1, "E06DEAROC");
    fields[39] = fieldE06DEARAC =
    new DecimalField(message, HEADERSIZE + 337, 13, 0, "E06DEARAC");
    fields[40] = fieldE06DEATRM =
    new DecimalField(message, HEADERSIZE + 350, 5, 0, "E06DEATRM");
    fields[41] = fieldE06DEATRC =
    new CharacterField(message, HEADERSIZE + 355, 1, "E06DEATRC");
    fields[42] = fieldE06INTRTE =
    new DecimalField(message, HEADERSIZE + 356, 11, 6, "E06INTRTE");
    fields[43] = fieldE06NUMREC =
    new DecimalField(message, HEADERSIZE + 367, 8, 0, "E06NUMREC");
    fields[44] = fieldE06INDOPE =
    new CharacterField(message, HEADERSIZE + 375, 1, "E06INDOPE");

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
  * Set field H06USERID using a String value.
  */
  public void setH06USERID(String newvalue)
  {
    fieldH06USERID.setString(newvalue);
  }

  /**
  * Get value of field H06USERID as a String.
  */
  public String getH06USERID()
  {
    return fieldH06USERID.getString();
  }

  /**
  * Set field H06PROGRM using a String value.
  */
  public void setH06PROGRM(String newvalue)
  {
    fieldH06PROGRM.setString(newvalue);
  }

  /**
  * Get value of field H06PROGRM as a String.
  */
  public String getH06PROGRM()
  {
    return fieldH06PROGRM.getString();
  }

  /**
  * Set field H06TIMSYS using a String value.
  */
  public void setH06TIMSYS(String newvalue)
  {
    fieldH06TIMSYS.setString(newvalue);
  }

  /**
  * Get value of field H06TIMSYS as a String.
  */
  public String getH06TIMSYS()
  {
    return fieldH06TIMSYS.getString();
  }

  /**
  * Set field H06SCRCOD using a String value.
  */
  public void setH06SCRCOD(String newvalue)
  {
    fieldH06SCRCOD.setString(newvalue);
  }

  /**
  * Get value of field H06SCRCOD as a String.
  */
  public String getH06SCRCOD()
  {
    return fieldH06SCRCOD.getString();
  }

  /**
  * Set field H06OPECOD using a String value.
  */
  public void setH06OPECOD(String newvalue)
  {
    fieldH06OPECOD.setString(newvalue);
  }

  /**
  * Get value of field H06OPECOD as a String.
  */
  public String getH06OPECOD()
  {
    return fieldH06OPECOD.getString();
  }

  /**
  * Set field H06FLGMAS using a String value.
  */
  public void setH06FLGMAS(String newvalue)
  {
    fieldH06FLGMAS.setString(newvalue);
  }

  /**
  * Get value of field H06FLGMAS as a String.
  */
  public String getH06FLGMAS()
  {
    return fieldH06FLGMAS.getString();
  }

  /**
  * Set field H06FLGWK1 using a String value.
  */
  public void setH06FLGWK1(String newvalue)
  {
    fieldH06FLGWK1.setString(newvalue);
  }

  /**
  * Get value of field H06FLGWK1 as a String.
  */
  public String getH06FLGWK1()
  {
    return fieldH06FLGWK1.getString();
  }

  /**
  * Set field H06FLGWK2 using a String value.
  */
  public void setH06FLGWK2(String newvalue)
  {
    fieldH06FLGWK2.setString(newvalue);
  }

  /**
  * Get value of field H06FLGWK2 as a String.
  */
  public String getH06FLGWK2()
  {
    return fieldH06FLGWK2.getString();
  }

  /**
  * Set field H06FLGWK3 using a String value.
  */
  public void setH06FLGWK3(String newvalue)
  {
    fieldH06FLGWK3.setString(newvalue);
  }

  /**
  * Get value of field H06FLGWK3 as a String.
  */
  public String getH06FLGWK3()
  {
    return fieldH06FLGWK3.getString();
  }

  /**
  * Set field E06SELCUN using a String value.
  */
  public void setE06SELCUN(String newvalue)
  {
    fieldE06SELCUN.setString(newvalue);
  }

  /**
  * Get value of field E06SELCUN as a String.
  */
  public String getE06SELCUN()
  {
    return fieldE06SELCUN.getString();
  }

  /**
  * Set numeric field E06SELCUN using a BigDecimal value.
  */
  public void setE06SELCUN(BigDecimal newvalue)
  {
    fieldE06SELCUN.setBigDecimal(newvalue);
  }

  /**
  * Return value of numeric field E06SELCUN as a BigDecimal.
  */
  public BigDecimal getBigDecimalE06SELCUN()
  {
    return fieldE06SELCUN.getBigDecimal();
  }

  /**
  * Set field E06CUSNA1 using a String value.
  */
  public void setE06CUSNA1(String newvalue)
  {
    fieldE06CUSNA1.setString(newvalue);
  }

  /**
  * Get value of field E06CUSNA1 as a String.
  */
  public String getE06CUSNA1()
  {
    return fieldE06CUSNA1.getString();
  }

  /**
  * Set field E06SELACD using a String value.
  */
  public void setE06SELACD(String newvalue)
  {
    fieldE06SELACD.setString(newvalue);
  }

  /**
  * Get value of field E06SELACD as a String.
  */
  public String getE06SELACD()
  {
    return fieldE06SELACD.getString();
  }

  /**
  * Set field E06SELFLG using a String value.
  */
  public void setE06SELFLG(String newvalue)
  {
    fieldE06SELFLG.setString(newvalue);
  }

  /**
  * Get value of field E06SELFLG as a String.
  */
  public String getE06SELFLG()
  {
    return fieldE06SELFLG.getString();
  }

  /**
  * Set field E06SELTYP using a String value.
  */
  public void setE06SELTYP(String newvalue)
  {
    fieldE06SELTYP.setString(newvalue);
  }

  /**
  * Get value of field E06SELTYP as a String.
  */
  public String getE06SELTYP()
  {
    return fieldE06SELTYP.getString();
  }

  /**
  * Set field E06SELPRO using a String value.
  */
  public void setE06SELPRO(String newvalue)
  {
    fieldE06SELPRO.setString(newvalue);
  }

  /**
  * Get value of field E06SELPRO as a String.
  */
  public String getE06SELPRO()
  {
    return fieldE06SELPRO.getString();
  }

  /**
  * Set field E06ACCNUM using a String value.
  */
  public void setE06ACCNUM(String newvalue)
  {
    fieldE06ACCNUM.setString(newvalue);
  }

  /**
  * Get value of field E06ACCNUM as a String.
  */
  public String getE06ACCNUM()
  {
    return fieldE06ACCNUM.getString();
  }

  /**
  * Set numeric field E06ACCNUM using a BigDecimal value.
  */
  public void setE06ACCNUM(BigDecimal newvalue)
  {
    fieldE06ACCNUM.setBigDecimal(newvalue);
  }

  /**
  * Return value of numeric field E06ACCNUM as a BigDecimal.
  */
  public BigDecimal getBigDecimalE06ACCNUM()
  {
    return fieldE06ACCNUM.getBigDecimal();
  }

  /**
  * Set field E06BNKNUM using a String value.
  */
  public void setE06BNKNUM(String newvalue)
  {
    fieldE06BNKNUM.setString(newvalue);
  }

  /**
  * Get value of field E06BNKNUM as a String.
  */
  public String getE06BNKNUM()
  {
    return fieldE06BNKNUM.getString();
  }

  /**
  * Set field E06BRNNUM using a String value.
  */
  public void setE06BRNNUM(String newvalue)
  {
    fieldE06BRNNUM.setString(newvalue);
  }

  /**
  * Get value of field E06BRNNUM as a String.
  */
  public String getE06BRNNUM()
  {
    return fieldE06BRNNUM.getString();
  }

  /**
  * Set numeric field E06BRNNUM using a BigDecimal value.
  */
  public void setE06BRNNUM(BigDecimal newvalue)
  {
    fieldE06BRNNUM.setBigDecimal(newvalue);
  }

  /**
  * Return value of numeric field E06BRNNUM as a BigDecimal.
  */
  public BigDecimal getBigDecimalE06BRNNUM()
  {
    return fieldE06BRNNUM.getBigDecimal();
  }

  /**
  * Set field E06CCYCDE using a String value.
  */
  public void setE06CCYCDE(String newvalue)
  {
    fieldE06CCYCDE.setString(newvalue);
  }

  /**
  * Get value of field E06CCYCDE as a String.
  */
  public String getE06CCYCDE()
  {
    return fieldE06CCYCDE.getString();
  }

  /**
  * Set field E06ACCTYP using a String value.
  */
  public void setE06ACCTYP(String newvalue)
  {
    fieldE06ACCTYP.setString(newvalue);
  }

  /**
  * Get value of field E06ACCTYP as a String.
  */
  public String getE06ACCTYP()
  {
    return fieldE06ACCTYP.getString();
  }

  /**
  * Set field E06PROCDE using a String value.
  */
  public void setE06PROCDE(String newvalue)
  {
    fieldE06PROCDE.setString(newvalue);
  }

  /**
  * Get value of field E06PROCDE as a String.
  */
  public String getE06PROCDE()
  {
    return fieldE06PROCDE.getString();
  }

  /**
  * Set field E06PRIAMN using a String value.
  */
  public void setE06PRIAMN(String newvalue)
  {
    fieldE06PRIAMN.setString(newvalue);
  }

  /**
  * Get value of field E06PRIAMN as a String.
  */
  public String getE06PRIAMN()
  {
    return fieldE06PRIAMN.getString();
  }

  /**
  * Set numeric field E06PRIAMN using a BigDecimal value.
  */
  public void setE06PRIAMN(BigDecimal newvalue)
  {
    fieldE06PRIAMN.setBigDecimal(newvalue);
  }

  /**
  * Return value of numeric field E06PRIAMN as a BigDecimal.
  */
  public BigDecimal getBigDecimalE06PRIAMN()
  {
    return fieldE06PRIAMN.getBigDecimal();
  }

  /**
  * Set field E06INTAMN using a String value.
  */
  public void setE06INTAMN(String newvalue)
  {
    fieldE06INTAMN.setString(newvalue);
  }

  /**
  * Get value of field E06INTAMN as a String.
  */
  public String getE06INTAMN()
  {
    return fieldE06INTAMN.getString();
  }

  /**
  * Set numeric field E06INTAMN using a BigDecimal value.
  */
  public void setE06INTAMN(BigDecimal newvalue)
  {
    fieldE06INTAMN.setBigDecimal(newvalue);
  }

  /**
  * Return value of numeric field E06INTAMN as a BigDecimal.
  */
  public BigDecimal getBigDecimalE06INTAMN()
  {
    return fieldE06INTAMN.getBigDecimal();
  }

  /**
  * Set field E06OTHAMN using a String value.
  */
  public void setE06OTHAMN(String newvalue)
  {
    fieldE06OTHAMN.setString(newvalue);
  }

  /**
  * Get value of field E06OTHAMN as a String.
  */
  public String getE06OTHAMN()
  {
    return fieldE06OTHAMN.getString();
  }

  /**
  * Set numeric field E06OTHAMN using a BigDecimal value.
  */
  public void setE06OTHAMN(BigDecimal newvalue)
  {
    fieldE06OTHAMN.setBigDecimal(newvalue);
  }

  /**
  * Return value of numeric field E06OTHAMN as a BigDecimal.
  */
  public BigDecimal getBigDecimalE06OTHAMN()
  {
    return fieldE06OTHAMN.getBigDecimal();
  }

  /**
  * Set field E06TOTAMN using a String value.
  */
  public void setE06TOTAMN(String newvalue)
  {
    fieldE06TOTAMN.setString(newvalue);
  }

  /**
  * Get value of field E06TOTAMN as a String.
  */
  public String getE06TOTAMN()
  {
    return fieldE06TOTAMN.getString();
  }

  /**
  * Set numeric field E06TOTAMN using a BigDecimal value.
  */
  public void setE06TOTAMN(BigDecimal newvalue)
  {
    fieldE06TOTAMN.setBigDecimal(newvalue);
  }

  /**
  * Return value of numeric field E06TOTAMN as a BigDecimal.
  */
  public BigDecimal getBigDecimalE06TOTAMN()
  {
    return fieldE06TOTAMN.getBigDecimal();
  }

  /**
  * Set field E06EQVPRI using a String value.
  */
  public void setE06EQVPRI(String newvalue)
  {
    fieldE06EQVPRI.setString(newvalue);
  }

  /**
  * Get value of field E06EQVPRI as a String.
  */
  public String getE06EQVPRI()
  {
    return fieldE06EQVPRI.getString();
  }

  /**
  * Set numeric field E06EQVPRI using a BigDecimal value.
  */
  public void setE06EQVPRI(BigDecimal newvalue)
  {
    fieldE06EQVPRI.setBigDecimal(newvalue);
  }

  /**
  * Return value of numeric field E06EQVPRI as a BigDecimal.
  */
  public BigDecimal getBigDecimalE06EQVPRI()
  {
    return fieldE06EQVPRI.getBigDecimal();
  }

  /**
  * Set field E06EQVINT using a String value.
  */
  public void setE06EQVINT(String newvalue)
  {
    fieldE06EQVINT.setString(newvalue);
  }

  /**
  * Get value of field E06EQVINT as a String.
  */
  public String getE06EQVINT()
  {
    return fieldE06EQVINT.getString();
  }

  /**
  * Set numeric field E06EQVINT using a BigDecimal value.
  */
  public void setE06EQVINT(BigDecimal newvalue)
  {
    fieldE06EQVINT.setBigDecimal(newvalue);
  }

  /**
  * Return value of numeric field E06EQVINT as a BigDecimal.
  */
  public BigDecimal getBigDecimalE06EQVINT()
  {
    return fieldE06EQVINT.getBigDecimal();
  }

  /**
  * Set field E06EQVOTH using a String value.
  */
  public void setE06EQVOTH(String newvalue)
  {
    fieldE06EQVOTH.setString(newvalue);
  }

  /**
  * Get value of field E06EQVOTH as a String.
  */
  public String getE06EQVOTH()
  {
    return fieldE06EQVOTH.getString();
  }

  /**
  * Set numeric field E06EQVOTH using a BigDecimal value.
  */
  public void setE06EQVOTH(BigDecimal newvalue)
  {
    fieldE06EQVOTH.setBigDecimal(newvalue);
  }

  /**
  * Return value of numeric field E06EQVOTH as a BigDecimal.
  */
  public BigDecimal getBigDecimalE06EQVOTH()
  {
    return fieldE06EQVOTH.getBigDecimal();
  }

  /**
  * Set field E06EQVAMN using a String value.
  */
  public void setE06EQVAMN(String newvalue)
  {
    fieldE06EQVAMN.setString(newvalue);
  }

  /**
  * Get value of field E06EQVAMN as a String.
  */
  public String getE06EQVAMN()
  {
    return fieldE06EQVAMN.getString();
  }

  /**
  * Set numeric field E06EQVAMN using a BigDecimal value.
  */
  public void setE06EQVAMN(BigDecimal newvalue)
  {
    fieldE06EQVAMN.setBigDecimal(newvalue);
  }

  /**
  * Return value of numeric field E06EQVAMN as a BigDecimal.
  */
  public BigDecimal getBigDecimalE06EQVAMN()
  {
    return fieldE06EQVAMN.getBigDecimal();
  }

  /**
  * Set field E06ACCSTS using a String value.
  */
  public void setE06ACCSTS(String newvalue)
  {
    fieldE06ACCSTS.setString(newvalue);
  }

  /**
  * Get value of field E06ACCSTS as a String.
  */
  public String getE06ACCSTS()
  {
    return fieldE06ACCSTS.getString();
  }

  /**
  * Set field E06OPEDT1 using a String value.
  */
  public void setE06OPEDT1(String newvalue)
  {
    fieldE06OPEDT1.setString(newvalue);
  }

  /**
  * Get value of field E06OPEDT1 as a String.
  */
  public String getE06OPEDT1()
  {
    return fieldE06OPEDT1.getString();
  }

  /**
  * Set numeric field E06OPEDT1 using a BigDecimal value.
  */
  public void setE06OPEDT1(BigDecimal newvalue)
  {
    fieldE06OPEDT1.setBigDecimal(newvalue);
  }

  /**
  * Return value of numeric field E06OPEDT1 as a BigDecimal.
  */
  public BigDecimal getBigDecimalE06OPEDT1()
  {
    return fieldE06OPEDT1.getBigDecimal();
  }

  /**
  * Set field E06OPEDT2 using a String value.
  */
  public void setE06OPEDT2(String newvalue)
  {
    fieldE06OPEDT2.setString(newvalue);
  }

  /**
  * Get value of field E06OPEDT2 as a String.
  */
  public String getE06OPEDT2()
  {
    return fieldE06OPEDT2.getString();
  }

  /**
  * Set numeric field E06OPEDT2 using a BigDecimal value.
  */
  public void setE06OPEDT2(BigDecimal newvalue)
  {
    fieldE06OPEDT2.setBigDecimal(newvalue);
  }

  /**
  * Return value of numeric field E06OPEDT2 as a BigDecimal.
  */
  public BigDecimal getBigDecimalE06OPEDT2()
  {
    return fieldE06OPEDT2.getBigDecimal();
  }

  /**
  * Set field E06OPEDT3 using a String value.
  */
  public void setE06OPEDT3(String newvalue)
  {
    fieldE06OPEDT3.setString(newvalue);
  }

  /**
  * Get value of field E06OPEDT3 as a String.
  */
  public String getE06OPEDT3()
  {
    return fieldE06OPEDT3.getString();
  }

  /**
  * Set numeric field E06OPEDT3 using a BigDecimal value.
  */
  public void setE06OPEDT3(BigDecimal newvalue)
  {
    fieldE06OPEDT3.setBigDecimal(newvalue);
  }

  /**
  * Return value of numeric field E06OPEDT3 as a BigDecimal.
  */
  public BigDecimal getBigDecimalE06OPEDT3()
  {
    return fieldE06OPEDT3.getBigDecimal();
  }

  /**
  * Set field E06OFFICR using a String value.
  */
  public void setE06OFFICR(String newvalue)
  {
    fieldE06OFFICR.setString(newvalue);
  }

  /**
  * Get value of field E06OFFICR as a String.
  */
  public String getE06OFFICR()
  {
    return fieldE06OFFICR.getString();
  }

  /**
  * Set field E06PRODSC using a String value.
  */
  public void setE06PRODSC(String newvalue)
  {
    fieldE06PRODSC.setString(newvalue);
  }

  /**
  * Get value of field E06PRODSC as a String.
  */
  public String getE06PRODSC()
  {
    return fieldE06PRODSC.getString();
  }

  /**
  * Set field E06MATDT1 using a String value.
  */
  public void setE06MATDT1(String newvalue)
  {
    fieldE06MATDT1.setString(newvalue);
  }

  /**
  * Get value of field E06MATDT1 as a String.
  */
  public String getE06MATDT1()
  {
    return fieldE06MATDT1.getString();
  }

  /**
  * Set numeric field E06MATDT1 using a BigDecimal value.
  */
  public void setE06MATDT1(BigDecimal newvalue)
  {
    fieldE06MATDT1.setBigDecimal(newvalue);
  }

  /**
  * Return value of numeric field E06MATDT1 as a BigDecimal.
  */
  public BigDecimal getBigDecimalE06MATDT1()
  {
    return fieldE06MATDT1.getBigDecimal();
  }

  /**
  * Set field E06MATDT2 using a String value.
  */
  public void setE06MATDT2(String newvalue)
  {
    fieldE06MATDT2.setString(newvalue);
  }

  /**
  * Get value of field E06MATDT2 as a String.
  */
  public String getE06MATDT2()
  {
    return fieldE06MATDT2.getString();
  }

  /**
  * Set numeric field E06MATDT2 using a BigDecimal value.
  */
  public void setE06MATDT2(BigDecimal newvalue)
  {
    fieldE06MATDT2.setBigDecimal(newvalue);
  }

  /**
  * Return value of numeric field E06MATDT2 as a BigDecimal.
  */
  public BigDecimal getBigDecimalE06MATDT2()
  {
    return fieldE06MATDT2.getBigDecimal();
  }

  /**
  * Set field E06MATDT3 using a String value.
  */
  public void setE06MATDT3(String newvalue)
  {
    fieldE06MATDT3.setString(newvalue);
  }

  /**
  * Get value of field E06MATDT3 as a String.
  */
  public String getE06MATDT3()
  {
    return fieldE06MATDT3.getString();
  }

  /**
  * Set numeric field E06MATDT3 using a BigDecimal value.
  */
  public void setE06MATDT3(BigDecimal newvalue)
  {
    fieldE06MATDT3.setBigDecimal(newvalue);
  }

  /**
  * Return value of numeric field E06MATDT3 as a BigDecimal.
  */
  public BigDecimal getBigDecimalE06MATDT3()
  {
    return fieldE06MATDT3.getBigDecimal();
  }

  /**
  * Set field E06DEAROC using a String value.
  */
  public void setE06DEAROC(String newvalue)
  {
    fieldE06DEAROC.setString(newvalue);
  }

  /**
  * Get value of field E06DEAROC as a String.
  */
  public String getE06DEAROC()
  {
    return fieldE06DEAROC.getString();
  }

  /**
  * Set field E06DEARAC using a String value.
  */
  public void setE06DEARAC(String newvalue)
  {
    fieldE06DEARAC.setString(newvalue);
  }

  /**
  * Get value of field E06DEARAC as a String.
  */
  public String getE06DEARAC()
  {
    return fieldE06DEARAC.getString();
  }

  /**
  * Set numeric field E06DEARAC using a BigDecimal value.
  */
  public void setE06DEARAC(BigDecimal newvalue)
  {
    fieldE06DEARAC.setBigDecimal(newvalue);
  }

  /**
  * Return value of numeric field E06DEARAC as a BigDecimal.
  */
  public BigDecimal getBigDecimalE06DEARAC()
  {
    return fieldE06DEARAC.getBigDecimal();
  }

  /**
  * Set field E06DEATRM using a String value.
  */
  public void setE06DEATRM(String newvalue)
  {
    fieldE06DEATRM.setString(newvalue);
  }

  /**
  * Get value of field E06DEATRM as a String.
  */
  public String getE06DEATRM()
  {
    return fieldE06DEATRM.getString();
  }

  /**
  * Set numeric field E06DEATRM using a BigDecimal value.
  */
  public void setE06DEATRM(BigDecimal newvalue)
  {
    fieldE06DEATRM.setBigDecimal(newvalue);
  }

  /**
  * Return value of numeric field E06DEATRM as a BigDecimal.
  */
  public BigDecimal getBigDecimalE06DEATRM()
  {
    return fieldE06DEATRM.getBigDecimal();
  }

  /**
  * Set field E06DEATRC using a String value.
  */
  public void setE06DEATRC(String newvalue)
  {
    fieldE06DEATRC.setString(newvalue);
  }

  /**
  * Get value of field E06DEATRC as a String.
  */
  public String getE06DEATRC()
  {
    return fieldE06DEATRC.getString();
  }

  /**
  * Set field E06INTRTE using a String value.
  */
  public void setE06INTRTE(String newvalue)
  {
    fieldE06INTRTE.setString(newvalue);
  }

  /**
  * Get value of field E06INTRTE as a String.
  */
  public String getE06INTRTE()
  {
    return fieldE06INTRTE.getString();
  }

  /**
  * Set numeric field E06INTRTE using a BigDecimal value.
  */
  public void setE06INTRTE(BigDecimal newvalue)
  {
    fieldE06INTRTE.setBigDecimal(newvalue);
  }

  /**
  * Return value of numeric field E06INTRTE as a BigDecimal.
  */
  public BigDecimal getBigDecimalE06INTRTE()
  {
    return fieldE06INTRTE.getBigDecimal();
  }

  /**
  * Set field E06NUMREC using a String value.
  */
  public void setE06NUMREC(String newvalue)
  {
    fieldE06NUMREC.setString(newvalue);
  }

  /**
  * Get value of field E06NUMREC as a String.
  */
  public String getE06NUMREC()
  {
    return fieldE06NUMREC.getString();
  }

  /**
  * Set numeric field E06NUMREC using a BigDecimal value.
  */
  public void setE06NUMREC(BigDecimal newvalue)
  {
    fieldE06NUMREC.setBigDecimal(newvalue);
  }

  /**
  * Return value of numeric field E06NUMREC as a BigDecimal.
  */
  public BigDecimal getBigDecimalE06NUMREC()
  {
    return fieldE06NUMREC.getBigDecimal();
  }

  /**
  * Set field E06INDOPE using a String value.
  */
  public void setE06INDOPE(String newvalue)
  {
    fieldE06INDOPE.setString(newvalue);
  }

  /**
  * Get value of field E06INDOPE as a String.
  */
  public String getE06INDOPE()
  {
    return fieldE06INDOPE.getString();
  }

}
