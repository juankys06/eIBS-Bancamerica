package datapro.eibs.beans;

import datapro.eibs.sockets.*;
import java.io.*;
import java.math.*;

import java.util.*;

/**
* Class generated by AS/400 CRTCLASS command from ECU000005 physical file definition.
* 
* File level identifier is 1050525135111.
* Record format level identifier is 295D53385A45E.
*/

public class ECU000005Message extends MessageRecord
{
  final static String fldnames[] = {
                                     "H05USERID",
                                     "H05PROGRM",
                                     "H05TIMSYS",
                                     "H05SCRCOD",
                                     "H05OPECOD",
                                     "H05FLGMAS",
                                     "H05FLGWK1",
                                     "H05FLGWK2",
                                     "H05FLGWK3",
                                     "E05CUMCUN",
                                     "E05CUSNA1",
                                     "E05CUMRTP",
                                     "E05CUMMAN",
                                     "E05CUMUC5",
                                     "E05CUMUCN",
                                     "E05CUMMA1",
                                     "E05CUMBNI",
                                     "E05CUMTID",
                                     "E05CUMTIN",
                                     "E05CUMPID",
                                     "E05CUMPIN",
                                     "E05CUMDD1",
                                     "E05CUMDD2",
                                     "E05CUMDD3",
                                     "E05CUMD21",
                                     "E05CUMD22",
                                     "E05CUMD23",
                                     "E05CUMFL1",
                                     "E05CUMFL2",
                                     "E05CUMOB1",
                                     "E05CUMOB2",
                                     "E05CUMOB3",
                                     "E05CUMOB4",
                                     "E05CUMOB5",
                                     "E05CUMAC1",
                                     "E05CUMAC2",
                                     "E05CUMAC3",
                                     "E05CUMACT",
                                     "E05CUMACU",
                                     "E05MBMTRM",
                                     "E05MBRTRC",
                                     "E05CUMMA2",
                                     "E05CUMNST",
                                     "E05CUMPOB",
                                     "E05CUMCTY",
                                     "E05REGRDD",
                                     "E05REGRMM",
                                     "E05REGRYY",
                                     "E05CUMMA3",
                                     "E05CUMMA4",
                                     "E05CUMCTR",
                                     "E05CUMINC",
                                     "E05CUMINN",
                                     "E05CUMBNC",
                                     "E05CUMBNN"
                                   };
  final static String tnames[] = {
                                   "H05USERID",
                                   "H05PROGRM",
                                   "H05TIMSYS",
                                   "H05SCRCOD",
                                   "H05OPECOD",
                                   "H05FLGMAS",
                                   "H05FLGWK1",
                                   "H05FLGWK2",
                                   "H05FLGWK3",
                                   "E05CUMCUN",
                                   "E05CUSNA1",
                                   "E05CUMRTP",
                                   "E05CUMMAN",
                                   "E05CUMUC5",
                                   "E05CUMUCN",
                                   "E05CUMMA1",
                                   "E05CUMBNI",
                                   "E05CUMTID",
                                   "E05CUMTIN",
                                   "E05CUMPID",
                                   "E05CUMPIN",
                                   "E05CUMDD1",
                                   "E05CUMDD2",
                                   "E05CUMDD3",
                                   "E05CUMD21",
                                   "E05CUMD22",
                                   "E05CUMD23",
                                   "E05CUMFL1",
                                   "E05CUMFL2",
                                   "E05CUMOB1",
                                   "E05CUMOB2",
                                   "E05CUMOB3",
                                   "E05CUMOB4",
                                   "E05CUMOB5",
                                   "E05CUMAC1",
                                   "E05CUMAC2",
                                   "E05CUMAC3",
                                   "E05CUMACT",
                                   "E05CUMACU",
                                   "E05MBMTRM",
                                   "E05MBRTRC",
                                   "E05CUMMA2",
                                   "E05CUMNST",
                                   "E05CUMPOB",
                                   "E05CUMCTY",
                                   "E05REGRDD",
                                   "E05REGRMM",
                                   "E05REGRYY",
                                   "E05CUMMA3",
                                   "E05CUMMA4",
                                   "E05CUMCTR",
                                   "E05CUMINC",
                                   "E05CUMINN",
                                   "E05CUMBNC",
                                   "E05CUMBNN"
                                 };
  final static String fid = "1050525135111";
  final static String rid = "295D53385A45E";
  final static String fmtname = "ECU000005";
  final int FIELDCOUNT = 55;
  private static Hashtable tlookup = new Hashtable();
  private CharacterField fieldH05USERID = null;
  private CharacterField fieldH05PROGRM = null;
  private CharacterField fieldH05TIMSYS = null;
  private CharacterField fieldH05SCRCOD = null;
  private CharacterField fieldH05OPECOD = null;
  private CharacterField fieldH05FLGMAS = null;
  private CharacterField fieldH05FLGWK1 = null;
  private CharacterField fieldH05FLGWK2 = null;
  private CharacterField fieldH05FLGWK3 = null;
  private DecimalField fieldE05CUMCUN = null;
  private CharacterField fieldE05CUSNA1 = null;
  private CharacterField fieldE05CUMRTP = null;
  private DecimalField fieldE05CUMMAN = null;
  private CharacterField fieldE05CUMUC5 = null;
  private CharacterField fieldE05CUMUCN = null;
  private CharacterField fieldE05CUMMA1 = null;
  private CharacterField fieldE05CUMBNI = null;
  private CharacterField fieldE05CUMTID = null;
  private CharacterField fieldE05CUMTIN = null;
  private CharacterField fieldE05CUMPID = null;
  private CharacterField fieldE05CUMPIN = null;
  private DecimalField fieldE05CUMDD1 = null;
  private DecimalField fieldE05CUMDD2 = null;
  private DecimalField fieldE05CUMDD3 = null;
  private DecimalField fieldE05CUMD21 = null;
  private DecimalField fieldE05CUMD22 = null;
  private DecimalField fieldE05CUMD23 = null;
  private CharacterField fieldE05CUMFL1 = null;
  private CharacterField fieldE05CUMFL2 = null;
  private CharacterField fieldE05CUMOB1 = null;
  private CharacterField fieldE05CUMOB2 = null;
  private CharacterField fieldE05CUMOB3 = null;
  private CharacterField fieldE05CUMOB4 = null;
  private CharacterField fieldE05CUMOB5 = null;
  private DecimalField fieldE05CUMAC1 = null;
  private DecimalField fieldE05CUMAC2 = null;
  private DecimalField fieldE05CUMAC3 = null;
  private DecimalField fieldE05CUMACT = null;
  private CharacterField fieldE05CUMACU = null;
  private DecimalField fieldE05MBMTRM = null;
  private CharacterField fieldE05MBRTRC = null;
  private CharacterField fieldE05CUMMA2 = null;
  private DecimalField fieldE05CUMNST = null;
  private CharacterField fieldE05CUMPOB = null;
  private CharacterField fieldE05CUMCTY = null;
  private DecimalField fieldE05REGRDD = null;
  private DecimalField fieldE05REGRMM = null;
  private DecimalField fieldE05REGRYY = null;
  private CharacterField fieldE05CUMMA3 = null;
  private CharacterField fieldE05CUMMA4 = null;
  private CharacterField fieldE05CUMCTR = null;
  private CharacterField fieldE05CUMINC = null;
  private CharacterField fieldE05CUMINN = null;
  private CharacterField fieldE05CUMBNC = null;
  private CharacterField fieldE05CUMBNN = null;

