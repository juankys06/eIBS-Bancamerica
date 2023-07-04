<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.0 Transitional//EN">
<%@ page import="datapro.eibs.master.*" %>
<html>
<head>
<LINK HREF="<%=request.getContextPath()%>/pages/style.css" REL="stylesheet">
<title>Errores - Advertencias</title>
<META HTTP-EQUIV="Content-Type" CONTENT="text/html; charset=ISO-8859-1">
<META name="GENERATOR" content="IBM WebSphere Page Designer V3.5.2 for Windows">
<META http-equiv="Content-Style-Type" content="text/css">
<jsp:useBean id="error" class="datapro.eibs.beans.ELEERRMessage"  scope="session" />

<script language="Javascript1.1" src="<%=request.getContextPath()%>/pages/s/javascripts/eIBS.jsp"> </SCRIPT>

<SCRIPT LANGUAGE="JavaScript">
setTimeout("top.close()", <%= datapro.eibs.master.JSEIBSProp.getPopUpTimeOut() %>)

function setFocus(focusItem, focusRow) {
var form = top.opener.document.forms[0];
 if ( focusItem == "" ) {
	return;
 }
 if ( !(focusRow == "" || focusRow == "0") ) {
	focusRow--;
       focusItem = focusItem + "_" + focusRow;
 }
 focusItem = focusItem.toUpperCase();
 if(setFieldFocus(form, focusItem) == false){
 	setFieldFocus(form, 'D' + focusItem.substring(1));
 }
}

function setFieldFocus(form, focusItem) {
 
 try {
  var lenItem = form.item(focusItem).length;
  
  if (lenItem != null) {
    if ( form.item(focusItem).tagName == "SELECT" ) {       
    	form[focusItem].focus();
    }
    else {
   	  if ( form[focusItem][0].type == "radio") {
     	for (var i=0;i < lenItem; i++) {
       		if (form[focusItem][i].checked) {
         		form[focusItem][i].focus();
         		form[focusItem][i].select();
         		break;
       		}
     	}
   	  } else if ( form[focusItem][0].type !== "hidden" ) {form[focusItem][0].focus();}
   	}
  } 
  else {
    if ( form[focusItem].type !== "hidden" ) {   
     form[focusItem].focus();
     if ( form[focusItem].type == "text" ||  form[focusItem].type == "textarea" ) { form[focusItem].select(); }
    } else {
     return false;//false if it is hidden
    }
  }
  return true;//true otherwise
 }
 catch (e) {
  return false;//false if an error
 }
}

</SCRIPT>

</head>


<%
response.setHeader("Pragma", "No-cache");
response.setDateHeader ("Expires", 0);
response.setHeader("Cache-Control", "no-cache");
%>

<%
  if ( JSEIBSProp.getVoiceActive() ) {
%>
<body onLoad="SayThis('phrase_01.au')">
<APPLET archive="eibs_applets.zip" code="datapro.eibs.applets.speak.AMySpeaker.class" name="Speaker" width="0" height="0"   CODEBASE="<%=request.getContextPath()%>/pages/s/">
</APPLET>
<%
}
else {
%>
<body>
<%
}
%>
<table id="tbhelp" width=100% align=center>
<tr>
    <td>
	<img src="<%=request.getContextPath()%>/images/e_ibs.gif" align="left" name="EIBS_GIF" ALT="error_viewer">
    </td>
    <td align=center colspan=2 >
      
      <table width="100%" border="0" cellspacing="2" cellpadding="2" class="tbenter">
        <tr>
          <td width="75"> 
            <div align="right"><font color="red"><img src="<%=request.getContextPath()%>/images/warning.gif"></font></div>
          </td>
          <td>
            <div align="center" nowrap><font color="#FF3333" face="Arial, Helvetica, sans-serif" size="3"><b>Errores - Advertencias</b></font></div>
          </td>
          <td width="75"><font color="red"><img src="<%=request.getContextPath()%>/images/warning.gif"></font></td>
        </tr>
      </table>
      
      <br>
    
   </td>
</tr>
<tr><td colspan=3>&nbsp;</td></tr>

