<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<link href="<%=request.getContextPath()%>/pages/style.css"
	rel="stylesheet">
<script type="text/javascript"
	src="<%=request.getContextPath()%>/pages/s/javascripts/jquery.js"></script>
<script language="Javascript1.1"
	src="<%=request.getContextPath()%>/pages/s/javascripts/eIBS.jsp"> </SCRIPT>
<script language="Javascript1.1"
	src="<%=request.getContextPath()%>/pages/s/javascripts/optMenu.jsp"> </SCRIPT>

<jsp:useBean id="qryForexSearchParams" class="datapro.eibs.beans.EFE0300DSMessage"
	scope="session" />
<jsp:useBean id="error" class="datapro.eibs.beans.ELEERRMessage"
	scope="session" />

<script language="JavaScript">

  builtHPopUp();
  
  function showPopUp(opth,field,bank,ccy,field1,field2,opcod) {
   init(opth,field,bank,ccy,field1,field2,opcod);
   showPopupHelp();
  }

function enter(){
	  document.forms[0].submit();
	 }

$(document).ready(function() {
<% if (qryForexSearchParams.getE01FEIREV().trim().length() > 0) { %>	
	$("#feirevID").attr('checked', true);
<% } %>

    $("#searchCriteriaID").change(function() {
        var src = $("option:selected", this).val();
        var criteriaValue = '';
        if (src == 'E01FEICUN')
        {
	        criteriaValue = '<input type="text" name="E01FEICUN" size="10" maxlength="9" onkeypress="enterInteger()" value="<%=qryForexSearchParams.getE01FEICUN().equals("0")?"":qryForexSearchParams.getE01FEICUN()%>"> <a href="javascript:GetCustomerDescId(\'E01FEICUN\',\'\',\'\')"><img src="<%=request.getContextPath()%>/images/1b.gif" alt="Help" align="absmiddle" border="0"></a>';
			criteriaValue += '<input type="hidden" name="E01FEICID" value="">';
			criteriaValue += '<input type="hidden" name="E01FEIBRN" value="">';
			criteriaValue += '<input type="hidden" name="E01FEIUSR" value="">';
			criteriaValue += '<input type="hidden" name="E01FEICCY" value="">';						
		}
		else if(src == 'E01FEICID')
		{
			criteriaValue = '<input type="text" name="E01FEICID" size="17" maxlength="15" onkeypress="enterInteger()" value="<%=qryForexSearchParams.getE01FEICID().equals("0")?"":qryForexSearchParams.getE01FEICID()%>"> ';
			criteriaValue += '<input type="hidden" name="E01FEICUN" value="">';
			criteriaValue += '<input type="hidden" name="E01FEIBRN" value="">';
			criteriaValue += '<input type="hidden" name="E01FEIUSR" value="">';
			criteriaValue += '<input type="hidden" name="E01FEICCY" value="">';				
		}
		else if(src == 'E01FEIBRN')
		{
			criteriaValue = '<input type="text" name="E01FEIBRN" size="4" maxlength="3" onkeypress="enterInteger()" value="<%=qryForexSearchParams.getE01FEIBRN().equals("0")?"":qryForexSearchParams.getE01FEIBRN()%>"> <a href="javascript:GetBranch(\'E01FEIBRN\',\'\',\'RT\',\'\')"><img src="<%=request.getContextPath()%>/images/1b.gif" alt="Help" align="absmiddle" border="0"></a>';
			criteriaValue += '<input type="hidden" name="E01FEICUN" value="">';
			criteriaValue += '<input type="hidden" name="E01FEICID" value="">';
			criteriaValue += '<input type="hidden" name="E01FEIUSR" value="">';
			criteriaValue += '<input type="hidden" name="E01FEICCY" value="">';				
		}
		else if(src == 'E01FEIUSR')
		{ 
			criteriaValue = '<input type="text" name="E01FEIUSR" size="17" maxlength="15" value="<%=qryForexSearchParams.getE01FEIUSR().equals("0")?"":qryForexSearchParams.getE01FEIUSR()%>">  <a href="javascript:GetUser(\'E01FEIUSR\',\'E01DSC\',document.forms[0].E01FEIUSR.value)"><img src="<%=request.getContextPath()%>/images/1b.gif" alt="Help" align="absmiddle" border="0"></a>';
			criteriaValue += '<input type="hidden" name="E01FEICUN" value="">';
			criteriaValue += '<input type="hidden" name="E01FEICID" value="">';
			criteriaValue += '<input type="hidden" name="E01FEIBRN" value="">';
			criteriaValue += '<input type="hidden" name="E01FEICCY" value="">';		
			criteriaValue += '<INPUT type="hidden" name="E01DSC">';
					
		}
		else if(src == 'E01FEICCY')
		{
			criteriaValue = '<input type="text" name="E01FEICCY" size="4" maxlength="3" value="<%=qryForexSearchParams.getE01FEICCY().equals("0")?"":qryForexSearchParams.getE01FEICCY()%>"> ';
			criteriaValue += '<input type="hidden" name="E01FEICUN" value="">';
			criteriaValue += '<input type="hidden" name="E01FEICID" value="">';
			criteriaValue += '<input type="hidden" name="E01FEIBRN" value="">';
			criteriaValue += '<input type="hidden" name="E01FEIUSR" value="">';				
		}
		else
		{
			criteriaValue = '<input type="hidden" name="E01FEICCY" value="" value="<%=qryForexSearchParams.getE01FEICCY().equals("0")?"":qryForexSearchParams.getE01FEICCY()%>"> ';
			criteriaValue += '<input type="hidden" name="E01FEICUN" value="">';
			criteriaValue += '<input type="hidden" name="E01FEICID" value="">';
			criteriaValue += '<input type="hidden" name="E01FEIBRN" value="">';
			criteriaValue += '<input type="hidden" name="E01FEIUSR" value="">';						
		}
			
        $('#criteriaValueID').html(criteriaValue);
    });
<%
if (!qryForexSearchParams.getE01FEICUN().equals("0"))
	out.write("$('#searchCriteriaID').val('E01FEICUN'); $('#searchCriteriaID').trigger('change');");
else if (!qryForexSearchParams.getE01FEICID().equals(""))
	out.write("$('#searchCriteriaID').val('E01FEICID'); $('#searchCriteriaID').trigger('change');");
else if (!qryForexSearchParams.getE01FEIBRN().equals("0"))
	out.write("$('#searchCriteriaID').val('E01FEIBRN'); $('#searchCriteriaID').trigger('change');");
else if (!qryForexSearchParams.getE01FEIUSR().equals(""))
	out.write("$('#searchCriteriaID').val('E01FEIUSR'); $('#searchCriteriaID').trigger('change');");
else if (!qryForexSearchParams.getE01FEICCY().equals(""))
	out.write("$('#searchCriteriaID').val('E01FEICCY'); $('#searchCriteriaID').trigger('change');");			
%>
});
</script>

