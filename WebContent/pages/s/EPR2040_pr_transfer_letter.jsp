<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.0 Transitional//EN">

<html>
<head>
<title>Confirmacion</title>
<META HTTP-EQUIV="Content-Type" CONTENT="text/html; charset=ISO-8859-1">
<META name="GENERATOR" content="IBM WebSphere Page Designer V3.5.2 for Windows">
<META http-equiv="Content-Style-Type" content="text/css">
<link Href="<%=request.getContextPath()%>/pages/style.css" rel="stylesheet">
<%@ page import = "datapro.eibs.master.*" %>

<SCRIPT SRC="<%=request.getContextPath()%>/pages/s/javascripts/eIBS.jsp"> </SCRIPT>
<jsp:useBean id="msgLetter" class="datapro.eibs.beans.EPR204001Message"  scope="session" />
<%
String smonth = "";
String syear ="" ;
String expyear="";
int nmonth = Integer.parseInt(msgLetter.getE01PRODT2());
int nyear = 2000 + Integer.parseInt(msgLetter.getE01PRODT3());
int nexpyear = 2000 + Integer.parseInt(msgLetter.getE01EXPDT3()); 
syear = "" + nyear;
expyear="" + nexpyear;
switch (nmonth){
	case 1 : smonth = "Enero";
			 break;
	case 2 : smonth = "Febrero";
			 break;
	case 3 : smonth = "Marzo";
			 break;
	case 4 : smonth = "Abril";
			 break;
	case 5 : smonth = "Mayo";
			 break;
	case 6 : smonth = "Junio";
			 break;		 
	case 7 : smonth = "Julio";
			 break;		 		
	case 8 : smonth = "Agosto";
			 break;
	case 9 : smonth = "Setiembre";
			 break;
	case 10 : smonth = "Octubre";
			 break;
	case 11 : smonth = "Noviembre";
			 break;
	case 12 : smonth = "Diciembre";
			 break;		 		 		 		 		  		 
}
%> 
</head>
<body>
<TABLE class="border=no">
	<TBODY>
		<TR>
			<TD colspan="3" align="center"
				style="font-weight: bold; text-decoration: underline">MANDATO
			TRANSFERENCIA DE FONDOS<BR>
			(Uso Exclusivo Clientes Cuenta Correntistas)</TD>
		</TR>
		<TR>
			<TD style="font-weight: bold;" width="184"><br></TD>
			<TD style="font-weight: bold;" colspan="2" width="437"></TD>
		</TR>
		<TR>
			<TD style="font-weight: bold;" width="184">Folio:</TD>
			<td><%= msgLetter.getE01PRPNUM() %></td>
			<TD style="font-weight: bold;"></TD>
		</TR>
		<TR>
			<TD colspan="3" style="font-weight: bold"><BR>
			ANTECEDENTES PERSONALES DEL SOLICITANTE<BR>
			</TD>
		</TR>
		<TR>
			<TD style="font-weight: bold;" width="184"><BR></TD>
			<TD style="font-weight: bold;" colspan="2" width="437"></TD>
		</TR>	
		<TR>
			<TD style="font-weight: bold;" width="184">Nombre Cliente :</TD>
			<TD style="font-weight: bold;" colspan="2" width="437"><%= msgLetter.getE01CUSNA1() %></TD>
		</TR>
		<TR>
			<TD style="font-weight: bold;" width="184">Cedula de Identidad :</TD>
			<TD style="font-weight: bold;" colspan="2" width="437"><%= Util.formatRUT(msgLetter.getE01CUSIDN()) %></TD>
		</TR>
		<TR>
			<TD style="font-weight: bold;" width="184">Cuenta Corriente de cargo :</TD>
			<TD style="font-weight: bold;" colspan="2" width="437"><%= msgLetter.getE01PRPDAC() %></TD>
		</TR>
		<TR>
			<TD colspan="3" style="text-align: justify"><BR>
			<p>Por el Presente instrumento, otorgo un mandato especial a
			Scotiabank Sud Americano, para transferir desde mi cuenta corriente
			señalada precedentemente, o en su defecto, si en ella no existiesen
			fondos disponibles suficientes, en la línea de crédito asociada, a
			la cuenta de abono que se detallan más abajo, el 
			valor, en el día y con la periodicidad que se ha señalado. En el
			evento que el  día que corresponda efectuar la transferencia,
			fuese un día sábado, domingo o festivo, ella se realizará el día
			hábil siguiente.</p>

			<p>Me obligo a mantener en la cuenta señalada precedentemente, los
			fondos necesarios para cubrir cada uno de los cargos que se hagan por
			la transferencia de fondos a las cuentas de abono señaladas, como
			asimismo el impuesto que grava estos actos.</p>
			<p>Libero a Scotiabank Sud Americano de toda responsabilidad en el
			ejercicio de este mandato, especialmente por no cargar las
			transferencias de fondos a las cuentas de abono en el caso de no
			tener fondos disponibles suficientes en la cuenta corriente o línea
			de crédito.</p>
			<p>Este mandato expirará en la fecha señalada, por voluntad del
			mandante, del Banco o por el cierre de la cuenta corriente
			respectiva; la revocación del mandato deberá notificarse por escrito
			al Banco y surtirá efectos dentro de las 48 horas siguientes al acuse
			de recibo, salvo en el primer caso señalado.</p>
			</TD>
		</TR>
		<TR>
			<TD style="font-weight: bold;" width="184"><BR>DETALLE DEL MANDATO</TD>
			<TD colspan="2" width="437"></TD>
		</TR>
		<TR>
			<TD width="184"><BR></TD>
			<TD colspan="2" width="437"></TD>
		</TR>
		<TR>
			<TD width="184">Sucursal:</TD>
			<TD colspan="2" width="437"><%=msgLetter.getE01PRPOBR() %> - <%= msgLetter.getE01BRNNME() %></TD>
		</TR>
		<TR>
			<TD width="184">Ejecutivo:</TD>
			<TD colspan="2" width="437"><%=msgLetter.getE01PRPOFC() %> - <%=msgLetter.getE01OFCNME() %></TD>
		</TR>
		<TR>
			<TD width="184">Fecha de Expiración:</TD>
			<TD colspan="2" width="437"><%=msgLetter.getE01EXPDT1() %>/<%=msgLetter.getE01EXPDT2()%>/<%= expyear %></TD>
		</TR>
		<TR>
			<TD width="184">Tipo de Moneda(2/CLP):</TD>
			<TD colspan="2" width="437"><% if (msgLetter.getE01PRPTCY().equals("2")) { out.println("UF"); } else if (msgLetter.getE01PRPTCY().equals("CLP")) {out.println("PESOS");}%></TD>
		</TR>
		<TR>
			<TD width="184">Tipo de Pago (Fijo/Variable):</TD>
			<TD colspan="2" width="437"><%=msgLetter.getE01TIPOPA() %></TD>
		</TR>
		<TR>
			<TD width="184">N° Cuenta de Abono:</TD>
			<TD colspan="2" width="437"><%=msgLetter.getE01PRPCAC() %></TD>
		</TR>
		<TR>
			<TD width="184">Nombre Titular Cuenta de Abono:</TD>
			<TD colspan="2" width="437"><%=msgLetter.getE01CRENA1() %></TD>
		</TR>
		<TR>
			<TD width="184">Monto del Abono:</TD>
			<TD colspan="2" width="437"><%=msgLetter.getE01PRPAMT() %></TD>
		</TR>
		<TR>
			<TD width="184">Frecuencia (D/S/M/2M/3M/V):</TD>
			<TD colspan="2" width="437"><%=msgLetter.getE01TIPOFR() %></TD>
		</TR>
		<TR>
			<TD width="184">Día de Pago (S:Lu -&gt; Vi/M:1-&gt;31):</TD>
			<TD colspan="2" width="437"><%=msgLetter.getE01DIAPAG() %></TD>
		</TR>
		<TR>
			<TD width="184">Número de Días (V: Cada 1-&gt; 999):</TD>
			<TD colspan="2" width="437"><% if (!msgLetter.getE01PRPDYS().equals("0")) out.print(msgLetter.getE01PRPDYS()); %></TD>
		</TR>
		<TR>
			<TD width="184">N° de Pagos (Obligatorio: 1 -&gt; 9999):</TD>
			<TD colspan="2" width="437"><%=msgLetter.getE01PRPNPM() %></TD>
		</TR>
		<TR>
			<TD width="184"><br></TD>
			<TD colspan="2" width="437"></TD>
		</TR>
		<TR>
			<TD width="184">Monto Comisión:</TD>
			<TD colspan="2" width="437"><%=msgLetter.getE01COMAMT() %></TD>
		</TR>
		<TR>
			<TD width="184">Moneda Comisión (Pesos/UF):</TD>
			<TD colspan="2" width="437"><%=msgLetter.getE01COMNME() %></TD>
		</TR>
		<TR>
			<TD width="184"><br></TD>
			<TD colspan="2" width="437"></TD>
		</TR>
		<TR>
			<TD colspan="3">EN SANTIAGO, a <%=msgLetter.getE01PRODT1() %> de <%=smonth %> de <%= syear %></TD>
		</TR>
		<TR>
			<TD width="184"><br></TD>
			<TD colspan="2" width="437"></TD>
		</TR>
		<TR>
			<TD width="184"><br></TD>
			<TD colspan="2" width="437"></TD>
		</TR>
		<TR>
			<TD width="184"></TD>
			<TD align="left" colspan="2" width="437"><div align=center>----------------------------------------<br>Firma del Cliente</div></TD>
		</TR>
	</TBODY>
</TABLE>
</body>
</html>


