
<HTML>
<HEAD>
<META HTTP-EQUIV="Content-Type" CONTENT="text/html; charset=ISO-8859-1">
<TITLE>C�lculo de Comisi�n Aunmento de Carta de Cr�dito</TITLE>
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
<h4>C�lculo de Comisi�n Aunmento de Carta de Cr�dito</h4>
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
    	Ser� calculado en base al n�mero de d�as transcurridos desde el d�a de emisi�n  <br>
        hasta la fecha de vencimiento de la Carta de Cr�dito</a>
    </td>
  </tr>
  <tr> 
    <td width=30 height="18" > 
      <div align="center"><b>N</b></div>
    </td>
    <td width="420"><a href="javascript:a('N')">
		Ser� calculado en base al n�mero de d�as transcurridos desde fecha de vencimiento de transacci�n <br>
		hasta la fecha de vencimiento de la Carta de Cr�dito</a>
	</td>
  </tr> 
</table>
<P>
</P>
</BODY>
</HTML>
