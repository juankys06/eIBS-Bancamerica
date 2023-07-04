<html>
<head>
<title>Mantenimiento Rutas de Propuesta de Credito</title>
<META HTTP-EQUIV="Content-Type" CONTENT="text/html; charset=ISO-8859-1">
<link Href="<%=request.getContextPath()%>/pages/style.css" rel="stylesheet">

<jsp:useBean id="brnDetails" class="datapro.eibs.beans.EDP070801Message"  scope="session" />

<jsp:useBean id= "error" class= "datapro.eibs.beans.ELEERRMessage"  scope="session" />

<jsp:useBean id= "optLP4" class= "datapro.eibs.beans.JBList"  scope="session" />

<jsp:useBean id= "userPO" class= "datapro.eibs.beans.UserPos"  scope="session" />
<jsp:useBean id= "currUser" class= "datapro.eibs.beans.ESS0030DSMessage"  scope="session" />

<script language="Javascript1.1" src="<%=request.getContextPath()%>/pages/s/javascripts/eIBS.jsp"> </SCRIPT>

<script language="JavaScript">

//carga valores iniciales de formulas indices
function initOver() {
i=0 ;

selectedValue = "<%= brnDetails.getE01DPPP01().trim() %>";
if ((selectedValue != null ) && (formatFloat(selectedValue) > 0 )){
newoptionX = "<%= brnDetails.getE01DPPP01().trim()%>";
newoptionX = newoptionX + " - " ;
newoptionX = newoptionX + "<%= brnDetails.getE01DPPD01().trim()%>";
newoption = new Option(newoptionX, selectedValue, false, false);
document.forms[0].choiceBox.options[i] = newoption;
i++ ;
}

selectedValue = "<%= brnDetails.getE01DPPP02().trim() %>";
if ((selectedValue != null ) && (formatFloat(selectedValue) > 0 )){
newoptionX = "<%= brnDetails.getE01DPPP02().trim()%>";
newoptionX = newoptionX + " - " ;
newoptionX = newoptionX + "<%= brnDetails.getE01DPPD02().trim()%>";
newoption = new Option(newoptionX, selectedValue, false, false);
document.forms[0].choiceBox.options[i] = newoption;
i++ ;
}

selectedValue = "<%= brnDetails.getE01DPPP03().trim() %>";
if ((selectedValue != null  ) && (formatFloat(selectedValue) > 0 )) {
newoptionX = "<%= brnDetails.getE01DPPP03().trim()%>";
newoptionX = newoptionX + " - " ;
newoptionX = newoptionX + "<%= brnDetails.getE01DPPD03().trim()%>";
newoption = new Option(newoptionX, selectedValue, false, false);
document.forms[0].choiceBox.options[i] = newoption;
i++ ;
}

selectedValue = "<%= brnDetails.getE01DPPP04().trim() %>";
if ((selectedValue != null ) && (formatFloat(selectedValue) > 0 )) {
newoptionX = "<%= brnDetails.getE01DPPP04().trim()%>";
newoptionX = newoptionX + " - " ;
newoptionX = newoptionX + "<%= brnDetails.getE01DPPD04().trim()%>";
newoption = new Option(newoptionX, selectedValue, false, false);
document.forms[0].choiceBox.options[i] = newoption;
i++ ;
}

selectedValue = "<%= brnDetails.getE01DPPP05().trim() %>";
if ((selectedValue != null ) && (formatFloat(selectedValue) > 0 )){
newoptionX = "<%= brnDetails.getE01DPPP05().trim()%>";
newoptionX = newoptionX + " - " ;
newoptionX = newoptionX + "<%= brnDetails.getE01DPPD05().trim()%>";
newoption = new Option(newoptionX, selectedValue, false, false);
document.forms[0].choiceBox.options[i] = newoption;
i++ ;
}

selectedValue = "<%= brnDetails.getE01DPPP06().trim() %>";
if ((selectedValue != null ) && (formatFloat(selectedValue) > 0 )){
newoptionX = "<%= brnDetails.getE01DPPP06().trim()%>";
newoptionX = newoptionX + " - " ;
newoptionX = newoptionX + "<%= brnDetails.getE01DPPD06().trim()%>";
newoption = new Option(newoptionX, selectedValue, false, false);
document.forms[0].choiceBox.options[i] = newoption;
i++ ;
}

selectedValue = "<%= brnDetails.getE01DPPP07().trim() %>";
if ((selectedValue != null ) && (formatFloat(selectedValue) > 0 )){
newoptionX = "<%= brnDetails.getE01DPPP07().trim()%>";
newoptionX = newoptionX + " - " ;
newoptionX = newoptionX + "<%= brnDetails.getE01DPPD07().trim()%>";
newoption = new Option(newoptionX, selectedValue, false, false);
document.forms[0].choiceBox.options[i] = newoption;
i++ ;
}

selectedValue = "<%= brnDetails.getE01DPPP08().trim() %>";
if ((selectedValue != null ) && (formatFloat(selectedValue) > 0 )){
newoptionX = "<%= brnDetails.getE01DPPP08().trim()%>";
newoptionX = newoptionX + " - " ;
newoptionX = newoptionX + "<%= brnDetails.getE01DPPD08().trim()%>";
newoption = new Option(newoptionX, selectedValue, false, false);
document.forms[0].choiceBox.options[i] = newoption;
i++ ;
}

selectedValue = "<%= brnDetails.getE01DPPP09().trim() %>";
if ((selectedValue != null ) && (formatFloat(selectedValue) > 0 )){
newoptionX = "<%= brnDetails.getE01DPPP09().trim()%>";
newoptionX = newoptionX + " - " ;
newoptionX = newoptionX + "<%= brnDetails.getE01DPPD09().trim()%>";
newoption = new Option(newoptionX, selectedValue, false, false);
document.forms[0].choiceBox.options[i] = newoption;
i++ ;
}

selectedValue = "<%= brnDetails.getE01DPPP10().trim() %>";
if ((selectedValue != null ) && (formatFloat(selectedValue) > 0 )){
newoptionX = "<%= brnDetails.getE01DPPP10().trim()%>";
newoptionX = newoptionX + " - " ;
newoptionX = newoptionX + "<%= brnDetails.getE01DPPD10().trim()%>";
newoption = new Option(newoptionX, selectedValue, false, false);
document.forms[0].choiceBox.options[i] = newoption;
i++ ;
}

selectedValue = "<%= brnDetails.getE01DPPP11().trim() %>";
if ((selectedValue != null ) && (formatFloat(selectedValue) > 0 )){
newoptionX = "<%= brnDetails.getE01DPPP11().trim()%>";
newoptionX = newoptionX + " - " ;
newoptionX = newoptionX + "<%= brnDetails.getE01DPPD11().trim()%>";
newoption = new Option(newoptionX, selectedValue, false, false);
document.forms[0].choiceBox.options[i] = newoption;
i++ ;
}

selectedValue = "<%= brnDetails.getE01DPPP12().trim() %>";
if ((selectedValue != null ) && (formatFloat(selectedValue) > 0 )){
newoptionX = "<%= brnDetails.getE01DPPP12().trim()%>";
newoptionX = newoptionX + " - " ;
newoptionX = newoptionX + "<%= brnDetails.getE01DPPD12().trim()%>";
newoption = new Option(newoptionX, selectedValue, false, false);
document.forms[0].choiceBox.options[i] = newoption;
i++ ;
}

selectedValue = "<%= brnDetails.getE01DPPP13().trim() %>";
if ((selectedValue != null ) && (formatFloat(selectedValue) > 0 )){
newoptionX = "<%= brnDetails.getE01DPPP13().trim()%>";
newoptionX = newoptionX + " - " ;
newoptionX = newoptionX + "<%= brnDetails.getE01DPPD13().trim()%>";
newoption = new Option(newoptionX, selectedValue, false, false);
document.forms[0].choiceBox.options[i] = newoption;
i++ ;
}

selectedValue = "<%= brnDetails.getE01DPPP14().trim() %>";
if ((selectedValue != null ) && (formatFloat(selectedValue) > 0 )){
newoptionX = "<%= brnDetails.getE01DPPP14().trim()%>";
newoptionX = newoptionX + " - " ;
newoptionX = newoptionX + "<%= brnDetails.getE01DPPD14().trim()%>";
newoption = new Option(newoptionX, selectedValue, false, false);
document.forms[0].choiceBox.options[i] = newoption;
i++ ;
}

selectedValue = "<%= brnDetails.getE01DPPP15().trim() %>";
if ((selectedValue != null ) && (formatFloat(selectedValue) > 0 )){
newoptionX = "<%= brnDetails.getE01DPPP15().trim()%>";
newoptionX = newoptionX + " - " ;
newoptionX = newoptionX + "<%= brnDetails.getE01DPPD15().trim()%>";
newoption = new Option(newoptionX, selectedValue, false, false);
document.forms[0].choiceBox.options[i] = newoption;
i++ ;
}

selectedValue = "<%= brnDetails.getE01DPPP16().trim() %>";
if ((selectedValue != null ) && (formatFloat(selectedValue) > 0 )){
newoptionX = "<%= brnDetails.getE01DPPP16().trim()%>";
newoptionX = newoptionX + " - " ;
newoptionX = newoptionX + "<%= brnDetails.getE01DPPD16().trim()%>";
newoption = new Option(newoptionX, selectedValue, false, false);
document.forms[0].choiceBox.options[i] = newoption;
i++ ;
}

selectedValue = "<%= brnDetails.getE01DPPP17().trim() %>";
if ((selectedValue != null ) && (formatFloat(selectedValue) > 0 )){
newoptionX = "<%= brnDetails.getE01DPPP17().trim()%>";
newoptionX = newoptionX + " - " ;
newoptionX = newoptionX + "<%= brnDetails.getE01DPPD17().trim()%>";
newoption = new Option(newoptionX, selectedValue, false, false);
document.forms[0].choiceBox.options[i] = newoption;
i++ ;
}

selectedValue = "<%= brnDetails.getE01DPPP18().trim() %>";
if ((selectedValue != null ) && (formatFloat(selectedValue) > 0 )){
newoptionX = "<%= brnDetails.getE01DPPP18().trim()%>";
newoptionX = newoptionX + " - " ;
newoptionX = newoptionX + "<%= brnDetails.getE01DPPD18().trim()%>";
newoption = new Option(newoptionX, selectedValue, false, false);
document.forms[0].choiceBox.options[i] = newoption;
i++ ;
}

selectedValue = "<%= brnDetails.getE01DPPP19().trim() %>";
if ((selectedValue != null ) && (formatFloat(selectedValue) > 0 )){
newoptionX = "<%= brnDetails.getE01DPPP19().trim()%>";
newoptionX = newoptionX + " - " ;
newoptionX = newoptionX + "<%= brnDetails.getE01DPPD19().trim()%>";
newoption = new Option(newoptionX, selectedValue, false, false);
document.forms[0].choiceBox.options[i] = newoption;
i++ ;
}

selectedValue = "<%= brnDetails.getE01DPPP20().trim() %>";
if ((selectedValue != null ) && (formatFloat(selectedValue) > 0 )){
newoptionX = "<%= brnDetails.getE01DPPP20().trim()%>";
newoptionX = newoptionX + " - " ;
newoptionX = newoptionX + "<%= brnDetails.getE01DPPD20().trim()%>";
newoption = new Option(newoptionX, selectedValue, false, false);
document.forms[0].choiceBox.options[i] = newoption;
i++ ;
}

selectedValue = "<%= brnDetails.getE01DPPP21().trim() %>";
if ((selectedValue != null ) && (formatFloat(selectedValue) > 0 )){
newoptionX = "<%= brnDetails.getE01DPPP21().trim()%>";
newoptionX = newoptionX + " - " ;
newoptionX = newoptionX + "<%= brnDetails.getE01DPPD21().trim()%>";
newoption = new Option(newoptionX, selectedValue, false, false);
document.forms[0].choiceBox.options[i] = newoption;
i++ ;
}

selectedValue = "<%= brnDetails.getE01DPPP22().trim() %>";
if ((selectedValue != null ) && (formatFloat(selectedValue) > 0 )){
newoptionX = "<%= brnDetails.getE01DPPP22().trim()%>";
newoptionX = newoptionX + " - " ;
newoptionX = newoptionX + "<%= brnDetails.getE01DPPD22().trim()%>";
newoption = new Option(newoptionX, selectedValue, false, false);
document.forms[0].choiceBox.options[i] = newoption;
i++ ;
}

selectedValue = "<%= brnDetails.getE01DPPP23().trim() %>";
if ((selectedValue != null ) && (formatFloat(selectedValue) > 0 )){
newoptionX = "<%= brnDetails.getE01DPPP23().trim()%>";
newoptionX = newoptionX + " - " ;
newoptionX = newoptionX + "<%= brnDetails.getE01DPPD23().trim()%>";
newoption = new Option(newoptionX, selectedValue, false, false);
document.forms[0].choiceBox.options[i] = newoption;
i++ ;
}

selectedValue = "<%= brnDetails.getE01DPPP24().trim() %>";
if ((selectedValue != null ) && (formatFloat(selectedValue) > 0 )){
newoptionX = "<%= brnDetails.getE01DPPP24().trim()%>";
newoptionX = newoptionX + " - " ;
newoptionX = newoptionX + "<%= brnDetails.getE01DPPD24().trim()%>";
newoption = new Option(newoptionX, selectedValue, false, false);
document.forms[0].choiceBox.options[i] = newoption;
i++ ;
}

}

