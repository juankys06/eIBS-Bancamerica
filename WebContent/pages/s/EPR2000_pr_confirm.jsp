<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.0 Transitional//EN">

<html>
<head>
<title>Confirmacion</title>
<META HTTP-EQUIV="Content-Type" CONTENT="text/html; charset=ISO-8859-1">
<META name="GENERATOR" content="IBM WebSphere Page Designer V3.5.2 for Windows">
<META http-equiv="Content-Style-Type" content="text/css">
<link Href="<%=request.getContextPath()%>/pages/style.css" rel="stylesheet">

<SCRIPT SRC="<%=request.getContextPath()%>/pages/s/javascripts/eIBS.jsp"> </SCRIPT>
<jsp:useBean id="prBasic" class="datapro.eibs.beans.EPR200001Message"  scope="session" />
 
</head> 
<body>
<SCRIPT language="javascript">
	opener.window.location.href="<%=request.getContextPath()%>/servlet/datapro.eibs.transfer.JSEPR2000?SCREEN=200&E01PRPDAC=<%= prBasic.getE01PRPDAC()%>";
    close();
 </SCRIPT>
</body>
</html>


