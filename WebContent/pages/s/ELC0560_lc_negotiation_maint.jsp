<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.0 Transitional//EN">
<html>
<head>
<title>Negociaciones de Cartas de Creditos</title>
<META HTTP-EQUIV="Content-Type" CONTENT="text/html; charset=ISO-8859-1">
<META name="GENERATOR" content="IBM WebSphere Page Designer V4.0 for Windows">
<META http-equiv="Content-Style-Type" content="text/css">
<link Href="<%=request.getContextPath()%>/pages/style.css" rel="stylesheet">
<%@ page import = "datapro.eibs.master.Util" %>

<jsp:useBean id= "msg01" class= "datapro.eibs.beans.ELC056001Message"  scope="session" />
<jsp:useBean id= "error" class= "datapro.eibs.beans.ELEERRMessage"  scope="session" />
<jsp:useBean id= "userPO" class= "datapro.eibs.beans.UserPos"  scope="session" />

<script language="Javascript1.1" src="<%=request.getContextPath()%>/pages/s/javascripts/eIBS.jsp"> </SCRIPT>
<script language="Javascript1.1" src="<%=request.getContextPath()%>/pages/s/javascripts/optMenu.jsp"> </SCRIPT>


<script language="Javascript">

function enviar1(){

	document.forms[0].btenviar.disabled = true;
	document.forms[0].submit();

}

</script>
<SCRIPT Language="Javascript">
  builtHPopUp();
  
  function showPopUp(opth,field,bank,ccy,field1,field2,opcod) {
   init(opth,field,bank,ccy,field1,field2,opcod);
   showPopupHelp();
  }

</SCRIPT>

<SCRIPT Language="Javascript">
  
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

</SCRIPT>

</head>

<body>

<h3 align="center">Pago/Negociación de Cartas de Crédito<img src="<%=request.getContextPath()%>/images/e_ibs.gif" align="left" name="EIBS_GIF" ALT="lc_negotiation_maint.jsp,ELC0560"></h3>
<hr size="4">

<form method="post" action="<%=request.getContextPath()%>/servlet/datapro.eibs.products.JSELC0560">
 
    <input type=HIDDEN name="SCREEN" value="2">
    <input type=HIDDEN name="TRANSFTYPE" value="YL">
    <input type=HIDDEN name="E01REQCON" value="2">
    
    <div id="DivHead">
    <table class="tableinfo">
    <tr bgcolor="#ffffff"> 
      <td nowrap height="79" width="100%">
		<table cellspacing="0" cellpadding="2" width="100%" border="0">
			<TR id="trdark">
				<TD align="left" valign="top" height="24" width="128">Cliente:</TD>
				<td nowrap valign="top" height="24" width="284"><div align="left">
					<INPUT type="text" name="E01LCMCUN" size="20" maxlength="9" value="<%= ng.getE01LCMCUN()%>"></div>
				</td>
				<td nowrap align="left" valign="top" height="24" width="56">Nombre:</td>
				<TD nowrap align="left" valign="top" height="24" width="231">
					<INPUT type="text" name="E01CUSNA1" size="46" maxlength="45" value="<%= ng.getE01CUSNA1()%>">
				</TD>
			</TR>
			<TR id="trclear">
				<TD align="left" valign="top" height="24" width="128">Carta de Crédito:</TD>
				<TD nowrap valign="top" height="24" width="284">
					<INPUT type="text" name="E01LCRNUM" size="23" maxlength="12" value="<%= ng.getE01LCRNUM()%>">
				</TD>
				<TD nowrap align="left" valign="top" height="24" width="56">Moneda:</TD>
				<TD nowrap align="left" valign="top" height="24" width="291">
					<INPUT type="text" name="E01LCMCCY" size="8" maxlength="3" value="<%= ng.getE01LCMCCY()%>">
					&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; Producto: 
					<INPUT type="text" name="E01LCMPRO" size="8" maxlength="8" value="<%= ng.getE01LCMPRO()%>">
				</TD>
			</TR>
			<TR id="trdark">
				<TD align="left" valign="top" height="29" width="128">Tipo de Negociación:</TD>
				<TD nowrap valign="top" height="29" width="284">
					<INPUT type="text" name="E01UPCODE" size="4" maxlength="1" value="<%= ng.getE01UPCODE()%>"> 
					<INPUT type="text" name="E01UPCDSC" size="25" maxlength="25" value="<%= ng.getE01UPCDSC()%>">
				</TD>
				<TD nowrap align="left" valign="top" height="29" width="56">Monto:</TD>
				<TD nowrap align="left" valign="top" height="29" width="291">
					<INPUT type="text" name="E01DRWAMN" size="26" maxlength="15" value="<%= ng.getE01DRWAMN()%>">
				</TD>
			</TR>						
		</table>
		</td>
    </tr>
   </table>        
 </div>
  <div id="OtherOpt"><BR>
