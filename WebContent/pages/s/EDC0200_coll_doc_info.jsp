<HTML>
<HEAD>
<TITLE>Negociación de Cartas de Crédito</TITLE>
<META HTTP-EQUIV="Content-Type" CONTENT="text/html; charset=ISO-8859-1">
<LINK HREF="<%=request.getContextPath()%>/pages/style.css" REL="stylesheet">

<SCRIPT LANGUAGE="Javascript1.1" SRC="<%=request.getContextPath()%>/pages/s/javascripts/eIBS.jsp"> </SCRIPT>
<jsp:useBean id="msg01" class="datapro.eibs.beans.EDC020001Message" scope="session" />
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
	NAME="EIBS_GIF" ALT="coll_doc_info.jsp, EDC0200"></H3>
<HR SIZE="4">
<BR>


<FORM METHOD="post" ACTION="<%=request.getContextPath()%>/servlet/datapro.eibs.products.JSEDC0200">
<INPUT TYPE="HIDDEN" NAME="SCREEN" VALUE="5">
<INPUT TYPE="HIDDEN" NAME="E01DCIDNO" VALUE="<%=msg01.getE01DCIDNO().trim()%>">

<TABLE width="100%" border="0" class="tableinfo" id="trdark" align="center">
  <TR>
    <TD align="right" nowrap>Banco: </TD>
    <TD nowrap><INPUT name="E01DCMBNK" value="<%=msg01.getE01DCMBNK()%>" size="4" maxlength="4" readonly></TD>
    <TD colspan="2" align="right" nowrap>Numero de Cobranza: </TD>
    <TD nowrap><INPUT name="E01DCMACC" value="<%=msg01.getE01DCMACC()%>" size="15" readonly></TD>
  </TR>
  <TR>
    <TD align="right" nowrap>Nuestra Referencia: </TD>
    <TD colspan="2" nowrap><INPUT name="E01DCMORF" value="<%=msg01.getE01DCMORF()%>" size="18" maxlength="18" readonly></TD>
    <TD align="right" nowrap>Nombre del Cliente: </TD>
    <TD nowrap><INPUT name="E01CUSNA1" value="<%=msg01.getE01CUSNA1()%>" size="46" maxlength="46" readonly></TD>
  </TR>
  <TR>
    <TD align="right" nowrap>Referencia del Otro Banco: </TD>
    <TD colspan="2" nowrap><INPUT name="E01DCMTRF" value="<%=msg01.getE01DCMTRF()%>" size="18" maxlength="18" readonly></TD>
    <TD align="right" nowrap>Nombre del Beneficiario: </TD>
    <TD nowrap><INPUT name="E01DCMBN1" value="<%=msg01.getE01DCMBN1()%>" size="46" maxlength="40" readonly></TD>
  </TR>
</TABLE>

