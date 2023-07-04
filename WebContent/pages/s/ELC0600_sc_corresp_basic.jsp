<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.0 Transitional//EN">
<HTML>
<HEAD>
<TITLE>Tabla de Gastos y Comisiones de Cartas de Crédito por Cliente</TITLE>
<META HTTP-EQUIV="Content-Type" CONTENT="text/html; charset=ISO-8859-1">
<META name="GENERATOR" content="IBM WebSphere Page Designer V3.5.2 for Windows">
<META http-equiv="Content-Style-Type" content="text/css">
<LINK Href="<%=request.getContextPath()%>/pages/style.css" rel="stylesheet">

<jsp:useBean id="msg01" class="datapro.eibs.beans.ELC060001Message"  scope="session" />
<jsp:useBean id= "error" class= "datapro.eibs.beans.ELEERRMessage"  scope="session" />
<jsp:useBean id= "userPO" class= "datapro.eibs.beans.UserPos"  scope="session" />

<SCRIPT language="Javascript1.1" src="<%=request.getContextPath()%>/pages/s/javascripts/eIBS.jsp"> </SCRIPT>
<SCRIPT language="Javascript1.1" src="<%=request.getContextPath()%>/pages/s/javascripts/optMenu.jsp"> </SCRIPT>

<SCRIPT Language="Javascript">

   	builtHPopUp();

  function showPopUp(opth,field,bank,ccy,field1,field2,opcod) {
   init(opth,field,bank,ccy,field1,field2,opcod);
   showPopupHelp();
   }

</SCRIPT>

</HEAD>

<BODY>
<%
 if ( !error.getERRNUM().equals("0")  ) {
 	 error.setERRNUM("0");
    out.println("<SCRIPT Language=\"Javascript\">");
     out.println("       showErrors()");
     out.println("</SCRIPT>");
 }
%>

<H3 align="center">Tabla de Gastos y Comisiones de Corresponsal<IMG
	src="<%=request.getContextPath()%>/images/e_ibs.gif" align="left"
	name="EIBS_GIF" ALT="ELC0600_sc_corresp_basic"></H3>
<HR size="4">
<FORM method="post" action="<%=request.getContextPath()%>/servlet/datapro.eibs.products.JSELC0600" >
  <INPUT TYPE=HIDDEN NAME="SCREEN" VALUE="305">
  <INPUT TYPE=HIDDEN NAME="E01RLCCUN" VALUE="<%=msg01.getE01RLCCUN()%>">
  <INPUT TYPE=HIDDEN NAME="OPT" VALUE="<%= userPO.getPurpose()%>">

<TABLE class="tableinfo" cellspacing="0" cellpadding="2" width="100%" border="0">
          <TR id="trdark">
            <TD width="25%" align="right" nowrap  ><B>Banco :</B></TD>
            <TD width="25%" nowrap  ><INPUT type="text" name="E01RLCBNK" size="3" maxlength="2" value="<%= msg01.getE01RLCBNK().trim()%>" readonly></TD>
            <TD width="25%" align="right" nowrap  ><B>N&uacute;mero de Tabla :</B></TD>
            <TD width="25%" nowrap  ><INPUT type="text" name="E01RLCTAR" size="3" maxlength="2" value="<%= msg01.getE01RLCTAR().trim()%>" readonly></TD>
    </TR>
          <TR id="trclear">
            <TD align="right" nowrap  ><B>Moneda de Tarifa :</B></TD>
            <TD nowrap  ><input type="text" name="E01RLCTCY" size="4" maxlength="3" value="<%= msg01.getE01RLCTCY().trim()%>" >
              <a href="javascript:GetCurrency('E01RLCTCY','')"><img src="<%=request.getContextPath()%>/images/1b.gif" alt="help" border="0" ></a> </TD>
            <TD align="right" nowrap  ><b>Moneda de Cuenta :</b></TD>
            <TD nowrap ><input type="text" name="E01RLCACY" size="4" maxlength="3" value="<%= msg01.getE01RLCACY().trim()%>" >
              <a href="javascript:GetCurrency('E01RLCACY','')"><img src="<%=request.getContextPath()%>/images/1b.gif" alt="help" border="0" ></a> </TD>
    </TR>
          <TR id="trdark">
            <TD align="right" nowrap  ><b>Número de Banco :</b></TD>
            <TD nowrap  ><input type=HIDDEN name="OPT" value="<%= userPO.getPurpose()%>">
            <input type="text" name="E01RLCCUN2" size="15" maxlength="10" value="<%= msg01.getE01RLCCUN().trim()%>" readonly ></TD>
            <TD align="right" nowrap  ><b>Nombre del Banco :</b></TD>
            <TD nowrap ><input type="text" name="E01CUSNME" size="30" maxlength="25" value="<%= msg01.getE01CUSNME().trim()%>" readonly></TD>
    </TR>
          <TR id="trclear">
            <TD align="right" nowrap  ><B>Tipo de Producto :</B></TD>
            <TD nowrap  ><INPUT type="text" name="E01RLCATY"  size=5 maxlength="8" value="<%= msg01.getE01RLCATY().trim()%>">
        <A href="javascript:GetProductRates('E01RLCATY','LC')">
        <IMG src="<%=request.getContextPath()%>/images/1b.gif" alt="help" align="middle" border="0" ></A> </TD>
            <TD align="right" nowrap  ><b>Descripción :</b></TD>
            <TD nowrap ><input type="text" name="E01RLCDSC" size="23" maxlength="21" value="<%= msg01.getE01RLCDSC().trim()%>" ></TD>
          </TR>
</TABLE>

<% if (userPO.getPurpose().equals("NEW")) {%>
<BR>
	<TABLE class="tableinfo" cellspacing="0" cellpadding="2" width="100%" border="0">
			<TR id="trdark">
				<TD nowrap>
				<DIV align="right"><B>Copiar Desde </B></DIV>
				</TD>
				<TD nowrap></TD>
				<TD nowrap>
				<DIV align="right"><B>Número de Cliente :</B></DIV>
				</TD>
				<TD nowrap width="143">
					<INPUT type="text" name="E01CPYCUN" size="12" maxlength="9" value="">
        			<A href="javascript:GetCorrespondentDescId('E01CPYCUN','','')">
        			<IMG src="<%=request.getContextPath()%>/images/1b.gif" alt="help" align="absbottom" border="0" ></A>
        		</TD>
				<TD nowrap width="142">
				<DIV align="right"><B>Número de Tabla :</B></DIV>
				</TD>
				<TD nowrap width="49"><INPUT type="text" name="E01CPYTAR" size="3"
					maxlength="2" value="<%= msg01.getE01CPYTAR().trim()%>"></TD>
			</TR>
    </TABLE>
<%}%>

