
<!DOCTYPE html public "-//W3C//DTD HTML 4.0 Transitional//EN">
<HTML> 
<HEAD>
<link Href="<%=request.getContextPath()%>/pages/style.css" rel="stylesheet">
<SCRIPT language="JavaScript">
function enter(){
var NameSearch = document.forms[0].NameSearch.value
var FromRecord = 0
//	if (NameSearch.length > 0){

//	parent.results.window.location.href="<%=request.getContextPath()%>/pages/s/MISC_search_wait.jsp?URL='<%=request.getContextPath()%>/pages/s/EWD0030_vendor_help_helpmessage.jsp?NameSearch=" + NameSearch + "@FromRecord=" + FromRecord + "'";
	parent.results.window.location.href="<%=request.getContextPath()%>/pages/s/MISC_search_wait.jsp?URL='<%=request.getContextPath()%>/servlet/datapro.eibs.helps.JSEWD0122?NameSearch=" + NameSearch + "@FromRecord=" + FromRecord + "@Branch=" + <%=request.getParameter("Branch")%> + "'";


//	}else{
//	alert("Debe ingresar nombre ")
//	}
}
</SCRIPT>
</HEAD>
<BODY>
<FORM Action="javascript:enter()">
	
  <TABLE  id="tbhelp" align="center" width="447" border="0" cellspacing="0" cellpadding="0">
    <TR> 
      <Td align="CENTER">
        <p><b>B&uacute;squeda de Ejecutivo</b></p>
      </Td>
    </TR>
    <TR>
      <TD align="CENTER">&nbsp;</TD>
    </TR>
    <TR> 
      <TD align="CENTER"> Escriba el Nombre Corto: 
        <INPUT type="text" name="NameSearch" size=14>
        &nbsp;&nbsp;<img src="<%=request.getContextPath()%>/images/search1.gif" onClick="enter()"> 
      </TD>
    </TR>
  </TABLE>
  <br>

<script language="JavaScript">
  document.forms[0].NameSearch.focus();
</script>

</FORM>
</BODY>
</HTML>
