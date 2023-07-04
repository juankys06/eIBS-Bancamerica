package datapro.eibs.beans;

import datapro.eibs.sockets.*;
import java.io.*;
import java.math.*;

import java.util.*;

/**
* Class generated by AS/400 CRTCLASS command from EIRA02002 physical file definition.
* 
* File level identifier is 1041109155248.
* Record format level identifier is 3CB26FD618E10.
*/

public class EIRA02002Message extends MessageRecord
{
  final static String fldnames[] = {
                                     "H02USERID",
                                     "H02PROGRM",
                                     "H02TIMSYS",
                                     "H02SCRCOD",
                                     "H02OPECOD",
                                     "H02FLGMAS",
                                     "H02FLGWK1",
                                     "H02FLGWK2",
                                     "H02FLGWK3",
                                     "E02IRABNK",
                                     "E02IRACUN",
                                     "E02IRAYEA",
                                     "D02IRATYP",
                                     "D02IRADSC",
                                     "D02BEGBAL",
                                     "D02TOTCON",
                                     "D02TOTDIS",
                                     "D02TOTINT",
                                     "D02TOTWHA",
                                     "D02ENDBAL",
                                     "D02CUSNA1",
                                     "D02CUSBD1",
                                     "D02CUSBD2",
                                     "D02CUSBD3"
                                   };
  final static String tnames[] = {
                                   "H02USERID",
                                   "H02PROGRM",
                                   "H02TIMSYS",
                                   "H02SCRCOD",
                                   "H02OPECOD",
                                   "H02FLGMAS",
                                   "H02FLGWK1",
                                   "H02FLGWK2",
                                   "H02FLGWK3",
                                   "E02IRABNK",
                                   "E02IRACUN",
                                   "E02IRAYEA",
                                   "D02IRATYP",
                                   "D02IRADSC",
                                   "D02BEGBAL",
                                   "D02TOTCON",
                                   "D02TOTDIS",
                                   "D02TOTINT",
                                   "D02TOTWHA",
                                   "D02ENDBAL",
                                   "D02CUSNA1",
                                   "D02CUSBD1",
                                   "D02CUSBD2",
                                   "D02CUSBD3"
                                 };
  final static String fid = "1041109155248";
  final static String rid = "3CB26FD618E10";
  final static String fmtname = "EIRA02002";
  final int FIELDCOUNT = 24;
  private static Hashtable tlookup = new Hashtable();
  private CharacterField fieldH02USERID = null;
  private CharacterField fieldH02PROGRM = null;
  private CharacterField fieldH02TIMSYS = null;
  private CharacterField fieldH02SCRCOD = null;
  private CharacterField fieldH02OPECOD = null;
  private CharacterField fieldH02FLGMAS = null;
  private CharacterField fieldH02FLGWK1 = null;
  private CharacterField fieldH02FLGWK2 = null;
  private CharacterField fieldH02FLGWK3 = null;
  private CharacterField fieldE02IRABNK = null;
  private DecimalField fieldE02IRACUN = null;
  private DecimalField fieldE02IRAYEA = null;
  private CharacterField fieldD02IRATYP = null;
  private CharacterField fieldD02IRADSC = null;
  private DecimalField fieldD02BEGBAL = null;
  private DecimalField fieldD02TOTCON = null;
  private DecimalField fieldD02TOTDIS = null;
  private DecimalField fieldD02TOTINT = null;
  private DecimalField fieldD02TOTWHA = null;
  private DecimalField fieldD02ENDBAL = null;
  private CharacterField fieldD02CUSNA1 = null;
  private DecimalField fieldD02CUSBD1 = null;
  private DecimalField fieldD02CUSBD2 = null;
  private DecimalField fieldD02CUSBD3 = null;

  /**
  * Constructor for EIRA02002Message.
  */
  public EIRA02002Message()
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
    recordsize = 219;
    fileid = fid;
    recordid = rid;
    message = new byte[getByteLength()];
    formatname = fmtname;
    fieldnames = fldnames;
    tagnames = tnames;
    fields = new MessageField[FIELDCOUNT];

