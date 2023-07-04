<html>
<head>
<title>Tabla de Módulos de Clasificación</title>
<META HTTP-EQUIV="Content-Type" CONTENT="text/html; charset=ISO-8859-1">
<link Href="<%=request.getContextPath()%>/pages/style.css" rel="stylesheet">


<script language="Javascript1.1" src="<%=request.getContextPath()%>/pages/s/javascripts/eIBS.jsp"> </SCRIPT>

</head>

<jsp:useBean id= "error" class= "datapro.eibs.beans.ELEERRMessage"  scope="session" />

<jsp:useBean id= "userPO" class= "datapro.eibs.beans.UserPos"  scope="session" />

<body>
 
<H3 align="center">Tabla de Módulos de Clasificación<img src="<%=request.getContextPath()%>/images/e_ibs.gif" align="left" name="EIBS_GIF" ALT="modules_enter,EDP0820"></H3>

<hr size="4">
<p>&nbsp;</p>

<form method="post" action="<%=request.getContextPath()%>/servlet/datapro.eibs.creditproposal.JSEDP0820">
  <p> 
    <INPUT TYPE=HIDDEN NAME="SCREEN" VALUE="200">
  </p>
  <h4>&nbsp;</h4>
  <table class="tbenter" cellspacing=0 cellpadding=3 width="100%" border="0">
    <tr> 
      <td width="30%"> 
        <div align="right">Tipo de Cliente : </div>
      </td>
      <td width="70%"> 
	    <input type="radio" name="E01MODLGT"  value="1" >
			Corporacion<BR>
	    <input type="radio" name="E01MODLGT"  value="2" checked >
			Persona Natural<BR>
	    <input type="radio" name="E01MODLGT"  value=" " >
			Todos
      </td>
    </tr>
  </table>
  
  <div align="center"> 
    <p><input id="EIBSBTN" type=submit name="Submit" value="Enviar"></p>
  </div>

<script language="JavaScript">

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
