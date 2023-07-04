<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.0 Transitional//EN">
<html>
<head>
<title>Corporative Portfolio Confirmation </title>
<META HTTP-EQUIV="Content-Type" CONTENT="text/html; charset=ISO-8859-1">

<jsp:useBean id= "userPO" class= "datapro.eibs.beans.UserPos"  scope="session" />

<!-- <meta http-equiv="Refresh" CONTENT="5;url='javascript:var newWindow=window.close()'"> -->
<META name="GENERATOR" content="IBM WebSphere Page Designer V3.5.2 for Windows">
<META http-equiv="Content-Style-Type" content="text/css">
<link href="<%=request.getContextPath()%>/pages/style.css" rel="stylesheet">

<script language="Javascript1.1" src="<%=request.getContextPath()%>/pages/e/javascripts/eIBS.jsp"> </SCRIPT>

<SCRIPT LANGUAGE="javascript">
 setTimeout('finish()',5000);
 function finish(){
 var newWindow=window.close()
 }
 
</SCRIPT>

</head>

<body>

 
<h3 align="center">Confirmación<img src="<%=request.getContextPath()%>/images/e_ibs.gif" align="left" name="EIBS_GIF" ALT="email_confirm, ECIF030" ></h3>
<hr size="4">

  <table class="tableinfo" height="100%">
    <tr > 
      <td> 
        <table width="100%" height="100%">
          <tr> 
            
          <td align=center><b><%= userPO.getHeader3()%></b></td>
          </tr>
        </table>
      </td>
    </tr>
  </table>

</body>
</html>
