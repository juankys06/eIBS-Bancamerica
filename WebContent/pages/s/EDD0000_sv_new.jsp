<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<title>Cuentas de Ahorro</title>
<META HTTP-EQUIV="Content-Type" CONTENT="text/html; charset=ISO-8859-1">
<META name="GENERATOR" content="IBM WebSphere Studio">
<link Href="<%=request.getContextPath()%>/pages/style.css" rel="stylesheet">

<%@ page import = "java.io.*,java.net.*,datapro.eibs.beans.*,datapro.eibs.master.*,java.math.*" %>

<jsp:useBean id="svBasic" class="datapro.eibs.beans.EDD000001Message"  scope="session" />
<jsp:useBean id= "error" class= "datapro.eibs.beans.ELEERRMessage"  scope="session" />
<jsp:useBean id= "userPO" class= "datapro.eibs.beans.UserPos"  scope="session" />
<jsp:useBean id= "currClient" class= "datapro.eibs.beans.ESD080001Message"  scope="session" />

<script language="Javascript1.1" src="<%=request.getContextPath()%>/pages/s/javascripts/eIBS.jsp"> </SCRIPT>
 
<script language="Javascript1.1" src="<%=request.getContextPath()%>/pages/s/javascripts/optMenu.jsp"> </SCRIPT>
 

</head>
<body>
<SCRIPT Language="Javascript">
  builtHPopUp();

function showPopUp(opth,field,bank,ccy,field1,field2,opcod) {
    if (field.substring(0,8) == "E01OFFAC"){
     var index = field.substr(8,1);
     var concepto=document.getElementById("E01OFFOP"+index).value;
     if (concepto == "01")
       Client=document.getElementById("E01ACMCUN").value;
     else
       Client="";
   }
 
   init(opth,field,bank,ccy,field1,field2,opcod);
   showPopupHelp();
   }
</SCRIPT>
<%
String CUSCUN = "";
 String CUSNA1 = "";
 
 if (currClient != null && error.getERRNUM().equals("0")) {
    CUSCUN = currClient.getE01CUSCUN().trim();
    CUSNA1 = currClient.getE01CUSNA1().trim();
 } else {
    CUSCUN = svBasic.getE01ACMCUN().trim();
    CUSNA1 = svBasic.getE01CUSNA1().trim();
 }
 
 if ( !error.getERRNUM().equals("0")  ) {
     out.println("<SCRIPT Language=\"Javascript\">");
     error.setERRNUM("0");
     out.println("       showErrors()");
     out.println("</SCRIPT>");
 }

boolean protect = JSEIBSProp.getProtectedBNKBRN();
%> 
<H3 align="center"> Apertura de Cuentas de Ahorro<img src="<%=request.getContextPath()%>/images/e_ibs.gif" align="left" name="EIBS_GIF" ALT="sv_new.jsp, EDD0000"></H3>
<hr size="4">
<form method="post" action="<%=request.getContextPath()%>/servlet/datapro.eibs.products.JSEXEDD0000" >
  <INPUT TYPE=HIDDEN NAME="SCREEN" VALUE="30">
  <INPUT TYPE=HIDDEN NAME="E01ACMATY" VALUE="<%= svBasic.getE01ACMATY().trim()%>">
  <INPUT TYPE=HIDDEN NAME="E01ACMACD" VALUE="<%= svBasic.getE01ACMACD().trim()%>">
  <table  class="tableinfo">
    <tr bordercolor="#FFFFFF"> 
      <td nowrap> 
        <table cellspacing="0" cellpadding="2" width="100%" border="0" class="tbhead">
          <tr id="trdark"> 
            <td nowrap width="16%" > 
              <div align="right"><b>Cliente :</b></div>
            </td>
            <td nowrap width="20%" > 
              <div align="left"> 
                <input type="text" name="E01ACMCUN" size="9" maxlength="9" value="<%= CUSCUN %>">
                <a href="javascript:GetCustomerDescId('E01ACMCUN','E01CUSNA1','')"><img src="<%=request.getContextPath()%>/images/1b.gif" alt="Busqueda de Clientes" align="absbottom" border="0" ></a></div>
            </td>
            <td nowrap width="16%" > 
              <div align="right"><b>Nombre :</b> </div>
            </td>
            <td nowrap colspan="3" > 
              <div align="left"> 
                <input type="text" name="E01CUSNA1" size="45" maxlength="45" readonly value="<%= CUSNA1 %>">
              </div>
            </td>
          </tr>
          <tr id="trdark"> 
            <td nowrap width="16%"> 
              <div align="right"><b>Cuenta :</b></div>
            </td>
            <td nowrap width="20%"> 
              <div align="left"> 
                <input type="text" name="E01ACMACC" size="15" maxlength="12" readonly value="<% if (svBasic.getE01ACMACC().trim().equals("999999999999")) out.print("NUEVA CUENTA"); else out.print(svBasic.getE01ACMACC().trim()); %>">
              </div>
            </td>
            <td nowrap width="16%"> 
              <div align="right"><b>Moneda : </b></div>
            </td>
            <td nowrap width="16%"> 
              <div align="left"><b> 
                <input type="text" name="E01ACMCCY" size="3" maxlength="3" value="<%= userPO.getCurrency().trim()%>" readonly>
                </b> </div>
            </td>
            <td nowrap width="16%"> 
              <div align="right"><b>Producto : </b></div>
            </td>
            <td nowrap width="16%"> 
              <div align="left"><b> 
                <input type="text" name="E01ACMPRO" size="4" maxlength="4" readonly value="<%= userPO.getProdCode().trim()%>">
                </b> </div>
            </td>
          </tr>
        </table>
      </td>
    </tr>
  </table>
  <H4>Datos B&aacute;sicos de la Operaci&oacute;n</H4>
