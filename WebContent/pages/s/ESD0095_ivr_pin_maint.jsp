<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">

<jsp:useBean id="client" class="datapro.eibs.beans.ESD008001Message"
	scope="session" />
<jsp:useBean id="ivrData" class="datapro.eibs.beans.ESD0095DSMessage"
	scope="session" />
<jsp:useBean id="error" class="datapro.eibs.beans.ELEERRMessage"
	scope="session" />
<jsp:useBean id="userPO" class="datapro.eibs.beans.UserPos"
	scope="session" />
<jsp:useBean id="currUser" class="datapro.eibs.beans.ESS0030DSMessage"
	scope="session" />
	
<html>
<head>
<title>Afiliaci&oacute;n al IVR</title>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<META name="GENERATOR" content="IBM WebSphere Studio">
<link href="<%=request.getContextPath()%>/pages/style.css"
	rel="stylesheet">
<script language="Javascript1.1"
	src="<%=request.getContextPath()%>/pages/s/javascripts/eIBS.jsp"> </SCRIPT>
<script language="Javascript1.1"
	src="<%=request.getContextPath()%>/pages/s/javascripts/optMenu.jsp"> </SCRIPT>
<script type="text/javascript"
	src="<%=request.getContextPath()%>/pages/s/javascripts/jquery.js"></script>
<script type="text/javascript">

//  Process according with user selection
 function goAction(op) {
	
   	switch (op){
	// afiliar
  	case 'a':  {
    	document.forms[0].H01OPECOD.value = '0001';
    	document.getElementById('pin_unassign_screen').style.visibility = 'hidden'; 
    	document.getElementById('pin_assign_screen').style.visibility = 'visible'; 
    	document.forms[0].E01PINNUM.focus();
       	break;
        }   	
    // modificar
  	case 'm':  {
    	document.forms[0].H01OPECOD.value = '0002';
    	document.getElementById('pin_unassign_screen').style.visibility = 'hidden'; 
    	document.getElementById('pin_assign_screen').style.visibility = 'visible';     	
    	document.forms[0].E01PINNUM.focus();
       	break;
        }
    // desafiliar
  	case 'd':  {
    	document.forms[0].H01OPECOD.value = '0004';
    	document.getElementById('pin_assign_screen').style.visibility = 'hidden';
    	document.getElementById('pin_unassign_screen').style.visibility = 'visible'; 
    	document.forms[0].remove_pin[1].focus();
       	break;
        }        
        
	// Afiliar & Modificar
  	case 1:  {
  		if(document.forms[0].E01PINNUM.value != document.forms[0].E01PINNUM1.value)
  		{
  			alert('Error: Los PIN no coinciden.\nIntente nuevamente.');
  			document.forms[0].E01PINNUM.value = '';
  			document.forms[0].E01PINNUM1.value = '';
  			break;
		}
  		else
  			document.forms[0].submit();
       	break;
        }
	// Desafiliar
	case 4:  {
    	if(!document.forms[0].remove_pin[0].checked)
	       	break;
	    else
  			document.forms[0].submit();	    
		}
	}
 }

<%--
if (ivrData != null)
{
	if (ivrData.getH01OPECOD().equals("0001") || ivrData.getH01OPECOD().equals("0002"))
	{
%>
$(document).ready(function() {
	goAction('a');
});
<%
	}
	else if (ivrData.getH01OPECOD().equals("0004"))
	{
%>
$(document).ready(function() {
	goAction('d');
});
<%
	}
}
--%>
</script>

<script type="text/javascript">
     builtNewMenu(client_personal_opt);
</script>

</head>
<body>
<%
		if (!error.getERRNUM().equals("0")) {
		error.setERRNUM("0");
		out.println("<script type=\"text/javascript\">");
		out.println("       showErrors()");
		out.println("</script>");
	    }
%>
<script> initMenu(); </script>

<h3 align="center">Afiliaci&oacute;n al IVR<img
	src="<%=request.getContextPath()%>/images/e_ibs.gif" align="left"
	name="EIBS_GIF" ALT="ivr_pin_maint, ESD0095"></h3>
<hr size="4">
<form method="post"
	action="<%=request.getContextPath()%>/servlet/datapro.eibs.client.JSESD0095">
