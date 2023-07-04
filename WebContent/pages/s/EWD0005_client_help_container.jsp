<html>
<head>
<title>Busqueda de Cuentas</title>

<!-- frames -->
</head>

<SCRIPT language="JavaScript">
	setTimeout("top.close()", <%= datapro.eibs.master.JSEIBSProp.getPopUpTimeOut() %>)
</SCRIPT>

<frameset rows="140,*" border=0> 
  <frame name="query" src="<%=request.getContextPath()%>/pages/s/EWD0005_client_help_search.jsp" marginwidth=5 marginheight=10 scrolling="no" frameborder="0" border = "0">
  <frame name="results" src="<%=request.getContextPath()%>/pages/s/EWD0005_client_help_blank.jsp" marginwidth="0" marginheight="0" scrolling="auto" frameborder="0" border = "0" noresize>
</frameset>

<noframes></noframes>


</html>



