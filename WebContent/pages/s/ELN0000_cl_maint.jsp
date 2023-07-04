<html>
<head>
<title>Información Básica de Lineas de Credito</title>
<META HTTP-EQUIV="Content-Type" CONTENT="text/html; charset=ISO-8859-1">
<link Href="<%=request.getContextPath()%>/pages/style.css" rel="stylesheet">

<jsp:useBean id="clMant" class="datapro.eibs.beans.ELN000001Message"  scope="session" />

<jsp:useBean id= "error" class= "datapro.eibs.beans.ELEERRMessage"  scope="session" />

<jsp:useBean id= "userPO" class= "datapro.eibs.beans.UserPos"  scope="session" />

<script language="Javascript1.1" src="<%=request.getContextPath()%>/pages/s/javascripts/eIBS.jsp"> </SCRIPT>
<script language="Javascript1.1" src="<%=request.getContextPath()%>/pages/s/javascripts/optMenu.jsp"> </SCRIPT>

<SCRIPT Language="Javascript">

   builtNewMenu(cl_m_opt);

	function cancelLC(){
		document.forms[0].SCREEN.value = '400';
		document.forms[0].OPTION.value = 'M';
		document.forms[0].submit();
	}


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
  out.println("<SCRIPT> initMenu(); </SCRIPT>");
%>

