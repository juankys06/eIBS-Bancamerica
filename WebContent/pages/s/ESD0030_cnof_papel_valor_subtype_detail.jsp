<html>
<head>
<title>Codigos de Referencia</title>
<META HTTP-EQUIV="Content-Type" CONTENT="text/html; charset=ISO-8859-1">
<link Href="<%=request.getContextPath()%>/pages/style.css"
	rel="stylesheet">

</head>

<jsp:useBean id="refCodes" class="datapro.eibs.beans.ESD003002Message"
	scope="session" />

<jsp:useBean id="error" class="datapro.eibs.beans.ELEERRMessage"
	scope="session" />

<jsp:useBean id="userPO" class="datapro.eibs.beans.UserPos"
	scope="session" />
<jsp:useBean id="currUser" class="datapro.eibs.beans.ESS0030DSMessage"
	scope="session" />

<body>

<script language="Javascript1.1"
	src="<%=request.getContextPath()%>/pages/s/javascripts/eIBS.jsp"> </SCRIPT>
<script language="Javascript1.1"
	src="<%=request.getContextPath()%>/pages/s/javascripts/optMenu.jsp"> </SCRIPT>

<SCRIPT LANGUAGE="JavaScript">
builtHPopUp();

function showPopUp(opth,field,bank,ccy,field1,field2,opcod) {
   init(opth,field,bank,ccy,field1,field2,opcod);
   showPopupHelp();
   }
   
</SCRIPT>

<% 
    if ( !error.getERRNUM().equals("0")  ) {
        out.println("<SCRIPT Language=\"Javascript\">");
        error.setERRNUM("0");
        out.println("       showErrors()");
        out.println("</SCRIPT>");
    }
    
%>


<H3 align="center">C&oacute;digos de Referencia del Sistema -Subtipos de
Papel Valor<img src="<%=request.getContextPath()%>/images/e_ibs.gif"
	align="left" name="EIBS_GIF"
	ALT="cnof_papel_valor_types.jsp, ESD0030"></H3>
<hr size="4">
<form method="post"
	action="<%=request.getContextPath()%>/servlet/datapro.eibs.params.JSESD0030">
<INPUT TYPE=HIDDEN NAME="SCREEN" VALUE="600"> <INPUT TYPE=HIDDEN
	NAME="E02CNOBNK" value="<%= currUser.getE01UBK()%>">
<table class="tableinfo">
	<tr bordercolor="#FFFFFF">
		<td nowrap>
		<table cellspacing="0" cellpadding="2" width="100%" border="0">
			<tr id="trdark">
				<td nowrap width="30%">
				<div align="right">Clasificaci&oacute;n :</div>
				</td>
				<td nowrap>
				<div align="left"><input type="text" name="E02CNOCFL" size="3"
					maxlength="2" value="<%= refCodes.getE02CNOCFL().trim()%>"></div>
				</td>
			</tr>
			<tr id="trclear">
				<td nowrap width="30%" height="23">
				<div align="right">C&oacute;digo :</div>
				</td>
				<td nowrap height="23">
				<div align="left"><input type="text" name="E02CNORCD" size="6"
					maxlength="4" value="<%= refCodes.getE02CNORCD().trim()%>"> <input
					type="text" name="E02CNODSC" size="36" maxlength="35"
					value="<%= refCodes.getE02CNODSC().trim()%>"></div>
				</td>
			</tr>
			<tr id="trclear">
				<td nowrap width="30%" height="19">
				<div align="right">C�digo Insrumento :</div>
				</td>
				<td nowrap height="19">
				<div align="left"><input type="text" name="E02CNOCPC" size="5"
					maxlength="45" value="<%= refCodes.getE02CNOCPC().trim()%>"><A
					href="javascript:GetCodeCNOFC('E02CNOCPC','YJ')"><IMG
					src="<%=request.getContextPath()%>/images/1b.gif" alt=". . ."
					align="absbottom" border="0"></A></div>
				</td>
			</tr>
			<tr id="trdark">
				<td nowrap width="30%" height="19">
				<div align="right">Valor Referencial :</div>
				</td>
				<td nowrap height="19">
				<div align="left"><input type="text" name="E02CNOCHG" size="16"
					maxlength="15" value="<%= refCodes.getE02CNOCHG().trim()%>"></div>
				</td>
			</tr>
		</table>
		</td>
	</tr>
</table>

<div align="center"><input id="EIBSBTN" type=submit name="Submit"
	value="Enviar"></div>
</form>
</body>
</html>