function moveOver()  
{
var boxLength = document.forms[0].choiceBox.length;
var selectedItem = document.forms[0].available.selectedIndex;
var selectedText = document.forms[0].available.options[selectedItem].text;
var selectedValue = document.forms[0].available.options[selectedItem].value;
var i;
var isNew = true;
if (boxLength != 0) {
for (i = 0; i < boxLength; i++) {
thisitem = document.forms[0].choiceBox.options[i].text;
if (thisitem == selectedText) {
isNew = false;
break;
      }
   }
} 
if (boxLength<24) {

if (isNew) {
newoption = new Option(selectedText, selectedValue, false, false);
document.forms[0].choiceBox.options[boxLength] = newoption;
}
document.forms[0].available.selectedIndex=-1;

} else{
	if (document.forms[0].LAN.value == 'S') {
		alert("Seleccione maximo 24 códigos de fórmulas");
	} else {
		alert("Select to 24 formula codes");
	}

}
}


function removeMe() {
var boxLength = document.forms[0].choiceBox.length;
arrSelected = new Array();
var count = 0;
for (i = 0; i < boxLength; i++) {
if (document.forms[0].choiceBox.options[i].selected) {
arrSelected[count] = document.forms[0].choiceBox.options[i].value;
}
count++;
}
var x;
for (i = 0; i < boxLength; i++) {
for (x = 0; x < arrSelected.length; x++) {
if (document.forms[0].choiceBox.options[i].value == arrSelected[x]) {
document.forms[0].choiceBox.options[i] = null;
   }
}
boxLength = document.forms[0].choiceBox.length;
   }
}


