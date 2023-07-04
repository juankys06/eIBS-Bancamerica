<html>

<head>
<title>e-IBS Integrated Banking System</title>

<SCRIPT SRC="<%=request.getContextPath()%>/pages/s/javascripts/eIBS.jsp"> </SCRIPT>

<SCRIPT language="JavaScript">

	function setLayout() {
		setCenterSize(800,600);
	}


	<!--hide JS code
	var msg =" eIBS --> Datapro, Inc. ...Miami, FL USA (305) 374-0606  "
	function scrollMsg()//  display scrolling text
	{
		window.status=msg
		//msg=msg.substring(1, msg.length) + msg.substring(0,1)
		setTimeout("scrollMsg()", 10000)
	}
	// end JS hide -->

	setLayout();
	scrollMsg();
	
</SCRIPT> 

</head>

<frameset rows="66,*" frameborder="0" border="0" framespacing="0"> 
  <frame name="top" scrolling="NO" noresize target="menu" src="<%=request.getContextPath()%>/servlet/datapro.eibs.products.JSESS0090"> 
  <frameset cols="180,*" frameborder="0" border="0" framespacing="0"> 
    <frame name="menu" target="main" src="<%=request.getContextPath()%>/servlet/datapro.eibs.menu.JSESS0040Bean" scrolling="NO" noresize>
    <frameset rows="20,*" frameborder="0" border="0" framespacing="0"> 
    	<frame name="client" src="<%=request.getContextPath()%>/pages/s/ESD0800_client_info.jsp" scrolling="NO" noresize>
    	<frame name="main" src="<%=request.getContextPath()%>/pages/background.jsp" scrolling="auto">
    </frameset>
  </frameset>
</frameset>

<noframes>

<body bgcolor="#FFFFFF">
</body>

</noframes>

</html>
