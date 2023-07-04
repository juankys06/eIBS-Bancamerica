<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.0 Transitional//EN">
<html>
<head>
<title>Letter of Credit Details</title>
<META HTTP-EQUIV="Content-Type" CONTENT="text/html; charset=ISO-8859-1">
<META name="GENERATOR"
	content="IBM WebSphere Page Designer V3.5.2 for Windows">
<META http-equiv="Content-Style-Type" content="text/css">
<link Href="<%=request.getContextPath()%>/pages/style.css"
	rel="stylesheet">

<script language="Javascript1.1"
	src="<%=request.getContextPath()%>/pages/s/javascripts/eIBS.jsp"> </SCRIPT>
<script language="Javascript1.1"
	src="<%=request.getContextPath()%>/pages/s/javascripts/optMenu.jsp"> </SCRIPT>

<jsp:useBean id="lcNew2" class="datapro.eibs.beans.ELC050002Message"
	scope="session" />
<jsp:useBean id="error" class="datapro.eibs.beans.ELEERRMessage"
	scope="session" />
<jsp:useBean id="userPO" class="datapro.eibs.beans.UserPos"
	scope="session" />

<SCRIPT LANGUAGE="javascript">

function goMsgSwift() {
    
     
     if (document.forms[0].E01LCMACC.value !== "") {
         
		   var dx = 450;
		   var dy = 350;
		   var y0 = (screen.height - dy) / 2;
		   var x0 = (screen.width - dx) / 2;
		   var attr = 'toolbar=no,location=no,directories=no,menubar=no,scrollbars=yes,resizable=yes,copyhistory=no,left=' + x0 + ',top=' + y0 + ',height=' + dy + ',width=' + dx;

		   page = webapp + "/servlet/datapro.eibs.products.JSELC0525?SCREEN=0&E01LCMACC="+document.forms[0].E01LCMACC.value;
		   listin = window.open(page,'',attr);
         
     } else {
		  alert("Seleccione una cuenta ");	   
     }

 }

function goDetail(scr){
	document.forms[0].SCREEN.value = scr;
	document.forms[0].submit();
}

<%if (!userPO.getPurpose().equals("NEW")) {%>
	builtNewMenu(lc_approbal_detail);
<%}%>

   builtHPopUp();

  function showPopUp(opth,field,bank,ccy,field1,field2,opcod) {
   init(opth,field,bank,ccy,field1,field2,opcod);
   showPopupHelp();
   }
   
</SCRIPT>

<%if (!error.getERRNUM().equals("0")) {
	error.setERRNUM("0");
	out.println("<SCRIPT Language=\"Javascript\">");
	out.println("       showErrors()");
	out.println("</SCRIPT>");
}
out.println("<SCRIPT> initMenu();  </SCRIPT>");
%>

</head>
<body>
<h3 align="center">Apertura de Carta de Credito<img
	src="<%=request.getContextPath()%>/images/e_ibs.gif" align="left"
	name="EIBS_GIF" ALT="letter_of_credit_details.jsp,ELC0525"></h3>

<hr size="4">
<form method="post" action="<%=request.getContextPath()%>/servlet/datapro.eibs.products.JSELC0525">
<INPUT TYPE=HIDDEN NAME="SCREEN" VALUE="6">
  <INPUT TYPE=HIDDEN NAME="E01LCMBNK" value=<%= lcNew2.getE02LCMBNK().trim()%>>
