package datapro.eibs.beans;

import datapro.eibs.sockets.*;
import java.io.*;
import java.math.*;

import java.util.*;

/**
* Class generated by AS/400 CRTCLASS command from EGC050091 physical file definition.
* 
* File level identifier is 1030121191928.
* Record format level identifier is 43328A8B63C9D.
*/

public class EGC050091Message extends MessageRecord
{
  final static String fldnames[] = {
                                     "H91USERID",
                                     "H91PROGRM",
                                     "H91TIMSYS",
                                     "H91SCRCOD",
                                     "H91OPECOD",
                                     "H91FLGMAS",
                                     "H91FLGWK1",
                                     "H91FLGWK2",
                                     "H91FLGWK3",
                                     "H91CODACC",
                                     "E90GCRUT",
                                     "E91GCNMBC",
                                     "E91GCGTYA",
                                     "E91GCNGTY",
                                     "E91GCMONG",
                                     "E91GCTAPY",
                                     "E91GCTAPM",
                                     "E91GCTAPD",
                                     "E91GCVENY",
                                     "E91GCVENM",
                                     "E91GCVEND",
                                     "E91INDOPE"
                                   };
  final static String tnames[] = {
                                   "H91USERID",
                                   "H91PROGRM",
                                   "H91TIMSYS",
                                   "H91SCRCOD",
                                   "H91OPECOD",
                                   "H91FLGMAS",
                                   "H91FLGWK1",
                                   "H91FLGWK2",
                                   "H91FLGWK3",
                                   "H91CODACC",
                                   "E90GCRUT",
                                   "E91GCNMBC",
                                   "E91GCGTYA",
                                   "E91GCNGTY",
                                   "E91GCMONG",
                                   "E91GCTAPY",
                                   "E91GCTAPM",
                                   "E91GCTAPD",
                                   "E91GCVENY",
                                   "E91GCVENM",
                                   "E91GCVEND",
                                   "E91INDOPE"
                                 };
  final static String fid = "1030121191928";
  final static String rid = "43328A8B63C9D";
  final static String fmtname = "EGC050091";
  final int FIELDCOUNT = 22;
  private static Hashtable tlookup = new Hashtable();
  private CharacterField fieldH91USERID = null;
  private CharacterField fieldH91PROGRM = null;
  private CharacterField fieldH91TIMSYS = null;
  private CharacterField fieldH91SCRCOD = null;
  private CharacterField fieldH91OPECOD = null;
  private CharacterField fieldH91FLGMAS = null;
  private CharacterField fieldH91FLGWK1 = null;
  private CharacterField fieldH91FLGWK2 = null;
  private CharacterField fieldH91FLGWK3 = null;
  private CharacterField fieldH91CODACC = null;
  private CharacterField fieldE90GCRUT = null;
  private CharacterField fieldE91GCNMBC = null;
  private CharacterField fieldE91GCGTYA = null;
  private DecimalField fieldE91GCNGTY = null;
  private DecimalField fieldE91GCMONG = null;
  private DecimalField fieldE91GCTAPY = null;
  private DecimalField fieldE91GCTAPM = null;
  private DecimalField fieldE91GCTAPD = null;
  private DecimalField fieldE91GCVENY = null;
  private DecimalField fieldE91GCVENM = null;
  private DecimalField fieldE91GCVEND = null;
  private CharacterField fieldE91INDOPE = null;

  /**
  * Constructor for EGC050091Message.
  */
  public EGC050091Message()
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
    recordsize = 149;
    fileid = fid;
    recordid = rid;
    message = new byte[getByteLength()];
    formatname = fmtname;
    fieldnames = fldnames;
    tagnames = tnames;
    fields = new MessageField[FIELDCOUNT];

