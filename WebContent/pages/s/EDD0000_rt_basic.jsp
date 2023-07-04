<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<title>Cuentas Corrientes</title>
<META HTTP-EQUIV="Content-Type" CONTENT="text/html; charset=ISO-8859-1">
<META name="GENERATOR" content="IBM WebSphere Studio">
<link Href="<%=request.getContextPath()%>/pages/style.css" rel="stylesheet">


<%@ page import = "java.io.*,java.net.*,datapro.eibs.beans.*,datapro.eibs.master.*,java.math.*" %>

<jsp:useBean id="rtBasic" class="datapro.eibs.beans.EDD000001Message"  scope="session" />
<jsp:useBean id= "error" class= "datapro.eibs.beans.ELEERRMessage"  scope="session" />
<jsp:useBean id= "userPO" class= "datapro.eibs.beans.UserPos"  scope="session" />
<jsp:useBean id= "currUser" class= "datapro.eibs.beans.ESS0030DSMessage"  scope="session" /> 

<script language="Javascript1.1" src="<%=request.getContextPath()%>/pages/s/javascripts/eIBS.jsp"> </SCRIPT>
<script language="Javascript1.1" src="<%=request.getContextPath()%>/pages/s/javascripts/optMenu.jsp"> </SCRIPT>

</head>
<body>
<SCRIPT Language="Javascript">

	builtNewMenu(rt_m_opt);
  builtHPopUp();

function showPopUp(opth,field,bank,ccy,field1,field2,opcod) {
   init(opth,field,bank,ccy,field1,field2,opcod);
   showPopupHelp();
   }

//  Process according with user selection
 function goAction(op) {
	
   	switch (op){
	// Validate & Write 
  	case 1:  {
    	document.forms[0].APPROVAL.value = 'N';
       	break;
        }
	// Validate and Approve
	case 2:  {
 		document.forms[0].APPROVAL.value = 'Y';
       	break;
		}
	}
	document.forms[0].submit();
 }
 
</SCRIPT>

<% 
 if ( !error.getERRNUM().equals("0")  ) {
     error.setERRNUM("0");
     out.println("<SCRIPT Language=\"Javascript\">");
     out.println("       showErrors()");
     out.println("</SCRIPT>");
 }
   out.println("<SCRIPT> initMenu(); </SCRIPT>");
   
 boolean protect = JSEIBSProp.getProtectedBNKBRN();
%> 
<H3 align="center"><% if (userPO.getPurpose().equals("NEW")) out.print("Apertura de Cuentas de Detalle");
   else if (userPO.getPurpose().equals("MAINTENANCE")) out.print("Mantenimiento de Cuentas de Detalle");
   else out.print("");%><img src="<%=request.getContextPath()%>/images/e_ibs.gif" align="left" name="EIBS_GIF" ALT="EDD0000_rt_basic.jsp, EDD0000"></H3>
