<html> 
<head>
<title>Compraventa Moneda Extranjera</title>
<META HTTP-EQUIV="Content-Type" CONTENT="text/html; charset=ISO-8859-1">
<link Href="<%=request.getContextPath()%>/pages/style.css" rel="stylesheet">
<%@ page import ="datapro.eibs.master.Util" %>

<script language="Javascript1.1" src="<%=request.getContextPath()%>/pages/s/javascripts/eIBS.jsp"> </SCRIPT>

<jsp:useBean id= "msgInst" 	class= "datapro.eibs.beans.EPR033002Message" scope="session" />

</head>
<body>

<H3 align="center">Detalle de Comisiones de Compraventa Moneda Extranjera<img src="<%=request.getContextPath()%>/images/e_ibs.gif" align="left" name="EIBS_GIF" ALT="foreign_currency_exchange_comission, EPR0330"></H3>
<hr size="4">


<form method="post" action="<%=request.getContextPath()%>/servlet/datapro.eibs.forex.JSEPR0330">

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
		<%if (!msgInst.getE02COMI01().equals("0.00")) {%>
		<tr>
			<td nowrap width="40%">
				<input type="text" name="E02COMD01" size="35" value="<%= msgInst.getE02COMD01() %>" readonly>
			</td>
			<td nowrap width="40%">
				<input type="text" name="E02COMI01" size="20" value="<%= msgInst.getE02COMI01() %>" readonly> 
			</td>
		</tr>
		<%}%>
		<%if (!msgInst.getE02COMI02().equals("0.00")) {%>
		<tr>
			<td nowrap width="40%">
				<input type="text" name="E02COMD02" size="35" value="<%= msgInst.getE02COMD02() %>" readonly>
			</td>
			<td nowrap width="40%">
				<input type="text" name="E02COMI02" size="20" value="<%= msgInst.getE02COMI02() %>" readonly> 
			</td>
		</tr>
		<%}%>
		<%if (!msgInst.getE02COMI03().equals("0.00")) {%>
		<tr>
			<td nowrap width="40%">
				<input type="text" name="E02COMD03" size="35" value="<%= msgInst.getE02COMD03() %>" readonly>
			</td>
			<td nowrap width="40%">
				<input type="text" name="E02COMI03" size="20" value="<%= msgInst.getE02COMI03() %>" readonly> 
			</td>
		</tr>
		<%}%>
		<%if (!msgInst.getE02COMI04().equals("0.00")) {%>
		<tr>
			<td nowrap width="40%">
				<input type="text" name="E02COMD04" size="35" value="<%= msgInst.getE02COMD04() %>" readonly>
			</td>
			<td nowrap width="40%">
				<input type="text" name="E02COMI04" size="20" value="<%= msgInst.getE02COMI04() %>" readonly> 
			</td>
		</tr>
		<%}%>
		<%if (!msgInst.getE02COMI05().equals("0.00")) {%>
		<tr>
			<td nowrap width="40%">
				<input type="text" name="E02COMD05" size="35" value="<%= msgInst.getE02COMD05() %>" readonly>
			</td>
			<td nowrap width="40%">
				<input type="text" name="E02COMI05" size="20" value="<%= msgInst.getE02COMI05() %>" readonly> 
			</td>
		</tr>
		<%}%>
		<%if (!msgInst.getE02COMI06().equals("0.00")) {%>
		<tr>
			<td nowrap width="40%">
				<input type="text" name="E02COMD06" size="35" value="<%= msgInst.getE02COMD06() %>" readonly>
			</td>
			<td nowrap width="40%">
				<input type="text" name="E02COMI06" size="20" value="<%= msgInst.getE02COMI06() %>" readonly> 
			</td>
		</tr>
		<%}%>
		<%if (!msgInst.getE02COMI07().equals("0.00")) {%>
		<tr>
			<td nowrap width="40%">
				<input type="text" name="E02COMD07" size="35" value="<%= msgInst.getE02COMD07() %>" readonly>
			</td>
			<td nowrap width="40%">
				<input type="text" name="E02COMI07" size="20" value="<%= msgInst.getE02COMI07() %>" readonly> 
			</td>
		</tr>
		<%}%>
		<%if (!msgInst.getE02COMI08().equals("0.00")) {%>
		<tr>
			<td nowrap width="40%">
				<input type="text" name="E02COMD08" size="35" value="<%= msgInst.getE02COMD08() %>" readonly>
			</td>
			<td nowrap width="40%">
				<input type="text" name="E02COMI08" size="20" value="<%= msgInst.getE02COMI08() %>" readonly> 
			</td>
		</tr>
		<%}%>
		<%if (!msgInst.getE02COMI09().equals("0.00")) {%>
		<tr>
			<td nowrap width="40%">
				<input type="text" name="E02COMD09" size="35" value="<%= msgInst.getE02COMD09() %>" readonly>
			</td>
			<td nowrap width="40%">
				<input type="text" name="E02COMI09" size="20" value="<%= msgInst.getE02COMI09() %>" readonly> 
			</td>
		</tr>
		<%}%>
		<%if (!msgInst.getE02COMI10().equals("0.00")) {%>
		<tr>
			<td nowrap width="40%">
				<input type="text" name="E02COMD10" size="35" value="<%= msgInst.getE02COMD10() %>" readonly>
			</td>
			<td nowrap width="40%">
				<input type="text" name="E02COMI10" size="20" value="<%= msgInst.getE02COMI10() %>" readonly> 
			</td>
		</tr>
		<%}%>
		<%if (!msgInst.getE02COMI11().equals("0.00")) {%>
		<tr>
			<td nowrap width="40%">
				<input type="text" name="E02COMD11" size="35" value="<%= msgInst.getE02COMD11() %>" readonly>
			</td>
			<td nowrap width="40%">
				<input type="text" name="E02COMI11" size="20" value="<%= msgInst.getE02COMI11() %>" readonly> 
			</td>
		</tr>
		<%}%>
		<%if (!msgInst.getE02COMI12().equals("0.00")) {%>
		<tr>
			<td nowrap width="40%">
				<input type="text" name="E02COMD12" size="35" value="<%= msgInst.getE02COMD12() %>" readonly>
			</td>
			<td nowrap width="40%">
				<input type="text" name="E02COMI12" size="20" value="<%= msgInst.getE02COMI12() %>" readonly> 
			</td>
		</tr>
		<%}%>
		<%if (!msgInst.getE02COMI13().equals("0.00")) {%>
		<tr>
			<td nowrap width="40%">
				<input type="text" name="E02COMD13" size="35" value="<%= msgInst.getE02COMD13() %>" readonly>
			</td>
			<td nowrap width="40%">
				<input type="text" name="E02COMI13" size="20" value="<%= msgInst.getE02COMI13() %>" readonly> 
			</td>
		</tr>
		<%}%>
		<%if (!msgInst.getE02COMI14().equals("0.00")) {%>
		<tr>
			<td nowrap width="40%">
				<input type="text" name="E02COMD14" size="35" value="<%= msgInst.getE02COMD14() %>" readonly>
			</td>
			<td nowrap width="40%">
				<input type="text" name="E02COMI14" size="20" value="<%= msgInst.getE02COMI14() %>" readonly> 
			</td>
		</tr>
		<%}%>
		<%if (!msgInst.getE02COMI15().equals("0.00")) {%>
		<tr>
			<td nowrap width="40%">
				<input type="text" name="E02COMD15" size="35" value="<%= msgInst.getE02COMD15() %>" readonly>
			</td>
			<td nowrap width="40%">
				<input type="text" name="E02COMI15" size="20" value="<%= msgInst.getE02COMI15() %>" readonly> 
			</td>
		</tr>
		<%}%>
		<%if (!msgInst.getE02COMI16().equals("0.00")) {%>
		<tr>
			<td nowrap width="40%">
				<input type="text" name="E02COMD16" size="35" value="<%= msgInst.getE02COMD16() %>" readonly>
			</td>
			<td nowrap width="40%">
				<input type="text" name="E02COMI16" size="20" value="<%= msgInst.getE02COMI16() %>" readonly> 
			</td>
		</tr>
		<%}%>
		<%if (!msgInst.getE02COMI17().equals("0.00")) {%>
		<tr>
			<td nowrap width="40%">
				<input type="text" name="E02COMD17" size="35" value="<%= msgInst.getE02COMD17() %>" readonly>
			</td>
			<td nowrap width="40%">
				<input type="text" name="E02COMI17" size="20" value="<%= msgInst.getE02COMI17() %>" readonly> 
			</td>
		</tr>
		<%}%>
		<%if (!msgInst.getE02COMI18().equals("0.00")) {%>
		<tr>
			<td nowrap width="40%">
				<input type="text" name="E02COMD18" size="35" value="<%= msgInst.getE02COMD18() %>" readonly>
			</td>
			<td nowrap width="40%">
				<input type="text" name="E02COMI18" size="20" value="<%= msgInst.getE02COMI18() %>" readonly> 
			</td>
		</tr>
		<%}%>
		<%if (!msgInst.getE02COMI19().equals("0.00")) {%>
		<tr>
			<td nowrap width="40%">
				<input type="text" name="E02COMD19" size="35" value="<%= msgInst.getE02COMD19() %>" readonly>
			</td>
			<td nowrap width="40%">
				<input type="text" name="E02COMI19" size="20" value="<%= msgInst.getE02COMI19() %>" readonly> 
			</td>
		</tr>
		<%}%>
		<%if (!msgInst.getE02COMI20().equals("0.00")) {%>
		<tr>
			<td nowrap width="40%">
				<input type="text" name="E02COMD20" size="35" value="<%= msgInst.getE02COMD20() %>" readonly>
			</td>
			<td nowrap width="40%">
				<input type="text" name="E02COMI20" size="20" value="<%= msgInst.getE02COMI20() %>" readonly> 
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
