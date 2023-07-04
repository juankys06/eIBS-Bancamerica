<%@ taglib uri="/WEB-INF/display.tld" prefix="display" %>
<html>
<head>
<title>Maestro de Agencias</title>
<META HTTP-EQUIV="Content-Type" CONTENT="text/html; charset=ISO-8859-1">
<link Href="<%=request.getContextPath()%>/pages/style.css" rel="stylesheet">

</head>
<jsp:useBean id= "userPO"        class= "datapro.eibs.beans.UserPos"  scope="session" />
<jsp:useBean id= "VendorTransac" class= "com.datapro.generics.BeanList"  scope="session" />
<body>

<script language="Javascript1.1" src="<%=request.getContextPath()%>/pages/s/javascripts/eIBS.jsp"> </SCRIPT>

<H3 align="center">Transacciones Pagos Proveedores por Internet<img src="<%=request.getContextPath()%>/images/e_ibs.gif" align="left" name="EIBS_GIF" ALT="DCIBS_log_transaction_list.jsp, DCIBS"></H3>
<hr size="4">
<form method="post" action="<%=request.getContextPath()%>/servlet/com.datapro.eibs.internet.JSESS2105" >
  	<INPUT TYPE=HIDDEN NAME="SCREEN" VALUE="100">
<%
  int oldvnd = 0;
  int        TotNumTrn = 0;
  java.math.BigDecimal TotValTrn = new java.math.BigDecimal("0.00");
  int        VndNumTrn = 0;
  java.math.BigDecimal VndValTrn = new java.math.BigDecimal("0.00");
  
  VendorTransac.initRow();
  while(VendorTransac.getNextRow()){
     datapro.eibs.beans.ESS210501Message msg = (datapro.eibs.beans.ESS210501Message) VendorTransac.getRecord();
        String rt = "000000" + msg.getE01VENDATB();
		String dat = rt.substring(rt.trim().length()-6);
		String dd = dat.substring(4);
		String mm = dat.substring(2,4);
		String yy = dat.substring(0,2);
		String fmtDat = dd + "/" + mm + "/" + yy;
		String DestPag = "????";
		if(msg.getH01FLGWK1().equals("T") || msg.getH01FLGWK1().equals("M")){
		  DestPag = "CHEQUE";
		}else if(msg.getH01FLGWK1().equals("A")){
		  DestPag = "ACH";
		}else if(msg.getH01FLGWK1().equals("C")){
		  DestPag = "PAGO A CUENTA";
		}
     if(oldvnd != msg.getBigDecimalE01VENNUM().intValue()){         
        if(oldvnd !=0){
 %>
		  <tr id="trdark">
			<td>Total Proveedor</td>
			<td><%= VndNumTrn  %></td>
			<td colspan="5"><%= VndValTrn  %></td>
	      </tr> 
	   </table > 
	   <BR>               

 <%     }
        VndValTrn = new java.math.BigDecimal("0.00");
		VndNumTrn = 0;
 %>
	<table border=0  CELLSPACING=0 CELLPADDING=0 width="100%" bgcolor="transparent" >
    	<tr id="trdark">
			<td width="10%">Proveedor : <%= msg.getE01VENNME() %></td>
			<td width="10%" align="right"> Destino Pago : <%= DestPag   %>  </td>
        </tr>
    </table>             
    <table border=0 style="border-collapse: collapse" CELLSPACING=0 CELLPADDING=0 width="100%" bgcolor="transparent" >
    	<tr id="trdark">
			<td>Fecha</td>
            <td>Usuario</td>
            <td>Monto</td>
            <td>Cta. Debito</td>
            <td>Cta. Contrato</td>
            <td>Descripcion</td>
            <td>Cheque</td>
        </tr>
    <!-- /table --->                 
<%        
     }
%>
        <tr id="trclear">
			<td><%= fmtDat %></td>
            <td><%= msg.getE01USERPMT() %></td>
            <td><%= msg.getE01VENAMT()  %></td>
            <td><%= msg.getE01VENACC() %> </td>
            <td><%= msg.getE01VENREF() %></td>
            <td><%= msg.getE01VENDSC() %></td>
            <td><%= msg.getE01PMTNUM() %></td>        
        </tr>
<%     
     VndNumTrn++;
     TotNumTrn++;
     VndValTrn = VndValTrn.add(msg.getBigDecimalE01VENAMT());
     TotValTrn = TotValTrn.add(msg.getBigDecimalE01VENAMT());
     oldvnd = msg.getBigDecimalE01VENNUM().intValue();
  }
         
%>  
  <!--    Totales Vendor -->
  <% if(VndNumTrn !=0 ) { %>
  
		  <tr id="trdark">
			<td>Total Proveedor</td>
			<td><%= VndNumTrn  %></td>
			<td colspan="5"><%= VndValTrn  %></td>
	      </tr> 
	   </table >   	   
  <% } %>	   
	<br>
	  <!--    Totales Generales  -->
	<table cborder=0 style="border-collapse: collapse" CELLSPACING=0 CELLPADDING=0 width="100%" bgcolor="transparent" >
		<tr id="trdark"> 
		  <td width="20%">Total General</td>
		  <td ><%= TotNumTrn  %></td>
		  <td ><%= TotValTrn  %></td>
		  <TD width="40%">&nbsp;</TD>
	    </tr>
	</table>
	

	<table class="tbenter" width=100% align=center>
		<tr> 
	        <td><div align="center"><input id="EIBSBTN" type=button name="Return" value="Volver" onClick="window.location.href='<%=request.getContextPath()%>/pages/s/ESS2105_vendor_list.jsp'"></div></td>
   	        <td><div align="center"><input id="EIBSBTN" type=button name="Return" value="Imprimir" onClick="javascript:eIBSPrint()"></div></td>
			<td><div align="center"><input id="EIBSBTN" type=button name="Download" value="Descargar" onClick="window.location.href='<%=request.getContextPath()%>/pages/s/ESS2105_vendor_transactions_download.jsp'"></div></td>
	    </tr>
	</table>
  
</form>
</body>
</html>
