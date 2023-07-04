<html> 
<head>
<title>Mantenimiento de Unidades de Agrupaci&oacute;n de Ejecutivos</title>
<META HTTP-EQUIV="Content-Type" CONTENT="text/html; charset=ISO-8859-1">
<link Href="<%=request.getContextPath()%>/pages/style.css" rel="stylesheet">


<script language="Javascript1.1" src="<%=request.getContextPath()%>/pages/s/javascripts/eIBS.jsp"> </SCRIPT>

<jsp:useBean id= "error" class= "datapro.eibs.beans.ELEERRMessage"  scope="session" />
<jsp:useBean id= "msgMT" class= "datapro.eibs.beans.EEJ002001Message"  scope="session" />
<jsp:useBean id= "currUser" class= "datapro.eibs.beans.ESS0030DSMessage"  scope="session" />

</head>
<body>

<H3 align="center">Mantenimiento de Unidades de Agrupaci&oacute;n de Ejecutivos<img src="<%=request.getContextPath()%>/images/e_ibs.gif" align="left" name="EIBS_GIF" ALT="params_group_units_enter_inq,EEJ0020"></H3>
<hr size="4">
<p>&nbsp;</p>

<form method="post" action="<%=request.getContextPath()%>/servlet/datapro.eibs.params.JSEEJ0020">
  <p> 
    <INPUT TYPE=HIDDEN NAME="SCREEN" VALUE="100">
  </p>
  <table class="tableinfo">
   <tr> 
   <td>
    <table cellspacing=0 cellpadding=2 width="100%" border="0">
    	<tr id=trdark> 
		  <td> 
		     <div align="right">C&oacute;digo de Ejecutivo : </div>        
		  </td>
      	  <td nowrap> 
      	    <input type="text" name="E01EJUEJE" size="12" maxlength="10" value="<%= msgMT.getE01EJUEJE()%>">
      	    <a href="javascript:GetOfficer('E01EJUEJE')"><img src="<%=request.getContextPath()%>/images/1b.gif" alt="Ayuda" align="absbottom" border="0" ></a>
      	  </td>     
      	</tr>    
     	<tr id=trclear> 
		  <td> 
		     <div align="right">Tipo de Agrupaci&oacute;n : </div>        
		  </td>
      	  <td nowrap> 
      	    <input type="text" name="E01EJUTYP" size="5" maxlength="4" value="<%= msgMT.getE01EJUTYP()%>">
      	    <a href="javascript:GetCodeCNOFC('E01EJUTYP','Y0')"><img src="<%=request.getContextPath()%>/images/1b.gif" alt="Ayuda" align="bottom" border="0" ></a>
      	  </td>     
      	</tr>
      	<tr id=trdark> 
		  <td> 
		     <div align="right">C&oacute;digo de Agrupaci&oacute;n : </div>        
		  </td>
      	  <td nowrap> 
      	    <input type="text" name="E01EJUCDE" size="5" maxlength="4" value="<%= msgMT.getE01EJUCDE()%>">
      	    <a href="javascript:GetCodeCNOFC('E01EJUCDE',document.forms[0].E01EJUTYP.value)"><img src="<%=request.getContextPath()%>/images/1b.gif" alt="Ayuda" align="bottom" border="0" ></a>
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
	  document.forms[0].E01EJUEJE.focus();
	  document.forms[0].E01EJUEJE.select();
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
