package datapro.eibs.beans;

import datapro.eibs.sockets.*;
import java.io.*;
import java.math.*;

import java.util.*;

/**
* Class generated by AS/400 CRTCLASS command from EDD093301 physical file definition.
* 
* File level identifier is 1051128113551.
* Record format level identifier is 3936D90A46EDD.
*/

public class EDD093301Message extends MessageRecord
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
                                     "E01CNTFE1",
                                     "E01CNTFE2",
                                     "E01CNTFE3",
                                     "E01USERCO",
                                     "E01TIMEDY",
                                     "E01MOTIVO",
                                     "E01TIPOCA",
                                     "E01PROCESO",
                                     "E01STSCAMI",
                                     "E01STSUSRI",
                                     "E01STSFECI",
                                     "E01STSTIMI",
                                     "E01STSCAMO",
                                     "E01STSUSRO",
                                     "E01STSFECO",
                                     "E01STSTIMO"
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
                                   "E01CNTFE1",
                                   "E01CNTFE2",
                                   "E01CNTFE3",
                                   "E01USERCO",
                                   "E01TIMEDY",
                                   "E01MOTIVO",
                                   "E01TIPOCA",
                                   "E01PROCESO",
                                   "E01STSCAMI",
                                   "E01STSUSRI",
                                   "E01STSFECI",
                                   "E01STSTIMI",
                                   "E01STSCAMO",
                                   "E01STSUSRO",
                                   "E01STSFECO",
                                   "E01STSTIMO"
                                 };
  final static String fid = "1051128113551";
  final static String rid = "3936D90A46EDD";
  final static String fmtname = "EDD093301";
  final int FIELDCOUNT = 25;
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
  private DecimalField fieldE01CNTFE1 = null;
  private DecimalField fieldE01CNTFE2 = null;
  private DecimalField fieldE01CNTFE3 = null;
  private CharacterField fieldE01USERCO = null;
  private DecimalField fieldE01TIMEDY = null;
  private CharacterField fieldE01MOTIVO = null;
  private CharacterField fieldE01TIPOCA = null;
  private CharacterField fieldE01PROCESO = null;
  private CharacterField fieldE01STSCAMI = null;
  private CharacterField fieldE01STSUSRI = null;
  private DecimalField fieldE01STSFECI = null;
  private DecimalField fieldE01STSTIMI = null;
  private CharacterField fieldE01STSCAMO = null;
  private CharacterField fieldE01STSUSRO = null;
  private DecimalField fieldE01STSFECO = null;
  private DecimalField fieldE01STSTIMO = null;

  /**
  * Constructor for EDD093301Message.
  */
  public EDD093301Message()
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
    recordsize = 170;
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
    fields[9] = fieldE01CNTFE1 =
    new DecimalField(message, HEADERSIZE + 42, 3, 0, "E01CNTFE1");
    fields[10] = fieldE01CNTFE2 =
    new DecimalField(message, HEADERSIZE + 45, 3, 0, "E01CNTFE2");
    fields[11] = fieldE01CNTFE3 =
    new DecimalField(message, HEADERSIZE + 48, 3, 0, "E01CNTFE3");
    fields[12] = fieldE01USERCO =
    new CharacterField(message, HEADERSIZE + 51, 10, "E01USERCO");
    fields[13] = fieldE01TIMEDY =
    new DecimalField(message, HEADERSIZE + 61, 7, 0, "E01TIMEDY");
    fields[14] = fieldE01MOTIVO =
    new CharacterField(message, HEADERSIZE + 68, 50, "E01MOTIVO");
    fields[15] = fieldE01TIPOCA =
    new CharacterField(message, HEADERSIZE + 118, 1, "E01TIPOCA");
    fields[16] = fieldE01PROCESO =
    new CharacterField(message, HEADERSIZE + 119, 1, "E01PROCESO");
    fields[17] = fieldE01STSCAMI =
    new CharacterField(message, HEADERSIZE + 120, 1, "E01STSCAMI");
    fields[18] = fieldE01STSUSRI =
    new CharacterField(message, HEADERSIZE + 121, 10, "E01STSUSRI");
    fields[19] = fieldE01STSFECI =
    new DecimalField(message, HEADERSIZE + 131, 7, 0, "E01STSFECI");
    fields[20] = fieldE01STSTIMI =
    new DecimalField(message, HEADERSIZE + 138, 7, 0, "E01STSTIMI");
    fields[21] = fieldE01STSCAMO =
    new CharacterField(message, HEADERSIZE + 145, 1, "E01STSCAMO");
    fields[22] = fieldE01STSUSRO =
    new CharacterField(message, HEADERSIZE + 146, 10, "E01STSUSRO");
    fields[23] = fieldE01STSFECO =
    new DecimalField(message, HEADERSIZE + 156, 7, 0, "E01STSFECO");
    fields[24] = fieldE01STSTIMO =
    new DecimalField(message, HEADERSIZE + 163, 7, 0, "E01STSTIMO");

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
  * Set field E01CNTFE1 using a String value.
  */
  public void setE01CNTFE1(String newvalue)
  {
    fieldE01CNTFE1.setString(newvalue);
  }

  /**
  * Get value of field E01CNTFE1 as a String.
  */
  public String getE01CNTFE1()
  {
    return fieldE01CNTFE1.getString();
  }

  /**
  * Set numeric field E01CNTFE1 using a BigDecimal value.
  */
  public void setE01CNTFE1(BigDecimal newvalue)
  {
    fieldE01CNTFE1.setBigDecimal(newvalue);
  }

  /**
  * Return value of numeric field E01CNTFE1 as a BigDecimal.
  */
  public BigDecimal getBigDecimalE01CNTFE1()
  {
    return fieldE01CNTFE1.getBigDecimal();
  }

  /**
  * Set field E01CNTFE2 using a String value.
  */
  public void setE01CNTFE2(String newvalue)
  {
    fieldE01CNTFE2.setString(newvalue);
  }

  /**
  * Get value of field E01CNTFE2 as a String.
  */
  public String getE01CNTFE2()
  {
    return fieldE01CNTFE2.getString();
  }

  /**
  * Set numeric field E01CNTFE2 using a BigDecimal value.
  */
  public void setE01CNTFE2(BigDecimal newvalue)
  {
    fieldE01CNTFE2.setBigDecimal(newvalue);
  }

  /**
  * Return value of numeric field E01CNTFE2 as a BigDecimal.
  */
  public BigDecimal getBigDecimalE01CNTFE2()
  {
    return fieldE01CNTFE2.getBigDecimal();
  }

  /**
  * Set field E01CNTFE3 using a String value.
  */
  public void setE01CNTFE3(String newvalue)
  {
    fieldE01CNTFE3.setString(newvalue);
  }

  /**
  * Get value of field E01CNTFE3 as a String.
  */
  public String getE01CNTFE3()
  {
    return fieldE01CNTFE3.getString();
  }

  /**
  * Set numeric field E01CNTFE3 using a BigDecimal value.
  */
  public void setE01CNTFE3(BigDecimal newvalue)
  {
    fieldE01CNTFE3.setBigDecimal(newvalue);
  }

  /**
  * Return value of numeric field E01CNTFE3 as a BigDecimal.
  */
  public BigDecimal getBigDecimalE01CNTFE3()
  {
    return fieldE01CNTFE3.getBigDecimal();
  }

  /**
  * Set field E01USERCO using a String value.
  */
  public void setE01USERCO(String newvalue)
  {
    fieldE01USERCO.setString(newvalue);
  }

  /**
  * Get value of field E01USERCO as a String.
  */
  public String getE01USERCO()
  {
    return fieldE01USERCO.getString();
  }

  /**
  * Set field E01TIMEDY using a String value.
  */
  public void setE01TIMEDY(String newvalue)
  {
    fieldE01TIMEDY.setString(newvalue);
  }

  /**
  * Get value of field E01TIMEDY as a String.
  */
  public String getE01TIMEDY()
  {
    return fieldE01TIMEDY.getString();
  }

  /**
  * Set numeric field E01TIMEDY using a BigDecimal value.
  */
  public void setE01TIMEDY(BigDecimal newvalue)
  {
    fieldE01TIMEDY.setBigDecimal(newvalue);
  }

  /**
  * Return value of numeric field E01TIMEDY as a BigDecimal.
  */
  public BigDecimal getBigDecimalE01TIMEDY()
  {
    return fieldE01TIMEDY.getBigDecimal();
  }

  /**
  * Set field E01MOTIVO using a String value.
  */
  public void setE01MOTIVO(String newvalue)
  {
    fieldE01MOTIVO.setString(newvalue);
  }

  /**
  * Get value of field E01MOTIVO as a String.
  */
  public String getE01MOTIVO()
  {
    return fieldE01MOTIVO.getString();
  }

  /**
  * Set field E01TIPOCA using a String value.
  */
  public void setE01TIPOCA(String newvalue)
  {
    fieldE01TIPOCA.setString(newvalue);
  }

  /**
  * Get value of field E01TIPOCA as a String.
  */
  public String getE01TIPOCA()
  {
    return fieldE01TIPOCA.getString();
  }

  /**
  * Set field E01PROCESO using a String value.
  */
  public void setE01PROCESO(String newvalue)
  {
    fieldE01PROCESO.setString(newvalue);
  }

  /**
  * Get value of field E01PROCESO as a String.
  */
  public String getE01PROCESO()
  {
    return fieldE01PROCESO.getString();
  }

  /**
  * Set field E01STSCAMI using a String value.
  */
  public void setE01STSCAMI(String newvalue)
  {
    fieldE01STSCAMI.setString(newvalue);
  }

  /**
  * Get value of field E01STSCAMI as a String.
  */
  public String getE01STSCAMI()
  {
    return fieldE01STSCAMI.getString();
  }

  /**
  * Set field E01STSUSRI using a String value.
  */
  public void setE01STSUSRI(String newvalue)
  {
    fieldE01STSUSRI.setString(newvalue);
  }

  /**
  * Get value of field E01STSUSRI as a String.
  */
  public String getE01STSUSRI()
  {
    return fieldE01STSUSRI.getString();
  }

  /**
  * Set field E01STSFECI using a String value.
  */
  public void setE01STSFECI(String newvalue)
  {
    fieldE01STSFECI.setString(newvalue);
  }

  /**
  * Get value of field E01STSFECI as a String.
  */
  public String getE01STSFECI()
  {
    return fieldE01STSFECI.getString();
  }

  /**
  * Set numeric field E01STSFECI using a BigDecimal value.
  */
  public void setE01STSFECI(BigDecimal newvalue)
  {
    fieldE01STSFECI.setBigDecimal(newvalue);
  }

  /**
  * Return value of numeric field E01STSFECI as a BigDecimal.
  */
  public BigDecimal getBigDecimalE01STSFECI()
  {
    return fieldE01STSFECI.getBigDecimal();
  }

  /**
  * Set field E01STSTIMI using a String value.
  */
  public void setE01STSTIMI(String newvalue)
  {
    fieldE01STSTIMI.setString(newvalue);
  }

  /**
  * Get value of field E01STSTIMI as a String.
  */
  public String getE01STSTIMI()
  {
    return fieldE01STSTIMI.getString();
  }

  /**
  * Set numeric field E01STSTIMI using a BigDecimal value.
  */
  public void setE01STSTIMI(BigDecimal newvalue)
  {
    fieldE01STSTIMI.setBigDecimal(newvalue);
  }

  /**
  * Return value of numeric field E01STSTIMI as a BigDecimal.
  */
  public BigDecimal getBigDecimalE01STSTIMI()
  {
    return fieldE01STSTIMI.getBigDecimal();
  }

  /**
  * Set field E01STSCAMO using a String value.
  */
  public void setE01STSCAMO(String newvalue)
  {
    fieldE01STSCAMO.setString(newvalue);
  }

  /**
  * Get value of field E01STSCAMO as a String.
  */
  public String getE01STSCAMO()
  {
    return fieldE01STSCAMO.getString();
  }

  /**
  * Set field E01STSUSRO using a String value.
  */
  public void setE01STSUSRO(String newvalue)
  {
    fieldE01STSUSRO.setString(newvalue);
  }

  /**
  * Get value of field E01STSUSRO as a String.
  */
  public String getE01STSUSRO()
  {
    return fieldE01STSUSRO.getString();
  }

  /**
  * Set field E01STSFECO using a String value.
  */
  public void setE01STSFECO(String newvalue)
  {
    fieldE01STSFECO.setString(newvalue);
  }

  /**
  * Get value of field E01STSFECO as a String.
  */
  public String getE01STSFECO()
  {
    return fieldE01STSFECO.getString();
  }

  /**
  * Set numeric field E01STSFECO using a BigDecimal value.
  */
  public void setE01STSFECO(BigDecimal newvalue)
  {
    fieldE01STSFECO.setBigDecimal(newvalue);
  }

  /**
  * Return value of numeric field E01STSFECO as a BigDecimal.
  */
  public BigDecimal getBigDecimalE01STSFECO()
  {
    return fieldE01STSFECO.getBigDecimal();
  }

  /**
  * Set field E01STSTIMO using a String value.
  */
  public void setE01STSTIMO(String newvalue)
  {
    fieldE01STSTIMO.setString(newvalue);
  }

  /**
  * Get value of field E01STSTIMO as a String.
  */
  public String getE01STSTIMO()
  {
    return fieldE01STSTIMO.getString();
  }

  /**
  * Set numeric field E01STSTIMO using a BigDecimal value.
  */
  public void setE01STSTIMO(BigDecimal newvalue)
  {
    fieldE01STSTIMO.setBigDecimal(newvalue);
  }

  /**
  * Return value of numeric field E01STSTIMO as a BigDecimal.
  */
  public BigDecimal getBigDecimalE01STSTIMO()
  {
    return fieldE01STSTIMO.getBigDecimal();
  }

}
