package datapro.eibs.beans;


import datapro.eibs.sockets.*;
import java.io.*;
import java.math.*;

import java.util.*;

/**
* Class generated by AS/400 CRTCLASS command from EDP072601 physical file definition.
* 
* File level identifier is 1060403113506.
* Record format level identifier is 5F89DDE4899E7.
*/

public class EDP072601Message extends MessageRecord
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
                                     "E01CNTLAN",
                                     "E01DPEBNK",
                                     "E01DPENPR",
                                     "E01DPEPRD",
                                     "E01DPETYP",
                                     "E01DPEREG",
                                     "E01DPEDRE",
                                     "E01DPEFA1",
                                     "E01DPEFA2",
                                     "E01DPEFA3",
                                     "E01DPZNPR",
                                     "E01DPZGRT",
                                     "E01DPZDGA",
                                     "E01DPZFA1",
                                     "E01DPZFA2",
                                     "E01DPZFA3",
                                     "E01DPZTDO",
                                     "E01DPZDRE",
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
                                   "E01CNTLAN",
                                   "E01DPEBNK",
                                   "E01DPENPR",
                                   "E01DPEPRD",
                                   "E01DPETYP",
                                   "E01DPEREG",
                                   "E01DPEDRE",
                                   "E01DPEFA1",
                                   "E01DPEFA2",
                                   "E01DPEFA3",
                                   "E01DPZNPR",
                                   "E01DPZGRT",
                                   "E01DPZDGA",
                                   "E01DPZFA1",
                                   "E01DPZFA2",
                                   "E01DPZFA3",
                                   "E01DPZTDO",
                                   "E01DPZDRE",
                                   "E01OPECDE"
                                 };
  final static String fid = "1060403113506";
  final static String rid = "5F89DDE4899E7";
  final static String fmtname = "EDP072601";
  final int FIELDCOUNT = 28;
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
  private CharacterField fieldE01CNTLAN = null;
  private CharacterField fieldE01DPEBNK = null;
  private DecimalField fieldE01DPENPR = null;
  private CharacterField fieldE01DPEPRD = null;
  private CharacterField fieldE01DPETYP = null;
  private CharacterField fieldE01DPEREG = null;
  private CharacterField fieldE01DPEDRE = null;
  private DecimalField fieldE01DPEFA1 = null;
  private DecimalField fieldE01DPEFA2 = null;
  private DecimalField fieldE01DPEFA3 = null;
  private DecimalField fieldE01DPZNPR = null;
  private CharacterField fieldE01DPZGRT = null;
  private CharacterField fieldE01DPZDGA = null;
  private DecimalField fieldE01DPZFA1 = null;
  private DecimalField fieldE01DPZFA2 = null;
  private DecimalField fieldE01DPZFA3 = null;
  private CharacterField fieldE01DPZTDO = null;
  private CharacterField fieldE01DPZDRE = null;
  private CharacterField fieldE01OPECDE = null;

  /**
  * Constructor for EDP072601Message.
  */
  public EDP072601Message()
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
    recordsize = 181;
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
    fields[9] = fieldE01CNTLAN =
    new CharacterField(message, HEADERSIZE + 42, 1, "E01CNTLAN");
    fields[10] = fieldE01DPEBNK =
    new CharacterField(message, HEADERSIZE + 43, 2, "E01DPEBNK");
    fields[11] = fieldE01DPENPR =
    new DecimalField(message, HEADERSIZE + 45, 13, 0, "E01DPENPR");
    fields[12] = fieldE01DPEPRD =
    new CharacterField(message, HEADERSIZE + 58, 4, "E01DPEPRD");
    fields[13] = fieldE01DPETYP =
    new CharacterField(message, HEADERSIZE + 62, 4, "E01DPETYP");
    fields[14] = fieldE01DPEREG =
    new CharacterField(message, HEADERSIZE + 66, 4, "E01DPEREG");
    fields[15] = fieldE01DPEDRE =
    new CharacterField(message, HEADERSIZE + 70, 35, "E01DPEDRE");
    fields[16] = fieldE01DPEFA1 =
    new DecimalField(message, HEADERSIZE + 105, 3, 0, "E01DPEFA1");
    fields[17] = fieldE01DPEFA2 =
    new DecimalField(message, HEADERSIZE + 108, 3, 0, "E01DPEFA2");
    fields[18] = fieldE01DPEFA3 =
    new DecimalField(message, HEADERSIZE + 111, 3, 0, "E01DPEFA3");
    fields[19] = fieldE01DPZNPR =
    new DecimalField(message, HEADERSIZE + 114, 13, 0, "E01DPZNPR");
    fields[20] = fieldE01DPZGRT =
    new CharacterField(message, HEADERSIZE + 127, 4, "E01DPZGRT");
    fields[21] = fieldE01DPZDGA =
    new CharacterField(message, HEADERSIZE + 131, 4, "E01DPZDGA");
    fields[22] = fieldE01DPZFA1 =
    new DecimalField(message, HEADERSIZE + 135, 3, 0, "E01DPZFA1");
    fields[23] = fieldE01DPZFA2 =
    new DecimalField(message, HEADERSIZE + 138, 3, 0, "E01DPZFA2");
    fields[24] = fieldE01DPZFA3 =
    new DecimalField(message, HEADERSIZE + 141, 3, 0, "E01DPZFA3");
    fields[25] = fieldE01DPZTDO =
    new CharacterField(message, HEADERSIZE + 144, 1, "E01DPZTDO");
    fields[26] = fieldE01DPZDRE =
    new CharacterField(message, HEADERSIZE + 145, 35, "E01DPZDRE");
    fields[27] = fieldE01OPECDE =
    new CharacterField(message, HEADERSIZE + 180, 1, "E01OPECDE");

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
  * Set field E01CNTLAN using a String value.
  */
  public void setE01CNTLAN(String newvalue)
  {
    fieldE01CNTLAN.setString(newvalue);
  }

  /**
  * Get value of field E01CNTLAN as a String.
  */
  public String getE01CNTLAN()
  {
    return fieldE01CNTLAN.getString();
  }

  /**
  * Set field E01DPEBNK using a String value.
  */
  public void setE01DPEBNK(String newvalue)
  {
    fieldE01DPEBNK.setString(newvalue);
  }

  /**
  * Get value of field E01DPEBNK as a String.
  */
  public String getE01DPEBNK()
  {
    return fieldE01DPEBNK.getString();
  }

  /**
  * Set field E01DPENPR using a String value.
  */
  public void setE01DPENPR(String newvalue)
  {
    fieldE01DPENPR.setString(newvalue);
  }

  /**
  * Get value of field E01DPENPR as a String.
  */
  public String getE01DPENPR()
  {
    return fieldE01DPENPR.getString();
  }

  /**
  * Set numeric field E01DPENPR using a BigDecimal value.
  */
  public void setE01DPENPR(BigDecimal newvalue)
  {
    fieldE01DPENPR.setBigDecimal(newvalue);
  }

  /**
  * Return value of numeric field E01DPENPR as a BigDecimal.
  */
  public BigDecimal getBigDecimalE01DPENPR()
  {
    return fieldE01DPENPR.getBigDecimal();
  }

  /**
  * Set field E01DPEPRD using a String value.
  */
  public void setE01DPEPRD(String newvalue)
  {
    fieldE01DPEPRD.setString(newvalue);
  }

  /**
  * Get value of field E01DPEPRD as a String.
  */
  public String getE01DPEPRD()
  {
    return fieldE01DPEPRD.getString();
  }

  /**
  * Set field E01DPETYP using a String value.
  */
  public void setE01DPETYP(String newvalue)
  {
    fieldE01DPETYP.setString(newvalue);
  }

  /**
  * Get value of field E01DPETYP as a String.
  */
  public String getE01DPETYP()
  {
    return fieldE01DPETYP.getString();
  }

  /**
  * Set field E01DPEREG using a String value.
  */
  public void setE01DPEREG(String newvalue)
  {
    fieldE01DPEREG.setString(newvalue);
  }

  /**
  * Get value of field E01DPEREG as a String.
  */
  public String getE01DPEREG()
  {
    return fieldE01DPEREG.getString();
  }

  /**
  * Set field E01DPEDRE using a String value.
  */
  public void setE01DPEDRE(String newvalue)
  {
    fieldE01DPEDRE.setString(newvalue);
  }

  /**
  * Get value of field E01DPEDRE as a String.
  */
  public String getE01DPEDRE()
  {
    return fieldE01DPEDRE.getString();
  }

  /**
  * Set field E01DPEFA1 using a String value.
  */
  public void setE01DPEFA1(String newvalue)
  {
    fieldE01DPEFA1.setString(newvalue);
  }

  /**
  * Get value of field E01DPEFA1 as a String.
  */
  public String getE01DPEFA1()
  {
    return fieldE01DPEFA1.getString();
  }

  /**
  * Set numeric field E01DPEFA1 using a BigDecimal value.
  */
  public void setE01DPEFA1(BigDecimal newvalue)
  {
    fieldE01DPEFA1.setBigDecimal(newvalue);
  }

  /**
  * Return value of numeric field E01DPEFA1 as a BigDecimal.
  */
  public BigDecimal getBigDecimalE01DPEFA1()
  {
    return fieldE01DPEFA1.getBigDecimal();
  }

  /**
  * Set field E01DPEFA2 using a String value.
  */
  public void setE01DPEFA2(String newvalue)
  {
    fieldE01DPEFA2.setString(newvalue);
  }

  /**
  * Get value of field E01DPEFA2 as a String.
  */
  public String getE01DPEFA2()
  {
    return fieldE01DPEFA2.getString();
  }

  /**
  * Set numeric field E01DPEFA2 using a BigDecimal value.
  */
  public void setE01DPEFA2(BigDecimal newvalue)
  {
    fieldE01DPEFA2.setBigDecimal(newvalue);
  }

  /**
  * Return value of numeric field E01DPEFA2 as a BigDecimal.
  */
  public BigDecimal getBigDecimalE01DPEFA2()
  {
    return fieldE01DPEFA2.getBigDecimal();
  }

  /**
  * Set field E01DPEFA3 using a String value.
  */
  public void setE01DPEFA3(String newvalue)
  {
    fieldE01DPEFA3.setString(newvalue);
  }

  /**
  * Get value of field E01DPEFA3 as a String.
  */
  public String getE01DPEFA3()
  {
    return fieldE01DPEFA3.getString();
  }

  /**
  * Set numeric field E01DPEFA3 using a BigDecimal value.
  */
  public void setE01DPEFA3(BigDecimal newvalue)
  {
    fieldE01DPEFA3.setBigDecimal(newvalue);
  }

  /**
  * Return value of numeric field E01DPEFA3 as a BigDecimal.
  */
  public BigDecimal getBigDecimalE01DPEFA3()
  {
    return fieldE01DPEFA3.getBigDecimal();
  }

  /**
  * Set field E01DPZNPR using a String value.
  */
  public void setE01DPZNPR(String newvalue)
  {
    fieldE01DPZNPR.setString(newvalue);
  }

  /**
  * Get value of field E01DPZNPR as a String.
  */
  public String getE01DPZNPR()
  {
    return fieldE01DPZNPR.getString();
  }

  /**
  * Set numeric field E01DPZNPR using a BigDecimal value.
  */
  public void setE01DPZNPR(BigDecimal newvalue)
  {
    fieldE01DPZNPR.setBigDecimal(newvalue);
  }

  /**
  * Return value of numeric field E01DPZNPR as a BigDecimal.
  */
  public BigDecimal getBigDecimalE01DPZNPR()
  {
    return fieldE01DPZNPR.getBigDecimal();
  }

  /**
  * Set field E01DPZGRT using a String value.
  */
  public void setE01DPZGRT(String newvalue)
  {
    fieldE01DPZGRT.setString(newvalue);
  }

  /**
  * Get value of field E01DPZGRT as a String.
  */
  public String getE01DPZGRT()
  {
    return fieldE01DPZGRT.getString();
  }

  /**
  * Set field E01DPZDGA using a String value.
  */
  public void setE01DPZDGA(String newvalue)
  {
    fieldE01DPZDGA.setString(newvalue);
  }

  /**
  * Get value of field E01DPZDGA as a String.
  */
  public String getE01DPZDGA()
  {
    return fieldE01DPZDGA.getString();
  }

  /**
  * Set field E01DPZFA1 using a String value.
  */
  public void setE01DPZFA1(String newvalue)
  {
    fieldE01DPZFA1.setString(newvalue);
  }

  /**
  * Get value of field E01DPZFA1 as a String.
  */
  public String getE01DPZFA1()
  {
    return fieldE01DPZFA1.getString();
  }

  /**
  * Set numeric field E01DPZFA1 using a BigDecimal value.
  */
  public void setE01DPZFA1(BigDecimal newvalue)
  {
    fieldE01DPZFA1.setBigDecimal(newvalue);
  }

  /**
  * Return value of numeric field E01DPZFA1 as a BigDecimal.
  */
  public BigDecimal getBigDecimalE01DPZFA1()
  {
    return fieldE01DPZFA1.getBigDecimal();
  }

  /**
  * Set field E01DPZFA2 using a String value.
  */
  public void setE01DPZFA2(String newvalue)
  {
    fieldE01DPZFA2.setString(newvalue);
  }

  /**
  * Get value of field E01DPZFA2 as a String.
  */
  public String getE01DPZFA2()
  {
    return fieldE01DPZFA2.getString();
  }

  /**
  * Set numeric field E01DPZFA2 using a BigDecimal value.
  */
  public void setE01DPZFA2(BigDecimal newvalue)
  {
    fieldE01DPZFA2.setBigDecimal(newvalue);
  }

  /**
  * Return value of numeric field E01DPZFA2 as a BigDecimal.
  */
  public BigDecimal getBigDecimalE01DPZFA2()
  {
    return fieldE01DPZFA2.getBigDecimal();
  }

  /**
  * Set field E01DPZFA3 using a String value.
  */
  public void setE01DPZFA3(String newvalue)
  {
    fieldE01DPZFA3.setString(newvalue);
  }

  /**
  * Get value of field E01DPZFA3 as a String.
  */
  public String getE01DPZFA3()
  {
    return fieldE01DPZFA3.getString();
  }

  /**
  * Set numeric field E01DPZFA3 using a BigDecimal value.
  */
  public void setE01DPZFA3(BigDecimal newvalue)
  {
    fieldE01DPZFA3.setBigDecimal(newvalue);
  }

  /**
  * Return value of numeric field E01DPZFA3 as a BigDecimal.
  */
  public BigDecimal getBigDecimalE01DPZFA3()
  {
    return fieldE01DPZFA3.getBigDecimal();
  }

  /**
  * Set field E01DPZTDO using a String value.
  */
  public void setE01DPZTDO(String newvalue)
  {
    fieldE01DPZTDO.setString(newvalue);
  }

  /**
  * Get value of field E01DPZTDO as a String.
  */
  public String getE01DPZTDO()
  {
    return fieldE01DPZTDO.getString();
  }

  /**
  * Set field E01DPZDRE using a String value.
  */
  public void setE01DPZDRE(String newvalue)
  {
    fieldE01DPZDRE.setString(newvalue);
  }

  /**
  * Get value of field E01DPZDRE as a String.
  */
  public String getE01DPZDRE()
  {
    return fieldE01DPZDRE.getString();
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
