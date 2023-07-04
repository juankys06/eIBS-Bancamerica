<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.0 Transitional//EN">
<html>
<head>
<title>Transacciones</title>
<META HTTP-EQUIV="Content-Type" CONTENT="text/html; charset=ISO-8859-1">
<META name="GENERATOR" content="IBM WebSphere Page Designer V3.5.2 for Windows">
<META http-equiv="Content-Style-Type" content="text/css">
<link Href="<%=request.getContextPath()%>/pages/style.css" rel="stylesheet">


<script language="Javascript1.1" src="<%=request.getContextPath()%>/pages/s/javascripts/eIBS.jsp"> </SCRIPT>
<script language="Javascript1.1" src="<%=request.getContextPath()%>/pages/s/javascripts/optMenu.jsp"> </SCRIPT>
 
 


<jsp:useBean id= "crossRef" class= "datapro.eibs.beans.EGL034002Message"  scope="session" /> 
<jsp:useBean id= "gLedger" class= "datapro.eibs.beans.EGL034001Message"  scope="session" /> 
<jsp:useBean id= "userPO" class= "datapro.eibs.beans.UserPos"  scope="session" /> 
<jsp:useBean id= "error" class= "datapro.eibs.beans.ELEERRMessage"  scope="session" />
<jsp:useBean id= "currUser" class= "datapro.eibs.beans.ESS0030DSMessage"  scope="session" />

</head>
<body > 

<SCRIPT LANGUAGE="JavaScript">
 builtHPopUp();

 function showPopUp(optHelp,fieldname,bank,ccy,aux1,aux2,opcode) {
   init(optHelp,fieldname,bank,ccy,aux1,aux2,opcode);
   showPopupHelp();
   }   
</SCRIPT>

<% 
 if ( !error.getERRNUM().equals("0")  ) {
      error.setERRNUM("0");
 %>
     <SCRIPT Language="Javascript">
            showErrors();
     </SCRIPT>
 <%
 }
%>


