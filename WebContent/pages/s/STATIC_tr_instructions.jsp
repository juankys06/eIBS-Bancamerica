<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.0 Transitional//EN">

<HTML>
<HEAD>
<META HTTP-EQUIV="Content-Type" CONTENT="text/html; charset=ISO-8859-1">
<META name="GENERATOR" content="IBM WebSphere Page Designer V3.5.2 for Windows">
<META http-equiv="Content-Style-Type" content="text/css">
<TITLE>Cógigo de Orden</TITLE>
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
<h4>Instrucciones</h4>
<table align=center class=tableinfo style="width:95%">
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
      <div align="center"><b>SDVA</b></div>
    </td>
    <td ><a href="javascript:a('SDVA')">El Pago debe ser efectuado con la misma fecha valor al beneficiario.</a></td>
  </tr>
  <tr> 
    <td width=30 > 
      <div align="center"><b>INTC</b></div>
    </td>
    <td ><a href="javascript:a('INTC')">El Pago es un pago intra-compañía  ej:, un pago entre dos compañías pertenecientes al mismo grupo</a></td>
  </tr>
  <tr> 
    <td width=30 > 
      <div align="center"><b>REPA</b></div>
    </td>
    <td ><a href="javascript:a('REPA')">El Pago tiene una referencia e-Pago</a></td>
  </tr>
  <tr> 
    <td width=30 > 
      <div align="center"><b>CORT</b></div>
    </td>
    <td ><A HREF="javascript:a('CORT')">El Pago es hecho por un acuerdo comercial, ej:, foreign exchange deal, transacciones de seguridad</a></td>
  </tr>
  <tr> 
    <td width=30 > 
      <div align="center"><b>BONL</b></div>
    </td>
    <td ><A HREF="javascript:a('BONL')">El Pago es hecho al cliente beneficiario únicamente</a></td>
  </tr>
  <tr> 
    <td width=30 > 
      <div align="center"><b>HOLD</b></div>
    </td>
    <td ><A HREF="javascript:a('HOLD')">El Cliente Beneficiario/solicitante reclamará el pago al identificarse</a></td>
  </tr>
  <tr> 
    <td width=30 > 
      <div align="center"><b>CHQB</b></div>
    </td>
    <td ><A HREF="javascript:a('CHQB')">Pagar al cliente beneficiario solamente por cheque, el número de cuenta opcional en el campo 59 no debe ser usado</a></td>
  </tr>
  <tr> 
    <td width=30 > 
      <div align="center"><b>PHOB</b></div>
    </td>
    <td ><A HREF="javascript:a('PHOB')">Por favor advertir/contactar al beneficiario/solicitante por telefóno</a></td>
  </tr>
  <tr> 
    <td width=30 > 
      <div align="center"><b>TELB</b></div>
    </td>
    <td ><A HREF="javascript:a('TELB')">Por favor advertir/contactar al beneficiario/solicitante por el más eficiente medio de telecomunicación</a></td>
  </tr>
  <tr> 
    <td width=30 > 
      <div align="center"><b>PHON</b></div>
    </td>
    <td ><A HREF="javascript:a('PHON')">Por favor advertir a la institución con la cuenta por telefóno</a></td>
  </tr>
  <tr> 
    <td width=30 > 
      <div align="center"><b>TELE</b></div>
    </td>
    <td ><A HREF="javascript:a('TELE')">Por favor advertir a la institución con la cuenta por el más eficiente medio de comunicación</a></td>
  </tr>
  <tr> 
    <td width=30 > 
      <div align="center"><b>PHOI</b></div>
    </td>
    <td ><A HREF="javascript:a('PHOI')">Por favor advertir a la institución intermediaria por telefóno</a></td>
  </tr>
  <tr> 
    <td width=30 > 
      <div align="center"><b>TELI</b></div>
    </td>
    <td ><A HREF="javascript:a('TELI')">Por favor advertir a la institución intermediaria por el más  eficiente medio de comunicación</a></td>
  </tr>
  
</table>
</BODY>
</HTML>
