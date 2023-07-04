
<!DOCTYPE html public "-//W3C//DTD HTML 4.0 Transitional//EN">
<HTML>
<HEAD>
<link Href="<%=request.getContextPath()%>/pages/style.css" rel="stylesheet">
<SCRIPT language="JavaScript">function enter(){
var AppCode = top.top.opener.AppCode;
var Bank = top.top.opener.Bank;
var Selection = top.top.opener.Selection;
var NameSearch = document.forms[0].NameSearch.value
var FromRecord = 0
//	if (NameSearch.length > 0){

    parent.results.window.location.href="<%=request.getContextPath()%>/pages/s/MISC_search_wait.jsp?URL='<%=request.getContextPath()%>/servlet/datapro.eibs.helps.JSEWD0140?NameSearch=" + NameSearch + "@FromRecord=" + FromRecord + "'";

//	}else{
//		alert("A valid number must be enter")
//	}
}
</SCRIPT>
</HEAD>
<BODY>
<FORM Action="javascript:enter()">
	
  <TABLE  id="tbhelp" align="center" width="447" border="0" cellspacing="0" cellpadding="0">
    <TR> 
      <Td align="CENTER"><b>Busqueda de Aceptantes</b></Td>
    </TR>
    <TR>
      <TD align="CENTER">&nbsp;</TD>
    </TR>
    <TR> 
      <TD align="CENTER"> Ingrese el nombre corto : 
        <INPUT type="text" name="NameSearch" size=14>
        &nbsp;&nbsp;<IMG src="<%=request.getContextPath()%>/images/search1.gif" onClick="enter()" width="25" height="21"> 
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
