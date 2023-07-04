<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.0 Transitional//EN">

<html>
<head>
<TITLE>Messages</TITLE>
<META HTTP-EQUIV="Content-Type" CONTENT="text/html; charset=ISO-8859-1">
<META name="GENERATOR" content="IBM WebSphere Page Designer V3.5.2 for Windows">
<META http-equiv="Content-Style-Type" content="text/css">
<link Href="<%=request.getContextPath()%>/pages/style.css" rel="stylesheet">

<script language="Javascript1.1" src="<%=request.getContextPath()%>/pages/s/javascripts/eIBS.jsp"> </SCRIPT>
</head>

<BODY BGCOLOR="#FFFFFF">
<FORM Method="post" Action="">
<INPUT TYPE=HIDDEN NAME="totalRow" VALUE="0">
<table  id="mainTable" class="tableinfo">
  <tr> 
    <td nowrap>
     <%		
     		int messlength = 80;
     		int lines = 0;

     		String message = request.getParameter("MESSAGE");
     		lines = Math.abs(message.length() / messlength) + 1;
			String[] messageArray = new String[lines];
     %>     
	<div  id="dataDiv1" align="center">    
     <table id="dataTable" > 
     <%		
			for (int i = 0; i < messageArray.length; i++){
     %> 

    <tr> 
      		<td width="100%" align=LEFT nowrap> 
     <%
     		int messend = i * messlength + messlength;
     		if (messend > message.length()) messend = message.length();
     		messageArray[i] = message.substring(i * messlength, messend);
			out.println(messageArray[i]);
   	 %> 
   	 		</td>
    </tr>
	 <% 
      		}
  	 %>     
  	</table>
	</div>
    </td>
  </tr>
</table>
<SCRIPT language="JavaScript">
     document.forms[0].totalRow.value="<%= lines %>";
     divResize();
     window.onresize=divResize;
     //window.resizeTo(<%= messlength * 15 %> , <%= lines * 15 * 4 %> );
     
</SCRIPT>
</FORM>
</BODY>
</HTML>
