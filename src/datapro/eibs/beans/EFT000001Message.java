package datapro.eibs.beans;

import datapro.eibs.sockets.*;
import java.io.*;
import java.math.*;

import java.util.*;

/**
* Class generated by AS/400 CRTCLASS command from EFT000001 physical file definition.
* 
* File level identifier is 1030121191920.
* Record format level identifier is 24F46847B3081.
*/

public class EFT000001Message extends MessageRecord
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
                                     "E01ACCNUM",
                                     "E01CUSCUN",
                                     "E01CUSNA1",
                                     "E01CUSNA2",
                                     "E01CUSNA3",
                                     "E01CUSNA4",
                                     "E01CUSCTY",
                                     "E01CUSSTE",
                                     "E01CUSCTR",
                                     "E01CUSZPC",
                                     "E01CUSPOB",
                                     "E01CUSIDN",
                                     "E01CUSTID",
                                     "E01CUSPID",
                                     "E01CUSLGT",
                                     "E01CUSFL1",
                                     "E01CUSBUC",
                                     "E01CUSINC",
                                     "E01DSCBUC",
                                     "E01DSCINC",
                                     "E01BANKNM",
                                     "E01BRNNME",
                                     "E01BRNADR",
                                     "E01BRNCIT",
                                     "E01BRNPHN",
                                     "E01CNTBID",
                                     "E01CNTRD1",
                                     "E01CNTRD2",
                                     "E01CNTRD3",
                                     "E01DSCPRO",
                                     "E01DSCTYP"
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
                                   "E01ACCNUM",
                                   "E01CUSCUN",
                                   "E01CUSNA1",
                                   "E01CUSNA2",
                                   "E01CUSNA3",
                                   "E01CUSNA4",
                                   "E01CUSCTY",
                                   "E01CUSSTE",
                                   "E01CUSCTR",
                                   "E01CUSZPC",
                                   "E01CUSPOB",
                                   "E01CUSIDN",
                                   "E01CUSTID",
                                   "E01CUSPID",
                                   "E01CUSLGT",
                                   "E01CUSFL1",
                                   "E01CUSBUC",
                                   "E01CUSINC",
                                   "E01DSCBUC",
                                   "E01DSCINC",
                                   "E01BANKNM",
                                   "E01BRNNME",
                                   "E01BRNADR",
                                   "E01BRNCIT",
                                   "E01BRNPHN",
                                   "E01CNTBID",
                                   "E01CNTRD1",
                                   "E01CNTRD2",
                                   "E01CNTRD3",
                                   "E01DSCPRO",
                                   "E01DSCTYP"
                                 };
  final static String fid = "1030121191920";
  final static String rid = "24F46847B3081";
  final static String fmtname = "EFT000001";
  final int FIELDCOUNT = 40;
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
  private DecimalField fieldE01ACCNUM = null;
  private DecimalField fieldE01CUSCUN = null;
  private CharacterField fieldE01CUSNA1 = null;
  private CharacterField fieldE01CUSNA2 = null;
  private CharacterField fieldE01CUSNA3 = null;
  private CharacterField fieldE01CUSNA4 = null;
  private CharacterField fieldE01CUSCTY = null;
  private CharacterField fieldE01CUSSTE = null;
  private CharacterField fieldE01CUSCTR = null;
  private CharacterField fieldE01CUSZPC = null;
  private CharacterField fieldE01CUSPOB = null;
  private CharacterField fieldE01CUSIDN = null;
  private CharacterField fieldE01CUSTID = null;
  private CharacterField fieldE01CUSPID = null;
  private CharacterField fieldE01CUSLGT = null;
  private CharacterField fieldE01CUSFL1 = null;
  private CharacterField fieldE01CUSBUC = null;
  private CharacterField fieldE01CUSINC = null;
  private CharacterField fieldE01DSCBUC = null;
  private CharacterField fieldE01DSCINC = null;
  private CharacterField fieldE01BANKNM = null;
  private CharacterField fieldE01BRNNME = null;
  private CharacterField fieldE01BRNADR = null;
  private CharacterField fieldE01BRNCIT = null;
  private DecimalField fieldE01BRNPHN = null;
  private CharacterField fieldE01CNTBID = null;
  private DecimalField fieldE01CNTRD1 = null;
  private DecimalField fieldE01CNTRD2 = null;
  private DecimalField fieldE01CNTRD3 = null;
  private CharacterField fieldE01DSCPRO = null;
  private CharacterField fieldE01DSCTYP = null;

  /**
  * Constructor for EFT000001Message.
  */
  public EFT000001Message()
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
    recordsize = 673;
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
    fields[9] = fieldE01ACCNUM =
    new DecimalField(message, HEADERSIZE + 42, 13, 0, "E01ACCNUM");
    fields[10] = fieldE01CUSCUN =
    new DecimalField(message, HEADERSIZE + 55, 10, 0, "E01CUSCUN");
    fields[11] = fieldE01CUSNA1 =
    new CharacterField(message, HEADERSIZE + 65, 45, "E01CUSNA1");
    fields[12] = fieldE01CUSNA2 =
    new CharacterField(message, HEADERSIZE + 110, 45, "E01CUSNA2");
    fields[13] = fieldE01CUSNA3 =
    new CharacterField(message, HEADERSIZE + 155, 45, "E01CUSNA3");
    fields[14] = fieldE01CUSNA4 =
    new CharacterField(message, HEADERSIZE + 200, 45, "E01CUSNA4");
    fields[15] = fieldE01CUSCTY =
    new CharacterField(message, HEADERSIZE + 245, 30, "E01CUSCTY");
    fields[16] = fieldE01CUSSTE =
    new CharacterField(message, HEADERSIZE + 275, 4, "E01CUSSTE");
    fields[17] = fieldE01CUSCTR =
    new CharacterField(message, HEADERSIZE + 279, 20, "E01CUSCTR");
    fields[18] = fieldE01CUSZPC =
    new CharacterField(message, HEADERSIZE + 299, 15, "E01CUSZPC");
    fields[19] = fieldE01CUSPOB =
    new CharacterField(message, HEADERSIZE + 314, 10, "E01CUSPOB");
    fields[20] = fieldE01CUSIDN =
    new CharacterField(message, HEADERSIZE + 324, 15, "E01CUSIDN");
    fields[21] = fieldE01CUSTID =
    new CharacterField(message, HEADERSIZE + 339, 4, "E01CUSTID");
    fields[22] = fieldE01CUSPID =
    new CharacterField(message, HEADERSIZE + 343, 4, "E01CUSPID");
    fields[23] = fieldE01CUSLGT =
    new CharacterField(message, HEADERSIZE + 347, 1, "E01CUSLGT");
    fields[24] = fieldE01CUSFL1 =
    new CharacterField(message, HEADERSIZE + 348, 1, "E01CUSFL1");
    fields[25] = fieldE01CUSBUC =
    new CharacterField(message, HEADERSIZE + 349, 4, "E01CUSBUC");
    fields[26] = fieldE01CUSINC =
    new CharacterField(message, HEADERSIZE + 353, 4, "E01CUSINC");
    fields[27] = fieldE01DSCBUC =
    new CharacterField(message, HEADERSIZE + 357, 35, "E01DSCBUC");
    fields[28] = fieldE01DSCINC =
    new CharacterField(message, HEADERSIZE + 392, 35, "E01DSCINC");
    fields[29] = fieldE01BANKNM =
    new CharacterField(message, HEADERSIZE + 427, 35, "E01BANKNM");
    fields[30] = fieldE01BRNNME =
    new CharacterField(message, HEADERSIZE + 462, 35, "E01BRNNME");
    fields[31] = fieldE01BRNADR =
    new CharacterField(message, HEADERSIZE + 497, 35, "E01BRNADR");
    fields[32] = fieldE01BRNCIT =
    new CharacterField(message, HEADERSIZE + 532, 35, "E01BRNCIT");
    fields[33] = fieldE01BRNPHN =
    new DecimalField(message, HEADERSIZE + 567, 12, 0, "E01BRNPHN");
    fields[34] = fieldE01CNTBID =
    new CharacterField(message, HEADERSIZE + 579, 15, "E01CNTBID");
    fields[35] = fieldE01CNTRD1 =
    new DecimalField(message, HEADERSIZE + 594, 3, 0, "E01CNTRD1");
    fields[36] = fieldE01CNTRD2 =
    new DecimalField(message, HEADERSIZE + 597, 3, 0, "E01CNTRD2");
    fields[37] = fieldE01CNTRD3 =
    new DecimalField(message, HEADERSIZE + 600, 3, 0, "E01CNTRD3");
    fields[38] = fieldE01DSCPRO =
    new CharacterField(message, HEADERSIZE + 603, 35, "E01DSCPRO");
    fields[39] = fieldE01DSCTYP =
    new CharacterField(message, HEADERSIZE + 638, 35, "E01DSCTYP");

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
  * Set field E01ACCNUM using a String value.
  */
  public void setE01ACCNUM(String newvalue)
  {
    fieldE01ACCNUM.setString(newvalue);
  }

  /**
  * Get value of field E01ACCNUM as a String.
  */
  public String getE01ACCNUM()
  {
    return fieldE01ACCNUM.getString();
  }

  /**
  * Set numeric field E01ACCNUM using a BigDecimal value.
  */
  public void setE01ACCNUM(BigDecimal newvalue)
  {
    fieldE01ACCNUM.setBigDecimal(newvalue);
  }

  /**
  * Return value of numeric field E01ACCNUM as a BigDecimal.
  */
  public BigDecimal getBigDecimalE01ACCNUM()
  {
    return fieldE01ACCNUM.getBigDecimal();
  }

  /**
  * Set field E01CUSCUN using a String value.
  */
  public void setE01CUSCUN(String newvalue)
  {
    fieldE01CUSCUN.setString(newvalue);
  }

  /**
  * Get value of field E01CUSCUN as a String.
  */
  public String getE01CUSCUN()
  {
    return fieldE01CUSCUN.getString();
  }

  /**
  * Set numeric field E01CUSCUN using a BigDecimal value.
  */
  public void setE01CUSCUN(BigDecimal newvalue)
  {
    fieldE01CUSCUN.setBigDecimal(newvalue);
  }

  /**
  * Return value of numeric field E01CUSCUN as a BigDecimal.
  */
  public BigDecimal getBigDecimalE01CUSCUN()
  {
    return fieldE01CUSCUN.getBigDecimal();
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
  * Set field E01CUSNA2 using a String value.
  */
  public void setE01CUSNA2(String newvalue)
  {
    fieldE01CUSNA2.setString(newvalue);
  }

  /**
  * Get value of field E01CUSNA2 as a String.
  */
  public String getE01CUSNA2()
  {
    return fieldE01CUSNA2.getString();
  }

  /**
  * Set field E01CUSNA3 using a String value.
  */
  public void setE01CUSNA3(String newvalue)
  {
    fieldE01CUSNA3.setString(newvalue);
  }

  /**
  * Get value of field E01CUSNA3 as a String.
  */
  public String getE01CUSNA3()
  {
    return fieldE01CUSNA3.getString();
  }

  /**
  * Set field E01CUSNA4 using a String value.
  */
  public void setE01CUSNA4(String newvalue)
  {
    fieldE01CUSNA4.setString(newvalue);
  }

  /**
  * Get value of field E01CUSNA4 as a String.
  */
  public String getE01CUSNA4()
  {
    return fieldE01CUSNA4.getString();
  }

  /**
  * Set field E01CUSCTY using a String value.
  */
  public void setE01CUSCTY(String newvalue)
  {
    fieldE01CUSCTY.setString(newvalue);
  }

  /**
  * Get value of field E01CUSCTY as a String.
  */
  public String getE01CUSCTY()
  {
    return fieldE01CUSCTY.getString();
  }

  /**
  * Set field E01CUSSTE using a String value.
  */
  public void setE01CUSSTE(String newvalue)
  {
    fieldE01CUSSTE.setString(newvalue);
  }

  /**
  * Get value of field E01CUSSTE as a String.
  */
  public String getE01CUSSTE()
  {
    return fieldE01CUSSTE.getString();
  }

  /**
  * Set field E01CUSCTR using a String value.
  */
  public void setE01CUSCTR(String newvalue)
  {
    fieldE01CUSCTR.setString(newvalue);
  }

  /**
  * Get value of field E01CUSCTR as a String.
  */
  public String getE01CUSCTR()
  {
    return fieldE01CUSCTR.getString();
  }

  /**
  * Set field E01CUSZPC using a String value.
  */
  public void setE01CUSZPC(String newvalue)
  {
    fieldE01CUSZPC.setString(newvalue);
  }

  /**
  * Get value of field E01CUSZPC as a String.
  */
  public String getE01CUSZPC()
  {
    return fieldE01CUSZPC.getString();
  }

  /**
  * Set field E01CUSPOB using a String value.
  */
  public void setE01CUSPOB(String newvalue)
  {
    fieldE01CUSPOB.setString(newvalue);
  }

  /**
  * Get value of field E01CUSPOB as a String.
  */
  public String getE01CUSPOB()
  {
    return fieldE01CUSPOB.getString();
  }

  /**
  * Set field E01CUSIDN using a String value.
  */
  public void setE01CUSIDN(String newvalue)
  {
    fieldE01CUSIDN.setString(newvalue);
  }

  /**
  * Get value of field E01CUSIDN as a String.
  */
  public String getE01CUSIDN()
  {
    return fieldE01CUSIDN.getString();
  }

  /**
  * Set field E01CUSTID using a String value.
  */
  public void setE01CUSTID(String newvalue)
  {
    fieldE01CUSTID.setString(newvalue);
  }

  /**
  * Get value of field E01CUSTID as a String.
  */
  public String getE01CUSTID()
  {
    return fieldE01CUSTID.getString();
  }

  /**
  * Set field E01CUSPID using a String value.
  */
  public void setE01CUSPID(String newvalue)
  {
    fieldE01CUSPID.setString(newvalue);
  }

  /**
  * Get value of field E01CUSPID as a String.
  */
  public String getE01CUSPID()
  {
    return fieldE01CUSPID.getString();
  }

  /**
  * Set field E01CUSLGT using a String value.
  */
  public void setE01CUSLGT(String newvalue)
  {
    fieldE01CUSLGT.setString(newvalue);
  }

  /**
  * Get value of field E01CUSLGT as a String.
  */
  public String getE01CUSLGT()
  {
    return fieldE01CUSLGT.getString();
  }

  /**
  * Set field E01CUSFL1 using a String value.
  */
  public void setE01CUSFL1(String newvalue)
  {
    fieldE01CUSFL1.setString(newvalue);
  }

  /**
  * Get value of field E01CUSFL1 as a String.
  */
  public String getE01CUSFL1()
  {
    return fieldE01CUSFL1.getString();
  }

  /**
  * Set field E01CUSBUC using a String value.
  */
  public void setE01CUSBUC(String newvalue)
  {
    fieldE01CUSBUC.setString(newvalue);
  }

  /**
  * Get value of field E01CUSBUC as a String.
  */
  public String getE01CUSBUC()
  {
    return fieldE01CUSBUC.getString();
  }

  /**
  * Set field E01CUSINC using a String value.
  */
  public void setE01CUSINC(String newvalue)
  {
    fieldE01CUSINC.setString(newvalue);
  }

  /**
  * Get value of field E01CUSINC as a String.
  */
  public String getE01CUSINC()
  {
    return fieldE01CUSINC.getString();
  }

  /**
  * Set field E01DSCBUC using a String value.
  */
  public void setE01DSCBUC(String newvalue)
  {
    fieldE01DSCBUC.setString(newvalue);
  }

  /**
  * Get value of field E01DSCBUC as a String.
  */
  public String getE01DSCBUC()
  {
    return fieldE01DSCBUC.getString();
  }

  /**
  * Set field E01DSCINC using a String value.
  */
  public void setE01DSCINC(String newvalue)
  {
    fieldE01DSCINC.setString(newvalue);
  }

  /**
  * Get value of field E01DSCINC as a String.
  */
  public String getE01DSCINC()
  {
    return fieldE01DSCINC.getString();
  }

  /**
  * Set field E01BANKNM using a String value.
  */
  public void setE01BANKNM(String newvalue)
  {
    fieldE01BANKNM.setString(newvalue);
  }

  /**
  * Get value of field E01BANKNM as a String.
  */
  public String getE01BANKNM()
  {
    return fieldE01BANKNM.getString();
  }

  /**
  * Set field E01BRNNME using a String value.
  */
  public void setE01BRNNME(String newvalue)
  {
    fieldE01BRNNME.setString(newvalue);
  }

  /**
  * Get value of field E01BRNNME as a String.
  */
  public String getE01BRNNME()
  {
    return fieldE01BRNNME.getString();
  }

  /**
  * Set field E01BRNADR using a String value.
  */
  public void setE01BRNADR(String newvalue)
  {
    fieldE01BRNADR.setString(newvalue);
  }

  /**
  * Get value of field E01BRNADR as a String.
  */
  public String getE01BRNADR()
  {
    return fieldE01BRNADR.getString();
  }

  /**
  * Set field E01BRNCIT using a String value.
  */
  public void setE01BRNCIT(String newvalue)
  {
    fieldE01BRNCIT.setString(newvalue);
  }

  /**
  * Get value of field E01BRNCIT as a String.
  */
  public String getE01BRNCIT()
  {
    return fieldE01BRNCIT.getString();
  }

  /**
  * Set field E01BRNPHN using a String value.
  */
  public void setE01BRNPHN(String newvalue)
  {
    fieldE01BRNPHN.setString(newvalue);
  }

  /**
  * Get value of field E01BRNPHN as a String.
  */
  public String getE01BRNPHN()
  {
    return fieldE01BRNPHN.getString();
  }

  /**
  * Set numeric field E01BRNPHN using a BigDecimal value.
  */
  public void setE01BRNPHN(BigDecimal newvalue)
  {
    fieldE01BRNPHN.setBigDecimal(newvalue);
  }

  /**
  * Return value of numeric field E01BRNPHN as a BigDecimal.
  */
  public BigDecimal getBigDecimalE01BRNPHN()
  {
    return fieldE01BRNPHN.getBigDecimal();
  }

  /**
  * Set field E01CNTBID using a String value.
  */
  public void setE01CNTBID(String newvalue)
  {
    fieldE01CNTBID.setString(newvalue);
  }

  /**
  * Get value of field E01CNTBID as a String.
  */
  public String getE01CNTBID()
  {
    return fieldE01CNTBID.getString();
  }

  /**
  * Set field E01CNTRD1 using a String value.
  */
  public void setE01CNTRD1(String newvalue)
  {
    fieldE01CNTRD1.setString(newvalue);
  }

  /**
  * Get value of field E01CNTRD1 as a String.
  */
  public String getE01CNTRD1()
  {
    return fieldE01CNTRD1.getString();
  }

  /**
  * Set numeric field E01CNTRD1 using a BigDecimal value.
  */
  public void setE01CNTRD1(BigDecimal newvalue)
  {
    fieldE01CNTRD1.setBigDecimal(newvalue);
  }

  /**
  * Return value of numeric field E01CNTRD1 as a BigDecimal.
  */
  public BigDecimal getBigDecimalE01CNTRD1()
  {
    return fieldE01CNTRD1.getBigDecimal();
  }

  /**
  * Set field E01CNTRD2 using a String value.
  */
  public void setE01CNTRD2(String newvalue)
  {
    fieldE01CNTRD2.setString(newvalue);
  }

  /**
  * Get value of field E01CNTRD2 as a String.
  */
  public String getE01CNTRD2()
  {
    return fieldE01CNTRD2.getString();
  }

  /**
  * Set numeric field E01CNTRD2 using a BigDecimal value.
  */
  public void setE01CNTRD2(BigDecimal newvalue)
  {
    fieldE01CNTRD2.setBigDecimal(newvalue);
  }

  /**
  * Return value of numeric field E01CNTRD2 as a BigDecimal.
  */
  public BigDecimal getBigDecimalE01CNTRD2()
  {
    return fieldE01CNTRD2.getBigDecimal();
  }

  /**
  * Set field E01CNTRD3 using a String value.
  */
  public void setE01CNTRD3(String newvalue)
  {
    fieldE01CNTRD3.setString(newvalue);
  }

  /**
  * Get value of field E01CNTRD3 as a String.
  */
  public String getE01CNTRD3()
  {
    return fieldE01CNTRD3.getString();
  }

  /**
  * Set numeric field E01CNTRD3 using a BigDecimal value.
  */
  public void setE01CNTRD3(BigDecimal newvalue)
  {
    fieldE01CNTRD3.setBigDecimal(newvalue);
  }

  /**
  * Return value of numeric field E01CNTRD3 as a BigDecimal.
  */
  public BigDecimal getBigDecimalE01CNTRD3()
  {
    return fieldE01CNTRD3.getBigDecimal();
  }

  /**
  * Set field E01DSCPRO using a String value.
  */
  public void setE01DSCPRO(String newvalue)
  {
    fieldE01DSCPRO.setString(newvalue);
  }

  /**
  * Get value of field E01DSCPRO as a String.
  */
  public String getE01DSCPRO()
  {
    return fieldE01DSCPRO.getString();
  }

  /**
  * Set field E01DSCTYP using a String value.
  */
  public void setE01DSCTYP(String newvalue)
  {
    fieldE01DSCTYP.setString(newvalue);
  }

  /**
  * Get value of field E01DSCTYP as a String.
  */
  public String getE01DSCTYP()
  {
    return fieldE01DSCTYP.getString();
  }

}
