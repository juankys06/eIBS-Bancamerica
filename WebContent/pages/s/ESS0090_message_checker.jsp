<%@ page import = "datapro.eibs.master.*" %>
<!-- Sample HTML file -->
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 3.2 //EN">
<HTML>
<HEAD>
<TITLE>
Chequeador de Mensajes
</TITLE>
<link href="<%=request.getContextPath()%>/pages/style.css" rel="stylesheet">
<STYLE type="text/css">
<!--
A:LINK {
	font-family: "Verdana, Arial, Helvetica, sans-serif";
	color : white;
	text-decoration : none;
	font-size : 8pt;
	font-style: normal;
}

A:VISITED {
	font-family: "Verdana, Arial, Helvetica, sans-serif";
	color : white;
	text-decoration : none;
	font-size : 8pt;
	font-style: normal;
}

A:HOVER {
	font-family: "Verdana, Arial, Helvetica, sans-serif";
	color : yellow;
	text-decoration : none;
	font-size : 8pt;
	font-style: normal;
}	
-->
</STYLE>
</HEAD>

<jsp:useBean  id="userPO" class="datapro.eibs.beans.UserPos"  scope="session" />

<SCRIPT SRC="<%=request.getContextPath()%>/pages/s/javascripts/eIBS.jsp"> </SCRIPT>

<SCRIPT LANGUAGE="JavaScript">
<!--
//    back
   function back() {
   	parent.main.history.back()
   }
      
//    forward
   
   function forward() {
	parent.main.history.forward()
   }
   
//    print
   
   function print_main() {
	parent.main.focus();parent.main.print()
   }

//    home
   
   function go_home() {
   	parent.main.window.location.href="<%=request.getContextPath()%>/pages/background.jsp"
   }
								
//    eIBS help
   
   function eIBS_help() { 
	CenterWindow("<%=request.getContextPath()%>/pages/s/WebHelp/eIBSHelp.jsp",650,550,2);
   } 
   

//    eIBS about

   function eIBS_about() {
	CenterWindow("<%=request.getContextPath()%>/pages/s/MISC_eIBS_about.jsp",340,340,2);
   }
    
//    Exit
   
   function log_out() {
    parent.main.window.location.href="<%=request.getContextPath()%>/servlet/datapro.eibs.menu.JSLogOff"
   }
   
   function showText(idx) {
     document.getElementById("divtext" + idx).style.display="";
	 if(document.getElementById("divtext" + idx).filters) {
	     document.getElementById("divtext" + idx).style.filter="blendTrans(duration=1)";
	     if (document.getElementById("divtext" + idx).filters.blendTrans.status != 2) {
	     	document.getElementById("divtext" + idx).filters.blendTrans.Apply();
	     	document.getElementById("divtext" + idx).style.visibility="visible";
	     	document.getElementById("divtext" + idx).filters.blendTrans.Play();
	    }
     } else document.getElementById("divtext" + idx).style.visibility="visible";
   }
   
   function clearText(idx) {
     document.getElementById("divtext" + idx).style.visibility="hidden";
     document.getElementById("divtext" + idx).style.display="none";   
   }
   
//-->
</SCRIPT>

