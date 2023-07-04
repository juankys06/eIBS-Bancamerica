package datapro.eibs.beans;

import datapro.eibs.sockets.*;
import java.io.*;
import java.math.*;

import java.util.*;

/**
* Class generated by AS/400 CRTCLASS command from EWD0412DS physical file definition.
* 
* File level identifier is 1060223192616.
* Record format level identifier is 495B6617FA991.
*/

public class EWD0412DSMessage extends MessageRecord
{
  final static String fldnames[] = {
                                     "EWDSTY",
                                     "EWDTBL",
                                     "EWDTBK",
                                     "EWDFCY",
                                     "EWDTYP",
                                     "EWDDSC",
                                     "EWDOPE"
                                   };
  final static String tnames[] = {
                                   "EWDSTY",
                                   "EWDTBL",
                                   "EWDTBK",
                                   "EWDFCY",
                                   "EWDTYP",
                                   "EWDDSC",
                                   "EWDOPE"
                                 };
  final static String fid = "1060223192616";
  final static String rid = "495B6617FA991";
  final static String fmtname = "EWD0412DS";
  final int FIELDCOUNT = 7;
  private static Hashtable tlookup = new Hashtable();
  private CharacterField fieldEWDSTY = null;
  private CharacterField fieldEWDTBL = null;
  private CharacterField fieldEWDTBK = null;
  private CharacterField fieldEWDFCY = null;
  private CharacterField fieldEWDTYP = null;
  private CharacterField fieldEWDDSC = null;
  private CharacterField fieldEWDOPE = null;

  /**
  * Constructor for EWD0412DSMessage.
  */
  public EWD0412DSMessage()
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
    recordsize = 51;
    fileid = fid;
    recordid = rid;
    message = new byte[getByteLength()];
    formatname = fmtname;
    fieldnames = fldnames;
    tagnames = tnames;
    fields = new MessageField[FIELDCOUNT];

    fields[0] = fieldEWDSTY =
    new CharacterField(message, HEADERSIZE + 0, 4, "EWDSTY");
    fields[1] = fieldEWDTBL =
    new CharacterField(message, HEADERSIZE + 4, 2, "EWDTBL");
    fields[2] = fieldEWDTBK =
    new CharacterField(message, HEADERSIZE + 6, 2, "EWDTBK");
    fields[3] = fieldEWDFCY =
    new CharacterField(message, HEADERSIZE + 8, 3, "EWDFCY");
    fields[4] = fieldEWDTYP =
    new CharacterField(message, HEADERSIZE + 11, 4, "EWDTYP");
    fields[5] = fieldEWDDSC =
    new CharacterField(message, HEADERSIZE + 15, 35, "EWDDSC");
    fields[6] = fieldEWDOPE =
    new CharacterField(message, HEADERSIZE + 50, 1, "EWDOPE");

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
  * Set field EWDSTY using a String value.
  */
  public void setEWDSTY(String newvalue)
  {
    fieldEWDSTY.setString(newvalue);
  }

  /**
  * Get value of field EWDSTY as a String.
  */
  public String getEWDSTY()
  {
    return fieldEWDSTY.getString();
  }

  /**
  * Set field EWDTBL using a String value.
  */
  public void setEWDTBL(String newvalue)
  {
    fieldEWDTBL.setString(newvalue);
  }

  /**
  * Get value of field EWDTBL as a String.
  */
  public String getEWDTBL()
  {
    return fieldEWDTBL.getString();
  }

  /**
  * Set field EWDTBK using a String value.
  */
  public void setEWDTBK(String newvalue)
  {
    fieldEWDTBK.setString(newvalue);
  }

  /**
  * Get value of field EWDTBK as a String.
  */
  public String getEWDTBK()
  {
    return fieldEWDTBK.getString();
  }

  /**
  * Set field EWDFCY using a String value.
  */
  public void setEWDFCY(String newvalue)
  {
    fieldEWDFCY.setString(newvalue);
  }

  /**
  * Get value of field EWDFCY as a String.
  */
  public String getEWDFCY()
  {
    return fieldEWDFCY.getString();
  }

  /**
  * Set field EWDTYP using a String value.
  */
  public void setEWDTYP(String newvalue)
  {
    fieldEWDTYP.setString(newvalue);
  }

  /**
  * Get value of field EWDTYP as a String.
  */
  public String getEWDTYP()
  {
    return fieldEWDTYP.getString();
  }

  /**
  * Set field EWDDSC using a String value.
  */
  public void setEWDDSC(String newvalue)
  {
    fieldEWDDSC.setString(newvalue);
  }

  /**
  * Get value of field EWDDSC as a String.
  */
  public String getEWDDSC()
  {
    return fieldEWDDSC.getString();
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

}
