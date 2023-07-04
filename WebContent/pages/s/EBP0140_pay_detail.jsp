<%@ page import="datapro.eibs.master.Util"%>
<html>
<head>
<title>Actualización Pago Factura</title>
<META HTTP-EQUIV="Content-Type" CONTENT="text/html; charset=ISO-8859-1">
<link Href="<%=request.getContextPath()%>/pages/style.css" rel="stylesheet">

</head>

<jsp:useBean id="EBP0140Record" class="datapro.eibs.beans.EBP014001Message" scope="session" />
<jsp:useBean id="error" class="datapro.eibs.beans.ELEERRMessage" scope="session" />
<jsp:useBean id="userPO" class="datapro.eibs.beans.UserPos" scope="session" />
<jsp:useBean id="currUser" class="datapro.eibs.beans.ESS0030DSMessage" scope="session" />

<body>

<script src="<%=request.getContextPath()%>/pages/s/javascripts/eIBS.jsp"> </SCRIPT>
<script src="<%=request.getContextPath()%>/pages/s/javascripts/optMenu.jsp"> </SCRIPT>
<script 	src="<%=request.getContextPath()%>/pages/s/javascripts/eIBSBillsP.jsp"> </SCRIPT>

<script language="JavaScript">

function goInquiry() {
	bilnum = document.getElementById("E01BPBNUM").value;
    var pg = "";
 	pg = "<%=request.getContextPath()%>/servlet/datapro.eibs.bap.JSEBP0160?SCREEN=3" + "&E01BPBNUM=" + bilnum;
 	CenterWindow(pg,900,600,2);
}

function goExit(){

   	document.forms[0].SCREEN.value = '2';
	document.forms[0].submit();
 }
</SCRIPT>

<%if (!error.getERRNUM().equals("0")) {
				out.println("<SCRIPT Language=\"Javascript\">");
				error.setERRNUM("0");
				out.println("       showErrors()");
				out.println("</SCRIPT>");
			}
%>


<h3 align="center"><%if (userPO.getType().equals("V")) {
				out.println("Vendor Bill ");
			} else if (userPO.getType().equals("C")) {
				out.println("Customer Bill ");
			} else {
				out.println("Purchase Order Bill ");
			}
			%>Payment - Update 
<img src="<%=request.getContextPath()%>/images/e_ibs.gif" align="left" 	name="EIBS_GIF" ALT="pay_detail.jsp, EBP0140"></h3>
<hr size="4">

<form method="post" action="<%=request.getContextPath()%>/servlet/datapro.eibs.bap.JSEBP0140">
<INPUT TYPE=HIDDEN name="SCREEN" value="6"> 
<INPUT TYPE=HIDDEN name="E01BPBNUM" value="<%= EBP0140Record.getE01BPBNUM()%>">
<INPUT TYPE=HIDDEN name="E01BPBSEQ" value="<%= EBP0140Record.getE01BPBSEQ()%>">
<INPUT TYPE=HIDDEN name="E01REQBNK" value="<%= userPO.getBank() %>"> 
<INPUT TYPE=HIDDEN name="E01REQBRN" value="<%= userPO.getBranch() %>"> 
<INPUT TYPE=HIDDEN name="E01REQTYP" value="<%= userPO.getType() %>"> 
<INPUT TYPE=HIDDEN name="E01REQPVI" value="<%= userPO.getHeader1() %>"> 
<INPUT TYPE=HIDDEN name="E01REQDF1" value="<%= userPO.getHeader2() %>"> 
<INPUT TYPE=HIDDEN name="E01REQDF2" value="<%= userPO.getHeader3() %>"> 
<INPUT TYPE=HIDDEN name="E01REQDF3" value="<%= userPO.getHeader4() %>"> 
<INPUT TYPE=HIDDEN name="E01REQDT1" value="<%= userPO.getHeader5() %>">
<INPUT TYPE=HIDDEN name="E01REQDT2" value="<%= userPO.getHeader6() %>"> 
<INPUT TYPE=HIDDEN name="E01REQDT3" value="<%= userPO.getHeader7() %>"> 
<INPUT TYPE=HIDDEN name="E01REQDFY" value="<%= userPO.getHeader8() %>"> 
<INPUT TYPE=HIDDEN name="E01REQDFM" value="<%= userPO.getHeader9() %>"> 
<INPUT TYPE=HIDDEN name="E01REQDFD" value="<%= userPO.getHeader10()%>"> 
<INPUT TYPE=HIDDEN name="E01REQDTY" value="<%= userPO.getHeader11()%>"> 
<INPUT TYPE=HIDDEN name="E01REQDTM" value="<%= userPO.getHeader12()%>"> 
<INPUT TYPE=HIDDEN name="E01REQDTD" value="<%= userPO.getHeader13()%>">

