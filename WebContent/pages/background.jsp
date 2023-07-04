<HTML>
<HEAD>
<meta http-equiv=Content-Type content="text/html;  charset=ISO-8859-1">
<TITLE>background</TITLE>
<SCRIPT LANGUAGE="JavaScript">
<!--
   function right() {
 if (navigator.appName == 'Microsoft Internet Explorer' && 
(event.button == 2 || event.button == 3)) {
alert("eIBS \nDatapro, inc.\nAll Right Reserved");
return false;
}
return true;
} 
document.onmousedown=right;
window.onmousedown=right;
   
   //-->
</SCRIPT>

<jsp:useBean id="currUser" class="datapro.eibs.beans.ESS0030DSMessage"  scope="session" />

</HEAD>
<script>
 if ( window.name !="main" ){
			top.close();
 }
</script>

<BODY leftmargin="0" topmargin="0" marginwidth="0" marginheight="0">
<div valign=center halign=top> 
  <table width="621" border="0" cellspacing="0" cellpadding="0" height="100%" align="left">
    <tr> 
      <td height="50" width="299" bgcolor="#CBD5DF">&nbsp;</td>
    </tr>
    <tr> 
      <td height="155"><img src="<%=request.getContextPath()%>/images/background<%=currUser.getE01UBK()%>.jpg" vspace="0" hspace="0" border="0"></td>
    </tr>
    <tr> 
      <td valign="bottom" width="299" bgcolor="#CBD5DF" align="right"><img src="<%=request.getContextPath()%>/images/background2.gif" vspace="0" hspace="0" border="0"></td>
    </tr>
  </table>
</div>
</BODY>
</HTML>
