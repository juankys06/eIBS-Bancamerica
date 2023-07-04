package datapro.eibs.beans;

import datapro.eibs.sockets.*;
import java.io.*;
import java.math.*;

import java.util.*;

/**
* Class generated by AS/400 CRTCLASS command from ECD0005DS physical file definition.
* 
* File level identifier is 1051019102820.
* Record format level identifier is 45922BA2A584B.
*/

public class ECD0005DSMessage extends MessageRecord
{
  final static String fldnames[] = {
                                     "H05USERID",
                                     "H05PROGRM",
                                     "H05TIMSYS",
                                     "H05SCRCOD",
                                     "H05OPECOD",
                                     "H05FLGMAS",
                                     "H05FLGWK1",
                                     "H05FLGWK2",
                                     "H05FLGWK3",
                                     "E05CDRTPL",
                                     "E05CDRNPL",
                                     "E05CDRFEE",
                                     "E05CDRFED",
                                     "E05CDRFFC",
                                     "E05CDRCHG",
                                     "E05CDRAMT",
                                     "E05CDRACC",
                                     "E05CDRGLN",
                                     "E05CDRPER",
                                     "E05CDRPED",
                                     "E05CDRUSR",
                                     "E05CDRDAY",
                                     "E05CDRMON",
                                     "E05CDRYEA",
                                     "E05CDRTIM"
                                   };
  final static String tnames[] = {
                                   "H05USERID",
                                   "H05PROGRM",
                                   "H05TIMSYS",
                                   "H05SCRCOD",
                                   "H05OPECOD",
                                   "H05FLGMAS",
                                   "H05FLGWK1",
                                   "H05FLGWK2",
                                   "H05FLGWK3",
                                   "E05CDRTPL",
                                   "E05CDRNPL",
                                   "E05CDRFEE",
                                   "E05CDRFED",
                                   "E05CDRFFC",
                                   "E05CDRCHG",
                                   "E05CDRAMT",
                                   "E05CDRACC",
                                   "E05CDRGLN",
                                   "E05CDRPER",
                                   "E05CDRPED",
                                   "E05CDRUSR",
                                   "E05CDRDAY",
                                   "E05CDRMON",
                                   "E05CDRYEA",
                                   "E05CDRTIM"
                                 };
  final static String fid = "1051019102820";
  final static String rid = "45922BA2A584B";
  final static String fmtname = "ECD0005DS";
  final int FIELDCOUNT = 25;
  private static Hashtable tlookup = new Hashtable();
  private CharacterField fieldH05USERID = null;
  private CharacterField fieldH05PROGRM = null;
  private CharacterField fieldH05TIMSYS = null;
  private CharacterField fieldH05SCRCOD = null;
  private CharacterField fieldH05OPECOD = null;
  private CharacterField fieldH05FLGMAS = null;
  private CharacterField fieldH05FLGWK1 = null;
  private CharacterField fieldH05FLGWK2 = null;
  private CharacterField fieldH05FLGWK3 = null;
  private CharacterField fieldE05CDRTPL = null;
  private CharacterField fieldE05CDRNPL = null;
  private CharacterField fieldE05CDRFEE = null;
  private CharacterField fieldE05CDRFED = null;
  private CharacterField fieldE05CDRFFC = null;
  private DecimalField fieldE05CDRCHG = null;
  private DecimalField fieldE05CDRAMT = null;
  private DecimalField fieldE05CDRACC = null;
  private CharacterField fieldE05CDRGLN = null;
  private CharacterField fieldE05CDRPER = null;
  private CharacterField fieldE05CDRPED = null;
  private CharacterField fieldE05CDRUSR = null;
  private DecimalField fieldE05CDRDAY = null;
  private DecimalField fieldE05CDRMON = null;
  private DecimalField fieldE05CDRYEA = null;
  private DecimalField fieldE05CDRTIM = null;

