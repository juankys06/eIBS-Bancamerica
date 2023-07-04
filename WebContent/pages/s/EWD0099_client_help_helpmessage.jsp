<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.0 Transitional//EN">
<html>
<head>
<title>SubCuentas</title>
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
EWD0012DSMessage m = null;
OutputStream outstream = null;
//** search values
String BankNumber = null;
//** field values
String	Currency = null;
String ExchangeRate = null;
String	Description = null;
//**utility variables
String marker = null;
boolean morerecords = true;
boolean close = false;
boolean firsttime = true;
boolean noresults = true;

out.println("<center><font><b>Busqueda de SubCuentas en el Sistema<b></font></font></center>");

//************* pull search parameters ********
try{BankNumber = request.getParameter("BankNumber");}catch(Exception e){BankNumber = "";}
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
		m = (EWD0012DSMessage)mc.getMessageRecord("EWD0012DS");//out.println("message title sent<br>");
	 	m.setEWDSHR(BankNumber.toUpperCase());//out.println("BankNumber field set<br>");
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
				if (newmessage.getFormatName().equals("EWD0012DS")) {	
				m = (EWD0012DSMessage) newmessage;//out.println("message cast<br>");
				noresults = false;
				}	
			}catch(Exception e){
			morerecords = false;
			break;
			}
			
		Currency = m.getEWDCCY().trim();
		ExchangeRate = m.getEWDEXR().trim();
		Description = m.getEWDDSC().trim();
				
		try{
		marker = m.getEWDOPE().trim();
		}catch(Exception e){
		marker = "*";
		}
		
			if(marker.equals("*") ){
			out.println("</table>");
			break;
			}
			else{
		
			if(firsttime == true){
			out.println("<table  class=\"tableinfo\"style=\"width:95%\" ALIGN=CENTER>");
			out.println("<tr id=\"trdark\">");
			out.println("<th>Moneda</th>");
			out.println("<th>tasa de Cambio</th>");
			out.println("<th>Descripcion</th>");
			out.println("</tr>");
			firsttime = false;
			}

			out.println("<tr>");
			out.println("<td><a href=\"javascript:enter('" + Currency + "')\">" + Currency + "</a></td>");
			out.println("<td><a href=\"javascript:enter('" + Currency + "')\">" + ExchangeRate + "</a></td>");
			out.println("<td><a href=\"javascript:enter('" + Currency + "')\">" + Description + "</a></td>");
			out.println("</tr>");		

		}// close marker evaluation
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