<b>Cuenta Débito Principal</b><TABLE class="tableinfo" height="52">
	<TBODY>
		<TR bgcolor="#ffffff">
			<TD nowrap width="100%" height="52">
			<TABLE cellspacing="0" cellpadding="2" width="100%" border="0">
				<TBODY>
					<TR id="trdark">
						<TD align="center" valign="top" height="24" width="230">Concepto</TD>
						<TD nowrap valign="top" height="24" align="center" width="51">Banco</TD>
						<TD nowrap valign="top" height="24" align="center" width="61">Agencia</TD>
						<TD nowrap align="center" valign="top" height="24" width="47">MDA</TD>
						<TD nowrap align="center" valign="top" height="24" width="128">Cuenta Contable</TD>
						<TD nowrap align="center" valign="top" height="24" width="132">Cuenta</TD>
						<TD nowrap align="center" valign="top" height="24" width="114">Centro de Costo</TD>
					</TR>
					<TR id="trclear">
						<TD align="left" valign="top" height="24" width="230"><INPUT
							type="text" name="E01CONCDR" size="4" maxlength="2"> <INPUT
							type="text" name="CTADESC1" size="25" maxlength="25"
							value="CTADESC1"></TD>
						<TD nowrap valign="top" height="24" align="center" width="51"><INPUT
							type="text" name="E01CUSBNK" size="4" maxlength="2"></TD>
						<TD nowrap valign="top" height="24" align="center" width="61"><INPUT
							type="text" name="E01CUSBRN" size="6" maxlength="3"></TD>
						<TD nowrap align="center" valign="top" height="24" width="47"><INPUT
							type="text" name="E01CUSCCY" size="6" maxlength="3"></TD>
						<TD nowrap align="center" valign="top" height="24" width="128"><INPUT
							type="text" name="E01CUSGLN" size="20" maxlength="16"></TD>
						<TD nowrap align="center" valign="top" height="24" width="132"><INPUT
							type="text" name="E01CUSACC" size="18" maxlength="12"></TD>
						<TD nowrap align="center" valign="top" height="24" width="114"><INPUT
							type="text" name="E01CUSCCN" size="13" maxlength="8"></TD>
					</TR>
				</TBODY>
			</TABLE>
			</TD>
		</TR>
	</TBODY>
</TABLE>
<BR>
<B>Cuenta Crédito</B><TABLE class="tableinfo">

	<TBODY>
		<TR bgcolor="#ffffff">
			<TD nowrap width="100%" height="52">
			<TABLE cellspacing="0" cellpadding="2" width="100%" border="0">
				<TBODY>
					<TR id="trdark">
						<TD align="center" valign="top" height="24" width="230">Concepto</TD>
						<TD nowrap valign="top" height="24" align="center" width="51">Banco</TD>
						<TD nowrap valign="top" height="24" align="center" width="61">Agencia</TD>
						<TD nowrap align="center" valign="top" height="24" width="47">MDA</TD>
						<TD nowrap align="center" valign="top" height="24" width="128">Cuenta Contable</TD>
						<TD nowrap align="center" valign="top" height="24" width="132">Cuenta</TD>
						<TD nowrap align="center" valign="top" height="24" width="114">Centro de Costo</TD>
					</TR>
					<TR id="trclear">
						<TD align="left" valign="top" height="24" width="230"><INPUT
							type="text" name="E01GNCCR" size="4" maxlength="2"> <INPUT
							type="text" name="CTADESC2" size="25" maxlength="25"
							value="CTADESC2"></TD>
						<TD nowrap valign="top" height="24" align="center" width="51"><INPUT
							type="text" name="E01PMTBNK" size="4" maxlength="2"></TD>
						<TD nowrap valign="top" height="24" align="center" width="61"><INPUT
							type="text" name="E01PMTBRN" size="6" maxlength="3"></TD>
						<TD nowrap align="center" valign="top" height="24" width="47"><INPUT
							type="text" name="E01PMTCCY" size="6" maxlength="3"></TD>
						<TD nowrap align="center" valign="top" height="24" width="128"><INPUT
							type="text" name="E01PMTGLN" size="20" maxlength="16"></TD>
						<TD nowrap align="center" valign="top" height="24" width="132"><INPUT
							type="text" name="E01PMTACC" size="18" maxlength="12"></TD>
						<TD nowrap align="center" valign="top" height="24" width="114"><INPUT
							type="text" name="E01PMTCCN" size="13" maxlength="8"></TD>
					</TR>
				</TBODY>
			</TABLE></TD>
		</TR>
	</TBODY>
