<html> 
<head>
<title>Compraventa Moneda Extranjera</title>
<META HTTP-EQUIV="Content-Type" CONTENT="text/html; charset=ISO-8859-1">
<link Href="<%=request.getContextPath()%>/pages/style.css" rel="stylesheet">


<script language="Javascript1.1" src="<%=request.getContextPath()%>/pages/s/javascripts/eIBS.jsp"> </SCRIPT>

<jsp:useBean id= "error" class= "datapro.eibs.beans.ELEERRMessage"  scope="session" />
<jsp:useBean id= "msgMT" class= "datapro.eibs.beans.EPR038501Message"  scope="session" />

<script language="Javascript1.1">
function checkCurrency() {
	if (document.forms[0].E01FETCCY.value == "") document.forms[0].E01FETCCY.value = "999";
}
</script>

</head>
<body>

<H3 align="center">Compraventa Moneda Extranjera - Apertura y Cierre de Oficinas<img src="<%=request.getContextPath()%>/images/e_ibs.gif" align="left" name="EIBS_GIF" ALT="foreign_currency_exchange_enter_inq, EPR0385"></H3>
<hr size="4">
<p>&nbsp;</p>

<form method="post" action="<%=request.getContextPath()%>/servlet/datapro.eibs.forex.JSEPR0385" onsubmit="checkCurrency()">
  <p> 
    <INPUT TYPE=HIDDEN NAME="SCREEN" VALUE="100">
  </p>
  <table class="tableinfo">
   <tr> 
   <td>
    <table cellspacing=0 cellpadding=2 width="100%" border="0">    
     	<tr id=trdark> 
	      <td nowrap width="40%"> 
	        <div align="right">Banco : </div>
	      </td>
	      <td nowrap> 
	        <input type="text" name="E01FETBNK" size="3" maxlength="2" onKeypress="enterInteger()" value="<%= msgMT.getE01FETBNK()%>">
	        <img src="<%=request.getContextPath()%>/images/Check.gif" alt="campo obligatorio" align="absbottom" border="0" width="16" height="20"> 
	      </td>
     	</tr>
     	<tr id=clear> 
		  <td> 
		     <div align="right">Moneda  : </div>        
		  </td>
      	  <td nowrap> 
      	    <input type="text" name="E01FETCCY" size="5" maxlength="3" value="<%= msgMT.getE01FETCCY()%>">
      	    <a href="javascript:GetCurrency('E01FETCCY',document.forms[0].E01FETBNK.value)"><img src="<%=request.getContextPath()%>/images/1b.gif" alt="Ayuda" align="bottom" border="0" ></a>
      	  </td>     
      	</tr>      	
     </table>
    </td>
   </tr>
  </table>
  
  <p align="center"> 
    <input id="EIBSBTN" type=submit name="Submit" value="Enviar">
  </p>
  
	<script language="JavaScript">
	  document.forms[0].E01FETBNK.focus();
	  document.forms[0].E01FETBNK.select();
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
