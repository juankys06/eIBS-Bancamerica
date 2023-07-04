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
<FORM METHOD="post" ACTION="<%=request.getContextPath()%>/servlet/datapro.eibs.invest.JSEWD0315I" >
	<INPUT TYPE=HIDDEN NAME="SCREEN" VALUE="1">
 	<INPUT TYPE=HIDDEN NAME="SEARCHC" VALUE="">
    
  <h3 align="center">Consulta Pago de Cup&oacute;n</h3>
  <table class="tbenter" height="55%" width="100%" border="0">
    <tr> 
      <td nowrap align="center"> 
        <table  class="tableinfo">
          <tr > 
            <td nowrap> 
              <table cellpadding=2 cellspacing=0 width="100%" border="0">
                <tr id="trdark"> 
                  <td nowrap colspan="2" > 
                    <div align="left"><b>Buscar por:</b></div>
                  </td>
                </tr>
                <tr id="trclear"> 
                  <td nowrap width="17%" > 
                    <div align="right"><a href="javascript:showInstrumentInq(document.forms[0].INSTCODE.value)"><img src="<%=request.getContextPath()%>/images/aquire.gif" alt="help" align="absbottom" border="0" > 
                      C&oacute;digo de Instrumento</a> :</div>
                  </td>
                  <td nowrap > 
                    <div align="left"> 
                      <input type="text" name="INSTCODE" size="9" maxlength="9" >
                      <input type="text" name="DESCRIPTION" size="35" maxlength="30">
                      <a href="javascript:GetInstrumentParams('INSTCODE','DESCRIPTION','','','','','')"><img src="<%=request.getContextPath()%>/images/1b.gif" alt="help" align="absmiddle" border="0"></a></div>
                  </td>
                </tr>
                <tr id="trclear"> 
                  <td nowrap width="17%" > 
                    <div align="right">Custodio :</div>
                  </td>
                  <td nowrap width="36%" > 
                    <input type="text" name="CUSTODIAN"  size=4 maxlength="3" 
					>
                    <input type="text" name="DESCR"  size=35 maxlength="35" 
					>
                    <a href="javascript:GetCustodian('CUSTODIAN','DESCR')"><img src="<%=request.getContextPath()%>/images/1b.gif" alt="help" align="absmiddle" border="0"></a> 
                  </td>
                </tr>
                <tr id="trclear"> 
                  <td nowrap width="17%" > 
                    <div align="right">Cliente :</div>
                  </td>
                  <td nowrap width="36%" > 
                    <input type="text" name="CUSTOMER"  size=9 maxlength="9" 
						>
                    <input type="text" name="CUSTDESC"  size=35 maxlength="35" 
						>
                    <a href="javascript:GetCustomerDescId('CUSTOMER','CUSTDESC','')"><img src="<%=request.getContextPath()%>/images/1b.gif" alt="help" align="absbottom" border="0" ></a> 
                  </td>
                </tr>
                <tr id="trclear"> 
                  <td nowrap width="17%" > 
                    <div align="right">Estado :</div>
                  </td>
                  <td nowrap > 
                    <div align="left">
                      <input type=HIDDEN name="STATUS" value="A">
                      <input type="radio" name="CSTATUS" value="" onClick="document.forms[0].STATUS.value=''">
                      Pendiente
<input type="radio" name="CSTATUS" value="P" onClick="document.forms[0].STATUS.value='P'">
                      Pagado 
                      <input type="radio" name="CSTATUS" value="C" onClick="document.forms[0].STATUS.value='C'">
                      Cancelado
<input type="radio" name="CSTATUS" value="A" onClick="document.forms[0].STATUS.value='A'" checked>
                      Todos</div>
                  </td>
                </tr>
                <tr id="trclear">
                  <td nowrap width="17%" >
                    <div align="right">Fecha de Pago :</div>
                  </td>
                  <td nowrap >
                    <input type="text" size="2" maxlength="2" name="DATEF1" 
					  >
                    <input type="text" size="2" maxlength="2" name="DATEF2" onKeyPress="enterInteger()">
                    <input type="text" size="2" maxlength="2" name="DATEF3" onKeyPress="enterInteger()">
                    <a href="javascript:DatePicker(document.forms[0].DATEF1,document.forms[0].DATEF2,document.forms[0].DATEF3)"><img src="<%=request.getContextPath()%>/images/calendar.gif" alt="help" align="absmiddle" border="0"></a> 
                    para 
                    <input type="text" size="2" maxlength="2" name="DATET1" 
					   >
                    <input type="text" size="2" maxlength="2" name="DATET2" onKeyPress="enterInteger()">
                    <input type="text" size="2" maxlength="2" name="DATET3" onKeyPress="enterInteger()">
                    <a href="javascript:DatePicker(document.forms[0].DATET1,document.forms[0].DATET2,document.forms[0].DATET3)"><img src="<%=request.getContextPath()%>/images/calendar.gif" alt="help" align="absmiddle" border="0"></a> 
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
        </table>
        
      </td>
    </tr>
  </table>

      
</form>
</body>
</html>
