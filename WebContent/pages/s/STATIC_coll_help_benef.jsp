<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.0 Transitional//EN">

<HTML>
<HEAD>
<META HTTP-EQUIV="Content-Type" CONTENT="text/html; charset=ISO-8859-1">
<META name="GENERATOR" content="IBM WebSphere Page Designer V3.5.2 for Windows">
<META http-equiv="Content-Style-Type" content="text/css">
<TITLE>Estado del Bien</TITLE>
<link href="<%=request.getContextPath()%>/pages/style.css" rel="stylesheet">
<script language="javascript">
//<!-- Hide from old browsers

function a(code) {
var formLength= top.opener.document.forms[0].elements.length;
//for(n=0;n<formLength;n++)
//{
	//var elementName= top.opener.document.forms[0].elements[n].name;
	var campo=top.opener.fieldName
	//if(elementName == top.opener.fieldName) 
	//{
  		top.opener.document.forms[0].elements[campo].value = code;
  			//campo.value = code;
  		//break;
	//}
// }
 top.close();
 }
//-->
</script>
</HEAD>
<BODY>
<H4>Estado del Bien/Beneficiario</H4>
<table align=center class="tableinfo" style="width:95%">
  <tr id="trdark">
    <td width=39  height="18">
      <div align="center"><b>Código</b></div>
    </td>
    <td width="424"> 
      <div align="left"><b>Descripción</b></div>
    </td>
  </tr>
  <tr > 
    <td width=39  height="18"> 
      <div align="center"><b>C</b></div>
    </td>
    <td width="424"><A HREF="javascript:a('C')"> Constituida</a></td>
  </tr>
 
</table> 
<P>
</P>
</BODY>
</HTML>
