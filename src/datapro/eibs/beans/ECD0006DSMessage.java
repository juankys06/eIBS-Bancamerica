package datapro.eibs.beans;

import datapro.eibs.sockets.*;
import java.io.*;
import java.math.*;

import java.util.*;

/**
* Class generated by AS/400 CRTCLASS command from ECD0006DS physical file definition.
* 
* File level identifier is 1051107142232.
* Record format level identifier is 35ED940A42FF8.
*/

public class ECD0006DSMessage extends MessageRecord
{
  final static String fldnames[] = {
                                     "H06USERID",
                                     "H06PROGRM",
                                     "H06TIMSYS",
                                     "H06SCRCOD",
                                     "H06OPECOD",
                                     "H06FLGMAS",
                                     "H06FLGWK1",
                                     "H06FLGWK2",
                                     "H06FLGWK3",
                                     "E06CDRBRN",
                                     "E06CDRTPL",
                                     "E06CDRNPL",
                                     "E06CDRMIN",
                                     "E06CDRMAX",
                                     "E06CDRUSR",
                                     "E06CDRDAY",
                                     "E06CDRMON",
                                     "E06CDRYEA",
                                     "E06CDRTIM"
                                   };
  final static String tnames[] = {
                                   "H06USERID",
                                   "H06PROGRM",
                                   "H06TIMSYS",
                                   "H06SCRCOD",
                                   "H06OPECOD",
                                   "H06FLGMAS",
                                   "H06FLGWK1",
                                   "H06FLGWK2",
                                   "H06FLGWK3",
                                   "E06CDRBRN",
                                   "E06CDRTPL",
                                   "E06CDRNPL",
                                   "E06CDRMIN",
                                   "E06CDRMAX",
                                   "E06CDRUSR",
                                   "E06CDRDAY",
                                   "E06CDRMON",
                                   "E06CDRYEA",
                                   "E06CDRTIM"
                                 };
  final static String fid = "1051107142232";
  final static String rid = "35ED940A42FF8";
  final static String fmtname = "ECD0006DS";
  final int FIELDCOUNT = 19;
  private static Hashtable tlookup = new Hashtable();
  private CharacterField fieldH06USERID = null;
  private CharacterField fieldH06PROGRM = null;
  private CharacterField fieldH06TIMSYS = null;
  private CharacterField fieldH06SCRCOD = null;
  private CharacterField fieldH06OPECOD = null;
  private CharacterField fieldH06FLGMAS = null;
  private CharacterField fieldH06FLGWK1 = null;
  private CharacterField fieldH06FLGWK2 = null;
  private CharacterField fieldH06FLGWK3 = null;
  private DecimalField fieldE06CDRBRN = null;
  private CharacterField fieldE06CDRTPL = null;
  private CharacterField fieldE06CDRNPL = null;
  private DecimalField fieldE06CDRMIN = null;
  private DecimalField fieldE06CDRMAX = null;
  private CharacterField fieldE06CDRUSR = null;
  private DecimalField fieldE06CDRDAY = null;
  private DecimalField fieldE06CDRMON = null;
  private DecimalField fieldE06CDRYEA = null;
  private DecimalField fieldE06CDRTIM = null;

  /**
  * Constructor for ECD0006DSMessage.
  */
  public ECD0006DSMessage()
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
    recordsize = 123;
    fileid = fid;
    recordid = rid;
    message = new byte[getByteLength()];
    formatname = fmtname;
    fieldnames = fldnames;
    tagnames = tnames;
    fields = new MessageField[FIELDCOUNT];

