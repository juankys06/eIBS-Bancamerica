<%@ page import = "datapro.eibs.master.*,datapro.eibs.beans.*,java.util.Iterator" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.0 Transitional//EN">
<HTML>
<HEAD>
<TITLE>Lista de Cuentas a Aprobar</TITLE>
<META HTTP-EQUIV="Content-Type" CONTENT="text/html; charset=ISO-8859-1">
<META name="GENERATOR" content="IBM WebSphere Page Designer V3.5.2 for Windows">
<META http-equiv="Content-Style-Type" content="text/css">
<LINK href="<%=request.getContextPath()%>/pages/style.css" rel="stylesheet">

<jsp:useBean id="jbList" class="datapro.eibs.beans.JBObjList" scope="session" />
<jsp:useBean id="error" class="datapro.eibs.beans.ELEERRMessage" scope="session" />
<jsp:useBean id="userPO" class="datapro.eibs.beans.UserPos" scope="session" />

<SCRIPT language="Javascript1.1" src="<%=request.getContextPath()%>/pages/s/javascripts/eIBS.jsp"> </SCRIPT>

<SCRIPT language="javascript">
 function goAction(op)
 {
	if(op == "N") 		{ document.forms[0].SCREEN.value = 3; }
	else if(op == "M")	{ document.forms[0].SCREEN.value = 4; }
	document.forms[0].submit();
 }

</SCRIPT>
</HEAD>
<BODY>

<%if (!error.getERRNUM().equals("0"))
{
	out.println("<SCRIPT Language=\"Javascript\">");
	out.println("       showErrors()");
	out.println("</SCRIPT>");
} 
%>

<FORM name="letterOfCreditForm" method="post" action="<%=request.getContextPath()%>/servlet/datapro.eibs.products.JSEDC0200">
<INPUT TYPE=HIDDEN NAME="SCREEN" VALUE="2">
<INPUT TYPE=HIDDEN NAME="totalRow" VALUE="0">
<H3 align="center">Control de Documentos Recibidos<IMG src="<%=request.getContextPath()%>/images/e_ibs.gif" align="left"
	name="EIBS_GIF" ALT="coll_doc_list.jsp,EDC0200"></H3>
<HR size="4">

<TABLE class="tbenter">
	<TR>
		<TD class=TDBKG>
		<DIV align="center"><A name="New" href="javascript:goAction('N')"><B>Nuevo</B></A></DIV>
		</TD>
		<TD class=TDBKG>
		<DIV align="center"><A name="Maint" href="javascript:goAction('M')"><B>Maintenimiento</B></A></DIV>
		</TD>
		<TD class=TDBKG>
		<DIV align="center"><A href="<%=request.getContextPath()%>/pages/background.jsp"><B>Salir</B></A></DIV>
		</TD>
	</TR>
</TABLE>

<TABLE id="mainTable" class="tableinfo">
	<TR>
		<TD NOWRAP valign="top">
		<TABLE id="headTable" width="100%">
			<TR id="trdark">
				<TH ALIGN=CENTER nowrap></TH>
				<TH ALIGN=CENTER nowrap>NRO</TH>
				<TH ALIGN=CENTER nowrap>Fecha Recibido </TH>
				<TH ALIGN=CENTER nowrap>MDA</TH>
				<TH ALIGN=CENTER nowrap>Monto</TH>
				<TH ALIGN=CENTER nowrap>Status</TH>
				<TH ALIGN=CENTER nowrap>Benefciario</TH>
			</TR>
			<%int k = 0;
jbList.initRow();
boolean firstTime = true;
String chk = "";
while (jbList.getNextRow())
{
	EDC020001Message msgPart = (EDC020001Message) jbList.getRecord();
	if (firstTime)
	{
		firstTime = false;
		%><INPUT TYPE="hidden" NAME="E01DCIDNO" value="<%=msgPart.getE01DCIDNO()%>"> <%
		chk = "checked";
	}
	else
	{
		chk = "";
	}
	%>
			<TR>
				<TD NOWRAP><INPUT type="radio" name="ACCNUM_TEMP" value="<%=msgPart.getE01DCIDNO()%>"
					onclick="document.forms[0].E01DCIDNO.value = this.value;" <%=chk%>></TD>
				<TD NOWRAP ALIGN="LEFT"><A href="javascript:document.forms[0].E01DCIDNO.value = '<%=msgPart.getE01DCIDNO()%>'; goAction('M');"><%=msgPart.getE01DCIDNO()%></A></TD>
				<TD NOWRAP ALIGN="CENTER"><A href="javascript:document.forms[0].E01DCIDNO.value = '<%=msgPart.getE01DCIDNO()%>'; goAction('M');">
				<%=msgPart.getE01DCIRDM()%> / <%=msgPart.getE01DCIRDD()%> /
				<%=(msgPart.getE01DCIRDY().length() == 1 ? "0":"") + msgPart.getE01DCIRDY()%></A></TD>
				<TD NOWRAP ALIGN="CENTER"><A href="javascript:document.forms[0].E01DCIDNO.value = '<%=msgPart.getE01DCIDNO()%>'; goAction('M');"><%=msgPart.getE01DCICCY()%></A></TD>
				<TD NOWRAP ALIGN="CENTER"><A href="javascript:document.forms[0].E01DCIDNO.value = '<%=msgPart.getE01DCIDNO()%>'; goAction('M');"><%=msgPart.getE01DCIAMN()%></A></TD>
				<TD NOWRAP ALIGN="CENTER"><A href="javascript:document.forms[0].E01DCIDNO.value = '<%=msgPart.getE01DCIDNO()%>'; goAction('M');"><%=msgPart.getE01STATUS()%></A></TD>
				<TD NOWRAP ALIGN="CENTER"><A href="javascript:document.forms[0].E01DCIDNO.value = '<%=msgPart.getE01DCIDNO()%>'; goAction('M');"><%=msgPart.getE01DCMBN1()%></A></TD>
			</TR>
			<%k++;
}%>
		</TABLE>		</TD>
	</TR>
</TABLE>

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
