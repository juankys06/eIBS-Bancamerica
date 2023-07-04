package datapro.eibs.beans;

import datapro.eibs.sockets.*;
import java.io.*;
import java.math.*;

/**
* Class generated by AS/400 CRTCLASS command from EDC00050 physical file definition.
* 
* File level identifier is 1021224085624.
* Record format level identifier is 3D4E52A16274B.
*/

public class EDC00050Message extends MessageRecord
{
  final int FIELDCOUNT = 17;
  private CharacterField fieldH01USERID = null;
  private CharacterField fieldH01PROGRM = null;
  private CharacterField fieldH01TIMSYS = null;
  private CharacterField fieldH01SCRCOD = null;
  private CharacterField fieldH01OPECOD = null;
  private CharacterField fieldH01FLGMAS = null;
  private CharacterField fieldH01FLGWK1 = null;
  private CharacterField fieldH01FLGWK2 = null;
  private CharacterField fieldH01FLGWK3 = null;
  private CharacterField fieldH01RUT = null;
  private DecimalField fieldH01NCL = null;
  private CharacterField fieldH01CUSIDN = null;
  private DecimalField fieldH01CUSCUN = null;
  private DecimalField fieldH01DEUDIR = null;
  private DecimalField fieldH01DEUIND = null;
  private DecimalField fieldH01DEUCOM = null;
  private DecimalField fieldH01DEUTOT = null;

  /**
  * Constructor for EDC00050Message.
  */
  public EDC00050Message()
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
    recordsize = 164;
    fileid = "1021224085624";
    recordid = "3D4E52A16274B";
    message = new byte[getByteLength()];
    formatname = "EDC00050";
    fieldnames = new String[FIELDCOUNT];
    tagnames = new String[FIELDCOUNT];
    fields = new MessageField[FIELDCOUNT];

    fieldnames[0] = "H01USERID";
    tagnames[0] = "H01USERID";
    fields[0] = fieldH01USERID =
    new CharacterField(message, HEADERSIZE + 0, 10, "H01USERID");
    fieldnames[1] = "H01PROGRM";
    tagnames[1] = "H01PROGRM";
    fields[1] = fieldH01PROGRM =
    new CharacterField(message, HEADERSIZE + 10, 10, "H01PROGRM");
    fieldnames[2] = "H01TIMSYS";
    tagnames[2] = "H01TIMSYS";
    fields[2] = fieldH01TIMSYS =
    new CharacterField(message, HEADERSIZE + 20, 12, "H01TIMSYS");
    fieldnames[3] = "H01SCRCOD";
    tagnames[3] = "H01SCRCOD";
    fields[3] = fieldH01SCRCOD =
    new CharacterField(message, HEADERSIZE + 32, 2, "H01SCRCOD");
    fieldnames[4] = "H01OPECOD";
    tagnames[4] = "H01OPECOD";
    fields[4] = fieldH01OPECOD =
    new CharacterField(message, HEADERSIZE + 34, 4, "H01OPECOD");
    fieldnames[5] = "H01FLGMAS";
    tagnames[5] = "H01FLGMAS";
    fields[5] = fieldH01FLGMAS =
    new CharacterField(message, HEADERSIZE + 38, 1, "H01FLGMAS");
    fieldnames[6] = "H01FLGWK1";
    tagnames[6] = "H01FLGWK1";
    fields[6] = fieldH01FLGWK1 =
    new CharacterField(message, HEADERSIZE + 39, 1, "H01FLGWK1");
    fieldnames[7] = "H01FLGWK2";
    tagnames[7] = "H01FLGWK2";
    fields[7] = fieldH01FLGWK2 =
    new CharacterField(message, HEADERSIZE + 40, 1, "H01FLGWK2");
    fieldnames[8] = "H01FLGWK3";
    tagnames[8] = "H01FLGWK3";
    fields[8] = fieldH01FLGWK3 =
    new CharacterField(message, HEADERSIZE + 41, 1, "H01FLGWK3");
    fieldnames[9] = "H01RUT";
    tagnames[9] = "H01RUT";
    fields[9] = fieldH01RUT =
    new CharacterField(message, HEADERSIZE + 42, 15, "H01RUT");
    fieldnames[10] = "H01NCL";
    tagnames[10] = "H01NCL";
    fields[10] = fieldH01NCL =
    new DecimalField(message, HEADERSIZE + 57, 10, 0, "H01NCL");
    fieldnames[11] = "H01CUSIDN";
    tagnames[11] = "H01CUSIDN";
    fields[11] = fieldH01CUSIDN =
    new CharacterField(message, HEADERSIZE + 67, 15, "H01CUSIDN");
    fieldnames[12] = "H01CUSCUN";
    tagnames[12] = "H01CUSCUN";
    fields[12] = fieldH01CUSCUN =
    new DecimalField(message, HEADERSIZE + 82, 10, 0, "H01CUSCUN");
    fieldnames[13] = "H01DEUDIR";
    tagnames[13] = "H01DEUDIR";
    fields[13] = fieldH01DEUDIR =
    new DecimalField(message, HEADERSIZE + 92, 18, 0, "H01DEUDIR");
    fieldnames[14] = "H01DEUIND";
    tagnames[14] = "H01DEUIND";
    fields[14] = fieldH01DEUIND =
    new DecimalField(message, HEADERSIZE + 110, 18, 0, "H01DEUIND");
    fieldnames[15] = "H01DEUCOM";
    tagnames[15] = "H01DEUCOM";
    fields[15] = fieldH01DEUCOM =
    new DecimalField(message, HEADERSIZE + 128, 18, 0, "H01DEUCOM");
    fieldnames[16] = "H01DEUTOT";
    tagnames[16] = "H01DEUTOT";
    fields[16] = fieldH01DEUTOT =
    new DecimalField(message, HEADERSIZE + 146, 18, 0, "H01DEUTOT");
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
  * Set field H01RUT using a String value.
  */
  public void setH01RUT(String newvalue)
  {
    fieldH01RUT.setString(newvalue);
  }

