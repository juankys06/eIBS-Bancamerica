<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<title>Consulta de Saldos</title>
<META HTTP-EQUIV="Content-Type" CONTENT="text/html; charset=ISO-8859-1">
<META name="GENERATOR" content="IBM WebSphere Studio">
<link Href="<%=request.getContextPath()%>/pages/style.css" rel="stylesheet">

</head>

<jsp:useBean id="rtBalances" class="datapro.eibs.beans.EDD009001Message"  scope="session" />

<jsp:useBean id= "error" class= "datapro.eibs.beans.ELEERRMessage"  scope="session" />

<jsp:useBean id= "userPO" class= "datapro.eibs.beans.UserPos"  scope="session" />

<jsp:useBean id= "currUser" class= "datapro.eibs.beans.ESS0030DSMessage"  scope="session" />

<body>

<script language="Javascript1.1" src="<%=request.getContextPath()%>/pages/s/javascripts/eIBS.jsp"> </SCRIPT>
 
<script language="Javascript1.1" src="<%=request.getContextPath()%>/pages/s/javascripts/optMenu.jsp"> </SCRIPT>
 

<SCRIPT Language="Javascript">

<%
if ( userPO.getOption().equals("RT") ) {
%>
	builtNewMenu(rt_i_opt);
<%   
}
else if ( userPO.getOption().equals("SV") ) {
%>
	builtNewMenu(sv_i_opt);
<%   
}
%>

</SCRIPT>
<% 
 if ( !error.getERRNUM().equals("0")  ) {
     out.println("<SCRIPT Language=\"Javascript\">");
     error.setERRNUM("0");
     out.println("       showErrors()");
     out.println("</SCRIPT>");
 }
   out.println("<SCRIPT> initMenu(); </SCRIPT>");
%> 
<H3 align="center">Consulta de Saldos<img src="<%=request.getContextPath()%>/images/e_ibs.gif" align="left" name="EIBS_GIF" ALT="rt_inq_balances, EDD0000"> 
  </H3>
<hr size="4">
<form method="post" action="<%=request.getContextPath()%>/servlet/datapro.eibs.products.JSEXEDD0000" >
  <INPUT TYPE=HIDDEN NAME="SCREEN" value="31">
  
  <table border="0" cellspacing="0" cellpadding="0" width="100%">
  	<tr>
  		<td align="left" valign="top" width="10%"><h4><%=rtBalances.getE01DSCAST()  %></h4></td>  		
  		<td align="right" valign="top" width="85%" style="color:red;font-size:12;"><b><%=rtBalances.getE01PENDAP()%></b></td>
  		<td width="5%"></td>
  	</tr>
  </table> 
<table class="tableinfo" border="0">
	<tr>
		<td nowrap>
		<table cellspacing="0" cellpadding="2" width="100%" border="0"
			class="tbhead">
			<tr id="trdark">
				<td nowrap width="14%">
				<div align="right"><b>Cliente :</b></div>
				</td>
				<td nowrap width="9%">
				<div align="left"><input type="text" name="E02CUN2" size="9"
					maxlength="9" readonly value="<%= userPO.getHeader2().trim()%>"></div>
				</td>
				<td nowrap width="12%">
				<div align="right"><b>Nombre :</b></div>
				</td>
				<td nowrap>
				<div align="left"><input type="text" name="E02NA12" size="45"
					maxlength="45" readonly value="<%= userPO.getHeader3().trim()%>"></div>
				</td>
				<td nowrap>
				<div align="right"><b>Producto : </b></div>
				</td>
				<td nowrap><b> <input type="text" name="E02PRO2" size="4"
					maxlength="4" readonly value="<%= userPO.getHeader1().trim()%>"> </b></td>
			</tr>
			<tr id="trdark">
				<td nowrap width="14%">
				<div align="right"><b>Cuenta :</b></div>
				</td>
				<td nowrap width="9%">
				<div align="left"><input type="text" name="E02ACC" size="12"
					maxlength="12" value="<%= userPO.getIdentifier().trim()%>" readonly>
				</div>
				</td>
				<td nowrap width="12%">
				<div align="right">Oficial :</div>
				</td>
				<td nowrap width="33%">
				<div align="left"><b> <input type="text" name="E02NA122" size="30"
					maxlength="30" readonly value="<%= userPO.getOfficer().trim()%>"> </b>
				</div>
				</td>
				<td nowrap width="11%">
				<div align="right"><b>Moneda : </b></div>
				</td>
				<td nowrap width="21%">
				<div align="left"><b> <input type="text" name="E01DEACCY" size="3"
					maxlength="3" value="<%= userPO.getCurrency().trim()%>" readonly> </b>
				</div>
				</td>
			</tr>
			<%if(currUser.getE01INT().equals("03")){%>
			<TR id="trdark">
				<TD nowrap width="14%" align="right" colspan="3"><B>Cuenta SUDEBAN:</B></TD>
				<TD nowrap width="33%">
	<!--			<INPUT type="text" name="E01FMTBCC_A" size="5" value="%=rtBalances.getE01FMTBCC().trim().substring(0,4) %>"
						readonly maxlength="4"> 
					<INPUT type="text" name="E01FMTBCC_B" size="5" value="%=rtBalances.getE01FMTBCC().trim().substring(4,8) %>"
						readonly maxlength="4"> 
					<INPUT type="text" name="E01FMTBCC_C" size="3" value="%=rtBalances.getE01FMTBCC().trim().substring(8,10) %>"
						readonly maxlength="2"> 
					<INPUT type="text" name="E01FMTBCC_A0" size="11" value="%=rtBalances.getE01FMTBCC().trim().substring(10,20) %>"
						readonly maxlength="10">
	-->
				</TD>
				<TD nowrap width="11%"></TD>
				<TD nowrap width="21%"></TD>
			</TR>
			<% } %>
		</table>
		</td>
	</tr>
