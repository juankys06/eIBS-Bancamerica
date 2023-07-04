package datapro.eibs.beans;

import datapro.eibs.sockets.*;
import java.io.*;
import java.math.*;

import java.util.*;

/**
* Class generated by AS/400 CRTCLASS command from EDL015601 physical file definition.
* 
* File level identifier is 1040614114040.
* Record format level identifier is 4198243858C21.
*/

public class EDL015601Message extends MessageRecord
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
                                     "E01DLEBNK",
                                     "E01DLEPRO",
                                     "E01DLEGLD",
                                     "E01DLEGLC",
                                     "E01DLECAM",
                                     "E01GLDDSC",
                                     "E01GLCDSC",
                                     "E01PRONME",
                                     "E01DLEOPE"
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
                                   "E01DLEBNK",
                                   "E01DLEPRO",
                                   "E01DLEGLD",
                                   "E01DLEGLC",
                                   "E01DLECAM",
                                   "E01GLDDSC",
                                   "E01GLCDSC",
                                   "E01PRONME",
                                   "E01DLEOPE"
                                 };
  final static String fid = "1040614114040";
  final static String rid = "4198243858C21";
  final static String fmtname = "EDL015601";
  final int FIELDCOUNT = 18;
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
  private CharacterField fieldE01DLEBNK = null;
  private CharacterField fieldE01DLEPRO = null;
  private DecimalField fieldE01DLEGLD = null;
  private DecimalField fieldE01DLEGLC = null;
  private DecimalField fieldE01DLECAM = null;
  private CharacterField fieldE01GLDDSC = null;
  private CharacterField fieldE01GLCDSC = null;
  private CharacterField fieldE01PRONME = null;
  private CharacterField fieldE01DLEOPE = null;

  /**
  * Constructor for EDL015601Message.
  */
  public EDL015601Message()
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
    recordsize = 228;
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
    fields[9] = fieldE01DLEBNK =
    new CharacterField(message, HEADERSIZE + 42, 2, "E01DLEBNK");
    fields[10] = fieldE01DLEPRO =
    new CharacterField(message, HEADERSIZE + 44, 4, "E01DLEPRO");
    fields[11] = fieldE01DLEGLD =
    new DecimalField(message, HEADERSIZE + 48, 17, 0, "E01DLEGLD");
    fields[12] = fieldE01DLEGLC =
    new DecimalField(message, HEADERSIZE + 65, 17, 0, "E01DLEGLC");
    fields[13] = fieldE01DLECAM =
    new DecimalField(message, HEADERSIZE + 82, 15, 2, "E01DLECAM");
    fields[14] = fieldE01GLDDSC =
    new CharacterField(message, HEADERSIZE + 97, 35, "E01GLDDSC");
    fields[15] = fieldE01GLCDSC =
    new CharacterField(message, HEADERSIZE + 132, 35, "E01GLCDSC");
    fields[16] = fieldE01PRONME =
    new CharacterField(message, HEADERSIZE + 167, 60, "E01PRONME");
    fields[17] = fieldE01DLEOPE =
    new CharacterField(message, HEADERSIZE + 227, 1, "E01DLEOPE");

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
  * Set field E01DLEBNK using a String value.
  */
  public void setE01DLEBNK(String newvalue)
  {
    fieldE01DLEBNK.setString(newvalue);
  }

  /**
  * Get value of field E01DLEBNK as a String.
  */
  public String getE01DLEBNK()
  {
    return fieldE01DLEBNK.getString();
  }

  /**
  * Set field E01DLEPRO using a String value.
  */
  public void setE01DLEPRO(String newvalue)
  {
    fieldE01DLEPRO.setString(newvalue);
  }

  /**
  * Get value of field E01DLEPRO as a String.
  */
  public String getE01DLEPRO()
  {
    return fieldE01DLEPRO.getString();
  }

  /**
  * Set field E01DLEGLD using a String value.
  */
  public void setE01DLEGLD(String newvalue)
  {
    fieldE01DLEGLD.setString(newvalue);
  }

  /**
  * Get value of field E01DLEGLD as a String.
  */
  public String getE01DLEGLD()
  {
    return fieldE01DLEGLD.getString();
  }

  /**
  * Set numeric field E01DLEGLD using a BigDecimal value.
  */
  public void setE01DLEGLD(BigDecimal newvalue)
  {
    fieldE01DLEGLD.setBigDecimal(newvalue);
  }

  /**
  * Return value of numeric field E01DLEGLD as a BigDecimal.
  */
  public BigDecimal getBigDecimalE01DLEGLD()
  {
    return fieldE01DLEGLD.getBigDecimal();
  }

  /**
  * Set field E01DLEGLC using a String value.
  */
  public void setE01DLEGLC(String newvalue)
  {
    fieldE01DLEGLC.setString(newvalue);
  }

  /**
  * Get value of field E01DLEGLC as a String.
  */
  public String getE01DLEGLC()
  {
    return fieldE01DLEGLC.getString();
  }

  /**
  * Set numeric field E01DLEGLC using a BigDecimal value.
  */
  public void setE01DLEGLC(BigDecimal newvalue)
  {
    fieldE01DLEGLC.setBigDecimal(newvalue);
  }

  /**
  * Return value of numeric field E01DLEGLC as a BigDecimal.
  */
  public BigDecimal getBigDecimalE01DLEGLC()
  {
    return fieldE01DLEGLC.getBigDecimal();
  }

  /**
  * Set field E01DLECAM using a String value.
  */
  public void setE01DLECAM(String newvalue)
  {
    fieldE01DLECAM.setString(newvalue);
  }

  /**
  * Get value of field E01DLECAM as a String.
  */
  public String getE01DLECAM()
  {
    return fieldE01DLECAM.getString();
  }

  /**
  * Set numeric field E01DLECAM using a BigDecimal value.
  */
  public void setE01DLECAM(BigDecimal newvalue)
  {
    fieldE01DLECAM.setBigDecimal(newvalue);
  }

  /**
  * Return value of numeric field E01DLECAM as a BigDecimal.
  */
  public BigDecimal getBigDecimalE01DLECAM()
  {
    return fieldE01DLECAM.getBigDecimal();
  }

  /**
  * Set field E01GLDDSC using a String value.
  */
  public void setE01GLDDSC(String newvalue)
  {
    fieldE01GLDDSC.setString(newvalue);
  }

  /**
  * Get value of field E01GLDDSC as a String.
  */
  public String getE01GLDDSC()
  {
    return fieldE01GLDDSC.getString();
  }

  /**
  * Set field E01GLCDSC using a String value.
  */
  public void setE01GLCDSC(String newvalue)
  {
    fieldE01GLCDSC.setString(newvalue);
  }

  /**
  * Get value of field E01GLCDSC as a String.
  */
  public String getE01GLCDSC()
  {
    return fieldE01GLCDSC.getString();
  }

  /**
  * Set field E01PRONME using a String value.
  */
  public void setE01PRONME(String newvalue)
  {
    fieldE01PRONME.setString(newvalue);
  }

  /**
  * Get value of field E01PRONME as a String.
  */
  public String getE01PRONME()
  {
    return fieldE01PRONME.getString();
  }

  /**
  * Set field E01DLEOPE using a String value.
  */
  public void setE01DLEOPE(String newvalue)
  {
    fieldE01DLEOPE.setString(newvalue);
  }

  /**
  * Get value of field E01DLEOPE as a String.
  */
  public String getE01DLEOPE()
  {
    return fieldE01DLEOPE.getString();
  }

}