  /**
  * Constructor for ECU000005Message.
  */
  public ECU000005Message()
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
    recordsize = 992;
    fileid = fid;
    recordid = rid;
    message = new byte[getByteLength()];
    formatname = fmtname;
    fieldnames = fldnames;
    tagnames = tnames;
    fields = new MessageField[FIELDCOUNT];

    fields[0] = fieldH05USERID =
    new CharacterField(message, HEADERSIZE + 0, 10, "H05USERID");
    fields[1] = fieldH05PROGRM =
    new CharacterField(message, HEADERSIZE + 10, 10, "H05PROGRM");
    fields[2] = fieldH05TIMSYS =
    new CharacterField(message, HEADERSIZE + 20, 12, "H05TIMSYS");
    fields[3] = fieldH05SCRCOD =
    new CharacterField(message, HEADERSIZE + 32, 2, "H05SCRCOD");
    fields[4] = fieldH05OPECOD =
    new CharacterField(message, HEADERSIZE + 34, 4, "H05OPECOD");
    fields[5] = fieldH05FLGMAS =
    new CharacterField(message, HEADERSIZE + 38, 1, "H05FLGMAS");
    fields[6] = fieldH05FLGWK1 =
    new CharacterField(message, HEADERSIZE + 39, 1, "H05FLGWK1");
    fields[7] = fieldH05FLGWK2 =
    new CharacterField(message, HEADERSIZE + 40, 1, "H05FLGWK2");
    fields[8] = fieldH05FLGWK3 =
    new CharacterField(message, HEADERSIZE + 41, 1, "H05FLGWK3");
    fields[9] = fieldE05CUMCUN =
    new DecimalField(message, HEADERSIZE + 42, 10, 0, "E05CUMCUN");
    fields[10] = fieldE05CUSNA1 =
    new CharacterField(message, HEADERSIZE + 52, 45, "E05CUSNA1");
    fields[11] = fieldE05CUMRTP =
    new CharacterField(message, HEADERSIZE + 97, 1, "E05CUMRTP");
    fields[12] = fieldE05CUMMAN =
    new DecimalField(message, HEADERSIZE + 98, 3, 0, "E05CUMMAN");
    fields[13] = fieldE05CUMUC5 =
    new CharacterField(message, HEADERSIZE + 101, 4, "E05CUMUC5");
    fields[14] = fieldE05CUMUCN =
    new CharacterField(message, HEADERSIZE + 105, 35, "E05CUMUCN");
    fields[15] = fieldE05CUMMA1 =
    new CharacterField(message, HEADERSIZE + 140, 45, "E05CUMMA1");
    fields[16] = fieldE05CUMBNI =
    new CharacterField(message, HEADERSIZE + 185, 15, "E05CUMBNI");
    fields[17] = fieldE05CUMTID =
    new CharacterField(message, HEADERSIZE + 200, 4, "E05CUMTID");
    fields[18] = fieldE05CUMTIN =
    new CharacterField(message, HEADERSIZE + 204, 35, "E05CUMTIN");
    fields[19] = fieldE05CUMPID =
    new CharacterField(message, HEADERSIZE + 239, 4, "E05CUMPID");
    fields[20] = fieldE05CUMPIN =
    new CharacterField(message, HEADERSIZE + 243, 35, "E05CUMPIN");
    fields[21] = fieldE05CUMDD1 =
    new DecimalField(message, HEADERSIZE + 278, 3, 0, "E05CUMDD1");
    fields[22] = fieldE05CUMDD2 =
    new DecimalField(message, HEADERSIZE + 281, 3, 0, "E05CUMDD2");
    fields[23] = fieldE05CUMDD3 =
    new DecimalField(message, HEADERSIZE + 284, 3, 0, "E05CUMDD3");
    fields[24] = fieldE05CUMD21 =
    new DecimalField(message, HEADERSIZE + 287, 3, 0, "E05CUMD21");
    fields[25] = fieldE05CUMD22 =
    new DecimalField(message, HEADERSIZE + 290, 3, 0, "E05CUMD22");
    fields[26] = fieldE05CUMD23 =
    new DecimalField(message, HEADERSIZE + 293, 3, 0, "E05CUMD23");
    fields[27] = fieldE05CUMFL1 =
    new CharacterField(message, HEADERSIZE + 296, 1, "E05CUMFL1");
    fields[28] = fieldE05CUMFL2 =
    new CharacterField(message, HEADERSIZE + 297, 1, "E05CUMFL2");
    fields[29] = fieldE05CUMOB1 =
    new CharacterField(message, HEADERSIZE + 298, 80, "E05CUMOB1");
    fields[30] = fieldE05CUMOB2 =
    new CharacterField(message, HEADERSIZE + 378, 80, "E05CUMOB2");
    fields[31] = fieldE05CUMOB3 =
    new CharacterField(message, HEADERSIZE + 458, 80, "E05CUMOB3");
    fields[32] = fieldE05CUMOB4 =
    new CharacterField(message, HEADERSIZE + 538, 80, "E05CUMOB4");
    fields[33] = fieldE05CUMOB5 =
    new CharacterField(message, HEADERSIZE + 618, 80, "E05CUMOB5");
    fields[34] = fieldE05CUMAC1 =
    new DecimalField(message, HEADERSIZE + 698, 3, 0, "E05CUMAC1");
    fields[35] = fieldE05CUMAC2 =
    new DecimalField(message, HEADERSIZE + 701, 3, 0, "E05CUMAC2");
    fields[36] = fieldE05CUMAC3 =
    new DecimalField(message, HEADERSIZE + 704, 3, 0, "E05CUMAC3");
    fields[37] = fieldE05CUMACT =
    new DecimalField(message, HEADERSIZE + 707, 7, 0, "E05CUMACT");
    fields[38] = fieldE05CUMACU =
    new CharacterField(message, HEADERSIZE + 714, 10, "E05CUMACU");
    fields[39] = fieldE05MBMTRM =
    new DecimalField(message, HEADERSIZE + 724, 5, 0, "E05MBMTRM");
    fields[40] = fieldE05MBRTRC =
    new CharacterField(message, HEADERSIZE + 729, 1, "E05MBRTRC");
    fields[41] = fieldE05CUMMA2 =
    new CharacterField(message, HEADERSIZE + 730, 35, "E05CUMMA2");
    fields[42] = fieldE05CUMNST =
    new DecimalField(message, HEADERSIZE + 765, 10, 0, "E05CUMNST");
    fields[43] = fieldE05CUMPOB =
    new CharacterField(message, HEADERSIZE + 775, 10, "E05CUMPOB");
    fields[44] = fieldE05CUMCTY =
    new CharacterField(message, HEADERSIZE + 785, 30, "E05CUMCTY");
    fields[45] = fieldE05REGRDD =
    new DecimalField(message, HEADERSIZE + 815, 3, 0, "E05REGRDD");
    fields[46] = fieldE05REGRMM =
    new DecimalField(message, HEADERSIZE + 818, 3, 0, "E05REGRMM");
    fields[47] = fieldE05REGRYY =
    new DecimalField(message, HEADERSIZE + 821, 3, 0, "E05REGRYY");
    fields[48] = fieldE05CUMMA3 =
    new CharacterField(message, HEADERSIZE + 824, 35, "E05CUMMA3");
    fields[49] = fieldE05CUMMA4 =
    new CharacterField(message, HEADERSIZE + 859, 35, "E05CUMMA4");
    fields[50] = fieldE05CUMCTR =
    new CharacterField(message, HEADERSIZE + 894, 20, "E05CUMCTR");
    fields[51] = fieldE05CUMINC =
    new CharacterField(message, HEADERSIZE + 914, 4, "E05CUMINC");
    fields[52] = fieldE05CUMINN =
    new CharacterField(message, HEADERSIZE + 918, 35, "E05CUMINN");
    fields[53] = fieldE05CUMBNC =
    new CharacterField(message, HEADERSIZE + 953, 4, "E05CUMBNC");
    fields[54] = fieldE05CUMBNN =
    new CharacterField(message, HEADERSIZE + 957, 35, "E05CUMBNN");

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
  * Set field H05USERID using a String value.
  */
  public void setH05USERID(String newvalue)
  {
    fieldH05USERID.setString(newvalue);
  }

