package datapro.eibs.beans; 
 
import datapro.eibs.sockets.*;
import java.io.*;
import java.math.*;

import java.util.*;

/**
* Class generated by AS/400 CRTCLASS command from EFRM00095 physical file definition.
* 
* File level identifier is 1040809125708.
* Record format level identifier is 2FFB8E0512050.
*/

public class EFRM00095Message extends MessageRecord
{
  final static String fldnames[] = {
                                     "H95USERID",
                                     "H95PROGRM",
                                     "H95TIMSYS",
                                     "H95SCRCOD",
                                     "H95OPECOD",
                                     "H95FLGMAS",
                                     "H95FLGWK1",
                                     "H95FLGWK2",
                                     "H95FLGWK3",
                                     "E95DEACUN",
                                     "E95CUSIDN",
                                     "E95CUSTID",
                                     "E95CUSPID",
                                     "E95CUSIDF",
                                     "E95CUSTIF",
                                     "E95CUSPIF",
                                     "E95CUSLGT",
                                     "E95CUSSTF",
                                     "E95CUSSHN",
                                     "E95CUSSTS",
                                     "E95CUSNA1",
                                     "E95CUSNA2",
                                     "E95CUSNA3",
                                     "E95CUSNA4",
                                     "E95CUSCTY",
                                     "E95CUSSTE",
                                     "E95CUSZPC",
                                     "E95CUSPOB",
                                     "E95CUSCTR",
                                     "E95CUSLIF",
                                     "E95CUSBDM",
                                     "E95CUSBDD",
                                     "E95CUSBDY",
                                     "E95CUSINC",
                                     "E95CUSBUC",
                                     "E95CUSGEC",
                                     "E95CUSOFC",
                                     "E95CUSOF2",
                                     "E95CUSTYP",
                                     "E95CUSGRP",
                                     "E95CUSMLC",
                                     "E95CUSIAD",
                                     "E95SECNME",
                                     "E95OFNME1",
                                     "E95OFPOS1",
                                     "E95OFNPO1",
                                     "E95OFNME2",
                                     "E95OFPOS2",
                                     "E95OFNPO2",
                                     "E95OFNME3",
                                     "E95OFPOS3",
                                     "E95OFNPO3",
                                     "E95OFNME4",
                                     "E95OFPOS4",
                                     "E95OFNPO4",
                                     "E95OFNME5",
                                     "E95OFPOS5",
                                     "E95OFNPO5",
                                     "E95OFNME6",
                                     "E95OFPOS6",
                                     "E95OFNPO6",
                                     "D95CUSSTE"
                                   };
  final static String tnames[] = {
                                   "H95USERID",
                                   "H95PROGRM",
                                   "H95TIMSYS",
                                   "H95SCRCOD",
                                   "H95OPECOD",
                                   "H95FLGMAS",
                                   "H95FLGWK1",
                                   "H95FLGWK2",
                                   "H95FLGWK3",
                                   "E95DEACUN",
                                   "E95CUSIDN",
                                   "E95CUSTID",
                                   "E95CUSPID",
                                   "E95CUSIDF",
                                   "E95CUSTIF",
                                   "E95CUSPIF",
                                   "E95CUSLGT",
                                   "E95CUSSTF",
                                   "E95CUSSHN",
                                   "E95CUSSTS",
                                   "E95CUSNA1",
                                   "E95CUSNA2",
                                   "E95CUSNA3",
                                   "E95CUSNA4",
                                   "E95CUSCTY",
                                   "E95CUSSTE",
                                   "E95CUSZPC",
                                   "E95CUSPOB",
                                   "E95CUSCTR",
                                   "E95CUSLIF",
                                   "E95CUSBDM",
                                   "E95CUSBDD",
                                   "E95CUSBDY",
                                   "E95CUSINC",
                                   "E95CUSBUC",
                                   "E95CUSGEC",
                                   "E95CUSOFC",
                                   "E95CUSOF2",
                                   "E95CUSTYP",
                                   "E95CUSGRP",
                                   "E95CUSMLC",
                                   "E95CUSIAD",
                                   "E95SECNME",
                                   "E95OFNME1",
                                   "E95OFPOS1",
                                   "E95OFNPO1",
                                   "E95OFNME2",
                                   "E95OFPOS2",
                                   "E95OFNPO2",
                                   "E95OFNME3",
                                   "E95OFPOS3",
                                   "E95OFNPO3",
                                   "E95OFNME4",
                                   "E95OFPOS4",
                                   "E95OFNPO4",
                                   "E95OFNME5",
                                   "E95OFPOS5",
                                   "E95OFNPO5",
                                   "E95OFNME6",
                                   "E95OFPOS6",
                                   "E95OFNPO6",
                                   "D95CUSSTE"
                                 };
  final static String fid = "1040809125708";
  final static String rid = "2FFB8E0512050";
  final static String fmtname = "EFRM00095";
  final int FIELDCOUNT = 62;
  private static Hashtable tlookup = new Hashtable();
  private CharacterField fieldH95USERID = null;
  private CharacterField fieldH95PROGRM = null;
  private CharacterField fieldH95TIMSYS = null;
  private CharacterField fieldH95SCRCOD = null;
  private CharacterField fieldH95OPECOD = null;
  private CharacterField fieldH95FLGMAS = null;
  private CharacterField fieldH95FLGWK1 = null;
  private CharacterField fieldH95FLGWK2 = null;
  private CharacterField fieldH95FLGWK3 = null;
  private DecimalField fieldE95DEACUN = null;
  private CharacterField fieldE95CUSIDN = null;
  private CharacterField fieldE95CUSTID = null;
  private CharacterField fieldE95CUSPID = null;
  private CharacterField fieldE95CUSIDF = null;
  private CharacterField fieldE95CUSTIF = null;
  private CharacterField fieldE95CUSPIF = null;
  private CharacterField fieldE95CUSLGT = null;
  private CharacterField fieldE95CUSSTF = null;
  private CharacterField fieldE95CUSSHN = null;
  private CharacterField fieldE95CUSSTS = null;
  private CharacterField fieldE95CUSNA1 = null;
  private CharacterField fieldE95CUSNA2 = null;
  private CharacterField fieldE95CUSNA3 = null;
  private CharacterField fieldE95CUSNA4 = null;
  private CharacterField fieldE95CUSCTY = null;
  private CharacterField fieldE95CUSSTE = null;
  private CharacterField fieldE95CUSZPC = null;
  private CharacterField fieldE95CUSPOB = null;
  private CharacterField fieldE95CUSCTR = null;
  private CharacterField fieldE95CUSLIF = null;
  private DecimalField fieldE95CUSBDM = null;
  private DecimalField fieldE95CUSBDD = null;
  private DecimalField fieldE95CUSBDY = null;
  private CharacterField fieldE95CUSINC = null;
  private CharacterField fieldE95CUSBUC = null;
  private CharacterField fieldE95CUSGEC = null;
  private CharacterField fieldE95CUSOFC = null;
  private CharacterField fieldE95CUSOF2 = null;
  private CharacterField fieldE95CUSTYP = null;
  private DecimalField fieldE95CUSGRP = null;
  private CharacterField fieldE95CUSMLC = null;
  private CharacterField fieldE95CUSIAD = null;
  private CharacterField fieldE95SECNME = null;
  private CharacterField fieldE95OFNME1 = null;
  private CharacterField fieldE95OFPOS1 = null;
  private CharacterField fieldE95OFNPO1 = null;
  private CharacterField fieldE95OFNME2 = null;
  private CharacterField fieldE95OFPOS2 = null;
  private CharacterField fieldE95OFNPO2 = null;
  private CharacterField fieldE95OFNME3 = null;
  private CharacterField fieldE95OFPOS3 = null;
  private CharacterField fieldE95OFNPO3 = null;
  private CharacterField fieldE95OFNME4 = null;
  private CharacterField fieldE95OFPOS4 = null;
  private CharacterField fieldE95OFNPO4 = null;
  private CharacterField fieldE95OFNME5 = null;
  private CharacterField fieldE95OFPOS5 = null;
  private CharacterField fieldE95OFNPO5 = null;
  private CharacterField fieldE95OFNME6 = null;
  private CharacterField fieldE95OFPOS6 = null;
  private CharacterField fieldE95OFNPO6 = null;
  private CharacterField fieldD95CUSSTE = null;

