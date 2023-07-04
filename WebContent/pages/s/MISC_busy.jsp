<!-- Sample HTML file -->
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 3.2 //EN">
<HTML>
<HEAD>
<meta http-equiv="Refresh" CONTENT="60"; url="<%=request.getContextPath()%>/pages/background.jsp">
<TITLE>
System Busy
</TITLE>
<script language="Javascript1.1" src="<%=request.getContextPath()%>/pages/s/javascripts/eIBS.jsp"> </SCRIPT>
<SCRIPT language="JavaScript">
   var newWin
 
   function setLayout() {
 if ( window.name !="main" ){
 		self.moveTo(300,150)
		self.resizeTo(450,430);
  											}
 														}
</SCRIPT>

</HEAD>

<BODY BGCOLOR="#FFFFFF" onLoad="setLayout()">
<table width="100%" border="0" cellspacing="0" cellpadding="0" height="100%" align="center">
  <tr>
    <td> 
      <table width="400" border="0" cellspacing="0" cellpadding="0" align="center">
        <tr> 
          <td colspan="3" height="10" bgcolor="#0078BD"> 
            <div align="right"><font face="Arial, Helvetica, sans-serif"><b><font color="#FFFFFF">eIBS</font></b></font></div>
          </td>
        </tr>
        <tr> 
          <td width="5">&nbsp;</td>
          <td width="369"> 
            <div align="center"><img src="//Nt_srvdev/VSS/eIBS_WebApp<%=request.getContextPath()%>/images/busy.gif" width="200" height="200"></div>
          </td>
          <td width="5">&nbsp;</td>
        </tr>
        <tr> 
          <td width="5">&nbsp;</td>
          <td width="369"> 
            <div align="center"><font face="Tahoma, Arial, Garamond, Futura Lt BT, Futura XBlk BT, Futura Md BT, FuturaBlack BT" size="5"><b><font color="#0078BD">El 
              Servidor est&aacute; saturado</font></b></font></div>
          </td>
          <td width="5">&nbsp;</td>
        </tr>
        <tr> 
          <td width="5" height="5">&nbsp;</td>
          <td width="369" height="5">&nbsp;</td>
          <td width="5" height="5">&nbsp;</td>
        </tr>
        <tr> 
          <td colspan="3">
            <div align="center"><font face="Tahoma, Arial, Garamond, Futura Lt BT, Futura XBlk BT, Futura Md BT, FuturaBlack BT" size="5"><b><font color="#0078BD">Por 
              favor int&eacute;ntelo m&aacute;s tarde</font></b></font></div>
          </td>
        </tr>
      </table>
    </td>
  </tr>
</table>
</BODY>
</HTML>
