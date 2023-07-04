package datapro.eibs.beans;

import datapro.eibs.sockets.*;
import java.io.*;
import java.math.*;

import java.util.*;

/**
* Class generated by AS/400 CRTCLASS command from EDEN01001 physical file definition.
* 
* File level identifier is 1041209113352.
* Record format level identifier is 456C5C19F09F1.
*/

public class EDEN01001Message extends MessageRecord
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
                                     "E01SRCHTP",
                                     "E01NROWOR",
                                     "E01NROMAT",
                                     "E01AVLFL1",
                                     "E01AVLFL2",
                                     "E01AVLFL3",
                                     "E01AVLFL4",
                                     "E01AVLFL5"
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
                                   "E01SRCHTP",
                                   "E01NROWOR",
                                   "E01NROMAT",
                                   "E01AVLFL1",
                                   "E01AVLFL2",
                                   "E01AVLFL3",
                                   "E01AVLFL4",
                                   "E01AVLFL5"
                                 };
  final static String fid = "1041209113352";
  final static String rid = "456C5C19F09F1";
  final static String fmtname = "EDEN01001";
  final int FIELDCOUNT = 17;
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
  private CharacterField fieldE01SRCHTP = null;
  private DecimalField fieldE01NROWOR = null;
  private DecimalField fieldE01NROMAT = null;
  private CharacterField fieldE01AVLFL1 = null;
  private CharacterField fieldE01AVLFL2 = null;
  private CharacterField fieldE01AVLFL3 = null;
  private CharacterField fieldE01AVLFL4 = null;
  private CharacterField fieldE01AVLFL5 = null;

  /**
  * Constructor for EDEN01001Message.
  */
  public EDEN01001Message()
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
    recordsize = 74;
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
    fields[9] = fieldE01SRCHTP =
    new CharacterField(message, HEADERSIZE + 42, 1, "E01SRCHTP");
    fields[10] = fieldE01NROWOR =
    new DecimalField(message, HEADERSIZE + 43, 3, 0, "E01NROWOR");
    fields[11] = fieldE01NROMAT =
    new DecimalField(message, HEADERSIZE + 46, 3, 0, "E01NROMAT");
    fields[12] = fieldE01AVLFL1 =
    new CharacterField(message, HEADERSIZE + 49, 5, "E01AVLFL1");
    fields[13] = fieldE01AVLFL2 =
    new CharacterField(message, HEADERSIZE + 54, 5, "E01AVLFL2");
    fields[14] = fieldE01AVLFL3 =
    new CharacterField(message, HEADERSIZE + 59, 5, "E01AVLFL3");
    fields[15] = fieldE01AVLFL4 =
    new CharacterField(message, HEADERSIZE + 64, 5, "E01AVLFL4");
    fields[16] = fieldE01AVLFL5 =
    new CharacterField(message, HEADERSIZE + 69, 5, "E01AVLFL5");

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
  * Set field E01SRCHTP using a String value.
  */
  public void setE01SRCHTP(String newvalue)
  {
    fieldE01SRCHTP.setString(newvalue);
  }

  /**
  * Get value of field E01SRCHTP as a String.
  */
  public String getE01SRCHTP()
  {
    return fieldE01SRCHTP.getString();
  }

  /**
  * Set field E01NROWOR using a String value.
  */
  public void setE01NROWOR(String newvalue)
  {
    fieldE01NROWOR.setString(newvalue);
  }

  /**
  * Get value of field E01NROWOR as a String.
  */
  public String getE01NROWOR()
  {
    return fieldE01NROWOR.getString();
  }

  /**
  * Set numeric field E01NROWOR using a BigDecimal value.
  */
  public void setE01NROWOR(BigDecimal newvalue)
  {
    fieldE01NROWOR.setBigDecimal(newvalue);
  }

  /**
  * Return value of numeric field E01NROWOR as a BigDecimal.
  */
  public BigDecimal getBigDecimalE01NROWOR()
  {
    return fieldE01NROWOR.getBigDecimal();
  }

  /**
  * Set field E01NROMAT using a String value.
  */
  public void setE01NROMAT(String newvalue)
  {
    fieldE01NROMAT.setString(newvalue);
  }

  /**
  * Get value of field E01NROMAT as a String.
  */
  public String getE01NROMAT()
  {
    return fieldE01NROMAT.getString();
  }

  /**
  * Set numeric field E01NROMAT using a BigDecimal value.
  */
  public void setE01NROMAT(BigDecimal newvalue)
  {
    fieldE01NROMAT.setBigDecimal(newvalue);
  }

  /**
  * Return value of numeric field E01NROMAT as a BigDecimal.
  */
  public BigDecimal getBigDecimalE01NROMAT()
  {
    return fieldE01NROMAT.getBigDecimal();
  }

  /**
  * Set field E01AVLFL1 using a String value.
  */
  public void setE01AVLFL1(String newvalue)
  {
    fieldE01AVLFL1.setString(newvalue);
  }

  /**
  * Get value of field E01AVLFL1 as a String.
  */
  public String getE01AVLFL1()
  {
    return fieldE01AVLFL1.getString();
  }

  /**
  * Set field E01AVLFL2 using a String value.
  */
  public void setE01AVLFL2(String newvalue)
  {
    fieldE01AVLFL2.setString(newvalue);
  }

  /**
  * Get value of field E01AVLFL2 as a String.
  */
  public String getE01AVLFL2()
  {
    return fieldE01AVLFL2.getString();
  }

  /**
  * Set field E01AVLFL3 using a String value.
  */
  public void setE01AVLFL3(String newvalue)
  {
    fieldE01AVLFL3.setString(newvalue);
  }

  /**
  * Get value of field E01AVLFL3 as a String.
  */
  public String getE01AVLFL3()
  {
    return fieldE01AVLFL3.getString();
  }

  /**
  * Set field E01AVLFL4 using a String value.
  */
  public void setE01AVLFL4(String newvalue)
  {
    fieldE01AVLFL4.setString(newvalue);
  }

  /**
  * Get value of field E01AVLFL4 as a String.
  */
  public String getE01AVLFL4()
  {
    return fieldE01AVLFL4.getString();
  }

  /**
  * Set field E01AVLFL5 using a String value.
  */
  public void setE01AVLFL5(String newvalue)
  {
    fieldE01AVLFL5.setString(newvalue);
  }

  /**
  * Get value of field E01AVLFL5 as a String.
  */
  public String getE01AVLFL5()
  {
    return fieldE01AVLFL5.getString();
  }

}