  /**
  * Constructor for EFRM00095Message.
  */
  public EFRM00095Message()
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
    recordsize = 1322;
    fileid = fid;
    recordid = rid;
    message = new byte[getByteLength()];
    formatname = fmtname;
    fieldnames = fldnames;
    tagnames = tnames;
    fields = new MessageField[FIELDCOUNT];

    fields[0] = fieldH95USERID =
    new CharacterField(message, HEADERSIZE + 0, 10, "H95USERID");
    fields[1] = fieldH95PROGRM =
    new CharacterField(message, HEADERSIZE + 10, 10, "H95PROGRM");
    fields[2] = fieldH95TIMSYS =
    new CharacterField(message, HEADERSIZE + 20, 12, "H95TIMSYS");
    fields[3] = fieldH95SCRCOD =
    new CharacterField(message, HEADERSIZE + 32, 2, "H95SCRCOD");
    fields[4] = fieldH95OPECOD =
    new CharacterField(message, HEADERSIZE + 34, 4, "H95OPECOD");
    fields[5] = fieldH95FLGMAS =
    new CharacterField(message, HEADERSIZE + 38, 1, "H95FLGMAS");
    fields[6] = fieldH95FLGWK1 =
    new CharacterField(message, HEADERSIZE + 39, 1, "H95FLGWK1");
    fields[7] = fieldH95FLGWK2 =
    new CharacterField(message, HEADERSIZE + 40, 1, "H95FLGWK2");
    fields[8] = fieldH95FLGWK3 =
    new CharacterField(message, HEADERSIZE + 41, 1, "H95FLGWK3");
    fields[9] = fieldE95DEACUN =
    new DecimalField(message, HEADERSIZE + 42, 10, 0, "E95DEACUN");
    fields[10] = fieldE95CUSIDN =
    new CharacterField(message, HEADERSIZE + 52, 15, "E95CUSIDN");
    fields[11] = fieldE95CUSTID =
    new CharacterField(message, HEADERSIZE + 67, 4, "E95CUSTID");
    fields[12] = fieldE95CUSPID =
    new CharacterField(message, HEADERSIZE + 71, 4, "E95CUSPID");
    fields[13] = fieldE95CUSIDF =
    new CharacterField(message, HEADERSIZE + 75, 15, "E95CUSIDF");
    fields[14] = fieldE95CUSTIF =
    new CharacterField(message, HEADERSIZE + 90, 4, "E95CUSTIF");
    fields[15] = fieldE95CUSPIF =
    new CharacterField(message, HEADERSIZE + 94, 4, "E95CUSPIF");
    fields[16] = fieldE95CUSLGT =
    new CharacterField(message, HEADERSIZE + 98, 1, "E95CUSLGT");
    fields[17] = fieldE95CUSSTF =
    new CharacterField(message, HEADERSIZE + 99, 1, "E95CUSSTF");
    fields[18] = fieldE95CUSSHN =
    new CharacterField(message, HEADERSIZE + 100, 15, "E95CUSSHN");
    fields[19] = fieldE95CUSSTS =
    new CharacterField(message, HEADERSIZE + 115, 1, "E95CUSSTS");
    fields[20] = fieldE95CUSNA1 =
    new CharacterField(message, HEADERSIZE + 116, 45, "E95CUSNA1");
    fields[21] = fieldE95CUSNA2 =
    new CharacterField(message, HEADERSIZE + 161, 35, "E95CUSNA2");
    fields[22] = fieldE95CUSNA3 =
    new CharacterField(message, HEADERSIZE + 196, 35, "E95CUSNA3");
    fields[23] = fieldE95CUSNA4 =
    new CharacterField(message, HEADERSIZE + 231, 35, "E95CUSNA4");
    fields[24] = fieldE95CUSCTY =
    new CharacterField(message, HEADERSIZE + 266, 30, "E95CUSCTY");
    fields[25] = fieldE95CUSSTE =
    new CharacterField(message, HEADERSIZE + 296, 4, "E95CUSSTE");
    fields[26] = fieldE95CUSZPC =
    new CharacterField(message, HEADERSIZE + 300, 15, "E95CUSZPC");
    fields[27] = fieldE95CUSPOB =
    new CharacterField(message, HEADERSIZE + 315, 10, "E95CUSPOB");
    fields[28] = fieldE95CUSCTR =
    new CharacterField(message, HEADERSIZE + 325, 20, "E95CUSCTR");
    fields[29] = fieldE95CUSLIF =
    new CharacterField(message, HEADERSIZE + 345, 1, "E95CUSLIF");
    fields[30] = fieldE95CUSBDM =
    new DecimalField(message, HEADERSIZE + 346, 3, 0, "E95CUSBDM");
    fields[31] = fieldE95CUSBDD =
    new DecimalField(message, HEADERSIZE + 349, 3, 0, "E95CUSBDD");
    fields[32] = fieldE95CUSBDY =
    new DecimalField(message, HEADERSIZE + 352, 5, 0, "E95CUSBDY");
    fields[33] = fieldE95CUSINC =
    new CharacterField(message, HEADERSIZE + 357, 4, "E95CUSINC");
    fields[34] = fieldE95CUSBUC =
    new CharacterField(message, HEADERSIZE + 361, 4, "E95CUSBUC");
    fields[35] = fieldE95CUSGEC =
    new CharacterField(message, HEADERSIZE + 365, 4, "E95CUSGEC");
    fields[36] = fieldE95CUSOFC =
    new CharacterField(message, HEADERSIZE + 369, 4, "E95CUSOFC");
    fields[37] = fieldE95CUSOF2 =
    new CharacterField(message, HEADERSIZE + 373, 4, "E95CUSOF2");
    fields[38] = fieldE95CUSTYP =
    new CharacterField(message, HEADERSIZE + 377, 1, "E95CUSTYP");
    fields[39] = fieldE95CUSGRP =
    new DecimalField(message, HEADERSIZE + 378, 10, 0, "E95CUSGRP");
    fields[40] = fieldE95CUSMLC =
    new CharacterField(message, HEADERSIZE + 388, 4, "E95CUSMLC");
    fields[41] = fieldE95CUSIAD =
    new CharacterField(message, HEADERSIZE + 392, 40, "E95CUSIAD");
    fields[42] = fieldE95SECNME =
    new CharacterField(message, HEADERSIZE + 432, 45, "E95SECNME");
    fields[43] = fieldE95OFNME1 =
    new CharacterField(message, HEADERSIZE + 477, 45, "E95OFNME1");
    fields[44] = fieldE95OFPOS1 =
    new CharacterField(message, HEADERSIZE + 522, 25, "E95OFPOS1");
    fields[45] = fieldE95OFNPO1 =
    new CharacterField(message, HEADERSIZE + 547, 65, "E95OFNPO1");
    fields[46] = fieldE95OFNME2 =
    new CharacterField(message, HEADERSIZE + 612, 45, "E95OFNME2");
    fields[47] = fieldE95OFPOS2 =
    new CharacterField(message, HEADERSIZE + 657, 25, "E95OFPOS2");
    fields[48] = fieldE95OFNPO2 =
    new CharacterField(message, HEADERSIZE + 682, 65, "E95OFNPO2");
    fields[49] = fieldE95OFNME3 =
    new CharacterField(message, HEADERSIZE + 747, 45, "E95OFNME3");
    fields[50] = fieldE95OFPOS3 =
    new CharacterField(message, HEADERSIZE + 792, 25, "E95OFPOS3");
    fields[51] = fieldE95OFNPO3 =
    new CharacterField(message, HEADERSIZE + 817, 65, "E95OFNPO3");
    fields[52] = fieldE95OFNME4 =
    new CharacterField(message, HEADERSIZE + 882, 45, "E95OFNME4");
    fields[53] = fieldE95OFPOS4 =
    new CharacterField(message, HEADERSIZE + 927, 25, "E95OFPOS4");
    fields[54] = fieldE95OFNPO4 =
    new CharacterField(message, HEADERSIZE + 952, 65, "E95OFNPO4");
    fields[55] = fieldE95OFNME5 =
    new CharacterField(message, HEADERSIZE + 1017, 45, "E95OFNME5");
    fields[56] = fieldE95OFPOS5 =
    new CharacterField(message, HEADERSIZE + 1062, 25, "E95OFPOS5");
    fields[57] = fieldE95OFNPO5 =
    new CharacterField(message, HEADERSIZE + 1087, 65, "E95OFNPO5");
    fields[58] = fieldE95OFNME6 =
    new CharacterField(message, HEADERSIZE + 1152, 45, "E95OFNME6");
    fields[59] = fieldE95OFPOS6 =
    new CharacterField(message, HEADERSIZE + 1197, 25, "E95OFPOS6");
    fields[60] = fieldE95OFNPO6 =
    new CharacterField(message, HEADERSIZE + 1222, 65, "E95OFNPO6");
    fields[61] = fieldD95CUSSTE =
    new CharacterField(message, HEADERSIZE + 1287, 35, "D95CUSSTE");

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
  * Set field H95USERID using a String value.
  */
  public void setH95USERID(String newvalue)
  {
    fieldH95USERID.setString(newvalue);
  }

