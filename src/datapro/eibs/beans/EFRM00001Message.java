package datapro.eibs.beans;

import datapro.eibs.sockets.*;
import java.io.*;
import java.math.*;

import java.util.*;

/**
* Class generated by AS/400 CRTCLASS command from EFRM00001 physical file definition.
* 
* File level identifier is 1100909162659.
* Record format level identifier is 444949D47D10B.
*/

public class EFRM00001Message extends MessageRecord
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
                                     "E01SELACC",
                                     "E01SELACD",
                                     "E01SELFTY",
                                     "E01APFFCD",
                                     "E01APFPTH",
                                     "E01APFDSC",
                                     "E01APFDDS",
                                     "E01APFCPI",
                                     "E01APFOPE",
                                     "E01APFIFS",
                                     "E01APFFRM",
                                     "E01APFF01",
                                     "E01APFF02",
                                     "E01APFUC1",
                                     "E01APFUC2",
                                     "E01MORFRM"
                                   };
  final static String tnames[] = {
                                   "USER",
                                   "PROGRAM",
                                   "TIME-DATE",
                                   "SCREEN",
                                   "OPERAT",
                                   "MORE",
                                   "LENGUAJE",
                                   "FLAG",
                                   "FLAG",
                                   "AccountNumber",
                                   "AccountClass",
                                   "FormType",
                                   "FormCode",
                                   "AddressForm",
                                   "Description",
                                   "NameDdS",
                                   "Copies",
                                   "OperationCode",
                                   "INTERFASE",
                                   "PrinterName",
                                   "Filler1",
                                   "Filler2",
                                   "User1Code",
                                   "User2Code",
                                   "MoreForms"
                                 };
  final static String fid = "1100909162659";
  final static String rid = "444949D47D10B";
  final static String fmtname = "EFRM00001";
  final int FIELDCOUNT = 25;
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
  private DecimalField fieldE01SELACC = null;
  private CharacterField fieldE01SELACD = null;
  private CharacterField fieldE01SELFTY = null;
  private CharacterField fieldE01APFFCD = null;
  private CharacterField fieldE01APFPTH = null;
  private CharacterField fieldE01APFDSC = null;
  private CharacterField fieldE01APFDDS = null;
  private DecimalField fieldE01APFCPI = null;
  private CharacterField fieldE01APFOPE = null;
  private CharacterField fieldE01APFIFS = null;
  private CharacterField fieldE01APFFRM = null;
  private CharacterField fieldE01APFF01 = null;
  private CharacterField fieldE01APFF02 = null;
  private CharacterField fieldE01APFUC1 = null;
  private CharacterField fieldE01APFUC2 = null;
  private CharacterField fieldE01MORFRM = null;

  /**
  * Constructor for EFRM00001Message.
  */
  public EFRM00001Message()
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
    recordsize = 224;
    fileid = fid;
    recordid = rid;
    message = new byte[getByteLength()];
    formatname = fmtname;
    fieldnames = fldnames;
    tagnames = tnames;
    fields = new MessageField[FIELDCOUNT];

    fields[0] = fieldH01USERID =
    new CharacterField(message, HEADERSIZE + 0, 10, "USER");
    fields[1] = fieldH01PROGRM =
    new CharacterField(message, HEADERSIZE + 10, 10, "PROGRAM");
    fields[2] = fieldH01TIMSYS =
    new CharacterField(message, HEADERSIZE + 20, 12, "TIME-DATE");
    fields[3] = fieldH01SCRCOD =
    new CharacterField(message, HEADERSIZE + 32, 2, "SCREEN");
    fields[4] = fieldH01OPECOD =
    new CharacterField(message, HEADERSIZE + 34, 4, "OPERAT");
    fields[5] = fieldH01FLGMAS =
    new CharacterField(message, HEADERSIZE + 38, 1, "MORE");
    fields[6] = fieldH01FLGWK1 =
    new CharacterField(message, HEADERSIZE + 39, 1, "LENGUAJE");
    fields[7] = fieldH01FLGWK2 =
    new CharacterField(message, HEADERSIZE + 40, 1, "FLAG");
    fields[8] = fieldH01FLGWK3 =
    new CharacterField(message, HEADERSIZE + 41, 1, "FLAG");
    fields[9] = fieldE01SELACC =
    new DecimalField(message, HEADERSIZE + 42, 13, 0, "AccountNumber");
    fields[10] = fieldE01SELACD =
    new CharacterField(message, HEADERSIZE + 55, 2, "AccountClass");
    fields[11] = fieldE01SELFTY =
    new CharacterField(message, HEADERSIZE + 57, 2, "FormType");
    fields[12] = fieldE01APFFCD =
    new CharacterField(message, HEADERSIZE + 59, 15, "FormCode");
    fields[13] = fieldE01APFPTH =
    new CharacterField(message, HEADERSIZE + 74, 80, "AddressForm");
    fields[14] = fieldE01APFDSC =
    new CharacterField(message, HEADERSIZE + 154, 35, "Description");
    fields[15] = fieldE01APFDDS =
    new CharacterField(message, HEADERSIZE + 189, 10, "NameDdS");
    fields[16] = fieldE01APFCPI =
    new DecimalField(message, HEADERSIZE + 199, 2, 0, "Copies");
    fields[17] = fieldE01APFOPE =
    new CharacterField(message, HEADERSIZE + 201, 1, "OperationCode");
    fields[18] = fieldE01APFIFS =
    new CharacterField(message, HEADERSIZE + 202, 1, "INTERFASE");
    fields[19] = fieldE01APFFRM =
    new CharacterField(message, HEADERSIZE + 203, 10, "PrinterName");
    fields[20] = fieldE01APFF01 =
    new CharacterField(message, HEADERSIZE + 213, 1, "Filler1");
    fields[21] = fieldE01APFF02 =
    new CharacterField(message, HEADERSIZE + 214, 1, "Filler2");
    fields[22] = fieldE01APFUC1 =
    new CharacterField(message, HEADERSIZE + 215, 4, "User1Code");
    fields[23] = fieldE01APFUC2 =
    new CharacterField(message, HEADERSIZE + 219, 4, "User2Code");
    fields[24] = fieldE01MORFRM =
    new CharacterField(message, HEADERSIZE + 223, 1, "MoreForms");

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
  * Set field E01SELACC using a String value.
  */
  public void setE01SELACC(String newvalue)
  {
    fieldE01SELACC.setString(newvalue);
  }

  /**
  * Get value of field E01SELACC as a String.
  */
  public String getE01SELACC()
  {
    return fieldE01SELACC.getString();
  }

  /**
  * Set numeric field E01SELACC using a BigDecimal value.
  */
  public void setE01SELACC(BigDecimal newvalue)
  {
    fieldE01SELACC.setBigDecimal(newvalue);
  }

  /**
  * Return value of numeric field E01SELACC as a BigDecimal.
  */
  public BigDecimal getBigDecimalE01SELACC()
  {
    return fieldE01SELACC.getBigDecimal();
  }

  /**
  * Set field E01SELACD using a String value.
  */
  public void setE01SELACD(String newvalue)
  {
    fieldE01SELACD.setString(newvalue);
  }

  /**
  * Get value of field E01SELACD as a String.
  */
  public String getE01SELACD()
  {
    return fieldE01SELACD.getString();
  }

  /**
  * Set field E01SELFTY using a String value.
  */
  public void setE01SELFTY(String newvalue)
  {
    fieldE01SELFTY.setString(newvalue);
  }

  /**
  * Get value of field E01SELFTY as a String.
  */
  public String getE01SELFTY()
  {
    return fieldE01SELFTY.getString();
  }

  /**
  * Set field E01APFFCD using a String value.
  */
  public void setE01APFFCD(String newvalue)
  {
    fieldE01APFFCD.setString(newvalue);
  }

  /**
  * Get value of field E01APFFCD as a String.
  */
  public String getE01APFFCD()
  {
    return fieldE01APFFCD.getString();
  }

  /**
  * Set field E01APFPTH using a String value.
  */
  public void setE01APFPTH(String newvalue)
  {
    fieldE01APFPTH.setString(newvalue);
  }

  /**
  * Get value of field E01APFPTH as a String.
  */
  public String getE01APFPTH()
  {
    return fieldE01APFPTH.getString();
  }

  /**
  * Set field E01APFDSC using a String value.
  */
  public void setE01APFDSC(String newvalue)
  {
    fieldE01APFDSC.setString(newvalue);
  }

  /**
  * Get value of field E01APFDSC as a String.
  */
  public String getE01APFDSC()
  {
    return fieldE01APFDSC.getString();
  }

  /**
  * Set field E01APFDDS using a String value.
  */
  public void setE01APFDDS(String newvalue)
  {
    fieldE01APFDDS.setString(newvalue);
  }

  /**
  * Get value of field E01APFDDS as a String.
  */
  public String getE01APFDDS()
  {
    return fieldE01APFDDS.getString();
  }

  /**
  * Set field E01APFCPI using a String value.
  */
  public void setE01APFCPI(String newvalue)
  {
    fieldE01APFCPI.setString(newvalue);
  }

  /**
  * Get value of field E01APFCPI as a String.
  */
  public String getE01APFCPI()
  {
    return fieldE01APFCPI.getString();
  }

  /**
  * Set numeric field E01APFCPI using a BigDecimal value.
  */
  public void setE01APFCPI(BigDecimal newvalue)
  {
    fieldE01APFCPI.setBigDecimal(newvalue);
  }

  /**
  * Return value of numeric field E01APFCPI as a BigDecimal.
  */
  public BigDecimal getBigDecimalE01APFCPI()
  {
    return fieldE01APFCPI.getBigDecimal();
  }

  /**
  * Set field E01APFOPE using a String value.
  */
  public void setE01APFOPE(String newvalue)
  {
    fieldE01APFOPE.setString(newvalue);
  }

  /**
  * Get value of field E01APFOPE as a String.
  */
  public String getE01APFOPE()
  {
    return fieldE01APFOPE.getString();
  }

  /**
  * Set field E01APFIFS using a String value.
  */
  public void setE01APFIFS(String newvalue)
  {
    fieldE01APFIFS.setString(newvalue);
  }

  /**
  * Get value of field E01APFIFS as a String.
  */
  public String getE01APFIFS()
  {
    return fieldE01APFIFS.getString();
  }

  /**
  * Set field E01APFFRM using a String value.
  */
  public void setE01APFFRM(String newvalue)
  {
    fieldE01APFFRM.setString(newvalue);
  }

  /**
  * Get value of field E01APFFRM as a String.
  */
  public String getE01APFFRM()
  {
    return fieldE01APFFRM.getString();
  }

  /**
  * Set field E01APFF01 using a String value.
  */
  public void setE01APFF01(String newvalue)
  {
    fieldE01APFF01.setString(newvalue);
  }

  /**
  * Get value of field E01APFF01 as a String.
  */
  public String getE01APFF01()
  {
    return fieldE01APFF01.getString();
  }

  /**
  * Set field E01APFF02 using a String value.
  */
  public void setE01APFF02(String newvalue)
  {
    fieldE01APFF02.setString(newvalue);
  }

  /**
  * Get value of field E01APFF02 as a String.
  */
  public String getE01APFF02()
  {
    return fieldE01APFF02.getString();
  }

  /**
  * Set field E01APFUC1 using a String value.
  */
  public void setE01APFUC1(String newvalue)
  {
    fieldE01APFUC1.setString(newvalue);
  }

  /**
  * Get value of field E01APFUC1 as a String.
  */
  public String getE01APFUC1()
  {
    return fieldE01APFUC1.getString();
  }

  /**
  * Set field E01APFUC2 using a String value.
  */
  public void setE01APFUC2(String newvalue)
  {
    fieldE01APFUC2.setString(newvalue);
  }

  /**
  * Get value of field E01APFUC2 as a String.
  */
  public String getE01APFUC2()
  {
    return fieldE01APFUC2.getString();
  }

  /**
  * Set field E01MORFRM using a String value.
  */
  public void setE01MORFRM(String newvalue)
  {
    fieldE01MORFRM.setString(newvalue);
  }

  /**
  * Get value of field E01MORFRM as a String.
  */
  public String getE01MORFRM()
  {
    return fieldE01MORFRM.getString();
  }

}
