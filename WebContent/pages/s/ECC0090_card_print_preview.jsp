<META HTTP-EQUIV="Pragma" CONTENT="No-cache">
<html>
<head>
<title>Asignación de Tarjetas</title>
<link Href="<%=request.getContextPath()%>/pages/style.css" rel="stylesheet">

<jsp:useBean id= "userPO" class= "datapro.eibs.beans.UserPos"  scope="session" />
<script language="Javascript1.1" src="<%=request.getContextPath()%>/pages/s/javascripts/eIBS.jsp"> </SCRIPT>

<script language="JavaScript">

function doPrint(){
	if(!window.print){
       var msg ="Debe actualizar su navegador para imprimir.";
	   alert(msg);
	   return;
	}
	
    window.focus();
	window.print();

	return;
}

</SCRIPT>  

</head>
<body>
<P><BR>
<BR>
<BR>
<BR>
<BR>
<BR>
<BR>
<BR>
<font face="Arial" size="2"><%= request.getParameter("NAME").toUpperCase() %></font>
</P>

<script language="JavaScript">
	doPrint();
</SCRIPT> 

</body>
</html>