  /**
  * Get value of field H95USERID as a String.
  */
  public String getH95USERID()
  {
    return fieldH95USERID.getString();
  }

  /**
  * Set field H95PROGRM using a String value.
  */
  public void setH95PROGRM(String newvalue)
  {
    fieldH95PROGRM.setString(newvalue);
  }

  /**
  * Get value of field H95PROGRM as a String.
  */
  public String getH95PROGRM()
  {
    return fieldH95PROGRM.getString();
  }

  /**
  * Set field H95TIMSYS using a String value.
  */
  public void setH95TIMSYS(String newvalue)
  {
    fieldH95TIMSYS.setString(newvalue);
  }

  /**
  * Get value of field H95TIMSYS as a String.
  */
  public String getH95TIMSYS()
  {
    return fieldH95TIMSYS.getString();
  }

  /**
  * Set field H95SCRCOD using a String value.
  */
  public void setH95SCRCOD(String newvalue)
  {
    fieldH95SCRCOD.setString(newvalue);
  }

  /**
  * Get value of field H95SCRCOD as a String.
  */
  public String getH95SCRCOD()
  {
    return fieldH95SCRCOD.getString();
  }

  /**
  * Set field H95OPECOD using a String value.
  */
  public void setH95OPECOD(String newvalue)
  {
    fieldH95OPECOD.setString(newvalue);
  }

