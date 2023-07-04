<html> 
<head>
<title>Consulta de Movimientos Papel Valor por Oficina</title>
<META HTTP-EQUIV="Content-Type" CONTENT="text/html; charset=ISO-8859-1">
<link Href="<%=request.getContextPath()%>/pages/style.css" rel="stylesheet">


<script language="Javascript1.1" src="<%=request.getContextPath()%>/pages/s/javascripts/eIBS.jsp"> </SCRIPT>

<jsp:useBean id= "error" class= "datapro.eibs.beans.ELEERRMessage"  scope="session" />
<jsp:useBean id= "msgMT" class= "datapro.eibs.beans.EVL007001Message"  scope="session" />
<jsp:useBean id= "currUser" class= "datapro.eibs.beans.ESS0030DSMessage"  scope="session" />

<script language="Javascript1.1">
   function checkBranch() {
   		if (document.forms[0].E01MOVBRN.value == "") document.forms[0].E01MOVBRN.value = "999";
   }
</script>
</head>
<body>

<H3 align="center">Consulta de Movimientos de Papel Valor por Oficina<img src="<%=request.getContextPath()%>/images/e_ibs.gif" align="left" name="EIBS_GIF" ALT="value_paper_branch_mov_inq,EVL0070"></H3>
<hr size="4">
<p>&nbsp;</p>

<form method="post" action="<%=request.getContextPath()%>/servlet/datapro.eibs.valuepaper.JSEVL0070" onsubmit="checkBranch()">
  <p> 
    <INPUT TYPE=HIDDEN NAME="SCREEN" VALUE="300">
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
	        <input type="text" name="E01MOVBNK" size="3" maxlength="2" onKeypress="enterInteger()" value="<%=(msgMT.getE01MOVBNK().equals(""))?currUser.getE01UBK():msgMT.getE01MOVBNK()%>">
	      </td>
     	</tr>
     	<tr id=trclear> 
	      <td nowrap width="40%"> 
	        <div align="right">Oficina : </div>
	      </td>
	      <td nowrap> 
	        <input type="text" name="E01MOVBRN" size="4" maxlength="3" value="<%=(msgMT.getE01MOVBRN().equals("0"))?currUser.getE01UBR():msgMT.getE01MOVBRN() %>">
	        <a href="javascript:GetBranch('E01MOVBRN',document.forms[0].E01MOVBNK.value)"><img src="<%=request.getContextPath()%>/images/1b.gif" alt="Ayuda" align="bottom" border="0" ></a> 
	      </td>
     	</tr>
     	<tr id=trdark> 
		  <td> 
		     <div align="right">Tipo Papel : </div>        
		  </td>
      	  <td nowrap> 
      	    <input type="text" name="E01MOVTIP" size="5" maxlength="4" value="<%= msgMT.getE01MOVTIP()%>">
      	    <a href="javascript:GetCodeCNOFC('E01MOVTIP','YJ')"><img src="<%=request.getContextPath()%>/images/1b.gif" alt="Ayuda" align="bottom" border="0" ></a>
      	  </td>     
      	</tr>
      	<tr id=trclear> 
		  <td> 
		     <div align="right">Subtipo Papel : </div>        
		  </td>
      	  <td nowrap> 
      	    <input type="text" name="E01MOVSUB" size="5" maxlength="4" value="<%= msgMT.getE01MOVSUB()%>">
      	    <a href="javascript:GetCodeAuxCNOFC('E01MOVSUB','YI',document.forms[0].E01MOVTIP.value)"><img src="<%=request.getContextPath()%>/images/1b.gif" alt="Ayuda" align="bottom" border="0" ></a>
      	  </td>     
      	</tr>
        <tr id=trdark>
          <td nowrap>
              <div align="right">Fecha Movimiento - Desde : </div>
          </td>
          <td nowrap> 
              <input type="text" name="E01MOVFD1" size="3" maxlength="2" value="<%= msgMT.getE01MOVFD1()%>" onkeypress="enterInteger()">
              <input type="text" name="E01MOVFD2" size="3" maxlength="2" value="<%= msgMT.getE01MOVFD2()%>" onkeypress="enterInteger()">
              <input type="text" name="E01MOVFD3" size="3" maxlength="2" value="<%= msgMT.getE01MOVFD3()%>" onkeypress="enterInteger()">
              &nbsp; Hasta : &nbsp; 
              <input type="text" name="E01MOVTD1" size="3" maxlength="2" value="<%= msgMT.getE01MOVTD1()%>" onkeypress="enterInteger()">
              <input type="text" name="E01MOVTD2" size="3" maxlength="2" value="<%= msgMT.getE01MOVTD2()%>" onkeypress="enterInteger()">
              <input type="text" name="E01MOVTD3" size="3" maxlength="2" value="<%= msgMT.getE01MOVTD3()%>" onkeypress="enterInteger()">               
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
	  document.forms[0].E01MOVBNK.focus();
	  document.forms[0].E01MOVBNK.select();
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
