<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.0 Transitional//EN">
<html>
<head>
<title>Negociaciones de Cartas de Creditos</title>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<meta name="GENERATOR" content="IBM WebSphere Page Designer V4.0 for Windows">
<meta http-equiv="Content-Style-Type" content="text/css">
<link href="<%=request.getContextPath()%>/pages/style.css" rel="stylesheet">
<%@ page import = "datapro.eibs.master.Util" %>

<jsp:useBean id="msg01" class="datapro.eibs.beans.ELC056001Message" scope="session" />
<jsp:useBean id="error" class="datapro.eibs.beans.ELEERRMessage" scope="session" />
<jsp:useBean id="userPO" class="datapro.eibs.beans.UserPos" scope="session" />

<script language="Javascript1.1" src="<%=request.getContextPath()%>/pages/s/javascripts/eIBS.jsp"> </script>
<script language="Javascript1.1" src="<%=request.getContextPath()%>/pages/s/javascripts/optMenu.jsp"> </script>
<script language="javascript">
	builtNewMenu(lc_nego_apr);
	initMenu();
</script>

<%
 if ( !error.getERRNUM().equals("0")  ) {
     error.setERRNUM("0");
     out.println("<SCRIPT Language=\"Javascript\">");
     out.println("       showErrors()");
     out.println("</SCRIPT>");
 }
%>

</head>

<body>

<h3 align="center">Cobro de Comisiones de Cartas de Credito
<img src="<%=request.getContextPath()%>/images/e_ibs.gif" alt="ELC0560_lc_nego_com_readonly.jsp" name="EIBS_GIF" align="left"></h3>
<hr size="4">

<form method="post" action="<%=request.getContextPath()%>/servlet/datapro.eibs.products.JSELC0560" >
<form method="post" action="<%=request.getContextPath()%>/servlet/datapro.eibs.products.JSELC0560" >
    <input name="SCREEN" type="HIDDEN" value="3" readonly="readonly">
<!--    <INPUT TYPE="HIDDEN" name="H01OPECOD" value="0005">
    <INPUT TYPE="HIDDEN" ame="E01DRWNUM" value="<%=msg01.getE01DRWNUM()%>">
    <INPUT TYPE="HIDDEN" name="E01OPCODE" value="<%=msg01.getE01OPCODE()%>">
	<INPUT type="hidden" name="E01LCRFXR" value="<%=msg01.getE01LCRFXR()%>">
	<INPUT type="hidden" name="E01CUSFXR" value="<%=msg01.getE01CUSFXR()%>">
	<INPUT type="hidden" name="E01PMTFXR" value="<%=msg01.getE01PMTFXR()%>">
	<INPUT TYPE="HIDDEN" NAME="prevPage" VALUE="ELC0560_lc_nego_acp.jsp">
	<INPUT TYPE="HIDDEN" NAME="nextPage" VALUE="ELC0560_lc_nego_conf.jsp">
-->
	<table class="tableinfo" cellspacing="0" cellpadding="2" width="100%" border="0">
            <tr id="trdark">
              <td align="left"  height="29">Tipo de Negociaci&oacute;n:</td>
              <td nowrap  height="29"><input type="text" name="E01OPCODE" size="4" maxlength="1" value="<%= msg01.getE01OPCODE()%>" readonly>
                  <input type="text" name="E01OPCDSC" size="31" maxlength="30" value="<%= msg01.getE01OPCDSC()%>" readonly></td>
              <td nowrap align="left"  height="29">Producto:</td>
              <td nowrap align="left"  height="29"><input type="text" name="E01LCMPRO" size="8" maxlength="8"
					value="<%= msg01.getE01LCMPRO()%>" readonly></td>
            </tr>
            <tr id="trclear">
              <td align="left"  height="24" width="128">Carta de Cr&eacute;dito:</td>
              <td nowrap  height="24" width="284"><input type="text" name="E01LCRNUM" size="23" maxlength="12"
							value="<%= msg01.getE01LCRNUM()%>" readonly></td>
              <td nowrap align="left"  height="24" width="56">Moneda:</td>
              <td nowrap align="left"  height="24" width="291"><input type="text" name="E01LCMCCY" size="8" maxlength="3"
							value="<%= msg01.getE01LCMCCY()%>" readonly>
                &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;Monto:
                <input type="text" name="E01DRWAMN"
					size="18" maxlength="15" value="<%= msg01.getE01DRWAMN()%>" readonly></td>
            </tr>
            <tr id="trdark">
              <td align="left"  height="24">Cliente:</td>
              <td nowrap  height="24" align="left"><input type="text" name="E01LCMCUN" size="20" maxlength="9" value="<%= msg01.getE01LCMCUN()%>" readonly>
              </td>
              <td nowrap align="left"  height="24">Nombre:</td>
              <td nowrap align="left"  height="24"><input type="text" name="E01CUSNA1" size="46" maxlength="45" value="<%= msg01.getE01CUSNA1()%>" readonly></td>
            </tr>
        </table>
	<p><b>Cuenta D&eacute;bito Comisiones e Impuestos </b></p>
<table class="tableinfo" cellspacing="0" cellpadding="2" width="100%" border="0">
         <tr id="trdark">
           <td align="center"  height="24" width="230">Concepto</td>
           <td nowrap  height="24" align="center" width="51">Banco</td>
           <td nowrap  height="24" align="center" width="61">Agencia</td>
           <td nowrap align="center"  height="24" width="47">Mda</td>
           <td nowrap align="center"  height="24" width="128">Cuenta Contable</td>
           <td nowrap align="center"  height="24" width="132">Cuenta</td>
           <td nowrap align="center"  height="24" width="114">Centro de Costo</td>
		</tr>
         <tr id="trclear">
           <td width="230" height="24" align="left"  nowrap>
			<input name="E01CONDCO" type="text" value="<%=msg01.getE01CONDCO()%>" size="4" maxlength="2" readonly="readonly">
             <input name="E01CONDCB" type="text" value="<%=msg01.getE01CONDCB()%>" size="25" maxlength="25" readonly="readonly"
				oncontextmenu="showPopUp(conceptHelp,'E01CONDCB','','','E01DEBGL2','E01CONDCO','40'); return false;"></td>
           <td nowrap  height="24" align="center" width="51">
			<input name="E01DEBBK2" type="text" value="<%=msg01.getE01DEBBK2()%>" size="4" maxlength="2" readonly="readonly"></td>
           <td nowrap  height="24" align="center" width="61">
			<input name="E01DEBBR2" type="text" onKeyPress="enterInteger()" value="<%=msg01.getE01DEBBR2()%>" size="6" maxlength="3" readonly="readonly" oncontextmenu="showPopUp(branchHelp,this.name,document.forms[0].E01DEBBK2.value,'','','','')"></td>
           <td nowrap align="center"  height="24" width="47">
			<input name="E01DEBCY2" type="text" value="<%=msg01.getE01DEBCY2()%>" size="6" maxlength="3" readonly="readonly" oncontextmenu="showPopUp(currencyHelp,this.name,document.forms[0].E01DEBBK2.value,'','','','')"></td>
           <td nowrap align="center"  height="24" width="128">
			<input name="E01DEBGL2" type="text" onKeyPress="enterInteger()" value="<%=msg01.getE01DEBGL2()%>" size="20" maxlength="16" readonly="readonly"
			oncontextmenu="showPopUp(ledgerHelp,this.name,document.forms[0].E01DEBBK2.value,document.forms[0].E01DEBCY2.value,'','','')"></td>
           <td nowrap align="center"  height="24" width="132">
			<input name="E01DEBAC2" type="text" value="<%=msg01.getE01DEBAC2()%>" size="18" maxlength="12" readonly="readonly" oncontextmenu="showPopUp(accountHelp,this.name,document.forms[0].E01DEBBK2.value,'','','','RT')"></td>
           <td nowrap align="center"  height="24" width="114">
			<input name="E01DEBCC2" type="text" value="<%=msg01.getE01DEBCC2()%>" size="13" maxlength="8" readonly="readonly"oncontextmenu="showPopUp(costcenterHelp,this.name,document.forms[0].E01DEBBK2.value,'','','','')"></td>
		</tr>
