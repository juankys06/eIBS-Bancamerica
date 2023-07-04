<HTML>
<HEAD>
<TITLE>Negociación de Cartas de Crédito</TITLE>
<META HTTP-EQUIV="Content-Type" CONTENT="text/html; charset=ISO-8859-1">
<LINK HREF="<%=request.getContextPath()%>/pages/style.css" REL="stylesheet">

<SCRIPT LANGUAGE="Javascript1.1" SRC="<%=request.getContextPath()%>/pages/s/javascripts/eIBS.jsp"> </SCRIPT>
<jsp:useBean id="msg01" class="datapro.eibs.beans.ELC020001Message" scope="session" />
<jsp:useBean id="error" class="datapro.eibs.beans.ELEERRMessage" scope="session" />
<jsp:useBean id="userPO" class="datapro.eibs.beans.UserPos" scope="session" />


<%if (!error.getERRNUM().equals("0"))
{
	error.setERRNUM("0");
	out.println("<SCRIPT Language=\"Javascript\">");
	out.println("       showErrors()");
	out.println("</SCRIPT>");
}
%>

</HEAD>

<BODY BGCOLOR="#FFFFFF">

<H3 ALIGN="center">Control de Documentos Recibidos <IMG SRC="<%=request.getContextPath()%>/images/e_ibs.gif" ALIGN="left"
	NAME="EIBS_GIF" ALT="lc_doc_info.jsp, ELC0200"></H3>
<HR SIZE="4">
<BR>


<FORM METHOD="post" ACTION="<%=request.getContextPath()%>/servlet/datapro.eibs.products.JSELC0200">
<INPUT TYPE="HIDDEN" NAME="SCREEN" VALUE="5">
<INPUT TYPE="HIDDEN" NAME="E01LCIDNO" VALUE="<%=msg01.getE01LCIDNO().trim()%>">

<TABLE width="100%" border="0" class="tableinfo" id="trdark" align="center">
  <TR>
    <TD align="right" nowrap>Banco: </TD>
    <TD nowrap><INPUT name="E01LCMBNK" value="<%=msg01.getE01LCMBNK()%>" size="4" maxlength="4" readonly></TD>
    <TD colspan="2" align="right" nowrap>Numero de Carta de Credito: </TD>
    <TD nowrap><INPUT name="E01LCMACC" value="<%=msg01.getE01LCMACC()%>" size="15" readonly></TD>
  </TR>
  <TR>
    <TD align="right" nowrap>Nuestra Referencia: </TD>
    <TD colspan="2" nowrap><INPUT name="E01LCMORF" value="<%=msg01.getE01LCMORF()%>" size="18" maxlength="18" readonly></TD>
    <TD align="right" nowrap>Nombre del Cliente: </TD>
    <TD nowrap><INPUT name="E01CUSNA1" value="<%=msg01.getE01CUSNA1()%>" size="46" maxlength="46" readonly></TD>
  </TR>
  <TR>
    <TD align="right" nowrap>Referencia del Otro Banco: </TD>
    <TD colspan="2" nowrap><INPUT name="E01LCMTRF" value="<%=msg01.getE01LCMTRF()%>" size="18" maxlength="18" readonly></TD>
    <TD align="right" nowrap>Nombre del Beneficiario: </TD>
    <TD nowrap><INPUT name="E01LCMBN1" value="<%=msg01.getE01LCMBN1()%>" size="46" maxlength="40" readonly></TD>
  </TR>
</TABLE>

