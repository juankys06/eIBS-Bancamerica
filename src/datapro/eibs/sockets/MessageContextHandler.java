package datapro.eibs.sockets;

import java.io.IOException;
import java.util.Calendar;

import javax.servlet.http.HttpServletRequest;

import datapro.eibs.beans.ELEERRMessage;
import datapro.eibs.beans.JBObjList;

/**
* The MessageContext class can contain information about the connection
* to be used for incoming and outgoing messages, or about the package that
* contains the message classes, or both.
*/
public class MessageContextHandler {
  
	private MessageContext mc = null;

  /**
  * Construct a MessageContextHandler that contains information about a connection
  * Message objects.
  *
  * @param mc the MessageContext object to be used for receiving or sending messages.
  */
	public MessageContextHandler(MessageContext msgContext)
		throws ClassNotFoundException, IllegalAccessException, InstantiationException {
		mc = msgContext;
	}
  
  /**
  * Receives an error message from the current input stream.
  *
  * @return the ELEERRMessage class.
  *
  * @exception IOException if there is any problem receiving the message.
  */
	public ELEERRMessage receiveErrorMessage()
		throws IOException, ClassCastException {
		MessageRecord newmessage = null;
		ELEERRMessage result = null;
		try {
			newmessage = mc.receiveMessage();
			System.out.println(newmessage.getFormatName() + "Message received.");
	
			result = (ELEERRMessage) newmessage;
			showERROR(result);
	
		} catch (ClassCastException e) {
			throw new ClassCastException("Message is not ELEERRMessage");
		} catch (IOException e) {
			throw new RuntimeException("Socket Communication Error");
		}
		return result;
	}
  
	public MessageRecord initMessage(
		String messageName,
		String user,
		String opCode) {
		MessageRecord message = null;
		try {
			message = mc.getMessageRecord(messageName);
			java.util.Enumeration enu = message.fieldEnumeration();
			MessageField field = null;
			String value = null;
			while (enu.hasMoreElements()) {
				field = (MessageField) enu.nextElement();
	
				if (field.getTag().indexOf("USERID") >= 0) {
					field.setString(user);
				} else if (field.getTag().indexOf("PROGRM") >= 0) {
					field.setString(getProgramName(message.getFormatName()));
				} else if (field.getTag().indexOf("TIMSYS") >= 0) {
					field.setString(getTimeStamp());
				} else if (opCode != "" && field.getTag().indexOf("OPECOD") >= 0) {
					field.setString(opCode);
				}
			}
		} catch (Exception e) {
			throw new RuntimeException("Error initializing " + messageName);
		}
		return message;
	}
  
	public MessageRecord initMessage(
		MessageRecord message,
		String user,
		String opCode) {
		try {
			java.util.Enumeration enu = message.fieldEnumeration();
			MessageField field = null;
			String value = null;
			while (enu.hasMoreElements()) {
				field = (MessageField) enu.nextElement();
	
				if (field.getTag().indexOf("USERID") >= 0) {
					field.setString(user);
				} else if (field.getTag().indexOf("PROGRM") >= 0) {
					field.setString(getProgramName(message.getFormatName()));
				} else if (field.getTag().indexOf("TIMSYS") >= 0) {
					field.setString(getTimeStamp());
				} else if (opCode != "" && field.getTag().indexOf("OPECOD") >= 0) {
					field.setString(opCode);
				}
			}
		} catch (Exception e) {
			throw new RuntimeException("Error initializing " + message.formatname);
		}
		return message;
	}
  
  /**
  * Receives a message from the current input stream.
  *
  * @return the MessageRecord class.
  *
  * @exception IOException if there is any problem receiving the message.
  */
	public MessageRecord receiveMessage() throws IOException {
		MessageRecord result = mc.receiveMessage();
		System.out.println(result.getFormatName() + "Message received.");
		return result;
	}
  
  /**
  * Receives a message from the current input stream.
  *
  * @return the MessageRecord class.
  *
  * @exception IOException if there is any problem receiving the message.
  * 
  */
	public MessageRecord receiveMessage(String formatname)
		throws IOException, ClassCastException {
		MessageRecord result = mc.receiveMessage();
		System.out.println(result.getFormatName() + "Message received.");
	
		if (!result.getFormatName().equals(formatname)) {
			throw new ClassCastException("Message is not " + formatname);
		}
		return result;
	}
  
  /**
  * Receive the a message list from the default
  * DataInputStream for this MessageContext.
  *
  * @return a JBObjList with MessageRecord objects as the objects List.
  *
  * @exception IOException if there is an error reading a message object.
  */
	public JBObjList receiveMessageList(String controlField) throws IOException {
		return receiveMessageList(null, controlField, null, 0);
	}
  
  /**
  * Receive the a message list from the default
  * DataInputStream for this MessageContext.
  *
  * @return a JBObjList with MessageRecord objects as the objects List.
  *
  * @exception IOException if there is an error reading a message object.
  */
	public JBObjList receiveMessageList(String formatname, String controlField)
		throws IOException {
		return receiveMessageList(formatname, controlField, null, 0);
	}
  
