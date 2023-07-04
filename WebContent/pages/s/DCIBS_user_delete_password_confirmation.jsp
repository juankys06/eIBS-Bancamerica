<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.0 Transitional//EN">

<%@ page import = "datapro.eibs.master.Util" %>
<html>
<head>
<title>Confirmacion</title>
<META HTTP-EQUIV="Content-Type" CONTENT="text/html; charset=ISO-8859-1">
<meta http-equiv="Refresh" CONTENT="7;url='<%=request.getContextPath()%>/pages/background.jsp'">
<META name="GENERATOR" content="IBM WebSphere Page Designer V3.5.2 for Windows">
<META http-equiv="Content-Style-Type" content="text/css">
<link Href="<%=request.getContextPath()%>/pages/style.css" rel="stylesheet">

<jsp:useBean id= "error" class= "datapro.eibs.beans.ELEERRMessage"  scope="session" />
<jsp:useBean id= "userPO" class= "datapro.eibs.beans.UserPos"  scope="session" />

<script language="Javascript1.1" src="<%=request.getContextPath()%>/pages/s/javascripts/eIBS.jsp"> </SCRIPT>

</head>

<body>


<h3 align="center">Confirmación Mantenimiento Clave de Operaciones</h3>
<img src="<%=request.getContextPath()%>/images/e_ibs.gif" align="left" name="EIBS_GIF" ALT="user_delete_password_confirmation, DCIBS"> 
<hr size="4">
 
 <% if (error.getERRNUM().equals("0")) { %> 
  
 <div align=center>
   <h4>
   La clave de operaciones ha sido eliminada.
   </h4>
 </div>

<% } else { %>
      
 <div align=center>
   <h4>
   La contraseña no ha sido eliminada
   </h4>
 </div>
 

<% } %>
      
</form>
</body>
</html>