</table>
<h4>Saldos</h4>
<table class="tableinfo">
	<tr bordercolor="#FFFFFF">
		<td nowrap>
		<table cellspacing="0" cellpadding="2" width="100%" border="0">
			<tr id="trdark">
				<td nowrap width="29%">
				<div align="right">Saldo en Libros :</div>
				</td>
				<td nowrap width="22%"><input type="text" name="E01ACMMGB" size="17"
					maxlength="17" value="<%= rtBalances.getE01ACMMGB().trim()%>"
					readonly></td>
				<td nowrap width="23%">
				<div align="right">Promedio Contable :</div>
				</td>
				<td nowrap width="26%"><input type="text" name="E01ACMGAV" readonly
					value="<%= rtBalances.getE01ACMGAV().trim()%>" size="17"
					maxlength="17">
			</tr>
			<tr id="trclear">
				<td nowrap width="29%">
				<div align="right">Saldo Diferido :</div>
				</td>
				<td nowrap width="22%"><input type="text" name="E01UNCBAL" size="17"
					maxlength="17" value="<%= rtBalances.getE01UNCBAL().trim()%>"
					readonly></td>
				<td nowrap width="23%">
				<div align="right">Promedio Neto :</div>
				</td>
				<td nowrap width="26%"><input type="text" name="E01ACMNAV" size="17"
					maxlength="17" value="<%= rtBalances.getE01ACMNAV().trim()%>"
					readonly></td>
			</tr>
			<tr id="trdark">
				<td nowrap width="29%" height="23">
				<div align="right">Saldo Neto :</div>
				</td>
				<td nowrap width="22%" height="23"><input type="text"
					name="E01ACMMNB" size="17" maxlength="17"
					value="<%= rtBalances.getE01ACMMNB().trim()%>" readonly></td>
				<td nowrap width="23%" height="23">
				<div align="right">Saldo Inicio Día :</div>
				</td>
				<td nowrap width="26%" height="23"><INPUT type="text"
					name="E01ACMGBL" size="17" maxlength="17"
					value="<%= rtBalances.getE01ACMGBL().trim()%>" readonly></td>
			</tr>
			<tr id="trclear">
				<td nowrap width="29%" height="19">
				<div align="right">Saldo Retenido :</div>
				</td>
				<td nowrap width="22%" height="19"><input type="text" size="17"
					maxlength="17" name="E01ACMHAM"
					value="<%= rtBalances.getE01ACMHAM().trim()%>" readonly></td>
				<td nowrap width="23%" height="19">
				<div align="right">Saldo Inicio de Ciclo :</div>
				</td>
				<td nowrap width="26%" height="19"><INPUT type="text"
					name="E01ACMLSB" size="17" maxlength="17"
					value="<%= rtBalances.getE01ACMLSB().trim()%>" readonly></td>
			</tr>
			<tr id="trdark">
				<td nowrap width="29%">
				<div align="right"><B>Saldo Disponible :</B></div>
				</td>
				<td nowrap width="22%"><input type="text" name="E01AVALBL" size="17"
					maxlength="17" value="<%= rtBalances.getE01AVALBL().trim()%>"
					readonly style="font-weight : bold"></td>
				<td nowrap width="23%">
				<div align="right">Saldo Inicio Año :</div>
				</td>
				<td nowrap width="26%"><input type="text" name="E01ACMLYB" size="17"
					maxlength="17" value="<%= rtBalances.getE01ACMLYB().trim()%>"
					readonly></td>
			</tr>
			<tr id="trclear">
				<td nowrap width="29%">
				<div align="right">Disponible Línea Crédito :</div>
				</td>
				<td nowrap width="22%"><INPUT type="text" name="E01LNESAL" size="17"
					maxlength="17" value="<%= rtBalances.getE01LNESAL().trim()%>"
					readonly></td>
				<td nowrap width="23%">
				<div align="right"><% if ( userPO.getOption().equals("RT") ) out.print("Utilizado Linea Credito :"); else out.print("Saldo en Libreta :");%>
				</div>
				</td>
				<td nowrap width="26%"><INPUT type="text" size="17" maxlength="17"
					name="E01ACMPBB" value="<%= rtBalances.getE01ACMPBB().trim()%>"
					readonly></td>
			</tr>
			<tr id="trdark">
				<td nowrap width="29%">
				<div align="right">Límite de Crédito :</div>
				</td>
				<td nowrap width="22%"><INPUT type="text" name="E01ACMCLI" size="17"
					maxlength="17" value="<%= rtBalances.getE01ACMCLI().trim()%>"
					readonly></td>
				<td nowrap width="23%">
				<div align="right">Balance de Prestamo :</div>
				</td>
				<td nowrap width="26%"><INPUT type="text" size="17" maxlength="17"
					name="E01LNSAMT" value="<%= rtBalances.getE01LNSAMT().trim()%>"
					readonly></td>
			</tr>
		</table>
		</td>
	</tr>