<TABLE class="tableinfo">
	<TBODY>
		<TR>
			<TD nowrap>
			<TABLE cellspacing="0" cellpadding="2" width="100%" border="0">
				<TBODY>
					<TR id="trdark0">
						<TD nowrap width="16%">
						<DIV align="right"><B>Banco :</B></DIV>
						</TD>
						<TD nowrap width="20%">
						<DIV align="left">
							<INPUT type="text" name="E02LCMBNK" readonly size="4" maxlength="2" value="<%=lcNew2.getE02LCMBNK().trim()%>"></DIV>
						</TD>
						<TD nowrap width="16%">
						<DIV align="right"><B>Número de Carta de Credito :</B></DIV>
						</TD>
						<TD nowrap width="16%">
							<DIV align="left">
								<B> 
									<%if (lcNew2.getE02LCMACC().trim().equals("999999999999")) {%>
										<INPUT type="text" size="12" maxlength="12" value="Nuevo" readonly>
										<INPUT type="hidden" name="E02LCMACC" value="<%=lcNew2.getE02LCMACC().trim()%>" readonly> 
										<INPUT type="hidden" name="E01LCMACC" value="<%=lcNew2.getE02LCMACC().trim()%>" readonly> 
									<%} else {%>
										<INPUT type="text" name="E02LCMACC" size="12" maxlength="12" value="<%=lcNew2.getE02LCMACC().trim()%>" readonly> 
										<INPUT type="hidden" name="E01LCMACC" value="<%=lcNew2.getE02LCMACC().trim()%>" readonly> 										
									<%}%> 
								</B>
							</DIV>
						</TD>
					</TR>
					<TR id="trclear0">
						<TD nowrap width="16%">
							<DIV align="right"><B>Nuestra Referencia :</B></DIV>
						</TD>
						<TD nowrap width="20%">
							<DIV align="left">
								<INPUT type="text" name="E02LCMORF" size="20" maxlength="16" value="<%=lcNew2.getE02LCMORF().trim()%>" readonly>
							</DIV>
						</TD>
						<TD nowrap width="16%">
							<DIV align="right"><B>Producto :</B></DIV>
						</TD>
						<TD nowrap width="16%">
							<DIV align="left">
								<B> 
									<INPUT type="text" name="E02LCMPRO" size="4" maxlength="4" value="<%=lcNew2.getE02LCMPRO().trim()%>" readonly>
								</B>
							</DIV>
						</TD>
					</TR>
					<TR id="trdark0">
						<TD nowrap width="16%">
							<DIV align="right"><B>Referencia del Otro Banco :</B></DIV>
						</TD>
						<TD nowrap width="16%">
							<DIV align="left">
								<B> 
									<INPUT type="text" name="E02LCMTRF" size="20" maxlength="16" value="<%=lcNew2.getE02LCMTRF().trim()%>" readonly> 
								</B>
							</DIV>
						</TD>

						<TD nowrap width="16%">
							<DIV align="right"><B>Descripcion de Producto :</B></DIV>
						</TD>
						<TD nowrap width="16%">
							<DIV align="left">
								<B> 
									<INPUT type="text" name="E02DSCPRO" size="30" maxlength="30" value="<%=lcNew2.getE02DSCPRO().trim()%>" readonly> 
								</B>
							</DIV>
						</TD>

					</TR>
				</TBODY>
			</TABLE>
			</TD>
		</TR>
	</TBODY>
</TABLE>

