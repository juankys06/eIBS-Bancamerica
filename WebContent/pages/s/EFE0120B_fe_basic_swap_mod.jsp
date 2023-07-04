<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<%@ page import = "datapro.eibs.master.Util" %>
<html>
<head>
<META HTTP-EQUIV="Content-Type" CONTENT="text/html; charset=ISO-8859-1">
<META name="GENERATOR" content="IBM WebSphere Studio">
<META http-equiv="Content-Style-Type" content="text/css">
<link Href="<%=request.getContextPath()%>/pages/style.css" rel="stylesheet">

<title>Foreign Exchange Module</title>

<jsp:useBean id="fex" class="datapro.eibs.beans.EFE0120DSMessage"  scope="session" />
<jsp:useBean id= "error" class= "datapro.eibs.beans.ELEERRMessage"  scope="session" />
<jsp:useBean id= "userPO" class= "datapro.eibs.beans.UserPos"  scope="session" />

<script src="<%=request.getContextPath()%>/pages/s/javascripts/eIBSTreasury.jsp"> </SCRIPT>
<script src="<%=request.getContextPath()%>/pages/s/javascripts/eIBS.jsp"> </SCRIPT>
<script src="<%=request.getContextPath()%>/pages/s/javascripts/optMenu.jsp"> </SCRIPT>


<SCRIPT Language="Javascript">

   builtNewMenu(sw_bo_opt);

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
 out.println("<SCRIPT> initMenu();  </SCRIPT>");
