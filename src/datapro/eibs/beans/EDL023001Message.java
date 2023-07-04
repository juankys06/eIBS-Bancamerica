package datapro.eibs.beans;

import datapro.eibs.sockets.*;
import java.io.*;
import java.math.*;

/**
* Class generated by AS/400 CRTCLASS command from EDL023001 physical file definition.
* 
* File level identifier is 1010803141850.
* Record format level identifier is 495AA7A0FD86A.
*/

public class EDL023001Message extends MessageRecord
{
  final int FIELDCOUNT = 30;
  private CharacterField fieldH01USERID = null;
  private CharacterField fieldH01PROGRM = null;
  private CharacterField fieldH01TIMSYS = null;
  private CharacterField fieldH01SCRCOD = null;
  private CharacterField fieldH01OPECOD = null;
  private CharacterField fieldH01FLGMAS = null;
  private CharacterField fieldH01FLGWK1 = null;
  private CharacterField fieldH01FLGWK2 = null;
  private CharacterField fieldH01FLGWK3 = null;
  private DecimalField fieldE01DEAACC = null;
  private CharacterField fieldE01CUSSHN = null;
  private DecimalField fieldE01DEARTE = null;
  private DecimalField fieldE01MATDAY = null;
  private DecimalField fieldE01MATDT1 = null;
  private DecimalField fieldE01MATDT2 = null;
  private DecimalField fieldE01MATDT3 = null;
  private DecimalField fieldE01MATBAL = null;
  private DecimalField fieldE01DEAEXR = null;
  private DecimalField fieldE01MATBSE = null;
  private CharacterField fieldE01DEATYP = null;
  private DecimalField fieldE01DEATRM = null;
  private CharacterField fieldE01DEATRC = null;
  private CharacterField fieldE01DEAOFC = null;
  private CharacterField fieldE01OFCNME = null;
  private DecimalField fieldE01OPNDT1 = null;
  private DecimalField fieldE01OPNDT2 = null;
  private DecimalField fieldE01OPNDT3 = null;
  private DecimalField fieldE01CUSDT1 = null;
  private DecimalField fieldE01CUSDT2 = null;
  private DecimalField fieldE01CUSDT3 = null;

