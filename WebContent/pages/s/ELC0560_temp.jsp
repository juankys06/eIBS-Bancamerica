<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.0 Transitional//EN">
<html>
<head>
<title>Negociaciones de Cartas de Creditos</title>
<meta HTTP-EQUIV="Content-Type" CONTENT="text/html; charset=ISO-8859-1">
<meta name="GENERATOR" content="IBM WebSphere Page Designer V4.0 for Windows">
<meta http-equiv="Content-Style-Type" content="text/css">
<link Href="<%=request.getContextPath()%>/pages/style.css" rel="stylesheet">
<%@ page import = "datapro.eibs.master.Util" %>

<jsp:useBean id= "msg" class= "datapro.eibs.beans.ELC056001Message"  scope="session" />
<jsp:useBean id= "error" class= "datapro.eibs.beans.ELEERRMessage"  scope="session" />
<jsp:useBean id= "userPO" class= "datapro.eibs.beans.UserPos"  scope="session" />

<script language="Javascript1.1" src="<%=request.getContextPath()%>/pages/s/javascripts/eIBS.jsp"> </script>
<script language="Javascript1.1" src="<%=request.getContextPath()%>/pages/s/javascripts/optMenu.jsp"> </script>


<script language="Javascript">

function enviar1(){

	document.forms[0].btenviar.disabled = true;
	document.forms[0].submit();

}

</script>
<script Language="Javascript">
  builtHPopUp();
  
  function showPopUp(opth,field,bank,ccy,field1,field2,opcod) {
   init(opth,field,bank,ccy,field1,field2,opcod);
   showPopupHelp();
  }

</script>

<script Language="Javascript">
  
  function showTab(index, name){  
  	for(var i=0;i<4;i++){
   		document.all["Tab"+i].className="tabnormal";
   		document.all["dataTab"+i].style.display="none";
   	}
  
    document.all["Tab"+index].className="tabhighlight";  
  	document.all["dataTab"+index].style.display="";
  	document.all[name].focus();
  }
 
  function showTabB(index,name){  
  	for(var i=0;i<4;i++){
   		document.all["TabB"+i].className="tabnormal";
   		document.all["dataTabB"+i].style.display="none";
   	}
  
    document.all["TabB"+index].className="tabhighlight";  
  	document.all["dataTabB"+index].style.display="";
  	document.all[name].focus();
  }
 
 
  }

</script>

</head>

<body>

<h3 align="center">Pago/Negociación de Cartas de Crédito<img src="<%=request.getContextPath()%>/images/e_ibs.gif" align="left" name="EIBS_GIF" ALT="lc_negotiation_maint.jsp,ELC0560"></h3>
<hr size="4">

<form method="post" action="<%=request.getContextPath()%>/servlet/datapro.eibs.trade.JSELC0560">
 
    <input type=HIDDEN name="SCREEN" value="2">
    <input type=HIDDEN name="TRANSFTYPE" value="YL">
    <input type=HIDDEN name="E01REQCON" value="2">
    
    <div id="DivHead">
    <table class="tableinfo">
    <tr bgcolor="#ffffff"> 
      <td nowrap height="79" width="100%">
		<table cellspacing="0" cellpadding="2" width="100%" border="0">
			<tr id="trdark">
				<td align="left" valign="top" height="24" width="128">Cliente:</td>
				<td nowrap valign="top" height="24" width="284"><div align="left">
					<input type="text" name="E01LCMCUN" size="20" maxlength="9" value="<%= msg.getE01LCMCUN()%>"></div>
				</td>
				<td nowrap align="left" valign="top" height="24" width="56">Nombre:</td>
				<td nowrap align="left" valign="top" height="24" width="231">
					<input type="text" name="E01CUSNA1" size="46" maxlength="45" value="<%= msg.getE01CUSNA1()%>">
				</td>
			</tr>
			<tr id="trclear">
				<td align="left" valign="top" height="24" width="128">Carta de Crédito:</td>
				<td nowrap valign="top" height="24" width="284">
					<input type="text" name="E01LCRNUM" size="23" maxlength="12" value="<%= msg.getE01LCRNUM()%>">
				</td>
				<td nowrap align="left" valign="top" height="24" width="56">Moneda:</td>
				<td nowrap align="left" valign="top" height="24" width="291">
					<input type="text" name="E01LCMCCY" size="8" maxlength="3" value="<%= msg.getE01LCMCCY()%>">
					&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; Producto: 
					<input type="text" name="E01LCMPRO" size="8" maxlength="8" value="<%= msg.getE01LCMPRO()%>">
				</td>
			</tr>
			<tr id="trdark">
				<td align="left" valign="top" height="29" width="128">Tipo de Negociación:</td>
				<td nowrap valign="top" height="29" width="284">
					<input type="text" name="E01UPCODE" size="4" maxlength="1" value="<%= msg.getE01OPCODE()%>"> 
					<input type="text" name="E01UPCDSC" size="25" maxlength="25" value="<%= msg.getE01OPCDSC()%>">
				</td>
				<td nowrap align="left" valign="top" height="29" width="56">Monto:</td>
				<td nowrap align="left" valign="top" height="29" width="291">
					<input type="text" name="E01DRWAMN" size="26" maxlength="15" value="<%= msg.getE01DRWAMN()%>">
				</td>
			</tr>						
		</table>
		</td>
    </tr>
   </table>        
 </div>
  <div id="OtherOpt"><br>
