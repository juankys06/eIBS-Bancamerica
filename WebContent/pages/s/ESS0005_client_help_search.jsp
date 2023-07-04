<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.0 Transitional//EN">
<html>

<head>
<META name="GENERATOR" content="IBM WebSphere Page Designer V3.5.2 for Windows">
<META http-equiv="Content-Style-Type" content="text/css">
<link href="<%=request.getContextPath()%>/pages/style.css" rel="stylesheet">

<script language="JavaScript">
function enter(){
var NameSearch = document.forms[0].NameSearch.value;
var FromRecord = 0;
var CusType = top.top.opener.fieldAux9;
	if (NameSearch.length > 0){
		for(var i = 0; i < document.forms[0].Type.length; i++) {
			if (document.forms[0].Type[i].checked){
			var Type = document.forms[0].Type[i].value
			}
 		}

    parent.results.window.location.href="<%=request.getContextPath()%>/pages/s/MISC_search_wait.jsp?URL='<%=request.getContextPath()%>/servlet/com.datapro.eibs.internet.JSESS0005?SCREEN=2@NameSearch=" + NameSearch + "@Type=" + Type + "@FromRecord=" + FromRecord + "@CusType=" + CusType + "'";

	}else{
	alert("Campo no puede ser blanco");
	}
}

</script>
<TITLE></TITLE>
</head>

<body>
<FORM Action="javascript:enter()">

<table id="TBHELP" align="center">
<tr><td nowrap>
<table border="0" bordercolor="Black" cellpadding=0 cellspacing=0><tr><td nowrap>
<table  id="TBHELP" align="center" width="140" border="0" cellspacing="0" cellpadding="0">
<tr>
   <th nowrap id="THHELP">Tipo de Búsqueda</th>
</tr>
<tr> 
	<td nowrap><input type="radio" name="Type" value="S" checked>Nombre Corto</td>
</tr>
<tr>
	<td nowrap><input type="radio" name="Type" value="I">Identificación</td>
</tr>
<tr>
	<td nowrap><input type="radio" name="Type" value="A">Cuenta</td>
</tr>
<tr>
	<td nowrap><input type="radio" name="Type" value="C">Numero de Cliente</td>
</tr>
<tr>
	<td nowrap><input type="radio" name="Type" value="U">Usuario/Entidad</td>
</tr>

</table>
</td></tr>

</table>

</td><td nowrap>
<input type="text" name="NameSearch"  size=25 maxlength="30">
        &nbsp;&nbsp;<img src="<%=request.getContextPath()%>/images/search1.gif" onClick="enter()" width="25" height="21"> 
      </td>
    </tr>
</table>
<hr>

<script language="JavaScript">
  document.forms[0].NameSearch.focus();
</script>

</form>
</body>
</html>
