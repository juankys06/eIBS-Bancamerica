<META HTTP-EQUIV="Pragma" CONTENT="No-cache">
<html>
<head>
<title>Búsqueda de Garantías</title>
<link href="<%=request.getContextPath()%>/pages/style.css" rel="stylesheet">

<!-- frames -->
</head>
<SCRIPT language="JavaScript">
	setTimeout("top.close()", <%= datapro.eibs.master.JSEIBSProp.getPopUpTimeOut() %>)
</SCRIPT>

<FRAMESET rows="125,*" cols="*"> 
   <FRAME name="query" src="<%=request.getContextPath()%>/pages/s/ERA0010_ga_acc_header.jsp" marginwidth="0" marginheight="0" scrolling="auto" frameborder="0">
  <FRAME name="results" src="<%=request.getContextPath()%>/pages/s/ERA0010_ga_acc_list.jsp" marginwidth="0" marginheight="0" scrolling="auto" frameborder="0" noresize>
  <NOFRAMES> 
  <P>To view this page, you need a browser that supports frames.</P>
  </NOFRAMES></FRAMESET>
<noframes></noframes>








</html>



