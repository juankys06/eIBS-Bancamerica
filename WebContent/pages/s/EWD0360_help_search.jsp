<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.0 Transitional//EN">
<html>

<head>
<link href="<%=request.getContextPath()%>/pages/style.css" rel="stylesheet">

<script language="JavaScript">

function enterAction(code,desc) {
	var form = top.top.opener.document.forms[0];
	
	if(top.top.opener.fieldDesc != ""){form[top.top.opener.fieldDesc].value = desc;}
	if(top.top.opener.fieldName != ""){
		form[top.top.opener.fieldName].value = code;
		form[top.top.opener.fieldName].focus();
		form[top.top.opener.fieldName].select();
	}	  
	top.close();
}

function enter(){
	var NameSearch = document.forms[0].NameSearch.value;
	var FromRecord = 0;
	var Type = "";
	if(top.top.opener.fieldAux1 != undefined) {
		Type = top.top.opener.fieldAux1;
	}
	
	if (NameSearch.length < 1){
		NameSearch=".";
	}
	var params = "?URL='<%=request.getContextPath()%>/servlet/datapro.eibs.helps.JSEWD0360";
	params = params + "?NameSearch=" + NameSearch + "@Type=" + Type + "@FromRecord=" + FromRecord + "'";
    parent.results.window.location.href="<%=request.getContextPath()%>/pages/s/MISC_search_wait.jsp"+params;
}
</script>
<META name="GENERATOR" content="IBM WebSphere Studio">
</head>

<body>
<FORM Action="javascript:enter()">

<table id="TBHELP" align="center">
<tr><td nowrap>
	Nombre :
	</td>
	<td nowrap>
		<input type="text" name="NameSearch"  size=14>
		<img src="<%=request.getContextPath()%>/images/search1.gif" onClick="enter()" width="25" height="20"> 
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
