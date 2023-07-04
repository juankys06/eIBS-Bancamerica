<META HTTP-EQUIV="Pragma" CONTENT="No-cache">
<html> 
<head>
<title>Busqueda Secuencias (Lotes) por Compañia</title>
<link Href="<%=request.getContextPath()%>/pages/style.css" rel="stylesheet">

<jsp:useBean id= "EWD0525Help" class= "datapro.eibs.beans.JBList"   scope="session"/>

<SCRIPT language="JavaScript">
	setTimeout("top.close()", <%= datapro.eibs.master.JSEIBSProp.getPopUpTimeOut() %>)
</SCRIPT>

<script language="javascript">
//<!-- Hide from old browsers
function enter(seq) {
	var form = top.opener.document.forms[0];
 	form[top.opener.fieldSeq].value = seq;
 	top.close();
}
//-->
</script>

</head>
<body>
<%if ( EWD0525Help.getNoResult() ) {%>
	<table class="tbenter" width=100% height=100%>
		<tr>
			<td> 
				<div align="center"> <font size="3"><b>No hay resultados para su criterio de busqueda </b></font></div>
	      	</td>
		</tr>
	</table>
<%}	else {%>	
	<table class="tableinfo" style="width:60%" ALIGN=CENTER>
		<tr id="trdark">
			<th align=center  nowrap width=100%>Secuencia (Lote) de Servicio</th>
		</tr>
		<%
		EWD0525Help.initRow();
		while (EWD0525Help.getNextRow()) {
		    if (EWD0525Help.getFlag().equals("")) {
		    		out.println(EWD0525Help.getRecord());
		    }
		}
	    %> 
	</table>
<%}%>
</body>
</html>			
