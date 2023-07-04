<!--	'S' = Emitida  (outgoing)  <->  'O' = Import
		'R' = Recibida (incoming)  <->  'I' = Export
-->

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.0 Transitional//EN">
<HTML>
<HEAD>
<TITLE>Letters of Credit Opening/Maintenance</TITLE>
<META HTTP-EQUIV="Content-Type" CONTENT="text/html; charset=ISO-8859-1">
<META name="GENERATOR" content="IBM WebSphere Page Designer V3.5.2 for Windows">
<META http-equiv="Content-Style-Type" content="text/css">
<LINK Href="<%=request.getContextPath()%>/pages/style.css" rel="stylesheet">


<SCRIPT language="Javascript1.1" src="<%=request.getContextPath()%>/pages/s/javascripts/eIBS.jsp"> </SCRIPT>
<SCRIPT language="Javascript1.1" src="<%=request.getContextPath()%>/pages/s/javascripts/optMenu.jsp"> </SCRIPT>

<jsp:useBean id="msg040001"  class="datapro.eibs.beans.ELC040001Message" scope="session" />
<jsp:useBean id="error"  class="datapro.eibs.beans.ELEERRMessage" scope="session" />
<jsp:useBean id="userPO" class="datapro.eibs.beans.UserPos" scope="session" />

<SCRIPT LANGUAGE="javascript">

	builtNewMenu(lc_apr_standby);
	initMenu();
   
</SCRIPT>

<%if (!error.getERRNUM().equals("0")) {
	error.setERRNUM("0");
	out.println("<SCRIPT Language=\"Javascript\">");
	out.println("       showErrors()");
	out.println("</SCRIPT>");
}
%>

</HEAD>
<BODY>
<H3 align="center"><%= (userPO.getPurpose().equals("NEW") ? "Apertura" : "Mantenimiento") %> de Carta de Credito
Stand By Recibida <IMG
	src="<%=request.getContextPath()%>/images/e_ibs.gif" align="left"
	name="EIBS_GIF" alt="ELC0525_lc_opening_incoming_sb.jsp"></H3>
<HR size="4">
<FORM>
<TABLE cellspacing="0" cellpadding="2" width="100%" border="0" class="tbhead">
  <TR id="trdark">
    <TD align="right"><STRONG>Banco</STRONG></TD>
    <TD><INPUT type="text" name="E01LCMBNK" readonly size="4" maxlength="2" value="<%=msg040001.getE01LCMBNK().trim()%>"></TD>
    <TD width="16%" align="right" nowrap><STRONG>N&uacute;mero de Carta de Credito : </STRONG></TD>
    <TD><%if (msg040001.getE01LCMACC().trim().equals("999999999999"))
				{%>
        <INPUT type="text" size="12" maxlength="12" value="Nuevo" readonly>
        <INPUT name="E01LCMACC" type="hidden" value="<%=msg040001.getE01LCMACC().trim()%>" readonly="readonly">
        <%}
				else
				{%>
        <INPUT type="text" name="E01LCMACC" size="12" maxlength="12" value="<%=msg040001.getE01LCMACC().trim()%>" readonly>
        <%}%></TD>
  </TR>
  <TR>
    <TD align="right"><STRONG>Nuestra Referencia</STRONG></TD>
    <TD><INPUT name="E01LCMORF" type="text" value="<%=msg040001.getE01LCMORF().trim()%>" size="20" maxlength="16" readonly="readonly"></TD>
    <TD width="16%" align="right" nowrap><STRONG>Producto : </STRONG></TD>
    <TD><B>
      <INPUT type="text" name="E01LCMPRO" size="4" maxlength="4" value="<%=msg040001.getE01LCMPRO().trim()%>" readonly>
    </B></TD>
  </TR>
  <TR id="trdark">
    <TD align="right"><STRONG>Referencia  del Otro Banco</STRONG></TD>
    <TD><B>
      <INPUT name="E01LCMTRF" type="text" value="<%=msg040001.getE01LCMTRF().trim()%>" size="20" maxlength="16" readonly="readonly">
    </B></TD>
    <TD width="16%" align="right" nowrap><STRONG>Descripcion de Producto : </STRONG></TD>
    <TD><B>
      <INPUT type="text" name="E01DSCPRO" size="40"
						maxlength="40" value="<%=msg040001.getE01DSCPRO().trim()%>" readonly>
    </B></TD>
  </TR>
