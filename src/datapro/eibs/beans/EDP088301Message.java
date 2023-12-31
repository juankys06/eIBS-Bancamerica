package datapro.eibs.beans;

import datapro.eibs.sockets.*;
import java.io.*;
import java.math.*;

import java.util.*;

/**
* Class generated by AS/400 CRTCLASS command from EDP088301 physical file definition.
* 
* File level identifier is 1090123181136.
* Record format level identifier is 3C0FB3D3C5902.
*/

public class EDP088301Message extends MessageRecord
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
                                     "E01CNTLAN",
                                     "E01CPTCUN",
                                     "E01CUSNA1",
                                     "E01MODLGT",
                                     "E01MODFL1",
                                     "E01MODCOD",
                                     "E01MODDSC",
                                     "E01MODPER",
                                     "E01MODPTS",
                                     "E01MODCAT",
                                     "E01PTSPTS",
                                     "E01PTSSEQ",
                                     "E01PTSFLG",
                                     "E01PTSCAL",
                                     "E01RECPOS",
                                     "E01OPECDE"
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
                                   "E01CNTLAN",
                                   "E01CPTCUN",
                                   "E01CUSNA1",
                                   "E01MODLGT",
                                   "E01MODFL1",
                                   "E01MODCOD",
                                   "E01MODDSC",
                                   "E01MODPER",
                                   "E01MODPTS",
                                   "E01MODCAT",
                                   "E01PTSPTS",
                                   "E01PTSSEQ",
                                   "E01PTSFLG",
                                   "E01PTSCAL",
                                   "E01RECPOS",
                                   "E01OPECDE"
                                 };
  final static String fid = "1090123181136";
  final static String rid = "3C0FB3D3C5902";
  final static String fmtname = "EDP088301";
  final int FIELDCOUNT = 25;
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
  private CharacterField fieldE01CNTLAN = null;
  private DecimalField fieldE01CPTCUN = null;
  private CharacterField fieldE01CUSNA1 = null;
  private CharacterField fieldE01MODLGT = null;
  private CharacterField fieldE01MODFL1 = null;
  private CharacterField fieldE01MODCOD = null;
  private CharacterField fieldE01MODDSC = null;
  private DecimalField fieldE01MODPER = null;
  private DecimalField fieldE01MODPTS = null;
  private DecimalField fieldE01MODCAT = null;
  private DecimalField fieldE01PTSPTS = null;
  private DecimalField fieldE01PTSSEQ = null;
  private CharacterField fieldE01PTSFLG = null;
  private DecimalField fieldE01PTSCAL = null;
  private DecimalField fieldE01RECPOS = null;
  private CharacterField fieldE01OPECDE = null;

  /**
  * Constructor for EDP088301Message.
  */
  public EDP088301Message()
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
    recordsize = 199;
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
    fields[9] = fieldE01CNTLAN =
    new CharacterField(message, HEADERSIZE + 42, 1, "E01CNTLAN");
    fields[10] = fieldE01CPTCUN =
    new DecimalField(message, HEADERSIZE + 43, 10, 0, "E01CPTCUN");
    fields[11] = fieldE01CUSNA1 =
    new CharacterField(message, HEADERSIZE + 53, 45, "E01CUSNA1");
    fields[12] = fieldE01MODLGT =
    new CharacterField(message, HEADERSIZE + 98, 1, "E01MODLGT");
    fields[13] = fieldE01MODFL1 =
    new CharacterField(message, HEADERSIZE + 99, 1, "E01MODFL1");
    fields[14] = fieldE01MODCOD =
    new CharacterField(message, HEADERSIZE + 100, 4, "E01MODCOD");
    fields[15] = fieldE01MODDSC =
    new CharacterField(message, HEADERSIZE + 104, 35, "E01MODDSC");
    fields[16] = fieldE01MODPER =
    new DecimalField(message, HEADERSIZE + 139, 9, 2, "E01MODPER");
    fields[17] = fieldE01MODPTS =
    new DecimalField(message, HEADERSIZE + 148, 9, 2, "E01MODPTS");
    fields[18] = fieldE01MODCAT =
    new DecimalField(message, HEADERSIZE + 157, 9, 2, "E01MODCAT");
    fields[19] = fieldE01PTSPTS =
    new DecimalField(message, HEADERSIZE + 166, 8, 3, "E01PTSPTS");
    fields[20] = fieldE01PTSSEQ =
    new DecimalField(message, HEADERSIZE + 174, 6, 0, "E01PTSSEQ");
    fields[21] = fieldE01PTSFLG =
    new CharacterField(message, HEADERSIZE + 180, 1, "E01PTSFLG");
    fields[22] = fieldE01PTSCAL =
    new DecimalField(message, HEADERSIZE + 181, 9, 2, "E01PTSCAL");
    fields[23] = fieldE01RECPOS =
    new DecimalField(message, HEADERSIZE + 190, 8, 0, "E01RECPOS");
    fields[24] = fieldE01OPECDE =
    new CharacterField(message, HEADERSIZE + 198, 1, "E01OPECDE");

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
  * Set field E01CNTLAN using a String value.
  */
  public void setE01CNTLAN(String newvalue)
  {
    fieldE01CNTLAN.setString(newvalue);
  }

  /**
  * Get value of field E01CNTLAN as a String.
  */
  public String getE01CNTLAN()
  {
    return fieldE01CNTLAN.getString();
  }

  /**
  * Set field E01CPTCUN using a String value.
  */
  public void setE01CPTCUN(String newvalue)
  {
    fieldE01CPTCUN.setString(newvalue);
  }

  /**
  * Get value of field E01CPTCUN as a String.
  */
  public String getE01CPTCUN()
  {
    return fieldE01CPTCUN.getString();
  }

  /**
  * Set numeric field E01CPTCUN using a BigDecimal value.
  */
  public void setE01CPTCUN(BigDecimal newvalue)
  {
    fieldE01CPTCUN.setBigDecimal(newvalue);
  }

  /**
  * Return value of numeric field E01CPTCUN as a BigDecimal.
  */
  public BigDecimal getBigDecimalE01CPTCUN()
  {
    return fieldE01CPTCUN.getBigDecimal();
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
  * Set field E01MODLGT using a String value.
  */
  public void setE01MODLGT(String newvalue)
  {
    fieldE01MODLGT.setString(newvalue);
  }

  /**
  * Get value of field E01MODLGT as a String.
  */
  public String getE01MODLGT()
  {
    return fieldE01MODLGT.getString();
  }

  /**
  * Set field E01MODFL1 using a String value.
  */
  public void setE01MODFL1(String newvalue)
  {
    fieldE01MODFL1.setString(newvalue);
  }

  /**
  * Get value of field E01MODFL1 as a String.
  */
  public String getE01MODFL1()
  {
    return fieldE01MODFL1.getString();
  }

  /**
  * Set field E01MODCOD using a String value.
  */
  public void setE01MODCOD(String newvalue)
  {
    fieldE01MODCOD.setString(newvalue);
  }

  /**
  * Get value of field E01MODCOD as a String.
  */
  public String getE01MODCOD()
  {
    return fieldE01MODCOD.getString();
  }

  /**
  * Set field E01MODDSC using a String value.
  */
  public void setE01MODDSC(String newvalue)
  {
    fieldE01MODDSC.setString(newvalue);
  }

  /**
  * Get value of field E01MODDSC as a String.
  */
  public String getE01MODDSC()
  {
    return fieldE01MODDSC.getString();
  }

  /**
  * Set field E01MODPER using a String value.
  */
  public void setE01MODPER(String newvalue)
  {
    fieldE01MODPER.setString(newvalue);
  }

  /**
  * Get value of field E01MODPER as a String.
  */
  public String getE01MODPER()
  {
    return fieldE01MODPER.getString();
  }

  /**
  * Set numeric field E01MODPER using a BigDecimal value.
  */
  public void setE01MODPER(BigDecimal newvalue)
  {
    fieldE01MODPER.setBigDecimal(newvalue);
  }

  /**
  * Return value of numeric field E01MODPER as a BigDecimal.
  */
  public BigDecimal getBigDecimalE01MODPER()
  {
    return fieldE01MODPER.getBigDecimal();
  }

  /**
  * Set field E01MODPTS using a String value.
  */
  public void setE01MODPTS(String newvalue)
  {
    fieldE01MODPTS.setString(newvalue);
  }

  /**
  * Get value of field E01MODPTS as a String.
  */
  public String getE01MODPTS()
  {
    return fieldE01MODPTS.getString();
  }

  /**
  * Set numeric field E01MODPTS using a BigDecimal value.
  */
  public void setE01MODPTS(BigDecimal newvalue)
  {
    fieldE01MODPTS.setBigDecimal(newvalue);
  }

  /**
  * Return value of numeric field E01MODPTS as a BigDecimal.
  */
  public BigDecimal getBigDecimalE01MODPTS()
  {
    return fieldE01MODPTS.getBigDecimal();
  }

  /**
  * Set field E01MODCAT using a String value.
  */
  public void setE01MODCAT(String newvalue)
  {
    fieldE01MODCAT.setString(newvalue);
  }

  /**
  * Get value of field E01MODCAT as a String.
  */
  public String getE01MODCAT()
  {
    return fieldE01MODCAT.getString();
  }

  /**
  * Set numeric field E01MODCAT using a BigDecimal value.
  */
  public void setE01MODCAT(BigDecimal newvalue)
  {
    fieldE01MODCAT.setBigDecimal(newvalue);
  }

  /**
  * Return value of numeric field E01MODCAT as a BigDecimal.
  */
  public BigDecimal getBigDecimalE01MODCAT()
  {
    return fieldE01MODCAT.getBigDecimal();
  }

  /**
  * Set field E01PTSPTS using a String value.
  */
  public void setE01PTSPTS(String newvalue)
  {
    fieldE01PTSPTS.setString(newvalue);
  }

  /**
  * Get value of field E01PTSPTS as a String.
  */
  public String getE01PTSPTS()
  {
    return fieldE01PTSPTS.getString();
  }

  /**
  * Set numeric field E01PTSPTS using a BigDecimal value.
  */
  public void setE01PTSPTS(BigDecimal newvalue)
  {
    fieldE01PTSPTS.setBigDecimal(newvalue);
  }

  /**
  * Return value of numeric field E01PTSPTS as a BigDecimal.
  */
  public BigDecimal getBigDecimalE01PTSPTS()
  {
    return fieldE01PTSPTS.getBigDecimal();
  }

  /**
  * Set field E01PTSSEQ using a String value.
  */
  public void setE01PTSSEQ(String newvalue)
  {
    fieldE01PTSSEQ.setString(newvalue);
  }

  /**
  * Get value of field E01PTSSEQ as a String.
  */
  public String getE01PTSSEQ()
  {
    return fieldE01PTSSEQ.getString();
  }

  /**
  * Set numeric field E01PTSSEQ using a BigDecimal value.
  */
  public void setE01PTSSEQ(BigDecimal newvalue)
  {
    fieldE01PTSSEQ.setBigDecimal(newvalue);
  }

  /**
  * Return value of numeric field E01PTSSEQ as a BigDecimal.
  */
  public BigDecimal getBigDecimalE01PTSSEQ()
  {
    return fieldE01PTSSEQ.getBigDecimal();
  }

  /**
  * Set field E01PTSFLG using a String value.
  */
  public void setE01PTSFLG(String newvalue)
  {
    fieldE01PTSFLG.setString(newvalue);
  }

  /**
  * Get value of field E01PTSFLG as a String.
  */
  public String getE01PTSFLG()
  {
    return fieldE01PTSFLG.getString();
  }

  /**
  * Set field E01PTSCAL using a String value.
  */
  public void setE01PTSCAL(String newvalue)
  {
    fieldE01PTSCAL.setString(newvalue);
  }

  /**
  * Get value of field E01PTSCAL as a String.
  */
  public String getE01PTSCAL()
  {
    return fieldE01PTSCAL.getString();
  }

  /**
  * Set numeric field E01PTSCAL using a BigDecimal value.
  */
  public void setE01PTSCAL(BigDecimal newvalue)
  {
    fieldE01PTSCAL.setBigDecimal(newvalue);
  }

  /**
  * Return value of numeric field E01PTSCAL as a BigDecimal.
  */
  public BigDecimal getBigDecimalE01PTSCAL()
  {
    return fieldE01PTSCAL.getBigDecimal();
  }

  /**
  * Set field E01RECPOS using a String value.
  */
  public void setE01RECPOS(String newvalue)
  {
    fieldE01RECPOS.setString(newvalue);
  }

  /**
  * Get value of field E01RECPOS as a String.
  */
  public String getE01RECPOS()
  {
    return fieldE01RECPOS.getString();
  }

  /**
  * Set numeric field E01RECPOS using a BigDecimal value.
  */
  public void setE01RECPOS(BigDecimal newvalue)
  {
    fieldE01RECPOS.setBigDecimal(newvalue);
  }

  /**
  * Return value of numeric field E01RECPOS as a BigDecimal.
  */
  public BigDecimal getBigDecimalE01RECPOS()
  {
    return fieldE01RECPOS.getBigDecimal();
  }

  /**
  * Set field E01OPECDE using a String value.
  */
  public void setE01OPECDE(String newvalue)
  {
    fieldE01OPECDE.setString(newvalue);
  }

  /**
  * Get value of field E01OPECDE as a String.
  */
  public String getE01OPECDE()
  {
    return fieldE01OPECDE.getString();
  }

}
