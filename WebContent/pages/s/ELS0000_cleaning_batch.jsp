<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.0 Transitional//EN">
 
<html>
<head>
<title>Confirmacion</title>
<META HTTP-EQUIV="Content-Type" CONTENT="text/html; charset=ISO-8859-1">
<!--meta http-equiv="Refresh" CONTENT="4;url='javascript:goExit()'"-->
<META name="GENERATOR" content="IBM WebSphere Page Designer V3.5.2 for Windows">
<META http-equiv="Content-Style-Type" content="text/css">
<link Href="<%=request.getContextPath()%>/pages/style.css" rel="stylesheet">
<jsp:useBean id="batch" class="datapro.eibs.beans.ELS000001Message"  scope="session" />


<SCRIPT SRC="<%=request.getContextPath()%>/pages/s/javascripts/eIBS.jsp"> </SCRIPT>

<SCRIPT language="javascript">
  function goExit(){
    window.location.href="<%=request.getContextPath()%>/pages/background.jsp";
  }

 setTimeout("document.forms[0].submit();", 7000);
 </SCRIPT>
</head>

<body>

<h3 align="center">Procesos Batch
<img src="<%=request.getContextPath()%>/images/e_ibs.gif" align="left" name="EIBS_GIF" ALT="cleaning_confirm, ELS0000"></h3> 
<hr size="4">
 
  <table class=tbenter height=50%>
    <tr > 
      <td align=right width=50%> Programa :
      </td>
      <td> <%= batch.getE01NOMPRG()%>
      </td>
    </tr>
    <tr > 
      <td align=right> Cola :
      </td>
      <td> <%= batch.getE01COLATR()%>
      </td>
    </tr>
    <tr > 
      <td align=right> Trabajo :
      </td>
      <td> <%= batch.getE01NOMTRA()%>
      </td>
    </tr>
    <tr > 
      <td colspan=2><h4 style="text-align=center">La informacion  ha sido procesada satisfactoriamente.<br>
      Por favor espere... </h4>
      </td>
    </tr>
  </table>
  

</body>
</html>