	/**
	* Receive the a message list from the default
	* DataInputStream for this MessageContext.
	*
	* @return a JBObjList with MessageRecord objects as the objects List.
	*
	* @exception IOException if there is an error reading a message object.
	*/
	  public JBObjList receiveMessageList(String controlField, int size)
		  throws IOException {
		  return receiveMessageList(null, controlField, null, size);
	  }
  
	/**
	* Receive the a message list from the default
	* DataInputStream for this MessageContext.
	*
	* @return a JBObjList with MessageRecord objects as the objects List.
	*
	* @exception IOException if there is an error reading a message object.
	*/
	  public JBObjList receiveMessageList(String controlField, String positionField, int size)
		  throws IOException {
		  return receiveMessageList(null, controlField, positionField, size);
	  }
  
  /**
  * Receive the a message list from the default
  * DataInputStream for the current MessageContext.
  *
  * @return a JBObjList with MessageRecord objects as the objects List.
  *
  * @exception IOException if there is an error reading a message object.
  */
	private JBObjList receiveMessageList(
		String formatname,
		String controlField,
		String positionField,
		int size)
		throws IOException, ClassCastException {
		JBObjList beanList = null;
		MessageRecord newmessage = null;
	
		newmessage = mc.receiveMessage();
		if (formatname == null || formatname.equals("")) {
			formatname = newmessage.getFormatName();
		}
	
		beanList = new JBObjList();
		boolean firstTime = true;
		String marker = "";
		int counter = size;
	
		while ((size == 0 ? true : counter-- > 0 )) {
			System.out.println(newmessage.getFormatName() + "Message received.");
			if (newmessage.getFormatName().equals(formatname)) {
				marker = newmessage.getField(controlField).getString();
	
				if (firstTime) {
					firstTime = false;
					if (positionField != null && !positionField.equals("")) {
						int pos = 0;
						try {
							pos =
								Integer.parseInt(
									newmessage.getField(positionField).getString());
						} catch (Exception e) {
						}
						beanList.setFirstRec(pos);
					}
				}
				if (marker.equals("*")) {
					beanList.setShowNext(false);
					break;
				} else {
					beanList.addRow(newmessage);
					if (marker.equals("+")) {
						beanList.setShowNext(true);
						break;
					}
				}
				newmessage = mc.receiveMessage();
	
			} else {
				throw new ClassCastException("Message is not " + formatname);
			}
		}
	
		return beanList;
	}
  
	/**
	* Receive the a message list from the default
	* DataInputStream for this MessageContext.
	*
	* @return a JBObjList with MessageRecord objects as the objects List.
	* @return a ELEERRMessage with MessageRecord objects if error and null
	* 
	* @exception IOException if there is an error reading a message object.
	*/
	public JBObjList receiveMessageList(String formatname, String controlField, ELEERRMessage error)
		  throws IOException {
		  return receiveMessageList(formatname, controlField, null, 0, error);
	  }
  
	public JBObjList receiveMessageList(String formatname, String controlField, String positionField, ELEERRMessage error)
		  throws IOException {
		  return receiveMessageList(formatname, controlField, positionField, 0, error);
	  }
  
	private JBObjList receiveMessageList(
		String formatname,
		String controlField,
		String positionField,
		int size,
		ELEERRMessage error)
		throws IOException, ClassCastException {
		JBObjList beanList = null;
		MessageRecord newmessage = null;
	
		newmessage = mc.receiveMessage();

		if (formatname == null || formatname.equals("")) {
			formatname = newmessage.getFormatName();
		}
		
		if (newmessage.getFormatName().equals("ELEERR")) {
			error.setMessageBytes(newmessage.message);
		} else {
			beanList = new JBObjList();
			boolean firstTime = true;
			String marker = "";
			int counter = size;
	
			while ((size == 0 ? true : counter-- > 0 )) {
				System.out.println(newmessage.getFormatName() + "Message received.");
				if (newmessage.getFormatName().equals(formatname)) {
					marker = newmessage.getField(controlField).getString();
	
					if (firstTime) {
						firstTime = false;
						if (positionField != null && !positionField.equals("")) {
							int pos = 0;
							try {
								pos =
									Integer.parseInt(
										newmessage.getField(positionField).getString());
							} catch (Exception e) {
							}
							beanList.setFirstRec(pos);
						}
					}
					if (marker.equals("*")) {
						beanList.setShowNext(false);
						break;
					} else {
						beanList.addRow(newmessage);
						if (marker.equals("+")) {
							beanList.setShowNext(true);
							break;
						}
					}
					newmessage = mc.receiveMessage();
	
				} else {
					throw new ClassCastException("Message is not " + formatname);
				}
			}	
		}
		return beanList;
	}
  
