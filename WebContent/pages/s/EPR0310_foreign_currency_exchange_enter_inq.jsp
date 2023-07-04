<html> 
<head>
<title>Compraventa Moneda Extranjera</title>
<META HTTP-EQUIV="Content-Type" CONTENT="text/html; charset=ISO-8859-1">
<link Href="<%=request.getContextPath()%>/pages/style.css" rel="stylesheet">


<script language="Javascript1.1" src="<%=request.getContextPath()%>/pages/s/javascripts/eIBS.jsp"> </SCRIPT>

<jsp:useBean id= "error" class= "datapro.eibs.beans.ELEERRMessage"  scope="session" />
<jsp:useBean id= "msgMT" class= "datapro.eibs.beans.EPR031001Message"  scope="session" />

<script language="Javascript1.1">
function checkOffice() {
	if (document.forms[0].E01FETBRN.value == "") document.forms[0].E01FETBRN.value = "999";
}
</script>

</head>
<body>

<H3 align="center">Compraventa Moneda Extranjera-Actualizacion de Oficinas<img src="<%=request.getContextPath()%>/images/e_ibs.gif" align="left" name="EIBS_GIF" ALT="foreign_currency_exchange_enter_inq, EPR0310"></H3>
<hr size="4">
<p>&nbsp;</p>

<form method="post" action="<%=request.getContextPath()%>/servlet/datapro.eibs.forex.JSEPR0310" onsubmit="checkOffice()">
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
	      </td>
     	</tr>
     	<tr id=trclear> 
	      <td nowrap width="40%"> 
	        <div align="right">Oficina : </div>
	      </td>
	      <td nowrap> 
	        <input type="text" name="E01FETBRN" size="4" maxlength="3" value="">
      	    <a href="javascript:GetBranch('E01FETBRN',document.forms[0].E01FETBNK.value)"><img src="<%=request.getContextPath()%>/images/1b.gif" alt="Ayuda" align="bottom" border="0" onKeypress="enterInteger()"></a>
	      </td>
     	</tr>
     	<tr id=trdark> 
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
