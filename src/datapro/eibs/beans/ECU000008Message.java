package datapro.eibs.beans;

import datapro.eibs.sockets.*;
import java.io.*;
import java.math.*;

import java.util.*;

/**
* Class generated by AS/400 CRTCLASS command from ECU000008 physical file definition.
* 
* File level identifier is 1050328133506.
* Record format level identifier is 57AA701AE10D3.
*/

public class ECU000008Message extends MessageRecord
{
  final static String fldnames[] = {
                                     "H08USERID",
                                     "H08PROGRM",
                                     "H08TIMSYS",
                                     "H08SCRCOD",
                                     "H08OPECOD",
                                     "H08FLGMAS",
                                     "H08FLGWK1",
                                     "H08FLGWK2",
                                     "H08FLGWK3",
                                     "E08CUFCUN",
                                     "E08CUSNA1",
                                     "E08CUFMAN",
                                     "E08CUFMA1",
                                     "E08CUMUC5",
                                     "E08CUMUCN",
                                     "E08CUFFAC",
                                     "E08CUFFAN",
                                     "E08CUFSEQ",
                                     "E08CUFACC",
                                     "E08CUFACN",
                                     "E08CUMBNI",
                                     "E08CUFOB1",
                                     "E08CUFOB2",
                                     "E08CUFOB3",
                                     "E08CUFOB4",
                                     "E08CUFOB5",
                                     "E08CUFAC1",
                                     "E08CUFAC2",
                                     "E08CUFAC3",
                                     "E08CUFACT",
                                     "E08CUFACU",
                                     "E08EWDNUM",
                                     "E08EWDDTE"
                                   };
  final static String tnames[] = {
                                   "H08USERID",
                                   "H08PROGRM",
                                   "H08TIMSYS",
                                   "H08SCRCOD",
                                   "H08OPECOD",
                                   "H08FLGMAS",
                                   "H08FLGWK1",
                                   "H08FLGWK2",
                                   "H08FLGWK3",
                                   "E08CUFCUN",
                                   "E08CUSNA1",
                                   "E08CUFMAN",
                                   "E08CUFMA1",
                                   "E08CUMUC5",
                                   "E08CUMUCN",
                                   "E08CUFFAC",
                                   "E08CUFFAN",
                                   "E08CUFSEQ",
                                   "E08CUFACC",
                                   "E08CUFACN",
                                   "E08CUMBNI",
                                   "E08CUFOB1",
                                   "E08CUFOB2",
                                   "E08CUFOB3",
                                   "E08CUFOB4",
                                   "E08CUFOB5",
                                   "E08CUFAC1",
                                   "E08CUFAC2",
                                   "E08CUFAC3",
                                   "E08CUFACT",
                                   "E08CUFACU",
                                   "E08EWDNUM",
                                   "E08EWDDTE"
                                 };
  final static String fid = "1050328133506";
  final static String rid = "57AA701AE10D3";
  final static String fmtname = "ECU000008";
  final int FIELDCOUNT = 33;
  private static Hashtable tlookup = new Hashtable();
  private CharacterField fieldH08USERID = null;
  private CharacterField fieldH08PROGRM = null;
  private CharacterField fieldH08TIMSYS = null;
  private CharacterField fieldH08SCRCOD = null;
  private CharacterField fieldH08OPECOD = null;
  private CharacterField fieldH08FLGMAS = null;
  private CharacterField fieldH08FLGWK1 = null;
  private CharacterField fieldH08FLGWK2 = null;
  private CharacterField fieldH08FLGWK3 = null;
  private DecimalField fieldE08CUFCUN = null;
  private CharacterField fieldE08CUSNA1 = null;
  private DecimalField fieldE08CUFMAN = null;
  private CharacterField fieldE08CUFMA1 = null;
  private CharacterField fieldE08CUMUC5 = null;
  private CharacterField fieldE08CUMUCN = null;
  private CharacterField fieldE08CUFFAC = null;
  private CharacterField fieldE08CUFFAN = null;
  private DecimalField fieldE08CUFSEQ = null;
  private CharacterField fieldE08CUFACC = null;
  private CharacterField fieldE08CUFACN = null;
  private CharacterField fieldE08CUMBNI = null;
  private CharacterField fieldE08CUFOB1 = null;
  private CharacterField fieldE08CUFOB2 = null;
  private CharacterField fieldE08CUFOB3 = null;
  private CharacterField fieldE08CUFOB4 = null;
  private CharacterField fieldE08CUFOB5 = null;
  private DecimalField fieldE08CUFAC1 = null;
  private DecimalField fieldE08CUFAC2 = null;
  private DecimalField fieldE08CUFAC3 = null;
  private DecimalField fieldE08CUFACT = null;
  private CharacterField fieldE08CUFACU = null;
  private DecimalField fieldE08EWDNUM = null;
  private DecimalField fieldE08EWDDTE = null;