<b>Cuenta Débito Principal</b><table class="tableinfo" height="52">
	<tbody>
		<tr bgcolor="#ffffff">
			<td nowrap width="100%" height="52">
			<table cellspacing="0" cellpadding="2" width="100%" border="0">
				<tbody>
					<tr id="trdark">
						<td align="center" valign="top" height="24" width="230">Concepto</td>
						<td nowrap valign="top" height="24" align="center" width="51">Banco</td>
						<td nowrap valign="top" height="24" align="center" width="61">Agencia</td>
						<td nowrap align="center" valign="top" height="24" width="47">MDA</td>
						<td nowrap align="center" valign="top" height="24" width="128">Cuenta Contable</td>
						<td nowrap align="center" valign="top" height="24" width="132">Cuenta</td>
						<td nowrap align="center" valign="top" height="24" width="114">Centro de Costo</td>
					</tr>
					<tr id="trclear">
						<td align="left" valign="top" height="24" width="230"><input
							type="text" name="E01CONCDR" size="4" maxlength="2"> <input
							type="text" name="CTADESC1" size="25" maxlength="25"
							value="CTADESC1"></td>
						<td nowrap valign="top" height="24" align="center" width="51"><input
							type="text" name="E01CUSBNK" size="4" maxlength="2"></td>
						<td nowrap valign="top" height="24" align="center" width="61"><input
							type="text" name="E01CUSBRN" size="6" maxlength="3"></td>
						<td nowrap align="center" valign="top" height="24" width="47"><input
							type="text" name="E01CUSCCY" size="6" maxlength="3"></td>
						<td nowrap align="center" valign="top" height="24" width="128"><input
							type="text" name="E01CUSGLN" size="20" maxlength="16"></td>
						<td nowrap align="center" valign="top" height="24" width="132"><input
							type="text" name="E01CUSACC" size="18" maxlength="12"></td>
						<td nowrap align="center" valign="top" height="24" width="114"><input
							type="text" name="E01CUSCCN" size="13" maxlength="8"></td>
					</tr>
				</tbody>
			</table>
			</td>
		</tr>
	</tbody>
</table>
<br>
<b>Cuenta Crédito</b><table class="tableinfo">

	<tbody>
		<tr bgcolor="#ffffff">
			<td nowrap width="100%" height="52">
			<table cellspacing="0" cellpadding="2" width="100%" border="0">
				<tbody>
					<tr id="trdark">
						<td align="center" valign="top" height="24" width="230">Concepto</td>
						<td nowrap valign="top" height="24" align="center" width="51">Banco</td>
						<td nowrap valign="top" height="24" align="center" width="61">Agencia</td>
						<td nowrap align="center" valign="top" height="24" width="47">MDA</td>
						<td nowrap align="center" valign="top" height="24" width="128">Cuenta Contable</td>
						<td nowrap align="center" valign="top" height="24" width="132">Cuenta</td>
						<td nowrap align="center" valign="top" height="24" width="114">Centro de Costo</td>
					</tr>
					<tr id="trclear">
						<td align="left" valign="top" height="24" width="230"><input
							type="text" name="E01GNCCR" size="4" maxlength="2"> <input
							type="text" name="CTADESC2" size="25" maxlength="25"
							value="CTADESC2"></td>
						<td nowrap valign="top" height="24" align="center" width="51"><input
							type="text" name="E01PMTBNK" size="4" maxlength="2"></td>
						<td nowrap valign="top" height="24" align="center" width="61"><input
							type="text" name="E01PMTBRN" size="6" maxlength="3"></td>
						<td nowrap align="center" valign="top" height="24" width="47"><input
							type="text" name="E01PMTCCY" size="6" maxlength="3"></td>
						<td nowrap align="center" valign="top" height="24" width="128"><input
							type="text" name="E01PMTGLN" size="20" maxlength="16"></td>
						<td nowrap align="center" valign="top" height="24" width="132"><input
							type="text" name="E01PMTACC" size="18" maxlength="12"></td>
						<td nowrap align="center" valign="top" height="24" width="114"><input
							type="text" name="E01PMTCCN" size="13" maxlength="8"></td>
					</tr>
				</tbody>
			</table></td>
		</tr>
	</tbody>
