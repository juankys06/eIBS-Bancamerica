<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.0 Transitional//EN">
<html>
<head>
<title>Consulta de Cuentas Corrientes</title>
<META HTTP-EQUIV="Content-Type" CONTENT="text/html; charset=ISO-8859-1">
<META name="GENERATOR" content="IBM WebSphere Page Designer V3.5.2 for Windows">
<META http-equiv="Content-Style-Type" content="text/css">
<link Href="<%=request.getContextPath()%>/pages/style.css" rel="stylesheet">

</head>

<jsp:useBean id="rtBasic" class="datapro.eibs.beans.EDD009002Message" scope="session" />
<jsp:useBean id= "error" class= "datapro.eibs.beans.ELEERRMessage" scope="session" />
<jsp:useBean id= "userPO" class= "datapro.eibs.beans.UserPos"  scope="session" />

<body>

<script language="Javascript1.1" src="<%=request.getContextPath()%>/pages/s/javascripts/eIBS.jsp"> </SCRIPT>
<script language="Javascript1.1" src="<%=request.getContextPath()%>/pages/s/javascripts/optMenu.jsp"> </SCRIPT>

<SCRIPT Language="Javascript">

<%
if ( userPO.getOption().equals("RT") ) {
%>
	builtNewMenu(rt_i_opt);
<%   
}
else if ( userPO.getOption().equals("SV") ) {
%>
	builtNewMenu(sv_i_opt);
<%   
}
%>

</SCRIPT>
<% 
 if ( !error.getERRNUM().equals("0")  ) {
     out.println("<SCRIPT Language=\"Javascript\">");
     out.println("       showErrors()");
     out.println("</SCRIPT>");
 }
   out.println("<SCRIPT> initMenu(); </SCRIPT>");
%> 
<H3 align="center">Informaci&oacute;n B&aacute;sica<img src="<%=request.getContextPath()%>/images/e_ibs.gif" align="left" name="EIBS_GIF" ALT="rt_inq_basic.jsp, EDD0000"> 
  </H3>
<hr size="4">
<form method="post" action="<%=request.getContextPath()%>/servlet/datapro.eibs.products.JSEXEDD0000" >
  <INPUT TYPE=HIDDEN NAME="SCREEN" value="29">
  
  <table border="0" cellspacing="0" cellpadding="0" width="100%">
  	<tr>
  		<td align="right" valign="top" width="85%" style="color:red;font-size:12;"><b><%=rtBasic.getE02PENDAP()%></b></td>
  		<td width="5%"><h4>&nbsp;</h4></td>
  	</tr>
  </table>
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
              <div align="right"><b>Oficial :</b></div>
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
                <input type="text" name="E02DEACCY" size="3" maxlength="3" value="<%= userPO.getCurrency().trim()%>" readonly>
                </b> </div>
            </td>
          </tr>
        </table>
      </td>
    </tr>
  </table>
  <h4>Datos B&aacute;sicos de la Operaci&oacute;n</h4>
