
<!DOCTYPE html public "-//W3C//DTD HTML 4.0 Transitional//EN">
<HTML>
<HEAD>
<link Href="<%=request.getContextPath()%>/pages/style.css" rel="stylesheet">
<SCRIPT language="JavaScript">
function enter(){
var AppCode = top.top.opener.AppCode;
var Bank = top.top.opener.Bank;
var Selection = top.top.opener.Selection;
var SelNew = document.forms[0].SelNew.value
var FromRecord = 0
var sType="";
	if (SelNew.length > 0){
	try{
	  sType = top.opener.fieldAux1;
	  if (sType == null) sType="";
	}
	catch(e){
	  sType="";
	}
    parent.results.window.location.href="<%=request.getContextPath()%>/pages/s/MISC_search_wait.jsp?URL='<%=request.getContextPath()%>/servlet/datapro.eibs.helps.JSEWD0170?SelNew=" + SelNew + "@SelOld=<%=request.getAttribute("SelOld")%>@FromRecord=" + FromRecord + "@codeflag=" + sType + "'";
	}else{
		alert("Un carácter válido debe ser tecleado")
	}
}
</SCRIPT>
</HEAD>
<BODY>
<FORM Action="javascript:enter()">
	
  <TABLE  id="tbhelp" align="center" width="447" border="0" cellspacing="0" cellpadding="0">
    <TR> 
      <Td align="CENTER"><b>Busqueda de Agentes Cobradores y Plazas de Pago</b></Td>
    </TR>
    <TR>
      <TD align="CENTER">&nbsp;</TD>
    </TR>
    <TR> 
      <TD align="CENTER"> Ingrese el nombre corto : 
        <INPUT type="text" name="SelNew" size=14>
        &nbsp;&nbsp;<IMG src="<%=request.getContextPath()%>/images/search1.gif" onClick="enter()" width="25" height="21"> 
      </TD>
    </TR>
  </TABLE>
  <br>

<script language="JavaScript">
  document.forms[0].SelNew.focus();
</script>

</FORM>
</BODY>
</HTML>
