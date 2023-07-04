<html> 
<head>
<title>Actualizacion Stock de Asesor</title>
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
   datapro.eibs.beans.EVL004003Message msgMT = (datapro.eibs.beans.EVL004003Message) appList.getRecord();
   
%>
</head>
<body>

<H3 align="center">Actualizacion Stock de Asesor<img src="<%=request.getContextPath()%>/images/e_ibs.gif" align="left" name="EIBS_GIF" ALT="value_paper_stock_update_detail,EVL0040"></H3>
<hr size="4">


<form method="post" action="<%=request.getContextPath()%>/servlet/datapro.eibs.valuepaper.JSEVL0040">
  
  <INPUT TYPE=HIDDEN NAME="SCREEN" VALUE="600">
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
	      	<input type="text" name="E03DOCBNK" size="3" maxlength="2" readonly value="<%= msgMT.getE03DOCBNK() %>"><b>-</b> 
	        <input type="text" name="E03DOCBRN" size="4" maxlength="3" readonly value="<%= msgMT.getE03DOCBRN() %>">
	      </td>
     	</tr>
     	<tr id=trclear> 
		  <td> 
		     <div align="right">Tipo Papel : </div>        
		  </td>
      	  <td nowrap> 
      	    <input type="text" name="E03DOCTIP" size="5" maxlength="4" readonly value="<%= msgMT.getE03DOCTIP() %>">
      	    <input type="text" name="E03DOCTIN" size="35" maxlength="35" readonly value="<%= msgMT.getE03DOCTIN() %>">
      	  </td>     
      	</tr>
      	<tr id=trdark>
          <td nowrap>
              <div align="right">Subtipo Papel : </div>
          </td>
          <td nowrap>
              <input type="text" name="E03DOCSUB" size="5" maxlength="4" readyonly value="<%= msgMT.getE03DOCSUB() %>">
              <input type="text" name="E03DOCSUN" size="35" maxlength="35" readonly value="<%= msgMT.getE03DOCSUN() %>"> 
          </td>
        </tr>
        <tr id=trclear> 
		  <td> 
		     <div align="right">Asesor : </div>        
		  </td>
      	  <td nowrap> 
      	    <input type="text" name="E03DOCUSR" size="12" maxlength="10" readonly value="<%= msgMT.getE03DOCUSR() %>">
      	  </td>     
      	</tr>
     	<tr id=trdark>
          <td nowrap>
             <div align="right">Cantidad Disponible : </div>
          </td>
          <td nowrap>
             <input type="text" name="E03DOCBAL" value ="<%= msgMT.getE03DOCBAL() %>" readonly>             
          </td>
        </tr>
      	<tr id=trclear>
          <td nowrap>
             <div align="right">Numero Inicial Disponible : </div>
          </td>
          <td nowrap>
             <input type="text" name="E03DOCLST" value ="<%= msgMT.getE03DOCLST() %>" readonly>             
          </td>
        </tr>
        <tr id=trdark>
          <td nowrap>
             <div align="right">Numero Final Disponible : </div>
          </td>
          <td nowrap>
             <input type="text" name="E03DOCFIN" value ="<%= msgMT.getE03DOCFIN() %>" readonly>             
          </td>
        </tr>
        <tr id=trclear>
          <td nowrap>
             <div align="right">Cantidad a Actualizar : </div>
          </td>
          <td nowrap>
             <input type="text" name="E03DOCAQT" size="13" maxlength="13" value ="<%= msgMT.getE03DOCAQT() %>" onkeypress="enterInteger()">
             <img src="<%=request.getContextPath()%>/images/Check.gif" alt="campo obligatorio" align="absbottom" border="0" width="16" height="20">             
          </td>
        </tr>
        <tr id=trdark>
          <td nowrap>
             <div align="right">Numero Inicial a Actualizar : </div>
          </td>
          <td nowrap>
          	 <input type="text" name="E03DOCAIN" value ="<%= msgMT.getE03DOCAIN() %>" >
             <img src="<%=request.getContextPath()%>/images/Check.gif" alt="campo obligatorio" align="absbottom" border="0" width="16" height="20">             
          </td>
        </tr>
        <tr id=trclear>
          <td nowrap>
             <div align="right">Numero Final a Actualizar : </div>
          </td>
          <td nowrap>
             <input type="text" name="E03DOCAFI" value ="<%= msgMT.getE03DOCAFI() %>" >
             <img src="<%=request.getContextPath()%>/images/Check.gif" alt="campo obligatorio" align="absbottom" border="0" width="16" height="20">             
          </td>
        </tr>
        <tr id=trdark>
          <td nowrap>
             <div align="right">Motivo de Actualizacion : </div>
          </td>
          <td nowrap>
      	    <input type="text" name="E03DOCMOT" size="5" maxlength="4" value="<%= msgMT.getE03DOCMOT() %>">
      	   <input type="text" name="E03DOCMON" size="35" maxlength="35" value="<%= msgMT.getE03DOCMON() %>" readonly>
      	    <a href="javascript:GetCodeDescCNOFC('E03DOCMOT','E03DOCMON','YD')"><img src="<%=request.getContextPath()%>/images/1b.gif" alt="ayuda" align="absbottom" border="0"></a>
      	    <img src="<%=request.getContextPath()%>/images/Check.gif" alt="campo obligatorio" align="absbottom" border="0" width="16" height="20"> 
          </td>
        </tr>
     </table>
    </td>
   </tr>
  </table>
  
  <p align="center"> 
    <input id="EIBSBTN" type="submit" name="Submit" value="Actualizar">
  </p>
  
</form>
</body>
</html>
