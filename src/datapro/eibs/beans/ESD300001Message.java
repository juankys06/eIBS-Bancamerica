package datapro.eibs.beans;

import datapro.eibs.sockets.*;
import java.io.*;
import java.math.*;

import java.util.*;

/**
* Class generated by AS/400 CRTCLASS command from ESD300001 physical file definition.
* 
* File level identifier is 1070612120116.
* Record format level identifier is 51EA161BB631C.
*/

public class ESD300001Message extends MessageRecord
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
                                     "E01OLDBNK",
                                     "E01OLDBRN",
                                     "E01OLDCCY",
                                     "E01OLDGLN",
                                     "E01OLDACC",
                                     "E01OLDACD",
                                     "E01OLDATY",
                                     "E01OLDPRD",
                                     "E01NEWBNK",
                                     "E01NEWBRN",
                                     "E01NEWCCY",
                                     "E01NEWGLN",
                                     "E01NEWPRD",
                                     "E01NEWATY",
                                     "E01CUSNUM",
                                     "E01CUSNA1",
                                     "E01OBNKNA",
                                     "E01OBRNNA",
                                     "E01OGLNDE",
                                     "E01OPRDNA",
                                     "E01OATYNA",
                                     "E01NBNKNA",
                                     "E01NBRNNA",
                                     "E01NGLNDE",
                                     "E01NPRDNA",
                                     "E01NATYNA",
                                     "E01AMOUNT"
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
                                   "E01OLDBNK",
                                   "E01OLDBRN",
                                   "E01OLDCCY",
                                   "E01OLDGLN",
                                   "E01OLDACC",
                                   "E01OLDACD",
                                   "E01OLDATY",
                                   "E01OLDPRD",
                                   "E01NEWBNK",
                                   "E01NEWBRN",
                                   "E01NEWCCY",
                                   "E01NEWGLN",
                                   "E01NEWPRD",
                                   "E01NEWATY",
                                   "E01CUSNUM",
                                   "E01CUSNA1",
                                   "E01OBNKNA",
                                   "E01OBRNNA",
                                   "E01OGLNDE",
                                   "E01OPRDNA",
                                   "E01OATYNA",
                                   "E01NBNKNA",
                                   "E01NBRNNA",
                                   "E01NGLNDE",
                                   "E01NPRDNA",
                                   "E01NATYNA",
                                   "E01AMOUNT"
                                 };
  final static String fid = "1070612120116";
  final static String rid = "51EA161BB631C";
  final static String fmtname = "ESD300001";
  final int FIELDCOUNT = 36;
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
  private CharacterField fieldE01OLDBNK = null;
  private DecimalField fieldE01OLDBRN = null;
  private CharacterField fieldE01OLDCCY = null;
  private DecimalField fieldE01OLDGLN = null;
  private DecimalField fieldE01OLDACC = null;
  private CharacterField fieldE01OLDACD = null;
  private CharacterField fieldE01OLDATY = null;
  private CharacterField fieldE01OLDPRD = null;
  private CharacterField fieldE01NEWBNK = null;
  private CharacterField fieldE01NEWBRN = null;
  private CharacterField fieldE01NEWCCY = null;
  private DecimalField fieldE01NEWGLN = null;
  private CharacterField fieldE01NEWPRD = null;
  private CharacterField fieldE01NEWATY = null;
  private DecimalField fieldE01CUSNUM = null;
  private CharacterField fieldE01CUSNA1 = null;
  private CharacterField fieldE01OBNKNA = null;
  private CharacterField fieldE01OBRNNA = null;
  private CharacterField fieldE01OGLNDE = null;
  private CharacterField fieldE01OPRDNA = null;
  private CharacterField fieldE01OATYNA = null;
  private CharacterField fieldE01NBNKNA = null;
  private CharacterField fieldE01NBRNNA = null;
  private CharacterField fieldE01NGLNDE = null;
  private CharacterField fieldE01NPRDNA = null;
  private CharacterField fieldE01NATYNA = null;
  private DecimalField fieldE01AMOUNT = null;

  /**
  * Constructor for ESD300001Message.
  */
  public ESD300001Message()
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
    recordsize = 586;
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
    fields[9] = fieldE01OLDBNK =
    new CharacterField(message, HEADERSIZE + 42, 2, "E01OLDBNK");
    fields[10] = fieldE01OLDBRN =
    new DecimalField(message, HEADERSIZE + 44, 4, 0, "E01OLDBRN");
    fields[11] = fieldE01OLDCCY =
    new CharacterField(message, HEADERSIZE + 48, 3, "E01OLDCCY");
    fields[12] = fieldE01OLDGLN =
    new DecimalField(message, HEADERSIZE + 51, 17, 0, "E01OLDGLN");
    fields[13] = fieldE01OLDACC =
    new DecimalField(message, HEADERSIZE + 68, 13, 0, "E01OLDACC");
    fields[14] = fieldE01OLDACD =
    new CharacterField(message, HEADERSIZE + 81, 2, "E01OLDACD");
    fields[15] = fieldE01OLDATY =
    new CharacterField(message, HEADERSIZE + 83, 4, "E01OLDATY");
    fields[16] = fieldE01OLDPRD =
    new CharacterField(message, HEADERSIZE + 87, 4, "E01OLDPRD");
    fields[17] = fieldE01NEWBNK =
    new CharacterField(message, HEADERSIZE + 91, 2, "E01NEWBNK");
    fields[18] = fieldE01NEWBRN =
    new CharacterField(message, HEADERSIZE + 93, 3, "E01NEWBRN");
    fields[19] = fieldE01NEWCCY =
    new CharacterField(message, HEADERSIZE + 96, 3, "E01NEWCCY");
    fields[20] = fieldE01NEWGLN =
    new DecimalField(message, HEADERSIZE + 99, 17, 0, "E01NEWGLN");
    fields[21] = fieldE01NEWPRD =
    new CharacterField(message, HEADERSIZE + 116, 4, "E01NEWPRD");
    fields[22] = fieldE01NEWATY =
    new CharacterField(message, HEADERSIZE + 120, 4, "E01NEWATY");
    fields[23] = fieldE01CUSNUM =
    new DecimalField(message, HEADERSIZE + 124, 10, 0, "E01CUSNUM");
    fields[24] = fieldE01CUSNA1 =
    new CharacterField(message, HEADERSIZE + 134, 45, "E01CUSNA1");
    fields[25] = fieldE01OBNKNA =
    new CharacterField(message, HEADERSIZE + 179, 35, "E01OBNKNA");
    fields[26] = fieldE01OBRNNA =
    new CharacterField(message, HEADERSIZE + 214, 35, "E01OBRNNA");
    fields[27] = fieldE01OGLNDE =
    new CharacterField(message, HEADERSIZE + 249, 35, "E01OGLNDE");
    fields[28] = fieldE01OPRDNA =
    new CharacterField(message, HEADERSIZE + 284, 55, "E01OPRDNA");
    fields[29] = fieldE01OATYNA =
    new CharacterField(message, HEADERSIZE + 339, 35, "E01OATYNA");
    fields[30] = fieldE01NBNKNA =
    new CharacterField(message, HEADERSIZE + 374, 35, "E01NBNKNA");
    fields[31] = fieldE01NBRNNA =
    new CharacterField(message, HEADERSIZE + 409, 35, "E01NBRNNA");
    fields[32] = fieldE01NGLNDE =
    new CharacterField(message, HEADERSIZE + 444, 35, "E01NGLNDE");
    fields[33] = fieldE01NPRDNA =
    new CharacterField(message, HEADERSIZE + 479, 55, "E01NPRDNA");
    fields[34] = fieldE01NATYNA =
    new CharacterField(message, HEADERSIZE + 534, 35, "E01NATYNA");
    fields[35] = fieldE01AMOUNT =
    new DecimalField(message, HEADERSIZE + 569, 17, 2, "E01AMOUNT");

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
  * Set field E01OLDBNK using a String value.
  */
  public void setE01OLDBNK(String newvalue)
  {
    fieldE01OLDBNK.setString(newvalue);
  }

  /**
  * Get value of field E01OLDBNK as a String.
  */
  public String getE01OLDBNK()
  {
    return fieldE01OLDBNK.getString();
  }

  /**
  * Set field E01OLDBRN using a String value.
  */
  public void setE01OLDBRN(String newvalue)
  {
    fieldE01OLDBRN.setString(newvalue);
  }

  /**
  * Get value of field E01OLDBRN as a String.
  */
  public String getE01OLDBRN()
  {
    return fieldE01OLDBRN.getString();
  }

  /**
  * Set numeric field E01OLDBRN using a BigDecimal value.
  */
  public void setE01OLDBRN(BigDecimal newvalue)
  {
    fieldE01OLDBRN.setBigDecimal(newvalue);
  }

  /**
  * Return value of numeric field E01OLDBRN as a BigDecimal.
  */
  public BigDecimal getBigDecimalE01OLDBRN()
  {
    return fieldE01OLDBRN.getBigDecimal();
  }

  /**
  * Set field E01OLDCCY using a String value.
  */
  public void setE01OLDCCY(String newvalue)
  {
    fieldE01OLDCCY.setString(newvalue);
  }

  /**
  * Get value of field E01OLDCCY as a String.
  */
  public String getE01OLDCCY()
  {
    return fieldE01OLDCCY.getString();
  }

  /**
  * Set field E01OLDGLN using a String value.
  */
  public void setE01OLDGLN(String newvalue)
  {
    fieldE01OLDGLN.setString(newvalue);
  }

  /**
  * Get value of field E01OLDGLN as a String.
  */
  public String getE01OLDGLN()
  {
    return fieldE01OLDGLN.getString();
  }

  /**
  * Set numeric field E01OLDGLN using a BigDecimal value.
  */
  public void setE01OLDGLN(BigDecimal newvalue)
  {
    fieldE01OLDGLN.setBigDecimal(newvalue);
  }

  /**
  * Return value of numeric field E01OLDGLN as a BigDecimal.
  */
  public BigDecimal getBigDecimalE01OLDGLN()
  {
    return fieldE01OLDGLN.getBigDecimal();
  }

  /**
  * Set field E01OLDACC using a String value.
  */
  public void setE01OLDACC(String newvalue)
  {
    fieldE01OLDACC.setString(newvalue);
  }

  /**
  * Get value of field E01OLDACC as a String.
  */
  public String getE01OLDACC()
  {
    return fieldE01OLDACC.getString();
  }

  /**
  * Set numeric field E01OLDACC using a BigDecimal value.
  */
  public void setE01OLDACC(BigDecimal newvalue)
  {
    fieldE01OLDACC.setBigDecimal(newvalue);
  }

  /**
  * Return value of numeric field E01OLDACC as a BigDecimal.
  */
  public BigDecimal getBigDecimalE01OLDACC()
  {
    return fieldE01OLDACC.getBigDecimal();
  }

  /**
  * Set field E01OLDACD using a String value.
  */
  public void setE01OLDACD(String newvalue)
  {
    fieldE01OLDACD.setString(newvalue);
  }

  /**
  * Get value of field E01OLDACD as a String.
  */
  public String getE01OLDACD()
  {
    return fieldE01OLDACD.getString();
  }

  /**
  * Set field E01OLDATY using a String value.
  */
  public void setE01OLDATY(String newvalue)
  {
    fieldE01OLDATY.setString(newvalue);
  }

  /**
  * Get value of field E01OLDATY as a String.
  */
  public String getE01OLDATY()
  {
    return fieldE01OLDATY.getString();
  }

  /**
  * Set field E01OLDPRD using a String value.
  */
  public void setE01OLDPRD(String newvalue)
  {
    fieldE01OLDPRD.setString(newvalue);
  }

  /**
  * Get value of field E01OLDPRD as a String.
  */
  public String getE01OLDPRD()
  {
    return fieldE01OLDPRD.getString();
  }

  /**
  * Set field E01NEWBNK using a String value.
  */
  public void setE01NEWBNK(String newvalue)
  {
    fieldE01NEWBNK.setString(newvalue);
  }

  /**
  * Get value of field E01NEWBNK as a String.
  */
  public String getE01NEWBNK()
  {
    return fieldE01NEWBNK.getString();
  }

  /**
  * Set field E01NEWBRN using a String value.
  */
  public void setE01NEWBRN(String newvalue)
  {
    fieldE01NEWBRN.setString(newvalue);
  }

  /**
  * Get value of field E01NEWBRN as a String.
  */
  public String getE01NEWBRN()
  {
    return fieldE01NEWBRN.getString();
  }

  /**
  * Set field E01NEWCCY using a String value.
  */
  public void setE01NEWCCY(String newvalue)
  {
    fieldE01NEWCCY.setString(newvalue);
  }

  /**
  * Get value of field E01NEWCCY as a String.
  */
  public String getE01NEWCCY()
  {
    return fieldE01NEWCCY.getString();
  }

  /**
  * Set field E01NEWGLN using a String value.
  */
  public void setE01NEWGLN(String newvalue)
  {
    fieldE01NEWGLN.setString(newvalue);
  }

  /**
  * Get value of field E01NEWGLN as a String.
  */
  public String getE01NEWGLN()
  {
    return fieldE01NEWGLN.getString();
  }

  /**
  * Set numeric field E01NEWGLN using a BigDecimal value.
  */
  public void setE01NEWGLN(BigDecimal newvalue)
  {
    fieldE01NEWGLN.setBigDecimal(newvalue);
  }

  /**
  * Return value of numeric field E01NEWGLN as a BigDecimal.
  */
  public BigDecimal getBigDecimalE01NEWGLN()
  {
    return fieldE01NEWGLN.getBigDecimal();
  }

  /**
  * Set field E01NEWPRD using a String value.
  */
  public void setE01NEWPRD(String newvalue)
  {
    fieldE01NEWPRD.setString(newvalue);
  }

  /**
  * Get value of field E01NEWPRD as a String.
  */
  public String getE01NEWPRD()
  {
    return fieldE01NEWPRD.getString();
  }

  /**
  * Set field E01NEWATY using a String value.
  */
  public void setE01NEWATY(String newvalue)
  {
    fieldE01NEWATY.setString(newvalue);
  }

  /**
  * Get value of field E01NEWATY as a String.
  */
  public String getE01NEWATY()
  {
    return fieldE01NEWATY.getString();
  }

  /**
  * Set field E01CUSNUM using a String value.
  */
  public void setE01CUSNUM(String newvalue)
  {
    fieldE01CUSNUM.setString(newvalue);
  }

  /**
  * Get value of field E01CUSNUM as a String.
  */
  public String getE01CUSNUM()
  {
    return fieldE01CUSNUM.getString();
  }

  /**
  * Set numeric field E01CUSNUM using a BigDecimal value.
  */
  public void setE01CUSNUM(BigDecimal newvalue)
  {
    fieldE01CUSNUM.setBigDecimal(newvalue);
  }

  /**
  * Return value of numeric field E01CUSNUM as a BigDecimal.
  */
  public BigDecimal getBigDecimalE01CUSNUM()
  {
    return fieldE01CUSNUM.getBigDecimal();
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
  * Set field E01OBNKNA using a String value.
  */
  public void setE01OBNKNA(String newvalue)
  {
    fieldE01OBNKNA.setString(newvalue);
  }

  /**
  * Get value of field E01OBNKNA as a String.
  */
  public String getE01OBNKNA()
  {
    return fieldE01OBNKNA.getString();
  }

  /**
  * Set field E01OBRNNA using a String value.
  */
  public void setE01OBRNNA(String newvalue)
  {
    fieldE01OBRNNA.setString(newvalue);
  }

  /**
  * Get value of field E01OBRNNA as a String.
  */
  public String getE01OBRNNA()
  {
    return fieldE01OBRNNA.getString();
  }

  /**
  * Set field E01OGLNDE using a String value.
  */
  public void setE01OGLNDE(String newvalue)
  {
    fieldE01OGLNDE.setString(newvalue);
  }

  /**
  * Get value of field E01OGLNDE as a String.
  */
  public String getE01OGLNDE()
  {
    return fieldE01OGLNDE.getString();
  }

  /**
  * Set field E01OPRDNA using a String value.
  */
  public void setE01OPRDNA(String newvalue)
  {
    fieldE01OPRDNA.setString(newvalue);
  }

  /**
  * Get value of field E01OPRDNA as a String.
  */
  public String getE01OPRDNA()
  {
    return fieldE01OPRDNA.getString();
  }

  /**
  * Set field E01OATYNA using a String value.
  */
  public void setE01OATYNA(String newvalue)
  {
    fieldE01OATYNA.setString(newvalue);
  }

  /**
  * Get value of field E01OATYNA as a String.
  */
  public String getE01OATYNA()
  {
    return fieldE01OATYNA.getString();
  }

  /**
  * Set field E01NBNKNA using a String value.
  */
  public void setE01NBNKNA(String newvalue)
  {
    fieldE01NBNKNA.setString(newvalue);
  }

  /**
  * Get value of field E01NBNKNA as a String.
  */
  public String getE01NBNKNA()
  {
    return fieldE01NBNKNA.getString();
  }

  /**
  * Set field E01NBRNNA using a String value.
  */
  public void setE01NBRNNA(String newvalue)
  {
    fieldE01NBRNNA.setString(newvalue);
  }

  /**
  * Get value of field E01NBRNNA as a String.
  */
  public String getE01NBRNNA()
  {
    return fieldE01NBRNNA.getString();
  }

  /**
  * Set field E01NGLNDE using a String value.
  */
  public void setE01NGLNDE(String newvalue)
  {
    fieldE01NGLNDE.setString(newvalue);
  }

  /**
  * Get value of field E01NGLNDE as a String.
  */
  public String getE01NGLNDE()
  {
    return fieldE01NGLNDE.getString();
  }

  /**
  * Set field E01NPRDNA using a String value.
  */
  public void setE01NPRDNA(String newvalue)
  {
    fieldE01NPRDNA.setString(newvalue);
  }

  /**
  * Get value of field E01NPRDNA as a String.
  */
  public String getE01NPRDNA()
  {
    return fieldE01NPRDNA.getString();
  }

  /**
  * Set field E01NATYNA using a String value.
  */
  public void setE01NATYNA(String newvalue)
  {
    fieldE01NATYNA.setString(newvalue);
  }

  /**
  * Get value of field E01NATYNA as a String.
  */
  public String getE01NATYNA()
  {
    return fieldE01NATYNA.getString();
  }

  /**
  * Set field E01AMOUNT using a String value.
  */
  public void setE01AMOUNT(String newvalue)
  {
    fieldE01AMOUNT.setString(newvalue);
  }

  /**
  * Get value of field E01AMOUNT as a String.
  */
  public String getE01AMOUNT()
  {
    return fieldE01AMOUNT.getString();
  }

  /**
  * Set numeric field E01AMOUNT using a BigDecimal value.
  */
  public void setE01AMOUNT(BigDecimal newvalue)
  {
    fieldE01AMOUNT.setBigDecimal(newvalue);
  }

  /**
  * Return value of numeric field E01AMOUNT as a BigDecimal.
  */
  public BigDecimal getBigDecimalE01AMOUNT()
  {
    return fieldE01AMOUNT.getBigDecimal();
  }

}
