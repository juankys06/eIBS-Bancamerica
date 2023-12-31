package datapro.eibs.beans;

import datapro.eibs.sockets.*;
import java.io.*;
import java.math.*;

/**
* Class generated by AS/400 CRTCLASS command from EDL914001 physical file definition.
* 
* File level identifier is 1020905155031.
* Record format level identifier is 31541749A9865.
*/

public class EDL914001Message extends MessageRecord
{
  final int FIELDCOUNT = 32;
  private CharacterField fieldH01USERID = null;
  private CharacterField fieldH01PROGRM = null;
  private CharacterField fieldH01TIMSYS = null;
  private CharacterField fieldH01SCRCOD = null;
  private CharacterField fieldH01OPECOD = null;
  private CharacterField fieldH01FLGMAS = null;
  private CharacterField fieldH01FLGWK1 = null;
  private CharacterField fieldH01FLGWK2 = null;
  private CharacterField fieldH01FLGWK3 = null;
  private DecimalField fieldE01LGRACC = null;
  private CharacterField fieldE01LGRACD = null;
  private DecimalField fieldE01LGRCUN = null;
  private CharacterField fieldE01CUSNA1 = null;
  private CharacterField fieldE01LGRPRO = null;
  private CharacterField fieldE01LGRBNK = null;
  private DecimalField fieldE01LGRBRN = null;
  private CharacterField fieldE01LGRCCY = null;
  private DecimalField fieldE01LGRGLN = null;
  private DecimalField fieldE01LGRCCN = null;
  private DecimalField fieldE01LGRAMT = null;
  private DecimalField fieldE01LGRUBT = null;
  private CharacterField fieldE01LGROPE = null;
  private CharacterField fieldE01LGRRM1 = null;
  private DecimalField fieldE01LGRRTR = null;
  private DecimalField fieldE01LGRRDC = null;
  private DecimalField fieldE01LGRRDY = null;
  private DecimalField fieldE01LGRRDM = null;
  private DecimalField fieldE01LGRRDD = null;
  private DecimalField fieldE01LGRTIM = null;
  private CharacterField fieldE01FLGBUS = null;
  private CharacterField fieldE01FLGTYP = null;
  private CharacterField fieldE01FLGIBF = null;

