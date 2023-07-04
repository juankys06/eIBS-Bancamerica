<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.0 Transitional//EN">

<%@page import="java.util.Iterator"%>
<%@page import="datapro.eibs.forex.ST_PaymentType"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<meta name="Generator"
	content="IBM WebSphere Page Designer V3.5.2 for Windows">
<meta http-equiv="Content-Style-Type" content="text/css">
<title>Forma de Pago</title>
<link href="<%=request.getContextPath()%>/pages/style.css"
	rel="stylesheet">
<script language="javascript">
//<!-- Hide from old browsers
function a(code) {
var formLength= top.opener.document.forms[0].elements.length;
for(n=0;n<formLength;n++)
{
	var elementName= top.opener.document.forms[0].elements[n].name;
	if(elementName == top.opener.fieldName) 
	{
  		top.opener.document.forms[0].elements[n].value = code;
  		break;
	}
 }
 top.close();
 }
//-->
</script>
</head>

<body>
<h4>Forma de Pago</h4>
<table align=center class=tableinfo style="width:95%">
	<tr id=trdark>
		<th style="text-align: left;">C&oacute;digo</th>
		<th style="text-align: left;">Descripci&oacute;n</th>
	</tr>
	<%
		    ST_PaymentType stPaymentType = new ST_PaymentType("es");
		    Iterator it = stPaymentType.getMap().entrySet().iterator();
		    while (it.hasNext()) {
			java.util.Map.Entry pairs = (java.util.Map.Entry) it.next();
	%>

	<tr>
		<td style="width: 30px; text-align:center; font-weight: bold;"><%=pairs.getKey()%>
		</td>
		<td><a href="javascript:a('<%=pairs.getKey()%>')"><%=pairs.getValue()%></a></td>
	</tr>
	<%
	}
	%>
</table>
</body>
</html>