</table>


<% if(msg01.getH01FLGWK3().equals("Y")){ %>
<p><b>Cuenta Cr�dito Banco Corresponsal </b></p>
<table class="tableinfo" cellspacing="0" cellpadding="2" width="100%" border="0">
  <tr id="trdark">
    <td align="center"  height="24" width="230">Concepto</td>
    <td nowrap  height="24" align="center" width="51">Banco</td>
    <td nowrap  height="24" align="center" width="61">Agencia</td>
    <td nowrap align="center"  height="24" width="47">Mda</td>
    <td nowrap align="center"  height="24" width="128">Cuenta Contable</td>
    <td nowrap align="center"  height="24" width="132">Cuenta</td>
    <td nowrap align="center"  height="24" width="114">Centro de Costo</td>
	</tr>
  <tr id="trclear">
    <td width="230" height="24" align="left"  nowrap>
		<input name="E01CONCBC" type="text" value="<%=msg01.getE01CONCBC()%>" size="4" maxlength="2" readonly="readonly">
		<input name="E01CONCBD" type="text"  value="<%=msg01.getE01CONCBD()%>" size="25" maxlength="25" readonly="readonly"
				oncontextmenu="showPopUp(conceptHelp,'E01CONCBD','','','E01CRPGLN','E01CONCBC','40'); return false;"></td>
    <td nowrap  height="24" align="center" width="51">
		<input name="E01CRPBNK" type="text" value="<%=msg01.getE01CRPBNK()%>" size="4" maxlength="2" readonly="readonly"></td>
    <td nowrap  height="24" align="center" width="61">
		<input name="E01CRPBRN" type="text" onKeyPress="enterInteger()" value="<%=msg01.getE01CRPBRN()%>" size="6" maxlength="3" readonly="readonly" oncontextmenu="showPopUp(branchHelp,this.name,document.forms[0].E01CRPBNK.value,'','','','')"></td>
    <td nowrap align="center"  height="24" width="47">
		<input name="E01CRPCCY" type="text" value="<%=msg01.getE01CRPCCY()%>" size="6" maxlength="3" readonly="readonly" oncontextmenu="showPopUp(currencyHelp,this.name,document.forms[0].E01CRPBNK.value,'','','','')"></td>
    <td nowrap align="center"  height="24" width="128">
		<input name="E01CRPGLN" type="text" onKeyPress="enterInteger()" value="<%=msg01.getE01CRPGLN()%>" size="20" maxlength="16" readonly="readonly"
			oncontextmenu="showPopUp(ledgerHelp,this.name,document.forms[0].E01CRPBNK.value,document.forms[0].E01CRPCCY.value,'','','')"></td>
    <td nowrap align="center"  height="24" width="132">
		<input name="E01CRPACC" type="text" value="<%=msg01.getE01CRPACC()%>" size="18" maxlength="12" readonly="readonly" oncontextmenu="showPopUp(accountHelp,this.name,document.forms[0].E01CRPBNK.value,'','','','RT')"></td>
    <td nowrap align="center"  height="24" width="114">
			<input name="E01CRPCCN" type="text" value="<%=msg01.getE01CRPCCN()%>" size="13" maxlength="8" readonly="readonly"oncontextmenu="showPopUp(costcenterHelp,this.name,document.forms[0].E01CRPBNK.value,'','','','')"></td>
	</tr>
</table>
<% } %>
<p></p>


<table class="tableinfo" cellspacing="0" cellpadding="2" width="100%" border="0">

	<tr id="trclear">
		<td align="right" height="24" width="233">Debitar Comisiones a Cta
		Beneficiario :</td>
		<td nowrap  align="left" width="115">
			<input name="E01DEBFLG" type="radio" value="Y" readonly="readonly" <%if (msg01.getE01DEBFLG().equals("Y")) out.print("checked"); %> disabled> Si
			<input name="E01DEBFLG" type="radio" value="N" readonly="readonly" <%if (msg01.getE01DEBFLG().equals("N")) out.print("checked"); %> disabled> No				</td>
		<td nowrap align="right" height="24" width="170">Cta. Cte.
		Beneficiario :</td>
		<td nowrap align="left"  height="24" width="160">
			<input name="E01LCMBAC" type="text" size="16" maxlength="12" readonly="readonly"></td>
	</tr>
	<tr id="trclear">
		<td align="right">Monto Debitado Por Corresponsal :</td>
		<td nowrap align="left">
			<input type="text" name="E01DEBCRP" size="18" maxlength="17" value="<%=msg01.getE01DEBCRP()%>" readonly>
		</td>
		<td nowrap align="right">Cargos Corresponsal x Cta. de :</td>
		<td nowrap align="left"><select name="E01CRPPBY" disabled>
			<option value="">&nbsp;</option>
			<option value="A"
				<%if (msg01.getE01CRPPBY().equals("A")) { out.print("selected"); }%>>Aplicante</option>
			<option value="B"
				<%if (msg01.getE01CRPPBY().equals("B")) { out.print("selected"); }%>>Beneficiario</option>
		</select></td>
	</tr></table>

