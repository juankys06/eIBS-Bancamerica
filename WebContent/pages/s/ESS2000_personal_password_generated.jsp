<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.0 Transitional//EN">
<html>
<head>
<title>Personal User</title>
<META HTTP-EQUIV="Content-Type" CONTENT="text/html; charset=ISO-8859-1">
<META name="GENERATOR" content="IBM WebSphere Page Designer V3.5.2 for Windows">
<META http-equiv="Content-Style-Type" content="text/css">
<meta http-equiv='Content-Type' content='text/html;charset=iso-8859-1'>
<meta http-equiv='expires' content='0'>
<meta http-equiv='cache-control' content='no-cache'>
<meta http-equiv='pragma' content='no-cache'>
<link href="<%=request.getContextPath()%>/pages/style.css" rel="stylesheet" media="screen, projection">

<script language="Javascript1.1" src="<%=request.getContextPath()%>/pages/s/javascripts/eIBS.jsp"> </SCRIPT>
<% String IUser = request.getParameter("E01EUSUSR"); %>

<jsp:useBean id="error" class="datapro.eibs.beans.ELEERRMessage"  scope="session" />
<jsp:useBean id="cusdata" class="datapro.eibs.beans.ESS200001Message" scope="session" />

<script language="JavaScript" type="text/javascript"> 
function makeFrame(Opt) { 
   page = "<%=request.getContextPath()%>/servlet/com.datapro.eibs.internet.JSESS2000?SCREEN=425&E01EUSUSR=<%= IUser %>&H01FLGWK2=" + Opt;
   ifrm = document.createElement("IFRAME");
   ifrm.setAttribute("src", page); 
   ifrm.style.width = 5+"px"; 
   ifrm.style.height = 5+"px"; 
   document.body.appendChild(ifrm);
   document.getElementById("Frame1").style.display = 'none';
   if(Opt=="E"){
      document.getElementById("FrameEml").style.display = '';
   }else{
      document.getElementById("Frame2").style.display = '';
   }
} 
</script> 

</head>

<body>
<h3 align="center">Generación de Contraseña Banca por Internet<img src="<%=request.getContextPath()%>/images/e_ibs.gif" align="left" name="EIBS_GIF" alt="client_personal_password_generated, ESS2000"></h3>
<hr size="4">
<form method="post" action="<%=request.getContextPath()%>/servlet/com.datapro.eibs.internet.JSESS2000">
<div id="Frame1">
 <INPUT TYPE=HIDDEN NAME="SCREEN" VALUE="425">
 <INPUT TYPE=HIDDEN NAME="E01EUSUSR" VALUE="<%= IUser %>">

  <% if(!error.getERRNUM().equals("0")){ %>
	  <h4 align="center"><%= error.getERDS01() %></h4>
  <% }else{ %>
      <h2 align="center">Para generar la nueva contraseña del Usuario Seleccione :</h2>
      <h3 align="left">Contraseña Impresa :</h3>
      <h3 align="center">1. Tener la impresora correcta configurada como default</h3>
      <h3 align="center">2. La impresora debe estar encendida.</h3>
      <h3 align="center">3. La impresora debe de tener el papel adecuado para impresión de contraseñas.</h3>
     <p align="center"> 
       <input class="EIBSBTN" id="BTMSBM" type=button name="Submit" value="Generar e Imprimir" onclick="makeFrame('P')">
      </p>
      <h3 align="left">Contraseña a eMail del Usuario :</h3>
      <h3 align="center">1. eMail del usuario Actualizado</h3>
      <p align="center"> 
       <input class="EIBSBTN" id="BTMSBM" type=button name="Submit" value="Generar e eMail" onclick="makeFrame('E')">
      </p>
      
  <% } 
     error.setERRNUM("0") ; %>	  
</div>  
<div id="Frame2" style='display:none'>
<h3 align="center">Contrasena Generada e Imprimiendose ... <BR>Espere la impresion y luego presione Cerrar</h3>
<P align="center">
   <input class="EIBSBTN" type=button name="Submit" value="Cerrar" onclick="window.close()">
</div> 

<div id="FrameEml" style='display:none'>
<h3 align="center">Contrasena Enviada a email de Usuario <BR></h3>
<P align="center">
   <input class="EIBSBTN" type=button name="Submit" value="Cerrar" onclick="window.close()">
</div> 
</form>
</body>
</html>