</TABLE>
<BR>
</div>
<TABLE class="tableinfo" height="47">
	<TBODY>
		<TR bgcolor="#ffffff">
			<TD nowrap height="79" width="100%">
			<TABLE cellspacing="0" cellpadding="2" width="100%" border="0">
				<TBODY>
					<TR id="trdark">
						<TD align="left" valign="top" height="24" width="233">Forma / Vía de Pago:</TD>
						<TD nowrap valign="top" align="left" width="115"><SELECT
							name="E01PMTVIA">
							<OPTION value="1" <%if (ng.getE01PMTVIA().equals("1")) { out.print("selected"); }%>>Cheque Oficial</OPTION>
							<OPTION value="2" <%if (ng.getE01PMTVIA().equals("2")) { out.print("selected"); }%>>Depósito Cta. Cte.</OPTION>
							<OPTION value="3" <%if (ng.getE01PMTVIA().equals("3")) { out.print("selected"); }%>>Cuenta Contable</OPTION>
							<OPTION value="5" <%if (ng.getE01PMTVIA().equals("5")) { out.print("selected"); }%>>Swift</OPTION>
						</SELECT></TD>
						<TD nowrap valign="top" height="24" align="left" width="200"></TD>
						<TD nowrap align="left" valign="top" height="24" width="170">Garantía en Efectivo:</TD>
						<TD nowrap align="left" valign="top" height="24" width="160"><INPUT
							type="text" name="E01CSHAMN" size="18" maxlength="15"></TD>
					</TR>
					<TR id="trclear">
						<TD align="left" valign="top" height="24" width="233">Debitar Comisiones a Cta Beneficiario:</TD>
						<TD nowrap valign="top" align="left" width="115">
						<INPUT type="radio" name="E01DEBFLG" value="Y" <%if (ng.getE01DEBFLG().equals("Y")) out.print("checked"); %>> Si 
                		<INPUT type="radio" name="E01DEBFLG" value="N" <%if (ng.getE01DEBFLG().equals("N")) out.print("checked"); %>> No
				</TD>
						<TD nowrap valign="top" height="24" align="left" width="200"></TD>
						<TD nowrap align="left" valign="top" height="24" width="170">Cta. Cte. Beneficiario:</TD>
						<TD nowrap align="left" valign="top" height="24" width="160"><INPUT
							type="text" name="E01LCMBAC" size="16" maxlength="12"></TD>
					</TR>
					<TR id="trdark">
						<TD align="left" valign="top" height="24" width="233">Cuenta débito Comisiones e Impuestos:</TD>
						<TD nowrap valign="top" align="left" width="115"><INPUT
							type="text" name="E01DEBACC" size="19" maxlength="12"></TD>
						<TD nowrap valign="top" height="24" align="left" width="200"></TD>
						<TD nowrap align="left" valign="top" height="24" width="170">Cancelar Saldo Remanente:</TD>
						<TD nowrap align="left" valign="top" height="24" width="160">
						<INPUT type="radio" name="E01CANBAL" value="Y" <%if (ng.getE01CANBAL().equals("Y")) out.print("checked"); %>> Si 
                		<INPUT type="radio" name="E01CANBAL" value="N" <%if (ng.getE01CANBAL().equals("N")) out.print("checked"); %>> No
				</TD>
					</TR>
				</TBODY>
			</TABLE>
			</TD>
		</TR>
	</TBODY>