</TABLE>
<BR>
<TABLE class="tableinfo" cellspacing="0" cellpadding="2" border="0" width="100%" align="center">
  <TR id="trdark">
    <TD colspan="2" align="center" valign="middle"><FONT size="medium"><B>Aplicante</B></FONT></TD>
  </TR>
  <TR id="">
    <TD align="right">No.  :</TD>
    <TD nowrap><SELECT name="E01LCMAF2" disabled>
        <OPTION value=" "
								<%if (!(msg040001.getE01LCMAF2().equals("C") || msg040001.getE01LCMAF2().equals("A"))) out.print("selected");%>
								selected></OPTION>
        <OPTION value="C" <%if (msg040001.getE01LCMAF2().equals("C")) out.print("selected");%>>Cliente</OPTION>
        <OPTION value="A" <%if (msg040001.getE01LCMAF2().equals("A")) out.print("selected");%>>Cuenta</OPTION>
      </SELECT>
        <INPUT name="E01LCMAPA" type="text" value="<%=msg040001.getE01LCMAPA()%>" size="12"	maxlength="12" readonly="readonly">
        <IMG src="<%=request.getContextPath()%>/images/Check.gif" alt="mandatory field" border="0"></TD>
  </TR>
  <TR id="">
    <TD width="35%" align="right">Nombre :</TD>
    <TD align="left"><INPUT name="E01LCMAP1" type="text" value="<%=msg040001.getE01LCMAP1()%>" size="45"
					maxlength="35" readonly="readonly">    </TD>
  </TR>
  <TR id="">
    <TD align="right">Direcci&oacute;n :</TD>
    <TD align="left"><INPUT name="E01LCMAP2" type="text" value="<%=msg040001.getE01LCMAP2()%>" size="45"
					maxlength="35" readonly="readonly">    </TD>
  </TR>
  <TR id="">
    <TD></TD>
    <TD align="left"><INPUT name="E01LCMAP3" type="text" value="<%=msg040001.getE01LCMAP3()%>" size="45"
					maxlength="35" readonly="readonly">    </TD>
  </TR>
  <TR id="">
    <TD></TD>
    <TD align="left"><INPUT name="E01LCMAP4" type="text" value="<%=msg040001.getE01LCMAP4()%>" size="45"
					maxlength="35" readonly="readonly">    </TD>
  </TR>
  <TR id="">
    <TD align="right">Estado</TD>
    <TD align="left"><INPUT name="E01LCMAP5" type="text"
							value="<%=msg040001.getE01LCMAP5()%>" size="4" maxlength="4" readonly="readonly">
      C&oacute;digo Postal
      <INPUT
							name="E01LCMAP6" type="text"
							value="<%=msg040001.getE01LCMAP6()%>" size="11" maxlength="10" readonly="readonly">
      Pa&iacute;s
      <INPUT
							name="E01LCMAP7" type="text"
							value="<%=msg040001.getE01LCMAP7()%>" size="4" maxlength="4" readonly="readonly"></TD>
  </TR>
