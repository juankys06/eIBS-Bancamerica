<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.0 Transitional//EN">
<HTML><HEAD>
<META HTTP-EQUIV="Content-Type" CONTENT="text/html; charset=ISO-8859-1">
<META name="GENERATOR" content="IBM WebSphere Page Designer V3.5.2 for Windows">
<META http-equiv="Content-Style-Type" content="text/css"> 
<TITLE>ACH Incoming</TITLE>
<link href="<%=request.getContextPath()%>/pages/style.css" rel="stylesheet">

<jsp:useBean id= "userPO" class= "datapro.eibs.beans.UserPos"  scope="session" />
<jsp:useBean id="error" class="datapro.eibs.beans.ELEERRMessage"  scope="session" />

<SCRIPT LANGUAGE="JavaScript" SRC="<%=request.getContextPath()%>/pages/s/javascripts/eIBS.jsp"> </SCRIPT>
<SCRIPT Language="javascript">

function CheckFile(op){
	if ( document.forms[0].FILENAME.value.length < 1) {
  		alert("Se necesita un nombre de archivo valido!!!");
  		document.forms[0].FILENAME.value='';
  		document.forms[0].FILENAME.focus();
	} else {
  		document.forms[0].SCREEN.value = op;
		document.forms[0].submit();
  	}
}

</SCRIPT>

</HEAD>

<body>

 <h3 align="center">Procesar Archivo Entrante de ACH<img src="<%=request.getContextPath()%>/images/e_ibs.gif" align="left" name="EIBS_GIF" alt="incoming_file, EACH400"></h3>
<hr size="4">
 <FORM METHOD="POST" action="<%=request.getContextPath()%>/servlet/datapro.eibs.ach.JSEACH400" ENCTYPE="multipart/form-data">	
    <INPUT TYPE=HIDDEN NAME="SCREEN" VALUE="2">
    
	<table  class="tbenter" width="100%" height="50%" border="0" cellspacing=0 cellpadding=2>
		<tr>
     		<td align="center">Archivo : 
            	<input type=file name="FILENAME" size="50" maxlength="255" >
 			</td>
		</tr>
		<tr>
			<td align="center">
				<br>
				Esta opción tomará el Archivo en formato NACHA, lo validará y si esta correcto lo procesará.<br><br>
				El reporte ACH400 con el resultado del proceso estará disponible en el Spool del usuario. <br>
				Las transacciones contables se ven en los reportes GL0020/GL0021 y GL0016 (Futuras). Estos <br>
				reportes estarán en el Spool del Usuario si tiene definido aprobación automática o deben<br>
				ser generados una vez apruebe el lote respectivo.<br> 
 			</td>      
	   	</tr>
  	</table>
	<table width="100%">		
  		<tr>
  			<td width="100%"> 
  		  		<div align="center"> 
     				<input id="EIBSBTN" type="button" name="Process" value="Procesar" onClick="javascript:CheckFile('5');">
					<% userPO.setPurpose("PROCESS"); %>
     	  		</div>	
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
 