<html> 
<head>
<title>Ingreso Manual de Papel Valor</title>
<META HTTP-EQUIV="Content-Type" CONTENT="text/html; charset=ISO-8859-1">
<link Href="<%=request.getContextPath()%>/pages/style.css" rel="stylesheet">
<%@ page import ="datapro.eibs.master.Util" %>

<script language="Javascript1.1" src="<%=request.getContextPath()%>/pages/s/javascripts/eIBS.jsp"> </SCRIPT>

<jsp:useBean id= "msgMT" class= "datapro.eibs.beans.EVL003001Message"  scope="session" />
<jsp:useBean id= "error" class= "datapro.eibs.beans.ELEERRMessage"  scope="session" />
<jsp:useBean id= "userPO" 	class= "datapro.eibs.beans.UserPos"  		scope="session"/>

<% 
   if (msgMT == null) msgMT = new datapro.eibs.beans.EVL003001Message();
   msgMT.setE01MOVBNK(userPO.getBank());
   msgMT.setE01MOVBRN(userPO.getBranch());   
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

<H3 align="center">Ingreso Manual de Papel Valor<img src="<%=request.getContextPath()%>/images/e_ibs.gif" align="left" name="EIBS_GIF" ALT="value_paper_mov_new,EVL0030"></H3>
<hr size="4">


<form method="post" action="<%=request.getContextPath()%>/servlet/datapro.eibs.valuepaper.JSEVL0030">
  <INPUT TYPE=HIDDEN NAME="SCREEN" VALUE="200">
  <table class="tableinfo">
   <tr> 
   <td>
    <table cellspacing=0 cellpadding=2 width="100%" border="0">    
     	<tr id=trdark> 	      
	      <td nowrap> 
	        <div align="right">Banco/Oficina : </div>
	      </td>
	      <td nowrap>
	      	<input type="text" name="E01MOVBNK" size="3" maxlength="2" value="<%= msgMT.getE01MOVBNK() %>"><b>-</b> 
	        <input type="text" name="E01MOVBRN" size="4" maxlength="3" value="<%= msgMT.getE01MOVBRN() %>">
	        <a href="javascript:GetBranch('E01MOVBRN',document.forms[0].E01MOVBNK.value)"><img src="<%=request.getContextPath()%>/images/1b.gif" alt="Ayuda" align="bottom" border="0" ></a>
	        <img src="<%=request.getContextPath()%>/images/Check.gif" alt="campo obligatorio" align="absbottom" border="0" width="16" height="20"> 
	      </td>
     	</tr>
     	<tr id=trclear> 
		  <td> 
		     <div align="right">Tipo de Papel : </div>        
		  </td>
      	  <td nowrap> 
      	    <input type="text" name="E01MOVTIP" size="5" maxlength="4" value="<%= msgMT.getE01MOVTIP() %>">
      	    <input type="text" name="E01MOVTIN" size="35" maxlength="35" value="<%= msgMT.getE01MOVTIN() %>" readonly >
      	    <a href="javascript:GetCodeDescCNOFC('E01MOVTIP','E01MOVTIN','YJ')"><img src="<%=request.getContextPath()%>/images/1b.gif" alt="Ayuda" align="bottom" border="0" ></a>
      	    <img src="<%=request.getContextPath()%>/images/Check.gif" alt="campo obligatorio" align="absbottom" border="0" width="16" height="20">
      	  </td>     
      	</tr>
      	<tr id=trdark> 
		  <td> 
		     <div align="right">Subtipo de Papel : </div>        
		  </td>
      	  <td nowrap> 
      	    <input type="text" name="E01MOVSUB" size="5" maxlength="4" value="<%= msgMT.getE01MOVSUB() %>">
      	    <input type="text" name="E01MOVSUN" size="35" maxlength="35" value="<%= msgMT.getE01MOVSUN() %>" readonly >
      	    <a href="javascript:GetCodeDescAuxCNOFC('E01MOVSUB','E01MOVSUN','YI',document.forms[0].E01MOVTIP.value)"><img src="<%=request.getContextPath()%>/images/1b.gif" alt="Ayuda" align="bottom" border="0" ></a>
      	    <img src="<%=request.getContextPath()%>/images/Check.gif" alt="campo obligatorio" align="absbottom" border="0" width="16" height="20">
      	  </td>     
      	</tr>
        <tr id=trclear>          
          <td nowrap>
             <div align="right">Numero de Serie : </div>
          </td>
          <td nowrap>
             <input type="text" name="E01MOVSER" value ="<%= msgMT.getE01MOVSER() %>" onkeypress="enterInteger()">             
          </td>
        </tr>
       	<tr id=trdark>          
          <td nowrap>
             <div align="right">Codigo Proveedor : </div>
          </td>
          <td nowrap>
             <input type="text" name="E01MOVVEN" size="9" maxlength="9" value="<%= msgMT.getE01MOVVEN().trim()%>">
             <a href="javascript:GetVendor('E01MOVVEN')"><img src="<%=request.getContextPath()%>/images/1b.gif" alt="Ayuda" align="absbottom" border="0" ></a>
             <img src="<%=request.getContextPath()%>/images/Check.gif" alt="campo obligatorio" align="absbottom" border="0" width="16" height="20">             
          </td>
        </tr>
        <tr id=trclear>          
          <td nowrap>
             <div align="right">Documento Inicial : </div>
          </td>
          <td nowrap>
             <input type="text" name="E01MOVINI" value ="<%= msgMT.getE01MOVINI() %>" onkeypress="enterInteger()">
             <img src="<%=request.getContextPath()%>/images/Check.gif" alt="campo obligatorio" align="absbottom" border="0" width="16" height="20">             
          </td>
        </tr>
       	<tr id=trdark>          
          <td nowrap>
             <div align="right">Documento Final : </div>
          </td>
          <td nowrap>
             <input type="text" name="E01MOVFIN" value ="<%= msgMT.getE01MOVFIN() %>" onkeypress="enterInteger()">
             <img src="<%=request.getContextPath()%>/images/Check.gif" alt="campo obligatorio" align="absbottom" border="0" width="16" height="20">             
          </td>
        </tr>
        <tr id=trclear>          
          <td nowrap>
             <div align="right">Cantidad Recibida : </div>
          </td>
          <td nowrap>
             <input type="text" name="E01MOVQTY" value ="<%= msgMT.getE01MOVQTY() %>" onkeypress="enterInteger()">
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
