<html> 
<head>
<title>Creacion de Unidades de Agrupaci&oacute;n de Ejecutivos</title>
<META HTTP-EQUIV="Content-Type" CONTENT="text/html; charset=ISO-8859-1">
<link Href="<%=request.getContextPath()%>/pages/style.css" rel="stylesheet">
<%@ page import ="datapro.eibs.master.Util" %>

<script language="Javascript1.1" src="<%=request.getContextPath()%>/pages/s/javascripts/eIBS.jsp"> </SCRIPT>

<jsp:useBean id= "msgPri" class= "datapro.eibs.beans.EEJ002001Message"  scope="session" />
<jsp:useBean id= "error" class= "datapro.eibs.beans.ELEERRMessage"  scope="session" />
<jsp:useBean id= "userPO" 	class= "datapro.eibs.beans.UserPos"  		scope="session"/>

<% 
   if (msgPri == null) msgPri = new datapro.eibs.beans.EEJ002001Message();   
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
<body>

<H3 align="center">Creacion de Unidades de Agrupaci&oacute;n de Ejecutivos<img src="<%=request.getContextPath()%>/images/e_ibs.gif" align="left" name="EIBS_GIF" ALT="params_group_units_new,EEJ0020"></H3>
<hr size="4">


<form method="post" action="<%=request.getContextPath()%>/servlet/datapro.eibs.params.JSEEJ0020">
  <INPUT TYPE=HIDDEN NAME="SCREEN" VALUE="300">
  <table class="tableinfo">
   <tr> 
   <td>
    <table cellspacing=0 cellpadding=2 width="100%" border="0">
    	<tr id=trdark> 
		  <td> 
		     <div align="right">C&oacute;digo de Ejecutivo : </div>        
		  </td>
      	  <td nowrap> 
      	    <input type="text" name="E01EJUEJE" size="12" maxlength="10" value="<%= msgPri.getE01EJUEJE() %>">
      	    <a href="javascript:GetOfficer('E01EJUEJE')"><img src="<%=request.getContextPath()%>/images/1b.gif" alt="Ayuda" align="absbottom" border="0" ></a>
      	    <img src="<%=request.getContextPath()%>/images/Check.gif" alt="campo obligatorio" align="absbottom" border="0" width="16" height="20">
      	  </td>     
      	</tr>    
     	<tr id=trclear> 
		  <td> 
		     <div align="right">Tipo de Agrupaci&oacute;n : </div>        
		  </td>
      	  <td nowrap> 
      	    <input type="text" name="E01EJUTYP" size="5" maxlength="4" value="<%= msgPri.getE01EJUTYP() %>">
      	    <input type="text" name="E01EJUTYN" size="35" maxlength="35" value="<%= msgPri.getE01EJUTYN() %>" readonly >
      	    <a href="javascript:GetCodeDescCNOFC('E01EJUTYP','E01EJUTYN','Y0')"><img src="<%=request.getContextPath()%>/images/1b.gif" alt="Ayuda" align="bottom" border="0" ></a>
      	    <img src="<%=request.getContextPath()%>/images/Check.gif" alt="campo obligatorio" align="absbottom" border="0" width="16" height="20"> 
      	  </td>     
      	</tr>
      	<tr id=trdark> 
	      <td nowrap> 
	        <div align="right">C&oacute;digo de Agrupaci&oacute;n : </div>
	      </td>
	      <td nowrap>
	      	<input type="text" name="E01EJUCDE" size="5" maxlength="4" value="<%= msgPri.getE01EJUCDE() %>">
      	    <input type="text" name="E01EJUCDN" size="35" maxlength="35" value="<%= msgPri.getE01EJUCDN() %>" readonly >
      	    <a href="javascript:GetCodeDescCNOFC('E01EJUCDE','E01EJUCDN',document.forms[0].E01EJUTYP.value)"><img src="<%=request.getContextPath()%>/images/1b.gif" alt="Ayuda" align="bottom" border="0" ></a>
      	    <img src="<%=request.getContextPath()%>/images/Check.gif" alt="campo obligatorio" align="absbottom" border="0" width="16" height="20"> 
      	  </td>
     	</tr>
     </table>
    </td>
   </tr>
  </table>
  
  <p align="center"> 
    <input id="EIBSBTN" type="submit" name="Submit" value="Enviar">
  </p>
  
</form>
</body>
</html>
