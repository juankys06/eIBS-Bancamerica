<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.0 Transitional//EN">
<html>
<head>
<title>eIBS Image Viewer</title>
<META HTTP-EQUIV="Pragma" CONTENT="No-cache">
<META HTTP-EQUIV="Content-Type" CONTENT="text/html; charset=ISO-8859-1">
<META name="GENERATOR" content="IBM WebSphere Page Designer V3.5.2 for Windows">
<META http-equiv="Content-Style-Type" content="text/css">
</head>

<frameset rows="110,*" frameborder="NO" border="0" framespacing="0" cols="*"> 
  <frame name="option_viewer" scrolling="NO" noresize src="<%=request.getContextPath()%>/pages/s/EDI0010_doc_viewer_img_options.jsp?DOC_NAME=<%= request.getParameter("DOC_NAME")%>&PAGE_NAME=<%= request.getParameter("PAGE_NAME")%>&IMG_SIZE=<%= request.getParameter("IMG_SIZE")%>" >
  <frame name="main_viewer" src="<%=request.getContextPath()%>/pages/s/EDI0010_doc_viewer_blank.jsp?PAGE_NAME=<%= request.getParameter("PAGE_NAME")%>&IMG_SIZE=<%= request.getParameter("IMG_SIZE")%>">
<NOFRAMES><P>To view this page, you need a browser that supports frames.</P></NOFRAMES>
</frameset>
</html>
