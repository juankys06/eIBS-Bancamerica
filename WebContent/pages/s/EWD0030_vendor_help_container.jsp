<html>
<head>
<title>Vendor Number Search</title>

<!-- frames -->
</head>
<SCRIPT language="JavaScript">
	setTimeout("top.close()", <%= datapro.eibs.master.JSEIBSProp.getPopUpTimeOut() %>)
</SCRIPT>
	<%
		 String codeflag = request.getParameter("codeflag"); 
		 if (codeflag == null) codeflag = "";
	%>
<frameset  rows="99,573*" cols="*"> 
  <frame name="query" src="<%=request.getContextPath()%>/pages/s/EWD0030_vendor_help_search.jsp?codeflag=<%=codeflag%>" marginwidth=0 marginheight=0 scrolling="no" frameborder="0" border = "0">
  <frame name="results" src="<%=request.getContextPath()%>/pages/s/EWD0030_vendor_help_blank.jsp" marginwidth="0" marginheight="0" scrolling="auto" frameborder="0" border = "0" noresize>
</frameset>
<noframes></noframes>








</html>



