
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.0 Transitional//EN">
<HTML>
<HEAD>
<TITLE>Negociaciones de Cartas de Creditos</TITLE>
<META HTTP-EQUIV="Content-Type" CONTENT="text/html; charset=ISO-8859-1">
<META NAME="GENERATOR" CONTENT="IBM WebSphere Page Designer V4.0 for Windows">
<META HTTP-EQUIV="Content-Style-Type" CONTENT="text/css">
<LINK HREF="<%=request.getContextPath()%>/pages/style.css" REL="stylesheet">
<%@ page import = "datapro.eibs.master.Util" %>

<jsp:useBean id="msg" class="datapro.eibs.beans.ELC056001Message" scope="session" />
<jsp:useBean id="error" class="datapro.eibs.beans.ELEERRMessage" scope="session" />
<jsp:useBean id="userPO" class="datapro.eibs.beans.UserPos" scope="session" />

<SCRIPT language="Javascript1.1" src="<%=request.getContextPath()%>/pages/s/javascripts/eIBS.jsp"> </SCRIPT>
<SCRIPT language="Javascript1.1" src="<%=request.getContextPath()%>/pages/s/javascripts/optMenu.jsp"> </SCRIPT>

<SCRIPT LANGUAGE="javascript">
	builtNewMenu(lc_nego_apr);
	initMenu();
</SCRIPT>

<%userPO.setOption("");%>
</HEAD>

<BODY>

<H3 align="center">Pago/Negociación de Cartas de Crédito
<IMG src="<%=request.getContextPath()%>/images/e_ibs.gif" align="left" name="EIBS_GIF"
alt="ELC0100_lc_nego_view.jsp"></H3>
<HR size="4">

<FORM method="post" action="<%=request.getContextPath()%>/servlet/datapro.eibs.products.JSELC0560" >
    <INPUT name="SCREEN" TYPE="HIDDEN" value="3" readonly="readonly">
<!--    <INPUT TYPE="HIDDEN" name="" value="0005">
    <INPUT TYPE="HIDDEN" ame="E01DRWNUM" value="<%=msg.getE01DRWNUM()%>">
    <INPUT TYPE="HIDDEN" name="E01OPCODE" value="<%=msg.getE01OPCODE()%>">
	<INPUT type="hidden" name="E01LCRFXR" value="<%=msg.getE01LCRFXR()%>">
	<INPUT type="hidden" name="E01CUSFXR" value="<%=msg.getE01CUSFXR()%>">
	<INPUT type="hidden" name="E01PMTFXR" value="<%=msg.getE01PMTFXR()%>">
	<INPUT TYPE="HIDDEN" NAME="prevPage" VALUE="ELC0560_lc_nego_acp.jsp">
	<INPUT TYPE="HIDDEN" NAME="nextPage" VALUE="ELC0560_lc_nego_conf.jsp">
-->	


    <TABLE class="tableinfo">
    <TR bgcolor="#ffffff"> 
      <TD nowrap height="79" width="100%">
		<TABLE cellspacing="0" cellpadding="2" width="100%" border="0">
			        <TR ID="trdark">
                      <TD ALIGN="left" VALIGN="top" HEIGHT="29">Tipo de Negociaci&oacute;n:</TD>
			          <TD NOWRAP VALIGN="top" HEIGHT="29"><INPUT TYPE="text" NAME="E01OPCODE" SIZE="4" MAXLENGTH="1" VALUE="<%= msg.getE01OPCODE()%>" READONLY>
                          <INPUT TYPE="text" NAME="E01OPCDSC" SIZE="31" MAXLENGTH="30" VALUE="<%= msg.getE01OPCDSC()%>" READONLY></TD>
			          <TD NOWRAP ALIGN="left" VALIGN="top" HEIGHT="29">Producto:</TD>
			          <TD NOWRAP ALIGN="left" VALIGN="top" HEIGHT="29"><INPUT type="text" name="E01LCMPRO" size="8" maxlength="8"
					value="<%= msg.getE01LCMPRO()%>" readonly></TD>
          </TR>
					<TR ID="trclear">
						<TD ALIGN="left" VALIGN="top" HEIGHT="24" WIDTH="128">Carta de Crédito:</TD>
						<TD NOWRAP VALIGN="top" HEIGHT="24" WIDTH="284"><INPUT TYPE="text" NAME="E01LCRNUM" SIZE="23" MAXLENGTH="12"
							VALUE="<%= msg.getE01LCRNUM()%>" READONLY></TD>
						<TD NOWRAP ALIGN="left" VALIGN="top" HEIGHT="24" WIDTH="56">Moneda:</TD>
						<TD NOWRAP ALIGN="left" VALIGN="top" HEIGHT="24" WIDTH="291"><INPUT TYPE="text" NAME="E01LCMCCY" SIZE="8" MAXLENGTH="3"
							VALUE="<%= msg.getE01LCMCCY()%>" READONLY>
						&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;Monto:<INPUT type="text" name="E01DRWAMN"
					size="18" maxlength="15" value="<%= msg.getE01DRWAMN()%>" readonly></TD>
					</TR>
					<TR id="trdark">
                      <TD align="left" valign="top" height="24">Cliente:</TD>
					  <TD nowrap valign="top" height="24" align="left"><INPUT type="text" name="E01LCMCUN" size="20" maxlength="9" value="<%= msg.getE01LCMCUN()%>" readonly>
                      </TD>
					  <TD nowrap align="left" valign="top" height="24">Nombre:</TD>
					  <TD nowrap align="left" valign="top" height="24"><INPUT type="text" name="E01CUSNA1" size="46" maxlength="45" value="<%= msg.getE01CUSNA1()%>" readonly></TD>
		  </TR>
		</TABLE>
	  </TD>
	  </TR>
	</TABLE>



<P><B>Cuenta D&eacute;bito Principal</B></P>
<TABLE CLASS="tableinfo" CELLSPACING="0" CELLPADDING="2" WIDTH="100%" BORDER="0">
  <TR ID="trdark">
    <TD ALIGN="center" VALIGN="top" HEIGHT="24" WIDTH="230">Concepto</TD>
    <TD NOWRAP VALIGN="top" HEIGHT="24" ALIGN="center" WIDTH="51">Banco</TD>
    <TD NOWRAP VALIGN="top" HEIGHT="24" ALIGN="center" WIDTH="61">Agencia</TD>
    <TD NOWRAP ALIGN="center" VALIGN="top" HEIGHT="24" WIDTH="47">Mda</TD>
    <TD NOWRAP ALIGN="center" VALIGN="top" HEIGHT="24" WIDTH="128">Cuenta Contable</TD>
    <TD NOWRAP ALIGN="center" VALIGN="top" HEIGHT="24" WIDTH="132">Cuenta</TD>
    <TD NOWRAP ALIGN="center" VALIGN="top" HEIGHT="24" WIDTH="114">Centro de Costo</TD>
  </TR>
  <TR id="trclear">
    <TD width="230" height="24" align="left" valign="top" nowrap><INPUT name="E01CONCDR" type="text" value="<%= msg.getE01CONCDR()%>" size="4" maxlength="2" readonly="readonly">
        <INPUT name="E01CONCDD" type="text" value="<%=msg.getE01CONCDD()%>" size="25" maxlength="25" readonly="readonly"></TD>
    <TD width="51" height="24" align="center" valign="top" nowrap><INPUT name="E01CUSBNK" type="text" value="<%= msg.getE01CUSBNK()%>" size="4" maxlength="2" readonly="readonly"></TD>
    <TD width="61" height="24" align="center" valign="top" nowrap><INPUT name="E01CUSBRN" type="text" onKeyPress="enterInteger()" value="<%= msg.getE01CUSBRN()%>" size="6" maxlength="3" readonly="readonly"></TD>
    <TD width="47" height="24" align="center" valign="top" nowrap><INPUT name="E01CUSCCY" type="text" value="<%= msg.getE01CUSCCY()%>" size="6" maxlength="3" readonly="readonly"></TD>
    <TD width="128" height="24" align="center" valign="top" nowrap><INPUT name="E01CUSGLN" type="text" onKeyPress="enterInteger()" value="<%= msg.getE01CUSGLN()%>" size="20" maxlength="16" readonly="readonly"></TD>
    <TD width="132" height="24" align="center" valign="top" nowrap><INPUT name="E01CUSACC" type="text" value="<%= msg.getE01CUSACC()%>" size="18" maxlength="12" readonly="readonly"></TD>
    <TD width="114" height="24" align="center" valign="top" nowrap><INPUT name="E01CUSCCN" type="text" value="<%= msg.getE01CUSCCN()%>" size="13" maxlength="8" readonly="readonly"></TD>
  </TR>
