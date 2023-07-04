<html>
<head>
<title>Cambio de Identificación</title>
<META HTTP-EQUIV="Content-Type" CONTENT="text/html; charset=ISO-8859-1">
<link href="<%=request.getContextPath()%>/pages/style.css" rel="stylesheet">

<jsp:useBean id="error" class="datapro.eibs.beans.ELEERRMessage" scope="session" />

<jsp:useBean id="currUser" class="datapro.eibs.beans.ESS0030DSMessage" scope="session" />
<jsp:useBean id="msgClient" class= "datapro.eibs.beans.ESD008015Message"  scope="session" />

<script language="Javascript1.1" src="<%=request.getContextPath()%>/pages/s/javascripts/eIBS.jsp"> </SCRIPT>

<SCRIPT SRC="<%=request.getContextPath()%>/pages/s/javascripts/optMenu.jsp"> </SCRIPT>


<%
	//Define variables request
	String country="";  
	String idn="";
	String idn2=null;
	//Define variables de la identificacion Panameña
	String cProvincia="";
	String cLetra="";
	String cFolio="";
	String cAsiento="";
	String rRollo="";
	String rFolio="";
	String rAsiento="";
	String digVer="";
	int indexI=0;
	int indexF=0;
	int len=0;
  
	if (msgClient.getE15GEC() != null) {
		country = msgClient.getE15GEC();
		if (country.toUpperCase().equals("PA")) { // Panamá
	        if (msgClient.getE15LN3() != null && !msgClient.getE15LN3().equals("")) {
            	idn2 = msgClient.getE15LN3();
            	// Obtiene la dirección panameña dependiendo del tipo de cliente
	      		indexI=msgClient.getE15LN3().indexOf("-",0);
	      		indexF=msgClient.getE15LN3().indexOf("-",indexI+1);
	      		if (msgClient.getE15LGT().equals("2") && msgClient.getE15LN3() != null) {
				    // 09-PI-00001-001277 70         
				    // Provincia-Letra-Folio-Asiento DV 
				    cProvincia = idn2.substring(0,indexI);
				    cLetra = idn2.substring(indexI+1,indexF);
				    indexI = indexF;
				    indexF = idn2.indexOf("-",indexI+1);
				    cFolio = idn2.substring(indexI+1,indexF);
				    indexI = indexF;
				    indexF = idn2.indexOf(" ",indexI+1);
				    if (indexF > 0) {
					    cAsiento = idn2.substring(indexI+1,indexF);
					    digVer = idn2.substring(indexF+1);			    
				    }
				    else {
				    	cAsiento = idn2.substring(indexI+1);
				    }
	
					System.out.println("Provincia: " + cProvincia);
					System.out.println("Letra:" + cLetra);
					System.out.println("Folio: " + cFolio);
					System.out.println("Asiento: " + cAsiento);
					System.out.println("Dígito verificador: " + digVer);
	      		}
	      		else if (msgClient.getE15LGT().equals("1") && (msgClient.getE15LN3() != null)){
				    // 0055496-0012-00304533 50
				    // Rollo-Folio-Asiento DV      
				    rRollo = idn2.substring(0,indexI);
				    rFolio = idn2.substring(indexI+1,indexF);
				    indexI = indexF;
				    indexF = idn2.indexOf(" ",indexI+1);
				    if (indexF > 0){
					    rAsiento = idn2.substring(indexI+1,indexF);
					    digVer = idn2.substring(indexF+1);		    
				    }
				    else {
				    	rAsiento = idn2.substring(indexI+1);
				    }
				    
					System.out.println("Rollo:" + rRollo);
					System.out.println("Folio: " + rFolio);
					System.out.println("Asiento: " + rAsiento);
					System.out.println("Dígito verificador: " + digVer);    
			    }
			}			
		}
	}
   
%>

