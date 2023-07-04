<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.0 Transitional//EN">

<HTML>
<HEAD>
<META HTTP-EQUIV="Content-Type" CONTENT="text/html; charset=ISO-8859-1">
<META name="GENERATOR" content="IBM WebSphere Page Designer V3.5.2 for Windows">
<META http-equiv="Content-Style-Type" content="text/css">
<TITLE>Transaction Process Type</TITLE>
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
<H4>Tipo de Proceso de la Transacción</H4>
<table align=center class=tableinfo style="width:95%">
  <TR id=trdark> 
    <TH align=left  > 
      <div align="left">Código</div>
    </TH>
     <TH align=left  > 
      <div align="left">Descripción</div>
    </TH>

  </TR>

  <tr id=trdark> 
    <td width=30 colspan="2"> 
      <div ><b>SERVICIOS</b></div>
    </td>
  </tr>
  <tr> 
    <td width=30> 
      <div align="center"><b>SP</b></div>
    </td>
    <td ><A HREF="javascript:a('SP')"> Suspender el Pago de un Cheque</a></td>
  </tr>
  <tr> 
    <td width=30 > 
      <div align="center"><b>SR</b></div>
    </td>
    <td ><A HREF="javascript:a('SR')"> Liberar la suspención de Pago</a></td>
  </tr>
  <tr> 
    <td width=30 > 
      <div align="center"><b>SV</b></div>
    </td>
    <td ><A HREF="javascript:a('SV')"> Servicio de Cobranza</a></td>
  </tr>
  <tr> 
    <td width=30 > 
      <div align="center"><b>PU</b></div>
    </td>
    <td ><A HREF="javascript:a('PU')"> Actualiza Saldos de Libreta</a></td>
  </tr>
  <tr> 
    <td width=30 > 
      <div align="center"><b>DP</b></div>
    </td>
    <td ><A HREF="javascript:a('DP')"> Depósitos</a></td>
  </tr>
  <tr> 
    <td width=30 > 
      <div align="center"><b>WH</b></div>
    </td>
    <td ><A HREF="javascript:a('WH')"> Retiros</a></td>
  </tr>
  <tr> 
    <td width=30 > 
      <div align="center"><b>PF</b></div>
    </td>
    <td ><A HREF="javascript:a('PF')"> Desglose Depósitos Cheques Gerencia</a></td>
  </tr>
  <tr> 
    <td width=30 > 
      <div align="center"><b>PB</b></div>
    </td>
    <td ><A HREF="javascript:a('PB')"> Desglose Depositos Cheques M/Banco</a></td>
  </tr>
  <tr> 
    <td width=30 > 
      <div align="center"><b>PN</b></div>
    </td>
    <td ><A HREF="javascript:a('PN')"> Desglose Depositos Cheques O/Bancos </a></td>
  </tr>
  <tr> 
    <td width=30 > 
      <div align="center"><b>GT</b></div>
    </td>
    <td ><A HREF="javascript:a('GT')"> Transacciones Contables  </a></td>
  </tr>
  <tr> 
    <td width=30 > 
      <div align="center"><b>RU</b></div>
    </td>
    <td ><A HREF="javascript:a('RU')"> Liberación de Diferidos</a></td>
  </tr>
  <tr> 
    <td width=30 > 
      <div align="center"><b>OF</b></div>
    </td>
    <td ><A HREF="javascript:a('OF')"> Cancela Cheque de Gerencia</a></td>
  </tr>
  <tr> 
    <td width=30 > 
      <div align="center"><b>HP</b></div>
    </td>
    <td ><A HREF="javascript:a('HP')"> Efectuar una Retención Interna</a></td>
  </tr>
  <tr> 
    <td width=30 > 
      <div align="center"><b>HR</b></div>
    </td>
    <td ><A HREF="javascript:a('HR')"> Liberar una Retención Interna</a></td>
  </tr>
  <tr> 
    <td width=30 > 
      <div align="center"><b>MC</b></div>
    </td>
    <td ><A HREF="javascript:a('MC')"> Miscelaneous Creditos</a></td>
  </tr>
  <tr> 
    <td width=30 > 
      <div align="center"><b>MD</b></div>
    </td>
    <td ><A HREF="javascript:a('MD')"> Miscelaneous Debitos </a></td>
  </tr>
  <tr> 
    <td width=30 > 
      <div align="center"><b>AL</b></div>
    </td>
    <td ><A HREF="javascript:a('AL')">Actualiza Nueva Libreta</a></td>
  </tr>
  <tr> 
    <td width=30 > 
      <div align="center"><b>TT</b></div>
    </td>
    <td ><A HREF="javascript:a('TT')">Totales de Cajero/Agencia</a></td>
  </tr>
  <tr> 
    <td width=30 > 
      <div align="center"><b>CT</b></div>
    </td>
    <td ><A HREF="javascript:a('CT')"> Cerrar Cajero</a></td>
  </tr>
  <tr> 
    <td width=30 > 
      <div align="center"><b>TR</b></div>
    </td>
    <td ><A HREF="javascript:a('TR')">Transferencia entre Cuentas </a></td>
  </tr>
  <tr> 
    <td width=30 > 
      <div align="center"><b>IR</b></div>
    </td>
    <td ><A HREF="javascript:a('IR')">Pagos de Impuestos Recibidos</a></td>
  </tr>
  
  <tr id=trdark> 
    <td width=30 colspan="2"> 
      <div ><b>INQUIRIES</b></div>
    </td>
  </tr>  
  <tr> 
    <td width=30> 
      <div align="center"><b>IB</b></div>
    </td>
    <td ><A HREF="javascript:a('IB')"> Saldos de la Cuenta</a></td>
  </tr>
  <tr> 
    <td width=30 > 
      <div align="center"><b>IL</b></div>
    </td>
    <td ><A HREF="javascript:a('IL')">Ultima Transaccion </a></td>
  </tr>
  <tr> 
    <td width=30 > 
      <div align="center"><b>IU</b></div>
    </td>
    <td ><A HREF="javascript:a('IU')"> Retenciones Internas</a></td>
  </tr>
  <tr>   
  <tr> 
    <td width=30> 
      <div align="center"><b>IH</b></div>
    </td>
    <td ><A HREF="javascript:a('IH')"> Historico transacciones</a></td>
  </tr>
  
  <tr id=trdark> 
    <td width=30 colspan="2"> 
      <div ><b>PLATAFORMA</b></div>
    </td>
  </tr>  
  
  <tr> 
    <td width=30 > 
      <div align="center"><b>CI</b></div>
    </td>
    <td ><A HREF="javascript:a('CI')"> Consulta de Clientes</a></td>
  </tr>
  <tr> 
    <td width=30 > 
      <div align="center"><b>CA</b></div>
    </td>
    <td ><A HREF="javascript:a('CA')"> Adicionar Cliente</a></td>
  </tr>
  <tr>
    <td width=30 > 
      <div align="center"><b>CM</b></div>
    </td>
    <td ><A HREF="javascript:a('CM')"> Modificar Cliente</a></td>
  </tr>
   <tr>
    <td width=30 > 
      <div align="center"><b>CC</b></div>
    </td>
    <td ><A HREF="javascript:a('CC')">Inf Complemtaria Cliente</a></td>
  </tr>
   <tr>
    <td width=30 > 
      <div align="center"><b>AI</b></div>
    </td>
    <td ><A HREF="javascript:a('AI')"> Consulta de Cuenta</a></td>
  </tr>
   <tr>
    <td width=30 > 
      <div align="center"><b>AA</b></div>
    </td>
    <td ><A HREF="javascript:a('CA')">Adiciona una Cuenta</a></td>
  </tr>
   <tr>
    <td width=30 > 
      <div align="center"><b>AM</b></div>
    </td>
    <td ><A HREF="javascript:a('AM')"> Modifica una Cuenta</a></td>
  </tr>
   <tr>
    <td width=30 > 
      <div align="center"><b>AC</b></div>
    </td>
    <td ><A HREF="javascript:a('AC')"> Cancela una Cuenta</a></td>
  </tr>
   <tr>
    <td width=30 > 
      <div align="center"><b>TI</b></div>
    </td>
    <td ><A HREF="javascript:a('TI')"> Consulta de Certificado</a></td>
  </tr>
   <tr>
    <td width=30 > 
      <div align="center"><b>TA</b></div>
    </td>
    <td ><A HREF="javascript:a('TA')"> Adiciona Certificado</a></td>
  </tr>
   <tr>
    <td width=30 > 
      <div align="center"><b>TM</b></div>
    </td>
    <td ><A HREF="javascript:a('TM')"> Modifica Certificado</a></td>
  </tr>
   <tr>
    <td width=30 > 
      <div align="center"><b>LI</b></div>
    </td>
    <td ><A HREF="javascript:a('LI')"> Consulta de Prestamos</a></td>
  </tr>
   <tr>
    <td width=30 > 
      <div align="center"><b>LA</b></div>
    </td>
    <td ><A HREF="javascript:a('LA')"> Adiciona Prestamo</a></td>
  </tr>
   <tr>
    <td width=30 > 
      <div align="center"><b>LP</b></div>
    </td>
    <td ><A HREF="javascript:a('LP')"> Pago de Prestamo </a></td>
  </tr>
    
</table>
<br>
</BODY>
</HTML>
