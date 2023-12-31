package datapro.eibs.beans;

import datapro.eibs.sockets.*;
import java.io.*;
import java.math.*;

import java.util.*;

/**
* Class generated by AS/400 CRTCLASS command from ELN006001 physical file definition.
* 
* File level identifier is 1030121191940.
* Record format level identifier is 42B84BCFAB84D.
*/

public class ELN006001Message extends MessageRecord
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
                                     "E01LNECUN",
                                     "E01LNENUM",
                                     "E01CUSNA1",
                                     "E01LNETYL",
                                     "E01LNEBNK",
                                     "E01LNEBRN",
                                     "E01LNECCY",
                                     "E01LNEGLN",
                                     "E01LNEAMN",
                                     "E01LNEREA",
                                     "E01LNEAMU",
                                     "E01LNEOFA",
                                     "E01LNEBAL",
                                     "E01LNECLT",
                                     "E01LNECYT",
                                     "E01LNEOCM",
                                     "E01LNEAVN",
                                     "E01LNEAVU",
                                     "E01LNEOP1",
                                     "E01LNEOP2",
                                     "E01LNEOP3",
                                     "E01LNEMT1",
                                     "E01LNEMT2",
                                     "E01LNEMT3",
                                     "E01LNERV1",
                                     "E01LNERV2",
                                     "E01LNERV3",
                                     "E01LNERE1",
                                     "E01LNERE2",
                                     "E01LNERE3",
                                     "E01LNEPUR",
                                     "E01LNEABY",
                                     "E01LNEOFC",
                                     "E01DSCOFC"
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
                                   "E01LNECUN",
                                   "E01LNENUM",
                                   "E01CUSNA1",
                                   "E01LNETYL",
                                   "E01LNEBNK",
                                   "E01LNEBRN",
                                   "E01LNECCY",
                                   "E01LNEGLN",
                                   "E01LNEAMN",
                                   "E01LNEREA",
                                   "E01LNEAMU",
                                   "E01LNEOFA",
                                   "E01LNEBAL",
                                   "E01LNECLT",
                                   "E01LNECYT",
                                   "E01LNEOCM",
                                   "E01LNEAVN",
                                   "E01LNEAVU",
                                   "E01LNEOP1",
                                   "E01LNEOP2",
                                   "E01LNEOP3",
                                   "E01LNEMT1",
                                   "E01LNEMT2",
                                   "E01LNEMT3",
                                   "E01LNERV1",
                                   "E01LNERV2",
                                   "E01LNERV3",
                                   "E01LNERE1",
                                   "E01LNERE2",
                                   "E01LNERE3",
                                   "E01LNEPUR",
                                   "E01LNEABY",
                                   "E01LNEOFC",
                                   "E01DSCOFC"
                                 };
  final static String fid = "1030121191940";
  final static String rid = "42B84BCFAB84D";
  final static String fmtname = "ELN006001";
  final int FIELDCOUNT = 43;
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
  private DecimalField fieldE01LNECUN = null;
  private DecimalField fieldE01LNENUM = null;
  private CharacterField fieldE01CUSNA1 = null;
  private CharacterField fieldE01LNETYL = null;
  private CharacterField fieldE01LNEBNK = null;
  private DecimalField fieldE01LNEBRN = null;
  private CharacterField fieldE01LNECCY = null;
  private DecimalField fieldE01LNEGLN = null;
  private DecimalField fieldE01LNEAMN = null;
  private DecimalField fieldE01LNEREA = null;
  private DecimalField fieldE01LNEAMU = null;
  private DecimalField fieldE01LNEOFA = null;
  private DecimalField fieldE01LNEBAL = null;
  private DecimalField fieldE01LNECLT = null;
  private DecimalField fieldE01LNECYT = null;
  private DecimalField fieldE01LNEOCM = null;
  private DecimalField fieldE01LNEAVN = null;
  private DecimalField fieldE01LNEAVU = null;
  private DecimalField fieldE01LNEOP1 = null;
  private DecimalField fieldE01LNEOP2 = null;
  private DecimalField fieldE01LNEOP3 = null;
  private DecimalField fieldE01LNEMT1 = null;
  private DecimalField fieldE01LNEMT2 = null;
  private DecimalField fieldE01LNEMT3 = null;
  private DecimalField fieldE01LNERV1 = null;
  private DecimalField fieldE01LNERV2 = null;
  private DecimalField fieldE01LNERV3 = null;
  private DecimalField fieldE01LNERE1 = null;
  private DecimalField fieldE01LNERE2 = null;
  private DecimalField fieldE01LNERE3 = null;
  private CharacterField fieldE01LNEPUR = null;
  private CharacterField fieldE01LNEABY = null;
  private CharacterField fieldE01LNEOFC = null;
  private CharacterField fieldE01DSCOFC = null;

  /**
  * Constructor for ELN006001Message.
  */
  public ELN006001Message()
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
    recordsize = 437;
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
    fields[9] = fieldE01LNECUN =
    new DecimalField(message, HEADERSIZE + 42, 10, 0, "E01LNECUN");
    fields[10] = fieldE01LNENUM =
    new DecimalField(message, HEADERSIZE + 52, 5, 0, "E01LNENUM");
    fields[11] = fieldE01CUSNA1 =
    new CharacterField(message, HEADERSIZE + 57, 45, "E01CUSNA1");
    fields[12] = fieldE01LNETYL =
    new CharacterField(message, HEADERSIZE + 102, 4, "E01LNETYL");
    fields[13] = fieldE01LNEBNK =
    new CharacterField(message, HEADERSIZE + 106, 2, "E01LNEBNK");
    fields[14] = fieldE01LNEBRN =
    new DecimalField(message, HEADERSIZE + 108, 4, 0, "E01LNEBRN");
    fields[15] = fieldE01LNECCY =
    new CharacterField(message, HEADERSIZE + 112, 3, "E01LNECCY");
    fields[16] = fieldE01LNEGLN =
    new DecimalField(message, HEADERSIZE + 115, 17, 0, "E01LNEGLN");
    fields[17] = fieldE01LNEAMN =
    new DecimalField(message, HEADERSIZE + 132, 17, 2, "E01LNEAMN");
    fields[18] = fieldE01LNEREA =
    new DecimalField(message, HEADERSIZE + 149, 17, 2, "E01LNEREA");
    fields[19] = fieldE01LNEAMU =
    new DecimalField(message, HEADERSIZE + 166, 17, 2, "E01LNEAMU");
    fields[20] = fieldE01LNEOFA =
    new DecimalField(message, HEADERSIZE + 183, 17, 2, "E01LNEOFA");
    fields[21] = fieldE01LNEBAL =
    new DecimalField(message, HEADERSIZE + 200, 17, 2, "E01LNEBAL");
    fields[22] = fieldE01LNECLT =
    new DecimalField(message, HEADERSIZE + 217, 17, 2, "E01LNECLT");
    fields[23] = fieldE01LNECYT =
    new DecimalField(message, HEADERSIZE + 234, 17, 2, "E01LNECYT");
    fields[24] = fieldE01LNEOCM =
    new DecimalField(message, HEADERSIZE + 251, 17, 2, "E01LNEOCM");
    fields[25] = fieldE01LNEAVN =
    new DecimalField(message, HEADERSIZE + 268, 17, 2, "E01LNEAVN");
    fields[26] = fieldE01LNEAVU =
    new DecimalField(message, HEADERSIZE + 285, 17, 2, "E01LNEAVU");
    fields[27] = fieldE01LNEOP1 =
    new DecimalField(message, HEADERSIZE + 302, 3, 0, "E01LNEOP1");
    fields[28] = fieldE01LNEOP2 =
    new DecimalField(message, HEADERSIZE + 305, 3, 0, "E01LNEOP2");
    fields[29] = fieldE01LNEOP3 =
    new DecimalField(message, HEADERSIZE + 308, 3, 0, "E01LNEOP3");
    fields[30] = fieldE01LNEMT1 =
    new DecimalField(message, HEADERSIZE + 311, 3, 0, "E01LNEMT1");
    fields[31] = fieldE01LNEMT2 =
    new DecimalField(message, HEADERSIZE + 314, 3, 0, "E01LNEMT2");
    fields[32] = fieldE01LNEMT3 =
    new DecimalField(message, HEADERSIZE + 317, 3, 0, "E01LNEMT3");
    fields[33] = fieldE01LNERV1 =
    new DecimalField(message, HEADERSIZE + 320, 3, 0, "E01LNERV1");
    fields[34] = fieldE01LNERV2 =
    new DecimalField(message, HEADERSIZE + 323, 3, 0, "E01LNERV2");
    fields[35] = fieldE01LNERV3 =
    new DecimalField(message, HEADERSIZE + 326, 3, 0, "E01LNERV3");
    fields[36] = fieldE01LNERE1 =
    new DecimalField(message, HEADERSIZE + 329, 3, 0, "E01LNERE1");
    fields[37] = fieldE01LNERE2 =
    new DecimalField(message, HEADERSIZE + 332, 3, 0, "E01LNERE2");
    fields[38] = fieldE01LNERE3 =
    new DecimalField(message, HEADERSIZE + 335, 3, 0, "E01LNERE3");
    fields[39] = fieldE01LNEPUR =
    new CharacterField(message, HEADERSIZE + 338, 35, "E01LNEPUR");
    fields[40] = fieldE01LNEABY =
    new CharacterField(message, HEADERSIZE + 373, 25, "E01LNEABY");
    fields[41] = fieldE01LNEOFC =
    new CharacterField(message, HEADERSIZE + 398, 4, "E01LNEOFC");
    fields[42] = fieldE01DSCOFC =
    new CharacterField(message, HEADERSIZE + 402, 35, "E01DSCOFC");

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
  * Set field E01LNECUN using a String value.
  */
  public void setE01LNECUN(String newvalue)
  {
    fieldE01LNECUN.setString(newvalue);
  }

  /**
  * Get value of field E01LNECUN as a String.
  */
  public String getE01LNECUN()
  {
    return fieldE01LNECUN.getString();
  }

  /**
  * Set numeric field E01LNECUN using a BigDecimal value.
  */
  public void setE01LNECUN(BigDecimal newvalue)
  {
    fieldE01LNECUN.setBigDecimal(newvalue);
  }

  /**
  * Return value of numeric field E01LNECUN as a BigDecimal.
  */
  public BigDecimal getBigDecimalE01LNECUN()
  {
    return fieldE01LNECUN.getBigDecimal();
  }

  /**
  * Set field E01LNENUM using a String value.
  */
  public void setE01LNENUM(String newvalue)
  {
    fieldE01LNENUM.setString(newvalue);
  }

  /**
  * Get value of field E01LNENUM as a String.
  */
  public String getE01LNENUM()
  {
    return fieldE01LNENUM.getString();
  }

  /**
  * Set numeric field E01LNENUM using a BigDecimal value.
  */
  public void setE01LNENUM(BigDecimal newvalue)
  {
    fieldE01LNENUM.setBigDecimal(newvalue);
  }

  /**
  * Return value of numeric field E01LNENUM as a BigDecimal.
  */
  public BigDecimal getBigDecimalE01LNENUM()
  {
    return fieldE01LNENUM.getBigDecimal();
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
  * Set field E01LNETYL using a String value.
  */
  public void setE01LNETYL(String newvalue)
  {
    fieldE01LNETYL.setString(newvalue);
  }

  /**
  * Get value of field E01LNETYL as a String.
  */
  public String getE01LNETYL()
  {
    return fieldE01LNETYL.getString();
  }

  /**
  * Set field E01LNEBNK using a String value.
  */
  public void setE01LNEBNK(String newvalue)
  {
    fieldE01LNEBNK.setString(newvalue);
  }

  /**
  * Get value of field E01LNEBNK as a String.
  */
  public String getE01LNEBNK()
  {
    return fieldE01LNEBNK.getString();
  }

  /**
  * Set field E01LNEBRN using a String value.
  */
  public void setE01LNEBRN(String newvalue)
  {
    fieldE01LNEBRN.setString(newvalue);
  }

  /**
  * Get value of field E01LNEBRN as a String.
  */
  public String getE01LNEBRN()
  {
    return fieldE01LNEBRN.getString();
  }

  /**
  * Set numeric field E01LNEBRN using a BigDecimal value.
  */
  public void setE01LNEBRN(BigDecimal newvalue)
  {
    fieldE01LNEBRN.setBigDecimal(newvalue);
  }

  /**
  * Return value of numeric field E01LNEBRN as a BigDecimal.
  */
  public BigDecimal getBigDecimalE01LNEBRN()
  {
    return fieldE01LNEBRN.getBigDecimal();
  }

  /**
  * Set field E01LNECCY using a String value.
  */
  public void setE01LNECCY(String newvalue)
  {
    fieldE01LNECCY.setString(newvalue);
  }

  /**
  * Get value of field E01LNECCY as a String.
  */
  public String getE01LNECCY()
  {
    return fieldE01LNECCY.getString();
  }

  /**
  * Set field E01LNEGLN using a String value.
  */
  public void setE01LNEGLN(String newvalue)
  {
    fieldE01LNEGLN.setString(newvalue);
  }

  /**
  * Get value of field E01LNEGLN as a String.
  */
  public String getE01LNEGLN()
  {
    return fieldE01LNEGLN.getString();
  }

  /**
  * Set numeric field E01LNEGLN using a BigDecimal value.
  */
  public void setE01LNEGLN(BigDecimal newvalue)
  {
    fieldE01LNEGLN.setBigDecimal(newvalue);
  }

  /**
  * Return value of numeric field E01LNEGLN as a BigDecimal.
  */
  public BigDecimal getBigDecimalE01LNEGLN()
  {
    return fieldE01LNEGLN.getBigDecimal();
  }

  /**
  * Set field E01LNEAMN using a String value.
  */
  public void setE01LNEAMN(String newvalue)
  {
    fieldE01LNEAMN.setString(newvalue);
  }

  /**
  * Get value of field E01LNEAMN as a String.
  */
  public String getE01LNEAMN()
  {
    return fieldE01LNEAMN.getString();
  }

  /**
  * Set numeric field E01LNEAMN using a BigDecimal value.
  */
  public void setE01LNEAMN(BigDecimal newvalue)
  {
    fieldE01LNEAMN.setBigDecimal(newvalue);
  }

  /**
  * Return value of numeric field E01LNEAMN as a BigDecimal.
  */
  public BigDecimal getBigDecimalE01LNEAMN()
  {
    return fieldE01LNEAMN.getBigDecimal();
  }

  /**
  * Set field E01LNEREA using a String value.
  */
  public void setE01LNEREA(String newvalue)
  {
    fieldE01LNEREA.setString(newvalue);
  }

  /**
  * Get value of field E01LNEREA as a String.
  */
  public String getE01LNEREA()
  {
    return fieldE01LNEREA.getString();
  }

  /**
  * Set numeric field E01LNEREA using a BigDecimal value.
  */
  public void setE01LNEREA(BigDecimal newvalue)
  {
    fieldE01LNEREA.setBigDecimal(newvalue);
  }

  /**
  * Return value of numeric field E01LNEREA as a BigDecimal.
  */
  public BigDecimal getBigDecimalE01LNEREA()
  {
    return fieldE01LNEREA.getBigDecimal();
  }

  /**
  * Set field E01LNEAMU using a String value.
  */
  public void setE01LNEAMU(String newvalue)
  {
    fieldE01LNEAMU.setString(newvalue);
  }

  /**
  * Get value of field E01LNEAMU as a String.
  */
  public String getE01LNEAMU()
  {
    return fieldE01LNEAMU.getString();
  }

  /**
  * Set numeric field E01LNEAMU using a BigDecimal value.
  */
  public void setE01LNEAMU(BigDecimal newvalue)
  {
    fieldE01LNEAMU.setBigDecimal(newvalue);
  }

  /**
  * Return value of numeric field E01LNEAMU as a BigDecimal.
  */
  public BigDecimal getBigDecimalE01LNEAMU()
  {
    return fieldE01LNEAMU.getBigDecimal();
  }

  /**
  * Set field E01LNEOFA using a String value.
  */
  public void setE01LNEOFA(String newvalue)
  {
    fieldE01LNEOFA.setString(newvalue);
  }

  /**
  * Get value of field E01LNEOFA as a String.
  */
  public String getE01LNEOFA()
  {
    return fieldE01LNEOFA.getString();
  }

  /**
  * Set numeric field E01LNEOFA using a BigDecimal value.
  */
  public void setE01LNEOFA(BigDecimal newvalue)
  {
    fieldE01LNEOFA.setBigDecimal(newvalue);
  }

  /**
  * Return value of numeric field E01LNEOFA as a BigDecimal.
  */
  public BigDecimal getBigDecimalE01LNEOFA()
  {
    return fieldE01LNEOFA.getBigDecimal();
  }

  /**
  * Set field E01LNEBAL using a String value.
  */
  public void setE01LNEBAL(String newvalue)
  {
    fieldE01LNEBAL.setString(newvalue);
  }

  /**
  * Get value of field E01LNEBAL as a String.
  */
  public String getE01LNEBAL()
  {
    return fieldE01LNEBAL.getString();
  }

  /**
  * Set numeric field E01LNEBAL using a BigDecimal value.
  */
  public void setE01LNEBAL(BigDecimal newvalue)
  {
    fieldE01LNEBAL.setBigDecimal(newvalue);
  }

  /**
  * Return value of numeric field E01LNEBAL as a BigDecimal.
  */
  public BigDecimal getBigDecimalE01LNEBAL()
  {
    return fieldE01LNEBAL.getBigDecimal();
  }

  /**
  * Set field E01LNECLT using a String value.
  */
  public void setE01LNECLT(String newvalue)
  {
    fieldE01LNECLT.setString(newvalue);
  }

  /**
  * Get value of field E01LNECLT as a String.
  */
  public String getE01LNECLT()
  {
    return fieldE01LNECLT.getString();
  }

  /**
  * Set numeric field E01LNECLT using a BigDecimal value.
  */
  public void setE01LNECLT(BigDecimal newvalue)
  {
    fieldE01LNECLT.setBigDecimal(newvalue);
  }

  /**
  * Return value of numeric field E01LNECLT as a BigDecimal.
  */
  public BigDecimal getBigDecimalE01LNECLT()
  {
    return fieldE01LNECLT.getBigDecimal();
  }

  /**
  * Set field E01LNECYT using a String value.
  */
  public void setE01LNECYT(String newvalue)
  {
    fieldE01LNECYT.setString(newvalue);
  }

  /**
  * Get value of field E01LNECYT as a String.
  */
  public String getE01LNECYT()
  {
    return fieldE01LNECYT.getString();
  }

  /**
  * Set numeric field E01LNECYT using a BigDecimal value.
  */
  public void setE01LNECYT(BigDecimal newvalue)
  {
    fieldE01LNECYT.setBigDecimal(newvalue);
  }

  /**
  * Return value of numeric field E01LNECYT as a BigDecimal.
  */
  public BigDecimal getBigDecimalE01LNECYT()
  {
    return fieldE01LNECYT.getBigDecimal();
  }

  /**
  * Set field E01LNEOCM using a String value.
  */
  public void setE01LNEOCM(String newvalue)
  {
    fieldE01LNEOCM.setString(newvalue);
  }

  /**
  * Get value of field E01LNEOCM as a String.
  */
  public String getE01LNEOCM()
  {
    return fieldE01LNEOCM.getString();
  }

  /**
  * Set numeric field E01LNEOCM using a BigDecimal value.
  */
  public void setE01LNEOCM(BigDecimal newvalue)
  {
    fieldE01LNEOCM.setBigDecimal(newvalue);
  }

  /**
  * Return value of numeric field E01LNEOCM as a BigDecimal.
  */
  public BigDecimal getBigDecimalE01LNEOCM()
  {
    return fieldE01LNEOCM.getBigDecimal();
  }

  /**
  * Set field E01LNEAVN using a String value.
  */
  public void setE01LNEAVN(String newvalue)
  {
    fieldE01LNEAVN.setString(newvalue);
  }

  /**
  * Get value of field E01LNEAVN as a String.
  */
  public String getE01LNEAVN()
  {
    return fieldE01LNEAVN.getString();
  }

  /**
  * Set numeric field E01LNEAVN using a BigDecimal value.
  */
  public void setE01LNEAVN(BigDecimal newvalue)
  {
    fieldE01LNEAVN.setBigDecimal(newvalue);
  }

  /**
  * Return value of numeric field E01LNEAVN as a BigDecimal.
  */
  public BigDecimal getBigDecimalE01LNEAVN()
  {
    return fieldE01LNEAVN.getBigDecimal();
  }

  /**
  * Set field E01LNEAVU using a String value.
  */
  public void setE01LNEAVU(String newvalue)
  {
    fieldE01LNEAVU.setString(newvalue);
  }

  /**
  * Get value of field E01LNEAVU as a String.
  */
  public String getE01LNEAVU()
  {
    return fieldE01LNEAVU.getString();
  }

  /**
  * Set numeric field E01LNEAVU using a BigDecimal value.
  */
  public void setE01LNEAVU(BigDecimal newvalue)
  {
    fieldE01LNEAVU.setBigDecimal(newvalue);
  }

  /**
  * Return value of numeric field E01LNEAVU as a BigDecimal.
  */
  public BigDecimal getBigDecimalE01LNEAVU()
  {
    return fieldE01LNEAVU.getBigDecimal();
  }

  /**
  * Set field E01LNEOP1 using a String value.
  */
  public void setE01LNEOP1(String newvalue)
  {
    fieldE01LNEOP1.setString(newvalue);
  }

  /**
  * Get value of field E01LNEOP1 as a String.
  */
  public String getE01LNEOP1()
  {
    return fieldE01LNEOP1.getString();
  }

  /**
  * Set numeric field E01LNEOP1 using a BigDecimal value.
  */
  public void setE01LNEOP1(BigDecimal newvalue)
  {
    fieldE01LNEOP1.setBigDecimal(newvalue);
  }

  /**
  * Return value of numeric field E01LNEOP1 as a BigDecimal.
  */
  public BigDecimal getBigDecimalE01LNEOP1()
  {
    return fieldE01LNEOP1.getBigDecimal();
  }

  /**
  * Set field E01LNEOP2 using a String value.
  */
  public void setE01LNEOP2(String newvalue)
  {
    fieldE01LNEOP2.setString(newvalue);
  }

  /**
  * Get value of field E01LNEOP2 as a String.
  */
  public String getE01LNEOP2()
  {
    return fieldE01LNEOP2.getString();
  }

  /**
  * Set numeric field E01LNEOP2 using a BigDecimal value.
  */
  public void setE01LNEOP2(BigDecimal newvalue)
  {
    fieldE01LNEOP2.setBigDecimal(newvalue);
  }

  /**
  * Return value of numeric field E01LNEOP2 as a BigDecimal.
  */
  public BigDecimal getBigDecimalE01LNEOP2()
  {
    return fieldE01LNEOP2.getBigDecimal();
  }

  /**
  * Set field E01LNEOP3 using a String value.
  */
  public void setE01LNEOP3(String newvalue)
  {
    fieldE01LNEOP3.setString(newvalue);
  }

  /**
  * Get value of field E01LNEOP3 as a String.
  */
  public String getE01LNEOP3()
  {
    return fieldE01LNEOP3.getString();
  }

  /**
  * Set numeric field E01LNEOP3 using a BigDecimal value.
  */
  public void setE01LNEOP3(BigDecimal newvalue)
  {
    fieldE01LNEOP3.setBigDecimal(newvalue);
  }

  /**
  * Return value of numeric field E01LNEOP3 as a BigDecimal.
  */
  public BigDecimal getBigDecimalE01LNEOP3()
  {
    return fieldE01LNEOP3.getBigDecimal();
  }

  /**
  * Set field E01LNEMT1 using a String value.
  */
  public void setE01LNEMT1(String newvalue)
  {
    fieldE01LNEMT1.setString(newvalue);
  }

  /**
  * Get value of field E01LNEMT1 as a String.
  */
  public String getE01LNEMT1()
  {
    return fieldE01LNEMT1.getString();
  }

  /**
  * Set numeric field E01LNEMT1 using a BigDecimal value.
  */
  public void setE01LNEMT1(BigDecimal newvalue)
  {
    fieldE01LNEMT1.setBigDecimal(newvalue);
  }

  /**
  * Return value of numeric field E01LNEMT1 as a BigDecimal.
  */
  public BigDecimal getBigDecimalE01LNEMT1()
  {
    return fieldE01LNEMT1.getBigDecimal();
  }

  /**
  * Set field E01LNEMT2 using a String value.
  */
  public void setE01LNEMT2(String newvalue)
  {
    fieldE01LNEMT2.setString(newvalue);
  }

  /**
  * Get value of field E01LNEMT2 as a String.
  */
  public String getE01LNEMT2()
  {
    return fieldE01LNEMT2.getString();
  }

  /**
  * Set numeric field E01LNEMT2 using a BigDecimal value.
  */
  public void setE01LNEMT2(BigDecimal newvalue)
  {
    fieldE01LNEMT2.setBigDecimal(newvalue);
  }

  /**
  * Return value of numeric field E01LNEMT2 as a BigDecimal.
  */
  public BigDecimal getBigDecimalE01LNEMT2()
  {
    return fieldE01LNEMT2.getBigDecimal();
  }

  /**
  * Set field E01LNEMT3 using a String value.
  */
  public void setE01LNEMT3(String newvalue)
  {
    fieldE01LNEMT3.setString(newvalue);
  }

  /**
  * Get value of field E01LNEMT3 as a String.
  */
  public String getE01LNEMT3()
  {
    return fieldE01LNEMT3.getString();
  }

  /**
  * Set numeric field E01LNEMT3 using a BigDecimal value.
  */
  public void setE01LNEMT3(BigDecimal newvalue)
  {
    fieldE01LNEMT3.setBigDecimal(newvalue);
  }

  /**
  * Return value of numeric field E01LNEMT3 as a BigDecimal.
  */
  public BigDecimal getBigDecimalE01LNEMT3()
  {
    return fieldE01LNEMT3.getBigDecimal();
  }

  /**
  * Set field E01LNERV1 using a String value.
  */
  public void setE01LNERV1(String newvalue)
  {
    fieldE01LNERV1.setString(newvalue);
  }

  /**
  * Get value of field E01LNERV1 as a String.
  */
  public String getE01LNERV1()
  {
    return fieldE01LNERV1.getString();
  }

  /**
  * Set numeric field E01LNERV1 using a BigDecimal value.
  */
  public void setE01LNERV1(BigDecimal newvalue)
  {
    fieldE01LNERV1.setBigDecimal(newvalue);
  }

  /**
  * Return value of numeric field E01LNERV1 as a BigDecimal.
  */
  public BigDecimal getBigDecimalE01LNERV1()
  {
    return fieldE01LNERV1.getBigDecimal();
  }

  /**
  * Set field E01LNERV2 using a String value.
  */
  public void setE01LNERV2(String newvalue)
  {
    fieldE01LNERV2.setString(newvalue);
  }

  /**
  * Get value of field E01LNERV2 as a String.
  */
  public String getE01LNERV2()
  {
    return fieldE01LNERV2.getString();
  }

  /**
  * Set numeric field E01LNERV2 using a BigDecimal value.
  */
  public void setE01LNERV2(BigDecimal newvalue)
  {
    fieldE01LNERV2.setBigDecimal(newvalue);
  }

  /**
  * Return value of numeric field E01LNERV2 as a BigDecimal.
  */
  public BigDecimal getBigDecimalE01LNERV2()
  {
    return fieldE01LNERV2.getBigDecimal();
  }

  /**
  * Set field E01LNERV3 using a String value.
  */
  public void setE01LNERV3(String newvalue)
  {
    fieldE01LNERV3.setString(newvalue);
  }

  /**
  * Get value of field E01LNERV3 as a String.
  */
  public String getE01LNERV3()
  {
    return fieldE01LNERV3.getString();
  }

  /**
  * Set numeric field E01LNERV3 using a BigDecimal value.
  */
  public void setE01LNERV3(BigDecimal newvalue)
  {
    fieldE01LNERV3.setBigDecimal(newvalue);
  }

  /**
  * Return value of numeric field E01LNERV3 as a BigDecimal.
  */
  public BigDecimal getBigDecimalE01LNERV3()
  {
    return fieldE01LNERV3.getBigDecimal();
  }

  /**
  * Set field E01LNERE1 using a String value.
  */
  public void setE01LNERE1(String newvalue)
  {
    fieldE01LNERE1.setString(newvalue);
  }

  /**
  * Get value of field E01LNERE1 as a String.
  */
  public String getE01LNERE1()
  {
    return fieldE01LNERE1.getString();
  }

  /**
  * Set numeric field E01LNERE1 using a BigDecimal value.
  */
  public void setE01LNERE1(BigDecimal newvalue)
  {
    fieldE01LNERE1.setBigDecimal(newvalue);
  }

  /**
  * Return value of numeric field E01LNERE1 as a BigDecimal.
  */
  public BigDecimal getBigDecimalE01LNERE1()
  {
    return fieldE01LNERE1.getBigDecimal();
  }

  /**
  * Set field E01LNERE2 using a String value.
  */
  public void setE01LNERE2(String newvalue)
  {
    fieldE01LNERE2.setString(newvalue);
  }

  /**
  * Get value of field E01LNERE2 as a String.
  */
  public String getE01LNERE2()
  {
    return fieldE01LNERE2.getString();
  }

  /**
  * Set numeric field E01LNERE2 using a BigDecimal value.
  */
  public void setE01LNERE2(BigDecimal newvalue)
  {
    fieldE01LNERE2.setBigDecimal(newvalue);
  }

  /**
  * Return value of numeric field E01LNERE2 as a BigDecimal.
  */
  public BigDecimal getBigDecimalE01LNERE2()
  {
    return fieldE01LNERE2.getBigDecimal();
  }

  /**
  * Set field E01LNERE3 using a String value.
  */
  public void setE01LNERE3(String newvalue)
  {
    fieldE01LNERE3.setString(newvalue);
  }

  /**
  * Get value of field E01LNERE3 as a String.
  */
  public String getE01LNERE3()
  {
    return fieldE01LNERE3.getString();
  }

  /**
  * Set numeric field E01LNERE3 using a BigDecimal value.
  */
  public void setE01LNERE3(BigDecimal newvalue)
  {
    fieldE01LNERE3.setBigDecimal(newvalue);
  }

  /**
  * Return value of numeric field E01LNERE3 as a BigDecimal.
  */
  public BigDecimal getBigDecimalE01LNERE3()
  {
    return fieldE01LNERE3.getBigDecimal();
  }

  /**
  * Set field E01LNEPUR using a String value.
  */
  public void setE01LNEPUR(String newvalue)
  {
    fieldE01LNEPUR.setString(newvalue);
  }

  /**
  * Get value of field E01LNEPUR as a String.
  */
  public String getE01LNEPUR()
  {
    return fieldE01LNEPUR.getString();
  }

  /**
  * Set field E01LNEABY using a String value.
  */
  public void setE01LNEABY(String newvalue)
  {
    fieldE01LNEABY.setString(newvalue);
  }

  /**
  * Get value of field E01LNEABY as a String.
  */
  public String getE01LNEABY()
  {
    return fieldE01LNEABY.getString();
  }

  /**
  * Set field E01LNEOFC using a String value.
  */
  public void setE01LNEOFC(String newvalue)
  {
    fieldE01LNEOFC.setString(newvalue);
  }

  /**
  * Get value of field E01LNEOFC as a String.
  */
  public String getE01LNEOFC()
  {
    return fieldE01LNEOFC.getString();
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

}