<script language="JavaScript"><!--

 function enterInteger1 (idType) 
 {

 if (idType.value != 'P')
   enterInteger();
 
 }

 function joinID(idField, idType, idNum){

	  var strID = "";
	    while ((idNum.value.length < 9) && (idType.value!='P'))
	      idNum.value='0'+idNum.value;
	  strID = trim(idType.value) + trim(idNum.value);
      idField.value=strID.toUpperCase();
}

 function getIdTypeHelp(){
   // Get the client type selected
   var clientTypeSelected="1";
   for (counter = 0; counter < document.forma.E15LGT.length; counter++)
   {
      if (document.forma.E15LGT[counter].checked)
        clientTypeSelected = document.forma.E15LGT[counter].value; 
   }
   // Display the id screen help 
   if (clientTypeSelected == "1") 
     GetCode('VIDN0','STATIC_client_help_id_type.jsp?clientType=CORPORATIVE');
   else
     GetCode('VIDN0','STATIC_client_help_id_type.jsp?clientType=PERSONAL');
 }
 
function showIdFields(countryCode){

	var cusType = getCheckedValue(document.forms[0].elements["E15LGT"]) ;
	
	if( countryCode.toUpperCase() == "VE" ){
				document.getElementById('IDVEN').style.display='block' ;
				document.getElementById('IDOTHER').style.display='none' ;
				document.getElementById('IDPAPERS').style.display='none' ;
				document.getElementById('IDPACORP').style.display='none' ;
	} else if( countryCode.toUpperCase() == "PA" && cusType == "1" ){
				document.getElementById('IDVEN').style.display='none' ;
				document.getElementById('IDOTHER').style.display='none' ;
				document.getElementById('IDPAPERS').style.display='none' ;
				document.getElementById('IDPACORP').style.display='block' ;
	} else if( countryCode.toUpperCase() == "PA" && cusType == "2" ){
				document.getElementById('IDVEN').style.display='none' ;
				document.getElementById('IDOTHER').style.display='none' ;
				document.getElementById('IDPAPERS').style.display='block' ;
				document.getElementById('IDPACORP').style.display='none' ;
	} else if( countryCode.toUpperCase() != "" ){
				document.getElementById('IDVEN').style.display='none' ;
				document.getElementById('IDOTHER').style.display='block' ;
				document.getElementById('IDPAPERS').style.display='none' ;
				document.getElementById('IDPACORP').style.display='none' ;
	}

}
function fill0Left(idField){

	  //alert("**idFieldNAME: "+idField.name + " **Longitud: " + idField.value.length + " **MaxLongitud: " + idField.maxLength);
	    while (idField.value.length < idField.maxLength )
	      idField.value='0'+idField.value;	  	 
}
function joinPersonalIDFieldsPA(){

	document.forms[0].IDN2.value=document.forms[0].PIDN0.value + "-" +
								document.forms[0].PIDN1.value + "-" +
								document.forms[0].PIDN2.value + "-" +
								document.forms[0].PIDN3.value ;
}

