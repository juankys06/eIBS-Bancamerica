package datapro.eibs.beans;

import datapro.eibs.sockets.*;
import java.io.*;
import java.math.*;

import java.util.*;

/**
* Class generated by AS/400 CRTCLASS command from ESD014001 physical file definition.
* 
* File level identifier is 1030121192003.
* Record format level identifier is 2CFB9246CBFE6.
*/

public class ESD014001Message extends MessageRecord
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
                                     "E01APLCDE",
                                     "E01LNENUM",
                                     "E01CUSNUM",
                                     "E01CUSNA1",
                                     "E01PRODUC",
                                     "E01BNKNUM",
                                     "E01BRANCH",
                                     "E01CURRCY",
                                     "E01GLNUMB",
                                     "E01ACTCLS",
                                     "E01CSTCNT",
                                     "E01BALAMN",
                                     "E01BTHNUM",
                                     "E01REMARK",
                                     "E01OPETYP",
                                     "E01OPERID",
                                     "E01FILCOD"
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
                                   "E01APLCDE",
                                   "E01LNENUM",
                                   "E01CUSNUM",
                                   "E01CUSNA1",
                                   "E01PRODUC",
                                   "E01BNKNUM",
                                   "E01BRANCH",
                                   "E01CURRCY",
                                   "E01GLNUMB",
                                   "E01ACTCLS",
                                   "E01CSTCNT",
                                   "E01BALAMN",
                                   "E01BTHNUM",
                                   "E01REMARK",
                                   "E01OPETYP",
                                   "E01OPERID",
                                   "E01FILCOD"
                                 };
  final static String fid = "1030121192003";
  final static String rid = "2CFB9246CBFE6";
  final static String fmtname = "ESD014001";
  final int FIELDCOUNT = 27;
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
  private CharacterField fieldE01APLCDE = null;
  private DecimalField fieldE01LNENUM = null;
  private DecimalField fieldE01CUSNUM = null;
  private CharacterField fieldE01CUSNA1 = null;
  private CharacterField fieldE01PRODUC = null;
  private CharacterField fieldE01BNKNUM = null;
  private DecimalField fieldE01BRANCH = null;
  private CharacterField fieldE01CURRCY = null;
  private DecimalField fieldE01GLNUMB = null;
  private DecimalField fieldE01ACTCLS = null;
  private DecimalField fieldE01CSTCNT = null;
  private DecimalField fieldE01BALAMN = null;
  private DecimalField fieldE01BTHNUM = null;
  private CharacterField fieldE01REMARK = null;
  private CharacterField fieldE01OPETYP = null;
  private CharacterField fieldE01OPERID = null;
  private CharacterField fieldE01FILCOD = null;

  /**
  * Constructor for ESD014001Message.
  */
  public ESD014001Message()
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
    recordsize = 214;
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
    fields[10] = fieldE01APLCDE =
    new CharacterField(message, HEADERSIZE + 55, 2, "E01APLCDE");
    fields[11] = fieldE01LNENUM =
    new DecimalField(message, HEADERSIZE + 57, 5, 0, "E01LNENUM");
    fields[12] = fieldE01CUSNUM =
    new DecimalField(message, HEADERSIZE + 62, 10, 0, "E01CUSNUM");
    fields[13] = fieldE01CUSNA1 =
    new CharacterField(message, HEADERSIZE + 72, 45, "E01CUSNA1");
    fields[14] = fieldE01PRODUC =
    new CharacterField(message, HEADERSIZE + 117, 4, "E01PRODUC");
    fields[15] = fieldE01BNKNUM =
    new CharacterField(message, HEADERSIZE + 121, 2, "E01BNKNUM");
    fields[16] = fieldE01BRANCH =
    new DecimalField(message, HEADERSIZE + 123, 4, 0, "E01BRANCH");
    fields[17] = fieldE01CURRCY =
    new CharacterField(message, HEADERSIZE + 127, 3, "E01CURRCY");
    fields[18] = fieldE01GLNUMB =
    new DecimalField(message, HEADERSIZE + 130, 17, 0, "E01GLNUMB");
    fields[19] = fieldE01ACTCLS =
    new DecimalField(message, HEADERSIZE + 147, 3, 0, "E01ACTCLS");
    fields[20] = fieldE01CSTCNT =
    new DecimalField(message, HEADERSIZE + 150, 9, 0, "E01CSTCNT");
    fields[21] = fieldE01BALAMN =
    new DecimalField(message, HEADERSIZE + 159, 17, 2, "E01BALAMN");
    fields[22] = fieldE01BTHNUM =
    new DecimalField(message, HEADERSIZE + 176, 5, 0, "E01BTHNUM");
    fields[23] = fieldE01REMARK =
    new CharacterField(message, HEADERSIZE + 181, 20, "E01REMARK");
    fields[24] = fieldE01OPETYP =
    new CharacterField(message, HEADERSIZE + 201, 2, "E01OPETYP");
    fields[25] = fieldE01OPERID =
    new CharacterField(message, HEADERSIZE + 203, 10, "E01OPERID");
    fields[26] = fieldE01FILCOD =
    new CharacterField(message, HEADERSIZE + 213, 1, "E01FILCOD");

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
  * Set field E01APLCDE using a String value.
  */
  public void setE01APLCDE(String newvalue)
  {
    fieldE01APLCDE.setString(newvalue);
  }

  /**
  * Get value of field E01APLCDE as a String.
  */
  public String getE01APLCDE()
  {
    return fieldE01APLCDE.getString();
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
  * Set field E01PRODUC using a String value.
  */
  public void setE01PRODUC(String newvalue)
  {
    fieldE01PRODUC.setString(newvalue);
  }

  /**
  * Get value of field E01PRODUC as a String.
  */
  public String getE01PRODUC()
  {
    return fieldE01PRODUC.getString();
  }

  /**
  * Set field E01BNKNUM using a String value.
  */
  public void setE01BNKNUM(String newvalue)
  {
    fieldE01BNKNUM.setString(newvalue);
  }

  /**
  * Get value of field E01BNKNUM as a String.
  */
  public String getE01BNKNUM()
  {
    return fieldE01BNKNUM.getString();
  }

  /**
  * Set field E01BRANCH using a String value.
  */
  public void setE01BRANCH(String newvalue)
  {
    fieldE01BRANCH.setString(newvalue);
  }

  /**
  * Get value of field E01BRANCH as a String.
  */
  public String getE01BRANCH()
  {
    return fieldE01BRANCH.getString();
  }

  /**
  * Set numeric field E01BRANCH using a BigDecimal value.
  */
  public void setE01BRANCH(BigDecimal newvalue)
  {
    fieldE01BRANCH.setBigDecimal(newvalue);
  }

  /**
  * Return value of numeric field E01BRANCH as a BigDecimal.
  */
  public BigDecimal getBigDecimalE01BRANCH()
  {
    return fieldE01BRANCH.getBigDecimal();
  }

  /**
  * Set field E01CURRCY using a String value.
  */
  public void setE01CURRCY(String newvalue)
  {
    fieldE01CURRCY.setString(newvalue);
  }

  /**
  * Get value of field E01CURRCY as a String.
  */
  public String getE01CURRCY()
  {
    return fieldE01CURRCY.getString();
  }

  /**
  * Set field E01GLNUMB using a String value.
  */
  public void setE01GLNUMB(String newvalue)
  {
    fieldE01GLNUMB.setString(newvalue);
  }

  /**
  * Get value of field E01GLNUMB as a String.
  */
  public String getE01GLNUMB()
  {
    return fieldE01GLNUMB.getString();
  }

  /**
  * Set numeric field E01GLNUMB using a BigDecimal value.
  */
  public void setE01GLNUMB(BigDecimal newvalue)
  {
    fieldE01GLNUMB.setBigDecimal(newvalue);
  }

  /**
  * Return value of numeric field E01GLNUMB as a BigDecimal.
  */
  public BigDecimal getBigDecimalE01GLNUMB()
  {
    return fieldE01GLNUMB.getBigDecimal();
  }

  /**
  * Set field E01ACTCLS using a String value.
  */
  public void setE01ACTCLS(String newvalue)
  {
    fieldE01ACTCLS.setString(newvalue);
  }

  /**
  * Get value of field E01ACTCLS as a String.
  */
  public String getE01ACTCLS()
  {
    return fieldE01ACTCLS.getString();
  }

  /**
  * Set numeric field E01ACTCLS using a BigDecimal value.
  */
  public void setE01ACTCLS(BigDecimal newvalue)
  {
    fieldE01ACTCLS.setBigDecimal(newvalue);
  }

  /**
  * Return value of numeric field E01ACTCLS as a BigDecimal.
  */
  public BigDecimal getBigDecimalE01ACTCLS()
  {
    return fieldE01ACTCLS.getBigDecimal();
  }

  /**
  * Set field E01CSTCNT using a String value.
  */
  public void setE01CSTCNT(String newvalue)
  {
    fieldE01CSTCNT.setString(newvalue);
  }

  /**
  * Get value of field E01CSTCNT as a String.
  */
  public String getE01CSTCNT()
  {
    return fieldE01CSTCNT.getString();
  }

  /**
  * Set numeric field E01CSTCNT using a BigDecimal value.
  */
  public void setE01CSTCNT(BigDecimal newvalue)
  {
    fieldE01CSTCNT.setBigDecimal(newvalue);
  }

  /**
  * Return value of numeric field E01CSTCNT as a BigDecimal.
  */
  public BigDecimal getBigDecimalE01CSTCNT()
  {
    return fieldE01CSTCNT.getBigDecimal();
  }

  /**
  * Set field E01BALAMN using a String value.
  */
  public void setE01BALAMN(String newvalue)
  {
    fieldE01BALAMN.setString(newvalue);
  }

  /**
  * Get value of field E01BALAMN as a String.
  */
  public String getE01BALAMN()
  {
    return fieldE01BALAMN.getString();
  }

  /**
  * Set numeric field E01BALAMN using a BigDecimal value.
  */
  public void setE01BALAMN(BigDecimal newvalue)
  {
    fieldE01BALAMN.setBigDecimal(newvalue);
  }

  /**
  * Return value of numeric field E01BALAMN as a BigDecimal.
  */
  public BigDecimal getBigDecimalE01BALAMN()
  {
    return fieldE01BALAMN.getBigDecimal();
  }

  /**
  * Set field E01BTHNUM using a String value.
  */
  public void setE01BTHNUM(String newvalue)
  {
    fieldE01BTHNUM.setString(newvalue);
  }

  /**
  * Get value of field E01BTHNUM as a String.
  */
  public String getE01BTHNUM()
  {
    return fieldE01BTHNUM.getString();
  }

  /**
  * Set numeric field E01BTHNUM using a BigDecimal value.
  */
  public void setE01BTHNUM(BigDecimal newvalue)
  {
    fieldE01BTHNUM.setBigDecimal(newvalue);
  }

  /**
  * Return value of numeric field E01BTHNUM as a BigDecimal.
  */
  public BigDecimal getBigDecimalE01BTHNUM()
  {
    return fieldE01BTHNUM.getBigDecimal();
  }

  /**
  * Set field E01REMARK using a String value.
  */
  public void setE01REMARK(String newvalue)
  {
    fieldE01REMARK.setString(newvalue);
  }

  /**
  * Get value of field E01REMARK as a String.
  */
  public String getE01REMARK()
  {
    return fieldE01REMARK.getString();
  }

  /**
  * Set field E01OPETYP using a String value.
  */
  public void setE01OPETYP(String newvalue)
  {
    fieldE01OPETYP.setString(newvalue);
  }

  /**
  * Get value of field E01OPETYP as a String.
  */
  public String getE01OPETYP()
  {
    return fieldE01OPETYP.getString();
  }

  /**
  * Set field E01OPERID using a String value.
  */
  public void setE01OPERID(String newvalue)
  {
    fieldE01OPERID.setString(newvalue);
  }

  /**
  * Get value of field E01OPERID as a String.
  */
  public String getE01OPERID()
  {
    return fieldE01OPERID.getString();
  }

  /**
  * Set field E01FILCOD using a String value.
  */
  public void setE01FILCOD(String newvalue)
  {
    fieldE01FILCOD.setString(newvalue);
  }

  /**
  * Get value of field E01FILCOD as a String.
  */
  public String getE01FILCOD()
  {
    return fieldE01FILCOD.getString();
  }

}