  /**
  * Constructor for EDL914001Message.
  */
  public EDL914001Message()
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
    recordsize = 240;
    fileid = "1020905155031";
    recordid = "31541749A9865";
    message = new byte[getByteLength()];
    formatname = "EDL914001";
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
    fieldnames[9] = "E01LGRACC";
    tagnames[9] = "E01LGRACC";
    fields[9] = fieldE01LGRACC =
    new DecimalField(message, HEADERSIZE + 42, 13, 0, "E01LGRACC");
    fieldnames[10] = "E01LGRACD";
    tagnames[10] = "E01LGRACD";
    fields[10] = fieldE01LGRACD =
    new CharacterField(message, HEADERSIZE + 55, 2, "E01LGRACD");
    fieldnames[11] = "E01LGRCUN";
    tagnames[11] = "E01LGRCUN";
    fields[11] = fieldE01LGRCUN =
    new DecimalField(message, HEADERSIZE + 57, 10, 0, "E01LGRCUN");
    fieldnames[12] = "E01CUSNA1";
    tagnames[12] = "E01CUSNA1";
    fields[12] = fieldE01CUSNA1 =
    new CharacterField(message, HEADERSIZE + 67, 45, "E01CUSNA1");
    fieldnames[13] = "E01LGRPRO";
    tagnames[13] = "E01LGRPRO";
    fields[13] = fieldE01LGRPRO =
    new CharacterField(message, HEADERSIZE + 112, 4, "E01LGRPRO");
    fieldnames[14] = "E01LGRBNK";
    tagnames[14] = "E01LGRBNK";
    fields[14] = fieldE01LGRBNK =
    new CharacterField(message, HEADERSIZE + 116, 2, "E01LGRBNK");
    fieldnames[15] = "E01LGRBRN";
    tagnames[15] = "E01LGRBRN";
    fields[15] = fieldE01LGRBRN =
    new DecimalField(message, HEADERSIZE + 118, 4, 0, "E01LGRBRN");
    fieldnames[16] = "E01LGRCCY";
    tagnames[16] = "E01LGRCCY";
    fields[16] = fieldE01LGRCCY =
    new CharacterField(message, HEADERSIZE + 122, 3, "E01LGRCCY");
    fieldnames[17] = "E01LGRGLN";
    tagnames[17] = "E01LGRGLN";
    fields[17] = fieldE01LGRGLN =
    new DecimalField(message, HEADERSIZE + 125, 17, 0, "E01LGRGLN");
    fieldnames[18] = "E01LGRCCN";
    tagnames[18] = "E01LGRCCN";
    fields[18] = fieldE01LGRCCN =
    new DecimalField(message, HEADERSIZE + 142, 9, 0, "E01LGRCCN");
    fieldnames[19] = "E01LGRAMT";
    tagnames[19] = "E01LGRAMT";
    fields[19] = fieldE01LGRAMT =
    new DecimalField(message, HEADERSIZE + 151, 17, 2, "E01LGRAMT");
    fieldnames[20] = "E01LGRUBT";
    tagnames[20] = "E01LGRUBT";
    fields[20] = fieldE01LGRUBT =
    new DecimalField(message, HEADERSIZE + 168, 5, 0, "E01LGRUBT");
    fieldnames[21] = "E01LGROPE";
    tagnames[21] = "E01LGROPE";
    fields[21] = fieldE01LGROPE =
    new CharacterField(message, HEADERSIZE + 173, 10, "E01LGROPE");
    fieldnames[22] = "E01LGRRM1";
    tagnames[22] = "E01LGRRM1";
    fields[22] = fieldE01LGRRM1 =
    new CharacterField(message, HEADERSIZE + 183, 20, "E01LGRRM1");
    fieldnames[23] = "E01LGRRTR";
    tagnames[23] = "E01LGRRTR";
    fields[23] = fieldE01LGRRTR =
    new DecimalField(message, HEADERSIZE + 203, 13, 6, "E01LGRRTR");
    fieldnames[24] = "E01LGRRDC";
    tagnames[24] = "E01LGRRDC";
    fields[24] = fieldE01LGRRDC =
    new DecimalField(message, HEADERSIZE + 216, 2, 0, "E01LGRRDC");
    fieldnames[25] = "E01LGRRDY";
    tagnames[25] = "E01LGRRDY";
    fields[25] = fieldE01LGRRDY =
    new DecimalField(message, HEADERSIZE + 218, 3, 0, "E01LGRRDY");
    fieldnames[26] = "E01LGRRDM";
    tagnames[26] = "E01LGRRDM";
    fields[26] = fieldE01LGRRDM =
    new DecimalField(message, HEADERSIZE + 221, 3, 0, "E01LGRRDM");
    fieldnames[27] = "E01LGRRDD";
    tagnames[27] = "E01LGRRDD";
    fields[27] = fieldE01LGRRDD =
    new DecimalField(message, HEADERSIZE + 224, 3, 0, "E01LGRRDD");
    fieldnames[28] = "E01LGRTIM";
    tagnames[28] = "E01LGRTIM";
    fields[28] = fieldE01LGRTIM =
    new DecimalField(message, HEADERSIZE + 227, 7, 0, "E01LGRTIM");
    fieldnames[29] = "E01FLGBUS";
    tagnames[29] = "E01FLGBUS";
    fields[29] = fieldE01FLGBUS =
    new CharacterField(message, HEADERSIZE + 234, 4, "E01FLGBUS");
    fieldnames[30] = "E01FLGTYP";
    tagnames[30] = "E01FLGTYP";
    fields[30] = fieldE01FLGTYP =
    new CharacterField(message, HEADERSIZE + 238, 1, "E01FLGTYP");
    fieldnames[31] = "E01FLGIBF";
    tagnames[31] = "E01FLGIBF";
    fields[31] = fieldE01FLGIBF =
    new CharacterField(message, HEADERSIZE + 239, 1, "E01FLGIBF");
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
  * Set field E01LGRACC using a String value.
  */
  public void setE01LGRACC(String newvalue)
  {
    fieldE01LGRACC.setString(newvalue);
  }

  /**
  * Get value of field E01LGRACC as a String.
  */
  public String getE01LGRACC()
  {
    return fieldE01LGRACC.getString();
  }

