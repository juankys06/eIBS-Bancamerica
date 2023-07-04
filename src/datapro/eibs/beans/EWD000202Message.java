package datapro.eibs.beans;

import datapro.eibs.sockets.*;
import java.io.*;
import java.math.*;

import java.util.*;

/**
* Class generated by AS/400 CRTCLASS command from EWD000202 physical file definition.
* 
* File level identifier is 1120925120833.
* Record format level identifier is 408A5B6962206.
*/

public class EWD000202Message extends MessageRecord
{
  final static String fldnames[] = {
                                     "E02WDTBL",
                                     "E02WDACD",
                                     "E02WDSHN",
                                     "E02WDSHO",
                                     "E02WDCOD",
                                     "E02WDDSC",
                                     "E02WDSCG",
                                     "E02WDMID",
                                     "E02WDMIC",
                                     "E02WDCPC",
                                     "E02WDF02",
                                     "E02WDREC",
                                     "E02WDOPE"
                                   };
  final static String tnames[] = {
                                   "E02WDTBL",
                                   "E02WDACD",
                                   "E02WDSHN",
                                   "E02WDSHO",
                                   "E02WDCOD",
                                   "E02WDDSC",
                                   "E02WDSCG",
                                   "E02WDMID",
                                   "E02WDMIC",
                                   "E02WDCPC",
                                   "E02WDF02",
                                   "E02WDREC",
                                   "E02WDOPE"
                                 };
  final static String fid = "1120925120833";
  final static String rid = "408A5B6962206";
  final static String fmtname = "EWD000202";
  final int FIELDCOUNT = 13;
  private static Hashtable tlookup = new Hashtable();
  private CharacterField fieldE02WDTBL = null;
  private CharacterField fieldE02WDACD = null;
  private CharacterField fieldE02WDSHN = null;
  private CharacterField fieldE02WDSHO = null;
  private CharacterField fieldE02WDCOD = null;
  private CharacterField fieldE02WDDSC = null;
  private DecimalField fieldE02WDSCG = null;
  private CharacterField fieldE02WDMID = null;
  private CharacterField fieldE02WDMIC = null;
  private CharacterField fieldE02WDCPC = null;
  private CharacterField fieldE02WDF02 = null;
  private DecimalField fieldE02WDREC = null;
  private CharacterField fieldE02WDOPE = null;

  /**
  * Constructor for EWD000202Message.
  */
  public EWD000202Message()
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
    recordsize = 130;
    fileid = fid;
    recordid = rid;
    message = new byte[getByteLength()];
    formatname = fmtname;
    fieldnames = fldnames;
    tagnames = tnames;
    fields = new MessageField[FIELDCOUNT];

    fields[0] = fieldE02WDTBL =
    new CharacterField(message, HEADERSIZE + 0, 2, "E02WDTBL");
    fields[1] = fieldE02WDACD =
    new CharacterField(message, HEADERSIZE + 2, 2, "E02WDACD");
    fields[2] = fieldE02WDSHN =
    new CharacterField(message, HEADERSIZE + 4, 20, "E02WDSHN");
    fields[3] = fieldE02WDSHO =
    new CharacterField(message, HEADERSIZE + 24, 20, "E02WDSHO");
    fields[4] = fieldE02WDCOD =
    new CharacterField(message, HEADERSIZE + 44, 6, "E02WDCOD");
    fields[5] = fieldE02WDDSC =
    new CharacterField(message, HEADERSIZE + 50, 35, "E02WDDSC");
    fields[6] = fieldE02WDSCG =
    new DecimalField(message, HEADERSIZE + 85, 17, 0, "E02WDSCG");
    fields[7] = fieldE02WDMID =
    new CharacterField(message, HEADERSIZE + 102, 6, "E02WDMID");
    fields[8] = fieldE02WDMIC =
    new CharacterField(message, HEADERSIZE + 108, 6, "E02WDMIC");
    fields[9] = fieldE02WDCPC =
    new CharacterField(message, HEADERSIZE + 114, 4, "E02WDCPC");
    fields[10] = fieldE02WDF02 =
    new CharacterField(message, HEADERSIZE + 118, 3, "E02WDF02");
    fields[11] = fieldE02WDREC =
    new DecimalField(message, HEADERSIZE + 121, 8, 0, "E02WDREC");
    fields[12] = fieldE02WDOPE =
    new CharacterField(message, HEADERSIZE + 129, 1, "E02WDOPE");

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
  * Set field E02WDTBL using a String value.
  */
  public void setE02WDTBL(String newvalue)
  {
    fieldE02WDTBL.setString(newvalue);
  }

  /**
  * Get value of field E02WDTBL as a String.
  */
  public String getE02WDTBL()
  {
    return fieldE02WDTBL.getString();
  }

