<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.0 Transitional//EN">
<html>

<head>
<link href="<%=request.getContextPath()%>/pages/style.css" rel="stylesheet">

<script language="JavaScript">

function enterAction(code,desc,id) {
	var form = top.top.opener.document.forms[0];
	
	if(top.top.opener.fieldDesc !== ""){form[top.top.opener.fieldDesc].value = desc;}
	if(top.top.opener.fieldId !== ""){form[top.top.opener.fieldId ].value = id;}
	if(top.top.opener.fieldName !== ""){
		form[top.top.opener.fieldName].value = code;
		form[top.top.opener.fieldName].focus();
		form[top.top.opener.fieldName].select();
	}
	  
	top.close();
}

function enter(){
	var NameSearch = document.forms[0].NameSearch.value;
	var FromRecord = 0
	var CusType = top.top.opener.fieldAux1;
	var searchType = top.top.opener.fieldId;
	var Type = document.forms[0].Type.value;
	
	if (NameSearch.length < 1){
		NameSearch=".";
	}
	
    parent.results.window.location.href="<%=request.getContextPath()%>/pages/s/MISC_search_wait.jsp?URL='<%=request.getContextPath()%>/servlet/datapro.eibs.helps.JSEWD0505?NameSearch="
    + NameSearch + "@Type=" + Type + "@FromRecord=" + FromRecord + "@CusType=" + CusType + "@searchType=" + searchType + "'";
}
</script>
<META name="GENERATOR" content="IBM WebSphere Studio">
</head>

<body>
<FORM Action="javascript:enter()">

<table id="TBHELP" align="center">
<tr><td nowrap>
<table border="0" bordercolor="Black" cellpadding=0 cellspacing=0><tr><td nowrap>
<table  id="TBHELP" align="center" width="140" border="0" cellspacing="0" cellpadding="0">
<tr>
<th nowrap id="THHELP">Tipo de Busqueda</th>
</tr>
<tr>
<td nowrap>
<input type="radio" name="Type" value="S" checked>Nombre Corto</td>
</tr>
</table>
</td></tr>

</table>

</td><td nowrap>
<input type="text" name="NameSearch"  size=14>
        &nbsp;&nbsp;<img src="<%=request.getContextPath()%>/images/search1.gif" onClick="enter()" width="25" height="20"> 
      </td>
    </tr>
</table>
  <hr align="center" width="90%">
</form>
<script language="JavaScript">
  document.forms[0].NameSearch.focus();
</script>

</body>
</html>
