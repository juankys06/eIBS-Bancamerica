<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.0 Transitional//EN">
<html>
<head>
<title>Apertura de Cobranza Simple</title>
<META HTTP-EQUIV="Content-Type" CONTENT="text/html; charset=ISO-8859-1">
<META name="GENERATOR" content="IBM WebSphere Page Designer V3.5.2 for Windows">
<META http-equiv="Content-Style-Type" content="text/css">
<link Href="<%=request.getContextPath()%>/pages/style.css" rel="stylesheet">


<script language="Javascript1.1" src="<%=request.getContextPath()%>/pages/s/javascripts/eIBS.jsp"> </SCRIPT>
<script language="Javascript1.1" src="<%=request.getContextPath()%>/pages/s/javascripts/optMenu.jsp"> </SCRIPT>

<jsp:useBean id="dcNew"  class="datapro.eibs.beans.EDC001001Message" scope="session" />
<jsp:useBean id="error"  class="datapro.eibs.beans.ELEERRMessage" scope="session" />
<jsp:useBean id="userPO" class="datapro.eibs.beans.UserPos" scope="session" />

<SCRIPT LANGUAGE="javascript">

<%if (!userPO.getPurpose().equals("NEW")) {%>

	builtNewMenu(dc_s_maint);
	initMenu();

<%}%>

   builtHPopUp();

  function showPopUp(opth,field,bank,ccy,field1,field2,opcod) {
   init(opth,field,bank,ccy,field1,field2,opcod);
   showPopupHelp();
   }

</SCRIPT>

<%if (!error.getERRNUM().equals("0")) {
	error.setERRNUM("0");
	out.println("<SCRIPT Language=\"Javascript\">");
	out.println("       showErrors()");
	out.println("</SCRIPT>");
}%>

</head>
<body>
<h3 align="center"><%= dcNew.getE01DCMOPT().equals("N")?"Apertura":"Mantenimiento" %> de Cobranza Simple<img src="<%=request.getContextPath()%>/images/e_ibs.gif" align="left" name="EIBS_GIF" alt="dc_opening.jsp, EDC0010"></h3>
<hr size="4">
<form method="post" action="<%=request.getContextPath()%>/servlet/datapro.eibs.products.JSEDC0010">
<INPUT TYPE=HIDDEN NAME="SCREEN" VALUE="<%if (dcNew.getE01DCMOPT().equals("N"))out.print("2"); else out.print("4");%>">

<table class="tableinfo">
	<tr>
		<td nowrap>
		<TABLE cellspacing="0" cellpadding="2" width="100%" border="0" class="tbhead">
			<TBODY><TR id="trdark">
				<TD nowrap width="16%">
					<DIV align="right"><B>Banco :</B></DIV>
				</TD>
				<TD nowrap width="20%">
					<DIV align="left">
						<INPUT type="text" name="E01DCMBNK" readonly size="4" maxlength="2" value="<%=dcNew.getE01DCMBNK().trim()%>">
					</DIV>
				</TD>
				<TD nowrap width="16%">
					<DIV align="right"><B>Número de Cobranza :</B></DIV>
				</TD>
				<TD nowrap width="16%">
					<DIV align="left"><B>
				<%if (dcNew.getE01DCMACC().trim().equals("999999999999"))
				{%>
					<INPUT type="text" size="12" maxlength="12" value="Nuevo" readonly>
					<INPUT type="hidden" name="E01DCMACC" value="<%=dcNew.getE01DCMACC().trim()%>">
				<%}
				else
				{%>
					<INPUT type="text" name="E01DCMACC" size="12" maxlength="12" value="<%=dcNew.getE01DCMACC().trim()%>" readonly>
				<%}%>
					</B></DIV>
				</TD>
			</TR>
			<TR id="trclear">
				<TD nowrap width="16%">
					<DIV align="right"><B>Nuestra Referencia :</B></DIV>
				</TD>
				<TD nowrap width="20%">
					<DIV align="left">
						<INPUT type="text" name="E01DCMORF" size="20" maxlength="16" value="<%=dcNew.getE01DCMORF().trim()%>">
					</DIV>
				</TD>
				<TD nowrap width="16%">
					<DIV align="right"><B>Producto :</B></DIV>
				</TD>
				<TD nowrap width="16%">
					<DIV align="left"><B>
						<INPUT type="text" name="E01DCMPRO" size="4" maxlength="4" value="<%=dcNew.getE01DCMPRO().trim()%>" readonly>
					</B></DIV>
				</TD>
			</TR>
			<TR id="trdark">
				<TD nowrap width="16%">
					<DIV align="right"><B>Tipo de Cobranza :</B></DIV>
				</TD>
				<TD nowrap width="16%">
					<DIV align="left"><B>
						<INPUT type="text" name="E01DCMTYP" size="20" maxlength="16" value="<%=dcNew.getE01DCMTYP().trim()%>">
					</B></DIV>
				</TD>
				<TD nowrap width="16%">
					<DIV align="right"><B>Descripcion de Producto :</B></DIV>
				</TD>
				<TD nowrap width="16%">
					<DIV align="left"><B>
						<INPUT type="text" name="E01DSCPRO" size="30" maxlength="30" value="<%=dcNew.getE01DSCPRO().trim()%>" readonly>
					</B></DIV>
				</TD>
			</TR>
			</TBODY></TABLE>
		</td>
	</tr>
