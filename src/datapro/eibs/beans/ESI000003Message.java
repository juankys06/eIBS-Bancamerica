package datapro.eibs.beans;

import datapro.eibs.sockets.*;
import java.io.*;
import java.math.*;

import java.util.*;

/**
* Class generated by AS/400 CRTCLASS command from ESI000003 physical file definition.
* 
* File level identifier is 1051020145138.
* Record format level identifier is 47CD47BBFBDCB.
*/

public class ESI000003Message extends MessageRecord
{
  final static String fldnames[] = {
                                     "H03USERID",
                                     "H03PROGRM",
                                     "H03TIMSYS",
                                     "H03SCRCOD",
                                     "H03OPECOD",
                                     "H03FLGMAS",
                                     "H03FLGWK1",
                                     "H03FLGWK2",
                                     "H03FLGWK3",
                                     "E03WTIPOI",
                                     "E03WRIF",
                                     "E03WTIPGA",
                                     "E03WDESGA",
                                     "E03WTIPIG",
                                     "E03WRIFGA",
                                     "E03WMONGA",
                                     "E03WTOGAR",
                                     "E03NUMREC",
                                     "E03INDOPE"
                                   };
  final static String tnames[] = {
                                   "H03USERID",
                                   "H03PROGRM",
                                   "H03TIMSYS",
                                   "H03SCRCOD",
                                   "H03OPECOD",
                                   "H03FLGMAS",
                                   "H03FLGWK1",
                                   "H03FLGWK2",
                                   "H03FLGWK3",
                                   "E03WTIPOI",
                                   "E03WRIF",
                                   "E03WTIPGA",
                                   "E03WDESGA",
                                   "E03WTIPIG",
                                   "E03WRIFGA",
                                   "E03WMONGA",
                                   "E03WTOGAR",
                                   "E03NUMREC",
                                   "E03INDOPE"
                                 };
  final static String fid = "1051020145138";
  final static String rid = "47CD47BBFBDCB";
  final static String fmtname = "ESI000003";
  final int FIELDCOUNT = 19;
  private static Hashtable tlookup = new Hashtable();
  private CharacterField fieldH03USERID = null;
  private CharacterField fieldH03PROGRM = null;
  private CharacterField fieldH03TIMSYS = null;
  private CharacterField fieldH03SCRCOD = null;
  private CharacterField fieldH03OPECOD = null;
  private CharacterField fieldH03FLGMAS = null;
  private CharacterField fieldH03FLGWK1 = null;
  private CharacterField fieldH03FLGWK2 = null;
  private CharacterField fieldH03FLGWK3 = null;
  private CharacterField fieldE03WTIPOI = null;
  private DecimalField fieldE03WRIF = null;
  private DecimalField fieldE03WTIPGA = null;
  private CharacterField fieldE03WDESGA = null;
  private CharacterField fieldE03WTIPIG = null;
  private DecimalField fieldE03WRIFGA = null;
  private DecimalField fieldE03WMONGA = null;
  private DecimalField fieldE03WTOGAR = null;
  private DecimalField fieldE03NUMREC = null;
  private CharacterField fieldE03INDOPE = null;

  /**
  * Constructor for ESI000003Message.
  */
  public ESI000003Message()
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
    recordsize = 170;
    fileid = fid;
    recordid = rid;
    message = new byte[getByteLength()];
    formatname = fmtname;
    fieldnames = fldnames;
    tagnames = tnames;
    fields = new MessageField[FIELDCOUNT];

    fields[0] = fieldH03USERID =
    new CharacterField(message, HEADERSIZE + 0, 10, "H03USERID");
    fields[1] = fieldH03PROGRM =
    new CharacterField(message, HEADERSIZE + 10, 10, "H03PROGRM");
    fields[2] = fieldH03TIMSYS =
    new CharacterField(message, HEADERSIZE + 20, 12, "H03TIMSYS");
    fields[3] = fieldH03SCRCOD =
    new CharacterField(message, HEADERSIZE + 32, 2, "H03SCRCOD");
    fields[4] = fieldH03OPECOD =
    new CharacterField(message, HEADERSIZE + 34, 4, "H03OPECOD");
    fields[5] = fieldH03FLGMAS =
    new CharacterField(message, HEADERSIZE + 38, 1, "H03FLGMAS");
    fields[6] = fieldH03FLGWK1 =
    new CharacterField(message, HEADERSIZE + 39, 1, "H03FLGWK1");
    fields[7] = fieldH03FLGWK2 =
    new CharacterField(message, HEADERSIZE + 40, 1, "H03FLGWK2");
    fields[8] = fieldH03FLGWK3 =
    new CharacterField(message, HEADERSIZE + 41, 1, "H03FLGWK3");
    fields[9] = fieldE03WTIPOI =
    new CharacterField(message, HEADERSIZE + 42, 1, "E03WTIPOI");
    fields[10] = fieldE03WRIF =
    new DecimalField(message, HEADERSIZE + 43, 20, 0, "E03WRIF");
    fields[11] = fieldE03WTIPGA =
    new DecimalField(message, HEADERSIZE + 63, 3, 0, "E03WTIPGA");
    fields[12] = fieldE03WDESGA =
    new CharacterField(message, HEADERSIZE + 66, 30, "E03WDESGA");
    fields[13] = fieldE03WTIPIG =
    new CharacterField(message, HEADERSIZE + 96, 1, "E03WTIPIG");
    fields[14] = fieldE03WRIFGA =
    new DecimalField(message, HEADERSIZE + 97, 20, 0, "E03WRIFGA");
    fields[15] = fieldE03WMONGA =
    new DecimalField(message, HEADERSIZE + 117, 22, 2, "E03WMONGA");
    fields[16] = fieldE03WTOGAR =
    new DecimalField(message, HEADERSIZE + 139, 22, 2, "E03WTOGAR");
    fields[17] = fieldE03NUMREC =
    new DecimalField(message, HEADERSIZE + 161, 8, 0, "E03NUMREC");
    fields[18] = fieldE03INDOPE =
    new CharacterField(message, HEADERSIZE + 169, 1, "E03INDOPE");

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
  * Set field H03USERID using a String value.
  */
  public void setH03USERID(String newvalue)
  {
    fieldH03USERID.setString(newvalue);
  }

