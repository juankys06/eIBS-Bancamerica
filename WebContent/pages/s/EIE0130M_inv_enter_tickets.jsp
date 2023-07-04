<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<link href="<%=request.getContextPath()%>/pages/style.css" rel="stylesheet">

<script language="Javascript1.1" src="<%=request.getContextPath()%>/pages/s/javascripts/eIBS.jsp"> </SCRIPT>

<jsp:useBean id= "error" class= "datapro.eibs.beans.ELEERRMessage"  scope="session"/>

<script language="JavaScript">
function advanceSearch(){
	  document.forms[0].SCREEN.value = 300;
	  document.forms[0].submit();
	 }
</script>

<META name="GENERATOR" content="IBM WebSphere Studio">
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
 <FORM METHOD="post" ACTION="<%=request.getContextPath()%>/servlet/datapro.eibs.invest.JSEWD0309" >
	<INPUT TYPE=HIDDEN NAME="SCREEN" VALUE="13">
  <input type=HIDDEN name="SEARCHC" value="">
  <h3 align="center">Mantenimiento Ordenes de Compra - Venta</h3>
  <table class="tbenter" height="55%" width="100%" border="0">
    <tr> 
      <td nowrap align="center"> 
        <table class="tableinfo" align="center" width="100%" >
          <tr> 
            <td> 
              <table width="100%">
                <tr tr id="trdark"> 
                  <td valign="middle" align="center" colspan="2" height=33> 
                    <div align="left"><b>Buscar por</b></div>
                  </td>
                </tr>
                <tr id="trclear"> 
                  <td width="27%" nowrap> 
                    <div align="right"> N&uacute;mero de Transacci&oacute;n:</div>
                  </td>
                  <td width="73%" nowrap> 
                    <input type="text" name="ORDERNUM"  size=9 maxlength="9" 
					  onKeyPress="document.forms[0].SEARCHC.value='O'">
                    o</td>
                </tr>
                <tr id="trclear"> 
                  <td width="27%" nowrap> 
                    <div align="right">Cliente :</div>
                  </td>
                  <td width="73%" nowrap> 
                    <input type="text" name="CUSTOMER"  size=9 maxlength="9" 
						onKeyPress="document.forms[0].SEARCHC.value='C'">
                    <a href="javascript:GetCustomerDescId('CUSTOMER','','')"><img src="<%=request.getContextPath()%>/images/1b.gif" alt="help" align="absbottom" border="0" ></a> 
                    o</td>
                </tr>
                <tr id="trclear"> 
                  <td width="27%" nowrap> 
                    <div align="right">Portafolio :</div>
                  </td>
                  <td nowrap width="73%"> 
                    <input type="text" name="PORTFOLIO"  size=6 maxlength="5" 
					onKeyPress="document.forms[0].SEARCHC.value='P'">
                    <a href="javascript:GetPortfolioNumDesc('PORTFOLIO','',document.forms[0].CUSTOMER.value)"><img src="<%=request.getContextPath()%>/images/1b.gif" alt="help" align="absbottom" border="0" ></a> 
                    o</td>
                </tr>
                <tr id="trclear"> 
                  <td width="27%" nowrap> 
                    <div align="right">Broker : </div>
                  </td>
                  <td nowrap width="73%"> 
                    <input type="text" name="BROKER"  size=4 maxlength="3" 
					onKeyPress="document.forms[0].SEARCHC.value='B'">
                    <input type="text" name="BDESC"  size=35 maxlength="30" 
					>
                    <a href="javascript:GetBrokerI('BROKER','BDESC')"><img src="<%=request.getContextPath()%>/images/1b.gif" alt="help" align="absmiddle" border="0"></a> 
                    o</td>
                </tr>
                <tr id="trclear">
                  <td width="27%" nowrap >
                    <div align="right">Instrumento :</div>
                  </td>
                  <td nowrap width="73%" >
                    <input type="text" name="INSTCODE" size="9" maxlength="9" >
                    <input type="text" name="DESCRIPTION" size="35" maxlength="30">
                    <a href="javascript:GetInstrumentParams('INSTCODE','DESCRIPTION','','','','','')"><img src="<%=request.getContextPath()%>/images/1b.gif" alt="help" align="absmiddle" border="0"></a></td>
                </tr>
                <tr id="trclear"> 
                  <td width="27%" nowrap > 
                    <div align="right">Emisor : </div>
                  </td>
                  <td nowrap width="73%" > 
                    <input type="text" name="ISSUER"  size=35 maxlength="30" 
					onKeyPress="document.forms[0].SEARCHC.value='I'">
                  </td>
                </tr>
                <tr id="trclear"> 
                  <td width="27%" nowrap > 
                    <div align="right">Tipo de Instrumento : </div>
                  </td>
                  <td nowrap width="73%" > 
                    <div align="left"> 
                      <select name="TYPE">
                        <option value=" ">Todos </option>
                        <option value="BND">Bonos</option>
                        <option value="EQT">Acciones</option>
                        <option value="MUT">Fondos Mutuos</option>
                        <option value="PFS">Acciones Preferenciales</option>
                      </select>
                    </div>
                  </td>
                </tr>
                <tr id="trclear"> 
                  <td width="27%" nowrap > 
                    <div align="right">Tipo de Transacci&oacute;n :</div>
                  </td>
                  <td nowrap width="73%" > 
                    <select name="TRANSTYPE">
                      <option value="">Todos</option>
                      <option value="1">Compra</option>
                      <option value="2">Venta</option>
                      <option value="3">Transferencia de Entrada</option>
                      <option value="4">Transferencia de Salida</option>
                      <option value="5">Compras Múltiples</option>
                    </select>
                  </td>
                </tr>
                <tr id="trclear"> 
                  <td width="31%" nowrap > 
                    <div align="right">Fecha de la Orden : </div>
                  </td>
                  <td nowrap width="69%" > 
                    <div align="left"> 
                      <input type="text" size="2" maxlength="2" name="DATEF1" 
					  onKeyPress="document.forms[0].SEARCHC.value='D'">
                      <input type="text" size="2" maxlength="2" name="DATEF2" onKeyPress="enterInteger()">
                      <input type="text" size="2" maxlength="2" name="DATEF3" onKeyPress="enterInteger()">
                      <a href="javascript:DatePicker(document.forms[0].DATEF1,document.forms[0].DATEF2,document.forms[0].DATEF3)"><img src="<%=request.getContextPath()%>/images/calendar.gif" alt="help" align="absmiddle" border="0"></a> 
                      para 
                      <input type="text" size="2" maxlength="2" name="DATET1" 
					   onKeyPress="document.forms[0].SEARCHC.value='D'">
                      <input type="text" size="2" maxlength="2" name="DATET2" onKeyPress="enterInteger()">
                      <input type="text" size="2" maxlength="2" name="DATET3" onKeyPress="enterInteger()">
                      <a href="javascript:DatePicker(document.forms[0].DATET1,document.forms[0].DATET2,document.forms[0].DATET3)"><img src="<%=request.getContextPath()%>/images/calendar.gif" alt="help" align="absmiddle" border="0"></a> 
                    </div>
                  </td>
                </tr>
              </table>
            </td>
          </tr>
        </table>
        <table width="100%" border="0" cellspacing="0" cellpadding="0" bgcolor="#FFFFFF" bordercolor="#FFFFFF">
          <tr bgcolor="#FFFFFF"> 
            <td width="33%"> 
              <div align="center"> 
                <input id="EIBSBTN" type=submit name="Submit" value="Enviar">
              </div>
            </td>
          </tr>
          <tr bgcolor="#FFFFFF"> 
            <td> 
              <div align="center"> </div>
            </td>
          </tr>
        </table>
      </td>
    </tr>
  </table>
</form>
</body>
</html>
