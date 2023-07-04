<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.0 Transitional//EN">
<html>
<head>
<title>Ordenes de Pago</title>
<META HTTP-EQUIV="Content-Type" CONTENT="text/html; charset=ISO-8859-1">
<META name="GENERATOR" content="IBM WebSphere Page Designer V3.5.2 for Windows">
<META http-equiv="Content-Style-Type" content="text/css">
<link Href="<%=request.getContextPath()%>/pages/style.css" rel="stylesheet">

<jsp:useBean id="prBasic" class="datapro.eibs.beans.EDD067001Message"  scope="session" />
<jsp:useBean id= "error" class= "datapro.eibs.beans.ELEERRMessage"  scope="session" />

<script language="Javascript1.1" src="<%=request.getContextPath()%>/pages/s/javascripts/eIBS.jsp"> </SCRIPT>
<SCRIPT Language="javascript">

function CheckACC(){
//if ( document.forms[0].E01ACMACC.value.length < 1) {
//  alert("A valid account number must be entered");
//  document.forms[0].E01ACMACC.value='';
//  document.forms[0].E01ACMACC.focus();
//}
//else {
  document.forms[0].submit();
//}
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

<h3 align="center">Borrar Ordenes de Pago </h3>
<hr size="4">


<form method="post" action="<%=request.getContextPath()%>/servlet/datapro.eibs.transfer.JSEDD0670">

    <INPUT TYPE=HIDDEN NAME="SCREEN" VALUE="600">
	<INPUT TYPE=HIDDEN NAME="E01POSPR1" VALUE="<%= prBasic.getE01POSPR1()%>">
    <INPUT TYPE=HIDDEN NAME="E01POSPR2" VALUE="<%= prBasic.getE01POSPR2()%>">
	<INPUT TYPE=HIDDEN NAME="E01POSCHB" VALUE="<%= prBasic.getE01POSCHB()%>">
    <INPUT TYPE=HIDDEN NAME="E01POSCHO" VALUE="<%= prBasic.getE01POSCHO()%>">

  <p>Nota : Esta opci&oacute;n borrar&aacute; la Orden de Pago seleccionada</p>
  <h4>Informaci&oacute;n B&aacute;sica</h4>
  
     
  <table class="tableinfo">
    <tr id="trdark"> 
      <td nowrap align="center" width="40%"> 
        <div align="right">C&oacute;digo de Banco :</div>
      </td>
      <td nowrap align="center" width="60%"> 
        <div align="left"> 
          <input type="text" name="E01BKNNUM" size="3" maxlength="2" onKeyPress="enterInteger()" value="<%= prBasic.getE01BKNNUM()%>">
        </div>
      </td>
    </tr>
    <tr id="trclear"> 
      <td nowrap align="center" width="40%"> 
        <div align="right">Moneda :</div>
      </td>
      <td nowrap align="center" width="60%"> 
        <div align="left"> 
          <input type="text" name="E01CCY" size="4" maxlength="3" value="<%= prBasic.getE01CCY()%>">
          <a href="javascript:GetCurrency('E01CCY','')"> <img src="<%=request.getContextPath()%>/images/1b.gif" alt="help" align="absmiddle" border="0" width="18" height="22"> 
          </a> </div>
      </td>
    </tr>
    <tr id="trdark"> 
      <td nowrap align="center" width="40%"> 
        <div align="right">N&uacute;mero de Cliente :</div>
      </td>
      <td nowrap align="center" width="60%"> 
        <div align="left"> 
          <input type="text" name="E01CUSNUM" size="12" maxlength="9" onKeyPress="enterInteger()" value="<%= prBasic.getE01CUSNUM()%>">
          <a href="javascript:GetCustomerDescId('E01CUSNUM','','')"><img src="<%=request.getContextPath()%>/images/1b.gif" alt=". . ." align="absmiddle" border="0" width="18" height="22" ></a></div>
      </td>
    </tr>
    <tr id="trclear"> 
      <td nowrap align="center" width="40%"> 
        <div align="right">N&uacute;mero de Cuenta :</div>
      </td>
      <td nowrap align="center" width="60%"> 
        <div align="left"> 
          <input type="text" name="E01ACCNUM" size="12" maxlength="12" onKeyPress="enterInteger()" value="<%= prBasic.getE01ACCNUM()%>">
          <a href="javascript:GetAccount('E01ACMNUM','','','')"><img src="<%=request.getContextPath()%>/images/1b.gif" alt="help" align="absbottom" border="0" ></a></div>
      </td>
    </tr>
    <tr id="trdark"> 
      <td nowrap align="center" width="40%"> 
        <div align="right">Tipo de Operaci&oacute;n :</div>
      </td>
      <td nowrap align="center" width="60%"> 
        <div align="left"> 
          <p> 
            <input type="text" name="E01OPTYPE" size="3" maxlength="1" onKeyPress="enterInteger()" value="<%= prBasic.getE01OPTYPE()%>">
            <a href="javascript:GetCode('E01OPTYPE','STATIC_pr_operation_types.jsp')"><img src="<%=request.getContextPath()%>/images/1b.gif" alt="help" align="absbottom" border="0" ></a> 
        </div>
      </td>
    </tr>
    <tr id="trclear"> 
      <td nowrap align="center" width="40%"> 
        <div align="right">Tipo de Pago :</div>
      </td>
      <td nowrap align="center" width="60%"> 
        <div align="left"> 
          <select name="E01PODTYP">
            <option value="2" <% if(prBasic.getE01PODTYP().equals("2")) out.print("selected");%>>Formato Swift 
           MT-100 </option>
            <option value="3" <% if(prBasic.getE01PODTYP().equals("3")) out.print("selected");%>>Formato Swift 
            MT-200</option>
            <option value="4" <% if(prBasic.getE01PODTYP().equals("4")) out.print("selected");%>>Formato Swift 
           MT-202</option>
            <option value="5" <% if(prBasic.getE01PODTYP().equals("5")) out.print("selected");%>>Telex</option>
          </select>
        </div>
      </td>
    </tr>
    <tr id="trdark"> 
      <td nowrap align="center" width="40%"> 
        <div align="right">Tipo de Comisi&oacute;n :</div>
      </td>
      <td nowrap align="center" width="60%"> 
        <div align="left"> 
          <input type="text" name="E01FEETYP" size="3" maxlength="2" onKeyPress="enterInteger()" value="<%= prBasic.getE01FEETYP()%>">
        </div>
      </td>
    </tr>
    <tr id="trclear"> 
      <td nowrap align="center" colspan="2"> 
        <div align="left"><b>Ordenes de Pago Simple</b></div>
      </td>
    </tr>
    <tr id="trdark"> 
      <td nowrap align="center" width="40%"> 
        <div align="right">N&uacute;mero de Referencia :</div>
      </td>
      <td nowrap align="center" width="60%"> 
        <div align="left"> 
          <input type="text" name="E01REFNUM" size="6" maxlength="4" onKeyPress="enterInteger()" value="<%= prBasic.getE01REFNUM()%>">
        </div>
      </td>
    </tr>
    <tr> 
      <td nowrap align="center" colspan="2"> 
        <p align="center">&nbsp; </p>
      </td>
    </tr>
  </table>
  <p align="center"> 
    <input id="EIBSBTN" type=submit name="Submit" value="Enviar">
  </p>
</form>
</body>
</html>