  /**
  * Get value of field H03USERID as a String.
  */
  public String getH03USERID()
  {
    return fieldH03USERID.getString();
  }

  /**
  * Set field H03PROGRM using a String value.
  */
  public void setH03PROGRM(String newvalue)
  {
    fieldH03PROGRM.setString(newvalue);
  }

  /**
  * Get value of field H03PROGRM as a String.
  */
  public String getH03PROGRM()
  {
    return fieldH03PROGRM.getString();
  }

  /**
  * Set field H03TIMSYS using a String value.
  */
  public void setH03TIMSYS(String newvalue)
  {
    fieldH03TIMSYS.setString(newvalue);
  }

  /**
  * Get value of field H03TIMSYS as a String.
  */
  public String getH03TIMSYS()
  {
    return fieldH03TIMSYS.getString();
  }

  /**
  * Set field H03SCRCOD using a String value.
  */
  public void setH03SCRCOD(String newvalue)
  {
    fieldH03SCRCOD.setString(newvalue);
  }

  /**
  * Get value of field H03SCRCOD as a String.
  */
  public String getH03SCRCOD()
  {
    return fieldH03SCRCOD.getString();
  }

  /**
  * Set field H03OPECOD using a String value.
  */
  public void setH03OPECOD(String newvalue)
  {
    fieldH03OPECOD.setString(newvalue);
  }

  /**
  * Get value of field H03OPECOD as a String.
  */
  public String getH03OPECOD()
  {
    return fieldH03OPECOD.getString();
  }

  /**
  * Set field H03FLGMAS using a String value.
  */
  public void setH03FLGMAS(String newvalue)
  {
    fieldH03FLGMAS.setString(newvalue);
  }

  /**
  * Get value of field H03FLGMAS as a String.
  */
  public String getH03FLGMAS()
  {
    return fieldH03FLGMAS.getString();
  }

  /**
  * Set field H03FLGWK1 using a String value.
  */
  public void setH03FLGWK1(String newvalue)
  {
    fieldH03FLGWK1.setString(newvalue);
  }

  /**
  * Get value of field H03FLGWK1 as a String.
  */
  public String getH03FLGWK1()
  {
    return fieldH03FLGWK1.getString();
  }

  /**
  * Set field H03FLGWK2 using a String value.
  */
  public void setH03FLGWK2(String newvalue)
  {
    fieldH03FLGWK2.setString(newvalue);
  }

  /**
  * Get value of field H03FLGWK2 as a String.
  */
  public String getH03FLGWK2()
  {
    return fieldH03FLGWK2.getString();
  }

  /**
  * Set field H03FLGWK3 using a String value.
  */
  public void setH03FLGWK3(String newvalue)
  {
    fieldH03FLGWK3.setString(newvalue);
  }

  /**
  * Get value of field H03FLGWK3 as a String.
  */
  public String getH03FLGWK3()
  {
    return fieldH03FLGWK3.getString();
  }

  /**
  * Set field E03WTIPOI using a String value.
  */
  public void setE03WTIPOI(String newvalue)
  {
    fieldE03WTIPOI.setString(newvalue);
  }

  /**
  * Get value of field E03WTIPOI as a String.
  */
  public String getE03WTIPOI()
  {
    return fieldE03WTIPOI.getString();
  }

  /**
  * Set field E03WRIF using a String value.
  */
  public void setE03WRIF(String newvalue)
  {
    fieldE03WRIF.setString(newvalue);
  }

  /**
  * Get value of field E03WRIF as a String.
  */
  public String getE03WRIF()
  {
    return fieldE03WRIF.getString();
  }

  /**
  * Set numeric field E03WRIF using a BigDecimal value.
  */
  public void setE03WRIF(BigDecimal newvalue)
  {
    fieldE03WRIF.setBigDecimal(newvalue);
  }

