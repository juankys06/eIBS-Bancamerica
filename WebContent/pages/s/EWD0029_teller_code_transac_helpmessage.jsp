<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.0 Transitional//EN">
<html>
<head><title>Codigo de Transacciones</title>
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
//HttpSession session = request.getSession(true);
MessageRecord newmessage = null;
EWD0029DSMessage m = null;
OutputStream outstream = null;
//** field values
String	TableCode = null;
String  Description = null;
String	Type = null;
//**utility variables
String marker = null;
boolean close = false;
boolean firsttime = true;

out.println("<center><font><b>Busqueda de Codigos de Transaccion para Cajeros en el Sistema<b></font></font></center>");

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
		m = (EWD0029DSMessage)mc.getMessageRecord("EWD0029DS");//out.println("message title sent<br>");
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
				if (newmessage.getFormatName().equals("EWD0029DS")) {	
				m = (EWD0029DSMessage) newmessage;//out.println("message cast<br>");
				}	
			}catch(Exception e){
			break;
			}
			
		TableCode = m.getEWDTCD().trim();
		Description = m.getEWDITD().trim();
		Type = m.getEWDTYP().trim();
		try{
		marker = m.getEWDOPE().trim();
		}catch(Exception e){
		marker = "*";
		}

		
		if(TableCode.equals("0")){
		out.print("There were no search results");
		}else{

			if(marker.equals("*") ){
			out.println("</table>");
			break;
			}

		
			if(firsttime == true){
			out.println("<table  class=\"tableinfo\"width:95%\" ALIGN=CENTER>");
			out.println("<tr id=\"trdark\">");
			out.println("<th>Codigo de Transaccion</th>");
			out.println("<th>Descripcion</th>");
			out.println("<th>Tipo de Transaccion</th>");
			out.println("</tr>");
			firsttime = false;
			}

			out.println("<tr>");
			out.println("<td><a href=\"javascript:enter('" + TableCode + "')\">" + TableCode + "</a></td>");
			out.println("<td><a href=\"javascript:enter('" + TableCode + "')\">" + Description + "</a></td>");
			out.println("<td><a href=\"javascript:enter('" + TableCode + "')\">" + Type + "</a></td>");
			out.println("</tr>");		


		}// close TableCode evaluation
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