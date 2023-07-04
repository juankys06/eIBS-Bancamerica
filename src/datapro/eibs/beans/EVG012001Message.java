package datapro.eibs.beans;

import datapro.eibs.sockets.*;
import java.io.*;
import java.math.*;

import java.util.*;

/**
* Class generated by AS/400 CRTCLASS command from EVG012001 physical file definition.
* 
* File level identifier is 1040220181646.
* Record format level identifier is 2028BF5F56576.
*/

public class EVG012001Message extends MessageRecord
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
                                     "E01OFMBNK",
                                     "E01OFMBRN",
                                     "E01OFMCCY",
                                     "E01OFMDSC",
                                     "E01OFMCKN",
                                     "E01OFMFTY",
                                     "E01OFMEM1",
                                     "E01OFMEM2",
                                     "E01OFMEM3",
                                     "E01DEBBNK",
                                     "E01DEBBRN",
                                     "E01DEBCCY",
                                     "E01DEBGLN",
                                     "E01DEBACC",
                                     "E01OFMAMT",
                                     "E01OFMBNF",
                                     "E01OFMBN1",
                                     "E01OFMBN2",
                                     "E01OFMAPL",
                                     "E01OFMAP1",
                                     "E01OFMAP2",
                                     "E01OFMCUN",
                                     "E01OFMAD1",
                                     "E01OFMAD2",
                                     "E01OFMAD3",
                                     "E01OFMCO1",
                                     "E01OFMCO2",
                                     "E01OFMCO3",
                                     "E01OFMCLS",
                                     "E01LETAMT",
                                     "E01OFMAPV",
                                     "E01OFMPTH",
                                     "E01OFMCOM",
                                     "E01OFMIVA",
                                     "E01OFMBTH",
                                     "E01NEWCKN",
                                     "E01OFMBID",
                                     "E01OFMAID",
                                     "E01OFMCTY",
                                     "D01OFMCTY",
                                     "E01CREBNK",
                                     "E01CREBRN",
                                     "E01CRECCY",
                                     "E01CREGLN",
                                     "E01CREACC",
                                     "E01CRECCN",
                                     "E01DESCRIP"
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
                                   "E01OFMBNK",
                                   "E01OFMBRN",
                                   "E01OFMCCY",
                                   "E01OFMDSC",
                                   "E01OFMCKN",
                                   "E01OFMFTY",
                                   "E01OFMEM1",
                                   "E01OFMEM2",
                                   "E01OFMEM3",
                                   "E01DEBBNK",
                                   "E01DEBBRN",
                                   "E01DEBCCY",
                                   "E01DEBGLN",
                                   "E01DEBACC",
                                   "E01OFMAMT",
                                   "E01OFMBNF",
                                   "E01OFMBN1",
                                   "E01OFMBN2",
                                   "E01OFMAPL",
                                   "E01OFMAP1",
                                   "E01OFMAP2",
                                   "E01OFMCUN",
                                   "E01OFMAD1",
                                   "E01OFMAD2",
                                   "E01OFMAD3",
                                   "E01OFMCO1",
                                   "E01OFMCO2",
                                   "E01OFMCO3",
                                   "E01OFMCLS",
                                   "E01LETAMT",
                                   "E01OFMAPV",
                                   "E01OFMPTH",
                                   "E01OFMCOM",
                                   "E01OFMIVA",
                                   "E01OFMBTH",
                                   "E01NEWCKN",
                                   "E01OFMBID",
                                   "E01OFMAID",
                                   "E01OFMCTY",
                                   "D01OFMCTY",
                                   "E01CREBNK",
                                   "E01CREBRN",
                                   "E01CRECCY",
                                   "E01CREGLN",
                                   "E01CREACC",
                                   "E01CRECCN",
                                   "E01DESCRIP"
                                 };
  final static String fid = "1040220181646";
  final static String rid = "2028BF5F56576";
  final static String fmtname = "EVG012001";
  final int FIELDCOUNT = 56;
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
  private CharacterField fieldE01OFMBNK = null;
  private DecimalField fieldE01OFMBRN = null;
  private CharacterField fieldE01OFMCCY = null;
  private CharacterField fieldE01OFMDSC = null;
  private DecimalField fieldE01OFMCKN = null;
  private CharacterField fieldE01OFMFTY = null;
  private DecimalField fieldE01OFMEM1 = null;
  private DecimalField fieldE01OFMEM2 = null;
  private DecimalField fieldE01OFMEM3 = null;
  private CharacterField fieldE01DEBBNK = null;
  private DecimalField fieldE01DEBBRN = null;
  private CharacterField fieldE01DEBCCY = null;
  private DecimalField fieldE01DEBGLN = null;
  private DecimalField fieldE01DEBACC = null;
  private DecimalField fieldE01OFMAMT = null;
  private CharacterField fieldE01OFMBNF = null;
  private CharacterField fieldE01OFMBN1 = null;
  private CharacterField fieldE01OFMBN2 = null;
  private CharacterField fieldE01OFMAPL = null;
  private CharacterField fieldE01OFMAP1 = null;
  private CharacterField fieldE01OFMAP2 = null;
  private DecimalField fieldE01OFMCUN = null;
  private CharacterField fieldE01OFMAD1 = null;
  private CharacterField fieldE01OFMAD2 = null;
  private CharacterField fieldE01OFMAD3 = null;
  private CharacterField fieldE01OFMCO1 = null;
  private CharacterField fieldE01OFMCO2 = null;
  private CharacterField fieldE01OFMCO3 = null;
  private CharacterField fieldE01OFMCLS = null;
  private CharacterField fieldE01LETAMT = null;
  private CharacterField fieldE01OFMAPV = null;
  private CharacterField fieldE01OFMPTH = null;
  private DecimalField fieldE01OFMCOM = null;
  private DecimalField fieldE01OFMIVA = null;
  private DecimalField fieldE01OFMBTH = null;
  private DecimalField fieldE01NEWCKN = null;
  private CharacterField fieldE01OFMBID = null;
  private CharacterField fieldE01OFMAID = null;
  private CharacterField fieldE01OFMCTY = null;
  private CharacterField fieldD01OFMCTY = null;
  private CharacterField fieldE01CREBNK = null;
  private DecimalField fieldE01CREBRN = null;
  private CharacterField fieldE01CRECCY = null;
  private DecimalField fieldE01CREGLN = null;
  private DecimalField fieldE01CREACC = null;
  private DecimalField fieldE01CRECCN = null;
  private CharacterField fieldE01DESCRIP = null;

  /**
  * Constructor for EVG012001Message.
  */
  public EVG012001Message()
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
    recordsize = 1240;
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
    fields[9] = fieldE01OFMBNK =
    new CharacterField(message, HEADERSIZE + 42, 2, "E01OFMBNK");
    fields[10] = fieldE01OFMBRN =
    new DecimalField(message, HEADERSIZE + 44, 4, 0, "E01OFMBRN");
    fields[11] = fieldE01OFMCCY =
    new CharacterField(message, HEADERSIZE + 48, 3, "E01OFMCCY");
    fields[12] = fieldE01OFMDSC =
    new CharacterField(message, HEADERSIZE + 51, 35, "E01OFMDSC");
    fields[13] = fieldE01OFMCKN =
    new DecimalField(message, HEADERSIZE + 86, 10, 0, "E01OFMCKN");
    fields[14] = fieldE01OFMFTY =
    new CharacterField(message, HEADERSIZE + 96, 1, "E01OFMFTY");
    fields[15] = fieldE01OFMEM1 =
    new DecimalField(message, HEADERSIZE + 97, 3, 0, "E01OFMEM1");
    fields[16] = fieldE01OFMEM2 =
    new DecimalField(message, HEADERSIZE + 100, 3, 0, "E01OFMEM2");
    fields[17] = fieldE01OFMEM3 =
    new DecimalField(message, HEADERSIZE + 103, 3, 0, "E01OFMEM3");
    fields[18] = fieldE01DEBBNK =
    new CharacterField(message, HEADERSIZE + 106, 2, "E01DEBBNK");
    fields[19] = fieldE01DEBBRN =
    new DecimalField(message, HEADERSIZE + 108, 4, 0, "E01DEBBRN");
    fields[20] = fieldE01DEBCCY =
    new CharacterField(message, HEADERSIZE + 112, 3, "E01DEBCCY");
    fields[21] = fieldE01DEBGLN =
    new DecimalField(message, HEADERSIZE + 115, 17, 0, "E01DEBGLN");
    fields[22] = fieldE01DEBACC =
    new DecimalField(message, HEADERSIZE + 132, 13, 0, "E01DEBACC");
    fields[23] = fieldE01OFMAMT =
    new DecimalField(message, HEADERSIZE + 145, 17, 2, "E01OFMAMT");
    fields[24] = fieldE01OFMBNF =
    new CharacterField(message, HEADERSIZE + 162, 35, "E01OFMBNF");
    fields[25] = fieldE01OFMBN1 =
    new CharacterField(message, HEADERSIZE + 197, 35, "E01OFMBN1");
    fields[26] = fieldE01OFMBN2 =
    new CharacterField(message, HEADERSIZE + 232, 35, "E01OFMBN2");
    fields[27] = fieldE01OFMAPL =
    new CharacterField(message, HEADERSIZE + 267, 35, "E01OFMAPL");
    fields[28] = fieldE01OFMAP1 =
    new CharacterField(message, HEADERSIZE + 302, 35, "E01OFMAP1");
    fields[29] = fieldE01OFMAP2 =
    new CharacterField(message, HEADERSIZE + 337, 35, "E01OFMAP2");
    fields[30] = fieldE01OFMCUN =
    new DecimalField(message, HEADERSIZE + 372, 10, 0, "E01OFMCUN");
    fields[31] = fieldE01OFMAD1 =
    new CharacterField(message, HEADERSIZE + 382, 70, "E01OFMAD1");
    fields[32] = fieldE01OFMAD2 =
    new CharacterField(message, HEADERSIZE + 452, 70, "E01OFMAD2");
    fields[33] = fieldE01OFMAD3 =
    new CharacterField(message, HEADERSIZE + 522, 70, "E01OFMAD3");
    fields[34] = fieldE01OFMCO1 =
    new CharacterField(message, HEADERSIZE + 592, 70, "E01OFMCO1");
    fields[35] = fieldE01OFMCO2 =
    new CharacterField(message, HEADERSIZE + 662, 70, "E01OFMCO2");
    fields[36] = fieldE01OFMCO3 =
    new CharacterField(message, HEADERSIZE + 732, 70, "E01OFMCO3");
    fields[37] = fieldE01OFMCLS =
    new CharacterField(message, HEADERSIZE + 802, 1, "E01OFMCLS");
    fields[38] = fieldE01LETAMT =
    new CharacterField(message, HEADERSIZE + 803, 160, "E01LETAMT");
    fields[39] = fieldE01OFMAPV =
    new CharacterField(message, HEADERSIZE + 963, 1, "E01OFMAPV");
    fields[40] = fieldE01OFMPTH =
    new CharacterField(message, HEADERSIZE + 964, 80, "E01OFMPTH");
    fields[41] = fieldE01OFMCOM =
    new DecimalField(message, HEADERSIZE + 1044, 17, 2, "E01OFMCOM");
    fields[42] = fieldE01OFMIVA =
    new DecimalField(message, HEADERSIZE + 1061, 17, 2, "E01OFMIVA");
    fields[43] = fieldE01OFMBTH =
    new DecimalField(message, HEADERSIZE + 1078, 5, 0, "E01OFMBTH");
    fields[44] = fieldE01NEWCKN =
    new DecimalField(message, HEADERSIZE + 1083, 10, 0, "E01NEWCKN");
    fields[45] = fieldE01OFMBID =
    new CharacterField(message, HEADERSIZE + 1093, 15, "E01OFMBID");
    fields[46] = fieldE01OFMAID =
    new CharacterField(message, HEADERSIZE + 1108, 15, "E01OFMAID");
    fields[47] = fieldE01OFMCTY =
    new CharacterField(message, HEADERSIZE + 1123, 4, "E01OFMCTY");
    fields[48] = fieldD01OFMCTY =
    new CharacterField(message, HEADERSIZE + 1127, 35, "D01OFMCTY");
    fields[49] = fieldE01CREBNK =
    new CharacterField(message, HEADERSIZE + 1162, 2, "E01CREBNK");
    fields[50] = fieldE01CREBRN =
    new DecimalField(message, HEADERSIZE + 1164, 4, 0, "E01CREBRN");
    fields[51] = fieldE01CRECCY =
    new CharacterField(message, HEADERSIZE + 1168, 3, "E01CRECCY");
    fields[52] = fieldE01CREGLN =
    new DecimalField(message, HEADERSIZE + 1171, 17, 0, "E01CREGLN");
    fields[53] = fieldE01CREACC =
    new DecimalField(message, HEADERSIZE + 1188, 13, 0, "E01CREACC");
    fields[54] = fieldE01CRECCN =
    new DecimalField(message, HEADERSIZE + 1201, 9, 0, "E01CRECCN");
    fields[55] = fieldE01DESCRIP =
    new CharacterField(message, HEADERSIZE + 1210, 30, "E01DESCRIP");

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
  * Set field E01OFMBNK using a String value.
  */
  public void setE01OFMBNK(String newvalue)
  {
    fieldE01OFMBNK.setString(newvalue);
  }

  /**
  * Get value of field E01OFMBNK as a String.
  */
  public String getE01OFMBNK()
  {
    return fieldE01OFMBNK.getString();
  }

  /**
  * Set field E01OFMBRN using a String value.
  */
  public void setE01OFMBRN(String newvalue)
  {
    fieldE01OFMBRN.setString(newvalue);
  }

  /**
  * Get value of field E01OFMBRN as a String.
  */
  public String getE01OFMBRN()
  {
    return fieldE01OFMBRN.getString();
  }

  /**
  * Set numeric field E01OFMBRN using a BigDecimal value.
  */
  public void setE01OFMBRN(BigDecimal newvalue)
  {
    fieldE01OFMBRN.setBigDecimal(newvalue);
  }

  /**
  * Return value of numeric field E01OFMBRN as a BigDecimal.
  */
  public BigDecimal getBigDecimalE01OFMBRN()
  {
    return fieldE01OFMBRN.getBigDecimal();
  }

  /**
  * Set field E01OFMCCY using a String value.
  */
  public void setE01OFMCCY(String newvalue)
  {
    fieldE01OFMCCY.setString(newvalue);
  }

  /**
  * Get value of field E01OFMCCY as a String.
  */
  public String getE01OFMCCY()
  {
    return fieldE01OFMCCY.getString();
  }

  /**
  * Set field E01OFMDSC using a String value.
  */
  public void setE01OFMDSC(String newvalue)
  {
    fieldE01OFMDSC.setString(newvalue);
  }

  /**
  * Get value of field E01OFMDSC as a String.
  */
  public String getE01OFMDSC()
  {
    return fieldE01OFMDSC.getString();
  }

  /**
  * Set field E01OFMCKN using a String value.
  */
  public void setE01OFMCKN(String newvalue)
  {
    fieldE01OFMCKN.setString(newvalue);
  }

  /**
  * Get value of field E01OFMCKN as a String.
  */
  public String getE01OFMCKN()
  {
    return fieldE01OFMCKN.getString();
  }

  /**
  * Set numeric field E01OFMCKN using a BigDecimal value.
  */
  public void setE01OFMCKN(BigDecimal newvalue)
  {
    fieldE01OFMCKN.setBigDecimal(newvalue);
  }

  /**
  * Return value of numeric field E01OFMCKN as a BigDecimal.
  */
  public BigDecimal getBigDecimalE01OFMCKN()
  {
    return fieldE01OFMCKN.getBigDecimal();
  }

  /**
  * Set field E01OFMFTY using a String value.
  */
  public void setE01OFMFTY(String newvalue)
  {
    fieldE01OFMFTY.setString(newvalue);
  }

  /**
  * Get value of field E01OFMFTY as a String.
  */
  public String getE01OFMFTY()
  {
    return fieldE01OFMFTY.getString();
  }

  /**
  * Set field E01OFMEM1 using a String value.
  */
  public void setE01OFMEM1(String newvalue)
  {
    fieldE01OFMEM1.setString(newvalue);
  }

  /**
  * Get value of field E01OFMEM1 as a String.
  */
  public String getE01OFMEM1()
  {
    return fieldE01OFMEM1.getString();
  }

  /**
  * Set numeric field E01OFMEM1 using a BigDecimal value.
  */
  public void setE01OFMEM1(BigDecimal newvalue)
  {
    fieldE01OFMEM1.setBigDecimal(newvalue);
  }

  /**
  * Return value of numeric field E01OFMEM1 as a BigDecimal.
  */
  public BigDecimal getBigDecimalE01OFMEM1()
  {
    return fieldE01OFMEM1.getBigDecimal();
  }

  /**
  * Set field E01OFMEM2 using a String value.
  */
  public void setE01OFMEM2(String newvalue)
  {
    fieldE01OFMEM2.setString(newvalue);
  }

  /**
  * Get value of field E01OFMEM2 as a String.
  */
  public String getE01OFMEM2()
  {
    return fieldE01OFMEM2.getString();
  }

  /**
  * Set numeric field E01OFMEM2 using a BigDecimal value.
  */
  public void setE01OFMEM2(BigDecimal newvalue)
  {
    fieldE01OFMEM2.setBigDecimal(newvalue);
  }

  /**
  * Return value of numeric field E01OFMEM2 as a BigDecimal.
  */
  public BigDecimal getBigDecimalE01OFMEM2()
  {
    return fieldE01OFMEM2.getBigDecimal();
  }

  /**
  * Set field E01OFMEM3 using a String value.
  */
  public void setE01OFMEM3(String newvalue)
  {
    fieldE01OFMEM3.setString(newvalue);
  }

  /**
  * Get value of field E01OFMEM3 as a String.
  */
  public String getE01OFMEM3()
  {
    return fieldE01OFMEM3.getString();
  }

  /**
  * Set numeric field E01OFMEM3 using a BigDecimal value.
  */
  public void setE01OFMEM3(BigDecimal newvalue)
  {
    fieldE01OFMEM3.setBigDecimal(newvalue);
  }

  /**
  * Return value of numeric field E01OFMEM3 as a BigDecimal.
  */
  public BigDecimal getBigDecimalE01OFMEM3()
  {
    return fieldE01OFMEM3.getBigDecimal();
  }

  /**
  * Set field E01DEBBNK using a String value.
  */
  public void setE01DEBBNK(String newvalue)
  {
    fieldE01DEBBNK.setString(newvalue);
  }

  /**
  * Get value of field E01DEBBNK as a String.
  */
  public String getE01DEBBNK()
  {
    return fieldE01DEBBNK.getString();
  }

  /**
  * Set field E01DEBBRN using a String value.
  */
  public void setE01DEBBRN(String newvalue)
  {
    fieldE01DEBBRN.setString(newvalue);
  }

  /**
  * Get value of field E01DEBBRN as a String.
  */
  public String getE01DEBBRN()
  {
    return fieldE01DEBBRN.getString();
  }

  /**
  * Set numeric field E01DEBBRN using a BigDecimal value.
  */
  public void setE01DEBBRN(BigDecimal newvalue)
  {
    fieldE01DEBBRN.setBigDecimal(newvalue);
  }

  /**
  * Return value of numeric field E01DEBBRN as a BigDecimal.
  */
  public BigDecimal getBigDecimalE01DEBBRN()
  {
    return fieldE01DEBBRN.getBigDecimal();
  }

  /**
  * Set field E01DEBCCY using a String value.
  */
  public void setE01DEBCCY(String newvalue)
  {
    fieldE01DEBCCY.setString(newvalue);
  }

  /**
  * Get value of field E01DEBCCY as a String.
  */
  public String getE01DEBCCY()
  {
    return fieldE01DEBCCY.getString();
  }

  /**
  * Set field E01DEBGLN using a String value.
  */
  public void setE01DEBGLN(String newvalue)
  {
    fieldE01DEBGLN.setString(newvalue);
  }

  /**
  * Get value of field E01DEBGLN as a String.
  */
  public String getE01DEBGLN()
  {
    return fieldE01DEBGLN.getString();
  }

  /**
  * Set numeric field E01DEBGLN using a BigDecimal value.
  */
  public void setE01DEBGLN(BigDecimal newvalue)
  {
    fieldE01DEBGLN.setBigDecimal(newvalue);
  }

  /**
  * Return value of numeric field E01DEBGLN as a BigDecimal.
  */
  public BigDecimal getBigDecimalE01DEBGLN()
  {
    return fieldE01DEBGLN.getBigDecimal();
  }

  /**
  * Set field E01DEBACC using a String value.
  */
  public void setE01DEBACC(String newvalue)
  {
    fieldE01DEBACC.setString(newvalue);
  }

  /**
  * Get value of field E01DEBACC as a String.
  */
  public String getE01DEBACC()
  {
    return fieldE01DEBACC.getString();
  }

  /**
  * Set numeric field E01DEBACC using a BigDecimal value.
  */
  public void setE01DEBACC(BigDecimal newvalue)
  {
    fieldE01DEBACC.setBigDecimal(newvalue);
  }

  /**
  * Return value of numeric field E01DEBACC as a BigDecimal.
  */
  public BigDecimal getBigDecimalE01DEBACC()
  {
    return fieldE01DEBACC.getBigDecimal();
  }

  /**
  * Set field E01OFMAMT using a String value.
  */
  public void setE01OFMAMT(String newvalue)
  {
    fieldE01OFMAMT.setString(newvalue);
  }

  /**
  * Get value of field E01OFMAMT as a String.
  */
  public String getE01OFMAMT()
  {
    return fieldE01OFMAMT.getString();
  }

  /**
  * Set numeric field E01OFMAMT using a BigDecimal value.
  */
  public void setE01OFMAMT(BigDecimal newvalue)
  {
    fieldE01OFMAMT.setBigDecimal(newvalue);
  }

  /**
  * Return value of numeric field E01OFMAMT as a BigDecimal.
  */
  public BigDecimal getBigDecimalE01OFMAMT()
  {
    return fieldE01OFMAMT.getBigDecimal();
  }

  /**
  * Set field E01OFMBNF using a String value.
  */
  public void setE01OFMBNF(String newvalue)
  {
    fieldE01OFMBNF.setString(newvalue);
  }

  /**
  * Get value of field E01OFMBNF as a String.
  */
  public String getE01OFMBNF()
  {
    return fieldE01OFMBNF.getString();
  }

  /**
  * Set field E01OFMBN1 using a String value.
  */
  public void setE01OFMBN1(String newvalue)
  {
    fieldE01OFMBN1.setString(newvalue);
  }

  /**
  * Get value of field E01OFMBN1 as a String.
  */
  public String getE01OFMBN1()
  {
    return fieldE01OFMBN1.getString();
  }

  /**
  * Set field E01OFMBN2 using a String value.
  */
  public void setE01OFMBN2(String newvalue)
  {
    fieldE01OFMBN2.setString(newvalue);
  }

  /**
  * Get value of field E01OFMBN2 as a String.
  */
  public String getE01OFMBN2()
  {
    return fieldE01OFMBN2.getString();
  }

  /**
  * Set field E01OFMAPL using a String value.
  */
  public void setE01OFMAPL(String newvalue)
  {
    fieldE01OFMAPL.setString(newvalue);
  }

  /**
  * Get value of field E01OFMAPL as a String.
  */
  public String getE01OFMAPL()
  {
    return fieldE01OFMAPL.getString();
  }

  /**
  * Set field E01OFMAP1 using a String value.
  */
  public void setE01OFMAP1(String newvalue)
  {
    fieldE01OFMAP1.setString(newvalue);
  }

  /**
  * Get value of field E01OFMAP1 as a String.
  */
  public String getE01OFMAP1()
  {
    return fieldE01OFMAP1.getString();
  }

  /**
  * Set field E01OFMAP2 using a String value.
  */
  public void setE01OFMAP2(String newvalue)
  {
    fieldE01OFMAP2.setString(newvalue);
  }

  /**
  * Get value of field E01OFMAP2 as a String.
  */
  public String getE01OFMAP2()
  {
    return fieldE01OFMAP2.getString();
  }

  /**
  * Set field E01OFMCUN using a String value.
  */
  public void setE01OFMCUN(String newvalue)
  {
    fieldE01OFMCUN.setString(newvalue);
  }

  /**
  * Get value of field E01OFMCUN as a String.
  */
  public String getE01OFMCUN()
  {
    return fieldE01OFMCUN.getString();
  }

  /**
  * Set numeric field E01OFMCUN using a BigDecimal value.
  */
  public void setE01OFMCUN(BigDecimal newvalue)
  {
    fieldE01OFMCUN.setBigDecimal(newvalue);
  }

  /**
  * Return value of numeric field E01OFMCUN as a BigDecimal.
  */
  public BigDecimal getBigDecimalE01OFMCUN()
  {
    return fieldE01OFMCUN.getBigDecimal();
  }

  /**
  * Set field E01OFMAD1 using a String value.
  */
  public void setE01OFMAD1(String newvalue)
  {
    fieldE01OFMAD1.setString(newvalue);
  }

  /**
  * Get value of field E01OFMAD1 as a String.
  */
  public String getE01OFMAD1()
  {
    return fieldE01OFMAD1.getString();
  }

  /**
  * Set field E01OFMAD2 using a String value.
  */
  public void setE01OFMAD2(String newvalue)
  {
    fieldE01OFMAD2.setString(newvalue);
  }

  /**
  * Get value of field E01OFMAD2 as a String.
  */
  public String getE01OFMAD2()
  {
    return fieldE01OFMAD2.getString();
  }

  /**
  * Set field E01OFMAD3 using a String value.
  */
  public void setE01OFMAD3(String newvalue)
  {
    fieldE01OFMAD3.setString(newvalue);
  }

  /**
  * Get value of field E01OFMAD3 as a String.
  */
  public String getE01OFMAD3()
  {
    return fieldE01OFMAD3.getString();
  }

  /**
  * Set field E01OFMCO1 using a String value.
  */
  public void setE01OFMCO1(String newvalue)
  {
    fieldE01OFMCO1.setString(newvalue);
  }

  /**
  * Get value of field E01OFMCO1 as a String.
  */
  public String getE01OFMCO1()
  {
    return fieldE01OFMCO1.getString();
  }

  /**
  * Set field E01OFMCO2 using a String value.
  */
  public void setE01OFMCO2(String newvalue)
  {
    fieldE01OFMCO2.setString(newvalue);
  }

  /**
  * Get value of field E01OFMCO2 as a String.
  */
  public String getE01OFMCO2()
  {
    return fieldE01OFMCO2.getString();
  }

  /**
  * Set field E01OFMCO3 using a String value.
  */
  public void setE01OFMCO3(String newvalue)
  {
    fieldE01OFMCO3.setString(newvalue);
  }

  /**
  * Get value of field E01OFMCO3 as a String.
  */
  public String getE01OFMCO3()
  {
    return fieldE01OFMCO3.getString();
  }

  /**
  * Set field E01OFMCLS using a String value.
  */
  public void setE01OFMCLS(String newvalue)
  {
    fieldE01OFMCLS.setString(newvalue);
  }

  /**
  * Get value of field E01OFMCLS as a String.
  */
  public String getE01OFMCLS()
  {
    return fieldE01OFMCLS.getString();
  }

  /**
  * Set field E01LETAMT using a String value.
  */
  public void setE01LETAMT(String newvalue)
  {
    fieldE01LETAMT.setString(newvalue);
  }

  /**
  * Get value of field E01LETAMT as a String.
  */
  public String getE01LETAMT()
  {
    return fieldE01LETAMT.getString();
  }

  /**
  * Set field E01OFMAPV using a String value.
  */
  public void setE01OFMAPV(String newvalue)
  {
    fieldE01OFMAPV.setString(newvalue);
  }

  /**
  * Get value of field E01OFMAPV as a String.
  */
  public String getE01OFMAPV()
  {
    return fieldE01OFMAPV.getString();
  }

  /**
  * Set field E01OFMPTH using a String value.
  */
  public void setE01OFMPTH(String newvalue)
  {
    fieldE01OFMPTH.setString(newvalue);
  }

  /**
  * Get value of field E01OFMPTH as a String.
  */
  public String getE01OFMPTH()
  {
    return fieldE01OFMPTH.getString();
  }

  /**
  * Set field E01OFMCOM using a String value.
  */
  public void setE01OFMCOM(String newvalue)
  {
    fieldE01OFMCOM.setString(newvalue);
  }

  /**
  * Get value of field E01OFMCOM as a String.
  */
  public String getE01OFMCOM()
  {
    return fieldE01OFMCOM.getString();
  }

  /**
  * Set numeric field E01OFMCOM using a BigDecimal value.
  */
  public void setE01OFMCOM(BigDecimal newvalue)
  {
    fieldE01OFMCOM.setBigDecimal(newvalue);
  }

  /**
  * Return value of numeric field E01OFMCOM as a BigDecimal.
  */
  public BigDecimal getBigDecimalE01OFMCOM()
  {
    return fieldE01OFMCOM.getBigDecimal();
  }

  /**
  * Set field E01OFMIVA using a String value.
  */
  public void setE01OFMIVA(String newvalue)
  {
    fieldE01OFMIVA.setString(newvalue);
  }

  /**
  * Get value of field E01OFMIVA as a String.
  */
  public String getE01OFMIVA()
  {
    return fieldE01OFMIVA.getString();
  }

  /**
  * Set numeric field E01OFMIVA using a BigDecimal value.
  */
  public void setE01OFMIVA(BigDecimal newvalue)
  {
    fieldE01OFMIVA.setBigDecimal(newvalue);
  }

  /**
  * Return value of numeric field E01OFMIVA as a BigDecimal.
  */
  public BigDecimal getBigDecimalE01OFMIVA()
  {
    return fieldE01OFMIVA.getBigDecimal();
  }

  /**
  * Set field E01OFMBTH using a String value.
  */
  public void setE01OFMBTH(String newvalue)
  {
    fieldE01OFMBTH.setString(newvalue);
  }

  /**
  * Get value of field E01OFMBTH as a String.
  */
  public String getE01OFMBTH()
  {
    return fieldE01OFMBTH.getString();
  }

  /**
  * Set numeric field E01OFMBTH using a BigDecimal value.
  */
  public void setE01OFMBTH(BigDecimal newvalue)
  {
    fieldE01OFMBTH.setBigDecimal(newvalue);
  }

  /**
  * Return value of numeric field E01OFMBTH as a BigDecimal.
  */
  public BigDecimal getBigDecimalE01OFMBTH()
  {
    return fieldE01OFMBTH.getBigDecimal();
  }

  /**
  * Set field E01NEWCKN using a String value.
  */
  public void setE01NEWCKN(String newvalue)
  {
    fieldE01NEWCKN.setString(newvalue);
  }

  /**
  * Get value of field E01NEWCKN as a String.
  */
  public String getE01NEWCKN()
  {
    return fieldE01NEWCKN.getString();
  }

  /**
  * Set numeric field E01NEWCKN using a BigDecimal value.
  */
  public void setE01NEWCKN(BigDecimal newvalue)
  {
    fieldE01NEWCKN.setBigDecimal(newvalue);
  }

  /**
  * Return value of numeric field E01NEWCKN as a BigDecimal.
  */
  public BigDecimal getBigDecimalE01NEWCKN()
  {
    return fieldE01NEWCKN.getBigDecimal();
  }

  /**
  * Set field E01OFMBID using a String value.
  */
  public void setE01OFMBID(String newvalue)
  {
    fieldE01OFMBID.setString(newvalue);
  }

  /**
  * Get value of field E01OFMBID as a String.
  */
  public String getE01OFMBID()
  {
    return fieldE01OFMBID.getString();
  }

  /**
  * Set field E01OFMAID using a String value.
  */
  public void setE01OFMAID(String newvalue)
  {
    fieldE01OFMAID.setString(newvalue);
  }

  /**
  * Get value of field E01OFMAID as a String.
  */
  public String getE01OFMAID()
  {
    return fieldE01OFMAID.getString();
  }

  /**
  * Set field E01OFMCTY using a String value.
  */
  public void setE01OFMCTY(String newvalue)
  {
    fieldE01OFMCTY.setString(newvalue);
  }

  /**
  * Get value of field E01OFMCTY as a String.
  */
  public String getE01OFMCTY()
  {
    return fieldE01OFMCTY.getString();
  }

  /**
  * Set field D01OFMCTY using a String value.
  */
  public void setD01OFMCTY(String newvalue)
  {
    fieldD01OFMCTY.setString(newvalue);
  }

  /**
  * Get value of field D01OFMCTY as a String.
  */
  public String getD01OFMCTY()
  {
    return fieldD01OFMCTY.getString();
  }

  /**
  * Set field E01CREBNK using a String value.
  */
  public void setE01CREBNK(String newvalue)
  {
    fieldE01CREBNK.setString(newvalue);
  }

  /**
  * Get value of field E01CREBNK as a String.
  */
  public String getE01CREBNK()
  {
    return fieldE01CREBNK.getString();
  }

  /**
  * Set field E01CREBRN using a String value.
  */
  public void setE01CREBRN(String newvalue)
  {
    fieldE01CREBRN.setString(newvalue);
  }

  /**
  * Get value of field E01CREBRN as a String.
  */
  public String getE01CREBRN()
  {
    return fieldE01CREBRN.getString();
  }

  /**
  * Set numeric field E01CREBRN using a BigDecimal value.
  */
  public void setE01CREBRN(BigDecimal newvalue)
  {
    fieldE01CREBRN.setBigDecimal(newvalue);
  }

  /**
  * Return value of numeric field E01CREBRN as a BigDecimal.
  */
  public BigDecimal getBigDecimalE01CREBRN()
  {
    return fieldE01CREBRN.getBigDecimal();
  }

  /**
  * Set field E01CRECCY using a String value.
  */
  public void setE01CRECCY(String newvalue)
  {
    fieldE01CRECCY.setString(newvalue);
  }

  /**
  * Get value of field E01CRECCY as a String.
  */
  public String getE01CRECCY()
  {
    return fieldE01CRECCY.getString();
  }

  /**
  * Set field E01CREGLN using a String value.
  */
  public void setE01CREGLN(String newvalue)
  {
    fieldE01CREGLN.setString(newvalue);
  }

  /**
  * Get value of field E01CREGLN as a String.
  */
  public String getE01CREGLN()
  {
    return fieldE01CREGLN.getString();
  }

  /**
  * Set numeric field E01CREGLN using a BigDecimal value.
  */
  public void setE01CREGLN(BigDecimal newvalue)
  {
    fieldE01CREGLN.setBigDecimal(newvalue);
  }

  /**
  * Return value of numeric field E01CREGLN as a BigDecimal.
  */
  public BigDecimal getBigDecimalE01CREGLN()
  {
    return fieldE01CREGLN.getBigDecimal();
  }

  /**
  * Set field E01CREACC using a String value.
  */
  public void setE01CREACC(String newvalue)
  {
    fieldE01CREACC.setString(newvalue);
  }

  /**
  * Get value of field E01CREACC as a String.
  */
  public String getE01CREACC()
  {
    return fieldE01CREACC.getString();
  }

  /**
  * Set numeric field E01CREACC using a BigDecimal value.
  */
  public void setE01CREACC(BigDecimal newvalue)
  {
    fieldE01CREACC.setBigDecimal(newvalue);
  }

  /**
  * Return value of numeric field E01CREACC as a BigDecimal.
  */
  public BigDecimal getBigDecimalE01CREACC()
  {
    return fieldE01CREACC.getBigDecimal();
  }

  /**
  * Set field E01CRECCN using a String value.
  */
  public void setE01CRECCN(String newvalue)
  {
    fieldE01CRECCN.setString(newvalue);
  }

  /**
  * Get value of field E01CRECCN as a String.
  */
  public String getE01CRECCN()
  {
    return fieldE01CRECCN.getString();
  }

  /**
  * Set numeric field E01CRECCN using a BigDecimal value.
  */
  public void setE01CRECCN(BigDecimal newvalue)
  {
    fieldE01CRECCN.setBigDecimal(newvalue);
  }

  /**
  * Return value of numeric field E01CRECCN as a BigDecimal.
  */
  public BigDecimal getBigDecimalE01CRECCN()
  {
    return fieldE01CRECCN.getBigDecimal();
  }

  /**
  * Set field E01DESCRIP using a String value.
  */
  public void setE01DESCRIP(String newvalue)
  {
    fieldE01DESCRIP.setString(newvalue);
  }

  /**
  * Get value of field E01DESCRIP as a String.
  */
  public String getE01DESCRIP()
  {
    return fieldE01DESCRIP.getString();
  }

}
