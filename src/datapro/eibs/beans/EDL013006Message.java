package datapro.eibs.beans;

import datapro.eibs.sockets.*;
import java.io.*;
import java.math.*;

import java.util.*;

/**
* Class generated by AS/400 CRTCLASS command from EDL013006 physical file definition.
* 
* File level identifier is 1030121191855.
* Record format level identifier is 5BCBD956E5800.
*/

public class EDL013006Message extends MessageRecord
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
                                     "E06DEAACC",
                                     "E06DEACUN",
                                     "E06CUSNA1",
                                     "E06DEAACD",
                                     "E06DEAPRO",
                                     "E06OLDRTE",
                                     "E06NEWRTE",
                                     "E06TRNVD1",
                                     "E06TRNVD2",
                                     "E06TRNVD3",
                                     "E06DEANR1",
                                     "E06DEANR2"
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
                                   "E06DEAACC",
                                   "E06DEACUN",
                                   "E06CUSNA1",
                                   "E06DEAACD",
                                   "E06DEAPRO",
                                   "E06OLDRTE",
                                   "E06NEWRTE",
                                   "E06TRNVD1",
                                   "E06TRNVD2",
                                   "E06TRNVD3",
                                   "E06DEANR1",
                                   "E06DEANR2"
                                 };
  final static String fid = "1030121191855";
  final static String rid = "5BCBD956E5800";
  final static String fmtname = "EDL013006";
  final int FIELDCOUNT = 21;
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
  private DecimalField fieldE06DEAACC = null;
  private DecimalField fieldE06DEACUN = null;
  private CharacterField fieldE06CUSNA1 = null;
  private CharacterField fieldE06DEAACD = null;
  private CharacterField fieldE06DEAPRO = null;
  private DecimalField fieldE06OLDRTE = null;
  private DecimalField fieldE06NEWRTE = null;
  private DecimalField fieldE06TRNVD1 = null;
  private DecimalField fieldE06TRNVD2 = null;
  private DecimalField fieldE06TRNVD3 = null;
  private CharacterField fieldE06DEANR1 = null;
  private CharacterField fieldE06DEANR2 = null;

  /**
  * Constructor for EDL013006Message.
  */
  public EDL013006Message()
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
    recordsize = 207;
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
    fields[9] = fieldE06DEAACC =
    new DecimalField(message, HEADERSIZE + 42, 13, 0, "E06DEAACC");
    fields[10] = fieldE06DEACUN =
    new DecimalField(message, HEADERSIZE + 55, 10, 0, "E06DEACUN");
    fields[11] = fieldE06CUSNA1 =
    new CharacterField(message, HEADERSIZE + 65, 45, "E06CUSNA1");
    fields[12] = fieldE06DEAACD =
    new CharacterField(message, HEADERSIZE + 110, 2, "E06DEAACD");
    fields[13] = fieldE06DEAPRO =
    new CharacterField(message, HEADERSIZE + 112, 4, "E06DEAPRO");
    fields[14] = fieldE06OLDRTE =
    new DecimalField(message, HEADERSIZE + 116, 11, 6, "E06OLDRTE");
    fields[15] = fieldE06NEWRTE =
    new DecimalField(message, HEADERSIZE + 127, 11, 6, "E06NEWRTE");
    fields[16] = fieldE06TRNVD1 =
    new DecimalField(message, HEADERSIZE + 138, 3, 0, "E06TRNVD1");
    fields[17] = fieldE06TRNVD2 =
    new DecimalField(message, HEADERSIZE + 141, 3, 0, "E06TRNVD2");
    fields[18] = fieldE06TRNVD3 =
    new DecimalField(message, HEADERSIZE + 144, 3, 0, "E06TRNVD3");
    fields[19] = fieldE06DEANR1 =
    new CharacterField(message, HEADERSIZE + 147, 30, "E06DEANR1");
    fields[20] = fieldE06DEANR2 =
    new CharacterField(message, HEADERSIZE + 177, 30, "E06DEANR2");

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
  * Set field E06DEAACC using a String value.
  */
  public void setE06DEAACC(String newvalue)
  {
    fieldE06DEAACC.setString(newvalue);
  }

  /**
  * Get value of field E06DEAACC as a String.
  */
  public String getE06DEAACC()
  {
    return fieldE06DEAACC.getString();
  }

  /**
  * Set numeric field E06DEAACC using a BigDecimal value.
  */
  public void setE06DEAACC(BigDecimal newvalue)
  {
    fieldE06DEAACC.setBigDecimal(newvalue);
  }

  /**
  * Return value of numeric field E06DEAACC as a BigDecimal.
  */
  public BigDecimal getBigDecimalE06DEAACC()
  {
    return fieldE06DEAACC.getBigDecimal();
  }

  /**
  * Set field E06DEACUN using a String value.
  */
  public void setE06DEACUN(String newvalue)
  {
    fieldE06DEACUN.setString(newvalue);
  }

  /**
  * Get value of field E06DEACUN as a String.
  */
  public String getE06DEACUN()
  {
    return fieldE06DEACUN.getString();
  }

  /**
  * Set numeric field E06DEACUN using a BigDecimal value.
  */
  public void setE06DEACUN(BigDecimal newvalue)
  {
    fieldE06DEACUN.setBigDecimal(newvalue);
  }

  /**
  * Return value of numeric field E06DEACUN as a BigDecimal.
  */
  public BigDecimal getBigDecimalE06DEACUN()
  {
    return fieldE06DEACUN.getBigDecimal();
  }

  /**
  * Set field E06CUSNA1 using a String value.
  */
  public void setE06CUSNA1(String newvalue)
  {
    fieldE06CUSNA1.setString(newvalue);
  }

  /**
  * Get value of field E06CUSNA1 as a String.
  */
  public String getE06CUSNA1()
  {
    return fieldE06CUSNA1.getString();
  }

  /**
  * Set field E06DEAACD using a String value.
  */
  public void setE06DEAACD(String newvalue)
  {
    fieldE06DEAACD.setString(newvalue);
  }

  /**
  * Get value of field E06DEAACD as a String.
  */
  public String getE06DEAACD()
  {
    return fieldE06DEAACD.getString();
  }

  /**
  * Set field E06DEAPRO using a String value.
  */
  public void setE06DEAPRO(String newvalue)
  {
    fieldE06DEAPRO.setString(newvalue);
  }

  /**
  * Get value of field E06DEAPRO as a String.
  */
  public String getE06DEAPRO()
  {
    return fieldE06DEAPRO.getString();
  }

  /**
  * Set field E06OLDRTE using a String value.
  */
  public void setE06OLDRTE(String newvalue)
  {
    fieldE06OLDRTE.setString(newvalue);
  }

  /**
  * Get value of field E06OLDRTE as a String.
  */
  public String getE06OLDRTE()
  {
    return fieldE06OLDRTE.getString();
  }

  /**
  * Set numeric field E06OLDRTE using a BigDecimal value.
  */
  public void setE06OLDRTE(BigDecimal newvalue)
  {
    fieldE06OLDRTE.setBigDecimal(newvalue);
  }

  /**
  * Return value of numeric field E06OLDRTE as a BigDecimal.
  */
  public BigDecimal getBigDecimalE06OLDRTE()
  {
    return fieldE06OLDRTE.getBigDecimal();
  }

  /**
  * Set field E06NEWRTE using a String value.
  */
  public void setE06NEWRTE(String newvalue)
  {
    fieldE06NEWRTE.setString(newvalue);
  }

  /**
  * Get value of field E06NEWRTE as a String.
  */
  public String getE06NEWRTE()
  {
    return fieldE06NEWRTE.getString();
  }

  /**
  * Set numeric field E06NEWRTE using a BigDecimal value.
  */
  public void setE06NEWRTE(BigDecimal newvalue)
  {
    fieldE06NEWRTE.setBigDecimal(newvalue);
  }

  /**
  * Return value of numeric field E06NEWRTE as a BigDecimal.
  */
  public BigDecimal getBigDecimalE06NEWRTE()
  {
    return fieldE06NEWRTE.getBigDecimal();
  }

  /**
  * Set field E06TRNVD1 using a String value.
  */
  public void setE06TRNVD1(String newvalue)
  {
    fieldE06TRNVD1.setString(newvalue);
  }

  /**
  * Get value of field E06TRNVD1 as a String.
  */
  public String getE06TRNVD1()
  {
    return fieldE06TRNVD1.getString();
  }

  /**
  * Set numeric field E06TRNVD1 using a BigDecimal value.
  */
  public void setE06TRNVD1(BigDecimal newvalue)
  {
    fieldE06TRNVD1.setBigDecimal(newvalue);
  }

  /**
  * Return value of numeric field E06TRNVD1 as a BigDecimal.
  */
  public BigDecimal getBigDecimalE06TRNVD1()
  {
    return fieldE06TRNVD1.getBigDecimal();
  }

  /**
  * Set field E06TRNVD2 using a String value.
  */
  public void setE06TRNVD2(String newvalue)
  {
    fieldE06TRNVD2.setString(newvalue);
  }

  /**
  * Get value of field E06TRNVD2 as a String.
  */
  public String getE06TRNVD2()
  {
    return fieldE06TRNVD2.getString();
  }

  /**
  * Set numeric field E06TRNVD2 using a BigDecimal value.
  */
  public void setE06TRNVD2(BigDecimal newvalue)
  {
    fieldE06TRNVD2.setBigDecimal(newvalue);
  }

  /**
  * Return value of numeric field E06TRNVD2 as a BigDecimal.
  */
  public BigDecimal getBigDecimalE06TRNVD2()
  {
    return fieldE06TRNVD2.getBigDecimal();
  }

  /**
  * Set field E06TRNVD3 using a String value.
  */
  public void setE06TRNVD3(String newvalue)
  {
    fieldE06TRNVD3.setString(newvalue);
  }

  /**
  * Get value of field E06TRNVD3 as a String.
  */
  public String getE06TRNVD3()
  {
    return fieldE06TRNVD3.getString();
  }

  /**
  * Set numeric field E06TRNVD3 using a BigDecimal value.
  */
  public void setE06TRNVD3(BigDecimal newvalue)
  {
    fieldE06TRNVD3.setBigDecimal(newvalue);
  }

  /**
  * Return value of numeric field E06TRNVD3 as a BigDecimal.
  */
  public BigDecimal getBigDecimalE06TRNVD3()
  {
    return fieldE06TRNVD3.getBigDecimal();
  }

  /**
  * Set field E06DEANR1 using a String value.
  */
  public void setE06DEANR1(String newvalue)
  {
    fieldE06DEANR1.setString(newvalue);
  }

  /**
  * Get value of field E06DEANR1 as a String.
  */
  public String getE06DEANR1()
  {
    return fieldE06DEANR1.getString();
  }

  /**
  * Set field E06DEANR2 using a String value.
  */
  public void setE06DEANR2(String newvalue)
  {
    fieldE06DEANR2.setString(newvalue);
  }

  /**
  * Get value of field E06DEANR2 as a String.
  */
  public String getE06DEANR2()
  {
    return fieldE06DEANR2.getString();
  }

}
