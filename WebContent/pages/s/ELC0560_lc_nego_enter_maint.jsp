<HTML>
<HEAD>
<TITLE>Negociación de Cartas de Crédito</TITLE>
<META HTTP-EQUIV="Content-Type" CONTENT="text/html; charset=ISO-8859-1">
<LINK HREF="<%=request.getContextPath()%>/pages/style.css" REL="stylesheet">

<SCRIPT LANGUAGE="Javascript1.1" SRC="<%=request.getContextPath()%>/pages/s/javascripts/eIBS.jsp"> </SCRIPT>
<jsp:useBean id="error" class="datapro.eibs.beans.ELEERRMessage" scope="session" />
<jsp:useBean id="userPO" class="datapro.eibs.beans.UserPos" scope="session" />


<%if (!error.getERRNUM().equals("0")){
	error.setERRNUM("0");
	out.println("<SCRIPT Language=\"Javascript\">");
	out.println("       showErrors()");
	out.println("</SCRIPT>");
}
%>
<SCRIPT language="javascript">
 function HelpWindow(fld1,fld2){
 	fieldAux1=fld1;
	fieldAux2=fld2;
	var page= webapp +'/servlet/datapro.eibs.products.JSELC0100?SCREEN=6';
	CenterWindow(page,500,400,3);
 }
</SCRIPT >
 
</HEAD>

<BODY BGCOLOR="#FFFFFF">

<H3 ALIGN="center">Pago/Negociaci&oacute;n de Cartas de Cr&eacute;dito
	<IMG SRC="<%=request.getContextPath()%>/images/e_ibs.gif" ALIGN="left" NAME="EIBS_GIF" ALT="lc_nego_enter_maint.jsp, ELC0560"></H3>
<HR SIZE="4">


<FORM METHOD="post" ACTION="<%=request.getContextPath()%>/servlet/datapro.eibs.products.JSELC0560">
<INPUT TYPE="HIDDEN" NAME="SCREEN" VALUE="0002">
<INPUT TYPE="HIDDEN" NAME="H01OPECOD" VALUE="0002">
<INPUT TYPE="HIDDEN" NAME="prevPage" VALUE="ELC0560_lc_negotiation_enter_maint.jsp">
<DIV ALIGN="center">
<TABLE CLASS="tbenter" WIDTH="100%" BORDER="0">
	<TR>
		<TD align="right" nowrap>N&uacute;mero de Carta de Cr&eacute;dito:		</TD>
		<TD nowrap><INPUT TYPE="text" NAME="E01LCRNUM" SIZE="13" MAXLENGTH="12" ONKEYPRESS="enterInteger()">
		<A HREF="javascript:HelpWindow('E01LCRNUM','E01DRWNUM')">
		<IMG SRC="<%=request.getContextPath()%>/images/1b.gif" ALT="Ayuda" BORDER="0"></A>
		</TD>
	</TR>
	<TR>
		<TD align="right" nowrap>
		Numero de Negociacion:		</TD>
		<TD nowrap><INPUT TYPE="text" NAME="E01DRWNUM" SIZE="2" MAXLENGTH="2" ONKEYPRESS="return enterDecimalNum(event)"><IMG
			src="<%=request.getContextPath()%>/images/Check.gif" alt="campo obligatorio" border="0"> <A
			href="javascript:HelpWindow('E01LCRNUM','E01DRWNUM')"></A>
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
