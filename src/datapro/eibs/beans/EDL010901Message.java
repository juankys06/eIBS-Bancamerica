package datapro.eibs.beans;

import datapro.eibs.sockets.*;
import java.io.*;
import java.math.*;

import java.util.*;

/**
* Class generated by AS/400 CRTCLASS command from EDL010901 physical file definition.
* 
* File level identifier is 1060822183718.
* Record format level identifier is 47294588D9EE3.
*/

public class EDL010901Message extends MessageRecord
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
                                     "E01DLIACC",
                                     "E01DEAACD",
                                     "E01DLICUS",
                                     "E01CUSNA1",
                                     "E01DEAPRO",
                                     "E01DLIBNK",
                                     "E01DLIBRN",
                                     "E01DLIFCV",
                                     "E01DLIUSR",
                                     "E01FLGBUS",
                                     "E01DLIRMK",
                                     "E01DLIOPT"
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
                                   "E01DLIACC",
                                   "E01DEAACD",
                                   "E01DLICUS",
                                   "E01CUSNA1",
                                   "E01DEAPRO",
                                   "E01DLIBNK",
                                   "E01DLIBRN",
                                   "E01DLIFCV",
                                   "E01DLIUSR",
                                   "E01FLGBUS",
                                   "E01DLIRMK",
                                   "E01DLIOPT"
                                 };
  final static String fid = "1060822183718";
  final static String rid = "47294588D9EE3";
  final static String fmtname = "EDL010901";
  final int FIELDCOUNT = 21;
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
  private DecimalField fieldE01DLIACC = null;
  private CharacterField fieldE01DEAACD = null;
  private DecimalField fieldE01DLICUS = null;
  private CharacterField fieldE01CUSNA1 = null;
  private CharacterField fieldE01DEAPRO = null;
  private CharacterField fieldE01DLIBNK = null;
  private DecimalField fieldE01DLIBRN = null;
  private DecimalField fieldE01DLIFCV = null;
  private CharacterField fieldE01DLIUSR = null;
  private CharacterField fieldE01FLGBUS = null;
  private CharacterField fieldE01DLIRMK = null;
  private CharacterField fieldE01DLIOPT = null;

  /**
  * Constructor for EDL010901Message.
  */
  public EDL010901Message()
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
    recordsize = 174;
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
    fields[9] = fieldE01DLIACC =
    new DecimalField(message, HEADERSIZE + 42, 13, 0, "E01DLIACC");
    fields[10] = fieldE01DEAACD =
    new CharacterField(message, HEADERSIZE + 55, 2, "E01DEAACD");
    fields[11] = fieldE01DLICUS =
    new DecimalField(message, HEADERSIZE + 57, 10, 0, "E01DLICUS");
    fields[12] = fieldE01CUSNA1 =
    new CharacterField(message, HEADERSIZE + 67, 45, "E01CUSNA1");
    fields[13] = fieldE01DEAPRO =
    new CharacterField(message, HEADERSIZE + 112, 4, "E01DEAPRO");
    fields[14] = fieldE01DLIBNK =
    new CharacterField(message, HEADERSIZE + 116, 2, "E01DLIBNK");
    fields[15] = fieldE01DLIBRN =
    new DecimalField(message, HEADERSIZE + 118, 4, 0, "E01DLIBRN");
    fields[16] = fieldE01DLIFCV =
    new DecimalField(message, HEADERSIZE + 122, 17, 2, "E01DLIFCV");
    fields[17] = fieldE01DLIUSR =
    new CharacterField(message, HEADERSIZE + 139, 10, "E01DLIUSR");
    fields[18] = fieldE01FLGBUS =
    new CharacterField(message, HEADERSIZE + 149, 4, "E01FLGBUS");
    fields[19] = fieldE01DLIRMK =
    new CharacterField(message, HEADERSIZE + 153, 20, "E01DLIRMK");
    fields[20] = fieldE01DLIOPT =
    new CharacterField(message, HEADERSIZE + 173, 1, "E01DLIOPT");

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
  * Set field E01DLIACC using a String value.
  */
  public void setE01DLIACC(String newvalue)
  {
    fieldE01DLIACC.setString(newvalue);
  }

  /**
  * Get value of field E01DLIACC as a String.
  */
  public String getE01DLIACC()
  {
    return fieldE01DLIACC.getString();
  }

  /**
  * Set numeric field E01DLIACC using a BigDecimal value.
  */
  public void setE01DLIACC(BigDecimal newvalue)
  {
    fieldE01DLIACC.setBigDecimal(newvalue);
  }

  /**
  * Return value of numeric field E01DLIACC as a BigDecimal.
  */
  public BigDecimal getBigDecimalE01DLIACC()
  {
    return fieldE01DLIACC.getBigDecimal();
  }

  /**
  * Set field E01DEAACD using a String value.
  */
  public void setE01DEAACD(String newvalue)
  {
    fieldE01DEAACD.setString(newvalue);
  }

  /**
  * Get value of field E01DEAACD as a String.
  */
  public String getE01DEAACD()
  {
    return fieldE01DEAACD.getString();
  }

  /**
  * Set field E01DLICUS using a String value.
  */
  public void setE01DLICUS(String newvalue)
  {
    fieldE01DLICUS.setString(newvalue);
  }

  /**
  * Get value of field E01DLICUS as a String.
  */
  public String getE01DLICUS()
  {
    return fieldE01DLICUS.getString();
  }

  /**
  * Set numeric field E01DLICUS using a BigDecimal value.
  */
  public void setE01DLICUS(BigDecimal newvalue)
  {
    fieldE01DLICUS.setBigDecimal(newvalue);
  }

  /**
  * Return value of numeric field E01DLICUS as a BigDecimal.
  */
  public BigDecimal getBigDecimalE01DLICUS()
  {
    return fieldE01DLICUS.getBigDecimal();
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
  * Set field E01DEAPRO using a String value.
  */
  public void setE01DEAPRO(String newvalue)
  {
    fieldE01DEAPRO.setString(newvalue);
  }

  /**
  * Get value of field E01DEAPRO as a String.
  */
  public String getE01DEAPRO()
  {
    return fieldE01DEAPRO.getString();
  }

  /**
  * Set field E01DLIBNK using a String value.
  */
  public void setE01DLIBNK(String newvalue)
  {
    fieldE01DLIBNK.setString(newvalue);
  }

  /**
  * Get value of field E01DLIBNK as a String.
  */
  public String getE01DLIBNK()
  {
    return fieldE01DLIBNK.getString();
  }

  /**
  * Set field E01DLIBRN using a String value.
  */
  public void setE01DLIBRN(String newvalue)
  {
    fieldE01DLIBRN.setString(newvalue);
  }

  /**
  * Get value of field E01DLIBRN as a String.
  */
  public String getE01DLIBRN()
  {
    return fieldE01DLIBRN.getString();
  }

  /**
  * Set numeric field E01DLIBRN using a BigDecimal value.
  */
  public void setE01DLIBRN(BigDecimal newvalue)
  {
    fieldE01DLIBRN.setBigDecimal(newvalue);
  }

  /**
  * Return value of numeric field E01DLIBRN as a BigDecimal.
  */
  public BigDecimal getBigDecimalE01DLIBRN()
  {
    return fieldE01DLIBRN.getBigDecimal();
  }

  /**
  * Set field E01DLIFCV using a String value.
  */
  public void setE01DLIFCV(String newvalue)
  {
    fieldE01DLIFCV.setString(newvalue);
  }

  /**
  * Get value of field E01DLIFCV as a String.
  */
  public String getE01DLIFCV()
  {
    return fieldE01DLIFCV.getString();
  }

  /**
  * Set numeric field E01DLIFCV using a BigDecimal value.
  */
  public void setE01DLIFCV(BigDecimal newvalue)
  {
    fieldE01DLIFCV.setBigDecimal(newvalue);
  }

  /**
  * Return value of numeric field E01DLIFCV as a BigDecimal.
  */
  public BigDecimal getBigDecimalE01DLIFCV()
  {
    return fieldE01DLIFCV.getBigDecimal();
  }

  /**
  * Set field E01DLIUSR using a String value.
  */
  public void setE01DLIUSR(String newvalue)
  {
    fieldE01DLIUSR.setString(newvalue);
  }

  /**
  * Get value of field E01DLIUSR as a String.
  */
  public String getE01DLIUSR()
  {
    return fieldE01DLIUSR.getString();
  }

  /**
  * Set field E01FLGBUS using a String value.
  */
  public void setE01FLGBUS(String newvalue)
  {
    fieldE01FLGBUS.setString(newvalue);
  }

  /**
  * Get value of field E01FLGBUS as a String.
  */
  public String getE01FLGBUS()
  {
    return fieldE01FLGBUS.getString();
  }

  /**
  * Set field E01DLIRMK using a String value.
  */
  public void setE01DLIRMK(String newvalue)
  {
    fieldE01DLIRMK.setString(newvalue);
  }

  /**
  * Get value of field E01DLIRMK as a String.
  */
  public String getE01DLIRMK()
  {
    return fieldE01DLIRMK.getString();
  }

  /**
  * Set field E01DLIOPT using a String value.
  */
  public void setE01DLIOPT(String newvalue)
  {
    fieldE01DLIOPT.setString(newvalue);
  }

  /**
  * Get value of field E01DLIOPT as a String.
  */
  public String getE01DLIOPT()
  {
    return fieldE01DLIOPT.getString();
  }

}
