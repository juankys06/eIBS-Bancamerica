<META HTTP-EQUIV="Pragma" CONTENT="No-cache">
<html> 
<head>
<title>Busqueda de Servicios por Compañia</title>
<link Href="<%=request.getContextPath()%>/pages/style.css" rel="stylesheet">

<jsp:useBean id= "EWD0520Help" class= "datapro.eibs.beans.JBList"   scope="session"/>

<SCRIPT language="JavaScript">
	setTimeout("top.close()", <%= datapro.eibs.master.JSEIBSProp.getPopUpTimeOut() %>)
</SCRIPT>

<script language="javascript">
//<!-- Hide from old browsers
function enter(srv,srn) {
	var form = top.opener.document.forms[0];
 	form[top.opener.fieldSrv].value = srv;
 	form[top.opener.fieldSrn].value = srn;
 	top.close();
}
//-->
</script>

</head>
<body>
<%if ( EWD0520Help.getNoResult() ) {%>
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
			<th align=center  nowrap width=30%>Codigo de Servicio</th>
			<th align=center  nowrap width=70%>Descripcion</th>
		</tr>
		<%
		EWD0520Help.initRow();
		while (EWD0520Help.getNextRow()) {
		    if (EWD0520Help.getFlag().equals("")) {
		    		out.println(EWD0520Help.getRecord());
		    }
		}
	    %> 
	</table>
<%}%>
</body>
</html>			
