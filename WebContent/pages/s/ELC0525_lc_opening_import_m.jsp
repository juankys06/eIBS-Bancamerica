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
<jsp:useBean id="msg051001"  class="datapro.eibs.beans.ELC051001Message" scope="session" />
<jsp:useBean id="error"  class="datapro.eibs.beans.ELEERRMessage" scope="session" />
<jsp:useBean id="userPO" class="datapro.eibs.beans.UserPos" scope="session" />

<SCRIPT LANGUAGE="javascript">

	<% if (userPO.getAccOpt().equals("T") )  { %>
			builtNewMenu(lc_apr_tranfer_cc_maint);
	<% } else { %>
			builtNewMenu(lc_apr_cc_maint);
	<% } %>
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
<H3 align="center">Mantenimiento Carta de Credito<IMG
	src="<%=request.getContextPath()%>/images/e_ibs.gif" align="left"
	name="EIBS_GIF" alt="ELC0525_lc_opening_import_m.jsp"></H3>
<HR size="4">
<FORM>
<TABLE cellspacing="0" cellpadding="2" width="100%" border="0" class="tableinfo" id="trclear">
            <TR id="trdark">
              <TD nowrap width="16%" align="right"><B>Banco :</B> </TD>
              <TD nowrap width="20%" align="left"><INPUT type="text" name="E01LCMBNK" readonly
						size="4" maxlength="2" value="<%= msg051001.getE01LCMBNK().trim()%>"></TD>
              <TD nowrap width="16%" align="right"><B>Número de Carta de Credito :</B> </TD>
              <TD nowrap width="16%" align="left"><B> <B>
                <INPUT type="text" name="E01LCMACC" size="13" maxlength="12"
						value="<%= msg051001.getE01LCMACC().trim()%>" readonly>
                </B></B></TD>
            </TR>
            <TR>
              <TD nowrap width="16%" align="right"><B>Nuestra Referencia :</B> </TD>
              <TD nowrap width="20%" align="left"><INPUT name="E01LCMORF" type="text" value="<%= msg051001.getE01LCMORF().trim()%>" size="20"
						maxlength="16" readonly="readonly">
              </TD>
              <TD nowrap width="16%" align="right"><B>Producto :</B> </TD>
              <TD nowrap width="16%" align="left"><B>
                <INPUT type="text" name="E01LCMPRO" size="4"
						maxlength="4" value="<%= msg051001.getE01LCMPRO().trim()%>" readonly>
                </B> </TD>
            </TR>
            <TR id="trdark">
              <TD nowrap width="16%" align="right"><B>Referencia del Otro Banco :</B> </TD>
              <TD nowrap width="16%" align="left"><B>
                <INPUT name="E01LCMTRF" type="text" value="<%=msg051001.getE01LCMTRF().trim()%>" size="20" maxlength="16" readonly="readonly">
                </B> </TD>
              <TD nowrap width="16%" align="right"><B>Descripcion de Producto :</B> </TD>
              <TD nowrap width="16%" align="left"><B>
                <INPUT type="text" name="E01DSCPRO" size="40"
						maxlength="35" value="<%=msg051001.getE01DSCPRO().trim()%>" readonly>
                </B> </TD>
            </TR>
  </TABLE>
  <H4>Tipo de Operación</H4>
  <TABLE class="tableinfo">
    <TBODY>
      <TR>
        <TD nowrap><TABLE cellspacing="0" cellpadding="2" width="100%" border="0"
				class="tbhead">
            <TBODY>
              <TR id="trdark">
                <TD nowrap width="16%" align="right"><B>Enmienda :</B> </TD>
                <TD nowrap width="20%" align="left"><% if (msg051001.getE01LCMAMF().equals("Y")) out.print("YES"); else out.print("NO");%>
                  <INPUT name="E01LCMLAN" type="HIDDEN"
							value="<%= msg051001.getE01LCMLAN().trim()%>" readonly="readonly">
                </TD>
                <TD nowrap width="16%" align="right"><B>Ultima Enmienda: </B> </TD>
                <TD nowrap width="16%" align="left"><B>
                  <INPUT type="text" name="E01LCMLAN" size="4"
							maxlength="4" value="<%= msg051001.getE01LCMLAN().trim()%>" readonly>
                  </B>
                  <INPUT name="E01LCMLAN" type="HIDDEN" value="<%= msg051001.getE01LCMLAN().trim()%>" readonly="readonly"></TD>
                <TD nowrap width="16%" align="right"><B>Fecha Ultima Enmienda: </B></TD>
                <TD nowrap width="16%" align="left"><B>
                  <INPUT type="text" name="E01LCMLA1" size="3"
							maxlength="2" value="<%= msg051001.getE01LCMLA1().trim()%>" readonly>
                  <INPUT type="text" name="E01LCMLA2" size="3" maxlength="2" value="<%= msg051001.getE01LCMLA2().trim()%>" readonly>
                  <INPUT type="text" name="E01LCMLA3" size="3" maxlength="2" value="<%= msg051001.getE01LCMLA3().trim()%>" readonly>
                  </B>
                  <INPUT name="E01LCMLA1" type="HIDDEN" value="<%= msg051001.getE01LCMLA1().trim()%>" readonly="readonly">
                  <INPUT
							name="E01LCMLA2" type="HIDDEN" value="<%= msg051001.getE01LCMLA2().trim()%>" readonly="readonly">
                  <INPUT name="E01LCMLA3" type="HIDDEN"
							value="<%= msg051001.getE01LCMLA3().trim()%>" readonly="readonly"></TD>
              </TR>
            </TBODY>
          </TABLE></TD>
      </TR>
    </TBODY>
  </TABLE>
  
  <P>&nbsp;</P>
  <TABLE class="tableinfo" cellspacing="0" cellpadding="2" border="0" width="100%" align="center">
    <TR id="trdark">
      <TD colspan="2" align="center" valign="middle"><FONT size="medium"><B>Aplicante</B></FONT></TD>
    </TR>
    <TR id="">
      <TD width="35%" align="right">No.  :</TD>
      <TD nowrap>
      	<SELECT name="E01LCMAF2" disabled >
          		<OPTION value=" "	<%if (!(msg051001.getE01LCMAF2().equals("C") || msg051001.getE01LCMAF2().equals("A"))) out.print("selected");%>selected></OPTION>
          		<OPTION value="C" <%if (msg051001.getE01LCMAF2().equals("C")) out.print("selected");%>>Cliente</OPTION>
          		<OPTION value="A" <%if (msg051001.getE01LCMAF2().equals("A")) out.print("selected");%>>Cuenta</OPTION>
        </SELECT>
        <INPUT name="E01LCMAPA" type="text" value="<%=msg051001.getE01LCMAPA()%>" size="12"	maxlength="12" readonly="readonly" 
        	<%if (msg051001.getF01LCMAPA().equals("Y")) out.print("id=\"txtchanged\"");%>>
        	<IMG src="<%=request.getContextPath()%>/images/Check.gif" alt="mandatory field" border="0">  
      </TD>
    </TR>
    <TR id="">
      <TD align="right">Nombre :</TD>
      <TD align="left"><INPUT name="E01LCMAP1" type="text" value="<%=msg051001.getE01LCMAP1()%>" size="45" maxlength="35" readonly="readonly" 
        	<%if (msg051001.getF01LCMAP1().equals("Y")) out.print("id=\"txtchanged\"");%>>
      </TD>
    </TR>
    <TR id="">
      <TD align="right">Direcci&oacute;n :</TD>
      <TD align="left"><INPUT name="E01LCMAP2" type="text" value="<%=msg051001.getE01LCMAP2()%>" size="45" maxlength="35" readonly="readonly"
        	<%if (msg051001.getF01LCMAP2().equals("Y")) out.print("id=\"txtchanged\"");%>>
      </TD>
    </TR>
    <TR id="">
      <TD></TD>
      <TD align="left"><INPUT name="E01LCMAP3" type="text" value="<%=msg051001.getE01LCMAP3()%>" size="45" maxlength="35" readonly="readonly"
        	<%if (msg051001.getF01LCMAP3().equals("Y")) out.print("id=\"txtchanged\"");%>>
      </TD>
    </TR>
    <TR id="">
      <TD></TD>
      <TD align="left"><INPUT name="E01LCMAP4" type="text" value="<%=msg051001.getE01LCMAP4()%>" size="45" maxlength="35" readonly="readonly"
        	<%if (msg051001.getF01LCMAP4().equals("Y")) out.print("id=\"txtchanged\"");%>>
      </TD>
    </TR>
    <TR id="">
      <TD align="right">Estado</TD>
      <TD align="left"><INPUT name="E01LCMAP5" type="text" value="<%=msg051001.getE01LCMAP5()%>" size="4" maxlength="4" readonly="readonly"
        	<%if (msg051001.getF01LCMAP5().equals("Y")) out.print("id=\"txtchanged\"");%>>
        C&oacute;digo Postal
        <INPUT name="E01LCMAP6" type="text"	value="<%=msg051001.getE01LCMAP6()%>" size="11" maxlength="10" readonly="readonly"
        	<%if (msg051001.getF01LCMAP6().equals("Y")) out.print("id=\"txtchanged\"");%>>
        Pa&iacute;s<INPUT name="E01LCMAP7" type="text" value="<%=msg051001.getE01LCMAP7()%>" size="4" maxlength="4" readonly="readonly"
        	<%if (msg051001.getF01LCMAP7().equals("Y")) out.print("id=\"txtchanged\"");%>>
      </TD>
    </TR>
  </TABLE>
  <BR>
  <TABLE class="tableinfo"  cellspacing="0" cellpadding="2" border="0" width="100%" align="center">
    <TR id="trdark">
      <TD colspan="2" align="center" valign="middle"><FONT size="medium"><B>Beneficiario</B></FONT></TD>
    </TR>
    <TR id="">
      <TD width="35%" align="right">No.  :</TD>
      <TD nowrap><SELECT name="E01LCMAF3" disabled >
          <OPTION value=" " <%if (!(msg051001.getE01LCMAF3().equals("C") || msg051001.getE01LCMAF3().equals("A"))) out.print("selected");%>></OPTION>
          <OPTION value="C" <%if (msg051001.getE01LCMAF3().equals("C")) out.print("selected");%>>Cliente</OPTION>
          <OPTION value="A" <%if (msg051001.getE01LCMAF3().equals("A")) out.print("selected");%>>Cuenta</OPTION>
        </SELECT>
        <INPUT name="E01LCMBAC" type="text" value="<%=msg051001.getE01LCMBAC()%>" size="12" maxlength="12" readonly="readonly"
        	<%if (msg051001.getF01LCMBAC().equals("Y")) out.print("id=\"txtchanged\"");%>>
      </TD>
    </TR>
    <TR id="">
      <TD align="right"> Nombre :</TD>
      <TD align="left"><INPUT name="E01LCMBN1" type="text" value="<%=msg051001.getE01LCMBN1()%>" size="45" maxlength="35" readonly="readonly"
        	<%if (msg051001.getF01LCMBN1().equals("Y")) out.print("id=\"txtchanged\"");%>>
          <IMG src="<%=request.getContextPath()%>/images/Check.gif" alt="campo obligatorio" border="0"></TD>
    </TR>
    <TR id="">
      <TD align="right">Direcci&oacute;n :</TD>
      <TD align="left"><INPUT name="E01LCMBN2" type="text" value="<%=msg051001.getE01LCMBN2()%>" size="45" maxlength="35" readonly="readonly"
        	<%if (msg051001.getF01LCMBN2().equals("Y")) out.print("id=\"txtchanged\"");%>>
      </TD>
    </TR>
    <TR id="">
      <TD></TD>
      <TD align="left"><INPUT name="E01LCMBN3" type="text" value="<%=msg051001.getE01LCMBN3()%>" size="45" maxlength="35" readonly="readonly"
        	<%if (msg051001.getF01LCMBN3().equals("Y")) out.print("id=\"txtchanged\"");%>>
      </TD>
    </TR>
    <TR id="">
      <TD></TD>
      <TD align="left"><INPUT name="E01LCMBN4" type="text" value="<%=msg051001.getE01LCMBN4()%>" size="45" maxlength="35" readonly="readonly"
        	<%if (msg051001.getF01LCMBN4().equals("Y")) out.print("id=\"txtchanged\"");%>>
      </TD>
    </TR>
    <TR id="">
      <TD align="right">Estado </TD>
      <TD align="left"><INPUT name="E01LCMBN5" type="text" value="<%=msg051001.getE01LCMBN5()%>" size="2" maxlength="2" readonly="readonly"
        	<%if (msg051001.getF01LCMBN5().equals("Y")) out.print("id=\"txtchanged\"");%>>
        C&oacute;digo Postal
        <INPUT name="E01LCMBN6" type="text"	value="<%=msg051001.getE01LCMBN6()%>" size="11" maxlength="10" readonly="readonly"
        	<%if (msg051001.getF01LCMBN6().equals("Y")) out.print("id=\"txtchanged\"");%>>
        Pa&iacute;s
        <INPUT name="E01LCMBN7" type="text"	value="<%=msg051001.getE01LCMBN7()%>" size="4" maxlength="4" readonly="readonly"
        	<%if (msg051001.getF01LCMBN7().equals("Y")) out.print("id=\"txtchanged\"");%>>
         </TD>
    </TR>
  </TABLE>
  <BR>
  <TABLE class="tableinfo"  cellspacing="0" cellpadding="2" border="0" width="100%" align="center">
    <TR id="trdark">
      <TD colspan="2" align="center" valign="middle" ><FONT size="medium"><B>Banco Corresponsal</B></FONT></TD>
    </TR>
    <TR id="">
      <TD width="35%" align="right" nowrap="nowrap">Cliente:</TD>
      <TD align="left" nowrap><P>
        <INPUT name="E01LCMCBC" type="text" value="<%=msg051001.getE01LCMCBC()%>" size="12" maxlength="12" readonly="readonly"
        	<%if (msg051001.getF01LCMCBC().equals("Y")) out.print("id=\"txtchanged\"");%>>
        </P>
      </TD>
    </TR>
    <TR id="">
      <TD nowrap="nowrap" align="right">Codigo Swift:</TD>
      <TD align="left"><INPUT name="E01LCMCAI" type="text" value="<%=msg051001.getE01LCMCAI()%>" size="12" maxlength="12" readonly="readonly"
        	<%if (msg051001.getF01LCMCAI().equals("Y")) out.print("id=\"txtchanged\"");%>>
         </TD>
    </TR>
    <TR id="">
      <TD align="right"> Nombre:</TD>
      <TD align="left"><INPUT name="E01LCMCA1" type="text" value="<%=msg051001.getE01LCMCA1()%>" size="45" maxlength="35" readonly="readonly"
        	<%if (msg051001.getF01LCMCA1().equals("Y")) out.print("id=\"txtchanged\"");%>>
      </TD>
    </TR>
    <TR id="">
      <TD align="right">Direcci&oacute;n:</TD>
      <TD align="left"><INPUT name="E01LCMCA2" type="text" value="<%=msg051001.getE01LCMCA2()%>" size="45" maxlength="35" readonly="readonly"
        	<%if (msg051001.getF01LCMCA2().equals("Y")) out.print("id=\"txtchanged\"");%>>
      </TD>
    </TR>
    <TR id="">
      <TD></TD>
      <TD align="left"><INPUT name="E01LCMCA3" type="text" value="<%=msg051001.getE01LCMCA3()%>" size="45" maxlength="35" readonly="readonly"
        	<%if (msg051001.getF01LCMCA3().equals("Y")) out.print("id=\"txtchanged\"");%>>
      </TD>
    </TR>
    <TR id="">
      <TD></TD>
      <TD align="left"><INPUT name="E01LCMCA4" type="text" value="<%=msg051001.getE01LCMCA4()%>" size="45" maxlength="35" readonly="readonly"
        	<%if (msg051001.getF01LCMCA4().equals("Y")) out.print("id=\"txtchanged\"");%>>
      </TD>
    </TR>
    <TR id="">
      <TD align="right" valign="bottom">Estado </TD>
      <TD align="left" nowrap><INPUT name="E01LCMCA5" type="text" value="<%=msg051001.getE01LCMCA5()%>" size="2" maxlength="2" readonly="readonly"
        	<%if (msg051001.getF01LCMCA5().equals("Y")) out.print("id=\"txtchanged\"");%>>
        C&oacute;digo Postal
        <INPUT name="E01LCMCA6" type="text" value="<%=msg051001.getE01LCMCA6()%>" size="11" maxlength="10" readonly="readonly"
        	<%if (msg051001.getF01LCMCA6().equals("Y")) out.print("id=\"txtchanged\"");%>>
        Pa&iacute;s
        <INPUT name="E01LCMCA7" type="text" value="<%=msg051001.getE01LCMCA7()%>" size="3" maxlength="7" readonly="readonly"
        	<%if (msg051001.getF01LCMCA7().equals("Y")) out.print("id=\"txtchanged\"");%>>
        </TD>
    </TR>
  </TABLE>
  <BR>
  <TABLE width="100%" border="0" align="center" cellpadding="2"  cellspacing="0" class="tableinfo">
    <TR id="trdark">
      <TD colspan="2" align="center" valign="middle" ><FONT size="medium"><B>Banco Linea </B></FONT></TD>
    </TR>
    <TR id="">
      <TD width="35%" align="right" nowrap="nowrap">Cliente:</TD>
      <TD align="left" nowrap><INPUT name="E01LCMCOR" type="text" id="E01LCMCOR" value="<%=msg051001.getE01LCMCOR()%>" size="12" maxlength="12" readonly="readonly"
        	<%if (msg051001.getF01LCMCOR().equals("Y")) out.print("id=\"txtchanged\"");%>>
           Linea de Credito:
        <INPUT name="E01LCMCMG" type="text" value="<%=msg051001.getE01LCMCMG()%>" size="4" maxlength="4" readonly="readonly" 
        	<%if (msg051001.getF01LCMCMG().equals("Y")) out.print("id=\"txtchanged\"");%>>
		</TD>
    </TR>
    <TR id="">
      <TD nowrap="nowrap" align="right">Codigo Swift:</TD>
      <TD align="left"><INPUT name="E01LCMCBI" type="text" id="E01LCMCBI" value="<%=msg051001.getE01LCMCBI()%>" size="12" maxlength="12" readonly="readonly"
        	<%if (msg051001.getF01LCMCBI().equals("Y")) out.print("id=\"txtchanged\"");%>>
         </TD>
    </TR>
    <TR id="">
      <TD align="right"> Nombre:</TD>
      <TD align="left"><INPUT name="E01LCMCB1" type="text" id="E01LCMCB1" value="<%=msg051001.getE01LCMCB1()%>" size="45" maxlength="35" readonly="readonly"
        	<%if (msg051001.getF01LCMCB1().equals("Y")) out.print("id=\"txtchanged\"");%>>
      </TD>
    </TR>
    <TR id="">
      <TD align="right">Direcci&oacute;n:</TD>
      <TD align="left"><INPUT name="E01LCMCB2" type="text" id="E01LCMCB2" value="<%=msg051001.getE01LCMCB2()%>" size="45" maxlength="35" readonly="readonly"
        	<%if (msg051001.getF01LCMCB2().equals("Y")) out.print("id=\"txtchanged\"");%>>
      </TD>
    </TR>
    <TR id="">
      <TD></TD>
      <TD align="left"><INPUT name="E01LCMCB3" type="text" id="E01LCMCB3" value="<%=msg051001.getE01LCMCB3()%>" size="45" maxlength="35" readonly="readonly"
        	<%if (msg051001.getF01LCMCB3().equals("Y")) out.print("id=\"txtchanged\"");%>>
      </TD>
    </TR>
    <TR id="">
      <TD></TD>
      <TD align="left"><INPUT name="E01LCMCB4" type="text" id="E01LCMCB4" value="<%=msg051001.getE01LCMCB4()%>" size="45" maxlength="35" readonly="readonly"
        	<%if (msg051001.getF01LCMCB4().equals("Y")) out.print("id=\"txtchanged\"");%>>
      </TD>
    </TR>
    <TR id="">
      <TD align="right" valign="bottom">Estado </TD>
      <TD align="left" nowrap><INPUT name="E01LCMCB5" type="text" id="E01LCMCB5" value="<%=msg051001.getE01LCMCB5()%>" size="2" maxlength="2" readonly="readonly"
        	<%if (msg051001.getF01LCMCB5().equals("Y")) out.print("id=\"txtchanged\"");%>>
        C&oacute;digo Postal
        <INPUT name="E01LCMCB6" type="text" id="E01LCMCB6" value="<%=msg051001.getE01LCMCB6()%>" size="11" maxlength="10" readonly="readonly"
        	<%if (msg051001.getF01LCMCB6().equals("Y")) out.print("id=\"txtchanged\"");%>>
        Pa&iacute;s
        <INPUT name="E01LCMCB7" type="text" id="E01LCMCB7" value="<%=msg051001.getE01LCMCB7()%>" size="3" maxlength="7" readonly="readonly"
        	<%if (msg051001.getF01LCMCB7().equals("Y")) out.print("id=\"txtchanged\"");%>>
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
      <TD  align="left" nowrap><INPUT name="E01LCMRBA" type="text" value="<%=msg051001.getE01LCMRBA()%>" size="12" maxlength="12" readonly="readonly" 
        	<%if (msg051001.getF01LCMRBA().equals("Y")) out.print("id=\"txtchanged\"");%>>
	  </TD>
    </TR>
    <TR id="">
      <TD nowrap="nowrap" align="right">Codigo Swift: </TD>
      <TD align="left" nowrap><INPUT name="E01LCMRBI" type="text" value="<%=msg051001.getE01LCMRBI()%>" size="14" maxlength="12" readonly="readonly"
        	<%if (msg051001.getF01LCMRBI().equals("Y")) out.print("id=\"txtchanged\"");%>>
        </TD>
    </TR>
    <TR id="">
      <TD align="right" nowrap="nowrap">Nombre:</TD>
      <TD><INPUT name="E01LCMRB1" type="text" value="<%=msg051001.getE01LCMRB1()%>" size="45" maxlength="35" readonly="readonly"
        	<%if (msg051001.getF01LCMRB1().equals("Y")) out.print("id=\"txtchanged\"");%>>
      </TD>
    </TR>
    <TR id="">
      <TD align="right">Direcci&oacute;n :</TD>
      <TD align="left"><INPUT name="E01LCMRB2" type="text" value="<%=msg051001.getE01LCMRB2()%>" size="45" maxlength="35" readonly="readonly"
        	<%if (msg051001.getF01LCMRB2().equals("Y")) out.print("id=\"txtchanged\"");%>>
      </TD>
    </TR>
    <TR id="">
      <TD width="35%"></TD>
      <TD align="left"><INPUT name="E01LCMRB3" type="text" value="<%=msg051001.getE01LCMRB3()%>" size="45" maxlength="35" readonly="readonly"        	
        	<%if (msg051001.getF01LCMRB3().equals("Y")) out.print("id=\"txtchanged\"");%>>
	  </TD>
    </TR>
    <TR id="">
      <TD></TD>
      <TD align="left"><INPUT name="E01LCMRB4" type="text" value="<%=msg051001.getE01LCMRB4()%>" size="45" maxlength="35" readonly="readonly"
        	<%if (msg051001.getF01LCMRB4().equals("Y")) out.print("id=\"txtchanged\"");%>>
      </TD>
    </TR>
    <TR id="">
      <TD align="right" valign="bottom">Estado:</TD>
      <TD align="left" valign="bottom" nowrap><INPUT name="E01LCMRB5" type="text" value="<%=msg051001.getE01LCMRB5()%>" size="2" maxlength="2" readonly="readonly"
        	<%if (msg051001.getF01LCMRB5().equals("Y")) out.print("id=\"txtchanged\"");%>>
        C&oacute;digo Postal
        <INPUT name="E01LCMRB6" type="text" value="<%=msg051001.getE01LCMRB6()%>" size="11" maxlength="10" readonly="readonly"
        	<%if (msg051001.getF01LCMRB6().equals("Y")) out.print("id=\"txtchanged\"");%>>
        Pa&iacute;s
        <INPUT name="E01LCMRB7" type="text" value="<%=msg051001.getE01LCMRB7()%>" size="4" maxlength="4" readonly="readonly"
        	<%if (msg051001.getF01LCMRB7().equals("Y")) out.print("id=\"txtchanged\"");%>>
        </TD>
    </TR>
  </TABLE>
  <BR>
  <TABLE border="0" cellpadding="2"  cellspacing="0" class="tableinfo" width="100%" align="center">
    <TR id="trdark">
      <TD colspan="2" align="center" valign="middle"><FONT size="medium"><B>Banco Avisador / Confirmador</B></FONT></TD>
    </TR>
    <TR id="">
      <TD align="right" nowrap="NOWRAP">No. :</TD>
      <TD nowrap><SELECT name="E01LCMAF4" disabled >
          <OPTION value=""></OPTION>
          <OPTION value="C" <%if (msg051001.getE01LCMAF4().equals("C")) out.print("selected");%>>Cliente</OPTION>
          <OPTION value="A" <%if (msg051001.getE01LCMAF4().equals("A")) out.print("selected");%>>Cuenta</OPTION>
        </SELECT>
        <INPUT name="E01LCMABA" type="text" value="<%=msg051001.getE01LCMABA()%>" size="12" maxlength="12" readonly="readonly"
       		<%if (msg051001.getF01LCMABA().equals("Y")) out.print("id=\"txtchanged\"");%>>
           </TD>
    </TR>
    <TR id="">
      <TD width="35%" align="right" nowrap>Codigo Swift:</TD>
      <TD align="left" nowrap><INPUT name="E01LCMABI" type="text" value="<%=msg051001.getE01LCMABI()%>" size="12" maxlength="12" readonly="readonly"
          		<%if (msg051001.getF01LCMABI().equals("Y")) out.print("id=\"txtchanged\"");%>>
         </TD>
    </TR>
    <TR id="">
      <TD align="right" nowrap>Nombre:</TD>
      <TD align="left" nowrap><INPUT name="E01LCMAB1" type="text" value="<%=msg051001.getE01LCMAB1()%>" size="45" maxlength="35" readonly="readonly">
       		<%if (msg051001.getF01LCMAB1().equals("Y")) out.print("id=\"txtchanged\"");%>
          <IMG src="<%=request.getContextPath()%>/images/Check.gif" alt="campo obligatorio" border="0"></TD>
    </TR>
    <TR id="">
      <TD align="right" nowrap>Direcci&oacute;n :</TD>
      <TD align="left" nowrap><INPUT name="E01LCMAB2" type="text" value="<%=msg051001.getE01LCMAB2()%>" size="45" maxlength="35" readonly="readonly"      
       		<%if (msg051001.getF01LCMAB2().equals("Y")) out.print("id=\"txtchanged\"");%>>
      </TD>    		
    </TR>
    <TR id="">
      <TD nowrap></TD>
      <TD align="left" nowrap><INPUT name="E01LCMAB3" type="text" value="<%=msg051001.getE01LCMAB3()%>" size="45" maxlength="35" readonly="readonly"      
       		<%if (msg051001.getF01LCMAB3().equals("Y")) out.print("id=\"txtchanged\"");%>>
      </TD>
    </TR>
    <TR id="">
      <TD nowrap></TD>
      <TD align="left" nowrap><INPUT name="E01LCMAB4" type="text" value="<%=msg051001.getE01LCMAB4()%>" size="45" maxlength="35" readonly="readonly"      
       		<%if (msg051001.getF01LCMAB4().equals("Y")) out.print("id=\"txtchanged\"");%>>
      </TD>
    </TR>
    <TR id="">
      <TD align="right" valign="bottom" nowrap>Estado</TD>
      <TD align="left" nowrap><INPUT name="E01LCMAB5" type="text" value="<%=msg051001.getE01LCMAB5()%>" size="2" maxlength="2" readonly="readonly"
       		<%if (msg051001.getF01LCMAB5().equals("Y")) out.print("id=\"txtchanged\"");%>>
        C&oacute;digo Postal
        <INPUT name="E01LCMAB6" type="text" value="<%=msg051001.getE01LCMAB6()%>" size="11" maxlength="10" readonly="readonly"
       		<%if (msg051001.getF01LCMAB6().equals("Y")) out.print("id=\"txtchanged\"");%>>
        Pa&iacute;s
        <INPUT name="E01LCMAB7" type="text" value="<%=msg051001.getE01LCMAB7()%>" size="4" maxlength="4" readonly="readonly"
       		<%if (msg051001.getF01LCMAB7().equals("Y")) out.print("id=\"txtchanged\"");%>>
        </TD>
    </TR>
  </TABLE>
</FORM>
</BODY>
</HTML>
