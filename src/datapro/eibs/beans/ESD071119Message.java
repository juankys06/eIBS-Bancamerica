package datapro.eibs.beans;

import datapro.eibs.sockets.*;
import java.io.*;
import java.math.*;

import java.util.*;

/**
* Class generated by AS/400 CRTCLASS command from ESD071119 physical file definition.
* 
* File level identifier is 1030121192007.
* Record format level identifier is 4DE0827A7083A.
*/

public class ESD071119Message extends MessageRecord
{
  final static String fldnames[] = {
                                     "H19USERID",
                                     "H19PROGRM",
                                     "H19TIMSYS",
                                     "H19SCRCOD",
                                     "H19OPECOD",
                                     "H19FLGMAS",
                                     "H19FLGWK1",
                                     "H19FLGWK2",
                                     "H19FLGWK3",
                                     "E19APCTYP",
                                     "E19TYPDES",
                                     "E19APCCDE",
                                     "E19DESCRI",
                                     "E19MERCAD",
                                     "E19APCBNK",
                                     "E19APCCCY",
                                     "E19CCYDSC",
                                     "E19APCGLN",
                                     "E19GLMDSC",
                                     "E19APCICT",
                                     "E19APCINV",
                                     "E19APCROY",
                                     "E19APCAMO",
                                     "E19APCITP",
                                     "E19APCFRN",
                                     "E19APCIPD",
                                     "E19APCFTF",
                                     "E19APEAUD",
                                     "E19APEVID",
                                     "E19APEHTM"
                                   };
  final static String tnames[] = {
                                   "H19USERID",
                                   "H19PROGRM",
                                   "H19TIMSYS",
                                   "H19SCRCOD",
                                   "H19OPECOD",
                                   "H19FLGMAS",
                                   "H19FLGWK1",
                                   "H19FLGWK2",
                                   "H19FLGWK3",
                                   "E19APCTYP",
                                   "E19TYPDES",
                                   "E19APCCDE",
                                   "E19DESCRI",
                                   "E19MERCAD",
                                   "E19APCBNK",
                                   "E19APCCCY",
                                   "E19CCYDSC",
                                   "E19APCGLN",
                                   "E19GLMDSC",
                                   "E19APCICT",
                                   "E19APCINV",
                                   "E19APCROY",
                                   "E19APCAMO",
                                   "E19APCITP",
                                   "E19APCFRN",
                                   "E19APCIPD",
                                   "E19APCFTF",
                                   "E19APEAUD",
                                   "E19APEVID",
                                   "E19APEHTM"
                                 };
  final static String fid = "1030121192007";
  final static String rid = "4DE0827A7083A";
  final static String fmtname = "ESD071119";
  final int FIELDCOUNT = 30;
  private static Hashtable tlookup = new Hashtable();
  private CharacterField fieldH19USERID = null;
  private CharacterField fieldH19PROGRM = null;
  private CharacterField fieldH19TIMSYS = null;
  private CharacterField fieldH19SCRCOD = null;
  private CharacterField fieldH19OPECOD = null;
  private CharacterField fieldH19FLGMAS = null;
  private CharacterField fieldH19FLGWK1 = null;
  private CharacterField fieldH19FLGWK2 = null;
  private CharacterField fieldH19FLGWK3 = null;
  private CharacterField fieldE19APCTYP = null;
  private CharacterField fieldE19TYPDES = null;
  private CharacterField fieldE19APCCDE = null;
  private CharacterField fieldE19DESCRI = null;
  private CharacterField fieldE19MERCAD = null;
  private CharacterField fieldE19APCBNK = null;
  private CharacterField fieldE19APCCCY = null;
  private CharacterField fieldE19CCYDSC = null;
  private DecimalField fieldE19APCGLN = null;
  private CharacterField fieldE19GLMDSC = null;
  private CharacterField fieldE19APCICT = null;
  private CharacterField fieldE19APCINV = null;
  private CharacterField fieldE19APCROY = null;
  private CharacterField fieldE19APCAMO = null;
  private CharacterField fieldE19APCITP = null;
  private CharacterField fieldE19APCFRN = null;
  private CharacterField fieldE19APCIPD = null;
  private CharacterField fieldE19APCFTF = null;
  private CharacterField fieldE19APEAUD = null;
  private CharacterField fieldE19APEVID = null;
  private CharacterField fieldE19APEHTM = null;

  /**
  * Constructor for ESD071119Message.
  */
  public ESD071119Message()
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
    recordsize = 511;
    fileid = fid;
    recordid = rid;
    message = new byte[getByteLength()];
    formatname = fmtname;
    fieldnames = fldnames;
    tagnames = tnames;
    fields = new MessageField[FIELDCOUNT];

