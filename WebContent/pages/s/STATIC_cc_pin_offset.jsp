<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.0 Transitional//EN">

<HTML>
<HEAD>
<META HTTP-EQUIV="Content-Type" CONTENT="text/html; charset=ISO-8859-1">
<META name="GENERATOR" content="IBM WebSphere Page Designer V3.5.2 for Windows">
<META http-equiv="Content-Style-Type" content="text/css">
<TITLE>PIN/Offset Indicators</TITLE>
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

<h4> PIN/Offset Indicators</h4>
<table align=center class=tableinfo style="width:95%">
  <TR id=trdark> 
    <TH align=left  > 
      <div align="left">Code</div>
    </TH>
    <TH align=left  > 
      <div align="left">Description</div>
    </TH>
  </TR>
  <tr> 
    <td width=30  > 
      <div align="center"><b> </b></div>
    </td>
    <td ><a href="javascript:a(' ')">Unused-N/A</a></td>
  </tr>
  <tr> 
    <td width=30  > 
      <div align="center"><b>1</b></div>
    </td>
    <td ><a href="javascript:a('1')">PIN (clear)</a></td>
  </tr>
  <tr> 
    <td width=30  > 
      <div align="center"><b></b>2</div>
    </td>
    <td ><A HREF="javascript:a('2')">DES Offset (clear)</a></td>
  </tr>
  <tr> 
    <td width=30  > 
      <div align="center"><b>3</b></div>
    </td>
    <td ><a href="javascript:a('3')">PIN Encrypted (hex representation)</a></td>
  </tr>
  <tr> 
    <td width=30  > 
      <div align="center"><b>4</b></div>
    </td>
    <td ><a href="javascript:a('4')">PIN Encrypted (character representation of hex)</a></td>
  </tr>  
  <tr> 
    <td width=30  > 
      <div align="center"><b>5</b></div>
    </td>
    <td ><a href="javascript:a('5')">Offset Encrypted (hex representation)</a></td>
  </tr>  
  <tr> 
    <td width=30  > 
      <div align="center"><b>6</b></div>
    </td>
    <td ><a href="javascript:a('6')">Offset Encrypted (character representation of hex)</a></td>
  </tr>   
  <tr> 
    <td width=30  > 
      <div align="center"><b>7</b></div>
    </td>
    <td ><a href="javascript:a('7')">Fifth Third Generated PIN/OFFSET</a></td>
  </tr>   
</table>
<P>
</P>
</BODY>
</HTML>
