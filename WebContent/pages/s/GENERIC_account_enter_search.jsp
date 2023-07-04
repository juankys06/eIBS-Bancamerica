<%  
response.setHeader("Pragma", "no-cache"); 
response.setDateHeader("Expires", 0); 

response.setHeader("Cache-Control", "no-cache");
response.addHeader("Cache-Control", "max-age=0");
response.addHeader("Cache-Control", "s-maxage=0");
%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.0 Transitional//EN">
<HTML><HEAD>

<META HTTP-EQUIV="Content-Type" CONTENT="text/html; charset=ISO-8859-1">
<META name="GENERATOR" content="IBM WebSphere Page Designer V3.5.2 for Windows">
<META http-equiv="Content-Style-Type" content="text/css">
 
<TITLE>Conexión</TITLE>
<link href="<%=request.getContextPath()%>/pages/style.css" rel="stylesheet">
<script language="Javascript1.1" src="<%=request.getContextPath()%>/pages/s/javascripts/eIBS.jsp"> </SCRIPT>

<jsp:useBean id= "userPO" class= "datapro.eibs.beans.UserPos"  scope="session" />

<jsp:useBean id= "error" class= "datapro.eibs.beans.ELEERRMessage"  scope="session" />

<jsp:useBean id= "currClient" class= "datapro.eibs.beans.ESD080001Message"  scope="session" />

<% 
String client = "0";
String title = "";
String cuenta = "Cuenta";

if (userPO.getPurpose().equals("MAINTENANCE")) {
   title="Mantenimiento de ";
}else if (userPO.getPurpose().equals("STATEMENT")) {
   title="Estados de Cuenta - ";
}
 else if (userPO.getPurpose().equals("PDN")) {
   title="";
} else title = "Consulta de ";

if (userPO.getOption().equals("CD")) { 
    cuenta = "Deposito";
	title = title + "Depositos a Plazo";
} else if (userPO.getOption().equals("LN")) { 
	cuenta = "Prestamo";
	title = title + "Prestamos";
} else if (userPO.getOption().equals("CL")) { 
	cuenta = "Linea";
	title = title + "Linea Multiuso";
} else if (userPO.getOption().equals("RT")) { title = title + "Cuentas Corrientes";
} else if (userPO.getOption().equals("SV")) { title = title + "Cuentas Ahorro";
} else if (userPO.getOption().equals("CC")) { title = title + "Cuenta de Crédito";
} else if (userPO.getOption().equals("WT")) { title = title + "Giros Diferidos";
} else if (userPO.getOption().equals("SB")) { 
	//cuenta = "Prestamo";
	title = title + "Sobregiro Pactado";
} else if (userPO.getOption().equals("LC")) { 
	cuenta = "Linea";
	title = title + "Linea de Credito";
} else if (userPO.getOption().equals("TC")) { 
	//cuenta = "Prestamo";
	title = title + "Tarjetas de Credito";
} else if (userPO.getOption().equals("X1")) { 
	//cuenta = "Linea";
	title = title + "Creditos Hipotecarios";
} else if (userPO.getOption().equals("X2")) { 
	//cuenta = "Linea";
	title = title + "Mutuos Hipotecarios";
} else if (userPO.getOption().equals("X3")) { 
	//cuenta = "Linea";
	title = title + "Comercio Exterior";
} else if (userPO.getOption().equals("X4")) { 
	//cuenta = "Linea";
	title = title + "Cobranza de Letras";
} else if (userPO.getOption().equals("IN")) {
    title = title + "Inversiones";
} else if (userPO.getOption().equals("RT_CHG")) {
    title = title + "Comisiones Varias en Cuentas Corrientes";
} else if (userPO.getOption().equals("SV_CHG")) {
    title = title + "Comisiones Varias en Cuentas de Ahorro";
} else if (userPO.getOption().equals("LNR")) {
    title = title + "Tasa de Cambio en Prestamos";
} else if (userPO.getOption().equals("PR_OP")) {
    title = title + "Ordenes de Pago";
}

if (currClient != null) {
  client = currClient.getE01CUSCUN();
}
%>


<%
// ***********************************
// **                               ** 
// **   RFPN088 - New search mode   **
// **                               ** 
// ***********************************

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

