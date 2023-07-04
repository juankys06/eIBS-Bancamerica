<html> 
<head>
<title>Consulta de Niveles de Ejecutivos</title>
<META HTTP-EQUIV="Content-Type" CONTENT="text/html; charset=ISO-8859-1">
<link Href="<%=request.getContextPath()%>/pages/style.css" rel="stylesheet">


<script language="Javascript1.1" src="<%=request.getContextPath()%>/pages/s/javascripts/eIBS.jsp"> </SCRIPT>

<jsp:useBean id= "error" class= "datapro.eibs.beans.ELEERRMessage"  scope="session" />
<jsp:useBean id= "msgMT" class= "datapro.eibs.beans.EEJ004001Message"  scope="session" />

</head>
<body>

<H3 align="center">Consulta de Niveles de Ejecutivos<img src="<%=request.getContextPath()%>/images/e_ibs.gif" align="left" name="EIBS_GIF" ALT="officers_levels_enter_inq,EEJ0040"></H3>
<hr size="4">
<p>&nbsp;</p>

<form method="post" action="<%=request.getContextPath()%>/servlet/datapro.eibs.params.JSEEJ0040" >
  <p> 
    <INPUT TYPE=HIDDEN NAME="SCREEN" VALUE="200">
  </p>
  <table class="tableinfo">
   <tr> 
   <td>
    <table cellspacing=0 cellpadding=2 width="100%" border="0">    
        <tr id=trdark> 
		  <td nowrap width="40%"> 
		     <div align="right">Ejecutivo : </div>        
		  </td>
      	  <td nowrap> 
      	    <input type="text" name="E01EJVOFC" size="5" maxlength="4" value="<%= msgMT.getE01EJVOFC()%>">
      	    <a href="javascript:GetCodeCNOFC('E01EJVOFC','15')"><img src="<%=request.getContextPath()%>/images/1b.gif" alt="Ayuda" align="bottom" border="0" ></a>
      	    <img src="<%=request.getContextPath()%>/images/Check.gif" alt="campo obligatorio" align="absbottom" border="0" width="16" height="20">
      	  </td>
      	</tr>
     </table>
    </td>
   </tr>
  </table>
  
  <p align="center"> 
    <input id="EIBSBTN" type=submit name="Submit" value="Enviar">
  </p>
  
	<script language="JavaScript">
	  document.forms[0].E01EJVOFC.focus();
	  document.forms[0].E01EJVOFC.select();
	</script>
<% 
 if ( !error.getERRNUM().equals("0")  ) {
      error.setERRNUM("0");
 %>
     <SCRIPT Language="Javascript">
            showErrors();
     </SCRIPT>
 <%
 }
%>
</form>
</body>
</html>
