<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.0 Transitional//EN">
<html>
<head>
<title>Tabla de Gastos y Comisiones de Cartas de Crédito</title>
<META HTTP-EQUIV="Content-Type" CONTENT="text/html; charset=ISO-8859-1">
<META name="GENERATOR" content="IBM WebSphere Page Designer V3.5.2 for Windows">
<META http-equiv="Content-Style-Type" content="text/css">
<link Href="<%=request.getContextPath()%>/pages/style.css" rel="stylesheet">

<jsp:useBean id="sc" class="datapro.eibs.beans.ELC060001Message"  scope="session" />
<jsp:useBean id= "error" class= "datapro.eibs.beans.ELEERRMessage"  scope="session" />
<jsp:useBean id= "userPO" class= "datapro.eibs.beans.UserPos"  scope="session" />

<script language="Javascript1.1" src="<%=request.getContextPath()%>/pages/s/javascripts/eIBS.jsp"> </SCRIPT>
<script language="Javascript1.1" src="<%=request.getContextPath()%>/pages/s/javascripts/optMenu.jsp"> </SCRIPT>

<SCRIPT Language="Javascript">

   	builtHPopUp();

  function showPopUp(opth,field,bank,ccy,field1,field2,opcod) {
   init(opth,field,bank,ccy,field1,field2,opcod);
   showPopupHelp();
   }

</SCRIPT>

</head>

<body>
<% 
 if ( !error.getERRNUM().equals("0")  ) {
 	 error.setERRNUM("0");
    out.println("<SCRIPT Language=\"Javascript\">");
     out.println("       showErrors()");
     out.println("</SCRIPT>");
 }
%>

<h3 align="center">Tabla de Gastos y Comisiones de Cartas de Crédito<img
	src="<%=request.getContextPath()%>/images/e_ibs.gif" align="left"
	name="EIBS_GIF" ALT="sc_basic.jsp,ELC0600"></h3>
<hr size="4">
<form method="post" action="<%=request.getContextPath()%>/servlet/datapro.eibs.products.JSELC0600">
	<INPUT TYPE=HIDDEN NAME="SCREEN" VALUE="4"> 
	<INPUT TYPE=HIDDEN NAME="E01RLCATY" VALUE="<%=sc.getE01RLCATY()%>"> 
	<INPUT TYPE=HIDDEN NAME="OPT" VALUE="<%= userPO.getPurpose()%>">

<table class="tableinfo" cellspacing="0" cellpadding="2" width="100%" border="0">
			<tr id="trdark">
				<td nowrap>
				<div align="right"><b>Banco :</b></div>
				</td>
				<td nowrap>
				<div align="left"><input type="text" name="E01RLCBNK" size="3"
					maxlength="2" value="<%= sc.getE01RLCBNK().trim()%>" readonly></div>
				</td>
				<td nowrap>
				<div align="right"><b>Número de Tabla :</b></div>
				</td>
				<td nowrap>
				<div align="left"><input type="text" name="E01RLCTAR" size="3"
					maxlength="2" value="<%= sc.getE01RLCTAR().trim()%>" readonly></div>
				</td>
				<td nowrap>
				<div align="right"><b>Moneda de Tarifa:</b></div>
				</td>
				<td nowrap>
				<div align="left"><input type="text" name="E01RLCTCY" size="4"
					maxlength="3" value="<%= sc.getE01RLCTCY().trim()%>"> <a
					href="javascript:GetCurrency('E01RLCTCY','')"><img
					src="<%=request.getContextPath()%>/images/1b.gif" alt="help"
					align="absmiddle" border="0"></a></div>
				</td>
			</tr>
			<tr id="trclear">
				<td nowrap height="34">
				<div align="right"><b>Moneda de Cuenta:</b></div>
				</td>
				<td nowrap height="34">
				<div align="left"><input type="text" name="E01RLCACY" size="4"
					maxlength="3" value="<%= sc.getE01RLCACY().trim()%>"> <a
					href="javascript:GetCurrency('E01RLCACY','')"><img
					src="<%=request.getContextPath()%>/images/1b.gif" alt="help"
					align="absmiddle" border="0"></a></div>
				</td>
				<td nowrap height="34">
				<div align="right"><b>Descripción :</b></div>
				</td>
				<td nowrap colspan="3" height="34">
				<div align="left"><input type="text" name="E01RLCDSC" size="30"
					maxlength="25" value="<%= sc.getE01RLCDSC().trim()%>"><A
			href="javascript:GetCorrespondentDescIdSwift('E01PRIRID','E01REQCON','')"></A></div>
				</td>
			</tr>
</table>

<% if (userPO.getPurpose().equals("N")) {%>
<BR>
	<table class="tableinfo" cellspacing="0" cellpadding="2" width="100%" border="0">
			<tr id="trdark">
				<TD nowrap>
				<DIV align="right"><B>Copiar Desde </B></DIV>
				</TD>
				<td nowrap></td>
				<td nowrap>
				<DIV align="right"><B>Tipo de Producto :</B></DIV>
				</TD>
				<TD nowrap width="143">
					<INPUT type="text" name="E01CPYATY" size="12" maxlength="9" value="">
        			<a href="javascript:GetProductRates('E01CPYATY','LC')"> 
        			<img src="<%=request.getContextPath()%>/images/1b.gif" alt="help" align="middle" border="0" ></a> 
        		</TD>
				<TD nowrap width="142">
				<DIV align="right"><B>Número de Tabla :</B></DIV>
				</TD>
				<TD nowrap width="49"><INPUT type="text" name="E01CPYTAR" size="3"
					maxlength="2" value="<%= sc.getE01CPYTAR().trim()%>"></TD>
			</TR>
	</TABLE>
<%}%>
			
<h4>Cargos</h4>

