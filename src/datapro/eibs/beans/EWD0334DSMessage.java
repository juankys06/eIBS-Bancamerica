package datapro.eibs.beans;

import datapro.eibs.sockets.*;
import java.io.*;
import java.math.*;

import java.util.*;

/**
* Class generated by AS/400 CRTCLASS command from EWD0334DS physical file definition.
* 
* File level identifier is 1060830114057.
* Record format level identifier is 4DCE139E091C6.
*/

public class EWD0334DSMessage extends MessageRecord
{
  final static String fldnames[] = {
                                     "RWDUSR",
                                     "RWDTYP",
                                     "RWDDAY",
                                     "RWDFRC",
                                     "SWDBNK",
                                     "SWDBRN",
                                     "SWDCCY",
                                     "SWDACC",
                                     "SWDTYP",
                                     "SWDSBT",
                                     "SWDPRO",
                                     "SWDTRA",
                                     "SWDVAL",
                                     "SWDMAT",
                                     "SWDCUN",
                                     "SWDAMT",
                                     "SWDDSC",
                                     "SWDSTS",
                                     "SWDREC",
                                     "SWDOPE"
                                   };
  final static String tnames[] = {
                                   "RWDUSR",
                                   "RWDTYP",
                                   "RWDDAY",
                                   "RWDFRC",
                                   "SWDBNK",
                                   "SWDBRN",
                                   "SWDCCY",
                                   "SWDACC",
                                   "SWDTYP",
                                   "SWDSBT",
                                   "SWDPRO",
                                   "SWDTRA",
                                   "SWDVAL",
                                   "SWDMAT",
                                   "SWDCUN",
                                   "SWDAMT",
                                   "SWDDSC",
                                   "SWDSTS",
                                   "SWDREC",
                                   "SWDOPE"
                                 };
  final static String fid = "1060830114057";
  final static String rid = "4DCE139E091C6";
  final static String fmtname = "EWD0334DS";
  final int FIELDCOUNT = 20;
  private static Hashtable tlookup = new Hashtable();
  private CharacterField fieldRWDUSR = null;
  private CharacterField fieldRWDTYP = null;
  private DecimalField fieldRWDDAY = null;
  private DecimalField fieldRWDFRC = null;
  private CharacterField fieldSWDBNK = null;
  private DecimalField fieldSWDBRN = null;
  private CharacterField fieldSWDCCY = null;
  private DecimalField fieldSWDACC = null;
  private CharacterField fieldSWDTYP = null;
  private CharacterField fieldSWDSBT = null;
  private CharacterField fieldSWDPRO = null;
  private DecimalField fieldSWDTRA = null;
  private DecimalField fieldSWDVAL = null;
  private DecimalField fieldSWDMAT = null;
  private DecimalField fieldSWDCUN = null;
  private DecimalField fieldSWDAMT = null;
  private CharacterField fieldSWDDSC = null;
  private CharacterField fieldSWDSTS = null;
  private DecimalField fieldSWDREC = null;
  private CharacterField fieldSWDOPE = null;

  /**
  * Constructor for EWD0334DSMessage.
  */
  public EWD0334DSMessage()
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
    recordsize = 163;
    fileid = fid;
    recordid = rid;
    message = new byte[getByteLength()];
    formatname = fmtname;
    fieldnames = fldnames;
    tagnames = tnames;
    fields = new MessageField[FIELDCOUNT];

