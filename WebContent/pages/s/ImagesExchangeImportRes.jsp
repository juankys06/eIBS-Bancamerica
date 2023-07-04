<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.0 Transitional//EN">
<html>
<head>
<META HTTP-EQUIV="Content-Type" CONTENT="text/html; charset=ISO-8859-1">
<META name="GENERATOR" content="IBM WebSphere Page Designer V3.5.2 for Windows">
<META http-equiv="Content-Style-Type" content="text/css">
<link Href="<%=request.getContextPath()%>/pages/style.css" rel="stylesheet">

<script language="Javascript1.1" src="<%=request.getContextPath()%>/pages/s/javascripts/eIBS.jsp"> </SCRIPT>
</head>

<body bgcolor="#FFFFFF">

<H3 align="center">Resultado Importaci&oacute;n de Im&aacute;genes<img src="<%=request.getContextPath()%>/images/e_ibs.gif" align="left" name="EIBS_GIF" ALT="ExportImagesEnter.jsp"></H3>
<hr size="4">

<form method="post" action="<%=request.getContextPath()%>/servlet/datapro.eibs.tools.JSImagesExchange" >

<INPUT TYPE=HIDDEN NAME="SCREEN" VALUE="200">

<TABLE width="70%" border="0" align="center">
	<TBODY>
		<TR>
			<TD> <B>Fecha de Importaci&oacute;n :</B></TD>
			<TD> <%= request.getAttribute("pDate") %> </TD>
		</TR>

		<TR>
			<TD> <B>N&uacute;mero de Imágenes Recibidas :</B></TD>
			<TD> <%= request.getAttribute("nImgs") %> </TD>
		</TR>

		<TR>
			<TD> <B>Archivo Importado</B></TD>
			<TD> <%= request.getAttribute("fileName") %> </TD>
		</TR>


	</TBODY>
</TABLE>

</form>
</body>
</html>

 