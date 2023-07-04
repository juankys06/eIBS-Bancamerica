<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.0 Transitional//EN">

<%@ page import="datapro.eibs.master.Util"%>
<html>
<head>
<title>Foreign Exchange Module</title>
<META HTTP-EQUIV="Content-Type" CONTENT="text/html; charset=ISO-8859-1">
<META name="GENERATOR"
	content="IBM WebSphere Page Designer V3.5.2 for Windows">
<META http-equiv="Content-Style-Type" content="text/css">
<link Href="<%=request.getContextPath()%>/pages/style.css"
	rel="stylesheet">

<jsp:useBean id="qryForexSearchParams"
	class="datapro.eibs.beans.EFE0300DSMessage" scope="session" />
<jsp:useBean id="fex" class="datapro.eibs.beans.EFE0300DSMessage"
	scope="session" />
<jsp:useBean id="error" class="datapro.eibs.beans.ELEERRMessage"
	scope="session" />
<jsp:useBean id="userPO" class="datapro.eibs.beans.UserPos"
	scope="session" />
<jsp:useBean id="trOption" class="datapro.eibs.beans.TrOption"
	scope="session" />

<script
	src="<%=request.getContextPath()%>/pages/s/javascripts/eIBSTreasury.jsp"> </SCRIPT>
<script src="<%=request.getContextPath()%>/pages/s/javascripts/eIBS.jsp"> </SCRIPT>
<script
	src="<%=request.getContextPath()%>/pages/s/javascripts/optMenu.jsp"> </SCRIPT>

</head>
<body>
<%
		if (!error.getERRNUM().equals("0")) {
		error.setERRNUM("0");
		out.println("<SCRIPT Language=\"Javascript\">");
		out.println("       showErrors()");
		out.println("</SCRIPT>");
	}
	String sTitle = " Swap - Moneda Extranjera";
	try {
		sTitle = trOption.getHeader2();
	} catch (Exception e) {
		sTitle = " Swap - Moneda Extranjera";
	}
	if (sTitle.trim().length() == 0)
		sTitle = " Swap - Moneda Extranjera";
%>
<h3 align="center"><%=sTitle%> <img
	src="<%=request.getContextPath()%>/images/e_ibs.gif" align="left"
	name="EIBS_GIF" alt="fe_basic_ModExt.jsp,EFE0300"></h3>
<hr size="4">
<form method="post"
	action="<%=request.getContextPath()%>/servlet/datapro.eibs.forex.JSEFE0300">
<INPUT TYPE=HIDDEN NAME="SCREEN" VALUE="2"> <br>
<table class="tableinfo">
	<tr>
		<td nowrap width="100%">
		<table width="100%" border=0>
			<tr id="trdark">
				<td nowrap colspan="6">
				<div align="center"><b>Detalle de Moneda Extranjera </b></div>
				</td>
			</tr>
			<tr>
				<td colspan="6" style="height:10px;"></td>
			</tr>
			<tr>
				<td
					style="white-space: nowrap; width: 26%; text-align: right;font-weight: bold">ID
				Cliente :</td>
				<td style="white-space: nowrap; width: 24%;"><%=fex.getE01FEICID()%></td>
				<td
					style="white-space: nowrap; width: 26%; text-align: right;font-weight: bold">
				N&uacute;mero de Cliente :</td>
				<td style="white-space: nowrap; width: 24%;"><%=fex.getE01FEICUN()%></td>
			</tr>
			<tr>
				<td style="white-space: nowrap; text-align: right;"><b>Nombre
				del Cliente :</b></td>
				<td nowrap colspan=2><%=fex.getE01FEINAM()%></td>
			</tr>
		</table>
		</td>
	</tr>