<TABLE class="tableinfo" cellspacing="0" cellpadding="2" width="100%"	border="0">
	<tr id="trdark">
		<td nowrap align="center"><b>Concepto</b></td>
		<td nowrap align="center"><b>Monto</b></td>
		<td nowrap align="center"><b>Porcentaje</b></td>
		<td nowrap align="center"><b>Período</b></td>
		<td nowrap align="center"><b>Mínimo</b></td>
		<td nowrap align="center"><b>Máximo</b></td>
		<td nowrap align="center"><b>IVA</b></td>
	</tr>
				<tr id="trclear">
					<td nowrap>
					<div align="right">Apertura 1 :</div>
					</td>
					<td nowrap></td>
					<td nowrap>
					<div align="center"><input type="text" name="E01RLCIBR" size="12"
						maxlength="11" value="<%= sc.getE01RLCIBR().trim()%>"
						onkeypress="return enterDecimalNum(event)"></div>
					</td>
					<td nowrap>
					<div align="center"><input type="text" name="E01RLCIBP" size="5"
						maxlength="4" value="<%= sc.getE01RLCIBP().trim()%>"
						onkeypress="enterInteger()"></div>
					</td>
					<td nowrap>

					<div align="center"><input type="text" name="E01RLCIMF" size="12"
						maxlength="11" value="<%= sc.getE01RLCIMF().trim()%>"
						onkeypress="enterDecimal()"></div>
					</td>
					<td nowrap>
					<div align="center"><input type="text" name="E01RLCIMX" size="12"
						maxlength="11" value="<%= sc.getE01RLCIMX().trim()%>"
						onkeypress="enterDecimal()"></div>
					</td>
					<td nowrap>
					<div align="center"><select name="E01RLCI01">
						<option value=" "
							<% if (!(sc.getE01RLCI01().equals("Y")||sc.getE01RLCI01().equals("N"))) out.print("selected"); %>></option>
						<option value="Y"
							<% if (sc.getE01RLCI01().equals("Y")) out.print("selected"); %>>Si</option>
						<option value="N"
							<% if (sc.getE01RLCI01().equals("N")) out.print("selected"); %>>No</option>
					</select></div>
					</td>
				</tr>
				<tr id="trdark">
					<td nowrap>
					<div align="right">Apertura 2 :</div>
					</td>
					<td nowrap></td>
					<td nowrap>
					<div align="center"><input type="text" name="E01RLCIR2" size="12"
						maxlength="11" value="<%= sc.getE01RLCIR2().trim()%>"
						onkeypress="return enterDecimalNum(event)"></div>
					</td>
					<td nowrap>
					<div align="center"><input type="text" name="E01RLCIP2" size="5"
						maxlength="4" value="<%= sc.getE01RLCIP2().trim()%>"
						onkeypress="enterInteger()"></div>
					</td>
					<td nowrap>
					<div align="center"><input type="text" name="E01RLCIM2" size="12"
						maxlength="11" value="<%= sc.getE01RLCIM2().trim()%>"
						onkeypress="enterDecimal()"></div>
					</td>
					<td nowrap>
					<div align="center"><input type="text" name="E01RLCIX2" size="12"
						maxlength="11" value="<%= sc.getE01RLCIX2().trim()%>"
						onkeypress="enterDecimal()"></div>
					</td>
					<td nowrap></td>
				</tr>
				<tr id="trclear">
					<td nowrap>
					<div align="right">Aviso :</div>
					</td>
					<td nowrap>
					<div align="center"><input type="text" name="E01RLCADF" size="12"
						maxlength="11" value="<%= sc.getE01RLCADF().trim()%>"
						onkeypress="enterDecimal()"></div>
					</td>
					<td nowrap></td>
					<td nowrap></td>
					<td nowrap></td>
					<td nowrap></td>
					<td nowrap>
					<div align="center"><select name="E01RLCI02">
						<option value=" "
							<% if (!(sc.getE01RLCI02().equals("Y")||sc.getE01RLCI02().equals("N"))) out.print("selected"); %>></option>
						<option value="Y"
							<% if (sc.getE01RLCI02().equals("Y")) out.print("selected"); %>>Si</option>
						<option value="N"
							<% if (sc.getE01RLCI02().equals("N")) out.print("selected"); %>>No</option>
					</select></div>
					</td>
				</tr>
				<tr id="trdark">
					<td nowrap>
					<div align="right">Confirmación 1 :</div>
					</td>
					<td nowrap></td>
					<td nowrap>
					<div align="center"><input type="text" name="E01RLCCBR" size="12"
						maxlength="11" value="<%= sc.getE01RLCCBR().trim()%>"
						onkeypress="return enterDecimalNum(event)"></div>
					</td>
					<td nowrap>
					<div align="center"><input type="text" name="E01RLCCBP" size="5"
						maxlength="4" value="<%= sc.getE01RLCCBP().trim()%>"
						onkeypress="enterInteger()"></div>
					</td>
					<td nowrap>

					<div align="center"><input type="text" name="E01RLCCMF" size="12"
						maxlength="11" value="<%= sc.getE01RLCCMF().trim()%>"
						onkeypress="enterDecimal()"></div>
					</td>
					<td nowrap>
					<div align="center"><input type="text" name="E01RLCCMX" size="12"
						maxlength="11" value="<%= sc.getE01RLCCMX().trim()%>"
						onkeypress="enterDecimal()"></div>
					</td>
					<td nowrap>
					<div align="center"><select name="E01RLCI03">
						<option value=" "
							<% if (!(sc.getE01RLCI03().equals("Y")||sc.getE01RLCI03().equals("N"))) out.print("selected"); %>></option>
						<option value="Y"
							<% if (sc.getE01RLCI03().equals("Y")) out.print("selected"); %>>Si</option>
						<option value="N"
							<% if (sc.getE01RLCI03().equals("N")) out.print("selected"); %>>No</option>
					</select></div>
					</td>
				</tr>
				<tr id="trclear">
					<td nowrap>
					<div align="right">Confirmación 2 :</div>
					</td>
					<td nowrap></td>
					<td nowrap>
					<div align="center"><input type="text" name="E01RLCCR2" size="12"
						maxlength="11" value="<%= sc.getE01RLCCR2().trim()%>"
						onkeypress="return enterDecimalNum(event)"></div>
					</td>
					<td nowrap>
					<div align="center"><input type="text" name="E01RLCCP2" size="5"
						maxlength="4" value="<%= sc.getE01RLCCP2().trim()%>"
						onkeypress="enterInteger()"></div>
					</td>
					<td nowrap>

					<div align="center"><input type="text" name="E01RLCCM2" size="12"
						maxlength="11" value="<%= sc.getE01RLCCM2().trim()%>"
						onkeypress="enterDecimal()"></div>
					</td>
					<td nowrap>
					<div align="center"><input type="text" name="E01RLCCX2" size="12"
						maxlength="11" value="<%= sc.getE01RLCCX2().trim()%>"
						onkeypress="enterDecimal()"></div>
					</td>
					<td nowrap></td>
				</tr>
				<tr id="trdark">
					<td nowrap>
					<div align="right">Extensión de Validez:</div>
					</td>
					<td nowrap></td>
					<td nowrap>
					<div align="center"><input type="text" name="E01RLCEBR" size="12"
						maxlength="11" value="<%= sc.getE01RLCEBR().trim()%>"
						onkeypress="return enterDecimalNum(event)"></div>
					</td>
					<td nowrap>
					<div align="center"><input type="text" name="E01RLCEBP" size="5"
						maxlength="4" value="<%= sc.getE01RLCEBP().trim()%>"
						onkeypress="enterInteger()"></div>
					</td>
					<td nowrap>
					<div align="center"><input type="text" name="E01RLCEMF" size="12"
						maxlength="11" value="<%= sc.getE01RLCEMF().trim()%>"
						onkeypress="enterDecimal()"></div>
					</td>
					<td nowrap>
					<div align="center"><input type="text" name="E01RLCEMX" size="12"
						maxlength="11" value="<%= sc.getE01RLCEMX().trim()%>"
						onkeypress="enterDecimal()"></div>
					</td>
					<td nowrap>
					<div align="center"><select name="E01RLCI04">
						<option value=" "
							<% if (!(sc.getE01RLCI04().equals("Y")||sc.getE01RLCI04().equals("N"))) out.print("selected"); %>></option>
						<option value="Y"
							<% if (sc.getE01RLCI04().equals("Y")) out.print("selected"); %>>Si</option>
						<option value="N"
							<% if (sc.getE01RLCI04().equals("N")) out.print("selected"); %>>No</option>
					</select></div>
					</td>
				</tr>
				<tr id="trclear">
					<td nowrap>
					<div align="right">Enmienda :</div>
					</td>
					<td nowrap>
					<div align="center"><input type="text" name="E01RLCAMF" size="12"
						maxlength="11" value="<%= sc.getE01RLCAMF().trim()%>"
						onkeypress="enterDecimal()"></div>
					</td>
					<td nowrap></td>
					<td nowrap></td>
					<td nowrap></td>
					<td nowrap></td>
					<td nowrap>
					<div align="center"><select name="E01RLCI05">
						<option value=" "
							<% if (!(sc.getE01RLCI05().equals("Y")||sc.getE01RLCI05().equals("N"))) out.print("selected"); %>></option>
						<option value="Y"
							<% if (sc.getE01RLCI05().equals("Y")) out.print("selected"); %>>Si</option>
						<option value="N"
							<% if (sc.getE01RLCI05().equals("N")) out.print("selected"); %>>No</option>
					</select></div>
					</td>
				</tr>
				<tr id="trdark">
					<td nowrap>
					<div align="right">Aviso de Enmienda :</div>
					</td>
					<td nowrap>
					<div align="center"><input type="text" name="E01RLCAAF" size="12"
						maxlength="11" value="<%= sc.getE01RLCAAF().trim()%>"
						onkeypress="enterDecimal()"></div>
					</td>
					<td nowrap></td>
					<td nowrap></td>
					<td nowrap></td>
					<td nowrap></td>
					<td nowrap>
					<div align="center"><select name="E01RLCI06">
						<option value=" "
							<% if (!(sc.getE01RLCI06().equals("Y")||sc.getE01RLCI06().equals("N"))) out.print("selected"); %>></option>
						<option value="Y"
							<% if (sc.getE01RLCI06().equals("Y")) out.print("selected"); %>>Si</option>
						<option value="N"
							<% if (sc.getE01RLCI06().equals("N")) out.print("selected"); %>>No</option>
					</select></div>
					</td>
				</tr>
				<tr id="trclear">
					<td nowrap>
					<div align="right">Discrepancia :</div>
					</td>
					<td nowrap>
					<div align="center"><input type="text" name="E01RLCDIF" size="12"
						maxlength="11" value="<%= sc.getE01RLCDIF().trim()%>"
						onkeypress="enterDecimal()"></div>
					</td>
					<td nowrap></td>
					<td nowrap></td>
					<td nowrap></td>
					<td nowrap></td>
					<td nowrap>
					<div align="center"><select name="E01RLCI07">
						<option value=" "
							<% if (!(sc.getE01RLCI07().equals("Y")||sc.getE01RLCI07().equals("N"))) out.print("selected"); %>></option>
						<option value="Y"
							<% if (sc.getE01RLCI07().equals("Y")) out.print("selected"); %>>Si</option>
						<option value="N"
							<% if (sc.getE01RLCI07().equals("N")) out.print("selected"); %>>No</option>
					</select></div>
					</td>
				</tr>
				<tr id="trdark">
					<td nowrap>
					<div align="right">Pago a la Vista :</div>
					</td>
					<td nowrap></td>
					<td nowrap>
					<div align="center"><input type="text" name="E01RLCSBR" size="12"
						maxlength="11" value="<%= sc.getE01RLCSBR().trim()%>"
						onkeypress="return enterDecimalNum(event)"></div>
					</td>
					<td nowrap></td>
					<td nowrap>
					<div align="center"><input type="text" name="E01RLCSPM" size="12"
						maxlength="11" value="<%= sc.getE01RLCSPM().trim()%>"
						onkeypress="enterDecimal()"></div>
					</td>
					<td nowrap></td>
					<td nowrap>
					<div align="center"><select name="E01RLCI08">
						<option value=" "
							<% if (!(sc.getE01RLCI08().equals("Y")||sc.getE01RLCI08().equals("N"))) out.print("selected"); %>></option>
						<option value="Y"
							<% if (sc.getE01RLCI08().equals("Y")) out.print("selected"); %>>Si</option>
						<option value="N"
							<% if (sc.getE01RLCI08().equals("N")) out.print("selected"); %>>No</option>
					</select></div>
					</td>
				</tr>
				<tr id="trclear">
					<td nowrap>
					<div align="right">Aceptación :</div>
					</td>
					<td nowrap></td>
					<td nowrap>
					<div align="center"><input type="text" name="E01RLCACB" size="12"
						maxlength="11" value="<%= sc.getE01RLCACB().trim()%>"
						onkeypress="return enterDecimalNum(event)"></div>
					</td>
					<td nowrap>
					<div align="center"><input type="text" name="E01RLCABP" size="5"
						maxlength="4" value="<%= sc.getE01RLCABP().trim()%>"
						onkeypress="enterInteger()"></div>
					</td>
					<td nowrap>
					<div align="center"><input type="text" name="E01RLCACM" size="12"
						maxlength="11" value="<%= sc.getE01RLCACM().trim()%>"
						onkeypress="enterDecimal()"></div>
					</td>
					<td nowrap>
					<div align="center"><input type="text" name="E01RLCAMX" size="12"
						maxlength="11" value="<%= sc.getE01RLCAMX().trim()%>"
						onkeypress="enterDecimal()"></div>
					</td>
					<td nowrap>
					<div align="center"><select name="E01RLCI09">
						<option value=" "
							<% if (!(sc.getE01RLCI09().equals("Y")||sc.getE01RLCI09().equals("N"))) out.print("selected"); %>></option>
						<option value="Y"
							<% if (sc.getE01RLCI09().equals("Y")) out.print("selected"); %>>Si</option>
						<option value="N"
							<% if (sc.getE01RLCI09().equals("N")) out.print("selected"); %>>No</option>
					</select></div>
					</td>
				</tr>
				<tr id="trdark">
					<td nowrap>
					<div align="right">Pagos Diferido :</div>
					</td>
					<td nowrap></td>
					<td nowrap>
					<div align="center"><input type="text" name="E01RLCDPB" size="12"
						maxlength="11" value="<%= sc.getE01RLCDPB().trim()%>"
						onkeypress="return enterDecimalNum(event)"></div>
					</td>
					<td nowrap>
					<div align="center"><input type="text" name="E01RLCDBP" size="5"
						maxlength="4" value="<%= sc.getE01RLCDBP().trim()%>"
						onkeypress="enterInteger()"></div>
					</td>
					<td nowrap>
					<div align="center"><input type="text" name="E01RLCDPM" size="12"
						maxlength="11" value="<%= sc.getE01RLCDPM().trim()%>"
						onkeypress="enterDecimal()"></div>
					</td>
					<td nowrap>
					<div align="center"><input type="text" name="E01RLCDMX" size="12"
						maxlength="11" value="<%= sc.getE01RLCDMX().trim()%>"
						onkeypress="enterDecimal()"></div>
					</td>
					<td nowrap>
					<div align="center"><select name="E01RLCI10">
						<option value=" "
							<% if (!(sc.getE01RLCI10().equals("Y")||sc.getE01RLCI10().equals("N"))) out.print("selected"); %>></option>
						<option value="Y"
							<% if (sc.getE01RLCI10().equals("Y")) out.print("selected"); %>>Si</option>
						<option value="N"
							<% if (sc.getE01RLCI10().equals("N")) out.print("selected"); %>>No</option>
					</select></div>
					</td>
				</tr>
				<tr id="trclear">
					<td nowrap>
					<div align="right">Negociación :</div>
					</td>
					<td nowrap></td>
					<td nowrap>
					<div align="center"><input type="text" name="E01RLCNBR" size="12"
						maxlength="11" value="<%= sc.getE01RLCNBR().trim()%>"
						onkeypress="return enterDecimalNum(event)"></div>
					</td>
					<td nowrap></td>
					<td nowrap>
					<div align="center"><input type="text" name="E01RLCNMF" size="12"
						maxlength="11" value="<%= sc.getE01RLCNMF().trim()%>"
						onkeypress="enterDecimal()"></div>
					</td>
					<td nowrap>
					<div align="center"><input type="text" name="E01RLCNMX" size="12"
						maxlength="11" value="<%= sc.getE01RLCNMX().trim()%>"
						onkeypress="enterDecimal()"></div>
					</td>
					<td nowrap>
					<div align="center"><select name="E01RLCI11">
						<option value=" "
							<% if (!(sc.getE01RLCI11().equals("Y")||sc.getE01RLCI11().equals("N"))) out.print("selected"); %>></option>
						<option value="Y"
							<% if (sc.getE01RLCI11().equals("Y")) out.print("selected"); %>>Si</option>
						<option value="N"
							<% if (sc.getE01RLCI11().equals("N")) out.print("selected"); %>>No</option>
					</select></div>
					</td>
				</tr>
				<tr id="trdark">
					<td nowrap>
					<div align="right">Asignación de Fondos :</div>
					</td>
					<td nowrap></td>
					<td nowrap>
					<div align="center"><input type="text" name="E01RLCAPB" size="12"
						maxlength="11" value="<%= sc.getE01RLCAPB().trim()%>"
						onkeypress="return enterDecimalNum(event)"></div>
					</td>
					<td nowrap></td>
					<td nowrap>
					<div align="center"><input type="text" name="E01RLCAPM" size="12"
						maxlength="11" value="<%= sc.getE01RLCAPM().trim()%>"
						onkeypress="enterDecimal()"></div>
					</td>
					<td nowrap>
					<div align="center"></div>
					</td>
					<td nowrap>
					<div align="center"><select name="E01RLCI12">
						<option value=" "
							<% if (!(sc.getE01RLCI12().equals("Y")||sc.getE01RLCI12().equals("N"))) out.print("selected"); %>></option>
						<option value="Y"
							<% if (sc.getE01RLCI12().equals("Y")) out.print("selected"); %>>Si</option>
						<option value="N"
							<% if (sc.getE01RLCI12().equals("N")) out.print("selected"); %>>No</option>
					</select></div>
					</td>
				</tr>
				<tr id="trclear">
					<td nowrap>
					<div align="right">Transferencia :</div>
					</td>
					<td nowrap></td>
					<td nowrap>
					<div align="center"><input type="text" name="E01RLCTBR" size="12"
						maxlength="11" value="<%= sc.getE01RLCTBR().trim()%>"
						onkeypress="return enterDecimalNum(event)"></div>
					</td>
					<td nowrap></td>
					<td nowrap>
					<div align="center"><input type="text" name="E01RLCTMF" size="12"
						maxlength="11" value="<%= sc.getE01RLCTMF().trim()%>"
						onkeypress="enterDecimal()"></div>
					</td>
					<td nowrap>
					<div align="center"><input type="text" name="E01RLCTMX" size="12"
						maxlength="11" value="<%= sc.getE01RLCTMX().trim()%>"
						onkeypress="enterDecimal()"></div>
					</td>
					<td nowrap>
					<div align="center"><select name="E01RLCI13">
						<option value=" "
							<% if (!(sc.getE01RLCI13().equals("Y")||sc.getE01RLCI13().equals("N"))) out.print("selected"); %>></option>
						<option value="Y"
							<% if (sc.getE01RLCI13().equals("Y")) out.print("selected"); %>>Si</option>
						<option value="N"
							<% if (sc.getE01RLCI13().equals("N")) out.print("selected"); %>>No</option>
					</select></div>
					</td>
				</tr>
				<tr id="trdark">
					<td nowrap>
					<div align="right">Portes :</div>
					</td>
					<td nowrap>
					<div align="center"><input type="text" name="E01RLCPST" size="12"
						maxlength="11" value="<%= sc.getE01RLCPST().trim()%>"
						onkeypress="enterDecimal()"></div>
					</td>
					<td nowrap></td>
					<td nowrap></td>
					<td nowrap></td>
					<td nowrap></td>
					<td nowrap>
					<div align="center"><select name="E01RLCI14">
						<option value=" "
							<% if (!(sc.getE01RLCI14().equals("Y")||sc.getE01RLCI14().equals("N"))) out.print("selected"); %>></option>
						<option value="Y"
							<% if (sc.getE01RLCI14().equals("Y")) out.print("selected"); %>>Si</option>
						<option value="N"
							<% if (sc.getE01RLCI14().equals("N")) out.print("selected"); %>>No</option>
					</select></div>
					</td>
				</tr>
				<tr id="trclear">
					<td nowrap>
					<div align="right">Courier :</div>
					</td>
					<td nowrap>
					<div align="center"><input type="text" name="E01RLCCOU" size="12"
						maxlength="11" value="<%= sc.getE01RLCCOU().trim()%>"
						onkeypress="enterDecimal()"></div>
					</td>
					<td nowrap></td>
					<td nowrap></td>
					<td nowrap></td>
					<td nowrap></td>
					<td nowrap>
					<div align="center"><select name="E01RLCI15">
						<option value=" "
							<% if (!(sc.getE01RLCI15().equals("Y")||sc.getE01RLCI15().equals("N"))) out.print("selected"); %>></option>
						<option value="Y"
							<% if (sc.getE01RLCI15().equals("Y")) out.print("selected"); %>>Si</option>
						<option value="N"
							<% if (sc.getE01RLCI15().equals("N")) out.print("selected"); %>>No</option>
					</select></div>
					</td>
				</tr>
				<tr id="trdark">
					<td nowrap>
					<div align="right">Cancelación :</div>
					</td>
					<td nowrap>
					<div align="center"><input type="text" name="E01RLCCAN" size="12"
						maxlength="11" value="<%= sc.getE01RLCCAN().trim()%>"
						onkeypress="enterDecimal()"></div>
					</td>
					<td nowrap></td>
					<td nowrap></td>
					<td nowrap></td>
					<td nowrap></td>
					<td nowrap>
					<div align="center"><select name="E01RLCI16">
						<option value=" "
							<% if (!(sc.getE01RLCI16().equals("Y")||sc.getE01RLCI16().equals("N"))) out.print("selected"); %>></option>
						<option value="Y"
							<% if (sc.getE01RLCI16().equals("Y")) out.print("selected"); %>>Si</option>
						<option value="N"
							<% if (sc.getE01RLCI16().equals("N")) out.print("selected"); %>>No</option>
					</select></div>
					</td>
				</tr>
				<tr id="trclear">
					<td nowrap>
					<div align="right">Miscelaneos :</div>
					</td>
					<td nowrap>
					<div align="center"><input type="text" name="E01RLCNOF" size="12"
						maxlength="11" value="<%= sc.getE01RLCNOF().trim()%>"
						onkeypress="enterDecimal()"></div>
					</td>
					<td nowrap></td>
					<td nowrap></td>
					<td nowrap></td>
					<td nowrap></td>
					<td nowrap>
					<div align="center"><select name="E01RLCI17">
						<option value=" "
							<% if (!(sc.getE01RLCI17().equals("Y")||sc.getE01RLCI17().equals("N"))) out.print("selected"); %>></option>
						<option value="Y"
							<% if (sc.getE01RLCI17().equals("Y")) out.print("selected"); %>>Si</option>
						<option value="N"
							<% if (sc.getE01RLCI17().equals("N")) out.print("selected"); %>>No</option>
					</select></div>
					</td>
				</tr>
				<tr id="trdark">
					<td nowrap>
					<div align="right">Transferencia de Fondos :</div>
					</td>
					<td nowrap>
					<div align="center"><input type="text" name="E01RLCWTF" size="12"
						maxlength="11" value="<%= sc.getE01RLCWTF().trim()%>"
						onkeypress="enterDecimal()"></div>
					</td>
					<td nowrap></td>
					<td nowrap></td>
					<td nowrap></td>
					<td nowrap></td>
					<td nowrap>
					<div align="center"><select name="E01RLCI18">
						<option value=" "
							<% if (!(sc.getE01RLCI18().equals("Y")||sc.getE01RLCI18().equals("N"))) out.print("selected"); %>></option>
						<option value="Y"
							<% if (sc.getE01RLCI18().equals("Y")) out.print("selected"); %>>Si</option>
						<option value="N"
							<% if (sc.getE01RLCI18().equals("N")) out.print("selected"); %>>No</option>
					</select></div>
					</td>
				</tr>
				<tr id="trclear">
					<td nowrap>
					<div align="right">Swift de Apertura :</div>
					</td>
					<td nowrap>
					<div align="center"><input type="text" name="E01RLCCTF" size="12"
						maxlength="11" value="<%= sc.getE01RLCCTF().trim()%>"
						onkeypress="enterDecimal()"></div>
					</td>
					<td nowrap></td>
					<td nowrap></td>
					<td nowrap></td>
					<td nowrap></td>
					<td nowrap>
					<div align="center"><select name="E01RLCI19">
						<option value=" "
							<% if (!(sc.getE01RLCI19().equals("Y")||sc.getE01RLCI19().equals("N"))) out.print("selected"); %>></option>
						<option value="Y"
							<% if (sc.getE01RLCI19().equals("Y")) out.print("selected"); %>>Si</option>
						<option value="N"
							<% if (sc.getE01RLCI19().equals("N")) out.print("selected"); %>>No</option>
					</select></div>
					</td>
				</tr>
				<tr id="trdark">
					<td nowrap>
					<div align="right">Swift Adicional :</div>
					</td>
					<td nowrap>
					<div align="center"><input type="text" name="E01RLCCTM" size="12"
						maxlength="11" value="<%= sc.getE01RLCCTM().trim()%>"
						onkeypress="enterDecimal()"></div>
					</td>
					<td nowrap></td>
					<td nowrap></td>
					<td nowrap></td>
					<td nowrap></td>
					<td nowrap>
					<div align="center"><select name="E01RLCI20">
						<option value=" "
							<% if (!(sc.getE01RLCI20().equals("Y")||sc.getE01RLCI20().equals("N"))) out.print("selected"); %>></option>
						<option value="Y"
							<% if (sc.getE01RLCI20().equals("Y")) out.print("selected"); %>>Si</option>
						<option value="N"
							<% if (sc.getE01RLCI20().equals("N")) out.print("selected"); %>>No</option>
					</select></div>
					</td>
				</tr>
				<tr id="trclear">
					<td nowrap>
					<div align="right">Comisión por Plazo :</div>
					</td>
					<td nowrap></td>
					<td nowrap>
					<div align="center"><input type="text" name="E01RLCCA1" size="12"
						maxlength="11" value="<%= sc.getE01RLCCA1().trim()%>"
						onkeypress="return enterDecimalNum(event)"></div>
					</td>
					<td nowrap>
					<div align="center"><input type="text" name="E01RLCCO1" size="5"
						maxlength="4" value="<%= sc.getE01RLCCO1().trim()%>"
						onkeypress="enterInteger()"></div>
					</td>
					<td nowrap>

					<div align="center"><input type="text" name="E01RLCCN1" size="12"
						maxlength="11" value="<%= sc.getE01RLCCN1().trim()%>"
						onkeypress="enterDecimal()"></div>
					</td>
					<td nowrap>
					<div align="center"><input type="text" name="E01RLCCV1" size="12"
						maxlength="11" value="<%= sc.getE01RLCCV1().trim()%>"
						onkeypress="enterDecimal()"></div>
					</td>
					<td nowrap>
					<div align="center"><select name="E01RLCCI1">
						<option value=" "
							<% if (!(sc.getE01RLCCI1().equals("Y")||sc.getE01RLCCI1().equals("N"))) out.print("selected"); %>></option>
						<option value="Y"
							<% if (sc.getE01RLCCI1().equals("Y")) out.print("selected"); %>>Si</option>
						<option value="N"
							<% if (sc.getE01RLCCI1().equals("N")) out.print("selected"); %>>No</option>
					</select></div>
					</td>
				</tr>
				<tr id="trdark">
					<td nowrap>
					<div align="right">Comisión de Reembolso :</div>
					</td>
					<td nowrap>
					<div align="center"><input type="text" name="E01RLCCA2" size="12"
						maxlength="11" value="<%= sc.getE01RLCCA2().trim()%>"
						onkeypress="enterDecimal()"></div>
					</td>
					<td nowrap></td>
					<td nowrap></td>
					<td nowrap></td>
					<td nowrap></td>
					<td nowrap>
					<div align="center"><select name="E01RLCCI2">
						<option value=" "
							<% if (!(sc.getE01RLCCI1().equals("Y")||sc.getE01RLCCI2().equals("N"))) out.print("selected"); %>></option>
						<option value="Y"
							<% if (sc.getE01RLCCI2().equals("Y")) out.print("selected"); %>>Si</option>
						<option value="N"
							<% if (sc.getE01RLCCI2().equals("N")) out.print("selected"); %>>No</option>
					</select></div>
					</td>
				</tr>
