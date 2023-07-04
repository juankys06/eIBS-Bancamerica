<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.0 Transitional//EN">
<HTML><HEAD><TITLE>eIBS Portal</TITLE>
<META content="text/html; charset=iso-8859-1" http-equiv=Content-Type>
<META content="MSHTML 5.00.2920.0" name=GENERATOR>
<SCRIPT SRC="<%=request.getContextPath()%>/pages/s/javascripts/eIBS.jsp"> </SCRIPT>
<script language="JavaScript">
<!--
function OpenWin(lang_path) {
   var w=screen.width;
   var h=screen.height;
   var posTop=(h-200)/2;
   var posLeft=(w-310)/2;
   var position='left='+posLeft+',top='+posTop;
   var newWindow=window.open('<%=request.getContextPath()%>/pages/' +lang_path +'/ESS0030_LogIn.jsp','eibs','status=yes,toolbar=no,location=no,directories=no,menubar=no,scrollbars=yes,resizable=yes,copyhistory=no,height=150,width=300,'+position)
   
}

function MM_swapImgRestore() { //v3.0
  var i,x,a=document.MM_sr; for(i=0;a&&i<a.length&&(x=a[i])&&x.oSrc;i++) x.src=x.oSrc;
}

function MM_preloadImages() { //v3.0
  var d=document; if(d.images){ if(!d.MM_p) d.MM_p=new Array();
    var i,j=d.MM_p.length,a=MM_preloadImages.arguments; for(i=0; i<a.length; i++)
    if (a[i].indexOf("#")!=0){ d.MM_p[j]=new Image; d.MM_p[j++].src=a[i];}}
}

function MM_findObj(n, d) { //v4.0
  var p,i,x;  if(!d) d=document; if((p=n.indexOf("?"))>0&&parent.frames.length) {
    d=parent.frames[n.substring(p+1)].document; n=n.substring(0,p);}
  if(!(x=d[n])&&d.all) x=d.all[n]; for (i=0;!x&&i<d.forms.length;i++) x=d.forms[i][n];
  for(i=0;!x&&d.layers&&i<d.layers.length;i++) x=MM_findObj(n,d.layers[i].document);
  if(!x && document.getElementById) x=document.getElementById(n); return x;
}

function MM_swapImage() { //v3.0
  var i,j=0,x,a=MM_swapImage.arguments; document.MM_sr=new Array; for(i=0;i<(a.length-2);i+=3)
   if ((x=MM_findObj(a[i]))!=null){document.MM_sr[j++]=x; if(!x.oSrc) x.oSrc=x.src; x.src=a[i+2];}
}
//-->
</script>
</HEAD>
<body onLoad="MM_preloadImages('<%=request.getContextPath()%>/images/login_english_over.gif')">
<DIV VALIGN="middle">
  <table border="0" cellspacing="0" cellpadding="0" align="center" width="98%" height="98%">
    <tr> 
      <td align="right">&nbsp;</td>
      <td width="427" align="right">&nbsp;</td>
      <td width="82" align="left">&nbsp;</td>
      <td width="92" align="left">&nbsp;</td>
      <td align="left">&nbsp;</td>
    </tr>
    <tr> 
      <td height="96" align="right">&nbsp;</td>
      <td height="96" width="427" align="right"><img src="<%=request.getContextPath()%>/images/login_1.gif" width="427" height="96" vspace="0" hspace="0"></td>
      <td height="96" width="82" align="left"><img src="<%=request.getContextPath()%>/images/login_2.gif" width="82" height="96" vspace="0" hspace="0"></td>
      <td height="96" width="92" align="left"><img src="<%=request.getContextPath()%>/images/login_3.gif" width="155" height="96" vspace="0" hspace="0" align="left"></td>
      <td height="96" align="left">&nbsp;</td>
    </tr>
    <tr> 
      <td height="91" align="right">&nbsp;</td>
      <td height="91" width="427" align="right">&nbsp;</td>
      <td height="91" width="82" align="left">&nbsp;</td>
      <td height="91" width="92" align="left">&nbsp;</td>
      <td height="91" align="left">&nbsp;</td>
    </tr>
    <tr> 
      <td height="29" background="<%=request.getContextPath()%>/images/login_backgroundimage.gif" align="right">&nbsp;</td>
      <td height="29" width="427" align="right"><img src="<%=request.getContextPath()%>/images/login_4.gif" width="427" height="29" vspace="0" hspace="0"></td>
      <td height="29" background="<%=request.getContextPath()%>/images/login_backgroundimage.gif" align="right">&nbsp;</td>
      <td height="29" width="92" align="left"><a href="javascript: OpenWin('s')" onMouseOut="MM_swapImgRestore()" onMouseOver="MM_swapImage('spanish','','<%=request.getContextPath()%>/images/login_espaniol_over.gif',1)"><img name="spanish" border="0" src="<%=request.getContextPath()%>/images/login_espaniol.gif" width="155" height="29" vspace="0" hspace="0"></a></td>
      <td height="29" align="left" background="<%=request.getContextPath()%>/images/login_backgroundimage.gif">&nbsp;</td>
    </tr>
    <tr> 
      <td height="101" align="right">&nbsp;</td>
      <td height="101" width="427" align="right">&nbsp;</td>
      <td height="101" width="82" align="left">&nbsp;</td>
      <td height="101" width="92" align="left">&nbsp;</td>
      <td height="101" align="left">&nbsp;</td>
    </tr>
    <tr> 
      <td height="51" align="right">&nbsp;</td>
      <td height="51" width="427" align="right">&nbsp;</td>
      <td height="51" width="82" align="left"><img src="<%=request.getContextPath()%>/images/login_datapro.gif" width="82" height="51" vspace="0" hspace="0" align="right" border="0" usemap="#Map2"></td>
      <td height="51" width="92" align="left"><img src="<%=request.getContextPath()%>/images/login_datapro_2.gif" width="155" height="51" vspace="0" hspace="0" border="0" usemap="#Map"></td>
      <td height="51" align="left">&nbsp;</td>
    </tr>
    <tr> 
      <td align="right">&nbsp;</td>
      <td width="427" align="right">&nbsp;</td>
      <td width="82" align="left">&nbsp;</td>
      <td width="92" align="left">&nbsp;</td>
      <td align="left">&nbsp;</td>
    </tr>
    <tr>
      <td align="right">&nbsp;</td>
      <td width="427" align="right">&nbsp;</td>
      <td width="82" align="left">&nbsp;</td>
      <td width="92" align="left">&nbsp;</td>
      <td align="left">&nbsp;</td>
    </tr>
  </table>
</DIV>
<map name="Map"> 
  <area shape="rect" coords="-69,1,160,38" href="http://www.datapromiami.com">
</map>
<map name="Map2"> 
  <area shape="rect" coords="1,0,82,38" href="http://www.datapromiami.com">
</map>
</BODY>
</HTML>

