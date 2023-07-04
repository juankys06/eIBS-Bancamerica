package datapro.eibs.beans;

import datapro.eibs.sockets.*;
import java.io.*;
import java.math.*;

import java.util.*;

/**
* Class generated by AS/400 CRTCLASS command from ELC525001 physical file definition.
* 
* File level identifier is 1030121191936.
* Record format level identifier is 40E92630DDB81.
*/

public class ELC525001Message extends MessageRecord
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
                                     "E01LCMACC",
                                     "E01LCMACD",
                                     "E01LCMCUN",
                                     "E01CUSNA1",
                                     "E01LCMPRO",
                                     "E01LCMBNK",
                                     "E01LCMBRN",
                                     "E01LCMCCY",
                                     "E01LCMGLN",
                                     "E01LCMCCN",
                                     "E01LCMAMT",
                                     "E01LCMUBT",
                                     "E01LCMUSR",
                                     "E01LCMRMK",
                                     "E01FLGBUS",
                                     "E01FLGTYP",
                                     "E01FLGIBF"
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
                                   "E01LCMACC",
                                   "E01LCMACD",
                                   "E01LCMCUN",
                                   "E01CUSNA1",
                                   "E01LCMPRO",
                                   "E01LCMBNK",
                                   "E01LCMBRN",
                                   "E01LCMCCY",
                                   "E01LCMGLN",
                                   "E01LCMCCN",
                                   "E01LCMAMT",
                                   "E01LCMUBT",
                                   "E01LCMUSR",
                                   "E01LCMRMK",
                                   "E01FLGBUS",
                                   "E01FLGTYP",
                                   "E01FLGIBF"
                                 };
  final static String fid = "1030121191936";
  final static String rid = "40E92630DDB81";
  final static String fmtname = "ELC525001";
  final int FIELDCOUNT = 26;
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
  private DecimalField fieldE01LCMACC = null;
  private CharacterField fieldE01LCMACD = null;
  private DecimalField fieldE01LCMCUN = null;
  private CharacterField fieldE01CUSNA1 = null;
  private CharacterField fieldE01LCMPRO = null;
  private CharacterField fieldE01LCMBNK = null;
  private DecimalField fieldE01LCMBRN = null;
  private CharacterField fieldE01LCMCCY = null;
  private DecimalField fieldE01LCMGLN = null;
  private DecimalField fieldE01LCMCCN = null;
  private DecimalField fieldE01LCMAMT = null;
  private DecimalField fieldE01LCMUBT = null;
  private CharacterField fieldE01LCMUSR = null;
  private CharacterField fieldE01LCMRMK = null;
  private CharacterField fieldE01FLGBUS = null;
  private CharacterField fieldE01FLGTYP = null;
  private CharacterField fieldE01FLGIBF = null;

  /**
  * Constructor for ELC525001Message.
  */
  public ELC525001Message()
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
    recordsize = 209;
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
    fields[9] = fieldE01LCMACC =
    new DecimalField(message, HEADERSIZE + 42, 13, 0, "E01LCMACC");
    fields[10] = fieldE01LCMACD =
    new CharacterField(message, HEADERSIZE + 55, 2, "E01LCMACD");
    fields[11] = fieldE01LCMCUN =
    new DecimalField(message, HEADERSIZE + 57, 10, 0, "E01LCMCUN");
    fields[12] = fieldE01CUSNA1 =
    new CharacterField(message, HEADERSIZE + 67, 45, "E01CUSNA1");
    fields[13] = fieldE01LCMPRO =
    new CharacterField(message, HEADERSIZE + 112, 4, "E01LCMPRO");
    fields[14] = fieldE01LCMBNK =
    new CharacterField(message, HEADERSIZE + 116, 2, "E01LCMBNK");
    fields[15] = fieldE01LCMBRN =
    new DecimalField(message, HEADERSIZE + 118, 4, 0, "E01LCMBRN");
    fields[16] = fieldE01LCMCCY =
    new CharacterField(message, HEADERSIZE + 122, 3, "E01LCMCCY");
    fields[17] = fieldE01LCMGLN =
    new DecimalField(message, HEADERSIZE + 125, 17, 0, "E01LCMGLN");
    fields[18] = fieldE01LCMCCN =
    new DecimalField(message, HEADERSIZE + 142, 9, 0, "E01LCMCCN");
    fields[19] = fieldE01LCMAMT =
    new DecimalField(message, HEADERSIZE + 151, 17, 2, "E01LCMAMT");
    fields[20] = fieldE01LCMUBT =
    new DecimalField(message, HEADERSIZE + 168, 5, 0, "E01LCMUBT");
    fields[21] = fieldE01LCMUSR =
    new CharacterField(message, HEADERSIZE + 173, 10, "E01LCMUSR");
    fields[22] = fieldE01LCMRMK =
    new CharacterField(message, HEADERSIZE + 183, 20, "E01LCMRMK");
    fields[23] = fieldE01FLGBUS =
    new CharacterField(message, HEADERSIZE + 203, 4, "E01FLGBUS");
    fields[24] = fieldE01FLGTYP =
    new CharacterField(message, HEADERSIZE + 207, 1, "E01FLGTYP");
    fields[25] = fieldE01FLGIBF =
    new CharacterField(message, HEADERSIZE + 208, 1, "E01FLGIBF");

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
  * Set field E01LCMACC using a String value.
  */
  public void setE01LCMACC(String newvalue)
  {
    fieldE01LCMACC.setString(newvalue);
  }

  /**
  * Get value of field E01LCMACC as a String.
  */
  public String getE01LCMACC()
  {
    return fieldE01LCMACC.getString();
  }

  /**
  * Set numeric field E01LCMACC using a BigDecimal value.
  */
  public void setE01LCMACC(BigDecimal newvalue)
  {
    fieldE01LCMACC.setBigDecimal(newvalue);
  }

  /**
  * Return value of numeric field E01LCMACC as a BigDecimal.
  */
  public BigDecimal getBigDecimalE01LCMACC()
  {
    return fieldE01LCMACC.getBigDecimal();
  }

  /**
  * Set field E01LCMACD using a String value.
  */
  public void setE01LCMACD(String newvalue)
  {
    fieldE01LCMACD.setString(newvalue);
  }

  /**
  * Get value of field E01LCMACD as a String.
  */
  public String getE01LCMACD()
  {
    return fieldE01LCMACD.getString();
  }

  /**
  * Set field E01LCMCUN using a String value.
  */
  public void setE01LCMCUN(String newvalue)
  {
    fieldE01LCMCUN.setString(newvalue);
  }

  /**
  * Get value of field E01LCMCUN as a String.
  */
  public String getE01LCMCUN()
  {
    return fieldE01LCMCUN.getString();
  }

  /**
  * Set numeric field E01LCMCUN using a BigDecimal value.
  */
  public void setE01LCMCUN(BigDecimal newvalue)
  {
    fieldE01LCMCUN.setBigDecimal(newvalue);
  }

  /**
  * Return value of numeric field E01LCMCUN as a BigDecimal.
  */
  public BigDecimal getBigDecimalE01LCMCUN()
  {
    return fieldE01LCMCUN.getBigDecimal();
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
  * Set field E01LCMPRO using a String value.
  */
  public void setE01LCMPRO(String newvalue)
  {
    fieldE01LCMPRO.setString(newvalue);
  }

  /**
  * Get value of field E01LCMPRO as a String.
  */
  public String getE01LCMPRO()
  {
    return fieldE01LCMPRO.getString();
  }

  /**
  * Set field E01LCMBNK using a String value.
  */
  public void setE01LCMBNK(String newvalue)
  {
    fieldE01LCMBNK.setString(newvalue);
  }

  /**
  * Get value of field E01LCMBNK as a String.
  */
  public String getE01LCMBNK()
  {
    return fieldE01LCMBNK.getString();
  }

  /**
  * Set field E01LCMBRN using a String value.
  */
  public void setE01LCMBRN(String newvalue)
  {
    fieldE01LCMBRN.setString(newvalue);
  }

  /**
  * Get value of field E01LCMBRN as a String.
  */
  public String getE01LCMBRN()
  {
    return fieldE01LCMBRN.getString();
  }

  /**
  * Set numeric field E01LCMBRN using a BigDecimal value.
  */
  public void setE01LCMBRN(BigDecimal newvalue)
  {
    fieldE01LCMBRN.setBigDecimal(newvalue);
  }

  /**
  * Return value of numeric field E01LCMBRN as a BigDecimal.
  */
  public BigDecimal getBigDecimalE01LCMBRN()
  {
    return fieldE01LCMBRN.getBigDecimal();
  }

  /**
  * Set field E01LCMCCY using a String value.
  */
  public void setE01LCMCCY(String newvalue)
  {
    fieldE01LCMCCY.setString(newvalue);
  }

  /**
  * Get value of field E01LCMCCY as a String.
  */
  public String getE01LCMCCY()
  {
    return fieldE01LCMCCY.getString();
  }

  /**
  * Set field E01LCMGLN using a String value.
  */
  public void setE01LCMGLN(String newvalue)
  {
    fieldE01LCMGLN.setString(newvalue);
  }

  /**
  * Get value of field E01LCMGLN as a String.
  */
  public String getE01LCMGLN()
  {
    return fieldE01LCMGLN.getString();
  }

  /**
  * Set numeric field E01LCMGLN using a BigDecimal value.
  */
  public void setE01LCMGLN(BigDecimal newvalue)
  {
    fieldE01LCMGLN.setBigDecimal(newvalue);
  }

  /**
  * Return value of numeric field E01LCMGLN as a BigDecimal.
  */
  public BigDecimal getBigDecimalE01LCMGLN()
  {
    return fieldE01LCMGLN.getBigDecimal();
  }

  /**
  * Set field E01LCMCCN using a String value.
  */
  public void setE01LCMCCN(String newvalue)
  {
    fieldE01LCMCCN.setString(newvalue);
  }

  /**
  * Get value of field E01LCMCCN as a String.
  */
  public String getE01LCMCCN()
  {
    return fieldE01LCMCCN.getString();
  }

  /**
  * Set numeric field E01LCMCCN using a BigDecimal value.
  */
  public void setE01LCMCCN(BigDecimal newvalue)
  {
    fieldE01LCMCCN.setBigDecimal(newvalue);
  }

  /**
  * Return value of numeric field E01LCMCCN as a BigDecimal.
  */
  public BigDecimal getBigDecimalE01LCMCCN()
  {
    return fieldE01LCMCCN.getBigDecimal();
  }

  /**
  * Set field E01LCMAMT using a String value.
  */
  public void setE01LCMAMT(String newvalue)
  {
    fieldE01LCMAMT.setString(newvalue);
  }

  /**
  * Get value of field E01LCMAMT as a String.
  */
  public String getE01LCMAMT()
  {
    return fieldE01LCMAMT.getString();
  }

  /**
  * Set numeric field E01LCMAMT using a BigDecimal value.
  */
  public void setE01LCMAMT(BigDecimal newvalue)
  {
    fieldE01LCMAMT.setBigDecimal(newvalue);
  }

  /**
  * Return value of numeric field E01LCMAMT as a BigDecimal.
  */
  public BigDecimal getBigDecimalE01LCMAMT()
  {
    return fieldE01LCMAMT.getBigDecimal();
  }

  /**
  * Set field E01LCMUBT using a String value.
  */
  public void setE01LCMUBT(String newvalue)
  {
    fieldE01LCMUBT.setString(newvalue);
  }

  /**
  * Get value of field E01LCMUBT as a String.
  */
  public String getE01LCMUBT()
  {
    return fieldE01LCMUBT.getString();
  }

  /**
  * Set numeric field E01LCMUBT using a BigDecimal value.
  */
  public void setE01LCMUBT(BigDecimal newvalue)
  {
    fieldE01LCMUBT.setBigDecimal(newvalue);
  }

  /**
  * Return value of numeric field E01LCMUBT as a BigDecimal.
  */
  public BigDecimal getBigDecimalE01LCMUBT()
  {
    return fieldE01LCMUBT.getBigDecimal();
  }

  /**
  * Set field E01LCMUSR using a String value.
  */
  public void setE01LCMUSR(String newvalue)
  {
    fieldE01LCMUSR.setString(newvalue);
  }

  /**
  * Get value of field E01LCMUSR as a String.
  */
  public String getE01LCMUSR()
  {
    return fieldE01LCMUSR.getString();
  }

  /**
  * Set field E01LCMRMK using a String value.
  */
  public void setE01LCMRMK(String newvalue)
  {
    fieldE01LCMRMK.setString(newvalue);
  }

  /**
  * Get value of field E01LCMRMK as a String.
  */
  public String getE01LCMRMK()
  {
    return fieldE01LCMRMK.getString();
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