function enter(code) {
 	document.forms[0].<%= userPO.getHeader1() %>.value = code;
	document.forms[0].submit();
}
  
function enterAction(code,numc,id,prod,card) {
 	document.forms[0].<%= userPO.getHeader1() %>.value = code;
 	<% if (!userPO.getHeader3().equals("")) {%>
 		document.forms[0].<%= userPO.getHeader3() %>.value = prod;
 	<% } %>
 	<% if (!userPO.getHeader4().equals("")) {%>
 		document.forms[0].<%= userPO.getHeader4() %>.value = id;
 	<% } %>
 	<% if (!userPO.getHeader5().equals("")) {%>
 		document.forms[0].<%= userPO.getHeader5() %>.value = card;
 	<% } %>
 	document.forms[0].SELCLIENT.value = numc;
 	document.forms[0].submit();
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
	document.getElementById('DIVNAME').style.display="none" ;
	document.getElementById('DIVCOUNTRY').style.display="" ;
	document.getElementById('DIVCUSTCLASS').style.display="none" ;
	document.getElementById('IDVEN').style.display="none" ;
	document.getElementById('IDOTHER').style.display="none" ;
	document.getElementById('IDPAPERS').style.display="none" ;
	document.getElementById('IDPACORP').style.display="none" ;
    document.forms[0].COUNTRY.focus();
  }
  else{
	document.getElementById('DIVNAME').style.display="" ;
	document.getElementById('DIVCOUNTRY').style.display="none" ;
	document.getElementById('DIVCUSTCLASS').style.display="none" ;
	document.getElementById('IDVEN').style.display="none" ;
	document.getElementById('IDOTHER').style.display="none" ;
	document.getElementById('IDPAPERS').style.display="none" ;
	document.getElementById('IDPACORP').style.display="none" ;
    document.forms[0].NameSearch.focus();
  }

  if (value == "A"){
   document.getElementById("DIVSUBMIT").style.display="";  
  }

}

function enterSearch(firsTime){
var NameSearch = document.forms[0].NameSearch.value
var FromRecord = 0
var Client = "<%= client %>";
var AppCode = "<%= userPO.getProdCode() %>";
var Bank = "";
var Selection = "";
var Type ="A";
var Account = "";

	if (NameSearch.length < 1){
		NameSearch=".";
	} //else Client = "0";
	
	for(var i = 0; i < document.forms[0].Type.length; i++)
	{
		if (document.forms[0].Type[i].checked)
			 Type = document.forms[0].Type[i].value;
	}
	
	if (Type == "N" && !firsTime) {
		Client = justifyRight(NameSearch,20);
		NameSearch=".";
	}
	if (Type == "A" || Type == "O") { //Account Search
	    Account = NameSearch;
	    NameSearch="";
	}  
	// alert("Type = " + Type);
    parent.results.window.location.href="<%=request.getContextPath()%>/pages/s/MISC_search_wait.jsp?URL='<%=request.getContextPath()%>/servlet/datapro.eibs.helps.JSEWD0005?NameSearch=" + NameSearch + "@FromRecord=" + FromRecord + "@shrBank=" + Bank + "@shrAppCode=" +  AppCode + "@shrSelect=" + Type + "@shrClient=" + Client + "@shrAcc=" + Account + "'";
}

function enterACC(){
var NameSearch = trim(document.forms[0].NameSearch.value);
    
    if (document.getElementById("DIVSUBMIT").style.display !="") {
    	if (document.forms[0].<%= userPO.getHeader1() %>.value !="0") return true;
    	else {
    	   enterSearch(false); //call search
    	   return false;
    	}
    }
	if (NameSearch.length > 0){
	    document.forms[0].<%= userPO.getHeader1() %>.value=NameSearch;	 
	    return true;
	}else{
		alert("Es requerido que se entre un valor");
		document.forms[0].NameSearch.focus();
		return false;
	}
}

<%-- 
// ***********************************
// **                               ** 
// **   RFPN088 - New search mode   **
// **                               ** 
// ***********************************
--%>

function enterInteger1 (idType) {
 if (idType.value != 'P')
   enterInteger();
 }

