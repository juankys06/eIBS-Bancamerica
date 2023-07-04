<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.0 Transitional//EN">
<html>
<head>
<title>Collaterals Assignment</title>
<META HTTP-EQUIV="Content-Type" CONTENT="text/html; charset=ISO-8859-1">
<META name="GENERATOR" content="IBM WebSphere Page Designer V3.5.2 for Windows">
<META http-equiv="Content-Style-Type" content="text/css">

<link Href="<%=request.getContextPath()%>/pages/style.css" rel="stylesheet">

<script language="Javascript1.1" src="<%=request.getContextPath()%>/pages/s/javascripts/eIBS.jsp"> </SCRIPT>
<script language="Javascript1.1" src="<%=request.getContextPath()%>/pages/s/javascripts/optMenu.jsp"> </SCRIPT>

<SCRIPT Language="javascript">

function CheckACC(){
if ( (document.forms[0].ACCOUNT1.value.length < 1)&&(document.forms[0].ACCOUNT2.value.length < 1)&&(document.forms[0].ACCOUNT3.value.length < 1)) {
  alert("Debe teclear una cuenta valida");
  document.forms[0].ACCOUNT1.value='';
  document.forms[0].ACCOUNT1.focus();
  return false;
}
else if (((document.forms[0].ACCOUNT1.value.length > 1)&&(document.forms[0].ACCOUNT2.value.length > 1)&&(document.forms[0].ACCOUNT3.value.length > 1))||
		  ((document.forms[0].ACCOUNT1.value.length > 1)&&(document.forms[0].ACCOUNT2.value.length > 1)&&(document.forms[0].ACCOUNT3.value.length < 1))||
		  ((document.forms[0].ACCOUNT1.value.length > 1)&&(document.forms[0].ACCOUNT2.value.length < 1)&&(document.forms[0].ACCOUNT3.value.length > 1))||
		  ((document.forms[0].ACCOUNT1.value.length < 1)&&(document.forms[0].ACCOUNT2.value.length > 1)&&(document.forms[0].ACCOUNT3.value.length > 1))) {
  alert("Solo una cuenta puede ser asignada");
  document.forms[0].ACCOUNT1.value='';
  document.forms[0].ACCOUNT2.value='';
  document.forms[0].ACCOUNT3.value='';
  document.forms[0].ACCOUNT1.focus();
  return false;
}
else if (((document.forms[0].DEPOSITACC.value.length > 1)&&(document.forms[0].NONEACC.value.length > 1))) {
  alert("Por favor seleccione otra Cuenta");
  document.forms[0].DEPOSITACC.value='';
  document.forms[0].NONEACC.value='';
  document.forms[0].DEPOSITACC.focus();
  return false;
}
else {
  return true;
  }
}

function Blocking(){
 if(document.forms[0].ACCOUNT2.value.length > 1||document.forms[0].ACCOUNT3.value.length > 1){
    alert("Ud tiene otra cuenta selecionada, Por favor verificar");
    document.forms[0].ACCOUNT1.value = "0";
    document.forms[0].AMOUNT.focus();
}
}

</SCRIPT>

</head>


<jsp:useBean id= "error" class= "datapro.eibs.beans.ELEERRMessage"  scope="session" />

<jsp:useBean id= "userPO" class= "datapro.eibs.beans.UserPos"  scope="session" />


<body>

<h3 align="center">Asignacion de Garantias<img src="<%=request.getContextPath()%>/images/e_ibs.gif" align="left" name="EIBS_GIF" ALT="ga_selection,ERA0005"></h3>
<hr size="4">

