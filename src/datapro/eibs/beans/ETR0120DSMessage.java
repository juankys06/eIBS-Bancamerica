package datapro.eibs.beans;

import datapro.eibs.sockets.*;
import java.io.*;
import java.math.*;

import java.util.*;

/**
* Class generated by AS/400 CRTCLASS command from ETR0120DS physical file definition.
* 
* File level identifier is 1060822183932.
* Record format level identifier is 4A753AA3F5F91.
*/

public class ETR0120DSMessage extends MessageRecord
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
                                     "E01WFRDID",
                                     "E01WFRREF",
                                     "E01WFRBNK",
                                     "E01WFRBRN",
                                     "E01WFRCCY",
                                     "E01WFRACC",
                                     "E01WFRGLN",
                                     "E01WFRTYP",
                                     "E01WFRSBT",
                                     "E01WFRPRO",
                                     "E01WFRITP",
                                     "E01WFRDD1",
                                     "E01WFRDD2",
                                     "E01WFRDD3",
                                     "E01WFRVD1",
                                     "E01WFRVD2",
                                     "E01WFRVD3",
                                     "E01WFRMA1",
                                     "E01WFRMA2",
                                     "E01WFRMA3",
                                     "E01WFRCUN",
                                     "E01WFRRTE",
                                     "E01WFROAM",
                                     "E01WFRLCU",
                                     "E01WFRCMM",
                                     "E01WFRHEF",
                                     "E01WFRHEM",
                                     "E01WFROT1",
                                     "E01WFROT2",
                                     "D01WFRCP1",
                                     "D01WFRCP2",
                                     "D01WFRCP3",
                                     "D01LIMAMT",
                                     "D01LIMAVL",
                                     "D01LIMEND",
                                     "D01FEOLIM",
                                     "D01FEOAVL",
                                     "D01FEOEND",
                                     "D01USRDSC"
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
                                   "E01WFRDID",
                                   "E01WFRREF",
                                   "E01WFRBNK",
                                   "E01WFRBRN",
                                   "E01WFRCCY",
                                   "E01WFRACC",
                                   "E01WFRGLN",
                                   "E01WFRTYP",
                                   "E01WFRSBT",
                                   "E01WFRPRO",
                                   "E01WFRITP",
                                   "E01WFRDD1",
                                   "E01WFRDD2",
                                   "E01WFRDD3",
                                   "E01WFRVD1",
                                   "E01WFRVD2",
                                   "E01WFRVD3",
                                   "E01WFRMA1",
                                   "E01WFRMA2",
                                   "E01WFRMA3",
                                   "E01WFRCUN",
                                   "E01WFRRTE",
                                   "E01WFROAM",
                                   "E01WFRLCU",
                                   "E01WFRCMM",
                                   "E01WFRHEF",
                                   "E01WFRHEM",
                                   "E01WFROT1",
                                   "E01WFROT2",
                                   "D01WFRCP1",
                                   "D01WFRCP2",
                                   "D01WFRCP3",
                                   "D01LIMAMT",
                                   "D01LIMAVL",
                                   "D01LIMEND",
                                   "D01FEOLIM",
                                   "D01FEOAVL",
                                   "D01FEOEND",
                                   "D01USRDSC"
                                 };
  final static String fid = "1060822183932";
  final static String rid = "4A753AA3F5F91";
  final static String fmtname = "ETR0120DS";
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
  private CharacterField fieldE01WFRDID = null;
  private CharacterField fieldE01WFRREF = null;
  private CharacterField fieldE01WFRBNK = null;
  private DecimalField fieldE01WFRBRN = null;
  private CharacterField fieldE01WFRCCY = null;
  private DecimalField fieldE01WFRACC = null;
  private DecimalField fieldE01WFRGLN = null;
  private CharacterField fieldE01WFRTYP = null;
  private CharacterField fieldE01WFRSBT = null;
  private CharacterField fieldE01WFRPRO = null;
  private CharacterField fieldE01WFRITP = null;
  private DecimalField fieldE01WFRDD1 = null;
  private DecimalField fieldE01WFRDD2 = null;
  private DecimalField fieldE01WFRDD3 = null;
  private DecimalField fieldE01WFRVD1 = null;
  private DecimalField fieldE01WFRVD2 = null;
  private DecimalField fieldE01WFRVD3 = null;
  private DecimalField fieldE01WFRMA1 = null;
  private DecimalField fieldE01WFRMA2 = null;
  private DecimalField fieldE01WFRMA3 = null;
  private DecimalField fieldE01WFRCUN = null;
  private DecimalField fieldE01WFRRTE = null;
  private DecimalField fieldE01WFROAM = null;
  private DecimalField fieldE01WFRLCU = null;
  private DecimalField fieldE01WFRCMM = null;
  private CharacterField fieldE01WFRHEF = null;
  private DecimalField fieldE01WFRHEM = null;
  private CharacterField fieldE01WFROT1 = null;
  private CharacterField fieldE01WFROT2 = null;
  private CharacterField fieldD01WFRCP1 = null;
  private CharacterField fieldD01WFRCP2 = null;
  private CharacterField fieldD01WFRCP3 = null;
  private DecimalField fieldD01LIMAMT = null;
  private DecimalField fieldD01LIMAVL = null;
  private DecimalField fieldD01LIMEND = null;
  private DecimalField fieldD01FEOLIM = null;
  private DecimalField fieldD01FEOAVL = null;
  private DecimalField fieldD01FEOEND = null;
  private CharacterField fieldD01USRDSC = null;

  /**
  * Constructor for ETR0120DSMessage.
  */
  public ETR0120DSMessage()
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
    recordsize = 571;
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
    fields[9] = fieldE01WFRDID =
    new CharacterField(message, HEADERSIZE + 42, 10, "E01WFRDID");
    fields[10] = fieldE01WFRREF =
    new CharacterField(message, HEADERSIZE + 52, 12, "E01WFRREF");
    fields[11] = fieldE01WFRBNK =
    new CharacterField(message, HEADERSIZE + 64, 2, "E01WFRBNK");
    fields[12] = fieldE01WFRBRN =
    new DecimalField(message, HEADERSIZE + 66, 4, 0, "E01WFRBRN");
    fields[13] = fieldE01WFRCCY =
    new CharacterField(message, HEADERSIZE + 70, 3, "E01WFRCCY");
    fields[14] = fieldE01WFRACC =
    new DecimalField(message, HEADERSIZE + 73, 13, 0, "E01WFRACC");
    fields[15] = fieldE01WFRGLN =
    new DecimalField(message, HEADERSIZE + 86, 17, 0, "E01WFRGLN");
    fields[16] = fieldE01WFRTYP =
    new CharacterField(message, HEADERSIZE + 103, 4, "E01WFRTYP");
    fields[17] = fieldE01WFRSBT =
    new CharacterField(message, HEADERSIZE + 107, 4, "E01WFRSBT");
    fields[18] = fieldE01WFRPRO =
    new CharacterField(message, HEADERSIZE + 111, 4, "E01WFRPRO");
    fields[19] = fieldE01WFRITP =
    new CharacterField(message, HEADERSIZE + 115, 5, "E01WFRITP");
    fields[20] = fieldE01WFRDD1 =
    new DecimalField(message, HEADERSIZE + 120, 3, 0, "E01WFRDD1");
    fields[21] = fieldE01WFRDD2 =
    new DecimalField(message, HEADERSIZE + 123, 3, 0, "E01WFRDD2");
    fields[22] = fieldE01WFRDD3 =
    new DecimalField(message, HEADERSIZE + 126, 3, 0, "E01WFRDD3");
    fields[23] = fieldE01WFRVD1 =
    new DecimalField(message, HEADERSIZE + 129, 3, 0, "E01WFRVD1");
    fields[24] = fieldE01WFRVD2 =
    new DecimalField(message, HEADERSIZE + 132, 3, 0, "E01WFRVD2");
    fields[25] = fieldE01WFRVD3 =
    new DecimalField(message, HEADERSIZE + 135, 3, 0, "E01WFRVD3");
    fields[26] = fieldE01WFRMA1 =
    new DecimalField(message, HEADERSIZE + 138, 3, 0, "E01WFRMA1");
    fields[27] = fieldE01WFRMA2 =
    new DecimalField(message, HEADERSIZE + 141, 3, 0, "E01WFRMA2");
    fields[28] = fieldE01WFRMA3 =
    new DecimalField(message, HEADERSIZE + 144, 3, 0, "E01WFRMA3");
    fields[29] = fieldE01WFRCUN =
    new DecimalField(message, HEADERSIZE + 147, 10, 0, "E01WFRCUN");
    fields[30] = fieldE01WFRRTE =
    new DecimalField(message, HEADERSIZE + 157, 11, 6, "E01WFRRTE");
    fields[31] = fieldE01WFROAM =
    new DecimalField(message, HEADERSIZE + 168, 17, 2, "E01WFROAM");
    fields[32] = fieldE01WFRLCU =
    new DecimalField(message, HEADERSIZE + 185, 10, 0, "E01WFRLCU");
    fields[33] = fieldE01WFRCMM =
    new DecimalField(message, HEADERSIZE + 195, 5, 0, "E01WFRCMM");
    fields[34] = fieldE01WFRHEF =
    new CharacterField(message, HEADERSIZE + 200, 1, "E01WFRHEF");
    fields[35] = fieldE01WFRHEM =
    new DecimalField(message, HEADERSIZE + 201, 13, 0, "E01WFRHEM");
    fields[36] = fieldE01WFROT1 =
    new CharacterField(message, HEADERSIZE + 214, 60, "E01WFROT1");
    fields[37] = fieldE01WFROT2 =
    new CharacterField(message, HEADERSIZE + 274, 60, "E01WFROT2");
    fields[38] = fieldD01WFRCP1 =
    new CharacterField(message, HEADERSIZE + 334, 35, "D01WFRCP1");
    fields[39] = fieldD01WFRCP2 =
    new CharacterField(message, HEADERSIZE + 369, 35, "D01WFRCP2");
    fields[40] = fieldD01WFRCP3 =
    new CharacterField(message, HEADERSIZE + 404, 35, "D01WFRCP3");
    fields[41] = fieldD01LIMAMT =
    new DecimalField(message, HEADERSIZE + 439, 17, 2, "D01LIMAMT");
    fields[42] = fieldD01LIMAVL =
    new DecimalField(message, HEADERSIZE + 456, 17, 2, "D01LIMAVL");
    fields[43] = fieldD01LIMEND =
    new DecimalField(message, HEADERSIZE + 473, 17, 2, "D01LIMEND");
    fields[44] = fieldD01FEOLIM =
    new DecimalField(message, HEADERSIZE + 490, 17, 2, "D01FEOLIM");
    fields[45] = fieldD01FEOAVL =
    new DecimalField(message, HEADERSIZE + 507, 17, 2, "D01FEOAVL");
    fields[46] = fieldD01FEOEND =
    new DecimalField(message, HEADERSIZE + 524, 17, 2, "D01FEOEND");
    fields[47] = fieldD01USRDSC =
    new CharacterField(message, HEADERSIZE + 541, 30, "D01USRDSC");

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
  * Set field E01WFRDID using a String value.
  */
  public void setE01WFRDID(String newvalue)
  {
    fieldE01WFRDID.setString(newvalue);
  }

  /**
  * Get value of field E01WFRDID as a String.
  */
  public String getE01WFRDID()
  {
    return fieldE01WFRDID.getString();
  }

  /**
  * Set field E01WFRREF using a String value.
  */
  public void setE01WFRREF(String newvalue)
  {
    fieldE01WFRREF.setString(newvalue);
  }

  /**
  * Get value of field E01WFRREF as a String.
  */
  public String getE01WFRREF()
  {
    return fieldE01WFRREF.getString();
  }

  /**
  * Set field E01WFRBNK using a String value.
  */
  public void setE01WFRBNK(String newvalue)
  {
    fieldE01WFRBNK.setString(newvalue);
  }

  /**
  * Get value of field E01WFRBNK as a String.
  */
  public String getE01WFRBNK()
  {
    return fieldE01WFRBNK.getString();
  }

  /**
  * Set field E01WFRBRN using a String value.
  */
  public void setE01WFRBRN(String newvalue)
  {
    fieldE01WFRBRN.setString(newvalue);
  }

  /**
  * Get value of field E01WFRBRN as a String.
  */
  public String getE01WFRBRN()
  {
    return fieldE01WFRBRN.getString();
  }

  /**
  * Set numeric field E01WFRBRN using a BigDecimal value.
  */
  public void setE01WFRBRN(BigDecimal newvalue)
  {
    fieldE01WFRBRN.setBigDecimal(newvalue);
  }

  /**
  * Return value of numeric field E01WFRBRN as a BigDecimal.
  */
  public BigDecimal getBigDecimalE01WFRBRN()
  {
    return fieldE01WFRBRN.getBigDecimal();
  }

  /**
  * Set field E01WFRCCY using a String value.
  */
  public void setE01WFRCCY(String newvalue)
  {
    fieldE01WFRCCY.setString(newvalue);
  }

  /**
  * Get value of field E01WFRCCY as a String.
  */
  public String getE01WFRCCY()
  {
    return fieldE01WFRCCY.getString();
  }

  /**
  * Set field E01WFRACC using a String value.
  */
  public void setE01WFRACC(String newvalue)
  {
    fieldE01WFRACC.setString(newvalue);
  }

  /**
  * Get value of field E01WFRACC as a String.
  */
  public String getE01WFRACC()
  {
    return fieldE01WFRACC.getString();
  }

  /**
  * Set numeric field E01WFRACC using a BigDecimal value.
  */
  public void setE01WFRACC(BigDecimal newvalue)
  {
    fieldE01WFRACC.setBigDecimal(newvalue);
  }

  /**
  * Return value of numeric field E01WFRACC as a BigDecimal.
  */
  public BigDecimal getBigDecimalE01WFRACC()
  {
    return fieldE01WFRACC.getBigDecimal();
  }

  /**
  * Set field E01WFRGLN using a String value.
  */
  public void setE01WFRGLN(String newvalue)
  {
    fieldE01WFRGLN.setString(newvalue);
  }

  /**
  * Get value of field E01WFRGLN as a String.
  */
  public String getE01WFRGLN()
  {
    return fieldE01WFRGLN.getString();
  }

  /**
  * Set numeric field E01WFRGLN using a BigDecimal value.
  */
  public void setE01WFRGLN(BigDecimal newvalue)
  {
    fieldE01WFRGLN.setBigDecimal(newvalue);
  }

  /**
  * Return value of numeric field E01WFRGLN as a BigDecimal.
  */
  public BigDecimal getBigDecimalE01WFRGLN()
  {
    return fieldE01WFRGLN.getBigDecimal();
  }

  /**
  * Set field E01WFRTYP using a String value.
  */
  public void setE01WFRTYP(String newvalue)
  {
    fieldE01WFRTYP.setString(newvalue);
  }

  /**
  * Get value of field E01WFRTYP as a String.
  */
  public String getE01WFRTYP()
  {
    return fieldE01WFRTYP.getString();
  }

  /**
  * Set field E01WFRSBT using a String value.
  */
  public void setE01WFRSBT(String newvalue)
  {
    fieldE01WFRSBT.setString(newvalue);
  }

  /**
  * Get value of field E01WFRSBT as a String.
  */
  public String getE01WFRSBT()
  {
    return fieldE01WFRSBT.getString();
  }

  /**
  * Set field E01WFRPRO using a String value.
  */
  public void setE01WFRPRO(String newvalue)
  {
    fieldE01WFRPRO.setString(newvalue);
  }

  /**
  * Get value of field E01WFRPRO as a String.
  */
  public String getE01WFRPRO()
  {
    return fieldE01WFRPRO.getString();
  }

  /**
  * Set field E01WFRITP using a String value.
  */
  public void setE01WFRITP(String newvalue)
  {
    fieldE01WFRITP.setString(newvalue);
  }

  /**
  * Get value of field E01WFRITP as a String.
  */
  public String getE01WFRITP()
  {
    return fieldE01WFRITP.getString();
  }

  /**
  * Set field E01WFRDD1 using a String value.
  */
  public void setE01WFRDD1(String newvalue)
  {
    fieldE01WFRDD1.setString(newvalue);
  }

  /**
  * Get value of field E01WFRDD1 as a String.
  */
  public String getE01WFRDD1()
  {
    return fieldE01WFRDD1.getString();
  }

  /**
  * Set numeric field E01WFRDD1 using a BigDecimal value.
  */
  public void setE01WFRDD1(BigDecimal newvalue)
  {
    fieldE01WFRDD1.setBigDecimal(newvalue);
  }

  /**
  * Return value of numeric field E01WFRDD1 as a BigDecimal.
  */
  public BigDecimal getBigDecimalE01WFRDD1()
  {
    return fieldE01WFRDD1.getBigDecimal();
  }

  /**
  * Set field E01WFRDD2 using a String value.
  */
  public void setE01WFRDD2(String newvalue)
  {
    fieldE01WFRDD2.setString(newvalue);
  }

  /**
  * Get value of field E01WFRDD2 as a String.
  */
  public String getE01WFRDD2()
  {
    return fieldE01WFRDD2.getString();
  }

  /**
  * Set numeric field E01WFRDD2 using a BigDecimal value.
  */
  public void setE01WFRDD2(BigDecimal newvalue)
  {
    fieldE01WFRDD2.setBigDecimal(newvalue);
  }

  /**
  * Return value of numeric field E01WFRDD2 as a BigDecimal.
  */
  public BigDecimal getBigDecimalE01WFRDD2()
  {
    return fieldE01WFRDD2.getBigDecimal();
  }

  /**
  * Set field E01WFRDD3 using a String value.
  */
  public void setE01WFRDD3(String newvalue)
  {
    fieldE01WFRDD3.setString(newvalue);
  }

  /**
  * Get value of field E01WFRDD3 as a String.
  */
  public String getE01WFRDD3()
  {
    return fieldE01WFRDD3.getString();
  }

  /**
  * Set numeric field E01WFRDD3 using a BigDecimal value.
  */
  public void setE01WFRDD3(BigDecimal newvalue)
  {
    fieldE01WFRDD3.setBigDecimal(newvalue);
  }

  /**
  * Return value of numeric field E01WFRDD3 as a BigDecimal.
  */
  public BigDecimal getBigDecimalE01WFRDD3()
  {
    return fieldE01WFRDD3.getBigDecimal();
  }

  /**
  * Set field E01WFRVD1 using a String value.
  */
  public void setE01WFRVD1(String newvalue)
  {
    fieldE01WFRVD1.setString(newvalue);
  }

  /**
  * Get value of field E01WFRVD1 as a String.
  */
  public String getE01WFRVD1()
  {
    return fieldE01WFRVD1.getString();
  }

  /**
  * Set numeric field E01WFRVD1 using a BigDecimal value.
  */
  public void setE01WFRVD1(BigDecimal newvalue)
  {
    fieldE01WFRVD1.setBigDecimal(newvalue);
  }

  /**
  * Return value of numeric field E01WFRVD1 as a BigDecimal.
  */
  public BigDecimal getBigDecimalE01WFRVD1()
  {
    return fieldE01WFRVD1.getBigDecimal();
  }

  /**
  * Set field E01WFRVD2 using a String value.
  */
  public void setE01WFRVD2(String newvalue)
  {
    fieldE01WFRVD2.setString(newvalue);
  }

  /**
  * Get value of field E01WFRVD2 as a String.
  */
  public String getE01WFRVD2()
  {
    return fieldE01WFRVD2.getString();
  }

  /**
  * Set numeric field E01WFRVD2 using a BigDecimal value.
  */
  public void setE01WFRVD2(BigDecimal newvalue)
  {
    fieldE01WFRVD2.setBigDecimal(newvalue);
  }

  /**
  * Return value of numeric field E01WFRVD2 as a BigDecimal.
  */
  public BigDecimal getBigDecimalE01WFRVD2()
  {
    return fieldE01WFRVD2.getBigDecimal();
  }

  /**
  * Set field E01WFRVD3 using a String value.
  */
  public void setE01WFRVD3(String newvalue)
  {
    fieldE01WFRVD3.setString(newvalue);
  }

  /**
  * Get value of field E01WFRVD3 as a String.
  */
  public String getE01WFRVD3()
  {
    return fieldE01WFRVD3.getString();
  }

  /**
  * Set numeric field E01WFRVD3 using a BigDecimal value.
  */
  public void setE01WFRVD3(BigDecimal newvalue)
  {
    fieldE01WFRVD3.setBigDecimal(newvalue);
  }

  /**
  * Return value of numeric field E01WFRVD3 as a BigDecimal.
  */
  public BigDecimal getBigDecimalE01WFRVD3()
  {
    return fieldE01WFRVD3.getBigDecimal();
  }

  /**
  * Set field E01WFRMA1 using a String value.
  */
  public void setE01WFRMA1(String newvalue)
  {
    fieldE01WFRMA1.setString(newvalue);
  }

  /**
  * Get value of field E01WFRMA1 as a String.
  */
  public String getE01WFRMA1()
  {
    return fieldE01WFRMA1.getString();
  }

  /**
  * Set numeric field E01WFRMA1 using a BigDecimal value.
  */
  public void setE01WFRMA1(BigDecimal newvalue)
  {
    fieldE01WFRMA1.setBigDecimal(newvalue);
  }

  /**
  * Return value of numeric field E01WFRMA1 as a BigDecimal.
  */
  public BigDecimal getBigDecimalE01WFRMA1()
  {
    return fieldE01WFRMA1.getBigDecimal();
  }

  /**
  * Set field E01WFRMA2 using a String value.
  */
  public void setE01WFRMA2(String newvalue)
  {
    fieldE01WFRMA2.setString(newvalue);
  }

  /**
  * Get value of field E01WFRMA2 as a String.
  */
  public String getE01WFRMA2()
  {
    return fieldE01WFRMA2.getString();
  }

  /**
  * Set numeric field E01WFRMA2 using a BigDecimal value.
  */
  public void setE01WFRMA2(BigDecimal newvalue)
  {
    fieldE01WFRMA2.setBigDecimal(newvalue);
  }

  /**
  * Return value of numeric field E01WFRMA2 as a BigDecimal.
  */
  public BigDecimal getBigDecimalE01WFRMA2()
  {
    return fieldE01WFRMA2.getBigDecimal();
  }

  /**
  * Set field E01WFRMA3 using a String value.
  */
  public void setE01WFRMA3(String newvalue)
  {
    fieldE01WFRMA3.setString(newvalue);
  }

  /**
  * Get value of field E01WFRMA3 as a String.
  */
  public String getE01WFRMA3()
  {
    return fieldE01WFRMA3.getString();
  }

  /**
  * Set numeric field E01WFRMA3 using a BigDecimal value.
  */
  public void setE01WFRMA3(BigDecimal newvalue)
  {
    fieldE01WFRMA3.setBigDecimal(newvalue);
  }

  /**
  * Return value of numeric field E01WFRMA3 as a BigDecimal.
  */
  public BigDecimal getBigDecimalE01WFRMA3()
  {
    return fieldE01WFRMA3.getBigDecimal();
  }

  /**
  * Set field E01WFRCUN using a String value.
  */
  public void setE01WFRCUN(String newvalue)
  {
    fieldE01WFRCUN.setString(newvalue);
  }

  /**
  * Get value of field E01WFRCUN as a String.
  */
  public String getE01WFRCUN()
  {
    return fieldE01WFRCUN.getString();
  }

  /**
  * Set numeric field E01WFRCUN using a BigDecimal value.
  */
  public void setE01WFRCUN(BigDecimal newvalue)
  {
    fieldE01WFRCUN.setBigDecimal(newvalue);
  }

  /**
  * Return value of numeric field E01WFRCUN as a BigDecimal.
  */
  public BigDecimal getBigDecimalE01WFRCUN()
  {
    return fieldE01WFRCUN.getBigDecimal();
  }

  /**
  * Set field E01WFRRTE using a String value.
  */
  public void setE01WFRRTE(String newvalue)
  {
    fieldE01WFRRTE.setString(newvalue);
  }

  /**
  * Get value of field E01WFRRTE as a String.
  */
  public String getE01WFRRTE()
  {
    return fieldE01WFRRTE.getString();
  }

  /**
  * Set numeric field E01WFRRTE using a BigDecimal value.
  */
  public void setE01WFRRTE(BigDecimal newvalue)
  {
    fieldE01WFRRTE.setBigDecimal(newvalue);
  }

  /**
  * Return value of numeric field E01WFRRTE as a BigDecimal.
  */
  public BigDecimal getBigDecimalE01WFRRTE()
  {
    return fieldE01WFRRTE.getBigDecimal();
  }

  /**
  * Set field E01WFROAM using a String value.
  */
  public void setE01WFROAM(String newvalue)
  {
    fieldE01WFROAM.setString(newvalue);
  }

  /**
  * Get value of field E01WFROAM as a String.
  */
  public String getE01WFROAM()
  {
    return fieldE01WFROAM.getString();
  }

  /**
  * Set numeric field E01WFROAM using a BigDecimal value.
  */
  public void setE01WFROAM(BigDecimal newvalue)
  {
    fieldE01WFROAM.setBigDecimal(newvalue);
  }

  /**
  * Return value of numeric field E01WFROAM as a BigDecimal.
  */
  public BigDecimal getBigDecimalE01WFROAM()
  {
    return fieldE01WFROAM.getBigDecimal();
  }

  /**
  * Set field E01WFRLCU using a String value.
  */
  public void setE01WFRLCU(String newvalue)
  {
    fieldE01WFRLCU.setString(newvalue);
  }

  /**
  * Get value of field E01WFRLCU as a String.
  */
  public String getE01WFRLCU()
  {
    return fieldE01WFRLCU.getString();
  }

  /**
  * Set numeric field E01WFRLCU using a BigDecimal value.
  */
  public void setE01WFRLCU(BigDecimal newvalue)
  {
    fieldE01WFRLCU.setBigDecimal(newvalue);
  }

  /**
  * Return value of numeric field E01WFRLCU as a BigDecimal.
  */
  public BigDecimal getBigDecimalE01WFRLCU()
  {
    return fieldE01WFRLCU.getBigDecimal();
  }

  /**
  * Set field E01WFRCMM using a String value.
  */
  public void setE01WFRCMM(String newvalue)
  {
    fieldE01WFRCMM.setString(newvalue);
  }

  /**
  * Get value of field E01WFRCMM as a String.
  */
  public String getE01WFRCMM()
  {
    return fieldE01WFRCMM.getString();
  }

  /**
  * Set numeric field E01WFRCMM using a BigDecimal value.
  */
  public void setE01WFRCMM(BigDecimal newvalue)
  {
    fieldE01WFRCMM.setBigDecimal(newvalue);
  }

  /**
  * Return value of numeric field E01WFRCMM as a BigDecimal.
  */
  public BigDecimal getBigDecimalE01WFRCMM()
  {
    return fieldE01WFRCMM.getBigDecimal();
  }

  /**
  * Set field E01WFRHEF using a String value.
  */
  public void setE01WFRHEF(String newvalue)
  {
    fieldE01WFRHEF.setString(newvalue);
  }

  /**
  * Get value of field E01WFRHEF as a String.
  */
  public String getE01WFRHEF()
  {
    return fieldE01WFRHEF.getString();
  }

  /**
  * Set field E01WFRHEM using a String value.
  */
  public void setE01WFRHEM(String newvalue)
  {
    fieldE01WFRHEM.setString(newvalue);
  }

  /**
  * Get value of field E01WFRHEM as a String.
  */
  public String getE01WFRHEM()
  {
    return fieldE01WFRHEM.getString();
  }

  /**
  * Set numeric field E01WFRHEM using a BigDecimal value.
  */
  public void setE01WFRHEM(BigDecimal newvalue)
  {
    fieldE01WFRHEM.setBigDecimal(newvalue);
  }

  /**
  * Return value of numeric field E01WFRHEM as a BigDecimal.
  */
  public BigDecimal getBigDecimalE01WFRHEM()
  {
    return fieldE01WFRHEM.getBigDecimal();
  }

  /**
  * Set field E01WFROT1 using a String value.
  */
  public void setE01WFROT1(String newvalue)
  {
    fieldE01WFROT1.setString(newvalue);
  }

  /**
  * Get value of field E01WFROT1 as a String.
  */
  public String getE01WFROT1()
  {
    return fieldE01WFROT1.getString();
  }

  /**
  * Set field E01WFROT2 using a String value.
  */
  public void setE01WFROT2(String newvalue)
  {
    fieldE01WFROT2.setString(newvalue);
  }

  /**
  * Get value of field E01WFROT2 as a String.
  */
  public String getE01WFROT2()
  {
    return fieldE01WFROT2.getString();
  }

  /**
  * Set field D01WFRCP1 using a String value.
  */
  public void setD01WFRCP1(String newvalue)
  {
    fieldD01WFRCP1.setString(newvalue);
  }

  /**
  * Get value of field D01WFRCP1 as a String.
  */
  public String getD01WFRCP1()
  {
    return fieldD01WFRCP1.getString();
  }

  /**
  * Set field D01WFRCP2 using a String value.
  */
  public void setD01WFRCP2(String newvalue)
  {
    fieldD01WFRCP2.setString(newvalue);
  }

  /**
  * Get value of field D01WFRCP2 as a String.
  */
  public String getD01WFRCP2()
  {
    return fieldD01WFRCP2.getString();
  }

  /**
  * Set field D01WFRCP3 using a String value.
  */
  public void setD01WFRCP3(String newvalue)
  {
    fieldD01WFRCP3.setString(newvalue);
  }

  /**
  * Get value of field D01WFRCP3 as a String.
  */
  public String getD01WFRCP3()
  {
    return fieldD01WFRCP3.getString();
  }

  /**
  * Set field D01LIMAMT using a String value.
  */
  public void setD01LIMAMT(String newvalue)
  {
    fieldD01LIMAMT.setString(newvalue);
  }

  /**
  * Get value of field D01LIMAMT as a String.
  */
  public String getD01LIMAMT()
  {
    return fieldD01LIMAMT.getString();
  }

  /**
  * Set numeric field D01LIMAMT using a BigDecimal value.
  */
  public void setD01LIMAMT(BigDecimal newvalue)
  {
    fieldD01LIMAMT.setBigDecimal(newvalue);
  }

  /**
  * Return value of numeric field D01LIMAMT as a BigDecimal.
  */
  public BigDecimal getBigDecimalD01LIMAMT()
  {
    return fieldD01LIMAMT.getBigDecimal();
  }

  /**
  * Set field D01LIMAVL using a String value.
  */
  public void setD01LIMAVL(String newvalue)
  {
    fieldD01LIMAVL.setString(newvalue);
  }

  /**
  * Get value of field D01LIMAVL as a String.
  */
  public String getD01LIMAVL()
  {
    return fieldD01LIMAVL.getString();
  }

  /**
  * Set numeric field D01LIMAVL using a BigDecimal value.
  */
  public void setD01LIMAVL(BigDecimal newvalue)
  {
    fieldD01LIMAVL.setBigDecimal(newvalue);
  }

  /**
  * Return value of numeric field D01LIMAVL as a BigDecimal.
  */
  public BigDecimal getBigDecimalD01LIMAVL()
  {
    return fieldD01LIMAVL.getBigDecimal();
  }

  /**
  * Set field D01LIMEND using a String value.
  */
  public void setD01LIMEND(String newvalue)
  {
    fieldD01LIMEND.setString(newvalue);
  }

  /**
  * Get value of field D01LIMEND as a String.
  */
  public String getD01LIMEND()
  {
    return fieldD01LIMEND.getString();
  }

  /**
  * Set numeric field D01LIMEND using a BigDecimal value.
  */
  public void setD01LIMEND(BigDecimal newvalue)
  {
    fieldD01LIMEND.setBigDecimal(newvalue);
  }

  /**
  * Return value of numeric field D01LIMEND as a BigDecimal.
  */
  public BigDecimal getBigDecimalD01LIMEND()
  {
    return fieldD01LIMEND.getBigDecimal();
  }

  /**
  * Set field D01FEOLIM using a String value.
  */
  public void setD01FEOLIM(String newvalue)
  {
    fieldD01FEOLIM.setString(newvalue);
  }

  /**
  * Get value of field D01FEOLIM as a String.
  */
  public String getD01FEOLIM()
  {
    return fieldD01FEOLIM.getString();
  }

  /**
  * Set numeric field D01FEOLIM using a BigDecimal value.
  */
  public void setD01FEOLIM(BigDecimal newvalue)
  {
    fieldD01FEOLIM.setBigDecimal(newvalue);
  }

  /**
  * Return value of numeric field D01FEOLIM as a BigDecimal.
  */
  public BigDecimal getBigDecimalD01FEOLIM()
  {
    return fieldD01FEOLIM.getBigDecimal();
  }

  /**
  * Set field D01FEOAVL using a String value.
  */
  public void setD01FEOAVL(String newvalue)
  {
    fieldD01FEOAVL.setString(newvalue);
  }

  /**
  * Get value of field D01FEOAVL as a String.
  */
  public String getD01FEOAVL()
  {
    return fieldD01FEOAVL.getString();
  }

  /**
  * Set numeric field D01FEOAVL using a BigDecimal value.
  */
  public void setD01FEOAVL(BigDecimal newvalue)
  {
    fieldD01FEOAVL.setBigDecimal(newvalue);
  }

  /**
  * Return value of numeric field D01FEOAVL as a BigDecimal.
  */
  public BigDecimal getBigDecimalD01FEOAVL()
  {
    return fieldD01FEOAVL.getBigDecimal();
  }

  /**
  * Set field D01FEOEND using a String value.
  */
  public void setD01FEOEND(String newvalue)
  {
    fieldD01FEOEND.setString(newvalue);
  }

  /**
  * Get value of field D01FEOEND as a String.
  */
  public String getD01FEOEND()
  {
    return fieldD01FEOEND.getString();
  }

  /**
  * Set numeric field D01FEOEND using a BigDecimal value.
  */
  public void setD01FEOEND(BigDecimal newvalue)
  {
    fieldD01FEOEND.setBigDecimal(newvalue);
  }

  /**
  * Return value of numeric field D01FEOEND as a BigDecimal.
  */
  public BigDecimal getBigDecimalD01FEOEND()
  {
    return fieldD01FEOEND.getBigDecimal();
  }

  /**
  * Set field D01USRDSC using a String value.
  */
  public void setD01USRDSC(String newvalue)
  {
    fieldD01USRDSC.setString(newvalue);
  }

  /**
  * Get value of field D01USRDSC as a String.
  */
  public String getD01USRDSC()
  {
    return fieldD01USRDSC.getString();
  }

}
