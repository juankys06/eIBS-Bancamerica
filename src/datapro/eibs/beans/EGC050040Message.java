package datapro.eibs.beans;

import datapro.eibs.sockets.*;
import java.io.*;
import java.math.*;

import java.util.*;

/**
* Class generated by AS/400 CRTCLASS command from EGC050040 physical file definition.
* 
* File level identifier is 1030121191927.
* Record format level identifier is 3977A2E6F524B.
*/

public class EGC050040Message extends MessageRecord
{
  final static String fldnames[] = {
                                     "H40USERID",
                                     "H40PROGRM",
                                     "H40TIMSYS",
                                     "H40SCRCOD",
                                     "H40OPECOD",
                                     "H40FLGMAS",
                                     "H40FLGWK1",
                                     "H40FLGWK2",
                                     "H40FLGWK3",
                                     "H40CODACC",
                                     "E40GCRUT",
                                     "E40GCNMBC",
                                     "E40GCGTPB",
                                     "E40GCNOPR",
                                     "E40GCGMND",
                                     "E40GCSTAT",
                                     "E40GCNMBF",
                                     "E40GCMBOL",
                                     "E40GCEMSY",
                                     "E40GCEMSM",
                                     "E40GCEMSD",
                                     "E40GCVENY",
                                     "E40GCVENM",
                                     "E40GCVEND",
                                     "E40GCINTR",
                                     "E40GCCOMM",
                                     "E40INDOPE"
                                   };
  final static String tnames[] = {
                                   "H40USERID",
                                   "H40PROGRM",
                                   "H40TIMSYS",
                                   "H40SCRCOD",
                                   "H40OPECOD",
                                   "H40FLGMAS",
                                   "H40FLGWK1",
                                   "H40FLGWK2",
                                   "H40FLGWK3",
                                   "H40CODACC",
                                   "E40GCRUT",
                                   "E40GCNMBC",
                                   "E40GCGTPB",
                                   "E40GCNOPR",
                                   "E40GCGMND",
                                   "E40GCSTAT",
                                   "E40GCNMBF",
                                   "E40GCMBOL",
                                   "E40GCEMSY",
                                   "E40GCEMSM",
                                   "E40GCEMSD",
                                   "E40GCVENY",
                                   "E40GCVENM",
                                   "E40GCVEND",
                                   "E40GCINTR",
                                   "E40GCCOMM",
                                   "E40INDOPE"
                                 };
  final static String fid = "1030121191927";
  final static String rid = "3977A2E6F524B";
  final static String fmtname = "EGC050040";
  final int FIELDCOUNT = 27;
  private static Hashtable tlookup = new Hashtable();
  private CharacterField fieldH40USERID = null;
  private CharacterField fieldH40PROGRM = null;
  private CharacterField fieldH40TIMSYS = null;
  private CharacterField fieldH40SCRCOD = null;
  private CharacterField fieldH40OPECOD = null;
  private CharacterField fieldH40FLGMAS = null;
  private CharacterField fieldH40FLGWK1 = null;
  private CharacterField fieldH40FLGWK2 = null;
  private CharacterField fieldH40FLGWK3 = null;
  private CharacterField fieldH40CODACC = null;
  private CharacterField fieldE40GCRUT = null;
  private CharacterField fieldE40GCNMBC = null;
  private CharacterField fieldE40GCGTPB = null;
  private DecimalField fieldE40GCNOPR = null;
  private CharacterField fieldE40GCGMND = null;
  private CharacterField fieldE40GCSTAT = null;
  private CharacterField fieldE40GCNMBF = null;
  private DecimalField fieldE40GCMBOL = null;
  private DecimalField fieldE40GCEMSY = null;
  private DecimalField fieldE40GCEMSM = null;
  private DecimalField fieldE40GCEMSD = null;
  private DecimalField fieldE40GCVENY = null;
  private DecimalField fieldE40GCVENM = null;
  private DecimalField fieldE40GCVEND = null;
  private DecimalField fieldE40GCINTR = null;
  private DecimalField fieldE40GCCOMM = null;
  private CharacterField fieldE40INDOPE = null;

  /**
  * Constructor for EGC050040Message.
  */
  public EGC050040Message()
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
    recordsize = 220;
    fileid = fid;
    recordid = rid;
    message = new byte[getByteLength()];
    formatname = fmtname;
    fieldnames = fldnames;
    tagnames = tnames;
    fields = new MessageField[FIELDCOUNT];

