<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.0 Transitional//EN">
<html>
<head>
<META name="GENERATOR" content="IBM WebSphere Page Designer V4.0 for Windows">
<META http-equiv="Content-Style-Type" content="text/css">
<title>Corporate User</title>
<link href="<%=request.getContextPath()%>/pages/style.css" rel="stylesheet">


<jsp:useBean id="msgData" class="datapro.eibs.beans.ESS570001Message" scope="session" />
<jsp:useBean id="error" class="datapro.eibs.beans.ELEERRMessage"  scope="session" />

<script language="Javascript1.1" src="<%=request.getContextPath()%>/pages/s/javascripts/eIBS.jsp"> </SCRIPT> 
<SCRIPT language="JavaScript">

setTimeout("top.close()", <%= datapro.eibs.master.JSEIBSProp.getPopUpTimeOut() %>)

function a(code) {
  var form = top.opener.document.forms[0];
  form[top.opener.fieldName].value = code;
  top.close();
}

</SCRIPT>
</head>

<body bgcolor="#FFFFFF">

<% 
 if ( !error.getERRNUM().equals("0")  ) {
     error.setERRNUM("0") ;
     out.println("<SCRIPT Language=\"Javascript\">");
     out.println("       showErrors()");
     out.println("</SCRIPT>");
 }
 %> 
 
 