  /**
  * Constructor for ECD0005DSMessage.
  */
  public ECD0005DSMessage()
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
    recordsize = 203;
    fileid = fid;
    recordid = rid;
    message = new byte[getByteLength()];
    formatname = fmtname;
    fieldnames = fldnames;
    tagnames = tnames;
    fields = new MessageField[FIELDCOUNT];

    fields[0] = fieldH05USERID =
    new CharacterField(message, HEADERSIZE + 0, 10, "H05USERID");
    fields[1] = fieldH05PROGRM =
    new CharacterField(message, HEADERSIZE + 10, 10, "H05PROGRM");
    fields[2] = fieldH05TIMSYS =
    new CharacterField(message, HEADERSIZE + 20, 12, "H05TIMSYS");
    fields[3] = fieldH05SCRCOD =
    new CharacterField(message, HEADERSIZE + 32, 2, "H05SCRCOD");
    fields[4] = fieldH05OPECOD =
    new CharacterField(message, HEADERSIZE + 34, 4, "H05OPECOD");
    fields[5] = fieldH05FLGMAS =
    new CharacterField(message, HEADERSIZE + 38, 1, "H05FLGMAS");
    fields[6] = fieldH05FLGWK1 =
    new CharacterField(message, HEADERSIZE + 39, 1, "H05FLGWK1");
    fields[7] = fieldH05FLGWK2 =
    new CharacterField(message, HEADERSIZE + 40, 1, "H05FLGWK2");
    fields[8] = fieldH05FLGWK3 =
    new CharacterField(message, HEADERSIZE + 41, 1, "H05FLGWK3");
    fields[9] = fieldE05CDRTPL =
    new CharacterField(message, HEADERSIZE + 42, 2, "E05CDRTPL");
    fields[10] = fieldE05CDRNPL =
    new CharacterField(message, HEADERSIZE + 44, 30, "E05CDRNPL");
    fields[11] = fieldE05CDRFEE =
    new CharacterField(message, HEADERSIZE + 74, 4, "E05CDRFEE");
    fields[12] = fieldE05CDRFED =
    new CharacterField(message, HEADERSIZE + 78, 15, "E05CDRFED");
    fields[13] = fieldE05CDRFFC =
    new CharacterField(message, HEADERSIZE + 93, 1, "E05CDRFFC");
    fields[14] = fieldE05CDRCHG =
    new DecimalField(message, HEADERSIZE + 94, 12, 6, "E05CDRCHG");
    fields[15] = fieldE05CDRAMT =
    new DecimalField(message, HEADERSIZE + 106, 14, 2, "E05CDRAMT");
    fields[16] = fieldE05CDRACC =
    new DecimalField(message, HEADERSIZE + 120, 13, 0, "E05CDRACC");
    fields[17] = fieldE05CDRGLN =
    new CharacterField(message, HEADERSIZE + 133, 20, "E05CDRGLN");
    fields[18] = fieldE05CDRPER =
    new CharacterField(message, HEADERSIZE + 153, 4, "E05CDRPER");
    fields[19] = fieldE05CDRPED =
    new CharacterField(message, HEADERSIZE + 157, 15, "E05CDRPED");
    fields[20] = fieldE05CDRUSR =
    new CharacterField(message, HEADERSIZE + 172, 15, "E05CDRUSR");
    fields[21] = fieldE05CDRDAY =
    new DecimalField(message, HEADERSIZE + 187, 3, 0, "E05CDRDAY");
    fields[22] = fieldE05CDRMON =
    new DecimalField(message, HEADERSIZE + 190, 3, 0, "E05CDRMON");
    fields[23] = fieldE05CDRYEA =
    new DecimalField(message, HEADERSIZE + 193, 3, 0, "E05CDRYEA");
    fields[24] = fieldE05CDRTIM =
    new DecimalField(message, HEADERSIZE + 196, 7, 0, "E05CDRTIM");

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
  * Set field H05USERID using a String value.
  */
  public void setH05USERID(String newvalue)
  {
    fieldH05USERID.setString(newvalue);
  }