</TABLE>
<BR>
<B>Tipos de Cambio</B><TABLE class="tableinfo">
	<TBODY>
		<TR align="left">
			<TD align="center" height="83" width="100%">
			<DIV align="center">
			<TABLE id="headTable1" cellspacing="0" cellpadding="2" width="100%"	border="0">
				<TBODY>
					<TR id="trdark">
						<TD nowrap align="center" width="77">Moneda</TD>
						<TD nowrap align="center" width="143">T/C Compra</TD>
						<TD nowrap align="center" width="142">T/C Venta</TD>
						<TD nowrap align="center" width="120">Moneda</TD>
						<TD nowrap align="center" width="126">T/C Compra</TD>
						<TD nowrap align="center" width="150">T/C Venta</TD>
					</TR>
				</TBODY>
			</TABLE>

			<DIV id="dataDiv1">
			<TABLE id="dataTable1" cellspacing="0" cellpadding="2" width="100%"
				border="0">
				<TBODY>
					<TR id="trclear">
						<TD nowrap align="center" height="26" width="80"><INPUT type="text"
							name="E01FXCCY1" size="9" maxlength="3"></TD>
						<TD nowrap height="26" width="142" align="center">
						<DIV align="center"><INPUT type="text" name="E01FXRP1" size="20"
							maxlength="11"></DIV>
						</TD>
						<TD nowrap height="26" width="144" align="center">
						<DIV align="center"><INPUT type="text" name="E01FXRSA1" size="20"
							maxlength="11"></DIV>
						</TD>
						<TD nowrap height="26" width="123" align="center">

						<DIV align="center"><INPUT type="text" name="E01FXCCY3" size="9"
							maxlength="3"></DIV>
						</TD>
						<TD nowrap height="26" width="100" align="center"><INPUT type="text"
							name="E01FXRP3" size="20" maxlength="11"></TD>
						<TD nowrap height="26" width="151" align="center">
						<DIV align="center"><INPUT type="text" name="E01FXRSA3" size="20"
							maxlength="11"></DIV>
						</TD>
					</TR>
					<TR id="trdark">
						<TD nowrap align="center" width="80" height="32">
						<DIV align="center"><INPUT type="text" name="E01FXCCY2" size="9"
							maxlength="3"></DIV>
						</TD>
						<TD nowrap width="142" align="center" height="32">
						<DIV align="center"><INPUT type="text" name="E01FXRP2" size="20"
							maxlength="11"></DIV>
						</TD>
						<TD nowrap width="144" align="center" height="32">
						<DIV align="center"><INPUT type="text" name="E01FXRSA2" size="20"
							maxlength="11"></DIV>
						</TD>
						<TD nowrap width="123" align="center" height="32">
						<DIV align="center"><INPUT type="text" name="E01FXCCY4" size="9"
							maxlength="3"></DIV>
						</TD>
						<TD nowrap width="100" align="center" height="32"><INPUT type="text"
							name="E01FXRP4" size="20" maxlength="11"></TD>
						<TD nowrap width="151" align="center" height="32">
						<DIV align="center"><INPUT type="text" name="E01FXRSA4" size="20"
							maxlength="11"></DIV>
						</TD>
					</TR>
				</TBODY>
			</TABLE>
			</DIV>
			</DIV>
			</TD>
		</TR>
	</TBODY>