    fields[0] = fieldRWDUSR =
    new CharacterField(message, HEADERSIZE + 0, 10, "RWDUSR");
    fields[1] = fieldRWDTYP =
    new CharacterField(message, HEADERSIZE + 10, 1, "RWDTYP");
    fields[2] = fieldRWDDAY =
    new DecimalField(message, HEADERSIZE + 11, 8, 0, "RWDDAY");
    fields[3] = fieldRWDFRC =
    new DecimalField(message, HEADERSIZE + 19, 8, 0, "RWDFRC");
    fields[4] = fieldSWDBNK =
    new CharacterField(message, HEADERSIZE + 27, 2, "SWDBNK");
    fields[5] = fieldSWDBRN =
    new DecimalField(message, HEADERSIZE + 29, 4, 0, "SWDBRN");
    fields[6] = fieldSWDCCY =
    new CharacterField(message, HEADERSIZE + 33, 3, "SWDCCY");
    fields[7] = fieldSWDACC =
    new DecimalField(message, HEADERSIZE + 36, 13, 0, "SWDACC");
    fields[8] = fieldSWDTYP =
    new CharacterField(message, HEADERSIZE + 49, 4, "SWDTYP");
    fields[9] = fieldSWDSBT =
    new CharacterField(message, HEADERSIZE + 53, 4, "SWDSBT");
    fields[10] = fieldSWDPRO =
    new CharacterField(message, HEADERSIZE + 57, 4, "SWDPRO");
    fields[11] = fieldSWDTRA =
    new DecimalField(message, HEADERSIZE + 61, 7, 0, "SWDTRA");
    fields[12] = fieldSWDVAL =
    new DecimalField(message, HEADERSIZE + 68, 7, 0, "SWDVAL");
    fields[13] = fieldSWDMAT =
    new DecimalField(message, HEADERSIZE + 75, 7, 0, "SWDMAT");
    fields[14] = fieldSWDCUN =
    new DecimalField(message, HEADERSIZE + 82, 10, 0, "SWDCUN");
    fields[15] = fieldSWDAMT =
    new DecimalField(message, HEADERSIZE + 92, 17, 2, "SWDAMT");
    fields[16] = fieldSWDDSC =
    new CharacterField(message, HEADERSIZE + 109, 30, "SWDDSC");
    fields[17] = fieldSWDSTS =
    new CharacterField(message, HEADERSIZE + 139, 15, "SWDSTS");
    fields[18] = fieldSWDREC =
    new DecimalField(message, HEADERSIZE + 154, 8, 0, "SWDREC");
    fields[19] = fieldSWDOPE =
    new CharacterField(message, HEADERSIZE + 162, 1, "SWDOPE");

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
  * Set field RWDUSR using a String value.
  */
  public void setRWDUSR(String newvalue)
  {
    fieldRWDUSR.setString(newvalue);
  }

  /**
  * Get value of field RWDUSR as a String.
  */
  public String getRWDUSR()
  {
    return fieldRWDUSR.getString();
  }

  /**
  * Set field RWDTYP using a String value.
  */
  public void setRWDTYP(String newvalue)
  {
    fieldRWDTYP.setString(newvalue);
  }

  /**
  * Get value of field RWDTYP as a String.
  */
  public String getRWDTYP()
  {
    return fieldRWDTYP.getString();
  }

  /**
  * Set field RWDDAY using a String value.
  */
  public void setRWDDAY(String newvalue)
  {
    fieldRWDDAY.setString(newvalue);
  }

  /**
  * Get value of field RWDDAY as a String.
  */
  public String getRWDDAY()
  {
    return fieldRWDDAY.getString();
  }

  /**
  * Set numeric field RWDDAY using a BigDecimal value.
  */
  public void setRWDDAY(BigDecimal newvalue)
  {
    fieldRWDDAY.setBigDecimal(newvalue);
  }

  /**
  * Return value of numeric field RWDDAY as a BigDecimal.
  */
  public BigDecimal getBigDecimalRWDDAY()
  {
    return fieldRWDDAY.getBigDecimal();
  }

  /**
  * Set field RWDFRC using a String value.
  */
  public void setRWDFRC(String newvalue)
  {
    fieldRWDFRC.setString(newvalue);
  }

  /**
  * Get value of field RWDFRC as a String.
  */
  public String getRWDFRC()
  {
    return fieldRWDFRC.getString();
  }

  /**
  * Set numeric field RWDFRC using a BigDecimal value.
  */
  public void setRWDFRC(BigDecimal newvalue)
  {
    fieldRWDFRC.setBigDecimal(newvalue);
  }

  /**
  * Return value of numeric field RWDFRC as a BigDecimal.
  */
  public BigDecimal getBigDecimalRWDFRC()
  {
    return fieldRWDFRC.getBigDecimal();
  }

  /**
  * Set field SWDBNK using a String value.
  */
  public void setSWDBNK(String newvalue)
  {
    fieldSWDBNK.setString(newvalue);
  }

