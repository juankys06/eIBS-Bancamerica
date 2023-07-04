package datapro.eibs.beans;

import datapro.eibs.sockets.*;
import java.io.*;
import java.math.*;

import java.util.*;

/**
* Class generated by AS/400 CRTCLASS command from EDL030501 physical file definition.
* 
* File level identifier is 1030121191907.
* Record format level identifier is 31183A3EAE7A4.
*/

public class EDL030501Message extends MessageRecord
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
                                     "E01DLIACC",
                                     "E01DLICDE",
                                     "E01DLITYP",
                                     "E01DEDNME",
                                     "E01DLIREF",
                                     "E01DLIPAM",
                                     "E01DLIDPM",
                                     "E01DLIFAC",
                                     "E01ENDFLD"
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
                                   "E01DLIACC",
                                   "E01DLICDE",
                                   "E01DLITYP",
                                   "E01DEDNME",
                                   "E01DLIREF",
                                   "E01DLIPAM",
                                   "E01DLIDPM",
                                   "E01DLIFAC",
                                   "E01ENDFLD"
                                 };
  final static String fid = "1030121191907";
  final static String rid = "31183A3EAE7A4";
  final static String fmtname = "EDL030501";
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
  private DecimalField fieldE01DLIACC = null;
  private CharacterField fieldE01DLICDE = null;
  private CharacterField fieldE01DLITYP = null;
  private CharacterField fieldE01DEDNME = null;
  private CharacterField fieldE01DLIREF = null;
  private DecimalField fieldE01DLIPAM = null;
  private DecimalField fieldE01DLIDPM = null;
  private CharacterField fieldE01DLIFAC = null;
  private CharacterField fieldE01ENDFLD = null;

  /**
  * Constructor for EDL030501Message.
  */
  public EDL030501Message()
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
    recordsize = 151;
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
    fields[9] = fieldE01DLIACC =
    new DecimalField(message, HEADERSIZE + 42, 13, 0, "E01DLIACC");
    fields[10] = fieldE01DLICDE =
    new CharacterField(message, HEADERSIZE + 55, 4, "E01DLICDE");
    fields[11] = fieldE01DLITYP =
    new CharacterField(message, HEADERSIZE + 59, 1, "E01DLITYP");
    fields[12] = fieldE01DEDNME =
    new CharacterField(message, HEADERSIZE + 60, 35, "E01DEDNME");
    fields[13] = fieldE01DLIREF =
    new CharacterField(message, HEADERSIZE + 95, 20, "E01DLIREF");
    fields[14] = fieldE01DLIPAM =
    new DecimalField(message, HEADERSIZE + 115, 17, 2, "E01DLIPAM");
    fields[15] = fieldE01DLIDPM =
    new DecimalField(message, HEADERSIZE + 132, 17, 2, "E01DLIDPM");
    fields[16] = fieldE01DLIFAC =
    new CharacterField(message, HEADERSIZE + 149, 1, "E01DLIFAC");
    fields[17] = fieldE01ENDFLD =
    new CharacterField(message, HEADERSIZE + 150, 1, "E01ENDFLD");

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
  * Set field E01DLIACC using a String value.
  */
  public void setE01DLIACC(String newvalue)
  {
    fieldE01DLIACC.setString(newvalue);
  }

  /**
  * Get value of field E01DLIACC as a String.
  */
  public String getE01DLIACC()
  {
    return fieldE01DLIACC.getString();
  }

  /**
  * Set numeric field E01DLIACC using a BigDecimal value.
  */
  public void setE01DLIACC(BigDecimal newvalue)
  {
    fieldE01DLIACC.setBigDecimal(newvalue);
  }

  /**
  * Return value of numeric field E01DLIACC as a BigDecimal.
  */
  public BigDecimal getBigDecimalE01DLIACC()
  {
    return fieldE01DLIACC.getBigDecimal();
  }

  /**
  * Set field E01DLICDE using a String value.
  */
  public void setE01DLICDE(String newvalue)
  {
    fieldE01DLICDE.setString(newvalue);
  }

  /**
  * Get value of field E01DLICDE as a String.
  */
  public String getE01DLICDE()
  {
    return fieldE01DLICDE.getString();
  }

  /**
  * Set field E01DLITYP using a String value.
  */
  public void setE01DLITYP(String newvalue)
  {
    fieldE01DLITYP.setString(newvalue);
  }

  /**
  * Get value of field E01DLITYP as a String.
  */
  public String getE01DLITYP()
  {
    return fieldE01DLITYP.getString();
  }

  /**
  * Set field E01DEDNME using a String value.
  */
  public void setE01DEDNME(String newvalue)
  {
    fieldE01DEDNME.setString(newvalue);
  }

  /**
  * Get value of field E01DEDNME as a String.
  */
  public String getE01DEDNME()
  {
    return fieldE01DEDNME.getString();
  }

  /**
  * Set field E01DLIREF using a String value.
  */
  public void setE01DLIREF(String newvalue)
  {
    fieldE01DLIREF.setString(newvalue);
  }

  /**
  * Get value of field E01DLIREF as a String.
  */
  public String getE01DLIREF()
  {
    return fieldE01DLIREF.getString();
  }

  /**
  * Set field E01DLIPAM using a String value.
  */
  public void setE01DLIPAM(String newvalue)
  {
    fieldE01DLIPAM.setString(newvalue);
  }

  /**
  * Get value of field E01DLIPAM as a String.
  */
  public String getE01DLIPAM()
  {
    return fieldE01DLIPAM.getString();
  }

  /**
  * Set numeric field E01DLIPAM using a BigDecimal value.
  */
  public void setE01DLIPAM(BigDecimal newvalue)
  {
    fieldE01DLIPAM.setBigDecimal(newvalue);
  }

  /**
  * Return value of numeric field E01DLIPAM as a BigDecimal.
  */
  public BigDecimal getBigDecimalE01DLIPAM()
  {
    return fieldE01DLIPAM.getBigDecimal();
  }

  /**
  * Set field E01DLIDPM using a String value.
  */
  public void setE01DLIDPM(String newvalue)
  {
    fieldE01DLIDPM.setString(newvalue);
  }

  /**
  * Get value of field E01DLIDPM as a String.
  */
  public String getE01DLIDPM()
  {
    return fieldE01DLIDPM.getString();
  }

  /**
  * Set numeric field E01DLIDPM using a BigDecimal value.
  */
  public void setE01DLIDPM(BigDecimal newvalue)
  {
    fieldE01DLIDPM.setBigDecimal(newvalue);
  }

  /**
  * Return value of numeric field E01DLIDPM as a BigDecimal.
  */
  public BigDecimal getBigDecimalE01DLIDPM()
  {
    return fieldE01DLIDPM.getBigDecimal();
  }

  /**
  * Set field E01DLIFAC using a String value.
  */
  public void setE01DLIFAC(String newvalue)
  {
    fieldE01DLIFAC.setString(newvalue);
  }

  /**
  * Get value of field E01DLIFAC as a String.
  */
  public String getE01DLIFAC()
  {
    return fieldE01DLIFAC.getString();
  }

  /**
  * Set field E01ENDFLD using a String value.
  */
  public void setE01ENDFLD(String newvalue)
  {
    fieldE01ENDFLD.setString(newvalue);
  }

  /**
  * Get value of field E01ENDFLD as a String.
  */
  public String getE01ENDFLD()
  {
    return fieldE01ENDFLD.getString();
  }

}
