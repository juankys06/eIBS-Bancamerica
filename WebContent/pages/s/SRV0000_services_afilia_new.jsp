<html> 
<head>
<title>Creacion de Afiliaciones</title>
<META HTTP-EQUIV="Content-Type" CONTENT="text/html; charset=ISO-8859-1">
<link Href="<%=request.getContextPath()%>/pages/style.css" rel="stylesheet">
<%@ page import ="datapro.eibs.master.Util" %>

<script language="Javascript1.1" src="<%=request.getContextPath()%>/pages/s/javascripts/eIBS.jsp"> </SCRIPT>

<jsp:useBean id= "msgAfi" class= "datapro.eibs.beans.SRV000001Message"  scope="session" />
<jsp:useBean id= "error" class= "datapro.eibs.beans.ELEERRMessage"  scope="session" />
<jsp:useBean id= "userPO" 	class= "datapro.eibs.beans.UserPos"  		scope="session"/>

<% 
   if (msgAfi == null) msgAfi = new datapro.eibs.beans.SRV000001Message();   
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

<H3 align="center">Creacion de Afiliaciones - Pago y Cobro de Servicios<img src="<%=request.getContextPath()%>/images/e_ibs.gif" align="left" name="EIBS_GIF" ALT="services_afilia_new,SRV0000"></H3>
<hr size="4">

<form method="post" action="<%=request.getContextPath()%>/servlet/datapro.eibs.services.JSSRV0000">
  <INPUT TYPE=HIDDEN NAME="SCREEN" VALUE="300">
  
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
		  <td> 
		     <div align="right">C&oacute;digo de Compañ&iacute;a : </div>        
		  </td>
      	  <td nowrap> 
      	    <input type="text" name="E01AFICIA" size="5" maxlength="4" value="<%= msgAfi.getE01AFICIA() %>">
      	    <input type="text" name="E01AFICIN" size="35" maxlength="35" value="<%= msgAfi.getE01AFICIN() %>" readonly >
      	    <a href="javascript:GetCodeDescCNOFC('E01AFICIA','E01AFICIN','YP')"><img src="<%=request.getContextPath()%>/images/1b.gif" alt="Ayuda" align="bottom" border="0" ></a>
      	    <img src="<%=request.getContextPath()%>/images/Check.gif" alt="campo obligatorio" align="absbottom" border="0" width="16" height="20"> 
      	  </td>     
      	</tr>
      	<tr id=trclear> 
	      <td nowrap> 
	        <div align="right">C&oacute;digo de Servicio : </div>
	      </td>
	      <td nowrap>
	      	<input type="text" name="E01AFISRV" size="5" maxlength="4" value="<%= msgAfi.getE01AFISRV() %>">
      	    <input type="text" name="E01AFISRN" size="35" maxlength="35" value="<%= msgAfi.getE01AFISRN() %>" readonly >
      	    <a href="javascript:GetCodeService('E01AFISRV','E01AFISRN',document.forms[0].E01AFICIA.value)"><img src="<%=request.getContextPath()%>/images/1b.gif" alt="Ayuda" align="bottom" border="0" ></a>
      	    <img src="<%=request.getContextPath()%>/images/Check.gif" alt="campo obligatorio" align="absbottom" border="0" width="16" height="20"> 
      	  </td>
     	</tr>
     	<tr id=trdark> 
     	
		  <td> 
		     <div align="right">Area y Referencia de Servicio 1: </div>        
		  </td>
      	  <td nowrap> 
       	    <input type="text" name="E01AFIARE" size="5" maxlength="4" value="<%= msgAfi.getE01AFIARE() %>">
      	    <input type="text" name="E01AFICTA" size="22" maxlength="20" value="<%= msgAfi.getE01AFICTA() %>">
      	    <img src="<%=request.getContextPath()%>/images/Check.gif" alt="campo obligatorio" align="absbottom" border="0" width="16" height="20">
      	  </td>     
      	</tr>
      		<tr id=trclear> 
	  <td> 
	     <div align="right">Area y Referencia de Servicio 2: </div>        
	  </td>
   	  <td nowrap> 
   	    <input type="text" name="E01AFIAR1" size="5" maxlength="4" value="<%= msgAfi.getE01AFIAR1() %>">
   	    <input type="text" name="E01AFICT1" size="22" maxlength="20" value="<%= msgAfi.getE01AFICT1() %>">
   	    <img src="<%=request.getContextPath()%>/images/Check.gif" alt="campo obligatorio" align="absbottom" border="0" width="16" height="20">
   	  </td>     
  	</tr>
  	<tr id=trdark> 
      <td> 
	    <div align="right">Area y Referencia de Servicio 3: </div>        
	  </td>
      <td nowrap> 
       <input type="text" name="E01AFIAR2" size="5" maxlength="4" value="<%= msgAfi.getE01AFIAR2() %>">
       <input type="text" name="E01AFICT2" size="22" maxlength="20" value="<%= msgAfi.getE01AFICT2() %>">
        <img src="<%=request.getContextPath()%>/images/Check.gif" alt="campo obligatorio" align="absbottom" border="0" width="16" height="20">
      </td>     
    </tr>

  	<tr id=trclear> 
	  <td> 
	  <div align="right">Area y Referencia de Servicio 4: </div>        
	 </td>
     <td nowrap> 
        <input type="text" name="E01AFIAR3" size="5" maxlength="4" value="<%= msgAfi.getE01AFIAR3() %>">
       <input type="text" name="E01AFICT3" size="22" maxlength="20" value="<%= msgAfi.getE01AFICT3() %>">
        <img src="<%=request.getContextPath()%>/images/Check.gif" alt="campo obligatorio" align="absbottom" border="0" width="16" height="20">
    </td>
   </tr>
   
   	<tr id=trdark> 
	  <td> 
	  <div align="right">Area y Referencia de Servicio 5: </div>        
	 </td>
      <td nowrap> 
        <input type="text" name="E01AFIAR4" size="5" maxlength="4" value="<%= msgAfi.getE01AFIAR4() %>">
        <input type="text" name="E01AFICT4" size="22" maxlength="20" value="<%= msgAfi.getE01AFICT4() %>">
        <img src="<%=request.getContextPath()%>/images/Check.gif" alt="campo obligatorio" align="absbottom" border="0" width="16" height="20">
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