  /**
  * Constructor for ECU000008Message.
  */
  public ECU000008Message()
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
    recordsize = 777;
    fileid = fid;
    recordid = rid;
    message = new byte[getByteLength()];
    formatname = fmtname;
    fieldnames = fldnames;
    tagnames = tnames;
    fields = new MessageField[FIELDCOUNT];

    fields[0] = fieldH08USERID =
    new CharacterField(message, HEADERSIZE + 0, 10, "H08USERID");
    fields[1] = fieldH08PROGRM =
    new CharacterField(message, HEADERSIZE + 10, 10, "H08PROGRM");
    fields[2] = fieldH08TIMSYS =
    new CharacterField(message, HEADERSIZE + 20, 12, "H08TIMSYS");
    fields[3] = fieldH08SCRCOD =
    new CharacterField(message, HEADERSIZE + 32, 2, "H08SCRCOD");
    fields[4] = fieldH08OPECOD =
    new CharacterField(message, HEADERSIZE + 34, 4, "H08OPECOD");
    fields[5] = fieldH08FLGMAS =
    new CharacterField(message, HEADERSIZE + 38, 1, "H08FLGMAS");
    fields[6] = fieldH08FLGWK1 =
    new CharacterField(message, HEADERSIZE + 39, 1, "H08FLGWK1");
    fields[7] = fieldH08FLGWK2 =
    new CharacterField(message, HEADERSIZE + 40, 1, "H08FLGWK2");
    fields[8] = fieldH08FLGWK3 =
    new CharacterField(message, HEADERSIZE + 41, 1, "H08FLGWK3");
    fields[9] = fieldE08CUFCUN =
    new DecimalField(message, HEADERSIZE + 42, 10, 0, "E08CUFCUN");
    fields[10] = fieldE08CUSNA1 =
    new CharacterField(message, HEADERSIZE + 52, 45, "E08CUSNA1");
    fields[11] = fieldE08CUFMAN =
    new DecimalField(message, HEADERSIZE + 97, 3, 0, "E08CUFMAN");
    fields[12] = fieldE08CUFMA1 =
    new CharacterField(message, HEADERSIZE + 100, 45, "E08CUFMA1");
    fields[13] = fieldE08CUMUC5 =
    new CharacterField(message, HEADERSIZE + 145, 4, "E08CUMUC5");
    fields[14] = fieldE08CUMUCN =
    new CharacterField(message, HEADERSIZE + 149, 35, "E08CUMUCN");
    fields[15] = fieldE08CUFFAC =
    new CharacterField(message, HEADERSIZE + 184, 4, "E08CUFFAC");
    fields[16] = fieldE08CUFFAN =
    new CharacterField(message, HEADERSIZE + 188, 35, "E08CUFFAN");
    fields[17] = fieldE08CUFSEQ =
    new DecimalField(message, HEADERSIZE + 223, 3, 0, "E08CUFSEQ");
    fields[18] = fieldE08CUFACC =
    new CharacterField(message, HEADERSIZE + 226, 35, "E08CUFACC");
    fields[19] = fieldE08CUFACN =
    new CharacterField(message, HEADERSIZE + 261, 45, "E08CUFACN");
    fields[20] = fieldE08CUMBNI =
    new CharacterField(message, HEADERSIZE + 306, 15, "E08CUMBNI");
    fields[21] = fieldE08CUFOB1 =
    new CharacterField(message, HEADERSIZE + 321, 80, "E08CUFOB1");
    fields[22] = fieldE08CUFOB2 =
    new CharacterField(message, HEADERSIZE + 401, 80, "E08CUFOB2");
    fields[23] = fieldE08CUFOB3 =
    new CharacterField(message, HEADERSIZE + 481, 80, "E08CUFOB3");
    fields[24] = fieldE08CUFOB4 =
    new CharacterField(message, HEADERSIZE + 561, 80, "E08CUFOB4");
    fields[25] = fieldE08CUFOB5 =
    new CharacterField(message, HEADERSIZE + 641, 80, "E08CUFOB5");
    fields[26] = fieldE08CUFAC1 =
    new DecimalField(message, HEADERSIZE + 721, 3, 0, "E08CUFAC1");
    fields[27] = fieldE08CUFAC2 =
    new DecimalField(message, HEADERSIZE + 724, 3, 0, "E08CUFAC2");
    fields[28] = fieldE08CUFAC3 =
    new DecimalField(message, HEADERSIZE + 727, 3, 0, "E08CUFAC3");
    fields[29] = fieldE08CUFACT =
    new DecimalField(message, HEADERSIZE + 730, 7, 0, "E08CUFACT");
    fields[30] = fieldE08CUFACU =
    new CharacterField(message, HEADERSIZE + 737, 10, "E08CUFACU");
    fields[31] = fieldE08EWDNUM =
    new DecimalField(message, HEADERSIZE + 747, 21, 0, "E08EWDNUM");
    fields[32] = fieldE08EWDDTE =
    new DecimalField(message, HEADERSIZE + 768, 9, 0, "E08EWDDTE");

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
  * Set field H08USERID using a String value.
  */
  public void setH08USERID(String newvalue)
  {
    fieldH08USERID.setString(newvalue);
  }

