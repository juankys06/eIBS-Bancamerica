<%@page import="datapro.eibs.beans.ESS580101Message"%>
<html>
<head>
<title>Pago de Navieras</title>
<META http-equiv="Content-Style-Type" content="text/css">
<link href="<%=request.getContextPath()%>/pages/style.css" rel="stylesheet">
<script language="Javascript1.1" src="<%=request.getContextPath()%>/pages/e/javascripts/eIBS.jsp"> </SCRIPT>

<jsp:useBean id= "ESS5801List" class= "com.datapro.generics.BeanList"  scope="session" />
</head>
<body>
<% String CusName = "";
   ESS5801List.initRow();
   while(ESS5801List.getNextRow()){
      ESS580101Message  msg = (ESS580101Message) ESS5801List.getRecord();
      CusName = msg.getE01INPFL1();
      break;
   }   
 %>
<H3 align="center">Pagos de Navieras por Internet<img src="<%=request.getContextPath()%>/images/e_ibs.gif" align="left" name="EIBS_GIF" ALT="transaction_list, ESS5801"></H3>
<hr size="4">
<form method="post" action="<%=request.getContextPath()%>/servlet/com.datapro.eibs.internet.JSESS5801" >
<INPUT TYPE=HIDDEN NAME="SCREEN" VALUE="100">
    <p><%= CusName %></p>
    <table border=0 style="border-collapse: collapse" CELLSPACING=0 CELLPADDING=0 width="100%" bgcolor="transparent" >
    	<tr id="trdark">
			<td>Fecha</td>
            <td>Cuenta de Debito</td>
            <td>Monto de Pago</td>
            <td>Numero de la Nave</td>
            <td>Nombre de la Nave</td>
        </tr>
        <% ESS5801List.initRow();
           while(ESS5801List.getNextRow()){
               ESS580101Message  msg = (ESS580101Message) ESS5801List.getRecord(); %> 
         <tr id="trclear">
			<td><%= msg.getE01INPFAD() + "/" + msg.getE01INPFAM() + "/" + msg.getE01INPFAY() %></td>
            <td><%= msg.getE01INPDAC() %></td>
            <td><%= msg.getE01INPPAM() %></td>
            <td><%= msg.getE01INPNAN()  %></td>
            <td><%= msg.getE01INPNAV()  %></td>
          </tr>        
        <% }    %>
	   </table >  
	<br>

	<table class="tbenter" width=100% align=center>
		<tr> 
			<td><div align="center"><input id="EIBSBTN" type=button name="Return" value="Volver" onClick="window.location.href='<%=request.getContextPath()%>/pages/s/ESS5801_enter_customer.jsp'"></div></td>
            <td><div align="center"><input id="EIBSBTN" type=button name="Imprimir" value="Imprimir" onclick="JavaScript:print()"></div></td>
	    </tr>
	</table>
  
</form>
</body>
</html>
