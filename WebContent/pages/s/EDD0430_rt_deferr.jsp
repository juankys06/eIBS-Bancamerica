<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<title>Diferir Cargos por Sobregiro</title>
<META HTTP-EQUIV="Content-Type" CONTENT="text/html; charset=ISO-8859-1">
<META name="GENERATOR" content="IBM WebSphere Studio">
<link Href="<%=request.getContextPath()%>/pages/style.css" rel="stylesheet">



<jsp:useBean id="rtDif" class="datapro.eibs.beans.EDD043003Message"  scope="session" />

<jsp:useBean id= "error" class= "datapro.eibs.beans.ELEERRMessage"  scope="session" />

<jsp:useBean id= "userPO" class= "datapro.eibs.beans.UserPos"  scope="session" />

<script language="Javascript1.1" src="<%=request.getContextPath()%>/pages/s/javascripts/eIBS.jsp"> </SCRIPT>
<script language="Javascript1.1" src="<%=request.getContextPath()%>/pages/s/javascripts/optMenu.jsp"> </SCRIPT>

<SCRIPT Language="Javascript">
  builtHPopUp();

  function showPopUp(opth,field,bank,ccy,field1,field2,opcod) {
   init(opth,field,bank,ccy,field1,field2,opcod);
   showPopupHelp();
  }
</SCRIPT>
</head>

<body>
<% 
 if ( !error.getERRNUM().equals("0")  ) {
     out.println("<SCRIPT Language=\"Javascript\">");
     error.setERRNUM("0");
     out.println("       showErrors()");
     out.println("</SCRIPT>");
 }

%>