<META name="GENERATOR" content="IBM WebSphere Studio">
</head>
<body>
<%
		if (!error.getERRNUM().equals("0")) {
		out.println("<SCRIPT Language=\"Javascript\">");
		error.setERRNUM("0");
		out.println("       showErrors()");
		out.println("</SCRIPT>");
	}
%>
<FORM METHOD="post"
	ACTION="<%=request.getContextPath()%>/servlet/datapro.eibs.forex.JSEFE0300">
<INPUT TYPE=HIDDEN NAME="SCREEN" VALUE="2"> 
<INPUT TYPE=HIDDEN
	NAME="Pos" VALUE="0"> <img
	src="<%=request.getContextPath()%>/images/e_ibs.gif" align="left"
	name="EIBS_GIF" ALT="pr_inq_search.jsp, EFE0300"> <br>
<br>
<br>
<table id="tbenter" align="center" style="width:85%" border="1">
	<tr>
		<td>
		<table id="tbenter" width="100%" border="0" cellspacing="1"
			cellpadding="0">
			<tr>
				<td valign="middle" align="center" colspan="3" height="33"><b>Consulta
				de Moneda</b></td>
			</tr>
			<tr>
				<td style="text-align:right; width:44%">D&iacute;a :</td>
				<td></td>
				<td style="white-space: nowrap; width:55%;">
				<input type="text" size="2"
					maxlength="2" name="E01FEIDDI" onkeypress="enterInteger()" value="<%=qryForexSearchParams.getE01FEIDDI().equals("0")?"":qryForexSearchParams.getE01FEIDDI()%>">
				<input type="text" size="2" maxlength="2" name="E01FEIMMI"
					onkeypress="enterInteger()" value="<%=qryForexSearchParams.getE01FEIMMI().equals("0")?"":qryForexSearchParams.getE01FEIMMI()%>"> <input type="text" size="4"
					maxlength="4" name="E01FEIYYI" onkeypress="enterInteger()" value="<%=qryForexSearchParams.getE01FEIYYI().equals("0")?"":qryForexSearchParams.getE01FEIYYI()%>">
				<a
					href="javascript:DatePicker(document.forms[0].E01FEIDDI, document.forms[0].E01FEIMMI, document.forms[0].E01FEIYYI)">
				<img src="<%=request.getContextPath()%>/images/calendar.gif"
					alt="help" align="absmiddle" border="0"> </a> 
					<%--
					&nbsp;&nbsp;Hasta : <input
					type="text" size="2" maxlength="2" name="E01FEIDDF"
					onkeypress="enterInteger()" value="<%=qryForexSearchParams.getE01FEIDDF().equals("0")?"":qryForexSearchParams.getE01FEIDDF()%>"> <input type="text" size="2"
					maxlength="2" name="E01FEIMMF" onkeypress="enterInteger()" value="<%=qryForexSearchParams.getE01FEIMMF().equals("0")?"":qryForexSearchParams.getE01FEIMMF()%>">
				<input type="text" size="4" maxlength="4" name="E01FEIYYF"
					onkeypress="enterInteger()" value="<%=qryForexSearchParams.getE01FEIYYF().equals("0")?"":qryForexSearchParams.getE01FEIYYF()%>"> <a
					href="javascript:DatePicker(document.forms[0].E01FEIDDF, document.forms[0].E01FEIMMF, document.forms[0].E01FEIYYF)">
				<img src="<%=request.getContextPath()%>/images/calendar.gif"
					alt="help" align="absmiddle" border="0"></a> <img
					src="<%=request.getContextPath()%>/images/Check.gif"
					alt="Campo Obligatorio" align="bottom"></td> --%>
			</tr>
			<tr>
				<td colspan="3" style="height:6px;"></td>
			</tr>
			<tr>
				<td style="text-align:right;">&nbsp;<select id="searchCriteriaID">
					<option value="">--Elija un criterio--</option>
					<option value="E01FEICUN">N&uacute;mero de Cliente</option>
					<option value="E01FEICID">Identificaci&oacute;n</option>
					<option value="E01FEIBRN">Agencia</option>
					<option value="E01FEIUSR">Usuario o Trade</option>
					<option value="E01FEICCY">Moneda</option>
				</select></td>
				<td></td>
				<td>
					<div id="criteriaValueID"></div>
				</td>
			</tr>
			<tr>
				<td colspan="3" style="height:6px;"></td>
			</tr>
			<tr>
				<td style="text-align:right;"><input type="checkbox" name="E01FEIREV" value="Y" id="feirevID"></td>
				<td></td>
				<td>Ver reversos</td>
			</tr>
			<tr>
				<td colspan="3" style="height:10px;"></td>
			</tr>
			
		</table>
		</td>
	</tr>
</table>

<p align="center"><input id="EIBSBTN" type=submit name="Submit"
	value="Enviar"></p>

</form>
</body>
</html>