%>
<h3 align="center"> Swap Moneda Extranjera (Back Office) </h3>
<hr size="4">
<form method="post" action="<%=request.getContextPath()%>/servlet/datapro.eibs.forex.JSEFE0120B" >
<%
String ogen = "";
if (fex.getE01FESTIN().equals("T")) {
	ogen = "Tesorería";
} else if (fex.getE01FESTIN().equals("F")) {
	ogen = "Fideicomiso";
}  else if (fex.getE01FESTIN().equals("E")) {
	ogen = "FEM";
}  else if (fex.getE01FESTIN().equals("R")) {
	ogen = "Terceros";
}
%>
  <table class="tableinfo" width="100%" >
    <tr > 
      <td nowrap> 
        <table cellspacing="0" cellpadding="2" width="100%" border="0" >
          <tr id="trclear"> 
            <td nowrap width="15%" > 
              <div align="right"><b>Contrapartida :</b></div>
            </td>
            <td nowrap width="85%" > 
              <div align="left"> 
                <input type="hidden" name="D01FESCP1"  value="<%= fex.getD01FESCP1()%>" >
                <%= fex.getD01FESCP1()%> </div>
            </td>
          </tr>
          <tr id="trclear"> 
            <td nowrap width="15%" > 
              <div align="right"><b>Localizaci&oacute;n :</b></div>
            </td>
            <td nowrap width="85%" > 
              <input type="hidden" name="D01FESCP2"  value="<%= fex.getD01FESCP2()%>" readonly>
              <%= fex.getD01FESCP2()%></td>
          </tr>
          <tr id="trclear"> 
            <td nowrap width="15%" > 
              <div align="right"></div>
            </td>
            <td nowrap  width="85%" >
              <input type="hidden" name="D01FESCP3"  value="<%= fex.getD01FESCP3()%>" readonly>
              <%= fex.getD01FESCP3()%> </td>
          </tr>
          <tr id="trclear"> 
            <td nowrap width="15%" > 
              <div align="right"><b>N&uacute;mero de Cuenta :</b></div>
            </td>
            <td nowrap width="85%" >
              <input type="hidden" name="E01FESACC"  value="<%= fex.getE01FESACC()%>" readonly>
              <%= fex.getE01FESACC()%></td>
          </tr>
          <tr id="trclear"> 
            <td nowrap width="15%" > 
              <div align="right"><b>Orden Generada :</b></div>
            </td>
            <td nowrap colspan="3" width="85%" ><font face="Arial"><font face="Arial"><font face="Arial"><font face="Arial"><font size="2"> 
              <input type="hidden" name="E01FESTIN"  value="<%= fex.getE01FESTIN()%>" readonly>
              </font></font></font></font></font><%= ogen%></td>
          </tr>
          
        </table>
      </td>
    </tr>
  </table>
  <h4>Informaci&oacute;n<b></b> B&aacute;sica <b> 
    <input type="hidden" name="SCREEN"  value="28" >
    </b></h4>
  <table  class="tableinfo" width="545">
    <tr > 
      <td nowrap> 
        <table cellpadding=2 cellspacing=0 width="100%" border="0">
          <tr id="trclear"> 
            <td nowrap > 
              <div align="right">N&uacute;mero de Referencia :</div>
            </td>
            <td nowrap ><%= fex.getE01FESREF()%></td>
            <td nowrap >&nbsp;</td>
            <td nowrap > 
              <div align="right">Fecha :<%= Util.formatDate(fex.getE01FESDD1(),fex.getE01FESDD2(),fex.getE01FESDD3())%></div>
            </td>
          </tr>

          <tr id="trclear"> 
            <td nowrap align="right"> Operaci&oacute;n Spot : </td>
            <td><% if(fex.getE01FESSBT().equals("PU")) out.print("Compra");
						else out.print("Venta");%> 
			</td>
			<td nowrap><div align="right">Concepto : </div></td>
            <td nowrap > <%=fex.getE01FESBRC()%></td>
          </tr>
          <tr id="trclear"> 
            <td nowrap width="18%" > 
              <div align="right">Moneda Primaria :</div>
            </td>
            <td nowrap width="31%" > <%= fex.getE01FESCCY().trim()%> : <%= Util.fcolorCCY(fex.getE01FESAMN())%> 
              <input type="hidden" name="E01FESCCY"  value="<%= fex.getE01FESCCY()%>" >
            </td>
            <td nowrap width="20%" > 
              <div align="right">Tasa Spot :</div>
            </td>
            <td nowrap width="31%"> <%= fex.getE01FESEXR()%> </td>
          </tr>
          <tr id="trclear"> 
            <td nowrap width="18%" > 
              <div align="right">Moneda Base :</div>
            </td>
            <td nowrap width="31%" ><%= fex.getE01FESDCY().trim()%> : <%= Util.fcolorCCY(fex.getE01FESDAM())%> 
            </td>
            <td nowrap align="right" width="20%">Fecha Spot :</td>
            <td nowrap width="31%"> <%= Util.formatDate(fex.getE01FESVD1(),fex.getE01FESVD2(),fex.getE01FESVD3())%> 
            </td>
          </tr>
          <tr id="trclear"> 
            <td nowrap align="right">Operaci&oacute;n Forward : </td>
            <td>    <% if(fex.getE01FESSBT().equals("PU")) out.print("Venta");
						else out.print("Compra");%>
            </td>
             <td nowrap><div align="right">Concepto : </div></td>
            <td nowrap > <%=fex.getE01FESBRC()%></td>
          </tr>
          <tr id="trclear"> 
            <td nowrap width="18%" > 
              <div align="right">Forward Monto Mda. Primaria :</div>
            </td>
            <td nowrap width="31%" ><%= fex.getE01FESCCY().trim()%> : <%= Util.fcolorCCY(fex.getE01FESAM1())%> 
            </td>
            <td nowrap align="right" width="20%"> 
              <div align="right">Tasa Forward :</div>
            </td>
            <td nowrap width="31%"><%= fex.getE01FESBRF()%> </td>
          </tr>
          <tr id="trclear"> 
            <td nowrap width="18%" > 
              <div align="right">Forward Monto Mda. Base :</div>
            </td>
            <td nowrap ><%= fex.getE01FESDCY().trim()%> : <%= Util.fcolorCCY(fex.getE01FESAM2())%></td>
            <td nowrap > 
              <div align="right">Fecha Forward :</div>
            </td>
            <td nowrap ><%= Util.formatDate(fex.getE01FESMA1(),fex.getE01FESMA2(),fex.getE01FESMA3())%></td>
          </tr>
          <tr id="trclear"> 
            <td nowrap width="18%" > 
              <div align="right">Notas :</div>
            </td>
            <td nowrap colspan="3" ><%= fex.getE01FESOT1()%></td>
          </tr>
          <tr id="trclear"> 
            <td nowrap width="18%" >&nbsp;</td>
            <td nowrap colspan="3" ><%= fex.getE01FESOT2()%></td>
          </tr>
          <tr id="trclear"> 
            <td nowrap width="18%" > 
              <div align="right">Tesorero :</div>
            </td>
            <td nowrap colspan="3" ><%= fex.getE01FESDID()%> - <%= fex.getD01FEGDSC()%></td>
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
              <div align="right">Clasificaci&oacute;n Spot :</div>
            </td>
            <td nowrap colspan="2" > <%= fex.getE01FESCLS()%> </td>
            <td nowrap colspan="2" > 
              <div align="right">Clasificaci&oacute;n Forward :</div>
            </td>
            <td nowrap ><%= fex.getE01FESFLS()%> </td>
            <td nowrap >&nbsp;</td>
          </tr>
          <tr id="trclear"> 
            <td nowrap > 
              <div align="right">Tipo de Reevaluaci&oacute;n :</div>
            </td>
            <td nowrap colspan="2" > 
              <div align="left"><% if(fex.getE01FESRVF().equals("3")) out.print("Acumulado Diario");
				 else if(fex.getE01FESRVF().equals("1")) out.print("Tasa SPOT Diaria");
				 else if(fex.getE01FESRVF().equals("2")) out.print("Tasa Futurra");
				 else out.print("No Revalua");%></div>
            </td>
            <td nowrap colspan="2" > 
              <div align="right">Tipo de Confirmaci&oacute;n :</div>
            </td>
            <td nowrap ><% if(fex.getE01FESCOT().equals("P")) out.print("Imprimir");
				 else if(fex.getE01FESCOT().equals("S")) out.print("Via Swift");
				 else if(fex.getE01FESCOT().equals("H")) out.print("Via Chip");
				 else if(fex.getE01FESCOT().equals("T")) out.print("Via Telex");
				 else if(fex.getE01FESCOT().equals("F")) out.print("Via Fax");
				 else out.print("Ninguna");%></td>
            <td nowrap width="17%" >&nbsp;</td>
          </tr>
          <tr id="trdark"> 
            <td nowrap > 
              <div align="right">Spot Forma de Pago :</div>
            </td>
            <td nowrap colspan="2" > <% if(fex.getE01FESBPV().equals("F")) out.print("Via Fed");
				 else if(fex.getE01FESBPV().equals("1")) out.print("Via Swift MT-103");
				 else if(fex.getE01FESBPV().equals("2")) out.print("Via Swift MT-200");
				 else if(fex.getE01FESBPV().equals("3")) out.print("Via Swift MT-202");
				 else if(fex.getE01FESBPV().equals("T")) out.print("Via Telex");
				 else out.print("Ninguna");%> </td>
            <td nowrap colspan="2" > 
              <div align="right">Forward Forma de Pago :</div>
            </td>
            <td nowrap > 
              <div align="left"><% if(fex.getE01FESBCT().equals("F")) out.print("Via Fed");
				 else if(fex.getE01FESBCT().equals("1")) out.print("Via Swift MT-103");
				 else if(fex.getE01FESBCT().equals("2")) out.print("Via Swift MT-200");
				 else if(fex.getE01FESBCT().equals("3")) out.print("Via Swift MT-202");
				 else if(fex.getE01FESBCT().equals("T")) out.print("Via Telex");
				 else out.print("Ninguna");%></div>
            </td>
          </tr>
          <tr id="trclear"> 
            <td nowrap > 
              <div align="center"></div>
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
          <tr id="trdark"> 
            <td nowrap > 
              <div align="right">Cuenta Nostro :</div>
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
          <tr id="trclear"> 
            <td nowrap > 
              <div align="right">Spot Cuenta Vostro :</div>
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
          <tr id="trdark"> 
            <td nowrap > 
              <div align="right"> Forward Cuenta Vostro :</div>
            </td>
            <td nowrap > 
              <div align="center"><%= fex.getE01FESFBK()%></div>
            </td>
            <td nowrap > 
              <div align="center"><%= fex.getE01FESFBR()%></div>
            </td>
            <td nowrap > 
              <div align="center"><%= fex.getE01FESFCU()%></div>
            </td>
            <td nowrap > 
              <div align="center"><%= fex.getE01FESFGL()%></div>
            </td>
            <td nowrap > 
              <div align="center"><%= fex.getE01FESFAC()%></div>
            </td>
            <td nowrap > 
              <div align="center"><%= fex.getE01FESFCC()%></div>
            </td>
          </tr>
        </table>
      </td>
    </tr>
  </table>
  <p><b>L&iacute;mites</b></p>
  <table  class="tableinfo" >
    <tr > 
      <td nowrap> 
        <table width="100%">
          <tr id="trdark"> 
            <td nowrap  colspan="2"> 
              <div align="center"></div>
            </td>
            <td nowrap > 
              <div align="center"><b>L&iacute;nea de Cr&eacute;dito</b></div>
            </td>
            <td nowrap > 
              <div align="center"><b>Actividad Diaria</b></div>
            </td>
            <td nowrap > 
              <div align="right">Posici&oacute;n D&iacute;a Anterior :</div>
            </td>
            <td nowrap ><%= Util.fcolorCCY(fex.getD01YTDBAL())%> </td>
          </tr>
          <tr id="trclear"> 
            <td nowrap  colspan="2"> 
              <div align="right">Monto L&iacute;mite :</div>
            </td>
            <td nowrap > 
              <div align="center"><%= Util.fcolorCCY(fex.getD01LIMAMT())%></div>
            </td>
            <td nowrap > 
              <div align="center"><%= Util.fcolorCCY(fex.getD01FEOLIM())%></div>
            </td>
            <td nowrap > 
              <div align="right">(+) Compras :</div>
            </td>
            <td nowrap ><%= Util.fcolorCCY(fex.getD01TOTPUR())%> </td>
          </tr>
          <tr id="trdark"> 
            <td nowrap colspan="2"> 
              <div align="right">L&iacute;mite Disponible :</div>
            </td>
            <td nowrap > 
              <div align="center"> <%= Util.fcolorCCY(fex.getD01LIMAVL())%> </div>
            </td>
            <td nowrap > 
              <div align="center"> <%= Util.fcolorCCY(fex.getD01FEOAVL())%> </div>
            </td>
            <td nowrap > 
              <div align="right">(-) Ventas :</div>
            </td>
            <td nowrap ><%= Util.fcolorCCY(fex.getD01TOTSAL())%> </td>
          </tr>
          <tr id="trclear"> 
            <td nowrap  colspan="2"> 
              <div align="right">Monto L&iacute;mite Final :</div>
            </td>
            <td nowrap > 
              <div align="center"> <%= Util.fcolorCCY(fex.getD01LIMEND())%> </div>
            </td>
            <td nowrap > 
              <div align="center"> <%= Util.fcolorCCY(fex.getD01FEOEND())%> </div>
            </td>
            <td nowrap > 
              <div align="right">Disponible :</div>
            </td>
            <td nowrap ><%= Util.fcolorCCY(fex.getD01POSBAL())%> </td>
          </tr>
        </table>
      </td>
    </tr>
  </table>
  <table width="100%" border="0" cellspacing="0" cellpadding="0" bgcolor="#FFFFFF" bordercolor="#FFFFFF">
    <tr bgcolor="#FFFFFF"> 
      <td> 
        <div align="center"> </div>
      </td>
    </tr>
  </table>
  <table width="100%" border="0" cellspacing="0" cellpadding="0" bgcolor="#FFFFFF" bordercolor="#FFFFFF">
    <tr bgcolor="#FFFFFF"> 
      <td width="33%"> 
  <div align="center"> 
	      <input id="EIBSBTN" type=submit name="Submit" value="Enviar">
  </div>      </td>
    </tr>
    <tr bgcolor="#FFFFFF"> 
      <td> 
        <div align="center"> </div>
      </td>
    </tr>
  </table>
 
  </form>
</body>
</html>