<H3 align="center">Diferir Cargos por Sobregiro<img src="<%=request.getContextPath()%>/images/e_ibs.gif" align="left" name="EIBS_GIF" ALT="rt_deferr, EDD0430"></H3>
<hr size="4">
<form method="post" action="<%=request.getContextPath()%>/servlet/datapro.eibs.products.JSEDD0430" >
  <input type=HIDDEN name="SCREEN" value="6">
  <table class="tableinfo">
    <tr> 
      <td nowrap>
        <table cellspacing="0" cellpadding="2" width="100%" border="0">
          <tr id="trdark"> 
            <td nowrap> 
              <div align="right"><b>Cliente:</b></div>
            </td>
            <td nowrap> 
              <input type="text" name="E03ACMCUN" size="9" maxlength="9" value="<%= rtDif.getE03ACMCUN().trim()%>" readonly>
            </td>
            <td nowrap> 
              <div align="right"><b>Nombre:</b></div>
            </td>
            <td nowrap> 
              <input type="text" name="E03CUSNA1" size="45" maxlength="45" value="<%= rtDif.getE03CUSNA1().trim()%>" readonly>
            </td>            
          </tr>
          <tr id="trdark"> 
            <td nowrap> 
              <div align="right"><b>Cuenta:</b></div>
            </td>
            <td nowrap> 
              <input type="text" name="E03ACMACC" size="12" maxlength="12" value="<%= rtDif.getE03ACMACC().trim()%>" readonly>
            </td>
            <td nowrap> 
              <div align="right"><b>Producto:</b></div>
            </td>
            <td nowrap> 
              <input type="text" name="E03ACMPRO" size="4" maxlength="4" value="<%= rtDif.getE03ACMPRO().trim()%>" readonly>
            </td>
          </tr>
        </table>
      </td>
    </tr>
  </table>
  <br>
  <table class="tableinfo">
    <tr> 
      <td nowrap> 
        <table cellspacing="0" cellpadding="2" width="100%" border="0">
          <tr id="trdark">
            <td nowrap width="45%">
              <div align="right">Cuenta Contable de la Relaci&oacute;n :</div>
            </td>
            <td nowrap>
              <INPUT type="text" name="E03INVRBK" size="2" maxlength="2" value="<%= rtDif.getE03INVRBK().trim()%>">
              <INPUT type="text" name="E03INVRBR" size="3" maxlength="3" value="<%= rtDif.getE03INVRBR().trim()%>" 
                <% if (!userPO.getPurpose().equals("APPROVAL_INQ")) {%>
                oncontextmenu="showPopUp(branchHelp,this.name,document.forms[0].E03INVRBK.value,'','','',''); return false;"
                <% } %>>
              <INPUT type="text" name="E03INVRCY" size="3" maxlength="3" value="<%= rtDif.getE03INVRCY().trim()%>" 
                <% if (!userPO.getPurpose().equals("APPROVAL_INQ")) {%> oncontextmenu="showPopUp(currencyHelp,this.name,document.forms[0].E03INVRBK.value,'','','',''); return false;"<% } %>>              
              <input type="text" name="E03INVRGL" size="16" maxlength="16" value="<%= rtDif.getE03INVRGL().trim()%>" onKeypress="enterInteger()" 
                <% if (!userPO.getPurpose().equals("APPROVAL_INQ")) {%> oncontextmenu="showPopUp(ledgerHelp,this.name,document.forms[0].E03INVRBK.value,document.forms[0].E03INVRCY.value,'','',''); return false;"<% } %>>              
            </td>
          </tr>
          <tr id="trclear"> 
            <td nowrap> 
              <div align="right">Cuenta Detalle de la Relaci&oacute;n :</div>
            </td>
            <td nowrap > 
              <div align="left">
                <input type="text" name="E03INVRAC" size="16" maxlength="16" value="<%= rtDif.getE03INVRAC().trim()%>" onKeypress="enterInteger()"
				<% if (!userPO.getPurpose().equals("APPROVAL_INQ")) {%> oncontextmenu="showPopUp(accountHelp,this.name,document.forms[0].E03INVRBK.value,'','','','RT'); return false;"<% } %>>
              </div>
            </td>
          </tr>
          <tr id="trdark"> 
            <td nowrap> 
              <div align="right">Fecha de Inicio Relaci&oacute;n :</div>
            </td>
            <td nowrap> 
              <input type="text" name="E03INVSD1" size="2" maxlength="2" value="<%= rtDif.getE03INVSD1().trim()%>" onKeypress="enterInteger()">
              <input type="text" name="E03INVSD2" size="2" maxlength="2" value="<%= rtDif.getE03INVSD2().trim()%>" onKeypress="enterInteger()">
              <input type="text" name="E03INVSD3" size="2" maxlength="2" value="<%= rtDif.getE03INVSD3().trim()%>" onKeypress="enterInteger()">
            </td>
          </tr>
          <tr id="trclear"> 
            <td nowrap> 
              <div align="right">Fecha Vencimiento Relaci&oacute;n :</div>
            </td>
            <td nowrap> 
              <div align="left"> 
                <input type="text" name="E03INVMD1" size="2" maxlength="2" value="<%= rtDif.getE03INVMD1().trim()%>" onKeypress="enterInteger()">
                <input type="text" name="E03INVMD2" size="2" maxlength="2" value="<%= rtDif.getE03INVMD2().trim()%>" onKeypress="enterInteger()">
                <input type="text" name="E03INVMD3" size="2" maxlength="2" value="<%= rtDif.getE03INVMD3().trim()%>" onKeypress="enterInteger()">
              </div>
            </td>
          </tr>
        </table>
      </td>
    </tr>
  </table>
 <% if (userPO.getPurpose().equals("APPROVAL_INQ")) {%>
 <SCRIPT Language="Javascript">
   var j=document.forms[0].elements.length;
    var felem=document.forms[0].elements;
    for(i=0;i< j;i++){
       if (felem[i].tagName!=="select"){
         if (felem[i].type=="text") felem[i].readOnly=true; else felem[i].disabled=true;
       } 
       else { felem[i].disabled=true;}
    }
</SCRIPT>
 
 <% } else { %>    
  <p>&nbsp;</p>
          <div align="center"> 
            <input id="EIBSBTN" type=submit name="Submit" value="Enviar">
          </div>
 <% } %>
</form>
</body>
</html>
