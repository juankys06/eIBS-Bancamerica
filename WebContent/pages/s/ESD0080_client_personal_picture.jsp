<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<title>Informacion Basica</title>
<META HTTP-EQUIV="Content-Type" CONTENT="text/html; charset=ISO-8859-1">
<META name="GENERATOR" content="IBM WebSphere Studio">
<link href="../style.css" rel="stylesheet">

<script language="Javascript1.1" src="<%=request.getContextPath()%>/pages/s/javascripts/eIBS.jsp"> </SCRIPT>
<script language="Javascript1.1" src="<%=request.getContextPath()%>/pages/s/javascripts/optMenu.jsp"> </SCRIPT>
 
</head>

<body bgcolor="#FFFFFF">

<h3 align="center">Fotografía Cliente Personal<img src="../../images/e_ibs.gif" align="left" name="EIBS_GIF" ALT="client_personal_picture, ESD0080"  ></h3>
<hr size="4">

	<DIV align="center"  >
	<img src="<%=request.getContextPath()%>/servlet/datapro.eibs.client.JSESD0080?SCREEN=1000" alt="Imagen" align="bottom" border="0" 
		onerror="this.style.display='none';document.getElementById('imgnotav').style.display='block';" >
	</DIV>

	<DIV id="imgnotav" style="display:none;" >
	<%-- THIS SECTION IS SHOWN ONLY IF THERE IS NO IMAGE --%>
	<h3>Imágen No Disponible.</h3>
	</DIV>

</body>
</html>

