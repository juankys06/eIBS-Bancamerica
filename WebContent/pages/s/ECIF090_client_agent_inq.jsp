<html> 
<head>
<title>Consulta de Ejecutivos de Cuenta</title>
<META HTTP-EQUIV="Content-Type" CONTENT="text/html; charset=ISO-8859-1">
<link Href="<%=request.getContextPath()%>/pages/style.css" rel="stylesheet">


<script language="Javascript1.1" src="<%=request.getContextPath()%>/pages/s/javascripts/eIBS.jsp"> </SCRIPT>

<jsp:useBean id= "error" class= "datapro.eibs.beans.ELEERRMessage"  scope="session" />
<jsp:useBean id= "msgMT" class= "datapro.eibs.beans.ECIF09001Message"  scope="session" />
<jsp:useBean id= "userPOLevel" class= "datapro.eibs.beans.UserPos"  scope="session" />
</head>
<body>

<%
	if (msgMT.getE01INQRDY().equals("0"))
	{
	msgMT.setE01INQBNK(userPOLevel.getBank());
	msgMT.setE01INQRDY(userPOLevel.getHeader22());
	msgMT.setE01INQRDM(userPOLevel.getHeader23());
	}
%>

<H3 align="center">Consulta de Ejecutivos de Cuenta<img src="<%=request.getContextPath()%>/images/e_ibs.gif" align="left" name="EIBS_GIF" ALT="client_agent_inq,ECIF090"></H3>
<hr size="4">
<p>&nbsp;</p>

<form method="post" action="<%=request.getContextPath()%>/servlet/datapro.eibs.client.JSECIF090">
  <p> 
    <INPUT TYPE=HIDDEN NAME="SCREEN" VALUE="200">
  </p>
  <table class="tableinfo">
   <tr> 
   <td>
    <table cellspacing=0 cellpadding=2 width="100%" border="0">    
     	<tr id=trdark> 
	      <td nowrap width="40%"> 
	        <div align="right">Banco : </div>
	      </td>
	      <td nowrap> 
	        <input type="text" name="E01INQBNK" size="3" maxlength="2" onKeypress="enterInteger()" value="<%= msgMT.getE01INQBNK()%>">
	        <img src="<%=request.getContextPath()%>/images/Check.gif" alt="campo obligatorio" align="absbottom" border="0" width="16" height="20">
	      </td>
     	</tr>
     	<tr id=trclear> 
	      <td nowrap width="40%"> 
	        <div align="right">Año : </div>
	      </td>
	      <td nowrap> 
	        <input type="text" name="E01INQRDY" size="5" maxlength="4" onKeypress="enterInteger()" value="<%= msgMT.getE01INQRDY()%>">
	        <img src="<%=request.getContextPath()%>/images/Check.gif" alt="campo obligatorio" align="absbottom" border="0" width="16" height="20"> 
	      </td>
     	</tr>
     	<tr id=trdark> 
		  <td nowrap width="40%"> 
		     <div align="right">Mes : </div>        
		  </td>
      	  <td nowrap> 
      	    <input type="text" name="E01INQRDM" size="3" maxlength="2" onKeypress="enterInteger()" value="<%= msgMT.getE01INQRDM()%>">
      	    <img src="<%=request.getContextPath()%>/images/Check.gif" alt="campo obligatorio" align="absbottom" border="0" width="16" height="20">
      	  </td>     
      	</tr>
      	<tr id=trclear> 
		  <td nowrap width="40%"> 
		     <div align="right">Codigo de Ejecutivo : </div>        
		  </td>
      	  <td nowrap> 
      	    <input type="text" name="E01INQOFC" size="5" maxlength="4" value="<%= msgMT.getE01INQOFC()%>">
      	    <a href="javascript:GetCodeCNOFC('E01INQOFC','15')"><img src="<%=request.getContextPath()%>/images/1b.gif" alt="Ayuda" align="bottom" border="0" ></a>
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
	  document.forms[0].E01INQOFC.focus();
	  document.forms[0].E01INQOFC.select();
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
