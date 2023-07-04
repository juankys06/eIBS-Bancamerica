<html>
<head>
<title>Impresion Factura</title>
<%
		String E01TRFACR = request.getParameter("E01TRFACR");
		String E01TRFURN = request.getParameter("E01TRFURN");
		String E02CUSCUN = request.getParameter("E02CUSCUN");
		String E01TRFDT1 = request.getParameter("E01TRFDT1");
		String E01TRFDT2 = request.getParameter("E01TRFDT2");
		String E01TRFDT3 = request.getParameter("E01TRFDT3");
		String E01OPESEL = request.getParameter("E01OPESEL");
		
		String Parm = "&E01TRFACR=" + E01TRFACR +"&E01TRFURN=" + E01TRFURN + "&E02CUSCUN=" +E02CUSCUN +
		              "&E01TRFDT1="+E01TRFDT1+"&E01TRFDT2="+E01TRFDT2+"&E01TRFDT3="+E01TRFDT3 +
		              "&E01OPESEL="+E01OPESEL;
		String URLPrint = request.getContextPath() + "/servlet/datapro.eibs.client.JSEFA0000?SCREEN=400" + Parm;

%>
<!-- frames -->
</head>

<frameset  rows="205,*" cols="*"> 
  <frame name="query" src="<%=request.getContextPath()%>/pages/s/EFA0000_list_head.jsp" marginwidth=0 marginheight=0 scrolling="no" frameborder="0">
  <frame name="results" src="<%= URLPrint %>" marginwidth="0" marginheight="0" scrolling="auto" frameborder="0" noresize>
</frameset>
<noframes>
</noframes>
</html>