    fields[0] = fieldH06USERID =
    new CharacterField(message, HEADERSIZE + 0, 10, "H06USERID");
    fields[1] = fieldH06PROGRM =
    new CharacterField(message, HEADERSIZE + 10, 10, "H06PROGRM");
    fields[2] = fieldH06TIMSYS =
    new CharacterField(message, HEADERSIZE + 20, 12, "H06TIMSYS");
    fields[3] = fieldH06SCRCOD =
    new CharacterField(message, HEADERSIZE + 32, 2, "H06SCRCOD");
    fields[4] = fieldH06OPECOD =
    new CharacterField(message, HEADERSIZE + 34, 4, "H06OPECOD");
    fields[5] = fieldH06FLGMAS =
    new CharacterField(message, HEADERSIZE + 38, 1, "H06FLGMAS");
    fields[6] = fieldH06FLGWK1 =
    new CharacterField(message, HEADERSIZE + 39, 1, "H06FLGWK1");
    fields[7] = fieldH06FLGWK2 =
    new CharacterField(message, HEADERSIZE + 40, 1, "H06FLGWK2");
    fields[8] = fieldH06FLGWK3 =
    new CharacterField(message, HEADERSIZE + 41, 1, "H06FLGWK3");
    fields[9] = fieldE06CDRBRN =
    new DecimalField(message, HEADERSIZE + 42, 4, 0, "E06CDRBRN");
    fields[10] = fieldE06CDRTPL =
    new CharacterField(message, HEADERSIZE + 46, 2, "E06CDRTPL");
    fields[11] = fieldE06CDRNPL =
    new CharacterField(message, HEADERSIZE + 48, 30, "E06CDRNPL");
    fields[12] = fieldE06CDRMIN =
    new DecimalField(message, HEADERSIZE + 78, 7, 0, "E06CDRMIN");
    fields[13] = fieldE06CDRMAX =
    new DecimalField(message, HEADERSIZE + 85, 7, 0, "E06CDRMAX");
    fields[14] = fieldE06CDRUSR =
    new CharacterField(message, HEADERSIZE + 92, 15, "E06CDRUSR");
    fields[15] = fieldE06CDRDAY =
    new DecimalField(message, HEADERSIZE + 107, 3, 0, "E06CDRDAY");
    fields[16] = fieldE06CDRMON =
    new DecimalField(message, HEADERSIZE + 110, 3, 0, "E06CDRMON");
    fields[17] = fieldE06CDRYEA =
    new DecimalField(message, HEADERSIZE + 113, 3, 0, "E06CDRYEA");
    fields[18] = fieldE06CDRTIM =
    new DecimalField(message, HEADERSIZE + 116, 7, 0, "E06CDRTIM");

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
  * Set field H06USERID using a String value.
  */
  public void setH06USERID(String newvalue)
  {
    fieldH06USERID.setString(newvalue);
  }

  /**
  * Get value of field H06USERID as a String.
  */
  public String getH06USERID()
  {
    return fieldH06USERID.getString();
  }

  /**
  * Set field H06PROGRM using a String value.
  */
  public void setH06PROGRM(String newvalue)
  {
    fieldH06PROGRM.setString(newvalue);
  }

  /**
  * Get value of field H06PROGRM as a String.
  */
  public String getH06PROGRM()
  {
    return fieldH06PROGRM.getString();
  }

  /**
  * Set field H06TIMSYS using a String value.
  */
  public void setH06TIMSYS(String newvalue)
  {
    fieldH06TIMSYS.setString(newvalue);
  }

  /**
  * Get value of field H06TIMSYS as a String.
  */
  public String getH06TIMSYS()
  {
    return fieldH06TIMSYS.getString();
  }

  /**
  * Set field H06SCRCOD using a String value.
  */
  public void setH06SCRCOD(String newvalue)
  {
    fieldH06SCRCOD.setString(newvalue);
  }

  /**
  * Get value of field H06SCRCOD as a String.
  */
  public String getH06SCRCOD()
  {
    return fieldH06SCRCOD.getString();
  }

  /**
  * Set field H06OPECOD using a String value.
  */
  public void setH06OPECOD(String newvalue)
  {
    fieldH06OPECOD.setString(newvalue);
  }

  /**
  * Get value of field H06OPECOD as a String.
  */
  public String getH06OPECOD()
  {
    return fieldH06OPECOD.getString();
  }

