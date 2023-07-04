<html> 
<head>
<title>Compraventa Moneda Extranjera</title>
<META HTTP-EQUIV="Content-Type" CONTENT="text/html; charset=ISO-8859-1">
<link Href="<%=request.getContextPath()%>/pages/style.css" rel="stylesheet">


<script language="Javascript1.1" src="<%=request.getContextPath()%>/pages/s/javascripts/eIBS.jsp"> </SCRIPT>

<jsp:useBean id= "error" class= "datapro.eibs.beans.ELEERRMessage"  scope="session" />
<jsp:useBean id= "msgMT" class= "datapro.eibs.beans.EPR030501Message"  scope="session" />

<script language="Javascript1.1">
function checkCustomer() {
	if (document.forms[0].E01PSICUS.value == "") document.forms[0].E01PSICUS.value = "999999999";
}
</script>

</head>
<body>

<H3 align="center">Compraventa Moneda Extranjera-Actualizacion de Intrumentos<img src="<%=request.getContextPath()%>/images/e_ibs.gif" align="left" name="EIBS_GIF" ALT="foreign_currency_exchange_enter_inq, EPR0305"></H3>
<hr size="4">
<p>&nbsp;</p>

<form method="post" action="<%=request.getContextPath()%>/servlet/datapro.eibs.forex.JSEPR0305" onsubmit="checkCustomer()">
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
	        <input type="text" name="E01PSIBNK" size="3" maxlength="2" onKeypress="enterInteger()" value="<%= msgMT.getE01PSIBNK()%>">
	      </td>
     	</tr>
     	<tr id=trclear> 
	      <td nowrap width="40%"> 
	        <div align="right">Instrumento : </div>
	      </td>
	      <td nowrap> 
	        <input type="text" name="E01PSIINS" size="4" maxlength="4" value="<%= msgMT.getE01PSIINS()%>">
      	    <a href="javascript:GetCodeCNOFC('E01PSIINS','YJ')"><img src="<%=request.getContextPath()%>/images/1b.gif" alt="Ayuda" align="bottom" border="0" ></a>
	      </td>
     	</tr>
     	<tr id=trdark> 
		  <td> 
		     <div align="right">Moneda  : </div>        
		  </td>
      	  <td nowrap> 
      	    <input type="text" name="E01PSICCY" size="5" maxlength="3" value="<%= msgMT.getE01PSICCY()%>">
      	    <a href="javascript:GetCurrency('E01PSICCY',document.forms[0].E01PSIBNK.value)"><img src="<%=request.getContextPath()%>/images/1b.gif" alt="Ayuda" align="bottom" border="0" ></a>
      	  </td>     
      	</tr>
      	<tr id=trclear> 
		  <td> 
		     <div align="right">Cliente  : </div>        
		  </td>
      	  <td nowrap> 
      	    <input type="text" name="E01PSICUS" size="10" maxlength="9" value="<%= msgMT.getE01PSICUS()%>">
            <a href="javascript:GetCustomerDescId('E01PSICUS','','')"><img src="<%=request.getContextPath()%>/images/1b.gif" alt="Ayuda" align="absbottom" border="0" ></a>
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
	  document.forms[0].E01PSIBNK.focus();
	  document.forms[0].E01PSIBNK.select();
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
