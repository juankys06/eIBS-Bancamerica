<jsp:useBean id="error" class="datapro.eibs.beans.ELEERRMessage"
	scope="session" />
<jsp:useBean id="currUser" class="datapro.eibs.beans.ESS0030DSMessage"
	scope="session" />
<jsp:useBean id="idData" class="datapro.eibs.beans.IdentificationData" scope="request" />
	
<html>
<head>
<title>Nuevo Cliente</title>
<META HTTP-EQUIV="Content-Type" CONTENT="text/html; charset=ISO-8859-1">
<link href="<%=request.getContextPath()%>/pages/style.css"
	rel="stylesheet">
<script language="Javascript1.1"
	src="<%=request.getContextPath()%>/pages/s/javascripts/eIBS.jsp"> </SCRIPT>


<SCRIPT
	SRC="<%=request.getContextPath()%>/pages/s/javascripts/optMenu.jsp"> </SCRIPT>


<script language="JavaScript">

//Joins the multiple input fields in one string
//Token is optional an probably should not be used
 function joinID(idField, idType, len, token){

	var countryCode = document.forms[0].COUNTRY.value;
	var strID = "";
	if(!token){
		token = '';
	}
	for(var i = 0; i < len; i++) {
		try {
			var name = countryCode + idType + "IDN" + i;
			strID = strID + document.forms[0][name.toUpperCase()].value;
			if(i < len - 1){
				strID = strID + token
			}
		} catch (e){
		}
	}
	idField.value=strID.toUpperCase();
}

 function getIdTypeHelp(){
   // Get the client type selected
   var clientTypeSelected="CORPORATIVE";
   for (counter = 0; counter < document.forma.TYPE.length; counter++)
   {
      if (document.forma.TYPE[counter].checked)
        clientTypeSelected = document.forma.TYPE[counter].value; 
   }
   // Display the id screen help 
   if (clientTypeSelected == "CORPORATIVE") 
     GetCode('VECIDN0','STATIC_client_help_id_type.jsp?clientType=CORPORATIVE');
   else
     GetCode('VEPIDN0','STATIC_client_help_id_type.jsp?clientType=PERSONAL');
 }
 
function showDivType(countryCode, type){
	var show = false;
	var sufix = 'ID'+countryCode+type;
	tabDiv = document.getElementsByTagName('div');
	if (tabDiv!=null){
		for (i=0; i<tabDiv.length; i++) {
			var elem = tabDiv[i];
			if(elem.id.match(sufix) == sufix){
				elem.style.display='block';
				show = true;
			} else {
				elem.style.display='none';
			}
	    }
  	}
  	if(!show){
		document.getElementById('IDXXOTHER').style.display='block' ;
  	}
}
 
function showIdFields(countryCode){
	
	countryCode = trim(countryCode).toUpperCase();
	if(countryCode == ""){
		countryCode = "DR";//Set here the default;
	}
	var cusType = getCheckedValue(document.forms[0].elements["TYPE"]);
	if( cusType.toUpperCase() == "PERSONAL"){
		showDivType(countryCode, 'P');
	} else if( cusType.toUpperCase() == "CORPORATIVE"){
		showDivType(countryCode, 'C');
	} else if( cusType.toUpperCase() == "MINOR"){
		showDivType(countryCode, 'M');		
	} else {
		showDivType(countryCode, 'O');
	}
}
function fill0Left(idField){
	idField.value = addLeftChar('0', idField.maxLength, idField.value);
}


// return the value of the radio button that is checked
// return an empty string if none are checked, or
// there are no radio buttons
function getCheckedValue(radioObj) {
	if(!radioObj)
		return "";
	var radioLength = radioObj.length;
	if(radioLength == undefined)
		if(radioObj.checked)
			return radioObj.value;
		else
			return "";
	for(var i = 0; i < radioLength; i++) {
		if(radioObj[i].checked) {
			return radioObj[i].value;
		}
	}
	return "";
}

</script>

</head>

<body bgcolor="#FFFFFF">

<h3 align="center">Nuevo Cliente<img
	src="<%=request.getContextPath()%>/images/e_ibs.gif" align="left"
	name="EIBS_GIF" alt="client_both_enter_new, ESD0080"></h3>
<hr size="4">
<form name="forma" method="post"
	action="<%=request.getContextPath()%>/servlet/datapro.eibs.client.JSESD0080">
