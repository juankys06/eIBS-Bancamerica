
<HTML>
<HEAD>
<META HTTP-EQUIV="Content-Type" CONTENT="text/html; charset=ISO-8859-1">
<TITLE>Clase de Cliente</TITLE>
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
<h4>Seleccione la clase de cliente</h4>
<table class="tableinfo" align=center style="width:95%">
  <tr id="trdark">
    <td width=30  height="18">
      <div align="left"><b>C&oacute;digo</b></div>
    </td>
    <td width="420">
      <div align="left"><b>Descripci&oacute;n</b></div>
    </td>
  </tr>
	<TR>
		<TD width="30" height="18"></TD>
		<TD width="420"><NOBR><A href="javascript:a(' ')"> NO APLICA</A></NOBR></TD>
	</TR>
	<tr> 
    <td width=30  height="18"> 
      <div align="center"><b>1</b></div>
    </td>
    <td width="420"><NOBR><A HREF="javascript:a('1')"> ENTE GUBERNAMENTAL</a></td>
  </tr>
  <tr> 
    <td width=30  height="18"> 
      <div align="center"><b>2</b></div>
    </td>
    <td width="420"><NOBR><A HREF="javascript:a('2')"> ENTE DESCENTRALIZADO</a></td>
  </tr>
  <tr> 
     <td width=30  height="18"> 
      <div align="center"><b>3</b></div>
    </td>
    <td width="420"><NOBR><A HREF="javascript:a('3')"> REGULAR</a></td>
  </tr>

<tr> 
     <td width=30  height="18"> 
      <div align="center"><b>4</b></div>
    </td>
    <td width="420"><NOBR><A HREF="javascript:a('4')"> CIVIL</a></td>
  </tr>

<tr> 
     <td width=30  height="18"> 
      <div align="center"><b>5</b></div>
    </td>
    <td width="420"><NOBR><A HREF="javascript:a('5')"> MILITAR</a></td>
  </tr>

<tr> 
     <td width=30  height="18"> 
      <div align="center"><b>6</b></div>
    </td>
    <td width="420"><NOBR><A HREF="javascript:a('6')"> PEP</a></td>
  </tr>
<tr> 
     <td width=30  height="18"> 
      <div align="center"><b>7</b></div>
    </td>
    <td width="420"><NOBR><A HREF="javascript:a('7')"> EMPRESAS PRIVADAS</a></td>
  </tr>
<tr> 
     <td width=30  height="18"> 
      <div align="center"><b>8</b></div>
    </td>
    <td width="420"><NOBR><A HREF="javascript:a('8')"> EMPRESAS SIN FINES DE LUCRO</a></td>
  </tr>
<tr> 
     <td width=30  height="18"> 
      <div align="center"><b>9</b></div>
    </td>
    <td width="420"><NOBR><A HREF="javascript:a('9')"> EMPRESAS REGULADAS</a></td>
  </tr>
<tr> 
     <td width=30  height="18"> 
      <div align="center"><b>A</b></div>
    </td>
    <td width="420"><NOBR><A HREF="javascript:a('A')">EMBAJADAS/CONSULADOS </a></td>
  </tr>
<tr> 
     <td width=30  height="18"> 
      <div align="center"><b>B</b></div>
    </td>
    <td width="420"><NOBR><A HREF="javascript:a('B')"> RELIGIOSAS</a></td>
  </tr>

</table>
</BODY>
</HTML>
