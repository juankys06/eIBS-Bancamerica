package datapro.eibs.beans;

import datapro.eibs.sockets.*;
import java.io.*;
import java.math.*;

import java.util.*;

/**
* Class generated by AS/400 CRTCLASS command from EPV001002 physical file definition.
* 
* File level identifier is 1041203172159.
* Record format level identifier is 4D3D235E31AC9.
*/

public class EPV001002Message extends MessageRecord
{
  final static String fldnames[] = {
                                     "H02USERID",
                                     "H02PROGRM",
                                     "H02TIMSYS",
                                     "H02SCRCOD",
                                     "H02OPECOD",
                                     "H02FLGMAS",
                                     "H02FLGWK1",
                                     "H02FLGWK2",
                                     "H02FLGWK3",
                                     "E02PVMNUM",
                                     "E02PVMCUN",
                                     "E02PVMNA1",
                                     "E02PVMIDE",
                                     "E02NUMPRO",
                                     "E02NUMSBP",
                                     "E02NUMBCO",
                                     "E02NUMOFI",
                                     "E02DATAVR"
                                   };
  final static String tnames[] = {
                                   "H02USERID",
                                   "H02PROGRM",
                                   "H02TIMSYS",
                                   "H02SCRCOD",
                                   "H02OPECOD",
                                   "H02FLGMAS",
                                   "H02FLGWK1",
                                   "H02FLGWK2",
                                   "H02FLGWK3",
                                   "E02PVMNUM",
                                   "E02PVMCUN",
                                   "E02PVMNA1",
                                   "E02PVMIDE",
                                   "E02NUMPRO",
                                   "E02NUMSBP",
                                   "E02NUMBCO",
                                   "E02NUMOFI",
                                   "E02DATAVR"
                                 };
  final static String fid = "1041203172159";
  final static String rid = "4D3D235E31AC9";
  final static String fmtname = "EPV001002";
  final int FIELDCOUNT = 18;
  private static Hashtable tlookup = new Hashtable();
  private CharacterField fieldH02USERID = null;
  private CharacterField fieldH02PROGRM = null;
  private CharacterField fieldH02TIMSYS = null;
  private CharacterField fieldH02SCRCOD = null;
  private CharacterField fieldH02OPECOD = null;
  private CharacterField fieldH02FLGMAS = null;
  private CharacterField fieldH02FLGWK1 = null;
  private CharacterField fieldH02FLGWK2 = null;
  private CharacterField fieldH02FLGWK3 = null;
  private DecimalField fieldE02PVMNUM = null;
  private DecimalField fieldE02PVMCUN = null;
  private CharacterField fieldE02PVMNA1 = null;
  private CharacterField fieldE02PVMIDE = null;
  private DecimalField fieldE02NUMPRO = null;
  private DecimalField fieldE02NUMSBP = null;
  private CharacterField fieldE02NUMBCO = null;
  private CharacterField fieldE02NUMOFI = null;
  private CharacterField fieldE02DATAVR = null;

  /**
  * Constructor for EPV001002Message.
  */
  public EPV001002Message()
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
    recordsize = 9137;
    fileid = fid;
    recordid = rid;
    message = new byte[getByteLength()];
    formatname = fmtname;
    fieldnames = fldnames;
    tagnames = tnames;
    fields = new MessageField[FIELDCOUNT];

