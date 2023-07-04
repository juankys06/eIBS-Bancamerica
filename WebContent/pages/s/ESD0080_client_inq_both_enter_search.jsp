<!-- saved from url=(0022)http://internet.e-mail -->
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<HTML><HEAD>
<META HTTP-EQUIV="Content-Type" CONTENT="text/html; charset=ISO-8859-1">
<META name="GENERATOR" content="IBM WebSphere Page Designer V3.5.2 for Windows">
<META http-equiv="Content-Style-Type" content="text/css"> 
<TITLE>Conexión</TITLE>
<link href="<%=request.getContextPath()%>/pages/style.css" rel="stylesheet">
<script language="Javascript1.1" src="<%=request.getContextPath()%>/pages/s/javascripts/eIBS.jsp"> </SCRIPT>

<jsp:useBean id= "client" class= "datapro.eibs.beans.ESD008001Message"  scope="session" />

<jsp:useBean id= "error" class= "datapro.eibs.beans.ELEERRMessage"  scope="session" />

<jsp:useBean id= "currClient" class= "datapro.eibs.beans.ESD080001Message"  scope="session" />

<%
   //Define variables
  String countryDsc="";
  String country="";  
  String idn="";
  String idn2="";
   //Define variables identificacion Panameña
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
 %>

<script language="JavaScript" id="eAction"> 
function enterAction(code,desc,id) {
 var form = document.forms[0];
 form["E01CUN"].value = code;
 form["E01IDN"].value = id;
 form.submit();
}
</script>
 
<script language="JavaScript">
function justifyRight(c,l) {
	var fc = "";
	for (var i = 0; i < (l - c.length); i++){
	  	fc += "0";
	}
	return (fc + c);
}

function typeClick(value){
  if (value == "I" || value == "D"){    
	document.getElementById('DIVNAME').style.display='none' ;
	document.getElementById('DIVCOUNTRY').style.display='block' ;
	document.getElementById('DIVCUSTCLASS').style.display='none' ;
	document.getElementById('IDVEN').style.display='none' ;
	document.getElementById('IDOTHER').style.display='none' ;
	document.getElementById('IDPAPERS').style.display='none' ;
	document.getElementById('IDPACORP').style.display='none' ;
    document.forms[0].COUNTRY.focus();
	DIVSUBMIT.style.display="";   
  }
  else{
	document.getElementById('DIVNAME').style.display='block' ;
	document.getElementById('DIVCOUNTRY').style.display='none' ;
	document.getElementById('DIVCUSTCLASS').style.display='none' ;
	document.getElementById('IDVEN').style.display='none' ;
	document.getElementById('IDOTHER').style.display='none' ;
	document.getElementById('IDPAPERS').style.display='none' ;
	document.getElementById('IDPACORP').style.display='none' ;
    document.forms[0].NameSearch.focus();
	DIVSUBMIT.style.display="none"; 
  }

  if (value == "I" || value == "N"|| value == "D"){    
   DIVSUBMIT.style.display="block";   
  }
  else{
   DIVSUBMIT.style.display="none"; 
  }

}

function enter(){
var NameSearch = document.forms[0].NameSearch.value;
var FromRecord = 0;
var Type ='N';

	if (NameSearch.length < 1){
		alert("Debe entrar algún criterio de búsqueda");
		return ;
	}
	
	for(var i = 0; i < document.forms[0].Type.length; i++)
	{
		if (document.forms[0].Type[i].checked)
			Type = document.forms[0].Type[i].value;
	}
    
    if (Type == "N") {
		NameSearch = justifyRight(NameSearch,30);
	}
    parent.results.window.location.href="<%=request.getContextPath()%>/pages/s/MISC_search_wait.jsp?URL='<%=request.getContextPath()%>/servlet/datapro.eibs.helps.JSEWD0001?NameSearch=" + NameSearch + "@Type=" + Type + "@FromRecord=" + FromRecord + "'";
}