function saveMe() {
var strValues = "";
var boxLength = document.forms[0].choiceBox.length;
var count = 0;
if (boxLength != 0) {
for (i = 0; i < boxLength; i++) {
if (count == 0) {
strValues = document.forms[0].choiceBox.options[i].value;
}
else {
strValues = strValues + "," + document.forms[0].choiceBox.options[i].value;
}
count++;
   }
}
if (strValues.length == 0) {
alert("You have not made any selections");
}
else {
alert("Here are the values you've selected:\r\n" + strValues);
   }
}


function moveto400() {
var boxLength = document.forms[0].choiceBox.length;

if (0 < boxLength) {
if (document.forms[0].choiceBox.options[0].value !=null) {
document.forms[0].E01DPPP01.value= document.forms[0].choiceBox.options[0].value;
}
}

if (1 < boxLength) {
if (document.forms[0].choiceBox.options[1].value !=null) {
document.forms[0].E01DPPP02.value=document.forms[0].choiceBox.options[1].value;
}
}

if (2 < boxLength) {
if (document.forms[0].choiceBox.options[2].value !=null) {
document.forms[0].E01DPPP03.value=document.forms[0].choiceBox.options[2].value;
}
}

if (3 < boxLength) {
if (document.forms[0].choiceBox.options[3].value !=null) {
document.forms[0].E01DPPP04.value=document.forms[0].choiceBox.options[3].value;
}
}

if (4 < boxLength) {
if (document.forms[0].choiceBox.options[4].value !=null) {
document.forms[0].E01DPPP05.value=document.forms[0].choiceBox.options[4].value;
}
}

if (5 < boxLength) {
if (document.forms[0].choiceBox.options[5].value !=null) {
document.forms[0].E01DPPP06.value=document.forms[0].choiceBox.options[5].value;
}
}

if (6 < boxLength) {
if (document.forms[0].choiceBox.options[6].value !=null) {
document.forms[0].E01DPPP07.value=document.forms[0].choiceBox.options[6].value;
}
}

if (7 < boxLength) {
if (document.forms[0].choiceBox.options[7].value !=null) {
document.forms[0].E01DPPP08.value=document.forms[0].choiceBox.options[7].value;
}
}

if (8 < boxLength) {
if (document.forms[0].choiceBox.options[8].value !=null) {
document.forms[0].E01DPPP09.value=document.forms[0].choiceBox.options[8].value;
}
}

if (9 < boxLength) {
if (document.forms[0].choiceBox.options[9].value !=null) {
document.forms[0].E01DPPP10.value=document.forms[0].choiceBox.options[9].value;
}
}

if (10 < boxLength) {
if (document.forms[0].choiceBox.options[10].value !=null) {
document.forms[0].E01DPPP11.value=document.forms[0].choiceBox.options[10].value;
}
}

if (11 < boxLength) {
if (document.forms[0].choiceBox.options[11].value !=null) {
document.forms[0].E01DPPP12.value=document.forms[0].choiceBox.options[11].value;
}
}

if (12 < boxLength) {
if (document.forms[0].choiceBox.options[12].value !=null) {
document.forms[0].E01DPPP13.value=document.forms[0].choiceBox.options[12].value;
}
}

if (13 < boxLength) {
if (document.forms[0].choiceBox.options[13].value !=null) {
document.forms[0].E01DPPP14.value=document.forms[0].choiceBox.options[13].value;
}
}

if (14 < boxLength) {
if (document.forms[0].choiceBox.options[14].value !=null) {
document.forms[0].E01DPPP15.value=document.forms[0].choiceBox.options[14].value;
}
}

if (15 < boxLength) {
if (document.forms[0].choiceBox.options[15].value !=null) {
document.forms[0].E01DPPP16.value=document.forms[0].choiceBox.options[15].value;
}
}

if (16 < boxLength) {
if (document.forms[0].choiceBox.options[16].value !=null) {
document.forms[0].E01DPPP17.value=document.forms[0].choiceBox.options[16].value;
}
}

if (17 < boxLength) {
if (document.forms[0].choiceBox.options[17].value !=null) {
document.forms[0].E01DPPP18.value=document.forms[0].choiceBox.options[17].value;
}
}

if (18 < boxLength) {
if (document.forms[0].choiceBox.options[18].value !=null) {
document.forms[0].E01DPPP19.value=document.forms[0].choiceBox.options[18].value;
}
}

if (19 < boxLength) {
if (document.forms[0].choiceBox.options[19].value !=null) {
document.forms[0].E01DPPP20.value=document.forms[0].choiceBox.options[19].value;
}
}

if (20 < boxLength) {
if (document.forms[0].choiceBox.options[20].value !=null) {
document.forms[0].E01DPPP21.value=document.forms[0].choiceBox.options[20].value;
}
}

if (21 < boxLength) {
if (document.forms[0].choiceBox.options[21].value !=null) {
document.forms[0].E01DPPP22.value=document.forms[0].choiceBox.options[21].value;
}
}

if (22 < boxLength) {
if (document.forms[0].choiceBox.options[22].value !=null) {
document.forms[0].E01DPPP23.value=document.forms[0].choiceBox.options[22].value;
}
}

if (23 < boxLength) {
if (document.forms[0].choiceBox.options[23].value !=null) {
document.forms[0].E01DPPP24.value=document.forms[0].choiceBox.options[23].value;
}
}
}

