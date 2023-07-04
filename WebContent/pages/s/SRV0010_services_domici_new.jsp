<html> 
<head>
<title>Creacion de Afiliaciones - Pago y Cobro de Servicios</title>
<META HTTP-EQUIV="Content-Type" CONTENT="text/html; charset=ISO-8859-1">
<link Href="<%=request.getContextPath()%>/pages/style.css" rel="stylesheet">
<%@ page import ="datapro.eibs.master.Util" %>

<script language="Javascript1.1" src="<%=request.getContextPath()%>/pages/s/javascripts/eIBS.jsp"> </SCRIPT>

<jsp:useBean id= "msgDom" class= "datapro.eibs.beans.SRV001001Message"  scope="session" />
<jsp:useBean id= "error" class= "datapro.eibs.beans.ELEERRMessage"  scope="session" />
<jsp:useBean id= "userPO" 	class= "datapro.eibs.beans.UserPos"  		scope="session"/>

<% 
   if (msgDom == null) msgDom = new datapro.eibs.beans.SRV001001Message();   
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

<H3 align="center">Creacion de Domiciliaciones - Pago y Cobro de Servicios<img src="<%=request.getContextPath()%>/images/e_ibs.gif" align="left" name="EIBS_GIF" ALT="services_domici_new,SRV0010"></H3>
<hr size="4">


