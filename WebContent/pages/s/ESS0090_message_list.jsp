<HTML>
<HEAD>
<TITLE>
Lista de Clientes
</TITLE>
<META HTTP-EQUIV="Content-Type" CONTENT="text/html; charset=ISO-8859-1">
<link href="<%=request.getContextPath()%>/pages/style.css" rel="stylesheet">
</HEAD>

<jsp:useBean id= "msgList" class= "datapro.eibs.beans.JBList"  scope="session" />

<script language="Javascript1.1" src="<%=request.getContextPath()%>/pages/s/javascripts/eIBS.jsp"> </SCRIPT>

<script language="javascript">
  function setMsg(row, app, msg) {

		document.forms[0].appCode.value = app;
		document.forms[0].msgCode.value = msg;
		parent.msgText.window.location.href="<%=request.getContextPath()%>/pages/s/ESS0090_message_text.jsp?row=" + row;

  }
</script>

<BODY >

<FORM Method="post" Action="<%=request.getContextPath()%>/servlet/datapro.eibs.products.JSESS0090" target="main">
<INPUT TYPE=HIDDEN NAME="SCREEN" VALUE="2">
<INPUT TYPE=HIDDEN NAME="opt" VALUE="1">
<INPUT TYPE=HIDDEN NAME="appCode" VALUE="">
<INPUT TYPE=HIDDEN NAME="msgCode" VALUE="">
<INPUT TYPE=HIDDEN NAME="reason" VALUE="">

<%
	if ( msgList.getNoResult() ) {
 %>
   		<TABLE class="tbenter" width=100% height=100%>
   		<TR>
      <TD> 
        <div align="center">
        		<font size="3"><b>
        				No hay resultados que correspondan a su criterio de búsqueda 
        		</b></font>
        	</div>
      </TD></TR>
   		</TABLE>
<%   		
	}
	else {
%>

  <TABLE class="tableinfo">
    <TR id="trdark"> 
      <TH ALIGN=CENTER rowspan="2"></TH>
      <TH ALIGN=CENTER colspan="2">Referido a</TH>
      <TH ALIGN=CENTER colspan="3">Originado</TH>
    </TR>
    <TR id="trdark"> 
      <TH ALIGN=CENTER>C&oacute;digo</TH>
      <TH ALIGN=CENTER>Cuenta</TH>
      <TH ALIGN=CENTER>Por</TH>
      <TH ALIGN=CENTER>Fecha</TH>
      <TH ALIGN=CENTER>Hora</TH>
    </TR>
    <%
                msgList.initRow();
                while (msgList.getNextRow()) {
                    		out.println(msgList.getRecord());
                }
    %> 
  </TABLE>
  <script language="javascript">
		// document.forms[0].ACCNUM[0].checked = true;
      try {
				document.forms[0].ACCNUM[0].click();
		}
		catch (exception) {
				document.forms[0].ACCNUM.click();
		}
  </script>

<%
  }
%>

</FORM>

</BODY>
</HTML>
