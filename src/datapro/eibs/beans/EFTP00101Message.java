package datapro.eibs.beans;

import datapro.eibs.sockets.*;
import java.io.*;
import java.math.*;

import java.util.*;

/**
* Class generated by AS/400 CRTCLASS command from EFTP00101 physical file definition.
* 
* File level identifier is 1140117100038.
* Record format level identifier is 5419AA112410A.
*/

public class EFTP00101Message extends MessageRecord
{
  final static String fldnames[] = {
                                     "EFTPUSERID",
                                     "EFTPPROGRM",
                                     "EFTPTIMSYS",
                                     "EFTPSCRCOD",
                                     "EFTPOPECOD",
                                     "EFTPFLGMAS",
                                     "EFTPFLGWK1",
                                     "EFTPFLGWK2",
                                     "EFTPFLGWK3",
                                     "E01FTCDE",
                                     "E01FTDSC",
                                     "E01FTADD",
                                     "E01FTUSR",
                                     "E01FTPWR",
                                     "E01FTTPT"
                                   };
  final static String tnames[] = {
                                   "EFTPUSERID",
                                   "EFTPPROGRM",
                                   "EFTPTIMSYS",
                                   "EFTPSCRCOD",
                                   "EFTPOPECOD",
                                   "EFTPFLGMAS",
                                   "EFTPFLGWK1",
                                   "EFTPFLGWK2",
                                   "EFTPFLGWK3",
                                   "E01FTCDE",
                                   "E01FTDSC",
                                   "E01FTADD",
                                   "E01FTUSR",
                                   "E01FTPWR",
                                   "E01FTTPT"
                                 };
  final static String fid = "1140117100038";
  final static String rid = "5419AA112410A";
  final static String fmtname = "EFTP00101";
  final int FIELDCOUNT = 15;
  private static Hashtable tlookup = new Hashtable();
  private CharacterField fieldEFTPUSERID = null;
  private CharacterField fieldEFTPPROGRM = null;
  private CharacterField fieldEFTPTIMSYS = null;
  private CharacterField fieldEFTPSCRCOD = null;
  private CharacterField fieldEFTPOPECOD = null;
  private CharacterField fieldEFTPFLGMAS = null;
  private CharacterField fieldEFTPFLGWK1 = null;
  private CharacterField fieldEFTPFLGWK2 = null;
  private CharacterField fieldEFTPFLGWK3 = null;
  private CharacterField fieldE01FTCDE = null;
  private CharacterField fieldE01FTDSC = null;
  private CharacterField fieldE01FTADD = null;
  private CharacterField fieldE01FTUSR = null;
  private CharacterField fieldE01FTPWR = null;
  private CharacterField fieldE01FTTPT = null;

  /**
  * Constructor for EFTP00101Message.
  */
  public EFTP00101Message()
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
    recordsize = 261;
    fileid = fid;
    recordid = rid;
    message = new byte[getByteLength()];
    formatname = fmtname;
    fieldnames = fldnames;
    tagnames = tnames;
    fields = new MessageField[FIELDCOUNT];

    fields[0] = fieldEFTPUSERID =
    new CharacterField(message, HEADERSIZE + 0, 10, "EFTPUSERID");
    fields[1] = fieldEFTPPROGRM =
    new CharacterField(message, HEADERSIZE + 10, 10, "EFTPPROGRM");
    fields[2] = fieldEFTPTIMSYS =
    new CharacterField(message, HEADERSIZE + 20, 12, "EFTPTIMSYS");
    fields[3] = fieldEFTPSCRCOD =
    new CharacterField(message, HEADERSIZE + 32, 2, "EFTPSCRCOD");
    fields[4] = fieldEFTPOPECOD =
    new CharacterField(message, HEADERSIZE + 34, 4, "EFTPOPECOD");
    fields[5] = fieldEFTPFLGMAS =
    new CharacterField(message, HEADERSIZE + 38, 1, "EFTPFLGMAS");
    fields[6] = fieldEFTPFLGWK1 =
    new CharacterField(message, HEADERSIZE + 39, 1, "EFTPFLGWK1");
    fields[7] = fieldEFTPFLGWK2 =
    new CharacterField(message, HEADERSIZE + 40, 1, "EFTPFLGWK2");
    fields[8] = fieldEFTPFLGWK3 =
    new CharacterField(message, HEADERSIZE + 41, 1, "EFTPFLGWK3");
    fields[9] = fieldE01FTCDE =
    new CharacterField(message, HEADERSIZE + 42, 2, "E01FTCDE");
    fields[10] = fieldE01FTDSC =
    new CharacterField(message, HEADERSIZE + 44, 30, "E01FTDSC");
    fields[11] = fieldE01FTADD =
    new CharacterField(message, HEADERSIZE + 74, 15, "E01FTADD");
    fields[12] = fieldE01FTUSR =
    new CharacterField(message, HEADERSIZE + 89, 20, "E01FTUSR");
    fields[13] = fieldE01FTPWR =
    new CharacterField(message, HEADERSIZE + 109, 20, "E01FTPWR");
    fields[14] = fieldE01FTTPT =
    new CharacterField(message, HEADERSIZE + 129, 132, "E01FTTPT");

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
  * Set field EFTPUSERID using a String value.
  */
  public void setEFTPUSERID(String newvalue)
  {
    fieldEFTPUSERID.setString(newvalue);
  }

