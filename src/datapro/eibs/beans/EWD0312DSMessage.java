package datapro.eibs.beans;

import datapro.eibs.sockets.*;
import java.io.*;
import java.math.*;

/**
* Class generated by AS/400 CRTCLASS command from EWD0312DS physical file definition.
* 
* File level identifier is 1030115123555.
* Record format level identifier is 37F1761AE1E20.
*/

public class EWD0312DSMessage extends MessageRecord
{
  final int FIELDCOUNT = 12;
  private CharacterField fieldRWDUSR = null;
  private CharacterField fieldRWDTYP = null;
  private DecimalField fieldRWDFRC = null;
  private CharacterField fieldSWDBNK = null;
  private CharacterField fieldSWDTYP = null;
  private CharacterField fieldSWDTBL = null;
  private CharacterField fieldSWDTCO = null;
  private DecimalField fieldSWDCUN = null;
  private CharacterField fieldSWDDSC = null;
  private CharacterField fieldSWDDE0 = null;
  private DecimalField fieldSWDREC = null;
  private CharacterField fieldSWDOPE = null;

  /**
  * Constructor for EWD0312DSMessage.
  */
  public EWD0312DSMessage()
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
    recordsize = 137;
    fileid = "1030115123555";
    recordid = "37F1761AE1E20";
    message = new byte[getByteLength()];
    formatname = "EWD0312DS";
    fieldnames = new String[FIELDCOUNT];
    tagnames = new String[FIELDCOUNT];
    fields = new MessageField[FIELDCOUNT];

