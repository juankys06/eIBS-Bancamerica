<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.0 Transitional//EN">
<html>
<head>
<title>Image</title>
<META HTTP-EQUIV="Pragma" CONTENT="No-cache">
<META HTTP-EQUIV="Content-Type" CONTENT="text/html; charset=ISO-8859-1">
<META name="GENERATOR" content="IBM WebSphere Page Designer V3.5.2 for Windows">
<META http-equiv="Content-Style-Type" content="text/css">

<script language="Javascript1.1" src="<%=request.getContextPath()%>/pages/s/javascripts/eIBS.jsp"></SCRIPT>
</head>

<body>
<table width="100%" cellspacing="0" cellpadding="0" align="center">
  <tr valign="middle"> 
    <td colspan="2"> 
	<div align="center">
	  <IMG  name="img" src="<%= request.getParameter("PAGE_NAME") %>" width="<%= request.getParameter("IMG_SIZE") %>" border="0" align="middle">
    	</div>
    </td> 
  </tr>
</table>

</body>
</html>
