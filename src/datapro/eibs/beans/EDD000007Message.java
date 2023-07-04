package datapro.eibs.beans;


import java.io.*;
import java.math.*;

import datapro.eibs.sockets.*;

/**
* Class generated by AS/400 CRTCLASS command from EDD000007 physical file definition.
* 
* File level identifier is 1000616134641.
* Record format level identifier is 43BBAB55F4F3B.
*/

public class EDD000007Message extends MessageRecord
{
  final int FIELDCOUNT = 25;
  private CharacterField fieldH07USERID = null;
  private CharacterField fieldH07PROGRM = null;
  private CharacterField fieldH07TIMSYS = null;
  private CharacterField fieldH07SCRCOD = null;
  private CharacterField fieldH07OPECOD = null;
  private CharacterField fieldH07FLGMAS = null;
  private CharacterField fieldH07FLGWK1 = null;
  private CharacterField fieldH07FLGWK2 = null;
  private CharacterField fieldH07FLGWK3 = null;
  private DecimalField fieldE07ACMACC = null;
  private CharacterField fieldE07ACMPRO = null;
  private DecimalField fieldE07ACMCUN = null;
  private CharacterField fieldE07CUSNA1 = null;
  private DecimalField fieldE07INVRAC = null;
  private DecimalField fieldE07INVSQN = null;
  private DecimalField fieldE07INVMUL = null;
  private DecimalField fieldE07INVMIN = null;
  private DecimalField fieldE07INVMAX = null;
  private DecimalField fieldE07INVBLS = null;
  private DecimalField fieldE07INVID1 = null;
  private DecimalField fieldE07INVID2 = null;
  private DecimalField fieldE07INVID3 = null;
  private DecimalField fieldE07INVMD1 = null;
  private DecimalField fieldE07INVMD2 = null;
  private DecimalField fieldE07INVMD3 = null;

  /**
  * Constructor for EDD000007Message.
  */
  public EDD000007Message()
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
	recordsize = 207;
	fileid = "1000616134641";
	recordid = "43BBAB55F4F3B";
	message = new byte[getByteLength()];
	formatname = "EDD000007";
	fieldnames = new String[FIELDCOUNT];
	tagnames = new String[FIELDCOUNT];
	fields = new MessageField[FIELDCOUNT];

