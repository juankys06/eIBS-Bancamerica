<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.0 Transitional//EN"> 
<html>
<head>
<title>Sumario Operacional por Oficial</title>
<META HTTP-EQUIV="Content-Type" CONTENT="text/html; charset=ISO-8859-1">
<META name="GENERATOR" content="IBM WebSphere Page Designer V3.5.2 for Windows">
<META http-equiv="Content-Style-Type" content="text/css">
<link Href="<%=request.getContextPath()%>/pages/style.css" rel="stylesheet">

<jsp:useBean id="cifTotal" class="datapro.eibs.beans.ECIF17003Message" scope="session" />

<jsp:useBean id= "error" class= "datapro.eibs.beans.ELEERRMessage"  scope="session" />

<script language="Javascript1.1" src="<%=request.getContextPath()%>/pages/s/javascripts/eIBS.jsp"> </SCRIPT>
<script language="Javascript1.1" src="<%=request.getContextPath()%>/pages/s/javascripts/optMenu.jsp"> </SCRIPT>

<SCRIPT Language="Javascript">

function showGraph()
{
	var pg= "<%=request.getContextPath()%>/pages/s/ECIF010_cif_total_graph.jsp";
	CenterNamedWindow(pg,'graph',700,560,2);
}

builtNewMenu(ecif170_i_opt);

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

