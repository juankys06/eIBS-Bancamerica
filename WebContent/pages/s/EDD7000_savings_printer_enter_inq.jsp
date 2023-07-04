<html>
<head>
<title>Impresion Datos del Titular en Libreta de Ahorros</title>
<META HTTP-EQUIV="Content-Type" CONTENT="text/html; charset=ISO-8859-1">
<link Href="<%=request.getContextPath()%>/pages/style.css" rel="stylesheet">


<script language="Javascript1.1" src="<%=request.getContextPath()%>/pages/s/javascripts/eIBS.jsp"> </SCRIPT>
 

<jsp:useBean id= "error" class= "datapro.eibs.beans.ELEERRMessage"  scope="session" />
<jsp:useBean id= "msgMT" class= "datapro.eibs.beans.EDD700001Message"  scope="session" />

</head>
<body>

<H3 align="center">Impresion Datos del Titular en Libreta de Ahorros<img src="<%=request.getContextPath()%>/images/e_ibs.gif" align="left" name="EIBS_GIF" ALT="savings_printer_enter_inq,EDD7000"></H3>
<hr size="4">
<p>&nbsp;</p>

<form method="post" action="<%=request.getContextPath()%>/servlet/datapro.eibs.products.JSEDD7000">
  <p> 
    <INPUT TYPE=HIDDEN NAME="SCREEN" VALUE="200">
  </p>
  <table class="tableinfo">
   <tr> 
   <td width="100%">
		<table cellspacing=0 cellpadding=2 width="100%" border="0">
			<tr id=trdark>
				<td nowrap width="50%">
				<div align="right">N&uacute;mero de Cuenta :</div>
				</td>
				<td nowrap width="50%"><input type="text" name="E01CUSACC" size="15"
					maxlength="12" value="<%= msgMT.getE01CUSACC()%>"> <a
					href="javascript:GetAccount('E01CUSACC','','04','')"><img
					src="<%=request.getContextPath()%>/images/1b.gif" alt="help"
					align="absbottom" border="0"></a></td>
			</tr>
			<tr id=trclear>
				<td nowrap width="50%">
				<div align="right">N&uacute;mero de Libreta :</div>
				</td>
				<td nowrap width="50%">
				 <!-- <input type="text" name="E01CUSF01" size="2"
					maxlength="1" value="<%= msgMT.getE01CUSF01()%>"> -->
				 <INPUT
					type="text" name="E01CUSPBN" size="8" maxlength="8"
					value="<%= msgMT.getE01CUSPBN()%>"> </td>

			</tr>
			<tr id=trdark>
				<td nowrap width="50%">
				<div align="right">Motivo :</div>
				</td>
				<td nowrap width="50%">
				<select name="E01CUSMOT">
				<option value="N" <% if (!(msgMT.getE01CUSMOT().equals("N") ||msgMT.getE01CUSMOT().equals("R")
				||msgMT.getE01CUSMOT().equals("E")))
				out.print("selected"); %>></option>
                <option value="N" <%if (msgMT.getE01CUSMOT().equals("N")) out.print("selected"); %>>Nuevo</option>
                <option value="R" <%if (msgMT.getE01CUSMOT().equals("R")) out.print("selected"); %>>Reposicion</option>
                <option value="E" <%if (msgMT.getE01CUSMOT().equals("E")) out.print("selected"); %>>Extravio</option>
                </select>
				</td>												
			</tr>
		</table>
		</td>
   </tr>
  </table>
  
  <p align="center"> 
    <input id="EIBSBTN" type=submit name="Submit" value="Enviar">
  </p>
  
	<script language="JavaScript">
	  document.forms[0].E01CUSACC.focus();
	  document.forms[0].E01CUSACC.select();
	</script>
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
</form>
</body>
</html>
