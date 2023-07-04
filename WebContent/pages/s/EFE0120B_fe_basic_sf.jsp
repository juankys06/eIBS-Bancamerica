<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.0 Transitional//EN">

<%@ page import="datapro.eibs.master.Util"%>
<%@ page import="datapro.eibs.forex.ST_PaymentType"%>
<%@ page import="datapro.eibs.forex.ST_ReceptionType"%>
<html>
<head>
<title>Foreign Exchange Module</title>
<META HTTP-EQUIV="Content-Type" CONTENT="text/html; charset=ISO-8859-1">
<META name="GENERATOR"
	content="IBM WebSphere Page Designer V3.5.2 for Windows">
<META http-equiv="Content-Style-Type" content="text/css">
<link Href="<%=request.getContextPath()%>/pages/style.css"
	rel="stylesheet">

<jsp:useBean id="fex" class="datapro.eibs.beans.EFE0120DSMessage"
	scope="session" />
<jsp:useBean id="currClient" class="datapro.eibs.beans.ESD080001Message"
	scope="session" />
<jsp:useBean id="error" class="datapro.eibs.beans.ELEERRMessage"
	scope="session" />
<jsp:useBean id="userPO" class="datapro.eibs.beans.UserPos"
	scope="session" />

<script
	src="<%=request.getContextPath()%>/pages/s/javascripts/eIBSTreasury.jsp"> </SCRIPT>
<script src="<%=request.getContextPath()%>/pages/s/javascripts/eIBS.jsp"> </SCRIPT>
<script
	src="<%=request.getContextPath()%>/pages/s/javascripts/optMenu.jsp"> </SCRIPT>


<SCRIPT Language="Javascript">

   builtHPopUp();

  function showPopUp(opth,field,bank,ccy,field1,field2,opcod) {
   init(opth,field,bank,ccy,field1,field2,opcod);
   showPopupHelp();
   }
  function showPopUp2(opth,field,bank,ccy,field1,field2,opcod) {
   var concepto=document.getElementById("E01FESOOP").value;
   //if (concepto == "01")
//     Client=document.getElementById("E01DEACUN").value;
   //else
     //Client="";  
   init(opth,field,bank,ccy,field1,field2,opcod);
   showPopupHelp();
   }

</SCRIPT>

</head>
<% 
	ST_PaymentType paymentType_ES = new ST_PaymentType("es"); 
	ST_ReceptionType receptionType_ES = new ST_ReceptionType("es"); 	
%>
<body>
<%
		if (!error.getERRNUM().equals("0")) {
		error.setERRNUM("0");
		out.println("<SCRIPT Language=\"Javascript\">");
		out.println("       showErrors()");
		out.println("</SCRIPT>");
	}
%>
<h3 align="center">Spot Moneda Extranjera (Back Office)<img
	src="<%=request.getContextPath()%>/images/e_ibs.gif" align="left"
	name="EIBS_GIF" alt="fe_basic_sf.jsp,EFE0120B"></h3>
<hr size="4">
<form method="post"
	action="<%=request.getContextPath()%>/servlet/datapro.eibs.forex.JSEFE0120B">
<%
	String ogen = "";
	if (fex.getE01FESTIN().equals("T")) {
		ogen = "Tesorería";
		currClient.setE01CUSCUN(fex.getE01FESCUN());
	} else if (fex.getE01FESTIN().equals("F")) {
		ogen = "Fideicomiso";
	} else if (fex.getE01FESTIN().equals("E")) {
		ogen = "FEM";
	} else if (fex.getE01FESTIN().equals("R")) {
		ogen = "Terceros";
	}