</table>
<h4>Banco Girado</h4>
<table class="tableinfo">
	<tr>
		<td>
		<table cellspacing="0" cellpadding="2" width="100%" border="0">
			<tr id="trdark">
				<td align="right">
				<table border="0" cellspacing="0">
					<tr id="trdark">
						<td align="right">Numero de Cliente o de Cuenta:</td>
						<td nowrap width="25%">
							<select name="E01DCMAF4">
								<option value=" " <%if (!(dcNew.getE01DCMAF4().equals("C") || dcNew.getE01DCMAF4().equals("A"))) out.print("selected");%> selected></option>
								<option value="C" <%if (dcNew.getE01DCMAF4().equals("C")) out.print("selected");%>>Cliente</option>
								<option value="A" <%if (dcNew.getE01DCMAF4().equals("A")) out.print("selected");%>>Cuenta</option>
							</select>
						</td>
					</tr>
				</table>
				</td>
				<td>
				<table border="0" cellspacing="0">
					<tr id="trdark">
						<td align="left">
							<input type="text" name="E01DCMCLA" size="12" maxlength="12" value="<%=dcNew.getE01DCMCLA()%>">
							<img src="<%=request.getContextPath()%>/images/Check.gif" alt="mandatory field" border="0">
						</td>
						<td>
							<a href="javascript: GetCustomerDetails('E01DCMCLA','E01DCMCL1','','','','E01DCMCL2','E01DCMCL3','E01DCMCL4','','','','','','','','','','')">
							<img src="<%=request.getContextPath()%>/images/1b.gif" alt=". . ." onclick="javascript: document.forms[0].E01DCMAF4.selectedIndex = 1" border="0"></a> Cliente o
							<a href="javascript: GetAccByClient('E01DCMCLA','','RT','','E01DCMCL1')">
							<img src="<%=request.getContextPath()%>/images/1b.gif" alt=". . ." onclick="javascript: document.forms[0].E01DCMAF4.selectedIndex = 2" border="0"></a> Cuenta
						</td>
					</tr>
				</table>
				</td>
			</tr>
			  <TR id="trclear">
			    <TD align="right" nowrap>Codigo Swift:</TD>
			    <TD align="left" nowrap><INPUT type="text" name="E01DCMCLI" size="14" maxlength="12" value="<%=dcNew.getE01DCMCLI()%>">
			      <A href="javascript:GetSwiftIdDesc('E01DCMCLI','','','')"> <IMG src="<%=request.getContextPath()%>/images/1b.gif" alt="Ayuda"  border="0"></A> </TD>
			  </TR>
			<tr id="trdark">
				<td>
					<div align="right">Nombre :</div>
				</td>
				<td>
					<div align="left">
						<input type="text" name="E01DCMCL1" size="45" maxlength="35" value="<%=dcNew.getE01DCMCL1()%>">
					</div>
				</td>
			</tr>
			<tr id="trclear">
				<td>
					<div align="right">Dirección :</div>
				</td>
				<td>
					<div align="left"><input type="text" name="E01DCMCL2" size="45" maxlength="35" value="<%=dcNew.getE01DCMCL2()%>">
				</div>
				</td>
			</tr>
			<tr id="trdark">
				<td>
					<div align="right"></div>
				</td>
				<td>
					<div align="left">
						<input type="text" name="E01DCMCL3" size="45" maxlength="35" value="<%=dcNew.getE01DCMCL3()%>">
					</div>
				</td>
			</tr>
			<tr id="trclear">
				<td>
					<div align="right"></div>
				</td>
				<td>
					<div align="left">
						<input type="text" name="E01DCMCL4" size="45" maxlength="35" value="<%=dcNew.getE01DCMCL4()%>">
					</div>
				</td>
			</tr>
			<tr id="trdark">
				<td>
					<div align="right">Referencia :</div>
				</td>
				<td>
					<div align="left">
						<input type="text" name="E01DCMCRF" size="20" maxlength="16" value="<%=dcNew.getE01DCMCRF()%>">
					</div>
				</td>
			</tr>
		</table>
		</td>
	</tr>
