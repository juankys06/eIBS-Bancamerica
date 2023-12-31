package datapro.eibs.beans;

import datapro.eibs.sockets.*;
import java.io.*;
import java.math.*;

import java.util.*;

/**
* Class generated by AS/400 CRTCLASS command from ECH056502 physical file definition.
* 
* File level identifier is 1030716174747.
* Record format level identifier is 54D8F4F413B9A.
*/

public class ECH056502Message extends MessageRecord
{
  final static String fldnames[] = {
                                     "H02USERID",
                                     "H02PROGRM",
                                     "H02TIMSYS",
                                     "H02SCRCOD",
                                     "H02OPECOD",
                                     "H02FLGMAS",
                                     "H02FLGWK1",
                                     "H02FLGWK2",
                                     "H02FLGWK3",
                                     "E02CHMACC",
                                     "E02CHMNCB",
                                     "E02CHMN01",
                                     "E02CHMN02",
                                     "E02CHMN03",
                                     "E02CHMN04",
                                     "E02CHMN05",
                                     "E02CHMS01",
                                     "E02CHMS02",
                                     "E02CHMS03",
                                     "E02CHMS04",
                                     "E02CHMS05"
                                   };
  final static String tnames[] = {
                                   "H02USERID",
                                   "H02PROGRM",
                                   "H02TIMSYS",
                                   "H02SCRCOD",
                                   "H02OPECOD",
                                   "H02FLGMAS",
                                   "H02FLGWK1",
                                   "H02FLGWK2",
                                   "H02FLGWK3",
                                   "E02CHMACC",
                                   "E02CHMNCB",
                                   "E02CHMN01",
                                   "E02CHMN02",
                                   "E02CHMN03",
                                   "E02CHMN04",
                                   "E02CHMN05",
                                   "E02CHMS01",
                                   "E02CHMS02",
                                   "E02CHMS03",
                                   "E02CHMS04",
                                   "E02CHMS05"
                                 };
  final static String fid = "1030716174747";
  final static String rid = "54D8F4F413B9A";
  final static String fmtname = "ECH056502";
  final int FIELDCOUNT = 21;
  private static Hashtable tlookup = new Hashtable();
  private CharacterField fieldH02USERID = null;
  private CharacterField fieldH02PROGRM = null;
  private CharacterField fieldH02TIMSYS = null;
  private CharacterField fieldH02SCRCOD = null;
  private CharacterField fieldH02OPECOD = null;
  private CharacterField fieldH02FLGMAS = null;
  private CharacterField fieldH02FLGWK1 = null;
  private CharacterField fieldH02FLGWK2 = null;
  private CharacterField fieldH02FLGWK3 = null;
  private DecimalField fieldE02CHMACC = null;
  private DecimalField fieldE02CHMNCB = null;
  private DecimalField fieldE02CHMN01 = null;
  private DecimalField fieldE02CHMN02 = null;
  private DecimalField fieldE02CHMN03 = null;
  private DecimalField fieldE02CHMN04 = null;
  private DecimalField fieldE02CHMN05 = null;
  private CharacterField fieldE02CHMS01 = null;
  private CharacterField fieldE02CHMS02 = null;
  private CharacterField fieldE02CHMS03 = null;
  private CharacterField fieldE02CHMS04 = null;
  private CharacterField fieldE02CHMS05 = null;

  /**
  * Constructor for ECH056502Message.
  */
  public ECH056502Message()
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