<H4><B>Cargos</B></H4>

<TABLE class="tableinfo" cellspacing="0" cellpadding="2" width="100%"	border="0">
	<TR id="trdark">
		<TD nowrap align="center"><B>Concepto</B></TD>
		<TD nowrap align="center"><B>Monto</B></TD>
		<TD nowrap align="center"><B>Porcentaje</B></TD>
		<TD nowrap align="center"><B>Período</B></TD>
		<TD nowrap align="center"><B>Mínimo</B></TD>
		<TD nowrap align="center"><B>Máximo</B></TD>
	</TR>
				<TR id="trclear">
					<TD nowrap>
					<DIV align="right">Apertura 1 :</DIV>
					</TD>
					<TD nowrap></TD>
					<TD nowrap>
					<DIV align="center"><INPUT type="text" name="E01RLCIBR" size="12"
						maxlength="11" value="<%= msg01.getE01RLCIBR().trim()%>"
						onkeypress="return enterDecimalNum(event)"></DIV>
					</TD>
					<TD nowrap>
					<DIV align="center"><INPUT type="text" name="E01RLCIBP" size="5"
						maxlength="4" value="<%= msg01.getE01RLCIBP().trim()%>"
						onkeypress="enterInteger()"></DIV>
					</TD>
					<TD nowrap>

					<DIV align="center"><INPUT type="text" name="E01RLCIMF" size="12"
						maxlength="11" value="<%= msg01.getE01RLCIMF().trim()%>"
						onkeypress="enterDecimal()"></DIV>
					</TD>
					<TD nowrap>
					<DIV align="center"><INPUT type="text" name="E01RLCIMX" size="12"
						maxlength="11" value="<%= msg01.getE01RLCIMX().trim()%>"
						onkeypress="enterDecimal()"></DIV>
					</TD>
				</TR>
				<TR id="trdark">
					<TD nowrap>
					<DIV align="right">Apertura 2 :</DIV>
					</TD>
					<TD nowrap></TD>
					<TD nowrap>
					<DIV align="center"><INPUT type="text" name="E01RLCIR2" size="12"
						maxlength="11" value="<%= msg01.getE01RLCIR2().trim()%>"
						onkeypress="return enterDecimalNum(event)"></DIV>
					</TD>
					<TD nowrap>
					<DIV align="center"><INPUT type="text" name="E01RLCIP2" size="5"
						maxlength="4" value="<%= msg01.getE01RLCIP2().trim()%>"
						onkeypress="enterInteger()"></DIV>
					</TD>
					<TD nowrap>
					<DIV align="center"><INPUT type="text" name="E01RLCIM2" size="12"
						maxlength="11" value="<%= msg01.getE01RLCIM2().trim()%>"
						onkeypress="enterDecimal()"></DIV>
					</TD>
					<TD nowrap>
					<DIV align="center"><INPUT type="text" name="E01RLCIX2" size="12"
						maxlength="11" value="<%= msg01.getE01RLCIX2().trim()%>"
						onkeypress="enterDecimal()"></DIV>
					</TD>
				</TR>
				<TR id="trclear">
					<TD nowrap>
					<DIV align="right">Aviso :</DIV>
					</TD>
					<TD nowrap>
					<DIV align="center"><INPUT type="text" name="E01RLCADF" size="12"
						maxlength="11" value="<%= msg01.getE01RLCADF().trim()%>"
						onkeypress="enterDecimal()"></DIV>
					</TD>
					<TD nowrap></TD>
					<TD nowrap></TD>
					<TD nowrap></TD>
					<TD nowrap></TD>
				</TR>
				<TR id="trdark">
					<TD nowrap>
					<DIV align="right">Confirmación 1 :</DIV>
					</TD>
					<TD nowrap></TD>
					<TD nowrap>
					<DIV align="center"><INPUT type="text" name="E01RLCCBR" size="12"
						maxlength="11" value="<%= msg01.getE01RLCCBR().trim()%>"
						onkeypress="return enterDecimalNum(event)"></DIV>
					</TD>
					<TD nowrap>
					<DIV align="center"><INPUT type="text" name="E01RLCCBP" size="5"
						maxlength="4" value="<%= msg01.getE01RLCCBP().trim()%>"
						onkeypress="enterInteger()"></DIV>
					</TD>
					<TD nowrap>

					<DIV align="center"><INPUT type="text" name="E01RLCCMF" size="12"
						maxlength="11" value="<%= msg01.getE01RLCCMF().trim()%>"
						onkeypress="enterDecimal()"></DIV>
					</TD>
					<TD nowrap>
					<DIV align="center"><INPUT type="text" name="E01RLCCMX" size="12"
						maxlength="11" value="<%= msg01.getE01RLCCMX().trim()%>"
						onkeypress="enterDecimal()"></DIV>
					</TD>
				</TR>
				<TR id="trclear">
					<TD nowrap>
					<DIV align="right">Confirmación 2 :</DIV>
					</TD>
					<TD nowrap></TD>
					<TD nowrap>
					<DIV align="center"><INPUT type="text" name="E01RLCCR2" size="12"
						maxlength="11" value="<%= msg01.getE01RLCCR2().trim()%>"
						onkeypress="return enterDecimalNum(event)"></DIV>
					</TD>
					<TD nowrap>
					<DIV align="center"><INPUT type="text" name="E01RLCCP2" size="5"
						maxlength="4" value="<%= msg01.getE01RLCCP2().trim()%>"
						onkeypress="enterInteger()"></DIV>
					</TD>
					<TD nowrap>

					<DIV align="center"><INPUT type="text" name="E01RLCCM2" size="12"
						maxlength="11" value="<%= msg01.getE01RLCCM2().trim()%>"
						onkeypress="enterDecimal()"></DIV>
					</TD>
					<TD nowrap>
					<DIV align="center"><INPUT type="text" name="E01RLCCX2" size="12"
						maxlength="11" value="<%= msg01.getE01RLCCX2().trim()%>"
						onkeypress="enterDecimal()"></DIV>
					</TD>
				</TR>
				<TR id="trdark">
					<TD nowrap>
					<DIV align="right">Extensión de Validez:</DIV>
					</TD>
					<TD nowrap></TD>
					<TD nowrap>
					<DIV align="center"><INPUT type="text" name="E01RLCEBR" size="12"
						maxlength="11" value="<%= msg01.getE01RLCEBR().trim()%>"
						onkeypress="return enterDecimalNum(event)"></DIV>
					</TD>
					<TD nowrap>
					<DIV align="center"><INPUT type="text" name="E01RLCEBP" size="5"
						maxlength="4" value="<%= msg01.getE01RLCEBP().trim()%>"
						onkeypress="enterInteger()"></DIV>
					</TD>
					<TD nowrap>
					<DIV align="center"><INPUT type="text" name="E01RLCEMF" size="12"
						maxlength="11" value="<%= msg01.getE01RLCEMF().trim()%>"
						onkeypress="enterDecimal()"></DIV>
					</TD>
					<TD nowrap>
					<DIV align="center"><INPUT type="text" name="E01RLCEMX" size="12"
						maxlength="11" value="<%= msg01.getE01RLCEMX().trim()%>"
						onkeypress="enterDecimal()"></DIV>
					</TD>
				</TR>
				<TR id="trclear">
					<TD nowrap>
					<DIV align="right">Enmienda :</DIV>
					</TD>
					<TD nowrap>
					<DIV align="center"><INPUT type="text" name="E01RLCAMF" size="12"
						maxlength="11" value="<%= msg01.getE01RLCAMF().trim()%>"
						onkeypress="enterDecimal()"></DIV>
					</TD>
					<TD nowrap></TD>
					<TD nowrap></TD>
					<TD nowrap></TD>
					<TD nowrap></TD>
				</TR>
				<TR id="trdark">
					<TD nowrap>
					<DIV align="right">Aviso de Enmienda :</DIV>
					</TD>
					<TD nowrap>
					<DIV align="center"><INPUT type="text" name="E01RLCAAF" size="12"
						maxlength="11" value="<%= msg01.getE01RLCAAF().trim()%>"
						onkeypress="enterDecimal()"></DIV>
					</TD>
					<TD nowrap></TD>
					<TD nowrap></TD>
					<TD nowrap></TD>
					<TD nowrap></TD>
				</TR>
				<TR id="trclear">
					<TD nowrap>
					<DIV align="right">Discrepancia :</DIV>
					</TD>
					<TD nowrap>
					<DIV align="center"><INPUT type="text" name="E01RLCDIF" size="12"
						maxlength="11" value="<%= msg01.getE01RLCDIF().trim()%>"
						onkeypress="enterDecimal()"></DIV>
					</TD>
					<TD nowrap></TD>
					<TD nowrap></TD>
					<TD nowrap></TD>
					<TD nowrap></TD>
				</TR>
				<TR id="trdark">
					<TD nowrap>
					<DIV align="right">Pago a la Vista :</DIV>
					</TD>
					<TD nowrap></TD>
					<TD nowrap>
					<DIV align="center"><INPUT type="text" name="E01RLCSBR" size="12"
						maxlength="11" value="<%= msg01.getE01RLCSBR().trim()%>"
						onkeypress="return enterDecimalNum(event)"></DIV>
					</TD>
					<TD nowrap></TD>
					<TD nowrap>
					<DIV align="center"><INPUT type="text" name="E01RLCSPM" size="12"
						maxlength="11" value="<%= msg01.getE01RLCSPM().trim()%>"
						onkeypress="enterDecimal()"></DIV>
					</TD>
					<TD nowrap></TD>
				</TR>
				<TR id="trclear">
					<TD nowrap>
					<DIV align="right">Aceptación :</DIV>
					</TD>
					<TD nowrap></TD>
					<TD nowrap>
					<DIV align="center"><INPUT type="text" name="E01RLCACB" size="12"
						maxlength="11" value="<%= msg01.getE01RLCACB().trim()%>"
						onkeypress="return enterDecimalNum(event)"></DIV>
					</TD>
					<TD nowrap>
					<DIV align="center"><INPUT type="text" name="E01RLCABP" size="5"
						maxlength="4" value="<%= msg01.getE01RLCABP().trim()%>"
						onkeypress="enterInteger()"></DIV>
					</TD>
					<TD nowrap>
					<DIV align="center"><INPUT type="text" name="E01RLCACM" size="12"
						maxlength="11" value="<%= msg01.getE01RLCACM().trim()%>"
						onkeypress="enterDecimal()"></DIV>
					</TD>
					<TD nowrap>
					<DIV align="center"><INPUT type="text" name="E01RLCAMX" size="12"
						maxlength="11" value="<%= msg01.getE01RLCAMX().trim()%>"
						onkeypress="enterDecimal()"></DIV>
					</TD>
				</TR>
				<TR id="trdark">
					<TD nowrap>
					<DIV align="right">Pagos Diferido :</DIV>
					</TD>
					<TD nowrap></TD>
					<TD nowrap>
					<DIV align="center"><INPUT type="text" name="E01RLCDPB" size="12"
						maxlength="11" value="<%= msg01.getE01RLCDPB().trim()%>"
						onkeypress="return enterDecimalNum(event)"></DIV>
					</TD>
					<TD nowrap>
					<DIV align="center"><INPUT type="text" name="E01RLCDBP" size="5"
						maxlength="4" value="<%= msg01.getE01RLCDBP().trim()%>"
						onkeypress="enterInteger()"></DIV>
					</TD>
					<TD nowrap>
					<DIV align="center"><INPUT type="text" name="E01RLCDPM" size="12"
						maxlength="11" value="<%= msg01.getE01RLCDPM().trim()%>"
						onkeypress="enterDecimal()"></DIV>
					</TD>
					<TD nowrap>
					<DIV align="center"><INPUT type="text" name="E01RLCDMX" size="12"
						maxlength="11" value="<%= msg01.getE01RLCDMX().trim()%>"
						onkeypress="enterDecimal()"></DIV>
					</TD>
				</TR>
				<TR id="trclear">
					<TD nowrap>
					<DIV align="right">Negociación :</DIV>
					</TD>
					<TD nowrap></TD>
					<TD nowrap>
					<DIV align="center"><INPUT type="text" name="E01RLCNBR" size="12"
						maxlength="11" value="<%= msg01.getE01RLCNBR().trim()%>"
						onkeypress="return enterDecimalNum(event)"></DIV>
					</TD>
					<TD nowrap></TD>
					<TD nowrap>
					<DIV align="center"><INPUT type="text" name="E01RLCNMF" size="12"
						maxlength="11" value="<%= msg01.getE01RLCNMF().trim()%>"
						onkeypress="enterDecimal()"></DIV>
					</TD>
					<TD nowrap>
					<DIV align="center"><INPUT type="text" name="E01RLCNMX" size="12"
						maxlength="11" value="<%= msg01.getE01RLCNMX().trim()%>"
						onkeypress="enterDecimal()"></DIV>
					</TD>
				</TR>
				<TR id="trdark">
					<TD nowrap>
					<DIV align="right">Asignación de Fondos :</DIV>
					</TD>
					<TD nowrap></TD>
					<TD nowrap>
					<DIV align="center"><INPUT type="text" name="E01RLCAPB" size="12"
						maxlength="11" value="<%= msg01.getE01RLCAPB().trim()%>"
						onkeypress="return enterDecimalNum(event)"></DIV>
					</TD>
					<TD nowrap></TD>
					<TD nowrap>
					<DIV align="center"><INPUT type="text" name="E01RLCAPM" size="12"
						maxlength="11" value="<%= msg01.getE01RLCAPM().trim()%>"
						onkeypress="enterDecimal()"></DIV>
					</TD>
					<TD nowrap>
					<DIV align="center"></DIV>
					</TD>
				</TR>
				<TR id="trclear">
					<TD nowrap>
					<DIV align="right">Transferencia :</DIV>
					</TD>
					<TD nowrap></TD>
					<TD nowrap>
					<DIV align="center"><INPUT type="text" name="E01RLCTBR" size="12"
						maxlength="11" value="<%= msg01.getE01RLCTBR().trim()%>"
						onkeypress="return enterDecimalNum(event)"></DIV>
					</TD>
					<TD nowrap></TD>
					<TD nowrap>
					<DIV align="center"><INPUT type="text" name="E01RLCTMF" size="12"
						maxlength="11" value="<%= msg01.getE01RLCTMF().trim()%>"
						onkeypress="enterDecimal()"></DIV>
					</TD>
					<TD nowrap>
					<DIV align="center"><INPUT type="text" name="E01RLCTMX" size="12"
						maxlength="11" value="<%= msg01.getE01RLCTMX().trim()%>"
						onkeypress="enterDecimal()"></DIV>
					</TD>
				</TR>
				<TR id="trdark">
					<TD nowrap>
					<DIV align="right">Portes :</DIV>
					</TD>
					<TD nowrap>
					<DIV align="center"><INPUT type="text" name="E01RLCPST" size="12"
						maxlength="11" value="<%= msg01.getE01RLCPST().trim()%>"
						onkeypress="enterDecimal()"></DIV>
					</TD>
					<TD nowrap></TD>
					<TD nowrap></TD>
					<TD nowrap></TD>
					<TD nowrap></TD>
				</TR>
				<TR id="trclear">
					<TD nowrap>
					<DIV align="right">Courier :</DIV>
					</TD>
					<TD nowrap>
					<DIV align="center"><INPUT type="text" name="E01RLCCOU" size="12"
						maxlength="11" value="<%= msg01.getE01RLCCOU().trim()%>"
						onkeypress="enterDecimal()"></DIV>
					</TD>
					<TD nowrap></TD>
					<TD nowrap></TD>
					<TD nowrap></TD>
					<TD nowrap></TD>
				</TR>
				<TR id="trdark">
					<TD nowrap>
					<DIV align="right">Cancelación :</DIV>
					</TD>
					<TD nowrap>
					<DIV align="center"><INPUT type="text" name="E01RLCCAN" size="12"
						maxlength="11" value="<%= msg01.getE01RLCCAN().trim()%>"
						onkeypress="enterDecimal()"></DIV>
					</TD>
					<TD nowrap></TD>
					<TD nowrap></TD>
					<TD nowrap></TD>
					<TD nowrap></TD>
				</TR>
				<TR id="trclear">
					<TD nowrap>
					<DIV align="right">Miscelaneos :</DIV>
					</TD>
					<TD nowrap>
					<DIV align="center"><INPUT type="text" name="E01RLCNOF" size="12"
						maxlength="11" value="<%= msg01.getE01RLCNOF().trim()%>"
						onkeypress="enterDecimal()"></DIV>
					</TD>
					<TD nowrap></TD>
					<TD nowrap></TD>
					<TD nowrap></TD>
					<TD nowrap></TD>
				</TR>
				<TR id="trdark">
					<TD nowrap>
					<DIV align="right">Transferencia de Fondos :</DIV>
					</TD>
					<TD nowrap>
					<DIV align="center"><INPUT type="text" name="E01RLCWTF" size="12"
						maxlength="11" value="<%= msg01.getE01RLCWTF().trim()%>"
						onkeypress="enterDecimal()"></DIV>
					</TD>
					<TD nowrap></TD>
					<TD nowrap></TD>
					<TD nowrap></TD>
					<TD nowrap></TD>
				</TR>
				<TR id="trclear">
					<TD nowrap>
					<DIV align="right">Swift de Apertura :</DIV>
					</TD>
					<TD nowrap>
					<DIV align="center"><INPUT type="text" name="E01RLCCTF" size="12"
						maxlength="11" value="<%= msg01.getE01RLCCTF().trim()%>"
						onkeypress="enterDecimal()"></DIV>
					</TD>
					<TD nowrap></TD>
					<TD nowrap></TD>
					<TD nowrap></TD>
					<TD nowrap></TD>
				</TR>
				<TR id="trdark">
					<TD nowrap>
					<DIV align="right">Swift Adicional :</DIV>
					</TD>
					<TD nowrap>
					<DIV align="center"><INPUT type="text" name="E01RLCCTM" size="12"
						maxlength="11" value="<%= msg01.getE01RLCCTM().trim()%>"
						onkeypress="enterDecimal()"></DIV>
					</TD>
					<TD nowrap></TD>
					<TD nowrap></TD>
					<TD nowrap></TD>
					<TD nowrap></TD>
				</TR>
				<TR id="trclear">
					<TD nowrap>
					<DIV align="right">Comisión por Plazo :</DIV>
					</TD>
					<TD nowrap></TD>
					<TD nowrap>
					<DIV align="center"><INPUT type="text" name="E01RLCCA1" size="12"
						maxlength="11" value="<%= msg01.getE01RLCCA1().trim()%>"
						onkeypress="return enterDecimalNum(event)"></DIV>
					</TD>
					<TD nowrap>
					<DIV align="center"><INPUT type="text" name="E01RLCCO1" size="5"
						maxlength="4" value="<%= msg01.getE01RLCCO1().trim()%>"
						onkeypress="enterInteger()"></DIV>
					</TD>
					<TD nowrap>

					<DIV align="center"><INPUT type="text" name="E01RLCCN1" size="12"
						maxlength="11" value="<%= msg01.getE01RLCCN1().trim()%>"
						onkeypress="enterDecimal()"></DIV>
					</TD>
					<TD nowrap>
					<DIV align="center"><INPUT type="text" name="E01RLCCV1" size="12"
						maxlength="11" value="<%= msg01.getE01RLCCV1().trim()%>"
						onkeypress="enterDecimal()"></DIV>
					</TD>
				</TR>
				<TR id="trdark">
					<TD nowrap>
					<DIV align="right">Comisión de Reembolso :</DIV>
					</TD>
					<TD nowrap>
					<DIV align="center"><INPUT type="text" name="E01RLCCA2" size="12"
						maxlength="11" value="<%= msg01.getE01RLCCA2().trim()%>"
						onkeypress="enterDecimal()"></DIV>
					</TD>
					<TD nowrap></TD>
					<TD nowrap></TD>
					<TD nowrap></TD>
					<TD nowrap></TD>
				</TR>
