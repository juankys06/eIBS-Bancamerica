<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.0 Transitional//EN">
<HTML><HEAD>
<META HTTP-EQUIV="Content-Type" CONTENT="text/html; charset=ISO-8859-1">
<META name="GENERATOR" content="IBM WebSphere Page Designer V3.5.2 for Windows">
<META http-equiv="Content-Style-Type" content="text/css"> 
<TITLE>Portfolio</TITLE>
<link href="<%=request.getContextPath()%>/pages/style.css" rel="stylesheet">

<script language="Javascript1.1" src="<%=request.getContextPath()%>/pages/s/javascripts/eIBS.jsp"> </SCRIPT>

<jsp:useBean id="error" class= "datapro.eibs.beans.ELEERRMessage"  scope="session" />
<SCRIPT Language="javascript">

function checkCust(){
	if(trim(document.forms[0].E01PRFCUN.value).length < 1){
		alert("Introduzca un numero valido de portafolio");
		document.forms[0].E01PRFCUN.value='';
		document.forms[0].E01PRFCUN.focus();
		return false;
	}
	else {
	    if(trim(document.forms[0].E01PRFNUM.value).length > 0) document.forms[0].OPCODE.value='0002'; 
 		return true;
	}
}

</SCRIPT>
</HEAD>

<body>

<h3 align="center">Portafolio de Inversiones<img src="<%=request.getContextPath()%>/images/e_ibs.gif" align="left" name="EIBS_GIF" alt="inv_enter_port_new, EIE0010"></h3>
<hr>
 <FORM METHOD="post" ACTION="<%=request.getContextPath()%>/servlet/datapro.eibs.invest.JSEIE0010" onSubmit="return(checkCust())">
  
    <INPUT TYPE=HIDDEN NAME="SCREEN" VALUE="200">
    <INPUT TYPE=HIDDEN NAME="OPCODE" VALUE="0001">
    <table class="tbenter" cellspacing=0 cellpadding=2 height="30%" border="0">
      <tr> 
        <td></td>
      </tr>
    </table>
    <table class="tbenter" cellspacing=0 cellpadding=2 width="100%" border="0">
      <tr> 
        <td width="40%"> 
        	
        <div align="right">N&uacute;mero de Clientes :</div> 
        </td>
        <td nowrap> 
          <INPUT type="text" name="E01PRFCUN" size="9" maxlength="9" onkeypress="enterInteger()">
          <a href="javascript:GetCustomerDescId('E01PRFCUN','D01CUSNA1','')"><img src="<%=request.getContextPath()%>/images/1b.gif" alt="help" align="absmiddle" border="0" ></a> 
          <input type="text" name="D01CUSNA1" size="35" maxlength="35" readonly>
      </td>
      </tr>
      <tr> 
        <td width="40%"> 
        	
        <div align="right">Portafolio :</div> 
        </td>
        <td nowrap align="left"> 
          <INPUT type="text" name="E01PRFNUM" size="5" maxlength="4" onkeypress="enterInteger()">
        <a href="javascript:GetPortfolioNumDesc('E01PRFNUM','E01PRFCUN','',document.forms[0].E01PRFCUN.value)"><i><img src="<%=request.getContextPath()%>/images/1b.gif" alt="help" align="absmiddle" border="0" ></i></a> 
        ( Blanco para nuevo) </td>
      </tr>
          
    </table>
  
  <p align="center"> 
    <input id="EIBSBTN" type=submit name="Submit" value="Enviar">
  </p>
<script language="JavaScript">
  document.forms[0].E01PRFCUN.focus();
  document.forms[0].E01PRFCUN.select();
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
 