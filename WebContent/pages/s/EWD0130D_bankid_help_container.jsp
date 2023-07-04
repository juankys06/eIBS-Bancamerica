<html>
<head>
<title>Busqueda de Bancos</title>

<!-- frames -->
</head>
<SCRIPT language="JavaScript">
	setTimeout("top.close()", <%= datapro.eibs.master.JSEIBSProp.getPopUpTimeOut() %>)
</SCRIPT>
<frameset  rows="90,*" cols="*"> 
  <frame name="query" src="<%=request.getContextPath()%>/pages/s/EWD0130D_bankid_help_search.jsp?<%=request.getParameter("args")%>" marginwidth=0 marginheight=0 scrolling="auto" frameborder="0">
  <frame name="results" src="<%=request.getContextPath()%>/pages/s/EWD0130D_bankid_help_blank.jsp" marginwidth="0" marginheight="0" scrolling="auto" frameborder="0" noresize>
</frameset>
<noframes></noframes>








</html>