  /**
  * Get value of field SWDBNK as a String.
  */
  public String getSWDBNK()
  {
    return fieldSWDBNK.getString();
  }

  /**
  * Set field SWDBRN using a String value.
  */
  public void setSWDBRN(String newvalue)
  {
    fieldSWDBRN.setString(newvalue);
  }

  /**
  * Get value of field SWDBRN as a String.
  */
  public String getSWDBRN()
  {
    return fieldSWDBRN.getString();
  }

  /**
  * Set numeric field SWDBRN using a BigDecimal value.
  */
  public void setSWDBRN(BigDecimal newvalue)
  {
    fieldSWDBRN.setBigDecimal(newvalue);
  }

  /**
  * Return value of numeric field SWDBRN as a BigDecimal.
  */
  public BigDecimal getBigDecimalSWDBRN()
  {
    return fieldSWDBRN.getBigDecimal();
  }

  /**
  * Set field SWDCCY using a String value.
  */
  public void setSWDCCY(String newvalue)
  {
    fieldSWDCCY.setString(newvalue);
  }

  /**
  * Get value of field SWDCCY as a String.
  */
  public String getSWDCCY()
  {
    return fieldSWDCCY.getString();
  }

  /**
  * Set field SWDACC using a String value.
  */
  public void setSWDACC(String newvalue)
  {
    fieldSWDACC.setString(newvalue);
  }

  /**
  * Get value of field SWDACC as a String.
  */
  public String getSWDACC()
  {
    return fieldSWDACC.getString();
  }

  /**
  * Set numeric field SWDACC using a BigDecimal value.
  */
  public void setSWDACC(BigDecimal newvalue)
  {
    fieldSWDACC.setBigDecimal(newvalue);
  }

  /**
  * Return value of numeric field SWDACC as a BigDecimal.
  */
  public BigDecimal getBigDecimalSWDACC()
  {
    return fieldSWDACC.getBigDecimal();
  }

  /**
  * Set field SWDTYP using a String value.
  */
  public void setSWDTYP(String newvalue)
  {
    fieldSWDTYP.setString(newvalue);
  }

  /**
  * Get value of field SWDTYP as a String.
  */
  public String getSWDTYP()
  {
    return fieldSWDTYP.getString();
  }

  /**
  * Set field SWDSBT using a String value.
  */
  public void setSWDSBT(String newvalue)
  {
    fieldSWDSBT.setString(newvalue);
  }

  /**
  * Get value of field SWDSBT as a String.
  */
  public String getSWDSBT()
  {
    return fieldSWDSBT.getString();
  }

  /**
  * Set field SWDPRO using a String value.
  */
  public void setSWDPRO(String newvalue)
  {
    fieldSWDPRO.setString(newvalue);
  }

  /**
  * Get value of field SWDPRO as a String.
  */
  public String getSWDPRO()
  {
    return fieldSWDPRO.getString();
  }

  /**
  * Set field SWDTRA using a String value.
  */
  public void setSWDTRA(String newvalue)
  {
    fieldSWDTRA.setString(newvalue);
  }

  /**
  * Get value of field SWDTRA as a String.
  */
  public String getSWDTRA()
  {
    return fieldSWDTRA.getString();
  }

  /**
  * Set numeric field SWDTRA using a BigDecimal value.
  */
  public void setSWDTRA(BigDecimal newvalue)
  {
    fieldSWDTRA.setBigDecimal(newvalue);
  }

  /**
  * Return value of numeric field SWDTRA as a BigDecimal.
  */
  public BigDecimal getBigDecimalSWDTRA()
  {
    return fieldSWDTRA.getBigDecimal();
  }

  /**
  * Set field SWDVAL using a String value.
  */
  public void setSWDVAL(String newvalue)
  {
    fieldSWDVAL.setString(newvalue);
  }

  /**
  * Get value of field SWDVAL as a String.
  */
  public String getSWDVAL()
  {
    return fieldSWDVAL.getString();
  }

  /**
  * Set numeric field SWDVAL using a BigDecimal value.
  */
  public void setSWDVAL(BigDecimal newvalue)
  {
    fieldSWDVAL.setBigDecimal(newvalue);
  }

