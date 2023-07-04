
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.0 Transitional//EN">
<HTML>
<HEAD>
<META name="GENERATOR" content="IBM WebSphere Page Designer V3.5.2 for Windows">
<META http-equiv="Content-Style-Type" content="text/css">
<link Href="<%=request.getContextPath()%>/pages/style.css" rel="stylesheet">
<SCRIPT language="JavaScript">
function enter(){
 var PmtVia = top.opener.PVia;
 var NameSearch = document.forms[0].NameSearch.value
 
	if (NameSearch.length > 0){

    parent.results.window.location.href="<%=request.getContextPath()%>/pages/s/MISC_search_wait.jsp?URL='<%=request.getContextPath()%>/servlet/datapro.eibs.helps.JSEWD0130?NameSearch=" + NameSearch + "@PmtVia=" + PmtVia + "'";    

	}else{
	alert("A valid name must be enter")
	}
}
</SCRIPT>
<TITLE></TITLE>
</HEAD>
<BODY>
<FORM Action="javascript:enter()">
<br>	
  <TABLE  id="tbhelp" align="center"  border="0" cellspacing="0" cellpadding="3">
    <TR> 
      <Td align="CENTER"><p><b>Busqueda de Bancos</b></p></Td>
    </TR>
    <TR> 
      <TD align="CENTER"> Ingrese el Nombre Corto : 
        <INPUT type="text" name="NameSearch" size=14>
        &nbsp;&nbsp;<img src="<%=request.getContextPath()%>/images/search1.gif" onClick="enter()"> 
      </TD>
    </TR>
  </TABLE>

<script language="JavaScript">
  document.forms[0].NameSearch.focus();
</script>

</FORM>
</BODY>
</HTML>
