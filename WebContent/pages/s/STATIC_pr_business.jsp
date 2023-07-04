<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.0 Transitional//EN">

<HTML>
<HEAD>
<META HTTP-EQUIV="Content-Type" CONTENT="text/html; charset=ISO-8859-1">
<META name="GENERATOR" content="IBM WebSphere Page Designer V3.5.2 for Windows">
<META http-equiv="Content-Style-Type" content="text/css">
<TITLE>Funciond e Negocios</TITLE>
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

<h4> Funci&oacute;n de Negocios</h4>
<table align=center class=tableinfo style="width:95%">
  <TR id=trdark> 
    <TH align=left  > 
      <div align="left"></div>
    </TH>
    <TH align=left  > 
      <div align="left">Descripcion</div>
    </TH>
  </TR>
  <tr> 
    <td width=30  > 
      <div align="center"><b>BTR</b></div>
    </td>
    <td ><A HREF="javascript:a('BTR')"> Transferencia de banco, el beneficiario 
      es un banco</a></td>
  </tr>
  <tr> 
    <td width=30  > 
      <div align="center"><b>CTR</b></div>
    </td>
    <td ><a href="javascript:a('CTR')">Transferencia de un cliente, el beneficiario 
      no es un banco</a></td>
  </tr>
  <tr> 
    <td width=30  > 
      <div align="center"><b>DEP</b></div>
    </td>
    <td ><a href="javascript:a('DEP')">Deposito en la Cuenta del emisor</a></td>
  </tr>
  <tr> 
    <td width=30  > 
      <div align="center"><b>DRB</b></div>
    </td>
    <td ><a href="javascript:a('DRB')">Solicitud de retiro Banco a Banco</a></td>
  </tr>
  <tr> 
    <td width=30  > 
      <div align="center"><b>DRC</b></div>
    </td>
    <td ><a href="javascript:a('DRC')">Solicitud de Retiro de Cliente a Corporacion</a></td>
  </tr>
  <tr> 
    <td width=30  > 
      <div align="center"><b>DRW</b></div>
    </td>
    <td ><a href="javascript:a('DRW')">Pago de Retiro</a></td>
  </tr>
  <tr> 
    <td width=30  > 
      <div align="center"><b>FFR</b></div>
    </td>
    <td ><a href="javascript:a('FFR')">Fondos de FED devueltos</a></td>
  </tr>
  <tr> 
    <td width=30  > 
      <div align="center"><b>FFS</b></div>
    </td>
    <td ><a href="javascript:a('FFS')">Fondos de FED vendidos</a></td>
  </tr>
  <tr> 
    <td width=30  > 
      <div align="center"><b>IRS</b></div>
    </td>
    <td ><a href="javascript:a('IRS')">Pagos de Impuesto al IRS</a></td>
  </tr>
  <tr> 
    <td width=30  > 
      <div align="center"><b>CKS</b></div>
    </td>
    <td ><a href="javascript:a('CKS')">Activacion de chequeo el mismo dia</a></td>
  </tr>
  <tr> 
    <td width=30  > 
      <div align="center"><b>SVC</b></div>
    </td>
    <td ><a href="javascript:a('SVC')">Servicio de Mensajes</a></td>
  </tr>
</table>
<P>
</P>
</BODY>
</HTML>
