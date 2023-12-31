package datapro.eibs.beans;

import datapro.eibs.sockets.*;
import java.io.*;
import java.math.*;

import java.util.*;

/**
* Class generated by AS/400 CRTCLASS command from EDC000003 physical file definition.
* 
* File level identifier is 1070713111556.
* Record format level identifier is 48E6CEEF31D45.
*/

public class EDC000003Message extends MessageRecord
{
  final static String fldnames[] = {
                                     "H03USERID",
                                     "H03PROGRM",
                                     "H03TIMSYS",
                                     "H03SCRCOD",
                                     "H03OPECOD",
                                     "H03FLGMAS",
                                     "H03FLGWK1",
                                     "H03FLGWK2",
                                     "H03FLGWK3",
                                     "E03DCMACC",
                                     "E03DCMACD",
                                     "E03DCMCUN",
                                     "E03CUSNA1",
                                     "E03DCMPRO",
                                     "E03DSCPRO",
                                     "E03DCMATY",
                                     "E03DCMTYP",
                                     "E03DCMBNK",
                                     "E03DCMBRN",
                                     "E03DCMCCY",
                                     "E03DCMGLN",
                                     "E03DCMOPR",
                                     "E03DCMAMN",
                                     "E03DCMORF",
                                     "E03DCMCRF",
                                     "E03DCMDRF",
                                     "E03DCMOPT",
                                     "E03DCMIDF",
                                     "E03DCMIDA"
                                   };
  final static String tnames[] = {
                                   "H03USERID",
                                   "H03PROGRM",
                                   "H03TIMSYS",
                                   "H03SCRCOD",
                                   "H03OPECOD",
                                   "H03FLGMAS",
                                   "H03FLGWK1",
                                   "H03FLGWK2",
                                   "H03FLGWK3",
                                   "E03DCMACC",
                                   "E03DCMACD",
                                   "E03DCMCUN",
                                   "E03CUSNA1",
                                   "E03DCMPRO",
                                   "E03DSCPRO",
                                   "E03DCMATY",
                                   "E03DCMTYP",
                                   "E03DCMBNK",
                                   "E03DCMBRN",
                                   "E03DCMCCY",
                                   "E03DCMGLN",
                                   "E03DCMOPR",
                                   "E03DCMAMN",
                                   "E03DCMORF",
                                   "E03DCMCRF",
                                   "E03DCMDRF",
                                   "E03DCMOPT",
                                   "E03DCMIDF",
                                   "E03DCMIDA"
                                 };
  final static String fid = "1070713111556";
  final static String rid = "48E6CEEF31D45";
  final static String fmtname = "EDC000003";
  final int FIELDCOUNT = 29;
  private static Hashtable tlookup = new Hashtable();
  private CharacterField fieldH03USERID = null;
  private CharacterField fieldH03PROGRM = null;
  private CharacterField fieldH03TIMSYS = null;
  private CharacterField fieldH03SCRCOD = null;
  private CharacterField fieldH03OPECOD = null;
  private CharacterField fieldH03FLGMAS = null;
  private CharacterField fieldH03FLGWK1 = null;
  private CharacterField fieldH03FLGWK2 = null;
  private CharacterField fieldH03FLGWK3 = null;
  private DecimalField fieldE03DCMACC = null;
  private CharacterField fieldE03DCMACD = null;
  private DecimalField fieldE03DCMCUN = null;
  private CharacterField fieldE03CUSNA1 = null;
  private CharacterField fieldE03DCMPRO = null;
  private CharacterField fieldE03DSCPRO = null;
  private CharacterField fieldE03DCMATY = null;
  private CharacterField fieldE03DCMTYP = null;
  private CharacterField fieldE03DCMBNK = null;
  private DecimalField fieldE03DCMBRN = null;
  private CharacterField fieldE03DCMCCY = null;
  private DecimalField fieldE03DCMGLN = null;
  private CharacterField fieldE03DCMOPR = null;
  private DecimalField fieldE03DCMAMN = null;
  private CharacterField fieldE03DCMORF = null;
  private CharacterField fieldE03DCMCRF = null;
  private CharacterField fieldE03DCMDRF = null;
  private CharacterField fieldE03DCMOPT = null;
  private CharacterField fieldE03DCMIDF = null;
  private DecimalField fieldE03DCMIDA = null;