  /**
  * Get value of field H05USERID as a String.
  */
  public String getH05USERID()
  {
    return fieldH05USERID.getString();
  }

  /**
  * Set field H05PROGRM using a String value.
  */
  public void setH05PROGRM(String newvalue)
  {
    fieldH05PROGRM.setString(newvalue);
  }

  /**
  * Get value of field H05PROGRM as a String.
  */
  public String getH05PROGRM()
  {
    return fieldH05PROGRM.getString();
  }

  /**
  * Set field H05TIMSYS using a String value.
  */
  public void setH05TIMSYS(String newvalue)
  {
    fieldH05TIMSYS.setString(newvalue);
  }

  /**
  * Get value of field H05TIMSYS as a String.
  */
  public String getH05TIMSYS()
  {
    return fieldH05TIMSYS.getString();
  }

  /**
  * Set field H05SCRCOD using a String value.
  */
  public void setH05SCRCOD(String newvalue)
  {
    fieldH05SCRCOD.setString(newvalue);
  }

  /**
  * Get value of field H05SCRCOD as a String.
  */
  public String getH05SCRCOD()
  {
    return fieldH05SCRCOD.getString();
  }

  /**
  * Set field H05OPECOD using a String value.
  */
  public void setH05OPECOD(String newvalue)
  {
    fieldH05OPECOD.setString(newvalue);
  }

