<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<jsp:useBean id= "userPO" class= "datapro.eibs.beans.UserPos"  scope="session" />
<META HTTP-EQUIV="Content-Type" CONTENT="text/html; charset=ISO-8859-1">
<meta http-equiv="Refresh" CONTENT="5;url='<%=request.getContextPath()%>/servlet/datapro.eibs.products.JSEXEDL0150?SCREEN=400&E01DEAACC=<%= userPO.getIdentifier()%>'">
<META name="GENERATOR" content="IBM WebSphere Studio">
<head>
<title>Confirmacion </title>
<link href="<%=request.getContextPath()%>/pages/style.css" rel="stylesheet">

<script language="Javascript1.1" src="<%=request.getContextPath()%>/pages/s/javascripts/eIBS.jsp"> </SCRIPT>




</head>

<body>

 
<h3 align="center"><img src="<%=request.getContextPath()%>/images/e_ibs.gif" align="left" name="EIBS_GIF" ALT="client_corp_confirm, ESD0080" ></h3>
<hr size="4">

  <table width="100%" height="100%" border="1" bordercolor="#000000">
    <tr > 
      <td> 
        <table width="100%" height="100%">
          <tr> 
            
          <td align=center> El prestamo n&uacute;mero <b><%= userPO.getIdentifier()%></b> 
            ha sido satisfactoriamente emitido. </td>
          </tr>
        </table>
      </td>
    </tr>
  </table>
<%  userPO.setPurpose("MAINTENANCE");%>
</body>
</html>
