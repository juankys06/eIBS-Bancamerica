package datapro.eibs.beans;

import datapro.eibs.sockets.*;
import java.io.*;
import java.math.*;

import java.util.*;

/**
* Class generated by AS/400 CRTCLASS command from ECH030602 physical file definition.
* 
* File level identifier is 1040329165912.
* Record format level identifier is 408219D8A8540.
*/

public class ECH030602Message extends MessageRecord
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
                                     "E02CODBNK",
                                     "E02SUCDST",
                                     "E02SUCORG",
                                     "E02NUMCTA",
                                     "E02CODCCY",
                                     "E02TIPPER",
                                     "E02TIPCHK",
                                     "E02CANCHK",
                                     "E02SERIEK",
                                     "E02USERID",
                                     "E02FECDT1",
                                     "E02FECDT2",
                                     "E02FECDT3",
                                     "E02TIMEIN",
                                     "E02REMARK",
                                     "E02NUMSOL"
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
                                   "E02CODBNK",
                                   "E02SUCDST",
                                   "E02SUCORG",
                                   "E02NUMCTA",
                                   "E02CODCCY",
                                   "E02TIPPER",
                                   "E02TIPCHK",
                                   "E02CANCHK",
                                   "E02SERIEK",
                                   "E02USERID",
                                   "E02FECDT1",
                                   "E02FECDT2",
                                   "E02FECDT3",
                                   "E02TIMEIN",
                                   "E02REMARK",
                                   "E02NUMSOL"
                                 };
  final static String fid = "1040329165912";
  final static String rid = "408219D8A8540";
  final static String fmtname = "ECH030602";
  final int FIELDCOUNT = 25;
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
  private CharacterField fieldE02CODBNK = null;
  private DecimalField fieldE02SUCDST = null;
  private DecimalField fieldE02SUCORG = null;
  private CharacterField fieldE02NUMCTA = null;
  private CharacterField fieldE02CODCCY = null;
  private CharacterField fieldE02TIPPER = null;
  private DecimalField fieldE02TIPCHK = null;
  private DecimalField fieldE02CANCHK = null;
  private CharacterField fieldE02SERIEK = null;
  private CharacterField fieldE02USERID = null;
  private DecimalField fieldE02FECDT1 = null;
  private DecimalField fieldE02FECDT2 = null;
  private DecimalField fieldE02FECDT3 = null;
  private DecimalField fieldE02TIMEIN = null;
  private CharacterField fieldE02REMARK = null;
  private DecimalField fieldE02NUMSOL = null;

  /**
  * Constructor for ECH030602Message.
  */
  public ECH030602Message()
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
    recordsize = 147;
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
    fields[9] = fieldE02CODBNK =
    new CharacterField(message, HEADERSIZE + 42, 2, "E02CODBNK");
    fields[10] = fieldE02SUCDST =
    new DecimalField(message, HEADERSIZE + 44, 4, 0, "E02SUCDST");
    fields[11] = fieldE02SUCORG =
    new DecimalField(message, HEADERSIZE + 48, 4, 0, "E02SUCORG");
    fields[12] = fieldE02NUMCTA =
    new CharacterField(message, HEADERSIZE + 52, 1, "E02NUMCTA");
    fields[13] = fieldE02CODCCY =
    new CharacterField(message, HEADERSIZE + 53, 3, "E02CODCCY");
    fields[14] = fieldE02TIPPER =
    new CharacterField(message, HEADERSIZE + 56, 1, "E02TIPPER");
    fields[15] = fieldE02TIPCHK =
    new DecimalField(message, HEADERSIZE + 57, 3, 0, "E02TIPCHK");
    fields[16] = fieldE02CANCHK =
    new DecimalField(message, HEADERSIZE + 60, 8, 0, "E02CANCHK");
    fields[17] = fieldE02SERIEK =
    new CharacterField(message, HEADERSIZE + 68, 10, "E02SERIEK");
    fields[18] = fieldE02USERID =
    new CharacterField(message, HEADERSIZE + 78, 10, "E02USERID");
    fields[19] = fieldE02FECDT1 =
    new DecimalField(message, HEADERSIZE + 88, 3, 0, "E02FECDT1");
    fields[20] = fieldE02FECDT2 =
    new DecimalField(message, HEADERSIZE + 91, 3, 0, "E02FECDT2");
    fields[21] = fieldE02FECDT3 =
    new DecimalField(message, HEADERSIZE + 94, 3, 0, "E02FECDT3");
    fields[22] = fieldE02TIMEIN =
    new DecimalField(message, HEADERSIZE + 97, 7, 0, "E02TIMEIN");
    fields[23] = fieldE02REMARK =
    new CharacterField(message, HEADERSIZE + 104, 30, "E02REMARK");
    fields[24] = fieldE02NUMSOL =
    new DecimalField(message, HEADERSIZE + 134, 13, 0, "E02NUMSOL");

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
  * Set field E02CODBNK using a String value.
  */
  public void setE02CODBNK(String newvalue)
  {
    fieldE02CODBNK.setString(newvalue);
  }

  /**
  * Get value of field E02CODBNK as a String.
  */
  public String getE02CODBNK()
  {
    return fieldE02CODBNK.getString();
  }

  /**
  * Set field E02SUCDST using a String value.
  */
  public void setE02SUCDST(String newvalue)
  {
    fieldE02SUCDST.setString(newvalue);
  }

  /**
  * Get value of field E02SUCDST as a String.
  */
  public String getE02SUCDST()
  {
    return fieldE02SUCDST.getString();
  }

  /**
  * Set numeric field E02SUCDST using a BigDecimal value.
  */
  public void setE02SUCDST(BigDecimal newvalue)
  {
    fieldE02SUCDST.setBigDecimal(newvalue);
  }

  /**
  * Return value of numeric field E02SUCDST as a BigDecimal.
  */
  public BigDecimal getBigDecimalE02SUCDST()
  {
    return fieldE02SUCDST.getBigDecimal();
  }

  /**
  * Set field E02SUCORG using a String value.
  */
  public void setE02SUCORG(String newvalue)
  {
    fieldE02SUCORG.setString(newvalue);
  }

  /**
  * Get value of field E02SUCORG as a String.
  */
  public String getE02SUCORG()
  {
    return fieldE02SUCORG.getString();
  }

  /**
  * Set numeric field E02SUCORG using a BigDecimal value.
  */
  public void setE02SUCORG(BigDecimal newvalue)
  {
    fieldE02SUCORG.setBigDecimal(newvalue);
  }

  /**
  * Return value of numeric field E02SUCORG as a BigDecimal.
  */
  public BigDecimal getBigDecimalE02SUCORG()
  {
    return fieldE02SUCORG.getBigDecimal();
  }

  /**
  * Set field E02NUMCTA using a String value.
  */
  public void setE02NUMCTA(String newvalue)
  {
    fieldE02NUMCTA.setString(newvalue);
  }

  /**
  * Get value of field E02NUMCTA as a String.
  */
  public String getE02NUMCTA()
  {
    return fieldE02NUMCTA.getString();
  }

  /**
  * Set field E02CODCCY using a String value.
  */
  public void setE02CODCCY(String newvalue)
  {
    fieldE02CODCCY.setString(newvalue);
  }

  /**
  * Get value of field E02CODCCY as a String.
  */
  public String getE02CODCCY()
  {
    return fieldE02CODCCY.getString();
  }

  /**
  * Set field E02TIPPER using a String value.
  */
  public void setE02TIPPER(String newvalue)
  {
    fieldE02TIPPER.setString(newvalue);
  }

  /**
  * Get value of field E02TIPPER as a String.
  */
  public String getE02TIPPER()
  {
    return fieldE02TIPPER.getString();
  }

  /**
  * Set field E02TIPCHK using a String value.
  */
  public void setE02TIPCHK(String newvalue)
  {
    fieldE02TIPCHK.setString(newvalue);
  }

  /**
  * Get value of field E02TIPCHK as a String.
  */
  public String getE02TIPCHK()
  {
    return fieldE02TIPCHK.getString();
  }

  /**
  * Set numeric field E02TIPCHK using a BigDecimal value.
  */
  public void setE02TIPCHK(BigDecimal newvalue)
  {
    fieldE02TIPCHK.setBigDecimal(newvalue);
  }

  /**
  * Return value of numeric field E02TIPCHK as a BigDecimal.
  */
  public BigDecimal getBigDecimalE02TIPCHK()
  {
    return fieldE02TIPCHK.getBigDecimal();
  }

  /**
  * Set field E02CANCHK using a String value.
  */
  public void setE02CANCHK(String newvalue)
  {
    fieldE02CANCHK.setString(newvalue);
  }

  /**
  * Get value of field E02CANCHK as a String.
  */
  public String getE02CANCHK()
  {
    return fieldE02CANCHK.getString();
  }

  /**
  * Set numeric field E02CANCHK using a BigDecimal value.
  */
  public void setE02CANCHK(BigDecimal newvalue)
  {
    fieldE02CANCHK.setBigDecimal(newvalue);
  }

  /**
  * Return value of numeric field E02CANCHK as a BigDecimal.
  */
  public BigDecimal getBigDecimalE02CANCHK()
  {
    return fieldE02CANCHK.getBigDecimal();
  }

  /**
  * Set field E02SERIEK using a String value.
  */
  public void setE02SERIEK(String newvalue)
  {
    fieldE02SERIEK.setString(newvalue);
  }

  /**
  * Get value of field E02SERIEK as a String.
  */
  public String getE02SERIEK()
  {
    return fieldE02SERIEK.getString();
  }

  /**
  * Set field E02USERID using a String value.
  */
  public void setE02USERID(String newvalue)
  {
    fieldE02USERID.setString(newvalue);
  }

  /**
  * Get value of field E02USERID as a String.
  */
  public String getE02USERID()
  {
    return fieldE02USERID.getString();
  }

  /**
  * Set field E02FECDT1 using a String value.
  */
  public void setE02FECDT1(String newvalue)
  {
    fieldE02FECDT1.setString(newvalue);
  }

  /**
  * Get value of field E02FECDT1 as a String.
  */
  public String getE02FECDT1()
  {
    return fieldE02FECDT1.getString();
  }

  /**
  * Set numeric field E02FECDT1 using a BigDecimal value.
  */
  public void setE02FECDT1(BigDecimal newvalue)
  {
    fieldE02FECDT1.setBigDecimal(newvalue);
  }

  /**
  * Return value of numeric field E02FECDT1 as a BigDecimal.
  */
  public BigDecimal getBigDecimalE02FECDT1()
  {
    return fieldE02FECDT1.getBigDecimal();
  }

  /**
  * Set field E02FECDT2 using a String value.
  */
  public void setE02FECDT2(String newvalue)
  {
    fieldE02FECDT2.setString(newvalue);
  }

  /**
  * Get value of field E02FECDT2 as a String.
  */
  public String getE02FECDT2()
  {
    return fieldE02FECDT2.getString();
  }

  /**
  * Set numeric field E02FECDT2 using a BigDecimal value.
  */
  public void setE02FECDT2(BigDecimal newvalue)
  {
    fieldE02FECDT2.setBigDecimal(newvalue);
  }

  /**
  * Return value of numeric field E02FECDT2 as a BigDecimal.
  */
  public BigDecimal getBigDecimalE02FECDT2()
  {
    return fieldE02FECDT2.getBigDecimal();
  }

  /**
  * Set field E02FECDT3 using a String value.
  */
  public void setE02FECDT3(String newvalue)
  {
    fieldE02FECDT3.setString(newvalue);
  }

  /**
  * Get value of field E02FECDT3 as a String.
  */
  public String getE02FECDT3()
  {
    return fieldE02FECDT3.getString();
  }

  /**
  * Set numeric field E02FECDT3 using a BigDecimal value.
  */
  public void setE02FECDT3(BigDecimal newvalue)
  {
    fieldE02FECDT3.setBigDecimal(newvalue);
  }

  /**
  * Return value of numeric field E02FECDT3 as a BigDecimal.
  */
  public BigDecimal getBigDecimalE02FECDT3()
  {
    return fieldE02FECDT3.getBigDecimal();
  }

  /**
  * Set field E02TIMEIN using a String value.
  */
  public void setE02TIMEIN(String newvalue)
  {
    fieldE02TIMEIN.setString(newvalue);
  }

  /**
  * Get value of field E02TIMEIN as a String.
  */
  public String getE02TIMEIN()
  {
    return fieldE02TIMEIN.getString();
  }

  /**
  * Set numeric field E02TIMEIN using a BigDecimal value.
  */
  public void setE02TIMEIN(BigDecimal newvalue)
  {
    fieldE02TIMEIN.setBigDecimal(newvalue);
  }

  /**
  * Return value of numeric field E02TIMEIN as a BigDecimal.
  */
  public BigDecimal getBigDecimalE02TIMEIN()
  {
    return fieldE02TIMEIN.getBigDecimal();
  }

  /**
  * Set field E02REMARK using a String value.
  */
  public void setE02REMARK(String newvalue)
  {
    fieldE02REMARK.setString(newvalue);
  }

  /**
  * Get value of field E02REMARK as a String.
  */
  public String getE02REMARK()
  {
    return fieldE02REMARK.getString();
  }

  /**
  * Set field E02NUMSOL using a String value.
  */
  public void setE02NUMSOL(String newvalue)
  {
    fieldE02NUMSOL.setString(newvalue);
  }

  /**
  * Get value of field E02NUMSOL as a String.
  */
  public String getE02NUMSOL()
  {
    return fieldE02NUMSOL.getString();
  }

  /**
  * Set numeric field E02NUMSOL using a BigDecimal value.
  */
  public void setE02NUMSOL(BigDecimal newvalue)
  {
    fieldE02NUMSOL.setBigDecimal(newvalue);
  }

  /**
  * Return value of numeric field E02NUMSOL as a BigDecimal.
  */
  public BigDecimal getBigDecimalE02NUMSOL()
  {
    return fieldE02NUMSOL.getBigDecimal();
  }

}
