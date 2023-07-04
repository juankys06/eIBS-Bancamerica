<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.0 Transitional//EN">

<%@ page import = "datapro.eibs.master.Util" %>
<html>
<head>
<title>Confirmation</title>
<META HTTP-EQUIV="Content-Type" CONTENT="text/html; charset=ISO-8859-1">
<meta http-equiv="Refresh" CONTENT="3;url='javascript:reCall()'">
<META name="GENERATOR" content="IBM WebSphere Page Designer V3.5.2 for Windows">
<META http-equiv="Content-Style-Type" content="text/css">

<link Href="<%=request.getContextPath()%>/pages/style.css" rel="stylesheet">






<jsp:useBean id= "error" class= "datapro.eibs.beans.ELEERRMessage"  scope="session" />

<jsp:useBean id= "userPO" class= "datapro.eibs.beans.UserPos"  scope="session" />

<script language="Javascript1.1" src="<%=request.getContextPath()%>/pages/s/javascripts/eIBS.jsp"> </SCRIPT>
<script language="Javascript1.1" src="<%=request.getContextPath()%>/pages/s/javascripts/optMenu.jsp"> </SCRIPT>

<SCRIPT Languaje="javascript">
function reCall() {
  	top.opener.location.href = "<%=request.getContextPath()%>/servlet/datapro.eibs.transfer.JSEWD0341F?SCREEN=1";
	top.close();
}</SCRIPT>

</head>
<body nowrap >
<% 
 if ( !error.getERRNUM().equals("0")  ) {
     error.setERRNUM("0");
     out.println("<SCRIPT Language=\"Javascript\">");
     out.println("       showErrors()");
     out.println("</SCRIPT>");
     }

%> 
<h3 align="center"> Reparaci&oacute;n de Mensaje de Swift <img src="<%=request.getContextPath()%>/images/e_ibs.gif" align="left" name="EIBS_GIF" alt="generic_conf.jsp,ESWF100"> 
</h3>
<hr size="4">
<form method="post" action="<%=request.getContextPath()%>/servlet/datapro.eibs.transfer.JSEWD0341F" >
  <p>
    <input type=HIDDEN name="SCREEN" value="7">
  </p>
  <p>&nbsp; </p>
  <table cellspacing="0" cellpadding="2" class="tbenter" border=0   width=70% align="center" height="80%">
    <tr valign="middle"> 
      <td nowrap colspan="2" align="middle" height="131">&nbsp;</td>
    </tr>
    <tr>
      <td nowrap colspan="2" valign="middle">
        <div align="center">El Mensaje de Swift con el n&uacute;mero de referencia 
          <%= userPO.getIdentifier()%> ha sido reparado</div>
      </td>
    </tr>
    <tr> 
      <td nowrap colspan="2" valign="middle"> 
        <div align="center"></div>
      </td>
    </tr>
  </table>
  <p>&nbsp;</p>
  <BR>

  </form>
<p>&nbsp;</p>
</body>
</html>