</TABLE>
<P><B>Cuenta D&eacute;bito Comisiones e Impuestos </B></P>
<TABLE class="tableinfo" cellspacing="0" cellpadding="2" width="100%" border="0">
  <TR id="trdark">
    <TD align="center" valign="top" height="24" width="230">Concepto</TD>
    <TD nowrap valign="top" height="24" align="center" width="51">Banco</TD>
    <TD nowrap valign="top" height="24" align="center" width="61">Agencia</TD>
    <TD nowrap align="center" valign="top" height="24" width="47">Mda</TD>
    <TD nowrap align="center" valign="top" height="24" width="128">Cuenta Contable</TD>
    <TD nowrap align="center" valign="top" height="24" width="132">Cuenta</TD>
    <TD nowrap align="center" valign="top" height="24" width="114">Centro de Costo</TD>
  </TR>
  <TR id="trclear">
    <TD width="230" height="24" align="left" valign="top" nowrap><INPUT name="E01CONDCO" type="text" value="<%=msg.getE01CONDCO()%>" size="4" maxlength="2" readonly="readonly">
        <INPUT name="E01CONDCB" type="text" value="<%=msg.getE01CONDCB()%>" size="25" maxlength="25" readonly="readonly"></TD>
    <TD nowrap valign="top" height="24" align="center" width="51"><INPUT name="E01DEBBK2" type="text" value="<%=msg.getE01DEBBK2()%>" size="4" maxlength="2" readonly="readonly"></TD>
    <TD nowrap valign="top" height="24" align="center" width="61"><INPUT name="E01DEBBR2" type="text" onKeyPress="enterInteger()" value="<%=msg.getE01DEBBR2()%>" size="6" maxlength="3" readonly="readonly"></TD>
    <TD nowrap align="center" valign="top" height="24" width="47"><INPUT name="E01DEBCY2" type="text" value="<%=msg.getE01DEBCY2()%>" size="6" maxlength="3" readonly="readonly"></TD>
    <TD nowrap align="center" valign="top" height="24" width="128"><INPUT name="E01DEBGL2" type="text" onKeyPress="enterInteger()" value="<%=msg.getE01DEBGL2()%>" size="20" maxlength="16" readonly="readonly"></TD>
    <TD nowrap align="center" valign="top" height="24" width="132"><INPUT name="E01DEBAC2" type="text" value="<%=msg.getE01DEBAC2()%>" size="18" maxlength="12" readonly="readonly"></TD>
    <TD nowrap align="center" valign="top" height="24" width="114"><INPUT name="E01DEBCC2" type="text" value="<%=msg.getE01DEBCC2()%>" size="13" maxlength="8" readonly="readonly"></TD>
  </TR>
</TABLE>
<P><B>Cuenta Cr&eacute;dito</B></P>
<TABLE class="tableinfo" cellspacing="0" cellpadding="2" width="100%" border="0">
  <TR id="trdark">
    <TD align="center" valign="top" height="24" width="230">Concepto</TD>
    <TD nowrap valign="top" height="24" align="center" width="51">Banco</TD>
    <TD nowrap valign="top" height="24" align="center" width="61">Agencia</TD>
    <TD nowrap align="center" valign="top" height="24" width="47">Mda</TD>
    <TD nowrap align="center" valign="top" height="24" width="128">Cuenta Contable</TD>
    <TD nowrap align="center" valign="top" height="24" width="132">Cuenta</TD>
    <TD nowrap align="center" valign="top" height="24" width="114">Centro de Costo</TD>
  </TR>
  <TR id="trclear">
    <TD width="230" height="24" align="left" valign="top" nowrap><INPUT name="E01CONCCR" type="text" value="<%= msg.getE01CONCCR()%>" size="4" maxlength="2" readonly="readonly">
        <INPUT name="E01CONCCD" type="text" value="<%= msg.getE01CONCCD()%>" size="25" maxlength="25" readonly="readonly"></TD>
    <TD nowrap valign="top" height="24" align="center" width="51"><INPUT name="E01PMTBNK" type="text" value="<%= msg.getE01PMTBNK()%>" size="4" maxlength="2" readonly="readonly"></TD>
    <TD nowrap valign="top" height="24" align="center" width="61"><INPUT name="E01PMTBRN" type="text" onKeyPress="enterInteger()" value="<%= msg.getE01PMTBRN()%>" size="6" maxlength="3" readonly="readonly"></TD>
    <TD nowrap align="center" valign="top" height="24" width="47"><INPUT name="E01PMTCCY" type="text" value="<%= msg.getE01PMTCCY()%>" size="6" maxlength="3" readonly="readonly"></TD>
    <TD nowrap align="center" valign="top" height="24" width="128"><INPUT name="E01PMTGLN" type="text" onKeyPress="enterInteger()" value="<%= msg.getE01PMTGLN()%>" size="20" maxlength="16" readonly="readonly"></TD>
    <TD nowrap align="center" valign="top" height="24" width="132"><INPUT name="E01PMTACC" type="text" value="<%= msg.getE01PMTACC()%>" size="18" maxlength="12" readonly="readonly"></TD>
    <TD nowrap align="center" valign="top" height="24" width="114"><INPUT name="E01PMTCCN" type="text" value="<%= msg.getE01PMTCCN()%>" size="13" maxlength="8" readonly="readonly"></TD>
  </TR>
</TABLE>
<P><B>Cuenta Banco Corresponsal </B></P>
<TABLE class="tableinfo" cellspacing="0" cellpadding="2" width="100%" border="0">
  <TR id="trdark">
    <TD align="center" valign="top" height="24" width="230">Concepto</TD>
    <TD nowrap valign="top" height="24" align="center" width="51">Banco</TD>
    <TD nowrap valign="top" height="24" align="center" width="61">Agencia</TD>
    <TD nowrap align="center" valign="top" height="24" width="47">Mda</TD>
    <TD nowrap align="center" valign="top" height="24" width="128">Cuenta Contable</TD>
    <TD nowrap align="center" valign="top" height="24" width="132">Cuenta</TD>
    <TD nowrap align="center" valign="top" height="24" width="114">Centro de Costo</TD>
  </TR>
  <TR id="trclear">
    <TD width="230" height="24" align="left" valign="top" nowrap><INPUT name="E01CONCBC" type="text" value="<%=msg.getE01CONCBC()%>" size="4" maxlength="2" readonly="readonly">
        <INPUT name="E01CONCBD" type="text"  value="<%=msg.getE01CONCBD()%>" size="25" maxlength="25" readonly="readonly"></TD>
    <TD nowrap valign="top" height="24" align="center" width="51"><INPUT name="E01CRPBNK" type="text" value="<%=msg.getE01CRPBNK()%>" size="4" maxlength="2" readonly="readonly"></TD>
    <TD nowrap valign="top" height="24" align="center" width="61"><INPUT name="E01CRPBRN" type="text" onKeyPress="enterInteger()" value="<%=msg.getE01CRPBRN()%>" size="6" maxlength="3" readonly="readonly"></TD>
    <TD nowrap align="center" valign="top" height="24" width="47"><INPUT name="E01CRPCCY" type="text" value="<%=msg.getE01CRPCCY()%>" size="6" maxlength="3" readonly="readonly"></TD>
    <TD nowrap align="center" valign="top" height="24" width="128"><INPUT name="E01CRPGLN" type="text" onKeyPress="enterInteger()" value="<%=msg.getE01CRPGLN()%>" size="20" maxlength="16" readonly="readonly"></TD>
    <TD nowrap align="center" valign="top" height="24" width="132"><INPUT name="E01CRPACC" type="text" value="<%=msg.getE01CRPACC()%>" size="18" maxlength="12" readonly="readonly"></TD>
    <TD nowrap align="center" valign="top" height="24" width="114"><INPUT name="E01CRPCCN" type="text" value="<%=msg.getE01CRPCCN()%>" size="13" maxlength="8" readonly="readonly"></TD>
  </TR>
