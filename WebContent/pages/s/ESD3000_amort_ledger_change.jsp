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

<SCRIPT language="JavaScript">
function validate(){
	document.forms[0].E01OLDBRN.value = addLeftChar('0',3,document.forms[0].E01OLDBRN.value);
	document.forms[0].E01NEWBRN.value = addLeftChar('0',3,document.forms[0].E01NEWBRN.value);
}
</SCRIPT>

</head>
<body>

<h3 align="center">Cambio de Cuenta Contable para Amortizaciones<img src="<%=request.getContextPath()%>/images/e_ibs.gif" align="left" name="EIBS_GIF" ALT="amort_ledger_change.jsp, ESD3000"></h3>
<hr size="4">

<form method="post" name="ledger_change" action="<%=request.getContextPath()%>/servlet/datapro.eibs.products.JSESD3000" onSubmit="validate()">
    <INPUT TYPE=HIDDEN NAME="SCREEN" VALUE="100">
    <INPUT TYPE=HIDDEN NAME="H01FLGWK1" VALUE="2">
    <INPUT TYPE=HIDDEN NAME="Type" VALUE="<%= userPO.getType()%>">
    <INPUT TYPE=HIDDEN NAME="APC">

  <table  class="tbenter" width="100%" border="0" cellspacing=0 cellpadding=2>
	<tr>
    	<td align="right"> 
              Banco :
        </td>
    	<td align="left"> 
              <input type="text" name="E01OLDBNK" size="3" maxlength="2" value="<%= account.getE01OLDBNK()%>">
        </td>
    </tr>
	<tr>
    	<td align="right"> 
              Sucursal :
        </td>
    	<td align="left"> 
              <input type="text" name="E01OLDBRN" size="4" maxlength="3" value="<%if (account.getE01OLDBRN().equals("0")) {out.println("0");} else {out.println(account.getE01OLDBRN());}%>" onBlur="document.forms[0].E01OLDBRN.value = addLeftChar('0',3,document.forms[0].E01OLDBRN.value)">
			  <a href="javascript:GetBranch('E01OLDBRN',document.forms[0].E01OLDBNK.value)"><img src="<%=request.getContextPath()%>/images/1b.gif" alt="help" align="absbottom" border="0" ></a> 
        </td>
    </tr>
	<tr>
    	<td align="right"> 
              Moneda :
        </td>
    	<td align="left"> 
              <input type="text" name="E01OLDCCY" size="4" maxlength="3" value="<%if (account.getE01OLDCCY().equals("0")) {out.println("0");} else {out.println(account.getE01OLDCCY());}%>">
			  <a href="javascript:GetCurrency('E01OLDCCY',document.forms[0].E01OLDBNK.value)"><img src="<%=request.getContextPath()%>/images/1b.gif" alt="help" align="absbottom" border="0" ></a> 
        </td>
    </tr>
	<tr>
    	<td align="right"> 
        	Número de Amortización : 
        </td>
    	<td align="left"> 
            <input type="text" name="E01OLDACC" size="15" maxlength="12" onkeypress="enterInteger()" value="<%= account.getE01OLDACC()%>">
    	</td>
    </tr>
	<tr>
    	<td align="right"> 
        	Número de Cuenta Contable : 
        </td>
    	<td align="left"> 
            <input type="text" name="E01OLDGLN" size="18" maxlength="16" onkeypress="enterInteger()" value="<%= account.getE01OLDGLN()%>">
            <a href="javascript:GetLedger('E01OLDGLN','','','92')"><img src="<%=request.getContextPath()%>/images/1b.gif" alt="help" align="absbottom" border="0" ></a> 
    	</td>
    </tr>
	<tr>
    	<td align="right"> 
              Nuevo Banco :
        </td>
    	<td align="left"> 
              <input type="text" name="E01NEWBNK" size="3" maxlength="2" value="<%= account.getE01NEWBNK()%>">
        </td>
    </tr>
	<tr>
    	<td align="right"> 
              Nueva Sucursal :
        </td>
    	<td align="left"> 
              <input type="text" name="E01NEWBRN" size="4" maxlength="3" value="<%if (account.getE01NEWBRN().equals("0")) {out.println("0");} else {out.println(account.getE01NEWBRN());}%>" onBlur="document.forms[0].E01NEWBRN.value = addLeftChar('0',3,document.forms[0].E01NEWBRN.value)">
			  <a href="javascript:GetBranch('E01NEWBRN',document.forms[0].E01NEWBNK.value)"><img src="<%=request.getContextPath()%>/images/1b.gif" alt="help" align="absbottom" border="0" ></a> 
        </td>
    </tr>
	<tr>
    	<td align="right"> 
              Nueva Cuenta Contable :
        </td>
    	<td align="left"> 
              <input type="text" name="E01NEWGLN" size="18" maxlength="16" value="<%= account.getE01NEWGLN()%>">
              <a href="javascript:GetLedger('E01NEWGLN',document.forms[0].E01NEWBNK.value,'','92')"><img src="<%=request.getContextPath()%>/images/1b.gif" alt="help" align="absbottom" border="0" ></a> 
        </td>
    </tr>
	<tr>
    	<td align="center" colspan="2"> 
			<input id="EIBSBTN" type=submit name="Submit" value="Enviar">
    	</td>
    </tr>
   </table>
      
<script language="JavaScript">
  document.forms[0].E01OLDBNK.focus();
  document.forms[0].E01OLDBNK.select();
</script>

<%if ( !error.getERRNUM().equals("0")  ) {
      error.setERRNUM("0");
%>
     <SCRIPT Language="Javascript">
            showErrors();
     </SCRIPT>
<%}%>
</form>
</body>
</html>
