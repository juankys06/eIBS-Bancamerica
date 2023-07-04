package datapro.eibs.beans;

import datapro.eibs.sockets.*;
import java.io.*;
import java.math.*;

/**
* Class generated by AS/400 CRTCLASS command from EDD043002 physical file definition.
* 
* File level identifier is 1050420173552.
* Record format level identifier is 4AB3FEE0A757A.
*/

public class EDD043002Message extends MessageRecord
{
  final int FIELDCOUNT = 28;
  private CharacterField fieldH02USERID = null;
  private CharacterField fieldH02PROGRM = null;
  private CharacterField fieldH02TIMSYS = null;
  private CharacterField fieldH02SCRCOD = null;
  private CharacterField fieldH02OPECOD = null;
  private CharacterField fieldH02FLGMAS = null;
  private CharacterField fieldH02FLGWK1 = null;
  private CharacterField fieldH02FLGWK2 = null;
  private CharacterField fieldH02FLGWK3 = null;
  private DecimalField fieldE02ACMACC = null;
  private CharacterField fieldE02ACMPRO = null;
  private DecimalField fieldE02ACMCUN = null;
  private CharacterField fieldE02CUSNA1 = null;
  private DecimalField fieldE02INVRAC = null;
  private DecimalField fieldE02INVSQN = null;
  private DecimalField fieldE02INVMUL = null;
  private DecimalField fieldE02INVMIN = null;
  private DecimalField fieldE02INVMAX = null;
  private DecimalField fieldE02INVBLS = null;
  private DecimalField fieldE02INVSD1 = null;
  private DecimalField fieldE02INVSD2 = null;
  private DecimalField fieldE02INVSD3 = null;
  private DecimalField fieldE02INVMD1 = null;
  private DecimalField fieldE02INVMD2 = null;
  private DecimalField fieldE02INVMD3 = null;
  private DecimalField fieldE02INVBLM = null;
  private CharacterField fieldE02INVSTS = null;
  private CharacterField fieldD02ACMPRO = null;

