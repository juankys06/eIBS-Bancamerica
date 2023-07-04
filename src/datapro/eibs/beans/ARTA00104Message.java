package datapro.eibs.beans;

import datapro.eibs.sockets.*;
import java.io.*;
import java.math.*;

import java.util.*;

/**
* Class generated by AS/400 CRTCLASS command from ARTA00104 physical file definition.
* 
* File level identifier is 1040520160408.
* Record format level identifier is 592A463438E83.
*/

public class ARTA00104Message extends MessageRecord
{
  final static String fldnames[] = {
                                     "H04USERID",
                                     "H04PROGRM",
                                     "H04TIMSYS",
                                     "H04SCRCOD",
                                     "H04OPECOD",
                                     "H04FLGMAS",
                                     "H04FLGWK1",
                                     "H04FLGWK2",
                                     "H04FLGWK3",
                                     "E04DEAACC",
                                     "E04NUMGAR",
                                     "E04COLDSC",
                                     "E04COLAMT"
                                   };
  final static String tnames[] = {
                                   "H04USERID",
                                   "H04PROGRM",
                                   "H04TIMSYS",
                                   "H04SCRCOD",
                                   "H04OPECOD",
                                   "H04FLGMAS",
                                   "H04FLGWK1",
                                   "H04FLGWK2",
                                   "H04FLGWK3",
                                   "E04DEAACC",
                                   "E04NUMGAR",
                                   "E04COLDSC",
                                   "E04COLAMT"
                                 };
  final static String fid = "1040520160408";
  final static String rid = "592A463438E83";
  final static String fmtname = "ARTA00104";
  final int FIELDCOUNT = 13;
  private static Hashtable tlookup = new Hashtable();
  private CharacterField fieldH04USERID = null;
  private CharacterField fieldH04PROGRM = null;
  private CharacterField fieldH04TIMSYS = null;
  private CharacterField fieldH04SCRCOD = null;
  private CharacterField fieldH04OPECOD = null;
  private CharacterField fieldH04FLGMAS = null;
  private CharacterField fieldH04FLGWK1 = null;
  private CharacterField fieldH04FLGWK2 = null;
  private CharacterField fieldH04FLGWK3 = null;
  private DecimalField fieldE04DEAACC = null;
  private DecimalField fieldE04NUMGAR = null;
  private CharacterField fieldE04COLDSC = null;
  private DecimalField fieldE04COLAMT = null;

  /**
  * Constructor for ARTA00104Message.
  */
  public ARTA00104Message()
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
    recordsize = 112;
    fileid = fid;
    recordid = rid;
    message = new byte[getByteLength()];
    formatname = fmtname;
    fieldnames = fldnames;
    tagnames = tnames;
    fields = new MessageField[FIELDCOUNT];

    fields[0] = fieldH04USERID =
    new CharacterField(message, HEADERSIZE + 0, 10, "H04USERID");
    fields[1] = fieldH04PROGRM =
    new CharacterField(message, HEADERSIZE + 10, 10, "H04PROGRM");
    fields[2] = fieldH04TIMSYS =
    new CharacterField(message, HEADERSIZE + 20, 12, "H04TIMSYS");
    fields[3] = fieldH04SCRCOD =
    new CharacterField(message, HEADERSIZE + 32, 2, "H04SCRCOD");
    fields[4] = fieldH04OPECOD =
    new CharacterField(message, HEADERSIZE + 34, 4, "H04OPECOD");
    fields[5] = fieldH04FLGMAS =
    new CharacterField(message, HEADERSIZE + 38, 1, "H04FLGMAS");
    fields[6] = fieldH04FLGWK1 =
    new CharacterField(message, HEADERSIZE + 39, 1, "H04FLGWK1");
    fields[7] = fieldH04FLGWK2 =
    new CharacterField(message, HEADERSIZE + 40, 1, "H04FLGWK2");
    fields[8] = fieldH04FLGWK3 =
    new CharacterField(message, HEADERSIZE + 41, 1, "H04FLGWK3");
    fields[9] = fieldE04DEAACC =
    new DecimalField(message, HEADERSIZE + 42, 10, 0, "E04DEAACC");
    fields[10] = fieldE04NUMGAR =
    new DecimalField(message, HEADERSIZE + 52, 10, 0, "E04NUMGAR");
    fields[11] = fieldE04COLDSC =
    new CharacterField(message, HEADERSIZE + 62, 35, "E04COLDSC");
    fields[12] = fieldE04COLAMT =
    new DecimalField(message, HEADERSIZE + 97, 15, 2, "E04COLAMT");

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
  * Set field H04USERID using a String value.
  */
  public void setH04USERID(String newvalue)
  {
    fieldH04USERID.setString(newvalue);
  }

  /**
  * Get value of field H04USERID as a String.
  */
  public String getH04USERID()
  {
    return fieldH04USERID.getString();
  }

  /**
  * Set field H04PROGRM using a String value.
  */
  public void setH04PROGRM(String newvalue)
  {
    fieldH04PROGRM.setString(newvalue);
  }

  /**
  * Get value of field H04PROGRM as a String.
  */
  public String getH04PROGRM()
  {
    return fieldH04PROGRM.getString();
  }