  /**
  * Get value of field H05OPECOD as a String.
  */
  public String getH05OPECOD()
  {
    return fieldH05OPECOD.getString();
  }

  /**
  * Set field H05FLGMAS using a String value.
  */
  public void setH05FLGMAS(String newvalue)
  {
    fieldH05FLGMAS.setString(newvalue);
  }

  /**
  * Get value of field H05FLGMAS as a String.
  */
  public String getH05FLGMAS()
  {
    return fieldH05FLGMAS.getString();
  }

  /**
  * Set field H05FLGWK1 using a String value.
  */
  public void setH05FLGWK1(String newvalue)
  {
    fieldH05FLGWK1.setString(newvalue);
  }

  /**
  * Get value of field H05FLGWK1 as a String.
  */
  public String getH05FLGWK1()
  {
    return fieldH05FLGWK1.getString();
  }

  /**
  * Set field H05FLGWK2 using a String value.
  */
  public void setH05FLGWK2(String newvalue)
  {
    fieldH05FLGWK2.setString(newvalue);
  }

  /**
  * Get value of field H05FLGWK2 as a String.
  */
  public String getH05FLGWK2()
  {
    return fieldH05FLGWK2.getString();
  }

  /**
  * Set field H05FLGWK3 using a String value.
  */
  public void setH05FLGWK3(String newvalue)
  {
    fieldH05FLGWK3.setString(newvalue);
  }

  /**
  * Get value of field H05FLGWK3 as a String.
  */
  public String getH05FLGWK3()
  {
    return fieldH05FLGWK3.getString();
  }

  /**
  * Set field E05CUMCUN using a String value.
  */
  public void setE05CUMCUN(String newvalue)
  {
    fieldE05CUMCUN.setString(newvalue);
  }

  /**
  * Get value of field E05CUMCUN as a String.
  */
  public String getE05CUMCUN()
  {
    return fieldE05CUMCUN.getString();
  }

  /**
  * Set numeric field E05CUMCUN using a BigDecimal value.
  */
  public void setE05CUMCUN(BigDecimal newvalue)
  {
    fieldE05CUMCUN.setBigDecimal(newvalue);
  }

  /**
  * Return value of numeric field E05CUMCUN as a BigDecimal.
  */
  public BigDecimal getBigDecimalE05CUMCUN()
  {
    return fieldE05CUMCUN.getBigDecimal();
  }

  /**
  * Set field E05CUSNA1 using a String value.
  */
  public void setE05CUSNA1(String newvalue)
  {
    fieldE05CUSNA1.setString(newvalue);
  }

  /**
  * Get value of field E05CUSNA1 as a String.
  */
  public String getE05CUSNA1()
  {
    return fieldE05CUSNA1.getString();
  }

  /**
  * Set field E05CUMRTP using a String value.
  */
  public void setE05CUMRTP(String newvalue)
  {
    fieldE05CUMRTP.setString(newvalue);
  }

  /**
  * Get value of field E05CUMRTP as a String.
  */
  public String getE05CUMRTP()
  {
    return fieldE05CUMRTP.getString();
  }

  /**
  * Set field E05CUMMAN using a String value.
  */
  public void setE05CUMMAN(String newvalue)
  {
    fieldE05CUMMAN.setString(newvalue);
  }

  /**
  * Get value of field E05CUMMAN as a String.
  */
  public String getE05CUMMAN()
  {
    return fieldE05CUMMAN.getString();
  }

  /**
  * Set numeric field E05CUMMAN using a BigDecimal value.
  */
  public void setE05CUMMAN(BigDecimal newvalue)
  {
    fieldE05CUMMAN.setBigDecimal(newvalue);
  }

  /**
  * Return value of numeric field E05CUMMAN as a BigDecimal.
  */
  public BigDecimal getBigDecimalE05CUMMAN()
  {
    return fieldE05CUMMAN.getBigDecimal();
  }

  /**
  * Set field E05CUMUC5 using a String value.
  */
  public void setE05CUMUC5(String newvalue)
  {
    fieldE05CUMUC5.setString(newvalue);
  }