%>
<table class="tableinfo" width="100%">
	<tr>
		<td nowrap>
		<table cellspacing="0" cellpadding="2" width="100%" border="0">
			<tr id="trclear">
				<td nowrap width="15%">
				<div align="right"><b>Contraparte :</b></div>
				</td>
				<td nowrap width="85%">
				<div align="left"><input type="hidden" name="D01FESCP1"
					value="<%=fex.getD01FESCP1()%>"> <%=fex.getD01FESCP1()%>
				</div>
				</td>
			</tr>
			<tr id="trclear">
				<td nowrap width="15%">
				<div align="right"><b>Localizaci&oacute;n :</b></div>
				</td>
				<td nowrap width="85%"><input type="hidden" name="D01FESCP2"
					value="<%=fex.getD01FESCP2()%>" readonly> <%=fex.getD01FESCP2()%></td>
			</tr>
			<tr id="trclear">
				<td nowrap width="15%">
				<div align="right"></div>
				</td>
				<td nowrap width="85%"><input type="hidden" name="D01FESCP3"
					value="<%=fex.getD01FESCP3()%>" readonly> <%=fex.getD01FESCP3()%>
				</td>
			</tr>
			<tr id="trclear">
				<td nowrap width="15%">
				<div align="right"><b>N&uacute;mero de Cuenta :</b></div>
				</td>
				<td nowrap width="85%"><input type="hidden" name="E01FESACC"
					value="<%=fex.getE01FESACC()%>" readonly> <%=fex.getE01FESACC()%></td>
			</tr>
			<tr id="trclear">
				<td nowrap width="15%">
				<div align="right"><b>Orden Generada :</b></div>
				</td>
				<td nowrap width="85%"><input type="hidden" name="E01FESTIN"
					value="<%=fex.getE01FESTIN()%>" readonly> <%=ogen%></td>
			</tr>

		</table>
		</td>
	</tr>
</table>
<h4>Informaci&oacute;n B&aacute;sica <input type="hidden"
	name="SCREEN" value="2"></h4>
<table class="tableinfo" width="545">
	<tr>
		<td nowrap>
		<table cellpadding=2 cellspacing=0 width="100%" border="0">
			<tr id="trclear">
				<td nowrap width="33%">
				<div align="right">Fecha Pacto :</div>
				</td>
				<td nowrap width="21%">
				<div align="left"><%=Util.formatDate(fex.getE01FESDD1(), fex.getE01FESDD2(),
					fex.getE01FESDD3())%>
				- <%=userPO.getHeader8()%></div>
				</td>
				<td nowrap width="23%">
				<div align="right">N&uacute;mero de Referencia :</div>
				</td>
				<td nowrap width="23%"><%=fex.getE01FESREF()%></td>
			</tr>
			<tr id="trclear">
				<td nowrap width="33%" align="right">Tipo de Operaci&oacute;n :
				</td>
				<td nowrap width="21%">
				<%
						if (fex.getE01FESSBT().equals("PU"))
						out.print("Compra");
					else
						out.print("Venta");
				%>
				</td>
				<td nowrap>
				<div align="right">Concepto :</div>
				</td>
				<td nowrap><%=fex.getE01FESBRC()%></td>
			</tr>
			<tr id="trclear">
				<td nowrap>
				<div align="right">Moneda Primaria :</div>
				</td>
				<td nowrap><input type="hidden" name="E01FESCCY"
					value="<%=fex.getE01FESCCY()%>"> <%=fex.getE01FESCCY().trim()%>
				: <%=Util.fcolorCCY(fex.getE01FESAMN())%></td>
				<td nowrap>
				<div align="right">Tasa Spot :</div>
				</td>
				<td nowrap><%=fex.getE01FESEXR()%></td>
			</tr>
			<tr id="trclear">
				<td nowrap>
				<div align="right">Moneda Base :</div>
				</td>
				<td nowrap><%=fex.getE01FESDCY().trim()%> : <%=Util.fcolorCCY(fex.getE01FESDAM())%>
				</td>
				<td nowrap align="right">Fecha Valor :</td>
				<td nowrap><%=Util.formatDate(fex.getE01FESVD1(), fex.getE01FESVD2(),
					fex.getE01FESVD3())%>
				</td>
			</tr>
			<tr id="trclear">
				<td nowrap style="text-align:right">Notas :</td>
				<td nowrap><%=fex.getE01FESOT1().trim()%></td>
				<td style="white-space: nowrap; text-align: right;">Forma de Pago :</td>
				<td style="white-space: nowrap;"><%=paymentType_ES.get(fex.getE01FESBPV())%></td>
			</tr>
			<tr id="trclear">
				<td nowrap>
				<div align="right"></div>
				</td>
				<td nowrap><%=fex.getE01FESOT2().trim()%></td>
				<td style="white-space: nowrap; text-align: right;">Forma de Recepci&oacute;n :</td>
				<td style="white-space: nowrap;"><%=receptionType_ES.get(fex.getE01FESARB())%></td>
			</tr>
			<tr id="trclear">
				<td nowrap>
				<div align="right">Tesorero :</div>
				</td>
				<td nowrap colspan="3"><%=fex.getE01FESDID()%> - <%=fex.getD01FEGDSC().trim()%>
				<input type="hidden" name="E01FESCCY"
					value="<%=fex.getE01FESCCY()%>"></td>
			</tr>
		</table>
		</td>
	</tr>
