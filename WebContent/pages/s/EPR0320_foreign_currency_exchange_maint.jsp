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
datapro.eibs.beans.EPR032001Message msgInst = (datapro.eibs.beans.EPR032001Message) mtList.getRecord();

if ( !error.getERRNUM().equals("0")  ) {
      error.setERRNUM("0");
%>
<SCRIPT Language="Javascript">
        showErrors();
</SCRIPT>
<%}%>

</head>
<body>

<H3 align="center">Mantenimiento de Comisiones de Moneda Extranjera<img src="<%=request.getContextPath()%>/images/e_ibs.gif" align="left" name="EIBS_GIF" ALT="foreign_currency_exchange_maint, EPR0320"></H3>
<hr size="4">


<form method="post" action="<%=request.getContextPath()%>/servlet/datapro.eibs.forex.JSEPR0320">
 
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
	        <input type="text" name="E01COMBNK" readonly size="3" maxlength="2" value="<%= msgInst.getE01COMBNK() %>">
	      </td>
     	</tr>
     	<tr id=trclear> 
	      <td nowrap> 
	        <div align="right">Instrumento : </div>
	      </td>
	      <td nowrap>
	      	<input type="text" name="E01COMINS" readonly size="5" maxlength="4" value="<%= msgInst.getE01COMINS() %>">
	      	<input type="text" name="E01COMINA" readonly size="35" maxlength="35" readonly value="<%= msgInst.getE01COMINA() %>">
		  </td>
     	</tr>
     	<tr id=trdark> 
		  <td> 
		     <div align="right">Moneda : </div>        
		  </td>
      	  <td nowrap> 
      	    <input type="text" name="E01COMCCY" readonly size="5" maxlength="3" value="<%= msgInst.getE01COMCCY() %>">
      	  </td>     
      	</tr>
      	<tr id=trclear>
          <td nowrap>
              <div align="right">Cliente : </div>
          </td>
          <td nowrap>
              <input type="text" name="E01COMCUS" readonly size="10" maxlength="9" value="<%= msgInst.getE01COMCUS() %>">
              <input type="text" name="E01COMCUN" readonly size="35" maxlength="35" readonly value="<%= msgInst.getE01COMCUN() %>"> 
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
				<input type="text" name="E01COMCUR" size="5" maxlength="3" value="<%= msgInst.getE01COMCUR() %>"> 
				<a href="javascript:GetCurrency('E01COMCUR',document.forms[0].E01COMBNK.value)"><img src="<%=request.getContextPath()%>/images/1b.gif" alt="Ayuda" align="bottom" border="0"></a>
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
				<input type="text" name="E01COMD01" size="35" maxlength="35" value="<%= msgInst.getE01COMD01() %>">
			</td>
			<td>
				<select name="E01COMT01">
					<option value="" <%if (msgInst.getE01COMT01().equals("")) { out.print("selected"); }%>></option>
                	<option value="F" <%if (msgInst.getE01COMT01().equals("F")) { out.print("selected"); }%>>Fija</option>
                	<option value="P" <%if (msgInst.getE01COMT01().equals("P")) { out.print("selected"); }%>>Porcentual</option>
              	</select> 
			</td>
			<td>
				<select name="E01COMO01">
					<option value="" <%if (msgInst.getE01COMO01().equals("")) { out.print("selected"); }%>></option>
                	<option value="P" <%if (msgInst.getE01COMO01().equals("P")) { out.print("selected"); }%>>Compra</option>
                	<option value="S" <%if (msgInst.getE01COMO01().equals("S")) { out.print("selected"); }%>>Venta</option>
                	<option value="C" <%if (msgInst.getE01COMO01().equals("C")) { out.print("selected"); }%>>Canje</option>
                	<option value="T" <%if (msgInst.getE01COMO01().equals("T")) { out.print("selected"); }%>>Todas</option>
              	</select> 
			</td>
			<td>
				<select name="E01COMP01">
					<option value="" <%if (msgInst.getE01COMP01().equals("")) { out.print("selected"); }%>></option>
                	<option value="1" <%if (msgInst.getE01COMP01().equals("1")) { out.print("selected"); }%>>Jur</option>
                	<option value="2" <%if (msgInst.getE01COMP01().equals("2")) { out.print("selected"); }%>>Nat</option>
                	<option value="3" <%if (msgInst.getE01COMP01().equals("3")) { out.print("selected"); }%>>Todas</option>
              	</select> 
			</td>
			<td>
				<input type="text" name="E01COMA01"	size="12" maxlength="15" value="<%= msgInst.getE01COMA01() %>" onkeypress="enterDecimal()">
			</td>
			<td>
				<input type="text" name="E01COMI01"	size="12" maxlength="15" value="<%= msgInst.getE01COMI01() %>" onkeypress="enterDecimal()">
			</td>
			<td>
				<input type="text" name="E01COMM01"	size="12" maxlength="15" value="<%= msgInst.getE01COMM01() %>" onkeypress="enterDecimal()">
			</td>
			<td nowrap>
				<input type="text" name="E01COMC01" size="18" maxlength="16" value="<%= msgInst.getE01COMC01() %>"> 
				<a href="javascript:GetLedger('E01COMC01',document.forms[0].E01COMBNK.value,document.forms[0].E01CNTBCU.value,'')">
              	<img src="<%=request.getContextPath()%>/images/1b.gif" alt=". . ." align="absbottom" border="0" ></a>
              	<input type="hidden" name="E01CNTBCU" size="5" maxlength="3" value="<%= msgInst.getE01CNTBCU() %>"> 
			</td>
		</tr>
		<tr>
			<td>
				<input type="text" name="E01COMD02" size="35" maxlength="35" value="<%= msgInst.getE01COMD02() %>">
			</td>
			<td>
				<select name="E01COMT02">
					<option value="" <%if (msgInst.getE01COMT02().equals("")) { out.print("selected"); }%>></option>
                	<option value="F" <%if (msgInst.getE01COMT02().equals("F")) { out.print("selected"); }%>>Fija</option>
                	<option value="P" <%if (msgInst.getE01COMT02().equals("P")) { out.print("selected"); }%>>Porcentual</option>
              	</select> 
			</td>
			<td>
				<select name="E01COMO02">
					<option value="" <%if (msgInst.getE01COMO02().equals("")) { out.print("selected"); }%>></option>
                	<option value="P" <%if (msgInst.getE01COMO02().equals("P")) { out.print("selected"); }%>>Compra</option>
                	<option value="S" <%if (msgInst.getE01COMO02().equals("S")) { out.print("selected"); }%>>Venta</option>
                	<option value="C" <%if (msgInst.getE01COMO02().equals("C")) { out.print("selected"); }%>>Canje</option>
                	<option value="T" <%if (msgInst.getE01COMO02().equals("T")) { out.print("selected"); }%>>Todas</option>
              	</select> 
			</td>
			<td>
				<select name="E01COMP02">
					<option value="" <%if (msgInst.getE01COMP02().equals("")) { out.print("selected"); }%>></option>
                	<option value="1" <%if (msgInst.getE01COMP02().equals("1")) { out.print("selected"); }%>>Jur</option>
                	<option value="2" <%if (msgInst.getE01COMP02().equals("2")) { out.print("selected"); }%>>Nat</option>
                	<option value="3" <%if (msgInst.getE01COMP02().equals("3")) { out.print("selected"); }%>>Todas</option>
              	</select> 
			</td>
			<td>
				<input type="text" name="E01COMA02"	size="12" maxlength="15" value="<%= msgInst.getE01COMA02() %>" onkeypress="enterDecimal()">
			</td>
			<td>
				<input type="text" name="E01COMI02"	size="12" maxlength="15" value="<%= msgInst.getE01COMI02() %>" onkeypress="enterDecimal()">
			</td>
			<td>
				<input type="text" name="E01COMM02"	size="12" maxlength="15" value="<%= msgInst.getE01COMM02() %>" onkeypress="enterDecimal()">
			</td>
			<td nowrap>
				<input type="text" name="E01COMC02" size="18" maxlength="16" value="<%= msgInst.getE01COMC02() %>"> 
				<a href="javascript:GetLedger('E01COMC02',document.forms[0].E01COMBNK.value,document.forms[0].E01CNTBCU.value,'')">
              	<img src="<%=request.getContextPath()%>/images/1b.gif" alt=". . ." align="absbottom" border="0" ></a>
			</td>
		</tr>
		<tr>
			<td>
				<input type="text" name="E01COMD03" size="35" maxlength="35" value="<%= msgInst.getE01COMD03() %>">
			</td>
			<td>
				<select name="E01COMT03">
					<option value="" <%if (msgInst.getE01COMT03().equals("")) { out.print("selected"); }%>></option>
                	<option value="F" <%if (msgInst.getE01COMT03().equals("F")) { out.print("selected"); }%>>Fija</option>
                	<option value="P" <%if (msgInst.getE01COMT03().equals("P")) { out.print("selected"); }%>>Porcentual</option>
              	</select> 
			</td>
			<td>
				<select name="E01COMO03">
					<option value="" <%if (msgInst.getE01COMO03().equals("")) { out.print("selected"); }%>></option>
                	<option value="P" <%if (msgInst.getE01COMO03().equals("P")) { out.print("selected"); }%>>Compra</option>
                	<option value="S" <%if (msgInst.getE01COMO03().equals("S")) { out.print("selected"); }%>>Venta</option>
                	<option value="C" <%if (msgInst.getE01COMO03().equals("C")) { out.print("selected"); }%>>Canje</option>
                	<option value="T" <%if (msgInst.getE01COMO03().equals("T")) { out.print("selected"); }%>>Todas</option>
              	</select> 
			</td>
			<td>
				<select name="E01COMP03">
					<option value="" <%if (msgInst.getE01COMP03().equals("")) { out.print("selected"); }%>></option>
                	<option value="1" <%if (msgInst.getE01COMP03().equals("1")) { out.print("selected"); }%>>Jur</option>
                	<option value="2" <%if (msgInst.getE01COMP03().equals("2")) { out.print("selected"); }%>>Nat</option>
                	<option value="3" <%if (msgInst.getE01COMP03().equals("3")) { out.print("selected"); }%>>Todas</option>
              	</select> 
			</td>
			<td>
				<input type="text" name="E01COMA03"	size="12" maxlength="15" value="<%= msgInst.getE01COMA03() %>" onkeypress="enterDecimal()">
			</td>
			<td>
				<input type="text" name="E01COMI03"	size="12" maxlength="15" value="<%= msgInst.getE01COMI03() %>" onkeypress="enterDecimal()">
			</td>
			<td>
				<input type="text" name="E01COMM03"	size="12" maxlength="15" value="<%= msgInst.getE01COMM03() %>" onkeypress="enterDecimal()">
			</td>
			<td nowrap>
				<input type="text" name="E01COMC03" size="18" maxlength="16" value="<%= msgInst.getE01COMC03() %>"> 
				<a href="javascript:GetLedger('E01COMC03',document.forms[0].E01COMBNK.value,document.forms[0].E01CNTBCU.value,'')">
              	<img src="<%=request.getContextPath()%>/images/1b.gif" alt=". . ." align="absbottom" border="0" ></a>
			</td>
		</tr>
		<tr>
			<td>
				<input type="text" name="E01COMD04" size="35" maxlength="35" value="<%= msgInst.getE01COMD04() %>">
			</td>
			<td>
				<select name="E01COMT04">
					<option value="" <%if (msgInst.getE01COMT04().equals("")) { out.print("selected"); }%>></option>
                	<option value="F" <%if (msgInst.getE01COMT04().equals("F")) { out.print("selected"); }%>>Fija</option>
                	<option value="P" <%if (msgInst.getE01COMT04().equals("P")) { out.print("selected"); }%>>Porcentual</option>
              	</select> 
			</td>
			<td>
				<select name="E01COMO04">
					<option value="" <%if (msgInst.getE01COMO04().equals("")) { out.print("selected"); }%>></option>
                	<option value="P" <%if (msgInst.getE01COMO04().equals("P")) { out.print("selected"); }%>>Compra</option>
                	<option value="S" <%if (msgInst.getE01COMO04().equals("S")) { out.print("selected"); }%>>Venta</option>
                	<option value="C" <%if (msgInst.getE01COMO04().equals("C")) { out.print("selected"); }%>>Canje</option>
                	<option value="T" <%if (msgInst.getE01COMO04().equals("T")) { out.print("selected"); }%>>Todas</option>
              	</select> 
			</td>
			<td>
				<select name="E01COMP04">
					<option value="" <%if (msgInst.getE01COMP04().equals("")) { out.print("selected"); }%>></option>
                	<option value="1" <%if (msgInst.getE01COMP04().equals("1")) { out.print("selected"); }%>>Jur</option>
                	<option value="2" <%if (msgInst.getE01COMP04().equals("2")) { out.print("selected"); }%>>Nat</option>
                	<option value="3" <%if (msgInst.getE01COMP04().equals("3")) { out.print("selected"); }%>>Todas</option>
              	</select> 
			</td>
			<td>
				<input type="text" name="E01COMA04"	size="12" maxlength="15" value="<%= msgInst.getE01COMA04() %>" onkeypress="enterDecimal()">
			</td>
			<td>
				<input type="text" name="E01COMI04"	size="12" maxlength="15" value="<%= msgInst.getE01COMI04() %>" onkeypress="enterDecimal()">
			</td>
			<td>
				<input type="text" name="E01COMM04"	size="12" maxlength="15" value="<%= msgInst.getE01COMM04() %>" onkeypress="enterDecimal()">
			</td>
			<td nowrap>
				<input type="text" name="E01COMC04" size="18" maxlength="16" value="<%= msgInst.getE01COMC04() %>"> 
				<a href="javascript:GetLedger('E01COMC04',document.forms[0].E01COMBNK.value,document.forms[0].E01CNTBCU.value,'')">
              	<img src="<%=request.getContextPath()%>/images/1b.gif" alt=". . ." align="absbottom" border="0" ></a>
			</td>
		</tr>
		<tr>
			<td>
				<input type="text" name="E01COMD05" size="35" maxlength="35" value="<%= msgInst.getE01COMD05() %>">
			</td>
			<td>
				<select name="E01COMT05">
					<option value="" <%if (msgInst.getE01COMT05().equals("")) { out.print("selected"); }%>></option>
                	<option value="F" <%if (msgInst.getE01COMT05().equals("F")) { out.print("selected"); }%>>Fija</option>
                	<option value="P" <%if (msgInst.getE01COMT05().equals("P")) { out.print("selected"); }%>>Porcentual</option>
              	</select> 
			</td>
			<td>
				<select name="E01COMO05">
					<option value="" <%if (msgInst.getE01COMO05().equals("")) { out.print("selected"); }%>></option>
                	<option value="P" <%if (msgInst.getE01COMO05().equals("P")) { out.print("selected"); }%>>Compra</option>
                	<option value="S" <%if (msgInst.getE01COMO05().equals("S")) { out.print("selected"); }%>>Venta</option>
                	<option value="C" <%if (msgInst.getE01COMO05().equals("C")) { out.print("selected"); }%>>Canje</option>
                	<option value="T" <%if (msgInst.getE01COMO05().equals("T")) { out.print("selected"); }%>>Todas</option>
              	</select> 
			</td>
			<td>
				<select name="E01COMP05">
					<option value="" <%if (msgInst.getE01COMP05().equals("")) { out.print("selected"); }%>></option>
                	<option value="1" <%if (msgInst.getE01COMP05().equals("1")) { out.print("selected"); }%>>Jur</option>
                	<option value="2" <%if (msgInst.getE01COMP05().equals("2")) { out.print("selected"); }%>>Nat</option>
                	<option value="3" <%if (msgInst.getE01COMP05().equals("3")) { out.print("selected"); }%>>Todas</option>
              	</select> 
			</td>
			<td>
				<input type="text" name="E01COMA05"	size="12" maxlength="15" value="<%= msgInst.getE01COMA05() %>" onkeypress="enterDecimal()">
			</td>
			<td>
				<input type="text" name="E01COMI05"	size="12" maxlength="15" value="<%= msgInst.getE01COMI05() %>" onkeypress="enterDecimal()">
			</td>
			<td>
				<input type="text" name="E01COMM05"	size="12" maxlength="15" value="<%= msgInst.getE01COMM05() %>" onkeypress="enterDecimal()">
			</td>
			<td nowrap>
				<input type="text" name="E01COMC05" size="18" maxlength="16" value="<%= msgInst.getE01COMC05() %>"> 
				<a href="javascript:GetLedger('E01COMC05',document.forms[0].E01COMBNK.value,document.forms[0].E01CNTBCU.value,'')">
              	<img src="<%=request.getContextPath()%>/images/1b.gif" alt=". . ." align="absbottom" border="0" ></a>
			</td>
		</tr>
		<tr>
			<td>
				<input type="text" name="E01COMD06" size="35" maxlength="35" value="<%= msgInst.getE01COMD06() %>">
			</td>
			<td>
				<select name="E01COMT06">
					<option value="" <%if (msgInst.getE01COMT06().equals("")) { out.print("selected"); }%>></option>
                	<option value="F" <%if (msgInst.getE01COMT06().equals("F")) { out.print("selected"); }%>>Fija</option>
                	<option value="P" <%if (msgInst.getE01COMT06().equals("P")) { out.print("selected"); }%>>Porcentual</option>
              	</select> 
			</td>
			<td>
				<select name="E01COMO06">
					<option value="" <%if (msgInst.getE01COMO06().equals("")) { out.print("selected"); }%>></option>
                	<option value="P" <%if (msgInst.getE01COMO06().equals("P")) { out.print("selected"); }%>>Compra</option>
                	<option value="S" <%if (msgInst.getE01COMO06().equals("S")) { out.print("selected"); }%>>Venta</option>
                	<option value="C" <%if (msgInst.getE01COMO06().equals("C")) { out.print("selected"); }%>>Canje</option>
                	<option value="T" <%if (msgInst.getE01COMO06().equals("T")) { out.print("selected"); }%>>Todas</option>
              	</select> 
			</td>
			<td>
				<select name="E01COMP06">
					<option value="" <%if (msgInst.getE01COMP06().equals("")) { out.print("selected"); }%>></option>
                	<option value="1" <%if (msgInst.getE01COMP06().equals("1")) { out.print("selected"); }%>>Jur</option>
                	<option value="2" <%if (msgInst.getE01COMP06().equals("2")) { out.print("selected"); }%>>Nat</option>
                	<option value="3" <%if (msgInst.getE01COMP06().equals("3")) { out.print("selected"); }%>>Todas</option>
              	</select> 
			</td>
			<td>
				<input type="text" name="E01COMA06"	size="12" maxlength="15" value="<%= msgInst.getE01COMA06() %>" onkeypress="enterDecimal()">
			</td>
			<td>
				<input type="text" name="E01COMI06"	size="12" maxlength="15" value="<%= msgInst.getE01COMI06() %>" onkeypress="enterDecimal()">
			</td>
			<td>
				<input type="text" name="E01COMM06"	size="12" maxlength="15" value="<%= msgInst.getE01COMM06() %>" onkeypress="enterDecimal()">
			</td>
			<td nowrap>
				<input type="text" name="E01COMC06" size="18" maxlength="16" value="<%= msgInst.getE01COMC06() %>"> 
				<a href="javascript:GetLedger('E01COMC06',document.forms[0].E01COMBNK.value,document.forms[0].E01CNTBCU.value,'')">
              	<img src="<%=request.getContextPath()%>/images/1b.gif" alt=". . ." align="absbottom" border="0" ></a>
			</td>
		</tr>
		<tr>
			<td>
				<input type="text" name="E01COMD07" size="35" maxlength="35" value="<%= msgInst.getE01COMD07() %>">
			</td>
			<td>
				<select name="E01COMT07">
					<option value="" <%if (msgInst.getE01COMT07().equals("")) { out.print("selected"); }%>></option>
                	<option value="F" <%if (msgInst.getE01COMT07().equals("F")) { out.print("selected"); }%>>Fija</option>
                	<option value="P" <%if (msgInst.getE01COMT07().equals("P")) { out.print("selected"); }%>>Porcentual</option>
              	</select> 
			</td>
			<td>
				<select name="E01COMO07">
					<option value="" <%if (msgInst.getE01COMO07().equals("")) { out.print("selected"); }%>></option>
                	<option value="P" <%if (msgInst.getE01COMO07().equals("P")) { out.print("selected"); }%>>Compra</option>
                	<option value="S" <%if (msgInst.getE01COMO07().equals("S")) { out.print("selected"); }%>>Venta</option>
                	<option value="C" <%if (msgInst.getE01COMO07().equals("C")) { out.print("selected"); }%>>Canje</option>
                	<option value="T" <%if (msgInst.getE01COMO07().equals("T")) { out.print("selected"); }%>>Todas</option>
              	</select> 
			</td>
			<td>
				<select name="E01COMP07">
					<option value="" <%if (msgInst.getE01COMP07().equals("")) { out.print("selected"); }%>></option>
                	<option value="1" <%if (msgInst.getE01COMP07().equals("1")) { out.print("selected"); }%>>Jur</option>
                	<option value="2" <%if (msgInst.getE01COMP07().equals("2")) { out.print("selected"); }%>>Nat</option>
                	<option value="3" <%if (msgInst.getE01COMP07().equals("3")) { out.print("selected"); }%>>Todas</option>
              	</select> 
			</td>
			<td>
				<input type="text" name="E01COMA07"	size="12" maxlength="15" value="<%= msgInst.getE01COMA07() %>" onkeypress="enterDecimal()">
			</td>
			<td>
				<input type="text" name="E01COMI07"	size="12" maxlength="15" value="<%= msgInst.getE01COMI07() %>" onkeypress="enterDecimal()">
			</td>
			<td>
				<input type="text" name="E01COMM07"	size="12" maxlength="15" value="<%= msgInst.getE01COMM07() %>" onkeypress="enterDecimal()">
			</td>
			<td nowrap>
				<input type="text" name="E01COMC07" size="18" maxlength="16" value="<%= msgInst.getE01COMC07() %>"> 
				<a href="javascript:GetLedger('E01COMC07',document.forms[0].E01COMBNK.value,document.forms[0].E01CNTBCU.value,'')">
              	<img src="<%=request.getContextPath()%>/images/1b.gif" alt=". . ." align="absbottom" border="0" ></a>
			</td>
		</tr>
		<tr>
			<td>
				<input type="text" name="E01COMD08" size="35" maxlength="35" value="<%= msgInst.getE01COMD08() %>">
			</td>
			<td>
				<select name="E01COMT08">
					<option value="" <%if (msgInst.getE01COMT08().equals("")) { out.print("selected"); }%>></option>
                	<option value="F" <%if (msgInst.getE01COMT08().equals("F")) { out.print("selected"); }%>>Fija</option>
                	<option value="P" <%if (msgInst.getE01COMT08().equals("P")) { out.print("selected"); }%>>Porcentual</option>
              	</select> 
			</td>
			<td>
				<select name="E01COMO08">
					<option value="" <%if (msgInst.getE01COMO08().equals("")) { out.print("selected"); }%>></option>
                	<option value="P" <%if (msgInst.getE01COMO08().equals("P")) { out.print("selected"); }%>>Compra</option>
                	<option value="S" <%if (msgInst.getE01COMO08().equals("S")) { out.print("selected"); }%>>Venta</option>
                	<option value="C" <%if (msgInst.getE01COMO08().equals("C")) { out.print("selected"); }%>>Canje</option>
                	<option value="T" <%if (msgInst.getE01COMO08().equals("T")) { out.print("selected"); }%>>Todas</option>
              	</select> 
			</td>
			<td>
				<select name="E01COMP08">
					<option value="" <%if (msgInst.getE01COMP08().equals("")) { out.print("selected"); }%>></option>
                	<option value="1" <%if (msgInst.getE01COMP08().equals("1")) { out.print("selected"); }%>>Jur</option>
                	<option value="2" <%if (msgInst.getE01COMP08().equals("2")) { out.print("selected"); }%>>Nat</option>
                	<option value="3" <%if (msgInst.getE01COMP08().equals("3")) { out.print("selected"); }%>>Todas</option>
              	</select> 
			</td>
			<td>
				<input type="text" name="E01COMA08"	size="12" maxlength="15" value="<%= msgInst.getE01COMA08() %>" onkeypress="enterDecimal()">
			</td>
			<td>
				<input type="text" name="E01COMI08"	size="12" maxlength="15" value="<%= msgInst.getE01COMI08() %>" onkeypress="enterDecimal()">
			</td>
			<td>
				<input type="text" name="E01COMM08"	size="12" maxlength="15" value="<%= msgInst.getE01COMM08() %>" onkeypress="enterDecimal()">
			</td>
			<td nowrap>
				<input type="text" name="E01COMC08" size="18" maxlength="16" value="<%= msgInst.getE01COMC08() %>"> 
				<a href="javascript:GetLedger('E01COMC08',document.forms[0].E01COMBNK.value,document.forms[0].E01CNTBCU.value,'')">
              	<img src="<%=request.getContextPath()%>/images/1b.gif" alt=". . ." align="absbottom" border="0" ></a>
			</td>
		</tr>
		<tr>
			<td>
				<input type="text" name="E01COMD09" size="35" maxlength="35" value="<%= msgInst.getE01COMD09() %>">
			</td>
			<td>
				<select name="E01COMT09">
					<option value="" <%if (msgInst.getE01COMT09().equals("")) { out.print("selected"); }%>></option>
                	<option value="F" <%if (msgInst.getE01COMT09().equals("F")) { out.print("selected"); }%>>Fija</option>
                	<option value="P" <%if (msgInst.getE01COMT09().equals("P")) { out.print("selected"); }%>>Porcentual</option>
              	</select> 
			</td>
			<td>
				<select name="E01COMO09">
					<option value="" <%if (msgInst.getE01COMO09().equals("")) { out.print("selected"); }%>></option>
                	<option value="P" <%if (msgInst.getE01COMO09().equals("P")) { out.print("selected"); }%>>Compra</option>
                	<option value="S" <%if (msgInst.getE01COMO09().equals("S")) { out.print("selected"); }%>>Venta</option>
                	<option value="C" <%if (msgInst.getE01COMO09().equals("C")) { out.print("selected"); }%>>Canje</option>
                	<option value="T" <%if (msgInst.getE01COMO09().equals("T")) { out.print("selected"); }%>>Todas</option>
              	</select> 
			</td>
			<td>
				<select name="E01COMP09">
					<option value="" <%if (msgInst.getE01COMP09().equals("")) { out.print("selected"); }%>></option>
                	<option value="1" <%if (msgInst.getE01COMP09().equals("1")) { out.print("selected"); }%>>Jur</option>
                	<option value="2" <%if (msgInst.getE01COMP09().equals("2")) { out.print("selected"); }%>>Nat</option>
                	<option value="3" <%if (msgInst.getE01COMP09().equals("3")) { out.print("selected"); }%>>Todas</option>
              	</select> 
			</td>
			<td>
				<input type="text" name="E01COMA09"	size="12" maxlength="15" value="<%= msgInst.getE01COMA09() %>" onkeypress="enterDecimal()">
			</td>
			<td>
				<input type="text" name="E01COMI09"	size="12" maxlength="15" value="<%= msgInst.getE01COMI09() %>" onkeypress="enterDecimal()">
			</td>
			<td>
				<input type="text" name="E01COMM09"	size="12" maxlength="15" value="<%= msgInst.getE01COMM09() %>" onkeypress="enterDecimal()">
			</td>
			<td nowrap>
				<input type="text" name="E01COMC09" size="18" maxlength="16" value="<%= msgInst.getE01COMC09() %>"> 
				<a href="javascript:GetLedger('E01COMC09',document.forms[0].E01COMBNK.value,document.forms[0].E01CNTBCU.value,'')">
              	<img src="<%=request.getContextPath()%>/images/1b.gif" alt=". . ." align="absbottom" border="0" ></a>
			</td>
		</tr>
		<tr>
			<td>
				<input type="text" name="E01COMD10" size="35" maxlength="35" value="<%= msgInst.getE01COMD10() %>">
			</td>
			<td>
				<select name="E01COMT10">
					<option value="" <%if (msgInst.getE01COMT10().equals("")) { out.print("selected"); }%>></option>
                	<option value="F" <%if (msgInst.getE01COMT10().equals("F")) { out.print("selected"); }%>>Fija</option>
                	<option value="P" <%if (msgInst.getE01COMT10().equals("P")) { out.print("selected"); }%>>Porcentual</option>
              	</select> 
			</td>
			<td>
				<select name="E01COMO10">
					<option value="" <%if (msgInst.getE01COMO10().equals("")) { out.print("selected"); }%>></option>
                	<option value="P" <%if (msgInst.getE01COMO10().equals("P")) { out.print("selected"); }%>>Compra</option>
                	<option value="S" <%if (msgInst.getE01COMO10().equals("S")) { out.print("selected"); }%>>Venta</option>
                	<option value="C" <%if (msgInst.getE01COMO10().equals("C")) { out.print("selected"); }%>>Canje</option>
                	<option value="T" <%if (msgInst.getE01COMO10().equals("T")) { out.print("selected"); }%>>Todas</option>
              	</select> 
			</td>
			<td>
				<select name="E01COMP10">
					<option value="" <%if (msgInst.getE01COMP10().equals("")) { out.print("selected"); }%>></option>
                	<option value="1" <%if (msgInst.getE01COMP10().equals("1")) { out.print("selected"); }%>>Jur</option>
                	<option value="2" <%if (msgInst.getE01COMP10().equals("2")) { out.print("selected"); }%>>Nat</option>
                	<option value="3" <%if (msgInst.getE01COMP10().equals("3")) { out.print("selected"); }%>>Todas</option>
              	</select> 
			</td>
			<td>
				<input type="text" name="E01COMA10"	size="12" maxlength="15" value="<%= msgInst.getE01COMA10() %>" onkeypress="enterDecimal()">
			</td>
			<td>
				<input type="text" name="E01COMI10"	size="12" maxlength="15" value="<%= msgInst.getE01COMI10() %>" onkeypress="enterDecimal()">
			</td>
			<td>
				<input type="text" name="E01COMM10"	size="12" maxlength="15" value="<%= msgInst.getE01COMM10() %>" onkeypress="enterDecimal()">
			</td>
			<td nowrap>
				<input type="text" name="E01COMC10" size="18" maxlength="16" value="<%= msgInst.getE01COMC10() %>"> 
				<a href="javascript:GetLedger('E01COMC10',document.forms[0].E01COMBNK.value,document.forms[0].E01CNTBCU.value,'')">
              	<img src="<%=request.getContextPath()%>/images/1b.gif" alt=". . ." align="absbottom" border="0" ></a>
			</td>
		</tr>
		<tr>
			<td>
				<input type="text" name="E01COMD11" size="35" maxlength="35" value="<%= msgInst.getE01COMD11() %>">
			</td>
			<td>
				<select name="E01COMT11">
					<option value="" <%if (msgInst.getE01COMT11().equals("")) { out.print("selected"); }%>></option>
                	<option value="F" <%if (msgInst.getE01COMT11().equals("F")) { out.print("selected"); }%>>Fija</option>
                	<option value="P" <%if (msgInst.getE01COMT11().equals("P")) { out.print("selected"); }%>>Porcentual</option>
              	</select> 
			</td>
			<td>
				<select name="E01COMO11">
					<option value="" <%if (msgInst.getE01COMO11().equals("")) { out.print("selected"); }%>></option>
                	<option value="P" <%if (msgInst.getE01COMO11().equals("P")) { out.print("selected"); }%>>Compra</option>
                	<option value="S" <%if (msgInst.getE01COMO11().equals("S")) { out.print("selected"); }%>>Venta</option>
                	<option value="C" <%if (msgInst.getE01COMO11().equals("C")) { out.print("selected"); }%>>Canje</option>
                	<option value="T" <%if (msgInst.getE01COMO11().equals("T")) { out.print("selected"); }%>>Todas</option>
              	</select> 
			</td>
			<td>
				<select name="E01COMP11">
					<option value="" <%if (msgInst.getE01COMP11().equals("")) { out.print("selected"); }%>></option>
                	<option value="1" <%if (msgInst.getE01COMP11().equals("1")) { out.print("selected"); }%>>Jur</option>
                	<option value="2" <%if (msgInst.getE01COMP11().equals("2")) { out.print("selected"); }%>>Nat</option>
                	<option value="3" <%if (msgInst.getE01COMP11().equals("3")) { out.print("selected"); }%>>Todas</option>
              	</select> 
			</td>
			<td>
				<input type="text" name="E01COMA11"	size="12" maxlength="15" value="<%= msgInst.getE01COMA11() %>" onkeypress="enterDecimal()">
			</td>
			<td>
				<input type="text" name="E01COMI11"	size="12" maxlength="15" value="<%= msgInst.getE01COMI11() %>" onkeypress="enterDecimal()">
			</td>
			<td>
				<input type="text" name="E01COMM11"	size="12" maxlength="15" value="<%= msgInst.getE01COMM11() %>" onkeypress="enterDecimal()">
			</td>
			<td nowrap>
				<input type="text" name="E01COMC11" size="18" maxlength="16" value="<%= msgInst.getE01COMC11() %>"> 
				<a href="javascript:GetLedger('E01COMC11',document.forms[0].E01COMBNK.value,document.forms[0].E01CNTBCU.value,'')">
              	<img src="<%=request.getContextPath()%>/images/1b.gif" alt=". . ." align="absbottom" border="0" ></a>
			</td>
		</tr>
		<tr>
			<td>
				<input type="text" name="E01COMD12" size="35" maxlength="35" value="<%= msgInst.getE01COMD12() %>">
			</td>
			<td>
				<select name="E01COMT12">
					<option value="" <%if (msgInst.getE01COMT12().equals("")) { out.print("selected"); }%>></option>
                	<option value="F" <%if (msgInst.getE01COMT12().equals("F")) { out.print("selected"); }%>>Fija</option>
                	<option value="P" <%if (msgInst.getE01COMT12().equals("P")) { out.print("selected"); }%>>Porcentual</option>
              	</select> 
			</td>
			<td>
				<select name="E01COMO12">
					<option value="" <%if (msgInst.getE01COMO12().equals("")) { out.print("selected"); }%>></option>
                	<option value="P" <%if (msgInst.getE01COMO12().equals("P")) { out.print("selected"); }%>>Compra</option>
                	<option value="S" <%if (msgInst.getE01COMO12().equals("S")) { out.print("selected"); }%>>Venta</option>
                	<option value="C" <%if (msgInst.getE01COMO12().equals("C")) { out.print("selected"); }%>>Canje</option>
                	<option value="T" <%if (msgInst.getE01COMO12().equals("T")) { out.print("selected"); }%>>Todas</option>
              	</select> 
			</td>
			<td>
				<select name="E01COMP12">
					<option value="" <%if (msgInst.getE01COMP12().equals("")) { out.print("selected"); }%>></option>
                	<option value="1" <%if (msgInst.getE01COMP12().equals("1")) { out.print("selected"); }%>>Jur</option>
                	<option value="2" <%if (msgInst.getE01COMP12().equals("2")) { out.print("selected"); }%>>Nat</option>
                	<option value="3" <%if (msgInst.getE01COMP12().equals("3")) { out.print("selected"); }%>>Todas</option>
              	</select> 
			</td>
			<td>
				<input type="text" name="E01COMA12"	size="12" maxlength="15" value="<%= msgInst.getE01COMA12() %>" onkeypress="enterDecimal()">
			</td>
			<td>
				<input type="text" name="E01COMI12"	size="12" maxlength="15" value="<%= msgInst.getE01COMI12() %>" onkeypress="enterDecimal()">
			</td>
			<td>
				<input type="text" name="E01COMM12"	size="12" maxlength="15" value="<%= msgInst.getE01COMM12() %>" onkeypress="enterDecimal()">
			</td>
			<td nowrap>
				<input type="text" name="E01COMC12" size="18" maxlength="16" value="<%= msgInst.getE01COMC12() %>"> 
				<a href="javascript:GetLedger('E01COMC12',document.forms[0].E01COMBNK.value,document.forms[0].E01CNTBCU.value,'')">
              	<img src="<%=request.getContextPath()%>/images/1b.gif" alt=". . ." align="absbottom" border="0" ></a>
			</td>
		</tr>
		<tr>
			<td>
				<input type="text" name="E01COMD13" size="35" maxlength="35" value="<%= msgInst.getE01COMD13() %>">
			</td>
			<td>
				<select name="E01COMT13">
					<option value="" <%if (msgInst.getE01COMT13().equals("")) { out.print("selected"); }%>></option>
                	<option value="F" <%if (msgInst.getE01COMT13().equals("F")) { out.print("selected"); }%>>Fija</option>
                	<option value="P" <%if (msgInst.getE01COMT13().equals("P")) { out.print("selected"); }%>>Porcentual</option>
              	</select> 
			</td>
			<td>
				<select name="E01COMO13">
					<option value="" <%if (msgInst.getE01COMO13().equals("")) { out.print("selected"); }%>></option>
                	<option value="P" <%if (msgInst.getE01COMO13().equals("P")) { out.print("selected"); }%>>Compra</option>
                	<option value="S" <%if (msgInst.getE01COMO13().equals("S")) { out.print("selected"); }%>>Venta</option>
                	<option value="C" <%if (msgInst.getE01COMO13().equals("C")) { out.print("selected"); }%>>Canje</option>
                	<option value="T" <%if (msgInst.getE01COMO13().equals("T")) { out.print("selected"); }%>>Todas</option>
              	</select> 
			</td>
			<td>
				<select name="E01COMP13">
					<option value="" <%if (msgInst.getE01COMP13().equals("")) { out.print("selected"); }%>></option>
                	<option value="1" <%if (msgInst.getE01COMP13().equals("1")) { out.print("selected"); }%>>Jur</option>
                	<option value="2" <%if (msgInst.getE01COMP13().equals("2")) { out.print("selected"); }%>>Nat</option>
                	<option value="3" <%if (msgInst.getE01COMP13().equals("3")) { out.print("selected"); }%>>Todas</option>
              	</select> 
			</td>
			<td>
				<input type="text" name="E01COMA13"	size="12" maxlength="15" value="<%= msgInst.getE01COMA13() %>" onkeypress="enterDecimal()">
			</td>
			<td>
				<input type="text" name="E01COMI13"	size="12" maxlength="15" value="<%= msgInst.getE01COMI13() %>" onkeypress="enterDecimal()">
			</td>
			<td>
				<input type="text" name="E01COMM13"	size="12" maxlength="15" value="<%= msgInst.getE01COMM13() %>" onkeypress="enterDecimal()">
			</td>
			<td nowrap>
				<input type="text" name="E01COMC13" size="18" maxlength="16" value="<%= msgInst.getE01COMC13() %>"> 
				<a href="javascript:GetLedger('E01COMC13',document.forms[0].E01COMBNK.value,document.forms[0].E01CNTBCU.value,'')">
              	<img src="<%=request.getContextPath()%>/images/1b.gif" alt=". . ." align="absbottom" border="0" ></a>
			</td>
		</tr>
		<tr>
			<td>
				<input type="text" name="E01COMD14" size="35" maxlength="35" value="<%= msgInst.getE01COMD14() %>">
			</td>
			<td>
				<select name="E01COMT14">
					<option value="" <%if (msgInst.getE01COMT14().equals("")) { out.print("selected"); }%>></option>
                	<option value="F" <%if (msgInst.getE01COMT14().equals("F")) { out.print("selected"); }%>>Fija</option>
                	<option value="P" <%if (msgInst.getE01COMT14().equals("P")) { out.print("selected"); }%>>Porcentual</option>
              	</select> 
			</td>
			<td>
				<select name="E01COMO14">
					<option value="" <%if (msgInst.getE01COMO14().equals("")) { out.print("selected"); }%>></option>
                	<option value="P" <%if (msgInst.getE01COMO14().equals("P")) { out.print("selected"); }%>>Compra</option>
                	<option value="S" <%if (msgInst.getE01COMO14().equals("S")) { out.print("selected"); }%>>Venta</option>
                	<option value="C" <%if (msgInst.getE01COMO14().equals("C")) { out.print("selected"); }%>>Canje</option>
                	<option value="T" <%if (msgInst.getE01COMO14().equals("T")) { out.print("selected"); }%>>Todas</option>
              	</select> 
			</td>
			<td>
				<select name="E01COMP14">
					<option value="" <%if (msgInst.getE01COMP14().equals("")) { out.print("selected"); }%>></option>
                	<option value="1" <%if (msgInst.getE01COMP14().equals("1")) { out.print("selected"); }%>>Jur</option>
                	<option value="2" <%if (msgInst.getE01COMP14().equals("2")) { out.print("selected"); }%>>Nat</option>
                	<option value="3" <%if (msgInst.getE01COMP14().equals("3")) { out.print("selected"); }%>>Todas</option>
              	</select> 
			</td>
			<td>
				<input type="text" name="E01COMA14"	size="12" maxlength="15" value="<%= msgInst.getE01COMA14() %>" onkeypress="enterDecimal()">
			</td>
			<td>
				<input type="text" name="E01COMI14"	size="12" maxlength="15" value="<%= msgInst.getE01COMI14() %>" onkeypress="enterDecimal()">
			</td>
			<td>
				<input type="text" name="E01COMM14"	size="12" maxlength="15" value="<%= msgInst.getE01COMM14() %>" onkeypress="enterDecimal()">
			</td>
			<td nowrap>
				<input type="text" name="E01COMC14" size="18" maxlength="16" value="<%= msgInst.getE01COMC14() %>"> 
				<a href="javascript:GetLedger('E01COMC14',document.forms[0].E01COMBNK.value,document.forms[0].E01CNTBCU.value,'')">
              	<img src="<%=request.getContextPath()%>/images/1b.gif" alt=". . ." align="absbottom" border="0" ></a>
			</td>
		</tr>
		<tr>
			<td>
				<input type="text" name="E01COMD15" size="35" maxlength="35" value="<%= msgInst.getE01COMD15() %>">
			</td>
			<td>
				<select name="E01COMT15">
					<option value="" <%if (msgInst.getE01COMT15().equals("")) { out.print("selected"); }%>></option>
                	<option value="F" <%if (msgInst.getE01COMT15().equals("F")) { out.print("selected"); }%>>Fija</option>
                	<option value="P" <%if (msgInst.getE01COMT15().equals("P")) { out.print("selected"); }%>>Porcentual</option>
              	</select> 
			</td>
			<td>
				<select name="E01COMO15">
					<option value="" <%if (msgInst.getE01COMO15().equals("")) { out.print("selected"); }%>></option>
                	<option value="P" <%if (msgInst.getE01COMO15().equals("P")) { out.print("selected"); }%>>Compra</option>
                	<option value="S" <%if (msgInst.getE01COMO15().equals("S")) { out.print("selected"); }%>>Venta</option>
                	<option value="C" <%if (msgInst.getE01COMO15().equals("C")) { out.print("selected"); }%>>Canje</option>
                	<option value="T" <%if (msgInst.getE01COMO15().equals("T")) { out.print("selected"); }%>>Todas</option>
              	</select> 
			</td>
			<td>
				<select name="E01COMP15">
					<option value="" <%if (msgInst.getE01COMP15().equals("")) { out.print("selected"); }%>></option>
                	<option value="1" <%if (msgInst.getE01COMP15().equals("1")) { out.print("selected"); }%>>Jur</option>
                	<option value="2" <%if (msgInst.getE01COMP15().equals("2")) { out.print("selected"); }%>>Nat</option>
                	<option value="3" <%if (msgInst.getE01COMP15().equals("3")) { out.print("selected"); }%>>Todas</option>
              	</select> 
			</td>
			<td>
				<input type="text" name="E01COMA15"	size="12" maxlength="15" value="<%= msgInst.getE01COMA15() %>" onkeypress="enterDecimal()">
			</td>
			<td>
				<input type="text" name="E01COMI15"	size="12" maxlength="15" value="<%= msgInst.getE01COMI15() %>" onkeypress="enterDecimal()">
			</td>
			<td>
				<input type="text" name="E01COMM15"	size="12" maxlength="15" value="<%= msgInst.getE01COMM15() %>" onkeypress="enterDecimal()">
			</td>
			<td nowrap>
				<input type="text" name="E01COMC15" size="18" maxlength="16" value="<%= msgInst.getE01COMC15() %>"> 
				<a href="javascript:GetLedger('E01COMC15',document.forms[0].E01COMBNK.value,document.forms[0].E01CNTBCU.value,'')">
              	<img src="<%=request.getContextPath()%>/images/1b.gif" alt=". . ." align="absbottom" border="0" ></a>
			</td>
		</tr>
		<tr>
			<td>
				<input type="text" name="E01COMD16" size="35" maxlength="35" value="<%= msgInst.getE01COMD16() %>">
			</td>
			<td>
				<select name="E01COMT16">
					<option value="" <%if (msgInst.getE01COMT16().equals("")) { out.print("selected"); }%>></option>
                	<option value="F" <%if (msgInst.getE01COMT16().equals("F")) { out.print("selected"); }%>>Fija</option>
                	<option value="P" <%if (msgInst.getE01COMT16().equals("P")) { out.print("selected"); }%>>Porcentual</option>
              	</select> 
			</td>
			<td>
				<select name="E01COMO16">
					<option value="" <%if (msgInst.getE01COMO16().equals("")) { out.print("selected"); }%>></option>
                	<option value="P" <%if (msgInst.getE01COMO16().equals("P")) { out.print("selected"); }%>>Compra</option>
                	<option value="S" <%if (msgInst.getE01COMO16().equals("S")) { out.print("selected"); }%>>Venta</option>
                	<option value="C" <%if (msgInst.getE01COMO16().equals("C")) { out.print("selected"); }%>>Canje</option>
                	<option value="T" <%if (msgInst.getE01COMO16().equals("T")) { out.print("selected"); }%>>Todas</option>
              	</select> 
			</td>
			<td>
				<select name="E01COMP16">
					<option value="" <%if (msgInst.getE01COMP16().equals("")) { out.print("selected"); }%>></option>
                	<option value="1" <%if (msgInst.getE01COMP16().equals("1")) { out.print("selected"); }%>>Jur</option>
                	<option value="2" <%if (msgInst.getE01COMP16().equals("2")) { out.print("selected"); }%>>Nat</option>
                	<option value="3" <%if (msgInst.getE01COMP16().equals("3")) { out.print("selected"); }%>>Todas</option>
              	</select> 
			</td>
			<td>
				<input type="text" name="E01COMA16"	size="12" maxlength="15" value="<%= msgInst.getE01COMA16() %>" onkeypress="enterDecimal()">
			</td>
			<td>
				<input type="text" name="E01COMI16"	size="12" maxlength="15" value="<%= msgInst.getE01COMI16() %>" onkeypress="enterDecimal()">
			</td>
			<td>
				<input type="text" name="E01COMM16"	size="12" maxlength="15" value="<%= msgInst.getE01COMM16() %>" onkeypress="enterDecimal()">
			</td>
			<td nowrap>
				<input type="text" name="E01COMC16" size="18" maxlength="16" value="<%= msgInst.getE01COMC16() %>"> 
				<a href="javascript:GetLedger('E01COMC16',document.forms[0].E01COMBNK.value,document.forms[0].E01CNTBCU.value,'')">
              	<img src="<%=request.getContextPath()%>/images/1b.gif" alt=". . ." align="absbottom" border="0" ></a>
			</td>
		</tr>
		<tr>
			<td>
				<input type="text" name="E01COMD17" size="35" maxlength="35" value="<%= msgInst.getE01COMD17() %>">
			</td>
			<td>
				<select name="E01COMT17">
					<option value="" <%if (msgInst.getE01COMT17().equals("")) { out.print("selected"); }%>></option>
                	<option value="F" <%if (msgInst.getE01COMT17().equals("F")) { out.print("selected"); }%>>Fija</option>
                	<option value="P" <%if (msgInst.getE01COMT17().equals("P")) { out.print("selected"); }%>>Porcentual</option>
              	</select> 
			</td>
			<td>
				<select name="E01COMO17">
					<option value="" <%if (msgInst.getE01COMO17().equals("")) { out.print("selected"); }%>></option>
                	<option value="P" <%if (msgInst.getE01COMO17().equals("P")) { out.print("selected"); }%>>Compra</option>
                	<option value="S" <%if (msgInst.getE01COMO17().equals("S")) { out.print("selected"); }%>>Venta</option>
                	<option value="C" <%if (msgInst.getE01COMO17().equals("C")) { out.print("selected"); }%>>Canje</option>
                	<option value="T" <%if (msgInst.getE01COMO17().equals("T")) { out.print("selected"); }%>>Todas</option>
              	</select> 
			</td>
			<td>
				<select name="E01COMP17">
					<option value="" <%if (msgInst.getE01COMP17().equals("")) { out.print("selected"); }%>></option>
                	<option value="1" <%if (msgInst.getE01COMP17().equals("1")) { out.print("selected"); }%>>Jur</option>
                	<option value="2" <%if (msgInst.getE01COMP17().equals("2")) { out.print("selected"); }%>>Nat</option>
                	<option value="3" <%if (msgInst.getE01COMP17().equals("3")) { out.print("selected"); }%>>Todas</option>
              	</select> 
			</td>
			<td>
				<input type="text" name="E01COMA17"	size="12" maxlength="15" value="<%= msgInst.getE01COMA17() %>" onkeypress="enterDecimal()">
			</td>
			<td>
				<input type="text" name="E01COMI17"	size="12" maxlength="15" value="<%= msgInst.getE01COMI17() %>" onkeypress="enterDecimal()">
			</td>
			<td>
				<input type="text" name="E01COMM17"	size="12" maxlength="15" value="<%= msgInst.getE01COMM17() %>" onkeypress="enterDecimal()">
			</td>
			<td nowrap>
				<input type="text" name="E01COMC17" size="18" maxlength="16" value="<%= msgInst.getE01COMC17() %>"> 
				<a href="javascript:GetLedger('E01COMC17',document.forms[0].E01COMBNK.value,document.forms[0].E01CNTBCU.value,'')">
              	<img src="<%=request.getContextPath()%>/images/1b.gif" alt=". . ." align="absbottom" border="0" ></a>
			</td>
		</tr>
		<tr>
			<td>
				<input type="text" name="E01COMD18" size="35" maxlength="35" value="<%= msgInst.getE01COMD18() %>">
			</td>
			<td>
				<select name="E01COMT18">
					<option value="" <%if (msgInst.getE01COMT18().equals("")) { out.print("selected"); }%>></option>
                	<option value="F" <%if (msgInst.getE01COMT18().equals("F")) { out.print("selected"); }%>>Fija</option>
                	<option value="P" <%if (msgInst.getE01COMT18().equals("P")) { out.print("selected"); }%>>Porcentual</option>
              	</select> 
			</td>
			<td>
				<select name="E01COMO18">
					<option value="" <%if (msgInst.getE01COMO18().equals("")) { out.print("selected"); }%>></option>
                	<option value="P" <%if (msgInst.getE01COMO18().equals("P")) { out.print("selected"); }%>>Compra</option>
                	<option value="S" <%if (msgInst.getE01COMO18().equals("S")) { out.print("selected"); }%>>Venta</option>
                	<option value="C" <%if (msgInst.getE01COMO18().equals("C")) { out.print("selected"); }%>>Canje</option>
                	<option value="T" <%if (msgInst.getE01COMO18().equals("T")) { out.print("selected"); }%>>Todas</option>
              	</select> 
			</td>
			<td>
				<select name="E01COMP18">
					<option value="" <%if (msgInst.getE01COMP18().equals("")) { out.print("selected"); }%>></option>
                	<option value="1" <%if (msgInst.getE01COMP18().equals("1")) { out.print("selected"); }%>>Jur</option>
                	<option value="2" <%if (msgInst.getE01COMP18().equals("2")) { out.print("selected"); }%>>Nat</option>
                	<option value="3" <%if (msgInst.getE01COMP18().equals("3")) { out.print("selected"); }%>>Todas</option>
              	</select> 
			</td>
			<td>
				<input type="text" name="E01COMA18"	size="12" maxlength="15" value="<%= msgInst.getE01COMA18() %>" onkeypress="enterDecimal()">
			</td>
			<td>
				<input type="text" name="E01COMI18"	size="12" maxlength="15" value="<%= msgInst.getE01COMI18() %>" onkeypress="enterDecimal()">
			</td>
			<td>
				<input type="text" name="E01COMM18"	size="12" maxlength="15" value="<%= msgInst.getE01COMM18() %>" onkeypress="enterDecimal()">
			</td>
			<td nowrap>
				<input type="text" name="E01COMC18" size="18" maxlength="16" value="<%= msgInst.getE01COMC18() %>"> 
				<a href="javascript:GetLedger('E01COMC18',document.forms[0].E01COMBNK.value,document.forms[0].E01CNTBCU.value,'')">
              	<img src="<%=request.getContextPath()%>/images/1b.gif" alt=". . ." align="absbottom" border="0" ></a>
			</td>
		</tr>
		<tr>
			<td>
				<input type="text" name="E01COMD19" size="35" maxlength="35" value="<%= msgInst.getE01COMD19() %>">
			</td>
			<td>
				<select name="E01COMT19">
					<option value="" <%if (msgInst.getE01COMT19().equals("")) { out.print("selected"); }%>></option>
                	<option value="F" <%if (msgInst.getE01COMT19().equals("F")) { out.print("selected"); }%>>Fija</option>
                	<option value="P" <%if (msgInst.getE01COMT19().equals("P")) { out.print("selected"); }%>>Porcentual</option>
              	</select> 
			</td>
			<td>
				<select name="E01COMO19">
					<option value="" <%if (msgInst.getE01COMO19().equals("")) { out.print("selected"); }%>></option>
                	<option value="P" <%if (msgInst.getE01COMO19().equals("P")) { out.print("selected"); }%>>Compra</option>
                	<option value="S" <%if (msgInst.getE01COMO19().equals("S")) { out.print("selected"); }%>>Venta</option>
                	<option value="C" <%if (msgInst.getE01COMO19().equals("C")) { out.print("selected"); }%>>Canje</option>
                	<option value="T" <%if (msgInst.getE01COMO19().equals("T")) { out.print("selected"); }%>>Todas</option>
              	</select> 
			</td>
			<td>
				<select name="E01COMP19">
					<option value="" <%if (msgInst.getE01COMP19().equals("")) { out.print("selected"); }%>></option>
                	<option value="1" <%if (msgInst.getE01COMP19().equals("1")) { out.print("selected"); }%>>Jur</option>
                	<option value="2" <%if (msgInst.getE01COMP19().equals("2")) { out.print("selected"); }%>>Nat</option>
                	<option value="3" <%if (msgInst.getE01COMP19().equals("3")) { out.print("selected"); }%>>Todas</option>
              	</select> 
			</td>
			<td>
				<input type="text" name="E01COMA19"	size="12" maxlength="15" value="<%= msgInst.getE01COMA19() %>" onkeypress="enterDecimal()">
			</td>
			<td>
				<input type="text" name="E01COMI19"	size="12" maxlength="15" value="<%= msgInst.getE01COMI19() %>" onkeypress="enterDecimal()">
			</td>
			<td>
				<input type="text" name="E01COMM19"	size="12" maxlength="15" value="<%= msgInst.getE01COMM19() %>" onkeypress="enterDecimal()">
			</td>
			<td nowrap>
				<input type="text" name="E01COMC19" size="18" maxlength="16" value="<%= msgInst.getE01COMC19() %>"> 
				<a href="javascript:GetLedger('E01COMC19',document.forms[0].E01COMBNK.value,document.forms[0].E01CNTBCU.value,'')">
              	<img src="<%=request.getContextPath()%>/images/1b.gif" alt=". . ." align="absbottom" border="0" ></a>
			</td>
		</tr>
		<tr>
			<td>
				<input type="text" name="E01COMD20" size="35" maxlength="35" value="<%= msgInst.getE01COMD20() %>">
			</td>
			<td>
				<select name="E01COMT20">
					<option value="" <%if (msgInst.getE01COMT20().equals("")) { out.print("selected"); }%>></option>
                	<option value="F" <%if (msgInst.getE01COMT20().equals("F")) { out.print("selected"); }%>>Fija</option>
                	<option value="P" <%if (msgInst.getE01COMT20().equals("P")) { out.print("selected"); }%>>Porcentual</option>
              	</select> 
			</td>
			<td>
				<select name="E01COMO20">
					<option value="" <%if (msgInst.getE01COMO20().equals("")) { out.print("selected"); }%>></option>
                	<option value="P" <%if (msgInst.getE01COMO20().equals("P")) { out.print("selected"); }%>>Compra</option>
                	<option value="S" <%if (msgInst.getE01COMO20().equals("S")) { out.print("selected"); }%>>Venta</option>
                	<option value="C" <%if (msgInst.getE01COMO20().equals("C")) { out.print("selected"); }%>>Canje</option>
                	<option value="T" <%if (msgInst.getE01COMO20().equals("T")) { out.print("selected"); }%>>Todas</option>
              	</select> 
			</td>
			<td>
				<select name="E01COMP20">
					<option value="" <%if (msgInst.getE01COMP20().equals("")) { out.print("selected"); }%>></option>
                	<option value="1" <%if (msgInst.getE01COMP20().equals("1")) { out.print("selected"); }%>>Jur</option>
                	<option value="2" <%if (msgInst.getE01COMP20().equals("2")) { out.print("selected"); }%>>Nat</option>
                	<option value="3" <%if (msgInst.getE01COMP20().equals("3")) { out.print("selected"); }%>>Todas</option>
              	</select> 
			</td>
			<td>
				<input type="text" name="E01COMA20"	size="12" maxlength="15" value="<%= msgInst.getE01COMA20() %>" onkeypress="enterDecimal()">
			</td>
			<td>
				<input type="text" name="E01COMI20"	size="12" maxlength="15" value="<%= msgInst.getE01COMI20() %>" onkeypress="enterDecimal()">
			</td>
			<td>
				<input type="text" name="E01COMM20"	size="12" maxlength="15" value="<%= msgInst.getE01COMM20() %>" onkeypress="enterDecimal()">
			</td>
			<td nowrap>
				<input type="text" name="E01COMC20" size="18" maxlength="16" value="<%= msgInst.getE01COMC20() %>"> 
				<a href="javascript:GetLedger('E01COMC20',document.forms[0].E01COMBNK.value,document.forms[0].E01CNTBCU.value,'')">
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
