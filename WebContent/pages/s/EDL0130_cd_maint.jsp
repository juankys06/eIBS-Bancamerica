<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.0 Transitional//EN">
<html>
<head>
<title>Información Básica de Certificados de Depósito</title>
<META HTTP-EQUIV="Content-Type" CONTENT="text/html; charset=ISO-8859-1">
<META name="GENERATOR" content="IBM WebSphere Page Designer V3.5.2 for Windows">
<META http-equiv="Content-Style-Type" content="text/css">
<link Href="<%=request.getContextPath()%>/pages/style.css" rel="stylesheet">
<%@ page import = "java.io.*,java.net.*,datapro.eibs.beans.*,datapro.eibs.master.*,java.math.*" %>

<jsp:useBean id="cdMant" class="datapro.eibs.beans.EDL013001Message"  scope="session" />

<jsp:useBean id= "error" class= "datapro.eibs.beans.ELEERRMessage"  scope="session" />

<jsp:useBean id= "userPO" class= "datapro.eibs.beans.UserPos"  scope="session" />

<script language="Javascript1.1" src="<%=request.getContextPath()%>/pages/s/javascripts/eIBS.jsp"> </SCRIPT>
<script language="Javascript1.1" src="<%=request.getContextPath()%>/pages/s/javascripts/optMenu.jsp"> </SCRIPT>

</head>

<body>

<SCRIPT LANGUAGE="JavaScript">
builtHPopUp();
builtNewMenu(cd_m_opt);
initMenu();

function showPopUp(opth,field,bank,ccy,field1,field2,opcod) {
   init(opth,field,bank,ccy,field1,field2,opcod);
   showPopupHelp();
   }
   
</SCRIPT>

<% 
 if ( !error.getERRNUM().equals("0")  ) {
     error.setERRNUM("0");
     out.println("<SCRIPT Language=\"Javascript\">");
     out.println("       showErrors()");
     out.println("</SCRIPT>");
 }
 
 boolean protect = JSEIBSProp.getProtectedBNKBRN();
%>

