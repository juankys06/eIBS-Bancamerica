
<HTML>
<HEAD>
<META HTTP-EQUIV="Content-Type" CONTENT="text/html; charset=ISO-8859-1">
<TITLE>Cálculo de Comisión Aunmento de Carta de Crédito</TITLE>
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
<h4>Cálculo de Comisión Aunmento de Carta de Crédito</h4>
<table class="tableinfo" style="width:95%" ALIGN=CENTER>
  <tr id="trdark">
    <td width=30 height="18" >
      <div align="left"><b>Código</b></div>
    </td>
    <td width="420">
      <div align="left"><b>Descripción</b></div>
    </td>
  </tr>
  <tr> 
    <td width=30 height="18" > 
      <div align="center"><b>Y</b></div>
    </td>
    <td width="420"><a href="javascript:a('Y')">
    	Será calculado en base al número de días transcurridos desde el día de emisión  <br>
        hasta la fecha de vencimiento de la Carta de Crédito</a>
    </td>
  </tr>
  <tr> 
    <td width=30 height="18" > 
      <div align="center"><b>N</b></div>
    </td>
    <td width="420"><a href="javascript:a('N')">
		Será calculado en base al número de días transcurridos desde fecha de vencimiento de transacción <br>
		hasta la fecha de vencimiento de la Carta de Crédito</a>
	</td>
  </tr> 
</table>
<P>
</P>
</BODY>
</HTML>
