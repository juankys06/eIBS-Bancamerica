package datapro.eibs.beans;

import datapro.eibs.sockets.*;
import java.io.*;
import java.math.*;

import java.util.*;

/**
* Class generated by AS/400 CRTCLASS command from ECMTRNDS physical file definition.
* 
* File level identifier is 1030121191839.
* Record format level identifier is 4AF43619D2132.
*/

public class ECMTRNDSMessage extends MessageRecord
{
  final static String fldnames[] = {
                                     "FECHADEM",
                                     "FECHADED",
                                     "FECHADEY",
                                     "FECHAHAM",
                                     "FECHAHAD",
                                     "FECHAHAY",
                                     "TRANCO01",
                                     "TRANCO02",
                                     "TRANCO03",
                                     "TRANCO04",
                                     "TRANCO05",
                                     "TRANCO06",
                                     "TRANCO07",
                                     "TRANCO08",
                                     "TRANCO09",
                                     "TRANCO10",
                                     "TRANCO11",
                                     "TRANCO12",
                                     "TRANCO13",
                                     "TRANCO14",
                                     "TRANCO15",
                                     "TRANCO16",
                                     "TRANCO17",
                                     "TRANCO18",
                                     "TRANCO19",
                                     "TRANCO20",
                                     "TRANCO21",
                                     "TRANCO22",
                                     "TRANCO23",
                                     "TRANCO24",
                                     "TRANCO25",
                                     "TRANCO26",
                                     "TRANCO27",
                                     "TRANCO28",
                                     "TRANCO29",
                                     "TRANCO30"
                                   };
  final static String tnames[] = {
                                   "FECHADEM",
                                   "FECHADED",
                                   "FECHADEY",
                                   "FECHAHAM",
                                   "FECHAHAD",
                                   "FECHAHAY",
                                   "TRANCO01",
                                   "TRANCO02",
                                   "TRANCO03",
                                   "TRANCO04",
                                   "TRANCO05",
                                   "TRANCO06",
                                   "TRANCO07",
                                   "TRANCO08",
                                   "TRANCO09",
                                   "TRANCO10",
                                   "TRANCO11",
                                   "TRANCO12",
                                   "TRANCO13",
                                   "TRANCO14",
                                   "TRANCO15",
                                   "TRANCO16",
                                   "TRANCO17",
                                   "TRANCO18",
                                   "TRANCO19",
                                   "TRANCO20",
                                   "TRANCO21",
                                   "TRANCO22",
                                   "TRANCO23",
                                   "TRANCO24",
                                   "TRANCO25",
                                   "TRANCO26",
                                   "TRANCO27",
                                   "TRANCO28",
                                   "TRANCO29",
                                   "TRANCO30"
                                 };
  final static String fid = "1030121191839";
  final static String rid = "4AF43619D2132";
  final static String fmtname = "ECMTRNDS";
  final int FIELDCOUNT = 36;
  private static Hashtable tlookup = new Hashtable();
  private DecimalField fieldFECHADEM = null;
  private DecimalField fieldFECHADED = null;
  private DecimalField fieldFECHADEY = null;
  private DecimalField fieldFECHAHAM = null;
  private DecimalField fieldFECHAHAD = null;
  private DecimalField fieldFECHAHAY = null;
  private CharacterField fieldTRANCO01 = null;
  private CharacterField fieldTRANCO02 = null;
  private CharacterField fieldTRANCO03 = null;
  private CharacterField fieldTRANCO04 = null;
  private CharacterField fieldTRANCO05 = null;
  private CharacterField fieldTRANCO06 = null;
  private CharacterField fieldTRANCO07 = null;
  private CharacterField fieldTRANCO08 = null;
  private CharacterField fieldTRANCO09 = null;
  private CharacterField fieldTRANCO10 = null;
  private CharacterField fieldTRANCO11 = null;
  private CharacterField fieldTRANCO12 = null;
  private CharacterField fieldTRANCO13 = null;
  private CharacterField fieldTRANCO14 = null;
  private CharacterField fieldTRANCO15 = null;
  private CharacterField fieldTRANCO16 = null;
  private CharacterField fieldTRANCO17 = null;
  private CharacterField fieldTRANCO18 = null;
  private CharacterField fieldTRANCO19 = null;
  private CharacterField fieldTRANCO20 = null;
  private CharacterField fieldTRANCO21 = null;
  private CharacterField fieldTRANCO22 = null;
  private CharacterField fieldTRANCO23 = null;
  private CharacterField fieldTRANCO24 = null;
  private CharacterField fieldTRANCO25 = null;
  private CharacterField fieldTRANCO26 = null;
  private CharacterField fieldTRANCO27 = null;
  private CharacterField fieldTRANCO28 = null;
  private CharacterField fieldTRANCO29 = null;
  private CharacterField fieldTRANCO30 = null;

