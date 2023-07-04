<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.0 Transitional//EN">
<html>

<head>
<link Href="<%=request.getContextPath()%>/pages/style.css" rel="stylesheet">
<script language="JavaScript">
function enter(){
var BankNumber = top.top.opener.BankNumber
var Currency = top.top.opener.Currency
var BudgetNum = document.forms[0].BudgetNum.value
var FromRecord = 0
//parent.results.location.href="<%=request.getContextPath()%>/pages/s/MISC_search_wait.jsp?URL='<%=request.getContextPath()%>/pages/s/EWD0011_client_help_helpmessage.jsp?BankNumber=" + BankNumber + "@Currency=" + Currency + "@BudgetNum=" + BudgetNum + "@FromRecord=" + FromRecord + "'";
parent.results.location.href="<%=request.getContextPath()%>/pages/s/MISC_search_wait.jsp?URL='<%=request.getContextPath()%>/servlet/datapro.eibs.helps.JSEWD0011?BankNumber=" + BankNumber + "@Currency=" + Currency + "@BudgetNum=" + BudgetNum + "@FromRecord=" + FromRecord + "'";
}
</script>
</head>

<body>
<FORM Action="javascript:enter()">
  <table id="tbhelp" align="center" width="459" border="0" cellspacing="0" cellpadding="0">
    <tr>
      <td align="center"><b>Busquedas de Cuentas de Presupuesto en el Sistema</b></td>
    </tr>
    <tr> 
      <td align="center">&nbsp;</td>
    </tr>
	<tr> 
	
      <td align="center">N&uacute;mero de Cuenta de Presupuesto&nbsp; 
        <input type="text" name="BudgetNum" size=14>
        &nbsp;&nbsp;<img src="<%=request.getContextPath()%>/images/search1.gif" onClick="enter()" width="25" height="21"> 
      </td>
    </tr>
  </table>
</form>
<script language="JavaScript">
  document.forms[0].NameSearch.focus();
</script>
</body>
</html>