<BR>
<TABLE class="tableinfo" id="trclear" cellspacing="0" cellpadding="2" width="100%" border="0" >
				  <TR  id="trdark">
				    <TD align="right" nowrap>Fecha y Hora Recibidos: </TD>
					<TD nowrap><INPUT name="E01LCIRDM" value="<%=msg01.getE01LCIRDM()%>" size="2" maxlength="2"> / 
						<INPUT name="E01LCIRDD" value="<%=msg01.getE01LCIRDD()%>" size="2" maxlength="2"> / 
						<INPUT name="E01LCIRDY" value="<%=msg01.getE01LCIRDY()%>" size="2" maxlength="2"> 
						<A href="javascript:DatePicker(document.forms[0].E01LCIRDM,document.forms[0].E01LCIRDD,document.forms[0].E01LCIRDY)">
						<IMG src="<%=request.getContextPath()%>/images/calendar.gif" alt="help" align="middle" border="0"></A>
					    <INPUT name="E01LCITIM" value="<%=msg01.getE01LCITIM()%>" size="8" maxlength="8">	 <IMG
						src="<%=request.getContextPath()%>/images/Check.gif" alt="campo obligatorio" border="0"></TD>
					<TD align="right" nowrap>Moneda e Importe: </TD>
					<TD nowrap><INPUT name="E01LCICCY" value="<%=msg01.getE01LCICCY()%>" size="3" maxlength="3">
					<A href="javascript:GetCurrency('E01LCICCY',document.forms[0].E01LCMBNK.value)">
					 <IMG src="<%=request.getContextPath()%>/images/1b.gif" alt="Ayuda" align="bottom" border="0"></A> 
					  <INPUT name="E01LCIAMN" value="<%=msg01.getE01LCIAMN()%>" size="15" maxlength="15">
					  <IMG src="<%=request.getContextPath()%>/images/Check.gif" alt="campo obligatorio" border="0"></TD>
				  </TR>
				  <TR>
				    <TD align="right" nowrap>Tipo de Transaccion: </TD>
				    <TD nowrap>
					<SELECT name="E01LCINTY">
						<OPTION>&nbsp;</OPTION>
						<OPTION value="1" <%if(msg01.getE01LCINTY().equals("1")) out.print("selected");%>>Pago a la Vista</OPTION>
						<OPTION value="2" <%if(msg01.getE01LCINTY().equals("2")) out.print("selected");%>>Aceptacion</OPTION>
						<OPTION value="3" <%if(msg01.getE01LCINTY().equals("3")) out.print("selected");%>>Aceptacion Descontada</OPTION>
						<OPTION value="4" <%if(msg01.getE01LCINTY().equals("4")) out.print("selected");%>>Pago Diferido</OPTION>
						<OPTION value="5" <%if(msg01.getE01LCINTY().equals("5")) out.print("selected");%>>Refinanciacion</OPTION>
						<OPTION value="M" <%if(msg01.getE01LCINTY().equals("M")) out.print("selected");%>>Pago Mixto</OPTION>
						<OPTION value="N" <%if(msg01.getE01LCINTY().equals("N")) out.print("selected");%>>Negociacion</OPTION>
					</SELECT>
					<IMG src="<%=request.getContextPath()%>/images/Check.gif"
						alt="campo obligatorio" border="0"></TD>
				    <TD align="right" nowrap>Referencia del Beneficiario: </TD>
				    <TD nowrap><INPUT name="E01LCIBRE" value="<%=msg01.getE01LCIBRE()%>" size="16, 18"></TD>
				  </TR>
				  <TR id="trdark">
				    <TD align="right" nowrap>Documentos Asignados a: </TD>
				    <TD nowrap><INPUT name="E01LCIAST" value="<%=msg01.getE01LCIAST()%>" size="40" maxlength="30"></TD>
		            <TD align="right" nowrap>Estado / Situacion: </TD>
		            <TD nowrap><SELECT name="E01LCISTA">
                      <OPTION value=" "></OPTION>
                      <OPTION value="D" <% if(msg01.getE01LCISTA().equals("D")) out.print("selected");%>>Documentos con Discrepancias</OPTION>
                      <OPTION value="A" <% if(msg01.getE01LCISTA().equals("A")) out.print("selected");%>>Discrepancia Autorizada</OPTION>
                      <OPTION value="R" <% if(msg01.getE01LCISTA().equals("R")) out.print("selected");%>>Discrepancia denegada</OPTION>
                      <OPTION value="O" <% if(msg01.getE01LCISTA().equals("O")) out.print("selected");%>>Documentos en Orden</OPTION>
                    </SELECT></TD>
			  </TR>
				  <TR id="trdark">
				    <TD align="right" nowrap>Aviso sin Documentos: </TD>
				    <TD colspan="3" nowrap><SELECT name="E01LCINDF">
                      <OPTION value=" "></OPTION>
                      <OPTION value="D" <% if(msg01.getE01LCINDF().equals("D")) out.print("selected");%>>Aviso de Disrepancias sin Documentos</OPTION>
                      <OPTION value="A" <% if(msg01.getE01LCINDF().equals("A")) out.print("selected");%>>Aviso de Negociacion sin Documentos</OPTION>
                    </SELECT></TD>
			      </TR>
			  </TABLE>
		