    fields[0] = fieldH02USERID =
    new CharacterField(message, HEADERSIZE + 0, 10, "H02USERID");
    fields[1] = fieldH02PROGRM =
    new CharacterField(message, HEADERSIZE + 10, 10, "H02PROGRM");
    fields[2] = fieldH02TIMSYS =
    new CharacterField(message, HEADERSIZE + 20, 12, "H02TIMSYS");
    fields[3] = fieldH02SCRCOD =
    new CharacterField(message, HEADERSIZE + 32, 2, "H02SCRCOD");
    fields[4] = fieldH02OPECOD =
    new CharacterField(message, HEADERSIZE + 34, 4, "H02OPECOD");
    fields[5] = fieldH02FLGMAS =
    new CharacterField(message, HEADERSIZE + 38, 1, "H02FLGMAS");
    fields[6] = fieldH02FLGWK1 =
    new CharacterField(message, HEADERSIZE + 39, 1, "H02FLGWK1");
    fields[7] = fieldH02FLGWK2 =
    new CharacterField(message, HEADERSIZE + 40, 1, "H02FLGWK2");
    fields[8] = fieldH02FLGWK3 =
    new CharacterField(message, HEADERSIZE + 41, 1, "H02FLGWK3");
    fields[9] = fieldE02IRABNK =
    new CharacterField(message, HEADERSIZE + 42, 2, "E02IRABNK");
    fields[10] = fieldE02IRACUN =
    new DecimalField(message, HEADERSIZE + 44, 10, 0, "E02IRACUN");
    fields[11] = fieldE02IRAYEA =
    new DecimalField(message, HEADERSIZE + 54, 5, 0, "E02IRAYEA");
    fields[12] = fieldD02IRATYP =
    new CharacterField(message, HEADERSIZE + 59, 1, "D02IRATYP");
    fields[13] = fieldD02IRADSC =
    new CharacterField(message, HEADERSIZE + 60, 25, "D02IRADSC");
    fields[14] = fieldD02BEGBAL =
    new DecimalField(message, HEADERSIZE + 85, 15, 2, "D02BEGBAL");
    fields[15] = fieldD02TOTCON =
    new DecimalField(message, HEADERSIZE + 100, 15, 2, "D02TOTCON");
    fields[16] = fieldD02TOTDIS =
    new DecimalField(message, HEADERSIZE + 115, 15, 2, "D02TOTDIS");
    fields[17] = fieldD02TOTINT =
    new DecimalField(message, HEADERSIZE + 130, 15, 2, "D02TOTINT");
    fields[18] = fieldD02TOTWHA =
    new DecimalField(message, HEADERSIZE + 145, 15, 2, "D02TOTWHA");
    fields[19] = fieldD02ENDBAL =
    new DecimalField(message, HEADERSIZE + 160, 15, 2, "D02ENDBAL");
    fields[20] = fieldD02CUSNA1 =
    new CharacterField(message, HEADERSIZE + 175, 35, "D02CUSNA1");
    fields[21] = fieldD02CUSBD1 =
    new DecimalField(message, HEADERSIZE + 210, 3, 0, "D02CUSBD1");
    fields[22] = fieldD02CUSBD2 =
    new DecimalField(message, HEADERSIZE + 213, 3, 0, "D02CUSBD2");
    fields[23] = fieldD02CUSBD3 =
    new DecimalField(message, HEADERSIZE + 216, 3, 0, "D02CUSBD3");

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
  * Set field H02USERID using a String value.
  */
  public void setH02USERID(String newvalue)
  {
    fieldH02USERID.setString(newvalue);
  }

  /**
  * Get value of field H02USERID as a String.
  */
  public String getH02USERID()
  {
    return fieldH02USERID.getString();
  }

  /**
  * Set field H02PROGRM using a String value.
  */
  public void setH02PROGRM(String newvalue)
  {
    fieldH02PROGRM.setString(newvalue);
  }

  /**
  * Get value of field H02PROGRM as a String.
  */
  public String getH02PROGRM()
  {
    return fieldH02PROGRM.getString();
  }

  /**
  * Set field H02TIMSYS using a String value.
  */
  public void setH02TIMSYS(String newvalue)
  {
    fieldH02TIMSYS.setString(newvalue);
  }

