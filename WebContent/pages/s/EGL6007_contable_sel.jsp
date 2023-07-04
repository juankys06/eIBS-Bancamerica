<html> 
<head>
<title>Reproduction Financial Statements</title>
<META HTTP-EQUIV="Content-Type" CONTENT="text/html; charset=ISO-8859-1">
<link Href="<%=request.getContextPath()%>/pages/style.css" rel="stylesheet">
<%@ page import ="datapro.eibs.master.Util" %>

<script language="Javascript1.1" src="<%=request.getContextPath()%>/pages/e/javascripts/eIBS.jsp"> </SCRIPT>

<jsp:useBean id= "msgMC" class= "datapro.eibs.beans.EGL600701Message"  scope="session" />
<jsp:useBean id= "error" class= "datapro.eibs.beans.ELEERRMessage"  scope="session" />
<jsp:useBean id= "userPO" 	class= "datapro.eibs.beans.UserPos"  		scope="session"/>

<% 

          
	int NEW = 0;
	try { NEW = Integer.parseInt(request.getParameter("NEW"));} catch (Exception e) {}
	if (NEW == 1) {
	msgMC.destroy();
	}

   	if (msgMC == null) msgMC = new datapro.eibs.beans.EGL600701Message();
   	   
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
      
<H3 align="center">Reproduccion Estados Financieros<img src="<%=request.getContextPath()%>/images/e_ibs.gif" align="left" name="EIBS_GIF" ALT="products,EGL6007"></H3>
<hr size="4">


<form method="post" action="<%=request.getContextPath()%>/servlet/datapro.eibs.products.JSEGL6007">
  <INPUT TYPE=HIDDEN NAME="SCREEN" VALUE="0002">
  <table class="tableinfo">
   <tr> 
   <td>
    <table cellspacing=0 cellpadding=2 width="100%" border="0">    
     	
     	<tr id=trdark> 	            
	      <td> 
	        <div align="center">Reproceso : </div>
	      </td>
	      <td nowrap width="19%" height="23"> 
              <input type="hidden" name="E01REPROC" value="<%= msgMC.getE01REPROC() %>">
              <input type="radio" name="CE01REPROC" value="Y" onClick="document.forms[0].E01REPROC.value='Y'"
			  <%if(msgMC.getE01REPROC().equals("Y")) out.print("checked");%>>
              S&iacute; 
              <input type="radio" name="CE01REPROC" value="N" onClick="document.forms[0].E01REPROC.value='N'"
			  <%if(msgMC.getE01REPROC().equals("N")) out.print("checked");%>>
              No 
          </td>
     	</tr>
     </table>
  	</td>
   </tr>
   </table>
  <p align="center"> 
    <input id="EIBSBTN" type="submit" name="Submit" value="Submit">
  </p>
  
</form>
</body>      
</html>
