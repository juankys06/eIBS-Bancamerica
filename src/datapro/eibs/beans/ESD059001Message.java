package datapro.eibs.beans;

import datapro.eibs.sockets.*;
import java.io.*;
import java.math.*;

import java.util.*;

/**
* Class generated by AS/400 CRTCLASS command from ESD059001 physical file definition.
* 
* File level identifier is 1031124135024.
* Record format level identifier is 5811274385617.
*/

public class ESD059001Message extends MessageRecord
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
                                     "E01BRNBNK",
                                     "E01BRNNUM",
                                     "E01BRNF011",
                                     "E01BRNF012",
                                     "E01F012NME",
                                     "E01BRNNME",
                                     "E01BRNADR",
                                     "E01BRNCIT",
                                     "E01BRNPHN",
                                     "E01BRNCLB",
                                     "E01CLRNME",
                                     "E01BRNRGN",
                                     "E01RGNNME",
                                     "E01BRNSBR",
                                     "E01SBRNME",
                                     "E01BRNDID",
                                     "E01BRNUS1",
                                     "E01US1NME",
                                     "E01BRNUS2",
                                     "E01US2NME",
                                     "E01BRNFL1",
                                     "E01BRNFL2",
                                     "E01OPECDE"
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
                                   "E01BRNBNK",
                                   "E01BRNNUM",
                                   "E01BRNF011",
                                   "E01BRNF012",
                                   "E01F012NME",
                                   "E01BRNNME",
                                   "E01BRNADR",
                                   "E01BRNCIT",
                                   "E01BRNPHN",
                                   "E01BRNCLB",
                                   "E01CLRNME",
                                   "E01BRNRGN",
                                   "E01RGNNME",
                                   "E01BRNSBR",
                                   "E01SBRNME",
                                   "E01BRNDID",
                                   "E01BRNUS1",
                                   "E01US1NME",
                                   "E01BRNUS2",
                                   "E01US2NME",
                                   "E01BRNFL1",
                                   "E01BRNFL2",
                                   "E01OPECDE"
                                 };
  final static String fid = "1031124135024";
  final static String rid = "5811274385617";
  final static String fmtname = "ESD059001";
  final int FIELDCOUNT = 32;
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
  private CharacterField fieldE01BRNBNK = null;
  private DecimalField fieldE01BRNNUM = null;
  private CharacterField fieldE01BRNF011 = null;
  private CharacterField fieldE01BRNF012 = null;
  private CharacterField fieldE01F012NME = null;
  private CharacterField fieldE01BRNNME = null;
  private CharacterField fieldE01BRNADR = null;
  private CharacterField fieldE01BRNCIT = null;
  private DecimalField fieldE01BRNPHN = null;
  private DecimalField fieldE01BRNCLB = null;
  private CharacterField fieldE01CLRNME = null;
  private CharacterField fieldE01BRNRGN = null;
  private CharacterField fieldE01RGNNME = null;
  private CharacterField fieldE01BRNSBR = null;
  private CharacterField fieldE01SBRNME = null;
  private CharacterField fieldE01BRNDID = null;
  private CharacterField fieldE01BRNUS1 = null;
  private CharacterField fieldE01US1NME = null;
  private CharacterField fieldE01BRNUS2 = null;
  private CharacterField fieldE01US2NME = null;
  private CharacterField fieldE01BRNFL1 = null;
  private CharacterField fieldE01BRNFL2 = null;
  private CharacterField fieldE01OPECDE = null;

  /**
  * Constructor for ESD059001Message.
  */
  public ESD059001Message()
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
    recordsize = 415;
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
    fields[9] = fieldE01BRNBNK =
    new CharacterField(message, HEADERSIZE + 42, 2, "E01BRNBNK");
    fields[10] = fieldE01BRNNUM =
    new DecimalField(message, HEADERSIZE + 44, 4, 0, "E01BRNNUM");
    fields[11] = fieldE01BRNF011 =
    new CharacterField(message, HEADERSIZE + 48, 4, "E01BRNF011");
    fields[12] = fieldE01BRNF012 =
    new CharacterField(message, HEADERSIZE + 52, 3, "E01BRNF012");
    fields[13] = fieldE01F012NME =
    new CharacterField(message, HEADERSIZE + 55, 35, "E01F012NME");
    fields[14] = fieldE01BRNNME =
    new CharacterField(message, HEADERSIZE + 90, 35, "E01BRNNME");
    fields[15] = fieldE01BRNADR =
    new CharacterField(message, HEADERSIZE + 125, 35, "E01BRNADR");
    fields[16] = fieldE01BRNCIT =
    new CharacterField(message, HEADERSIZE + 160, 35, "E01BRNCIT");
    fields[17] = fieldE01BRNPHN =
    new DecimalField(message, HEADERSIZE + 195, 12, 0, "E01BRNPHN");
    fields[18] = fieldE01BRNCLB =
    new DecimalField(message, HEADERSIZE + 207, 4, 0, "E01BRNCLB");
    fields[19] = fieldE01CLRNME =
    new CharacterField(message, HEADERSIZE + 211, 35, "E01CLRNME");
    fields[20] = fieldE01BRNRGN =
    new CharacterField(message, HEADERSIZE + 246, 4, "E01BRNRGN");
    fields[21] = fieldE01RGNNME =
    new CharacterField(message, HEADERSIZE + 250, 35, "E01RGNNME");
    fields[22] = fieldE01BRNSBR =
    new CharacterField(message, HEADERSIZE + 285, 4, "E01BRNSBR");
    fields[23] = fieldE01SBRNME =
    new CharacterField(message, HEADERSIZE + 289, 35, "E01SBRNME");
    fields[24] = fieldE01BRNDID =
    new CharacterField(message, HEADERSIZE + 324, 10, "E01BRNDID");
    fields[25] = fieldE01BRNUS1 =
    new CharacterField(message, HEADERSIZE + 334, 4, "E01BRNUS1");
    fields[26] = fieldE01US1NME =
    new CharacterField(message, HEADERSIZE + 338, 35, "E01US1NME");
    fields[27] = fieldE01BRNUS2 =
    new CharacterField(message, HEADERSIZE + 373, 4, "E01BRNUS2");
    fields[28] = fieldE01US2NME =
    new CharacterField(message, HEADERSIZE + 377, 35, "E01US2NME");
    fields[29] = fieldE01BRNFL1 =
    new CharacterField(message, HEADERSIZE + 412, 1, "E01BRNFL1");
    fields[30] = fieldE01BRNFL2 =
    new CharacterField(message, HEADERSIZE + 413, 1, "E01BRNFL2");
    fields[31] = fieldE01OPECDE =
    new CharacterField(message, HEADERSIZE + 414, 1, "E01OPECDE");

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
  * Set field E01BRNBNK using a String value.
  */
  public void setE01BRNBNK(String newvalue)
  {
    fieldE01BRNBNK.setString(newvalue);
  }

  /**
  * Get value of field E01BRNBNK as a String.
  */
  public String getE01BRNBNK()
  {
    return fieldE01BRNBNK.getString();
  }

  /**
  * Set field E01BRNNUM using a String value.
  */
  public void setE01BRNNUM(String newvalue)
  {
    fieldE01BRNNUM.setString(newvalue);
  }

  /**
  * Get value of field E01BRNNUM as a String.
  */
  public String getE01BRNNUM()
  {
    return fieldE01BRNNUM.getString();
  }

  /**
  * Set numeric field E01BRNNUM using a BigDecimal value.
  */
  public void setE01BRNNUM(BigDecimal newvalue)
  {
    fieldE01BRNNUM.setBigDecimal(newvalue);
  }

  /**
  * Return value of numeric field E01BRNNUM as a BigDecimal.
  */
  public BigDecimal getBigDecimalE01BRNNUM()
  {
    return fieldE01BRNNUM.getBigDecimal();
  }

  /**
  * Set field E01BRNF011 using a String value.
  */
  public void setE01BRNF011(String newvalue)
  {
    fieldE01BRNF011.setString(newvalue);
  }

  /**
  * Get value of field E01BRNF011 as a String.
  */
  public String getE01BRNF011()
  {
    return fieldE01BRNF011.getString();
  }

  /**
  * Set field E01BRNF012 using a String value.
  */
  public void setE01BRNF012(String newvalue)
  {
    fieldE01BRNF012.setString(newvalue);
  }

  /**
  * Get value of field E01BRNF012 as a String.
  */
  public String getE01BRNF012()
  {
    return fieldE01BRNF012.getString();
  }

  /**
  * Set field E01F012NME using a String value.
  */
  public void setE01F012NME(String newvalue)
  {
    fieldE01F012NME.setString(newvalue);
  }

  /**
  * Get value of field E01F012NME as a String.
  */
  public String getE01F012NME()
  {
    return fieldE01F012NME.getString();
  }

  /**
  * Set field E01BRNNME using a String value.
  */
  public void setE01BRNNME(String newvalue)
  {
    fieldE01BRNNME.setString(newvalue);
  }

  /**
  * Get value of field E01BRNNME as a String.
  */
  public String getE01BRNNME()
  {
    return fieldE01BRNNME.getString();
  }

  /**
  * Set field E01BRNADR using a String value.
  */
  public void setE01BRNADR(String newvalue)
  {
    fieldE01BRNADR.setString(newvalue);
  }

  /**
  * Get value of field E01BRNADR as a String.
  */
  public String getE01BRNADR()
  {
    return fieldE01BRNADR.getString();
  }

  /**
  * Set field E01BRNCIT using a String value.
  */
  public void setE01BRNCIT(String newvalue)
  {
    fieldE01BRNCIT.setString(newvalue);
  }

  /**
  * Get value of field E01BRNCIT as a String.
  */
  public String getE01BRNCIT()
  {
    return fieldE01BRNCIT.getString();
  }

  /**
  * Set field E01BRNPHN using a String value.
  */
  public void setE01BRNPHN(String newvalue)
  {
    fieldE01BRNPHN.setString(newvalue);
  }

  /**
  * Get value of field E01BRNPHN as a String.
  */
  public String getE01BRNPHN()
  {
    return fieldE01BRNPHN.getString();
  }

  /**
  * Set numeric field E01BRNPHN using a BigDecimal value.
  */
  public void setE01BRNPHN(BigDecimal newvalue)
  {
    fieldE01BRNPHN.setBigDecimal(newvalue);
  }

  /**
  * Return value of numeric field E01BRNPHN as a BigDecimal.
  */
  public BigDecimal getBigDecimalE01BRNPHN()
  {
    return fieldE01BRNPHN.getBigDecimal();
  }

  /**
  * Set field E01BRNCLB using a String value.
  */
  public void setE01BRNCLB(String newvalue)
  {
    fieldE01BRNCLB.setString(newvalue);
  }

  /**
  * Get value of field E01BRNCLB as a String.
  */
  public String getE01BRNCLB()
  {
    return fieldE01BRNCLB.getString();
  }

  /**
  * Set numeric field E01BRNCLB using a BigDecimal value.
  */
  public void setE01BRNCLB(BigDecimal newvalue)
  {
    fieldE01BRNCLB.setBigDecimal(newvalue);
  }

  /**
  * Return value of numeric field E01BRNCLB as a BigDecimal.
  */
  public BigDecimal getBigDecimalE01BRNCLB()
  {
    return fieldE01BRNCLB.getBigDecimal();
  }

  /**
  * Set field E01CLRNME using a String value.
  */
  public void setE01CLRNME(String newvalue)
  {
    fieldE01CLRNME.setString(newvalue);
  }

  /**
  * Get value of field E01CLRNME as a String.
  */
  public String getE01CLRNME()
  {
    return fieldE01CLRNME.getString();
  }

  /**
  * Set field E01BRNRGN using a String value.
  */
  public void setE01BRNRGN(String newvalue)
  {
    fieldE01BRNRGN.setString(newvalue);
  }

  /**
  * Get value of field E01BRNRGN as a String.
  */
  public String getE01BRNRGN()
  {
    return fieldE01BRNRGN.getString();
  }

  /**
  * Set field E01RGNNME using a String value.
  */
  public void setE01RGNNME(String newvalue)
  {
    fieldE01RGNNME.setString(newvalue);
  }

  /**
  * Get value of field E01RGNNME as a String.
  */
  public String getE01RGNNME()
  {
    return fieldE01RGNNME.getString();
  }

  /**
  * Set field E01BRNSBR using a String value.
  */
  public void setE01BRNSBR(String newvalue)
  {
    fieldE01BRNSBR.setString(newvalue);
  }

  /**
  * Get value of field E01BRNSBR as a String.
  */
  public String getE01BRNSBR()
  {
    return fieldE01BRNSBR.getString();
  }

  /**
  * Set field E01SBRNME using a String value.
  */
  public void setE01SBRNME(String newvalue)
  {
    fieldE01SBRNME.setString(newvalue);
  }

  /**
  * Get value of field E01SBRNME as a String.
  */
  public String getE01SBRNME()
  {
    return fieldE01SBRNME.getString();
  }

  /**
  * Set field E01BRNDID using a String value.
  */
  public void setE01BRNDID(String newvalue)
  {
    fieldE01BRNDID.setString(newvalue);
  }

  /**
  * Get value of field E01BRNDID as a String.
  */
  public String getE01BRNDID()
  {
    return fieldE01BRNDID.getString();
  }

  /**
  * Set field E01BRNUS1 using a String value.
  */
  public void setE01BRNUS1(String newvalue)
  {
    fieldE01BRNUS1.setString(newvalue);
  }

  /**
  * Get value of field E01BRNUS1 as a String.
  */
  public String getE01BRNUS1()
  {
    return fieldE01BRNUS1.getString();
  }

  /**
  * Set field E01US1NME using a String value.
  */
  public void setE01US1NME(String newvalue)
  {
    fieldE01US1NME.setString(newvalue);
  }

  /**
  * Get value of field E01US1NME as a String.
  */
  public String getE01US1NME()
  {
    return fieldE01US1NME.getString();
  }

  /**
  * Set field E01BRNUS2 using a String value.
  */
  public void setE01BRNUS2(String newvalue)
  {
    fieldE01BRNUS2.setString(newvalue);
  }

  /**
  * Get value of field E01BRNUS2 as a String.
  */
  public String getE01BRNUS2()
  {
    return fieldE01BRNUS2.getString();
  }

  /**
  * Set field E01US2NME using a String value.
  */
  public void setE01US2NME(String newvalue)
  {
    fieldE01US2NME.setString(newvalue);
  }

  /**
  * Get value of field E01US2NME as a String.
  */
  public String getE01US2NME()
  {
    return fieldE01US2NME.getString();
  }

  /**
  * Set field E01BRNFL1 using a String value.
  */
  public void setE01BRNFL1(String newvalue)
  {
    fieldE01BRNFL1.setString(newvalue);
  }

  /**
  * Get value of field E01BRNFL1 as a String.
  */
  public String getE01BRNFL1()
  {
    return fieldE01BRNFL1.getString();
  }

  /**
  * Set field E01BRNFL2 using a String value.
  */
  public void setE01BRNFL2(String newvalue)
  {
    fieldE01BRNFL2.setString(newvalue);
  }

  /**
  * Get value of field E01BRNFL2 as a String.
  */
  public String getE01BRNFL2()
  {
    return fieldE01BRNFL2.getString();
  }

  /**
  * Set field E01OPECDE using a String value.
  */
  public void setE01OPECDE(String newvalue)
  {
    fieldE01OPECDE.setString(newvalue);
  }

  /**
  * Get value of field E01OPECDE as a String.
  */
  public String getE01OPECDE()
  {
    return fieldE01OPECDE.getString();
  }

}
