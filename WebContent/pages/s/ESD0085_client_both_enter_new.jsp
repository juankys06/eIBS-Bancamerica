<jsp:useBean id="error" class="datapro.eibs.beans.ELEERRMessage"
	scope="session" />

<jsp:useBean id="currUser" class="datapro.eibs.beans.ESS0030DSMessage"
	scope="session" />

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


<%
   //Define variables request
  String countryDsc="";
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
  
  //Obtiene los campos del request
 //Obtiene los campos del request
   if (request.getParameter("COUNTRY") != null){
        country = request.getParameter("COUNTRY");
        if (request.getParameter("COUNTRYDSC") != null) countryDsc = request.getParameter("COUNTRYDSC");        
        if (country.toUpperCase().equals("PA")){ // Panama
	        idn2 = request.getParameter("IDN2");
	        if (idn2 != null && !idn2.equals("")){
            	idn2 = request.getParameter("IDN2");
            	//Obtiene la direccion panameña dependiendo del Legal Type
	      		indexI=idn2.indexOf("-",0);
	      		indexF=idn2.indexOf("-",indexI+1);
	      		if ((request.getParameter("TYPE").equals("PERSONAL"))&&(idn2!= null)){
				     //                 09-PI-00001-001277 70         
				     //                 Pr Lt Folio Asien  DV         
				     //             Provincia  letra Folio  Asiento DV 
				    cProvincia=idn2.substring(0,indexI );
				    cLetra=idn2.substring(indexI+1,indexF );
				    indexI=indexF;
				    indexF=idn2.indexOf("-",indexI+1);
				    cFolio=idn2.substring(indexI+1,indexF);
				    indexI=indexF;
				    indexF=idn2.indexOf(" ",indexI+1);
				    if (indexF>0 ){
					    cAsiento=idn2.substring(indexI+1,indexF);
					    digVer=idn2.substring(indexF+1);			    
				    }else{cAsiento=idn2.substring(indexI+1);}  
	
			       System.out.println("cProvincia:" + cProvincia);
			       System.out.println("cLetra:" + cLetra);
			       System.out.println("cFolio: "+ cFolio);
			       System.out.println("cAsiento: "+ cAsiento);
			       //System.out.println("Digito verif: "+ digVer);

	      		}else if ((request.getParameter("TYPE").equals("CORPORATIVE"))&&(idn2!= null)){
				    //                 0055496-0012-00304533 50
				    //                 Rollo  Folio  Asiento DV      
				    rRollo=idn2.substring(0,indexI);
				    rFolio=idn2.substring(indexI+1,indexF);
				    indexI=indexF;
				    indexF=idn2.indexOf(" ",indexI+1);
				    if (indexF>0 ){
					    rAsiento=idn2.substring(indexI+1,indexF);
					    digVer=idn2.substring(indexF+1);			    
				    }else{rAsiento=idn2.substring(indexI+1);}  
				       System.out.println("rRollo:" + rRollo);
				       System.out.println("rFolio: "+ rFolio);
				       System.out.println("rAsiento: "+ rAsiento);
				       System.out.println("Digito verif: "+ digVer);    
			    }
			}
			
		}else {  // venezuela y otros
            if (request.getParameter("IDN")!= null) idn = request.getParameter("IDN");               
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
   var clientTypeSelected="CORPORATIVE";
   for (counter = 0; counter < document.forma.TYPE.length; counter++)
   {
      if (document.forma.TYPE[counter].checked)
        clientTypeSelected = document.forma.TYPE[counter].value; 
   }
   // Display the id screen help 
   if (clientTypeSelected == "CORPORATIVE") 
     GetCode('VIDN0','STATIC_client_help_id_type.jsp?clientType=CORPORATIVE');
   else
     GetCode('VIDN0','STATIC_client_help_id_type.jsp?clientType=PERSONAL');
 }
 
function showIdFields(countryCode){

	var cusType = getCheckedValue(document.forms[0].elements["TYPE"]) ;
	
	countryCode="DR";
	
	if( countryCode.toUpperCase() == "VE" ){
				document.getElementById('IDVEN').style.display='block' ;
				document.getElementById('IDOTHER').style.display='none' ;
				document.getElementById('IDPAPERS').style.display='none' ;
				document.getElementById('IDPACORP').style.display='none' ;
	} else if( countryCode.toUpperCase() == "PA" && cusType.toUpperCase() == "CORPORATIVE" ){
				document.getElementById('IDVEN').style.display='none' ;
				document.getElementById('IDOTHER').style.display='none' ;
				document.getElementById('IDPAPERS').style.display='none' ;
				document.getElementById('IDPACORP').style.display='block' ;
	} else if( countryCode.toUpperCase() == "PA" && cusType.toUpperCase() == "PERSONAL" ){
				document.getElementById('IDVEN').style.display='none' ;
				document.getElementById('IDOTHER').style.display='none' ;
				document.getElementById('IDPAPERS').style.display='block' ;
				document.getElementById('IDPACORP').style.display='none' ;
	} else if( countryCode.toUpperCase() == "DR" && cusType.toUpperCase() == "CORPORATIVE" ){
				document.getElementById('IDVEN').style.display='none' ;
				document.getElementById('IDOTHER').style.display='none' ;
				document.getElementById('IDDRPERS').style.display='none' ;
				document.getElementById('IDDRCORP').style.display='block' ;
	} else if( countryCode.toUpperCase() == "DR" && cusType.toUpperCase() == "PERSONAL" ){
				document.getElementById('IDVEN').style.display='none' ;
				document.getElementById('IDOTHER').style.display='none' ;
				document.getElementById('IDDRPERS').style.display='block' ;
				document.getElementById('IDDRCORP').style.display='none' ;
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

function joinPersonalIDFieldsDR(){

	document.forms[0].IDN2.value=document.forms[0].DRPIDN0.value + 
								document.forms[0].DRPIDN1.value + 
								document.forms[0].DRPIDN2.value ;
								
}

function joinCorpIDFieldsDR(){

	

	document.forms[0].IDN2.value=document.forms[0].DRCIDN0.value + 
								document.forms[0].DRCIDN1.value + 
								document.forms[0].DRCIDN2.value + 
								document.forms[0].DRCIDN3.value ;

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

<h3 align="center">Mantenimiento Padron Electoral Alterno<img
	src="<%=request.getContextPath()%>/images/e_ibs.gif" align="left"
	name="EIBS_GIF" alt="client_both_enter_new, ESD0085"></h3>
<hr size="4">
<form name="forma" method="post"
	action="<%=request.getContextPath()%>/servlet/datapro.eibs.client.JSESD0085">
<p><INPUT TYPE=HIDDEN NAME="SCREEN" VALUE="400"></p>
<h4 align="center">Ingrese el N&uacute;mero de
Identificaci&oacute;n.</h4>
<p>&nbsp;</p>
<table class="tableinfo">

	<tr>
		<td nowrap>
		<table cellspacing="0" cellpadding="2" width="100%" border="0">
			<tr id="trdark">
				<td nowrap align="right" valign="middle" width="49%">Pa&iacute;s :</td>
				<td colspan="2">Republica Dominicana
				<INPUT TYPE=HIDDEN NAME="INT" value=""> 
				<INPUT TYPE=HIDDEN NAME="COUNTRY" value="DR"> 
				</td>
			</tr>

			<tr id="trclear">
				<td nowrap align="right" valign="middle" width="49%">Tipo de Cliente :</td>
				<td colspan="2">
				<INPUT TYPE=HIDDEN NAME="IDN" value=""> 
				<INPUT TYPE=HIDDEN NAME="IDN2" value=""> 
				<label for="corp" > 
				</label>
				<label for="pers" >
				<input type="radio" id="pers" name="TYPE" value="PERSONAL" checked="checked" onclick="showIdFields(document.forms[0].COUNTRY.value);" >Fisico 
				</label>
				<label for="other" >
				</label>
				</td>
			</tr>
		</table>

		<div id="IDVEN" style="position:relative; display:none;">
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
				<input type="text" name="VIDN0" value="" maxlength="1" size="1" readonly
					onchange="joinID(document.forms[0].IDN, document.forms[0].VIDN0, document.forms[0].VIDN1);">
				<a href="javascript:getIdTypeHelp()"> 
				<img src="<%=request.getContextPath()%>/images/1b.gif" alt=". . ."
					align="bottom" border="0">
				</a> 
				<input type="text" name="VIDN1" value="<%=idn%>" maxlength="14" size="17"
					onKeypress="enterInteger1(document.forms[0].VIDN0)"
					onchange="joinID(document.forms[0].IDN, document.forms[0].VIDN0, document.forms[0].VIDN1);">

				</td>
			</tr>
		</table>
		</div>

		<div id="IDOTHER" style="position:relative; display:none;">
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
				<input type="text" name="OIDNO" value="<%=idn%>" maxlength="15" size="17"
				onblur="document.forms[0].IDN.value=this.value" >
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
				<input type="text" name="CIDN0" value="<%=rRollo%>" maxlength="7" size="8" onKeyPress="enterInteger();"
				onblur="fill0Left(this);joinCorpIDFieldsPA();" > - 
				<input type="text" name="CIDN1" value="<%=rFolio%>" maxlength="4" size="5" onKeyPress="enterInteger();"
				onblur="fill0Left(this);joinCorpIDFieldsPA();" > - 
				<input type="text" name="CIDN2" value="<%=rAsiento%>" maxlength="8" size="9" onKeyPress="enterInteger();"
				onblur="fill0Left(this);joinCorpIDFieldsPA();" >
				<input type="hidden" name="CIDN3" value="00" maxlength="2" size="3" onKeyPress="enterInteger();"
				onblur="joinCorpIDFieldsPA();document.forms[0].EIBSBTN.focus();" >
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
				<input type="text" name="PIDN0" value="<%=cProvincia %>" maxlength="2" size="3" onKeyPress="enterInteger();"
				onblur="fill0Left(this);joinPersonalIDFieldsPA();" > - 
				<input type="text" name="PIDN1" value="<%=cLetra  %>" maxlength="2" size="3"
				onblur="this.value=this.value.replace(/ /ig,'');joinPersonalIDFieldsPA();" > - 
				<input type="text" name="PIDN2" value="<%=cFolio  %>" maxlength="5" size="6" onKeyPress="enterInteger();"
				onblur="fill0Left(this);joinPersonalIDFieldsPA();" > - 
				<input type="text" name="PIDN3" value="<%=cAsiento  %>" maxlength="6" size="7" onKeyPress="enterInteger();"
				onblur="fill0Left(this);joinPersonalIDFieldsPA();" >
				<%-- 
				<input type="text" name="PIDN4" value="  " maxlength="2" size="4"
				onblur="joinPersonalIDFieldsPA();" readonly="readonly" >
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
				<input type="text" name="DRCIDN0" value="<%=rRollo%>" maxlength="1" size="2" onKeyPress="enterInteger();"
				onblur="fill0Left(this);joinCorpIDFieldsDR();" > - 
				<input type="text" name="DRCIDN1" value="<%=rFolio%>" maxlength="2" size="3" onKeyPress="enterInteger();"
				onblur="fill0Left(this);joinCorpIDFieldsDR();" > - 
				<input type="text" name="DRCIDN2" value="<%=rAsiento%>" maxlength="5" size="6" onKeyPress="enterInteger();"
				onblur="fill0Left(this);joinCorpIDFieldsDR();" > -
				<input type="text" name="DRCIDN3" value="<%=digVer%>" maxlength="1" size="2" onKeyPress="enterInteger();"
				onblur="joinCorpIDFieldsDR();document.forms[0].EIBSBTN.focus();" >
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
				<input type="text" name="DRPIDN0" value="<%=cProvincia %>" maxlength="3" size="4" onKeyPress="enterInteger();"
				onblur="fill0Left(this);joinPersonalIDFieldsDR();" > - 
				<input type="text" name="DRPIDN1" value="<%=cLetra  %>" maxlength="7" size="8" onKeyPress="enterInteger();"
				onblur="fill0Left(this);joinPersonalIDFieldsDR();" > - 
				<input type="text" name="DRPIDN2" value="<%=digVer  %>" maxlength="1" size="2" onKeyPress="enterInteger();"
				onblur="joinPersonalIDFieldsDR();document.forms[0].EIBSBTN.focus();" >  
								
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

	showIdFields('<%= country %>');
</SCRIPT>
</body>
</html>