</table>
<h4>Informaci&oacute;n Adicional</h4>
<table class="tableinfo">
	<tr>
		<td nowrap>
		<table cellpadding=2 cellspacing=0 width="100%" border="0">
			<tr id="trdark">
				<td nowrap>
				<div align="right">Clasificaci&oacute;n :</div>
				</td>
				<td nowrap colspan="3"><input type="text" name="E01FESCLS"
					size="5" maxlength="4" value="<%=fex.getE01FESCLS()%>"> <a
					href="javascript:GetClassFex('E01FESCLS','SPT','<%=fex.getE01FESCCY()%>')"><img
					src="<%=request.getContextPath()%>/images/1b.gif" alt="help"
					align="middle" border="0"></a></td>

				<td nowrap colspan="2">
				<div align="right">Tipo de Reevaluaci&oacute;n :</div>
				</td>
				<td nowrap>
				<%
				String srvf = "";
				String fescot = "";
						try {
						srvf = (fex.getE01FESRVF().trim().length() > 0) ? fex
						.getE01FESRVF() : "N";
						fescot = (fex.getE01FESCOT().trim().length() > 0) ? fex
						.getE01FESCOT() : "N";
					} catch (Exception e) {
						srvf = "N";
						fescot = "N";
					}
				%> <input type="text" name="E01FESRVF" size="3"
					maxlength="1" value="<%=srvf%>">
				<a href="javascript:GetCode('E01FESRVF','STATIC_fe_bo_rev.jsp')"><img
					src="<%=request.getContextPath()%>/images/1b.gif" alt="help"
					align="middle" border="0"></a></td>
				<td nowrap>&nbsp;</td>
			</tr>
			<tr id="trclear">
				<td nowrap>
				<div align="right">Tipo de Confirmaci&oacute;n :</div>
				</td>
				<td nowrap colspan="3"><input type="text" name="E01FESCOT"
					size="3" maxlength="1" value="<%=fescot%>">
				<a href="javascript:GetCode('E01FESCOT','STATIC_fe_bo_not.jsp')"><img
					src="<%=request.getContextPath()%>/images/1b.gif" alt="help"
					align="middle" border="0"></a>
				</td>
				<td nowrap colspan="2" style="text-align:right;">
				</td>
				<td nowrap>
			 </td>
			<td nowrap width="17%">&nbsp;</td>
			</tr>
			<tr id="trdark">
				<td nowrap>
				<div align="center"></div>
				</td>
				<td nowrap > 
              	<h5 align="center">Concepto</h5>
            	</td>
				<td nowrap>
				<h5 align="center">Banco</h5>
				</td>
				<td nowrap>
				<h5 align="center">Sucursal</h5>
				</td>
				<td nowrap>
				<h5 align="center">Moneda</h5>
				</td>
				<td nowrap>
				<h5 align="center">Cuenta Contable</h5>
				</td>
				<td nowrap>
				<h5 align="center">Referencia</h5>
				</td>
				<td nowrap>
				<h5 align="center">Centro de Costo</h5>
				</td>
			</tr>
			<tr id="trclear">
				<td nowrap>
				<div align="right">Cuenta Nostro :</div>
				</td>
				<td nowrap > 
                <div align="center"> 
                  <input type="text" name="E01FESOOP" value="<%= fex.getE01FESOOP()%>" size="2" maxlength="2"> 
                  <input type="text" name="E01FESOCO" size="25" maxlength="25" readonly value="<%= fex.getE01FESOCO()%>" 
                  oncontextmenu="showPopUp2(conceptHelp,this.name,document.forms[0].E01FESOBK.value,'','E01FESOGL','E01FESOOP','10'); return false;">
