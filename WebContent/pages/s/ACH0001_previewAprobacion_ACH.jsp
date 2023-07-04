<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.0 Transitional//EN">
<html>
<head>
<title>Previa Aprobacion de Rechazos</title>
<META HTTP-EQUIV="Content-Type" CONTENT="text/html; charset=ISO-8859-1">
<META name="GENERATOR" content="IBM WebSphere Page Designer V3.5.2 for Windows">
<META http-equiv="Content-Style-Type" content="text/css">
<link Href="<%=request.getContextPath()%>/pages/style.css" rel="stylesheet">


<script language="Javascript1.1" src="<%=request.getContextPath()%>/pages/s/javascripts/eIBS.jsp"> </SCRIPT>
<SCRIPT Language="javascript">
function CheckACC(){
		
			document.forms[0].submit();	
	
}
function Validate(){	
	if(document.forms[0].TIPTRN.value == "0"){
		alert("Debe Seleccionar Un tipo de Transaccion");
		
	}else{
		CheckACC();		
	}


}

</SCRIPT>

</head>


<jsp:useBean id= "error" class= "datapro.eibs.beans.ELEERRMessage"  scope="session" />
<jsp:useBean id= "userPO" class= "datapro.eibs.beans.UserPos"  scope="session" />

<body bgcolor="#FFFFFF">

<H3 align="center"> Aprobacion de Transacciones Entrantes ACH Rechazadas<img src="<%=request.getContextPath()%>/images/e_ibs.gif" align="left" name="EIBS_GIF" ALT="previewRechazo_ACH, JSEACH001"></H3>

<hr size="4">

<form method="post" action="<%=request.getContextPath()%>/servlet/datapro.eibs.ach.JSEACH001">
  <p> 
    <INPUT TYPE=HIDDEN NAME="SCREEN" VALUE="110">
    <INPUT TYPE=HIDDEN NAME="OPECOD" VALUE="0004">
     <INPUT TYPE=HIDDEN NAME="opt" VALUE="1">
  </p>

<p>
<table id="TBHELP" align="center" width="100%" height="60%" border="1">

<th id="THHELP">Seleccione El Tipo de Transaccion: 

 				<select name="TIPTRN">
  <option value="0"></option>					
  <option value="*">Todos</option>				
  <option value="TAR">Pagos de Tarjetas De Credito</option>  
  <option value="PRE">Pagos de Prestamos</option>
  <option value="LCL">Abonos en Cuenta</option>
 
	</select>
</th>

				  
				  
     
	
</table>

<div align="center"> 
	   <input id="EIBSBTN" type=button name="Submit" value="Enviar" onClick="Validate()">
  </div> 
</p> 
  

<% 
 if ( !error.getERRNUM().equals("0")  ) {
      error.setERRNUM("0");
 %>
     <SCRIPT Language="Javascript">;
            showErrors();
     </SCRIPT>
  <%
 }
%> 
</form>
</body>
</html>