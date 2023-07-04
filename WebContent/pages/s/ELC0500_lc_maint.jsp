<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.0 Transitional//EN">
<html>
<head>
<title>Apertura de Cartas de Credito</title>
<META HTTP-EQUIV="Content-Type" CONTENT="text/html; charset=ISO-8859-1">
<META name="GENERATOR" content="IBM WebSphere Page Designer V3.5.2 for Windows">
<META http-equiv="Content-Style-Type" content="text/css">
<link href="<%=request.getContextPath()%>/pages/style.css" rel="stylesheet">

<script language="Javascript1.1" src="<%=request.getContextPath()%>/pages/s/javascripts/eIBS.jsp"> </SCRIPT>
<script language="Javascript1.1" src="<%=request.getContextPath()%>/pages/s/javascripts/optMenu.jsp"> </SCRIPT>

<jsp:useBean id= "error" class= "datapro.eibs.beans.ELEERRMessage"  scope="session" />

<jsp:useBean id= "userPO" class= "datapro.eibs.beans.UserPos"  scope="session" />

<jsp:useBean id="lcABasic" class="datapro.eibs.beans.ELC051001Message"  scope="session" />

<SCRIPT Language="Javascript">

	 builtNewMenu(lc_i_opt);

</SCRIPT>

</head>

<body bgcolor="#FFFFFF">

<%@ page import = "datapro.eibs.master.Util" %>
<SCRIPT> initMenu(); </SCRIPT>

<H3 align="center">Apertura de Cartas de Cr&eacute;dito<img src="<%=request.getContextPath()%>/images/e_ibs.gif" align="left" name="EIBS_GIF" ALT="lc_inq_basic.jsp,ELC0450"></h3>
<hr size="4">
<form method="post" action="<%=request.getContextPath()%>/servlet/datapro.eibs.products.JSELC0500" >
<INPUT TYPE=HIDDEN NAME="SCREEN" value="">
  <table class="tableinfo">
    <tr > 
      <td nowrap> 
        <table cellspacing="0" cellpadding="2" width="100%" border="0" class="tbhead">
          <tr id="trdark"> 
            <td nowrap width="16%" > 
              <div align="right"><b>Cliente :</b></div>
            </td>
            <td nowrap width="20%" > 
              <div align="left">
                <input type="text" name="E01LCMCUN" readonly size="11" maxlength="9" value="<%= lcABasic.getE01LCMCUN().trim()%>">
              </div>
            </td>
            <td nowrap width="16%" > 
              <div align="right"><b>Nombre :</b> </div>
            </td>
            <td nowrap colspan="3" > 
              <div align="left"><font face="Arial"><font face="Arial"><font size="2">
                <input type="text" name="E01CUSNA1" readonly size="47" maxlength="45" value="<%= lcABasic.getE01CUSNA1().trim()%>">
                </font></font></font></div>
            </td>
          </tr>
          <tr id="trdark"> 
            <td nowrap width="16%"> 
              <div align="right"><b>Cuenta :</b></div>
            </td>
            <td nowrap width="20%"> 
              <div align="left">
                <input type="text" name="E01LCMACC" readonly size="14" maxlength="12" value="<%= lcABasic.getE01LCMACC().trim()%>">
              </div>
            </td>
            <td nowrap width="16%"> 
              <div align="right"><b>Moneda : </b></div>
            </td>
            <td nowrap width="16%"> 
              <div align="left"><b>
                <input type="text" name="E01LCMCCY" readonly size="5" maxlength="3" value="<%= lcABasic.getE01LCMCCY().trim()%>">
                </b> </div>
            </td>
            <td nowrap width="16%"> 
              <div align="right"><b>Producto : </b></div>
            </td>
            <td nowrap width="16%"> 
              <div align="left"><b>
                <input type="text" name="E01LCMPRO" readonly size="6" maxlength="4" value="<%= lcABasic.getE01LCMPRO().trim()%>">
                </b> </div>
            </td>
          </tr>
        </table>
      </td>
    </tr>
  </table>
  <H4>Informaci&oacute;n B&aacute;sica</H4>
