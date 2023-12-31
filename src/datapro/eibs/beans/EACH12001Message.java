package datapro.eibs.beans;

import datapro.eibs.sockets.*;
import java.io.*;
import java.math.*;

import java.util.*;

/**
* Class generated by AS/400 CRTCLASS command from EACH12001 physical file definition.
* 
* File level identifier is 1080721121337.
* Record format level identifier is 405FA67D33E2C.
*/

public class EACH12001Message extends MessageRecord
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
                                     "E01ACECDE",
                                     "E01ACEDSC",
                                     "E01ACERAP",
                                     "E01ACERAT",
                                     "E01ACEADR",
                                     "E01ACEADL",
                                     "E01ACEAMT",
                                     "E01ACEDFI",
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
                                   "E01ACECDE",
                                   "E01ACEDSC",
                                   "E01ACERAP",
                                   "E01ACERAT",
                                   "E01ACEADR",
                                   "E01ACEADL",
                                   "E01ACEAMT",
                                   "E01ACEDFI",
                                   "E01ACT",
                                   "E01NUMREC",
                                   "E01INDOPE"
                                 };
  final static String fid = "1080721121337";
  final static String rid = "405FA67D33E2C";
  final static String fmtname = "EACH12001";
  final int FIELDCOUNT = 20;
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
  private CharacterField fieldE01ACECDE = null;
  private CharacterField fieldE01ACEDSC = null;
  private CharacterField fieldE01ACERAP = null;
  private CharacterField fieldE01ACERAT = null;
  private CharacterField fieldE01ACEADR = null;
  private DecimalField fieldE01ACEADL = null;
  private DecimalField fieldE01ACEAMT = null;
  private CharacterField fieldE01ACEDFI = null;
  private CharacterField fieldE01ACT = null;
  private DecimalField fieldE01NUMREC = null;
  private CharacterField fieldE01INDOPE = null;

  /**
  * Constructor for EACH12001Message.
  */
  public EACH12001Message()
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
    recordsize = 135;
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
    fields[9] = fieldE01ACECDE =
    new CharacterField(message, HEADERSIZE + 42, 3, "E01ACECDE");
    fields[10] = fieldE01ACEDSC =
    new CharacterField(message, HEADERSIZE + 45, 45, "E01ACEDSC");
    fields[11] = fieldE01ACERAP =
    new CharacterField(message, HEADERSIZE + 90, 1, "E01ACERAP");
    fields[12] = fieldE01ACERAT =
    new CharacterField(message, HEADERSIZE + 91, 1, "E01ACERAT");
    fields[13] = fieldE01ACEADR =
    new CharacterField(message, HEADERSIZE + 92, 1, "E01ACEADR");
    fields[14] = fieldE01ACEADL =
    new DecimalField(message, HEADERSIZE + 93, 5, 0, "E01ACEADL");
    fields[15] = fieldE01ACEAMT =
    new DecimalField(message, HEADERSIZE + 98, 17, 2, "E01ACEAMT");
    fields[16] = fieldE01ACEDFI =
    new CharacterField(message, HEADERSIZE + 115, 10, "E01ACEDFI");
    fields[17] = fieldE01ACT =
    new CharacterField(message, HEADERSIZE + 125, 1, "E01ACT");
    fields[18] = fieldE01NUMREC =
    new DecimalField(message, HEADERSIZE + 126, 8, 0, "E01NUMREC");
    fields[19] = fieldE01INDOPE =
    new CharacterField(message, HEADERSIZE + 134, 1, "E01INDOPE");

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
  * Set field E01ACECDE using a String value.
  */
  public void setE01ACECDE(String newvalue)
  {
    fieldE01ACECDE.setString(newvalue);
  }

  /**
  * Get value of field E01ACECDE as a String.
  */
  public String getE01ACECDE()
  {
    return fieldE01ACECDE.getString();
  }

  /**
  * Set field E01ACEDSC using a String value.
  */
  public void setE01ACEDSC(String newvalue)
  {
    fieldE01ACEDSC.setString(newvalue);
  }

  /**
  * Get value of field E01ACEDSC as a String.
  */
  public String getE01ACEDSC()
  {
    return fieldE01ACEDSC.getString();
  }

  /**
  * Set field E01ACERAP using a String value.
  */
  public void setE01ACERAP(String newvalue)
  {
    fieldE01ACERAP.setString(newvalue);
  }

  /**
  * Get value of field E01ACERAP as a String.
  */
  public String getE01ACERAP()
  {
    return fieldE01ACERAP.getString();
  }

  /**
  * Set field E01ACERAT using a String value.
  */
  public void setE01ACERAT(String newvalue)
  {
    fieldE01ACERAT.setString(newvalue);
  }

  /**
  * Get value of field E01ACERAT as a String.
  */
  public String getE01ACERAT()
  {
    return fieldE01ACERAT.getString();
  }

  /**
  * Set field E01ACEADR using a String value.
  */
  public void setE01ACEADR(String newvalue)
  {
    fieldE01ACEADR.setString(newvalue);
  }

  /**
  * Get value of field E01ACEADR as a String.
  */
  public String getE01ACEADR()
  {
    return fieldE01ACEADR.getString();
  }

  /**
  * Set field E01ACEADL using a String value.
  */
  public void setE01ACEADL(String newvalue)
  {
    fieldE01ACEADL.setString(newvalue);
  }

  /**
  * Get value of field E01ACEADL as a String.
  */
  public String getE01ACEADL()
  {
    return fieldE01ACEADL.getString();
  }

  /**
  * Set numeric field E01ACEADL using a BigDecimal value.
  */
  public void setE01ACEADL(BigDecimal newvalue)
  {
    fieldE01ACEADL.setBigDecimal(newvalue);
  }

  /**
  * Return value of numeric field E01ACEADL as a BigDecimal.
  */
  public BigDecimal getBigDecimalE01ACEADL()
  {
    return fieldE01ACEADL.getBigDecimal();
  }

  /**
  * Set field E01ACEAMT using a String value.
  */
  public void setE01ACEAMT(String newvalue)
  {
    fieldE01ACEAMT.setString(newvalue);
  }

  /**
  * Get value of field E01ACEAMT as a String.
  */
  public String getE01ACEAMT()
  {
    return fieldE01ACEAMT.getString();
  }

  /**
  * Set numeric field E01ACEAMT using a BigDecimal value.
  */
  public void setE01ACEAMT(BigDecimal newvalue)
  {
    fieldE01ACEAMT.setBigDecimal(newvalue);
  }

  /**
  * Return value of numeric field E01ACEAMT as a BigDecimal.
  */
  public BigDecimal getBigDecimalE01ACEAMT()
  {
    return fieldE01ACEAMT.getBigDecimal();
  }

  /**
  * Set field E01ACEDFI using a String value.
  */
  public void setE01ACEDFI(String newvalue)
  {
    fieldE01ACEDFI.setString(newvalue);
  }

  /**
  * Get value of field E01ACEDFI as a String.
  */
  public String getE01ACEDFI()
  {
    return fieldE01ACEDFI.getString();
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