  /**
  * Get value of field H08USERID as a String.
  */
  public String getH08USERID()
  {
    return fieldH08USERID.getString();
  }

  /**
  * Set field H08PROGRM using a String value.
  */
  public void setH08PROGRM(String newvalue)
  {
    fieldH08PROGRM.setString(newvalue);
  }

  /**
  * Get value of field H08PROGRM as a String.
  */
  public String getH08PROGRM()
  {
    return fieldH08PROGRM.getString();
  }

  /**
  * Set field H08TIMSYS using a String value.
  */
  public void setH08TIMSYS(String newvalue)
  {
    fieldH08TIMSYS.setString(newvalue);
  }

  /**
  * Get value of field H08TIMSYS as a String.
  */
  public String getH08TIMSYS()
  {
    return fieldH08TIMSYS.getString();
  }

  /**
  * Set field H08SCRCOD using a String value.
  */
  public void setH08SCRCOD(String newvalue)
  {
    fieldH08SCRCOD.setString(newvalue);
  }

  /**
  * Get value of field H08SCRCOD as a String.
  */
  public String getH08SCRCOD()
  {
    return fieldH08SCRCOD.getString();
  }

  /**
  * Set field H08OPECOD using a String value.
  */
  public void setH08OPECOD(String newvalue)
  {
    fieldH08OPECOD.setString(newvalue);
  }

  /**
  * Get value of field H08OPECOD as a String.
  */
  public String getH08OPECOD()
  {
    return fieldH08OPECOD.getString();
  }

  /**
  * Set field H08FLGMAS using a String value.
  */
  public void setH08FLGMAS(String newvalue)
  {
    fieldH08FLGMAS.setString(newvalue);
  }

  /**
  * Get value of field H08FLGMAS as a String.
  */
  public String getH08FLGMAS()
  {
    return fieldH08FLGMAS.getString();
  }

  /**
  * Set field H08FLGWK1 using a String value.
  */
  public void setH08FLGWK1(String newvalue)
  {
    fieldH08FLGWK1.setString(newvalue);
  }

  /**
  * Get value of field H08FLGWK1 as a String.
  */
  public String getH08FLGWK1()
  {
    return fieldH08FLGWK1.getString();
  }

  /**
  * Set field H08FLGWK2 using a String value.
  */
  public void setH08FLGWK2(String newvalue)
  {
    fieldH08FLGWK2.setString(newvalue);
  }

  /**
  * Get value of field H08FLGWK2 as a String.
  */
  public String getH08FLGWK2()
  {
    return fieldH08FLGWK2.getString();
  }

  /**
  * Set field H08FLGWK3 using a String value.
  */
  public void setH08FLGWK3(String newvalue)
  {
    fieldH08FLGWK3.setString(newvalue);
  }

