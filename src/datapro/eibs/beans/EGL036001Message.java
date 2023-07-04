package datapro.eibs.beans;

import datapro.eibs.sockets.*;
import java.io.*;
import java.math.*;

import java.util.*;

/**
* Class generated by AS/400 CRTCLASS command from EGL036001 physical file definition.
* 
* File level identifier is 1031118180844.
* Record format level identifier is 5092BDFC4FE8A.
*/

public class EGL036001Message extends MessageRecord
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
                                     "E01RATBNK",
                                     "E01RATCCY",
                                     "E01CNTBCU",
                                     "E01CNTRD1",
                                     "E01CNTRD2",
                                     "E01CNTRD3",
                                     "E01RATEXR",
                                     "E01RATMUD",
                                     "E01RATDCP",
                                     "E01RATDSC",
                                     "E01RATCYA",
                                     "E01RATRNM",
                                     "E01RATSCY",
                                     "E01RATPBK",
                                     "E01RATPCY",
                                     "E01RATPGL",
                                     "E01GLDSCP",
                                     "E01RATVBK",
                                     "E01RATVCY",
                                     "E01RATVGL",
                                     "E01GLDSCV",
                                     "E01RATBK1",
                                     "E01RATCY1",
                                     "E01RATGL1",
                                     "E01GLDSC1",
                                     "E01RATBK2",
                                     "E01RATCY2",
                                     "E01RATGL2",
                                     "E01GLDSC2",
                                     "E01RATBK3",
                                     "E01RATCY3",
                                     "E01RATGL3",
                                     "E01GLDSC3",
                                     "E01RATBK4",
                                     "E01RATCY4",
                                     "E01RATGL4",
                                     "E01GLDSC4",
                                     "E01OPECDE"
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
                                   "E01RATBNK",
                                   "E01RATCCY",
                                   "E01CNTBCU",
                                   "E01CNTRD1",
                                   "E01CNTRD2",
                                   "E01CNTRD3",
                                   "E01RATEXR",
                                   "E01RATMUD",
                                   "E01RATDCP",
                                   "E01RATDSC",
                                   "E01RATCYA",
                                   "E01RATRNM",
                                   "E01RATSCY",
                                   "E01RATPBK",
                                   "E01RATPCY",
                                   "E01RATPGL",
                                   "E01GLDSCP",
                                   "E01RATVBK",
                                   "E01RATVCY",
                                   "E01RATVGL",
                                   "E01GLDSCV",
                                   "E01RATBK1",
                                   "E01RATCY1",
                                   "E01RATGL1",
                                   "E01GLDSC1",
                                   "E01RATBK2",
                                   "E01RATCY2",
                                   "E01RATGL2",
                                   "E01GLDSC2",
                                   "E01RATBK3",
                                   "E01RATCY3",
                                   "E01RATGL3",
                                   "E01GLDSC3",
                                   "E01RATBK4",
                                   "E01RATCY4",
                                   "E01RATGL4",
                                   "E01GLDSC4",
                                   "E01OPECDE"
                                 };
  final static String fid = "1031118180844";
  final static String rid = "5092BDFC4FE8A";
  final static String fmtname = "EGL036001";
  final int FIELDCOUNT = 47;
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
  private CharacterField fieldE01RATBNK = null;
  private CharacterField fieldE01RATCCY = null;
  private CharacterField fieldE01CNTBCU = null;
  private DecimalField fieldE01CNTRD1 = null;
  private DecimalField fieldE01CNTRD2 = null;
  private DecimalField fieldE01CNTRD3 = null;
  private DecimalField fieldE01RATEXR = null;
  private CharacterField fieldE01RATMUD = null;
  private DecimalField fieldE01RATDCP = null;
  private CharacterField fieldE01RATDSC = null;
  private CharacterField fieldE01RATCYA = null;
  private CharacterField fieldE01RATRNM = null;
  private CharacterField fieldE01RATSCY = null;
  private CharacterField fieldE01RATPBK = null;
  private CharacterField fieldE01RATPCY = null;
  private DecimalField fieldE01RATPGL = null;
  private CharacterField fieldE01GLDSCP = null;
  private CharacterField fieldE01RATVBK = null;
  private CharacterField fieldE01RATVCY = null;
  private DecimalField fieldE01RATVGL = null;
  private CharacterField fieldE01GLDSCV = null;
  private CharacterField fieldE01RATBK1 = null;
  private CharacterField fieldE01RATCY1 = null;
  private DecimalField fieldE01RATGL1 = null;
  private CharacterField fieldE01GLDSC1 = null;
  private CharacterField fieldE01RATBK2 = null;
  private CharacterField fieldE01RATCY2 = null;
  private DecimalField fieldE01RATGL2 = null;
  private CharacterField fieldE01GLDSC2 = null;
  private CharacterField fieldE01RATBK3 = null;
  private CharacterField fieldE01RATCY3 = null;
  private DecimalField fieldE01RATGL3 = null;
  private CharacterField fieldE01GLDSC3 = null;
  private CharacterField fieldE01RATBK4 = null;
  private CharacterField fieldE01RATCY4 = null;
  private DecimalField fieldE01RATGL4 = null;
  private CharacterField fieldE01GLDSC4 = null;
  private CharacterField fieldE01OPECDE = null;

  /**
  * Constructor for EGL036001Message.
  */
  public EGL036001Message()
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
    recordsize = 472;
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
    fields[9] = fieldE01RATBNK =
    new CharacterField(message, HEADERSIZE + 42, 2, "E01RATBNK");
    fields[10] = fieldE01RATCCY =
    new CharacterField(message, HEADERSIZE + 44, 3, "E01RATCCY");
    fields[11] = fieldE01CNTBCU =
    new CharacterField(message, HEADERSIZE + 47, 3, "E01CNTBCU");
    fields[12] = fieldE01CNTRD1 =
    new DecimalField(message, HEADERSIZE + 50, 3, 0, "E01CNTRD1");
    fields[13] = fieldE01CNTRD2 =
    new DecimalField(message, HEADERSIZE + 53, 3, 0, "E01CNTRD2");
    fields[14] = fieldE01CNTRD3 =
    new DecimalField(message, HEADERSIZE + 56, 3, 0, "E01CNTRD3");
    fields[15] = fieldE01RATEXR =
    new DecimalField(message, HEADERSIZE + 59, 13, 6, "E01RATEXR");
    fields[16] = fieldE01RATMUD =
    new CharacterField(message, HEADERSIZE + 72, 1, "E01RATMUD");
    fields[17] = fieldE01RATDCP =
    new DecimalField(message, HEADERSIZE + 73, 2, 0, "E01RATDCP");
    fields[18] = fieldE01RATDSC =
    new CharacterField(message, HEADERSIZE + 75, 35, "E01RATDSC");
    fields[19] = fieldE01RATCYA =
    new CharacterField(message, HEADERSIZE + 110, 12, "E01RATCYA");
    fields[20] = fieldE01RATRNM =
    new CharacterField(message, HEADERSIZE + 122, 4, "E01RATRNM");
    fields[21] = fieldE01RATSCY =
    new CharacterField(message, HEADERSIZE + 126, 3, "E01RATSCY");
    fields[22] = fieldE01RATPBK =
    new CharacterField(message, HEADERSIZE + 129, 2, "E01RATPBK");
    fields[23] = fieldE01RATPCY =
    new CharacterField(message, HEADERSIZE + 131, 3, "E01RATPCY");
    fields[24] = fieldE01RATPGL =
    new DecimalField(message, HEADERSIZE + 134, 17, 0, "E01RATPGL");
    fields[25] = fieldE01GLDSCP =
    new CharacterField(message, HEADERSIZE + 151, 35, "E01GLDSCP");
    fields[26] = fieldE01RATVBK =
    new CharacterField(message, HEADERSIZE + 186, 2, "E01RATVBK");
    fields[27] = fieldE01RATVCY =
    new CharacterField(message, HEADERSIZE + 188, 3, "E01RATVCY");
    fields[28] = fieldE01RATVGL =
    new DecimalField(message, HEADERSIZE + 191, 17, 0, "E01RATVGL");
    fields[29] = fieldE01GLDSCV =
    new CharacterField(message, HEADERSIZE + 208, 35, "E01GLDSCV");
    fields[30] = fieldE01RATBK1 =
    new CharacterField(message, HEADERSIZE + 243, 2, "E01RATBK1");
    fields[31] = fieldE01RATCY1 =
    new CharacterField(message, HEADERSIZE + 245, 3, "E01RATCY1");
    fields[32] = fieldE01RATGL1 =
    new DecimalField(message, HEADERSIZE + 248, 17, 0, "E01RATGL1");
    fields[33] = fieldE01GLDSC1 =
    new CharacterField(message, HEADERSIZE + 265, 35, "E01GLDSC1");
    fields[34] = fieldE01RATBK2 =
    new CharacterField(message, HEADERSIZE + 300, 2, "E01RATBK2");
    fields[35] = fieldE01RATCY2 =
    new CharacterField(message, HEADERSIZE + 302, 3, "E01RATCY2");
    fields[36] = fieldE01RATGL2 =
    new DecimalField(message, HEADERSIZE + 305, 17, 0, "E01RATGL2");
    fields[37] = fieldE01GLDSC2 =
    new CharacterField(message, HEADERSIZE + 322, 35, "E01GLDSC2");
    fields[38] = fieldE01RATBK3 =
    new CharacterField(message, HEADERSIZE + 357, 2, "E01RATBK3");
    fields[39] = fieldE01RATCY3 =
    new CharacterField(message, HEADERSIZE + 359, 3, "E01RATCY3");
    fields[40] = fieldE01RATGL3 =
    new DecimalField(message, HEADERSIZE + 362, 17, 0, "E01RATGL3");
    fields[41] = fieldE01GLDSC3 =
    new CharacterField(message, HEADERSIZE + 379, 35, "E01GLDSC3");
    fields[42] = fieldE01RATBK4 =
    new CharacterField(message, HEADERSIZE + 414, 2, "E01RATBK4");
    fields[43] = fieldE01RATCY4 =
    new CharacterField(message, HEADERSIZE + 416, 3, "E01RATCY4");
    fields[44] = fieldE01RATGL4 =
    new DecimalField(message, HEADERSIZE + 419, 17, 0, "E01RATGL4");
    fields[45] = fieldE01GLDSC4 =
    new CharacterField(message, HEADERSIZE + 436, 35, "E01GLDSC4");
    fields[46] = fieldE01OPECDE =
    new CharacterField(message, HEADERSIZE + 471, 1, "E01OPECDE");

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
  * Set field E01RATBNK using a String value.
  */
  public void setE01RATBNK(String newvalue)
  {
    fieldE01RATBNK.setString(newvalue);
  }

  /**
  * Get value of field E01RATBNK as a String.
  */
  public String getE01RATBNK()
  {
    return fieldE01RATBNK.getString();
  }

  /**
  * Set field E01RATCCY using a String value.
  */
  public void setE01RATCCY(String newvalue)
  {
    fieldE01RATCCY.setString(newvalue);
  }

  /**
  * Get value of field E01RATCCY as a String.
  */
  public String getE01RATCCY()
  {
    return fieldE01RATCCY.getString();
  }

  /**
  * Set field E01CNTBCU using a String value.
  */
  public void setE01CNTBCU(String newvalue)
  {
    fieldE01CNTBCU.setString(newvalue);
  }

  /**
  * Get value of field E01CNTBCU as a String.
  */
  public String getE01CNTBCU()
  {
    return fieldE01CNTBCU.getString();
  }

  /**
  * Set field E01CNTRD1 using a String value.
  */
  public void setE01CNTRD1(String newvalue)
  {
    fieldE01CNTRD1.setString(newvalue);
  }

  /**
  * Get value of field E01CNTRD1 as a String.
  */
  public String getE01CNTRD1()
  {
    return fieldE01CNTRD1.getString();
  }

  /**
  * Set numeric field E01CNTRD1 using a BigDecimal value.
  */
  public void setE01CNTRD1(BigDecimal newvalue)
  {
    fieldE01CNTRD1.setBigDecimal(newvalue);
  }

  /**
  * Return value of numeric field E01CNTRD1 as a BigDecimal.
  */
  public BigDecimal getBigDecimalE01CNTRD1()
  {
    return fieldE01CNTRD1.getBigDecimal();
  }

  /**
  * Set field E01CNTRD2 using a String value.
  */
  public void setE01CNTRD2(String newvalue)
  {
    fieldE01CNTRD2.setString(newvalue);
  }

  /**
  * Get value of field E01CNTRD2 as a String.
  */
  public String getE01CNTRD2()
  {
    return fieldE01CNTRD2.getString();
  }

  /**
  * Set numeric field E01CNTRD2 using a BigDecimal value.
  */
  public void setE01CNTRD2(BigDecimal newvalue)
  {
    fieldE01CNTRD2.setBigDecimal(newvalue);
  }

  /**
  * Return value of numeric field E01CNTRD2 as a BigDecimal.
  */
  public BigDecimal getBigDecimalE01CNTRD2()
  {
    return fieldE01CNTRD2.getBigDecimal();
  }

  /**
  * Set field E01CNTRD3 using a String value.
  */
  public void setE01CNTRD3(String newvalue)
  {
    fieldE01CNTRD3.setString(newvalue);
  }

  /**
  * Get value of field E01CNTRD3 as a String.
  */
  public String getE01CNTRD3()
  {
    return fieldE01CNTRD3.getString();
  }

  /**
  * Set numeric field E01CNTRD3 using a BigDecimal value.
  */
  public void setE01CNTRD3(BigDecimal newvalue)
  {
    fieldE01CNTRD3.setBigDecimal(newvalue);
  }

  /**
  * Return value of numeric field E01CNTRD3 as a BigDecimal.
  */
  public BigDecimal getBigDecimalE01CNTRD3()
  {
    return fieldE01CNTRD3.getBigDecimal();
  }

  /**
  * Set field E01RATEXR using a String value.
  */
  public void setE01RATEXR(String newvalue)
  {
    fieldE01RATEXR.setString(newvalue);
  }

  /**
  * Get value of field E01RATEXR as a String.
  */
  public String getE01RATEXR()
  {
    return fieldE01RATEXR.getString();
  }

  /**
  * Set numeric field E01RATEXR using a BigDecimal value.
  */
  public void setE01RATEXR(BigDecimal newvalue)
  {
    fieldE01RATEXR.setBigDecimal(newvalue);
  }

  /**
  * Return value of numeric field E01RATEXR as a BigDecimal.
  */
  public BigDecimal getBigDecimalE01RATEXR()
  {
    return fieldE01RATEXR.getBigDecimal();
  }

  /**
  * Set field E01RATMUD using a String value.
  */
  public void setE01RATMUD(String newvalue)
  {
    fieldE01RATMUD.setString(newvalue);
  }

  /**
  * Get value of field E01RATMUD as a String.
  */
  public String getE01RATMUD()
  {
    return fieldE01RATMUD.getString();
  }

  /**
  * Set field E01RATDCP using a String value.
  */
  public void setE01RATDCP(String newvalue)
  {
    fieldE01RATDCP.setString(newvalue);
  }

  /**
  * Get value of field E01RATDCP as a String.
  */
  public String getE01RATDCP()
  {
    return fieldE01RATDCP.getString();
  }

  /**
  * Set numeric field E01RATDCP using a BigDecimal value.
  */
  public void setE01RATDCP(BigDecimal newvalue)
  {
    fieldE01RATDCP.setBigDecimal(newvalue);
  }

  /**
  * Return value of numeric field E01RATDCP as a BigDecimal.
  */
  public BigDecimal getBigDecimalE01RATDCP()
  {
    return fieldE01RATDCP.getBigDecimal();
  }

  /**
  * Set field E01RATDSC using a String value.
  */
  public void setE01RATDSC(String newvalue)
  {
    fieldE01RATDSC.setString(newvalue);
  }

  /**
  * Get value of field E01RATDSC as a String.
  */
  public String getE01RATDSC()
  {
    return fieldE01RATDSC.getString();
  }

  /**
  * Set field E01RATCYA using a String value.
  */
  public void setE01RATCYA(String newvalue)
  {
    fieldE01RATCYA.setString(newvalue);
  }

  /**
  * Get value of field E01RATCYA as a String.
  */
  public String getE01RATCYA()
  {
    return fieldE01RATCYA.getString();
  }

  /**
  * Set field E01RATRNM using a String value.
  */
  public void setE01RATRNM(String newvalue)
  {
    fieldE01RATRNM.setString(newvalue);
  }

  /**
  * Get value of field E01RATRNM as a String.
  */
  public String getE01RATRNM()
  {
    return fieldE01RATRNM.getString();
  }

  /**
  * Set field E01RATSCY using a String value.
  */
  public void setE01RATSCY(String newvalue)
  {
    fieldE01RATSCY.setString(newvalue);
  }

  /**
  * Get value of field E01RATSCY as a String.
  */
  public String getE01RATSCY()
  {
    return fieldE01RATSCY.getString();
  }

  /**
  * Set field E01RATPBK using a String value.
  */
  public void setE01RATPBK(String newvalue)
  {
    fieldE01RATPBK.setString(newvalue);
  }

  /**
  * Get value of field E01RATPBK as a String.
  */
  public String getE01RATPBK()
  {
    return fieldE01RATPBK.getString();
  }

  /**
  * Set field E01RATPCY using a String value.
  */
  public void setE01RATPCY(String newvalue)
  {
    fieldE01RATPCY.setString(newvalue);
  }

  /**
  * Get value of field E01RATPCY as a String.
  */
  public String getE01RATPCY()
  {
    return fieldE01RATPCY.getString();
  }

  /**
  * Set field E01RATPGL using a String value.
  */
  public void setE01RATPGL(String newvalue)
  {
    fieldE01RATPGL.setString(newvalue);
  }

  /**
  * Get value of field E01RATPGL as a String.
  */
  public String getE01RATPGL()
  {
    return fieldE01RATPGL.getString();
  }

  /**
  * Set numeric field E01RATPGL using a BigDecimal value.
  */
  public void setE01RATPGL(BigDecimal newvalue)
  {
    fieldE01RATPGL.setBigDecimal(newvalue);
  }

  /**
  * Return value of numeric field E01RATPGL as a BigDecimal.
  */
  public BigDecimal getBigDecimalE01RATPGL()
  {
    return fieldE01RATPGL.getBigDecimal();
  }

  /**
  * Set field E01GLDSCP using a String value.
  */
  public void setE01GLDSCP(String newvalue)
  {
    fieldE01GLDSCP.setString(newvalue);
  }

  /**
  * Get value of field E01GLDSCP as a String.
  */
  public String getE01GLDSCP()
  {
    return fieldE01GLDSCP.getString();
  }

  /**
  * Set field E01RATVBK using a String value.
  */
  public void setE01RATVBK(String newvalue)
  {
    fieldE01RATVBK.setString(newvalue);
  }

  /**
  * Get value of field E01RATVBK as a String.
  */
  public String getE01RATVBK()
  {
    return fieldE01RATVBK.getString();
  }

  /**
  * Set field E01RATVCY using a String value.
  */
  public void setE01RATVCY(String newvalue)
  {
    fieldE01RATVCY.setString(newvalue);
  }

  /**
  * Get value of field E01RATVCY as a String.
  */
  public String getE01RATVCY()
  {
    return fieldE01RATVCY.getString();
  }

  /**
  * Set field E01RATVGL using a String value.
  */
  public void setE01RATVGL(String newvalue)
  {
    fieldE01RATVGL.setString(newvalue);
  }

  /**
  * Get value of field E01RATVGL as a String.
  */
  public String getE01RATVGL()
  {
    return fieldE01RATVGL.getString();
  }

  /**
  * Set numeric field E01RATVGL using a BigDecimal value.
  */
  public void setE01RATVGL(BigDecimal newvalue)
  {
    fieldE01RATVGL.setBigDecimal(newvalue);
  }

  /**
  * Return value of numeric field E01RATVGL as a BigDecimal.
  */
  public BigDecimal getBigDecimalE01RATVGL()
  {
    return fieldE01RATVGL.getBigDecimal();
  }

  /**
  * Set field E01GLDSCV using a String value.
  */
  public void setE01GLDSCV(String newvalue)
  {
    fieldE01GLDSCV.setString(newvalue);
  }

  /**
  * Get value of field E01GLDSCV as a String.
  */
  public String getE01GLDSCV()
  {
    return fieldE01GLDSCV.getString();
  }

  /**
  * Set field E01RATBK1 using a String value.
  */
  public void setE01RATBK1(String newvalue)
  {
    fieldE01RATBK1.setString(newvalue);
  }

  /**
  * Get value of field E01RATBK1 as a String.
  */
  public String getE01RATBK1()
  {
    return fieldE01RATBK1.getString();
  }

  /**
  * Set field E01RATCY1 using a String value.
  */
  public void setE01RATCY1(String newvalue)
  {
    fieldE01RATCY1.setString(newvalue);
  }

  /**
  * Get value of field E01RATCY1 as a String.
  */
  public String getE01RATCY1()
  {
    return fieldE01RATCY1.getString();
  }

  /**
  * Set field E01RATGL1 using a String value.
  */
  public void setE01RATGL1(String newvalue)
  {
    fieldE01RATGL1.setString(newvalue);
  }

  /**
  * Get value of field E01RATGL1 as a String.
  */
  public String getE01RATGL1()
  {
    return fieldE01RATGL1.getString();
  }

  /**
  * Set numeric field E01RATGL1 using a BigDecimal value.
  */
  public void setE01RATGL1(BigDecimal newvalue)
  {
    fieldE01RATGL1.setBigDecimal(newvalue);
  }

  /**
  * Return value of numeric field E01RATGL1 as a BigDecimal.
  */
  public BigDecimal getBigDecimalE01RATGL1()
  {
    return fieldE01RATGL1.getBigDecimal();
  }

  /**
  * Set field E01GLDSC1 using a String value.
  */
  public void setE01GLDSC1(String newvalue)
  {
    fieldE01GLDSC1.setString(newvalue);
  }

  /**
  * Get value of field E01GLDSC1 as a String.
  */
  public String getE01GLDSC1()
  {
    return fieldE01GLDSC1.getString();
  }

  /**
  * Set field E01RATBK2 using a String value.
  */
  public void setE01RATBK2(String newvalue)
  {
    fieldE01RATBK2.setString(newvalue);
  }

  /**
  * Get value of field E01RATBK2 as a String.
  */
  public String getE01RATBK2()
  {
    return fieldE01RATBK2.getString();
  }

  /**
  * Set field E01RATCY2 using a String value.
  */
  public void setE01RATCY2(String newvalue)
  {
    fieldE01RATCY2.setString(newvalue);
  }

  /**
  * Get value of field E01RATCY2 as a String.
  */
  public String getE01RATCY2()
  {
    return fieldE01RATCY2.getString();
  }

  /**
  * Set field E01RATGL2 using a String value.
  */
  public void setE01RATGL2(String newvalue)
  {
    fieldE01RATGL2.setString(newvalue);
  }

  /**
  * Get value of field E01RATGL2 as a String.
  */
  public String getE01RATGL2()
  {
    return fieldE01RATGL2.getString();
  }

  /**
  * Set numeric field E01RATGL2 using a BigDecimal value.
  */
  public void setE01RATGL2(BigDecimal newvalue)
  {
    fieldE01RATGL2.setBigDecimal(newvalue);
  }

  /**
  * Return value of numeric field E01RATGL2 as a BigDecimal.
  */
  public BigDecimal getBigDecimalE01RATGL2()
  {
    return fieldE01RATGL2.getBigDecimal();
  }

  /**
  * Set field E01GLDSC2 using a String value.
  */
  public void setE01GLDSC2(String newvalue)
  {
    fieldE01GLDSC2.setString(newvalue);
  }

  /**
  * Get value of field E01GLDSC2 as a String.
  */
  public String getE01GLDSC2()
  {
    return fieldE01GLDSC2.getString();
  }

  /**
  * Set field E01RATBK3 using a String value.
  */
  public void setE01RATBK3(String newvalue)
  {
    fieldE01RATBK3.setString(newvalue);
  }

  /**
  * Get value of field E01RATBK3 as a String.
  */
  public String getE01RATBK3()
  {
    return fieldE01RATBK3.getString();
  }

  /**
  * Set field E01RATCY3 using a String value.
  */
  public void setE01RATCY3(String newvalue)
  {
    fieldE01RATCY3.setString(newvalue);
  }

  /**
  * Get value of field E01RATCY3 as a String.
  */
  public String getE01RATCY3()
  {
    return fieldE01RATCY3.getString();
  }

  /**
  * Set field E01RATGL3 using a String value.
  */
  public void setE01RATGL3(String newvalue)
  {
    fieldE01RATGL3.setString(newvalue);
  }

  /**
  * Get value of field E01RATGL3 as a String.
  */
  public String getE01RATGL3()
  {
    return fieldE01RATGL3.getString();
  }

  /**
  * Set numeric field E01RATGL3 using a BigDecimal value.
  */
  public void setE01RATGL3(BigDecimal newvalue)
  {
    fieldE01RATGL3.setBigDecimal(newvalue);
  }

  /**
  * Return value of numeric field E01RATGL3 as a BigDecimal.
  */
  public BigDecimal getBigDecimalE01RATGL3()
  {
    return fieldE01RATGL3.getBigDecimal();
  }

  /**
  * Set field E01GLDSC3 using a String value.
  */
  public void setE01GLDSC3(String newvalue)
  {
    fieldE01GLDSC3.setString(newvalue);
  }

  /**
  * Get value of field E01GLDSC3 as a String.
  */
  public String getE01GLDSC3()
  {
    return fieldE01GLDSC3.getString();
  }

  /**
  * Set field E01RATBK4 using a String value.
  */
  public void setE01RATBK4(String newvalue)
  {
    fieldE01RATBK4.setString(newvalue);
  }

  /**
  * Get value of field E01RATBK4 as a String.
  */
  public String getE01RATBK4()
  {
    return fieldE01RATBK4.getString();
  }

  /**
  * Set field E01RATCY4 using a String value.
  */
  public void setE01RATCY4(String newvalue)
  {
    fieldE01RATCY4.setString(newvalue);
  }

  /**
  * Get value of field E01RATCY4 as a String.
  */
  public String getE01RATCY4()
  {
    return fieldE01RATCY4.getString();
  }

  /**
  * Set field E01RATGL4 using a String value.
  */
  public void setE01RATGL4(String newvalue)
  {
    fieldE01RATGL4.setString(newvalue);
  }

  /**
  * Get value of field E01RATGL4 as a String.
  */
  public String getE01RATGL4()
  {
    return fieldE01RATGL4.getString();
  }

  /**
  * Set numeric field E01RATGL4 using a BigDecimal value.
  */
  public void setE01RATGL4(BigDecimal newvalue)
  {
    fieldE01RATGL4.setBigDecimal(newvalue);
  }

  /**
  * Return value of numeric field E01RATGL4 as a BigDecimal.
  */
  public BigDecimal getBigDecimalE01RATGL4()
  {
    return fieldE01RATGL4.getBigDecimal();
  }

  /**
  * Set field E01GLDSC4 using a String value.
  */
  public void setE01GLDSC4(String newvalue)
  {
    fieldE01GLDSC4.setString(newvalue);
  }

  /**
  * Get value of field E01GLDSC4 as a String.
  */
  public String getE01GLDSC4()
  {
    return fieldE01GLDSC4.getString();
  }

  /**
  * Set field E01OPECDE using a String value.
  */
  public void setE01OPECDE(String newvalue)
  {
    fieldE01OPECDE.setString(newvalue);
  }

  /**
  * Get value of field E01OPECDE as a String.
  */
  public String getE01OPECDE()
  {
    return fieldE01OPECDE.getString();
  }

}