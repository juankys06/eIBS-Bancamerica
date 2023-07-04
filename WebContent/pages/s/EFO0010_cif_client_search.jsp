<html>
<head>
<link href="<%=request.getContextPath()%>/pages/style.css" rel="stylesheet">

<script language="Javascript1.1" src="<%=request.getContextPath()%>/pages/e/javascripts/eIBS.jsp"> </SCRIPT>

<jsp:useBean id= "error" class= "datapro.eibs.beans.ELEERRMessage"  scope="session" />
<jsp:useBean id= "userPO" class= "datapro.eibs.beans.UserPos"  scope="session"/>

<style>
TH {
  background: #FFFFFF;
}
</style>

<script language="JavaScript">
function enter(){
var NameSearch = document.forms[0].NameSearch.value
var FromRecord = 0
	if (NameSearch.length > 0){
		for(var i = 0; i < document.forms[0].Type.length; i++) {
			if (document.forms[0].Type[i].checked){
			var Type = document.forms[0].Type[i].value
			}
 		}
 		document.forms[0].submit();
	}
	else {
		alert("Entre Criterio de Busqueda")
	}
}
</script>

</head>

<body>

<% 
 if ( !error.getERRNUM().equals("0")  ) {
 	 error.setERRNUM("0");
%>
     <SCRIPT Language="Javascript">
            showErrors()
     </SCRIPT> 
<% 
 }
%>
<h3 align="center"><%= userPO.getHeader7()%><br>Busqueda de Clientes<img src="<%=request.getContextPath()%>/images/e_ibs.gif" align="left" 
	name="EIBS_GIF" alt="cif_client_search, EFO0010"></h3>

 <FORM METHOD="post" ACTION="<%=request.getContextPath()%>/servlet/datapro.eibs.products.JSEFO0010" >
  <INPUT TYPE=HIDDEN NAME="SCREEN" VALUE="2">
  <INPUT TYPE=HIDDEN NAME="Pos" VALUE="0">
  
  <br>
  <br>
  <br>
  <table class="tableinfo"> 
    <tr>
		<td width="17%">&nbsp;</td>
		<td width="51%">
		<table align="center">
			<tr>
				<td width="69%">
				<table border="0" cellspacing="0" cellpadding="0">
					<tr>
						<td align="center" width="282">Tipo de Busqueda</td>
					</tr>
					<tr>
						<td width="282">&nbsp;</td>
					</tr>
					<tr>
						<td width="282"><input type="radio" name="Type" value="1" checked> Número de Cliente</td>
					</tr>
					<tr>
						<td width="282"><input type="radio" name="Type" value="2"> Número de Identificación</td>
					</tr>
					<tr>
						<td width="282"><input type="radio" name="Type" value="3"> Nombre Corto</td>
					</tr>
					<tr>
						<td width="282"><input type="radio" name="Type" value="4"> Nombre Completo</td>
					</tr>
					<TR>
						<TD width="282"><INPUT type="radio" name="Type" value="9"> Número de Identificación Largo</TD>
					</TR>					
					<tr>
						<td width="282"><input type="radio" name="Type" value="0"> Todos </td>
					</tr>
				</table>
				</td>
				<td NOWRAP width="31%">
				
				<div align="center"><input type="text" name="NameSearch" size="20">
				&nbsp;&nbsp;</div>
				</td>
			</tr>
		</table>
		</td>
		<td width="32%">&nbsp;</td>
	</tr>
  </table>	

  <br>
  <div align="center"> 
	   <input id="EIBSBTN" type=submit name="Submit" value="Someter">
  </div>

<script language="JavaScript">
  document.forms[0].NameSearch.focus();
  document.forms[0].NameSearch.select();
</script>
</form>
</body>
</html>
