<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.0 Transitional//EN">
<HTML><HEAD>
<META HTTP-EQUIV="Content-Type" CONTENT="text/html; charset=ISO-8859-1">
<META name="GENERATOR" content="IBM WebSphere Page Designer V3.5.2 for Windows">
<META http-equiv="Content-Style-Type" content="text/css"> 
<TITLE>Importación de Interfaces</TITLE>
<link href="<%=request.getContextPath()%>/pages/style.css" rel="stylesheet">

<SCRIPT LANGUAGE="JavaScript" SRC="<%=request.getContextPath()%>/pages/s/javascripts/eIBS.jsp"> </SCRIPT>
<SCRIPT Language="javascript">

function CheckFile(){
	if (document.forms[0].FILENAME.value.length < 1) {
		alert("Por favor, ingrese un nombre de archivo.");
		document.forms[0].FILENAME.value='';
		document.forms[0].FILENAME.focus();
	}
	else {
		document.forms[0].SCREEN.value = "400";
		document.forms[0].submit();
	}
}

</SCRIPT>
<jsp:useBean id="userPO" class= "datapro.eibs.beans.UserPos"  scope="session" />
<jsp:useBean id="error" class="datapro.eibs.beans.ELEERRMessage"  scope="session" />

</HEAD>

<body>

 <h3 align="center">Importar Interfaz IMPORT<img src="<%=request.getContextPath()%>/images/e_ibs.gif" align="left" name="EIBS_GIF" alt="enter_file.jsp, EIMP000"></h3>
<hr size="4">
 <FORM METHOD="POST" action="<%=request.getContextPath()%>/servlet/datapro.eibs.misc.JSEIMP002" ENCTYPE="multipart/form-data">	
    <p><INPUT TYPE=HIDDEN NAME="SCREEN" VALUE="400">
    </p>
    <table  class="tbenter" width="100%" height="75%" border="0" cellspacing=0 cellpadding=2>
	<tr>
     <td align="center"> 
             Archivo : 
            <input type=file name="FILENAME" size="50" maxlength="255" defaultValue="10.101.2.10/L:/LA:/WS"><p align="center"> 
			  <input id="EIBSBTN" type="button" name="Submit" value="Enviar" onClick="CheckFile()"> 
 			</p> 
     </td>        
    </tr>
  </table>

<script language="JavaScript">
  document.forms[0].FILENAME.focus();
</script>
<% 
 if ( !error.getERRNUM().equals("0")  ) {
      error.setERRNUM("0");
 %>
     <SCRIPT Language="Javascript">
            showErrors();
     </SCRIPT>
 <%
 }
%>
 </FORM>
</BODY>
</HTML>
 