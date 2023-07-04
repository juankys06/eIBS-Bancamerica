<html> 
<head>
<title>Mantenimiento de Compañias - Pago y Cobro de Servicios</title>
<META HTTP-EQUIV="Content-Type" CONTENT="text/html; charset=ISO-8859-1">
<link Href="<%=request.getContextPath()%>/pages/style.css" rel="stylesheet">
<%@ page import ="datapro.eibs.master.Util" %>

<script language="Javascript1.1" src="<%=request.getContextPath()%>/pages/s/javascripts/eIBS.jsp"> </SCRIPT>

<jsp:useBean id= "mtList" class= "datapro.eibs.beans.JBObjList"  scope="session" />
<jsp:useBean id= "error" class= "datapro.eibs.beans.ELEERRMessage"  scope="session" />
<jsp:useBean id= "userPO" 	class= "datapro.eibs.beans.UserPos"  		scope="session"/>

<% 
   int row = 0;
   try { row = Integer.parseInt(request.getParameter("ROW"));} catch (Exception e) {}
   mtList.setCurrentRow(row);
   datapro.eibs.beans.SRV005001Message msgMT = (datapro.eibs.beans.SRV005001Message) mtList.getRecord();
   
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

<H3 align="center">Mantenimiento de Compañias - Pago y Cobro de Servicios<img src="<%=request.getContextPath()%>/images/e_ibs.gif" align="left" name="EIBS_GIF" ALT="services_cias_maint,SRV0050"></H3>
<hr size="4">


<form method="post" action="<%=request.getContextPath()%>/servlet/datapro.eibs.services.JSSRV0050">
 
  <INPUT TYPE=HIDDEN NAME="SCREEN" VALUE="400">
  <INPUT TYPE=HIDDEN NAME="ROW" VALUE="<%= row %>">
  
  <table class="tableinfo">
   <tr> 
   <td>
    <table cellspacing=0 cellpadding=2 width="100%" border="0">    
     	<tr id=trdark> 
	      <td nowrap width="20%"> 
	        <div align="right">Compañia : </div>
	      </td>
	      <td nowrap> 
	        <input type="text" name="E01CIACOD" size="5" maxlength="4" readonly value="<%= msgMT.getE01CIACOD() %>">
	       
	      </td>
     	</tr>
     	<tr id=trclear> 
	      <td nowrap width="20%"> 
	        <div align="right">Cliente : </div>
	      </td>
	      <td nowrap width="80%"> 
	        <input type="text" name="E01CNOSCG" size="10" maxlength="9" value="<%= msgMT.getE01CNOSCG() %>" onKeypress="enterInteger()">
	        <input type="text" name="E01CIANAM" size="40" maxlength="35" value="<%= msgMT.getE01CIANAM() %>" readonly>
	                    <a href="javascript:GetCustomerDescId('E01CNOSCG','E01CIANAM','')"><img src="<%=request.getContextPath()%>/images/1b.gif" alt=". . ." align="absbottom" border="0" ></a>
                         <img src="<%=request.getContextPath()%>/images/Check.gif" alt="campo obligatorio" align="absbottom" border="0" > 
	                    </div>
	      </td>
     	</tr>
     	<tr id=trdark> 
	      <td nowrap width="20%"> 
	        <div align="right">Tipo de Servicio: </div>
	      </td>
	      <td nowrap> 
      	    <input type="radio" name="E01CNOF01" value="D" <%if (msgMT.getE01CNOF01().equals("D")) out.print("checked"); %>>
            Domiciliaci&oacute;n
            <input type="radio" name="E01CNOF01" value="A" <%if (msgMT.getE01CNOF01().equals("A")) out.print("checked"); %>>
            Afiliaci&oacute;n
	        <input type="radio" name="E01CNOF01" value="B" <%if (msgMT.getE01CNOF01().equals("B")) out.print("checked"); %>>
            Ambos
			<input type="radio" name="E01CNOF01" value=" " <%if (msgMT.getE01CNOF01().equals(" ")) out.print("checked"); %>>
            Gen&eacute;rico
			 
	        <img src="<%=request.getContextPath()%>/images/Check.gif" alt="campo obligatorio" align="absbottom" border="0" width="16" height="20">
	      </td>
	      <td nowrap>
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
