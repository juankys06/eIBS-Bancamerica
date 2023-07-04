<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.0 Transitional//EN">
<HTML>
<HEAD>
<TITLE>PDF Form</TITLE>
<META HTTP-EQUIV="Content-Type" CONTENT="text/html; charset=ISO-8859-1">
<META name="GENERATOR" content="IBM WebSphere Page Designer V3.5.2 for Windows">
<META http-equiv="Content-Style-Type" content="text/css">
<link href="<%=request.getContextPath()%>/pages/style.css" rel="stylesheet">

</HEAD>

<BODY onload=javascript:document.forms[0].submit()>
<h3 align="center">Formulario<img src="<%=request.getContextPath()%>/images/e_ibs.gif" align="left" name="EIBS_GIF" ALT="pdf_form.jsp,EFRM000"></h3>
<hr size="4">
<FORM Method="post" Action="<%=request.getContextPath()%>/servlet/datapro.eibs.reports.JSEFRM000PDF" target="pdf">
  <INPUT TYPE=HIDDEN NAME="SCREEN" VALUE="<%=request.getParameter("SCREEN")%>">
  <INPUT TYPE=HIDDEN NAME="OPE_CODE" VALUE="<%=request.getParameter("OPE_CODE")%>">
<% if ( request.getParameter("APP_CODE") != null ) { %>
  <INPUT TYPE=HIDDEN NAME="APP_CODE" VALUE="<%=request.getParameter("APP_CODE")%>">
<% } %>
<% if ( request.getParameter("ACCOUNT") != null ) { %>
  <INPUT TYPE=HIDDEN NAME="ACCOUNT" VALUE="<%=request.getParameter("ACCOUNT")%>">
<% } %>
</FORM>

</BODY>
</HTML>