  /**
  * Constructor for EDC000003Message.
  */
  public EDC000003Message()
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
    recordsize = 276;
    fileid = fid;
    recordid = rid;
    message = new byte[getByteLength()];
    formatname = fmtname;
    fieldnames = fldnames;
    tagnames = tnames;
    fields = new MessageField[FIELDCOUNT];

    fields[0] = fieldH03USERID =
    new CharacterField(message, HEADERSIZE + 0, 10, "H03USERID");
    fields[1] = fieldH03PROGRM =
    new CharacterField(message, HEADERSIZE + 10, 10, "H03PROGRM");
    fields[2] = fieldH03TIMSYS =
    new CharacterField(message, HEADERSIZE + 20, 12, "H03TIMSYS");
    fields[3] = fieldH03SCRCOD =
    new CharacterField(message, HEADERSIZE + 32, 2, "H03SCRCOD");
    fields[4] = fieldH03OPECOD =
    new CharacterField(message, HEADERSIZE + 34, 4, "H03OPECOD");
    fields[5] = fieldH03FLGMAS =
    new CharacterField(message, HEADERSIZE + 38, 1, "H03FLGMAS");
    fields[6] = fieldH03FLGWK1 =
    new CharacterField(message, HEADERSIZE + 39, 1, "H03FLGWK1");
    fields[7] = fieldH03FLGWK2 =
    new CharacterField(message, HEADERSIZE + 40, 1, "H03FLGWK2");
    fields[8] = fieldH03FLGWK3 =
    new CharacterField(message, HEADERSIZE + 41, 1, "H03FLGWK3");
    fields[9] = fieldE03DCMACC =
    new DecimalField(message, HEADERSIZE + 42, 13, 0, "E03DCMACC");
    fields[10] = fieldE03DCMACD =
    new CharacterField(message, HEADERSIZE + 55, 2, "E03DCMACD");
    fields[11] = fieldE03DCMCUN =
    new DecimalField(message, HEADERSIZE + 57, 10, 0, "E03DCMCUN");
    fields[12] = fieldE03CUSNA1 =
    new CharacterField(message, HEADERSIZE + 67, 45, "E03CUSNA1");
    fields[13] = fieldE03DCMPRO =
    new CharacterField(message, HEADERSIZE + 112, 4, "E03DCMPRO");
    fields[14] = fieldE03DSCPRO =
    new CharacterField(message, HEADERSIZE + 116, 35, "E03DSCPRO");
    fields[15] = fieldE03DCMATY =
    new CharacterField(message, HEADERSIZE + 151, 4, "E03DCMATY");
    fields[16] = fieldE03DCMTYP =
    new CharacterField(message, HEADERSIZE + 155, 1, "E03DCMTYP");
    fields[17] = fieldE03DCMBNK =
    new CharacterField(message, HEADERSIZE + 156, 2, "E03DCMBNK");
    fields[18] = fieldE03DCMBRN =
    new DecimalField(message, HEADERSIZE + 158, 4, 0, "E03DCMBRN");
    fields[19] = fieldE03DCMCCY =
    new CharacterField(message, HEADERSIZE + 162, 3, "E03DCMCCY");
    fields[20] = fieldE03DCMGLN =
    new DecimalField(message, HEADERSIZE + 165, 17, 0, "E03DCMGLN");
    fields[21] = fieldE03DCMOPR =
    new CharacterField(message, HEADERSIZE + 182, 10, "E03DCMOPR");
    fields[22] = fieldE03DCMAMN =
    new DecimalField(message, HEADERSIZE + 192, 17, 2, "E03DCMAMN");
    fields[23] = fieldE03DCMORF =
    new CharacterField(message, HEADERSIZE + 209, 16, "E03DCMORF");
    fields[24] = fieldE03DCMCRF =
    new CharacterField(message, HEADERSIZE + 225, 16, "E03DCMCRF");
    fields[25] = fieldE03DCMDRF =
    new CharacterField(message, HEADERSIZE + 241, 16, "E03DCMDRF");
    fields[26] = fieldE03DCMOPT =
    new CharacterField(message, HEADERSIZE + 257, 1, "E03DCMOPT");
    fields[27] = fieldE03DCMIDF =
    new CharacterField(message, HEADERSIZE + 258, 1, "E03DCMIDF");
    fields[28] = fieldE03DCMIDA =
    new DecimalField(message, HEADERSIZE + 259, 17, 2, "E03DCMIDA");

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
  * Set field H03USERID using a String value.
  */
  public void setH03USERID(String newvalue)
  {
    fieldH03USERID.setString(newvalue);
  }