</TABLE>
<BR>
<B>Comisiones y Gastos</B><TABLE class="tableinfo">

	<TBODY>
		<TR align="left">
			<TD align="center" height="83" width="100%">
			<DIV align="center">
			<TABLE id="headTable10" cellspacing="0" cellpadding="2" width="100%"
				border="0">
				<TBODY>
					<TR id="trdark">
						<TD nowrap align="center" width="150">Comisión</TD>
						<TD nowrap align="center" width="99">Monto</TD>
						<TD nowrap align="center" width="143">Por</TD>
						<TD nowrap align="center" width="192">Comisión</TD>
						<TD nowrap align="center" width="104">Monto</TD>
						<TD nowrap align="center" width="152">Por</TD>
					</TR>
				</TBODY>
			</TABLE>

			<DIV id="dataDiv10">
			<TABLE id="dataTable10" cellspacing="0" cellpadding="2" width="100%"
				border="0">
				<TBODY>
					<TR id="trclear">
						<TD nowrap align="center" height="26" width="150">Emisión</TD>
						<TD nowrap height="26" align="center" width="86">
						<DIV align="center"><INPUT type="text" name="E01FXRP10" size="16"
							maxlength="11"></DIV>
						</TD>
						<TD nowrap height="26" width="144" align="center">
						<DIV align="center"><INPUT type="text" name="E01FXRSA10" size="6"
							maxlength="11"></DIV>
						</TD>
						<TD nowrap height="26" align="center" width="190">Aviso</TD>
						<TD nowrap height="26" align="center" width="68"><INPUT
							type="text" name="E01FXRP30" size="16" maxlength="11"></TD>
						<TD nowrap height="26" width="151" align="center">
						<DIV align="center"><INPUT type="text" name="E01FXRSA30" size="5"
							maxlength="11"></DIV>
						</TD>
					</TR>
					<TR id="trdark">
						<TD nowrap align="center" height="26" width="150">Confirmación</TD>
						<TD nowrap height="26" align="center" width="86"></TD>
						<TD nowrap height="26" width="144" align="center"></TD>
						<TD nowrap height="26" align="center" width="190">Enmienda</TD>
						<TD nowrap height="26" align="center" width="68"></TD>
						<TD nowrap height="26" width="151" align="center"></TD>
					</TR>
					<TR id="trclear">
						<TD nowrap align="center" height="26" width="150">Aviso de Enmienda</TD>
						<TD nowrap height="26" align="center" width="86"></TD>
						<TD nowrap height="26" width="144" align="center"></TD>
						<TD nowrap height="26" align="center" width="190">Discrepancia</TD>
						<TD nowrap height="26" align="center" width="68"></TD>
						<TD nowrap height="26" width="151" align="center"></TD>
					</TR>
					<TR id="trdark">
						<TD nowrap align="center" height="26" width="150">Extensión de Validez</TD>
						<TD nowrap height="26" align="center" width="86"></TD>
						<TD nowrap height="26" width="144" align="center"></TD>
						<TD nowrap height="26" align="center" width="190">Portes</TD>
						<TD nowrap height="26" align="center" width="68"></TD>
						<TD nowrap height="26" width="151" align="center"></TD>
					</TR>
					<TR id="trclear">
						<TD nowrap align="center" height="26" width="150">Courier</TD>
						<TD nowrap height="26" align="center" width="86"></TD>
						<TD nowrap height="26" width="144" align="center"></TD>
						<TD nowrap height="26" align="center" width="190">Swift de Apertura</TD>
						<TD nowrap height="26" align="center" width="68"></TD>
						<TD nowrap height="26" width="151" align="center"></TD>
					</TR>
					<TR id="trdark">
						<TD nowrap align="center" height="26" width="150">Swift Adicional(es)</TD>
						<TD nowrap height="26" align="center" width="86"></TD>
						<TD nowrap height="26" width="144" align="center"></TD>
						<TD nowrap height="26" align="center" width="190">Cancelación</TD>
						<TD nowrap height="26" align="center" width="68"></TD>
						<TD nowrap height="26" width="151" align="center"></TD>
					</TR>
					<TR id="trclear">
						<TD nowrap align="center" height="26" width="150">Convenio ALADI</TD>
						<TD nowrap height="26" align="center" width="86"></TD>
						<TD nowrap height="26" width="144" align="center"></TD>
						<TD nowrap height="26" align="center" width="190">Comisión de Agente</TD>
						<TD nowrap height="26" align="center" width="68"></TD>
						<TD nowrap height="26" width="151" align="center"></TD>
					</TR>
					<TR id="trdark">
						<TD nowrap align="center" height="32" width="150">Intereses</TD>
						<TD nowrap align="center" height="32" width="86">
						<DIV align="center"><INPUT type="text" name="E01FXRP20" size="15"
							maxlength="11"></DIV>
						</TD>
						<TD nowrap width="144" align="center" height="32">
						<DIV align="center"><INPUT type="text" name="E01FXRSA20" size="9"
							maxlength="11"></DIV>
						</TD>
						<TD nowrap align="center" height="32" width="190">Pago</TD>
						<TD nowrap align="center" height="32" width="68"><INPUT
							type="text" name="E01FXRP40" size="16" maxlength="11"></TD>
						<TD nowrap width="151" align="center" height="32">
						<DIV align="center"><INPUT type="text" name="E01FXRSA40" size="6"
							maxlength="11"></DIV>
						</TD>
					</TR>
					<TR id="trclear">
						<TD nowrap align="center" height="26" width="150">Reembolso</TD>
						<TD nowrap height="26" align="center" width="86"></TD>
						<TD nowrap height="26" width="144" align="center"></TD>
						<TD nowrap height="26" align="center" width="190">Gastos de Corresponsal</TD>
						<TD nowrap height="26" align="center" width="68"></TD>
						<TD nowrap height="26" width="151" align="center"></TD>
					</TR>
				</TBODY>
			</TABLE>
			</DIV>
			</DIV>
			</TD>
		</TR>
	</TBODY>
</TABLE></form>


<FORM method="post">
<DIV id="OtherOpt">
<H4 style="text-align: center">
	Aceptar con Advertencias</H4>
</DIV>




<P align="center"><INPUT type="submit" name="Enviar" value="Enviar"></P>
</FORM>


</body>
</html>
