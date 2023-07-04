<html> 
<head>
<title>Compraventa Moneda Extranjera</title>
<META HTTP-EQUIV="Content-Type" CONTENT="text/html; charset=ISO-8859-1">
<link Href="<%=request.getContextPath()%>/pages/style.css" rel="stylesheet">
<%@ page import ="datapro.eibs.master.Util" %>

<script language="Javascript1.1" src="<%=request.getContextPath()%>/pages/s/javascripts/eIBS.jsp"> </SCRIPT>

<jsp:useBean id= "msgInst" 	class= "datapro.eibs.beans.EPR031002Message"  	scope="session" />
<jsp:useBean id= "error" 	class= "datapro.eibs.beans.ELEERRMessage"  		scope="session" />
<jsp:useBean id= "userPO" 	class= "datapro.eibs.beans.UserPos"  			scope="session"/>

<%
int NEW = 0;
try { NEW = Integer.parseInt(request.getParameter("NEW"));} catch (Exception e) {}
if (NEW == 1) {
msgInst.destroy();
}
 
if (msgInst == null) {
 	msgInst = new datapro.eibs.beans.EPR031002Message();   
}
	
if (!error.getERRNUM().equals("0")) {
      error.setERRNUM("0");
%>
	<SCRIPT Language="Javascript">
		showErrors();
	</SCRIPT>
<%}%>
</head>
<body>

<H3 align="center">Mantenimiento de Oficinas de Moneda Extranjera<img src="<%=request.getContextPath()%>/images/e_ibs.gif" align="left" name="EIBS_GIF" ALT="foreign_currency_exchange_new, EPR0310"></H3>
<hr size="4">

<form method="post" action="<%=request.getContextPath()%>/servlet/datapro.eibs.forex.JSEPR0310">
  <INPUT TYPE=HIDDEN NAME="SCREEN" VALUE="400">

  <table class="tableinfo">
   <tr> 
   <td>
    <table cellspacing=0 cellpadding=2 width="100%" border="0">    
     	<tr id=trdark> 
	      <td nowrap width="40%"> 
	        <div align="right">Banco : </div>
	      </td>
	      <td nowrap width="60%"> 
	        <input type="text" name="E02FETBNK" size="3" maxlength="2" value="<%= msgInst.getE02FETBNK() %>">
	        <img src="<%=request.getContextPath()%>/images/Check.gif" alt="campo obligatorio" align="absbottom" border="0" width="16" height="20"> 
	      </td>
     	</tr>
     	<tr id=trclear> 
	      <td nowrap> 
	        <div align="right">Oficina : </div>
	      </td>
	      <td nowrap>
	      	<input type="text" name="E02FETBRN" size="5" maxlength="3" value="">
	        <a href="javascript:GetBranch('E02FETBRN',document.forms[0].E02FETBNK.value)"><img src="<%=request.getContextPath()%>/images/1b.gif" alt="Ayuda" align="bottom" border="0" ></a>
	        <img src="<%=request.getContextPath()%>/images/Check.gif" alt="campo obligatorio" align="absbottom" border="0" width="16" height="20">  
		  </td>
     	</tr>
     	<tr id=trdark> 
		  <td> 
		     <div align="right">Moneda : </div>        
		  </td>
      	  <td nowrap> 
      	    <input type="text" name="E02FETCCY" size="5" maxlength="3" value="<%= msgInst.getE02FETCCY() %>">
			<a href="javascript:GetCurrency('E02FETCCY',document.forms[0].E02FETBNK.value)"><img src="<%=request.getContextPath()%>/images/1b.gif" alt="Ayuda" align="bottom" border="0"></a>
			<img src="<%=request.getContextPath()%>/images/Check.gif" alt="campo obligatorio" align="absbottom" border="0" width="16" height="20"> 
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
			<td nowrap width="40%">
				<div align="right">Codigo de Moneda de Limites :</div>
			</td>
			<td nowrap width="60%">
				<input type="text" name="E02FETLIC" size="5" maxlength="3" value="<%= msgInst.getE02FETLIC() %>" > 
				<a href="javascript:GetCurrency('E02FETLIC',document.forms[0].E02FETBNK.value)"><img src="<%=request.getContextPath()%>/images/1b.gif" alt="Ayuda" align="bottom" border="0"></a>
				<img src="<%=request.getContextPath()%>/images/Check.gif" alt="campo obligatorio" align="absbottom" border="0" width="16" height="20"> 
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
			<td align="right">Limite Maximo por Dia</td>
			<td align="center">
				<input type="text" name="E02FETPMD"	value="<%= msgInst.getE02FETPMD() %>" onkeypress="enterDecimal()">
			</td>
			<td align="center">
				<input type="text" name="E02FETSMD"	value="<%= msgInst.getE02FETSMD() %>" onkeypress="enterDecimal()">
			</td>
		</tr>
		<tr>
			<td align="right">Limite Maximo por Mes</td>
			<td align="center">
				<input type="text" name="E02FETPMM"	value="<%= msgInst.getE02FETPMM() %>" onkeypress="enterDecimal()">
			</td>
			<td align="center">
				<input type="text" name="E02FETSMM"	value="<%= msgInst.getE02FETSMM() %>" onkeypress="enterDecimal()">
			</td>
		</tr>
		<tr>
			<td align="right">Limite Maximo por Semestre</td>
			<td align="center">
				<input type="text" name="E02FETPMS"	value="<%= msgInst.getE02FETPMS() %>" onkeypress="enterDecimal()">
			</td>
			<td align="center">
				<input type="text" name="E02FETSMS"	value="<%= msgInst.getE02FETSMS() %>" onkeypress="enterDecimal()">
			</td>
		</tr>
		<tr>
			<td align="right">Limite Maximo por Ano</td>
			<td align="center">
				<input type="text" name="E02FETPMY"	value="<%= msgInst.getE02FETPMY() %>" onkeypress="enterDecimal()">
			</td>
			<td align="center">
				<input type="text" name="E02FETSMY"	value="<%= msgInst.getE02FETSMY() %>" onkeypress="enterDecimal()">
			</td>
		</tr>
		<tr>
			<td align="right">Estado</td>
			<td align="center">
				<input type="radio" name="E02FETPST" value="0" <%if (msgInst.getE02FETPST().equals("0")) {%> checked <%}%>>Cerrado&nbsp;
				<input type="radio" name="E02FETPST" value="1" <%if (msgInst.getE02FETPST().equals("1")) {%> checked <%}%>>Abierto
			</td>
			<td align="center">
				<input type="radio" name="E02FETSST" value="0" <%if (msgInst.getE02FETSST().equals("0")) {%> checked <%}%>>Cerrado&nbsp;
				<input type="radio" name="E02FETSST" value="1" <%if (msgInst.getE02FETSST().equals("1")) {%> checked <%}%>>Abierto
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