    fields[0] = fieldH40USERID =
    new CharacterField(message, HEADERSIZE + 0, 10, "H40USERID");
    fields[1] = fieldH40PROGRM =
    new CharacterField(message, HEADERSIZE + 10, 10, "H40PROGRM");
    fields[2] = fieldH40TIMSYS =
    new CharacterField(message, HEADERSIZE + 20, 12, "H40TIMSYS");
    fields[3] = fieldH40SCRCOD =
    new CharacterField(message, HEADERSIZE + 32, 2, "H40SCRCOD");
    fields[4] = fieldH40OPECOD =
    new CharacterField(message, HEADERSIZE + 34, 4, "H40OPECOD");
    fields[5] = fieldH40FLGMAS =
    new CharacterField(message, HEADERSIZE + 38, 1, "H40FLGMAS");
    fields[6] = fieldH40FLGWK1 =
    new CharacterField(message, HEADERSIZE + 39, 1, "H40FLGWK1");
    fields[7] = fieldH40FLGWK2 =
    new CharacterField(message, HEADERSIZE + 40, 1, "H40FLGWK2");
    fields[8] = fieldH40FLGWK3 =
    new CharacterField(message, HEADERSIZE + 41, 1, "H40FLGWK3");
    fields[9] = fieldH40CODACC =
    new CharacterField(message, HEADERSIZE + 42, 4, "H40CODACC");
    fields[10] = fieldE40GCRUT =
    new CharacterField(message, HEADERSIZE + 46, 15, "E40GCRUT");
    fields[11] = fieldE40GCNMBC =
    new CharacterField(message, HEADERSIZE + 61, 45, "E40GCNMBC");
    fields[12] = fieldE40GCGTPB =
    new CharacterField(message, HEADERSIZE + 106, 4, "E40GCGTPB");
    fields[13] = fieldE40GCNOPR =
    new DecimalField(message, HEADERSIZE + 110, 6, 0, "E40GCNOPR");
    fields[14] = fieldE40GCGMND =
    new CharacterField(message, HEADERSIZE + 116, 3, "E40GCGMND");
    fields[15] = fieldE40GCSTAT =
    new CharacterField(message, HEADERSIZE + 119, 1, "E40GCSTAT");
    fields[16] = fieldE40GCNMBF =
    new CharacterField(message, HEADERSIZE + 120, 45, "E40GCNMBF");
    fields[17] = fieldE40GCMBOL =
    new DecimalField(message, HEADERSIZE + 165, 17, 2, "E40GCMBOL");
    fields[18] = fieldE40GCEMSY =
    new DecimalField(message, HEADERSIZE + 182, 3, 0, "E40GCEMSY");
    fields[19] = fieldE40GCEMSM =
    new DecimalField(message, HEADERSIZE + 185, 3, 0, "E40GCEMSM");
    fields[20] = fieldE40GCEMSD =
    new DecimalField(message, HEADERSIZE + 188, 3, 0, "E40GCEMSD");
    fields[21] = fieldE40GCVENY =
    new DecimalField(message, HEADERSIZE + 191, 3, 0, "E40GCVENY");
    fields[22] = fieldE40GCVENM =
    new DecimalField(message, HEADERSIZE + 194, 3, 0, "E40GCVENM");
    fields[23] = fieldE40GCVEND =
    new DecimalField(message, HEADERSIZE + 197, 3, 0, "E40GCVEND");
    fields[24] = fieldE40GCINTR =
    new DecimalField(message, HEADERSIZE + 200, 13, 6, "E40GCINTR");
    fields[25] = fieldE40GCCOMM =
    new DecimalField(message, HEADERSIZE + 213, 6, 0, "E40GCCOMM");
    fields[26] = fieldE40INDOPE =
    new CharacterField(message, HEADERSIZE + 219, 1, "E40INDOPE");

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
  * Set field H40USERID using a String value.
  */
  public void setH40USERID(String newvalue)
  {
    fieldH40USERID.setString(newvalue);
  }

  /**
  * Get value of field H40USERID as a String.
  */
  public String getH40USERID()
  {
    return fieldH40USERID.getString();
  }

  /**
  * Set field H40PROGRM using a String value.
  */
  public void setH40PROGRM(String newvalue)
  {
    fieldH40PROGRM.setString(newvalue);
  }

