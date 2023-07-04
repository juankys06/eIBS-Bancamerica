<html> 
<head>
<title>Mantenimiento de Afiliaciones - Pago y Cobro de Servicios</title>
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
   datapro.eibs.beans.SRV001001Message msgMT = (datapro.eibs.beans.SRV001001Message) mtList.getRecord();
   
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

<H3 align="center">Mantenimiento de Domiciliaciones - Pago y Cobro de Servicios<img src="<%=request.getContextPath()%>/images/e_ibs.gif" align="left" name="EIBS_GIF" ALT="services_domici_maint,SRV0010"></H3>
<hr size="4">


<form method="post" action="<%=request.getContextPath()%>/servlet/datapro.eibs.services.JSSRV0010">
 
  <INPUT TYPE=HIDDEN NAME="SCREEN" VALUE="400">
  <INPUT TYPE=HIDDEN NAME="ROW" VALUE="<%= row %>">
  
  <TABLE cellspacing=0 cellpadding=2 width="100%" border="0">    
		<TR>
			<TD nowrap width="40%">
				<DIV align="right">N&uacute;mero de Cuenta :</DIV>
			</TD>
			<TD nowrap width="60%">
				<INPUT type="text" name="E01DOMACC" size="15" maxlength="12" value="<%= userPO.getHeader16() %>" readonly> 
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
		<tr> 
	      <td nowrap width="40%"> 
	        <div align="right">Compañia : </div>
	      </td>
	      <td nowrap width="60%"> 
	        <input type="text" name="E01DOMCIA" size="4" maxlength="4" readonly value="<%= msgMT.getE01DOMCIA() %>">
	        <input type="text" name="E01DOMCIN" size="40" maxlength="35" readonly value="<%= msgMT.getE01DOMCIN() %>">
	      </td>
     	</tr>
     	<tr> 
	      <td nowrap width="40%"> 
	        <div align="right">Servicio : </div>
	      </td>
	      <td nowrap width="60%"> 
	        <input type="text" name="E01DOMSRV" size="4" maxlength="4" readonly value="<%= msgMT.getE01DOMSRV() %>">
	        <input type="text" name="E01DOMSRN" size="40" maxlength="35" readonly value="<%= msgMT.getE01DOMSRN() %>">
	      </td>
     	</tr>
     	<tr> 
		  <td nowrap width="40%"> 
		     <div align="right">Referencia de Servicio : </div>        
		  </td>
      	  <td nowrap width="60%"> 
      	    <input type="text" name="E01DOMCTA" size="22" maxlength="20" readonly value="<%= msgMT.getE01DOMCTA() %>">
      	  </td>     
      	</tr>
  </TABLE>
  <h4></h4>
  <table class="tableinfo">
   <tr> 
   <td>
    <table cellspacing=0 cellpadding=2 width="100%" border="0">    
       	<tr id=trdark> 
		  <td> 
		     <div align="right">C&oacute;digo de Central : </div>        
		  </td>
      	  <td nowrap> 
      	    <input type="text" name="E01DOMCEN" size="5" maxlength="4" value="<%= msgMT.getE01DOMCEN() %>" onkeypress="enterInteger()">
      	  </td>     
      	</tr>
    
      	<tr id=trclear> 
		  <td> 
		     <div align="right">Monto M&aacute;ximo Autorizado : </div>        
		  </td>
      	  <td nowrap> 
      	    <input type="text" name="E01DOMMAX" size="20" maxlength="19" value="<%= msgMT.getE01DOMMAX() %>" onkeypress="enterDecimal()">
      	  </td>     
      	</tr>
     	<tr id=trdark> 
	      <td nowrap> 
	        <div align="right">Fecha Desde: </div>
	      </td>
	      <td nowrap>
	      	<input type="text" name="E01DOMFD1" size="2" maxlength="2" value="<%= msgMT.getE01DOMFD1() %>" onkeypress="enterInteger()">
	      	<input type="text" name="E01DOMFD2" size="2" maxlength="2" value="<%= msgMT.getE01DOMFD2() %>" onkeypress="enterInteger()">
	      	<input type="text" name="E01DOMFD3" size="2" maxlength="2" value="<%= msgMT.getE01DOMFD3() %>" onkeypress="enterInteger()">
	      	<a href="javascript:DatePicker(document.forms[0].E01DOMFD1,document.forms[0].E01DOMFD2,document.forms[0].E01DOMFD3)"><img src="<%=request.getContextPath()%>/images/calendar.gif" alt=". . ." align="absbottom" border="0" ></a>
	      	
	      </td>
     	</tr>
     	<tr id=trclear> 
	      <td nowrap> 
	        <div align="right">Fecha Hasta: </div>
	      </td>
	      <td nowrap>
	      	<input type="text" name="E01DOMTD1" size="2" maxlength="2" value="<%= msgMT.getE01DOMTD1() %>" onkeypress="enterInteger()">
	      	<input type="text" name="E01DOMTD2" size="2" maxlength="2" value="<%= msgMT.getE01DOMTD2() %>" onkeypress="enterInteger()">
	      	<input type="text" name="E01DOMTD3" size="2" maxlength="2" value="<%= msgMT.getE01DOMTD3() %>" onkeypress="enterInteger()">
	      	<a href="javascript:DatePicker(document.forms[0].E01DOMTD1,document.forms[0].E01DOMTD2,document.forms[0].E01DOMTD3)"><img src="<%=request.getContextPath()%>/images/calendar.gif" alt=". . ." align="absbottom" border="0" ></a>
	      </td>
     	</tr>
     	<tr id=trdark> 
	      <td nowrap> 
	        <div align="right">Fecha de Creaci&oacute;n: </div>
	      </td>
	      <td nowrap>
	      	<input type="text" name="E01DOMCD1" size="2" maxlength="2" readonly value="<%= msgMT.getE01DOMCD1() %>">
	      	<input type="text" name="E01DOMCD2" size="2" maxlength="2" readonly value="<%= msgMT.getE01DOMCD2() %>">
	      	<input type="text" name="E01DOMCD3" size="2" maxlength="2" readonly value="<%= msgMT.getE01DOMCD3() %>">
	      </td>
     	</tr>
     	<tr id=trclear> 
	      <td nowrap> 
	        <div align="right">Fecha Ultima Actualizaci&oacute;n: </div>
	      </td>
	      <td nowrap>
	      	<input type="text" name="E01DOMUD1" size="2" maxlength="2" readonly value="<%= msgMT.getE01DOMUD1() %>">
	      	<input type="text" name="E01DOMUD2" size="2" maxlength="2" readonly value="<%= msgMT.getE01DOMUD2() %>">
	      	<input type="text" name="E01DOMUD3" size="2" maxlength="2" readonly value="<%= msgMT.getE01DOMUD3() %>">
	      </td>
     	</tr>
     	<tr id=trdark> 
		  <td> 
		     <div align="right">Usuario : </div>        
		  </td>
      	  <td nowrap> 
      	    <input type="text" name="E01DOMUPU" size="12" maxlength="10" readonly value="<%= msgMT.getE01DOMUPU() %>">
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
