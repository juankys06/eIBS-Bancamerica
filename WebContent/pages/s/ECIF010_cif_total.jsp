<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.0 Transitional//EN"> 
<html>
<head>
<title>Resumen de Operaciones por Cliente</title>
<META HTTP-EQUIV="Content-Type" CONTENT="text/html; charset=ISO-8859-1">
<META name="GENERATOR" content="IBM WebSphere Page Designer V3.5.2 for Windows">
<META http-equiv="Content-Style-Type" content="text/css">
<link Href="<%=request.getContextPath()%>/pages/style.css" rel="stylesheet">

<jsp:useBean id="cifTotal" class="datapro.eibs.beans.ECIF01003Message" scope="session" />

<jsp:useBean id= "error" class= "datapro.eibs.beans.ELEERRMessage"  scope="session" />

<script language="Javascript1.1" src="<%=request.getContextPath()%>/pages/s/javascripts/eIBS.jsp"> </SCRIPT>
<script language="Javascript1.1" src="<%=request.getContextPath()%>/pages/s/javascripts/optMenu.jsp"> </SCRIPT>

<SCRIPT Language="Javascript">

function showGraph()
{
	var pg= "<%=request.getContextPath()%>/pages/s/ECIF010_cif_total_graph.jsp";
	CenterNamedWindow(pg,'graph',700,560,2);
}

builtNewMenu(ecif10_i_opt);

</SCRIPT>


</head>

<body>

<%@ page import = "datapro.eibs.master.Util" %>

 <% 
 if ( !error.getERRNUM().equals("0")  ) {
     out.println("<SCRIPT Language=\"Javascript\">");
     error.setERRNUM("0");
     out.println("       showErrors()");
     out.println("</SCRIPT>");
 }
 out.println("<SCRIPT> initMenu(); </SCRIPT>");
%>