<p><b>Tipos de Cambio</b></p>
<table class="tableinfo" cellspacing="0" cellpadding="2" width="100%" border="0">
  <tr id="trdark">
			<td>Moneda</td>
			<td>T/C Compra</td>
			<td>T/C Venta</td>
			<td>Moneda</td>
			<td>T/C Compra</td>
			<td>T/C Venta</td>
	</tr>
		<tr id="trclear">
    <td><input name="E01FXCCY1" type="text" value="<%=msg01.getE01FXCCY1()%>" size="9" maxlength="3" readonly="readonly"></td>
    <td><input name="E01FXRPU1" type="text" value="<%=msg01.getE01FXRPU1()%>" size="20" maxlength="11" readonly="readonly"></td>
    <td><input name="E01FXRSA1" type="text" value="<%=msg01.getE01FXRSA1()%>" size="20" maxlength="11" readonly="readonly"></td>
    <td><input name="E01FXCCY2" type="text" value="<%=msg01.getE01FXCCY2()%>" size="9" maxlength="3" readonly="readonly"></td>
    <td><input name="E01FXRPU2" type="text" value="<%=msg01.getE01FXRPU2()%>" size="20" maxlength="11" readonly="readonly"></td>
    <td><input name="E01FXRSA2" type="text" value="<%=msg01.getE01FXRSA2()%>" size="20" maxlength="11" readonly="readonly"></td>
		</tr>
		<tr id="trclear">
    <td><input name="E01FXCCY3" type="text" value="<%=msg01.getE01FXCCY3()%>" size="9" maxlength="3" readonly="readonly"></td>
    <td><input name="E01FXRPU3" type="text" value="<%=msg01.getE01FXRPU3()%>" size="20" maxlength="11" readonly="readonly"></td>
    <td><input name="E01FXRSA3" type="text" value="<%=msg01.getE01FXRSA3()%>" size="20" maxlength="11" readonly="readonly"></td>
    <td><input name="E01FXCCY4" type="text" value="<%=msg01.getE01FXCCY4()%>" size="9" maxlength="3" readonly="readonly"></td>
    <td><input name="E01FXRPU4" type="text" value="<%=msg01.getE01FXRPU4()%>" size="20" maxlength="11" readonly="readonly"></td>
    <td><input name="E01FXRSA4" type="text" value="<%=msg01.getE01FXRSA4()%>" size="20" maxlength="11" readonly="readonly"></td>
		</tr>
  </table>

<%	int i = 0;	%>

<p><b>Comisiones y Gastos</b></p>

