<html> 
<head>
<title>Actualizacion Stock de Oficina</title>
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
   datapro.eibs.beans.EVL008001Message msgMT = (datapro.eibs.beans.EVL008001Message) appList.getRecord();
   
%>
</head>
<body>

<H3 align="center">Actualizacion Stock de Oficina<img src="<%=request.getContextPath()%>/images/e_ibs.gif" align="left" name="EIBS_GIF" ALT="value_paper_stock_update_detail,EVL0080"></H3>
<hr size="4">


<form method="post" action="<%=request.getContextPath()%>/servlet/datapro.eibs.valuepaper.JSEVL0080">
  
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
	      	<input type="text" name="E01BALBNK" size="3" maxlength="2" readonly value="<%= msgMT.getE01BALBNK() %>"><b>-</b> 
	        <input type="text" name="E01BALBRN" size="4" maxlength="3" readonly value="<%= msgMT.getE01BALBRN() %>">
	      </td>
     	</tr>
     	<tr id=trclear> 
		  <td> 
		     <div align="right">Tipo Papel : </div>        
		  </td>
      	  <td nowrap> 
      	    <input type="text" name="E01BALTIP" size="5" maxlength="4" readonly value="<%= msgMT.getE01BALTIP() %>">
      	    <input type="text" name="E01BALTIN" size="35" maxlength="35" readonly value="<%= msgMT.getE01BALTIN() %>">
      	  </td>     
      	</tr>
      	<tr id=trdark>
          <td nowrap>
              <div align="right">Subtipo Papel : </div>
          </td>
          <td nowrap>
              <input type="text" name="E01BALSUB" size="5" maxlength="4" readyonly value="<%= msgMT.getE01BALSUB() %>">
              <input type="text" name="E01BALSUN" size="35" maxlength="35" readonly value="<%= msgMT.getE01BALSUN() %>"> 
          </td>
        </tr>
     	<tr id=trclear>
          <td nowrap>
             <div align="right">Cantidad Disponible : </div>
          </td>
          <td nowrap>
             <input type="text" name="E01BALBAL" value ="<%= msgMT.getE01BALBAL() %>" readonly>             
          </td>
        </tr>
      	<tr id=trdark>
          <td nowrap>
             <div align="right">Numero Inicial Disponible : </div>
          </td>
          <td nowrap>
             <input type="text" name="E01BALLST" value ="<%= msgMT.getE01BALLST() %>" readonly>             
          </td>
        </tr>
        <tr id=trclear>
          <td nowrap>
             <div align="right">Numero Final Disponible : </div>
          </td>
          <td nowrap>
             <input type="text" name="E01BALFIN" value ="<%= msgMT.getE01BALFIN() %>" readonly>             
          </td>
        </tr>
        <tr id=trdark>
          <td nowrap>
             <div align="right">Numero Inicial Actualizacion : </div>
          </td>
          <td nowrap>
          	 <input type="text" name="E01BALAIN" value ="<%= msgMT.getE01BALAIN() %>" >
             <img src="<%=request.getContextPath()%>/images/Check.gif" alt="campo obligatorio" align="absbottom" border="0" width="16" height="20">             
          </td>
        </tr>
        <tr id=trclear>
          <td nowrap>
             <div align="right">Numero Final Actualizacion : </div>
          </td>
          <td nowrap>
             <input type="text" name="E01BALAFI" value ="<%= msgMT.getE01BALAFI() %>" >
             <img src="<%=request.getContextPath()%>/images/Check.gif" alt="campo obligatorio" align="absbottom" border="0" width="16" height="20">             
          </td>
        </tr>
        <tr id=trdark>
          <td nowrap>
             <div align="right">Motivo de Actualizacion : </div>
          </td>
          <td nowrap>
      	    <input type="text" name="E01BALMOT" size="5" maxlength="4" value="<%= msgMT.getE01BALMOT() %>">
      	    <input type="text" name="E01BALMON" size="35" maxlength="35" value="<%= msgMT.getE01BALMON() %>" readonly>
      	    <a href="javascript:GetCodeDescCNOFC('E01BALMOT','E01BALMON','YD')"><img src="<%=request.getContextPath()%>/images/1b.gif" alt="ayuda" align="absbottom" border="0"></a>
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