  /**
  * Set field E02WDACD using a String value.
  */
  public void setE02WDACD(String newvalue)
  {
    fieldE02WDACD.setString(newvalue);
  }

  /**
  * Get value of field E02WDACD as a String.
  */
  public String getE02WDACD()
  {
    return fieldE02WDACD.getString();
  }

  /**
  * Set field E02WDSHN using a String value.
  */
  public void setE02WDSHN(String newvalue)
  {
    fieldE02WDSHN.setString(newvalue);
  }

  /**
  * Get value of field E02WDSHN as a String.
  */
  public String getE02WDSHN()
  {
    return fieldE02WDSHN.getString();
  }

  /**
  * Set field E02WDSHO using a String value.
  */
  public void setE02WDSHO(String newvalue)
  {
    fieldE02WDSHO.setString(newvalue);
  }

  /**
  * Get value of field E02WDSHO as a String.
  */
  public String getE02WDSHO()
  {
    return fieldE02WDSHO.getString();
  }

  /**
  * Set field E02WDCOD using a String value.
  */
  public void setE02WDCOD(String newvalue)
  {
    fieldE02WDCOD.setString(newvalue);
  }

  /**
  * Get value of field E02WDCOD as a String.
  */
  public String getE02WDCOD()
  {
    return fieldE02WDCOD.getString();
  }

  /**
  * Set field E02WDDSC using a String value.
  */
  public void setE02WDDSC(String newvalue)
  {
    fieldE02WDDSC.setString(newvalue);
  }

  /**
  * Get value of field E02WDDSC as a String.
  */
  public String getE02WDDSC()
  {
    return fieldE02WDDSC.getString();
  }

  /**
  * Set field E02WDSCG using a String value.
  */
  public void setE02WDSCG(String newvalue)
  {
    fieldE02WDSCG.setString(newvalue);
  }

  /**
  * Get value of field E02WDSCG as a String.
  */
  public String getE02WDSCG()
  {
    return fieldE02WDSCG.getString();
  }

  /**
  * Set numeric field E02WDSCG using a BigDecimal value.
  */
  public void setE02WDSCG(BigDecimal newvalue)
  {
    fieldE02WDSCG.setBigDecimal(newvalue);
  }

  /**
  * Return value of numeric field E02WDSCG as a BigDecimal.
  */
  public BigDecimal getBigDecimalE02WDSCG()
  {
    return fieldE02WDSCG.getBigDecimal();
  }

  /**
  * Set field E02WDMID using a String value.
  */
  public void setE02WDMID(String newvalue)
  {
    fieldE02WDMID.setString(newvalue);
  }

  /**
  * Get value of field E02WDMID as a String.
  */
  public String getE02WDMID()
  {
    return fieldE02WDMID.getString();
  }

  /**
  * Set field E02WDMIC using a String value.
  */
  public void setE02WDMIC(String newvalue)
  {
    fieldE02WDMIC.setString(newvalue);
  }

  /**
  * Get value of field E02WDMIC as a String.
  */
  public String getE02WDMIC()
  {
    return fieldE02WDMIC.getString();
  }

  /**
  * Set field E02WDCPC using a String value.
  */
  public void setE02WDCPC(String newvalue)
  {
    fieldE02WDCPC.setString(newvalue);
  }

  /**
  * Get value of field E02WDCPC as a String.
  */
  public String getE02WDCPC()
  {
    return fieldE02WDCPC.getString();
  }

  /**
  * Set field E02WDF02 using a String value.
  */
  public void setE02WDF02(String newvalue)
  {
    fieldE02WDF02.setString(newvalue);
  }

  /**
  * Get value of field E02WDF02 as a String.
  */
  public String getE02WDF02()
  {
    return fieldE02WDF02.getString();
  }

  /**
  * Set field E02WDREC using a String value.
  */
  public void setE02WDREC(String newvalue)
  {
    fieldE02WDREC.setString(newvalue);
  }

  /**
  * Get value of field E02WDREC as a String.
  */
  public String getE02WDREC()
  {
    return fieldE02WDREC.getString();
  }

  /**
  * Set numeric field E02WDREC using a BigDecimal value.
  */
  public void setE02WDREC(BigDecimal newvalue)
  {
    fieldE02WDREC.setBigDecimal(newvalue);
  }

  /**
  * Return value of numeric field E02WDREC as a BigDecimal.
  */
  public BigDecimal getBigDecimalE02WDREC()
  {
    return fieldE02WDREC.getBigDecimal();
  }

  /**
  * Set field E02WDOPE using a String value.
  */
  public void setE02WDOPE(String newvalue)
  {
    fieldE02WDOPE.setString(newvalue);
  }

  /**
  * Get value of field E02WDOPE as a String.
  */
  public String getE02WDOPE()
  {
    return fieldE02WDOPE.getString();
  }

}