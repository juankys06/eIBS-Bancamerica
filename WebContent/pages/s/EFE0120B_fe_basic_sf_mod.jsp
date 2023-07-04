<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<%@ page import="datapro.eibs.master.Util"%>
<%@ page import="datapro.eibs.forex.ST_PaymentType"%>
<%@ page import="datapro.eibs.forex.ST_ReceptionType"%>
<html>
<head>
<META HTTP-EQUIV="Content-Type" CONTENT="text/html; charset=ISO-8859-1">
<META name="GENERATOR" content="IBM WebSphere Studio">
<META http-equiv="Content-Style-Type" content="text/css">
<link Href="<%=request.getContextPath()%>/pages/style.css"
	rel="stylesheet">

<title>Foreign Exchange Module</title>

<jsp:useBean id="fex" class="datapro.eibs.beans.EFE0120DSMessage" scope="session" />
<jsp:useBean id="error" class="datapro.eibs.beans.ELEERRMessage" scope="session" />
<jsp:useBean id="userPO" class="datapro.eibs.beans.UserPos" 	scope="session" />

<script src="<%=request.getContextPath()%>/pages/s/javascripts/eIBSTreasury.jsp"> </SCRIPT>
<script src="<%=request.getContextPath()%>/pages/s/javascripts/eIBS.jsp"> </SCRIPT>
<script src="<%=request.getContextPath()%>/pages/s/javascripts/optMenu.jsp"> </SCRIPT>

<SCRIPT Language="Javascript">

   builtNewMenu(sf_bo_opt);

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
	out.println("<SCRIPT> initMenu();  </SCRIPT>");
%>
<h3 align="center">Spot Moneda Extranjera (Back Office)<img
	src="<%=request.getContextPath()%>/images/e_ibs.gif" align="left"
	name="EIBS_GIF" alt="fe_basic_sf_mod.jsp,EFE0120B"></h3>
<hr size="4">
<form method="post"
	action="<%=request.getContextPath()%>/servlet/datapro.eibs.forex.JSEFE0120B">
