<html> 
<head>
<title>Compraventa Moneda Extranjera</title>
<META HTTP-EQUIV="Content-Type" CONTENT="text/html; charset=ISO-8859-1">
<link Href="<%=request.getContextPath()%>/pages/style.css" rel="stylesheet">
<%@ page import ="datapro.eibs.master.Util" %>

<script language="Javascript1.1" src="<%=request.getContextPath()%>/pages/s/javascripts/eIBS.jsp"> </SCRIPT>

<jsp:useBean id= "msgInst" class= "datapro.eibs.beans.EPR038501Message"  scope="session" />
<jsp:useBean id= "error" 	class= "datapro.eibs.beans.ELEERRMessage"  	scope="session" />
<jsp:useBean id= "userPO" 	class= "datapro.eibs.beans.UserPos"  		scope="session"/>

<% 

if ( !error.getERRNUM().equals("0")  ) {
      error.setERRNUM("0");
%>
<SCRIPT Language="Javascript">
        showErrors();
</SCRIPT>
<%}%>

</head>
<body>

<H3 align="center">Compraventa Moneda Extranjera - Apertura y Cierre de Oficinas<img src="<%=request.getContextPath()%>/images/e_ibs.gif" align="left" name="EIBS_GIF" ALT="foreign_currency_exchange_maint, EPR0385"></H3>
<hr size="4">


<form method="post" action="<%=request.getContextPath()%>/servlet/datapro.eibs.forex.JSEPR0385">
 
  <INPUT TYPE=HIDDEN NAME="SCREEN" VALUE="600">
  
  <table class="tableinfo">
   <tr> 
   <td>
    <table cellspacing=0 cellpadding=2 width="100%" border="0">    
     	<tr id=trdark> 
	      <td nowrap width="40%"> 
	        <div align="right">Banco : </div>
	      </td>
	      <td nowrap width="60%"> 
	        <input type="text" name="E01PSIBNK" readonly size="3" maxlength="2" value="<%= userPO.getBank() %>">
	      </td>
     	</tr>
     	<tr id=trdark> 
		  <td> 
		     <div align="right">Moneda : </div>        
		  </td>
      	  <td nowrap> 
      	    <input type="text" name="E01PSICCY" readonly size="5" maxlength="3" value="<%= userPO.getCurrency() %>">
      	  </td>     
      	</tr>
     </table>
    </td>
   </tr>
  </table>
  <br>
  <table class="tableinfo">
   <tr> 
   <td>
    <table cellspacing=0 cellpadding=2 width="100%" border="0">    
		<tr>
			<td width="33%"></td>
			<td width="33%" align="center"><B>Compra</B></td>
			<td width="33%" align="center"><B>Venta</B></td>
		</tr>
		<tr>
			<td align="right">Estado</td>
			<td align="center">
				<input type="radio" name="E01FETPNS" value="0" <%if (msgInst.getE01FETPNS().equals("0")) {%> checked <%}%>>Cerrado&nbsp;
				<input type="radio" name="E01FETPNS" value="1" <%if (msgInst.getE01FETPNS().equals("1")) {%> checked <%}%>>Abierto
			</td>
			<td align="center">
				<input type="radio" name="E01FETSNS" value="0" <%if (msgInst.getE01FETSNS().equals("0")) {%> checked <%}%>>Cerrado&nbsp;
				<input type="radio" name="E01FETSNS" value="1" <%if (msgInst.getE01FETSNS().equals("1")) {%> checked <%}%>>Abierto
			</td>
		</tr>
     </table>
    </td>
   </tr>
  </table>

  <p align="center"> 
    <input id="EIBSBTN" type="submit" name="Submit" value="Enviar">
  </p>
  
</form>
</body>
</html>