<!--                    oncontextmenu="showPopUp(conceptHelp,this.name,document.forms[0].E01FESOBK.value,'','E01FESOGL','E01FESOOP','32');">-->
        		</div>
      			</td>
				<td nowrap>
				<div align="center"><input type="text" name="E01FESOBK"
					size="2" maxlength="2" value="<%=fex.getE01FESOBK()%>"></div>
				</td>
				<td nowrap>
				<div align="center"><input type="text" name="E01FESOBR"
					size="3" maxlength="3"
					oncontextmenu="showPopUp(branchHelp,this.name,document.forms[0].E01FESOBK.value,'','','','');"
					value="<%=fex.getE01FESOBR()%>"></div>
				</td>
				<td nowrap>
				<div align="center"><input type="text" name="E01FESOCY"
					size="3" maxlength="3" value="<%=fex.getE01FESOCY()%>"
					oncontextmenu="showPopUp(currencyHelp,this.name,document.forms[0].E01FESOBK.value,'','','','');">
				</div>
				</td>
				<td nowrap>
				<div align="center"><input type="text" name="E01FESOGL"
					size="17" maxlength="16" value="<%=fex.getE01FESOGL()%>"
					oncontextmenu="showPopUp(ledgerHelp,this.name,document.forms[0].E01FESOBK.value,document.forms[0].E01FESOCY.value,'','','');">
				</div>
				</td>
				<td nowrap>
				<div align="center"><input type="text" name="E01FESOAC"
					size="13" maxlength="12" value="<%=fex.getE01FESOAC()%>"
					oncontextmenu="showPopUp(accountHelp,this.name,document.forms[0].E01FESOBK.value,'','','','RT');">
				</div>
				</td>
				<td nowrap>
				<div align="center"><input type="text" name="E01FESOCC"
					size="12" maxlength="9" value="<%=fex.getE01FESOCC()%>"
					oncontextmenu="showPopUp(costcenterHelp,this.name,document.forms[0].E01FESOBK.value,'','','','');">
				</div>
				</td>
			</tr>
			<tr id="trdark">
				<td nowrap>
				<div align="right">Cuenta Vostro :</div>
				</td>
				<td nowrap > 
                <div align="center"> 
                  <input type="text" name="E01FESCOP" value="<%= fex.getE01FESCOP()%>" size="2" maxlength="2">
                  <input type="text" name="E01FESCCO" size="25" maxlength="25" readonly value="<%= fex.getE01FESCCO()%>" 
                  oncontextmenu="showPopUp2(conceptHelp,this.name,document.forms[0].E01FESCBK.value,'','E01FESCGL','E01FESCOP','10'); return false;">                  
