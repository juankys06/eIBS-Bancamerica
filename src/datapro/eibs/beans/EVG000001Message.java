package datapro.eibs.beans;

import datapro.eibs.sockets.*;
import java.io.*;
import java.math.*;

import java.util.*;

/**
* Class generated by AS/400 CRTCLASS command from EVG000001 physical file definition.
* 
* File level identifier is 1040220181645.
* Record format level identifier is 43F46CDDC04E1.
*/

public class EVG000001Message extends MessageRecord
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
                                     "E01VGNBNK",
                                     "E01VGNCCY",
                                     "E01VGNFTY",
                                     "E01VGNDSC",
                                     "E01VGNVIG",
                                     "E01VGNGI1",
                                     "E01VGNGI2",
                                     "E01VGNDI1",
                                     "E01VGNDI2",
                                     "E01VGNFOC",
                                     "E01VGNFL1",
                                     "E01VGNFL2"
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
                                   "E01VGNBNK",
                                   "E01VGNCCY",
                                   "E01VGNFTY",
                                   "E01VGNDSC",
                                   "E01VGNVIG",
                                   "E01VGNGI1",
                                   "E01VGNGI2",
                                   "E01VGNDI1",
                                   "E01VGNDI2",
                                   "E01VGNFOC",
                                   "E01VGNFL1",
                                   "E01VGNFL2"
                                 };
  final static String fid = "1040220181645";
  final static String rid = "43F46CDDC04E1";
  final static String fmtname = "EVG000001";
  final int FIELDCOUNT = 21;
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
  private CharacterField fieldE01VGNBNK = null;
  private CharacterField fieldE01VGNCCY = null;
  private CharacterField fieldE01VGNFTY = null;
  private CharacterField fieldE01VGNDSC = null;
  private DecimalField fieldE01VGNVIG = null;
  private DecimalField fieldE01VGNGI1 = null;
  private DecimalField fieldE01VGNGI2 = null;
  private DecimalField fieldE01VGNDI1 = null;
  private DecimalField fieldE01VGNDI2 = null;
  private CharacterField fieldE01VGNFOC = null;
  private CharacterField fieldE01VGNFL1 = null;
  private CharacterField fieldE01VGNFL2 = null;

  /**
  * Constructor for EVG000001Message.
  */
  public EVG000001Message()
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
    recordsize = 147;
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
    fields[9] = fieldE01VGNBNK =
    new CharacterField(message, HEADERSIZE + 42, 2, "E01VGNBNK");
    fields[10] = fieldE01VGNCCY =
    new CharacterField(message, HEADERSIZE + 44, 3, "E01VGNCCY");
    fields[11] = fieldE01VGNFTY =
    new CharacterField(message, HEADERSIZE + 47, 1, "E01VGNFTY");
    fields[12] = fieldE01VGNDSC =
    new CharacterField(message, HEADERSIZE + 48, 35, "E01VGNDSC");
    fields[13] = fieldE01VGNVIG =
    new DecimalField(message, HEADERSIZE + 83, 17, 0, "E01VGNVIG");
    fields[14] = fieldE01VGNGI1 =
    new DecimalField(message, HEADERSIZE + 100, 17, 0, "E01VGNGI1");
    fields[15] = fieldE01VGNGI2 =
    new DecimalField(message, HEADERSIZE + 117, 17, 0, "E01VGNGI2");
    fields[16] = fieldE01VGNDI1 =
    new DecimalField(message, HEADERSIZE + 134, 5, 0, "E01VGNDI1");
    fields[17] = fieldE01VGNDI2 =
    new DecimalField(message, HEADERSIZE + 139, 5, 0, "E01VGNDI2");
    fields[18] = fieldE01VGNFOC =
    new CharacterField(message, HEADERSIZE + 144, 1, "E01VGNFOC");
    fields[19] = fieldE01VGNFL1 =
    new CharacterField(message, HEADERSIZE + 145, 1, "E01VGNFL1");
    fields[20] = fieldE01VGNFL2 =
    new CharacterField(message, HEADERSIZE + 146, 1, "E01VGNFL2");

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
  * Set field E01VGNBNK using a String value.
  */
  public void setE01VGNBNK(String newvalue)
  {
    fieldE01VGNBNK.setString(newvalue);
  }

  /**
  * Get value of field E01VGNBNK as a String.
  */
  public String getE01VGNBNK()
  {
    return fieldE01VGNBNK.getString();
  }

  /**
  * Set field E01VGNCCY using a String value.
  */
  public void setE01VGNCCY(String newvalue)
  {
    fieldE01VGNCCY.setString(newvalue);
  }

  /**
  * Get value of field E01VGNCCY as a String.
  */
  public String getE01VGNCCY()
  {
    return fieldE01VGNCCY.getString();
  }

  /**
  * Set field E01VGNFTY using a String value.
  */
  public void setE01VGNFTY(String newvalue)
  {
    fieldE01VGNFTY.setString(newvalue);
  }

  /**
  * Get value of field E01VGNFTY as a String.
  */
  public String getE01VGNFTY()
  {
    return fieldE01VGNFTY.getString();
  }

  /**
  * Set field E01VGNDSC using a String value.
  */
  public void setE01VGNDSC(String newvalue)
  {
    fieldE01VGNDSC.setString(newvalue);
  }

  /**
  * Get value of field E01VGNDSC as a String.
  */
  public String getE01VGNDSC()
  {
    return fieldE01VGNDSC.getString();
  }

  /**
  * Set field E01VGNVIG using a String value.
  */
  public void setE01VGNVIG(String newvalue)
  {
    fieldE01VGNVIG.setString(newvalue);
  }

  /**
  * Get value of field E01VGNVIG as a String.
  */
  public String getE01VGNVIG()
  {
    return fieldE01VGNVIG.getString();
  }

  /**
  * Set numeric field E01VGNVIG using a BigDecimal value.
  */
  public void setE01VGNVIG(BigDecimal newvalue)
  {
    fieldE01VGNVIG.setBigDecimal(newvalue);
  }

  /**
  * Return value of numeric field E01VGNVIG as a BigDecimal.
  */
  public BigDecimal getBigDecimalE01VGNVIG()
  {
    return fieldE01VGNVIG.getBigDecimal();
  }

  /**
  * Set field E01VGNGI1 using a String value.
  */
  public void setE01VGNGI1(String newvalue)
  {
    fieldE01VGNGI1.setString(newvalue);
  }

  /**
  * Get value of field E01VGNGI1 as a String.
  */
  public String getE01VGNGI1()
  {
    return fieldE01VGNGI1.getString();
  }

  /**
  * Set numeric field E01VGNGI1 using a BigDecimal value.
  */
  public void setE01VGNGI1(BigDecimal newvalue)
  {
    fieldE01VGNGI1.setBigDecimal(newvalue);
  }

  /**
  * Return value of numeric field E01VGNGI1 as a BigDecimal.
  */
  public BigDecimal getBigDecimalE01VGNGI1()
  {
    return fieldE01VGNGI1.getBigDecimal();
  }

  /**
  * Set field E01VGNGI2 using a String value.
  */
  public void setE01VGNGI2(String newvalue)
  {
    fieldE01VGNGI2.setString(newvalue);
  }

  /**
  * Get value of field E01VGNGI2 as a String.
  */
  public String getE01VGNGI2()
  {
    return fieldE01VGNGI2.getString();
  }

  /**
  * Set numeric field E01VGNGI2 using a BigDecimal value.
  */
  public void setE01VGNGI2(BigDecimal newvalue)
  {
    fieldE01VGNGI2.setBigDecimal(newvalue);
  }

  /**
  * Return value of numeric field E01VGNGI2 as a BigDecimal.
  */
  public BigDecimal getBigDecimalE01VGNGI2()
  {
    return fieldE01VGNGI2.getBigDecimal();
  }

  /**
  * Set field E01VGNDI1 using a String value.
  */
  public void setE01VGNDI1(String newvalue)
  {
    fieldE01VGNDI1.setString(newvalue);
  }

  /**
  * Get value of field E01VGNDI1 as a String.
  */
  public String getE01VGNDI1()
  {
    return fieldE01VGNDI1.getString();
  }

  /**
  * Set numeric field E01VGNDI1 using a BigDecimal value.
  */
  public void setE01VGNDI1(BigDecimal newvalue)
  {
    fieldE01VGNDI1.setBigDecimal(newvalue);
  }

  /**
  * Return value of numeric field E01VGNDI1 as a BigDecimal.
  */
  public BigDecimal getBigDecimalE01VGNDI1()
  {
    return fieldE01VGNDI1.getBigDecimal();
  }

  /**
  * Set field E01VGNDI2 using a String value.
  */
  public void setE01VGNDI2(String newvalue)
  {
    fieldE01VGNDI2.setString(newvalue);
  }

  /**
  * Get value of field E01VGNDI2 as a String.
  */
  public String getE01VGNDI2()
  {
    return fieldE01VGNDI2.getString();
  }

  /**
  * Set numeric field E01VGNDI2 using a BigDecimal value.
  */
  public void setE01VGNDI2(BigDecimal newvalue)
  {
    fieldE01VGNDI2.setBigDecimal(newvalue);
  }

  /**
  * Return value of numeric field E01VGNDI2 as a BigDecimal.
  */
  public BigDecimal getBigDecimalE01VGNDI2()
  {
    return fieldE01VGNDI2.getBigDecimal();
  }

  /**
  * Set field E01VGNFOC using a String value.
  */
  public void setE01VGNFOC(String newvalue)
  {
    fieldE01VGNFOC.setString(newvalue);
  }

  /**
  * Get value of field E01VGNFOC as a String.
  */
  public String getE01VGNFOC()
  {
    return fieldE01VGNFOC.getString();
  }

  /**
  * Set field E01VGNFL1 using a String value.
  */
  public void setE01VGNFL1(String newvalue)
  {
    fieldE01VGNFL1.setString(newvalue);
  }

  /**
  * Get value of field E01VGNFL1 as a String.
  */
  public String getE01VGNFL1()
  {
    return fieldE01VGNFL1.getString();
  }

  /**
  * Set field E01VGNFL2 using a String value.
  */
  public void setE01VGNFL2(String newvalue)
  {
    fieldE01VGNFL2.setString(newvalue);
  }

  /**
  * Get value of field E01VGNFL2 as a String.
  */
  public String getE01VGNFL2()
  {
    return fieldE01VGNFL2.getString();
  }

}