// 





function goConfirm(opt) {

	moveto400();
    //saveMe();
	var op = opt;  	  
	var msg1 = "Esta seguro que desea ";
	var msg2 = " el registro seleccionado";
	document.forms[0].opt.value = op;
		switch (op) {
	case  "1":  //ok = confirm(msg1 + " Ingresar " + msg2);
				//document.forms[0].SCREEN.value="600";
				//alert(document.forms[0].SCREEN.value);
	 break; 
	case  "2":  //ok = confirm(msg1 + " Actualizar " + msg2);
	            //document.forms[0].SCREEN.value="600";
	 break;   
	case  "3":  ok = confirm(msg1 + " Eliminar " + msg2);
	            document.forms[0].SCREEN.value="630";
	 break;
	//default : alert("something is wrong"); 
	};
//	alert(document.forms[0].SCREEN.value);	
	document.forms[0].submit();		  	       	       
}

function goCancel(fmt) {

document.forms[0].SCREEN.value="200";
document.forms[0].submit(); 
	  		  
}

</script>

</head>
<body  onload=javascript:initOver()>

<script language="Javascript1.1" src="<%=request.getContextPath()%>/pages/s/javascripts/eIBS.jsp"> </SCRIPT>
<script language="Javascript1.1" src="<%=request.getContextPath()%>/pages/s/javascripts/optMenu.jsp"> </SCRIPT>