  /**
  * Get value of field H40PROGRM as a String.
  */
  public String getH40PROGRM()
  {
    return fieldH40PROGRM.getString();
  }

  /**
  * Set field H40TIMSYS using a String value.
  */
  public void setH40TIMSYS(String newvalue)
  {
    fieldH40TIMSYS.setString(newvalue);
  }

  /**
  * Get value of field H40TIMSYS as a String.
  */
  public String getH40TIMSYS()
  {
    return fieldH40TIMSYS.getString();
  }

  /**
  * Set field H40SCRCOD using a String value.
  */
  public void setH40SCRCOD(String newvalue)
  {
    fieldH40SCRCOD.setString(newvalue);
  }

  /**
  * Get value of field H40SCRCOD as a String.
  */
  public String getH40SCRCOD()
  {
    return fieldH40SCRCOD.getString();
  }

  /**
  * Set field H40OPECOD using a String value.
  */
  public void setH40OPECOD(String newvalue)
  {
    fieldH40OPECOD.setString(newvalue);
  }

  /**
  * Get value of field H40OPECOD as a String.
  */
  public String getH40OPECOD()
  {
    return fieldH40OPECOD.getString();
  }

  /**
  * Set field H40FLGMAS using a String value.
  */
  public void setH40FLGMAS(String newvalue)
  {
    fieldH40FLGMAS.setString(newvalue);
  }

  /**
  * Get value of field H40FLGMAS as a String.
  */
  public String getH40FLGMAS()
  {
    return fieldH40FLGMAS.getString();
  }

  /**
  * Set field H40FLGWK1 using a String value.
  */
  public void setH40FLGWK1(String newvalue)
  {
    fieldH40FLGWK1.setString(newvalue);
  }

  /**
  * Get value of field H40FLGWK1 as a String.
  */
  public String getH40FLGWK1()
  {
    return fieldH40FLGWK1.getString();
  }

  /**
  * Set field H40FLGWK2 using a String value.
  */
  public void setH40FLGWK2(String newvalue)
  {
    fieldH40FLGWK2.setString(newvalue);
  }

  /**
  * Get value of field H40FLGWK2 as a String.
  */
  public String getH40FLGWK2()
  {
    return fieldH40FLGWK2.getString();
  }

  /**
  * Set field H40FLGWK3 using a String value.
  */
  public void setH40FLGWK3(String newvalue)
  {
    fieldH40FLGWK3.setString(newvalue);
  }

  /**
  * Get value of field H40FLGWK3 as a String.
  */
  public String getH40FLGWK3()
  {
    return fieldH40FLGWK3.getString();
  }

  /**
  * Set field H40CODACC using a String value.
  */
  public void setH40CODACC(String newvalue)
  {
    fieldH40CODACC.setString(newvalue);
  }

  /**
  * Get value of field H40CODACC as a String.
  */
  public String getH40CODACC()
  {
    return fieldH40CODACC.getString();
  }

  /**
  * Set field E40GCRUT using a String value.
  */
  public void setE40GCRUT(String newvalue)
  {
    fieldE40GCRUT.setString(newvalue);
  }

  /**
  * Get value of field E40GCRUT as a String.
  */
  public String getE40GCRUT()
  {
    return fieldE40GCRUT.getString();
  }

  /**
  * Set field E40GCNMBC using a String value.
  */
  public void setE40GCNMBC(String newvalue)
  {
    fieldE40GCNMBC.setString(newvalue);
  }

  /**
  * Get value of field E40GCNMBC as a String.
  */
  public String getE40GCNMBC()
  {
    return fieldE40GCNMBC.getString();
  }

  /**
  * Set field E40GCGTPB using a String value.
  */
  public void setE40GCGTPB(String newvalue)
  {
    fieldE40GCGTPB.setString(newvalue);
  }

  /**
  * Get value of field E40GCGTPB as a String.
  */
  public String getE40GCGTPB()
  {
    return fieldE40GCGTPB.getString();
  }

  /**
  * Set field E40GCNOPR using a String value.
  */
  public void setE40GCNOPR(String newvalue)
  {
    fieldE40GCNOPR.setString(newvalue);
  }

  /**
  * Get value of field E40GCNOPR as a String.
  */
  public String getE40GCNOPR()
  {
    return fieldE40GCNOPR.getString();
  }

  /**
  * Set numeric field E40GCNOPR using a BigDecimal value.
  */
  public void setE40GCNOPR(BigDecimal newvalue)
  {
    fieldE40GCNOPR.setBigDecimal(newvalue);
  }