</table>
<br>
</div>
<table class="tableinfo" height="47">
	<tbody>
		<tr bgcolor="#ffffff">
			<td nowrap height="79" width="100%">
			<table cellspacing="0" cellpadding="2" width="100%" border="0">
				<tbody>
					<tr id="trdark">
						<td align="left" valign="top" height="24" width="233">Forma / Vía de Pago:</td>
						<td nowrap valign="top" align="left" width="115"><select
							name="E01PMTVIA">
							<option value="1" <%if (msg.getE01PMTVIA().equals("1")) { out.print("selected"); }%>>Cheque Oficial</option>
							<option value="2" <%if (msg.getE01PMTVIA().equals("2")) { out.print("selected"); }%>>Depósito Cta. Cte.</option>
							<option value="3" <%if (msg.getE01PMTVIA().equals("3")) { out.print("selected"); }%>>Cuenta Contable</option>
							<option value="5" <%if (msg.getE01PMTVIA().equals("5")) { out.print("selected"); }%>>Swift</option>
						</select></td>
						<td nowrap valign="top" height="24" align="left" width="200"></td>
						<td nowrap align="left" valign="top" height="24" width="170">Garantía en Efectivo:</td>
						<td nowrap align="left" valign="top" height="24" width="160"><input
							type="text" name="E01CSHAMN" size="18" maxlength="15"></td>
					</tr>
					<tr id="trclear">
						<td align="left" valign="top" height="24" width="233">Debitar Comisiones a Cta Beneficiario:</td>
						<td nowrap valign="top" align="left" width="115">
						<input type="radio" name="E01DEBFLG" value="Y" <%if (msg.getE01DEBFLG().equals("Y")) out.print("checked"); %>> Si 
                		<input type="radio" name="E01DEBFLG" value="N" <%if (msg.getE01DEBFLG().equals("N")) out.print("checked"); %>> No
				</td>
						<td nowrap valign="top" height="24" align="left" width="200"></td>
						<td nowrap align="left" valign="top" height="24" width="170">Cta. Cte. Beneficiario:</td>
						<td nowrap align="left" valign="top" height="24" width="160"><input
							type="text" name="E01LCMBAC" size="16" maxlength="12"></td>
					</tr>
					<tr id="trdark">
						<td align="left" valign="top" height="24" width="233">Cuenta débito Comisiones e Impuestos:</td>
						<td nowrap valign="top" align="left" width="115"><input
							type="text" name="E01DEBACC" size="19" maxlength="12"></td>
						<td nowrap valign="top" height="24" align="left" width="200"></td>
						<td nowrap align="left" valign="top" height="24" width="170">Cancelar Saldo Remanente:</td>
						<td nowrap align="left" valign="top" height="24" width="160">
						<input type="radio" name="E01CANBAL" value="Y" <%if (msg.getE01CANBAL().equals("Y")) out.print("checked"); %>> Si 
                		<input type="radio" name="E01CANBAL" value="N" <%if (msg.getE01CANBAL().equals("N")) out.print("checked"); %>> No
				</td>
					</tr>
				</tbody>
			</table>
			</td>
		</tr>
	</tbody>
