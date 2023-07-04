<META HTTP-EQUIV="Pragma" CONTENT="No-cache">
<html>
<head>
<title>Busqueda de Moneda por Instrumento</title>
<link Href="<%=request.getContextPath()%>/pages/style.css" rel="stylesheet">

<jsp:useBean id= "EWD0500Help" class= "datapro.eibs.beans.JBList"   scope="session"/>

<SCRIPT language="JavaScript">
	setTimeout("top.close()", <%= datapro.eibs.master.JSEIBSProp.getPopUpTimeOut() %>)
</SCRIPT>

<script language="javascript">
//<!-- Hide from old browsers
function enter(code) {
	var form = top.opener.document.forms[0];
 	form[top.opener.fieldName].value = code;
 	form[top.opener.fieldName].focus();
 	form[top.opener.fieldName].select();
 	top.close();
}
//-->
</script>

</head>
<body>
<%if ( EWD0500Help.getNoResult() ) {%>
	<table class="tbenter" width=100% height=100%>
		<tr>
			<td> 
				<div align="center"> <font size="3"><b>No hay resultados para su criterio de busqueda </b></font></div>
	      	</td>
		</tr>
	</table>
<%}	else {%>	
	<table class="tableinfo" style="width:95%" ALIGN=CENTER>
		<tr id="trdark">
			<th align=center  nowrap width=10%>Moneda</th>
			<th align=center  nowrap width=15%>Tasa de Cambio <BR>
		Compra</th>
			<th align=center  nowrap width=15%>Tasa de Cambio <BR>
		Venta</th>
			<th align=center  nowrap width=70%>Descripcion</th>
		</tr>
		<%
		EWD0500Help.initRow();
		while (EWD0500Help.getNextRow()) {
		    if (EWD0500Help.getFlag().equals("")) {
		    		out.println(EWD0500Help.getRecord());
		    }
		}
	    %> 
	</table>
<%}%>
</body>
</html>			
