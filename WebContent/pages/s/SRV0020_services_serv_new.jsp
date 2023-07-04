<html> 
<head>
<title>Creacion de Servicios - Pago y Cobro de Servicios</title>
<META HTTP-EQUIV="Content-Type" CONTENT="text/html; charset=ISO-8859-1">
<link Href="<%=request.getContextPath()%>/pages/style.css" rel="stylesheet">
<%@ page import ="datapro.eibs.master.Util" %>

<script language="Javascript1.1" src="<%=request.getContextPath()%>/pages/s/javascripts/eIBS.jsp"> </SCRIPT>

<jsp:useBean id= "msgSer" class= "datapro.eibs.beans.SRV002001Message"  scope="session" />
<jsp:useBean id= "error" class= "datapro.eibs.beans.ELEERRMessage"  scope="session" />
<jsp:useBean id= "userPO" 	class= "datapro.eibs.beans.UserPos"  		scope="session"/>

<% 
   if (msgSer == null) msgSer = new datapro.eibs.beans.SRV002001Message();   
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

<H3 align="center">Creacion de Servicios - Pago y Cobro de Servicios<img src="<%=request.getContextPath()%>/images/e_ibs.gif" align="left" name="EIBS_GIF" ALT="services_serv_new,SRV0020"></H3>
<hr size="4">


<form method="post" action="<%=request.getContextPath()%>/servlet/datapro.eibs.services.JSSRV0020">
  <INPUT TYPE=HIDDEN NAME="SCREEN" VALUE="300">
  
  <table class="tableinfo">
   <tr> 
   <td>
    <table cellspacing=0 cellpadding=2 width="100%" border="0">    
     	<tr id=trdark> 
	      <td nowrap width="20%"> 
	        <div align="right">Compañia : </div>
	      </td>
	      <td nowrap> 
	        <input type="text" name="E01SERCIA" size="4" maxlength="4" value="<%= msgSer.getE01SERCIA() %>">
	        <input type="text" name="E01SERCIN" size="40" maxlength="35" readonly value="<%= msgSer.getE01SERCIN() %>">
	        <a href="javascript:GetCodeDescCNOFC('E01SERCIA','E01SERCIN','YP')"><img src="<%=request.getContextPath()%>/images/1b.gif" alt="Ayuda" align="bottom" border="0" ></a>
	        <img src="<%=request.getContextPath()%>/images/Check.gif" alt="campo obligatorio" align="absbottom" border="0" width="16" height="20">
	      </td>
     	</tr>
     	<tr id=trclear> 
	      <td nowrap width="20%"> 
	        <div align="right">Servicio : </div>
	      </td>
	      <td nowrap> 
	        <input type="text" name="E01SERSRV" size="4" maxlength="4" value="<%= msgSer.getE01SERSRV() %>">
	        <input type="text" name="E01SERSRN" size="40" maxlength="35" value="<%= msgSer.getE01SERSRN() %>">
	        <img src="<%=request.getContextPath()%>/images/Check.gif" alt="campo obligatorio" align="absbottom" border="0" width="16" height="20">
	      </td>
     	</tr>
     	<tr id=trdark> 
	      <td nowrap width="20%"> 
	        <div align="right">Narrativa : </div>
	      </td>
	      <td nowrap> 
	        <input type="text" name="E01SERNAR" size="35" maxlength="30" value="<%= msgSer.getE01SERNAR() %>">
	      </td>
     	</tr>
     	<tr id=trclear> 
		  <td> 
		     <div align="right">N&uacute;mero de Cuenta : </div>        
		  </td>
      	  <td nowrap> 
      	    <input type="text" name="E01SERACC" size="15" maxlength="12" value="<%= msgSer.getE01SERACC() %>" onKeypress="enterInteger()">
      	    <a href="javascript:GetAccount('E01SERACC','','RT','')"><img src="<%=request.getContextPath()%>/images/1b.gif" alt="help" align="absbottom" border="0" ></a>
      	  </td>     
      	</tr>
      	<tr id=trdark> 
		  <td> 
		     <div align="right">N&uacute;mero de Batch : </div>        
		  </td>
      	  <td nowrap> 
      	    <input type="text" name="E01SERBTH" size="5" maxlength="4" value="<%= msgSer.getE01SERBTH() %>" onkeypress="enterInteger()">
      	  </td>     
      	</tr>
      	<tr id=trclear> 
		  <td> 
		    <div align="right">Tipo de Aplicaci&oacute;n  : </div>        
		  </td>
      	  <td nowrap> 
      	    <input type="radio" name="E01SERTIP" value="0" <%if (msgSer.getE01SERTIP().equals("0")) out.print("checked"); %>>
            Cobros (DB) 
            <input type="radio" name="E01SERTIP" value="1" <%if (msgSer.getE01SERTIP().equals("1")) out.print("checked"); %>>
            Pagos (CR) </td>     
      	</tr>
     	<tr id=trdark> 
		  <td nowrap width="20%"> 
	        <div align="right">Mnem&oacute;nico para D&eacute;bito : </div>
	      </td>
	      <td nowrap> 
	        <input type="text" name="E01SERDMN" size="5" maxlength="4" value="<%= msgSer.getE01SERDMN() %>">
	        <input type="text" name="E01SERDM1" size="40" maxlength="35" readonly value="<%= msgSer.getE01SERDM1() %>">
	      	<a href="javascript:GetCodeDescCNOFC('E01SERDMN','E01SERDM1','20')"><img src="<%=request.getContextPath()%>/images/1b.gif" alt="Ayuda" align="bottom" border="0" ></a>
      	  </td>     
      	</tr>
      	<tr id=trclear> 
		  <td nowrap width="20%"> 
	        <div align="right">Mnem&oacute;nico para Cr&eacute;dito : </div>
	      </td>
	      <td nowrap> 
	        <input type="text" name="E01SERCMN" size="5" maxlength="4" value="<%= msgSer.getE01SERCMN() %>">
	        <input type="text" name="E01SERCM1" size="40" maxlength="35" readonly value="<%= msgSer.getE01SERCM1() %>">
	      	<a href="javascript:GetCodeDescCNOFC('E01SERCMN','E01SERCM1','20')"><img src="<%=request.getContextPath()%>/images/1b.gif" alt="Ayuda" align="bottom" border="0" ></a>
      	  </td>     
      	</tr>
      	
      	<tr id=trdark> 
		  <td nowrap width="20%"> 
	        <div align="right">C&oacute;digo de Comisi&oacute;n : </div>
	      </td>
	      <td nowrap> 
	        <input type="text" name="E01SERCOM" size="5" maxlength="4" value="<%= msgSer.getE01SERCOM() %>">
	      	<a href="javascript:GetCodeCNOFC('E01SERCOM','CM')"><img src="<%=request.getContextPath()%>/images/1b.gif" alt="Ayuda" align="bottom" border="0" ></a>
      	  </td>     
      	</tr>

      	<tr id=trclear> 
		  <td> 
		    <div align="right">Pago Parcial : </div>        
		  </td>
      	  <td nowrap> 
      	    <input type="radio" name="E01SERPAP" value="0" <%if (msgSer.getE01SERPAP().equals("0")) out.print("checked"); %>>
            No Acepta 
            <input type="radio" name="E01SERPAP" value="1" <%if (msgSer.getE01SERPAP().equals("1")) out.print("checked"); %>>
            Acepta </td>     
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