</table>
<br>
<b>Tipos de Cambio</b><table class="tableinfo">
	<tbody>
		<tr align="left">
			<td align="center" height="83" width="100%">
			<div align="center">
			<table id="headTable1" cellspacing="0" cellpadding="2" width="100%"	border="0">
				<tbody>
					<tr id="trdark">
						<td nowrap align="center" width="77">Moneda</td>
						<td nowrap align="center" width="143">T/C Compra</td>
						<td nowrap align="center" width="142">T/C Venta</td>
						<td nowrap align="center" width="120">Moneda</td>
						<td nowrap align="center" width="126">T/C Compra</td>
						<td nowrap align="center" width="150">T/C Venta</td>
					</tr>
				</tbody>
			</table>

			<div id="dataDiv1">
			<table id="dataTable1" cellspacing="0" cellpadding="2" width="100%"
				border="0">
				<tbody>
					<tr id="trclear">
						<td nowrap align="center" height="26" width="80"><input type="text"
							name="E01FXCCY1" size="9" maxlength="3"></td>
						<td nowrap height="26" width="142" align="center">
						<div align="center"><input type="text" name="E01FXRP1" size="20"
							maxlength="11"></div>
						</td>
						<td nowrap height="26" width="144" align="center">
						<div align="center"><input type="text" name="E01FXRSA1" size="20"
							maxlength="11"></div>
						</td>
						<td nowrap height="26" width="123" align="center">

						<div align="center"><input type="text" name="E01FXCCY3" size="9"
							maxlength="3"></div>
						</td>
						<td nowrap height="26" width="100" align="center"><input type="text"
							name="E01FXRP3" size="20" maxlength="11"></td>
						<td nowrap height="26" width="151" align="center">
						<div align="center"><input type="text" name="E01FXRSA3" size="20"
							maxlength="11"></div>
						</td>
					</tr>
					<tr id="trdark">
						<td nowrap align="center" width="80" height="32">
						<div align="center"><input type="text" name="E01FXCCY2" size="9"
							maxlength="3"></div>
						</td>
						<td nowrap width="142" align="center" height="32">
						<div align="center"><input type="text" name="E01FXRP2" size="20"
							maxlength="11"></div>
						</td>
						<td nowrap width="144" align="center" height="32">
						<div align="center"><input type="text" name="E01FXRSA2" size="20"
							maxlength="11"></div>
						</td>
						<td nowrap width="123" align="center" height="32">
						<div align="center"><input type="text" name="E01FXCCY4" size="9"
							maxlength="3"></div>
						</td>
						<td nowrap width="100" align="center" height="32"><input type="text"
							name="E01FXRP4" size="20" maxlength="11"></td>
						<td nowrap width="151" align="center" height="32">
						<div align="center"><input type="text" name="E01FXRSA4" size="20"
							maxlength="11"></div>
						</td>
					</tr>
				</tbody>
			</table>
			</div>
			</div>
			</td>
		</tr>
	</tbody>
</table>
<p><br>
<%	String cells[]= {
	"Emisión",				"E01ISSFEE", "E01ISSPBY", "Aviso",					"E01ADVCOM", "E01ADVPBY",
	"Confirmación",			"E01CNFCOM", "E01CNFPBY", "Enmienda",				"E01AMDFEE", "E01AMDPBY",
	"Aviso de Enmienda",	"E01ADVAMF", "E01AAMPBY", "Discrepancia",			"E01DISCOM", "E01DISPBY",
	"Extensión de Validez",	"E01EOVCOM", "E01EOVPBY", "Portes",					"E01POSTAM", "E01POSPBY",
	"Courier",				"E01COUROM", "E01COUPBY", "Swift de Apertura",		"E01SWFFEE", "E01SWFPBY",
	"Swift Adicional(es)",	"E01TLXFEE", "E01TLXPBY", "Cancelación",			"E01CANFEE", "E01CANPBY",
	"Convenio ALADI",		"E01ALDCOM", "E01ALDPBY", "Comisión de Agente",		"E01BRKCOM", "E01BRKPBY",
	"Intereses",			"E01INTCHG", "E01INTPBY", "Pago",					"E01PAYCOM", "E01PMCPBY",
	"Reembolso",			"E01RMBCOM", "E01RMBPBY", "Gastos de Corresponsal",	"E01CRPCHG", "E01CRPPBY",
	"Aceptacion",			"E01ACPCOM", "E01ACCPBY", "Comision Por Plazo",		"E01TRMCOM", "E01TRMPBY"};
%>
  
    <b>Comisiones y Gastos</b></p>