<table class="tableinfo">
    <tr > 
      <td nowrap> 
        <table cellspacing="0" cellpadding="2" width="100%" border="0">
          <tr id="trdark"> 
            <td nowrap width="25%"> 
              <div align="right">Monto Original :</div>
            </td>
            <td nowrap width="18%"> 
              <input type="text" readonly size="15" maxlength="13" name="E01LCMOAM" value="<%= Util.formatCCY(lcABasic.getE01LCMOAM().trim())%>">
            </td>
            <td nowrap width="27%"> 
              <div align="right">Fecha de Emisi&oacute;n :</div>
            </td>
            <td nowrap width="30%"> 
              <input type="text" name="E01LCMIDM" readonly size="4" maxlength="2" value="<%= lcABasic.getE01LCMIDM().trim()%>">
              <input type="text" name="E01LCMIDD" readonly size="4" maxlength="2" value="<%= lcABasic.getE01LCMIDD().trim()%>">
              <input type="text" name="E01LCMIDY" readonly size="4" maxlength="2" value="<%= lcABasic.getE01LCMIDY().trim()%>">
            </td>
          </tr>
          <tr id="trclear"> 
            <td nowrap width="25%"> 
              <div align="right">% de Tolerancia</div>
            </td>
            <td nowrap width="18%"> 
              <input type="text" readonly size="3" maxlength="1">
              / 
              <input type="text" name="E" readonly size="3" maxlength="1">
            </td>
            <td nowrap width="27%"> 
              <div align="right">Fecha de Expiraci&oacute;n :</div>
            </td>
            <td nowrap width="30%"> 
              <input type="text" name="E01LCMEXM" readonly size="4" maxlength="2" value="<%= lcABasic.getE01LCMEXM().trim()%>">
              <input type="text" name="E01LCMEXD" readonly size="4" maxlength="2" value="<%= lcABasic.getE01LCMEXD().trim()%>">
              <input type="text" name="E01LCMEXY" readonly size="4" maxlength="2" value="<%= lcABasic.getE01LCMEXY().trim()%>">
            </td>
          </tr>
          <tr id="trdark"> 
            <td nowrap width="25%" height="23"> 
              <div align="right">Monto Actual :</div>
            </td>
            <td nowrap height="23" width="18%"> 
              <input type="text" readonly size="15" maxlength="13" value="<%= Util.formatCCY(lcABasic.getE01LCMAMN().trim())%>"  name="E01LCMAMN">
            </td>
            <td nowrap width="27%" height="23"> 
              <div align="right">Tasa de Cambio Mda. Ext.</div>
            </td>
            <td nowrap width="30%" height="23"> 
              <input type="text" name="E01LCMOFX" readonly size="13" maxlength="11" value="<%= lcABasic.getE01LCMOFX().trim()%>">
            </td>
          </tr>
          <tr id="trclear"> 
            <td nowrap width="25%" height="23"> 
              <div align="right">Garant&iacute;a en Efectivo :</div>
            </td>
            <td nowrap height="23" width="18%"> 
              <input type="text"  name="E01LCMCAM" readonly size="15" maxlength="13" value="<%= Util.formatCCY(lcABasic.getE01LCMCAM().trim())%>">
            </td>
            <td nowrap width="27%" height="23"> 
              <div align="right"> Cuenta Garant&iacute;a en Efectivo ::</div>
            </td>
            <td nowrap width="30%" height="23"> 
              <input type="text"  name="E01LCMCCA" readonly size="18" maxlength="16" value="<%= Util.formatCCY(lcABasic.getE01LCMCCA().trim())%>">
            </td>
          </tr>
          <tr id="trdark"> 
            <td nowrap width="25%" height="19"> 
              <div align="right">Otras Garant&iacute;as :</div>
            </td>
            <td nowrap height="19" width="18%"> 
              <input type="text"  name="E01LCMOCA" readonly size="15" maxlength="13" value="<%= Util.formatCCY(lcABasic.getE01LCMOCA().trim())%>">
            </td>
            <td nowrap width="27%" height="19"> 
              <div align="right">Tenor :</div>
            </td>
            <td nowrap width="30%" height="19"> 
              <input type="text" name="E01LCMTNR" readonly size="3" maxlength="1" value="<%= lcABasic.getE01LCMTNR().trim()%>">
              <img src="<%=request.getContextPath()%>/images/1b.gif" alt=". . ." align="absmiddle" border="0" width="32" height="32" > 
            </td>
          </tr>
          <tr id="trclear"> 
            <td nowrap width="25%" height="19"> 
              <div align="right">Confirmada:</div>
            </td>
            <td nowrap height="19" width="18%"> 
              <input type="radio" name="E01LCMCNF" value="1" <%if (client.getE01LCMCNF().equals("1")) out.print("checked"); %> checked>
              S&iacute; 
              <input type="radio" name="E01LCMCNF" value="2"<%if (!client.getE01LCMCNF().equals("1")) out.print("checked"); %>>
              No </td>
            <td nowrap width="27%" height="19"> 
              <div align="right">Conf. otro Banco :</div>
            </td>
            <td nowrap width="30%" height="19">
              <input type="radio" name="E01LCMCNO" value="1" <%if (client.getE01LCMCNO().equals("1")) out.print("checked"); %> checked>
              S&iacute; 
              <input type="radio" name="E01LCMCNO" value="2"<%if (!client.getE01LCMCNO().equals("1")) out.print("checked"); %>>
              No </td>
          </tr>
          <tr id="trdark"> 
            <td nowrap width="25%" height="19"> 
              <div align="right">Incoterms :</div>
            </td>
            <td nowrap height="19" width="18%">
              <input type="text" name="E01LCMITR" readonly size="6" maxlength="4" value="<%= lcABasic.getE01LCMITR().trim()%>">
            </td>
            <td nowrap width="27%" height="19"> 
              <div align="right">Embarques Parciales :</div>
            </td>
            <td nowrap width="30%" height="19">
              <input type="radio" name="E01LCMPSH" value="1" <%if (client.getE01LCMPSH().equals("1")) out.print("checked"); %> checked>
              S&iacute; 
              <input type="radio" name="E01LCMPSH" value="2"<%if (!client.getE01LCMPSH().equals("1")) out.print("checked"); %>>
              No </td>
          </tr>
          <tr id="trclear"> 
            <td nowrap width="25%" height="19"> 
              <div align="right">Transferible :</div>
            </td>
            <td nowrap height="19" width="18%"> 
              <input type="radio" name="E01LCMTRN" value="1" <%if (client.getE01LCMTRN().equals("1")) out.print("checked"); %> checked>
              S&iacute; 
              <input type="radio" name="E01LCMTRN" value="2"<%if (!client.getE01LCMTRN().equals("1")) out.print("checked"); %>>
              No </td>
            <td nowrap width="27%" height="19"> 
              <div align="right">Transbordo :</div>
            </td>
            <td nowrap width="30%" height="19"> 
              <input type="radio" name="E01LCMTSH" value="1" <%if (client.getE01LCMTSH().equals("1")) out.print("checked"); %> checked>
              S&iacute; 
              <input type="radio" name="E01LCMTSH" value="2"<%if (!client.getE01LCMTSH().equals("1")) out.print("checked"); %>>
              No </td>
          </tr>
          <tr id="trdark"> 
            <td nowrap width="25%" height="19"> 
              <div align="right">Revocable:</div>
            </td>
            <td nowrap height="19" width="18%"> 
              <input type="radio" name="E01LCMRVC" value="1" <%if (client.getE01LCMRVC().equals("1")) out.print("checked"); %> checked>
              S&iacute; 
              <input type="radio" name="E01LCMRVC" value="2"<%if (!client.E01LCMRVC().equals("1")) out.print("checked"); %>>
              No </td>
            <td nowrap width="27%" height="19"> 
              <div align="right">Negociable:</div>
            </td>
            <td nowrap width="30%" height="19"> 
              <input type="radio" name="E01LCMNEG" value="1" <%if (client.getE01LCMNEG().equals("1")) out.print("checked"); %> checked>
              S&iacute; 
              <input type="radio" name="E01LCMNEG" value="2"<%if (!client.getE01LCMNEG().equals("1")) out.print("checked"); %>>
              No </td>
          </tr>
          <tr id="trclear"> 
            <td nowrap width="25%" height="19"> 
              <div align="right">Cargos por Cuenta de :</div>
            </td>
            <td nowrap height="19" width="18%"> 
              <input type="text" name="E01LCMAOB" readonly size="15" maxlength="13" value="
