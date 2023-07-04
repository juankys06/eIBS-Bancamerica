<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.0 Transitional//EN">
<html>
<head>
<title>Confirmation </title>
<meta http-equiv="Refresh" CONTENT="10;url='<%=request.getContextPath()%>/pages/background.jsp'">
<META HTTP-EQUIV="Content-Type" CONTENT="text/html; charset=ISO-8859-1">
<META name="GENERATOR" content="IBM WebSphere Page Designer V3.5.2 for Windows">
<META http-equiv="Content-Style-Type" content="text/css">

<link href="<%=request.getContextPath()%>/pages/style.css" rel="stylesheet">

<script language="Javascript1.1" src="<%=request.getContextPath()%>/pages/s/javascripts/eIBS.jsp"> </SCRIPT>

<jsp:useBean id= "userPO" class= "datapro.eibs.beans.UserPos"  scope="session" />


</head>

<body>

 
<h3 align="center">Confirmacion<img src="<%=request.getContextPath()%>/images/e_ibs.gif" align="left" name="EIBS_GIF" ></h3>
<hr size="4">

  <table width="100%" height="100%" border="1" >
    <tr > 
      
    <td> 
      <table width="100%" height="100%">
        <tr> 
          <td align=center> El porcentaje de incidencia ha sido aplicado a la linea finaciera <b><%= userPO.getIdentifier()%></b> </td>
        </tr>
      </table>
    </td>
    </tr>
  </table>

</body>
</html>
