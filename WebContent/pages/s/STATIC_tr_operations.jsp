<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.0 Transitional//EN">

<HTML>
<HEAD>
<META HTTP-EQUIV="Content-Type" CONTENT="text/html; charset=ISO-8859-1">
<META name="GENERATOR" content="IBM WebSphere Page Designer V3.5.2 for Windows">
<META http-equiv="Content-Style-Type" content="text/css">
<TITLE>Código de Operación Bancaria</TITLE>
<link href="<%=request.getContextPath()%>/pages/style.css" rel="stylesheet">
<script language="javascript">
//<!-- Hide from old browsers
function a(code) {
var formLength= top.opener.document.forms[0].elements.length;
for(n=0;n<formLength;n++)
{
	var elementName= top.opener.document.forms[0].elements[n].name;
	if(elementName == top.opener.fieldName) 
	{
  		top.opener.document.forms[0].elements[n].value = code;
  		break;
	}
 }
 top.close();
 }
//-->
</script>
</HEAD>
<BODY>
<h4>Códigos de Operación de Banco</h4><table align=center class=tableinfo style="width:95%">
  <TR id=trdark> 
    <TH align=left  > 
      <div align="left">Code</div>
    </TH>
    <TH align=left  > 
      <div align="left">Descripción</div>
    </TH>
  </TR>
  <tr> 
    <td width=30 > 
      <div align="center"><b>CRED</b></div>
    </td>
    <td ><a href="javascript:a('CRED')">Este mensaje contiene una transferencia de crédito donde no hay Nivel de Servicio SWIFT involucrado.</a></td>
  </tr>
  <tr> 
    <td width=30 > 
      <div align="center"><b>CRTS</b></div>
    </td>
    <td ><a href="javascript:a('CRTS')">Este mensaje contiene una transferencia de crédito con propósitos de prueba</a></td>
  </tr>
  <tr> 
    <td width=30 > 
      <div align="center"><b>SPAY</b></div>
    </td>
    <td ><a href="javascript:a('SPAY')">Este mensaje contiene una transferencia de crédito para ser procesada de acuerdo al Nivel de Servicio SWIFTPay</a></td>
  </tr>
  <tr> 
    <td width=30 > 
      <div align="center"><b>SPRI</b></div>
    </td>
    <td ><A HREF="javascript:a('SPRI')">Este mensaje contiene una transferencia de crédito para ser procesada de acuerdo al Nivel de Servicio Prioritario</a></td>
  </tr>
  <tr> 
    <td width=30 > 
      <div align="center"><b>SSTD</b></div>
    </td>
    <td ><A HREF="javascript:a('SSTD')">Este mensaje contiene una transferencia de crédito para ser procesada de acuerdo al Nivel de Servicio Estandar</a></td>
  </tr>
  
</table>
</BODY>
</HTML>