</TABLE>

<br>
<h4>Cuentas Contables</h4>
<TABLE class="tableinfo" cellspacing="0" cellpadding="2" width="100%" border="0">
			<TR id="trdark">
				<TD nowrap align="right" width="178"><B>Concepto</B></TD>
				<TD nowrap align="center" width="199"><B>Cuenta</B></TD>
				<TD nowrap align="center" width="260"><B>Descripción</B></TD>
			</TR>
			<TR id="trclear">
				<TD nowrap width="178">
				<DIV align="right">Apertura:</DIV>
				</TD>
				<TD nowrap width="212">
				<DIV align="center"><INPUT type="text" name="E01RLCGIS" size="19"
							maxlength="16" value="<%= sc.getE01RLCGIS().trim()%>"
							onkeypress="enterInteger()"> 
							<A href="javascript:GetLedger('E01RLCGIS',document.forms[0].E01RLCBNK.value,document.forms[0].E01RLCACY.value,'')">
							<IMG src="<%=request.getContextPath()%>/images/1b.gif" alt="help" align="absbottom" border="0"></A>
				</DIV>
				</TD>
				<TD nowrap width="234">
				<DIV align="center"><INPUT type="text" name="E01DSCGIS" size="40" maxlength="35" value="<%= sc.getE01DSCGIS().trim()%>" readonly></DIV>
				</TD>
			</TR>
			<TR id="trdark">
				<TD nowrap width="178">
				<DIV align="right">Aviso :</DIV>
				</TD>
				<TD nowrap width="212">
				<DIV align="center"><INPUT type="text" name="E01RLCGAD" size="19"
							maxlength="16" value="<%= sc.getE01RLCGAD().trim()%>"
							onkeypress="enterInteger()"> 
							<A href="javascript:GetLedger('E01RLCGAD',document.forms[0].E01RLCBNK.value,document.forms[0].E01RLCACY.value,'')">
							<IMG src="<%=request.getContextPath()%>/images/1b.gif" alt="help" align="absbottom" border="0"></A>
				</DIV>
				</TD>
				<TD nowrap width="234">
				<DIV align="center"><INPUT type="text" name="E01DSCGAD" size="40" maxlength="35" value="<%= sc.getE01DSCGAD().trim()%>" readonly></DIV>
				</TD>
			</TR>
			<TR id="trclear">
				<TD nowrap width="178">
				<DIV align="right">Confirmación :</DIV>
				</TD>
				<TD nowrap width="212">
				<DIV align="center"><INPUT type="text" name="E01RLCGCO" size="19"
							maxlength="16" value="<%= sc.getE01RLCGCO().trim()%>"
							onkeypress="enterInteger()"> 
							<A href="javascript:GetLedger('E01RLCGCO',document.forms[0].E01RLCBNK.value,document.forms[0].E01RLCACY.value,'')">
							<IMG src="<%=request.getContextPath()%>/images/1b.gif" alt="help" align="absbottom" border="0"></A>
				</DIV>
				</TD>
				<TD nowrap width="234">
				<DIV align="center"><INPUT type="text" name="E01DSCGCO" size="40" maxlength="35" value="<%= sc.getE01DSCGCO().trim()%>" readonly></DIV>
				</TD>
			</TR>
			<TR id="trdark">
				<TD nowrap width="178">
				<DIV align="right">Extensión de Validez:</DIV>
				</TD>
				<TD nowrap width="212">
				<DIV align="center"><INPUT type="text" name="E01RLCGEV" size="19"
							maxlength="16" value="<%= sc.getE01RLCGEV().trim()%>"
							onkeypress="enterInteger()"> 
							<A href="javascript:GetLedger('E01RLCGEV',document.forms[0].E01RLCBNK.value,document.forms[0].E01RLCACY.value,'')">
							<IMG src="<%=request.getContextPath()%>/images/1b.gif" alt="help" align="absbottom" border="0"></A>
				</DIV>
				</TD>
				<TD nowrap width="234">
				<DIV align="center"><INPUT type="text" name="E01DSCGEV" size="40" maxlength="35" value="<%= sc.getE01DSCGEV().trim()%>" readonly></DIV>
				</TD>
			</TR>
			<TR id="trclear">
				<TD nowrap width="178">
				<DIV align="right">Enmienda :</DIV>
				</TD>
				<TD nowrap width="212">
				<DIV align="center"><INPUT type="text" name="E01RLCGAM" size="19"
							maxlength="16" value="<%= sc.getE01RLCGAM().trim()%>"
							onkeypress="enterInteger()"> 
							<A href="javascript:GetLedger('E01RLCGAM',document.forms[0].E01RLCBNK.value,document.forms[0].E01RLCACY.value,'')">
							<IMG src="<%=request.getContextPath()%>/images/1b.gif" alt="help" align="absbottom" border="0"></A>
				</DIV>
				</TD>
				<TD nowrap width="234">
				<DIV align="center"><INPUT type="text" name="E01DSCGAM" size="40" maxlength="35" value="<%= sc.getE01DSCGAM().trim()%>" readonly></DIV>
				</TD>
			</TR>
			<TR id="trdark">
				<TD nowrap width="178">
				<DIV align="right">Aviso de Enmienda :</DIV>
				</TD>
				<TD nowrap width="212">
				<DIV align="center"><INPUT type="text" name="E01RLCGAA" size="19"
							maxlength="16" value="<%= sc.getE01RLCGAA().trim()%>"
							onkeypress="enterInteger()"> 
							<A href="javascript:GetLedger('E01RLCGAA',document.forms[0].E01RLCBNK.value,document.forms[0].E01RLCACY.value,'')">
							<IMG src="<%=request.getContextPath()%>/images/1b.gif" alt="help" align="absbottom" border="0"></A>
				</DIV>
				</TD>
				<TD nowrap width="234">
				<DIV align="center"><INPUT type="text" name="E01DSCGAA" size="40" maxlength="35" value="<%= sc.getE01DSCGAA().trim()%>" readonly></DIV>
				</TD>
			</TR>
			<TR id="trclear">
				<TD nowrap width="178">
				<DIV align="right">Discrepancia :</DIV>
				</TD>
				<TD nowrap width="212">
				<DIV align="center"><INPUT type="text" name="E01RLCGDI" size="19"
							maxlength="16" value="<%= sc.getE01RLCGDI().trim()%>"
							onkeypress="enterInteger()"> 
							<A href="javascript:GetLedger('E01RLCGDI',document.forms[0].E01RLCBNK.value,document.forms[0].E01RLCACY.value,'')">
							<IMG src="<%=request.getContextPath()%>/images/1b.gif" alt="help" align="absbottom" border="0"></A>
				</DIV>
				</TD>
				<TD nowrap width="234">
				<DIV align="center"><INPUT type="text" name="E01DSCGDI" size="40" maxlength="35" value="<%= sc.getE01DSCGDI().trim()%>" readonly></DIV>
				</TD>
			</TR>
			<TR id="trdark">
				<TD nowrap width="178">
				<DIV align="right">Pago a la Vista :</DIV>
				</TD>
				<TD nowrap width="212">
				<DIV align="center"><INPUT type="text" name="E01RLCGPM" size="19"
							maxlength="16" value="<%= sc.getE01RLCGPM().trim()%>"
							onkeypress="enterInteger()"> 
							<A href="javascript:GetLedger('E01RLCGPM',document.forms[0].E01RLCBNK.value,document.forms[0].E01RLCACY.value,'')">
							<IMG src="<%=request.getContextPath()%>/images/1b.gif" alt="help" align="absbottom" border="0"></A>
				</DIV>
				</TD>
				<TD nowrap width="234">
				<DIV align="center"><INPUT type="text" name="E01DSCGPM" size="40" maxlength="35" value="<%= sc.getE01DSCGPM().trim()%>" readonly></DIV>
				</TD>
			</TR>
			<TR id="trclear">
				<TD nowrap width="178">
				<DIV align="right">Aceptación:</DIV>
				</TD>
				<TD nowrap width="212">
				<DIV align="center"><INPUT type="text" name="E01RLCGAC" size="19"
							maxlength="16" value="<%= sc.getE01RLCGAC().trim()%>"
							onkeypress="enterInteger()"> 
							<A href="javascript:GetLedger('E01RLCGAC',document.forms[0].E01RLCBNK.value,document.forms[0].E01RLCACY.value,'')">
							<IMG src="<%=request.getContextPath()%>/images/1b.gif" alt="help" align="absbottom" border="0"></A>
				</DIV>
				</TD>
				<TD nowrap width="234">
				<DIV align="center"><INPUT type="text" name="E01DSCGAC" size="40" maxlength="35" value="<%= sc.getE01DSCGAC().trim()%>" readonly></DIV>
				</TD>
			</TR>
			<TR id="trdark">
				<TD nowrap width="178">
				<DIV align="right">Pagos Diferido :</DIV>
				</TD>
				<TD nowrap width="212">
				<DIV align="center"><INPUT type="text" name="E01RLCGDP" size="19"
							maxlength="16" value="<%= sc.getE01RLCGDP().trim()%>"
							onkeypress="enterInteger()"> 
							<A href="javascript:GetLedger('E01RLCGDP',document.forms[0].E01RLCBNK.value,document.forms[0].E01RLCACY.value,'')">
							<IMG src="<%=request.getContextPath()%>/images/1b.gif" alt="help" align="absbottom" border="0"></A>
				</DIV>
				</TD>
				<TD nowrap width="234">
				<DIV align="center"><INPUT type="text" name="E01DSCGDP" size="40" maxlength="35" value="<%= sc.getE01DSCGDP().trim()%>" readonly></DIV>
				</TD>
			</TR>
			<TR id="trclear">
				<TD nowrap width="178">
				<DIV align="right">Negociación :</DIV>
				</TD>
				<TD nowrap width="212">
				<DIV align="center"><INPUT type="text" name="E01RLCGNE" size="19"
							maxlength="16" value="<%= sc.getE01RLCGNE().trim()%>"
							onkeypress="enterInteger()"> 
							<A href="javascript:GetLedger('E01RLCGNE',document.forms[0].E01RLCBNK.value,document.forms[0].E01RLCACY.value,'')">
							<IMG src="<%=request.getContextPath()%>/images/1b.gif" alt="help" align="absbottom" border="0"></A>
				</DIV>
				</TD>
				<TD nowrap width="234">
				<DIV align="center"><INPUT type="text" name="E01DSCGNE" size="40" maxlength="35" value="<%= sc.getE01DSCGNE().trim()%>" readonly></DIV>
				</TD>
			</TR>
			<TR id="trdark">
				<TD nowrap width="178">
				<DIV align="right">Asignación de Fondos:</DIV>
				</TD>
				<TD nowrap width="212">
				<DIV align="center"><INPUT type="text" name="E01RLCGAS" size="19"
							maxlength="16" value="<%= sc.getE01RLCGAS().trim()%>"
							onkeypress="enterInteger()"> 
							<A href="javascript:GetLedger('E01RLCGAS',document.forms[0].E01RLCBNK.value,document.forms[0].E01RLCACY.value,'')">
							<IMG src="<%=request.getContextPath()%>/images/1b.gif" alt="help" align="absbottom" border="0"></A>
				</DIV>
				</TD>
				<TD nowrap width="234">
				<DIV align="center"><INPUT type="text" name="E01DSCGAS" size="40" maxlength="35" value="<%= sc.getE01DSCGAS().trim()%>" readonly></DIV>
				</TD>
			</TR>
			<TR id="trclear">
				<TD nowrap width="178">
				<DIV align="right">Transferencia :</DIV>
				</TD>
				<TD nowrap width="212">
				<DIV align="center"><INPUT type="text" name="E01RLCGTR" size="19"
							maxlength="16" value="<%= sc.getE01RLCGTR().trim()%>"
							onkeypress="enterInteger()"> 
							<A href="javascript:GetLedger('E01RLCGTR',document.forms[0].E01RLCBNK.value,document.forms[0].E01RLCACY.value,'')">
							<IMG src="<%=request.getContextPath()%>/images/1b.gif" alt="help" align="absbottom" border="0"></A>
				</DIV>
				</TD>
				<TD nowrap width="234">
				<DIV align="center"><INPUT type="text" name="E01DSCGTR" size="40" maxlength="35" value="<%= sc.getE01DSCGTR().trim()%>" readonly></DIV>
				</TD>
			</TR>
			<TR id="trdark">
				<TD nowrap width="178">
				<DIV align="right">Portes :</DIV>
				</TD>
				<TD nowrap width="212">
				<DIV align="center"><INPUT type="text" name="E01RLCGPO" size="19"
							maxlength="16" value="<%= sc.getE01RLCGPO().trim()%>"
							onkeypress="enterInteger()"> 
							<A href="javascript:GetLedger('E01RLCGPO',document.forms[0].E01RLCBNK.value,document.forms[0].E01RLCACY.value,'')">
							<IMG src="<%=request.getContextPath()%>/images/1b.gif" alt="help" align="absbottom" border="0"></A>
				</DIV>
				</TD>
				<TD nowrap width="234">
				<DIV align="center"><INPUT type="text" name="E01DSCGPO" size="40" maxlength="35" value="<%= sc.getE01DSCGPO().trim()%>" readonly></DIV>
				</TD>
			</TR>
			<TR id="trclear">
				<TD nowrap width="178">
				<DIV align="right">Courier :</DIV>
				</TD>
				<TD nowrap width="212">
				<DIV align="center"><INPUT type="text" name="E01RLCGCR" size="19"
							maxlength="16" value="<%= sc.getE01RLCGCR().trim()%>"
							onkeypress="enterInteger()"> 
							<A href="javascript:GetLedger('E01RLCGCR',document.forms[0].E01RLCBNK.value,document.forms[0].E01RLCACY.value,'')">
							<IMG src="<%=request.getContextPath()%>/images/1b.gif" alt="help" align="absbottom" border="0"></A>
				</DIV>
				</TD>
				<TD nowrap width="234">
				<DIV align="center"><INPUT type="text" name="E01DSCGCR" size="40" maxlength="35" value="<%= sc.getE01DSCGCR().trim()%>" readonly></DIV>
				</TD>
			</TR>
			<TR id="trdark">
				<TD nowrap width="178">
				<DIV align="right">Cancelación :</DIV>
				</TD>
				<TD nowrap width="212">
				<DIV align="center"><INPUT type="text" name="E01RLCGCA" size="19"
							maxlength="16" value="<%= sc.getE01RLCGCA().trim()%>"
							onkeypress="enterInteger()"> 
							<A href="javascript:GetLedger('E01RLCGCA',document.forms[0].E01RLCBNK.value,document.forms[0].E01RLCACY.value,'')">
							<IMG src="<%=request.getContextPath()%>/images/1b.gif" alt="help" align="absbottom" border="0"></A>
				</DIV>
				</TD>
				<TD nowrap width="234">
				<DIV align="center"><INPUT type="text" name="E01DSCGCA" size="40" maxlength="35" value="<%= sc.getE01DSCGCA().trim()%>" readonly></DIV>
				</TD>
			</TR>
			<TR id="trclear">
				<TD nowrap width="178">
				<DIV align="right">Miscelaneos :</DIV>
				</TD>
				<TD nowrap width="212">
				<DIV align="center"><INPUT type="text" name="E01RLCGNF" size="19"
							maxlength="16" value="<%= sc.getE01RLCGNF().trim()%>"
							onkeypress="enterInteger()"> 
							<A href="javascript:GetLedger('E01RLCGNF',document.forms[0].E01RLCBNK.value,document.forms[0].E01RLCACY.value,'')">
							<IMG src="<%=request.getContextPath()%>/images/1b.gif" alt="help" align="absbottom" border="0"></A>
				</DIV>
				</TD>
				<TD nowrap width="234">
				<DIV align="center"><INPUT type="text" name="E01DSCGNF" size="40" maxlength="35" value="<%= sc.getE01DSCGNF().trim()%>" readonly></DIV>
				</TD>
			</TR>
			<TR id="trdark">
				<TD nowrap width="178">
				<DIV align="right">Transferencia de Fondos:</DIV>
				</TD>
				<TD nowrap width="212">
				<DIV align="center"><INPUT type="text" name="E01RLCGWT" size="19"
							maxlength="16" value="<%= sc.getE01RLCGWT().trim()%>"
							onkeypress="enterInteger()"> 
							<A href="javascript:GetLedger('E01RLCGWT',document.forms[0].E01RLCBNK.value,document.forms[0].E01RLCACY.value,'')">
							<IMG src="<%=request.getContextPath()%>/images/1b.gif" alt="help" align="absbottom" border="0"></A>
				</DIV>
				</TD>
				<TD nowrap width="234">
				<DIV align="center"><INPUT type="text" name="E01DSCGWT" size="40" maxlength="35" value="<%= sc.getE01DSCGWT().trim()%>" readonly></DIV>
				</TD>
			</TR>
			<TR id="trclear">
				<TD nowrap width="178">
				<DIV align="right">Swift de Apertura:</DIV>
				</TD>
				<TD nowrap width="212">
				<DIV align="center"><INPUT type="text" name="E01RLCGTF" size="19"
							maxlength="16" value="<%= sc.getE01RLCGTF().trim()%>"
							onkeypress="enterInteger()"> 
							<A href="javascript:GetLedger('E01RLCGTF',document.forms[0].E01RLCBNK.value,document.forms[0].E01RLCACY.value,'')">
							<IMG src="<%=request.getContextPath()%>/images/1b.gif" alt="help" align="absbottom" border="0"></A>
				</DIV>
				</TD>
				<TD nowrap width="234">
				<DIV align="center"><INPUT type="text" name="E01DSCGTF" size="40" maxlength="35" value="<%= sc.getE01DSCGTF().trim()%>" readonly></DIV>
				</TD>
			</TR>
			<TR id="trdark">
				<TD nowrap width="178">
				<DIV align="right">Swift Adicional:</DIV>
				</TD>
				<TD nowrap width="212">
				<DIV align="center"><INPUT type="text" name="E01RLCGTM" size="19"
							maxlength="16" value="<%= sc.getE01RLCGTM().trim()%>"
							onkeypress="enterInteger()"> 
							<A href="javascript:GetLedger('E01RLCGTM',document.forms[0].E01RLCBNK.value,document.forms[0].E01RLCACY.value,'')">
							<IMG src="<%=request.getContextPath()%>/images/1b.gif" alt="help" align="absbottom" border="0"></A>
				</DIV>
				</TD>
				<TD nowrap width="234">
				<DIV align="center"><INPUT type="text" name="E01DSCGTM" size="40" maxlength="35" value="<%= sc.getE01DSCGTM().trim()%>" readonly></DIV>
				</TD>
			</TR>
			<TR id="trclear">
				<TD nowrap width="178">
				<DIV align="right">Comisión por Plazo:</DIV>
				</TD>
				<TD nowrap width="212">
				<DIV align="center"><INPUT type="text" name="E01RLCCG1" size="19"
							maxlength="16" value="<%= sc.getE01RLCCG1().trim()%>"
							onkeypress="enterInteger()"> 
							<A href="javascript:GetLedger('E01RLCCG1',document.forms[0].E01RLCBNK.value,document.forms[0].E01RLCACY.value,'')">
							<IMG src="<%=request.getContextPath()%>/images/1b.gif" alt="help" align="absbottom" border="0"></A>
				</DIV>
				</TD>
				<TD nowrap width="234">
				<DIV align="center"><INPUT type="text" name="E01RLCCD1" size="40" maxlength="35" value="<%= sc.getE01RLCCD1().trim()%>" readonly></DIV>
				</TD>
			</TR>
			<TR id="trdark">
				<TD nowrap width="178">
				<DIV align="right">Comisión de Reembolso :</DIV>
				</TD>
				<TD nowrap width="212">
				<DIV align="center"><INPUT type="text" name="E01RLCCG2" size="19"
							maxlength="16" value="<%= sc.getE01RLCCG2().trim()%>"
							onkeypress="enterInteger()"> 
							<A href="javascript:GetLedger('E01RLCCG2',document.forms[0].E01RLCBNK.value,document.forms[0].E01RLCACY.value,'')">
							<IMG src="<%=request.getContextPath()%>/images/1b.gif" alt="help" align="absbottom" border="0"></A>
				</DIV>
				</TD>
				<TD nowrap width="234">
				<DIV align="center"><INPUT type="text" name="E01RLCCD2" size="40" maxlength="35" value="<%= sc.getE01RLCCD2().trim()%>" readonly></DIV>
				</TD>
			</TR>
</TABLE>

<SCRIPT language="javascript">
    function tableresize() {
     adjustEquTables(headTable1,dataTable1,dataDiv1,0,false);
   }
  tableresize();
  window.onresize=tableresize;  
  
  </SCRIPT> <% 
		if (error.getERWRNG().equals("Y")) { 
			error.setERWRNG(" ");
	%>
<h4 style="text-align: center"><input type="checkbox" name="H01FLGWK2" value="A"> Aceptar con Advertencias</h4>
<% 
		} 
	%> <br>
<div align="center"><input id="EIBSBTN" type=submit name="Submit"
	value="Enviar"></div>
</form>
</body>
</html>