    fields[0] = fieldH02USERID =
    new CharacterField(message, HEADERSIZE + 0, 10, "H02USERID");
    fields[1] = fieldH02PROGRM =
    new CharacterField(message, HEADERSIZE + 10, 10, "H02PROGRM");
    fields[2] = fieldH02TIMSYS =
    new CharacterField(message, HEADERSIZE + 20, 12, "H02TIMSYS");
    fields[3] = fieldH02SCRCOD =
    new CharacterField(message, HEADERSIZE + 32, 2, "H02SCRCOD");
    fields[4] = fieldH02OPECOD =
    new CharacterField(message, HEADERSIZE + 34, 4, "H02OPECOD");
    fields[5] = fieldH02FLGMAS =
    new CharacterField(message, HEADERSIZE + 38, 1, "H02FLGMAS");
    fields[6] = fieldH02FLGWK1 =
    new CharacterField(message, HEADERSIZE + 39, 1, "H02FLGWK1");
    fields[7] = fieldH02FLGWK2 =
    new CharacterField(message, HEADERSIZE + 40, 1, "H02FLGWK2");
    fields[8] = fieldH02FLGWK3 =
    new CharacterField(message, HEADERSIZE + 41, 1, "H02FLGWK3");
    fields[9] = fieldE02CHMACC =
    new DecimalField(message, HEADERSIZE + 42, 13, 0, "E02CHMACC");
    fields[10] = fieldE02CHMNCB =
    new DecimalField(message, HEADERSIZE + 55, 5, 0, "E02CHMNCB");
    fields[11] = fieldE02CHMN01 =
    new DecimalField(message, HEADERSIZE + 60, 10, 0, "E02CHMN01");
    fields[12] = fieldE02CHMN02 =
    new DecimalField(message, HEADERSIZE + 70, 10, 0, "E02CHMN02");
    fields[13] = fieldE02CHMN03 =
    new DecimalField(message, HEADERSIZE + 80, 10, 0, "E02CHMN03");
    fields[14] = fieldE02CHMN04 =
    new DecimalField(message, HEADERSIZE + 90, 10, 0, "E02CHMN04");
    fields[15] = fieldE02CHMN05 =
    new DecimalField(message, HEADERSIZE + 100, 10, 0, "E02CHMN05");
    fields[16] = fieldE02CHMS01 =
    new CharacterField(message, HEADERSIZE + 110, 12, "E02CHMS01");
    fields[17] = fieldE02CHMS02 =
    new CharacterField(message, HEADERSIZE + 122, 12, "E02CHMS02");
    fields[18] = fieldE02CHMS03 =
    new CharacterField(message, HEADERSIZE + 134, 12, "E02CHMS03");
    fields[19] = fieldE02CHMS04 =
    new CharacterField(message, HEADERSIZE + 146, 12, "E02CHMS04");
    fields[20] = fieldE02CHMS05 =
    new CharacterField(message, HEADERSIZE + 158, 12, "E02CHMS05");

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
  * Set field H02USERID using a String value.
  */
  public void setH02USERID(String newvalue)
  {
    fieldH02USERID.setString(newvalue);
  }

  /**
  * Get value of field H02USERID as a String.
  */
  public String getH02USERID()
  {
    return fieldH02USERID.getString();
  }

  /**
  * Set field H02PROGRM using a String value.
  */
  public void setH02PROGRM(String newvalue)
  {
    fieldH02PROGRM.setString(newvalue);
  }

  /**
  * Get value of field H02PROGRM as a String.
  */
  public String getH02PROGRM()
  {
    return fieldH02PROGRM.getString();
  }

  /**
  * Set field H02TIMSYS using a String value.
  */
  public void setH02TIMSYS(String newvalue)
  {
    fieldH02TIMSYS.setString(newvalue);
  }

  /**
  * Get value of field H02TIMSYS as a String.
  */
  public String getH02TIMSYS()
  {
    return fieldH02TIMSYS.getString();
  }

  /**
  * Set field H02SCRCOD using a String value.
  */
  public void setH02SCRCOD(String newvalue)
  {
    fieldH02SCRCOD.setString(newvalue);
  }

  /**
  * Get value of field H02SCRCOD as a String.
  */
  public String getH02SCRCOD()
  {
    return fieldH02SCRCOD.getString();
  }

  /**
  * Set field H02OPECOD using a String value.
  */
  public void setH02OPECOD(String newvalue)
  {
    fieldH02OPECOD.setString(newvalue);
  }

  /**
  * Get value of field H02OPECOD as a String.
  */
  public String getH02OPECOD()
  {
    return fieldH02OPECOD.getString();
  }

  /**
  * Set field H02FLGMAS using a String value.
  */
  public void setH02FLGMAS(String newvalue)
  {
    fieldH02FLGMAS.setString(newvalue);
  }

  /**
  * Get value of field H02FLGMAS as a String.
  */
  public String getH02FLGMAS()
  {
    return fieldH02FLGMAS.getString();
  }

