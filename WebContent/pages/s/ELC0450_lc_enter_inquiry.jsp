<html>
<head>
<title>Consulta de Cartas de Crédito</title>
<META HTTP-EQUIV="Content-Type" CONTENT="text/html; charset=ISO-8859-1">
<link Href="<%=request.getContextPath()%>/pages/style.css" rel="stylesheet">

<script language="Javascript1.1" src="<%=request.getContextPath()%>/pages/s/javascripts/eIBS.jsp"> </SCRIPT>

<SCRIPT Language="Javascript">
function redirect() {
	if (document.forms[0].E01LCMACC.value != "") {
		document.forms[0].SCREEN.value = 200;
	}
	else {
		document.forms[0].SCREEN.value = 210;
	}
}

</SCRIPT>

</head>

<jsp:useBean id= "error" class= "datapro.eibs.beans.ELEERRMessage"  scope="session" />

<jsp:useBean id= "userPO" class= "datapro.eibs.beans.UserPos"  scope="session" />

<body nowrap bgcolor="#FFFFFF">

<h3 align="center">Consulta de Cartas de Crédito<img src="<%=request.getContextPath()%>/images/e_ibs.gif" align="left" name="EIBS_GIF" ALT="lc_enter_inquiry.jsp, ELC0450"></h3>
<hr size="4">

