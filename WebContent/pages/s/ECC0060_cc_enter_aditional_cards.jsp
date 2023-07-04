<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.0 Transitional//EN">
<html>
<head>
<title>Tarjetas Adicionales</title>
<META HTTP-EQUIV="Content-Type" CONTENT="text/html; charset=ISO-8859-1">
<META name="GENERATOR" content="IBM WebSphere Page Designer V3.5.2 for Windows">
<META http-equiv="Content-Style-Type" content="text/css">
<link Href="<%=request.getContextPath()%>/pages/style.css" rel="stylesheet">


<script language="Javascript1.1" src="<%=request.getContextPath()%>/pages/s/javascripts/eIBS.jsp"> </SCRIPT>
<SCRIPT Language="javascript">
<% 
	String appCode = "";
	String type = request.getParameter("Type");
	if (type == null) type = "";
	if (type.equals("D")) {
		appCode = "RT";
	} else if (type.equals("C")) {
		appCode = "94";
	}
	
	String opt = request.getParameter("opt");
	if (opt == null) opt = "";
%>

function CheckACC(){
if ( document.forms[0].E01CCRCRA.value.length < 1) {
  alert("Debe ser dado un número de cuenta válido");
  document.forms[0].E01CCRCRA.value='';
  document.forms[0].E01CCRCRA.focus();
}
else {
  document.forms[0].submit();
  }
}

function getType(type){
	if (type == 'D') {
		document.forms[0].Type.value='D';
		document.forms[0].typAcc.value='RT';
	} else {
		document.forms[0].Type.value='C';
		document.forms[0].typAcc.value='94';
	}
}

</SCRIPT>


</head>


<jsp:useBean id= "error" class= "datapro.eibs.beans.ELEERRMessage"  scope="session" />
<jsp:useBean id= "userPO" class= "datapro.eibs.beans.UserPos"  scope="session" />

<body bgcolor="#FFFFFF">

<H3 align="center">
<%	if (!opt.equals("4")) out.print("Asignar "); 
	else out.print("Cambio de Estado de "); 
	
	if (type.equals("D")) out.print("Tarjetas de Débito");
	else if (type.equals("C")) out.print("Tarjetas de Crédito");
	else out.print("Tarjetas");
%>
					
<img src="<%=request.getContextPath()%>/images/e_ibs.gif" align="left" name="EIBS_GIF" ALT="cc_enter_aditional_cards, ECC0060"></H3>
<hr size="4">

<form method="post" action="<%=request.getContextPath()%>/servlet/datapro.eibs.products.JSECC0060">
  <p> 
    <INPUT TYPE=HIDDEN NAME="SCREEN" VALUE="600">
    <INPUT TYPE=HIDDEN NAME="opt" VALUE="<%= opt %>">
  </p>

<% if (appCode.equals("")) { %>
    <INPUT TYPE=HIDDEN NAME="Type" VALUE="D">
    <INPUT TYPE=HIDDEN NAME="typAcc" VALUE="RT">

  <table class="tbenter" HEIGHT="25%" width="100%" border="0">
    <tr> 
      <td nowrap ALIGN=CENTER>Numero de Cuenta : 
        <INPUT type="text" name="E01CCRCRA" size="12" maxlength="12" onkeypress="enterInteger()">
        <a href="javascript:GetAccount('E01CCRCRA','',document.forms[0].typAcc.value,'')">
        <img src="<%=request.getContextPath()%>/images/1b.gif" alt="help" align="absbottom" border="0" ></a>     
      </td>
    </tr>       
  </table>
  <h4>Tipo de Tarjeta</h4>
  <table class="tableinfo" cellspacing="0" cellpadding="2" width="100%"border="0" >
   <tr> 
      <td > 
        <table id="tbenter" width="100%" border="0" cellspacing="1" cellpadding="0">      
          <tr> 
            <td width="20%" nowrap>&nbsp;</td>
            <td width="20%" nowrap> 
              <input type="radio" name="E01TARTYP" value="D" onClick="javascript:getType(this.value)" checked> Tarjeta de Debito
            </td>
            <td width="60%" nowrap> 
            </td>
          </tr>  
          <tr> 
            <td width="20%" nowrap>&nbsp;</td>
            <td width="20%" nowrap> 
              <input type="radio" name="E01TARTYP" value="C" onClick="javascript:getType(this.value)"> Tarjeta
            de Credito</td>
            <td width="60%" nowrap> 
            </td>
          </tr>                                     
        </table>      
      </td>
    </tr>  
  </table>  
  
<% } else { %>
    <INPUT TYPE=HIDDEN NAME="Type" VALUE="<%= type %>">
    
  <table class="tbenter" HEIGHT="25%" width="100%" border="0">
    <tr> 
      <td nowrap ALIGN=CENTER>Numero de Cuenta : 
        <INPUT type="text" name="E01CCRCRA" size="12" maxlength="12" onkeypress="enterInteger()">
        <a href="javascript:GetAccount('E01CCRCRA','','<%= appCode%>','')">
        <img src="<%=request.getContextPath()%>/images/1b.gif" alt="help" align="absbottom" border="0" ></a>     
      </td>
    </tr>       
  </table> 
  
<% } %>
  
 <p align="center"> 
    <input id="EIBSBTN" type=button name="Submit" value="Aceptar" onClick="CheckACC()">
  </p>  
<script language="JavaScript">
  document.forms[0].E01CCRCRA.focus();
  document.forms[0].E01CCRCRA.select();
</script>
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

