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
   datapro.eibs.beans.EVL002003Message msgMT = (datapro.eibs.beans.EVL002003Message) appList.getRecord();
   
%>
</head>
<body>

<H3 align="center">Actualizacion Saldos de Oficina<img src="<%=request.getContextPath()%>/images/e_ibs.gif" align="left" name="EIBS_GIF" ALT="value_paper_bal_update_detail,EVL0020"></H3>
<hr size="4">


<form method="post" action="<%=request.getContextPath()%>/servlet/datapro.eibs.valuepaper.JSEVL0020">
  
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
	      	<input type="text" name="E03BALBNK" size="3" maxlength="2" readonly value="<%= msgMT.getE03BALBNK() %>"><b>-</b> 
	        <input type="text" name="E03BALBRN" size="4" maxlength="3" readonly value="<%= msgMT.getE03BALBRN() %>">
	      </td>
     	</tr>
     	<tr id=trclear> 
		  <td> 
		     <div align="right">Tipo Papel : </div>        
		  </td>
      	  <td nowrap> 
      	    <input type="text" name="E03BALTIP" size="5" maxlength="4" readonly value="<%= msgMT.getE03BALTIP() %>">
      	    <input type="text" name="E03BALTIN" size="35" maxlength="35" readonly value="<%= msgMT.getE03BALTIN() %>">
      	  </td>     
      	</tr>
      	<tr id=trdark>
          <td nowrap>
              <div align="right">Subtipo Papel : </div>
          </td>
          <td nowrap>
              <input type="text" name="E03BALSUB" size="5" maxlength="4" readyonly value="<%= msgMT.getE03BALSUB() %>">
              <input type="text" name="E03BALSUN" size="35" maxlength="35" readonly value="<%= msgMT.getE03BALSUN() %>"> 
          </td>
        </tr>
        <tr id=trclear> 
		  <td> 
		     <div align="right">Serial : </div>        
		  </td>
      	  <td nowrap> 
      	    <input type="text" name="E03BALSER" size="12" maxlength="10" readonly value="<%= msgMT.getE03BALSER() %>">
      	  </td>     
      	</tr>
     	<tr id=trdark>
          <td nowrap>
             <div align="right">Cantidad Disponible : </div>
          </td>
          <td nowrap>
             <input type="text" name="E03BALBAL" value ="<%= msgMT.getE03BALBAL() %>" readonly>             
          </td>
        </tr>
      	<tr id=trclear>
          <td nowrap>
             <div align="right">Numero Inicial Disponible : </div>
          </td>
          <td nowrap>
             <input type="text" name="E03BALINI" value ="<%= msgMT.getE03BALINI() %>" readonly>             
          </td>
        </tr>
        <tr id=trdark>
          <td nowrap>
             <div align="right">Numero Final Disponible : </div>
          </td>
          <td nowrap>
             <input type="text" name="E03BALFIN" value ="<%= msgMT.getE03BALFIN() %>" readonly>             
          </td>
        </tr>
        <tr id=trclear>
          <td nowrap>
             <div align="right">Cantidad a Actualizar : </div>
          </td>
          <td nowrap>
             <input type="text" name="E03BALAQT" size="13" maxlength="13" value ="<%= msgMT.getE03BALAQT() %>" onkeypress="enterInteger()">
             <img src="<%=request.getContextPath()%>/images/Check.gif" alt="campo obligatorio" align="absbottom" border="0" width="16" height="20">             
          </td>
        </tr>
        <tr id=trdark>
          <td nowrap>
             <div align="right">Numero Inicial a Actualizar : </div>
          </td>
          <td nowrap>
          	 <input type="text" name="E03BALAIN" value ="<%= msgMT.getE03BALAIN() %>" >
             <img src="<%=request.getContextPath()%>/images/Check.gif" alt="campo obligatorio" align="absbottom" border="0" width="16" height="20">             
          </td>
        </tr>
        <tr id=trclear>
          <td nowrap>
             <div align="right">Numero Final a Actualizar : </div>
          </td>
          <td nowrap>
             <input type="text" name="E03BALAFI" value ="<%= msgMT.getE03BALAFI() %>" >
             <img src="<%=request.getContextPath()%>/images/Check.gif" alt="campo obligatorio" align="absbottom" border="0" width="16" height="20">             
          </td>
        </tr>
        <tr id=trdark>
          <td nowrap>
             <div align="right">Motivo de Actualizacion : </div>
          </td>
          <td nowrap>
      	    <input type="text" name="E03BALMOT" size="5" maxlength="4" value="<%= msgMT.getE03BALMOT() %>">
      	   <input type="text" name="E03BALMON" size="35" maxlength="35" value="<%= msgMT.getE03BALMON() %>" readonly>
      	    <a href="javascript:GetCodeDescCNOFC('E03BALMOT','E03BALMON','YD')"><img src="<%=request.getContextPath()%>/images/1b.gif" alt="ayuda" align="absbottom" border="0"></a>
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
