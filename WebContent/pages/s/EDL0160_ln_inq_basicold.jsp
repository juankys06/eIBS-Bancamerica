<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<title>Información Básica</title>
<META HTTP-EQUIV="Content-Type" CONTENT="text/html; charset=ISO-8859-1">
<META name="GENERATOR" content="IBM WebSphere Studio">
<link Href="<%=request.getContextPath()%>/pages/style.css" rel="stylesheet">

<jsp:useBean id="inqBasic" class="datapro.eibs.beans.EDL016002Message"  scope="session" />

<jsp:useBean id= "error" class= "datapro.eibs.beans.ELEERRMessage"  scope="session" />

<jsp:useBean id= "userPO" class= "datapro.eibs.beans.UserPos"  scope="session" />

<script language="Javascript1.1" src="<%=request.getContextPath()%>/pages/s/javascripts/eIBS.jsp"> </SCRIPT>
 
<script language="Javascript1.1" src="<%=request.getContextPath()%>/pages/s/javascripts/optMenu.jsp"> </SCRIPT>
 

<SCRIPT Language="Javascript">

<%
if ( userPO.getHeader23().equals("G") ||  userPO.getHeader23().equals("V")){
%>
	builtNewMenu(ln_i_1_opt);
<%   
}
else  {
%>
	builtNewMenu(ln_i_2_opt);
<%   
}
%>

</SCRIPT>

</head>

<body>


<% 
 if ( !error.getERRNUM().equals("0")  ) {
     out.println("<SCRIPT Language=\"Javascript\">");
     out.println("       showErrors()");
     out.println("</SCRIPT>");
 }
  out.println("<SCRIPT> initMenu(); </SCRIPT>");
%>