function joinID(idField, idType, idNum){
	var chkfld = "" ;
	chkfld = document.forms[0].elements["VIDN1"].value;
	document.forms[0].NameSearch.value=document.forms[0].VIDN0.value  +
								document.forms[0].VIDN1.value ;

	if( !chkfld == "" ){
	enterSearch(false);
	}
}

function fill0Left(idField){
	  //alert("**idFieldNAME: "+idField.name + " **Longitud: " + idField.value.length + " **MaxLongitud: " + idField.maxLength);
	    while (idField.value.length < idField.maxLength )
	      idField.value='0'+idField.value;	  	 
}
function joinPersonalIDFieldsPA(){

	var chkfld = "" ;
	chkfld = document.forms[0].elements["PIDN3"].value;
	document.forms[0].NameSearch.value=document.forms[0].PIDN0.value + "-" +
								document.forms[0].PIDN1.value + "-" +
								document.forms[0].PIDN2.value + "-" +
								document.forms[0].PIDN3.value ;
	// alert("PIDN3 = " + chkfld);
	if( !chkfld == "" ){
	enterSearch(false);
	}

}

function joinCorpIDFieldsPA(){
	var dv = "" ;
	dv = document.forms[0].elements["CIDN3"].value;

	if( dv == "" || dv == "undefined" || dv == null ){
		dv = "  " ;
	} else if (dv.length < 2) {
		dv = " " + dv ;
	}

	var chkfld = "" ;
	chkfld = document.forms[0].elements["CIDN2"].value;

	document.forms[0].NameSearch.value=document.forms[0].CIDN0.value + "-" +
								document.forms[0].CIDN1.value + "-" +
								document.forms[0].CIDN2.value + " ";
	if( !chkfld == "" ){
	enterSearch(false);
	}

}

// return the value of the radio button that is checked or return an empty string if none are checked, or there are no radio buttons

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
   if (document.getElementById('DIVCUSTCLASS').style.display == "none") clientTypeSelected="BOTH";
   
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
		document.getElementById('DIVNAME').style.display="none" ;
		document.getElementById('IDVEN').style.display="" ;
		document.getElementById('DIVCUSTCLASS').style.display="none" ;
	} else if( countryCode.toUpperCase() == "PA"){
		document.getElementById('DIVNAME').style.display="none" ;
		document.getElementById('IDVEN').style.display="none" ;
		document.getElementById('DIVCUSTCLASS').style.display="" ;
	} else if( countryCode.toUpperCase() != "" ){
		document.getElementById('DIVNAME').style.display="" ;
		document.getElementById('IDVEN').style.display="none" ;
		document.getElementById('DIVCUSTCLASS').style.display="none" ;
	}
}

function showIdFields(countryCode,cusType){
	
	if( countryCode.toUpperCase() == "VE" ){
		document.getElementById('IDVEN').style.display="" ;
		document.getElementById('IDOTHER').style.display="none" ;
		document.getElementById('IDPAPERS').style.display="none" ;
		document.getElementById('IDPACORP').style.display="none" ;
	} else if( countryCode.toUpperCase() == "PA" && cusType.toUpperCase() == "CORP" ){
		document.getElementById('IDVEN').style.display="none" ;
		document.getElementById('IDOTHER').style.display="none" ;
		document.getElementById('IDPAPERS').style.display="none" ;
		document.getElementById('IDPACORP').style.display="" ;
	} else if( countryCode.toUpperCase() == "PA" && cusType.toUpperCase() == "PERS" ){
		document.getElementById('IDVEN').style.display="none" ;
		document.getElementById('IDOTHER').style.display="none" ;
		document.getElementById('IDPAPERS').style.display="" ;
		document.getElementById('IDPACORP').style.display="none" ;
	} else if( countryCode.toUpperCase() != "" ){
		document.getElementById('IDVEN').style.display="none" ;
		document.getElementById('IDOTHER').style.display="" ;
		document.getElementById('IDPAPERS').style.display="none" ;
		document.getElementById('IDPACORP').style.display="none" ;
	}

}



</script>

</HEAD>


<body> 