</table>
<H4>Diferidos</H4>
<table class="tableinfo">
	<tr bordercolor="#FFFFFF">
		<td nowrap>
		<table cellspacing="0" cellpadding="2" width="100%" border="0">
			<tr id="trdark">
				<td nowrap width="30%">
				<div align="right">Diferido 1 D&iacute;a :</div>
				</td>
				<td nowrap width="21%"><input type="text" name="E01ACMUL1" size="15"
					maxlength="15" value="<%= rtBalances.getE01ACMUL1().trim()%>"
					readonly></td>
				<td nowrap width="25%">
				<div align="right">Diferido 3 D&iacute;as :</div>
				</td>
				<td nowrap width="24%"><input type="text" name="E01ACMUL3" size="15"
					maxlength="15" value="<%= rtBalances.getE01ACMUL3().trim()%>"
					readonly></td>
			</tr>
			<tr id="trclear">
				<td nowrap width="30%">
				<div align="right">Diferido 2 D&iacute;as :</div>
				</td>
				<td nowrap width="21%"><input type="text" name="E01ACMUL2" size="15"
					maxlength="15" value="<%= rtBalances.getE01ACMUL2().trim()%>"
					readonly></td>
				<td nowrap width="25%">
				<div align="right">Diferido + 3 D&iacute;as :</div>
				</td>
				<td nowrap width="24%"><input type="text" name="E01ACMFL1" size="15"
					maxlength="15" value="<%= rtBalances.getE01ACMFL1().trim()%>"
					readonly></td>
			</tr>
			<tr id="trdark">
				<td nowrap width="30%">
				<div align="right">Diferido Indefinido :</div>
				</td>
				<td nowrap width="21%"><input type="text" name="E01ACMFL2" size="15"
					maxlength="15" value="<%= rtBalances.getE01ACMFL2().trim()%>"
					readonly></td>
				<td nowrap width="25%">
				<div align="right"></div>
				</td>
				<td nowrap width="24%">&nbsp;</td>
			</tr>
		</table>
		</td>
	</tr>
