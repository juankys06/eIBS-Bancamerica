<html> 
<head>
<title>Compraventa Moneda Extranjera</title>
<META HTTP-EQUIV="Content-Type" CONTENT="text/html; charset=ISO-8859-1">
<link Href="<%=request.getContextPath()%>/pages/style.css" rel="stylesheet">
<%@ page import ="datapro.eibs.master.Util" %>

<script language="Javascript1.1" src="<%=request.getContextPath()%>/pages/s/javascripts/eIBS.jsp"> </SCRIPT>

<jsp:useBean id= "msgInst" 	class= "datapro.eibs.beans.EPR032002Message"  	scope="session" />
<jsp:useBean id= "error" 	class= "datapro.eibs.beans.ELEERRMessage"  		scope="session" />
<jsp:useBean id= "userPO" 	class= "datapro.eibs.beans.UserPos"  			scope="session"/>

<%
int NEW = 0;
try { NEW = Integer.parseInt(request.getParameter("NEW"));} catch (Exception e) {}
if (NEW == 1) {
msgInst.destroy();
}
 
if (msgInst == null) {
 	msgInst = new datapro.eibs.beans.EPR032002Message();   
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

<H3 align="center">Mantenimiento de Comisiones de Moneda Extranjera<img src="<%=request.getContextPath()%>/images/e_ibs.gif" align="left" name="EIBS_GIF" ALT="foreign_currency_exchange_new, EPR0320"></H3>
<hr size="4">

<form method="post" action="<%=request.getContextPath()%>/servlet/datapro.eibs.forex.JSEPR0320">
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
	        <input type="text" name="E02COMBNK" size="3" maxlength="2" value="<%= msgInst.getE02COMBNK() %>">
	        <img src="<%=request.getContextPath()%>/images/Check.gif" alt="campo obligatorio" align="absbottom" border="0" width="16" height="20">
	      </td>
     	</tr>
     	<tr id=trclear> 
	      <td nowrap> 
	        <div align="right">Instrumento : </div>
	      </td>
	      <td nowrap>
	      	<input type="text" name="E02COMINS" size="5" maxlength="4" value="<%= msgInst.getE02COMINS() %>">
	      	<input type="text" name="E02COMINA" size="35" maxlength="35" readonly value="<%= msgInst.getE02COMINA() %>">
			<a href="javascript:GetCodeDescCNOFC('E02COMINS','E02COMINA','YJ')"><img src="<%=request.getContextPath()%>/images/1b.gif" alt="ayuda" align="absbottom" border="0"></a>
			<img src="<%=request.getContextPath()%>/images/Check.gif" alt="campo obligatorio" align="absbottom" border="0" width="16" height="20">
		  </td>
     	</tr>
     	<tr id=trdark> 
		  <td> 
		     <div align="right">Moneda : </div>        
		  </td>
      	  <td nowrap> 
      	    <input type="text" name="E02COMCCY" size="5" maxlength="3" value="<%= msgInst.getE02COMCCY() %>">
			<a href="javascript:GetCurrency('E02COMCCY',document.forms[0].E02COMBNK.value)"><img src="<%=request.getContextPath()%>/images/1b.gif" alt="Ayuda" align="bottom" border="0"></a>
			<img src="<%=request.getContextPath()%>/images/Check.gif" alt="campo obligatorio" align="absbottom" border="0" width="16" height="20">
      	  </td>     
      	</tr>
      	<tr id=trclear>
          <td nowrap>
              <div align="right">Cliente : </div>
          </td>
          <td nowrap>
              <input type="text" name="E02COMCUS" size="10" maxlength="9" value="<%= msgInst.getE02COMCUS() %>">
              <input type="text" name="E02COMCUN" size="35" maxlength="35" readonly value="<%= msgInst.getE02COMCUN() %>"> 
              <a href="javascript:GetCustomerDescId('E02COMCUS','E02COMCUN','')"><img src="<%=request.getContextPath()%>/images/1b.gif" alt="Ayuda" align="absbottom" border="0" ></a>
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
				<div align="right">Codigo de Moneda de Comisiones :</div>
			</td>
			<td nowrap width="60%">
				<input type="text" name="E02COMCUR" size="5" maxlength="3" value="<%= msgInst.getE02COMCUR() %>"> 
				<a href="javascript:GetCurrency('E02COMCUR',document.forms[0].E02COMBNK.value)"><img src="<%=request.getContextPath()%>/images/1b.gif" alt="Ayuda" align="bottom" border="0"></a>
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
			<td align="center">Descripcion</td>
			<td align="center">Tipo</td>
			<td align="center">Operacion</td>
			<td align="center">Persona</td>
			<td align="center">Valor</td>
			<td align="center">Minimo</td>
			<td align="center">Maximo</td>
			<td align="center">Cuenta</td>
		</tr>
		<tr>
			<td>
				<input type="text" name="E02COMD01" size="35" maxlength="35" value="<%= msgInst.getE02COMD01() %>">
			</td>
			<td>
				<select name="E02COMT01">
					<option value="" <%if (msgInst.getE02COMT01().equals("")) { out.print("selected"); }%>></option>
                	<option value="F" <%if (msgInst.getE02COMT01().equals("F")) { out.print("selected"); }%>>Fija</option>
                	<option value="P" <%if (msgInst.getE02COMT01().equals("P")) { out.print("selected"); }%>>Porcentual</option>
              	</select> 
			</td>
			<td>
				<select name="E02COMO01">
					<option value="" <%if (msgInst.getE02COMO01().equals("")) { out.print("selected"); }%>></option>
                	<option value="P" <%if (msgInst.getE02COMO01().equals("P")) { out.print("selected"); }%>>Compra</option>
                	<option value="S" <%if (msgInst.getE02COMO01().equals("S")) { out.print("selected"); }%>>Venta</option>
                	<option value="C" <%if (msgInst.getE02COMO01().equals("C")) { out.print("selected"); }%>>Canje</option>
                	<option value="T" <%if (msgInst.getE02COMO01().equals("T")) { out.print("selected"); }%>>Todas</option>
              	</select> 
			</td>
			<td>
				<select name="E02COMP01">
					<option value="" <%if (msgInst.getE02COMP01().equals("")) { out.print("selected"); }%>></option>
                	<option value="1" <%if (msgInst.getE02COMP01().equals("1")) { out.print("selected"); }%>>Jur</option>
                	<option value="2" <%if (msgInst.getE02COMP01().equals("2")) { out.print("selected"); }%>>Nat</option>
                	<option value="3" <%if (msgInst.getE02COMP01().equals("3")) { out.print("selected"); }%>>Todas</option>
              	</select> 
			</td>
			<td>
				<input type="text" name="E02COMA01"	size="12" maxlength="15" value="<%= msgInst.getE02COMA01() %>" onkeypress="enterDecimal()">
			</td>
			<td>
				<input type="text" name="E02COMI01"	size="12" maxlength="15" value="<%= msgInst.getE02COMI01() %>" onkeypress="enterDecimal()">
			</td>
			<td>
				<input type="text" name="E02COMM01"	size="12" maxlength="15" value="<%= msgInst.getE02COMM01() %>" onkeypress="enterDecimal()">
			</td>
			<td nowrap>
				<input type="text" name="E02COMC01" size="18" maxlength="16" value="<%= msgInst.getE02COMC01() %>"> 
				<a href="javascript:GetLedger('E02COMC01',document.forms[0].E02COMBNK.value,document.forms[0].E02CNTBCU.value,'')">
              	<img src="<%=request.getContextPath()%>/images/1b.gif" alt=". . ." align="absbottom" border="0" ></a>
              	<input type="hidden" name="E02CNTBCU" size="5" maxlength="3" value="<%= userPO.getHeader20() %>">  
			</td>
		</tr>
		<tr>
			<td>
				<input type="text" name="E02COMD02" size="35" maxlength="35" value="<%= msgInst.getE02COMD02() %>">
			</td>
			<td>
				<select name="E02COMT02">
					<option value="" <%if (msgInst.getE02COMT02().equals("")) { out.print("selected"); }%>></option>
                	<option value="F" <%if (msgInst.getE02COMT02().equals("F")) { out.print("selected"); }%>>Fija</option>
                	<option value="P" <%if (msgInst.getE02COMT02().equals("P")) { out.print("selected"); }%>>Porcentual</option>
              	</select> 
			</td>
			<td>
				<select name="E02COMO02">
					<option value="" <%if (msgInst.getE02COMO02().equals("")) { out.print("selected"); }%>></option>
                	<option value="P" <%if (msgInst.getE02COMO02().equals("P")) { out.print("selected"); }%>>Compra</option>
                	<option value="S" <%if (msgInst.getE02COMO02().equals("S")) { out.print("selected"); }%>>Venta</option>
                	<option value="C" <%if (msgInst.getE02COMO02().equals("C")) { out.print("selected"); }%>>Canje</option>
                	<option value="T" <%if (msgInst.getE02COMO02().equals("T")) { out.print("selected"); }%>>Todas</option>
              	</select> 
			</td>
			<td>
				<select name="E02COMP02">
					<option value="" <%if (msgInst.getE02COMP02().equals("")) { out.print("selected"); }%>></option>
                	<option value="1" <%if (msgInst.getE02COMP02().equals("1")) { out.print("selected"); }%>>Jur</option>
                	<option value="2" <%if (msgInst.getE02COMP02().equals("2")) { out.print("selected"); }%>>Nat</option>
                	<option value="3" <%if (msgInst.getE02COMP02().equals("3")) { out.print("selected"); }%>>Todas</option>
              	</select> 
			</td>
			<td>
				<input type="text" name="E02COMA02"	size="12" maxlength="15" value="<%= msgInst.getE02COMA02() %>" onkeypress="enterDecimal()">
			</td>
			<td>
				<input type="text" name="E02COMI02"	size="12" maxlength="15" value="<%= msgInst.getE02COMI02() %>" onkeypress="enterDecimal()">
			</td>
			<td>
				<input type="text" name="E02COMM02"	size="12" maxlength="15" value="<%= msgInst.getE02COMM02() %>" onkeypress="enterDecimal()">
			</td>
			<td nowrap>
				<input type="text" name="E02COMC02" size="18" maxlength="16" value="<%= msgInst.getE02COMC02() %>"> 
				<a href="javascript:GetLedger('E02COMC02',document.forms[0].E02COMBNK.value,document.forms[0].E02CNTBCU.value,'')">
              	<img src="<%=request.getContextPath()%>/images/1b.gif" alt=". . ." align="absbottom" border="0" ></a> 
			</td>
		</tr>
		<tr>
			<td>
				<input type="text" name="E02COMD03" size="35" maxlength="35" value="<%= msgInst.getE02COMD03() %>">
			</td>
			<td>
				<select name="E02COMT03">
					<option value="" <%if (msgInst.getE02COMT03().equals("")) { out.print("selected"); }%>></option>
                	<option value="F" <%if (msgInst.getE02COMT03().equals("F")) { out.print("selected"); }%>>Fija</option>
                	<option value="P" <%if (msgInst.getE02COMT03().equals("P")) { out.print("selected"); }%>>Porcentual</option>
              	</select> 
			</td>
			<td>
				<select name="E02COMO03">
					<option value="" <%if (msgInst.getE02COMO03().equals("")) { out.print("selected"); }%>></option>
                	<option value="P" <%if (msgInst.getE02COMO03().equals("P")) { out.print("selected"); }%>>Compra</option>
                	<option value="S" <%if (msgInst.getE02COMO03().equals("S")) { out.print("selected"); }%>>Venta</option>
                	<option value="C" <%if (msgInst.getE02COMO03().equals("C")) { out.print("selected"); }%>>Canje</option>
                	<option value="T" <%if (msgInst.getE02COMO03().equals("T")) { out.print("selected"); }%>>Todas</option>
              	</select> 
			</td>
			<td>
				<select name="E02COMP03">
					<option value="" <%if (msgInst.getE02COMP03().equals("")) { out.print("selected"); }%>></option>
                	<option value="1" <%if (msgInst.getE02COMP03().equals("1")) { out.print("selected"); }%>>Jur</option>
                	<option value="2" <%if (msgInst.getE02COMP03().equals("2")) { out.print("selected"); }%>>Nat</option>
                	<option value="3" <%if (msgInst.getE02COMP03().equals("3")) { out.print("selected"); }%>>Todas</option>
              	</select> 
			</td>
			<td>
				<input type="text" name="E02COMA03"	size="12" maxlength="15" value="<%= msgInst.getE02COMA03() %>" onkeypress="enterDecimal()">
			</td>
			<td>
				<input type="text" name="E02COMI03"	size="12" maxlength="15" value="<%= msgInst.getE02COMI03() %>" onkeypress="enterDecimal()">
			</td>
			<td>
				<input type="text" name="E02COMM03"	size="12" maxlength="15" value="<%= msgInst.getE02COMM03() %>" onkeypress="enterDecimal()">
			</td>
			<td nowrap>
				<input type="text" name="E02COMC03" size="18" maxlength="16" value="<%= msgInst.getE02COMC03() %>"> 
				<a href="javascript:GetLedger('E02COMC03',document.forms[0].E02COMBNK.value,document.forms[0].E02CNTBCU.value,'')">
              	<img src="<%=request.getContextPath()%>/images/1b.gif" alt=". . ." align="absbottom" border="0" ></a> 
			</td>
		</tr>
		<tr>
			<td>
				<input type="text" name="E02COMD04" size="35" maxlength="35" value="<%= msgInst.getE02COMD04() %>">
			</td>
			<td>
				<select name="E02COMT04">
					<option value="" <%if (msgInst.getE02COMT04().equals("")) { out.print("selected"); }%>></option>
                	<option value="F" <%if (msgInst.getE02COMT04().equals("F")) { out.print("selected"); }%>>Fija</option>
                	<option value="P" <%if (msgInst.getE02COMT04().equals("P")) { out.print("selected"); }%>>Porcentual</option>
              	</select> 
			</td>
			<td>
				<select name="E02COMO04">
					<option value="" <%if (msgInst.getE02COMO04().equals("")) { out.print("selected"); }%>></option>
                	<option value="P" <%if (msgInst.getE02COMO04().equals("P")) { out.print("selected"); }%>>Compra</option>
                	<option value="S" <%if (msgInst.getE02COMO04().equals("S")) { out.print("selected"); }%>>Venta</option>
                	<option value="C" <%if (msgInst.getE02COMO04().equals("C")) { out.print("selected"); }%>>Canje</option>
                	<option value="T" <%if (msgInst.getE02COMO04().equals("T")) { out.print("selected"); }%>>Todas</option>
              	</select> 
			</td>
			<td>
				<select name="E02COMP04">
					<option value="" <%if (msgInst.getE02COMP04().equals("")) { out.print("selected"); }%>></option>
                	<option value="1" <%if (msgInst.getE02COMP04().equals("1")) { out.print("selected"); }%>>Jur</option>
                	<option value="2" <%if (msgInst.getE02COMP04().equals("2")) { out.print("selected"); }%>>Nat</option>
                	<option value="3" <%if (msgInst.getE02COMP04().equals("3")) { out.print("selected"); }%>>Todas</option>
              	</select> 
			</td>
			<td>
				<input type="text" name="E02COMA04"	size="12" maxlength="15" value="<%= msgInst.getE02COMA04() %>" onkeypress="enterDecimal()">
			</td>
			<td>
				<input type="text" name="E02COMI04"	size="12" maxlength="15" value="<%= msgInst.getE02COMI04() %>" onkeypress="enterDecimal()">
			</td>
			<td>
				<input type="text" name="E02COMM04"	size="12" maxlength="15" value="<%= msgInst.getE02COMM04() %>" onkeypress="enterDecimal()">
			</td>
			<td nowrap>
				<input type="text" name="E02COMC04" size="18" maxlength="16" value="<%= msgInst.getE02COMC04() %>"> 
				<a href="javascript:GetLedger('E02COMC04',document.forms[0].E02COMBNK.value,document.forms[0].E02CNTBCU.value,'')">
              	<img src="<%=request.getContextPath()%>/images/1b.gif" alt=". . ." align="absbottom" border="0" ></a> 
			</td>
		</tr>
		<tr>
			<td>
				<input type="text" name="E02COMD05" size="35" maxlength="35" value="<%= msgInst.getE02COMD05() %>">
			</td>
			<td>
				<select name="E02COMT05">
					<option value="" <%if (msgInst.getE02COMT05().equals("")) { out.print("selected"); }%>></option>
                	<option value="F" <%if (msgInst.getE02COMT05().equals("F")) { out.print("selected"); }%>>Fija</option>
                	<option value="P" <%if (msgInst.getE02COMT05().equals("P")) { out.print("selected"); }%>>Porcentual</option>
              	</select> 
			</td>
			<td>
				<select name="E02COMO05">
					<option value="" <%if (msgInst.getE02COMO05().equals("")) { out.print("selected"); }%>></option>
                	<option value="P" <%if (msgInst.getE02COMO05().equals("P")) { out.print("selected"); }%>>Compra</option>
                	<option value="S" <%if (msgInst.getE02COMO05().equals("S")) { out.print("selected"); }%>>Venta</option>
                	<option value="C" <%if (msgInst.getE02COMO05().equals("C")) { out.print("selected"); }%>>Canje</option>
                	<option value="T" <%if (msgInst.getE02COMO05().equals("T")) { out.print("selected"); }%>>Todas</option>
              	</select> 
			</td>
			<td>
				<select name="E02COMP05">
					<option value="" <%if (msgInst.getE02COMP05().equals("")) { out.print("selected"); }%>></option>
                	<option value="1" <%if (msgInst.getE02COMP05().equals("1")) { out.print("selected"); }%>>Jur</option>
                	<option value="2" <%if (msgInst.getE02COMP05().equals("2")) { out.print("selected"); }%>>Nat</option>
                	<option value="3" <%if (msgInst.getE02COMP05().equals("3")) { out.print("selected"); }%>>Todas</option>
              	</select> 
			</td>
			<td>
				<input type="text" name="E02COMA05"	size="12" maxlength="15" value="<%= msgInst.getE02COMA05() %>" onkeypress="enterDecimal()">
			</td>
			<td>
				<input type="text" name="E02COMI05"	size="12" maxlength="15" value="<%= msgInst.getE02COMI05() %>" onkeypress="enterDecimal()">
			</td>
			<td>
				<input type="text" name="E02COMM05"	size="12" maxlength="15" value="<%= msgInst.getE02COMM05() %>" onkeypress="enterDecimal()">
			</td>
			<td nowrap>
				<input type="text" name="E02COMC05" size="18" maxlength="16" value="<%= msgInst.getE02COMC05() %>"> 
				<a href="javascript:GetLedger('E02COMC05',document.forms[0].E02COMBNK.value,document.forms[0].E02CNTBCU.value,'')">
              	<img src="<%=request.getContextPath()%>/images/1b.gif" alt=". . ." align="absbottom" border="0" ></a> 
			</td>
		</tr>
		<tr>
			<td>
				<input type="text" name="E02COMD06" size="35" maxlength="35" value="<%= msgInst.getE02COMD06() %>">
			</td>
			<td>
				<select name="E02COMT06">
					<option value="" <%if (msgInst.getE02COMT06().equals("")) { out.print("selected"); }%>></option>
                	<option value="F" <%if (msgInst.getE02COMT06().equals("F")) { out.print("selected"); }%>>Fija</option>
                	<option value="P" <%if (msgInst.getE02COMT06().equals("P")) { out.print("selected"); }%>>Porcentual</option>
              	</select> 
			</td>
			<td>
				<select name="E02COMO06">
					<option value="" <%if (msgInst.getE02COMO06().equals("")) { out.print("selected"); }%>></option>
                	<option value="P" <%if (msgInst.getE02COMO06().equals("P")) { out.print("selected"); }%>>Compra</option>
                	<option value="S" <%if (msgInst.getE02COMO06().equals("S")) { out.print("selected"); }%>>Venta</option>
                	<option value="C" <%if (msgInst.getE02COMO06().equals("C")) { out.print("selected"); }%>>Canje</option>
                	<option value="T" <%if (msgInst.getE02COMO06().equals("T")) { out.print("selected"); }%>>Todas</option>
              	</select> 
			</td>
			<td>
				<select name="E02COMP06">
					<option value="" <%if (msgInst.getE02COMP06().equals("")) { out.print("selected"); }%>></option>
                	<option value="1" <%if (msgInst.getE02COMP06().equals("1")) { out.print("selected"); }%>>Jur</option>
                	<option value="2" <%if (msgInst.getE02COMP06().equals("2")) { out.print("selected"); }%>>Nat</option>
                	<option value="3" <%if (msgInst.getE02COMP06().equals("3")) { out.print("selected"); }%>>Todas</option>
              	</select> 
			</td>
			<td>
				<input type="text" name="E02COMA06"	size="12" maxlength="15" value="<%= msgInst.getE02COMA06() %>" onkeypress="enterDecimal()">
			</td>
			<td>
				<input type="text" name="E02COMI06"	size="12" maxlength="15" value="<%= msgInst.getE02COMI06() %>" onkeypress="enterDecimal()">
			</td>
			<td>
				<input type="text" name="E02COMM06"	size="12" maxlength="15" value="<%= msgInst.getE02COMM06() %>" onkeypress="enterDecimal()">
			</td>
			<td nowrap>
				<input type="text" name="E02COMC06" size="18" maxlength="16" value="<%= msgInst.getE02COMC06() %>"> 
				<a href="javascript:GetLedger('E02COMC06',document.forms[0].E02COMBNK.value,document.forms[0].E02CNTBCU.value,'')">
              	<img src="<%=request.getContextPath()%>/images/1b.gif" alt=". . ." align="absbottom" border="0" ></a> 
			</td>
		</tr>
		<tr>
			<td>
				<input type="text" name="E02COMD07" size="35" maxlength="35" value="<%= msgInst.getE02COMD07() %>">
			</td>
			<td>
				<select name="E02COMT07">
					<option value="" <%if (msgInst.getE02COMT07().equals("")) { out.print("selected"); }%>></option>
                	<option value="F" <%if (msgInst.getE02COMT07().equals("F")) { out.print("selected"); }%>>Fija</option>
                	<option value="P" <%if (msgInst.getE02COMT07().equals("P")) { out.print("selected"); }%>>Porcentual</option>
              	</select> 
			</td>
			<td>
				<select name="E02COMO07">
					<option value="" <%if (msgInst.getE02COMO07().equals("")) { out.print("selected"); }%>></option>
                	<option value="P" <%if (msgInst.getE02COMO07().equals("P")) { out.print("selected"); }%>>Compra</option>
                	<option value="S" <%if (msgInst.getE02COMO07().equals("S")) { out.print("selected"); }%>>Venta</option>
                	<option value="C" <%if (msgInst.getE02COMO07().equals("C")) { out.print("selected"); }%>>Canje</option>
                	<option value="T" <%if (msgInst.getE02COMO07().equals("T")) { out.print("selected"); }%>>Todas</option>
              	</select> 
			</td>
			<td>
				<select name="E02COMP07">
					<option value="" <%if (msgInst.getE02COMP07().equals("")) { out.print("selected"); }%>></option>
                	<option value="1" <%if (msgInst.getE02COMP07().equals("1")) { out.print("selected"); }%>>Jur</option>
                	<option value="2" <%if (msgInst.getE02COMP07().equals("2")) { out.print("selected"); }%>>Nat</option>
                	<option value="3" <%if (msgInst.getE02COMP07().equals("3")) { out.print("selected"); }%>>Todas</option>
              	</select> 
			</td>
			<td>
				<input type="text" name="E02COMA07"	size="12" maxlength="15" value="<%= msgInst.getE02COMA07() %>" onkeypress="enterDecimal()">
			</td>
			<td>
				<input type="text" name="E02COMI07"	size="12" maxlength="15" value="<%= msgInst.getE02COMI07() %>" onkeypress="enterDecimal()">
			</td>
			<td>
				<input type="text" name="E02COMM07"	size="12" maxlength="15" value="<%= msgInst.getE02COMM07() %>" onkeypress="enterDecimal()">
			</td>
			<td nowrap>
				<input type="text" name="E02COMC07" size="18" maxlength="16" value="<%= msgInst.getE02COMC07() %>"> 
				<a href="javascript:GetLedger('E02COMC07',document.forms[0].E02COMBNK.value,document.forms[0].E02CNTBCU.value,'')">
              	<img src="<%=request.getContextPath()%>/images/1b.gif" alt=". . ." align="absbottom" border="0" ></a> 
			</td>
		</tr>
		<tr>
			<td>
				<input type="text" name="E02COMD08" size="35" maxlength="35" value="<%= msgInst.getE02COMD08() %>">
			</td>
			<td>
				<select name="E02COMT08">
					<option value="" <%if (msgInst.getE02COMT08().equals("")) { out.print("selected"); }%>></option>
                	<option value="F" <%if (msgInst.getE02COMT08().equals("F")) { out.print("selected"); }%>>Fija</option>
                	<option value="P" <%if (msgInst.getE02COMT08().equals("P")) { out.print("selected"); }%>>Porcentual</option>
              	</select> 
			</td>
			<td>
				<select name="E02COMO08">
					<option value="" <%if (msgInst.getE02COMO08().equals("")) { out.print("selected"); }%>></option>
                	<option value="P" <%if (msgInst.getE02COMO08().equals("P")) { out.print("selected"); }%>>Compra</option>
                	<option value="S" <%if (msgInst.getE02COMO08().equals("S")) { out.print("selected"); }%>>Venta</option>
                	<option value="C" <%if (msgInst.getE02COMO08().equals("C")) { out.print("selected"); }%>>Canje</option>
                	<option value="T" <%if (msgInst.getE02COMO08().equals("T")) { out.print("selected"); }%>>Todas</option>
              	</select> 
			</td>
			<td>
				<select name="E02COMP08">
					<option value="" <%if (msgInst.getE02COMP08().equals("")) { out.print("selected"); }%>></option>
                	<option value="1" <%if (msgInst.getE02COMP08().equals("1")) { out.print("selected"); }%>>Jur</option>
                	<option value="2" <%if (msgInst.getE02COMP08().equals("2")) { out.print("selected"); }%>>Nat</option>
                	<option value="3" <%if (msgInst.getE02COMP08().equals("3")) { out.print("selected"); }%>>Todas</option>
              	</select> 
			</td>
			<td>
				<input type="text" name="E02COMA08"	size="12" maxlength="15" value="<%= msgInst.getE02COMA08() %>" onkeypress="enterDecimal()">
			</td>
			<td>
				<input type="text" name="E02COMI08"	size="12" maxlength="15" value="<%= msgInst.getE02COMI08() %>" onkeypress="enterDecimal()">
			</td>
			<td>
				<input type="text" name="E02COMM08"	size="12" maxlength="15" value="<%= msgInst.getE02COMM08() %>" onkeypress="enterDecimal()">
			</td>
			<td nowrap>
				<input type="text" name="E02COMC08" size="18" maxlength="16" value="<%= msgInst.getE02COMC08() %>"> 
				<a href="javascript:GetLedger('E02COMC08',document.forms[0].E02COMBNK.value,document.forms[0].E02CNTBCU.value,'')">
              	<img src="<%=request.getContextPath()%>/images/1b.gif" alt=". . ." align="absbottom" border="0" ></a> 
			</td>
		</tr>
		<tr>
			<td>
				<input type="text" name="E02COMD09" size="35" maxlength="35" value="<%= msgInst.getE02COMD09() %>">
			</td>
			<td>
				<select name="E02COMT09">
					<option value="" <%if (msgInst.getE02COMT09().equals("")) { out.print("selected"); }%>></option>
                	<option value="F" <%if (msgInst.getE02COMT09().equals("F")) { out.print("selected"); }%>>Fija</option>
                	<option value="P" <%if (msgInst.getE02COMT09().equals("P")) { out.print("selected"); }%>>Porcentual</option>
              	</select> 
			</td>
			<td>
				<select name="E02COMO09">
					<option value="" <%if (msgInst.getE02COMO09().equals("")) { out.print("selected"); }%>></option>
                	<option value="P" <%if (msgInst.getE02COMO09().equals("P")) { out.print("selected"); }%>>Compra</option>
                	<option value="S" <%if (msgInst.getE02COMO09().equals("S")) { out.print("selected"); }%>>Venta</option>
                	<option value="C" <%if (msgInst.getE02COMO09().equals("C")) { out.print("selected"); }%>>Canje</option>
                	<option value="T" <%if (msgInst.getE02COMO09().equals("T")) { out.print("selected"); }%>>Todas</option>
              	</select> 
			</td>
			<td>
				<select name="E02COMP09">
					<option value="" <%if (msgInst.getE02COMP09().equals("")) { out.print("selected"); }%>></option>
                	<option value="1" <%if (msgInst.getE02COMP09().equals("1")) { out.print("selected"); }%>>Jur</option>
                	<option value="2" <%if (msgInst.getE02COMP09().equals("2")) { out.print("selected"); }%>>Nat</option>
                	<option value="3" <%if (msgInst.getE02COMP09().equals("3")) { out.print("selected"); }%>>Todas</option>
              	</select> 
			</td>
			<td>
				<input type="text" name="E02COMA09"	size="12" maxlength="15" value="<%= msgInst.getE02COMA09() %>" onkeypress="enterDecimal()">
			</td>
			<td>
				<input type="text" name="E02COMI09"	size="12" maxlength="15" value="<%= msgInst.getE02COMI09() %>" onkeypress="enterDecimal()">
			</td>
			<td>
				<input type="text" name="E02COMM09"	size="12" maxlength="15" value="<%= msgInst.getE02COMM09() %>" onkeypress="enterDecimal()">
			</td>
			<td nowrap>
				<input type="text" name="E02COMC09" size="18" maxlength="16" value="<%= msgInst.getE02COMC09() %>"> 
				<a href="javascript:GetLedger('E02COMC09',document.forms[0].E02COMBNK.value,document.forms[0].E02CNTBCU.value,'')">
              	<img src="<%=request.getContextPath()%>/images/1b.gif" alt=". . ." align="absbottom" border="0" ></a> 
			</td>
		</tr>
		<tr>
			<td>
				<input type="text" name="E02COMD10" size="35" maxlength="35" value="<%= msgInst.getE02COMD10() %>">
			</td>
			<td>
				<select name="E02COMT10">
					<option value="" <%if (msgInst.getE02COMT10().equals("")) { out.print("selected"); }%>></option>
                	<option value="F" <%if (msgInst.getE02COMT10().equals("F")) { out.print("selected"); }%>>Fija</option>
                	<option value="P" <%if (msgInst.getE02COMT10().equals("P")) { out.print("selected"); }%>>Porcentual</option>
              	</select> 
			</td>
			<td>
				<select name="E02COMO10">
					<option value="" <%if (msgInst.getE02COMO10().equals("")) { out.print("selected"); }%>></option>
                	<option value="P" <%if (msgInst.getE02COMO10().equals("P")) { out.print("selected"); }%>>Compra</option>
                	<option value="S" <%if (msgInst.getE02COMO10().equals("S")) { out.print("selected"); }%>>Venta</option>
                	<option value="C" <%if (msgInst.getE02COMO10().equals("C")) { out.print("selected"); }%>>Canje</option>
                	<option value="T" <%if (msgInst.getE02COMO10().equals("T")) { out.print("selected"); }%>>Todas</option>
              	</select> 
			</td>
			<td>
				<select name="E02COMP10">
					<option value="" <%if (msgInst.getE02COMP10().equals("")) { out.print("selected"); }%>></option>
                	<option value="1" <%if (msgInst.getE02COMP10().equals("1")) { out.print("selected"); }%>>Jur</option>
                	<option value="2" <%if (msgInst.getE02COMP10().equals("2")) { out.print("selected"); }%>>Nat</option>
                	<option value="3" <%if (msgInst.getE02COMP10().equals("3")) { out.print("selected"); }%>>Todas</option>
              	</select> 
			</td>
			<td>
				<input type="text" name="E02COMA10"	size="12" maxlength="15" value="<%= msgInst.getE02COMA10() %>" onkeypress="enterDecimal()">
			</td>
			<td>
				<input type="text" name="E02COMI10"	size="12" maxlength="15" value="<%= msgInst.getE02COMI10() %>" onkeypress="enterDecimal()">
			</td>
			<td>
				<input type="text" name="E02COMM10"	size="12" maxlength="15" value="<%= msgInst.getE02COMM10() %>" onkeypress="enterDecimal()">
			</td>
			<td nowrap>
				<input type="text" name="E02COMC10" size="18" maxlength="16" value="<%= msgInst.getE02COMC10() %>"> 
				<a href="javascript:GetLedger('E02COMC10',document.forms[0].E02COMBNK.value,document.forms[0].E02CNTBCU.value,'')">
              	<img src="<%=request.getContextPath()%>/images/1b.gif" alt=". . ." align="absbottom" border="0" ></a> 
			</td>
		</tr>
		<tr>
			<td>
				<input type="text" name="E02COMD11" size="35" maxlength="35" value="<%= msgInst.getE02COMD11() %>">
			</td>
			<td>
				<select name="E02COMT11">
					<option value="" <%if (msgInst.getE02COMT11().equals("")) { out.print("selected"); }%>></option>
                	<option value="F" <%if (msgInst.getE02COMT11().equals("F")) { out.print("selected"); }%>>Fija</option>
                	<option value="P" <%if (msgInst.getE02COMT11().equals("P")) { out.print("selected"); }%>>Porcentual</option>
              	</select> 
			</td>
			<td>
				<select name="E02COMO11">
					<option value="" <%if (msgInst.getE02COMO11().equals("")) { out.print("selected"); }%>></option>
                	<option value="P" <%if (msgInst.getE02COMO11().equals("P")) { out.print("selected"); }%>>Compra</option>
                	<option value="S" <%if (msgInst.getE02COMO11().equals("S")) { out.print("selected"); }%>>Venta</option>
                	<option value="C" <%if (msgInst.getE02COMO11().equals("C")) { out.print("selected"); }%>>Canje</option>
                	<option value="T" <%if (msgInst.getE02COMO11().equals("T")) { out.print("selected"); }%>>Todas</option>
              	</select> 
			</td>
			<td>
				<select name="E02COMP11">
					<option value="" <%if (msgInst.getE02COMP11().equals("")) { out.print("selected"); }%>></option>
                	<option value="1" <%if (msgInst.getE02COMP11().equals("1")) { out.print("selected"); }%>>Jur</option>
                	<option value="2" <%if (msgInst.getE02COMP11().equals("2")) { out.print("selected"); }%>>Nat</option>
                	<option value="3" <%if (msgInst.getE02COMP11().equals("3")) { out.print("selected"); }%>>Todas</option>
              	</select> 
			</td>
			<td>
				<input type="text" name="E02COMA11"	size="12" maxlength="15" value="<%= msgInst.getE02COMA11() %>" onkeypress="enterDecimal()">
			</td>
			<td>
				<input type="text" name="E02COMI11"	size="12" maxlength="15" value="<%= msgInst.getE02COMI11() %>" onkeypress="enterDecimal()">
			</td>
			<td>
				<input type="text" name="E02COMM11"	size="12" maxlength="15" value="<%= msgInst.getE02COMM11() %>" onkeypress="enterDecimal()">
			</td>
			<td nowrap>
				<input type="text" name="E02COMC11" size="18" maxlength="16" value="<%= msgInst.getE02COMC11() %>"> 
				<a href="javascript:GetLedger('E02COMC11',document.forms[0].E02COMBNK.value,document.forms[0].E02CNTBCU.value,'')">
              	<img src="<%=request.getContextPath()%>/images/1b.gif" alt=". . ." align="absbottom" border="0" ></a> 
			</td>
		</tr>
		<tr>
			<td>
				<input type="text" name="E02COMD12" size="35" maxlength="35" value="<%= msgInst.getE02COMD12() %>">
			</td>
			<td>
				<select name="E02COMT12">
					<option value="" <%if (msgInst.getE02COMT12().equals("")) { out.print("selected"); }%>></option>
                	<option value="F" <%if (msgInst.getE02COMT12().equals("F")) { out.print("selected"); }%>>Fija</option>
                	<option value="P" <%if (msgInst.getE02COMT12().equals("P")) { out.print("selected"); }%>>Porcentual</option>
              	</select> 
			</td>
			<td>
				<select name="E02COMO12">
					<option value="" <%if (msgInst.getE02COMO12().equals("")) { out.print("selected"); }%>></option>
                	<option value="P" <%if (msgInst.getE02COMO12().equals("P")) { out.print("selected"); }%>>Compra</option>
                	<option value="S" <%if (msgInst.getE02COMO12().equals("S")) { out.print("selected"); }%>>Venta</option>
                	<option value="C" <%if (msgInst.getE02COMO12().equals("C")) { out.print("selected"); }%>>Canje</option>
                	<option value="T" <%if (msgInst.getE02COMO12().equals("T")) { out.print("selected"); }%>>Todas</option>
              	</select> 
			</td>
			<td>
				<select name="E02COMP12">
					<option value="" <%if (msgInst.getE02COMP12().equals("")) { out.print("selected"); }%>></option>
                	<option value="1" <%if (msgInst.getE02COMP12().equals("1")) { out.print("selected"); }%>>Jur</option>
                	<option value="2" <%if (msgInst.getE02COMP12().equals("2")) { out.print("selected"); }%>>Nat</option>
                	<option value="3" <%if (msgInst.getE02COMP12().equals("3")) { out.print("selected"); }%>>Todas</option>
              	</select> 
			</td>
			<td>
				<input type="text" name="E02COMA12"	size="12" maxlength="15" value="<%= msgInst.getE02COMA12() %>" onkeypress="enterDecimal()">
			</td>
			<td>
				<input type="text" name="E02COMI12"	size="12" maxlength="15" value="<%= msgInst.getE02COMI12() %>" onkeypress="enterDecimal()">
			</td>
			<td>
				<input type="text" name="E02COMM12"	size="12" maxlength="15" value="<%= msgInst.getE02COMM12() %>" onkeypress="enterDecimal()">
			</td>
			<td nowrap>
				<input type="text" name="E02COMC12" size="18" maxlength="16" value="<%= msgInst.getE02COMC12() %>"> 
				<a href="javascript:GetLedger('E02COMC12',document.forms[0].E02COMBNK.value,document.forms[0].E02CNTBCU.value,'')">
              	<img src="<%=request.getContextPath()%>/images/1b.gif" alt=". . ." align="absbottom" border="0" ></a> 
			</td>
		</tr>
		<tr>
			<td>
				<input type="text" name="E02COMD13" size="35" maxlength="35" value="<%= msgInst.getE02COMD13() %>">
			</td>
			<td>
				<select name="E02COMT13">
					<option value="" <%if (msgInst.getE02COMT13().equals("")) { out.print("selected"); }%>></option>
                	<option value="F" <%if (msgInst.getE02COMT13().equals("F")) { out.print("selected"); }%>>Fija</option>
                	<option value="P" <%if (msgInst.getE02COMT13().equals("P")) { out.print("selected"); }%>>Porcentual</option>
              	</select> 
			</td>
			<td>
				<select name="E02COMO13">
					<option value="" <%if (msgInst.getE02COMO13().equals("")) { out.print("selected"); }%>></option>
                	<option value="P" <%if (msgInst.getE02COMO13().equals("P")) { out.print("selected"); }%>>Compra</option>
                	<option value="S" <%if (msgInst.getE02COMO13().equals("S")) { out.print("selected"); }%>>Venta</option>
                	<option value="C" <%if (msgInst.getE02COMO13().equals("C")) { out.print("selected"); }%>>Canje</option>
                	<option value="T" <%if (msgInst.getE02COMO13().equals("T")) { out.print("selected"); }%>>Todas</option>
              	</select> 
			</td>
			<td>
				<select name="E02COMP13">
					<option value="" <%if (msgInst.getE02COMP13().equals("")) { out.print("selected"); }%>></option>
                	<option value="1" <%if (msgInst.getE02COMP13().equals("1")) { out.print("selected"); }%>>Jur</option>
                	<option value="2" <%if (msgInst.getE02COMP13().equals("2")) { out.print("selected"); }%>>Nat</option>
                	<option value="3" <%if (msgInst.getE02COMP13().equals("3")) { out.print("selected"); }%>>Todas</option>
              	</select> 
			</td>
			<td>
				<input type="text" name="E02COMA13"	size="12" maxlength="15" value="<%= msgInst.getE02COMA13() %>" onkeypress="enterDecimal()">
			</td>
			<td>
				<input type="text" name="E02COMI13"	size="12" maxlength="15" value="<%= msgInst.getE02COMI13() %>" onkeypress="enterDecimal()">
			</td>
			<td>
				<input type="text" name="E02COMM13"	size="12" maxlength="15" value="<%= msgInst.getE02COMM13() %>" onkeypress="enterDecimal()">
			</td>
			<td nowrap>
				<input type="text" name="E02COMC13" size="18" maxlength="16" value="<%= msgInst.getE02COMC13() %>"> 
				<a href="javascript:GetLedger('E02COMC13',document.forms[0].E02COMBNK.value,document.forms[0].E02CNTBCU.value,'')">
              	<img src="<%=request.getContextPath()%>/images/1b.gif" alt=". . ." align="absbottom" border="0" ></a> 
			</td>
		</tr>
		<tr>
			<td>
				<input type="text" name="E02COMD14" size="35" maxlength="35" value="<%= msgInst.getE02COMD14() %>">
			</td>
			<td>
				<select name="E02COMT14">
					<option value="" <%if (msgInst.getE02COMT14().equals("")) { out.print("selected"); }%>></option>
                	<option value="F" <%if (msgInst.getE02COMT14().equals("F")) { out.print("selected"); }%>>Fija</option>
                	<option value="P" <%if (msgInst.getE02COMT14().equals("P")) { out.print("selected"); }%>>Porcentual</option>
              	</select> 
			</td>
			<td>
				<select name="E02COMO14">
					<option value="" <%if (msgInst.getE02COMO14().equals("")) { out.print("selected"); }%>></option>
                	<option value="P" <%if (msgInst.getE02COMO14().equals("P")) { out.print("selected"); }%>>Compra</option>
                	<option value="S" <%if (msgInst.getE02COMO14().equals("S")) { out.print("selected"); }%>>Venta</option>
                	<option value="C" <%if (msgInst.getE02COMO14().equals("C")) { out.print("selected"); }%>>Canje</option>
                	<option value="T" <%if (msgInst.getE02COMO14().equals("T")) { out.print("selected"); }%>>Todas</option>
              	</select> 
			</td>
			<td>
				<select name="E02COMP14">
					<option value="" <%if (msgInst.getE02COMP14().equals("")) { out.print("selected"); }%>></option>
                	<option value="1" <%if (msgInst.getE02COMP14().equals("1")) { out.print("selected"); }%>>Jur</option>
                	<option value="2" <%if (msgInst.getE02COMP14().equals("2")) { out.print("selected"); }%>>Nat</option>
                	<option value="3" <%if (msgInst.getE02COMP14().equals("3")) { out.print("selected"); }%>>Todas</option>
              	</select> 
			</td>
			<td>
				<input type="text" name="E02COMA14"	size="12" maxlength="15" value="<%= msgInst.getE02COMA14() %>" onkeypress="enterDecimal()">
			</td>
			<td>
				<input type="text" name="E02COMI14"	size="12" maxlength="15" value="<%= msgInst.getE02COMI14() %>" onkeypress="enterDecimal()">
			</td>
			<td>
				<input type="text" name="E02COMM14"	size="12" maxlength="15" value="<%= msgInst.getE02COMM14() %>" onkeypress="enterDecimal()">
			</td>
			<td nowrap>
				<input type="text" name="E02COMC14" size="18" maxlength="16" value="<%= msgInst.getE02COMC14() %>"> 
				<a href="javascript:GetLedger('E02COMC14',document.forms[0].E02COMBNK.value,document.forms[0].E02CNTBCU.value,'')">
              	<img src="<%=request.getContextPath()%>/images/1b.gif" alt=". . ." align="absbottom" border="0" ></a> 
			</td>
		</tr>
		<tr>
			<td>
				<input type="text" name="E02COMD15" size="35" maxlength="35" value="<%= msgInst.getE02COMD15() %>">
			</td>
			<td>
				<select name="E02COMT15">
					<option value="" <%if (msgInst.getE02COMT15().equals("")) { out.print("selected"); }%>></option>
                	<option value="F" <%if (msgInst.getE02COMT15().equals("F")) { out.print("selected"); }%>>Fija</option>
                	<option value="P" <%if (msgInst.getE02COMT15().equals("P")) { out.print("selected"); }%>>Porcentual</option>
              	</select> 
			</td>
			<td>
				<select name="E02COMO15">
					<option value="" <%if (msgInst.getE02COMO15().equals("")) { out.print("selected"); }%>></option>
                	<option value="P" <%if (msgInst.getE02COMO15().equals("P")) { out.print("selected"); }%>>Compra</option>
                	<option value="S" <%if (msgInst.getE02COMO15().equals("S")) { out.print("selected"); }%>>Venta</option>
                	<option value="C" <%if (msgInst.getE02COMO15().equals("C")) { out.print("selected"); }%>>Canje</option>
                	<option value="T" <%if (msgInst.getE02COMO15().equals("T")) { out.print("selected"); }%>>Todas</option>
              	</select> 
			</td>
			<td>
				<select name="E02COMP15">
					<option value="" <%if (msgInst.getE02COMP15().equals("")) { out.print("selected"); }%>></option>
                	<option value="1" <%if (msgInst.getE02COMP15().equals("1")) { out.print("selected"); }%>>Jur</option>
                	<option value="2" <%if (msgInst.getE02COMP15().equals("2")) { out.print("selected"); }%>>Nat</option>
                	<option value="3" <%if (msgInst.getE02COMP15().equals("3")) { out.print("selected"); }%>>Todas</option>
              	</select> 
			</td>
			<td>
				<input type="text" name="E02COMA15"	size="12" maxlength="15" value="<%= msgInst.getE02COMA15() %>" onkeypress="enterDecimal()">
			</td>
			<td>
				<input type="text" name="E02COMI15"	size="12" maxlength="15" value="<%= msgInst.getE02COMI15() %>" onkeypress="enterDecimal()">
			</td>
			<td>
				<input type="text" name="E02COMM15"	size="12" maxlength="15" value="<%= msgInst.getE02COMM15() %>" onkeypress="enterDecimal()">
			</td>
			<td nowrap>
				<input type="text" name="E02COMC15" size="18" maxlength="16" value="<%= msgInst.getE02COMC15() %>"> 
				<a href="javascript:GetLedger('E02COMC15',document.forms[0].E02COMBNK.value,document.forms[0].E02CNTBCU.value,'')">
              	<img src="<%=request.getContextPath()%>/images/1b.gif" alt=". . ." align="absbottom" border="0" ></a> 
			</td>
		</tr>
		<tr>
			<td>
				<input type="text" name="E02COMD16" size="35" maxlength="35" value="<%= msgInst.getE02COMD16() %>">
			</td>
			<td>
				<select name="E02COMT16">
					<option value="" <%if (msgInst.getE02COMT16().equals("")) { out.print("selected"); }%>></option>
                	<option value="F" <%if (msgInst.getE02COMT16().equals("F")) { out.print("selected"); }%>>Fija</option>
                	<option value="P" <%if (msgInst.getE02COMT16().equals("P")) { out.print("selected"); }%>>Porcentual</option>
              	</select> 
			</td>
			<td>
				<select name="E02COMO16">
					<option value="" <%if (msgInst.getE02COMO16().equals("")) { out.print("selected"); }%>></option>
                	<option value="P" <%if (msgInst.getE02COMO16().equals("P")) { out.print("selected"); }%>>Compra</option>
                	<option value="S" <%if (msgInst.getE02COMO16().equals("S")) { out.print("selected"); }%>>Venta</option>
                	<option value="C" <%if (msgInst.getE02COMO16().equals("C")) { out.print("selected"); }%>>Canje</option>
                	<option value="T" <%if (msgInst.getE02COMO16().equals("T")) { out.print("selected"); }%>>Todas</option>
              	</select> 
			</td>
			<td>
				<select name="E02COMP16">
					<option value="" <%if (msgInst.getE02COMP16().equals("")) { out.print("selected"); }%>></option>
                	<option value="1" <%if (msgInst.getE02COMP16().equals("1")) { out.print("selected"); }%>>Jur</option>
                	<option value="2" <%if (msgInst.getE02COMP16().equals("2")) { out.print("selected"); }%>>Nat</option>
                	<option value="3" <%if (msgInst.getE02COMP16().equals("3")) { out.print("selected"); }%>>Todas</option>
              	</select> 
			</td>
			<td>
				<input type="text" name="E02COMA16"	size="12" maxlength="15" value="<%= msgInst.getE02COMA16() %>" onkeypress="enterDecimal()">
			</td>
			<td>
				<input type="text" name="E02COMI16"	size="12" maxlength="15" value="<%= msgInst.getE02COMI16() %>" onkeypress="enterDecimal()">
			</td>
			<td>
				<input type="text" name="E02COMM16"	size="12" maxlength="15" value="<%= msgInst.getE02COMM16() %>" onkeypress="enterDecimal()">
			</td>
			<td nowrap>
				<input type="text" name="E02COMC16" size="18" maxlength="16" value="<%= msgInst.getE02COMC16() %>"> 
				<a href="javascript:GetLedger('E02COMC16',document.forms[0].E02COMBNK.value,document.forms[0].E02CNTBCU.value,'')">
              	<img src="<%=request.getContextPath()%>/images/1b.gif" alt=". . ." align="absbottom" border="0" ></a> 
			</td>
		</tr>
		<tr>
			<td>
				<input type="text" name="E02COMD17" size="35" maxlength="35" value="<%= msgInst.getE02COMD17() %>">
			</td>
			<td>
				<select name="E02COMT17">
					<option value="" <%if (msgInst.getE02COMT17().equals("")) { out.print("selected"); }%>></option>
                	<option value="F" <%if (msgInst.getE02COMT17().equals("F")) { out.print("selected"); }%>>Fija</option>
                	<option value="P" <%if (msgInst.getE02COMT17().equals("P")) { out.print("selected"); }%>>Porcentual</option>
              	</select> 
			</td>
			<td>
				<select name="E02COMO17">
					<option value="" <%if (msgInst.getE02COMO17().equals("")) { out.print("selected"); }%>></option>
                	<option value="P" <%if (msgInst.getE02COMO17().equals("P")) { out.print("selected"); }%>>Compra</option>
                	<option value="S" <%if (msgInst.getE02COMO17().equals("S")) { out.print("selected"); }%>>Venta</option>
                	<option value="C" <%if (msgInst.getE02COMO17().equals("C")) { out.print("selected"); }%>>Canje</option>
                	<option value="T" <%if (msgInst.getE02COMO17().equals("T")) { out.print("selected"); }%>>Todas</option>
              	</select> 
			</td>
			<td>
				<select name="E02COMP17">
					<option value="" <%if (msgInst.getE02COMP17().equals("")) { out.print("selected"); }%>></option>
                	<option value="1" <%if (msgInst.getE02COMP17().equals("1")) { out.print("selected"); }%>>Jur</option>
                	<option value="2" <%if (msgInst.getE02COMP17().equals("2")) { out.print("selected"); }%>>Nat</option>
                	<option value="3" <%if (msgInst.getE02COMP17().equals("3")) { out.print("selected"); }%>>Todas</option>
              	</select> 
			</td>
			<td>
				<input type="text" name="E02COMA17"	size="12" maxlength="15" value="<%= msgInst.getE02COMA17() %>" onkeypress="enterDecimal()">
			</td>
			<td>
				<input type="text" name="E02COMI17"	size="12" maxlength="15" value="<%= msgInst.getE02COMI17() %>" onkeypress="enterDecimal()">
			</td>
			<td>
				<input type="text" name="E02COMM17"	size="12" maxlength="15" value="<%= msgInst.getE02COMM17() %>" onkeypress="enterDecimal()">
			</td>
			<td nowrap>
				<input type="text" name="E02COMC17" size="18" maxlength="16" value="<%= msgInst.getE02COMC17() %>"> 
				<a href="javascript:GetLedger('E02COMC17',document.forms[0].E02COMBNK.value,document.forms[0].E02CNTBCU.value,'')">
              	<img src="<%=request.getContextPath()%>/images/1b.gif" alt=". . ." align="absbottom" border="0" ></a> 
			</td>
		</tr>
		<tr>
			<td>
				<input type="text" name="E02COMD18" size="35" maxlength="35" value="<%= msgInst.getE02COMD18() %>">
			</td>
			<td>
				<select name="E02COMT18">
					<option value="" <%if (msgInst.getE02COMT18().equals("")) { out.print("selected"); }%>></option>
                	<option value="F" <%if (msgInst.getE02COMT18().equals("F")) { out.print("selected"); }%>>Fija</option>
                	<option value="P" <%if (msgInst.getE02COMT18().equals("P")) { out.print("selected"); }%>>Porcentual</option>
              	</select> 
			</td>
			<td>
				<select name="E02COMO18">
					<option value="" <%if (msgInst.getE02COMO18().equals("")) { out.print("selected"); }%>></option>
                	<option value="P" <%if (msgInst.getE02COMO18().equals("P")) { out.print("selected"); }%>>Compra</option>
                	<option value="S" <%if (msgInst.getE02COMO18().equals("S")) { out.print("selected"); }%>>Venta</option>
                	<option value="C" <%if (msgInst.getE02COMO18().equals("C")) { out.print("selected"); }%>>Canje</option>
                	<option value="T" <%if (msgInst.getE02COMO18().equals("T")) { out.print("selected"); }%>>Todas</option>
              	</select> 
			</td>
			<td>
				<select name="E02COMP18">
					<option value="" <%if (msgInst.getE02COMP18().equals("")) { out.print("selected"); }%>></option>
                	<option value="1" <%if (msgInst.getE02COMP18().equals("1")) { out.print("selected"); }%>>Jur</option>
                	<option value="2" <%if (msgInst.getE02COMP18().equals("2")) { out.print("selected"); }%>>Nat</option>
                	<option value="3" <%if (msgInst.getE02COMP18().equals("3")) { out.print("selected"); }%>>Todas</option>
              	</select> 
			</td>
			<td>
				<input type="text" name="E02COMA18"	size="12" maxlength="15" value="<%= msgInst.getE02COMA18() %>" onkeypress="enterDecimal()">
			</td>
			<td>
				<input type="text" name="E02COMI18"	size="12" maxlength="15" value="<%= msgInst.getE02COMI18() %>" onkeypress="enterDecimal()">
			</td>
			<td>
				<input type="text" name="E02COMM18"	size="12" maxlength="15" value="<%= msgInst.getE02COMM18() %>" onkeypress="enterDecimal()">
			</td>
			<td nowrap>
				<input type="text" name="E02COMC18" size="18" maxlength="16" value="<%= msgInst.getE02COMC18() %>"> 
				<a href="javascript:GetLedger('E02COMC18',document.forms[0].E02COMBNK.value,document.forms[0].E02CNTBCU.value,'')">
              	<img src="<%=request.getContextPath()%>/images/1b.gif" alt=". . ." align="absbottom" border="0" ></a> 
			</td>
		</tr>
		<tr>
			<td>
				<input type="text" name="E02COMD19" size="35" maxlength="35" value="<%= msgInst.getE02COMD19() %>">
			</td>
			<td>
				<select name="E02COMT19">
					<option value="" <%if (msgInst.getE02COMT19().equals("")) { out.print("selected"); }%>></option>
                	<option value="F" <%if (msgInst.getE02COMT19().equals("F")) { out.print("selected"); }%>>Fija</option>
                	<option value="P" <%if (msgInst.getE02COMT19().equals("P")) { out.print("selected"); }%>>Porcentual</option>
              	</select> 
			</td>
			<td>
				<select name="E02COMO19">
					<option value="" <%if (msgInst.getE02COMO19().equals("")) { out.print("selected"); }%>></option>
                	<option value="P" <%if (msgInst.getE02COMO19().equals("P")) { out.print("selected"); }%>>Compra</option>
                	<option value="S" <%if (msgInst.getE02COMO19().equals("S")) { out.print("selected"); }%>>Venta</option>
                	<option value="C" <%if (msgInst.getE02COMO19().equals("C")) { out.print("selected"); }%>>Canje</option>
                	<option value="T" <%if (msgInst.getE02COMO19().equals("T")) { out.print("selected"); }%>>Todas</option>
              	</select> 
			</td>
			<td>
				<select name="E02COMP19">
					<option value="" <%if (msgInst.getE02COMP19().equals("")) { out.print("selected"); }%>></option>
                	<option value="1" <%if (msgInst.getE02COMP19().equals("1")) { out.print("selected"); }%>>Jur</option>
                	<option value="2" <%if (msgInst.getE02COMP19().equals("2")) { out.print("selected"); }%>>Nat</option>
                	<option value="3" <%if (msgInst.getE02COMP19().equals("3")) { out.print("selected"); }%>>Todas</option>
              	</select> 
			</td>
			<td>
				<input type="text" name="E02COMA19"	size="12" maxlength="15" value="<%= msgInst.getE02COMA19() %>" onkeypress="enterDecimal()">
			</td>
			<td>
				<input type="text" name="E02COMI19"	size="12" maxlength="15" value="<%= msgInst.getE02COMI19() %>" onkeypress="enterDecimal()">
			</td>
			<td>
				<input type="text" name="E02COMM19"	size="12" maxlength="15" value="<%= msgInst.getE02COMM19() %>" onkeypress="enterDecimal()">
			</td>
			<td nowrap>
				<input type="text" name="E02COMC19" size="18" maxlength="16" value="<%= msgInst.getE02COMC19() %>"> 
				<a href="javascript:GetLedger('E02COMC19',document.forms[0].E02COMBNK.value,document.forms[0].E02CNTBCU.value,'')">
              	<img src="<%=request.getContextPath()%>/images/1b.gif" alt=". . ." align="absbottom" border="0" ></a> 
			</td>
		</tr>
		<tr>
			<td>
				<input type="text" name="E02COMD20" size="35" maxlength="35" value="<%= msgInst.getE02COMD20() %>">
			</td>
			<td>
				<select name="E02COMT20">
					<option value="" <%if (msgInst.getE02COMT20().equals("")) { out.print("selected"); }%>></option>
                	<option value="F" <%if (msgInst.getE02COMT20().equals("F")) { out.print("selected"); }%>>Fija</option>
                	<option value="P" <%if (msgInst.getE02COMT20().equals("P")) { out.print("selected"); }%>>Porcentual</option>
              	</select> 
			</td>
			<td>
				<select name="E02COMO20">
					<option value="" <%if (msgInst.getE02COMO20().equals("")) { out.print("selected"); }%>></option>
                	<option value="P" <%if (msgInst.getE02COMO20().equals("P")) { out.print("selected"); }%>>Compra</option>
                	<option value="S" <%if (msgInst.getE02COMO20().equals("S")) { out.print("selected"); }%>>Venta</option>
                	<option value="C" <%if (msgInst.getE02COMO20().equals("C")) { out.print("selected"); }%>>Canje</option>
                	<option value="T" <%if (msgInst.getE02COMO20().equals("T")) { out.print("selected"); }%>>Todas</option>
              	</select> 
			</td>
			<td>
				<select name="E02COMP20">
					<option value="" <%if (msgInst.getE02COMP20().equals("")) { out.print("selected"); }%>></option>
                	<option value="1" <%if (msgInst.getE02COMP20().equals("1")) { out.print("selected"); }%>>Jur</option>
                	<option value="2" <%if (msgInst.getE02COMP20().equals("2")) { out.print("selected"); }%>>Nat</option>
                	<option value="3" <%if (msgInst.getE02COMP20().equals("3")) { out.print("selected"); }%>>Todas</option>
              	</select> 
			</td>
			<td>
				<input type="text" name="E02COMA20"	size="12" maxlength="15" value="<%= msgInst.getE02COMA20() %>" onkeypress="enterDecimal()">
			</td>
			<td>
				<input type="text" name="E02COMI20"	size="12" maxlength="15" value="<%= msgInst.getE02COMI20() %>" onkeypress="enterDecimal()">
			</td>
			<td>
				<input type="text" name="E02COMM20"	size="12" maxlength="15" value="<%= msgInst.getE02COMM20() %>" onkeypress="enterDecimal()">
			</td>
			<td nowrap>
				<input type="text" name="E02COMC20" size="18" maxlength="16" value="<%= msgInst.getE02COMC20() %>"> 
				<a href="javascript:GetLedger('E02COMC20',document.forms[0].E02COMBNK.value,document.forms[0].E02CNTBCU.value,'')">
              	<img src="<%=request.getContextPath()%>/images/1b.gif" alt=". . ." align="absbottom" border="0" ></a> 
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