<h3 align="center">Lineas de Cr&eacute;dito - Informaci&oacute;n B&aacute;sica<img src="<%=request.getContextPath()%>/images/e_ibs.gif" align="left" name="EIBS_GIF" ALT="cl_maint,ELN0000"></h3>
<hr size="4">
<form method="post" action="<%=request.getContextPath()%>/servlet/datapro.eibs.credit.JSELN0000" >
  <INPUT TYPE=HIDDEN NAME="SCREEN" VALUE="4">
  <INPUT TYPE=HIDDEN NAME="OPTION" VALUE="">
  <table class="tableinfo">
    <tr > 
      <td nowrap> 
        <table cellspacing="0" cellpadding="2" width="100%" border="0" class="tbhead">
          <tr id="trdark"> 
            <td nowrap width="13%" > 
              <div align="right"><b>Cliente :</b></div>
            </td>
            <td nowrap width="20%" > 
              <div align="left"> 
                <input type="text" name="E01WLNCUN" size="9" maxlength="9" value="<%= clMant.getE01WLNCUN().trim()%>">
                <a href="javascript:GetCustomer('E01WLNCUN')"><img src="<%=request.getContextPath()%>/images/1b.gif" alt=". . ." align="absbottom" border="0" ></a></div>
            </td>
            <td nowrap width="17%" > 
              <div align="right"><b>Linea : </b></div>
            </td>
            <td nowrap width="17%" > 
              <input type="text" name="E01WLNNUM" size="5" maxlength="4" value="<%= clMant.getE01WLNNUM().trim()%>" >
            </td>
            <td nowrap width="17%" > 
              <div align="right"><b>Nombre :</b> </div>
            </td>
            <td nowrap colspan="3" > 
              <div align="left"><font face="Arial"><font face="Arial"><font size="2"> 
                <input type="text" name="E01CUSNA1" size="45" maxlength="45" value="<%= clMant.getE01CUSNA1().trim()%>" >
                </font></font></font></div>
            </td>
          </tr>
        </table>
      </td>
    </tr>
  </table>
  <h4>Datos B&aacute;sicos</h4>
      <table class="tableinfo">
    <tr > 
      <td nowrap> 
        <table cellspacing=0 cellpadding=2 width="100%" border="0">
          <tr id="trdark"> 
            <td nowrap width="24%" > 
              <div align="right">Banco / Sucursal :</div>
            </td>
            <td nowrap width="28%" > 
              <input type="text" name="E01WLNBNK" size="2" maxlength="2" value="<%= clMant.getE01WLNBNK().trim()%>" >
              <input type="text" name="E01WLNBRN" size="4" maxlength="3" value="<%= clMant.getE01WLNBRN().trim()%>" >
              <a href="javascript:GetBranch('E01WLNBRN',document.forms[0].E01WLNBNK.value)"><img src="<%=request.getContextPath()%>/images/1b.gif" alt=". . ." align="absmiddle" border="0" ></a> 
              <img src="<%=request.getContextPath()%>/images/Check.gif" alt="mandatory field" align="absbottom" > 
            </td>
            <td nowrap width="24%" > 
              <div align="right">Comprometida o No :</div>
            </td>
            <td nowrap width="24%" > 
              <input type="hidden" name="E01WLNCOU" value="<%= clMant.getE01WLNCOU()%>">
              <input type="radio" name="CE01WLNCOU" value="C" onClick="document.forms[0].E01WLNCOU.value='C'"
			  <%if(clMant.getE01WLNCOU().equals("C")) out.print("checked");%>>
              S&iacute; 
              <input type="radio" name="CE01WLNCOU" value="U" onClick="document.forms[0].E01WLNCOU.value='U'"
			  <%if(clMant.getE01WLNCOU().equals("U")) out.print("checked");%>>
              No </td>
          </tr>
          <tr id="trclear"> 
            <td nowrap width="24%" > 
              <div align="right">Moneda / Cuenta Contable :</div>
            </td>
            <td nowrap width="28%" > 
              <input type="text" name="E01WLNCCY" size="4" maxlength="3" value="<%= clMant.getE01WLNCCY().trim()%>" >
              <a href="javascript:GetCurrency('E01WLNCCY',document.forms[0].E01WLNBNK.value)"><img src="<%=request.getContextPath()%>/images/1b.gif" alt=". . ." align="absmiddle" border="0" ></a> 
              <input type="text" name="E01WLNGLN" size="20" maxlength="16" value="<%= clMant.getE01WLNGLN().trim()%>" >
              <a href="javascript:GetLedger('E01WLNGLN',document.forms[0].E01WLNBNK.value,document.forms[0].E01WLNCCY.value,'90')"><img src="<%=request.getContextPath()%>/images/1b.gif" alt=". . ." align="absbottom" border="0" ></a></td>
            <td nowrap width="24%" > 
              <div align="right">Fecha de Vencimiento :</div>
            </td>
            <td nowrap width="24%" > 
              <input type="text" name="E01WLNMT1" size="2" maxlength="2" value="<%= clMant.getE01WLNMT1().trim()%>" >
              <input type="text" name="E01WLNMT2" size="2" maxlength="2" value="<%= clMant.getE01WLNMT2().trim()%>" >
              <input type="text" name="E01WLNMT3" size="2" maxlength="2" value="<%= clMant.getE01WLNMT3().trim()%>" >
              <img src="<%=request.getContextPath()%>/images/Check.gif" alt="mandatory field" align="absbottom" > 
            </td>
          </tr>
          <tr id="trdark"> 
            <td nowrap width="24%" > 
              <div align="right">Fecha de Apertura :</div>
            </td>
            <td nowrap width="28%" > 
              <input type="text" name="E01WLNOP1" size="2" maxlength="2" value="<%= clMant.getE01WLNOP1().trim()%>" >
              <input type="text" name="E01WLNOP2" size="2" maxlength="2" value="<%= clMant.getE01WLNOP2().trim()%>" >
              <input type="text" name="E01WLNOP3" size="2" maxlength="2" value="<%= clMant.getE01WLNOP3().trim()%>" >
              <img src="<%=request.getContextPath()%>/images/Check.gif" alt="mandatory field" align="absbottom" > 
            </td>
            <td nowrap width="24%" > 
              <div align="right">Centro de Costo :</div>
            </td>
            <td nowrap width="24%" > 
              <input type="text" name="E01WLNCCS" size="8" maxlength="8" value="<%= clMant.getE01WLNCCS().trim()%>">
              <a href="javascript:GetCostCenter('E01WLNCCS',document.forms[0].E01WLNBNK.value)"><img src="<%=request.getContextPath()%>/images/1b.gif" alt="Centros de Costo" align="absmiddle" border="0" ></a> 
            </td>
          </tr>
          <tr id="trclear"> 
            <td nowrap width="24%" > 
              <div align="right">T&eacute;rmino :</div>
            </td>
            <td nowrap width="28%" > 
              <input type="text" name="E01WLNTER" size="4" maxlength="3" value="<%= clMant.getE01WLNTER().trim()%>">
              <select name="E01WLNTRC">
                <option value=" " <% if (!(clMant.getE01WLNTRC().equals("D") ||clMant.getE01WLNTRC().equals("M")||clMant.getE01WLNTRC().equals("Y"))) out.print("selected"); %>></option>
                <option value="D" <% if(clMant.getE01WLNTRC().equals("D")) out.print("selected");%>>D&iacute;a(s)</option>
                <option value="M" <% if(clMant.getE01WLNTRC().equals("M")) out.print("selected");%>>Mes(es)</option>
                <option value="Y" <% if(clMant.getE01WLNTRC().equals("Y")) out.print("selected");%>>A&ntilde;o(s)</option>
              </select>
              <img src="<%=request.getContextPath()%>/images/Check.gif" alt="mandatory field" align="absbottom" > 
            </td>
            <td nowrap width="24%" > 
              <div align="right">Fecha de Autorizaci&oacute;n :</div>
            </td>
            <td nowrap width="24%" > 
              <input type="text" name="E01WLNAU1" size="2" maxlength="2" value="<%= clMant.getE01WLNAU1().trim()%>" >
              <input type="text" name="E01WLNAU2" size="2" maxlength="2" value="<%= clMant.getE01WLNAU2().trim()%>" >
              <input type="text" name="E01WLNAU3" size="2" maxlength="2" value="<%= clMant.getE01WLNAU3().trim()%>" >
              <img src="<%=request.getContextPath()%>/images/Check.gif" alt="mandatory field" align="absbottom" > 
            </td>
          </tr>
          <tr id="trdark"> 
            <td nowrap width="24%" > 
              <div align="right">Tipo de Autorizaci&oacute;n :</div>
            </td>
            <td nowrap width="28%" > 
				<input type="text" name="E01WLNATY" size="2" maxlength="3" value="<%= clMant.getE01WLNATY().trim()%>">            
					<A href="javascript:GetCodeCNOFC('E01WLNATY','A4')">
					<IMG src="<%=request.getContextPath()%>/images/1b.gif" alt=". . ." align="bottom" border="0">
					</A>
			</td>
            <td nowrap width="24%" > 
              <div align="right">Tipo de Linea :</div>
            </td>
            <td nowrap width="24%" > 
              <input type="text" name="E01WLNTYL" size="4" maxlength="4" value="<%= clMant.getE01WLNTYL().trim()%>">
              <a href="javascript:GetCodeCNOFC('E01WLNTYL','14')"><img src="<%=request.getContextPath()%>/images/1b.gif" alt=". . ." align="absbottom" border="0"></a> 
              <img src="<%=request.getContextPath()%>/images/Check.gif" alt="mandatory field" align="absbottom" > 
            </td>
          </tr>
          <tr id="trclear"> 
            <td nowrap width="24%" > 
              <div align="right">Categor&iacute;a de la Linea :</div>
            </td>
            <td nowrap width="28%" > 
              <input type="text" name="E01WLNCAT" size="2" maxlength="1" value="<%= clMant.getE01WLNCAT().trim()%>" >
              <a href="javascript:GetCode('E01WLNCAT','STATIC_cl_cat.jsp')"><img src="<%=request.getContextPath()%>/images/1b.gif" alt=". . ." align="absbottom" border="0" ></a> 
            </td>
            <td nowrap width="24%" > 
              <div align="right">Fecha de Revisi&oacute;n :</div>
            </td>
            <td nowrap width="24%" > 
              <input type="text" name="E01WLNRV1" size="2" maxlength="2" value="<%= clMant.getE01WLNRV1().trim()%>" >
              <input type="text" name="E01WLNRV2" size="2" maxlength="2" value="<%= clMant.getE01WLNRV2().trim()%>" >
              <input type="text" name="E01WLNRV3" size="2" maxlength="2" value="<%= clMant.getE01WLNRV3().trim()%>" >
            </td>
          </tr>
          <tr id="trdark"> 
            <td nowrap width="24%" height="22" > 
              <div align="right">Importe Aprobado :</div>
            </td>
            <td nowrap width="28%" height="22" > 
              <input type="text" name="E01WLNAMN" size="15" maxlength="15" value="<%= clMant.getE01WLNAMN().trim()%>" onKeypress="enterDecimal()" >
              <img src="<%=request.getContextPath()%>/images/Check.gif" alt="mandatory field" align="absbottom" > 
            </td>
            <td nowrap width="24%" height="22" > 
              <div align="right">Fecha de Reasignaci&oacute;n :</div>
            </td>
            <td nowrap width="24%" height="22" > 
              <input type="text" name="E01WLNRE1" size="2" maxlength="2" value="<%= clMant.getE01WLNRE1().trim()%>" >
              <input type="text" name="E01WLNRE2" size="2" maxlength="2" value="<%= clMant.getE01WLNRE2().trim()%>" >
              <input type="text" name="E01WLNRE3" size="2" maxlength="2" value="<%= clMant.getE01WLNRE3().trim()%>" >
            </td>
          </tr>
          <tr id="trclear"> 
            <td nowrap width="24%" > 
              <div align="right">Monto de Reasignaci&oacute;n :</div>
            </td>
            <td nowrap width="28%" > 
              <input type="text" name="E01WLNREA" size="15" maxlength="15" value="<%= clMant.getE01WLNREA().trim()%>" onKeypress="enterDecimal()" >
            </td>
            <td nowrap width="24%" > 
              <div align="right">Operaci&oacute;n en otra Moneda :</div>
            </td>
            <td nowrap width="24%" > 
              <input type="hidden" name="E01WLNMUB" value="<%= clMant.getE01WLNMUB()%>">
              <input type="radio" name="CE01WLNMUB" value="Y" onClick="document.forms[0].E01WLNMUB.value='Y'"
			  <%if(clMant.getE01WLNMUB().equals("Y")) out.print("checked");%>>
              S&iacute; 
              <input type="radio" name="CE01WLNMUB" value="N" onClick="document.forms[0].E01WLNMUB.value='N'"
			  <%if(clMant.getE01WLNMUB().equals("N")) out.print("checked");%>>
              No </td>
          </tr>
          <tr id="trdark"> 
            <td nowrap width="24%" > 
              <div align="right">Autorizada por :</div>
            </td>
            <td nowrap width="28%" > 
              <input type="text" name="E01WLNABY" size="26" maxlength="25" value="<%= clMant.getE01WLNABY().trim()%>" >
            </td>
            <td nowrap width="24%" > 
              <div align="right">Indicador T&eacute;rmino :</div>
            </td>
            <td nowrap width="24%" > 
              <select name="E01WLNTRM">
                <option value=" " <% if (!(clMant.getE01WLNTRM().equals("D") ||clMant.getE01WLNTRM().equals("M")||clMant.getE01WLNTRM().equals("Y"))) out.print("selected"); %>></option>
                <option value="L" <% if(clMant.getE01WLNTRM().equals("L")) out.print("selected");%>>Largo 
                Plazo</option>
                <option value="S" <% if(clMant.getE01WLNTRM().equals("S")) out.print("selected");%>>Corto 
                Plazo</option>
                <option value="T" <% if(clMant.getE01WLNTRM().equals("T")) out.print("selected");%>>Tomadas 
                a Corresponsal</option>
              </select>
            </td>
          </tr>
          <tr id="trclear"> 
            <td nowrap width="24%" > 
              <div align="right">Cliente / Linea Relacionada :</div>
            </td>
            <td nowrap width="28%" > 
              <input type="text" name="E01WLNCGM" size="10" maxlength="9" value="<%= clMant.getE01WLNCGM().trim()%>" >
              <input type="text" name="E01WLNLGM" size="5" maxlength="4" value="<%= clMant.getE01WLNLGM().trim()%>" >
              <a href="javascript:GetCreditLine('E01WLNLGM',document.forms[0].E01WLNCGM.value)"><img src="<%=request.getContextPath()%>/images/1b.gif" alt="Centros de Costo" align="absmiddle" border="0" ></a> 
            </td>
            <td nowrap width="24%" > 
              <div align="right">Linea de Control :</div>
            </td>
            <td nowrap width="24%" > 
              <input type="text" name="E01WLNCCU" size="10" maxlength="9" value="<%= clMant.getE01WLNCCU().trim()%>" >
              <input type="text" name="E01WLNCLN" size="5" maxlength="4" value="<%= clMant.getE01WLNCLN().trim()%>" >
              <a href="javascript:GetCreditLine('E01WLNCLN',document.forms[0].E01WLNCCU.value)"><img src="<%=request.getContextPath()%>/images/1b.gif" alt="Centros de Costo" align="absmiddle" border="0" ></a> 
            </td>
          </tr>
        </table>
      </td>
    </tr>
  </table>
  <h4>Tarifas</h4>
  <table class="tableinfo">
    <tr "> 
      <td nowrap> 
        <table cellspacing=0 cellpadding=2 width="100%" border="0">
          <tr id="trdark"> 
            <td nowrap width="32%" > 
              <div align="right">Tabla de Tarifas para Prestamos :</div>
            </td>
            <td nowrap width="27%" > 
              <input type="text" name="E01WLNTLN" size="2" maxlength="1" value="<%= clMant.getE01WLNTLN().trim()%>" >
              <a href="javascript:GetLoanTable('E01WLNTLN','')"><img src="<%=request.getContextPath()%>/images/1b.gif" alt="Direcciones de Correo del Cliente" align="absmiddle" border="0" ></a> 
            </td>
            <td nowrap width="21%" > 
              <div align="right">Tasa de Inter&eacute;s :</div>
            </td>
            <td nowrap width="20%" > 
              <input type="text" name="E01WLNORT" size="10" maxlength="9" value="<%= clMant.getE01WLNORT().trim()%>" onKeypress="enterSignDecimal()">
            </td>
          </tr>
          <tr id="trclear"> 
            <td nowrap width="32%" > 
              <div align="right">Tabla de Tarifas para L/C :</div>
            </td>
            <td nowrap width="27%" > 
              <input type="text" name="E01WLNTLC" size="2" maxlength="1" value="<%= clMant.getE01WLNTLC().trim()%>" >
              <a href="javascript:GetTariffLC('E01WLNTLC','')"><img src="<%=request.getContextPath()%>/images/1b.gif" alt="Direcciones de Correo del Cliente" align="absmiddle" border="0" ></a> 
            </td>
            <td nowrap width="21%" > 
              <div align="right">Tasa D&iacute;as Gracia :</div>
            </td>
            <td nowrap width="20%" > 
              <input type="text" name="E01WLNMRT" size="10" maxlength="9" value="<%= clMant.getE01WLNMRT().trim()%>" onKeypress="enterSignDecimal()">
            </td>
          </tr>
          <tr id="trdark"> 
            <td nowrap width="32%" > 
              <div align="right">Tabla de Tarifas para Cta./ Cte. :</div>
            </td>
            <td nowrap width="27%" > 
              <input type="text" name="E01WLNTRT" size="2" maxlength="1" value="<%= clMant.getE01WLNTRT().trim()%>" >
              <a href="javascript:GetRetCod('E01WLNTRT','')"><img src="<%=request.getContextPath()%>/images/1b.gif" alt="Direcciones de Correo del Cliente" align="absmiddle" border="0" ></a> 
            </td>
            <td nowrap width="21%" > 
              <div align="right">D&iacute;as Gracia Inter&eacute;s :</div>
            </td>
            <td nowrap width="20%" > 
              <input type="text" name="E01WLNMXT" size="4" maxlength="3" value="<%= clMant.getE01WLNMXT().trim()%>" onKeypress="enterInteger()" >
            </td>
          </tr>
          <tr id="trclear"> 
            <td nowrap width="32%" > 
              <div align="right">Tabla / Tipo Tasa Flotante :</div>
            </td>
            <td nowrap width="27%" > 
              <input type="text" name="E01WLNFTB" size="2" maxlength="2" value="<%= clMant.getE01WLNFTB().trim()%>">
              <a href="javascript:GetFloating('E01WLNFTB')"><img src="<%=request.getContextPath()%>/images/1b.gif" alt="Tabla de Tasas Flotantes" align="absmiddle" border="0" ></a> 
              <select name="E01WLNFTY">
                <option value=" " <% if (!(clMant.getE01WLNFTY().equals("FP") ||clMant.getE01WLNFTY().equals("FS"))) out.print("selected"); %>></option>
                <option value="FP" <% if (clMant.getE01WLNFTY().equals("FP")) out.print("selected"); %>>Primaria</option>
                <option value="FS" <% if (clMant.getE01WLNFTY().equals("FS")) out.print("selected"); %>>Secundaria</option>
              </select>
            </td>
            <td nowrap width="21%" > 
              <div align="right">Nivel Cr&eacute;dito Cliente :</div>
            </td>
            <td nowrap width="20%" > 
              <input type="text" name="E01WLNCRR" size="2" maxlength="1" value="<%= clMant.getE01WLNCRR().trim()%>" >
            </td>
          </tr>
          <tr id="trdark"> 
            <td nowrap width="32%" > 
              <div align="right">Prop&oacute;sito de L&iacute;nea :</div>
            </td>
            <td nowrap width="27%" > 
              <input type="text" name="E01WLNPUR" size="36" maxlength="35" value="<%= clMant.getE01WLNPUR().trim()%>">
            </td>
            <td nowrap width="21%" > 
              <div align="right"></div>
            </td>
            <td nowrap width="20%" >&nbsp; </td>
          </tr>
        </table>
      </td>
    </tr>
  </table>
  <h4>Cargos</h4>
  <table class="tableinfo">
    <tr "> 
      <td nowrap> 
        <table cellspacing=0 cellpadding=2 width="100%" border="0">
          <tr id="trdark"> 
            <td nowrap width="19%" > 
              <div align="right">Tipo de Cargos :</div>
            </td>
            <td nowrap width="40%" > 
              <input type="text" name="E01WLNFET" size="3" maxlength="2" value="<%= clMant.getE01WLNFET().trim()%>" >
              <a href="javascript:GetCode('E01WLNFET','STATIC_cl_chrg.jsp')"><img src="<%=request.getContextPath()%>/images/1b.gif" alt=". . ." align="absbottom" border="0" ></a> 
              <img src="<%=request.getContextPath()%>/images/Check.gif" alt="mandatory field" align="absbottom" > 
            </td>
            <td nowrap width="21%" > 
              <div align="right">Fecha Pr&oacute;ximo Cargo :</div>
            </td>
            <td nowrap width="20%" > 
              <input type="text" name="E01WLNCH1" size="2" maxlength="2" value="<%= clMant.getE01WLNCH1().trim()%>" >
              <input type="text" name="E01WLNCH2" size="2" maxlength="2" value="<%= clMant.getE01WLNCH2().trim()%>" >
              <input type="text" name="E01WLNCH3" size="2" maxlength="2" value="<%= clMant.getE01WLNCH3().trim()%>" >
            </td>
          </tr>
          <tr id="trclear"> 
            <td nowrap width="19%" > 
              <div align="right">Tasa para Cargos :</div>
            </td>
            <td nowrap width="40%" > 
              <input type="text" name="E01WLNBSR" size="10" maxlength="9" value="<%= clMant.getE01WLNBSR().trim()%>" >
            </td>
            <td nowrap width="21%" > 
              <div align="right">Base para Cargos :</div>
            </td>
            <td nowrap width="20%" > 
              <input type="text" name="E01WLNYBS" size="4" maxlength="3" value="<%= clMant.getE01WLNYBS().trim()%>" >
            </td>
          </tr>
          <tr id="trdark"> 
            <td nowrap width="19%" > 
              <div align="right">Frecuencia :</div>
            </td>
            <td nowrap width="40%" >
              <input type="text" name="E01WLNPRD" size="2" maxlength="1" value="<%= clMant.getE01WLNPRD().trim()%>" >
              <a href="javascript:GetCode('E01WLNPRD','STATIC_cl_frec.jsp')"><img src="<%=request.getContextPath()%>/images/1b.gif" alt=". . ." align="absbottom" border="0" ></a> 
            </td>
            <td nowrap width="21%" > 
              <div align="right">I.V.A. :</div>
            </td>
            <td nowrap width="20%" >
              <input type="hidden" name="E01WLNIVA" value="<%= clMant.getE01WLNIVA()%>">
              <input type="radio" name="CE01WLNIVA" value="Y" onClick="document.forms[0].E01WLNIVA.value='Y'"
			  <%if(clMant.getE01WLNIVA().equals("Y")) out.print("checked");%>>
              S&iacute; 
              <input type="radio" name="CE01WLNIVA" value="N" onClick="document.forms[0].E01WLNIVA.value='N'"
			  <%if(clMant.getE01WLNIVA().equals("N")) out.print("checked");%>>
              No </td>
          </tr>
          <tr id="trclear">
            <td nowrap width="19%" > 
              <div align="right">Cuenta a Debitar :</div>
            </td>
            <td nowrap width="40%" > 
              <input type="text" name="E01WLNBDA" size="2" maxlength="2" value="<%= clMant.getE01WLNBDA().trim()%>" >
              <input type="text" name="E01WLNDAB" size="4" maxlength="3" value="<%= clMant.getE01WLNDAB().trim()%>" >
              <a href="javascript:GetBranch('E01WLNDAB',document.forms[0].E01WLNBDA.value)"><img src="<%=request.getContextPath()%>/images/1b.gif" alt=". . ." align="absmiddle" border="0" ></a> 
              <input type="text" name="E01WLNCDA" size="4" maxlength="3" value="<%= clMant.getE01WLNCDA().trim()%>" >
              <a href="javascript:GetCurrency('E01WLNCCY',document.forms[0].E01WLNBDA.value)"><img src="<%=request.getContextPath()%>/images/1b.gif" alt=". . ." align="absmiddle" border="0" ></a> 
              <input type="text" name="E01WLNADN" size="16" maxlength="16" value="<%= clMant.getE01WLNADN().trim()%>" >
              <a href="javascript:GetLedgerOrAccount('E01WLNADN',document.forms[0].E01WLNBDA.value,document.forms[0].E01WLNCDA.value,'','')"><img src="<%=request.getContextPath()%>/images/1b.gif" alt=". . ." align="absbottom" border="0" ></a> 
            </td>
            <td nowrap width="21%" >&nbsp;</td>
            <td nowrap width="20%" >&nbsp; </td>
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
