package datapro.eibs.beans;

import datapro.eibs.sockets.*;
import java.io.*;
import java.math.*;

import java.util.*;

/**
* Class generated by AS/400 CRTCLASS command from ECIF09001 physical file definition.
* 
* File level identifier is 1050328133459.
* Record format level identifier is 474093AC573F4.
*/

public class ECIF09001Message extends MessageRecord
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
                                     "E01INQBNK",
                                     "E01INQRDY",
                                     "E01INQRDM",
                                     "E01INQOFC"
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
                                   "E01INQBNK",
                                   "E01INQRDY",
                                   "E01INQRDM",
                                   "E01INQOFC"
                                 };
  final static String fid = "1050328133459";
  final static String rid = "474093AC573F4";
  final static String fmtname = "ECIF09001";
  final int FIELDCOUNT = 13;
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
  private CharacterField fieldE01INQBNK = null;
  private DecimalField fieldE01INQRDY = null;
  private DecimalField fieldE01INQRDM = null;
  private CharacterField fieldE01INQOFC = null;

  /**
  * Constructor for ECIF09001Message.
  */
  public ECIF09001Message()
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
    recordsize = 56;
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
    fields[9] = fieldE01INQBNK =
    new CharacterField(message, HEADERSIZE + 42, 2, "E01INQBNK");
    fields[10] = fieldE01INQRDY =
    new DecimalField(message, HEADERSIZE + 44, 5, 0, "E01INQRDY");
    fields[11] = fieldE01INQRDM =
    new DecimalField(message, HEADERSIZE + 49, 3, 0, "E01INQRDM");
    fields[12] = fieldE01INQOFC =
    new CharacterField(message, HEADERSIZE + 52, 4, "E01INQOFC");

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
  * Set field E01INQBNK using a String value.
  */
  public void setE01INQBNK(String newvalue)
  {
    fieldE01INQBNK.setString(newvalue);
  }

  /**
  * Get value of field E01INQBNK as a String.
  */
  public String getE01INQBNK()
  {
    return fieldE01INQBNK.getString();
  }

  /**
  * Set field E01INQRDY using a String value.
  */
  public void setE01INQRDY(String newvalue)
  {
    fieldE01INQRDY.setString(newvalue);
  }

  /**
  * Get value of field E01INQRDY as a String.
  */
  public String getE01INQRDY()
  {
    return fieldE01INQRDY.getString();
  }

  /**
  * Set numeric field E01INQRDY using a BigDecimal value.
  */
  public void setE01INQRDY(BigDecimal newvalue)
  {
    fieldE01INQRDY.setBigDecimal(newvalue);
  }

  /**
  * Return value of numeric field E01INQRDY as a BigDecimal.
  */
  public BigDecimal getBigDecimalE01INQRDY()
  {
    return fieldE01INQRDY.getBigDecimal();
  }

  /**
  * Set field E01INQRDM using a String value.
  */
  public void setE01INQRDM(String newvalue)
  {
    fieldE01INQRDM.setString(newvalue);
  }

  /**
  * Get value of field E01INQRDM as a String.
  */
  public String getE01INQRDM()
  {
    return fieldE01INQRDM.getString();
  }

  /**
  * Set numeric field E01INQRDM using a BigDecimal value.
  */
  public void setE01INQRDM(BigDecimal newvalue)
  {
    fieldE01INQRDM.setBigDecimal(newvalue);
  }

  /**
  * Return value of numeric field E01INQRDM as a BigDecimal.
  */
  public BigDecimal getBigDecimalE01INQRDM()
  {
    return fieldE01INQRDM.getBigDecimal();
  }

  /**
  * Set field E01INQOFC using a String value.
  */
  public void setE01INQOFC(String newvalue)
  {
    fieldE01INQOFC.setString(newvalue);
  }

  /**
  * Get value of field E01INQOFC as a String.
  */
  public String getE01INQOFC()
  {
    return fieldE01INQOFC.getString();
  }

}