 /**
   * Sends a list of MessageRecord subclasses
   */
	public void sendMessageList(
		JBObjList messageList,
		String formatname,
		String controlField,
		String user)
		throws
			IOException,
			ClassNotFoundException,
			InstantiationException,
			IllegalAccessException {
	
		MessageRecord message = null;
	
		messageList.initRow();
		while (messageList.getNextRow()) {
			message = (MessageRecord) messageList.getRecord();
			message.getField("H01USERID").setString(user);
			message.getField("H01PROGRM").setString(getProgramName(formatname));
			message.getField("H01TIMSYS").setString(getTimeStamp());
			//message.getField(controlField).setString("+");
			mc.sendMessage(message);
			System.out.println(message.getFormatName() + "Message Sent.");
		}
		message = mc.getMessageRecord(formatname);
		message.getField("H01USERID").setString(user);
		message.getField("H01PROGRM").setString(getProgramName(formatname));
		message.getField("H01TIMSYS").setString(getTimeStamp());
		message.getField(controlField).setString("*");
		mc.sendMessage(message);
		message.destroy();
		System.out.println(message.getFormatName() + "Message Sent.");
	}

  /**
  * Get the MessageContext for this handler.
  *
  * @return the MessageContext for this context
  */
	public MessageContext getMessageContext() {
		return mc;
	}
  
	public void setFieldsFromPage(HttpServletRequest request, MessageRecord message) throws Exception {
		// all the fields here
		java.util.Enumeration enu = message.fieldEnumeration();
		MessageField field = null;
		String value = "";
		String name = "";
		while (enu.hasMoreElements()) {
			try {
				field = (MessageField) enu.nextElement();
				name = field.getTag();
				try {
					value = request.getParameter(name).toUpperCase();
				} catch (Exception e) {
					value = null;
				}
				if (value != null) {
					field.setString(value);
				}
			} catch (Exception e) {
				throw new Exception("Exception get value from "  + name + " " + e.toString() + e.getMessage());
			}
		}
	}

  /**
  * Send a message over the current output stream.
  *
  * @param message an instance of a MessageRecord subclass.
  *
  * @exception IOException if there is any problem sending the message.
  */
	public void sendMessage(MessageRecord message) throws IOException {
		mc.sendMessage(message);
		message.destroy();
		System.out.println(message.getFormatName() + "Message Sent.");
	}
  
  
  /**
   * This method returns the Program Name used by the Header Message
   * in the sockets protocol.
   *
   * @return the formatname of the MessageRecord class, as a String.
   *
   */
	public final static String getProgramName(String formatname) {
		String result = "";
		if (formatname != null && formatname.length() > 7) {
			result = formatname.substring(0, 7);
		} else if (formatname != null) {
			result = formatname;
		}
		return result;
	}
  
  /**
   * This method returns the Time Stamp String used by the Header Message
   * in the sockets protocol.
   *
   * @return TimeStamp, as a String, in this very moment.
   *
   */
	public final static String getTimeStamp() {
	
		Calendar calendar = Calendar.getInstance();
		String year = "" + calendar.get(Calendar.YEAR);
		String month = "" + (calendar.get(Calendar.MONTH) + 1);
		String day = "" + calendar.get(Calendar.DAY_OF_MONTH);
		String hour = "" + calendar.get(Calendar.HOUR_OF_DAY);
		String minute = "" + calendar.get(Calendar.MINUTE);
		String second = "" + calendar.get(Calendar.SECOND);
	
		year = year.substring(2);
		month = month.length() == 1 ? "0" + month : month;
		day = day.length() == 1 ? "0" + day : day;
		hour = hour.length() == 1 ? "0" + hour : hour;
		minute = minute.length() == 1 ? "0" + minute : minute;
		second = second.length() == 1 ? "0" + second : second;
	
		return (year + month + day + hour + minute + second);
	
	}
  
	private void showERROR(ELEERRMessage m) {
		System.out.println("ERROR received.");
		System.out.println("ERROR number:" + m.getERRNUM());
		System.out.println("ERR001 = " + m.getERNU01() + " desc: " + m.getERDS01());
		System.out.println("ERR002 = " + m.getERNU02() + " desc: " + m.getERDS02());
		System.out.println("ERR003 = " + m.getERNU03() + " desc: " + m.getERDS03());
		System.out.println("ERR004 = " + m.getERNU04() + " desc: " + m.getERDS04());
		System.out.println("ERR005 = " + m.getERNU05() + " desc: " + m.getERDS05());
		System.out.println("ERR006 = " + m.getERNU06() + " desc: " + m.getERDS06());
		System.out.println("ERR007 = " + m.getERNU07() + " desc: " + m.getERDS07());
		System.out.println("ERR008 = " + m.getERNU08() + " desc: " + m.getERDS08());
		System.out.println("ERR009 = " + m.getERNU09() + " desc: " + m.getERDS09());
		System.out.println("ERR010 = " + m.getERNU10() + " desc: " + m.getERDS10());
	}
  
}
