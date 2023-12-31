package datapro.eibs.beans;

import datapro.eibs.sockets.*;
import java.io.*;
import java.math.*;

import java.util.*;

/**
* Class generated by AS/400 CRTCLASS command from EACH13001 physical file definition.
* 
* File level identifier is 1080814145440.
* Record format level identifier is 30964BB70CDFA.
*/

public class EACH13001Message extends MessageRecord
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
                                     "E01ACTCDE",
                                     "E01ACTDSC",
                                     "E01ACTDC",
                                     "E01ACTTYP",
                                     "E01ACTITC",
                                     "E01ACTRET",
                                     "E01ACTAMT",
                                     "E01ACTDYS",
                                     "E01ACTAT1",
                                     "E01ACTAT2",
                                     "E01ACTAT3",
                                     "E01ACTAT4",
                                     "E01ACTAT5",
                                     "E01ACTAT6",
                                     "E01ACTAT7",
                                     "E01ACTAT8",
                                     "E01ACTAT9",
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
                                   "E01ACTCDE",
                                   "E01ACTDSC",
                                   "E01ACTDC",
                                   "E01ACTTYP",
                                   "E01ACTITC",
                                   "E01ACTRET",
                                   "E01ACTAMT",
                                   "E01ACTDYS",
                                   "E01ACTAT1",
                                   "E01ACTAT2",
                                   "E01ACTAT3",
                                   "E01ACTAT4",
                                   "E01ACTAT5",
                                   "E01ACTAT6",
                                   "E01ACTAT7",
                                   "E01ACTAT8",
                                   "E01ACTAT9",
                                   "E01ACT",
                                   "E01NUMREC",
                                   "E01INDOPE"
                                 };
  final static String fid = "1080814145440";
  final static String rid = "30964BB70CDFA";
  final static String fmtname = "EACH13001";
  final int FIELDCOUNT = 29;
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
  private CharacterField fieldE01ACTCDE = null;
  private CharacterField fieldE01ACTDSC = null;
  private CharacterField fieldE01ACTDC = null;
  private CharacterField fieldE01ACTTYP = null;
  private CharacterField fieldE01ACTITC = null;
  private CharacterField fieldE01ACTRET = null;
  private CharacterField fieldE01ACTAMT = null;
  private DecimalField fieldE01ACTDYS = null;
  private CharacterField fieldE01ACTAT1 = null;
  private CharacterField fieldE01ACTAT2 = null;
  private CharacterField fieldE01ACTAT3 = null;
  private CharacterField fieldE01ACTAT4 = null;
  private CharacterField fieldE01ACTAT5 = null;
  private CharacterField fieldE01ACTAT6 = null;
  private CharacterField fieldE01ACTAT7 = null;
  private CharacterField fieldE01ACTAT8 = null;
  private CharacterField fieldE01ACTAT9 = null;
  private CharacterField fieldE01ACT = null;
  private DecimalField fieldE01NUMREC = null;
  private CharacterField fieldE01INDOPE = null;

  /**
  * Constructor for EACH13001Message.
  */
  public EACH13001Message()
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
    recordsize = 136;
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
    fields[9] = fieldE01ACTCDE =
    new CharacterField(message, HEADERSIZE + 42, 2, "E01ACTCDE");
    fields[10] = fieldE01ACTDSC =
    new CharacterField(message, HEADERSIZE + 44, 45, "E01ACTDSC");
    fields[11] = fieldE01ACTDC =
    new CharacterField(message, HEADERSIZE + 89, 1, "E01ACTDC");
    fields[12] = fieldE01ACTTYP =
    new CharacterField(message, HEADERSIZE + 90, 1, "E01ACTTYP");
    fields[13] = fieldE01ACTITC =
    new CharacterField(message, HEADERSIZE + 91, 2, "E01ACTITC");
    fields[14] = fieldE01ACTRET =
    new CharacterField(message, HEADERSIZE + 93, 2, "E01ACTRET");
    fields[15] = fieldE01ACTAMT =
    new CharacterField(message, HEADERSIZE + 95, 1, "E01ACTAMT");
    fields[16] = fieldE01ACTDYS =
    new DecimalField(message, HEADERSIZE + 96, 3, 0, "E01ACTDYS");
    fields[17] = fieldE01ACTAT1 =
    new CharacterField(message, HEADERSIZE + 99, 3, "E01ACTAT1");
    fields[18] = fieldE01ACTAT2 =
    new CharacterField(message, HEADERSIZE + 102, 3, "E01ACTAT2");
    fields[19] = fieldE01ACTAT3 =
    new CharacterField(message, HEADERSIZE + 105, 3, "E01ACTAT3");
    fields[20] = fieldE01ACTAT4 =
    new CharacterField(message, HEADERSIZE + 108, 3, "E01ACTAT4");
    fields[21] = fieldE01ACTAT5 =
    new CharacterField(message, HEADERSIZE + 111, 3, "E01ACTAT5");
    fields[22] = fieldE01ACTAT6 =
    new CharacterField(message, HEADERSIZE + 114, 3, "E01ACTAT6");
    fields[23] = fieldE01ACTAT7 =
    new CharacterField(message, HEADERSIZE + 117, 3, "E01ACTAT7");
    fields[24] = fieldE01ACTAT8 =
    new CharacterField(message, HEADERSIZE + 120, 3, "E01ACTAT8");
    fields[25] = fieldE01ACTAT9 =
    new CharacterField(message, HEADERSIZE + 123, 3, "E01ACTAT9");
    fields[26] = fieldE01ACT =
    new CharacterField(message, HEADERSIZE + 126, 1, "E01ACT");
    fields[27] = fieldE01NUMREC =
    new DecimalField(message, HEADERSIZE + 127, 8, 0, "E01NUMREC");
    fields[28] = fieldE01INDOPE =
    new CharacterField(message, HEADERSIZE + 135, 1, "E01INDOPE");

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
  * Set field E01ACTCDE using a String value.
  */
  public void setE01ACTCDE(String newvalue)
  {
    fieldE01ACTCDE.setString(newvalue);
  }

  /**
  * Get value of field E01ACTCDE as a String.
  */
  public String getE01ACTCDE()
  {
    return fieldE01ACTCDE.getString();
  }

  /**
  * Set field E01ACTDSC using a String value.
  */
  public void setE01ACTDSC(String newvalue)
  {
    fieldE01ACTDSC.setString(newvalue);
  }

  /**
  * Get value of field E01ACTDSC as a String.
  */
  public String getE01ACTDSC()
  {
    return fieldE01ACTDSC.getString();
  }

  /**
  * Set field E01ACTDC using a String value.
  */
  public void setE01ACTDC(String newvalue)
  {
    fieldE01ACTDC.setString(newvalue);
  }

  /**
  * Get value of field E01ACTDC as a String.
  */
  public String getE01ACTDC()
  {
    return fieldE01ACTDC.getString();
  }

  /**
  * Set field E01ACTTYP using a String value.
  */
  public void setE01ACTTYP(String newvalue)
  {
    fieldE01ACTTYP.setString(newvalue);
  }

  /**
  * Get value of field E01ACTTYP as a String.
  */
  public String getE01ACTTYP()
  {
    return fieldE01ACTTYP.getString();
  }

  /**
  * Set field E01ACTITC using a String value.
  */
  public void setE01ACTITC(String newvalue)
  {
    fieldE01ACTITC.setString(newvalue);
  }

  /**
  * Get value of field E01ACTITC as a String.
  */
  public String getE01ACTITC()
  {
    return fieldE01ACTITC.getString();
  }

  /**
  * Set field E01ACTRET using a String value.
  */
  public void setE01ACTRET(String newvalue)
  {
    fieldE01ACTRET.setString(newvalue);
  }

  /**
  * Get value of field E01ACTRET as a String.
  */
  public String getE01ACTRET()
  {
    return fieldE01ACTRET.getString();
  }

  /**
  * Set field E01ACTAMT using a String value.
  */
  public void setE01ACTAMT(String newvalue)
  {
    fieldE01ACTAMT.setString(newvalue);
  }

  /**
  * Get value of field E01ACTAMT as a String.
  */
  public String getE01ACTAMT()
  {
    return fieldE01ACTAMT.getString();
  }

  /**
  * Set field E01ACTDYS using a String value.
  */
  public void setE01ACTDYS(String newvalue)
  {
    fieldE01ACTDYS.setString(newvalue);
  }

  /**
  * Get value of field E01ACTDYS as a String.
  */
  public String getE01ACTDYS()
  {
    return fieldE01ACTDYS.getString();
  }

  /**
  * Set numeric field E01ACTDYS using a BigDecimal value.
  */
  public void setE01ACTDYS(BigDecimal newvalue)
  {
    fieldE01ACTDYS.setBigDecimal(newvalue);
  }

  /**
  * Return value of numeric field E01ACTDYS as a BigDecimal.
  */
  public BigDecimal getBigDecimalE01ACTDYS()
  {
    return fieldE01ACTDYS.getBigDecimal();
  }

  /**
  * Set field E01ACTAT1 using a String value.
  */
  public void setE01ACTAT1(String newvalue)
  {
    fieldE01ACTAT1.setString(newvalue);
  }

  /**
  * Get value of field E01ACTAT1 as a String.
  */
  public String getE01ACTAT1()
  {
    return fieldE01ACTAT1.getString();
  }

  /**
  * Set field E01ACTAT2 using a String value.
  */
  public void setE01ACTAT2(String newvalue)
  {
    fieldE01ACTAT2.setString(newvalue);
  }

  /**
  * Get value of field E01ACTAT2 as a String.
  */
  public String getE01ACTAT2()
  {
    return fieldE01ACTAT2.getString();
  }

  /**
  * Set field E01ACTAT3 using a String value.
  */
  public void setE01ACTAT3(String newvalue)
  {
    fieldE01ACTAT3.setString(newvalue);
  }

  /**
  * Get value of field E01ACTAT3 as a String.
  */
  public String getE01ACTAT3()
  {
    return fieldE01ACTAT3.getString();
  }

  /**
  * Set field E01ACTAT4 using a String value.
  */
  public void setE01ACTAT4(String newvalue)
  {
    fieldE01ACTAT4.setString(newvalue);
  }

  /**
  * Get value of field E01ACTAT4 as a String.
  */
  public String getE01ACTAT4()
  {
    return fieldE01ACTAT4.getString();
  }

  /**
  * Set field E01ACTAT5 using a String value.
  */
  public void setE01ACTAT5(String newvalue)
  {
    fieldE01ACTAT5.setString(newvalue);
  }

  /**
  * Get value of field E01ACTAT5 as a String.
  */
  public String getE01ACTAT5()
  {
    return fieldE01ACTAT5.getString();
  }

  /**
  * Set field E01ACTAT6 using a String value.
  */
  public void setE01ACTAT6(String newvalue)
  {
    fieldE01ACTAT6.setString(newvalue);
  }

  /**
  * Get value of field E01ACTAT6 as a String.
  */
  public String getE01ACTAT6()
  {
    return fieldE01ACTAT6.getString();
  }

  /**
  * Set field E01ACTAT7 using a String value.
  */
  public void setE01ACTAT7(String newvalue)
  {
    fieldE01ACTAT7.setString(newvalue);
  }

  /**
  * Get value of field E01ACTAT7 as a String.
  */
  public String getE01ACTAT7()
  {
    return fieldE01ACTAT7.getString();
  }

  /**
  * Set field E01ACTAT8 using a String value.
  */
  public void setE01ACTAT8(String newvalue)
  {
    fieldE01ACTAT8.setString(newvalue);
  }

  /**
  * Get value of field E01ACTAT8 as a String.
  */
  public String getE01ACTAT8()
  {
    return fieldE01ACTAT8.getString();
  }

  /**
  * Set field E01ACTAT9 using a String value.
  */
  public void setE01ACTAT9(String newvalue)
  {
    fieldE01ACTAT9.setString(newvalue);
  }

  /**
  * Get value of field E01ACTAT9 as a String.
  */
  public String getE01ACTAT9()
  {
    return fieldE01ACTAT9.getString();
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
