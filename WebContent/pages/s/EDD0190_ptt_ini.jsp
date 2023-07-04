<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.0 Transitional//EN">
<html>
<head>
<title>Depositos</title>
<META HTTP-EQUIV="Content-Type" CONTENT="text/html; charset=ISO-8859-1">
<META name="GENERATOR" content="IBM WebSphere Page Designer V3.5.2 for Windows">
<META http-equiv="Content-Style-Type" content="text/css">
<link Href="/eIBS_R04M07/pages/style.css" rel="stylesheet">


<SCRIPT SRC="/eIBS_R04M07/pages/s/javascripts/eIBS.js"> </SCRIPT>

</head>
<jsp:useBean id= "error" class= "datapro.eibs.beans.ELEERRMessage"  scope="session" />

<jsp:useBean id= "userPO" class= "datapro.eibs.beans.UserPos"  scope="session" />

<body bgcolor="#FFFFFF">
 
<H3 align="center">Consulta de Comisiones<img src="/eIBS_R04M07/images/e_ibs.gif" align="left" name="EIBS_GIF" ALT="transaction_enter,EGL0035"></H3>

<hr size="4">
<p>&nbsp;</p>
					
<form method="post" action="/eIBS_R04M07/servlet/datapro.eibs.comisiones.JSECM0001?SCREEN=100">
  <p> 
    <INPUT TYPE=HIDDEN NAME="SCREEN" VALUE="100">
  </p>
  <h4>&nbsp;</h4>
    <p align="center">
    <input type=image class=imgfilter name="Submit" src="/eIBS_R04M07/images/s/bt_enviar.gif" onMouseDown="this.className='' "onMouseUp="this.className='imgfilter' " >
  </p>

</form>
</body>
</html>
