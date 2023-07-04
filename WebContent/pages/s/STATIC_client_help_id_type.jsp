
<%!
  String clientType="CORPORATIVE";
%>
<%
  if (request.getParameter("clientType") != null && 
      !request.getParameter("clientType").equals(""))
      clientType=request.getParameter("clientType");
%>

<HTML>
<HEAD>
<META HTTP-EQUIV="Content-Type" CONTENT="text/html; charset=ISO-8859-1">
<TITLE>Tipos de transferencias</TITLE>
<link href="<%=request.getContextPath()%>/pages/style.css" rel="stylesheet">
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
</HEAD>
<BODY>
<h4>Tipos de Identificaciones</h4> 
<table class="tableinfo" style="width:95%" ALIGN=CENTER>
  <tr id="trdark">
    <td width=32  height="18">
      <div align="left"><b>C&oacute;digo</b></div>
    </td>
    <td width="406">
      <div align="left"><b>Descripci&oacute;n</b></div>
    </td>
  </tr>
  <% if (clientType.equals("CORPORATIVE") || clientType.equals("BOTH")) { %>
  <tr> 
    <td width=32  height="18"> 
      <div align="center"><b>G</b></div>
    </td>
    <td width="406"><A HREF="javascript:a('G')"> GOBIERNO</a></td>
  </tr>
  <tr> 
    <td width=32  height="18"> 
      <div align="center"><b>J</b></div>
    </td>
    <td width="406"><A HREF="javascript:a('J')"> JURIDICO</a></td>
  </tr>
  <%} 
  if (clientType.equals("PERSONAL") 
  	|| clientType.equals("BOTH")
  	|| clientType.equals("")) { %>
  <tr> 
    <td width=32  height="18"> 
      <div align="center"><b>V</b></div>
    </td>
    <td width="406"><A HREF="javascript:a('V')"> VENEZOLANO</a></td>
  </tr>
  <tr> 
    <td width=32  height="18"> 
      <div align="center"><b>E</b></div>
    </td>
    <td width="406"><A HREF="javascript:a('E')"> EXTRANJERO</a></td>
  </tr>
  <tr> 
    <td width=32  height="18"> 
      <div align="center"><b>P</b></div>
    </td>
    <td width="406"><A HREF="javascript:a('P')"> PASAPORTE</a></td>
  </tr>
  <% } %>
</table>
<P align="left"> </P>
</BODY>
</HTML>