</TABLE>
<P></P>
<%int i = 0;%>
<TABLE class="tableinfo" cellspacing="0" cellpadding="2" width="100%" border="0">
	<TR id="<%out.print((i++%2==0 ? "trdark" : "trclear"));%>">
		<TD align="right">Forma / Vía de Pago:</TD>
		<TD nowrap valign="top" align="left" width="115"><SELECT
			name="E01PMTVIA" disabled>
			<OPTION value="">&nbsp;</OPTION>
				<!--	<OPTION value="1" <%if (msg.getE01PMTVIA().equals("1")) { out.print("selected"); }%>>Cheque Oficial</OPTION>-->
			<OPTION value="2" <%if (msg.getE01PMTVIA().equals("2")) { out.print("selected"); }%>>Depósito Cta. Cte.</OPTION>
			<OPTION value="3" <%if (msg.getE01PMTVIA().equals("3")) { out.print("selected"); }%>>Cuenta Contable</OPTION>
			<OPTION value="5" <%if (msg.getE01PMTVIA().equals("5")) { out.print("selected"); }%>>Swift</OPTION>
			</SELECT></TD>
		<TD nowrap align="right">Formato Swift:</TD>
		<TD nowrap align="left">
		<SELECT name="E01SWFFMT" disabled>
			<OPTION value="">&nbsp;</OPTION>
			<OPTION value="103" <%if (msg.getE01SWFFMT().equals("103")) { out.print("selected"); }%>>MT03</OPTION>
			<OPTION value="202" <%if (msg.getE01SWFFMT().equals("202")) { out.print("selected"); }%>>MT02</OPTION>
		</SELECT></TD>
	</TR>
	<TR id="<%out.print((i++%2==0 ? "trdark" : "trclear"));%>">
		<TD align="right">Debitar Comisiones a Cta Beneficiario:</TD>
		<TD nowrap valign="top" align="left">
			<INPUT name="E01DEBFLG" type="radio" value="Y" readonly="readonly" <%if (msg.getE01DEBFLG().equals("Y")) out.print("checked"); %>> Si 
			<INPUT name="E01DEBFLG" type="radio" value="N" readonly="readonly" <%if (msg.getE01DEBFLG().equals("N")) out.print("checked"); %>> No				</TD>
		<TD nowrap align="right">Cta. Cte. Beneficiario:</TD>
		<TD nowrap align="left">
			<INPUT name="E01LCMBAC" type="text" size="16" maxlength="12" readonly="readonly"></TD>
	</TR>
	<TR id="<%out.print((i++%2==0 ? "trdark" : "trclear"));%>">
	  <TD nowrap align="right" valign="top" height="24">Cancelar Saldo Remanente:</TD>
	  <TD nowrap align="left" valign="top" height="24"><INPUT name="E01CANBAL" type="radio" value="Y" readonly="readonly" <%if (msg.getE01CANBAL().equals("Y")) out.print("checked"); %>>
	    Si
	    <INPUT name="E01CANBAL" type="radio" value="N" readonly="readonly" <%if (msg.getE01CANBAL().equals("N")) out.print("checked"); %>>
	    No </TD>
		<TD nowrap align="right">Garantía en Efectivo:</TD>
		<TD nowrap align="left"><INPUT name="E01CSHAMN" type="text" size="18" maxlength="15" readonly="readonly"></TD>
	</TR>
	<TR id="<%out.print((i++ %2==0 ? "trdark" : "trclear"));%>">
		<TD nowrap align="right" valign="top" height="24">Fecha de Aceptacion:</TD>
		<TD nowrap align="left" valign="top" height="24">
		<INPUT name="E01DRFDTM" value="<%=msg.getE01DRFDTM()%>" size="2" maxlength="2" readonly="readonly" >
		<INPUT name="E01DRFDTD" value="<%=msg.getE01DRFDTD()%>" size="2" maxlength="2" readonly="readonly" >
		<INPUT name="E01DRFDTY" value="<%=msg.getE01DRFDTY()%>" size="2" maxlength="2" readonly="readonly" >
		<A href="javascript:DatePicker(document.forms[0].E01DRFDTM,document.forms[0].E01DRFDTD,document.forms[0].E01DRFDTY)">
		<IMG src="<%=request.getContextPath()%>/images/calendar.gif" alt="help" align="middle" border="0"></A></TD>
		<TD nowrap align="right">Fecha Valor:</TD>
		<TD nowrap align="left">
		<INPUT name="E01VALDTM" value="<%=msg.getE01VALDTM()%>" size="2" maxlength="2" readonly="readonly" >
		<INPUT name="E01VALDTD" value="<%=msg.getE01VALDTD()%>" size="2" maxlength="2" readonly="readonly" >
		<INPUT name="E01VALDTY" value="<%=msg.getE01VALDTY()%>" size="2" maxlength="2" readonly="readonly" >
		<A href="javascript:DatePicker(document.forms[0].E01VALDTM,document.forms[0].E01VALDTD,document.forms[0].E01VALDTY)">
		<IMG src="<%=request.getContextPath()%>/images/calendar.gif" alt="help" align="middle" border="0"></A></TD>
	</TR>
	<TR id="<%out.print((i++ %2==0 ? "trdark" : "trclear"));%>">
		<TD nowrap align="right" valign="top" height="24">Plazo (Nro. de Dias):</TD>
		<TD nowrap align="left" valign="top" height="24">
		<INPUT name="E01NUMDYS" value="<%=msg.getE01NUMDYS()%>" size="4" maxlength="4" readonly="readonly" ></TD>
		<TD nowrap align="right">Fecha de Vencimiento</TD>
		<TD nowrap align="left">
		<INPUT name="E01MATDTM" value="<%=msg.getE01MATDTM()%>" size="2" maxlength="2" readonly="readonly" >
		<INPUT name="E01MATDTD" value="<%=msg.getE01MATDTD()%>" size="2" maxlength="2" readonly="readonly" >
		<INPUT name="E01MATDTY" value="<%=msg.getE01MATDTY()%>" size="2" maxlength="2" readonly="readonly" >
		<A href="javascript:DatePicker(document.forms[0].E01MATDTM,document.forms[0].E01MATDTD,document.forms[0].E01MATDTY)">
		<IMG src="<%=request.getContextPath()%>/images/calendar.gif" alt="help"  border="0"></A></TD>
	</TR>
	<TR id="<%out.print((i++ %2==0 ? "trdark" : "trclear"));%>">
		<TD nowrap align="right" valign="top" height="24">Codigo de Producto</TD>
		<TD nowrap align="left" valign="top" height="24">
		<INPUT name="E01NEWPRO" value="<%=msg.getE01NEWPRO()%>" size="4" maxlength="4" readonly="readonly" >
		<A href="javascript: GetProduct('E01NEWPRO','<%=(msg.getE01OPCODE().equals("5") ? "10" : "14")%>','<%= msg.getE01CUSBNK()%>')"><IMG src="<%=request.getContextPath()%>/images/1b.gif" alt="Ayuda" align="bottom" border="0"></A>		</TD>
		<TD nowrap align="right">Base Anual</TD>
		<TD nowrap align="left">
		<INPUT name="E01PERBAS" value="<%=msg.getE01PERBAS()%>" size="4" maxlength="4" readonly="readonly" ></TD>
	</TR>
	
	<TR id="<%out.print((i++ %2==0 ? "trdark" : "trclear"));%>">
	  <TD align="right" nowrap>Tabla / Tipo de Tasa Flotante:</TD>
	  <TD nowrap><INPUT name="E01BNKFLT" type="text" value="<%= msg.getE01BNKFLT().trim()%>" size="2" maxlength="2" readonly="readonly">
          <SELECT name="E01INTTYP" disabled>
            <OPTION value=""> </OPTION>
            <OPTION value="FP" <% if(msg.getE01INTTYP().equals("FP")) out.print("selected");%>>Tasa Primaria</OPTION>
            <OPTION value="FS" <% if(msg.getE01INTTYP().equals("FS")) out.print("selected");%>>Tasa Secundaria</OPTION>
        </SELECT></TD>
	  <TD height="24" align="right" valign="top" nowrap>Tasa / Sobretasa </TD>
	  <TD height="24" align="left" valign="top" nowrap><INPUT name="E01INTRTE" type="text" onKeyPress="enterDecimal()" value="<%=msg.getE01INTRTE()%>" size="10" maxlength="9" readonly="readonly"></TD>
    </TR>
	<TR id="<%out.print((i++ %2==0 ? "trdark" : "trclear"));%>">
	  <TD height="24" align="right" valign="top" nowrap>Tipo de Interes </TD>
	  <TD height="24" align="left" valign="top" nowrap><INPUT name="E01INTFLG" type="text" value="<%=msg.getE01INTFLG()%>" size="1" maxlength="1" readonly="readonly"></TD>
	  <TD nowrap align="right">Intereses Pre-Pagados </TD>
	  <TD nowrap><INPUT name="E01DISCNT" type="radio" value="Y" readonly="readonly" <% if(msg.getE01DISCNT().equals("Y")) out.print("checked");%>>
	    Si
	    <INPUT name="E01DISCNT" type="radio" value="N" readonly="readonly" <% if(msg.getE01DISCNT().equals("N")) out.print("checked");%>>
	    No </TD>
	</TR>
	<TR id="<%out.print((i++ %2==0 ? "trdark" : "trclear"));%>">
	  <TD align="right" nowrap>Tasa de Interes de Mora </TD>
	  <TD nowrap><INPUT name="E01PENINT" type="text" value="<%=msg.getE01PENINT()%>" size="9" maxlength="7" readonly="readonly"></TD>
	  <TD height="24" align="right" valign="top" nowrap>Factor / Porcentaje de Mora </TD>
	  <TD height="24" align="left" valign="top" nowrap><SELECT name="E01PENFLG" disabled>
        <OPTION value=""> </OPTION>
        <OPTION value="F" <% if(msg.getE01PENFLG().equals("%")) out.print("selected");%>>Monto Fijo</OPTION>
        <OPTION value="%" <% if(msg.getE01PENFLG().equals("%")) out.print("selected");%>>porcentaje</OPTION>
      </SELECT></TD>
    </TR>
	<TR id="<%out.print((i++ %2==0 ? "trdark" : "trclear"));%>">
	  <TD align="right" nowrap>Calculo Interes Normal </TD>
	  <TD nowrap>
	  <SELECT name="E01INCLTP" disabled>
          <OPTION value=""> </OPTION>
          <OPTION value="1" <% if(msg.getE01INCLTP().equals("1")) out.print("selected");%>>Sobre Capital Vigente</OPTION>
          <OPTION value="2" <% if(msg.getE01INCLTP().equals("2")) out.print("selected");%>>Sobre Capital Original</OPTION>
          <OPTION value="3" <% if(msg.getE01INCLTP().equals("3")) out.print("selected");%>>Sobre Capital Vigente menos Vencido </OPTION>
          <OPTION value="N" <% if(msg.getE01INCLTP().equals("N")) out.print("selected");%>>No Calcula Intereses</OPTION>
      </SELECT>
	  </TD>
	  <TD align="right" nowrap>Calculo Interes de Mora </TD>
	  <TD nowrap><SELECT name="E01PNCLTP" disabled>
          <OPTION value=""> </OPTION>
          <OPTION value="1" <% if(msg.getE01PNCLTP().equals("1")) out.print("selected");%>>Sobre Capital Vencido</OPTION>
          <OPTION value="2" <% if(msg.getE01PNCLTP().equals("2")) out.print("selected");%>>Sobre Capital Original</OPTION>
          <OPTION value="3" <% if(msg.getE01PNCLTP().equals("3")) out.print("selected");%>>Sobre Capital Bigente</OPTION>
          <OPTION value="4" <% if(msg.getE01PNCLTP().equals("4")) out.print("selected");%>>Sobre Capital Venicdo + Int. Vencido</OPTION>
          <OPTION value="N" <% if(msg.getE01PNCLTP().equals("N")) out.print("selected");%>>No Calcula Mora</OPTION>
      </SELECT></TD>
	</TR>
	<TR id="<%out.print((i++ %2==0 ? "trdark" : "trclear"));%>">
	  <TD align="right" nowrap>Ciclo Pago Intereses </TD>
	  <TD nowrap><INPUT name="E01INPDYS" type="text" value="<%=msg.getE01INPDYS()%>" size="3" maxlength="3" readonly="readonly">
	  <A href="javascript:GetCode('E01INPDYS','STATIC_payment_cycle.jsp')">
	  <IMG src="/eIBS_R04M08/images/1b.gif" alt=". . ." align="absbottom" border="0"></A></TD>
	  <TD height="24" align="right" valign="top" nowrap>Fecha Proximo Pago Intereses </TD>
	  <TD height="24" align="left" valign="top" nowrap>
	  <INPUT name="E01NXIPDM" type="text" onKeyPress="enterInteger()" value="<%=msg.getE01NXIPDM()%>" size="2" maxlength="2" readonly="readonly">
          <INPUT name="E01NXIPDD" type="text" onKeyPress="enterInteger()" value="<%=msg.getE01NXIPDD()%>" size="2" maxlength="2" readonly="readonly">
          <INPUT name="E01NXIPDY" type="text" onKeyPress="enterInteger()" value="<%=msg.getE01NXIPDY()%>" size="2" maxlength="2" readonly="readonly">
        <A href="javascript:DatePicker(document.forms[0].E01NXIPDM,document.forms[0].E01NXIPDD,document.forms[0].E01NXIPDY)">
		<IMG src="<%=request.getContextPath()%>/images/calendar.gif" alt="help" align="middle" border="0"></A></TD>
    </TR>
	<TR id="<%out.print((i++ %2==0 ? "trdark" : "trclear"));%>">
      <TD align="right" nowrap>Ciclo Pago Principal </TD>
	  <TD nowrap><INPUT name="E01PRPDYS" type="text" value="<%=msg.getE01PRPDYS()%>" size="3" maxlength="3" readonly="readonly">
      <A href="javascript:GetCode('E01PRPDYS','STATIC_payment_cycle.jsp')">
	  <IMG src="/eIBS_R04M08/images/1b.gif" alt=". . ." align="absbottom" border="0"></A></TD>
	  <TD height="24" align="right" valign="top" nowrap>Fecha Proximo Pago Principal </TD>
	  <TD height="24" align="left" valign="top" nowrap>
	  <INPUT name="E01NXPPDM" type="text" onKeyPress="enterInteger()" value="<%=msg.getE01NXPPDM()%>" size="2" maxlength="2" readonly="readonly">
          <INPUT name="E01NXPPDD" type="text" onKeyPress="enterInteger()" value="<%=msg.getE01NXPPDD()%>" size="2" maxlength="2" readonly="readonly">
          <INPUT name="E01NXPPDY" type="text" onKeyPress="enterInteger()" value="<%=msg.getE01NXPPDY()%>" size="2" maxlength="2" readonly="readonly">
        <A href="javascript:DatePicker(document.forms[0].E01NXPPDM,document.forms[0].E01NXPPDD,document.forms[0].E01NXPPDY)">
		<IMG src="<%=request.getContextPath()%>/images/calendar.gif" alt="help" align="middle" border="0"></A></TD>
    </TR>
  </TABLE>