    fields[0] = fieldH91USERID =
    new CharacterField(message, HEADERSIZE + 0, 10, "H91USERID");
    fields[1] = fieldH91PROGRM =
    new CharacterField(message, HEADERSIZE + 10, 10, "H91PROGRM");
    fields[2] = fieldH91TIMSYS =
    new CharacterField(message, HEADERSIZE + 20, 12, "H91TIMSYS");
    fields[3] = fieldH91SCRCOD =
    new CharacterField(message, HEADERSIZE + 32, 2, "H91SCRCOD");
    fields[4] = fieldH91OPECOD =
    new CharacterField(message, HEADERSIZE + 34, 4, "H91OPECOD");
    fields[5] = fieldH91FLGMAS =
    new CharacterField(message, HEADERSIZE + 38, 1, "H91FLGMAS");
    fields[6] = fieldH91FLGWK1 =
    new CharacterField(message, HEADERSIZE + 39, 1, "H91FLGWK1");
    fields[7] = fieldH91FLGWK2 =
    new CharacterField(message, HEADERSIZE + 40, 1, "H91FLGWK2");
    fields[8] = fieldH91FLGWK3 =
    new CharacterField(message, HEADERSIZE + 41, 1, "H91FLGWK3");
    fields[9] = fieldH91CODACC =
    new CharacterField(message, HEADERSIZE + 42, 4, "H91CODACC");
    fields[10] = fieldE90GCRUT =
    new CharacterField(message, HEADERSIZE + 46, 15, "E90GCRUT");
    fields[11] = fieldE91GCNMBC =
    new CharacterField(message, HEADERSIZE + 61, 45, "E91GCNMBC");
    fields[12] = fieldE91GCGTYA =
    new CharacterField(message, HEADERSIZE + 106, 1, "E91GCGTYA");
    fields[13] = fieldE91GCNGTY =
    new DecimalField(message, HEADERSIZE + 107, 6, 0, "E91GCNGTY");
    fields[14] = fieldE91GCMONG =
    new DecimalField(message, HEADERSIZE + 113, 17, 2, "E91GCMONG");
    fields[15] = fieldE91GCTAPY =
    new DecimalField(message, HEADERSIZE + 130, 3, 0, "E91GCTAPY");
    fields[16] = fieldE91GCTAPM =
    new DecimalField(message, HEADERSIZE + 133, 3, 0, "E91GCTAPM");
    fields[17] = fieldE91GCTAPD =
    new DecimalField(message, HEADERSIZE + 136, 3, 0, "E91GCTAPD");
    fields[18] = fieldE91GCVENY =
    new DecimalField(message, HEADERSIZE + 139, 3, 0, "E91GCVENY");
    fields[19] = fieldE91GCVENM =
    new DecimalField(message, HEADERSIZE + 142, 3, 0, "E91GCVENM");
    fields[20] = fieldE91GCVEND =
    new DecimalField(message, HEADERSIZE + 145, 3, 0, "E91GCVEND");
    fields[21] = fieldE91INDOPE =
    new CharacterField(message, HEADERSIZE + 148, 1, "E91INDOPE");

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
  * Set field H91USERID using a String value.
  */
  public void setH91USERID(String newvalue)
  {
    fieldH91USERID.setString(newvalue);
  }

  /**
  * Get value of field H91USERID as a String.
  */
  public String getH91USERID()
  {
    return fieldH91USERID.getString();
  }

  /**
  * Set field H91PROGRM using a String value.
  */
  public void setH91PROGRM(String newvalue)
  {
    fieldH91PROGRM.setString(newvalue);
  }

  /**
  * Get value of field H91PROGRM as a String.
  */
  public String getH91PROGRM()
  {
    return fieldH91PROGRM.getString();
  }

  /**
  * Set field H91TIMSYS using a String value.
  */
  public void setH91TIMSYS(String newvalue)
  {
    fieldH91TIMSYS.setString(newvalue);
  }

  /**
  * Get value of field H91TIMSYS as a String.
  */
  public String getH91TIMSYS()
  {
    return fieldH91TIMSYS.getString();
  }

  /**
  * Set field H91SCRCOD using a String value.
  */
  public void setH91SCRCOD(String newvalue)
  {
    fieldH91SCRCOD.setString(newvalue);
  }

  /**
  * Get value of field H91SCRCOD as a String.
  */
  public String getH91SCRCOD()
  {
    return fieldH91SCRCOD.getString();
  }

  /**
  * Set field H91OPECOD using a String value.
  */
  public void setH91OPECOD(String newvalue)
  {
    fieldH91OPECOD.setString(newvalue);
  }

  /**
  * Get value of field H91OPECOD as a String.
  */
  public String getH91OPECOD()
  {
    return fieldH91OPECOD.getString();
  }

  /**
  * Set field H91FLGMAS using a String value.
  */
  public void setH91FLGMAS(String newvalue)
  {
    fieldH91FLGMAS.setString(newvalue);
  }

  /**
  * Get value of field H91FLGMAS as a String.
  */
  public String getH91FLGMAS()
  {
    return fieldH91FLGMAS.getString();
  }

  /**
  * Set field H91FLGWK1 using a String value.
  */
  public void setH91FLGWK1(String newvalue)
  {
    fieldH91FLGWK1.setString(newvalue);
  }

