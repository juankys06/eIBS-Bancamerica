<html> 
<head>
<title>Consulta de Afiliaciones</title>
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
   datapro.eibs.beans.SRV000001Message msgMT = (datapro.eibs.beans.SRV000001Message) mtList.getRecord();
   
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

<H3 align="center">Consulta de Afiliaciones - Pago y Cobro de Servicios<img src="<%=request.getContextPath()%>/images/e_ibs.gif" align="left" name="EIBS_GIF" ALT="services_afilia_inq,SRV0000"></H3>
<hr size="4">


<form method="post" action="<%=request.getContextPath()%>/servlet/datapro.eibs.services.JSSRV0000">
 
  <INPUT TYPE=HIDDEN NAME="ROW" VALUE="<%= row %>">
  
  <TABLE cellspacing=0 cellpadding=2 width="100%" border="0">    
		<TR>
			<TD nowrap width="40%">
				<DIV align="right">N&uacute;mero de Cuenta :</DIV>
			</TD>
			<TD nowrap width="60%">
				<INPUT type="text" name="E01AFIACC" size="15" maxlength="12" value="<%= userPO.getHeader16() %>" readonly> 
			</TD>
		</TR>
		<TR>
			<TD nowrap width="40%">
				<DIV align="right">Nombre del Cliente :</DIV>
			</TD>
			<TD nowrap width="60%">
				<INPUT type="text" name="E01CUSNA1" size="40" maxlength="35" value="<%= userPO.getHeader17() %>" readonly>  
			</TD>
		</TR>
  </TABLE>
  <h4></h4>
  <table class="tableinfo">
   <tr> 
   <td>
    <table cellspacing=0 cellpadding=2 width="100%" border="0">    
     	<tr id=trdark> 
	      <td nowrap width="20%"> 
	        <div align="right">Compañia : </div>
	      </td>
	      <td nowrap> 
	        <input type="text" name="E01AFICIA" size="4" maxlength="4" readonly value="<%= msgMT.getE01AFICIA() %>">
	        <input type="text" name="E01AFICIN" size="40" maxlength="35" readonly value="<%= msgMT.getE01AFICIN() %>">
	      </td>
     	</tr>
     	<tr id=trclear> 
	      <td nowrap width="20%"> 
	        <div align="right">Servicio : </div>
	      </td>
	      <td nowrap> 
	        <input type="text" name="E01AFISRV" size="4" maxlength="4" readonly value="<%= msgMT.getE01AFISRV() %>">
	        <input type="text" name="E01AFISRN" size="40" maxlength="35" readonly value="<%= msgMT.getE01AFISRN() %>">
	      </td>
     	</tr>
     	<tr id=trdark> 
		  <td> 
		     <div align="right">Codigo de Area : </div>        
		  </td>
      	  <td nowrap> 
      	    <input type="text" name="E01AFIARE" size="5" maxlength="4" readonly value="<%= msgMT.getE01AFIARE() %>">
      	  </td>     
      	</tr>

     	<tr id=trclear> 
		  <td> 
		     <div align="right">Referencia de Servicio : </div>        
		  </td>
      	  <td nowrap> 
      	    <input type="text" name="E01AFICTA" size="22" maxlength="20" readonly value="<%= msgMT.getE01AFICTA() %>">
      	  </td>     
      	</tr>
     	<tr id=trdark> 
	      <td nowrap> 
	        <div align="right">Fecha de Creacion: </div>
	      </td>
	      <td nowrap>
	      	<input type="text" name="E01AFICD1" size="2" maxlength="2" readonly value="<%= msgMT.getE01AFICD1() %>">
	      	<input type="text" name="E01AFICD2" size="2" maxlength="2" readonly value="<%= msgMT.getE01AFICD2() %>">
	      	<input type="text" name="E01AFICD3" size="2" maxlength="2" readonly value="<%= msgMT.getE01AFICD3() %>">
	      </td>
     	</tr>
     	<tr id=trclear> 
		  <td> 
		     <div align="right">Usuario : </div>        
		  </td>
      	  <td nowrap> 
      	    <input type="text" name="E01AFICRU" size="12" maxlength="10" readonly value="<%= msgMT.getE01AFICRU() %>">
      	  </td>     
      	</tr>
     </table>
    </td>
   </tr>
  </table>
  
</form>
</body>
</html>