  /**
  * Get value of field H01RUT as a String.
  */
  public String getH01RUT()
  {
    return fieldH01RUT.getString();
  }

  /**
  * Set field H01NCL using a String value.
  */
  public void setH01NCL(String newvalue)
  {
    fieldH01NCL.setString(newvalue);
  }

  /**
  * Get value of field H01NCL as a String.
  */
  public String getH01NCL()
  {
    return fieldH01NCL.getString();
  }

  /**
  * Set numeric field H01NCL using a BigDecimal value.
  */
  public void setH01NCL(BigDecimal newvalue)
  {
    fieldH01NCL.setBigDecimal(newvalue);
  }

  /**
  * Return value of numeric field H01NCL as a BigDecimal.
  */
  public BigDecimal getBigDecimalH01NCL()
  {
    return fieldH01NCL.getBigDecimal();
  }

  /**
  * Set field H01CUSIDN using a String value.
  */
  public void setH01CUSIDN(String newvalue)
  {
    fieldH01CUSIDN.setString(newvalue);
  }

  /**
  * Get value of field H01CUSIDN as a String.
  */
  public String getH01CUSIDN()
  {
    return fieldH01CUSIDN.getString();
  }

  /**
  * Set field H01CUSCUN using a String value.
  */
  public void setH01CUSCUN(String newvalue)
  {
    fieldH01CUSCUN.setString(newvalue);
  }

  /**
  * Get value of field H01CUSCUN as a String.
  */
  public String getH01CUSCUN()
  {
    return fieldH01CUSCUN.getString();
  }

  /**
  * Set numeric field H01CUSCUN using a BigDecimal value.
  */
  public void setH01CUSCUN(BigDecimal newvalue)
  {
    fieldH01CUSCUN.setBigDecimal(newvalue);
  }

  /**
  * Return value of numeric field H01CUSCUN as a BigDecimal.
  */
  public BigDecimal getBigDecimalH01CUSCUN()
  {
    return fieldH01CUSCUN.getBigDecimal();
  }

  /**
  * Set field H01DEUDIR using a String value.
  */
  public void setH01DEUDIR(String newvalue)
  {
    fieldH01DEUDIR.setString(newvalue);
  }

  /**
  * Get value of field H01DEUDIR as a String.
  */
  public String getH01DEUDIR()
  {
    return fieldH01DEUDIR.getString();
  }

  /**
  * Set numeric field H01DEUDIR using a BigDecimal value.
  */
  public void setH01DEUDIR(BigDecimal newvalue)
  {
    fieldH01DEUDIR.setBigDecimal(newvalue);
  }

  /**
  * Return value of numeric field H01DEUDIR as a BigDecimal.
  */
  public BigDecimal getBigDecimalH01DEUDIR()
  {
    return fieldH01DEUDIR.getBigDecimal();
  }

  /**
  * Set field H01DEUIND using a String value.
  */
  public void setH01DEUIND(String newvalue)
  {
    fieldH01DEUIND.setString(newvalue);
  }

  /**
  * Get value of field H01DEUIND as a String.
  */
  public String getH01DEUIND()
  {
    return fieldH01DEUIND.getString();
  }

  /**
  * Set numeric field H01DEUIND using a BigDecimal value.
  */
  public void setH01DEUIND(BigDecimal newvalue)
  {
    fieldH01DEUIND.setBigDecimal(newvalue);
  }

  /**
  * Return value of numeric field H01DEUIND as a BigDecimal.
  */
  public BigDecimal getBigDecimalH01DEUIND()
  {
    return fieldH01DEUIND.getBigDecimal();
  }

  /**
  * Set field H01DEUCOM using a String value.
  */
  public void setH01DEUCOM(String newvalue)
  {
    fieldH01DEUCOM.setString(newvalue);
  }

  /**
  * Get value of field H01DEUCOM as a String.
  */
  public String getH01DEUCOM()
  {
    return fieldH01DEUCOM.getString();
  }

  /**
  * Set numeric field H01DEUCOM using a BigDecimal value.
  */
  public void setH01DEUCOM(BigDecimal newvalue)
  {
    fieldH01DEUCOM.setBigDecimal(newvalue);
  }

  /**
  * Return value of numeric field H01DEUCOM as a BigDecimal.
  */
  public BigDecimal getBigDecimalH01DEUCOM()
  {
    return fieldH01DEUCOM.getBigDecimal();
  }

  /**
  * Set field H01DEUTOT using a String value.
  */
  public void setH01DEUTOT(String newvalue)
  {
    fieldH01DEUTOT.setString(newvalue);
  }

  /**
  * Get value of field H01DEUTOT as a String.
  */
  public String getH01DEUTOT()
  {
    return fieldH01DEUTOT.getString();
  }

  /**
  * Set numeric field H01DEUTOT using a BigDecimal value.
  */
  public void setH01DEUTOT(BigDecimal newvalue)
  {
    fieldH01DEUTOT.setBigDecimal(newvalue);
  }

  /**
  * Return value of numeric field H01DEUTOT as a BigDecimal.
  */
  public BigDecimal getBigDecimalH01DEUTOT()
  {
    return fieldH01DEUTOT.getBigDecimal();
  }

}