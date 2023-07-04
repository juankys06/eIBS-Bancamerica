<%@ page import = "datapro.eibs.master.*,datapro.eibs.beans.*,java.util.Iterator" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.0 Transitional//EN">
<HTML>
<HEAD>
<TITLE>Lista de Cheques a Aprobar</TITLE>
<META HTTP-EQUIV="Content-Type" CONTENT="text/html; charset=ISO-8859-1">
<META name="GENERATOR"
	content="IBM WebSphere Page Designer V3.5.2 for Windows">
<META http-equiv="Content-Style-Type" content="text/css">
<LINK href="<%=request.getContextPath()%>/pages/style.css"
	rel="stylesheet">
<jsp:useBean id="msgList" class="java.util.ArrayList" scope="session" />
<jsp:useBean id="errorList" class="java.util.ArrayList" scope="session" />
<jsp:useBean id="recordList" class="java.util.ArrayList" scope="session" />
<jsp:useBean id="error" class="datapro.eibs.beans.ELEERRMessage"
	scope="session" />
<jsp:useBean id="userPO" class="datapro.eibs.beans.UserPos"
	scope="session" />

<SCRIPT language="Javascript1.1"
	src="<%=request.getContextPath()%>/pages/s/javascripts/eIBS.jsp"></SCRIPT>