  /**
  * Set field H02FLGWK1 using a String value.
  */
  public void setH02FLGWK1(String newvalue)
  {
    fieldH02FLGWK1.setString(newvalue);
  }

  /**
  * Get value of field H02FLGWK1 as a String.
  */
  public String getH02FLGWK1()
  {
    return fieldH02FLGWK1.getString();
  }

  /**
  * Set field H02FLGWK2 using a String value.
  */
  public void setH02FLGWK2(String newvalue)
  {
    fieldH02FLGWK2.setString(newvalue);
  }

  /**
  * Get value of field H02FLGWK2 as a String.
  */
  public String getH02FLGWK2()
  {
    return fieldH02FLGWK2.getString();
  }

  /**
  * Set field H02FLGWK3 using a String value.
  */
  public void setH02FLGWK3(String newvalue)
  {
    fieldH02FLGWK3.setString(newvalue);
  }

  /**
  * Get value of field H02FLGWK3 as a String.
  */
  public String getH02FLGWK3()
  {
    return fieldH02FLGWK3.getString();
  }

  /**
  * Set field E02CHMACC using a String value.
  */
  public void setE02CHMACC(String newvalue)
  {
    fieldE02CHMACC.setString(newvalue);
  }

  /**
  * Get value of field E02CHMACC as a String.
  */
  public String getE02CHMACC()
  {
    return fieldE02CHMACC.getString();
  }

  /**
  * Set numeric field E02CHMACC using a BigDecimal value.
  */
  public void setE02CHMACC(BigDecimal newvalue)
  {
    fieldE02CHMACC.setBigDecimal(newvalue);
  }

  /**
  * Return value of numeric field E02CHMACC as a BigDecimal.
  */
  public BigDecimal getBigDecimalE02CHMACC()
  {
    return fieldE02CHMACC.getBigDecimal();
  }

  /**
  * Set field E02CHMNCB using a String value.
  */
  public void setE02CHMNCB(String newvalue)
  {
    fieldE02CHMNCB.setString(newvalue);
  }

  /**
  * Get value of field E02CHMNCB as a String.
  */
  public String getE02CHMNCB()
  {
    return fieldE02CHMNCB.getString();
  }

  /**
  * Set numeric field E02CHMNCB using a BigDecimal value.
  */
  public void setE02CHMNCB(BigDecimal newvalue)
  {
    fieldE02CHMNCB.setBigDecimal(newvalue);
  }

  /**
  * Return value of numeric field E02CHMNCB as a BigDecimal.
  */
  public BigDecimal getBigDecimalE02CHMNCB()
  {
    return fieldE02CHMNCB.getBigDecimal();
  }

  /**
  * Set field E02CHMN01 using a String value.
  */
  public void setE02CHMN01(String newvalue)
  {
    fieldE02CHMN01.setString(newvalue);
  }

  /**
  * Get value of field E02CHMN01 as a String.
  */
  public String getE02CHMN01()
  {
    return fieldE02CHMN01.getString();
  }

  /**
  * Set numeric field E02CHMN01 using a BigDecimal value.
  */
  public void setE02CHMN01(BigDecimal newvalue)
  {
    fieldE02CHMN01.setBigDecimal(newvalue);
  }

  /**
  * Return value of numeric field E02CHMN01 as a BigDecimal.
  */
  public BigDecimal getBigDecimalE02CHMN01()
  {
    return fieldE02CHMN01.getBigDecimal();
  }

  /**
  * Set field E02CHMN02 using a String value.
  */
  public void setE02CHMN02(String newvalue)
  {
    fieldE02CHMN02.setString(newvalue);
  }

  /**
  * Get value of field E02CHMN02 as a String.
  */
  public String getE02CHMN02()
  {
    return fieldE02CHMN02.getString();
  }

  /**
  * Set numeric field E02CHMN02 using a BigDecimal value.
  */
  public void setE02CHMN02(BigDecimal newvalue)
  {
    fieldE02CHMN02.setBigDecimal(newvalue);
  }

  /**
  * Return value of numeric field E02CHMN02 as a BigDecimal.
  */
  public BigDecimal getBigDecimalE02CHMN02()
  {
    return fieldE02CHMN02.getBigDecimal();
  }

