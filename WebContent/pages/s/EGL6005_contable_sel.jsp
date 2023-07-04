<html> 
<head>
<title>Reproducci&oacute;n Estados Financieros</title>
<META HTTP-EQUIV="Content-Type" CONTENT="text/html; charset=ISO-8859-1">
<link Href="<%=request.getContextPath()%>/pages/style.css" rel="stylesheet">
<%@ page import ="datapro.eibs.master.Util" %>

<script language="Javascript1.1" src="<%=request.getContextPath()%>/pages/s/javascripts/eIBS.jsp"> </SCRIPT>

<jsp:useBean id= "msgMT" class= "datapro.eibs.beans.EGL600501Message"  scope="session" />
<jsp:useBean id= "error" class= "datapro.eibs.beans.ELEERRMessage"  scope="session" />
<jsp:useBean id= "userPO" 	class= "datapro.eibs.beans.UserPos"  		scope="session"/>

<% 

    
	int NEW = 0;
	try { NEW = Integer.parseInt(request.getParameter("NEW"));} catch (Exception e) {}
	if (NEW == 1) {
	msgMT.destroy();
	}

   	if (msgMT == null) msgMT = new datapro.eibs.beans.EGL600501Message();
   	   
%>
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
</head>
<body >

<H3 align="center">Reproducci&oacute;n Estados Financieros<img src="<%=request.getContextPath()%>/images/e_ibs.gif" align="left" name="EIBS_GIF" ALT="products,EGL6005"></H3>
<hr size="4">


<form method="post" action="<%=request.getContextPath()%>/servlet/datapro.eibs.products.JSEGL6005">
  <INPUT TYPE=HIDDEN NAME="SCREEN" VALUE="0002">
  <table class="tableinfo">
   <tr> 
   <td>
    <table cellspacing=0 cellpadding=2 width="100%" border="0">    
     	
     	<tr id=trdark> 	      
	      <td> 
	        <div align="center">Ano Inicio de Reproduccio&oacute;n : </div>
	      </td>
	      <td width="50%">  
            <input type="text" name="E01ANOREP" size="3" maxlength="2"  value="<%= msgMT.getE01ANOREP()%>">
          </td>
     	</tr>
     	<tr id=trclear> 
		  <td> 
		     <div align="center">Mes Inicio de Reproduccio&oacute;n : </div>        
		  </td>
		  <td width="50%">  
           <input type="text" name="E01MESREP" size="3" maxlength="2"  value="<%= msgMT.getE01MESREP()%>">
          </td>
      	</tr>
     </table>
  	</td>
   </tr>
   </table>
  <p align="center"> 
    <input id="EIBSBTN" type="submit" name="Submit" value="Procesar">
  </p>
  
</form>
</body>
</html>