  /**
  * Get value of field H91FLGWK1 as a String.
  */
  public String getH91FLGWK1()
  {
    return fieldH91FLGWK1.getString();
  }

  /**
  * Set field H91FLGWK2 using a String value.
  */
  public void setH91FLGWK2(String newvalue)
  {
    fieldH91FLGWK2.setString(newvalue);
  }

  /**
  * Get value of field H91FLGWK2 as a String.
  */
  public String getH91FLGWK2()
  {
    return fieldH91FLGWK2.getString();
  }

  /**
  * Set field H91FLGWK3 using a String value.
  */
  public void setH91FLGWK3(String newvalue)
  {
    fieldH91FLGWK3.setString(newvalue);
  }

  /**
  * Get value of field H91FLGWK3 as a String.
  */
  public String getH91FLGWK3()
  {
    return fieldH91FLGWK3.getString();
  }

  /**
  * Set field H91CODACC using a String value.
  */
  public void setH91CODACC(String newvalue)
  {
    fieldH91CODACC.setString(newvalue);
  }

  /**
  * Get value of field H91CODACC as a String.
  */
  public String getH91CODACC()
  {
    return fieldH91CODACC.getString();
  }

  /**
  * Set field E90GCRUT using a String value.
  */
  public void setE90GCRUT(String newvalue)
  {
    fieldE90GCRUT.setString(newvalue);
  }

  /**
  * Get value of field E90GCRUT as a String.
  */
  public String getE90GCRUT()
  {
    return fieldE90GCRUT.getString();
  }

  /**
  * Set field E91GCNMBC using a String value.
  */
  public void setE91GCNMBC(String newvalue)
  {
    fieldE91GCNMBC.setString(newvalue);
  }

  /**
  * Get value of field E91GCNMBC as a String.
  */
  public String getE91GCNMBC()
  {
    return fieldE91GCNMBC.getString();
  }

  /**
  * Set field E91GCGTYA using a String value.
  */
  public void setE91GCGTYA(String newvalue)
  {
    fieldE91GCGTYA.setString(newvalue);
  }

  /**
  * Get value of field E91GCGTYA as a String.
  */
  public String getE91GCGTYA()
  {
    return fieldE91GCGTYA.getString();
  }

  /**
  * Set field E91GCNGTY using a String value.
  */
  public void setE91GCNGTY(String newvalue)
  {
    fieldE91GCNGTY.setString(newvalue);
  }

  /**
  * Get value of field E91GCNGTY as a String.
  */
  public String getE91GCNGTY()
  {
    return fieldE91GCNGTY.getString();
  }

  /**
  * Set numeric field E91GCNGTY using a BigDecimal value.
  */
  public void setE91GCNGTY(BigDecimal newvalue)
  {
    fieldE91GCNGTY.setBigDecimal(newvalue);
  }

  /**
  * Return value of numeric field E91GCNGTY as a BigDecimal.
  */
  public BigDecimal getBigDecimalE91GCNGTY()
  {
    return fieldE91GCNGTY.getBigDecimal();
  }

  /**
  * Set field E91GCMONG using a String value.
  */
  public void setE91GCMONG(String newvalue)
  {
    fieldE91GCMONG.setString(newvalue);
  }

  /**
  * Get value of field E91GCMONG as a String.
  */
  public String getE91GCMONG()
  {
    return fieldE91GCMONG.getString();
  }

  /**
  * Set numeric field E91GCMONG using a BigDecimal value.
  */
  public void setE91GCMONG(BigDecimal newvalue)
  {
    fieldE91GCMONG.setBigDecimal(newvalue);
  }

  /**
  * Return value of numeric field E91GCMONG as a BigDecimal.
  */
  public BigDecimal getBigDecimalE91GCMONG()
  {
    return fieldE91GCMONG.getBigDecimal();
  }

  /**
  * Set field E91GCTAPY using a String value.
  */
  public void setE91GCTAPY(String newvalue)
  {
    fieldE91GCTAPY.setString(newvalue);
  }

  /**
  * Get value of field E91GCTAPY as a String.
  */
  public String getE91GCTAPY()
  {
    return fieldE91GCTAPY.getString();
  }

  /**
  * Set numeric field E91GCTAPY using a BigDecimal value.
  */
  public void setE91GCTAPY(BigDecimal newvalue)
  {
    fieldE91GCTAPY.setBigDecimal(newvalue);
  }

