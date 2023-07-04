<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.0 Transitional//EN">
<html>
<head>
<title>eIBS Viewer</title>
<META HTTP-EQUIV="Content-Type" CONTENT="text/html; charset=ISO-8859-1">
<META name="GENERATOR" content="IBM WebSphere Page Designer V3.5.2 for Windows">
<META http-equiv="Content-Style-Type" content="text/css">
</head>

<frameset cols="200,*" frameborder="NO" border="0" framespacing="0" rows="*"> 
	<FRAME name="browser_viewer" src="<%=request.getContextPath()%>/pages/s/EDI0010_doc_viewer_browser.jsp?ROW=0&DOC_NAME=<%=request.getParameter("DOC_NAME")%>">
 	<FRAME name="blank_viewer" scrolling="AUTO" noresize src="<%=request.getContextPath()%>/pages/s/EDI0010_doc_viewer_blank.jsp">
	<NOFRAMES><P>To view this page, you need a browser that supports frames.</P></NOFRAMES>
</frameset>
</html>
