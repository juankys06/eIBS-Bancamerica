<html> 
<head>
<title>Consulta de Vencimientos Sistema de Bastanteo</title>
<META HTTP-EQUIV="Content-Type" CONTENT="text/html; charset=ISO-8859-1">
<link Href="<%=request.getContextPath()%>/pages/style.css" rel="stylesheet">


<script language="Javascript1.1" src="<%=request.getContextPath()%>/pages/s/javascripts/eIBS.jsp"> </SCRIPT>

<jsp:useBean id= "error" class= "datapro.eibs.beans.ELEERRMessage"  scope="session" />
<jsp:useBean id= "msgMT" class= "datapro.eibs.beans.ECU001001Message"  scope="session" />
<jsp:useBean id= "currUser" class= "datapro.eibs.beans.ESS0030DSMessage"  scope="session" />

<script language="Javascript1.1">
   function checkBranch() {
   		if (document.forms[0].E01CUSBRA.value == "") document.forms[0].E01CUSBRA.value = "999";
   }
</script>

</head>
<body>

<H3 align="center">Consulta de Vencimientos - Sistema de Bastanteo<img src="<%=request.getContextPath()%>/images/e_ibs.gif" align="left" name="EIBS_GIF" ALT="bastanteo_enter_memb_inq,ECU0010"></H3>
<hr size="4">
<p>&nbsp;</p>

<form method="post" action="<%=request.getContextPath()%>/servlet/datapro.eibs.bastanteo.JSECU0010" onsubmit="checkBranch()">
  <p> 
    <INPUT TYPE=HIDDEN NAME="SCREEN" VALUE="20">
  </p>
  <table class="tableinfo">
   <tr> 
   <td>
    <table cellspacing=0 cellpadding=2 width="100%" border="0">    
     	<tr id=trdark> 
		  <td width="50%"> 
		     <div align="right">C&oacute;digo de Oficina : </div>        
		  </td>
      	  <td nowrap width="50%"> 
      	    <input type="text" name="E01CUSBRA" size="4" maxlength="3" value="">
	        <a href="javascript:GetBranch('E01CUSBRA','<%=currUser.getE01UBK().trim()%>')"><img src="<%=request.getContextPath()%>/images/1b.gif" alt="Ayuda" align="bottom" border="0" ></a> 
	      </td>     
      	</tr>
      	<tr id=trclear> 
		  <td width="50%"> 
		     <div align="right">C&oacute;digo de Ejecutivo : </div>        
		  </td>
      	  <td nowrap width="50%"> 
      	    <input type="text" name="E01CUSOFC" size="5" maxlength="4" value="<%= msgMT.getE01CUSOFC()%>">
      	    <a href="javascript:GetCodeCNOFC('E01CUSOFC','15')"><img src="<%=request.getContextPath()%>/images/1b.gif" alt="Ayuda" align="bottom" border="0" ></a>
      	  </td>     
      	</tr>
      	<tr id=trdark> 
		  <td> 
		     <div align="right">Abogado Revisor : </div>        
		  </td>
      	  <td nowrap> 
      	    <input type="text" name="E01CUFABO" size="5" maxlength="4" value="<%= msgMT.getE01CUFABO()%>">
      	    <a href="javascript:GetCodeCNOFC('E01CUFABO','YQ')"><img src="<%=request.getContextPath()%>/images/1b.gif" alt="Ayuda" align="bottom" border="0" ></a>
      	  </td>     
      	</tr>
      	<tr id=trclear>
          <td nowrap>
             <div align="right">Tipo de Cliente : </div>
          </td>
          <td nowrap>
             <select name="E01CUFFIR" >
                <OPTION value=" " ></OPTION>
                <OPTION value="Y" <% if (msgMT.getE01CUFFIR().equals("Y")) out.print("SELECTED");%>>Firma Personal</OPTION>
                <OPTION value="N" <% if (msgMT.getE01CUFFIR().equals("N")) out.print("SELECTED");%>>Corporacion</OPTION>
            </select> 
          </td>
        </tr>
      	<tr id=trdark>
          <td nowrap>
              <div align="right">Fecha de Vencimiento - Desde : </div>
          </td>
          <td nowrap> 
              <input type="text" name="E01CUSVD1" size="3" maxlength="2" value="<%= msgMT.getE01CUSVD1()%>" onkeypress="enterInteger()">
              <input type="text" name="E01CUSVD2" size="3" maxlength="2" value="<%= msgMT.getE01CUSVD2()%>" onkeypress="enterInteger()">
              <input type="text" name="E01CUSVD3" size="3" maxlength="2" value="<%= msgMT.getE01CUSVD3()%>" onkeypress="enterInteger()">
              &nbsp; Hasta : &nbsp; 
              <input type="text" name="E01CUSTD1" size="3" maxlength="2" value="<%= msgMT.getE01CUSTD1()%>" onkeypress="enterInteger()">
              <input type="text" name="E01CUSTD2" size="3" maxlength="2" value="<%= msgMT.getE01CUSTD2()%>" onkeypress="enterInteger()">
              <input type="text" name="E01CUSTD3" size="3" maxlength="2" value="<%= msgMT.getE01CUSTD3()%>" onkeypress="enterInteger()">               
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
	  document.forms[0].E01CUSBRA.focus();
	  document.forms[0].E01CUSBRA.select();
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
