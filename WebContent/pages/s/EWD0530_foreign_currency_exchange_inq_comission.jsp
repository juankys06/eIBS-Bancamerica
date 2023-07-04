<html> 
<head>
<title>Compraventa Moneda Extranjera</title>
<META HTTP-EQUIV="Content-Type" CONTENT="text/html; charset=ISO-8859-1">
<link Href="<%=request.getContextPath()%>/pages/style.css" rel="stylesheet">
<%@ page import ="datapro.eibs.master.Util" %>

<script language="Javascript1.1" src="<%=request.getContextPath()%>/pages/s/javascripts/eIBS.jsp"> </SCRIPT>

<jsp:useBean id= "msgCom" 	class= "datapro.eibs.beans.EWD0530DSMessage" scope="session" />

</head>
<body>

<H3 align="center">Detalle de Comisiones de Compraventa Moneda Extranjera<img src="<%=request.getContextPath()%>/images/e_ibs.gif" align="left" name="EIBS_GIF" ALT="foreign_currency_exchange_inq_comission, EWD0530"></H3>
<hr size="4">


<form method="post" action="<%=request.getContextPath()%>/servlet/datapro.eibs.helps.JSEWD0530">

  <table class="tableinfo">
   <tr> 
   <td>
    <table cellspacing=0 cellpadding=2 width="100%" border="0">    
		<tr>
			<td nowrap width="40%">
				<div align="center">Descripcion</div>
			</td>
			<td nowrap width="40%">
				<div align="center">Valor</div>
			</td>
		</tr>
		<%if (!msgCom.getE01COMI01().equals("0.00")) {%>
		<tr>
			<td nowrap width="40%">
				<input type="text" name="E01COMD01" size="35" value="<%= msgCom.getE01COMD01() %>" readonly>
			</td>
			<td nowrap width="40%">
				<input type="text" name="E01COMI01" size="20" value="<%= msgCom.getE01COMI01() %>" readonly> 
			</td>
		</tr>
		<%}%>
		<%if (!msgCom.getE01COMI02().equals("0.00")) {%>
		<tr>
			<td nowrap width="40%">
				<input type="text" name="E01COMD02" size="35" value="<%= msgCom.getE01COMD02() %>" readonly>
			</td>
			<td nowrap width="40%">
				<input type="text" name="E01COMI02" size="20" value="<%= msgCom.getE01COMI02() %>" readonly> 
			</td>
		</tr>
		<%}%>
		<%if (!msgCom.getE01COMI03().equals("0.00")) {%>
		<tr>
			<td nowrap width="40%">
				<input type="text" name="E01COMD03" size="35" value="<%= msgCom.getE01COMD03() %>" readonly>
			</td>
			<td nowrap width="40%">
				<input type="text" name="E01COMI03" size="20" value="<%= msgCom.getE01COMI03() %>" readonly> 
			</td>
		</tr>
		<%}%>
		<%if (!msgCom.getE01COMI04().equals("0.00")) {%>
		<tr>
			<td nowrap width="40%">
				<input type="text" name="E01COMD04" size="35" value="<%= msgCom.getE01COMD04() %>" readonly>
			</td>
			<td nowrap width="40%">
				<input type="text" name="E01COMI04" size="20" value="<%= msgCom.getE01COMI04() %>" readonly> 
			</td>
		</tr>
		<%}%>
		<%if (!msgCom.getE01COMI05().equals("0.00")) {%>
		<tr>
			<td nowrap width="40%">
				<input type="text" name="E01COMD05" size="35" value="<%= msgCom.getE01COMD05() %>" readonly>
			</td>
			<td nowrap width="40%">
				<input type="text" name="E01COMI05" size="20" value="<%= msgCom.getE01COMI05() %>" readonly> 
			</td>
		</tr>
		<%}%>
		<%if (!msgCom.getE01COMI06().equals("0.00")) {%>
		<tr>
			<td nowrap width="40%">
				<input type="text" name="E01COMD06" size="35" value="<%= msgCom.getE01COMD06() %>" readonly>
			</td>
			<td nowrap width="40%">
				<input type="text" name="E01COMI06" size="20" value="<%= msgCom.getE01COMI06() %>" readonly> 
			</td>
		</tr>
		<%}%>
		<%if (!msgCom.getE01COMI07().equals("0.00")) {%>
		<tr>
			<td nowrap width="40%">
				<input type="text" name="E01COMD07" size="35" value="<%= msgCom.getE01COMD07() %>" readonly>
			</td>
			<td nowrap width="40%">
				<input type="text" name="E01COMI07" size="20" value="<%= msgCom.getE01COMI07() %>" readonly> 
			</td>
		</tr>
		<%}%>
		<%if (!msgCom.getE01COMI08().equals("0.00")) {%>
		<tr>
			<td nowrap width="40%">
				<input type="text" name="E01COMD08" size="35" value="<%= msgCom.getE01COMD08() %>" readonly>
			</td>
			<td nowrap width="40%">
				<input type="text" name="E01COMI08" size="20" value="<%= msgCom.getE01COMI08() %>" readonly> 
			</td>
		</tr>
		<%}%>
		<%if (!msgCom.getE01COMI09().equals("0.00")) {%>
		<tr>
			<td nowrap width="40%">
				<input type="text" name="E01COMD09" size="35" value="<%= msgCom.getE01COMD09() %>" readonly>
			</td>
			<td nowrap width="40%">
				<input type="text" name="E01COMI09" size="20" value="<%= msgCom.getE01COMI09() %>" readonly> 
			</td>
		</tr>
		<%}%>
		<%if (!msgCom.getE01COMI10().equals("0.00")) {%>
		<tr>
			<td nowrap width="40%">
				<input type="text" name="E01COMD10" size="35" value="<%= msgCom.getE01COMD10() %>" readonly>
			</td>
			<td nowrap width="40%">
				<input type="text" name="E01COMI10" size="20" value="<%= msgCom.getE01COMI10() %>" readonly> 
			</td>
		</tr>
		<%}%>
		<%if (!msgCom.getE01COMI11().equals("0.00")) {%>
		<tr>
			<td nowrap width="40%">
				<input type="text" name="E01COMD11" size="35" value="<%= msgCom.getE01COMD11() %>" readonly>
			</td>
			<td nowrap width="40%">
				<input type="text" name="E01COMI11" size="20" value="<%= msgCom.getE01COMI11() %>" readonly> 
			</td>
		</tr>
		<%}%>
		<%if (!msgCom.getE01COMI12().equals("0.00")) {%>
		<tr>
			<td nowrap width="40%">
				<input type="text" name="E01COMD12" size="35" value="<%= msgCom.getE01COMD12() %>" readonly>
			</td>
			<td nowrap width="40%">
				<input type="text" name="E01COMI12" size="20" value="<%= msgCom.getE01COMI12() %>" readonly> 
			</td>
		</tr>
		<%}%>
		<%if (!msgCom.getE01COMI13().equals("0.00")) {%>
		<tr>
			<td nowrap width="40%">
				<input type="text" name="E01COMD13" size="35" value="<%= msgCom.getE01COMD13() %>" readonly>
			</td>
			<td nowrap width="40%">
				<input type="text" name="E01COMI13" size="20" value="<%= msgCom.getE01COMI13() %>" readonly> 
			</td>
		</tr>
		<%}%>
		<%if (!msgCom.getE01COMI14().equals("0.00")) {%>
		<tr>
			<td nowrap width="40%">
				<input type="text" name="E01COMD14" size="35" value="<%= msgCom.getE01COMD14() %>" readonly>
			</td>
			<td nowrap width="40%">
				<input type="text" name="E01COMI14" size="20" value="<%= msgCom.getE01COMI14() %>" readonly> 
			</td>
		</tr>
		<%}%>
		<%if (!msgCom.getE01COMI15().equals("0.00")) {%>
		<tr>
			<td nowrap width="40%">
				<input type="text" name="E01COMD15" size="35" value="<%= msgCom.getE01COMD15() %>" readonly>
			</td>
			<td nowrap width="40%">
				<input type="text" name="E01COMI15" size="20" value="<%= msgCom.getE01COMI15() %>" readonly> 
			</td>
		</tr>
		<%}%>
		<%if (!msgCom.getE01COMI16().equals("0.00")) {%>
		<tr>
			<td nowrap width="40%">
				<input type="text" name="E01COMD16" size="35" value="<%= msgCom.getE01COMD16() %>" readonly>
			</td>
			<td nowrap width="40%">
				<input type="text" name="E01COMI16" size="20" value="<%= msgCom.getE01COMI16() %>" readonly> 
			</td>
		</tr>
		<%}%>
		<%if (!msgCom.getE01COMI17().equals("0.00")) {%>
		<tr>
			<td nowrap width="40%">
				<input type="text" name="E01COMD17" size="35" value="<%= msgCom.getE01COMD17() %>" readonly>
			</td>
			<td nowrap width="40%">
				<input type="text" name="E01COMI17" size="20" value="<%= msgCom.getE01COMI17() %>" readonly> 
			</td>
		</tr>
		<%}%>
		<%if (!msgCom.getE01COMI18().equals("0.00")) {%>
		<tr>
			<td nowrap width="40%">
				<input type="text" name="E01COMD18" size="35" value="<%= msgCom.getE01COMD18() %>" readonly>
			</td>
			<td nowrap width="40%">
				<input type="text" name="E01COMI18" size="20" value="<%= msgCom.getE01COMI18() %>" readonly> 
			</td>
		</tr>
		<%}%>
		<%if (!msgCom.getE01COMI19().equals("0.00")) {%>
		<tr>
			<td nowrap width="40%">
				<input type="text" name="E01COMD19" size="35" value="<%= msgCom.getE01COMD19() %>" readonly>
			</td>
			<td nowrap width="40%">
				<input type="text" name="E01COMI19" size="20" value="<%= msgCom.getE01COMI19() %>" readonly> 
			</td>
		</tr>
		<%}%>
		<%if (!msgCom.getE01COMI20().equals("0.00")) {%>
		<tr>
			<td nowrap width="40%">
				<input type="text" name="E01COMD20" size="35" value="<%= msgCom.getE01COMD20() %>" readonly>
			</td>
			<td nowrap width="40%">
				<input type="text" name="E01COMI20" size="20" value="<%= msgCom.getE01COMI20() %>" readonly> 
			</td>
		</tr>
		<%}%>
     </table>
    </td>
   </tr>
  </table>
</form>
</body>
</html>