<tr>
  <td>
	<% 
	if (!error.getERDF01().equals("")) {
		if (error.getERWF01().equals("Y")) { 
	%>
	<IMG src="<%=request.getContextPath()%>/images/warning01.gif" alt="Warning!!!">
	<% 
		} 
		else { 
	%>
	<IMG src="<%=request.getContextPath()%>/images/error01.gif" alt="Error!!!">
	<% 
		} 
	}
	%>
  </td>
  <td width=30><a href="javascript:setFocus('<%= error.getERDF01().trim() %>', <%= error.getERDR01().trim() %>)"><b><%= error.getERNU01() %></b></a></td>
  <td><a href="javascript:setFocus('<%= error.getERDF01().trim() %>', <%= error.getERDR01().trim() %>)"><%= error.getERDS01() %></a></td>
</tr>

<tr>
  <td>
	<% 
	if (!error.getERDF02().equals("")) {
		if (error.getERWF02().equals("Y")) { 
	%>
	<IMG src="<%=request.getContextPath()%>/images/warning01.gif" alt="Warning!!!">
	<% 
		} 
		else { 
	%>
	<IMG src="<%=request.getContextPath()%>/images/error01.gif" alt="Error!!!">
	<% 
		} 
	}
	%>
  </td>
  <td width=30><a href="javascript:setFocus('<%= error.getERDF02().trim() %>', <%= error.getERDR02().trim() %>)"><b><%= error.getERNU02() %></b></a></td>
  <td><a href="javascript:setFocus('<%= error.getERDF02().trim() %>', <%= error.getERDR02().trim() %>)"><%= error.getERDS02() %></a></td>
</tr>

<tr>
  <td>
	<% 
	if (!error.getERDF03().equals("")) {
		if (error.getERWF03().equals("Y")) { 
	%>
	<IMG src="<%=request.getContextPath()%>/images/warning01.gif" alt="Warning!!!">
	<% 
		} 
		else { 
	%>
	<IMG src="<%=request.getContextPath()%>/images/error01.gif" alt="Error!!!">
	<% 
		} 
	}
	%>
  </td>
  <td width=30><a href="javascript:setFocus('<%= error.getERDF03().trim() %>', <%= error.getERDR03().trim() %>)"><b><%= error.getERNU03() %></b></a></td>
  <td><a href="javascript:setFocus('<%= error.getERDF03().trim() %>', <%= error.getERDR03().trim() %>)"><%= error.getERDS03() %></a></td>
</tr>

<tr>
  <td>
	<% 
	if (!error.getERDF04().equals("")) {
		if (error.getERWF04().equals("Y")) { 
	%>
	<IMG src="<%=request.getContextPath()%>/images/warning01.gif" alt="Warning!!!">
	<% 
		} 
		else { 
	%>
	<IMG src="<%=request.getContextPath()%>/images/error01.gif" alt="Error!!!">
	<% 
		} 
	}
	%>
  </td>
  <td width=30><a href="javascript:setFocus('<%= error.getERDF04().trim() %>', <%= error.getERDR04().trim() %>)"><b><%= error.getERNU04() %></b></a></td>
  <td><a href="javascript:setFocus('<%= error.getERDF04().trim() %>', <%= error.getERDR04().trim() %>)"><%= error.getERDS04() %></a></td>
</tr>

<tr>
  <td>
	<% 
	if (!error.getERDF05().equals("")) {
		if (error.getERWF05().equals("Y")) { 
	%>
	<IMG src="<%=request.getContextPath()%>/images/warning01.gif" alt="Warning!!!">
	<% 
		} 
		else { 
	%>
	<IMG src="<%=request.getContextPath()%>/images/error01.gif" alt="Error!!!">
	<% 
		} 
	}
	%>
  </td>
  <td width=30><a href="javascript:setFocus('<%= error.getERDF05().trim() %>', <%= error.getERDR05().trim() %>)"><b><%= error.getERNU05() %></b></a></td>
  <td><a href="javascript:setFocus('<%= error.getERDF05().trim() %>', '<%= error.getERDR05().trim() %>')"><%= error.getERDS05() %></a></td>
</tr>

