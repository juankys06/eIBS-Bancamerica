<html> 
<head>
<title>Mantenimiento de L&iacute;mites de Aprobaci&oacute;n de Ejecutivos</title>
<META HTTP-EQUIV="Content-Type" CONTENT="text/html; charset=ISO-8859-1">
<link Href="<%=request.getContextPath()%>/pages/style.css" rel="stylesheet">


<script language="Javascript1.1" src="<%=request.getContextPath()%>/pages/s/javascripts/eIBS.jsp"> </SCRIPT>

<jsp:useBean id= "error" class= "datapro.eibs.beans.ELEERRMessage"  scope="session" />
<jsp:useBean id= "msgMT" class= "datapro.eibs.beans.EEJ000001Message"  scope="session" />
<jsp:useBean id= "currUser" class= "datapro.eibs.beans.ESS0030DSMessage"  scope="session" />

</head>
<body>

<H3 align="center">Mantenimiento de L&iacute;mites de Aprobaci&oacute;n de Ejecutivos<img src="<%=request.getContextPath()%>/images/e_ibs.gif" align="left" name="EIBS_GIF" ALT="params_approv_limits_inq,EEJ0000"></H3>
<hr size="4">
<p>&nbsp;</p>

<form method="post" action="<%=request.getContextPath()%>/servlet/datapro.eibs.params.JSEEJ0000">
  <p> 
    <INPUT TYPE=HIDDEN NAME="SCREEN" VALUE="100">
  </p>
  <table class="tableinfo">
   <tr> 
   <td>
    <table cellspacing=0 cellpadding=2 width="100%" border="0">    
     	<tr id=trdark> 
		  <td nowrap width="40%"> 
		     <div align="right">Tipo de Agrupaci&oacute;n : </div>        
		  </td>
      	  <td nowrap> 
      	    <input type="text" name="E01EJLGRT" size="5" maxlength="4" value="<%= msgMT.getE01EJLGRT()%>">
      	    <a href="javascript:GetCodeCNOFC('E01EJLGRT','Y0')"><img src="<%=request.getContextPath()%>/images/1b.gif" alt="Ayuda" align="bottom" border="0" ></a>
      	  </td>     
      	</tr>
      	<tr id=trclear> 
		  <td nowrap width="40%"> 
		     <div align="right">C&oacute;digo de Agrupaci&oacute;n : </div>        
		  </td>
      	  <td nowrap> 
      	    <input type="text" name="E01EJLGRC" size="5" maxlength="4" value="<%= msgMT.getE01EJLGRC()%>">
      	    <a href="javascript:GetCodeCNOFC('E01EJLGRC',document.forms[0].E01EJLGRT.value)"><img src="<%=request.getContextPath()%>/images/1b.gif" alt="Ayuda" align="bottom" border="0" ></a>
      	  </td>     
      	</tr>
      	<tr id=trdark> 
		  <td nowrap width="40%"> 
		     <div align="right">C&oacute;digo de Jerarqu&iacute;a : </div>        
		  </td>
      	  <td nowrap> 
      	    <input type="text" name="E01EJLJRQ" size="5" maxlength="4" value="<%= msgMT.getE01EJLJRQ()%>">
      	    <a href="javascript:GetCodeCNOFC('E01EJLJRQ','YY')"><img src="<%=request.getContextPath()%>/images/1b.gif" alt="Ayuda" align="bottom" border="0" ></a>
      	  </td>     
      	</tr>
      	<tr id=trclear> 
		  <td nowrap width="40%"> 
		     <div align="right">Moneda  : </div>        
		  </td>
      	  <td nowrap> 
      	    <input type="text" name="E01EJLCCY" size="4" maxlength="3" value="<%= msgMT.getE01EJLCCY()%>">
      	    <a href="javascript:GetCurrency('E01EJLCCY',<%=currUser.getE01UBK()%>)"><img src="<%=request.getContextPath()%>/images/1b.gif" alt="Ayuda" align="bottom" border="0" ></a>
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
	  document.forms[0].E01EJLGRT.focus();
	  document.forms[0].E01EJLGRT.select();
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
