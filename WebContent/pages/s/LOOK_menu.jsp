<HTML>
<HEAD>
<meta http-equiv=Content-Type content="text/html;  charset=ISO-8859-1">
<TITLE>options</TITLE>
<%@ page import = "datapro.eibs.master.Util" %>

<jsp:useBean id= "currUser" class= "datapro.eibs.beans.ESS0030DSMessage"   scope="session"/>

<SCRIPT SRC="<%=request.getContextPath()%>/pages/s/javascripts/DynWrite.js"> </SCRIPT>

<SCRIPT language="JavaScript">
function ShowNow() {
 with (new Date()) { 
 	var Q=getTime();
 	Timer = setTimeout("ShowNow()", 1000-Q%1000);

	var h 	=  getHours();
	var m 	=  getMinutes();
	var s 	=  getSeconds();
	var sufix = '';
	
	if (h == 0) {
	  h = 12;
	  sufix = 'AM';
	} else if (h == 12) {
	  h = 12;
	  sufix = 'M';
	} else if (h > 12) {
	  h -= 12;
	  sufix = 'PM';
	} else {
	  sufix = 'AM';
	}

  	var St = '&nbsp;';
	St += h < 10 ? "0" + h  : h;
	St += ':';
	St += m < 10 ? "0" + m  : m;
	St += ':';
	St += s < 10 ? "0" + s  : s;
	St += '&nbsp;';
	St += sufix;

  	// var St = '&nbsp;' + getHours() + ':' + getMinutes() + ':' + getSeconds();
 	DynWrite('RunClock', St);
 }
}

</SCRIPT>

</HEAD>
<BODY bgcolor="#FFFFFF" leftmargin="0" topmargin="0" marginwidth="0" marginheight="0" onload="ShowNow();">
<table width="161" border="0" align="right" cellspacing="0" cellpadding="0">
  <tr>
    <td colspan=2 width="161" align="right" height="50" valign="bottom"><img src="<%=request.getContextPath()%>/images/s/options_up.gif" width="161" height="69" vspace="0" hspace="0" border="0"></td>
  </tr>
  <tr>
    <td nowrap width="81" align="left" height="24" valign="top"><font face="Verdana, Arial, Helvetica, sans-serif" size="1pt" color="#003399">
      <div ID=RunClock></div>
      </font> 
    </td>
    <td nowrap width="80" align="right" height="24" valign="top"><font face="Verdana, Arial, Helvetica, sans-serif" size="1pt" color="#003399">
<%
	if ( currUser.getE01DTF().equals("MDY") ) {
		out.print(Util.formatDate(currUser.getE01RDM(), currUser.getE01RDD(), currUser.getE01RDY()));
	}
	else if ( currUser.getE01DTF().equals("DMY") ) {
		out.print(Util.formatDate(currUser.getE01RDD(), currUser.getE01RDM(), currUser.getE01RDY()));
	}
	else {
		out.print(Util.formatDate(currUser.getE01RDD(), currUser.getE01RDY(), currUser.getE01RDM()));
	}
%>
      &nbsp;&nbsp;
      </font> 
    </td>
  </tr>
  <tr>
    <td colspan=2 width="161" align="right" valign="top" height="389"><font face="Verdana, Arial, Helvetica, sans-serif" size="2" color="#000099"><object classid="clsid:D27CDB6E-AE6D-11cf-96B8-444553540000"
 codebase="http://download.macromedia.com/pub/shockwave/cabs/flash/swflash.cab#version=6,0,0,0"
 width="161" height="389" id="options" align="" vspace="0" hspace="0">
        <param name=movie value="flash/options.swf?MenuURL=<%=request.getContextPath()%>/servlet/datapro.eibs.menu.JSESS0040Flash">
        <param name=quality value=high>
        <param name=bgcolor value=#FFFFFF>
        <embed src="flash/options.swf?MenuURL=<%=request.getContextPath()%>/servlet/datapro.eibs.menu.JSESS0040Flash" quality=high bgcolor=#FFFFFF  width="161" height="389" vspace="0" align=""
 type="application/x-shockwave-flash" pluginspage="http://www.macromedia.com/go/getflashplayer" hspace="0">
        </embed> 
      </object></font></td>
  </tr>
</table>
<!-- URL's used in the movie-->
<!-- text used in the movie-->
<!--TEST CLIPSET NORMAL TITLE COLOR:SET mouseOver TITLE COLOR:(change tints to change color)-->
</BODY>
</HTML>
