<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.0 Transitional//EN">
<HTML>
<HEAD>
<TITLE>Mantenimiento de Carta de Credito Comercial</TITLE>
<META HTTP-EQUIV="Content-Type" CONTENT="text/html; charset=ISO-8859-1">
<META name="GENERATOR" content="IBM WebSphere Page Designer V3.5.2 for Windows">
<META http-equiv="Content-Style-Type" content="text/css">
<LINK Href="<%=request.getContextPath()%>/pages/style.css" rel="stylesheet">
<SCRIPT language="Javascript1.1" src="<%=request.getContextPath()%>/pages/s/javascripts/eIBS.jsp"> </SCRIPT>
<SCRIPT language="Javascript1.1" src="<%=request.getContextPath()%>/pages/s/javascripts/optMenu.jsp"> </SCRIPT>
<jsp:useBean id="msgLC" class="datapro.eibs.beans.ELC051002Message"  scope="session" />
<jsp:useBean id= "error" class= "datapro.eibs.beans.ELEERRMessage"  scope="session" />
<jsp:useBean id= "userPO" class= "datapro.eibs.beans.UserPos"  scope="session" />

<SCRIPT LANGUAGE="javascript">
	builtNewMenu(<%= msgLC.getE02LCMOPT().equals("N") ? "lc_opening" : "lc_maint"%>);
	initMenu();
</SCRIPT>
<% 
 if ( !error.getERRNUM().equals("0")  ) {
     error.setERRNUM("0"); 
     out.println("<SCRIPT Language=\"Javascript\">");
     out.println("       showErrors()");
     out.println("</SCRIPT>");
 } 
%>
</HEAD>
<BODY>
<H3 align="center">Mantenimiento de Carta de Credito Comercial<IMG src="<%=request.getContextPath()%>/images/e_ibs.gif" align="left" name="EIBS_GIF" ALT="lc_details.jsp,ELC0510"></H3>
<HR size="4">
<FORM method="post" action="<%=request.getContextPath()%>/servlet/datapro.eibs.products.JSELC0510" >
  <INPUT TYPE=HIDDEN NAME="SCREEN" VALUE="6">
