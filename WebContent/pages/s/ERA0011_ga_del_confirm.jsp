<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.0 Transitional//EN">

<%@ page import = "datapro.eibs.master.Util" %>
<html>
<head>
<title>Assign Accounts to Cards Confirmation</title>
<META HTTP-EQUIV="Content-Type" CONTENT="text/html; charset=ISO-8859-1">
<!--meta http-equiv="Refresh"CONTENT="7;url='<%=request.getContextPath()%>/servlet/datapro.eibs.products.JSECC0010?SCREEN=3'"-->
<META name="GENERATOR" content="IBM WebSphere Page Designer V3.5.2 for Windows">
<META http-equiv="Content-Style-Type" content="text/css">
<link Href="<%=request.getContextPath()%>/pages/style.css" rel="stylesheet">


<jsp:useBean id="gaMant" class= "datapro.eibs.beans.ERA001101Message"  scope="session"/>
<jsp:useBean id= "error" class= "datapro.eibs.beans.ELEERRMessage"  scope="session" />
<jsp:useBean id= "userPO" class= "datapro.eibs.beans.UserPos"  scope="session" />

<script language="Javascript1.1" src="<%=request.getContextPath()%>/pages/s/javascripts/eIBS.jsp"> </SCRIPT>

<SCRIPT LANGUAGE="javascript">

function finish() {
	self.window.location.href = "<%=request.getContextPath()%>/pages/background.jsp";
}

setTimeout("document.forms[0].submit();", 7000);
 
</SCRIPT>

</head>

<body>
<% 
 if ( !error.getERRNUM().equals("0")  ) {
 	 error.setERRNUM("0");
    out.println("<SCRIPT Language=\"Javascript\">");
     out.println("       showErrors()");
     out.println("</SCRIPT>");
 } 
%> 
<h3 align="center">Confirmacion de asignacion de cuentas a tarjetas<img src="<%=request.getContextPath()%>/images/e_ibs.gif" align="left" name="EIBS_GIF" ALT="ga_del_confirm.jsp,ERA0011"></h3> 
<hr size="4">
<FORM METHOD="post" action="<%=request.getContextPath()%>/servlet/datapro.eibs.client.JSERA0011" >
<INPUT TYPE=HIDDEN NAME="SCREEN" VALUE="100">

<table class="tableinfo">
	<tr > 
      	<td> 
        	<p align="center">&nbsp; </p>
    		<table width="100%">
      			<tr>
        			<td width="9%">&nbsp;</td>
        			<td width="91%"><div align="center"> </div></td>
      			</tr>
      			<tr>
        			<td width="9%">&nbsp;</td>
        			<td width="91%">&nbsp;</td>
      			</tr>
      			<tr>
        			<td width="9%">&nbsp;</td>
        			<td width="91%">Referencia : <%= gaMant.getE01ROCREF().trim() %></td>
      			</tr>
      			<tr>
        			<td width="9%">&nbsp;</td>
        			<td width="91%">&nbsp;</td>
      			</tr>
      			<tr>
        			<td width="9%">&nbsp;</td>
        			<td width="91%"></td>
      			</tr>
      			<tr>
        			<td width="9%">&nbsp;</td>
        			<td width="91%"></td>
      			</tr>
      			<tr>
        			<td width="9%">&nbsp;</td>
        			<td width="91%"><div align="center">Ha sido eliminada exitosamente y esta en espera de su aprobación</div></td>
      			</tr>
       		</table>
   		</td>
   	</tr>
</table>

<p align="center">&nbsp; </p>
<div align="center"> </div>
</form>
</body>
</html>