<P><B>Tipos de Cambio</B></P>
<TABLE class="tableinfo" cellspacing="0" cellpadding="2" width="100%" border="0">
  <TR id="trdark">
			<TD>Moneda</TD>
			<TD>T/C Compra</TD>
			<TD>T/C Venta</TD>
			<TD>Moneda</TD>
			<TD>T/C Compra</TD>
			<TD>T/C Venta</TD>
	</TR>
		<TR>
    <TD><INPUT name="E01FXCCY1" type="text" value="<%=msg.getE01FXCCY1()%>" size="9" maxlength="3" readonly="readonly"></TD>
    <TD><INPUT name="E01FXRPU1" type="text" value="<%=msg.getE01FXRPU1()%>" size="20" maxlength="11" readonly="readonly"></TD>
    <TD><INPUT name="E01FXRSA1" type="text" value="<%=msg.getE01FXRSA1()%>" size="20" maxlength="11" readonly="readonly"></TD>
    <TD><INPUT name="E01FXCCY2" type="text" value="<%=msg.getE01FXCCY2()%>" size="9" maxlength="3" readonly="readonly"></TD>
    <TD><INPUT name="E01FXRPU2" type="text" value="<%=msg.getE01FXRPU2()%>" size="20" maxlength="11" readonly="readonly"></TD>
    <TD><INPUT name="E01FXRSA2" type="text" value="<%=msg.getE01FXRSA2()%>" size="20" maxlength="11" readonly="readonly"></TD>
		</TR>
		<TR>
    <TD><INPUT name="E01FXCCY3" type="text" value="<%=msg.getE01FXCCY3()%>" size="9" maxlength="3" readonly="readonly"></TD>
    <TD><INPUT name="E01FXRPU3" type="text" value="<%=msg.getE01FXRPU3()%>" size="20" maxlength="11" readonly="readonly"></TD>
    <TD><INPUT name="E01FXRSA3" type="text" value="<%=msg.getE01FXRSA3()%>" size="20" maxlength="11" readonly="readonly"></TD>
    <TD><INPUT name="E01FXCCY4" type="text" value="<%=msg.getE01FXCCY4()%>" size="9" maxlength="3" readonly="readonly"></TD>
    <TD><INPUT name="E01FXRPU4" type="text" value="<%=msg.getE01FXRPU4()%>" size="20" maxlength="11" readonly="readonly"></TD>
    <TD><INPUT name="E01FXRSA4" type="text" value="<%=msg.getE01FXRSA4()%>" size="20" maxlength="11" readonly="readonly"></TD>
		</TR>
  </TABLE>