<h3 align="center">Mantenimiento Referencias Cruzadas<img src="<%=request.getContextPath()%>/images/e_ibs.gif" align="left" name="EIBS_GIF" ALT="crossRef_basic,EGL0340"></h3>
<hr size="4">      
<form method="post" action="<%=request.getContextPath()%>/servlet/datapro.eibs.products.JSEGL0340">
  <INPUT TYPE=HIDDEN NAME="SCREEN" VALUE="5">    
  <INPUT TYPE=HIDDEN NAME="E02GLMACD" value="<%= crossRef.getE02GLMACD().trim()%>">
  <table class="tableinfo" id="trdark">
    <tr id="trdark"> 
      <td align="right"  nowrap width="30%" >
       Cuenta Contable :
      </td>
      <td align="right"  nowrap > 
        <div align="left">         
           <input type="text" name="E02GLMBNK" size="3" maxlength="3" readonly value = "<%= crossRef.getE02GLMBNK() %>">         
           <input type="text" name="E02GLMCCY" size="3" maxlength="3" readonly value = "<%= crossRef.getE02GLMCCY() %>">          
           <input type="text" name="E02GLMGLN" size="17" maxlength="16" readonly value = "<%= crossRef.getE02GLMGLN() %>">          
        </div>
      </td>
    </tr>
    <tr id="trdark"> 
      <td align="right" nowrap width="30%"  >
        Descripción :
      </td>
      <td align="right" nowrap > 
        <div align="left"> 
          <input type="text" name="E02GLMDSC" size="35" maxlength="35" readonly value="<%= crossRef.getE02GLMDSC() %>">
        </div>
      </td>
    </tr>   
  </table>
  <% if (crossRef.getE02GLMACD().equals("10")) { %>
  <H4>Modulo de Prestamos</H4>
  <table class="tableinfo">
    <tr  id="trdark">
      <TH align="left" colspan=2>Periodificación de Interes  </TH>
    </tr> 
    <tr  id="trclear">
      <td align="right" width="30%"> 
        Debito por Interes Normal : 
      </td>
      <td align="left" width="60%" nowrap> 
        <input type="text" name="E02GLMXDR" size="17" maxlength="17" value = "<%= crossRef.getE02GLMXDR() %>"
        oncontextmenu="showPopUp(ledgerHelp,this.name,document.forms[0].E02GLMBNK.value,document.forms[0].E02GLMCCY.value,'','','')">
        <input type="text" name="D02GLMXDR" size="35" maxlength="35" value = "<%= crossRef.getD02GLMXDR() %>">
      </td>
    </tr>
    <tr  id="trclear">
      <td align="right" width="30%"> 
        Credito por Interes Normal : 
      </td>
      <td align="left" width="60%" nowrap> 
        <input type="text" name="E02GLMXCR" size="17" maxlength="17" value = "<%= crossRef.getE02GLMXCR() %>"
        oncontextmenu="showPopUp(ledgerHelp,this.name,document.forms[0].E02GLMBNK.value,document.forms[0].E02GLMCCY.value,'','','')">
        <input type="text" name="D02GLMXCR" size="35" maxlength="35" value = "<%= crossRef.getD02GLMXCR() %>">
      </td>
    </tr>
    <tr  id="trclear">
      <td align="right" width="30%"> 
        Debito por Interes de Mora : 
      </td>
      <td align="left" width="60%" nowrap> 
        <input type="text" name="E02GLMXLR" size="17" maxlength="17" value = "<%= crossRef.getE02GLMXLR() %>"
        oncontextmenu="showPopUp(ledgerHelp,this.name,document.forms[0].E02GLMBNK.value,document.forms[0].E02GLMCCY.value,'','','')">
        <input type="text" name="D02GLMXLR" size="35" maxlength="35" value = "<%= crossRef.getD02GLMXLR() %>">
      </td>
    </tr>
    <tr  id="trclear">
      <td align="right" width="30%"> 
        Credito por Interes de Mora : 
      </td>
      <td align="left" width="60%" nowrap> 
        <input type="text" name="E02GLMXLC" size="17" maxlength="17" value = "<%= crossRef.getE02GLMXLC() %>"
        oncontextmenu="showPopUp(ledgerHelp,this.name,document.forms[0].E02GLMBNK.value,document.forms[0].E02GLMCCY.value,'','','')">
        <input type="text" name="D02GLMXLC" size="35" maxlength="35" value = "<%= crossRef.getD02GLMXLC() %>">
      </td>
    </tr>
    <%if(currUser.getE01INT().equals("06")){%> 
    
        <tr  id="trclear">
      <td align="right" width="30%"> 
        Intereses Recibidos Por Anticipado   : 
      </td>
      <td align="left" width="60%" nowrap> 
        <input type="text" name="E02GLXG22" size="17" maxlength="17" value = "<%= crossRef.getE02GLXG22() %>"
        oncontextmenu="showPopUp(ledgerHelp,this.name,document.forms[0].E02GLMBNK.value,document.forms[0].E02GLMCCY.value,'','','')">
        <input type="text" name="D02GLXG22" size="35" maxlength="35" value = "<%= crossRef.getD02GLXG22() %>">
      </td>
    </tr>
        <tr  id="trclear">
      <td align="right" width="30%"> 
        Cta Reguladora Activo para devengo de int. : 
      </td>
      <td align="left" width="60%" nowrap> 
        <input type="text" name="E02GLXG23" size="17" maxlength="17" value = "<%= crossRef.getE02GLXG23() %>" 
        oncontextmenu="showPopUp(ledgerHelp,this.name,document.forms[0].E02GLMBNK.value,document.forms[0].E02GLMCCY.value,'','','')">
        <input type="text" name="D02GLXG23" size="35" maxlength="35" value = "<%= crossRef.getD02GLXG23() %>">
      </td>
    </tr>
        <tr  id="trclear">
      <td align="right" width="30%"> 
          Cta Reguladora Pasivo para devengo de int. : 
      </td>
      <td align="left" width="60%" nowrap> 
        <input type="text" name="E02GLXG24" size="17" maxlength="17" value = "<%= crossRef.getE02GLXG24() %>"
        oncontextmenu="showPopUp(ledgerHelp,this.name,document.forms[0].E02GLMBNK.value,document.forms[0].E02GLMCCY.value,'','','')">
        <input type="text" name="D02GLXG24" size="35" maxlength="35" value = "<%= crossRef.getD02GLXG24() %>">
      </td>
    </tr>
      <% } %>
    <tr  id="trdark">
      <TH align="left" colspan=2>Renovación Reestructuración  </TH>
    </tr>
    <tr  id="trclear">
      <td align="right" width="30%"> 
        Cuenta Debito Contingencia : 
      </td>
      <td align="left" width="60%" nowrap> 
        <input type="text" name="E02GLMXO2" size="17" maxlength="17" value = "<%= crossRef.getE02GLMXO2() %>"
        oncontextmenu="showPopUp(ledgerHelp,this.name,document.forms[0].E02GLMBNK.value,document.forms[0].E02GLMCCY.value,'','','')">
        <input type="text" name="D02GLMXO2" size="35" maxlength="35" value = "<%= crossRef.getD02GLMXO2() %>">
      </td>
    </tr>
    <tr  id="trclear">
      <td align="right" width="30%"> 
        Cuenta Credito Contingencia : 
      </td>
      <td align="left" width="60%" nowrap> 
        <input type="text" name="E02GLMX16" size="17" maxlength="17" value = "<%= crossRef.getE02GLMX16() %>"
        oncontextmenu="showPopUp(ledgerHelp,this.name,document.forms[0].E02GLMBNK.value,document.forms[0].E02GLMCCY.value,'','','')">
        <input type="text" name="D02GLMX16" size="35" maxlength="35" value = "<%= crossRef.getD02GLMX16() %>">
      </td>
    </tr>
    <tr  id="trdark">
      <TH align="left" colspan=2>Entradas Garantias  </TH>
    </tr>
    <tr  id="trclear">
      <td align="right" width="30%"> 
        Cuenta Debito Contingencia : 
      </td>
      <td align="left" width="60%" nowrap> 
        <input type="text" name="E02GLMXIE" size="17" maxlength="17" value = "<%= crossRef.getE02GLMXIE() %>"
        oncontextmenu="showPopUp(ledgerHelp,this.name,document.forms[0].E02GLMBNK.value,document.forms[0].E02GLMCCY.value,'','','')">
        <input type="text" name="D02GLMXIE" size="35" maxlength="35" value = "<%= crossRef.getD02GLMXIE() %>">
      </td>
    </tr>
    <tr  id="trclear">
      <td align="right" width="30%"> 
        Cuenta Credito Contingencia : 
      </td>
      <td align="left" width="60%" nowrap> 
        <input type="text" name="E02GLMDFP" size="17" maxlength="17" value = "<%= crossRef.getE02GLMDFP() %>"
        oncontextmenu="showPopUp(ledgerHelp,this.name,document.forms[0].E02GLMBNK.value,document.forms[0].E02GLMCCY.value,'','','')">
        <input type="text" name="D02GLMDFP" size="35" maxlength="35" value = "<%= crossRef.getD02GLMDFP() %>">
      </td>
    </tr>
    <tr  id="trdark">
      <TH align="left" colspan=2>Referencias Adicionales  </TH>
    </tr>
    <tr  id="trclear">
      <td align="right" width="30%"> 
        Cta Ingresos x Recibir/Orden : 
      </td>
      <td align="left" width="60%" nowrap> 
        <input type="text" name="E02GLMXSO" size="17" maxlength="17" value = "<%= crossRef.getE02GLMXSO() %>"
        oncontextmenu="showPopUp(ledgerHelp,this.name,document.forms[0].E02GLMBNK.value,document.forms[0].E02GLMCCY.value,'','','')">
        <input type="text" name="D02GLMXSO" size="35" maxlength="35" value = "<%= crossRef.getD02GLMXSO() %>">
      </td>
    </tr>
    <tr  id="trclear">
      <td align="right" width="30%"> 
        Cta Ingresos x Mora/Orden : 
      </td>
      <td align="left" width="60%" nowrap> 
        <input type="text" name="E02GLMXSD" size="17" maxlength="17" value = "<%= crossRef.getE02GLMXSD() %>"
        oncontextmenu="showPopUp(ledgerHelp,this.name,document.forms[0].E02GLMBNK.value,document.forms[0].E02GLMCCY.value,'','','')">
        <input type="text" name="D02GLMXSD" size="35" maxlength="35" value = "<%= crossRef.getD02GLMXSD() %>">
      </td>
    </tr>
    <tr  id="trclear">
      <td align="right" width="30%"> 
        Cta Interes Pendiente Activo : 
      </td>
      <td align="left" width="60%" nowrap> 
        <input type="text" name="E02GLMXCC" size="17" maxlength="17" value = "<%= crossRef.getE02GLMXCC() %>"
        oncontextmenu="showPopUp(ledgerHelp,this.name,document.forms[0].E02GLMBNK.value,document.forms[0].E02GLMCCY.value,'','','')">
        <input type="text" name="D02GLMXCC" size="35" maxlength="35" value = "<%= crossRef.getD02GLMXCC() %>">
      </td>
    </tr>
    <tr  id="trclear">
      <td align="right" width="30%"> 
        Cta de Prestamos Vencidos : 
      </td>
      <td align="left" width="60%" nowrap> 
        <input type="text" name="E02GLMXSM" size="17" maxlength="17" value = "<%= crossRef.getE02GLMXSM() %>"
        oncontextmenu="showPopUp(ledgerHelp,this.name,document.forms[0].E02GLMBNK.value,document.forms[0].E02GLMCCY.value,'','','')">
        <input type="text" name="D02GLMXSM" size="35" maxlength="35" value = "<%= crossRef.getD02GLMXSM() %>">
      </td>
    </tr>    
    <tr  id="trclear">
      <td align="right" width="30%"> 
        Cta de Prestamos % Capital Vencido : 
      </td>
      <td align="left" width="60%" nowrap> 
        <input type="text" name="E02GLXG30" size="17" maxlength="17" value = "<%= crossRef.getE02GLXG30() %>"
        oncontextmenu="showPopUp(ledgerHelp,this.name,document.forms[0].E02GLMBNK.value,document.forms[0].E02GLMCCY.value,'','','')">
        <input type="text" name="D02GLXG30" size="35" maxlength="35" value = "<%= crossRef.getD02GLXG30() %>">
      </td>
    </tr>
    <tr  id="trclear">
      <td align="right" width="30%"> 
        Cta Activo Vencido : 
      </td>
      <td align="left" width="60%" nowrap> 
        <input type="text" name="E02GLMXSS" size="17" maxlength="17" value = "<%= crossRef.getE02GLMXSS() %>"
        oncontextmenu="showPopUp(ledgerHelp,this.name,document.forms[0].E02GLMBNK.value,document.forms[0].E02GLMCCY.value,'','','')">
        <input type="text" name="D02GLMXSS" size="35" maxlength="35" value = "<%= crossRef.getD02GLMXSS() %>">
      </td>
    </tr>
    <tr  id="trclear">
      <td align="right" width="30%"> 
        Cta de Prestamo Vigente : 
      </td>
      <td align="left" width="60%" nowrap> 
        <input type="text" name="E02GLMXSR" size="17" maxlength="17" value = "<%= crossRef.getE02GLMXSR() %>"
        oncontextmenu="showPopUp(ledgerHelp,this.name,document.forms[0].E02GLMBNK.value,document.forms[0].E02GLMCCY.value,'','','')">
        <input type="text" name="D02GLMXSR" size="35" maxlength="35" value = "<%= crossRef.getD02GLMXSR() %>">
      </td>
    </tr>
    <tr  id="trclear">
      <td align="right" width="30%"> 
        Prórrogas De Préstamos  : 
      </td>
      <td align="left" width="60%" nowrap> 
        <input type="text" name="E02GLXG21" size="17" maxlength="17" value = "<%= crossRef.getE02GLXG21() %>"
        oncontextmenu="showPopUp(ledgerHelp,this.name,document.forms[0].E02GLMBNK.value,document.forms[0].E02GLMCCY.value,'','','')">
        <input type="text" name="D02GLXG21" size="35" maxlength="35" value = "<%= crossRef.getD02GLXG21() %>">
      </td>
    </tr>
    <tr  id="trclear">
      <td align="right" width="30%"> 
        Gastos Extraordinarios : 
      </td>
      <td align="left" width="60%" nowrap> 
        <input type="text" name="E02GLXG29" size="17" maxlength="17" value = "<%= crossRef.getE02GLXG29() %>"
        oncontextmenu="showPopUp(ledgerHelp,this.name,document.forms[0].E02GLMBNK.value,document.forms[0].E02GLMCCY.value,'','','')">
        <input type="text" name="D02GLXG29" size="35" maxlength="35" value = "<%= crossRef.getD02GLXG29() %>">
      </td>
    </tr>
    
  </table>
  <% } %>
  <% if (crossRef.getE02GLMACD().equals("50") || crossRef.getE02GLMACD().equals("51")) { %>
  <H4>Modulo de Cobranzas</H4>
  <table class="tableinfo"> 
    <tr  id="trclear">
      <td align="right" width="30%"> 
        Comisiones por Cobrar : 
      </td>
      <td align="left" width="60%" nowrap> 
        <input type="text" name="E02GLMXDR" size="17" maxlength="17" value = "<%= crossRef.getE02GLMXDR() %>"
        oncontextmenu="showPopUp(ledgerHelp,this.name,document.forms[0].E02GLMBNK.value,document.forms[0].E02GLMCCY.value,'','','')">
        <input type="text" name="D02GLMXDR" size="35" maxlength="35" value = "<%= crossRef.getD02GLMXDR() %>">
      </td>
    </tr>
    <tr  id="trclear">
      <td align="right" width="30%"> 
        Cta. Contrapartida : 
      </td>
      <td align="left" width="60%" nowrap> 
        <input type="text" name="E02GLMXCR" size="17" maxlength="17" value = "<%= crossRef.getE02GLMXCR() %>"
        oncontextmenu="showPopUp(ledgerHelp,this.name,document.forms[0].E02GLMBNK.value,document.forms[0].E02GLMCCY.value,'','','')">
        <input type="text" name="D02GLMXCR" size="35" maxlength="35" value = "<%= crossRef.getD02GLMXCR() %>">
      </td>
    </tr>
    <tr  id="trclear">
      <td align="right" width="30%"> 
        Retenciones de Impuestos : 
      </td>
      <td align="left" width="60%" nowrap> 
        <input type="text" name="E02GLMX16" size="17" maxlength="17" value = "<%= crossRef.getE02GLMX16() %>"
        oncontextmenu="showPopUp(ledgerHelp,this.name,document.forms[0].E02GLMBNK.value,document.forms[0].E02GLMCCY.value,'','','')">
        <input type="text" name="D02GLMX16" size="35" maxlength="35" value = "<%= crossRef.getD02GLMX16() %>">
      </td>
    </tr>
    <tr  id="trclear">
      <td align="right" width="30%"> 
        Otras Retenciones : 
      </td>
      <td align="left" width="60%" nowrap> 
        <input type="text" name="E02GLMX17" size="17" maxlength="17" value = "<%= crossRef.getE02GLMX17() %>"
        oncontextmenu="showPopUp(ledgerHelp,this.name,document.forms[0].E02GLMBNK.value,document.forms[0].E02GLMCCY.value,'','','')">
        <input type="text" name="D02GLMX17" size="35" maxlength="35" value = "<%= crossRef.getD02GLMX17() %>">
      </td>
    </tr>
    <tr  id="trclear">
      <td align="right" width="30%"> 
        Intereses de Terceros : 
      </td>
      <td align="left" width="60%" nowrap> 
        <input type="text" name="E02GLMXST" size="17" maxlength="17" value = "<%= crossRef.getE02GLMXST() %>"
        oncontextmenu="showPopUp(ledgerHelp,this.name,document.forms[0].E02GLMBNK.value,document.forms[0].E02GLMCCY.value,'','','')">
        <input type="text" name="D02GLMXST" size="35" maxlength="35" value = "<%= crossRef.getD02GLMXST() %>">
      </td>
    </tr>
    <tr  id="trclear">
      <td align="right" width="30%"> 
        Gastos del Corresponsal : 
      </td>
      <td align="left" width="60%" nowrap> 
        <input type="text" name="E02GLMXIE" size="17" maxlength="17" value = "<%= crossRef.getE02GLMXIE() %>"
        oncontextmenu="showPopUp(ledgerHelp,this.name,document.forms[0].E02GLMBNK.value,document.forms[0].E02GLMCCY.value,'','','')">
        <input type="text" name="D02GLMXIE" size="35" maxlength="35" value = "<%= crossRef.getD02GLMXIE() %>">
      </td>
    </tr>
  </table>
  <% } %>
  <% if (crossRef.getE02GLMACD().equals("40") || crossRef.getE02GLMACD().equals("41") || crossRef.getE02GLMACD().equals("42")) { %>
  <H4>Modulo de Cartas de Credito</H4>
  <table class="tableinfo"> 
    <tr  id="trclear">
      <td align="right" width="30%"> 
        Comisiones por Cobrar : 
      </td>
      <td align="left" width="60%" nowrap> 
        <input type="text" name="E02GLMXDR" size="17" maxlength="17" value = "<%= crossRef.getE02GLMXDR() %>"
        oncontextmenu="showPopUp(ledgerHelp,this.name,document.forms[0].E02GLMBNK.value,document.forms[0].E02GLMCCY.value,'','','')">
        <input type="text" name="D02GLMXDR" size="35" maxlength="35" value = "<%= crossRef.getD02GLMXDR() %>">
      </td>
    </tr>
    <tr  id="trclear">
      <td align="right" width="30%"> 
        Cuenta Contrapartida : 
      </td>
      <td align="left" width="60%" nowrap> 
        <input type="text" name="E02GLMXCR" size="17" maxlength="17" value = "<%= crossRef.getE02GLMXCR() %>"
        oncontextmenu="showPopUp(ledgerHelp,this.name,document.forms[0].E02GLMBNK.value,document.forms[0].E02GLMCCY.value,'','','')">
        <input type="text" name="D02GLMXCR" size="35" maxlength="35" value = "<%= crossRef.getD02GLMXCR() %>">
      </td>
    </tr>
    <tr  id="trclear">
      <td align="right" width="30%"> 
        Cta. Debito Aceptaciones: 
      </td>
      <td align="left" width="60%" nowrap> 
        <input type="text" name="E02GLMXSD" size="17" maxlength="17" value = "<%= crossRef.getE02GLMXSD() %>"
        oncontextmenu="showPopUp(ledgerHelp,this.name,document.forms[0].E02GLMBNK.value,document.forms[0].E02GLMCCY.value,'','','')">
        <input type="text" name="D02GLMXSD" size="35" maxlength="35" value = "<%= crossRef.getD02GLMXSD() %>">
      </td>
    </tr>
    <tr  id="trclear">
      <td align="right" width="30%"> 
        Cta. Credito Aceptaciones : 
      </td>
      <td align="left" width="60%" nowrap> 
        <input type="text" name="E02GLMXST" size="17" maxlength="17" value = "<%= crossRef.getE02GLMXST() %>"
        oncontextmenu="showPopUp(ledgerHelp,this.name,document.forms[0].E02GLMBNK.value,document.forms[0].E02GLMCCY.value,'','','')">
        <input type="text" name="D02GLMXST" size="35" maxlength="35" value = "<%= crossRef.getD02GLMXST() %>">
      </td>
    </tr>
    <tr  id="trclear">
      <td align="right" width="30%"> 
        Cta. Garantia Efectivo : 
      </td>
      <td align="left" width="60%" nowrap> 
        <input type="text" name="E02GLMXSR" size="17" maxlength="17" value = "<%= crossRef.getE02GLMXSR() %>"
        oncontextmenu="showPopUp(ledgerHelp,this.name,document.forms[0].E02GLMBNK.value,document.forms[0].E02GLMCCY.value,'','','')">
        <input type="text" name="D02GLMXSR" size="35" maxlength="35" value = "<%= crossRef.getD02GLMXSR() %>">
      </td>
    </tr>
    <tr  id="trclear">
      <td align="right" width="30%"> 
        Aceptaciones Descontadas : 
      </td>
      <td align="left" width="60%" nowrap> 
        <input type="text" name="E02GLMXSO" size="17" maxlength="17" value = "<%= crossRef.getE02GLMXSO() %>"
        oncontextmenu="showPopUp(ledgerHelp,this.name,document.forms[0].E02GLMBNK.value,document.forms[0].E02GLMCCY.value,'','','')">
        <input type="text" name="D02GLMXSO" size="35" maxlength="35" value = "<%= crossRef.getD02GLMXSO() %>">
      </td>
    </tr>
    <tr  id="trclear">
      <td align="right" width="30%"> 
        Refinanciamientos (Prestamos) : 
      </td>
      <td align="left" width="60%" nowrap> 
        <input type="text" name="E02GLMXSS" size="17" maxlength="17" value = "<%= crossRef.getE02GLMXSS() %>"
        oncontextmenu="showPopUp(ledgerHelp,this.name,document.forms[0].E02GLMBNK.value,document.forms[0].E02GLMCCY.value,'','','')">
        <input type="text" name="D02GLMXSS" size="35" maxlength="35" value = "<%= crossRef.getD02GLMXSS() %>">
      </td>
    </tr>
    <tr  id="trclear">
      <td align="right" width="30%"> 
        Cta. Debito Pagos Diferidos  : 
      </td>
      <td align="left" width="60%" nowrap> 
        <input type="text" name="E02GLMXIE" size="17" maxlength="17" value = "<%= crossRef.getE02GLMXIE() %>"
        oncontextmenu="showPopUp(ledgerHelp,this.name,document.forms[0].E02GLMBNK.value,document.forms[0].E02GLMCCY.value,'','','')">
        <input type="text" name="D02GLMXIE" size="35" maxlength="35" value = "<%= crossRef.getD02GLMXIE() %>">
      </td>
    </tr>
    <tr  id="trclear">
      <td align="right" width="30%"> 
        Cta. Credito Pagos Diferidos : 
      </td>
      <td align="left" width="60%" nowrap> 
        <input type="text" name="E02GLMDFP" size="17" maxlength="17" value = "<%= crossRef.getE02GLMDFP() %>"
        oncontextmenu="showPopUp(ledgerHelp,this.name,document.forms[0].E02GLMBNK.value,document.forms[0].E02GLMCCY.value,'','','')">
        <input type="text" name="D02GLMDFP" size="35" maxlength="35" value = "<%= crossRef.getD02GLMDFP() %>">
      </td>
    </tr>
    <tr  id="trdark">
      <TH align="left" colspan=2>Periodificación de Interes  </TH>
    </tr>
    <tr  id="trclear">
      <td align="right" width="30%"> 
        Cta. Debito por Intereses : 
      </td>
      <td align="left" width="60%" nowrap> 
        <input type="text" name="E02GLMX21" size="17" maxlength="17" value = "<%= crossRef.getE02GLMX21() %>"
        oncontextmenu="showPopUp(ledgerHelp,this.name,document.forms[0].E02GLMBNK.value,document.forms[0].E02GLMCCY.value,'','','')">
        <input type="text" name="D02GLMX21" size="35" maxlength="35" value = "<%= crossRef.getD02GLMX21() %>">
      </td>
    </tr>
    
    <tr  id="trclear">
      <td align="right" width="30%"> 
        Cta. Credito por Intereses : 
      </td>
      <td align="left" width="60%" nowrap> 
        <input type="text" name="E02GLMX22" size="17" maxlength="17" value = "<%= crossRef.getE02GLMX22() %>"
        oncontextmenu="showPopUp(ledgerHelp,this.name,document.forms[0].E02GLMBNK.value,document.forms[0].E02GLMCCY.value,'','','')">
        <input type="text" name="D02GLMX22" size="35" maxlength="35" value = "<%= crossRef.getD02GLMX22() %>">
      </td>
    </tr>   
  </table>
  <% } %>
  <% if (crossRef.getE02GLMACD().equals("01") || crossRef.getE02GLMACD().equals("02") || crossRef.getE02GLMACD().equals("03") || crossRef.getE02GLMACD().equals("04")) { %>
  <H4>Modulo de Depositos a la Vista</H4>
  <table class="tableinfo">
    <tr  id="trdark">
      <TH align="left" colspan=2>Creditos </TH>
    </tr> 
    <tr  id="trclear">
      <td align="right" width="30%">Cta. Credito Interes : </td>
      <td align="left" width="60%" nowrap> 
        <input type="text" name="E02GLMXCR" size="17" maxlength="17" value = "<%= crossRef.getE02GLMXCR() %>"
        oncontextmenu="showPopUp(ledgerHelp,this.name,document.forms[0].E02GLMBNK.value,document.forms[0].E02GLMCCY.value,'','','')">
        <input type="text" name="D02GLMXCR" size="35" maxlength="35" value = "<%= crossRef.getD02GLMXCR() %>">
      </td>
    </tr>
    <tr  id="trclear">
      <td align="right" width="30%">C/ Servicio por Saldo Minimo : </td>
      <td align="left" width="60%" nowrap> 
        <input type="text" name="E02GLMXSM" size="17" maxlength="17" value = "<%= crossRef.getE02GLMXSM() %>"
        oncontextmenu="showPopUp(ledgerHelp,this.name,document.forms[0].E02GLMBNK.value,document.forms[0].E02GLMCCY.value,'','','')">
        <input type="text" name="D02GLMXSM" size="35" maxlength="35" value = "<%= crossRef.getD02GLMXSM() %>">
      </td>
    </tr>
    <tr  id="trclear">
      <td align="right" width="30%">C/ Servicio Diferidos/Sobregiro : </td>
      <td align="left" width="60%" nowrap> 
        <input type="text" name="E02GLMXSR" size="17" maxlength="17" value = "<%= crossRef.getE02GLMXSR() %>"
        oncontextmenu="showPopUp(ledgerHelp,this.name,document.forms[0].E02GLMBNK.value,document.forms[0].E02GLMCCY.value,'','','')">
        <input type="text" name="D02GLMXSR" size="35" maxlength="35" value = "<%= crossRef.getD02GLMXSR() %>">
      </td>
    </tr>
    <tr  id="trclear">
      <td align="right" width="30%">C/Servicio Suspencion Pagos : </td>
      <td align="left" width="60%" nowrap> 
        <input type="text" name="E02GLMXSS" size="17" maxlength="17" value = "<%= crossRef.getE02GLMXSS() %>"
        oncontextmenu="showPopUp(ledgerHelp,this.name,document.forms[0].E02GLMBNK.value,document.forms[0].E02GLMCCY.value,'','','')">
        <input type="text" name="D02GLMXSS" size="35" maxlength="35" value = "<%= crossRef.getD02GLMXSS() %>">
      </td>
    </tr>
        <tr  id="trclear">
      <td align="right" width="30%">C/Servicio Cuentas Inactivas : </td>
      <td align="left" width="60%" nowrap> 
        <input type="text" name="E02GLMXSD" size="17" maxlength="17" value = "<%= crossRef.getE02GLMXSD() %>"
        oncontextmenu="showPopUp(ledgerHelp,this.name,document.forms[0].E02GLMBNK.value,document.forms[0].E02GLMCCY.value,'','','')">
        <input type="text" name="D02GLMXSD" size="35" maxlength="35" value = "<%= crossRef.getD02GLMXSD() %>">
      </td>
    </tr>
    <tr  id="trclear">
      <td align="right" width="30%">C/Servicio Varios : </td>
      <td align="left" width="60%" nowrap> 
        <input type="text" name="E02GLMXST" size="17" maxlength="17" value = "<%= crossRef.getE02GLMXST() %>"
        oncontextmenu="showPopUp(ledgerHelp,this.name,document.forms[0].E02GLMBNK.value,document.forms[0].E02GLMCCY.value,'','','')">
        <input type="text" name="D02GLMXST" size="35" maxlength="35" value = "<%= crossRef.getD02GLMXST() %>">
      </td>
    </tr>
        <tr  id="trclear">
      <td align="right" width="30%">Interes por Sobregiro : </td>
      <td align="left" width="60%" nowrap> 
        <input type="text" name="E02GLMXSO" size="17" maxlength="17" value = "<%= crossRef.getE02GLMXSO() %>"
        oncontextmenu="showPopUp(ledgerHelp,this.name,document.forms[0].E02GLMBNK.value,document.forms[0].E02GLMCCY.value,'','','')">
        <input type="text" name="D02GLMXSO" size="35" maxlength="35" value = "<%= crossRef.getD02GLMXSO() %>">
      </td>
    </tr>
    <tr  id="trclear">
      <td align="right" width="30%">Garantias (Contingencia) : </td>
      <td align="left" width="60%" nowrap> 
        <input type="text" name="E02GLMXDR" size="17" maxlength="17" value = "<%= crossRef.getE02GLMXDR() %>"
        oncontextmenu="showPopUp(ledgerHelp,this.name,document.forms[0].E02GLMBNK.value,document.forms[0].E02GLMCCY.value,'','','')">
        <input type="text" name="D02GLMXDR" size="35" maxlength="35" value = "<%= crossRef.getD02GLMXDR() %>">
      </td>
    </tr>
    <tr  id="trclear">
      <td align="right" width="30%">Comisiones x Cancelaciones : </td>
      <td align="left" width="60%" nowrap> 
        <input type="text" name="E02GLMXCC" size="17" maxlength="17" value = "<%= crossRef.getE02GLMXCC() %>"
        oncontextmenu="showPopUp(ledgerHelp,this.name,document.forms[0].E02GLMBNK.value,document.forms[0].E02GLMCCY.value,'','','')">
        <input type="text" name="D02GLMXCC" size="35" maxlength="35" value = "<%= crossRef.getD02GLMXCC() %>">
      </td>
    </tr>
        <tr  id="trclear">
      <td align="right" width="30%">Cheques Certificados : </td>
      <td align="left" width="60%" nowrap> 
        <input type="text" name="E02GLMX17" size="17" maxlength="17" value = "<%= crossRef.getE02GLMX17() %>"
        oncontextmenu="showPopUp(ledgerHelp,this.name,document.forms[0].E02GLMBNK.value,document.forms[0].E02GLMCCY.value,'','','')">
        <input type="text" name="D02GLMX17" size="35" maxlength="35" value = "<%= crossRef.getD02GLMX17() %>">
      </td>
    </tr>
    <tr  id="trclear">
      <td align="right" width="30%">Interes en Suspenso x Sobregiros : </td>
      <td align="left" width="60%" nowrap> 
        <input type="text" name="E02GLMXLC" size="17" maxlength="17" value = "<%= crossRef.getE02GLMXLC() %>"
        oncontextmenu="showPopUp(ledgerHelp,this.name,document.forms[0].E02GLMBNK.value,document.forms[0].E02GLMCCY.value,'','','')">
        <input type="text" name="D02GLMXLC" size="35" maxlength="35" value = "<%= crossRef.getD02GLMXLC() %>">
      </td>
    </tr>
	<TR>
		<TD align="right" width="30%">Cargo de Timbres x Cheque :</TD>
		<TD align="left" width="60%" nowrap><INPUT type="text"
			name="E02GLMX28" size="17" maxlength="17"
			value="<%= crossRef.getE02GLMX28() %>"
			oncontextmenu="showPopUp(ledgerHelp,this.name,document.forms[0].E02GLMBNK.value,document.forms[0].E02GLMCCY.value,'','','')"> <INPUT
			type="text" name="D02GLMX28" size="35" maxlength="35"
			value="<%= crossRef.getD02GLMX28() %>"></TD>
	</TR>
	<tr  id="trdark">
      <TH align="left" colspan=2>Debitos </TH>
    </tr>
    <tr  id="trclear">
      <td align="right" width="30%"> 
        Contra Sobregiro Garantizado : 
      </td>
      <td align="left" width="60%" nowrap> 
        <input type="text" name="E02GLMXLR" size="17" maxlength="17" value = "<%= crossRef.getE02GLMXLR() %>"
        oncontextmenu="showPopUp(ledgerHelp,this.name,document.forms[0].E02GLMBNK.value,document.forms[0].E02GLMCCY.value,'','','')">
        <input type="text" name="D02GLMXLR" size="35" maxlength="35" value = "<%= crossRef.getD02GLMXLR() %>">
      </td>
    </tr>
    <tr  id="trclear">
      <td align="right" width="30%"> 
        Contra Sobregiro No Garantizado : 
      </td>
      <td align="left" width="60%" nowrap> 
        <input type="text" name="E02GLMXOD" size="17" maxlength="17" value = "<%= crossRef.getE02GLMXOD() %>"
        oncontextmenu="showPopUp(ledgerHelp,this.name,document.forms[0].E02GLMBNK.value,document.forms[0].E02GLMCCY.value,'','','')">
        <input type="text" name="D02GLMXOD" size="35" maxlength="35" value = "<%= crossRef.getD02GLMXOD() %>">
      </td>
    </tr>
    <tr  id="trclear">
      <td align="right" width="30%"> 
        Contra Sobregiro Mayor 2 Dias : 
      </td>
      <td align="left" width="60%" nowrap> 
        <input type="text" name="E02GLMXO2" size="17" maxlength="17" value = "<%= crossRef.getE02GLMXO2() %>"
        oncontextmenu="showPopUp(ledgerHelp,this.name,document.forms[0].E02GLMBNK.value,document.forms[0].E02GLMCCY.value,'','','')">
        <input type="text" name="D02GLMXO2" size="35" maxlength="35" value = "<%= crossRef.getD02GLMXO2() %>">
      </td>
    </tr>
    <tr  id="trclear">
      <td align="right" width="30%"> 
        Gastos Intereses (MMK,SAV,NOW) : 
      </td>
      <td align="left" width="60%" nowrap> 
        <input type="text" name="E02GLMXIE" size="17" maxlength="17" value = "<%= crossRef.getE02GLMXIE() %>"
        oncontextmenu="showPopUp(ledgerHelp,this.name,document.forms[0].E02GLMBNK.value,document.forms[0].E02GLMCCY.value,'','','')">
        <input type="text" name="D02GLMXIE" size="35" maxlength="35" value = "<%= crossRef.getD02GLMXIE() %>">
      </td>
    </tr>
    <tr  id="trclear">
      <td align="right" width="30%"> 
        Garantias (Contingencia) : 
      </td>
      <td align="left" width="60%" nowrap> 
        <input type="text" name="E02GLMDFP" size="17" maxlength="17" value = "<%= crossRef.getE02GLMDFP() %>"
        oncontextmenu="showPopUp(ledgerHelp,this.name,document.forms[0].E02GLMBNK.value,document.forms[0].E02GLMCCY.value,'','','')">
        <input type="text" name="D02GLMDFP" size="35" maxlength="35" value = "<%= crossRef.getD02GLMDFP() %>">
      </td>
    </tr>
    <tr  id="trclear">
      <td align="right" width="30%"> 
        Interes de Sobregiro a Cobrar : 
      </td>
      <td align="left" width="60%" nowrap> 
        <input type="text" name="E02GLMX16" size="17" maxlength="17" value = "<%= crossRef.getE02GLMX16() %>"
        oncontextmenu="showPopUp(ledgerHelp,this.name,document.forms[0].E02GLMBNK.value,document.forms[0].E02GLMCCY.value,'','','')">
        <input type="text" name="D02GLMX16" size="35" maxlength="35" value = "<%= crossRef.getD02GLMX16() %>">
      </td>
    </tr>
    <tr  id="trclear">
      <td align="right" width="30%"> 
        Por Contra Cuentas Inactivas : 
      </td>
      <td align="left" width="60%" nowrap> 
        <input type="text" name="E02GLMX19" size="17" maxlength="17" value = "<%= crossRef.getE02GLMX19() %>"
        oncontextmenu="showPopUp(ledgerHelp,this.name,document.forms[0].E02GLMBNK.value,document.forms[0].E02GLMCCY.value,'','','')">
        <input type="text" name="D02GLMX19" size="35" maxlength="35" value = "<%= crossRef.getD02GLMX19() %>">
      </td>
    </tr>
    <tr  id="trclear">
      <td align="right" width="30%"> 
        Por Contra Cuentas Dormidas : 
      </td>
      <td align="left" width="60%" nowrap> 
        <input type="text" name="E02GLMX20" size="17" maxlength="17" value = "<%= crossRef.getE02GLMX20() %>"
        oncontextmenu="showPopUp(ledgerHelp,this.name,document.forms[0].E02GLMBNK.value,document.forms[0].E02GLMCCY.value,'','','')">
        <input type="text" name="D02GLMX20" size="35" maxlength="35" value = "<%= crossRef.getD02GLMX20() %>">
      </td>
    </tr>
    <tr  id="trdark">
      <TH align="left" colspan=2>Intereses de Sobregiros  </TH>
    </tr>
    <tr  id="trclear">
      <td align="right" width="30%"> 
        Contingencias Debito : 
      </td>
      <td align="left" width="60%" nowrap> 
        <input type="text" name="E02GLMX26" size="17" maxlength="17" value = "<%= crossRef.getE02GLMX26() %>"
        oncontextmenu="showPopUp(ledgerHelp,this.name,document.forms[0].E02GLMBNK.value,document.forms[0].E02GLMCCY.value,'','','')">
        <input type="text" name="D02GLMX26" size="35" maxlength="35" value = "<%= crossRef.getD02GLMX26() %>">
      </td>
    </tr>
    <tr  id="trclear">
      <td align="right" width="30%"> 
        Contingencias Credito : 
      </td>
      <td align="left" width="60%" nowrap> 
        <input type="text" name="E02GLMX27" size="17" maxlength="17" value = "<%= crossRef.getE02GLMX27() %>"
        oncontextmenu="showPopUp(ledgerHelp,this.name,document.forms[0].E02GLMBNK.value,document.forms[0].E02GLMCCY.value,'','','')">
        <input type="text" name="D02GLMX27" size="35" maxlength="35" value = "<%= crossRef.getD02GLMX27() %>">
      </td>
    </tr>
    <tr  id="trdark">
      <TH align="left" colspan=2>Otras Entradas  </TH>
    </tr>
    <tr  id="trclear">
      <td align="right" width="30%"> 
        Diferidos a 24 Horas : 
      </td>
      <td align="left" width="60%" nowrap> 
        <input type="text" name="E02GLMX21" size="17" maxlength="17" value = "<%= crossRef.getE02GLMX21() %>"
        oncontextmenu="showPopUp(ledgerHelp,this.name,document.forms[0].E02GLMBNK.value,document.forms[0].E02GLMCCY.value,'','','')">
        <input type="text" name="D02GLMX21" size="35" maxlength="35" value = "<%= crossRef.getD02GLMX21() %>">
      </td>
    </tr>
    <tr  id="trclear">
      <td align="right" width="30%"> 
        Diferidos a 48 Horas : 
      </td>
      <td align="left" width="60%" nowrap> 
        <input type="text" name="E02GLMX22" size="17" maxlength="17" value = "<%= crossRef.getE02GLMX22() %>"
        oncontextmenu="showPopUp(ledgerHelp,this.name,document.forms[0].E02GLMBNK.value,document.forms[0].E02GLMCCY.value,'','','')">
        <input type="text" name="D02GLMX22" size="35" maxlength="35" value = "<%= crossRef.getD02GLMX22() %>">
      </td>
    </tr>    
    <tr  id="trclear">
      <td align="right" width="30%"> 
        Diferidos a 72 Horas : 
      </td>
      <td align="left" width="60%" nowrap> 
        <input type="text" name="E02GLMX23" size="17" maxlength="17" value = "<%= crossRef.getE02GLMX23() %>"
        oncontextmenu="showPopUp(ledgerHelp,this.name,document.forms[0].E02GLMBNK.value,document.forms[0].E02GLMCCY.value,'','','')">
        <input type="text" name="D02GLMX23" size="35" maxlength="35" value = "<%= crossRef.getD02GLMX23() %>">
      </td>
    </tr>
    <tr  id="trclear">
      <td align="right" width="30%"> 
        Diferidos a 96 Horas : 
      </td>
      <td align="left" width="60%" nowrap> 
        <input type="text" name="E02GLMX24" size="17" maxlength="17" value = "<%= crossRef.getE02GLMX24() %>"
        oncontextmenu="showPopUp(ledgerHelp,this.name,document.forms[0].E02GLMBNK.value,document.forms[0].E02GLMCCY.value,'','','')">
        <input type="text" name="D02GLMX24" size="35" maxlength="35" value = "<%= crossRef.getD02GLMX24() %>">
      </td>
    </tr>
    <tr  id="trclear">
      <td align="right" width="30%"> 
        Diferidos Mas 96 Horas : 
      </td>
      <td align="left" width="60%" nowrap> 
        <input type="text" name="E02GLMX25" size="17" maxlength="17" value = "<%= crossRef.getE02GLMX25() %>"
        oncontextmenu="showPopUp(ledgerHelp,this.name,document.forms[0].E02GLMBNK.value,document.forms[0].E02GLMCCY.value,'','','')">
        <input type="text" name="D02GLMX25" size="35" maxlength="35" value = "<%= crossRef.getD02GLMX25() %>">
      </td>
    </tr>
     <%if(currUser.getE01INT().equals("21")){%> 
    <tr  id="trclear">
      <td align="right" width="30%">Comision Pendiente x Cobrar DR : 
      </td>
      <td align="left" width="60%" nowrap> 
        <input type="text" name="E02GLXG01" size="17" maxlength="17" value = "<%= crossRef.getE02GLXG01() %>"
        oncontextmenu="showPopUp(ledgerHelp,this.name,document.forms[0].E02GLMBNK.value,document.forms[0].E02GLMCCY.value,'','','')">
        <input type="text" name="D02GLXG01" size="35" maxlength="35" value = "<%= crossRef.getD02GLXG01() %>">
      </td>
    </tr>
    <tr  id="trclear">
      <td align="right" width="30%"> 
        Comision Pendiente x Cobrar CR : 
      </td>
      <td align="left" width="60%" nowrap> 
        <input type="text" name="E02GLXG02" size="17" maxlength="17" value = "<%= crossRef.getE02GLXG02() %>"
        oncontextmenu="showPopUp(ledgerHelp,this.name,document.forms[0].E02GLMBNK.value,document.forms[0].E02GLMCCY.value,'','','')">
        <input type="text" name="D02GLXG02" size="35" maxlength="35" value = "<%= crossRef.getD02GLXG02() %>">
      </td>
    </tr>
  <% } %>
  </table>
  <% } %>
  <% if (crossRef.getE02GLMACD().equals("90") || crossRef.getE02GLMACD().equals("91") || crossRef.getE02GLMACD().equals("92")) { %>
  <H4>Modulo de Amortización / Garantias / Lineas de Credito / Otros Productos</H4>
   <table class="tableinfo"> 
    <tr  id="trclear">
      <td align="right" width="2%">(*)</td>
      <td align="right" width="28%"> 
        Cuenta Contarpartida : 
      </td>
      <td align="left" width="60%" nowrap> 
        <input type="text" name="E02GLMXDR" size="17" maxlength="17" value = "<%= crossRef.getE02GLMXDR() %>"
        oncontextmenu="showPopUp(ledgerHelp,this.name,document.forms[0].E02GLMBNK.value,document.forms[0].E02GLMCCY.value,'','','')">
        <input type="text" name="D02GLMXDR" size="35" maxlength="35" value = "<%= crossRef.getD02GLMXDR() %>">
      </td>
    </tr>
    <tr  id="trclear">
      <td align="right" width="2%">(**)</td>
      <td align="right" width="28%"> 
        Cuenta Amortización : 
      </td>
      <td align="left" width="60%" nowrap> 
        <input type="text" name="E02GLMXCR" size="17" maxlength="17" value = "<%= crossRef.getE02GLMXCR() %>"
        oncontextmenu="showPopUp(ledgerHelp,this.name,document.forms[0].E02GLMBNK.value,document.forms[0].E02GLMCCY.value,'','','')">
        <input type="text" name="D02GLMXCR" size="35" maxlength="35" value = "<%= crossRef.getD02GLMXCR() %>">
      </td>
    </tr>
    <tr  id="trclear">
      <td align="right" width="2%"></td>
      <td align="right" width="28%"> 
        Cta/Debito Monto Usado : 
      </td>
      <td align="left" width="60%" nowrap> 
        <input type="text" name="E02GLMX21" size="17" maxlength="17" value = "<%= crossRef.getE02GLMX21() %>"
        oncontextmenu="showPopUp(ledgerHelp,this.name,document.forms[0].E02GLMBNK.value,document.forms[0].E02GLMCCY.value,'','','')">
        <input type="text" name="D02GLMX21" size="35" maxlength="35" value = "<%= crossRef.getD02GLMX21() %>">
      </td>
    </tr>
    <tr  id="trclear">
      <td align="right" width="2%"></td>
      <td align="right" width="28%"> 
        Cta/Credito Monto Usado : 
      </td>
      <td align="left" width="60%" nowrap> 
        <input type="text" name="E02GLMX22" size="17" maxlength="17" value = "<%= crossRef.getE02GLMX22() %>"
        oncontextmenu="showPopUp(ledgerHelp,this.name,document.forms[0].E02GLMBNK.value,document.forms[0].E02GLMCCY.value,'','','')">
        <input type="text" name="D02GLMX22" size="35" maxlength="35" value = "<%= crossRef.getD02GLMX22() %>">
      </td>
    </tr>
    <tr  id="trclear">
      <td align="right" width="2%"></td>
      <td align="right" colspan="2">         
      </td>
    </tr>
    <tr  id="trclear">
      <td align="right" valign="top" width="2%">(*)</td>
      <td align="left" colspan="2">
       Cuenta de Contrapartida sera usada para GARANTIAS, LINEAS DE CREDITO Y OTROS.         
      </td>
    </tr>
    <tr  id="trclear">
      <td align="right" valign="top" width="2%">(**)</td>
      <td align="left" colspan="2">
       La Cuenta de Ingresos x Comision o Gastos Prepagados serán utilizadas para la Amortización Diaria;
       Ingresos y Egresos serán afectados a diario de acuerdo a la clasificación de la Cuenta Principal Utilizada.
       (Cuenta Contable Reflejada Arriba)        
      </td>
    </tr>
    <tr  id="trclear">
      <td align="right" valign="top" width="2%">(**)</td>
      <td align="left" colspan="2">
       Lineas de Credito usaran la cuenta de Amortización para incluir la cuenta de Ingresos por Comisión.         
      </td>
    </tr>
  </table>
  <% } %>
  <% if (crossRef.getE02GLMACD().equals("11") || crossRef.getE02GLMACD().equals("12") || crossRef.getE02GLMACD().equals("14") || crossRef.getE02GLMACD().equals("15")) { %>
  <H4>Modulo de Contratos</H4>
  <table class="tableinfo">
    <tr  id="trdark">
      <TH align="left" colspan=2>Periodificación de Interes  </TH>
    </tr> 
    <tr  id="trclear">
      <td align="right" width="30%"> 
        Debito por Interes Normal : 
      </td>
      <td align="left" width="60%" nowrap> 
        <input type="text" name="E02GLMXDR" size="17" maxlength="17" value = "<%= crossRef.getE02GLMXDR() %>"
        oncontextmenu="showPopUp(ledgerHelp,this.name,document.forms[0].E02GLMBNK.value,document.forms[0].E02GLMCCY.value,'','','')">
        <input type="text" name="D02GLMXDR" size="35" maxlength="35" value = "<%= crossRef.getD02GLMXDR() %>">
      </td>
    </tr>
    <tr  id="trclear">
      <td align="right" width="30%"> 
        Credito por Interes Normal : 
      </td>
      <td align="left" width="60%" nowrap> 
        <input type="text" name="E02GLMXCR" size="17" maxlength="17" value = "<%= crossRef.getE02GLMXCR() %>"
        oncontextmenu="showPopUp(ledgerHelp,this.name,document.forms[0].E02GLMBNK.value,document.forms[0].E02GLMCCY.value,'','','')">
        <input type="text" name="D02GLMXCR" size="35" maxlength="35" value = "<%= crossRef.getD02GLMXCR() %>">
      </td>
    </tr>
    <tr  id="trclear">
      <td align="right" width="30%"> 
        Debito por Interes de Mora : 
      </td>
      <td align="left" width="60%" nowrap> 
        <input type="text" name="E02GLMXLR" size="17" maxlength="17" value = "<%= crossRef.getE02GLMXLR() %>"
        oncontextmenu="showPopUp(ledgerHelp,this.name,document.forms[0].E02GLMBNK.value,document.forms[0].E02GLMCCY.value,'','','')">
        <input type="text" name="D02GLMXLR" size="35" maxlength="35" value = "<%= crossRef.getD02GLMXLR() %>">
      </td>
    </tr>
    <tr  id="trclear">
      <td align="right" width="30%"> 
        Credito por Interes de Mora : 
      </td>
      <td align="left" width="60%" nowrap> 
        <input type="text" name="E02GLMXLC" size="17" maxlength="17" value = "<%= crossRef.getE02GLMXLC() %>"
        oncontextmenu="showPopUp(ledgerHelp,this.name,document.forms[0].E02GLMBNK.value,document.forms[0].E02GLMCCY.value,'','','')">
        <input type="text" name="D02GLMXLC" size="35" maxlength="35" value = "<%= crossRef.getD02GLMXLC() %>">
      </td>
    </tr>
        <tr  id="trdark">
      <TH align="left" colspan=2>Entradas Garantias  </TH>
    </tr>
    <tr  id="trclear">
      <td align="right" width="30%"> 
        Cuenta Debito Contingencia : 
      </td>
      <td align="left" width="60%" nowrap> 
        <input type="text" name="E02GLMXIE" size="17" maxlength="17" value = "<%= crossRef.getE02GLMXIE() %>"
        oncontextmenu="showPopUp(ledgerHelp,this.name,document.forms[0].E02GLMBNK.value,document.forms[0].E02GLMCCY.value,'','','')">
        <input type="text" name="D02GLMXIE" size="35" maxlength="35" value = "<%= crossRef.getD02GLMXIE() %>">
      </td>
    </tr>
    <tr  id="trclear">
      <td align="right" width="30%"> 
        Cuenta Credito Contingencia : 
      </td>
      <td align="left" width="60%" nowrap> 
        <input type="text" name="E02GLMDFP" size="17" maxlength="17" value = "<%= crossRef.getE02GLMDFP() %>"
        oncontextmenu="showPopUp(ledgerHelp,this.name,document.forms[0].E02GLMBNK.value,document.forms[0].E02GLMCCY.value,'','','')">
        <input type="text" name="D02GLMDFP" size="35" maxlength="35" value = "<%= crossRef.getD02GLMDFP() %>">
      </td>
    </tr>
    <tr  id="trclear">
      <td align="right" width="30%"> 
        Cuenta Debito Monto Usado : 
      </td>
      <td align="left" width="60%" nowrap> 
        <input type="text" name="E02GLMX21" size="17" maxlength="17" value = "<%= crossRef.getE02GLMX21() %>"
        oncontextmenu="showPopUp(ledgerHelp,this.name,document.forms[0].E02GLMBNK.value,document.forms[0].E02GLMCCY.value,'','','')">
        <input type="text" name="D02GLMX21" size="35" maxlength="35" value = "<%= crossRef.getD02GLMX21() %>">
      </td>
    </tr>
    <tr  id="trclear">
      <td align="right" width="30%"> 
        Cuenta Credito Monto Usado : 
      </td>
      <td align="left" width="60%" nowrap> 
        <input type="text" name="E02GLMX22" size="17" maxlength="17" value = "<%= crossRef.getE02GLMX22() %>"
        oncontextmenu="showPopUp(ledgerHelp,this.name,document.forms[0].E02GLMBNK.value,document.forms[0].E02GLMCCY.value,'','','')">
        <input type="text" name="D02GLMX22" size="35" maxlength="35" value = "<%= crossRef.getD02GLMX22() %>">
      </td>
    </tr>
    <tr  id="trdark">
      <TH align="left" colspan=2>Referencias Adicionales  </TH>
    </tr>
    <tr  id="trclear">
      <td align="right" width="30%">Operación dada en Garantia : </td>
      <td align="left" width="60%" nowrap> 
        <input type="text" name="E02GLMXSO" size="17" maxlength="17" value = "<%= crossRef.getE02GLMXSO() %>"
        oncontextmenu="showPopUp(ledgerHelp,this.name,document.forms[0].E02GLMBNK.value,document.forms[0].E02GLMCCY.value,'','','')">
        <input type="text" name="D02GLMXSO" size="35" maxlength="35" value = "<%= crossRef.getD02GLMXSO() %>">
      </td>
    </tr>
       <tr  id="trclear">
      <td align="right" width="30%">Cuenta Origen del Contrato : </td>
      <td align="left" width="60%" nowrap> 
        <input type="text" name="E02GLMXSR" size="17" maxlength="17" value = "<%= crossRef.getE02GLMXSR() %>"
        oncontextmenu="showPopUp(ledgerHelp,this.name,document.forms[0].E02GLMBNK.value,document.forms[0].E02GLMCCY.value,'','','')">
        <input type="text" name="D02GLMXSR" size="35" maxlength="35" value = "<%= crossRef.getD02GLMXSR() %>">
      </td>
    </tr>
        <tr  id="trclear">
      <td align="right" width="30%">Cuenta Contratos Vencidos : </td>
      <td align="left" width="60%" nowrap> 
        <input type="text" name="E02GLMXSM" size="17" maxlength="17" value = "<%= crossRef.getE02GLMXSM() %>"
        oncontextmenu="showPopUp(ledgerHelp,this.name,document.forms[0].E02GLMBNK.value,document.forms[0].E02GLMCCY.value,'','','')">
        <input type="text" name="D02GLMXSM" size="35" maxlength="35" value = "<%= crossRef.getD02GLMXSM() %>">
      </td>
    </tr>
     
  </table>
  <% } %>
  <% if (crossRef.getE02GLMACD().equals("13")) { %>
  <H4>Modulo de Inversiones (Papel Comercial)</H4>
  <table class="tableinfo">
    <tr  id="trdark">
      <TH align="left" colspan=2>Periodificación de Interes  </TH>
    </tr> 
    <tr  id="trclear">
      <td align="right" width="21%"> 
        Cta. Debito por Interes : 
      </td>
      <td align="left" nowrap width="79%"> 
        <input type="text" name="E02GLMXDR" size="17" maxlength="17" value = "<%= crossRef.getE02GLMXDR() %>"
        oncontextmenu="showPopUp(ledgerHelp,this.name,document.forms[0].E02GLMBNK.value,document.forms[0].E02GLMCCY.value,'','','')">
        <input type="text" name="D02GLMXDR" size="35" maxlength="35" value = "<%= crossRef.getD02GLMXDR() %>">
      </td>
    </tr>
    <tr  id="trclear">
      <td align="right" width="21%"> 
        Cta. Credito por Interes  : 
      </td>
      <td align="left" nowrap width="79%"> 
        <input type="text" name="E02GLMXCR" size="17" maxlength="17" value = "<%= crossRef.getE02GLMXCR() %>"
        oncontextmenu="showPopUp(ledgerHelp,this.name,document.forms[0].E02GLMBNK.value,document.forms[0].E02GLMCCY.value,'','','')">
        <input type="text" name="D02GLMXCR" size="35" maxlength="35" value = "<%= crossRef.getD02GLMXCR() %>">
      </td>
    </tr>
    <tr  id="trdark">
      <TH align="left" colspan=2>Custodio (Contingencias)  </TH>
    </tr>
    <tr  id="trclear">
      <td align="right" width="21%"> 
        Cuenta Debito : 
      </td>
      <td align="left" nowrap width="79%"> 
        <input type="text" name="E02GLMXIE" size="17" maxlength="17" value = "<%= crossRef.getE02GLMXIE() %>"
        oncontextmenu="showPopUp(ledgerHelp,this.name,document.forms[0].E02GLMBNK.value,document.forms[0].E02GLMCCY.value,'','','')">
        <input type="text" name="D02GLMXIE" size="35" maxlength="35" value = "<%= crossRef.getD02GLMXIE() %>">
      </td>
    </tr>
    <tr  id="trclear">
      <td align="right" width="21%"> 
        Cuenta Credito : 
      </td>
      <td align="left" nowrap width="79%"> 
        <input type="text" name="E02GLMDFP" size="17" maxlength="17" value = "<%= crossRef.getE02GLMDFP() %>"
        oncontextmenu="showPopUp(ledgerHelp,this.name,document.forms[0].E02GLMBNK.value,document.forms[0].E02GLMCCY.value,'','','')">
        <input type="text" name="D02GLMDFP" size="35" maxlength="35" value = "<%= crossRef.getD02GLMDFP() %>">
      </td>
    </tr>
    <tr  id="trdark">
      <TH align="left" colspan=2>Otras Entradas  </TH>
    </tr>
    <tr  id="trclear">
      <td align="right" width="21%"> 
        Amortización de Prima : 
      </td>
      <td align="left" nowrap width="79%"> 
        <input type="text" name="E02GLMXSD" size="17" maxlength="17" value = "<%= crossRef.getE02GLMXSD() %>"
        oncontextmenu="showPopUp(ledgerHelp,this.name,document.forms[0].E02GLMBNK.value,document.forms[0].E02GLMCCY.value,'','','')">
        <input type="text" name="D02GLMXSD" size="35" maxlength="35" value = "<%= crossRef.getD02GLMXSD() %>">
      </td>
    </tr>
    <tr  id="trclear">
      <td align="right" width="21%"> 
        Amortización de Descuento : 
      </td>
      <td align="left" nowrap width="79%"> 
        <input type="text" name="E02GLMXSO" size="17" maxlength="17" value = "<%= crossRef.getE02GLMXSO() %>"
        oncontextmenu="showPopUp(ledgerHelp,this.name,document.forms[0].E02GLMBNK.value,document.forms[0].E02GLMCCY.value,'','','')">
        <input type="text" name="D02GLMXSO" size="35" maxlength="35" value = "<%= crossRef.getD02GLMXSO() %>">
      </td>
    </tr>    
    <tr  id="trclear">
      <td align="right" width="21%"> 
        Ajuste de Precio de Mercado : 
      </td>
      <td align="left" nowrap width="79%"> 
        <input type="text" name="E02GLMXSM" size="17" maxlength="17" value = "<%= crossRef.getE02GLMXSM() %>"
        oncontextmenu="showPopUp(ledgerHelp,this.name,document.forms[0].E02GLMBNK.value,document.forms[0].E02GLMCCY.value,'','','')">
        <input type="text" name="D02GLMXSM" size="35" maxlength="35" value = "<%= crossRef.getD02GLMXSM() %>">
      </td>
    </tr>
    <tr  id="trclear">
      <td align="right" width="21%"> 
        Contrapartida de Ajuste. Ganancias : 
      </td>
      <td align="left" nowrap width="79%"> 
        <input type="text" name="E02GLMXSR" size="17" maxlength="17" value = "<%= crossRef.getE02GLMXSR() %>"
        oncontextmenu="showPopUp(ledgerHelp,this.name,document.forms[0].E02GLMBNK.value,document.forms[0].E02GLMCCY.value,'','','')">
        <input type="text" name="D02GLMXSR" size="35" maxlength="35" value = "<%= crossRef.getD02GLMXSR() %>">
      </td>
    </tr>
    <tr  id="trclear">
      <td align="right" width="21%"> 
        Contrapartida de Ajuste. Perdidas : 
      </td>
      <td align="left" nowrap width="79%"> 
        <input type="text" name="E02GLMXCC" size="17" maxlength="17" value = "<%= crossRef.getE02GLMXCC() %>"
        oncontextmenu="showPopUp(ledgerHelp,this.name,document.forms[0].E02GLMBNK.value,document.forms[0].E02GLMCCY.value,'','','')">
        <input type="text" name="D02GLMXCC" size="35" maxlength="35" value = "<%= crossRef.getD02GLMXCC() %>">
      </td>
    </tr>
    <tr  id="trclear">
      <td align="right" width="21%"> 
        Ganancias por Ventas : 
      </td>
      <td align="left" nowrap width="79%"> 
        <input type="text" name="E02GLMXSS" size="17" maxlength="17" value = "<%= crossRef.getE02GLMXSS() %>"
        oncontextmenu="showPopUp(ledgerHelp,this.name,document.forms[0].E02GLMBNK.value,document.forms[0].E02GLMCCY.value,'','','')">
        <input type="text" name="D02GLMXSS" size="35" maxlength="35" value = "<%= crossRef.getD02GLMXSS() %>">
      </td>
    </tr>    
    <tr  id="trclear">
      <td align="right" width="21%"> 
        Perdidas por Ventas : 
      </td>
      <td align="left" nowrap width="79%"> 
        <input type="text" name="E02GLMXLC" size="17" maxlength="17" value = "<%= crossRef.getE02GLMXLC() %>"
        oncontextmenu="showPopUp(ledgerHelp,this.name,document.forms[0].E02GLMBNK.value,document.forms[0].E02GLMCCY.value,'','','')">
        <input type="text" name="D02GLMXLC" size="35" maxlength="35" value = "<%= crossRef.getD02GLMXLC() %>">
      </td>
    </tr>
    <tr  id="trclear">
      <td align="right" width="21%"> 
        Cuenta de Inversiones Vencidas : 
      </td>
      <td align="left" nowrap width="79%"> 
        <input type="text" name="E02GLMX16" size="17" maxlength="17" value = "<%= crossRef.getE02GLMX16() %>"
        oncontextmenu="showPopUp(ledgerHelp,this.name,document.forms[0].E02GLMBNK.value,document.forms[0].E02GLMCCY.value,'','','')">
        <input type="text" name="D02GLMX16" size="35" maxlength="35" value = "<%= crossRef.getD02GLMX16() %>">
      </td>
    </tr>
    <tr  id="trclear">
      <td align="right" width="21%"> 
        Cuenta de Ganancias Realizadas : 
      </td>
      <td align="left" nowrap width="79%"> 
        <input type="text" name="E02GLMXO2" size="17" maxlength="17" value = "<%= crossRef.getE02GLMXO2() %>"
        oncontextmenu="showPopUp(ledgerHelp,this.name,document.forms[0].E02GLMBNK.value,document.forms[0].E02GLMCCY.value,'','','')">
        <input type="text" name="D02GLMXO2" size="35" maxlength="35" value = "<%= crossRef.getD02GLMXO2() %>">
      </td>
    </tr>
    <tr  id="trclear">
      <td align="right" width="21%"> 
        Cuenta de Perdidas Realizadas : 
      </td>
      <td align="left" nowrap width="79%"> 
        <input type="text" name="E02GLMXLR" size="17" maxlength="17" value = "<%= crossRef.getE02GLMXLR() %>"
        oncontextmenu="showPopUp(ledgerHelp,this.name,document.forms[0].E02GLMBNK.value,document.forms[0].E02GLMCCY.value,'','','')">
        <input type="text" name="D02GLMXLR" size="35" maxlength="35" value = "<%= crossRef.getD02GLMXLR() %>">
      </td>
    </tr>
  </table>
  <% } %>
  <% if (crossRef.getE02GLMACD().equals("19")) { %>
  <H4>Modulo de Proyectos de Constructor</H4>
  <table class="tableinfo">
    <tr  id="trdark">
      <TH align="left" colspan=2>Solicitud  </TH>
    </tr>     
    <tr  id="trclear">
      <td align="right" width="30%"> 
        Credito por Solicitud Recibida : 
      </td>
      <td align="left" width="60%" nowrap> 
        <input type="text" name="E02GLMXCR" size="17" maxlength="17" value = "<%= crossRef.getE02GLMXCR() %>"
        oncontextmenu="showPopUp(ledgerHelp,this.name,document.forms[0].E02GLMBNK.value,document.forms[0].E02GLMCCY.value,'','','')">
        <input type="text" name="D02GLMXCR" size="35" maxlength="35" value = "<%= crossRef.getD02GLMXCR() %>">
      </td>
    </tr>
    
    <tr  id="trdark">
      <TH align="left" colspan=2>Aprobación Solicitud  </TH>
    </tr>
    <tr  id="trclear">
      <td align="right" width="30%"> 
        Debito Solicitud Aprobada : 
      </td>
      <td align="left" width="60%" nowrap> 
        <input type="text" name="E02GLMXSM" size="17" maxlength="17" value = "<%= crossRef.getE02GLMXSM() %>"
        oncontextmenu="showPopUp(ledgerHelp,this.name,document.forms[0].E02GLMBNK.value,document.forms[0].E02GLMCCY.value,'','','')">
        <input type="text" name="D02GLMXSM" size="35" maxlength="35" value = "<%= crossRef.getD02GLMXSM() %>">
      </td>
    </tr>
    <tr  id="trclear">
      <td align="right" width="30%"> 
        Credito Solicitud Aprobada : 
      </td>
      <td align="left" width="60%" nowrap> 
        <input type="text" name="E02GLMXSR" size="17" maxlength="17" value = "<%= crossRef.getE02GLMXSR() %>"
        oncontextmenu="showPopUp(ledgerHelp,this.name,document.forms[0].E02GLMBNK.value,document.forms[0].E02GLMCCY.value,'','','')">
        <input type="text" name="D02GLMXSR" size="35" maxlength="35" value = "<%= crossRef.getD02GLMXSR() %>">
      </td>
    </tr>    
    <tr  id="trdark">
      <TH align="left" colspan=2>Protocolización  </TH>
    </tr>
    <tr  id="trclear">
      <td align="right" width="30%"> 
        Debito Protocolizado x Utilizar : 
      </td>
      <td align="left" width="60%" nowrap> 
        <input type="text" name="E02GLMXSS" size="17" maxlength="17" value = "<%= crossRef.getE02GLMXSS() %>"
        oncontextmenu="showPopUp(ledgerHelp,this.name,document.forms[0].E02GLMBNK.value,document.forms[0].E02GLMCCY.value,'','','')">
        <input type="text" name="D02GLMXSS" size="35" maxlength="35" value = "<%= crossRef.getD02GLMXSS() %>">
      </td>
    </tr>
    <tr  id="trclear">
      <td align="right" width="30%"> 
        Credito Protocolizado x Utilizar : 
      </td>
      <td align="left" width="60%" nowrap> 
        <input type="text" name="E02GLMXSD" size="17" maxlength="17" value = "<%= crossRef.getE02GLMXSD() %>"
        oncontextmenu="showPopUp(ledgerHelp,this.name,document.forms[0].E02GLMBNK.value,document.forms[0].E02GLMCCY.value,'','','')">
        <input type="text" name="D02GLMXSD" size="35" maxlength="35" value = "<%= crossRef.getD02GLMXSD() %>">
      </td>
    </tr>
    <tr  id="trdark">
      <TH align="left" colspan=2>Referencias Adicionales  </TH>
    </tr>
    <tr  id="trclear">
      <td align="right" width="30%"> 
        Contribución : 
      </td>
      <td align="left" width="60%" nowrap> 
        <input type="text" name="E02GLMXST" size="17" maxlength="17" value = "<%= crossRef.getE02GLMXST() %>"
        oncontextmenu="showPopUp(ledgerHelp,this.name,document.forms[0].E02GLMBNK.value,document.forms[0].E02GLMCCY.value,'','','')">
        <input type="text" name="D02GLMXST" size="35" maxlength="35" value = "<%= crossRef.getD02GLMXST() %>">
      </td>
    </tr>
    <tr  id="trclear">
      <td align="right" width="30%"> 
        Anticipos : 
      </td>
      <td align="left" width="60%" nowrap> 
        <input type="text" name="E02GLMXSO" size="17" maxlength="17" value = "<%= crossRef.getE02GLMXSO() %>"
        oncontextmenu="showPopUp(ledgerHelp,this.name,document.forms[0].E02GLMBNK.value,document.forms[0].E02GLMCCY.value,'','','')">
        <input type="text" name="D02GLMXSO" size="35" maxlength="35" value = "<%= crossRef.getD02GLMXSO() %>">
      </td>
    </tr>
    <tr  id="trclear">
      <td align="right" width="30%"> 
        Prima Fondo de Garantia : 
      </td>
      <td align="left" width="60%" nowrap> 
        <input type="text" name="E02GLMXDR" size="17" maxlength="17" value = "<%= crossRef.getE02GLMXDR() %>"
        oncontextmenu="showPopUp(ledgerHelp,this.name,document.forms[0].E02GLMBNK.value,document.forms[0].E02GLMCCY.value,'','','')">
        <input type="text" name="D02GLMXDR" size="35" maxlength="35" value = "<%= crossRef.getD02GLMXDR() %>">
      </td>
    </tr>
    <tr  id="trclear">
      <td align="right" width="30%"> 
        Retención Fiel Cumplimiento : 
      </td>
      <td align="left" width="60%" nowrap> 
        <input type="text" name="E02GLMXCC" size="17" maxlength="17" value = "<%= crossRef.getE02GLMXCC() %>"
        oncontextmenu="showPopUp(ledgerHelp,this.name,document.forms[0].E02GLMBNK.value,document.forms[0].E02GLMCCY.value,'','','')">
        <input type="text" name="D02GLMXCC" size="35" maxlength="35" value = "<%= crossRef.getD02GLMXCC() %>">
      </td>
    </tr>
    <tr  id="trclear">
      <td align="right" width="30%"> 
        Pago de Valuaciones : 
      </td>
      <td align="left" width="60%" nowrap> 
        <input type="text" name="E02GLMX16" size="17" maxlength="17" value = "<%= crossRef.getE02GLMX16() %>"
        oncontextmenu="showPopUp(ledgerHelp,this.name,document.forms[0].E02GLMBNK.value,document.forms[0].E02GLMCCY.value,'','','')">
        <input type="text" name="D02GLMX16" size="35" maxlength="35" value = "<%= crossRef.getD02GLMX16() %>">
      </td>
    </tr>
    <tr  id="trclear">
      <td align="right" width="30%"> 
        Comisiones y Otros : 
      </td>
      <td align="left" width="60%" nowrap> 
        <input type="text" name="E02GLMXOD" size="17" maxlength="17" value = "<%= crossRef.getE02GLMXOD() %>"
        oncontextmenu="showPopUp(ledgerHelp,this.name,document.forms[0].E02GLMBNK.value,document.forms[0].E02GLMCCY.value,'','','')">
        <input type="text" name="D02GLMXOD" size="35" maxlength="35" value = "<%= crossRef.getD02GLMXOD() %>">
      </td>
    </tr>
    <tr  id="trclear">
      <td align="right" width="30%"> 
        Honorarios Inspector  : 
      </td>
      <td align="left" width="60%" nowrap> 
        <input type="text" name="E02GLMXO2" size="17" maxlength="17" value = "<%= crossRef.getE02GLMXO2() %>"
        oncontextmenu="showPopUp(ledgerHelp,this.name,document.forms[0].E02GLMBNK.value,document.forms[0].E02GLMCCY.value,'','','')">
        <input type="text" name="D02GLMXO2" size="35" maxlength="35" value = "<%= crossRef.getD02GLMXO2() %>">
      </td>
    </tr>
    <tr  id="trclear">
      <td align="right" width="30%"> 
        Retención Fiel Cump. Inspector : 
      </td>
      <td align="left" width="60%" nowrap> 
        <input type="text" name="E02GLMXIE" size="17" maxlength="17" value = "<%= crossRef.getE02GLMXIE() %>"
        oncontextmenu="showPopUp(ledgerHelp,this.name,document.forms[0].E02GLMBNK.value,document.forms[0].E02GLMCCY.value,'','','')">
        <input type="text" name="D02GLMXIE" size="35" maxlength="35" value = "<%= crossRef.getD02GLMXIE() %>">
      </td>
    </tr>
    <tr  id="trclear">
      <td align="right" width="30%"> 
        Contrapartida Adicional : 
      </td>
      <td align="left" width="60%" nowrap> 
        <input type="text" name="E02GLMX17" size="17" maxlength="17" value = "<%= crossRef.getE02GLMX17() %>"
        oncontextmenu="showPopUp(ledgerHelp,this.name,document.forms[0].E02GLMBNK.value,document.forms[0].E02GLMCCY.value,'','','')">
        <input type="text" name="D02GLMX17" size="35" maxlength="35" value = "<%= crossRef.getD02GLMX17() %>">
      </td>
    </tr>
  </table>
  <% } %>
  <% if (crossRef.getE02GLMACD().equals("10") || crossRef.getE02GLMACD().equals("11") || crossRef.getE02GLMACD().equals("12") || crossRef.getE02GLMACD().equals("13") || crossRef.getE02GLMACD().equals("14") || crossRef.getE02GLMACD().equals("15")) { %>
  <H4>Referencias Cruzadas sobre Contratos</H4>
  <table class="tableinfo">
    <tr  id="trdark">
      <TH align="left" colspan=2>Contratos a Futuro  </TH>
    </tr> 
    <tr  id="trclear">
      <td align="right" width="30%"> 
        Debito Contingencia : 
      </td>
      <td align="left" width="60%" nowrap> 
        <input type="text" name="E02GLMXST" size="17" maxlength="17" value = "<%= crossRef.getE02GLMXST() %>"
        oncontextmenu="showPopUp(ledgerHelp,this.name,document.forms[0].E02GLMBNK.value,document.forms[0].E02GLMCCY.value,'','','')">
        <input type="text" name="D02GLMXST" size="35" maxlength="35" value = "<%= crossRef.getD02GLMXST() %>">
      </td>
    </tr>
    <tr  id="trclear">
      <td align="right" width="30%"> 
        Credito Contingencia : 
      </td>
      <td align="left" width="60%" nowrap> 
        <input type="text" name="E02GLMXOD" size="17" maxlength="17" value = "<%= crossRef.getE02GLMXOD() %>"
        oncontextmenu="showPopUp(ledgerHelp,this.name,document.forms[0].E02GLMBNK.value,document.forms[0].E02GLMCCY.value,'','','')">
        <input type="text" name="D02GLMXOD" size="35" maxlength="35" value = "<%= crossRef.getD02GLMXOD() %>">
      </td>
    </tr>
    <tr  id="trdark">
      <TH align="left" colspan=2>Proyeccion Intereses  </TH>
    </tr>
    <tr  id="trclear">
      <td align="right" width="30%"> 
        Intereses Debito : 
      </td>
      <td align="left" width="60%" nowrap> 
        <input type="text" name="E02GLMX23" size="17" maxlength="17" value = "<%= crossRef.getE02GLMX23() %>"
        oncontextmenu="showPopUp(ledgerHelp,this.name,document.forms[0].E02GLMBNK.value,document.forms[0].E02GLMCCY.value,'','','')">
        <input type="text" name="D02GLMX23" size="35" maxlength="35" value = "<%= crossRef.getD02GLMX23() %>">
      </td>
    </tr>
    <tr  id="trclear">
      <td align="right" width="30%"> 
        Intereses Creditos : 
      </td>
      <td align="left" width="60%" nowrap> 
        <input type="text" name="E02GLMX24" size="17" maxlength="17" value = "<%= crossRef.getE02GLMX24() %>"
        oncontextmenu="showPopUp(ledgerHelp,this.name,document.forms[0].E02GLMBNK.value,document.forms[0].E02GLMCCY.value,'','','')">
        <input type="text" name="D02GLMX24" size="35" maxlength="35" value = "<%= crossRef.getD02GLMX24() %>">
      </td>
    </tr>
    <tr  id="trdark">
      <TH align="left" colspan=2>Referencias Adicionales Contratos </TH>
    </tr>
    <tr  id="trdark">
      <td align="right" width="30%"> 
        Debito Custodia Magnetica : 
      </td>
      <td align="left" width="60%" nowrap> 
        <input type="text" name="E02GLMX26" size="17" maxlength="17" value = "<%= crossRef.getE02GLMX26() %>"
        oncontextmenu="showPopUp(ledgerHelp,this.name,document.forms[0].E02GLMBNK.value,document.forms[0].E02GLMCCY.value,'','','')">
        <input type="text" name="D02GLMX26" size="35" maxlength="35" value = "<%= crossRef.getD02GLMX26() %>">
      </td>
    </tr>
    <tr  id="trclear">
      <td align="right" width="30%"> 
        Credito Custodia Magnetica : 
      </td>
      <td align="left" width="60%" nowrap> 
        <input type="text" name="E02GLMX27" size="17" maxlength="17" value = "<%= crossRef.getE02GLMX27() %>"
        oncontextmenu="showPopUp(ledgerHelp,this.name,document.forms[0].E02GLMBNK.value,document.forms[0].E02GLMCCY.value,'','','')">
        <input type="text" name="D02GLMX27" size="35" maxlength="35" value = "<%= crossRef.getD02GLMX27() %>">
      </td>
    </tr>
    <%if(currUser.getE01INT().equals("03")){%> 
     <tr  id="trdark">
      <TH align="left" colspan=2>Politica Habitacional (Cuentas de Orden)  </TH>
    </tr>
    <tr  id="trclear">
      <td align="right" width="30%"> 
        Contrapartida Principal : 
      </td>
      <td align="left" width="60%" nowrap> 
        <input type="text" name="E02GLXG01" size="17" maxlength="17" value = "<%= crossRef.getE02GLXG01() %>"
        oncontextmenu="showPopUp(ledgerHelp,this.name,document.forms[0].E02GLMBNK.value,document.forms[0].E02GLMCCY.value,'','','')">
        <input type="text" name="D02GLXG01" size="35" maxlength="35" value = "<%= crossRef.getD02GLXG01() %>">
      </td>
    </tr>
    <tr  id="trclear">
      <td align="right" width="30%"> 
        Cuenta Contable Fideicomiso: 
      </td>
      <td align="left" width="60%" nowrap> 
        <input type="text" name="E02GLXG02" size="17" maxlength="17" value = "<%= crossRef.getE02GLXG02() %>"
        oncontextmenu="showPopUp(ledgerHelp,this.name,document.forms[0].E02GLMBNK.value,document.forms[0].E02GLMCCY.value,'','','')">
        <input type="text" name="D02GLXG02" size="35" maxlength="35" value = "<%= crossRef.getD02GLXG02() %>">
      </td>
    </tr>
    
    <tr  id="trclear">
      <td align="right" width="30%"> 
        Disponibilidad : 
      </td>
      <td align="left" width="60%" nowrap> 
        <input type="text" name="E02GLXG03" size="17" maxlength="17" value = "<%= crossRef.getE02GLXG03() %>"
        oncontextmenu="showPopUp(ledgerHelp,this.name,document.forms[0].E02GLMBNK.value,document.forms[0].E02GLMCCY.value,'','','')">
        <input type="text" name="D02GLXG03" size="35" maxlength="35" value = "<%= crossRef.getD02GLXG03() %>">
      </td>
    </tr>
    <tr  id="trclear">
      <td align="right" width="30%">Aporte FAOV x Pagar : 
      </td>
      <td align="left" width="60%" nowrap> 
        <input type="text" name="E02GLXG04" size="17" maxlength="17" value = "<%= crossRef.getE02GLXG04() %>"
        oncontextmenu="showPopUp(ledgerHelp,this.name,document.forms[0].E02GLMBNK.value,document.forms[0].E02GLMCCY.value,'','','')">
        <input type="text" name="D02GLXG04" size="35" maxlength="35" value = "<%= crossRef.getD02GLXG04() %>">
      </td>
    </tr>   
     <tr  id="trclear">
      <td align="right" width="30%">Contrapartida x Seguros : 
      </td>
      <td align="left" width="60%" nowrap> 
        <input type="text" name="E02GLXG05" size="17" maxlength="17" value = "<%= crossRef.getE02GLXG05() %>"
        oncontextmenu="showPopUp(ledgerHelp,this.name,document.forms[0].E02GLMBNK.value,document.forms[0].E02GLMCCY.value,'','','')">
        <input type="text" name="D02GLXG05" size="35" maxlength="35" value = "<%= crossRef.getD02GLXG05() %>">
      </td>
    </tr>     
    <tr  id="trclear">
      <td align="right" width="30%"> 
         : 
      </td>
      <td align="left" width="60%" nowrap> 
        <input type="text" name="E02GLXG06" size="17" maxlength="17" value = "<%= crossRef.getE02GLXG06() %>"
        oncontextmenu="showPopUp(ledgerHelp,this.name,document.forms[0].E02GLMBNK.value,document.forms[0].E02GLMCCY.value,'','','')">
        <input type="text" name="D02GLXG06" size="35" maxlength="35" value = "<%= crossRef.getD02GLXG06() %>">
      </td>
    </tr>
    <tr  id="trclear">
      <td align="right" width="30%"> 
        Intermediación Financiera : 
      </td>
      <td align="left" width="60%" nowrap> 
        <input type="text" name="E02GLXG07" size="17" maxlength="17" value = "<%= crossRef.getE02GLXG07() %>"
        oncontextmenu="showPopUp(ledgerHelp,this.name,document.forms[0].E02GLMBNK.value,document.forms[0].E02GLMCCY.value,'','','')">
        <input type="text" name="D02GLXG07" size="35" maxlength="35" value = "<%= crossRef.getD02GLXG07() %>">
      </td>
    </tr>
    <tr  id="trclear">
      <td align="right" width="30%"> 
        Ingresos x Intermediación : 
      </td>
      <td align="left" width="60%" nowrap> 
        <input type="text" name="E02GLXG08" size="17" maxlength="17" value = "<%= crossRef.getE02GLXG08() %>"
        oncontextmenu="showPopUp(ledgerHelp,this.name,document.forms[0].E02GLMBNK.value,document.forms[0].E02GLMCCY.value,'','','')">
        <input type="text" name="D02GLXG08" size="35" maxlength="35" value = "<%= crossRef.getD02GLXG08() %>">
      </td>
    </tr>
    <tr  id="trclear">
      <td align="right" width="30%"> 
        Obligaciones con BANAVIH : 
      </td>
      <td align="left" width="60%" nowrap> 
        <input type="text" name="E02GLXG09" size="17" maxlength="17" value = "<%= crossRef.getE02GLXG09() %>"
        oncontextmenu="showPopUp(ledgerHelp,this.name,document.forms[0].E02GLMBNK.value,document.forms[0].E02GLMCCY.value,'','','')">
        <input type="text" name="D02GLXG09" size="35" maxlength="35" value = "<%= crossRef.getD02GLXG09() %>">
      </td>
    </tr>
    <tr  id="trclear">
      <td align="right" width="30%"> 
        Gastos   BANAVIH : 
      </td>
      <td align="left" width="60%" nowrap> 
        <input type="text" name="E02GLXG10" size="17" maxlength="17" value = "<%= crossRef.getE02GLXG10() %>"
        oncontextmenu="showPopUp(ledgerHelp,this.name,document.forms[0].E02GLMBNK.value,document.forms[0].E02GLMCCY.value,'','','')">
        <input type="text" name="D02GLXG10" size="35" maxlength="35" value = "<%= crossRef.getD02GLXG10() %>">
      </td>
    </tr>
    <tr  id="trclear">
      <td align="right" width="30%"> 
        Rendimientos no Cobrados : 
      </td>
      <td align="left" width="60%" nowrap> 
        <input type="text" name="E02GLXG11" size="17" maxlength="17" value = "<%= crossRef.getE02GLXG11() %>"
        oncontextmenu="showPopUp(ledgerHelp,this.name,document.forms[0].E02GLMBNK.value,document.forms[0].E02GLMCCY.value,'','','')">
        <input type="text" name="D02GLXG11" size="35" maxlength="35" value = "<%= crossRef.getD02GLXG11() %>">
      </td>
    </tr>
    <tr  id="trclear">
      <td align="right" width="30%"> 
        Costos Operativos BANAVIH : 
      </td>
      <td align="left" width="60%" nowrap> 
        <input type="text" name="E02GLXG12" size="17" maxlength="17" value = "<%= crossRef.getE02GLXG12() %>"
        oncontextmenu="showPopUp(ledgerHelp,this.name,document.forms[0].E02GLMBNK.value,document.forms[0].E02GLMCCY.value,'','','')">
        <input type="text" name="D02GLXG12" size="35" maxlength="35" value = "<%= crossRef.getD02GLXG12() %>">
      </td>
    </tr>
     <tr  id="trclear">
      <td align="right" width="30%"> 
        Fideicomiso de Inversion : 
      </td>
      <td align="left" width="60%" nowrap> 
        <input type="text" name="E02GLXG13" size="17" maxlength="17" value = "<%= crossRef.getE02GLXG13() %>"
        oncontextmenu="showPopUp(ledgerHelp,this.name,document.forms[0].E02GLMBNK.value,document.forms[0].E02GLMCCY.value,'','','')">
        <input type="text" name="D02GLXG13" size="35" maxlength="35" value = "<%= crossRef.getD02GLXG13() %>">
      </td>
    </tr>
  <% } %>
  </table>
  <% } %>
  <% if (gLedger.getE01GLMPRV().equals("Y")) { %>
  <H4>Cuentas para Previsiones</H4>
  <table class="tableinfo">
    <tr  id="trdark">
      <TH align="left" colspan=2>Año Actual </TH>
    </tr> 
    <tr  id="trclear">
      <td align="right" width="30%"> 
        Debito Principal : 
      </td>
      <td align="left" width="60%" nowrap> 
        <input type="text" name="E02GLMX30" size="17" maxlength="17" value = "<%= crossRef.getE02GLMX30() %>"
        oncontextmenu="showPopUp(ledgerHelp,this.name,document.forms[0].E02GLMBNK.value,document.forms[0].E02GLMCCY.value,'','','')">
        <input type="text" name="D02GLMX30" size="35" maxlength="35" value = "<%= crossRef.getD02GLMX30() %>">
      </td>
    </tr>
    <tr  id="trclear">
      <td align="right" width="30%"> 
        Credito Principal : 
      </td>
      <td align="left" width="60%" nowrap> 
        <input type="text" name="E02GLMX31" size="17" maxlength="17" value = "<%= crossRef.getE02GLMX31() %>"
        oncontextmenu="showPopUp(ledgerHelp,this.name,document.forms[0].E02GLMBNK.value,document.forms[0].E02GLMCCY.value,'','','')">
        <input type="text" name="D02GLMX31" size="35" maxlength="35" value = "<%= crossRef.getD02GLMX31() %>">
      </td>
    </tr>
    <tr  id="trclear">
      <td align="right" width="30%"> 
        Debito Intereses : 
      </td>
      <td align="left" width="60%" nowrap> 
        <input type="text" name="E02GLMX32" size="17" maxlength="17" value = "<%= crossRef.getE02GLMX32() %>"
        oncontextmenu="showPopUp(ledgerHelp,this.name,document.forms[0].E02GLMBNK.value,document.forms[0].E02GLMCCY.value,'','','')">
        <input type="text" name="D02GLMX32" size="35" maxlength="35" value = "<%= crossRef.getD02GLMX32() %>">
      </td>
    </tr>
    <tr  id="trclear">
      <td align="right" width="30%"> 
        Credito Intereses : 
      </td>
      <td align="left" width="60%" nowrap> 
        <input type="text" name="E02GLMX20" size="17" maxlength="17" value = "<%= crossRef.getE02GLMX20() %>"
        oncontextmenu="showPopUp(ledgerHelp,this.name,document.forms[0].E02GLMBNK.value,document.forms[0].E02GLMCCY.value,'','','')">
        <input type="text" name="D02GLMX20" size="35" maxlength="35" value = "<%= crossRef.getD02GLMX20() %>">
      </td>
    </tr>
    <tr  id="trdark">
      <TH align="left" colspan=2>Año Pasado  </TH>
    </tr>
    <tr  id="trclear">
      <td align="right" width="30%"> 
        Debito Principal : 
      </td>
      <td align="left" width="60%" nowrap> 
        <input type="text" name="E02GLMX28" size="17" maxlength="17" value = "<%= crossRef.getE02GLMX28() %>"
        oncontextmenu="showPopUp(ledgerHelp,this.name,document.forms[0].E02GLMBNK.value,document.forms[0].E02GLMCCY.value,'','','')">
        <input type="text" name="D02GLMX28" size="35" maxlength="35" value = "<%= crossRef.getD02GLMX28() %>">
      </td>
    </tr>
    <tr  id="trclear">
      <td align="right" width="30%"> 
        Credito Principal : 
      </td>
      <td align="left" width="60%" nowrap> 
        <input type="text" name="E02GLMX29" size="17" maxlength="17" value = "<%= crossRef.getE02GLMX29() %>"
        oncontextmenu="showPopUp(ledgerHelp,this.name,document.forms[0].E02GLMBNK.value,document.forms[0].E02GLMCCY.value,'','','')">
        <input type="text" name="D02GLMX29" size="35" maxlength="35" value = "<%= crossRef.getD02GLMX29() %>">
      </td>
    </tr>
  </table>
  <% } %>
  <% if (gLedger.getE01GLMRVF().equals("Y")) { %>
  <H4>Cuentas de Revaluación Moneda Extranjera</H4>
  <table class="tableinfo"> 
    <tr  id="trclear">
      <td align="right" width="30%"> 
        Cuenta de Ingreso : 
      </td>
      <td align="left" width="60%" nowrap> 
        <input type="text" name="E02GLMRIN" size="17" maxlength="17" value = "<%= crossRef.getE02GLMRIN() %>"
        oncontextmenu="showPopUp(ledgerHelp,this.name,document.forms[0].E02GLMBNK.value,document.forms[0].E02GLMCCY.value,'','','')">
        <input type="text" name="D02GLMRIN" size="35" maxlength="35" value = "<%= crossRef.getD02GLMRIN() %>">
      </td>
    </tr>
    <tr  id="trclear">
      <td align="right" width="30%"> 
        Cuenta de Egreso : 
      </td>
      <td align="left" width="60%" nowrap> 
        <input type="text" name="E02GLMREX" size="17" maxlength="17" value = "<%= crossRef.getE02GLMREX() %>"
        oncontextmenu="showPopUp(ledgerHelp,this.name,document.forms[0].E02GLMBNK.value,document.forms[0].E02GLMCCY.value,'','','')">
        <input type="text" name="D02GLMREX" size="35" maxlength="35" value = "<%= crossRef.getD02GLMREX() %>">
      </td>
    </tr>
   </table>
  <% } %>
  <% if (gLedger.getE01GLMRVF().equals("4")) { %>
  <H4>Cuentas de Diferencial Cambiario</H4>
  <table class="tableinfo"> 
    <tr  id="trclear">
      <td align="right" width="30%"> 
        Cuenta de Diferencial Cambiario : 
      </td>
      <td align="left" width="60%" nowrap> 
        <input type="text" name="E02GLMRIN" size="17" maxlength="17" value = "<%= crossRef.getE02GLMRIN() %>"
        oncontextmenu="showPopUp(ledgerHelp,this.name,document.forms[0].E02GLMBNK.value,'<%= currUser.getE01HCU() %>','','','')">
        <input type="text" name="D02GLMRIN" size="35" maxlength="35" value = "<%= crossRef.getD02GLMRIN() %>">
      </td>
    </tr>
   </table>
  <% } %>
  <% if (crossRef.getE02GLMATY().equals("BND") ||
				 crossRef.getE02GLMATY().equals("EQT") ||
				 crossRef.getE02GLMATY().equals("MUT") ||
				 crossRef.getE02GLMATY().equals("ACD") ||				 
				 crossRef.getE02GLMATY().equals("PFS")) { %> 
  <h4>Cuentas de Inversiones </h4>
  <table class="tableinfo">
    <tr  id="trdark"> 
      <td align="right" width="30%"> 
        <div align="right">Cuenta Compensacion :</div>
      </td>
      <td align="left" width="60%" nowrap> 
        <div align="left"> 
          <input type="text" name="E02GLMX32" size="13" maxlength="12" value = "<%= crossRef.getE02GLMX32() %>"
        oncontextmenu="showPopUp(ledgerHelp,this.name,document.forms[0].E02GLMBNK.value,document.forms[0].E02GLMCCY.value,'','','')">
          <input type="text" readonly name="D02GLMX32" size="35" maxlength="35" value = "<%= crossRef.getD02GLMX32() %>">
        </div>
      </td>
    </tr>
    <tr  id="trclear"> 
      <td align="right" width="30%"> 
        <div align="right">Ganancias/Perdidas    Ingresos : </div>
      </td>
      <td align="left" width="60%" nowrap> 
        <div align="left"> 
          <input type="text" name="E02GLMXCR" size="13" maxlength="12" value = "<%= crossRef.getE02GLMXCR() %>"
        oncontextmenu="showPopUp(ledgerHelp,this.name,document.forms[0].E02GLMBNK.value,document.forms[0].E02GLMCCY.value,'','','')">
          <input type="text" readonly name="D02GLMXCR" size="35" maxlength="35" value = "<%= crossRef.getD02GLMXCR() %>">
        </div>
      </td>
    </tr>
    <tr  id="trdark"> 
      <td align="right" width="30%"> 
        <div align="right">Ganancias/Perdidas    Gastos : </div>
      </td>
      <td align="left" width="60%" nowrap> 
        <div align="left"> 
          <input type="text" name="E02GLMXSM" size="13" maxlength="12" value = "<%= crossRef.getE02GLMXSM() %>"
        oncontextmenu="showPopUp(ledgerHelp,this.name,document.forms[0].E02GLMBNK.value,document.forms[0].E02GLMCCY.value,'','','')">
          <input type="text" readonly name="D02GLMXSM" size="35" maxlength="35" value = "<%= crossRef.getD02GLMXSM() %>">
        </div>
      </td>
    </tr>
    <tr  id="trclear"> 
      <td align="right" width="30%"> 
        <div align="right">Debito Interes Devengos :</div>
      </td>
      <td align="left" width="60%" nowrap> 
        <div align="left"> 
          <input type="text" name="E02GLMXSS" size="13" maxlength="12" value = "<%= crossRef.getE02GLMXSS() %>"
        oncontextmenu="showPopUp(ledgerHelp,this.name,document.forms[0].E02GLMBNK.value,document.forms[0].E02GLMCCY.value,'','','')">
          <input type="text" readonly name="D02GLMXSS" size="35" maxlength="35" value = "<%= crossRef.getD02GLMXSS() %>">
        </div>
      </td>
    </tr>
    <tr  id="trdark"> 
      <td align="right" width="30%"> 
        <div align="right">Credito Interes Devengos  :</div>
      </td>
      <td align="left" width="60%" nowrap> 
        <div align="left"> 
          <input type="text" name="E02GLMXSD" size="13" maxlength="12" value = "<%= crossRef.getE02GLMXSD() %>"
        oncontextmenu="showPopUp(ledgerHelp,this.name,document.forms[0].E02GLMBNK.value,document.forms[0].E02GLMCCY.value,'','','')">
          <input type="text" readonly name="D02GLMXSD" size="35" maxlength="35" value = "<%= crossRef.getD02GLMXSD() %>">
        </div>
      </td>
    </tr>
    <tr  id="trclear"> 
      <td align="right" width="30%"> 
        <div align="right">Debito Ordenes de Venta Pendientes :</div>
      </td>
      <td align="left" width="60%" nowrap> 
        <div align="left"> 
          <input type="text" name="E02GLMXST" size="13" maxlength="12" value = "<%= crossRef.getE02GLMXST() %>"
        oncontextmenu="showPopUp(ledgerHelp,this.name,document.forms[0].E02GLMBNK.value,document.forms[0].E02GLMCCY.value,'','','')">
          <input type="text" readonly name="D02GLMXST" size="35" maxlength="35" value = "<%= crossRef.getD02GLMXST() %>">
        </div>
      </td>
    </tr>
    <tr  id="trdark"> 
      <td align="right" width="30%"> 
        <div align="right">Credito Ordenes de Venta Pendientes :</div>
      </td>
      <td align="left" width="60%" nowrap> 
        <div align="left"> 
          <input type="text" name="E02GLMXSO" size="13" maxlength="12" value = "<%= crossRef.getE02GLMXSO() %>"
        oncontextmenu="showPopUp(ledgerHelp,this.name,document.forms[0].E02GLMBNK.value,document.forms[0].E02GLMCCY.value,'','','')">
          <input type="text" readonly name="D02GLMXSO" size="35" maxlength="35" value = "<%= crossRef.getD02GLMXSO() %>">
        </div>
      </td>
    </tr>
    <tr  id="trclear"> 
      <td align="right" width="30%"> 
        <div align="right">Debito Ordenes de Compra Pendientes :</div>
      </td>
      <td align="left" width="60%" nowrap> 
        <div align="left"> 
          <input type="text" name="E02GLMXLR" size="13" maxlength="12" value = "<%= crossRef.getE02GLMXLR() %>"
        oncontextmenu="showPopUp(ledgerHelp,this.name,document.forms[0].E02GLMBNK.value,document.forms[0].E02GLMCCY.value,'','','')">
          <input type="text" readonly name="D02GLMXLR" size="35" maxlength="35" value = "<%= crossRef.getD02GLMXLR() %>">
        </div>
      </td>
    </tr>
    <tr  id="trdark"> 
      <td align="right" width="30%"> 
        <div align="right">Credito Ordenes de Compra Pendientes :</div>
      </td>
      <td align="left" width="60%" nowrap> 
        <div align="left"> 
          <input type="text" name="E02GLMXO2" size="13" maxlength="12" value = "<%= crossRef.getE02GLMXO2() %>"
        oncontextmenu="showPopUp(ledgerHelp,this.name,document.forms[0].E02GLMBNK.value,document.forms[0].E02GLMCCY.value,'','','')">
          <input type="text" readonly name="D02GLMXO2" size="35" maxlength="35" value = "<%= crossRef.getD02GLMXO2() %>">
        </div>
      </td>
    </tr>
    
    <tr  id="trclear"> 
      <td align="right" width="30%"> 
        <div align="right">Custody Fee Accrual Debit :</div>
      </td>
      <td align="left" width="60%" nowrap> 
        <div align="left"> 
          <input type="text" name="E02GLMX17" size="13" maxlength="12" value = "<%= crossRef.getE02GLMX17() %>"
        oncontextmenu="showPopUp(ledgerHelp,this.name,document.forms[0].E02GLMBNK.value,document.forms[0].E02GLMCCY.value,'','','')">
          <input type="text" readonly name="D02GLMX17" size="35" maxlength="35" value = "<%= crossRef.getD02GLMX17() %>">
        </div>
      </td>
    </tr>
    <tr  id="trdark"> 
      <td align="right" width="30%"> 
        <div align="right">Custody Fee Accrual Credit :</div>
      </td>
      <td align="left" width="60%" nowrap> 
        <div align="left"> 
          <input type="text" name="E02GLMX18" size="13" maxlength="12" value = "<%= crossRef.getE02GLMX18() %>"
        oncontextmenu="showPopUp(ledgerHelp,this.name,document.forms[0].E02GLMBNK.value,document.forms[0].E02GLMCCY.value,'','','')">
          <input type="text" readonly name="D02GLMX18" size="35" maxlength="35" value = "<%= crossRef.getD02GLMX18() %>">
        </div>
      </td>
    </tr>
    <tr  id="trclear"> 
      <td align="right" width="30%"> 
        <div align="right">Ingresos Broker :</div>
      </td>
      <td align="left" width="60%" nowrap> 
        <div align="left"> 
          <input type="text" name="E02GLMXSR" size="13" maxlength="12" value = "<%= crossRef.getE02GLMXSR() %>"
        oncontextmenu="showPopUp(ledgerHelp,this.name,document.forms[0].E02GLMBNK.value,document.forms[0].E02GLMCCY.value,'','','')">
          <input type="text" readonly name="D02GLMXSR" size="35" maxlength="35" value = "<%= crossRef.getD02GLMXSR() %>">
        </div>
      </td>
    </tr>
    <tr  id="trdark"> 
      <td align="right" width="30%"> 
        <div align="right">Gastos Broker :</div>
      </td>
      <td align="left" width="60%" nowrap> 
        <div align="left"> 
          <input type="text" name="E02GLMXCC" size="13" maxlength="12" value = "<%= crossRef.getE02GLMXCC() %>"
        oncontextmenu="showPopUp(ledgerHelp,this.name,document.forms[0].E02GLMBNK.value,document.forms[0].E02GLMCCY.value,'','','')">
          <input type="text" readonly name="D02GLMXCC" size="35" maxlength="35" value = "<%= crossRef.getD02GLMXCC() %>">
        </div>
      </td>
    </tr>
    
    
  </table>
<H4>Posicion Banco</H4>
<TABLE class="tableinfo">
	<TBODY>
		<TR id="trdark">
			<TD align="right" width="30%">
			<DIV align="right">Face value Account</DIV>
			</TD>
			<TD align="left" width="60%" nowrap>
			<DIV align="left"><INPUT type="text" name="E02GLMDFP" size="13"
				maxlength="12" value="<%= crossRef.getE02GLMDFP() %>"
				oncontextmenu="showPopUp(ledgerHelp,this.name,document.forms[0].E02GLMBNK.value,document.forms[0].E02GLMCCY.value,'','','')">
			<INPUT type="text" name="D02GLMDFP0" size="35" maxlength="35"
				value="<%= crossRef.getD02GLMDFP() %>"></DIV>
			</TD>
		</TR>
		<TR id="trclear">
			<TD align="right" width="30%">
			<DIV align="right">Cuenta Prima/Descuento</DIV>
			</TD>
			<TD align="left" width="60%" nowrap>
			<DIV align="left"><INPUT type="text" name="E02GLMX16" size="13"
				maxlength="12" value="<%= crossRef.getE02GLMX16() %>"
				oncontextmenu="showPopUp(ledgerHelp,this.name,document.forms[0].E02GLMBNK.value,document.forms[0].E02GLMCCY.value,'','','')">
			<INPUT type="text" name="D02GLMX160" size="35" maxlength="35"
				value="<%= crossRef.getD02GLMX16() %>"></DIV>
			</TD>
		</TR>
		<TR id="trdark">
			<TD align="right" width="30%">
			<DIV align="right">Accrued Int.time of purchase</DIV>
			</TD>
			<TD align="left" width="60%" nowrap>
			<DIV align="left"><INPUT type="text" name="E02GLMXIE" size="13"
				maxlength="12" value="<%= crossRef.getE02GLMXIE() %>"
				oncontextmenu="showPopUp(ledgerHelp,this.name,document.forms[0].E02GLMBNK.value,document.forms[0].E02GLMCCY.value,'','','')">
			<INPUT type="text" name="D02GLMXIE" size="35" maxlength="35"
				value="<%= crossRef.getD02GLMXIE() %>"></DIV>
			</TD>
		</TR>
		<TR id="trclear">
			<TD align="right" width="30%">
			<DIV align="right">Sec. Held by the Bank Debit</DIV>
			</TD>
			<TD align="left" width="60%" nowrap>
			<DIV align="left"><INPUT type="text" name="E02GLMX19" size="13"
				maxlength="12" value="<%= crossRef.getE02GLMX19() %>"
				oncontextmenu="showPopUp(ledgerHelp,this.name,document.forms[0].E02GLMBNK.value,document.forms[0].E02GLMCCY.value,'','','')">
			<INPUT type="text" name="D02GLMX19" size="35" maxlength="35"
				value="<%= crossRef.getD02GLMX19() %>"></DIV>
			</TD>			
		</TR>
		<TR id="trclear">
			<TD align="right" width="30%">
			<DIV align="right">Sec. Held by the Bank Credit</DIV>
			</TD>
			<TD align="left" width="60%" nowrap>
			<DIV align="left"><INPUT type="text" name="E02GLMX20" size="13"
				maxlength="12" value="<%= crossRef.getE02GLMX20() %>"
				oncontextmenu="showPopUp(ledgerHelp,this.name,document.forms[0].E02GLMBNK.value,document.forms[0].E02GLMCCY.value,'','','')">
			<INPUT type="text" name="D02GLMX20" size="35" maxlength="35"
				value="<%= crossRef.getD02GLMX20() %>"></DIV>
			</TD>		
		</TR>		
	</TBODY>
</TABLE>
<% } %>
  <% if (!gLedger.getE01GLMREV().equals("N")) { %>
  <H4>Cuentas de Reajuste/Corrección Monetaria</H4>
  <table class="tableinfo"> 
    <tr  id="trclear">
      <td align="right" width="30%"> 
        Debito x Reajuste : 
      </td>
      <td align="left" width="60%" nowrap> 
        <input type="text" name="E02GLMRIN" size="17" maxlength="17" value = "<%= crossRef.getE02GLMRIN() %>"
        oncontextmenu="showPopUp(ledgerHelp,this.name,document.forms[0].E02GLMBNK.value,document.forms[0].E02GLMCCY.value,'','','')">
        <input type="text" name="D02GLMRIN" size="35" maxlength="35" value = "<%= crossRef.getD02GLMRIN() %>">
      </td>
    </tr>
    <tr  id="trclear">
      <td align="right" width="30%"> 
        Credito x Reajuste : 
      </td>
      <td align="left" width="60%" nowrap> 
        <input type="text" name="E02GLMREX" size="17" maxlength="17" value = "<%= crossRef.getE02GLMREX() %>"
        oncontextmenu="showPopUp(ledgerHelp,this.name,document.forms[0].E02GLMBNK.value,document.forms[0].E02GLMCCY.value,'','','')">
        <input type="text" name="D02GLMREX" size="35" maxlength="35" value = "<%= crossRef.getD02GLMREX() %>">
      </td>
    </tr>
    <tr  id="trclear">
      <td align="right" width="30%"> 
        Ing/Reajuste/Contingencia : 
      </td>
      <td align="left" width="60%" nowrap> 
        <input type="text" name="E02GLMX25" size="17" maxlength="17" value = "<%= crossRef.getE02GLMX25() %>"
        oncontextmenu="showPopUp(ledgerHelp,this.name,document.forms[0].E02GLMBNK.value,document.forms[0].E02GLMCCY.value,'','','')">
        <input type="text" name="D02GLMX25" size="35" maxlength="35" value = "<%= crossRef.getD02GLMX25() %>">
      </td>
    </tr>
   </table>
  <% } %>
  <p>
  <div align="center"> 
    <input id="EIBSBTN" type=submit name="Submit" value="Enviar">
  </div>

</form>
</body>
</html>