<h3 align="center">Mensajes de Confirmación<img src="<%=request.getContextPath()%>/images/e_ibs.gif" align="left" name="EIBS_GIF" ALT="cliente_personal_new_data, ESS2000" ></h3>
<hr size="4">
<form method="post" action="<%=request.getContextPath()%>/servlet/com.datapro.eibs.internet.JSESS5600" onsubmit="return FieldNotBlank(this)">
  <INPUT TYPE=HIDDEN NAME="SCREEN" VALUE="200">
  <h4>Algunos Codigos Predefinidos</h4><table class="tableinfo">
     <tr id="trdark"> 
        <TH>Código</TH>
        <TH>Descripción</TH>
     </tr>
     <tr>
       <td><A HREF="javascript:a('0001')">0001</a></td>
       <td><A HREF="javascript:a('0001')">INTERNET TRANSFERENCIA INTERNA</a></td>
     </tr>
	<TR>
		<TD><A HREF="javascript:a('0002')">0002</a></TD>
		<TD><A HREF="javascript:a('0002')">INTERNET TRANSFERENCIA EXTERNA</a></TD>
	</TR>
	<TR>
		<TD><A HREF="javascript:a('0003')">0003</a></TD>
		<TD><A HREF="javascript:a('0003')">BILL PAYMENT MADE</a></TD>
	</TR>
	<TR>
		<TD><A HREF="javascript:a('0004')">0004</a></TD>
		<TD><A HREF="javascript:a('0004')">BILL PAYMENT REJECT</a></TD>
	</TR>
	<TR>
		<TD><A HREF="javascript:a('0010')">0010</a></TD>
		<TD><A HREF="javascript:a('0010')">LOAN PAYMENT</a></TD>
	</TR>
	<TR>
		<TD><A HREF="javascript:a('0011')">0011</a></TD>
		<TD><A HREF="javascript:a('0011')">RATE CHANGE CONFIRMATION</a></TD>
	</TR>
	<TR>
		<TD><A HREF="javascript:a('0012')">0012</a></TD>
		<TD><A HREF="javascript:a('0012')">PRINCIPAL DUE ONLY</a></TD>
	</TR>
	<TR>
		<TD><A HREF="javascript:a('0013')">0013</a></TD>
		<TD><A HREF="javascript:a('0013')">INTEREST DUE ONLY</a></TD>
	</TR>
	<TR>
		<TD><A HREF="javascript:a('0014')">0014</a></TD>
		<TD><A HREF="javascript:a('0014')">DUE PAYMENT PRINCIPAL + INTEREST</a></TD>
	</TR>
	<TR>
		<TD><A HREF="javascript:a('0015')">0015</a></TD>
		<TD><A HREF="javascript:a('0015')">PRINCIPAL PAYMENT (ONLY) ADVICE</a></TD>
	</TR>
	<TR>
		<TD><A HREF="javascript:a('0016')">0016</a></TD>
		<TD><A HREF="javascript:a('0016')">INTEREST PAYMENT (ONLY) ADVICE</a></TD>
	</TR>
	<TR>
		<TD><A HREF="javascript:a('0017')">0017</a></TD>
		<TD><A HREF="javascript:a('0017')">PRINCIPAL + INTEREST PAYMENT ADVICE</a></TD>
	</TR>
	<TR>
		<TD><A HREF="javascript:a('0020')">0020</a></TD>
		<TD><A HREF="javascript:a('0020')">LOAN PAYMENT (REJECTED)</a></TD>
	</TR>
	<TR>
		<TD><A HREF="javascript:a('0021')">0021</a></TD>
		<TD><A HREF="javascript:a('0021')">R01 CHARGES FOR INSUFFICIENT FUNDS</a></TD>
	</TR>
	<TR>
		<TD><A HREF="javascript:a('0022')">0022</a></TD>
		<TD><A HREF="javascript:a('0022')">R02 CHARGES FOR USE OF UNCOLLECTED FUND</a></TD>
	</TR>
	<TR>
		<TD><A HREF="javascript:a('0023')">0023</a></TD>
		<TD><A HREF="javascript:a('0023')">R03 CHARGES FOR STOP PAYMENT</a></TD>
	</TR>
	<TR>
		<TD><A HREF="javascript:a('0024')">0024</a></TD>
		<TD><A HREF="javascript:a('0024')">T01 TRANSACTION RELATED</a></TD>
	</TR>
	<TR>
		<TD><A HREF="javascript:a('0025')">0025</a></TD>
		<TD><A HREF="javascript:a('0025')">NSF CHARGES ISUED</a></TD>
	</TR>
	<TR>
		<TD><A HREF="javascript:a('0026')">0026</a></TD>
		<TD><A HREF="javascript:a('0026')">CHARHES FOR USE OF UNCOLLECTED FUNDS</a></TD>
	</TR>
	<TR>
		<TD><A HREF="javascript:a('0030')">0030</a></TD>
		<TD><A HREF="javascript:a('0030')">TIME DEPOSIT MATURITY NOTICE</a></TD>
	</TR>
	<TR>
		<TD><A HREF="javascript:a('0031')">0031</a></TD>
		<TD><A HREF="javascript:a('0031')">TIME DEPOSIT RENEWAL NOTICE</a></TD>
	</TR>
	<TR>
		<TD><A HREF="javascript:a('0060')">0060</a></TD>
		<TD><A HREF="javascript:a('0060')">W-8  LETTER</a></TD>
	</TR>
	<TR>
		<TD><A HREF="javascript:a('0061')">0061</a></TD>
		<TD><A HREF="javascript:a('0061')">W-9  LETTER </a></TD>
	</TR>
	<TR>
		<TD><A HREF="javascript:a('1000')">1000</a></TD>
		<TD><A HREF="javascript:a('1000')">LOAN APPLICATION</a></TD>
	</TR>
	<TR>
		<TD><A HREF="javascript:a('1001')">1001</a></TD>
		<TD><A HREF="javascript:a('1001')">CREDIT CARD APPLICATION</a></TD>
	</TR>
	<TR>
		<TD><A HREF="javascript:a('1002')">1002</a></TD>
		<TD><A HREF="javascript:a('1002')">PAYMENT ORDER PLACED </a></TD>
	</TR>
	<TR>
		<TD><A HREF="javascript:a('1003')">1003</a></TD>
		<TD><A HREF="javascript:a('1003')">RETAIL ACCOUNT OPENING WITHOUT TRANSFER</a></TD>
	</TR>
	<TR>
		<TD><A HREF="javascript:a('1004')">1004</a></TD>
		<TD><A HREF="javascript:a('1004')">RETAIL ACCOUNT OPENING WITH TRANSFER</a></TD>
	</TR>
	<TR>
		<TD><A HREF="javascript:a('1005')">1005</a></TD>
		<TD><A HREF="javascript:a('1005')">DELETE PAYMENT ORDER</a></TD>
	</TR>
	<TR>
		<TD><A HREF="javascript:a('1010')">1010</a></TD>
		<TD><A HREF="javascript:a('1010')">CERTIFICATES OF DEPOSIT DEBIT CUSTOMER </a></TD>
	</TR>
	<TR>
		<TD><A HREF="javascript:a('1011')">1011</a></TD>
		<TD><A HREF="javascript:a('1011')">CERTIFICATES OF DEPOSIT WITH CREDIT CARD</a></TD>
	</TR>
	<TR>
		<TD><A HREF="javascript:a('1030')">1030</a></TD>
		<TD><A HREF="javascript:a('1030')">CAMBIO ESTADO DE CHEQUERA</a></TD>
	</TR>
	<TR>
		<TD><A HREF="javascript:a('1070')">1070</a></TD>
		<TD><A HREF="javascript:a('1070')">LETTER OF CREDIT REQUEST</a></TD>
	</TR>
	<TR>
		<TD><A HREF="javascript:a('1100')">1100</a></TD>
		<TD><A HREF="javascript:a('1100')">REQUEST FOR MANUAL REGISTRATION</a></TD>
	</TR>
	<TR>
		<TD><A HREF="javascript:a('1101')">1101</a></TD>
		<TD><A HREF="javascript:a('1101')">REQUEST FOR AUTOMATIC REGISTRATION</a></TD>
	</TR>
	<TR>
		<TD><A HREF="javascript:a('1102')">1102</a></TD>
		<TD><A HREF="javascript:a('1102')">CONFORMATION DE CHEQUES</a></TD>
	</TR>
	<TR>
		<TD><A HREF="javascript:a('1003')">1103</a></TD>
		<TD><A HREF="javascript:a('1103')">REPORTO DE LIBRETA  EXTRAVIADA</a> </TD>
	</TR>
	<TR>
		<TD><A HREF="javascript:a('1104')">1104</a></TD>
		<TD><A HREF="javascript:a('1104')">REPORTO DE TARJETA DE CREDITO EXTRAVIADA</a></TD>
	</TR>
	<TR>
		<TD><A HREF="javascript:a('1105')">1105</a></TD>
		<TD><A HREF="javascript:a('1105')">CREDIT CARD PAYMENT</a></TD>
	</TR>
	<TR>
		<TD><A HREF="javascript:a('1110')">1110</a></TD>
		<TD><A HREF="javascript:a('1110')">GIRO LOCAL</a></TD>
	</TR>
	<TR>
		<TD><A HREF="javascript:a('1120')">1120</a></TD>
		<TD><A HREF="javascript:a('1120')">PAGO DE SERVICIO  DEBITO A CUENTA</a></TD>
	</TR>
	<TR>
		<TD><A HREF="javascript:a('1121')">1121</a></TD>
		<TD><A HREF="javascript:a('1121')">PAGO DE SERVICIO  DEBITO TARJETA DE CREDITO</a></TD>
	</TR>
	<TR>
		<TD><A HREF="javascript:a('1201')">1201</a></TD>
		<TD><A HREF="javascript:a('1201')">PAGO DE PLANILLAS</a></TD>
	</TR>
	<TR>
		<TD><A HREF="javascript:a('1300')">1300</a></TD>
		<TD><A HREF="javascript:a('1300')">PAGO A TERCEROS</a></TD>
	</TR>
	<TR>
		<TD><A HREF="javascript:a('7001')">7001</a></TD>
		<TD><A HREF="javascript:a('7001')">Alerta Balance Cuenta Bajo el Minimo</a></TD>
	</TR>
	<TR>
		<TD><A HREF="javascript:a('7002')">7002</a></TD>
		<TD><A HREF="javascript:a('7002')">Alerta por Debito a Cuenta</a></TD>
	</TR>
	<TR>
		<TD><A HREF="javascript:a('7003')">7003</a></TD>
		<TD><A HREF="javascript:a('7003')">Alerta por Deposito a Cuenta</a></TD>
	</TR>
	<TR>
		<TD><A HREF="javascript:a('7004')">7004</a></TD>
		<TD><A HREF="javascript:a('7004')">Alerta por Wire Transfer a Cuenta</a></TD>
	</TR>
	<TR>
		<TD><A HREF="javascript:a('7005')">7005</a></TD>
		<TD><A HREF="javascript:a('7005')">Alerta por Pago Automatico de Cuenta</a></TD>
	</TR>	
	<TR>	
		<TD><A HREF="javascript:a('7006')">7002</a></TD>
		<TD><A HREF="javascript:a('7006')">Alerta por Dias Inactivos de Cuenta</a></TD>
	</TR>
	<TR>
		<TD><A HREF="javascript:a('7007')">7007</a></TD>
		<TD><A HREF="javascript:a('7007')">Alerta por Sobregiro en Cuenta</a></TD>		
	</TR>
	<TR>
		<TD><A HREF="javascript:a('7008')">7008</a></TD>
		<TD><A HREF="javascript:a('7008')">Alerta por Cheque Rechazado</a></TD>
	</TR>
	<TR>
		<TD><A HREF="javascript:a('7009')">7009</a></TD>
		<TD><A HREF="javascript:a('7009')">Alerta Vencimiento de Certificado</a></TD>
	</TR>
	<TR>
		<TD><A HREF="javascript:a('7010')">7010</a></TD>
		<TD><A HREF="javascript:a('7010')">Alerta Proximo Pago Cuota de Prestamo</a></TD>
	</TR>
	<TR>
		<TD><A HREF="javascript:a('7011')">7011</a></TD>
		<TD><A HREF="javascript:a('7011')">Alerta Cuota Vencida de Prestamo</a></TD>
	</TR>



</table>  
       
  <p align="center"> 
  <input id="EIBSBTN" type=submit name="Submit" value="Enviar"> </p>
</form>
</body>
</html>