  /**
  * Return value of numeric field E03WRIF as a BigDecimal.
  */
  public BigDecimal getBigDecimalE03WRIF()
  {
    return fieldE03WRIF.getBigDecimal();
  }

  /**
  * Set field E03WTIPGA using a String value.
  */
  public void setE03WTIPGA(String newvalue)
  {
    fieldE03WTIPGA.setString(newvalue);
  }

  /**
  * Get value of field E03WTIPGA as a String.
  */
  public String getE03WTIPGA()
  {
    return fieldE03WTIPGA.getString();
  }

  /**
  * Set numeric field E03WTIPGA using a BigDecimal value.
  */
  public void setE03WTIPGA(BigDecimal newvalue)
  {
    fieldE03WTIPGA.setBigDecimal(newvalue);
  }

  /**
  * Return value of numeric field E03WTIPGA as a BigDecimal.
  */
  public BigDecimal getBigDecimalE03WTIPGA()
  {
    return fieldE03WTIPGA.getBigDecimal();
  }

  /**
  * Set field E03WDESGA using a String value.
  */
  public void setE03WDESGA(String newvalue)
  {
    fieldE03WDESGA.setString(newvalue);
  }

  /**
  * Get value of field E03WDESGA as a String.
  */
  public String getE03WDESGA()
  {
    return fieldE03WDESGA.getString();
  }

  /**
  * Set field E03WTIPIG using a String value.
  */
  public void setE03WTIPIG(String newvalue)
  {
    fieldE03WTIPIG.setString(newvalue);
  }

  /**
  * Get value of field E03WTIPIG as a String.
  */
  public String getE03WTIPIG()
  {
    return fieldE03WTIPIG.getString();
  }

  /**
  * Set field E03WRIFGA using a String value.
  */
  public void setE03WRIFGA(String newvalue)
  {
    fieldE03WRIFGA.setString(newvalue);
  }

  /**
  * Get value of field E03WRIFGA as a String.
  */
  public String getE03WRIFGA()
  {
    return fieldE03WRIFGA.getString();
  }

  /**
  * Set numeric field E03WRIFGA using a BigDecimal value.
  */
  public void setE03WRIFGA(BigDecimal newvalue)
  {
    fieldE03WRIFGA.setBigDecimal(newvalue);
  }

  /**
  * Return value of numeric field E03WRIFGA as a BigDecimal.
  */
  public BigDecimal getBigDecimalE03WRIFGA()
  {
    return fieldE03WRIFGA.getBigDecimal();
  }

  /**
  * Set field E03WMONGA using a String value.
  */
  public void setE03WMONGA(String newvalue)
  {
    fieldE03WMONGA.setString(newvalue);
  }

  /**
  * Get value of field E03WMONGA as a String.
  */
  public String getE03WMONGA()
  {
    return fieldE03WMONGA.getString();
  }

  /**
  * Set numeric field E03WMONGA using a BigDecimal value.
  */
  public void setE03WMONGA(BigDecimal newvalue)
  {
    fieldE03WMONGA.setBigDecimal(newvalue);
  }

  /**
  * Return value of numeric field E03WMONGA as a BigDecimal.
  */
  public BigDecimal getBigDecimalE03WMONGA()
  {
    return fieldE03WMONGA.getBigDecimal();
  }

  /**
  * Set field E03WTOGAR using a String value.
  */
  public void setE03WTOGAR(String newvalue)
  {
    fieldE03WTOGAR.setString(newvalue);
  }

  /**
  * Get value of field E03WTOGAR as a String.
  */
  public String getE03WTOGAR()
  {
    return fieldE03WTOGAR.getString();
  }

  /**
  * Set numeric field E03WTOGAR using a BigDecimal value.
  */
  public void setE03WTOGAR(BigDecimal newvalue)
  {
    fieldE03WTOGAR.setBigDecimal(newvalue);
  }

  /**
  * Return value of numeric field E03WTOGAR as a BigDecimal.
  */
  public BigDecimal getBigDecimalE03WTOGAR()
  {
    return fieldE03WTOGAR.getBigDecimal();
  }

  /**
  * Set field E03NUMREC using a String value.
  */
  public void setE03NUMREC(String newvalue)
  {
    fieldE03NUMREC.setString(newvalue);
  }

  /**
  * Get value of field E03NUMREC as a String.
  */
  public String getE03NUMREC()
  {
    return fieldE03NUMREC.getString();
  }

  /**
  * Set numeric field E03NUMREC using a BigDecimal value.
  */
  public void setE03NUMREC(BigDecimal newvalue)
  {
    fieldE03NUMREC.setBigDecimal(newvalue);
  }

  /**
  * Return value of numeric field E03NUMREC as a BigDecimal.
  */
  public BigDecimal getBigDecimalE03NUMREC()
  {
    return fieldE03NUMREC.getBigDecimal();
  }

  /**
  * Set field E03INDOPE using a String value.
  */
  public void setE03INDOPE(String newvalue)
  {
    fieldE03INDOPE.setString(newvalue);
  }

  /**
  * Get value of field E03INDOPE as a String.
  */
  public String getE03INDOPE()
  {
    return fieldE03INDOPE.getString();
  }

}