<h3 align="center">Informaci&oacute;n B&aacute;sica<img src="<%=request.getContextPath()%>/images/e_ibs.gif" align="left" name="EIBS_GIF" ALT="ln_inq_basic.jsp,EDL0160"></h3>
<hr size="4">
<form method="post" action="<%=request.getContextPath()%>/servlet/datapro.eibs.products.JSEXEDL0160" >
  <INPUT TYPE=HIDDEN NAME="SCREEN" VALUE="2">
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
    <table class=tbenter>
   <tr > 
      <td nowrap> 
   		<h4>Informaci&oacute;n B&aacute;sica</h4>
      </td>
      <td nowrap align=right> 
   		<b>ESTADO :</b>
      </td>
      <td nowrap> 
   		<b><font color="#ff6600"><%= inqBasic.getE02STATUS().trim()%></font></b>
      </td>
    </tr>
  </table>
  <table class="tableinfo">
    <tr> 
      <td nowrap> 
        <table cellspacing=0 cellpadding=2 width="100%" border="0">
          <tr id="trdark"> 
            <td nowrap > 
              <div align="right">Monto Original :</div>
            </td>
            <td nowrap > 
              <input type="text" readonly name="E02DEAOAM" size="15" maxlength="15" value="<%= inqBasic.getE02DEAOAM().trim()%>" >
            </td>
            <td nowrap > 
              <div align="right">Fecha de Apertura :</div>
            </td>
            <td nowrap> 
              <input type="text" readonly name="E02DEAOD1" size="2" maxlength="2" value="<%= inqBasic.getE02DEAOD1().trim()%>" >
              <input type="text" readonly name="E02DEAOD2" size="2" maxlength="2" value="<%= inqBasic.getE02DEAOD2().trim()%>" >
              <input type="text" readonly name="E02DEAOD3" size="2" maxlength="2" value="<%= inqBasic.getE02DEAOD3().trim()%>" >
            </td>
          </tr>
          <tr id="trclear"> 
            <td nowrap > 
              <div align="right">Tasa de Inter&eacute;s/Spread :</div>
            </td>
            <td nowrap > 
              <input type="text" readonly name="E02DEARTE" size="10" maxlength="9" value="<%= inqBasic.getE02DEARTE().trim()%>">
            </td>
            <td nowrap > 
              <div align="right">Fecha de Vencimiento :</div>
            </td>
            <td nowrap > 
              <input type="text" readonly name="E02DEAMD1" size="2" maxlength="2" value="<%= inqBasic.getE02DEAMD1().trim()%>" >
              <input type="text" readonly name="E02DEAMD2" size="2" maxlength="2" value="<%= inqBasic.getE02DEAMD2().trim()%>" >
              <input type="text" readonly name="E02DEAMD3" size="2" maxlength="2" value="<%= inqBasic.getE02DEAMD3().trim()%>" >
            </td>
          </tr>
          <tr id="trdark"> 
            <td nowrap > 
              <div align="right">Per&iacute;odo Base :</div>
            </td>
            <td nowrap > 
              <input type="text" readonly name="E02DEABAS" size="3" maxlength="3" value="<%= inqBasic.getE02DEABAS().trim()%>" >
            </td>
            <td nowrap > 
              <div align="right">T&eacute;rmino :</div>
            </td>
            <td nowrap > 
              <input type="text" readonly name="E02DEATRM" size="4" maxlength="4" value="<%= inqBasic.getE02DEATRM().trim()%>">
              <input type="text" readonly name="E02DEATRC" size="10" 
				  value="<% if (inqBasic.getE02DEATRC().equals("D")) out.print("D&iacute;a(s)");
							else if (inqBasic.getE02DEATRC().equals("M")) out.print("Mes(es)");
							else if (inqBasic.getE02DEATRC().equals("Y")) out.print("A&ntilde;o(s)");
							else out.print("");%>" 
				>
            </td>
          </tr>
          <tr id="trclear"> 
            <td nowrap > 
              <div align="right">Tipo de Inter&eacute;s :</div>
            </td>
            <td nowrap > 
              <input type="text" readonly name="E02DEAICT" size="35" maxlength="25" 
				value="<% if (inqBasic.getE02DEAICT().equals("S")) out.print("Al Vencimiento Calendario");
							else if (inqBasic.getE02DEAICT().equals("M")) out.print("Al Vencimiento Comercial");
							else if (inqBasic.getE02DEAICT().equals("P")) out.print("Prepagados Calendario");
							else if (inqBasic.getE02DEAICT().equals("A")) out.print("Prepagados Comerciales");
							else if (inqBasic.getE02DEAICT().equals("D")) out.print("Descontados Calendario");
							else if (inqBasic.getE02DEAICT().equals("T")) out.print("Descontados Comerciales");
							else if (inqBasic.getE02DEAICT().equals("R")) out.print("Capitalizados(CD's)");
							else if (inqBasic.getE02DEAICT().equals("1")) out.print("Al Vencimiento Calendario");
							else if (inqBasic.getE02DEAICT().equals("2")) out.print("Al Vencimiento Comercial");
							else if (inqBasic.getE02DEAICT().equals("3")) out.print("Prepagados Calendario");
							else if (inqBasic.getE02DEAICT().equals("4")) out.print("Prepagados Comerciales");
							else if (inqBasic.getE02DEAICT().equals("5")) out.print("Descontados Calendario");
							else if (inqBasic.getE02DEAICT().equals("6")) out.print("Descontados Comerciales");
							else if (inqBasic.getE02DEAICT().equals("7")) out.print("Capitalizados (CD's)");
							else if (inqBasic.getE02DEAICT().equals("8")) out.print("Regla 78");
							else out.print("");%>" >
            </td>
            <td nowrap > 
              <div align="right">Tabla de Tasa Flotante :</div>
            </td>
            <td nowrap > 
              <input type="text" readonly name="E02DEAFTB" size="2" maxlength="2" value="<%= inqBasic.getE02DEAFTB().trim()%>">
              <input type="text" readonly name="E02DEAFTY" size="20" 
				  value="<% if (inqBasic.getE02DEAFTY().equals("FP")) out.print("Primaria Flotante");
							else if (inqBasic.getE02DEAFTY().equals("FS")) out.print("Secundaria Flotante");
							else out.print("");%>" 
				>
            </td>
          </tr>
          <tr id="trdark"> 
            <td nowrap > 
              <div align="right">C&aacute;lculo Inter&eacute;s Normal :</div>
            </td>
            <td nowrap > 
              <input type="text" readonly name="E02DEAIFL" size="35" maxlength="35" 
				  value="<% if (inqBasic.getE02DEAIFL().equals("1")) out.print("Capital Vigente");
							else if (inqBasic.getE02DEAIFL().equals("2")) out.print("Capital Original");
							else if (inqBasic.getE02DEAIFL().equals("3")) out.print("Capital Vigente menos Capital Vencido");
							else if (inqBasic.getE02DEAIFL().equals("4")) out.print("No Calcula Intereses");
							else out.print("");%>" >
            </td>
            <td nowrap > 
              <div align="right">Tasa M&aacute;xima Permitida :</div>
            </td>
            <td nowrap > 
              <input type="text" readonly name="E02DEAMXR" size="9" 
				  value="<%= inqBasic.getE02DEAMXR().trim()%>" 
				 maxlength="9">
            </td>
          </tr>
          <tr id="trclear"> 
            <td nowrap > 
              <div align="right">C&aacute;lculo Inter&eacute;s Mora :</div>
            </td>
            <td nowrap > 
              <input type="text" readonly name="E02DEAPCL" size="25" maxlength="25" 
				  value="<% if (inqBasic.getE02DEAPCL().equals("1")) out.print("Sobre Capital Vencido");
							else if (inqBasic.getE02DEAPCL().equals("2")) out.print("Sobre Capital Original");
							else if (inqBasic.getE02DEAPCL().equals("3")) out.print("Sobre Capital Vigente");
							else if (inqBasic.getE02DEAPCL().equals("4")) out.print("Sobre Capital Vencido mas Intereses Vencido");
							else out.print("No Calcula Intereses");%>" >
              Gracia : 
              <input type="text" readonly name="E02DEAGPD" size="2" maxlength="2" value="<%= inqBasic.getE02DEAGPD().trim()%>" >
            </td>
            <td nowrap >
              <div align="right">Tasa M&iacute;nima Permitida :</div>
            </td>
            <td nowrap >
              <input type="text" readonly name="E02DEAMIR" size="9" 
				  value="<%= inqBasic.getE02DEAMIR().trim()%>" 
				 maxlength="9">
            </td>
          </tr>
          <tr id="trdark"> 
            <td nowrap > 
              <div align="right">Tabla de Cargos :</div>
            </td>
            <td nowrap > 
              <input type="text" readonly name="E02DEATLN" size="2" maxlength="2" value="<%= inqBasic.getE02DEATLN().trim()%>" >
            </td>
            <td nowrap > 
              <div align="right">Inter&eacute;s de Mora :</div>
            </td>
            <td nowrap > 
              <input type="text" readonly name="E02DEAPIF" size="2" maxlength="1" value="<%= inqBasic.getE02DEAPIF().trim()%>">
              <input type="text" readonly name="E02DEAPEI" size="7" maxlength="7" value="<%= inqBasic.getE02DEAPEI().trim()%>">
            </td>
          </tr>
          <tr id="trclear"> 
            <td nowrap > 
              <div align="right">Banco / Sucursal :</div>
            </td>
            <td nowrap > 
              <input type="text" readonly name="E02DEABNK" size="2" maxlength="2" value="<%= inqBasic.getE02DEABNK().trim()%>" >
              / 
              <input type="text" readonly name="E02DEABRN" size="3" maxlength="3" value="<%= inqBasic.getE02DEABRN().trim()%>" >
            </td>
            <td nowrap >
              <div align="right">Cuenta Contable :</div>
            </td>
            <td nowrap >
              <input type="text" readonly name="E02DEAGLN" size="16" maxlength="16" value="<%= inqBasic.getE02DEAGLN().trim()%>" >
            </td>
          </tr>
          <tr id="trdark"> 
            <td nowrap > 
              <div align="right">Tasa de Cambio :</div>
            </td>
            <td nowrap > 
              <input type="text" readonly name="E02DEAEXR" size="11" maxlength="11" value="<%= inqBasic.getE02DEAEXR().trim()%>">
            </td>
            <td nowrap > 
              <div align="right">Centro de Costos :</div>
            </td>
            <td nowrap > 
              <input type="text" readonly name="E02DEACCN" size="8" maxlength="8" value="<%= inqBasic.getE02DEACCN().trim()%>" >
            </td>
          </tr>
          <tr id="trclear"> 
            <td nowrap > 
              <div align="right">Contrapartida :</div>
            </td>
            <td nowrap > 
              <input type="text" readonly name="E02DEAOFB" size="2" maxlength="2" value="<%= inqBasic.getE02DEAOFB().trim()%>" >
              <input type="text" readonly name="E02DEAOCR" size="3" maxlength="3" value="<%= inqBasic.getE02DEAOCR().trim()%>" >
              <input type="text" readonly name="E02DEAOCY" size="3" maxlength="3" value="<%= inqBasic.getE02DEAOCY().trim()%>" >
              <input type="text" readonly name="E02DEAOFA" size="16" maxlength="16" value="<%= inqBasic.getE02DEAOFA().trim()%>" >
            </td>
            <td nowrap > 
              <div align="right">L&iacute;nea de Cr&eacute;dito :</div>
            </td>
            <td nowrap > 
              <input type="text" readonly name="E02DEACMC" size="9" maxlength="9" value="<%= inqBasic.getE02DEACMC().trim()%>" >
              <input type="text" readonly name="E02DEACMN" size="4" maxlength="4" value="<%= inqBasic.getE02DEACMN().trim()%>" >
            </td>
          </tr>
          <tr id="trdark"> 
            <td nowrap > 
              <div align="right">Prest&aacute;mo a Demanda :</div>
            </td>
            <td nowrap > 
              <input type="text" readonly name="E02DEALNC" size="4" 
				  value="<% if (inqBasic.getE02DEALNC().equals("Y")) out.print("Si");
							else if (inqBasic.getE02DEALNC().equals("N")) out.print("No");
							else out.print("");%>" 
				 maxlength="2">
            </td>
            <td nowrap > 
              <div align="right">Clase de Cr&eacute;dito :</div>
            </td>
            <td nowrap > 
              <input type="text" readonly name="E02DEACLF" size="35" maxlength="25" 
				value="<% if (inqBasic.getE02DEACLF().equals("A")) out.print("Arrendamiento Financiero");
							else if (inqBasic.getE02DEACLF().equals("C")) out.print("Préstamos de Consumo");
							else if (inqBasic.getE02DEACLF().equals("D")) out.print("Préstamos sobre Saldo Disoluto");
							else if (inqBasic.getE02DEACLF().equals("L")) out.print("Préstamo Regular");
							else if (inqBasic.getE02DEACLF().equals("H")) out.print("Hipotecarios");
							else if (inqBasic.getE02DEACLF().equals("P")) out.print("Politica Habitacional");
							else if (inqBasic.getE02DEACLF().equals("G")) out.print("Descuento Documentos(Factoring)");
							else if (inqBasic.getE02DEACLF().equals("V")) out.print("Valores al Cobro");
							else if (inqBasic.getE02DEACLF().equals("O")) out.print("Para Control de Sobregiros");
							else if (inqBasic.getE02DEACLF().equals("R")) out.print("Préstamo para Refinanciar Otro");
							else if (inqBasic.getE02DEACLF().equals("I")) out.print("Préstamo Credilinea");
							else out.print("Proyectos de Constructor");%>" >
            </td>
          </tr>
          <tr id="trclear"> 
            <td nowrap > 
              <div align="right">Forma C&aacute;lculo IVA :</div>
            </td>
            <td nowrap > 
              <input type="text" readonly name="E02DEAWHF" size="35" maxlength="35" 
				value="<% if (inqBasic.getE02DEAWHF().equals("1")) out.print("Retención sobre Intereses ISR");
							else if (inqBasic.getE02DEAWHF().equals("2")) out.print("Cobre del IVA");
							else if (inqBasic.getE02DEAWHF().equals("3")) out.print("IVA mas ISR");
							else if (inqBasic.getE02DEAWHF().equals("4")) out.print("IVA solo en Comisiones");
							else if (inqBasic.getE02DEAWHF().equals("5")) out.print("IVA solo en Intereses");
							else if (inqBasic.getE02DEAWHF().equals("6")) out.print("Debito Bancario IDB");
							else if (inqBasic.getE02DEAWHF().equals("7")) out.print("IDB mas ISR");
							else if (inqBasic.getE02DEAWHF().equals("8")) out.print("IDB mas IVA");
							else if (inqBasic.getE02DEAWHF().equals("9")) out.print("Todo Tipo de Impuesto");
							else if (inqBasic.getE02DEAWHF().equals("N")) out.print("No Calcula Impuestos");
							else if (inqBasic.getE02DEAWHF().equals("F")) out.print("Cobro del FECI");
							else out.print("");%>" >
            </td>
            <td nowrap > 
              <div align="right">Condici&oacute;n de Cr&eacute;dito :</div>
            </td>
            <td nowrap >
              <input type="text" readonly name="E02DEADLC" size="35" maxlength="15" 
				value="<% if (inqBasic.getE02DEADLC().equals("1")) out.print("Vigente");
							else if (inqBasic.getE02DEADLC().equals("2")) out.print("Vencido");
							else if (inqBasic.getE02DEADLC().equals("3")) out.print("Protestado");
							else if (inqBasic.getE02DEADLC().equals("4")) out.print("Abogados / Notaría");
							else if (inqBasic.getE02DEADLC().equals("5")) out.print("Cobro Judicial");
							else if (inqBasic.getE02DEADLC().equals("6")) out.print("En Proceso de Recuperación");
							else out.print("");%>" >
            </td>
          </tr>
          <tr id="trdark">
            <td nowrap >
              <div align="right">Tipo de Relaci&oacute;n 1 :</div>
            </td>
            <td nowrap >
              <INPUT type="text" readonly name="E02DEAPAR" size="40" maxlength="25" value='<% if (inqBasic.getE02DEAPAR().equals("A")) out.print("Arrendamiento Financiero con Capitalización");
							else if (inqBasic.getE02DEAPAR().equals("B")) out.print("Arrendamiento Financiero con Capitalización");
							else if (inqBasic.getE02DEAPAR().equals("F")) out.print("Fondeo");
							else if (inqBasic.getE02DEAPAR().equals("G")) out.print("Administración de Fondos");
							else if (inqBasic.getE02DEAPAR().equals("P")) out.print("Participación");
							else if (inqBasic.getE02DEAPAR().equals("O")) out.print("Proyectos de Constructor");
							else if (inqBasic.getE02DEAPAR().equals("S")) out.print("Sindicación");
							else if (inqBasic.getE02DEAPAR().equals("T")) out.print("Indexado a Certificado");
							else if (inqBasic.getE02DEAPAR().equals("1")) out.print("Tasa más alta de Certificado");
							else if (inqBasic.getE02DEAPAR().equals("2")) out.print("Tasa más alta de Ahorros");
							else if (inqBasic.getE02DEAPAR().equals("3")) out.print("Tasa más alta de Certificados / Ahorros");
							else out.print("Ninguno de los Anteriores");%>'>
			  <INPUT type="text" readonly name="E02DEAPAC" size="7" maxlength="7" value="<%= inqBasic.getE02DEAPAC().trim()%>">
		    </td>
            <td nowrap >
              <div align="right">N&uacute;mero de Referencia :</div>
            </td>
            <td nowrap >
              <input type="text" readonly name="E02DEAREF" size="12" maxlength="12" value="<%= inqBasic.getE02DEAREF().trim()%>">
            </td>
          </tr>
          <tr id="trclear"> 
            <td nowrap >
              <div align="right">Tipo de Relaci&oacute;n 2 :</div>
            </td>
            <td nowrap >
               <INPUT type="text" readonly name="E02DEARET" size="25" maxlength="25" value='<% if (inqBasic.getE02DEARET().equals("P")) out.print("Pago sobre Préstamos");
							else if (inqBasic.getE02DEARET().equals("R")) out.print("Crédito Renovado");
							else if (inqBasic.getE02DEARET().equals("E")) out.print("Crédito Reestructurado");
							else if (inqBasic.getE02DEARET().equals("F")) out.print("Refinanciar sobre otro Crédito");
							else out.print("Ninguno");%>'>
			   <INPUT type="text" readonly name="E02DEAREA" size="7" maxlength="7" value="<%= inqBasic.getE02DEAREA().trim()%>">
			</td>
            <td nowrap >
              <div align="right">Direcciones de Correo :</div>
            </td>
            <td nowrap width="23%" height="19"><INPUT type="text" readonly name="E02DEAMLA" size="2" maxlength="1" value="<%= inqBasic.getE02DEAMLA().trim()%>"></td>
          </tr>
          <tr id="trdark"> 
            <td nowrap >
              <div align="right">Vendedor :</div>
            </td>
            <td nowrap >
              <input type="text" readonly name="E02DEABRK" size="4" value="<%= inqBasic.getE02DEABRK().trim() %>" >
              <input type="text" readonly name="E02DEAPAC" size="36" value="<%= inqBasic.getE02DSCBRK().trim()%>">
            </td>
            <td nowrap >
              <div align="right">Comisi&oacute;n Vendedor :</div>
            </td>
            <td nowrap >
              <input type="text" readonly name="E02DEABCP" size="9" maxlength="9" value="<%= inqBasic.getE02DEABCP().trim()%>">
            </td>
          </tr>
        </table>
      </td>
    </tr>
  </table>
  <h4>Condiciones de Pagos</h4>
  <table class="tableinfo">
    <tr > 
      <td nowrap> 
        <table cellspacing=0 cellpadding=2 width="100%" border="0">
          <tr id="trdark"> 
            <td nowrap width="27%" > 
              <div align="right">Cuenta a Debitar :</div>
            </td>
            <td nowrap width="26%"> 
              <input type="text" readonly name="E02DEAREB" size="2" maxlength="2" value="<%= inqBasic.getE02DEAREB().trim()%>" >
              <input type="text" readonly name="E02DEARPR" size="3" maxlength="3" value="<%= inqBasic.getE02DEARPR().trim()%>" >
              <input type="text" readonly name="E02DEARPC" size="3" maxlength="3" value="<%= inqBasic.getE02DEARPC().trim()%>" >
              <input type="text" readonly name="E02DEARAC" size="16" maxlength="16" value="<%= inqBasic.getE02DEARAC().trim()%>" >
            </td>
            <td nowrap width="21%" > 
              <div align="right">Autoriza Sobregiro :</div>
            </td>
            <td nowrap colspan="7"> 
              <input type="text" readonly name="E02DEAODA" size="2" 
			  value="<%= inqBasic.getE02DEAODA().trim()%>"  maxlength="2">
            </td>
          </tr>
          <tr id="trclear"> 
            <td nowrap width="27%" > 
              <div align="right">Ciclo / Fecha Refinancia Int. :</div>
            </td>
            <td nowrap width="26%"> 
              <input type="text" readonly name="E02DEAXRC" size="3" maxlength="3" value="<%= inqBasic.getE02DEAXRC().trim()%>" >
              / 
              <input type="text" readonly name="E02DEAXR1" size="2" maxlength="2" value="<%= inqBasic.getE02DEAXR1().trim()%>" >
              <input type="text" readonly name="E02DEAXR2" size="2" maxlength="2" value="<%= inqBasic.getE02DEAXR2().trim()%>" >
              <input type="text" readonly name="E02DEAXR3" size="2" maxlength="2" value="<%= inqBasic.getE02DEAXR3().trim()%>" >
            </td>
            <td nowrap width="21%" > 
              <div align="right">Refinanciar Hasta :</div>
            </td>
            <td nowrap colspan="7"> 
              <input type="text" readonly name="E02DEAPC1" size="2" maxlength="2" value="<%= inqBasic.getE02DEAPC1().trim()%>" >
              <input type="text" readonly name="E02DEAPC2" size="2" maxlength="2" value="<%= inqBasic.getE02DEAPC2().trim()%>" >
              <input type="text" readonly name="E02DEAPC3" size="2" maxlength="2" value="<%= inqBasic.getE02DEAPC3().trim()%>" >
            </td>
          </tr>
          <tr id="trdark"> 
            <td nowrap width="27%" > 
              <div align="right">Monto Pago Final :</div>
            </td>
            <td nowrap width="26%" > 
              <input type="text" readonly name="E02DEABAP" size="15" maxlength="15" value="<%= inqBasic.getE02DEABAP().trim()%>" >
            </td>
            <td nowrap width="21%" > 
              <div align="right">Fecha Pago Final :</div>
            </td>
            <td nowrap  colspan="7"> 
              <input type="text" readonly name="E02DEABA1" size="2" maxlength="2" value="<%= inqBasic.getE02DEABA1().trim()%>" >
              <input type="text" readonly name="E02DEABA2" size="2" maxlength="2" value="<%= inqBasic.getE02DEABA2().trim()%>" >
              <input type="text" readonly name="E02DEABA3" size="2" maxlength="2" value="<%= inqBasic.getE02DEABA3().trim()%>" >
            </td>
          </tr>
          <tr id="trclear"> 
            <td nowrap width="27%" > 
              <div align="right">Mes Excluido en Pago :</div>
            </td>
            <td nowrap width="26%" > 
              <input type="text" readonly name="E02DEARPT" size="2" maxlength="2" value="<%= inqBasic.getE02DEARPT().trim()%>" >
            </td>
            <td nowrap width="21%" > 
              <div align="right">Prioridad Pago :</div>
            </td>
            <td nowrap width="3%" > 
              <p> 
                <input type="text" readonly name="E02DEAPP1" size="2" maxlength="1" value="<%= inqBasic.getE02DEAPP1().trim()%>" >
              </p>
            </td>
            <td nowrap width="3%" > 
              <input type="text" readonly name="E02DEAPP2" size="2" maxlength="1" value="<%= inqBasic.getE02DEAPP2().trim()%>" >
            </td>
            <td nowrap width="3%" > 
              <input type="text" readonly name="E02DEAPP3" size="2" maxlength="1" value="<%= inqBasic.getE02DEAPP3().trim()%>" >
            </td>
            <td nowrap width="3%" > 
              <input type="text" readonly name="E02DEAPP4" size="2" maxlength="1" value="<%= inqBasic.getE02DEAPP4().trim()%>" >
            </td>
            <td nowrap width="2%" > 
              <input type="text" readonly name="E02DEAPP5" size="2" maxlength="1" value="<%= inqBasic.getE02DEAPP5().trim()%>" >
            </td>
            <td nowrap width="2%" > 
              <input type="text" readonly name="E02DEAPP6" size="2" maxlength="1" value="<%= inqBasic.getE02DEAPP6().trim()%>" >
            </td>
            <td nowrap width="10%" height="20">&nbsp;</td>
          </tr>
          <tr id="trclear"> 
            <td nowrap width="27%" height="19"> 
              <div align="right"></div>
            </td>
            <td nowrap width="26%" height="19">&nbsp;</td>
            <td nowrap width="21%" height="19">&nbsp;</td>
            <td nowrap width="3%" > 
              <div align="center"><b> P </b></div>
            </td>
            <td nowrap width="3%" > 
              <div align="center"><b>I</b></div>
            </td>
            <td nowrap width="3%" > 
              <div align="center"><b>M</b></div>
            </td>
            <td nowrap width="3%" > 
              <div align="center"><b>T</b></div>
            </td>
            <td nowrap width="2%" > 
              <div align="center"><b>C</b></div>
            </td>
            <td nowrap width="2%" > 
              <div align="center"><b>D</b></div>
            </td>
            <td nowrap height="19" width="10%">&nbsp;</td>
          </tr>
          <tr id="trdark"> 
            <td nowrap width="27%" > 
              <div align="right">% Programa / Incremento Pago :</div>
            </td>
            <td nowrap width="26%" > 
              <input type="text" readonly name="E02DEAPAP" size="9" maxlength="9" value="<%= inqBasic.getE02DEAPAP().trim()%>" >
              / 
              <input type="text" readonly name="E02DEA2TC" size="2" maxlength="1" value="<%= inqBasic.getE02DEA2TC().trim()%>" >
            </td>
            <td nowrap width="21%" > 
              <div align="right">Ciclo/ Pr&oacute;x. Incr. Pago :</div>
            </td>
            <td nowrap  colspan="7">
              <input type="text" readonly name="E02DEAPCU" size="3" maxlength="3" value="<%= inqBasic.getE02DEAPCU().trim()%>" >
              / 
              <input type="text" readonly name="E02DEALS1" size="2" maxlength="2" value="<%= inqBasic.getE02DEALS1().trim()%>" >
              <input type="text" readonly name="E02DEALS2" size="2" maxlength="2" value="<%= inqBasic.getE02DEALS2().trim()%>" >
              <input type="text" readonly name="E02DEALS3" size="2" maxlength="2" value="<%= inqBasic.getE02DEALS3().trim()%>" >
            </td>
          </tr>
          <tr id="trclear"> 
            <td nowrap width="27%" > 
              <div align="right">Abonos a Capital :</div>
            </td>
            <td nowrap width="26%" > 
              <input type="text" readonly name="E02DLCABC" size="3" maxlength="3" value="<%= inqBasic.getE02DLCABC().trim()%>" >
              <input type="text" readonly name="E02DLCABF" size="2" maxlength="1" value="<%= inqBasic.getE02DLCABF().trim()%>" >
            </td>
            <td nowrap width="21%" > 
              <div align="right">Ciclo/ Pr&oacute;x. Abono Capital :</div>
            </td>
            <td nowrap  colspan="7">
              <input type="text" readonly name="E02DLCABC" size="3" maxlength="3" value="<%= inqBasic.getE02DLCABC().trim()%>" >
              / 
              <input type="text" readonly name="E02DLCAB1" size="2" maxlength="2" value="<%= inqBasic.getE02DLCAB1().trim()%>" >
              <input type="text" readonly name="E02DLCAB2" size="2" maxlength="2" value="<%= inqBasic.getE02DLCAB2().trim()%>" >
              <input type="text" readonly name="E02DLCAB3" size="2" maxlength="2" value="<%= inqBasic.getE02DLCAB3().trim()%>" >
            </td>
          </tr>
        </table>
      </td>
    </tr>
  </table>
  <p align="center">&nbsp; </p>
  </form>
</body>
</html>