</table>
<H4>Intereses por Sobregiro</H4>
<table class="tableinfo">
	<tr bordercolor="#FFFFFF">
		<td nowrap>
		<table cellspacing="0" cellpadding="2" width="100%" border="0">
			<tr id="trdark">
				<td nowrap width="30%">
				<div align="right">Inter&eacute;s por Cobrar :</div>
				</td>
				<td nowrap width="18%"><input type="text" name="E01ACMOIA" size="15"
					maxlength="15" value="<%= rtBalances.getE01ACMOIA().trim()%>"
					readonly></td>
				<td nowrap width="28%">
				<div align="right">Pago Acumulado (Anual) :</div>
				</td>
				<td nowrap width="24%"><font face="Arial" size="2"> <input
					type="text" name="E01ACMOIY" size="15" maxlength="15"
					value="<%= rtBalances.getE01ACMOIY().trim()%>" readonly> </font></td>
			</tr>
			<tr id="trclear">
				<td nowrap width="30%">
				<div align="right">N&uacute;mero de Veces (Total) :</div>
				</td>
				<td nowrap width="18%"><input type="text" name="E01ACMTOD" size="5"
					maxlength="5" value="<%= rtBalances.getE01ACMTOD().trim()%>"
					readonly></td>
				<td nowrap width="28%">
				<div align="right">N&uacute;mero de Veces (Anual) :</div>
				</td>
				<td nowrap width="24%"><font face="Arial" size="2"> <input
					type="text" name="E01ACMDOA" size="5" maxlength="5"
					value="<%= rtBalances.getE01ACMDOA().trim()%>" readonly> </font></td>
			</tr>
			<tr id="trdark">
				<td nowrap width="30%" height="23">
				<div align="right">Total de D&iacute;as (Anual) :</div>
				</td>
				<td nowrap width="18%" height="23"><input type="text"
					name="E01ACMTOY" size="5" maxlength="5"
					value="<%= rtBalances.getE01ACMTOY().trim()%>" readonly></td>
				<td nowrap width="28%" height="23">
				<div align="right">Total de D&iacute;as (Ciclo) :</div>
				</td>
				<td nowrap width="24%" height="23"><input type="text"
					name="E01ACMCDO" size="5" maxlength="5"
					value="<%= rtBalances.getE01ACMCDO().trim()%>" readonly></td>
			</tr>
		</table>
		</td>
	</tr>
</table>
<H4>Intereses por Pagar</H4>
<table class="tableinfo">
	<tr bordercolor="#FFFFFF">
		<td nowrap>
		<table cellspacing="0" cellpadding="2" width="100%" border="0">
			<tr id="trdark">
				<td nowrap width="23%">
				<div align="right">Pago Total :</div>
				</td>
				<td nowrap width="23%"><input type="text" name="E01ACMIPL" size="13"
					maxlength="13" value="<%= rtBalances.getE01ACMIPL().trim()%>"
					readonly></td>
				<td nowrap width="22%">
				<div align="right">Pago Anual :</div>
				</td>
				<td nowrap width="32%"><input type="text" name="E01ACMIPY" size="13"
					maxlength="13" value="<%= rtBalances.getE01ACMIPY().trim()%>"
					readonly></td>
			</tr>
			<tr id="trclear">
				<td nowrap width="23%">
				<div align="right">Pendientes :</div>
				</td>
				<td nowrap width="23%"><input type="text" name="E01ACMIAC" size="15"
					maxlength="15" value="<%= rtBalances.getE01ACMIAC().trim()%>"
					readonly></td>
				<td nowrap width="22%">
				<div align="right"></div>
				</td>
				<td nowrap width="32%">&nbsp;</td>
			</tr>
		</table>
		</td>
	</tr>
