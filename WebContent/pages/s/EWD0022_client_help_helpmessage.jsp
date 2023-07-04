<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.0 Transitional//EN">
<html>
<head><title>L/C Document Types</title>
<META HTTP-EQUIV="Pragma" CONTENT="No-cache">
<META name="GENERATOR" content="IBM WebSphere Page Designer V3.5.2 for Windows">
<META http-equiv="Content-Style-Type" content="text/css">
<link Href="<%=request.getContextPath()%>/pages/style.css" rel="stylesheet">

<%@ page import = "java.io.*,java.net.*,datapro.eibs.sockets.*,datapro.eibs.beans.*,datapro.eibs.master.*" %>

<SCRIPT language="JavaScript">
	setTimeout("top.close()", <%= datapro.eibs.master.JSEIBSProp.getPopUpTimeOut() %>)
</SCRIPT>
<script language="javascript">
//<!-- Hide from old browsers
function enter(code) {
var formLength= top.opener.document.forms[0].elements.length;
for(n=0;n<formLength;n++){
var elementName= top.opener.document.forms[0].elements[n].name;
	if(elementName == top.opener.fieldName){
  		top.opener.document.forms[0].elements[n].value = code;
  		break;
	}
 }
top.close();
 }
//-->
</script>
</head>
<body>
<%
Socket s = null;
MessageContext mc = null;
MessageRecord newmessage = null;
EWD0022DSMessage m = null;
OutputStream outstream = null;
//** search values
String Level = null;
//** field values
String DocuCode = null;
String	TextNum = null;
String Description = null;
//**utility variables
String marker = null;
boolean close = false;
boolean firsttime = true;

out.println("<center><font><b>Busqueda de Tipos de Documentos para Cartas de Credito en el Sistema<b></font></font></center>");

//************* pull search parameters ********
try{Level = request.getParameter("Level");}catch(Exception e){Level = "0";}
//***************************************************

//*************open the connection*************
	try	{
		s = new Socket(JSEIBSProp.getHostIP(), JSEIBSProp.getIniSocket() + 1);
	  	mc = new MessageContext(new DataInputStream(new BufferedInputStream(s.getInputStream())),
					      	    new DataOutputStream(new BufferedOutputStream(s.getOutputStream())), "datapro.eibs.beans");
				      	    
	}catch (Exception e)	{
	  out.println("Connection error " + e);
	}
//***************************************************

//********************** send initial data******
	try	{
		m = (EWD0022DSMessage)mc.getMessageRecord("EWD0022DS");//out.println("message title sent<br>");
	 	m.setEWDSLV(Level.toUpperCase());//out.println("Level field set<br>");
	 	m.send();//out.println("message sent<br>");
	 	m.destroy();//out.println("mesage destroyed<br>");
	 	
	}catch (Exception e)	{
	  e.printStackTrace();
	  out.println("Send Client Header Information error " + e);
	}
//**************************************************


//*************receive message************

	try	{
		
		while(true) {	
			try{//out.println("message requested<br>");
			newmessage = mc.receiveMessage();//out.println("message received<br>");
				if (newmessage.getFormatName().equals("EWD0022DS")) {	
				m = (EWD0022DSMessage) newmessage;//out.println("message cast<br>");
				}	
			}catch(Exception e){
			break;
			}
			
		DocuCode = m.getEWDCDC().trim();if(DocuCode.length() == 0){DocuCode = "&nbsp;";}
		TextNum = m.getEWDNTX().trim();if(TextNum.length() == 0){TextNum = "&nbsp;";}
		Description = m.getEWDDTS().trim();if(Description.length() == 0){Description = "&nbsp;";}
				
		try{
		marker = m.getEWDOPE().trim();
		}catch(Exception e){
		marker = "*";
		}
		
			if(marker.equals("*") ){
			out.println("</table>");
			break;
			}		
		
			if(firsttime == true){
			out.println("<table  class=\"tableinfo\"style=\"width:95%\" ALIGN=CENTER>");
			out.println("<tr id=\"trdark\">");
			out.println("<th>Codigo de Documento</th>");
			out.println("<th>Numero de Texto</th>");
			out.println("<th>Descripcion</th>");
			out.println("</tr>");
			firsttime = false;
			}

			out.println("<tr>");
			out.println("<td><a href=\"javascript:enter('" + DocuCode + "')\">" + DocuCode + "</a></td>");
			out.println("<td><a href=\"javascript:enter('" + DocuCode + "')\">" + TextNum + "</a></td>");
			out.println("<td><a href=\"javascript:enter('" + DocuCode + "')\">" + Description + "</a></td>");
			out.println("</tr>");		


		
	}// close while loop
		

//************* close the connection************
	}catch (Exception e)	{
	out.println("Read error " + e);
	}	
	
	try	{
	  s.close();
	}catch (Exception e) {
	  out.println("Error closing socket connection " + e);
	}
	


%>
</body>
</html>