  /**
  * Get value of field H02TIMSYS as a String.
  */
  public String getH02TIMSYS()
  {
    return fieldH02TIMSYS.getString();
  }

  /**
  * Set field H02SCRCOD using a String value.
  */
  public void setH02SCRCOD(String newvalue)
  {
    fieldH02SCRCOD.setString(newvalue);
  }

  /**
  * Get value of field H02SCRCOD as a String.
  */
  public String getH02SCRCOD()
  {
    return fieldH02SCRCOD.getString();
  }

  /**
  * Set field H02OPECOD using a String value.
  */
  public void setH02OPECOD(String newvalue)
  {
    fieldH02OPECOD.setString(newvalue);
  }

  /**
  * Get value of field H02OPECOD as a String.
  */
  public String getH02OPECOD()
  {
    return fieldH02OPECOD.getString();
  }

  /**
  * Set field H02FLGMAS using a String value.
  */
  public void setH02FLGMAS(String newvalue)
  {
    fieldH02FLGMAS.setString(newvalue);
  }

  /**
  * Get value of field H02FLGMAS as a String.
  */
  public String getH02FLGMAS()
  {
    return fieldH02FLGMAS.getString();
  }

  /**
  * Set field H02FLGWK1 using a String value.
  */
  public void setH02FLGWK1(String newvalue)
  {
    fieldH02FLGWK1.setString(newvalue);
  }

  /**
  * Get value of field H02FLGWK1 as a String.
  */
  public String getH02FLGWK1()
  {
    return fieldH02FLGWK1.getString();
  }

  /**
  * Set field H02FLGWK2 using a String value.
  */
  public void setH02FLGWK2(String newvalue)
  {
    fieldH02FLGWK2.setString(newvalue);
  }

  /**
  * Get value of field H02FLGWK2 as a String.
  */
  public String getH02FLGWK2()
  {
    return fieldH02FLGWK2.getString();
  }

  /**
  * Set field H02FLGWK3 using a String value.
  */
  public void setH02FLGWK3(String newvalue)
  {
    fieldH02FLGWK3.setString(newvalue);
  }

  /**
  * Get value of field H02FLGWK3 as a String.
  */
  public String getH02FLGWK3()
  {
    return fieldH02FLGWK3.getString();
  }

  /**
  * Set field E02IRABNK using a String value.
  */
  public void setE02IRABNK(String newvalue)
  {
    fieldE02IRABNK.setString(newvalue);
  }

  /**
  * Get value of field E02IRABNK as a String.
  */
  public String getE02IRABNK()
  {
    return fieldE02IRABNK.getString();
  }

  /**
  * Set field E02IRACUN using a String value.
  */
  public void setE02IRACUN(String newvalue)
  {
    fieldE02IRACUN.setString(newvalue);
  }

  /**
  * Get value of field E02IRACUN as a String.
  */
  public String getE02IRACUN()
  {
    return fieldE02IRACUN.getString();
  }

  /**
  * Set numeric field E02IRACUN using a BigDecimal value.
  */
  public void setE02IRACUN(BigDecimal newvalue)
  {
    fieldE02IRACUN.setBigDecimal(newvalue);
  }

  /**
  * Return value of numeric field E02IRACUN as a BigDecimal.
  */
  public BigDecimal getBigDecimalE02IRACUN()
  {
    return fieldE02IRACUN.getBigDecimal();
  }

  /**
  * Set field E02IRAYEA using a String value.
  */
  public void setE02IRAYEA(String newvalue)
  {
    fieldE02IRAYEA.setString(newvalue);
  }

  /**
  * Get value of field E02IRAYEA as a String.
  */
  public String getE02IRAYEA()
  {
    return fieldE02IRAYEA.getString();
  }

  /**
  * Set numeric field E02IRAYEA using a BigDecimal value.
  */
  public void setE02IRAYEA(BigDecimal newvalue)
  {
    fieldE02IRAYEA.setBigDecimal(newvalue);
  }

  /**
  * Return value of numeric field E02IRAYEA as a BigDecimal.
  */
  public BigDecimal getBigDecimalE02IRAYEA()
  {
    return fieldE02IRAYEA.getBigDecimal();
  }