  /**
  * Get value of field EFTPUSERID as a String.
  */
  public String getEFTPUSERID()
  {
    return fieldEFTPUSERID.getString();
  }

  /**
  * Set field EFTPPROGRM using a String value.
  */
  public void setEFTPPROGRM(String newvalue)
  {
    fieldEFTPPROGRM.setString(newvalue);
  }

  /**
  * Get value of field EFTPPROGRM as a String.
  */
  public String getEFTPPROGRM()
  {
    return fieldEFTPPROGRM.getString();
  }

  /**
  * Set field EFTPTIMSYS using a String value.
  */
  public void setEFTPTIMSYS(String newvalue)
  {
    fieldEFTPTIMSYS.setString(newvalue);
  }

  /**
  * Get value of field EFTPTIMSYS as a String.
  */
  public String getEFTPTIMSYS()
  {
    return fieldEFTPTIMSYS.getString();
  }

  /**
  * Set field EFTPSCRCOD using a String value.
  */
  public void setEFTPSCRCOD(String newvalue)
  {
    fieldEFTPSCRCOD.setString(newvalue);
  }

  /**
  * Get value of field EFTPSCRCOD as a String.
  */
  public String getEFTPSCRCOD()
  {
    return fieldEFTPSCRCOD.getString();
  }

  /**
  * Set field EFTPOPECOD using a String value.
  */
  public void setEFTPOPECOD(String newvalue)
  {
    fieldEFTPOPECOD.setString(newvalue);
  }

  /**
  * Get value of field EFTPOPECOD as a String.
  */
  public String getEFTPOPECOD()
  {
    return fieldEFTPOPECOD.getString();
  }

  /**
  * Set field EFTPFLGMAS using a String value.
  */
  public void setEFTPFLGMAS(String newvalue)
  {
    fieldEFTPFLGMAS.setString(newvalue);
  }

  /**
  * Get value of field EFTPFLGMAS as a String.
  */
  public String getEFTPFLGMAS()
  {
    return fieldEFTPFLGMAS.getString();
  }

  /**
  * Set field EFTPFLGWK1 using a String value.
  */
  public void setEFTPFLGWK1(String newvalue)
  {
    fieldEFTPFLGWK1.setString(newvalue);
  }

  /**
  * Get value of field EFTPFLGWK1 as a String.
  */
  public String getEFTPFLGWK1()
  {
    return fieldEFTPFLGWK1.getString();
  }

  /**
  * Set field EFTPFLGWK2 using a String value.
  */
  public void setEFTPFLGWK2(String newvalue)
  {
    fieldEFTPFLGWK2.setString(newvalue);
  }

  /**
  * Get value of field EFTPFLGWK2 as a String.
  */
  public String getEFTPFLGWK2()
  {
    return fieldEFTPFLGWK2.getString();
  }

  /**
  * Set field EFTPFLGWK3 using a String value.
  */
  public void setEFTPFLGWK3(String newvalue)
  {
    fieldEFTPFLGWK3.setString(newvalue);
  }

  /**
  * Get value of field EFTPFLGWK3 as a String.
  */
  public String getEFTPFLGWK3()
  {
    return fieldEFTPFLGWK3.getString();
  }

  /**
  * Set field E01FTCDE using a String value.
  */
  public void setE01FTCDE(String newvalue)
  {
    fieldE01FTCDE.setString(newvalue);
  }

  /**
  * Get value of field E01FTCDE as a String.
  */
  public String getE01FTCDE()
  {
    return fieldE01FTCDE.getString();
  }

  /**
  * Set field E01FTDSC using a String value.
  */
  public void setE01FTDSC(String newvalue)
  {
    fieldE01FTDSC.setString(newvalue);
  }

  /**
  * Get value of field E01FTDSC as a String.
  */
  public String getE01FTDSC()
  {
    return fieldE01FTDSC.getString();
  }

  /**
  * Set field E01FTADD using a String value.
  */
  public void setE01FTADD(String newvalue)
  {
    fieldE01FTADD.setString(newvalue);
  }

  /**
  * Get value of field E01FTADD as a String.
  */
  public String getE01FTADD()
  {
    return fieldE01FTADD.getString();
  }

  /**
  * Set field E01FTUSR using a String value.
  */
  public void setE01FTUSR(String newvalue)
  {
    fieldE01FTUSR.setString(newvalue);
  }

  /**
  * Get value of field E01FTUSR as a String.
  */
  public String getE01FTUSR()
  {
    return fieldE01FTUSR.getString();
  }

  /**
  * Set field E01FTPWR using a String value.
  */
  public void setE01FTPWR(String newvalue)
  {
    fieldE01FTPWR.setString(newvalue);
  }

  /**
  * Get value of field E01FTPWR as a String.
  */
  public String getE01FTPWR()
  {
    return fieldE01FTPWR.getString();
  }

  /**
  * Set field E01FTTPT using a String value.
  */
  public void setE01FTTPT(String newvalue)
  {
    fieldE01FTTPT.setString(newvalue);
  }

  /**
  * Get value of field E01FTTPT as a String.
  */
  public String getE01FTTPT()
  {
    return fieldE01FTTPT.getString();
  }

}