<hr size="4">
<form method="post" action="<%=request.getContextPath()%>/servlet/datapro.eibs.products.JSEXEDD0000" >
  <INPUT TYPE=HIDDEN NAME="SCREEN" value="29">
  <INPUT TYPE=HIDDEN NAME="E01ACMATY" VALUE="<%= rtBasic.getE01ACMATY().trim()%>">
  <input type=HIDDEN name="E01ACMACD" value="<%= rtBasic.getE01ACMACD().trim()%>">
  <INPUT TYPE=HIDDEN NAME="APPROVAL" VALUE="N">
  
  <table class="tableinfo">
    <tr bordercolor="#FFFFFF"> 
      <td nowrap> 
        <table cellspacing="0" cellpadding="2" width="100%" border="0" class="tbhead">
          <tr id="trdark"> 
            <td nowrap width="16%" > 
              <div align="right"><b>Cliente :</b></div>
            </td>
            <td nowrap width="20%" > 
              <div align="left"> 
                <input type="text" name="E01ACMCUN" size="9" maxlength="9" value="<%= rtBasic.getE01ACMCUN().trim()%>">
                <a href="javascript:GetCustomer('E01ACMCUN')">
                <img src="<%=request.getContextPath()%>/images/1b.gif" alt="Ayuda" border="0" ></a></div>
            </td>
            <td nowrap width="16%" > 
              <div align="right"><b>Nombre :</b> </div>
            </td>
            <td nowrap colspan="3" > 
              <div align="left"> 
                <input type="text" name="E01CUSNA1" size="45" maxlength="45" readonly value="<%= userPO.getHeader3().trim()%>">
              </div>
            </td>
          </tr>
          <tr id="trdark"> 
            <td nowrap width="16%"> 
              <div align="right"><b>Cuenta :</b></div>
            </td>
            <td nowrap width="20%"> 
              <div align="left"> 
                <input type="text" name="E01ACMACC" size="12" maxlength="12" value="<%= userPO.getIdentifier().trim()%>" readonly>
              </div>
            </td>
            <td nowrap width="16%"> 
              <div align="right"><b>Moneda : </b></div>
            </td>
            <td nowrap width="16%"> 
              <div align="left"><b> 
                <input type="text" name="E01DEACCY" size="3" maxlength="3" value="<%= userPO.getCurrency().trim()%>" readonly>
                </b> </div>
            </td>
            <td nowrap width="16%"> 
              <div align="right"><b>Producto : </b></div>
            </td>
            <td nowrap width="16%"> 
              <div align="left"><b> 
                <input type="text" name="E01ACMPRO" size="4" maxlength="4" readonly value="<%= userPO.getHeader1().trim()%>">
                </b> </div>
            </td>
          </tr>
        </table>
      </td>
    </tr>
  </table>
  <p>&nbsp;</p>
  <H4>Datos B&aacute;sicos de la Operaci&oacute;n</H4>
