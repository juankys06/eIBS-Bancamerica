<META HTTP-EQUIV="Pragma" CONTENT="No-cache">
<html> 
<head>
<title>Busqueda de Integrantes Junta Directiva</title>
<link Href="<%=request.getContextPath()%>/pages/style.css" rel="stylesheet">

<jsp:useBean id= "EWD0515Help" class= "datapro.eibs.beans.JBList"   scope="session"/>

<SCRIPT language="JavaScript">
	setTimeout("top.close()", <%= datapro.eibs.master.JSEIBSProp.getPopUpTimeOut() %>)
</SCRIPT>

<script language="javascript">
//<!-- Hide from old browsers
function enter(seq,nam,job) {
	var form = top.opener.document.forms[0];
 	form[top.opener.fieldSeq].value = seq;
 	form[top.opener.fieldNam].value = nam;
 	form[top.opener.fieldJob].value = job;
 	top.close();
}
//-->
</script>

</head>
<body>
<%if ( EWD0515Help.getNoResult() ) {%>
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
			<th align=center  nowrap width=10%>Integrante</th>
			<th align=center  nowrap width=45%>Nombre</th>
			<th align=center  nowrap width=45%>Cargo</th>
		</tr>
		<%
		EWD0515Help.initRow();
		while (EWD0515Help.getNextRow()) {
		    if (EWD0515Help.getFlag().equals("")) {
		    		out.println(EWD0515Help.getRecord());
		    }
		}
	    %> 
	</table>
<%}%>
</body>
</html>			