  /**
  * Get value of field H95OPECOD as a String.
  */
  public String getH95OPECOD()
  {
    return fieldH95OPECOD.getString();
  }

  /**
  * Set field H95FLGMAS using a String value.
  */
  public void setH95FLGMAS(String newvalue)
  {
    fieldH95FLGMAS.setString(newvalue);
  }

  /**
  * Get value of field H95FLGMAS as a String.
  */
  public String getH95FLGMAS()
  {
    return fieldH95FLGMAS.getString();
  }

  /**
  * Set field H95FLGWK1 using a String value.
  */
  public void setH95FLGWK1(String newvalue)
  {
    fieldH95FLGWK1.setString(newvalue);
  }

  /**
  * Get value of field H95FLGWK1 as a String.
  */
  public String getH95FLGWK1()
  {
    return fieldH95FLGWK1.getString();
  }

  /**
  * Set field H95FLGWK2 using a String value.
  */
  public void setH95FLGWK2(String newvalue)
  {
    fieldH95FLGWK2.setString(newvalue);
  }

  /**
  * Get value of field H95FLGWK2 as a String.
  */
  public String getH95FLGWK2()
  {
    return fieldH95FLGWK2.getString();
  }

  /**
  * Set field H95FLGWK3 using a String value.
  */
  public void setH95FLGWK3(String newvalue)
  {
    fieldH95FLGWK3.setString(newvalue);
  }

  /**
  * Get value of field H95FLGWK3 as a String.
  */
  public String getH95FLGWK3()
  {
    return fieldH95FLGWK3.getString();
  }

