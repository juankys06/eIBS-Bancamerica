<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.0 Transitional//EN">
<html>
<head>
<META HTTP-EQUIV="Content-Type" CONTENT="text/html; charset=ISO-8859-1">
<META name="GENERATOR" content="IBM WebSphere Page Designer V3.5.2 for Windows">
<META http-equiv="Content-Style-Type" content="text/css">
<link Href="<%=request.getContextPath()%>/pages/style.css" rel="stylesheet">

<script language="Javascript1.1" src="<%=request.getContextPath()%>/pages/s/javascripts/eIBS.jsp"> </SCRIPT>

<jsp:useBean id="error" class="datapro.eibs.beans.ELEERRMessage" scope="session" />

<%
if (!error.getERRNUM().equals("0")) {

	error.setERRNUM("0");
	out.println("<SCRIPT Language=\"Javascript\">");
	out.println("       showErrors()");
	out.println("</SCRIPT>");

}

%>

</head>

<body bgcolor="#FFFFFF">

<H3 align="center">Importaci&oacute;n de Im&aacute;genes<img src="<%=request.getContextPath()%>/images/e_ibs.gif" align="left" name="EIBS_GIF" ALT="ExportImagesEnter.jsp"></H3>
<hr size="4">

<form method="post" action="<%=request.getContextPath()%>/servlet/datapro.eibs.tools.JSImagesExchange" >

<INPUT TYPE=HIDDEN NAME="SCREEN" VALUE="201">

<TABLE width="40%" border="0" class="tbenter" align="center">
	<TBODY>
		<TR align="center" >
			<TD>
			    DIA:
        		<INPUT type="text" name="DAY" size="4" maxlength="2" value=<%= request.getAttribute("TODAY_DAY") %> >
			</TD>
			<TD>
		        MES:
        		<INPUT type="text" name="MONTH" size="4" maxlength="2" value=<%= request.getAttribute("TODAY_MON") %>>
			</TD>
			<TD>
		        AÑO:
        		<INPUT type="text" name="YEAR" size="6" maxlength="4" value=<%= request.getAttribute("TODAY_YEA") %>>
			</TD>
		</TR>
	</TBODY>
</TABLE>

<table class="tbenter" width="100%" border="0">
    <tr> 
      <td nowrap ALIGN=CENTER> 
		 <div align="center"> 
			   <input id="EIBSBTN" type=submit name="Submit" value="Aceptar">
		  </div>     
      </td>
    </tr>
</table>
 
</form>
</body>
</html>

 