<form method="post" action="<%=request.getContextPath()%>/servlet/datapro.eibs.trade.JSELC0450" onSubmit="return redirect();">
  <INPUT TYPE=HIDDEN NAME="SCREEN" VALUE="200">

  <table class="tableinfo">
    <tr > 
      <td nowrap> 
        <table cellspacing="0" cellpadding="2" width="100%" border="0" >
          <tr id="trdark">
            <td width="50%"> 
              <div align="right">Número de Carta de Crédito : </div>
            </td>
            <td width="50%"> 
              <div align="left"> 
	              <input type="text" name="E01LCMACC" size="13" maxlength="12" onKeypress="enterInteger()">
    	          <a href="javascript:GetAccount('E01LCMACC','','LC','')"><img src="<%=request.getContextPath()%>/images/1b.gif" alt=". . ." align="absbottom" border="0" ></a> 
              </div>
            </td>
          </tr>
         </table>
      </td>
    </tr>
  </table>
  <br>
  <h5 align="center">o</h5>
  <table class="tableinfo">
    <tr > 
      <td nowrap> 
        <table cellspacing="0" cellpadding="2" width="100%" border="0" >
          <tr id="trdark">
            <td width="30%" nowrap>
              <div align="right">Número de Cliente : </div>
            </td>
            <td width="70%" nowrap>
              <input type="text" name="E01SCHCUN" size="10" maxlength="9" onKeyPress="enterInteger()">
              <a href="javascript:GetCustomer('E01SCHCUN')"><img src="<%=request.getContextPath()%>/images/1b.gif" alt="Help" align="absbottom" border="0"></a>
            </td>
          </tr>
          <tr id="trclear">
            <td width="30%" nowrap> 
              <div align="right">Fecha de Emisión : </div>
            </td>
            <td width="70%" nowrap>
              Del 
              <input type="text" size="3" maxlength="2" name="E01SCHFI1" onKeypress="enterInteger()">
              <input type="text" size="3" maxlength="2" name="E01SCHFI2" onKeyPress="enterInteger()">
              <input type="text" size="3" maxlength="2" name="E01SCHFI3" onKeyPress="enterInteger()">
              al 
              <input type="text" size="3" maxlength="2" name="E01SCHTI1" onKeypress="enterInteger()">
              <input type="text" size="3" maxlength="2" name="E01SCHTI2" onKeyPress="enterInteger()">
              <input type="text" size="3" maxlength="2" name="E01SCHTI3" onKeyPress="enterInteger()">
            </td>
          </tr>
          <tr id="trdark">
            <td width="30%" nowrap> 
              <div align="right">Fecha de Vencimiento : </div>
            </td>
            <td width="70%" nowrap>
              Del 
              <input type="text" size="3" maxlength="2" name="E01SCHFE1" onKeypress="enterInteger()">
              <input type="text" size="3" maxlength="2" name="E01SCHFE2" onKeyPress="enterInteger()">
              <input type="text" size="3" maxlength="2" name="E01SCHFE3" onKeyPress="enterInteger()">
              al 
              <input type="text" size="3" maxlength="2" name="E01SCHTE1" onKeypress="enterInteger()">
              <input type="text" size="3" maxlength="2" name="E01SCHTE2" onKeyPress="enterInteger()">
              <input type="text" size="3" maxlength="2" name="E01SCHTE3" onKeyPress="enterInteger()">
            </td>
          </tr>
          <tr id="trclear">
            <td width="30%" nowrap>
              <div align="right">Tipo de Cuenta : </div>
            </td>
            <td width="70%" nowrap>
				<SELECT name="E01SCHACD">
					<OPTION value="40">Carta de Crédito</OPTION>
					<OPTION value="41">Aceptación</OPTION>
					<OPTION value="42">Pago Diferido</OPTION>
				</SELECT>
            </td>
          </tr>
          <tr id="trdark">
            <td width="30%" nowrap>
              <div align="right">Tipo de Producto : </div>
            </td>
            <td width="70%" nowrap>
              <input type="text" name="E01SCHATY" size="5" maxlength="4">
              <a href="javascript:GetProductRates('E01SCHATY','LC')"><img src="<%=request.getContextPath()%>/images/1b.gif" alt="Help" align="absbottom" border="0"></a>
            </td>
          </tr>
          <tr id="trclear">
            <td width="30%" nowrap>
              <div align="right">Código de Producto : </div>
            </td>
            <td width="70%" nowrap>
              <input type="text" name="E01SCHPRO" size="5" maxlength="4">
              <a href="javascript:GetProduct('E01SCHPRO','LC','')"><img src="<%=request.getContextPath()%>/images/1b.gif" alt="Help" align="absbottom" border="0"></a>
            </td>
          </tr>
          <tr id="trdark">
            <td width="30%" nowrap>
              <div align="right">Agencia : </div>
            </td>
            <td width="70%" nowrap>
              <input type="text" name="E01SCHBRN" size="4" maxlength="3">
              <a href="javascript:GetBranch('E01SCHBRN','','')"><img src="<%=request.getContextPath()%>/images/1b.gif" alt="Help" align="absbottom" border="0"></a>
            </td>
          </tr>
          <tr id="trclear">
            <td width="30%" nowrap>
              <div align="right">Moneda : </div>
            </td>
            <td width="70%" nowrap>
              <input type="text" name="E01SCHCCY" size="4" maxlength="3">
              <a href="javascript:GetCurrency('E01SCHCCY','')"><img src="<%=request.getContextPath()%>/images/1b.gif" alt="Help" align="absbottom" border="0"></a>
            </td>
          </tr>
          <tr id="trdark">
            <td width="30%" nowrap > 
              <div align="right">Monto : </div>
            </td>
            <td width="70%" nowrap >
          	  Desde 
              <input type="text" name="E01SCHAMF" size="20" maxlength="19" onKeypress="enterDecimal()">
              hasta
              <input type="text" name="E01SCHAMT" size="20" maxlength="19" onKeypress="enterDecimal()">
            </td>
          </tr>
          <tr id="trclear">
            <td width="30%" nowrap>
              <div align="right">Nombre del Aplicante : </div>
            </td>
            <td width="70%" nowrap>
              <input type="text" name="E01SCHAPP" size="40" maxlength="35">
            </td>
          </tr>
          <tr id="trdark">
            <td width="30%" nowrap>
              <div align="right">Nombre del Beneficiario : </div>
            </td>
            <td width="70%" nowrap>
              <input type="text" name="E01SCHBNF" size="40" maxlength="35">
            </td>
          </tr>
          <tr id="trclear">
            <td width="30%" nowrap>
              <div align="right">Tipo de Carta de Crédito : </div>
            </td>
            <td width="70%" nowrap>
				<SELECT name="E01SCHTYP">
					<OPTION value=""></OPTION>
					<OPTION value="O">Importación</OPTION>
					<OPTION value="I">Exportación</OPTION>
					<OPTION value="S">Stand-By Emitida</OPTION>
					<OPTION value="R">Stand-By Recibida</OPTION>
				</SELECT>
            </td>
          </tr>
          <tr id="trdark">
            <td width="30%" nowrap>
              <div align="right">Nuestra Referencia : </div>
            </td>
            <td width="70%" nowrap>
              <input type="text" name="E01SCHORF" size="17" maxlength="16">
            </td>
          </tr>
          <tr id="trclear">
            <td width="30%" nowrap>
              <div align="right">Referencia del Banco Corresponsal : </div>
            </td>
            <td width="70%" nowrap>
              <input type="text" name="E01SCHTRF" size="17" maxlength="16">
            </td>
          </tr>
          <tr id="trdark">
            <td width="30%" nowrap>
              <div align="right">Referencia ALADI : </div>
            </td>
            <td width="70%" nowrap>
              <input type="text" name="E01SCHSRF" size="17" maxlength="16">
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

<script language="JavaScript">
  document.forms[0].E01LCMACC.focus();
  document.forms[0].E01LCMACC.select();
</script>
<% 
 if ( !error.getERRNUM().equals("0")  ) {
      error.setERRNUM("0");
 %>
     <SCRIPT Language="Javascript">;
            showErrors();
     </SCRIPT>;
 <%
 }
%>
</form>
</body>
</html>
