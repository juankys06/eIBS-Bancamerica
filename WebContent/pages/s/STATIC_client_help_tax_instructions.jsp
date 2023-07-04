
<HTML>
<HEAD>
<META HTTP-EQUIV="Content-Type" CONTENT="text/html; charset=ISO-8859-1">
<TITLE>TAX</TITLE>
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
<h4> Seleccione el código de instrucciones de impuestos</h4>
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
      <div align="center"><b>1</b></div>
    </td>
    <td width="420"><NOBR><A HREF="javascript:a('1')"> RETENCION SOBRE INTERESS 
      ISR </a></td>
  </tr>
  <tr> 
    <td width=30 height="18" > 
      <div align="center"><b>2</b></div>
    </td>
    <td width="420"><NOBR><A HREF="javascript:a('2')"> COBRE DEL IVA </a></td>
  </tr>
  <tr> 
    <td width=30 height="18" > 
      <div align="center"><b>3</b></div>
    </td>
    <td width="420"><NOBR><A HREF="javascript:a('3')"> IVA MAS ISR </a></td>
  </tr>
  <tr> 
    <td width=30 height="18" > 
      <div align="center"><b>4</b></div>
    </td>
    <td width="420"><NOBR><A HREF="javascript:a('4')"> IVA COM/ITBMS 
      </a></td>
  </tr>
  <tr> 
    <td width=30 height="18" > 
      <div align="center"><b>5</b></div>
    </td>
    <td width="420"><NOBR><A HREF="javascript:a('5')"> IVA SOLO EN INTERESES </a></td>
  </tr>
  <tr> 
    <td width=30 height="18" > 
      <div align="center"><b>6</b></div>
    </td>
    <td width="420"><NOBR><A HREF="javascript:a('6')"> DEBITO BANCARIO IDB </a></td>
  </tr>
  <tr> 
    <td width=30 height="18" > 
      <div align="center"><b>7</b></div>
    </td>
    <td width="420"><NOBR><A HREF="javascript:a('7')"> IDB MAS ISR </a></td>
  </tr>
  <tr> 
    <td width=30 height="18" > 
      <div align="center"><b>8</b></div>
    </td>
    <td width="420"><NOBR><A HREF="javascript:a('8')"> IDB MAS IVA </a></td>
  </tr>
  <tr> 
    <td width=30 height="18" > 
      <div align="center"><b>9</b></div>
    </td>
    <td width="420"><NOBR><A HREF="javascript:a('9')"> TODO TIPO DE IMPUESTO </a></td>
  </tr>

  <tr> 
    <td width=30 height="18" > 
      <div align="center"><b>F</b></div>
    </td>
    <td width="420"><NOBR><A HREF="javascript:a('F')"> FECI (Panamá)</a></td>
  </tr>

  <tr> 
    <td width=30 height="18" > 
      <div align="center"><b>G</b></div>
    </td>
    <td width="420"><NOBR><A HREF="javascript:a('G')"> FECI e ITBMS (Panamá)</a> </td>
  </tr>


  <tr> 
    <td width=30 height="18" > 
      <div align="center"><b>N</b></div>
    </td>
    <td width="420"><NOBR><A HREF="javascript:a('N')"> NO CALCULA IMPUESTOS </a></td>
  </tr>
</table>
</BODY>
</HTML>
