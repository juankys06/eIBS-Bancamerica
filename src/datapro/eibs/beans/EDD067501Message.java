package datapro.eibs.beans;

import datapro.eibs.sockets.*;
import java.io.*;
import java.math.*;

import java.util.*;

/**
* Class generated by AS/400 CRTCLASS command from EDD067501 physical file definition.
* 
* File level identifier is 1030122105100.
* Record format level identifier is 3F0EA2F024B1A.
*/

public class EDD067501Message extends MessageRecord
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
                                     "E01CNTNME",
                                     "E01PODLDT",
                                     "E01POILPN",
                                     "E01POILPA",
                                     "E01LSTREF",
                                     "E01POIAM1",
                                     "E01POIAM2",
                                     "E01POIDCY",
                                     "E01POIDGL",
                                     "E01POIDAC",
                                     "E01POIDSB",
                                     "E01POIDCS",
                                     "E01DEBDSC",
                                     "E01POICCY",
                                     "E01POICGL",
                                     "E01POICAC",
                                     "E01POICSB",
                                     "E01POICCS",
                                     "E01CREDSC",
                                     "E01POIFRQ",
                                     "E01POIPMD",
                                     "E01POIDYS",
                                     "E01POINPM",
                                     "E01POIODA",
                                     "E01POISPM",
                                     "E01POIDBR",
                                     "E01POICBR",
                                     "E01POIDSC",
                                     "E01POIPMT",
                                     "E01POIDAS"
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
                                   "E01CNTNME",
                                   "E01PODLDT",
                                   "E01POILPN",
                                   "E01POILPA",
                                   "E01LSTREF",
                                   "E01POIAM1",
                                   "E01POIAM2",
                                   "E01POIDCY",
                                   "E01POIDGL",
                                   "E01POIDAC",
                                   "E01POIDSB",
                                   "E01POIDCS",
                                   "E01DEBDSC",
                                   "E01POICCY",
                                   "E01POICGL",
                                   "E01POICAC",
                                   "E01POICSB",
                                   "E01POICCS",
                                   "E01CREDSC",
                                   "E01POIFRQ",
                                   "E01POIPMD",
                                   "E01POIDYS",
                                   "E01POINPM",
                                   "E01POIODA",
                                   "E01POISPM",
                                   "E01POIDBR",
                                   "E01POICBR",
                                   "E01POIDSC",
                                   "E01POIPMT",
                                   "E01POIDAS"
                                 };
  final static String fid = "1030122105100";
  final static String rid = "3F0EA2F024B1A";
  final static String fmtname = "EDD067501";
  final int FIELDCOUNT = 39;
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
  private CharacterField fieldE01CNTNME = null;
  private CharacterField fieldE01PODLDT = null;
  private DecimalField fieldE01POILPN = null;
  private DecimalField fieldE01POILPA = null;
  private CharacterField fieldE01LSTREF = null;
  private CharacterField fieldE01POIAM1 = null;
  private CharacterField fieldE01POIAM2 = null;
  private CharacterField fieldE01POIDCY = null;
  private DecimalField fieldE01POIDGL = null;
  private DecimalField fieldE01POIDAC = null;
  private DecimalField fieldE01POIDSB = null;
  private DecimalField fieldE01POIDCS = null;
  private CharacterField fieldE01DEBDSC = null;
  private CharacterField fieldE01POICCY = null;
  private DecimalField fieldE01POICGL = null;
  private DecimalField fieldE01POICAC = null;
  private DecimalField fieldE01POICSB = null;
  private DecimalField fieldE01POICCS = null;
  private CharacterField fieldE01CREDSC = null;
  private CharacterField fieldE01POIFRQ = null;
  private CharacterField fieldE01POIPMD = null;
  private CharacterField fieldE01POIDYS = null;
  private DecimalField fieldE01POINPM = null;
  private CharacterField fieldE01POIODA = null;
  private CharacterField fieldE01POISPM = null;
  private DecimalField fieldE01POIDBR = null;
  private DecimalField fieldE01POICBR = null;
  private CharacterField fieldE01POIDSC = null;
  private CharacterField fieldE01POIPMT = null;
  private CharacterField fieldE01POIDAS = null;

  /**
  * Constructor for EDD067501Message.
  */
  public EDD067501Message()
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
    recordsize = 345;
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
    fields[9] = fieldE01CNTNME =
    new CharacterField(message, HEADERSIZE + 42, 35, "E01CNTNME");
    fields[10] = fieldE01PODLDT =
    new CharacterField(message, HEADERSIZE + 77, 6, "E01PODLDT");
    fields[11] = fieldE01POILPN =
    new DecimalField(message, HEADERSIZE + 83, 5, 0, "E01POILPN");
    fields[12] = fieldE01POILPA =
    new DecimalField(message, HEADERSIZE + 88, 17, 2, "E01POILPA");
    fields[13] = fieldE01LSTREF =
    new CharacterField(message, HEADERSIZE + 105, 7, "E01LSTREF");
    fields[14] = fieldE01POIAM1 =
    new CharacterField(message, HEADERSIZE + 112, 11, "E01POIAM1");
    fields[15] = fieldE01POIAM2 =
    new CharacterField(message, HEADERSIZE + 123, 2, "E01POIAM2");
    fields[16] = fieldE01POIDCY =
    new CharacterField(message, HEADERSIZE + 125, 3, "E01POIDCY");
    fields[17] = fieldE01POIDGL =
    new DecimalField(message, HEADERSIZE + 128, 17, 0, "E01POIDGL");
    fields[18] = fieldE01POIDAC =
    new DecimalField(message, HEADERSIZE + 145, 13, 0, "E01POIDAC");
    fields[19] = fieldE01POIDSB =
    new DecimalField(message, HEADERSIZE + 158, 6, 0, "E01POIDSB");
    fields[20] = fieldE01POIDCS =
    new DecimalField(message, HEADERSIZE + 164, 9, 0, "E01POIDCS");
    fields[21] = fieldE01DEBDSC =
    new CharacterField(message, HEADERSIZE + 173, 35, "E01DEBDSC");
    fields[22] = fieldE01POICCY =
    new CharacterField(message, HEADERSIZE + 208, 3, "E01POICCY");
    fields[23] = fieldE01POICGL =
    new DecimalField(message, HEADERSIZE + 211, 17, 0, "E01POICGL");
    fields[24] = fieldE01POICAC =
    new DecimalField(message, HEADERSIZE + 228, 13, 0, "E01POICAC");
    fields[25] = fieldE01POICSB =
    new DecimalField(message, HEADERSIZE + 241, 6, 0, "E01POICSB");
    fields[26] = fieldE01POICCS =
    new DecimalField(message, HEADERSIZE + 247, 9, 0, "E01POICCS");
    fields[27] = fieldE01CREDSC =
    new CharacterField(message, HEADERSIZE + 256, 35, "E01CREDSC");
    fields[28] = fieldE01POIFRQ =
    new CharacterField(message, HEADERSIZE + 291, 1, "E01POIFRQ");
    fields[29] = fieldE01POIPMD =
    new CharacterField(message, HEADERSIZE + 292, 2, "E01POIPMD");
    fields[30] = fieldE01POIDYS =
    new CharacterField(message, HEADERSIZE + 294, 3, "E01POIDYS");
    fields[31] = fieldE01POINPM =
    new DecimalField(message, HEADERSIZE + 297, 5, 0, "E01POINPM");
    fields[32] = fieldE01POIODA =
    new CharacterField(message, HEADERSIZE + 302, 1, "E01POIODA");
    fields[33] = fieldE01POISPM =
    new CharacterField(message, HEADERSIZE + 303, 1, "E01POISPM");
    fields[34] = fieldE01POIDBR =
    new DecimalField(message, HEADERSIZE + 304, 4, 0, "E01POIDBR");
    fields[35] = fieldE01POICBR =
    new DecimalField(message, HEADERSIZE + 308, 4, 0, "E01POICBR");
    fields[36] = fieldE01POIDSC =
    new CharacterField(message, HEADERSIZE + 312, 30, "E01POIDSC");
    fields[37] = fieldE01POIPMT =
    new CharacterField(message, HEADERSIZE + 342, 1, "E01POIPMT");
    fields[38] = fieldE01POIDAS =
    new CharacterField(message, HEADERSIZE + 343, 2, "E01POIDAS");

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
  * Set field E01CNTNME using a String value.
  */
  public void setE01CNTNME(String newvalue)
  {
    fieldE01CNTNME.setString(newvalue);
  }

  /**
  * Get value of field E01CNTNME as a String.
  */
  public String getE01CNTNME()
  {
    return fieldE01CNTNME.getString();
  }

  /**
  * Set field E01PODLDT using a String value.
  */
  public void setE01PODLDT(String newvalue)
  {
    fieldE01PODLDT.setString(newvalue);
  }

  /**
  * Get value of field E01PODLDT as a String.
  */
  public String getE01PODLDT()
  {
    return fieldE01PODLDT.getString();
  }

  /**
  * Set field E01POILPN using a String value.
  */
  public void setE01POILPN(String newvalue)
  {
    fieldE01POILPN.setString(newvalue);
  }

  /**
  * Get value of field E01POILPN as a String.
  */
  public String getE01POILPN()
  {
    return fieldE01POILPN.getString();
  }

  /**
  * Set numeric field E01POILPN using a BigDecimal value.
  */
  public void setE01POILPN(BigDecimal newvalue)
  {
    fieldE01POILPN.setBigDecimal(newvalue);
  }

  /**
  * Return value of numeric field E01POILPN as a BigDecimal.
  */
  public BigDecimal getBigDecimalE01POILPN()
  {
    return fieldE01POILPN.getBigDecimal();
  }

  /**
  * Set field E01POILPA using a String value.
  */
  public void setE01POILPA(String newvalue)
  {
    fieldE01POILPA.setString(newvalue);
  }

  /**
  * Get value of field E01POILPA as a String.
  */
  public String getE01POILPA()
  {
    return fieldE01POILPA.getString();
  }

  /**
  * Set numeric field E01POILPA using a BigDecimal value.
  */
  public void setE01POILPA(BigDecimal newvalue)
  {
    fieldE01POILPA.setBigDecimal(newvalue);
  }

  /**
  * Return value of numeric field E01POILPA as a BigDecimal.
  */
  public BigDecimal getBigDecimalE01POILPA()
  {
    return fieldE01POILPA.getBigDecimal();
  }

  /**
  * Set field E01LSTREF using a String value.
  */
  public void setE01LSTREF(String newvalue)
  {
    fieldE01LSTREF.setString(newvalue);
  }

  /**
  * Get value of field E01LSTREF as a String.
  */
  public String getE01LSTREF()
  {
    return fieldE01LSTREF.getString();
  }

  /**
  * Set field E01POIAM1 using a String value.
  */
  public void setE01POIAM1(String newvalue)
  {
    fieldE01POIAM1.setString(newvalue);
  }

  /**
  * Get value of field E01POIAM1 as a String.
  */
  public String getE01POIAM1()
  {
    return fieldE01POIAM1.getString();
  }

  /**
  * Set field E01POIAM2 using a String value.
  */
  public void setE01POIAM2(String newvalue)
  {
    fieldE01POIAM2.setString(newvalue);
  }

  /**
  * Get value of field E01POIAM2 as a String.
  */
  public String getE01POIAM2()
  {
    return fieldE01POIAM2.getString();
  }

  /**
  * Set field E01POIDCY using a String value.
  */
  public void setE01POIDCY(String newvalue)
  {
    fieldE01POIDCY.setString(newvalue);
  }

  /**
  * Get value of field E01POIDCY as a String.
  */
  public String getE01POIDCY()
  {
    return fieldE01POIDCY.getString();
  }

  /**
  * Set field E01POIDGL using a String value.
  */
  public void setE01POIDGL(String newvalue)
  {
    fieldE01POIDGL.setString(newvalue);
  }

  /**
  * Get value of field E01POIDGL as a String.
  */
  public String getE01POIDGL()
  {
    return fieldE01POIDGL.getString();
  }

  /**
  * Set numeric field E01POIDGL using a BigDecimal value.
  */
  public void setE01POIDGL(BigDecimal newvalue)
  {
    fieldE01POIDGL.setBigDecimal(newvalue);
  }

  /**
  * Return value of numeric field E01POIDGL as a BigDecimal.
  */
  public BigDecimal getBigDecimalE01POIDGL()
  {
    return fieldE01POIDGL.getBigDecimal();
  }

  /**
  * Set field E01POIDAC using a String value.
  */
  public void setE01POIDAC(String newvalue)
  {
    fieldE01POIDAC.setString(newvalue);
  }

  /**
  * Get value of field E01POIDAC as a String.
  */
  public String getE01POIDAC()
  {
    return fieldE01POIDAC.getString();
  }

  /**
  * Set numeric field E01POIDAC using a BigDecimal value.
  */
  public void setE01POIDAC(BigDecimal newvalue)
  {
    fieldE01POIDAC.setBigDecimal(newvalue);
  }

  /**
  * Return value of numeric field E01POIDAC as a BigDecimal.
  */
  public BigDecimal getBigDecimalE01POIDAC()
  {
    return fieldE01POIDAC.getBigDecimal();
  }

  /**
  * Set field E01POIDSB using a String value.
  */
  public void setE01POIDSB(String newvalue)
  {
    fieldE01POIDSB.setString(newvalue);
  }

  /**
  * Get value of field E01POIDSB as a String.
  */
  public String getE01POIDSB()
  {
    return fieldE01POIDSB.getString();
  }

  /**
  * Set numeric field E01POIDSB using a BigDecimal value.
  */
  public void setE01POIDSB(BigDecimal newvalue)
  {
    fieldE01POIDSB.setBigDecimal(newvalue);
  }

  /**
  * Return value of numeric field E01POIDSB as a BigDecimal.
  */
  public BigDecimal getBigDecimalE01POIDSB()
  {
    return fieldE01POIDSB.getBigDecimal();
  }

  /**
  * Set field E01POIDCS using a String value.
  */
  public void setE01POIDCS(String newvalue)
  {
    fieldE01POIDCS.setString(newvalue);
  }

  /**
  * Get value of field E01POIDCS as a String.
  */
  public String getE01POIDCS()
  {
    return fieldE01POIDCS.getString();
  }

  /**
  * Set numeric field E01POIDCS using a BigDecimal value.
  */
  public void setE01POIDCS(BigDecimal newvalue)
  {
    fieldE01POIDCS.setBigDecimal(newvalue);
  }

  /**
  * Return value of numeric field E01POIDCS as a BigDecimal.
  */
  public BigDecimal getBigDecimalE01POIDCS()
  {
    return fieldE01POIDCS.getBigDecimal();
  }

  /**
  * Set field E01DEBDSC using a String value.
  */
  public void setE01DEBDSC(String newvalue)
  {
    fieldE01DEBDSC.setString(newvalue);
  }

  /**
  * Get value of field E01DEBDSC as a String.
  */
  public String getE01DEBDSC()
  {
    return fieldE01DEBDSC.getString();
  }

  /**
  * Set field E01POICCY using a String value.
  */
  public void setE01POICCY(String newvalue)
  {
    fieldE01POICCY.setString(newvalue);
  }

  /**
  * Get value of field E01POICCY as a String.
  */
  public String getE01POICCY()
  {
    return fieldE01POICCY.getString();
  }

  /**
  * Set field E01POICGL using a String value.
  */
  public void setE01POICGL(String newvalue)
  {
    fieldE01POICGL.setString(newvalue);
  }

  /**
  * Get value of field E01POICGL as a String.
  */
  public String getE01POICGL()
  {
    return fieldE01POICGL.getString();
  }

  /**
  * Set numeric field E01POICGL using a BigDecimal value.
  */
  public void setE01POICGL(BigDecimal newvalue)
  {
    fieldE01POICGL.setBigDecimal(newvalue);
  }

  /**
  * Return value of numeric field E01POICGL as a BigDecimal.
  */
  public BigDecimal getBigDecimalE01POICGL()
  {
    return fieldE01POICGL.getBigDecimal();
  }

  /**
  * Set field E01POICAC using a String value.
  */
  public void setE01POICAC(String newvalue)
  {
    fieldE01POICAC.setString(newvalue);
  }

  /**
  * Get value of field E01POICAC as a String.
  */
  public String getE01POICAC()
  {
    return fieldE01POICAC.getString();
  }

  /**
  * Set numeric field E01POICAC using a BigDecimal value.
  */
  public void setE01POICAC(BigDecimal newvalue)
  {
    fieldE01POICAC.setBigDecimal(newvalue);
  }

  /**
  * Return value of numeric field E01POICAC as a BigDecimal.
  */
  public BigDecimal getBigDecimalE01POICAC()
  {
    return fieldE01POICAC.getBigDecimal();
  }

  /**
  * Set field E01POICSB using a String value.
  */
  public void setE01POICSB(String newvalue)
  {
    fieldE01POICSB.setString(newvalue);
  }

  /**
  * Get value of field E01POICSB as a String.
  */
  public String getE01POICSB()
  {
    return fieldE01POICSB.getString();
  }

  /**
  * Set numeric field E01POICSB using a BigDecimal value.
  */
  public void setE01POICSB(BigDecimal newvalue)
  {
    fieldE01POICSB.setBigDecimal(newvalue);
  }

  /**
  * Return value of numeric field E01POICSB as a BigDecimal.
  */
  public BigDecimal getBigDecimalE01POICSB()
  {
    return fieldE01POICSB.getBigDecimal();
  }

  /**
  * Set field E01POICCS using a String value.
  */
  public void setE01POICCS(String newvalue)
  {
    fieldE01POICCS.setString(newvalue);
  }

  /**
  * Get value of field E01POICCS as a String.
  */
  public String getE01POICCS()
  {
    return fieldE01POICCS.getString();
  }

  /**
  * Set numeric field E01POICCS using a BigDecimal value.
  */
  public void setE01POICCS(BigDecimal newvalue)
  {
    fieldE01POICCS.setBigDecimal(newvalue);
  }

  /**
  * Return value of numeric field E01POICCS as a BigDecimal.
  */
  public BigDecimal getBigDecimalE01POICCS()
  {
    return fieldE01POICCS.getBigDecimal();
  }

  /**
  * Set field E01CREDSC using a String value.
  */
  public void setE01CREDSC(String newvalue)
  {
    fieldE01CREDSC.setString(newvalue);
  }

  /**
  * Get value of field E01CREDSC as a String.
  */
  public String getE01CREDSC()
  {
    return fieldE01CREDSC.getString();
  }

  /**
  * Set field E01POIFRQ using a String value.
  */
  public void setE01POIFRQ(String newvalue)
  {
    fieldE01POIFRQ.setString(newvalue);
  }

  /**
  * Get value of field E01POIFRQ as a String.
  */
  public String getE01POIFRQ()
  {
    return fieldE01POIFRQ.getString();
  }

  /**
  * Set field E01POIPMD using a String value.
  */
  public void setE01POIPMD(String newvalue)
  {
    fieldE01POIPMD.setString(newvalue);
  }

  /**
  * Get value of field E01POIPMD as a String.
  */
  public String getE01POIPMD()
  {
    return fieldE01POIPMD.getString();
  }

  /**
  * Set field E01POIDYS using a String value.
  */
  public void setE01POIDYS(String newvalue)
  {
    fieldE01POIDYS.setString(newvalue);
  }

  /**
  * Get value of field E01POIDYS as a String.
  */
  public String getE01POIDYS()
  {
    return fieldE01POIDYS.getString();
  }

  /**
  * Set field E01POINPM using a String value.
  */
  public void setE01POINPM(String newvalue)
  {
    fieldE01POINPM.setString(newvalue);
  }

  /**
  * Get value of field E01POINPM as a String.
  */
  public String getE01POINPM()
  {
    return fieldE01POINPM.getString();
  }

  /**
  * Set numeric field E01POINPM using a BigDecimal value.
  */
  public void setE01POINPM(BigDecimal newvalue)
  {
    fieldE01POINPM.setBigDecimal(newvalue);
  }

  /**
  * Return value of numeric field E01POINPM as a BigDecimal.
  */
  public BigDecimal getBigDecimalE01POINPM()
  {
    return fieldE01POINPM.getBigDecimal();
  }

  /**
  * Set field E01POIODA using a String value.
  */
  public void setE01POIODA(String newvalue)
  {
    fieldE01POIODA.setString(newvalue);
  }

  /**
  * Get value of field E01POIODA as a String.
  */
  public String getE01POIODA()
  {
    return fieldE01POIODA.getString();
  }

  /**
  * Set field E01POISPM using a String value.
  */
  public void setE01POISPM(String newvalue)
  {
    fieldE01POISPM.setString(newvalue);
  }

  /**
  * Get value of field E01POISPM as a String.
  */
  public String getE01POISPM()
  {
    return fieldE01POISPM.getString();
  }

  /**
  * Set field E01POIDBR using a String value.
  */
  public void setE01POIDBR(String newvalue)
  {
    fieldE01POIDBR.setString(newvalue);
  }

  /**
  * Get value of field E01POIDBR as a String.
  */
  public String getE01POIDBR()
  {
    return fieldE01POIDBR.getString();
  }

  /**
  * Set numeric field E01POIDBR using a BigDecimal value.
  */
  public void setE01POIDBR(BigDecimal newvalue)
  {
    fieldE01POIDBR.setBigDecimal(newvalue);
  }

  /**
  * Return value of numeric field E01POIDBR as a BigDecimal.
  */
  public BigDecimal getBigDecimalE01POIDBR()
  {
    return fieldE01POIDBR.getBigDecimal();
  }

  /**
  * Set field E01POICBR using a String value.
  */
  public void setE01POICBR(String newvalue)
  {
    fieldE01POICBR.setString(newvalue);
  }

  /**
  * Get value of field E01POICBR as a String.
  */
  public String getE01POICBR()
  {
    return fieldE01POICBR.getString();
  }

  /**
  * Set numeric field E01POICBR using a BigDecimal value.
  */
  public void setE01POICBR(BigDecimal newvalue)
  {
    fieldE01POICBR.setBigDecimal(newvalue);
  }

  /**
  * Return value of numeric field E01POICBR as a BigDecimal.
  */
  public BigDecimal getBigDecimalE01POICBR()
  {
    return fieldE01POICBR.getBigDecimal();
  }

  /**
  * Set field E01POIDSC using a String value.
  */
  public void setE01POIDSC(String newvalue)
  {
    fieldE01POIDSC.setString(newvalue);
  }

  /**
  * Get value of field E01POIDSC as a String.
  */
  public String getE01POIDSC()
  {
    return fieldE01POIDSC.getString();
  }

  /**
  * Set field E01POIPMT using a String value.
  */
  public void setE01POIPMT(String newvalue)
  {
    fieldE01POIPMT.setString(newvalue);
  }

  /**
  * Get value of field E01POIPMT as a String.
  */
  public String getE01POIPMT()
  {
    return fieldE01POIPMT.getString();
  }

  /**
  * Set field E01POIDAS using a String value.
  */
  public void setE01POIDAS(String newvalue)
  {
    fieldE01POIDAS.setString(newvalue);
  }

  /**
  * Get value of field E01POIDAS as a String.
  */
  public String getE01POIDAS()
  {
    return fieldE01POIDAS.getString();
  }

}