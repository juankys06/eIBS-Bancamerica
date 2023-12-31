package datapro.eibs.beans;

import datapro.eibs.sockets.*;
import java.io.*;
import java.math.*;

import java.util.*;

/**
* Class generated by AS/400 CRTCLASS command from EIRA02001 physical file definition.
* 
* File level identifier is 1041109162004.
* Record format level identifier is 410C6A7DDF1E8.
*/

public class EIRA02001Message extends MessageRecord
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
                                     "E01IRABNK",
                                     "E01IRACUN",
                                     "E01IRAACC",
                                     "E01IRATRN",
                                     "E01IRAYEA",
                                     "E01IRATCD",
                                     "E01IRAAM1",
                                     "E01IRAAM2",
                                     "D01IRADSC",
                                     "D01TRNDSC",
                                     "D01CODDSC",
                                     "D01CUSNA1",
                                     "D01CUSBD1",
                                     "D01CUSBD2",
                                     "D01CUSBD3",
                                     "D01DEAOD1",
                                     "D01DEAOD2",
                                     "D01DEAOD3",
                                     "D01IRAIAL",
                                     "D01IRAIPL",
                                     "D01IRAWHD",
                                     "D01CURBBL",
                                     "D01CUREBL",
                                     "D01PRVBBL",
                                     "D01PRVEBL"
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
                                   "E01IRABNK",
                                   "E01IRACUN",
                                   "E01IRAACC",
                                   "E01IRATRN",
                                   "E01IRAYEA",
                                   "E01IRATCD",
                                   "E01IRAAM1",
                                   "E01IRAAM2",
                                   "D01IRADSC",
                                   "D01TRNDSC",
                                   "D01CODDSC",
                                   "D01CUSNA1",
                                   "D01CUSBD1",
                                   "D01CUSBD2",
                                   "D01CUSBD3",
                                   "D01DEAOD1",
                                   "D01DEAOD2",
                                   "D01DEAOD3",
                                   "D01IRAIAL",
                                   "D01IRAIPL",
                                   "D01IRAWHD",
                                   "D01CURBBL",
                                   "D01CUREBL",
                                   "D01PRVBBL",
                                   "D01PRVEBL"
                                 };
  final static String fid = "1041109162004";
  final static String rid = "410C6A7DDF1E8";
  final static String fmtname = "EIRA02001";
  final int FIELDCOUNT = 34;
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
  private CharacterField fieldE01IRABNK = null;
  private DecimalField fieldE01IRACUN = null;
  private DecimalField fieldE01IRAACC = null;
  private CharacterField fieldE01IRATRN = null;
  private DecimalField fieldE01IRAYEA = null;
  private CharacterField fieldE01IRATCD = null;
  private DecimalField fieldE01IRAAM1 = null;
  private DecimalField fieldE01IRAAM2 = null;
  private CharacterField fieldD01IRADSC = null;
  private CharacterField fieldD01TRNDSC = null;
  private CharacterField fieldD01CODDSC = null;
  private CharacterField fieldD01CUSNA1 = null;
  private DecimalField fieldD01CUSBD1 = null;
  private DecimalField fieldD01CUSBD2 = null;
  private DecimalField fieldD01CUSBD3 = null;
  private DecimalField fieldD01DEAOD1 = null;
  private DecimalField fieldD01DEAOD2 = null;
  private DecimalField fieldD01DEAOD3 = null;
  private DecimalField fieldD01IRAIAL = null;
  private DecimalField fieldD01IRAIPL = null;
  private DecimalField fieldD01IRAWHD = null;
  private DecimalField fieldD01CURBBL = null;
  private DecimalField fieldD01CUREBL = null;
  private DecimalField fieldD01PRVBBL = null;
  private DecimalField fieldD01PRVEBL = null;

  /**
  * Constructor for EIRA02001Message.
  */
  public EIRA02001Message()
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
    recordsize = 346;
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
    fields[9] = fieldE01IRABNK =
    new CharacterField(message, HEADERSIZE + 42, 2, "E01IRABNK");
    fields[10] = fieldE01IRACUN =
    new DecimalField(message, HEADERSIZE + 44, 10, 0, "E01IRACUN");
    fields[11] = fieldE01IRAACC =
    new DecimalField(message, HEADERSIZE + 54, 10, 0, "E01IRAACC");
    fields[12] = fieldE01IRATRN =
    new CharacterField(message, HEADERSIZE + 64, 1, "E01IRATRN");
    fields[13] = fieldE01IRAYEA =
    new DecimalField(message, HEADERSIZE + 65, 5, 0, "E01IRAYEA");
    fields[14] = fieldE01IRATCD =
    new CharacterField(message, HEADERSIZE + 70, 3, "E01IRATCD");
    fields[15] = fieldE01IRAAM1 =
    new DecimalField(message, HEADERSIZE + 73, 15, 2, "E01IRAAM1");
    fields[16] = fieldE01IRAAM2 =
    new DecimalField(message, HEADERSIZE + 88, 15, 2, "E01IRAAM2");
    fields[17] = fieldD01IRADSC =
    new CharacterField(message, HEADERSIZE + 103, 25, "D01IRADSC");
    fields[18] = fieldD01TRNDSC =
    new CharacterField(message, HEADERSIZE + 128, 15, "D01TRNDSC");
    fields[19] = fieldD01CODDSC =
    new CharacterField(message, HEADERSIZE + 143, 35, "D01CODDSC");
    fields[20] = fieldD01CUSNA1 =
    new CharacterField(message, HEADERSIZE + 178, 45, "D01CUSNA1");
    fields[21] = fieldD01CUSBD1 =
    new DecimalField(message, HEADERSIZE + 223, 3, 0, "D01CUSBD1");
    fields[22] = fieldD01CUSBD2 =
    new DecimalField(message, HEADERSIZE + 226, 3, 0, "D01CUSBD2");
    fields[23] = fieldD01CUSBD3 =
    new DecimalField(message, HEADERSIZE + 229, 3, 0, "D01CUSBD3");
    fields[24] = fieldD01DEAOD1 =
    new DecimalField(message, HEADERSIZE + 232, 3, 0, "D01DEAOD1");
    fields[25] = fieldD01DEAOD2 =
    new DecimalField(message, HEADERSIZE + 235, 3, 0, "D01DEAOD2");
    fields[26] = fieldD01DEAOD3 =
    new DecimalField(message, HEADERSIZE + 238, 3, 0, "D01DEAOD3");
    fields[27] = fieldD01IRAIAL =
    new DecimalField(message, HEADERSIZE + 241, 15, 2, "D01IRAIAL");
    fields[28] = fieldD01IRAIPL =
    new DecimalField(message, HEADERSIZE + 256, 15, 2, "D01IRAIPL");
    fields[29] = fieldD01IRAWHD =
    new DecimalField(message, HEADERSIZE + 271, 15, 2, "D01IRAWHD");
    fields[30] = fieldD01CURBBL =
    new DecimalField(message, HEADERSIZE + 286, 15, 2, "D01CURBBL");
    fields[31] = fieldD01CUREBL =
    new DecimalField(message, HEADERSIZE + 301, 15, 2, "D01CUREBL");
    fields[32] = fieldD01PRVBBL =
    new DecimalField(message, HEADERSIZE + 316, 15, 2, "D01PRVBBL");
    fields[33] = fieldD01PRVEBL =
    new DecimalField(message, HEADERSIZE + 331, 15, 2, "D01PRVEBL");

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
  * Set field E01IRABNK using a String value.
  */
  public void setE01IRABNK(String newvalue)
  {
    fieldE01IRABNK.setString(newvalue);
  }

  /**
  * Get value of field E01IRABNK as a String.
  */
  public String getE01IRABNK()
  {
    return fieldE01IRABNK.getString();
  }

  /**
  * Set field E01IRACUN using a String value.
  */
  public void setE01IRACUN(String newvalue)
  {
    fieldE01IRACUN.setString(newvalue);
  }

  /**
  * Get value of field E01IRACUN as a String.
  */
  public String getE01IRACUN()
  {
    return fieldE01IRACUN.getString();
  }

  /**
  * Set numeric field E01IRACUN using a BigDecimal value.
  */
  public void setE01IRACUN(BigDecimal newvalue)
  {
    fieldE01IRACUN.setBigDecimal(newvalue);
  }

  /**
  * Return value of numeric field E01IRACUN as a BigDecimal.
  */
  public BigDecimal getBigDecimalE01IRACUN()
  {
    return fieldE01IRACUN.getBigDecimal();
  }

  /**
  * Set field E01IRAACC using a String value.
  */
  public void setE01IRAACC(String newvalue)
  {
    fieldE01IRAACC.setString(newvalue);
  }

  /**
  * Get value of field E01IRAACC as a String.
  */
  public String getE01IRAACC()
  {
    return fieldE01IRAACC.getString();
  }

  /**
  * Set numeric field E01IRAACC using a BigDecimal value.
  */
  public void setE01IRAACC(BigDecimal newvalue)
  {
    fieldE01IRAACC.setBigDecimal(newvalue);
  }

  /**
  * Return value of numeric field E01IRAACC as a BigDecimal.
  */
  public BigDecimal getBigDecimalE01IRAACC()
  {
    return fieldE01IRAACC.getBigDecimal();
  }

  /**
  * Set field E01IRATRN using a String value.
  */
  public void setE01IRATRN(String newvalue)
  {
    fieldE01IRATRN.setString(newvalue);
  }

  /**
  * Get value of field E01IRATRN as a String.
  */
  public String getE01IRATRN()
  {
    return fieldE01IRATRN.getString();
  }

  /**
  * Set field E01IRAYEA using a String value.
  */
  public void setE01IRAYEA(String newvalue)
  {
    fieldE01IRAYEA.setString(newvalue);
  }

  /**
  * Get value of field E01IRAYEA as a String.
  */
  public String getE01IRAYEA()
  {
    return fieldE01IRAYEA.getString();
  }

  /**
  * Set numeric field E01IRAYEA using a BigDecimal value.
  */
  public void setE01IRAYEA(BigDecimal newvalue)
  {
    fieldE01IRAYEA.setBigDecimal(newvalue);
  }

  /**
  * Return value of numeric field E01IRAYEA as a BigDecimal.
  */
  public BigDecimal getBigDecimalE01IRAYEA()
  {
    return fieldE01IRAYEA.getBigDecimal();
  }

  /**
  * Set field E01IRATCD using a String value.
  */
  public void setE01IRATCD(String newvalue)
  {
    fieldE01IRATCD.setString(newvalue);
  }

  /**
  * Get value of field E01IRATCD as a String.
  */
  public String getE01IRATCD()
  {
    return fieldE01IRATCD.getString();
  }

  /**
  * Set field E01IRAAM1 using a String value.
  */
  public void setE01IRAAM1(String newvalue)
  {
    fieldE01IRAAM1.setString(newvalue);
  }

  /**
  * Get value of field E01IRAAM1 as a String.
  */
  public String getE01IRAAM1()
  {
    return fieldE01IRAAM1.getString();
  }

  /**
  * Set numeric field E01IRAAM1 using a BigDecimal value.
  */
  public void setE01IRAAM1(BigDecimal newvalue)
  {
    fieldE01IRAAM1.setBigDecimal(newvalue);
  }

  /**
  * Return value of numeric field E01IRAAM1 as a BigDecimal.
  */
  public BigDecimal getBigDecimalE01IRAAM1()
  {
    return fieldE01IRAAM1.getBigDecimal();
  }

  /**
  * Set field E01IRAAM2 using a String value.
  */
  public void setE01IRAAM2(String newvalue)
  {
    fieldE01IRAAM2.setString(newvalue);
  }

  /**
  * Get value of field E01IRAAM2 as a String.
  */
  public String getE01IRAAM2()
  {
    return fieldE01IRAAM2.getString();
  }

  /**
  * Set numeric field E01IRAAM2 using a BigDecimal value.
  */
  public void setE01IRAAM2(BigDecimal newvalue)
  {
    fieldE01IRAAM2.setBigDecimal(newvalue);
  }

  /**
  * Return value of numeric field E01IRAAM2 as a BigDecimal.
  */
  public BigDecimal getBigDecimalE01IRAAM2()
  {
    return fieldE01IRAAM2.getBigDecimal();
  }

  /**
  * Set field D01IRADSC using a String value.
  */
  public void setD01IRADSC(String newvalue)
  {
    fieldD01IRADSC.setString(newvalue);
  }

  /**
  * Get value of field D01IRADSC as a String.
  */
  public String getD01IRADSC()
  {
    return fieldD01IRADSC.getString();
  }

  /**
  * Set field D01TRNDSC using a String value.
  */
  public void setD01TRNDSC(String newvalue)
  {
    fieldD01TRNDSC.setString(newvalue);
  }

  /**
  * Get value of field D01TRNDSC as a String.
  */
  public String getD01TRNDSC()
  {
    return fieldD01TRNDSC.getString();
  }

  /**
  * Set field D01CODDSC using a String value.
  */
  public void setD01CODDSC(String newvalue)
  {
    fieldD01CODDSC.setString(newvalue);
  }

  /**
  * Get value of field D01CODDSC as a String.
  */
  public String getD01CODDSC()
  {
    return fieldD01CODDSC.getString();
  }

  /**
  * Set field D01CUSNA1 using a String value.
  */
  public void setD01CUSNA1(String newvalue)
  {
    fieldD01CUSNA1.setString(newvalue);
  }

  /**
  * Get value of field D01CUSNA1 as a String.
  */
  public String getD01CUSNA1()
  {
    return fieldD01CUSNA1.getString();
  }

  /**
  * Set field D01CUSBD1 using a String value.
  */
  public void setD01CUSBD1(String newvalue)
  {
    fieldD01CUSBD1.setString(newvalue);
  }

  /**
  * Get value of field D01CUSBD1 as a String.
  */
  public String getD01CUSBD1()
  {
    return fieldD01CUSBD1.getString();
  }

  /**
  * Set numeric field D01CUSBD1 using a BigDecimal value.
  */
  public void setD01CUSBD1(BigDecimal newvalue)
  {
    fieldD01CUSBD1.setBigDecimal(newvalue);
  }

  /**
  * Return value of numeric field D01CUSBD1 as a BigDecimal.
  */
  public BigDecimal getBigDecimalD01CUSBD1()
  {
    return fieldD01CUSBD1.getBigDecimal();
  }

  /**
  * Set field D01CUSBD2 using a String value.
  */
  public void setD01CUSBD2(String newvalue)
  {
    fieldD01CUSBD2.setString(newvalue);
  }

  /**
  * Get value of field D01CUSBD2 as a String.
  */
  public String getD01CUSBD2()
  {
    return fieldD01CUSBD2.getString();
  }

  /**
  * Set numeric field D01CUSBD2 using a BigDecimal value.
  */
  public void setD01CUSBD2(BigDecimal newvalue)
  {
    fieldD01CUSBD2.setBigDecimal(newvalue);
  }

  /**
  * Return value of numeric field D01CUSBD2 as a BigDecimal.
  */
  public BigDecimal getBigDecimalD01CUSBD2()
  {
    return fieldD01CUSBD2.getBigDecimal();
  }

  /**
  * Set field D01CUSBD3 using a String value.
  */
  public void setD01CUSBD3(String newvalue)
  {
    fieldD01CUSBD3.setString(newvalue);
  }

  /**
  * Get value of field D01CUSBD3 as a String.
  */
  public String getD01CUSBD3()
  {
    return fieldD01CUSBD3.getString();
  }

  /**
  * Set numeric field D01CUSBD3 using a BigDecimal value.
  */
  public void setD01CUSBD3(BigDecimal newvalue)
  {
    fieldD01CUSBD3.setBigDecimal(newvalue);
  }

  /**
  * Return value of numeric field D01CUSBD3 as a BigDecimal.
  */
  public BigDecimal getBigDecimalD01CUSBD3()
  {
    return fieldD01CUSBD3.getBigDecimal();
  }

  /**
  * Set field D01DEAOD1 using a String value.
  */
  public void setD01DEAOD1(String newvalue)
  {
    fieldD01DEAOD1.setString(newvalue);
  }

  /**
  * Get value of field D01DEAOD1 as a String.
  */
  public String getD01DEAOD1()
  {
    return fieldD01DEAOD1.getString();
  }

  /**
  * Set numeric field D01DEAOD1 using a BigDecimal value.
  */
  public void setD01DEAOD1(BigDecimal newvalue)
  {
    fieldD01DEAOD1.setBigDecimal(newvalue);
  }

  /**
  * Return value of numeric field D01DEAOD1 as a BigDecimal.
  */
  public BigDecimal getBigDecimalD01DEAOD1()
  {
    return fieldD01DEAOD1.getBigDecimal();
  }

  /**
  * Set field D01DEAOD2 using a String value.
  */
  public void setD01DEAOD2(String newvalue)
  {
    fieldD01DEAOD2.setString(newvalue);
  }

  /**
  * Get value of field D01DEAOD2 as a String.
  */
  public String getD01DEAOD2()
  {
    return fieldD01DEAOD2.getString();
  }

  /**
  * Set numeric field D01DEAOD2 using a BigDecimal value.
  */
  public void setD01DEAOD2(BigDecimal newvalue)
  {
    fieldD01DEAOD2.setBigDecimal(newvalue);
  }

  /**
  * Return value of numeric field D01DEAOD2 as a BigDecimal.
  */
  public BigDecimal getBigDecimalD01DEAOD2()
  {
    return fieldD01DEAOD2.getBigDecimal();
  }

  /**
  * Set field D01DEAOD3 using a String value.
  */
  public void setD01DEAOD3(String newvalue)
  {
    fieldD01DEAOD3.setString(newvalue);
  }

  /**
  * Get value of field D01DEAOD3 as a String.
  */
  public String getD01DEAOD3()
  {
    return fieldD01DEAOD3.getString();
  }

  /**
  * Set numeric field D01DEAOD3 using a BigDecimal value.
  */
  public void setD01DEAOD3(BigDecimal newvalue)
  {
    fieldD01DEAOD3.setBigDecimal(newvalue);
  }

  /**
  * Return value of numeric field D01DEAOD3 as a BigDecimal.
  */
  public BigDecimal getBigDecimalD01DEAOD3()
  {
    return fieldD01DEAOD3.getBigDecimal();
  }

  /**
  * Set field D01IRAIAL using a String value.
  */
  public void setD01IRAIAL(String newvalue)
  {
    fieldD01IRAIAL.setString(newvalue);
  }

  /**
  * Get value of field D01IRAIAL as a String.
  */
  public String getD01IRAIAL()
  {
    return fieldD01IRAIAL.getString();
  }

  /**
  * Set numeric field D01IRAIAL using a BigDecimal value.
  */
  public void setD01IRAIAL(BigDecimal newvalue)
  {
    fieldD01IRAIAL.setBigDecimal(newvalue);
  }

  /**
  * Return value of numeric field D01IRAIAL as a BigDecimal.
  */
  public BigDecimal getBigDecimalD01IRAIAL()
  {
    return fieldD01IRAIAL.getBigDecimal();
  }

  /**
  * Set field D01IRAIPL using a String value.
  */
  public void setD01IRAIPL(String newvalue)
  {
    fieldD01IRAIPL.setString(newvalue);
  }

  /**
  * Get value of field D01IRAIPL as a String.
  */
  public String getD01IRAIPL()
  {
    return fieldD01IRAIPL.getString();
  }

  /**
  * Set numeric field D01IRAIPL using a BigDecimal value.
  */
  public void setD01IRAIPL(BigDecimal newvalue)
  {
    fieldD01IRAIPL.setBigDecimal(newvalue);
  }

  /**
  * Return value of numeric field D01IRAIPL as a BigDecimal.
  */
  public BigDecimal getBigDecimalD01IRAIPL()
  {
    return fieldD01IRAIPL.getBigDecimal();
  }

  /**
  * Set field D01IRAWHD using a String value.
  */
  public void setD01IRAWHD(String newvalue)
  {
    fieldD01IRAWHD.setString(newvalue);
  }

  /**
  * Get value of field D01IRAWHD as a String.
  */
  public String getD01IRAWHD()
  {
    return fieldD01IRAWHD.getString();
  }

  /**
  * Set numeric field D01IRAWHD using a BigDecimal value.
  */
  public void setD01IRAWHD(BigDecimal newvalue)
  {
    fieldD01IRAWHD.setBigDecimal(newvalue);
  }

  /**
  * Return value of numeric field D01IRAWHD as a BigDecimal.
  */
  public BigDecimal getBigDecimalD01IRAWHD()
  {
    return fieldD01IRAWHD.getBigDecimal();
  }

  /**
  * Set field D01CURBBL using a String value.
  */
  public void setD01CURBBL(String newvalue)
  {
    fieldD01CURBBL.setString(newvalue);
  }

  /**
  * Get value of field D01CURBBL as a String.
  */
  public String getD01CURBBL()
  {
    return fieldD01CURBBL.getString();
  }

  /**
  * Set numeric field D01CURBBL using a BigDecimal value.
  */
  public void setD01CURBBL(BigDecimal newvalue)
  {
    fieldD01CURBBL.setBigDecimal(newvalue);
  }

  /**
  * Return value of numeric field D01CURBBL as a BigDecimal.
  */
  public BigDecimal getBigDecimalD01CURBBL()
  {
    return fieldD01CURBBL.getBigDecimal();
  }

  /**
  * Set field D01CUREBL using a String value.
  */
  public void setD01CUREBL(String newvalue)
  {
    fieldD01CUREBL.setString(newvalue);
  }

  /**
  * Get value of field D01CUREBL as a String.
  */
  public String getD01CUREBL()
  {
    return fieldD01CUREBL.getString();
  }

  /**
  * Set numeric field D01CUREBL using a BigDecimal value.
  */
  public void setD01CUREBL(BigDecimal newvalue)
  {
    fieldD01CUREBL.setBigDecimal(newvalue);
  }

  /**
  * Return value of numeric field D01CUREBL as a BigDecimal.
  */
  public BigDecimal getBigDecimalD01CUREBL()
  {
    return fieldD01CUREBL.getBigDecimal();
  }

  /**
  * Set field D01PRVBBL using a String value.
  */
  public void setD01PRVBBL(String newvalue)
  {
    fieldD01PRVBBL.setString(newvalue);
  }

  /**
  * Get value of field D01PRVBBL as a String.
  */
  public String getD01PRVBBL()
  {
    return fieldD01PRVBBL.getString();
  }

  /**
  * Set numeric field D01PRVBBL using a BigDecimal value.
  */
  public void setD01PRVBBL(BigDecimal newvalue)
  {
    fieldD01PRVBBL.setBigDecimal(newvalue);
  }

  /**
  * Return value of numeric field D01PRVBBL as a BigDecimal.
  */
  public BigDecimal getBigDecimalD01PRVBBL()
  {
    return fieldD01PRVBBL.getBigDecimal();
  }

  /**
  * Set field D01PRVEBL using a String value.
  */
  public void setD01PRVEBL(String newvalue)
  {
    fieldD01PRVEBL.setString(newvalue);
  }

  /**
  * Get value of field D01PRVEBL as a String.
  */
  public String getD01PRVEBL()
  {
    return fieldD01PRVEBL.getString();
  }

  /**
  * Set numeric field D01PRVEBL using a BigDecimal value.
  */
  public void setD01PRVEBL(BigDecimal newvalue)
  {
    fieldD01PRVEBL.setBigDecimal(newvalue);
  }

  /**
  * Return value of numeric field D01PRVEBL as a BigDecimal.
  */
  public BigDecimal getBigDecimalD01PRVEBL()
  {
    return fieldD01PRVEBL.getBigDecimal();
  }

}
