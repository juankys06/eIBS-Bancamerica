<html> 
<head>
<title>Mantenimiento de Balances - Pago y Cobro de Servicios</title>
<META HTTP-EQUIV="Content-Type" CONTENT="text/html; charset=ISO-8859-1">
<link Href="<%=request.getContextPath()%>/pages/style.css" rel="stylesheet">
<%@ page import ="datapro.eibs.master.Util" %>

<script language="Javascript1.1" src="<%=request.getContextPath()%>/pages/s/javascripts/eIBS.jsp"> </SCRIPT>

<jsp:useBean id= "mtList" class= "datapro.eibs.beans.JBObjList"  scope="session" />
<jsp:useBean id= "msgMT1" class= "datapro.eibs.beans.SRV003001Message"  scope="session" />
<jsp:useBean id= "error" class= "datapro.eibs.beans.ELEERRMessage"  scope="session" />
<jsp:useBean id= "userPO" 	class= "datapro.eibs.beans.UserPos"  		scope="session"/>

<% 
   int row = 0;
   try { row = Integer.parseInt(request.getParameter("ROW"));} catch (Exception e) {}
   mtList.setCurrentRow(row);
   datapro.eibs.beans.SRV003001Message msgMT = (datapro.eibs.beans.SRV003001Message) mtList.getRecord();
   
%>
<% 
 if ( !error.getERRNUM().equals("0")  ) {
      error.setERRNUM("0");
      msgMT = msgMT1;
 %>
     <SCRIPT Language="Javascript">
            showErrors();
     </SCRIPT>
 <%
 }
%>
</head>
<body>

<H3 align="center">Consulta de Balances - Pago y Cobro de Servicios<img src="<%=request.getContextPath()%>/images/e_ibs.gif" align="left" name="EIBS_GIF" ALT="services_balance_maint,SRV0030"></H3>
<hr size="4">


