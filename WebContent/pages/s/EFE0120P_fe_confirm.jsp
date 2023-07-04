<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.0 Transitional//EN">
<html>
<head>
<title>Confirmation </title>
<meta http-equiv="Refresh" CONTENT="10;url='<%=request.getContextPath()%>/pages/background.jsp'">
<META HTTP-EQUIV="Content-Type" CONTENT="text/html; charset=ISO-8859-1">
<META name="GENERATOR" content="IBM WebSphere Page Designer V3.5.2 for Windows">
<META http-equiv="Content-Style-Type" content="text/css">

<link href="<%=request.getContextPath()%>/pages/style.css" rel="stylesheet">

<script src="<%=request.getContextPath()%>/pages/s/javascripts/eIBSTreasury.jsp"> </SCRIPT>
<script src="<%=request.getContextPath()%>/pages/s/javascripts/eIBS.jsp"> </SCRIPT>

<jsp:useBean id= "userPO" class= "datapro.eibs.beans.UserPos"  scope="session" />

</head>

<body>
<h3 align="center">Confirmaci&oacute;n<img src="<%=request.getContextPath()%>/images/e_ibs.gif" align="left" name="EIBS_GIF" alt="fe_confirm.jsp,EFE0120P" ></h3>
<hr size="4">

  <table width="100%" height="100%" border="1" >
    <tr > 
      
    <td> 
      <table width="100%" height="100%">
        <tr> 
          <td align=center> La operaci&oacute;n ha sido procesada satisfactoriamente 
            y tiene <b><%= userPO.getIdentifier()%></b> un n&uacute;mero de referencia</td>
        </tr>
      </table>
    </td>
    </tr>
  </table>

</body>
</html>