<table  class="tableinfo">
    <tr bordercolor="#FFFFFF"> 
      <td nowrap> 
        <table cellspacing="0" cellpadding="2" width="100%" border="0">
          <tr id="trclear"> 
            <td nowrap width="29%"> 
              <div align="right">Nombre de la Cuenta:</div>
            </td>
            <td nowrap width="19%"> 
              <input type="text" name="E01ACMNME" size="81" maxlength="80" value="<%= svBasic.getE01ACMNME().trim()%>">             
            </td>
            <td nowrap width="26%"> 
            </td>
            <td nowrap width="26%"> 
            </td>
          </tr>        
          <tr id="trdark"> 
            <td nowrap width="29%"> 
              <div align="right">Fecha de Apertura:</div>
            </td>
            <td nowrap width="19%"> 
              <input type="text" name="E01ACMOP1" size="2" maxlength="2" value="<%= svBasic.getE01ACMOP1().trim()%>" readonly>
              <input type="text" name="E01ACMOP2" size="2" maxlength="2" value="<%= svBasic.getE01ACMOP2().trim()%>" readonly>
              <input type="text" name="E01ACMOP3" size="2" maxlength="2" value="<%= svBasic.getE01ACMOP3().trim()%>" readonly>
            </td>
            <td nowrap width="26%"> 
              <div align="right">C&oacute;digo Referencial:</div>
            </td>
            <td nowrap width="26%"> 
              <select name="E01ACMRCD">
                <option value=" " <% if (!(svBasic.getE01ACMRCD().equals("D") ||svBasic.getE01ACMRCD().equals("C")||svBasic.getE01ACMRCD().equals("A"))) out.print("selected"); %>></option>
                <option value="D" <% if (svBasic.getE01ACMRCD().equals("D")) out.print("selected"); %>>D&eacute;bitos</option>
                <option value="C" <% if (svBasic.getE01ACMRCD().equals("C")) out.print("selected"); %>>Cr&eacute;ditos</option>
                <option value="A" <% if (svBasic.getE01ACMRCD().equals("A")) out.print("selected"); %>>Ambos</option>
              </select>
              <img src="<%=request.getContextPath()%>/images/Check.gif" alt="campo obligatorio" align="absbottom" border="0" > 
            </td>
          </tr>
          <tr id="trclear"> 
            <td nowrap width="29%"> 
              <div align="right">Retenci&oacute;n de Impuestos:</div>
            </td>
            <td nowrap width="19%"> 
              <input type="text" name="E01ACMWHF" size="2" maxlength="1" value="<%= svBasic.getE01ACMWHF().trim()%>" readonly>
              <a href="javascript:GetCode('E01ACMWHF','STATIC_cd_taxes.jsp')"></a> 
              <img src="<%=request.getContextPath()%>/images/Check.gif" alt="campo obligatorio" align="absbottom" border="0" ></td>
            <td nowrap width="26%"> 
              <div align="right">Firmas Autorizadas:</div>
            </td>
            <td nowrap width="26%"> 
              <select name="E01ACMPEC">
                <option value=" " <% if (!(svBasic.getE01ACMPEC().equals("1") ||svBasic.getE01ACMPEC().equals("2"))) out.print("selected"); %>></option>
                <option value="1" <%if (svBasic.getE01ACMPEC().equals("1")) out.print("selected"); %>>Individual</option>
                <option value="2" <% if (svBasic.getE01ACMPEC().equals("2")) out.print("selected"); %>>Mancomunada</option>
			  </select>
              <img src="<%=request.getContextPath()%>/images/Check.gif" alt="campo obligatorio" align="absbottom" border="0" > 
            </td>
          </tr>
          <tr id="trdark"> 
            <td nowrap width="29%" height="23"> 
              <div align="right">Mensajes al Cliente:</div>
            </td>
            <td nowrap width="19%" height="23"> 
              <input type="hidden" name="E01ACMPMF" value="<%= svBasic.getE01ACMPMF()%>">
              <input type="radio" name="CE01ACMPMF" value="Y" onClick="document.forms[0].E01ACMPMF.value='Y'"
			  <%if(svBasic.getE01ACMPMF().equals("Y")) out.print("checked");%>>
              S&iacute; 
              <input type="radio" name="CE01ACMPMF" value="N" onClick="document.forms[0].E01ACMPMF.value='N'"
			  <%if(svBasic.getE01ACMPMF().equals("N")) out.print("checked");%>>
              No </td>
            <td nowrap width="26%" height="23"> 
              <div align="right">Manejo de Sub-Cuentas:</div>
            </td>
            <td nowrap width="26%" height="23"> 
              <input type="hidden" name="E01ACMPTF" value="<%= svBasic.getE01ACMPTF()%>">
              <input type="radio" name="CE01ACMPTF" value="Y" onClick="document.forms[0].E01ACMPTF.value='Y'"
			  <%if(svBasic.getE01ACMPTF().equals("Y")) out.print("checked");%>>
              S&iacute; 
              <input type="radio" name="CE01ACMPTF" value="N" onClick="document.forms[0].E01ACMPTF.value='N'"
			  <%if(svBasic.getE01ACMPTF().equals("N")) out.print("checked");%>>
              No <img src="<%=request.getContextPath()%>/images/Check.gif" alt="campo obligatorio" align="absbottom" border="0" ></td>
          </tr>
          <tr id="trclear"> 
            <td nowrap width="29%" height="19"> 
              <div align="right">N&uacute;mero de Direcci&oacute;n Postal:</div>
            </td>
            <td nowrap width="19%" height="19"> 
              <input type="text" name="E01ACMMLA" size="2" maxlength="1" value="<%= svBasic.getE01ACMMLA().trim()%>">
              <a href="javascript:GetMailing('E01ACMMLA',document.forms[0].E01ACMCUN.value)"><img src="<%=request.getContextPath()%>/images/1b.gif" alt="Direcciones de Correo del Cliente" align="absmiddle" border="0" ></a> 
            </td>
            <td nowrap width="26%" height="19"> 
              <div align="right">Centro de Costo:</div>
            </td>
            <td nowrap width="26%" height="19">
              <input type="text" name="E01ACMCCN" size="8" maxlength="8" value="<%= svBasic.getE01ACMCCN().trim()%>">
              <a href="javascript:GetCostCenter('E01ACMCCN','01')"><img src="<%=request.getContextPath()%>/images/1b.gif" alt="help" align="absmiddle" border="0" ></a> 
            </td>
          </tr>
          <tr id="trdark"> 
            <td nowrap width="29%" height="19"> 
              <div align="right">Diferidos Locales:</div>
            </td>
            <td nowrap width="19%" height="19"> 
              <input type="text" size="2" maxlength="1" value="<%= svBasic.getE01ACMWLF().trim()%>" name="E01ACMWLF" readonly disabled>
              <a href="javascript:GetCode('E01ACMWLF','STATIC_rt_delay.jsp')"></a> 
            </td>
            <td nowrap width="26%" height="19"> 
              <div align="right">Diferidos No Locales:</div>
            </td>
            <td nowrap width="26%" height="19"> 
              <input type="text" size="2" maxlength="1" value="<%= svBasic.getE01ACMWNF().trim()%>" name="E01ACMWNF" readonly disabled>
              <a href="javascript:GetCode('E01ACMWNF','STATIC_rt_delay.jsp')"></a> 
            </td>
          </tr>
          <tr id="trclear">
            <td nowrap width="29%" height="19">
              <div align="right">Tabla de Documentos:</div>
            </td>
            <td nowrap width="19%" height="19">
              <input type="text" size="2" maxlength="1" readonly name="text">
            </td>
            <td nowrap width="29%" height="19">
              <div align="right">Banco/Sucursal :</div>
            </td>
            <td nowrap >
            <% if (!protect) {%>
              <input type="text" name="E01ACMBNK" size="2" maxlength="2" value="<%= svBasic.getE01ACMBNK().trim()%>">
              <input type="text" name="E01ACMBRN" size="2" maxlength="3" value="<%= svBasic.getE01ACMBRN().trim()%>">
              <a href="javascript:GetBranch('E01ACMBRN','')"><img src="<%=request.getContextPath()%>/images/1b.gif" alt="ayuda" align="absbottom" border="0"  ></a>
             <% } else {%>
              <input type="text" name="E01ACMBNK" size="2" maxlength="2" value="<%= svBasic.getE01ACMBNK().trim()%>" readonly>
              <input type="text" name="E01ACMBRN" size="2" maxlength="3" value="<%= svBasic.getE01ACMBRN().trim()%>" readonly>
             <% }%> 
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
              <div align="right">Cargos por Servicios:</div>
            </td>
            <td nowrap width="18%"> 
              <input type="hidden" name="E01ACMSCF" value="<%= svBasic.getE01ACMSCF()%>">
              <input type="radio" name="CE01ACMSCF" value="Y" onClick="document.forms[0].E01ACMSCF.value='Y'"
			  <%if(svBasic.getE01ACMSCF().equals("Y")) out.print("checked");%> readonly disabled>
              S&iacute; 
              <input type="radio" name="CE01ACMSCF" value="N" onClick="document.forms[0].E01ACMSCF.value='N'"
			  <%if(svBasic.getE01ACMSCF().equals("N")) out.print("checked");%> readonly disabled>
              No <img src="<%=request.getContextPath()%>/images/Check.gif" alt="campo obligatorio" align="absbottom" border="0" ></td>
            <td nowrap width="28%"> 
              <div align="right">Frecuencia Cobro Cargos:</div>
            </td>
            <td nowrap width="24%">
              <select name="E01ACMSHF" disabled>
				<option value=" " <% if (!(svBasic.getE01ACMSHF().equals("D") ||svBasic.getE01ACMSHF().equals("W")
				||svBasic.getE01ACMSHF().equals("B")||svBasic.getE01ACMSHF().equals("M")||svBasic.getE01ACMSHF().equals("Q")))
				out.print("selected"); %>></option>
                <option value="D" <%if (svBasic.getE01ACMSHF().equals("D")) out.print("selected"); %>>Diario</option>
                <option value="W" <%if (svBasic.getE01ACMSHF().equals("W")) out.print("selected"); %>>Semanal</option>
                <option value="B" <%if (svBasic.getE01ACMSHF().equals("B")) out.print("selected"); %>>Quincenal</option>
                <option value="M" <%if (svBasic.getE01ACMSHF().equals("M")) out.print("selected"); %>>Mensual</option>
                <option value="Q" <%if (svBasic.getE01ACMSHF().equals("Q")) out.print("selected"); %>>Trimestral</option>
              </select>
              <img src="<%=request.getContextPath()%>/images/Check.gif" alt="campo obligatorio" align="absbottom" border="0" > 
            </td>
          </tr>
          <tr id="trclear"> 
            <td nowrap width="30%"> 
              <div align="right">C&oacute;digos Tabla 
                de Cargos:</div>
            </td>
            <td nowrap width="18%"> 
              <input type="text" name="E01ACMACL" size="2" maxlength="2" value="<%= svBasic.getE01ACMACL().trim()%>" readonly disabled>
              <a href="javascript:GetRetCod('E01ACMACL',document.forms[0].E01ACMATY.value)"></a> 
              <img src="<%=request.getContextPath()%>/images/Check.gif" alt="campo obligatorio" align="absbottom" border="0" > 
            </td>
            <td nowrap width="28%"> 
              <div align="right">Ciclo/D&iacute;a 
                de Cobro de Cargos:</div>
            </td>
            <td nowrap width="24%"> 
              <input type="text" name="E01ACMSHY" size="3" maxlength="2" value="<%= svBasic.getE01ACMSHY().trim()%>" readonly disabled>
              <a href="javascript:GetCode('E01ACMSHY','STATIC_rt_ciclo.jsp')"></a> 
              <img src="<%=request.getContextPath()%>/images/Check.gif" alt="campo obligatorio" align="absbottom" border="0" ></td>
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
              <div align="right">Frecuencia :</div>
            </td>
            <td nowrap width="18%">
              <select name="E01ACMSTF">
				<option value=" " <% if (!(svBasic.getE01ACMSTF().equals("D") ||svBasic.getE01ACMSTF().equals("W")
				||svBasic.getE01ACMSTF().equals("B")||svBasic.getE01ACMSTF().equals("M")||svBasic.getE01ACMSTF().equals("Q")))
				out.print("selected"); %>></option>
                <option value="D" <%if (svBasic.getE01ACMSTF().equals("D")) out.print("selected"); %>>Diario</option>
                <option value="W" <%if (svBasic.getE01ACMSTF().equals("W")) out.print("selected"); %>>Semanal</option>
                <option value="B" <%if (svBasic.getE01ACMSTF().equals("B")) out.print("selected"); %>>Quincenal</option>
                <option value="M" <%if (svBasic.getE01ACMSTF().equals("M")) out.print("selected"); %>>Mensual</option>
                <option value="Q" <%if (svBasic.getE01ACMSTF().equals("Q")) out.print("selected"); %>>Trimestral</option>
              </select>
              <img src="<%=request.getContextPath()%>/images/Check.gif" alt="campo obligatorio" align="absbottom" border="0" > 
            </td>
            <td nowrap width="28%"> 
              <div align="right">Retenci&oacute;n de Correos :</div>
            </td>
            <td nowrap width="24%"><font face="Arial" size="2"> 
              <input type="hidden" name="E01ACMHSF" value="<%= svBasic.getE01ACMHSF()%>">
              <input type="radio" name="CE01ACMHSF" value="H" onClick="document.forms[0].E01ACMHSF.value='H'"
			  <%if(svBasic.getE01ACMHSF().equals("H")) out.print("checked");%>>
              S&iacute; 
              <input type="radio" name="CE01ACMHSF" value="" onClick="document.forms[0].E01ACMHSF.value=''"
			  <%if(svBasic.getE01ACMHSF().equals("")) out.print("checked");%>>
              No </font> </td>
          </tr>
          <tr id="trclear"> 
            <td nowrap width="30%"> 
              <div align="right">Ciclo/D&iacute;a 
                Impresi&oacute;n :</div>
            </td>
            <td nowrap width="18%"> 
              <input type="text" name="E01ACMSDY" size="3" maxlength="2" value="<%= svBasic.getE01ACMSDY().trim()%>">
              <a href="javascript:GetCode('E01ACMSDY','STATIC_rt_ciclo.jsp')"><img src="<%=request.getContextPath()%>/images/1b.gif" alt="Dia/Ciclo Cobro de Cargos" align="absbottom" border="0" ></a> 
              <img src="<%=request.getContextPath()%>/images/Check.gif" alt="campo obligatorio" align="absbottom" border="0" > 
            </td>
            <td nowrap width="28%"> 
              <div align="right">Estado  Consolidado :</div>
            </td>
            <td nowrap width="24%"><font face="Arial" size="2"> </font><font face="Arial" size="2"> 
              </font><font face="Arial" size="2">
              <input type="hidden" name="E01ACMCSF" value="<%= svBasic.getE01ACMCSF()%>">
              <input type="radio" name="CE01ACMCSF" value="Y" onClick="document.forms[0].E01ACMCSF.value='Y'"
			  <%if(svBasic.getE01ACMCSF().equals("Y")) out.print("checked");%>>
              S&iacute; 
              <input type="radio" name="CE01ACMCSF" value="N" onClick="document.forms[0].E01ACMCSF.value='N'"
			  <%if(svBasic.getE01ACMCSF().equals("N")) out.print("checked");%>>
              No</font> </td>
          </tr>
          <tr id="trdark"> 
            <td nowrap width="30%" height="23"> 
              <div align="right">Tipo de Estado de Cuenta :</div>
            </td>
            <td nowrap width="18%" height="23"> 
              <select name="E01ACMSTY" >
                <option value=" " <% if (!(svBasic.getE01ACMSTY().equals("P") ||svBasic.getE01ACMSTY().equals("C")||svBasic.getE01ACMSTY().equals("N"))) out.print("selected"); %>></option>
                <option value="P" <% if (svBasic.getE01ACMSTY().equals("P")) out.print("selected"); %>>Personal</option>
                <option value="C" <% if (svBasic.getE01ACMSTY().equals("C")) out.print("selected"); %>>Corporativa</option>
                <option value="N" <% if (svBasic.getE01ACMSTY().equals("N")) out.print("selected"); %>>Ninguna</option>
              </select> 
              <img src="<%=request.getContextPath()%>/images/Check.gif" alt="campo obligatorio" align="bottom" border="0" > 
            </td>
            <td nowrap width="28%" height="23"> 
              <div align="right">Forma de Env&iacute;o :</div>
            </td>
            <td nowrap width="24%" height="23">
              <select name="E01ACMSTE" disabled="disabled">
				<option value="N" <% if (!(svBasic.getE01ACMSTE().equals("T") ||svBasic.getE01ACMSTE().equals("P")
				||svBasic.getE01ACMSTE().equals("F")||svBasic.getE01ACMSTE().equals("E")))
				out.print("selected"); %>>Ninguna</option>
                <option value="T" <%if (svBasic.getE01ACMSTE().equals("T")) out.print("selected"); %>>Telex</option>
                <option value="P" <%if (svBasic.getE01ACMSTE().equals("P")) out.print("selected"); %>>Impresora</option>
                <option value="F" <%if (svBasic.getE01ACMSTE().equals("F")) out.print("selected"); %>>Facsimil</option>
                <option value="E" <%if (svBasic.getE01ACMSTE().equals("E")) out.print("selected"); %>>Email</option>
              </select>
              <img src="<%=request.getContextPath()%>/images/Check.gif" alt="campo obligatorio" align="absbottom" border="0" > 
            </td>
          </tr>
        </table>
      </td>
    </tr>
  </table>
  <H4>Asignaci&oacute;n de Libreta de Ahorros</H4>
  <table  class="tableinfo">
    <tr bordercolor="#FFFFFF"> 
      <td nowrap> 
        <table cellspacing="0" cellpadding="2" width="100%" border="0">
          <tr id="trdark"> 
            <td nowrap width="30%"> 
              <div align="right">Libreta de Ahorros N&uacute;mero:</div>
            </td>
            <td nowrap width="70%"> 
              <input type="text" size="12" maxlength="12" name="E01ACMPBN" value="<%= svBasic.getE01ACMPBN().trim()%>" onKeypress="enterInteger()">             
             <img src="<%=request.getContextPath()%>/images/Check.gif" alt="mandatory field" align="absbottom" >  	     		

            </td>
          </tr>
        </table>
      </td>
    </tr>
  </table>
  <H4>Origen de Fondos</H4>
  <TABLE id="mainTable" class="tableinfo">
  <TR><TD>
  
    <table id="headTable" >
    <tr id="trdark"> 
      <td nowrap align="center" >Concepto</td>
      <td nowrap align="center" >Banco</td>
      <td nowrap align="center" >Sucursal</td>
      <td nowrap align="center" >Moneda</td>
      <td nowrap align="center" >Referencia</td>
      <td nowrap align="center" >Monto</td>
    </tr>
    </table> 
    
    <div id="dataDiv" style="height:60; overflow-y :scroll; z-index:0" >
     <table id="dataTable" >
            <%
  				   int amount = 9;
 				   String name;
  					for ( int i=1; i<=amount; i++ ) {
   					  name = i + "";
   			%> 
          <tr id="trclear"> 
            <td nowrap > 
              <div align="center" nowrap> 
                <input type="text" name="E01OFFOP<%= name %>" value="<%= svBasic.getField("E01OFFOP"+name).getString().trim()%>" size="3" maxlength="3">
                <input type="hidden" name="E01OFFGL<%= name %>" value="<%= svBasic.getField("E01OFFGL"+name).getString().trim()%>">
                <input type="text" name="E01OFFCO<%= name %>" size="25" maxlength="25" readonly value="<%= svBasic.getField("E01OFFCO"+name).getString().trim()%>" 
                  oncontextmenu="showPopUp(conceptHelp,this.name,document.forms[0].E01ACMBNK.value,'','E01OFFGL<%= name %>','E01OFFOP<%= name %>',document.forms[0].E01ACMACD.value); return false;">
              </div>
            </td>
            <td nowrap> 
              <div align="center"> 
                <input type="text" name="E01OFFBK<%= name %>" size="2" maxlength="2" value="<%= svBasic.getField("E01OFFBK"+name).getString().trim()%>">
              </div>
            </td>
            <td nowrap> 
              <div align="center"> 
                <input type="text" name="E01OFFBR<%= name %>" size="3" maxlength="3" value="<%= svBasic.getField("E01OFFBR"+name).getString().trim()%>"
                oncontextmenu="showPopUp(branchHelp,this.name,document.forms[0].E01ACMBNK.value,'','','',''); return false;">
              </div>
            </td>
            <td nowrap> 
              <div align="center"> 
                <input type="text" name="E01OFFCY<%= name %>" size="3" maxlength="3" value="<%= svBasic.getField("E01OFFCY"+name).getString().trim()%>"
                oncontextmenu="showPopUp(currencyHelp,this.name,document.forms[0].E01ACMBNK.value,'','','',''); return false;">
              </div>
            </td>
            <td nowrap> 
              <div align="center"> 
                <input type="text" name="E01OFFAC<%= name %>" size="12" maxlength="12"  value="<%= svBasic.getField("E01OFFAC"+name).getString().trim()%>"
                oncontextmenu="showPopUp(accountHelp,this.name,document.forms[0].E01ACMBNK.value,'','','','RT'); return false;">
              </div>
            </td>
            <td nowrap > 
              <div align="center"> 
                <input type="text" name="E01OFFAM<%= name %>" size="15" maxlength="15"  value="<%= svBasic.getField("E01OFFAM"+name).getString().trim()%>" onKeypress="enterDecimal()">
              </div>
            </td>
          </tr>
          <%
    		}
    		%> 
           </table>
        </div>
        
     <TABLE id="footTable" >    
          <tr id="trdark"> 
            <td nowrap align="right"><b>Equivalente Moneda de la Cuenta:</b> 
            </td>
            <td nowrap align="center"> 
                <input type="text" name="E01OFFEQV" size="15" maxlength="15" readonly value="<%= svBasic.getE01OFFEQV().trim()%>">
            </td>
          </tr>
        </table>
 
   </TD>  
</TR>	
</TABLE>    
 <SCRIPT language="javascript">
    function tableresize() {
     adjustEquTables(headTable,dataTable,dataDiv,0,true);
   }
  tableresize();
  window.onresize=tableresize; 
  </SCRIPT>     
  <% if(error.getERWRNG().equals("Y")){%>
   <h4 style="text-align:center"><input type="checkbox" name="H01FLGWK2" value="A" <% if(svBasic.getH01FLGWK2().equals("A")){ out.print("checked");} %>>
      Aceptar con Errores</h4>
  <% } %>
  <br>
          <div align="center"> 
            <input id="EIBSBTN" type=submit name="Submit" value="Enviar">
          </div>
  </form>
</body>
</html>
