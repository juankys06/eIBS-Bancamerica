<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<link href="<%=request.getContextPath()%>/pages/style.css" rel="stylesheet">

<script language="Javascript1.1" src="<%=request.getContextPath()%>/pages/s/javascripts/eIBS.jsp"> </SCRIPT>

<jsp:useBean id= "error" class= "datapro.eibs.beans.ELEERRMessage"  scope="session"/>

<META name="GENERATOR" content="IBM WebSphere Studio">

<SCRIPT Language="javascript">

function CheckACC(){
if ( (document.forms[0].E01INSAB9.value.length < 1 || document.forms[0].E01INSAB9.value == "0") & (document.forms[0].E01INSSWF.value.length < 1 || document.forms[0].E01INSSWF.value == "0") ) {
  alert("Un número ABA o un Swift ID debe ser ingresado.");
  document.forms[0].E01INSAB9.value='';
  document.forms[0].E01INSAB9.focus();
}
else {
  document.forms[0].submit();
  }
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
<FORM METHOD="post" ACTION="<%=request.getContextPath()%>/servlet/datapro.eibs.transfer.JSEDD6000" >
	<INPUT TYPE=HIDDEN NAME="SCREEN" VALUE="2">


<h3 align="center">Mantenimiento de Números ABA y Swift ID<img src="<%=request.getContextPath()%>/images/e_ibs.gif" align="left" name="EIBS_GIF" ALT="aba_swift_enter.jsp, EDD6000"></h3>
<hr size="4">
  <br><br><br>
  <table class="tableinfo"  cellspacing="0" cellpadding="2" width="100%" border="0">
    <tr id="trdark"> 
      <td valign="middle" align="center" width="49%"> 
        <div align="right">Número ABA : </div>
      </td>
      <td valign="middle" align="center" width="51%"> 
        <div align="left"> 
          <input type="text" name="E01INSAB9"  size="14" maxlength="10" onkeypress="enterInteger()" >
          <a href="javascript:GetFedId('E01INSAB9')"><img src="<%=request.getContextPath()%>/images/1b.gif" alt="Help" align="absmiddle" border="0"></a> 
        </div>
      </td>
    </tr>
    <tr id="trclear"> 
      <td valign="middle" align="center" colspan="2"> 
        <div align="center"><b>o</b></div>
      </td>
    </tr>
    <tr id="trdark"> 
      <td valign="middle" align="center" width="49%"> 
        <div align="right">Swift ID : </div>
      </td>
      <td valign="middle" align="center" width="51%"> 
        <div align="left"> 
          <input type="text" name="E01INSSWF"  size="14" maxlength="12"  >
          <a href="javascript:GetSwiftId('E01INSSWF')"><img src="<%=request.getContextPath()%>/images/1b.gif" alt="Help" align="absmiddle" border="0"></a> 
        </div>
      </td>
    </tr>
    
  </table>
  <br>
  <table width="100%" border="0" cellspacing="0" cellpadding="0" bgcolor="#FFFFFF" bordercolor="#FFFFFF">
          <tr bgcolor="#FFFFFF"> 
            <td width="33%"> 
              <div align="center"> 
                <input id="EIBSBTN" type=button name="Submit" value="Enviar" onClick="CheckACC()">
              </div>
            </td>
          </tr>
          <tr bgcolor="#FFFFFF"> 
            <td> 
              <div align="center"> </div>
            </td>
          </tr>
        </table>
              
</form>
</body>
</html>
