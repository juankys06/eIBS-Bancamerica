<html>
<head>
<title>Busqueda de Privilegios</title>

<!-- frames -->
                                                
</head>

<frameset rows="160,*"> 
  <frame name="query" src="<%=request.getContextPath()%>/pages/s/PrivilProfile_search.jsp" marginwidth=5 marginheight=10 scrolling="no" frameborder="0" border = "0">
  <frame name="results" src="<%=request.getContextPath()%>/servlet/com.datapro.eibs.security.servlet.JSPrivilegeProfile?SCREEN=1" marginwidth="5" marginheight="5" scrolling="auto" frameborder="0" border = "0" noresize>
</frameset>

<noframes></noframes>


</html>



