<%@ taglib uri="/WEB-INF/display.tld" prefix="display" %>
<html>
<head>
<title>Maestro de Agencias</title>
<META HTTP-EQUIV="Content-Type" CONTENT="text/html; charset=ISO-8859-1">
<link Href="<%=request.getContextPath()%>/pages/style.css" rel="stylesheet">

</head>
<jsp:useBean id= "userPO"        class= "datapro.eibs.beans.UserPos"  scope="session" />
<jsp:useBean id= "VendorRegistered" class= "com.datapro.generics.BeanList"  scope="session" />
<body>

<script language="Javascript1.1" src="<%=request.getContextPath()%>/pages/e/javascripts/eIBS.jsp"> </SCRIPT>

<H3 align="center"> Proveedores Registrados por Internet<img src="<%=request.getContextPath()%>/images/e_ibs.gif" align="left" name="EIBS_GIF" ALT="vendor_register.jsp, ESS2105"></H3>
<hr size="4">
<form method="post" action="<%=request.getContextPath()%>/servlet/com.datapro.eibs.internet.JSESS2105" >
  	<INPUT TYPE=HIDDEN NAME="SCREEN" VALUE="100">
<%
  int oldvnd = 0;
  int        TotNumTrn = 0;
  int        VndNumTrn = 0;  
  VendorRegistered.initRow();
  while(VendorRegistered.getNextRow()){
     datapro.eibs.beans.ESS210501Message msg = (datapro.eibs.beans.ESS210501Message) VendorRegistered.getRecord();
    	String rt = "000000" + msg.getE01VENDATB();
		String dat = rt.substring(rt.trim().length()-6);
		String dd = dat.substring(4);
		String mm = dat.substring(2,4);
		String yy = dat.substring(0,2);
		String fmtDat = dd + "/" + mm + "/" + yy;
     if(oldvnd != msg.getBigDecimalE01VENNUM().intValue()){
        if(oldvnd !=0 ){
 %>
		  <tr id="trdark">
			<td>Total Proveedor</td>
			<td><%= VndNumTrn  %></td>
			<td colspan="4"></td>
	      </tr> 
	   </table > 
	   <BR>               

 <%
       }
       VndNumTrn = 0;  
 %>
	<table border=1  CELLSPACING=0 CELLPADDING=0 width="100%" bgcolor="transparent" >
    	<tr id="trdark">
			<td width="10%">Proveedor : <%= msg.getE01VENNME() %></td>
        </tr>
    </table>             
    <table border=0 style="border-collapse: collapse" CELLSPACING=0 CELLPADDING=0 width="100%" bgcolor="transparent" >
    	<tr id="trdark">
			<td>Fecha</td>
            <td>Usuario</td>
            <td>Cta. Contrato</td>
            <td>Descripcion</td>
        </tr>
    <!-- /table --->                 
<%        
     }
%>
        <tr id="trclear">
			<td><%= fmtDat %></td>
            <td><%= msg.getE01USERPMT() %></td>
            <td><%= msg.getE01VENREF() %></td>
            <td><%= msg.getE01VENDSC() %></td>        
        </tr>
<%     
     VndNumTrn++;
     TotNumTrn++;
     oldvnd = msg.getBigDecimalE01VENNUM().intValue();
  }
         
%>  
  <!--    Totales Vendor -->
  <% if(VndNumTrn !=0 ) { %>
  
		  <tr id="trdark">
			<td>Total Proveedor</td>
			<td><%= VndNumTrn  %></td>
			<td colspan="4"></td>
	      </tr> 
	   </table >   	   
  <% } %>	   
	<br>
	  <!--    Totales Generales  -->
	<table cborder=0 style="border-collapse: collapse" CELLSPACING=0 CELLPADDING=0 width="100%" bgcolor="transparent" >
		<tr id="trdark"> 
		  <td width="20%">Total General</td>
		  <td ><%= TotNumTrn  %></td>
		  <td ></td>
		  <TD width="40%">&nbsp;</TD>
	    </tr>
	</table>
	

	<table class="tbenter" width=100% align=center>
		<tr> 
	        <td><div align="center"><input id="EIBSBTN" type=button name="Return" value="Return" onClick="window.location.href='<%=request.getContextPath()%>/pages/s/ESS2105_vendor_list_register.jsp'"></div></td>
			<td><div align="center"><input id="EIBSBTN" type=button name="Download" value="Download" onClick="window.location.href='<%=request.getContextPath()%>/pages/s/ESS2105_vendor_registered_download.jsp'"></div></td>
	    </tr>
	</table>
  
</form>
</body>
</html>