  /**
  * Get value of field H03USERID as a String.
  */
  public String getH03USERID()
  {
    return fieldH03USERID.getString();
  }

  /**
  * Set field H03PROGRM using a String value.
  */
  public void setH03PROGRM(String newvalue)
  {
    fieldH03PROGRM.setString(newvalue);
  }

  /**
  * Get value of field H03PROGRM as a String.
  */
  public String getH03PROGRM()
  {
    return fieldH03PROGRM.getString();
  }

  /**
  * Set field H03TIMSYS using a String value.
  */
  public void setH03TIMSYS(String newvalue)
  {
    fieldH03TIMSYS.setString(newvalue);
  }

  /**
  * Get value of field H03TIMSYS as a String.
  */
  public String getH03TIMSYS()
  {
    return fieldH03TIMSYS.getString();
  }

  /**
  * Set field H03SCRCOD using a String value.
  */
  public void setH03SCRCOD(String newvalue)
  {
    fieldH03SCRCOD.setString(newvalue);
  }

  /**
  * Get value of field H03SCRCOD as a String.
  */
  public String getH03SCRCOD()
  {
    return fieldH03SCRCOD.getString();
  }

  /**
  * Set field H03OPECOD using a String value.
  */
  public void setH03OPECOD(String newvalue)
  {
    fieldH03OPECOD.setString(newvalue);
  }

  /**
  * Get value of field H03OPECOD as a String.
  */
  public String getH03OPECOD()
  {
    return fieldH03OPECOD.getString();
  }

  /**
  * Set field H03FLGMAS using a String value.
  */
  public void setH03FLGMAS(String newvalue)
  {
    fieldH03FLGMAS.setString(newvalue);
  }

  /**
  * Get value of field H03FLGMAS as a String.
  */
  public String getH03FLGMAS()
  {
    return fieldH03FLGMAS.getString();
  }

  /**
  * Set field H03FLGWK1 using a String value.
  */
  public void setH03FLGWK1(String newvalue)
  {
    fieldH03FLGWK1.setString(newvalue);
  }

  /**
  * Get value of field H03FLGWK1 as a String.
  */
  public String getH03FLGWK1()
  {
    return fieldH03FLGWK1.getString();
  }

  /**
  * Set field H03FLGWK2 using a String value.
  */
  public void setH03FLGWK2(String newvalue)
  {
    fieldH03FLGWK2.setString(newvalue);
  }

  /**
  * Get value of field H03FLGWK2 as a String.
  */
  public String getH03FLGWK2()
  {
    return fieldH03FLGWK2.getString();
  }

  /**
  * Set field H03FLGWK3 using a String value.
  */
  public void setH03FLGWK3(String newvalue)
  {
    fieldH03FLGWK3.setString(newvalue);
  }

  /**
  * Get value of field H03FLGWK3 as a String.
  */
  public String getH03FLGWK3()
  {
    return fieldH03FLGWK3.getString();
  }

  /**
  * Set field E03DCMACC using a String value.
  */
  public void setE03DCMACC(String newvalue)
  {
    fieldE03DCMACC.setString(newvalue);
  }

  /**
  * Get value of field E03DCMACC as a String.
  */
  public String getE03DCMACC()
  {
    return fieldE03DCMACC.getString();
  }

  /**
  * Set numeric field E03DCMACC using a BigDecimal value.
  */
  public void setE03DCMACC(BigDecimal newvalue)
  {
    fieldE03DCMACC.setBigDecimal(newvalue);
  }

  /**
  * Return value of numeric field E03DCMACC as a BigDecimal.
  */
  public BigDecimal getBigDecimalE03DCMACC()
  {
    return fieldE03DCMACC.getBigDecimal();
  }

  /**
  * Set field E03DCMACD using a String value.
  */
  public void setE03DCMACD(String newvalue)
  {
    fieldE03DCMACD.setString(newvalue);
  }

  /**
  * Get value of field E03DCMACD as a String.
  */
  public String getE03DCMACD()
  {
    return fieldE03DCMACD.getString();
  }

