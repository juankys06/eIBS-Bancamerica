<!DOCTYPE HTML PUBLIC "-/W3C/DTD HTML 3.2 FINAL/EN">
<HTML>
<HEAD>
<META HTTP-EQUIV="Content-Type" CONTENT="text/html; charset=ISO-8859-1">
<META NAME="Author" CONTENT="Datapro">
<META NAME="Generator" CONTENT="NetObjects Fusion 4.0.1 for Windows">
<link Href="<%=request.getContextPath()%>/pages/style.css" rel="stylesheet">
<TITLE></TITLE>

<SCRIPT SRC="<%= request.getContextPath() %>/pages/s/javascripts/eIBS.jsp"> </SCRIPT>
</HEAD>
<jsp:useBean id= "error" class= "datapro.eibs.beans.ELEERRMessage"  scope="session" />
<BODY>
<%
    if ( !error.getERRNUM().equals("0")  ) {
        out.println("<SCRIPT Language=\"Javascript\">");
        error.setERRNUM("0");
        out.println("       showErrors()");
        out.println("</SCRIPT>");
    }

%>
<H3 align="center">Mantenimiento Clave de Operaciones: Elimina la clave de operaciones para que el cliente la vuelva a crear.
	<img src="<%=request.getContextPath()%>/images/e_ibs.gif" align="left" name="EIBS_GIF" ALT="DCIBS_user_delete_password.jsp, DCIBS"></H3>
<hr size="4">
<form method="post" action="<%=request.getContextPath()%>/servlet/com.datapro.eibs.internet.JSDeletePassword">

<BR>

<table width="90%" class="tableinfo" align="center">
  <TR id="trdark"> 
    <TD width=50% ALIGN=RIGHT>Usuario</TD>
    <TD width="50%">
    	<INPUT size="30" type="text" maxlength="10" name="USERID" value="" >
  </TR>
</TABLE>
<BR>
  <div align="center"> 
    <input id="EIBSBTN" type=submit name="Submit" value="Enviar">
  </div>


</FORM>
</BODY>
</HTML>
