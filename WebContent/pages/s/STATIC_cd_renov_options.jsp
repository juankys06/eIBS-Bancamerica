
<HTML>
<HEAD>
<META HTTP-EQUIV="Content-Type" CONTENT="text/html; charset=ISO-8859-1">
<TITLE>Opciones de Renovacion</TITLE>
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
		top.opener.document.forms[0].elements[n].focus();
  		break;
	}
 }
 top.close();
 }
//-->
</script>
</HEAD>
<BODY>
<H4>Seleccione el código de calificación</H4>
<table class="tableinfo" style="width:95%" ALIGN=CENTER>
  <tr id="trdark">
    <td width=30><b>C&oacute;digo</b></td>
    <td><b>Descripci&oacute;n</b></td>
  </tr>
  <tr> 
    <td width=30><b>A</b></td>
    <td><A HREF="javascript:a('A')"> Principal m&aacute;s intereses ser&aacute;n 
      renovados por el mismo per&iacute;odo de tiempo </a></td>
  </tr>
  <tr> 
    <td width=30><b>B</b></td>
    <td ><A HREF="javascript:a('B')"> Inter&eacute;s ser&aacute; pagado a una 
      Cuenta de Detalle (Corriente, Ahorro, etc.) o a otro Certificado y el Principal 
      ser&aacute; renovado por el mismo per&iacute;odo de tiempo </a></td>
  </tr>
  <tr> 
    <td width=30><b>C</b></td>
    <td ><A HREF="javascript:a('C')"> Inter&eacute;s ser&aacute; pagado a una 
      cuenta puente y el Principal ser&aacute; renovado por el mismo per&iacute;odo 
      de tiempo </a></td>
  </tr>
  <tr> 
    <td width=30><b>D</b></td>
    <td ><A HREF="javascript:a('D')"> Inter&eacute;s y Principal ser&aacute;n 
      pagados a una cuenta puente. No hay renovaci&oacute;n en esta opci&oacute;n 
      </a></td>
  </tr>
  <tr> 
    <td width=30><b>E</b></td>
    <td ><A HREF="javascript:a('E')"> Inter&eacute;s y Principal ser&aacute;n 
      pagados a una Cuenta de Detalle(Corriente, Ahorro, etc.). No hay renovaci&oacute;n 
      en esta opci&oacute;n </a></td>
  </tr>
  <tr> 
    <td width=30><b>F</b></td>
    <td ><A HREF="javascript:a('F')"> El Monto de Repago ser&aacute; debitado 
      o acreditado a una Cuenta de Detalle y el resto del Principal e Inter&eacute;s 
      ser&aacute;n renovado por el mismo per&iacute;odo de tiempo </a></td>
  </tr>
  <tr> 
    <td width=30><b>G</b></td>
    <td ><A HREF="javascript:a('G')"> Inter&eacute;s Parcial ser&aacute; pagado 
      a una Cuenta de Detalle y el resto de Principal e Inter&eacute;s ser&aacute;n 
      renovados por el mismo per&iacute;odo de tiempo </a></td>
  </tr>
  <tr> 
    <td width=30><b>H</b></td>
    <td ><A HREF="javascript:a('H')"> Inter&eacute;s ser&aacute; pagado mensualmente 
      en el Aniversario Indicado a una cuenta o mismo Contrato. El Principal ser&aacute; 
      renovado por el mismo per&iacute;odo de tiempo </a></td>
  </tr>
  <tr> 
    <td width=30><b>I</b></td>
    <td ><A HREF="javascript:a('I')"> Inter&eacute;s ser&aacute; pagado en xx 
      n&uacute;mero de d&iacute;as a una cuenta puente. El Principal ser&aacute; 
      renovado por el mismo per&iacute;odo de tiempo </a></td>
  </tr>
  <tr> 
    <td width=30><b>J</b></td>
    <td ><A HREF="javascript:a('J')"> Inter&eacute;s y Principal ser&aacute;n 
      pagados a un Certificado de Dep&oacute;sito.No hay renovaci&oacute;n en 
      esta opci&oacute;n </a></td>
  </tr>
  <tr> 
    <td width=30><b>K</b></td>
    <td ><A HREF="javascript:a('K')"> Igual al C&oacute;digo &quot;H&quot; pero 
      sin Renovaci&oacute;n. Tener en cuenta si se usa Aniversario &quot;31&quot; 
      ser&aacute; pagado a Fin de Mes, si es Mayor entonces ser&aacute; C&iacute;clico. 
      </a></td>
  </tr>
  <tr> 
    <td width=30><b>P</b></td>
    <td ><A HREF="javascript:a('P')"> Pendiente Instrucciones del Cliente.Despu&eacute;s 
      X d&iacute;as se renueva como C&oacute;digo </a></td>
  </tr>
  <tr> 
    <td width=30><b>S</b></td>
    <td ><A HREF="javascript:a('S')"> Inter&eacute;s y Principal ser&aacute;n 
      pagados basados en un Plan de Pagos Predefinido </a></td>
  </tr>
</table>
</BODY>
</HTML>
