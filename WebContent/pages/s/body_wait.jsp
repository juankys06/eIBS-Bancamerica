<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.0 Transitional//EN">
<HTML>
<HEAD>
<META name="GENERATOR" content="IBM WebSphere Page Designer V3.5.2 for Windows">
<META http-equiv="Content-Style-Type" content="text/css">
<TITLE>Internet Banking System: Please Wait</TITLE>
<link Href="<%=request.getContextPath()%>/pages/style.css" rel="stylesheet">
 
</HEAD>

<BODY onLoad="document.forms[0].submit()">
<FORM METHOD=POST>

<SCRIPT> document.forms[0].action="<%= request.getParameter("ServletName")%>" </SCRIPT> 

<%
		// Here you should initialize the session objects
		// Begin
		
		// 1. Get session object
		session = (javax.servlet.http.HttpSession) request.getSession(false);

		if (session == null || session.getAttribute("currUser") == null) {
			out.println("<SCRIPT LANGUAGE=\"JavaScript\">");
			out.println("		alert('eIBS Message\\n" + datapro.eibs.master.JSEIBSProp.getSignOn() + "');");
			out.println("		top.close();");
			out.println("</SCRIPT>");
		} else {
			// 2. Get every object that you want to keep
			datapro.eibs.beans.ESS0030DSMessage  msgUser = (datapro.eibs.beans.ESS0030DSMessage) session.getAttribute("currUser");
			datapro.eibs.beans.UserPos userPO = (datapro.eibs.beans.UserPos) session.getAttribute("userPO");
			datapro.eibs.beans.ESD080001Message currClient = (datapro.eibs.beans.ESD080001Message) session.getAttribute("currClient");
			// 3. Clean session object
			session.invalidate();
			session = (javax.servlet.http.HttpSession) request.getSession(true);
			
			// 4. Put back objects into session
			session.setAttribute("currUser", msgUser);
			session.setAttribute("userPO", userPO);
			if (currClient != null) session.setAttribute("currClient", currClient);
			// End 
		}
%>

<%
java.util.Enumeration enu = request.getParameterNames();
String parname = "";
String value = "";
while (enu.hasMoreElements()) {
	parname = (String)enu.nextElement();
	try {
		value = request.getParameter(parname);
		if (value != null) {
%>
			<INPUT TYPE=HIDDEN NAME="<%= parname %>" VALUE="<%= value %>">
<%
		}
	}
		catch (Exception e) {
	}	
}
%>
<table class=tbenter border="0" cellspacing="0" cellpadding="0" style="background-repeat:no-repeat; background-attachement:fixed;" align="center" width="100%" vspace="0" hspace="0" height="100%">
  <tr valign="middle" align="center"> 
    <td> 
      <table width="350" border="0" cellspacing="0" cellpadding="0" align="center">
        <tr> 
          <td height="50" width="51">&nbsp;</td>
          <td width="258" height="3"> 
            <div align="center"><img src="<%=request.getContextPath()%>/images/search.gif" width="65" height="65"></div>
          </td>
          <td width="41" height="3"><font size="4"></font></td>
        </tr>
        <tr> 
          <td height="50" width="51"> 
            <div align="right"><img src="<%=request.getContextPath()%>/images/wait.gif" width="30" height="30"></div>
          </td>
          <td width="258" height="32"> 
            <div align="center"><font face="Tahoma, Arial, Garamond, Futura Lt BT, Futura XBlk BT, Futura Md BT, FuturaBlack BT" size="4" color="#000099"><b><font color="#0066CC" size="5">Por 
              Favor espere</font></b></font></div>
          </td>
          <td width="41" height="32"><img src="<%=request.getContextPath()%>/images/wait.gif" width="30" height="30"></td>
        </tr>
        <tr> 
          <td colspan="3" height="50"> 
            <div align="center"><font face="Tahoma, Arial, Garamond, Futura Lt BT, Futura XBlk BT, Futura Md BT, FuturaBlack BT" size="4" color="#000099"><b><font color="#0066CC" size="3">El 
              Sistema est&aacute; preparando la opci&oacute;n seleccionada</font> </b></font></div>
          </td>
        </tr>
      </table>
      <div align="center"> </div>
    </td>
  </tr>
</table>

</FORM>
</BODY>
</HTML>
 