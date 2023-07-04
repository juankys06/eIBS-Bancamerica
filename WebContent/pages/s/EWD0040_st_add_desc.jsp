<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.0 Transitional//EN">
<html>
<head><title>Account Statement Description</title>
<META HTTP-EQUIV="Pragma" CONTENT="No-cache">
<META name="GENERATOR" content="IBM WebSphere Page Designer V3.5.2 for Windows">
<META http-equiv="Content-Style-Type" content="text/css">
<link Href="<%=request.getContextPath()%>/pages/style.css" rel="stylesheet">
<%@ page import = "java.io.*,java.net.*,datapro.eibs.sockets.*,datapro.eibs.beans.*,datapro.eibs.master.*" %>
</head>
<body>
<SCRIPT language="JavaScript">
	setTimeout("top.close()", <%= datapro.eibs.master.JSEIBSProp.getPopUpTimeOut() %>)
</SCRIPT>
<%
Socket s = null;
MessageContext mc = null;
MessageRecord newmessage = null;
EWD0040DSMessage m = null;
OutputStream outstream = null;

//** field values
String Line = null;
String AppCon = null;
String Desc = null;
String Dte = null;
String TC = null;

//**utility variables
String marker = null;
boolean firsttime = true;

 
//** pull search parameters ****
try{AppCon = request.getParameter("AppCode");} catch(Exception e){AppCon="0";}
try{Desc = request.getParameter("Desc");} catch(Exception e){Desc="";}
try{Dte = request.getParameter("Dte");} catch(Exception e){AppCon="";}
try{TC = request.getParameter("TC");} catch(Exception e){TC="";}
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
		m = (EWD0040DSMessage)mc.getMessageRecord("EWD0040DS");//out.println("message title sent<br>");
		m.setEWDDRR(AppCon);//out.println("CustNum field set<br>");
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
				if (newmessage.getFormatName().equals("EWD0040DS")) {	
				m = (EWD0040DSMessage) newmessage;//out.println("message cast<br>");
				}	
			}catch(Exception e){
			break;
			}
			
		Line = m.getEWDNAR().trim();

		
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
			out.println("<table  class=\"tableinfo\">");
			out.println("<tr id=\"trdark\">");
			out.println("<td>" + Dte + " </td>");
			out.println("<td>" + TC + " </td>");
			out.println("</tr>");
			out.println("<tr>");
			out.println("<td>" + Desc+ " </td>");
			out.println("</tr>");
			firsttime = false;
			}

			out.println("<tr>");
			out.println("<td>" + Line + " </td>");
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