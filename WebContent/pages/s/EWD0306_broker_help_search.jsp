
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.0 Transitional//EN">
<HTML>
<HEAD>
<META name="GENERATOR" content="IBM WebSphere Page Designer V3.5.2 for Windows">
<META http-equiv="Content-Style-Type" content="text/css">
<link Href="<%=request.getContextPath()%>/pages/style.css" rel="stylesheet">
<SCRIPT language="JavaScript">
function enter(){

var Type = top.opener.fieldId;
var NameSearch = document.forms[0].NameSearch.value
var FromRecord = 0
//	if (NameSearch.length > 0){

      parent.results.window.location.href="<%=request.getContextPath()%>/pages/s/MISC_search_wait.jsp?URL='<%=request.getContextPath()%>/servlet/datapro.eibs.helps.JSEWD0306?NameSearch=" + NameSearch + "@FromRecord=" + FromRecord + "@shrType=" + Type + "'";    

//	}else{
//	alert("A valid number must be enter")
//	}
}
</SCRIPT>
<TITLE></TITLE>
</HEAD>
<BODY>
<FORM Action="javascript:enter()">
	
  <TABLE  id="tbhelp" align="center" width="447" border="0" cellspacing="0" cellpadding="0">
    <TR> 
      <Td align="CENTER"><b>Agentes de Bolsa Search</b></Td>
    </TR>
    <TR>
      <TD align="CENTER">&nbsp;</TD>
    </TR>
    <TR> 
      <TD align="CENTER"> Enter Short Name : 
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