  /**
  * Return value of numeric field E40GCNOPR as a BigDecimal.
  */
  public BigDecimal getBigDecimalE40GCNOPR()
  {
    return fieldE40GCNOPR.getBigDecimal();
  }

  /**
  * Set field E40GCGMND using a String value.
  */
  public void setE40GCGMND(String newvalue)
  {
    fieldE40GCGMND.setString(newvalue);
  }

  /**
  * Get value of field E40GCGMND as a String.
  */
  public String getE40GCGMND()
  {
    return fieldE40GCGMND.getString();
  }

  /**
  * Set field E40GCSTAT using a String value.
  */
  public void setE40GCSTAT(String newvalue)
  {
    fieldE40GCSTAT.setString(newvalue);
  }

  /**
  * Get value of field E40GCSTAT as a String.
  */
  public String getE40GCSTAT()
  {
    return fieldE40GCSTAT.getString();
  }

  /**
  * Set field E40GCNMBF using a String value.
  */
  public void setE40GCNMBF(String newvalue)
  {
    fieldE40GCNMBF.setString(newvalue);
  }

  /**
  * Get value of field E40GCNMBF as a String.
  */
  public String getE40GCNMBF()
  {
    return fieldE40GCNMBF.getString();
  }

  /**
  * Set field E40GCMBOL using a String value.
  */
  public void setE40GCMBOL(String newvalue)
  {
    fieldE40GCMBOL.setString(newvalue);
  }

  /**
  * Get value of field E40GCMBOL as a String.
  */
  public String getE40GCMBOL()
  {
    return fieldE40GCMBOL.getString();
  }

  /**
  * Set numeric field E40GCMBOL using a BigDecimal value.
  */
  public void setE40GCMBOL(BigDecimal newvalue)
  {
    fieldE40GCMBOL.setBigDecimal(newvalue);
  }

  /**
  * Return value of numeric field E40GCMBOL as a BigDecimal.
  */
  public BigDecimal getBigDecimalE40GCMBOL()
  {
    return fieldE40GCMBOL.getBigDecimal();
  }

  /**
  * Set field E40GCEMSY using a String value.
  */
  public void setE40GCEMSY(String newvalue)
  {
    fieldE40GCEMSY.setString(newvalue);
  }

  /**
  * Get value of field E40GCEMSY as a String.
  */
  public String getE40GCEMSY()
  {
    return fieldE40GCEMSY.getString();
  }

  /**
  * Set numeric field E40GCEMSY using a BigDecimal value.
  */
  public void setE40GCEMSY(BigDecimal newvalue)
  {
    fieldE40GCEMSY.setBigDecimal(newvalue);
  }

  /**
  * Return value of numeric field E40GCEMSY as a BigDecimal.
  */
  public BigDecimal getBigDecimalE40GCEMSY()
  {
    return fieldE40GCEMSY.getBigDecimal();
  }

  /**
  * Set field E40GCEMSM using a String value.
  */
  public void setE40GCEMSM(String newvalue)
  {
    fieldE40GCEMSM.setString(newvalue);
  }

  /**
  * Get value of field E40GCEMSM as a String.
  */
  public String getE40GCEMSM()
  {
    return fieldE40GCEMSM.getString();
  }

  /**
  * Set numeric field E40GCEMSM using a BigDecimal value.
  */
  public void setE40GCEMSM(BigDecimal newvalue)
  {
    fieldE40GCEMSM.setBigDecimal(newvalue);
  }

  /**
  * Return value of numeric field E40GCEMSM as a BigDecimal.
  */
  public BigDecimal getBigDecimalE40GCEMSM()
  {
    return fieldE40GCEMSM.getBigDecimal();
  }

  /**
  * Set field E40GCEMSD using a String value.
  */
  public void setE40GCEMSD(String newvalue)
  {
    fieldE40GCEMSD.setString(newvalue);
  }

  /**
  * Get value of field E40GCEMSD as a String.
  */
  public String getE40GCEMSD()
  {
    return fieldE40GCEMSD.getString();
  }

  /**
  * Set numeric field E40GCEMSD using a BigDecimal value.
  */
  public void setE40GCEMSD(BigDecimal newvalue)
  {
    fieldE40GCEMSD.setBigDecimal(newvalue);
  }

