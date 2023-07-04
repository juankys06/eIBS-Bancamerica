<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.0 Transitional//EN">
<html>
<head>
<title>Services Charges</title>
<META HTTP-EQUIV="Content-Type" CONTENT="text/html; charset=ISO-8859-1">
<META name="GENERATOR" content="IBM WebSphere Page Designer V3.5.2 for Windows">
<META http-equiv="Content-Style-Type" content="text/css">
<link Href="<%=request.getContextPath()%>/pages/style.css" rel="stylesheet">

<script language="Javascript1.1" src="<%=request.getContextPath()%>/pages/s/javascripts/eIBS.jsp"> </SCRIPT>

</head>


<jsp:useBean id= "error" class= "datapro.eibs.beans.ELEERRMessage"  scope="session" />
<jsp:useBean id= "userPO" class= "datapro.eibs.beans.UserPos"  scope="session" />

<body bgcolor="#FFFFFF">

<H3 align="center">Comisiones y Gastos de Cartas de Credito<img src="<%=request.getContextPath()%>/images/e_ibs.gif" align="left" name="EIBS_GIF" ALT="sc_enter_selection, ELC0600"></H3>

<hr size="4">

<form method="post" action="<%=request.getContextPath()%>/servlet/datapro.eibs.products.JSELC0600">
  <p> 
    <INPUT TYPE=HIDDEN NAME="SCREEN" VALUE="2">
  </p>
<br>
  <table class="tableinfo" cellspacing="0" cellpadding="2" width="100%"border="0" >
   <tr> 
      <td > 
        <table id="tbenter" width="100%" border="0" cellspacing="1" cellpadding="0">      
          <tr> 
            <td width="50%" nowrap> 
              <div align="right">Banco :</div>  
            </td>
            <td width="50%" nowrap>
             	<input type="text" name="E01RLCBNK" size="3" maxlength="2" value="" onkeypress="enterInteger()">
            </td>
          </tr>  
          <td width="49%" nowrap> 
        <div align="right">Tipo de Producto : </div>
      </td>
      <td width="51%" nowrap> 
        <input type="text" name="E01RLCATY"  size=5 maxlength="8" value="">
        <a href="javascript:GetProductRates('E01RLCATY','LC')"> 
        <img src="<%=request.getContextPath()%>/images/1b.gif" alt="help" align="middle" border="0" ></a> 
      </td>       
        </table>      
      </td>      
        </table>      
      </td>
    </tr>  
  </table>  
 <p align="center"> 
    <input id="EIBSBTN" type=submit name="Aceptar" value="Aceptar" >
  </p>  
<script language="JavaScript">
  document.forms[0].E01RLCBNK.focus();
  document.forms[0].E01RLCBNK.select();
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
</form>
</body>
</html>