    fields[0] = fieldH19USERID =
    new CharacterField(message, HEADERSIZE + 0, 10, "H19USERID");
    fields[1] = fieldH19PROGRM =
    new CharacterField(message, HEADERSIZE + 10, 10, "H19PROGRM");
    fields[2] = fieldH19TIMSYS =
    new CharacterField(message, HEADERSIZE + 20, 12, "H19TIMSYS");
    fields[3] = fieldH19SCRCOD =
    new CharacterField(message, HEADERSIZE + 32, 2, "H19SCRCOD");
    fields[4] = fieldH19OPECOD =
    new CharacterField(message, HEADERSIZE + 34, 4, "H19OPECOD");
    fields[5] = fieldH19FLGMAS =
    new CharacterField(message, HEADERSIZE + 38, 1, "H19FLGMAS");
    fields[6] = fieldH19FLGWK1 =
    new CharacterField(message, HEADERSIZE + 39, 1, "H19FLGWK1");
    fields[7] = fieldH19FLGWK2 =
    new CharacterField(message, HEADERSIZE + 40, 1, "H19FLGWK2");
    fields[8] = fieldH19FLGWK3 =
    new CharacterField(message, HEADERSIZE + 41, 1, "H19FLGWK3");
    fields[9] = fieldE19APCTYP =
    new CharacterField(message, HEADERSIZE + 42, 4, "E19APCTYP");
    fields[10] = fieldE19TYPDES =
    new CharacterField(message, HEADERSIZE + 46, 35, "E19TYPDES");
    fields[11] = fieldE19APCCDE =
    new CharacterField(message, HEADERSIZE + 81, 4, "E19APCCDE");
    fields[12] = fieldE19DESCRI =
    new CharacterField(message, HEADERSIZE + 85, 63, "E19DESCRI");
    fields[13] = fieldE19MERCAD =
    new CharacterField(message, HEADERSIZE + 148, 15, "E19MERCAD");
    fields[14] = fieldE19APCBNK =
    new CharacterField(message, HEADERSIZE + 163, 2, "E19APCBNK");
    fields[15] = fieldE19APCCCY =
    new CharacterField(message, HEADERSIZE + 165, 3, "E19APCCCY");
    fields[16] = fieldE19CCYDSC =
    new CharacterField(message, HEADERSIZE + 168, 35, "E19CCYDSC");
    fields[17] = fieldE19APCGLN =
    new DecimalField(message, HEADERSIZE + 203, 17, 0, "E19APCGLN");
    fields[18] = fieldE19GLMDSC =
    new CharacterField(message, HEADERSIZE + 220, 35, "E19GLMDSC");
    fields[19] = fieldE19APCICT =
    new CharacterField(message, HEADERSIZE + 255, 1, "E19APCICT");
    fields[20] = fieldE19APCINV =
    new CharacterField(message, HEADERSIZE + 256, 4, "E19APCINV");
    fields[21] = fieldE19APCROY =
    new CharacterField(message, HEADERSIZE + 260, 3, "E19APCROY");
    fields[22] = fieldE19APCAMO =
    new CharacterField(message, HEADERSIZE + 263, 1, "E19APCAMO");
    fields[23] = fieldE19APCITP =
    new CharacterField(message, HEADERSIZE + 264, 1, "E19APCITP");
    fields[24] = fieldE19APCFRN =
    new CharacterField(message, HEADERSIZE + 265, 1, "E19APCFRN");
    fields[25] = fieldE19APCIPD =
    new CharacterField(message, HEADERSIZE + 266, 3, "E19APCIPD");
    fields[26] = fieldE19APCFTF =
    new CharacterField(message, HEADERSIZE + 269, 2, "E19APCFTF");
    fields[27] = fieldE19APEAUD =
    new CharacterField(message, HEADERSIZE + 271, 80, "E19APEAUD");
    fields[28] = fieldE19APEVID =
    new CharacterField(message, HEADERSIZE + 351, 80, "E19APEVID");
    fields[29] = fieldE19APEHTM =
    new CharacterField(message, HEADERSIZE + 431, 80, "E19APEHTM");

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
  * Set field H19USERID using a String value.
  */
  public void setH19USERID(String newvalue)
  {
    fieldH19USERID.setString(newvalue);
  }

  /**
  * Get value of field H19USERID as a String.
  */
  public String getH19USERID()
  {
    return fieldH19USERID.getString();
  }

  /**
  * Set field H19PROGRM using a String value.
  */
  public void setH19PROGRM(String newvalue)
  {
    fieldH19PROGRM.setString(newvalue);
  }

  /**
  * Get value of field H19PROGRM as a String.
  */
  public String getH19PROGRM()
  {
    return fieldH19PROGRM.getString();
  }

  /**
  * Set field H19TIMSYS using a String value.
  */
  public void setH19TIMSYS(String newvalue)
  {
    fieldH19TIMSYS.setString(newvalue);
  }