<table  class="tableinfo">
    <tr bordercolor="#FFFFFF"> 
      <td nowrap> 
        <table cellspacing="0" cellpadding="2" width="100%" border="0">
         <tr id="trclear"> 
            <td nowrap width="29%"> 
              <div align="right">Nombre de la Cuenta :</div>
            </td>
            <td nowrap width="19%"> 
              <input type="text" name="E01ACMNME" size="81" maxlength="80" value="<%= rtBasic.getE01ACMNME().trim()%>">              
            </td>
            <td nowrap width="26%"> 
              <div align="right">C&oacute;digo Referencial :</div>
            </td>
            <td nowrap width="26%">
              <select name="E01ACMRCD">
                <option value=" " <% if (!(rtBasic.getE01ACMRCD().equals("D") ||rtBasic.getE01ACMRCD().equals("C")||rtBasic.getE01ACMRCD().equals("A"))) out.print("selected"); %>></option>
                <option value="D" <% if (rtBasic.getE01ACMRCD().equals("D")) out.print("selected"); %>>D&eacute;bitos</option>
                <option value="C" <% if (rtBasic.getE01ACMRCD().equals("C")) out.print("selected"); %>>Cr&eacute;ditos</option>
                <option value="A" <% if (rtBasic.getE01ACMRCD().equals("A")) out.print("selected"); %>>Ambos</option>
              </select>
              <img src="<%=request.getContextPath()%>/images/Check.gif" alt="campo obligatorio" border="0" > 
            </td>
          </tr>        
          <tr id="trdark"> 
            <td nowrap width="29%"> 
              <div align="right">Fecha de Apertura :</div>
            </td>
            <td nowrap width="19%"> 
              <input type="text" name="E01ACMOP1" size="2" maxlength="2" value="<%= rtBasic.getE01ACMOP1().trim()%>" readonly>
              <input type="text" name="E01ACMOP2" size="2" maxlength="2" value="<%= rtBasic.getE01ACMOP2().trim()%>" readonly>
              <input type="text" name="E01ACMOP3" size="2" maxlength="2" value="<%= rtBasic.getE01ACMOP3().trim()%>" readonly>
            </td>
            <td nowrap width="26%"> 
              <div align="right">Fecha de Ingreso :</div>
            </td>
            <td nowrap width="26%">
              <input type="text" name="E01ACMSU1" size="2" maxlength="2" value="<%= rtBasic.getE01ACMSU1().trim()%>" readonly>
              <input type="text" name="E01ACMSU2" size="2" maxlength="2" value="<%= rtBasic.getE01ACMSU2().trim()%>" readonly>
              <input type="text" name="E01ACMSU3" size="2" maxlength="2" value="<%= rtBasic.getE01ACMSU3().trim()%>" readonly>            
            </td>
          </tr>
          <tr id="trclear"> 
            <td nowrap width="29%"> 
              <div align="right">Retenci&oacute;n de Impuestos :</div>
            </td>
            <td nowrap width="19%"> 
              <input type="text" name="E01ACMWHF" size="2" maxlength="1" value="<%= rtBasic.getE01ACMWHF().trim()%>">
              <a href="javascript:GetCode('E01ACMWHF','STATIC_cd_taxes.jsp')"><img src="<%=request.getContextPath()%>/images/1b.gif" alt="Ayuda" border="0" ></a> 
              <img src="<%=request.getContextPath()%>/images/Check.gif" alt="campo obligatorio" border="0" > 
            </td>
            <td nowrap width="26%"> 
              <div align="right">Firmas Autorizadas :</div>
            </td>
            <td nowrap width="26%">
              <select name="E01ACMPEC">
                <option value=" " <% if (!(rtBasic.getE01ACMPEC().equals("1") ||rtBasic.getE01ACMPEC().equals("2"))) out.print("selected"); %>></option>
                <option value="1" <%if (rtBasic.getE01ACMPEC().equals("1")) out.print("selected"); %>>Individual</option>
                <option value="2" <% if (rtBasic.getE01ACMPEC().equals("2")) out.print("selected"); %>>Mancomunada</option>
				<!--<OPTION value="3">Indistinta</OPTION>-->
			  </select>
              <img src="<%=request.getContextPath()%>/images/Check.gif" alt="campo obligatorio" border="0" > 
            </td>
          </tr>
          <tr id="trdark"> 
            <td nowrap width="29%" height="23"> 
              <div align="right">Mensajes al Cliente :</div>
            </td>
            <td nowrap width="19%" height="23"> 
              <input type="hidden" name="E01ACMPMF" value="<%= rtBasic.getE01ACMPMF() %>">
              <input type="radio" name="CE01ACMPMF" value="Y" onClick="document.forms[0].E01ACMPMF.value='Y'"
			  <%if(rtBasic.getE01ACMPMF().equals("Y")) out.print("checked");%>>
              S&iacute; 
              <input type="radio" name="CE01ACMPMF" value="N" onClick="document.forms[0].E01ACMPMF.value='N'"
			  <%if(rtBasic.getE01ACMPMF().equals("N")) out.print("checked");%>>
              No </td>
            <td nowrap width="26%" height="23"> 
              <div align="right">Sobregiros Permitidos :</div>
            </td>
            <td nowrap width="26%" height="23">
              <input type="hidden" name="E01ACMF03" value="<%= rtBasic.getE01ACMF03()%>">
              <input type="radio" name="CE01ACMF03" value="1" onClick="document.forms[0].E01ACMF03.value='1'"
			  <%if(rtBasic.getE01ACMF03().equals("1")) out.print("checked");%>>
              S&iacute; 
              <input type="radio" name="CE01ACMF03" value="N" onClick="document.forms[0].E01ACMF03.value='N'"
			  <%if(rtBasic.getE01ACMF03().equals("N")) out.print("checked");%>>
              No <img src="<%=request.getContextPath()%>/images/Check.gif" alt="campo obligatorio" border="0" ></td>
          </tr>
          <tr id="trclear"> 
            <td nowrap width="29%" height="19"> 
              <div align="right">N&uacute;mero de Direcci&oacute;n Postal :</div>
            </td>
            <td nowrap width="19%" height="19"> 
              <input type="text" name="E01ACMMLA" size="2" maxlength="1" value="<%= rtBasic.getE01ACMMLA().trim()%>">
              <a href="javascript:GetMailing('E01ACMMLA',document.forms[0].E01ACMCUN.value)"><img src="<%=request.getContextPath()%>/images/1b.gif" alt="Ayuda" align="middle" border="0"></a> 
            </td>
            <td nowrap width="26%" height="19"> 
              <div align="right">Manejo de Sub-Cuentas :</div>
            </td>
            <td nowrap width="26%" height="19">
              <input type="hidden" name="E01ACMPTF" value="<%= rtBasic.getE01ACMPTF()%>">
              <input type="radio" name="CE01ACMPTF" value="Y" onClick="document.forms[0].E01ACMPTF.value='Y'"
			  <%if(rtBasic.getE01ACMPTF().equals("Y")) out.print("checked");%>>
              S&iacute; 
              <input type="radio" name="CE01ACMPTF" value="N" onClick="document.forms[0].E01ACMPTF.value='N'"
			  <%if(rtBasic.getE01ACMPTF().equals("N")) out.print("checked");%>>
              No <img src="<%=request.getContextPath()%>/images/Check.gif" alt="campo obligatorio" border="0" ></td>
          </tr>
          <tr id="trdark">
            <td nowrap width="29%" height="19">
              <div align="right">Tabla de Documentos :</div>
            </td>
            <td nowrap width="19%" height="19">
              <input type="text" size="2" maxlength="1" readonly value="<%= rtBasic.getE01APCTNU().trim()%>" name="E01APCTNU">
            </td>
            <td nowrap width="26%" height="19">
              <div align="right">Centro de Costo :</div>
            </td>
            <td nowrap width="26%" height="19">
              <input type="text" name="E01ACMCCN" size="8" maxlength="8" value="<%= rtBasic.getE01ACMCCN().trim()%>">
              <a href="javascript:GetCostCenter('E01ACMCCN','01')"><img src="<%=request.getContextPath()%>/images/1b.gif" alt="Ayuda" align="middle" border="0" ></a> 
              <img src="<%=request.getContextPath()%>/images/Check.gif" alt="campo obligatorio" border="0" > 
            </td>
          </tr>
          <tr id="trclear"> 
            <td nowrap width="29%" height="19"> 
              <div align="right">Diferidos Locales :</div>
            </td>
            <td nowrap width="19%" height="19">
              <input type="text" size="2" maxlength="1" value="<%= rtBasic.getE01ACMWLF().trim()%>" name="E01ACMWLF">
              <a href="javascript:GetCode('E01ACMWLF','STATIC_rt_delay.jsp')"><img src="<%=request.getContextPath()%>/images/1b.gif" alt="Ayuda" border="0" ></a> 
            </td>
            <td nowrap width="26%" height="19"> 
              <div align="right">Diferidos No Locales :</div>
            </td>
            <td nowrap width="26%" height="19">
              <input type="text" size="2" maxlength="1" value="<%= rtBasic.getE01ACMWNF().trim()%>" name="E01ACMWNF">
              <a href="javascript:GetCode('E01ACMWNF','STATIC_rt_delay.jsp')"><img src="<%=request.getContextPath()%>/images/1b.gif" alt="Ayuda" border="0" ></a> 
            </td>
          </tr>
          <tr id="trdark">
            <td nowrap width="29%" height="19">
              <div align="right">Banco/Sucursal :</div>
            </td>
            <td nowrap colspan=3>
            <% if (!protect && rtBasic.getH01FLGMAS().equals("N")) {%>
              <input type="text" name="E01ACMBNK" size="2" maxlength="2" value="<%= rtBasic.getE01ACMBNK().trim()%>">
              <input type="text" name="E01ACMBRN" size="2" maxlength="3" value="<%= rtBasic.getE01ACMBRN().trim()%>">
              <a href="javascript:GetBranch('E01ACMBRN','')"><img src="<%=request.getContextPath()%>/images/1b.gif" alt="ayuda" border="0"  ></a>
             <% } else {%>
              <input type="text" name="E01ACMBNK" size="2" maxlength="2" value="<%= rtBasic.getE01ACMBNK().trim()%>" readonly>
              <input type="text" name="E01ACMBRN" size="2" maxlength="3" value="<%= rtBasic.getE01ACMBRN().trim()%>" readonly>
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
              <div align="right">Cargos por Servicios :</div>
            </td>
            <td nowrap width="18%"> 
              <input type="hidden" name="E01ACMSCF" value="<%= rtBasic.getE01ACMSCF()%>">
              <input type="radio" name="CE01ACMSCF" value="Y" onClick="document.forms[0].E01ACMSCF.value='Y'"
			  <%if(rtBasic.getE01ACMSCF().equals("Y")) out.print("checked");%>>
              S&iacute; 
              <input type="radio" name="CE01ACMSCF" value="N" onClick="document.forms[0].E01ACMSCF.value='N'"
			  <%if(rtBasic.getE01ACMSCF().equals("N")) out.print("checked");%>>
              No <img src="<%=request.getContextPath()%>/images/Check.gif" alt="campo obligatorio" border="0" ></td>
            <td nowrap width="28%"> 
              <div align="right">Frecuencia Cobro de Cargos :</div>
            </td>
            <td nowrap width="24%">
              <select name="E01ACMSHF">
				<option value=" " <% if (!(rtBasic.getE01ACMSHF().equals("D") ||rtBasic.getE01ACMSHF().equals("W")
				||rtBasic.getE01ACMSHF().equals("B")||rtBasic.getE01ACMSHF().equals("M")||rtBasic.getE01ACMSHF().equals("Q")))
				out.print("selected"); %>></option>
                <option value="D" <%if (rtBasic.getE01ACMSHF().equals("D")) out.print("selected"); %>>Diario</option>
                <option value="W" <%if (rtBasic.getE01ACMSHF().equals("W")) out.print("selected"); %>>Semanal</option>
                <option value="B" <%if (rtBasic.getE01ACMSHF().equals("B")) out.print("selected"); %>>Quincenal</option>
                <option value="M" <%if (rtBasic.getE01ACMSHF().equals("M")) out.print("selected"); %>>Mensual</option>
                <option value="Q" <%if (rtBasic.getE01ACMSHF().equals("Q")) out.print("selected"); %>>Trimestral</option>
              </select>
              <img src="<%=request.getContextPath()%>/images/Check.gif" alt="campo obligatorio" border="0" > 
            </td>
          </tr>
          <tr id="trclear"> 
            <td nowrap width="30%"> 
              <div align="right">C&oacute;digos Tabla de Cargos :</div>
            </td>
            <td nowrap width="18%"> 
              <input type="text" name="E01ACMACL" size="2" maxlength="2" value="<%= rtBasic.getE01ACMACL().trim()%>">
              <a href="javascript:GetRetCod('E01ACMACL',document.forms[0].E01ACMATY.value)"><img src="<%=request.getContextPath()%>/images/1b.gif" alt="Ayuda" align="middle" border="0" ></a> 
              <img src="<%=request.getContextPath()%>/images/Check.gif" alt="campo obligatorio" border="0" > 
            </td>
            <td nowrap width="28%"> 
              <div align="right">Ciclo/D&iacute;a Cobro de Cargos :</div>
            </td>
            <td nowrap width="24%"> 
              <input type="text" name="E01ACMSHY" size="3" maxlength="2" value="<%= rtBasic.getE01ACMSHY().trim()%>">
              <a href="javascript:GetCode('E01ACMSHY','STATIC_rt_ciclo.jsp')"><img src="<%=request.getContextPath()%>/images/1b.gif" alt="Ayuda" border="0" ></a> 
              <img src="<%=request.getContextPath()%>/images/Check.gif" alt="campo obligatorio" border="0" > 
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
              <div align="right">Frecuencia  :</div>
            </td>
            <td nowrap width="18%">
              <select name="E01ACMSTF">
				<option value=" " <% if (!(rtBasic.getE01ACMSTF().equals("D") ||rtBasic.getE01ACMSTF().equals("W")
				||rtBasic.getE01ACMSTF().equals("B")||rtBasic.getE01ACMSTF().equals("M")||rtBasic.getE01ACMSTF().equals("Q")))
				out.print("selected"); %>></option>
                <option value="D" <%if (rtBasic.getE01ACMSTF().equals("D")) out.print("selected"); %>>Diario</option>
                <option value="W" <%if (rtBasic.getE01ACMSTF().equals("W")) out.print("selected"); %>>Semanal</option>
                <option value="B" <%if (rtBasic.getE01ACMSTF().equals("B")) out.print("selected"); %>>Quincenal</option>
                <option value="M" <%if (rtBasic.getE01ACMSTF().equals("M")) out.print("selected"); %>>Mensual</option>
                <option value="Q" <%if (rtBasic.getE01ACMSTF().equals("Q")) out.print("selected"); %>>Trimestral</option>
              </select>
              <img src="<%=request.getContextPath()%>/images/Check.gif" alt="campo obligatorio" border="0" > 
            </td>
            <td nowrap width="28%"> 
              <div align="right">Retenci&oacute;n de Correos :</div>
            </td>
            <td nowrap width="24%"> 
              <input type="hidden" name="E01ACMHSF" value="<%= rtBasic.getE01ACMHSF()%>">
              <input type="radio" name="CE01ACMHSF" value="H" onClick="document.forms[0].E01ACMHSF.value='H'"
			  <%if(rtBasic.getE01ACMHSF().equals("H")) out.print("checked");%>>
              S&iacute; 
              <input type="radio" name="CE01ACMHSF" value="" onClick="document.forms[0].E01ACMHSF.value=''"
			  <%if(rtBasic.getE01ACMHSF().equals("")) out.print("checked");%>>
              No</td>
          </tr>
          <tr id="trclear"> 
            <td nowrap width="30%"> 
              <div align="right">Ciclo/D&iacute;a Impresi&oacute;n  
                :</div>
            </td>
            <td nowrap width="18%"> 
              <input type="text" name="E01ACMSDY" size="3" maxlength="2" value="<%= rtBasic.getE01ACMSDY().trim()%>">
              <a href="javascript:GetCode('E01ACMSDY','STATIC_rt_ciclo.jsp')"><img src="<%=request.getContextPath()%>/images/1b.gif" alt="Ayuda" border="0" ></a> 
              <img src="<%=request.getContextPath()%>/images/Check.gif" alt="campo obligatorio" border="0" > 
            </td>
            <td nowrap width="28%"> 
              <div align="right">Estado Consolidado :</div>
            </td>
            <td nowrap width="24%"> 
              <input type="hidden" name="E01ACMCSF" value="<%= rtBasic.getE01ACMCSF()%>">
              <input type="radio" name="CE01ACMCSF" value="Y" onClick="document.forms[0].E01ACMCSF.value='Y'"
			  <%if(rtBasic.getE01ACMCSF().equals("Y")) out.print("checked");%>>
              S&iacute; 
              <input type="radio" name="CE01ACMCSF" value="N" onClick="document.forms[0].E01ACMCSF.value='N'"
			  <%if(rtBasic.getE01ACMCSF().equals("N")) out.print("checked");%>>
              No</td>
          </tr>
          <tr id="trdark"> 
            <td nowrap width="30%" height="23"> 
              <div align="right">Tipo de Estado de 
                Cuenta :</div>
            </td>
            <td nowrap width="18%" height="23"> 
              <select name="E01ACMSTY">
                <option value=" " <% if (!(rtBasic.getE01ACMSTY().equals("P") ||rtBasic.getE01ACMSTY().equals("C")
				||rtBasic.getE01ACMSTY().equals("N"))) out.print("selected"); %>></option>
                <option value="P" <%if (rtBasic.getE01ACMSTY().equals("P")) out.print("selected"); %>>Personal</option>
                <option value="C" <%if (rtBasic.getE01ACMSTY().equals("C")) out.print("selected"); %>>Corporativa</option>
                <option value="N" <%if (rtBasic.getE01ACMSTY().equals("N")) out.print("selected"); %>>Ninguna</option>
              </select>
              <img src="<%=request.getContextPath()%>/images/Check.gif" alt="campo obligatorio" border="0" > 
            </td>
            <td nowrap width="28%" height="23"> 
              <div align="right">Forma de Env&iacute;o  :</div>
            </td>
            <td nowrap width="24%" height="23">
              <select name="E01ACMSTE" 
				<% if (rtBasic.getH01FLGWK2().equals("Y")) {%>
					disabled="disabled"
				<%}%>
			  >
				<option value="N" <% if (!(rtBasic.getE01ACMSTE().equals("T") ||rtBasic.getE01ACMSTE().equals("P")
				||rtBasic.getE01ACMSTE().equals("F")||rtBasic.getE01ACMSTE().equals("E")))
				out.print("selected"); %>>Ninguno</option>
                <option value="T" <%if (rtBasic.getE01ACMSTE().equals("T")) out.print("selected"); %>>Telex</option>
                <option value="P" <%if (rtBasic.getE01ACMSTE().equals("P")) out.print("selected"); %>>Impresora</option>
                <option value="F" <%if (rtBasic.getE01ACMSTE().equals("F")) out.print("selected"); %>>Facsimil</option>
                <option value="E" <%if (rtBasic.getE01ACMSTE().equals("E")) out.print("selected"); %>>Email</option>
             </select>
              <img src="<%=request.getContextPath()%>/images/Check.gif" alt="campo obligatorio" border="0" > 
            </td>
          </tr>
        </table>
      </td>
    </tr>
  </table>  


  <H4>Asignaci&oacute;n de Chequeras</H4>
  <table  class="tableinfo">
    <tr> 
      <td nowrap> 
        <table cellspacing="0" cellpadding="2" width="100%" border="0">
          <tr id="trdark">
            <td nowrap><div align="right">Nombre Personalizado :</div></td>
            <td nowrap colspan=3>
            <INPUT type="text" name="E01ACMCK1" size="35" maxlength="35" value="<%= rtBasic.getE01ACMCK1().trim()%>"></td>
            
          </tr>
          <tr id="trclear">
            <td nowrap>
              <div align="right">
              <SELECT name="E01ACMRE1">
              	<OPTION value="Y" <%if (!rtBasic.getE01ACMRE1().equals("O")) out.print("selected"); %>="">Y</OPTION>
              <%-- if (currUser.getE01INT().equals("07")) { %>
                <OPTION value="O" <%if (rtBasic.getE01ACMRE1().equals("O")) out.print("selected"); %>="">O</OPTION>
                <%} else { %>              	
                <OPTION value="O" <%if (rtBasic.getE01ACMRE1().equals("O")) out.print("selected"); %>="">Y/O</OPTION>
               <% } --%>
              </SELECT></div>
            </td>
            <td nowrap colspan=3>
              <INPUT type="text" name="E01ACMCK2" size="35" maxlength="35" value="<%= rtBasic.getE01ACMCK2().trim()%>" >
                          <!--    <a href="javascript:GetCustomerDescId('','E01ACMCK2','')">
              <IMG src="<%=request.getContextPath()%>/images/1b.gif" alt="Ayuda" align="middle" border="0"></A> -->
            </td>
            
          </tr>
          <tr id="trdark"> 
            <td nowrap> 
              <div align="right">Tipo  de Chequera :</div>
            </td>
            <td nowrap <% if (!rtBasic.getH01FLGMAS().equals("N")) out.print("colspan=3");%>>
            <INPUT type="text" name="E01ACMTCB" size="2" maxlength="2" value="<%= rtBasic.getE01ACMTCB().trim()%>">
              <A href="javascript:GetTypCHK('E01ACMTCB','','<%= rtBasic.getE01ACMATY().trim()%>','<%= rtBasic.getE01ACMCCY().trim()%>')">
              <IMG src="<%=request.getContextPath()%>/images/1b.gif" alt="Ayuda" align="middle" border="0"></A> 
            </td>
            <% if (rtBasic.getH01FLGMAS().equals("N")) {%>
            <td nowrap>
              <div align="right">Cantidad :</div>
            </td>
            <td nowrap>
              <INPUT type="text" name="E01ACMNCB" size="3" maxlength="2" value="<%= rtBasic.getE01ACMNCB().trim()%>">
            </td>
            <% } %>
            
          </tr>
          <tr id="trclear"> 
            <td nowrap> 
              <div align="right">Cobro de Cargos :</div>
            </td>
            <td nowrap ><INPUT type="radio" name="E01ACMCBC" value="Y" <% if(!rtBasic.getE01ACMCBC().equals("N")) out.print("checked");%>>
              S&iacute; 
              <INPUT type="radio" name="E01ACMCBC" value="N" <% if(rtBasic.getE01ACMCBC().equals("N")) out.print("checked");%>>
               No
            </td>
            <td nowrap> 
              <div align="right">Stock Mínimo :</div>
            </td>
            <td nowrap><INPUT type="text" name="E01ACMMSK" size="3" maxlength="2" value="<%= rtBasic.getE01ACMMSK().trim()%>"> </td>
            
            
          </tr>
          <tr id="trdark"> 
            <td nowrap> 
              <div align="right">Restricción de Entregas :</div>
            </td>
            <td nowrap >
             <INPUT type="radio" name="E01ACMCON" value="Y" <% if(rtBasic.getE01ACMCON().equals("Y")) out.print("checked");%>>
              S&iacute; 
              <INPUT type="radio" name="E01ACMCON" value="N" <% if(!rtBasic.getE01ACMCON().equals("Y")) out.print("checked");%>>
              No
            </td>
            <td nowrap></td>
            <td nowrap></tr>
        </table>
      </td>
    </tr>
  </table>
<% if (rtBasic.getH01FLGMAS().equals("N")) {%>
  <H4>Origen de Fondos</H4>
  
  <TABLE id="mainTable" class="tableinfo">
  <TR><TD>
   <table id="headTable" >
    <tr id="trdark"> 
      <td nowrap align="center" >        
         Concepto
      </td>
      <td nowrap align="center" > 
         Banco
      </td>
      <td nowrap align="center" > 
        Sucursal
      </td>
      <td nowrap align="center" > 
        Moneda
      </td>
      <td nowrap align="center" > 
        Referencia
      </td>
      <td nowrap align="center" > 
        <input type="text"  size="15" readonly value=" Monto" id="txtlabel">
      </td>
    </tr>
    </TABLE>
     
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
              <div align="center"> 
                <input type="text" name="E01OFFOP<%= name %>" value="<%= rtBasic.getField("E01OFFOP"+name).getString().trim()%>" size="3" maxlength="3">
                <input type="hidden" name="E01OFFGL<%= name %>" value="<%= rtBasic.getField("E01OFFGL"+name).getString().trim()%>">
                <input type="text" name="E01OFFCO<%= name %>" size="25" maxlength="25" readonly value="<%= rtBasic.getField("E01OFFCO"+name).getString().trim()%>" 
                  oncontextmenu="showPopUp(conceptHelp,this.name,document.forms[0].E01ACMBNK.value,'','E01OFFGL<%= name %>','E01OFFOP<%= name %>',document.forms[0].E01ACMACD.value); return false;">
              </div>
            </td>
            <td nowrap> 
              <div align="center"> 
                <input type="text" name="E01OFFBK<%= name %>" size="2" maxlength="2" value="<%= rtBasic.getField("E01OFFBK"+name).getString().trim()%>">
              </div>
            </td>
            <td nowrap> 
              <div align="center"> 
                <input type="text" name="E01OFFBR<%= name %>" size="3" maxlength="3" value="<%= rtBasic.getField("E01OFFBR"+name).getString().trim()%>"
                oncontextmenu="showPopUp(branchHelp,this.name,document.forms[0].E01ACMBNK.value,'','','',''); return false;">
              </div>
            </td>
            <td nowrap> 
              <div align="center"> 
                <input type="text" name="E01OFFCY<%= name %>" size="3" maxlength="3" value="<%= rtBasic.getField("E01OFFCY"+name).getString().trim()%>"
                oncontextmenu="showPopUp(currencyHelp,this.name,document.forms[0].E01ACMBNK.value,'','','',''); return false;">
              </div>
            </td>
            <td nowrap> 
              <div align="center"> 
                <input type="text" name="E01OFFAC<%= name %>" size="12" maxlength="12"  value="<%= rtBasic.getField("E01OFFAC"+name).getString().trim()%>"
                oncontextmenu="showPopUp(accountHelp,this.name,document.forms[0].E01ACMBNK.value,'','','','RT'); return false;">
              </div>
            </td>
            <td nowrap > 
              <div align="center"> 
                <input type="text" name="E01OFFAM<%= name %>" size="15" maxlength="15"  value="<%= rtBasic.getField("E01OFFAM"+name).getString().trim()%>" onKeypress="enterDecimal()">
              </div>
            </td>
          </tr>
          <%
    		}
    		%> 
        </table>
    </div>

<TABLE id="footTable">
          <tr id="trdark"> 
            <td nowrap  align="right"><b>Equivalente Moneda de la Cuenta:</b> </td>
            <td nowrap  align="center"> 
                <input type="text" name="E01OFFEQV" size="15" maxlength="15" readonly value="<%= rtBasic.getE01OFFEQV().trim()%>">
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
  <% } %>
  
<table width="100%">		
  	<tr>
		<% if (rtBasic.getH01FLGWK2().equals("Y")) {%>
  		<td width="50%">
		<%} else { %>
		<td width="100%">
		<% } %>

  		  <div align="center"> 
     		<input id="EIBSBTN" type="button" name="Submit" value="Enviar" onClick="javascript:goAction(1);">
     	  </div>	
  		</td>
		<% if (rtBasic.getH01FLGWK2().equals("Y")) {%>
		<td width="50%">
  		  		<div align="center">
				<input id="EIBSBTN" type="button" name="Submit2" value="Crear" onClick="javascript:goAction(2);">
     	  	 	</div>	
  		</td>
		<%} %>
  	</tr>	
</table>	

</form>
</body>
</html>
