package datapro.eibs.beans;

import datapro.eibs.sockets.*;
import java.io.*;
import java.math.*;

import java.util.*;

/**
* Class generated by AS/400 CRTCLASS command from EACH30001 physical file definition.
* 
* File level identifier is 1080611171946.
* Record format level identifier is 404FB28C1B77B.
*/

public class EACH30001Message extends MessageRecord
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
                                     "E01ACACUN",
                                     "E01ACAACC",
                                     "E01ACAOCD",
                                     "E01ACAMAM",
                                     "E01ACAADY",
                                     "E01ACAADM",
                                     "E01ACAADD",
                                     "E01ACASTS",
                                     "E01CUNDSC",
                                     "E01OCDDSC",
                                     "E01ACT",
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
                                   "E01ACACUN",
                                   "E01ACAACC",
                                   "E01ACAOCD",
                                   "E01ACAMAM",
                                   "E01ACAADY",
                                   "E01ACAADM",
                                   "E01ACAADD",
                                   "E01ACASTS",
                                   "E01CUNDSC",
                                   "E01OCDDSC",
                                   "E01ACT",
                                   "E01NUMREC",
                                   "E01INDOPE"
                                 };
  final static String fid = "1080611171946";
  final static String rid = "404FB28C1B77B";
  final static String fmtname = "EACH30001";
  final int FIELDCOUNT = 22;
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
  private DecimalField fieldE01ACACUN = null;
  private DecimalField fieldE01ACAACC = null;
  private CharacterField fieldE01ACAOCD = null;
  private DecimalField fieldE01ACAMAM = null;
  private DecimalField fieldE01ACAADY = null;
  private DecimalField fieldE01ACAADM = null;
  private DecimalField fieldE01ACAADD = null;
  private CharacterField fieldE01ACASTS = null;
  private CharacterField fieldE01CUNDSC = null;
  private CharacterField fieldE01OCDDSC = null;
  private CharacterField fieldE01ACT = null;
  private DecimalField fieldE01NUMREC = null;
  private CharacterField fieldE01INDOPE = null;

  /**
  * Constructor for EACH30001Message.
  */
  public EACH30001Message()
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
    recordsize = 182;
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
    fields[9] = fieldE01ACACUN =
    new DecimalField(message, HEADERSIZE + 42, 10, 0, "E01ACACUN");
    fields[10] = fieldE01ACAACC =
    new DecimalField(message, HEADERSIZE + 52, 13, 0, "E01ACAACC");
    fields[11] = fieldE01ACAOCD =
    new CharacterField(message, HEADERSIZE + 65, 10, "E01ACAOCD");
    fields[12] = fieldE01ACAMAM =
    new DecimalField(message, HEADERSIZE + 75, 17, 2, "E01ACAMAM");
    fields[13] = fieldE01ACAADY =
    new DecimalField(message, HEADERSIZE + 92, 3, 0, "E01ACAADY");
    fields[14] = fieldE01ACAADM =
    new DecimalField(message, HEADERSIZE + 95, 3, 0, "E01ACAADM");
    fields[15] = fieldE01ACAADD =
    new DecimalField(message, HEADERSIZE + 98, 3, 0, "E01ACAADD");
    fields[16] = fieldE01ACASTS =
    new CharacterField(message, HEADERSIZE + 101, 1, "E01ACASTS");
    fields[17] = fieldE01CUNDSC =
    new CharacterField(message, HEADERSIZE + 102, 35, "E01CUNDSC");
    fields[18] = fieldE01OCDDSC =
    new CharacterField(message, HEADERSIZE + 137, 35, "E01OCDDSC");
    fields[19] = fieldE01ACT =
    new CharacterField(message, HEADERSIZE + 172, 1, "E01ACT");
    fields[20] = fieldE01NUMREC =
    new DecimalField(message, HEADERSIZE + 173, 8, 0, "E01NUMREC");
    fields[21] = fieldE01INDOPE =
    new CharacterField(message, HEADERSIZE + 181, 1, "E01INDOPE");

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
  * Set field E01ACACUN using a String value.
  */
  public void setE01ACACUN(String newvalue)
  {
    fieldE01ACACUN.setString(newvalue);
  }

  /**
  * Get value of field E01ACACUN as a String.
  */
  public String getE01ACACUN()
  {
    return fieldE01ACACUN.getString();
  }

  /**
  * Set numeric field E01ACACUN using a BigDecimal value.
  */
  public void setE01ACACUN(BigDecimal newvalue)
  {
    fieldE01ACACUN.setBigDecimal(newvalue);
  }

  /**
  * Return value of numeric field E01ACACUN as a BigDecimal.
  */
  public BigDecimal getBigDecimalE01ACACUN()
  {
    return fieldE01ACACUN.getBigDecimal();
  }

  /**
  * Set field E01ACAACC using a String value.
  */
  public void setE01ACAACC(String newvalue)
  {
    fieldE01ACAACC.setString(newvalue);
  }

  /**
  * Get value of field E01ACAACC as a String.
  */
  public String getE01ACAACC()
  {
    return fieldE01ACAACC.getString();
  }

  /**
  * Set numeric field E01ACAACC using a BigDecimal value.
  */
  public void setE01ACAACC(BigDecimal newvalue)
  {
    fieldE01ACAACC.setBigDecimal(newvalue);
  }

  /**
  * Return value of numeric field E01ACAACC as a BigDecimal.
  */
  public BigDecimal getBigDecimalE01ACAACC()
  {
    return fieldE01ACAACC.getBigDecimal();
  }

  /**
  * Set field E01ACAOCD using a String value.
  */
  public void setE01ACAOCD(String newvalue)
  {
    fieldE01ACAOCD.setString(newvalue);
  }

  /**
  * Get value of field E01ACAOCD as a String.
  */
  public String getE01ACAOCD()
  {
    return fieldE01ACAOCD.getString();
  }

  /**
  * Set field E01ACAMAM using a String value.
  */
  public void setE01ACAMAM(String newvalue)
  {
    fieldE01ACAMAM.setString(newvalue);
  }

  /**
  * Get value of field E01ACAMAM as a String.
  */
  public String getE01ACAMAM()
  {
    return fieldE01ACAMAM.getString();
  }

  /**
  * Set numeric field E01ACAMAM using a BigDecimal value.
  */
  public void setE01ACAMAM(BigDecimal newvalue)
  {
    fieldE01ACAMAM.setBigDecimal(newvalue);
  }

  /**
  * Return value of numeric field E01ACAMAM as a BigDecimal.
  */
  public BigDecimal getBigDecimalE01ACAMAM()
  {
    return fieldE01ACAMAM.getBigDecimal();
  }

  /**
  * Set field E01ACAADY using a String value.
  */
  public void setE01ACAADY(String newvalue)
  {
    fieldE01ACAADY.setString(newvalue);
  }

  /**
  * Get value of field E01ACAADY as a String.
  */
  public String getE01ACAADY()
  {
    return fieldE01ACAADY.getString();
  }

  /**
  * Set numeric field E01ACAADY using a BigDecimal value.
  */
  public void setE01ACAADY(BigDecimal newvalue)
  {
    fieldE01ACAADY.setBigDecimal(newvalue);
  }

  /**
  * Return value of numeric field E01ACAADY as a BigDecimal.
  */
  public BigDecimal getBigDecimalE01ACAADY()
  {
    return fieldE01ACAADY.getBigDecimal();
  }

  /**
  * Set field E01ACAADM using a String value.
  */
  public void setE01ACAADM(String newvalue)
  {
    fieldE01ACAADM.setString(newvalue);
  }

  /**
  * Get value of field E01ACAADM as a String.
  */
  public String getE01ACAADM()
  {
    return fieldE01ACAADM.getString();
  }

  /**
  * Set numeric field E01ACAADM using a BigDecimal value.
  */
  public void setE01ACAADM(BigDecimal newvalue)
  {
    fieldE01ACAADM.setBigDecimal(newvalue);
  }

  /**
  * Return value of numeric field E01ACAADM as a BigDecimal.
  */
  public BigDecimal getBigDecimalE01ACAADM()
  {
    return fieldE01ACAADM.getBigDecimal();
  }

  /**
  * Set field E01ACAADD using a String value.
  */
  public void setE01ACAADD(String newvalue)
  {
    fieldE01ACAADD.setString(newvalue);
  }

  /**
  * Get value of field E01ACAADD as a String.
  */
  public String getE01ACAADD()
  {
    return fieldE01ACAADD.getString();
  }

  /**
  * Set numeric field E01ACAADD using a BigDecimal value.
  */
  public void setE01ACAADD(BigDecimal newvalue)
  {
    fieldE01ACAADD.setBigDecimal(newvalue);
  }

  /**
  * Return value of numeric field E01ACAADD as a BigDecimal.
  */
  public BigDecimal getBigDecimalE01ACAADD()
  {
    return fieldE01ACAADD.getBigDecimal();
  }

  /**
  * Set field E01ACASTS using a String value.
  */
  public void setE01ACASTS(String newvalue)
  {
    fieldE01ACASTS.setString(newvalue);
  }

  /**
  * Get value of field E01ACASTS as a String.
  */
  public String getE01ACASTS()
  {
    return fieldE01ACASTS.getString();
  }

  /**
  * Set field E01CUNDSC using a String value.
  */
  public void setE01CUNDSC(String newvalue)
  {
    fieldE01CUNDSC.setString(newvalue);
  }

  /**
  * Get value of field E01CUNDSC as a String.
  */
  public String getE01CUNDSC()
  {
    return fieldE01CUNDSC.getString();
  }

  /**
  * Set field E01OCDDSC using a String value.
  */
  public void setE01OCDDSC(String newvalue)
  {
    fieldE01OCDDSC.setString(newvalue);
  }

  /**
  * Get value of field E01OCDDSC as a String.
  */
  public String getE01OCDDSC()
  {
    return fieldE01OCDDSC.getString();
  }

  /**
  * Set field E01ACT using a String value.
  */
  public void setE01ACT(String newvalue)
  {
    fieldE01ACT.setString(newvalue);
  }

  /**
  * Get value of field E01ACT as a String.
  */
  public String getE01ACT()
  {
    return fieldE01ACT.getString();
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
