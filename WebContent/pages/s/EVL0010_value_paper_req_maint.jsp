<html> 
<head>
<title>Mantenimiento de Pedidos Papel Valor</title>
<META HTTP-EQUIV="Content-Type" CONTENT="text/html; charset=ISO-8859-1">
<link Href="<%=request.getContextPath()%>/pages/style.css" rel="stylesheet">
<%@ page import ="datapro.eibs.master.Util" %>

<script language="Javascript1.1" src="<%=request.getContextPath()%>/pages/s/javascripts/eIBS.jsp"> </SCRIPT>

<jsp:useBean id= "mtList" class= "datapro.eibs.beans.JBObjList"  scope="session" />
<jsp:useBean id= "error" class= "datapro.eibs.beans.ELEERRMessage"  scope="session" />

<% 
   int row = 0;
   try { row = Integer.parseInt(request.getParameter("ROW"));} catch (Exception e) {}
   mtList.setCurrentRow(row);
   datapro.eibs.beans.EVL001001Message msgMT = (datapro.eibs.beans.EVL001001Message) mtList.getRecord();
   
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

<H3 align="center">Mantenimiento de Pedidos Papel Valor<img src="<%=request.getContextPath()%>/images/e_ibs.gif" align="left" name="EIBS_GIF" ALT="value_paper_req_maint,EVL0010"></H3>
<hr size="4">


<form method="post" action="<%=request.getContextPath()%>/servlet/datapro.eibs.valuepaper.JSEVL0010">
 
  <INPUT TYPE=HIDDEN NAME="SCREEN" VALUE="400">
  <INPUT TYPE=HIDDEN NAME="ROW" VALUE="<%= row %>">
  
  <table class="tableinfo">
   <tr> 
   <td>
    <table cellspacing=0 cellpadding=2 width="100%" border="0">    
     	<tr id=trdark> 
	      <td nowrap width="20%"> 
	        <div align="right">Referencia : </div>
	      </td>
	      <td nowrap> 
	        <input type="text" name="E01REQREF" size="10" maxlength="2" readonly value="<%= msgMT.getE01REQREF() %>">
	      </td>
     	</tr>
     	<tr id=trclear> 
	      <td nowrap width="20%"> 
	        <div align="right">Banco : </div>
	      </td>
	      <td nowrap> 
	        <input type="text" name="E01REQBNK" size="3" maxlength="2" readonly value="<%= msgMT.getE01REQBNK() %>">
	        <input type="text" name="E01REQBNN" size="35" maxlength="35" readonly value="<%= msgMT.getE01REQBNN() %>">
	      </td>
     	</tr>
     	<tr id=trdark> 
	      <td nowrap> 
	        <div align="right">Oficina : </div>
	      </td>
	      <td nowrap>
	      	<input type="text" name="E01REQBRN" size="4" maxlength="3" readonly value="<%= msgMT.getE01REQBRN() %>">
	      	<input type="text" name="E01REQBRM" size="35" maxlength="35" readonly value="<%= msgMT.getE01REQBRM() %>">
	      </td>
     	</tr>
     	<tr id=trclear> 
		  <td> 
		     <div align="right">Tipo Papel : </div>        
		  </td>
      	  <td nowrap> 
      	    <input type="text" name="E01REQTIP" size="5" maxlength="4" readonly value="<%= msgMT.getE01REQTIP() %>">
      	    <input type="text" name="E01REQTIN" size="35" maxlength="35" readonly value="<%= msgMT.getE01REQTIN() %>">
      	  </td>     
      	</tr>
      	<tr id=trdark>
          <td nowrap>
              <div align="right">Subtipo Papel : </div>
          </td>
          <td nowrap>
              <input type="text" name="E01REQSUB" size="5" maxlength="4" readonly value="<%= msgMT.getE01REQSUB() %>">
              <input type="text" name="E01REQSUN" size="35" maxlength="35" readonly value="<%= msgMT.getE01REQSUN() %>"> 
          </td>
        </tr>
        <tr id=trclear>
          <td nowrap>
             <div align="right">Cantidad Solicitada : </div>
          </td>
          <td nowrap>
             <input type="text" name="E01REQRQT" value ="<%= msgMT.getE01REQRQT() %>" onkeypress="enterInteger()">             
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
