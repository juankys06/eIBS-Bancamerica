<html> 
<head>
<title>Creacion de Niveles Papel Valor</title>
<META HTTP-EQUIV="Content-Type" CONTENT="text/html; charset=ISO-8859-1">
<link Href="<%=request.getContextPath()%>/pages/style.css" rel="stylesheet">
<%@ page import ="datapro.eibs.master.Util" %>

<script language="Javascript1.1" src="<%=request.getContextPath()%>/pages/s/javascripts/eIBS.jsp"> </SCRIPT>

<jsp:useBean id= "msgLvl" class= "datapro.eibs.beans.EVL000002Message"  scope="session" />
<jsp:useBean id= "error" class= "datapro.eibs.beans.ELEERRMessage"  scope="session" />
<jsp:useBean id= "userPO" 	class= "datapro.eibs.beans.UserPos"  		scope="session"/>

<%

int NEW = 0;
try { NEW = Integer.parseInt(request.getParameter("NEW"));} catch (Exception e) {}
if (NEW == 1) {
msgLvl.destroy();
} 
if (msgLvl == null) msgLvl = new datapro.eibs.beans.EVL000002Message();
msgLvl.setE02LVLBNK(userPO.getBank());
msgLvl.setE02LVLBRN(userPO.getBranch());
   
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

<H3 align="center">Creacion de Niveles Papel Valor<img src="<%=request.getContextPath()%>/images/e_ibs.gif" align="left" name="EIBS_GIF" ALT="value_paper_lvl_new,EVL0000"></H3>
<hr size="4">


<form method="post" action="<%=request.getContextPath()%>/servlet/datapro.eibs.valuepaper.JSEVL0000">
  <INPUT TYPE=HIDDEN NAME="SCREEN" VALUE="300">
  <table class="tableinfo">
   <tr> 
   <td>
    <table cellspacing=0 cellpadding=2 width="100%" border="0">    
     	<tr id=trdark> 	      
	      <td nowrap> 
	        <div align="right">Banco/Oficina : </div>
	      </td>
	      <td nowrap>
	      	<input type="text" name="E02LVLBNK" size="3" maxlength="2" value="<%= msgLvl.getE02LVLBNK() %>"><b>-</b> 
	        <input type="text" name="E02LVLBRN" size="4" maxlength="3" value="<%= msgLvl.getE02LVLBRN() %>">
	        <a href="javascript:GetBranch('E02LVLBRN',document.forms[0].E02LVLBNK.value)"><img src="<%=request.getContextPath()%>/images/1b.gif" alt="Ayuda" align="bottom" border="0" ></a>
	        <img src="<%=request.getContextPath()%>/images/Check.gif" alt="campo obligatorio" align="absbottom" border="0" width="16" height="20">  
	      </td>
     	</tr>
     	<tr id=trclear> 
		  <td> 
		     <div align="right">Tipo Papel : </div>        
		  </td>
      	  <td nowrap> 
      	    <input type="text" name="E02LVLTIP" size="5" maxlength="4" value="<%= msgLvl.getE02LVLTIP() %>">
      	    <a href="javascript:GetCodeCNOFC('E02LVLTIP','YJ')"><img src="<%=request.getContextPath()%>/images/1b.gif" alt="Ayuda" align="bottom" border="0" ></a>
      	    <img src="<%=request.getContextPath()%>/images/Check.gif" alt="campo obligatorio" align="absbottom" border="0" width="16" height="20"> 
      	  </td>     
      	</tr>
      	<tr id=trdark>
          <td nowrap>
              <div align="right">Subtipo Papel : </div>
          </td>
          <td nowrap>
            <input type="text" name="E02LVLSUB" size="5" maxlength="4" value="<%= msgLvl.getE02LVLSUB() %>" >
            <a href="javascript:GetCodeAuxCNOFC('E02LVLSUB','YI',document.forms[0].E02LVLTIP.value)"><img src="<%=request.getContextPath()%>/images/1b.gif" alt="Ayuda" align="bottom" border="0" ></a>
            <img src="<%=request.getContextPath()%>/images/Check.gif" alt="campo obligatorio" align="absbottom" border="0" width="16" height="20">  
          </td>
        </tr>
        <tr id=trclear>          
          <td nowrap>
             <div align="right">Nivel Minimo : </div>
          </td>
          <td nowrap>
             <input type="text" name="E02LVLMIN" value ="<%= msgLvl.getE02LVLMIN() %>" onkeypress="enterInteger()">             
          </td>
        </tr>
        <tr id=trdark>          
          <td nowrap>
             <div align="right">Nivel Medio : </div>
          </td>
          <td nowrap>
             <input type="text" name="E02LVLMED" value ="<%= msgLvl.getE02LVLMED() %>" onkeypress="enterInteger()">             
          </td>
        </tr>
       	<tr id=trclear>          
          <td nowrap>
             <div align="right">Nivel Maximo : </div>
          </td>
          <td nowrap>
             <input type="text" name="E02LVLMAX" value ="<%= msgLvl.getE02LVLMAX() %>" onkeypress="enterInteger()">             
          </td>
        </tr>
        <tr id=trdark>          
          <td nowrap>
             <div align="right">Cantidad a Pedir : </div>
          </td>
          <td nowrap>
             <input type="text" name="E02LVLQTY" value ="<%= msgLvl.getE02LVLQTY() %>" onkeypress="enterInteger()">             
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