<form method="post" action="<%=request.getContextPath()%>/servlet/datapro.eibs.services.JSSRV0030">
 
  <INPUT TYPE=HIDDEN NAME="SCREEN" VALUE="400">
  <INPUT TYPE=HIDDEN NAME="ROW" VALUE="<%= row %>">
  
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
		<TR>
			<TD nowrap width="40%">
				<DIV align="right">Secuencia :</DIV>
			</TD>
			<TD nowrap width="60%">
				<INPUT type="text" name="E01BALSEQ" size="6" maxlength="5" value="<%= msgMT.getE01BALSEQ() %>" readonly>  
			</TD>
		</TR>
		<tr> 
	      <td nowrap width="40%"> 
	        <div align="right">C&oacute;digo de Cuenta : </div>
	      </td>
	      <td nowrap width="60%"> 
	        <input type="text" name="E01BALCTA" size="22" maxlength="20" readonly value="<%= msgMT.getE01BALCTA() %>">
	      </td>
     	</tr>
     	<tr> 
	      <td nowrap width="40%"> 
	        <div align="right">Consecutivo: </div>
	      </td>
	      <td nowrap width="60%"> 
	        <input type="text" name="E01BALCON" size="5" maxlength="4" readonly value="<%= msgMT.getE01BALCON() %>">
	      </td>
     	</tr>
     	
  </TABLE>
  <h4></h4>
  <table class="tableinfo">
   <tr> 
   <td>
    <table cellspacing=0 cellpadding=2 width="100%" border="0">    
     	<tr id=trclear> 
	      <td nowrap width="20%"> 
	        <div align="right">Valor Saldo : </div>
	      </td>
	      <td nowrap> 
	        <input type="text" name="E01BALBAL" size="20" maxlength="19" value="<%= msgMT.getE01BALBAL() %>" readonly>
	      </td>
     	</tr>
     	<tr id=trdark> 
	      <td nowrap> 
	        <div align="right">Fecha de Saldo : </div>
	      </td>
	      <td nowrap>
	      	<input type="text" name="E01BALBD1" size="2" maxlength="2" value="<%= msgMT.getE01BALBD1() %>" readonly>
	      	<input type="text" name="E01BALBD2" size="2" maxlength="2" value="<%= msgMT.getE01BALBD2() %>" readonly>
	      	<input type="text" name="E01BALBD3" size="2" maxlength="2" value="<%= msgMT.getE01BALBD3() %>" readonly>

	      </td>
     	</tr>
     	<tr id=trclear> 
		  <td> 
		     <div align="right">Valor Aplicado : </div>        
		  </td>
      	  <td nowrap> 
      	    <input type="text" name="E01BALAPP" size="20" maxlength="19" value="<%= msgMT.getE01BALAPP() %>" readonly>
      	  </td>     
      	</tr>
     	<tr id=trdark> 
		  <td> 
		     <div align="right">Status : </div>        
		  </td>
      	  <td nowrap> 
     	    <input type="text" name="E01BALSTS" size="20" maxlength="19" value="<%if (msgMT.getE01BALSTS().equals("P")) out.print("Pendiente"); 
     	    else if (msgMT.getE01BALSTS().equals("A")) out.print("Aplicado");
       	    else if (msgMT.getE01BALSTS().equals("R")) out.print("Aplicado Parcial");
       	    else if (msgMT.getE01BALSTS().equals("E")) out.print("Error");%>" readonly>
          </td>    
      	</tr>
      	<tr id=trclear> 
		  <td> 
		     <div align="right">C&oacute;digo de Error : </div>        
		  </td>
      	  <td nowrap> 
      	    <input type="text" name="E01BALERR" size="5" maxlength="4" value="<%= msgMT.getE01BALERR() %>" readonly>
      	    <input type="text" name="E01BALETX" size="80" maxlength="74" value="<%= msgMT.getE01BALETX() %>" readonly>
      	  </td>     
      	</tr>
     	<tr id=trdark> 
	      <td nowrap> 
	        <div align="right">Fecha de Creaci&oacute;n : </div>
	      </td>
	      <td nowrap>
	      	<input type="text" name="E01BALCD1" size="2" maxlength="2" value="<%= msgMT.getE01BALCD1() %>" readonly>
	      	<input type="text" name="E01BALCD2" size="2" maxlength="2" value="<%= msgMT.getE01BALCD2() %>" readonly>
	      	<input type="text" name="E01BALCD3" size="2" maxlength="2" value="<%= msgMT.getE01BALCD3() %>" readonly>
	      </td>
     	</tr>
     	<tr id=trclear> 
		  <td> 
		     <div align="right">Usuario Crea : </div>        
		  </td>
      	  <td nowrap> 
      	    <input type="text" name="E01BALCRU" size="12" maxlength="10" readonly value="<%= msgMT.getE01BALCRU() %>">
      	  </td>     
      	</tr>
      	<tr id=trdark> 
	      <td nowrap> 
	        <div align="right">Fecha de Aplicaci&oacute;n : </div>
	      </td>
	      <td nowrap>
	      	<input type="text" name="E01BALAD1" size="2" maxlength="2" readonly value="<%= msgMT.getE01BALAD1() %>">
	      	<input type="text" name="E01BALAD2" size="2" maxlength="2" readonly value="<%= msgMT.getE01BALAD2() %>">
	      	<input type="text" name="E01BALAD3" size="2" maxlength="2" readonly value="<%= msgMT.getE01BALAD3() %>">
	      </td>
     	</tr>
     	<tr id=trclear> 
		  <td> 
		     <div align="right">Usuario Aplica : </div>        
		  </td>
      	  <td nowrap> 
      	    <input type="text" name="E01BALAPU" size="12" maxlength="10" readonly value="<%= msgMT.getE01BALAPU() %>">
      	  </td>     
      	</tr>
     	<tr id=trdark> 
	      <td nowrap> 
	        <div align="right">Fecha Ultima Actualizaci&oacute;n : </div>
	      </td>
	      <td nowrap>
	      	<input type="text" name="E01BALUD1" size="2" maxlength="2" readonly value="<%= msgMT.getE01BALUD1() %>">
	      	<input type="text" name="E01BALUD2" size="2" maxlength="2" readonly value="<%= msgMT.getE01BALUD2() %>">
	      	<input type="text" name="E01BALUD3" size="2" maxlength="2" readonly value="<%= msgMT.getE01BALUD3() %>">
	      </td>
     	</tr>
     	<tr id=trclear> 
		  <td> 
		     <div align="right">Usuario Actualiza : </div>        
		  </td>
      	  <td nowrap> 
      	    <input type="text" name="E01BALUPU" size="12" maxlength="10" readonly value="<%= msgMT.getE01BALUPU() %>">
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
