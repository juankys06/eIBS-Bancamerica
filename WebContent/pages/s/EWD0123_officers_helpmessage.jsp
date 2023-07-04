<META HTTP-EQUIV="Pragma" CONTENT="No-cache"> 
<html> 
<head>
<title>Busqueda de Ejecutivos</title>
<link Href="<%=request.getContextPath()%>/pages/style.css" rel="stylesheet">

<jsp:useBean id= "EWD0123Help" class= "datapro.eibs.beans.JBList"   scope="session"/>

<SCRIPT language="JavaScript">
	setTimeout("top.close()", <%= datapro.eibs.master.JSEIBSProp.getPopUpTimeOut() %>)
</SCRIPT>

<script language="javascript">
//<!-- Hide from old browsers
function enter(cod) {
	var form = top.opener.document.forms[0];
 	form[top.opener.fieldCod].value = cod;
 	top.close();
}
//-->
</script>

</head>
<body>
<%if ( EWD0123Help.getNoResult() ) {%>
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
			<th align=center  nowrap width=10%>Ejecutivo</th>
			<th align=center  nowrap width=45%>Nombre</th>
		</tr>
		<%
		EWD0123Help.initRow();
		while (EWD0123Help.getNextRow()) {
		    if (EWD0123Help.getFlag().equals("")) {
		    		out.println(EWD0123Help.getRecord());
		    }
		}
	    %> 
	</table>
<%}%>
</body>
</html>			
