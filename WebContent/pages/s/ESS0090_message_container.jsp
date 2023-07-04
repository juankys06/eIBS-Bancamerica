
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 3.2 //EN">
<HTML>
<HEAD>
<TITLE>
Visor de Mensajes
</TITLE>
<link href="<%=request.getContextPath()%>/pages/style.css" rel="stylesheet">
</HEAD>
<FRAMESET cols="70%,*">
<FRAMESET rows="45,*">
<FRAME src="<%=request.getContextPath()%>/pages/s/ESS0090_message_header.jsp" name="msgHeader" scrolling="NO" noresize frameborder="NO" marginheight="0">
<FRAME src="<%=request.getContextPath()%>/servlet/datapro.eibs.products.JSESS0090?SCREEN=1" name="msgList" scrolling="YES" noresize frameborder="NO" >

</FRAMESET>

<FRAME src="" name="msgText" scrolling="NO" noresize frameborder="NO">
</FRAMESET>
<NOFRAMES>
<BODY>
<P>Need browser which supports frames to see
this page</P>
</BODY>
</NOFRAMES>
</HTML>