</table>

<h4>Girador del Instrumento</h4>
<table class="tableinfo">
	<tr>
		<td>
		<table cellspacing="0" cellpadding="2" width="100%" border="0">
			<tr id="trdark">
				<td align="right">
				<table border="0" cellspacing="0">
					<tr id="trdark">
						<td align="right">Numero de Cliente o de Cuenta:</td>
						<td nowrap width="25%">
							<select name="E01DCMAF1">
								<option value=" " <%if (!(dcNew.getE01DCMAF1().equals("C") || dcNew.getE01DCMAF1().equals("A"))) out.print("selected");%> selected></option>
								<option value="C" <%if (dcNew.getE01DCMAF1().equals("C")) out.print("selected");%>>Cliente</option>
								<option value="A" <%if (dcNew.getE01DCMAF1().equals("A")) out.print("selected");%>>Cuenta</option>
							</select>
						</td>
					</tr>
				</table>
				</td>
				<td>
				<table border="0" cellspacing="0">
					<tr id="trdark">
						<td align="left">
							<input type="text" name="E01DCMDRA" size="12" maxlength="12" value="<%=dcNew.getE01DCMDRA()%>">
						</td>
						<td>
							<a href="javascript: GetCustomerDetails('E01DCMDRA','E01DCMDR1','','','','E01DCMDR2','E01DCMDR3','E01DCMDR4','','','','','','','','','','')">
							<img src="<%=request.getContextPath()%>/images/1b.gif" alt=". . ." onclick="javascript: document.forms[0].E01DCMAF1.selectedIndex = 1" border="0"></a> Cliente o
							<a href="javascript: GetAccByClient('E01DCMDRA','','RT','','E01DCMDR1')">
							<img src="<%=request.getContextPath()%>/images/1b.gif" alt=". . ." onclick="javascript: document.forms[0].E01DCMAF1.selectedIndex = 2" border="0"></a> Cuenta
						</td>
					</tr>
				</table>
				</td>
			</tr>
			<tr id="trclear">
				<td>
					<div align="right">Nombre :</div>
				</td>
				<td>
					<div align="left">
						<input type="text" name="E01DCMDR1" size="45" maxlength="35" value="<%=dcNew.getE01DCMDR1()%>">
						<IMG src="<%=request.getContextPath()%>/images/Check.gif" alt="campo obligatorio" border="0">
					</div>
				</td>
			</tr>
			<tr id="trdark">
				<td>
					<div align="right">Dirección :</div>
				</td>
				<td>
					<div align="left">
						<input type="text" name="E01DCMDR2" size="45" maxlength="35" value="<%=dcNew.getE01DCMDR2()%>">
					</div>
				</td>
			</tr>
			<tr id="trclear">
				<td>
					<div align="right"></div>
				</td>
				<td>
					<div align="left">
						<input type="text" name="E01DCMDR3" size="45" maxlength="35" value="<%=dcNew.getE01DCMDR3()%>">
					</div>
				</td>
			</tr>
			<tr id="trdark">
				<td>
					<div align="right"></div>
				</td>
				<td>
					<div align="left">
						<input type="text" name="E01DCMDR4" size="45" maxlength="35" value="<%=dcNew.getE01DCMDR4()%>">
					</div>
				</td>
			</tr>
			<tr id="trclear">
				<td>
					<div align="right">Referencia :</div>
				</td>
				<td>
					<div align="left">
						<input type="text" name="E01DCMDRF" size="20" maxlength="16" value="<%=dcNew.getE01DCMDRF()%>">
					</div>
				</td>
			</tr>
		</table>
		</td>
	</tr>
</table>