	fieldnames[0] = "H07USERID";
	tagnames[0] = "H07USERID";
	fields[0] = fieldH07USERID =
	new CharacterField(message, HEADERSIZE + 0, 10, "H07USERID");
	fieldnames[1] = "H07PROGRM";
	tagnames[1] = "H07PROGRM";
	fields[1] = fieldH07PROGRM =
	new CharacterField(message, HEADERSIZE + 10, 10, "H07PROGRM");
	fieldnames[2] = "H07TIMSYS";
	tagnames[2] = "H07TIMSYS";
	fields[2] = fieldH07TIMSYS =
	new CharacterField(message, HEADERSIZE + 20, 12, "H07TIMSYS");
	fieldnames[3] = "H07SCRCOD";
	tagnames[3] = "H07SCRCOD";
	fields[3] = fieldH07SCRCOD =
	new CharacterField(message, HEADERSIZE + 32, 2, "H07SCRCOD");
	fieldnames[4] = "H07OPECOD";
	tagnames[4] = "H07OPECOD";
	fields[4] = fieldH07OPECOD =
	new CharacterField(message, HEADERSIZE + 34, 4, "H07OPECOD");
	fieldnames[5] = "H07FLGMAS";
	tagnames[5] = "H07FLGMAS";
	fields[5] = fieldH07FLGMAS =
	new CharacterField(message, HEADERSIZE + 38, 1, "H07FLGMAS");
	fieldnames[6] = "H07FLGWK1";
	tagnames[6] = "H07FLGWK1";
	fields[6] = fieldH07FLGWK1 =
	new CharacterField(message, HEADERSIZE + 39, 1, "H07FLGWK1");
	fieldnames[7] = "H07FLGWK2";
	tagnames[7] = "H07FLGWK2";
	fields[7] = fieldH07FLGWK2 =
	new CharacterField(message, HEADERSIZE + 40, 1, "H07FLGWK2");
	fieldnames[8] = "H07FLGWK3";
	tagnames[8] = "H07FLGWK3";
	fields[8] = fieldH07FLGWK3 =
	new CharacterField(message, HEADERSIZE + 41, 1, "H07FLGWK3");
	fieldnames[9] = "E07ACMACC";
	tagnames[9] = "E07ACMACC";
	fields[9] = fieldE07ACMACC =
	new DecimalField(message, HEADERSIZE + 42, 13, 0, "E07ACMACC");
	fieldnames[10] = "E07ACMPRO";
	tagnames[10] = "E07ACMPRO";
	fields[10] = fieldE07ACMPRO =
	new CharacterField(message, HEADERSIZE + 55, 4, "E07ACMPRO");
	fieldnames[11] = "E07ACMCUN";
	tagnames[11] = "E07ACMCUN";
	fields[11] = fieldE07ACMCUN =
	new DecimalField(message, HEADERSIZE + 59, 10, 0, "E07ACMCUN");
	fieldnames[12] = "E07CUSNA1";
	tagnames[12] = "E07CUSNA1";
	fields[12] = fieldE07CUSNA1 =
	new CharacterField(message, HEADERSIZE + 69, 45, "E07CUSNA1");
	fieldnames[13] = "E07INVRAC";
	tagnames[13] = "E07INVRAC";
	fields[13] = fieldE07INVRAC =
	new DecimalField(message, HEADERSIZE + 114, 13, 0, "E07INVRAC");
	fieldnames[14] = "E07INVSQN";
	tagnames[14] = "E07INVSQN";
	fields[14] = fieldE07INVSQN =
	new DecimalField(message, HEADERSIZE + 127, 3, 0, "E07INVSQN");
	fieldnames[15] = "E07INVMUL";
	tagnames[15] = "E07INVMUL";
	fields[15] = fieldE07INVMUL =
	new DecimalField(message, HEADERSIZE + 130, 14, 0, "E07INVMUL");
	fieldnames[16] = "E07INVMIN";
	tagnames[16] = "E07INVMIN";
	fields[16] = fieldE07INVMIN =
	new DecimalField(message, HEADERSIZE + 144, 15, 2, "E07INVMIN");
	fieldnames[17] = "E07INVMAX";
	tagnames[17] = "E07INVMAX";
	fields[17] = fieldE07INVMAX =
	new DecimalField(message, HEADERSIZE + 159, 15, 2, "E07INVMAX");
	fieldnames[18] = "E07INVBLS";
	tagnames[18] = "E07INVBLS";
	fields[18] = fieldE07INVBLS =
	new DecimalField(message, HEADERSIZE + 174, 15, 2, "E07INVBLS");
	fieldnames[19] = "E07INVID1";
	tagnames[19] = "E07INVID1";
	fields[19] = fieldE07INVID1 =
	new DecimalField(message, HEADERSIZE + 189, 3, 0, "E07INVID1");
	fieldnames[20] = "E07INVID2";
	tagnames[20] = "E07INVID2";
	fields[20] = fieldE07INVID2 =
	new DecimalField(message, HEADERSIZE + 192, 3, 0, "E07INVID2");
	fieldnames[21] = "E07INVID3";
	tagnames[21] = "E07INVID3";
	fields[21] = fieldE07INVID3 =
	new DecimalField(message, HEADERSIZE + 195, 3, 0, "E07INVID3");
	fieldnames[22] = "E07INVMD1";
	tagnames[22] = "E07INVMD1";
	fields[22] = fieldE07INVMD1 =
	new DecimalField(message, HEADERSIZE + 198, 3, 0, "E07INVMD1");
	fieldnames[23] = "E07INVMD2";
	tagnames[23] = "E07INVMD2";
	fields[23] = fieldE07INVMD2 =
	new DecimalField(message, HEADERSIZE + 201, 3, 0, "E07INVMD2");
	fieldnames[24] = "E07INVMD3";
	tagnames[24] = "E07INVMD3";
	fields[24] = fieldE07INVMD3 =
	new DecimalField(message, HEADERSIZE + 204, 3, 0, "E07INVMD3");
  }    
  /**
  * Return value of numeric field E07ACMACC as a BigDecimal.
  */
  public BigDecimal getBigDecimalE07ACMACC()
  {
	return fieldE07ACMACC.getBigDecimal();
  }    
  /**
  * Return value of numeric field E07ACMCUN as a BigDecimal.
  */
  public BigDecimal getBigDecimalE07ACMCUN()
  {
	return fieldE07ACMCUN.getBigDecimal();
  }    
  /**
  * Return value of numeric field E07INVBLS as a BigDecimal.
  */
  public BigDecimal getBigDecimalE07INVBLS()
  {
	return fieldE07INVBLS.getBigDecimal();
  }    
  /**
  * Return value of numeric field E07INVID1 as a BigDecimal.
  */
  public BigDecimal getBigDecimalE07INVID1()
  {
	return fieldE07INVID1.getBigDecimal();
  }    
  /**
  * Return value of numeric field E07INVID2 as a BigDecimal.
  */
  public BigDecimal getBigDecimalE07INVID2()
  {
	return fieldE07INVID2.getBigDecimal();
  }    
  /**
  * Return value of numeric field E07INVID3 as a BigDecimal.
  */
  public BigDecimal getBigDecimalE07INVID3()
  {
	return fieldE07INVID3.getBigDecimal();
  }    
  /**
  * Return value of numeric field E07INVMAX as a BigDecimal.
  */
  public BigDecimal getBigDecimalE07INVMAX()
  {
	return fieldE07INVMAX.getBigDecimal();
  }    
  /**
  * Return value of numeric field E07INVMD1 as a BigDecimal.
  */
  public BigDecimal getBigDecimalE07INVMD1()
  {
	return fieldE07INVMD1.getBigDecimal();
  }    
  /**
  * Return value of numeric field E07INVMD2 as a BigDecimal.
  */
  public BigDecimal getBigDecimalE07INVMD2()
  {
	return fieldE07INVMD2.getBigDecimal();
  }    
  /**
  * Return value of numeric field E07INVMD3 as a BigDecimal.
  */
  public BigDecimal getBigDecimalE07INVMD3()
  {
	return fieldE07INVMD3.getBigDecimal();
  }    
  /**
  * Return value of numeric field E07INVMIN as a BigDecimal.
  */
  public BigDecimal getBigDecimalE07INVMIN()
  {
	return fieldE07INVMIN.getBigDecimal();
  }    
  /**
  * Return value of numeric field E07INVMUL as a BigDecimal.
  */
  public BigDecimal getBigDecimalE07INVMUL()
  {
	return fieldE07INVMUL.getBigDecimal();
  }    
  /**
  * Return value of numeric field E07INVRAC as a BigDecimal.
  */
  public BigDecimal getBigDecimalE07INVRAC()
  {
	return fieldE07INVRAC.getBigDecimal();
  }    
  /**
  * Return value of numeric field E07INVSQN as a BigDecimal.
  */
  public BigDecimal getBigDecimalE07INVSQN()
  {
	return fieldE07INVSQN.getBigDecimal();
  }    
  /**
  * Get value of field E07ACMACC as a String.
  */
  public String getE07ACMACC()
  {
	return fieldE07ACMACC.getString();
  }    
  /**
  * Get value of field E07ACMCUN as a String.
  */
  public String getE07ACMCUN()
  {
	return fieldE07ACMCUN.getString();
  }    
  /**
  * Get value of field E07ACMPRO as a String.
  */
  public String getE07ACMPRO()
  {
	return fieldE07ACMPRO.getString();
  }    
  /**
  * Get value of field E07CUSNA1 as a String.
  */
  public String getE07CUSNA1()
  {
	return fieldE07CUSNA1.getString();
  }    
  /**
  * Get value of field E07INVBLS as a String.
  */
  public String getE07INVBLS()
  {
	return fieldE07INVBLS.getString();
  }    
  /**
  * Get value of field E07INVID1 as a String.
  */
  public String getE07INVID1()
  {
	return fieldE07INVID1.getString();
  }    
  /**
  * Get value of field E07INVID2 as a String.
  */
  public String getE07INVID2()
  {
	return fieldE07INVID2.getString();
  }    
  /**
  * Get value of field E07INVID3 as a String.
  */
  public String getE07INVID3()
  {
	return fieldE07INVID3.getString();
  }    
  /**
  * Get value of field E07INVMAX as a String.
  */
  public String getE07INVMAX()
  {
	return fieldE07INVMAX.getString();
  }    
  /**
  * Get value of field E07INVMD1 as a String.
  */
  public String getE07INVMD1()
  {
	return fieldE07INVMD1.getString();
  }    
  /**
  * Get value of field E07INVMD2 as a String.
  */
  public String getE07INVMD2()
  {
	return fieldE07INVMD2.getString();
  }    
  /**
  * Get value of field E07INVMD3 as a String.
  */
  public String getE07INVMD3()
  {
	return fieldE07INVMD3.getString();
  }    
  /**
  * Get value of field E07INVMIN as a String.
  */
  public String getE07INVMIN()
  {
	return fieldE07INVMIN.getString();
  }    
  /**
  * Get value of field E07INVMUL as a String.
  */
  public String getE07INVMUL()
  {
	return fieldE07INVMUL.getString();
  }    
  /**
  * Get value of field E07INVRAC as a String.
  */
  public String getE07INVRAC()
  {
	return fieldE07INVRAC.getString();
  }    
  /**
  * Get value of field E07INVSQN as a String.
  */
  public String getE07INVSQN()
  {
	return fieldE07INVSQN.getString();
  }    
  /**
  * Get value of field H07FLGMAS as a String.
  */
  public String getH07FLGMAS()
  {
	return fieldH07FLGMAS.getString();
  }    
  /**
  * Get value of field H07FLGWK1 as a String.
  */
  public String getH07FLGWK1()
  {
	return fieldH07FLGWK1.getString();
  }    
  /**
  * Get value of field H07FLGWK2 as a String.
  */
  public String getH07FLGWK2()
  {
	return fieldH07FLGWK2.getString();
  }    
  /**
  * Get value of field H07FLGWK3 as a String.
  */
  public String getH07FLGWK3()
  {
	return fieldH07FLGWK3.getString();
  }    
  /**
  * Get value of field H07OPECOD as a String.
  */
  public String getH07OPECOD()
  {
	return fieldH07OPECOD.getString();
  }    
  /**
  * Get value of field H07PROGRM as a String.
  */
  public String getH07PROGRM()
  {
	return fieldH07PROGRM.getString();
  }    
  /**
  * Get value of field H07SCRCOD as a String.
  */
  public String getH07SCRCOD()
  {
	return fieldH07SCRCOD.getString();
  }    
  /**
  * Get value of field H07TIMSYS as a String.
  */
  public String getH07TIMSYS()
  {
	return fieldH07TIMSYS.getString();
  }    
  /**
  * Get value of field H07USERID as a String.
  */
  public String getH07USERID()
  {
	return fieldH07USERID.getString();
  }    
  /**
  * Set field E07ACMACC using a String value.
  */
  public void setE07ACMACC(String newvalue)
  {
	fieldE07ACMACC.setString(newvalue);
  }    
  /**
  * Set numeric field E07ACMACC using a BigDecimal value.
  */
  public void setE07ACMACC(BigDecimal newvalue)
  {
	fieldE07ACMACC.setBigDecimal(newvalue);
  }    
  /**
  * Set field E07ACMCUN using a String value.
  */
  public void setE07ACMCUN(String newvalue)
  {
	fieldE07ACMCUN.setString(newvalue);
  }    
  /**
  * Set numeric field E07ACMCUN using a BigDecimal value.
  */
  public void setE07ACMCUN(BigDecimal newvalue)
  {
	fieldE07ACMCUN.setBigDecimal(newvalue);
  }    
  /**
  * Set field E07ACMPRO using a String value.
  */
  public void setE07ACMPRO(String newvalue)
  {
	fieldE07ACMPRO.setString(newvalue);
  }    
  /**
  * Set field E07CUSNA1 using a String value.
  */
  public void setE07CUSNA1(String newvalue)
  {
	fieldE07CUSNA1.setString(newvalue);
  }    
  /**
  * Set field E07INVBLS using a String value.
  */
  public void setE07INVBLS(String newvalue)
  {
	fieldE07INVBLS.setString(newvalue);
  }    
  /**
  * Set numeric field E07INVBLS using a BigDecimal value.
  */
  public void setE07INVBLS(BigDecimal newvalue)
  {
	fieldE07INVBLS.setBigDecimal(newvalue);
  }    
  /**
  * Set field E07INVID1 using a String value.
  */
  public void setE07INVID1(String newvalue)
  {
	fieldE07INVID1.setString(newvalue);
  }    
  /**
  * Set numeric field E07INVID1 using a BigDecimal value.
  */
  public void setE07INVID1(BigDecimal newvalue)
  {
	fieldE07INVID1.setBigDecimal(newvalue);
  }    
  /**
  * Set field E07INVID2 using a String value.
  */
  public void setE07INVID2(String newvalue)
  {
	fieldE07INVID2.setString(newvalue);
  }    
  /**
  * Set numeric field E07INVID2 using a BigDecimal value.
  */
  public void setE07INVID2(BigDecimal newvalue)
  {
	fieldE07INVID2.setBigDecimal(newvalue);
  }    
  /**
  * Set field E07INVID3 using a String value.
  */
  public void setE07INVID3(String newvalue)
  {
	fieldE07INVID3.setString(newvalue);
  }    
  /**
  * Set numeric field E07INVID3 using a BigDecimal value.
  */
  public void setE07INVID3(BigDecimal newvalue)
  {
	fieldE07INVID3.setBigDecimal(newvalue);
  }    
  /**
  * Set field E07INVMAX using a String value.
  */
  public void setE07INVMAX(String newvalue)
  {
	fieldE07INVMAX.setString(newvalue);
  }    
  /**
  * Set numeric field E07INVMAX using a BigDecimal value.
  */
  public void setE07INVMAX(BigDecimal newvalue)
  {
	fieldE07INVMAX.setBigDecimal(newvalue);
  }    
  /**
  * Set field E07INVMD1 using a String value.
  */
  public void setE07INVMD1(String newvalue)
  {
	fieldE07INVMD1.setString(newvalue);
  }    
  /**
  * Set numeric field E07INVMD1 using a BigDecimal value.
  */
  public void setE07INVMD1(BigDecimal newvalue)
  {
	fieldE07INVMD1.setBigDecimal(newvalue);
  }    
  /**
  * Set field E07INVMD2 using a String value.
  */
  public void setE07INVMD2(String newvalue)
  {
	fieldE07INVMD2.setString(newvalue);
  }    
  /**
  * Set numeric field E07INVMD2 using a BigDecimal value.
  */
  public void setE07INVMD2(BigDecimal newvalue)
  {
	fieldE07INVMD2.setBigDecimal(newvalue);
  }    
  /**
  * Set field E07INVMD3 using a String value.
  */
  public void setE07INVMD3(String newvalue)
  {
	fieldE07INVMD3.setString(newvalue);
  }    
  /**
  * Set numeric field E07INVMD3 using a BigDecimal value.
  */
  public void setE07INVMD3(BigDecimal newvalue)
  {
	fieldE07INVMD3.setBigDecimal(newvalue);
  }    
  /**
  * Set field E07INVMIN using a String value.
  */
  public void setE07INVMIN(String newvalue)
  {
	fieldE07INVMIN.setString(newvalue);
  }    
  /**
  * Set numeric field E07INVMIN using a BigDecimal value.
  */
  public void setE07INVMIN(BigDecimal newvalue)
  {
	fieldE07INVMIN.setBigDecimal(newvalue);
  }    
  /**
  * Set field E07INVMUL using a String value.
  */
  public void setE07INVMUL(String newvalue)
  {
	fieldE07INVMUL.setString(newvalue);
  }    
  /**
  * Set numeric field E07INVMUL using a BigDecimal value.
  */
  public void setE07INVMUL(BigDecimal newvalue)
  {
	fieldE07INVMUL.setBigDecimal(newvalue);
  }    
  /**
  * Set field E07INVRAC using a String value.
  */
  public void setE07INVRAC(String newvalue)
  {
	fieldE07INVRAC.setString(newvalue);
  }    
  /**
  * Set numeric field E07INVRAC using a BigDecimal value.
  */
  public void setE07INVRAC(BigDecimal newvalue)
  {
	fieldE07INVRAC.setBigDecimal(newvalue);
  }    
  /**
  * Set field E07INVSQN using a String value.
  */
  public void setE07INVSQN(String newvalue)
  {
	fieldE07INVSQN.setString(newvalue);
  }    
  /**
  * Set numeric field E07INVSQN using a BigDecimal value.
  */
  public void setE07INVSQN(BigDecimal newvalue)
  {
	fieldE07INVSQN.setBigDecimal(newvalue);
  }    
  /**
  * Set field H07FLGMAS using a String value.
  */
  public void setH07FLGMAS(String newvalue)
  {
	fieldH07FLGMAS.setString(newvalue);
  }    
  /**
  * Set field H07FLGWK1 using a String value.
  */
  public void setH07FLGWK1(String newvalue)
  {
	fieldH07FLGWK1.setString(newvalue);
  }    
  /**
  * Set field H07FLGWK2 using a String value.
  */
  public void setH07FLGWK2(String newvalue)
  {
	fieldH07FLGWK2.setString(newvalue);
  }    
  /**
  * Set field H07FLGWK3 using a String value.
  */
  public void setH07FLGWK3(String newvalue)
  {
	fieldH07FLGWK3.setString(newvalue);
  }    
  /**
  * Set field H07OPECOD using a String value.
  */
  public void setH07OPECOD(String newvalue)
  {
	fieldH07OPECOD.setString(newvalue);
  }    
  /**
  * Set field H07PROGRM using a String value.
  */
  public void setH07PROGRM(String newvalue)
  {
	fieldH07PROGRM.setString(newvalue);
  }    
  /**
  * Set field H07SCRCOD using a String value.
  */
  public void setH07SCRCOD(String newvalue)
  {
	fieldH07SCRCOD.setString(newvalue);
  }    
  /**
  * Set field H07TIMSYS using a String value.
  */
  public void setH07TIMSYS(String newvalue)
  {
	fieldH07TIMSYS.setString(newvalue);
  }    
  /**
  * Set field H07USERID using a String value.
  */
  public void setH07USERID(String newvalue)
  {
	fieldH07USERID.setString(newvalue);
  }    
}