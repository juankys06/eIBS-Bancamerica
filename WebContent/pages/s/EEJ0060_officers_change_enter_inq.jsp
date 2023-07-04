<html> 
<head> 
<title>Traspaso de Productos entre Ejecutivos</title>
<META HTTP-EQUIV="Content-Type" CONTENT="text/html; charset=ISO-8859-1">
<link Href="<%=request.getContextPath()%>/pages/style.css" rel="stylesheet">


<script language="Javascript1.1" src="<%=request.getContextPath()%>/pages/s/javascripts/eIBS.jsp"> </SCRIPT>

<jsp:useBean id= "error" class= "datapro.eibs.beans.ELEERRMessage"  scope="session" />
<jsp:useBean id= "msgMT" class= "datapro.eibs.beans.EEJ006001Message"  scope="session" />

<SCRIPT language="javascript">
  
  function goAction(opt) {
   
      ok = confirm("Esta seguro que desea realizar el cambio de ejecutivo?");
	  if (ok) document.forms[0].submit();
  }
  
</SCRIPT>

</head>
<body>

<H3 align="center">Traspaso de Productos entre Ejecutivos<img src="<%=request.getContextPath()%>/images/e_ibs.gif" align="left" name="EIBS_GIF" ALT="officers_change_enter_inq,EEJ0060"></H3>
<hr size="4">
<p>&nbsp;</p>

<form method="post" action="<%=request.getContextPath()%>/servlet/datapro.eibs.params.JSEEJ0060">
  <p> 
    <INPUT TYPE=HIDDEN NAME="SCREEN" VALUE="200">
  </p>
  <table class="tableinfo">
   <tr> 
   <td>
    <table cellspacing=0 cellpadding=2 width="100%" border="0">    
     	<tr id=trdark> 
		  <td nowrap width="40%"> 
		     <div align="right">Ejecutivo Origen : </div>        
		  </td>
      	  <td nowrap> 
      	    <input type="text" name="E01EJEORI" size="5" maxlength="4" value="<%= msgMT.getE01EJEORI()%>" >
      	    <a href="javascript:GetCodeCNOFC('E01EJEORI','15')"><img src="<%=request.getContextPath()%>/images/1b.gif" alt="Ayuda" align="bottom" border="0" ></a>
      	  </td>
      	</tr>
      	<tr id=trdark> 
		  <td nowrap width="40%"> 
		     <div align="right">Ejecutivo Destino : </div>        
		  </td>
      	  <td nowrap> 
      	    <input type="text" name="E01EJEDES" size="5" maxlength="4" value="<%= msgMT.getE01EJEDES()%>" >
      	    <a href="javascript:GetCodeCNOFC('E01EJEDES','15')"><img src="<%=request.getContextPath()%>/images/1b.gif" alt="Ayuda" align="bottom" border="0" ></a>
      	  </td>
      	</tr>
     </table>
    </td>
   </tr>  
  </table>
  <table class="tbenter" align="center">
  	<tr>
  		<TD class=TDBKG width="100%" align="center"> 
        	<div align="center"><a href="javascript:goAction()">Enviar</a> 
        	</div>
        </TD>
    </tr>
  </table>
 
	<script language="JavaScript">
	  document.forms[0].E01EJEORI.focus();
	  document.forms[0].E01EJEORI.select();
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