</TABLE>

<BR>
<H4> Cuentas Contables</H4>
<TABLE class="tableinfo" cellspacing="0" cellpadding="2" width="100%" border="0">
		<TR id="trdark">
			<TD nowrap align="right" width="178"><B>Concepto</B></TD>
			<TD nowrap align="center" width="199"><B>Cuenta</B></TD>
			<TD nowrap align="center" width="260"><B>Descripción</B></TD>
		</TR>
			<TR id="trclear">
				<TD nowrap width="178">
				<DIV align="right">Apertura:</DIV>
				</TD>
				<TD nowrap width="212">
				<DIV align="center"><INPUT type="text" name="E01RLCGIS" size="19"
							maxlength="16" value="<%= msg01.getE01RLCGIS().trim()%>"
							onkeypress="enterInteger()">
							<A href="javascript:GetLedger('E01RLCGIS',document.forms[0].E01RLCBNK.value,document.forms[0].E01RLCACY.value,'')">
							<IMG src="<%=request.getContextPath()%>/images/1b.gif" alt="help"  border="0"></A>
				</DIV>
				</TD>
				<TD nowrap width="234">
				<DIV align="center"><INPUT type="text" name="E01DSCGIS" size="40" maxlength="35" value="<%= msg01.getE01DSCGIS().trim()%>" readonly></DIV>
				</TD>
			</TR>
			<TR id="trdark">
				<TD nowrap width="178">
				<DIV align="right">Aviso :</DIV>
				</TD>
				<TD nowrap width="212">
				<DIV align="center"><INPUT type="text" name="E01RLCGAD" size="19"
							maxlength="16" value="<%= msg01.getE01RLCGAD().trim()%>"
							onkeypress="enterInteger()">
							<A href="javascript:GetLedger('E01RLCGAD',document.forms[0].E01RLCBNK.value,document.forms[0].E01RLCACY.value,'')">
							<IMG src="<%=request.getContextPath()%>/images/1b.gif" alt="help"  border="0"></A>
				</DIV>
				</TD>
				<TD nowrap width="234">
				<DIV align="center"><INPUT type="text" name="E01DSCGAD" size="40" maxlength="35" value="<%= msg01.getE01DSCGAD().trim()%>" readonly></DIV>
				</TD>
			</TR>
			<TR id="trclear">
				<TD nowrap width="178">
				<DIV align="right">Confirmación :</DIV>
				</TD>
				<TD nowrap width="212">
				<DIV align="center"><INPUT type="text" name="E01RLCGCO" size="19"
							maxlength="16" value="<%= msg01.getE01RLCGCO().trim()%>"
							onkeypress="enterInteger()">
							<A href="javascript:GetLedger('E01RLCGCO',document.forms[0].E01RLCBNK.value,document.forms[0].E01RLCACY.value,'')">
							<IMG src="<%=request.getContextPath()%>/images/1b.gif" alt="help"  border="0"></A>
				</DIV>
				</TD>
				<TD nowrap width="234">
				<DIV align="center"><INPUT type="text" name="E01DSCGCO" size="40" maxlength="35" value="<%= msg01.getE01DSCGCO().trim()%>" readonly></DIV>
				</TD>
			</TR>
			<TR id="trdark">
				<TD nowrap width="178">
				<DIV align="right">Extensión de Validez:</DIV>
				</TD>
				<TD nowrap width="212">
				<DIV align="center"><INPUT type="text" name="E01RLCGEV" size="19"
							maxlength="16" value="<%= msg01.getE01RLCGEV().trim()%>"
							onkeypress="enterInteger()">
							<A href="javascript:GetLedger('E01RLCGEV',document.forms[0].E01RLCBNK.value,document.forms[0].E01RLCACY.value,'')">
							<IMG src="<%=request.getContextPath()%>/images/1b.gif" alt="help"  border="0"></A>
				</DIV>
				</TD>
				<TD nowrap width="234">
				<DIV align="center"><INPUT type="text" name="E01DSCGEV" size="40" maxlength="35" value="<%= msg01.getE01DSCGEV().trim()%>" readonly></DIV>
				</TD>
			</TR>
			<TR id="trclear">
				<TD nowrap width="178">
				<DIV align="right">Enmienda :</DIV>
				</TD>
				<TD nowrap width="212">
				<DIV align="center"><INPUT type="text" name="E01RLCGAM" size="19"
							maxlength="16" value="<%= msg01.getE01RLCGAM().trim()%>"
							onkeypress="enterInteger()">
							<A href="javascript:GetLedger('E01RLCGAM',document.forms[0].E01RLCBNK.value,document.forms[0].E01RLCACY.value,'')">
							<IMG src="<%=request.getContextPath()%>/images/1b.gif" alt="help"  border="0"></A>
				</DIV>
				</TD>
				<TD nowrap width="234">
				<DIV align="center"><INPUT type="text" name="E01DSCGAM" size="40" maxlength="35" value="<%= msg01.getE01DSCGAM().trim()%>" readonly></DIV>
				</TD>
			</TR>
			<TR id="trdark">
				<TD nowrap width="178">
				<DIV align="right">Aviso de Enmienda :</DIV>
				</TD>
				<TD nowrap width="212">
				<DIV align="center"><INPUT type="text" name="E01RLCGAA" size="19"
							maxlength="16" value="<%= msg01.getE01RLCGAA().trim()%>"
							onkeypress="enterInteger()">
							<A href="javascript:GetLedger('E01RLCGAA',document.forms[0].E01RLCBNK.value,document.forms[0].E01RLCACY.value,'')">
							<IMG src="<%=request.getContextPath()%>/images/1b.gif" alt="help"  border="0"></A>
				</DIV>
				</TD>
				<TD nowrap width="234">
				<DIV align="center"><INPUT type="text" name="E01DSCGAA" size="40" maxlength="35" value="<%= msg01.getE01DSCGAA().trim()%>" readonly></DIV>
				</TD>
			</TR>
			<TR id="trclear">
				<TD nowrap width="178">
				<DIV align="right">Discrepancia :</DIV>
				</TD>
				<TD nowrap width="212">
				<DIV align="center"><INPUT type="text" name="E01RLCGDI" size="19"
							maxlength="16" value="<%= msg01.getE01RLCGDI().trim()%>"
							onkeypress="enterInteger()">
							<A href="javascript:GetLedger('E01RLCGDI',document.forms[0].E01RLCBNK.value,document.forms[0].E01RLCACY.value,'')">
							<IMG src="<%=request.getContextPath()%>/images/1b.gif" alt="help"  border="0"></A>
				</DIV>
				</TD>
				<TD nowrap width="234">
				<DIV align="center"><INPUT type="text" name="E01DSCGDI" size="40" maxlength="35" value="<%= msg01.getE01DSCGDI().trim()%>" readonly></DIV>
				</TD>
			</TR>
			<TR id="trdark">
				<TD nowrap width="178">
				<DIV align="right">Pago a la Vista :</DIV>
				</TD>
				<TD nowrap width="212">
				<DIV align="center"><INPUT type="text" name="E01RLCGPM" size="19"
							maxlength="16" value="<%= msg01.getE01RLCGPM().trim()%>"
							onkeypress="enterInteger()">
							<A href="javascript:GetLedger('E01RLCGPM',document.forms[0].E01RLCBNK.value,document.forms[0].E01RLCACY.value,'')">
							<IMG src="<%=request.getContextPath()%>/images/1b.gif" alt="help"  border="0"></A>
				</DIV>
				</TD>
				<TD nowrap width="234">
				<DIV align="center"><INPUT type="text" name="E01DSCGPM" size="40" maxlength="35" value="<%= msg01.getE01DSCGPM().trim()%>" readonly></DIV>
				</TD>
			</TR>
			<TR id="trclear">
				<TD nowrap width="178">
				<DIV align="right">Aceptación:</DIV>
				</TD>
				<TD nowrap width="212">
				<DIV align="center"><INPUT type="text" name="E01RLCGAC" size="19"
							maxlength="16" value="<%= msg01.getE01RLCGAC().trim()%>"
							onkeypress="enterInteger()">
							<A href="javascript:GetLedger('E01RLCGAC',document.forms[0].E01RLCBNK.value,document.forms[0].E01RLCACY.value,'')">
							<IMG src="<%=request.getContextPath()%>/images/1b.gif" alt="help"  border="0"></A>
				</DIV>
				</TD>
				<TD nowrap width="234">
				<DIV align="center"><INPUT type="text" name="E01DSCGAC" size="40" maxlength="35" value="<%= msg01.getE01DSCGAC().trim()%>" readonly></DIV>
				</TD>
			</TR>
			<TR id="trdark">
				<TD nowrap width="178">
				<DIV align="right">Pagos Diferido :</DIV>
				</TD>
				<TD nowrap width="212">
				<DIV align="center"><INPUT type="text" name="E01RLCGDP" size="19"
							maxlength="16" value="<%= msg01.getE01RLCGDP().trim()%>"
							onkeypress="enterInteger()">
							<A href="javascript:GetLedger('E01RLCGDP',document.forms[0].E01RLCBNK.value,document.forms[0].E01RLCACY.value,'')">
							<IMG src="<%=request.getContextPath()%>/images/1b.gif" alt="help"  border="0"></A>
				</DIV>
				</TD>
				<TD nowrap width="234">
				<DIV align="center"><INPUT type="text" name="E01DSCGDP" size="40" maxlength="35" value="<%= msg01.getE01DSCGDP().trim()%>" readonly></DIV>
				</TD>
			</TR>
			<TR id="trclear">
				<TD nowrap width="178">
				<DIV align="right">Negociación :</DIV>
				</TD>
				<TD nowrap width="212">
				<DIV align="center"><INPUT type="text" name="E01RLCGNE" size="19"
							maxlength="16" value="<%= msg01.getE01RLCGNE().trim()%>"
							onkeypress="enterInteger()">
							<A href="javascript:GetLedger('E01RLCGNE',document.forms[0].E01RLCBNK.value,document.forms[0].E01RLCACY.value,'')">
							<IMG src="<%=request.getContextPath()%>/images/1b.gif" alt="help"  border="0"></A>
				</DIV>
				</TD>
				<TD nowrap width="234">
				<DIV align="center"><INPUT type="text" name="E01DSCGNE" size="40" maxlength="35" value="<%= msg01.getE01DSCGNE().trim()%>" readonly></DIV>
				</TD>
			</TR>
			<TR id="trdark">
				<TD nowrap width="178">
				<DIV align="right">Asignación de Fondos:</DIV>
				</TD>
				<TD nowrap width="212">
				<DIV align="center"><INPUT type="text" name="E01RLCGAS" size="19"
							maxlength="16" value="<%= msg01.getE01RLCGAS().trim()%>"
							onkeypress="enterInteger()">
							<A href="javascript:GetLedger('E01RLCGAS',document.forms[0].E01RLCBNK.value,document.forms[0].E01RLCACY.value,'')">
							<IMG src="<%=request.getContextPath()%>/images/1b.gif" alt="help"  border="0"></A>
				</DIV>
				</TD>
				<TD nowrap width="234">
				<DIV align="center"><INPUT type="text" name="E01DSCGAS" size="40" maxlength="35" value="<%= msg01.getE01DSCGAS().trim()%>" readonly></DIV>
				</TD>
			</TR>
			<TR id="trclear">
				<TD nowrap width="178">
				<DIV align="right">Transferencia :</DIV>
				</TD>
				<TD nowrap width="212">
				<DIV align="center"><INPUT type="text" name="E01RLCGTR" size="19"
							maxlength="16" value="<%= msg01.getE01RLCGTR().trim()%>"
							onkeypress="enterInteger()">
							<A href="javascript:GetLedger('E01RLCGTR',document.forms[0].E01RLCBNK.value,document.forms[0].E01RLCACY.value,'')">
							<IMG src="<%=request.getContextPath()%>/images/1b.gif" alt="help"  border="0"></A>
				</DIV>
				</TD>
				<TD nowrap width="234">
				<DIV align="center"><INPUT type="text" name="E01DSCGTR" size="40" maxlength="35" value="<%= msg01.getE01DSCGTR().trim()%>" readonly></DIV>
				</TD>
			</TR>
			<TR id="trdark">
				<TD nowrap width="178">
				<DIV align="right">Portes :</DIV>
				</TD>
				<TD nowrap width="212">
				<DIV align="center"><INPUT type="text" name="E01RLCGPO" size="19"
							maxlength="16" value="<%= msg01.getE01RLCGPO().trim()%>"
							onkeypress="enterInteger()">
							<A href="javascript:GetLedger('E01RLCGPO',document.forms[0].E01RLCBNK.value,document.forms[0].E01RLCACY.value,'')">
							<IMG src="<%=request.getContextPath()%>/images/1b.gif" alt="help"  border="0"></A>
				</DIV>
				</TD>
				<TD nowrap width="234">
				<DIV align="center"><INPUT type="text" name="E01DSCGPO" size="40" maxlength="35" value="<%= msg01.getE01DSCGPO().trim()%>" readonly></DIV>
				</TD>
			</TR>
			<TR id="trclear">
				<TD nowrap width="178">
				<DIV align="right">Courier :</DIV>
				</TD>
				<TD nowrap width="212">
				<DIV align="center"><INPUT type="text" name="E01RLCGCR" size="19"
							maxlength="16" value="<%= msg01.getE01RLCGCR().trim()%>"
							onkeypress="enterInteger()">
							<A href="javascript:GetLedger('E01RLCGCR',document.forms[0].E01RLCBNK.value,document.forms[0].E01RLCACY.value,'')">
							<IMG src="<%=request.getContextPath()%>/images/1b.gif" alt="help"  border="0"></A>
				</DIV>
				</TD>
				<TD nowrap width="234">
				<DIV align="center"><INPUT type="text" name="E01DSCGCR" size="40" maxlength="35" value="<%= msg01.getE01DSCGCR().trim()%>" readonly></DIV>
				</TD>
			</TR>
			<TR id="trdark">
				<TD nowrap width="178">
				<DIV align="right">Cancelación :</DIV>
				</TD>
				<TD nowrap width="212">
				<DIV align="center"><INPUT type="text" name="E01RLCGCA" size="19"
							maxlength="16" value="<%= msg01.getE01RLCGCA().trim()%>"
							onkeypress="enterInteger()">
							<A href="javascript:GetLedger('E01RLCGCA',document.forms[0].E01RLCBNK.value,document.forms[0].E01RLCACY.value,'')">
							<IMG src="<%=request.getContextPath()%>/images/1b.gif" alt="help"  border="0"></A>
				</DIV>
				</TD>
				<TD nowrap width="234">
				<DIV align="center"><INPUT type="text" name="E01DSCGCA" size="40" maxlength="35" value="<%= msg01.getE01DSCGCA().trim()%>" readonly></DIV>
				</TD>
			</TR>
			<TR id="trclear">
				<TD nowrap width="178">
				<DIV align="right">Miscelaneos :</DIV>
				</TD>
				<TD nowrap width="212">
				<DIV align="center"><INPUT type="text" name="E01RLCGNF" size="19"
							maxlength="16" value="<%= msg01.getE01RLCGNF().trim()%>"
							onkeypress="enterInteger()">
							<A href="javascript:GetLedger('E01RLCGNF',document.forms[0].E01RLCBNK.value,document.forms[0].E01RLCACY.value,'')">
							<IMG src="<%=request.getContextPath()%>/images/1b.gif" alt="help"  border="0"></A>
				</DIV>
				</TD>
				<TD nowrap width="234">
				<DIV align="center"><INPUT type="text" name="E01DSCGNF" size="40" maxlength="35" value="<%= msg01.getE01DSCGNF().trim()%>" readonly></DIV>
				</TD>
			</TR>
			<TR id="trdark">
				<TD nowrap width="178">
				<DIV align="right">Transferencia de Fondos:</DIV>
				</TD>
				<TD nowrap width="212">
				<DIV align="center"><INPUT type="text" name="E01RLCGWT" size="19"
							maxlength="16" value="<%= msg01.getE01RLCGWT().trim()%>"
							onkeypress="enterInteger()">
							<A href="javascript:GetLedger('E01RLCGWT',document.forms[0].E01RLCBNK.value,document.forms[0].E01RLCACY.value,'')">
							<IMG src="<%=request.getContextPath()%>/images/1b.gif" alt="help"  border="0"></A>
				</DIV>
				</TD>
				<TD nowrap width="234">
				<DIV align="center"><INPUT type="text" name="E01DSCGWT" size="40" maxlength="35" value="<%= msg01.getE01DSCGWT().trim()%>" readonly></DIV>
				</TD>
			</TR>
			<TR id="trclear">
				<TD nowrap width="178">
				<DIV align="right">Swift de Apertura :</DIV>
				</TD>
				<TD nowrap width="212">
				<DIV align="center"><INPUT type="text" name="E01RLCGTF" size="19"
							maxlength="16" value="<%= msg01.getE01RLCGTF().trim()%>"
							onkeypress="enterInteger()">
							<A href="javascript:GetLedger('E01RLCGTF',document.forms[0].E01RLCBNK.value,document.forms[0].E01RLCACY.value,'')">
							<IMG src="<%=request.getContextPath()%>/images/1b.gif" alt="help"  border="0"></A>
				</DIV>
				</TD>
				<TD nowrap width="234">
				<DIV align="center"><INPUT type="text" name="E01DSCGTF" size="40" maxlength="35" value="<%= msg01.getE01DSCGTF().trim()%>" readonly></DIV>
				</TD>
			</TR>
			<TR id="trdark">
				<TD nowrap width="178">
				<DIV align="right">Swift Adicional :</DIV>
				</TD>
				<TD nowrap width="212">
				<DIV align="center"><INPUT type="text" name="E01RLCGTM" size="19"
							maxlength="16" value="<%= msg01.getE01RLCGTM().trim()%>"
							onkeypress="enterInteger()">
							<A href="javascript:GetLedger('E01RLCGTM',document.forms[0].E01RLCBNK.value,document.forms[0].E01RLCACY.value,'')">
							<IMG src="<%=request.getContextPath()%>/images/1b.gif" alt="help"  border="0"></A>
				</DIV>
				</TD>

				<TD nowrap width="234">
				<DIV align="center"><INPUT type="text" name="E01DSCGTM" size="40" maxlength="35" value="<%= msg01.getE01DSCGTM().trim()%>" readonly></DIV>
				</TD>
			</TR>
			<TR id="trclear">
				<TD nowrap width="178">
				<DIV align="right">Comisión por Plazo :</DIV>
				</TD>
				<TD nowrap width="212">
				<DIV align="center"><INPUT type="text" name="E01RLCCG1" size="19"
							maxlength="16" value="<%= msg01.getE01RLCCG1().trim()%>"
							onkeypress="enterInteger()">
							<A href="javascript:GetLedger('E01RLCCG1',document.forms[0].E01RLCBNK.value,document.forms[0].E01RLCACY.value,'')">
							<IMG src="<%=request.getContextPath()%>/images/1b.gif" alt="help"  border="0"></A>
				</DIV>
				</TD>
				<TD nowrap width="234">
				<DIV align="center"><INPUT type="text" name="E01RLCCD1" size="40" maxlength="35" value="<%= msg01.getE01RLCCD1().trim()%>" readonly></DIV>
				</TD>
			</TR>
			<TR id="trdark">
				<TD nowrap width="178">
				<DIV align="right">Comisión de Reembolso :</DIV>
				</TD>
				<TD nowrap width="212">
				<DIV align="center"><INPUT type="text" name="E01RLCCG2" size="19"
							maxlength="16" value="<%= msg01.getE01RLCCG2().trim()%>"
							onkeypress="enterInteger()">
							<A href="javascript:GetLedger('E01RLCCG2',document.forms[0].E01RLCBNK.value,document.forms[0].E01RLCACY.value,'')">
							<IMG src="<%=request.getContextPath()%>/images/1b.gif" alt="help"  border="0"></A>
				</DIV>
				</TD>
				<TD nowrap width="234">
				<DIV align="center"><INPUT type="text" name="E01RLCCD2" size="40" maxlength="35" value="<%= msg01.getE01RLCCD2().trim()%>" readonly></DIV>
				</TD>
			</TR>