<table  class="tableinfo">
    <tr bordercolor="#FFFFFF"> 
      <td nowrap> 
        <table cellspacing="0" cellpadding="2" width="100%" border="0">
          <tr id="trclear">
            <td nowrap width="29%">
              <div align="right">Banco/Sucursal</div>
            </td>
            <td nowrap width="22%">
              <input type="text" name="E02ACMBNK" size="4" maxlength="2" value="<%= rtBasic.getE02ACMBNK().trim()%>" readonly>
              <input type="text" name="E02ACMCBRN" size="5" maxlength="3" value="<%= rtBasic.getE02ACMBRN().trim()%>" readonly>
            </td>
            <td nowrap width="23%">
              <div align="right">Moneda/Cta Contable</div>
            </td>
            <td nowrap width="26%">
              <input type="text" name="E02ACMCCCY" size="5" maxlength="3" value="<%= rtBasic.getE02ACMCCY().trim()%>" readonly>
              <input type="text" name="E02ACMCGLN" size="18" maxlength="16" value="<%= rtBasic.getE02ACMGLN().trim()%>" readonly>
            
          </tr>
          <tr id="trdark"> 
            <td nowrap width="29%"> 
              <div align="right">Nombre de la Cuenta :</div>
            </td>
            <td nowrap width="22%"> 
              <input type="text" name="E02ACMNME" size="81" maxlength="80" value="<%= rtBasic.getE02ACMNME().trim()%>" readonly>
            </td>
            <td nowrap width="23%"> 
              <div align="right">C&oacute;digo Referencial :</div>
            </td>
            <td nowrap width="26%"> 
              <input type="text" name="E02ACMRCD" readonly value="<% if(rtBasic.getE02ACMRCD().equals("D")) out.print("Referencia D&eacute;bitos");
              				else if(rtBasic.getE02ACMRCD().equals("C")) out.print("Referencia Cr&eacute;ditos");
							else out.print("Ambos");%>" size="25">
          </tr>
          <tr id="trclear"> 
            <td nowrap width="29%"> 
              <div align="right">Fecha de Apertura :</div>
            </td>
            <td nowrap width="22%"> 
              <input type="text" name="E02OPNDT1" size="2" maxlength="2" value="<%= rtBasic.getE02OPNDT1().trim()%>" readonly>
              <input type="text" name="E02OPNDT2" size="2" maxlength="2" value="<%= rtBasic.getE02OPNDT2().trim()%>" readonly>
              <input type="text" name="E02OPNDT3" size="2" maxlength="2" value="<%= rtBasic.getE02OPNDT3().trim()%>" readonly>
            </td>
            <td nowrap width="23%"> 
              <div align="right">Fecha de Ingreso :</div>
            </td>
            <td nowrap width="22%"> 
              <input type="text" name="E02ACMSU1" size="2" maxlength="2" value="<%= rtBasic.getE02ACMSU1().trim()%>" readonly>
              <input type="text" name="E02ACMSU2" size="2" maxlength="2" value="<%= rtBasic.getE02ACMSU2().trim()%>" readonly>
              <input type="text" name="E02ACMSU3" size="2" maxlength="2" value="<%= rtBasic.getE02ACMSU3().trim()%>" readonly>
            </td>
          </tr>          
          <tr id="trdark"> 
            <td nowrap width="29%"> 
              <div align="right">Retenci&oacute;n de Impuestos :</div>
            </td>
            <td nowrap width="22%"> 
              <input type="text" name="E02ACMWHF" size="2" maxlength="1" value="<%= rtBasic.getE02ACMWHF().trim()%>" readonly>
            </td>
            <td nowrap width="23%"> 
              <div align="right">Manejo de Sub-Cuentas :</div>
            </td>
            <td nowrap width="26%"> 
              <input type="text" name="E02ACMPTF" size="2" value="<% if(rtBasic.getE02ACMPTF().equals("Y")) out.print("Si");
              				else if(rtBasic.getE02ACMPTF().equals("N")) out.print("No");
							else out.print("");%>" readonly>
            </td>
          </tr>
          <tr id="trclear"> 
            <td nowrap width="29%" height="23"> 
              <div align="right">N&uacute;mero de Direcci&oacute;n Postal :</div>
            </td>
            <td nowrap width="22%" height="23"> 
              <input type="text" name="E02ACMMLA" size="2" maxlength="1" value="<%= rtBasic.getE02ACMMLA().trim()%>" readonly>
            </td>
            <td nowrap width="23%" height="23"> 
              <div align="right">Centro de Costo :</div>
            </td>
            <td nowrap width="26%" height="23"> 
              <input type="text" name="E02ACMCCN" size="8" maxlength="8" value="<%= rtBasic.getE02ACMCCN().trim()%>" readonly>
            </td>
          </tr>
          <tr id="trdark"> 
            <td nowrap width="29%" height="19"> 
              <div align="right">Diferidos Locales :</div>
            </td>
            <td nowrap width="22%" height="19"> 
              <input type="text" size="2" maxlength="1" value="<%= rtBasic.getE02ACMWLF().trim()%>" name="E02ACMWLF" readonly>
            </td>
            <td nowrap width="23%" height="19"> 
              <div align="right">Diferidos No Locales :</div>
            </td>
            <td nowrap width="26%" height="19"> 
              <input type="text" size="2" maxlength="1" value="<%= rtBasic.getE02ACMWNF().trim()%>" name="E02ACMWNF" readonly>
            </td>
          </tr>
          <tr id="trclear"> 
            <td nowrap width="29%" height="19"> 
              <div align="right">Tarjeta ATM :</div>
            </td>
            <td nowrap width="22%" height="19"> 
              <input type="text" name="E02ACMATM" size="8" maxlength="8" value="<%= rtBasic.getE02ACMATM().trim()%>" readonly>
            </td>
            <td nowrap width="23%" height="19"> 
              <div align="right">Ubicaci&oacute;n de Firmas :</div>
            </td>
            <td nowrap width="26%" height="19"> 
              <input type="text" name="E02ACMSL1" size="3" maxlength="3" value="<%= rtBasic.getE02ACMSL1().trim()%>" readonly>
              <input type="text" name="E02ACMSL2" size="2" maxlength="2" value="<%= rtBasic.getE02ACMSL2().trim()%>" readonly>
              <input type="text" name="E02ACMSL3" size="2" maxlength="2" value="<%= rtBasic.getE02ACMSL3().trim()%>" readonly>
            </td>
          </tr>
          <tr id="trdark"> 
            <td nowrap> 
              <div align="right">Vendedor :</div>
            </td>
            <td nowrap> 
              <input type="text" name="E02ACMBRK" size="4" maxlength="3" value="<%= rtBasic.getE02ACMBRK().trim()%>" readonly>
              <input type="text" name="E02DSCBRK" size="35" maxlength="1" value="<%= rtBasic.getE02DSCBRK().trim()%>" readonly>
            </td>
            <td nowrap> 
              <div align="right">Comisión del Vendedor :</div>
            </td>
            <td nowrap> 
              <input type="text" name="E02ACMBCP" size="16" maxlength="15" value="<%= rtBasic.getE02ACMBCP().trim()%>" readonly>
            </td>
          </tr>
          <tr id="trclear">
            <td nowrap width="29%" height="19">
              <div align="right">Banco/Sucursal :</div>
            </td>
            <td nowrap colspan=3>            
              <input type="text" name="E02ACMBNK" size="2" maxlength="2" value="<%= rtBasic.getE02ACMBNK().trim()%>" readonly>
              <input type="text" name="E02ACMBRN" size="2" maxlength="3" value="<%= rtBasic.getE02ACMBRN().trim()%>" readonly>
            </td>
          </tr>
        </table>
      </td>
    </tr>
  </table>
  <H4>Informaci&oacute;n para Cargos por Servicios</H4>
  <table  class="tableinfo">
    <tr bordercolor="#FFFFFF"> 
      <td nowrap> 
        <table cellspacing="0" cellpadding="2" width="100%" border="0">
          <tr id="trdark"> 
            <td nowrap width="30%"> 
              <div align="right">Cargos por Servicios :</div>
            </td>
            <td nowrap width="21%"> 
              <input type="text" name="E02ACMSCF" size="2" value="<% if(rtBasic.getE02ACMSCF().equals("Y")) out.print("Si");
              				else if(rtBasic.getE02ACMSCF().equals("N")) out.print("No");
							else out.print("");%>" readonly>
            </td>
            <td nowrap width="25%"> 
              <div align="right">Frecuencia Cobro de Cargos :</div>
            </td>
            <td nowrap width="24%"> 
              <input type="text" name="E02ACMSHF" readonly value="<% if(rtBasic.getE02ACMSHF().equals("D")) out.print("Diario");
              				else if(rtBasic.getE02ACMSHF().equals("W")) out.print("Semanal");
							else if(rtBasic.getE02ACMSHF().equals("B")) out.print("Quincenal");
							else if(rtBasic.getE02ACMSHF().equals("M")) out.print("Mensual");
							else if(rtBasic.getE02ACMSHF().equals("Q")) out.print("Trimestral");%>" size="25">
            </td>
          </tr>
          <tr id="trclear"> 
            <td nowrap width="30%"> 
              <div align="right">C&oacute;digos Tabla de Cargos :</div>
            </td>
            <td nowrap width="21%"> 
              <input type="text" name="E02DSCACL" size="35" maxlength="35" value="<%= rtBasic.getE02DSCACL().trim()%>" readonly>
            </td>
            <td nowrap width="25%"> 
              <div align="right">Ciclo/D&iacute;a Cobro de Cargos :</div>
            </td>
            <td nowrap width="24%"> 
              <input type="text" name="E02ACMSHY" size="3" maxlength="2" value="<%= rtBasic.getE02ACMSHY().trim()%>" readonly>
            </td>
          </tr>
        </table>
      </td>
    </tr>
  </table>
  <H4>Informaci&oacute;n Estado de Cuenta</H4>
  <table  class="tableinfo">
    <tr bordercolor="#FFFFFF"> 
      <td nowrap> 
        <table cellspacing="0" cellpadding="2" width="100%" border="0">
          <tr id="trdark"> 
            <td nowrap width="30%"> 
              <div align="right">Frecuencia de Estado de Cuenta :</div>
            </td>
            <td nowrap width="18%"> 
              <input type="text" name="E02ACMSTF" readonly value="<% if(rtBasic.getE02ACMSTF().equals("D")) out.print("Diario");
              				else if(rtBasic.getE02ACMSTF().equals("W")) out.print("Semanal");
							else if(rtBasic.getE02ACMSTF().equals("B")) out.print("Quincenal");
							else if(rtBasic.getE02ACMSTF().equals("M")) out.print("Mensual");
							else if(rtBasic.getE02ACMSTF().equals("Q")) out.print("Trimestral");%>" size="15">
            </td>
            <td nowrap width="28%"> 
              <div align="right">Retenci&oacute;n de Correos :</div>
            </td>
            <td nowrap width="24%"><font face="Arial" size="2"> 
              <input type="text" name="E02ACMHSF" size="2" value="<% if(rtBasic.getE02ACMHSF().equals("H")) out.print("Si");
              				else if(rtBasic.getE02ACMHSF().equals("")) out.print("No");
							else out.print("");%>" readonly>
              </font> </td>
          </tr>
          <tr id="trclear"> 
            <td nowrap width="30%"> 
              <div align="right">Ciclo/D&iacute;a Impresi&oacute;n Estado de Cuentas 
                :</div>
            </td>
            <td nowrap width="18%"> 
              <input type="text" name="E02ACMSDY" size="3" maxlength="2" value="<%= rtBasic.getE02ACMSDY().trim()%>" readonly>
            </td>
            <td nowrap width="28%"> 
              <div align="right">Estado de Cuentas Consolidado :</div>
            </td>
            <td nowrap width="24%"><font face="Arial" size="2"> 
              <input type="text" name="E02ACMSCF" size="2" value="<% if(rtBasic.getE02ACMSCF().equals("Y")) out.print("Si");
              				else if(rtBasic.getE02ACMSCF().equals("N")) out.print("No");
							else out.print("");%>" readonly>
              </font></td>
          </tr>
          <tr id="trdark"> 
            <td nowrap width="30%" height="23"> 
              <div align="right">Tipo de Estado de 
                Cuenta :</div>
            </td>
            <td nowrap width="18%" height="23"> 
              <input type="text" name="E02ACMSTY" readonly value="<% if(rtBasic.getE02ACMSTY().equals("P")) out.print("Personal");
              				else if(rtBasic.getE02ACMSTY().equals("C")) out.print("Corporativa");
							else if(rtBasic.getE02ACMSTY().equals("N")) out.print("Ninguna");%>" size="15">
            </td>
            <td nowrap width="28%" height="23"> 
              <div align="right">Forma de Env&iacute;o de Estado de Cuenta :</div>
            </td>
            <td nowrap width="24%" height="23"> 
              <input type="text" name="E02ACMSTE" readonly value="<% if(rtBasic.getE02ACMSTE().equals("T")) out.print("Telex");
              				else if(rtBasic.getE02ACMSTE().equals("P")) out.print("Impresora");
							else if(rtBasic.getE02ACMSTE().equals("F")) out.print("Facsimil");
							else if(rtBasic.getE02ACMSTE().equals("E")) out.print("Correo Electronico");
							else if(rtBasic.getE02ACMSTE().equals("N")) out.print("Ninguno");%>" size="15">
            </td>
          </tr>
        </table>
      </td>
    </tr>
  </table>
  <H4>Cambio de Estado de la Cuenta</H4>
  <table  class="tableinfo">
    <tr bordercolor="#FFFFFF"> 
      <td nowrap> 
        <table cellspacing="0" cellpadding="2" width="100%" border="0">
          <tr id="trdark"> 
            <td nowrap width="23%"> 
              <div align="right">Estado de la Cuenta:</div>
            </td>
            <td nowrap width="23%"> 
              <input type="text" name="E02ACMAST" readonly value="<% if(rtBasic.getE02ACMAST().equals("A")) out.print("Cuenta Activa");
              				else if(rtBasic.getE02ACMAST().equals("C")) out.print("Cuenta Cancelada");
							else if(rtBasic.getE02ACMAST().equals("I")) out.print("Cuenta Inactiva 1");
							else if(rtBasic.getE02ACMAST().equals("D")) out.print("Cuenta Inactiva 2");
							else if(rtBasic.getE02ACMAST().equals("O")) out.print("Cuenta Controlada");
							else if(rtBasic.getE02ACMAST().equals("E")) out.print("Cuenta Embargada");
							else if(rtBasic.getE02ACMAST().equals("T")) out.print("Acepta Solo Depositos");
							else out.print("");%>" size="25">
            </td>
            <td nowrap width="22%"> 
              <div align="right">Cambiado por (Funcionario):</div>
            </td>
            <td nowrap width="32%"> 
              <input type="text" name="E02ACMUIN" size="5" maxlength="4" value="<%= rtBasic.getE02ACMUIN().trim()%>" readonly>
              <input type="text" name="E02DSCUIN" size="25" value="<%= rtBasic.getE02DSCUIN().trim()%>" readonly>
            </td>
          </tr>
          <tr id="trclear"> 
            <td nowrap width="23%"> 
              <div align="right">Fecha de Cambio:</div>
            </td>
            <td nowrap width="23%">
              <input type="text" name="E02LSCST1" size="2" maxlength="2" value="<%= rtBasic.getE02LSCST1().trim()%>" readonly>
              <input type="text" name="E02LSCST2" size="2" maxlength="2" value="<%= rtBasic.getE02LSCST2().trim()%>" readonly>
              <input type="text" name="E02LSCST3" size="2" maxlength="2" value="<%= rtBasic.getE02LSCST3().trim()%>" readonly>
            </td>
            <td nowrap width="22%"> 
              <div align="right"></div>
            </td>
            <td nowrap width="32%">&nbsp; </td>
          </tr>
        </table>
      </td>
    </tr>
  </table>
  <h4>L&iacute;nea y L&iacute;mite de Cr&eacute;dito</h4>
  <table  class="tableinfo">
    <tr bordercolor="#FFFFFF"> 
      <td nowrap> 
        <table cellspacing="0" cellpadding="2" width="100%" border="0">
          <tr id="trdark"> 
            <td nowrap width="29%"> 
              <div align="right">Cliente de la L&iacute;nea:</div>
            </td>
            <td nowrap width="16%"> 
              <input type="text" name="E02ACMCMC" maxlength="9" size="9" value="<%= rtBasic.getE02ACMCMC().trim()%>" readonly>
            </td>
            <td nowrap width="27%"> 
              <div align="right">L&iacute;nea de Cr&eacute;dito:</div>
            </td>
            <td nowrap width="28%">
              <input type="text" name="E02ACMCMN" maxlength="4" size="4" value="<%= rtBasic.getE02ACMCMN().trim()%>" readonly>
            </td>
          </tr>
          <tr id="trclear"> 
            <td nowrap width="29%"> 
              <div align="right">Valor L&iacute;mite de Cr&eacute;dito:</div>
            </td>
            <td nowrap width="16%"> 
              <input type="text" name="E02ACMCLI" size="11" maxlength="11" value="<%= rtBasic.getE02ACMCLI().trim()%>" readonly>
            </td>
            <td nowrap width="27%"> 
              <div align="right">Fecha Revisi&oacute;n de Cr&eacute;dito:</div>
            </td>
            <td nowrap width="28%"> 
              <input type="text" maxlength="2" size="2" name="E02ODLRD1" value="<%= rtBasic.getE02ODLRD1().trim()%>" readonly>
              <input type="text" maxlength="2" size="2" name="E02ODLRD2" value="<%= rtBasic.getE02ODLRD2().trim()%>" readonly>
              <input type="text" maxlength="2" size="2" name="E02ODLRD3" value="<%= rtBasic.getE02ODLRD3().trim()%>" readonly>
            </td>
          </tr>
        </table>
      </td>
    </tr>
  </table>
  <h4>L&iacute;mites para Sobregiros</h4>
  <table  class="tableinfo">
    <tr bordercolor="#FFFFFF"> 
      <td nowrap> 
        <table cellspacing="0" cellpadding="2" width="100%" border="0">
          <tr id="trdark"> 
            <td nowrap width="29%" height="33"> 
              <div align="right">Permitir Sobregiros :</div>
            </td>
            <td nowrap width="16%" height="33"> <font face="Arial" size="2"> 
              <input type="text" name="E02ACMF03" size="2" value="<% if(rtBasic.getE02ACMF03().equals("1")) out.print("Si");
              				else if(rtBasic.getE02ACMF03().equals("N")) out.print("No");
							else out.print("");%>" readonly>
              </font> </td>
            <td nowrap width="27%" height="33"> 
              <div align="right">Saldo Usado para Sobregiros :</div>
            </td>
            <td nowrap width="28%" height="33"> 
              <input type="text" name="E02ACMONG" readonly value="<% if(rtBasic.getE02ACMONG().equals("G")) out.print("Saldo en Libros");
              				else if(rtBasic.getE02ACMONG().equals("N")) out.print("Saldo Neto");
							else if(rtBasic.getE02ACMONG().equals("C")) out.print("Segun Control");
							else out.print("");%>" size="25">
            </td>
          </tr>
          <tr id="trclear"> 
            <td nowrap width="29%"> 
              <div align="right">Cargos por Sobregiros :</div>
            </td>
            <td nowrap width="16%"> 
              <input type="text" name="E02ACMODF" readonly value="<% if(rtBasic.getE02ACMODF().equals("N")) out.print("No Cargos");
              				else if(rtBasic.getE02ACMODF().equals("1")) out.print("Si Cargos");
							else if(rtBasic.getE02ACMODF().equals("2")) out.print("Diferir Cargos");
							else if(rtBasic.getE02ACMODF().equals("3")) out.print("Cargos Diarios");
							else if(rtBasic.getE02ACMODF().equals("4")) out.print("A Prestamos");
							else out.print("");%>" size="25">
            </td>
            <td nowrap width="27%"> 
              <div align="right">Porcentaje Maximo de Garantia :</div>
            </td>
            <td nowrap width="28%"> 
              <input type="text" name="E02ACMCPE" maxlength="7" size="7" value="<%= rtBasic.getE02ACMCPE().trim()%>" readonly>
            </td>
          </tr>
          <tr id="trdark"> 
            <td nowrap width="29%"> 
              <div align="right">Valor L&iacute;mite de Sobregiro 1:</div>
            </td>
            <td nowrap width="16%"> 
              <input type="text" name="E02ACMOL1" maxlength="11" size="11" value="<%= rtBasic.getE02ACMOL1().trim()%>" readonly>
            </td>
            <td nowrap width="27%"> 
              <div align="right">Sobretasa por Sobregiro 1:</div>
            </td>
            <td nowrap width="28%"> 
              <input type="text" name="E02ACMOI1" size="5" maxlength="5" value="<%= rtBasic.getE02ACMOI1().trim()%>" readonly>
            </td>
          </tr>
          <tr id="trclear"> 
            <td nowrap width="29%" height="23"> 
              <div align="right">Valor L&iacute;mite de Sobregiro 2:</div>
            </td>
            <td nowrap width="16%" height="23"> 
              <input type="text" name="E02ACMOL2" maxlength="11" size="11" value="<%= rtBasic.getE02ACMOL2().trim()%>" readonly>
            </td>
            <td nowrap width="27%" height="23"> 
              <div align="right">Sobretasa por Sobregiro2 :</div>
            </td>
            <td nowrap width="28%" height="23"> 
              <input type="text" name="E02ACMOI2" size="5" maxlength="5" value="<%= rtBasic.getE02ACMOI2().trim()%>" readonly>
            </td>
          </tr>
          <tr id="trdark"> 
            <td nowrap width="29%" height="19"> 
              <div align="right">Cuenta Contable Relacionada</div>
            </td>
            <td nowrap width="16%" height="19"> 
              <input type="text" name="E02ACMRGL" size="16" maxlength="16" value="<%= rtBasic.getE02ACMRGL().trim()%>" readonly>
            </td>
            <td nowrap width="27%" height="19"> 
              <div align="right">Cuenta Detalle Relacionada:</div>
            </td>
            <td nowrap width="28%" height="19"> 
              <input type="text" name="E02ACMRAC" size="12" maxlength="12" value="<%= rtBasic.getE02ACMRAC().trim()%>" readonly>
            </td>
          </tr>
          <tr id="trclear"> 
            <td nowrap width="29%" height="19"> 
              <div align="right">Oficina Relacionada:</div>
            </td>
            <td nowrap width="16%" height="19"> 
              <input type="text" name="E02ACMRBR" size="3" maxlength="3" value="<%= rtBasic.getE02ACMRBR().trim()%>" readonly>
            </td>
            <td nowrap width="27%" height="19">&nbsp;</td>
            <td nowrap width="28%" height="19">&nbsp; </td>
          </tr>
        </table>
      </td>
    </tr>
  </table>
  </form>
</body>
</html>
