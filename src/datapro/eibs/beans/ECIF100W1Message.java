package datapro.eibs.beans;

import datapro.eibs.sockets.*;
import java.io.*;
import java.math.*;

/**
* Class generated by AS/400 CRTCLASS command from ECIF100W1 physical file definition.
* 
* File level identifier is 1020501193855.
* Record format level identifier is 336B3A0E12ECF.
*/

public class ECIF100W1Message extends MessageRecord
{
  final int FIELDCOUNT = 23;
  private CharacterField fieldCI1LOANYN = null;
  private DecimalField fieldCI1DEAACC = null;
  private DecimalField fieldCI1DEATRM = null;
  private CharacterField fieldCI1DEATRC = null;
  private CharacterField fieldCI1CUSSHN = null;
  private DecimalField fieldCI1CUSTOM = null;
  private DecimalField fieldCI1BALANC = null;
  private DecimalField fieldCI1EXCRTE = null;
  private DecimalField fieldCI1BALBSE = null;
  private DecimalField fieldCI1INTRTE = null;
  private DecimalField fieldCI1MATDT1 = null;
  private DecimalField fieldCI1MATDT2 = null;
  private DecimalField fieldCI1MATDT3 = null;
  private DecimalField fieldCI1OPNDT1 = null;
  private DecimalField fieldCI1OPNDT2 = null;
  private DecimalField fieldCI1OPNDT3 = null;
  private DecimalField fieldCI1INIDT1 = null;
  private DecimalField fieldCI1INIDT2 = null;
  private DecimalField fieldCI1INIDT3 = null;
  private CharacterField fieldCI1PRODUC = null;
  private CharacterField fieldCI1OFCCDE = null;
  private CharacterField fieldCI1OFCNME = null;
  private CharacterField fieldCI1OLDRSP = null;

  /**
  * Constructor for ECIF100W1Message.
  */
  public ECIF100W1Message()
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
    recordsize = 205;
    fileid = "1020501193855";
    recordid = "336B3A0E12ECF";
    message = new byte[getByteLength()];
    formatname = "ECIF100W1";
    fieldnames = new String[FIELDCOUNT];
    tagnames = new String[FIELDCOUNT];
    fields = new MessageField[FIELDCOUNT];