<form method="post" action="<%=request.getContextPath()%>/servlet/datapro.eibs.client.JSERA0005" onsubmit="return(CheckACC())">
  <p> 
    <INPUT TYPE=HIDDEN NAME="SCREEN" VALUE="200">
  </p>
  <table class="tableinfo">
    <tr > 
      <td nowrap> 
        <table width="100%" cellpadding="0" cellspacing="0">
          <tr id="trdark" > 
            <td> 
              <div align="right">
                <input type="radio" name="OPTION" value="1" >
				<%if(userPO.getHeader8().equals("1")) out.print("checked");%>
                </div>
            </td>
            <td> 
              <p>1. Prestamos, Aceptaciones, Inversiones, Etc 
                <input type="text" name="ACCOUNT1" size="13" maxlength="12" value="<%= userPO.getHeader1().trim()%>">
                <a href="javascript:GetAccount('ACCOUNT1','','AS','')"><img src="<%=request.getContextPath()%>/images/1b.gif" alt="help" align="absbottom" border="0"></a>
               </p>
            </td>
            <td >&nbsp;</td>
          </tr>
          <tr id="trclear"> 
            <td nowrap align="center" > 
              <div align="right"> 
                <input type="radio" name="OPTION" value="2"> <%if(userPO.getHeader8().equals("2")) out.print("checked");%>
              </div>
            </td>
            <td nowrap align="center"> 
              <div align="right"></div>
              <div align="left"> </div>
              <div align="left">2. Cartas de Credito, Cobranzas 
                <input type="text" size="12" maxlength="9" onKeyPress="enterInteger()" name="ACCOUNT2" value="<%= userPO.getHeader2().trim()%>">
                <a href="javascript:GetAccount('ACCOUNT2','','LC','')"><img src="<%=request.getContextPath()%>/images/1b.gif" alt="help" align="absbottom" border="0" ></a> 
              </div>
            </td>
            <td nowrap align="center" >&nbsp;</td>
          </tr>
          <tr id="trdark"> 
            <td nowrap align="center" > 
              <div align="right"> 
                <input type="radio" name="OPTION" value="3"> <%if(userPO.getHeader8().equals("3")) out.print("checked");%>
                </div>
            </td>
            <td nowrap align="center" > 
              <div align="right"></div>
              <div align="left">3. Garantias a Lineas de Credito 
                <input type="text" name="CUSTOMER" size="12" maxlength="9" onKeyPress="enterInteger()" value="<%= userPO.getHeader3().trim()%>">
                <a href="javascript:GetCustomer('CUSTOMER')"><img src="<%=request.getContextPath()%>/images/1b.gif" alt="help" align="absbottom" border="0" ></a> 
                <input type="text" name="ACCOUNT3" size="12" maxlength="9" onKeyPress="enterInteger()" value="<%= userPO.getHeader4().trim()%>">
                <a href="javascript:GetCreditLine('ACCOUNT3',document.forms[0].CUSTOMER.value)"><img src="<%=request.getContextPath()%>/images/1b.gif" alt="help" align="absmiddle" border="0" ></a> 
              </div>
            </td>
            <td nowrap align="center" >&nbsp;</td>
          </tr>
          <tr id="trclear"> 
            <td nowrap align="center" > 
              <div align="right"></div>
            </td>
            <td nowrap align="center" > 
              <div align="right"></div>
              <div align="left"> </div>
              <div align="left"> </div>
            </td>
            <td nowrap align="center" >&nbsp;</td>
          </tr>           
          <tr id="trclear"> 
            <td nowrap align="center">&nbsp;</td>
            <td nowrap align="center" > 
              <div align="left">numero de Garantia<b> Cuenta No Deposito </b>
                <input type="text" name="NONEACC" size="13" maxlength="12" onClick="Blocking()" value="<%= userPO.getHeader6().trim()%>">
                <a href="javascript:GetAccount('NONEACC','','91','')"> <img src="<%=request.getContextPath()%>/images/1b.gif" alt="help" align="absbottom" border="0" ></a></div>
            </td>
            <td nowrap align="center" >&nbsp;</td>
          </tr>
          <tr id="trdark"> 
            <td nowrap align="center" >&nbsp;</td>
            <td nowrap align="center" > 
              <div align="left">o numero de Garantia<b> Con Cuenta Deposito </b> 
                <b> 
                <input type="text" name="DEPOSITACC" size="13" maxlength="12" onClick="Blocking()" value="<%= userPO.getHeader7().trim()%>">
                <a href="javascript:GetAccount('DEPOSITACC','','LI','')"><img src="<%=request.getContextPath()%>/images/1b.gif" alt="help" align="absbottom" border="0" ></a> 
                </b></div>
            </td>
            <td nowrap align="center">&nbsp;</td>
          </tr>
        </table>
      </td>
    </tr>
  </table>
  
  <p align="center">
   <input id="EIBSBTN" type=submit name="Submit" value="Enviar"> 
  </p>
    <script language="JavaScript">
  document.forms[0].ACCOUNT1.focus();
  document.forms[0].ACCOUNT1.select();
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