  /**
  * Set field E95DEACUN using a String value.
  */
  public void setE95DEACUN(String newvalue)
  {
    fieldE95DEACUN.setString(newvalue);
  }

  /**
  * Get value of field E95DEACUN as a String.
  */
  public String getE95DEACUN()
  {
    return fieldE95DEACUN.getString();
  }

  /**
  * Set numeric field E95DEACUN using a BigDecimal value.
  */
  public void setE95DEACUN(BigDecimal newvalue)
  {
    fieldE95DEACUN.setBigDecimal(newvalue);
  }

  /**
  * Return value of numeric field E95DEACUN as a BigDecimal.
  */
  public BigDecimal getBigDecimalE95DEACUN()
  {
    return fieldE95DEACUN.getBigDecimal();
  }

  /**
  * Set field E95CUSIDN using a String value.
  */
  public void setE95CUSIDN(String newvalue)
  {
    fieldE95CUSIDN.setString(newvalue);
  }

  /**
  * Get value of field E95CUSIDN as a String.
  */
  public String getE95CUSIDN()
  {
    return fieldE95CUSIDN.getString();
  }

  /**
  * Set field E95CUSTID using a String value.
  */
  public void setE95CUSTID(String newvalue)
  {
    fieldE95CUSTID.setString(newvalue);
  }

  /**
  * Get value of field E95CUSTID as a String.
  */
  public String getE95CUSTID()
  {
    return fieldE95CUSTID.getString();
  }

  /**
  * Set field E95CUSPID using a String value.
  */
  public void setE95CUSPID(String newvalue)
  {
    fieldE95CUSPID.setString(newvalue);
  }

  /**
  * Get value of field E95CUSPID as a String.
  */
  public String getE95CUSPID()
  {
    return fieldE95CUSPID.getString();
  }

  /**
  * Set field E95CUSIDF using a String value.
  */
  public void setE95CUSIDF(String newvalue)
  {
    fieldE95CUSIDF.setString(newvalue);
  }

  /**
  * Get value of field E95CUSIDF as a String.
  */
  public String getE95CUSIDF()
  {
    return fieldE95CUSIDF.getString();
  }

  /**
  * Set field E95CUSTIF using a String value.
  */
  public void setE95CUSTIF(String newvalue)
  {
    fieldE95CUSTIF.setString(newvalue);
  }

  /**
  * Get value of field E95CUSTIF as a String.
  */
  public String getE95CUSTIF()
  {
    return fieldE95CUSTIF.getString();
  }

  /**
  * Set field E95CUSPIF using a String value.
  */
  public void setE95CUSPIF(String newvalue)
  {
    fieldE95CUSPIF.setString(newvalue);
  }

  /**
  * Get value of field E95CUSPIF as a String.
  */
  public String getE95CUSPIF()
  {
    return fieldE95CUSPIF.getString();
  }

  /**
  * Set field E95CUSLGT using a String value.
  */
  public void setE95CUSLGT(String newvalue)
  {
    fieldE95CUSLGT.setString(newvalue);
  }

  /**
  * Get value of field E95CUSLGT as a String.
  */
  public String getE95CUSLGT()
  {
    return fieldE95CUSLGT.getString();
  }

  /**
  * Set field E95CUSSTF using a String value.
  */
  public void setE95CUSSTF(String newvalue)
  {
    fieldE95CUSSTF.setString(newvalue);
  }

  /**
  * Get value of field E95CUSSTF as a String.
  */
  public String getE95CUSSTF()
  {
    return fieldE95CUSSTF.getString();
  }

  /**
  * Set field E95CUSSHN using a String value.
  */
  public void setE95CUSSHN(String newvalue)
  {
    fieldE95CUSSHN.setString(newvalue);
  }

  /**
  * Get value of field E95CUSSHN as a String.
  */
  public String getE95CUSSHN()
  {
    return fieldE95CUSSHN.getString();
  }

  /**
  * Set field E95CUSSTS using a String value.
  */
  public void setE95CUSSTS(String newvalue)
  {
    fieldE95CUSSTS.setString(newvalue);
  }

  /**
  * Get value of field E95CUSSTS as a String.
  */
  public String getE95CUSSTS()
  {
    return fieldE95CUSSTS.getString();
  }

  /**
  * Set field E95CUSNA1 using a String value.
  */
  public void setE95CUSNA1(String newvalue)
  {
    fieldE95CUSNA1.setString(newvalue);
  }

  /**
  * Get value of field E95CUSNA1 as a String.
  */
  public String getE95CUSNA1()
  {
    return fieldE95CUSNA1.getString();
  }

  /**
  * Set field E95CUSNA2 using a String value.
  */
  public void setE95CUSNA2(String newvalue)
  {
    fieldE95CUSNA2.setString(newvalue);
  }

  /**
  * Get value of field E95CUSNA2 as a String.
  */
  public String getE95CUSNA2()
  {
    return fieldE95CUSNA2.getString();
  }

  /**
  * Set field E95CUSNA3 using a String value.
  */
  public void setE95CUSNA3(String newvalue)
  {
    fieldE95CUSNA3.setString(newvalue);
  }