<input type="hidden" name="SCREEN" value="2"> <input
	type="hidden" name="E01LGT" size="15" maxlength="10"
	value="<%=client.getE01LGT().trim()%>"> <input type="hidden"
	name="H01OPECOD">
<h4>Datos del Cliente</h4>
<div align="left">
<table class="tableinfo">
	<tr>
		<td nowrap>
		<div align="center">

		<table cellspacing="0" cellpadding="2" width="100%" border="0">
			<tr id="trclear">
				<td nowrap width="25%">
				<div align="right">No. Cliente :</div>
				</td>
				<td nowrap colspan="4"><input type="hidden" name="E01CUN"
					size="15" maxlength="10" value="<%=client.getE01CUN().trim()%>">
				<b><%=client.getE01CUN().trim()%></b> 
				</td>
			</tr>
			<tr id="trdark">
				<td nowrap width="25%">
				<div align="right">Identificaci&oacute;n :</div>
				</td>
				<td nowrap colspan=4><b><%=client.getE01LN3().trim()%></b></td>
			</tr>
			<tr id="trclear">
				<td nowrap width="25%">
				<div align="right">Nombre Legal :</div>
				</td>
				<td nowrap colspan=4><b><%=client.getE01NA1().trim()%></b></td>
			</tr>
			<tr id="trdark">
				<td nowrap width="25%">
				<div align="right">Nombre Corto :</div>
				</td>
				<td nowrap colspan=4><b><%=client.getE01SHN().trim()%></b></td>
			</tr>
		</table>

		</div>
		</td>
	</tr>
</table>
<br>
<table class="tbenter">
	<tr>
		<td class=tdbkg>
		<div align="center"><a name="linkapproval"
			href="javascript:goAction('a')"><b>Afiliaci&oacute;n</b></a></div>
		</td>
		<td class=tdbkg>
		<div align="center"><a name="linkreject"
			href="javascript:goAction('m')"><b>Modificar</b></a></div>
		</td>
		<td class=tdbkg>
		<div align="center"><a href="javascript:goAction('d')"><b>Desafiliaci&oacute;n</b></a></div>
		</td>
		<td class=tdbkg>
		<div align="center"><a
			href="<%=request.getContextPath()%>/pages/background.jsp"><b>salir</b></a></div>
		</td>
	</tr>
</table>

</div>
<br>
<div id="pin_assign_screen"
	style="visibility: hidden;position: absolute;">
<table class="tableinfo" cellspacing="0" cellpadding="2" width="100%"
	border="0">
	<tr id="trdark">
		<td style="width:50%; text-align:right;">PIN :</td>
		<td width="50%"><input type="password" name="E01PINNUM" size="6"
			maxlength="4"></td>
	</tr>
	<tr id="trclear">
		<td style="width:50%; text-align:right;">Confirmar PIN :</td>
		<td width="50%"><input type="password" name="E01PINNUM1" size="6"
			maxlength="4"></td>
	</tr>
</table>
<br>
<table width="100%">
	<tr>
		<td style="text-align:center; width:100%;"><input id="EIBSBTN"
			type="button" name="Submit" value="Enviar"
			onClick="javascript:goAction(1);"></td>
	</tr>
</table>
</div>

<div id="pin_unassign_screen"
	style="visibility: hidden;position: absolute;">
<table class="tableinfo" cellspacing="0" cellpadding="2" width="100%"
	border="0">
	<tr id="trdark">
		<td colspan="2" style="text-align:center;font-weight: bold;">El
		PIN del IVR del cliente ser&aacute; removido. &iquest;Desea desafiliar
		al cliente?</td>
	</tr>
	<tr id="trclear">
		<td style="width:50%; text-align:right;"><input type="radio"
			name="remove_pin" value="Y"></td>
		<td width="50%">S&iacute;</td>
	</tr>
	<tr id="trdark">
		<td style="width:50%; text-align:right;"><input type="radio"
			name="remove_pin" value="N" checked="checked"></td>
		<td width="50%">No</td>
	</tr>
</table>
<br>
<table width="100%">
	<tr>
		<td style="text-align:center; width:100%;"><input id="EIBSBTN"
			type="button" name="Submit" value="Enviar"
			onClick="javascript:goAction(4);"></td>
	</tr>
</table>
</div>
</body>
</html>
