<html> 
<head>
<title>Aplicacion de Pagos y Cobros de Servicios</title>
<META HTTP-EQUIV="Content-Type" CONTENT="text/html; charset=ISO-8859-1">
<link Href="<%=request.getContextPath()%>/pages/style.css" rel="stylesheet">


<script language="Javascript1.1" src="<%=request.getContextPath()%>/pages/s/javascripts/eIBS.jsp"> </SCRIPT>

<jsp:useBean id= "error" class= "datapro.eibs.beans.ELEERRMessage"  scope="session" />
<jsp:useBean id= "msgMT" class= "datapro.eibs.beans.SRV003501Message"  scope="session" />

<SCRIPT language="javascript">
  
  function goAction(opt) {
   
      ok = confirm("Esta seguro que desea ejecutar el proceso?");
	  if (ok) document.forms[0].submit();
  }
  
</SCRIPT>

</head>
<body>

<H3 align="center">Aplicacion de Pagos y Cobros de Servicios<img src="<%=request.getContextPath()%>/images/e_ibs.gif" align="left" name="EIBS_GIF" ALT="services_application_enter_inq,SRV0035"></H3>
<hr size="4">
<p>&nbsp;</p>

<form method="post" action="<%=request.getContextPath()%>/servlet/datapro.eibs.services.JSSRV0035">
  <p> 
    <INPUT TYPE=HIDDEN NAME="SCREEN" VALUE="200">
  </p>
  <table class="tableinfo">
   <tr> 
   <td>
    <table cellspacing=0 cellpadding=2 width="100%" border="0">    
     	<tr id=trdark> 
		  <td> 
		     <div align="right">Compañia : </div>        
		  </td>
      	  <td nowrap> 
      	    <input type="text" name="E01BALCIA" size="5" maxlength="4" value="<%= msgMT.getE01BALCIA() %>">
	        <input type="text" name="E01BALCIN" size="40" maxlength="35" readonly value="<%= msgMT.getE01BALCIN() %>">
	      	<a href="javascript:GetCodeDescCNOFC('E01BALCIA','E01BALCIN','YP')"><img src="<%=request.getContextPath()%>/images/1b.gif" alt="Ayuda" align="bottom" border="0" ></a>
      	    <img src="<%=request.getContextPath()%>/images/Check.gif" alt="campo obligatorio" align="absbottom" border="0" width="16" height="20"> 
      	  </td>     
      	</tr>
      	<tr id=trclear> 
		  <td> 
		     <div align="right">Servicio : </div>        
		  </td>
      	  <td nowrap> 
      	    <input type="text" name="E01BALSRV" size="5" maxlength="4" value="<%= msgMT.getE01BALSRV() %>">
	        <input type="text" name="E01BALSRN" size="40" maxlength="35" readonly value="<%= msgMT.getE01BALSRN() %>">
	        <a href="javascript:GetCodeService('E01BALSRV','E01BALSRN',document.forms[0].E01BALCIA.value)"><img src="<%=request.getContextPath()%>/images/1b.gif" alt="Ayuda" align="bottom" border="0" ></a>
	        <img src="<%=request.getContextPath()%>/images/Check.gif" alt="campo obligatorio" align="absbottom" border="0" width="16" height="20"> 
	      </td>     
      	</tr>
      	<tr id=trdark> 
		  <td> 
		     <div align="right">Secuencia : </div>        
		  </td>
      	  <td nowrap> 
      	    <input type="text" name="E01BALSEQ" size="6" maxlength="5" value="<%= msgMT.getE01BALSEQ() %>" onkeypress="enterInteger()">
      	    <a href="javascript:GetSequence('E01BALSEQ',document.forms[0].E01BALCIA.value,document.forms[0].E01BALSRV.value)"><img src="<%=request.getContextPath()%>/images/1b.gif" alt="Ayuda" align="bottom" border="0" ></a>
	        <img src="<%=request.getContextPath()%>/images/Check.gif" alt="campo obligatorio" align="absbottom" border="0" width="16" height="20"> 
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
	  document.forms[0].E01BALCIA.focus();
	  document.forms[0].E01BALCIA.select();
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
