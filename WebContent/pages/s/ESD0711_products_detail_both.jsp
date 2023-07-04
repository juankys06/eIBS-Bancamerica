<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.0 Transitional//EN">

<HTML>
<HEAD>
<META HTTP-EQUIV="Content-Type" CONTENT="text/html; charset=ISO-8859-1">
<META name="GENERATOR" content="IBM WebSphere Page Designer V3.5.2 for Windows">
<META http-equiv="Content-Style-Type" content="text/css">
<LINK HREF="<%=request.getContextPath()%>/pages/style.css" REL="stylesheet">
<TITLE>Ayuda</TITLE>
<script language="javascript">
</script>
</HEAD>
<BODY>

<table width=100% align=center border=1>


<%@ page import = "java.io.*,java.net.*,datapro.eibs.sockets.*,datapro.eibs.beans.*,datapro.eibs.master.*,java.math.*" %>

<%
  	// Parameter entries
  	String appCode = request.getParameter("appcode");
  	String typeCode = request.getParameter("typecode");
  	String title = request.getParameter("title");
  	boolean displayTypes;
  	displayTypes=typeCode.equals("") ? true : false;

	// out.println("<tr><td colspan=5>" +  appCode + "&nbsp;" + typeCode + "&nbsp;" + "</td></tr>");
  	
  	if ( displayTypes ) {
			out.println("<tr><TH align=left colspan=5 >" +  title + "</TH></tr>");			   
			out.println("<tr><td colspan=5>&nbsp;</td></tr>");
			out.println("<tr>");
			out.println("<td align=center width=10> </td>");
			out.println("<td align=center width=40>Tipo</td>");
			out.println("<td align=center width=40>Moneda</td>");
			out.println("<td align=center width=80>Nombre de Mercadeo</td>");
			out.println("<td align=center>Descripción</td>");
			out.println("</tr>");
   }
   else {
			out.println("<tr><TH align=left colspan=5 >" + typeCode + " - " +  title + "</TH></tr>");			   
			out.println("<tr><td colspan=5>&nbsp;</td></tr>");
			out.println("<tr>");
			out.println("<td align=center width=10> </td>");
			out.println("<td align=center width=40>Producto</td>");
			out.println("<td align=center width=40>Moneda</td>");
			out.println("<td align=center width=80>Nombre de Mercadeo</td>");
			out.println("<td align=center>Descripción</td>");
			out.println("</tr>");
   }

	HttpSession session;
  	Socket s = null;
  	MessageContext mc = null;

  	MessageRecord newmessage = null;
  	ESD071102Message msgHelp = null;
	ESS0030DSMessage msgUser = null;
   
	session = (HttpSession)request.getSession(false);
	
	if (session != null) {

					msgUser = (datapro.eibs.beans.ESS0030DSMessage)session.getValue("currUser");
										
              	try
               	{
							s = new Socket(JSEIBSProp.getHostIP(), JSEIBSProp.getIniSocket() + 1);
               	  	mc = new MessageContext(new DataInputStream(new BufferedInputStream(s.getInputStream())),
               					      	    new DataOutputStream(new BufferedOutputStream(s.getOutputStream())),
               							    "datapro.eibs.beans");
               	}
               	catch (Exception e)
               	{
               	  out.println("Connection error " + e);
               	  System.exit(1);
               	}

               	// Send Request
               	try
               	{
               		msgHelp = (ESD071102Message)mc.getMessageRecord("ESD071102");
            		 	msgHelp.setE02USERID(msgUser.getH01USR());
            		 	msgHelp.setE02SELACD(appCode); 
            		 	msgHelp.setE02SELTYP(typeCode); 
               	 	msgHelp.send();	
               	 	msgHelp.destroy();
               	}		
               	catch (Exception e)
               	{
               	  e.printStackTrace();
               	  out.println("Send Client Header Information error " + e);
               	}

               	// Receive Help
               	try
               	{
                             
                  	while(true) {
                  	   newmessage = mc.receiveMessage();
                  	  
                  	   if (newmessage.getFormatName().equals("ESD071102")) {

                              msgHelp =  (ESD071102Message)newmessage;
                             
           						   if ( msgHelp.getE02OPERAC().equals("*") ) {
   										break;
										}

           							out.println("<tr>");
           							if ( displayTypes ) {
           								out.println("<td width=10><input type=\"radio\" name=\"CODE\" value=\"" + msgHelp.getE02APCTYP().trim() + "\"></td>"); // radio button
           								out.println("<td width=30>" + msgHelp.getE02APCTYP().trim() + "</td>"); // Type Code
                              }
                              else {
           								out.println("<td width=10><input type=\"radio\" name=\"CODE\" value=\"" + msgHelp.getE02APCCDE().trim() + "\"></td>"); // radio button
           								out.println("<td width=30>" + msgHelp.getE02APCCDE().trim() + "</td>"); // Prod Code
                              }
           							out.println("<td width=30>" + msgHelp.getE02CURREN().trim() + "</td>"); // Currency Code
           							out.println("<td width=80>" + msgHelp.getE02NICNME().trim() + "</td>"); // Nick Name
           							out.println("<td>"  + msgHelp.getE02DESCRI().trim() + "</td>"); // Description
           							out.println("</tr>");
           						   
                 		   }
                  	   else {
                  		     out.println("Message " + newmessage.getFormatName() + " received.");
                  		     break;
                  		}
               		}
               	}
               	catch (Exception e)
               	{
               	  out.println("Read error " + e);
               	}	

                	try
                	{
                	  s.close();
                	}
                	catch (Exception e) {
                	  out.println("Error closing socket connection " + e);
                	}
	}
                	
%>
</table>
</BODY>
</HTML>