  /**
  * Get value of field H19TIMSYS as a String.
  */
  public String getH19TIMSYS()
  {
    return fieldH19TIMSYS.getString();
  }

  /**
  * Set field H19SCRCOD using a String value.
  */
  public void setH19SCRCOD(String newvalue)
  {
    fieldH19SCRCOD.setString(newvalue);
  }

  /**
  * Get value of field H19SCRCOD as a String.
  */
  public String getH19SCRCOD()
  {
    return fieldH19SCRCOD.getString();
  }

  /**
  * Set field H19OPECOD using a String value.
  */
  public void setH19OPECOD(String newvalue)
  {
    fieldH19OPECOD.setString(newvalue);
  }

  /**
  * Get value of field H19OPECOD as a String.
  */
  public String getH19OPECOD()
  {
    return fieldH19OPECOD.getString();
  }

  /**
  * Set field H19FLGMAS using a String value.
  */
  public void setH19FLGMAS(String newvalue)
  {
    fieldH19FLGMAS.setString(newvalue);
  }

  /**
  * Get value of field H19FLGMAS as a String.
  */
  public String getH19FLGMAS()
  {
    return fieldH19FLGMAS.getString();
  }

  /**
  * Set field H19FLGWK1 using a String value.
  */
  public void setH19FLGWK1(String newvalue)
  {
    fieldH19FLGWK1.setString(newvalue);
  }

  /**
  * Get value of field H19FLGWK1 as a String.
  */
  public String getH19FLGWK1()
  {
    return fieldH19FLGWK1.getString();
  }

  /**
  * Set field H19FLGWK2 using a String value.
  */
  public void setH19FLGWK2(String newvalue)
  {
    fieldH19FLGWK2.setString(newvalue);
  }

  /**
  * Get value of field H19FLGWK2 as a String.
  */
  public String getH19FLGWK2()
  {
    return fieldH19FLGWK2.getString();
  }

  /**
  * Set field H19FLGWK3 using a String value.
  */
  public void setH19FLGWK3(String newvalue)
  {
    fieldH19FLGWK3.setString(newvalue);
  }

  /**
  * Get value of field H19FLGWK3 as a String.
  */
  public String getH19FLGWK3()
  {
    return fieldH19FLGWK3.getString();
  }

  /**
  * Set field E19APCTYP using a String value.
  */
  public void setE19APCTYP(String newvalue)
  {
    fieldE19APCTYP.setString(newvalue);
  }

  /**
  * Get value of field E19APCTYP as a String.
  */
  public String getE19APCTYP()
  {
    return fieldE19APCTYP.getString();
  }

  /**
  * Set field E19TYPDES using a String value.
  */
  public void setE19TYPDES(String newvalue)
  {
    fieldE19TYPDES.setString(newvalue);
  }

  /**
  * Get value of field E19TYPDES as a String.
  */
  public String getE19TYPDES()
  {
    return fieldE19TYPDES.getString();
  }

  /**
  * Set field E19APCCDE using a String value.
  */
  public void setE19APCCDE(String newvalue)
  {
    fieldE19APCCDE.setString(newvalue);
  }

  /**
  * Get value of field E19APCCDE as a String.
  */
  public String getE19APCCDE()
  {
    return fieldE19APCCDE.getString();
  }

  /**
  * Set field E19DESCRI using a String value.
  */
  public void setE19DESCRI(String newvalue)
  {
    fieldE19DESCRI.setString(newvalue);
  }

  /**
  * Get value of field E19DESCRI as a String.
  */
  public String getE19DESCRI()
  {
    return fieldE19DESCRI.getString();
  }

  /**
  * Set field E19MERCAD using a String value.
  */
  public void setE19MERCAD(String newvalue)
  {
    fieldE19MERCAD.setString(newvalue);
  }

  /**
  * Get value of field E19MERCAD as a String.
  */
  public String getE19MERCAD()
  {
    return fieldE19MERCAD.getString();
  }

  /**
  * Set field E19APCBNK using a String value.
  */
  public void setE19APCBNK(String newvalue)
  {
    fieldE19APCBNK.setString(newvalue);
  }

  /**
  * Get value of field E19APCBNK as a String.
  */
  public String getE19APCBNK()
  {
    return fieldE19APCBNK.getString();
  }

  /**
  * Set field E19APCCCY using a String value.
  */
  public void setE19APCCCY(String newvalue)
  {
    fieldE19APCCCY.setString(newvalue);
  }

  /**
  * Get value of field E19APCCCY as a String.
  */
  public String getE19APCCCY()
  {
    return fieldE19APCCCY.getString();
  }

  /**
  * Set field E19CCYDSC using a String value.
  */
  public void setE19CCYDSC(String newvalue)
  {
    fieldE19CCYDSC.setString(newvalue);
  }