    fields[0] = fieldH02USERID =
    new CharacterField(message, HEADERSIZE + 0, 10, "H02USERID");
    fields[1] = fieldH02PROGRM =
    new CharacterField(message, HEADERSIZE + 10, 10, "H02PROGRM");
    fields[2] = fieldH02TIMSYS =
    new CharacterField(message, HEADERSIZE + 20, 12, "H02TIMSYS");
    fields[3] = fieldH02SCRCOD =
    new CharacterField(message, HEADERSIZE + 32, 2, "H02SCRCOD");
    fields[4] = fieldH02OPECOD =
    new CharacterField(message, HEADERSIZE + 34, 4, "H02OPECOD");
    fields[5] = fieldH02FLGMAS =
    new CharacterField(message, HEADERSIZE + 38, 1, "H02FLGMAS");
    fields[6] = fieldH02FLGWK1 =
    new CharacterField(message, HEADERSIZE + 39, 1, "H02FLGWK1");
    fields[7] = fieldH02FLGWK2 =
    new CharacterField(message, HEADERSIZE + 40, 1, "H02FLGWK2");
    fields[8] = fieldH02FLGWK3 =
    new CharacterField(message, HEADERSIZE + 41, 1, "H02FLGWK3");
    fields[9] = fieldE02PVMNUM =
    new DecimalField(message, HEADERSIZE + 42, 13, 0, "E02PVMNUM");
    fields[10] = fieldE02PVMCUN =
    new DecimalField(message, HEADERSIZE + 55, 10, 0, "E02PVMCUN");
    fields[11] = fieldE02PVMNA1 =
    new CharacterField(message, HEADERSIZE + 65, 45, "E02PVMNA1");
    fields[12] = fieldE02PVMIDE =
    new CharacterField(message, HEADERSIZE + 110, 15, "E02PVMIDE");
    fields[13] = fieldE02NUMPRO =
    new DecimalField(message, HEADERSIZE + 125, 4, 0, "E02NUMPRO");
    fields[14] = fieldE02NUMSBP =
    new DecimalField(message, HEADERSIZE + 129, 3, 0, "E02NUMSBP");
    fields[15] = fieldE02NUMBCO =
    new CharacterField(message, HEADERSIZE + 132, 2, "E02NUMBCO");
    fields[16] = fieldE02NUMOFI =
    new CharacterField(message, HEADERSIZE + 134, 3, "E02NUMOFI");
    fields[17] = fieldE02DATAVR =
    new CharacterField(message, HEADERSIZE + 137, 9000, "E02DATAVR");

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
  * Set field H02USERID using a String value.
  */
  public void setH02USERID(String newvalue)
  {
    fieldH02USERID.setString(newvalue);
  }

  /**
  * Get value of field H02USERID as a String.
  */
  public String getH02USERID()
  {
    return fieldH02USERID.getString();
  }

  /**
  * Set field H02PROGRM using a String value.
  */
  public void setH02PROGRM(String newvalue)
  {
    fieldH02PROGRM.setString(newvalue);
  }

  /**
  * Get value of field H02PROGRM as a String.
  */
  public String getH02PROGRM()
  {
    return fieldH02PROGRM.getString();
  }

  /**
  * Set field H02TIMSYS using a String value.
  */
  public void setH02TIMSYS(String newvalue)
  {
    fieldH02TIMSYS.setString(newvalue);
  }

  /**
  * Get value of field H02TIMSYS as a String.
  */
  public String getH02TIMSYS()
  {
    return fieldH02TIMSYS.getString();
  }

  /**
  * Set field H02SCRCOD using a String value.
  */
  public void setH02SCRCOD(String newvalue)
  {
    fieldH02SCRCOD.setString(newvalue);
  }

  /**
  * Get value of field H02SCRCOD as a String.
  */
  public String getH02SCRCOD()
  {
    return fieldH02SCRCOD.getString();
  }

  /**
  * Set field H02OPECOD using a String value.
  */
  public void setH02OPECOD(String newvalue)
  {
    fieldH02OPECOD.setString(newvalue);
  }

  /**
  * Get value of field H02OPECOD as a String.
  */
  public String getH02OPECOD()
  {
    return fieldH02OPECOD.getString();
  }

  /**
  * Set field H02FLGMAS using a String value.
  */
  public void setH02FLGMAS(String newvalue)
  {
    fieldH02FLGMAS.setString(newvalue);
  }

  /**
  * Get value of field H02FLGMAS as a String.
  */
  public String getH02FLGMAS()
  {
    return fieldH02FLGMAS.getString();
  }

  /**
  * Set field H02FLGWK1 using a String value.
  */
  public void setH02FLGWK1(String newvalue)
  {
    fieldH02FLGWK1.setString(newvalue);
  }

  /**
  * Get value of field H02FLGWK1 as a String.
  */
  public String getH02FLGWK1()
  {
    return fieldH02FLGWK1.getString();
  }

  /**
  * Set field H02FLGWK2 using a String value.
  */
  public void setH02FLGWK2(String newvalue)
  {
    fieldH02FLGWK2.setString(newvalue);
  }

  /**
  * Get value of field H02FLGWK2 as a String.
  */
  public String getH02FLGWK2()
  {
    return fieldH02FLGWK2.getString();
  }

  /**
  * Set field H02FLGWK3 using a String value.
  */
  public void setH02FLGWK3(String newvalue)
  {
    fieldH02FLGWK3.setString(newvalue);
  }

  /**
  * Get value of field H02FLGWK3 as a String.
  */
  public String getH02FLGWK3()
  {
    return fieldH02FLGWK3.getString();
  }

  /**
  * Set field E02PVMNUM using a String value.
  */
  public void setE02PVMNUM(String newvalue)
  {
    fieldE02PVMNUM.setString(newvalue);
  }

  /**
  * Get value of field E02PVMNUM as a String.
  */
  public String getE02PVMNUM()
  {
    return fieldE02PVMNUM.getString();
  }