</TABLE>
<BR>
<TABLE class="tableinfo"  cellspacing="0" cellpadding="2" border="0" width="100%" align="center">
  <TR id="trdark">
    <TD colspan="2" align="center" valign="middle"><FONT size="medium"><B>Beneficiario</B></FONT></TD>
  </TR>
  <TR id="">
    <TD align="right">No.  :</TD>
    <TD nowrap><SELECT name="E01LCMAF3" disabled>
        <OPTION value=" " <%if (!(msg040001.getE01LCMAF3().equals("C") || msg040001.getE01LCMAF3().equals("A"))) out.print("selected");%>></OPTION>
        <OPTION value="C" <%if (msg040001.getE01LCMAF3().equals("C")) out.print("selected");%>>Cliente</OPTION>
        <OPTION value="A" <%if (msg040001.getE01LCMAF3().equals("A")) out.print("selected");%>>Cuenta</OPTION>
      </SELECT>
        <INPUT name="E01LCMBAC" type="text" value="<%=msg040001.getE01LCMBAC()%>" size="12" maxlength="12" readonly="readonly"></TD>
  </TR>
  <TR id="">
    <TD width="35%" align="right"> Nombre :</TD>
    <TD align="left"><INPUT name="E01LCMBN1" type="text" value="<%=msg040001.getE01LCMBN1()%>" size="45"
					maxlength="35" readonly="readonly">
        <IMG
					src="<%=request.getContextPath()%>/images/Check.gif"
					alt="campo obligatorio" border="0"></TD>
  </TR>
  <TR id="">
    <TD align="right">Direcci&oacute;n :</TD>
    <TD align="left"><INPUT name="E01LCMBN2" type="text" value="<%=msg040001.getE01LCMBN2()%>" size="45"
					maxlength="35" readonly="readonly">    </TD>
  </TR>
  <TR id="">
    <TD></TD>
    <TD align="left"><INPUT name="E01LCMBN3" type="text" value="<%=msg040001.getE01LCMBN3()%>" size="45"
					maxlength="35" readonly="readonly">    </TD>
  </TR>
  <TR id="">
    <TD></TD>
    <TD align="left"><INPUT name="E01LCMBN4" type="text" value="<%=msg040001.getE01LCMBN4()%>" size="45"
					maxlength="35" readonly="readonly">    </TD>
  </TR>
  <TR id="">
    <TD align="right">Estado </TD>
    <TD align="left"><INPUT name="E01LCMBN5" type="text"
							value="<%=msg040001.getE01LCMBN5()%>" size="2" maxlength="2" readonly="readonly">
      C&oacute;digo Postal
      <INPUT
							name="E01LCMBN6" type="text"
							value="<%=msg040001.getE01LCMBN6()%>" size="11" maxlength="10" readonly="readonly">
      Pa&iacute;s
      <INPUT
							name="E01LCMBN7" type="text"
							value="<%=msg040001.getE01LCMBN7()%>" size="4" maxlength="4" readonly="readonly">
     </TD>
  </TR>
</TABLE>
<BR>
<BR>
<TABLE class="tableinfo"  cellspacing="0" cellpadding="2" border="0" width="100%" align="center">
  <TR id="trdark">
    <TD colspan="2" align="center" valign="middle" ><FONT size="medium"><B>Banco Corresponsal</B></FONT></TD>
  </TR>
  <TR id="">
    <TD align="right" nowrap="nowrap">Cliente:</TD>
    <TD align="left" nowrap><INPUT name="E01LCMCBC" type="text" value="<%=msg040001.getE01LCMCBC()%>" size="12" maxlength="12" readonly="readonly"></TD>
  </TR>
  <TR id="">
    <TD width="35%" align="right" nowrap="nowrap">Codigo Swift:</TD>
    <TD align="left"><INPUT name="E01LCMCAI" type="text" value="<%=msg040001.getE01LCMCAI()%>" size="12" maxlength="12" readonly="readonly">
 </TD>
  </TR>
  <TR id="">
    <TD align="right"> Nombre:</TD>
    <TD align="left"><INPUT name="E01LCMCA1" type="text" value="<%=msg040001.getE01LCMCA1()%>" size="45" maxlength="35" readonly="readonly">    </TD>
  </TR>
  <TR id="">
    <TD align="right">Direcci&oacute;n:</TD>
    <TD align="left"><INPUT name="E01LCMCA2" type="text" value="<%=msg040001.getE01LCMCA2()%>" size="45" maxlength="35" readonly="readonly">    </TD>
  </TR>
  <TR id="">
    <TD></TD>
    <TD align="left"><INPUT name="E01LCMCA3" type="text" value="<%=msg040001.getE01LCMCA3()%>" size="45" maxlength="35" readonly="readonly">    </TD>
  </TR>
  <TR id="">
    <TD></TD>
    <TD align="left"><INPUT name="E01LCMCA4" type="text" value="<%=msg040001.getE01LCMCA4()%>" size="45" maxlength="35" readonly="readonly">    </TD>
  </TR>
  <TR id="">
    <TD align="right" valign="bottom">Estado </TD>
    <TD align="left" nowrap><INPUT name="E01LCMCA5" type="text" value="<%=msg040001.getE01LCMCA5()%>" size="2" maxlength="2" readonly="readonly">
      C&oacute;digo Postal
      <INPUT name="E01LCMCA6" type="text" value="<%=msg040001.getE01LCMCA6()%>" size="11" maxlength="10" readonly="readonly">
      Pa&iacute;s
      <INPUT name="E01LCMCA7" type="text" value="<%=msg040001.getE01LCMCA7()%>" size="3" maxlength="7" readonly="readonly"></TD>
  </TR>