  /**
  * Return value of numeric field E91GCTAPY as a BigDecimal.
  */
  public BigDecimal getBigDecimalE91GCTAPY()
  {
    return fieldE91GCTAPY.getBigDecimal();
  }

  /**
  * Set field E91GCTAPM using a String value.
  */
  public void setE91GCTAPM(String newvalue)
  {
    fieldE91GCTAPM.setString(newvalue);
  }

  /**
  * Get value of field E91GCTAPM as a String.
  */
  public String getE91GCTAPM()
  {
    return fieldE91GCTAPM.getString();
  }

  /**
  * Set numeric field E91GCTAPM using a BigDecimal value.
  */
  public void setE91GCTAPM(BigDecimal newvalue)
  {
    fieldE91GCTAPM.setBigDecimal(newvalue);
  }

  /**
  * Return value of numeric field E91GCTAPM as a BigDecimal.
  */
  public BigDecimal getBigDecimalE91GCTAPM()
  {
    return fieldE91GCTAPM.getBigDecimal();
  }

  /**
  * Set field E91GCTAPD using a String value.
  */
  public void setE91GCTAPD(String newvalue)
  {
    fieldE91GCTAPD.setString(newvalue);
  }

  /**
  * Get value of field E91GCTAPD as a String.
  */
  public String getE91GCTAPD()
  {
    return fieldE91GCTAPD.getString();
  }

  /**
  * Set numeric field E91GCTAPD using a BigDecimal value.
  */
  public void setE91GCTAPD(BigDecimal newvalue)
  {
    fieldE91GCTAPD.setBigDecimal(newvalue);
  }

  /**
  * Return value of numeric field E91GCTAPD as a BigDecimal.
  */
  public BigDecimal getBigDecimalE91GCTAPD()
  {
    return fieldE91GCTAPD.getBigDecimal();
  }

  /**
  * Set field E91GCVENY using a String value.
  */
  public void setE91GCVENY(String newvalue)
  {
    fieldE91GCVENY.setString(newvalue);
  }

  /**
  * Get value of field E91GCVENY as a String.
  */
  public String getE91GCVENY()
  {
    return fieldE91GCVENY.getString();
  }

  /**
  * Set numeric field E91GCVENY using a BigDecimal value.
  */
  public void setE91GCVENY(BigDecimal newvalue)
  {
    fieldE91GCVENY.setBigDecimal(newvalue);
  }

  /**
  * Return value of numeric field E91GCVENY as a BigDecimal.
  */
  public BigDecimal getBigDecimalE91GCVENY()
  {
    return fieldE91GCVENY.getBigDecimal();
  }

  /**
  * Set field E91GCVENM using a String value.
  */
  public void setE91GCVENM(String newvalue)
  {
    fieldE91GCVENM.setString(newvalue);
  }

  /**
  * Get value of field E91GCVENM as a String.
  */
  public String getE91GCVENM()
  {
    return fieldE91GCVENM.getString();
  }

  /**
  * Set numeric field E91GCVENM using a BigDecimal value.
  */
  public void setE91GCVENM(BigDecimal newvalue)
  {
    fieldE91GCVENM.setBigDecimal(newvalue);
  }

  /**
  * Return value of numeric field E91GCVENM as a BigDecimal.
  */
  public BigDecimal getBigDecimalE91GCVENM()
  {
    return fieldE91GCVENM.getBigDecimal();
  }

  /**
  * Set field E91GCVEND using a String value.
  */
  public void setE91GCVEND(String newvalue)
  {
    fieldE91GCVEND.setString(newvalue);
  }

  /**
  * Get value of field E91GCVEND as a String.
  */
  public String getE91GCVEND()
  {
    return fieldE91GCVEND.getString();
  }

  /**
  * Set numeric field E91GCVEND using a BigDecimal value.
  */
  public void setE91GCVEND(BigDecimal newvalue)
  {
    fieldE91GCVEND.setBigDecimal(newvalue);
  }

  /**
  * Return value of numeric field E91GCVEND as a BigDecimal.
  */
  public BigDecimal getBigDecimalE91GCVEND()
  {
    return fieldE91GCVEND.getBigDecimal();
  }

  /**
  * Set field E91INDOPE using a String value.
  */
  public void setE91INDOPE(String newvalue)
  {
    fieldE91INDOPE.setString(newvalue);
  }

  /**
  * Get value of field E91INDOPE as a String.
  */
  public String getE91INDOPE()
  {
    return fieldE91INDOPE.getString();
  }

}