<table class="tableinfo" width="100%">
	<tr bordercolor="#FFFFFF">
		<td nowrap>
		<H5>Información Factura</H5>
		<table cellspacing="0" cellpadding="2" width="100%" border="0">
			<tr id="trclear">
				<td nowrap width="25%" align="right"><B>Número Interno : </B></td>
				<td nowrap width="25%" align="left">
					<a href="javascript:goInquiry()"><%=EBP0140Record.getE01BPBNUM()%></a> - <%=EBP0140Record.getE01BPBSEQ()%>
				</td>
				<td nowrap width="25%" align="right"><B>Proveedor : </B></td>
				<td nowrap width="25%" align="left"><%=EBP0140Record.getE01BPBCOD()%> <%=EBP0140Record.getE01BPBVCN()%></td>
			</tr>
			<tr id="trclear">
				<td nowrap width="25%" align="right">
				 	<%if (!EBP0140Record.getE01BPBORD().equals("0")) {
						%> 	<B>Purchase Order : </B> 
					<%}%> 
				<td nowrap width="25%" align="left">
					<%if (!EBP0140Record.getE01BPBORD().equals("0")) {
						%><%=EBP0140Record.getE01BPBORD()%> 
					<%}%>
				</td>
				<td nowrap width="25%" align="right"><B> </B></td>
				<td nowrap width="25%" align="left"></td>
			</tr>
			<tr id="trclear">
				<td nowrap width="25%" align="right"><B>Factura Referencia : </B></td>
				<td nowrap width="25%" align="left"><%=EBP0140Record.getE01BPBBIL()%></td>
				<td nowrap width="25%" align="right"><B>Tipo de Pago : </B></td>
				<td nowrap width="25%" align="left">
					<%if (EBP0140Record.getE01BPBPTY().equals("F"))
						out.print("Monto Fijo");%>
					<%if (EBP0140Record.getE01BPBPTY().equals("V"))
						out.print("Monto Variable");%>
				</td>
			</tr>
			<tr id="trclear">
				<td nowrap width="25%" align="right"><B>Descripción Factura :</B></td>
				<td nowrap width="25%" align="left"><%=EBP0140Record.getE01BPBDSC()%></td>
				<td nowrap width="25%" align="right"><B>Fecha Vencimiento de Pago : </B></td>
				<td nowrap width="25%" align="left">
					<%=datapro.eibs.master.Util.formatDate(EBP0140Record.getE01BPBPDM(), EBP0140Record.getE01BPBPDD(),
							EBP0140Record.getE01BPBPDY())%>
				</td>
			</tr>
			<tr id="trclear">
				<td nowrap width="20%" align="right"><B>Banco : </B></td>
				<td nowrap width="25%" align="left"><%=EBP0140Record.getE01BPBBNK()%></td>
				<td nowrap width="25%" align="right"><B>Moneda : </B><B></B></td>
				<td nowrap width="25%" align="left"><%=EBP0140Record.getE01BPBCCY()%><B></B></td>
			</tr>
			<tr id="trclear">
				<td nowrap width="25%" align="right"><B>Oficina : </B></td>
				<td nowrap width="25%" align="left"><%=EBP0140Record.getE01BPBBRN()%></td>
				<td nowrap width="25%" align="right"><B><B>Monto Factura : </B></B></td>
				<td nowrap width="25%" align="left"><B></B><%=EBP0140Record.getE01BPBBAM()%></td>
			</tr>
			<tr id="trclear">
				<td nowrap width="25%" align="right"><B>Tipo : </B></td>
				<td nowrap width="25%" align="left"><%=EBP0140Record.getE01BPBTYPD()%>
					<% if (userPO.getType().equals("C") )  { 	
								out.println(EBP0140Record.getE01BPBCUN().trim());
			  		}%>
				</td>
				<td nowrap width="25%" align="right"><B>Via de Pago : </B></td>
				<td nowrap width="25%" align="left">
					<%if (EBP0140Record.getE01BPBPVI().equals("A"))
						out.print("ACH");%>
					<%if (EBP0140Record.getE01BPBPVI().equals("R"))
						out.print("Cuenta de Detalle");%>
					<%if (EBP0140Record.getE01BPBPVI().equals("G"))
						out.print("Cuenta Contable");%>
					<%if (EBP0140Record.getE01BPBPVI().equals("C"))
						out.print("Cheque Oficial");%>
					<%if (EBP0140Record.getE01BPBPVI().equals("S"))
						out.print("Swift");%>
					<%if (EBP0140Record.getE01BPBPVI().equals("P"))
					out.print("Caja Menor");%>
				</td>
			</tr>
		</table>

		<BR>

		<h5>Información de Pago</h5>
		<table class="tableinfo" width="100%">
			<tr>
				<td nowrap>
				<table cellspacing="0" cellpadding="2" width="100%" border="0">
					<tr id="trdark">
						<td nowrap width="50%" align="right"><B>Fecha : </B></td>
						<td nowrap width="50%" align="left">
							<INPUT type="text" size="3" maxlength="2" name="E01BPPPDD" onkeypress="enterInteger()" value="<%= EBP0140Record.getE01BPPPDD().trim()%>"> 
							<INPUT type="text" size="3" maxlength="2" name="E01BPPPDM" onkeypress="enterInteger()" value="<%= EBP0140Record.getE01BPPPDM().trim()%>"> 
							<INPUT type="text" size="3" maxlength="2" name="E01BPPPDY" onkeypress="enterInteger()" value="<%= EBP0140Record.getE01BPPPDY().trim()%>"> 
							<A href="javascript:DatePicker(document.forms[0].E01BPPPDD,document.forms[0].E01BPPPDM,document.forms[0].E01BPPPDY)">
								<IMG src="<%=request.getContextPath()%>/images/calendar.gif" alt="Ayuda" align="middle" border="0">
							</A> <B><%=currUser.getE01DTF()%></B>
						</td>
					</tr>
					<tr id="trclear">
						<td nowrap width="50%" align="right"><B>Monto Total : </B></td>
						<td nowrap width="50%" align="left">
							<INPUT type="text" name="E01BPPAMT" size="20" maxlength="19" value="<%= EBP0140Record.getE01BPPAMT().trim()%>"
								onkeypress="enterDecimal()" <% if (EBP0140Record.getE01BPBPTY().equals("F")) out.print(" readonly ");%>>
						</td>
					</tr>
					<tr id="trdark">
						<td nowrap width="50%" align="right"><B> Via : </B></td>
						<td nowrap width="50%" align="left">
							<SELECT name="E01BPPPVI">
								<OPTION <%= EBP0140Record.getE01BPPPVI().trim().equals("A")?"selected":"" %> value="A">ACH</OPTION>
								<OPTION <%= EBP0140Record.getE01BPPPVI().trim().equals("R")?"selected":"" %> value="R">Cuenta Detalle</OPTION>
								<OPTION <%= EBP0140Record.getE01BPPPVI().trim().equals("G")?"selected":"" %> value="G">Cuenta Contable</OPTION>
								<OPTION <%= EBP0140Record.getE01BPPPVI().trim().equals("C")?"selected":"" %> value="C">Cheque Gerencia</OPTION>
								<OPTION <%= EBP0140Record.getE01BPPPVI().trim().equals("S")?"selected":"" %> value="S">Swift</OPTION>
								<OPTION <%= EBP0140Record.getE01BPPPVI().trim().equals("P")?"selected":"" %> value="P">Caja Menor</OPTION>
							</SELECT>
						</td>
					</tr>
					<tr id="trclear">
						<td nowrap width="50%" align="right"><B>Estatus : </B></td>
						<td nowrap width="50%" align="left">
							<SELECT name="E01BPPSTS">
								<OPTION <%= EBP0140Record.getE01BPPSTS().trim().equals("W")?"selected":"" %> value="W">Pendiente</OPTION>
								<OPTION <%= EBP0140Record.getE01BPPSTS().trim().equals("C")?"selected":"" %> value="C">Confirmada</OPTION>
								<OPTION <%= EBP0140Record.getE01BPPSTS().trim().equals("R")?"selected":"" %> value="R">Rechazada</OPTION>
							</SELECT>
						</td>
					</tr>
				</table>
				</td>
			</tr>
		</table>
		<BR>
		<table class="tableinfo" cellspacing="0" cellpadding="2" width="100%"
			border="0">
			<tr id="trdark">
				<td nowrap width="10%" align="center"></td>
				<td nowrap width="10%" align="center"><b>Banco</b></td>
				<td nowrap width="10%" align="center"><b>Agencia</b></td>
				<td nowrap width="10%" align="center"><b>Moneda</b></td>
				<td nowrap width="10%" align="center"><b>Cuenta Contable</b></td>
				<td nowrap width="10%" align="center"><b>Cuenta/Cliente</b></td>
				<td nowrap width="10%" align="center"><b>Centro de Costo</b></td>
				<td nowrap width="10%" align="center"><b>DB/CR</b></td>
				<td nowrap width="20%" align="center"><b>Monto</b></td>
			</tr>
			<tr id="trclear" align="center">
				<td nowrap width="10%" align="right"><b>Base</b></td>
				<td nowrap width="10%" align="center"><%=EBP0140Record.getE01BPGBNK1().trim()%></td>
				<td nowrap width="10%" align="center"><%=EBP0140Record.getE01BPGBRN1().trim()%></td>
				<td nowrap width="10%" align="center"><%=EBP0140Record.getE01BPGCCY1().trim()%></td>
				<td nowrap width="10%" align="center"><%=EBP0140Record.getE01BPGGLN1().trim()%></td>
				<td nowrap width="10%" align="center"><%=EBP0140Record.getE01BPGACC1().trim()%></td>
				<td nowrap width="10%" align="center"><%=EBP0140Record.getE01BPGCCN1().trim()%></td>
				<td nowrap width="10%" align="center"><%=EBP0140Record.getE01BPGDC1().trim()%></td>
				<td nowrap width="20%" align="center">
					<input type="text" name="E01BPGAMT1" maxlength="17" size="20" value="<%= EBP0140Record.getE01BPGAMT1().trim()%>"
						<% if (EBP0140Record.getE01BPBPTY().equals("F") || Double.valueOf(EBP0140Record.getE01BPGAMT1()).doubleValue() == 0) out.print(" readonly ");%>
						onkeypress="enterDecimal()"> 
				</td>
			</tr>
			<tr id="trdark" align="center">
				<td nowrap width="10%" align="right"><b>Tax</b></td>
				<td nowrap width="10%" align="center"><%=EBP0140Record.getE01BPGBNK2().trim()%></td>
				<td nowrap width="10%" align="center"><%=EBP0140Record.getE01BPGBRN2().trim()%></td>
				<td nowrap width="10%" align="center"><%=EBP0140Record.getE01BPGCCY2().trim()%></td>
				<td nowrap width="10%" align="center"><%=EBP0140Record.getE01BPGGLN2().trim()%></td>
				<td nowrap width="10%" align="center"><%=EBP0140Record.getE01BPGACC2().trim()%></td>
				<td nowrap width="10%" align="center"><%=EBP0140Record.getE01BPGCCN2().trim()%></td>
				<td nowrap width="10%" align="center"><%=EBP0140Record.getE01BPGDC2().trim()%></td>
				<td nowrap width="20%" align="center">
					<input type="text" name="E01BPGAMT2 maxlength=" 17" size="20" value="<%= EBP0140Record.getE01BPGAMT2().trim()%>"
						<% if (EBP0140Record.getE01BPBPTY().equals("F") || Double.valueOf(EBP0140Record.getE01BPGAMT2()).doubleValue() == 0) out.print(" readonly ");%>
						onkeypress="enterDecimal()"> 
				</td>
			</tr>
			<tr id="trclear" align="center">
				<td nowrap width="10%" align="right"><b>Other</b></td>
				<td nowrap width="10%" align="center"><%=EBP0140Record.getE01BPGBNK3().trim()%></td>
				<td nowrap width="10%" align="center"><%=EBP0140Record.getE01BPGBRN3().trim()%></td>
				<td nowrap width="10%" align="center"><%=EBP0140Record.getE01BPGCCY3().trim()%></td>
				<td nowrap width="10%" align="center"><%=EBP0140Record.getE01BPGGLN3().trim()%></td>
				<td nowrap width="10%" align="center"><%=EBP0140Record.getE01BPGACC3().trim()%></td>
				<td nowrap width="10%" align="center"><%=EBP0140Record.getE01BPGCCN3().trim()%></td>
				<td nowrap width="10%" align="center"><%=EBP0140Record.getE01BPGDC3().trim()%></td>
				<td nowrap width="20%" align="center">
					<input type="text" name="E01BPGAMT3" maxlength="17" size="20" value="<%= EBP0140Record.getE01BPGAMT3().trim()%>"
						<% if (EBP0140Record.getE01BPBPTY().equals("F") || Double.valueOf(EBP0140Record.getE01BPGAMT3()).doubleValue() == 0) out.print(" readonly ");%> 
						onkeypress="enterDecimal()"> 
				</td>
			</tr>
		</table>
		</td>
	</tr>
</table>

<TABLE width="100%">
	<TR>
		<TD width="50%" align="center"><INPUT id="EIBSBTN" type="submit"
			name="Submit" value="Enviar"></TD>
		<TD width="50%" align="center"><INPUT id="EIBSBTN" type="submit"
			name="Exit" value="Salir" onclick="goExit();"></TD>
	</TR>
</TABLE>

</form>
</body>
</html>