<BR>

<TABLE width="70%" border="0" class="tableinfo">
  <TR id="trdark">
		<TD colspan="6" align="center"><B><FONT size="-1">Documentos Recibidos</FONT>
		</B></TD>
	</TR>
  <TR id="trclear">
    <TD align="center">
    <TABLE border="0" class="tableinfo">
    <TR align="center">
    <TD width="8%" align="center"></TD>
		<TD align="center" width="320"></TD>
		<TD colspan="2" align="center">Requeridos</TD><TD colspan="2" align="center">Recibidos</TD></TR>
	<TR align="center">
		<TD align="center">Cod. Documento</TD>
		<TD align="center" width="320">Descripcion</TD>
		<TD align="center" width="84">Originales</TD>
		<TD align="center" width="85">Copias</TD>
		<TD align="center" width="82">Originales</TD>
		<TD align="center" width="85">Copias</TD>
	</TR>
	<TR align="center">
      <TD align="center"><INPUT type="text" name="E01LCMDD1" value="<%=msg01.getE01LCMDD1()%>" size="4" maxlength="5" readonly></TD>
	  <TD align="center" width="320"><INPUT type="text" name="E01LCMDS1" value="<%=msg01.getE01LCMDS1()%>" size="35" maxlength="35" readonly></TD>
	  <TD align="center" width="84"><INPUT type="text" name="E01LCMDO1" value="<%=msg01.getE01LCMDO1()%>" size="2" maxlength="1" readonly></TD>
	  <TD align="center" width="85"><INPUT type="text" name="E01LCMDC1" value="<%=msg01.getE01LCMDC1()%>" size="2" maxlength="1" readonly></TD>
	  <TD align="center" width="82"><INPUT type="text" name="E01RCVDO1" value="<%=msg01.getE01RCVDO1()%>" size="2" maxlength="1"></TD>
	  <TD align="center" width="85"><INPUT type="text" name="E01RCVDC1" value="<%=msg01.getE01RCVDC1()%>" size="2" maxlength="1"></TD>
	</TR>
	<TR align="center">
      <TD align="center"><INPUT type="text" name="E01LCMDD2" value="<%=msg01.getE01LCMDD2()%>" size="4" maxlength="5" readonly></TD>
	  <TD align="center" width="320"><INPUT type="text" name="E01LCMDS2" value="<%=msg01.getE01LCMDS2()%>" size="35" maxlength="35" readonly></TD>
	  <TD align="center" width="84"><INPUT type="text" name="E01LCMDO2" value="<%=msg01.getE01LCMDO2()%>" size="2" maxlength="1" readonly></TD>
	  <TD align="center" width="85"><INPUT type="text" name="E01LCMDC2" value="<%=msg01.getE01LCMDC2()%>" size="2" maxlength="1" readonly></TD>
	  <TD align="center" width="82"><INPUT type="text" name="E01RCVDO2" value="<%=msg01.getE01RCVDO2()%>" size="2" maxlength="1"></TD>
	  <TD align="center" width="85"><INPUT type="text" name="E01RCVDC2" value="<%=msg01.getE01RCVDC2()%>" size="2" maxlength="1"></TD>
	</TR>
	<TR align="center">
      <TD align="center"><INPUT type="text" name="E01LCMDD3" value="<%=msg01.getE01LCMDD3()%>" size="4" maxlength="5" readonly></TD>
	  <TD align="center" width="320"><INPUT type="text" name="E01LCMDS3" value="<%=msg01.getE01LCMDS3()%>" size="35" maxlength="35" readonly></TD>
	  <TD align="center" width="84"><INPUT type="text" name="E01LCMDO3" value="<%=msg01.getE01LCMDO3()%>" size="2" maxlength="1" readonly></TD>
	  <TD align="center" width="85"><INPUT type="text" name="E01LCMDC3" value="<%=msg01.getE01LCMDC3()%>" size="2" maxlength="1" readonly></TD>
	  <TD align="center" width="82"><INPUT type="text" name="E01RCVDO3" value="<%=msg01.getE01RCVDO3()%>" size="2" maxlength="1"></TD>
	  <TD align="center" width="85"><INPUT type="text" name="E01RCVDC3" value="<%=msg01.getE01RCVDC3()%>" size="2" maxlength="1"></TD>
	</TR>
	<TR align="center">
      <TD align="center"><INPUT type="text" name="E01LCMDD4" value="<%=msg01.getE01LCMDD4()%>" size="4" maxlength="5" readonly></TD>
	  <TD align="center" width="320"><INPUT type="text" name="E01LCMDS4" value="<%=msg01.getE01LCMDS4()%>" size="35" maxlength="35" readonly></TD>
	  <TD align="center" width="84"><INPUT type="text" name="E01LCMDO4" value="<%=msg01.getE01LCMDO4()%>" size="2" maxlength="1" readonly></TD>
	  <TD align="center" width="85"><INPUT type="text" name="E01LCMDC4" value="<%=msg01.getE01LCMDC4()%>" size="2" maxlength="1" readonly></TD>
	  <TD align="center" width="82"><INPUT type="text" name="E01RCVDO4" value="<%=msg01.getE01RCVDO4()%>" size="2" maxlength="1"></TD>
	  <TD align="center" width="85"><INPUT type="text" name="E01RCVDC4" value="<%=msg01.getE01RCVDC4()%>" size="2" maxlength="1"></TD>
	</TR>
	<TR align="center">
      <TD align="center"><INPUT type="text" name="E01LCMDD5" value="<%=msg01.getE01LCMDD5()%>" size="4" maxlength="5" readonly></TD>
	  <TD align="center" width="320"><INPUT type="text" name="E01LCMDS5" value="<%=msg01.getE01LCMDS5()%>" size="35" maxlength="35" readonly></TD>
	  <TD align="center" width="84"><INPUT type="text" name="E01LCMDO5" value="<%=msg01.getE01LCMDO5()%>" size="2" maxlength="1" readonly></TD>
	  <TD align="center" width="85"><INPUT type="text" name="E01LCMDC5" value="<%=msg01.getE01LCMDC5()%>" size="2" maxlength="1" readonly></TD>
	  <TD align="center" width="82"><INPUT type="text" name="E01RCVDO5" value="<%=msg01.getE01RCVDO5()%>" size="2" maxlength="1"></TD>
	  <TD align="center" width="85"><INPUT type="text" name="E01RCVDC5" value="<%=msg01.getE01RCVDC5()%>" size="2" maxlength="1"></TD>
	</TR>
	<TR align="center">
      <TD align="center"><INPUT type="text" name="E01LCMDD6" value="<%=msg01.getE01LCMDD6()%>" size="4" maxlength="5" readonly></TD>
	  <TD align="center" width="320"><INPUT type="text" name="E01LCMDS6" value="<%=msg01.getE01LCMDS6()%>" size="35" maxlength="35" readonly></TD>
	  <TD align="center" width="84"><INPUT type="text" name="E01LCMDO6" value="<%=msg01.getE01LCMDO6()%>" size="2" maxlength="1" readonly></TD>
	  <TD align="center" width="85"><INPUT type="text" name="E01LCMDC6" value="<%=msg01.getE01LCMDC6()%>" size="2" maxlength="1" readonly></TD>
	  <TD align="center" width="82"><INPUT type="text" name="E01RCVDO6" value="<%=msg01.getE01RCVDO6()%>" size="2" maxlength="1"></TD>
	  <TD align="center" width="85"><INPUT type="text" name="E01RCVDC6" value="<%=msg01.getE01RCVDC6()%>" size="2" maxlength="1"></TD>	  
	</TR>
	<TR align="center">
      <TD align="center"><INPUT type="text" name="E01LCMDD7" value="<%=msg01.getE01LCMDD7()%>" size="4" maxlength="5" readonly></TD>
	  <TD align="center" width="320"><INPUT type="text" name="E01LCMDS7" value="<%=msg01.getE01LCMDS7()%>" size="35" maxlength="35" readonly></TD>
	  <TD align="center" width="84"><INPUT type="text" name="E01LCMDO7" value="<%=msg01.getE01LCMDO7()%>" size="2" maxlength="1" readonly></TD>
	  <TD align="center" width="85"><INPUT type="text" name="E01LCMDC7" value="<%=msg01.getE01LCMDC7()%>" size="2" maxlength="1" readonly></TD>
	  <TD align="center" width="82"><INPUT type="text" name="E01RCVDO7" value="<%=msg01.getE01RCVDO7()%>" size="2" maxlength="1"></TD>
	  <TD align="center" width="85"><INPUT type="text" name="E01RCVDC7" value="<%=msg01.getE01RCVDC7()%>" size="2" maxlength="1"></TD>	  
	</TR>
	<TR align="center">
      <TD align="center"><INPUT type="text" name="E01LCMDD8" value="<%=msg01.getE01LCMDD8()%>" size="4" maxlength="5" readonly></TD>
	  <TD align="center" width="320"><INPUT type="text" name="E01LCMDS8" value="<%=msg01.getE01LCMDS8()%>" size="35" maxlength="35" readonly></TD>
	  <TD align="center" width="84"><INPUT type="text" name="E01LCMDO8" value="<%=msg01.getE01LCMDO8()%>" size="2" maxlength="1" readonly></TD>
	  <TD align="center" width="85"><INPUT type="text" name="E01LCMDC8" value="<%=msg01.getE01LCMDC8()%>" size="2" maxlength="1" readonly></TD>
	  <TD align="center" width="82"><INPUT type="text" name="E01RCVDO8" value="<%=msg01.getE01RCVDO8()%>" size="2" maxlength="1"></TD>
	  <TD align="center" width="85"><INPUT type="text" name="E01RCVDC8" value="<%=msg01.getE01RCVDC8()%>" size="2" maxlength="1"></TD>	  
	</TR>
	<TR align="center">
      <TD align="center"><INPUT type="text" name="E01LCMDD9" value="<%=msg01.getE01LCMDD9()%>" size="4" maxlength="5" readonly></TD>
	  <TD align="center" width="320"><INPUT type="text" name="E01LCMDS9" value="<%=msg01.getE01LCMDS9()%>" size="35" maxlength="35" readonly></TD>
	  <TD align="center" width="84"><INPUT type="text" name="E01LCMDO9" value="<%=msg01.getE01LCMDO9()%>" size="2" maxlength="1" readonly></TD>
	  <TD align="center" width="85"><INPUT type="text" name="E01LCMDC9" value="<%=msg01.getE01LCMDC9()%>" size="2" maxlength="1" readonly></TD>
	  <TD align="center" width="82"><INPUT type="text" name="E01RCVDO9" value="<%=msg01.getE01RCVDO9()%>" size="2" maxlength="1"></TD>
	  <TD align="center" width="85"><INPUT type="text" name="E01RCVDC9" value="<%=msg01.getE01RCVDC9()%>" size="2" maxlength="1"></TD>	  
	</TR>
	</TABLE>
	</TD>
	</TR>					
