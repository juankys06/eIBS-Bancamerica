<html> 
<head>
<title>Reasignacion Stock Papel Valor</title>
<META HTTP-EQUIV="Content-Type" CONTENT="text/html; charset=ISO-8859-1">
<link Href="<%=request.getContextPath()%>/pages/style.css" rel="stylesheet">
<%@ page import ="datapro.eibs.master.Util" %>

<script language="Javascript1.1" src="<%=request.getContextPath()%>/pages/s/javascripts/eIBS.jsp"> </SCRIPT>

<jsp:useBean id= "appList" class= "datapro.eibs.beans.JBObjList"  scope="session" />
<jsp:useBean id= "error" class= "datapro.eibs.beans.ELEERRMessage"  scope="session" />

<% 
 if ( !error.getERRNUM().equals("0")  ) {
      error.setERRNUM("0");
 %>
     <SCRIPT Language="Javascript">
            showErrors();
     </SCRIPT>
 <%
 }

   int row = 0;
   try { row = Integer.parseInt(request.getParameter("ROW"));} catch (Exception e) {}
   appList.setCurrentRow(row);
   datapro.eibs.beans.EVL004002Message msgMT = (datapro.eibs.beans.EVL004002Message) appList.getRecord();
   
%>
</head>
<body>

<H3 align="center">Reasignacion de Stock de Papel Valor<img src="<%=request.getContextPath()%>/images/e_ibs.gif" align="left" name="EIBS_GIF" ALT="value_paper_stock_reassign_detail,EVL0040"></H3>
<hr size="4">


<form method="post" action="<%=request.getContextPath()%>/servlet/datapro.eibs.valuepaper.JSEVL0040">
  
  <INPUT TYPE=HIDDEN NAME="SCREEN" VALUE="500">
  <INPUT TYPE=HIDDEN NAME="ROW" VALUE="<%= row%>">
  <table class="tableinfo">
   <tr> 
   <td>
    <table cellspacing=0 cellpadding=2 width="100%" border="0">    
     	<tr id=trdark> 
	      <td nowrap> 
	        <div align="right">Banco/Oficina : </div>
	      </td>
	      <td nowrap>
	      	<input type="text" name="E02DOCBNK" size="3" maxlength="2" readonly value="<%= msgMT.getE02DOCBNK() %>"><b>-</b> 
	        <input type="text" name="E02DOCBRN" size="4" maxlength="3" readonly value="<%= msgMT.getE02DOCBRN() %>">
	      </td>
     	</tr>
     	<tr id=trclear> 
		  <td> 
		     <div align="right">Tipo Papel : </div>        
		  </td>
      	  <td nowrap> 
      	    <input type="text" name="E02DOCTIP" size="5" maxlength="4" readonly value="<%= msgMT.getE02DOCTIP() %>">
      	    <input type="text" name="E02DOCTIN" size="35" maxlength="35" readonly value="<%= msgMT.getE02DOCTIN() %>">
      	  </td>     
      	</tr>
      	<tr id=trdark>
          <td nowrap>
              <div align="right">Subtipo Papel : </div>
          </td>
          <td nowrap>
              <input type="text" name="E02DOCSUB" size="5" maxlength="4" readyonly value="<%= msgMT.getE02DOCSUB() %>">
              <input type="text" name="E02DOCSUN" size="35" maxlength="35" readonly value="<%= msgMT.getE02DOCSUN() %>"> 
          </td>
        </tr>
        <tr id=trclear> 
		  <td> 
		     <div align="right">Asesor : </div>        
		  </td>
      	  <td nowrap> 
      	    <input type="text" name="E02DOCUSR" size="12" maxlength="10" readonly value="<%= msgMT.getE02DOCUSR() %>">
      	  </td>     
      	</tr>
     	<tr id=trclear>
          <td nowrap>
             <div align="right">Cantidad Disponible : </div>
          </td>
          <td nowrap>
             <input type="text" name="E02DOCBAL" value ="<%= msgMT.getE02DOCBAL() %>" readonly>             
          </td>
        </tr>
      	<tr id=trdark>
          <td nowrap>
             <div align="right">Numero Inicial Disponible : </div>
          </td>
          <td nowrap>
             <input type="text" name="E02DOCLST" value ="<%= msgMT.getE02DOCLST() %>" readonly>             
          </td>
        </tr>
        <tr id=trclear>
          <td nowrap>
             <div align="right">Numero Final Disponible : </div>
          </td>
          <td nowrap>
             <input type="text" name="E02DOCFIN" value ="<%= msgMT.getE02DOCFIN() %>" readonly>             
          </td>
        </tr>
        <tr id=trdark> 
		  <td> 
		     <div align="right">Asesor Recibe : </div>        
		  </td>
      	  <td nowrap> 
      	    <input type="text" name="E02MOVUSR" size="12" maxlength="10" value="<%= msgMT.getE02MOVUSR() %>">
      	    <a href="javascript:getOficial('E02MOVUSR',document.forms[0].E02DOCBRN.value)"><img src="<%=request.getContextPath()%>/images/1b.gif" alt="Ayuda" align="absbottom" border="0" ></a>
      	    <img src="<%=request.getContextPath()%>/images/Check.gif" alt="campo obligatorio" align="absbottom" border="0" width="16" height="20">
      	  </td>     
      	</tr>
      	<tr id=trclear>
          <td nowrap>
             <div align="right">Cantidad a Reasignar : </div>
          </td>
          <td nowrap>
             <input type="text" name="E02MOVQTY" size="13" maxlength="13" value ="<%= msgMT.getE02MOVQTY() %>" onkeypress="enterInteger()">
             <img src="<%=request.getContextPath()%>/images/Check.gif" alt="campo obligatorio" align="absbottom" border="0" width="16" height="20">             
          </td>
        </tr>
        <tr id=trdark>
          <td nowrap>
             <div align="right">Numero Inicial a Reasignar : </div>
          </td>
          <td nowrap>
             <input type="text" name="E02MOVINI" size="20" maxlength="12" value ="<%= msgMT.getE02MOVINI() %>" onkeypress="enterInteger()">
             <img src="<%=request.getContextPath()%>/images/Check.gif" alt="campo obligatorio" align="absbottom" border="0" width="16" height="20">             
          </td>
        </tr>
        <tr id=trclear>
          <td nowrap>
             <div align="right">Numero Final a Reasignar : </div>
          </td>
          <td nowrap>
             <input type="text" name="E02MOVFIN" size="20" maxlength="12" value ="<%= msgMT.getE02MOVFIN() %>" onkeypress="enterInteger()">
             <img src="<%=request.getContextPath()%>/images/Check.gif" alt="campo obligatorio" align="absbottom" border="0" width="16" height="20">             
          </td>
        </tr>
     </table>
    </td>
   </tr>
  </table>
  
  <p align="center"> 
    <input id="EIBSBTN" type="submit" name="Submit" value="Reasignar">
  </p>
  
</form>
</body>
</html>