  /**
  * Constructor for ECMTRNDSMessage.
  */
  public ECMTRNDSMessage()
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
    recordsize = 78;
    fileid = fid;
    recordid = rid;
    message = new byte[getByteLength()];
    formatname = fmtname;
    fieldnames = fldnames;
    tagnames = tnames;
    fields = new MessageField[FIELDCOUNT];

    fields[0] = fieldFECHADEM =
    new DecimalField(message, HEADERSIZE + 0, 3, 0, "FECHADEM");
    fields[1] = fieldFECHADED =
    new DecimalField(message, HEADERSIZE + 3, 3, 0, "FECHADED");
    fields[2] = fieldFECHADEY =
    new DecimalField(message, HEADERSIZE + 6, 3, 0, "FECHADEY");
    fields[3] = fieldFECHAHAM =
    new DecimalField(message, HEADERSIZE + 9, 3, 0, "FECHAHAM");
    fields[4] = fieldFECHAHAD =
    new DecimalField(message, HEADERSIZE + 12, 3, 0, "FECHAHAD");
    fields[5] = fieldFECHAHAY =
    new DecimalField(message, HEADERSIZE + 15, 3, 0, "FECHAHAY");
    fields[6] = fieldTRANCO01 =
    new CharacterField(message, HEADERSIZE + 18, 2, "TRANCO01");
    fields[7] = fieldTRANCO02 =
    new CharacterField(message, HEADERSIZE + 20, 2, "TRANCO02");
    fields[8] = fieldTRANCO03 =
    new CharacterField(message, HEADERSIZE + 22, 2, "TRANCO03");
    fields[9] = fieldTRANCO04 =
    new CharacterField(message, HEADERSIZE + 24, 2, "TRANCO04");
    fields[10] = fieldTRANCO05 =
    new CharacterField(message, HEADERSIZE + 26, 2, "TRANCO05");
    fields[11] = fieldTRANCO06 =
    new CharacterField(message, HEADERSIZE + 28, 2, "TRANCO06");
    fields[12] = fieldTRANCO07 =
    new CharacterField(message, HEADERSIZE + 30, 2, "TRANCO07");
    fields[13] = fieldTRANCO08 =
    new CharacterField(message, HEADERSIZE + 32, 2, "TRANCO08");
    fields[14] = fieldTRANCO09 =
    new CharacterField(message, HEADERSIZE + 34, 2, "TRANCO09");
    fields[15] = fieldTRANCO10 =
    new CharacterField(message, HEADERSIZE + 36, 2, "TRANCO10");
    fields[16] = fieldTRANCO11 =
    new CharacterField(message, HEADERSIZE + 38, 2, "TRANCO11");
    fields[17] = fieldTRANCO12 =
    new CharacterField(message, HEADERSIZE + 40, 2, "TRANCO12");
    fields[18] = fieldTRANCO13 =
    new CharacterField(message, HEADERSIZE + 42, 2, "TRANCO13");
    fields[19] = fieldTRANCO14 =
    new CharacterField(message, HEADERSIZE + 44, 2, "TRANCO14");
    fields[20] = fieldTRANCO15 =
    new CharacterField(message, HEADERSIZE + 46, 2, "TRANCO15");
    fields[21] = fieldTRANCO16 =
    new CharacterField(message, HEADERSIZE + 48, 2, "TRANCO16");
    fields[22] = fieldTRANCO17 =
    new CharacterField(message, HEADERSIZE + 50, 2, "TRANCO17");
    fields[23] = fieldTRANCO18 =
    new CharacterField(message, HEADERSIZE + 52, 2, "TRANCO18");
    fields[24] = fieldTRANCO19 =
    new CharacterField(message, HEADERSIZE + 54, 2, "TRANCO19");
    fields[25] = fieldTRANCO20 =
    new CharacterField(message, HEADERSIZE + 56, 2, "TRANCO20");
    fields[26] = fieldTRANCO21 =
    new CharacterField(message, HEADERSIZE + 58, 2, "TRANCO21");
    fields[27] = fieldTRANCO22 =
    new CharacterField(message, HEADERSIZE + 60, 2, "TRANCO22");
    fields[28] = fieldTRANCO23 =
    new CharacterField(message, HEADERSIZE + 62, 2, "TRANCO23");
    fields[29] = fieldTRANCO24 =
    new CharacterField(message, HEADERSIZE + 64, 2, "TRANCO24");
    fields[30] = fieldTRANCO25 =
    new CharacterField(message, HEADERSIZE + 66, 2, "TRANCO25");
    fields[31] = fieldTRANCO26 =
    new CharacterField(message, HEADERSIZE + 68, 2, "TRANCO26");
    fields[32] = fieldTRANCO27 =
    new CharacterField(message, HEADERSIZE + 70, 2, "TRANCO27");
    fields[33] = fieldTRANCO28 =
    new CharacterField(message, HEADERSIZE + 72, 2, "TRANCO28");
    fields[34] = fieldTRANCO29 =
    new CharacterField(message, HEADERSIZE + 74, 2, "TRANCO29");
    fields[35] = fieldTRANCO30 =
    new CharacterField(message, HEADERSIZE + 76, 2, "TRANCO30");

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
  * Set field FECHADEM using a String value.
  */
  public void setFECHADEM(String newvalue)
  {
    fieldFECHADEM.setString(newvalue);
  }