  /**
  * Set numeric field E01LGRACC using a BigDecimal value.
  */
  public void setE01LGRACC(BigDecimal newvalue)
  {
    fieldE01LGRACC.setBigDecimal(newvalue);
  }

  /**
  * Return value of numeric field E01LGRACC as a BigDecimal.
  */
  public BigDecimal getBigDecimalE01LGRACC()
  {
    return fieldE01LGRACC.getBigDecimal();
  }

  /**
  * Set field E01LGRACD using a String value.
  */
  public void setE01LGRACD(String newvalue)
  {
    fieldE01LGRACD.setString(newvalue);
  }

  /**
  * Get value of field E01LGRACD as a String.
  */
  public String getE01LGRACD()
  {
    return fieldE01LGRACD.getString();
  }

  /**
  * Set field E01LGRCUN using a String value.
  */
  public void setE01LGRCUN(String newvalue)
  {
    fieldE01LGRCUN.setString(newvalue);
  }

  /**
  * Get value of field E01LGRCUN as a String.
  */
  public String getE01LGRCUN()
  {
    return fieldE01LGRCUN.getString();
  }

  /**
  * Set numeric field E01LGRCUN using a BigDecimal value.
  */
  public void setE01LGRCUN(BigDecimal newvalue)
  {
    fieldE01LGRCUN.setBigDecimal(newvalue);
  }

