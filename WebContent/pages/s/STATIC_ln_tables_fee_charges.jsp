
<HTML>
<HEAD>
<META HTTP-EQUIV="Content-Type" CONTENT="text/html; charset=ISO-8859-1">
<TITLE>Factor para Cargos</TITLE>
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
<h4>Factor para Cargos</h4>
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
      <div align="center"><b>%</b></div>
    </td>
    <td width="420"><a href="javascript:a('%')">Porcentaje</a></td>
  </tr>
  <tr> 
    <td width=30 height="18" > 
      <div align="center"><b>F</b></div>
    </td>
    <td width="420"><a href="javascript:a('F')">Monto Fijo</a></td>
  </tr>
  <tr> 
    <td width=30 height="18" > 
      <div align="center"><b>1</b></div>
    </td>
    <td width="420"><a href="javascript:a('1')">Porcentaje de Tasa y Término </a></td>
  </tr>
  <tr> 
    <td width=30 height="18" > 
      <div align="center"><b>2</b></div>
    </td>
    <td width="420"><a href="javascript:a('2')">Porcentaje de Tasa y Término Base(Principal + Interes + Mora) </a></td>
  </tr>
  <tr> 
    <td width=30 height="18" > 
      <div align="center"><b>3</b></div>
    </td>
    <td width="420"><a href="javascript:a('3')">Porcentaje del Monto Original </a></td>
  </tr>
  <tr> 
    <td width=30 height="18" > 
      <div align="center"><b>4</b></div>
    </td>
    <td width="420"><a href="javascript:a('4')">Sobretasa de Impuesto sobre Saldo de Principal </a></td>
  </tr>
  <tr> 
    <td width=30 height="18" > 
      <div align="center"><b>5</b></div>
    </td>
    <td width="420"><a href="javascript:a('5')">Formula de Impuesto (Mes y Fracción, Maximo 12) </a></td>
  </tr>
  <tr> 
    <td width=30 height="18" > 
      <div align="center"><b>6</b></div>
    </td>
    <td width="420"><a href="javascript:a('6')">Fogape (Chile) </a></td>
  </tr>
  <tr> 
    <td width=30 height="18" > 
      <div align="center"><b>7</b></div>
    </td>
    <td width="420"><a href="javascript:a('7')">Penalidad Prepago </a></td>
  </tr>
  <tr> 
    <td width=30 height="18" > 
      <div align="center"><b>8</b></div>
    </td>
    <td width="420"><a href="javascript:a('8')">Timbre Fiscal (Venezuela) </a></td>
  </tr>
  <tr> 
    <td width=30 height="18" > 
      <div align="center"><b>9</b></div>
    </td>
    <td width="420"><a href="javascript:a('9')">Seguro Contra Riesgos (Guatemala)</a></td>
  </tr>

  <tr> 
    <td width=30 height="18" > 
      <div align="center"><b>A</b></div>
    </td>
    <td width="420"><a href="javascript:a('A')">Timbre Fiscal (Panamá)</a></td>
  </tr>
  <tr> 
    <td width=30 height="18" > 
      <div align="center"><b>B</b></div>
    </td>
    <td width="420"><a href="javascript:a('B')">Servicio de Descuento (Panamá)</a></td>
  </tr>
  <tr> 
    <td width=30 height="18" > 
      <div align="center"><b>N</b></div>
    </td>
    <td width="420"><a href="javascript:a('N')">Notaria (Panamá)</a></td>
  </tr>

  <tr> 
    <td width=30 height="18" > 
      <div align="center"><b>W</b></div>
    </td>
    <td width="420"><a href="javascript:a('W')">Comisión de Manejo Variable (Panamá)</a></td>
  </tr>


  <tr> 
    <td width=30 height="18" > 
      <div align="center"><b>X</b></div>
    </td>
    <td width="420"><a href="javascript:a('X')">Provisión Seg.Vida (Panamá)</a></td>
  </tr>

  
</table>
<P>
</P>
</BODY>
</HTML>