  /**
  * Get value of field FECHADEM as a String.
  */
  public String getFECHADEM()
  {
    return fieldFECHADEM.getString();
  }

  /**
  * Set numeric field FECHADEM using a BigDecimal value.
  */
  public void setFECHADEM(BigDecimal newvalue)
  {
    fieldFECHADEM.setBigDecimal(newvalue);
  }

  /**
  * Return value of numeric field FECHADEM as a BigDecimal.
  */
  public BigDecimal getBigDecimalFECHADEM()
  {
    return fieldFECHADEM.getBigDecimal();
  }

  /**
  * Set field FECHADED using a String value.
  */
  public void setFECHADED(String newvalue)
  {
    fieldFECHADED.setString(newvalue);
  }

  /**
  * Get value of field FECHADED as a String.
  */
  public String getFECHADED()
  {
    return fieldFECHADED.getString();
  }

  /**
  * Set numeric field FECHADED using a BigDecimal value.
  */
  public void setFECHADED(BigDecimal newvalue)
  {
    fieldFECHADED.setBigDecimal(newvalue);
  }

  /**
  * Return value of numeric field FECHADED as a BigDecimal.
  */
  public BigDecimal getBigDecimalFECHADED()
  {
    return fieldFECHADED.getBigDecimal();
  }

  /**
  * Set field FECHADEY using a String value.
  */
  public void setFECHADEY(String newvalue)
  {
    fieldFECHADEY.setString(newvalue);
  }

  /**
  * Get value of field FECHADEY as a String.
  */
  public String getFECHADEY()
  {
    return fieldFECHADEY.getString();
  }

  /**
  * Set numeric field FECHADEY using a BigDecimal value.
  */
  public void setFECHADEY(BigDecimal newvalue)
  {
    fieldFECHADEY.setBigDecimal(newvalue);
  }

  /**
  * Return value of numeric field FECHADEY as a BigDecimal.
  */
  public BigDecimal getBigDecimalFECHADEY()
  {
    return fieldFECHADEY.getBigDecimal();
  }