  /**
  * Set field D02IRATYP using a String value.
  */
  public void setD02IRATYP(String newvalue)
  {
    fieldD02IRATYP.setString(newvalue);
  }

  /**
  * Get value of field D02IRATYP as a String.
  */
  public String getD02IRATYP()
  {
    return fieldD02IRATYP.getString();
  }

  /**
  * Set field D02IRADSC using a String value.
  */
  public void setD02IRADSC(String newvalue)
  {
    fieldD02IRADSC.setString(newvalue);
  }

  /**
  * Get value of field D02IRADSC as a String.
  */
  public String getD02IRADSC()
  {
    return fieldD02IRADSC.getString();
  }

  /**
  * Set field D02BEGBAL using a String value.
  */
  public void setD02BEGBAL(String newvalue)
  {
    fieldD02BEGBAL.setString(newvalue);
  }

  /**
  * Get value of field D02BEGBAL as a String.
  */
  public String getD02BEGBAL()
  {
    return fieldD02BEGBAL.getString();
  }

  /**
  * Set numeric field D02BEGBAL using a BigDecimal value.
  */
  public void setD02BEGBAL(BigDecimal newvalue)
  {
    fieldD02BEGBAL.setBigDecimal(newvalue);
  }

  /**
  * Return value of numeric field D02BEGBAL as a BigDecimal.
  */
  public BigDecimal getBigDecimalD02BEGBAL()
  {
    return fieldD02BEGBAL.getBigDecimal();
  }

  /**
  * Set field D02TOTCON using a String value.
  */
  public void setD02TOTCON(String newvalue)
  {
    fieldD02TOTCON.setString(newvalue);
  }

  /**
  * Get value of field D02TOTCON as a String.
  */
  public String getD02TOTCON()
  {
    return fieldD02TOTCON.getString();
  }

  /**
  * Set numeric field D02TOTCON using a BigDecimal value.
  */
  public void setD02TOTCON(BigDecimal newvalue)
  {
    fieldD02TOTCON.setBigDecimal(newvalue);
  }

  /**
  * Return value of numeric field D02TOTCON as a BigDecimal.
  */
  public BigDecimal getBigDecimalD02TOTCON()
  {
    return fieldD02TOTCON.getBigDecimal();
  }

  /**
  * Set field D02TOTDIS using a String value.
  */
  public void setD02TOTDIS(String newvalue)
  {
    fieldD02TOTDIS.setString(newvalue);
  }

  /**
  * Get value of field D02TOTDIS as a String.
  */
  public String getD02TOTDIS()
  {
    return fieldD02TOTDIS.getString();
  }

  /**
  * Set numeric field D02TOTDIS using a BigDecimal value.
  */
  public void setD02TOTDIS(BigDecimal newvalue)
  {
    fieldD02TOTDIS.setBigDecimal(newvalue);
  }

  /**
  * Return value of numeric field D02TOTDIS as a BigDecimal.
  */
  public BigDecimal getBigDecimalD02TOTDIS()
  {
    return fieldD02TOTDIS.getBigDecimal();
  }

  /**
  * Set field D02TOTINT using a String value.
  */
  public void setD02TOTINT(String newvalue)
  {
    fieldD02TOTINT.setString(newvalue);
  }

  /**
  * Get value of field D02TOTINT as a String.
  */
  public String getD02TOTINT()
  {
    return fieldD02TOTINT.getString();
  }

  /**
  * Set numeric field D02TOTINT using a BigDecimal value.
  */
  public void setD02TOTINT(BigDecimal newvalue)
  {
    fieldD02TOTINT.setBigDecimal(newvalue);
  }

  /**
  * Return value of numeric field D02TOTINT as a BigDecimal.
  */
  public BigDecimal getBigDecimalD02TOTINT()
  {
    return fieldD02TOTINT.getBigDecimal();
  }

  /**
  * Set field D02TOTWHA using a String value.
  */
  public void setD02TOTWHA(String newvalue)
  {
    fieldD02TOTWHA.setString(newvalue);
  }

