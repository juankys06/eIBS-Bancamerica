<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.0 Transitional//EN">
<html>
<head>
<title>Cambio de Cuenta Contable para Amortizaciones</title>
<META HTTP-EQUIV="Content-Type" CONTENT="text/html; charset=ISO-8859-1">
<META name="GENERATOR" content="IBM WebSphere Page Designer V3.5.2 for Windows">
<META http-equiv="Content-Style-Type" content="text/css">
<link Href="<%=request.getContextPath()%>/pages/style.css" rel="stylesheet">

<script language="Javascript1.1" src="<%=request.getContextPath()%>/pages/s/javascripts/eIBS.jsp"> </SCRIPT>

<jsp:useBean id= "error" 	class= "datapro.eibs.beans.ELEERRMessage"  		scope="session" />
<jsp:useBean id= "userPO" 	class= "datapro.eibs.beans.UserPos" 			scope="session" />
<jsp:useBean id= "account" 	class= "datapro.eibs.beans.ESD300001Message"  	scope="session" />

<%
if ( !error.getERRNUM().equals("0")  ) {
      error.setERRNUM("0");
%>
<SCRIPT Language="Javascript">
        showErrors();
</SCRIPT>
<%}%>

</head>

<body>

<h3 align="center">Cambio de Cuenta Contable para Amortizaciones<br>Confirmación<img src="<%=request.getContextPath()%>/images/e_ibs.gif" align="left" name="EIBS_GIF" ALT="amort_ledger_change_confirmation.jsp, ESD3000"></h3>
<hr size="4">

<form method="post"  action="<%=request.getContextPath()%>/servlet/datapro.eibs.products.JSESD3000">
   	<INPUT TYPE=HIDDEN NAME="SCREEN" VALUE="200">
    <INPUT TYPE=HIDDEN NAME="H01FLGWK1" VALUE="2">

	<h4>Información Básica</h4>
	<table class="tableinfo">
	<tr > 
	<td nowrap> 
		<table  class="tbenter" width="100%" border="0" cellspacing=0 cellpadding=2>
			<tr>
		    	<td align="right"> 
		        	Cliente : 
		        </td>
		    	<td align="left"> 
		            <input type="text" name="E01CUSNUM" size="12" readonly value="<%= account.getE01CUSNUM()%>">
		            <input type="text" name="E01CUSNA1" size="35" readonly value="<%= account.getE01CUSNA1()%>">
		    	</td>
		    </tr>
			<tr>
		    	<td align="right"> 
		        	Número de Amortización : 
		        </td>
		    	<td align="left"> 
		            <input type="text" name="E01OLDACC" size="18" readonly value="<%= account.getE01OLDACC()%>">
		    	</td>
		    </tr>
			<tr>
		    	<td align="right"> 
		        	Moneda : 
		        </td>
		    	<td align="left"> 
		            <input type="text" name="E01OLDCCY" size="4" readonly value="<%= account.getE01OLDCCY()%>">
		    	</td>
		    </tr>
		</table>
	</td>
	</tr>
	</table>
	<h4>Información Previa</h4>
	<table class="tableinfo">
	<tr > 
	<td nowrap> 
		<table  class="tbenter" width="100%" height="75%" border="0" cellspacing=0 cellpadding=2>
			<tr>
		    	<td align="right"> 
		              Banco :
		        </td>
		    	<td align="left"> 
					<input type="text" name="E01OLDBNK" size="4" readonly value="<%= account.getE01OLDBNK()%>">
		            <input type="text" name="E01OBNKNA" size="35" readonly value="<%= account.getE01OBNKNA()%>">
		        </td>
		    </tr>
		    <tr>
		    	<td align="right"> 
		              Sucursal :
		        </td>
		    	<td align="left"> 
					<input type="text" name="E01OLDBRN" size="4" readonly value="<%= account.getE01OLDBRN()%>" >
		            <input type="text" name="E01OBRNNA" size="35" readonly value="<%= account.getE01OBRNNA()%>">
		        </td>
		    </tr>
		    <tr>
		    	<td align="right"> 
		              Cuenta Contable :
		        </td>
		    	<td align="left"> 
					<input type="text" name="E01OLDGLN" size="19" readonly value="<%= account.getE01OLDGLN()%>" maxlength="16">
		            <input type="text" name="E01OGLNDE" size="35" readonly value="<%= account.getE01OGLNDE()%>">
		        </td>
		    </tr>
		</table>
	</td>
	</tr>
	</table>
	<h4>Información Nueva</h4>
	<table class="tableinfo">
	<tr > 
	<td nowrap> 
		<table  class="tbenter" width="100%" height="75%" border="0" cellspacing=0 cellpadding=2>
		    <tr>
		    	<td align="right"> 
		              Banco :
		        </td>
		    	<td align="left"> 
		    		<input type="text" name="E01NEWBNK" size="4" readonly value="<%= account.getE01NEWBNK()%>">
		            <input type="text" name="E01NBNKNA" size="35" readonly value="<%= account.getE01NBNKNA()%>">
		        </td>
		    </tr>
		    <tr>
		    	<td align="right"> 
		              Sucursal :
		        </td>
		    	<td align="left"> 
					<input type="text" name="E01NEWBRN" size="4" readonly value="<%= account.getE01NEWBRN()%>">
		            <input type="text" name="E01NBRNNA" size="35" readonly value="<%= account.getE01NBRNNA()%>">
		        </td>
		    </tr>
		    <tr>
		    	<td align="right"> 
		              Cuenta Contable :
		        </td>
		    	<td align="left"> 
					<input type="text" name="E01NEWGLN" size="19" readonly value="<%= account.getE01NEWGLN()%>" maxlength="16">
		            <input type="text" name="E01NGLNDE" size="35" readonly value="<%= account.getE01NGLNDE()%>">
		        </td>
		    </tr>
		</table>
	</td>
	</tr>
	</table>
  	<p align="center"> 
		<input id="EIBSBTN" type="submit" name="Submit" value="Confirmar">
	</p>            
<SCRIPT language="JavaScript">
	document.forms[0].E01OLDBRN.value = addLeftChar('0',3,document.forms[0].E01OLDBRN.value);
	document.forms[0].E01NEWBRN.value = addLeftChar('0',3,document.forms[0].E01NEWBRN.value);
</SCRIPT>
</form>
</body>
</html>
