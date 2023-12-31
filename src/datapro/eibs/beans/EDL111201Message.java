package datapro.eibs.beans;

import datapro.eibs.sockets.*;
import java.io.*;
import java.math.*;

import java.util.*;

/**
* Class generated by AS/400 CRTCLASS command from EDL111201 physical file definition.
* 
* File level identifier is 1100621154835.
* Record format level identifier is 4EA67541BE2A6.
*/

public class EDL111201Message extends MessageRecord
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
                                     "E01CNOMID",
                                     "E01ORDLST",
                                     "E01OFFOPC",
                                     "E01OFFCON",
                                     "E01OFFBNK",
                                     "E01OFFBRN",
                                     "E01OFFCCY",
                                     "E01OFFGLN",
                                     "E01OFFACC",
                                     "E01NARRAT",
                                     "E01FRECUE",
                                     "E01PRETYP",
                                     "E01BTHUBK",
                                     "E01BTHUBR",
                                     "E01BTHDIB",
                                     "E01CNODSC",
                                     "E01GLMDSC",
                                     "E01TOTAMT",
                                     "E01CUSNA1",
                                     "E01CUSLN3",
                                     "E01CUFTOM",
                                     "E01DEAACC",
                                     "E01DLCVA1",
                                     "E01CUSLN1",
                                     "E01CUSBRN",
                                     "E01DLCVA2",
                                     "E01NUMREC",
                                     "E01INDOPE"
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
                                   "E01CNOMID",
                                   "E01ORDLST",
                                   "E01OFFOPC",
                                   "E01OFFCON",
                                   "E01OFFBNK",
                                   "E01OFFBRN",
                                   "E01OFFCCY",
                                   "E01OFFGLN",
                                   "E01OFFACC",
                                   "E01NARRAT",
                                   "E01FRECUE",
                                   "E01PRETYP",
                                   "E01BTHUBK",
                                   "E01BTHUBR",
                                   "E01BTHDIB",
                                   "E01CNODSC",
                                   "E01GLMDSC",
                                   "E01TOTAMT",
                                   "E01CUSNA1",
                                   "E01CUSLN3",
                                   "E01CUFTOM",
                                   "E01DEAACC",
                                   "E01DLCVA1",
                                   "E01CUSLN1",
                                   "E01CUSBRN",
                                   "E01DLCVA2",
                                   "E01NUMREC",
                                   "E01INDOPE"
                                 };
  final static String fid = "1100621154835";
  final static String rid = "4EA67541BE2A6";
  final static String fmtname = "EDL111201";
  final int FIELDCOUNT = 37;
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
  private CharacterField fieldE01CNOMID = null;
  private CharacterField fieldE01ORDLST = null;
  private CharacterField fieldE01OFFOPC = null;
  private CharacterField fieldE01OFFCON = null;
  private CharacterField fieldE01OFFBNK = null;
  private DecimalField fieldE01OFFBRN = null;
  private CharacterField fieldE01OFFCCY = null;
  private DecimalField fieldE01OFFGLN = null;
  private DecimalField fieldE01OFFACC = null;
  private CharacterField fieldE01NARRAT = null;
  private CharacterField fieldE01FRECUE = null;
  private CharacterField fieldE01PRETYP = null;
  private CharacterField fieldE01BTHUBK = null;
  private DecimalField fieldE01BTHUBR = null;
  private DecimalField fieldE01BTHDIB = null;
  private CharacterField fieldE01CNODSC = null;
  private CharacterField fieldE01GLMDSC = null;
  private DecimalField fieldE01TOTAMT = null;
  private CharacterField fieldE01CUSNA1 = null;
  private CharacterField fieldE01CUSLN3 = null;
  private CharacterField fieldE01CUFTOM = null;
  private DecimalField fieldE01DEAACC = null;
  private DecimalField fieldE01DLCVA1 = null;
  private CharacterField fieldE01CUSLN1 = null;
  private DecimalField fieldE01CUSBRN = null;
  private DecimalField fieldE01DLCVA2 = null;
  private DecimalField fieldE01NUMREC = null;
  private CharacterField fieldE01INDOPE = null;

  /**
  * Constructor for EDL111201Message.
  */
  public EDL111201Message()
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
    recordsize = 418;
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
    fields[9] = fieldE01CNOMID =
    new CharacterField(message, HEADERSIZE + 42, 6, "E01CNOMID");
    fields[10] = fieldE01ORDLST =
    new CharacterField(message, HEADERSIZE + 48, 1, "E01ORDLST");
    fields[11] = fieldE01OFFOPC =
    new CharacterField(message, HEADERSIZE + 49, 2, "E01OFFOPC");
    fields[12] = fieldE01OFFCON =
    new CharacterField(message, HEADERSIZE + 51, 25, "E01OFFCON");
    fields[13] = fieldE01OFFBNK =
    new CharacterField(message, HEADERSIZE + 76, 2, "E01OFFBNK");
    fields[14] = fieldE01OFFBRN =
    new DecimalField(message, HEADERSIZE + 78, 4, 0, "E01OFFBRN");
    fields[15] = fieldE01OFFCCY =
    new CharacterField(message, HEADERSIZE + 82, 3, "E01OFFCCY");
    fields[16] = fieldE01OFFGLN =
    new DecimalField(message, HEADERSIZE + 85, 17, 0, "E01OFFGLN");
    fields[17] = fieldE01OFFACC =
    new DecimalField(message, HEADERSIZE + 102, 13, 0, "E01OFFACC");
    fields[18] = fieldE01NARRAT =
    new CharacterField(message, HEADERSIZE + 115, 30, "E01NARRAT");
    fields[19] = fieldE01FRECUE =
    new CharacterField(message, HEADERSIZE + 145, 1, "E01FRECUE");
    fields[20] = fieldE01PRETYP =
    new CharacterField(message, HEADERSIZE + 146, 1, "E01PRETYP");
    fields[21] = fieldE01BTHUBK =
    new CharacterField(message, HEADERSIZE + 147, 2, "E01BTHUBK");
    fields[22] = fieldE01BTHUBR =
    new DecimalField(message, HEADERSIZE + 149, 4, 0, "E01BTHUBR");
    fields[23] = fieldE01BTHDIB =
    new DecimalField(message, HEADERSIZE + 153, 5, 0, "E01BTHDIB");
    fields[24] = fieldE01CNODSC =
    new CharacterField(message, HEADERSIZE + 158, 35, "E01CNODSC");
    fields[25] = fieldE01GLMDSC =
    new CharacterField(message, HEADERSIZE + 193, 35, "E01GLMDSC");
    fields[26] = fieldE01TOTAMT =
    new DecimalField(message, HEADERSIZE + 228, 17, 2, "E01TOTAMT");
    fields[27] = fieldE01CUSNA1 =
    new CharacterField(message, HEADERSIZE + 245, 45, "E01CUSNA1");
    fields[28] = fieldE01CUSLN3 =
    new CharacterField(message, HEADERSIZE + 290, 30, "E01CUSLN3");
    fields[29] = fieldE01CUFTOM =
    new CharacterField(message, HEADERSIZE + 320, 8, "E01CUFTOM");
    fields[30] = fieldE01DEAACC =
    new DecimalField(message, HEADERSIZE + 328, 13, 0, "E01DEAACC");
    fields[31] = fieldE01DLCVA1 =
    new DecimalField(message, HEADERSIZE + 341, 17, 2, "E01DLCVA1");
    fields[32] = fieldE01CUSLN1 =
    new CharacterField(message, HEADERSIZE + 358, 30, "E01CUSLN1");
    fields[33] = fieldE01CUSBRN =
    new DecimalField(message, HEADERSIZE + 388, 4, 0, "E01CUSBRN");
    fields[34] = fieldE01DLCVA2 =
    new DecimalField(message, HEADERSIZE + 392, 17, 2, "E01DLCVA2");
    fields[35] = fieldE01NUMREC =
    new DecimalField(message, HEADERSIZE + 409, 8, 0, "E01NUMREC");
    fields[36] = fieldE01INDOPE =
    new CharacterField(message, HEADERSIZE + 417, 1, "E01INDOPE");

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
  * Set field E01CNOMID using a String value.
  */
  public void setE01CNOMID(String newvalue)
  {
    fieldE01CNOMID.setString(newvalue);
  }

  /**
  * Get value of field E01CNOMID as a String.
  */
  public String getE01CNOMID()
  {
    return fieldE01CNOMID.getString();
  }

  /**
  * Set field E01ORDLST using a String value.
  */
  public void setE01ORDLST(String newvalue)
  {
    fieldE01ORDLST.setString(newvalue);
  }

  /**
  * Get value of field E01ORDLST as a String.
  */
  public String getE01ORDLST()
  {
    return fieldE01ORDLST.getString();
  }

  /**
  * Set field E01OFFOPC using a String value.
  */
  public void setE01OFFOPC(String newvalue)
  {
    fieldE01OFFOPC.setString(newvalue);
  }

  /**
  * Get value of field E01OFFOPC as a String.
  */
  public String getE01OFFOPC()
  {
    return fieldE01OFFOPC.getString();
  }

  /**
  * Set field E01OFFCON using a String value.
  */
  public void setE01OFFCON(String newvalue)
  {
    fieldE01OFFCON.setString(newvalue);
  }

  /**
  * Get value of field E01OFFCON as a String.
  */
  public String getE01OFFCON()
  {
    return fieldE01OFFCON.getString();
  }

  /**
  * Set field E01OFFBNK using a String value.
  */
  public void setE01OFFBNK(String newvalue)
  {
    fieldE01OFFBNK.setString(newvalue);
  }

  /**
  * Get value of field E01OFFBNK as a String.
  */
  public String getE01OFFBNK()
  {
    return fieldE01OFFBNK.getString();
  }

  /**
  * Set field E01OFFBRN using a String value.
  */
  public void setE01OFFBRN(String newvalue)
  {
    fieldE01OFFBRN.setString(newvalue);
  }

  /**
  * Get value of field E01OFFBRN as a String.
  */
  public String getE01OFFBRN()
  {
    return fieldE01OFFBRN.getString();
  }

  /**
  * Set numeric field E01OFFBRN using a BigDecimal value.
  */
  public void setE01OFFBRN(BigDecimal newvalue)
  {
    fieldE01OFFBRN.setBigDecimal(newvalue);
  }

  /**
  * Return value of numeric field E01OFFBRN as a BigDecimal.
  */
  public BigDecimal getBigDecimalE01OFFBRN()
  {
    return fieldE01OFFBRN.getBigDecimal();
  }

  /**
  * Set field E01OFFCCY using a String value.
  */
  public void setE01OFFCCY(String newvalue)
  {
    fieldE01OFFCCY.setString(newvalue);
  }

  /**
  * Get value of field E01OFFCCY as a String.
  */
  public String getE01OFFCCY()
  {
    return fieldE01OFFCCY.getString();
  }

  /**
  * Set field E01OFFGLN using a String value.
  */
  public void setE01OFFGLN(String newvalue)
  {
    fieldE01OFFGLN.setString(newvalue);
  }

  /**
  * Get value of field E01OFFGLN as a String.
  */
  public String getE01OFFGLN()
  {
    return fieldE01OFFGLN.getString();
  }

  /**
  * Set numeric field E01OFFGLN using a BigDecimal value.
  */
  public void setE01OFFGLN(BigDecimal newvalue)
  {
    fieldE01OFFGLN.setBigDecimal(newvalue);
  }

  /**
  * Return value of numeric field E01OFFGLN as a BigDecimal.
  */
  public BigDecimal getBigDecimalE01OFFGLN()
  {
    return fieldE01OFFGLN.getBigDecimal();
  }

  /**
  * Set field E01OFFACC using a String value.
  */
  public void setE01OFFACC(String newvalue)
  {
    fieldE01OFFACC.setString(newvalue);
  }

  /**
  * Get value of field E01OFFACC as a String.
  */
  public String getE01OFFACC()
  {
    return fieldE01OFFACC.getString();
  }

  /**
  * Set numeric field E01OFFACC using a BigDecimal value.
  */
  public void setE01OFFACC(BigDecimal newvalue)
  {
    fieldE01OFFACC.setBigDecimal(newvalue);
  }

  /**
  * Return value of numeric field E01OFFACC as a BigDecimal.
  */
  public BigDecimal getBigDecimalE01OFFACC()
  {
    return fieldE01OFFACC.getBigDecimal();
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
  * Set field E01FRECUE using a String value.
  */
  public void setE01FRECUE(String newvalue)
  {
    fieldE01FRECUE.setString(newvalue);
  }

  /**
  * Get value of field E01FRECUE as a String.
  */
  public String getE01FRECUE()
  {
    return fieldE01FRECUE.getString();
  }

  /**
  * Set field E01PRETYP using a String value.
  */
  public void setE01PRETYP(String newvalue)
  {
    fieldE01PRETYP.setString(newvalue);
  }

  /**
  * Get value of field E01PRETYP as a String.
  */
  public String getE01PRETYP()
  {
    return fieldE01PRETYP.getString();
  }

  /**
  * Set field E01BTHUBK using a String value.
  */
  public void setE01BTHUBK(String newvalue)
  {
    fieldE01BTHUBK.setString(newvalue);
  }

  /**
  * Get value of field E01BTHUBK as a String.
  */
  public String getE01BTHUBK()
  {
    return fieldE01BTHUBK.getString();
  }

  /**
  * Set field E01BTHUBR using a String value.
  */
  public void setE01BTHUBR(String newvalue)
  {
    fieldE01BTHUBR.setString(newvalue);
  }

  /**
  * Get value of field E01BTHUBR as a String.
  */
  public String getE01BTHUBR()
  {
    return fieldE01BTHUBR.getString();
  }

  /**
  * Set numeric field E01BTHUBR using a BigDecimal value.
  */
  public void setE01BTHUBR(BigDecimal newvalue)
  {
    fieldE01BTHUBR.setBigDecimal(newvalue);
  }

  /**
  * Return value of numeric field E01BTHUBR as a BigDecimal.
  */
  public BigDecimal getBigDecimalE01BTHUBR()
  {
    return fieldE01BTHUBR.getBigDecimal();
  }

  /**
  * Set field E01BTHDIB using a String value.
  */
  public void setE01BTHDIB(String newvalue)
  {
    fieldE01BTHDIB.setString(newvalue);
  }

  /**
  * Get value of field E01BTHDIB as a String.
  */
  public String getE01BTHDIB()
  {
    return fieldE01BTHDIB.getString();
  }

  /**
  * Set numeric field E01BTHDIB using a BigDecimal value.
  */
  public void setE01BTHDIB(BigDecimal newvalue)
  {
    fieldE01BTHDIB.setBigDecimal(newvalue);
  }

  /**
  * Return value of numeric field E01BTHDIB as a BigDecimal.
  */
  public BigDecimal getBigDecimalE01BTHDIB()
  {
    return fieldE01BTHDIB.getBigDecimal();
  }

  /**
  * Set field E01CNODSC using a String value.
  */
  public void setE01CNODSC(String newvalue)
  {
    fieldE01CNODSC.setString(newvalue);
  }

  /**
  * Get value of field E01CNODSC as a String.
  */
  public String getE01CNODSC()
  {
    return fieldE01CNODSC.getString();
  }

  /**
  * Set field E01GLMDSC using a String value.
  */
  public void setE01GLMDSC(String newvalue)
  {
    fieldE01GLMDSC.setString(newvalue);
  }

  /**
  * Get value of field E01GLMDSC as a String.
  */
  public String getE01GLMDSC()
  {
    return fieldE01GLMDSC.getString();
  }

  /**
  * Set field E01TOTAMT using a String value.
  */
  public void setE01TOTAMT(String newvalue)
  {
    fieldE01TOTAMT.setString(newvalue);
  }

  /**
  * Get value of field E01TOTAMT as a String.
  */
  public String getE01TOTAMT()
  {
    return fieldE01TOTAMT.getString();
  }

  /**
  * Set numeric field E01TOTAMT using a BigDecimal value.
  */
  public void setE01TOTAMT(BigDecimal newvalue)
  {
    fieldE01TOTAMT.setBigDecimal(newvalue);
  }

  /**
  * Return value of numeric field E01TOTAMT as a BigDecimal.
  */
  public BigDecimal getBigDecimalE01TOTAMT()
  {
    return fieldE01TOTAMT.getBigDecimal();
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
  * Set field E01CUSLN3 using a String value.
  */
  public void setE01CUSLN3(String newvalue)
  {
    fieldE01CUSLN3.setString(newvalue);
  }

  /**
  * Get value of field E01CUSLN3 as a String.
  */
  public String getE01CUSLN3()
  {
    return fieldE01CUSLN3.getString();
  }

  /**
  * Set field E01CUFTOM using a String value.
  */
  public void setE01CUFTOM(String newvalue)
  {
    fieldE01CUFTOM.setString(newvalue);
  }

  /**
  * Get value of field E01CUFTOM as a String.
  */
  public String getE01CUFTOM()
  {
    return fieldE01CUFTOM.getString();
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
  * Set field E01DLCVA1 using a String value.
  */
  public void setE01DLCVA1(String newvalue)
  {
    fieldE01DLCVA1.setString(newvalue);
  }

  /**
  * Get value of field E01DLCVA1 as a String.
  */
  public String getE01DLCVA1()
  {
    return fieldE01DLCVA1.getString();
  }

  /**
  * Set numeric field E01DLCVA1 using a BigDecimal value.
  */
  public void setE01DLCVA1(BigDecimal newvalue)
  {
    fieldE01DLCVA1.setBigDecimal(newvalue);
  }

  /**
  * Return value of numeric field E01DLCVA1 as a BigDecimal.
  */
  public BigDecimal getBigDecimalE01DLCVA1()
  {
    return fieldE01DLCVA1.getBigDecimal();
  }

  /**
  * Set field E01CUSLN1 using a String value.
  */
  public void setE01CUSLN1(String newvalue)
  {
    fieldE01CUSLN1.setString(newvalue);
  }

  /**
  * Get value of field E01CUSLN1 as a String.
  */
  public String getE01CUSLN1()
  {
    return fieldE01CUSLN1.getString();
  }

  /**
  * Set field E01CUSBRN using a String value.
  */
  public void setE01CUSBRN(String newvalue)
  {
    fieldE01CUSBRN.setString(newvalue);
  }

  /**
  * Get value of field E01CUSBRN as a String.
  */
  public String getE01CUSBRN()
  {
    return fieldE01CUSBRN.getString();
  }

  /**
  * Set numeric field E01CUSBRN using a BigDecimal value.
  */
  public void setE01CUSBRN(BigDecimal newvalue)
  {
    fieldE01CUSBRN.setBigDecimal(newvalue);
  }

  /**
  * Return value of numeric field E01CUSBRN as a BigDecimal.
  */
  public BigDecimal getBigDecimalE01CUSBRN()
  {
    return fieldE01CUSBRN.getBigDecimal();
  }

  /**
  * Set field E01DLCVA2 using a String value.
  */
  public void setE01DLCVA2(String newvalue)
  {
    fieldE01DLCVA2.setString(newvalue);
  }

  /**
  * Get value of field E01DLCVA2 as a String.
  */
  public String getE01DLCVA2()
  {
    return fieldE01DLCVA2.getString();
  }

  /**
  * Set numeric field E01DLCVA2 using a BigDecimal value.
  */
  public void setE01DLCVA2(BigDecimal newvalue)
  {
    fieldE01DLCVA2.setBigDecimal(newvalue);
  }

  /**
  * Return value of numeric field E01DLCVA2 as a BigDecimal.
  */
  public BigDecimal getBigDecimalE01DLCVA2()
  {
    return fieldE01DLCVA2.getBigDecimal();
  }

  /**
  * Set field E01NUMREC using a String value.
  */
  public void setE01NUMREC(String newvalue)
  {
    fieldE01NUMREC.setString(newvalue);
  }

  /**
  * Get value of field E01NUMREC as a String.
  */
  public String getE01NUMREC()
  {
    return fieldE01NUMREC.getString();
  }

  /**
  * Set numeric field E01NUMREC using a BigDecimal value.
  */
  public void setE01NUMREC(BigDecimal newvalue)
  {
    fieldE01NUMREC.setBigDecimal(newvalue);
  }

  /**
  * Return value of numeric field E01NUMREC as a BigDecimal.
  */
  public BigDecimal getBigDecimalE01NUMREC()
  {
    return fieldE01NUMREC.getBigDecimal();
  }

  /**
  * Set field E01INDOPE using a String value.
  */
  public void setE01INDOPE(String newvalue)
  {
    fieldE01INDOPE.setString(newvalue);
  }

  /**
  * Get value of field E01INDOPE as a String.
  */
  public String getE01INDOPE()
  {
    return fieldE01INDOPE.getString();
  }

}
