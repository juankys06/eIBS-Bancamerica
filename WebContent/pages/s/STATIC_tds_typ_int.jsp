
<HTML>
<HEAD>
<META HTTP-EQUIV="Content-Type" CONTENT="text/html; charset=ISO-8859-1">
<TITLE>Formulas de Calculo de Interes</TITLE>
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
<h4>Formulas de Calculo de Interes</h4>

<h4>Formula Lineal</h4>
<table class="tableinfo" style="width:95%" ALIGN=CENTER>
  <tr id="trdark">
    <td width=30 height="18" >
      <div align="left"><b>C&oacute;digo</b></div>
    </td>
    <td width="420">
      <div align="left"><b>Descripcion</b></div>
    </td>	
  </tr>
  <tr> 
    <td width=30 height="18" > 
      <div align="center"><b>S</b></div>
    </td>
    <td width="420"><a href="javascript:a('S')">Al Vencimiento Calendario</a></td>	
  </tr>
  <tr> 
    <td width=30 height="18" > 
      <div align="center"><b>M</b></div>
    </td>
    <td width="420"><a href="javascript:a('M')">Al Vencimiento Comerciales</a></td>    
  </tr>
  <tr> 
    <td width=30 height="18" > 
      <div align="center"><b>P</b></div>
    </td>
    <td width="420"><A HREF="javascript:a('P')">Prepagados Calendario</a></td>     
  </tr>
  <tr> 
    <td width=30 height="18" > 
      <div align="center"><b>A</b></div>
    </td>
    <td width="420"><A HREF="javascript:a('A')">Prepagados Comerciales</a></td>     
  </tr>
  <tr> 
    <td width=30 height="18" > 
      <div align="center"><b>D</b></div>
    </td>
    <td width="420"><A HREF="javascript:a('D')">Descontados Calendario</a></td>    
  </tr>
  <tr> 
    <td width=30 height="18" > 
      <div align="center"><b>T</b></div>
    </td>
    <td width="420"><A HREF="javascript:a('T')">Descontados Comerciales</a></td>    
  </tr>
  <tr> 
    <td width=30 height="18" > 
      <div align="center"><b>R</b></div>
    </td>
    <td width="420"><A HREF="javascript:a('R')">Capitalizados (CD'S)</a></td>    
  </tr>
  <tr> 
    <td width=30 height="18" > 
      <div align="center"><b>C</b></div>
    </td>
    <td width="420"><A HREF="javascript:a('C')">Interes Compuesto</a></td>    
  </tr>  
</table>

<h4>Formula Exponencial</h4>

<table class="tableinfo" style="width:95%" ALIGN=CENTER>
  <tr id="trdark">
    <td width=30 height="18" >
      <div align="left"><b>C&oacute;digo</b></div>
    </td>
    <td width="420">
      <div align="left"><b>Descripcion</b></div>
    </td>	
  </tr>
  <tr>
  	<td width=30 height="18" > 
      <div align="center"><b>1</b></div>
    </td>
    <td width="420"><a href="javascript:a('1')">Al Vencimiento Calendario</a></td>
  </tr>
  <tr>
  	<td width=30 height="18" > 
      <div align="center"><b>2</b></div>
    </td>
    <td width="420"><a href="javascript:a('2')">Al Vencimiento Comerciales</a></td>
  </tr>  
  <tr>
  	<td width=30 height="18" > 
      <div align="center"><b>3</b></div>
    </td>
    <td width="420"><A HREF="javascript:a('3')">Prepagados Calendario</a></td>
  </tr>
  <tr>
  	<td width=30 height="18" > 
      <div align="center"><b>4</b></div>
    </td>
    <td width="420"><A HREF="javascript:a('4')">Prepagados Comerciales</a></td>
  </tr>
  <tr>
  	<td width=30 height="18" > 
      <div align="center"><b>5</b></div>
    </td>
    <td width="420"><A HREF="javascript:a('5')">Descontados Calendario</a></td>
  </tr>
  <tr>
  	<td width=30 height="18" > 
      <div align="center"><b>6</b></div>
    </td>
    <td width="420"><A HREF="javascript:a('6')">Descontados Comerciales</a></td>
  </tr>
  <tr>
  	<td width=30 height="18" > 
      <div align="center"><b>7</b></div>
    </td>
    <td width="420"><A HREF="javascript:a('7')">Capitalizados (CD'S)</a></td>
  </tr>
  <tr>
  	<td width=30 height="18" > 
      <div align="center"><b>8</b></div>
    </td>
    <td width="420"><A HREF="javascript:a('8')">Interes Compuesto</a></td>
  </tr>
</table>  
  
<P>
</P>
</BODY>
</HTML>