  /**
  * Return value of numeric field E40GCEMSD as a BigDecimal.
  */
  public BigDecimal getBigDecimalE40GCEMSD()
  {
    return fieldE40GCEMSD.getBigDecimal();
  }

  /**
  * Set field E40GCVENY using a String value.
  */
  public void setE40GCVENY(String newvalue)
  {
    fieldE40GCVENY.setString(newvalue);
  }

  /**
  * Get value of field E40GCVENY as a String.
  */
  public String getE40GCVENY()
  {
    return fieldE40GCVENY.getString();
  }

  /**
  * Set numeric field E40GCVENY using a BigDecimal value.
  */
  public void setE40GCVENY(BigDecimal newvalue)
  {
    fieldE40GCVENY.setBigDecimal(newvalue);
  }

  /**
  * Return value of numeric field E40GCVENY as a BigDecimal.
  */
  public BigDecimal getBigDecimalE40GCVENY()
  {
    return fieldE40GCVENY.getBigDecimal();
  }

  /**
  * Set field E40GCVENM using a String value.
  */
  public void setE40GCVENM(String newvalue)
  {
    fieldE40GCVENM.setString(newvalue);
  }

  /**
  * Get value of field E40GCVENM as a String.
  */
  public String getE40GCVENM()
  {
    return fieldE40GCVENM.getString();
  }

  /**
  * Set numeric field E40GCVENM using a BigDecimal value.
  */
  public void setE40GCVENM(BigDecimal newvalue)
  {
    fieldE40GCVENM.setBigDecimal(newvalue);
  }

  /**
  * Return value of numeric field E40GCVENM as a BigDecimal.
  */
  public BigDecimal getBigDecimalE40GCVENM()
  {
    return fieldE40GCVENM.getBigDecimal();
  }

  /**
  * Set field E40GCVEND using a String value.
  */
  public void setE40GCVEND(String newvalue)
  {
    fieldE40GCVEND.setString(newvalue);
  }

  /**
  * Get value of field E40GCVEND as a String.
  */
  public String getE40GCVEND()
  {
    return fieldE40GCVEND.getString();
  }

  /**
  * Set numeric field E40GCVEND using a BigDecimal value.
  */
  public void setE40GCVEND(BigDecimal newvalue)
  {
    fieldE40GCVEND.setBigDecimal(newvalue);
  }

  /**
  * Return value of numeric field E40GCVEND as a BigDecimal.
  */
  public BigDecimal getBigDecimalE40GCVEND()
  {
    return fieldE40GCVEND.getBigDecimal();
  }

  /**
  * Set field E40GCINTR using a String value.
  */
  public void setE40GCINTR(String newvalue)
  {
    fieldE40GCINTR.setString(newvalue);
  }

  /**
  * Get value of field E40GCINTR as a String.
  */
  public String getE40GCINTR()
  {
    return fieldE40GCINTR.getString();
  }

  /**
  * Set numeric field E40GCINTR using a BigDecimal value.
  */
  public void setE40GCINTR(BigDecimal newvalue)
  {
    fieldE40GCINTR.setBigDecimal(newvalue);
  }

  /**
  * Return value of numeric field E40GCINTR as a BigDecimal.
  */
  public BigDecimal getBigDecimalE40GCINTR()
  {
    return fieldE40GCINTR.getBigDecimal();
  }

  /**
  * Set field E40GCCOMM using a String value.
  */
  public void setE40GCCOMM(String newvalue)
  {
    fieldE40GCCOMM.setString(newvalue);
  }

  /**
  * Get value of field E40GCCOMM as a String.
  */
  public String getE40GCCOMM()
  {
    return fieldE40GCCOMM.getString();
  }

  /**
  * Set numeric field E40GCCOMM using a BigDecimal value.
  */
  public void setE40GCCOMM(BigDecimal newvalue)
  {
    fieldE40GCCOMM.setBigDecimal(newvalue);
  }

  /**
  * Return value of numeric field E40GCCOMM as a BigDecimal.
  */
  public BigDecimal getBigDecimalE40GCCOMM()
  {
    return fieldE40GCCOMM.getBigDecimal();
  }

  /**
  * Set field E40INDOPE using a String value.
  */
  public void setE40INDOPE(String newvalue)
  {
    fieldE40INDOPE.setString(newvalue);
  }

  /**
  * Get value of field E40INDOPE as a String.
  */
  public String getE40INDOPE()
  {
    return fieldE40INDOPE.getString();
  }

}