<SCRIPT language="javascript">
 function goAction(op) {
	if(op == "A"){
		document.forms[0].SCREEN.value = 11;
		document.forms[0].submit();		 
	}else if(op == "D"){
		document.forms[0].SCREEN.value = 11;
		document.forms[0].action.value="D";
		document.forms[0].submit();
	}
 }
 
 <%
 try
{
	if(session.getAttribute("CONFIRM").equals("CONFIRM")){
	%>
		CenterWindow('<%=request.getContextPath()%>/pages/s/EOF0115_of_chk_apr_confirm.jsp',700,300,2);
	<%}
}
catch (Exception e){}
%>
</SCRIPT>
</HEAD>
<BODY>
<%if(msgList.size() == 0) { /////////////////////////////////////// IF NO RESULTS /////////////////////////////////////// %> 	
<table class="tbenter" width=101% height=100%>
  <tr> 
    <td> 
      <div align="center"> <font size="3"><b> No hay resultados que correspondan 
        a su criterio de búsqueda </b></font> </div>
    </td>
  </tr>
</table>
<div align="center"></div>
<%}else{  /////////////////////////////////////// ELSE IF RESULTS /////////////////////////////////////// %>



<FORM Method="post" Action="<%=request.getContextPath()%>/servlet/datapro.eibs.products.JSEOF0115A">
<INPUT TYPE=HIDDEN NAME="SCREEN" VALUE="12">
<INPUT TYPE=HIDDEN NAME="action" VALUE="A">
<INPUT TYPE=HIDDEN NAME="reason" VALUE="">
<INPUT TYPE=HIDDEN NAME="totalRow" VALUE="0">
<INPUT TYPE=HIDDEN NAME="RD" VALUE="10">
<H3 align="center">Aprobación de Cheques de Gerencia <IMG
	src="<%=request.getContextPath()%>/images/e_ibs.gif" align="left"
	name="EIBS_GIF" ALT="EOF0115_of_chk_apr_list.jsp"></H3>
<HR size="4">

<TABLE class="tbenter" width="100%">
	<TR>
		<TD width="25%" class=TDBKG>
		<DIV align="center"><A name="linkApproval"
			href="javascript:goAction('A')"><B>Aprobar</B></A></DIV>
		</TD>
		<TD width="25%" class=TDBKG>
		<DIV align="center"><A href="javascript:deleteRecord()"><B>Eliminar</B></A></DIV>
		</TD>
		<TD width="25%" class=TDBKG>
		<DIV align="center"><A
			href="<%=request.getContextPath()%>/pages/background.jsp"><B>Salir</B></A></DIV>
		</TD>
	</TR>
</TABLE>

<TABLE id="mainTable" class="tableinfo">
	<TR>
		<TD NOWRAP valign="top">
		<TABLE id="headTable" width="100%">
			<TR id="trdark">
				<TH ALIGN=CENTER nowrap width="1%"><input type="checkbox" name="checkall" onclick="javascript:checkUncheckAll()"></TH>
				<TH ALIGN=CENTER nowrap>REFERENCIA</TH>
				<TH ALIGN=CENTER nowrap>MONEDA</TH>
				<TH ALIGN=CENTER nowrap>SUCURSAL</TH>
				<TH ALIGN=CENTER nowrap>MONTO</TH>
				<TH ALIGN=CENTER nowrap>ESTADO</TH>
				<TH ALIGN=CENTER nowrap>EMITIDO</TH>
				<TH align="CENTER" nowrap>BENEFICIARIO</TH>
			</TR>
			<%
String strPurpose = "I";
String showRef = "";
int k = 0;
boolean firstTime = true;
String chk = "checked";
for(int i = 0; i < msgList.size(); i++)
{
	if (i != 0)
	{
		chk = "";
	}
	ETL051001Message msgPart = (ETL051001Message) msgList.get(i);

	showRef = "showOffChkApproval('" + msgPart.getE01OFMNCH() + "', '" + msgPart.getE01OFMCCY() + "', '" + strPurpose + "')";%>
			<TR>
				<TD NOWRAP><INPUT type="checkbox" name="RECNUM<%=i%>"value="<%=msgPart.getE01OFMNCH() + "_" + msgPart.getE01OFMCCY()%>"></TD>
				<TD NOWRAP ALIGN="CENTER"><A href="javascript:<%=showRef%>"><%=Util.formatCell(msgPart.getE01OFMNCH())%></A></TD>
				<TD NOWRAP ALIGN="CENTER"><A href="javascript:<%=showRef%>"><%=Util.formatCell(msgPart.getE01OFMCCY())%></A></TD>
				<TD NOWRAP ALIGN="CENTER"><A href="javascript:<%=showRef%>"><%=Util.formatCell(msgPart.getE01OFMBRN())%></A></TD>
				<TD NOWRAP ALIGN="CENTER"><A href="javascript:<%=showRef%>"><%=Util.formatCell(msgPart.getE01OFMMCH())%></A></TD>
				<TD NOWRAP ALIGN="CENTER"><A href="javascript:<%=showRef%>"><%=Util.formatCell(msgPart.getE01OFMSTS())%></A></TD>
				<TD NOWRAP ALIGN="CENTER"><A href="javascript:<%=showRef%>"><%=Util.formatDate(msgPart.getE01OFMID1(), msgPart.getE01OFMID2(), msgPart.getE01OFMID3())%></A></TD>
				<TD NOWRAP ALIGN="CENTER"><A href="javascript:<%=showRef%>"><%=Util.formatCell(msgPart.getE01OFMBNF())%></A></TD>
				<TD nowrap align="CENTER"></TD>
			</TR>
			<%}%>
		</TABLE>
		</TD>
	</TR>
</TABLE>
<Input type="hidden" name="total_records" value="<%=msgList.size()%>">
<input type="hidden" name="ACCNUM" value="">
</FORM>

<SCRIPT language="javascript">
	function deleteRecord()
	{
		var count = 0;
		var accvalue;
		<%for(int i = 0; i < msgList.size(); i++){%>
			if (document.forms[0].RECNUM<%=i%>.checked == 1)
			{
				//alert('found one <%=i%>');
				count = count + 1;
				accvalue=document.forms[0].RECNUM<%=i%>.value;
			}
		<%}%>
		if (count == 1)
		{
			document.forms[0].ACCNUM.value = accvalue;
			enterReason('D');
		}
		else
			alert("Por favor, seleccione solo un elemento");
	}

</SCRIPT>

<SCRIPT language="javascript">
	function checkUncheckAll()
	{
		<%for(int i = 0; i < msgList.size(); i++){%>
		document.forms[0].RECNUM<%=i%>.checked = document.forms[0].checkall.checked;<%}%>
	}
</SCRIPT>
<%} /////////////////////////////////////// END ELSE IF RESULTS /////////////////////////////////////// %>
</BODY>
</HTML>