</table>
<br>
<INPUT TYPE=HIDDEN NAME="E01FEITYP" VALUE="SWAP">
<table class="tableinfo" width="100%">
	<tr>
		<td nowrap align="left">
		<table cellspacing="0" cellpadding="2" width="100%" border="0">
			<tr id="trclear">
				<td nowrap width="26%">
				<div align="right"><b>Banco :</b></div>
				</td>
				<td nowrap width="24%"><%=fex.getE01FEIBNK()%></td>

				<td nowrap width="26%">
				<div align="right"><b>Cuenta :</b></div>
				</td>
				<td nowrap width="24%"><%=fex.getE01FEIACC()%></td>

			</tr>
			<tr id="trclear">
				<td nowrap width="15%">
				<div align="right"><b>Moneda :</b></div>
				</td>
				<td nowrap width="182"><%=fex.getE01FEICCY()%></td>
				<td nowrap width="15%">
				<div align="right"><b>Monto de Operaci&oacute;n :</b></div>
				</td>
				<td nowrap><%=fex.getE01FEIAMR()%></td>
			</tr>
			<tr id="trclear">
				<td nowrap width="15%">
				<div align="right"><b>Utilidad de la Operaci&oacute;n :</b></div>
				</td>
				<td nowrap width="182"><%=fex.getE01FEIUTI()%></td>
				<td nowrap width="15%">
				<div align="right"><b>Monto en Moneda Local :</b></div>
				</td>
				<td nowrap><%=fex.getE01FEIAMD()%></td>
			</tr>
			<tr id="trclear">
				<td nowrap width="15%">
				<div align="right"><b>Sucursal :</b></div>
				</td>
				<td nowrap width="182"><%=fex.getE01FEIBRN()%></td>
				<td nowrap width="15%" align="left">
				<div align="right"><b> Tasa de Operaci&oacute;n :</b></div>
				</td>
				<td nowrap><%=fex.getE01FEIRAT()%></td>


			</tr>
			<tr id="trclear">
				<td nowrap width="15%">
				<div align="right"><b>Cajero :</b></div>
				</td>
				<td nowrap width="182"><%=fex.getE01FEITLR()%></td>
				<td nowrap width="15%" align="left">
				<div align="right"><b>Tasa Spot :</b></div>
				</td>
				<td nowrap><%=fex.getE01FEIRAB()%></td>
			</tr>			
			<tr id="trclear">
				<td nowrap width="15%">
				<div align="right"><b>Trader :</b></div>
				</td>
				<td nowrap width="182"><%=fex.getE01FEIUSR()%></td>
				<td nowrap width="15%" align="left">
				<div align="right"><b> Operaci&oacute;n :</b></div>
				</td>
				<td nowrap>
				<%
					String typ = fex.getE01FEITYP();
					if (typ.equalsIgnoreCase("P"))
						out.write("Compra");
					else if (typ.equalsIgnoreCase("S"))
						out.write("Venta");
					else
						out.write(typ);
				%>
				</td>
			</tr>

			<tr id="trclear">
				<td nowrap width="15%">
				<div align="right"><b>Forma de pago :</b></div>
				</td>
				<td nowrap width="182"><%=fex.getE01FEIFOR()%></td>

				<td nowrap width="15%" align="left">
				<div align="right"><b> Fecha de Transacci&oacute;n :</b></div>
				</td>
				<td nowrap><%=Util.formatDate(fex.getE01FEIDDD(), fex.getE01FEIDMM(),
					fex.getE01FEIDYY())%></td>
			</tr>

			<tr id="trclear">
				<td nowrap width="15%">
				<div align="right"><b>Referencia :</b></div>
				</td>
				<td nowrap width="182"><%=fex.getE01FEIREF()%></td>

				<td nowrap width="15%" align="left">
				<div align="right"><b> </b></div>
				</td>
				<td nowrap></td>
			</tr>
		</table>
		<input type="HIDDEN" name="E01FESTYP0" value="SWAP"></td>
	</tr>
</table>
<font face="Arial"><font face="Arial"><font face="Arial"><font
	face="Arial"><br>
<font size="2"><input type="hidden" name="E01FEICP3" value=""
	readonly> </font></font></font></font></font>
<table width="100%" border="0" cellspacing="0" cellpadding="0"
	bgcolor="#FFFFFF" bordercolor="#FFFFFF">
	<tr bgcolor="#FFFFFF">
		<td width="33%"></td>
	</tr>
</table>
<table width="100%" border="0" cellspacing="0" cellpadding="0"
	bgcolor="#FFFFFF" bordercolor="#FFFFFF">
	<tr bgcolor="#FFFFFF">
		<td width="33%">
		<div align="center"><input id="EIBSBTN" type=submit
			name="Volver" value="Volver"></div>
		</td>
	</tr>
	<tr bgcolor="#FFFFFF">
		<td></td>
	</tr>
</table>
<input type="hidden" name="E01FEIDDI"
	value="<%=qryForexSearchParams.getE01FEIDDI()%>"> <input
	type="hidden" name="E01FEIMMI"
	value="<%=qryForexSearchParams.getE01FEIMMI()%>"> <input
	type="hidden" name="E01FEIYYI"
	value="<%=qryForexSearchParams.getE01FEIYYI()%>"> <input
	type="hidden" name="E01FEIDDF"
	value="<%=qryForexSearchParams.getE01FEIDDF()%>"> <input
	type="hidden" name="E01FEIMMF"
	value="<%=qryForexSearchParams.getE01FEIMMF()%>"> <input
	type="hidden" name="E01FEIYYF"
	value="<%=qryForexSearchParams.getE01FEIYYF()%>"> <input
	type="hidden" name="E01FEICUN"
	value="<%=qryForexSearchParams.getE01FEICUN()%>"> <input
	type="hidden" name="E01FEICID"
	value="<%=qryForexSearchParams.getE01FEICID()%>"> <input
	type="hidden" name="E01FEIBRN"
	value="<%=qryForexSearchParams.getE01FEIBRN()%>"> <input
	type="hidden" name="E01FEIUSR"
	value="<%=qryForexSearchParams.getE01FEIUSR()%>"> <input
	type="hidden" name="E01FEICCY"
	value="<%=qryForexSearchParams.getE01FEICCY()%>"></form>
</body>
</html>