  /**
  * Constructor for EDL023001Message.
  */
  public EDL023001Message()
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
	recordsize = 229;
	fileid = "1010803141850";
	recordid = "495AA7A0FD86A";
	message = new byte[getByteLength()];
	formatname = "EDL023001";
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
	fieldnames[9] = "E01DEAACC";
	tagnames[9] = "E01DEAACC";
	fields[9] = fieldE01DEAACC =
	new DecimalField(message, HEADERSIZE + 42, 13, 0, "E01DEAACC");
	fieldnames[10] = "E01CUSSHN";
	tagnames[10] = "E01CUSSHN";
	fields[10] = fieldE01CUSSHN =
	new CharacterField(message, HEADERSIZE + 55, 35, "E01CUSSHN");
	fieldnames[11] = "E01DEARTE";
	tagnames[11] = "E01DEARTE";
	fields[11] = fieldE01DEARTE =
	new DecimalField(message, HEADERSIZE + 90, 11, 6, "E01DEARTE");
	fieldnames[12] = "E01MATDAY";
	tagnames[12] = "E01MATDAY";
	fields[12] = fieldE01MATDAY =
	new DecimalField(message, HEADERSIZE + 101, 5, 0, "E01MATDAY");
	fieldnames[13] = "E01MATDT1";
	tagnames[13] = "E01MATDT1";
	fields[13] = fieldE01MATDT1 =
	new DecimalField(message, HEADERSIZE + 106, 3, 0, "E01MATDT1");
	fieldnames[14] = "E01MATDT2";
	tagnames[14] = "E01MATDT2";
	fields[14] = fieldE01MATDT2 =
	new DecimalField(message, HEADERSIZE + 109, 3, 0, "E01MATDT2");
	fieldnames[15] = "E01MATDT3";
	tagnames[15] = "E01MATDT3";
	fields[15] = fieldE01MATDT3 =
	new DecimalField(message, HEADERSIZE + 112, 3, 0, "E01MATDT3");
	fieldnames[16] = "E01MATBAL";
	tagnames[16] = "E01MATBAL";
	fields[16] = fieldE01MATBAL =
	new DecimalField(message, HEADERSIZE + 115, 17, 2, "E01MATBAL");
	fieldnames[17] = "E01DEAEXR";
	tagnames[17] = "E01DEAEXR";
	fields[17] = fieldE01DEAEXR =
	new DecimalField(message, HEADERSIZE + 132, 13, 6, "E01DEAEXR");
	fieldnames[18] = "E01MATBSE";
	tagnames[18] = "E01MATBSE";
	fields[18] = fieldE01MATBSE =
	new DecimalField(message, HEADERSIZE + 145, 17, 2, "E01MATBSE");
	fieldnames[19] = "E01DEATYP";
	tagnames[19] = "E01DEATYP";
	fields[19] = fieldE01DEATYP =
	new CharacterField(message, HEADERSIZE + 162, 4, "E01DEATYP");
	fieldnames[20] = "E01DEATRM";
	tagnames[20] = "E01DEATRM";
	fields[20] = fieldE01DEATRM =
	new DecimalField(message, HEADERSIZE + 166, 5, 0, "E01DEATRM");
	fieldnames[21] = "E01DEATRC";
	tagnames[21] = "E01DEATRC";
	fields[21] = fieldE01DEATRC =
	new CharacterField(message, HEADERSIZE + 171, 1, "E01DEATRC");
	fieldnames[22] = "E01DEAOFC";
	tagnames[22] = "E01DEAOFC";
	fields[22] = fieldE01DEAOFC =
	new CharacterField(message, HEADERSIZE + 172, 4, "E01DEAOFC");
	fieldnames[23] = "E01OFCNME";
	tagnames[23] = "E01OFCNME";
	fields[23] = fieldE01OFCNME =
	new CharacterField(message, HEADERSIZE + 176, 35, "E01OFCNME");
	fieldnames[24] = "E01OPNDT1";
	tagnames[24] = "E01OPNDT1";
	fields[24] = fieldE01OPNDT1 =
	new DecimalField(message, HEADERSIZE + 211, 3, 0, "E01OPNDT1");
	fieldnames[25] = "E01OPNDT2";
	tagnames[25] = "E01OPNDT2";
	fields[25] = fieldE01OPNDT2 =
	new DecimalField(message, HEADERSIZE + 214, 3, 0, "E01OPNDT2");
	fieldnames[26] = "E01OPNDT3";
	tagnames[26] = "E01OPNDT3";
	fields[26] = fieldE01OPNDT3 =
	new DecimalField(message, HEADERSIZE + 217, 3, 0, "E01OPNDT3");
	fieldnames[27] = "E01CUSDT1";
	tagnames[27] = "E01CUSDT1";
	fields[27] = fieldE01CUSDT1 =
	new DecimalField(message, HEADERSIZE + 220, 3, 0, "E01CUSDT1");
	fieldnames[28] = "E01CUSDT2";
	tagnames[28] = "E01CUSDT2";
	fields[28] = fieldE01CUSDT2 =
	new DecimalField(message, HEADERSIZE + 223, 3, 0, "E01CUSDT2");
	fieldnames[29] = "E01CUSDT3";
	tagnames[29] = "E01CUSDT3";
	fields[29] = fieldE01CUSDT3 =
	new DecimalField(message, HEADERSIZE + 226, 3, 0, "E01CUSDT3");
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
  * Set field E01CUSSHN using a String value.
  */
  public void setE01CUSSHN(String newvalue)
  {
	fieldE01CUSSHN.setString(newvalue);
  }  

  /**
  * Get value of field E01CUSSHN as a String.
  */
  public String getE01CUSSHN()
  {
	return fieldE01CUSSHN.getString();
  }  

  /**
  * Set field E01DEARTE using a String value.
  */
  public void setE01DEARTE(String newvalue)
  {
	fieldE01DEARTE.setString(newvalue);
  }  

  /**
  * Get value of field E01DEARTE as a String.
  */
  public String getE01DEARTE()
  {
	return fieldE01DEARTE.getString();
  }  

  /**
  * Set numeric field E01DEARTE using a BigDecimal value.
  */
  public void setE01DEARTE(BigDecimal newvalue)
  {
	fieldE01DEARTE.setBigDecimal(newvalue);
  }  