  /**
  * Set field H06FLGMAS using a String value.
  */
  public void setH06FLGMAS(String newvalue)
  {
    fieldH06FLGMAS.setString(newvalue);
  }

  /**
  * Get value of field H06FLGMAS as a String.
  */
  public String getH06FLGMAS()
  {
    return fieldH06FLGMAS.getString();
  }

  /**
  * Set field H06FLGWK1 using a String value.
  */
  public void setH06FLGWK1(String newvalue)
  {
    fieldH06FLGWK1.setString(newvalue);
  }

  /**
  * Get value of field H06FLGWK1 as a String.
  */
  public String getH06FLGWK1()
  {
    return fieldH06FLGWK1.getString();
  }

  /**
  * Set field H06FLGWK2 using a String value.
  */
  public void setH06FLGWK2(String newvalue)
  {
    fieldH06FLGWK2.setString(newvalue);
  }

  /**
  * Get value of field H06FLGWK2 as a String.
  */
  public String getH06FLGWK2()
  {
    return fieldH06FLGWK2.getString();
  }

  /**
  * Set field H06FLGWK3 using a String value.
  */
  public void setH06FLGWK3(String newvalue)
  {
    fieldH06FLGWK3.setString(newvalue);
  }

  /**
  * Get value of field H06FLGWK3 as a String.
  */
  public String getH06FLGWK3()
  {
    return fieldH06FLGWK3.getString();
  }

  /**
  * Set field E06CDRBRN using a String value.
  */
  public void setE06CDRBRN(String newvalue)
  {
    fieldE06CDRBRN.setString(newvalue);
  }

  /**
  * Get value of field E06CDRBRN as a String.
  */
  public String getE06CDRBRN()
  {
    return fieldE06CDRBRN.getString();
  }

  /**
  * Set numeric field E06CDRBRN using a BigDecimal value.
  */
  public void setE06CDRBRN(BigDecimal newvalue)
  {
    fieldE06CDRBRN.setBigDecimal(newvalue);
  }

  /**
  * Return value of numeric field E06CDRBRN as a BigDecimal.
  */
  public BigDecimal getBigDecimalE06CDRBRN()
  {
    return fieldE06CDRBRN.getBigDecimal();
  }

  /**
  * Set field E06CDRTPL using a String value.
  */
  public void setE06CDRTPL(String newvalue)
  {
    fieldE06CDRTPL.setString(newvalue);
  }

  /**
  * Get value of field E06CDRTPL as a String.
  */
  public String getE06CDRTPL()
  {
    return fieldE06CDRTPL.getString();
  }

  /**
  * Set field E06CDRNPL using a String value.
  */
  public void setE06CDRNPL(String newvalue)
  {
    fieldE06CDRNPL.setString(newvalue);
  }

  /**
  * Get value of field E06CDRNPL as a String.
  */
  public String getE06CDRNPL()
  {
    return fieldE06CDRNPL.getString();
  }

  /**
  * Set field E06CDRMIN using a String value.
  */
  public void setE06CDRMIN(String newvalue)
  {
    fieldE06CDRMIN.setString(newvalue);
  }

  /**
  * Get value of field E06CDRMIN as a String.
  */
  public String getE06CDRMIN()
  {
    return fieldE06CDRMIN.getString();
  }

  /**
  * Set numeric field E06CDRMIN using a BigDecimal value.
  */
  public void setE06CDRMIN(BigDecimal newvalue)
  {
    fieldE06CDRMIN.setBigDecimal(newvalue);
  }

  /**
  * Return value of numeric field E06CDRMIN as a BigDecimal.
  */
  public BigDecimal getBigDecimalE06CDRMIN()
  {
    return fieldE06CDRMIN.getBigDecimal();
  }

  /**
  * Set field E06CDRMAX using a String value.
  */
  public void setE06CDRMAX(String newvalue)
  {
    fieldE06CDRMAX.setString(newvalue);
  }

  /**
  * Get value of field E06CDRMAX as a String.
  */
  public String getE06CDRMAX()
  {
    return fieldE06CDRMAX.getString();
  }

