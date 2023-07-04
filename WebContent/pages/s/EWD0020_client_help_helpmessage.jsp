<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.0 Transitional//EN">
<html>
<head><title>Clases</title>
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
EWD0020DSMessage m = null;
OutputStream outstream = null;
//** field values
String ClassNum = null;
String Name = null;
String Group = null;
//**utility variables
String marker = null;
boolean firsttime = true;

out.println("<center><font><b>Busqueda de Clases de Activos en el Sistema<b></font></font></center>");

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
		m = (EWD0020DSMessage)mc.getMessageRecord("EWD0020DS");//out.println("message title sent<br>");
		m.send();//out.println("message sent<br>");
	 	m.destroy();//out.println("message destroyed<br>");
	 	
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
				if (newmessage.getFormatName().equals("EWD0020DS")) {	
				m = (EWD0020DSMessage) newmessage;//out.println("message cast<br>");
				}	
			}catch(Exception e){
			break;
			}
			
		ClassNum = m.getEWDNUM().trim();
		Name = m.getEWDNME().trim();if(Name.length() == 0){Name="&nbsp;";}
		Group = m.getEWDGRP().trim();if(Group.length() == 0){Group="&nbsp;";}
				
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
			out.println("<table  class=\"tableinfo\" style=\"width:95%\" ALIGN=CENTER>");
			out.println("<tr id=\"trdark\">");
			out.println("<th>Clase</th>");
			out.println("<th>Nombre</th>");
			out.println("<th>Grupo</th>");
			out.println("</tr>");
			firsttime = false;
			}

			out.println("<tr>");
			out.println("<td><a href=\"javascript:enter('" + ClassNum + "')\">" + ClassNum + "</a></td>");
			out.println("<td><a href=\"javascript:enter('" + ClassNum + "')\">" + Name + "</a></td>");
			out.println("<td><a href=\"javascript:enter('" + ClassNum + "')\">" + Group + "</a></td>");
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