</TABLE>
<BR>
<TABLE width="100%" border="0" align="center" cellpadding="2"  cellspacing="0" class="tableinfo">
  <TR id="trdark">
    <TD colspan="2" align="center" valign="middle" ><FONT size="medium"><B>Banco Linea </B></FONT></TD>
  </TR>
  <TR id="">
    <TD align="right" nowrap="nowrap">Cliente:</TD>
    <TD align="left" nowrap><INPUT name="E01LCMCOR" type="text" id="E01LCMCOR" value="<%=msg040001.getE01LCMCOR()%>" size="12" maxlength="12" readonly="readonly">
         Linea de Credito:
      <INPUT name="E01LCMCMG" type="text" value="<%=msg040001.getE01LCMCMG()%>" size="4" maxlength="4" readonly="readonly">
	 </TD>
  </TR>
  <TR id="">
    <TD width="35%" align="right" nowrap="nowrap">Codigo Swift:</TD>
    <TD align="left"><INPUT name="E01LCMCBI" type="text" id="E01LCMCBI" value="<%=msg040001.getE01LCMCBI()%>" size="12" maxlength="12" readonly="readonly">
</TD>
  </TR>
  <TR id="">
    <TD align="right"> Nombre:</TD>
    <TD align="left"><INPUT name="E01LCMCB1" type="text" id="E01LCMCB1" value="<%=msg040001.getE01LCMCB1()%>" size="45" maxlength="35" readonly="readonly">    </TD>
  </TR>
  <TR id="">
    <TD align="right">Direcci&oacute;n:</TD>
    <TD align="left"><INPUT name="E01LCMCB2" type="text" id="E01LCMCB2" value="<%=msg040001.getE01LCMCB2()%>" size="45" maxlength="35" readonly="readonly">    </TD>
  </TR>
  <TR id="">
    <TD></TD>
    <TD align="left"><INPUT name="E01LCMCB3" type="text" id="E01LCMCB3" value="<%=msg040001.getE01LCMCB3()%>" size="45" maxlength="35" readonly="readonly">    </TD>
  </TR>
  <TR id="">
    <TD></TD>
    <TD align="left"><INPUT name="E01LCMCB4" type="text" id="E01LCMCB4" value="<%=msg040001.getE01LCMCB4()%>" size="45" maxlength="35" readonly="readonly">    </TD>
  </TR>
  <TR id="">
    <TD align="right" valign="bottom">Estado </TD>
    <TD align="left" nowrap><INPUT name="E01LCMCB5" type="text" id="E01LCMCB5" value="<%=msg040001.getE01LCMCB5()%>" size="2" maxlength="2" readonly="readonly">
      C&oacute;digo Postal
      <INPUT name="E01LCMCB6" type="text" id="E01LCMCB6" value="<%=msg040001.getE01LCMCB6()%>" size="11" maxlength="10" readonly="readonly">
      Pa&iacute;s
      <INPUT name="E01LCMCB7" type="text" id="E01LCMCB7" value="<%=msg040001.getE01LCMCB7()%>" size="3" maxlength="7" readonly="readonly">
      </TD>
  </TR>
</TABLE>
<BR>
<TABLE class="tableinfo"  cellspacing="0" cellpadding="2" border="0" width="100%" align="center">
  <TR id="trdark">
    <TD colspan="2" align="center" valign="middle" ><FONT size="medium"><B>Banco Reembolsador</B></FONT></TD>
  </TR>
  <TR id="">
    <TD nowrap="nowrap" align="right">Numero de Cliente o de Cuenta:</TD>
    <TD  align="left" nowrap><INPUT name="E01LCMRBA" type="text" value="<%=msg040001.getE01LCMRBA()%>" size="12" maxlength="12" readonly="readonly"></TD>
  </TR>
  <TR id="">
    <TD nowrap="nowrap" align="right">Codigo Swift: </TD>
    <TD align="left" nowrap><INPUT name="E01LCMRBI" type="text" value="<%=msg040001.getE01LCMRBI()%>" size="14" maxlength="12" readonly="readonly">
      </TD>
  </TR>
  <TR id="">
    <TD align="right" nowrap="nowrap">Nombre:</TD>
    <TD><INPUT name="E01LCMRB1" type="text" value="<%=msg040001.getE01LCMRB1()%>" size="45" maxlength="35" readonly="readonly"></TD>
  </TR>
  <TR id="">
    <TD align="right">Direcci&oacute;n :</TD>
    <TD align="left"><INPUT name="E01LCMRB2" type="text" value="<%=msg040001.getE01LCMRB2()%>" size="45" maxlength="35" readonly="readonly"></TD>
  </TR>
  <TR id="">
    <TD width="35%"></TD>
    <TD align="left"><INPUT name="E01LCMRB3" type="text" value="<%=msg040001.getE01LCMRB3()%>" size="45" maxlength="35" readonly="readonly"></TD>
  </TR>
  <TR id="">
    <TD></TD>
    <TD align="left"><INPUT name="E01LCMRB4" type="text" value="<%=msg040001.getE01LCMRB4()%>" size="45" maxlength="35" readonly="readonly"></TD>
  </TR>
  <TR id="">
    <TD align="right" valign="bottom">Estado:</TD>
    <TD align="left" valign="bottom" nowrap><INPUT name="E01LCMRB5" type="text" value="<%=msg040001.getE01LCMRB5()%>" size="2" maxlength="2" readonly="readonly">
      C&oacute;digo Postal
      <INPUT name="E01LCMRB6" type="text" value="<%=msg040001.getE01LCMRB6()%>" size="11" maxlength="10" readonly="readonly">
      Pa&iacute;s
      <INPUT name="E01LCMRB7" type="text" value="<%=msg040001.getE01LCMRB7()%>" size="4" maxlength="4" readonly="readonly">
      </TD>
  </TR>