<!--                   oncontextmenu="showPopUp(conceptHelp,this.name,document.forms[0].E01FESCBK.value,'','E01FESCGL','E01FESCOP','32');">-->
        		</div>
      			</td>
				<td nowrap>
				<div align="center"><input type="text" name="E01FESCBK"
					size="2" maxlength="2" value="<%=fex.getE01FESCBK()%>"></div>
				</td>
				<td nowrap>
				<div align="center"><input type="text" name="E01FESCBR"
					size="3" maxlength="3" value="<%=fex.getE01FESCBR()%>"
					oncontextmenu="showPopUp(branchHelp,this.name,document.forms[0].E01FESCBK.value,'','','','');">
				</div>
				</td>
				<td nowrap>
				<div align="center"><input type="text" name="E01FESCCU"
					size="3" maxlength="3" value="<%=fex.getE01FESCCU()%>"
					oncontextmenu="showPopUp(currencyHelp,this.name,document.forms[0].E01FESCBK.value,'','','','');">
				</div>
				</td>
				<td nowrap>
				<div align="center"><input type="text" name="E01FESCGL"
					size="17" maxlength="16" value="<%=fex.getE01FESCGL()%>"
					oncontextmenu="showPopUp(ledgerHelp,this.name,document.forms[0].E01FESCBK.value,document.forms[0].E01FESCCU.value,'','','');">
				</div>
				</td>
				<td nowrap>
				<div align="center"><input type="text" name="E01FESCAC"
					size="13" maxlength="12" value="<%=fex.getE01FESCAC()%>"
					oncontextmenu="showPopUp(accountHelp,this.name,document.forms[0].E01FESCBK.value,'','','','RT');">
				</div>
				</td>
				<td nowrap>
				<div align="center"><input type="text" name="E01FESCCC"
					size="12" maxlength="9" value="<%=fex.getE01FESCCC()%>"
					oncontextmenu="showPopUp(costcenterHelp,this.name,document.forms[0].E01FESCBK.value,'','','','');">
				</div>
				</td>
			</tr>
			<tr id="trclear"><td colspan=8></td></tr>
			<tr id="trdark">
				<td nowrap align="center"></td>
				<td nowrap align="center">
				<h5>Tabla</h5>
				</td>
				<td nowrap align="center"></td>
				<td nowrap align="center"></td>
				<td nowrap align="center">
				<h5></h5>
				</td>
				<td nowrap align="center">
				<h5></h5>
				</td>
				<td nowrap align="center">
				<h5>Monto</h5>
				</td>
				<td nowrap align="center"></td>
			</tr>
			<tr id="trclear">
				<td nowrap align="right">Cargos (1) :</td>
				<td nowrap align="center">
					<input type="text" name="E01FESFTP" size="3" maxlength="2" value="<%=fex.getE01FESFTP()%>"> <a
					href="javascript:GetForExcFee('E01FESFTP')">
					<img src="<%=request.getContextPath()%>/images/1b.gif" alt="help" align="middle" border="0"> </a>
				</td>
				<td nowrap align="center"></td>
				<td nowrap align="center"></td>
				<td nowrap align="center">
					<input type="text" name="E01FESC1Y" size="4" maxlength="3" value="<%=fex.getE01FESC1Y()%>"></td>
				<td nowrap align="center">
					<input type="text" name="E01FESC1G" size="17" maxlength="16" value="<%=fex.getE01FESC1G()%>">
				</td>
				<td nowrap align="center">
					<input type="text" name="E01FESC1A" size="16" maxlength="15" value="<%=fex.getE01FESC1A()%>">
				</td>
				<td nowrap align="center"></td>

			</tr>
			<tr id="trdark">
				<td nowrap align="right">Cargos (2) :</td>
				<td nowrap align="center"></td>
				<td nowrap align="center"></td>
				<td nowrap align="center"></td>
				<td nowrap align="center">
					<input type="text" name="E01FESC2Y" size="4" maxlength="3" value="<%=fex.getE01FESC2Y()%>" readonly>
				</td>
				<td nowrap align="center">
					<input type="text" name="E02FESC1G" size="17" maxlength="16" value="<%=fex.getE01FESC2G()%>" readonly>
				</td>
				<td nowrap align="center">
					<input type="text" name="E01FESC2A" size="16" maxlength="15" value="<%=fex.getE01FESC2A()%>" readonly>
				</td>
				<td nowrap align="center"></td>
			</tr>
		</table>
		</td>
	</tr>