<%	i = 0;	%>
<P><B>Comisiones y Gastos</B></P>

<TABLE class="tableinfo" cellspacing="0" cellpadding="2" width="100%" border="0">
  <TR id="trdark">
			<TD>&nbsp;</TD>
    <TD colspan="2" align="center"><B>Nuestros</B></TD>
    <TD colspan="2" align="center"><B>Banco Corresponsal</B></TD>
	</TR>
  <TR id="trdark">
    <TD nowrap align="left">Comisi&oacute;n</TD>
    <TD nowrap align="center">Monto</TD>
    <TD nowrap align="center">Por</TD>
    <TD nowrap align="center">Monto</TD>
    <TD nowrap align="center">Por</TD>
	</TR>
	  <TR id='<%= (i++ %2 == 0 ? "trclear" : "trdark") %>'>
	    <TD align="left">Emisi&oacute;n</TD>
	    <TD align="center"><INPUT name="E01ISSFEE" type="text" id="E01ISSFEE" value="<%=msg.getE01ISSFEE()%>" readonly="readonly"></TD>
	    <TD align="center"><SELECT name="E01ISSPBY" disabled>
			<OPTION value="A">APLICANTE</OPTION>
			<OPTION value="B" <%if(msg.getE01ISSPBY().equals("B")) out.print("selected");%>>Beneficiario</OPTION>
			<OPTION value="W" <%if(msg.getE01ISSPBY().equals("W")) out.print("selected");%>>Condonar</OPTION>
			</SELECT></TD>
		    <TD align="center"><INPUT name="E01CRPISS" type="text" id="E01CRPISS" value="<%=msg.getE01CRPISS()%>" readonly="readonly"></TD>
	    <TD align="center"><SELECT name="E01ISSCPB" disabled>
			<OPTION value="A">APLICANTE</OPTION>
			<OPTION value="B" <%if(msg.getE01ISSCPB().equals("B")) out.print("selected");%>>Beneficiario</OPTION>
			<OPTION value="W" <%if(msg.getE01ISSCPB().equals("W")) out.print("selected");%>>Condonar</OPTION>
			</SELECT></TD>
	</TR>
	  	  <TR id='<%= (i++ %2 == 0 ? "trclear" : "trdark") %>'>
	    <TD align="left">Aviso</TD>
	    <TD align="center"><INPUT name="E01ADVCOM" type="text" value="<%=msg.getE01ADVCOM()%>" readonly="readonly"></TD>
	    <TD align="center"><SELECT name="E01ADVPBY" disabled>
			<OPTION value="A">APLICANTE</OPTION>
			<OPTION value="B" <%if(msg.getE01ADVPBY().equals("B")) out.print("selected");%>>Beneficiario</OPTION>
			<OPTION value="W" <%if(msg.getE01ADVPBY().equals("W")) out.print("selected");%>>Condonar</OPTION>
			</SELECT></TD>
		    <TD align="center"><INPUT name="E01CRPADV" type="text" value="<%=msg.getE01CRPADV()%>" readonly="readonly"></TD>
	    <TD align="center"><SELECT name="E01ADVCPB" disabled>
			<OPTION value="A">APLICANTE</OPTION>
			<OPTION value="B" <%if(msg.getE01ADVCPB().equals("B")) out.print("selected");%>>Beneficiario</OPTION>
			<OPTION value="W" <%if(msg.getE01ADVCPB().equals("W")) out.print("selected");%>>Condonar</OPTION>
			</SELECT></TD>
		</TR>
	  <TR id='<%= (i++ %2 == 0 ? "trclear" : "trdark") %>'>
	    <TD align="left">Confirmaci&oacute;n</TD>
	    <TD align="center"><INPUT name="E01CNFCOM" type="text" value="<%=msg.getE01CNFCOM()%>" readonly="readonly"></TD>
	    <TD align="center"><SELECT name="E01CNFPBY" disabled>
			<OPTION value="A">APLICANTE</OPTION>
			<OPTION value="B" <%if(msg.getE01CNFPBY().equals("B")) out.print("selected");%>>Beneficiario</OPTION>
			<OPTION value="W" <%if(msg.getE01CNFPBY().equals("W")) out.print("selected");%>>Condonar</OPTION>
			</SELECT></TD>
		    <TD align="center"><INPUT name="E01CRPCNF" type="text" value="<%=msg.getE01CRPCNF()%>" readonly="readonly"></TD>
	    <TD align="center"><SELECT name="E01CNFCPB" disabled>
			<OPTION value="A">APLICANTE</OPTION>
			<OPTION value="B" <%if(msg.getE01CNFCPB().equals("B")) out.print("selected");%>>Beneficiario</OPTION>
			<OPTION value="W" <%if(msg.getE01CNFCPB().equals("W")) out.print("selected");%>>Condonar</OPTION>
			</SELECT></TD>
	</TR>
	  <TR id='<%= (i++ %2 == 0 ? "trclear" : "trdark") %>'>
	    <TD align="left">Enmienda</TD>
	    <TD align="center"><INPUT name="E01AMDFEE" type="text" value="<%=msg.getE01AMDFEE()%>" readonly="readonly"></TD>
	    <TD align="center"><SELECT name="E01AMDPBY" disabled>
			<OPTION value="A">APLICANTE</OPTION>
			<OPTION value="B" <%if(msg.getE01AMDPBY().equals("B")) out.print("selected");%>>Beneficiario</OPTION>
			<OPTION value="W" <%if(msg.getE01AMDPBY().equals("W")) out.print("selected");%>>Condonar</OPTION>
			</SELECT></TD>
		    <TD align="center"><INPUT name="E01CRPAMD" type="text" value="<%=msg.getE01CRPAMD()%>" readonly="readonly"></TD>
	    <TD align="center"><SELECT name="E01AMDCPB" disabled>
			<OPTION value="A">APLICANTE</OPTION>
			<OPTION value="B" <%if(msg.getE01AMDCPB().equals("B")) out.print("selected");%>>Beneficiario</OPTION>
			<OPTION value="W" <%if(msg.getE01AMDCPB().equals("W")) out.print("selected");%>>Condonar</OPTION>
			</SELECT></TD>
	</TR>
