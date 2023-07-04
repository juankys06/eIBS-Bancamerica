package datapro.eibs.beans;

import datapro.eibs.sockets.*;
import java.io.*;
import java.math.*;

import java.util.*;

/**
* Class generated by AS/400 CRTCLASS command from ESI000001 physical file definition.
* 
* File level identifier is 1051027173351.
* Record format level identifier is 4E07E8047863F.
*/

public class ESI000001Message extends MessageRecord
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
                                     "E01WTIPOI",
                                     "E01WRIF",
                                     "E01WMES",
                                     "E01WANO",
                                     "E01WNOMBR",
                                     "E01ACTIV",
                                     "E01WCLASI",
                                     "E01WHIPOT",
                                     "E01WLIMIT",
                                     "E01WSALVI",
                                     "E01WSALVE",
                                     "E01WSALLI",
                                     "E01WSALRE",
                                     "E01WSUBTA",
                                     "E01WTOTTA",
                                     "E01WCANTA",
                                     "E01WMODAL",
                                     "E01WCOMBI",
                                     "E01NUMREC",
                                     "E01INDOPE"
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
                                   "E01WTIPOI",
                                   "E01WRIF",
                                   "E01WMES",
                                   "E01WANO",
                                   "E01WNOMBR",
                                   "E01ACTIV",
                                   "E01WCLASI",
                                   "E01WHIPOT",
                                   "E01WLIMIT",
                                   "E01WSALVI",
                                   "E01WSALVE",
                                   "E01WSALLI",
                                   "E01WSALRE",
                                   "E01WSUBTA",
                                   "E01WTOTTA",
                                   "E01WCANTA",
                                   "E01WMODAL",
                                   "E01WCOMBI",
                                   "E01NUMREC",
                                   "E01INDOPE"
                                 };
  final static String fid = "1051027173351";
  final static String rid = "4E07E8047863F";
  final static String fmtname = "ESI000001";
  final int FIELDCOUNT = 29;
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
  private CharacterField fieldE01WTIPOI = null;
  private DecimalField fieldE01WRIF = null;
  private DecimalField fieldE01WMES = null;
  private DecimalField fieldE01WANO = null;
  private CharacterField fieldE01WNOMBR = null;
  private DecimalField fieldE01ACTIV = null;
  private CharacterField fieldE01WCLASI = null;
  private CharacterField fieldE01WHIPOT = null;
  private DecimalField fieldE01WLIMIT = null;
  private DecimalField fieldE01WSALVI = null;
  private DecimalField fieldE01WSALVE = null;
  private DecimalField fieldE01WSALLI = null;
  private DecimalField fieldE01WSALRE = null;
  private DecimalField fieldE01WSUBTA = null;
  private DecimalField fieldE01WTOTTA = null;
  private DecimalField fieldE01WCANTA = null;
  private CharacterField fieldE01WMODAL = null;
  private DecimalField fieldE01WCOMBI = null;
  private DecimalField fieldE01NUMREC = null;
  private CharacterField fieldE01INDOPE = null;

  /**
  * Constructor for ESI000001Message.
  */
  public ESI000001Message()
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
    recordsize = 293;
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
    fields[9] = fieldE01WTIPOI =
    new CharacterField(message, HEADERSIZE + 42, 1, "E01WTIPOI");
    fields[10] = fieldE01WRIF =
    new DecimalField(message, HEADERSIZE + 43, 20, 0, "E01WRIF");
    fields[11] = fieldE01WMES =
    new DecimalField(message, HEADERSIZE + 63, 3, 0, "E01WMES");
    fields[12] = fieldE01WANO =
    new DecimalField(message, HEADERSIZE + 66, 5, 0, "E01WANO");
    fields[13] = fieldE01WNOMBR =
    new CharacterField(message, HEADERSIZE + 71, 50, "E01WNOMBR");
    fields[14] = fieldE01ACTIV =
    new DecimalField(message, HEADERSIZE + 121, 3, 0, "E01ACTIV");
    fields[15] = fieldE01WCLASI =
    new CharacterField(message, HEADERSIZE + 124, 1, "E01WCLASI");
    fields[16] = fieldE01WHIPOT =
    new CharacterField(message, HEADERSIZE + 125, 1, "E01WHIPOT");
    fields[17] = fieldE01WLIMIT =
    new DecimalField(message, HEADERSIZE + 126, 19, 0, "E01WLIMIT");
    fields[18] = fieldE01WSALVI =
    new DecimalField(message, HEADERSIZE + 145, 22, 2, "E01WSALVI");
    fields[19] = fieldE01WSALVE =
    new DecimalField(message, HEADERSIZE + 167, 22, 2, "E01WSALVE");
    fields[20] = fieldE01WSALLI =
    new DecimalField(message, HEADERSIZE + 189, 22, 2, "E01WSALLI");
    fields[21] = fieldE01WSALRE =
    new DecimalField(message, HEADERSIZE + 211, 22, 2, "E01WSALRE");
    fields[22] = fieldE01WSUBTA =
    new DecimalField(message, HEADERSIZE + 233, 22, 2, "E01WSUBTA");
    fields[23] = fieldE01WTOTTA =
    new DecimalField(message, HEADERSIZE + 255, 22, 2, "E01WTOTTA");
    fields[24] = fieldE01WCANTA =
    new DecimalField(message, HEADERSIZE + 277, 3, 0, "E01WCANTA");
    fields[25] = fieldE01WMODAL =
    new CharacterField(message, HEADERSIZE + 280, 1, "E01WMODAL");
    fields[26] = fieldE01WCOMBI =
    new DecimalField(message, HEADERSIZE + 281, 3, 0, "E01WCOMBI");
    fields[27] = fieldE01NUMREC =
    new DecimalField(message, HEADERSIZE + 284, 8, 0, "E01NUMREC");
    fields[28] = fieldE01INDOPE =
    new CharacterField(message, HEADERSIZE + 292, 1, "E01INDOPE");

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
  * Set field E01WTIPOI using a String value.
  */
  public void setE01WTIPOI(String newvalue)
  {
    fieldE01WTIPOI.setString(newvalue);
  }

  /**
  * Get value of field E01WTIPOI as a String.
  */
  public String getE01WTIPOI()
  {
    return fieldE01WTIPOI.getString();
  }

  /**
  * Set field E01WRIF using a String value.
  */
  public void setE01WRIF(String newvalue)
  {
    fieldE01WRIF.setString(newvalue);
  }

  /**
  * Get value of field E01WRIF as a String.
  */
  public String getE01WRIF()
  {
    return fieldE01WRIF.getString();
  }

  /**
  * Set numeric field E01WRIF using a BigDecimal value.
  */
  public void setE01WRIF(BigDecimal newvalue)
  {
    fieldE01WRIF.setBigDecimal(newvalue);
  }

  /**
  * Return value of numeric field E01WRIF as a BigDecimal.
  */
  public BigDecimal getBigDecimalE01WRIF()
  {
    return fieldE01WRIF.getBigDecimal();
  }

  /**
  * Set field E01WMES using a String value.
  */
  public void setE01WMES(String newvalue)
  {
    fieldE01WMES.setString(newvalue);
  }

  /**
  * Get value of field E01WMES as a String.
  */
  public String getE01WMES()
  {
    return fieldE01WMES.getString();
  }

  /**
  * Set numeric field E01WMES using a BigDecimal value.
  */
  public void setE01WMES(BigDecimal newvalue)
  {
    fieldE01WMES.setBigDecimal(newvalue);
  }

  /**
  * Return value of numeric field E01WMES as a BigDecimal.
  */
  public BigDecimal getBigDecimalE01WMES()
  {
    return fieldE01WMES.getBigDecimal();
  }

  /**
  * Set field E01WANO using a String value.
  */
  public void setE01WANO(String newvalue)
  {
    fieldE01WANO.setString(newvalue);
  }

  /**
  * Get value of field E01WANO as a String.
  */
  public String getE01WANO()
  {
    return fieldE01WANO.getString();
  }

  /**
  * Set numeric field E01WANO using a BigDecimal value.
  */
  public void setE01WANO(BigDecimal newvalue)
  {
    fieldE01WANO.setBigDecimal(newvalue);
  }

  /**
  * Return value of numeric field E01WANO as a BigDecimal.
  */
  public BigDecimal getBigDecimalE01WANO()
  {
    return fieldE01WANO.getBigDecimal();
  }

  /**
  * Set field E01WNOMBR using a String value.
  */
  public void setE01WNOMBR(String newvalue)
  {
    fieldE01WNOMBR.setString(newvalue);
  }

  /**
  * Get value of field E01WNOMBR as a String.
  */
  public String getE01WNOMBR()
  {
    return fieldE01WNOMBR.getString();
  }

  /**
  * Set field E01ACTIV using a String value.
  */
  public void setE01ACTIV(String newvalue)
  {
    fieldE01ACTIV.setString(newvalue);
  }

  /**
  * Get value of field E01ACTIV as a String.
  */
  public String getE01ACTIV()
  {
    return fieldE01ACTIV.getString();
  }

  /**
  * Set numeric field E01ACTIV using a BigDecimal value.
  */
  public void setE01ACTIV(BigDecimal newvalue)
  {
    fieldE01ACTIV.setBigDecimal(newvalue);
  }

  /**
  * Return value of numeric field E01ACTIV as a BigDecimal.
  */
  public BigDecimal getBigDecimalE01ACTIV()
  {
    return fieldE01ACTIV.getBigDecimal();
  }

  /**
  * Set field E01WCLASI using a String value.
  */
  public void setE01WCLASI(String newvalue)
  {
    fieldE01WCLASI.setString(newvalue);
  }

  /**
  * Get value of field E01WCLASI as a String.
  */
  public String getE01WCLASI()
  {
    return fieldE01WCLASI.getString();
  }

  /**
  * Set field E01WHIPOT using a String value.
  */
  public void setE01WHIPOT(String newvalue)
  {
    fieldE01WHIPOT.setString(newvalue);
  }

  /**
  * Get value of field E01WHIPOT as a String.
  */
  public String getE01WHIPOT()
  {
    return fieldE01WHIPOT.getString();
  }

  /**
  * Set field E01WLIMIT using a String value.
  */
  public void setE01WLIMIT(String newvalue)
  {
    fieldE01WLIMIT.setString(newvalue);
  }

  /**
  * Get value of field E01WLIMIT as a String.
  */
  public String getE01WLIMIT()
  {
    return fieldE01WLIMIT.getString();
  }

  /**
  * Set numeric field E01WLIMIT using a BigDecimal value.
  */
  public void setE01WLIMIT(BigDecimal newvalue)
  {
    fieldE01WLIMIT.setBigDecimal(newvalue);
  }

  /**
  * Return value of numeric field E01WLIMIT as a BigDecimal.
  */
  public BigDecimal getBigDecimalE01WLIMIT()
  {
    return fieldE01WLIMIT.getBigDecimal();
  }

  /**
  * Set field E01WSALVI using a String value.
  */
  public void setE01WSALVI(String newvalue)
  {
    fieldE01WSALVI.setString(newvalue);
  }

  /**
  * Get value of field E01WSALVI as a String.
  */
  public String getE01WSALVI()
  {
    return fieldE01WSALVI.getString();
  }

  /**
  * Set numeric field E01WSALVI using a BigDecimal value.
  */
  public void setE01WSALVI(BigDecimal newvalue)
  {
    fieldE01WSALVI.setBigDecimal(newvalue);
  }

  /**
  * Return value of numeric field E01WSALVI as a BigDecimal.
  */
  public BigDecimal getBigDecimalE01WSALVI()
  {
    return fieldE01WSALVI.getBigDecimal();
  }

  /**
  * Set field E01WSALVE using a String value.
  */
  public void setE01WSALVE(String newvalue)
  {
    fieldE01WSALVE.setString(newvalue);
  }

  /**
  * Get value of field E01WSALVE as a String.
  */
  public String getE01WSALVE()
  {
    return fieldE01WSALVE.getString();
  }

  /**
  * Set numeric field E01WSALVE using a BigDecimal value.
  */
  public void setE01WSALVE(BigDecimal newvalue)
  {
    fieldE01WSALVE.setBigDecimal(newvalue);
  }

  /**
  * Return value of numeric field E01WSALVE as a BigDecimal.
  */
  public BigDecimal getBigDecimalE01WSALVE()
  {
    return fieldE01WSALVE.getBigDecimal();
  }

  /**
  * Set field E01WSALLI using a String value.
  */
  public void setE01WSALLI(String newvalue)
  {
    fieldE01WSALLI.setString(newvalue);
  }

  /**
  * Get value of field E01WSALLI as a String.
  */
  public String getE01WSALLI()
  {
    return fieldE01WSALLI.getString();
  }

  /**
  * Set numeric field E01WSALLI using a BigDecimal value.
  */
  public void setE01WSALLI(BigDecimal newvalue)
  {
    fieldE01WSALLI.setBigDecimal(newvalue);
  }

  /**
  * Return value of numeric field E01WSALLI as a BigDecimal.
  */
  public BigDecimal getBigDecimalE01WSALLI()
  {
    return fieldE01WSALLI.getBigDecimal();
  }

  /**
  * Set field E01WSALRE using a String value.
  */
  public void setE01WSALRE(String newvalue)
  {
    fieldE01WSALRE.setString(newvalue);
  }

  /**
  * Get value of field E01WSALRE as a String.
  */
  public String getE01WSALRE()
  {
    return fieldE01WSALRE.getString();
  }

  /**
  * Set numeric field E01WSALRE using a BigDecimal value.
  */
  public void setE01WSALRE(BigDecimal newvalue)
  {
    fieldE01WSALRE.setBigDecimal(newvalue);
  }

  /**
  * Return value of numeric field E01WSALRE as a BigDecimal.
  */
  public BigDecimal getBigDecimalE01WSALRE()
  {
    return fieldE01WSALRE.getBigDecimal();
  }

  /**
  * Set field E01WSUBTA using a String value.
  */
  public void setE01WSUBTA(String newvalue)
  {
    fieldE01WSUBTA.setString(newvalue);
  }

  /**
  * Get value of field E01WSUBTA as a String.
  */
  public String getE01WSUBTA()
  {
    return fieldE01WSUBTA.getString();
  }

  /**
  * Set numeric field E01WSUBTA using a BigDecimal value.
  */
  public void setE01WSUBTA(BigDecimal newvalue)
  {
    fieldE01WSUBTA.setBigDecimal(newvalue);
  }

  /**
  * Return value of numeric field E01WSUBTA as a BigDecimal.
  */
  public BigDecimal getBigDecimalE01WSUBTA()
  {
    return fieldE01WSUBTA.getBigDecimal();
  }

  /**
  * Set field E01WTOTTA using a String value.
  */
  public void setE01WTOTTA(String newvalue)
  {
    fieldE01WTOTTA.setString(newvalue);
  }

  /**
  * Get value of field E01WTOTTA as a String.
  */
  public String getE01WTOTTA()
  {
    return fieldE01WTOTTA.getString();
  }

  /**
  * Set numeric field E01WTOTTA using a BigDecimal value.
  */
  public void setE01WTOTTA(BigDecimal newvalue)
  {
    fieldE01WTOTTA.setBigDecimal(newvalue);
  }

  /**
  * Return value of numeric field E01WTOTTA as a BigDecimal.
  */
  public BigDecimal getBigDecimalE01WTOTTA()
  {
    return fieldE01WTOTTA.getBigDecimal();
  }

  /**
  * Set field E01WCANTA using a String value.
  */
  public void setE01WCANTA(String newvalue)
  {
    fieldE01WCANTA.setString(newvalue);
  }

  /**
  * Get value of field E01WCANTA as a String.
  */
  public String getE01WCANTA()
  {
    return fieldE01WCANTA.getString();
  }

  /**
  * Set numeric field E01WCANTA using a BigDecimal value.
  */
  public void setE01WCANTA(BigDecimal newvalue)
  {
    fieldE01WCANTA.setBigDecimal(newvalue);
  }

  /**
  * Return value of numeric field E01WCANTA as a BigDecimal.
  */
  public BigDecimal getBigDecimalE01WCANTA()
  {
    return fieldE01WCANTA.getBigDecimal();
  }

  /**
  * Set field E01WMODAL using a String value.
  */
  public void setE01WMODAL(String newvalue)
  {
    fieldE01WMODAL.setString(newvalue);
  }

  /**
  * Get value of field E01WMODAL as a String.
  */
  public String getE01WMODAL()
  {
    return fieldE01WMODAL.getString();
  }

  /**
  * Set field E01WCOMBI using a String value.
  */
  public void setE01WCOMBI(String newvalue)
  {
    fieldE01WCOMBI.setString(newvalue);
  }

  /**
  * Get value of field E01WCOMBI as a String.
  */
  public String getE01WCOMBI()
  {
    return fieldE01WCOMBI.getString();
  }

  /**
  * Set numeric field E01WCOMBI using a BigDecimal value.
  */
  public void setE01WCOMBI(BigDecimal newvalue)
  {
    fieldE01WCOMBI.setBigDecimal(newvalue);
  }

  /**
  * Return value of numeric field E01WCOMBI as a BigDecimal.
  */
  public BigDecimal getBigDecimalE01WCOMBI()
  {
    return fieldE01WCOMBI.getBigDecimal();
  }

  /**
  * Set field E01NUMREC using a String value.
  */
  public void setE01NUMREC(String newvalue)
  {
    fieldE01NUMREC.setString(newvalue);
  }

  /**
  * Get value of field E01NUMREC as a String.
  */
  public String getE01NUMREC()
  {
    return fieldE01NUMREC.getString();
  }

  /**
  * Set numeric field E01NUMREC using a BigDecimal value.
  */
  public void setE01NUMREC(BigDecimal newvalue)
  {
    fieldE01NUMREC.setBigDecimal(newvalue);
  }

  /**
  * Return value of numeric field E01NUMREC as a BigDecimal.
  */
  public BigDecimal getBigDecimalE01NUMREC()
  {
    return fieldE01NUMREC.getBigDecimal();
  }

  /**
  * Set field E01INDOPE using a String value.
  */
  public void setE01INDOPE(String newvalue)
  {
    fieldE01INDOPE.setString(newvalue);
  }

  /**
  * Get value of field E01INDOPE as a String.
  */
  public String getE01INDOPE()
  {
    return fieldE01INDOPE.getString();
  }

}