</TABLE>
<BR>
<TABLE border="0" cellpadding="2"  cellspacing="0" class="tableinfo" width="100%" align="center">
  <TR id="trdark">
    <TD colspan="2" align="center" valign="middle"><FONT size="medium"><B>Banco Emisor </B></FONT></TD>
  </TR>
  <TR id="">
    <TD align="right" nowrap="NOWRAP">No. :</TD>
    <TD nowrap><SELECT name="E01LCMAF1" disabled>
        <OPTION value=" "></OPTION>
        <OPTION value="C" <%if (msg040001.getE01LCMAF1().equals("C")) out.print("selected");%>>Cliente</OPTION>
        <OPTION value="A" <%if (msg040001.getE01LCMAF1().equals("A")) out.print("selected");%>>Cuenta</OPTION>
      </SELECT>
        <INPUT name="E01LCMIBA" type="text" value="<%=msg040001.getE01LCMIBA()%>" size="12" maxlength="12" readonly="readonly"></TD>
  </TR>
  <TR id="">
    <TD width="35%" align="right">Codigo Swift:</TD>
    <TD align="left" nowrap><INPUT name="E01LCMIBI" type="text" value="<%=msg040001.getE01LCMIBI()%>" size="14" maxlength="12" readonly="readonly">
      </TD>
  </TR>
  <TR id="">
    <TD align="right">Nombre:</TD>
    <TD align="left" nowrap><INPUT name="E01LCMIB1" type="text" value="<%=msg040001.getE01LCMIB1()%>" size="45" maxlength="35" readonly="readonly">
      </TD>
  </TR>
  <TR id="">
    <TD align="right">Direcci&oacute;n :</TD>
    <TD align="left"><INPUT name="E01LCMIB2" type="text" value="<%=msg040001.getE01LCMIB2()%>" size="45" maxlength="35" readonly="readonly">    </TD>
  </TR>
  <TR id="">
    <TD></TD>
    <TD align="left"><INPUT name="E01LCMIB3" type="text" value="<%=msg040001.getE01LCMIB3()%>" size="45" maxlength="35" readonly="readonly"></TD>
  </TR>
  <TR id="">
    <TD></TD>
    <TD align="left"><INPUT name="E01LCMIB4" type="text" value="<%=msg040001.getE01LCMIB4()%>" size="45" maxlength="35" readonly="readonly">    </TD>
  </TR>
  <TR id="">
    <TD align="right" valign="bottom">Estado</TD>
    <TD align="left" valign="bottom" nowrap><INPUT name="E01LCMIB5" type="text" value="<%=msg040001.getE01LCMIB5()%>" size="2" maxlength="2" readonly="readonly">
      C&oacute;digo Postal
      <INPUT name="E01LCMIB6" type="text" value="<%=msg040001.getE01LCMIB6()%>" size="11" maxlength="10" readonly="readonly">
      Pa&iacute;s
      <INPUT name="E01LCMIB7" type="text" value="<%=msg040001.getE01LCMIB7()%>" size="4" maxlength="4" readonly="readonly">
       </TD>
  </TR>
</TABLE>

</FORM>
</BODY>
</HTML>