<h3 align="center">Sumario Operacional por Oficial<img src="<%=request.getContextPath()%>/images/e_ibs.gif" align="left" name="EIBS_GIF" ALT="cust_oper_summary.jsp, ECIF170"></h3>
<hr size="4">
<form method="post" action="<%=request.getContextPath()%>/servlet/datapro.eibs.products.JSECIF170" >
  <INPUT TYPE=HIDDEN NAME="SCREEN" value="4">
  <h4>Oficial</h4>
 <table class="tableinfo">
    <tr bordercolor="#FFFFFF"> 
      <td nowrap>
        <table cellspacing=0 cellpadding=2 width="100%" border="0">
          <tr id="trdark"> 
            <td nowrap width="22%" height="31"> 
              <div align="right">Oficial :</div>
            </td>
            <td nowrap width="18%" height="31"> 
              <input type="text" readonly name="E03OFICDE"  size="11" maxlength="9" value="<%= cifTotal.getE03OFICDE().trim()%>">
            </td>
            <td nowrap width="21%" height="31"> 
              <div align="right">Nombre :</div>
            </td>
            <td nowrap width="39%" height="31"> 
              <input type="text" readonly name="E03OFINME"  size="35" maxlength="45" value="<%= cifTotal.getE03OFINME().trim()%>">
            </td>
          </tr>
          <tr id="trclear"> 
            <td nowrap width="22%"> 
              <div align="right">Tel&eacute;fono:</div>
            </td>
            <td nowrap width="18%"> 
              <input type="text" readonly name="E03OFIPHN" size="13" maxlength="11" value="<%= cifTotal.getE03OFIPHN().trim()%>">
            </td>
            <td nowrap width="21%"><div align="right">Extensión  :</div>
            </td>
            <td nowrap width="39%"> 
              <input type="text" readonly name="E03OFIPXT" size="13" maxlength="11" value="<%= cifTotal.getE03OFIPXT().trim()%>">
            </td>
          </tr>
          <tr id="trclear"> 
            <td nowrap width="21%"> 
              <div align="right">Correo Electronico :</div>
            </td>
            <td nowrap width="39%"><a href="mailto:<%= cifTotal.getE03OFIEML().trim()%>" target="body"><%= cifTotal.getE03OFIEML().trim()%></a> 
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
  <h4>Sumario</h4>
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
              <div align="center"><B>Activos</B></div>
            </td>
            <td nowrap height="31" bordercolor="#000000" colspan=2> 
              <div align="center"><b>A Pasivos</b></div>
            </td>
          </tr>
          <tr id="trclear"> 
            <td nowrap bordercolor="#000000" height="31" width="28%"> 
              <div align="right"><a href="javascript:showAcc2('OV','', '<%= cifTotal.getE03OFICDE().trim()%>')            
              ">Cuentas 
                Sobregiradas : </a> </div>
            </td>
            <td nowrap bordercolor="#000000" height="31" width="21%"> 
              <div align="center"> 
                <input type="text" id="txtright" readonly name="E03OVDRFT" size="17" maxlength="15" value="<%= Util.formatCCY(cifTotal.getE03OVDRFT())%>" onDblClick="showAcc2('OV','', '<%= cifTotal.getE03OFICDE().trim()%>')">
              </div>
            </td>
            <td nowrap width="25%" bordercolor="#000000" height="31"> 
              <div align="right"><a href="javascript:showAcc2('01','', '<%= cifTotal.getE03OFICDE().trim()%>')">Cuentas 
                sin Interes :</a> </div>
            </td>
            <td nowrap width="26%" height="31" bordercolor="#000000" > 
              <div align="center">
                <input type="text" id="txtright" readonly name="E03CTACTE" size="17" maxlength="15" value="<%= Util.formatCCY(cifTotal.getE03CTACTE())%>" onDblClick="showAcc2('01','', '<%= cifTotal.getE03OFICDE().trim()%>')">
              </div>
            </td>
          </tr>
          <tr id="trdark"> 
            <td nowrap bordercolor="#000000" width="28%"> 
              <div align="right"><a href="javascript:showAcc2('10','H', '<%= cifTotal.getE03OFICDE().trim()%>')">Prestamos 
                Hipotecarios :</a> </div>
            </td>
            <td nowrap bordercolor="#000000" width="21%"> 
              <div align="center"> 
                <input type="text" id="txtright" readonly name="E03LNSMOR" size="17" maxlength="15" value="<%= Util.formatCCY(cifTotal.getE03LNSMOR())%>" onDblClick="showAcc2('10','H', '<%= cifTotal.getE03OFICDE().trim()%>')">
              </div>
            </td>
            <td nowrap width="25%" bordercolor="#000000"> 
              <div align="right"><a href="javascript:showAcc2('MK','', '<%= cifTotal.getE03OFICDE().trim()%>')">Cuentas 
                con Interes:</a> </div>
            </td>
            <td nowrap width="26%" height="22" bordercolor="#000000" > 
              <div align="center">
                <input type="text" id="txtright" readonly name="E03CTAMMK" size="17" maxlength="15" value="<%= Util.formatCCY(cifTotal.getE03CTAMMK())%>" onDblClick="showAcc2('MK','', '<%= cifTotal.getE03OFICDE().trim()%>')">
              </div>
            </td>
          </tr>
          <tr id="trclear"> 
            <td nowrap bordercolor="#000000" height="35" width="28%"> 
              <div align="right"><a href="javascript:showAcc2('10','A', '<%= cifTotal.getE03OFICDE().trim()%>')">Arrendamiento 
                Financiero :</a> </div>
            </td>
            <td nowrap bordercolor="#000000" height="35" width="21%"> 
              <div align="center"> 
                <input type="text" id="txtright" readonly name="E03LNSLSG" size="17" maxlength="15" value="<%= Util.formatCCY(cifTotal.getE03LNSLSG())%>" onDblClick="showAcc2('10','A', '<%= cifTotal.getE03OFICDE().trim()%>')">
              </div>
            </td>
            <td nowrap width="25%" bordercolor="#000000" height="35"> 
              <div align="right"><a href="javascript:showAcc2('04','', '<%= cifTotal.getE03OFICDE().trim()%>')">Cuentas 
                de Ahorro :</a> </div>
            </td>
            <td nowrap width="26%" height="35" bordercolor="#000000" > 
              <div align="center"> 
                <input type="text" id="txtright" readonly name="E03CTAAHO" size="17" maxlength="15" value="<%= Util.formatCCY(cifTotal.getE03CTAAHO())%>" onDblClick="showAcc2('04','', '<%= cifTotal.getE03OFICDE().trim()%>')">
              </div>
            </td>
          </tr>
          <tr id="trdark"> 
            <td nowrap bordercolor="#000000" width="28%"> 
              <div align="right"><a href="javascript:showAcc2('10','C', '<%= cifTotal.getE03OFICDE().trim()%>')">Cr&eacute;dito 
                de Consumo :</a></div>
            </td>
            <td nowrap bordercolor="#000000" width="21%"> 
              <div align="center"> 
                <input type="text" id="txtright" readonly name="E03LNSCON" size="17" maxlength="15" value="<%= Util.formatCCY(cifTotal.getE03LNSCON())%>" onDblClick="showAcc2('10','C', '<%= cifTotal.getE03OFICDE().trim()%>')">
              </div>
            </td>
            <td nowrap width="25%" bordercolor="#000000"> 
              <div align="right"><a href="javascript:showAcc2('CD','', '<%= cifTotal.getE03OFICDE().trim()%>')">Certificados 
                :</a> </div>
            </td>
            <td nowrap width="26%" height="22" bordercolor="#000000" > 
              <div align="center"> 
                <input type="text" id="txtright" readonly name="E03CDTDPO" size="17" maxlength="15" value="<%= Util.formatCCY(cifTotal.getE03CDTDPO())%>" onDblClick="showAcc2('CD','', '<%= cifTotal.getE03OFICDE().trim()%>')">
              </div>
            </td>
          </tr>
          <tr id="trclear"> 
          <td nowrap bordercolor="#000000" width="28%"> 
              <div align="right"><a href="javascript:showAcc2('13','A', '<%= cifTotal.getE03OFICDE().trim()%>')">TDS/CDS Purchased
                :</a> </div>
            </td>
            <td nowrap bordercolor="#000000" width="21%"> 
              <div align="center"> 
                <input type="text" id="txtright" readonly name="E03INVERA" size="17" maxlength="15" value="<%= Util.formatCCY(cifTotal.getE03INVERA())%>" onDblClick="showAcc2('13','A', '<%= cifTotal.getE03OFICDE().trim()%>')">
              </div>
            </td>
            <td nowrap width="25%" bordercolor="#000000">&nbsp;</td>
            <td nowrap width="26%" height="22" bordercolor="#000000" > 
              <div align="center"></div>
            </td>
          </tr>
          <tr id="trdark"> 
            <td nowrap width="25%" bordercolor="#000000"> 
              <div align="right"><a href="javascript:showAcc2('13','L', '<%= cifTotal.getE03OFICDE().trim()%>')">Inversiones 
                :</a></div>
            </td>
            <td nowrap width="26%" height="22" bordercolor="#000000" > 
              <div align="center"> 
                <input type="text" id="txtright" readonly name="E03INVERP" size="17" maxlength="15" value="<%= Util.formatCCY(cifTotal.getE03INVERP())%>" onDblClick="showAcc2('13','L', '<%= cifTotal.getE03OFICDE().trim()%>')">
              </div>
            </td>
            <td nowrap width="25%" bordercolor="#000000">&nbsp;</td>
            <td nowrap width="26%" height="22" bordercolor="#000000" > 
              <div align="center"></div>
            </td>
          </tr>
          <tr id="trdark"> 
            <td nowrap bordercolor="#000000" width="28%"> 
              <div align="right"><a href="javascript:showAcc2('10','X', '<%= cifTotal.getE03OFICDE().trim()%>')">Prest&aacute;mos 
                :</a></div>
            </td>
            <td nowrap bordercolor="#000000" width="21%"> 
              <div align="center"> 
                <input type="text" id="txtright" readonly name="E03LNSGRL" size="17" maxlength="15" value="<%= Util.formatCCY(cifTotal.getE03LNSGRL())%>" onDblClick="showAcc2('10','X', '<%= cifTotal.getE03OFICDE().trim()%>')">
              </div>
            </td>
            <td nowrap width="25%" bordercolor="#000000"> 
              <div align="right"><a href="javascript:showAcc2('10','P', '<%= cifTotal.getE03OFICDE().trim()%>')">Participaciones 
                :</a> </div>
            </td>
            <td nowrap width="26%" height="22" bordercolor="#000000" > 
              <div align="center"> 
                <input type="text" id="txtright" readonly name="E03PARTIC" size="17" maxlength="15" value="<%= Util.formatCCY(cifTotal.getE03PARTIC())%>" onDblClick="showAcc2('10','P', '<%= cifTotal.getE03OFICDE().trim()%>')">
              </div>
            </td>
          </tr>
          <tr id="trclear"> 
            <td nowrap bordercolor="#000000" width="28%"> 
              <div align="right"><a href="javascript:showAcc2('14','A', '<%= cifTotal.getE03OFICDE().trim()%>')">Aceptaciones 
                :</a></div>
            </td>
            <td nowrap bordercolor="#000000" width="21%"> 
              <div align="center"> 
                <input type="text" id="txtright" readonly name="E03ACEPTA" size="17" maxlength="15" value="<%= Util.formatCCY(cifTotal.getE03ACEPTA())%>" onDblClick="showAcc2('14','A', '<%= cifTotal.getE03OFICDE().trim()%>')">
              </div>
            </td>
            <td nowrap width="25%" bordercolor="#000000"> 
              <div align="right"><a href="javascript:showAcc2('14','L', '<%= cifTotal.getE03OFICDE().trim()%>')">Aceptaciones 
                :</a> </div>
            </td>
            <td nowrap width="26%" height="22" bordercolor="#000000" > 
              <div align="center"> 
                <input type="text" id="txtright" readonly name="E03ACEPTP" size="17" maxlength="15" value="<%= Util.formatCCY(cifTotal.getE03ACEPTP())%>"  onDblClick="showAcc2('14','L', '<%= cifTotal.getE03OFICDE().trim()%>')">
              </div>
            </td>
          </tr>
          <tr id="trdark"> 
            <td nowrap bordercolor="#000000" width="28%"> 
              <div align="right"><a href="javascript:showAcc2('LC','C', '<%= cifTotal.getE03OFICDE().trim()%>')">C. de 
                C. Confirmadas :</a></div>
            </td>
            <td nowrap bordercolor="#000000" width="21%"> 
              <div align="center"> 
                <input type="text" id="txtright" readonly name="E03LCCONF" size="17" maxlength="15" value="<%= Util.formatCCY(cifTotal.getE03LCCONF())%>" onDblClick="showAcc2('LC','C', '<%= cifTotal.getE03OFICDE().trim()%>')">
              </div>
            </td>
            <td nowrap width="25%" bordercolor="#000000"> 
              <div align="right"><a href="javascript:showAcc2('LC','P', '<%= cifTotal.getE03OFICDE().trim()%>')">C. de 
                C. en Proceso :</a> </div>
            </td>
            <td nowrap width="26%" height="22" bordercolor="#000000" > 
              <div align="center"> 
                <input type="text" id="txtright" readonly name="E03LCPROC" size="17" maxlength="15" value="<%= Util.formatCCY(cifTotal.getE03LCPROC())%>"  onDblClick="showAcc2('LC','P', '<%= cifTotal.getE03OFICDE().trim()%>')">
              </div>
            </td>
          </tr>
          <tr id="trclear"> 
            <td nowrap bordercolor="#000000" width="28%"> 
              <div align="right"><a href="javascript:showAcc2('30','', '<%= cifTotal.getE03OFICDE().trim()%>')">Spot Comprados 
                :</a> </div>
            </td>
            <td nowrap bordercolor="#000000" width="21%"> 
              <div align="center"> 
                <input type="text" id="txtright" readonly name="E03SPTPUR" size="17" maxlength="15" value="<%= Util.formatCCY(cifTotal.getE03SPTPUR())%>" onDblClick="showAcc2('30','', '<%= cifTotal.getE03OFICDE().trim()%>')">
              </div>
            </td>
            <td nowrap width="25%" bordercolor="#000000"> 
              <div align="right"><a href="javascript:showAcc2('31','', '<%= cifTotal.getE03OFICDE().trim()%>')">Spot Vendidos 
                :</a></div>
            </td>
            <td nowrap width="26%" height="22" bordercolor="#000000" > 
              <div align="center"> 
                <input type="text" id="txtright" readonly name="E03SPTSAL" size="17" maxlength="15" value="<%= Util.formatCCY(cifTotal.getE03SPTSAL())%>" onDblClick="showAcc2('31','', '<%= cifTotal.getE03OFICDE().trim()%>')">
              </div>
            </td>
          </tr>
          <tr id="trdark"> 
            <td nowrap bordercolor="#000000" width="28%"> 
              <div align="right"><a href="javascript:showAcc2('32','', '<%= cifTotal.getE03OFICDE().trim()%>')">Forward 
                Comprados :</a></div>
            </td>
            <td nowrap bordercolor="#000000" width="21%"> 
              <div align="center"> 
                <input type="text" id="txtright" readonly name="E03FRWPUR" size="17" maxlength="15" value="<%= Util.formatCCY(cifTotal.getE03FRWPUR())%>" onDblClick="showAcc2('32','', '<%= cifTotal.getE03OFICDE().trim()%>')">
              </div>
            </td>
            <td nowrap width="25%" bordercolor="#000000"> 
              <div align="right"><a href="javascript:showAcc2('33','', '<%= cifTotal.getE03OFICDE().trim()%>')">Forward 
                Vendidos :</a> </div>
            </td>
            <td nowrap width="26%" height="22" bordercolor="#000000" > 
              <div align="center"> 
                <input type="text" id="txtright" readonly name="E03FRWSAL" size="17" maxlength="15" value="<%= Util.formatCCY(cifTotal.getE03FRWSAL())%>"  onDblClick="showAcc2('33','', '<%= cifTotal.getE03OFICDE().trim()%>')">
              </div>
            </td>
          </tr>
          <tr id="trclear"> 
            <td nowrap bordercolor="#000000" width="28%"> 
              <div align="right"><a href="javascript:showAcc2('32','', '<%= cifTotal.getE03OFICDE().trim()%>')">Opciones 
                Comprados :</a></div>
            </td>
            <td nowrap bordercolor="#000000" width="21%"> 
              <div align="center"> 
                <input type="text" id="txtright" readonly name="E03OPTPUR" size="17" maxlength="15" value="<%= Util.formatCCY(cifTotal.getE03OPTPUR())%>" onDblClick="showAcc2('32','', '<%= cifTotal.getE03OFICDE().trim()%>')">
              </div>
            </td>
            <td nowrap width="25%" bordercolor="#000000"> 
              <div align="right"><a href="javascript:showAcc2('33','', '<%= cifTotal.getE03OFICDE().trim()%>')">Opciones 
                Vendidas :</a> </div>
            </td>
            <td nowrap width="26%" height="22" bordercolor="#000000" > 
              <div align="center"> 
                <input type="text" id="txtright" readonly name="E03OPTSAL" size="17" maxlength="15" value="<%= Util.formatCCY(cifTotal.getE03OPTSAL())%>"  onDblClick="showAcc2('33','', '<%= cifTotal.getE03OFICDE().trim()%>')">
              </div>
            </td>
          </tr>
           <tr id="trdark"> 
            <td nowrap bordercolor="#000000" width="28%"> 
              <div align="right"><a href="javascript:showAcc2('32','', '<%= cifTotal.getE03OFICDE().trim()%>')">FRAs Comprados :</a></div>
            </td>
            <td nowrap bordercolor="#000000" width="21%"> 
              <div align="center"> 
                <input type="text" id="txtright" readonly name="E03OPTPUR" size="17" maxlength="15" value="<%= Util.formatCCY(cifTotal.getE03OPTPUR())%>" onDblClick="showAcc2('32','', '<%= cifTotal.getE03OFICDE().trim()%>')">
              </div>
            </td>
            <td nowrap width="25%" bordercolor="#000000"> 
              <div align="right"><a href="javascript:showAcc2('33','', '<%= cifTotal.getE03OFICDE().trim()%>')">FRAs 
                Vendidos :</a> </div>
            </td>
            <td nowrap width="26%" height="22" bordercolor="#000000" > 
              <div align="center"> 
                <input type="text" id="txtright" readonly name="E03FRASAL" size="17" maxlength="15" value="<%= Util.formatCCY(cifTotal.getE03FRASAL())%>"  onDblClick="showAcc2('33','', '<%= cifTotal.getE03OFICDE().trim()%>')">
              </div>
            </td>
          </tr>
          <tr id="trclear"> 
            <td nowrap bordercolor="#000000" width="28%"> 
              <div align="right"><a href="javascript:showAcc2('32','', '<%= cifTotal.getE03OFICDE().trim()%>')">Bloques Financieros :</a></div>
            </td>
            <td nowrap bordercolor="#000000" width="21%"> 
              <div align="center"> 
                <input type="text" id="txtright" readonly name="E03MMPPUR" size="17" maxlength="15" value="<%= Util.formatCCY(cifTotal.getE03MMPPUR())%>" onDblClick="showAcc2('32','', '<%= cifTotal.getE03OFICDE().trim()%>')">
              </div>
            </td>
            <td nowrap width="25%" bordercolor="#000000">&nbsp;</td>
            <td nowrap width="26%" height="22" bordercolor="#000000" > 
              <div align="center"></div>
            </td>
          </tr>
          <tr id="trdark"> 
            <td nowrap bordercolor="#000000" width="28%">&nbsp;</td>
            <td nowrap bordercolor="#000000" width="21%"> 
              <div align="center"></div>
            </td>
            <td nowrap width="25%" bordercolor="#000000">&nbsp;</td>
            <td nowrap width="26%" height="22" bordercolor="#000000" > 
              <div align="center"></div>
            </td>
          </tr>
          <tr id="trclear"> 
            <td nowrap bordercolor="#000000" width="28%"> 
              <div align="right"><b>Total Activo :</b></div>
            </td>
            <td nowrap bordercolor="#000000" width="21%"> 
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
              <div align="center"><b>Débito</b></div>
            </td>
            <td nowrap height="31" bordercolor="#000000" colspan=2> 
              <div align="center"><b>Crédito</b></div>
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
              <div align="right"><a href="javascript:showAcc2('LC','B', '<%= cifTotal.getE03OFICDE().trim()%>')">Garant&iacute;a en Efectivo : </a></div>
            </td>
            <td nowrap width="24%" height="22" bordercolor="#000000" > 
              <div align="center"> 
                <input type="text" id="txtright" readonly name="E03GAREFE" size="17" maxlength="15" value="<%= Util.formatCCY(cifTotal.getE03GAREFE())%>" onDblClick="showAcc2('LC','B', '<%= cifTotal.getE03OFICDE().trim()%>')">
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
              <div align="right"><a href="javascript:showAcc2('50','I', '<%= cifTotal.getE03OFICDE().trim()%>')">Cobranzas Internacionales : </a></div>
            </td>
            <td nowrap width="24%" height="22" bordercolor="#000000" > 
              <div align="center"> 
                <input type="text" id="txtright" readonly name="E03CBZINT" size="17" maxlength="15" value="<%= Util.formatCCY(cifTotal.getE03CBZINT())%>" onDblClick="showAcc2('50','I', '<%= cifTotal.getE03OFICDE().trim()%>')">
              </div>
            </td>
          </tr>
          <tr id="trclear"> 
            <td nowrap width="28%" bordercolor="#000000"> 
              <div align="right"><a href="javascript:showAcc2('41','', '<%= cifTotal.getE03OFICDE().trim()%>')">Aceptaciones Descontadas : </a></div>
            </td>
            <td nowrap width="22%" bordercolor="#000000"> 
              <div align="center"> 
                <input type="text" id="txtright" readonly name="E03ACPDES" size="17" maxlength="15" value="<%= Util.formatCCY(cifTotal.getE03ACPDES())%>" onDblClick="showAcc2('41','', '<%= cifTotal.getE03OFICDE().trim()%>')">
              </div>
            </td>
            <td nowrap width="26%" bordercolor="#000000"> 
              <div align="right"><a href="javascript:showAcc2('51','', '<%= cifTotal.getE03OFICDE().trim()%>')">Cobranzas Locales : </a></div>
            </td>
            <td nowrap width="24%" height="22" bordercolor="#000000" > 
              <div align="center"> 
                <input type="text" id="txtright" readonly name="E03CBZLOC" size="17" maxlength="15" value="<%= Util.formatCCY(cifTotal.getE03CBZLOC())%>" onDblClick="showAcc2('51',' ', '<%= cifTotal.getE03OFICDE().trim()%>')">
              </div>
            </td>
          </tr>
          <tr id="trdark"> 
            <td nowrap width="28%" bordercolor="#000000"> 
              <div align="right"><a href="javascript:showAcc2('LC','N', '<%= cifTotal.getE03OFICDE().trim()%>')">C. de C. No Confirmadas : </a></div>
            </td>
            <td nowrap width="22%" bordercolor="#000000"> 
              <div align="center"> 
                <input type="text" id="txtright" readonly name="E03LCNCON" size="17" maxlength="15" value="<%= Util.formatCCY(cifTotal.getE03LCNCON())%>" onDblClick="showAcc2('LC','N', '<%= cifTotal.getE03OFICDE().trim()%>')">
              </div>
            </td>
            <td nowrap width="26%" bordercolor="#000000"> 
              <div align="right"><a href="javascript:showAcc2('91','', '<%= cifTotal.getE03OFICDE().trim()%>')">Garant&iacute;as en Custodio : </a></div>
            </td>
            <td nowrap width="24%" height="22" bordercolor="#000000" > 
              <div align="center"> 
                <input type="text" id="txtright" readonly name="E03COLATE" size="17" maxlength="15" value="<%= Util.formatCCY(cifTotal.getE03COLATE())%>" onDblClick="showAcc2('91','', '<%= cifTotal.getE03OFICDE().trim()%>')">
              </div>
            </td>
          </tr>
          <tr id="trclear"> 
            <td nowrap width="28%" bordercolor="#000000"> 
              <div align="right"><a href="javascript:showAcc2('50','R', '<%= cifTotal.getE03OFICDE().trim()%>')">Cobranzas Recibidas : </a></div>
            </td>
            <td nowrap width="22%" bordercolor="#000000"> 
              <div align="center"> 
                <input type="text" id="txtright" readonly name="E03COLREC" size="17" maxlength="15" value="<%= Util.formatCCY(cifTotal.getE03COLREC())%>" onDblClick="showAcc2('50','R', '<%= cifTotal.getE03OFICDE().trim()%>')">
              </div>
            </td>
            <td nowrap width="26%" bordercolor="#000000"> 
              <div align="right"><a href="javascript:showAcc2('CD','P', '<%= cifTotal.getE03OFICDE().trim()%>')">Certificados Pignorados : </a></div>
            </td>
            <td nowrap width="24%" height="22" bordercolor="#000000" > 
              <div align="center"> 
                <input type="text" id="txtright" readonly name="E03CDTPIG" size="17" maxlength="15" value="<%= Util.formatCCY(cifTotal.getE03CDTPIG())%>" onDblClick="showAcc2('CD','P', '<%= cifTotal.getE03OFICDE().trim()%>')">
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
  
  <h4></h4>
  
  </form>
</body>
</html>