  /**
  * Get value of field E05CUMUC5 as a String.
  */
  public String getE05CUMUC5()
  {
    return fieldE05CUMUC5.getString();
  }

  /**
  * Set field E05CUMUCN using a String value.
  */
  public void setE05CUMUCN(String newvalue)
  {
    fieldE05CUMUCN.setString(newvalue);
  }

  /**
  * Get value of field E05CUMUCN as a String.
  */
  public String getE05CUMUCN()
  {
    return fieldE05CUMUCN.getString();
  }

  /**
  * Set field E05CUMMA1 using a String value.
  */
  public void setE05CUMMA1(String newvalue)
  {
    fieldE05CUMMA1.setString(newvalue);
  }

  /**
  * Get value of field E05CUMMA1 as a String.
  */
  public String getE05CUMMA1()
  {
    return fieldE05CUMMA1.getString();
  }

  /**
  * Set field E05CUMBNI using a String value.
  */
  public void setE05CUMBNI(String newvalue)
  {
    fieldE05CUMBNI.setString(newvalue);
  }

  /**
  * Get value of field E05CUMBNI as a String.
  */
  public String getE05CUMBNI()
  {
    return fieldE05CUMBNI.getString();
  }

  /**
  * Set field E05CUMTID using a String value.
  */
  public void setE05CUMTID(String newvalue)
  {
    fieldE05CUMTID.setString(newvalue);
  }

  /**
  * Get value of field E05CUMTID as a String.
  */
  public String getE05CUMTID()
  {
    return fieldE05CUMTID.getString();
  }

  /**
  * Set field E05CUMTIN using a String value.
  */
  public void setE05CUMTIN(String newvalue)
  {
    fieldE05CUMTIN.setString(newvalue);
  }

  /**
  * Get value of field E05CUMTIN as a String.
  */
  public String getE05CUMTIN()
  {
    return fieldE05CUMTIN.getString();
  }

  /**
  * Set field E05CUMPID using a String value.
  */
  public void setE05CUMPID(String newvalue)
  {
    fieldE05CUMPID.setString(newvalue);
  }

  /**
  * Get value of field E05CUMPID as a String.
  */
  public String getE05CUMPID()
  {
    return fieldE05CUMPID.getString();
  }

  /**
  * Set field E05CUMPIN using a String value.
  */
  public void setE05CUMPIN(String newvalue)
  {
    fieldE05CUMPIN.setString(newvalue);
  }

  /**
  * Get value of field E05CUMPIN as a String.
  */
  public String getE05CUMPIN()
  {
    return fieldE05CUMPIN.getString();
  }

  /**
  * Set field E05CUMDD1 using a String value.
  */
  public void setE05CUMDD1(String newvalue)
  {
    fieldE05CUMDD1.setString(newvalue);
  }

  /**
  * Get value of field E05CUMDD1 as a String.
  */
  public String getE05CUMDD1()
  {
    return fieldE05CUMDD1.getString();
  }

  /**
  * Set numeric field E05CUMDD1 using a BigDecimal value.
  */
  public void setE05CUMDD1(BigDecimal newvalue)
  {
    fieldE05CUMDD1.setBigDecimal(newvalue);
  }

  /**
  * Return value of numeric field E05CUMDD1 as a BigDecimal.
  */
  public BigDecimal getBigDecimalE05CUMDD1()
  {
    return fieldE05CUMDD1.getBigDecimal();
  }

  /**
  * Set field E05CUMDD2 using a String value.
  */
  public void setE05CUMDD2(String newvalue)
  {
    fieldE05CUMDD2.setString(newvalue);
  }

  /**
  * Get value of field E05CUMDD2 as a String.
  */
  public String getE05CUMDD2()
  {
    return fieldE05CUMDD2.getString();
  }

  /**
  * Set numeric field E05CUMDD2 using a BigDecimal value.
  */
  public void setE05CUMDD2(BigDecimal newvalue)
  {
    fieldE05CUMDD2.setBigDecimal(newvalue);
  }

  /**
  * Return value of numeric field E05CUMDD2 as a BigDecimal.
  */
  public BigDecimal getBigDecimalE05CUMDD2()
  {
    return fieldE05CUMDD2.getBigDecimal();
  }

  /**
  * Set field E05CUMDD3 using a String value.
  */
  public void setE05CUMDD3(String newvalue)
  {
    fieldE05CUMDD3.setString(newvalue);
  }

  /**
  * Get value of field E05CUMDD3 as a String.
  */
  public String getE05CUMDD3()
  {
    return fieldE05CUMDD3.getString();
  }

  /**
  * Set numeric field E05CUMDD3 using a BigDecimal value.
  */
  public void setE05CUMDD3(BigDecimal newvalue)
  {
    fieldE05CUMDD3.setBigDecimal(newvalue);
  }

  /**
  * Return value of numeric field E05CUMDD3 as a BigDecimal.
  */
  public BigDecimal getBigDecimalE05CUMDD3()
  {
    return fieldE05CUMDD3.getBigDecimal();
  }

  /**
  * Set field E05CUMD21 using a String value.
  */
  public void setE05CUMD21(String newvalue)
  {
    fieldE05CUMD21.setString(newvalue);
  }