  /**
  * Constructor for EDD043002Message.
  */
  public EDD043002Message()
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
	recordsize = 269;
	fileid = "1050420173552";
	recordid = "4AB3FEE0A757A";
	message = new byte[getByteLength()];
	formatname = "EDD043002";
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
	fieldnames[9] = "E02ACMACC";
	tagnames[9] = "E02ACMACC";
	fields[9] = fieldE02ACMACC =
	new DecimalField(message, HEADERSIZE + 42, 13, 0, "E02ACMACC");
	fieldnames[10] = "E02ACMPRO";
	tagnames[10] = "E02ACMPRO";
	fields[10] = fieldE02ACMPRO =
	new CharacterField(message, HEADERSIZE + 55, 4, "E02ACMPRO");
	fieldnames[11] = "E02ACMCUN";
	tagnames[11] = "E02ACMCUN";
	fields[11] = fieldE02ACMCUN =
	new DecimalField(message, HEADERSIZE + 59, 10, 0, "E02ACMCUN");
	fieldnames[12] = "E02CUSNA1";
	tagnames[12] = "E02CUSNA1";
	fields[12] = fieldE02CUSNA1 =
	new CharacterField(message, HEADERSIZE + 69, 45, "E02CUSNA1");
	fieldnames[13] = "E02INVRAC";
	tagnames[13] = "E02INVRAC";
	fields[13] = fieldE02INVRAC =
	new DecimalField(message, HEADERSIZE + 114, 13, 0, "E02INVRAC");
	fieldnames[14] = "E02INVSQN";
	tagnames[14] = "E02INVSQN";
	fields[14] = fieldE02INVSQN =
	new DecimalField(message, HEADERSIZE + 127, 3, 0, "E02INVSQN");
	fieldnames[15] = "E02INVMUL";
	tagnames[15] = "E02INVMUL";
	fields[15] = fieldE02INVMUL =
	new DecimalField(message, HEADERSIZE + 130, 17, 2, "E02INVMUL");
	fieldnames[16] = "E02INVMIN";
	tagnames[16] = "E02INVMIN";
	fields[16] = fieldE02INVMIN =
	new DecimalField(message, HEADERSIZE + 147, 17, 2, "E02INVMIN");
	fieldnames[17] = "E02INVMAX";
	tagnames[17] = "E02INVMAX";
	fields[17] = fieldE02INVMAX =
	new DecimalField(message, HEADERSIZE + 164, 17, 2, "E02INVMAX");
	fieldnames[18] = "E02INVBLS";
	tagnames[18] = "E02INVBLS";
	fields[18] = fieldE02INVBLS =
	new DecimalField(message, HEADERSIZE + 181, 17, 2, "E02INVBLS");
	fieldnames[19] = "E02INVSD1";
	tagnames[19] = "E02INVSD1";
	fields[19] = fieldE02INVSD1 =
	new DecimalField(message, HEADERSIZE + 198, 3, 0, "E02INVSD1");
	fieldnames[20] = "E02INVSD2";
	tagnames[20] = "E02INVSD2";
	fields[20] = fieldE02INVSD2 =
	new DecimalField(message, HEADERSIZE + 201, 3, 0, "E02INVSD2");
	fieldnames[21] = "E02INVSD3";
	tagnames[21] = "E02INVSD3";
	fields[21] = fieldE02INVSD3 =
	new DecimalField(message, HEADERSIZE + 204, 3, 0, "E02INVSD3");
	fieldnames[22] = "E02INVMD1";
	tagnames[22] = "E02INVMD1";
	fields[22] = fieldE02INVMD1 =
	new DecimalField(message, HEADERSIZE + 207, 3, 0, "E02INVMD1");
	fieldnames[23] = "E02INVMD2";
	tagnames[23] = "E02INVMD2";
	fields[23] = fieldE02INVMD2 =
	new DecimalField(message, HEADERSIZE + 210, 3, 0, "E02INVMD2");
	fieldnames[24] = "E02INVMD3";
	tagnames[24] = "E02INVMD3";
	fields[24] = fieldE02INVMD3 =
	new DecimalField(message, HEADERSIZE + 213, 3, 0, "E02INVMD3");
	fieldnames[25] = "E02INVBLM";
	tagnames[25] = "E02INVBLM";
	fields[25] = fieldE02INVBLM =
	new DecimalField(message, HEADERSIZE + 216, 17, 2, "E02INVBLM");
	fieldnames[26] = "E02INVSTS";
	tagnames[26] = "E02INVSTS";
	fields[26] = fieldE02INVSTS =
	new CharacterField(message, HEADERSIZE + 233, 1, "E02INVSTS");
	fieldnames[27] = "D02ACMPRO";
	tagnames[27] = "D02ACMPRO";
	fields[27] = fieldD02ACMPRO =
	new CharacterField(message, HEADERSIZE + 234, 35, "D02ACMPRO");
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
  * Set field E02ACMACC using a String value.
  */
  public void setE02ACMACC(String newvalue)
  {
	fieldE02ACMACC.setString(newvalue);
  }

  /**
  * Get value of field E02ACMACC as a String.
  */
  public String getE02ACMACC()
  {
	return fieldE02ACMACC.getString();
  }

  /**
  * Set numeric field E02ACMACC using a BigDecimal value.
  */
  public void setE02ACMACC(BigDecimal newvalue)
  {
	fieldE02ACMACC.setBigDecimal(newvalue);
  }

  /**
  * Return value of numeric field E02ACMACC as a BigDecimal.
  */
  public BigDecimal getBigDecimalE02ACMACC()
  {
	return fieldE02ACMACC.getBigDecimal();
  }

  /**
  * Set field E02ACMPRO using a String value.
  */
  public void setE02ACMPRO(String newvalue)
  {
	fieldE02ACMPRO.setString(newvalue);
  }

  /**
  * Get value of field E02ACMPRO as a String.
  */
  public String getE02ACMPRO()
  {
	return fieldE02ACMPRO.getString();
  }

