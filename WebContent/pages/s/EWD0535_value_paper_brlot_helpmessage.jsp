<META HTTP-EQUIV="Pragma" CONTENT="No-cache">
<html> 
<head>
<title>Busqueda de Documentos Papel Valor</title>
<link Href="<%=request.getContextPath()%>/pages/style.css" rel="stylesheet">

<jsp:useBean id= "EWD0535Help" class= "datapro.eibs.beans.JBList"   scope="session"/>

<SCRIPT language="JavaScript">
	setTimeout("top.close()", <%= datapro.eibs.master.JSEIBSProp.getPopUpTimeOut() %>)
</SCRIPT>

<script language="javascript">
//<!-- Hide from old browsers
function enter(lot,bal,ini,fin) {
	var form = top.opener.document.forms[0];
 	form[top.opener.fieldLot].value = lot;
 	form[top.opener.fieldBal].value = bal;
 	form[top.opener.fieldIni].value = ini;
 	form[top.opener.fieldFin].value = fin;
 	top.close();
}
//-->
</script>

</head>
<body>
<%if ( EWD0535Help.getNoResult() ) {%>
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
			<th align=center  nowrap width=25%>Lote</th>
			<th align=center  nowrap width=25%>Saldo</th>
			<th align=center  nowrap width=25%>Numero<br>Inicial</th>
			<th align=center  nowrap width=25%>Numero<br>Final</th>
		</tr>
		<%
		EWD0535Help.initRow();
		while (EWD0535Help.getNextRow()) {
		    if (EWD0535Help.getFlag().equals("")) {
		    		out.println(EWD0535Help.getRecord());
		    }
		}
	    %> 
	</table>
<%}%>
</body>
</html>			