<h3 align="center"><%=title%><img src="<%=request.getContextPath()%>/images/e_ibs.gif" align="left" name="EIBS_GIF" alt="account_enter_search, GENERIC"></h3>
<hr size="4">
 <FORM METHOD="post" ACTION="<%=request.getContextPath()%><%= userPO.getRedirect()%>" target="_parent" onsubmit="return(enterACC());">
  <input type="hidden" name="<%= userPO.getHeader1() %>" value="0">
  <input type="hidden" name="<%= userPO.getHeader2() %>" value="">
  <input type="hidden" name="SELCLIENT" value="">
  
  <% if (!userPO.getHeader3().equals("")) {%>
  	<input type="hidden" name="<%= userPO.getHeader3() %>" value="">
  <% } %>
  <% if (!userPO.getHeader4().equals("")) {%>
  	<input type="hidden" name="<%= userPO.getHeader4() %>" value="">
  <% } %>
  <% if (!userPO.getHeader5().equals("")) {%>
  	<input type="hidden" name="<%= userPO.getHeader5() %>" value="">
  <% } %>	      	
  
  <CENTER>  

  
  <table id="TBHELP" align="center">
    <tr>
	 <td nowrap>
	   <table  id="TBHELP" align="center" width="250" border="0" cellspacing="0" cellpadding="0">
		<tr>
		  <td nowrap id="THHELP">Búsqueda por:</td>
		  <td nowrap>
			<input type="radio" name="Type" value="N" onclick="typeClick('N')" checked>N&uacute;mero Cliente
		  </td>		
		</tr>
		<tr>
		  <td nowrap ></td>
		  <td nowrap>
		  	<input type="radio" name="Type" value="I" onclick="typeClick('I')">Identificaci&oacute;n Cliente
		  </td>
		</tr>
		<tr>
		  <td nowrap ></td>
		  <td nowrap>
		  	<input type="radio" name="Type" value="D" onclick="typeClick('D')">Identificaci&oacute;n Larga Cliente
		  </td>
		</tr>		
		<tr>
		  <td nowrap ></td>
		  <td nowrap>
		  	<input type="radio" name="Type" value="S" onclick="typeClick('S')">Nombre Corto
		  </td>
		</tr>
		<tr>
		  <td nowrap ></td>
		  <td nowrap>
		  	<input type="radio" name="Type" value="W" onclick="typeClick('W')">Por Palabra
		  </td>
		</tr>
		<tr>
		  <td nowrap ></td>
		  <td nowrap>
			<input type="radio" name="Type" value="A" onclick="typeClick('A')">Por No. <%=cuenta%>
		  </td>
		</tr>
		<tr>
		  <td nowrap ></td>
		  <td nowrap>
			<input type="radio" name="Type" value="O" onclick="typeClick('O')">Por No. <%=cuenta%> Viejo(a)
		  </td>
		</tr>


	   </table>
	 </td>


<%-- 
// ***********************************
// **                               ** 
// **   RFPN088 - New search mode   **
// **                               ** 
// ***********************************
--%>


	 <td nowrap>
		<div id="DIVNAME" style="position:relative; display:none;">
        <input type="text" name="NameSearch"  size=25 maxlength="30">
        &nbsp;&nbsp;<img src="<%=request.getContextPath()%>/images/search1.gif" style="cursor : hand;" onClick="enterSearch(false)" width="25" height="20"> 
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
			onblur="joinCorpIDFieldsPA()" >
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
          	<div id=DIVSUBMIT align="center" style="display:none;">    
    			<input id="EIBSBTN" type=submit name="Submit" value="Enviar">
          	</div>
      </td>

		<tr>
		  <td nowrap> </td>
		  <td nowrap> </td>
		</tr>

    </tr>
      
 </table>
 </CENTER>





</FORM>
<SCRIPT Language="Javascript">;
	 typeClick('N');
     function resizeDoc() {
       for(var i = 0; i < document.forms[0].Type.length; i++)
		{
		if (document.forms[0].Type[i].checked)
			document.forms[0].Type[i].click();
		}
     }
     
     window.onresize=resizeDoc;
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
 
 if (!client.equals("0")) { 
%>
     <SCRIPT Language="Javascript">
           enterSearch(true);
     </SCRIPT>
 <%
 }
%>


</BODY>
</HTML>
 