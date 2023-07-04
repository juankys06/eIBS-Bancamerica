<html> 
<head>
<title>Creacion de Balances - Pago y Cobro de Servicios</title>
<META HTTP-EQUIV="Content-Type" CONTENT="text/html; charset=ISO-8859-1">
<link Href="<%=request.getContextPath()%>/pages/style.css" rel="stylesheet">
<%@ page import ="datapro.eibs.master.Util" %>

<script language="Javascript1.1" src="<%=request.getContextPath()%>/pages/s/javascripts/eIBS.jsp"> </SCRIPT>

<jsp:useBean id= "msgBal" class= "datapro.eibs.beans.SRV003001Message"  scope="session" />
<jsp:useBean id= "error" class= "datapro.eibs.beans.ELEERRMessage"  scope="session" />
<jsp:useBean id= "userPO" 	class= "datapro.eibs.beans.UserPos"  		scope="session"/>

<% 
   if (msgBal == null) msgBal = new datapro.eibs.beans.SRV003001Message();   
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

<H3 align="center">Creacion de Balances - Pago y Cobro de Servicios<img src="<%=request.getContextPath()%>/images/e_ibs.gif" align="left" name="EIBS_GIF" ALT="services_balance_new,SRV0030"></H3>
<hr size="4">


<form method="post" action="<%=request.getContextPath()%>/servlet/datapro.eibs.services.JSSRV0030">
  <INPUT TYPE=HIDDEN NAME="SCREEN" VALUE="300">
  
  <TABLE cellspacing=0 cellpadding=2 width="100%" border="0">    
		<TR>
			<TD nowrap width="40%">
				<DIV align="right">Compañ&iacute;a :</DIV>
			</TD>
			<TD nowrap width="60%">
				<INPUT type="text" name="E01BALCIA" size="5" maxlength="4" value="<%= userPO.getHeader16() %>" readonly>
				<INPUT type="text" name="E01BALCIN" size="40" maxlength="35" value="<%= userPO.getHeader17() %>" readonly> 
			</TD>
		</TR>
		<TR>
			<TD nowrap width="40%">
				<DIV align="right">Servicio :</DIV>
			</TD>
			<TD nowrap width="60%">
				<INPUT type="text" name="E01BALSRV" size="5" maxlength="4" value="<%= userPO.getHeader18() %>" readonly>
				<INPUT type="text" name="E01BALSRN" size="40" maxlength="35" value="<%= userPO.getHeader19() %>" readonly>  
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
	        <div align="right">Secuencia : </div>
	      </td>
	      <td nowrap> 
	        <input type="text" name="E01BALSEQ" size="6" maxlength="5" value="<%= msgBal.getE01BALSEQ() %>" onkeypress="enterInteger()">
	      </td>
     	</tr>    
     	<tr id=trclear> 
	      <td nowrap width="20%"> 
	        <div align="right">C&oacute;digo de Cuenta/Consecutivo: </div>
	      </td>
	      <td nowrap> 
	        <input type="text" name="E01BALCTA" size="22" maxlength="20" value="<%= msgBal.getE01BALCTA() %>">
	        <input type="text" name="E01BALCON" size="5" maxlength="4" value="<%= msgBal.getE01BALCON() %>">
	        <img src="<%=request.getContextPath()%>/images/Check.gif" alt="campo obligatorio" align="absbottom" border="0" width="16" height="20"> 
	      </td>
     	</tr>
     	<tr id=trdark> 
	      <td nowrap width="20%"> 
	        <div align="right">Valor Saldo : </div>
	      </td>
	      <td nowrap> 
	        <input type="text" name="E01BALBAL" size="20" maxlength="19" value="<%= msgBal.getE01BALBAL() %>" onkeypress="enterDecimal()">
	      </td>
     	</tr>
     	<tr id=trclear> 
	      <td nowrap> 
	        <div align="right">Fecha de Saldo : </div>
	      </td>
	      <td nowrap>
	      	<input type="text" name="E01BALBD1" size="2" maxlength="2" value="<%= msgBal.getE01BALBD1() %>" onkeypress="enterInteger()">
	      	<input type="text" name="E01BALBD2" size="2" maxlength="2" value="<%= msgBal.getE01BALBD2() %>" onkeypress="enterInteger()">
	      	<input type="text" name="E01BALBD3" size="2" maxlength="2" value="<%= msgBal.getE01BALBD3() %>" onkeypress="enterInteger()">
	      	<a href="javascript:DatePicker(document.forms[0].E01BALBD1,document.forms[0].E01BALBD2,document.forms[0].E01BALBD3)"><img src="<%=request.getContextPath()%>/images/calendar.gif" alt=". . ." align="absbottom" border="0" ></a>
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