  /**
  * Get value of field E05CUMD21 as a String.
  */
  public String getE05CUMD21()
  {
    return fieldE05CUMD21.getString();
  }

  /**
  * Set numeric field E05CUMD21 using a BigDecimal value.
  */
  public void setE05CUMD21(BigDecimal newvalue)
  {
    fieldE05CUMD21.setBigDecimal(newvalue);
  }

  /**
  * Return value of numeric field E05CUMD21 as a BigDecimal.
  */
  public BigDecimal getBigDecimalE05CUMD21()
  {
    return fieldE05CUMD21.getBigDecimal();
  }

  /**
  * Set field E05CUMD22 using a String value.
  */
  public void setE05CUMD22(String newvalue)
  {
    fieldE05CUMD22.setString(newvalue);
  }

  /**
  * Get value of field E05CUMD22 as a String.
  */
  public String getE05CUMD22()
  {
    return fieldE05CUMD22.getString();
  }

  /**
  * Set numeric field E05CUMD22 using a BigDecimal value.
  */
  public void setE05CUMD22(BigDecimal newvalue)
  {
    fieldE05CUMD22.setBigDecimal(newvalue);
  }

  /**
  * Return value of numeric field E05CUMD22 as a BigDecimal.
  */
  public BigDecimal getBigDecimalE05CUMD22()
  {
    return fieldE05CUMD22.getBigDecimal();
  }

  /**
  * Set field E05CUMD23 using a String value.
  */
  public void setE05CUMD23(String newvalue)
  {
    fieldE05CUMD23.setString(newvalue);
  }

  /**
  * Get value of field E05CUMD23 as a String.
  */
  public String getE05CUMD23()
  {
    return fieldE05CUMD23.getString();
  }

  /**
  * Set numeric field E05CUMD23 using a BigDecimal value.
  */
  public void setE05CUMD23(BigDecimal newvalue)
  {
    fieldE05CUMD23.setBigDecimal(newvalue);
  }

  /**
  * Return value of numeric field E05CUMD23 as a BigDecimal.
  */
  public BigDecimal getBigDecimalE05CUMD23()
  {
    return fieldE05CUMD23.getBigDecimal();
  }

  /**
  * Set field E05CUMFL1 using a String value.
  */
  public void setE05CUMFL1(String newvalue)
  {
    fieldE05CUMFL1.setString(newvalue);
  }

  /**
  * Get value of field E05CUMFL1 as a String.
  */
  public String getE05CUMFL1()
  {
    return fieldE05CUMFL1.getString();
  }

  /**
  * Set field E05CUMFL2 using a String value.
  */
  public void setE05CUMFL2(String newvalue)
  {
    fieldE05CUMFL2.setString(newvalue);
  }

  /**
  * Get value of field E05CUMFL2 as a String.
  */
  public String getE05CUMFL2()
  {
    return fieldE05CUMFL2.getString();
  }

  /**
  * Set field E05CUMOB1 using a String value.
  */
  public void setE05CUMOB1(String newvalue)
  {
    fieldE05CUMOB1.setString(newvalue);
  }

  /**
  * Get value of field E05CUMOB1 as a String.
  */
  public String getE05CUMOB1()
  {
    return fieldE05CUMOB1.getString();
  }

  /**
  * Set field E05CUMOB2 using a String value.
  */
  public void setE05CUMOB2(String newvalue)
  {
    fieldE05CUMOB2.setString(newvalue);
  }

  /**
  * Get value of field E05CUMOB2 as a String.
  */
  public String getE05CUMOB2()
  {
    return fieldE05CUMOB2.getString();
  }

  /**
  * Set field E05CUMOB3 using a String value.
  */
  public void setE05CUMOB3(String newvalue)
  {
    fieldE05CUMOB3.setString(newvalue);
  }

  /**
  * Get value of field E05CUMOB3 as a String.
  */
  public String getE05CUMOB3()
  {
    return fieldE05CUMOB3.getString();
  }

  /**
  * Set field E05CUMOB4 using a String value.
  */
  public void setE05CUMOB4(String newvalue)
  {
    fieldE05CUMOB4.setString(newvalue);
  }

  /**
  * Get value of field E05CUMOB4 as a String.
  */
  public String getE05CUMOB4()
  {
    return fieldE05CUMOB4.getString();
  }

  /**
  * Set field E05CUMOB5 using a String value.
  */
  public void setE05CUMOB5(String newvalue)
  {
    fieldE05CUMOB5.setString(newvalue);
  }

  /**
  * Get value of field E05CUMOB5 as a String.
  */
  public String getE05CUMOB5()
  {
    return fieldE05CUMOB5.getString();
  }

  /**
  * Set field E05CUMAC1 using a String value.
  */
  public void setE05CUMAC1(String newvalue)
  {
    fieldE05CUMAC1.setString(newvalue);
  }

  /**
  * Get value of field E05CUMAC1 as a String.
  */
  public String getE05CUMAC1()
  {
    return fieldE05CUMAC1.getString();
  }

  /**
  * Set numeric field E05CUMAC1 using a BigDecimal value.
  */
  public void setE05CUMAC1(BigDecimal newvalue)
  {
    fieldE05CUMAC1.setBigDecimal(newvalue);
  }

