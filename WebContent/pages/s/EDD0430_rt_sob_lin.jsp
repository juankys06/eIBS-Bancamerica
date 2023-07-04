<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<title>Sobregiros en Linea</title>
<META HTTP-EQUIV="Content-Type" CONTENT="text/html; charset=ISO-8859-1">
<META name="GENERATOR" content="IBM WebSphere Studio">
<link Href="<%=request.getContextPath()%>/pages/style.css" rel="stylesheet">

<jsp:useBean id="rtConc" class="datapro.eibs.beans.EDD043004Message"  scope="session" />

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

<H3 align="center">Sobregiros en Línea<img src="<%=request.getContextPath()%>/images/e_ibs.gif" align="left" name="EIBS_GIF" ALT="rt_sob_lin,EDD0430"></H3>
<hr size="4">
<form method="post" action="<%=request.getContextPath()%>/servlet/datapro.eibs.products.JSEDD0430" >
  <input type=HIDDEN name="SCREEN" value="8">
 <table class="tableinfo">
    <tr > 
      <td nowrap>
        <table cellspacing="0" cellpadding="2" width="100%" border="0" class="tbhead">
          <tr id="trdark"> 
            <td nowrap> 
              <div align="right"><b>Cliente:</b></div>
            </td>
            <td nowrap> 
              <input type="text" name="E04ACMCUN" size="9" maxlength="9" value="<%= rtConc.getE04ACMCUN().trim()%>">
            </td>
            <td nowrap> 
              <div align="right"><b>Nombre:</b></div>
            </td>
            <td nowrap> 
              <input type="text" name="E04CUSNA1" size="45" maxlength="45" value="<%= rtConc.getE04CUSNA1().trim()%>">
            </td>
          </tr>
          <tr id="trdark"> 
            <td nowrap> 
              <div align="right"><b>Cuenta:</b></div>
            </td>
            <td nowrap> 
              <input type="text" name="E04ACMACC" size="12" maxlength="12" value="<%= rtConc.getE04ACMACC().trim()%>">
            </td>
            <td nowrap> 
              <div align="right"><b>Producto:</b></div>
            </td>
            <td nowrap> 
              <input type="text" name="E04ACMPRO" size="4" maxlength="4" value="<%= rtConc.getE04ACMPRO().trim()%>">
            </td>
          </tr>
        </table>
      </td>
    </tr>
  </table>
  <br>
  <table class="tableinfo">
    <tr > 
      <td nowrap> 
        <table cellspacing="0" cellpadding="2" width="100%" border="0">
          <tr id="trdark"> 
            <td nowrap width="45%"> 
              <div align="right">Cuenta de la Relaci&oacute;n :</div>
            </td>
            <td nowrap> 
              <input type="text" name="E04INVRAC" size="12" maxlength="12" value="<%= rtConc.getE04INVRAC().trim()%>" onKeyPress="enterInteger()" 
                oncontextmenu="showPopUp(accountHelp,this.name,'','','','','RT'); return false;">
            </td>
          </tr>
          <tr id="trclear"> 
            <td nowrap> 
              <div align="right">Transferir en Multiplos de :</div>
            </td>
            <td nowrap> 
              <div align="left"> 
                <input type="text" name="E04INVMUL" size="13" maxlength="13" value="<%= rtConc.getE04INVMUL().trim()%>" onKeypress="enterInteger()">
              </div>
            </td>
          </tr>
          <tr id="trdark"> 
            <td nowrap> 
              <div align="right">Monto M&iacute;nimo Transferencia :</div>
            </td>
            <td nowrap> 
              <div align="left"> 
                <input type="text" name="E04INVMIN" size="13" maxlength="13" value="<%= rtConc.getE04INVMIN().trim()%>" onKeypress="enterDecimal()">
              </div>
            </td>
          </tr>
          <tr id="trclear"> 
            <td nowrap> 
              <div align="right">Monto M&aacute;ximo de Transferencia :</div>
            </td>
            <td nowrap> 
              <input type="text" name="E04INVMAX" size="12" maxlength="12" value="<%= rtConc.getE04INVMAX().trim()%>" onKeypress="enterDecimal()">
            </td>
          </tr>
          <tr id="trdark"> 
            <td nowrap> 
              <div align="right">Saldo M&iacute;nimo en Cuenta Relacionada :</div>
            </td>
            <td nowrap> 
              <input type="text" name="E04INVBLS" size="12" maxlength="12" value="<%= rtConc.getE04INVBLS().trim()%>" onKeypress="enterDecimal()">
            </td>
          </tr>
          <tr id="trclear"> 
            <td nowrap> 
              <div align="right">Fecha de Inicio Relaci&oacute;n :</div>
            </td>
            <td nowrap> 
              <input type="text" name="E04INVSD1" size="2" maxlength="2" value="<%= rtConc.getE04INVSD1().trim()%>">
              <input type="text" name="E04INVSD2" size="2" maxlength="2" value="<%= rtConc.getE04INVSD2().trim()%>">
              <input type="text" name="E04INVSD3" size="2" maxlength="2" value="<%= rtConc.getE04INVSD3().trim()%>">
            </td>
          </tr>
          <tr id="trdark"> 
            <td nowrap> 
              <div align="right">Fecha Vencimiento Relaci&oacute;n :</div>
            </td>
            <td nowrap> 
              <div align="left"> 
                <input type="text" name="E04INVMD1" size="2" maxlength="2" value="<%= rtConc.getE04INVMD1().trim()%>">
                <input type="text" name="E04INVMD2" size="2" maxlength="2" value="<%= rtConc.getE04INVMD2().trim()%>">
                <input type="text" name="E04INVMD3" size="2" maxlength="2" value="<%= rtConc.getE04INVMD3().trim()%>">
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
  <p align="center">
          <div align="center"> 
            <input id="EIBSBTN" type=submit name="Submit" value="Enviar">
          </div>
  </p>
 <% } %>
  </form>
</body>
</html>