  /**
  * Get value of field E19CCYDSC as a String.
  */
  public String getE19CCYDSC()
  {
    return fieldE19CCYDSC.getString();
  }

  /**
  * Set field E19APCGLN using a String value.
  */
  public void setE19APCGLN(String newvalue)
  {
    fieldE19APCGLN.setString(newvalue);
  }

  /**
  * Get value of field E19APCGLN as a String.
  */
  public String getE19APCGLN()
  {
    return fieldE19APCGLN.getString();
  }

  /**
  * Set numeric field E19APCGLN using a BigDecimal value.
  */
  public void setE19APCGLN(BigDecimal newvalue)
  {
    fieldE19APCGLN.setBigDecimal(newvalue);
  }

  /**
  * Return value of numeric field E19APCGLN as a BigDecimal.
  */
  public BigDecimal getBigDecimalE19APCGLN()
  {
    return fieldE19APCGLN.getBigDecimal();
  }

  /**
  * Set field E19GLMDSC using a String value.
  */
  public void setE19GLMDSC(String newvalue)
  {
    fieldE19GLMDSC.setString(newvalue);
  }

  /**
  * Get value of field E19GLMDSC as a String.
  */
  public String getE19GLMDSC()
  {
    return fieldE19GLMDSC.getString();
  }

  /**
  * Set field E19APCICT using a String value.
  */
  public void setE19APCICT(String newvalue)
  {
    fieldE19APCICT.setString(newvalue);
  }

  /**
  * Get value of field E19APCICT as a String.
  */
  public String getE19APCICT()
  {
    return fieldE19APCICT.getString();
  }

  /**
  * Set field E19APCINV using a String value.
  */
  public void setE19APCINV(String newvalue)
  {
    fieldE19APCINV.setString(newvalue);
  }

  /**
  * Get value of field E19APCINV as a String.
  */
  public String getE19APCINV()
  {
    return fieldE19APCINV.getString();
  }

  /**
  * Set field E19APCROY using a String value.
  */
  public void setE19APCROY(String newvalue)
  {
    fieldE19APCROY.setString(newvalue);
  }

  /**
  * Get value of field E19APCROY as a String.
  */
  public String getE19APCROY()
  {
    return fieldE19APCROY.getString();
  }

  /**
  * Set field E19APCAMO using a String value.
  */
  public void setE19APCAMO(String newvalue)
  {
    fieldE19APCAMO.setString(newvalue);
  }

  /**
  * Get value of field E19APCAMO as a String.
  */
  public String getE19APCAMO()
  {
    return fieldE19APCAMO.getString();
  }

  /**
  * Set field E19APCITP using a String value.
  */
  public void setE19APCITP(String newvalue)
  {
    fieldE19APCITP.setString(newvalue);
  }

  /**
  * Get value of field E19APCITP as a String.
  */
  public String getE19APCITP()
  {
    return fieldE19APCITP.getString();
  }

  /**
  * Set field E19APCFRN using a String value.
  */
  public void setE19APCFRN(String newvalue)
  {
    fieldE19APCFRN.setString(newvalue);
  }

  /**
  * Get value of field E19APCFRN as a String.
  */
  public String getE19APCFRN()
  {
    return fieldE19APCFRN.getString();
  }

  /**
  * Set field E19APCIPD using a String value.
  */
  public void setE19APCIPD(String newvalue)
  {
    fieldE19APCIPD.setString(newvalue);
  }

  /**
  * Get value of field E19APCIPD as a String.
  */
  public String getE19APCIPD()
  {
    return fieldE19APCIPD.getString();
  }

  /**
  * Set field E19APCFTF using a String value.
  */
  public void setE19APCFTF(String newvalue)
  {
    fieldE19APCFTF.setString(newvalue);
  }

  /**
  * Get value of field E19APCFTF as a String.
  */
  public String getE19APCFTF()
  {
    return fieldE19APCFTF.getString();
  }

  /**
  * Set field E19APEAUD using a String value.
  */
  public void setE19APEAUD(String newvalue)
  {
    fieldE19APEAUD.setString(newvalue);
  }

  /**
  * Get value of field E19APEAUD as a String.
  */
  public String getE19APEAUD()
  {
    return fieldE19APEAUD.getString();
  }

  /**
  * Set field E19APEVID using a String value.
  */
  public void setE19APEVID(String newvalue)
  {
    fieldE19APEVID.setString(newvalue);
  }

  /**
  * Get value of field E19APEVID as a String.
  */
  public String getE19APEVID()
  {
    return fieldE19APEVID.getString();
  }

  /**
  * Set field E19APEHTM using a String value.
  */
  public void setE19APEHTM(String newvalue)
  {
    fieldE19APEHTM.setString(newvalue);
  }

  /**
  * Get value of field E19APEHTM as a String.
  */
  public String getE19APEHTM()
  {
    return fieldE19APEHTM.getString();
  }

}
