<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.0 Transitional//EN">
<html>

<head>
<link Href="<%=request.getContextPath()%>/pages/style.css" rel="stylesheet">
<script language="Javascript1.1" src="<%=request.getContextPath()%>/pages/s/javascripts/eIBS.jsp"> </SCRIPT>
<script language="JavaScript">

function enter(){
var Bank = document.forms[0].Bank.value;
var Branch = document.forms[0].Branch.value;
var Currency = document.forms[0].Currency.value;
var PrdType = document.forms[0].PrdType.value;
var OldAcc = document.forms[0].OldAcc.value;
var NewAcc = document.forms[0].NewAcc.value;
var Customer = document.forms[0].Customer.value;
var FromRecord = 0;

parent.results.window.location.href="<%=request.getContextPath()%>/pages/s/MISC_search_wait.jsp?URL='<%=request.getContextPath()%>/servlet/datapro.eibs.helps.JSEWD0406?selOldAcc=" + OldAcc + "@FromRecord=" + FromRecord +
					"@selBank=" + Bank + "@selBranch=" + Branch + "@selPrdType=" + PrdType + "@selCurrency=" + Currency + "@selNewAcc=" + NewAcc + "@selCustomer=" + Customer + "'";
}
</script>
</head>

<body>
<h3 align="center">Consulta de Cuenta Vieja - Cuenta Nueva<img src="<%=request.getContextPath()%>/images/e_ibs.gif" align="left" name="EIBS_GIF" alt="old_acc_help_search, EWD0406"></h3>
<hr size="4">

<FORM Action="javascript:enter()">

  <table id="tbhelp" align="center" width="100%" border="0" cellspacing="0" cellpadding="0">
	<tr>
		<td align="right" width="25%"></td>
		<TD align="left" width="25%"><B>Cuenta a Buscar</B></TD>
		<TD align="center" width="25%"> <B>ó Lista de Cuentas filtrada por</B></TD>
		<TD align="left" width="25%"></TD>
	</tr>
    <tr>
		<TD align="right" width="25%">Cliente : </TD><TD align="left" width="25%">
			<INPUT type="text" name="Customer" size="10" maxlength="9">
				<A href="javascript:GetCustomerDescId('Customer','','')">
					<IMG src="<%=request.getContextPath()%>/images/1b.gif" alt="Ayuda" border="0"></A>
		</TD>
		
		<TD align="right" width="25%">Banco : </TD>
		<TD align="left" width="25%"> 
		<INPUT type="text" name="Bank" size="3" maxlength="2"></TD>
	</tr>
	<TR>
		<TD align="right" width="25%"><B></B> ó Número de Cuenta Vieja : </TD>
		<TD align="left" width="25%"><INPUT type="text" name="OldAcc"
			size="16" maxlength="17"></TD>
		<TD align="right" width="25%"> Sucursal / Moneda : </TD>
		<TD align="left" width="25%"> <INPUT
			type="text" name="Branch" size="4" maxlength="3">
			  <A href="javascript:GetBranch('Branch','')"> <IMG
			src="<%=request.getContextPath()%>/images/1b.gif" alt="Ayuda"
			border="0"></A> / 
			<INPUT type="text" name="Currency" size="4" maxlength="3">
				<A href="javascript:GetCurrency('Currency','')"> <IMG
			src="<%=request.getContextPath()%>/images/1b.gif" alt="Ayuda"
			border="0"></A>
		</TD>
	</TR>
	<TR>
		<TD align="right" width="25%">ó Cuenta Nueva : </TD>
		<TD align="left" width="25%"><INPUT type="text" name="NewAcc"
			size="13" maxlength="12"> <A
			href="javascript:GetAccount('NewAcc','','','')"> <IMG
			src="<%=request.getContextPath()%>/images/1b.gif" alt="Ayuda"
			align="bottom" border="0"></A></TD>
		<TD align="right" width="25%">Tipo de Producto : </TD>
		<TD align="left" width="25%">
			<INPUT type="text" name="PrdType" size="5" maxlength="4">
				<A href="javascript:GetCodeCNOFC('PrdType','04')"> 
					<IMG src="<%=request.getContextPath()%>/images/1b.gif" alt="Ayuda" align="middle" border="0">
				</A>
		</TD>
	</TR>
</table>
<div align=center>
<INPUT id="EIBSBTN" type="submit" name="Submit" value="Enviar">
</div>

</form>
<script language="JavaScript">
  document.forms[0].OldAcc.focus();
</script>
</body>
</html>
