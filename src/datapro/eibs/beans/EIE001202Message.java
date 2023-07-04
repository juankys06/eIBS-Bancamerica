package datapro.eibs.beans;

import datapro.eibs.sockets.*;
import java.io.*;
import java.math.*;

/**
* Class generated by AS/400 CRTCLASS command from EIE001202 physical file definition.
* 
* File level identifier is 1030115123335.
* Record format level identifier is 49FAE3BDB82DA.
*/

public class EIE001202Message extends MessageRecord
{
  final int FIELDCOUNT = 14;
  private CharacterField fieldH02USERID = null;
  private CharacterField fieldH02PROGRM = null;
  private CharacterField fieldH02TIMSYS = null;
  private CharacterField fieldH02SCRCOD = null;
  private CharacterField fieldH02OPECOD = null;
  private CharacterField fieldH02FLGMAS = null;
  private CharacterField fieldH02FLGWK1 = null;
  private CharacterField fieldH02FLGWK2 = null;
  private CharacterField fieldH02FLGWK3 = null;
  private DecimalField fieldE02PRFCUN = null;
  private DecimalField fieldE02PRFNUM = null;
  private CharacterField fieldE02PRFBNK = null;
  private CharacterField fieldE02ACTION = null;
  private CharacterField fieldE02MSGTXT = null;

  /**
  * Constructor for EIE001202Message.
  */
  public EIE001202Message()
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
    recordsize = 1065;
    fileid = "1030115123335";
    recordid = "49FAE3BDB82DA";
    message = new byte[getByteLength()];
    formatname = "EIE001202";
    fieldnames = new String[FIELDCOUNT];
    tagnames = new String[FIELDCOUNT];
    fields = new MessageField[FIELDCOUNT];

    fieldnames[0] = "H02USERID";
    tagnames[0] = "H02USERID";
    fields[0] = fieldH02USERID =
    new CharacterField(message, HEADERSIZE + 0, 10, "H02USERID");
    fieldnames[1] = "H02PROGRM";
    tagnames[1] = "H02PROGRM";
    fields[1] = fieldH02PROGRM =
    new CharacterField(message, HEADERSIZE + 10, 10, "H02PROGRM");
    fieldnames[2] = "H02TIMSYS";
    tagnames[2] = "H02TIMSYS";
    fields[2] = fieldH02TIMSYS =
    new CharacterField(message, HEADERSIZE + 20, 12, "H02TIMSYS");
    fieldnames[3] = "H02SCRCOD";
    tagnames[3] = "H02SCRCOD";
    fields[3] = fieldH02SCRCOD =
    new CharacterField(message, HEADERSIZE + 32, 2, "H02SCRCOD");
    fieldnames[4] = "H02OPECOD";
    tagnames[4] = "H02OPECOD";
    fields[4] = fieldH02OPECOD =
    new CharacterField(message, HEADERSIZE + 34, 4, "H02OPECOD");
    fieldnames[5] = "H02FLGMAS";
    tagnames[5] = "H02FLGMAS";
    fields[5] = fieldH02FLGMAS =
    new CharacterField(message, HEADERSIZE + 38, 1, "H02FLGMAS");
    fieldnames[6] = "H02FLGWK1";
    tagnames[6] = "H02FLGWK1";
    fields[6] = fieldH02FLGWK1 =
    new CharacterField(message, HEADERSIZE + 39, 1, "H02FLGWK1");
    fieldnames[7] = "H02FLGWK2";
    tagnames[7] = "H02FLGWK2";
    fields[7] = fieldH02FLGWK2 =
    new CharacterField(message, HEADERSIZE + 40, 1, "H02FLGWK2");
    fieldnames[8] = "H02FLGWK3";
    tagnames[8] = "H02FLGWK3";
    fields[8] = fieldH02FLGWK3 =
    new CharacterField(message, HEADERSIZE + 41, 1, "H02FLGWK3");
    fieldnames[9] = "E02PRFCUN";
    tagnames[9] = "E02PRFCUN";
    fields[9] = fieldE02PRFCUN =
    new DecimalField(message, HEADERSIZE + 42, 10, 0, "E02PRFCUN");
    fieldnames[10] = "E02PRFNUM";
    tagnames[10] = "E02PRFNUM";
    fields[10] = fieldE02PRFNUM =
    new DecimalField(message, HEADERSIZE + 52, 10, 0, "E02PRFNUM");
    fieldnames[11] = "E02PRFBNK";
    tagnames[11] = "E02PRFBNK";
    fields[11] = fieldE02PRFBNK =
    new CharacterField(message, HEADERSIZE + 62, 2, "E02PRFBNK");
    fieldnames[12] = "E02ACTION";
    tagnames[12] = "E02ACTION";
    fields[12] = fieldE02ACTION =
    new CharacterField(message, HEADERSIZE + 64, 1, "E02ACTION");
    fieldnames[13] = "E02MSGTXT";
    tagnames[13] = "E02MSGTXT";
    fields[13] = fieldE02MSGTXT =
    new CharacterField(message, HEADERSIZE + 65, 1000, "E02MSGTXT");
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
  * Set field E02PRFCUN using a String value.
  */
  public void setE02PRFCUN(String newvalue)
  {
    fieldE02PRFCUN.setString(newvalue);
  }