  /**
  * Return value of numeric field E05CUMAC1 as a BigDecimal.
  */
  public BigDecimal getBigDecimalE05CUMAC1()
  {
    return fieldE05CUMAC1.getBigDecimal();
  }

  /**
  * Set field E05CUMAC2 using a String value.
  */
  public void setE05CUMAC2(String newvalue)
  {
    fieldE05CUMAC2.setString(newvalue);
  }

  /**
  * Get value of field E05CUMAC2 as a String.
  */
  public String getE05CUMAC2()
  {
    return fieldE05CUMAC2.getString();
  }

  /**
  * Set numeric field E05CUMAC2 using a BigDecimal value.
  */
  public void setE05CUMAC2(BigDecimal newvalue)
  {
    fieldE05CUMAC2.setBigDecimal(newvalue);
  }

  /**
  * Return value of numeric field E05CUMAC2 as a BigDecimal.
  */
  public BigDecimal getBigDecimalE05CUMAC2()
  {
    return fieldE05CUMAC2.getBigDecimal();
  }

  /**
  * Set field E05CUMAC3 using a String value.
  */
  public void setE05CUMAC3(String newvalue)
  {
    fieldE05CUMAC3.setString(newvalue);
  }

  /**
  * Get value of field E05CUMAC3 as a String.
  */
  public String getE05CUMAC3()
  {
    return fieldE05CUMAC3.getString();
  }

  /**
  * Set numeric field E05CUMAC3 using a BigDecimal value.
  */
  public void setE05CUMAC3(BigDecimal newvalue)
  {
    fieldE05CUMAC3.setBigDecimal(newvalue);
  }

  /**
  * Return value of numeric field E05CUMAC3 as a BigDecimal.
  */
  public BigDecimal getBigDecimalE05CUMAC3()
  {
    return fieldE05CUMAC3.getBigDecimal();
  }

  /**
  * Set field E05CUMACT using a String value.
  */
  public void setE05CUMACT(String newvalue)
  {
    fieldE05CUMACT.setString(newvalue);
  }

  /**
  * Get value of field E05CUMACT as a String.
  */
  public String getE05CUMACT()
  {
    return fieldE05CUMACT.getString();
  }

  /**
  * Set numeric field E05CUMACT using a BigDecimal value.
  */
  public void setE05CUMACT(BigDecimal newvalue)
  {
    fieldE05CUMACT.setBigDecimal(newvalue);
  }

  /**
  * Return value of numeric field E05CUMACT as a BigDecimal.
  */
  public BigDecimal getBigDecimalE05CUMACT()
  {
    return fieldE05CUMACT.getBigDecimal();
  }

  /**
  * Set field E05CUMACU using a String value.
  */
  public void setE05CUMACU(String newvalue)
  {
    fieldE05CUMACU.setString(newvalue);
  }

  /**
  * Get value of field E05CUMACU as a String.
  */
  public String getE05CUMACU()
  {
    return fieldE05CUMACU.getString();
  }

  /**
  * Set field E05MBMTRM using a String value.
  */
  public void setE05MBMTRM(String newvalue)
  {
    fieldE05MBMTRM.setString(newvalue);
  }

  /**
  * Get value of field E05MBMTRM as a String.
  */
  public String getE05MBMTRM()
  {
    return fieldE05MBMTRM.getString();
  }

  /**
  * Set numeric field E05MBMTRM using a BigDecimal value.
  */
  public void setE05MBMTRM(BigDecimal newvalue)
  {
    fieldE05MBMTRM.setBigDecimal(newvalue);
  }

  /**
  * Return value of numeric field E05MBMTRM as a BigDecimal.
  */
  public BigDecimal getBigDecimalE05MBMTRM()
  {
    return fieldE05MBMTRM.getBigDecimal();
  }

  /**
  * Set field E05MBRTRC using a String value.
  */
  public void setE05MBRTRC(String newvalue)
  {
    fieldE05MBRTRC.setString(newvalue);
  }

  /**
  * Get value of field E05MBRTRC as a String.
  */
  public String getE05MBRTRC()
  {
    return fieldE05MBRTRC.getString();
  }

  /**
  * Set field E05CUMMA2 using a String value.
  */
  public void setE05CUMMA2(String newvalue)
  {
    fieldE05CUMMA2.setString(newvalue);
  }

  /**
  * Get value of field E05CUMMA2 as a String.
  */
  public String getE05CUMMA2()
  {
    return fieldE05CUMMA2.getString();
  }

  /**
  * Set field E05CUMNST using a String value.
  */
  public void setE05CUMNST(String newvalue)
  {
    fieldE05CUMNST.setString(newvalue);
  }

  /**
  * Get value of field E05CUMNST as a String.
  */
  public String getE05CUMNST()
  {
    return fieldE05CUMNST.getString();
  }

  /**
  * Set numeric field E05CUMNST using a BigDecimal value.
  */
  public void setE05CUMNST(BigDecimal newvalue)
  {
    fieldE05CUMNST.setBigDecimal(newvalue);
  }

  /**
  * Return value of numeric field E05CUMNST as a BigDecimal.
  */
  public BigDecimal getBigDecimalE05CUMNST()
  {
    return fieldE05CUMNST.getBigDecimal();
  }

  /**
  * Set field E05CUMPOB using a String value.
  */
  public void setE05CUMPOB(String newvalue)
  {
    fieldE05CUMPOB.setString(newvalue);
  }

