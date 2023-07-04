<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">

<HTML>
<HEAD>
<META HTTP-EQUIV="Content-Type" CONTENT="text/html; charset=ISO-8859-1">
<META name="GENERATOR" content="IBM WebSphere Studio">
<TITLE>Codigos de Tarjeta</TITLE>
<link href="<%=request.getContextPath()%>/pages/style.css" rel="stylesheet">
<script language="javascript">
//<!-- Hide from old browsers
function a(code) {
 top.opener.document.forms[0][top.opener.fieldName].value = code;
 top.close();
 }
//-->
</script>
</HEAD>
<BODY>
<h4>Estado de la Tarjeta</h4> 
<table class="tableinfo" style="width:95%" ALIGN=CENTER>
  <tr id="trdark"> 
    <td width=32 > 
      <div align="left"><b>C&oacute;digo</b></div>
    </td>
    <td width="406"> 
      <div align="left"><b>Descripci&oacute;n</b></div>
    </td>
  </tr>
  <tr> 
    <td width=32 > 
      <div align="center"><b>A</b></div>
    </td>
    <td width="406"><A HREF="javascript:a('A')"> Activa</a></td>
  </tr>
  <tr> 
    <td width=32 > 
      <div align="center"><b>C</b></div>
    </td>
    <td width="406"><A HREF="javascript:a('C')"> Cancelada</a></td>
  </tr>
  <tr> 
    <td width=32 > 
      <div align="center"><b>D</b></div>
    </td>
    <td width="406"><A HREF="javascript:a('D')"> Eliminada</a></td>
  </tr>
  <tr> 
    <td width=32  > 
      <div align="center"><b>F</b></div>
    </td>
    <td width="406"><A HREF="javascript:a('F')"> Fraude</a></td>
  </tr>
  <tr> 
    <td width=32  > 
      <div align="center"><b>I</b></div>
    </td>
    <td width="406"><A HREF="javascript:a('I')"> Emitida</a></td>
  </tr>
  <tr> 
    <td width=32  > 
      <div align="center"><b>H</b></div>
    </td>
    <td width="406"><A HREF="javascript:a('H')"> Orden de Captura</a></td>
  </tr>
  <tr> 
    <td width=32  > 
      <div align="center"><b>O</b></div>
    </td>
    <td width="406"><A HREF="javascript:a('O')"> Ordenada</a></td>
  </tr>
  <tr> 
    <td width=32  > 
      <div align="center"><b>P</b></div>
    </td>
    <td width="406"><A HREF="javascript:a('P')"> Procesada</a></td>
  </tr>
  <tr> 
    <td width=32  > 
      <div align="center"><b>W</b></div>
    </td>
    <td width="406"><A HREF="javascript:a('W')"> Suspendida</a></td>
  </tr>
</table>
<br>
</BODY>
</HTML>