<%
if (lcABasic.getE01LCMAOB().equals("A")) out.print("APLICANTE");
else if (lcABasic.getE01LCMAOB().equals("B")) out.print("BENEFICIARIO");
else out.print("");%>">
            </td>
            <td nowrap width="27%" height="19"> 
              <div align="right">L&iacute;nea de Cr&eacute;dito :</div>
            </td>
            <td nowrap width="30%" height="19"> 
              <input type="text"  name="E01LCMLNR" readonly size="11" maxlength="9" value="<%= Util.formatCCY(lcABasic.getE01LCMLNR().trim())%>">
              <input type="text" name="E01LCMCMN" readonly size="6" maxlength="4" value="<%= lcABasic.getE01LCMCMN().trim()%>">
            </td>
          </tr>
          <tr id="trdark"> 
            <td nowrap width="25%" height="19"> 
              <div align="right">Comisiones y Gastos :</div>
            </td>
            <td nowrap height="19" width="18%"> 
              <input type="text"  name="E01LCMCOM" readonly size="13" maxlength="11" value="<%= Util.formatCCY(lcABasic.getE01LCMCOM().trim())%>">
            </td>
            <td nowrap width="27%" height="19"> 
              <div align="right">Tarifa de Cargos :</div>
            </td>
            <td nowrap width="30%" height="19"> 
              <input type="text" name="E01LCMTAR" readonly size="4" maxlength="2" value="<%= lcABasic.getE01LCMTAR().trim()%>">
            </td>
          </tr>
          <tr id="trclear"> 
            <td nowrap width="25%" height="19"> 
              <div align="right">Centro de Costo:</div>
            </td>
            <td nowrap height="19" width="18%"> 
              <input type="text" name="E01LCMCCN" size="8" maxlength="5" value="<%= lcABasic.getE01LCMCCN().trim()%>">
            </td>
            <td nowrap width="27%" height="19"> 
              <div align="right">Retenci&oacute;n de Pago :</div>
            </td>
            <td nowrap width="30%" height="19"> 
              <input type="text" name="E01LCMHPF" readonly size="3" maxlength="1" value="<%= lcABasic.getE01LCMHPF().trim()%>">
            </td>
          </tr>
          <tr id="trdark"> 
            <td nowrap width="25%" height="19"> 
              <div align="right">Nuestra Referencia :</div>
            </td>
            <td nowrap height="19" width="18%"> 
              <input type="text" name="E01LCMORF" readonly size="18" maxlength="16" value="<%= lcABasic.getE01LCMORF().trim()%>">
            </td>
            <td nowrap width="27%" height="19"> 
              <p align="right">Referencia Otro Banco :</p>
            </td>
            <td nowrap width="30%" height="19"> 
              <input type="text" name="E01LCMTRF" readonly size="18" maxlength="16" value="<%= lcABasic.getE01LCMTRF().trim()%>">
            </td>
          </tr>
          <tr id="trclear"> 
            <td nowrap width="25%" height="36"> 
              <div align="right">Banco / Sucursal :</div>
            </td>
            <td nowrap height="36" width="18%"> 
              <input type="text" name="E01LCMBNK" readonly size="2" maxlength="2" value="<%= lcABasic.getE01LCMBNK().trim()%>">
              <input type="text" name="E01LCMBRN" readonly size="2" maxlength="2" value="<%= lcABasic.getE01LCMBRN().trim()%>">
            </td>
            <td nowrap width="27%" height="36"> 
              <div align="right">Cuenta Contable :</div>
            </td>
            <td nowrap width="30%" height="36"> 
              <input type="text" name="E01LCMGLN" readonly size="16" maxlength="16" value="<%= lcABasic.getE01LCMGLN().trim()%>">
            </td>
          </tr>
          <tr id="trdark"> 
            <td nowrap width="25%" height="19"> 
              <div align="right">Tipo de Cuenta :</div>
            </td>
            <td nowrap height="19" width="18%"> 
              <input type="text" name="E01LCMATY2" readonly size="4" maxlength="4" value="<%= lcABasic.getE01LCMATY().trim()%>">
              <a href="javascript:GetCodeCNOFC('E01LCMATY2','04')"><img src="<%=request.getContextPath()%>/images/1b.gif" alt="ayuda" align="absbottom" border="0"></a> 
            </td>
            <td nowrap width="27%" height="19"> 
              <div align="right">Tipo de Carta de Cr&eacute;dito:</div>
            </td>
            <td nowrap width="30%" height="19"> 
              <select name="select">
                <option value=" " <% if (!(lcABasic.getE01LCMTPY().equals("O") ||lnMant.getE01LCMTPY().equals("I"))) out.print("selected"); %>></option>
                <option value="O" <% if(lcABasic.getE01LCMTPY().equals("O")) out.print("selected");%>>EXPORTACION</option>
                <option value="I" <% if(lcABasic.getE01LCMTPY().equals("I")) out.print("selected");%>>IMPORTACION</option>
              </select>
            </td>
          </tr>
          <tr id="trclear"> 
            <td nowrap width="25%" height="19"> 
              <div align="right">Revaluacion :</div>
            </td>
            <td nowrap height="19" width="18%"> 
              <input type="radio" name="radiobutton" value="radiobutton" checked>
              S&iacute; 
              <input type="radio" name="radiobutton" value="radiobutton">
              No </td>
            <td nowrap width="27%" height="19"> 
              <div align="right">C&oacute;digo del Oficial:</div>
            </td>
            <td nowrap width="30%" height="19"> 
              <input type="text" name="E01LCMOFI" size="5" value="<%= lcABasic.getE01LCMOFI().trim()%>">
              <img src="<%=request.getContextPath()%>/images/1b.gif" alt=". . ." align="absmiddle" border="0" width="32" height="32" > 
            </td>
          </tr>
          <tr id="trdark"> 
            <td nowrap width="25%" height="19"> 
              <div align="right">Com.Apert.Dia Emisi&oacute;n:</div>
            </td>
            <td nowrap height="19" width="18%"> 
              <input type="radio" name="E01LCMOCI" value="1" <%if (client.getE01LCMOCI().equals("1")) out.print("checked"); %> checked>
              S&iacute; 
              <input type="radio" name="E01LCMOCI" value="2"<%if (!client.getE01LCMOCI().equals("1")) out.print("checked"); %>>
              No </td>
            <td nowrap width="27%" height="19"> 
              <div align="right">Com.Enmda.Dia Emisi&oacute;n:</div>
            </td>
            <td nowrap width="30%" height="19"> 
              <input type="radio" name="E01LCMACI" value="1" <%if (client.getE01LCMACI().equals("1")) out.print("checked"); %> checked>
              S&iacute; 
              <input type="radio" name="E01LCMACI" value="2" <%if (!client.E01LCMACI().equals("1")) out.print("checked"); %>>
              No </td>
          </tr>
          <tr id="trclear"> 
            <td nowrap width="25%" height="34"> 
              <div align="right">Inc/Dec:</div>
            </td>
            <td nowrap height="34" width="18%"> 
              <input type="radio" name="E01LCMIDF" value="I" <%if (client.getE01LCMPSH().equals("1")) out.print("checked"); %> checked>
              Inc. 
              <input type="radio" name="E01LCMIDF" value="D"<%if (!client.getE01LCMPSH().equals("1")) out.print("checked"); %>>
              Dec </td>
            <td nowrap width="27%" height="34"> 
              <div align="right">Enmendar Monto por: </div>
            </td>
            <td nowrap width="30%" height="34">
              <input type="text" readonly size="15" maxlength="13" value="<%= Util.formatCCY(lcABasic.getE01LCMIDA().trim())%>"  name="E01LCMIDA">
            </td>
          </tr>
        </table>
      </td>
    </tr>
  </table>
  <H4>Condiciones de Cr&eacute;dito</H4>
  <table class="tableinfo">
    <tr > 
      <td nowrap height="10">
        <table width="100%" border="0" cellpadding="2" cellspacing="0">
          <tr id= "trdark"> 
            <td width="16%"> 
              <div align="right">Localidad:</div>
            </td>
            <td width="27%"> 
              <input type="text" name="textfield5" size="25">
            </td>
            <td width="9%"> 
              <div align="right">Por: </div>
            </td>
            <td width="48%"> 
              <select name="select3">
                <option>Por Pago</option>
              </select>
            </td>
          </tr>
          <tr> 
            <td width="16%"> 
              <div align="right">Disponible con:</div>
            </td>
            <td width="27%"> 
              <textarea name="textfield10" cols="25"></textarea>
            </td>
            <td width="9%">&nbsp;</td>
            <td width="48%">&nbsp;</td>
          </tr>
          <tr id="trdark"> 
            <td colspan="3"> 
              <div align="center"><b>Girado en</b></div>
            </td>
            <td width="48%"> 
              <div align="center"><b>Destinatario del Giro</b></div>
            </td>
          </tr>
          <tr id ="trclear"> 
            <td colspan="3"> 
              <div align="center"> 
                <textarea name="textfield6" cols="50" rows="10"></textarea>
              </div>
            </td>
            <td width="48%"> 
              <div align="center"> 
                <textarea name="textfield7" cols="50" rows="10"></textarea>
              </div>
            </td>
          </tr>
          <tr id= "trdark"> 
            <td colspan="3"> 
              <div align="center"><b>Pagos Mixtos</b></div>
            </td>
            <td width="48%"> 
              <div align="center"><b>Pagos Diferidos</b></div>
            </td>
          </tr>
          <tr id= "trclear"> 
            <td colspan="3"> 
              <div align="center"> 
                <textarea name="textfield8" cols="50" rows="10"></textarea>
              </div>
            </td>
            <td width="48%"> 
              <div align="center"> 
                <textarea name="textfield9" cols="50" rows="10"></textarea>
              </div>
            </td>
          </tr>
        </table>
      </td>
    </tr>
  </table>
  <% lcABasic.getE01LCMTYP().equals("I")){ %>
  <H4>Informaci&oacute;n Adicional</H4>
  <table class="tableinfo">
    <tr > 
      <td nowrap> 
        <table cellspacing="0" cellpadding="2" width="100%" border="0">
          <tr id="trdark"> 
            <td nowrap colspan="2"> 
              <div align="left"><b>Aplicante / Ordenante</b></div>
            </td>
            <td nowrap colspan="2"><b>Beneficiario</b></td>
          </tr>
          <tr id="trclear"> 
            <td nowrap colspan="2"> 
              <div align="left"> 
                <input type="text" name="E01LCMIB1" readonly size="37" maxlength="35" value="<%= lcABasic.getE01LCMIB1().trim()%>">
              </div>
            </td>
            <td nowrap colspan="2"> 
              <input type="text" name="E01LCMBN1I" readonly size="37" maxlength="35" value="<%= lcABasic.getE01LCMBN1().trim()%>"onBlur="document.forms[0].E01LCMBN1.value = document.forms[0].E01LCMBN1I.value">
            </td>
          </tr>
          <tr id="trdark"> 
            <td nowrap colspan="2"> 
              <input type="text" name="E01LCMIB2" readonly size="37" maxlength="35" value="<%= lcABasic.getE01LCMIB2().trim()%>">
            </td>
            <td nowrap colspan="2"> 
              <input type="text" name="E01LCMBN2I" readonly size="37" maxlength="35" value="<%= lcABasic.getE01LCMBN2().trim()%>"onBlur="document.forms[0].E01LCMBN2.value = document.forms[0].E01LCMBN2I.value">
            </td>
          </tr>
          <tr id="trclear"> 
            <td nowrap colspan="2"> 
              <input type="text" name="E01LCMIB3" readonly size="37" maxlength="35" value="<%= lcABasic.getE01LCMIB3().trim()%>">
            </td>
            <td nowrap colspan="2"> 
              <input type="text" name="E01LCMBN3I" readonly size="37" maxlength="35" value="<%= lcABasic.getE01LCMBN3().trim()%>"onBlur="document.forms[0].E01LCMBN3.value = document.forms[0].E01LCMBN3I.value">
            </td>
          </tr>
          <tr id="trdark"> 
            <td nowrap colspan="2"> 
              <input type="text" name="E01LCMIB4" readonly size="27" maxlength="25" value="<%= lcABasic.getE01LCMIB4().trim()%>">
            </td>
            <td nowrap colspan="2"> 
              <input type="text" name="E01LCMBN4I" readonly size="27" maxlength="25" value="<%= lcABasic.getE01LCMBN4().trim()%>"onBlur="document.forms[0].E01LCMBN4.value = document.forms[0].E01LCMBN4I.value">
            </td>
          </tr>
          <tr id="trclear"> 
            <td nowrap width="21%"> 
              <div align="left">Cliente / Cuenta No. :</div>
            </td>
            <td nowrap width="21%"> 
              <input type="text" name="E01LCMIBAI" readonly size="14" maxlength="12" value="<%= lcABasic.getE01LCMIBA().trim()%>">
            </td>
            <td nowrap width="30%"> 
              <div align="left">Cliente / Cuenta No. :</div>
            </td>
            <td nowrap width="28%"> 
              <input type="text" name="E01LCMBACI" readonly size="11" maxlength="9" value="<%= lcABasic.getE01LCMBAC().trim()%>"onBlur="document.forms[0].E01LCMBAC.value = document.forms[0].E01LCMBACI.value">
            </td>
          </tr>
          <tr id="trdark"> 
            <td nowrap colspan="2"><b>Banco Confirmador</b></td>
            <td nowrap colspan="2"><b>Banco Corresponsal</b></td>
          </tr>
          <tr id="trclear"> 
            <td nowrap colspan="2"> 
              <input type="text" name="E01LCMAB1I" readonly size="37" maxlength="35" value="<%= lcABasic.getE01LCMAB1().trim()%>"onBlur="document.forms[0].E01LCMAB1.value = document.forms[0].E01LCMAB1I.value">
            </td>
            <td nowrap colspan="2"> 
              <input type="text" name="E01LCMCB1I" readonly size="37" maxlength="35" value="<%= lcABasic.getE01LCMCB1().trim()%>"onBlur="document.forms[0].E01LCMCB1.value = document.forms[0].E01LCMCB1I.value">
            </td>
          </tr>
          <tr id="trdark"> 
            <td nowrap colspan="2"> 
              <input type="text" name="E01LCMAB2I" readonly size="37" maxlength="35" value="<%= lcABasic.getE01LCMAB2().trim()%>"onBlur="document.forms[0].E01LCMAB2.value = document.forms[0].E01LCMAB2I.value">
            </td>
            <td nowrap colspan="2"> 
              <input type="text" name="E01LCMCB2I" readonly size="37" maxlength="35" value="<%= lcABasic.getE01LCMCB2().trim()%>"onBlur="document.forms[0].E01LCMCB2.value = document.forms[0].E01LCMCB2I.value">
            </td>
          </tr>
          <tr id="trclear"> 
            <td nowrap colspan="2"> 
              <input type="text" name="E01LCMAB3I" readonly size="37" maxlength="35" value="<%= lcABasic.getE01LCMAB3().trim()%>""onBlur="document.forms[0].E01LCMAB3.value = document.forms[0].E01LCMAB3I.value">
            </td>
            <td nowrap colspan="2"> 
              <input type="text" name="E01LCMCB3I" readonly size="37" maxlength="35" value="<%= lcABasic.getE01LCMCB3().trim()%>"onBlur="document.forms[0].E01LCMCB3.value = document.forms[0].E01LCMCB3I.value">
            </td>
          </tr>
          <tr id="trdark"> 
            <td nowrap colspan="2"> 
              <input type="text" name="E01LCMAB4I" readonly size="27" maxlength="25" value="<%= lcABasic.getE01LCMAB4().trim()%>"onBlur="document.forms[0].E01LCMAB4.value = document.forms[0].E01LCMAB4I.value">
            </td>
            <td nowrap colspan="2"> 
              <input type="text" name="E01LCMCB4I" readonly size="27" maxlength="25" value="<%= lcABasic.getE01LCMCB4().trim()%>"onBlur="document.forms[0].E01LCMCB4.value = document.forms[0].E01LCMCB4I.value">
            </td>
          </tr>
          <tr id="trclear"> 
            <td nowrap colspan="2">&nbsp;</td>
            <td nowrap width="30%">Cliente / Cuenta No. :</td>
            <td nowrap width="28%"> 
              <input type="text" name="E01LCMCORI" readonly size="11" maxlength="9" value="<%= lcABasic.getE01LCMCOR().trim()%>">
            </td>
          </tr>
          <tr id="trdark"> 
            <td nowrap colspan="2"><b>Lugar de Embarque</b></td>
            <td nowrap colspan="2"><b>Lugar de Destino</b></td>
          </tr>
          <tr id="trclear"> 
            <td nowrap colspan="2"> 
              <input type="text" name="E01LCMSHFI" readonly size="37" maxlength="35" value="<%= lcABasic.getE01LCMSHF().trim()%>"onBlur="document.forms[0].E01LCMSHF.value = document.forms[0].E01LCMSHFI.value">
            </td>
            <td nowrap colspan="2"> 
              <input type="text" name="E01LCMSHTI" readonly size="37" maxlength="35" value="<%= lcABasic.getE01LCMSHT().trim()%>"onBlur="document.forms[0].E01LCMSHT.value = document.forms[0].E01LCMSHTI.value">
            </td>
          </tr>
          <tr id="trdark"> 
            <td nowrap colspan="2"> 
              <input type="text" name="E01LCMSF2I" readonly size="37" maxlength="35" value="<%= lcABasic.getE01LCMSF2().trim()%>"onBlur="document.forms[0].E01LCMSF2.value = document.forms[0].E01LCMSF2I.value">
            </td>
            <td nowrap colspan="2"> 
              <input type="text" name="E01LCMST2I" readonly size="37" maxlength="35" value="<%= lcABasic.getE01LCMST2().trim()%>"onBlur="document.forms[0].E01LCMST2.value = document.forms[0].E01LCMST2I.value">
            </td>
          </tr>
          <tr id="trclear"> 
            <td nowrap colspan="2"> 
              <input type="text" name="E01LCMSF3I" readonly size="37" maxlength="35" value="<%= lcABasic.getE01LCMSF3().trim()%>"onBlur="document.forms[0].E01LCMSF3.value = document.forms[0].E01LCMSF3I.value">
            </td>
            <td nowrap colspan="2"> 
              <input type="text" name="E01LCMST3I" readonly size="37" maxlength="35" value="<%= lcABasic.getE01LCMST3().trim()%>"onBlur="document.forms[0].E01LCMST3.value = document.forms[0].E01LCMST3I.value">
            </td>
          </tr>
          <tr id="trdark"> 
            <td nowrap width="21%"> 
              <div align="right">Ultimo D&iacute;a de Embarque:</div>
            </td>
            <td nowrap width="21%"> 
              <input type="text" name="E01LCMSDMI" readonly size="4" maxlength="2" value="<%= lcABasic.getE01LCMSDM().trim()%>"onBlur="document.forms[0].E01LCMSDM.value = document.forms[0].E01LCMSDMI.value">
              <input type="text" name="E01LCMSDDI" readonly size="4" maxlength="2" value="<%= lcABasic.getE01LCMSDD().trim()%>"onBlur="document.forms[0].E01LCMSDD.value = document.forms[0].E01LCMSDDI.value">
              <input type="text" name="E01LCMSDYI" readonly size="4" maxlength="2" value="<%= lcABasic.getE01LCMSDY().trim()%>"onBlur="document.forms[0].E01LCMSDY.value = document.forms[0].E01LCMSDYI.value">
            </td>
            <td nowrap width="30%"> 
              <div align="right">Marcado para embarque:</div>
            </td>
            <td nowrap width="28%"> 
              <select name="select5">
              </select>
            </td>
          </tr>
          <tr id="trclear"> 
            <td nowrap width="21%"> 
              <div align="right">Embarques Parciales:</div>
            </td>
            <td nowrap width="21%"> 
              <input type="radio" name="radiobutton" value="radiobutton" checked>
              Perm. 
              <input type="radio" name="radiobutton" value="radiobutton">
              No Perm.</td>
            <td nowrap width="30%"> 
              <div align="right">Transporte-Embarques:</div>
            </td>
            <td nowrap width="28%"> 
              <input type="radio" name="radiobutton" value="radiobutton" checked>
              Perm. 
              <input type="radio" name="radiobutton" value="radiobutton">
              No Perm.</td>
          </tr>
          <tr id="trdark"> 
            <td nowrap colspan="4"> 
              <div align="center"><b>Mercanc&iacute;a</b> </div>
            </td>
          </tr>
          <tr id="trclear"> 
            <td nowrap colspan="4"> 
              <div align="center"> 
                <textarea wrap=PHYSICAL name="E01LCMME" rows=10 cols=64>
</textarea>
              </div>
            </td>
          </tr>
        </table>
      </td>
    </tr>
  </table>
<%}%>
<% lcABasic.getE01LCMTYP().equals("O")){ %>
  <H4>Informaci&oacute;n Adicional</H4>
  <table class="tableinfo">
    <tr > 
      <td nowrap> 
        <table cellspacing="0" cellpadding="2" width="100%" border="0">
          <tr id="trdark"> 
            <td nowrap colspan="2"> 
              <div align="left"><b>Aplicante / Ordenante</b></div>
            </td>
            <td nowrap colspan="2"><b>Beneficiario</b></td>
          </tr>
          <tr id="trclear"> 
            <td nowrap colspan="2"> 
              <div align="left"> 
                <input type="text" name="E01LCMAP1" readonly size="37" maxlength="35" value="<%= lcABasic.getE01LCMAP1().trim()%>">
              </div>
            </td>
            <td nowrap colspan="2"> 
              <input type="text" name="E01LCMBN1O" readonly size="37" maxlength="35" value="<%= lcABasic.getE01LCMBN1().trim()%>"onBlur="document.forms[0].E01LCMBN1.value = document.forms[0].E01LCMBN1O.value">
            </td>
          </tr>
          <tr id="trdark"> 
            <td nowrap colspan="2"> 
              <input type="text" name="E01LCMAP2" readonly size="37" maxlength="35" value="<%= lcABasic.getE01LCMAP2().trim()%>">
            </td>
            <td nowrap colspan="2"> 
              <input type="text" name="E01LCMBN2O" readonly size="37" maxlength="35" value="<%= lcABasic.getE01LCMBN2().trim()%>"onBlur="document.forms[0].E01LCMBN2.value = document.forms[0].E01LCMBN2O.value">
            </td>
          </tr>
          <tr id="trclear"> 
            <td nowrap colspan="2"> 
              <input type="text" name="E01LCMAP3" readonly size="37" maxlength="35" value="<%= lcABasic.getE01LCMAP3().trim()%>">
            </td>
            <td nowrap colspan="2"> 
              <input type="text" name="E01LCMBN3O" readonly size="37" maxlength="35" value="<%= lcABasic.getE01LCMBN3().trim()%>"onBlur="document.forms[0].E01LCMBN3.value = document.forms[0].E01LCMBN3O.value">
            </td>
          </tr>
          <tr id="trdark"> 
            <td nowrap colspan="2"> 
              <input type="text" name="E01LCMAP4" readonly size="27" maxlength="25" value="<%= lcABasic.getE01LCMAP4().trim()%>">
            </td>
            <td nowrap colspan="2"> 
              <input type="text" name="E01LCMBN4O" readonly size="27" maxlength="25" value="<%= lcABasic.getE01LCMBN4().trim()%>"onBlur="document.forms[0].E01LCMBN4.value = document.forms[0].E01LCMBN4O.value">
            </td>
          </tr>
          <tr id="trclear"> 
            <td nowrap width="21%"> 
              <div align="left">Cliente / Cuenta No. :</div>
            </td>
            <td nowrap width="21%"> 
              <input type="text" name="E01LCMAPAO" readonly size="14" maxlength="12" value="<%= lcABasic.getE01LCMAPA().trim()%>">
            </td>
            <td nowrap width="30%"> 
              <div align="left">Cliente / Cuenta No. :</div>
            </td>
            <td nowrap width="28%"> 
              <input type="text" name="E01LCMBACO" readonly size="11" maxlength="9" value="<%= lcABasic.getE01LCMBAC().trim()%>"onBlur="document.forms[0].E01LCMBAC.value = document.forms[0].E01LCMBACO.value">
            </td>
          </tr>
          <tr id="trdark"> 
            <td nowrap colspan="2"><b>Banco Confirmador</b></td>
            <td nowrap colspan="2"><b>Banco Corresponsal</b></td>
          </tr>
          <tr id="trclear"> 
            <td nowrap colspan="2"> 
              <input type="text" name="E01LCMAB1O" readonly size="37" maxlength="35" value="<%= lcABasic.getE01LCMAB1().trim()%>"onBlur="document.forms[0].E01LCMAB1.value = document.forms[0].E01LCMAB1O.value">
            </td>
            <td nowrap colspan="2"> 
              <input type="text" name="E01LCMCB1O" readonly size="37" maxlength="35" value="<%= lcABasic.getE01LCMCB1().trim()%>"onBlur="document.forms[0].E01LCMCB1.value = document.forms[0].E01LCMCB1O.value">
            </td>
          </tr>
          <tr id="trdark"> 
            <td nowrap colspan="2"> 
              <input type="text" name="E01LCMAB2O" readonly size="37" maxlength="35" value="<%= lcABasic.getE01LCMAB2().trim()%>"onBlur="document.forms[0].E01LCMAB2.value = document.forms[0].E01LCMAB2O.value">
            </td>
            <td nowrap colspan="2"> 
              <input type="text" name="E01LCMCB2O" readonly size="37" maxlength="35" value="<%= lcABasic.getE01LCMCB2().trim()%>"onBlur="document.forms[0].E01LCMCB2.value = document.forms[0].E01LCMCB2O.value">
            </td>
          </tr>
          <tr id="trclear"> 
            <td nowrap colspan="2"> 
              <input type="text" name="E01LCMAB3O" readonly size="37" maxlength="35" value="<%= lcABasic.getE01LCMAB3().trim()%>""onBlur="document.forms[0].E01LCMAB3.value = document.forms[0].E01LCMAB3O.value">
            </td>
            <td nowrap colspan="2"> 
              <input type="text" name="E01LCMCB3O" readonly size="37" maxlength="35" value="<%= lcABasic.getE01LCMCB3().trim()%>"onBlur="document.forms[0].E01LCMCB3.value = document.forms[0].E01LCMCB3O.value">
            </td>
          </tr>
          <tr id="trdark"> 
            <td nowrap colspan="2"> 
              <input type="text" name="E01LCMAB4O" readonly size="27" maxlength="25" value="<%= lcABasic.getE01LCMAB4().trim()%>"onBlur="document.forms[0].E01LCMAB4.value = document.forms[0].E01LCMAB4O.value">
            </td>
            <td nowrap colspan="2"> 
              <input type="text" name="E01LCMCB4O" readonly size="27" maxlength="25" value="<%= lcABasic.getE01LCMCB4().trim()%>"onBlur="document.forms[0].E01LCMCB4.value = document.forms[0].E01LCMCB4O.value">
            </td>
          </tr>
          <tr id="trclear"> 
            <td nowrap colspan="2">&nbsp;</td>
            <td nowrap width="30%">Cliente / Cuenta No. :</td>
            <td nowrap width="28%"> 
              <input type="text" name="E01LCMCORI" readonly size="11" maxlength="9" value="<%= lcABasic.getE01LCMCOR().trim()%>">
            </td>
          </tr>
          <tr id="trdark"> 
            <td nowrap colspan="2"><b>Lugar de Embarque</b></td>
            <td nowrap colspan="2"><b>Lugar de Destino</b></td>
          </tr>
          <tr id="trclear"> 
            <td nowrap colspan="2"> 
              <input type="text" name="E01LCMSHFO" readonly size="37" maxlength="35" value="<%= lcABasic.getE01LCMSHF().trim()%>"onBlur="document.forms[0].E01LCMSHF.value = document.forms[0].E01LCMSHFO.value">
            </td>
            <td nowrap colspan="2"> 
              <input type="text" name="E01LCMSHTO" readonly size="37" maxlength="35" value="<%= lcABasic.getE01LCMSHT().trim()%>"onBlur="document.forms[0].E01LCMSHT.value = document.forms[0].E01LCMSHTO.value">
            </td>
          </tr>
          <tr id="trdark"> 
            <td nowrap colspan="2"> 
              <input type="text" name="E01LCMSF2O" readonly size="37" maxlength="35" value="<%= lcABasic.getE01LCMSF2().trim()%>"onBlur="document.forms[0].E01LCMSF2.value = document.forms[0].E01LCMSF2O.value">
            </td>
            <td nowrap colspan="2"> 
              <input type="text" name="E01LCMST2O" readonly size="37" maxlength="35" value="<%= lcABasic.getE01LCMST2().trim()%>"onBlur="document.forms[0].E01LCMST2.value = document.forms[0].E01LCMST2O.value">
            </td>
          </tr>
          <tr id="trclear"> 
            <td nowrap colspan="2"> 
              <input type="text" name="E01LCMSF3O" readonly size="37" maxlength="35" value="<%= lcABasic.getE01LCMSF3().trim()%>"onBlur="document.forms[0].E01LCMSF3.value = document.forms[0].E01LCMSF3O.value">
            </td>
            <td nowrap colspan="2"> 
              <input type="text" name="E01LCMST3O" readonly size="37" maxlength="35" value="<%= lcABasic.getE01LCMST3().trim()%>"onBlur="document.forms[0].E01LCMST3.value = document.forms[0].E01LCMST3O.value">
            </td>
          </tr>
          <tr id="trdark"> 
            <td nowrap width="21%"> 
              <div align="right">Ultimo D&iacute;a de Embarque:</div>
            </td>
            <td nowrap width="21%"> 
              <input type="text" name="E01LCMSDMO" readonly size="4" maxlength="2" value="<%= lcABasic.getE01LCMSDM().trim()%>"onBlur="document.forms[0].E01LCMSDM.value = document.forms[0].E01LCMSDMO.value">
              <input type="text" name="E01LCMSDDO" readonly size="4" maxlength="2" value="<%= lcABasic.getE01LCMSDD().trim()%>"onBlur="document.forms[0].E01LCMSDD.value = document.forms[0].E01LCMSDDO.value">
              <input type="text" name="E01LCMSDYO" readonly size="4" maxlength="2" value="<%= lcABasic.getE01LCMSDY().trim()%>"onBlur="document.forms[0].E01LCMSDY.value = document.forms[0].E01LCMSDYO.value">
            </td>
            <td nowrap width="30%"> 
              <div align="right">Marcado para embarque:</div>
            </td>
            <td nowrap width="28%"> 
              <select name="select5">
              </select>
            </td>
          </tr>
          <tr id="trclear"> 
            <td nowrap width="21%"> 
              <div align="right">Embarques Parciales:</div>
            </td>
            <td nowrap width="21%"> 
              <input type="radio" name="radiobutton" value="radiobutton" checked>
              Perm. 
              <input type="radio" name="radiobutton" value="radiobutton">
              No Perm.</td>
            <td nowrap width="30%"> 
              <div align="right">Transporte-Embarques:</div>
            </td>
            <td nowrap width="28%"> 
              <input type="radio" name="radiobutton" value="radiobutton" checked>
              Perm. 
              <input type="radio" name="radiobutton" value="radiobutton">
              No Perm.</td>
          </tr>
          <tr id="trdark"> 
            <td nowrap colspan="4"> 
              <div align="center"><b>Mercanc&iacute;a</b> </div>
            </td>
          </tr>
          <tr id="trclear"> 
            <td nowrap colspan="4"> 
              <div align="center"> 
                <textarea wrap=PHYSICAL name="E01LCMME" rows=10 cols=64>
</textarea>
              </div>
            </td>
          </tr>
        </table>
      </td>
    </tr>
  </table>
 <%}%> 
  <H4>Cr&eacute;dito Disponible Seg&uacute;n Giros</H4>
  <table class="tableinfo">
    <tr > 
      <td nowrap> 
        <table cellspacing="0" cellpadding="2" width="100%" border="0">
          <tr id="trdark"> 
            <td nowrap width="7%"> 
              <div align="right">Por :</div>
            </td>
            <td nowrap width="9%"><font face="Arial" size="2"> 
              <input type="text" name="E01LCMPC1" readonly size="5" maxlength="3" value="<%= lcABasic.getE01LCMPC1().trim()%>">
              %</font> </td>
            <td nowrap width="16%"> 
              <div align="right"><font face="Arial" size="2">Valor de Factura 
                : </font></div>
            </td>
            <td nowrap width="11%"><font face="Arial" size="2"> 
              <input type="text" name="E01LCMDY1" readonly size="5" maxlength="3" value="<%= lcABasic.getE01LCMDY1().trim()%>">
              </font></td>
            <td nowrap colspan="2"> 
              <div align="left"><font face="Arial" size="2">Girado a : </font> 
              </div>
              <div align="left"><font face="Arial" size="2"> </font></div>
            </td>
          </tr>
          <tr id="trclear"> 
            <td nowrap width="7%"> 
              <div align="right"></div>
            </td>
            <td nowrap width="9%"><font face="Arial" size="2"> 
              <input type="text" name="E01LCMPC2" readonly size="5" maxlength="3" value="<%= lcABasic.getE01LCMPC2().trim()%>">
              %</font> </td>
            <td nowrap width="16%"> 
              <div align="right"><font face="Arial" size="2">Valor de Factura 
                : </font></div>
            </td>
            <td nowrap width="11%"><font face="Arial" size="2"> 
              <input type="text" name="E01LCMDY2" readonly size="5" maxlength="3" value="<%= lcABasic.getE01LCMDY2().trim()%>">
              </font></td>
            <td nowrap colspan="2"><font face="Arial" size="2"> 
              <input type="text" name="E01LCMDRW" readonly size="42" maxlength="40" value="<%= lcABasic.getE01LCMDRW().trim()%>">
              </font><font face="Arial" size="2"> </font></td>
          </tr>
          <tr id="trdark"> 
            <td nowrap colspan="6" height="23"> 
              <div align="center"><b>Documentos Requeridos</b></div>
            </td>
          </tr>
        </table>
        <table cellspacing="0" cellpadding="2" width="100%" border="1" bordercolor="#000000">
          <tr bordercolor="#FFFFFF"> 
            <td nowrap> 
              <table cellspacing="0" cellpadding="2" width="100%" border="0">
                <tr id="trdark"> 
                  <td nowrap width="8%"> 
                    <div align="center">C&oacute;digo </div>
                  </td>
                  <td nowrap width="81%"> 
                    <div align="center">Descripci&oacute;n </div>
                  </td>
                  <td nowrap width="5%"> 
                    <div align="center">Original</div>
                  </td>
                  <td nowrap width="6%"> 
                    <div align="center">Copia </div>
                  </td>
                </tr>
                <% if(!lcABasic.getE01LCMDD1().trim().equals("")){%> 
                <tr id="trclear"> 
                  <td nowrap width="8%"> 
                    <div align="left"></div>
                    <%= lcABasic.getE01LCMDD1().trim()%></td>
                  <td nowrap width="81%"> 
                    <div align="left"></div>
                    <%= lcABasic.getE01LCMDS1().trim()%></td>
                  <td nowrap width="5%"> 
                    <div align="center"></div>
                    <%= lcABasic.getE01LCMD01().charAt(0)%></td>
                  <td nowrap width="6%"> 
                    <div align="center"></div>
                    <% try { out.print(lcABasic.getE01LCMD01().charAt(1)); } catch (Exception e) { out.print("0"); }%></td>
                </tr>
                <%}%> <% if(!lcABasic.getE01LCMDD2().trim().equals("")){%> 
                <tr id="trdark"> 
                  <td nowrap width="8%"> 
                    <div align="left"></div>
                    <%= lcABasic.getE01LCMDD2().trim()%></td>
                  <td nowrap width="81%"> 
                    <div align="left"></div>
                    <%= lcABasic.getE01LCMDS2().trim()%></td>
                  <td nowrap width="5%"> 
                    <div align="center"></div>
                    <%= lcABasic.getE01LCMD02().charAt(0)%></td>
                  <td nowrap width="6%"> 
                    <div align="center"></div>
                    <% try { out.print(lcABasic.getE01LCMD02().charAt(1)); } catch (Exception e) { out.print("0"); }%></td>
                </tr>
                <%}%> <% if(!lcABasic.getE01LCMDD3().trim().equals("")){%> 
                <tr id="trclear"> 
                  <td nowrap width="8%"> 
                    <div align="left"></div>
                    <%= lcABasic.getE01LCMDD3().trim()%></td>
                  <td nowrap width="81%"> 
                    <div align="left"></div>
                    <%= lcABasic.getE01LCMDS3().trim()%></td>
                  <td nowrap width="5%"> 
                    <div align="center"></div>
                    <%= lcABasic.getE01LCMD03().charAt(0)%></td>
                  <td nowrap width="6%"> 
                    <div align="center"></div>
                    <% try { out.print(lcABasic.getE01LCMD03().charAt(1)); } catch (Exception e) { out.print("0"); }%></td>
                </tr>
                <%}%> <% if(!lcABasic.getE01LCMDD4().trim().equals("")){%> 
                <tr id="trdark"> 
                  <td nowrap width="8%"> 
                    <div align="left"></div>
                    <%= lcABasic.getE01LCMDD4().trim()%></td>
                  <td nowrap width="81%"> 
                    <div align="left"></div>
                    <%= lcABasic.getE01LCMDS4().trim()%></td>
                  <td nowrap width="5%"> 
                    <div align="center"></div>
                    <%= lcABasic.getE01LCMD04().charAt(0)%></td>
                  <td nowrap width="6%"> 
                    <div align="center"></div>
                    <% try { out.print(lcABasic.getE01LCMD04().charAt(1)); } catch (Exception e) { out.print("0"); }%></td>
                </tr>
                <%}%> <% if(!lcABasic.getE01LCMDD5().trim().equals("")){%> 
                <tr id="trclear"> 
                  <td nowrap width="8%"> 
                    <div align="left"></div>
                    <%= lcABasic.getE01LCMDD5().trim()%></td>
                  <td nowrap width="81%"> 
                    <div align="left"></div>
                    <%= lcABasic.getE01LCMDS5().trim()%></td>
                  <td nowrap width="5%"> 
                    <div align="center"></div>
                    <%= lcABasic.getE01LCMD05().charAt(0)%></td>
                  <td nowrap width="6%"> 
                    <div align="center"></div>
                    <% try { out.print(lcABasic.getE01LCMD05().charAt(1)); } catch (Exception e) { out.print("0"); }%></td>
                </tr>
                <%}%> <% if(!lcABasic.getE01LCMDD6().trim().equals("")){%> 
                <tr id="trdark"> 
                  <td nowrap width="8%"> 
                    <div align="left"></div>
                    <%= lcABasic.getE01LCMDD6().trim()%></td>
                  <td nowrap width="81%"> 
                    <div align="left"></div>
                    <%= lcABasic.getE01LCMDS6().trim()%></td>
                  <td nowrap width="5%"> 
                    <div align="center"></div>
                    <%= lcABasic.getE01LCMD06().charAt(0)%></td>
                  <td nowrap width="6%"> 
                    <div align="center"></div>
                    <% try { out.print(lcABasic.getE01LCMD06().charAt(1)); } catch (Exception e) { out.print("0"); }%></td>
                </tr>
                <%}%> <% if(!lcABasic.getE01LCMDD7().trim().equals("")){%> 
                <tr id="trclear"> 
                  <td nowrap width="8%"> 
                    <div align="left"></div>
                    <%= lcABasic.getE01LCMDD7().trim()%></td>
                  <td nowrap width="81%"> 
                    <div align="left"></div>
                    <%= lcABasic.getE01LCMDS7().trim()%></td>
                  <td nowrap width="5%"> 
                    <div align="center"></div>
                    <%= lcABasic.getE01LCMD07().charAt(0)%></td>
                  <td nowrap width="6%"> 
                    <div align="center"></div>
                    <% try { out.print(lcABasic.getE01LCMD07().charAt(1)); } catch (Exception e) { out.print("0"); }%></td>
                </tr>
                <%}%> <% if(!lcABasic.getE01LCMDD8().trim().equals("")){%> 
                <tr id="trdark"> 
                  <td nowrap width="8%"> 
                    <div align="left"></div>
                    <%= lcABasic.getE01LCMDD8().trim()%></td>
                  <td nowrap width="81%"> 
                    <div align="left"></div>
                    <%= lcABasic.getE01LCMDS8().trim()%></td>
                  <td nowrap width="5%"> 
                    <div align="center"></div>
                    <%= lcABasic.getE01LCMD08().charAt(0)%></td>
                  <td nowrap width="6%"> 
                    <div align="center"></div>
                    <% try { out.print(lcABasic.getE01LCMD08().charAt(1)); } catch (Exception e) { out.print("0"); }%></td>
                </tr>
                <%}%> <% if(!lcABasic.getE01LCMDD9().trim().equals("")){%> 
                <tr id="trclear"> 
                  <td nowrap width="8%"> 
                    <div align="left"></div>
                    <%= lcABasic.getE01LCMDD9().trim()%></td>
                  <td nowrap width="81%"> 
                    <div align="left"></div>
                    <%= lcABasic.getE01LCMDS9().trim()%></td>
                  <td nowrap width="5%"> 
                    <div align="center"></div>
                    <%= lcABasic.getE01LCMD09().charAt(0)%></td>
                  <td nowrap width="6%"> 
                    <div align="center"></div>
                    <% try { out.print(lcABasic.getE01LCMD09().charAt(1)); } catch (Exception e) { out.print("0"); }%></td>
                </tr>
                <%}%> 
              </table>
            </td>
          </tr>
        </table>
        
      </td>
    </tr>
  </table>
  <p>
  <div align="center"> 
    <input id="EIBSBTN" type=submit name="Submit" value="Enviar">
  </div>
</p>
</form>
</body>
</html>