  /**
  * Get value of field E95CUSNA3 as a String.
  */
  public String getE95CUSNA3()
  {
    return fieldE95CUSNA3.getString();
  }

  /**
  * Set field E95CUSNA4 using a String value.
  */
  public void setE95CUSNA4(String newvalue)
  {
    fieldE95CUSNA4.setString(newvalue);
  }

  /**
  * Get value of field E95CUSNA4 as a String.
  */
  public String getE95CUSNA4()
  {
    return fieldE95CUSNA4.getString();
  }

  /**
  * Set field E95CUSCTY using a String value.
  */
  public void setE95CUSCTY(String newvalue)
  {
    fieldE95CUSCTY.setString(newvalue);
  }

  /**
  * Get value of field E95CUSCTY as a String.
  */
  public String getE95CUSCTY()
  {
    return fieldE95CUSCTY.getString();
  }

  /**
  * Set field E95CUSSTE using a String value.
  */
  public void setE95CUSSTE(String newvalue)
  {
    fieldE95CUSSTE.setString(newvalue);
  }

  /**
  * Get value of field E95CUSSTE as a String.
  */
  public String getE95CUSSTE()
  {
    return fieldE95CUSSTE.getString();
  }

  /**
  * Set field E95CUSZPC using a String value.
  */
  public void setE95CUSZPC(String newvalue)
  {
    fieldE95CUSZPC.setString(newvalue);
  }

  /**
  * Get value of field E95CUSZPC as a String.
  */
  public String getE95CUSZPC()
  {
    return fieldE95CUSZPC.getString();
  }

  /**
  * Set field E95CUSPOB using a String value.
  */
  public void setE95CUSPOB(String newvalue)
  {
    fieldE95CUSPOB.setString(newvalue);
  }

  /**
  * Get value of field E95CUSPOB as a String.
  */
  public String getE95CUSPOB()
  {
    return fieldE95CUSPOB.getString();
  }

  /**
  * Set field E95CUSCTR using a String value.
  */
  public void setE95CUSCTR(String newvalue)
  {
    fieldE95CUSCTR.setString(newvalue);
  }

  /**
  * Get value of field E95CUSCTR as a String.
  */
  public String getE95CUSCTR()
  {
    return fieldE95CUSCTR.getString();
  }

  /**
  * Set field E95CUSLIF using a String value.
  */
  public void setE95CUSLIF(String newvalue)
  {
    fieldE95CUSLIF.setString(newvalue);
  }

  /**
  * Get value of field E95CUSLIF as a String.
  */
  public String getE95CUSLIF()
  {
    return fieldE95CUSLIF.getString();
  }

  /**
  * Set field E95CUSBDM using a String value.
  */
  public void setE95CUSBDM(String newvalue)
  {
    fieldE95CUSBDM.setString(newvalue);
  }

  /**
  * Get value of field E95CUSBDM as a String.
  */
  public String getE95CUSBDM()
  {
    return fieldE95CUSBDM.getString();
  }

  /**
  * Set numeric field E95CUSBDM using a BigDecimal value.
  */
  public void setE95CUSBDM(BigDecimal newvalue)
  {
    fieldE95CUSBDM.setBigDecimal(newvalue);
  }

  /**
  * Return value of numeric field E95CUSBDM as a BigDecimal.
  */
  public BigDecimal getBigDecimalE95CUSBDM()
  {
    return fieldE95CUSBDM.getBigDecimal();
  }

  /**
  * Set field E95CUSBDD using a String value.
  */
  public void setE95CUSBDD(String newvalue)
  {
    fieldE95CUSBDD.setString(newvalue);
  }

  /**
  * Get value of field E95CUSBDD as a String.
  */
  public String getE95CUSBDD()
  {
    return fieldE95CUSBDD.getString();
  }

  /**
  * Set numeric field E95CUSBDD using a BigDecimal value.
  */
  public void setE95CUSBDD(BigDecimal newvalue)
  {
    fieldE95CUSBDD.setBigDecimal(newvalue);
  }

  /**
  * Return value of numeric field E95CUSBDD as a BigDecimal.
  */
  public BigDecimal getBigDecimalE95CUSBDD()
  {
    return fieldE95CUSBDD.getBigDecimal();
  }

  /**
  * Set field E95CUSBDY using a String value.
  */
  public void setE95CUSBDY(String newvalue)
  {
    fieldE95CUSBDY.setString(newvalue);
  }

  /**
  * Get value of field E95CUSBDY as a String.
  */
  public String getE95CUSBDY()
  {
    return fieldE95CUSBDY.getString();
  }

  /**
  * Set numeric field E95CUSBDY using a BigDecimal value.
  */
  public void setE95CUSBDY(BigDecimal newvalue)
  {
    fieldE95CUSBDY.setBigDecimal(newvalue);
  }

  /**
  * Return value of numeric field E95CUSBDY as a BigDecimal.
  */
  public BigDecimal getBigDecimalE95CUSBDY()
  {
    return fieldE95CUSBDY.getBigDecimal();
  }

  /**
  * Set field E95CUSINC using a String value.
  */
  public void setE95CUSINC(String newvalue)
  {
    fieldE95CUSINC.setString(newvalue);
  }