<h4>Cliente / Beneficiario</h4>
<table class="tableinfo">
	<tr>
		<td>
		<TABLE cellspacing="0" cellpadding="2" width="100%" border="0">
			<TBODY><TR id="trdark">
				<TD align="right">
				<TABLE border="0" cellspacing="0">
					<TBODY><TR id="trdark">
						<TD align="right">Numero de Cliente o de Cuenta:</TD>
						<TD nowrap width="25%"><SELECT name="E01DCMAF3">
							<OPTION value=" "></OPTION>
							<OPTION value="C" <%if (dcNew.getE01DCMAF3().equals("C")) out.print("selected");%>>Cliente</OPTION>
							<OPTION value="A" <%if (dcNew.getE01DCMAF3().equals("A")) out.print("selected");%>>Cuenta</OPTION>
						</SELECT></TD>
					</TR>
				</TBODY></TABLE>
				</TD>
				<TD>
				<TABLE border="0" cellspacing="0">
					<TBODY>
						<TR id="trdark">
							<TD align="left">
								<INPUT type="text" name="E01DCMRBA" size="12" maxlength="12" value="<%=dcNew.getE01DCMRBA()%>">
							</TD>
							<TD>
								<A href="javascript: GetCustomerDetails('E01DCMRBA','E01DCMRB1','','','','E01DCMRB2','E01DCMRB3','E01DCMRB4','','','','','','','','','','')">
								<IMG src="<%=request.getContextPath()%>/images/1b.gif" alt="Ayuda" onclick="javascript: document.forms[0].E01DCMAF3.selectedIndex = 1" align="bottom" border="0"></A> Cliente o
								<A href="javascript: GetAccByClient('E01DCMRBA','','RT','','E01DCMRB1')">
								<IMG src="<%=request.getContextPath()%>/images/1b.gif" alt=". . ." onclick="javascript: document.forms[0].E01DCMAF3.selectedIndex = 2" border="0"></A> Cuenta
							</TD>
						</TR>
					</TBODY>
				</TABLE>
				</TD>
			</TR>
			<TR id="trclear">
				<TD>
					<DIV align="right">Nombre :</DIV>
				</TD>
				<TD>
					<DIV align="left">
						<INPUT type="text" name="E01DCMRB1" size="45" maxlength="35" value="<%=dcNew.getE01DCMRB1()%>">
					</DIV>
				</TD>
			</TR>
			<TR id="trdark">
				<TD>
					<DIV align="right">Dirección :</DIV>
				</TD>
				<TD>
					<DIV align="left">
						<INPUT type="text" name="E01DCMRB2" size="45" maxlength="35" value="<%=dcNew.getE01DCMRB2()%>">
					</DIV>
				</TD>
			</TR>
			<TR id="trclear">
				<TD>
					<DIV align="right"></DIV>
				</TD>
				<TD>
					<DIV align="left">
						<INPUT type="text" name="E01DCMRB3" size="45" maxlength="35" value="<%=dcNew.getE01DCMRB3()%>">
					</DIV>
				</TD>
			</TR>
			<TR id="trdark">
				<TD>
					<DIV align="right"></DIV>
				</TD>
				<TD>
					<DIV align="left">
						<INPUT type="text" name="E01DCMRB4" size="45" maxlength="35" value="<%=dcNew.getE01DCMRB4()%>">
					</DIV>
				</TD>
			</TR>
			<TR id="trclear">
				<TD>
					<DIV align="right">Referencia :</DIV>
				</TD>
				<TD>
					<DIV align="left">
						<INPUT type="text" name="E01DCMRRF" size="20" maxlength="16" value="<%=dcNew.getE01DCMRRF()%>">
					</DIV>
				</TD>
			</TR>
		</TBODY></TABLE>
		</td>
	</tr>
</table>