  /**
  * Get value of field H05USERID as a String.
  */
  public String getH05USERID()
  {
    return fieldH05USERID.getString();
  }

  /**
  * Set field H05PROGRM using a String value.
  */
  public void setH05PROGRM(String newvalue)
  {
    fieldH05PROGRM.setString(newvalue);
  }

  /**
  * Get value of field H05PROGRM as a String.
  */
  public String getH05PROGRM()
  {
    return fieldH05PROGRM.getString();
  }

  /**
  * Set field H05TIMSYS using a String value.
  */
  public void setH05TIMSYS(String newvalue)
  {
    fieldH05TIMSYS.setString(newvalue);
  }

  /**
  * Get value of field H05TIMSYS as a String.
  */
  public String getH05TIMSYS()
  {
    return fieldH05TIMSYS.getString();
  }

  /**
  * Set field H05SCRCOD using a String value.
  */
  public void setH05SCRCOD(String newvalue)
  {
    fieldH05SCRCOD.setString(newvalue);
  }

  /**
  * Get value of field H05SCRCOD as a String.
  */
  public String getH05SCRCOD()
  {
    return fieldH05SCRCOD.getString();
  }

  /**
  * Set field H05OPECOD using a String value.
  */
  public void setH05OPECOD(String newvalue)
  {
    fieldH05OPECOD.setString(newvalue);
  }

  /**
  * Get value of field H05OPECOD as a String.
  */
  public String getH05OPECOD()
  {
    return fieldH05OPECOD.getString();
  }

  /**
  * Set field H05FLGMAS using a String value.
  */
  public void setH05FLGMAS(String newvalue)
  {
    fieldH05FLGMAS.setString(newvalue);
  }

  /**
  * Get value of field H05FLGMAS as a String.
  */
  public String getH05FLGMAS()
  {
    return fieldH05FLGMAS.getString();
  }

  /**
  * Set field H05FLGWK1 using a String value.
  */
  public void setH05FLGWK1(String newvalue)
  {
    fieldH05FLGWK1.setString(newvalue);
  }

  /**
  * Get value of field H05FLGWK1 as a String.
  */
  public String getH05FLGWK1()
  {
    return fieldH05FLGWK1.getString();
  }

  /**
  * Set field H05FLGWK2 using a String value.
  */
  public void setH05FLGWK2(String newvalue)
  {
    fieldH05FLGWK2.setString(newvalue);
  }

  /**
  * Get value of field H05FLGWK2 as a String.
  */
  public String getH05FLGWK2()
  {
    return fieldH05FLGWK2.getString();
  }

  /**
  * Set field H05FLGWK3 using a String value.
  */
  public void setH05FLGWK3(String newvalue)
  {
    fieldH05FLGWK3.setString(newvalue);
  }

  /**
  * Get value of field H05FLGWK3 as a String.
  */
  public String getH05FLGWK3()
  {
    return fieldH05FLGWK3.getString();
  }

  /**
  * Set field E05CDRTPL using a String value.
  */
  public void setE05CDRTPL(String newvalue)
  {
    fieldE05CDRTPL.setString(newvalue);
  }

  /**
  * Get value of field E05CDRTPL as a String.
  */
  public String getE05CDRTPL()
  {
    return fieldE05CDRTPL.getString();
  }

  /**
  * Set field E05CDRNPL using a String value.
  */
  public void setE05CDRNPL(String newvalue)
  {
    fieldE05CDRNPL.setString(newvalue);
  }

  /**
  * Get value of field E05CDRNPL as a String.
  */
  public String getE05CDRNPL()
  {
    return fieldE05CDRNPL.getString();
  }

  /**
  * Set field E05CDRFEE using a String value.
  */
  public void setE05CDRFEE(String newvalue)
  {
    fieldE05CDRFEE.setString(newvalue);
  }