  /**
  * Return value of numeric field E01DEARTE as a BigDecimal.
  */
  public BigDecimal getBigDecimalE01DEARTE()
  {
	return fieldE01DEARTE.getBigDecimal();
  }  

  /**
  * Set field E01MATDAY using a String value.
  */
  public void setE01MATDAY(String newvalue)
  {
	fieldE01MATDAY.setString(newvalue);
  }  

  /**
  * Get value of field E01MATDAY as a String.
  */
  public String getE01MATDAY()
  {
	return fieldE01MATDAY.getString();
  }  

  /**
  * Set numeric field E01MATDAY using a BigDecimal value.
  */
  public void setE01MATDAY(BigDecimal newvalue)
  {
	fieldE01MATDAY.setBigDecimal(newvalue);
  }  

  /**
  * Return value of numeric field E01MATDAY as a BigDecimal.
  */
  public BigDecimal getBigDecimalE01MATDAY()
  {
	return fieldE01MATDAY.getBigDecimal();
  }  

  /**
  * Set field E01MATDT1 using a String value.
  */
  public void setE01MATDT1(String newvalue)
  {
	fieldE01MATDT1.setString(newvalue);
  }  

  /**
  * Get value of field E01MATDT1 as a String.
  */
  public String getE01MATDT1()
  {
	return fieldE01MATDT1.getString();
  }  

  /**
  * Set numeric field E01MATDT1 using a BigDecimal value.
  */
  public void setE01MATDT1(BigDecimal newvalue)
  {
	fieldE01MATDT1.setBigDecimal(newvalue);
  }  

  /**
  * Return value of numeric field E01MATDT1 as a BigDecimal.
  */
  public BigDecimal getBigDecimalE01MATDT1()
  {
	return fieldE01MATDT1.getBigDecimal();
  }  

  /**
  * Set field E01MATDT2 using a String value.
  */
  public void setE01MATDT2(String newvalue)
  {
	fieldE01MATDT2.setString(newvalue);
  }  

  /**
  * Get value of field E01MATDT2 as a String.
  */
  public String getE01MATDT2()
  {
	return fieldE01MATDT2.getString();
  }  

  /**
  * Set numeric field E01MATDT2 using a BigDecimal value.
  */
  public void setE01MATDT2(BigDecimal newvalue)
  {
	fieldE01MATDT2.setBigDecimal(newvalue);
  }  

  /**
  * Return value of numeric field E01MATDT2 as a BigDecimal.
  */
  public BigDecimal getBigDecimalE01MATDT2()
  {
	return fieldE01MATDT2.getBigDecimal();
  }  

  /**
  * Set field E01MATDT3 using a String value.
  */
  public void setE01MATDT3(String newvalue)
  {
	fieldE01MATDT3.setString(newvalue);
  }  

  /**
  * Get value of field E01MATDT3 as a String.
  */
  public String getE01MATDT3()
  {
	return fieldE01MATDT3.getString();
  }  

  /**
  * Set numeric field E01MATDT3 using a BigDecimal value.
  */
  public void setE01MATDT3(BigDecimal newvalue)
  {
	fieldE01MATDT3.setBigDecimal(newvalue);
  }  

  /**
  * Return value of numeric field E01MATDT3 as a BigDecimal.
  */
  public BigDecimal getBigDecimalE01MATDT3()
  {
	return fieldE01MATDT3.getBigDecimal();
  }  

  /**
  * Set field E01MATBAL using a String value.
  */
  public void setE01MATBAL(String newvalue)
  {
	fieldE01MATBAL.setString(newvalue);
  }  

  /**
  * Get value of field E01MATBAL as a String.
  */
  public String getE01MATBAL()
  {
	return fieldE01MATBAL.getString();
  }  

  /**
  * Set numeric field E01MATBAL using a BigDecimal value.
  */
  public void setE01MATBAL(BigDecimal newvalue)
  {
	fieldE01MATBAL.setBigDecimal(newvalue);
  }  

  /**
  * Return value of numeric field E01MATBAL as a BigDecimal.
  */
  public BigDecimal getBigDecimalE01MATBAL()
  {
	return fieldE01MATBAL.getBigDecimal();
  }  

  /**
  * Set field E01DEAEXR using a String value.
  */
  public void setE01DEAEXR(String newvalue)
  {
	fieldE01DEAEXR.setString(newvalue);
  }  

