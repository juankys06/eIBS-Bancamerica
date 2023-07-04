
<HTML>
<HEAD>
<META HTTP-EQUIV="Content-Type" CONTENT="text/html; charset=ISO-8859-1">
<TITLE>Doble Asiento de Aceptaciones Descontadas</TITLE>
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
<h4>Doble Asiento de Aceptaciones Descontadas</h4>
<table class="tableinfo" style="width:95%" ALIGN=CENTER>
  <tr id="trdark">
    <td width=30 height="18" >
      <div align="left"><b>C&oacute;digo</b></div>
    </td>
    <td width="420">
      <div align="left"><b>Descripci&oacute;n</b></div>
    </td>
  </tr>
  <tr> 
    <td width=30 height="18" > 
      <div align="center"><b>Y</b></div>
    </td>
    <td width="420"><a href="javascript:a('Y')">
    	El pasivo del Cliente y su transacci&oacute;n compensatoria ser&aacute;n asentadas <br>
        y se crear&aacute; un registro de aceptaciones en el M&oacute;dulo Cartas de Cr&eacute;dito <br>
        adem&aacute;s de un registro de Letra Descontada en el M&oacute;dulo de Deals</a>
    </td>
  </tr>
  <tr> 
    <td width=30 height="18" > 
      <div align="center"><b>N</b></div>
    </td>
    <td width="420"><a href="javascript:a('N')">
		Solo se crear&aacute; un registro de Letra Descontada en el M&oacute;dulo de Deals <br>
		y se registrar&aacute; su transacci&oacute;n correspondiente</a>
	</td>
  </tr> 
</table>
<P>
</P>
</BODY>
</HTML>
