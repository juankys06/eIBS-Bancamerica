package datapro.eibs.beans;

import datapro.eibs.sockets.*;
import java.io.*;
import java.math.*;

import java.util.*;

/**
* Class generated by AS/400 CRTCLASS command from EDP088202 physical file definition.
* 
* File level identifier is 1090108124219.
* Record format level identifier is 331FB148C3FA1.
*/

public class EDP088202Message extends MessageRecord
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
                                     "E02CNTLAN",
                                     "E02PLPNPR",
                                     "E02DUECUN",
                                     "E02CUSNA1",
                                     "E02CUSLGT",
                                     "E02BNKFLG",
                                     "E02DUEPRD",
                                     "E02DUEPDS",
                                     "E02DUEAMT",
                                     "E02DUERTE",
                                     "E02DUETRM",
                                     "E02DUERTM",
                                     "E02DUEPYM",
                                     "E02RECPOS",
                                     "E02OPECDE"
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
                                   "E02CNTLAN",
                                   "E02PLPNPR",
                                   "E02DUECUN",
                                   "E02CUSNA1",
                                   "E02CUSLGT",
                                   "E02BNKFLG",
                                   "E02DUEPRD",
                                   "E02DUEPDS",
                                   "E02DUEAMT",
                                   "E02DUERTE",
                                   "E02DUETRM",
                                   "E02DUERTM",
                                   "E02DUEPYM",
                                   "E02RECPOS",
                                   "E02OPECDE"
                                 };
  final static String fid = "1090108124219";
  final static String rid = "331FB148C3FA1";
  final static String fmtname = "EDP088202";
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
  private CharacterField fieldE02CNTLAN = null;
  private DecimalField fieldE02PLPNPR = null;
  private DecimalField fieldE02DUECUN = null;
  private CharacterField fieldE02CUSNA1 = null;
  private CharacterField fieldE02CUSLGT = null;
  private CharacterField fieldE02BNKFLG = null;
  private CharacterField fieldE02DUEPRD = null;
  private CharacterField fieldE02DUEPDS = null;
  private DecimalField fieldE02DUEAMT = null;
  private DecimalField fieldE02DUERTE = null;
  private DecimalField fieldE02DUETRM = null;
  private DecimalField fieldE02DUERTM = null;
  private DecimalField fieldE02DUEPYM = null;
  private DecimalField fieldE02RECPOS = null;
  private CharacterField fieldE02OPECDE = null;

  /**
  * Constructor for EDP088202Message.
  */
  public EDP088202Message()
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
    recordsize = 224;
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
    fields[9] = fieldE02CNTLAN =
    new CharacterField(message, HEADERSIZE + 42, 1, "E02CNTLAN");
    fields[10] = fieldE02PLPNPR =
    new DecimalField(message, HEADERSIZE + 43, 13, 0, "E02PLPNPR");
    fields[11] = fieldE02DUECUN =
    new DecimalField(message, HEADERSIZE + 56, 10, 0, "E02DUECUN");
    fields[12] = fieldE02CUSNA1 =
    new CharacterField(message, HEADERSIZE + 66, 45, "E02CUSNA1");
    fields[13] = fieldE02CUSLGT =
    new CharacterField(message, HEADERSIZE + 111, 1, "E02CUSLGT");
    fields[14] = fieldE02BNKFLG =
    new CharacterField(message, HEADERSIZE + 112, 1, "E02BNKFLG");
    fields[15] = fieldE02DUEPRD =
    new CharacterField(message, HEADERSIZE + 113, 4, "E02DUEPRD");
    fields[16] = fieldE02DUEPDS =
    new CharacterField(message, HEADERSIZE + 117, 35, "E02DUEPDS");
    fields[17] = fieldE02DUEAMT =
    new DecimalField(message, HEADERSIZE + 152, 17, 2, "E02DUEAMT");
    fields[18] = fieldE02DUERTE =
    new DecimalField(message, HEADERSIZE + 169, 13, 6, "E02DUERTE");
    fields[19] = fieldE02DUETRM =
    new DecimalField(message, HEADERSIZE + 182, 5, 0, "E02DUETRM");
    fields[20] = fieldE02DUERTM =
    new DecimalField(message, HEADERSIZE + 187, 11, 6, "E02DUERTM");
    fields[21] = fieldE02DUEPYM =
    new DecimalField(message, HEADERSIZE + 198, 17, 2, "E02DUEPYM");
    fields[22] = fieldE02RECPOS =
    new DecimalField(message, HEADERSIZE + 215, 8, 0, "E02RECPOS");
    fields[23] = fieldE02OPECDE =
    new CharacterField(message, HEADERSIZE + 223, 1, "E02OPECDE");

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
  * Set field E02CNTLAN using a String value.
  */
  public void setE02CNTLAN(String newvalue)
  {
    fieldE02CNTLAN.setString(newvalue);
  }

  /**
  * Get value of field E02CNTLAN as a String.
  */
  public String getE02CNTLAN()
  {
    return fieldE02CNTLAN.getString();
  }

  /**
  * Set field E02PLPNPR using a String value.
  */
  public void setE02PLPNPR(String newvalue)
  {
    fieldE02PLPNPR.setString(newvalue);
  }

  /**
  * Get value of field E02PLPNPR as a String.
  */
  public String getE02PLPNPR()
  {
    return fieldE02PLPNPR.getString();
  }

  /**
  * Set numeric field E02PLPNPR using a BigDecimal value.
  */
  public void setE02PLPNPR(BigDecimal newvalue)
  {
    fieldE02PLPNPR.setBigDecimal(newvalue);
  }

  /**
  * Return value of numeric field E02PLPNPR as a BigDecimal.
  */
  public BigDecimal getBigDecimalE02PLPNPR()
  {
    return fieldE02PLPNPR.getBigDecimal();
  }

  /**
  * Set field E02DUECUN using a String value.
  */
  public void setE02DUECUN(String newvalue)
  {
    fieldE02DUECUN.setString(newvalue);
  }

  /**
  * Get value of field E02DUECUN as a String.
  */
  public String getE02DUECUN()
  {
    return fieldE02DUECUN.getString();
  }

  /**
  * Set numeric field E02DUECUN using a BigDecimal value.
  */
  public void setE02DUECUN(BigDecimal newvalue)
  {
    fieldE02DUECUN.setBigDecimal(newvalue);
  }

  /**
  * Return value of numeric field E02DUECUN as a BigDecimal.
  */
  public BigDecimal getBigDecimalE02DUECUN()
  {
    return fieldE02DUECUN.getBigDecimal();
  }

  /**
  * Set field E02CUSNA1 using a String value.
  */
  public void setE02CUSNA1(String newvalue)
  {
    fieldE02CUSNA1.setString(newvalue);
  }

  /**
  * Get value of field E02CUSNA1 as a String.
  */
  public String getE02CUSNA1()
  {
    return fieldE02CUSNA1.getString();
  }

  /**
  * Set field E02CUSLGT using a String value.
  */
  public void setE02CUSLGT(String newvalue)
  {
    fieldE02CUSLGT.setString(newvalue);
  }

  /**
  * Get value of field E02CUSLGT as a String.
  */
  public String getE02CUSLGT()
  {
    return fieldE02CUSLGT.getString();
  }

  /**
  * Set field E02BNKFLG using a String value.
  */
  public void setE02BNKFLG(String newvalue)
  {
    fieldE02BNKFLG.setString(newvalue);
  }

  /**
  * Get value of field E02BNKFLG as a String.
  */
  public String getE02BNKFLG()
  {
    return fieldE02BNKFLG.getString();
  }

  /**
  * Set field E02DUEPRD using a String value.
  */
  public void setE02DUEPRD(String newvalue)
  {
    fieldE02DUEPRD.setString(newvalue);
  }

  /**
  * Get value of field E02DUEPRD as a String.
  */
  public String getE02DUEPRD()
  {
    return fieldE02DUEPRD.getString();
  }

  /**
  * Set field E02DUEPDS using a String value.
  */
  public void setE02DUEPDS(String newvalue)
  {
    fieldE02DUEPDS.setString(newvalue);
  }

  /**
  * Get value of field E02DUEPDS as a String.
  */
  public String getE02DUEPDS()
  {
    return fieldE02DUEPDS.getString();
  }

  /**
  * Set field E02DUEAMT using a String value.
  */
  public void setE02DUEAMT(String newvalue)
  {
    fieldE02DUEAMT.setString(newvalue);
  }

  /**
  * Get value of field E02DUEAMT as a String.
  */
  public String getE02DUEAMT()
  {
    return fieldE02DUEAMT.getString();
  }

  /**
  * Set numeric field E02DUEAMT using a BigDecimal value.
  */
  public void setE02DUEAMT(BigDecimal newvalue)
  {
    fieldE02DUEAMT.setBigDecimal(newvalue);
  }

  /**
  * Return value of numeric field E02DUEAMT as a BigDecimal.
  */
  public BigDecimal getBigDecimalE02DUEAMT()
  {
    return fieldE02DUEAMT.getBigDecimal();
  }

  /**
  * Set field E02DUERTE using a String value.
  */
  public void setE02DUERTE(String newvalue)
  {
    fieldE02DUERTE.setString(newvalue);
  }

  /**
  * Get value of field E02DUERTE as a String.
  */
  public String getE02DUERTE()
  {
    return fieldE02DUERTE.getString();
  }

  /**
  * Set numeric field E02DUERTE using a BigDecimal value.
  */
  public void setE02DUERTE(BigDecimal newvalue)
  {
    fieldE02DUERTE.setBigDecimal(newvalue);
  }

  /**
  * Return value of numeric field E02DUERTE as a BigDecimal.
  */
  public BigDecimal getBigDecimalE02DUERTE()
  {
    return fieldE02DUERTE.getBigDecimal();
  }

  /**
  * Set field E02DUETRM using a String value.
  */
  public void setE02DUETRM(String newvalue)
  {
    fieldE02DUETRM.setString(newvalue);
  }

  /**
  * Get value of field E02DUETRM as a String.
  */
  public String getE02DUETRM()
  {
    return fieldE02DUETRM.getString();
  }

  /**
  * Set numeric field E02DUETRM using a BigDecimal value.
  */
  public void setE02DUETRM(BigDecimal newvalue)
  {
    fieldE02DUETRM.setBigDecimal(newvalue);
  }

  /**
  * Return value of numeric field E02DUETRM as a BigDecimal.
  */
  public BigDecimal getBigDecimalE02DUETRM()
  {
    return fieldE02DUETRM.getBigDecimal();
  }

  /**
  * Set field E02DUERTM using a String value.
  */
  public void setE02DUERTM(String newvalue)
  {
    fieldE02DUERTM.setString(newvalue);
  }

  /**
  * Get value of field E02DUERTM as a String.
  */
  public String getE02DUERTM()
  {
    return fieldE02DUERTM.getString();
  }

  /**
  * Set numeric field E02DUERTM using a BigDecimal value.
  */
  public void setE02DUERTM(BigDecimal newvalue)
  {
    fieldE02DUERTM.setBigDecimal(newvalue);
  }

  /**
  * Return value of numeric field E02DUERTM as a BigDecimal.
  */
  public BigDecimal getBigDecimalE02DUERTM()
  {
    return fieldE02DUERTM.getBigDecimal();
  }

  /**
  * Set field E02DUEPYM using a String value.
  */
  public void setE02DUEPYM(String newvalue)
  {
    fieldE02DUEPYM.setString(newvalue);
  }

  /**
  * Get value of field E02DUEPYM as a String.
  */
  public String getE02DUEPYM()
  {
    return fieldE02DUEPYM.getString();
  }

  /**
  * Set numeric field E02DUEPYM using a BigDecimal value.
  */
  public void setE02DUEPYM(BigDecimal newvalue)
  {
    fieldE02DUEPYM.setBigDecimal(newvalue);
  }

  /**
  * Return value of numeric field E02DUEPYM as a BigDecimal.
  */
  public BigDecimal getBigDecimalE02DUEPYM()
  {
    return fieldE02DUEPYM.getBigDecimal();
  }

  /**
  * Set field E02RECPOS using a String value.
  */
  public void setE02RECPOS(String newvalue)
  {
    fieldE02RECPOS.setString(newvalue);
  }

  /**
  * Get value of field E02RECPOS as a String.
  */
  public String getE02RECPOS()
  {
    return fieldE02RECPOS.getString();
  }

  /**
  * Set numeric field E02RECPOS using a BigDecimal value.
  */
  public void setE02RECPOS(BigDecimal newvalue)
  {
    fieldE02RECPOS.setBigDecimal(newvalue);
  }

  /**
  * Return value of numeric field E02RECPOS as a BigDecimal.
  */
  public BigDecimal getBigDecimalE02RECPOS()
  {
    return fieldE02RECPOS.getBigDecimal();
  }

  /**
  * Set field E02OPECDE using a String value.
  */
  public void setE02OPECDE(String newvalue)
  {
    fieldE02OPECDE.setString(newvalue);
  }

  /**
  * Get value of field E02OPECDE as a String.
  */
  public String getE02OPECDE()
  {
    return fieldE02OPECDE.getString();
  }

}
