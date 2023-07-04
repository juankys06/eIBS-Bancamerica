<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.0 Transitional//EN">

<html>
<head>
<title>Confirmacion</title>
<META HTTP-EQUIV="Content-Type" CONTENT="text/html; charset=ISO-8859-1">
<!--meta http-equiv="Refresh" CONTENT="3;url='javascript:goExit()'"-->
<META name="GENERATOR" content="IBM WebSphere Page Designer V3.5.2 for Windows">
<META http-equiv="Content-Style-Type" content="text/css">
<link Href="<%=request.getContextPath()%>/pages/style.css" rel="stylesheet">


<SCRIPT language="Javascript1.1" SRC="<%=request.getContextPath()%>/pages/s/javascripts/eIBS.jsp"> </SCRIPT>

<SCRIPT language="javascript">
  function goExit(){
    window.location.href="<%=request.getContextPath()%>/pages/background.jsp";
  }

 setTimeout("document.forms[0].submit();", 7000);
 </SCRIPT>
</head>

<body>

<h3 align="center">Carga de Camara Entrante
<img src="<%=request.getContextPath()%>/images/e_ibs.gif" align="left" name="EIBS_GIF" ALT="cleaning_load_confirm, EDD0931"></h3> 
<hr size="4">
 <form method="post">
  <table class=tbenter height=50%>
    <tr > 
      <td><h4 style="text-align=center">La informacion  ha sido procesada satisfactoriamente.<br>
      Por favor espere... </h4>
      </td>
    </tr>
  </table>
  <div align="center"> 
    <p>&nbsp;</p>
    </div>
</form>
</body>
</html>