</TABLE>

<BR>

<TABLE width="100%" border="0" class="tableinfo">
  <TR id="trdark">
    <TD colspan="2" align="center"><B><FONT size="-1">Banco Autorizado a Pagar / Negociar</FONT> </B></TD>
    </TR>
  <TR>
    <TD width="40%" align="right">Codigo Swift</TD>
    <TD width="60%"><INPUT name="E01LCINBI" value="<%=msg01.getE01LCINBI()%>" size="12">
              <A href="javascript: GetCustomerDetailsLC('E01LCINBI','E01LCIMB1','E01LCIMB2','E01LCIMB3','E01LCIMB4','E01LCIMB5','E01LCIMB6','E01LCIMB7','C')">
              <IMG src="<%=request.getContextPath()%>/images/1b.gif" alt="Ayuda" align="bottom" border="0"></A></TD>
  </TR>
  <TR>
    <TD align="right">Nombre</TD>
    <TD><INPUT name="E01LCINB1" value="<%=msg01.getE01LCINB1()%>" maxlength="35" size="37"></TD>
  </TR>
  <TR>
    <TD align="right">Direccion</TD>
    <TD><INPUT name="E01LCINB2" value="<%=msg01.getE01LCINB2()%>" maxlength="35" size="37"></TD>
  </TR>
  <TR>
    <TD align="right">&nbsp;</TD>
    <TD><INPUT name="E01LCINB3" value="<%=msg01.getE01LCINB3()%>" maxlength="35" size="37"></TD>
  </TR>
  <TR>
    <TD align="right">&nbsp;</TD>
    <TD><INPUT name="E01LCINB4" value="<%=msg01.getE01LCINB4()%>" size="37" maxlength="25"></TD>
  </TR>
  <TR>
    <TD align="right">Estado</TD>
    <TD><INPUT name="E01LCINB5" value="<%=msg01.getE01LCINB5()%>" maxlength="2" size="2"> 
    Codigo Postal
	  <INPUT name="E01LCINB6" value="<%=msg01.getE01LCINB6()%>" size="10" maxlength="10"> 
	Pais <INPUT name="E01LCINB7" value="<%=msg01.getE01LCINB7()%>" maxlength="4" size="4"> <A href="javascript:GetCodeCNOFC('E01LCINB7','03')"><IMG
			src="<%=request.getContextPath()%>/images/1b.gif" alt="Ayuda" align="bottom" border="0"></A></TD>
  </TR>
