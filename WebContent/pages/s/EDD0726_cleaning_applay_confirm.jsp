<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.0 Transitional//EN">

<html>
<head>
<title>Confirmacion</title>
<META HTTP-EQUIV="Content-Type" CONTENT="text/html; charset=ISO-8859-1">
<!--meta http-equiv="Refresh" CONTENT="3;url='javascript:goExit()'"-->
<META name="GENERATOR" content="IBM WebSphere Page Designer V3.5.2 for Windows">
<META http-equiv="Content-Style-Type" content="text/css">
<link Href="<%=request.getContextPath()%>/pages/style.css" rel="stylesheet">

<jsp:useBean id= "applayList" class= "datapro.eibs.beans.JBObjList"  scope="session" />

<SCRIPT language="Javascript1.1" SRC="<%=request.getContextPath()%>/pages/s/javascripts/eIBS.jsp"> </SCRIPT>

<SCRIPT language="javascript">
  function goExit(){
<%
	if ( applayList.getNoResult() ) {
%>  
    window.location.href="<%=request.getContextPath()%>/pages/background.htm";
<%
	} else {
%>    
	window.location.href="<%=request.getContextPath()%>/pages/s/EDD0726_cleaning_applay_list.jsp";
<%
	} 
%>    
  }

 setTimeout("goExit();", 7000);
 </SCRIPT>
</head>

<body>

<h3 align="center">Aplicaci�n de Camara Saliente
<img src="<%=request.getContextPath()%>/images/e_ibs.gif" align="left" name="EIBS_GIF" ALT="cleaning_applay_confirm, EDD0726"></h3> 
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