    fieldnames[0] = "RWDUSR";
    tagnames[0] = "RWDUSR";
    fields[0] = fieldRWDUSR =
    new CharacterField(message, HEADERSIZE + 0, 10, "RWDUSR");
    fieldnames[1] = "RWDTYP";
    tagnames[1] = "RWDTYP";
    fields[1] = fieldRWDTYP =
    new CharacterField(message, HEADERSIZE + 10, 1, "RWDTYP");
    fieldnames[2] = "RWDFRC";
    tagnames[2] = "RWDFRC";
    fields[2] = fieldRWDFRC =
    new DecimalField(message, HEADERSIZE + 11, 8, 0, "RWDFRC");
    fieldnames[3] = "SWDBNK";
    tagnames[3] = "SWDBNK";
    fields[3] = fieldSWDBNK =
    new CharacterField(message, HEADERSIZE + 19, 2, "SWDBNK");
    fieldnames[4] = "SWDTYP";
    tagnames[4] = "SWDTYP";
    fields[4] = fieldSWDTYP =
    new CharacterField(message, HEADERSIZE + 21, 4, "SWDTYP");
    fieldnames[5] = "SWDTBL";
    tagnames[5] = "SWDTBL";
    fields[5] = fieldSWDTBL =
    new CharacterField(message, HEADERSIZE + 25, 2, "SWDTBL");
    fieldnames[6] = "SWDTCO";
    tagnames[6] = "SWDTCO";
    fields[6] = fieldSWDTCO =
    new CharacterField(message, HEADERSIZE + 27, 1, "SWDTCO");
    fieldnames[7] = "SWDCUN";
    tagnames[7] = "SWDCUN";
    fields[7] = fieldSWDCUN =
    new DecimalField(message, HEADERSIZE + 28, 10, 0, "SWDCUN");
    fieldnames[8] = "SWDDSC";
    tagnames[8] = "SWDDSC";
    fields[8] = fieldSWDDSC =
    new CharacterField(message, HEADERSIZE + 38, 70, "SWDDSC");
    fieldnames[9] = "SWDDE0";
    tagnames[9] = "SWDDE0";
    fields[9] = fieldSWDDE0 =
    new CharacterField(message, HEADERSIZE + 108, 20, "SWDDE0");
    fieldnames[10] = "SWDREC";
    tagnames[10] = "SWDREC";
    fields[10] = fieldSWDREC =
    new DecimalField(message, HEADERSIZE + 128, 8, 0, "SWDREC");
    fieldnames[11] = "SWDOPE";
    tagnames[11] = "SWDOPE";
    fields[11] = fieldSWDOPE =
    new CharacterField(message, HEADERSIZE + 136, 1, "SWDOPE");
  }

  /**
  * Set field RWDUSR using a String value.
  */
  public void setRWDUSR(String newvalue)
  {
    fieldRWDUSR.setString(newvalue);
  }

  /**
  * Get value of field RWDUSR as a String.
  */
  public String getRWDUSR()
  {
    return fieldRWDUSR.getString();
  }

  /**
  * Set field RWDTYP using a String value.
  */
  public void setRWDTYP(String newvalue)
  {
    fieldRWDTYP.setString(newvalue);
  }

  /**
  * Get value of field RWDTYP as a String.
  */
  public String getRWDTYP()
  {
    return fieldRWDTYP.getString();
  }

  /**
  * Set field RWDFRC using a String value.
  */
  public void setRWDFRC(String newvalue)
  {
    fieldRWDFRC.setString(newvalue);
  }

  /**
  * Get value of field RWDFRC as a String.
  */
  public String getRWDFRC()
  {
    return fieldRWDFRC.getString();
  }

  /**
  * Set numeric field RWDFRC using a BigDecimal value.
  */
  public void setRWDFRC(BigDecimal newvalue)
  {
    fieldRWDFRC.setBigDecimal(newvalue);
  }

  /**
  * Return value of numeric field RWDFRC as a BigDecimal.
  */
  public BigDecimal getBigDecimalRWDFRC()
  {
    return fieldRWDFRC.getBigDecimal();
  }

  /**
  * Set field SWDBNK using a String value.
  */
  public void setSWDBNK(String newvalue)
  {
    fieldSWDBNK.setString(newvalue);
  }

  /**
  * Get value of field SWDBNK as a String.
  */
  public String getSWDBNK()
  {
    return fieldSWDBNK.getString();
  }

  /**
  * Set field SWDTYP using a String value.
  */
  public void setSWDTYP(String newvalue)
  {
    fieldSWDTYP.setString(newvalue);
  }

  /**
  * Get value of field SWDTYP as a String.
  */
  public String getSWDTYP()
  {
    return fieldSWDTYP.getString();
  }

  /**
  * Set field SWDTBL using a String value.
  */
  public void setSWDTBL(String newvalue)
  {
    fieldSWDTBL.setString(newvalue);
  }

  /**
  * Get value of field SWDTBL as a String.
  */
  public String getSWDTBL()
  {
    return fieldSWDTBL.getString();
  }

  /**
  * Set field SWDTCO using a String value.
  */
  public void setSWDTCO(String newvalue)
  {
    fieldSWDTCO.setString(newvalue);
  }

  /**
  * Get value of field SWDTCO as a String.
  */
  public String getSWDTCO()
  {
    return fieldSWDTCO.getString();
  }

  /**
  * Set field SWDCUN using a String value.
  */
  public void setSWDCUN(String newvalue)
  {
    fieldSWDCUN.setString(newvalue);
  }

  /**
  * Get value of field SWDCUN as a String.
  */
  public String getSWDCUN()
  {
    return fieldSWDCUN.getString();
  }

  /**
  * Set numeric field SWDCUN using a BigDecimal value.
  */
  public void setSWDCUN(BigDecimal newvalue)
  {
    fieldSWDCUN.setBigDecimal(newvalue);
  }

  /**
  * Return value of numeric field SWDCUN as a BigDecimal.
  */
  public BigDecimal getBigDecimalSWDCUN()
  {
    return fieldSWDCUN.getBigDecimal();
  }

  /**
  * Set field SWDDSC using a String value.
  */
  public void setSWDDSC(String newvalue)
  {
    fieldSWDDSC.setString(newvalue);
  }

  /**
  * Get value of field SWDDSC as a String.
  */
  public String getSWDDSC()
  {
    return fieldSWDDSC.getString();
  }

  /**
  * Set field SWDDE0 using a String value.
  */
  public void setSWDDE0(String newvalue)
  {
    fieldSWDDE0.setString(newvalue);
  }

  /**
  * Get value of field SWDDE0 as a String.
  */
  public String getSWDDE0()
  {
    return fieldSWDDE0.getString();
  }

  /**
  * Set field SWDREC using a String value.
  */
  public void setSWDREC(String newvalue)
  {
    fieldSWDREC.setString(newvalue);
  }

  /**
  * Get value of field SWDREC as a String.
  */
  public String getSWDREC()
  {
    return fieldSWDREC.getString();
  }

  /**
  * Set numeric field SWDREC using a BigDecimal value.
  */
  public void setSWDREC(BigDecimal newvalue)
  {
    fieldSWDREC.setBigDecimal(newvalue);
  }

  /**
  * Return value of numeric field SWDREC as a BigDecimal.
  */
  public BigDecimal getBigDecimalSWDREC()
  {
    return fieldSWDREC.getBigDecimal();
  }

  /**
  * Set field SWDOPE using a String value.
  */
  public void setSWDOPE(String newvalue)
  {
    fieldSWDOPE.setString(newvalue);
  }

  /**
  * Get value of field SWDOPE as a String.
  */
  public String getSWDOPE()
  {
    return fieldSWDOPE.getString();
  }

}
