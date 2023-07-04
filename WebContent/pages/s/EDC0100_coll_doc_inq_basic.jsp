<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.0 Transitional//EN">
<html>
<head>
<title>Consulta de Cobranzas Documentarias</title>
<META HTTP-EQUIV="Content-Type" CONTENT="text/html; charset=ISO-8859-1">
<META name="GENERATOR" content="IBM WebSphere Page Designer V3.5.2 for Windows">
<META http-equiv="Content-Style-Type" content="text/css">
<link href="<%=request.getContextPath()%>/pages/style.css" rel="stylesheet">


<jsp:useBean id= "error" class= "datapro.eibs.beans.ELEERRMessage"  scope="session" />

<jsp:useBean id= "userPO" class= "datapro.eibs.beans.UserPos"  scope="session" />

<jsp:useBean id="collBasic" class="datapro.eibs.beans.EDC010001Message"  scope="session"/>

<script language="Javascript1.1" src="<%=request.getContextPath()%>/pages/s/javascripts/eIBS.jsp"> </SCRIPT>
<script language="Javascript1.1" src="<%=request.getContextPath()%>/pages/s/javascripts/optMenu.jsp"> </SCRIPT>

<SCRIPT Language="Javascript">

   builtNewMenu(coll_i_opt);

</SCRIPT>

</head>

<body bgcolor="#FFFFFF">

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

