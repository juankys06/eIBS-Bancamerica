
<HTML>
<HEAD>
<META HTTP-EQUIV="Content-Type" CONTENT="text/html; charset=ISO-8859-1">
<TITLE>Ciclo de Pagos de Comisi�n de Cartas de Cr�dito Stand-by</TITLE>
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
<h4>Ciclo de Pagos de Comisi�n de Cartas de Cr�dito Stand-by</h4>
<table class="tableinfo" style="width:95%" ALIGN=CENTER>
  <tr id="trdark">
    <td width=30 height="18" >
      <div align="left"><b>C�digo</b></div>
    </td>
    <td width="420">
      <div align="left"><b>Descripci�n</b></div>
    </td>
  </tr>
  <tr> 
    <td width=30 height="18" > 
      <div align="center"><b>Y</b></div>
    </td>
    <td width="420"><a href="javascript:a('Y')">
    	Ser� calculado y cargado al cliente por adelantado por per�odos</a>
    </td>
  </tr>
  <tr> 
    <td width=30 height="18" > 
      <div align="center"><b>N</b></div>
    </td>
    <td width="420"><a href="javascript:a('N')">
		Ser� calculado por la duraci�n de la Carta de Cr�dito y cargado al Cliente una sola vez</a>
	</td>
  </tr> 
  <tr> 
    <td width=30 height="18" > 
      <div align="center"><b>Q</b></div>
    </td>
    <td width="420"><a href="javascript:a('Q')">
		Ser� calculado y cargado al cliente por adelantado trimestralmente</a>
	</td>
  </tr>   
</table>
<P>
</P>
</BODY>
</HTML>
