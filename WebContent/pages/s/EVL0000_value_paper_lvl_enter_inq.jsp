<html> 
<head>
<title>Mantenimiento de Niveles Papel Valor</title>
<META HTTP-EQUIV="Content-Type" CONTENT="text/html; charset=ISO-8859-1">
<link Href="<%=request.getContextPath()%>/pages/style.css" rel="stylesheet">


<script language="Javascript1.1" src="<%=request.getContextPath()%>/pages/s/javascripts/eIBS.jsp"> </SCRIPT>

<jsp:useBean id= "error" class= "datapro.eibs.beans.ELEERRMessage"  scope="session" />
<jsp:useBean id= "msgMT" class= "datapro.eibs.beans.EVL000001Message"  scope="session" />
<jsp:useBean id= "userPO" 	class= "datapro.eibs.beans.UserPos"  		scope="session"/>

<%    
   	msgMT.setE01LVLBNK(userPO.getBank());
	msgMT.setE01LVLBRN(userPO.getBranch());   
%>

<script language="Javascript1.1">
   function checkBranch() {
   		if (document.forms[0].E01LVLBRN.value == "") document.forms[0].E01LVLBRN.value = "999";
   }
</script>
</head>
<body>

<H3 align="center">Mantenimiento de Niveles Papel Valor<img src="<%=request.getContextPath()%>/images/e_ibs.gif" align="left" name="EIBS_GIF" ALT="value_paper_lvl_enter_inq,EVL0000"></H3>
<hr size="4">
<p>&nbsp;</p>

<form method="post" action="<%=request.getContextPath()%>/servlet/datapro.eibs.valuepaper.JSEVL0000" onsubmit="checkBranch()">
  <p> 
    <INPUT TYPE=HIDDEN NAME="SCREEN" VALUE="100">
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
	        <input type="text" name="E01LVLBNK" size="3" maxlength="2" onKeypress="enterInteger()" value="<%= msgMT.getE01LVLBNK()%>">
	      </td>
     	</tr>
     	<tr id=trclear> 
	      <td nowrap width="40%"> 
	        <div align="right">Oficina : </div>
	      </td>
	      <td nowrap> 
	        <input type="text" name="E01LVLBRN" size="4" maxlength="3" value="<%= msgMT.getE01LVLBRN()%>">
	        <a href="javascript:GetBranch('E01LVLBRN',document.forms[0].E01LVLBNK.value)"><img src="<%=request.getContextPath()%>/images/1b.gif" alt="Ayuda" align="bottom" border="0" ></a> 
	      </td>
     	</tr>
     	<tr id=trdark> 
		  <td> 
		     <div align="right">Tipo Papel : </div>        
		  </td>
      	  <td nowrap> 
      	    <input type="text" name="E01LVLTIP" size="5" maxlength="4" value="<%= msgMT.getE01LVLTIP()%>">
      	    <a href="javascript:GetCodeCNOFC('E01LVLTIP','YJ')"><img src="<%=request.getContextPath()%>/images/1b.gif" alt="Ayuda" align="bottom" border="0" ></a>
      	  </td>     
      	</tr>
      	<tr id=trclear> 
		  <td> 
		     <div align="right">Subtipo Papel : </div>        
		  </td>
      	  <td nowrap> 
      	    <input type="text" name="E01LVLSUB" size="5" maxlength="4" value="<%= msgMT.getE01LVLSUB()%>">
      	    <a href="javascript:GetCodeAuxCNOFC('E01LVLSUB','YI',document.forms[0].E01LVLTIP.value)"><img src="<%=request.getContextPath()%>/images/1b.gif" alt="Ayuda" align="bottom" border="0" ></a>
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
	  document.forms[0].E01LVLBNK.focus();
	  document.forms[0].E01LVLBNK.select();
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
