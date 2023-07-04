<html>
<head>
<title>Visor de Imagenes de Transacciones</title>
<META HTTP-EQUIV="Content-Type" CONTENT="text/html; charset=ISO-8859-1">
<META name="GENERATOR" content="IBM WebSphere Page Designer V3.5 for Windows">
<META http-equiv="Content-Style-Type" content="text/css">

<script language="Javascript1.1" src="<%=request.getContextPath()%>/pages/s/javascripts/eIBS.jsp"></SCRIPT>
</head>

<body bgcolor="#FFFFFF">
    <%
        String imgname;
		  try{imgname = request.getParameter("imgName");}catch(Exception e){imgname = "";}
    %>
<table width="100%" cellspacing="0" cellpadding="0" align="center">
  <tr valign="middle"> 
    <td colspan="2"> 
      <div align="center"><IMG  src="http://<%=datapro.eibs.master.JSEIBSProp.getFtpHost()%><%=request.getContextPath()%>/ftp/eibsimage/data/<%=imgname%><%=datapro.eibs.master.JSEIBSProp.getImgExt()%>" border="0" align="middle"> 
      </div>
    
  </tr>
</table>

</body>
</html>