<BR>
<TABLE class="tableinfo" id="trclear" cellspacing="0" cellpadding="2" width="100%" border="0" >
				  <TR  id="trdark">
				    <TD align="right" nowrap>Fecha y Hora Recibidos: </TD>
					<TD nowrap><INPUT name="E01DCIRDM" value="<%=msg01.getE01DCIRDM()%>" size="2" maxlength="2"> / 
						<INPUT name="E01DCIRDD" value="<%=msg01.getE01DCIRDD()%>" size="2" maxlength="2"> / 
						<INPUT name="E01DCIRDY" value="<%=msg01.getE01DCIRDY()%>" size="2" maxlength="2"> 
						<A href="javascript:DatePicker(document.forms[0].E01DCIRDM,document.forms[0].E01DCIRDD,document.forms[0].E01DCIRDY)">
						<IMG src="<%=request.getContextPath()%>/images/calendar.gif" alt="help" align="middle" border="0"></A>
					    <INPUT name="E01DCITIM" value="<%=msg01.getE01DCITIM()%>" size="8" maxlength="8">	 <IMG
						src="<%=request.getContextPath()%>/images/Check.gif" alt="campo obligatorio" border="0"></TD>
					<TD align="right" nowrap>Moneda e Importe: </TD>
					<TD nowrap><INPUT name="E01DCICCY" value="<%=msg01.getE01DCICCY()%>" size="3" maxlength="3">
					<A href="javascript:GetCurrency('E01DCICCY',document.forms[0].E01DCMBNK.value)">
					 <IMG src="<%=request.getContextPath()%>/images/1b.gif" alt="Ayuda" align="bottom" border="0"></A> 
					  <INPUT name="E01DCIAMN" value="<%=msg01.getE01DCIAMN()%>" size="15" maxlength="15">
					  <IMG src="<%=request.getContextPath()%>/images/Check.gif" alt="campo obligatorio" border="0"></TD>
				  </TR>
				  <TR>
				    <TD align="right" nowrap>Tipo de Transaccion: </TD>
				    <TD nowrap>
					<SELECT name="E01DCINTY">
						<OPTION>&nbsp;</OPTION>
						<OPTION value="1" <%if(msg01.getE01DCINTY().equals("1")) out.print("selected");%>>Pago a la Vista</OPTION>
						<OPTION value="2" <%if(msg01.getE01DCINTY().equals("2")) out.print("selected");%>>Aceptacion</OPTION>
						<OPTION value="3" <%if(msg01.getE01DCINTY().equals("3")) out.print("selected");%>>Aceptacion Descontada</OPTION>
						<OPTION value="4" <%if(msg01.getE01DCINTY().equals("4")) out.print("selected");%>>Pago Diferido</OPTION>
						<OPTION value="5" <%if(msg01.getE01DCINTY().equals("5")) out.print("selected");%>>Refinanciacion</OPTION>
						<OPTION value="M" <%if(msg01.getE01DCINTY().equals("M")) out.print("selected");%>>Pago Mixto</OPTION>
						<OPTION value="N" <%if(msg01.getE01DCINTY().equals("N")) out.print("selected");%>>Negociacion</OPTION>
					</SELECT>
					<IMG src="<%=request.getContextPath()%>/images/Check.gif"
						alt="campo obligatorio" border="0"></TD>
				    <TD align="right" nowrap>Referencia del Beneficiario: </TD>
				    <TD nowrap><INPUT name="E01DCIBRE" value="<%=msg01.getE01DCIBRE()%>" size="16, 18"></TD>
				  </TR>
				  <TR id="trdark">
				    <TD align="right" nowrap>Documentos Asignados a: </TD>
				    <TD nowrap><INPUT name="E01DCIAST" value="<%=msg01.getE01DCIAST()%>" size="40" maxlength="30"></TD>
		            <TD align="right" nowrap>Estado / Situacion: </TD>
		            <TD nowrap><SELECT name="E01DCISTA">
                      <OPTION value=" "></OPTION>
                      <OPTION value="D" <% if(msg01.getE01DCISTA().equals("D")) out.print("selected");%>>Documentos con Discrepancias</OPTION>
                      <OPTION value="A" <% if(msg01.getE01DCISTA().equals("A")) out.print("selected");%>>Discrepancia Autorizada</OPTION>
                      <OPTION value="R" <% if(msg01.getE01DCISTA().equals("R")) out.print("selected");%>>Discrepancia denegada</OPTION>
                      <OPTION value="O" <% if(msg01.getE01DCISTA().equals("O")) out.print("selected");%>>Documentos en Orden</OPTION>
                    </SELECT></TD>
			  </TR>
				  <TR id="trdark">
				    <TD align="right" nowrap>Aviso sin Documentos: </TD>
				    <TD colspan="3" nowrap><SELECT name="E01DCINDF">
                      <OPTION value=" "></OPTION>
                      <OPTION value="D" <% if(msg01.getE01DCINDF().equals("D")) out.print("selected");%>>Aviso de Disrepancias sin Documentos</OPTION>
                      <OPTION value="A" <% if(msg01.getE01DCINDF().equals("A")) out.print("selected");%>>Aviso de Negociacion sin Documentos</OPTION>
                    </SELECT></TD>
			      </TR>
			  </TABLE>
		

<BR>

