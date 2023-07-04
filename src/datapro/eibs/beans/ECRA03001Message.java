package datapro.eibs.beans;

import datapro.eibs.sockets.*;
import java.io.*;
import java.math.*;

import java.util.*;

/**
* Class generated by AS/400 CRTCLASS command from ECRA03001 physical file definition.
* 
* File level identifier is 1041118153555.
* Record format level identifier is 573A7EC1AA44D.
*/

public class ECRA03001Message extends MessageRecord
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
                                     "E01CRMMSA",
                                     "E01CRMDSC",
                                     "E01CRMSTS",
                                     "E01CRMSAC",
                                     "E01CRMSTC",
                                     "D01STCDSC"
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
                                   "E01CRMMSA",
                                   "E01CRMDSC",
                                   "E01CRMSTS",
                                   "E01CRMSAC",
                                   "E01CRMSTC",
                                   "D01STCDSC"
                                 };
  final static String fid = "1041118153555";
  final static String rid = "573A7EC1AA44D";
  final static String fmtname = "ECRA03001";
  final int FIELDCOUNT = 15;
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
  private CharacterField fieldE01CRMMSA = null;
  private CharacterField fieldE01CRMDSC = null;
  private CharacterField fieldE01CRMSTS = null;
  private CharacterField fieldE01CRMSAC = null;
  private DecimalField fieldE01CRMSTC = null;
  private CharacterField fieldD01STCDSC = null;

  /**
  * Constructor for ECRA03001Message.
  */
  public ECRA03001Message()
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
    recordsize = 113;
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
    fields[9] = fieldE01CRMMSA =
    new CharacterField(message, HEADERSIZE + 42, 5, "E01CRMMSA");
    fields[10] = fieldE01CRMDSC =
    new CharacterField(message, HEADERSIZE + 47, 30, "E01CRMDSC");
    fields[11] = fieldE01CRMSTS =
    new CharacterField(message, HEADERSIZE + 77, 1, "E01CRMSTS");
    fields[12] = fieldE01CRMSAC =
    new CharacterField(message, HEADERSIZE + 78, 2, "E01CRMSAC");
    fields[13] = fieldE01CRMSTC =
    new DecimalField(message, HEADERSIZE + 80, 3, 0, "E01CRMSTC");
    fields[14] = fieldD01STCDSC =
    new CharacterField(message, HEADERSIZE + 83, 30, "D01STCDSC");

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
  * Set field E01CRMMSA using a String value.
  */
  public void setE01CRMMSA(String newvalue)
  {
    fieldE01CRMMSA.setString(newvalue);
  }

  /**
  * Get value of field E01CRMMSA as a String.
  */
  public String getE01CRMMSA()
  {
    return fieldE01CRMMSA.getString();
  }

  /**
  * Set field E01CRMDSC using a String value.
  */
  public void setE01CRMDSC(String newvalue)
  {
    fieldE01CRMDSC.setString(newvalue);
  }

  /**
  * Get value of field E01CRMDSC as a String.
  */
  public String getE01CRMDSC()
  {
    return fieldE01CRMDSC.getString();
  }

  /**
  * Set field E01CRMSTS using a String value.
  */
  public void setE01CRMSTS(String newvalue)
  {
    fieldE01CRMSTS.setString(newvalue);
  }

  /**
  * Get value of field E01CRMSTS as a String.
  */
  public String getE01CRMSTS()
  {
    return fieldE01CRMSTS.getString();
  }

  /**
  * Set field E01CRMSAC using a String value.
  */
  public void setE01CRMSAC(String newvalue)
  {
    fieldE01CRMSAC.setString(newvalue);
  }

  /**
  * Get value of field E01CRMSAC as a String.
  */
  public String getE01CRMSAC()
  {
    return fieldE01CRMSAC.getString();
  }

  /**
  * Set field E01CRMSTC using a String value.
  */
  public void setE01CRMSTC(String newvalue)
  {
    fieldE01CRMSTC.setString(newvalue);
  }

  /**
  * Get value of field E01CRMSTC as a String.
  */
  public String getE01CRMSTC()
  {
    return fieldE01CRMSTC.getString();
  }

  /**
  * Set numeric field E01CRMSTC using a BigDecimal value.
  */
  public void setE01CRMSTC(BigDecimal newvalue)
  {
    fieldE01CRMSTC.setBigDecimal(newvalue);
  }

  /**
  * Return value of numeric field E01CRMSTC as a BigDecimal.
  */
  public BigDecimal getBigDecimalE01CRMSTC()
  {
    return fieldE01CRMSTC.getBigDecimal();
  }

  /**
  * Set field D01STCDSC using a String value.
  */
  public void setD01STCDSC(String newvalue)
  {
    fieldD01STCDSC.setString(newvalue);
  }

  /**
  * Get value of field D01STCDSC as a String.
  */
  public String getD01STCDSC()
  {
    return fieldD01STCDSC.getString();
  }

}