function joinCorpIDFieldsPA(){

	var dv = "" ;
	dv = document.forms[0].elements["CIDN3"].value;

	if( dv == "" || dv == "undefined" || dv == null ){
		dv = "  " ;
	} else if (dv.length < 2) {
		dv = " " + dv ;
	}

	document.forms[0].IDN2.value=document.forms[0].CIDN0.value + "-" +
								document.forms[0].CIDN1.value + "-" +
								document.forms[0].CIDN2.value + " " +
								dv ;
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

function stripZerosAtLeft( valObj ){

	if( valObj.value != "" && !isNaN(valObj.value) ){
		valObj.value = parseInt(valObj.value , 10) ;
	}

}

</script>

</head>

<body bgcolor="#FFFFFF">

<h3 align="center">Cambio de Identificación<img src="<%=request.getContextPath()%>/images/e_ibs.gif" align="left" name="EIBS_GIF" alt="client_id_change.jsp, ESD0080"></h3>
<hr size="4">
<form name="forma" method="post" action="<%=request.getContextPath()%>/servlet/datapro.eibs.client.JSESD0080">
<INPUT TYPE=HIDDEN NAME="SCREEN" VALUE="56">

<table class="tableinfo">
	<tr>
		<td nowrap>
		<table cellspacing="0" cellpadding="2" width="100%" border="0">
			<tr id="trdark">
				<td nowrap align="right" valign="middle" width="49%">Pa&iacute;s :</td>
				<td colspan="2">
				<INPUT TYPE=HIDDEN NAME="INT" value=""> 
				<INPUT TYPE="text" NAME="E15GEC" value="<%= msgClient.getE15GEC() %>" size="3" maxlength="2" onfocus="showIdFields(document.forms[0].E15GEC.value);" onblur="showIdFields(document.forms[0].E15GEC.value);">  
				<INPUT TYPE="text" NAME="D15GEC" value="<%= msgClient.getD15GEC() %>" onfocus="showIdFields(document.forms[0].E15GEC.value);" readonly="readonly"> 
				<a href="javascript:GetCodeDescCNOFC('E15GEC','D15GEC','03')"> 
				<img src="<%=request.getContextPath()%>/images/1b.gif" alt=". . ." align="absbottom" border="0">
				</a> 
				
				<%-- 
				<input type="radio" name="E15GEC" value="07" onclick="showIdFields(this.value);" >Panam&aacute; 
				<input type="radio" name="E15GEC" value="03" onclick="showIdFields(this.value);" >Venezuela 
				<input type="radio" name="E15GEC" value="" onclick="this.value='';showIdFields(this.value);this.value='<%=currUser.getE01INT()%>';" >Otro
				--%>
				</td>
			</tr>

			<tr id="trclear">
				<td nowrap align="right" valign="middle" width="49%">Tipo de Cliente :</td>
				<td colspan="2">
				<INPUT TYPE=HIDDEN NAME="IDN" value=""> 
				<INPUT TYPE=HIDDEN NAME="IDN2" value=""> 
				<label for="corp" >
				<input type="radio" id="corp" name="E15LGT" value="1" onclick="showIdFields(document.forms[0].E15GEC.value);" <% if (msgClient.getE15LGT().equals("1")) { out.print("checked"); } %>>Corporativo 
				</label>
				<label for="pers" >
				<input type="radio" id="pers" name="E15LGT" value="2" onclick="showIdFields(document.forms[0].E15GEC.value);" <% if (msgClient.getE15LGT().equals("2")) { out.print("checked"); } %>>Personal 
				</label>
				<label for="other" >
				<input type="radio" id="other" name="E15LGT" value="3" onclick="showIdFields(document.forms[0].E15GEC.value);" <% if (msgClient.getE15LGT().equals("3")) { out.print("checked"); } %>>Otro
				</label>
				</td>
			</tr>
		</table>

		<div id="IDVEN" style="position:relative; display:none;">
		<table  cellspacing="0" cellpadding="2" width="100%" border="0">
			<tr id="trdark">
				<td nowrap align="right" valign="middle" width="49%">N&uacute;mero de Cliente :
				</td>
				<td nowrap align="left" valign="middle" colspan="2">
					<input type="text" name="E15CUN" value="<%= msgClient.getE15CUN() %>" maxlength="10" size="11" readonly>
				</td>
			</tr>		
			<tr id="trclear">
				<td nowrap align="right" valign="middle" width="49%">Identificaci&oacute;n :
				</td>
				<td nowrap align="left" valign="middle" colspan="2">
					<input type="text" name="VIDN0" value="<% if(!msgClient.getE15IDN().equals("")) { out.print(msgClient.getE15IDN().substring(0,1)); } %>" maxlength="1" size="1" readonly onchange="joinID(document.forms[0].IDN, document.forms[0].VIDN0, document.forms[0].VIDN1);">
					<a href="javascript:getIdTypeHelp()"> 
					<img src="<%=request.getContextPath()%>/images/1b.gif" alt=". . ." align="absbottom" border="0">
					</a> 
					<input type="text" name="VIDN1" value="<% if(!msgClient.getE15IDN().equals("")) { out.print(msgClient.getE15IDN().substring(1)); } %>" maxlength="14" size="17" onKeypress="enterInteger1(document.forms[0].VIDN0)" onchange="joinID(document.forms[0].IDN, document.forms[0].VIDN0, document.forms[0].VIDN1);">
				</td>
			</tr>
		</table>
		</div>

		<div id="IDOTHER" style="position:relative; display:none;">
		<table cellspacing="0" cellpadding="2" width="100%" border="0">
			<tr id="trdark">
				<td nowrap align="right" valign="middle" width="49%">N&uacute;mero de Cliente :
				</td>
				<td nowrap align="left" valign="middle" colspan="2">
					<input type="text" name="E15CUN" value="<%= msgClient.getE15CUN() %>" maxlength="10" size="11" readonly>
				</td>
			</tr>
		
			<tr id="trclear">
				<td nowrap align="right" valign="middle" width="49%">Identificaci&oacute;n:
				</td>
				<td nowrap align="left" valign="middle" colspan="2">
					<input type="text" name="OIDN" value="<%= msgClient.getE15IDN() %>" maxlength="15" size="17" onblur="document.forms[0].IDN.value=this.value" >
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
					<input type="text" name="CIDN0" value="<% if(!msgClient.getE15LN3().equals("")) { out.print(rRollo); } %>" maxlength="7" size="8" onKeyPress="enterInteger();" onblur="fill0Left(this);joinCorpIDFieldsPA();" > - 
					<input type="text" name="CIDN1" value="<% if(!msgClient.getE15LN3().equals("")) { out.print(rFolio); } %>" maxlength="4" size="5" onKeyPress="enterInteger();" onblur="fill0Left(this);joinCorpIDFieldsPA();" > - 
					<input type="text" name="CIDN2" value="<% if(!msgClient.getE15LN3().equals("")) { out.print(rAsiento); } %>" maxlength="8" size="9" onKeyPress="enterInteger();" onblur="fill0Left(this);joinCorpIDFieldsPA();" >
					<input type="hidden" name="CIDN3" value="00" maxlength="2" size="3" onKeyPress="enterInteger();" onblur="joinCorpIDFieldsPA();document.forms[0].EIBSBTN.focus();" >
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
					<input type="text" name="PIDN0" value="<% if(!msgClient.getE15LN3().equals("")) { out.print(cProvincia); } %>" maxlength="2" size="3" onKeyPress="enterInteger();" onblur="fill0Left(this);joinPersonalIDFieldsPA();" > - 
					<input type="text" name="PIDN1" value="<% if(!msgClient.getE15LN3().equals("")) { out.print(cLetra); } %>" maxlength="2" size="3" onblur="this.value=this.value.replace(/ /ig,'');joinPersonalIDFieldsPA();" > - 
					<input type="text" name="PIDN2" value="<% if(!msgClient.getE15LN3().equals("")) { out.print(cFolio); } %>" maxlength="5" size="6" onKeyPress="enterInteger();" onblur="fill0Left(this);joinPersonalIDFieldsPA();" > - 
					<input type="text" name="PIDN3" value="<% if(!msgClient.getE15LN3().equals("")) { out.print(cAsiento); } %>" maxlength="6" size="7" onKeyPress="enterInteger();" onblur="fill0Left(this);joinPersonalIDFieldsPA();" >
				<%-- 
				<input type="text" name="PIDN4" value="  " maxlength="2" size="4"
				onblur="joinPersonalIDFieldsPA();" readonly="readonly" >
				--%>
				</td>
			</tr>
		</table>
		</div>

		</td>
	</tr>

</table>
<p align="center"><input id="EIBSBTN" type=submit name="Submit"
	value="Enviar"></p>
</form>
<%-- 
<script language="JavaScript">
<%if(currUser.getE01INT().equals("03")){%>
  document.forms[0].IDN1.focus();
  document.forms[0].IDN1.select();
<% } else { %>
  document.forms[0].IDN.focus();
  document.forms[0].IDN.select();
<% } %>
</script>
--%>

<SCRIPT Language="Javascript">;
<%if (!error.getERRNUM().equals("0")) {
		error.setERRNUM("0");	
%>
            showErrors();
<% } %>

	showIdFields('<%= msgClient.getE15GEC() %>');
</SCRIPT>
</body>
</html>