</TABLE>

<BR>
<TABLE width="100%" border="0" class="tableinfo" id="trclear">
  <TR id="trdark">
    <TD colspan="2" align="center"><FONT size="-1"><B>Banco Reembolsador</B></FONT></TD>
  </TR>
  
<TR>
    <TD width="40%" align="right">Codigo Swift</TD>
    <TD width="60%"><INPUT name="E01LCIRBI" value="<%=msg01.getE01LCIRBI()%>" size="12">
		<A href="javascript: GetCustomerDetailsLC('E01LCIRBI','E01LCMIB1','E01LCMIB2','E01LCMIB3','E01LCMIB4','E01LCMIB5','E01LCMIB6','E01LCMIB7','5')">
		<IMG src="<%=request.getContextPath()%>/images/1b.gif" alt="Ayuda" align="bottom" border="0"></A></TD>
  </TR>
  <TR>
    <TD align="right">Nombre</TD>
    <TD><INPUT name="E01LCIRB1" value="<%=msg01.getE01LCIRB1()%>" maxlength="35" size="37"></TD>
  </TR>
  <TR>
    <TD align="right">Direccion</TD>
    <TD><INPUT name="E01LCIRB2" value="<%=msg01.getE01LCIRB2()%>" maxlength="35" size="37"></TD>
  </TR>
  <TR>
    <TD align="right">&nbsp;</TD>
    <TD><INPUT name="E01LCIRB3" value="<%=msg01.getE01LCIRB3()%>" maxlength="35" size="37"></TD>
  </TR>
  <TR>
    <TD align="right">&nbsp;</TD>
    <TD><INPUT name="E01LCIRB4" value="<%=msg01.getE01LCIRB4()%>" size="25, 37"></TD>
  </TR>
  <TR>
    <TD align="right">Estado</TD>
    <TD><INPUT name="E01LCIRB5" value="<%=msg01.getE01LCIRB5()%>" maxlength="2" size="2"> Codigo Postal <INPUT name="E01LCIRB6" value="<%=msg01.getE01LCIRB6()%>" size="10">&nbsp;
    Pais <INPUT name="E01LCIRB7" value="<%=msg01.getE01LCIRB7()%>" size="4"> <A
			href="javascript:GetCodeCNOFC('E01LCIRB7','03')"><IMG src="<%=request.getContextPath()%>/images/1b.gif" alt="Ayuda" align="bottom"
			border="0"></A></TD>
  </TR>
