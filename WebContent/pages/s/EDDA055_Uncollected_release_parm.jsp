<html>
<head>
<title>Liberación de Diferidos por Oficina</title>
<META HTTP-EQUIV="Content-Type" CONTENT="text/html; charset=ISO-8859-1">
<link Href="<%=request.getContextPath()%>/pages/style.css" rel="stylesheet">


<script language="Javascript1.1" src="<%=request.getContextPath()%>/pages/s/javascripts/eIBS.jsp"> </SCRIPT>

<jsp:useBean id= "error" class= "datapro.eibs.beans.ELEERRMessage"  scope="session" />
<jsp:useBean id= "msgMT" class= "datapro.eibs.beans.EDDA05501Message"  scope="session" /> 
<jsp:useBean id= "currUser" class= "datapro.eibs.beans.ESS0030DSMessage"  scope="session" />

</head>
<body>

<H3 align="center">Liberación de Diferidos por Oficina<img src="<%=request.getContextPath()%>/images/e_ibs.gif" align="left" name="EIBS_GIF" ALT="Uncollected_release_parm.jsp,EDA0055"></H3>
<hr size="4">
<p>&nbsp;</p>

<form method="post" action="<%=request.getContextPath()%>/servlet/datapro.eibs.products.JSEDDA055">
  <p> 
    <INPUT TYPE=HIDDEN NAME="SCREEN" VALUE="600">

  </p>
  <table class="tableinfo" align="center">
   <tr> 
   <td>
    <table cellspacing=0 cellpadding=2 width="100%" border="0" align="center">
    	<tr id=trdark> 
		  <td> 
		     <div align="right">C&oacute;digo de oficina : </div>        
		  </td>
      	  <td nowrap> 
      	    <input type="text" name="E01UNCBRN" size="3" maxlength="2" value="<%= msgMT.getE01UNCBRN()%>">
			<a href="javascript:GetBranch('E01UNCBRN','')"><img src="<%=request.getContextPath()%>/images/1b.gif" alt="help" align="bottom" border="0"  ></a>
      	  </td>     
      	</tr>    
     </table>
    </td>
   </tr>
  </table>
  
  <p align="center"> 
    <input id="EIBSBTN" type=submit name="Submit" value="Enviar">
  </p>
  
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
