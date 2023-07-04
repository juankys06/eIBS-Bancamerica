<html>  
<head>
<title>Compraventa Moneda Extranjera</title>
<META HTTP-EQUIV="Content-Type" CONTENT="text/html; charset=ISO-8859-1">
<link Href="<%=request.getContextPath()%>/pages/style.css" rel="stylesheet">
<%@ page import ="datapro.eibs.master.Util" %>

<script language="Javascript1.1" src="<%=request.getContextPath()%>/pages/s/javascripts/eIBS.jsp"> </SCRIPT>

<jsp:useBean id= "mtList" 	class= "datapro.eibs.beans.JBObjList"  		scope="session" />
<jsp:useBean id= "error" 	class= "datapro.eibs.beans.ELEERRMessage"  	scope="session" />
<jsp:useBean id= "userPO" 	class= "datapro.eibs.beans.UserPos"  		scope="session"/>

<% 
int row = 0;
try { row = Integer.parseInt(request.getParameter("ROW"));} catch (Exception e) {}
mtList.setCurrentRow(row);
datapro.eibs.beans.EPR030501Message msgInst = (datapro.eibs.beans.EPR030501Message) mtList.getRecord();

if ( !error.getERRNUM().equals("0")  ) {
      error.setERRNUM("0");
%>
<SCRIPT Language="Javascript">
        showErrors();
</SCRIPT>
<%}%>

</head>
<body>

<H3 align="center">Mantenimiento de Instrumentos de Moneda Extranjera<img src="<%=request.getContextPath()%>/images/e_ibs.gif" align="left" name="EIBS_GIF" ALT="foreign_currency_exchange_maint, EPR0305"></H3>
<hr size="4">


