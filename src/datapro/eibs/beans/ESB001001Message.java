package datapro.eibs.beans;

import datapro.eibs.sockets.*;
import java.io.*;
import java.math.*;

import java.util.*;

/**
* Class generated by AS/400 CRTCLASS command from ESB001001 physical file definition.
* 
* File level identifier is 1040831113428.
* Record format level identifier is 38BE723620337.
*/

public class ESB001001Message extends MessageRecord
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
                                     "E01SBTBNK",
                                     "E01SBTTYP",
                                     "E01SBTDSC",
                                     "E01SBTMOD",
                                     "E01SBTBRA",
                                     "E01SBTVEN",
                                     "E01SBTFL0",
                                     "E01SBTFL1",
                                     "E01SBTFL2",
                                     "E01SBTFL3",
                                     "E01SBTFL4",
                                     "E01SBTFL5",
                                     "E01SBTFL6",
                                     "E01SBTFL7",
                                     "E01SBTFL8",
                                     "E01SBTFL9",
                                     "D01BAVNM1"
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
                                   "E01SBTBNK",
                                   "E01SBTTYP",
                                   "E01SBTDSC",
                                   "E01SBTMOD",
                                   "E01SBTBRA",
                                   "E01SBTVEN",
                                   "E01SBTFL0",
                                   "E01SBTFL1",
                                   "E01SBTFL2",
                                   "E01SBTFL3",
                                   "E01SBTFL4",
                                   "E01SBTFL5",
                                   "E01SBTFL6",
                                   "E01SBTFL7",
                                   "E01SBTFL8",
                                   "E01SBTFL9",
                                   "D01BAVNM1"
                                 };
  final static String fid = "1040831113428";
  final static String rid = "38BE723620337";
  final static String fmtname = "ESB001001";
  final int FIELDCOUNT = 26;
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
  private CharacterField fieldE01SBTBNK = null;
  private DecimalField fieldE01SBTTYP = null;
  private CharacterField fieldE01SBTDSC = null;
  private CharacterField fieldE01SBTMOD = null;
  private CharacterField fieldE01SBTBRA = null;
  private DecimalField fieldE01SBTVEN = null;
  private CharacterField fieldE01SBTFL0 = null;
  private CharacterField fieldE01SBTFL1 = null;
  private CharacterField fieldE01SBTFL2 = null;
  private CharacterField fieldE01SBTFL3 = null;
  private CharacterField fieldE01SBTFL4 = null;
  private CharacterField fieldE01SBTFL5 = null;
  private CharacterField fieldE01SBTFL6 = null;
  private DecimalField fieldE01SBTFL7 = null;
  private DecimalField fieldE01SBTFL8 = null;
  private DecimalField fieldE01SBTFL9 = null;
  private CharacterField fieldD01BAVNM1 = null;

  /**
  * Constructor for ESB001001Message.
  */
  public ESB001001Message()
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
    recordsize = 298;
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
    fields[9] = fieldE01SBTBNK =
    new CharacterField(message, HEADERSIZE + 42, 2, "E01SBTBNK");
    fields[10] = fieldE01SBTTYP =
    new DecimalField(message, HEADERSIZE + 44, 5, 0, "E01SBTTYP");
    fields[11] = fieldE01SBTDSC =
    new CharacterField(message, HEADERSIZE + 49, 50, "E01SBTDSC");
    fields[12] = fieldE01SBTMOD =
    new CharacterField(message, HEADERSIZE + 99, 30, "E01SBTMOD");
    fields[13] = fieldE01SBTBRA =
    new CharacterField(message, HEADERSIZE + 129, 30, "E01SBTBRA");
    fields[14] = fieldE01SBTVEN =
    new DecimalField(message, HEADERSIZE + 159, 10, 0, "E01SBTVEN");
    fields[15] = fieldE01SBTFL0 =
    new CharacterField(message, HEADERSIZE + 169, 1, "E01SBTFL0");
    fields[16] = fieldE01SBTFL1 =
    new CharacterField(message, HEADERSIZE + 170, 1, "E01SBTFL1");
    fields[17] = fieldE01SBTFL2 =
    new CharacterField(message, HEADERSIZE + 171, 1, "E01SBTFL2");
    fields[18] = fieldE01SBTFL3 =
    new CharacterField(message, HEADERSIZE + 172, 1, "E01SBTFL3");
    fields[19] = fieldE01SBTFL4 =
    new CharacterField(message, HEADERSIZE + 173, 15, "E01SBTFL4");
    fields[20] = fieldE01SBTFL5 =
    new CharacterField(message, HEADERSIZE + 188, 15, "E01SBTFL5");
    fields[21] = fieldE01SBTFL6 =
    new CharacterField(message, HEADERSIZE + 203, 15, "E01SBTFL6");
    fields[22] = fieldE01SBTFL7 =
    new DecimalField(message, HEADERSIZE + 218, 15, 2, "E01SBTFL7");
    fields[23] = fieldE01SBTFL8 =
    new DecimalField(message, HEADERSIZE + 233, 15, 2, "E01SBTFL8");
    fields[24] = fieldE01SBTFL9 =
    new DecimalField(message, HEADERSIZE + 248, 15, 2, "E01SBTFL9");
    fields[25] = fieldD01BAVNM1 =
    new CharacterField(message, HEADERSIZE + 263, 35, "D01BAVNM1");

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
  * Set field E01SBTBNK using a String value.
  */
  public void setE01SBTBNK(String newvalue)
  {
    fieldE01SBTBNK.setString(newvalue);
  }

  /**
  * Get value of field E01SBTBNK as a String.
  */
  public String getE01SBTBNK()
  {
    return fieldE01SBTBNK.getString();
  }

  /**
  * Set field E01SBTTYP using a String value.
  */
  public void setE01SBTTYP(String newvalue)
  {
    fieldE01SBTTYP.setString(newvalue);
  }

  /**
  * Get value of field E01SBTTYP as a String.
  */
  public String getE01SBTTYP()
  {
    return fieldE01SBTTYP.getString();
  }

  /**
  * Set numeric field E01SBTTYP using a BigDecimal value.
  */
  public void setE01SBTTYP(BigDecimal newvalue)
  {
    fieldE01SBTTYP.setBigDecimal(newvalue);
  }

  /**
  * Return value of numeric field E01SBTTYP as a BigDecimal.
  */
  public BigDecimal getBigDecimalE01SBTTYP()
  {
    return fieldE01SBTTYP.getBigDecimal();
  }

  /**
  * Set field E01SBTDSC using a String value.
  */
  public void setE01SBTDSC(String newvalue)
  {
    fieldE01SBTDSC.setString(newvalue);
  }

  /**
  * Get value of field E01SBTDSC as a String.
  */
  public String getE01SBTDSC()
  {
    return fieldE01SBTDSC.getString();
  }

  /**
  * Set field E01SBTMOD using a String value.
  */
  public void setE01SBTMOD(String newvalue)
  {
    fieldE01SBTMOD.setString(newvalue);
  }

  /**
  * Get value of field E01SBTMOD as a String.
  */
  public String getE01SBTMOD()
  {
    return fieldE01SBTMOD.getString();
  }

  /**
  * Set field E01SBTBRA using a String value.
  */
  public void setE01SBTBRA(String newvalue)
  {
    fieldE01SBTBRA.setString(newvalue);
  }

  /**
  * Get value of field E01SBTBRA as a String.
  */
  public String getE01SBTBRA()
  {
    return fieldE01SBTBRA.getString();
  }

  /**
  * Set field E01SBTVEN using a String value.
  */
  public void setE01SBTVEN(String newvalue)
  {
    fieldE01SBTVEN.setString(newvalue);
  }

  /**
  * Get value of field E01SBTVEN as a String.
  */
  public String getE01SBTVEN()
  {
    return fieldE01SBTVEN.getString();
  }

  /**
  * Set numeric field E01SBTVEN using a BigDecimal value.
  */
  public void setE01SBTVEN(BigDecimal newvalue)
  {
    fieldE01SBTVEN.setBigDecimal(newvalue);
  }

  /**
  * Return value of numeric field E01SBTVEN as a BigDecimal.
  */
  public BigDecimal getBigDecimalE01SBTVEN()
  {
    return fieldE01SBTVEN.getBigDecimal();
  }

  /**
  * Set field E01SBTFL0 using a String value.
  */
  public void setE01SBTFL0(String newvalue)
  {
    fieldE01SBTFL0.setString(newvalue);
  }

  /**
  * Get value of field E01SBTFL0 as a String.
  */
  public String getE01SBTFL0()
  {
    return fieldE01SBTFL0.getString();
  }

  /**
  * Set field E01SBTFL1 using a String value.
  */
  public void setE01SBTFL1(String newvalue)
  {
    fieldE01SBTFL1.setString(newvalue);
  }

  /**
  * Get value of field E01SBTFL1 as a String.
  */
  public String getE01SBTFL1()
  {
    return fieldE01SBTFL1.getString();
  }

  /**
  * Set field E01SBTFL2 using a String value.
  */
  public void setE01SBTFL2(String newvalue)
  {
    fieldE01SBTFL2.setString(newvalue);
  }

  /**
  * Get value of field E01SBTFL2 as a String.
  */
  public String getE01SBTFL2()
  {
    return fieldE01SBTFL2.getString();
  }

  /**
  * Set field E01SBTFL3 using a String value.
  */
  public void setE01SBTFL3(String newvalue)
  {
    fieldE01SBTFL3.setString(newvalue);
  }

  /**
  * Get value of field E01SBTFL3 as a String.
  */
  public String getE01SBTFL3()
  {
    return fieldE01SBTFL3.getString();
  }

  /**
  * Set field E01SBTFL4 using a String value.
  */
  public void setE01SBTFL4(String newvalue)
  {
    fieldE01SBTFL4.setString(newvalue);
  }

  /**
  * Get value of field E01SBTFL4 as a String.
  */
  public String getE01SBTFL4()
  {
    return fieldE01SBTFL4.getString();
  }

  /**
  * Set field E01SBTFL5 using a String value.
  */
  public void setE01SBTFL5(String newvalue)
  {
    fieldE01SBTFL5.setString(newvalue);
  }

  /**
  * Get value of field E01SBTFL5 as a String.
  */
  public String getE01SBTFL5()
  {
    return fieldE01SBTFL5.getString();
  }

  /**
  * Set field E01SBTFL6 using a String value.
  */
  public void setE01SBTFL6(String newvalue)
  {
    fieldE01SBTFL6.setString(newvalue);
  }

  /**
  * Get value of field E01SBTFL6 as a String.
  */
  public String getE01SBTFL6()
  {
    return fieldE01SBTFL6.getString();
  }

  /**
  * Set field E01SBTFL7 using a String value.
  */
  public void setE01SBTFL7(String newvalue)
  {
    fieldE01SBTFL7.setString(newvalue);
  }

  /**
  * Get value of field E01SBTFL7 as a String.
  */
  public String getE01SBTFL7()
  {
    return fieldE01SBTFL7.getString();
  }

  /**
  * Set numeric field E01SBTFL7 using a BigDecimal value.
  */
  public void setE01SBTFL7(BigDecimal newvalue)
  {
    fieldE01SBTFL7.setBigDecimal(newvalue);
  }

  /**
  * Return value of numeric field E01SBTFL7 as a BigDecimal.
  */
  public BigDecimal getBigDecimalE01SBTFL7()
  {
    return fieldE01SBTFL7.getBigDecimal();
  }

  /**
  * Set field E01SBTFL8 using a String value.
  */
  public void setE01SBTFL8(String newvalue)
  {
    fieldE01SBTFL8.setString(newvalue);
  }

  /**
  * Get value of field E01SBTFL8 as a String.
  */
  public String getE01SBTFL8()
  {
    return fieldE01SBTFL8.getString();
  }

  /**
  * Set numeric field E01SBTFL8 using a BigDecimal value.
  */
  public void setE01SBTFL8(BigDecimal newvalue)
  {
    fieldE01SBTFL8.setBigDecimal(newvalue);
  }

  /**
  * Return value of numeric field E01SBTFL8 as a BigDecimal.
  */
  public BigDecimal getBigDecimalE01SBTFL8()
  {
    return fieldE01SBTFL8.getBigDecimal();
  }

  /**
  * Set field E01SBTFL9 using a String value.
  */
  public void setE01SBTFL9(String newvalue)
  {
    fieldE01SBTFL9.setString(newvalue);
  }

  /**
  * Get value of field E01SBTFL9 as a String.
  */
  public String getE01SBTFL9()
  {
    return fieldE01SBTFL9.getString();
  }

  /**
  * Set numeric field E01SBTFL9 using a BigDecimal value.
  */
  public void setE01SBTFL9(BigDecimal newvalue)
  {
    fieldE01SBTFL9.setBigDecimal(newvalue);
  }

  /**
  * Return value of numeric field E01SBTFL9 as a BigDecimal.
  */
  public BigDecimal getBigDecimalE01SBTFL9()
  {
    return fieldE01SBTFL9.getBigDecimal();
  }

  /**
  * Set field D01BAVNM1 using a String value.
  */
  public void setD01BAVNM1(String newvalue)
  {
    fieldD01BAVNM1.setString(newvalue);
  }

  /**
  * Get value of field D01BAVNM1 as a String.
  */
  public String getD01BAVNM1()
  {
    return fieldD01BAVNM1.getString();
  }

}