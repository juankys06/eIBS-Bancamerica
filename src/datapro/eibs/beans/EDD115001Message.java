package datapro.eibs.beans;

import datapro.eibs.sockets.*;
import java.io.*;
import java.math.*;

import java.util.*;

/**
* Class generated by AS/400 CRTCLASS command from EDD115001 physical file definition.
* 
* File level identifier is 1030121191852.
* Record format level identifier is 3B9007591CBF0.
*/

public class EDD115001Message extends MessageRecord
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
                                     "E01ACMACC",
                                     "E01ACMACD",
                                     "E01ACMPRO",
                                     "E01ACMCUN",
                                     "E01CUSNA1",
                                     "E01ACMBNK",
                                     "E01ACMBRN",
                                     "E01ACMCCY",
                                     "E01ACMGLN",
                                     "E01ACMCCN",
                                     "E01ACMOFC",
                                     "E01DSCOFC",
                                     "E01GRSBAL",
                                     "E01UNCBAL",
                                     "E01NETBAL",
                                     "E01HLDAMT",
                                     "E01OVDAMT",
                                     "E01INTAMT",
                                     "E01COMAMT",
                                     "E01DDCAMT",
                                     "E01AMOAMT",
                                     "E01PAGAMT",
                                     "E01OPNDT1",
                                     "E01OPNDT2",
                                     "E01OPNDT3",
                                     "E01RUNDT1",
                                     "E01RUNDT2",
                                     "E01RUNDT3",
                                     "E01NARRAT",
                                     "E01NPGOVD",
                                     "E01NPGINT",
                                     "E01PAGOPC",
                                     "E01PAGCON",
                                     "E01PAGOBK",
                                     "E01PAGOBR",
                                     "E01PAGOCY",
                                     "E01PAGOGL",
                                     "E01PAGOAC",
                                     "E01PAGOCN",
                                     "E01CANRSN"
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
                                   "E01ACMACC",
                                   "E01ACMACD",
                                   "E01ACMPRO",
                                   "E01ACMCUN",
                                   "E01CUSNA1",
                                   "E01ACMBNK",
                                   "E01ACMBRN",
                                   "E01ACMCCY",
                                   "E01ACMGLN",
                                   "E01ACMCCN",
                                   "E01ACMOFC",
                                   "E01DSCOFC",
                                   "E01GRSBAL",
                                   "E01UNCBAL",
                                   "E01NETBAL",
                                   "E01HLDAMT",
                                   "E01OVDAMT",
                                   "E01INTAMT",
                                   "E01COMAMT",
                                   "E01DDCAMT",
                                   "E01AMOAMT",
                                   "E01PAGAMT",
                                   "E01OPNDT1",
                                   "E01OPNDT2",
                                   "E01OPNDT3",
                                   "E01RUNDT1",
                                   "E01RUNDT2",
                                   "E01RUNDT3",
                                   "E01NARRAT",
                                   "E01NPGOVD",
                                   "E01NPGINT",
                                   "E01PAGOPC",
                                   "E01PAGCON",
                                   "E01PAGOBK",
                                   "E01PAGOBR",
                                   "E01PAGOCY",
                                   "E01PAGOGL",
                                   "E01PAGOAC",
                                   "E01PAGOCN",
                                   "E01CANRSN"
                                 };
  final static String fid = "1030121191852";
  final static String rid = "3B9007591CBF0";
  final static String fmtname = "EDD115001";
  final int FIELDCOUNT = 49;
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
  private DecimalField fieldE01ACMACC = null;
  private CharacterField fieldE01ACMACD = null;
  private CharacterField fieldE01ACMPRO = null;
  private DecimalField fieldE01ACMCUN = null;
  private CharacterField fieldE01CUSNA1 = null;
  private CharacterField fieldE01ACMBNK = null;
  private DecimalField fieldE01ACMBRN = null;
  private CharacterField fieldE01ACMCCY = null;
  private DecimalField fieldE01ACMGLN = null;
  private DecimalField fieldE01ACMCCN = null;
  private CharacterField fieldE01ACMOFC = null;
  private CharacterField fieldE01DSCOFC = null;
  private DecimalField fieldE01GRSBAL = null;
  private DecimalField fieldE01UNCBAL = null;
  private DecimalField fieldE01NETBAL = null;
  private DecimalField fieldE01HLDAMT = null;
  private DecimalField fieldE01OVDAMT = null;
  private DecimalField fieldE01INTAMT = null;
  private DecimalField fieldE01COMAMT = null;
  private DecimalField fieldE01DDCAMT = null;
  private DecimalField fieldE01AMOAMT = null;
  private DecimalField fieldE01PAGAMT = null;
  private DecimalField fieldE01OPNDT1 = null;
  private DecimalField fieldE01OPNDT2 = null;
  private DecimalField fieldE01OPNDT3 = null;
  private DecimalField fieldE01RUNDT1 = null;
  private DecimalField fieldE01RUNDT2 = null;
  private DecimalField fieldE01RUNDT3 = null;
  private CharacterField fieldE01NARRAT = null;
  private CharacterField fieldE01NPGOVD = null;
  private CharacterField fieldE01NPGINT = null;
  private CharacterField fieldE01PAGOPC = null;
  private CharacterField fieldE01PAGCON = null;
  private CharacterField fieldE01PAGOBK = null;
  private DecimalField fieldE01PAGOBR = null;
  private CharacterField fieldE01PAGOCY = null;
  private DecimalField fieldE01PAGOGL = null;
  private DecimalField fieldE01PAGOAC = null;
  private DecimalField fieldE01PAGOCN = null;
  private CharacterField fieldE01CANRSN = null;

  /**
  * Constructor for EDD115001Message.
  */
  public EDD115001Message()
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
    recordsize = 499;
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
    fields[9] = fieldE01ACMACC =
    new DecimalField(message, HEADERSIZE + 42, 13, 0, "E01ACMACC");
    fields[10] = fieldE01ACMACD =
    new CharacterField(message, HEADERSIZE + 55, 2, "E01ACMACD");
    fields[11] = fieldE01ACMPRO =
    new CharacterField(message, HEADERSIZE + 57, 4, "E01ACMPRO");
    fields[12] = fieldE01ACMCUN =
    new DecimalField(message, HEADERSIZE + 61, 10, 0, "E01ACMCUN");
    fields[13] = fieldE01CUSNA1 =
    new CharacterField(message, HEADERSIZE + 71, 45, "E01CUSNA1");
    fields[14] = fieldE01ACMBNK =
    new CharacterField(message, HEADERSIZE + 116, 2, "E01ACMBNK");
    fields[15] = fieldE01ACMBRN =
    new DecimalField(message, HEADERSIZE + 118, 4, 0, "E01ACMBRN");
    fields[16] = fieldE01ACMCCY =
    new CharacterField(message, HEADERSIZE + 122, 3, "E01ACMCCY");
    fields[17] = fieldE01ACMGLN =
    new DecimalField(message, HEADERSIZE + 125, 17, 0, "E01ACMGLN");
    fields[18] = fieldE01ACMCCN =
    new DecimalField(message, HEADERSIZE + 142, 9, 0, "E01ACMCCN");
    fields[19] = fieldE01ACMOFC =
    new CharacterField(message, HEADERSIZE + 151, 4, "E01ACMOFC");
    fields[20] = fieldE01DSCOFC =
    new CharacterField(message, HEADERSIZE + 155, 45, "E01DSCOFC");
    fields[21] = fieldE01GRSBAL =
    new DecimalField(message, HEADERSIZE + 200, 17, 2, "E01GRSBAL");
    fields[22] = fieldE01UNCBAL =
    new DecimalField(message, HEADERSIZE + 217, 17, 2, "E01UNCBAL");
    fields[23] = fieldE01NETBAL =
    new DecimalField(message, HEADERSIZE + 234, 17, 2, "E01NETBAL");
    fields[24] = fieldE01HLDAMT =
    new DecimalField(message, HEADERSIZE + 251, 17, 2, "E01HLDAMT");
    fields[25] = fieldE01OVDAMT =
    new DecimalField(message, HEADERSIZE + 268, 17, 2, "E01OVDAMT");
    fields[26] = fieldE01INTAMT =
    new DecimalField(message, HEADERSIZE + 285, 17, 2, "E01INTAMT");
    fields[27] = fieldE01COMAMT =
    new DecimalField(message, HEADERSIZE + 302, 17, 2, "E01COMAMT");
    fields[28] = fieldE01DDCAMT =
    new DecimalField(message, HEADERSIZE + 319, 17, 2, "E01DDCAMT");
    fields[29] = fieldE01AMOAMT =
    new DecimalField(message, HEADERSIZE + 336, 17, 2, "E01AMOAMT");
    fields[30] = fieldE01PAGAMT =
    new DecimalField(message, HEADERSIZE + 353, 17, 2, "E01PAGAMT");
    fields[31] = fieldE01OPNDT1 =
    new DecimalField(message, HEADERSIZE + 370, 3, 0, "E01OPNDT1");
    fields[32] = fieldE01OPNDT2 =
    new DecimalField(message, HEADERSIZE + 373, 3, 0, "E01OPNDT2");
    fields[33] = fieldE01OPNDT3 =
    new DecimalField(message, HEADERSIZE + 376, 3, 0, "E01OPNDT3");
    fields[34] = fieldE01RUNDT1 =
    new DecimalField(message, HEADERSIZE + 379, 3, 0, "E01RUNDT1");
    fields[35] = fieldE01RUNDT2 =
    new DecimalField(message, HEADERSIZE + 382, 3, 0, "E01RUNDT2");
    fields[36] = fieldE01RUNDT3 =
    new DecimalField(message, HEADERSIZE + 385, 3, 0, "E01RUNDT3");
    fields[37] = fieldE01NARRAT =
    new CharacterField(message, HEADERSIZE + 388, 30, "E01NARRAT");
    fields[38] = fieldE01NPGOVD =
    new CharacterField(message, HEADERSIZE + 418, 1, "E01NPGOVD");
    fields[39] = fieldE01NPGINT =
    new CharacterField(message, HEADERSIZE + 419, 1, "E01NPGINT");
    fields[40] = fieldE01PAGOPC =
    new CharacterField(message, HEADERSIZE + 420, 2, "E01PAGOPC");
    fields[41] = fieldE01PAGCON =
    new CharacterField(message, HEADERSIZE + 422, 25, "E01PAGCON");
    fields[42] = fieldE01PAGOBK =
    new CharacterField(message, HEADERSIZE + 447, 2, "E01PAGOBK");
    fields[43] = fieldE01PAGOBR =
    new DecimalField(message, HEADERSIZE + 449, 4, 0, "E01PAGOBR");
    fields[44] = fieldE01PAGOCY =
    new CharacterField(message, HEADERSIZE + 453, 3, "E01PAGOCY");
    fields[45] = fieldE01PAGOGL =
    new DecimalField(message, HEADERSIZE + 456, 17, 0, "E01PAGOGL");
    fields[46] = fieldE01PAGOAC =
    new DecimalField(message, HEADERSIZE + 473, 13, 0, "E01PAGOAC");
    fields[47] = fieldE01PAGOCN =
    new DecimalField(message, HEADERSIZE + 486, 9, 0, "E01PAGOCN");
    fields[48] = fieldE01CANRSN =
    new CharacterField(message, HEADERSIZE + 495, 4, "E01CANRSN");

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
  * Set field E01ACMACC using a String value.
  */
  public void setE01ACMACC(String newvalue)
  {
    fieldE01ACMACC.setString(newvalue);
  }

  /**
  * Get value of field E01ACMACC as a String.
  */
  public String getE01ACMACC()
  {
    return fieldE01ACMACC.getString();
  }

  /**
  * Set numeric field E01ACMACC using a BigDecimal value.
  */
  public void setE01ACMACC(BigDecimal newvalue)
  {
    fieldE01ACMACC.setBigDecimal(newvalue);
  }

  /**
  * Return value of numeric field E01ACMACC as a BigDecimal.
  */
  public BigDecimal getBigDecimalE01ACMACC()
  {
    return fieldE01ACMACC.getBigDecimal();
  }

  /**
  * Set field E01ACMACD using a String value.
  */
  public void setE01ACMACD(String newvalue)
  {
    fieldE01ACMACD.setString(newvalue);
  }

  /**
  * Get value of field E01ACMACD as a String.
  */
  public String getE01ACMACD()
  {
    return fieldE01ACMACD.getString();
  }

  /**
  * Set field E01ACMPRO using a String value.
  */
  public void setE01ACMPRO(String newvalue)
  {
    fieldE01ACMPRO.setString(newvalue);
  }

  /**
  * Get value of field E01ACMPRO as a String.
  */
  public String getE01ACMPRO()
  {
    return fieldE01ACMPRO.getString();
  }

  /**
  * Set field E01ACMCUN using a String value.
  */
  public void setE01ACMCUN(String newvalue)
  {
    fieldE01ACMCUN.setString(newvalue);
  }

  /**
  * Get value of field E01ACMCUN as a String.
  */
  public String getE01ACMCUN()
  {
    return fieldE01ACMCUN.getString();
  }

  /**
  * Set numeric field E01ACMCUN using a BigDecimal value.
  */
  public void setE01ACMCUN(BigDecimal newvalue)
  {
    fieldE01ACMCUN.setBigDecimal(newvalue);
  }

  /**
  * Return value of numeric field E01ACMCUN as a BigDecimal.
  */
  public BigDecimal getBigDecimalE01ACMCUN()
  {
    return fieldE01ACMCUN.getBigDecimal();
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
  * Set field E01ACMBNK using a String value.
  */
  public void setE01ACMBNK(String newvalue)
  {
    fieldE01ACMBNK.setString(newvalue);
  }

  /**
  * Get value of field E01ACMBNK as a String.
  */
  public String getE01ACMBNK()
  {
    return fieldE01ACMBNK.getString();
  }

  /**
  * Set field E01ACMBRN using a String value.
  */
  public void setE01ACMBRN(String newvalue)
  {
    fieldE01ACMBRN.setString(newvalue);
  }

  /**
  * Get value of field E01ACMBRN as a String.
  */
  public String getE01ACMBRN()
  {
    return fieldE01ACMBRN.getString();
  }

  /**
  * Set numeric field E01ACMBRN using a BigDecimal value.
  */
  public void setE01ACMBRN(BigDecimal newvalue)
  {
    fieldE01ACMBRN.setBigDecimal(newvalue);
  }

  /**
  * Return value of numeric field E01ACMBRN as a BigDecimal.
  */
  public BigDecimal getBigDecimalE01ACMBRN()
  {
    return fieldE01ACMBRN.getBigDecimal();
  }

  /**
  * Set field E01ACMCCY using a String value.
  */
  public void setE01ACMCCY(String newvalue)
  {
    fieldE01ACMCCY.setString(newvalue);
  }

  /**
  * Get value of field E01ACMCCY as a String.
  */
  public String getE01ACMCCY()
  {
    return fieldE01ACMCCY.getString();
  }

  /**
  * Set field E01ACMGLN using a String value.
  */
  public void setE01ACMGLN(String newvalue)
  {
    fieldE01ACMGLN.setString(newvalue);
  }

  /**
  * Get value of field E01ACMGLN as a String.
  */
  public String getE01ACMGLN()
  {
    return fieldE01ACMGLN.getString();
  }

  /**
  * Set numeric field E01ACMGLN using a BigDecimal value.
  */
  public void setE01ACMGLN(BigDecimal newvalue)
  {
    fieldE01ACMGLN.setBigDecimal(newvalue);
  }

  /**
  * Return value of numeric field E01ACMGLN as a BigDecimal.
  */
  public BigDecimal getBigDecimalE01ACMGLN()
  {
    return fieldE01ACMGLN.getBigDecimal();
  }

  /**
  * Set field E01ACMCCN using a String value.
  */
  public void setE01ACMCCN(String newvalue)
  {
    fieldE01ACMCCN.setString(newvalue);
  }

  /**
  * Get value of field E01ACMCCN as a String.
  */
  public String getE01ACMCCN()
  {
    return fieldE01ACMCCN.getString();
  }

  /**
  * Set numeric field E01ACMCCN using a BigDecimal value.
  */
  public void setE01ACMCCN(BigDecimal newvalue)
  {
    fieldE01ACMCCN.setBigDecimal(newvalue);
  }

  /**
  * Return value of numeric field E01ACMCCN as a BigDecimal.
  */
  public BigDecimal getBigDecimalE01ACMCCN()
  {
    return fieldE01ACMCCN.getBigDecimal();
  }

  /**
  * Set field E01ACMOFC using a String value.
  */
  public void setE01ACMOFC(String newvalue)
  {
    fieldE01ACMOFC.setString(newvalue);
  }

  /**
  * Get value of field E01ACMOFC as a String.
  */
  public String getE01ACMOFC()
  {
    return fieldE01ACMOFC.getString();
  }

  /**
  * Set field E01DSCOFC using a String value.
  */
  public void setE01DSCOFC(String newvalue)
  {
    fieldE01DSCOFC.setString(newvalue);
  }

  /**
  * Get value of field E01DSCOFC as a String.
  */
  public String getE01DSCOFC()
  {
    return fieldE01DSCOFC.getString();
  }

  /**
  * Set field E01GRSBAL using a String value.
  */
  public void setE01GRSBAL(String newvalue)
  {
    fieldE01GRSBAL.setString(newvalue);
  }

  /**
  * Get value of field E01GRSBAL as a String.
  */
  public String getE01GRSBAL()
  {
    return fieldE01GRSBAL.getString();
  }

  /**
  * Set numeric field E01GRSBAL using a BigDecimal value.
  */
  public void setE01GRSBAL(BigDecimal newvalue)
  {
    fieldE01GRSBAL.setBigDecimal(newvalue);
  }

  /**
  * Return value of numeric field E01GRSBAL as a BigDecimal.
  */
  public BigDecimal getBigDecimalE01GRSBAL()
  {
    return fieldE01GRSBAL.getBigDecimal();
  }

  /**
  * Set field E01UNCBAL using a String value.
  */
  public void setE01UNCBAL(String newvalue)
  {
    fieldE01UNCBAL.setString(newvalue);
  }

  /**
  * Get value of field E01UNCBAL as a String.
  */
  public String getE01UNCBAL()
  {
    return fieldE01UNCBAL.getString();
  }

  /**
  * Set numeric field E01UNCBAL using a BigDecimal value.
  */
  public void setE01UNCBAL(BigDecimal newvalue)
  {
    fieldE01UNCBAL.setBigDecimal(newvalue);
  }

  /**
  * Return value of numeric field E01UNCBAL as a BigDecimal.
  */
  public BigDecimal getBigDecimalE01UNCBAL()
  {
    return fieldE01UNCBAL.getBigDecimal();
  }

  /**
  * Set field E01NETBAL using a String value.
  */
  public void setE01NETBAL(String newvalue)
  {
    fieldE01NETBAL.setString(newvalue);
  }

  /**
  * Get value of field E01NETBAL as a String.
  */
  public String getE01NETBAL()
  {
    return fieldE01NETBAL.getString();
  }

  /**
  * Set numeric field E01NETBAL using a BigDecimal value.
  */
  public void setE01NETBAL(BigDecimal newvalue)
  {
    fieldE01NETBAL.setBigDecimal(newvalue);
  }

  /**
  * Return value of numeric field E01NETBAL as a BigDecimal.
  */
  public BigDecimal getBigDecimalE01NETBAL()
  {
    return fieldE01NETBAL.getBigDecimal();
  }

  /**
  * Set field E01HLDAMT using a String value.
  */
  public void setE01HLDAMT(String newvalue)
  {
    fieldE01HLDAMT.setString(newvalue);
  }

  /**
  * Get value of field E01HLDAMT as a String.
  */
  public String getE01HLDAMT()
  {
    return fieldE01HLDAMT.getString();
  }

  /**
  * Set numeric field E01HLDAMT using a BigDecimal value.
  */
  public void setE01HLDAMT(BigDecimal newvalue)
  {
    fieldE01HLDAMT.setBigDecimal(newvalue);
  }

  /**
  * Return value of numeric field E01HLDAMT as a BigDecimal.
  */
  public BigDecimal getBigDecimalE01HLDAMT()
  {
    return fieldE01HLDAMT.getBigDecimal();
  }

  /**
  * Set field E01OVDAMT using a String value.
  */
  public void setE01OVDAMT(String newvalue)
  {
    fieldE01OVDAMT.setString(newvalue);
  }

  /**
  * Get value of field E01OVDAMT as a String.
  */
  public String getE01OVDAMT()
  {
    return fieldE01OVDAMT.getString();
  }

  /**
  * Set numeric field E01OVDAMT using a BigDecimal value.
  */
  public void setE01OVDAMT(BigDecimal newvalue)
  {
    fieldE01OVDAMT.setBigDecimal(newvalue);
  }

  /**
  * Return value of numeric field E01OVDAMT as a BigDecimal.
  */
  public BigDecimal getBigDecimalE01OVDAMT()
  {
    return fieldE01OVDAMT.getBigDecimal();
  }

  /**
  * Set field E01INTAMT using a String value.
  */
  public void setE01INTAMT(String newvalue)
  {
    fieldE01INTAMT.setString(newvalue);
  }

  /**
  * Get value of field E01INTAMT as a String.
  */
  public String getE01INTAMT()
  {
    return fieldE01INTAMT.getString();
  }

  /**
  * Set numeric field E01INTAMT using a BigDecimal value.
  */
  public void setE01INTAMT(BigDecimal newvalue)
  {
    fieldE01INTAMT.setBigDecimal(newvalue);
  }

  /**
  * Return value of numeric field E01INTAMT as a BigDecimal.
  */
  public BigDecimal getBigDecimalE01INTAMT()
  {
    return fieldE01INTAMT.getBigDecimal();
  }

  /**
  * Set field E01COMAMT using a String value.
  */
  public void setE01COMAMT(String newvalue)
  {
    fieldE01COMAMT.setString(newvalue);
  }

  /**
  * Get value of field E01COMAMT as a String.
  */
  public String getE01COMAMT()
  {
    return fieldE01COMAMT.getString();
  }

  /**
  * Set numeric field E01COMAMT using a BigDecimal value.
  */
  public void setE01COMAMT(BigDecimal newvalue)
  {
    fieldE01COMAMT.setBigDecimal(newvalue);
  }

  /**
  * Return value of numeric field E01COMAMT as a BigDecimal.
  */
  public BigDecimal getBigDecimalE01COMAMT()
  {
    return fieldE01COMAMT.getBigDecimal();
  }

  /**
  * Set field E01DDCAMT using a String value.
  */
  public void setE01DDCAMT(String newvalue)
  {
    fieldE01DDCAMT.setString(newvalue);
  }

  /**
  * Get value of field E01DDCAMT as a String.
  */
  public String getE01DDCAMT()
  {
    return fieldE01DDCAMT.getString();
  }

  /**
  * Set numeric field E01DDCAMT using a BigDecimal value.
  */
  public void setE01DDCAMT(BigDecimal newvalue)
  {
    fieldE01DDCAMT.setBigDecimal(newvalue);
  }

  /**
  * Return value of numeric field E01DDCAMT as a BigDecimal.
  */
  public BigDecimal getBigDecimalE01DDCAMT()
  {
    return fieldE01DDCAMT.getBigDecimal();
  }

  /**
  * Set field E01AMOAMT using a String value.
  */
  public void setE01AMOAMT(String newvalue)
  {
    fieldE01AMOAMT.setString(newvalue);
  }

  /**
  * Get value of field E01AMOAMT as a String.
  */
  public String getE01AMOAMT()
  {
    return fieldE01AMOAMT.getString();
  }

  /**
  * Set numeric field E01AMOAMT using a BigDecimal value.
  */
  public void setE01AMOAMT(BigDecimal newvalue)
  {
    fieldE01AMOAMT.setBigDecimal(newvalue);
  }

  /**
  * Return value of numeric field E01AMOAMT as a BigDecimal.
  */
  public BigDecimal getBigDecimalE01AMOAMT()
  {
    return fieldE01AMOAMT.getBigDecimal();
  }

  /**
  * Set field E01PAGAMT using a String value.
  */
  public void setE01PAGAMT(String newvalue)
  {
    fieldE01PAGAMT.setString(newvalue);
  }

  /**
  * Get value of field E01PAGAMT as a String.
  */
  public String getE01PAGAMT()
  {
    return fieldE01PAGAMT.getString();
  }

  /**
  * Set numeric field E01PAGAMT using a BigDecimal value.
  */
  public void setE01PAGAMT(BigDecimal newvalue)
  {
    fieldE01PAGAMT.setBigDecimal(newvalue);
  }

  /**
  * Return value of numeric field E01PAGAMT as a BigDecimal.
  */
  public BigDecimal getBigDecimalE01PAGAMT()
  {
    return fieldE01PAGAMT.getBigDecimal();
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
  * Set field E01RUNDT1 using a String value.
  */
  public void setE01RUNDT1(String newvalue)
  {
    fieldE01RUNDT1.setString(newvalue);
  }

  /**
  * Get value of field E01RUNDT1 as a String.
  */
  public String getE01RUNDT1()
  {
    return fieldE01RUNDT1.getString();
  }

  /**
  * Set numeric field E01RUNDT1 using a BigDecimal value.
  */
  public void setE01RUNDT1(BigDecimal newvalue)
  {
    fieldE01RUNDT1.setBigDecimal(newvalue);
  }

  /**
  * Return value of numeric field E01RUNDT1 as a BigDecimal.
  */
  public BigDecimal getBigDecimalE01RUNDT1()
  {
    return fieldE01RUNDT1.getBigDecimal();
  }

  /**
  * Set field E01RUNDT2 using a String value.
  */
  public void setE01RUNDT2(String newvalue)
  {
    fieldE01RUNDT2.setString(newvalue);
  }

  /**
  * Get value of field E01RUNDT2 as a String.
  */
  public String getE01RUNDT2()
  {
    return fieldE01RUNDT2.getString();
  }

  /**
  * Set numeric field E01RUNDT2 using a BigDecimal value.
  */
  public void setE01RUNDT2(BigDecimal newvalue)
  {
    fieldE01RUNDT2.setBigDecimal(newvalue);
  }

  /**
  * Return value of numeric field E01RUNDT2 as a BigDecimal.
  */
  public BigDecimal getBigDecimalE01RUNDT2()
  {
    return fieldE01RUNDT2.getBigDecimal();
  }

  /**
  * Set field E01RUNDT3 using a String value.
  */
  public void setE01RUNDT3(String newvalue)
  {
    fieldE01RUNDT3.setString(newvalue);
  }

  /**
  * Get value of field E01RUNDT3 as a String.
  */
  public String getE01RUNDT3()
  {
    return fieldE01RUNDT3.getString();
  }

  /**
  * Set numeric field E01RUNDT3 using a BigDecimal value.
  */
  public void setE01RUNDT3(BigDecimal newvalue)
  {
    fieldE01RUNDT3.setBigDecimal(newvalue);
  }

  /**
  * Return value of numeric field E01RUNDT3 as a BigDecimal.
  */
  public BigDecimal getBigDecimalE01RUNDT3()
  {
    return fieldE01RUNDT3.getBigDecimal();
  }

  /**
  * Set field E01NARRAT using a String value.
  */
  public void setE01NARRAT(String newvalue)
  {
    fieldE01NARRAT.setString(newvalue);
  }

  /**
  * Get value of field E01NARRAT as a String.
  */
  public String getE01NARRAT()
  {
    return fieldE01NARRAT.getString();
  }

  /**
  * Set field E01NPGOVD using a String value.
  */
  public void setE01NPGOVD(String newvalue)
  {
    fieldE01NPGOVD.setString(newvalue);
  }

  /**
  * Get value of field E01NPGOVD as a String.
  */
  public String getE01NPGOVD()
  {
    return fieldE01NPGOVD.getString();
  }

  /**
  * Set field E01NPGINT using a String value.
  */
  public void setE01NPGINT(String newvalue)
  {
    fieldE01NPGINT.setString(newvalue);
  }

  /**
  * Get value of field E01NPGINT as a String.
  */
  public String getE01NPGINT()
  {
    return fieldE01NPGINT.getString();
  }

  /**
  * Set field E01PAGOPC using a String value.
  */
  public void setE01PAGOPC(String newvalue)
  {
    fieldE01PAGOPC.setString(newvalue);
  }

  /**
  * Get value of field E01PAGOPC as a String.
  */
  public String getE01PAGOPC()
  {
    return fieldE01PAGOPC.getString();
  }

  /**
  * Set field E01PAGCON using a String value.
  */
  public void setE01PAGCON(String newvalue)
  {
    fieldE01PAGCON.setString(newvalue);
  }

  /**
  * Get value of field E01PAGCON as a String.
  */
  public String getE01PAGCON()
  {
    return fieldE01PAGCON.getString();
  }

  /**
  * Set field E01PAGOBK using a String value.
  */
  public void setE01PAGOBK(String newvalue)
  {
    fieldE01PAGOBK.setString(newvalue);
  }

  /**
  * Get value of field E01PAGOBK as a String.
  */
  public String getE01PAGOBK()
  {
    return fieldE01PAGOBK.getString();
  }

  /**
  * Set field E01PAGOBR using a String value.
  */
  public void setE01PAGOBR(String newvalue)
  {
    fieldE01PAGOBR.setString(newvalue);
  }

  /**
  * Get value of field E01PAGOBR as a String.
  */
  public String getE01PAGOBR()
  {
    return fieldE01PAGOBR.getString();
  }

  /**
  * Set numeric field E01PAGOBR using a BigDecimal value.
  */
  public void setE01PAGOBR(BigDecimal newvalue)
  {
    fieldE01PAGOBR.setBigDecimal(newvalue);
  }

  /**
  * Return value of numeric field E01PAGOBR as a BigDecimal.
  */
  public BigDecimal getBigDecimalE01PAGOBR()
  {
    return fieldE01PAGOBR.getBigDecimal();
  }

  /**
  * Set field E01PAGOCY using a String value.
  */
  public void setE01PAGOCY(String newvalue)
  {
    fieldE01PAGOCY.setString(newvalue);
  }

  /**
  * Get value of field E01PAGOCY as a String.
  */
  public String getE01PAGOCY()
  {
    return fieldE01PAGOCY.getString();
  }

  /**
  * Set field E01PAGOGL using a String value.
  */
  public void setE01PAGOGL(String newvalue)
  {
    fieldE01PAGOGL.setString(newvalue);
  }

  /**
  * Get value of field E01PAGOGL as a String.
  */
  public String getE01PAGOGL()
  {
    return fieldE01PAGOGL.getString();
  }

  /**
  * Set numeric field E01PAGOGL using a BigDecimal value.
  */
  public void setE01PAGOGL(BigDecimal newvalue)
  {
    fieldE01PAGOGL.setBigDecimal(newvalue);
  }

  /**
  * Return value of numeric field E01PAGOGL as a BigDecimal.
  */
  public BigDecimal getBigDecimalE01PAGOGL()
  {
    return fieldE01PAGOGL.getBigDecimal();
  }

  /**
  * Set field E01PAGOAC using a String value.
  */
  public void setE01PAGOAC(String newvalue)
  {
    fieldE01PAGOAC.setString(newvalue);
  }

  /**
  * Get value of field E01PAGOAC as a String.
  */
  public String getE01PAGOAC()
  {
    return fieldE01PAGOAC.getString();
  }

  /**
  * Set numeric field E01PAGOAC using a BigDecimal value.
  */
  public void setE01PAGOAC(BigDecimal newvalue)
  {
    fieldE01PAGOAC.setBigDecimal(newvalue);
  }

  /**
  * Return value of numeric field E01PAGOAC as a BigDecimal.
  */
  public BigDecimal getBigDecimalE01PAGOAC()
  {
    return fieldE01PAGOAC.getBigDecimal();
  }

  /**
  * Set field E01PAGOCN using a String value.
  */
  public void setE01PAGOCN(String newvalue)
  {
    fieldE01PAGOCN.setString(newvalue);
  }

  /**
  * Get value of field E01PAGOCN as a String.
  */
  public String getE01PAGOCN()
  {
    return fieldE01PAGOCN.getString();
  }

  /**
  * Set numeric field E01PAGOCN using a BigDecimal value.
  */
  public void setE01PAGOCN(BigDecimal newvalue)
  {
    fieldE01PAGOCN.setBigDecimal(newvalue);
  }

  /**
  * Return value of numeric field E01PAGOCN as a BigDecimal.
  */
  public BigDecimal getBigDecimalE01PAGOCN()
  {
    return fieldE01PAGOCN.getBigDecimal();
  }

  /**
  * Set field E01CANRSN using a String value.
  */
  public void setE01CANRSN(String newvalue)
  {
    fieldE01CANRSN.setString(newvalue);
  }

  /**
  * Get value of field E01CANRSN as a String.
  */
  public String getE01CANRSN()
  {
    return fieldE01CANRSN.getString();
  }

}