  /**
  * Get value of field E01DEAEXR as a String.
  */
  public String getE01DEAEXR()
  {
	return fieldE01DEAEXR.getString();
  }  

  /**
  * Set numeric field E01DEAEXR using a BigDecimal value.
  */
  public void setE01DEAEXR(BigDecimal newvalue)
  {
	fieldE01DEAEXR.setBigDecimal(newvalue);
  }  

  /**
  * Return value of numeric field E01DEAEXR as a BigDecimal.
  */
  public BigDecimal getBigDecimalE01DEAEXR()
  {
	return fieldE01DEAEXR.getBigDecimal();
  }  

  /**
  * Set field E01MATBSE using a String value.
  */
  public void setE01MATBSE(String newvalue)
  {
	fieldE01MATBSE.setString(newvalue);
  }  

  /**
  * Get value of field E01MATBSE as a String.
  */
  public String getE01MATBSE()
  {
	return fieldE01MATBSE.getString();
  }  

  /**
  * Set numeric field E01MATBSE using a BigDecimal value.
  */
  public void setE01MATBSE(BigDecimal newvalue)
  {
	fieldE01MATBSE.setBigDecimal(newvalue);
  }  

  /**
  * Return value of numeric field E01MATBSE as a BigDecimal.
  */
  public BigDecimal getBigDecimalE01MATBSE()
  {
	return fieldE01MATBSE.getBigDecimal();
  }  

  /**
  * Set field E01DEATYP using a String value.
  */
  public void setE01DEATYP(String newvalue)
  {
	fieldE01DEATYP.setString(newvalue);
  }  

  /**
  * Get value of field E01DEATYP as a String.
  */
  public String getE01DEATYP()
  {
	return fieldE01DEATYP.getString();
  }  

  /**
  * Set field E01DEATRM using a String value.
  */
  public void setE01DEATRM(String newvalue)
  {
	fieldE01DEATRM.setString(newvalue);
  }  

  /**
  * Get value of field E01DEATRM as a String.
  */
  public String getE01DEATRM()
  {
	return fieldE01DEATRM.getString();
  }  

  /**
  * Set numeric field E01DEATRM using a BigDecimal value.
  */
  public void setE01DEATRM(BigDecimal newvalue)
  {
	fieldE01DEATRM.setBigDecimal(newvalue);
  }  

  /**
  * Return value of numeric field E01DEATRM as a BigDecimal.
  */
  public BigDecimal getBigDecimalE01DEATRM()
  {
	return fieldE01DEATRM.getBigDecimal();
  }  

  /**
  * Set field E01DEATRC using a String value.
  */
  public void setE01DEATRC(String newvalue)
  {
	fieldE01DEATRC.setString(newvalue);
  }  

  /**
  * Get value of field E01DEATRC as a String.
  */
  public String getE01DEATRC()
  {
	return fieldE01DEATRC.getString();
  }  

  /**
  * Set field E01DEAOFC using a String value.
  */
  public void setE01DEAOFC(String newvalue)
  {
	fieldE01DEAOFC.setString(newvalue);
  }  

  /**
  * Get value of field E01DEAOFC as a String.
  */
  public String getE01DEAOFC()
  {
	return fieldE01DEAOFC.getString();
  }  

  /**
  * Set field E01OFCNME using a String value.
  */
  public void setE01OFCNME(String newvalue)
  {
	fieldE01OFCNME.setString(newvalue);
  }  

  /**
  * Get value of field E01OFCNME as a String.
  */
  public String getE01OFCNME()
  {
	return fieldE01OFCNME.getString();
  }  

  /**
  * Set field E01OPNDT1 using a String value.
  */
  public void setE01OPNDT1(String newvalue)
  {
	fieldE01OPNDT1.setString(newvalue);
  }  

  /**
  * Get value of field E01OPNDT1 as a String.
  */
  public String getE01OPNDT1()
  {
	return fieldE01OPNDT1.getString();
  }  

  /**
  * Set numeric field E01OPNDT1 using a BigDecimal value.
  */
  public void setE01OPNDT1(BigDecimal newvalue)
  {
	fieldE01OPNDT1.setBigDecimal(newvalue);
  }  

