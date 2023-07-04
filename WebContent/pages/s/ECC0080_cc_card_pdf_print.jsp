<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.0 Transitional//EN">
<html>
<head>
<title>Print PDF File</title>
<META HTTP-EQUIV="Content-Type" CONTENT="text/html; charset=ISO-8859-1">
<META name="GENERATOR" content="IBM WebSphere Page Designer V3.5.2 for Windows">
<META http-equiv="Content-Style-Type" content="text/css">
<link Href="<%=request.getContextPath()%>/pages/style.css" rel="stylesheet">

<SCRIPT language="Javascript1.1"> 

function print(){ // Is prefered the javascript on pdf document
	Pdf1.printPages(1,1);	//Pdf1.print();
	self.window.location.href = "<%=request.getContextPath()%>/pages/background.jsp";
}

function goBack(){
	self.window.location.href = "<%=request.getContextPath()%>/pages/s/ECC0080_cc_accounts_list.jsp";
}

function goExit(){
	self.window.location.href = "<%=request.getContextPath()%>/pages/background.jsp";
}

</SCRIPT>
</head>
<body>
    <object id="Pdf1" type="application/pdf" width="590" height="25">
		<param name="src" value="file://<%=request.getParameter("path")%>ecc0080.pdf">
	</object>

  <br>
  <p></p>
  <table class="tbenter" HEIGHT="75%" width="100%" border="0">
    <tr> 
      	<td nowrap ALIGN=CENTER> 
  			<div align="center"> 
	   			<input id="EIBSBTN" type=button name="Regresar" value="Regresar" onclick="goBack();">
  			</div>  
  		</td>
  		<td>
  			<div align="center"> 
	   			<input id="EIBSBTN" type=button name="Salir" value="Salir" onclick="goExit();">
  			</div>   
      	</td>
    </tr>
  </table>

  
<SCRIPT language="Javascript1.1"> 
	//print();
</SCRIPT>
</body>
</html>

