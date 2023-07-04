<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.0 Transitional//EN">
<html>

<head>
<link Href="<%=request.getContextPath()%>/pages/style.css" rel="stylesheet">
<script language="Javascript1.1" src="<%=request.getContextPath()%>/pages/s/javascripts/eIBS.jsp"> </SCRIPT>
<style>
TH {
  background: #FFFFFF;
}
</style>
<script language="JavaScript">
function enter(){
var AppCode = top.top.opener.AppCode
var ProductBank = top.top.opener.ProductBank
var srhProduct = document.forms[0].srhProduct.value
//parent.results.window.location.href="file:///<%=request.getContextPath()%>/pages/s/MISC_search_wait.jsp?URL='<%=request.getContextPath()%>/pages/s/EWD0008_client_help_helpmessage.jsp?AppCode=" + AppCode + "@srhProduct=" + srhProduct + "@ProductBank=" + ProductBank + "'";
parent.results.window.location.href="<%=request.getContextPath()%>/pages/s/MISC_search_wait.jsp?URL='<%=request.getContextPath()%>/servlet/datapro.eibs.helps.JSEWD0008B?AppCode=" + AppCode +"@ProductBank=" + ProductBank + "@srhProduct=" + srhProduct +  "'";
}

</script>
</head>

<body>
<FORM Action="javascript:enter()">

  <table id="tbhelp" align="center" width="556" border="0" cellspacing="0" cellpadding="0">
    <tr> 
      <td align="center"><b>B&uacute;squeda de Productos</b></td>
    </tr>
    <tr>
      <td align="center">&nbsp;</td>
    </tr>
    <tr> 
      <td align="center">Por favor seleccione el tipo de producto:&nbsp; 
        <input type="text" name="srhProduct" size="5" maxlength="4" >
        <input type="HIDDEN" name="srhProduct1" size="5" maxlength="4" >    
        <a href="javascript:GetCodeDescCNOFC('srhProduct','srhProduct1','04')"><img src="<%=request.getContextPath()%>/images/1b.gif" alt=". . ." align="absbottom" border="0" width="18" height="22"></a>&nbsp;&nbsp;<img src="<%=request.getContextPath()%>/images/search1.gif" onClick="enter()" width="25" height="21"></td>
    </tr>
  </table>
  
<script language="JavaScript">
  document.forms[0].srhProduct.focus();
</script>
  
</form>
</body>
</html>