  /**
  * Set numeric field E06CDRMAX using a BigDecimal value.
  */
  public void setE06CDRMAX(BigDecimal newvalue)
  {
    fieldE06CDRMAX.setBigDecimal(newvalue);
  }

  /**
  * Return value of numeric field E06CDRMAX as a BigDecimal.
  */
  public BigDecimal getBigDecimalE06CDRMAX()
  {
    return fieldE06CDRMAX.getBigDecimal();
  }

  /**
  * Set field E06CDRUSR using a String value.
  */
  public void setE06CDRUSR(String newvalue)
  {
    fieldE06CDRUSR.setString(newvalue);
  }

  /**
  * Get value of field E06CDRUSR as a String.
  */
  public String getE06CDRUSR()
  {
    return fieldE06CDRUSR.getString();
  }

  /**
  * Set field E06CDRDAY using a String value.
  */
  public void setE06CDRDAY(String newvalue)
  {
    fieldE06CDRDAY.setString(newvalue);
  }

  /**
  * Get value of field E06CDRDAY as a String.
  */
  public String getE06CDRDAY()
  {
    return fieldE06CDRDAY.getString();
  }

  /**
  * Set numeric field E06CDRDAY using a BigDecimal value.
  */
  public void setE06CDRDAY(BigDecimal newvalue)
  {
    fieldE06CDRDAY.setBigDecimal(newvalue);
  }

  /**
  * Return value of numeric field E06CDRDAY as a BigDecimal.
  */
  public BigDecimal getBigDecimalE06CDRDAY()
  {
    return fieldE06CDRDAY.getBigDecimal();
  }

  /**
  * Set field E06CDRMON using a String value.
  */
  public void setE06CDRMON(String newvalue)
  {
    fieldE06CDRMON.setString(newvalue);
  }

  /**
  * Get value of field E06CDRMON as a String.
  */
  public String getE06CDRMON()
  {
    return fieldE06CDRMON.getString();
  }

  /**
  * Set numeric field E06CDRMON using a BigDecimal value.
  */
  public void setE06CDRMON(BigDecimal newvalue)
  {
    fieldE06CDRMON.setBigDecimal(newvalue);
  }

  /**
  * Return value of numeric field E06CDRMON as a BigDecimal.
  */
  public BigDecimal getBigDecimalE06CDRMON()
  {
    return fieldE06CDRMON.getBigDecimal();
  }

  /**
  * Set field E06CDRYEA using a String value.
  */
  public void setE06CDRYEA(String newvalue)
  {
    fieldE06CDRYEA.setString(newvalue);
  }

  /**
  * Get value of field E06CDRYEA as a String.
  */
  public String getE06CDRYEA()
  {
    return fieldE06CDRYEA.getString();
  }

  /**
  * Set numeric field E06CDRYEA using a BigDecimal value.
  */
  public void setE06CDRYEA(BigDecimal newvalue)
  {
    fieldE06CDRYEA.setBigDecimal(newvalue);
  }

  /**
  * Return value of numeric field E06CDRYEA as a BigDecimal.
  */
  public BigDecimal getBigDecimalE06CDRYEA()
  {
    return fieldE06CDRYEA.getBigDecimal();
  }

  /**
  * Set field E06CDRTIM using a String value.
  */
  public void setE06CDRTIM(String newvalue)
  {
    fieldE06CDRTIM.setString(newvalue);
  }

  /**
  * Get value of field E06CDRTIM as a String.
  */
  public String getE06CDRTIM()
  {
    return fieldE06CDRTIM.getString();
  }

  /**
  * Set numeric field E06CDRTIM using a BigDecimal value.
  */
  public void setE06CDRTIM(BigDecimal newvalue)
  {
    fieldE06CDRTIM.setBigDecimal(newvalue);
  }

  /**
  * Return value of numeric field E06CDRTIM as a BigDecimal.
  */
  public BigDecimal getBigDecimalE06CDRTIM()
  {
    return fieldE06CDRTIM.getBigDecimal();
  }

}