<form method="post" action="<%=request.getContextPath()%>/servlet/datapro.eibs.services.JSSRV0010">
  <INPUT TYPE=HIDDEN NAME="SCREEN" VALUE="300">
  
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
	        <input type="text" name="E01DOMCIA" size="4" maxlength="4" value="<%= msgDom.getE01DOMCIA() %>">
	        <input type="text" name="E01DOMCIN" size="40" maxlength="35" readonly value="<%= msgDom.getE01DOMCIN() %>">
	      	<a href="javascript:GetCodeDescCNOFC('E01DOMCIA','E01DOMCIN','YP')"><img src="<%=request.getContextPath()%>/images/1b.gif" alt="Ayuda" align="bottom" border="0" ></a>
      	    <img src="<%=request.getContextPath()%>/images/Check.gif" alt="campo obligatorio" align="absbottom" border="0" width="16" height="20"> 
      	  </td>     
      	</tr>
      	<tr id=trclear> 
	      <td nowrap width="20%"> 
	        <div align="right">Servicio : </div>
	      </td>
	      <td nowrap> 
	        <input type="text" name="E01DOMSRV" size="4" maxlength="4" value="<%= msgDom.getE01DOMSRV() %>">
	        <input type="text" name="E01DOMSRN" size="40" maxlength="35" readonly value="<%= msgDom.getE01DOMSRN() %>">
	        <a href="javascript:GetCodeService('E01DOMSRV','E01DOMSRN',document.forms[0].E01DOMCIA.value)"><img src="<%=request.getContextPath()%>/images/1b.gif" alt="Ayuda" align="bottom" border="0" ></a>
	        <img src="<%=request.getContextPath()%>/images/Check.gif" alt="campo obligatorio" align="absbottom" border="0" width="16" height="20"> 
	      </td>
     	</tr>
     	<tr id=trdark> 
		  <td> 
		     <div align="right">Central y Referencia de Servicio 1: </div>        
		  </td>
      	  <td nowrap> 
       	    <input type="text" name="E01DOMCEN" size="5" maxlength="4" value="<%= msgDom.getE01DOMCEN() %>">
      	    <input type="text" name="E01DOMCTA" size="22" maxlength="20" value="<%= msgDom.getE01DOMCTA() %>">
      	    <img src="<%=request.getContextPath()%>/images/Check.gif" alt="campo obligatorio" align="absbottom" border="0" width="16" height="20"> 
      	  </td>     
      	</tr>
     	<tr id=trclear> 
		  <td> 
		     <div align="right">Central y Referencia de Servicio 2: </div>        
		  </td>
      	  <td nowrap> 
       	    <input type="text" name="E01DOMCE1" size="5" maxlength="4" value="<%= msgDom.getE01DOMCE1() %>">
      	    <input type="text" name="E01DOMCT1" size="22" maxlength="20" value="<%= msgDom.getE01DOMCT1() %>">
      	    <img src="<%=request.getContextPath()%>/images/Check.gif" alt="campo obligatorio" align="absbottom" border="0" width="16" height="20"> 
      	  </td>     
      	</tr>
     	<tr id=trdark> 
		  <td> 
		     <div align="right">Central y Referencia de Servicio 3: </div>        
		  </td>
      	  <td nowrap> 
       	    <input type="text" name="E01DOMCE2" size="5" maxlength="4" value="<%= msgDom.getE01DOMCE2() %>">
      	    <input type="text" name="E01DOMCT2" size="22" maxlength="20" value="<%= msgDom.getE01DOMCT2() %>">
      	    <img src="<%=request.getContextPath()%>/images/Check.gif" alt="campo obligatorio" align="absbottom" border="0" width="16" height="20"> 
      	  </td>     
      	</tr>
     	<tr id=trclear> 
		  <td> 
		     <div align="right">Central y Referencia de Servicio 4: </div>        
		  </td>
      	  <td nowrap> 
       	    <input type="text" name="E01DOMCE3" size="5" maxlength="4" value="<%= msgDom.getE01DOMCE3() %>">
      	    <input type="text" name="E01DOMCT3" size="22" maxlength="20" value="<%= msgDom.getE01DOMCT3() %>">
      	    <img src="<%=request.getContextPath()%>/images/Check.gif" alt="campo obligatorio" align="absbottom" border="0" width="16" height="20"> 
      	  </td>     
      	</tr>
     	<tr id=trdark> 
		  <td> 
		     <div align="right">Central y Referencia de Servicio 5: </div>        
		  </td>
      	  <td nowrap> 
       	    <input type="text" name="E01DOMCE4" size="5" maxlength="4" value="<%= msgDom.getE01DOMCE4() %>">
      	    <input type="text" name="E01DOMCT4" size="22" maxlength="20" value="<%= msgDom.getE01DOMCT4() %>">
      	    <img src="<%=request.getContextPath()%>/images/Check.gif" alt="campo obligatorio" align="absbottom" border="0" width="16" height="20"> 
      	  </td>     
      	</tr>

      	<tr id=trclear> 
		  <td> 
		     <div align="right">Monto M&aacute;ximo Autorizado : </div>        
		  </td>
      	  <td nowrap> 
      	    <input type="text" name="E01DOMMAX" size="20" maxlength="19" value="<%= msgDom.getE01DOMMAX() %>" onkeypress="enterDecimal()"> 
      	  </td>     
      	</tr>
     	<tr id=trdark> 
	      <td nowrap> 
	        <div align="right">Fecha Desde: </div>
	      </td>
	      <td nowrap>
	      	<input type="text" name="E01DOMFD1" size="2" maxlength="2" value="<%= msgDom.getE01DOMFD1() %>" onkeypress="enterInteger()">
	      	<input type="text" name="E01DOMFD2" size="2" maxlength="2" value="<%= msgDom.getE01DOMFD2() %>" onkeypress="enterInteger()">
	      	<input type="text" name="E01DOMFD3" size="2" maxlength="2" value="<%= msgDom.getE01DOMFD3() %>" onkeypress="enterInteger()">
	      	<a href="javascript:DatePicker(document.forms[0].E01DOMFD1,document.forms[0].E01DOMFD2,document.forms[0].E01DOMFD3)"><img src="<%=request.getContextPath()%>/images/calendar.gif" alt=". . ." align="absbottom" border="0" ></a>
	      </td>
     	</tr>
     	<tr id=trclear> 
	      <td nowrap> 
	        <div align="right">Fecha Hasta: </div>
	      </td>
	      <td nowrap>
	      	<input type="text" name="E01DOMTD1" size="2" maxlength="2" value="<%= msgDom.getE01DOMTD1() %>" onkeypress="enterInteger()">
	      	<input type="text" name="E01DOMTD2" size="2" maxlength="2" value="<%= msgDom.getE01DOMTD2() %>" onkeypress="enterInteger()">
	      	<input type="text" name="E01DOMTD3" size="2" maxlength="2" value="<%= msgDom.getE01DOMTD3() %>" onkeypress="enterInteger()">
	      	<a href="javascript:DatePicker(document.forms[0].E01DOMTD1,document.forms[0].E01DOMTD2,document.forms[0].E01DOMTD3)"><img src="<%=request.getContextPath()%>/images/calendar.gif" alt=". . ." align="absbottom" border="0" ></a> 
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
