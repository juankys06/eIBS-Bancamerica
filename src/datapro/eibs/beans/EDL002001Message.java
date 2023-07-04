package datapro.eibs.beans;

import datapro.eibs.sockets.*;
import java.io.*;
import java.math.*;

import java.util.*;

/**
* Class generated by AS/400 CRTCLASS command from EDL002001 physical file definition.
* 
* File level identifier is 1070425133140.
* Record format level identifier is 37521DAB6B14B.
*/

public class EDL002001Message extends MessageRecord
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
                                     "E01DEABNK",
                                     "E01DEAACC",
                                     "E01DEACUN",
                                     "E01FRMDTM",
                                     "E01FRMDTD",
                                     "E01FRMDTY",
                                     "E01TODATM",
                                     "E01TODATD",
                                     "E01TODATY",
                                     "E01DEAOPE"
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
                                   "E01DEABNK",
                                   "E01DEAACC",
                                   "E01DEACUN",
                                   "E01FRMDTM",
                                   "E01FRMDTD",
                                   "E01FRMDTY",
                                   "E01TODATM",
                                   "E01TODATD",
                                   "E01TODATY",
                                   "E01DEAOPE"
                                 };
  final static String fid = "1070425133140";
  final static String rid = "37521DAB6B14B";
  final static String fmtname = "EDL002001";
  final int FIELDCOUNT = 19;
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
  private CharacterField fieldE01DEABNK = null;
  private DecimalField fieldE01DEAACC = null;
  private DecimalField fieldE01DEACUN = null;
  private DecimalField fieldE01FRMDTM = null;
  private DecimalField fieldE01FRMDTD = null;
  private DecimalField fieldE01FRMDTY = null;
  private DecimalField fieldE01TODATM = null;
  private DecimalField fieldE01TODATD = null;
  private DecimalField fieldE01TODATY = null;
  private CharacterField fieldE01DEAOPE = null;

  /**
  * Constructor for EDL002001Message.
  */
  public EDL002001Message()
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
    recordsize = 86;
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
    fields[9] = fieldE01DEABNK =
    new CharacterField(message, HEADERSIZE + 42, 2, "E01DEABNK");
    fields[10] = fieldE01DEAACC =
    new DecimalField(message, HEADERSIZE + 44, 13, 0, "E01DEAACC");
    fields[11] = fieldE01DEACUN =
    new DecimalField(message, HEADERSIZE + 57, 10, 0, "E01DEACUN");
    fields[12] = fieldE01FRMDTM =
    new DecimalField(message, HEADERSIZE + 67, 3, 0, "E01FRMDTM");
    fields[13] = fieldE01FRMDTD =
    new DecimalField(message, HEADERSIZE + 70, 3, 0, "E01FRMDTD");
    fields[14] = fieldE01FRMDTY =
    new DecimalField(message, HEADERSIZE + 73, 3, 0, "E01FRMDTY");
    fields[15] = fieldE01TODATM =
    new DecimalField(message, HEADERSIZE + 76, 3, 0, "E01TODATM");
    fields[16] = fieldE01TODATD =
    new DecimalField(message, HEADERSIZE + 79, 3, 0, "E01TODATD");
    fields[17] = fieldE01TODATY =
    new DecimalField(message, HEADERSIZE + 82, 3, 0, "E01TODATY");
    fields[18] = fieldE01DEAOPE =
    new CharacterField(message, HEADERSIZE + 85, 1, "E01DEAOPE");

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
  * Set field E01DEABNK using a String value.
  */
  public void setE01DEABNK(String newvalue)
  {
    fieldE01DEABNK.setString(newvalue);
  }

  /**
  * Get value of field E01DEABNK as a String.
  */
  public String getE01DEABNK()
  {
    return fieldE01DEABNK.getString();
  }

  /**
  * Set field E01DEAACC using a String value.
  */
  public void setE01DEAACC(String newvalue)
  {
    fieldE01DEAACC.setString(newvalue);
  }

  /**
  * Get value of field E01DEAACC as a String.
  */
  public String getE01DEAACC()
  {
    return fieldE01DEAACC.getString();
  }

  /**
  * Set numeric field E01DEAACC using a BigDecimal value.
  */
  public void setE01DEAACC(BigDecimal newvalue)
  {
    fieldE01DEAACC.setBigDecimal(newvalue);
  }

  /**
  * Return value of numeric field E01DEAACC as a BigDecimal.
  */
  public BigDecimal getBigDecimalE01DEAACC()
  {
    return fieldE01DEAACC.getBigDecimal();
  }

  /**
  * Set field E01DEACUN using a String value.
  */
  public void setE01DEACUN(String newvalue)
  {
    fieldE01DEACUN.setString(newvalue);
  }

  /**
  * Get value of field E01DEACUN as a String.
  */
  public String getE01DEACUN()
  {
    return fieldE01DEACUN.getString();
  }

  /**
  * Set numeric field E01DEACUN using a BigDecimal value.
  */
  public void setE01DEACUN(BigDecimal newvalue)
  {
    fieldE01DEACUN.setBigDecimal(newvalue);
  }

  /**
  * Return value of numeric field E01DEACUN as a BigDecimal.
  */
  public BigDecimal getBigDecimalE01DEACUN()
  {
    return fieldE01DEACUN.getBigDecimal();
  }

  /**
  * Set field E01FRMDTM using a String value.
  */
  public void setE01FRMDTM(String newvalue)
  {
    fieldE01FRMDTM.setString(newvalue);
  }

  /**
  * Get value of field E01FRMDTM as a String.
  */
  public String getE01FRMDTM()
  {
    return fieldE01FRMDTM.getString();
  }

  /**
  * Set numeric field E01FRMDTM using a BigDecimal value.
  */
  public void setE01FRMDTM(BigDecimal newvalue)
  {
    fieldE01FRMDTM.setBigDecimal(newvalue);
  }

  /**
  * Return value of numeric field E01FRMDTM as a BigDecimal.
  */
  public BigDecimal getBigDecimalE01FRMDTM()
  {
    return fieldE01FRMDTM.getBigDecimal();
  }

  /**
  * Set field E01FRMDTD using a String value.
  */
  public void setE01FRMDTD(String newvalue)
  {
    fieldE01FRMDTD.setString(newvalue);
  }

  /**
  * Get value of field E01FRMDTD as a String.
  */
  public String getE01FRMDTD()
  {
    return fieldE01FRMDTD.getString();
  }

  /**
  * Set numeric field E01FRMDTD using a BigDecimal value.
  */
  public void setE01FRMDTD(BigDecimal newvalue)
  {
    fieldE01FRMDTD.setBigDecimal(newvalue);
  }

  /**
  * Return value of numeric field E01FRMDTD as a BigDecimal.
  */
  public BigDecimal getBigDecimalE01FRMDTD()
  {
    return fieldE01FRMDTD.getBigDecimal();
  }

  /**
  * Set field E01FRMDTY using a String value.
  */
  public void setE01FRMDTY(String newvalue)
  {
    fieldE01FRMDTY.setString(newvalue);
  }

  /**
  * Get value of field E01FRMDTY as a String.
  */
  public String getE01FRMDTY()
  {
    return fieldE01FRMDTY.getString();
  }

  /**
  * Set numeric field E01FRMDTY using a BigDecimal value.
  */
  public void setE01FRMDTY(BigDecimal newvalue)
  {
    fieldE01FRMDTY.setBigDecimal(newvalue);
  }

  /**
  * Return value of numeric field E01FRMDTY as a BigDecimal.
  */
  public BigDecimal getBigDecimalE01FRMDTY()
  {
    return fieldE01FRMDTY.getBigDecimal();
  }

  /**
  * Set field E01TODATM using a String value.
  */
  public void setE01TODATM(String newvalue)
  {
    fieldE01TODATM.setString(newvalue);
  }

  /**
  * Get value of field E01TODATM as a String.
  */
  public String getE01TODATM()
  {
    return fieldE01TODATM.getString();
  }

  /**
  * Set numeric field E01TODATM using a BigDecimal value.
  */
  public void setE01TODATM(BigDecimal newvalue)
  {
    fieldE01TODATM.setBigDecimal(newvalue);
  }

  /**
  * Return value of numeric field E01TODATM as a BigDecimal.
  */
  public BigDecimal getBigDecimalE01TODATM()
  {
    return fieldE01TODATM.getBigDecimal();
  }

  /**
  * Set field E01TODATD using a String value.
  */
  public void setE01TODATD(String newvalue)
  {
    fieldE01TODATD.setString(newvalue);
  }

  /**
  * Get value of field E01TODATD as a String.
  */
  public String getE01TODATD()
  {
    return fieldE01TODATD.getString();
  }

  /**
  * Set numeric field E01TODATD using a BigDecimal value.
  */
  public void setE01TODATD(BigDecimal newvalue)
  {
    fieldE01TODATD.setBigDecimal(newvalue);
  }

  /**
  * Return value of numeric field E01TODATD as a BigDecimal.
  */
  public BigDecimal getBigDecimalE01TODATD()
  {
    return fieldE01TODATD.getBigDecimal();
  }

  /**
  * Set field E01TODATY using a String value.
  */
  public void setE01TODATY(String newvalue)
  {
    fieldE01TODATY.setString(newvalue);
  }

  /**
  * Get value of field E01TODATY as a String.
  */
  public String getE01TODATY()
  {
    return fieldE01TODATY.getString();
  }

  /**
  * Set numeric field E01TODATY using a BigDecimal value.
  */
  public void setE01TODATY(BigDecimal newvalue)
  {
    fieldE01TODATY.setBigDecimal(newvalue);
  }

  /**
  * Return value of numeric field E01TODATY as a BigDecimal.
  */
  public BigDecimal getBigDecimalE01TODATY()
  {
    return fieldE01TODATY.getBigDecimal();
  }

  /**
  * Set field E01DEAOPE using a String value.
  */
  public void setE01DEAOPE(String newvalue)
  {
    fieldE01DEAOPE.setString(newvalue);
  }

  /**
  * Get value of field E01DEAOPE as a String.
  */
  public String getE01DEAOPE()
  {
    return fieldE01DEAOPE.getString();
  }

}
