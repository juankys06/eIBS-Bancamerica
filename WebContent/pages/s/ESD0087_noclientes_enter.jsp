<jsp:useBean id="error" class="datapro.eibs.beans.ELEERRMessage"
	scope="session" />
<jsp:useBean id="currUser" class="datapro.eibs.beans.ESS0030DSMessage"
	scope="session" />
	
<html>
<head>
<title>Nuevo Cliente</title>
<META HTTP-EQUIV="Content-Type" CONTENT="text/html; charset=ISO-8859-1">
<link href="<%=request.getContextPath()%>/pages/style.css"
	rel="stylesheet">
<script language="Javascript1.1"
	src="<%=request.getContextPath()%>/pages/s/javascripts/eIBS.jsp"> </SCRIPT>


<SCRIPT
	SRC="<%=request.getContextPath()%>/pages/s/javascripts/optMenu.jsp"> </SCRIPT>

</head>

<body bgcolor="#FFFFFF">

<h3 align="center">No-Cliente<img
	src="<%=request.getContextPath()%>/images/e_ibs.gif" align="left"
	name="EIBS_GIF" alt="client_both_enter_new, ESD0080"></h3>
<hr size="4">
<form name="forma" method="post"
	action="<%=request.getContextPath()%>/servlet/datapro.eibs.client.JSESD0087">
<p><INPUT TYPE=HIDDEN NAME="SCREEN" VALUE="0001"></p>
<h4 align="center">Ingrese el N&uacute;mero de Cliente o de
Identificaci&oacute;n y seleccione una opci&oacute;n.</h4>
<p>&nbsp;</p>

<jsp:include page="ESD0080_client_enter_id_template.jsp" flush="true">				
</jsp:include>
		
<p align="center"><input id="EIBSBTN" type=submit name="Submit"
	value="Enviar"></p>
</form>

<SCRIPT Language="Javascript">;
<%if (!error.getERRNUM().equals("0")) {
		error.setERRNUM("0");	
%>
            showErrors();
<% } %>

</SCRIPT>
</body>
</html>