</table>
<h4>Fechas</h4>
<table class="tableinfo">
	<tr bordercolor="#FFFFFF">
		<td nowrap>
		<table cellspacing="0" cellpadding="2" width="100%" border="0">
			<tr id="trdark">
				<td nowrap width="29%">
				<div align="right">Ultima Transacci&oacute;n :</div>
				</td>
				<td nowrap width="16%"><input type="text" maxlength="2" size="2"
					name="E01LSTRD1" value="<%= rtBalances.getE01LSTRD1().trim()%>"
					readonly> <input type="text" maxlength="2" size="2"
					name="E01LSTRD2" value="<%= rtBalances.getE01LSTRD2().trim()%>"
					readonly> <input type="text" maxlength="2" size="2"
					name="E01LSTRD3" value="<%= rtBalances.getE01LSTRD3().trim()%>"
					readonly></td>
				<td nowrap width="27%">
				<div align="right">Ultimo Dep&oacute;sito :</div>
				</td>
				<td nowrap width="28%"><input type="text" maxlength="2" size="2"
					name="E01LSTDD1" value="<%= rtBalances.getE01LSTDD1().trim()%>"
					readonly> <input type="text" maxlength="2" size="2"
					name="E01LSTDD2" value="<%= rtBalances.getE01LSTDD2().trim()%>"
					readonly> <input type="text" maxlength="2" size="2"
					name="E01LSTDD3" value="<%= rtBalances.getE01LSTDD3().trim()%>"
					readonly></td>
			</tr>
			<tr id="trclear">
				<td nowrap width="29%">
				<div align="right">Ultimo C&aacute;lculo de Interes :</div>
				</td>
				<td nowrap width="16%"><input type="text" maxlength="2" size="2"
					name="E01LSTCL1" value="<%= rtBalances.getE01LSTCL1().trim()%>"
					readonly> <input type="text" maxlength="2" size="2"
					name="E01LSTCL2" value="<%= rtBalances.getE01LSTCL2().trim()%>"
					readonly> <input type="text" maxlength="2" size="2"
					name="E01LSTCL3" value="<%= rtBalances.getE01LSTCL3().trim()%>"
					readonly></td>
				<td nowrap width="27%">
				<div align="right">Ultimo Cargo por Servicio :</div>
				</td>
				<td nowrap width="28%"><input type="text" maxlength="2" size="2"
					name="E01LSCOD1" value="<%= rtBalances.getE01LSCOD1().trim()%>"
					readonly> <input type="text" maxlength="2" size="2"
					name="E01LSCOD2" value="<%= rtBalances.getE01LSCOD2().trim()%>"
					readonly> <input type="text" maxlength="2" size="2"
					name="E01LSCOD3" value="<%= rtBalances.getE01LSCOD3().trim()%>"
					readonly></td>
			</tr>
			<tr id="trdark">
				<td nowrap width="29%">
				<div align="right">Ultimo Sobregiro :</div>
				</td>
				<td nowrap width="16%"><input type="text" maxlength="2" size="2"
					name="E01LSTOD1" value="<%= rtBalances.getE01LSTOD1().trim()%>"
					readonly> <input type="text" maxlength="2" size="2"
					name="E01LSTOD2" value="<%= rtBalances.getE01LSTOD2().trim()%>"
					readonly> <input type="text" maxlength="2" size="2"
					name="E01LSTOD3" value="<%= rtBalances.getE01LSTOD3().trim()%>"
					readonly></td>
				<td nowrap width="27%">
				<div align="right">Ultimo Estado de Cuentas:</div>
				</td>
				<td nowrap width="28%"><input type="text" maxlength="2" size="2"
					name="E01LSSTM1" value="<%= rtBalances.getE01LSSTM1().trim()%>"
					readonly> <input type="text" maxlength="2" size="2"
					name="E01LSSTM2" value="<%= rtBalances.getE01LSSTM2().trim()%>"
					readonly> <input type="text" maxlength="2" size="2"
					name="E01LSSTM3" value="<%= rtBalances.getE01LSSTM3().trim()%>"
					readonly></td>
			</tr>
		</table>
		</td>
	</tr>
</table>
<h4>Totales por Transacci&oacute;n</h4>
<table class="tableinfo">
	<tr bordercolor="#FFFFFF">
		<td nowrap>
		<table cellspacing="0" cellpadding="2" width="100%" border="0">
			<tr id="trdark">
				<td nowrap width="29%" height="33">
				<div align="right">Total D&eacute;bitos :</div>
				</td>
				<td nowrap width="16%" height="33"><font face="Arial" size="2"> <input
					type="text" maxlength="5" size="5" name="E01ACMNDR"
					value="<%= rtBalances.getE01ACMNDR().trim()%>" readonly> </font></td>
				<td nowrap width="27%" height="33">
				<div align="right">Total Cr&eacute;ditos :</div>
				</td>
				<td nowrap width="28%" height="33"><input type="text" maxlength="5"
					size="5" name="E01ACMNCR"
					value="<%= rtBalances.getE01ACMNCR().trim()%>" readonly></td>
			</tr>
			<tr id="trclear">
				<td nowrap width="29%">
				<div align="right">Cheques Depositados :</div>
				</td>
				<td nowrap width="16%"><input type="text" maxlength="5" size="5"
					name="E01ACMIDC" value="<%= rtBalances.getE01ACMIDC().trim()%>"
					readonly></td>
				<td nowrap width="27%">
				<div align="right">Cheques Emitidos :</div>
				</td>
				<td nowrap width="28%"><input type="text" name="E01ACMNCK"
					maxlength="5" size="5"
					value="<%= rtBalances.getE01ACMNCK().trim()%>" readonly></td>
			</tr>
			<tr id="trdark">
				<td nowrap width="29%">
				<div align="right">Total Depositado :</div>
				</td>
				<td nowrap width="16%"><input type="text" name="E01ACMNDP"
					maxlength="5" size="5"
					value="<%= rtBalances.getE01ACMNDP().trim()%>" readonly></td>
				<td nowrap width="27%">
				<div align="right">Uso L&iacute;nea de Cr&eacute;dito :</div>
				</td>
				<td nowrap width="28%"><input type="text" name="E01ACMCLY" size="5"
					maxlength="5" value="<%= rtBalances.getE01ACMCLY().trim()%>"
					readonly></td>
			</tr>
		</table>
		</td>
	</tr>