  /**
  * Get value of field E02PRFCUN as a String.
  */
  public String getE02PRFCUN()
  {
    return fieldE02PRFCUN.getString();
  }

  /**
  * Set numeric field E02PRFCUN using a BigDecimal value.
  */
  public void setE02PRFCUN(BigDecimal newvalue)
  {
    fieldE02PRFCUN.setBigDecimal(newvalue);
  }

  /**
  * Return value of numeric field E02PRFCUN as a BigDecimal.
  */
  public BigDecimal getBigDecimalE02PRFCUN()
  {
    return fieldE02PRFCUN.getBigDecimal();
  }

  /**
  * Set field E02PRFNUM using a String value.
  */
  public void setE02PRFNUM(String newvalue)
  {
    fieldE02PRFNUM.setString(newvalue);
  }

  /**
  * Get value of field E02PRFNUM as a String.
  */
  public String getE02PRFNUM()
  {
    return fieldE02PRFNUM.getString();
  }

  /**
  * Set numeric field E02PRFNUM using a BigDecimal value.
  */
  public void setE02PRFNUM(BigDecimal newvalue)
  {
    fieldE02PRFNUM.setBigDecimal(newvalue);
  }

  /**
  * Return value of numeric field E02PRFNUM as a BigDecimal.
  */
  public BigDecimal getBigDecimalE02PRFNUM()
  {
    return fieldE02PRFNUM.getBigDecimal();
  }

  /**
  * Set field E02PRFBNK using a String value.
  */
  public void setE02PRFBNK(String newvalue)
  {
    fieldE02PRFBNK.setString(newvalue);
  }

  /**
  * Get value of field E02PRFBNK as a String.
  */
  public String getE02PRFBNK()
  {
    return fieldE02PRFBNK.getString();
  }

  /**
  * Set field E02ACTION using a String value.
  */
  public void setE02ACTION(String newvalue)
  {
    fieldE02ACTION.setString(newvalue);
  }

  /**
  * Get value of field E02ACTION as a String.
  */
  public String getE02ACTION()
  {
    return fieldE02ACTION.getString();
  }

  /**
  * Set field E02MSGTXT using a String value.
  */
  public void setE02MSGTXT(String newvalue)
  {
    fieldE02MSGTXT.setString(newvalue);
  }

  /**
  * Get value of field E02MSGTXT as a String.
  */
  public String getE02MSGTXT()
  {
    return fieldE02MSGTXT.getString();
  }

}