    fieldnames[0] = "CI1LOANYN";
    tagnames[0] = "CI1LOANYN";
    fields[0] = fieldCI1LOANYN =
    new CharacterField(message, HEADERSIZE + 0, 1, "CI1LOANYN");
    fieldnames[1] = "CI1DEAACC";
    tagnames[1] = "CI1DEAACC";
    fields[1] = fieldCI1DEAACC =
    new DecimalField(message, HEADERSIZE + 1, 13, 0, "CI1DEAACC");
    fieldnames[2] = "CI1DEATRM";
    tagnames[2] = "CI1DEATRM";
    fields[2] = fieldCI1DEATRM =
    new DecimalField(message, HEADERSIZE + 14, 5, 0, "CI1DEATRM");
    fieldnames[3] = "CI1DEATRC";
    tagnames[3] = "CI1DEATRC";
    fields[3] = fieldCI1DEATRC =
    new CharacterField(message, HEADERSIZE + 19, 1, "CI1DEATRC");
    fieldnames[4] = "CI1CUSSHN";
    tagnames[4] = "CI1CUSSHN";
    fields[4] = fieldCI1CUSSHN =
    new CharacterField(message, HEADERSIZE + 20, 35, "CI1CUSSHN");
    fieldnames[5] = "CI1CUSTOM";
    tagnames[5] = "CI1CUSTOM";
    fields[5] = fieldCI1CUSTOM =
    new DecimalField(message, HEADERSIZE + 55, 10, 0, "CI1CUSTOM");
    fieldnames[6] = "CI1BALANC";
    tagnames[6] = "CI1BALANC";
    fields[6] = fieldCI1BALANC =
    new DecimalField(message, HEADERSIZE + 65, 17, 2, "CI1BALANC");
    fieldnames[7] = "CI1EXCRTE";
    tagnames[7] = "CI1EXCRTE";
    fields[7] = fieldCI1EXCRTE =
    new DecimalField(message, HEADERSIZE + 82, 13, 6, "CI1EXCRTE");
    fieldnames[8] = "CI1BALBSE";
    tagnames[8] = "CI1BALBSE";
    fields[8] = fieldCI1BALBSE =
    new DecimalField(message, HEADERSIZE + 95, 17, 2, "CI1BALBSE");
    fieldnames[9] = "CI1INTRTE";
    tagnames[9] = "CI1INTRTE";
    fields[9] = fieldCI1INTRTE =
    new DecimalField(message, HEADERSIZE + 112, 11, 6, "CI1INTRTE");
    fieldnames[10] = "CI1MATDT1";
    tagnames[10] = "CI1MATDT1";
    fields[10] = fieldCI1MATDT1 =
    new DecimalField(message, HEADERSIZE + 123, 3, 0, "CI1MATDT1");
    fieldnames[11] = "CI1MATDT2";
    tagnames[11] = "CI1MATDT2";
    fields[11] = fieldCI1MATDT2 =
    new DecimalField(message, HEADERSIZE + 126, 3, 0, "CI1MATDT2");
    fieldnames[12] = "CI1MATDT3";
    tagnames[12] = "CI1MATDT3";
    fields[12] = fieldCI1MATDT3 =
    new DecimalField(message, HEADERSIZE + 129, 3, 0, "CI1MATDT3");
    fieldnames[13] = "CI1OPNDT1";
    tagnames[13] = "CI1OPNDT1";
    fields[13] = fieldCI1OPNDT1 =
    new DecimalField(message, HEADERSIZE + 132, 3, 0, "CI1OPNDT1");
    fieldnames[14] = "CI1OPNDT2";
    tagnames[14] = "CI1OPNDT2";
    fields[14] = fieldCI1OPNDT2 =
    new DecimalField(message, HEADERSIZE + 135, 3, 0, "CI1OPNDT2");
    fieldnames[15] = "CI1OPNDT3";
    tagnames[15] = "CI1OPNDT3";
    fields[15] = fieldCI1OPNDT3 =
    new DecimalField(message, HEADERSIZE + 138, 3, 0, "CI1OPNDT3");
    fieldnames[16] = "CI1INIDT1";
    tagnames[16] = "CI1INIDT1";
    fields[16] = fieldCI1INIDT1 =
    new DecimalField(message, HEADERSIZE + 141, 3, 0, "CI1INIDT1");
    fieldnames[17] = "CI1INIDT2";
    tagnames[17] = "CI1INIDT2";
    fields[17] = fieldCI1INIDT2 =
    new DecimalField(message, HEADERSIZE + 144, 3, 0, "CI1INIDT2");
    fieldnames[18] = "CI1INIDT3";
    tagnames[18] = "CI1INIDT3";
    fields[18] = fieldCI1INIDT3 =
    new DecimalField(message, HEADERSIZE + 147, 3, 0, "CI1INIDT3");
    fieldnames[19] = "CI1PRODUC";
    tagnames[19] = "CI1PRODUC";
    fields[19] = fieldCI1PRODUC =
    new CharacterField(message, HEADERSIZE + 150, 4, "CI1PRODUC");
    fieldnames[20] = "CI1OFCCDE";
    tagnames[20] = "CI1OFCCDE";
    fields[20] = fieldCI1OFCCDE =
    new CharacterField(message, HEADERSIZE + 154, 4, "CI1OFCCDE");
    fieldnames[21] = "CI1OFCNME";
    tagnames[21] = "CI1OFCNME";
    fields[21] = fieldCI1OFCNME =
    new CharacterField(message, HEADERSIZE + 158, 35, "CI1OFCNME");
    fieldnames[22] = "CI1OLDRSP";
    tagnames[22] = "CI1OLDRSP";
    fields[22] = fieldCI1OLDRSP =
    new CharacterField(message, HEADERSIZE + 193, 12, "CI1OLDRSP");
  }

  /**
  * Set field CI1LOANYN using a String value.
  */
  public void setCI1LOANYN(String newvalue)
  {
    fieldCI1LOANYN.setString(newvalue);
  }

  /**
  * Get value of field CI1LOANYN as a String.
  */
  public String getCI1LOANYN()
  {
    return fieldCI1LOANYN.getString();
  }

  /**
  * Set field CI1DEAACC using a String value.
  */
  public void setCI1DEAACC(String newvalue)
  {
    fieldCI1DEAACC.setString(newvalue);
  }

  /**
  * Get value of field CI1DEAACC as a String.
  */
  public String getCI1DEAACC()
  {
    return fieldCI1DEAACC.getString();
  }

  /**
  * Set numeric field CI1DEAACC using a BigDecimal value.
  */
  public void setCI1DEAACC(BigDecimal newvalue)
  {
    fieldCI1DEAACC.setBigDecimal(newvalue);
  }

  /**
  * Return value of numeric field CI1DEAACC as a BigDecimal.
  */
  public BigDecimal getBigDecimalCI1DEAACC()
  {
    return fieldCI1DEAACC.getBigDecimal();
  }

  /**
  * Set field CI1DEATRM using a String value.
  */
  public void setCI1DEATRM(String newvalue)
  {
    fieldCI1DEATRM.setString(newvalue);
  }

  /**
  * Get value of field CI1DEATRM as a String.
  */
  public String getCI1DEATRM()
  {
    return fieldCI1DEATRM.getString();
  }

  /**
  * Set numeric field CI1DEATRM using a BigDecimal value.
  */
  public void setCI1DEATRM(BigDecimal newvalue)
  {
    fieldCI1DEATRM.setBigDecimal(newvalue);
  }

  /**
  * Return value of numeric field CI1DEATRM as a BigDecimal.
  */
  public BigDecimal getBigDecimalCI1DEATRM()
  {
    return fieldCI1DEATRM.getBigDecimal();
  }

  /**
  * Set field CI1DEATRC using a String value.
  */
  public void setCI1DEATRC(String newvalue)
  {
    fieldCI1DEATRC.setString(newvalue);
  }

  /**
  * Get value of field CI1DEATRC as a String.
  */
  public String getCI1DEATRC()
  {
    return fieldCI1DEATRC.getString();
  }

  /**
  * Set field CI1CUSSHN using a String value.
  */
  public void setCI1CUSSHN(String newvalue)
  {
    fieldCI1CUSSHN.setString(newvalue);
  }

  /**
  * Get value of field CI1CUSSHN as a String.
  */
  public String getCI1CUSSHN()
  {
    return fieldCI1CUSSHN.getString();
  }

  /**
  * Set field CI1CUSTOM using a String value.
  */
  public void setCI1CUSTOM(String newvalue)
  {
    fieldCI1CUSTOM.setString(newvalue);
  }

  /**
  * Get value of field CI1CUSTOM as a String.
  */
  public String getCI1CUSTOM()
  {
    return fieldCI1CUSTOM.getString();
  }

  /**
  * Set numeric field CI1CUSTOM using a BigDecimal value.
  */
  public void setCI1CUSTOM(BigDecimal newvalue)
  {
    fieldCI1CUSTOM.setBigDecimal(newvalue);
  }

  /**
  * Return value of numeric field CI1CUSTOM as a BigDecimal.
  */
  public BigDecimal getBigDecimalCI1CUSTOM()
  {
    return fieldCI1CUSTOM.getBigDecimal();
  }

  /**
  * Set field CI1BALANC using a String value.
  */
  public void setCI1BALANC(String newvalue)
  {
    fieldCI1BALANC.setString(newvalue);
  }

  /**
  * Get value of field CI1BALANC as a String.
  */
  public String getCI1BALANC()
  {
    return fieldCI1BALANC.getString();
  }

  /**
  * Set numeric field CI1BALANC using a BigDecimal value.
  */
  public void setCI1BALANC(BigDecimal newvalue)
  {
    fieldCI1BALANC.setBigDecimal(newvalue);
  }

  /**
  * Return value of numeric field CI1BALANC as a BigDecimal.
  */
  public BigDecimal getBigDecimalCI1BALANC()
  {
    return fieldCI1BALANC.getBigDecimal();
  }

  /**
  * Set field CI1EXCRTE using a String value.
  */
  public void setCI1EXCRTE(String newvalue)
  {
    fieldCI1EXCRTE.setString(newvalue);
  }

  /**
  * Get value of field CI1EXCRTE as a String.
  */
  public String getCI1EXCRTE()
  {
    return fieldCI1EXCRTE.getString();
  }

  /**
  * Set numeric field CI1EXCRTE using a BigDecimal value.
  */
  public void setCI1EXCRTE(BigDecimal newvalue)
  {
    fieldCI1EXCRTE.setBigDecimal(newvalue);
  }

  /**
  * Return value of numeric field CI1EXCRTE as a BigDecimal.
  */
  public BigDecimal getBigDecimalCI1EXCRTE()
  {
    return fieldCI1EXCRTE.getBigDecimal();
  }

  /**
  * Set field CI1BALBSE using a String value.
  */
  public void setCI1BALBSE(String newvalue)
  {
    fieldCI1BALBSE.setString(newvalue);
  }

  /**
  * Get value of field CI1BALBSE as a String.
  */
  public String getCI1BALBSE()
  {
    return fieldCI1BALBSE.getString();
  }

  /**
  * Set numeric field CI1BALBSE using a BigDecimal value.
  */
  public void setCI1BALBSE(BigDecimal newvalue)
  {
    fieldCI1BALBSE.setBigDecimal(newvalue);
  }

  /**
  * Return value of numeric field CI1BALBSE as a BigDecimal.
  */
  public BigDecimal getBigDecimalCI1BALBSE()
  {
    return fieldCI1BALBSE.getBigDecimal();
  }

  /**
  * Set field CI1INTRTE using a String value.
  */
  public void setCI1INTRTE(String newvalue)
  {
    fieldCI1INTRTE.setString(newvalue);
  }

  /**
  * Get value of field CI1INTRTE as a String.
  */
  public String getCI1INTRTE()
  {
    return fieldCI1INTRTE.getString();
  }

  /**
  * Set numeric field CI1INTRTE using a BigDecimal value.
  */
  public void setCI1INTRTE(BigDecimal newvalue)
  {
    fieldCI1INTRTE.setBigDecimal(newvalue);
  }

  /**
  * Return value of numeric field CI1INTRTE as a BigDecimal.
  */
  public BigDecimal getBigDecimalCI1INTRTE()
  {
    return fieldCI1INTRTE.getBigDecimal();
  }

  /**
  * Set field CI1MATDT1 using a String value.
  */
  public void setCI1MATDT1(String newvalue)
  {
    fieldCI1MATDT1.setString(newvalue);
  }

  /**
  * Get value of field CI1MATDT1 as a String.
  */
  public String getCI1MATDT1()
  {
    return fieldCI1MATDT1.getString();
  }

  /**
  * Set numeric field CI1MATDT1 using a BigDecimal value.
  */
  public void setCI1MATDT1(BigDecimal newvalue)
  {
    fieldCI1MATDT1.setBigDecimal(newvalue);
  }

  /**
  * Return value of numeric field CI1MATDT1 as a BigDecimal.
  */
  public BigDecimal getBigDecimalCI1MATDT1()
  {
    return fieldCI1MATDT1.getBigDecimal();
  }

  /**
  * Set field CI1MATDT2 using a String value.
  */
  public void setCI1MATDT2(String newvalue)
  {
    fieldCI1MATDT2.setString(newvalue);
  }

  /**
  * Get value of field CI1MATDT2 as a String.
  */
  public String getCI1MATDT2()
  {
    return fieldCI1MATDT2.getString();
  }

  /**
  * Set numeric field CI1MATDT2 using a BigDecimal value.
  */
  public void setCI1MATDT2(BigDecimal newvalue)
  {
    fieldCI1MATDT2.setBigDecimal(newvalue);
  }

  /**
  * Return value of numeric field CI1MATDT2 as a BigDecimal.
  */
  public BigDecimal getBigDecimalCI1MATDT2()
  {
    return fieldCI1MATDT2.getBigDecimal();
  }

  /**
  * Set field CI1MATDT3 using a String value.
  */
  public void setCI1MATDT3(String newvalue)
  {
    fieldCI1MATDT3.setString(newvalue);
  }

  /**
  * Get value of field CI1MATDT3 as a String.
  */
  public String getCI1MATDT3()
  {
    return fieldCI1MATDT3.getString();
  }

  /**
  * Set numeric field CI1MATDT3 using a BigDecimal value.
  */
  public void setCI1MATDT3(BigDecimal newvalue)
  {
    fieldCI1MATDT3.setBigDecimal(newvalue);
  }

  /**
  * Return value of numeric field CI1MATDT3 as a BigDecimal.
  */
  public BigDecimal getBigDecimalCI1MATDT3()
  {
    return fieldCI1MATDT3.getBigDecimal();
  }

  /**
  * Set field CI1OPNDT1 using a String value.
  */
  public void setCI1OPNDT1(String newvalue)
  {
    fieldCI1OPNDT1.setString(newvalue);
  }

  /**
  * Get value of field CI1OPNDT1 as a String.
  */
  public String getCI1OPNDT1()
  {
    return fieldCI1OPNDT1.getString();
  }

  /**
  * Set numeric field CI1OPNDT1 using a BigDecimal value.
  */
  public void setCI1OPNDT1(BigDecimal newvalue)
  {
    fieldCI1OPNDT1.setBigDecimal(newvalue);
  }

  /**
  * Return value of numeric field CI1OPNDT1 as a BigDecimal.
  */
  public BigDecimal getBigDecimalCI1OPNDT1()
  {
    return fieldCI1OPNDT1.getBigDecimal();
  }

  /**
  * Set field CI1OPNDT2 using a String value.
  */
  public void setCI1OPNDT2(String newvalue)
  {
    fieldCI1OPNDT2.setString(newvalue);
  }

  /**
  * Get value of field CI1OPNDT2 as a String.
  */
  public String getCI1OPNDT2()
  {
    return fieldCI1OPNDT2.getString();
  }

  /**
  * Set numeric field CI1OPNDT2 using a BigDecimal value.
  */
  public void setCI1OPNDT2(BigDecimal newvalue)
  {
    fieldCI1OPNDT2.setBigDecimal(newvalue);
  }

  /**
  * Return value of numeric field CI1OPNDT2 as a BigDecimal.
  */
  public BigDecimal getBigDecimalCI1OPNDT2()
  {
    return fieldCI1OPNDT2.getBigDecimal();
  }

  /**
  * Set field CI1OPNDT3 using a String value.
  */
  public void setCI1OPNDT3(String newvalue)
  {
    fieldCI1OPNDT3.setString(newvalue);
  }

  /**
  * Get value of field CI1OPNDT3 as a String.
  */
  public String getCI1OPNDT3()
  {
    return fieldCI1OPNDT3.getString();
  }

  /**
  * Set numeric field CI1OPNDT3 using a BigDecimal value.
  */
  public void setCI1OPNDT3(BigDecimal newvalue)
  {
    fieldCI1OPNDT3.setBigDecimal(newvalue);
  }

  /**
  * Return value of numeric field CI1OPNDT3 as a BigDecimal.
  */
  public BigDecimal getBigDecimalCI1OPNDT3()
  {
    return fieldCI1OPNDT3.getBigDecimal();
  }

  /**
  * Set field CI1INIDT1 using a String value.
  */
  public void setCI1INIDT1(String newvalue)
  {
    fieldCI1INIDT1.setString(newvalue);
  }

  /**
  * Get value of field CI1INIDT1 as a String.
  */
  public String getCI1INIDT1()
  {
    return fieldCI1INIDT1.getString();
  }

  /**
  * Set numeric field CI1INIDT1 using a BigDecimal value.
  */
  public void setCI1INIDT1(BigDecimal newvalue)
  {
    fieldCI1INIDT1.setBigDecimal(newvalue);
  }

  /**
  * Return value of numeric field CI1INIDT1 as a BigDecimal.
  */
  public BigDecimal getBigDecimalCI1INIDT1()
  {
    return fieldCI1INIDT1.getBigDecimal();
  }

  /**
  * Set field CI1INIDT2 using a String value.
  */
  public void setCI1INIDT2(String newvalue)
  {
    fieldCI1INIDT2.setString(newvalue);
  }

  /**
  * Get value of field CI1INIDT2 as a String.
  */
  public String getCI1INIDT2()
  {
    return fieldCI1INIDT2.getString();
  }

  /**
  * Set numeric field CI1INIDT2 using a BigDecimal value.
  */
  public void setCI1INIDT2(BigDecimal newvalue)
  {
    fieldCI1INIDT2.setBigDecimal(newvalue);
  }

  /**
  * Return value of numeric field CI1INIDT2 as a BigDecimal.
  */
  public BigDecimal getBigDecimalCI1INIDT2()
  {
    return fieldCI1INIDT2.getBigDecimal();
  }

  /**
  * Set field CI1INIDT3 using a String value.
  */
  public void setCI1INIDT3(String newvalue)
  {
    fieldCI1INIDT3.setString(newvalue);
  }

  /**
  * Get value of field CI1INIDT3 as a String.
  */
  public String getCI1INIDT3()
  {
    return fieldCI1INIDT3.getString();
  }

  /**
  * Set numeric field CI1INIDT3 using a BigDecimal value.
  */
  public void setCI1INIDT3(BigDecimal newvalue)
  {
    fieldCI1INIDT3.setBigDecimal(newvalue);
  }

  /**
  * Return value of numeric field CI1INIDT3 as a BigDecimal.
  */
  public BigDecimal getBigDecimalCI1INIDT3()
  {
    return fieldCI1INIDT3.getBigDecimal();
  }

  /**
  * Set field CI1PRODUC using a String value.
  */
  public void setCI1PRODUC(String newvalue)
  {
    fieldCI1PRODUC.setString(newvalue);
  }

  /**
  * Get value of field CI1PRODUC as a String.
  */
  public String getCI1PRODUC()
  {
    return fieldCI1PRODUC.getString();
  }

  /**
  * Set field CI1OFCCDE using a String value.
  */
  public void setCI1OFCCDE(String newvalue)
  {
    fieldCI1OFCCDE.setString(newvalue);
  }

  /**
  * Get value of field CI1OFCCDE as a String.
  */
  public String getCI1OFCCDE()
  {
    return fieldCI1OFCCDE.getString();
  }

  /**
  * Set field CI1OFCNME using a String value.
  */
  public void setCI1OFCNME(String newvalue)
  {
    fieldCI1OFCNME.setString(newvalue);
  }

  /**
  * Get value of field CI1OFCNME as a String.
  */
  public String getCI1OFCNME()
  {
    return fieldCI1OFCNME.getString();
  }

  /**
  * Set field CI1OLDRSP using a String value.
  */
  public void setCI1OLDRSP(String newvalue)
  {
    fieldCI1OLDRSP.setString(newvalue);
  }

  /**
  * Get value of field CI1OLDRSP as a String.
  */
  public String getCI1OLDRSP()
  {
    return fieldCI1OLDRSP.getString();
  }

}