<form method="post" action="<%=request.getContextPath()%>/servlet/datapro.eibs.forex.JSEPR0305">
 
  <INPUT TYPE=HIDDEN NAME="SCREEN" VALUE="600">
  <INPUT TYPE=HIDDEN NAME="ROW" VALUE="<%= row %>">
  
  <table class="tableinfo">
   <tr> 
   <td>
    <table cellspacing=0 cellpadding=2 width="100%" border="0">    
     	<tr id=trdark> 
	      <td nowrap width="40%"> 
	        <div align="right">Banco : </div>
	      </td>
	      <td nowrap width="60%"> 
	        <input type="text" name="E01PSIBNK" readonly size="3" maxlength="2" value="<%= msgInst.getE01PSIBNK() %>">
	      </td>
     	</tr>
     	<tr id=trclear> 
	      <td nowrap> 
	        <div align="right">Instrumento : </div>
	      </td>
	      <td nowrap>
	      	<input type="text" name="E01PSIINS" readonly size="5" maxlength="4" value="<%= msgInst.getE01PSIINS() %>">
	      	<input type="text" name="E01PSIINA" readonly size="35" maxlength="35" readonly value="<%= msgInst.getE01PSIINA() %>">
		  </td>
     	</tr>
     	<tr id=trdark> 
		  <td> 
		     <div align="right">Moneda : </div>        
		  </td>
      	  <td nowrap> 
      	    <input type="text" name="E01PSICCY" readonly size="5" maxlength="3" value="<%= msgInst.getE01PSICCY() %>">
      	  </td>     
      	</tr>
      	<tr id=trclear>
          <td nowrap>
              <div align="right">Cliente : </div>
          </td>
          <td nowrap>
              <input type="text" name="E01PSICUS" readonly size="10" maxlength="9" value="<%= msgInst.getE01PSICUS() %>">
              <input type="text" name="E01PSICUN" readonly size="35" maxlength="35" readonly value="<%= msgInst.getE01PSICUN() %>"> 
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
				<input type="text" name="E01PSILIC" size="5" maxlength="3" value="<%= msgInst.getE01PSILIC() %>"> 
				<a href="javascript:GetCurrency('E01PSILIC',document.forms[0].E01PSIBNK.value)"><img src="<%=request.getContextPath()%>/images/1b.gif" alt="Ayuda" align="bottom" border="0"></a>
				<img src="<%=request.getContextPath()%>/images/Check.gif" alt="campo obligatorio" align="absbottom" border="0" width="16" height="20">
			</td>
		</tr>
		<tr>
			<td nowrap>
				<div align="right">Indicativo de Corresponsal Requerido :</div>
			</td>
			<td nowrap>
				<input type="radio" name="E01PSIREC" value="1" <%if (msgInst.getE01PSIREC().equals("1")) {%> checked <%}%>>Si&nbsp;
				<input type="radio" name="E01PSIREC" value="0" <%if (msgInst.getE01PSIREC().equals("0")) {%> checked <%}%>>No
			</td>
		</tr>
		<tr>
			<td nowrap>
				<div align="right">Indicativo de Aprobacion Requerida :</div>
			</td>
			<td nowrap>
				<input type="radio" name="E01PSIREA" value="1" <%if (msgInst.getE01PSIREA().equals("1")) {%> checked <%}%>>Si&nbsp;
				<input type="radio" name="E01PSIREA" value="0" <%if (msgInst.getE01PSIREA().equals("0")) {%> checked <%}%>>No
			</td>
		</tr>
		<tr>
			<td nowrap>
				<div align="right">Cuenta de Contrapartida :</div>
			</td>
			<td nowrap>
				<input type="text" name="E01PSIGLN" size="18" maxlength="16" value="<%= msgInst.getE01PSIGLN() %>"> 
				<a href="javascript:GetLedger('E01PSIGLN',document.forms[0].E01PSIBNK.value,document.forms[0].E01CNTBCU.value,'')">
              	<img src="<%=request.getContextPath()%>/images/1b.gif" alt=". . ." align="absbottom" border="0" ></a>
              	<img src="<%=request.getContextPath()%>/images/Check.gif" alt="campo obligatorio" align="absbottom" border="0" width="16" height="20">
              	<input type="hidden" name="E01CNTBCU" size="5" maxlength="3" value="<%= msgInst.getE01CNTBCU() %>"> 
			</td>
		</tr>
		<tr>
			<td nowrap>
				<div align="right">Cuenta Spot D&eacute;bito (Compras) :</div>
			</td>
			<td nowrap>
				<input type="text" name="E01PSIGL1" size="18" maxlength="16" value="<%= msgInst.getE01PSIGL1() %>"> 
				<a href="javascript:GetLedger('E01PSIGL1',document.forms[0].E01PSIBNK.value,document.forms[0].E01CNTBCU.value,'')">
              	<img src="<%=request.getContextPath()%>/images/1b.gif" alt=". . ." align="absbottom" border="0" ></a>
              	<img src="<%=request.getContextPath()%>/images/Check.gif" alt="campo obligatorio" align="absbottom" border="0" width="16" height="20">
              	<input type="hidden" name="E01CNTBCU" size="5" maxlength="3" value="<%= msgInst.getE01CNTBCU() %>"> 
			</td>
		</tr>
		
		<tr>
			<td nowrap>
				<div align="right">Cuenta Spot Cr&eacute;dito (Compras) :</div>
			</td>
			<td nowrap>
				<input type="text" name="E01PSIGL2" size="18" maxlength="16" value="<%= msgInst.getE01PSIGL2() %>"> 
				<a href="javascript:GetLedger('E01PSIGL2',document.forms[0].E01PSIBNK.value,document.forms[0].E01CNTBCU.value,'')">
              	<img src="<%=request.getContextPath()%>/images/1b.gif" alt=". . ." align="absbottom" border="0" ></a>
              	<img src="<%=request.getContextPath()%>/images/Check.gif" alt="campo obligatorio" align="absbottom" border="0" width="16" height="20">
              	<input type="hidden" name="E01CNTBCU" size="5" maxlength="3" value="<%= msgInst.getE01CNTBCU() %>"> 
			</td>
		</tr>
		
		<tr>
			<td nowrap>
				<div align="right">Cuenta Spot D&eacute;bito (Ventas) :</div>
			</td>
			<td nowrap>
				<input type="text" name="E01PSIGL3" size="18" maxlength="16" value="<%= msgInst.getE01PSIGL3() %>"> 
				<a href="javascript:GetLedger('E01PSIGL3',document.forms[0].E01PSIBNK.value,document.forms[0].E01CNTBCU.value,'')">
              	<img src="<%=request.getContextPath()%>/images/1b.gif" alt=". . ." align="absbottom" border="0" ></a>
              	<img src="<%=request.getContextPath()%>/images/Check.gif" alt="campo obligatorio" align="absbottom" border="0" width="16" height="20">
              	<input type="hidden" name="E01CNTBCU" size="5" maxlength="3" value="<%= msgInst.getE01CNTBCU() %>"> 
			</td>
		</tr>
		
		<tr>
			<td nowrap>
				<div align="right">Cuenta Spot Cr&eacute;dito (Ventas) :</div>
			</td>
			<td nowrap>
				<input type="text" name="E01PSIGL4" size="18" maxlength="16" value="<%= msgInst.getE01PSIGL4() %>"> 
				<a href="javascript:GetLedger('E01PSIGL4',document.forms[0].E01PSIBNK.value,document.forms[0].E01CNTBCU.value,'')">
              	<img src="<%=request.getContextPath()%>/images/1b.gif" alt=". . ." align="absbottom" border="0" ></a>
              	<img src="<%=request.getContextPath()%>/images/Check.gif" alt="campo obligatorio" align="absbottom" border="0" width="16" height="20">
              	<input type="hidden" name="E01CNTBCU" size="5" maxlength="3" value="<%= msgInst.getE01CNTBCU() %>"> 
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
				<input type="text" name="E01PSIPUR"	value="<%= msgInst.getE01PSIPUR() %>" onkeypress="enterDecimal()">
			</td>
			<td align="center">
				<input type="text" name="E01PSISAL"	value="<%= msgInst.getE01PSISAL() %>" onkeypress="enterDecimal()">
			</td>
		</tr>
		<tr>
			<td align="right">Limite Minimo</td>
			<td align="center">
				<input type="text" name="E01PSIPLL"	value="<%= msgInst.getE01PSIPLL() %>" onkeypress="enterDecimal()">
			</td>
			<td align="center">
				<input type="text" name="E01PSISLL"	value="<%= msgInst.getE01PSISLL() %>" onkeypress="enterDecimal()">
			</td>
		</tr>
		<tr>
			<td align="right">Limite Maximo</td>
			<td align="center">
				<input type="text" name="E01PSIPML"	value="<%= msgInst.getE01PSIPML() %>" onkeypress="enterDecimal()">
			</td>
			<td align="center">
				<input type="text" name="E01PSISML"	value="<%= msgInst.getE01PSISML() %>" onkeypress="enterDecimal()">
			</td>
		</tr>
		<tr>
			<td align="right">Limite Maximo por Cliente Dia</td>
			<td align="center">
				<input type="text" name="E01PSIPMD"	value="<%= msgInst.getE01PSIPMD() %>" onkeypress="enterDecimal()">
			</td>
			<td align="center">
				<input type="text" name="E01PSISMD"	value="<%= msgInst.getE01PSISMD() %>" onkeypress="enterDecimal()">
			</td>
		</tr>
		<tr>
			<td align="right">Limite Maximo por Cliente Mes</td>
			<td align="center">
				<input type="text" name="E01PSIPMM"	value="<%= msgInst.getE01PSIPMM() %>" onkeypress="enterDecimal()">
			</td>
			<td align="center">
				<input type="text" name="E01PSISMM"	value="<%= msgInst.getE01PSISMM() %>" onkeypress="enterDecimal()">
			</td>
		</tr>
		<tr>
			<td align="right">Limite Maximo por Cliente Semestre</td>
			<td align="center">
				<input type="text" name="E01PSIPMS"	value="<%= msgInst.getE01PSIPMS() %>" onkeypress="enterDecimal()">
			</td>
			<td align="center">
				<input type="text" name="E01PSISMS"	value="<%= msgInst.getE01PSISMS() %>" onkeypress="enterDecimal()">
			</td>
		</tr>
		<tr>
			<td align="right">Limite Maximo por Cliente Ano</td>
			<td align="center">
				<input type="text" name="E01PSIPMY"	value="<%= msgInst.getE01PSIPMY() %>" onkeypress="enterDecimal()">
			</td>
			<td align="center">
				<input type="text" name="E01PSISMY"	value="<%= msgInst.getE01PSISMY() %>" onkeypress="enterDecimal()">
			</td>
		</tr>
		<tr>
			<td align="right">Estado</td>
			<td align="center">
				<input type="radio" name="E01PSIPST" value="0" <%if (msgInst.getE01PSIPST().equals("0")) {%> checked <%}%>>Cerrado&nbsp;
				<input type="radio" name="E01PSIPST" value="1" <%if (msgInst.getE01PSIPST().equals("1")) {%> checked <%}%>>Abierto
			</td>
			<td align="center">
				<input type="radio" name="E01PSISST" value="0" <%if (msgInst.getE01PSISST().equals("0")) {%> checked <%}%>>Cerrado&nbsp;
				<input type="radio" name="E01PSISST" value="1" <%if (msgInst.getE01PSISST().equals("1")) {%> checked <%}%>>Abierto
			</td>
		</tr>
		<tr>
			<td align="right">Tiempo Limite de Liquidacion (seg)</td>
			<td align="center">
				<input type="text" name="E01PSIPTI"	value="<%= msgInst.getE01PSIPTI() %>" onkeypress="enterInteger()">
			</td>
			<td align="center">
				<input type="text" name="E01PSISTI" value="<%= msgInst.getE01PSISTI() %>" onkeypress="enterInteger()">
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
