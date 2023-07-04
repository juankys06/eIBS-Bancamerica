<html>  
<head>
<title>Compraventa Moneda Extranjera</title>
<META HTTP-EQUIV="Content-Type" CONTENT="text/html; charset=ISO-8859-1">
<link Href="<%=request.getContextPath()%>/pages/style.css" rel="stylesheet">
<%@ page import ="datapro.eibs.master.Util" %>

<script language="Javascript1.1" src="<%=request.getContextPath()%>/pages/s/javascripts/eIBS.jsp"> </SCRIPT>

<jsp:useBean id= "msgInst" 	class= "datapro.eibs.beans.EPR030502Message"  	scope="session" />
<jsp:useBean id= "error" 	class= "datapro.eibs.beans.ELEERRMessage"  		scope="session" />
<jsp:useBean id= "userPO" 	class= "datapro.eibs.beans.UserPos"  			scope="session"/>

<%
int NEW = 0;
try { NEW = Integer.parseInt(request.getParameter("NEW"));} catch (Exception e) {}
if (NEW == 1) {
msgInst.destroy();
}

if (msgInst == null) {
 	msgInst = new datapro.eibs.beans.EPR030502Message();
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

<H3 align="center">Mantenimiento de Instrumentos de Moneda Extranjera<img src="<%=request.getContextPath()%>/images/e_ibs.gif" align="left" name="EIBS_GIF" ALT="foreign_currency_exchange_new, EPR0305"></H3>
<hr size="4">

<form method="post" action="<%=request.getContextPath()%>/servlet/datapro.eibs.forex.JSEPR0305">
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
	        <input type="text" name="E02PSIBNK" size="3" maxlength="2" value="<%= msgInst.getE02PSIBNK() %>">
	        <img src="<%=request.getContextPath()%>/images/Check.gif" alt="campo obligatorio" align="absbottom" border="0" width="16" height="20">
	      </td>
     	</tr>
     	<tr id=trclear> 
	      <td nowrap> 
	        <div align="right">Instrumento : </div>
	      </td>
	      <td nowrap>
	      	<input type="text" name="E02PSIINS" size="5" maxlength="4" value="<%= msgInst.getE02PSIINS() %>">
	      	<input type="text" name="E02PSIINA" size="35" maxlength="35" readonly value="<%= msgInst.getE02PSIINA() %>">
			<a href="javascript:GetCodeDescCNOFC('E02PSIINS','E02PSIINA','YJ')"><img src="<%=request.getContextPath()%>/images/1b.gif" alt="ayuda" align="absbottom" border="0"></a>
			<img src="<%=request.getContextPath()%>/images/Check.gif" alt="campo obligatorio" align="absbottom" border="0" width="16" height="20">
		  </td>
     	</tr>
     	<tr id=trdark> 
		  <td> 
		     <div align="right">Moneda : </div>        
		  </td>
      	  <td nowrap> 
      	    <input type="text" name="E02PSICCY" size="5" maxlength="3" value="<%= msgInst.getE02PSICCY() %>">
			<a href="javascript:GetCurrency('E02PSICCY',document.forms[0].E02PSIBNK.value)"><img src="<%=request.getContextPath()%>/images/1b.gif" alt="Ayuda" align="bottom" border="0"></a>
			<img src="<%=request.getContextPath()%>/images/Check.gif" alt="campo obligatorio" align="absbottom" border="0" width="16" height="20">
      	  </td>     
      	</tr>
      	<tr id=trclear>
          <td nowrap>
              <div align="right">Cliente : </div>
          </td>
          <td nowrap>
              <input type="text" name="E02PSICUS" size="10" maxlength="9" value="<%= msgInst.getE02PSICUS() %>">
              <input type="text" name="E02PSICUN" size="35" maxlength="35" readonly value="<%= msgInst.getE02PSICUN() %>"> 
              <a href="javascript:GetCustomerDescId('E02PSICUS','E02PSICUN',document.forms[0].E02PSIBNK.value)"><img src="<%=request.getContextPath()%>/images/1b.gif" alt="Ayuda" align="absbottom" border="0" ></a>
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
				<input type="text" name="E02PSILIC" size="5" maxlength="3" value="<%= msgInst.getE02PSILIC() %>"> 
				<a href="javascript:GetCurrency('E02PSILIC',document.forms[0].E02PSIBNK.value)"><img src="<%=request.getContextPath()%>/images/1b.gif" alt="Ayuda" align="bottom" border="0"></a>
				<img src="<%=request.getContextPath()%>/images/Check.gif" alt="campo obligatorio" align="absbottom" border="0" width="16" height="20">
			</td>
		</tr>
		<tr>
			<td nowrap>
				<div align="right">Indicativo de Corresponsal Requerido :</div>
			</td>
			<td nowrap>
				<input type="radio" name="E02PSIREC" value="1" <%if (msgInst.getE02PSIREC().equals("1")) {%> checked <%}%>>Si&nbsp;
				<input type="radio" name="E02PSIREC" value="0" <%if (msgInst.getE02PSIREC().equals("0")) {%> checked <%}%>>No
			</td>
		</tr>
		<tr>
			<td nowrap>
				<div align="right">Indicativo de Aprobacion Requerida :</div>
			</td>
			<td nowrap>
				<input type="radio" name="E02PSIREA" value="1" <%if (msgInst.getE02PSIREA().equals("1")) {%> checked <%}%>>Si&nbsp;
				<input type="radio" name="E02PSIREA" value="0" <%if (msgInst.getE02PSIREA().equals("0")) {%> checked <%}%>>No
			</td>
		</tr>
		<tr>
			<td nowrap>
				<div align="right">Cuenta de Contrapartida :</div>
			</td>
			<td nowrap>
				<input type="text" name="E02PSIGLN" size="18" maxlength="16" value="<%= msgInst.getE02PSIGLN() %>"> 
				<a href="javascript:GetLedger('E02PSIGLN',document.forms[0].E02PSIBNK.value,document.forms[0].E02CNTBCU.value,'')">
              	<img src="<%=request.getContextPath()%>/images/1b.gif" alt=". . ." align="absbottom" border="0" ></a>
              	<img src="<%=request.getContextPath()%>/images/Check.gif" alt="campo obligatorio" align="absbottom" border="0" width="16" height="20">
              	<input type="hidden" name="E02CNTBCU" size="5" maxlength="3" value="<%= userPO.getHeader20() %>"> 
			</td>
		</tr>
			<tr>
			<td nowrap>
				<div align="right">Cuenta Spot D&eacute;bito (Compras)  :</div>
			</td>
			<td nowrap>
				<input type="text" name="E02PSIGL1" size="18" maxlength="16" value="<%= msgInst.getE02PSIGL1() %>"> 
				<a href="javascript:GetLedger('E02PSIGL1',document.forms[0].E02PSIBNK.value,document.forms[0].E02CNTBCU.value,'')">
              	<img src="<%=request.getContextPath()%>/images/1b.gif" alt=". . ." align="absbottom" border="0" ></a>
              	<img src="<%=request.getContextPath()%>/images/Check.gif" alt="campo obligatorio" align="absbottom" border="0" width="16" height="20">
              	<input type="hidden" name="E02CNTBCU" size="5" maxlength="3" value="<%= msgInst.getE02CNTBCU() %>"> 
			</td>
		</tr>
		
		<tr>
			<td nowrap>
				<div align="right">Cuenta Spot Cr&eacute;dito (Compras)  :</div>
			</td>
			<td nowrap>
				<input type="text" name="E02PSIGL2" size="18" maxlength="16" value="<%= msgInst.getE02PSIGL2() %>"> 
				<a href="javascript:GetLedger('E02PSIGL2',document.forms[0].E02PSIBNK.value,document.forms[0].E02CNTBCU.value,'')">
              	<img src="<%=request.getContextPath()%>/images/1b.gif" alt=". . ." align="absbottom" border="0" ></a>
              	<img src="<%=request.getContextPath()%>/images/Check.gif" alt="campo obligatorio" align="absbottom" border="0" width="16" height="20">
              	<input type="hidden" name="E02CNTBCU" size="5" maxlength="3" value="<%= msgInst.getE02CNTBCU() %>"> 
			</td>
		</tr>
			<tr>
			<td nowrap>
				<div align="right">Cuenta Spot D&eacute;bito (Ventas) :</div>
			</td>
			<td nowrap>
				<input type="text" name="E02PSIGL3" size="18" maxlength="16" value="<%= msgInst.getE02PSIGL3() %>"> 
				<a href="javascript:GetLedger('E02PSIGL3',document.forms[0].E02PSIBNK.value,document.forms[0].E02CNTBCU.value,'')">
              	<img src="<%=request.getContextPath()%>/images/1b.gif" alt=". . ." align="absbottom" border="0" ></a>
              	<img src="<%=request.getContextPath()%>/images/Check.gif" alt="campo obligatorio" align="absbottom" border="0" width="16" height="20">
              	<input type="hidden" name="E02CNTBCU" size="5" maxlength="3" value="<%= msgInst.getE02CNTBCU() %>"> 
			</td>
		</tr>
		
		<tr>
			<td nowrap>
				<div align="right">Cuenta Spot Cr&eacute;dito (Ventas) :</div>
			</td>
			<td nowrap>
				<input type="text" name="E02PSIGL4" size="18" maxlength="16" value="<%= msgInst.getE02PSIGL4() %>"> 
				<a href="javascript:GetLedger('E02PSIGL4',document.forms[0].E02PSIBNK.value,document.forms[0].E02CNTBCU.value,'')">
              	<img src="<%=request.getContextPath()%>/images/1b.gif" alt=". . ." align="absbottom" border="0" ></a>
              	<img src="<%=request.getContextPath()%>/images/Check.gif" alt="campo obligatorio" align="absbottom" border="0" width="16" height="20">
              	<input type="hidden" name="E02CNTBCU" size="5" maxlength="3" value="<%= msgInst.getE02CNTBCU() %>"> 
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
			<td align="right">Precio</td>
			<td align="center">
				<input type="text" name="E02PSIPUR"	value="<%= msgInst.getE02PSIPUR() %>" onkeypress="enterDecimal()">
			</td>
			<td align="center">
				<input type="text" name="E02PSISAL"	value="<%= msgInst.getE02PSISAL() %>" onkeypress="enterDecimal()">
			</td>
		</tr>
		<tr>
			<td align="right">Limite Minimo</td>
			<td align="center">
				<input type="text" name="E02PSIPLL"	value="<%= msgInst.getE02PSIPLL() %>" onkeypress="enterDecimal()">
			</td>
			<td align="center">
				<input type="text" name="E02PSISLL"	value="<%= msgInst.getE02PSISLL() %>" onkeypress="enterDecimal()">
			</td>
		</tr>
		<tr>
			<td align="right">Limite Maximo</td>
			<td align="center">
				<input type="text" name="E02PSIPML"	value="<%= msgInst.getE02PSIPML() %>" onkeypress="enterDecimal()">
			</td>
			<td align="center">
				<input type="text" name="E02PSISML"	value="<%= msgInst.getE02PSISML() %>" onkeypress="enterDecimal()">
			</td>
		</tr>
		<tr>
			<td align="right">Limite Maximo por Cliente Dia</td>
			<td align="center">
				<input type="text" name="E02PSIPMD"	value="<%= msgInst.getE02PSIPMD() %>" onkeypress="enterDecimal()">
			</td>
			<td align="center">
				<input type="text" name="E02PSISMD"	value="<%= msgInst.getE02PSISMD() %>" onkeypress="enterDecimal()">
			</td>
		</tr>
		<tr>
			<td align="right">Limite Maximo por Cliente Mes</td>
			<td align="center">
				<input type="text" name="E02PSIPMM"	value="<%= msgInst.getE02PSIPMM() %>" onkeypress="enterDecimal()">
			</td>
			<td align="center">
				<input type="text" name="E02PSISMM"	value="<%= msgInst.getE02PSISMM() %>" onkeypress="enterDecimal()">
			</td>
		</tr>
		<tr>
			<td align="right">Limite Maximo por Cliente Semestre</td>
			<td align="center">
				<input type="text" name="E02PSIPMS"	value="<%= msgInst.getE02PSIPMS() %>" onkeypress="enterDecimal()">
			</td>
			<td align="center">
				<input type="text" name="E02PSISMS"	value="<%= msgInst.getE02PSISMS() %>" onkeypress="enterDecimal()">
			</td>
		</tr>
		<tr>
			<td align="right">Limite Maximo por Cliente Año</td>
			<td align="center">
				<input type="text" name="E02PSIPMY"	value="<%= msgInst.getE02PSIPMY() %>" onkeypress="enterDecimal()">
			</td>
			<td align="center">
				<input type="text" name="E02PSISMY"	value="<%= msgInst.getE02PSISMY() %>" onkeypress="enterDecimal()">
			</td>
		</tr>
		<tr>
			<td align="right">Estado</td>
			<td align="center">
				<input type="radio" name="E02PSIPST" value="0" <%if (msgInst.getE02PSIPST().equals("0")) {%> checked <%}%>>Cerrado&nbsp;
				<input type="radio" name="E02PSIPST" value="1" <%if (msgInst.getE02PSIPST().equals("1")) {%> checked <%}%>>Abierto
			</td>
			<td align="center">
				<input type="radio" name="E02PSISST" value="0" <%if (msgInst.getE02PSISST().equals("0")) {%> checked <%}%>>Cerrado&nbsp;
				<input type="radio" name="E02PSISST" value="1" <%if (msgInst.getE02PSISST().equals("1")) {%> checked <%}%>>Abierto
			</td>
		</tr>
		<tr>
			<td align="right">Tiempo Limite de Liquidacion (seg)</td>
			<td align="center">
				<input type="text" name="E02PSIPTI"	value="<%= msgInst.getE02PSIPTI() %>" onkeypress="enterInteger()">
			</td>
			<td align="center">
				<input type="text" name="E02PSISTI" value="<%= msgInst.getE02PSISTI() %>" onkeypress="enterInteger()">
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
