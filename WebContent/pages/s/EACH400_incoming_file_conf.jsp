<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.0 Transitional//EN">
<HTML><HEAD>
<META HTTP-EQUIV="Content-Type" CONTENT="text/html; charset=ISO-8859-1">
<META name="GENERATOR" content="IBM WebSphere Page Designer V3.5.2 for Windows">
<META http-equiv="Content-Style-Type" content="text/css"> 
<TITLE>ACH Incoming</TITLE>
<link href="<%=request.getContextPath()%>/pages/style.css" rel="stylesheet">

<jsp:useBean id= "userPO" class= "datapro.eibs.beans.UserPos"  scope="session" />

<SCRIPT LANGUAGE="JavaScript" SRC="<%=request.getContextPath()%>/pages/s/javascripts/eIBS.jsp"> </SCRIPT>


</HEAD>

<body>

 <h3 align="center">Procesar Archivo Entrante de ACH<img src="<%=request.getContextPath()%>/images/e_ibs.gif" align="left" name="EIBS_GIF" alt="incoming_file, EACH400"></h3>
<hr size="4">
 <form method="post" action="<%=request.getContextPath()%>/servlet/datapro.eibs.ach.JSEACH400">
    <INPUT TYPE=HIDDEN NAME="SCREEN" VALUE="2">
    
	<table  class="tbenter" width="100%" height="50%" border="0" cellspacing=0 cellpadding=2>
		<tr>
     		<td align="center">
 			</td>
		</tr>
		<tr>
			<td align="center">
				<br>
				El Archivo fue procesado.<br><br>
				El reporte ACH400 con el resultado del proceso estará disponible en el Spool del usuario. <br>
				Las transacciones contables se ven en los reportes GL0020/GL0021. Estos <br>
				reportes estarán en el Spool del Usuario si tiene definido aprobación automática o deben<br>
				ser generados una vez apruebe el lote respectivo.<br> 
 			</td>      
	   	</tr>
  	</table>
	<table width="100%">		
  		<tr>
  			<td width="100%"> 
  			</td>
  		</tr>	
  </table>

 </FORM>
</BODY>
</HTML>
 