  /**
  * Get value of field E95CUSINC as a String.
  */
  public String getE95CUSINC()
  {
    return fieldE95CUSINC.getString();
  }

  /**
  * Set field E95CUSBUC using a String value.
  */
  public void setE95CUSBUC(String newvalue)
  {
    fieldE95CUSBUC.setString(newvalue);
  }

  /**
  * Get value of field E95CUSBUC as a String.
  */
  public String getE95CUSBUC()
  {
    return fieldE95CUSBUC.getString();
  }

  /**
  * Set field E95CUSGEC using a String value.
  */
  public void setE95CUSGEC(String newvalue)
  {
    fieldE95CUSGEC.setString(newvalue);
  }

  /**
  * Get value of field E95CUSGEC as a String.
  */
  public String getE95CUSGEC()
  {
    return fieldE95CUSGEC.getString();
  }

  /**
  * Set field E95CUSOFC using a String value.
  */
  public void setE95CUSOFC(String newvalue)
  {
    fieldE95CUSOFC.setString(newvalue);
  }

  /**
  * Get value of field E95CUSOFC as a String.
  */
  public String getE95CUSOFC()
  {
    return fieldE95CUSOFC.getString();
  }

  /**
  * Set field E95CUSOF2 using a String value.
  */
  public void setE95CUSOF2(String newvalue)
  {
    fieldE95CUSOF2.setString(newvalue);
  }

  /**
  * Get value of field E95CUSOF2 as a String.
  */
  public String getE95CUSOF2()
  {
    return fieldE95CUSOF2.getString();
  }

  /**
  * Set field E95CUSTYP using a String value.
  */
  public void setE95CUSTYP(String newvalue)
  {
    fieldE95CUSTYP.setString(newvalue);
  }

  /**
  * Get value of field E95CUSTYP as a String.
  */
  public String getE95CUSTYP()
  {
    return fieldE95CUSTYP.getString();
  }

  /**
  * Set field E95CUSGRP using a String value.
  */
  public void setE95CUSGRP(String newvalue)
  {
    fieldE95CUSGRP.setString(newvalue);
  }

  /**
  * Get value of field E95CUSGRP as a String.
  */
  public String getE95CUSGRP()
  {
    return fieldE95CUSGRP.getString();
  }

  /**
  * Set numeric field E95CUSGRP using a BigDecimal value.
  */
  public void setE95CUSGRP(BigDecimal newvalue)
  {
    fieldE95CUSGRP.setBigDecimal(newvalue);
  }

  /**
  * Return value of numeric field E95CUSGRP as a BigDecimal.
  */
  public BigDecimal getBigDecimalE95CUSGRP()
  {
    return fieldE95CUSGRP.getBigDecimal();
  }

  /**
  * Set field E95CUSMLC using a String value.
  */
  public void setE95CUSMLC(String newvalue)
  {
    fieldE95CUSMLC.setString(newvalue);
  }

  /**
  * Get value of field E95CUSMLC as a String.
  */
  public String getE95CUSMLC()
  {
    return fieldE95CUSMLC.getString();
  }

  /**
  * Set field E95CUSIAD using a String value.
  */
  public void setE95CUSIAD(String newvalue)
  {
    fieldE95CUSIAD.setString(newvalue);
  }

  /**
  * Get value of field E95CUSIAD as a String.
  */
  public String getE95CUSIAD()
  {
    return fieldE95CUSIAD.getString();
  }

  /**
  * Set field E95SECNME using a String value.
  */
  public void setE95SECNME(String newvalue)
  {
    fieldE95SECNME.setString(newvalue);
  }

  /**
  * Get value of field E95SECNME as a String.
  */
  public String getE95SECNME()
  {
    return fieldE95SECNME.getString();
  }

  /**
  * Set field E95OFNME1 using a String value.
  */
  public void setE95OFNME1(String newvalue)
  {
    fieldE95OFNME1.setString(newvalue);
  }

  /**
  * Get value of field E95OFNME1 as a String.
  */
  public String getE95OFNME1()
  {
    return fieldE95OFNME1.getString();
  }

  /**
  * Set field E95OFPOS1 using a String value.
  */
  public void setE95OFPOS1(String newvalue)
  {
    fieldE95OFPOS1.setString(newvalue);
  }

  /**
  * Get value of field E95OFPOS1 as a String.
  */
  public String getE95OFPOS1()
  {
    return fieldE95OFPOS1.getString();
  }

  /**
  * Set field E95OFNPO1 using a String value.
  */
  public void setE95OFNPO1(String newvalue)
  {
    fieldE95OFNPO1.setString(newvalue);
  }

  /**
  * Get value of field E95OFNPO1 as a String.
  */
  public String getE95OFNPO1()
  {
    return fieldE95OFNPO1.getString();
  }

  /**
  * Set field E95OFNME2 using a String value.
  */
  public void setE95OFNME2(String newvalue)
  {
    fieldE95OFNME2.setString(newvalue);
  }

  /**
  * Get value of field E95OFNME2 as a String.
  */
  public String getE95OFNME2()
  {
    return fieldE95OFNME2.getString();
  }