  /**
  * Set field E03DCMCUN using a String value.
  */
  public void setE03DCMCUN(String newvalue)
  {
    fieldE03DCMCUN.setString(newvalue);
  }

  /**
  * Get value of field E03DCMCUN as a String.
  */
  public String getE03DCMCUN()
  {
    return fieldE03DCMCUN.getString();
  }

  /**
  * Set numeric field E03DCMCUN using a BigDecimal value.
  */
  public void setE03DCMCUN(BigDecimal newvalue)
  {
    fieldE03DCMCUN.setBigDecimal(newvalue);
  }

  /**
  * Return value of numeric field E03DCMCUN as a BigDecimal.
  */
  public BigDecimal getBigDecimalE03DCMCUN()
  {
    return fieldE03DCMCUN.getBigDecimal();
  }

  /**
  * Set field E03CUSNA1 using a String value.
  */
  public void setE03CUSNA1(String newvalue)
  {
    fieldE03CUSNA1.setString(newvalue);
  }

  /**
  * Get value of field E03CUSNA1 as a String.
  */
  public String getE03CUSNA1()
  {
    return fieldE03CUSNA1.getString();
  }

  /**
  * Set field E03DCMPRO using a String value.
  */
  public void setE03DCMPRO(String newvalue)
  {
    fieldE03DCMPRO.setString(newvalue);
  }

  /**
  * Get value of field E03DCMPRO as a String.
  */
  public String getE03DCMPRO()
  {
    return fieldE03DCMPRO.getString();
  }

  /**
  * Set field E03DSCPRO using a String value.
  */
  public void setE03DSCPRO(String newvalue)
  {
    fieldE03DSCPRO.setString(newvalue);
  }

  /**
  * Get value of field E03DSCPRO as a String.
  */
  public String getE03DSCPRO()
  {
    return fieldE03DSCPRO.getString();
  }

  /**
  * Set field E03DCMATY using a String value.
  */
  public void setE03DCMATY(String newvalue)
  {
    fieldE03DCMATY.setString(newvalue);
  }

  /**
  * Get value of field E03DCMATY as a String.
  */
  public String getE03DCMATY()
  {
    return fieldE03DCMATY.getString();
  }

  /**
  * Set field E03DCMTYP using a String value.
  */
  public void setE03DCMTYP(String newvalue)
  {
    fieldE03DCMTYP.setString(newvalue);
  }

  /**
  * Get value of field E03DCMTYP as a String.
  */
  public String getE03DCMTYP()
  {
    return fieldE03DCMTYP.getString();
  }

  /**
  * Set field E03DCMBNK using a String value.
  */
  public void setE03DCMBNK(String newvalue)
  {
    fieldE03DCMBNK.setString(newvalue);
  }

  /**
  * Get value of field E03DCMBNK as a String.
  */
  public String getE03DCMBNK()
  {
    return fieldE03DCMBNK.getString();
  }

  /**
  * Set field E03DCMBRN using a String value.
  */
  public void setE03DCMBRN(String newvalue)
  {
    fieldE03DCMBRN.setString(newvalue);
  }

  /**
  * Get value of field E03DCMBRN as a String.
  */
  public String getE03DCMBRN()
  {
    return fieldE03DCMBRN.getString();
  }

  /**
  * Set numeric field E03DCMBRN using a BigDecimal value.
  */
  public void setE03DCMBRN(BigDecimal newvalue)
  {
    fieldE03DCMBRN.setBigDecimal(newvalue);
  }

  /**
  * Return value of numeric field E03DCMBRN as a BigDecimal.
  */
  public BigDecimal getBigDecimalE03DCMBRN()
  {
    return fieldE03DCMBRN.getBigDecimal();
  }

  /**
  * Set field E03DCMCCY using a String value.
  */
  public void setE03DCMCCY(String newvalue)
  {
    fieldE03DCMCCY.setString(newvalue);
  }

  /**
  * Get value of field E03DCMCCY as a String.
  */
  public String getE03DCMCCY()
  {
    return fieldE03DCMCCY.getString();
  }

  /**
  * Set field E03DCMGLN using a String value.
  */
  public void setE03DCMGLN(String newvalue)
  {
    fieldE03DCMGLN.setString(newvalue);
  }

