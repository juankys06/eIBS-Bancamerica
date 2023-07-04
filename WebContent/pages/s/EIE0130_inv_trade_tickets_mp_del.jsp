<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.0 Transitional//EN">
<html>
<head>
<title>Commissions Table</title>
<META HTTP-EQUIV="Content-Type" CONTENT="text/html; charset=ISO-8859-1">

<jsp:useBean id= "userPO" class= "datapro.eibs.beans.UserPos"  scope="session" />

<meta http-equiv="Refresh" CONTENT="5;url='<%=request.getContextPath()%>/servlet/datapro.eibs.invest.JSEWD0309?SCREEN=500'">
<META name="GENERATOR" content="IBM WebSphere Page Designer V3.5.2 for Windows">
<META http-equiv="Content-Style-Type" content="text/css">
<link href="<%=request.getContextPath()%>/pages/style.css" rel="stylesheet">

<script language="Javascript1.1" src="<%=request.getContextPath()%>/pages/s/javascripts/eIBS.jsp"> </SCRIPT>

</head>

<body>

 
<h3 align="center">Confirmaci&oacute;n de Borrado<img src="<%=request.getContextPath()%>/images/e_ibs.gif" align="left" name="EIBS_GIF" ALT="inv_trade_tickets_mp_del, EIE0130" ></h3>
<hr size="4">

  <table class="tableinfo" height="100%">
    <tr > 
      <td> 
        <table width="100%" height="100%">
          <tr> 
            
          <td align=center> La orden n&uacute;mero <b><%= userPO.getIdentifier()%></b> 
            ha sido borrada satisfactoriamente.</td>
          </tr>
        </table>
      </td>
    </tr>
  </table>

</body>
</html>
