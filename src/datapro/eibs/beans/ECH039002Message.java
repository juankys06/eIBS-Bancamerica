package datapro.eibs.beans;

import datapro.eibs.sockets.*;
import java.io.*;
import java.math.*;

import java.util.*;

/**
* Class generated by AS/400 CRTCLASS command from ECH039002 physical file definition.
* 
* File level identifier is 1021115125923.
* Record format level identifier is 58B43CC470948.
*/

public class ECH039002Message extends MessageRecord
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
                                     "E02ACCION"
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
                                   "E02ACCION"
                                 };
  final static String fid = "1021115125923";
  final static String rid = "58B43CC470948";
  final static String fmtname = "ECH039002";
  final int FIELDCOUNT = 12;
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
  private CharacterField fieldE02ACCION = null;

  /**
  * Constructor for ECH039002Message.
  */
  public ECH039002Message()
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
    recordsize = 61;
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
    fields[11] = fieldE02ACCION =
    new CharacterField(message, HEADERSIZE + 60, 1, "E02ACCION");

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
  * Set field E02ACCION using a String value.
  */
  public void setE02ACCION(String newvalue)
  {
    fieldE02ACCION.setString(newvalue);
  }

  /**
  * Get value of field E02ACCION as a String.
  */
  public String getE02ACCION()
  {
    return fieldE02ACCION.getString();
  }

}