<% if(msg01.getH01FLGWK3().equals("Y")){ %>
<table class="tableinfo" cellspacing="0" cellpadding="2" width="100%" border="0">
  <tbody><tr id="trdark">
			<td>&nbsp;</td>
    <td colspan="2" align="center"><b>Nuestros</b></td><td colspan="2" align="center"><b>Banco Corresponsal</b></td>

	</tr>
  <tr id="trdark">
    <td nowrap align="left">Comisi&oacute;n</td>
    <td nowrap align="center">Monto</td><td nowrap align="center">Por</td><td nowrap align="center">Monto</td><td nowrap align="center">Por</td>



	</tr>
	  <tr id='<%= (i++ %2 == 0 ? "trclear" : "trdark") %>'>
	    <td align="left">Apertura</td>
	    <td align="center"><input name="E01ISSFEE" type="text" id="E01ISSFEE" value="<%=msg01.getE01ISSFEE()%>" readonly></td><td align="center"><select name="E01ISSPBY" disabled>
			<option value="A" <%if(msg01.getE01ISSPBY().equals("A")) out.print("selected");%>>Aplicante</option>
			<option value="B" <%if(msg01.getE01ISSPBY().equals("B")) out.print("selected");%>>Beneficiario</option>
			<option value="W" <%if(msg01.getE01ISSPBY().equals("W")) out.print("selected");%>>No Cobrar</option>
			</select></td><td align="center"><input name="E01CRPISS" type="text" id="E01CRPISS" value="<%=msg01.getE01CRPISS()%>" readonly></td><td align="center"><select name="E01ISSCPB" disabled>
	    	<% if (msg01.getE01CRPPBY().equals("A")) { %>
			<option value="A" <%if(msg01.getE01ISSCPB().equals("A")) out.print("selected");%>>Aplicante</option>
			<% } %>
	    	<% if (msg01.getE01CRPPBY().equals("B")) { %>
			<option value="B" <%if(msg01.getE01ISSCPB().equals("B")) out.print("selected");%>>Beneficiario</option>
			<% } %>
			<option value="W" <%if(msg01.getE01ISSCPB().equals("W")) out.print("selected");%>>No Cobrar</option>
			</select></td>



	</tr>
	  	  <tr id='<%= (i++ %2 == 0 ? "trclear" : "trdark") %>'>
	    <td align="left">Aviso</td>
	    <td align="center"><input name="E01ADVCOM" type="text" value="<%=msg01.getE01ADVCOM()%>" readonly></td><td align="center"><select name="E01ADVPBY" disabled>
			<option value="A" <%if(msg01.getE01ADVPBY().equals("A")) out.print("selected");%>>Aplicante</option>
			<option value="B" <%if(msg01.getE01ADVPBY().equals("B")) out.print("selected");%>>Beneficiario</option>
			<option value="W" <%if(msg01.getE01ADVPBY().equals("W")) out.print("selected");%>>No Cobrar</option>
			</select></td><td align="center"><input type="text" name="E01CRPADV" value="<%=msg01.getE01CRPADV()%>" readonly></td><td align="center"><select name="E01ADVCPB" disabled>
	    	<% if (msg01.getE01CRPPBY().equals("A")) { %>
			<option value="A" <%if(msg01.getE01ADVCPB().equals("A")) out.print("selected");%>>Aplicante</option>
			<% } %>
	    	<% if (msg01.getE01CRPPBY().equals("B")) { %>
			<option value="B" <%if(msg01.getE01ADVCPB().equals("B")) out.print("selected");%>>Beneficiario</option>
			<% } %>
			<option value="W" <%if(msg01.getE01ADVCPB().equals("W")) out.print("selected");%>>No Cobrar</option>
			</select></td>



		</tr>
	  <tr id='<%= (i++ %2 == 0 ? "trclear" : "trdark") %>'>
	    <td align="left">Confirmaci&oacute;n</td>
	    <td align="center"><input type="text" name="E01CNFCOM" value="<%=msg01.getE01CNFCOM()%>" readonly></td><td align="center"><select name="E01CNFPBY" disabled>
			<option value="A" <%if(msg01.getE01CNFPBY().equals("A")) out.print("selected");%>>Aplicante</option>
			<option value="B" <%if(msg01.getE01CNFPBY().equals("B")) out.print("selected");%>>Beneficiario</option>
			<option value="W" <%if(msg01.getE01CNFPBY().equals("W")) out.print("selected");%>>No Cobrar</option>
			</select></td><td align="center"><input type="text" name="E01CRPCNF" value="<%=msg01.getE01CRPCNF()%>" readonly></td><td align="center"><select name="E01CNFCPB" disabled>
	    	<% if (msg01.getE01CRPPBY().equals("A")) { %>
			<option value="A" <%if(msg01.getE01CNFCPB().equals("A")) out.print("selected");%>>Aplicante</option>
			<% } %>
	    	<% if (msg01.getE01CRPPBY().equals("B")) { %>
			<option value="B" <%if(msg01.getE01CNFCPB().equals("B")) out.print("selected");%>>Beneficiario</option>
			<% } %>
			<option value="W" <%if(msg01.getE01CNFCPB().equals("W")) out.print("selected");%>>No Cobrar</option>
			</select></td>



	</tr>
	  <tr id='<%= (i++ %2 == 0 ? "trclear" : "trdark") %>'>
	    <td align="left">Enmienda</td>
	    <td align="center"><input type="text" name="E01AMDFEE" value="<%=msg01.getE01AMDFEE()%>" readonly></td><td align="center"><select name="E01AMDPBY" disabled>
			<option value="A" <%if(msg01.getE01AMDPBY().equals("A")) out.print("selected");%>>Aplicante</option>
			<option value="B" <%if(msg01.getE01AMDPBY().equals("B")) out.print("selected");%>>Beneficiario</option>
			<option value="W" <%if(msg01.getE01AMDPBY().equals("W")) out.print("selected");%>>No Cobrar</option>
			</select></td><td align="center"><input type="text" name="E01CRPAMD" value="<%=msg01.getE01CRPAMD()%>" readonly></td><td align="center"><select name="E01AMDCPB" disabled>
	    	<% if (msg01.getE01CRPPBY().equals("A")) { %>
			<option value="A" <%if(msg01.getE01AMDCPB().equals("A")) out.print("selected");%>>Aplicante</option>
			<% } %>
	    	<% if (msg01.getE01CRPPBY().equals("B")) { %>
			<option value="B" <%if(msg01.getE01AMDCPB().equals("B")) out.print("selected");%>>Beneficiario</option>
			<% } %>
			<option value="W" <%if(msg01.getE01AMDCPB().equals("W")) out.print("selected");%>>No Cobrar</option>
			</select></td>



	</tr>
<tr id='<%= (i++ %2 == 0 ? "trclear" : "trdark") %>'>
	    <td align="left">Aviso de Enmienda</td>
	    <td align="center"><input type="text" name="E01ADVAMF" value="<%=msg01.getE01ADVAMF()%>" readonly></td><td align="center"><select name="E01AAMPBY" disabled>
			<option value="A" <%if(msg01.getE01AAMPBY().equals("A")) out.print("selected");%>>Aplicante</option>
			<option value="B" <%if(msg01.getE01AAMPBY().equals("B")) out.print("selected");%>>Beneficiario</option>
			<option value="W" <%if(msg01.getE01AAMPBY().equals("W")) out.print("selected");%>>No Cobrar</option>
			</select></td><td align="center"><input type="text" name="E01CRPAAM" value="<%=msg01.getE01CRPAAM()%>" readonly></td><td align="center"><select name="E01AAMCPB" disabled>
	    	<% if (msg01.getE01CRPPBY().equals("A")) { %>
			<option value="A" <%if(msg01.getE01AAMCPB().equals("A")) out.print("selected");%>>Aplicante</option>
			<% } %>
	    	<% if (msg01.getE01CRPPBY().equals("B")) { %>
			<option value="B" <%if(msg01.getE01AAMCPB().equals("B")) out.print("selected");%>>Beneficiario</option>
			<% } %>
			<option value="W" <%if(msg01.getE01AAMCPB().equals("W")) out.print("selected");%>>No Cobrar</option>
			</select></td>



	</tr>
	  	  <tr id='<%= (i++ %2 == 0 ? "trclear" : "trdark") %>'>
	    <td align="left">Discrepancia</td>
	    <td align="center"><input type="text" name="E01DISCOM" value="<%=msg01.getE01DISCOM()%>" readonly></td><td align="center"><select name="E01DISPBY" disabled>
			<option value="A" <%if(msg01.getE01DISPBY().equals("A")) out.print("selected");%>>Aplicante</option>
			<option value="B" <%if(msg01.getE01DISPBY().equals("B")) out.print("selected");%>>Beneficiario</option>
			<option value="W" <%if(msg01.getE01DISPBY().equals("W")) out.print("selected");%>>No Cobrar</option>
			</select></td><td align="center"><input type="text" name="E01CRPDIS" value="<%=msg01.getE01CRPDIS()%>" readonly></td><td align="center"><select name="E01DISCPB" disabled>
	    	<% if (msg01.getE01CRPPBY().equals("A")) { %>
			<option value="A" <%if(msg01.getE01DISCPB().equals("A")) out.print("selected");%>>Aplicante</option>
			<% } %>
	    	<% if (msg01.getE01CRPPBY().equals("B")) { %>
			<option value="B" <%if(msg01.getE01DISCPB().equals("B")) out.print("selected");%>>Beneficiario</option>
			<% } %>
			<option value="W" <%if(msg01.getE01DISCPB().equals("W")) out.print("selected");%>>No Cobrar</option>
			</select></td>



		</tr>
	  <tr id='<%= (i++ %2 == 0 ? "trclear" : "trdark") %>'>
	    <td align="left">Extensi�n de Validez</td>
	    <td align="center"><input type="text" name="E01EOVCOM" value="<%=msg01.getE01EOVCOM()%>" readonly></td><td align="center"><select name="E01EOVPBY" disabled>
			<option value="A" <%if(msg01.getE01EOVPBY().equals("A")) out.print("selected");%>>Aplicante</option>
			<option value="B" <%if(msg01.getE01EOVPBY().equals("B")) out.print("selected");%>>Beneficiario</option>
			<option value="W" <%if(msg01.getE01EOVPBY().equals("W")) out.print("selected");%>>No Cobrar</option>
			</select></td><td align="center"><input type="text" name="E01CRPEOV" value="<%=msg01.getE01CRPEOV()%>" readonly></td><td align="center"><select name="E01EOVCPB" disabled>
	    	<% if (msg01.getE01CRPPBY().equals("A")) { %>
			<option value="A" <%if(msg01.getE01EOVCPB().equals("A")) out.print("selected");%>>Aplicante</option>
			<% } %>
	    	<% if (msg01.getE01CRPPBY().equals("B")) { %>
			<option value="B" <%if(msg01.getE01EOVCPB().equals("B")) out.print("selected");%>>Beneficiario</option>
			<% } %>
			<option value="W" <%if(msg01.getE01EOVCPB().equals("W")) out.print("selected");%>>No Cobrar</option>
			</select></td>



	</tr>
	  <tr id='<%= (i++ %2 == 0 ? "trclear" : "trdark") %>'>
	    <td align="left">Portes</td>
	    <td align="center"><input type="text" name="E01POSTAM" value="<%=msg01.getE01POSTAM()%>" readonly></td><td align="center"><select name="E01POSPBY" disabled>
			<option value="A" <%if(msg01.getE01POSPBY().equals("A")) out.print("selected");%>>Aplicante</option>
			<option value="B" <%if(msg01.getE01POSPBY().equals("B")) out.print("selected");%>>Beneficiario</option>
			<option value="W" <%if(msg01.getE01POSPBY().equals("W")) out.print("selected");%>>No Cobrar</option>
			</select></td><td align="center"><input type="text" name="E01CRPPOS" value="<%=msg01.getE01CRPPOS()%>" readonly></td><td align="center"><select name="E01POSCPB" disabled>
	    	<% if (msg01.getE01CRPPBY().equals("A")) { %>
			<option value="A" <%if(msg01.getE01POSCPB().equals("A")) out.print("selected");%>>Aplicante</option>
			<% } %>
	    	<% if (msg01.getE01CRPPBY().equals("B")) { %>
			<option value="B" <%if(msg01.getE01POSCPB().equals("B")) out.print("selected");%>>Beneficiario</option>
			<% } %>
			<option value="W" <%if(msg01.getE01POSCPB().equals("W")) out.print("selected");%>>No Cobrar</option>
			</select></td>



	</tr>
	<tr id='<%= (i++ %2 == 0 ? "trclear" : "trdark") %>'>
	    <td align="left">Courier</td>
	    <td align="center"><input type="text" name="E01COURAM" value="<%=msg01.getE01COURAM()%>" readonly></td><td align="center"><select name="E01COUPBY" disabled>
			<option value="A" <%if(msg01.getE01COUPBY().equals("A")) out.print("selected");%>>Aplicante</option>
			<option value="B" <%if(msg01.getE01COUPBY().equals("B")) out.print("selected");%>>Beneficiario</option>
			<option value="W" <%if(msg01.getE01COUPBY().equals("W")) out.print("selected");%>>No Cobrar</option>
			</select></td><td align="center"><input type="text" name="E01CRPCOU" value="<%=msg01.getE01CRPCOU()%>" readonly></td><td align="center"><select name="E01COUCPB" disabled>
	    	<% if (msg01.getE01CRPPBY().equals("A")) { %>
			<option value="A" <%if(msg01.getE01COUCPB().equals("A")) out.print("selected");%>>Aplicante</option>
			<% } %>
	    	<% if (msg01.getE01CRPPBY().equals("B")) { %>
			<option value="B" <%if(msg01.getE01COUCPB().equals("B")) out.print("selected");%>>Beneficiario</option>
			<% } %>
			<option value="W" <%if(msg01.getE01COUCPB().equals("W")) out.print("selected");%>>No Cobrar</option>
			</select></td>



	</tr>
	  	  <tr id='<%= (i++ %2 == 0 ? "trclear" : "trdark") %>'>
	    <td align="left">Swift de Apertura</td>
	    <td align="center"><input type="text" name="E01SWFFEE" value="<%=msg01.getE01SWFFEE()%>" readonly></td><td align="center"><select name="E01SWFPBY" disabled>
			<option value="A" <%if(msg01.getE01SWFPBY().equals("A")) out.print("selected");%>>Aplicante</option>
			<option value="B" <%if(msg01.getE01SWFPBY().equals("B")) out.print("selected");%>>Beneficiario</option>
			<option value="W" <%if(msg01.getE01SWFPBY().equals("W")) out.print("selected");%>>No Cobrar</option>
			</select></td><td align="center"><input type="text" name="E01CRPSWF" value="<%=msg01.getE01CRPSWF()%>" readonly></td><td align="center"><select name="E01SWFCPB" disabled>
	    	<% if (msg01.getE01CRPPBY().equals("A")) { %>
			<option value="A" <%if(msg01.getE01SWFCPB().equals("A")) out.print("selected");%>>Aplicante</option>
			<% } %>
	    	<% if (msg01.getE01CRPPBY().equals("B")) { %>
			<option value="B" <%if(msg01.getE01SWFCPB().equals("B")) out.print("selected");%>>Beneficiario</option>
			<% } %>
			<option value="W" <%if(msg01.getE01SWFCPB().equals("W")) out.print("selected");%>>No Cobrar</option>
			</select></td>



		</tr>
	  <tr id='<%= (i++ %2 == 0 ? "trclear" : "trdark") %>'>
	    <td align="left">Swift Adicional(es)</td>
	    <td align="center"><input type="text" name="E01TLXFEE" value="<%=msg01.getE01TLXFEE()%>" readonly></td><td align="center"><select name="E01TLXPBY" disabled>
			<option value="A" <%if(msg01.getE01TLXPBY().equals("A")) out.print("selected");%>>Aplicante</option>
			<option value="B" <%if(msg01.getE01TLXPBY().equals("B")) out.print("selected");%>>Beneficiario</option>
			<option value="W" <%if(msg01.getE01TLXPBY().equals("W")) out.print("selected");%>>No Cobrar</option>
			</select></td><td align="center"><input type="text" name="E01CRPTLX" value="<%=msg01.getE01CRPTLX()%>" readonly></td><td align="center"><select name="E01TLXCPB" disabled>
	    	<% if (msg01.getE01CRPPBY().equals("A")) { %>
			<option value="A" <%if(msg01.getE01TLXCPB().equals("A")) out.print("selected");%>>Aplicante</option>
			<% } %>
	    	<% if (msg01.getE01CRPPBY().equals("B")) { %>
			<option value="B" <%if(msg01.getE01TLXCPB().equals("B")) out.print("selected");%>>Beneficiario</option>
			<% } %>
			<option value="W" <%if(msg01.getE01TLXCPB().equals("W")) out.print("selected");%>>No Cobrar</option>
			</select></td>



	</tr>
	  <tr id='<%= (i++ %2 == 0 ? "trclear" : "trdark") %>'>
	    <td align="left">Cancelaci�n</td>
	    <td align="center"><input type="text" name="E01CANFEE" value="<%=msg01.getE01CANFEE()%>" readonly></td><td align="center"><select name="E01CANPBY" disabled>
			<option value="A" <%if(msg01.getE01CANPBY().equals("A")) out.print("selected");%>>Aplicante</option>
			<option value="B" <%if(msg01.getE01CANPBY().equals("B")) out.print("selected");%>>Beneficiario</option>
			<option value="W" <%if(msg01.getE01CANPBY().equals("W")) out.print("selected");%>>No Cobrar</option>
			</select></td><td align="center"><input type="text" name="E01CRPCAN" value="<%=msg01.getE01CRPCAN()%>" readonly></td><td align="center"><select name="E01CANCPB" disabled>
	    	<% if (msg01.getE01CRPPBY().equals("A")) { %>
			<option value="A" <%if(msg01.getE01CANCPB().equals("A")) out.print("selected");%>>Aplicante</option>
			<% } %>
	    	<% if (msg01.getE01CRPPBY().equals("B")) { %>
			<option value="B" <%if(msg01.getE01CANCPB().equals("B")) out.print("selected");%>>Beneficiario</option>
			<% } %>
			<option value="W" <%if(msg01.getE01CANCPB().equals("W")) out.print("selected");%>>No Cobrar</option>
			</select></td>



	</tr>
<tr id='<%= (i++ %2 == 0 ? "trclear" : "trdark") %>'>
	    <td align="left">Pago</td>
	    <td align="center"><input type="text" name="E01PAYCOM" value="<%=msg01.getE01PAYCOM()%>" readonly></td><td align="center"><select name="E01PMCPBY" disabled>
			<option value="A" <%if(msg01.getE01PMCPBY().equals("A")) out.print("selected");%>>Aplicante</option>
			<option value="B" <%if(msg01.getE01PMCPBY().equals("B")) out.print("selected");%>>Beneficiario</option>
			<option value="W" <%if(msg01.getE01PMCPBY().equals("W")) out.print("selected");%>>No Cobrar</option>
			</select></td><td align="center"><input type="text" name="E01CRPPAY" value="<%=msg01.getE01CRPPAY()%>" readonly></td><td align="center"><select name="E01PMCCPB" disabled>
	    	<% if (msg01.getE01CRPPBY().equals("A")) { %>
			<option value="A" <%if(msg01.getE01PMCCPB().equals("A")) out.print("selected");%>>Aplicante</option>
			<% } %>
	    	<% if (msg01.getE01CRPPBY().equals("B")) { %>
			<option value="B" <%if(msg01.getE01PMCCPB().equals("B")) out.print("selected");%>>Beneficiario</option>
			<% } %>
			<option value="W" <%if(msg01.getE01PMCCPB().equals("W")) out.print("selected");%>>No Cobrar</option>
			</select></td>



	</tr>
	<tr id='<%= (i++ %2 == 0 ? "trclear" : "trdark") %>'>
		<td align="left">Aceptacion</td><td align="center">
		<input type="text" name="E01ACPCOM" value="<%=msg01.getE01ACPCOM()%>" readonly></td><td align="center"><select name="E01ACCPBY" disabled>
			<option value="A" <%if(msg01.getE01ACCPBY().equals("A")) out.print("selected");%>>Aplicante</option>
			<option value="B" <%if(msg01.getE01ACCPBY().equals("B")) out.print("selected");%>>Beneficiario</option>
			<option value="W" <%if(msg01.getE01ACCPBY().equals("W")) out.print("selected");%>>No Cobrar</option>
			</select></td><td align="center">
			<input type="text" name="E01CRPACP" value="<%=msg01.getE01CRPACP()%>" readonly></td><td align="center"><select name="E01ACCCPB" disabled>
	    	<% if (msg01.getE01CRPPBY().equals("A")) { %>
			<option value="A" <%if(msg01.getE01ACCCPB().equals("A")) out.print("selected");%>>Aplicante</option>
			<% } %>
	    	<% if (msg01.getE01CRPPBY().equals("B")) { %>
			<option value="B" <%if(msg01.getE01ACCCPB().equals("B")) out.print("selected");%>>Beneficiario</option>
			<% } %>
			<option value="W" <%if(msg01.getE01ACCCPB().equals("W")) out.print("selected");%>>No Cobrar</option>
			</select></td>


	</tr>
	<tr id='<%= (i++ %2 == 0 ? "trclear" : "trdark") %>'>
		<td align="left">Pago Diferido</td><td align="center">
		<input type="text" name="E01DFPFEE" value="<%=msg01.getE01DFPFEE()%>" readonly></td><td align="center"><select name="E01DFPPBY" disabled>
			<option value="A" <%if(msg01.getE01DFPPBY().equals("A")) out.print("selected");%>>Aplicante</option>
			<option value="B" <%if(msg01.getE01DFPPBY().equals("B")) out.print("selected");%>>Beneficiario</option>
			<option value="W" <%if(msg01.getE01DFPPBY().equals("W")) out.print("selected");%>>No Cobrar</option>
			</select></td><td align="center">
			<input type="text" name="E01CRPDFP" value="<%=msg01.getE01CRPDFP()%>" readonly></td><td align="center"><select name="E01DFPCPB" disabled>
	    	<% if (msg01.getE01CRPPBY().equals("A")) { %>
			<option value="A" <%if(msg01.getE01DFPCPB().equals("A")) out.print("selected");%>>Aplicante</option>
			<% } %>
	    	<% if (msg01.getE01CRPPBY().equals("B")) { %>
			<option value="B" <%if(msg01.getE01DFPCPB().equals("B")) out.print("selected");%>>Beneficiario</option>
			<% } %>
			<option value="W" <%if(msg01.getE01DFPCPB().equals("W")) out.print("selected");%>>No Cobrar</option>
			</select></td>


	</tr>
	  	  <tr id='<%= (i++ %2 == 0 ? "trclear" : "trdark") %>'>
	    <td align="left">Transferencia de Fondos</td>
	    <td align="center"><input type="text" name="E01WTRCOM" value="<%=msg01.getE01WTRCOM()%>" readonly></td><td align="center"><select name="E01WTRPBY" disabled>
			<option value="A" <%if(msg01.getE01WTRPBY().equals("A")) out.print("selected");%>>Aplicante</option>
			<option value="B" <%if(msg01.getE01WTRPBY().equals("B")) out.print("selected");%>>Beneficiario</option>
			<option value="W" <%if(msg01.getE01WTRPBY().equals("W")) out.print("selected");%>>No Cobrar</option>
			</select></td><td align="center"><input type="text" name="E01CRPWTR" value="<%=msg01.getE01CRPWTR()%>" readonly></td><td align="center"><select name="E01WTRCPB" disabled>
	    	<% if (msg01.getE01CRPPBY().equals("A")) { %>
			<option value="A" <%if(msg01.getE01WTRCPB().equals("A")) out.print("selected");%>>Aplicante</option>
			<% } %>
	    	<% if (msg01.getE01CRPPBY().equals("B")) { %>
			<option value="B" <%if(msg01.getE01WTRCPB().equals("B")) out.print("selected");%>>Beneficiario</option>
			<% } %>
			<option value="W" <%if(msg01.getE01WTRCPB().equals("W")) out.print("selected");%>>No Cobrar</option>
			</select></td>



		</tr>
	  <tr id='<%= (i++ %2 == 0 ? "trclear" : "trdark") %>'>
	    <td align="left">Reembolso</td>
	    <td align="center"><input type="text" name="E01RMBCOM" value="<%=msg01.getE01RMBCOM()%>" readonly></td><td align="center"><select name="E01RMBPBY" disabled>
			<option value="A" <%if(msg01.getE01RMBPBY().equals("A")) out.print("selected");%>>Aplicante</option>
			<option value="B" <%if(msg01.getE01RMBPBY().equals("B")) out.print("selected");%>>Beneficiario</option>
			<option value="W" <%if(msg01.getE01RMBPBY().equals("W")) out.print("selected");%>>No Cobrar</option>
			</select></td><td align="center"><input type="text" name="E01CRPRMB" value="<%=msg01.getE01CRPRMB()%>" readonly></td><td align="center"><select name="E01RMBCPB" disabled>
	    	<% if (msg01.getE01CRPPBY().equals("A")) { %>
			<option value="A" <%if(msg01.getE01RMBCPB().equals("A")) out.print("selected");%>>Aplicante</option>
			<% } %>
	    	<% if (msg01.getE01CRPPBY().equals("B")) { %>
			<option value="B" <%if(msg01.getE01RMBCPB().equals("B")) out.print("selected");%>>Beneficiario</option>
			<% } %>
			<option value="W" <%if(msg01.getE01RMBCPB().equals("W")) out.print("selected");%>>No Cobrar</option>
			</select></td>



	</tr>
	<tr id='<%= (i++ %2 == 0 ? "trclear" : "trdark") %>'>
	    <td align="left">Comision Por Plazo</td>
	    <td align="center"><input type="text" name="E01TRMCOM" value="<%=msg01.getE01TRMCOM()%>" readonly></td><td align="center"><select name="E01TRMPBY" disabled>
			<option value="A" <%if(msg01.getE01TRMPBY().equals("A")) out.print("selected");%>>Aplicante</option>
			<option value="B" <%if(msg01.getE01TRMPBY().equals("B")) out.print("selected");%>>Beneficiario</option>
			<option value="W" <%if(msg01.getE01TRMPBY().equals("W")) out.print("selected");%>>No Cobrar</option>
			</select></td><td align="center">&nbsp;</td><td align="center">&nbsp;</td>



	</tr>
</tbody></table>
<% } else { %>
<table class="tableinfo" cellspacing="0" cellpadding="2" width="100%" border="0">
  <tbody><tr id="trdark">
			<td>&nbsp;</td>
    <td colspan="2" align="center"><b>Nuestros</b></td></tr>
  <tr id="trdark">
    <td nowrap align="left">Comisi&oacute;n</td>
    <td nowrap align="center">Monto</td><td nowrap align="center">Por</td></tr>
	  <tr id='<%= (i++ %2 == 0 ? "trclear" : "trdark") %>'>
	    <td align="left">Apertura</td>
	    <td align="center"><input name="E01ISSFEE" type="text" id="E01ISSFEE" value="<%=msg01.getE01ISSFEE()%>" readonly></td><td align="center"><select name="E01ISSPBY" disabled>
			<option value="A" <%if(msg01.getE01ISSPBY().equals("A")) out.print("selected");%>>Aplicante</option>
			<option value="B" <%if(msg01.getE01ISSPBY().equals("B")) out.print("selected");%>>Beneficiario</option>
			<option value="W" <%if(msg01.getE01ISSPBY().equals("W")) out.print("selected");%>>No Cobrar</option>
			</select></td></tr>
	  	  <tr id='<%= (i++ %2 == 0 ? "trclear" : "trdark") %>'>
	    <td align="left">Aviso</td>
	    <td align="center"><input name="E01ADVCOM" type="text" value="<%=msg01.getE01ADVCOM()%>" readonly></td><td align="center"><select name="E01ADVPBY" disabled>
			<option value="A" <%if(msg01.getE01ADVPBY().equals("A")) out.print("selected");%>>Aplicante</option>
			<option value="B" <%if(msg01.getE01ADVPBY().equals("B")) out.print("selected");%>>Beneficiario</option>
			<option value="W" <%if(msg01.getE01ADVPBY().equals("W")) out.print("selected");%>>No Cobrar</option>
			</select></td></tr>
	  <tr id='<%= (i++ %2 == 0 ? "trclear" : "trdark") %>'>
	    <td align="left">Confirmaci&oacute;n</td>
	    <td align="center"><input type="text" name="E01CNFCOM" value="<%=msg01.getE01CNFCOM()%>" readonly></td><td align="center"><select name="E01CNFPBY" disabled>
			<option value="A" <%if(msg01.getE01CNFPBY().equals("A")) out.print("selected");%>>Aplicante</option>
			<option value="B" <%if(msg01.getE01CNFPBY().equals("B")) out.print("selected");%>>Beneficiario</option>
			<option value="W" <%if(msg01.getE01CNFPBY().equals("W")) out.print("selected");%>>No Cobrar</option>
			</select></td></tr>
	  <tr id='<%= (i++ %2 == 0 ? "trclear" : "trdark") %>'>
	    <td align="left">Enmienda</td>
	    <td align="center"><input type="text" name="E01AMDFEE" value="<%=msg01.getE01AMDFEE()%>" readonly></td><td align="center"><select name="E01AMDPBY" disabled>
			<option value="A" <%if(msg01.getE01AMDPBY().equals("A")) out.print("selected");%>>Aplicante</option>
			<option value="B" <%if(msg01.getE01AMDPBY().equals("B")) out.print("selected");%>>Beneficiario</option>
			<option value="W" <%if(msg01.getE01AMDPBY().equals("W")) out.print("selected");%>>No Cobrar</option>
			</select></td></tr>
<tr id='<%= (i++ %2 == 0 ? "trclear" : "trdark") %>'>
	    <td align="left">Aviso de Enmienda</td>
	    <td align="center"><input type="text" name="E01ADVAMF" value="<%=msg01.getE01ADVAMF()%>" readonly></td><td align="center"><select name="E01AAMPBY" disabled>
			<option value="A" <%if(msg01.getE01AAMPBY().equals("A")) out.print("selected");%>>Aplicante</option>
			<option value="B" <%if(msg01.getE01AAMPBY().equals("B")) out.print("selected");%>>Beneficiario</option>
			<option value="W" <%if(msg01.getE01AAMPBY().equals("W")) out.print("selected");%>>No Cobrar</option>
			</select></td></tr>
	  	  <tr id='<%= (i++ %2 == 0 ? "trclear" : "trdark") %>'>
	    <td align="left">Discrepancia</td>
	    <td align="center"><input type="text" name="E01DISCOM" value="<%=msg01.getE01DISCOM()%>" readonly></td><td align="center"><select name="E01DISPBY" disabled>
			<option value="A" <%if(msg01.getE01DISPBY().equals("A")) out.print("selected");%>>Aplicante</option>
			<option value="B" <%if(msg01.getE01DISPBY().equals("B")) out.print("selected");%>>Beneficiario</option>
			<option value="W" <%if(msg01.getE01DISPBY().equals("W")) out.print("selected");%>>No Cobrar</option>
			</select></td></tr>
	  <tr id='<%= (i++ %2 == 0 ? "trclear" : "trdark") %>'>
	    <td align="left">Extensi�n de Validez</td>
	    <td align="center"><input type="text" name="E01EOVCOM" value="<%=msg01.getE01EOVCOM()%>" readonly></td><td align="center"><select name="E01EOVPBY" disabled>
			<option value="A" <%if(msg01.getE01EOVPBY().equals("A")) out.print("selected");%>>Aplicante</option>
			<option value="B" <%if(msg01.getE01EOVPBY().equals("B")) out.print("selected");%>>Beneficiario</option>
			<option value="W" <%if(msg01.getE01EOVPBY().equals("W")) out.print("selected");%>>No Cobrar</option>
			</select></td></tr>
	  <tr id='<%= (i++ %2 == 0 ? "trclear" : "trdark") %>'>
	    <td align="left">Portes</td>
	    <td align="center"><input type="text" name="E01POSTAM" value="<%=msg01.getE01POSTAM()%>" readonly></td><td align="center"><select name="E01POSPBY" disabled>
			<option value="A" <%if(msg01.getE01POSPBY().equals("A")) out.print("selected");%>>Aplicante</option>
			<option value="B" <%if(msg01.getE01POSPBY().equals("B")) out.print("selected");%>>Beneficiario</option>
			<option value="W" <%if(msg01.getE01POSPBY().equals("W")) out.print("selected");%>>No Cobrar</option>
			</select></td></tr>
	<tr id='<%= (i++ %2 == 0 ? "trclear" : "trdark") %>'>
	    <td align="left">Courier</td>
	    <td align="center"><input type="text" name="E01COURAM" value="<%=msg01.getE01COURAM()%>" readonly></td><td align="center"><select name="E01COUPBY" disabled>
			<option value="A" <%if(msg01.getE01COUPBY().equals("A")) out.print("selected");%>>Aplicante</option>
			<option value="B" <%if(msg01.getE01COUPBY().equals("B")) out.print("selected");%>>Beneficiario</option>
			<option value="W" <%if(msg01.getE01COUPBY().equals("W")) out.print("selected");%>>No Cobrar</option>
			</select></td></tr>
	  	  <tr id='<%= (i++ %2 == 0 ? "trclear" : "trdark") %>'>
	    <td align="left">Swift de Apertura</td>
	    <td align="center"><input type="text" name="E01SWFFEE" value="<%=msg01.getE01SWFFEE()%>" readonly></td><td align="center"><select name="E01SWFPBY" disabled>
			<option value="A" <%if(msg01.getE01SWFPBY().equals("A")) out.print("selected");%>>Aplicante</option>
			<option value="B" <%if(msg01.getE01SWFPBY().equals("B")) out.print("selected");%>>Beneficiario</option>
			<option value="W" <%if(msg01.getE01SWFPBY().equals("W")) out.print("selected");%>>No Cobrar</option>
			</select></td></tr>
	  <tr id='<%= (i++ %2 == 0 ? "trclear" : "trdark") %>'>
	    <td align="left">Swift Adicional(es)</td>
	    <td align="center"><input type="text" name="E01TLXFEE" value="<%=msg01.getE01TLXFEE()%>" readonly></td><td align="center"><select name="E01TLXPBY" disabled>
			<option value="A" <%if(msg01.getE01TLXPBY().equals("A")) out.print("selected");%>>Aplicante</option>
			<option value="B" <%if(msg01.getE01TLXPBY().equals("B")) out.print("selected");%>>Beneficiario</option>
			<option value="W" <%if(msg01.getE01TLXPBY().equals("W")) out.print("selected");%>>No Cobrar</option>
			</select></td></tr>
	  <tr id='<%= (i++ %2 == 0 ? "trclear" : "trdark") %>'>
	    <td align="left">Cancelaci�n</td>
	    <td align="center"><input type="text" name="E01CANFEE" value="<%=msg01.getE01CANFEE()%>" readonly></td><td align="center"><select name="E01CANPBY" disabled>
			<option value="A" <%if(msg01.getE01CANPBY().equals("A")) out.print("selected");%>>Aplicante</option>
			<option value="B" <%if(msg01.getE01CANPBY().equals("B")) out.print("selected");%>>Beneficiario</option>
			<option value="W" <%if(msg01.getE01CANPBY().equals("W")) out.print("selected");%>>No Cobrar</option>
			</select></td></tr>
<tr id='<%= (i++ %2 == 0 ? "trclear" : "trdark") %>'>
	    <td align="left">Pago</td>
	    <td align="center"><input type="text" name="E01PAYCOM" value="<%=msg01.getE01PAYCOM()%>" readonly></td><td align="center"><select name="E01PMCPBY" disabled>
			<option value="A" <%if(msg01.getE01PMCPBY().equals("A")) out.print("selected");%>>Aplicante</option>
			<option value="B" <%if(msg01.getE01PMCPBY().equals("B")) out.print("selected");%>>Beneficiario</option>
			<option value="W" <%if(msg01.getE01PMCPBY().equals("W")) out.print("selected");%>>No Cobrar</option>
			</select></td></tr>
	<tr id='<%= (i++ %2 == 0 ? "trclear" : "trdark") %>'>
		<td align="left">Aceptacion</td><td align="center">
		<input type="text" name="E01ACPCOM" value="<%=msg01.getE01ACPCOM()%>" readonly></td><td align="center"><select name="E01ACCPBY" disabled>
			<option value="A" <%if(msg01.getE01ACCPBY().equals("A")) out.print("selected");%>>Aplicante</option>
			<option value="B" <%if(msg01.getE01ACCPBY().equals("B")) out.print("selected");%>>Beneficiario</option>
			<option value="W" <%if(msg01.getE01ACCPBY().equals("W")) out.print("selected");%>>No Cobrar</option>
			</select></td></tr>
	<tr id='<%= (i++ %2 == 0 ? "trclear" : "trdark") %>'>
		<td align="left">Pago Diferido</td><td align="center">
		<input type="text" name="E01DFPFEE" value="<%=msg01.getE01DFPFEE()%>" readonly></td><td align="center"><select name="E01DFPPBY" disabled>
			<option value="A" <%if(msg01.getE01DFPPBY().equals("A")) out.print("selected");%>>Aplicante</option>
			<option value="B" <%if(msg01.getE01DFPPBY().equals("B")) out.print("selected");%>>Beneficiario</option>
			<option value="W" <%if(msg01.getE01DFPPBY().equals("W")) out.print("selected");%>>No Cobrar</option>
			</select></td></tr>
	  	  <tr id='<%= (i++ %2 == 0 ? "trclear" : "trdark") %>'>
	    <td align="left">Transferencia de Fondos</td>
	    <td align="center"><input type="text" name="E01WTRCOM" value="<%=msg01.getE01WTRCOM()%>" readonly></td><td align="center"><select name="E01WTRPBY" disabled>
			<option value="A" <%if(msg01.getE01WTRPBY().equals("A")) out.print("selected");%>>Aplicante</option>
			<option value="B" <%if(msg01.getE01WTRPBY().equals("B")) out.print("selected");%>>Beneficiario</option>
			<option value="W" <%if(msg01.getE01WTRPBY().equals("W")) out.print("selected");%>>No Cobrar</option>
			</select></td></tr>
	  <tr id='<%= (i++ %2 == 0 ? "trclear" : "trdark") %>'>
	    <td align="left">Reembolso</td>
	    <td align="center"><input type="text" name="E01RMBCOM" value="<%=msg01.getE01RMBCOM()%>" readonly></td><td align="center"><select name="E01RMBPBY" disabled>
			<option value="A" <%if(msg01.getE01RMBPBY().equals("A")) out.print("selected");%>>Aplicante</option>
			<option value="B" <%if(msg01.getE01RMBPBY().equals("B")) out.print("selected");%>>Beneficiario</option>
			<option value="W" <%if(msg01.getE01RMBPBY().equals("W")) out.print("selected");%>>No Cobrar</option>
			</select></td></tr>
	<tr id='<%= (i++ %2 == 0 ? "trclear" : "trdark") %>'>
	    <td align="left">Comision Por Plazo</td>
	    <td align="center"><input type="text" name="E01TRMCOM" value="<%=msg01.getE01TRMCOM()%>" readonly></td><td align="center"><select name="E01TRMPBY" disabled>
			<option value="A" <%if(msg01.getE01TRMPBY().equals("A")) out.print("selected");%>>Aplicante</option>
			<option value="B" <%if(msg01.getE01TRMPBY().equals("B")) out.print("selected");%>>Beneficiario</option>
			<option value="W" <%if(msg01.getE01TRMPBY().equals("W")) out.print("selected");%>>No Cobrar</option>
			</select></td></tr>
</tbody></table>
<% } %>
</form>

</body>
</html>