<h3 align="center">Resumen de Operaciones por Cliente<img src="<%=request.getContextPath()%>/images/e_ibs.gif" align="left" name="EIBS_GIF" ALT="cif_total.jsp, ECIF010"></h3>
<hr size="4">
<form method="post" action="<%=request.getContextPath()%>/servlet/datapro.eibs.products.JSECIF010" >
  <INPUT TYPE=HIDDEN NAME="SCREEN" value="6">
  <h4>Informaci&oacute;n del Cliente</h4>
 <table class="tableinfo">
    <tr bordercolor="#FFFFFF"> 
      <td nowrap>
        <table cellspacing=0 cellpadding=2 width="100%" border="0">
          <tr id="trdark"> 
            <td nowrap width="22%" height="31"> 
              <div align="right">Cliente :</div>
            </td>
            <td nowrap width="18%" height="31"> 
              <input type="text" readonly name="E03CUSCUN"  size="11" maxlength="9" value="<%= cifTotal.getE03CUSCUN().trim()%>">
            </td>
            <td nowrap width="21%" height="31"> 
              <div align="right">Nombre :</div>
            </td>
            <td nowrap width="39%" height="31"> 
              <input type="text" readonly name="E03CUSNA1"  size="35" maxlength="45" value="<%= cifTotal.getE03CUSNA1().trim()%>">
            </td>
          </tr>
          <tr id="trclear"> 
            <td nowrap width="22%"> 
              <div align="right">Tel&eacute;fono Residencia :</div>
            </td>
            <td nowrap width="18%"> 
              <input type="text" readonly name="E03CUSHPN" size="13" maxlength="11" value="<%= cifTotal.getE03CUSHPN().trim()%>">
            </td>
            <td nowrap width="21%"> 
              <div align="right">Tel&eacute;fono Trabajo :</div>
            </td>
            <td nowrap width="39%"> 
              <input type="text" readonly name="E03CUSPHN" size="13" maxlength="11" value="<%= cifTotal.getE03CUSPHN().trim()%>">
            </td>
          </tr>
          <tr id="trdark"> 
            <td nowrap width="22%"> 
              <div align="right">Teléfono Celular :</div>
            </td>
            <td nowrap width="18%"> 
              <input type="text" readonly name="E03CUSPH1" size="13" maxlength="11" value="<%= cifTotal.getE03CUSPH1().trim()%>">
            </td>
            <td nowrap width="22%"> 
              <div align="right">Facsimil :</div>
            </td>
            <td nowrap width="18%"> 
              <input type="text" readonly name="E03CUSFAX" size="13" maxlength="11" value="<%= cifTotal.getE03CUSFAX().trim()%>">
            </td>
          </tr>
          <tr id="trclear"> 
            <td nowrap width="21%"> 
              <div align="right">Correo Electronico :</div>
            </td>
            <td nowrap width="39%"><a href="mailto:<%= cifTotal.getE03CUSIAD().trim()%>" target="body"><%= cifTotal.getE03CUSIAD().trim()%></a> 
            </td>
            <td nowrap width="21%">
            </td>
            <td nowrap width="39%"> 
            </td>
          </tr>          
        </table>
      </td>
    </tr>
  </table>
  <h4>Operaciones en Libro</h4>
  <table  id="tbhelp">
    <tr> 
      <td width="12%" nowrap>Ver Gr&aacute;fica</td>
      <td width="88%"><a href="javascript:showGraph()"><img src="<%=request.getContextPath()%>/images/graphic.gif" border="0" width="32" height="32"></a></td>
    </tr>
  </table>
  <table class="tableinfo">
    <tr bordercolor="#FFFFFF"> 
      <td nowrap height="36"> 
        <table cellspacing=0 cellpadding=2 width="100%" border="0">
          <tr id="trdark"> 
            <td nowrap height="31" bordercolor="#000000" colspan=2> 
              <div align="center"><b>A Favor del Banco</b></div>
            </td>
            <td nowrap height="31" bordercolor="#000000" colspan=2> 
              <div align="center"><b>A Favor del Cliente</b></div>
            </td>
          </tr>
          <tr id="trclear"> 
            <td nowrap width="28%" bordercolor="#000000" height="31"> 
              <div align="right"><a href="javascript:showAcc('OV','')">Cuentas 
                Sobregiradas : </a> </div>
            </td>
            <td nowrap width="21%" bordercolor="#000000" height="31"> 
              <div align="center"> 
                <input type="text" id="txtright" readonly name="E03OVDRFT" size="17" maxlength="15" value="<%= Util.formatCCY(cifTotal.getE03OVDRFT())%>" onDblClick="showAcc('OV','')">
              </div>
            </td>
            <td nowrap width="25%" bordercolor="#000000" height="31"> 
              <div align="right"><a href="javascript:showAcc('01','')">Cuentas 
                sin Interes :</a> </div>
            </td>
            <td nowrap width="26%" height="31" bordercolor="#000000" > 
              <div align="center">
                <input type="text" id="txtright" readonly name="E03CTACTE" size="17" maxlength="15" value="<%= Util.formatCCY(cifTotal.getE03CTACTE())%>" onDblClick="showAcc('01','')">
              </div>
            </td>
          </tr>
          <tr id="trdark"> 
            <td nowrap width="28%" bordercolor="#000000"> 
              <div align="right"><a href="javascript:showAcc('10','H')">Prestamos 
                Hipotecarios :</a> </div>
            </td>
            <td nowrap width="21%" bordercolor="#000000"> 
              <div align="center"> 
                <input type="text" id="txtright" readonly name="E03LNSMOR" size="17" maxlength="15" value="<%= Util.formatCCY(cifTotal.getE03LNSMOR())%>" onDblClick="showAcc('10','H')">
              </div>
            </td>
            <td nowrap width="25%" bordercolor="#000000"> 
              <div align="right"><a href="javascript:showAcc('MK','')">Cuentas 
                con Interes:</a> </div>
            </td>
            <td nowrap width="26%" height="22" bordercolor="#000000" > 
              <div align="center">
                <input type="text" id="txtright" readonly name="E03CTAMMK" size="17" maxlength="15" value="<%= Util.formatCCY(cifTotal.getE03CTAMMK())%>" onDblClick="showAcc('MK','')">
              </div>
            </td>
          </tr>
          <tr id="trclear"> 
            <td nowrap width="28%" bordercolor="#000000" height="35"> 
              <div align="right"><a href="javascript:showAcc('10','A')">Arrendamiento 
                Financiero :</a> </div>
            </td>
            <td nowrap width="21%" bordercolor="#000000" height="35"> 
              <div align="center"> 
                <input type="text" id="txtright" readonly name="E03LNSLSG" size="17" maxlength="15" value="<%= Util.formatCCY(cifTotal.getE03LNSLSG())%>" onDblClick="showAcc('10','A')">
              </div>
            </td>
            <td nowrap width="25%" bordercolor="#000000" height="35"> 
              <div align="right"><a href="javascript:showAcc('04','')">Cuentas 
                de Ahorro :</a> </div>
            </td>
            <td nowrap width="26%" height="35" bordercolor="#000000" > 
              <div align="center"> 
                <input type="text" id="txtright" readonly name="E03CTAAHO" size="17" maxlength="15" value="<%= Util.formatCCY(cifTotal.getE03CTAAHO())%>" onDblClick="showAcc('04','')">
              </div>
            </td>
          </tr>
          <tr id="trdark"> 
            <td nowrap width="28%" bordercolor="#000000"> 
              <div align="right"><a href="javascript:showAcc('10','C')">Cr&eacute;dito 
                de Consumo :</a></div>
            </td>
            <td nowrap width="21%" bordercolor="#000000"> 
              <div align="center"> 
                <input type="text" id="txtright" readonly name="E03LNSCON" size="17" maxlength="15" value="<%= Util.formatCCY(cifTotal.getE03LNSCON())%>" onDblClick="showAcc('10','C')">
              </div>
            </td>
            <td nowrap width="25%" bordercolor="#000000"> 
              <div align="right"><a href="javascript:showAcc('CD','')">Certificados 
                :</a> </div>
            </td>
            <td nowrap width="26%" height="22" bordercolor="#000000" > 
              <div align="center"> 
                <input type="text" id="txtright" readonly name="E03CDTDPO" size="17" maxlength="15" value="<%= Util.formatCCY(cifTotal.getE03CDTDPO())%>" onDblClick="showAcc('CD','')">
              </div>
            </td>
          </tr>
          <tr id="trclear"> 
            <td nowrap width="28%" bordercolor="#000000"> 
              <div align="right"><a href="javascript:showAcc('10','X')">Prest&aacute;mos 
                :</a></div>
            </td>
            <td nowrap width="21%" bordercolor="#000000"> 
              <div align="center"> 
                <input type="text" id="txtright" readonly name="E03LNSGRL" size="17" maxlength="15" value="<%= Util.formatCCY(cifTotal.getE03LNSGRL())%>" onDblClick="showAcc('10','X')">
              </div>
            </td>
            <td nowrap width="25%" bordercolor="#000000"> 
              <div align="right"><a href="javascript:showAcc('13','L')">Inversiones 
                :</a></div>
            </td>
            <td nowrap width="26%" height="22" bordercolor="#000000" > 
              <div align="center"> 
                <input type="text" id="txtright" readonly name="E03INVERP" size="17" maxlength="15" value="<%= Util.formatCCY(cifTotal.getE03INVERP())%>" onDblClick="showAcc('13','L')">
              </div>
            </td>
          </tr>
          <tr id="trdark"> 
            <td nowrap width="28%" bordercolor="#000000"> 
              <div align="right"><a href="javascript:showAcc('10','G')">Facturas 
                Descontadas :</a></div>
            </td>
            <td nowrap width="21%" bordercolor="#000000"> 
              <div align="center"> 
                <input type="text" id="txtright" readonly name="E03DESDOC" size="17" maxlength="15" value="<%= Util.formatCCY(cifTotal.getE03DESDOC())%>" onDblClick="showAcc('10','G')">
              </div>
            </td>
            <td nowrap width="25%" bordercolor="#000000"> 
              <div align="right"><a href="javascript:showAcc('10','P')">Participaciones 
                :</a> </div>
            </td>
            <td nowrap width="26%" height="22" bordercolor="#000000" > 
              <div align="center"> 
                <input type="text" id="txtright" readonly name="E03PARTIC" size="17" maxlength="15" value="<%= Util.formatCCY(cifTotal.getE03PARTIC())%>" onDblClick="showAcc('10','P')">
              </div>
            </td>
          </tr>
          <tr id="trclear"> 
            <td nowrap width="28%" bordercolor="#000000"> 
              <div align="right"><a href="javascript:showAcc('13','A')">Inversiones 
                :</a> </div>
            </td>
            <td nowrap width="21%" bordercolor="#000000"> 
              <div align="center"> 
                <input type="text" id="txtright" readonly name="E03INVERA" size="17" maxlength="15" value="<%= Util.formatCCY(cifTotal.getE03INVERA())%>" onDblClick="showAcc('13','A')">
              </div>
            </td>
            <td nowrap width="25%" bordercolor="#000000"> 
              <div align="right"><a href="javascript:showAcc('14','L')">Aceptaciones 
                :</a> </div>
            </td>
            <td nowrap width="26%" height="22" bordercolor="#000000" > 
              <div align="center"> 
                <input type="text" id="txtright" readonly name="E03ACEPTP" size="17" maxlength="15" value="<%= Util.formatCCY(cifTotal.getE03ACEPTP())%>"  onDblClick="showAcc('14','L')">
              </div>
            </td>
          </tr>
          <tr id="trdark"> 
            <td nowrap width="28%" bordercolor="#000000"> 
              <div align="right"><a href="javascript:showAcc('14','A')">Aceptaciones 
                :</a></div>
            </td>
            <td nowrap width="21%" bordercolor="#000000"> 
              <div align="center"> 
                <input type="text" id="txtright" readonly name="E03ACEPTA" size="17" maxlength="15" value="<%= Util.formatCCY(cifTotal.getE03ACEPTA())%>" onDblClick="showAcc('14','A')">
              </div>
            </td>
            <td nowrap width="25%" bordercolor="#000000"> 
              <div align="right"><a href="javascript:showAcc('LC','P')">C. de 
                C. en Proceso :</a> </div>
            </td>
            <td nowrap width="26%" height="22" bordercolor="#000000" > 
              <div align="center"> 
                <input type="text" id="txtright" readonly name="E03LCPROC" size="17" maxlength="15" value="<%= Util.formatCCY(cifTotal.getE03LCPROC())%>"  onDblClick="showAcc('LC','P')">
              </div>
            </td>
          </tr>
          <tr id="trclear"> 
            <td nowrap width="28%" bordercolor="#000000"> 
              <div align="right"><a href="javascript:showAcc('LC','C')">C. de 
                C. Confirmadas :</a></div>
            </td>
            <td nowrap width="21%" bordercolor="#000000"> 
              <div align="center"> 
                <input type="text" id="txtright" readonly name="E03LCCONF" size="17" maxlength="15" value="<%= Util.formatCCY(cifTotal.getE03LCCONF())%>" onDblClick="showAcc('LC','C')">
              </div>
            </td>
            <td nowrap width="25%" bordercolor="#000000"> 
              <div align="right"><a href="javascript:showAcc('31','')">Spot Vendidos 
                :</a></div>
            </td>
            <td nowrap width="26%" height="22" bordercolor="#000000" > 
              <div align="center"> 
                <input type="text" id="txtright" readonly name="E03SPTSAL" size="17" maxlength="15" value="<%= Util.formatCCY(cifTotal.getE03SPTSAL())%>" onDblClick="showAcc('31','')">
              </div>
            </td>
          </tr>
          <tr id="trdark"> 
            <td nowrap width="28%" bordercolor="#000000"> 
              <div align="right"><a href="javascript:showAcc('30','')">Spot Comprados 
                :</a> </div>
            </td>
            <td nowrap width="21%" bordercolor="#000000"> 
              <div align="center"> 
                <input type="text" id="txtright" readonly name="E03SPTPUR" size="17" maxlength="15" value="<%= Util.formatCCY(cifTotal.getE03SPTPUR())%>" onDblClick="showAcc('30','')">
              </div>
            </td>
            <td nowrap width="25%" bordercolor="#000000"> 
              <div align="right"><a href="javascript:showAcc('33','')">Forward 
                Vendidos :</a> </div>
            </td>
            <td nowrap width="26%" height="22" bordercolor="#000000" > 
              <div align="center"> 
                <input type="text" id="txtright" readonly name="E03FRWSAL" size="17" maxlength="15" value="<%= Util.formatCCY(cifTotal.getE03FRWSAL())%>"  onDblClick="showAcc('33','')">
              </div>
            </td>
          </tr>
          <tr id="trclear"> 
            <td nowrap width="28%" bordercolor="#000000"> 
              <div align="right"><a href="javascript:showAcc('32','')">Forward 
                Comprados :</a></div>
            </td>
            <td nowrap width="21%" bordercolor="#000000"> 
              <div align="center"> 
                <input type="text" id="txtright" readonly name="E03FRWPUR" size="17" maxlength="15" value="<%= Util.formatCCY(cifTotal.getE03FRWPUR())%>" onDblClick="showAcc('32','')">
              </div>
            </td>
            <td nowrap width="25%" bordercolor="#000000">&nbsp;</td>
            <td nowrap width="26%" height="22" bordercolor="#000000" > 
              <div align="center"></div>
            </td>
          </tr>
          <tr id="trdark"> 
            <td nowrap width="28%" bordercolor="#000000">&nbsp;</td>
            <td nowrap width="21%" bordercolor="#000000"> 
              <div align="center"></div>
            </td>
            <td nowrap width="25%" bordercolor="#000000">&nbsp;</td>
            <td nowrap width="26%" height="22" bordercolor="#000000" > 
              <div align="center"></div>
            </td>
          </tr>
          <tr id="trclear"> 
            <td nowrap width="28%" bordercolor="#000000"> 
              <div align="right"><b>Total Activo :</b></div>
            </td>
            <td nowrap width="21%" bordercolor="#000000"> 
              <div align="center"> 
                <input type="text" id="txtright" readonly name="E03TOTASS" size="17" maxlength="15" value="<%= Util.formatCCY(cifTotal.getE03TOTASS())%>">
              </div>
            </td>
            <td nowrap width="25%" bordercolor="#000000"> 
              <div align="right"><b>Total Pasivo :</b></div>
            </td>
            <td nowrap width="26%" height="22" bordercolor="#000000" > 
              <div align="center"> 
                <input type="text" id="txtright" readonly name="E03TOTLIA" size="17" maxlength="15" value="<%= Util.formatCCY(cifTotal.getE03TOTLIA())%>">
              </div>
            </td>
          </tr>
        </table>
        
      </td>
    </tr>
  </table>
  <h4>Lineas de Crédito</h4>
  <table class="tableinfo">
    <tr bordercolor="#FFFFFF"> 
      <td nowrap height="36"> 
        <table cellspacing=0 cellpadding=2 width="100%" border="0">
          <tr id="trdark"> 
            <td nowrap width="21%" height="31" bordercolor="#000000"> 
        <div align="center">Monto Aprobado</div>
      </td>
      <td width="23%"> 
        <div align="center">Monto Utilizado</div>
      </td>
      <td width="20%"> 
              <div align="center">Monto Disponible</div>
      </td>
      <td width="20%"> 
              <div align="center">Participaciones</div>
      </td>
      <td width="20%"> 
        <div align="center">Solicitudes de Hoy</div>
      </td>
    </tr>
    <tr>
      <td width="17%"> 
        <div align="center">
          <input type="text" id="txtright" readonly name="E03LNEAMT"  size="17" maxlength="15" value="<%= Util.formatCCY(cifTotal.getE03LNEAMT())%>" >
        </div>
      </td>
      <td width="23%"> 
        <div align="center">
          <input type="text" id="txtright" readonly name="E03LNEUSE"  size="17" maxlength="15" value="<%= Util.formatCCY(cifTotal.getE03LNEUSE())%>">
        </div>
      </td>
      <td width="20%"> 
              <div align="center"> 
                <input type="text" id="txtright" readonly name="E03LNEAVA"  size="17" maxlength="15" value="<%= Util.formatCCY(cifTotal.getE03LNEAVA())%>">
              </div>
      </td>
      <td width="20%"> 
              <div align="center">
                <input type="text" id="txtright" readonly name="E03PARVEN"  size="17" maxlength="15" value="<%= Util.formatCCY(cifTotal.getE03PARVEN())%>">
              </div>
      </td>
      <td width="20%"> 
        <div align="center">
          <input type="text" id="txtright" readonly name="E03LNETDY"  size="17" maxlength="15" value="<%= Util.formatCCY(cifTotal.getE03LNETDY())%>">
        </div>
      </td>
    </tr>
  </table>
        
      </td>
    </tr>
  </table>
  <h4>Operaciones Contingentes</h4>
  <table class="tableinfo">
    <tr bordercolor="#FFFFFF"> 
      <td nowrap height="36"> 
        <table cellspacing=0 cellpadding=2 width="100%" border="0">
          <tr id="trdark"> 
            <td nowrap height="31" bordercolor="#000000" colspan=2> 
              <div align="center"><b>A Favor del Banco</b></div>
            </td>
            <td nowrap height="31" bordercolor="#000000" colspan=2> 
              <div align="center"><b>A Favor del Cliente</b></div>
            </td>
          </tr>
          <tr id="trclear"> 
            <td nowrap width="28%" bordercolor="#000000"> 
              <div align="right"></div>
            </td>
            <td nowrap width="22%" bordercolor="#000000"> 
              <div align="center"></div>
            </td>
            <td nowrap width="26%" bordercolor="#000000"> 
              <div align="right"><a href="javascript:showAcc('LC','B')">Garant&iacute;a en Efectivo : </a></div>
            </td>
            <td nowrap width="24%" height="22" bordercolor="#000000" > 
              <div align="center"> 
                <input type="text" id="txtright" readonly name="E03GAREFE" size="17" maxlength="15" value="<%= Util.formatCCY(cifTotal.getE03GAREFE())%>" onDblClick="showAcc('LC','B')">
              </div>
            </td>
          </tr>
          <tr id="trdark"> 
            <td nowrap width="28%" bordercolor="#000000"> 
              <div align="right"></div>
            </td>
            <td nowrap width="22%" bordercolor="#000000"> 
              <div align="center"> </div>
            </td>
            <td nowrap width="26%" bordercolor="#000000"> 
              <div align="right"><a href="javascript:showAcc('50','I')">Cobranzas Internacionales : </a></div>
            </td>
            <td nowrap width="24%" height="22" bordercolor="#000000" > 
              <div align="center"> 
                <input type="text" id="txtright" readonly name="E03CBZINT" size="17" maxlength="15" value="<%= Util.formatCCY(cifTotal.getE03CBZINT())%>" onDblClick="showAcc('50','I')">
              </div>
            </td>
          </tr>
          <tr id="trclear"> 
            <td nowrap width="28%" bordercolor="#000000"> 
              <div align="right"><a href="javascript:showAcc('41','')">Aceptaciones Descontadas : </a></div>
            </td>
            <td nowrap width="22%" bordercolor="#000000"> 
              <div align="center"> 
                <input type="text" id="txtright" readonly name="E03ACPDES" size="17" maxlength="15" value="<%= Util.formatCCY(cifTotal.getE03ACPDES())%>" onDblClick="showAcc('41','')">
              </div>
            </td>
            <td nowrap width="26%" bordercolor="#000000"> 
              <div align="right"><a href="javascript:showAcc('51','')">Cobranzas Locales : </a></div>
            </td>
            <td nowrap width="24%" height="22" bordercolor="#000000" > 
              <div align="center"> 
                <input type="text" id="txtright" readonly name="E03CBZLOC" size="17" maxlength="15" value="<%= Util.formatCCY(cifTotal.getE03CBZLOC())%>" onDblClick="showAcc('51',' ')">
              </div>
            </td>
          </tr>
          <tr id="trdark"> 
            <td nowrap width="28%" bordercolor="#000000"> 
              <div align="right"><a href="javascript:showAcc('LC','N')">C. de C. No Confirmadas : </a></div>
            </td>
            <td nowrap width="22%" bordercolor="#000000"> 
              <div align="center"> 
                <input type="text" id="txtright" readonly name="E03LCNCON" size="17" maxlength="15" value="<%= Util.formatCCY(cifTotal.getE03LCNCON())%>" onDblClick="showAcc('LC','N')">
              </div>
            </td>
            <td nowrap width="26%" bordercolor="#000000"> 
              <div align="right"><a href="javascript:showAcc('91','')">Garant&iacute;as en Custodio : </a></div>
            </td>
            <td nowrap width="24%" height="22" bordercolor="#000000" > 
              <div align="center"> 
                <input type="text" id="txtright" readonly name="E03COLATE" size="17" maxlength="15" value="<%= Util.formatCCY(cifTotal.getE03COLATE())%>" onDblClick="showAcc('91','')">
              </div>
            </td>
          </tr>
          <tr id="trclear"> 
            <td nowrap width="28%" bordercolor="#000000"> 
              <div align="right"><a href="javascript:showAcc('50','R')">Cobranzas Recibidas : </a></div>
            </td>
            <td nowrap width="22%" bordercolor="#000000"> 
              <div align="center"> 
                <input type="text" id="txtright" readonly name="E03COLREC" size="17" maxlength="15" value="<%= Util.formatCCY(cifTotal.getE03COLREC())%>" onDblClick="showAcc('50','R')">
              </div>
            </td>
            <td nowrap width="26%" bordercolor="#000000"> 
              <div align="right"><a href="javascript:showAcc('CD','P')">Certificados Pignorados : </a></div>
            </td>
            <td nowrap width="24%" height="22" bordercolor="#000000" > 
              <div align="center"> 
                <input type="text" id="txtright" readonly name="E03CDTPIG" size="17" maxlength="15" value="<%= Util.formatCCY(cifTotal.getE03CDTPIG())%>" onDblClick="showAcc('CD','P')">
              </div>
            </td>
          </tr>
          <tr id="trdark"> 
            <td nowrap width="28%" bordercolor="#000000">&nbsp;</td>
            <td nowrap width="22%" bordercolor="#000000">&nbsp;</td>
            <td nowrap width="26%" bordercolor="#000000"> 
              <div align="right"></div>
            </td>
            <td nowrap width="24%" height="22" bordercolor="#000000" > 
              <div align="center"> </div>
            </td>
          </tr>
          <tr id="trclear"> 
            <td nowrap width="28%" bordercolor="#000000"> 
              <div align="right"><b>Total D&eacute;bito : </b></div>
            </td>
            <td nowrap width="22%" bordercolor="#000000"> 
              <div align="center"> 
                <input type="text" id="txtright" readonly name="E03TOTCDB" size="17" maxlength="15" value="<%= Util.formatCCY(cifTotal.getE03TOTCDB())%>">
              </div>
            </td>
            <td nowrap width="26%" bordercolor="#000000"> 
              <div align="right"><b>Total Cr&eacute;dito : </b></div>
            </td>
            <td nowrap width="24%" height="22" bordercolor="#000000" > 
              <div align="center"> 
                <input type="text" id="txtright" readonly name="E03TOTCCR" size="17" maxlength="15" value="<%= Util.formatCCY(cifTotal.getE03TOTCCR())%>">
              </div>
            </td>
          </tr>
        </table>
        
      </td>
    </tr>
  </table>
  </form>
</body>
</html>