<TABLE cellspacing="0" cellpadding="2" width="100%" border="0" class="tableinfo">
<TR id="trdark">
                <TD nowrap width="16%"align="right"><B>Banco : </B></TD>
                <TD nowrap width="20%" align="left">
                  <INPUT type="text" name="E02LCMBNK" size="4" maxlength="2"
							value="<%=msgLC.getE02LCMBNK().trim()%>" readonly>
                </TD>
                <TD nowrap width="16%"align="right"><B>Número de Carta de Cr&eacute;dito : </B></TD>
                <TD nowrap width="16%" align="left">
                  <B>
                  <%if (msgLC.getE02LCMACC().trim().equals("999999999999"))
				{%>
                  <INPUT type="text" size="13" maxlength="12" value="Nuevo" readonly>
                  <INPUT type="hidden" name="E02LCMACC" value="<%=msgLC.getE02LCMACC().trim()%>" readonly>
                  <%}
				else
				{%>
                  <INPUT type="text" name="E02LCMACC" size="13" maxlength="12"
							value="<%=msgLC.getE02LCMACC().trim()%>" readonly>
                  <%}%>
                  </B></TD>
    </TR>
              <TR>
                <TD nowrap width="16%"align="right"><B>Nuestra Referencia : </B></TD>
                <TD nowrap width="20%" align="left">
                  <INPUT type="text" name="E02LCMORF" size="20"
							maxlength="16" value="<%=msgLC.getE02LCMORF().trim()%>" readonly></TD>
                <TD nowrap width="16%"align="right"><B>Producto : </B></TD>
                <TD nowrap width="16%" align="left">
                  <B>
                  <INPUT type="text" name="E02LCMPRO" size="4"
							maxlength="4" value="<%=msgLC.getE02LCMPRO().trim()%>" readonly>
                  </B></TD>
              </TR>
              <TR id="trdark">
                <TD nowrap width="16%"align="right"><B>Referencia del Otro Banco : </B></TD>
                <TD nowrap width="16%" align="left">
                  <B>
                  <INPUT type="text" name="E02LCMTRF" size="20" maxlength="16" value="<%=msgLC.getE02LCMTRF().trim()%>" readonly>
                  </B></TD>
                <TD nowrap width="16%" align="right"><B>Descripci&oacute;n del Producto : </B></TD>
                <TD nowrap width="16%" align="left">
                  <B>
                  <INPUT type="text" name="E02DSCPRO" size="40" maxlength="35" value="<%=userPO.getHeader14()%>" readonly>
                  </B></TD>
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
                <TD nowrap width="16%"align="right"><B>Enmienda : </B></TD>
                <TD nowrap width="20%" align="left">
                  <% if (msgLC.getE02LCMAMF().equals("Y")) out.print("YES"); else out.print("NO");%>
                  <INPUT type="HIDDEN" name="E02LCMLAN"	value="<%= msgLC.getE02LCMLAN().trim()%>"></TD>
                <TD nowrap width="16%"align="right"><B>Ultima Enmienda : </B></TD>
                <TD nowrap width="16%">
                	<DIV align="left"><B><INPUT type="text" name="E02LCMLAN" size="4" maxlength="4" value="<%= msgLC.getE02LCMLAN().trim()%>" disabled></B></DIV>
                  	<INPUT type="HIDDEN" name="E02LCMLAN" value="<%= msgLC.getE02LCMLAN().trim()%>">
                </TD>
                <TD nowrap width="16%"align="right"><B>Fecha Ultima Enmienda : </B></TD>
                <TD nowrap width="16%">
                	<DIV align="left"><B>
                		<INPUT type="text" name="E02LCMLA1" size="3" maxlength="2" value="<%= msgLC.getE02LCMLA1().trim()%>" disabled>                  
                		<INPUT type="text" name="E02LCMLA2" size="3" maxlength="2" value="<%= msgLC.getE02LCMLA2().trim()%>" disabled>
                    	<INPUT type="text" name="E02LCMLA3" size="3" maxlength="2" value="<%= msgLC.getE02LCMLA3().trim()%>" disabled>
                    </B></DIV>
                  	<INPUT type="HIDDEN" name="E02LCMLA1" value="<%= msgLC.getE02LCMLA1().trim()%>">
                  	<INPUT type="HIDDEN" name="E02LCMLA2" value="<%= msgLC.getE02LCMLA2().trim()%>">
                  	<INPUT type="HIDDEN" name="E02LCMLA3" value="<%= msgLC.getE02LCMLA3().trim()%>">
                 </TD>
              </TR>
            </TBODY>
          </TABLE></TD>
      </TR>
    </TBODY>
  </TABLE>
  <H4>Información de la Carta de Crédito</H4>
  <TABLE class="tableinfo">
    <TR >
      <TD nowrap><TABLE cellpadding="2" cellspacing="0" width="100%" border="0">
          <TBODY>
            <TR id="trdark">
              <TD nowrap width="25%" align="right">Fecha de Emisión: </TD>
              <TD nowrap width="27%"><INPUT type="text" name="E02LCMID1" size="2" maxlength="2" value='<%= (msgLC.getE02LCMID1().trim().equals("0") ? "":msgLC.getE02LCMID1().trim())%>' onkeypress="enterInteger()">
                <INPUT type="text" name="E02LCMID2" size="2" maxlength="2" value='<%= (msgLC.getE02LCMID2().trim().equals("0") ? "":msgLC.getE02LCMID2().trim())%>' onkeypress="enterInteger()">
                <INPUT type="text" name="E02LCMID3" size="2" maxlength="2" value='<%  if(msgLC.getE02LCMID3().length() < 2 && !msgLC.getE02LCMID3().equals("0"))
							out.print("0");
					out.print((msgLC.getE02LCMID3().trim().equals("0") ? "":msgLC.getE02LCMID3().trim()));%>' onkeypress="enterInteger()">
                <A href="javascript:DatePicker(document.forms[0].E02LCMID1,document.forms[0].E02LCMID2,document.forms[0].E02LCMID3)">
                <IMG src="<%=request.getContextPath()%>/images/calendar.gif" alt="Ayuda" align="middle" border="0"></A> 
                <IMG src="<%=request.getContextPath()%>/images/Check.gif" alt="campo obligatorio" border="0"> </TD>
              <TD nowrap width="27%" align="right"> Fecha de Expiración: </TD>
              <TD nowrap width="27%"><INPUT type="text" name="E02LCMEX1" size="2" maxlength="2" value='<%= (msgLC.getE02LCMEX1().trim().equals("0") ? "":msgLC.getE02LCMEX1().trim())%>' onkeypress="enterInteger()">
                <INPUT type="text" name="E02LCMEX2" size="2" maxlength="2" value='<%= (msgLC.getE02LCMEX2().trim().equals("0") ? "":msgLC.getE02LCMEX2().trim())%>' onkeypress="enterInteger()">
                <INPUT type="text" name="E02LCMEX3" size="2" maxlength="2" value='<%  if(msgLC.getE02LCMEX3().length() < 2 && !msgLC.getE02LCMEX3().equals("0"))
							out.print("0");
					out.print((msgLC.getE02LCMEX3().trim().equals("0") ? "":msgLC.getE02LCMEX3().trim()));%>' onkeypress="enterInteger()">
                <A href="javascript:DatePicker(document.forms[0].E02LCMEX1,document.forms[0].E02LCMEX2,document.forms[0].E02LCMEX3)">
                <IMG src="<%=request.getContextPath()%>/images/calendar.gif" alt="Ayuda" align="middle" border="0"></A> <IMG src="<%=request.getContextPath()%>/images/Check.gif" alt="campo obligatorio" border="0"></TD>
            </TR>
            <TR id="trclear">
              <TD nowrap width="25%" align="right">Ultima Fecha de Embarque: </TD>
              <TD nowrap width="27%"><INPUT type="text" name="E02LCMSD1" size="2" maxlength="2" value='<%= (msgLC.getE02LCMSD1().trim().equals("0") ? "":msgLC.getE02LCMSD1().trim())%>' onkeypress="enterInteger()">
                <INPUT type="text" name="E02LCMSD2" size="2" maxlength="2" value='<%= (msgLC.getE02LCMSD2().trim().equals("0") ? "":msgLC.getE02LCMSD2().trim())%>' onkeypress="enterInteger()">
                <INPUT type="text" name="E02LCMSD3" size="2" maxlength="2" value='<%  if(msgLC.getE02LCMSD3().length() < 2 && !msgLC.getE02LCMSD3().equals("0"))
							out.print("0");
					out.print((msgLC.getE02LCMSD3().trim().equals("0") ? "":msgLC.getE02LCMSD3().trim()));%>' onkeypress="enterInteger()">
                <A href="javascript:DatePicker(document.forms[0].E02LCMSD1,document.forms[0].E02LCMSD2,document.forms[0].E02LCMSD3)">
                <IMG src="<%=request.getContextPath()%>/images/calendar.gif" alt="Ayuda" align="middle" border="0"></A> </TD>
              <TD nowrap width="27%" align="right">Fecha de Aviso/Confirmación: </TD>
              <TD nowrap width="27%"><INPUT type="text" name="E02LCMCN1" size="2" maxlength="2" value='<%= (msgLC.getE02LCMCN1().trim().equals("0") ? "":msgLC.getE02LCMCN1().trim())%>' onkeypress="enterInteger()">
                <INPUT type="text" name="E02LCMCN2" size="2" maxlength="2" value='<%= (msgLC.getE02LCMCN2().trim().equals("0") ? "":msgLC.getE02LCMCN2().trim())%>' onkeypress="enterInteger()">
                <INPUT type="text" name="E02LCMCN3" size="2" maxlength="2" value='<%  if(msgLC.getE02LCMCN3().length() < 2 && !msgLC.getE02LCMCN3().equals("0"))
							out.print("0");
					out.print((msgLC.getE02LCMCN3().trim().equals("0") ? "":msgLC.getE02LCMCN3().trim()));%>' onkeypress="enterInteger()">
                <A href="javascript:DatePicker(document.forms[0].E02LCMCN1,document.forms[0].E02LCMCN2,document.forms[0].E02LCMCN3)">
                <IMG src="<%=request.getContextPath()%>/images/calendar.gif" alt="Ayuda" align="middle" border="0"></A> </TD>
            </TR>
            <TR id="trdark">
              <TD nowrap width="25%" align="right">Oficina: </TD>
              <TD nowrap width="25%">
                <INPUT type="text" name="E02LCMBRN" size="5" maxlength="3" value="<%= msgLC.getE02LCMBRN().trim() %>" readonly></TD>
              <TD nowrap width="25%" align="right">Moneda / Tipo de Cambio M/E:</TD>
              <TD nowrap width="27%">
                <INPUT type="text" name="E02LCMCCY" size="4" maxlength="4" value="<%= msgLC.getE02LCMCCY().trim()%>" readonly>
                <INPUT type="text" name="E02LCMOFX" size="12" maxlength="11" value="<%= msgLC.getE02LCMOFX().trim()%>" ></TD>
            </TR>
            <TR id="trclear">
              <TD nowrap width="25%" align="right">Centro de Costo: </TD>
              <TD nowrap width="27%"><INPUT type="text" name="E02LCMCCN" size="9" maxlength="8" value="<%= msgLC.getE02LCMCCN().trim()%>">
                <A href="javascript:GetCostCenter('E02LCMCCN',document.forms[0].E02LCMBNK.value)"><IMG src="<%=request.getContextPath()%>/images/1b.gif" alt="Ayuda" align="bottom" border="0"></A> </TD>
              <TD nowrap width="25%" align="right">Monto del Crédito: </TD>
              <TD nowrap width="27%"><INPUT type="text" name="E02LCMOAM" size="17" maxlength="16" value="<%= msgLC.getE02LCMOAM().trim()%>" <%= ( msgLC.getE02LCMOPT().equals("N") ? "onKeyPress=\"enterDecimal()\"" : "readonly=\"readonly\"" ) %>>
                <IMG src="<%=request.getContextPath()%>/images/Check.gif" alt="campo obligatorio" border="0"></TD>
            </TR>
            <TR id="trdark">
              <TD nowrap width="25%" align="right">Clausula de Aproximación: </TD>
              <TD nowrap width="27%"><INPUT type="radio" name="E02LCMABC" value="Y" <% if(msgLC.getE02LCMABC().equals("Y")) out.print("checked");%>>
                Si
                <INPUT type="radio" name="E02LCMABC" value="N" <% if(msgLC.getE02LCMABC().equals("N")) out.print("checked");%>>
                No
                &nbsp; &nbsp; &nbsp; Porcentaje:
                <INPUT type="text" name="E02LCMABP" size="2" maxlength="2" value='<%if(msgLC.getE02LCMABP().trim().length() == 1 && !msgLC.getE02LCMABP().trim().equals("0")) out.print("0");%><%= msgLC.getE02LCMABP().trim()%>' onkeypress="enterInteger()">
                <INPUT type="text" name="E02LCMAPM" size="2" maxlength="2" value='<%if(msgLC.getE02LCMAPM().trim().length() == 1 && !msgLC.getE02LCMAPM().trim().equals("0")) out.print("0");%><%= msgLC.getE02LCMAPM().trim()%>' onkeypress="enterInteger()"></TD>
              <TD nowrap width="27%" align="right">Incoterms: </TD>
              <TD nowrap width="27%"><INPUT type="text" name="E02LCMITR" size="4" maxlength="4" value="<%= msgLC.getE02LCMITR().trim()%>">
                <A href="javascript:GetCodeCNOFC('E02LCMITR','11')"><IMG src="<%=request.getContextPath()%>/images/1b.gif" alt="Ayuda" align="bottom" border="0"></A> </TD>
            </TR>
            <TR id="trclear">
              <TD nowrap width="25%" align="right">Tenor: </TD>
              <TD nowrap width="27%"><SELECT name="E02LCMTNR">
                  <OPTION value=" "></OPTION>
                  <OPTION value="S" <% if(msgLC.getE02LCMTNR().equals("S")) out.print("selected");%>>Pago</OPTION>
                  <OPTION value="A" <% if(msgLC.getE02LCMTNR().equals("A")) out.print("selected");%>>Aceptación</OPTION>
                  <OPTION value="M" <% if(msgLC.getE02LCMTNR().equals("M")) out.print("selected");%>>Pago Mixto</OPTION>
                  <OPTION value="D" <% if(msgLC.getE02LCMTNR().equals("D")) out.print("selected");%>>Pago Diferido</OPTION>
                  <OPTION value="N" <% if(msgLC.getE02LCMTNR().equals("N")) out.print("selected");%>>Negociación</OPTION>
                </SELECT>
                <IMG src="<%=request.getContextPath()%>/images/Check.gif" alt="campo obligatorio" border="0"></TD>
              <TD nowrap width="27%" align="right">Confirmada: </TD>
              <TD nowrap width="27%"><INPUT type="radio" name="E02LCMCNF" value="Y" <% if(msgLC.getE02LCMCNF().equals("Y")) out.print("checked");%>>
                Si
                <INPUT type="radio" name="E02LCMCNF" value="N" <% if(msgLC.getE02LCMCNF().equals("N")) out.print("checked");%>>
                No </TD>
            </TR>
            <TR id="trdark">
              <TD nowrap width="25%" align="right">Agregar Confirmacion: </TD>
              <TD align="left" nowrap><SELECT name="E02LCMCNO">
                  <OPTION value=" "></OPTION>
                  <OPTION value="Y" <% if(msgLC.getE02LCMCNO().equals("Y")) out.print("selected");%>>Confirmar(CONFIRM)/OPTION>
                  <OPTION value="N" <% if(msgLC.getE02LCMCNO().equals("N")) out.print("selected");%>>No Confirmar (WITHOUT)</OPTION>
                  <OPTION value="M" <% if(msgLC.getE02LCMCNO().equals("M")) out.print("selected");%>>Puede Confirmar (MAY ADD)</OPTION>
                </SELECT>             
              </TD>
              <TD nowrap width="27%" align="right">Transferible: </TD>
              <TD nowrap width="27%"><INPUT type="radio" name="E02LCMTRN" value="Y" <% if(msgLC.getE02LCMTRN().equals("Y")) out.print("checked");%>>
                Si
                <INPUT type="radio" name="E02LCMTRN" value="N" <% if(msgLC.getE02LCMTRN().equals("N")) out.print("checked");%>>
                No </TD>
            </TR>
            <TR id="trclear">
              <TD nowrap width="25%" align="right">Embarques Parciales: </TD>
              <TD nowrap width="27%"><INPUT type="radio" name="E02LCMPSH" value="Y" <% if(msgLC.getE02LCMPSH().equals("Y")) out.print("checked");%>>
                Si
                <INPUT type="radio" name="E02LCMPSH" value="N" <% if(msgLC.getE02LCMPSH().equals("N")) out.print("checked");%>>
                No </TD>
              <TD nowrap width="27%" align="right">Transbordo: </TD>
              <TD nowrap width="27%"><INPUT type="radio" name="E02LCMTSH" value="Y" <% if(msgLC.getE02LCMTSH().equals("Y")) out.print("checked");%>>
                Si
                <INPUT type="radio" name="E02LCMTSH" value="N" <% if(msgLC.getE02LCMTSH().equals("N")) out.print("checked");%>>
                No </TD>
            </TR>
            <TR id="trdark">
              <TD nowrap width="25%" align="right">Agente/Representante: </TD>
              <TD nowrap width="27%"><INPUT type="text" name="E02LCMBRK" size="4" maxlength="3" value="<%= msgLC.getE02LCMBRK().trim()%>" onkeypress="enterDecimal()">
              <A href="javascript:GetBroker('E02LCMBRK')"><IMG src="<%=request.getContextPath()%>/images/1b.gif" alt="Ayuda" align="bottom" border="0"></A> </TD>
              <TD nowrap width="27%" align="right">% Garantía Efectivo: </TD>
              <TD nowrap width="27%"><INPUT name="E02LCMCPE" type="text" onkeypress="enterDecimal()" value="<%= msgLC.getE02LCMCPE().trim()%>" size="17" maxlength="16" disabled="disabled"></TD>
            </TR>
            <TR id="trclear">
              <TD nowrap width="25%" align="right">Monto Garantía Efectivo: </TD>
              <TD nowrap width="27%"><INPUT name="E02LCMCAM" type="text" onkeypress="enterDecimal()" value="<%= msgLC.getE02LCMCAM().trim()%>" size="17" maxlength="16" disabled="disabled">              </TD>
              <TD nowrap width="27%" align="right">Cuenta Garantía Efectivo:</TD>
              <TD nowrap width="27%"><TABLE id="trclear">
                  <TBODY>
                    <TR>
                      <TD rowspan="2"><INPUT name="E02LCMCCA" type="text" value="<%= msgLC.getE02LCMCCA().trim()%>" size="17" maxlength="16"></TD>
                      <TD>Cuenta<BR>
                        Contable</TD>
                      <TD rowspan="2"><A href="javascript:GetLedger('E02LCMCCA',document.forms[0].E02LCMBNK.value,'','')"><IMG src="<%=request.getContextPath()%>/images/1b.gif" alt="Ayuda" align="bottom" border="0"></A></TD>
                      <TD>Cuenta<BR>
                        Cliente</TD>
                      <TD rowspan="2"><A href="javascript: GetAccount('E02LCMCCA','','RT','')"><IMG src="<%=request.getContextPath()%>/images/1b.gif" alt="Ayuda" align="bottom" border="0"></A></TD>
                  </TR></TBODY>
                </TABLE></TD>
            </TR>
            <TR id="trdark">
              <TD nowrap width="25%" align="right">Tarifa del Cliente: </TD>
              <TD nowrap width="27%"><INPUT type="text" name="E02LCMTAR" size="2" maxlength="2" value="<%= msgLC.getE02LCMTAR().trim()%>">
                <A href="javascript:GetTariffLC('E02LCMTAR','<%=msgLC.getE02LCMATY()%>','<%=msgLC.getE02LCMCUN()%>','')">
				<IMG src="<%=request.getContextPath()%>/images/1b.gif" alt="Ayuda" align="bottom" border="0"></A> 
				<IMG src="<%=request.getContextPath()%>/images/Check.gif" alt="campo obligatorio" border="0"></TD>
              <TD align="right">Nuestros Cargos por Cuenta de: </TD>
              <TD nowrap><SELECT name="E02LCMAOB">
                  <OPTION value=" "></OPTION>
                  <OPTION value="A" <% if(msgLC.getE02LCMAOB().equals("A")) out.print("selected");%>>Aplicante</OPTION>
                  <OPTION value="B" <% if(msgLC.getE02LCMAOB().equals("B")) out.print("selected");%>>Beneficiario</OPTION>
                </SELECT>
                <IMG src="<%=request.getContextPath()%>/images/Check.gif" alt="campo obligatorio" border="0">
              </TD>
            </TR>
            <TR id="trclear">
              <TD align="right">Linea de Cr&eacute;dito del Cliente: </TD>
              <TD nowrap><INPUT type="text" name="E02LCMLNR" size="10" maxlength="9" value="<%= msgLC.getE02LCMLNR().trim()%>" onkeypress="enterInteger()">
                <A href="javascript:GetCustomer('E02LCMLNR')"> <IMG src="<%=request.getContextPath()%>/images/1b.gif" alt="Ayuda" align="bottom" border="0"></A>
                <INPUT type="text" name="E02LCMCMN" size="4" maxlength="4" value="<%= msgLC.getE02LCMCMN().trim()%>" onkeypress="enterInteger()">
				<A href="javascript:GetCreditLine('E02LCMCMN',document.forms[0].E02LCMLNR.value)">
				<IMG src="<%=request.getContextPath()%>/images/1b.gif" alt="Ayuda" border="0"></A></TD>
              <TD nowrap width="27%" align="right">Número Referencia ALADI: </TD>
              <TD nowrap width="27%"><INPUT type="text" name="E02LCMSRF" size="17" maxlength="16" value="<%= msgLC.getE02LCMSRF().trim()%>" disabled>              </TD>
            </TR>
            <TR id="trdark">
              <TD nowrap width="25%" align="right">Com.Apertura Día Emisión: </TD>
              <TD nowrap width="27%"><INPUT type="radio" name="E02LCMOCI" value="Y" <% if(msgLC.getE02LCMOCI().equals("Y")) out.print("checked");%>>
                Si
                <INPUT type="radio" name="E02LCMOCI" value="N" <% if(msgLC.getE02LCMOCI().equals("N")) out.print("checked");%>>
                No </TD>
              <TD nowrap width="27%" align="right">Com.Enmienda Día Enmienda: </TD>
              <TD nowrap width="27%"><INPUT type="radio" name="E02LCMACI" value="Y" <% if(msgLC.getE02LCMACI().equals("Y")) out.print("checked");%>>
                Si
                <INPUT type="radio" name="E02LCMACI" value="N" <% if(msgLC.getE02LCMACI().equals("N")) out.print("checked");%>>
                No </TD>
            </TR>
            <TR id="trclear">
              <TD nowrap width="25%" align="right">Lugar de Expiración: </TD>
              <TD nowrap width="27%"><INPUT type="text" name="E02LCMPLE" size="29" maxlength="29" value="<%= msgLC.getE02LCMPLE().trim()%>">
                <IMG src="<%=request.getContextPath()%>/images/Check.gif" alt="campo obligatorio" border="0"></TD>
              <TD width="25%" align="right" nowrap>Referencia Pre-Aviso: </TD><TD nowrap width="27%"><INPUT type="text" name="E02LCMRF1" size="18" maxlength="16" value="<%= msgLC.getE02LCMRF1().trim()%>"></TD>
              
            </TR>
			<TR id="trdark">
				<TD width="25%" align="right" nowrap>Cuenta Debito Comisiones: </TD>
				<TD width="25%">
					<INPUT type="text" name="E02LCMSCA" size="17" maxlength="12" value="<%= msgLC.getE02LCMSCA().trim()%>">
						<A href="javascript: GetAccByClient('E02LCMSCA','','RT','','')" >
						<IMG src="<%=request.getContextPath()%>/images/1b.gif" alt="Ayuda" align="bottom" border="0"></A>
				</TD>
			  <% if (userPO.getID().equals("18") ) { %>		
              		<TD width="25%" align="right" nowrap>Notario: </TD>
              		<TD nowrap width="27%"><INPUT type="text" name="E02LCMUC5" size="6" maxlength="3" value="<%= msgLC.getE02LCMUC5().trim()%>"></TD>
			  <% } else {%>
				  	<TD width="25%" align="right" nowrap></TD>
				  	<TD width="25%" align="right" nowrap></TD>
			  <% } %>
			</TR>
            <TR id="trclear">
              <TD nowrap width="25%" align="right">Reglas a Aplicar: </TD>
              <TD nowrap width="27%" colspan="3">              <SELECT name="E02LCMAPR">
						<OPTION value=""></OPTION>
						<OPTION value="1" <% if(msgLC.getE02LCMAPR().equals("1")) out.print("selected");%>>UCP Latest Version</OPTION>
						<OPTION value="2" <% if(msgLC.getE02LCMAPR().equals("2")) out.print("selected");%>>EUCP Latest Version</OPTION>
						<OPTION value="3" <% if(msgLC.getE02LCMAPR().equals("3")) out.print("selected");%>>UCPURR Latest Version</OPTION>
						<OPTION value="4" <% if(msgLC.getE02LCMAPR().equals("4")) out.print("selected");%>>EUCPURR Latest Version</OPTION>
						<OPTION value="5" <% if(msgLC.getE02LCMAPR().equals("5")) out.print("selected");%>>ISP Latest Version</OPTION>
						<OPTION value="6" <% if(msgLC.getE02LCMAPR().equals("6")) out.print("selected");%>>Other</OPTION>
					</SELECT>  <IMG src="<%=request.getContextPath()%>/images/Check.gif" alt="campo obligatorio" border="0"> <INPUT type="text" name="E02LCMST3" size="40" maxlength="35" value="<%= msgLC.getE02LCMST3().trim()%>"></TD>
            </TR>
				<TR id="trdark">
				  <TD align="right" nowrap>Lugar de&nbsp;Recepci&oacute;n: </TD>
				  <TD nowrap colspan="3"><INPUT type="text" name="E02LCMPLR" size="100" maxlength="65" value="<%= msgLC.getE02LCMPLR().trim()%>"></TD>
		    </TR>
				<TR id="trclear">
              <TD nowrap width="25%" align="right">Puerto/Aeropuerto de Salida: </TD>
              <TD nowrap width="27%" colspan="3"><INPUT type="text" name="E02LCMPOL" size="100" maxlength="65" value="<%= msgLC.getE02LCMPOL().trim()%>"></TD>
            </TR>
            <TR id="trdark">
              <TD nowrap width="25%" align="right">Puerto/Aeropuerto de Destino: </TD>
              <TD nowrap width="27%" colspan="3"><INPUT type="text" name="E02LCMPOD" size="100" maxlength="65" value="<%= msgLC.getE02LCMPOD().trim()%>"></TD>
            </TR>
            <TR id="trclear">
              <TD nowrap width="25%" align="right">Lugar de Entrega: </TD>
              <TD nowrap width="27%" colspan="3"><INPUT type="text" name="E02LCMPLD" size="100" maxlength="65" value="<%= msgLC.getE02LCMPLD().trim()%>"></TD>
            </TR>
            <TR id="trdark">
              <TD align="right" nowrap>Credito Disponible Con: </TD>
              <TD align="left" nowrap><INPUT type="text" name="E02LCMAWI" size="14" maxlength="12" value="<%= msgLC.getE02LCMAWI().trim()%>">
                <A href="javascript:GetSwiftIdDesc('E02LCMAWI','','','')"> <IMG src="<%=request.getContextPath()%>/images/1b.gif" alt="Ayuda" border="0"></A></TD>
              <TD align="right" nowrap>Banco Girado: </TD>
              <TD align="left" nowrap><INPUT type="text" name="E02LCMDWI" size="14" maxlength="12" value="<%= msgLC.getE02LCMDWI().trim()%>">
                <A href="javascript:GetSwiftIdDesc('E02LCMDWI','','','')"><IMG src="<%=request.getContextPath()%>/images/1b.gif" alt="Ayuda" border="0"></A></TD>
            </TR>
            <TR id="trclear">
              <TD align="right" nowrap>Tarifa del Corresponsal: </TD>
              <TD align="left" nowrap><INPUT type="text" name="E02LCMCCT" size="3" maxlength="3" value="<%= msgLC.getE02LCMCCT().trim()%>" onkeypress="enterInteger()"> 
			  <A href="javascript:GetTariffLC('E02LCMCCT','<%=msgLC.getE02LCMATY()%>','<%=msgLC.getE02LCMCBC()%>','C')">
				  <IMG src="<%=request.getContextPath()%>/images/1b.gif" alt="Ayuda" align="bottom" border="0"></A></TD>
              <TD align="right" nowrap>Cargos O/B por Cuenta de: </TD>
              <TD align="left" nowrap><SELECT name="E02LCMOBC">
                  <OPTION value=" "></OPTION>
                  <OPTION value="A" <% if(msgLC.getE02LCMOBC().equals("A")) out.print("selected");%>>Aplicante</OPTION>
                  <OPTION value="B" <% if(msgLC.getE02LCMOBC().equals("B")) out.print("selected");%>>Beneficiario</OPTION>
                </SELECT>              </TD>
            </TR>
            <%if(userPO.getID().equals("18")){%>
            <TR id="trdark">
              <TD nowrap width="25%" align="right">Tipo de Inter&eacute;s: </TD>
              <TD nowrap width="27%"><INPUT name="E02LCMICT" type="text" value="<%= msgLC.getE02LCMICT().trim()%>" size="1" maxlength="1" disabled="disabled"></TD>
              <TD nowrap width="27%" align="right">Tasa de Inter&eacute;s: </TD>
              <TD nowrap width="27%"><INPUT type="text" name="E02LCMIRT" size="9" maxlength="9" value="<%= msgLC.getE02LCMIRT().trim()%>" disabled="disabled"></TD>
            </TR>
            <TR id="trclear">
              <TD nowrap width="25%" align="right">Tabla / Tipo de Tasa Flotante: </TD>
              <TD nowrap width="27%"><INPUT name="E02LCMFTB" type="text" value="<%= msgLC.getE02LCMFTB().trim()%>" size="2" maxlength="2" disabled="disabled">
                <SELECT name="E02LCMFTY" disabled="disabled">
                  <OPTION value=" "> </OPTION>
                  <OPTION value="FP">Tasa Primaria</OPTION>
                  <OPTION value="FS" <% if(msgLC.getE02LCMOCI().equals("FS")) out.print("selected");%>>Tasa Secundaria</OPTION>
                </SELECT></TD>
              <TD nowrap width="27%" align="right">Per&iacute;odo Base Calc.Intereses: </TD>
              <TD nowrap width="27%"><INPUT name="E02LCMBAS" type="text" onkeypress="enterInteger()" value="<%= msgLC.getE02LCMBAS().trim()%>" size="3" maxlength="3" disabled="disabled"></TD>
            </TR>
			<TR id="trdark">
					<TD nowrap width="25%" align="right">Condonar Intereses en Cancelacion: </TD>
					<TD nowrap width="25%">
					   <INPUT type="radio" name="E02LCMWIF" value="Y" <% if(msgLC.getE02LCMWIF().equals("Y")) out.print("checked");%>>Si
                	   <INPUT type="radio" name="E02LCMWIF" value="N" <% if(msgLC.getE02LCMWIF().equals("N")) out.print("checked");%>>No  
					</TD>
				    <TD nowrap width="25%" align="right">Cuenta de Repago de Intereses: </TD>
					<TD nowrap width="25%">
				       <INPUT type="text" name="E02LCMIPA" size="17" maxlength="12" value="<%= msgLC.getE02LCMIPA().trim()%>">
					  <A href="javascript: GetAccByClient('E02LCMIPA','','RT','','')" >
					  <IMG src="<%=request.getContextPath()%>/images/1b.gif" alt="Ayuda" align="bottom" border="0"></A>
					</TD>
              
            </TR><%}%>

			<TR id="trclear">
				  <TD width="25%" align="right" nowrap>Generar Swift:</TD>
				  <TD nowrap width="25%"><INPUT type="radio" name="E02LCMSWF" value="Y" <% if(!msgLC.getE02LCMSWF().equals("N")) out.print("checked");%>>
				    Si
				    <INPUT type="radio" name="E02LCMSWF" value="N" <% if( msgLC.getE02LCMSWF().equals("N")) out.print("checked");%>>
				    No </TD>
				  <TD nowrap>&nbsp;</TD>
				  <TD nowrap>&nbsp;</TD>
			</TR>
          </TBODY>
        </TABLE></TD>
    </TR>
  </TABLE>
    <% if( !msgLC.getE02LCMOPT().equals("N") ) {%>
  <H4>Aumento / Disminuci&oacute;n del Cr&eacute;dito</H4>
  <TABLE class="tableinfo">
    <TBODY>
      <TR>
        <TD nowrap><TABLE cellspacing="0" cellpadding="2" width="100%" border="0"
				class="tbhead">
            <TBODY>
              <TR id="trdark00">
                <TD nowrap width="16%"align="right">Incremento / Decremento: </TD>
                <TD nowrap width="20%" align="left"><SELECT name="E02LCMIDF">
                  <OPTION value=""> </OPTION>
                  <OPTION value="I" <% if(msgLC.getE02LCMIDF().equals("I")) out.print("selected");%>>Incremento</OPTION>
                  <OPTION value="D" <% if(msgLC.getE02LCMIDF().equals("D")) out.print("selected");%>>Decremento</OPTION>
                </SELECT></TD>
                <TD nowrap width="16%"align="right">Monto: </TD>
                <TD nowrap width="16%"><INPUT name="E02LCMIDA" type="text" onKeyPress="enterDecimal()" value="<%= msgLC.getE02LCMIDA().trim()%>" size="17" maxlength="16"></TD>
                <TD nowrap width="15%"align="right">Saldo Actual: </TD>
                <TD nowrap width="15%"><INPUT name="E02LCMMEB" type="text"  value="<%= msgLC.getE02LCMMEB().trim()%>" size="17" maxlength="16" readonly></TD>
              </TR>
            </TBODY>
        </TABLE></TD>
      </TR>
    </TBODY>
  </TABLE>
  <%}%>
  <P>&nbsp;</P>
  <DIV align="center">
    <INPUT type="hidden" id="FLAG" name="H02FLGWK1" value="">
  <h4 style="text-align:center"><input type="checkbox" name="H02FLGWK2" value="A" <% if(msgLC.getH02FLGWK2().equals("A")){ out.print("checked");} %>>
      Aceptar con Advertencias</h4>
  <INPUT id="EIBSBTN" type=submit name="Submit" value="Enviar"> &nbsp; &nbsp; &nbsp;
  <INPUT id="EIBSBTN" type="submit" name="Submit0" value="Finalizar" onClick="document.getElementById('FLAG').value='Y'">
  </DIV>
</FORM>
</BODY>
</HTML>