  /**
  * Get value of field E05CDRFEE as a String.
  */
  public String getE05CDRFEE()
  {
    return fieldE05CDRFEE.getString();
  }

  /**
  * Set field E05CDRFED using a String value.
  */
  public void setE05CDRFED(String newvalue)
  {
    fieldE05CDRFED.setString(newvalue);
  }

  /**
  * Get value of field E05CDRFED as a String.
  */
  public String getE05CDRFED()
  {
    return fieldE05CDRFED.getString();
  }

  /**
  * Set field E05CDRFFC using a String value.
  */
  public void setE05CDRFFC(String newvalue)
  {
    fieldE05CDRFFC.setString(newvalue);
  }

  /**
  * Get value of field E05CDRFFC as a String.
  */
  public String getE05CDRFFC()
  {
    return fieldE05CDRFFC.getString();
  }

  /**
  * Set field E05CDRCHG using a String value.
  */
  public void setE05CDRCHG(String newvalue)
  {
    fieldE05CDRCHG.setString(newvalue);
  }

  /**
  * Get value of field E05CDRCHG as a String.
  */
  public String getE05CDRCHG()
  {
    return fieldE05CDRCHG.getString();
  }

  /**
  * Set numeric field E05CDRCHG using a BigDecimal value.
  */
  public void setE05CDRCHG(BigDecimal newvalue)
  {
    fieldE05CDRCHG.setBigDecimal(newvalue);
  }

  /**
  * Return value of numeric field E05CDRCHG as a BigDecimal.
  */
  public BigDecimal getBigDecimalE05CDRCHG()
  {
    return fieldE05CDRCHG.getBigDecimal();
  }

  /**
  * Set field E05CDRAMT using a String value.
  */
  public void setE05CDRAMT(String newvalue)
  {
    fieldE05CDRAMT.setString(newvalue);
  }

  /**
  * Get value of field E05CDRAMT as a String.
  */
  public String getE05CDRAMT()
  {
    return fieldE05CDRAMT.getString();
  }

  /**
  * Set numeric field E05CDRAMT using a BigDecimal value.
  */
  public void setE05CDRAMT(BigDecimal newvalue)
  {
    fieldE05CDRAMT.setBigDecimal(newvalue);
  }

  /**
  * Return value of numeric field E05CDRAMT as a BigDecimal.
  */
  public BigDecimal getBigDecimalE05CDRAMT()
  {
    return fieldE05CDRAMT.getBigDecimal();
  }

  /**
  * Set field E05CDRACC using a String value.
  */
  public void setE05CDRACC(String newvalue)
  {
    fieldE05CDRACC.setString(newvalue);
  }

  /**
  * Get value of field E05CDRACC as a String.
  */
  public String getE05CDRACC()
  {
    return fieldE05CDRACC.getString();
  }

  /**
  * Set numeric field E05CDRACC using a BigDecimal value.
  */
  public void setE05CDRACC(BigDecimal newvalue)
  {
    fieldE05CDRACC.setBigDecimal(newvalue);
  }

  /**
  * Return value of numeric field E05CDRACC as a BigDecimal.
  */
  public BigDecimal getBigDecimalE05CDRACC()
  {
    return fieldE05CDRACC.getBigDecimal();
  }

  /**
  * Set field E05CDRGLN using a String value.
  */
  public void setE05CDRGLN(String newvalue)
  {
    fieldE05CDRGLN.setString(newvalue);
  }

  /**
  * Get value of field E05CDRGLN as a String.
  */
  public String getE05CDRGLN()
  {
    return fieldE05CDRGLN.getString();
  }

  /**
  * Set field E05CDRPER using a String value.
  */
  public void setE05CDRPER(String newvalue)
  {
    fieldE05CDRPER.setString(newvalue);
  }

  /**
  * Get value of field E05CDRPER as a String.
  */
  public String getE05CDRPER()
  {
    return fieldE05CDRPER.getString();
  }