<% 
    if ( !error.getERRNUM().equals("0")  ) {
        out.println("<SCRIPT Language=\"Javascript\">");
        error.setERRNUM("0");
        out.println("       showErrors()");
        out.println("</SCRIPT>");
    }
    
%>

<H3 align="center">Mantenimiento Ruta y Detalle<img src="<%=request.getContextPath()%>/images/e_ibs.gif" align="left" name="EIBS_GIF" ALT="path_maintenance.jsp, EDP0708"></H3>
<P align="center"><INPUT type="text" name="DSC" size="35" maxlength="35"
	value="<%= userPO.getHeader10().trim()%>" readonly></P>
<form method="post" action="<%=request.getContextPath()%>/servlet/datapro.eibs.creditproposal.JSEDP0708" >
  <INPUT TYPE=HIDDEN NAME="SCREEN" VALUE="600">
  <input type=HIDDEN name="opt">
  <input type=HIDDEN name="E01DPPP01">
  <input type=HIDDEN name="E01DPPP02">
  <input type=HIDDEN name="E01DPPP03">
  <input type=HIDDEN name="E01DPPP04">
  <input type=HIDDEN name="E01DPPP05">
  <input type=HIDDEN name="E01DPPP06">
  <input type=HIDDEN name="E01DPPP07">
  <input type=HIDDEN name="E01DPPP08">
  <input type=HIDDEN name="E01DPPP09">
  <input type=HIDDEN name="E01DPPP10">
  <input type=HIDDEN name="E01DPPP11">
  <input type=HIDDEN name="E01DPPP12">
  <input type=HIDDEN name="E01DPPP13">
  <input type=HIDDEN name="E01DPPP14">
  <input type=HIDDEN name="E01DPPP15">
  <input type=HIDDEN name="E01DPPP16">
  <input type=HIDDEN name="E01DPPP17">
  <input type=HIDDEN name="E01DPPP18">
  <input type=HIDDEN name="E01DPPP19">
  <input type=HIDDEN name="E01DPPP20">
  <input type=HIDDEN name="E01DPPP21">
  <input type=HIDDEN name="E01DPPP22">
  <input type=HIDDEN name="E01DPPP23">
  <input type=HIDDEN name="E01DPPP24">
  <input type=HIDDEN name="E01DPPD01">
  <input type=HIDDEN name="E01DPPD02">
  <input type=HIDDEN name="E01DPPD03">
  <input type=HIDDEN name="E01DPPD04">
  <input type=HIDDEN name="E01DPPD05">
  <input type=HIDDEN name="E01DPPD06">
  <input type=HIDDEN name="E01DPPD07">
  <input type=HIDDEN name="E01DPPD08">
  <input type=HIDDEN name="E01DPPD09">
  <input type=HIDDEN name="E01DPPD10">
  <input type=HIDDEN name="E01DPPD11">
  <input type=HIDDEN name="E01DPPD12">
  <input type=HIDDEN name="E01DPPD13">
  <input type=HIDDEN name="E01DPPD14">
  <input type=HIDDEN name="E01DPPD15">
  <input type=HIDDEN name="E01DPPD16">
  <input type=HIDDEN name="E01DPPD17">
  <input type=HIDDEN name="E01DPPD18">
  <input type=HIDDEN name="E01DPPD19">
  <input type=HIDDEN name="E01DPPD20">
  <input type=HIDDEN name="E01DPPD21">
  <input type=HIDDEN name="E01DPPD22">
  <input type=HIDDEN name="E01DPPD23">
  <input type=HIDDEN name="E01DPPD24">
  <INPUT TYPE=HIDDEN NAME="LAN" value="<%= brnDetails.getE01CNTLAN().trim()%>">
  



