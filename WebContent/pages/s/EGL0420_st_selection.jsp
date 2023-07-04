<html>
<head>
<title>Estado de Cuentas Contables</title>
<META HTTP-EQUIV="Content-Type" CONTENT="text/html; charset=ISO-8859-1">
<link Href="<%=request.getContextPath()%>/pages/style.css" rel="stylesheet">

<jsp:useBean id= "error" class= "datapro.eibs.beans.ELEERRMessage"  scope="session" />

<jsp:useBean id= "userPO" class= "datapro.eibs.beans.UserPos"  scope="session" />

</head>

<script language="Javascript1.1" src="<%=request.getContextPath()%>/pages/s/javascripts/eIBS.jsp"> </SCRIPT>

<body>

<% 
 if ( !error.getERRNUM().equals("0")  ) {
     error.setERRNUM("0");
     out.println("<SCRIPT Language=\"Javascript\">");
     out.println("       showErrors()");
     out.println("</SCRIPT>");
 }
%> 

<h3 align="center"> Consulta de Transacciones de Cuentas Contables<img src="<%=request.getContextPath()%>/images/e_ibs.gif" align="left" name="EIBS_GIF" alt="st_selection.jsp,EGL0420"> 
</h3>
<hr size="4">
<form method="post" action="<%=request.getContextPath()%>/servlet/datapro.eibs.client.JSEGL0420" >
  <INPUT TYPE=HIDDEN NAME="SCREEN" VALUE="200">
  <table class="tableinfo">
    <tr > 
      <td nowrap> 
        <table cellspacing=0 cellpadding=2 width="100%" border="0">
          <tr id="trdark"> 
            <td nowrap width="23%" height="31"> 
              <div align="right"><b>C&oacute;digo de Banco : </b></div>
            </td>
            <td nowrap width="32%" height="31"> 
              <input type="text" name="E01TRABNK" size="2" maxlength="2">
            </td>
          </tr>
          <tr id="trdark"> 
            <td nowrap width="23%"> 
              <div align="right"><b>N&uacute;mero de Sucursal : </b></div>
            </td>
            <td nowrap width="32%">
              <input type="text" name="E01TRABRN" size="3" maxlength="3">
              <a href="javascript:GetBranch('E01TRABRN',document.forms[0].E01TRABNK.value)"> 
              <img src="<%=request.getContextPath()%>/images/1b.gif" alt=". . ." align="absmiddle" border="0" > 
              </a> </td>
          </tr>
          <tr id="trdark">
            <td nowrap width="23%">
              <div align="right"><b>Moneda : </b></div>
            </td>
            <td nowrap width="32%">
              <input type="text" name="E01TRACCY" size="3" maxlength="3">
              <a href="javascript:GetCurrency('E01TRACCY',document.forms[0].E01TRABNK.value)"> 
              <img src="<%=request.getContextPath()%>/images/1b.gif" alt=". . ." align="absmiddle" border="0" > 
              </a> </td>
          </tr>
          <tr id="trdark">
            <td nowrap width="23%">
              <div align="right"><b>Cuenta Contable : </b></div>
            </td>
            <td nowrap width="32%">
              <input type="text" name="E01TRAGLN" size="20" maxlength="16" onKeypress="enterInteger()">
              <a href="javascript:GetLedger('E01TRAGLN',document.forms[0].E01TRABNK.value,document.forms[0].E01TRACCY.value,'')"> 
              <img src="<%=request.getContextPath()%>/images/1b.gif" alt=". . ." align="absmiddle" border="0" > 
              </a> </td>
          </tr>
        </table>
      </td>
    </tr>
  </table>
  <p>&nbsp;</p>
  <table class="tableinfo">
    <tr > 
      <td nowrap height="143"> 
        <table cellpadding=2 cellspacing=0 width="100%" border="0">
          <tr id="trdark"> 
            <td nowrap width="32%"> 
              <div align="right"><b>Seleccionar Transacci&oacute;n por :</b></div>
            </td>
            <td nowrap width="3%">&nbsp;</td>
            <td nowrap width="23%"> 
              <input type="radio" name="E01HISCYC" value="M" checked>
              <b>Este Ciclo</b></td>
            <td nowrap width="10%"> 
              <div align="right"></div>
            </td>
            <td nowrap width="32%"> 
              <input type="radio" name="E01HISCYC" value="H">
              <b>Hist&oacute;rico</b></td>
          </tr>
          <tr id="trclear"> 
            <td nowrap width="32%"> 
              <div align="right"><b>Tipo de Fecha :</b></div>
            </td>
            <td nowrap width="3%">&nbsp;</td>
            <td nowrap width="23%"> 
              <input type="radio" value="B" name="E01VALBTH" checked>
              <b> Fecha Proceso</b></td>
            <td nowrap width="10%"> 
              <div align="right"></div>
            </td>
            <td nowrap width="32%"> 
              <input type="radio" value="V" name="E01VALBTH" >
              <b>Fecha Valor</b></td>
          </tr>
          <tr id="trdark"> 
            <td nowrap width="32%"> 
              <div align="right"><b>Seleccionar Fechas desde :</b></div>
            </td>
            <td nowrap width="3%">&nbsp;</td>
            <td nowrap width="23%"> 
              <div align="left"> 
                <input type="text" name="E01FRDTE1" size="2" maxlength="2">
                <input type="text" name="E01FRDTE2" size="2" maxlength="2">
                <input type="text" name="E01FRDTE3" size="2" maxlength="2">
              </div>
            </td>
            <td nowrap width="10%"> 
              <div align="right"><b>hasta :</b></div>
            </td>
            <td nowrap width="32%"> 
              <div align="left"> 
                <input type="text" name="E01TODTE1" size="2" maxlength="2">
                <input type="text" name="E01TODTE2" size="2" maxlength="2">
                <input type="text" name="E01TODTE3" size="2" maxlength="2">
              </div>
            </td>
          </tr>
          <tr id="trclear"> 
            <td nowrap width="32%"> 
              <div align="right"><b>Seleccionar Referencia desde :</b></div>
            </td>
            <td nowrap width="3%">&nbsp;</td>
            <td nowrap width="23%"> 
              <div align="left"> 
                <input type="text" name="E01FRREFN" size="9" maxlength="9">
              </div>
            </td>
            <td nowrap width="10%"> 
              <div align="right"><b>hasta :</b></div>
            </td>
            <td nowrap width="32%"> 
              <div align="left"> 
                <input type="text" name="E01TOREFN" size="9" maxlength="9">
              </div>
            </td>
          </tr>
          <tr id="trdark"> 
            <td nowrap width="32%" height="26"> 
              <div align="right"><b>Seleccionar Montos desde :</b></div>
            </td>
            <td nowrap width="3%" height="26">&nbsp;</td>
            <td nowrap width="23%" height="26"> 
              <div align="left"> 
                <input type="text" name="E01FRAMNT" size="15" maxlength="15" onKeypress="enterDecimal()">
              </div>
            </td>
            <td nowrap width="10%" height="26"> 
              <div align="right"><b>hasta :</b></div>
            </td>
            <td nowrap width="32%" height="26"> 
              <div align="left"> 
                <input type="text" name="E01TOAMNT" size="15" maxlength="15" onKeypress="enterDecimal()">
              </div>
            </td>
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
