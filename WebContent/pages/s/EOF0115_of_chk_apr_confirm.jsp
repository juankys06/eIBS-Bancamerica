<%@ page import = "datapro.eibs.master.*,datapro.eibs.beans.*,java.util.Iterator" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.0 Transitional//EN">
<HTML>
<HEAD>
<TITLE>Confirmacion de Cheques Aprobados</TITLE>
<META HTTP-EQUIV="Content-Type" CONTENT="text/html; charset=ISO-8859-1">
<META name="GENERATOR"
	content="IBM WebSphere Page Designer V3.5.2 for Windows">
<META http-equiv="Content-Style-Type" content="text/css">
<LINK href="<%=request.getContextPath()%>/pages/style.css"
	rel="stylesheet">

<jsp:useBean id="oldMsgList" class="java.util.ArrayList" scope="session" />
<jsp:useBean id="recordList" class="java.util.ArrayList" scope="session" />
<jsp:useBean id="errorList" class="java.util.ArrayList" scope="session" />
<jsp:useBean id="userPO" class="datapro.eibs.beans.UserPos" scope="session" />

<SCRIPT language="Javascript1.1" src="<%=request.getContextPath()%>/pages/s/javascripts/eIBS.jsp"></SCRIPT>

</HEAD>

<BODY>
<H3 align="center">Confirmacion de Cheques Aprobados
<IMG src="<%=request.getContextPath()%>/images/e_ibs.gif" align="left" name="EIBS_GIF" ALT="EOF0115_of_chk_apr_confirm.jsp"></H3>
<HR size="4">
<BR>
<TABLE id="mainTable" class="tableinfo">
	<TR>
		<TD NOWRAP valign="top">
		<TABLE id="headTable" width="100%">
			<TR id="trdark">
			  <TH ALIGN=CENTER nowrap>STATUS</TH>
				<TH ALIGN=CENTER nowrap>REFERENCIA</TH>
				<TH ALIGN=CENTER nowrap>MONEDA</TH>
				<TH ALIGN=CENTER nowrap>SUCURSAL</TH>
				<TH ALIGN=CENTER nowrap>MONTO</TH>
				<TH ALIGN=CENTER nowrap>ESTADO</TH>
				<TH ALIGN=CENTER nowrap>EMITIDO</TH>
				<TH align="CENTER" nowrap>BENEFICIARIO</TH>
			</TR>
			<%
for( int i = 0; i < recordList.size(); i++)
{
	EOF011502Message record = (EOF011502Message) recordList.get(i);
	datapro.eibs.beans.ELEERRMessage error = (datapro.eibs.beans.ELEERRMessage) errorList.get(i);
	
	for (int ii = 0; ii < oldMsgList.size(); ii++)
	{
	ETL051001Message msgPart = (ETL051001Message) oldMsgList.get(ii);
	
	if ( record.getE02OFMCKN().equals( msgPart.getE01OFMNCH() ) && record.getE02OFMCCY().equals( msgPart.getE01OFMCCY( ) ) ) {
	
	//if(true){
%>
			<TR>
			  <TD NOWRAP ALIGN="CENTER">
<%			  if ( error.getERRNUM().equals("0")  )  { %> <FONT color="green"><B>APROBADO</B></FONT></TD>
<%			} else {%>
		<FONT color="red"><B>ERROR</B></FONT>
		<a href="javascript:CenterWindow('<%=request.getContextPath()%>/pages/s/error_list_viewer.jsp?ERRORNUM=<%=i%>',400,200,2);"><img src="<%=request.getContextPath()%>/images/1b.gif" alt="ayuda" border="0"  ></a>
		
	<%}%>			  
			  
				<TD NOWRAP ALIGN="CENTER"><%=Util.formatCell(msgPart.getE01OFMNCH())%></TD>
				<TD NOWRAP ALIGN="CENTER"><%=Util.formatCell(msgPart.getE01OFMCCY())%></TD>
				<TD NOWRAP ALIGN="CENTER"><%=Util.formatCell(msgPart.getE01OFMBRN())%></TD>
				<TD NOWRAP ALIGN="CENTER"><%=Util.formatCell(msgPart.getE01OFMMCH())%></TD>
				<TD NOWRAP ALIGN="CENTER"><%=Util.formatCell(msgPart.getE01OFMSTS())%></TD>
				<TD NOWRAP ALIGN="CENTER"><%=Util.formatDate(msgPart.getE01OFMID1(), msgPart.getE01OFMID2(), msgPart.getE01OFMID3())%></TD>
				<TD NOWRAP ALIGN="CENTER"><%=Util.formatCell(msgPart.getE01OFMBNF())%></TD>
				<TD nowrap align="CENTER"></TD>
			</TR>
			<%} } }%>
		</TABLE>
	  </TD>
	</TR>
</TABLE>
<!--<h5>
<%
out.print("oldMsgList size = " + oldMsgList.size() + "<BR>");
for (int i = 0; i < oldMsgList.size(); i++) {
		out.print( "oldMsgList " + i + ": " + oldMsgList.get(i).toString());
		}
		out.print("<br><br><br>errorList size = " + errorList.size() + "<BR>");
		for (int i = 0; i < errorList.size(); i++) {
		out.print( "errorList " + i + ": " + errorList.get(i).toString() );
		}
		out.print("<br><br><br>recordList size = " + recordList.size() + "<BR>");
		for (int i = 0; i < recordList.size(); i++) {
		out.print( "recordList " + i + ": " + recordList.get(i).toString() );
		}
%>
</h5>-->
</BODY>
</HTML>