<h4>  
      Detalle Ruta a 
      <% if(userPO.getHeader16().equals("1")) {out.print(" Ingresar");} %>
      <% if(userPO.getHeader16().equals("2")) {out.print(" Modificar");} %>
      <% if(userPO.getHeader16().equals("5")) {out.print(" Consultar");} %>
      <% if(userPO.getHeader16().equals("3")) {out.print(" Eliminar");} %>
  </h4> 
  
  <table  class="tableinfo">
    <tr bordercolor="#FFFFFF"> 
      <td nowrap> 
		
		<TABLE id="tbenter" align="center" style="width: 100%" border="0">
							<tr id="trdark">
								<td	nowrap width="33%">
									<div align="right">Código Ruta:</div>
								</td>
								<td nowrap align="left" width="67%">
									<INPUT type="text" 
									name="E01DPPRUT" size="3" maxlength="4" 
									readonly
									value="<%= userPO.getHeader9().trim()%>"> 
									<INPUT type="text" 
									name="E01DPPRUD" size="30" maxlength="30" 
									readonly 
									value="<%= userPO.getHeader10().trim()%>">
									<%  if(!userPO.getHeader17().equals("1")){ %>
									<A href="javascript:GetCodeDescCNOFC('E01DPPRUT','E01DPPRUD','48')">
									<IMG src="<%=request.getContextPath()%>/images/1b.gif" alt="Ayuda" align="bottom" border="0"></A>
									<%}; %>
								</td>
							</tr>

							<TR id="trclear">
								<td nowrap width="33%">
									<DIV align="right">Secuencia Actividad:</DIV>
								</TD>
								<td nowrap align="left" width="67%">
									<INPUT type="text" 
									name="E01DPPSEC" size="18" maxlength="16" 
									onkeypress="enterInteger()" 
									value="<%= brnDetails.getE01DPPSEC().trim()%>" 
									<%  if(userPO.getHeader17().equals("1")){out.print("readonly");} %>
									>
								</td>
							</tr>

							<tr id="trdark">
								<td nowrap width="33%">
								<DIV align="right">Codigo Actividad</DIV>
								</td>
								<td nowrap align="left" width="67%">
									<INPUT type="text" name="E01DPPACT" size="3" maxlength="4" 
									value="<%= brnDetails.getE01DPPACT().trim()%>" 
									<% if(userPO.getHeader18().equals("1")){out.print("readonly");} %>>
									<INPUT type="text" name="E01DPPACD" size="30" maxlength="30" readonly value="<%= brnDetails.getE01DPPACD().trim()%>">
									<%  if(!userPO.getHeader16().equals("3")){ %>
									<A href="javascript:GetCodeDescCNOFC('E01DPPACT','E01DPPACD','P2')">
									<IMG src="<%=request.getContextPath()%>/images/1b.gif" alt="Ayuda" align="absbottom" border="0"></A>
									<% } %>
								</td>
							</tr>

							<TR id="trclear">
								<td nowrap width="33%">
								<DIV align="right">Codigo Area/Depto. </DIV>
								</TD>
								<td nowrap align="left" width="67%">
									<INPUT type="text" name="E01DPPTAR" size="3" maxlength="4" value="<%= brnDetails.getE01DPPTAR().trim()%>" <% if(userPO.getHeader18().equals("1")){out.print("readonly");} %>>
									<INPUT type="text" name="E01DPPTAD" size="30" maxlength="30" readonly value="<%= brnDetails.getE01DPPTAD().trim()%>">
									<%  if(!userPO.getHeader16().equals("3")){ %>
									<A href="javascript:GetCodeDescCNOFC('E01DPPTAR','E01DPPTAD','A4')">
									<IMG src="<%=request.getContextPath()%>/images/1b.gif" alt="Ayuda" align="bottom" border="0">
									</A>
									<% } %>
								</td>
							</tr>
<%--
							<tr id="trdark">
								<td nowrap width="33%">
								<DIV align="right">Tipo Acceso :</DIV>
								</td>
								<td nowrap align="left" width="67%">
								<SELECT name="E01DPPTCC" 
								<% if(userPO.getHeader16().equals("5") || userPO.getHeader16().equals("3")){out.print("DISABLED");} %>>								
									<option value="1" 
									   <%if (brnDetails.getE01DPPTCC().equals("1")) { out.print("selected"); }%>>Sistema Ejecutivo</option>
									<option value="2"
										<%if (brnDetails.getE01DPPTCC().equals("2")) { out.print("selected"); }%>>Banca Especializada</option>
									<option value="5" 
										<%if (brnDetails.getE01DPPTCC().equals("5")) { out.print("selected"); }%>>Lista de Acceso</option>												
									<option value="3" 
										<%if (brnDetails.getE01DPPTCC().equals("3")) { out.print("selected"); }%>>Lista de Acceso(Post Comite)</option>	
									<option value="4" 
										<%if (brnDetails.getE01DPPTCC().equals("4")) { out.print("selected"); }%>>EXCLUSIVO</option>
									<option value="6" 
										<%if (brnDetails.getE01DPPTCC().equals("6")) { out.print("selected"); }%>>Proponente</option>	
									<option value="7" 
										<%if (brnDetails.getE01DPPTCC().equals("7")) { out.print("selected"); }%>>Historico</option>		
								</SELECT>
								</td>
							</tr>
