<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.0 Transitional//EN">
<html>
<head>
<title>Apertura de Cobranza Documentaria</title>
<META HTTP-EQUIV="Content-Type" CONTENT="text/html; charset=ISO-8859-1">
<META name="GENERATOR" content="IBM WebSphere Page Designer V3.5.2 for Windows">
<META http-equiv="Content-Style-Type" content="text/css">
<link Href="<%=request.getContextPath()%>/pages/style.css" rel="stylesheet">


<script language="Javascript1.1" src="<%=request.getContextPath()%>/pages/s/javascripts/eIBS.jsp"> </SCRIPT>
<script language="Javascript1.1" src="<%=request.getContextPath()%>/pages/s/javascripts/optMenu.jsp"> </SCRIPT>

<jsp:useBean id="dcNew"  class="datapro.eibs.beans.EDC000001Message" scope="session" />
<jsp:useBean id="error"  class="datapro.eibs.beans.ELEERRMessage" scope="session" />
<jsp:useBean id="userPO" class="datapro.eibs.beans.UserPos" scope="session" />

<SCRIPT LANGUAGE="javascript">

<%if (!userPO.getPurpose().equals("NEW")) {%>

	builtNewMenu(dc_d_maint);

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
}

if (!userPO.getPurpose().equals("NEW")) {
	out.println("<SCRIPT> initMenu();  </SCRIPT>");
}
%>

</head>
<body>
<h3 align="center"><%= dcNew.getE01DCMOPT().equals("N")?"Apertura":"Mantenimiento" %> de Cobranza Documentaria<img src="<%=request.getContextPath()%>/images/e_ibs.gif" align="left" name="EIBS_GIF" alt="dc_opening.jsp, EDC0000"></h3>
<hr size="4">
<form method="post" action="<%=request.getContextPath()%>/servlet/datapro.eibs.products.JSEDC0000">
<INPUT TYPE=HIDDEN NAME="SCREEN" VALUE="<%if (userPO.getPurpose().equals("NEW"))out.print("2"); else out.print("4");%>">

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
					<DIV align="right"><B>Número de Cobranza   :</B></DIV>
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


<h4>Girador (Exportador)</h4>
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
						<IMG src="<%=request.getContextPath()%>/images/Check.gif" alt="campo obligatorio"  border="0">
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
			<% if(dcNew.getE01DCMTYP().trim().equals("O")){%>
			<tr id="trdark">
				<td>
					<div align="right">Documento : </div>
				</td>
				<td>
					<div align="left">
						<input type="text" name="E01DSC101" size="45" maxlength="35" value="<%=dcNew.getE01DSC101()%>">
					</div>
				</td>
			</tr>
			<% } %>
		</table>
		</td>
	</tr>
</table>

<h4>Girado (Importador)</h4>
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
						<IMG src="<%=request.getContextPath()%>/images/Check.gif" alt="campo obligatorio"  border="0">
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
			<% if(dcNew.getE01DCMTYP().trim().equals("I")){%>
			<tr id="trclear">
				<td>
					<div align="right">Documento : </div>
				</td>
				<td>
					<div align="left">
						<input type="text" name="E01DSC101" size="45" maxlength="35" value="<%=dcNew.getE01DSC101()%>">
					</div>
				</td>
			</tr>
			<% } %>
		</table>
		</td>
	</tr>
