package datapro.eibs.beans;

import datapro.eibs.sockets.*;
import java.io.*;
import java.math.*;

import java.util.*;

/**
* Class generated by AS/400 CRTCLASS command from ESD094001 physical file definition.
* 
* File level identifier is 1080229120112.
* Record format level identifier is 272799205F25D.
*/

public class ESD094001Message extends MessageRecord
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
                                     "E01CHGAPL",
                                     "E01CHGACC",
                                     "E01CHGBNK",
                                     "E01CHGPRT",
                                     "E01CHGPRO",
                                     "E01CHGPRC",
                                     "E01CHGSTS",
                                     "E01CHGIUS",
                                     "E01CHGIUY",
                                     "E01CHGIUM",
                                     "E01CHGIUD",
                                     "E01CHGIUT",
                                     "E01CHGAUS",
                                     "E01CHGAUY",
                                     "E01CHGAUM",
                                     "E01CHGAUD",
                                     "E01CHGAUT",
                                     "E01CUSNA1",
                                     "E01PRDDSC",
                                     "E01MODDSC",
                                     "E01CHGOBR",
                                     "E01CHGNBR",
                                     "E01CHGOFC",
                                     "E01CHGNFC",
                                     "E01OLDBRN",
                                     "E01NEWBRN",
                                     "E01OLDGLD",
                                     "E01NEWGLD",
                                     "E01OLDOFN",
                                     "E01NEWOFN",
                                     "E01NEWPRD",
                                     "E01NEWMDS",
                                     "E01CHGTYP",
                                     "E01TYPDSC",
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
                                   "E01CHGAPL",
                                   "E01CHGACC",
                                   "E01CHGBNK",
                                   "E01CHGPRT",
                                   "E01CHGPRO",
                                   "E01CHGPRC",
                                   "E01CHGSTS",
                                   "E01CHGIUS",
                                   "E01CHGIUY",
                                   "E01CHGIUM",
                                   "E01CHGIUD",
                                   "E01CHGIUT",
                                   "E01CHGAUS",
                                   "E01CHGAUY",
                                   "E01CHGAUM",
                                   "E01CHGAUD",
                                   "E01CHGAUT",
                                   "E01CUSNA1",
                                   "E01PRDDSC",
                                   "E01MODDSC",
                                   "E01CHGOBR",
                                   "E01CHGNBR",
                                   "E01CHGOFC",
                                   "E01CHGNFC",
                                   "E01OLDBRN",
                                   "E01NEWBRN",
                                   "E01OLDGLD",
                                   "E01NEWGLD",
                                   "E01OLDOFN",
                                   "E01NEWOFN",
                                   "E01NEWPRD",
                                   "E01NEWMDS",
                                   "E01CHGTYP",
                                   "E01TYPDSC",
                                   "E01OPECDE"
                                 };
  final static String fid = "1080229120112";
  final static String rid = "272799205F25D";
  final static String fmtname = "ESD094001";
  final int FIELDCOUNT = 44;
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
  private CharacterField fieldE01CHGAPL = null;
  private DecimalField fieldE01CHGACC = null;
  private CharacterField fieldE01CHGBNK = null;
  private CharacterField fieldE01CHGPRT = null;
  private CharacterField fieldE01CHGPRO = null;
  private CharacterField fieldE01CHGPRC = null;
  private CharacterField fieldE01CHGSTS = null;
  private CharacterField fieldE01CHGIUS = null;
  private DecimalField fieldE01CHGIUY = null;
  private DecimalField fieldE01CHGIUM = null;
  private DecimalField fieldE01CHGIUD = null;
  private DecimalField fieldE01CHGIUT = null;
  private CharacterField fieldE01CHGAUS = null;
  private DecimalField fieldE01CHGAUY = null;
  private DecimalField fieldE01CHGAUM = null;
  private DecimalField fieldE01CHGAUD = null;
  private DecimalField fieldE01CHGAUT = null;
  private CharacterField fieldE01CUSNA1 = null;
  private CharacterField fieldE01PRDDSC = null;
  private CharacterField fieldE01MODDSC = null;
  private DecimalField fieldE01CHGOBR = null;
  private DecimalField fieldE01CHGNBR = null;
  private CharacterField fieldE01CHGOFC = null;
  private CharacterField fieldE01CHGNFC = null;
  private CharacterField fieldE01OLDBRN = null;
  private CharacterField fieldE01NEWBRN = null;
  private CharacterField fieldE01OLDGLD = null;
  private CharacterField fieldE01NEWGLD = null;
  private CharacterField fieldE01OLDOFN = null;
  private CharacterField fieldE01NEWOFN = null;
  private CharacterField fieldE01NEWPRD = null;
  private CharacterField fieldE01NEWMDS = null;
  private CharacterField fieldE01CHGTYP = null;
  private CharacterField fieldE01TYPDSC = null;
  private CharacterField fieldE01OPECDE = null;

  /**
  * Constructor for ESD094001Message.
  */
  public ESD094001Message()
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
    recordsize = 547;
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
    fields[9] = fieldE01CHGAPL =
    new CharacterField(message, HEADERSIZE + 42, 2, "E01CHGAPL");
    fields[10] = fieldE01CHGACC =
    new DecimalField(message, HEADERSIZE + 44, 13, 0, "E01CHGACC");
    fields[11] = fieldE01CHGBNK =
    new CharacterField(message, HEADERSIZE + 57, 2, "E01CHGBNK");
    fields[12] = fieldE01CHGPRT =
    new CharacterField(message, HEADERSIZE + 59, 4, "E01CHGPRT");
    fields[13] = fieldE01CHGPRO =
    new CharacterField(message, HEADERSIZE + 63, 4, "E01CHGPRO");
    fields[14] = fieldE01CHGPRC =
    new CharacterField(message, HEADERSIZE + 67, 4, "E01CHGPRC");
    fields[15] = fieldE01CHGSTS =
    new CharacterField(message, HEADERSIZE + 71, 1, "E01CHGSTS");
    fields[16] = fieldE01CHGIUS =
    new CharacterField(message, HEADERSIZE + 72, 10, "E01CHGIUS");
    fields[17] = fieldE01CHGIUY =
    new DecimalField(message, HEADERSIZE + 82, 3, 0, "E01CHGIUY");
    fields[18] = fieldE01CHGIUM =
    new DecimalField(message, HEADERSIZE + 85, 3, 0, "E01CHGIUM");
    fields[19] = fieldE01CHGIUD =
    new DecimalField(message, HEADERSIZE + 88, 3, 0, "E01CHGIUD");
    fields[20] = fieldE01CHGIUT =
    new DecimalField(message, HEADERSIZE + 91, 7, 0, "E01CHGIUT");
    fields[21] = fieldE01CHGAUS =
    new CharacterField(message, HEADERSIZE + 98, 10, "E01CHGAUS");
    fields[22] = fieldE01CHGAUY =
    new DecimalField(message, HEADERSIZE + 108, 3, 0, "E01CHGAUY");
    fields[23] = fieldE01CHGAUM =
    new DecimalField(message, HEADERSIZE + 111, 3, 0, "E01CHGAUM");
    fields[24] = fieldE01CHGAUD =
    new DecimalField(message, HEADERSIZE + 114, 3, 0, "E01CHGAUD");
    fields[25] = fieldE01CHGAUT =
    new DecimalField(message, HEADERSIZE + 117, 7, 0, "E01CHGAUT");
    fields[26] = fieldE01CUSNA1 =
    new CharacterField(message, HEADERSIZE + 124, 35, "E01CUSNA1");
    fields[27] = fieldE01PRDDSC =
    new CharacterField(message, HEADERSIZE + 159, 35, "E01PRDDSC");
    fields[28] = fieldE01MODDSC =
    new CharacterField(message, HEADERSIZE + 194, 35, "E01MODDSC");
    fields[29] = fieldE01CHGOBR =
    new DecimalField(message, HEADERSIZE + 229, 4, 0, "E01CHGOBR");
    fields[30] = fieldE01CHGNBR =
    new DecimalField(message, HEADERSIZE + 233, 4, 0, "E01CHGNBR");
    fields[31] = fieldE01CHGOFC =
    new CharacterField(message, HEADERSIZE + 237, 4, "E01CHGOFC");
    fields[32] = fieldE01CHGNFC =
    new CharacterField(message, HEADERSIZE + 241, 4, "E01CHGNFC");
    fields[33] = fieldE01OLDBRN =
    new CharacterField(message, HEADERSIZE + 245, 35, "E01OLDBRN");
    fields[34] = fieldE01NEWBRN =
    new CharacterField(message, HEADERSIZE + 280, 35, "E01NEWBRN");
    fields[35] = fieldE01OLDGLD =
    new CharacterField(message, HEADERSIZE + 315, 35, "E01OLDGLD");
    fields[36] = fieldE01NEWGLD =
    new CharacterField(message, HEADERSIZE + 350, 35, "E01NEWGLD");
    fields[37] = fieldE01OLDOFN =
    new CharacterField(message, HEADERSIZE + 385, 35, "E01OLDOFN");
    fields[38] = fieldE01NEWOFN =
    new CharacterField(message, HEADERSIZE + 420, 35, "E01NEWOFN");
    fields[39] = fieldE01NEWPRD =
    new CharacterField(message, HEADERSIZE + 455, 35, "E01NEWPRD");
    fields[40] = fieldE01NEWMDS =
    new CharacterField(message, HEADERSIZE + 490, 35, "E01NEWMDS");
    fields[41] = fieldE01CHGTYP =
    new CharacterField(message, HEADERSIZE + 525, 1, "E01CHGTYP");
    fields[42] = fieldE01TYPDSC =
    new CharacterField(message, HEADERSIZE + 526, 20, "E01TYPDSC");
    fields[43] = fieldE01OPECDE =
    new CharacterField(message, HEADERSIZE + 546, 1, "E01OPECDE");

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
  * Set field E01CHGAPL using a String value.
  */
  public void setE01CHGAPL(String newvalue)
  {
    fieldE01CHGAPL.setString(newvalue);
  }

  /**
  * Get value of field E01CHGAPL as a String.
  */
  public String getE01CHGAPL()
  {
    return fieldE01CHGAPL.getString();
  }

  /**
  * Set field E01CHGACC using a String value.
  */
  public void setE01CHGACC(String newvalue)
  {
    fieldE01CHGACC.setString(newvalue);
  }

  /**
  * Get value of field E01CHGACC as a String.
  */
  public String getE01CHGACC()
  {
    return fieldE01CHGACC.getString();
  }

  /**
  * Set numeric field E01CHGACC using a BigDecimal value.
  */
  public void setE01CHGACC(BigDecimal newvalue)
  {
    fieldE01CHGACC.setBigDecimal(newvalue);
  }

  /**
  * Return value of numeric field E01CHGACC as a BigDecimal.
  */
  public BigDecimal getBigDecimalE01CHGACC()
  {
    return fieldE01CHGACC.getBigDecimal();
  }

  /**
  * Set field E01CHGBNK using a String value.
  */
  public void setE01CHGBNK(String newvalue)
  {
    fieldE01CHGBNK.setString(newvalue);
  }

  /**
  * Get value of field E01CHGBNK as a String.
  */
  public String getE01CHGBNK()
  {
    return fieldE01CHGBNK.getString();
  }

  /**
  * Set field E01CHGPRT using a String value.
  */
  public void setE01CHGPRT(String newvalue)
  {
    fieldE01CHGPRT.setString(newvalue);
  }

  /**
  * Get value of field E01CHGPRT as a String.
  */
  public String getE01CHGPRT()
  {
    return fieldE01CHGPRT.getString();
  }

  /**
  * Set field E01CHGPRO using a String value.
  */
  public void setE01CHGPRO(String newvalue)
  {
    fieldE01CHGPRO.setString(newvalue);
  }

  /**
  * Get value of field E01CHGPRO as a String.
  */
  public String getE01CHGPRO()
  {
    return fieldE01CHGPRO.getString();
  }

  /**
  * Set field E01CHGPRC using a String value.
  */
  public void setE01CHGPRC(String newvalue)
  {
    fieldE01CHGPRC.setString(newvalue);
  }

  /**
  * Get value of field E01CHGPRC as a String.
  */
  public String getE01CHGPRC()
  {
    return fieldE01CHGPRC.getString();
  }

  /**
  * Set field E01CHGSTS using a String value.
  */
  public void setE01CHGSTS(String newvalue)
  {
    fieldE01CHGSTS.setString(newvalue);
  }

  /**
  * Get value of field E01CHGSTS as a String.
  */
  public String getE01CHGSTS()
  {
    return fieldE01CHGSTS.getString();
  }

  /**
  * Set field E01CHGIUS using a String value.
  */
  public void setE01CHGIUS(String newvalue)
  {
    fieldE01CHGIUS.setString(newvalue);
  }

  /**
  * Get value of field E01CHGIUS as a String.
  */
  public String getE01CHGIUS()
  {
    return fieldE01CHGIUS.getString();
  }

  /**
  * Set field E01CHGIUY using a String value.
  */
  public void setE01CHGIUY(String newvalue)
  {
    fieldE01CHGIUY.setString(newvalue);
  }

  /**
  * Get value of field E01CHGIUY as a String.
  */
  public String getE01CHGIUY()
  {
    return fieldE01CHGIUY.getString();
  }

  /**
  * Set numeric field E01CHGIUY using a BigDecimal value.
  */
  public void setE01CHGIUY(BigDecimal newvalue)
  {
    fieldE01CHGIUY.setBigDecimal(newvalue);
  }

  /**
  * Return value of numeric field E01CHGIUY as a BigDecimal.
  */
  public BigDecimal getBigDecimalE01CHGIUY()
  {
    return fieldE01CHGIUY.getBigDecimal();
  }

  /**
  * Set field E01CHGIUM using a String value.
  */
  public void setE01CHGIUM(String newvalue)
  {
    fieldE01CHGIUM.setString(newvalue);
  }

  /**
  * Get value of field E01CHGIUM as a String.
  */
  public String getE01CHGIUM()
  {
    return fieldE01CHGIUM.getString();
  }

  /**
  * Set numeric field E01CHGIUM using a BigDecimal value.
  */
  public void setE01CHGIUM(BigDecimal newvalue)
  {
    fieldE01CHGIUM.setBigDecimal(newvalue);
  }

  /**
  * Return value of numeric field E01CHGIUM as a BigDecimal.
  */
  public BigDecimal getBigDecimalE01CHGIUM()
  {
    return fieldE01CHGIUM.getBigDecimal();
  }

  /**
  * Set field E01CHGIUD using a String value.
  */
  public void setE01CHGIUD(String newvalue)
  {
    fieldE01CHGIUD.setString(newvalue);
  }

  /**
  * Get value of field E01CHGIUD as a String.
  */
  public String getE01CHGIUD()
  {
    return fieldE01CHGIUD.getString();
  }

  /**
  * Set numeric field E01CHGIUD using a BigDecimal value.
  */
  public void setE01CHGIUD(BigDecimal newvalue)
  {
    fieldE01CHGIUD.setBigDecimal(newvalue);
  }

  /**
  * Return value of numeric field E01CHGIUD as a BigDecimal.
  */
  public BigDecimal getBigDecimalE01CHGIUD()
  {
    return fieldE01CHGIUD.getBigDecimal();
  }

  /**
  * Set field E01CHGIUT using a String value.
  */
  public void setE01CHGIUT(String newvalue)
  {
    fieldE01CHGIUT.setString(newvalue);
  }

  /**
  * Get value of field E01CHGIUT as a String.
  */
  public String getE01CHGIUT()
  {
    return fieldE01CHGIUT.getString();
  }

  /**
  * Set numeric field E01CHGIUT using a BigDecimal value.
  */
  public void setE01CHGIUT(BigDecimal newvalue)
  {
    fieldE01CHGIUT.setBigDecimal(newvalue);
  }

  /**
  * Return value of numeric field E01CHGIUT as a BigDecimal.
  */
  public BigDecimal getBigDecimalE01CHGIUT()
  {
    return fieldE01CHGIUT.getBigDecimal();
  }

  /**
  * Set field E01CHGAUS using a String value.
  */
  public void setE01CHGAUS(String newvalue)
  {
    fieldE01CHGAUS.setString(newvalue);
  }

  /**
  * Get value of field E01CHGAUS as a String.
  */
  public String getE01CHGAUS()
  {
    return fieldE01CHGAUS.getString();
  }

  /**
  * Set field E01CHGAUY using a String value.
  */
  public void setE01CHGAUY(String newvalue)
  {
    fieldE01CHGAUY.setString(newvalue);
  }

  /**
  * Get value of field E01CHGAUY as a String.
  */
  public String getE01CHGAUY()
  {
    return fieldE01CHGAUY.getString();
  }

  /**
  * Set numeric field E01CHGAUY using a BigDecimal value.
  */
  public void setE01CHGAUY(BigDecimal newvalue)
  {
    fieldE01CHGAUY.setBigDecimal(newvalue);
  }

  /**
  * Return value of numeric field E01CHGAUY as a BigDecimal.
  */
  public BigDecimal getBigDecimalE01CHGAUY()
  {
    return fieldE01CHGAUY.getBigDecimal();
  }

  /**
  * Set field E01CHGAUM using a String value.
  */
  public void setE01CHGAUM(String newvalue)
  {
    fieldE01CHGAUM.setString(newvalue);
  }

  /**
  * Get value of field E01CHGAUM as a String.
  */
  public String getE01CHGAUM()
  {
    return fieldE01CHGAUM.getString();
  }

  /**
  * Set numeric field E01CHGAUM using a BigDecimal value.
  */
  public void setE01CHGAUM(BigDecimal newvalue)
  {
    fieldE01CHGAUM.setBigDecimal(newvalue);
  }

  /**
  * Return value of numeric field E01CHGAUM as a BigDecimal.
  */
  public BigDecimal getBigDecimalE01CHGAUM()
  {
    return fieldE01CHGAUM.getBigDecimal();
  }

  /**
  * Set field E01CHGAUD using a String value.
  */
  public void setE01CHGAUD(String newvalue)
  {
    fieldE01CHGAUD.setString(newvalue);
  }

  /**
  * Get value of field E01CHGAUD as a String.
  */
  public String getE01CHGAUD()
  {
    return fieldE01CHGAUD.getString();
  }

  /**
  * Set numeric field E01CHGAUD using a BigDecimal value.
  */
  public void setE01CHGAUD(BigDecimal newvalue)
  {
    fieldE01CHGAUD.setBigDecimal(newvalue);
  }

  /**
  * Return value of numeric field E01CHGAUD as a BigDecimal.
  */
  public BigDecimal getBigDecimalE01CHGAUD()
  {
    return fieldE01CHGAUD.getBigDecimal();
  }

  /**
  * Set field E01CHGAUT using a String value.
  */
  public void setE01CHGAUT(String newvalue)
  {
    fieldE01CHGAUT.setString(newvalue);
  }

  /**
  * Get value of field E01CHGAUT as a String.
  */
  public String getE01CHGAUT()
  {
    return fieldE01CHGAUT.getString();
  }

  /**
  * Set numeric field E01CHGAUT using a BigDecimal value.
  */
  public void setE01CHGAUT(BigDecimal newvalue)
  {
    fieldE01CHGAUT.setBigDecimal(newvalue);
  }

  /**
  * Return value of numeric field E01CHGAUT as a BigDecimal.
  */
  public BigDecimal getBigDecimalE01CHGAUT()
  {
    return fieldE01CHGAUT.getBigDecimal();
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
  * Set field E01PRDDSC using a String value.
  */
  public void setE01PRDDSC(String newvalue)
  {
    fieldE01PRDDSC.setString(newvalue);
  }

  /**
  * Get value of field E01PRDDSC as a String.
  */
  public String getE01PRDDSC()
  {
    return fieldE01PRDDSC.getString();
  }

  /**
  * Set field E01MODDSC using a String value.
  */
  public void setE01MODDSC(String newvalue)
  {
    fieldE01MODDSC.setString(newvalue);
  }

  /**
  * Get value of field E01MODDSC as a String.
  */
  public String getE01MODDSC()
  {
    return fieldE01MODDSC.getString();
  }

  /**
  * Set field E01CHGOBR using a String value.
  */
  public void setE01CHGOBR(String newvalue)
  {
    fieldE01CHGOBR.setString(newvalue);
  }

  /**
  * Get value of field E01CHGOBR as a String.
  */
  public String getE01CHGOBR()
  {
    return fieldE01CHGOBR.getString();
  }

  /**
  * Set numeric field E01CHGOBR using a BigDecimal value.
  */
  public void setE01CHGOBR(BigDecimal newvalue)
  {
    fieldE01CHGOBR.setBigDecimal(newvalue);
  }

  /**
  * Return value of numeric field E01CHGOBR as a BigDecimal.
  */
  public BigDecimal getBigDecimalE01CHGOBR()
  {
    return fieldE01CHGOBR.getBigDecimal();
  }

  /**
  * Set field E01CHGNBR using a String value.
  */
  public void setE01CHGNBR(String newvalue)
  {
    fieldE01CHGNBR.setString(newvalue);
  }

  /**
  * Get value of field E01CHGNBR as a String.
  */
  public String getE01CHGNBR()
  {
    return fieldE01CHGNBR.getString();
  }

  /**
  * Set numeric field E01CHGNBR using a BigDecimal value.
  */
  public void setE01CHGNBR(BigDecimal newvalue)
  {
    fieldE01CHGNBR.setBigDecimal(newvalue);
  }

  /**
  * Return value of numeric field E01CHGNBR as a BigDecimal.
  */
  public BigDecimal getBigDecimalE01CHGNBR()
  {
    return fieldE01CHGNBR.getBigDecimal();
  }

  /**
  * Set field E01CHGOFC using a String value.
  */
  public void setE01CHGOFC(String newvalue)
  {
    fieldE01CHGOFC.setString(newvalue);
  }

  /**
  * Get value of field E01CHGOFC as a String.
  */
  public String getE01CHGOFC()
  {
    return fieldE01CHGOFC.getString();
  }

  /**
  * Set field E01CHGNFC using a String value.
  */
  public void setE01CHGNFC(String newvalue)
  {
    fieldE01CHGNFC.setString(newvalue);
  }

  /**
  * Get value of field E01CHGNFC as a String.
  */
  public String getE01CHGNFC()
  {
    return fieldE01CHGNFC.getString();
  }

  /**
  * Set field E01OLDBRN using a String value.
  */
  public void setE01OLDBRN(String newvalue)
  {
    fieldE01OLDBRN.setString(newvalue);
  }

  /**
  * Get value of field E01OLDBRN as a String.
  */
  public String getE01OLDBRN()
  {
    return fieldE01OLDBRN.getString();
  }

  /**
  * Set field E01NEWBRN using a String value.
  */
  public void setE01NEWBRN(String newvalue)
  {
    fieldE01NEWBRN.setString(newvalue);
  }

  /**
  * Get value of field E01NEWBRN as a String.
  */
  public String getE01NEWBRN()
  {
    return fieldE01NEWBRN.getString();
  }

  /**
  * Set field E01OLDGLD using a String value.
  */
  public void setE01OLDGLD(String newvalue)
  {
    fieldE01OLDGLD.setString(newvalue);
  }

  /**
  * Get value of field E01OLDGLD as a String.
  */
  public String getE01OLDGLD()
  {
    return fieldE01OLDGLD.getString();
  }

  /**
  * Set field E01NEWGLD using a String value.
  */
  public void setE01NEWGLD(String newvalue)
  {
    fieldE01NEWGLD.setString(newvalue);
  }

  /**
  * Get value of field E01NEWGLD as a String.
  */
  public String getE01NEWGLD()
  {
    return fieldE01NEWGLD.getString();
  }

  /**
  * Set field E01OLDOFN using a String value.
  */
  public void setE01OLDOFN(String newvalue)
  {
    fieldE01OLDOFN.setString(newvalue);
  }

  /**
  * Get value of field E01OLDOFN as a String.
  */
  public String getE01OLDOFN()
  {
    return fieldE01OLDOFN.getString();
  }

  /**
  * Set field E01NEWOFN using a String value.
  */
  public void setE01NEWOFN(String newvalue)
  {
    fieldE01NEWOFN.setString(newvalue);
  }

  /**
  * Get value of field E01NEWOFN as a String.
  */
  public String getE01NEWOFN()
  {
    return fieldE01NEWOFN.getString();
  }

  /**
  * Set field E01NEWPRD using a String value.
  */
  public void setE01NEWPRD(String newvalue)
  {
    fieldE01NEWPRD.setString(newvalue);
  }

  /**
  * Get value of field E01NEWPRD as a String.
  */
  public String getE01NEWPRD()
  {
    return fieldE01NEWPRD.getString();
  }

  /**
  * Set field E01NEWMDS using a String value.
  */
  public void setE01NEWMDS(String newvalue)
  {
    fieldE01NEWMDS.setString(newvalue);
  }

  /**
  * Get value of field E01NEWMDS as a String.
  */
  public String getE01NEWMDS()
  {
    return fieldE01NEWMDS.getString();
  }

  /**
  * Set field E01CHGTYP using a String value.
  */
  public void setE01CHGTYP(String newvalue)
  {
    fieldE01CHGTYP.setString(newvalue);
  }

  /**
  * Get value of field E01CHGTYP as a String.
  */
  public String getE01CHGTYP()
  {
    return fieldE01CHGTYP.getString();
  }

  /**
  * Set field E01TYPDSC using a String value.
  */
  public void setE01TYPDSC(String newvalue)
  {
    fieldE01TYPDSC.setString(newvalue);
  }

  /**
  * Get value of field E01TYPDSC as a String.
  */
  public String getE01TYPDSC()
  {
    return fieldE01TYPDSC.getString();
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