<TR id='<%= (i++ %2 == 0 ? "trclear" : "trdark") %>'>
	    <TD align="left">Aviso de Enmienda</TD>
	    <TD align="center"><INPUT name="E01ADVAMF" type="text" value="<%=msg.getE01ADVAMF()%>" readonly="readonly"></TD>
	    <TD align="center"><SELECT name="E01AAMPBY" disabled>
			<OPTION value="A">APLICANTE</OPTION>
			<OPTION value="B" <%if(msg.getE01AAMPBY().equals("B")) out.print("selected");%>>Beneficiario</OPTION>
			<OPTION value="W" <%if(msg.getE01AAMPBY().equals("W")) out.print("selected");%>>Condonar</OPTION>
			</SELECT></TD>
		    <TD align="center"><INPUT name="E01CRPAAM" type="text" value="<%=msg.getE01CRPAAM()%>" readonly="readonly"></TD>
	    <TD align="center"><SELECT name="E01AAMCPB" disabled>
			<OPTION value="A">APLICANTE</OPTION>
			<OPTION value="B" <%if(msg.getE01AAMCPB().equals("B")) out.print("selected");%>>Beneficiario</OPTION>
			<OPTION value="W" <%if(msg.getE01AAMCPB().equals("W")) out.print("selected");%>>Condonar</OPTION>
			</SELECT></TD>
	</TR>
	  	  <TR id='<%= (i++ %2 == 0 ? "trclear" : "trdark") %>'>
	    <TD align="left">Discrepancia</TD>
	    <TD align="center"><INPUT name="E01DISCOM" type="text" value="<%=msg.getE01DISCOM()%>" readonly="readonly"></TD>
	    <TD align="center"><SELECT name="E01DISPBY" disabled>
			<OPTION value="A">APLICANTE</OPTION>
			<OPTION value="B" <%if(msg.getE01DISPBY().equals("B")) out.print("selected");%>>Beneficiario</OPTION>
			<OPTION value="W" <%if(msg.getE01DISPBY().equals("W")) out.print("selected");%>>Condonar</OPTION>
			</SELECT></TD>
		    <TD align="center"><INPUT name="E01CRPDIS" type="text" value="<%=msg.getE01CRPDIS()%>" readonly="readonly"></TD>
	    <TD align="center"><SELECT name="E01DISCPB" disabled>
			<OPTION value="A">APLICANTE</OPTION>
			<OPTION value="B" <%if(msg.getE01DISCPB().equals("B")) out.print("selected");%>>Beneficiario</OPTION>
			<OPTION value="W" <%if(msg.getE01DISCPB().equals("W")) out.print("selected");%>>Condonar</OPTION>
			</SELECT></TD>
		</TR>
	  <TR id='<%= (i++ %2 == 0 ? "trclear" : "trdark") %>'>
	    <TD align="left">Extensión de Validez</TD>
	    <TD align="center"><INPUT name="E01EOVCOM" type="text" value="<%=msg.getE01EOVCOM()%>" readonly="readonly"></TD>
	    <TD align="center"><SELECT name="E01EOVPBY" disabled>
			<OPTION value="A">APLICANTE</OPTION>
			<OPTION value="B" <%if(msg.getE01EOVPBY().equals("B")) out.print("selected");%>>Beneficiario</OPTION>
			<OPTION value="W" <%if(msg.getE01EOVPBY().equals("W")) out.print("selected");%>>Condonar</OPTION>
			</SELECT></TD>
		    <TD align="center"><INPUT name="E01CRPEOV" type="text" value="<%=msg.getE01CRPEOV()%>" readonly="readonly"></TD>
	    <TD align="center"><SELECT name="E01EOVCPB" disabled>
			<OPTION value="A">APLICANTE</OPTION>
			<OPTION value="B" <%if(msg.getE01EOVCPB().equals("B")) out.print("selected");%>>Beneficiario</OPTION>
			<OPTION value="W" <%if(msg.getE01EOVCPB().equals("W")) out.print("selected");%>>Condonar</OPTION>
			</SELECT></TD>
	</TR>
	  <TR id='<%= (i++ %2 == 0 ? "trclear" : "trdark") %>'>
	    <TD align="left">Portes</TD>
	    <TD align="center"><INPUT name="E01POSTAM" type="text" value="<%=msg.getE01POSTAM()%>" readonly="readonly"></TD>
	    <TD align="center"><SELECT name="E01POSPBY" disabled>
			<OPTION value="A">APLICANTE</OPTION>
			<OPTION value="B" <%if(msg.getE01POSPBY().equals("B")) out.print("selected");%>>Beneficiario</OPTION>
			<OPTION value="W" <%if(msg.getE01POSPBY().equals("W")) out.print("selected");%>>Condonar</OPTION>
			</SELECT></TD>
		    <TD align="center"><INPUT name="E01CRPPOS" type="text" value="<%=msg.getE01CRPPOS()%>" readonly="readonly"></TD>
	    <TD align="center"><SELECT name="E01POSCPB" disabled>
			<OPTION value="A">APLICANTE</OPTION>
			<OPTION value="B" <%if(msg.getE01POSCPB().equals("B")) out.print("selected");%>>Beneficiario</OPTION>
			<OPTION value="W" <%if(msg.getE01POSCPB().equals("W")) out.print("selected");%>>Condonar</OPTION>
			</SELECT></TD>
	</TR>