  /**
  * Get value of field H08FLGWK3 as a String.
  */
  public String getH08FLGWK3()
  {
    return fieldH08FLGWK3.getString();
  }

  /**
  * Set field E08CUFCUN using a String value.
  */
  public void setE08CUFCUN(String newvalue)
  {
    fieldE08CUFCUN.setString(newvalue);
  }

  /**
  * Get value of field E08CUFCUN as a String.
  */
  public String getE08CUFCUN()
  {
    return fieldE08CUFCUN.getString();
  }

  /**
  * Set numeric field E08CUFCUN using a BigDecimal value.
  */
  public void setE08CUFCUN(BigDecimal newvalue)
  {
    fieldE08CUFCUN.setBigDecimal(newvalue);
  }

  /**
  * Return value of numeric field E08CUFCUN as a BigDecimal.
  */
  public BigDecimal getBigDecimalE08CUFCUN()
  {
    return fieldE08CUFCUN.getBigDecimal();
  }

  /**
  * Set field E08CUSNA1 using a String value.
  */
  public void setE08CUSNA1(String newvalue)
  {
    fieldE08CUSNA1.setString(newvalue);
  }

  /**
  * Get value of field E08CUSNA1 as a String.
  */
  public String getE08CUSNA1()
  {
    return fieldE08CUSNA1.getString();
  }

  /**
  * Set field E08CUFMAN using a String value.
  */
  public void setE08CUFMAN(String newvalue)
  {
    fieldE08CUFMAN.setString(newvalue);
  }

  /**
  * Get value of field E08CUFMAN as a String.
  */
  public String getE08CUFMAN()
  {
    return fieldE08CUFMAN.getString();
  }

  /**
  * Set numeric field E08CUFMAN using a BigDecimal value.
  */
  public void setE08CUFMAN(BigDecimal newvalue)
  {
    fieldE08CUFMAN.setBigDecimal(newvalue);
  }

  /**
  * Return value of numeric field E08CUFMAN as a BigDecimal.
  */
  public BigDecimal getBigDecimalE08CUFMAN()
  {
    return fieldE08CUFMAN.getBigDecimal();
  }

  /**
  * Set field E08CUFMA1 using a String value.
  */
  public void setE08CUFMA1(String newvalue)
  {
    fieldE08CUFMA1.setString(newvalue);
  }

  /**
  * Get value of field E08CUFMA1 as a String.
  */
  public String getE08CUFMA1()
  {
    return fieldE08CUFMA1.getString();
  }

  /**
  * Set field E08CUMUC5 using a String value.
  */
  public void setE08CUMUC5(String newvalue)
  {
    fieldE08CUMUC5.setString(newvalue);
  }

  /**
  * Get value of field E08CUMUC5 as a String.
  */
  public String getE08CUMUC5()
  {
    return fieldE08CUMUC5.getString();
  }

  /**
  * Set field E08CUMUCN using a String value.
  */
  public void setE08CUMUCN(String newvalue)
  {
    fieldE08CUMUCN.setString(newvalue);
  }

  /**
  * Get value of field E08CUMUCN as a String.
  */
  public String getE08CUMUCN()
  {
    return fieldE08CUMUCN.getString();
  }

  /**
  * Set field E08CUFFAC using a String value.
  */
  public void setE08CUFFAC(String newvalue)
  {
    fieldE08CUFFAC.setString(newvalue);
  }

  /**
  * Get value of field E08CUFFAC as a String.
  */
  public String getE08CUFFAC()
  {
    return fieldE08CUFFAC.getString();
  }

  /**
  * Set field E08CUFFAN using a String value.
  */
  public void setE08CUFFAN(String newvalue)
  {
    fieldE08CUFFAN.setString(newvalue);
  }

  /**
  * Get value of field E08CUFFAN as a String.
  */
  public String getE08CUFFAN()
  {
    return fieldE08CUFFAN.getString();
  }

  /**
  * Set field E08CUFSEQ using a String value.
  */
  public void setE08CUFSEQ(String newvalue)
  {
    fieldE08CUFSEQ.setString(newvalue);
  }

  /**
  * Get value of field E08CUFSEQ as a String.
  */
  public String getE08CUFSEQ()
  {
    return fieldE08CUFSEQ.getString();
  }