<tr>
  <td>
	<% 
	if (!error.getERDF06().equals("")) {
		if (error.getERWF06().equals("Y")) { 
	%>
	<IMG src="<%=request.getContextPath()%>/images/warning01.gif" alt="Warning!!!">
	<% 
		} 
		else { 
	%>
	<IMG src="<%=request.getContextPath()%>/images/error01.gif" alt="Error!!!">
	<% 
		} 
	}
	%>
  </td>
  <td width=30><a href="javascript:setFocus('<%= error.getERDF06().trim() %>', <%= error.getERDR06().trim() %>)"><b><%= error.getERNU06() %></b></a></td>
  <td><a href="javascript:setFocus('<%= error.getERDF06().trim() %>', <%= error.getERDR06().trim() %>)"><%= error.getERDS06() %></a></td>
</tr>

<tr>
  <td>
	<% 
	if (!error.getERDF07().equals("")) {
		if (error.getERWF07().equals("Y")) { 
	%>
	<IMG src="<%=request.getContextPath()%>/images/warning01.gif" alt="Warning!!!">
	<% 
		} 
		else { 
	%>
	<IMG src="<%=request.getContextPath()%>/images/error01.gif" alt="Error!!!">
	<% 
		} 
	}
	%>
  </td>
  <td width=30><a href="javascript:setFocus('<%= error.getERDF07().trim() %>', <%= error.getERDR07().trim() %>)"><b><%= error.getERNU07() %></b></a></td>
  <td><a href="javascript:setFocus('<%= error.getERDF07().trim() %>', <%= error.getERDR07().trim() %>)"><%= error.getERDS07() %></a></td>
</tr>

<tr>
  <td>
	<% 
	if (!error.getERDF08().equals("")) {
		if (error.getERWF08().equals("Y")) { 
	%>
	<IMG src="<%=request.getContextPath()%>/images/warning01.gif" alt="Warning!!!">
	<% 
		} 
		else { 
	%>
	<IMG src="<%=request.getContextPath()%>/images/error01.gif" alt="Error!!!">
	<% 
		} 
	}
	%>
  </td>
  <td width=30><a href="javascript:setFocus('<%= error.getERDF08().trim() %>', <%= error.getERDR08().trim() %>)"><b><%= error.getERNU08() %></b></a></td>
  <td><a href="javascript:setFocus('<%= error.getERDF08().trim() %>', <%= error.getERDR08().trim() %>)"><%= error.getERDS08() %></a></td>
</tr>

<tr>
  <td>
	<% 
	if (!error.getERDF09().equals("")) {
		if (error.getERWF09().equals("Y")) { 
	%>
	<IMG src="<%=request.getContextPath()%>/images/warning01.gif" alt="Warning!!!">
	<% 
		} 
		else { 
	%>
	<IMG src="<%=request.getContextPath()%>/images/error01.gif" alt="Error!!!">
	<% 
		} 
	}
	%>
  </td>
  <td width=30><a href="javascript:setFocus('<%= error.getERDF09().trim() %>', <%= error.getERDR09().trim() %>)"><b><%= error.getERNU09() %></b></a></td>
  <td><a href="javascript:setFocus('<%= error.getERDF09().trim() %>', <%= error.getERDR09().trim() %>)"><%= error.getERDS09() %></a></td>
</tr>

<tr>
  <td>
	<% 
	if (!error.getERDF10().equals("")) {
		if (error.getERWF10().equals("Y")) { 
	%>
	<IMG src="<%=request.getContextPath()%>/images/warning01.gif" alt="Warning!!!">
	<% 
		} 
		else { 
	%>
	<IMG src="<%=request.getContextPath()%>/images/error01.gif" alt="Error!!!">
	<% 
		} 
	}
	%>
  </td>
  <td width=30><a href="javascript:setFocus('<%= error.getERDF10().trim() %>', <%= error.getERDR10().trim() %>)"><b><%= error.getERNU10() %></b></a></td>
  <td><a href="javascript:setFocus('<%= error.getERDF10().trim() %>', <%= error.getERDR10().trim() %>)"><%= error.getERDS10() %></a></td>
</tr>

</table>

<SCRIPT LANGUAGE="JavaScript" >
	// Positioning at first Error
	// setFocus('<%= error.getERDF01().trim() %>');
	self.focus();
</SCRIPT>
</body>
</html>