<%
	String ogen = "";
	if (fex.getE01FESTIN().equals("T")) {
		ogen = "Tesorería";
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
	name="SCREEN" value="20"></h4>
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
				<td nowrap><%=fex.getE01FESCCY().trim()%> : <%=Util.fcolorCCY(fex.getE01FESAMN())%>
				</td>
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
				<td nowrap>
				<div align="right">Notas :</div>
				</td>
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
			<tr id="trclear">
				<td nowrap>
				<div align="right">Tipo de Pago :</div>
				</td>
				<td nowrap colspan="3">
				<div align="left">
				<%
						if (fex.getE01FESTIN().equals("C"))
						out.print("Especiales");
					else
						out.print("Estándar");
				%>
				</div>
				</td>
			</tr>
		</table>
		</td>
	</tr>
</table>

<h4>Informaci&oacute;n Adicional </h4>
  <table  class="tableinfo" >
    <tr > 
      <td nowrap> 
        <table cellpadding=2 cellspacing=0 width="100%" border="0">
          <tr id="trdark"> 
            <td nowrap > 
              <div align="right">Clasificaci&oacute;n :</div>
            </td>
            <td nowrap colspan="3" > <%= fex.getE01FESCLS()%> </td>
            <td nowrap colspan="2" > 
              <div align="right">Tipo de Reevaluaci&oacute;n :</div>
            </td>
            <td nowrap > <% if(fex.getE01FESRVF().equals("3")) out.print("Acumulado Diario");
				 else if(fex.getE01FESRVF().equals("1")) out.print("Tasa SPOT Diaria");
				 else if(fex.getE01FESRVF().equals("2")) out.print("Tasa Futura");
				 else out.print("No Revalua");%> </td>
            <td nowrap >&nbsp;</td>
          </tr>
          <tr id="trclear"> 
            <td nowrap > 
              <div align="right">Tipo de Confirmaci&oacute;n :</div>
            </td>
            <td nowrap colspan="3" > <% if(fex.getE01FESCOT().equals("P")) out.print("Imprimir");
				 else if(fex.getE01FESCOT().equals("S")) out.print("Via Swift");
				 else if(fex.getE01FESCOT().equals("C")) out.print("Via CLS");
				 else if(fex.getE01FESCOT().equals("T")) out.print("Via Telex");
				 else if(fex.getE01FESCOT().equals("F")) out.print("Via Fax");
				 else out.print("Ninguna");%> </td>
            <td nowrap colspan="2" > 
            </td>
            <td nowrap > 
			 </td>
            <td nowrap width="17%" >&nbsp;</td>
          </tr>

          <tr id="trdark"> 
            <td nowrap > 
              <div align="center"></div>
            </td>
            <td nowrap > 
              <h5 align="center">Concepto</h5>
            </td>
            <td nowrap > 
              <h5 align="center">Banco</h5>
            </td>
            <td nowrap> 
              <h5 align="center">Sucursal</h5>
            </td>
            <td nowrap > 
              <h5 align="center">Moneda</h5>
            </td>
            <td nowrap > 
              <h5 align="center">Cuenta Contable</h5>
            </td>
            <td nowrap > 
              <h5 align="center">Referencia</h5>
            </td>
            <td nowrap > 
              <h5 align="center">Centro de Costo</h5>
            </td>
          </tr>
          <tr id="trclear"> 
            <td nowrap > 
              <div align="right">Cuenta Nostro :</div>
            </td>
            <td nowrap > 
                <div align="center"><%= fex.getE01FESOOP()%> - <%= fex.getE01FESOCO()%></div>
      		</td>
            <td nowrap > 
              <div align="center"> <%= fex.getE01FESOBK()%> </div>
            </td>
            <td nowrap > 
              <div align="center"> <%= fex.getE01FESOBR()%> </div>
            </td>
            <td nowrap > 
              <div align="center"> <%= fex.getE01FESOCY()%> </div>
            </td>
            <td nowrap > 
              <div align="center"> <%= fex.getE01FESOGL()%> </div>
            </td>
            <td nowrap > 
              <div align="center"> <%= fex.getE01FESOAC()%> </div>
            </td>
            <td nowrap > 
              <div align="center"> <%= fex.getE01FESOCC()%> </div>
            </td>
          </tr>
          <tr id="trdark"> 
            <td nowrap > 
              <div align="right">Cuenta Vostro :</div>
            </td>
            <td nowrap > 
                <div align="center"><%= fex.getE01FESCOP()%> - <%= fex.getE01FESCCO()%></div>
      		</td>
            <td nowrap > 
              <div align="center"> <%= fex.getE01FESCBK()%> </div>
            </td>
            <td nowrap > 
              <div align="center"> <%= fex.getE01FESCBR()%> </div>
            </td>
            <td nowrap > 
              <div align="center"> <%= fex.getE01FESCCU()%> </div>
            </td>
            <td nowrap > 
              <div align="center"> <%= fex.getE01FESCGL()%> </div>
            </td>
            <td nowrap > 
              <div align="center"> <%= fex.getE01FESCAC()%> </div>
            </td>
            <td nowrap > 
              <div align="center"> <%= fex.getE01FESCCC()%> </div>
            </td>
          </tr>
          <tr id="trclear">
          	<td colspan="8"></td>
          </tr>
		  <tr id="trdark">
			<td nowrap align="center"></td>
			<td nowrap align="center">
				<h5>Tabla</h5>
			</td>
			<td nowrap align="center"></td>
			<td nowrap align="center"></td>
			<td nowrap align="center">
				<h5> </h5>
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
				<td nowrap align="center"><%=fex.getE01FESFTP()%></td>
				<td nowrap align="center"></td>
				<td nowrap align="center"></td>
				<td nowrap align="center"><%=fex.getE01FESC1Y()%></td>
				<td nowrap align="center"><%=fex.getE01FESC1G()%></td>
				<td nowrap align="center"><%=fex.getE01FESC1A()%></td>
				<td nowrap align="center"></td>
			</tr>
			<tr id="trdark">
				<td nowrap align="right">Cargos (2) :</td>
				<td nowrap align="center"></td>
				<td nowrap align="center"></td>
				<td nowrap align="center"></td>
				<td nowrap align="center"><%=fex.getE01FESC2Y()%></td>
				<td nowrap align="center"><%=fex.getE01FESC2G()%></td>
				<td nowrap align="center"><%=fex.getE01FESC2A()%></td>
				<td nowrap align="center"></td>
			</tr>
        </table>
      </td>
    </tr>
  </table>
<p><b>L&iacute;imites</b></p>
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
				<div align="right">Posici&oacute;n D&iacute;a Anterior:</div>
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
	face="Arial"><font size="2"> </font></font></font></font></font>
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

</form>
</body>
</html>
