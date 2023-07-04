package datapro.eibs.beans;

import datapro.eibs.sockets.*;
import java.io.*;
import java.math.*;

import java.util.*;

/**
* Class generated by AS/400 CRTCLASS command from EWD0411DS physical file definition.
* 
* File level identifier is 1060223192611.
* Record format level identifier is 3B5415799858D.
*/

public class EWD0411DSMessage extends MessageRecord
{
  final static String fldnames[] = {
                                     "EWDNUM",
                                     "EWDDTE",
                                     "EWDOPE",
                                     "EWDNA1",
                                     "EWDFLG"
                                   };
  final static String tnames[] = {
                                   "EWDNUM",
                                   "EWDDTE",
                                   "EWDOPE",
                                   "EWDNA1",
                                   "EWDFLG"
                                 };
  final static String fid = "1060223192611";
  final static String rid = "3B5415799858D";
  final static String fmtname = "EWD0411DS";
  final int FIELDCOUNT = 5;
  private static Hashtable tlookup = new Hashtable();
  private CharacterField fieldEWDNUM = null;
  private DecimalField fieldEWDDTE = null;
  private CharacterField fieldEWDOPE = null;
  private CharacterField fieldEWDNA1 = null;
  private CharacterField fieldEWDFLG = null;

  /**
  * Constructor for EWD0411DSMessage.
  */
  public EWD0411DSMessage()
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
    recordsize = 76;
    fileid = fid;
    recordid = rid;
    message = new byte[getByteLength()];
    formatname = fmtname;
    fieldnames = fldnames;
    tagnames = tnames;
    fields = new MessageField[FIELDCOUNT];

    fields[0] = fieldEWDNUM =
    new CharacterField(message, HEADERSIZE + 0, 20, "EWDNUM");
    fields[1] = fieldEWDDTE =
    new DecimalField(message, HEADERSIZE + 20, 9, 0, "EWDDTE");
    fields[2] = fieldEWDOPE =
    new CharacterField(message, HEADERSIZE + 29, 1, "EWDOPE");
    fields[3] = fieldEWDNA1 =
    new CharacterField(message, HEADERSIZE + 30, 45, "EWDNA1");
    fields[4] = fieldEWDFLG =
    new CharacterField(message, HEADERSIZE + 75, 1, "EWDFLG");

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
  * Set field EWDNUM using a String value.
  */
  public void setEWDNUM(String newvalue)
  {
    fieldEWDNUM.setString(newvalue);
  }

  /**
  * Get value of field EWDNUM as a String.
  */
  public String getEWDNUM()
  {
    return fieldEWDNUM.getString();
  }

  /**
  * Set field EWDDTE using a String value.
  */
  public void setEWDDTE(String newvalue)
  {
    fieldEWDDTE.setString(newvalue);
  }

  /**
  * Get value of field EWDDTE as a String.
  */
  public String getEWDDTE()
  {
    return fieldEWDDTE.getString();
  }

  /**
  * Set numeric field EWDDTE using a BigDecimal value.
  */
  public void setEWDDTE(BigDecimal newvalue)
  {
    fieldEWDDTE.setBigDecimal(newvalue);
  }

  /**
  * Return value of numeric field EWDDTE as a BigDecimal.
  */
  public BigDecimal getBigDecimalEWDDTE()
  {
    return fieldEWDDTE.getBigDecimal();
  }

  /**
  * Set field EWDOPE using a String value.
  */
  public void setEWDOPE(String newvalue)
  {
    fieldEWDOPE.setString(newvalue);
  }

  /**
  * Get value of field EWDOPE as a String.
  */
  public String getEWDOPE()
  {
    return fieldEWDOPE.getString();
  }

  /**
  * Set field EWDNA1 using a String value.
  */
  public void setEWDNA1(String newvalue)
  {
    fieldEWDNA1.setString(newvalue);
  }

  /**
  * Get value of field EWDNA1 as a String.
  */
  public String getEWDNA1()
  {
    return fieldEWDNA1.getString();
  }

  /**
  * Set field EWDFLG using a String value.
  */
  public void setEWDFLG(String newvalue)
  {
    fieldEWDFLG.setString(newvalue);
  }

  /**
  * Get value of field EWDFLG as a String.
  */
  public String getEWDFLG()
  {
    return fieldEWDFLG.getString();
  }

}