  /**
  * Set field E02ACMCUN using a String value.
  */
  public void setE02ACMCUN(String newvalue)
  {
	fieldE02ACMCUN.setString(newvalue);
  }

  /**
  * Get value of field E02ACMCUN as a String.
  */
  public String getE02ACMCUN()
  {
	return fieldE02ACMCUN.getString();
  }

  /**
  * Set numeric field E02ACMCUN using a BigDecimal value.
  */
  public void setE02ACMCUN(BigDecimal newvalue)
  {
	fieldE02ACMCUN.setBigDecimal(newvalue);
  }

  /**
  * Return value of numeric field E02ACMCUN as a BigDecimal.
  */
  public BigDecimal getBigDecimalE02ACMCUN()
  {
	return fieldE02ACMCUN.getBigDecimal();
  }

  /**
  * Set field E02CUSNA1 using a String value.
  */
  public void setE02CUSNA1(String newvalue)
  {
	fieldE02CUSNA1.setString(newvalue);
  }

  /**
  * Get value of field E02CUSNA1 as a String.
  */
  public String getE02CUSNA1()
  {
	return fieldE02CUSNA1.getString();
  }

  /**
  * Set field E02INVRAC using a String value.
  */
  public void setE02INVRAC(String newvalue)
  {
	fieldE02INVRAC.setString(newvalue);
  }

  /**
  * Get value of field E02INVRAC as a String.
  */
  public String getE02INVRAC()
  {
	return fieldE02INVRAC.getString();
  }

  /**
  * Set numeric field E02INVRAC using a BigDecimal value.
  */
  public void setE02INVRAC(BigDecimal newvalue)
  {
	fieldE02INVRAC.setBigDecimal(newvalue);
  }

  /**
  * Return value of numeric field E02INVRAC as a BigDecimal.
  */
  public BigDecimal getBigDecimalE02INVRAC()
  {
	return fieldE02INVRAC.getBigDecimal();
  }

  /**
  * Set field E02INVSQN using a String value.
  */
  public void setE02INVSQN(String newvalue)
  {
	fieldE02INVSQN.setString(newvalue);
  }

  /**
  * Get value of field E02INVSQN as a String.
  */
  public String getE02INVSQN()
  {
	return fieldE02INVSQN.getString();
  }

  /**
  * Set numeric field E02INVSQN using a BigDecimal value.
  */
  public void setE02INVSQN(BigDecimal newvalue)
  {
	fieldE02INVSQN.setBigDecimal(newvalue);
  }

  /**
  * Return value of numeric field E02INVSQN as a BigDecimal.
  */
  public BigDecimal getBigDecimalE02INVSQN()
  {
	return fieldE02INVSQN.getBigDecimal();
  }

  /**
  * Set field E02INVMUL using a String value.
  */
  public void setE02INVMUL(String newvalue)
  {
	fieldE02INVMUL.setString(newvalue);
  }

  /**
  * Get value of field E02INVMUL as a String.
  */
  public String getE02INVMUL()
  {
	return fieldE02INVMUL.getString();
  }

  /**
  * Set numeric field E02INVMUL using a BigDecimal value.
  */
  public void setE02INVMUL(BigDecimal newvalue)
  {
	fieldE02INVMUL.setBigDecimal(newvalue);
  }

  /**
  * Return value of numeric field E02INVMUL as a BigDecimal.
  */
  public BigDecimal getBigDecimalE02INVMUL()
  {
	return fieldE02INVMUL.getBigDecimal();
  }

  /**
  * Set field E02INVMIN using a String value.
  */
  public void setE02INVMIN(String newvalue)
  {
	fieldE02INVMIN.setString(newvalue);
  }

  /**
  * Get value of field E02INVMIN as a String.
  */
  public String getE02INVMIN()
  {
	return fieldE02INVMIN.getString();
  }

  /**
  * Set numeric field E02INVMIN using a BigDecimal value.
  */
  public void setE02INVMIN(BigDecimal newvalue)
  {
	fieldE02INVMIN.setBigDecimal(newvalue);
  }