  /**
  * Set field H04TIMSYS using a String value.
  */
  public void setH04TIMSYS(String newvalue)
  {
    fieldH04TIMSYS.setString(newvalue);
  }

  /**
  * Get value of field H04TIMSYS as a String.
  */
  public String getH04TIMSYS()
  {
    return fieldH04TIMSYS.getString();
  }

  /**
  * Set field H04SCRCOD using a String value.
  */
  public void setH04SCRCOD(String newvalue)
  {
    fieldH04SCRCOD.setString(newvalue);
  }

  /**
  * Get value of field H04SCRCOD as a String.
  */
  public String getH04SCRCOD()
  {
    return fieldH04SCRCOD.getString();
  }

  /**
  * Set field H04OPECOD using a String value.
  */
  public void setH04OPECOD(String newvalue)
  {
    fieldH04OPECOD.setString(newvalue);
  }

  /**
  * Get value of field H04OPECOD as a String.
  */
  public String getH04OPECOD()
  {
    return fieldH04OPECOD.getString();
  }

  /**
  * Set field H04FLGMAS using a String value.
  */
  public void setH04FLGMAS(String newvalue)
  {
    fieldH04FLGMAS.setString(newvalue);
  }

  /**
  * Get value of field H04FLGMAS as a String.
  */
  public String getH04FLGMAS()
  {
    return fieldH04FLGMAS.getString();
  }

  /**
  * Set field H04FLGWK1 using a String value.
  */
  public void setH04FLGWK1(String newvalue)
  {
    fieldH04FLGWK1.setString(newvalue);
  }

  /**
  * Get value of field H04FLGWK1 as a String.
  */
  public String getH04FLGWK1()
  {
    return fieldH04FLGWK1.getString();
  }

  /**
  * Set field H04FLGWK2 using a String value.
  */
  public void setH04FLGWK2(String newvalue)
  {
    fieldH04FLGWK2.setString(newvalue);
  }

  /**
  * Get value of field H04FLGWK2 as a String.
  */
  public String getH04FLGWK2()
  {
    return fieldH04FLGWK2.getString();
  }

  /**
  * Set field H04FLGWK3 using a String value.
  */
  public void setH04FLGWK3(String newvalue)
  {
    fieldH04FLGWK3.setString(newvalue);
  }

  /**
  * Get value of field H04FLGWK3 as a String.
  */
  public String getH04FLGWK3()
  {
    return fieldH04FLGWK3.getString();
  }

  /**
  * Set field E04DEAACC using a String value.
  */
  public void setE04DEAACC(String newvalue)
  {
    fieldE04DEAACC.setString(newvalue);
  }

  /**
  * Get value of field E04DEAACC as a String.
  */
  public String getE04DEAACC()
  {
    return fieldE04DEAACC.getString();
  }

  /**
  * Set numeric field E04DEAACC using a BigDecimal value.
  */
  public void setE04DEAACC(BigDecimal newvalue)
  {
    fieldE04DEAACC.setBigDecimal(newvalue);
  }

  /**
  * Return value of numeric field E04DEAACC as a BigDecimal.
  */
  public BigDecimal getBigDecimalE04DEAACC()
  {
    return fieldE04DEAACC.getBigDecimal();
  }

  /**
  * Set field E04NUMGAR using a String value.
  */
  public void setE04NUMGAR(String newvalue)
  {
    fieldE04NUMGAR.setString(newvalue);
  }

  /**
  * Get value of field E04NUMGAR as a String.
  */
  public String getE04NUMGAR()
  {
    return fieldE04NUMGAR.getString();
  }

  /**
  * Set numeric field E04NUMGAR using a BigDecimal value.
  */
  public void setE04NUMGAR(BigDecimal newvalue)
  {
    fieldE04NUMGAR.setBigDecimal(newvalue);
  }

  /**
  * Return value of numeric field E04NUMGAR as a BigDecimal.
  */
  public BigDecimal getBigDecimalE04NUMGAR()
  {
    return fieldE04NUMGAR.getBigDecimal();
  }

  /**
  * Set field E04COLDSC using a String value.
  */
  public void setE04COLDSC(String newvalue)
  {
    fieldE04COLDSC.setString(newvalue);
  }

  /**
  * Get value of field E04COLDSC as a String.
  */
  public String getE04COLDSC()
  {
    return fieldE04COLDSC.getString();
  }

  /**
  * Set field E04COLAMT using a String value.
  */
  public void setE04COLAMT(String newvalue)
  {
    fieldE04COLAMT.setString(newvalue);
  }

  /**
  * Get value of field E04COLAMT as a String.
  */
  public String getE04COLAMT()
  {
    return fieldE04COLAMT.getString();
  }

  /**
  * Set numeric field E04COLAMT using a BigDecimal value.
  */
  public void setE04COLAMT(BigDecimal newvalue)
  {
    fieldE04COLAMT.setBigDecimal(newvalue);
  }

  /**
  * Return value of numeric field E04COLAMT as a BigDecimal.
  */
  public BigDecimal getBigDecimalE04COLAMT()
  {
    return fieldE04COLAMT.getBigDecimal();
  }

}