  /**
  * Set field FECHAHAM using a String value.
  */
  public void setFECHAHAM(String newvalue)
  {
    fieldFECHAHAM.setString(newvalue);
  }

  /**
  * Get value of field FECHAHAM as a String.
  */
  public String getFECHAHAM()
  {
    return fieldFECHAHAM.getString();
  }

  /**
  * Set numeric field FECHAHAM using a BigDecimal value.
  */
  public void setFECHAHAM(BigDecimal newvalue)
  {
    fieldFECHAHAM.setBigDecimal(newvalue);
  }

  /**
  * Return value of numeric field FECHAHAM as a BigDecimal.
  */
  public BigDecimal getBigDecimalFECHAHAM()
  {
    return fieldFECHAHAM.getBigDecimal();
  }

  /**
  * Set field FECHAHAD using a String value.
  */
  public void setFECHAHAD(String newvalue)
  {
    fieldFECHAHAD.setString(newvalue);
  }

  /**
  * Get value of field FECHAHAD as a String.
  */
  public String getFECHAHAD()
  {
    return fieldFECHAHAD.getString();
  }

  /**
  * Set numeric field FECHAHAD using a BigDecimal value.
  */
  public void setFECHAHAD(BigDecimal newvalue)
  {
    fieldFECHAHAD.setBigDecimal(newvalue);
  }

  /**
  * Return value of numeric field FECHAHAD as a BigDecimal.
  */
  public BigDecimal getBigDecimalFECHAHAD()
  {
    return fieldFECHAHAD.getBigDecimal();
  }

  /**
  * Set field FECHAHAY using a String value.
  */
  public void setFECHAHAY(String newvalue)
  {
    fieldFECHAHAY.setString(newvalue);
  }

  /**
  * Get value of field FECHAHAY as a String.
  */
  public String getFECHAHAY()
  {
    return fieldFECHAHAY.getString();
  }

  /**
  * Set numeric field FECHAHAY using a BigDecimal value.
  */
  public void setFECHAHAY(BigDecimal newvalue)
  {
    fieldFECHAHAY.setBigDecimal(newvalue);
  }

  /**
  * Return value of numeric field FECHAHAY as a BigDecimal.
  */
  public BigDecimal getBigDecimalFECHAHAY()
  {
    return fieldFECHAHAY.getBigDecimal();
  }

  /**
  * Set field TRANCO01 using a String value.
  */
  public void setTRANCO01(String newvalue)
  {
    fieldTRANCO01.setString(newvalue);
  }

  /**
  * Get value of field TRANCO01 as a String.
  */
  public String getTRANCO01()
  {
    return fieldTRANCO01.getString();
  }

  /**
  * Set field TRANCO02 using a String value.
  */
  public void setTRANCO02(String newvalue)
  {
    fieldTRANCO02.setString(newvalue);
  }

  /**
  * Get value of field TRANCO02 as a String.
  */
  public String getTRANCO02()
  {
    return fieldTRANCO02.getString();
  }

  /**
  * Set field TRANCO03 using a String value.
  */
  public void setTRANCO03(String newvalue)
  {
    fieldTRANCO03.setString(newvalue);
  }

  /**
  * Get value of field TRANCO03 as a String.
  */
  public String getTRANCO03()
  {
    return fieldTRANCO03.getString();
  }

  /**
  * Set field TRANCO04 using a String value.
  */
  public void setTRANCO04(String newvalue)
  {
    fieldTRANCO04.setString(newvalue);
  }

  /**
  * Get value of field TRANCO04 as a String.
  */
  public String getTRANCO04()
  {
    return fieldTRANCO04.getString();
  }

  /**
  * Set field TRANCO05 using a String value.
  */
  public void setTRANCO05(String newvalue)
  {
    fieldTRANCO05.setString(newvalue);
  }

  /**
  * Get value of field TRANCO05 as a String.
  */
  public String getTRANCO05()
  {
    return fieldTRANCO05.getString();
  }

