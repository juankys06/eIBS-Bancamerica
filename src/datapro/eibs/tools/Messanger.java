package datapro.eibs.tools;

import java.io.IOException;
import datapro.eibs.sockets.*;


import datapro.eibs.beans.ELEERRMessage;
import datapro.eibs.beans.JBObjList;
import javax.servlet.*;
import javax.servlet.http.*;


public class Messanger {
	
	public static final int WITH_ERROR_MODE=0;
	public static final int NO_ERROR_MODE=1;
	
	MessageRecord inputMessage;
	MessageRecord outputMessage;
	ELEERRMessage msgError;
	boolean IsNotError=true;
	MessageContext mc;
	int responseMode = 0; // Envia primero mensaje de error
	
	public void setResponseMode(int responseMode){
		this.responseMode=responseMode;
	}
	
	public int getResponseMode(){ return this.responseMode;}

	
	
	/**
	 * Encapsula el envio del mensaje a traves del socket y despues de la
	 * ejecucion del programa devuelve un message record
	 * 
	 * @param req
	 * @param res
	 * @param mc
	 * @param msg
	 * @throws ServletException
	 * @throws IOException
	 */
 
	public void sendMessage(HttpServletRequest req, HttpServletResponse res)
			throws  MessangerException {

        this.IsNotError = true;
		try {
			// all the fields here
			java.util.Enumeration enu = inputMessage.fieldEnumeration();
			MessageField field = null;
			String value = null;
			while (enu.hasMoreElements()) {
				field = (MessageField) enu.nextElement();
				try {
					value = req.getParameter(field.getTag()).toUpperCase();
					if (value != null) {
						field.setString(value);
					}
				} catch (Exception e) {
				}
			}
			//System.out.println ( inputMessage.getField("H02OPECOD").getString()+" "+inputMessage.getField("H02PROGRM").getString());
            System.out.println(inputMessage.toString());
			inputMessage.send();
			inputMessage.destroy();
		}
		catch (Exception e) {
			e.printStackTrace();
			throw new MessangerException("No se ha establecido comunicacion con el mainframe");
		}

		// Receive Error Message
		try {
		  if(this.responseMode == 0){	
			outputMessage = mc.receiveMessage();
			if (outputMessage.getFormatName().equals("ELEERR")) {
				msgError = (ELEERRMessage) outputMessage;
				this.showERROR(msgError);
				IsNotError = msgError.getERRNUM().equals("0");
			} 
		  }	

		} catch (Exception e) {
			e.printStackTrace();
			// flexLog("Error: " + e);
			throw new MessangerException("No se obtuvo respuesta del mainframe");
		}


	}
	
	
	
	
	public Messanger(MessageContext mc, MessageRecord inputMessage){
		this.mc = mc;
		this.inputMessage=inputMessage;
	}




	public ELEERRMessage getMsgError() {
		return msgError;
	}




	public void setMsgError(ELEERRMessage msgError) {
		this.msgError = msgError;
	}




	public MessageRecord getRecord() throws  MessangerException {
		// Receive Data
		try {
			outputMessage = mc.receiveMessage();

			/*if (!outputMessage.getFormatName().equals(inputMessage.getFormatName())) 
			   IsNotError = false; */

		} catch (Exception e) {
			e.printStackTrace();
			// flexLog("Error: " + e);
			throw new MessangerException("No se obtuvo el registro");
		}

		return outputMessage;
	}


	public JBObjList getList() throws MessangerException {
		JBObjList beanList=null;
		try {
			outputMessage = mc.receiveMessage();

			if (outputMessage.getFormatName().equals(inputMessage.getFormatName())) {

				try {
					beanList = new JBObjList();
					//flexLog("ECD0009DS Message Received");
				} catch (Exception ex) {
					IsNotError = false;
				}

				boolean firstTime = true;
				String marker = "";
				String myFlag = "";
				StringBuffer myRow = null;
				String chk = "";

				while (true) {

					//msg = (ECD0009DSMessage) newmessage;
                    try{
                      marker = outputMessage.getFieldString("H01FLGMAS");
                    }
                    catch (Exception e){
                    	try {
							marker = outputMessage.getFieldString("H02FLGMAS"); 
                    	}
                    	catch (Exception ex){
							marker = outputMessage.getFieldString("H03FLGMAS"); 
                    	}
                    }
					outputMessage.setHandler(null);

					if (marker.equals("*")) {
						beanList.setShowNext(false);
						break;
					} else {
						 
					/*
					 * if (firstTime) { firstTime = false; beanList.setFirstRec(
					 * Integer.parseInt(msg.getH01FLGMAS()));//ECDREC chk =
					 * "checked"; } else { chk = ""; }
					 * 
					 */	
						beanList.addRow(outputMessage);

						if (marker.equals("+")) {
							beanList.setShowNext(true);
							break;
						}
					}
					outputMessage = mc.receiveMessage();
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			throw new MessangerException ("No pudo obtener la lista");
			//flexLog("Error: " + e);
			//res.sendRedirect(super.srctx +LangPath + super.sckNotRespondPage);
		}
	   finally {
		
	   }
	   return beanList;
		
	}




	public boolean isNotError() {
		return IsNotError;
	}




	public MessageRecord getOutputMessage() {
		return outputMessage;
	}

	protected void showERROR(ELEERRMessage m)
	{
			
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
