<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<HTML><HEAD>
<META HTTP-EQUIV="Content-Type" CONTENT="text/html; charset=ISO-8859-1"> 

<META name="GENERATOR" content="IBM WebSphere Studio">
<TITLE>Mantenimiento  de Garantias</TITLE>
<link href="<%=request.getContextPath()%>/pages/style.css" rel="stylesheet">

<script language="Javascript1.1" src="<%=request.getContextPath()%>/pages/s/javascripts/eIBS.jsp"> </SCRIPT>

</HEAD>


<jsp:useBean id= "error" class= "datapro.eibs.beans.ELEERRMessage"  scope="session" />
<jsp:useBean id= "gaMant" class= "datapro.eibs.beans.ERA001101Message"  scope="session"/>

<body bgcolor="#FFFFFF">

 <% if ( !error.getERRNUM().equals("0")  ) {
     error.setERRNUM("0");
     out.println("<SCRIPT Language=\"Javascript\">");
     out.println("       showErrors()");
     out.println("</SCRIPT>");
 }
 %>

<h3 align="center">Mantenimiento de Garant&iacute;as<img src="<%=request.getContextPath()%>/images/e_ibs.gif" align="left" name="EIBS_GIF" ALT="ERA0011_ga_enter_maint.jsp"></h3>
<hr>
<FORM METHOD="post" ACTION="<%=request.getContextPath()%>/servlet/datapro.eibs.client.JSERA0011" >
	<CENTER>
    	<INPUT TYPE=HIDDEN NAME="SCREEN" VALUE="200">
    	<table cellspacing="0" cellpadding="2" class="tbenter" border=0   width=70% align="center" height="80%">
      		<tr valign="middle"> 
        		<td nowrap colspan="2" align="justify" height="120">&nbsp;</td>
      		</tr>
      		<tr valign="middle"> 
        		<td nowrap width=40% align="right" height="17" >Ingrese el N&uacute;mero de Garantía :</td>
        		<td nowrap width=40% align="left" height="17" > 
          			<input type=TEXT name="REF" value="<%= gaMant.getE01ROCREF() %>" size=15 maxlength=12>
          			<a href="javascript:GetAccount('REF','','91','')"><img src="<%=request.getContextPath()%>/images/1b.gif" alt=". . ." align="middle" border="0" ></a> 
          			&nbsp; 
          		</td>
      		</tr>
      		<tr> 
        		<td nowrap colspan="2" valign="middle" height="120"> 
          			<p align="center"><input id="EIBSBTN" type=submit name="Submit" value="Enviar"></p>
        		</td>
      		</tr>
      		<tr> 
        		<td nowrap colspan="2" valign="middle">&nbsp;</td>
      		</tr>
    	</table>
		<script language="JavaScript">
  			document.forms[0].REF.focus();
  			document.forms[0].REF.select();
		</script>
  	</CENTER>
</FORM>

</BODY>
</HTML>
 