  /**
  * Return value of numeric field SWDVAL as a BigDecimal.
  */
  public BigDecimal getBigDecimalSWDVAL()
  {
    return fieldSWDVAL.getBigDecimal();
  }

  /**
  * Set field SWDMAT using a String value.
  */
  public void setSWDMAT(String newvalue)
  {
    fieldSWDMAT.setString(newvalue);
  }

  /**
  * Get value of field SWDMAT as a String.
  */
  public String getSWDMAT()
  {
    return fieldSWDMAT.getString();
  }

  /**
  * Set numeric field SWDMAT using a BigDecimal value.
  */
  public void setSWDMAT(BigDecimal newvalue)
  {
    fieldSWDMAT.setBigDecimal(newvalue);
  }

  /**
  * Return value of numeric field SWDMAT as a BigDecimal.
  */
  public BigDecimal getBigDecimalSWDMAT()
  {
    return fieldSWDMAT.getBigDecimal();
  }

  /**
  * Set field SWDCUN using a String value.
  */
  public void setSWDCUN(String newvalue)
  {
    fieldSWDCUN.setString(newvalue);
  }

  /**
  * Get value of field SWDCUN as a String.
  */
  public String getSWDCUN()
  {
    return fieldSWDCUN.getString();
  }

  /**
  * Set numeric field SWDCUN using a BigDecimal value.
  */
  public void setSWDCUN(BigDecimal newvalue)
  {
    fieldSWDCUN.setBigDecimal(newvalue);
  }

  /**
  * Return value of numeric field SWDCUN as a BigDecimal.
  */
  public BigDecimal getBigDecimalSWDCUN()
  {
    return fieldSWDCUN.getBigDecimal();
  }

  /**
  * Set field SWDAMT using a String value.
  */
  public void setSWDAMT(String newvalue)
  {
    fieldSWDAMT.setString(newvalue);
  }

  /**
  * Get value of field SWDAMT as a String.
  */
  public String getSWDAMT()
  {
    return fieldSWDAMT.getString();
  }

  /**
  * Set numeric field SWDAMT using a BigDecimal value.
  */
  public void setSWDAMT(BigDecimal newvalue)
  {
    fieldSWDAMT.setBigDecimal(newvalue);
  }

  /**
  * Return value of numeric field SWDAMT as a BigDecimal.
  */
  public BigDecimal getBigDecimalSWDAMT()
  {
    return fieldSWDAMT.getBigDecimal();
  }

  /**
  * Set field SWDDSC using a String value.
  */
  public void setSWDDSC(String newvalue)
  {
    fieldSWDDSC.setString(newvalue);
  }

  /**
  * Get value of field SWDDSC as a String.
  */
  public String getSWDDSC()
  {
    return fieldSWDDSC.getString();
  }

  /**
  * Set field SWDSTS using a String value.
  */
  public void setSWDSTS(String newvalue)
  {
    fieldSWDSTS.setString(newvalue);
  }

  /**
  * Get value of field SWDSTS as a String.
  */
  public String getSWDSTS()
  {
    return fieldSWDSTS.getString();
  }

  /**
  * Set field SWDREC using a String value.
  */
  public void setSWDREC(String newvalue)
  {
    fieldSWDREC.setString(newvalue);
  }

  /**
  * Get value of field SWDREC as a String.
  */
  public String getSWDREC()
  {
    return fieldSWDREC.getString();
  }

  /**
  * Set numeric field SWDREC using a BigDecimal value.
  */
  public void setSWDREC(BigDecimal newvalue)
  {
    fieldSWDREC.setBigDecimal(newvalue);
  }

  /**
  * Return value of numeric field SWDREC as a BigDecimal.
  */
  public BigDecimal getBigDecimalSWDREC()
  {
    return fieldSWDREC.getBigDecimal();
  }

  /**
  * Set field SWDOPE using a String value.
  */
  public void setSWDOPE(String newvalue)
  {
    fieldSWDOPE.setString(newvalue);
  }

  /**
  * Get value of field SWDOPE as a String.
  */
  public String getSWDOPE()
  {
    return fieldSWDOPE.getString();
  }

}