</table>
<h4>Devoluciones</h4>
<table class="tableinfo">
	<tr bordercolor="#FFFFFF">
		<td nowrap>
		<table cellspacing="0" cellpadding="2" width="100%" border="0">
			<tr id="trdark">
				<td nowrap width="43%">
				<div align="right">Devoluciones por Fondos :</div>
				</td>
				<td nowrap width="24%"><input type="text" maxlength="5" size="5"
					name="E01ACMTNS" value="<%= rtBalances.getE01ACMTNS().trim()%>"
					readonly></td>
				<td nowrap width="33%">
				<div align="right"></div>
				</td>
			</tr>
			<tr id="trclear">
				<td nowrap width="43%">&nbsp;</td>
				<td nowrap width="24%">
				<div align="center">Forma</div>
				</td>
				<td nowrap width="33%">
				<div align="center">Fondos</div>
				</td>
			</tr>
			<tr id="trdark">
				<td nowrap width="43%">
				<div align="right">Ciclo :</div>
				</td>
				<td nowrap width="24%">
				<div align="center"><input type="text" maxlength="5" size="5"
					name="E01ACMNRY" value="<%= rtBalances.getE01ACMNRY().trim()%>"
					readonly></div>
				</td>
				<td nowrap width="33%">
				<div align="center"><input type="text" maxlength="5" size="5"
					name="E01ACMNRL" value="<%= rtBalances.getE01ACMNRL().trim()%>"
					readonly></div>
				</td>
			</tr>
			<tr id="trclear">
				<td nowrap width="43%">
				<div align="right">Anual :</div>
				</td>
				<td nowrap width="24%">
				<div align="center"><input type="text" maxlength="5" size="5"
					name="E01ACMDV3" value="<%= rtBalances.getE01ACMDV3().trim()%>"
					readonly></div>
				</td>
				<td nowrap width="33%">
				<div align="center"><input type="text" maxlength="5" size="5"
					name="E01ACMDV1" value="<%= rtBalances.getE01ACMDV1().trim()%>"
					readonly></div>
				</td>
			</tr>
			<tr id="trdark">
				<td nowrap width="43%">
				<div align="right">Total :</div>
				</td>
				<td nowrap width="24%">
				<div align="center"><input type="text" maxlength="5" size="5"
					name="E01ACMDV4" value="<%= rtBalances.getE01ACMDV4().trim()%>"
					readonly></div>
				</td>
				<td nowrap width="33%">
				<div align="center"><input type="text" maxlength="5" size="5"
					name="E01ACMDV2" value="<%= rtBalances.getE01ACMDV2().trim()%>"
					readonly></div>
				</td>
			</tr>
		</table>
		</td>
	</tr>
</table>
<h4>Otros</h4>
<table class="tableinfo">
	<tr bordercolor="#FFFFFF">
		<td nowrap>
		<table cellspacing="0" cellpadding="2" width="100%" border="0">
			<tr id="trdark">
				<td nowrap width="23%">
				<div align="right">N&uacute;mero de L&iacute;nea de Cr&eacute;dito :</div>
				</td>
				<td nowrap width="23%"><input type="text" maxlength="2" size="2"
					name="E01ACMPBL" value="<%= rtBalances.getE01ACMPBL().trim()%>"
					readonly></td>
				<td nowrap width="22%">
				<div align="right">N&uacute;mero de Libreta :</div>
				</td>
				<td nowrap width="32%"><input type="text" name="E01ACMPBN" size="8"
					maxlength="8" value="<%= rtBalances.getE01ACMPBN().trim()%>"
					readonly></td>
			</tr>
		</table>
		</td>
	</tr>
</table>
<p align="left">&nbsp;</p>
</form>
</body>
</html>