  /**
  * Return value of numeric field E01LGRCUN as a BigDecimal.
  */
  public BigDecimal getBigDecimalE01LGRCUN()
  {
    return fieldE01LGRCUN.getBigDecimal();
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
  * Set field E01LGRPRO using a String value.
  */
  public void setE01LGRPRO(String newvalue)
  {
    fieldE01LGRPRO.setString(newvalue);
  }

  /**
  * Get value of field E01LGRPRO as a String.
  */
  public String getE01LGRPRO()
  {
    return fieldE01LGRPRO.getString();
  }

  /**
  * Set field E01LGRBNK using a String value.
  */
  public void setE01LGRBNK(String newvalue)
  {
    fieldE01LGRBNK.setString(newvalue);
  }

  /**
  * Get value of field E01LGRBNK as a String.
  */
  public String getE01LGRBNK()
  {
    return fieldE01LGRBNK.getString();
  }

  /**
  * Set field E01LGRBRN using a String value.
  */
  public void setE01LGRBRN(String newvalue)
  {
    fieldE01LGRBRN.setString(newvalue);
  }

  /**
  * Get value of field E01LGRBRN as a String.
  */
  public String getE01LGRBRN()
  {
    return fieldE01LGRBRN.getString();
  }

  /**
  * Set numeric field E01LGRBRN using a BigDecimal value.
  */
  public void setE01LGRBRN(BigDecimal newvalue)
  {
    fieldE01LGRBRN.setBigDecimal(newvalue);
  }

  /**
  * Return value of numeric field E01LGRBRN as a BigDecimal.
  */
  public BigDecimal getBigDecimalE01LGRBRN()
  {
    return fieldE01LGRBRN.getBigDecimal();
  }

  /**
  * Set field E01LGRCCY using a String value.
  */
  public void setE01LGRCCY(String newvalue)
  {
    fieldE01LGRCCY.setString(newvalue);
  }

  /**
  * Get value of field E01LGRCCY as a String.
  */
  public String getE01LGRCCY()
  {
    return fieldE01LGRCCY.getString();
  }

  /**
  * Set field E01LGRGLN using a String value.
  */
  public void setE01LGRGLN(String newvalue)
  {
    fieldE01LGRGLN.setString(newvalue);
  }

  /**
  * Get value of field E01LGRGLN as a String.
  */
  public String getE01LGRGLN()
  {
    return fieldE01LGRGLN.getString();
  }

  /**
  * Set numeric field E01LGRGLN using a BigDecimal value.
  */
  public void setE01LGRGLN(BigDecimal newvalue)
  {
    fieldE01LGRGLN.setBigDecimal(newvalue);
  }

  /**
  * Return value of numeric field E01LGRGLN as a BigDecimal.
  */
  public BigDecimal getBigDecimalE01LGRGLN()
  {
    return fieldE01LGRGLN.getBigDecimal();
  }

  /**
  * Set field E01LGRCCN using a String value.
  */
  public void setE01LGRCCN(String newvalue)
  {
    fieldE01LGRCCN.setString(newvalue);
  }

  /**
  * Get value of field E01LGRCCN as a String.
  */
  public String getE01LGRCCN()
  {
    return fieldE01LGRCCN.getString();
  }

  /**
  * Set numeric field E01LGRCCN using a BigDecimal value.
  */
  public void setE01LGRCCN(BigDecimal newvalue)
  {
    fieldE01LGRCCN.setBigDecimal(newvalue);
  }

  /**
  * Return value of numeric field E01LGRCCN as a BigDecimal.
  */
  public BigDecimal getBigDecimalE01LGRCCN()
  {
    return fieldE01LGRCCN.getBigDecimal();
  }

  /**
  * Set field E01LGRAMT using a String value.
  */
  public void setE01LGRAMT(String newvalue)
  {
    fieldE01LGRAMT.setString(newvalue);
  }

  /**
  * Get value of field E01LGRAMT as a String.
  */
  public String getE01LGRAMT()
  {
    return fieldE01LGRAMT.getString();
  }

  /**
  * Set numeric field E01LGRAMT using a BigDecimal value.
  */
  public void setE01LGRAMT(BigDecimal newvalue)
  {
    fieldE01LGRAMT.setBigDecimal(newvalue);
  }

  /**
  * Return value of numeric field E01LGRAMT as a BigDecimal.
  */
  public BigDecimal getBigDecimalE01LGRAMT()
  {
    return fieldE01LGRAMT.getBigDecimal();
  }

  /**
  * Set field E01LGRUBT using a String value.
  */
  public void setE01LGRUBT(String newvalue)
  {
    fieldE01LGRUBT.setString(newvalue);
  }

  /**
  * Get value of field E01LGRUBT as a String.
  */
  public String getE01LGRUBT()
  {
    return fieldE01LGRUBT.getString();
  }

  /**
  * Set numeric field E01LGRUBT using a BigDecimal value.
  */
  public void setE01LGRUBT(BigDecimal newvalue)
  {
    fieldE01LGRUBT.setBigDecimal(newvalue);
  }

  /**
  * Return value of numeric field E01LGRUBT as a BigDecimal.
  */
  public BigDecimal getBigDecimalE01LGRUBT()
  {
    return fieldE01LGRUBT.getBigDecimal();
  }

  /**
  * Set field E01LGROPE using a String value.
  */
  public void setE01LGROPE(String newvalue)
  {
    fieldE01LGROPE.setString(newvalue);
  }

  /**
  * Get value of field E01LGROPE as a String.
  */
  public String getE01LGROPE()
  {
    return fieldE01LGROPE.getString();
  }

  /**
  * Set field E01LGRRM1 using a String value.
  */
  public void setE01LGRRM1(String newvalue)
  {
    fieldE01LGRRM1.setString(newvalue);
  }

  /**
  * Get value of field E01LGRRM1 as a String.
  */
  public String getE01LGRRM1()
  {
    return fieldE01LGRRM1.getString();
  }

  /**
  * Set field E01LGRRTR using a String value.
  */
  public void setE01LGRRTR(String newvalue)
  {
    fieldE01LGRRTR.setString(newvalue);
  }

  /**
  * Get value of field E01LGRRTR as a String.
  */
  public String getE01LGRRTR()
  {
    return fieldE01LGRRTR.getString();
  }

  /**
  * Set numeric field E01LGRRTR using a BigDecimal value.
  */
  public void setE01LGRRTR(BigDecimal newvalue)
  {
    fieldE01LGRRTR.setBigDecimal(newvalue);
  }

  /**
  * Return value of numeric field E01LGRRTR as a BigDecimal.
  */
  public BigDecimal getBigDecimalE01LGRRTR()
  {
    return fieldE01LGRRTR.getBigDecimal();
  }

  /**
  * Set field E01LGRRDC using a String value.
  */
  public void setE01LGRRDC(String newvalue)
  {
    fieldE01LGRRDC.setString(newvalue);
  }

  /**
  * Get value of field E01LGRRDC as a String.
  */
  public String getE01LGRRDC()
  {
    return fieldE01LGRRDC.getString();
  }

  /**
  * Set numeric field E01LGRRDC using a BigDecimal value.
  */
  public void setE01LGRRDC(BigDecimal newvalue)
  {
    fieldE01LGRRDC.setBigDecimal(newvalue);
  }

  /**
  * Return value of numeric field E01LGRRDC as a BigDecimal.
  */
  public BigDecimal getBigDecimalE01LGRRDC()
  {
    return fieldE01LGRRDC.getBigDecimal();
  }

  /**
  * Set field E01LGRRDY using a String value.
  */
  public void setE01LGRRDY(String newvalue)
  {
    fieldE01LGRRDY.setString(newvalue);
  }

  /**
  * Get value of field E01LGRRDY as a String.
  */
  public String getE01LGRRDY()
  {
    return fieldE01LGRRDY.getString();
  }

  /**
  * Set numeric field E01LGRRDY using a BigDecimal value.
  */
  public void setE01LGRRDY(BigDecimal newvalue)
  {
    fieldE01LGRRDY.setBigDecimal(newvalue);
  }

  /**
  * Return value of numeric field E01LGRRDY as a BigDecimal.
  */
  public BigDecimal getBigDecimalE01LGRRDY()
  {
    return fieldE01LGRRDY.getBigDecimal();
  }

  /**
  * Set field E01LGRRDM using a String value.
  */
  public void setE01LGRRDM(String newvalue)
  {
    fieldE01LGRRDM.setString(newvalue);
  }

  /**
  * Get value of field E01LGRRDM as a String.
  */
  public String getE01LGRRDM()
  {
    return fieldE01LGRRDM.getString();
  }

  /**
  * Set numeric field E01LGRRDM using a BigDecimal value.
  */
  public void setE01LGRRDM(BigDecimal newvalue)
  {
    fieldE01LGRRDM.setBigDecimal(newvalue);
  }

  /**
  * Return value of numeric field E01LGRRDM as a BigDecimal.
  */
  public BigDecimal getBigDecimalE01LGRRDM()
  {
    return fieldE01LGRRDM.getBigDecimal();
  }

  /**
  * Set field E01LGRRDD using a String value.
  */
  public void setE01LGRRDD(String newvalue)
  {
    fieldE01LGRRDD.setString(newvalue);
  }

  /**
  * Get value of field E01LGRRDD as a String.
  */
  public String getE01LGRRDD()
  {
    return fieldE01LGRRDD.getString();
  }

  /**
  * Set numeric field E01LGRRDD using a BigDecimal value.
  */
  public void setE01LGRRDD(BigDecimal newvalue)
  {
    fieldE01LGRRDD.setBigDecimal(newvalue);
  }

  /**
  * Return value of numeric field E01LGRRDD as a BigDecimal.
  */
  public BigDecimal getBigDecimalE01LGRRDD()
  {
    return fieldE01LGRRDD.getBigDecimal();
  }

  /**
  * Set field E01LGRTIM using a String value.
  */
  public void setE01LGRTIM(String newvalue)
  {
    fieldE01LGRTIM.setString(newvalue);
  }

  /**
  * Get value of field E01LGRTIM as a String.
  */
  public String getE01LGRTIM()
  {
    return fieldE01LGRTIM.getString();
  }

  /**
  * Set numeric field E01LGRTIM using a BigDecimal value.
  */
  public void setE01LGRTIM(BigDecimal newvalue)
  {
    fieldE01LGRTIM.setBigDecimal(newvalue);
  }

  /**
  * Return value of numeric field E01LGRTIM as a BigDecimal.
  */
  public BigDecimal getBigDecimalE01LGRTIM()
  {
    return fieldE01LGRTIM.getBigDecimal();
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
  * Set field E01FLGTYP using a String value.
  */
  public void setE01FLGTYP(String newvalue)
  {
    fieldE01FLGTYP.setString(newvalue);
  }

  /**
  * Get value of field E01FLGTYP as a String.
  */
  public String getE01FLGTYP()
  {
    return fieldE01FLGTYP.getString();
  }

  /**
  * Set field E01FLGIBF using a String value.
  */
  public void setE01FLGIBF(String newvalue)
  {
    fieldE01FLGIBF.setString(newvalue);
  }

  /**
  * Get value of field E01FLGIBF as a String.
  */
  public String getE01FLGIBF()
  {
    return fieldE01FLGIBF.getString();
  }

}