--%>
							<tr id="trclear">
								<td nowrap width="33%">
								<DIV align="right">Tipo Actividad :</DIV>
								</td>
								<td nowrap align="left" width="67%">
								<SELECT name="E01DPPTAC" 
								<% if(userPO.getHeader16().equals("5") || userPO.getHeader16().equals("3")){out.print("DISABLED");} %>>								
									<option value="0001" 
									   <%if (brnDetails.getE01DPPTAC().equals("0001")) { out.print("selected"); }%>>Procesar Actividad</option>
									<option value="0002"
										<%if (brnDetails.getE01DPPTAC().equals("0002")) { out.print("selected"); }%>>Bifurcar</option>
									<option value="0003"
										<%if (brnDetails.getE01DPPTAC().equals("0003")) { out.print("selected"); }%>>Flujo Finaliza</option>
									<option value="0004"
										<%if (brnDetails.getE01DPPTAC().equals("0004")) { out.print("selected"); }%>>Iniciar</option>	
									<option value="0005"
										<%if (brnDetails.getE01DPPTAC().equals("0005")) { out.print("selected"); }%>>Finalizar</option>
								</SELECT>
								</td>
							</tr>

							<tr id="trdark">
								<td nowrap width="33%">
								<DIV align="right">Aprobar/Negar/Diferir:</DIV>
								</td>
								<td nowrap align="left" width="67%">
								<SELECT name="E01DPPSAC" 
								<% if(userPO.getHeader16().equals("5") || userPO.getHeader16().equals("3")){out.print("DISABLED");} %>>
									<option value="" 
									   <%if (brnDetails.getE01DPPSAC().equals("")) { out.print("selected"); }%>></option>							
									<option value="0001" 
									   <%if (brnDetails.getE01DPPSAC().equals("0001")) { out.print("selected"); }%>>Aprobar</option>
									<option value="0002"
										<%if (brnDetails.getE01DPPSAC().equals("0002")) { out.print("selected"); }%>>Negar</option>
									<option value="0003"
										<%if (brnDetails.getE01DPPSAC().equals("0003")) { out.print("selected"); }%>>Diferir</option>	
								</SELECT>
								Monto Máximo Autorizado para Aprobar:
				 <INPUT type="text" name="E01DPPMAA" size="20" maxlength="20" value="<%= brnDetails.getE01DPPMAA().trim()%>" 
				 								<% if(userPO.getHeader16().equals("5") || userPO.getHeader16().equals("3")){out.print("readonly");} %> onkeypress=enterDecimal() >
								</td>
							</tr>

							<tr id="trclear">
								<td nowrap width="33%">
								<DIV align="right">Tipo Proceso :</DIV>
								</td>
								<td nowrap align="left" width="67%">
								<SELECT name="E01DPPPRO" 
								<% if(userPO.getHeader16().equals("5") || userPO.getHeader16().equals("3")){out.print("DISABLED");} %>>								
									<option value="1" 
									   <%if (brnDetails.getE01DPPPRO().equals("1")) { out.print("selected"); }%>>Interactivo</option>
									<option value="2"
										<%if (brnDetails.getE01DPPPRO().equals("2")) { out.print("selected"); }%>>Batch</option>
									<option value="3" 
										<%if (brnDetails.getE01DPPPRO().equals("3")) { out.print("selected"); }%>>Exclusivo</option>	
								</SELECT>
								</td>
							</tr>
								
							<tr id="trdark">
								<td	nowrap width="33%">
									<div align="right">Estado de Propuesta:</div>
								</td>
								<td nowrap align="left" width="67%">
									<INPUT type="text" name="E01DPPEST" size="3" maxlength="4" 
									readonly 
									value="<%= brnDetails.getE01DPPEST().trim()%>"> 
									
									<INPUT type="text" 
									name="E01DPPTED" size="30" maxlength="30" 
									readonly 
									value="<%= brnDetails.getE01DPPTED().trim()%>">
									<A href="javascript:GetCodeDescCNOFC('E01DPPEST','E01DPPTED','37')">
									<IMG src="<%=request.getContextPath()%>/images/1b.gif" alt="Ayuda" align="bottom" border="0"></A>
								</td>
							</tr>
				
							<tr id="trclear">
								<td	nowrap width="33%">
   									<DIV align="right">Comentario de Usuario</DIV>
								</td>
								
								<td nowrap align="left" width="67%">
              						<SELECT name="E01DPPCOM"
	    								<% if(userPO.getHeader16().equals("5") || userPO.getHeader16().equals("3")){out.print("DISABLED");} %>>								
										<OPTION value=""
											<%if (brnDetails.getE01DPPCOM().equals("")) { out.print("selected"); }%>></OPTION>
										<OPTION value="1"
											<%if (brnDetails.getE01DPPCOM().equals("1")) { out.print("selected"); }%>>Alta Prioridad</OPTION>
										<OPTION value="2" 
											<%if (brnDetails.getE01DPPCOM().equals("2")) { out.print("selected"); }%>>Alta Media Prioriodad</OPTION>
										<OPTION value="3" 
											<%if (brnDetails.getE01DPPCOM().equals("3")) { out.print("selected"); }%>>Media Prioridad</OPTION>
										<OPTION value="4" 
											<%if (brnDetails.getE01DPPCOM().equals("4")) { out.print("selected"); }%>>Media Baja Prioridad</OPTION>	
										<OPTION value="5" 
											<%if (brnDetails.getE01DPPCOM().equals("5")) { out.print("selected"); }%>>Baja Prioridad</OPTION>			
									</SELECT>     						
								</td>
							</tr>
				
							<tr id="trdark">
								<td	nowrap width="33%">
   									<DIV align="right">Interface IBS: </DIV>
								</td>
								
								<td nowrap align="left" width="67%">
  
									<SELECT name="E01DPPPTR"
	    								<% if(userPO.getHeader16().equals("5") || userPO.getHeader16().equals("3")){out.print("DISABLED");} %>>								
										<OPTION value=""
											<%if (brnDetails.getE01DPPPTR().equals("")) { out.print("selected"); }%>></OPTION>
										<OPTION value="1"
											<%if (brnDetails.getE01DPPPTR().equals("1")) { out.print("selected"); }%>>Punto Retorno</OPTION>
										<OPTION value="2" 
											<%if (brnDetails.getE01DPPPTR().equals("2")) { out.print("selected"); }%>>IBS</OPTION>
										<OPTION value="3" 
											<%if (brnDetails.getE01DPPPTR().equals("3")) { out.print("selected"); }%>>Genera Numeracion</OPTION>	
										</SELECT>
									</td>
								
							</tr>
