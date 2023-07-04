<html> 
<head> 
<title>Consulta de Movimientos Papel Valor por Asesor</title>
<META HTTP-EQUIV="Content-Type" CONTENT="text/html; charset=ISO-8859-1">
<link Href="<%=request.getContextPath()%>/pages/style.css" rel="stylesheet">

<script language="Javascript1.1" src="<%=request.getContextPath()%>/pages/s/javascripts/eIBS.jsp"> </SCRIPT>

<jsp:useBean id= "error" class= "datapro.eibs.beans.ELEERRMessage"  scope="session" />
<jsp:useBean id= "msgMT" class= "datapro.eibs.beans.EVL007002Message"  scope="session" />
<jsp:useBean id= "currUser" class= "datapro.eibs.beans.ESS0030DSMessage"  scope="session" />


<script language="Javascript1.1">
   function checkBranch() {
   		if (document.forms[0].E02MOVBRN.value == "") document.forms[0].E02MOVBRN.value = "999";
   }
</script>
</head>
<body>

<H3 align="center">Consulta de Movimientos de Papel Valor por Asesor<img src="<%=request.getContextPath()%>/images/e_ibs.gif" align="left" name="EIBS_GIF" ALT="value_paper_agent_mov_inq,EVL0070"></H3>
<hr size="4">
<p>&nbsp;</p>

<form method="post" action="<%=request.getContextPath()%>/servlet/datapro.eibs.valuepaper.JSEVL0070" onsubmit="checkBranch()">
  <p> 
    <INPUT TYPE=HIDDEN NAME="SCREEN" VALUE="400">
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
	        <input type="text" name="E02MOVBNK" size="3" maxlength="2" onKeypress="enterInteger()" value="<%= (msgMT.getE02MOVBNK().equals(""))?currUser.getE01UBK():msgMT.getE02MOVBNK()%>">
	      </td>
     	</tr>
     	<tr id=trclear> 
	      <td nowrap width="40%"> 
	        <div align="right">Oficina : </div>
	      </td>
	      <td nowrap> 
	        <input type="text" name="E02MOVBRN" size="4" maxlength="3" value="<%= (msgMT.getE02MOVBRN().equals("0"))?currUser.getE01UBR():msgMT.getE02MOVBRN()%>">
	        <a href="javascript:GetBranch('E02MOVBRN',document.forms[0].E02MOVBNK.value)"><img src="<%=request.getContextPath()%>/images/1b.gif" alt="Ayuda" align="bottom" border="0" ></a> 
	      </td>
     	</tr>
     	<tr id=trdark> 
		  <td> 
		     <div align="right">Tipo Papel : </div>        
		  </td>
      	  <td nowrap> 
      	    <input type="text" name="E02MOVTIP" size="5" maxlength="4" value="<%= msgMT.getE02MOVTIP()%>">
      	    <a href="javascript:GetCodeCNOFC('E02MOVTIP','YJ')"><img src="<%=request.getContextPath()%>/images/1b.gif" alt="Ayuda" align="bottom" border="0" ></a>
      	  </td>     
      	</tr>
      	<tr id=trclear> 
		  <td> 
		     <div align="right">Subtipo Papel : </div>        
		  </td>
      	  <td nowrap> 
      	    <input type="text" name="E02MOVSUB" size="5" maxlength="4" value="<%= msgMT.getE02MOVSUB()%>">
      	    <a href="javascript:GetCodeAuxCNOFC('E02MOVSUB','YI',document.forms[0].E02MOVTIP.value)"><img src="<%=request.getContextPath()%>/images/1b.gif" alt="Ayuda" align="bottom" border="0" ></a>
      	  </td>     
      	</tr>
      	<tr id=trdark> 
		  <td> 
		     <div align="right">Asesor : </div>        
		  </td>
      	  <td nowrap> 
      	    <input type="text" name="E02MOVUSR" size="12" maxlength="10" value="<%= msgMT.getE02MOVUSR()%>">
      	    <a href="javascript:getOficial('E02MOVUSR',document.forms[0].E02MOVBRN.value)"><img src="<%=request.getContextPath()%>/images/1b.gif" alt="Ayuda" align="absbottom" border="0" ></a>
      	  </td>     
      	</tr>
        <tr id=trclear>
          <td nowrap>
              <div align="right">Fecha Movimiento - Desde : </div>
          </td>
          <td nowrap> 
              <input type="text" name="E02MOVFD1" size="3" maxlength="2" value="<%= msgMT.getE02MOVFD1()%>" onkeypress="enterInteger()">
              <input type="text" name="E02MOVFD2" size="3" maxlength="2" value="<%= msgMT.getE02MOVFD2()%>" onkeypress="enterInteger()">
              <input type="text" name="E02MOVFD3" size="3" maxlength="2" value="<%= msgMT.getE02MOVFD3()%>" onkeypress="enterInteger()">
              &nbsp; Hasta : &nbsp; 
              <input type="text" name="E02MOVTD1" size="3" maxlength="2" value="<%= msgMT.getE02MOVTD1()%>" onkeypress="enterInteger()">
              <input type="text" name="E02MOVTD2" size="3" maxlength="2" value="<%= msgMT.getE02MOVTD2()%>" onkeypress="enterInteger()">
              <input type="text" name="E02MOVTD3" size="3" maxlength="2" value="<%= msgMT.getE02MOVTD3()%>" onkeypress="enterInteger()">               
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
	  document.forms[0].E02MOVBNK.focus();
	  document.forms[0].E02MOVBNK.select();
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
