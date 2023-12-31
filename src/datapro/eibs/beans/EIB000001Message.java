package datapro.eibs.beans;

import datapro.eibs.sockets.*;
import java.io.*;
import java.math.*;

import java.util.*;

/**
* Class generated by AS/400 CRTCLASS command from EIB000001 physical file definition.
* 
* File level identifier is 1040608120851.
* Record format level identifier is 480351976AF60.
*/

public class EIB000001Message extends MessageRecord
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
                                     "E01IBTDFB",
                                     "E01IBTDFC",
                                     "E01IBTDFA",
                                     "E01IBTDFF",
                                     "E01IBTDTB",
                                     "E01IBTDTA",
                                     "E01IBTDTF",
                                     "E01IBTENT",
                                     "E01INCBRN"
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
                                   "E01IBTDFB",
                                   "E01IBTDFC",
                                   "E01IBTDFA",
                                   "E01IBTDFF",
                                   "E01IBTDTB",
                                   "E01IBTDTA",
                                   "E01IBTDTF",
                                   "E01IBTENT",
                                   "E01INCBRN"
                                 };
  final static String fid = "1040608120851";
  final static String rid = "480351976AF60";
  final static String fmtname = "EIB000001";
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
  private CharacterField fieldE01IBTDFB = null;
  private CharacterField fieldE01IBTDFC = null;
  private DecimalField fieldE01IBTDFA = null;
  private CharacterField fieldE01IBTDFF = null;
  private CharacterField fieldE01IBTDTB = null;
  private DecimalField fieldE01IBTDTA = null;
  private CharacterField fieldE01IBTDTF = null;
  private CharacterField fieldE01IBTENT = null;
  private CharacterField fieldE01INCBRN = null;

  /**
  * Constructor for EIB000001Message.
  */
  public EIB000001Message()
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
    recordsize = 87;
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
    fields[9] = fieldE01IBTDFB =
    new CharacterField(message, HEADERSIZE + 42, 2, "E01IBTDFB");
    fields[10] = fieldE01IBTDFC =
    new CharacterField(message, HEADERSIZE + 44, 3, "E01IBTDFC");
    fields[11] = fieldE01IBTDFA =
    new DecimalField(message, HEADERSIZE + 47, 17, 0, "E01IBTDFA");
    fields[12] = fieldE01IBTDFF =
    new CharacterField(message, HEADERSIZE + 64, 1, "E01IBTDFF");
    fields[13] = fieldE01IBTDTB =
    new CharacterField(message, HEADERSIZE + 65, 2, "E01IBTDTB");
    fields[14] = fieldE01IBTDTA =
    new DecimalField(message, HEADERSIZE + 67, 17, 0, "E01IBTDTA");
    fields[15] = fieldE01IBTDTF =
    new CharacterField(message, HEADERSIZE + 84, 1, "E01IBTDTF");
    fields[16] = fieldE01IBTENT =
    new CharacterField(message, HEADERSIZE + 85, 1, "E01IBTENT");
    fields[17] = fieldE01INCBRN =
    new CharacterField(message, HEADERSIZE + 86, 1, "E01INCBRN");

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
  * Set field E01IBTDFB using a String value.
  */
  public void setE01IBTDFB(String newvalue)
  {
    fieldE01IBTDFB.setString(newvalue);
  }

  /**
  * Get value of field E01IBTDFB as a String.
  */
  public String getE01IBTDFB()
  {
    return fieldE01IBTDFB.getString();
  }

  /**
  * Set field E01IBTDFC using a String value.
  */
  public void setE01IBTDFC(String newvalue)
  {
    fieldE01IBTDFC.setString(newvalue);
  }

  /**
  * Get value of field E01IBTDFC as a String.
  */
  public String getE01IBTDFC()
  {
    return fieldE01IBTDFC.getString();
  }

  /**
  * Set field E01IBTDFA using a String value.
  */
  public void setE01IBTDFA(String newvalue)
  {
    fieldE01IBTDFA.setString(newvalue);
  }

  /**
  * Get value of field E01IBTDFA as a String.
  */
  public String getE01IBTDFA()
  {
    return fieldE01IBTDFA.getString();
  }

  /**
  * Set numeric field E01IBTDFA using a BigDecimal value.
  */
  public void setE01IBTDFA(BigDecimal newvalue)
  {
    fieldE01IBTDFA.setBigDecimal(newvalue);
  }

  /**
  * Return value of numeric field E01IBTDFA as a BigDecimal.
  */
  public BigDecimal getBigDecimalE01IBTDFA()
  {
    return fieldE01IBTDFA.getBigDecimal();
  }

  /**
  * Set field E01IBTDFF using a String value.
  */
  public void setE01IBTDFF(String newvalue)
  {
    fieldE01IBTDFF.setString(newvalue);
  }

  /**
  * Get value of field E01IBTDFF as a String.
  */
  public String getE01IBTDFF()
  {
    return fieldE01IBTDFF.getString();
  }

  /**
  * Set field E01IBTDTB using a String value.
  */
  public void setE01IBTDTB(String newvalue)
  {
    fieldE01IBTDTB.setString(newvalue);
  }

  /**
  * Get value of field E01IBTDTB as a String.
  */
  public String getE01IBTDTB()
  {
    return fieldE01IBTDTB.getString();
  }

  /**
  * Set field E01IBTDTA using a String value.
  */
  public void setE01IBTDTA(String newvalue)
  {
    fieldE01IBTDTA.setString(newvalue);
  }

  /**
  * Get value of field E01IBTDTA as a String.
  */
  public String getE01IBTDTA()
  {
    return fieldE01IBTDTA.getString();
  }

  /**
  * Set numeric field E01IBTDTA using a BigDecimal value.
  */
  public void setE01IBTDTA(BigDecimal newvalue)
  {
    fieldE01IBTDTA.setBigDecimal(newvalue);
  }

  /**
  * Return value of numeric field E01IBTDTA as a BigDecimal.
  */
  public BigDecimal getBigDecimalE01IBTDTA()
  {
    return fieldE01IBTDTA.getBigDecimal();
  }

  /**
  * Set field E01IBTDTF using a String value.
  */
  public void setE01IBTDTF(String newvalue)
  {
    fieldE01IBTDTF.setString(newvalue);
  }

  /**
  * Get value of field E01IBTDTF as a String.
  */
  public String getE01IBTDTF()
  {
    return fieldE01IBTDTF.getString();
  }

  /**
  * Set field E01IBTENT using a String value.
  */
  public void setE01IBTENT(String newvalue)
  {
    fieldE01IBTENT.setString(newvalue);
  }

  /**
  * Get value of field E01IBTENT as a String.
  */
  public String getE01IBTENT()
  {
    return fieldE01IBTENT.getString();
  }

  /**
  * Set field E01INCBRN using a String value.
  */
  public void setE01INCBRN(String newvalue)
  {
    fieldE01INCBRN.setString(newvalue);
  }

  /**
  * Get value of field E01INCBRN as a String.
  */
  public String getE01INCBRN()
  {
    return fieldE01INCBRN.getString();
  }

}