<%--				
							<tr id="trclear">
								<td	nowrap width="33%">
   									<DIV align="right">Tipo de Decision: </DIV>
								</td>
								
								<td nowrap align="left" width="67%">
  
									<SELECT name="E01DPPFG2"
	    								<% if(userPO.getHeader16().equals("5") || userPO.getHeader16().equals("3")){out.print("DISABLED");} %>>								
										<OPTION value="" 
											<%if (brnDetails.getE01DPPFG2().equals("")) { out.print("selected"); }%>></OPTION>
										<OPTION value="0"
											<%if (brnDetails.getE01DPPFG2().equals("0")) { out.print("selected"); }%>>No Aplica</OPTION>
										<OPTION value="1" 
											<%if (brnDetails.getE01DPPFG2().equals("1")) { out.print("selected"); }%>>Junta</OPTION>
										<OPTION value="2" 
											<%if (brnDetails.getE01DPPFG2().equals("2")) { out.print("selected"); }%>>Comite</OPTION>	
										</SELECT>
									</td>
							</tr>
--%>
							<tr id="trclear">
								<td nowrap width="33%">
								<DIV align="right">Control Recaudos:</DIV>
								</td>
								<td nowrap align="left" width="67%">
								<SELECT name="E01DPPFG3"
	    								<% if(userPO.getHeader16().equals("5") || userPO.getHeader16().equals("3")){out.print("DISABLED");} %>>	
										<OPTION value="" 
											<%if (brnDetails.getE01DPPFG3().equals("")) { out.print("selected"); }%>></OPTION>
										<OPTION value="1"
											<%if (brnDetails.getE01DPPFG3().equals("1")) { out.print("selected"); }%>>No Bloquea</OPTION>
										</SELECT>
								</td>
							</tr>

							<tr id="trdark">
								<td nowrap width="33%">
								<DIV align="right">Permitir  modificar en esta actividad:</DIV>
								</td>
								<td nowrap align="left" width="67%">
								<SELECT name="E01DPPFG2"
	    								<% if(userPO.getHeader16().equals("5") || userPO.getHeader16().equals("3")){out.print("DISABLED");} %>>	
										<OPTION value="" 
											<%if (brnDetails.getE01DPPFG2().equals("")) { out.print("selected"); }%>></OPTION>
										<OPTION value="1"
											<%if (brnDetails.getE01DPPFG2().equals("1")) { out.print("selected"); }%>>Si</OPTION>
										<OPTION value="0"
											<%if (brnDetails.getE01DPPFG2().equals("0")) { out.print("selected"); }%>>No</OPTION>
										</SELECT>
								</td>
							</tr>


							<TR id="trclear">
								<td nowrap width="33%">
									<DIV align="right">Dias para Alertar:</DIV>
								</TD>
								<td nowrap align="left" width="67%">
									<INPUT type="text" 
									name="E01DPPDAL" size="4" maxlength="3" 
									onkeypress="enterInteger()" 
									value="<%= brnDetails.getE01DPPDAL().trim()%>" 
									<% if(userPO.getHeader16().equals("5") || userPO.getHeader16().equals("3")){out.print("readonly");} %> %>
									>
									Horas:
									<INPUT type="text" 
									name="E01DPPHAL" size="5" maxlength="4" 
									onkeypress="enterInteger()" 
									value="<%= brnDetails.getE01DPPHAL().trim()%>" 
									<% if(userPO.getHeader16().equals("5") || userPO.getHeader16().equals("3")){out.print("readonly");} %>
									>
								</td>
							</tr>

		</TABLE>
		</td>
    </tr>
  </table>



<table  class="tableinfo">
    <tr bordercolor="#FFFFFF"> 
      <td nowrap> 

		<TABLE id="tbenter" align="center" style="width: 100%" border="0">
				
							<TR id="trclear">
								<TD nowrap width="40%">
									<DIV align="left">Actividades</DIV>
									<select name="available" size=10" 
									<% if( userPO.getHeader16().equals("5") || userPO.getHeader16().equals("3")) {out.print("DISABLED");} %>>
									<%
                					optLP4.initRow();
                					int j=1;
                					while (optLP4.getNextRow()) {
                    					if (optLP4.getFlag().equals("")) {
                    						out.println(optLP4.getRecord());
                    						j++;
                    						}        
                						}                 
    								%>
									</SELECT>	
								</TD>
								<TD nowrap width="20%">
										<input type="button" value="Seleccionar" onclick="moveOver();" 
										<% if(userPO.getHeader16().equals("5") || userPO.getHeader16().equals("3")){out.print("DISABLED");} %>>								
										<BR>								
										<input type="button" value=" Remover   " onclick="removeMe();" 
										<% if(userPO.getHeader16().equals("5") || userPO.getHeader16().equals("3")){out.print("DISABLED");} %>>								
										<BR>
								</TD>
								<TD nowrap width="40%">
										<DIV align="left">Proxima Actividad(es)</DIV>
								   		<select multiple name="choiceBox"  style="width: 250" size="10" >
										</select>
								</TD>
							</tr>
		</TABLE>
		</td>
    </tr>
</table>
  
<div align="center"> 
       <input id="EIBSBTN" type="button" name="Submit" value="Enviar" onclick="goConfirm('<%= userPO.getHeader16() %>')"
         <% if(userPO.getHeader16().equals("5")){out.print("DISABLED");} %>>								
       <INPUT id="EIBSBTN" type="button" name="Cancel" value="Cancelar" onclick="goCancel('<%= userPO.getHeader9().trim() %>')">
</div>
      <script language="JavaScript">
	  </script>
</form>
</body>
</html>