<p><table class="tableinfo">

	<tbody>
		<tr align="left">
			<td align="center" height="83" width="100%">
			<div align="center">
			<table id="headTable10" cellspacing="0" cellpadding="2" width="100%"
				border="0">
				<tbody>
					<tr id="trdark">
						<td nowrap align="center" width="150">Comisión</td>
						<td nowrap align="center" width="99">Monto</td>
						<td nowrap align="center" width="143">Por</td>
						<td nowrap align="center" width="192">Comisión</td>
						<td nowrap align="center" width="104">Monto</td>
						<td nowrap align="center" width="152">Por</td>
					</tr>
				</tbody>
			</table>

			<div id="dataDiv10">
			<table id="dataTable10" cellspacing="0" cellpadding="2" width="100%"
				border="0">
				<tbody>
				<%for( int i = 0; i < cells.length;){%>
					<tr id="trclear">
						<td nowrap align="right"><%=cells[i++]%></td>
						<td nowrap align="center">
							<input type="text" name="<%=cells[i]%>"  value="<%=msg.getField(cells[i++]).getString()%>"
								size="14" maxlength="13"></td>
						<td nowrap align="center">
							<input type="text" name="<%=cells[i]%>"  value="<%=msg.getField(cells[i++]).getString()%>"
								size="2" maxlength="2"></td>
						<td nowrap align="center"><%=cells[i++]%></td>
						<td nowrap align="center">
							<input type="text" name="<%=cells[i]%>"  value="<%=msg.getField(cells[i++]).getString()%>"
								size="14" maxlength="13"></td>
						<td nowrap align="center">
							<input type="text" name="<%=cells[i]%>"  value="<%=msg.getField(cells[i++]).getString()%>"
								size="2" maxlength="2"></td>
					</tr>
				<%}%>
				</tbody>
			</table>
			</div>
			</div>
			</td>
		</tr>
	</tbody>
</table>
<%
	datapro.eibs.tools.TableInfo table = new datapro.eibs.tools.TableInfo(msg);
	
	for(int i = 0; i < cells.length; )
	{
		table.newRow();
		table.newCol("right");
		table.addLabel(cells[i++]);
		
		table.newCol("center");
		table.addInput(cells[i++], 14, 13);
		
		table.newCol("center");
		table.addInput(cells[i++], 2);
		
		table.newCol("right");
		table.addLabel(cells[i++]);
		
		table.newCol("center");
		table.addInput(cells[i++], 14, 13);
	}
	
	out.print(table.getHtml());
	
