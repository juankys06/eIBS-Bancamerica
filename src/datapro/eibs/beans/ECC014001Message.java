package datapro.eibs.beans; 

import datapro.eibs.sockets.*;
import java.io.*;
import java.math.*;

import java.util.*;

/**
* Class generated by AS/400 CRTCLASS command from ECC014001 physical file definition.
* 
* File level identifier is 1050519114945.
* Record format level identifier is 4D3A33B2B059F.
*/

public class ECC014001Message extends MessageRecord
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
                                     "E01CCMACC",
                                     "E01CCMACD",
                                     "E01CCMCUN",
                                     "E01CUSNA1",
                                     "E01CCMPRO",
                                     "E01CCMBNK",
                                     "E01CCMBRN",
                                     "E01CCMCCY",
                                     "E01CCMGLN",
                                     "E01CCMCCN",
                                     "E01CCMAMT",
                                     "E01CCMUBT",
                                     "E01CCMUSR",
                                     "E01CCMRMK",
                                     "E01CCMOPM",
                                     "E01CCMOPD",
                                     "E01CCMOPY",
                                     "E01CCMEXM",
                                     "E01CCMEXD",
                                     "E01CCMEXY",
                                     "E01CCMNUM"
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
                                   "E01CCMACC",
                                   "E01CCMACD",
                                   "E01CCMCUN",
                                   "E01CUSNA1",
                                   "E01CCMPRO",
                                   "E01CCMBNK",
                                   "E01CCMBRN",
                                   "E01CCMCCY",
                                   "E01CCMGLN",
                                   "E01CCMCCN",
                                   "E01CCMAMT",
                                   "E01CCMUBT",
                                   "E01CCMUSR",
                                   "E01CCMRMK",
                                   "E01CCMOPM",
                                   "E01CCMOPD",
                                   "E01CCMOPY",
                                   "E01CCMEXM",
                                   "E01CCMEXD",
                                   "E01CCMEXY",
                                   "E01CCMNUM"
                                 };
  final static String fid = "1050519114945";
  final static String rid = "4D3A33B2B059F";
  final static String fmtname = "ECC014001";
  final int FIELDCOUNT = 30;
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
  private DecimalField fieldE01CCMACC = null;
  private CharacterField fieldE01CCMACD = null;
  private DecimalField fieldE01CCMCUN = null;
  private CharacterField fieldE01CUSNA1 = null;
  private CharacterField fieldE01CCMPRO = null;
  private CharacterField fieldE01CCMBNK = null;
  private DecimalField fieldE01CCMBRN = null;
  private CharacterField fieldE01CCMCCY = null;
  private DecimalField fieldE01CCMGLN = null;
  private DecimalField fieldE01CCMCCN = null;
  private DecimalField fieldE01CCMAMT = null;
  private DecimalField fieldE01CCMUBT = null;
  private CharacterField fieldE01CCMUSR = null;
  private CharacterField fieldE01CCMRMK = null;
  private DecimalField fieldE01CCMOPM = null;
  private DecimalField fieldE01CCMOPD = null;
  private DecimalField fieldE01CCMOPY = null;
  private DecimalField fieldE01CCMEXM = null;
  private DecimalField fieldE01CCMEXD = null;
  private DecimalField fieldE01CCMEXY = null;
  private CharacterField fieldE01CCMNUM = null;

  /**
  * Constructor for ECC014001Message.
  */
  public ECC014001Message()
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
    recordsize = 245;
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
    fields[9] = fieldE01CCMACC =
    new DecimalField(message, HEADERSIZE + 42, 13, 0, "E01CCMACC");
    fields[10] = fieldE01CCMACD =
    new CharacterField(message, HEADERSIZE + 55, 2, "E01CCMACD");
    fields[11] = fieldE01CCMCUN =
    new DecimalField(message, HEADERSIZE + 57, 10, 0, "E01CCMCUN");
    fields[12] = fieldE01CUSNA1 =
    new CharacterField(message, HEADERSIZE + 67, 45, "E01CUSNA1");
    fields[13] = fieldE01CCMPRO =
    new CharacterField(message, HEADERSIZE + 112, 4, "E01CCMPRO");
    fields[14] = fieldE01CCMBNK =
    new CharacterField(message, HEADERSIZE + 116, 2, "E01CCMBNK");
    fields[15] = fieldE01CCMBRN =
    new DecimalField(message, HEADERSIZE + 118, 4, 0, "E01CCMBRN");
    fields[16] = fieldE01CCMCCY =
    new CharacterField(message, HEADERSIZE + 122, 3, "E01CCMCCY");
    fields[17] = fieldE01CCMGLN =
    new DecimalField(message, HEADERSIZE + 125, 17, 0, "E01CCMGLN");
    fields[18] = fieldE01CCMCCN =
    new DecimalField(message, HEADERSIZE + 142, 9, 0, "E01CCMCCN");
    fields[19] = fieldE01CCMAMT =
    new DecimalField(message, HEADERSIZE + 151, 17, 2, "E01CCMAMT");
    fields[20] = fieldE01CCMUBT =
    new DecimalField(message, HEADERSIZE + 168, 5, 0, "E01CCMUBT");
    fields[21] = fieldE01CCMUSR =
    new CharacterField(message, HEADERSIZE + 173, 10, "E01CCMUSR");
    fields[22] = fieldE01CCMRMK =
    new CharacterField(message, HEADERSIZE + 183, 20, "E01CCMRMK");
    fields[23] = fieldE01CCMOPM =
    new DecimalField(message, HEADERSIZE + 203, 3, 0, "E01CCMOPM");
    fields[24] = fieldE01CCMOPD =
    new DecimalField(message, HEADERSIZE + 206, 3, 0, "E01CCMOPD");
    fields[25] = fieldE01CCMOPY =
    new DecimalField(message, HEADERSIZE + 209, 5, 0, "E01CCMOPY");
    fields[26] = fieldE01CCMEXM =
    new DecimalField(message, HEADERSIZE + 214, 3, 0, "E01CCMEXM");
    fields[27] = fieldE01CCMEXD =
    new DecimalField(message, HEADERSIZE + 217, 3, 0, "E01CCMEXD");
    fields[28] = fieldE01CCMEXY =
    new DecimalField(message, HEADERSIZE + 220, 5, 0, "E01CCMEXY");
    fields[29] = fieldE01CCMNUM =
    new CharacterField(message, HEADERSIZE + 225, 20, "E01CCMNUM");

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
  * Set field E01CCMACC using a String value.
  */
  public void setE01CCMACC(String newvalue)
  {
    fieldE01CCMACC.setString(newvalue);
  }

  /**
  * Get value of field E01CCMACC as a String.
  */
  public String getE01CCMACC()
  {
    return fieldE01CCMACC.getString();
  }

  /**
  * Set numeric field E01CCMACC using a BigDecimal value.
  */
  public void setE01CCMACC(BigDecimal newvalue)
  {
    fieldE01CCMACC.setBigDecimal(newvalue);
  }

  /**
  * Return value of numeric field E01CCMACC as a BigDecimal.
  */
  public BigDecimal getBigDecimalE01CCMACC()
  {
    return fieldE01CCMACC.getBigDecimal();
  }

  /**
  * Set field E01CCMACD using a String value.
  */
  public void setE01CCMACD(String newvalue)
  {
    fieldE01CCMACD.setString(newvalue);
  }

  /**
  * Get value of field E01CCMACD as a String.
  */
  public String getE01CCMACD()
  {
    return fieldE01CCMACD.getString();
  }

  /**
  * Set field E01CCMCUN using a String value.
  */
  public void setE01CCMCUN(String newvalue)
  {
    fieldE01CCMCUN.setString(newvalue);
  }

  /**
  * Get value of field E01CCMCUN as a String.
  */
  public String getE01CCMCUN()
  {
    return fieldE01CCMCUN.getString();
  }

  /**
  * Set numeric field E01CCMCUN using a BigDecimal value.
  */
  public void setE01CCMCUN(BigDecimal newvalue)
  {
    fieldE01CCMCUN.setBigDecimal(newvalue);
  }

  /**
  * Return value of numeric field E01CCMCUN as a BigDecimal.
  */
  public BigDecimal getBigDecimalE01CCMCUN()
  {
    return fieldE01CCMCUN.getBigDecimal();
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
  * Set field E01CCMPRO using a String value.
  */
  public void setE01CCMPRO(String newvalue)
  {
    fieldE01CCMPRO.setString(newvalue);
  }

  /**
  * Get value of field E01CCMPRO as a String.
  */
  public String getE01CCMPRO()
  {
    return fieldE01CCMPRO.getString();
  }

  /**
  * Set field E01CCMBNK using a String value.
  */
  public void setE01CCMBNK(String newvalue)
  {
    fieldE01CCMBNK.setString(newvalue);
  }

  /**
  * Get value of field E01CCMBNK as a String.
  */
  public String getE01CCMBNK()
  {
    return fieldE01CCMBNK.getString();
  }

  /**
  * Set field E01CCMBRN using a String value.
  */
  public void setE01CCMBRN(String newvalue)
  {
    fieldE01CCMBRN.setString(newvalue);
  }

  /**
  * Get value of field E01CCMBRN as a String.
  */
  public String getE01CCMBRN()
  {
    return fieldE01CCMBRN.getString();
  }

  /**
  * Set numeric field E01CCMBRN using a BigDecimal value.
  */
  public void setE01CCMBRN(BigDecimal newvalue)
  {
    fieldE01CCMBRN.setBigDecimal(newvalue);
  }

  /**
  * Return value of numeric field E01CCMBRN as a BigDecimal.
  */
  public BigDecimal getBigDecimalE01CCMBRN()
  {
    return fieldE01CCMBRN.getBigDecimal();
  }

  /**
  * Set field E01CCMCCY using a String value.
  */
  public void setE01CCMCCY(String newvalue)
  {
    fieldE01CCMCCY.setString(newvalue);
  }

  /**
  * Get value of field E01CCMCCY as a String.
  */
  public String getE01CCMCCY()
  {
    return fieldE01CCMCCY.getString();
  }

  /**
  * Set field E01CCMGLN using a String value.
  */
  public void setE01CCMGLN(String newvalue)
  {
    fieldE01CCMGLN.setString(newvalue);
  }

  /**
  * Get value of field E01CCMGLN as a String.
  */
  public String getE01CCMGLN()
  {
    return fieldE01CCMGLN.getString();
  }

  /**
  * Set numeric field E01CCMGLN using a BigDecimal value.
  */
  public void setE01CCMGLN(BigDecimal newvalue)
  {
    fieldE01CCMGLN.setBigDecimal(newvalue);
  }

  /**
  * Return value of numeric field E01CCMGLN as a BigDecimal.
  */
  public BigDecimal getBigDecimalE01CCMGLN()
  {
    return fieldE01CCMGLN.getBigDecimal();
  }

  /**
  * Set field E01CCMCCN using a String value.
  */
  public void setE01CCMCCN(String newvalue)
  {
    fieldE01CCMCCN.setString(newvalue);
  }

  /**
  * Get value of field E01CCMCCN as a String.
  */
  public String getE01CCMCCN()
  {
    return fieldE01CCMCCN.getString();
  }

  /**
  * Set numeric field E01CCMCCN using a BigDecimal value.
  */
  public void setE01CCMCCN(BigDecimal newvalue)
  {
    fieldE01CCMCCN.setBigDecimal(newvalue);
  }

  /**
  * Return value of numeric field E01CCMCCN as a BigDecimal.
  */
  public BigDecimal getBigDecimalE01CCMCCN()
  {
    return fieldE01CCMCCN.getBigDecimal();
  }

  /**
  * Set field E01CCMAMT using a String value.
  */
  public void setE01CCMAMT(String newvalue)
  {
    fieldE01CCMAMT.setString(newvalue);
  }

  /**
  * Get value of field E01CCMAMT as a String.
  */
  public String getE01CCMAMT()
  {
    return fieldE01CCMAMT.getString();
  }

  /**
  * Set numeric field E01CCMAMT using a BigDecimal value.
  */
  public void setE01CCMAMT(BigDecimal newvalue)
  {
    fieldE01CCMAMT.setBigDecimal(newvalue);
  }

  /**
  * Return value of numeric field E01CCMAMT as a BigDecimal.
  */
  public BigDecimal getBigDecimalE01CCMAMT()
  {
    return fieldE01CCMAMT.getBigDecimal();
  }

  /**
  * Set field E01CCMUBT using a String value.
  */
  public void setE01CCMUBT(String newvalue)
  {
    fieldE01CCMUBT.setString(newvalue);
  }

  /**
  * Get value of field E01CCMUBT as a String.
  */
  public String getE01CCMUBT()
  {
    return fieldE01CCMUBT.getString();
  }

  /**
  * Set numeric field E01CCMUBT using a BigDecimal value.
  */
  public void setE01CCMUBT(BigDecimal newvalue)
  {
    fieldE01CCMUBT.setBigDecimal(newvalue);
  }

  /**
  * Return value of numeric field E01CCMUBT as a BigDecimal.
  */
  public BigDecimal getBigDecimalE01CCMUBT()
  {
    return fieldE01CCMUBT.getBigDecimal();
  }

  /**
  * Set field E01CCMUSR using a String value.
  */
  public void setE01CCMUSR(String newvalue)
  {
    fieldE01CCMUSR.setString(newvalue);
  }

  /**
  * Get value of field E01CCMUSR as a String.
  */
  public String getE01CCMUSR()
  {
    return fieldE01CCMUSR.getString();
  }

  /**
  * Set field E01CCMRMK using a String value.
  */
  public void setE01CCMRMK(String newvalue)
  {
    fieldE01CCMRMK.setString(newvalue);
  }

  /**
  * Get value of field E01CCMRMK as a String.
  */
  public String getE01CCMRMK()
  {
    return fieldE01CCMRMK.getString();
  }

  /**
  * Set field E01CCMOPM using a String value.
  */
  public void setE01CCMOPM(String newvalue)
  {
    fieldE01CCMOPM.setString(newvalue);
  }

  /**
  * Get value of field E01CCMOPM as a String.
  */
  public String getE01CCMOPM()
  {
    return fieldE01CCMOPM.getString();
  }

  /**
  * Set numeric field E01CCMOPM using a BigDecimal value.
  */
  public void setE01CCMOPM(BigDecimal newvalue)
  {
    fieldE01CCMOPM.setBigDecimal(newvalue);
  }

  /**
  * Return value of numeric field E01CCMOPM as a BigDecimal.
  */
  public BigDecimal getBigDecimalE01CCMOPM()
  {
    return fieldE01CCMOPM.getBigDecimal();
  }

  /**
  * Set field E01CCMOPD using a String value.
  */
  public void setE01CCMOPD(String newvalue)
  {
    fieldE01CCMOPD.setString(newvalue);
  }

  /**
  * Get value of field E01CCMOPD as a String.
  */
  public String getE01CCMOPD()
  {
    return fieldE01CCMOPD.getString();
  }

  /**
  * Set numeric field E01CCMOPD using a BigDecimal value.
  */
  public void setE01CCMOPD(BigDecimal newvalue)
  {
    fieldE01CCMOPD.setBigDecimal(newvalue);
  }

  /**
  * Return value of numeric field E01CCMOPD as a BigDecimal.
  */
  public BigDecimal getBigDecimalE01CCMOPD()
  {
    return fieldE01CCMOPD.getBigDecimal();
  }

  /**
  * Set field E01CCMOPY using a String value.
  */
  public void setE01CCMOPY(String newvalue)
  {
    fieldE01CCMOPY.setString(newvalue);
  }

  /**
  * Get value of field E01CCMOPY as a String.
  */
  public String getE01CCMOPY()
  {
    return fieldE01CCMOPY.getString();
  }

  /**
  * Set numeric field E01CCMOPY using a BigDecimal value.
  */
  public void setE01CCMOPY(BigDecimal newvalue)
  {
    fieldE01CCMOPY.setBigDecimal(newvalue);
  }

  /**
  * Return value of numeric field E01CCMOPY as a BigDecimal.
  */
  public BigDecimal getBigDecimalE01CCMOPY()
  {
    return fieldE01CCMOPY.getBigDecimal();
  }

  /**
  * Set field E01CCMEXM using a String value.
  */
  public void setE01CCMEXM(String newvalue)
  {
    fieldE01CCMEXM.setString(newvalue);
  }

  /**
  * Get value of field E01CCMEXM as a String.
  */
  public String getE01CCMEXM()
  {
    return fieldE01CCMEXM.getString();
  }

  /**
  * Set numeric field E01CCMEXM using a BigDecimal value.
  */
  public void setE01CCMEXM(BigDecimal newvalue)
  {
    fieldE01CCMEXM.setBigDecimal(newvalue);
  }

  /**
  * Return value of numeric field E01CCMEXM as a BigDecimal.
  */
  public BigDecimal getBigDecimalE01CCMEXM()
  {
    return fieldE01CCMEXM.getBigDecimal();
  }

  /**
  * Set field E01CCMEXD using a String value.
  */
  public void setE01CCMEXD(String newvalue)
  {
    fieldE01CCMEXD.setString(newvalue);
  }

  /**
  * Get value of field E01CCMEXD as a String.
  */
  public String getE01CCMEXD()
  {
    return fieldE01CCMEXD.getString();
  }

  /**
  * Set numeric field E01CCMEXD using a BigDecimal value.
  */
  public void setE01CCMEXD(BigDecimal newvalue)
  {
    fieldE01CCMEXD.setBigDecimal(newvalue);
  }

  /**
  * Return value of numeric field E01CCMEXD as a BigDecimal.
  */
  public BigDecimal getBigDecimalE01CCMEXD()
  {
    return fieldE01CCMEXD.getBigDecimal();
  }

  /**
  * Set field E01CCMEXY using a String value.
  */
  public void setE01CCMEXY(String newvalue)
  {
    fieldE01CCMEXY.setString(newvalue);
  }

  /**
  * Get value of field E01CCMEXY as a String.
  */
  public String getE01CCMEXY()
  {
    return fieldE01CCMEXY.getString();
  }

  /**
  * Set numeric field E01CCMEXY using a BigDecimal value.
  */
  public void setE01CCMEXY(BigDecimal newvalue)
  {
    fieldE01CCMEXY.setBigDecimal(newvalue);
  }

  /**
  * Return value of numeric field E01CCMEXY as a BigDecimal.
  */
  public BigDecimal getBigDecimalE01CCMEXY()
  {
    return fieldE01CCMEXY.getBigDecimal();
  }

  /**
  * Set field E01CCMNUM using a String value.
  */
  public void setE01CCMNUM(String newvalue)
  {
    fieldE01CCMNUM.setString(newvalue);
  }

  /**
  * Get value of field E01CCMNUM as a String.
  */
  public String getE01CCMNUM()
  {
    return fieldE01CCMNUM.getString();
  }

}