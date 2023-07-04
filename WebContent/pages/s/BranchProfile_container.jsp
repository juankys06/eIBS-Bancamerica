<html>
<head>
<title>Busqueda de Sucursales</title>

<!-- frames -->
<%
   String bank = "";
   try {
     bank = request.getParameter("DEFBANK");
     if (bank == null) bank="";
   }
   catch (Exception e) {
   }
%>                                                 
</head>

<frameset rows="200,*"> 
  <frame name="query" src="<%=request.getContextPath()%>/servlet/com.datapro.eibs.security.servlet.JSBranchProfile?SCREEN=1&DEFBANK=<%=bank%>" marginwidth=5 marginheight=10 scrolling="no" frameborder="0" border = "0">
  <frame name="results" marginwidth="5" marginheight="5" scrolling="auto" frameborder="0" border = "0" noresize>
</frameset>

<noframes></noframes>


</html>