  /**
  * Set field E02CHMN03 using a String value.
  */
  public void setE02CHMN03(String newvalue)
  {
    fieldE02CHMN03.setString(newvalue);
  }

  /**
  * Get value of field E02CHMN03 as a String.
  */
  public String getE02CHMN03()
  {
    return fieldE02CHMN03.getString();
  }

  /**
  * Set numeric field E02CHMN03 using a BigDecimal value.
  */
  public void setE02CHMN03(BigDecimal newvalue)
  {
    fieldE02CHMN03.setBigDecimal(newvalue);
  }

  /**
  * Return value of numeric field E02CHMN03 as a BigDecimal.
  */
  public BigDecimal getBigDecimalE02CHMN03()
  {
    return fieldE02CHMN03.getBigDecimal();
  }

  /**
  * Set field E02CHMN04 using a String value.
  */
  public void setE02CHMN04(String newvalue)
  {
    fieldE02CHMN04.setString(newvalue);
  }

  /**
  * Get value of field E02CHMN04 as a String.
  */
  public String getE02CHMN04()
  {
    return fieldE02CHMN04.getString();
  }

  /**
  * Set numeric field E02CHMN04 using a BigDecimal value.
  */
  public void setE02CHMN04(BigDecimal newvalue)
  {
    fieldE02CHMN04.setBigDecimal(newvalue);
  }

  /**
  * Return value of numeric field E02CHMN04 as a BigDecimal.
  */
  public BigDecimal getBigDecimalE02CHMN04()
  {
    return fieldE02CHMN04.getBigDecimal();
  }

  /**
  * Set field E02CHMN05 using a String value.
  */
  public void setE02CHMN05(String newvalue)
  {
    fieldE02CHMN05.setString(newvalue);
  }

  /**
  * Get value of field E02CHMN05 as a String.
  */
  public String getE02CHMN05()
  {
    return fieldE02CHMN05.getString();
  }

  /**
  * Set numeric field E02CHMN05 using a BigDecimal value.
  */
  public void setE02CHMN05(BigDecimal newvalue)
  {
    fieldE02CHMN05.setBigDecimal(newvalue);
  }

  /**
  * Return value of numeric field E02CHMN05 as a BigDecimal.
  */
  public BigDecimal getBigDecimalE02CHMN05()
  {
    return fieldE02CHMN05.getBigDecimal();
  }

  /**
  * Set field E02CHMS01 using a String value.
  */
  public void setE02CHMS01(String newvalue)
  {
    fieldE02CHMS01.setString(newvalue);
  }

  /**
  * Get value of field E02CHMS01 as a String.
  */
  public String getE02CHMS01()
  {
    return fieldE02CHMS01.getString();
  }

  /**
  * Set field E02CHMS02 using a String value.
  */
  public void setE02CHMS02(String newvalue)
  {
    fieldE02CHMS02.setString(newvalue);
  }

  /**
  * Get value of field E02CHMS02 as a String.
  */
  public String getE02CHMS02()
  {
    return fieldE02CHMS02.getString();
  }

  /**
  * Set field E02CHMS03 using a String value.
  */
  public void setE02CHMS03(String newvalue)
  {
    fieldE02CHMS03.setString(newvalue);
  }

  /**
  * Get value of field E02CHMS03 as a String.
  */
  public String getE02CHMS03()
  {
    return fieldE02CHMS03.getString();
  }

  /**
  * Set field E02CHMS04 using a String value.
  */
  public void setE02CHMS04(String newvalue)
  {
    fieldE02CHMS04.setString(newvalue);
  }

  /**
  * Get value of field E02CHMS04 as a String.
  */
  public String getE02CHMS04()
  {
    return fieldE02CHMS04.getString();
  }

  /**
  * Set field E02CHMS05 using a String value.
  */
  public void setE02CHMS05(String newvalue)
  {
    fieldE02CHMS05.setString(newvalue);
  }

  /**
  * Get value of field E02CHMS05 as a String.
  */
  public String getE02CHMS05()
  {
    return fieldE02CHMS05.getString();
  }

}