  /**
  * Return value of numeric field E01OPNDT1 as a BigDecimal.
  */
  public BigDecimal getBigDecimalE01OPNDT1()
  {
	return fieldE01OPNDT1.getBigDecimal();
  }  

  /**
  * Set field E01OPNDT2 using a String value.
  */
  public void setE01OPNDT2(String newvalue)
  {
	fieldE01OPNDT2.setString(newvalue);
  }  

  /**
  * Get value of field E01OPNDT2 as a String.
  */
  public String getE01OPNDT2()
  {
	return fieldE01OPNDT2.getString();
  }  

  /**
  * Set numeric field E01OPNDT2 using a BigDecimal value.
  */
  public void setE01OPNDT2(BigDecimal newvalue)
  {
	fieldE01OPNDT2.setBigDecimal(newvalue);
  }  

  /**
  * Return value of numeric field E01OPNDT2 as a BigDecimal.
  */
  public BigDecimal getBigDecimalE01OPNDT2()
  {
	return fieldE01OPNDT2.getBigDecimal();
  }  

  /**
  * Set field E01OPNDT3 using a String value.
  */
  public void setE01OPNDT3(String newvalue)
  {
	fieldE01OPNDT3.setString(newvalue);
  }  

  /**
  * Get value of field E01OPNDT3 as a String.
  */
  public String getE01OPNDT3()
  {
	return fieldE01OPNDT3.getString();
  }  

  /**
  * Set numeric field E01OPNDT3 using a BigDecimal value.
  */
  public void setE01OPNDT3(BigDecimal newvalue)
  {
	fieldE01OPNDT3.setBigDecimal(newvalue);
  }  

  /**
  * Return value of numeric field E01OPNDT3 as a BigDecimal.
  */
  public BigDecimal getBigDecimalE01OPNDT3()
  {
	return fieldE01OPNDT3.getBigDecimal();
  }  

  /**
  * Set field E01CUSDT1 using a String value.
  */
  public void setE01CUSDT1(String newvalue)
  {
	fieldE01CUSDT1.setString(newvalue);
  }  

  /**
  * Get value of field E01CUSDT1 as a String.
  */
  public String getE01CUSDT1()
  {
	return fieldE01CUSDT1.getString();
  }  

  /**
  * Set numeric field E01CUSDT1 using a BigDecimal value.
  */
  public void setE01CUSDT1(BigDecimal newvalue)
  {
	fieldE01CUSDT1.setBigDecimal(newvalue);
  }  

  /**
  * Return value of numeric field E01CUSDT1 as a BigDecimal.
  */
  public BigDecimal getBigDecimalE01CUSDT1()
  {
	return fieldE01CUSDT1.getBigDecimal();
  }  

  /**
  * Set field E01CUSDT2 using a String value.
  */
  public void setE01CUSDT2(String newvalue)
  {
	fieldE01CUSDT2.setString(newvalue);
  }  

  /**
  * Get value of field E01CUSDT2 as a String.
  */
  public String getE01CUSDT2()
  {
	return fieldE01CUSDT2.getString();
  }  

  /**
  * Set numeric field E01CUSDT2 using a BigDecimal value.
  */
  public void setE01CUSDT2(BigDecimal newvalue)
  {
	fieldE01CUSDT2.setBigDecimal(newvalue);
  }  

  /**
  * Return value of numeric field E01CUSDT2 as a BigDecimal.
  */
  public BigDecimal getBigDecimalE01CUSDT2()
  {
	return fieldE01CUSDT2.getBigDecimal();
  }  

  /**
  * Set field E01CUSDT3 using a String value.
  */
  public void setE01CUSDT3(String newvalue)
  {
	fieldE01CUSDT3.setString(newvalue);
  }  

  /**
  * Get value of field E01CUSDT3 as a String.
  */
  public String getE01CUSDT3()
  {
	return fieldE01CUSDT3.getString();
  }  

  /**
  * Set numeric field E01CUSDT3 using a BigDecimal value.
  */
  public void setE01CUSDT3(BigDecimal newvalue)
  {
	fieldE01CUSDT3.setBigDecimal(newvalue);
  }  

  /**
  * Return value of numeric field E01CUSDT3 as a BigDecimal.
  */
  public BigDecimal getBigDecimalE01CUSDT3()
  {
	return fieldE01CUSDT3.getBigDecimal();
  }  

}