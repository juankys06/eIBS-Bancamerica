<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.0 Transitional//EN">
<html>
<head>
<title>>Customer Bills &amp; Account Payable Maintenance</title>
<META HTTP-EQUIV="Content-Type" CONTENT="text/html; charset=ISO-8859-1">
<META name="GENERATOR" content="IBM WebSphere Page Designer V3.5.2 for Windows">
<META http-equiv="Content-Style-Type" content="text/css">
<link Href="<%=request.getContextPath()%>/pages/style.css" rel="stylesheet">


<script language="Javascript1.1" src="<%=request.getContextPath()%>/pages/s/javascripts/eIBS.jsp"> </SCRIPT>
<SCRIPT Language="javascript">

function CheckACC(){
if ( document.forms[0].E01BAVNUM.value.length < 1) {
  alert("Ingrese un numero valido de Proveedor");
  document.forms[0].E01BAVNUM.value='';
  document.forms[0].E01BAVNUM.focus();
}
else {
  document.forms[0].submit();
  }
}

</SCRIPT>
</head>


<jsp:useBean id= "error" class= "datapro.eibs.beans.ELEERRMessage"  scope="session" />

<jsp:useBean id= "userPO" class= "datapro.eibs.beans.UserPos"  scope="session" />

<body bgcolor="#FFFFFF">

<H3 align="center">Mantenimiento de Proveedores<img src="<%=request.getContextPath()%>/images/e_ibs.gif" align="left" name="EIBS_GIF" ALT="enter_vendor, EBA0010"></H3>

<hr size="4">

<form method="post" action="<%=request.getContextPath()%>/servlet/datapro.eibs.params.JSEBA0010">
  <p> 
    <INPUT TYPE=HIDDEN NAME="SCREEN" VALUE="2">
  </p>

  <table class="tbenter"  width="100%" border="0">
    <tr> 
      <td nowrap ALIGN=CENTER> 
        Ingrese Código de Proveedor : 
        <INPUT type="text" name="E01BAVNUM" size="10" maxlength="9" onkeypress="enterInteger()">
        <a href="javascript:GetInternetVendor('E01BAVNUM','I')"><img src="<%=request.getContextPath()%>/images/1b.gif" alt="help" align="middle" border="0"></a> 
      </td>
    </tr>       
  </table>
  <h4>Tipo de Operación </h4>
  <table class="tableinfo" cellspacing="0" cellpadding="2" width="100%"border="0" >
   <tr> 
      <td > 
        <table id="tbenter" width="100%" border="0" cellspacing="1" cellpadding="0">      
          <tr> 
            <td width="20%" nowrap>&nbsp;</td>
            <td width="20%" nowrap> 
              <input type="radio" name="E01OPETYP" value="0002" checked > Mantenimiento   
            </td>
            <td width="60%" nowrap> 
            </td>
          </tr>  
          <tr> 
            <td width="20%" nowrap>&nbsp;</td>
            <td width="20%" nowrap> 
              <input type="radio" name="E01OPETYP" value="0001" > Nuevo
            </td>
            <td width="60%" nowrap> 
            </td>
          </tr>                                     
        </table>      
      </td>
    </tr>  
  </table>  
 <p align="center"> 
    <input id="EIBSBTN" type=button name="Submit" value="Aceptar" onClick="CheckACC()">
  </p>  
 
<script language="JavaScript">
  document.forms[0].E01BAVNUM.focus();
  document.forms[0].E01BAVNUM.select();
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
