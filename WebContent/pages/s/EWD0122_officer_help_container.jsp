<html> 
<head>
<title>Officer Number Search</title>

<!-- frames -->
</head>
<SCRIPT language="JavaScript">
	setTimeout("top.close()", <%= datapro.eibs.master.JSEIBSProp.getPopUpTimeOut() %>)
</SCRIPT>
<frameset  rows="99,573*" cols="*"> 
  <frame name="query" src="<%=request.getContextPath()%>/pages/s/EWD0122_officer_help_search.jsp?Branch=<%=request.getParameter("Branch")%>" marginwidth=0 marginheight=0 scrolling="no" frameborder="0" border = "0">
  <frame name="results" src="<%=request.getContextPath()%>/pages/s/EWD0122_officer_help_blank.jsp" marginwidth="0" marginheight="0" scrolling="auto" frameborder="0" border = "0" noresize>
</frameset>
<noframes></noframes>








</html>



