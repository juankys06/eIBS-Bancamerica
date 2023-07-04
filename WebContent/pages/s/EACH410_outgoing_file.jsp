<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.0 Transitional//EN">
<HTML><HEAD>
<META HTTP-EQUIV="Content-Type" CONTENT="text/html; charset=ISO-8859-1">
<META name="GENERATOR" content="IBM WebSphere Page Designer V3.5.2 for Windows">
<META http-equiv="Content-Style-Type" content="text/css"> 
<TITLE>ACH Outgoing</TITLE>
<link href="<%=request.getContextPath()%>/pages/style.css" rel="stylesheet">

<jsp:useBean id= "currUser" class= "datapro.eibs.beans.ESS0030DSMessage"  scope="session" /> 
<jsp:useBean id= "userPO" class= "datapro.eibs.beans.UserPos"  scope="session" />
<jsp:useBean id="error" class="datapro.eibs.beans.ELEERRMessage"  scope="session" />

<SCRIPT LANGUAGE="JavaScript" SRC="<%=request.getContextPath()%>/pages/s/javascripts/eIBS.jsp"> </SCRIPT>
<SCRIPT Language="javascript">

function CheckFile(op){

if (document.getElementById("E01DATA").value == "") {
		alert("Favor Seleccionar la Clase de Entrada a generar o ALL para Todas.");
		return;	 
	}
  		document.forms[0].SCREEN.value = op;
		document.forms[0].submit();
}

</SCRIPT>

</HEAD>

<body>

 <h3 align="center">Generar Archivo Saliente de ACH<img src="<%=request.getContextPath()%>/images/e_ibs.gif" align="left" name="EIBS_GIF" alt="incoming_file, EACH400"></h3>
<hr size="4">
 <FORM METHOD="POST" action="<%=request.getContextPath()%>/servlet/datapro.eibs.ach.JSEACH410" >	
    <INPUT TYPE=HIDDEN NAME="SCREEN" VALUE="5">
    
	<table  class="tbenter" width="100%" height="50%" border="0" cellspacing=0 cellpadding=2>
		<tr>
     		<td align="center">
				Esta opción generará el Archivo ACH con las transacciones y/o retornos<br>
				con fechas anteriores o iguales a <%= datapro.eibs.master.Util.formatDate(currUser.getE01RDD(),currUser.getE01RDM(),currUser.getE01RDY())%>.<br>
				<BR><BR>
				Una copia de este archivo estará disponible en el User Spool, asi como los reportes ACH410,ACH410E, ACH415 y GL0020. 
 			</td>      
    	</tr>
  	</table>
  	<table  class="tableinfo" width="100%">
    	<tr bordercolor="#FFFFFF"> 
      		<td nowrap> 
        		<table cellspacing="0" width="100%" border="0">
          			<tr id="trdark"> 
            			<td nowrap width="30%" align="right">Clase de Entrada a Generar : </td>
            			<td nowrap align="left" width="70%">
							<INPUT type="text" name="E01DATA" size="4"  maxlength="3" value="">
							<A href="javascript:GetAchClass('E01DATA','E01ECDDSC')">
							<IMG src="<%=request.getContextPath()%>/images/1b.gif" alt="Ayuda" align="bottom" border="0"> ALL = Todas</A> 
		        			<B><IMG src="<%=request.getContextPath()%>/images/Check.gif" alt="Campo Obligatorio" align="bottom"></B>
		        			<INPUT type="text" name="E01ECDDSC" size="30"  maxlength="30" value="" readonly>
		        		</td>
					</tr>
					<TR>
						<TD nowrap width="30%" align="right">Formato NACHA </TD>
						<TD nowrap align="left" width="70%">
							<INPUT type="text" name="E01NACHA" size="2" maxlength="1" value="Y">
							 (Y) Si (N) Formato sin encabezado
						</TD>
					</TR>
		</table>
			</td>
		</tr>
	</table>
			
	<table width="100%">		
  		<tr>
  			<td width="100%"> 
  		  		<div align="center"> 
     				<input id="EIBSBTN" type="button" name="Process" value="Generar" onClick="javascript:CheckFile('5');">
					<% userPO.setPurpose("GENERATE"); %>
     	  		</div>	
  			</td>
  		</tr>	
  </table>


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
 