<META HTTP-EQUIV="Pragma" CONTENT="No-cache">
<html> 
<head>
<title>Busqueda de Cheque</title>
<link Href="<%=request.getContextPath()%>/pages/style.css" rel="stylesheet">

<jsp:useBean id= "EWD0510Help" class= "datapro.eibs.beans.JBList"   scope="session"/>

<SCRIPT language="JavaScript">
	setTimeout("top.close()", <%= datapro.eibs.master.JSEIBSProp.getPopUpTimeOut() %>)
</SCRIPT>

<script language="javascript">
//<!-- Hide from old browsers
function enter(sub,name,lot,ini,fin,bal,chk) {
	var form = top.opener.document.forms[0];
	form[top.opener.fieldSub].value = sub;
 	form[top.opener.fieldName].value = name;
 	form[top.opener.fieldLot].value = lot;
 	form[top.opener.fieldIni].value = ini;
 	form[top.opener.fieldFin].value = fin;
 	form[top.opener.fieldBal].value = bal;
 	form[top.opener.fieldChk].value = chk;
 	top.close();
}
//-->
</script>

</head>
<body>
<%if ( EWD0510Help.getNoResult() ) {%>
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
			<th align=center  nowrap width=40%>Denominacion</th>
			<th align=center  nowrap width=15%>Numero<br>Inicial</th>
			<th align=center  nowrap width=15%>Numero<br>Final</th>
			<th align=center  nowrap width=30%>Balance</th>
		</tr>
		<%
		EWD0510Help.initRow();
		while (EWD0510Help.getNextRow()) {
		    if (EWD0510Help.getFlag().equals("")) {
		    		out.println(EWD0510Help.getRecord());
		    }
		}
	    %> 
	</table>
<%}%>
</body>
</html>			