<h4>Banco Reembolsador</h4>
<table class="tableinfo">
	<tr>
		<td>
		<table cellspacing="0" cellpadding="2" width="100%" border="0">
			<tr id="trdark">
				<td align="right">
				<table border="0" cellspacing="0">
					<tr id="trdark">
						<td align="right">Numero de Cliente o de Cuenta:</td>
						<td nowrap width="25%">
							<select name="E01DCMAF2">
								<option value=" " <%if (!(dcNew.getE01DCMAF2().equals("C") || dcNew.getE01DCMAF2().equals("A"))) out.print("selected");%> selected></option>
								<option value="C" <%if (dcNew.getE01DCMAF2().equals("C")) out.print("selected");%>>Cliente</option>
								<option value="A" <%if (dcNew.getE01DCMAF2().equals("A")) out.print("selected");%>>Cuenta</option>
							</select>
						</td>
					</tr>
				</table>
				</td>
				<td>
				<table border="0" cellspacing="0">
					<tr id="trdark">
						<td align="left">
							<input type="text" name="E01DCMDWA" size="12" maxlength="12" value="<%=dcNew.getE01DCMDWA()%>">
						</td>
						<td>
							<a href="javascript: GetCustomerDetails('E01DCMDWA','E01DCMDW1','','','','E01DCMDW2','E01DCMDW3','E01DCMDW4','','','','','','','','','','')">
							<img src="<%=request.getContextPath()%>/images/1b.gif" alt=". . ." onclick="javascript: document.forms[0].E01DCMAF2.selectedIndex = 1" border="0"></a> Cliente o
							<a href="javascript: GetAccByClient('E01DCMDWA','','RT','','E01DCMDW1')">
							<img src="<%=request.getContextPath()%>/images/1b.gif" alt=". . ." onclick="javascript: document.forms[0].E01DCMAF2.selectedIndex = 2" border="0"></a> Cuenta
						</td>
					</tr>
				</table>
				</td>
			</tr>
			<tr id="trclear">
				<td>
					<div align="right">Nombre :</div>
				</td>
				<td>
					<div align="left">
						<input type="text" name="E01DCMDW1" size="45" maxlength="35" value="<%=dcNew.getE01DCMDW1()%>">
						<IMG src="<%=request.getContextPath()%>/images/Check.gif" alt="campo obligatorio" border="0">
					</div>
				</td>
			</tr>
			<tr id="trdark">
				<td>
					<div align="right">Dirección :</div>
				</td>
				<td>
					<div align="left">
						<input type="text" name="E01DCMDW2" size="45" maxlength="35" value="<%=dcNew.getE01DCMDW2()%>">
					</div>
				</td>
			</tr>
			<tr id="trclear">
				<td>
					<div align="right"></div>
				</td>
				<td>
					<div align="left">
						<input type="text" name="E01DCMDW3" size="45" maxlength="35" value="<%=dcNew.getE01DCMDW3()%>">
					</div>
				</td>
			</tr>
			<tr id="trdark">
				<td>
					<div align="right"></div>
				</td>
				<td>
					<div align="left">
						<input type="text" name="E01DCMDW4" size="45" maxlength="35" value="<%=dcNew.getE01DCMDW4()%>">
					</div>
				</td>
			</tr>
		</table>
		</td>
	</tr>
