<HTML>
<HEAD>
<TITLE>Negociación de Cartas de Crédito</TITLE>
<META HTTP-EQUIV="Content-Type" CONTENT="text/html; charset=ISO-8859-1">
<LINK HREF="<%=request.getContextPath()%>/pages/style.css" REL="stylesheet">

<SCRIPT LANGUAGE="Javascript1.1" SRC="<%=request.getContextPath()%>/pages/s/javascripts/eIBS.jsp"> </SCRIPT>
<jsp:useBean id="msg01" class="datapro.eibs.beans.ELC056001Message" scope="session" />
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

<H3 ALIGN="center">Pago/Negociaci&oacute;n de Cartas de Cr&eacute;dito<IMG SRC="<%=request.getContextPath()%>/images/e_ibs.gif" ALIGN="left"
	NAME="EIBS_GIF" ALT="ELC0560_lc_negotiation_enter_maint.jsp"></H3>
<HR SIZE="4">


<FORM METHOD="post" ACTION="<%=request.getContextPath()%>/servlet/datapro.eibs.products.JSELC0560">
<INPUT TYPE="HIDDEN" NAME="opCode" VALUE="0001">
<INPUT TYPE="HIDDEN" NAME="prevPage" VALUE="ELC0560_lc_negotiation_enter.jsp">
<INPUT TYPE="HIDDEN" NAME="nextPage" VALUE="ELC0560_lc_negotiation_sp.jsp">
<DIV ALIGN="center">
<TABLE CLASS="tbenter" WIDTH="100%" BORDER="0">
	<TR>
		<TD>
		<DIV ALIGN="right">N&uacute;mero de Carta de Cr&eacute;dito:</DIV>
		</TD>
		<TD>
		<DIV><INPUT TYPE="text" NAME="E01LCRNUM" SIZE="13" MAXLENGTH="12" ONKEYPRESS="enterInteger()"> <A
			HREF="javascript:GetAccount('E01LCRNUM','','40','')"> <IMG SRC="<%=request.getContextPath()%>/images/1b.gif" ALT="Ayuda" BORDER="0"></A></DIV>
		</TD>
	</TR>
	<TR>
		<TD>
		<DIV ALIGN="right">Numero de Negociacion:</DIV>
		</TD>
		<TD>
		<DIV><INPUT TYPE="text" NAME="E01DRWNUM" SIZE="2" MAXLENGTH="2" ONKEYPRESS="return enterDecimalNum(event)"><IMG
			src="<%=request.getContextPath()%>/images/Check.gif" alt="campo obligatorio" border="0"> <A
			href="javascript:GetAccount('E01LCRNUM','','40','')"></A></DIV>
		</TD>
	</TR>
	<TR>
		<TD COLSPAN="2">
		<DIV ALIGN="center"><BR>
		<INPUT ID="EIBSBTN" TYPE="submit" NAME="Submit" VALUE="Enviar"></DIV>
		</TD>
	</TR>
</TABLE>
</DIV>

</FORM>


<%if (!userPO.getHeader20().equals(""))
{%>

<TABLE border="1">
	<TBODY>
		<TR>
			<TD><%String s = userPO.getHeader18();
			for (int i = 0; i < s.length(); i++)
			{
				if (s.charAt(i) == ':')
					out.print("<BR>");
				else if (s.charAt(i) == '<')
					out.print("{");
				else if (s.charAt(i) == '>')
					out.print("}");
				else
					out.print(s.charAt(i));
			
			}%></TD>
			<TD><%s = userPO.getHeader19();
			for (int i = 0; i < s.length(); i++)
			{
				if (s.charAt(i) == ':')
					out.print("<BR>");
				else if (s.charAt(i) == '<')
					out.print("{");
				else if (s.charAt(i) == '>')
					out.print("}");
				else
					out.print(s.charAt(i));
			
			}%></TD>
			<TD><%s = userPO.getHeader20();
			for (int i = 0; i < s.length(); i++)
			{
				if (s.charAt(i) == ':')
					out.print("<BR>");
				else if (s.charAt(i) == '<')
					out.print("{");
				else if (s.charAt(i) == '>')
					out.print("}");
				else
					out.print(s.charAt(i));
			
			}%></TD>
		</TR>
	</TBODY>
</TABLE>

<%}%>




</BODY>
</HTML>