</table>
<p><b>L&iacute;mites</b></p>
<table class="tableinfo">
	<tr>
		<td nowrap>
		<table width="100%">
			<tr id="trdark">
				<td nowrap colspan="2">
				<div align="center"></div>
				</td>
				<td nowrap>
				<div align="center"><b>L&iacute;nea de Cr&eacute;dito</b></div>
				</td>
				<td nowrap>
				<div align="center"><b>Actividad Diaria</b></div>
				</td>
				<td nowrap>
				<div align="right">Posici&oacute;n D&iacute;a Anterior :</div>
				</td>
				<td nowrap><%=Util.fcolorCCY(fex.getD01YTDBAL())%></td>
			</tr>
			<tr id="trclear">
				<td nowrap colspan="2">
				<div align="right">Monto L&iacute;mite :</div>
				</td>
				<td nowrap>
				<div align="center"><%=Util.fcolorCCY(fex.getD01LIMAMT())%></div>
				</td>
				<td nowrap>
				<div align="center"><%=Util.fcolorCCY(fex.getD01FEOLIM())%></div>
				</td>
				<td nowrap>
				<div align="right">(+) Compras :</div>
				</td>
				<td nowrap><%=Util.fcolorCCY(fex.getD01TOTPUR())%></td>
			</tr>
			<tr id="trdark">
				<td nowrap colspan="2">
				<div align="right">L&iacute;mite Disponible :</div>
				</td>
				<td nowrap>
				<div align="center"><%=Util.fcolorCCY(fex.getD01LIMAVL())%></div>
				</td>
				<td nowrap>
				<div align="center"><%=Util.fcolorCCY(fex.getD01FEOAVL())%></div>
				</td>
				<td nowrap>
				<div align="right">(-) Ventas :</div>
				</td>
				<td nowrap><%=Util.fcolorCCY(fex.getD01TOTSAL())%></td>
			</tr>
			<tr id="trclear">
				<td nowrap colspan="2">
				<div align="right">Monto L&iacute;mite Final :</div>
				</td>
				<td nowrap>
				<div align="center"><%=Util.fcolorCCY(fex.getD01LIMEND())%></div>
				</td>
				<td nowrap>
				<div align="center"><%=Util.fcolorCCY(fex.getD01FEOEND())%></div>
				</td>
				<td nowrap>
				<div align="right">Disponible :</div>
				</td>
				<td nowrap><%=Util.fcolorCCY(fex.getD01POSBAL())%></td>
			</tr>
		</table>
		</td>
	</tr>
</table>
<font face="Arial"><font face="Arial"><font face="Arial"><font
	face="Arial"><font size="2"> <input type="hidden"
	name="D01FESCP3" value="<%=fex.getD01FESCP3()%>" readonly> </font></font></font></font></font>
<table width="100%" border="0" cellspacing="0" cellpadding="0"
	bgcolor="#FFFFFF" bordercolor="#FFFFFF">
	<tr bgcolor="#FFFFFF">
		<td>
		<div align="center"></div>
		</td>
	</tr>
</table>
<table width="100%" border="0" cellspacing="0" cellpadding="0"
	bgcolor="#FFFFFF" bordercolor="#FFFFFF">
	<tr bgcolor="#FFFFFF">
		<td width="33%">
		<div align="center"><input id="EIBSBTN" type=submit
			name="Submit" value="Enviar"></div>
		</td>
	</tr>
	<tr bgcolor="#FFFFFF">
		<td>
		<div align="center"></div>
		</td>
	</tr>
</table>
<BR>
</form>
</body>
</html>
