<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.0 Transitional//EN">
<html>
<head>
<title>I.B.S.</title>
<META http-equiv="Content-Style-Type" content="text/css">
<link href="<%=request.getContextPath()%>/pages/style.css" rel="stylesheet">

<script language="Javascript1.1" src="<%=request.getContextPath()%>/pages/s/javascripts/eIBS.jsp"> </SCRIPT>

<jsp:useBean id="error" class="datapro.eibs.beans.ELEERRMessage"  scope="session" />

</head>

<body>

 <h3 align="center">Impresion de Comprobante Fiscal<img src="<%=request.getContextPath()%>/images/e_ibs.gif" align="left" name="EIBS_GIF" alt="enter_customer,EFA0000"></h3>
<hr size="4">
<form method="post" action="<%=request.getContextPath()%>/servlet/datapro.eibs.client.JSEFA0000">
  <p>
    <INPUT TYPE=HIDDEN NAME="SCREEN" VALUE="200">
  </p>
  <h4 align="center">Entre Numero de Cliente</h4>
  <p>&nbsp; </p>
  <table class="tableinfo">
    <tr> 
      <td nowrap> 
        <table cellspacing="0" cellpadding="2" width="100%" border="0">
          <tr id="trdark"> 
            <td nowrap align="right" valign="middle" width="49%">No. de Cliente :</td>
            <td nowrap align="left" valign="middle"  width="51%">
                <INPUT type="text" name="E01TRFCUN" value="" maxlength="10" size="10"> 
                <a href="javascript:GetCustomerDescId('E01TRFCUN','','')"><img src="<%=request.getContextPath()%>/images/1b.gif" alt="help" align="bottom" border="0" ></a>
                <img src="<%=request.getContextPath()%>/images/Check.gif" alt="mandatory field" align="bottom" border="0"  >
            </td>
          </tr>
          <tr>
            <td nowrap align="right" valign="middle" width="49%">Accion :</td>
            <td nowrap align="left" valign="middle"  width="51%">
                        <input type=radio name="E01OPESEL" value="I" checked="checked">Impresion
                        <input type=radio name="E01OPESEL" value="R">Reimpresion
            </td>          
          </tr>
          <tr>
            <td nowrap align="right" valign="middle" width="49%">Fechas de Facturas :</td>
            <td nowrap align="left" valign="middle"  width="51%">Desde :
				<input type="text" name="E01FRDTE1" size="2" maxlength="2" value="" onkeypress="enterInteger()">
				<input type="text" name="E01FRDTE2" size="2" maxlength="2" value="" onkeypress="enterInteger()">
				<input type="text" name="E01FRDTE3" size="2" maxlength="2" value="" onkeypress="enterInteger()">
				<a href="javascript:DatePicker(document.forms[0].E01FRDTE1,document.forms[0].E01FRDTE2,document.forms[0].E01FRDTE3)"><img src="<%=request.getContextPath()%>/images/calendar.gif" alt="Help" align="middle" border="0"></a>
				&nbsp;Hasta :
				<input type="text" name="E01TODTE1" size="2" maxlength="2" value="" onkeypress="enterInteger()">
				<input type="text" name="E01TODTE2" size="2" maxlength="2" value="" onkeypress="enterInteger()">
				<input type="text" name="E01TODTE3" size="2" maxlength="2" value="" onkeypress="enterInteger()">
				<a href="javascript:DatePicker(document.forms[0].E01TODTE1,document.forms[0].E01TODTE2,document.forms[0].E01TODTE3)"><img src="<%=request.getContextPath()%>/images/calendar.gif" alt="Help" align="middle" border="0"></a>


            </td>          
          </tr>
        </table>
      </td>
    </tr>
  </table>
  <p align="center"> 
   <input id="EIBSBTN" type=submit name="Submit" value="Enviar">
  </p>
<script language="JavaScript">
  document.forms[0].E01TRFCUN.focus();
  document.forms[0].E01TRFCUN.select();
</script>
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
</form>
</body>
</html>