  /**
  * Set numeric field E02PVMNUM using a BigDecimal value.
  */
  public void setE02PVMNUM(BigDecimal newvalue)
  {
    fieldE02PVMNUM.setBigDecimal(newvalue);
  }

  /**
  * Return value of numeric field E02PVMNUM as a BigDecimal.
  */
  public BigDecimal getBigDecimalE02PVMNUM()
  {
    return fieldE02PVMNUM.getBigDecimal();
  }

  /**
  * Set field E02PVMCUN using a String value.
  */
  public void setE02PVMCUN(String newvalue)
  {
    fieldE02PVMCUN.setString(newvalue);
  }

  /**
  * Get value of field E02PVMCUN as a String.
  */
  public String getE02PVMCUN()
  {
    return fieldE02PVMCUN.getString();
  }

  /**
  * Set numeric field E02PVMCUN using a BigDecimal value.
  */
  public void setE02PVMCUN(BigDecimal newvalue)
  {
    fieldE02PVMCUN.setBigDecimal(newvalue);
  }

  /**
  * Return value of numeric field E02PVMCUN as a BigDecimal.
  */
  public BigDecimal getBigDecimalE02PVMCUN()
  {
    return fieldE02PVMCUN.getBigDecimal();
  }

  /**
  * Set field E02PVMNA1 using a String value.
  */
  public void setE02PVMNA1(String newvalue)
  {
    fieldE02PVMNA1.setString(newvalue);
  }

  /**
  * Get value of field E02PVMNA1 as a String.
  */
  public String getE02PVMNA1()
  {
    return fieldE02PVMNA1.getString();
  }

  /**
  * Set field E02PVMIDE using a String value.
  */
  public void setE02PVMIDE(String newvalue)
  {
    fieldE02PVMIDE.setString(newvalue);
  }

  /**
  * Get value of field E02PVMIDE as a String.
  */
  public String getE02PVMIDE()
  {
    return fieldE02PVMIDE.getString();
  }

  /**
  * Set field E02NUMPRO using a String value.
  */
  public void setE02NUMPRO(String newvalue)
  {
    fieldE02NUMPRO.setString(newvalue);
  }

  /**
  * Get value of field E02NUMPRO as a String.
  */
  public String getE02NUMPRO()
  {
    return fieldE02NUMPRO.getString();
  }

  /**
  * Set numeric field E02NUMPRO using a BigDecimal value.
  */
  public void setE02NUMPRO(BigDecimal newvalue)
  {
    fieldE02NUMPRO.setBigDecimal(newvalue);
  }

  /**
  * Return value of numeric field E02NUMPRO as a BigDecimal.
  */
  public BigDecimal getBigDecimalE02NUMPRO()
  {
    return fieldE02NUMPRO.getBigDecimal();
  }

  /**
  * Set field E02NUMSBP using a String value.
  */
  public void setE02NUMSBP(String newvalue)
  {
    fieldE02NUMSBP.setString(newvalue);
  }

  /**
  * Get value of field E02NUMSBP as a String.
  */
  public String getE02NUMSBP()
  {
    return fieldE02NUMSBP.getString();
  }

  /**
  * Set numeric field E02NUMSBP using a BigDecimal value.
  */
  public void setE02NUMSBP(BigDecimal newvalue)
  {
    fieldE02NUMSBP.setBigDecimal(newvalue);
  }

  /**
  * Return value of numeric field E02NUMSBP as a BigDecimal.
  */
  public BigDecimal getBigDecimalE02NUMSBP()
  {
    return fieldE02NUMSBP.getBigDecimal();
  }

  /**
  * Set field E02NUMBCO using a String value.
  */
  public void setE02NUMBCO(String newvalue)
  {
    fieldE02NUMBCO.setString(newvalue);
  }

  /**
  * Get value of field E02NUMBCO as a String.
  */
  public String getE02NUMBCO()
  {
    return fieldE02NUMBCO.getString();
  }

  /**
  * Set field E02NUMOFI using a String value.
  */
  public void setE02NUMOFI(String newvalue)
  {
    fieldE02NUMOFI.setString(newvalue);
  }

  /**
  * Get value of field E02NUMOFI as a String.
  */
  public String getE02NUMOFI()
  {
    return fieldE02NUMOFI.getString();
  }

  /**
  * Set field E02DATAVR using a String value.
  */
  public void setE02DATAVR(String newvalue)
  {
    fieldE02DATAVR.setString(newvalue);
  }

  /**
  * Get value of field E02DATAVR as a String.
  */
  public String getE02DATAVR()
  {
    return fieldE02DATAVR.getString();
  }

}