  /**
  * Get value of field D02TOTWHA as a String.
  */
  public String getD02TOTWHA()
  {
    return fieldD02TOTWHA.getString();
  }

  /**
  * Set numeric field D02TOTWHA using a BigDecimal value.
  */
  public void setD02TOTWHA(BigDecimal newvalue)
  {
    fieldD02TOTWHA.setBigDecimal(newvalue);
  }

  /**
  * Return value of numeric field D02TOTWHA as a BigDecimal.
  */
  public BigDecimal getBigDecimalD02TOTWHA()
  {
    return fieldD02TOTWHA.getBigDecimal();
  }

  /**
  * Set field D02ENDBAL using a String value.
  */
  public void setD02ENDBAL(String newvalue)
  {
    fieldD02ENDBAL.setString(newvalue);
  }

  /**
  * Get value of field D02ENDBAL as a String.
  */
  public String getD02ENDBAL()
  {
    return fieldD02ENDBAL.getString();
  }

  /**
  * Set numeric field D02ENDBAL using a BigDecimal value.
  */
  public void setD02ENDBAL(BigDecimal newvalue)
  {
    fieldD02ENDBAL.setBigDecimal(newvalue);
  }

  /**
  * Return value of numeric field D02ENDBAL as a BigDecimal.
  */
  public BigDecimal getBigDecimalD02ENDBAL()
  {
    return fieldD02ENDBAL.getBigDecimal();
  }

  /**
  * Set field D02CUSNA1 using a String value.
  */
  public void setD02CUSNA1(String newvalue)
  {
    fieldD02CUSNA1.setString(newvalue);
  }

  /**
  * Get value of field D02CUSNA1 as a String.
  */
  public String getD02CUSNA1()
  {
    return fieldD02CUSNA1.getString();
  }

  /**
  * Set field D02CUSBD1 using a String value.
  */
  public void setD02CUSBD1(String newvalue)
  {
    fieldD02CUSBD1.setString(newvalue);
  }

  /**
  * Get value of field D02CUSBD1 as a String.
  */
  public String getD02CUSBD1()
  {
    return fieldD02CUSBD1.getString();
  }

  /**
  * Set numeric field D02CUSBD1 using a BigDecimal value.
  */
  public void setD02CUSBD1(BigDecimal newvalue)
  {
    fieldD02CUSBD1.setBigDecimal(newvalue);
  }

  /**
  * Return value of numeric field D02CUSBD1 as a BigDecimal.
  */
  public BigDecimal getBigDecimalD02CUSBD1()
  {
    return fieldD02CUSBD1.getBigDecimal();
  }

  /**
  * Set field D02CUSBD2 using a String value.
  */
  public void setD02CUSBD2(String newvalue)
  {
    fieldD02CUSBD2.setString(newvalue);
  }

  /**
  * Get value of field D02CUSBD2 as a String.
  */
  public String getD02CUSBD2()
  {
    return fieldD02CUSBD2.getString();
  }

  /**
  * Set numeric field D02CUSBD2 using a BigDecimal value.
  */
  public void setD02CUSBD2(BigDecimal newvalue)
  {
    fieldD02CUSBD2.setBigDecimal(newvalue);
  }

  /**
  * Return value of numeric field D02CUSBD2 as a BigDecimal.
  */
  public BigDecimal getBigDecimalD02CUSBD2()
  {
    return fieldD02CUSBD2.getBigDecimal();
  }

  /**
  * Set field D02CUSBD3 using a String value.
  */
  public void setD02CUSBD3(String newvalue)
  {
    fieldD02CUSBD3.setString(newvalue);
  }

  /**
  * Get value of field D02CUSBD3 as a String.
  */
  public String getD02CUSBD3()
  {
    return fieldD02CUSBD3.getString();
  }

  /**
  * Set numeric field D02CUSBD3 using a BigDecimal value.
  */
  public void setD02CUSBD3(BigDecimal newvalue)
  {
    fieldD02CUSBD3.setBigDecimal(newvalue);
  }

  /**
  * Return value of numeric field D02CUSBD3 as a BigDecimal.
  */
  public BigDecimal getBigDecimalD02CUSBD3()
  {
    return fieldD02CUSBD3.getBigDecimal();
  }

}