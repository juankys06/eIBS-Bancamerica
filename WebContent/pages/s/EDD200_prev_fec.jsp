<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.0 Transitional//EN">
<html>
<head>
<title>Fecha de Camara de Compensacion Entrante</title>
<META HTTP-EQUIV="Content-Type" CONTENT="text/html; charset=ISO-8859-1">
<META name="GENERATOR" content="IBM WebSphere Page Designer V3.5.2 for Windows">
<META http-equiv="Content-Style-Type" content="text/css">
<link Href="<%=request.getContextPath()%>/pages/style.css" rel="stylesheet">


<script language="Javascript1.1" src="<%=request.getContextPath()%>/pages/s/javascripts/eIBS.jsp"> </SCRIPT>
<SCRIPT Language="javascript">
function CheckACC(){
	var ok=true;
	if(document.forms[0].dia.value == ""){
		alert("Debe Ingresar Fecha");
		ok=false;
		
	}
	
	if(ok){
		document.forms[0].submit();
	}
		
	
}

</SCRIPT>

</head>


<jsp:useBean id= "error" class= "datapro.eibs.beans.ELEERRMessage"  scope="session" />
<jsp:useBean id= "userPO" class= "datapro.eibs.beans.UserPos"  scope="session" />

<body bgcolor="#FFFFFF">

<H3 align="center">Fecha de Camara de Compensacion Entrante <img src="<%=request.getContextPath()%>/images/e_ibs.gif" align="left" name="EIBS_GIF" ALT="previewMantParamRT, JSEFE0501"></H3>

<hr size="4">

<form method="post" action="<%=request.getContextPath()%>/servlet/datapro.eibs.approval.JSEDD2000">
  <p> 
    <INPUT TYPE=HIDDEN NAME="SCREEN" VALUE="11">
    
  </p>

<p>
  <table id="TBHELP" align="center" width="100%" height="10%"  border="0">
<th id="THHELP">Fecha del Proceso: 

 				<INPUT type="text" name="dia" size="2" maxlength="2" value="" readonly> 
				 <INPUT type="text" name="mes" size="2"  maxlength="2" value="" readonly> 
				 <INPUT type="text" name="ano" size="2" maxlength="2" value="" readonly>
				<a href="javascript:DatePicker(document.forms[0].dia,document.forms[0].mes,document.forms[0].ano)">
				<img src="<%=request.getContextPath()%>/images/calendar.gif" alt="ayuda" align="absmiddle" border="0"></a>
</th>
      

    
  </table> 

<div align="center"> 
	   <input id="EIBSBTN" type=button name="Submit" value="Enviar" onClick="CheckACC()">
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