<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.0 Transitional//EN">
<html>
<head>
<title>Autorización de Accesos a Códigos de Referencia</title>
<META HTTP-EQUIV="Content-Type" CONTENT="text/html; charset=ISO-8859-1">
<META name="GENERATOR" content="IBM WebSphere Page Designer V3.5.2 for Windows">
<META http-equiv="Content-Style-Type" content="text/css">
<link Href="<%=request.getContextPath()%>/pages/style.css" rel="stylesheet">

<jsp:useBean id="user" class="datapro.eibs.beans.ESD003501Message"  scope="session" />
<jsp:useBean id= "error" class= "datapro.eibs.beans.ELEERRMessage"  scope="session" />
<jsp:useBean id= "userPO" class= "datapro.eibs.beans.UserPos"  scope="session" />

<script language="Javascript1.1" src="<%=request.getContextPath()%>/pages/s/javascripts/eIBS.jsp"> </SCRIPT>
 <% 
 if ( !error.getERRNUM().equals("0")  ) {
 	 error.setERRNUM("0");
     out.println("<SCRIPT Language=\"Javascript\">");
     out.println("       showErrors()");
     out.println("</SCRIPT>");
 }
%>

</head>


<body bgcolor="#FFFFFF">

<H3 align="center">Autorización de Accesos a Códigos de Referencia<img src="<%=request.getContextPath()%>/images/e_ibs.gif" align="left" name="EIBS_GIF" ALT="ref_codes_user_access_enter.jsp, ESD0035"></H3>

<hr size="4">
<form method="post" action="<%=request.getContextPath()%>/servlet/datapro.eibs.security.JSESD0035">
  <p> 
    <INPUT TYPE=HIDDEN NAME="SCREEN" VALUE="3">
  </p>

  <table class="tableinfo">
    <tr > 
      <td nowrap> 
        <TABLE cellspacing="0" cellpadding="2" width="100%" border="0">
		    <tr id="trdark"> 
		      <td nowrap width="50%"> 
		      	<div align="right">Usuario :</div>
		      </td> 
		      <td nowrap width="50%"> 
		        <INPUT type="text" name="E01CREUSR" size="12" maxlength="10" value="<%= user.getE01CREUSR().trim()%>">
		        <a href="javascript:GetUser('E01CREUSR','','')"><img src="<%=request.getContextPath()%>/images/1b.gif" alt="help" align="absbottom" border="0"></a> 
		      </td>
		    </tr> 
		</TABLE>
      </td>
    </tr>
  </table>
 
 <p align="center"> 
    <input id="EIBSBTN" type=submit name="Submit" value="Enviar" >
  </p>  
 
<% 
 if ( !error.getERRNUM().equals("0")  ) {
      error.setERRNUM("0");
 %>
     <SCRIPT Language="Javascript">;
            showErrors();
     </SCRIPT>
  <%
 }
%> 
</form>
</body>
</html>