  /**
  * Return value of numeric field E02INVMIN as a BigDecimal.
  */
  public BigDecimal getBigDecimalE02INVMIN()
  {
	return fieldE02INVMIN.getBigDecimal();
  }

  /**
  * Set field E02INVMAX using a String value.
  */
  public void setE02INVMAX(String newvalue)
  {
	fieldE02INVMAX.setString(newvalue);
  }

  /**
  * Get value of field E02INVMAX as a String.
  */
  public String getE02INVMAX()
  {
	return fieldE02INVMAX.getString();
  }

  /**
  * Set numeric field E02INVMAX using a BigDecimal value.
  */
  public void setE02INVMAX(BigDecimal newvalue)
  {
	fieldE02INVMAX.setBigDecimal(newvalue);
  }

  /**
  * Return value of numeric field E02INVMAX as a BigDecimal.
  */
  public BigDecimal getBigDecimalE02INVMAX()
  {
	return fieldE02INVMAX.getBigDecimal();
  }

  /**
  * Set field E02INVBLS using a String value.
  */
  public void setE02INVBLS(String newvalue)
  {
	fieldE02INVBLS.setString(newvalue);
  }

  /**
  * Get value of field E02INVBLS as a String.
  */
  public String getE02INVBLS()
  {
	return fieldE02INVBLS.getString();
  }

  /**
  * Set numeric field E02INVBLS using a BigDecimal value.
  */
  public void setE02INVBLS(BigDecimal newvalue)
  {
	fieldE02INVBLS.setBigDecimal(newvalue);
  }

  /**
  * Return value of numeric field E02INVBLS as a BigDecimal.
  */
  public BigDecimal getBigDecimalE02INVBLS()
  {
	return fieldE02INVBLS.getBigDecimal();
  }

  /**
  * Set field E02INVSD1 using a String value.
  */
  public void setE02INVSD1(String newvalue)
  {
	fieldE02INVSD1.setString(newvalue);
  }

  /**
  * Get value of field E02INVSD1 as a String.
  */
  public String getE02INVSD1()
  {
	return fieldE02INVSD1.getString();
  }

  /**
  * Set numeric field E02INVSD1 using a BigDecimal value.
  */
  public void setE02INVSD1(BigDecimal newvalue)
  {
	fieldE02INVSD1.setBigDecimal(newvalue);
  }

  /**
  * Return value of numeric field E02INVSD1 as a BigDecimal.
  */
  public BigDecimal getBigDecimalE02INVSD1()
  {
	return fieldE02INVSD1.getBigDecimal();
  }

  /**
  * Set field E02INVSD2 using a String value.
  */
  public void setE02INVSD2(String newvalue)
  {
	fieldE02INVSD2.setString(newvalue);
  }

  /**
  * Get value of field E02INVSD2 as a String.
  */
  public String getE02INVSD2()
  {
	return fieldE02INVSD2.getString();
  }

  /**
  * Set numeric field E02INVSD2 using a BigDecimal value.
  */
  public void setE02INVSD2(BigDecimal newvalue)
  {
	fieldE02INVSD2.setBigDecimal(newvalue);
  }

  /**
  * Return value of numeric field E02INVSD2 as a BigDecimal.
  */
  public BigDecimal getBigDecimalE02INVSD2()
  {
	return fieldE02INVSD2.getBigDecimal();
  }

  /**
  * Set field E02INVSD3 using a String value.
  */
  public void setE02INVSD3(String newvalue)
  {
	fieldE02INVSD3.setString(newvalue);
  }

  /**
  * Get value of field E02INVSD3 as a String.
  */
  public String getE02INVSD3()
  {
	return fieldE02INVSD3.getString();
  }

  /**
  * Set numeric field E02INVSD3 using a BigDecimal value.
  */
  public void setE02INVSD3(BigDecimal newvalue)
  {
	fieldE02INVSD3.setBigDecimal(newvalue);
  }