</TABLE>
<BR>
<TABLE class="tableinfo" width="100%">
	<TR id="trdark">
    <TD  align="center"><FONT size="-1"><B>Discrepancias</B></FONT></TD>
  </TR>
    <TR > 
      <TD nowrap align="center"> 
      <DIV style="height:220px; overflow-y: scroll">
          <INPUT type="text" name="E01LCID01" size="50" maxlength="35" value="<%= msg01.getE01LCID01().trim()%>"><BR>
          <INPUT type="text" name="E01LCID02" size="50" maxlength="35" value="<%= msg01.getE01LCID02().trim()%>"><BR>
          <INPUT type="text" name="E01LCID03" size="50" maxlength="35" value="<%= msg01.getE01LCID03().trim()%>"><BR>
          <INPUT type="text" name="E01LCID04" size="50" maxlength="35" value="<%= msg01.getE01LCID04().trim()%>"><BR>
          <INPUT type="text" name="E01LCID05" size="50" maxlength="35" value="<%= msg01.getE01LCID05().trim()%>"><BR>
          <INPUT type="text" name="E01LCID06" size="50" maxlength="35" value="<%= msg01.getE01LCID06().trim()%>"><BR>
          <INPUT type="text" name="E01LCID07" size="50" maxlength="35" value="<%= msg01.getE01LCID07().trim()%>"><BR>
          <INPUT type="text" name="E01LCID08" size="50" maxlength="35" value="<%= msg01.getE01LCID08().trim()%>"><BR>
          <INPUT type="text" name="E01LCID09" size="50" maxlength="35" value="<%= msg01.getE01LCID09().trim()%>"><BR>
          <INPUT type="text" name="E01LCID10" size="50" maxlength="35" value="<%= msg01.getE01LCID10().trim()%>"><BR>
          <INPUT type="text" name="E01LCID11" size="50" maxlength="35" value="<%= msg01.getE01LCID11().trim()%>"><BR>
          <INPUT type="text" name="E01LCID12" size="50" maxlength="35" value="<%= msg01.getE01LCID12().trim()%>"><BR>
          <INPUT type="text" name="E01LCID13" size="50" maxlength="35" value="<%= msg01.getE01LCID13().trim()%>"><BR>
          <INPUT type="text" name="E01LCID14" size="50" maxlength="35" value="<%= msg01.getE01LCID14().trim()%>"><BR>
          <INPUT type="text" name="E01LCID15" size="50" maxlength="35" value="<%= msg01.getE01LCID15().trim()%>"><BR>
          <INPUT type="text" name="E01LCID16" size="50" maxlength="35" value="<%= msg01.getE01LCID16().trim()%>"><BR>
          <INPUT type="text" name="E01LCID17" size="50" maxlength="35" value="<%= msg01.getE01LCID17().trim()%>"><BR>
          <INPUT type="text" name="E01LCID18" size="50" maxlength="35" value="<%= msg01.getE01LCID18().trim()%>"><BR>
          <INPUT type="text" name="E01LCID19" size="50" maxlength="35" value="<%= msg01.getE01LCID19().trim()%>"><BR>
          <INPUT type="text" name="E01LCID20" size="50" maxlength="35" value="<%= msg01.getE01LCID20().trim()%>"><BR>
          <INPUT type="text" name="E01LCID21" size="50" maxlength="35" value="<%= msg01.getE01LCID21().trim()%>"><BR>
          <INPUT type="text" name="E01LCID22" size="50" maxlength="35" value="<%= msg01.getE01LCID22().trim()%>"><BR>
          <INPUT type="text" name="E01LCID23" size="50" maxlength="35" value="<%= msg01.getE01LCID23().trim()%>"><BR>
          <INPUT type="text" name="E01LCID24" size="50" maxlength="35" value="<%= msg01.getE01LCID24().trim()%>"><BR>
          <INPUT type="text" name="E01LCID25" size="50" maxlength="35" value="<%= msg01.getE01LCID25().trim()%>"><BR>
          <INPUT type="text" name="E01LCID26" size="50" maxlength="35" value="<%= msg01.getE01LCID26().trim()%>"><BR>
          <INPUT type="text" name="E01LCID27" size="50" maxlength="35" value="<%= msg01.getE01LCID27().trim()%>"><BR>
          <INPUT type="text" name="E01LCID28" size="50" maxlength="35" value="<%= msg01.getE01LCID28().trim()%>"><BR>
          <INPUT type="text" name="E01LCID29" size="50" maxlength="35" value="<%= msg01.getE01LCID29().trim()%>"><BR>
          <INPUT type="text" name="E01LCID30" size="50" maxlength="35" value="<%= msg01.getE01LCID30().trim()%>"><BR>
          <INPUT type="text" name="E01LCID31" size="50" maxlength="35" value="<%= msg01.getE01LCID31().trim()%>"><BR>
          <INPUT type="text" name="E01LCID32" size="50" maxlength="35" value="<%= msg01.getE01LCID32().trim()%>"><BR>
          <INPUT type="text" name="E01LCID33" size="50" maxlength="35" value="<%= msg01.getE01LCID33().trim()%>"><BR>
          <INPUT type="text" name="E01LCID34" size="50" maxlength="35" value="<%= msg01.getE01LCID34().trim()%>"><BR>
          <INPUT type="text" name="E01LCID35" size="50" maxlength="35" value="<%= msg01.getE01LCID35().trim()%>"><BR>
          <INPUT type="text" name="E01LCID36" size="50" maxlength="35" value="<%= msg01.getE01LCID36().trim()%>"><BR>
          <INPUT type="text" name="E01LCID37" size="50" maxlength="35" value="<%= msg01.getE01LCID37().trim()%>"><BR>
          <INPUT type="text" name="E01LCID38" size="50" maxlength="35" value="<%= msg01.getE01LCID38().trim()%>"><BR>
          <INPUT type="text" name="E01LCID39" size="50" maxlength="35" value="<%= msg01.getE01LCID39().trim()%>"><BR>
          <INPUT type="text" name="E01LCID40" size="50" maxlength="35" value="<%= msg01.getE01LCID40().trim()%>"><BR>
        </DIV></TD>
    </TR>
  </TABLE>
<P align="center">
  <INPUT id="EIBSBTN" type="submit" name="Submit" value="Enviar">
</P>
</FORM>
<!--<%	if (!userPO.getHeader20().equals("")) {%>

<TABLE border="1">
	<TBODY>
		<TR>
			<TD>
			<%
			String s = userPO.getHeader18();
			for(int i = 0; i < s.length(); i++)
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
			for(int i = 0; i < s.length(); i++)
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
			for(int i = 0; i < s.length(); i++)
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
<%}%>-->
</BODY>
</HTML>
