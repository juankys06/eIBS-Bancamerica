<html>
<head>
<title>Old Account Search</title>

<!-- frames -->
</head>
<SCRIPT language="JavaScript">
	// setTimeout("top.close()", <%= datapro.eibs.master.JSEIBSProp.getPopUpTimeOut() %>)
</SCRIPT>
<frameset  rows="250,600*" cols="*"> 
  <frame name="query" src="<%=request.getContextPath()%>/pages/s/EWD0406_old_acc_help_search.jsp" marginwidth=0 marginheight=0 scrolling="auto" frameborder="0">
  <frame name="results" src="<%=request.getContextPath()%>/pages/s/EWD0406_old_acc_help_blank.jsp" marginwidth="0" marginheight="0" scrolling="auto" frameborder="0" noresize>
</frameset>
<noframes></noframes>
</html>