  /**
  * Set field TRANCO06 using a String value.
  */
  public void setTRANCO06(String newvalue)
  {
    fieldTRANCO06.setString(newvalue);
  }

  /**
  * Get value of field TRANCO06 as a String.
  */
  public String getTRANCO06()
  {
    return fieldTRANCO06.getString();
  }

  /**
  * Set field TRANCO07 using a String value.
  */
  public void setTRANCO07(String newvalue)
  {
    fieldTRANCO07.setString(newvalue);
  }

  /**
  * Get value of field TRANCO07 as a String.
  */
  public String getTRANCO07()
  {
    return fieldTRANCO07.getString();
  }

  /**
  * Set field TRANCO08 using a String value.
  */
  public void setTRANCO08(String newvalue)
  {
    fieldTRANCO08.setString(newvalue);
  }

  /**
  * Get value of field TRANCO08 as a String.
  */
  public String getTRANCO08()
  {
    return fieldTRANCO08.getString();
  }

  /**
  * Set field TRANCO09 using a String value.
  */
  public void setTRANCO09(String newvalue)
  {
    fieldTRANCO09.setString(newvalue);
  }

  /**
  * Get value of field TRANCO09 as a String.
  */
  public String getTRANCO09()
  {
    return fieldTRANCO09.getString();
  }

  /**
  * Set field TRANCO10 using a String value.
  */
  public void setTRANCO10(String newvalue)
  {
    fieldTRANCO10.setString(newvalue);
  }

  /**
  * Get value of field TRANCO10 as a String.
  */
  public String getTRANCO10()
  {
    return fieldTRANCO10.getString();
  }

  /**
  * Set field TRANCO11 using a String value.
  */
  public void setTRANCO11(String newvalue)
  {
    fieldTRANCO11.setString(newvalue);
  }

  /**
  * Get value of field TRANCO11 as a String.
  */
  public String getTRANCO11()
  {
    return fieldTRANCO11.getString();
  }

  /**
  * Set field TRANCO12 using a String value.
  */
  public void setTRANCO12(String newvalue)
  {
    fieldTRANCO12.setString(newvalue);
  }

  /**
  * Get value of field TRANCO12 as a String.
  */
  public String getTRANCO12()
  {
    return fieldTRANCO12.getString();
  }

  /**
  * Set field TRANCO13 using a String value.
  */
  public void setTRANCO13(String newvalue)
  {
    fieldTRANCO13.setString(newvalue);
  }

  /**
  * Get value of field TRANCO13 as a String.
  */
  public String getTRANCO13()
  {
    return fieldTRANCO13.getString();
  }

  /**
  * Set field TRANCO14 using a String value.
  */
  public void setTRANCO14(String newvalue)
  {
    fieldTRANCO14.setString(newvalue);
  }

  /**
  * Get value of field TRANCO14 as a String.
  */
  public String getTRANCO14()
  {
    return fieldTRANCO14.getString();
  }

  /**
  * Set field TRANCO15 using a String value.
  */
  public void setTRANCO15(String newvalue)
  {
    fieldTRANCO15.setString(newvalue);
  }

  /**
  * Get value of field TRANCO15 as a String.
  */
  public String getTRANCO15()
  {
    return fieldTRANCO15.getString();
  }

  /**
  * Set field TRANCO16 using a String value.
  */
  public void setTRANCO16(String newvalue)
  {
    fieldTRANCO16.setString(newvalue);
  }

  /**
  * Get value of field TRANCO16 as a String.
  */
  public String getTRANCO16()
  {
    return fieldTRANCO16.getString();
  }

  /**
  * Set field TRANCO17 using a String value.
  */
  public void setTRANCO17(String newvalue)
  {
    fieldTRANCO17.setString(newvalue);
  }

  /**
  * Get value of field TRANCO17 as a String.
  */
  public String getTRANCO17()
  {
    return fieldTRANCO17.getString();
  }

  /**
  * Set field TRANCO18 using a String value.
  */
  public void setTRANCO18(String newvalue)
  {
    fieldTRANCO18.setString(newvalue);
  }