</table>
<% if(dcNew.getE01DCMTYP().trim().equals("I")){ %>
<h4>Banco Remitente</h4>
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
			    <TD align="right" nowrap>Codigo Swift:</TD>
			    <TD align="left" nowrap><INPUT type="text" name="E01DCMRBI" size="14" maxlength="12" value="<%=dcNew.getE01DCMRBI()%>">
			      <A href="javascript:GetSwiftIdDesc('E01DCMRBI','','','')"> <IMG src="<%=request.getContextPath()%>/images/1b.gif" alt="Ayuda"  border="0"></A> </TD>
			  </TR>
			<TR id="trdark">
				<TD>
					<DIV align="right">Nombre :</DIV>
				</TD>
				<TD>
					<DIV align="left">
						<INPUT type="text" name="E01DCMRB1" size="45" maxlength="35" value="<%=dcNew.getE01DCMRB1()%>">
					</DIV>
				</TD>
			</TR>
			<TR id="trclear">
				<TD>
					<DIV align="right">Dirección :</DIV>
				</TD>
				<TD>
					<DIV align="left">
						<INPUT type="text" name="E01DCMRB2" size="45" maxlength="35" value="<%=dcNew.getE01DCMRB2()%>">
					</DIV>
				</TD>
			</TR>
			<TR id="trdark">
				<TD>
					<DIV align="right"></DIV>
				</TD>
				<TD>
					<DIV align="left">
						<INPUT type="text" name="E01DCMRB3" size="45" maxlength="35" value="<%=dcNew.getE01DCMRB3()%>">
					</DIV>
				</TD>
			</TR>
			<TR id="trclear">
				<TD>
					<DIV align="right"></DIV>
				</TD>
				<TD>
					<DIV align="left">
						<INPUT type="text" name="E01DCMRB4" size="45" maxlength="35" value="<%=dcNew.getE01DCMRB4()%>">
					</DIV>
				</TD>
			</TR>
			<TR id="trdark">
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
<% } %>
<h4>Banco <%= dcNew.getE01DCMTYP().trim().equals("I")?"Reembolsador":"Presentador" %> </h4>
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

  <h4>Información de Fechas</h4>
  <table class="tableinfo">
    <tr bordercolor="#FFFFFF">
      <td nowrap>
        <table cellspacing="0" cellpadding="2" width="100%" border="0">
          <tr id="trdark">
            <td nowrap width="30%">
             	<div align="right">Fecha de Vencimiento :</div>
            </td>
            <td nowrap width="20%">
            	<INPUT type="text" name="E01DCMMA1" maxlength="2" size="3" value="<%= dcNew.getE01DCMMA1().trim()%>" onKeyPress="enterInteger()">
            	<INPUT type="text" name="E01DCMMA2" maxlength="2" size="3" value="<%= dcNew.getE01DCMMA2().trim()%>" onKeyPress="enterInteger()">
            	<INPUT type="text" name="E01DCMMA3" maxlength="2" size="3" value="<%= dcNew.getE01DCMMA3().trim()%>" onKeyPress="enterInteger()">
                <A href="javascript:DatePicker(document.forms[0].E01DCMMA1,document.forms[0].E01DCMMA2,document.forms[0].E01DCMMA3)"><IMG src="<%=request.getContextPath()%>/images/calendar.gif" alt="help" border="0"></A>
				<IMG src="<%=request.getContextPath()%>/images/Check.gif" alt="campo obligatorio"  border="0">
			</td>
			<td nowrap width="30%">
             	<div align="right">Fecha de Emisión :</div>
			</td>
            <td nowrap width="20%">
            	<INPUT type="text" name="E01DCMID1" maxlength="2" size="3" value="<%= dcNew.getE01DCMID1().trim()%>" onKeyPress="enterInteger()">
            	<INPUT type="text" name="E01DCMID2" maxlength="2" size="3" value="<%= dcNew.getE01DCMID2().trim()%>" onKeyPress="enterInteger()">
            	<INPUT type="text" name="E01DCMID3" maxlength="2" size="3" value="<%= dcNew.getE01DCMID3().trim()%>" onKeyPress="enterInteger()">
                <A href="javascript:DatePicker(document.forms[0].E01DCMID1,document.forms[0].E01DCMID2,document.forms[0].E01DCMID3)"><IMG src="<%=request.getContextPath()%>/images/calendar.gif" alt="help" border="0"></A>
            </td>
          </tr>
          <tr id="trclear">
            <td nowrap width="30%">
             	<div align="right">Fecha de Remitente :</div>
            </td>
            <td nowrap width="20%">
            	<INPUT type="text" name="E01DCMRM1" maxlength="2" size="3" value="<%= dcNew.getE01DCMRM1().trim()%>" onKeyPress="enterInteger()">
            	<INPUT type="text" name="E01DCMRM2" maxlength="2" size="3" value="<%= dcNew.getE01DCMRM2().trim()%>" onKeyPress="enterInteger()">
            	<INPUT type="text" name="E01DCMRM3" maxlength="2" size="3" value="<%= dcNew.getE01DCMRM3().trim()%>" onKeyPress="enterInteger()">
                <A href="javascript:DatePicker(document.forms[0].E01DCMRM1,document.forms[0].E01DCMRM2,document.forms[0].E01DCMRM3)"><IMG src="<%=request.getContextPath()%>/images/calendar.gif" alt="help" border="0"></A>
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
          <tr id="trdark">
            <td nowrap width="30%">
             	<div align="right">Fecha de Acuse de Recibo :</div>
            </td>
            <td nowrap width="20%">
            	<INPUT type="text" name="E01DCMAK1" maxlength="2" size="3" value="<%= dcNew.getE01DCMAK1().trim()%>" onKeyPress="enterInteger()">
            	<INPUT type="text" name="E01DCMAK2" maxlength="2" size="3" value="<%= dcNew.getE01DCMAK2().trim()%>" onKeyPress="enterInteger()">
            	<INPUT type="text" name="E01DCMAK3" maxlength="2" size="3" value="<%= dcNew.getE01DCMAK3().trim()%>" onKeyPress="enterInteger()">
                <A href="javascript:DatePicker(document.forms[0].E01DCMAK1,document.forms[0].E01DCMAK2,document.forms[0].E01DCMAK3)"><IMG src="<%=request.getContextPath()%>/images/calendar.gif" alt="help" border="0"></A>
			</td>
			<td nowrap width="30%">
             	<div align="right">Fecha de Aceptación :</div>
			</td>
            <td nowrap width="20%">
            	<INPUT type="text" name="E01DCMAC1" maxlength="2" size="3" value="<%= dcNew.getE01DCMAC1().trim()%>" onKeyPress="enterInteger()">
            	<INPUT type="text" name="E01DCMAC2" maxlength="2" size="3" value="<%= dcNew.getE01DCMAC2().trim()%>" onKeyPress="enterInteger()">
            	<INPUT type="text" name="E01DCMAC3" maxlength="2" size="3" value="<%= dcNew.getE01DCMAC3().trim()%>" onKeyPress="enterInteger()">
                <A href="javascript:DatePicker(document.forms[0].E01DCMAC1,document.forms[0].E01DCMAC2,document.forms[0].E01DCMAC3)"><IMG src="<%=request.getContextPath()%>/images/calendar.gif" alt="help" border="0"></A>
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
          <TR id="trdark">
            <TD nowrap width="30%">
             	<DIV align="right">Oficina :</DIV>
			</TD><TD nowrap width="20%">
			    <% if(!dcNew.getE01DCMOPT().equals("N")){ %>
			    <INPUT type="text" name="E01DCMBRN" maxlength="3" size="3" value="<%= dcNew.getE01DCMBRN().trim()%>" readonly>
            	<% } else { %>	
            	<INPUT type="text" name="E01DCMBRN" maxlength="3" size="3" value="<%= dcNew.getE01DCMBRN().trim()%>">
            	<A href="javascript:GetBranch('E01DCMBRN',document.forms[0].E01DCMBNK.value,'')">
					<IMG src="<%=request.getContextPath()%>/images/1b.gif" alt="Ayuda" align="bottom" border="0">
					<IMG src="<%=request.getContextPath()%>/images/Check.gif" alt="campo obligatorio" align="bottom" border="0"></A> 
            	<% } %>	
            </TD>
            <TD nowrap width="30%">
             	<DIV align="right">Centro de Costo :</DIV>
            </TD><TD nowrap width="20%">
            	<INPUT type="text" name="E01DCMCCN" maxlength="9" size="10" value="<%= dcNew.getE01DCMCCN().trim()%>">
            	<A href="javascript:GetCostCenter('E01DCMCCN',document.forms[0].E01DCMBNK.value)"><IMG src="<%=request.getContextPath()%>/images/1b.gif" alt="Ayuda" border="0"></A>
			</TD>
            </TR>
          <TR id="trclear">
            <TD nowrap width="30%">
             	<DIV align="right">Moneda :</DIV>
            </TD><TD nowrap width="20%">
            	<INPUT type="text" name="E01DCMCCY" maxlength="3" size="3" value="<%= dcNew.getE01DCMCCY().trim()%>" readonly>
            </TD>
			<TD nowrap width="30%">
             	<DIV align="right">Tasa de Cambio Mda. Extranjera :</DIV>
			</TD><TD nowrap width="20%">
            	<INPUT type="text" name="E01DCMOFX" maxlength="19" size="20" value="<%= dcNew.getE01DCMOFX().trim()%>" onkeypress="enterDecimal()">
            </TD>
          </TR>
          <TR id="trclear">
            <TD nowrap width="30%">
             	<DIV align="right">Monto de la Cobranza :</DIV>
			</TD><TD nowrap width="20%">
            	<INPUT type="text" name="E01DCMOAM" maxlength="19" size="20" value="<%= dcNew.getE01DCMOAM().trim()%>" onkeypress="enterDecimal()" <%= !dcNew.getE01DCMOPT().equals("N")?"readonly":"" %>>
				<IMG src="<%=request.getContextPath()%>/images/Check.gif" alt="campo obligatorio"  border="0">
            </TD>
			<TD nowrap width="30%">
             	<DIV align="right">Protestar :</DIV>
            </TD><TD nowrap width="20%">
            	<INPUT type="radio" name="E01DCMPTF" value="Y" <%if(dcNew.getE01DCMPTF().equals("Y")) out.print("checked");%>>Sí
            	<INPUT type="radio" name="E01DCMPTF" value="N" <%if(dcNew.getE01DCMPTF().equals("N")) out.print("checked");%>>No
			</TD>
          </TR>
          <TR id="trdark">
            <TD nowrap width="30%">
             	<DIV align="right">Entregar Documentos Contra :</DIV>
            </TD>
            <TD nowrap width="20%">
				<SELECT name="E01DCMRDF">
					<OPTION value=" " <% if ((!dcNew.getE01DCMRDF().equals("P")) && (!dcNew.getE01DCMRDF().equals("A"))) out.println("selected"); %>></OPTION>
					<OPTION value="P" <% if (dcNew.getE01DCMRDF().equals("P")) out.println("selected"); %>>Pago</OPTION>
					<OPTION value="A" <% if (dcNew.getE01DCMRDF().equals("A")) out.println("selected"); %>>Aceptación</OPTION>
				</SELECT>
			</TD>
			<TD nowrap width="30%">
             	<DIV align="right">Condonar Cargos :</DIV>
            </TD><TD nowrap width="20%">
            	<INPUT type="radio" name="E01DCMWCF" value="Y" <%if(dcNew.getE01DCMWCF().equals("Y")) out.print("checked");%>>Sí
            	<INPUT type="radio" name="E01DCMWCF" value="N" <%if(dcNew.getE01DCMWCF().equals("N")) out.print("checked");%>>No
			</TD>
          </TR>
          <TR id="trclear">
            <TD nowrap width="30%">
             	<DIV align="right">Avisar Aceptación Vía :</DIV>
            </TD>
            <TD nowrap width="20%">
				<SELECT name="E01DCMAAF">
					<OPTION value=" " <% if ((!dcNew.getE01DCMAAF().equals("T")) && (!dcNew.getE01DCMAAF().equals("S")) && (!dcNew.getE01DCMAAF().equals("A")) && (!dcNew.getE01DCMAAF().equals("C")) && (!dcNew.getE01DCMAAF().equals("F"))) out.println("selected"); %>></OPTION>
					<OPTION value="T" <% if (dcNew.getE01DCMAAF().equals("T")) out.println("selected"); %>>Télex</OPTION>
					<OPTION value="S" <% if (dcNew.getE01DCMAAF().equals("S")) out.println("selected"); %>>Swift</OPTION>
					<OPTION value="A" <% if (dcNew.getE01DCMAAF().equals("A")) out.println("selected"); %>>Correo Aéreo</OPTION>
					<OPTION value="C" <% if (dcNew.getE01DCMAAF().equals("C")) out.println("selected"); %>>Mensaje</OPTION>
					<OPTION value="F" <% if (dcNew.getE01DCMAAF().equals("F")) out.println("selected"); %>>Fax</OPTION>
				</SELECT>
				<IMG src="<%=request.getContextPath()%>/images/Check.gif" alt="campo obligatorio"  border="0">
			</TD>
			<TD nowrap width="30%">
             	<DIV align="right">Pagos Parciales :</DIV>
			</TD>
            <TD nowrap width="20%">
            	<INPUT type="radio" name="E01DCMPPF" value="Y" <%if(dcNew.getE01DCMPPF().equals("Y")) out.print("checked");%>>Sí
            	<INPUT type="radio" name="E01DCMPPF" value="N" <%if(dcNew.getE01DCMPPF().equals("N")) out.print("checked");%>>No
            </TD>
          </TR>
          <TR id="trdark">
            <TD nowrap width="30%">
             	<DIV align="right">Avisar No Aceptación Vía :</DIV>
			</TD><TD nowrap width="20%">
				<SELECT name="E01DCMADF">
					<OPTION value=" " <% if ((!dcNew.getE01DCMADF().equals("T")) && (!dcNew.getE01DCMADF().equals("S")) && (!dcNew.getE01DCMADF().equals("A")) && (!dcNew.getE01DCMADF().equals("C")) && (!dcNew.getE01DCMADF().equals("F"))) out.println("selected"); %>></OPTION>
					<OPTION value="T" <% if (dcNew.getE01DCMADF().equals("T")) out.println("selected"); %>>Télex</OPTION>
					<OPTION value="S" <% if (dcNew.getE01DCMADF().equals("S")) out.println("selected"); %>>Swift</OPTION>
					<OPTION value="A" <% if (dcNew.getE01DCMADF().equals("A")) out.println("selected"); %>>Correo Aéreo</OPTION>
					<OPTION value="C" <% if (dcNew.getE01DCMADF().equals("C")) out.println("selected"); %>>Mensaje</OPTION>
					<OPTION value="F" <% if (dcNew.getE01DCMADF().equals("F")) out.println("selected"); %>>Fax</OPTION>
				</SELECT>
            </TD>
			<TD nowrap width="30%">
             	<DIV align="right">Condonar Intereses :</DIV>
			</TD>
            <TD nowrap width="20%">
            	<INPUT type="radio" name="E01DCMWIF" value="Y" <%if(dcNew.getE01DCMWIF().equals("Y")) out.print("checked");%>>Sí
            	<INPUT type="radio" name="E01DCMWIF" value="N" <%if(dcNew.getE01DCMWIF().equals("N")) out.print("checked");%>>No
            </TD>
          </TR>
          <TR id="trclear">
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
				<IMG src="<%=request.getContextPath()%>/images/Check.gif" alt="campo obligatorio"  border="0">
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
				<IMG src="<%=request.getContextPath()%>/images/Check.gif" alt="campo obligatorio"  border="0">
			</TD>
			<TD nowrap width="30%">

					<DIV align="right">Estado de la Cobranza</DIV>
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
				<IMG src="<%=request.getContextPath()%>/images/Check.gif" alt="campo obligatorio"  border="0">
            </TD>
			<TD nowrap width="30%"><DIV align="right">
			N° de Tarifa de Cargos :</DIV></TD>
			<TD nowrap width="20%">
			<INPUT type="text" name="E01DCMTAR" maxlength="3" size="4" value="<%= dcNew.getE01DCMTAR().trim()%>">
			<A href="javascript:GetTariffColl('E01DCMTAR','<%=dcNew.getE01DCMATY()%>','<%=dcNew.getE01DCMCUN()%>')">
			<IMG src="<%=request.getContextPath()%>/images/1b.gif" alt="Ayuda" border="0"></A></TD>
		  </TR>
          <TR id="trdark">
            <TD nowrap width="30%">
             	<DIV align="right">Término :</DIV>
			</TD>
			<TD nowrap width="20%">
				<SELECT name="E01DCMTRC">
					<OPTION value="  " <% if ((!dcNew.getE01DCMTRC().equals("AS")) && (!dcNew.getE01DCMTRC().equals("BE")) && (!dcNew.getE01DCMTRC().equals("BL")) && (!dcNew.getE01DCMTRC().equals("CC")) && (!dcNew.getE01DCMTRC().equals("FD")) && (!dcNew.getE01DCMTRC().equals("FP")) && (!dcNew.getE01DCMTRC().equals("GA")) && (!dcNew.getE01DCMTRC().equals("ID")) && (!dcNew.getE01DCMTRC().equals("ST")) && (!dcNew.getE01DCMTRC().equals("TD"))) out.println("selected"); %>></OPTION>
					<OPTION value="AS" <% if (dcNew.getE01DCMTRC().equals("AS")) out.println("selected"); %>>A la vista</OPTION>
					<OPTION value="BE" <% if (dcNew.getE01DCMTRC().equals("BE")) out.println("selected"); %>>Después de Fecha de Letra de Cambio</OPTION>
					<OPTION value="BL" <% if (dcNew.getE01DCMTRC().equals("BL")) out.println("selected"); %>>Después de Fecha de Embarque</OPTION>
					<OPTION value="CC" <% if (dcNew.getE01DCMTRC().equals("CC")) out.println("selected"); %>>Después de Pase de Mercancía por Aduana</OPTION>
					<OPTION value="FD" <% if (dcNew.getE01DCMTRC().equals("FD")) out.println("selected"); %>>Después de Pase de Mercancía por Revisión</OPTION>
					<OPTION value="FP" <% if (dcNew.getE01DCMTRC().equals("FP")) out.println("selected"); %>>Primera presentación</OPTION>
					<OPTION value="GA" <% if (dcNew.getE01DCMTRC().equals("GA")) out.println("selected"); %>>Después de Arribo de Mercancía</OPTION>
					<OPTION value="ID" <% if (dcNew.getE01DCMTRC().equals("ID")) out.println("selected"); %>>Después de Fecha de Factura</OPTION>
					<OPTION value="ST" <% if (dcNew.getE01DCMTRC().equals("ST")) out.println("selected"); %>>Después de Entregado</OPTION>
					<OPTION value="TD" <% if (dcNew.getE01DCMTRC().equals("TD")) out.println("selected"); %>>Después de Fecha de Doc. de Transporte</OPTION>
				</SELECT>
            </TD>
			<TD nowrap width="30%">
             	<DIV align="right">Plazo :</DIV>
            </TD>
            <TD nowrap width="20%">
            	<INPUT type="text" name="E01DCMDYS" maxlength="4" size="5" value="<%= dcNew.getE01DCMDYS().trim()%>">
			</TD>
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
          <% if(dcNew.getE01DCMTYP().equals("I")) { %>
				<TR id="trdark">
				  <TD width="25%" align="right" nowrap>Generar Swift:</TD>
				  <TD nowrap width="25%"><INPUT type="radio" name="E01DCMSWF" value="Y" <% if(!dcNew.getE01DCMSWF().equals("N")) out.print("checked");%>>
				    Si
				    <INPUT type="radio" name="E01DCMSWF" value="N" <% if( dcNew.getE01DCMSWF().equals("N")) out.print("checked");%>>
				    No </TD>
				  <TD align="right" nowrap>Tipo Mensaje Swift: </TD>
				  <TD align="left" nowrap><SELECT name="E01DCMSMT">
				  <% if (dcNew.getE01DCMOPT().equals("N")) { %>			
                      <OPTION value="410" <% if(dcNew.getE01DCMSMT().equals("410")) out.print("selected");%>>MT410</OPTION>
                  <% } else { %>    
                      <OPTION value="412" <% if(dcNew.getE01DCMSMT().equals("412")) out.print("selected");%>>MT412</OPTION>
                      <OPTION value="430" <% if(dcNew.getE01DCMSMT().equals("430")) out.print("selected");%>>MT430</OPTION>
                  <% } %>    
                  </SELECT></TD>
			 </TR>
		  <% } %>
        </TBODY></TABLE>
      </td>
    </tr>
  </table>

  <h4>Información de la Mercancía</h4>
  <table class="tableinfo">
    <tr bordercolor="#FFFFFF">
      <td nowrap>
        <table cellspacing="0" cellpadding="2" width="100%" border="0">
          <tr id="trdark">
            <td nowrap width="30%">
				<div align="right">N° de Guía del Embarque :</DIV>
            </td>
            <td nowrap width="20%"><INPUT type="text" name="E01DCMBLN" 	maxlength="16" size="17" value="<%= dcNew.getE01DCMBLN().trim()%>" ></td>
			<td nowrap width="30%">
             	<div align="right">Fecha de Guía del Embarque :</div>
			</td>
            <td nowrap width="20%">
            	<INPUT type="text" name="E01DCMBD1" maxlength="2" size="3" value="<%= dcNew.getE01DCMBD1().trim()%>" onKeyPress="enterInteger()">
            	<INPUT type="text" name="E01DCMBD2" maxlength="2" size="3" value="<%= dcNew.getE01DCMBD2().trim()%>" onKeyPress="enterInteger()">
            	<INPUT type="text" name="E01DCMBD3" maxlength="2" size="3" value="<%= dcNew.getE01DCMBD3().trim()%>" onKeyPress="enterInteger()">
                <A href="javascript:DatePicker(document.forms[0].E01DCMBD1,document.forms[0].E01DCMBD2,document.forms[0].E01DCMBD3)"><IMG src="<%=request.getContextPath()%>/images/calendar.gif" alt="help" border="0"></A>
            </td>
          </tr>
		  <tr id="trclear">
            <td nowrap width="30%">
				<DIV align="right">Descripción :</DIV>
            </td>
            <td nowrap width="20%"><INPUT type="text" name="E01DCMMED" 	maxlength="35" size="50" value="<%= dcNew.getE01DCMMED().trim()%>" ></td>
            <td nowrap width="30%"> 
             	<div align="right">Mercancía en Consignación :</div>
            </td>
            <td nowrap width="20%">  
            	<input type="radio" name="E01DCMMCO" value="Y" <%if(dcNew.getE01DCMMCO().equals("Y")) out.print("checked");%>>Sí 
            	<input type="radio" name="E01DCMMCO" value="N" <%if(dcNew.getE01DCMMCO().equals("N")) out.print("checked");%>>No 
			</td>
		  </tr>
		  <tr id="trdark">
			<td nowrap>
             	<div align="right">Nombre del Navío :</div>
			</td>
            <td nowrap colspan=3>
            	<INPUT type="text" name="E01DCMVSL" maxlength="20" size="35" value="<%= dcNew.getE01DCMVSL().trim()%>" >            	
            </td>
          </tr>
          <tr id="trclear">
             <td><DIV align="right">Agente :</div></td>
             <td colspan=3>
                <INPUT type="text" name="E01DCMAG1" maxlength="35" size="50" value="<%= dcNew.getE01DCMAG1().trim()%>" ><br>
                <INPUT type="text" name="E01DCMAG2" maxlength="35" size="50" value="<%= dcNew.getE01DCMAG2().trim()%>" ><br>
                <INPUT type="text" name="E01DCMAG3" maxlength="35" size="50" value="<%= dcNew.getE01DCMAG3().trim()%>" ><br>
                <INPUT type="text" name="E01DCMAG4" maxlength="35" size="50" value="<%= dcNew.getE01DCMAG4().trim()%>" >
             </td>
          </tr>
        </table>
      </td>
    </tr>
  </table>
    <h4>Información de Documentos Recibidos</h4>
  <table class="tableinfo">

    <tr bordercolor="#FFFFFF">
      <td nowrap>
        <table cellspacing="0" cellpadding="2" width="100%" border="0">
          <tr>
		      <td align="center">Documento</td>
		      <td align="center">Descripcion</td>
		      <td align="center">Originales</td>
  			  <td align="center">Copias</td>
		      <td align="center"></td>
		      <td align="center">Documento</td>
  			  <td align="center">Descripcion</td>
		      <td align="center">Originales</td>
		      <td align="center">Copias</td>		      
         </tr>
          <tr id="trdark">
            <td><INPUT type="text" name="E01DCMDD1" maxlength="4" size="5" value="<%= dcNew.getE01DCMDD1() %>" ><A
					href="javascript:GetCodeDescCNOFC('E01DCMDD1','E01DCMDS1','08')"><IMG
					src="<%=request.getContextPath()%>/images/1b.gif" alt="Ayuda"
					border="0"></A></td>
			<td><INPUT type="text" name="E01DCMDS1" maxlength="35" size="35" value="<%= dcNew.getE01DCMDS1() %>" readonly></td>
            <td align="center"> <INPUT type="text" name="E01DCMDO1" maxlength="1" size="2" value="<%= dcNew.getE01DCMDO1() %>" onKeyPress="enterInteger()"></td>
            <td align="center"> <INPUT type="text" name="E01DCMDC1" maxlength="1" size="2" value="<%= dcNew.getE01DCMDC1() %>" onKeyPress="enterInteger()"></td>
            <td></td>
            <td><INPUT type="text" name="E01DCMDD2" maxlength="4" size="5" value="<%= dcNew.getE01DCMDD2() %>" ><A
					href="javascript:GetCodeDescCNOFC('E01DCMDD2','E01DCMDS2','08')"><IMG
					src="<%=request.getContextPath()%>/images/1b.gif" alt="Ayuda"
					border="0"></A></td>
			<td><INPUT type="text" name="E01DCMDS2" maxlength="35" size="35" value="<%= dcNew.getE01DCMDS2() %>" readonly></td>
            <td align="center"> <INPUT type="text" name="E01DCMDO2" maxlength="1" size="2" value="<%= dcNew.getE01DCMDO2() %>" onKeyPress="enterInteger()"></td>
            <td align="center"> <INPUT type="text" name="E01DCMDC2" maxlength="1" size="2" value="<%= dcNew.getE01DCMDC2() %>" onKeyPress="enterInteger()"></td>
          </tr>
          <tr id="trclear">
            <td><INPUT type="text" name="E01DCMDD3" maxlength="4" size="5" value="<%= dcNew.getE01DCMDD3() %>" ><A
					href="javascript:GetCodeDescCNOFC('E01DCMDD3','E01DCMDS3','08')"><IMG
					src="<%=request.getContextPath()%>/images/1b.gif" alt="Ayuda"
					border="0"></A></td>
			<td><INPUT type="text" name="E01DCMDS3" maxlength="35" size="35" value="<%= dcNew.getE01DCMDS3() %>" readonly></td>
            <td align="center"> <INPUT type="text" name="E01DCMDO3" maxlength="1" size="2" value="<%= dcNew.getE01DCMDO3() %>" onKeyPress="enterInteger()"></td>
            <td align="center"> <INPUT type="text" name="E01DCMDC3" maxlength="1" size="2" value="<%= dcNew.getE01DCMDC3() %>" onKeyPress="enterInteger()"></td>
            <td></td>
            <td><INPUT type="text" name="E01DCMDD4" maxlength="4" size="5" value="<%= dcNew.getE01DCMDD4() %>" ><A
					href="javascript:GetCodeDescCNOFC('E01DCMDD4','E01DCMDS4','08')"><IMG
					src="<%=request.getContextPath()%>/images/1b.gif" alt="Ayuda"
					border="0"></A></td>
			<td><INPUT type="text" name="E01DCMDS4" maxlength="35" size="35" value="<%= dcNew.getE01DCMDS4() %>" readonly></td>
            <td align="center"> <INPUT type="text" name="E01DCMDO4" maxlength="1" size="2" value="<%= dcNew.getE01DCMDO4() %>" onKeyPress="enterInteger()"></td>
            <td align="center"> <INPUT type="text" name="E01DCMDC4" maxlength="1" size="2" value="<%= dcNew.getE01DCMDC4() %>" onKeyPress="enterInteger()"></td>
          </tr>
          <tr id="trdark">
            <td><INPUT type="text" name="E01DCMDD5" maxlength="4" size="5" value="<%= dcNew.getE01DCMDD5() %>" ><A
					href="javascript:GetCodeDescCNOFC('E01DCMDD5','E01DCMDS5','08')"><IMG
					src="<%=request.getContextPath()%>/images/1b.gif" alt="Ayuda"
					border="0"></A></td>
			<td><INPUT type="text" name="E01DCMDS5" maxlength="35" size="35" value="<%= dcNew.getE01DCMDS5() %>" readonly></td>
            <td align="center"> <INPUT type="text" name="E01DCMDO5" maxlength="1" size="2" value="<%= dcNew.getE01DCMDO5() %>" onKeyPress="enterInteger()"></td>
            <td align="center"> <INPUT type="text" name="E01DCMDC5" maxlength="1" size="2" value="<%= dcNew.getE01DCMDC5() %>" onKeyPress="enterInteger()"></td>
            <td></td>
            <td><INPUT type="text" name="E01DCMDD6" maxlength="4" size="5" value="<%= dcNew.getE01DCMDD6() %>" ><A
					href="javascript:GetCodeDescCNOFC('E01DCMDD6','E01DCMDS6','08')"><IMG
					src="<%=request.getContextPath()%>/images/1b.gif" alt="Ayuda"
					border="0"></A></td>
			<td><INPUT type="text" name="E01DCMDS6" maxlength="35" size="35" value="<%= dcNew.getE01DCMDS6() %>" readonly></td>
            <td align="center"> <INPUT type="text" name="E01DCMDO6" maxlength="1" size="2" value="<%= dcNew.getE01DCMDO6() %>" onKeyPress="enterInteger()"></td>
            <td align="center"> <INPUT type="text" name="E01DCMDC6" maxlength="1" size="2" value="<%= dcNew.getE01DCMDC6() %>" onKeyPress="enterInteger()"></td>
          </tr>
          <tr id="trclear">
            <td><INPUT type="text" name="E01DCMDD7" maxlength="4" size="5" value="<%= dcNew.getE01DCMDD7() %>" ><A
					href="javascript:GetCodeDescCNOFC('E01DCMDD7','E01DCMDS7','08')"><IMG
					src="<%=request.getContextPath()%>/images/1b.gif" alt="Ayuda"
					border="0"></A></td>
			<td><INPUT type="text" name="E01DCMDS7" maxlength="35" size="35" value="<%= dcNew.getE01DCMDS7() %>" readonly></td>
            <td align="center"> <INPUT type="text" name="E01DCMDO7" maxlength="1" size="2" value="<%= dcNew.getE01DCMDO7() %>" onKeyPress="enterInteger()"></td>
            <td align="center"> <INPUT type="text" name="E01DCMDC7" maxlength="1" size="2" value="<%= dcNew.getE01DCMDC7() %>" onKeyPress="enterInteger()"></td>
            <td></td>
            <td><INPUT type="text" name="E01DCMDD8" maxlength="4" size="5" value="<%= dcNew.getE01DCMDD8() %>" ><A
					href="javascript:GetCodeDescCNOFC('E01DCMDD8','E01DCMDS8','08')"><IMG
					src="<%=request.getContextPath()%>/images/1b.gif" alt="Ayuda"
					border="0"></A></td>
			<td><INPUT type="text" name="E01DCMDS8" maxlength="35" size="35" value="<%= dcNew.getE01DCMDS8() %>" readonly></td>
            <td align="center"> <INPUT type="text" name="E01DCMDO8" maxlength="1" size="2" value="<%= dcNew.getE01DCMDO8() %>" onKeyPress="enterInteger()"></td>
            <td align="center"> <INPUT type="text" name="E01DCMDC8" maxlength="1" size="2" value="<%= dcNew.getE01DCMDC8() %>" onKeyPress="enterInteger()"></td>
          </tr>
        </table>
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
