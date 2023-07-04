<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.0 Transitional//EN"> 
<%@ page import="datapro.eibs.master.*" %>
<html>
<head>
<LINK HREF="<%=request.getContextPath()%>/pages/style.css" REL="stylesheet">
<title>Warnings</title>
<META HTTP-EQUIV="Content-Type" CONTENT="text/html; charset=ISO-8859-1">
<META name="GENERATOR" content="IBM WebSphere Page Designer V4.0 for Windows">
<META http-equiv="Content-Style-Type" content="text/css">
<jsp:useBean id= "msgCD" class= "datapro.eibs.beans.ECD0003DSMessage"  scope="session" />
<script language="Javascript1.1" src="<%=request.getContextPath()%>/pages/s/javascripts/eIBS.jsp"> </SCRIPT>

<SCRIPT LANGUAGE="JavaScript">
setTimeout("top.close()", <%= datapro.eibs.master.JSEIBSProp.getPopUpTimeOut() %>)

</SCRIPT>

</head>


<%
response.setHeader("Pragma", "No-cache");
response.setDateHeader ("Expires", 0);
response.setHeader("Cache-Control", "no-cache");
%>


<body>

<table class="tbenter" align=center height="72%" >
  <TR valign=top height="2%">
    <TD>
	<img src="<%=request.getContextPath()%>/images/e_ibs.gif" alt="ECD0003_plastic_inv_stock_warning_viewer">
	</TD>
    <TD align=center valign=top>
      <table class="tbenter">
        <tr>
          <td width="75" align="center"> 
			<img src="<%=request.getContextPath()%>/images/warning.gif" >
		  </td>
          <td align="center">
            <font color="red" face="Arial, Helvetica, sans-serif" size="3"><b>Alertas</b></font>
          </td>
          <td width="75" align="center">
			<img src="<%=request.getContextPath()%>/images/warning.gif">
		  </td>
        </tr>
      </table>
   </TD>
 </TR>
 <TR valign=top height="2%">
	<td nowrap><IMG border="0" src="<%=request.getContextPath()%>/images/warning01.gif"></td>
	<td nowrap>Realizar Solicitud de Plástico a Oficina Principal</td>
 </TR>
 
</table>
<p align="center">
	<input id="EIBSBTN" type=submit name="Submit" value="Cerrar" onclick="top.close()">
</p>

</body>
</html>