</TABLE>

	<%
		if (error.getERWRNG().equals("Y")) {
			error.setERWRNG(" ");
	%>
			<H4 style="text-align:center"><INPUT type="checkbox" name="H01FLGWK2" value="A">Aceptar con Advertencia</H4>
	<%
		}
	%>

   <BR>
  <DIV align="center">
	   <INPUT id="EIBSBTN" type=submit name="Submit" value="Enviar" >
  </DIV>
</FORM>
<!--
<TABLE border="1">
<TR><TD>
<H5 align="left">
  <%
String s = userPO.getHeader18();
for(int i = 0; i < s.length(); i++)
{
	if(s.charAt(i) == ':')	out.print("<BR>");
	else if (s.charAt(i) == '<') out.print("{");
		else if (s.charAt(i) == '>') out.print("}");
	else 					out.print(s.charAt(i));

}%>
</H5>
</TD>
<TD>
<H5 align="left">
  <%
 s = userPO.getHeader20();
for(int i = 0; i < s.length(); i++)
{
	if(s.charAt(i) == ':')	out.print("<BR>");
	else if (s.charAt(i) == '<') out.print("{");
		else if (s.charAt(i) == '>') out.print("}");
	else 					out.print(s.charAt(i));

}%>
</H5>
</TD>
<TD>
<H5 align="left">
  <%
 s = userPO.getHeader19();
for(int i = 0; i < s.length(); i++)
{
	if(s.charAt(i) == ':')	out.print("<BR>");
	else if (s.charAt(i) == '<') out.print("{");
		else if (s.charAt(i) == '>') out.print("}");
	else 					out.print(s.charAt(i));

}%>
</H5>
</TD>
</TR></TABLE>-->
</BODY>
</HTML>