<TR id='<%= (i++ %2 == 0 ? "trclear" : "trdark") %>'>
	    <TD align="left">Courier</TD>
	    <TD align="center"><INPUT name="E01COURAM" type="text" value="<%=msg.getE01COURAM()%>" readonly="readonly"></TD>
	    <TD align="center"><SELECT name="E01COUPBY" disabled>
			<OPTION value="A">APLICANTE</OPTION>
			<OPTION value="B" <%if(msg.getE01COUPBY().equals("B")) out.print("selected");%>>Beneficiario</OPTION>
			<OPTION value="W" <%if(msg.getE01COUPBY().equals("W")) out.print("selected");%>>Condonar</OPTION>
			</SELECT></TD>
		    <TD align="center"><INPUT name="E01CRPCOU" type="text" value="<%=msg.getE01CRPCOU()%>" readonly="readonly"></TD>
	    <TD align="center"><SELECT name="E01COUCPB" disabled>
			<OPTION value="A">APLICANTE</OPTION>
			<OPTION value="B" <%if(msg.getE01COUCPB().equals("B")) out.print("selected");%>>Beneficiario</OPTION>
			<OPTION value="W" <%if(msg.getE01COUCPB().equals("W")) out.print("selected");%>>Condonar</OPTION>
			</SELECT></TD>
	</TR>
	  	  <TR id='<%= (i++ %2 == 0 ? "trclear" : "trdark") %>'>
	    <TD align="left">Swift de Apertura</TD>
	    <TD align="center"><INPUT name="E01SWFFEE" type="text" value="<%=msg.getE01SWFFEE()%>" readonly="readonly"></TD>
	    <TD align="center"><SELECT name="E01SWFPBY" disabled>
			<OPTION value="A">APLICANTE</OPTION>
			<OPTION value="B" <%if(msg.getE01SWFPBY().equals("B")) out.print("selected");%>>Beneficiario</OPTION>
			<OPTION value="W" <%if(msg.getE01SWFPBY().equals("W")) out.print("selected");%>>Condonar</OPTION>
			</SELECT></TD>
		    <TD align="center"><INPUT name="E01CRPSWF" type="text" value="<%=msg.getE01CRPSWF()%>" readonly="readonly"></TD>
	    <TD align="center"><SELECT name="E01SWFCPB" disabled>
			<OPTION value="A">APLICANTE</OPTION>
			<OPTION value="B" <%if(msg.getE01SWFCPB().equals("B")) out.print("selected");%>>Beneficiario</OPTION>
			<OPTION value="W" <%if(msg.getE01SWFCPB().equals("W")) out.print("selected");%>>Condonar</OPTION>
			</SELECT></TD>
		</TR>
	  <TR id='<%= (i++ %2 == 0 ? "trclear" : "trdark") %>'>
	    <TD align="left">Swift Adicional(es)</TD>
	    <TD align="center"><INPUT name="E01TLXFEE" type="text" value="<%=msg.getE01TLXFEE()%>" readonly="readonly"></TD>
	    <TD align="center"><SELECT name="E01TLXPBY" disabled>
			<OPTION value="A">APLICANTE</OPTION>
			<OPTION value="B" <%if(msg.getE01TLXPBY().equals("B")) out.print("selected");%>>Beneficiario</OPTION>
			<OPTION value="W" <%if(msg.getE01TLXPBY().equals("W")) out.print("selected");%>>Condonar</OPTION>
			</SELECT></TD>
		    <TD align="center"><INPUT name="E01CRPTLX" type="text" value="<%=msg.getE01CRPTLX()%>" readonly="readonly"></TD>
	    <TD align="center"><SELECT name="E01TLXCPB" disabled>
			<OPTION value="A">APLICANTE</OPTION>
			<OPTION value="B" <%if(msg.getE01TLXCPB().equals("B")) out.print("selected");%>>Beneficiario</OPTION>
			<OPTION value="W" <%if(msg.getE01TLXCPB().equals("W")) out.print("selected");%>>Condonar</OPTION>
			</SELECT></TD>
	</TR>
	  <TR id='<%= (i++ %2 == 0 ? "trclear" : "trdark") %>'>
	    <TD align="left">Cancelación</TD>
	    <TD align="center"><INPUT name="E01CANFEE" type="text" value="<%=msg.getE01CANFEE()%>" readonly="readonly"></TD>
	    <TD align="center"><SELECT name="E01CANPBY" disabled>
			<OPTION value="A">APLICANTE</OPTION>
			<OPTION value="B" <%if(msg.getE01CANPBY().equals("B")) out.print("selected");%>>Beneficiario</OPTION>
			<OPTION value="W" <%if(msg.getE01CANPBY().equals("W")) out.print("selected");%>>Condonar</OPTION>
			</SELECT></TD>
		    <TD align="center"><INPUT name="E01CRPCAN" type="text" value="<%=msg.getE01CRPCAN()%>" readonly="readonly"></TD>
	    <TD align="center"><SELECT name="E01CANCPB" disabled>
			<OPTION value="A">APLICANTE</OPTION>
			<OPTION value="B" <%if(msg.getE01CANCPB().equals("B")) out.print("selected");%>>Beneficiario</OPTION>
			<OPTION value="W" <%if(msg.getE01CANCPB().equals("W")) out.print("selected");%>>Condonar</OPTION>
			</SELECT></TD>
	</TR>
<TR id='<%= (i++ %2 == 0 ? "trclear" : "trdark") %>'>
	    <TD align="left">Pago</TD>
	    <TD align="center"><INPUT name="E01PAYCOM" type="text" value="<%=msg.getE01PAYCOM()%>" readonly="readonly"></TD>
	    <TD align="center"><SELECT name="E01PMCPBY" disabled>
			<OPTION value="A">APLICANTE</OPTION>
			<OPTION value="B" <%if(msg.getE01PMCPBY().equals("B")) out.print("selected");%>>Beneficiario</OPTION>
			<OPTION value="W" <%if(msg.getE01PMCPBY().equals("W")) out.print("selected");%>>Condonar</OPTION>
			</SELECT></TD>
		    <TD align="center"><INPUT name="E01CRPPAY" type="text" value="<%=msg.getE01CRPPAY()%>" readonly="readonly"></TD>
	    <TD align="center"><SELECT name="E01PMCCPB" disabled>
			<OPTION value="A">APLICANTE</OPTION>
			<OPTION value="B" <%if(msg.getE01PMCCPB().equals("B")) out.print("selected");%>>Beneficiario</OPTION>
			<OPTION value="W" <%if(msg.getE01PMCCPB().equals("W")) out.print("selected");%>>Condonar</OPTION>
			</SELECT></TD>
	</TR>
	  	  <TR id='<%= (i++ %2 == 0 ? "trclear" : "trdark") %>'>
	    <TD align="left">Transferencia de Fondos</TD>
	    <TD align="center"><INPUT name="E01WTRCOM" type="text" value="<%=msg.getE01WTRCOM()%>" readonly="readonly"></TD>
	    <TD align="center"><SELECT name="E01WTRPBY" disabled>
			<OPTION value="A">APLICANTE</OPTION>
			<OPTION value="B" <%if(msg.getE01WTRPBY().equals("B")) out.print("selected");%>>Beneficiario</OPTION>
			<OPTION value="W" <%if(msg.getE01WTRPBY().equals("W")) out.print("selected");%>>Condonar</OPTION>
			</SELECT></TD>
		    <TD align="center"><INPUT name="E01CRPWTR" type="text" value="<%=msg.getE01CRPWTR()%>" readonly="readonly"></TD>
	    <TD align="center"><SELECT name="E01WTRCPB" disabled>
			<OPTION value="A">APLICANTE</OPTION>
			<OPTION value="B" <%if(msg.getE01WTRCPB().equals("B")) out.print("selected");%>>Beneficiario</OPTION>
			<OPTION value="W" <%if(msg.getE01WTRCPB().equals("W")) out.print("selected");%>>Condonar</OPTION>
			</SELECT></TD>
		</TR>
	  <TR id='<%= (i++ %2 == 0 ? "trclear" : "trdark") %>'>
	    <TD align="left">Reembolso</TD>
	    <TD align="center"><INPUT name="E01RMBCOM" type="text" value="<%=msg.getE01RMBCOM()%>" readonly="readonly"></TD>
	    <TD align="center"><SELECT name="E01RMBPBY" disabled>
			<OPTION value="A">APLICANTE</OPTION>
			<OPTION value="B" <%if(msg.getE01RMBPBY().equals("B")) out.print("selected");%>>Beneficiario</OPTION>
			<OPTION value="W" <%if(msg.getE01RMBPBY().equals("W")) out.print("selected");%>>Condonar</OPTION>
			</SELECT></TD>
		    <TD align="center"><INPUT name="E01CRPRMB" type="text" value="<%=msg.getE01CRPRMB()%>" readonly="readonly"></TD>
	    <TD align="center"><SELECT name="E01RMBCPB" disabled>
			<OPTION value="A">APLICANTE</OPTION>
			<OPTION value="B" <%if(msg.getE01RMBCPB().equals("B")) out.print("selected");%>>Beneficiario</OPTION>
			<OPTION value="W" <%if(msg.getE01RMBCPB().equals("W")) out.print("selected");%>>Condonar</OPTION>
			</SELECT></TD>
	</TR>
	  <TR id='<%= (i++ %2 == 0 ? "trclear" : "trdark") %>'>
	    <TD align="left">Comision Por Plazo</TD>
	    <TD align="center"><INPUT name="E01TRMCOM" type="text" value="<%=msg.getE01TRMCOM()%>" readonly="readonly"></TD>
	    <TD align="center"><SELECT name="E01TRMPBY" disabled>
			<OPTION value="A">APLICANTE</OPTION>
			<OPTION value="B" <%if(msg.getE01TRMPBY().equals("B")) out.print("selected");%>>Beneficiario</OPTION>
			<OPTION value="W" <%if(msg.getE01TRMPBY().equals("W")) out.print("selected");%>>Condonar</OPTION>
			</SELECT></TD>
		    <TD align="center">&nbsp;</TD>
	    <TD align="center">&nbsp;</TD>
	</TR>
