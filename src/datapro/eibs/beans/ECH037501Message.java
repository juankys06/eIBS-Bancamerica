package datapro.eibs.beans;

import datapro.eibs.sockets.*;
import java.io.*;
import java.math.*;

import java.util.*;

/**
* Class generated by AS/400 CRTCLASS command from ECH037501 physical file definition.
* 
* File level identifier is 1030716174746.
* Record format level identifier is 3A3521C5A3D00.
*/

public class ECH037501Message extends MessageRecord
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
                                     "E01CHPACC",
                                     "E01CHPCCY",
                                     "E01CHPACD",
                                     "E01CHPPRO",
                                     "E01CHPCUN",
                                     "E01CHPNA1",
                                     "E01CHPOFC",
                                     "E01DSCOFC",
                                     "E01CHPNTC",
                                     "E01CHPTCB",
                                     "E01CHPMSK",
                                     "E01CHPASK",
                                     "E01CHPCBC",
                                     "E01CHPDT1",
                                     "E01CHPDT2",
                                     "E01CHPDT3",
                                     "E01CHPST0",
                                     "E01CHPST1",
                                     "E01CHPST2",
                                     "E01CHPST3",
                                     "E01CHPST4",
                                     "E01CHPST5",
                                     "E01CHPST6",
                                     "E01CHPST7",
                                     "E01CHPST8",
                                     "E01CHPST9",
                                     "E01CHPCON",
                                     "E01CHPVAL",
                                     "E01CHPN01",
                                     "E01CHPN02",
                                     "E01CHPSTA",
                                     "E01CHPRE1",
                                     "E01CHPDBR"
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
                                   "E01CHPACC",
                                   "E01CHPCCY",
                                   "E01CHPACD",
                                   "E01CHPPRO",
                                   "E01CHPCUN",
                                   "E01CHPNA1",
                                   "E01CHPOFC",
                                   "E01DSCOFC",
                                   "E01CHPNTC",
                                   "E01CHPTCB",
                                   "E01CHPMSK",
                                   "E01CHPASK",
                                   "E01CHPCBC",
                                   "E01CHPDT1",
                                   "E01CHPDT2",
                                   "E01CHPDT3",
                                   "E01CHPST0",
                                   "E01CHPST1",
                                   "E01CHPST2",
                                   "E01CHPST3",
                                   "E01CHPST4",
                                   "E01CHPST5",
                                   "E01CHPST6",
                                   "E01CHPST7",
                                   "E01CHPST8",
                                   "E01CHPST9",
                                   "E01CHPCON",
                                   "E01CHPVAL",
                                   "E01CHPN01",
                                   "E01CHPN02",
                                   "E01CHPSTA",
                                   "E01CHPRE1",
                                   "E01CHPDBR"
                                 };
  final static String fid = "1030716174746";
  final static String rid = "3A3521C5A3D00";
  final static String fmtname = "ECH037501";
  final int FIELDCOUNT = 42;
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
  private DecimalField fieldE01CHPACC = null;
  private CharacterField fieldE01CHPCCY = null;
  private CharacterField fieldE01CHPACD = null;
  private CharacterField fieldE01CHPPRO = null;
  private DecimalField fieldE01CHPCUN = null;
  private CharacterField fieldE01CHPNA1 = null;
  private CharacterField fieldE01CHPOFC = null;
  private CharacterField fieldE01DSCOFC = null;
  private DecimalField fieldE01CHPNTC = null;
  private DecimalField fieldE01CHPTCB = null;
  private DecimalField fieldE01CHPMSK = null;
  private DecimalField fieldE01CHPASK = null;
  private CharacterField fieldE01CHPCBC = null;
  private DecimalField fieldE01CHPDT1 = null;
  private DecimalField fieldE01CHPDT2 = null;
  private DecimalField fieldE01CHPDT3 = null;
  private CharacterField fieldE01CHPST0 = null;
  private CharacterField fieldE01CHPST1 = null;
  private CharacterField fieldE01CHPST2 = null;
  private CharacterField fieldE01CHPST3 = null;
  private CharacterField fieldE01CHPST4 = null;
  private CharacterField fieldE01CHPST5 = null;
  private CharacterField fieldE01CHPST6 = null;
  private CharacterField fieldE01CHPST7 = null;
  private CharacterField fieldE01CHPST8 = null;
  private CharacterField fieldE01CHPST9 = null;
  private CharacterField fieldE01CHPCON = null;
  private DecimalField fieldE01CHPVAL = null;
  private CharacterField fieldE01CHPN01 = null;
  private CharacterField fieldE01CHPN02 = null;
  private CharacterField fieldE01CHPSTA = null;
  private CharacterField fieldE01CHPRE1 = null;
  private DecimalField fieldE01CHPDBR = null;

  /**
  * Constructor for ECH037501Message.
  */
  public ECH037501Message()
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
    recordsize = 289;
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
    fields[9] = fieldE01CHPACC =
    new DecimalField(message, HEADERSIZE + 42, 13, 0, "E01CHPACC");
    fields[10] = fieldE01CHPCCY =
    new CharacterField(message, HEADERSIZE + 55, 3, "E01CHPCCY");
    fields[11] = fieldE01CHPACD =
    new CharacterField(message, HEADERSIZE + 58, 2, "E01CHPACD");
    fields[12] = fieldE01CHPPRO =
    new CharacterField(message, HEADERSIZE + 60, 4, "E01CHPPRO");
    fields[13] = fieldE01CHPCUN =
    new DecimalField(message, HEADERSIZE + 64, 10, 0, "E01CHPCUN");
    fields[14] = fieldE01CHPNA1 =
    new CharacterField(message, HEADERSIZE + 74, 45, "E01CHPNA1");
    fields[15] = fieldE01CHPOFC =
    new CharacterField(message, HEADERSIZE + 119, 4, "E01CHPOFC");
    fields[16] = fieldE01DSCOFC =
    new CharacterField(message, HEADERSIZE + 123, 35, "E01DSCOFC");
    fields[17] = fieldE01CHPNTC =
    new DecimalField(message, HEADERSIZE + 158, 5, 0, "E01CHPNTC");
    fields[18] = fieldE01CHPTCB =
    new DecimalField(message, HEADERSIZE + 163, 3, 0, "E01CHPTCB");
    fields[19] = fieldE01CHPMSK =
    new DecimalField(message, HEADERSIZE + 166, 3, 0, "E01CHPMSK");
    fields[20] = fieldE01CHPASK =
    new DecimalField(message, HEADERSIZE + 169, 3, 0, "E01CHPASK");
    fields[21] = fieldE01CHPCBC =
    new CharacterField(message, HEADERSIZE + 172, 1, "E01CHPCBC");
    fields[22] = fieldE01CHPDT1 =
    new DecimalField(message, HEADERSIZE + 173, 3, 0, "E01CHPDT1");
    fields[23] = fieldE01CHPDT2 =
    new DecimalField(message, HEADERSIZE + 176, 3, 0, "E01CHPDT2");
    fields[24] = fieldE01CHPDT3 =
    new DecimalField(message, HEADERSIZE + 179, 3, 0, "E01CHPDT3");
    fields[25] = fieldE01CHPST0 =
    new CharacterField(message, HEADERSIZE + 182, 2, "E01CHPST0");
    fields[26] = fieldE01CHPST1 =
    new CharacterField(message, HEADERSIZE + 184, 2, "E01CHPST1");
    fields[27] = fieldE01CHPST2 =
    new CharacterField(message, HEADERSIZE + 186, 2, "E01CHPST2");
    fields[28] = fieldE01CHPST3 =
    new CharacterField(message, HEADERSIZE + 188, 2, "E01CHPST3");
    fields[29] = fieldE01CHPST4 =
    new CharacterField(message, HEADERSIZE + 190, 2, "E01CHPST4");
    fields[30] = fieldE01CHPST5 =
    new CharacterField(message, HEADERSIZE + 192, 2, "E01CHPST5");
    fields[31] = fieldE01CHPST6 =
    new CharacterField(message, HEADERSIZE + 194, 2, "E01CHPST6");
    fields[32] = fieldE01CHPST7 =
    new CharacterField(message, HEADERSIZE + 196, 2, "E01CHPST7");
    fields[33] = fieldE01CHPST8 =
    new CharacterField(message, HEADERSIZE + 198, 2, "E01CHPST8");
    fields[34] = fieldE01CHPST9 =
    new CharacterField(message, HEADERSIZE + 200, 2, "E01CHPST9");
    fields[35] = fieldE01CHPCON =
    new CharacterField(message, HEADERSIZE + 202, 1, "E01CHPCON");
    fields[36] = fieldE01CHPVAL =
    new DecimalField(message, HEADERSIZE + 203, 10, 0, "E01CHPVAL");
    fields[37] = fieldE01CHPN01 =
    new CharacterField(message, HEADERSIZE + 213, 35, "E01CHPN01");
    fields[38] = fieldE01CHPN02 =
    new CharacterField(message, HEADERSIZE + 248, 35, "E01CHPN02");
    fields[39] = fieldE01CHPSTA =
    new CharacterField(message, HEADERSIZE + 283, 1, "E01CHPSTA");
    fields[40] = fieldE01CHPRE1 =
    new CharacterField(message, HEADERSIZE + 284, 1, "E01CHPRE1");
    fields[41] = fieldE01CHPDBR =
    new DecimalField(message, HEADERSIZE + 285, 4, 0, "E01CHPDBR");

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
  * Set field E01CHPACC using a String value.
  */
  public void setE01CHPACC(String newvalue)
  {
    fieldE01CHPACC.setString(newvalue);
  }

  /**
  * Get value of field E01CHPACC as a String.
  */
  public String getE01CHPACC()
  {
    return fieldE01CHPACC.getString();
  }

  /**
  * Set numeric field E01CHPACC using a BigDecimal value.
  */
  public void setE01CHPACC(BigDecimal newvalue)
  {
    fieldE01CHPACC.setBigDecimal(newvalue);
  }

  /**
  * Return value of numeric field E01CHPACC as a BigDecimal.
  */
  public BigDecimal getBigDecimalE01CHPACC()
  {
    return fieldE01CHPACC.getBigDecimal();
  }

  /**
  * Set field E01CHPCCY using a String value.
  */
  public void setE01CHPCCY(String newvalue)
  {
    fieldE01CHPCCY.setString(newvalue);
  }

  /**
  * Get value of field E01CHPCCY as a String.
  */
  public String getE01CHPCCY()
  {
    return fieldE01CHPCCY.getString();
  }

  /**
  * Set field E01CHPACD using a String value.
  */
  public void setE01CHPACD(String newvalue)
  {
    fieldE01CHPACD.setString(newvalue);
  }

  /**
  * Get value of field E01CHPACD as a String.
  */
  public String getE01CHPACD()
  {
    return fieldE01CHPACD.getString();
  }

  /**
  * Set field E01CHPPRO using a String value.
  */
  public void setE01CHPPRO(String newvalue)
  {
    fieldE01CHPPRO.setString(newvalue);
  }

  /**
  * Get value of field E01CHPPRO as a String.
  */
  public String getE01CHPPRO()
  {
    return fieldE01CHPPRO.getString();
  }

  /**
  * Set field E01CHPCUN using a String value.
  */
  public void setE01CHPCUN(String newvalue)
  {
    fieldE01CHPCUN.setString(newvalue);
  }

  /**
  * Get value of field E01CHPCUN as a String.
  */
  public String getE01CHPCUN()
  {
    return fieldE01CHPCUN.getString();
  }

  /**
  * Set numeric field E01CHPCUN using a BigDecimal value.
  */
  public void setE01CHPCUN(BigDecimal newvalue)
  {
    fieldE01CHPCUN.setBigDecimal(newvalue);
  }

  /**
  * Return value of numeric field E01CHPCUN as a BigDecimal.
  */
  public BigDecimal getBigDecimalE01CHPCUN()
  {
    return fieldE01CHPCUN.getBigDecimal();
  }

  /**
  * Set field E01CHPNA1 using a String value.
  */
  public void setE01CHPNA1(String newvalue)
  {
    fieldE01CHPNA1.setString(newvalue);
  }

  /**
  * Get value of field E01CHPNA1 as a String.
  */
  public String getE01CHPNA1()
  {
    return fieldE01CHPNA1.getString();
  }

  /**
  * Set field E01CHPOFC using a String value.
  */
  public void setE01CHPOFC(String newvalue)
  {
    fieldE01CHPOFC.setString(newvalue);
  }

  /**
  * Get value of field E01CHPOFC as a String.
  */
  public String getE01CHPOFC()
  {
    return fieldE01CHPOFC.getString();
  }

  /**
  * Set field E01DSCOFC using a String value.
  */
  public void setE01DSCOFC(String newvalue)
  {
    fieldE01DSCOFC.setString(newvalue);
  }

  /**
  * Get value of field E01DSCOFC as a String.
  */
  public String getE01DSCOFC()
  {
    return fieldE01DSCOFC.getString();
  }

  /**
  * Set field E01CHPNTC using a String value.
  */
  public void setE01CHPNTC(String newvalue)
  {
    fieldE01CHPNTC.setString(newvalue);
  }

  /**
  * Get value of field E01CHPNTC as a String.
  */
  public String getE01CHPNTC()
  {
    return fieldE01CHPNTC.getString();
  }

  /**
  * Set numeric field E01CHPNTC using a BigDecimal value.
  */
  public void setE01CHPNTC(BigDecimal newvalue)
  {
    fieldE01CHPNTC.setBigDecimal(newvalue);
  }

  /**
  * Return value of numeric field E01CHPNTC as a BigDecimal.
  */
  public BigDecimal getBigDecimalE01CHPNTC()
  {
    return fieldE01CHPNTC.getBigDecimal();
  }

  /**
  * Set field E01CHPTCB using a String value.
  */
  public void setE01CHPTCB(String newvalue)
  {
    fieldE01CHPTCB.setString(newvalue);
  }

  /**
  * Get value of field E01CHPTCB as a String.
  */
  public String getE01CHPTCB()
  {
    return fieldE01CHPTCB.getString();
  }

  /**
  * Set numeric field E01CHPTCB using a BigDecimal value.
  */
  public void setE01CHPTCB(BigDecimal newvalue)
  {
    fieldE01CHPTCB.setBigDecimal(newvalue);
  }

  /**
  * Return value of numeric field E01CHPTCB as a BigDecimal.
  */
  public BigDecimal getBigDecimalE01CHPTCB()
  {
    return fieldE01CHPTCB.getBigDecimal();
  }

  /**
  * Set field E01CHPMSK using a String value.
  */
  public void setE01CHPMSK(String newvalue)
  {
    fieldE01CHPMSK.setString(newvalue);
  }

  /**
  * Get value of field E01CHPMSK as a String.
  */
  public String getE01CHPMSK()
  {
    return fieldE01CHPMSK.getString();
  }

  /**
  * Set numeric field E01CHPMSK using a BigDecimal value.
  */
  public void setE01CHPMSK(BigDecimal newvalue)
  {
    fieldE01CHPMSK.setBigDecimal(newvalue);
  }

  /**
  * Return value of numeric field E01CHPMSK as a BigDecimal.
  */
  public BigDecimal getBigDecimalE01CHPMSK()
  {
    return fieldE01CHPMSK.getBigDecimal();
  }

  /**
  * Set field E01CHPASK using a String value.
  */
  public void setE01CHPASK(String newvalue)
  {
    fieldE01CHPASK.setString(newvalue);
  }

  /**
  * Get value of field E01CHPASK as a String.
  */
  public String getE01CHPASK()
  {
    return fieldE01CHPASK.getString();
  }

  /**
  * Set numeric field E01CHPASK using a BigDecimal value.
  */
  public void setE01CHPASK(BigDecimal newvalue)
  {
    fieldE01CHPASK.setBigDecimal(newvalue);
  }

  /**
  * Return value of numeric field E01CHPASK as a BigDecimal.
  */
  public BigDecimal getBigDecimalE01CHPASK()
  {
    return fieldE01CHPASK.getBigDecimal();
  }

  /**
  * Set field E01CHPCBC using a String value.
  */
  public void setE01CHPCBC(String newvalue)
  {
    fieldE01CHPCBC.setString(newvalue);
  }

  /**
  * Get value of field E01CHPCBC as a String.
  */
  public String getE01CHPCBC()
  {
    return fieldE01CHPCBC.getString();
  }

  /**
  * Set field E01CHPDT1 using a String value.
  */
  public void setE01CHPDT1(String newvalue)
  {
    fieldE01CHPDT1.setString(newvalue);
  }

  /**
  * Get value of field E01CHPDT1 as a String.
  */
  public String getE01CHPDT1()
  {
    return fieldE01CHPDT1.getString();
  }

  /**
  * Set numeric field E01CHPDT1 using a BigDecimal value.
  */
  public void setE01CHPDT1(BigDecimal newvalue)
  {
    fieldE01CHPDT1.setBigDecimal(newvalue);
  }

  /**
  * Return value of numeric field E01CHPDT1 as a BigDecimal.
  */
  public BigDecimal getBigDecimalE01CHPDT1()
  {
    return fieldE01CHPDT1.getBigDecimal();
  }

  /**
  * Set field E01CHPDT2 using a String value.
  */
  public void setE01CHPDT2(String newvalue)
  {
    fieldE01CHPDT2.setString(newvalue);
  }

  /**
  * Get value of field E01CHPDT2 as a String.
  */
  public String getE01CHPDT2()
  {
    return fieldE01CHPDT2.getString();
  }

  /**
  * Set numeric field E01CHPDT2 using a BigDecimal value.
  */
  public void setE01CHPDT2(BigDecimal newvalue)
  {
    fieldE01CHPDT2.setBigDecimal(newvalue);
  }

  /**
  * Return value of numeric field E01CHPDT2 as a BigDecimal.
  */
  public BigDecimal getBigDecimalE01CHPDT2()
  {
    return fieldE01CHPDT2.getBigDecimal();
  }

  /**
  * Set field E01CHPDT3 using a String value.
  */
  public void setE01CHPDT3(String newvalue)
  {
    fieldE01CHPDT3.setString(newvalue);
  }

  /**
  * Get value of field E01CHPDT3 as a String.
  */
  public String getE01CHPDT3()
  {
    return fieldE01CHPDT3.getString();
  }

  /**
  * Set numeric field E01CHPDT3 using a BigDecimal value.
  */
  public void setE01CHPDT3(BigDecimal newvalue)
  {
    fieldE01CHPDT3.setBigDecimal(newvalue);
  }

  /**
  * Return value of numeric field E01CHPDT3 as a BigDecimal.
  */
  public BigDecimal getBigDecimalE01CHPDT3()
  {
    return fieldE01CHPDT3.getBigDecimal();
  }

  /**
  * Set field E01CHPST0 using a String value.
  */
  public void setE01CHPST0(String newvalue)
  {
    fieldE01CHPST0.setString(newvalue);
  }

  /**
  * Get value of field E01CHPST0 as a String.
  */
  public String getE01CHPST0()
  {
    return fieldE01CHPST0.getString();
  }

  /**
  * Set field E01CHPST1 using a String value.
  */
  public void setE01CHPST1(String newvalue)
  {
    fieldE01CHPST1.setString(newvalue);
  }

  /**
  * Get value of field E01CHPST1 as a String.
  */
  public String getE01CHPST1()
  {
    return fieldE01CHPST1.getString();
  }

  /**
  * Set field E01CHPST2 using a String value.
  */
  public void setE01CHPST2(String newvalue)
  {
    fieldE01CHPST2.setString(newvalue);
  }

  /**
  * Get value of field E01CHPST2 as a String.
  */
  public String getE01CHPST2()
  {
    return fieldE01CHPST2.getString();
  }

  /**
  * Set field E01CHPST3 using a String value.
  */
  public void setE01CHPST3(String newvalue)
  {
    fieldE01CHPST3.setString(newvalue);
  }

  /**
  * Get value of field E01CHPST3 as a String.
  */
  public String getE01CHPST3()
  {
    return fieldE01CHPST3.getString();
  }

  /**
  * Set field E01CHPST4 using a String value.
  */
  public void setE01CHPST4(String newvalue)
  {
    fieldE01CHPST4.setString(newvalue);
  }

  /**
  * Get value of field E01CHPST4 as a String.
  */
  public String getE01CHPST4()
  {
    return fieldE01CHPST4.getString();
  }

  /**
  * Set field E01CHPST5 using a String value.
  */
  public void setE01CHPST5(String newvalue)
  {
    fieldE01CHPST5.setString(newvalue);
  }

  /**
  * Get value of field E01CHPST5 as a String.
  */
  public String getE01CHPST5()
  {
    return fieldE01CHPST5.getString();
  }

  /**
  * Set field E01CHPST6 using a String value.
  */
  public void setE01CHPST6(String newvalue)
  {
    fieldE01CHPST6.setString(newvalue);
  }

  /**
  * Get value of field E01CHPST6 as a String.
  */
  public String getE01CHPST6()
  {
    return fieldE01CHPST6.getString();
  }

  /**
  * Set field E01CHPST7 using a String value.
  */
  public void setE01CHPST7(String newvalue)
  {
    fieldE01CHPST7.setString(newvalue);
  }

  /**
  * Get value of field E01CHPST7 as a String.
  */
  public String getE01CHPST7()
  {
    return fieldE01CHPST7.getString();
  }

  /**
  * Set field E01CHPST8 using a String value.
  */
  public void setE01CHPST8(String newvalue)
  {
    fieldE01CHPST8.setString(newvalue);
  }

  /**
  * Get value of field E01CHPST8 as a String.
  */
  public String getE01CHPST8()
  {
    return fieldE01CHPST8.getString();
  }

  /**
  * Set field E01CHPST9 using a String value.
  */
  public void setE01CHPST9(String newvalue)
  {
    fieldE01CHPST9.setString(newvalue);
  }

  /**
  * Get value of field E01CHPST9 as a String.
  */
  public String getE01CHPST9()
  {
    return fieldE01CHPST9.getString();
  }

  /**
  * Set field E01CHPCON using a String value.
  */
  public void setE01CHPCON(String newvalue)
  {
    fieldE01CHPCON.setString(newvalue);
  }

  /**
  * Get value of field E01CHPCON as a String.
  */
  public String getE01CHPCON()
  {
    return fieldE01CHPCON.getString();
  }

  /**
  * Set field E01CHPVAL using a String value.
  */
  public void setE01CHPVAL(String newvalue)
  {
    fieldE01CHPVAL.setString(newvalue);
  }

  /**
  * Get value of field E01CHPVAL as a String.
  */
  public String getE01CHPVAL()
  {
    return fieldE01CHPVAL.getString();
  }

  /**
  * Set numeric field E01CHPVAL using a BigDecimal value.
  */
  public void setE01CHPVAL(BigDecimal newvalue)
  {
    fieldE01CHPVAL.setBigDecimal(newvalue);
  }

  /**
  * Return value of numeric field E01CHPVAL as a BigDecimal.
  */
  public BigDecimal getBigDecimalE01CHPVAL()
  {
    return fieldE01CHPVAL.getBigDecimal();
  }

  /**
  * Set field E01CHPN01 using a String value.
  */
  public void setE01CHPN01(String newvalue)
  {
    fieldE01CHPN01.setString(newvalue);
  }

  /**
  * Get value of field E01CHPN01 as a String.
  */
  public String getE01CHPN01()
  {
    return fieldE01CHPN01.getString();
  }

  /**
  * Set field E01CHPN02 using a String value.
  */
  public void setE01CHPN02(String newvalue)
  {
    fieldE01CHPN02.setString(newvalue);
  }

  /**
  * Get value of field E01CHPN02 as a String.
  */
  public String getE01CHPN02()
  {
    return fieldE01CHPN02.getString();
  }

  /**
  * Set field E01CHPSTA using a String value.
  */
  public void setE01CHPSTA(String newvalue)
  {
    fieldE01CHPSTA.setString(newvalue);
  }

  /**
  * Get value of field E01CHPSTA as a String.
  */
  public String getE01CHPSTA()
  {
    return fieldE01CHPSTA.getString();
  }

  /**
  * Set field E01CHPRE1 using a String value.
  */
  public void setE01CHPRE1(String newvalue)
  {
    fieldE01CHPRE1.setString(newvalue);
  }

  /**
  * Get value of field E01CHPRE1 as a String.
  */
  public String getE01CHPRE1()
  {
    return fieldE01CHPRE1.getString();
  }

  /**
  * Set field E01CHPDBR using a String value.
  */
  public void setE01CHPDBR(String newvalue)
  {
    fieldE01CHPDBR.setString(newvalue);
  }

  /**
  * Get value of field E01CHPDBR as a String.
  */
  public String getE01CHPDBR()
  {
    return fieldE01CHPDBR.getString();
  }

  /**
  * Set numeric field E01CHPDBR using a BigDecimal value.
  */
  public void setE01CHPDBR(BigDecimal newvalue)
  {
    fieldE01CHPDBR.setBigDecimal(newvalue);
  }

  /**
  * Return value of numeric field E01CHPDBR as a BigDecimal.
  */
  public BigDecimal getBigDecimalE01CHPDBR()
  {
    return fieldE01CHPDBR.getBigDecimal();
  }

}