  /**
  * Get value of field E03DCMGLN as a String.
  */
  public String getE03DCMGLN()
  {
    return fieldE03DCMGLN.getString();
  }

  /**
  * Set numeric field E03DCMGLN using a BigDecimal value.
  */
  public void setE03DCMGLN(BigDecimal newvalue)
  {
    fieldE03DCMGLN.setBigDecimal(newvalue);
  }

  /**
  * Return value of numeric field E03DCMGLN as a BigDecimal.
  */
  public BigDecimal getBigDecimalE03DCMGLN()
  {
    return fieldE03DCMGLN.getBigDecimal();
  }

  /**
  * Set field E03DCMOPR using a String value.
  */
  public void setE03DCMOPR(String newvalue)
  {
    fieldE03DCMOPR.setString(newvalue);
  }

  /**
  * Get value of field E03DCMOPR as a String.
  */
  public String getE03DCMOPR()
  {
    return fieldE03DCMOPR.getString();
  }

  /**
  * Set field E03DCMAMN using a String value.
  */
  public void setE03DCMAMN(String newvalue)
  {
    fieldE03DCMAMN.setString(newvalue);
  }

  /**
  * Get value of field E03DCMAMN as a String.
  */
  public String getE03DCMAMN()
  {
    return fieldE03DCMAMN.getString();
  }

  /**
  * Set numeric field E03DCMAMN using a BigDecimal value.
  */
  public void setE03DCMAMN(BigDecimal newvalue)
  {
    fieldE03DCMAMN.setBigDecimal(newvalue);
  }

  /**
  * Return value of numeric field E03DCMAMN as a BigDecimal.
  */
  public BigDecimal getBigDecimalE03DCMAMN()
  {
    return fieldE03DCMAMN.getBigDecimal();
  }

  /**
  * Set field E03DCMORF using a String value.
  */
  public void setE03DCMORF(String newvalue)
  {
    fieldE03DCMORF.setString(newvalue);
  }

  /**
  * Get value of field E03DCMORF as a String.
  */
  public String getE03DCMORF()
  {
    return fieldE03DCMORF.getString();
  }

  /**
  * Set field E03DCMCRF using a String value.
  */
  public void setE03DCMCRF(String newvalue)
  {
    fieldE03DCMCRF.setString(newvalue);
  }

  /**
  * Get value of field E03DCMCRF as a String.
  */
  public String getE03DCMCRF()
  {
    return fieldE03DCMCRF.getString();
  }

  /**
  * Set field E03DCMDRF using a String value.
  */
  public void setE03DCMDRF(String newvalue)
  {
    fieldE03DCMDRF.setString(newvalue);
  }

  /**
  * Get value of field E03DCMDRF as a String.
  */
  public String getE03DCMDRF()
  {
    return fieldE03DCMDRF.getString();
  }

  /**
  * Set field E03DCMOPT using a String value.
  */
  public void setE03DCMOPT(String newvalue)
  {
    fieldE03DCMOPT.setString(newvalue);
  }

  /**
  * Get value of field E03DCMOPT as a String.
  */
  public String getE03DCMOPT()
  {
    return fieldE03DCMOPT.getString();
  }

  /**
  * Set field E03DCMIDF using a String value.
  */
  public void setE03DCMIDF(String newvalue)
  {
    fieldE03DCMIDF.setString(newvalue);
  }

  /**
  * Get value of field E03DCMIDF as a String.
  */
  public String getE03DCMIDF()
  {
    return fieldE03DCMIDF.getString();
  }

  /**
  * Set field E03DCMIDA using a String value.
  */
  public void setE03DCMIDA(String newvalue)
  {
    fieldE03DCMIDA.setString(newvalue);
  }

  /**
  * Get value of field E03DCMIDA as a String.
  */
  public String getE03DCMIDA()
  {
    return fieldE03DCMIDA.getString();
  }

  /**
  * Set numeric field E03DCMIDA using a BigDecimal value.
  */
  public void setE03DCMIDA(BigDecimal newvalue)
  {
    fieldE03DCMIDA.setBigDecimal(newvalue);
  }

  /**
  * Return value of numeric field E03DCMIDA as a BigDecimal.
  */
  public BigDecimal getBigDecimalE03DCMIDA()
  {
    return fieldE03DCMIDA.getBigDecimal();
  }

}