  /**
  * Set numeric field E08CUFSEQ using a BigDecimal value.
  */
  public void setE08CUFSEQ(BigDecimal newvalue)
  {
    fieldE08CUFSEQ.setBigDecimal(newvalue);
  }

  /**
  * Return value of numeric field E08CUFSEQ as a BigDecimal.
  */
  public BigDecimal getBigDecimalE08CUFSEQ()
  {
    return fieldE08CUFSEQ.getBigDecimal();
  }

  /**
  * Set field E08CUFACC using a String value.
  */
  public void setE08CUFACC(String newvalue)
  {
    fieldE08CUFACC.setString(newvalue);
  }

  /**
  * Get value of field E08CUFACC as a String.
  */
  public String getE08CUFACC()
  {
    return fieldE08CUFACC.getString();
  }

  /**
  * Set field E08CUFACN using a String value.
  */
  public void setE08CUFACN(String newvalue)
  {
    fieldE08CUFACN.setString(newvalue);
  }

  /**
  * Get value of field E08CUFACN as a String.
  */
  public String getE08CUFACN()
  {
    return fieldE08CUFACN.getString();
  }

  /**
  * Set field E08CUMBNI using a String value.
  */
  public void setE08CUMBNI(String newvalue)
  {
    fieldE08CUMBNI.setString(newvalue);
  }

  /**
  * Get value of field E08CUMBNI as a String.
  */
  public String getE08CUMBNI()
  {
    return fieldE08CUMBNI.getString();
  }

  /**
  * Set field E08CUFOB1 using a String value.
  */
  public void setE08CUFOB1(String newvalue)
  {
    fieldE08CUFOB1.setString(newvalue);
  }

  /**
  * Get value of field E08CUFOB1 as a String.
  */
  public String getE08CUFOB1()
  {
    return fieldE08CUFOB1.getString();
  }

  /**
  * Set field E08CUFOB2 using a String value.
  */
  public void setE08CUFOB2(String newvalue)
  {
    fieldE08CUFOB2.setString(newvalue);
  }

  /**
  * Get value of field E08CUFOB2 as a String.
  */
  public String getE08CUFOB2()
  {
    return fieldE08CUFOB2.getString();
  }

  /**
  * Set field E08CUFOB3 using a String value.
  */
  public void setE08CUFOB3(String newvalue)
  {
    fieldE08CUFOB3.setString(newvalue);
  }

  /**
  * Get value of field E08CUFOB3 as a String.
  */
  public String getE08CUFOB3()
  {
    return fieldE08CUFOB3.getString();
  }

  /**
  * Set field E08CUFOB4 using a String value.
  */
  public void setE08CUFOB4(String newvalue)
  {
    fieldE08CUFOB4.setString(newvalue);
  }

  /**
  * Get value of field E08CUFOB4 as a String.
  */
  public String getE08CUFOB4()
  {
    return fieldE08CUFOB4.getString();
  }

  /**
  * Set field E08CUFOB5 using a String value.
  */
  public void setE08CUFOB5(String newvalue)
  {
    fieldE08CUFOB5.setString(newvalue);
  }

  /**
  * Get value of field E08CUFOB5 as a String.
  */
  public String getE08CUFOB5()
  {
    return fieldE08CUFOB5.getString();
  }

  /**
  * Set field E08CUFAC1 using a String value.
  */
  public void setE08CUFAC1(String newvalue)
  {
    fieldE08CUFAC1.setString(newvalue);
  }

  /**
  * Get value of field E08CUFAC1 as a String.
  */
  public String getE08CUFAC1()
  {
    return fieldE08CUFAC1.getString();
  }

  /**
  * Set numeric field E08CUFAC1 using a BigDecimal value.
  */
  public void setE08CUFAC1(BigDecimal newvalue)
  {
    fieldE08CUFAC1.setBigDecimal(newvalue);
  }

  /**
  * Return value of numeric field E08CUFAC1 as a BigDecimal.
  */
  public BigDecimal getBigDecimalE08CUFAC1()
  {
    return fieldE08CUFAC1.getBigDecimal();
  }

  /**
  * Set field E08CUFAC2 using a String value.
  */
  public void setE08CUFAC2(String newvalue)
  {
    fieldE08CUFAC2.setString(newvalue);
  }