</table>

  <h4>Información de la Cobranza</h4>
  <table class="tableinfo">
    <tr bordercolor="#FFFFFF">
      <td nowrap>
        <TABLE cellspacing="0" cellpadding="2" width="100%" border="0">
          <TBODY>
          <tr id="trdark">
			<td nowrap width="30%">
             	<div align="right">Fecha de Emisión :</div>
			</td>
            <td nowrap width="20%">
            	<INPUT type="text" name="E01DCMID1" maxlength="2" size="3" value="<%= dcNew.getE01DCMID1().trim()%>" onKeyPress="enterInteger()">
            	<INPUT type="text" name="E01DCMID2" maxlength="2" size="3" value="<%= dcNew.getE01DCMID2().trim()%>" onKeyPress="enterInteger()">
            	<INPUT type="text" name="E01DCMID3" maxlength="2" size="3" value="<%= dcNew.getE01DCMID3().trim()%>" onKeyPress="enterInteger()">
                <A href="javascript:DatePicker(document.forms[0].E01DCMID1,document.forms[0].E01DCMID2,document.forms[0].E01DCMID3)"><IMG src="<%=request.getContextPath()%>/images/calendar.gif" alt="help" border="0"></A>
            </td>
			<td nowrap width="30%">
             	<div align="right">Próxima Fecha de Rastreo :</div>
			</td>
            <td nowrap width="20%">
            	<INPUT type="text" name="E01DCMNT1" maxlength="2" size="3" value="<%= dcNew.getE01DCMNT1().trim()%>" onKeyPress="enterInteger()">
            	<INPUT type="text" name="E01DCMNT2" maxlength="2" size="3" value="<%= dcNew.getE01DCMNT2().trim()%>" onKeyPress="enterInteger()">
            	<INPUT type="text" name="E01DCMNT3" maxlength="2" size="3" value="<%= dcNew.getE01DCMNT3().trim()%>" onKeyPress="enterInteger()">
                <A href="javascript:DatePicker(document.forms[0].E01DCMNT1,document.forms[0].E01DCMNT2,document.forms[0].E01DCMNT3)"><IMG src="<%=request.getContextPath()%>/images/calendar.gif" alt="help" border="0"></A>
            </td>
          </tr>
          <TR id="trclear">
            <TD nowrap width="30%">
             	<DIV align="right">Tasa de Cambio Mda. Extranjera :</DIV>
			</TD>
			<TD nowrap width="20%">
            	<INPUT type="text" name="E01DCMOFX" maxlength="19" size="20" value="<%= dcNew.getE01DCMOFX().trim()%>" onkeypress="enterDecimal()">
				<IMG src="<%=request.getContextPath()%>/images/Check.gif" alt="campo obligatorio" border="0">
            </TD>
			<TD nowrap width="30%"><DIV align="right">Centro de Costo :</DIV>
            </TD>
            <TD nowrap width="20%">
            	<A href="javascript:GetCurrency('E01DCMFCY','')"></A>
			<INPUT type="text" name="E01DCMCCN" maxlength="9" size="10"
						value="<%= dcNew.getE01DCMCCN().trim()%>"><A
						href="javascript:GetCostCenter('E01DCMCCN',document.forms[0].E01DCMBNK.value)"><IMG
						src="<%=request.getContextPath()%>/images/1b.gif" alt="Ayuda"
						border="0"></A></TD>
          </TR>
          <TR id="trdark">
            <TD nowrap width="30%">
             	<DIV align="right">Número del Cheque :</DIV>
			</TD>
			<TD nowrap width="20%">
            	<INPUT type="text" name="E01DCMCKN" maxlength="10" size="11" value="<%= dcNew.getE01DCMCKN().trim()%>" onkeypress="enterInteger()">
				<IMG src="<%=request.getContextPath()%>/images/Check.gif" alt="campo obligatorio" border="0">
            </TD>
			<TD nowrap width="30%">
             	<DIV align="right">N° de Tarifa de Cargos :</DIV>
            </TD>
            <TD nowrap width="20%">


			<INPUT type="text" name="E01DCMTAR" maxlength="3" size="4"
						value="<%= dcNew.getE01DCMTAR().trim()%>"><A
						href="javascript:GetTariffColl('E01DCMTAR','<%=dcNew.getE01DCMATY()%>','<%=dcNew.getE01DCMCUN()%>')"><IMG
						src="<%=request.getContextPath()%>/images/1b.gif" alt="Ayuda" border="0"></A><IMG
						src="<%=request.getContextPath()%>/images/Check.gif" alt="campo obligatorio" border="0"></TD>
          </TR>
          <TR id="trclear">
            <TD nowrap width="30%">
             	<DIV align="right">Monto del Cheque :</DIV>
			</TD>
			<TD nowrap width="20%">
            	<INPUT type="text" name="E01DCMOAM" maxlength="19" size="20" value="<%= dcNew.getE01DCMOAM().trim()%>" onkeypress="enterDecimal()" <%= !dcNew.getE01DCMOPT().equals("N")?"readonly":"" %>>
				<IMG src="<%=request.getContextPath()%>/images/Check.gif" alt="campo obligatorio" border="0">
            </TD>
			<TD nowrap width="30%">
             	<DIV align="right">Tracers Vía Swift :</DIV>
			</TD>
            <TD nowrap width="20%">
            	<INPUT type="radio" name="E01DCMTVS" value="Y" <%if(dcNew.getE01DCMTVS().equals("Y")) out.print("checked");%>>Sí
            	<INPUT type="radio" name="E01DCMTVS" value="N" <%if(dcNew.getE01DCMTVS().equals("N")) out.print("checked");%>>No
            </TD>
          </TR>
          <TR id="trdark">
            <TD nowrap width="30%">
             	<DIV align="right">Cargos Nuestros x Cta. del :</DIV>
            </TD>
            <TD nowrap width="20%">
				<SELECT name="E01DCMOCF">
					<OPTION value=" " <% if ((!dcNew.getE01DCMOCF().equals("D")) && (!dcNew.getE01DCMOCF().equals("P")) && (!dcNew.getE01DCMOCF().equals("N"))) out.println("selected"); %>></OPTION>
					<OPTION value="D" <% if (dcNew.getE01DCMOCF().equals("D")) out.println("selected"); %>>Girado/Importador</OPTION>
					<OPTION value="P" <% if (dcNew.getE01DCMOCF().equals("P")) out.println("selected"); %>>Girador/Exportador</OPTION>
					<OPTION value="N" <% if (dcNew.getE01DCMOCF().equals("N")) out.println("selected"); %>>Sin Cargos</OPTION>
				</SELECT>
				<IMG src="<%=request.getContextPath()%>/images/Check.gif" alt="campo obligatorio" border="0">
			</TD>
			<TD nowrap width="30%">
             	<DIV align="right">Avisar No Pago Vía :</DIV>
			</TD>
			<TD nowrap width="20%">
				<SELECT name="E01DCMADF">
					<OPTION value=" " <% if ((!dcNew.getE01DCMADF().equals("T")) && (!dcNew.getE01DCMADF().equals("S")) && (!dcNew.getE01DCMADF().equals("A")) && (!dcNew.getE01DCMADF().equals("C")) && (!dcNew.getE01DCMADF().equals("F"))) out.println("selected"); %>></OPTION>
					<OPTION value="T" <% if (dcNew.getE01DCMADF().equals("T")) out.println("selected"); %>>Télex</OPTION>
					<OPTION value="S" <% if (dcNew.getE01DCMADF().equals("S")) out.println("selected"); %>>Swift</OPTION>
					<OPTION value="A" <% if (dcNew.getE01DCMADF().equals("A")) out.println("selected"); %>>Correo Aéreo</OPTION>
					<OPTION value="C" <% if (dcNew.getE01DCMADF().equals("C")) out.println("selected"); %>>Mensaje</OPTION>
					<OPTION value="F" <% if (dcNew.getE01DCMADF().equals("F")) out.println("selected"); %>>Fax</OPTION>
				</SELECT>
				<IMG src="<%=request.getContextPath()%>/images/Check.gif" alt="campo obligatorio" border="0">
            </TD>
          </TR>
		  <TR id="trclear">
			<TD nowrap width="30%">
             	<DIV align="right">Cargos del Otro Banco x Cta. del :</DIV>
			</TD>
			<TD nowrap width="20%">
				<SELECT name="E01DCMOBF">
					<OPTION value=" " <% if ((!dcNew.getE01DCMOBF().equals("D")) && (!dcNew.getE01DCMOBF().equals("P"))) out.println("selected"); %>></OPTION>
					<OPTION value="D" <% if (dcNew.getE01DCMOBF().equals("D")) out.println("selected"); %>>Girado/Importador</OPTION>
					<OPTION value="P" <% if (dcNew.getE01DCMOBF().equals("P")) out.println("selected"); %>>Girador/Exportador</OPTION>
				</SELECT>
				<IMG src="<%=request.getContextPath()%>/images/Check.gif" alt="campo obligatorio" border="0">
            </TD>
			<TD nowrap width="30%">
             	<DIV align="right">Avisar Pago Vía :</DIV>
			</TD>
			<TD nowrap width="20%">
				<SELECT name="E01DCMAPF">
					<OPTION value=" " <% if ((!dcNew.getE01DCMAPF().equals("T")) && (!dcNew.getE01DCMAPF().equals("S")) && (!dcNew.getE01DCMAPF().equals("A")) && (!dcNew.getE01DCMAPF().equals("C")) && (!dcNew.getE01DCMAPF().equals("F"))) out.println("selected"); %>></OPTION>
					<OPTION value="T" <% if (dcNew.getE01DCMAPF().equals("T")) out.println("selected"); %>>Télex</OPTION>
					<OPTION value="S" <% if (dcNew.getE01DCMAPF().equals("S")) out.println("selected"); %>>Swift</OPTION>
					<OPTION value="A" <% if (dcNew.getE01DCMAPF().equals("A")) out.println("selected"); %>>Correo Aéreo</OPTION>
					<OPTION value="C" <% if (dcNew.getE01DCMAPF().equals("C")) out.println("selected"); %>>Mensaje</OPTION>
					<OPTION value="F" <% if (dcNew.getE01DCMAPF().equals("F")) out.println("selected"); %>>Fax</OPTION>
				</SELECT>
            </TD>
		  </TR>
		  <TR id="trdark">
            <TD nowrap width="30%"><DIV align="right"><DIV align="right"></DIV>
					</DIV>
            </TD>
            <TD nowrap width="20%">
				<A href="javascript:GetCustomer('E01DCMLNR')"></A></TD>
			<TD nowrap width="30%">
             	<DIV align="right">Estado de la Cobranza :</DIV>
			</TD>
			<TD nowrap width="20%">



            <SELECT name="E01DCMCST">
						<OPTION value=" "
							<% if ((!dcNew.getE01DCMCST().equals("0")) && (!dcNew.getE01DCMCST().equals("1")) && (!dcNew.getE01DCMCST().equals("2")) && (!dcNew.getE01DCMCST().equals("3")) && (!dcNew.getE01DCMCST().equals("4")) && (!dcNew.getE01DCMCST().equals("5")) && (!dcNew.getE01DCMCST().equals("6")) && (!dcNew.getE01DCMCST().equals("7")) && (!dcNew.getE01DCMCST().equals("8")) && (!dcNew.getE01DCMCST().equals("9")) && (!dcNew.getE01DCMCST().equals("P")) && (!dcNew.getE01DCMCST().equals("V")))%>></OPTION>
						<OPTION value="0"
							<% if (dcNew.getE01DCMCST().equals("0")) out.println("selected"); %>>Esperando
						Confirmación Recibida</OPTION>
						<OPTION value="1"
							<% if (dcNew.getE01DCMCST().equals("1")) out.println("selected"); %>>Pendiente
						de Aceptación</OPTION>
						<OPTION value="2"
							<% if (dcNew.getE01DCMCST().equals("2")) out.println("selected"); %>>Pendiente
						de Pago</OPTION>
						<OPTION value="3"
							<% if (dcNew.getE01DCMCST().equals("3")) out.println("selected"); %>>Aceptada</OPTION>
						<OPTION value="4"
							<% if (dcNew.getE01DCMCST().equals("4")) out.println("selected"); %>>Protestada
						Vigente</OPTION>
						<OPTION value="5"
							<% if (dcNew.getE01DCMCST().equals("5")) out.println("selected"); %>>Prorrogada
						Extendida</OPTION>
						<OPTION value="6"
							<% if (dcNew.getE01DCMCST().equals("6")) out.println("selected"); %>>Cancelada
						- Libre de Pago</OPTION>
						<OPTION value="7"
							<% if (dcNew.getE01DCMCST().equals("7")) out.println("selected"); %>>Cancelada
						- Devuelta</OPTION>
						<OPTION value="8"
							<% if (dcNew.getE01DCMCST().equals("8")) out.println("selected"); %>>Cerrada
						Parcialmente Pagada</OPTION>
						<OPTION value="9"
							<% if (dcNew.getE01DCMCST().equals("9")) out.println("selected"); %>>Cerrada
						Totalmente Pagada</OPTION>
						<OPTION value="P"
							<% if (dcNew.getE01DCMCST().equals("P")) out.println("selected"); %>>Vencida
						Protestada</OPTION>
						<OPTION value="V"
							<% if (dcNew.getE01DCMCST().equals("V")) out.println("selected"); %>>Vencida
						Sin Protestar</OPTION>
					</SELECT></TD>
          </TR>
         <% if(!dcNew.getE01DCMOPT().equals("N")){ %>
		  <TR id="trclear">
			<TD nowrap width="30%">
             	<DIV align="right">Aumento/Disminucion de Saldos :</DIV>
			</TD>
			<TD nowrap width="20%">
				<SELECT name="E01DCMOPT">
					<OPTION value=" " <% if (!dcNew.getE01DCMOPT().equals("I") && !dcNew.getE01DCMOPT().equals("D")) out.println("selected"); %>></OPTION>
					<OPTION value="I" <%= dcNew.getE01DCMOPT().equals("I")?"selected":"" %>>Aumento</OPTION>
					<OPTION value="D" <%= dcNew.getE01DCMOPT().equals("D")?"selected":"" %>>Disminucion</OPTION>
				</SELECT>
                <INPUT type="text" name="E01AMTOPT" maxlength="16" size="17" value="<%= dcNew.getE01AMTOPT()%>" onkeypress="enterDecimal()">
            </TD>
			<TD nowrap width="30%"></TD>
			<TD nowrap width="20%"></TD>
		  </TR>
		  <% } %>

        </TBODY></TABLE>
      </td>
    </tr>
  </table>
<BR>

<DIV align="center">
	<H4 style="text-align:center">
		<INPUT type="checkbox" name="H01FLGWK2" value="A" <% if(dcNew.getH01FLGWK2().equals("A")){ out.print("checked");} %>>Aceptar con Advertencias
	</H4>
	<INPUT id="EIBSBTN" type=submit name="Submit" value="Enviar">
</DIV>

</form>
</body>
</html>