<%
  if ( JSEIBSProp.getVoiceActive() && userPO.getThereIsMsg() ) {
%>
     <BODY bgcolor="#000066" leftmargin="0" topmargin="0" marginwidth="0" marginheight="0"onLoad="SayThis('msg_alert.au')">
<%
}
else {
%>
    <BODY bgcolor="#000066" leftmargin="0" topmargin="0" marginwidth="0" marginheight="0">
<%
}
%>
     <FORM METHOD="post" ACTION="<%=request.getContextPath()%>/servlet/datapro.eibs.products.JSESS0090">
       <INPUT TYPE="hidden" NAME="SCREEN" VALUE="3">
		<table border="0" cellspacing="0" cellpadding="0" width="100%" height="66" border=0>
  			<tr>
    			<td width="578" height="40" align="left" valign="top" style="background: url(<%=request.getContextPath()%>/images/s/topbar_1.jpg) no-repeat;">&nbsp;</td> 
            	<td width="171" colspan= 6 height="40" align="left" valign="top" style="background: url(<%=request.getContextPath()%>/images/s/topbar_3.jpg)">
            	   <div id="dMsg" valign="middle" style="color: white; font-size:8pt; padding:4;">
            	   	 <%
          			if (!userPO.getThereIsMsg() ) {          			
          			%>
          			 <IMG border="0" src="<%=request.getContextPath()%>/images/s/no_message.gif" align=absmiddle>
          			 &nbsp;
          			 <A href="javascript:document.forms[0].submit()"><b>NO HAY mensajes</b></A>
          			<%
          			}
          			else {
          			%> 
          			  <a href="javascript:showMsgViewer()"><IMG border="0" src="<%=request.getContextPath()%>/images/s/new_message.gif" align=absmiddle></a>
          			  &nbsp;<A href="javascript:document.forms[0].submit()"><b>NUEVO mensaje</b></A>
          			<%
          			}
          			%>
            	   </div>
            	</td> 
            	<td rowspan=2 background="<%=request.getContextPath()%>/images/s/topbar_background.jpg" height="66">&nbsp;</td>
            </tr>
            <tr>
				<td nowrap width="578" height="25" align=right valign="middle" style="background: url(<%=request.getContextPath()%>/images/s/topbar_2.jpg) no-repeat;">
					<div id="divtext1" style="display:none; visibility:hidden; color: yellow; font-size:8pt "><b>Imprimir  &nbsp;</b></div>
					<div id="divtext2" style="display:none; visibility:hidden; color: yellow; font-size:8pt"><b>Inicio  &nbsp;</b></div>
					<div id="divtext3" style="display:none; visibility:hidden; color: yellow; font-size:8pt"><b>Ayuda  &nbsp;</b></div>
					<div id="divtext4" style="display:none; visibility:hidden; color: yellow; font-size:8pt"><b>Acerca de  &nbsp;</b></div>
					<div id="divtext5" style="display:none; visibility:hidden; color: yellow; font-size:8pt"><b>Salir  &nbsp;</b></div>					
				</td>
    			<td nowrap width="25" height="25" style ="cursor:pointer;" align="left" valign="top" background="<%=request.getContextPath()%>/images/s/topbar_print.jpg" onmouseenter = "showText(1)" onmouseout = "clearText(1)" onclick="print_main()">&nbsp;</td>
    			<td nowrap width="26" height="25" style ="cursor:pointer;" align="left" valign="top" background="<%=request.getContextPath()%>/images/s/topbar_home.jpg" onmouseenter = "showText(2)" onmouseout = "clearText(2)" onclick="go_home()">&nbsp;</td>
    			<td nowrap width="17" height="25" style ="cursor:pointer;" align="left" valign="top" background="<%=request.getContextPath()%>/images/s/topbar_help.jpg" onmouseenter = "showText(3)" onmouseout = "clearText(3)" onclick="eIBS_help()">&nbsp;</td>
    			<td nowrap width="19" height="25" style ="cursor:pointer;" align="left" valign="top" background="<%=request.getContextPath()%>/images/s/topbar_about.jpg" onmouseenter = "showText(4)" onmouseout = "clearText(4)" onclick="eIBS_about()">&nbsp;</td>
    			<td nowrap width="23" height="25" style ="cursor:pointer;" align="left" valign="top" background="<%=request.getContextPath()%>/images/s/topbar_exit.jpg" onmouseenter = "showText(5)" onmouseout = "clearText(5)" onclick="log_out()">&nbsp;</td>
    		    <td nowrap width="61" height="25" align="left" valign="top" background="<%=request.getContextPath()%>/images/s/topbar_4.jpg">&nbsp;</td>    		
    		</tr>
		</table>
     </FORM>
    
     <APPLET archive="eibs_applets.zip" code="datapro.eibs.applets.speak.AMySpeaker.class" name="Speaker" width="0" height="0" codebase="<%=request.getContextPath()%>/pages/s/">
     </APPLET>
    </BODY>
</HTML>