  /**
  * Set field E95OFPOS2 using a String value.
  */
  public void setE95OFPOS2(String newvalue)
  {
    fieldE95OFPOS2.setString(newvalue);
  }

  /**
  * Get value of field E95OFPOS2 as a String.
  */
  public String getE95OFPOS2()
  {
    return fieldE95OFPOS2.getString();
  }

  /**
  * Set field E95OFNPO2 using a String value.
  */
  public void setE95OFNPO2(String newvalue)
  {
    fieldE95OFNPO2.setString(newvalue);
  }

  /**
  * Get value of field E95OFNPO2 as a String.
  */
  public String getE95OFNPO2()
  {
    return fieldE95OFNPO2.getString();
  }

  /**
  * Set field E95OFNME3 using a String value.
  */
  public void setE95OFNME3(String newvalue)
  {
    fieldE95OFNME3.setString(newvalue);
  }

  /**
  * Get value of field E95OFNME3 as a String.
  */
  public String getE95OFNME3()
  {
    return fieldE95OFNME3.getString();
  }

  /**
  * Set field E95OFPOS3 using a String value.
  */
  public void setE95OFPOS3(String newvalue)
  {
    fieldE95OFPOS3.setString(newvalue);
  }

  /**
  * Get value of field E95OFPOS3 as a String.
  */
  public String getE95OFPOS3()
  {
    return fieldE95OFPOS3.getString();
  }

  /**
  * Set field E95OFNPO3 using a String value.
  */
  public void setE95OFNPO3(String newvalue)
  {
    fieldE95OFNPO3.setString(newvalue);
  }

  /**
  * Get value of field E95OFNPO3 as a String.
  */
  public String getE95OFNPO3()
  {
    return fieldE95OFNPO3.getString();
  }

  /**
  * Set field E95OFNME4 using a String value.
  */
  public void setE95OFNME4(String newvalue)
  {
    fieldE95OFNME4.setString(newvalue);
  }

  /**
  * Get value of field E95OFNME4 as a String.
  */
  public String getE95OFNME4()
  {
    return fieldE95OFNME4.getString();
  }

  /**
  * Set field E95OFPOS4 using a String value.
  */
  public void setE95OFPOS4(String newvalue)
  {
    fieldE95OFPOS4.setString(newvalue);
  }

  /**
  * Get value of field E95OFPOS4 as a String.
  */
  public String getE95OFPOS4()
  {
    return fieldE95OFPOS4.getString();
  }

  /**
  * Set field E95OFNPO4 using a String value.
  */
  public void setE95OFNPO4(String newvalue)
  {
    fieldE95OFNPO4.setString(newvalue);
  }

  /**
  * Get value of field E95OFNPO4 as a String.
  */
  public String getE95OFNPO4()
  {
    return fieldE95OFNPO4.getString();
  }

  /**
  * Set field E95OFNME5 using a String value.
  */
  public void setE95OFNME5(String newvalue)
  {
    fieldE95OFNME5.setString(newvalue);
  }

  /**
  * Get value of field E95OFNME5 as a String.
  */
  public String getE95OFNME5()
  {
    return fieldE95OFNME5.getString();
  }

  /**
  * Set field E95OFPOS5 using a String value.
  */
  public void setE95OFPOS5(String newvalue)
  {
    fieldE95OFPOS5.setString(newvalue);
  }

  /**
  * Get value of field E95OFPOS5 as a String.
  */
  public String getE95OFPOS5()
  {
    return fieldE95OFPOS5.getString();
  }

  /**
  * Set field E95OFNPO5 using a String value.
  */
  public void setE95OFNPO5(String newvalue)
  {
    fieldE95OFNPO5.setString(newvalue);
  }

  /**
  * Get value of field E95OFNPO5 as a String.
  */
  public String getE95OFNPO5()
  {
    return fieldE95OFNPO5.getString();
  }

  /**
  * Set field E95OFNME6 using a String value.
  */
  public void setE95OFNME6(String newvalue)
  {
    fieldE95OFNME6.setString(newvalue);
  }

  /**
  * Get value of field E95OFNME6 as a String.
  */
  public String getE95OFNME6()
  {
    return fieldE95OFNME6.getString();
  }

  /**
  * Set field E95OFPOS6 using a String value.
  */
  public void setE95OFPOS6(String newvalue)
  {
    fieldE95OFPOS6.setString(newvalue);
  }

  /**
  * Get value of field E95OFPOS6 as a String.
  */
  public String getE95OFPOS6()
  {
    return fieldE95OFPOS6.getString();
  }

  /**
  * Set field E95OFNPO6 using a String value.
  */
  public void setE95OFNPO6(String newvalue)
  {
    fieldE95OFNPO6.setString(newvalue);
  }

  /**
  * Get value of field E95OFNPO6 as a String.
  */
  public String getE95OFNPO6()
  {
    return fieldE95OFNPO6.getString();
  }

  /**
  * Set field D95CUSSTE using a String value.
  */
  public void setD95CUSSTE(String newvalue)
  {
    fieldD95CUSSTE.setString(newvalue);
  }

  /**
  * Get value of field D95CUSSTE as a String.
  */
  public String getD95CUSSTE()
  {
    return fieldD95CUSSTE.getString();
  }

}