<h4>Información de la Carta de Crédito</h4>
<table class="tableinfo">
	<tr>
		<td nowrap>
		<TABLE cellpadding="2" cellspacing="0" width="100%" border="0">
			<TBODY>
				<TR id="trdark">
					<TD nowrap width="25%">
						<DIV align="right">Fecha de Emisión:</DIV>
					</TD>
					<TD nowrap width="25%">
						<INPUT type="text" name="E02LCMID1" size="2" maxlength="2" readonly="readonly" value="<%=(lcNew2.getE02LCMID1().trim().equals("0") ? "" : lcNew2.getE02LCMID1().trim())%>"> 
						<INPUT type="text" name="E02LCMID2"	size="2" maxlength="2" readonly="readonly" value="<%=(lcNew2.getE02LCMID2().trim().equals("0") ? "" : lcNew2.getE02LCMID2().trim())%>"> 
						<INPUT type="text" name="E02LCMID3" size="2" maxlength="2" readonly="readonly"
								value='<%if (lcNew2.getE02LCMID3().length() < 2 && !lcNew2.getE02LCMID3().equals("0"))
												out.print("0");
												out.print((lcNew2.getE02LCMID3().trim().equals("0")
												? ""
												: lcNew2.getE02LCMID3().trim()));%>'> 
						<A	href="javascript:DatePicker(document.forms[0].E02LCMID1,document.forms[0].E02LCMID2,document.forms[0].E02LCMID3)"></A>
						<img src="<%=request.getContextPath()%>/images/Check.gif" alt="campo obligatorio" border="0"></TD>
					<TD nowrap width="25%">
						<DIV align="right">Fecha de Expiración:</DIV>
					</TD>
					<TD nowrap width="25%">
						<INPUT type="text" name="E02LCMEX1" size="2" maxlength="2" readonly="readonly" value="<%=(lcNew2.getE02LCMEX1().trim().equals("0") ? "" : lcNew2.getE02LCMEX1().trim())%>"> 
						<INPUT type="text" name="E02LCMEX2" size="2" maxlength="2" readonly="readonly" value="<%=(lcNew2.getE02LCMEX2().trim().equals("0") ? "" : lcNew2.getE02LCMEX2().trim())%>">
						<INPUT type="text" name="E02LCMEX3" size="2" maxlength="2" readonly="readonly"
							value='<%						if (lcNew2.getE02LCMEX3().length() < 2
								&& !lcNew2.getE02LCMEX3().equals("0"))
								out.print("0");
							out.print(
								(lcNew2.getE02LCMEX3().trim().equals("0")
									? ""
									: lcNew2.getE02LCMEX3().trim()));%>'
							onkeypress="enterInteger()">
						<A href="javascript:DatePicker(document.forms[0].E02LCMEX1,document.forms[0].E02LCMEX2,document.forms[0].E02LCMEX3)"></A>
						<IMG src="<%=request.getContextPath()%>/images/Check.gif" alt="campo obligatorio" border="0"></TD>
				</TR>
				<TR id="trclear">
					<TD nowrap width="25%">
					<DIV align="right">Ultima Fecha de Embarque:</DIV>
					</TD>
					<TD nowrap width="25%">
						<INPUT type="text" name="E02LCMSD1" size="2" maxlength="2" readonly="readonly" value="<%=(lcNew2.getE02LCMSD1().trim().equals("0") ? "" : lcNew2.getE02LCMSD1().trim())%>"> 
						<INPUT type="text" name="E02LCMSD2"	size="2" maxlength="2" readonly="readonly" value="<%=(lcNew2.getE02LCMSD2().trim().equals("0") ? "" : lcNew2.getE02LCMSD2().trim())%>">
						<INPUT type="text" name="E02LCMSD3" size="2" maxlength="2"
							readonly="readonly"
							value='<%						if (lcNew2.getE02LCMSD3().length() < 2
								&& !lcNew2.getE02LCMSD3().equals("0"))
								out.print("0");
							out.print(
								(lcNew2.getE02LCMSD3().trim().equals("0")
									? ""
									: lcNew2.getE02LCMSD3().trim()));%>'> 
						<A href="javascript:DatePicker(document.forms[0].E02LCMSD1,document.forms[0].E02LCMSD2,document.forms[0].E02LCMSD3)"></A>
					</TD>
					<TD nowrap width="25%">
					<DIV align="right">Fecha de Aviso/Confirmación:</DIV>
					</TD>
					<TD nowrap width="25%">
						<INPUT type="text" name="E02LCMCN1" size="2" maxlength="2" readonly="readonly" value="<%=(lcNew2.getE02LCMCN1().trim().equals("0") ? "" : lcNew2.getE02LCMCN1().trim())%>" > 
						<INPUT type="text" name="E02LCMCN2" size="2" maxlength="2" readonly="readonly" value="<%=(lcNew2.getE02LCMCN2().trim().equals("0") ? "" : lcNew2.getE02LCMCN2().trim())%>" >
						<INPUT type="text" name="E02LCMCN3" size="2" maxlength="2"
							readonly="readonly"
							value='<%						if (lcNew2.getE02LCMCN3().length() < 2
								&& !lcNew2.getE02LCMCN3().equals("0"))
								out.print("0");
							out.print(
								(lcNew2.getE02LCMCN3().trim().equals("0")
									? ""
									: lcNew2.getE02LCMCN3().trim()));%>' > 
						<A href="javascript:DatePicker(document.forms[0].E02LCMCN1,document.forms[0].E02LCMCN2,document.forms[0].E02LCMCN3)"></A>
					</TD>
				</TR>
				<TR id="trdark">
					<TD nowrap width="25%">
					<DIV align="right">Moneda Extranjera:</DIV>
					</TD>
					<TD nowrap width="25%">
						<INPUT type="text" name="E02LCMFCY" size="4" maxlength="4" readonly="readonly" value="<%=lcNew2.getE02LCMFCY().trim()%>"> 
						<A href="javascript:GetCurrency('E02LCMFCY',document.forms[0].E02LCMBNK.value)"></A>
					</TD>
					<TD nowrap width="25%">
						<DIV align="right">Tipo de Cambio M/E:</DIV>
					</TD>
					<TD nowrap width="25%">
						<INPUT type="text" name="E02LCMOFX" readonly="readonly" size="12" maxlength="11" value="<%=lcNew2.getE02LCMOFX().trim()%>">
					</TD>
				</TR>
				<TR id="trclear">
					<TD nowrap width="25%">
					<DIV align="right">Centro de Costo:</DIV>
					</TD>
					<TD nowrap width="25%">
						<INPUT type="text" name="E02LCMCCN" size="9" readonly="readonly" maxlength="8" value="<%=lcNew2.getE02LCMCCN().trim()%>"> 
						<A href="javascript:GetCostCenter('E02LCMCCN',document.forms[0].E02LCMBNK.value)"></A>
					</TD>
					<TD nowrap width="25%">
						<DIV align="right">Monto del Crédito:</DIV>
					</TD>
					<TD nowrap width="25%">
						<INPUT type="text" name="E02LCMOAM"	size="17" maxlength="16" readonly="readonly" value="<%=lcNew2.getE02LCMOAM().trim()%>" > 
						<IMG src="<%=request.getContextPath()%>/images/Check.gif" alt="campo obligatorio" border="0"></TD>
				</TR>
				<TR id="trdark">
					<TD nowrap width="25%">
						<DIV align="right">Clausula de Aproximación:</DIV>
					</TD>
					<TD nowrap width="25%">
						<input type="radio" name="E02LCMABC" value="Y" disabled="disabled" <%if (lcNew2.getE02LCMABC().equals("Y")) out.print("checked");%>>Si
						<input type="radio" name="E02LCMABC" value="N" disabled="disabled" <%if (lcNew2.getE02LCMABC().equals("N")) out.print("checked");%>>No
						&nbsp; &nbsp; &nbsp; Porcentaje: 
						<INPUT type="text" name="E02LCMABP" size="3" maxlength="3" readonly="readonly" value="<%=lcNew2.getE02LCMABP().trim()%>" >
					</TD>
					<TD nowrap width="25%">
					<DIV align="right">Incoterms :</DIV>
					</TD>
					<TD nowrap width="25%">
						<INPUT type="text" name="E02LCMITR" size="4" maxlength="4" readonly="readonly" value="<%=lcNew2.getE02LCMITR().trim()%>"> 
						<A href="javascript:GetCodeCNOFC('E02LCMITR','11')"></A>
					</TD>
				</TR>
				<TR id="trclear">
					<TD nowrap width="25%">
					<DIV align="right">Tenor:</DIV>
					</TD>
					<TD nowrap width="25%">
						<SELECT name="E02LCMTNR" disabled="disabled">
						<OPTION value=" "></OPTION>
						<OPTION value="S"
							<%if (lcNew2.getE02LCMTNR().equals("S"))
	out.print("selected");%>>Pago</OPTION>
						<OPTION value="A"
							<%if (lcNew2.getE02LCMTNR().equals("T"))
	out.print("selected");%>>Aceptación</OPTION>
						<OPTION value="M"
							<%if (lcNew2.getE02LCMTNR().equals("M"))
	out.print("selected");%>>Pago
						Mixto</OPTION>
						<OPTION value="D"
							<%if (lcNew2.getE02LCMTNR().equals("D"))
	out.print("selected");%>>Pago
						Diferido</OPTION>
						<OPTION value="N"
							<%if (lcNew2.getE02LCMTNR().equals("N"))
	out.print("selected");%>>Negociación</OPTION>
					</SELECT> <IMG src="<%=request.getContextPath()%>/images/Check.gif"
						alt="campo obligatorio" border="0"></TD>
					<TD nowrap width="25%">
					<DIV align="right">Confirmada:</DIV>
					</TD>
					<TD nowrap width="25%"><input type="radio" name="E02LCMCNF" disabled="disabled" 
						value="Y"
						<%if (lcNew2.getE02LCMCNF().equals("Y"))
	out.print("checked");%>>Si
					<input type="radio" name="E02LCMCNF" value="N" disabled="disabled"
						<%if (lcNew2.getE02LCMCNF().equals("N"))
	out.print("checked");%>>No
					</TD>
				</TR>
				<TR id="trdark">
					<TD nowrap width="25%">
					<DIV align="right">Confirmada por Otro Banco:</DIV>
					</TD>
					<TD nowrap width="25%"><input type="radio" name="E02LCMCNO" disabled="disabled"
						value="Y"
						<%if (lcNew2.getE02LCMCNO().equals("Y"))
	out.print("checked");%>>Si
					<input type="radio" name="E02LCMCNO" value="N" disabled="disabled" 
						<%if (lcNew2.getE02LCMCNO().equals("N")) 
	out.print("checked");%>>No
					</TD>
					<TD nowrap width="25%">
					<DIV align="right">Transferible:</DIV>
					</TD>
					<TD nowrap width="25%"><input type="radio" name="E02LCMTRN" disabled="disabled"
						value="Y"
						<%if (lcNew2.getE02LCMTRN().equals("Y"))
	out.print("checked");%>>Si
					<input type="radio" name="E02LCMTRN" value="N" disabled="disabled" 
						<%if (lcNew2.getE02LCMTRN().equals("N"))
	out.print("checked");%>>No
					</TD>
				</TR>
				<TR id="trclear">
					<TD nowrap width="25%">
					<DIV align="right">Embarques Parciales:</DIV>
					</TD>
					<TD nowrap width="25%"><input type="radio" name="E02LCMPSH" disabled="disabled"
						value="Y"
						<%if (lcNew2.getE02LCMPSH().equals("Y"))
	out.print("checked");%>>Si
					<input type="radio" name="E02LCMPSH" value="N" disabled="disabled"
						<%if (lcNew2.getE02LCMPSH().equals("N"))
	out.print("checked");%>>No
					</TD>
					<TD nowrap width="25%">
					<DIV align="right">Transbordo:</DIV>
					</TD>
					<TD nowrap width="25%"><input type="radio" name="E02LCMTSH" disabled="disabled"
						value="Y"
						<%if (lcNew2.getE02LCMTSH().equals("Y"))
	out.print("checked");%>>Si
					<input type="radio" name="E02LCMTSH" value="N" disabled="disabled"
						<%if (lcNew2.getE02LCMTSH().equals("N"))
	out.print("checked");%>>No
					</TD>
				</TR>
				<TR id="trdark">
					<TD nowrap width="25%">
					<DIV align="right">Agente/Representante:</DIV>
					</TD>
					<TD nowrap width="25%"><INPUT type="text" name="E02LCMBRK" readonly="readonly" 
						size="17" maxlength="16" value="<%=lcNew2.getE02LCMBRK().trim()%>"
						onkeypress="enterDecimal()"></TD>
					<TD nowrap width="25%">
					<DIV align="right">% Garantía Efectivo:</DIV>
					</TD>
					<TD nowrap width="25%"><INPUT type="text" name="E02LCMCPE" readonly="readonly" 
						size="17" maxlength="16" value="<%=lcNew2.getE02LCMCPE().trim()%>"></TD>
				</TR>
				<TR id="trclear">
					<TD nowrap width="25%">
					<DIV align="right">Monto Garantía Efectivo:</DIV>
					</TD>
					<TD nowrap width="25%"><INPUT type="text" name="E02LCMCAM" readonly="readonly" 
						size="17" maxlength="16" value="<%=lcNew2.getE02LCMCAM().trim()%>"
						onkeypress="enterDecimal()"></TD>
					<TD nowrap width="25%">
					<DIV align="right">Cuenta Garantía Efectivo:</DIV>
					</TD>
					<TD nowrap width="25%">
					<TABLE>
						<TBODY>
							<TR>
								<TD rowspan="2"><INPUT type="text" name="E02LCMCCA" size="17" readonly="readonly" 
									maxlength="16" value="<%=lcNew2.getE02LCMCCA().trim()%>"></TD>
								<TD>Cuenta<br>
								Contable</TD>
								<TD rowspan="2"><A
									href="javascript:GetLedger('E02LCMCCA',document.forms[0].E02LCMBNK.value,'','')"></A></TD>
								<TD>Cuenta<br>
								Cliente</TD>
								<TD rowspan="2"><A
									href="javascript: GetAccount('E02LCMCCA','','','')"></A></TD>
						</TBODY>
					</TABLE>
					</TD>

				</TR>
				<TR id="trdark">
					<TD nowrap width="25%">
					<DIV align="right">Tarifa de Cargos:</DIV>
					</TD>
					<TD nowrap width="25%"><INPUT type="text" name="E02LCMTAR" size="2" readonly="readonly" 
						maxlength="2" value="<%=lcNew2.getE02LCMTAR().trim()%>"> <A
						href="javascript:GetTariffLC('E02LCMTAR','')"></A> <IMG
						src="<%=request.getContextPath()%>/images/Check.gif"
						alt="campo obligatorio" border="0"></TD>
					<TD nowrap width="25%">
					<DIV align="right">Linea de Crédito:</DIV>
					</TD>
					<TD nowrap width="25%"><INPUT type="text" name="E02LCMLNR" readonly="readonly" 
						size="10" maxlength="9" value="<%=lcNew2.getE02LCMLNR().trim()%>"
						> <A
						href="javascript:GetCustomer('E02LCMLNR')"></A> <INPUT type="text" readonly="readonly" 
						name="E02LCMCMN" size="4" maxlength="4"
						value="<%=lcNew2.getE02LCMCMN().trim()%>"
						></TD>
				</TR>
				<TR id="trclear">
					<TD nowrap width="25%">
					<DIV align="right">Cargos por Cuenta de:</DIV>
					</TD>
					<TD nowrap width="25%"><SELECT name="E02LCMAOB" disabled="disabled">
						<OPTION value=" "></OPTION>
						<OPTION value="A"
							<%if (lcNew2.getE02LCMAOB().equals("A"))
	out.print("selected");%>>Aplicante</OPTION>
						<OPTION value="B"
							<%if (lcNew2.getE02LCMAOB().equals("B"))
	out.print("selected");%>>Beneficiario</OPTION>
					</SELECT> <IMG src="<%=request.getContextPath()%>/images/Check.gif"
						alt="campo obligatorio" border="0"></TD>
					<TD nowrap width="25%">
					<DIV align="right">Número Referencia ALADI:</DIV>
					</TD>
					<TD nowrap width="25%"><INPUT type="text" name="E02LCMSRF" readonly="readonly" 
						size="17" maxlength="16" value="<%=lcNew2.getE02LCMSRF().trim()%>"></TD>
				</TR>
				<TR id="trdark">
					<TD nowrap width="25%">
					<DIV align="right">Com.Apertura Día Emisión:</DIV>
					</TD>
					<TD nowrap width="23%"><input type="radio" name="E02LCMOCI" disabled="disabled"
						value="Y"
						<%if (lcNew2.getE02LCMOCI().equals("Y"))
	out.print("checked");%>>Si
					<input type="radio" name="E02LCMOCI" value="N" disabled="disabled" 
						<%if (lcNew2.getE02LCMOCI().equals("N"))
	out.print("checked");%>>No
					</TD>
					<TD nowrap width="25%">
					<DIV align="right">Com.Enmienda Día Enmienda:</DIV>
					</TD>
					<TD nowrap width="27%"><input type="radio" name="E02LCMACI" disabled="disabled" 
						value="Y"
						<%if (lcNew2.getE02LCMACI().equals("Y"))
	out.print("checked");%>>Si
					<input type="radio" name="E02LCMACI" value="N" disabled="disabled" 
						<%if (lcNew2.getE02LCMACI().equals("N"))
	out.print("checked");%>>No
					</TD>
				</TR>
				<TR id="trclear">
					<TD nowrap width="25%">
					<DIV align="right">Lugar de Expiración:</DIV>
					</TD>
					<TD nowrap width="25%"><INPUT type="text" name="E02LCMPLE" readonly="readonly" 
						size="29" maxlength="29" value="<%=lcNew2.getE02LCMPLE().trim()%>">
					<IMG src="<%=request.getContextPath()%>/images/Check.gif"
						alt="campo obligatorio" border="0"></TD>

					<TD nowrap width="25%">
					<DIV align="right">Reglas a Aplicar:</DIV>
					</TD>
					<TD nowrap width="27%"><SELECT name="E02LCMAPR" disabled="disabled">
						<OPTION value=""></OPTION>
						<OPTION value="1"
							<%if (lcNew2.getE02LCMAPR().equals("1"))
	out.print("selected");%>>UCP
						Latest Version</OPTION>
						<OPTION value="2"
							<%if (lcNew2.getE02LCMAPR().equals("2"))
	out.print("selected");%>>EUCP
						Latest Version</OPTION>
						<OPTION value="3"
							<%if (lcNew2.getE02LCMAPR().equals("3"))
	out.print("selected");%>>UCPURR
						Latest Version</OPTION>
						<OPTION value="4"
							<%if (lcNew2.getE02LCMAPR().equals("4"))
	out.print("selected");%>>EUCPURR
						Latest Version</OPTION>
						<OPTION value="5"
							<%if (lcNew2.getE02LCMAPR().equals("5"))
	out.print("selected");%>>ISP
						Latest Version</OPTION>
						<OPTION value="6"
							<%if (lcNew2.getE02LCMAPR().equals("6"))
	out.print("selected");%>>Other</OPTION>
					</SELECT> <IMG src="<%=request.getContextPath()%>/images/Check.gif"
						alt="campo obligatorio" border="0"></TD>

				</TR>
				<TR id="trdark">
					<TD nowrap width="25%">
					<DIV align="right">Lugar de&nbsp;Recepción:</DIV>
					</TD>
					<TD nowrap width="23%" colspan="3"><INPUT type="text" readonly="readonly" 
						name="E02LCMPLR" size="65" maxlength="65"
						value="<%=lcNew2.getE02LCMPLR().trim()%>"></TD>
				</TR>
				<TR id="trclear">
					<TD nowrap width="25%">
					<DIV align="right">Puerto/Aeropuerto de Salida:</DIV>
					</TD>
					<TD nowrap width="23%" colspan="3"><INPUT type="text" readonly="readonly" 
						name="E02LCMPOL" size="65" maxlength="65"
						value="<%=lcNew2.getE02LCMPOL().trim()%>"></TD>

				</TR>
				<TR id="trdark">
					<TD nowrap width="25%">
					<DIV align="right">Puerto/Aeropuerto de Destino:</DIV>
					</TD>
					<TD nowrap width="23%" colspan="3"><INPUT type="text" readonly="readonly" 
						name="E02LCMPOD" size="65" maxlength="65"
						value="<%=lcNew2.getE02LCMPOD().trim()%>"></TD>
				</TR>
				<TR id="trclear">
					<TD nowrap width="25%">
					<DIV align="right">Lugar de Entrega</DIV>
					</TD>
					<TD nowrap width="23%" colspan="3"><INPUT type="text" readonly="readonly" 
						name="E02LCMPLD" size="65" maxlength="65"
						value="<%=lcNew2.getE02LCMPLD().trim()%>"></TD>

				</TR>
				<TR id="trdark">
					<TD nowrap width="25%">
					<DIV align="right">Tipo de Interés:</DIV>
					</TD>
					<TD nowrap width="25%"><INPUT type="text" name="E02LCMICT" size="1" readonly="readonly" 
						maxlength="1" value="<%=lcNew2.getE02LCMICT().trim()%>"></TD>

					<TD nowrap width="25%">
					<DIV align="right">Tasa de Interés:</DIV>
					</TD>
					<TD nowrap width="25%"><INPUT type="text" name="E02LCMIRT" size="9" readonly="readonly" 
						maxlength="9" value="<%=lcNew2.getE02LCMIRT().trim()%>"></TD>

				</TR>
				<TR id="trclear">
					<TD nowrap width="25%">
					<DIV align="right">Tabla / Tipo de Tasa Flotante:</DIV>
					</TD>
					<TD nowrap width="25%"><INPUT type="text" name="E02LCMFTB" size="2" readonly="readonly" 
						maxlength="2" value="<%=lcNew2.getE02LCMFTB().trim()%>"> <SELECT
						name="E02LCMFTY" disabled="disabled">
						<OPTION value=" "></option>
						<OPTION value="FP">Tasa Primaria</OPTION>
						<OPTION value="FS"
							<%if (lcNew2.getE02LCMOCI().equals("FS"))
	out.print("selected");%>>Tasa
						Secundaria</OPTION>
					</SELECT></TD>
					<TD nowrap width="25%">
					<DIV align="right">Período Base Calc.Intereses:</DIV>
					</TD>
					<TD nowrap width="25%"><INPUT type="text" name="E02LCMBAS" size="3" readonly="readonly" 
						maxlength="3" value="<%=lcNew2.getE02LCMBAS().trim()%>"
						></TD>
				</TR>
			</TBODY>
		</TABLE>
		</td>
	</tr>
</table>
<div align="center"><input type="hidden" name="H01FLGWK1" value="">
&nbsp; &nbsp; &nbsp;</div>
</form>
</body>
</html>