<TABLE width="100%" border="0" class="tableinfo">
  <TR id="trdark">
    <TD colspan="2" align="center"><B><FONT size="-1">Banco Autorizado a Pagar / Negociar</FONT> </B></TD>
    </TR>
  <TR>
    <TD width="40%" align="right">Codigo Swift</TD>
    <TD width="60%"><INPUT name="E01DCINBI" value="<%=msg01.getE01DCINBI()%>" size="12">
              <A href="javascript: GetCustomerDetailsLC('E01DCINBI','E01DCIMB1','E01DCIMB2','E01DCIMB3','E01DCIMB4','E01DCIMB5','E01DCIMB6','E01DCIMB7','C')">
              <IMG src="<%=request.getContextPath()%>/images/1b.gif" alt="Ayuda" align="bottom" border="0"></A></TD>
  </TR>
  <TR>
    <TD align="right">Nombre</TD>
    <TD><INPUT name="E01DCINB1" value="<%=msg01.getE01DCINB1()%>" maxlength="35" size="37"></TD>
  </TR>
  <TR>
    <TD align="right">Direccion</TD>
    <TD><INPUT name="E01DCINB2" value="<%=msg01.getE01DCINB2()%>" maxlength="35" size="37"></TD>
  </TR>
  <TR>
    <TD align="right">&nbsp;</TD>
    <TD><INPUT name="E01DCINB3" value="<%=msg01.getE01DCINB3()%>" maxlength="35" size="37"></TD>
  </TR>
  <TR>
    <TD align="right">&nbsp;</TD>
    <TD><INPUT name="E01DCINB4" value="<%=msg01.getE01DCINB4()%>" size="37" maxlength="25"></TD>
  </TR>
  <TR>
    <TD align="right">Estado</TD>
    <TD><INPUT name="E01DCINB5" value="<%=msg01.getE01DCINB5()%>" maxlength="2" size="2"> 
    Codigo Postal
	  <INPUT name="E01DCINB6" value="<%=msg01.getE01DCINB6()%>" size="10" maxlength="10"> 
	Pais <INPUT name="E01DCINB7" value="<%=msg01.getE01DCINB7()%>" maxlength="4" size="4"> <A href="javascript:GetCodeCNOFC('E01DCINB7','03')"><IMG
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
    <TD width="60%"><INPUT name="E01DCIRBI" value="<%=msg01.getE01DCIRBI()%>" size="12">
		<A href="javascript: GetCustomerDetailsLC('E01DCIRBI','E01DCMIB1','E01DCMIB2','E01DCMIB3','E01DCMIB4','E01DCMIB5','E01DCMIB6','E01DCMIB7','5')">
		<IMG src="<%=request.getContextPath()%>/images/1b.gif" alt="Ayuda" align="bottom" border="0"></A></TD>
  </TR>
  <TR>
    <TD align="right">Nombre</TD>
    <TD><INPUT name="E01DCIRB1" value="<%=msg01.getE01DCIRB1()%>" maxlength="35" size="37"></TD>
  </TR>
  <TR>
    <TD align="right">Direccion</TD>
    <TD><INPUT name="E01DCIRB2" value="<%=msg01.getE01DCIRB2()%>" maxlength="35" size="37"></TD>
  </TR>
  <TR>
    <TD align="right">&nbsp;</TD>
    <TD><INPUT name="E01DCIRB3" value="<%=msg01.getE01DCIRB3()%>" maxlength="35" size="37"></TD>
  </TR>
  <TR>
    <TD align="right">&nbsp;</TD>
    <TD><INPUT name="E01DCIRB4" value="<%=msg01.getE01DCIRB4()%>" size="25, 37"></TD>
  </TR>
  <TR>
    <TD align="right">Estado</TD>
    <TD><INPUT name="E01DCIRB5" value="<%=msg01.getE01DCIRB5()%>" maxlength="2" size="2"> Codigo Postal <INPUT name="E01DCIRB6" value="<%=msg01.getE01DCIRB6()%>" size="10">&nbsp;
    Pais <INPUT name="E01DCIRB7" value="<%=msg01.getE01DCIRB7()%>" size="4"> <A
			href="javascript:GetCodeCNOFC('E01DCIRB7','03')"><IMG src="<%=request.getContextPath()%>/images/1b.gif" alt="Ayuda" align="bottom"
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
          <INPUT type="text" name="E01DCID01" size="50" maxlength="35" value="<%= msg01.getE01DCID01().trim()%>"><BR>
          <INPUT type="text" name="E01DCID02" size="50" maxlength="35" value="<%= msg01.getE01DCID02().trim()%>"><BR>
          <INPUT type="text" name="E01DCID03" size="50" maxlength="35" value="<%= msg01.getE01DCID03().trim()%>"><BR>
          <INPUT type="text" name="E01DCID04" size="50" maxlength="35" value="<%= msg01.getE01DCID04().trim()%>"><BR>
          <INPUT type="text" name="E01DCID05" size="50" maxlength="35" value="<%= msg01.getE01DCID05().trim()%>"><BR>
          <INPUT type="text" name="E01DCID06" size="50" maxlength="35" value="<%= msg01.getE01DCID06().trim()%>"><BR>
          <INPUT type="text" name="E01DCID07" size="50" maxlength="35" value="<%= msg01.getE01DCID07().trim()%>"><BR>
          <INPUT type="text" name="E01DCID08" size="50" maxlength="35" value="<%= msg01.getE01DCID08().trim()%>"><BR>
          <INPUT type="text" name="E01DCID09" size="50" maxlength="35" value="<%= msg01.getE01DCID09().trim()%>"><BR>
          <INPUT type="text" name="E01DCID10" size="50" maxlength="35" value="<%= msg01.getE01DCID10().trim()%>"><BR>
          <INPUT type="text" name="E01DCID11" size="50" maxlength="35" value="<%= msg01.getE01DCID11().trim()%>"><BR>
          <INPUT type="text" name="E01DCID12" size="50" maxlength="35" value="<%= msg01.getE01DCID12().trim()%>"><BR>
          <INPUT type="text" name="E01DCID13" size="50" maxlength="35" value="<%= msg01.getE01DCID13().trim()%>"><BR>
          <INPUT type="text" name="E01DCID14" size="50" maxlength="35" value="<%= msg01.getE01DCID14().trim()%>"><BR>
          <INPUT type="text" name="E01DCID15" size="50" maxlength="35" value="<%= msg01.getE01DCID15().trim()%>"><BR>
          <INPUT type="text" name="E01DCID16" size="50" maxlength="35" value="<%= msg01.getE01DCID16().trim()%>"><BR>
          <INPUT type="text" name="E01DCID17" size="50" maxlength="35" value="<%= msg01.getE01DCID17().trim()%>"><BR>
          <INPUT type="text" name="E01DCID18" size="50" maxlength="35" value="<%= msg01.getE01DCID18().trim()%>"><BR>
          <INPUT type="text" name="E01DCID19" size="50" maxlength="35" value="<%= msg01.getE01DCID19().trim()%>"><BR>
          <INPUT type="text" name="E01DCID20" size="50" maxlength="35" value="<%= msg01.getE01DCID20().trim()%>"><BR>
          <INPUT type="text" name="E01DCID21" size="50" maxlength="35" value="<%= msg01.getE01DCID21().trim()%>"><BR>
          <INPUT type="text" name="E01DCID22" size="50" maxlength="35" value="<%= msg01.getE01DCID22().trim()%>"><BR>
          <INPUT type="text" name="E01DCID23" size="50" maxlength="35" value="<%= msg01.getE01DCID23().trim()%>"><BR>
          <INPUT type="text" name="E01DCID24" size="50" maxlength="35" value="<%= msg01.getE01DCID24().trim()%>"><BR>
          <INPUT type="text" name="E01DCID25" size="50" maxlength="35" value="<%= msg01.getE01DCID25().trim()%>"><BR>
          <INPUT type="text" name="E01DCID26" size="50" maxlength="35" value="<%= msg01.getE01DCID26().trim()%>"><BR>
          <INPUT type="text" name="E01DCID27" size="50" maxlength="35" value="<%= msg01.getE01DCID27().trim()%>"><BR>
          <INPUT type="text" name="E01DCID28" size="50" maxlength="35" value="<%= msg01.getE01DCID28().trim()%>"><BR>
          <INPUT type="text" name="E01DCID29" size="50" maxlength="35" value="<%= msg01.getE01DCID29().trim()%>"><BR>
          <INPUT type="text" name="E01DCID30" size="50" maxlength="35" value="<%= msg01.getE01DCID30().trim()%>"><BR>
          <INPUT type="text" name="E01DCID31" size="50" maxlength="35" value="<%= msg01.getE01DCID31().trim()%>"><BR>
          <INPUT type="text" name="E01DCID32" size="50" maxlength="35" value="<%= msg01.getE01DCID32().trim()%>"><BR>
          <INPUT type="text" name="E01DCID33" size="50" maxlength="35" value="<%= msg01.getE01DCID33().trim()%>"><BR>
          <INPUT type="text" name="E01DCID34" size="50" maxlength="35" value="<%= msg01.getE01DCID34().trim()%>"><BR>
          <INPUT type="text" name="E01DCID35" size="50" maxlength="35" value="<%= msg01.getE01DCID35().trim()%>"><BR>
          <INPUT type="text" name="E01DCID36" size="50" maxlength="35" value="<%= msg01.getE01DCID36().trim()%>"><BR>
          <INPUT type="text" name="E01DCID37" size="50" maxlength="35" value="<%= msg01.getE01DCID37().trim()%>"><BR>
          <INPUT type="text" name="E01DCID38" size="50" maxlength="35" value="<%= msg01.getE01DCID38().trim()%>"><BR>
          <INPUT type="text" name="E01DCID39" size="50" maxlength="35" value="<%= msg01.getE01DCID39().trim()%>"><BR>
          <INPUT type="text" name="E01DCID40" size="50" maxlength="35" value="<%= msg01.getE01DCID40().trim()%>"><BR>
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
