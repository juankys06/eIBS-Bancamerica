<html>
<head>
<title>Concentracion de Cuentas</title>
<META HTTP-EQUIV="Content-Type" CONTENT="text/html; charset=ISO-8859-1">
<link Href="<%=request.getContextPath()%>/pages/style.css" rel="stylesheet">

<jsp:useBean id="rtConc" class="datapro.eibs.beans.EDD043002Message"  scope="session" />

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
<H3 align="center">Concentraci&oacute;n de Cuentas<img src="<%=request.getContextPath()%>/images/e_ibs.gif" align="left" name="EIBS_GIF" ALT="rt_conc_det, EDD0430"></H3>
<hr size="4">
<form method="post" action="<%=request.getContextPath()%>/servlet/datapro.eibs.products.JSEDD0430" >
  <input type=HIDDEN name="SCREEN" value="4">
 <table class="tableinfo">
    <tr > 
      <td nowrap>
        <table cellspacing="0" cellpadding="2" width="100%" border="0" class="tbhead">
          <tr id="trdark"> 
            <td nowrap> 
              <div align="right"><b>Cliente:</b></div>
            </td>
            <td nowrap> 
              <input type="text" name="E02ACMCUN" size="9" maxlength="9" value="<%= userPO.getHeader2().trim()%>" readonly>
            </td>
            <td nowrap> 
              <div align="right"><b>Nombre:</b></div>
            </td>
            <td nowrap> 
              <input type="text" name="E02CUSNA1" size="45" maxlength="45" value="<%= userPO.getHeader3().trim()%>" readonly>
            </td>            
          </tr>
          <tr id="trdark"> 
            <td nowrap> 
              <div align="right"><b>Cuenta:</b></div>
            </td>
            <td nowrap> 
              <input type="text" name="E02ACMACC" size="12" maxlength="12" value="<%= userPO.getIdentifier().trim()%>" readonly>
            </td>
            <td nowrap> 
              <div align="right"><b>Producto:</b></div>
            </td>
            <td nowrap> 
              <input type="text" name="E02ACMPRO" size="4" maxlength="4" value="<%= userPO.getHeader1().trim()%>" readonly>
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
              <div align="left"> 
                <input type="text" name="E02INVRAC" size="12" maxlength="12" value="<%= rtConc.getE02INVRAC().trim()%>" onKeypress="enterInteger()" >
              <a href="javascript:GetAccount('E02INVRAC','','RT','')">
              <img src="<%=request.getContextPath()%>/images/1b.gif" alt=". . ." align="absbottom" border="0" ></a> 
              </div>
            </td>
          </tr>
          <tr id="trclear"> 
            <td nowrap> 
              <div align="right">Transferir en Multiplos de :</div>
            </td>
            <td nowrap> 
              <div align="left"> 
                <input type="text" name="E02INVMUL" size="13" maxlength="13" value="<%= rtConc.getE02INVMUL().trim()%>" onKeypress="enterInteger()">
              </div>
            </td>
          </tr>
          <tr id="trdark"> 
            <td nowrap> 
              <div align="right">Monto M&iacute;nimo Transferencia :</div>
            </td>
            <td nowrap> 
              <div align="left"> 
                <input type="text" name="E02INVMIN" size="13" maxlength="13" value="<%= rtConc.getE02INVMIN().trim()%>" onKeypress="enterDecimal()">
              </div>
            </td>
          </tr>
          <tr id="trclear"> 
            <td nowrap> 
              <div align="right">Monto M&aacute;ximo de Transferencia :</div>
            </td>
            <td nowrap> 
              <input type="text" name="E02INVMAX" size="12" maxlength="12" value="<%= rtConc.getE02INVMAX().trim()%>" onKeypress="enterDecimal()">
            </td>
          </tr>
          <tr id="trdark"> 
            <td nowrap> 
              <div align="right">Saldo M&iacute;nimo en Cuenta Relacionada :</div>
            </td>
            <td nowrap> 
              <input type="text" name="E02INVBLS" size="12" maxlength="12" value="<%= rtConc.getE02INVBLS().trim()%>" onKeypress="enterDecimal()">
            </td>
          </tr>
          <tr id="trclear"> 
            <td nowrap> 
              <div align="right">Fecha de Inicio Relaci&oacute;n :</div>
            </td>
            <td nowrap> 
              <input type="text" name="E02INVSD1" size="2" maxlength="2" value="<%= rtConc.getE02INVSD1().trim()%>" onKeypress="enterInteger()">
              <input type="text" name="E02INVSD2" size="2" maxlength="2" value="<%= rtConc.getE02INVSD2().trim()%>" onKeypress="enterInteger()">
              <input type="text" name="E02INVSD3" size="2" maxlength="2" value="<%= rtConc.getE02INVSD3().trim()%>" onKeypress="enterInteger()">
            </td>
          </tr>
          <tr id="trdark"> 
            <td nowrap> 
              <div align="right">Fecha Vencimiento Relaci&oacute;n :</div>
            </td>
            <td nowrap> 
              <div align="left"> 
                <input type="text" name="E02INVMD1" size="2" maxlength="2" value="<%= rtConc.getE02INVMD1().trim()%>" onKeypress="enterInteger()">
                <input type="text" name="E02INVMD2" size="2" maxlength="2" value="<%= rtConc.getE02INVMD2().trim()%>" onKeypress="enterInteger()">
                <input type="text" name="E02INVMD3" size="2" maxlength="2" value="<%= rtConc.getE02INVMD3().trim()%>" onKeypress="enterInteger()">
              </div>
            </td>
          </tr>
        </table>
      </td>
    </tr>
  </table>
  <br>
          <div align="center"> 
            <input id="EIBSBTN" type=submit name="Submit" value="Enviar">
          </div>
  </form>
</body>
</html>