<p><INPUT TYPE=HIDDEN NAME="SCREEN" VALUE="400"></p>
<h4 align="center">Ingrese el N&uacute;mero de Cliente o de
Identificaci&oacute;n y seleccione una opci&oacute;n.</h4>
<p>&nbsp;</p>
<table class="tableinfo">

	<tr>
		<td nowrap>
		<table cellspacing="0" cellpadding="2" width="100%" border="0">
			<tr id="trdark">
				<td nowrap align="right" valign="middle" width="49%">Pa&iacute;s :</td>
				<td colspan="2">
				<INPUT TYPE=HIDDEN NAME="INT" value=""> 
				<INPUT TYPE="text" NAME="COUNTRY" value="<%=idData.getCountry().equals("") ? "DR" : idData.getCountry() %>" size="3" maxlength="2" 
					onfocus="showIdFields(document.forms[0].COUNTRY.value);" 
					onblur="showIdFields(document.forms[0].COUNTRY.value);">  
				<INPUT TYPE="text" NAME="COUNTRYDSC" value="<%=idData.getCountryDescription().equals("") 
							? "REPUBLICA DOMINICANA" : idData.getCountryDescription()%>" size="40" maxlength="35" 
					onfocus="showIdFields(document.forms[0].COUNTRY.value);" readonly="readonly"> 
				<a href="javascript:GetCodeDescCNOFC('COUNTRY','COUNTRYDSC','03')"> 
				<img src="<%=request.getContextPath()%>/images/1b.gif" alt=". . ."
					align="bottom" border="0">
				</a> 

				</td>
			</tr>

			<tr id="trclear">
				<td nowrap align="right" valign="middle" width="49%">Tipo de Cliente :</td>
				<td colspan="2">
				<INPUT TYPE=HIDDEN NAME="IDN" value=""> 
				<INPUT TYPE=HIDDEN NAME="IDN2" value=""> 
				<% boolean isChecked = idData.isLegalType("OTHER"); %> 
				<label for="other" >
				<input type="radio" id="other" name="TYPE" value="OTHER" <%= idData.isLegalType("OTHER") ? "checked" : "" %> 
					onclick="showIdFields(document.forms[0].COUNTRY.value);" >Otro
				</label>
				<% isChecked = isChecked || idData.isLegalType("CORPORATIVE"); %>
				<label for="corp" >
				<input type="radio" id="corp" name="TYPE" value="CORPORATIVE" <%= idData.isLegalType("CORPORATIVE") ? "checked" : "" %> 
					onclick="showIdFields(document.forms[0].COUNTRY.value);" >Juridico 
				</label>
				<label for="pers" >
				<input type="radio" id="pers" name="TYPE" value="PERSONAL" <%= !isChecked ? "checked" : "" %> 
					onclick="showIdFields(document.forms[0].COUNTRY.value);" >Fisico 
				</label>
				<label for="minor" >
				<input type="radio" id="minor" name="TYPE" value="MINOR" <%= idData.isLegalType("MINOR") ? "checked" : "" %> 
					onclick="showIdFields(document.forms[0].COUNTRY.value);" >Menor de edad 
				</label>				
				</td>
			</tr>
		</table>

		<div id="IDXXOTHER" style="position:relative; display:none;">
		<table cellspacing="0" cellpadding="2" width="100%" border="0">
			<tr id="trdark">
				<td nowrap align="right" valign="middle" width="49%">N&uacute;mero
				de Cliente :</td>
				<td nowrap align="left" valign="middle" colspan="2">
				<input type="text" name="CUN" value="0" maxlength="10" size="11" onKeypress="enterInteger()">
				</td>
			</tr>
		
			<tr id="trclear">
				<td nowrap align="right" valign="middle" width="49%">Identificaci&oacute;n:
				</td>
				<td nowrap align="left" valign="middle" colspan="2">
				<input type="text" name="XXOIDN0" value="<%=idData.getIdentification()%>" maxlength="15" size="17"
				onblur="document.forms[0].IDN.value=this.value" >
				</td>
			</tr>
		</table>
		</div>
		
		<div id="IDVECORP" style="position:relative; display:none;">
		<table  cellspacing="0" cellpadding="2" width="100%" border="0">
			<tr id="trdark">
				<td nowrap align="right" valign="middle" width="49%">N&uacute;mero
				de Cliente :</td>
				<td nowrap align="left" valign="middle" colspan="2">
				<input type="text" name="CUN" value="0" maxlength="10" size="11" onKeypress="enterInteger()">
				</td>
			</tr>		
			<tr id="trclear">
				<td nowrap align="right" valign="middle" width="49%">Identificaci&oacute;n :
				</td>
				<td nowrap align="left" valign="middle" colspan="2">
				<input type="text" name="VECIDN0" value="<%=idData.getIdentification(0, 1)%>" maxlength="1" size="1" readonly
					onchange="joinID(document.forms[0].IDN, document.forms[0].VIDN0, 2);">
				<a href="javascript:getIdTypeHelp()"> 
				<img src="<%=request.getContextPath()%>/images/1b.gif" alt=". . ."
					align="bottom" border="0">
				</a> 
				<input type="text" name="VECIDN1" value="<%=idData.getIdentification(1, 14)%>" maxlength="14" size="17" onKeypress="enterInteger()"
					onchange="joinID(document.forms[0].IDN, document.forms[0].VIDN0, document.forms[0].VIDN1);">

				</td>
			</tr>
		</table>
		</div>
		
		<div id="IDVEPERS" style="position:relative; display:none;">
		<table  cellspacing="0" cellpadding="2" width="100%" border="0">
			<tr id="trdark">
				<td nowrap align="right" valign="middle" width="49%">N&uacute;mero
				de Cliente :</td>
				<td nowrap align="left" valign="middle" colspan="2">
				<input type="text" name="CUN" value="0" maxlength="10" size="11" onKeypress="enterInteger()">
				</td>
			</tr>		
			<tr id="trclear">
				<td nowrap align="right" valign="middle" width="49%">Identificaci&oacute;n :
				</td>
				<td nowrap align="left" valign="middle" colspan="2">
				<input type="text" name="VEPIDN0" value="<%=idData.getIdentification(0, 1)%>" maxlength="1" size="1" readonly
					onchange="joinID(document.forms[0].IDN, 'P', 2);">
				<a href="javascript:getIdTypeHelp()"> 
				<img src="<%=request.getContextPath()%>/images/1b.gif" alt=". . ."
					align="bottom" border="0">
				</a> 
				<input type="text" name="VEPIDN1" value="<%=idData.getIdentification(1, 14)%>" maxlength="14" size="17"
					onchange="joinID(document.forms[0].IDN, 'P', 2);">

				</td>
			</tr>
		</table>
		</div>

		<div id="IDPACORP" style="position:relative; display:none;">
		<table cellspacing="0" cellpadding="2" width="100%" border="0">
			<tr id="trclear">
				<td nowrap align="right" valign="middle" width="49%">Identificaci&oacute;n:
				</td>
				<td nowrap align="left" valign="middle" colspan="2">
				<input type="text" name="PACIDN0" value="<%=idData.getIdentification(0, 7)%>" maxlength="7" size="8" onKeyPress="enterInteger();"
				onblur="fill0Left(this);joinID(document.forms[0].IDN2, 'C', 4, '-');" > - 
				<input type="text" name="PACIDN1" value="<%=idData.getIdentification(1, 4)%>" maxlength="4" size="5" onKeyPress="enterInteger();"
				onblur="fill0Left(this);joinID(document.forms[0].IDN2, 'C', 4, '-');" > - 
				<input type="text" name="PACIDN2" value="<%=idData.getIdentification(2, 8)%>" maxlength="8" size="9" onKeyPress="enterInteger();"
				onblur="fill0Left(this);joinID(document.forms[0].IDN2, 'C', 4, '-');" >
				<input type="hidden" name="PACIDN3" value="00" maxlength="2" size="3" onKeyPress="enterInteger();"
				onblur="joinID(document.forms[0].IDN2, 'C', 4, '-');document.forms[0].EIBSBTN.focus();" >
				</td>
			</tr>
		</table>
		</div>

		<div id="IDPAPERS" style="position:relative; display:none;">
		<table cellspacing="0" cellpadding="2" width="100%" border="0">
			<tr id="trclear">
				<td nowrap align="right" valign="middle" width="49%">Identificaci&oacute;n:
				</td>
				<td nowrap align="left" valign="middle" colspan="2">
				<input type="text" name="PAPIDN0" value="<%=idData.getIdentification(0, 2) %>" maxlength="2" size="3" onKeyPress="enterInteger();"
				onblur="fill0Left(this);joinID(document.forms[0].IDN2, 'P', 4, '-');" > - 
				<input type="text" name="PAPIDN1" value="<%=idData.getIdentification(1, 2)  %>" maxlength="2" size="3"
				onblur="this.value=this.value.replace(/ /ig,'');joinID(document.forms[0].IDN2, 'P', 4, '-');" > - 
				<input type="text" name="PAPIDN2" value="<%=idData.getIdentification(2, 5)  %>" maxlength="5" size="6" onKeyPress="enterInteger();"
				onblur="fill0Left(this);joinID(document.forms[0].IDN2, 'P', 4, '-');" > - 
				<input type="text" name="PAPIDN3" value="<%=idData.getIdentification(3, 6)  %>" maxlength="6" size="7" onKeyPress="enterInteger();"
				onblur="fill0Left(this);joinID(document.forms[0].IDN2, 'P', 4, '-');" >
				<%-- 
				<input type="text" name="PAPIDN4" value="00" maxlength="2" size="4"
				onblur="fill0Left(this);joinID(document.forms[0].IDN2, 'P', 5);" readonly="readonly" >
				--%>
				</td>
			</tr>
		</table>
		</div>


		<div id="IDDRCORP" style="position:relative; display:none;">
		<table cellspacing="0" cellpadding="2" width="100%" border="0">
			<tr id="trclear">
				<td nowrap align="right" valign="middle" width="49%">Identificaci&oacute;n:
				</td>
				<td nowrap align="left" valign="middle" colspan="2">
				<input type="text" name="DRCIDN0" value="<%=idData.getIdentification(0, 1)%>" maxlength="1" size="2" onKeyPress="enterInteger();"
				onblur="fill0Left(this);joinID(document.forms[0].IDN2, 'C', 4);" > - 
				<input type="text" name="DRCIDN1" value="<%=idData.getIdentification(1, 2)%>" maxlength="2" size="3" onKeyPress="enterInteger();"
				onblur="fill0Left(this);joinID(document.forms[0].IDN2, 'C', 4);" > - 
				<input type="text" name="DRCIDN2" value="<%=idData.getIdentification(2, 5)%>" maxlength="5" size="6" onKeyPress="enterInteger();"
				onblur="fill0Left(this);joinID(document.forms[0].IDN2, 'C', 4);" > -
				<input type="text" name="DRCIDN3" value="<%=idData.getCheckDigit(1)%>" maxlength="1" size="2" onKeyPress="enterInteger();"
				onblur="joinID(document.forms[0].IDN2, 'C', 4);document.forms[0].EIBSBTN.focus();" >
				</td>
			</tr>
		</table>
		</div>

		<div id="IDDRPERS" style="position:relative; display:none;">
		<table cellspacing="0" cellpadding="2" width="100%" border="0">
			<tr id="trclear">
				<td nowrap align="right" valign="middle" width="49%">Identificaci&oacute;n:
				</td>
				<td nowrap align="left" valign="middle" colspan="2">
				<input type="text" name="DRPIDN0" value="<%=idData.getIdentification(0, 3) %>" maxlength="3" size="4" onKeyPress="enterInteger();"
				onblur="fill0Left(this);joinID(document.forms[0].IDN2, 'P', 3);" > - 
				<input type="text" name="DRPIDN1" value="<%=idData.getIdentification(1, 7)  %>" maxlength="7" size="8" onKeyPress="enterInteger();"
				onblur="fill0Left(this);joinID(document.forms[0].IDN2, 'P', 3);" > - 
				<input type="text" name="DRPIDN2" value="<%=idData.getCheckDigit(1)  %>" maxlength="1" size="2" onKeyPress="enterInteger();"
				onblur="joinID(document.forms[0].IDN2, 'P', 3);document.forms[0].EIBSBTN.focus();" >  
				</td>
			</tr>
		</table>
		</div>
		<div id="IDDRMINOR" style="position:relative; display:none;"></div>
		</td>
	</tr>

</table>
<p align="center"><input id="EIBSBTN" type=submit name="Submit"
	value="Enviar"></p>
</form>

<SCRIPT Language="Javascript">;
<%if (!error.getERRNUM().equals("0")) {
		error.setERRNUM("0");	
%>
            showErrors();
<% } %>

	showIdFields("<%= idData.getCountry() %>");
</SCRIPT>
</body>
</html>
