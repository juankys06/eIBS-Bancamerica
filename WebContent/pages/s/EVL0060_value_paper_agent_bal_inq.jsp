<html>  
<head>
<title>Consulta de Saldos Papel Valor por Asesor</title>
<META HTTP-EQUIV="Content-Type" CONTENT="text/html; charset=ISO-8859-1">
<link Href="<%=request.getContextPath()%>/pages/style.css" rel="stylesheet">


<script language="Javascript1.1" src="<%=request.getContextPath()%>/pages/s/javascripts/eIBS.jsp"> </SCRIPT>

<jsp:useBean id= "error" class= "datapro.eibs.beans.ELEERRMessage"  scope="session" />
<jsp:useBean id= "msgMT" class= "datapro.eibs.beans.EVL006001Message"  scope="session" />
<jsp:useBean id= "currUser" class= "datapro.eibs.beans.ESS0030DSMessage"  scope="session" />

<script language="Javascript1.1">
   function checkBranch() {
   		if (document.forms[0].E01DOCBRN.value == "") document.forms[0].E01DOCBRN.value = "999";
   }
</script>
</head>
<body>

<H3 align="center">Consulta de Saldos de Papel Valor por Asesor<img src="<%=request.getContextPath()%>/images/e_ibs.gif" align="left" name="EIBS_GIF" ALT="value_paper_agent_bal_inq,EVL0060"></H3>
<hr size="4">
<p>&nbsp;</p>

<form method="post" action="<%=request.getContextPath()%>/servlet/datapro.eibs.valuepaper.JSEVL0060" onsubmit="checkBranch()">
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
	        <input type="text" name="E01DOCBNK" size="3" maxlength="2" onKeypress="enterInteger()" value="<%= (msgMT.getE01DOCBNK().equals(""))?currUser.getE01UBK():msgMT.getE01DOCBNK()  %>">
	      </td>
     	</tr>
     	<tr id=trclear> 
	      <td nowrap width="40%"> 
	        <div align="right">Oficina : </div>
	      </td>
	      <td nowrap> 
	        <input type="text" name="E01DOCBRN" size="4" maxlength="3" value="<%=(msgMT.getE01DOCBRN().equals("0"))?currUser.getE01UBR():msgMT.getE01DOCBRN()%>">
	        <a href="javascript:GetBranch('E01DOCBRN',document.forms[0].E01DOCBNK.value)"><img src="<%=request.getContextPath()%>/images/1b.gif" alt="Ayuda" align="bottom" border="0" ></a> 
	      </td>
     	</tr>
     	<tr id=trdark> 
		  <td> 
		     <div align="right">Tipo Papel : </div>        
		  </td>
      	  <td nowrap> 
      	    <input type="text" name="E01DOCTIP" size="5" maxlength="4" value="<%= msgMT.getE01DOCTIP()%>">
      	    <a href="javascript:GetCodeCNOFC('E01DOCTIP','YJ')"><img src="<%=request.getContextPath()%>/images/1b.gif" alt="Ayuda" align="bottom" border="0" ></a>
      	  </td>     
      	</tr>
      	<tr id=trclear> 
		  <td> 
		     <div align="right">Subtipo Papel : </div>        
		  </td>
      	  <td nowrap> 
      	    <input type="text" name="E01DOCSUB" size="5" maxlength="4" value="<%= msgMT.getE01DOCSUB()%>">
      	    <a href="javascript:GetCodeAuxCNOFC('E01DOCSUB','YI',document.forms[0].E01DOCTIP.value)"><img src="<%=request.getContextPath()%>/images/1b.gif" alt="Ayuda" align="bottom" border="0" ></a>
      	  </td>     
      	</tr>
      	<tr id=trdark> 
		  <td> 
		     <div align="right">Asesor : </div>        
		  </td>
      	  <td nowrap> 
      	    <input type="text" name="E01DOCUSR" size="12" maxlength="10" value="<%= msgMT.getE01DOCUSR()%>">
      	    <a href="javascript:getOficial('E01DOCUSR',document.forms[0].E01DOCBRN.value)"><img src="<%=request.getContextPath()%>/images/1b.gif" alt="Ayuda" align="absbottom" border="0" ></a>
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
	  document.forms[0].E01DOCBNK.focus();
	  document.forms[0].E01DOCBNK.select();
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