  /**
  * Get value of field E08CUFAC2 as a String.
  */
  public String getE08CUFAC2()
  {
    return fieldE08CUFAC2.getString();
  }

  /**
  * Set numeric field E08CUFAC2 using a BigDecimal value.
  */
  public void setE08CUFAC2(BigDecimal newvalue)
  {
    fieldE08CUFAC2.setBigDecimal(newvalue);
  }

  /**
  * Return value of numeric field E08CUFAC2 as a BigDecimal.
  */
  public BigDecimal getBigDecimalE08CUFAC2()
  {
    return fieldE08CUFAC2.getBigDecimal();
  }

  /**
  * Set field E08CUFAC3 using a String value.
  */
  public void setE08CUFAC3(String newvalue)
  {
    fieldE08CUFAC3.setString(newvalue);
  }

  /**
  * Get value of field E08CUFAC3 as a String.
  */
  public String getE08CUFAC3()
  {
    return fieldE08CUFAC3.getString();
  }

  /**
  * Set numeric field E08CUFAC3 using a BigDecimal value.
  */
  public void setE08CUFAC3(BigDecimal newvalue)
  {
    fieldE08CUFAC3.setBigDecimal(newvalue);
  }

  /**
  * Return value of numeric field E08CUFAC3 as a BigDecimal.
  */
  public BigDecimal getBigDecimalE08CUFAC3()
  {
    return fieldE08CUFAC3.getBigDecimal();
  }

  /**
  * Set field E08CUFACT using a String value.
  */
  public void setE08CUFACT(String newvalue)
  {
    fieldE08CUFACT.setString(newvalue);
  }

  /**
  * Get value of field E08CUFACT as a String.
  */
  public String getE08CUFACT()
  {
    return fieldE08CUFACT.getString();
  }

  /**
  * Set numeric field E08CUFACT using a BigDecimal value.
  */
  public void setE08CUFACT(BigDecimal newvalue)
  {
    fieldE08CUFACT.setBigDecimal(newvalue);
  }

  /**
  * Return value of numeric field E08CUFACT as a BigDecimal.
  */
  public BigDecimal getBigDecimalE08CUFACT()
  {
    return fieldE08CUFACT.getBigDecimal();
  }

  /**
  * Set field E08CUFACU using a String value.
  */
  public void setE08CUFACU(String newvalue)
  {
    fieldE08CUFACU.setString(newvalue);
  }

  /**
  * Get value of field E08CUFACU as a String.
  */
  public String getE08CUFACU()
  {
    return fieldE08CUFACU.getString();
  }

  /**
  * Set field E08EWDNUM using a String value.
  */
  public void setE08EWDNUM(String newvalue)
  {
    fieldE08EWDNUM.setString(newvalue);
  }

  /**
  * Get value of field E08EWDNUM as a String.
  */
  public String getE08EWDNUM()
  {
    return fieldE08EWDNUM.getString();
  }

  /**
  * Set numeric field E08EWDNUM using a BigDecimal value.
  */
  public void setE08EWDNUM(BigDecimal newvalue)
  {
    fieldE08EWDNUM.setBigDecimal(newvalue);
  }

  /**
  * Return value of numeric field E08EWDNUM as a BigDecimal.
  */
  public BigDecimal getBigDecimalE08EWDNUM()
  {
    return fieldE08EWDNUM.getBigDecimal();
  }

  /**
  * Set field E08EWDDTE using a String value.
  */
  public void setE08EWDDTE(String newvalue)
  {
    fieldE08EWDDTE.setString(newvalue);
  }

  /**
  * Get value of field E08EWDDTE as a String.
  */
  public String getE08EWDDTE()
  {
    return fieldE08EWDDTE.getString();
  }

  /**
  * Set numeric field E08EWDDTE using a BigDecimal value.
  */
  public void setE08EWDDTE(BigDecimal newvalue)
  {
    fieldE08EWDDTE.setBigDecimal(newvalue);
  }

  /**
  * Return value of numeric field E08EWDDTE as a BigDecimal.
  */
  public BigDecimal getBigDecimalE08EWDDTE()
  {
    return fieldE08EWDDTE.getBigDecimal();
  }

}