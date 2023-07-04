<META HTTP-EQUIV="Pragma" CONTENT="No-cache">
<html>
<head>
<title>Busqueda de Numeros de Plasticos</title>
<link Href="<%=request.getContextPath()%>/pages/style.css" rel="stylesheet">

<jsp:useBean id= "EWD0410Help" class= "datapro.eibs.beans.JBList"   scope="session"/>

<SCRIPT language="JavaScript">
	setTimeout("top.close()", <%= datapro.eibs.master.JSEIBSProp.getPopUpTimeOut() %>)
</SCRIPT>

<script language="javascript">
//<!-- Hide from old browsers
function enter(num) {
	var form = top.opener.document.forms[0];
	form[top.opener.fieldNum].value = num;
// 	form[top.opener.fieldDte].value = dte;
 	top.close();
}
//-->
</script>

</head>
<body>
<%if ( EWD0410Help.getNoResult() ) {%>
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
			<th align=center  nowrap width=10%>Numero de Tarjeta</th>
		</tr>
		<%
		EWD0410Help.initRow();
		while (EWD0410Help.getNextRow()) {
		    if (EWD0410Help.getFlag().equals("")) {
		    		out.println(EWD0410Help.getRecord());
		    }
		}
	    %> 
	</table>
<%}%>
</body>
</html>			