  /**
  * Return value of numeric field E02INVSD3 as a BigDecimal.
  */
  public BigDecimal getBigDecimalE02INVSD3()
  {
	return fieldE02INVSD3.getBigDecimal();
  }

  /**
  * Set field E02INVMD1 using a String value.
  */
  public void setE02INVMD1(String newvalue)
  {
	fieldE02INVMD1.setString(newvalue);
  }

  /**
  * Get value of field E02INVMD1 as a String.
  */
  public String getE02INVMD1()
  {
	return fieldE02INVMD1.getString();
  }

  /**
  * Set numeric field E02INVMD1 using a BigDecimal value.
  */
  public void setE02INVMD1(BigDecimal newvalue)
  {
	fieldE02INVMD1.setBigDecimal(newvalue);
  }

  /**
  * Return value of numeric field E02INVMD1 as a BigDecimal.
  */
  public BigDecimal getBigDecimalE02INVMD1()
  {
	return fieldE02INVMD1.getBigDecimal();
  }

  /**
  * Set field E02INVMD2 using a String value.
  */
  public void setE02INVMD2(String newvalue)
  {
	fieldE02INVMD2.setString(newvalue);
  }

  /**
  * Get value of field E02INVMD2 as a String.
  */
  public String getE02INVMD2()
  {
	return fieldE02INVMD2.getString();
  }

  /**
  * Set numeric field E02INVMD2 using a BigDecimal value.
  */
  public void setE02INVMD2(BigDecimal newvalue)
  {
	fieldE02INVMD2.setBigDecimal(newvalue);
  }

  /**
  * Return value of numeric field E02INVMD2 as a BigDecimal.
  */
  public BigDecimal getBigDecimalE02INVMD2()
  {
	return fieldE02INVMD2.getBigDecimal();
  }

  /**
  * Set field E02INVMD3 using a String value.
  */
  public void setE02INVMD3(String newvalue)
  {
	fieldE02INVMD3.setString(newvalue);
  }

  /**
  * Get value of field E02INVMD3 as a String.
  */
  public String getE02INVMD3()
  {
	return fieldE02INVMD3.getString();
  }

  /**
  * Set numeric field E02INVMD3 using a BigDecimal value.
  */
  public void setE02INVMD3(BigDecimal newvalue)
  {
	fieldE02INVMD3.setBigDecimal(newvalue);
  }

  /**
  * Return value of numeric field E02INVMD3 as a BigDecimal.
  */
  public BigDecimal getBigDecimalE02INVMD3()
  {
	return fieldE02INVMD3.getBigDecimal();
  }

  /**
  * Set field E02INVBLM using a String value.
  */
  public void setE02INVBLM(String newvalue)
  {
	fieldE02INVBLM.setString(newvalue);
  }

  /**
  * Get value of field E02INVBLM as a String.
  */
  public String getE02INVBLM()
  {
	return fieldE02INVBLM.getString();
  }

  /**
  * Set numeric field E02INVBLM using a BigDecimal value.
  */
  public void setE02INVBLM(BigDecimal newvalue)
  {
	fieldE02INVBLM.setBigDecimal(newvalue);
  }

  /**
  * Return value of numeric field E02INVBLM as a BigDecimal.
  */
  public BigDecimal getBigDecimalE02INVBLM()
  {
	return fieldE02INVBLM.getBigDecimal();
  }

  /**
  * Set field E02INVSTS using a String value.
  */
  public void setE02INVSTS(String newvalue)
  {
	fieldE02INVSTS.setString(newvalue);
  }

  /**
  * Get value of field E02INVSTS as a String.
  */
  public String getE02INVSTS()
  {
	return fieldE02INVSTS.getString();
  }

  /**
  * Set field D02ACMPRO using a String value.
  */
  public void setD02ACMPRO(String newvalue)
  {
	fieldD02ACMPRO.setString(newvalue);
  }

  /**
  * Get value of field D02ACMPRO as a String.
  */
  public String getD02ACMPRO()
  {
	return fieldD02ACMPRO.getString();
  }

}
