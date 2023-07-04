<%@ taglib uri="/WEB-INF/display.tld" prefix="display" %>
<html>
<head>
<title>Maestro de Agencias</title>
<META HTTP-EQUIV="Content-Type" CONTENT="text/html; charset=ISO-8859-1">
<link Href="<%=request.getContextPath()%>/pages/style.css" rel="stylesheet">

</head>
<jsp:useBean id= "userPO"        class= "datapro.eibs.beans.UserPos"  scope="session" />
<!-- jsp:useBean id= "VendorTransacD" class= "datapro.eibs.beans.JBObjList"  scope="session" / -->
<jsp:useBean id= "VendorTransac" class= "com.datapro.generics.BeanList"  scope="session" />
<body>

<script language="Javascript1.1" src="<%=request.getContextPath()%>/pages/e/javascripts/eIBS.jsp"> </SCRIPT>

<H3 align="center">Transacciones Pagos Proveedores por Internet<img src="<%=request.getContextPath()%>/images/e_ibs.gif" align="left" name="EIBS_GIF" ALT="vendor_transactions_download, ESS2105"></H3>
<hr size="4">
<form method="post" action="<%=request.getContextPath()%>/servlet/com.datapro.eibs.internet.JSESS2105" >
  	<INPUT TYPE=HIDDEN NAME="SCREEN" VALUE="100">
  
	<table border=0 style="border-collapse: collapse" CELLSPACING=0 CELLPADDING=0 width="100%" bgcolor="transparent" >
    	<tr>
			<td>
				<display:table  width="100%" name="VendorTransac" property="list" scope="session"  export="true" decorator="com.datapro.eibs.internet.databeans.ESS2105Wrapper">
  					<display:column property="vndname" 		align="center" title="Proveedor"/> 
  					<display:column property="paymentdate" 	align="center" title="Fecha Pago"/> 
  					<display:column property="payamt" 		align="left"   title="Monto Pago"/> 
  					<display:column property="reference" 	align="left"   title="Referencia"/> 
  					<display:column property="description" 	align="left"   title="Descripcion"/> 
  					<display:column property="userpmt" 	    align="right"  title="Usuario Internet"/> 
  					<!-- display:column property="cheque" 	align="right"  title="Cheque"/ --> 
				</display:table> 
			</td>
		</tr>
	</table>	

	<br>

	<table class="tbenter" width=100% align=center>
		<tr> 
			<td><div align="center"><input id="EIBSBTN" type=button name="Return" value="Volver" onClick="window.location.href='<%=request.getContextPath()%>/pages/s/ESS2105_vendor_transactions.jsp'"></div></td>
	    </tr>
	</table>
  
</form>
</body>
</html>