  /**
  * Set field E05CDRPED using a String value.
  */
  public void setE05CDRPED(String newvalue)
  {
    fieldE05CDRPED.setString(newvalue);
  }

  /**
  * Get value of field E05CDRPED as a String.
  */
  public String getE05CDRPED()
  {
    return fieldE05CDRPED.getString();
  }

  /**
  * Set field E05CDRUSR using a String value.
  */
  public void setE05CDRUSR(String newvalue)
  {
    fieldE05CDRUSR.setString(newvalue);
  }

  /**
  * Get value of field E05CDRUSR as a String.
  */
  public String getE05CDRUSR()
  {
    return fieldE05CDRUSR.getString();
  }

  /**
  * Set field E05CDRDAY using a String value.
  */
  public void setE05CDRDAY(String newvalue)
  {
    fieldE05CDRDAY.setString(newvalue);
  }

  /**
  * Get value of field E05CDRDAY as a String.
  */
  public String getE05CDRDAY()
  {
    return fieldE05CDRDAY.getString();
  }

  /**
  * Set numeric field E05CDRDAY using a BigDecimal value.
  */
  public void setE05CDRDAY(BigDecimal newvalue)
  {
    fieldE05CDRDAY.setBigDecimal(newvalue);
  }

  /**
  * Return value of numeric field E05CDRDAY as a BigDecimal.
  */
  public BigDecimal getBigDecimalE05CDRDAY()
  {
    return fieldE05CDRDAY.getBigDecimal();
  }

  /**
  * Set field E05CDRMON using a String value.
  */
  public void setE05CDRMON(String newvalue)
  {
    fieldE05CDRMON.setString(newvalue);
  }

  /**
  * Get value of field E05CDRMON as a String.
  */
  public String getE05CDRMON()
  {
    return fieldE05CDRMON.getString();
  }

  /**
  * Set numeric field E05CDRMON using a BigDecimal value.
  */
  public void setE05CDRMON(BigDecimal newvalue)
  {
    fieldE05CDRMON.setBigDecimal(newvalue);
  }

  /**
  * Return value of numeric field E05CDRMON as a BigDecimal.
  */
  public BigDecimal getBigDecimalE05CDRMON()
  {
    return fieldE05CDRMON.getBigDecimal();
  }

  /**
  * Set field E05CDRYEA using a String value.
  */
  public void setE05CDRYEA(String newvalue)
  {
    fieldE05CDRYEA.setString(newvalue);
  }

  /**
  * Get value of field E05CDRYEA as a String.
  */
  public String getE05CDRYEA()
  {
    return fieldE05CDRYEA.getString();
  }

  /**
  * Set numeric field E05CDRYEA using a BigDecimal value.
  */
  public void setE05CDRYEA(BigDecimal newvalue)
  {
    fieldE05CDRYEA.setBigDecimal(newvalue);
  }

  /**
  * Return value of numeric field E05CDRYEA as a BigDecimal.
  */
  public BigDecimal getBigDecimalE05CDRYEA()
  {
    return fieldE05CDRYEA.getBigDecimal();
  }

  /**
  * Set field E05CDRTIM using a String value.
  */
  public void setE05CDRTIM(String newvalue)
  {
    fieldE05CDRTIM.setString(newvalue);
  }

  /**
  * Get value of field E05CDRTIM as a String.
  */
  public String getE05CDRTIM()
  {
    return fieldE05CDRTIM.getString();
  }

  /**
  * Set numeric field E05CDRTIM using a BigDecimal value.
  */
  public void setE05CDRTIM(BigDecimal newvalue)
  {
    fieldE05CDRTIM.setBigDecimal(newvalue);
  }

  /**
  * Return value of numeric field E05CDRTIM as a BigDecimal.
  */
  public BigDecimal getBigDecimalE05CDRTIM()
  {
    return fieldE05CDRTIM.getBigDecimal();
  }

}