<h3 align="center"><%=cdMant.getE01DEANR1() %>- Informaci&oacute;n B&aacute;sica
<img src="<%=request.getContextPath()%>/images/e_ibs.gif" align="left" name="EIBS_GIF" ALT="cd_maint.jsp,EDL0130"></h3>
<hr size="4">
<form method="post" action="<%=request.getContextPath()%>/servlet/datapro.eibs.products.JSEXEDL0130" >
  <INPUT TYPE=HIDDEN NAME="SCREEN" VALUE="4">
  <input type=HIDDEN name="E01DEAACD"  value="<%= cdMant.getE01DEAACD().trim()%>">
  <table class="tableinfo">
    <tr bordercolor="#FFFFFF"> 
      <td nowrap> 
        <table cellspacing="0" cellpadding="0" width="100%" >
          <tr id="trdark"> 
            <td nowrap width="16%" > 
              <div align="right"><b>Cliente :</b></div>
            </td>
            <td nowrap width="20%" > 
              <div align="left">
                <input type="text" name="E01DEACUN" size="9" maxlength="9" value="<%= cdMant.getE01DEACUN().trim()%>">
                <a href="javascript:GetCustomer('E01DEACUN')"><img src="<%=request.getContextPath()%>/images/1b.gif" alt=". . ." align="absbottom" border="0" ></a></div>
            </td>
            <td nowrap width="16%" > 
              <div align="right"><b>Nombre :</b> </div>
            </td>
            <td nowrap colspan="3" > 
              <div align="left">
                <input type="text" name="E01CUSNA1" size="45" maxlength="45" value="<%= cdMant.getE01CUSNA1().trim()%>" readonly>
                </div>
            </td>
          </tr>
          <tr id="trdark"> 
            <td nowrap width="16%"> 
              <div align="right"><b>Certificado :</b></div>
            </td>
            <td nowrap width="20%"> 
              <div align="left">
                <input type="text" name="E01DEAACC" size="12" maxlength="12" value="<%= cdMant.getE01DEAACC().trim()%>" readonly>
              </div>
            </td>
            <td nowrap width="16%"> 
              <div align="right"><b>Moneda : </b></div>
            </td>
            <td nowrap width="16%"> 
              <div align="left"><b>
                <input type="text" name="E01DEACCY2" size="3" maxlength="3" value="<%= cdMant.getE01DEACCY().trim()%>" readonly>
                </b> </div>
            </td>
            <td nowrap width="16%"> 
              <div align="right"><b>Producto : </b></div>
            </td>
            <td nowrap width="16%"> 
              <div align="left"><b>
                <input type="text" name="E01DEAPRO" size="4" maxlength="4" value="<%= cdMant.getE01DEAPRO().trim()%>" readonly>
                </b> </div>
            </td>
          </tr>
        </table>
      </td>
    </tr>
  </table>
  <h4>Informaci&oacute;n General</h4>
      <table class="tableinfo">
    <tr > 
      <td nowrap> 
        <table cellspacing=0 cellpadding=2 width="100%" border="0">
          <tr id="trclear"> 
            <td nowrap > 
              <div align="right">Nombre del Certificado :</div>
            </td>
            <td nowrap > 
              <input type="text" name="E01DEANME" size="81" maxlength="80" value="<%= cdMant.getE01DEANME().trim()%>">              
            </td>
            <td nowrap > 
            </td>
            <td nowrap > 
            </td>
          </tr>        
          <tr id="trdark"> 
            <td nowrap > 
              <div align="right">Fecha de Apertura :</div>
            </td>
            <td nowrap > 
              <input type="text" name="E01DEAOD1" size="2" maxlength="2" value="<%= cdMant.getE01DEAOD1().trim()%>" readonly>
              <input type="text" name="E01DEAOD2" size="2" maxlength="2" value="<%= cdMant.getE01DEAOD2().trim()%>" readonly>
              <input type="text" name="E01DEAOD3" size="2" maxlength="2" value="<%= cdMant.getE01DEAOD3().trim()%>" readonly>
            </td>
            <td nowrap > 
              <div align="right">Monto Original :</div>
            </td>
            <td nowrap > 
              <input type="text" name="E01DEAOAM" size="23" maxlength="15" value="<%= cdMant.getE01DEAOAM().trim()%>" readonly onKeypress="enterDecimal()">
            </td>
          </tr>
          <tr id="trclear"> 
            <td nowrap > 
              <div align="right">Fecha de Vencimiento :</div>
            </td>
            <td nowrap > 
              <input type="text" name="E01DEAMD1" size="2" maxlength="2" value="<%= cdMant.getE01DEAMD1().trim()%>" readonly>
              <input type="text" name="E01DEAMD2" size="2" maxlength="2" value="<%= cdMant.getE01DEAMD2().trim()%>" readonly>
              <input type="text" name="E01DEAMD3" size="2" maxlength="2" value="<%= cdMant.getE01DEAMD3().trim()%>" readonly>
              <img src="<%=request.getContextPath()%>/images/Check.gif" alt="campo obligatorio" align="absbottom" border="0" > 
            </td>
            <td nowrap > 
              <div align="right">Saldo Principal :</div>
            </td>
            <td nowrap > 
              <input type="text" name="E01DEAMEP" size="23" maxlength="15" value="<%= cdMant.getE01DEAMEP().trim()%>" readonly>
            </td>
          </tr>
          <tr id="trdark"> 
            <td nowrap > 
              <div align="right">Tasa Actual :</div>
            </td>
            <td nowrap > 
              <input type="text" name="E01RATE" size="10" maxlength="9" value="<%= cdMant.getE01RATE().trim()%>" readonly>
            </td>
            <td nowrap > 
              <div align="right">Saldo de Inter&eacute;s :</div>
            </td>
            <td nowrap > 
              <input type="text" name="E01DEAMEI" size="15" maxlength="15" value="<%= cdMant.getE01DEAMEI().trim()%>" readonly>
            </td>
          </tr>
          <tr id="trclear"> 
            <td nowrap > 
              <div align="right">Per&iacute;odo Base :</div>
            </td>
            <td nowrap > 
              <input type="text" name="E01DEABAS" size="3" maxlength="3" value="<%= cdMant.getE01DEABAS().trim()%>" readonly>
            </td>
            <td nowrap > 
              <div align="right"> Moneda :</div>
            </td>
            <td nowrap > 
              <input type="text" name="E01DEACCY" size="3" maxlength="3" value="<%= cdMant.getE01DEACCY().trim()%>" readonly>
            </td>
          </tr>
        </table>
      </td>
    </tr>
  </table>
  <h4>Datos B&aacute;sicos de la Operaci&oacute;n</h4>
  <table class="tableinfo">
    <tr bordercolor="#FFFFFF"> 
      <td nowrap> 
        <table cellspacing=0 cellpadding=2 width="100%" border="0">
          <tr id="trdark"> 
            <td nowrap > 
              <div align="right">Nuevo  Vencimiento :</div>
            </td>
            <td nowrap > 
              <input type="text" name="E01NEWMD1" size="2" maxlength="2" value="<%= cdMant.getE01NEWMD1().trim()%>">
              <input type="text" name="E01NEWMD2" size="2" maxlength="2" value="<%= cdMant.getE01NEWMD2().trim()%>">
              <input type="text" name="E01NEWMD3" size="2" maxlength="2" value="<%= cdMant.getE01NEWMD3().trim()%>">
              <img src="<%=request.getContextPath()%>/images/Check.gif" alt="campo obligatorio" align="absbottom" border="0" > 
            </td>
            <td nowrap > 
              <div align="right">T&eacute;rmino :</div>
            </td>
            <td nowrap > 
              <div align="left">
              <input type="text" name="E01DEATRM" size="4" maxlength="4" value="<%= cdMant.getE01DEATRM().trim()%>" onKeypress="enterInteger()">
              <select name="E01DEATRC">
                <option value=" " <% if (!(cdMant.getE01DEATRC().equals("D") ||cdMant.getE01DEATRC().equals("M")||cdMant.getE01DEATRC().equals("Y"))) out.print("selected"); %>></option>
                <option value="D" <% if(cdMant.getE01DEATRC().equals("D")) out.print("selected");%>>D&iacute;a(s)</option>
                <option value="M" <% if(cdMant.getE01DEATRC().equals("M")) out.print("selected");%>>Mes(es)</option>
                <option value="Y" <% if(cdMant.getE01DEATRC().equals("Y")) out.print("selected");%>>A&ntilde;o(s)</option>
              </select>
              <img src="<%=request.getContextPath()%>/images/Check.gif" alt="campo obligatorio" align="absbottom" border="0"> 
              </div>
            </td>
          </tr>
          <tr id="trclear"> 
            <td nowrap > 
              <div align="right">Tipo de Tasa Variable :</div>
            </td>
            <td nowrap > 
              <input type="text" name="E01DEAFTB" size="2" maxlength="2" value="<%= cdMant.getE01DEAFTB().trim()%>">
              <a href="javascript:GetFloating('E01DEAFTB')"><img src="<%=request.getContextPath()%>/images/1b.gif" alt="Tabla de Tasas Flotantes" align="absmiddle" border="0" ></a> 
              <select name="E01DEAFTY">
                <option value=" " <% if (!(cdMant.getE01DEAFTY().equals("FP") ||cdMant.getE01DEAFTY().equals("FS"))) out.print("selected"); %>></option>
                <option value="FP" <% if (cdMant.getE01DEAFTY().equals("FP")) out.print("selected"); %>>FP</option>
                <option value="FS" <% if (cdMant.getE01DEAFTY().equals("FS")) out.print("selected"); %>>FS</option>
              </select>
            </td>
            <td nowrap > 
              <div align="right">Tasa Variable :</div>
            </td>
            <td nowrap > 
              <input type="text" name="E01FLTRTE" size="10" maxlength="9" value="<%= cdMant.getE01FLTRTE().trim()%>" readonly>
            </td>
          </tr>
          <tr id="trdark"> 
            <td nowrap > 
              <div align="right">Ciclo/Fecha  Revis. Tasa :</div>
            </td>
            <td nowrap > 
              <input type="text" name="E01DEARRP" size="3" maxlength="3" value="<%= cdMant.getE01DEARRP().trim()%>" onblur="rightAlignCharNumber()">
              / 
              <input type="text" name="E01DEARD1" size="2" maxlength="2" value="<%= cdMant.getE01DEARD1().trim()%>">
              <input type="text" name="E01DEARD2" size="2" maxlength="2" value="<%= cdMant.getE01DEARD2().trim()%>">
              <input type="text" name="E01DEARD3" size="2" maxlength="2" value="<%= cdMant.getE01DEARD3().trim()%>">
            </td>
            <td nowrap > 
              <div align="right">Tasa Inter&eacute;s/Spread :</div>
            </td>
            <td nowrap > 
              <input type="text" name="E01DEARTE" size="10" maxlength="9" value="<%= cdMant.getE01DEARTE().trim()%>" readonly>
            </td>
          </tr>
          <tr id="trclear"> 
            <td nowrap > 
              <div align="right">Retenci&oacute;n/Impuesto :</div>
            </td>
            <td nowrap > 
              <input type="text" name="E01DEAWHF" size="2" maxlength="1" value="<%= cdMant.getE01DEAWHF().trim()%>">
              <a href="javascript:GetCode('E01DEAWHF','STATIC_cd_taxes.jsp')"><img src="<%=request.getContextPath()%>/images/1b.gif" alt=". . ." align="absbottom" border="0" ></a> 
            </td>
            <td nowrap > 
              <div align="right">N&uacute;mero Referencia :</div>
            </td>
            <td nowrap > 
              <input type="text" name="E01DEAREF" size="12" maxlength="12" value="<%= cdMant.getE01DEAREF().trim()%>" onKeypress="enterInteger()">
            </td>
          </tr>
          <tr id="trdark"> 
            <td nowrap > 
              <div align="right">Condici&oacute;n de Contrato :</div>
            </td>
            <td nowrap > 
              <select name="E01DEADLC">
                <option value=" " <% if (!(cdMant.getE01DEADLC().equals("1") ||cdMant.getE01DEADLC().equals("2")||cdMant.getE01DEADLC().equals("3"))) out.print("selected"); %>></option>
                <option value="1" <% if (cdMant.getE01DEADLC().equals("1")) out.print("selected"); %>>Vigente</option>
                <option value="2" <% if (cdMant.getE01DEADLC().equals("2")) out.print("selected"); %>>Vencido</option>
                <option value="3" <% if (cdMant.getE01DEADLC().equals("3")) out.print("selected"); %>>Embargado</option>
              </select>
            </td>
            <td nowrap > 
              <div align="right">Tasa de Cambio :</div>
            </td>
            <td nowrap > 
              <input type="text" name="E01DEAEXR" size="11" maxlength="11" value="<%= cdMant.getE01DEAEXR().trim()%>" onKeypress="enterDecimal()">
            </td>
          </tr>
          <tr id="trclear"> 
            <td nowrap > 
              <div align="right">Tipo de Inter&eacute;s :</div>
            </td>
            <td nowrap > 
              <input type="text" name="E01DEAICT" size="2" maxlength="1" value="<%= cdMant.getE01DEAICT().trim()%>">
              <a href="javascript:GetCode('E01DEAICT','STATIC_cd_formula.jsp')"><img src="<%=request.getContextPath()%>/images/1b.gif" alt=". . ." align="absbottom" border="0" ></a> 
            </td>
            <td nowrap > 
              <div align="right">Calcular Inter&eacute;s :</div>
            </td>
            <td nowrap > 
              <input type="radio" name="E01DEAIFL" value="1" <% if (!cdMant.getE01DEAIFL().equals("N")) out.print("checked"); %>>
              Yes 
              <input type="radio" name="E01DEAIFL" value="N" <% if (cdMant.getE01DEAIFL().equals("N")) out.print("checked"); %>>
              No </td>
          </tr>
          <tr id="trdark"> 
            <td nowrap > 
              <div align="right">Direcciones de Correo:</div>
            </td>
            <td nowrap > 
              <input type="text" name="E01DEAMLA" size="2" maxlength="1" value="<%= cdMant.getE01DEAMLA().trim()%>">
              <a href="javascript:GetMailing('E01DEAMLA',document.forms[0].E01DEACUN.value)"><img src="<%=request.getContextPath()%>/images/1b.gif" alt="Direcciones de Correo del Cliente" align="absmiddle" border="0"></a> 
            </td>
            <td nowrap > 
              <div align="right">Clase de Certificado :</div>
            </td>
            <td nowrap > 
              <input type="text" name="E01DEACLF" size="2" maxlength="1" value="<%= cdMant.getE01DEACLF().trim()%>">
              <a href="javascript:GetCode('E01DEACLF','STATIC_cd_class.jsp')"><img src="<%=request.getContextPath()%>/images/1b.gif" alt=". . ." align="absbottom" border="0"  ></a> 
            </td>
          </tr>
          <tr id="trclear">
            <td nowrap >
              <div align="right">Centro de Costos :</div>
            </td>
            <td nowrap >
              <input type="text" name="E01DEACCN" size="8" maxlength="8" value="<%= cdMant.getE01DEACCN().trim()%>">
              <a href="javascript:GetCostCenter('E01DEACCN','01')"><img src="<%=request.getContextPath()%>/images/1b.gif" alt="Centros de Costo" align="absmiddle" border="0"  ></a> 
            </td>
            <td nowrap >
              <div align="right">Porcentaje Garant&iacute;a :</div>
            </td>
            <td nowrap >
              <input type="text" name="E01DEACPE" size="7" maxlength="7" value="<%= cdMant.getE01DEACPE().trim()%>">
            </td>
          </tr>
          <tr id="trdark">
            <td nowrap>
              <div align="right">Documento en Custodia :</div>
            </td>
            <td nowrap>
             <SELECT name="E01DEASOF">
                <OPTION value="N" <% if (!(cdMant.getE01DEASOF().equals("1") ||cdMant.getE01DEASOF().equals("2"))) out.print("selected"); %>>No Custodia</OPTION>
                <OPTION value="1" <% if (cdMant.getE01DEASOF().equals("1")) out.print("selected"); %>>Electronica</OPTION>
                <OPTION value="2" <% if (cdMant.getE01DEASOF().equals("2")) out.print("selected"); %>>Impreso</OPTION>
              </SELECT>
            </td>   
            <td nowrap > 
              <div align="right">Tabla de Tasa : </div>
            </td>
            <td nowrap >
              <input type="text" name="E01DEARTB" size="1" maxlength="1" value="<%= cdMant.getE01DEARTB().trim()%>">
              <a href="javascript:GetRateTable('E01DEARTB')"><img src="<%=request.getContextPath()%>/images/1b.gif" alt="help" align="absmiddle" border="0"  ></a> 
            </td>                                      
          </tr>
          <tr id="trclear">
            <td nowrap width="25%" > 
              <div align="right">Banco/Sucursal :</div>
            </td>
            <td nowrap width="23%" colspan=3>
            <% if (!protect && cdMant.getH01FLGMAS().equals("N")) {%>
              <input type="text" name="E01DEABNK" size="2" maxlength="2" value="<%= cdMant.getE01DEABNK().trim()%>" >
              <input type="text" name="E01DEABRN" size="2" maxlength="3" value="<%= cdMant.getE01DEABRN().trim()%>">
              <a href="javascript:GetBranch('E01DEABRN','')"><img src="<%=request.getContextPath()%>/images/1b.gif" alt=". . ." align="absbottom" border="0"  ></a>
            <% } else { %>
              <input type="text" name="E01DEABNK" size="2" maxlength="2" value="<%= cdMant.getE01DEABNK().trim()%>" readonly>
              <input type="text" name="E01DEABRN" size="2" maxlength="3" value="<%= cdMant.getE01DEABRN().trim()%>" readonly>
            <% } %>
            </td>
          </tr>
        </table>
      </td>
    </tr>
  </table>
  <% if (cdMant.getH01FLGMAS().equals("N")) {%> 
  <h4>Origen de Fondos</h4>
  
  <TABLE id="mainTable" class="tableinfo">
  <TR><TD>
  
   <table id="headTable" >
    <tr id="trdark"> 
      <td nowrap align="center" > Concepto</td>
      <td nowrap align="center" >Banco </td>
      <td nowrap align="center" >Sucursal</td>
      <td nowrap align="center" >Moneda</td>
      <td nowrap align="center" >Referencia</td>
      <td nowrap align="center" >Monto</td>
    </tr>
    </table> 
      
    <div id="dataDiv" style="height:60; overflow-y :scroll; z-index:0" >
     <table id="dataTable">
          <%
  				   int amount = 9;
 				   String name;
  					for ( int i=1; i<=amount; i++ ) {
   					  name = i + "";
   			%> 
          <tr id="trclear"> 
            <td nowrap > 
              <div align="center" nowrap> 
                <input type="text" name="E01OFFOP<%= name %>" value="<%= cdMant.getField("E01OFFOP"+name).getString().trim()%>" size="3" maxlength="3">
                <input type="hidden" name="E01OFFGL<%= name %>" value="<%= cdMant.getField("E01OFFGL"+name).getString().trim()%>">
                <input type="text" name="E01OFFCO<%= name %>" size="25" maxlength="25" readonly value="<%= cdMant.getField("E01OFFCO"+name).getString().trim()%>" 
                  oncontextmenu="showPopUp(conceptHelp,this.name,document.forms[0].E01DEABNK.value,'','E01OFFGL<%= name %>','E01OFFOP<%= name %>',document.forms[0].E01DEAACD.value); return false;">
              </div>
            </td>
            <td nowrap > 
              <div align="left"> 
                <input type="text" name="E01OFFBK<%= name %>" size="2" maxlength="2" value="<%= cdMant.getField("E01OFFBK"+name).getString().trim()%>">
              </div>
            </td>
            <td nowrap > 
              <div align="left"> 
                <input type="text" name="E01OFFBR<%= name %>" size="3" maxlength="3" value="<%= cdMant.getField("E01OFFBR"+name).getString().trim()%>"
                oncontextmenu="showPopUp(branchHelp,this.name,document.forms[0].E01DEABNK.value,'','','',''); return false;">
              </div>
            </td>
            <td nowrap > 
              <div align="center"> 
                <input type="text" name="E01OFFCY<%= name %>" size="3" maxlength="3" value="<%= cdMant.getField("E01OFFCY"+name).getString().trim()%>"
                oncontextmenu="showPopUp(currencyHelp,this.name,document.forms[0].E01DEABNK.value,'','','',''); return false;">
              </div>
            </td>
            <td nowrap > 
              <div align="center"> 
                <input type="text" name="E01OFFAC<%= name %>" size="12" maxlength="12"  value="<%= cdMant.getField("E01OFFAC"+name).getString().trim()%>"
                oncontextmenu="showPopUp(accountHelp,this.name,document.forms[0].E01DEABNK.value,'','','','RT'); return false;">
              </div>
            </td>
            <td nowrap> 
              <div align="center"> 
                <input type="text" name="E01OFFAM<%= name %>" size="15" maxlength="15"  value="<%= cdMant.getField("E01OFFAM"+name).getString().trim()%>" onKeypress="enterDecimal()">
              </div>
            </td>
          </tr>
          <%
    		}
    		%> 
    	  </table>
        </div>
        
      <table id="footTable" >
          <tr id="trdark"> 
            <td nowrap align="right"><b>Equivalente Moneda del Certificado :</b> 
            </td>
            <td nowrap align="center"><b><i><strong> 
                <input type="text" name="E01OFFEQV" size="15" maxlength="15" readonly value="<%= cdMant.getE01OFFEQV().trim()%>">
                </strong></i></b> 
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
  <% } %> <%
String flag = cdMant.getH01FLGWK3();
%> <%@ include file="ESD0840_reevaluation_inquiry.jsp" %> 
  <% if(error.getERWRNG().equals("Y")){%>
   <h4 style="text-align:center"><input type="checkbox" name="H01FLGWK2" value="A" <% if(cdMant.getH01FLGWK2().equals("A")){ out.print("checked");} %>>
      Aceptar con Errores</h4>
  <% } %>         
  <p align="center"> 
    <input id="EIBSBTN" type=submit name="Submit" value="Enviar">
  </p>

</form>
</body>
</html>