<H3 align="center">Consulta de Cobranzas Documentarias<img src="<%=request.getContextPath()%>/images/e_ibs.gif" align="left" name="EIBS_GIF" ALT="coll_doc_inq_basic,EDC0100"></h3>
<hr size="4">
<form method="post">
  <table class="tableinfo">
    <tr > 
      <td nowrap> 
        <table cellspacing="0" cellpadding="2" width="100%" border="0" class="tbhead">
          <tr id="trdark"> 
            <td nowrap width="14%" > 
              <div align="right"><b>Cliente :</b></div>
            </td>
            <td nowrap width="9%" > 
              <div align="left"> 
                <input type="text" name="E02CUN2" size="9" maxlength="9" readonly value="<%= userPO.getHeader2().trim()%>">
              </div>
            </td>
            <td nowrap width="12%" > 
              <div align="right"><b>Nombre :</b> </div>
            </td>
            <td nowrap > 
              <div align="left"> 
                <input type="text" name="E02NA12" size="45" maxlength="45" readonly value="<%= userPO.getHeader3().trim()%>">
              </div>
            </td>
            <td nowrap > 
              <div align="right"><b>Producto : </b></div>
            </td>
            <td nowrap ><b> 
              <input type="text" name="E02PRO2" size="4" maxlength="4" readonly value="<%= userPO.getHeader1().trim()%>">
              </b></td>
          </tr>
          <tr id="trdark"> 
            <td nowrap width="14%"> 
              <div align="right"><b>Cuenta :</b></div>
            </td>
            <td nowrap width="9%"> 
              <div align="left"> 
                <input type="text" name="E02ACC" size="12" maxlength="12" value="<%= userPO.getIdentifier().trim()%>" readonly>
              </div>
            </td>
            <td nowrap width="12%"> 
              <div align="right">Oficial :</div>
            </td>
            <td nowrap width="33%"> 
              <div align="left"><b> 
                <input type="text" name="E02NA122" size="30" maxlength="30" readonly value="<%= userPO.getOfficer().trim()%>">
                </b> </div>
            </td>
            <td nowrap width="11%"> 
              <div align="right"><b>Moneda : </b></div>
            </td>
            <td nowrap width="21%"> 
              <div align="left"><b> 
                <input type="text" name="E01DEACCY" size="3" maxlength="3" value="<%= userPO.getCurrency().trim()%>" readonly>
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
            <td nowrap width="29%"> 
              <div align="right">Monto Original :</div>
            </td>
            <td nowrap width="18%"> 
              <input type="text"  name="E01DCMOAM" readonly size="16" maxlength="15" value="<%= Util.formatCCY(collBasic.getE01DCMOAM().trim())%>">
            </td>
            <td nowrap width="27%"> 
              <div align="right">Fecha de Emisi&oacute;n :</div>
            </td>
            <td nowrap width="26%"> 
              <input type="text" name="E01DCMID1" readonly size="2" maxlength="2" value="<%= collBasic.getE01DCMID1().trim()%>">
              <input type="text" name="E01DCMID2" readonly size="2" maxlength="2" value="<%= collBasic.getE01DCMID2().trim()%>">
              <input type="text" name="E01DCMID3" readonly size="2" maxlength="2" value="<%= collBasic.getE01DCMID3().trim()%>">
            </td>
          </tr>
          <tr id="trclear"> 
            <td nowrap width="29%"> 
              <div align="right">Saldo de la Cobranza :</div>
            </td>
            <td nowrap width="18%"> 
              <input type="text"  name="E01DCMMEB" readonly size="16" maxlength="15" value="<%= collBasic.getE01DCMMEB().trim()%>">
            </td>
            <td nowrap width="27%"> 
              <div align="right">Fecha de Vencimiento :</div>
            </td>
            <td nowrap width="26%"> 
              <input type="text" name="E01DCMMA1" readonly size="2" maxlength="2" value="<%= collBasic.getE01DCMMA1().trim()%>">
              <input type="text" name="E01DCMMA2" readonly size="2" maxlength="2" value="<%= collBasic.getE01DCMMA2().trim()%>">
              <input type="text" name="E01DCMMA3" readonly size="2" maxlength="2" value="<%= collBasic.getE01DCMMA3().trim()%>">
            </td>
          </tr>
          <tr id="trdark"> 
            <td nowrap width="29%" height="20"> 
              <div align="right">Tipo de Cobranza :</div>
            </td>
            <td nowrap width="18%" height="20"> 
              <input type="text" name="E01DCMTYP" readonly size="2" maxlength="1" value="<%= collBasic.getE01DCMTYP().trim()%>">
            </td>
            <td nowrap width="27%" height="20"> 
              <div align="right">Estado de la Cobranza :</div>
            </td>
            <td nowrap width="26%" height="20"> 
              <input type="text" name="E01DCMCST " size="30" 
				  value="<% if (collBasic.getE01DCMCST().equals("0")) out.print("Esperando Confirmación Recibida");
							else if (collBasic.getE01DCMCST().equals("1")) out.print("Pendiente de Aceptación");
							else if (collBasic.getE01DCMCST().equals("2")) out.print("Pendiente de Pago");
							else if (collBasic.getE01DCMCST().equals("3")) out.print("Aceptada");
							else if (collBasic.getE01DCMCST().equals("4")) out.print("Protestada Vigente");
							else if (collBasic.getE01DCMCST().equals("5")) out.print("Prorrogada/Extendida");
							else if (collBasic.getE01DCMCST().equals("6")) out.print("Cancelada - Libre de Pago");
							else if (collBasic.getE01DCMCST().equals("7")) out.print("Cancelada - Devuelta");
							else if (collBasic.getE01DCMCST().equals("8")) out.print("Cerrada Parcialmente Pagada");
							else if (collBasic.getE01DCMCST().equals("9")) out.print("Cerrada Completamente Pagada");
							else if (collBasic.getE01DCMCST().equals("P")) out.print("Vencida Protestada");
							else if (collBasic.getE01DCMCST().equals("V")) out.print("Vencida sin Protestar");%>"
				readonly>
            </td>
          </tr>
          <tr id="trclear"> 
            <td nowrap width="29%" height="19"> 
              <div align="right">Entregar Documentos Contra :</div>
            </td>
            <td nowrap width="18%" height="19"> 
              <input type="text" name="E01DCMDRF" readonly size="16" maxlength="16" value="<%= collBasic.getE01DCMDRF().trim()%>">
            </td>
            <td nowrap width="27%" height="19"> 
              <div align="right">Pagos Parciales :</div>
            </td>
            <td nowrap width="26%" height="19"> 
              <input type="text" name="E01DCMPPF" size="3" 
				  value="<% if (collBasic.getE01DCMPPF().equals("Y")) out.print("Sí");
							else out.print("No");%>" 
				readonly>
            </td>
          </tr>
          <tr id="trdark"> 
            <td nowrap width="29%" height="19"> 
              <div align="right">Avisar No Aceptaci&oacute;n V&iacute;a :</div>
            </td>
            <td nowrap width="18%" height="19"> 
              <input type="text" name="E01DCMADF" size="20" 
				  value="<% if (collBasic.getE01DCMADF().equals("T")) out.print("Telex");
							else if (collBasic.getE01DCMADF().equals("S")) out.print("Swift");
							else if (collBasic.getE01DCMADF().equals("A")) out.print("Correo Aereo");
							else if (collBasic.getE01DCMADF().equals("C")) out.print("Courier");
							else out.print("Fax");%>" 
				readonly>
            </td>
            <td nowrap width="27%" height="19"> 
              <div align="right">Avisar Aceptaci&oacute;n V&iacute;a :</div>
            </td>
            <td nowrap width="26%" height="19"> 
              <input type="text" name="E01DCMAPF" size="20" 
				  value="<% if (collBasic.getE01DCMAPF().equals("T")) out.print("Telex");
							else if (collBasic.getE01DCMAPF().equals("S")) out.print("Swift");
							else if (collBasic.getE01DCMAPF().equals("A")) out.print("Correo Aereo");
							else if (collBasic.getE01DCMAPF().equals("C")) out.print("Courier");
							else out.print("Fax");%>" 
				readonly>
            </td>
          </tr>
          <tr id="trclear"> 
            <td nowrap width="29%" height="19"> 
              <div align="right">C&oacute;digo Moneda Extranjera :</div>
            </td>
            <td nowrap width="18%" height="19"> 
              <input type="text"  name="E01DCMFCY" readonly size="4" maxlength="3" value="<%= collBasic.getE01DCMFCY().trim()%>">
            </td>
            <td nowrap width="27%" height="19"> 
              <div align="right">Tipo Cambio Moneda Extranjera : </div>
            </td>
            <td nowrap width="26%" height="19"> 
              <input type="text" name="E01DCMOFX" readonly size="12" maxlength="11" value="<%= collBasic.getE01DCMOFX().trim()%>">
            </td>
          </tr>
          <tr id="trdark"> 
            <td nowrap width="29%" height="19"> 
              <div align="right">Nuestros Cargos por Cuenta de :</div>
            </td>
            <td nowrap width="18%" height="19"> 
              <input type="text" name="E01DCMOCF" size="30" 
				  value="<% if (collBasic.getE01DCMOCF().equals("P")) out.print("Girado (A favor de..)");
							else if (collBasic.getE01DCMOCF().equals("D")) out.print("Girador del Cheque");
							else if (collBasic.getE01DCMOCF().equals("N")) out.print("No Cargos");%>"
				readonly>
            </td>
            <td nowrap width="26%" height="19">
              <div align="right">Sus Cargos por Cuenta de : </div>
            </td>
            <td nowrap width="27%" height="19"> 
              <div align="left"> 
                <input type="text" name="E01DCMOBF2" size="30" 
				  value="<% if (collBasic.getE01DCMOBF().equals("P")) out.print("Girado (A favor de..)");
							else if (collBasic.getE01DCMOBF().equals("D")) out.print("Girador del Cheque");
							else if (collBasic.getE01DCMOBF().equals("N")) out.print("No Cargos");%>"
				readonly/>
            </td>
            <td nowrap width="27%" height="19">
              </div>
            </td>
          </tr>
          <tr id="trclear"> 
            <td nowrap width="29%" height="19"> 
              <div align="right">Condonar Cargos :</div>
            </td>
            <td nowrap width="18%" height="19"> 
              <input type="text" name="E01DCMWCF" size="3" 
				  value="<% if (collBasic.getE01DCMWCF().equals("Y")) out.print("Sí");
							else out.print("No");%>" 
				readonly/>
            </td>
            <td nowrap width="27%" height="19"> 
              <div align="right">Condonar Intereses :</div>
            </td>
            <td nowrap width="26%" height="19"> 
              <input type="text" name="E01DCMWIF" size="3" 
				  value="<% if (collBasic.getE01DCMWIF().equals("Y")) out.print("Sí");
							else out.print("No");%>" 
				readonly>
            </td>
          </tr>
          <tr id="trdark"> 
            <td nowrap width="29%" height="19"> 
              <div align="right">Fecha Remitente :</div>
            </td>
            <td nowrap width="18%" height="19"> 
              <input type="text" name="E01DCMRM12" readonly size="2" maxlength="2" value="<%= collBasic.getE01DCMRM1().trim()%>">
              <input type="text" name="E01DCMRM22" readonly size="2" maxlength="2" value="<%= collBasic.getE01DCMRM2().trim()%>">
              <input type="text" name="E01DCMRM32" readonly size="2" maxlength="2" value="<%= collBasic.getE01DCMRM3().trim()%>">
            </td>
            <td nowrap width="27%" height="19"> 
              <div align="right">Fecha de Aceptaci&oacute;n : </div>
            </td>
            <td nowrap width="26%" height="19"> 
              <input type="text" name="E01DCMAC1" readonly size="2" maxlength="2" value="<%= collBasic.getE01DCMAC1().trim()%>">
              <input type="text" name="E01DCMAC2" readonly size="2" maxlength="2" value="<%= collBasic.getE01DCMAC2().trim()%>">
              <input type="text" name="E01DCMAC3" readonly size="2" maxlength="2" value="<%= collBasic.getE01DCMAC3().trim()%>">
            </td>
          </tr>
          <tr id="trclear"> 
            <td nowrap width="29%" height="19"> 
              <div align="right">Fecha Acuse de Recibo :</div>
            </td>
            <td nowrap width="18%" height="19"> 
              <input type="text" name="E01DCMAK1" readonly size="2" maxlength="2" value="<%= collBasic.getE01DCMAK1().trim()%>">
              <input type="text" name="E01DCMAK2" readonly size="2" maxlength="2" value="<%= collBasic.getE01DCMAK2().trim()%>">
              <input type="text" name="E01DCMAK3" readonly size="2" maxlength="2" value="<%= collBasic.getE01DCMAK3().trim()%>">
            </td>
            <td nowrap width="27%" height="19"> 
              <div align="right">Fecha Ultima Transacci&oacute;n :</div>
            </td>
            <td nowrap width="26%" height="19"> 
              <input type="text" name="E01DCMLR1" readonly size="2" maxlength="2" value="<%= collBasic.getE01DCMLR1().trim()%>">
              <input type="text" name="E01DCMLR2" readonly size="2" maxlength="2" value="<%= collBasic.getE01DCMLR2().trim()%>">
              <input type="text" name="E01DCMLR3" readonly size="2" maxlength="2" value="<%= collBasic.getE01DCMLR3().trim()%>">
            </td>
          </tr>
          <tr id="trdark"> 
            <td nowrap width="29%" height="19"> 
              <div align="right">Protestar :</div>
            </td>
            <td nowrap width="18%" height="19"> 
              <input type="text" name="E01DCMPTF" size="3" 
				  value="<% if (collBasic.getE01DCMPTF().equals("Y")) out.print("Sí");
							else out.print("No");%>" 
				readonly>
            </td>
            <td nowrap width="27%" height="19"> 
              <div align="right">N&uacute;mero Tarifa de Cargos :</div>
            </td>
            <td nowrap width="26%" height="19"> 
              <input type="text" name="E01DCMTAR" readonly size="3" maxlength="2" value="<%= collBasic.getE01DCMTAR().trim()%>">
            </td>
          </tr>
          <tr id="trclear"> 
            <td nowrap width="29%" height="19"> 
              <div align="right">Status :</div>
            </td>
            <td nowrap width="18%" height="19"> 
              <input type="text"  name="E01DCMSTS" readonly size="2" maxlength="1" value="<%= collBasic.getE01DCMSTS().trim()%>">
            </td>
            <td nowrap width="27%" height="19"> 
              <div align="right">Nuestra Referencia :</div>
            </td>
            <td nowrap width="26%" height="19"> 
              <input type="text" name="E01DCMORF" readonly size="16" maxlength="16" value="<%= collBasic.getE01DCMORF().trim()%>">
            </td>
          </tr>
          <tr id="trdark"> 
            <td nowrap width="29%" height="19"> 
              <div align="right">V&iacute;a de Reembolso : </div>
            </td>
            <td nowrap width="18%" height="19"> 
              <input type="text" name="E01DCMADF2" size="25" 
				  value="<% if (collBasic.getE01DCMSRF().equals("T")) out.print("Telex");
							else if (collBasic.getE01DCMSRF().equals("S")) out.print("Swift");
							else if (collBasic.getE01DCMSRF().equals("F")) out.print("Tranfer. FED (USA)");
							else if (collBasic.getE01DCMSRF().equals("C")) out.print("Cheque de Gerencia");
							else if (collBasic.getE01DCMSRF().equals("D")) out.print("Cuenta Corriente");
							else out.print("Cuenta Contable");%>" 
				readonly>
            </td>
            <td nowrap width="27%" height="19"> 
              <div align="right">V&iacute;a de Pago :</div>
            </td>
            <td nowrap width="26%" height="19"> 
              <input type="text" name="E01DCMSPF" size="25" 
				  value="<% if (collBasic.getE01DCMSPF().equals("T")) out.print("Telex");
							else if (collBasic.getE01DCMSPF().equals("S")) out.print("Swift");
							else if (collBasic.getE01DCMSPF().equals("F")) out.print("Tranfer. FED (USA)");
							else if (collBasic.getE01DCMSPF().equals("C")) out.print("Cheque de Gerencia");
							else if (collBasic.getE01DCMSPF().equals("D")) out.print("Cuenta Corriente");
							else out.print("Cuenta Contable");%>" 
				readonly>
            </td>
          </tr>
          <tr id="trclear"> 
            <td nowrap width="29%" height="19"> 
              <div align="right">Cuenta de Reembolso :</div>
            </td>
            <td nowrap width="18%" height="19"> 
              <input type="text" name="E01DCMSRA" readonly size="13" maxlength="12" value="<%= collBasic.getE01DCMSRA().trim()%>">
            </td>
            <td nowrap width="27%" height="19"> 
              <div align="right">Cuenta de Pago :</div>
            </td>
            <td nowrap width="26%" height="19"> 
              <input type="text" name="E01DCMSPA" readonly size="13" maxlength="12" value="<%= collBasic.getE01DCMSPA().trim()%>">
            </td>
          </tr>
          <tr id="trdark"> 
            <td nowrap width="29%" height="19"> 
              <div align="right">Banco / Sucursal :</div>
            </td>
            <td nowrap width="18%" height="19"> 
              <input type="text" name="E01DCMBNK" readonly size="3" maxlength="2" value="<%= collBasic.getE01DCMBNK().trim()%>">
              / 
              <input type="text" name="E01DCMBRN" readonly size="4" maxlength="3" value="<%= collBasic.getE01DCMBRN().trim()%>">
            </td>
            <td nowrap width="27%" height="19"> 
              <div align="right">Cuenta Contable :</div>
            </td>
            <td nowrap width="26%" height="19"> 
              <input type="text" name="E01DCMGLN" readonly size="17" maxlength="16" value="<%= collBasic.getE01DCMGLN().trim()%>">
            </td>
          </tr>
          <tr id="trclear"> 
            <td nowrap width="29%" height="19"> 
              <div align="right">Tipo de Cuenta :</div>
            </td>
            <td nowrap width="18%" height="19"> 
              <input type="text" name="E01DCMSTY" readonly size="2" maxlength="1" value="<%= collBasic.getE01DCMSTY().trim()%>">
            </td>
            <td nowrap width="27%" height="19"> 
              <p align="right">&nbsp;</p>
            </td>
            <td nowrap width="26%" height="19">&nbsp; </td>
          </tr>
        </table>
      </td>
    </tr>
  </table>
  <H4>Informaci&oacute;n Adicional</H4>
  <table class="tableinfo">
    <tr > 
      <td nowrap> 
        <table cellspacing="0" cellpadding="2" width="100%" border="0">
          <tr id="trdark"> 
            <td nowrap colspan="2"> 
              <div align="center"><b>Girador(Exportador)</b></div>
            </td>
            <td nowrap width="12%">&nbsp; </td>
            <td nowrap colspan="2"> 
              <div align="center"><b>Girado (Importador)</b></div>
            </td>
          </tr>
          <tr id="trclear"> 
            <td nowrap colspan="2"> 
              <div align="center"> 
                <input type="text" name="E01DCMDR1" readonly size="40" maxlength="35" value="<%= collBasic.getE01DCMDR1().trim()%>">
              </div>
            </td>
            <td nowrap width="12%">&nbsp; </td>
            <td nowrap colspan="2"> 
              <div align="center">
                <input type="text" name="E01DCMDW1" readonly size="40" maxlength="35" value="<%= collBasic.getE01DCMDW1().trim()%>">
              </div>
            </td>
          </tr>
          <tr id="trdark"> 
            <td nowrap colspan="2"> 
              <div align="center"> 
                <input type="text" name="E01DCMDR2" readonly size="40" maxlength="35" value="<%= collBasic.getE01DCMDR2().trim()%>">
              </div>
            </td>
            <td nowrap width="12%">&nbsp;</td>
            <td nowrap colspan="2"> 
              <div align="center"> 
                <input type="text" name="E01DCMDW2" readonly size="40" maxlength="35" value="<%= collBasic.getE01DCMDW2().trim()%>">
              </div>
            </td>
          </tr>
          <tr id="trclear"> 
            <td nowrap colspan="2"> 
              <div align="center"> 
                <input type="text" name="E01DCMDR3" readonly size="40" maxlength="35" value="<%= collBasic.getE01DCMDR3().trim()%>">
              </div>
            </td>
            <td nowrap width="12%">&nbsp;</td>
            <td nowrap colspan="2"> 
              <div align="center">
                <input type="text" name="E01DCMDW3" readonly size="40" maxlength="35" value="<%= collBasic.getE01DCMDW3().trim()%>">
              </div>
            </td>
          </tr>
          <tr id="trdark"> 
            <td nowrap colspan="2"> 
              <div align="center"> 
                <input type="text" name="E01DCMDR4" readonly size="40" maxlength="35" value="<%= collBasic.getE01DCMDR4().trim()%>">
              </div>
            </td>
            <td nowrap width="12%">&nbsp;</td>
            <td nowrap colspan="2"> 
              <div align="center">
                <input type="text" name="E01DCMDW4" readonly size="40" maxlength="35" value="<%= collBasic.getE01DCMDW4().trim()%>">
              </div>
            </td>
          </tr>
          <tr id="trclear"> 
            <td nowrap width="26%"> 
              <div align="left">Cliente / Cuenta No. :</div>
            </td>
            <td nowrap width="11%"> 
              <input type="text" name="E01DCMDRA" readonly size="13" maxlength="12" value="<%= collBasic.getE01DCMDRA().trim()%>">
            </td>
            <td nowrap width="12%"> 
              <div align="left"> </div>
            </td>
            <td nowrap width="36%"> 
              <div align="left">Cliente / Cuenta No. :</div>
            </td>
            <td nowrap width="15%"> 
              <input type="text" name="E01DCMDWA" readonly size="13" maxlength="12" value="<%= collBasic.getE01DCMDWA().trim()%>">
            </td>
          </tr>
          <tr id="trdark"> 
            <td nowrap width="26%">&nbsp;</td>
            <td nowrap width="11%">&nbsp; </td>
            <td nowrap width="12%">&nbsp;</td>
            <td nowrap>Sub Cuenta P / T :</td>
            <td nowrap> 
              <input type="text" name="E01DCMDWS" readonly size="7" maxlength="5" value="<%= collBasic.getE01DCMDWS().trim()%>">
            </td>
          </tr>
          <tr id="trclear"> 
            <td nowrap colspan="2"> 
              <div align="center"><b>Banco Presentador</b></div>
            </td>
            <td nowrap width="12%">&nbsp;</td>
            <td nowrap colspan="2"> 
              <div align="center"><b>Remitido por</b></div>
            </td>
          </tr>
          <tr id="trdark"> 
            <td nowrap colspan="2"> 
              <div align="center"> 
                <input type="text" name="E01DCMCL1" readonly size="40" maxlength="35" value="<%= collBasic.getE01DCMCL1().trim()%>">
              </div>
            </td>
            <td nowrap width="12%">&nbsp;</td>
            <td nowrap colspan="2"> 
              <div align="center">
                <input type="text" name="E01DCMRB1" readonly size="40" maxlength="35" value="<%= collBasic.getE01DCMRB1().trim()%>">
              </div>
            </td>
          </tr>
          <tr id="trclear"> 
            <td nowrap colspan="2"> 
              <div align="center"> 
                <input type="text" name="E01DCMCL2" readonly size="40" maxlength="35" value="<%= collBasic.getE01DCMCL2().trim()%>">
              </div>
            </td>
            <td nowrap width="12%">&nbsp;</td>
            <td nowrap colspan="2"> 
              <div align="center">
                <input type="text" name="E01DCMRB2" readonly size="40" maxlength="35" value="<%= collBasic.getE01DCMRB2().trim()%>">
              </div>
            </td>
          </tr>
          <tr id="trdark"> 
            <td nowrap colspan="2"> 
              <div align="center"> 
                <input type="text" name="E01DCMCL3" readonly size="40" maxlength="35" value="<%= collBasic.getE01DCMCL3().trim()%>">
              </div>
            </td>
            <td nowrap width="12%">&nbsp;</td>
            <td nowrap colspan="2"> 
              <div align="center">
                <input type="text" name="E01DCMRB3" readonly size="40" maxlength="35" value="<%= collBasic.getE01DCMRB3().trim()%>">
              </div>
            </td>
          </tr>
          <tr id="trclear"> 
            <td nowrap colspan="2"> 
              <div align="center"> 
                <input type="text" name="E01DCMCL4" readonly size="40" maxlength="35" value="<%= collBasic.getE01DCMCL4().trim()%>">
              </div>
            </td>
            <td nowrap width="12%">&nbsp;</td>
            <td nowrap colspan="2"> 
              <div align="center"> 
                <input type="text" name="E01DCMRB4" readonly size="40" maxlength="35" value="<%= collBasic.getE01DCMRB4().trim()%>">
              </div>
            </td>
          </tr>
          <tr id="trdark"> 
            <td nowrap width="26%">Cliente / Cuenta No. :</td>
            <td nowrap width="11%"> 
              <input type="text" name="E01DCMCLA" readonly size="13" maxlength="12" value="<%= collBasic.getE01DCMCLA().trim()%>">
            </td>
            <td nowrap width="12%">&nbsp;</td>
            <td nowrap width="36%">Cliente / Cuenta No. :</td>
            <td nowrap width="15%">
              <input type="text" name="E01DCMRBA" readonly size="13" maxlength="12" value="<%= collBasic.getE01DCMRBA().trim()%>">
            </td>
          </tr>
          <tr id="trclear"> 
            <td nowrap width="26%">&nbsp;</td>
            <td nowrap width="11%">&nbsp; </td>
            <td nowrap width="12%">&nbsp;</td>
            <td nowrap>Referencia :</td>
            <td nowrap>
              <input type="text" name="E01DCMRRF" readonly size="17" maxlength="16" value="<%= collBasic.getE01DCMRRF().trim()%>">
            </td>
          </tr>
        </table>
        <table width="100%">
          <tr id="trdark"> 
            <td width="37%"><b>Mercanc&iacute;a / Embarque</b></td>
            <td width="63%">&nbsp;</td>
          </tr>
			<tr id="trclear">
            <td nowrap >
             	<div align="right">
				<DIV align="right">Descripción :</DIV>
            </td>
            <td nowrap colspan=3><INPUT type="text" name="E01DCMMED" 	maxlength="35" size="50" value="<%= collBasic.getE01DCMMED().trim()%>" ></td>
		  </tr>
		  <tr id="trdark">
			<td nowrap>
             	<div align="right">Nombre del Navío :</div>
			</td>
            <td nowrap colspan=3>
            	<INPUT type="text" name="E01DCMVSL" maxlength="20" size="35" value="<%= collBasic.getE01DCMVSL().trim()%>" >            	
            </td>
          </tr>
          <tr id="trclear"> 
            <td width="37%"> 
              <div align="right">Tenor :</div>
            </td>
            <td width="63%"> 
              <input type="text" name="E01DCMDYS" readonly size="4" maxlength="3" value="<%= collBasic.getE01DCMDYS().trim()%>">
              <input type="text" name="E01DCMTRC" size="60" 
				  value="<% if (collBasic.getE01DCMTRC().equals("AS")) out.print("A la Vista");
							else if (collBasic.getE01DCMTRC().equals("BE")) out.print("Después Fecha Letra de Cambio");
							else if (collBasic.getE01DCMTRC().equals("BL")) out.print("Después Fecha de Embarque");
							else if (collBasic.getE01DCMTRC().equals("CC")) out.print("Después Mercancia Pase Aduana");
							else if (collBasic.getE01DCMTRC().equals("FD")) out.print("Después Mercancía Pase Revisión");
							else if (collBasic.getE01DCMTRC().equals("FP")) out.print("A la Primera Presentación");
							else if (collBasic.getE01DCMTRC().equals("GA")) out.print("Después de Arrivo Mercancía");
							else if (collBasic.getE01DCMTRC().equals("ID")) out.print("Después de Fecha de Factura");
							else if (collBasic.getE01DCMTRC().equals("ST")) out.print("Después de Entregado");
							else if (collBasic.getE01DCMTRC().equals("TD")) out.print("Después de Fecha Doc.Transporte");%>"
				readonly>
            </td>
          </tr>
          <tr id="trdark"> 
            <td width="37%"> 
              <div align="right">Mercanc&iacute;a :</div>
            </td>
            <td width="63%"> 
              <input type="text" name="E01DCMMED" readonly size="35" maxlength="35" value="<%= collBasic.getE01DCMMED().trim()%>">
            </td>
          </tr>
          <tr id="trclear"> 
            <td width="37%"> 
              <div align="right">Nombre del Barco :</div>
            </td>
            <td width="63%"> 
              <input type="text" name="E01DCMVSL" readonly size="20" maxlength="20" value="<%= collBasic.getE01DCMVSL().trim()%>">
            </td>
          </tr>
          <tr id="trdark"> 
            <td width="37%"> 
              <div align="right">Embarque desde :</div>
            </td>
            <td width="63%"> 
              <input type="text" name="E01DCMSHF" readonly size="25" maxlength="25" value="<%= collBasic.getE01DCMSHF().trim()%>">
            </td>
          </tr>
          <tr id="trclear"> 
            <td width="37%" height="20"> 
              <div align="right">Embarque hasta :</div>
            </td>
            <td width="63%" height="20"> 
              <input type="text" name="E01DCMSHT" readonly size="25" maxlength="25" value="<%= collBasic.getE01DCMSHT().trim()%>">
            </td>
          </tr>
          <tr id="trdark"> 
            <td width="37%"> 
              <div align="right">Mercanc&iacute;a Consignada :</div>
            </td>
            <td width="63%"> 
              <input type="text" name="E01DCMMCO" size="3" 
				  value="<% if (collBasic.getE01DCMMCO().equals("Y")) out.print("Sí");
							else out.print("No");%>" 
				readonly>
            </td>
          </tr>
          <tr id="trclear"> 
            <td width="37%"> 
              <div align="right">Fecha Gu&iacute;a de Embarque :</div>
            </td>
            <td width="63%"> 
              <input type="text" name="E01DCMBD1" readonly size="2" maxlength="2" value="<%= collBasic.getE01DCMBD1().trim()%>">
              <input type="text" name="E01DCMBD2" readonly size="2" maxlength="2" value="<%= collBasic.getE01DCMBD2().trim()%>">
              <input type="text" name="E01DCMBD3" readonly size="2" maxlength="2" value="<%= collBasic.getE01DCMBD3().trim()%>">
            </td>
          </tr>
          <tr id="trdark"> 
            <td width="37%"> 
              <div align="right">N&uacute;mero Gu&iacute;a de Embarque :</div>
            </td>
            <td width="63%"> 
              <input type="text" name="E01DCMBLN" readonly size="16" maxlength="16" value="<%= collBasic.getE01DCMBLN().trim()%>">
            </td>
          </tr>
          <tr id="trclear"> 
            <td width="37%"> 
              <div align="right">Agente / Representante :</div>
            </td>
            <td width="63%"> 
              <input type="text" name="E01DCMAG1" readonly size="40" maxlength="35" value="<%= collBasic.getE01DCMAG1().trim()%>">
            </td>
          </tr>
          <tr id="trdark"> 
            <td width="37%"> 
              <div align="right"></div>
            </td>
            <td width="63%"> 
              <input type="text" name="E01DCMAG2" readonly size="40" maxlength="35" value="<%= collBasic.getE01DCMAG2().trim()%>">
            </td>
          </tr>
          <tr id="trclear"> 
            <td width="37%"> 
              <div align="right"></div>
            </td>
            <td width="63%"> 
              <input type="text" name="E01DCMAG3" readonly size="40" maxlength="35" value="<%= collBasic.getE01DCMAG3().trim()%>">
            </td>
          </tr>
          <tr id="trdark"> 
            <td width="37%"> 
              <div align="right"></div>
            </td>
            <td width="63%"> 
              <input type="text" name="E01DCMAG4" readonly size="40" maxlength="35" value="<%= collBasic.getE01DCMAG4().trim()%>">
            </td>
          </tr>
          <tr id="trclear"> 
            <td width="37%"> 
              <div align="right">Remitir / Enviar a :</div>
            </td>
            <td width="63%"> 
              <input type="text" name="E01DCMRM1" readonly size="40" maxlength="35" value="<%= collBasic.getE01DCMRM1().trim()%>">
            </td>
          </tr>
          <tr id="trdark"> 
            <td width="37%"> 
              <div align="right"></div>
            </td>
            <td width="63%"> 
              <input type="text" name="E01DCMRM2" readonly size="40" maxlength="35" value="<%= collBasic.getE01DCMRM2().trim()%>">
            </td>
          </tr>
          <tr id="trclear"> 
            <td width="37%"> 
              <div align="right"></div>
            </td>
            <td width="63%"> 
              <input type="text" name="E01DCMRM3" readonly size="40" maxlength="35" value="<%= collBasic.getE01DCMRM3().trim()%>">
            </td>
          </tr>
          <tr id="trdark"> 
            <td width="37%"> 
              <div align="right"></div>
            </td>
            <td width="63%"> 
              <input type="text" name="E01DCMRM4" readonly size="40" maxlength="35" value="<%= collBasic.getE01DCMRM4().trim()%>">
            </td>
          </tr>
          <tr id="trclear"> 
            <td width="37%"> 
              <div align="right"></div>
            </td>
            <td width="63%"> 
              <input type="text" name="E01DCMRM5" readonly size="40" maxlength="35" value="<%= collBasic.getE01DCMRM5().trim()%>">
            </td>
          </tr>
          <tr id="trdark"> 
            <td width="37%"> 
              <div align="right"></div>
            </td>
            <td width="63%"> 
              <input type="text" name="E01DCMRM6" readonly size="40" maxlength="35" value="<%= collBasic.getE01DCMRM6().trim()%>">
            </td>
          </tr>
        </table>
      </td>
    </tr>
  </table>
  <h4>Documentos Requeridos</h4>
  <table class="tableinfo">
    <tr > 
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
        <% if(!collBasic.getE01DCMDD1().trim().equals("")){%>  
		<tr id="trclear"> 
            <td nowrap width="8%"> 
              <div align="left"></div>
              <%= collBasic.getE01DCMDD1().trim()%></td>
            <td nowrap width="81%"> 
              <div align="left"></div>
              <%= collBasic.getE01DCMDS1().trim()%></td>
            <td nowrap width="5%"> 
              <div align="center"></div>
              <%= collBasic.getE01DCMD01().charAt(0)%></td>
            <td nowrap width="6%"> 
              <div align="center"></div>
              <% try { out.print(collBasic.getE01DCMD01().charAt(1)); } catch (Exception e) { out.print("0"); }%></td>
              </tr>
		<%}%>
        <% if(!collBasic.getE01DCMDD2().trim().equals("")){%>  		
          <tr id="trdark"> 
            <td nowrap width="8%"> 
              <div align="left"></div>
              <%= collBasic.getE01DCMDD2().trim()%></td>
            <td nowrap width="81%"> 
              <div align="left"></div>
              <%= collBasic.getE01DCMDS2().trim()%></td>
            <td nowrap width="5%"> 
              <div align="center"></div>
              <%= collBasic.getE01DCMD02().charAt(0)%></td>
            <td nowrap width="6%"> 
              <div align="center"></div>
              <% try { out.print(collBasic.getE01DCMD02().charAt(1)); } catch (Exception e) { out.print("0"); }%></td>
          </tr>
		<%}%>
        <% if(!collBasic.getE01DCMDD3().trim().equals("")){%>  
          <tr id="trclear"> 
            <td nowrap width="8%"> 
              <div align="left"></div>
              <%= collBasic.getE01DCMDD3().trim()%></td>
            <td nowrap width="81%"> 
              <div align="left"></div>
              <%= collBasic.getE01DCMDS3().trim()%></td>
            <td nowrap width="5%"> 
              <div align="center"></div>
              <%= collBasic.getE01DCMD03().charAt(0)%></td>
            <td nowrap width="6%"> 
              <div align="center"></div>
              <% try { out.print(collBasic.getE01DCMD03().charAt(1)); } catch (Exception e) { out.print("0"); }%></td>
          </tr>
		 <%}%>
        <% if(!collBasic.getE01DCMDD4().trim().equals("")){%>  
          <tr id="trdark"> 
            <td nowrap width="8%"> 
              <div align="left"></div>
              <%= collBasic.getE01DCMDD4().trim()%></td>
            <td nowrap width="81%"> 
              <div align="left"></div>
              <%= collBasic.getE01DCMDS4().trim()%></td>
            <td nowrap width="5%"> 
              <div align="center"></div>
              <%= collBasic.getE01DCMD04().charAt(0)%></td>
            <td nowrap width="6%"> 
              <div align="center"></div>
              <% try { out.print(collBasic.getE01DCMD04().charAt(1)); } catch (Exception e) { out.print("0"); }%></td>
          </tr>
		 <%}%>
        <% if(!collBasic.getE01DCMDD5().trim().equals("")){%>  
          <tr id="trclear"> 
            <td nowrap width="8%"> 
              <div align="left"></div>
              <%= collBasic.getE01DCMDD5().trim()%></td>
            <td nowrap width="81%"> 
              <div align="left"></div>
              <%= collBasic.getE01DCMDS5().trim()%></td>
            <td nowrap width="5%"> 
              <div align="center"></div>
              <%= collBasic.getE01DCMD05().charAt(0)%></td>
            <td nowrap width="6%"> 
              <div align="center"></div>
              <% try { out.print(collBasic.getE01DCMD05().charAt(1)); } catch (Exception e) { out.print("0"); }%></td>
          </tr>
		  <%}%>
        <% if(!collBasic.getE01DCMDD6().trim().equals("")){%>  
          <tr id="trdark"> 
            <td nowrap width="8%"> 
              <div align="left"></div>
              <%= collBasic.getE01DCMDD6().trim()%></td>
            <td nowrap width="81%"> 
              <div align="left"></div>
              <%= collBasic.getE01DCMDS6().trim()%></td>
            <td nowrap width="5%"> 
              <div align="center"></div>
              <%= collBasic.getE01DCMD06().charAt(0)%></td>
            <td nowrap width="6%"> 
              <div align="center"></div>
              <% try { out.print(collBasic.getE01DCMD06().charAt(1)); } catch (Exception e) { out.print("0"); }%></td>
          </tr>
		 <%}%>
        <% if(!collBasic.getE01DCMDD7().trim().equals("")){%>  
          <tr id="trclear"> 
            <td nowrap width="8%"> 
              <div align="left"></div>
              <%= collBasic.getE01DCMDD7().trim()%></td>
            <td nowrap width="81%"> 
              <div align="left"></div>
              <%= collBasic.getE01DCMDS7().trim()%></td>
            <td nowrap width="5%"> 
              <div align="center"></div>
              <%= collBasic.getE01DCMD07().charAt(0)%></td>
            <td nowrap width="6%"> 
              <div align="center"></div>
              <% try { out.print(collBasic.getE01DCMD07().charAt(1)); } catch (Exception e) { out.print("0"); }%></td>
          </tr>
		 <%}%>
        <% if(!collBasic.getE01DCMDD8().trim().equals("")){%>  
          <tr id="trdark"> 
            <td nowrap width="8%"> 
              <div align="left"></div>
              <%= collBasic.getE01DCMDD8().trim()%></td>
            <td nowrap width="81%"> 
              <div align="left"></div>
              <%= collBasic.getE01DCMDS8().trim()%></td>
            <td nowrap width="5%"> 
              <div align="center"></div>
              <%= collBasic.getE01DCMD08().charAt(0)%></td>
            <td nowrap width="6%"> 
              <div align="center"></div>
              <% try { out.print(collBasic.getE01DCMD08().charAt(1)); } catch (Exception e) { out.print("0"); }%></td>
          </tr>
		  <%}%>
          <% if(!collBasic.getE01DCMDD9().trim().equals("")){%>  
          <tr id="trclear"> 
            <td nowrap width="8%"> 
              <div align="left"></div>
              <%= collBasic.getE01DCMDD9().trim()%></td>
            <td nowrap width="81%"> 
              <div align="left"></div>
              <%= collBasic.getE01DCMDS9().trim()%></td>
            <td nowrap width="5%"> 
              <div align="center"></div>
              <%= collBasic.getE01DCMD09().charAt(0)%></td>
            <td nowrap width="6%"> 
              <div align="center"></div>
              <% try { out.print(collBasic.getE01DCMD09().charAt(1)); } catch (Exception e) { out.print("0"); }%></td>
          </tr>
		 <%}%>
        </table>
      </td>
    </tr>
  </table>
  </form>
</body>
</html>
