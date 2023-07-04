<html> 
<head>
<title>Mantenimiento de Ejecutivos</title>
<META HTTP-EQUIV="Content-Type" CONTENT="text/html; charset=ISO-8859-1">
<link Href="<%=request.getContextPath()%>/pages/style.css" rel="stylesheet">


<script language="Javascript1.1" src="<%=request.getContextPath()%>/pages/s/javascripts/eIBS.jsp"> </SCRIPT>

<jsp:useBean id= "error" class= "datapro.eibs.beans.ELEERRMessage"  scope="session" />
<jsp:useBean id= "msgMT" class= "datapro.eibs.beans.EEJ003001Message"  scope="session" />

<script language="Javascript1.1">
   function checkBranch() {
   		if (document.forms[0].E01EJEBRN.value == "") document.forms[0].E01EJEBRN.value = "999";
   }
</script>

</head>
<body>

<H3 align="center">Mantenimiento de Ejecutivos<img src="<%=request.getContextPath()%>/images/e_ibs.gif" align="left" name="EIBS_GIF" ALT="params_officer_enter_inq,EEJ0030"></H3>
<hr size="4">
<p>&nbsp;</p>

<form method="post" action="<%=request.getContextPath()%>/servlet/datapro.eibs.params.JSEEJ0030" onsubmit="checkBranch()">
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
      	    <input type="text" name="E01EJEBNK" size="3" maxlength="2" onKeypress="enterInteger()" value="<%= msgMT.getE01EJEBNK()%>"></td>     
      	</tr>
      	<tr id=trclear>
          <td nowrap width="40%">
              <div align="right">Oficina : </div>
          </td>
          <td nowrap> 
              <input type="text" name="E01EJEBRN" size="4" maxlength="3" value="">
	          <a href="javascript:GetBranch('E01EJEBRN',document.forms[0].E01EJEBNK.value)"><img src="<%=request.getContextPath()%>/images/1b.gif" alt="Ayuda" align="bottom" border="0" ></a> 
	      </td>
        </tr>
        <tr id=trdark> 
		  <td nowrap width="40%"> 
		     <div align="right">Ejecutivo : </div>        
		  </td>
      	  <td nowrap> 
      	    <input type="text" name="E01EJECOD" size="5" maxlength="4" value="<%= msgMT.getE01EJECOD()%>">
      	    <a href="javascript:GetCodeCNOFC('E01EJECOD','15')"><img src="<%=request.getContextPath()%>/images/1b.gif" alt="Ayuda" align="bottom" border="0" ></a>
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
	  document.forms[0].E01EJEBNK.focus();
	  document.forms[0].E01EJEBNK.select();
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