  /**
  * Get value of field TRANCO18 as a String.
  */
  public String getTRANCO18()
  {
    return fieldTRANCO18.getString();
  }

  /**
  * Set field TRANCO19 using a String value.
  */
  public void setTRANCO19(String newvalue)
  {
    fieldTRANCO19.setString(newvalue);
  }

  /**
  * Get value of field TRANCO19 as a String.
  */
  public String getTRANCO19()
  {
    return fieldTRANCO19.getString();
  }

  /**
  * Set field TRANCO20 using a String value.
  */
  public void setTRANCO20(String newvalue)
  {
    fieldTRANCO20.setString(newvalue);
  }

  /**
  * Get value of field TRANCO20 as a String.
  */
  public String getTRANCO20()
  {
    return fieldTRANCO20.getString();
  }

  /**
  * Set field TRANCO21 using a String value.
  */
  public void setTRANCO21(String newvalue)
  {
    fieldTRANCO21.setString(newvalue);
  }

  /**
  * Get value of field TRANCO21 as a String.
  */
  public String getTRANCO21()
  {
    return fieldTRANCO21.getString();
  }

  /**
  * Set field TRANCO22 using a String value.
  */
  public void setTRANCO22(String newvalue)
  {
    fieldTRANCO22.setString(newvalue);
  }

  /**
  * Get value of field TRANCO22 as a String.
  */
  public String getTRANCO22()
  {
    return fieldTRANCO22.getString();
  }

  /**
  * Set field TRANCO23 using a String value.
  */
  public void setTRANCO23(String newvalue)
  {
    fieldTRANCO23.setString(newvalue);
  }

  /**
  * Get value of field TRANCO23 as a String.
  */
  public String getTRANCO23()
  {
    return fieldTRANCO23.getString();
  }

  /**
  * Set field TRANCO24 using a String value.
  */
  public void setTRANCO24(String newvalue)
  {
    fieldTRANCO24.setString(newvalue);
  }

  /**
  * Get value of field TRANCO24 as a String.
  */
  public String getTRANCO24()
  {
    return fieldTRANCO24.getString();
  }

  /**
  * Set field TRANCO25 using a String value.
  */
  public void setTRANCO25(String newvalue)
  {
    fieldTRANCO25.setString(newvalue);
  }

  /**
  * Get value of field TRANCO25 as a String.
  */
  public String getTRANCO25()
  {
    return fieldTRANCO25.getString();
  }

  /**
  * Set field TRANCO26 using a String value.
  */
  public void setTRANCO26(String newvalue)
  {
    fieldTRANCO26.setString(newvalue);
  }

  /**
  * Get value of field TRANCO26 as a String.
  */
  public String getTRANCO26()
  {
    return fieldTRANCO26.getString();
  }

  /**
  * Set field TRANCO27 using a String value.
  */
  public void setTRANCO27(String newvalue)
  {
    fieldTRANCO27.setString(newvalue);
  }

  /**
  * Get value of field TRANCO27 as a String.
  */
  public String getTRANCO27()
  {
    return fieldTRANCO27.getString();
  }

  /**
  * Set field TRANCO28 using a String value.
  */
  public void setTRANCO28(String newvalue)
  {
    fieldTRANCO28.setString(newvalue);
  }

  /**
  * Get value of field TRANCO28 as a String.
  */
  public String getTRANCO28()
  {
    return fieldTRANCO28.getString();
  }

  /**
  * Set field TRANCO29 using a String value.
  */
  public void setTRANCO29(String newvalue)
  {
    fieldTRANCO29.setString(newvalue);
  }

  /**
  * Get value of field TRANCO29 as a String.
  */
  public String getTRANCO29()
  {
    return fieldTRANCO29.getString();
  }

  /**
  * Set field TRANCO30 using a String value.
  */
  public void setTRANCO30(String newvalue)
  {
    fieldTRANCO30.setString(newvalue);
  }

  /**
  * Get value of field TRANCO30 as a String.
  */
  public String getTRANCO30()
  {
    return fieldTRANCO30.getString();
  }

}