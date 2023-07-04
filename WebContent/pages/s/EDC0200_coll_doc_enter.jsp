<HTML>
<HEAD>
<TITLE>Control de Documentos Recibidos</TITLE>
<META HTTP-EQUIV="Content-Type" CONTENT="text/html; charset=ISO-8859-1">
<LINK HREF="<%=request.getContextPath()%>/pages/style.css" REL="stylesheet">

<SCRIPT LANGUAGE="Javascript1.1" SRC="<%=request.getContextPath()%>/pages/s/javascripts/eIBS.jsp"> </SCRIPT>
<jsp:useBean id="error" class="datapro.eibs.beans.ELEERRMessage" scope="session" />


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

<H3 ALIGN="center">Control de Documentos Recibidos<IMG SRC="<%=request.getContextPath()%>/images/e_ibs.gif" ALIGN="left"
	NAME="EIBS_GIF" ALT="coll_doc_enter.jsp, EDCC0200"></H3>
<HR SIZE="4">


<FORM METHOD="post" ACTION="<%=request.getContextPath()%>/servlet/datapro.eibs.products.JSEDC0200">
<INPUT TYPE="HIDDEN" NAME="SCREEN" VALUE="2">
<DIV ALIGN="center">
<TABLE CLASS="tbenter" WIDTH="100%" BORDER="0">
	<TR>
		<TD width="50%" align="right">N&uacute;mero de Cobranza:		</TD>
		<TD width="50%">
		   <DIV><INPUT TYPE="text" NAME="E01DCMACC" SIZE="13" MAXLENGTH="12" ONKEYPRESS="enterInteger()">
		    <A HREF="javascript:GetAccount('E01DCMACC','','51','')"> <IMG SRC="<%=request.getContextPath()%>/images/1b.gif" ALT="Ayuda" BORDER="0"></A>
		   </DIV>
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

</BODY>
</HTML>