  /**
  * Get value of field E05CUMPOB as a String.
  */
  public String getE05CUMPOB()
  {
    return fieldE05CUMPOB.getString();
  }

  /**
  * Set field E05CUMCTY using a String value.
  */
  public void setE05CUMCTY(String newvalue)
  {
    fieldE05CUMCTY.setString(newvalue);
  }

  /**
  * Get value of field E05CUMCTY as a String.
  */
  public String getE05CUMCTY()
  {
    return fieldE05CUMCTY.getString();
  }

  /**
  * Set field E05REGRDD using a String value.
  */
  public void setE05REGRDD(String newvalue)
  {
    fieldE05REGRDD.setString(newvalue);
  }

  /**
  * Get value of field E05REGRDD as a String.
  */
  public String getE05REGRDD()
  {
    return fieldE05REGRDD.getString();
  }

  /**
  * Set numeric field E05REGRDD using a BigDecimal value.
  */
  public void setE05REGRDD(BigDecimal newvalue)
  {
    fieldE05REGRDD.setBigDecimal(newvalue);
  }

  /**
  * Return value of numeric field E05REGRDD as a BigDecimal.
  */
  public BigDecimal getBigDecimalE05REGRDD()
  {
    return fieldE05REGRDD.getBigDecimal();
  }

  /**
  * Set field E05REGRMM using a String value.
  */
  public void setE05REGRMM(String newvalue)
  {
    fieldE05REGRMM.setString(newvalue);
  }

  /**
  * Get value of field E05REGRMM as a String.
  */
  public String getE05REGRMM()
  {
    return fieldE05REGRMM.getString();
  }

  /**
  * Set numeric field E05REGRMM using a BigDecimal value.
  */
  public void setE05REGRMM(BigDecimal newvalue)
  {
    fieldE05REGRMM.setBigDecimal(newvalue);
  }

  /**
  * Return value of numeric field E05REGRMM as a BigDecimal.
  */
  public BigDecimal getBigDecimalE05REGRMM()
  {
    return fieldE05REGRMM.getBigDecimal();
  }

  /**
  * Set field E05REGRYY using a String value.
  */
  public void setE05REGRYY(String newvalue)
  {
    fieldE05REGRYY.setString(newvalue);
  }

  /**
  * Get value of field E05REGRYY as a String.
  */
  public String getE05REGRYY()
  {
    return fieldE05REGRYY.getString();
  }

  /**
  * Set numeric field E05REGRYY using a BigDecimal value.
  */
  public void setE05REGRYY(BigDecimal newvalue)
  {
    fieldE05REGRYY.setBigDecimal(newvalue);
  }

  /**
  * Return value of numeric field E05REGRYY as a BigDecimal.
  */
  public BigDecimal getBigDecimalE05REGRYY()
  {
    return fieldE05REGRYY.getBigDecimal();
  }

  /**
  * Set field E05CUMMA3 using a String value.
  */
  public void setE05CUMMA3(String newvalue)
  {
    fieldE05CUMMA3.setString(newvalue);
  }

  /**
  * Get value of field E05CUMMA3 as a String.
  */
  public String getE05CUMMA3()
  {
    return fieldE05CUMMA3.getString();
  }

  /**
  * Set field E05CUMMA4 using a String value.
  */
  public void setE05CUMMA4(String newvalue)
  {
    fieldE05CUMMA4.setString(newvalue);
  }

  /**
  * Get value of field E05CUMMA4 as a String.
  */
  public String getE05CUMMA4()
  {
    return fieldE05CUMMA4.getString();
  }

  /**
  * Set field E05CUMCTR using a String value.
  */
  public void setE05CUMCTR(String newvalue)
  {
    fieldE05CUMCTR.setString(newvalue);
  }

  /**
  * Get value of field E05CUMCTR as a String.
  */
  public String getE05CUMCTR()
  {
    return fieldE05CUMCTR.getString();
  }

  /**
  * Set field E05CUMINC using a String value.
  */
  public void setE05CUMINC(String newvalue)
  {
    fieldE05CUMINC.setString(newvalue);
  }

  /**
  * Get value of field E05CUMINC as a String.
  */
  public String getE05CUMINC()
  {
    return fieldE05CUMINC.getString();
  }

  /**
  * Set field E05CUMINN using a String value.
  */
  public void setE05CUMINN(String newvalue)
  {
    fieldE05CUMINN.setString(newvalue);
  }

  /**
  * Get value of field E05CUMINN as a String.
  */
  public String getE05CUMINN()
  {
    return fieldE05CUMINN.getString();
  }

  /**
  * Set field E05CUMBNC using a String value.
  */
  public void setE05CUMBNC(String newvalue)
  {
    fieldE05CUMBNC.setString(newvalue);
  }

  /**
  * Get value of field E05CUMBNC as a String.
  */
  public String getE05CUMBNC()
  {
    return fieldE05CUMBNC.getString();
  }

  /**
  * Set field E05CUMBNN using a String value.
  */
  public void setE05CUMBNN(String newvalue)
  {
    fieldE05CUMBNN.setString(newvalue);
  }

  /**
  * Get value of field E05CUMBNN as a String.
  */
  public String getE05CUMBNN()
  {
    return fieldE05CUMBNN.getString();
  }

}