function enterRUT(){
var NameSearch = document.forms[0].NameSearch.value
//		alert(document.forms[0].NameSearch.value);
	if (DIVSUBMIT.style.display !="block") return false;
	if (NameSearch.length > 0){
	    if (document.forms[0].Type[0].checked) document.forms[0].E01CUN.value=NameSearch;
	    else {
	    	document.forms[0].E01IDN.value=NameSearch;
	    	document.forms[0].E01CUN.value="0";
	    }
	    return true;
	}else{
		alert("Es requerido que se entre un valor");
		document.forms[0].NameSearch.focus();
		return false;
	}
}

function enterInteger1 (idType) {
 if (idType.value != 'P')
   enterInteger();
 }

function joinID(idField, idType, idNum){

	document.forms[0].NameSearch.value=document.forms[0].VIDN0.value  +
								document.forms[0].VIDN1.value ;

//	  var strID = "";
//	    while ((idNum.value.length < 9) && (idType.value!='P'))
//	      idNum.value='0'+idNum.value;
//	  strID = trim(idType.value) + trim(idNum.value);
//    idField.value=strID.toUpperCase();
//	  document.forms[0].NameSearch.value=strID.toUpperCase();      
}

function fill0Left(idField){
	  //alert("**idFieldNAME: "+idField.name + " **Longitud: " + idField.value.length + " **MaxLongitud: " + idField.maxLength);
	    while (idField.value.length < idField.maxLength )
	      idField.value='0'+idField.value;	  	 
}
function joinPersonalIDFieldsPA(){
	document.forms[0].NameSearch.value=document.forms[0].PIDN0.value + "-" +
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

	document.forms[0].NameSearch.value=document.forms[0].CIDN0.value + "-" +
								document.forms[0].CIDN1.value + "-" +
								document.forms[0].CIDN2.value + " ";
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


function getIdTypeHelp(){
   // Get the client type selected
   var clientTypeSelected="CORP";
   if (document.getElementById('DIVCUSTCLASS').style.display == 'none') clientTypeSelected="BOTH";
   
   for (counter = 0; counter < document.forms[0].CUSCLS.length; counter++)
   {
      if (document.forms[0].CUSCLS[counter].checked)
        clientTypeSelected = document.forms[0].CUSCLS[counter].value; 
   }
   
   // Display the id screen help 
   if (clientTypeSelected == "CORP") 
     GetCode('VIDN0','STATIC_client_help_id_type.jsp?clientType=CORPORATIVE');
   else if (clientTypeSelected == "BOTH")
     GetCode('VIDN0','STATIC_client_help_id_type.jsp?clientType=BOTH');   	
   else
     GetCode('VIDN0','STATIC_client_help_id_type.jsp?clientType=PERSONAL');
 }
 
function showFields(countryCode){
	
	if( countryCode.toUpperCase() == "VE" ){
		document.getElementById('DIVNAME').style.display='none' ;
		document.getElementById('IDVEN').style.display='block' ;
		document.getElementById('DIVCUSTCLASS').style.display='none' ;
	} else if( countryCode.toUpperCase() == "PA"){
		document.getElementById('DIVNAME').style.display='none' ;
		document.getElementById('IDVEN').style.display='none' ;
		document.getElementById('DIVCUSTCLASS').style.display='block' ;
	} else if( countryCode.toUpperCase() != "" ){
		document.getElementById('DIVNAME').style.display='block' ;
		document.getElementById('IDVEN').style.display='none' ;
		document.getElementById('DIVCUSTCLASS').style.display='none' ;
	}
}

function showIdFields(countryCode,cusType){
	
	if( countryCode.toUpperCase() == "VE" ){
		document.getElementById('IDVEN').style.display='block' ;
		document.getElementById('IDOTHER').style.display='none' ;
		document.getElementById('IDPAPERS').style.display='none' ;
		document.getElementById('IDPACORP').style.display='none' ;
	} else if( countryCode.toUpperCase() == "PA" && cusType.toUpperCase() == "CORP" ){
		document.getElementById('IDVEN').style.display='none' ;
		document.getElementById('IDOTHER').style.display='none' ;
		document.getElementById('IDPAPERS').style.display='none' ;
		document.getElementById('IDPACORP').style.display='block' ;
	} else if( countryCode.toUpperCase() == "PA" && cusType.toUpperCase() == "PERS" ){
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

</script>

</HEAD>


<body >

<h3 align="center">Consulta de Cliente<img src="<%=request.getContextPath()%>/images/e_ibs.gif" align="left" name="EIBS_GIF" alt="client_inq_both_enter_search, ESD0080"></h3>
<hr size="4">
 <FORM METHOD="post" ACTION="<%=request.getContextPath()%>/servlet/datapro.eibs.client.JSESD0080" target="_parent" onsubmit="return(enterRUT());">
  <CENTER>
  <INPUT TYPE=HIDDEN NAME="SCREEN" VALUE="600">
  <input type="hidden" name="E01CUN" value="<%= client.getE01CUN() %>" size=15 maxlength=9 >
  <input type="hidden" name="E01IDN" value="<%= client.getE01IDN() %>" size=15 maxlength=9 >
  
  <table id="TBHELP" align="center">
    <tr>
	 <td nowrap>
	   <table  id="TBHELP" align="center" width="250" border="0" cellspacing="0" cellpadding="0">
		<tr>
		  <td nowrap id="THHELP">Búsqueda por:</td>
		  <td nowrap><input type="radio" name="Type" value="N" onclick="typeClick('N')">N&uacute;mero</td>		
		</tr>
		<tr>
		  <td nowrap ></td>
		  <td nowrap><input type="radio" name="Type" value="I" onclick="typeClick('I')">Identificaci&oacute;n</td>
		</tr>
		<tr>
		  <td nowrap ></td>
		  <td nowrap><input type="radio" name="Type" value="D" onclick="typeClick('D')">Identificaci&oacute;n Larga</td>
		</tr>		
		<tr>
		  <td nowrap ></td>
		  <td nowrap><input type="radio" name="Type" value="S" onclick="typeClick('S')">Nombre Corto</td>
		</tr>
		<tr>
		  <td nowrap ></td>
		  <td nowrap><input type="radio" name="Type" value="W" onclick="typeClick('W')">Por Palabra</td>
		</tr>
	   </table>
	 </td>

	 <td nowrap>
		<div id="DIVNAME" style="position:relative; display:none;">
        <input type="text" name="NameSearch"  size=25 maxlength="30">
        &nbsp;&nbsp;<img src="<%=request.getContextPath()%>/images/search1.gif" style="cursor : hand;" onClick="enter()" width="25" height="20"> 
       </div>
     </td>


	 <td nowrap>
		<div id="DIVCOUNTRY" style="position:relative; display:none;">
			Pais:
			<INPUT TYPE=HIDDEN NAME="INT" value=""> 
			<INPUT TYPE="text" NAME="COUNTRY" value="<%=country%>" size="3" maxlength="2"  onblur="showFields(document.forms[0].COUNTRY.value);">
			<a href="javascript:GetCodeDescCNOFC('COUNTRY','','03')"> 
			<img src="<%=request.getContextPath()%>/images/1b.gif" alt=". . ." align="bottom" border="0"></a> 
		</div>
	</td>


	 <td nowrap>
		<div id="DIVCUSTCLASS" style="position:relative; display:none;">
			<INPUT TYPE=HIDDEN NAME="IDN" value=""> 
			<INPUT TYPE=HIDDEN NAME="IDN2" value=""> 
			<label for="corp" >
			<input type="radio" id="corp" name="CUSCLS" value="CORP" onclick="showIdFields(document.forms[0].COUNTRY.value,'CORP');" >Corporativo 
			</label>
			<label for="pers" >
			<input type="radio" id="pers" name="CUSCLS" value="PERS" onclick="showIdFields(document.forms[0].COUNTRY.value,'PERS');" >Personal 
			</label>
			<label for="other" >
			<input type="radio" id="other" name="CUSCLS" value="OTHER" onclick="showIdFields(document.forms[0].COUNTRY.value,'OTHE');" >Otro
			</label>
		</div>
	</td>


	 <td nowrap>
		<div id="IDVEN" style="position:relative; display:none;">
			<input type="text" name="VIDN0" value="<%=idn2%>" maxlength="1" size="1" 
			onblur="joinID(document.forms[0].IDN, document.forms[0].VIDN0, document.forms[0].VIDN1);">
				<a href="javascript:getIdTypeHelp()"> 
				<img src="<%=request.getContextPath()%>/images/1b.gif" alt=". . ."
					align="bottom" border="0">
				</a> 
			<input type="text" name="VIDN1" value="<%=idn%>" maxlength="14" size="17"
				onKeypress="enterInteger1(document.forms[0].VIDN0)"
				onblur="joinID(document.forms[0].IDN, document.forms[0].VIDN0, document.forms[0].VIDN1);">
		</div>
	</td>


    <td nowrap>
		<div id="IDOTHER" style="position:relative; display:none;">
			<input type="text" name="OIDNO" value="<%=idn%>" maxlength="15" size="17"
			onblur="document.forms[0].NameSearch.value=this.value" >
		</div>
	</td>

    <td nowrap>
		<div id="IDPACORP" style="position:relative; display:none;">
			<input type="text" name="CIDN0" value="<%=rRollo%>" maxlength="7" size="8" onKeyPress="enterInteger();"
			onblur="fill0Left(this);joinCorpIDFieldsPA();" > - 
			<input type="text" name="CIDN1" value="<%=rFolio%>" maxlength="4" size="5" onKeyPress="enterInteger();"
			onblur="fill0Left(this);joinCorpIDFieldsPA();" > - 
			<input type="text" name="CIDN2" value="<%=rAsiento%>" maxlength="8" size="9" onKeyPress="enterInteger();"
			onblur="fill0Left(this);joinCorpIDFieldsPA();" >
			<input type="hidden" name="CIDN3" value="00" maxlength="2" size="3" onKeyPress="enterInteger();"
			onblur="joinCorpIDFieldsPA();document.forms[0].EIBSBTN.focus();" >
		</div>
	</td>


    <td nowrap>
		<div id="IDPAPERS" style="position:relative; display:none;">
			<input type="text" name="PIDN0" value="<%=cProvincia %>" maxlength="2" size="3" onKeyPress="enterInteger();"
			onblur="fill0Left(this);joinPersonalIDFieldsPA();" > - 
			<input type="text" name="PIDN1" value="<%=cLetra  %>" maxlength="2" size="3"
			onblur="this.value=this.value.replace(/ /ig,'');joinPersonalIDFieldsPA();" > - 
			<input type="text" name="PIDN2" value="<%=cFolio  %>" maxlength="5" size="6" onKeyPress="enterInteger();"
			onblur="fill0Left(this);joinPersonalIDFieldsPA();" > - 
			<input type="text" name="PIDN3" value="<%=cAsiento  %>" maxlength="6" size="7" onKeyPress="enterInteger();"
			onblur="fill0Left(this);joinPersonalIDFieldsPA();" >
		</div>
	</td>

	</tr>

   <tr valign="middle"> 
      <td nowrap colspan="2" align="center" >
      	  <p>
          	<div id=DIVSUBMIT align="center">    
    			<input id="EIBSBTN" type=submit name="Submit" value="Enviar">
          	</div>
          
      </td>
    </tr>
      
 </table>
 </CENTER>
</FORM>
<SCRIPT Language="Javascript">;     
     <% 		
 		if (currClient != null && !currClient.getE01CUSCUN().equals("0")) {
  	 %>
  		document.forms[0].NameSearch.value = "<%= currClient.getE01CUSCUN() %>";
  		enter();	
 	<%	}
		%>
//		document.forms[0].NameSearch.focus();
</SCRIPT>
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
</BODY>
</HTML>
 