<TR id='<%= (i++ %2 == 0 ? "trclear" : "trdark") %>'>
	    <TD align="left">	<% if(msg.getE01LCCFL1().equals("Y"))  out.print("Convenio ALADI");
	    					else out.print("Timbres");%>
	    </TD>
	    <TD align="center"><INPUT name="E01ALDCOM" type="text" value="<%=msg.getE01ALDCOM()%>" readonly="readonly"></TD>
	    <TD align="center"><SELECT name="E01ALDPBY" disabled>
			<OPTION value="A">APLICANTE</OPTION>
			<OPTION value="B" <%if(msg.getE01ALDPBY().equals("B")) out.print("selected");%>>Beneficiario</OPTION>
			<OPTION value="W" <%if(msg.getE01ALDPBY().equals("W")) out.print("selected");%>>Condonar</OPTION>
			</SELECT></TD>
		    <TD align="center"><INPUT name="E01CRPALD" type="text" value="<%=msg.getE01CRPALD()%>" readonly="readonly"></TD>
	    <TD align="center"><SELECT name="E01ALDCPB" disabled>
			<OPTION value="A">APLICANTE</OPTION>
			<OPTION value="B" <%if(msg.getE01ALDCPB().equals("B")) out.print("selected");%>>Beneficiario</OPTION>
			<OPTION value="W" <%if(msg.getE01ALDCPB().equals("W")) out.print("selected");%>>Condonar</OPTION>
			</SELECT></TD>
	</TR>
	  	  <TR id='<%= (i++ %2 == 0 ? "trclear" : "trdark") %>'>
	    <TD align="left">Comisión de Agente</TD>
	    <TD align="center"><INPUT name="E01BRKCOM" type="text" value="<%=msg.getE01BRKCOM()%>" readonly="readonly"></TD>
	    <TD align="center"><SELECT name="E01BRKPBY" disabled>
			<OPTION value="A">APLICANTE</OPTION>
			<OPTION value="B" <%if(msg.getE01BRKPBY().equals("B")) out.print("selected");%>>Beneficiario</OPTION>
			<OPTION value="W" <%if(msg.getE01BRKPBY().equals("W")) out.print("selected");%>>Condonar</OPTION>
			</SELECT></TD>
		    <TD align="center">&nbsp;</TD>
	    <TD align="center">&nbsp;</TD>
		</TR>
	  <TR id='<%= (i++ %2 == 0 ? "trclear" : "trdark") %>'>
	    <TD align="left">Intereses</TD>
	    <TD align="center"><INPUT name="E01INTCHG" type="text" value="<%=msg.getE01INTCHG()%>" readonly="readonly"></TD>
	    <TD align="center"><SELECT name="E01INTPBY" disabled>
			<OPTION value="A">APLICANTE</OPTION>
			<OPTION value="B" <%if(msg.getE01INTPBY().equals("B")) out.print("selected");%>>Beneficiario</OPTION>
			<OPTION value="W" <%if(msg.getE01INTPBY().equals("W")) out.print("selected");%>>Condonar</OPTION>
			</SELECT></TD>
		    <TD align="center">&nbsp;</TD>
	    <TD align="center">&nbsp;</TD>
	</TR>
	<TR id='<%= (i++ %2 == 0 ? "trclear" : "trdark") %>'>
		<TD align="left">Aceptacion</TD><TD align="center">
		<INPUT name="E01ACPCOM" type="text" value="<%=msg.getE01ACPCOM()%>" readonly="readonly"></TD>
		<TD align="center"><SELECT name="E01ACCPBY" disabled>
			<OPTION value="A">APLICANTE</OPTION>
			<OPTION value="B" <%if(msg.getE01ACCPBY().equals("B")) out.print("selected");%>>Beneficiario</OPTION>
			<OPTION value="W" <%if(msg.getE01ACCPBY().equals("W")) out.print("selected");%>>Condonar</OPTION>
			</SELECT></TD><TD align="center">
			<INPUT name="E01CRPACP" type="text" value="<%=msg.getE01CRPACP()%>" readonly="readonly"></TD>
			<TD align="center"><SELECT name="E01ACCCPB" disabled>
			<OPTION value="A">APLICANTE</OPTION>
			<OPTION value="B" <%if(msg.getE01ACCCPB().equals("B")) out.print("selected");%>>Beneficiario</OPTION>
			<OPTION value="W" <%if(msg.getE01ACCCPB().equals("W")) out.print("selected");%>>Condonar</OPTION>
			</SELECT></TD>			
	</TR></TABLE>
	
		<% 
		if (error.getERWRNG().equals("Y")) { 
			error.setERWRNG(" ");
	%>
			<H4 style="text-align:center"><INPUT name="H01FLGWK2" type="checkbox" value="A" readonly="readonly">Aceptar con Advertencia</H4>
	<% 
		} 
	%>       
 
   <BR>	 
</FORM>
<%	if (!userPO.getHeader20().equals("")) {%>

<TABLE border="1">
		<TR>
			<TD>
			<%
			String s = userPO.getHeader18();
			for(i = 0; i < s.length(); i++)
			{
				if(s.charAt(i) == ':')	out.print("<BR>");
				else if (s.charAt(i) == '<') out.print("{");
					else if (s.charAt(i) == '>') out.print("}");
				else 					out.print(s.charAt(i));

			}
			%>
			</TD>
			<TD>
			<%
			s = userPO.getHeader20();
			for(i = 0; i < s.length(); i++)
			{
				if(s.charAt(i) == ':')	out.print("<BR>");
				else if (s.charAt(i) == '<') out.print("{");
					else if (s.charAt(i) == '>') out.print("}");
				else 					out.print(s.charAt(i));

			}
			%>
			</TD>
			<TD>
			<%
			s = userPO.getHeader19();
			for(i = 0; i < s.length(); i++)
			{
				if(s.charAt(i) == ':')	out.print("<BR>");
				else if (s.charAt(i) == '<') out.print("{");
					else if (s.charAt(i) == '>') out.print("}");
				else 					out.print(s.charAt(i));

			}
			%>
			</TD>
		</TR>
	</TBODY>
</TABLE>

<%}%>

</BODY>
</HTML>