%>
<p>---------------------------------------------------------</p>
<p><b>Comisiones y Gastos</b>
</p>
<table class="tableinfo">

	<tbody>
		<tr align="left">
			<td align="center" height="83" width="100%">
			<div align="center">
			<table id="headTable10" cellspacing="0" cellpadding="2" width="100%"
				border="0">
				<tbody>
					<tr id="trdark">
						<td nowrap align="center" width="150">Comisión</td>
						<td nowrap align="center" width="99">Monto</td>
						<td nowrap align="center" width="143">Por</td>
						<td nowrap align="center" width="192">Comisión</td>
						<td nowrap align="center" width="104">Monto</td>
						<td nowrap align="center" width="152">Por</td>
					</tr>
				</tbody>
			</table>

			<div id="dataDiv10">
			<table id="dataTable10" cellspacing="0" cellpadding="2" width="100%"
				border="0">
				<tbody>
					<tr id="trclear">
						<td nowrap align="center" height="26" width="150">Emisión</td>
						<td nowrap height="26" align="center" width="86">
						<div align="center"><input type="text" name="E01FXRP10" size="16"
							maxlength="11"></div>						</td>
						<td nowrap height="26" width="144" align="center">
						<div align="center"><input type="text" name="E01FXRSA10" size="6"
							maxlength="11"></div>						</td>
						<td nowrap height="26" align="center" width="190">Aviso</td>
						<td nowrap height="26" align="center" width="68"><input
							type="text" name="E01FXRP30" size="16" maxlength="11"></td>
						<td nowrap height="26" width="151" align="center">
						<div align="center"><input type="text" name="E01FXRSA30" size="5"
							maxlength="11"></div>						</td>
					</tr>
					<tr id="trdark">
						<td nowrap align="center" height="26" width="150">Confirmación</td>
						<td nowrap height="26" align="center" width="86"></td>
						<td nowrap height="26" width="144" align="center"></td>
						<td nowrap height="26" align="center" width="190">Enmienda</td>
						<td nowrap height="26" align="center" width="68"></td>
						<td nowrap height="26" width="151" align="center"></td>
					</tr>
					<tr id="trclear">
						<td nowrap align="center" height="26" width="150">Aviso de Enmienda</td>
						<td nowrap height="26" align="center" width="86"></td>
						<td nowrap height="26" width="144" align="center"></td>
						<td nowrap height="26" align="center" width="190">Discrepancia</td>
						<td nowrap height="26" align="center" width="68"></td>
						<td nowrap height="26" width="151" align="center"></td>
					</tr>
					<tr id="trdark">
						<td nowrap align="center" height="26" width="150">Extensión de Validez</td>
						<td nowrap height="26" align="center" width="86"></td>
						<td nowrap height="26" width="144" align="center"></td>
						<td nowrap height="26" align="center" width="190">Portes</td>
						<td nowrap height="26" align="center" width="68"></td>
						<td nowrap height="26" width="151" align="center"></td>
					</tr>
					<tr id="trclear">
						<td nowrap align="center" height="26" width="150">Courier</td>
						<td nowrap height="26" align="center" width="86"></td>
						<td nowrap height="26" width="144" align="center"></td>
						<td nowrap height="26" align="center" width="190">Swift de Apertura</td>
						<td nowrap height="26" align="center" width="68"></td>
						<td nowrap height="26" width="151" align="center"></td>
					</tr>
					<tr id="trdark">
						<td nowrap align="center" height="26" width="150">Swift Adicional(es)</td>
						<td nowrap height="26" align="center" width="86"></td>
						<td nowrap height="26" width="144" align="center"></td>
						<td nowrap height="26" align="center" width="190">Cancelación</td>
						<td nowrap height="26" align="center" width="68"></td>
						<td nowrap height="26" width="151" align="center"></td>
					</tr>
					<tr id="trclear">
					    <TD align="left">	<% if(msg.getE01LCCFL1().equals("Y"))  out.print("Convenio ALADI");
					    					else out.print("Timbres");%>
					    </TD>
						<td nowrap height="26" align="center" width="86"></td>
						<td nowrap height="26" width="144" align="center"></td>
						<td nowrap height="26" align="center" width="190">Comisión de Agente</td>
						<td nowrap height="26" align="center" width="68"></td>
						<td nowrap height="26" width="151" align="center"></td>
					</tr>
					<tr id="trdark">
						<td nowrap align="center" height="32" width="150">Intereses</td>
						<td nowrap align="center" height="32" width="86">
						<div align="center"><input type="text" name="E01FXRP20" size="15"
							maxlength="11"></div>						</td>
						<td nowrap width="144" align="center" height="32">
						<div align="center"><input type="text" name="E01FXRSA20" size="9"
							maxlength="11"></div>						</td>
						<td nowrap align="center" height="32" width="190">Pago</td>
						<td nowrap align="center" height="32" width="68"><input
							type="text" name="E01FXRP40" size="16" maxlength="11"></td>
						<td nowrap width="151" align="center" height="32">
						<div align="center"><input type="text" name="E01FXRSA40" size="6"
							maxlength="11"></div>						</td>
					</tr>
					<tr id="trclear">
						<td nowrap align="center" height="26" width="150">Reembolso</td>
						<td nowrap height="26" align="center" width="86"></td>
						<td nowrap height="26" width="144" align="center"></td>
						<td nowrap height="26" align="center" width="190">Gastos de Corresponsal</td>
						<td nowrap height="26" align="center" width="68"></td>
						<td nowrap height="26" width="151" align="center"></td>
					</tr>
					<tr id="trclear">
					  <td nowrap align="center" height="26">Aceptacion</td>
					  <td nowrap height="26" align="center">
					    <input type="text" name="E01ACPCOM" value="<%=msg.getE01ACPCOM()%>" size="15" maxlength="11">
				      </td>
					  <td nowrap height="26" align="center">
					  	<input type="text" name="E01ACCPBY" value="<%=msg.getE01ACCPBY()%>" size="9" maxlength="11"></td>
					  <td nowrap height="26" align="center">Comision Por Plazo </td>
					  <td nowrap height="26" align="center">
					  	<input type="text" name="E01TRMCOM" value="<%=msg.getE01TRMCOM()%>" size="16" maxlength="11"></td>
					  <td nowrap height="26" align="center"></td>
				  </tr>
				</tbody>
			</table>
			</div>
			</div>
			</td>
		</tr>
	</tbody>
</table></form>


<form method="post">
<div id="OtherOpt">
<h4 style="text-align: center">
	Aceptar con Advertencias</h4>
</div>




<p align="center"><input type="submit" name="Enviar" value="Enviar"></p>
</form>


</body>
</html>
