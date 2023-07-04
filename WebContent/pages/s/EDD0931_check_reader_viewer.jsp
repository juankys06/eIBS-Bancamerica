<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.0 Transitional//EN">
<HTML>
<HEAD>
<META name="GENERATOR" content="IBM WebSphere Page Designer V3.5.2 for Windows">
<META http-equiv="Content-Style-Type" content="text/css">
<TITLE>Check Reader Tool</TITLE>

<LINK href="<%=request.getContextPath()%>/pages/style.css" rel="stylesheet" type="text/css">

<script language="Javascript1.1" src="<%=request.getContextPath()%>/pages/s/javascripts/eIBS.jsp"> </SCRIPT>
<jsp:useBean id= "error" class= "datapro.eibs.beans.ELEERRMessage"  scope="session" />

<SCRIPT LANGUAGE="JavaScript">

function loadScan(uid) {
	var pagename = "<%=request.getContextPath()%>/servlet/datapro.eibs.tools.JSEDD0931?SCREEN=2&PROCESS="+uid;
	CenterWindow(pagename, 620, 500, 2);
}


function exit() {
	if ( window.name !="main" ) top.close(); 
	else window.location.href = "<%=request.getContextPath()%>/pages/background.jsp";
}

function refresh() {
   	if (document.getElementById('UID').value != "0") {
   		document.forms[0].submit();  
   	}
}


 setTimeout("refresh();", 1000);
 
</SCRIPT>
</HEAD>

<% 		
	String action = (request.getParameter("ACTION") == null) ? "" : request.getParameter("ACTION");
	String process = (request.getParameter("PROCESS") == null) ? "" : request.getParameter("PROCESS");
	String check = (request.getParameter("CHECK") == null || request.getParameter("CHECK").equals("")) ? "0" : request.getParameter("CHECK");
%>
<BODY>

<h3 align="center">Camara Entrante
	<img src="<%=request.getContextPath()%>/images/e_ibs.gif" align="left" name="EIBS_GIF" ALT="check_reader_viewer, EDD0931" ></h3>
<hr size="4">

 <form method="post" action="<%=request.getContextPath()%>/servlet/datapro.eibs.tools.JSEDD0931">
	<INPUT TYPE=HIDDEN NAME="SCREEN" VALUE="3">
	<INPUT TYPE=HIDDEN ID="UID" NAME="PROCESS" VALUE="<%= process %>">
<% 
		if (action.equals("SCAN")) {
%>		
	       	<SCRIPT Language="Javascript">
		   		loadScan('<%= process %>');
	       	</SCRIPT>
<%	       	
		} else if (error != null && !error.getERRNUM().equals("0")) {
%>
		  <table class="tbenter" align="center" height=50% width="100%">
		  	<tr> 
		      <td align="right" width="30%"><font color="red" size="3">Error: <%= error.getERNU01() %>&nbsp;&nbsp;&nbsp;</font></td>
		      <td align="left" width="60%"><font color="red" size="3"><%= error.getERDS01() %></font></td>
		    </tr>
		  </table>
			<TABLE class="tbenter" width="100%">
		    	<TR> 
		      		<TD ALIGN=CENTER  class=TDBKG> <a href="javascript:exit()" ><b>Salir</b></a></TD>
		    	</TR>
		  	</TABLE>

<%
		} else {
%>
		  <table class=tbenter height=50%>
		  	<tr > 
		      <td><h3 style="text-align=center">Total Cheques Procesados: <%= check %></h3></td>
		    </tr>
		  </table>
			<TABLE class="tbenter" width="100%">
		    	<TR> 
		      		<TD ALIGN=CENTER  class=TDBKG> <a href="javascript:exit()" ><b>Salir</b></a></TD>
		    	</TR>
		  	</TABLE>
<%
		}
%>		   	
</BODY>
</HTML>
