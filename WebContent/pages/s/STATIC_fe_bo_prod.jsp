<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.0 Transitional//EN">

<HTML>
<HEAD>
<META HTTP-EQUIV="Content-Type" CONTENT="text/html; charset=ISO-8859-1">
<META name="GENERATOR" content="IBM WebSphere Page Designer V3.5.2 for Windows">
<META http-equiv="Content-Style-Type" content="text/css">
<TITLE>Product Selection</TITLE>
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
<h4>Selecci&oacute;n de Producto</h4>
<table align=center class=tableinfo style="width:95%">
  <TR id=trdark> 
    <TH align=left colspan="2"  > 
      <div align="left"></div>
      <div align="left">Moneda Extranjera</div>
    </TH>
  </TR>
  <tr> 
    <td width=30 > 
      <div align="center"><b>SPOT</b></div>
    </td>
    <td ><a href="javascript:a('SPOT')">Spot</a></td>
  </tr>
  <tr> 
    <td width=30 > 
      <div align="center"><b>FWRD</b></div>
    </td>
    <td ><a href="javascript:a('FWRD')">Forward</a></td>
  </tr>
  <tr> 
    <td width=30 > 
      <div align="center"><b>NDF </b></div>
    </td>
    <td ><A HREF="javascript:a('NDF ')">Non Deliverable Forward</a></td>
  </tr>
  <tr> 
    <td width=30 > 
      <div align="center"><b>SWAP</b></div>
    </td>
    <td ><A HREF="javascript:a('SWAP')">Swap</a></td>
  </tr>
  <tr> 
    <td width=30 > 
      <div align="center"><b>OPTI</b></div>
    </td>
    <td ><a href="javascript:a('OPTI')">Options</a></td>
  </tr>
  <tr id="trdark"> 
    <td colspan="2" > 
      <div align="left"><b>Money Market</b></div>
    </td>
  </tr>
  <tr> 
    <td width=30 > 
      <div align="center"><b>FFS</b></div>
    </td>
    <td ><a href="javascript:a('FFS')">Venta de Inversi&oacute;n Nocturna</a></td>
  </tr>
  <tr> 
    <td width=30 > 
      <div align="center"><b>FFP</b></div>
    </td>
    <td ><a href="javascript:a('FFP')">Compra de Inversi&oacute;n Nocturna</a></td>
  </tr>
  <tr> 
    <td width=30 > 
      <div align="center"><b>CDS</b></div>
    </td>
    <td ><a href="javascript:a('CDS')">Venta de Certificados de Depósito</a></td>
  </tr>
  <tr> 
    <td width=30 > 
      <div align="center"><b>CDP</b></div>
    </td>
    <td ><a href="javascript:a('CDP')">Compra de Certificados de Depósito</a></td>
  </tr>
  <tr> 
    <td width=30 > 
      <div align="center"><b>TDS</b></div>
    </td>
    <td ><a href="javascript:a('TDS')">Venta de Depósitos a Plazo</a></td>
  </tr>
  <tr> 
    <td width=30 > 
      <div align="center"><b>TDP</b></div>
    </td>
    <td ><a href="javascript:a('TDP')">Compra de Depósitos a Plazo</a></td>
  </tr>
  <tr> 
    <td width=30 > 
      <div align="center"><b>PLP</b></div>
    </td>
    <td ><a href="javascript:a('PLP')">Papeles Comerciales</a></td>
  </tr>
  <tr> 
    <td width=30 > 
      <div align="center"><b>ACS</b></div>
    </td>
    <td ><a href="javascript:a('ACS')">Venta de Aceptaciones Bancarias</a></td>
  </tr>
  <tr> 
    <td width=30 > 
      <div align="center"><b>ACP</b></div>
    </td>
    <td ><a href="javascript:a('ACP')">Compra de Aceptaciones Bancarias</a></td>
  </tr>
  <tr id="trdark"> 
    <td colspan="2" > 
      <div align="left"><b>Otros</b></div>
    </td>
  </tr>
  <tr> 
    <td width=30 > 
      <div align="center"><b>FRA</b></div>
    </td>
    <td ><a href="javascript:a('FRA')">Contratos Tasa Futura</a></td>
  </tr>
  <tr>
    <td width=30 >
      <div align="center"><b>MMP</b></div>
    </td>
    <td ><a href="javascript:a('MMP')">Límites Financieros</a></td>
  </tr>
</table>
</BODY>
</HTML>
