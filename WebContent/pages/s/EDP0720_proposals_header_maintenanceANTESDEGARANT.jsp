<%@ page import ="datapro.eibs.master.Util" %>
<html>
<head>
<title>Propuesta de Credito</title>
<META HTTP-EQUIV="Content-Type" CONTENT="text/html; charset=ISO-8859-1">
<link Href="<%=request.getContextPath()%>/pages/style.css" rel="stylesheet">

<jsp:useBean id="brnDetails" class="datapro.eibs.beans.EDP072001Message"  scope="session" />
<jsp:useBean id="EDP072101Help" class="datapro.eibs.beans.JBObjList" scope="session" />

<jsp:useBean id="msgRT" class="datapro.eibs.beans.EDP072001Message"  scope="session" />
<jsp:useBean id="msgRTC" class="datapro.eibs.beans.EDP072201Message"  scope="session" />
<jsp:useBean id= "error" class= "datapro.eibs.beans.ELEERRMessage"  scope="session" />

<jsp:useBean id= "optLP4" class= "datapro.eibs.beans.JBList"  scope="session" />
<jsp:useBean id= "optCN19" class= "datapro.eibs.beans.JBList"  scope="session" />
<jsp:useBean id= "optCN30" class= "datapro.eibs.beans.JBList"  scope="session" />
<jsp:useBean id= "optCN38" class= "datapro.eibs.beans.JBList"  scope="session" />

<jsp:useBean id= "userPO" class= "datapro.eibs.beans.UserPos"  scope="session" />
<jsp:useBean id= "currUser" class= "datapro.eibs.beans.ESS0030DSMessage"  scope="session" />
<jsp:useBean id= "optList2"  class= "datapro.eibs.beans.JBList"  scope="session" />
<jsp:useBean id= "optList4"  class= "datapro.eibs.beans.JBList"  scope="session" />
<jsp:useBean id= "optList11"  class= "datapro.eibs.beans.JBList"  scope="session" />

<jsp:useBean id="gaCodeList" class="datapro.eibs.beans.JBListRec" scope="session" />
<jsp:useBean id="gaList" class="datapro.eibs.beans.JBListRec" scope="session" />

<script language="Javascript1.1" src="<%=request.getContextPath()%>/pages/s/javascripts/eIBS.jsp"> </SCRIPT>
<script language="Javascript1.1" src="<%=request.getContextPath()%>/pages/s/javascripts/optMenu.jsp"> </SCRIPT>
 
 

<SCRIPT Language="Javascript">
	builtNewMenu(pc_optionHeader);

</SCRIPT>

<%
if (!error.getERRNUM().equals("0")) {
      error.setERRNUM("0");
%>
	<SCRIPT Language="Javascript">
		showErrors();
		</SCRIPT>
<%}%>


<SCRIPT Language="Javascript">

function callCust() {
var customer = "<%=msgRT.getE01CUSCUN().trim()%>"    
    	page = "<%=request.getContextPath()%>/servlet/datapro.eibs.client.JSESD0080?SCREEN=600&E01CUN="+customer;
    	CenterNamedWindow(page,'Information',650,500,2);
}

function callPosition() {
var customer = "<%=msgRT.getE01CUSCUN().trim()%>"    
    	page = "<%=request.getContextPath()%>/servlet/datapro.eibs.client.JSECIF010?SCREEN=5&CUSTOMER="+customer;
    	CenterNamedWindow(page,'Information',650,500,2);
}

function callGuard() {

    	page = "<%=request.getContextPath()%>/servlet/datapro.eibs.creditproposal.JSEDP0725?SCREEN=200&NPR="+ <%=msgRT.getE01PLPNPR().trim()%>
    														+"&CUN="
        													+ <%=msgRT.getE01CUSCUN().trim()%>
        													+"&NAM="
        													+ "<%=msgRT.getE01CUSNA1().trim()%>"
        													+"&opt=2";
    	CenterNamedWindow(page,'Information',650,500,2);
}


function callInfF() {
    	page = "<%=request.getContextPath()%>/servlet/datapro.eibs.creditproposal.JSEDP0730?SCREEN=100&CUSTOMER="+ <%=msgRT.getE01CUSCUN().trim()%>;
    	CenterNamedWindow(page,'Information',700,600,2);
}

function callCupo() {
    	page = "<%=request.getContextPath()%>/servlet/datapro.eibs.credit.JSELN0110?SCREEN=1";
    	CenterNamedWindow(page,'Information',700,600,2);
}


function callSegP() {
    	page = "<%=request.getContextPath()%>/servlet/datapro.eibs.creditproposal.JSEDP0750?SCREEN=200&RUT="+ <%=msgRT.getE01PLPNPR().trim()%>
    														+"&DSC= ";
    	CenterNamedWindow(page,'Information',700,600,2);
}

function callPosition() {
var customer = "<%=msgRT.getE01CUSCUN().trim()%>"    
    	page = "<%=request.getContextPath()%>/servlet/datapro.eibs.client.JSECIF010?SCREEN=5&CUSTOMER="+customer;
    	CenterNamedWindow(page,'Information',650,500,2);
}


function init()
{
// texto comentarios 
document.forms[0].E02DPAC99.value = "<%=msgRTC.getE02DPAC99().trim()%>";
document.forms[0].E01DPSCOM.value = "<%=msgRT.getE01DPSCOM().trim()%>";

// asume siguiente actividad cuando hay una sola actividad para seleccionar si no es apertura
if (document.forms[0].available != null) {
var boxLength = document.forms[0].available.length;
if (boxLength == 1) {
// if (document.forms[0].available.options[1].text == "") {
var selectedText = document.forms[0].available.options[0].text;
var selectedValue = document.forms[0].available.options[0].value;
   	document.forms[0].E01DPPPXX.value = selectedValue;
   	document.forms[0].E01DPPDXX.value = selectedText;

}
} else {
	document.forms[0].E01DPPPXX.value = "0001";
	document.forms[0].E01DPPDXX.value = "APERTURA PROPUESTA";
	document.forms[0].TASK.value = "EDP0720_proposals_header_maintenance.jsp";
}


var boxLength = document.forms[0].E01PLPBRN.length;
i = 0;
document.forms[0].E01PLPBRN.selectedIndex=0;
if (boxLength != 0) {
for (i = 0; i < boxLength; i++) {
thisvalue = document.forms[0].E01PLPBRN.options[i].value;
if (thisvalue == document.forms[0].BRN.value) {
	document.forms[0].E01PLPBRN.selectedIndex=i;
	break;
   }
}
}

var boxLength = document.forms[0].E01PLPAGD.length;
i = 0;
document.forms[0].E01PLPAGD.selectedIndex=0;
if (boxLength != 0) {
for (i = 0; i < boxLength; i++) {
thisvalue = document.forms[0].E01PLPAGD.options[i].value;
if (thisvalue == document.forms[0].AGD.value) {
	document.forms[0].E01PLPAGD.selectedIndex=i;
	break;
   }
}
}


}


function goConfirm(opt,TASK, OPECOD,PARAM) {
	document.forms[0].OPECOD.value = OPECOD;
	document.forms[0].PARAM.value = PARAM;
   	document.forms[0].TASK.value = TASK;

	var op = opt;  	  
	var msg1 = "Esta seguro que desea ";
	var msg2 = " el registro seleccionado";
	document.forms[0].opt.value = op;

	var error = 0
	var msg = ""
	var msg1 = ""
	if (document.forms[0].E01DPSCOM.value == "") { 
		msg1 = 'Especifique comentarios de '+"<%= msgRT.getE01XXXEJ1().trim()%>";
		error = 1;
		document.forms[0].E01DPSCOM.focus();
	}
	if (document.forms[0].E02DPAC99.value == "") { 
		msg = 'Especifique comentarios de Análisis de Credito';
		error = 1;
		document.forms[0].E02DPAC99.focus();
	}

	if (error  == 0){

	switch (op) {
	case  "1": 	document.forms[0].OPECOD.value = "0001";
	            document.forms[0].PARAM.value = "1-1-1";
	            document.forms[0].E01DPPPXX.value = "0001";
	            document.forms[0].E01DPPDXX.value = "APERTURA PROPUESTA";
	            document.forms[0].TASK.value = "EDP0720_proposals_header_maintenance.jsp";
	 break; 
	case  "2":  //ok = confirm(msg1 + " Actualizar " + msg2);
	            //document.forms[0].SCREEN.value="600";
	 break;   
	case  "3":  ok = confirm(msg1 + " Eliminar " + msg2);
	            document.forms[0].SCREEN.value="630";
	 break;
	};

	document.forms[0].OPECOD.value = "0004";
    document.forms[0].SCREEN.value="600";

	document.forms[0].submit();		  	       	       

	} else {
		alert(msg+"\n\n"+msg1+"\n\n");
	}
}

function goControlDoc(opt) {


	var error = 0
	var msg = ""
	var msg1 = ""
	var msg2 = ""

	if (<%=(gaCodeList.getNoResult())%> == true) {
		msg = 'Debe adicionar un producto para esta propuesta'
		error = 1;
	}

	var op = opt;  
	switch (op) {
	case  "1": 	document.forms[0].OPECOD.value = "0001";
	            document.forms[0].PARAM.value = "1-1-1";
	            document.forms[0].E01DPPPXX.value = "0001";
	            document.forms[0].E01DPPDXX.value = "APERTURA PROPUESTA";
	            document.forms[0].TASK.value = "EDP0720_proposals_header_maintenance.jsp";

	 break; 
	case  "2":  //ok = confirm(msg1 + " Actualizar " + msg2);
	            //document.forms[0].SCREEN.value="600";
	 break;   
	case  "3":  ok = confirm(msg1 + " Eliminar " + msg2);
	            document.forms[0].SCREEN.value="630";
	 break;
	case  "8": 	document.forms[0].OPECOD.value = "0001";
	            document.forms[0].PARAM.value = "1-1-1";
	            document.forms[0].E01DPPPXX.value = "0001";
	            document.forms[0].E01DPPDXX.value = "RENOVACION PROPUESTA";
	            document.forms[0].TASK.value = "EDP0720_proposals_header_maintenance.jsp";
	 break; 
	};

	if (op != "1") {
		if (document.forms[0].E02DPAC99.value == "") { 
			msg1 = 'Especifique comentarios de Análisis de Credito';
			error = 1;
			document.forms[0].E02DPAC99.focus();
		}
		if (document.forms[0].E01DPSCOM.value == "") { 
			msg2 = 'Especifique comentarios de '+"<%= msgRT.getE01XXXEJ1().trim()%>";
			error = 1;
			document.forms[0].E01DPSCOM.focus();
		}
	}

	if (error  == 0){

	document.forms[0].SCREEN.value="600";
	document.forms[0].submit(); 

	} else {
		alert(msg+"\n\n"+msg1+"\n\n"+msg2+"\n\n");
	}
}



function goCancel(fmt) {
document.forms[0].SCREEN.value="200";
document.forms[0].submit(); 
	  		  
}

function moveOver()  
{
var selectedItem = document.forms[0].available.selectedIndex;
if (selectedItem >-1) {
var selectedText = document.forms[0].available.options[selectedItem].text;
var selectedValue = document.forms[0].available.options[selectedItem].value;
   	document.forms[0].E01DPPPXX.value = selectedValue;
   	document.forms[0].E01DPPDXX.value = selectedText;
}

}

function asigDat(name) {
	var n = name;
	var f1 = "document.forms[0].E01DPDA0"+n+".value"

	switch (n) {
	case  1: 
		if (document.forms[0].E01DPUB01.checked == true) {

		document.forms[0].E01DPUB01.value = "1";
		document.forms[0].E01DPDA01.value = "<%=msgRT.getE01PLPIPD().trim()%>";
		document.forms[0].E01DPDM01.value = "<%=msgRT.getE01PLPIPM().trim()%>";
		document.forms[0].E01DPDY01.value = "<%=msgRT.getE01PLPPLP().trim()%>";
		}else{
		document.forms[0].E01DPUB01.value = "";
		document.forms[0].E01DPDA01.value = "";
		document.forms[0].E01DPDM01.value = "";
		document.forms[0].E01DPDY01.value = "";
		}
	break;
	case  2: 
		if (document.forms[0].E01DPUB02.checked == true) {
		document.forms[0].E01DPUB02.value = "1";
		document.forms[0].E01DPDA02.value = "<%=msgRT.getE01PLPIPD().trim()%>";
		document.forms[0].E01DPDM02.value = "<%=msgRT.getE01PLPIPM().trim()%>";
		document.forms[0].E01DPDY02.value = "<%=msgRT.getE01PLPPLP().trim()%>";
		}else{
		document.forms[0].E01DPUB02.value = "";
		document.forms[0].E01DPDA02.value = "";
		document.forms[0].E01DPDM02.value = "";
		document.forms[0].E01DPDY02.value = "";
		}
	break;
	case  3: 
		if (document.forms[0].E01DPUB03.checked == true) {
		document.forms[0].E01DPUB03.value = "1";
		document.forms[0].E01DPDA03.value = "<%=msgRT.getE01PLPIPD().trim()%>";
		document.forms[0].E01DPDM03.value = "<%=msgRT.getE01PLPIPM().trim()%>";
		document.forms[0].E01DPDY03.value = "<%=msgRT.getE01PLPPLP().trim()%>";
		}else{
		document.forms[0].E01DPUB03.value = "";
		document.forms[0].E01DPDA03.value = "";
		document.forms[0].E01DPDM03.value = "";
		document.forms[0].E01DPDY03.value = "";
		}
	break;
	case  4: 
		if (document.forms[0].E01DPUB04.checked == true) {
		document.forms[0].E01DPUB04.value = "1";
		document.forms[0].E01DPDA04.value = "<%=msgRT.getE01PLPIPD().trim()%>";
		document.forms[0].E01DPDM04.value = "<%=msgRT.getE01PLPIPM().trim()%>";
		document.forms[0].E01DPDY04.value = "<%=msgRT.getE01PLPPLP().trim()%>";
		}else{
		document.forms[0].E01DPUB04.value = "";
		document.forms[0].E01DPDA04.value = "";
		document.forms[0].E01DPDM04.value = "";
		document.forms[0].E01DPDY04.value = "";
		}
	break;
	case  5: 
		if (document.forms[0].E01DPUB05.checked == true) {
		document.forms[0].E01DPUB05.value = "1";
		document.forms[0].E01DPDA05.value = "<%=msgRT.getE01PLPIPD().trim()%>";
		document.forms[0].E01DPDM05.value = "<%=msgRT.getE01PLPIPM().trim()%>";
		document.forms[0].E01DPDY05.value = "<%=msgRT.getE01PLPPLP().trim()%>";
		}else{
		document.forms[0].E01DPUB05.value = "";
		document.forms[0].E01DPDA05.value = "";
		document.forms[0].E01DPDM05.value = "";
		document.forms[0].E01DPDY05.value = "";
		}
	break;
	case  6: 
		if (document.forms[0].E01DPUB06.checked == true) {
		document.forms[0].E01DPUB06.value = "1";
		document.forms[0].E01DPDA06.value = "<%=msgRT.getE01PLPIPD().trim()%>";
		document.forms[0].E01DPDM06.value = "<%=msgRT.getE01PLPIPM().trim()%>";
		document.forms[0].E01DPDY06.value = "<%=msgRT.getE01PLPPLP().trim()%>";
		}else{
		document.forms[0].E01DPUB06.value = "";
		document.forms[0].E01DPDA06.value = "";
		document.forms[0].E01DPDM06.value = "";
		document.forms[0].E01DPDY06.value = "";
		}
	break;
	case  7: 
		if (document.forms[0].E01DPUB07.checked == true) {
		document.forms[0].E01DPUB07.value = "1";
		document.forms[0].E01DPDA07.value = "<%=msgRT.getE01PLPIPD().trim()%>";
		document.forms[0].E01DPDM07.value = "<%=msgRT.getE01PLPIPM().trim()%>";
		document.forms[0].E01DPDY07.value = "<%=msgRT.getE01PLPPLP().trim()%>";
		}else{
		document.forms[0].E01DPUB07.value = "";
		document.forms[0].E01DPDA07.value = "";
		document.forms[0].E01DPDM07.value = "";
		document.forms[0].E01DPDY07.value = "";
		}
	break;
	case  8: 
		if (document.forms[0].E01DPUB08.checked == true) {
		document.forms[0].E01DPUB08.value = "1";
		document.forms[0].E01DPDA08.value = "<%=msgRT.getE01PLPIPD().trim()%>";
		document.forms[0].E01DPDM08.value = "<%=msgRT.getE01PLPIPM().trim()%>";
		document.forms[0].E01DPDY08.value = "<%=msgRT.getE01PLPPLP().trim()%>";
		}else{
		document.forms[0].E01DPUB08.value = "";
		document.forms[0].E01DPDA08.value = "";
		document.forms[0].E01DPDM08.value = "";
		document.forms[0].E01DPDY08.value = "";
		}
	break;
	case  9: 
		if (document.forms[0].E01DPUB09.checked == true) {
		document.forms[0].E01DPUB09.value = "1";
		document.forms[0].E01DPDA09.value = "<%=msgRT.getE01PLPIPD().trim()%>";
		document.forms[0].E01DPDM09.value = "<%=msgRT.getE01PLPIPM().trim()%>";
		document.forms[0].E01DPDY09.value = "<%=msgRT.getE01PLPPLP().trim()%>";
		}else{
		document.forms[0].E01DPUB09.value = "";
		document.forms[0].E01DPDA09.value = "";
		document.forms[0].E01DPDM09.value = "";
		document.forms[0].E01DPDY09.value = "";
		}
	break;
	case  10: 
		if (document.forms[0].E01DPUB10.checked == true) {
		document.forms[0].E01DPUB10.value = "1";
		document.forms[0].E01DPDA10.value = "<%=msgRT.getE01PLPIPD().trim()%>";
		document.forms[0].E01DPDM10.value = "<%=msgRT.getE01PLPIPM().trim()%>";
		document.forms[0].E01DPDY10.value = "<%=msgRT.getE01PLPPLP().trim()%>";
		}else{
		document.forms[0].E01DPUB10.value = "";
		document.forms[0].E01DPDA10.value = "";
		document.forms[0].E01DPDM10.value = "";
		document.forms[0].E01DPDY10.value = "";
		}
	break;
	case  11: 
		if (document.forms[0].E01DPUB11.checked == true) {
		document.forms[0].E01DPUB11.value = "1";
		document.forms[0].E01DPDA11.value = "<%=msgRT.getE01PLPIPD().trim()%>";
		document.forms[0].E01DPDM11.value = "<%=msgRT.getE01PLPIPM().trim()%>";
		document.forms[0].E01DPDY11.value = "<%=msgRT.getE01PLPPLP().trim()%>";
		}else{
		document.forms[0].E01DPUB11.value = "";
		document.forms[0].E01DPDA11.value = "";
		document.forms[0].E01DPDM11.value = "";
		document.forms[0].E01DPDY11.value = "";
		}
	break;
	case  12: 
		if (document.forms[0].E01DPUB12.checked == true) {
		document.forms[0].E01DPUB12.value = "1";
		document.forms[0].E01DPDA12.value = "<%=msgRT.getE01PLPIPD().trim()%>";
		document.forms[0].E01DPDM12.value = "<%=msgRT.getE01PLPIPM().trim()%>";
		document.forms[0].E01DPDY12.value = "<%=msgRT.getE01PLPPLP().trim()%>";
		}else{
		document.forms[0].E01DPUB12.value = "";
		document.forms[0].E01DPDA12.value = "";
		document.forms[0].E01DPDM12.value = "";
		document.forms[0].E01DPDY12.value = "";
		}
	break;
	case  13: 
		if (document.forms[0].E01DPUB13.checked == true) {
		document.forms[0].E01DPUB13.value = "1";
		document.forms[0].E01DPDA13.value = "<%=msgRT.getE01PLPIPD().trim()%>";
		document.forms[0].E01DPDM13.value = "<%=msgRT.getE01PLPIPM().trim()%>";
		document.forms[0].E01DPDY13.value = "<%=msgRT.getE01PLPPLP().trim()%>";
		}else{
		document.forms[0].E01DPUB13.value = "";
		document.forms[0].E01DPDA13.value = "";
		document.forms[0].E01DPDM13.value = "";
		document.forms[0].E01DPDY13.value = "";
		}
	break;
	case  14: 
		if (document.forms[0].E01DPUB14.checked == true) {
		document.forms[0].E01DPUB14.value = "1";
		document.forms[0].E01DPDA14.value = "<%=msgRT.getE01PLPIPD().trim()%>";
		document.forms[0].E01DPDM14.value = "<%=msgRT.getE01PLPIPM().trim()%>";
		document.forms[0].E01DPDY14.value = "<%=msgRT.getE01PLPPLP().trim()%>";
		}else{
		document.forms[0].E01DPUB14.value = "";
		document.forms[0].E01DPDA14.value = "";
		document.forms[0].E01DPDM14.value = "";
		document.forms[0].E01DPDY14.value = "";
		}
	break;
	case  15: 
		if (document.forms[0].E01DPUB15.checked == true) {
		document.forms[0].E01DPUB15.value = "1";
		document.forms[0].E01DPDA15.value = "<%=msgRT.getE01PLPIPD().trim()%>";
		document.forms[0].E01DPDM15.value = "<%=msgRT.getE01PLPIPM().trim()%>";
		document.forms[0].E01DPDY15.value = "<%=msgRT.getE01PLPPLP().trim()%>";
		}else{
		document.forms[0].E01DPUB15.value = "";
		document.forms[0].E01DPDA15.value = "";
		document.forms[0].E01DPDM15.value = "";
		document.forms[0].E01DPDY15.value = "";
		}
	break;
	case  16: 
		if (document.forms[0].E01DPUB16.checked == true) {
		document.forms[0].E01DPUB16.value = "1";
		document.forms[0].E01DPDA16.value = "<%=msgRT.getE01PLPIPD().trim()%>";
		document.forms[0].E01DPDM16.value = "<%=msgRT.getE01PLPIPM().trim()%>";
		document.forms[0].E01DPDY16.value = "<%=msgRT.getE01PLPPLP().trim()%>";
		}else{
		document.forms[0].E01DPUB16.value = "";
		document.forms[0].E01DPDA16.value = "";
		document.forms[0].E01DPDM16.value = "";
		document.forms[0].E01DPDY16.value = "";
		}
	break;
	case  17: 
		if (document.forms[0].E01DPUB17.value == "") {
		document.forms[0].E01DPUB17.value = "1";
		document.forms[0].E01DPDA17.value = "<%=msgRT.getE01PLPIPD().trim()%>";
		document.forms[0].E01DPDM17.value = "<%=msgRT.getE01PLPIPM().trim()%>";
		document.forms[0].E01DPDY17.value = "<%=msgRT.getE01PLPPLP().trim()%>";
		}else{
		document.forms[0].E01DPUB17.value = "";
		document.forms[0].E01DPDA17.value = "";
		document.forms[0].E01DPDM17.value = "";
		document.forms[0].E01DPDY17.value = "";
		}
	break;
	case  18: 
		if (document.forms[0].E01DPUB18.value == "") {
		document.forms[0].E01DPUB18.value = "1";
		document.forms[0].E01DPDA18.value = "<%=msgRT.getE01PLPIPD().trim()%>";
		document.forms[0].E01DPDM18.value = "<%=msgRT.getE01PLPIPM().trim()%>";
		document.forms[0].E01DPDY18.value = "<%=msgRT.getE01PLPPLP().trim()%>";
		}else{
		document.forms[0].E01DPUB18.value = "";
		document.forms[0].E01DPDA18.value = "";
		document.forms[0].E01DPDM18.value = "";
		document.forms[0].E01DPDY18.value = "";
		}
	break;
	case  19: 
		if (document.forms[0].E01DPUB19.checked == true) {
		document.forms[0].E01DPUB19.value = "1";
		document.forms[0].E01DPDA19.value = "<%=msgRT.getE01PLPIPD().trim()%>";
		document.forms[0].E01DPDM19.value = "<%=msgRT.getE01PLPIPM().trim()%>";
		document.forms[0].E01DPDY19.value = "<%=msgRT.getE01PLPPLP().trim()%>";
		}else{
		document.forms[0].E01DPUB19.value = "";
		document.forms[0].E01DPDA19.value = "";
		document.forms[0].E01DPDM19.value = "";
		document.forms[0].E01DPDY19.value = "";
		}
	break;
	case  20: 
		if (document.forms[0].E01DPUB20.checked == true) {
		document.forms[0].E01DPUB20.value = "1";
		document.forms[0].E01DPDA20.value = "<%=msgRT.getE01PLPIPD().trim()%>";
		document.forms[0].E01DPDM20.value = "<%=msgRT.getE01PLPIPM().trim()%>";
		document.forms[0].E01DPDY20.value = "<%=msgRT.getE01PLPPLP().trim()%>";
		}else{
		document.forms[0].E01DPUB20.value = "";
		document.forms[0].E01DPDA20.value = "";
		document.forms[0].E01DPDM20.value = "";
		document.forms[0].E01DPDY20.value = "";
		}
	break;
	case  21: 
		if (document.forms[0].E01DPUB21.checked == true) {
		document.forms[0].E01DPUB21.value = "1";
		document.forms[0].E01DPDA21.value = "<%=msgRT.getE01PLPIPD().trim()%>";
		document.forms[0].E01DPDM21.value = "<%=msgRT.getE01PLPIPM().trim()%>";
		document.forms[0].E01DPDY21.value = "<%=msgRT.getE01PLPPLP().trim()%>";
		}else{
		document.forms[0].E01DPUB21.value = "";
		document.forms[0].E01DPDA21.value = "";
		document.forms[0].E01DPDM21.value = "";
		document.forms[0].E01DPDY21.value = "";
		}
	break;
	case  22: 
		if (document.forms[0].E01DPUB22.checked == true) {
		document.forms[0].E01DPUB22.value = "1";
		document.forms[0].E01DPDA22.value = "<%=msgRT.getE01PLPIPD().trim()%>";
		document.forms[0].E01DPDM22.value = "<%=msgRT.getE01PLPIPM().trim()%>";
		document.forms[0].E01DPDY22.value = "<%=msgRT.getE01PLPPLP().trim()%>";
		}else{
		document.forms[0].E01DPUB22.value = "";
		document.forms[0].E01DPDA22.value = "";
		document.forms[0].E01DPDM22.value = "";
		document.forms[0].E01DPDY22.value = "";
		}
	break;
	case  23: 
		if (document.forms[0].E01DPUB23.checked == true) {
		document.forms[0].E01DPUB23.value = "1";
		document.forms[0].E01DPDA23.value = "<%=msgRT.getE01PLPIPD().trim()%>";
		document.forms[0].E01DPDM23.value = "<%=msgRT.getE01PLPIPM().trim()%>";
		document.forms[0].E01DPDY23.value = "<%=msgRT.getE01PLPPLP().trim()%>";
		}else{
		document.forms[0].E01DPUB23.value = "";
		document.forms[0].E01DPDA23.value = "";
		document.forms[0].E01DPDM23.value = "";
		document.forms[0].E01DPDY23.value = "";
		}
	break;
	case  24: 
		if (document.forms[0].E01DPUB24.checked == true) {
		document.forms[0].E01DPUB24.value = "1";
		document.forms[0].E01DPDA24.value = "<%=msgRT.getE01PLPIPD().trim()%>";
		document.forms[0].E01DPDM24.value = "<%=msgRT.getE01PLPIPM().trim()%>";
		document.forms[0].E01DPDY24.value = "<%=msgRT.getE01PLPPLP().trim()%>";
		}else{
		document.forms[0].E01DPUB24.value = "";
		document.forms[0].E01DPDA24.value = "";
		document.forms[0].E01DPDM24.value = "";
		document.forms[0].E01DPDY24.value = "";
		}
	break;
	case  25: 
	if (document.forms[0].E01DPDA25.value == "0") {
		if (document.forms[0].E01DPUB01.value == "") {
		document.forms[0].E01DPUB25.value = "1";
		document.forms[0].E01DPDA25.value = "<%=msgRT.getE01PLPIPD().trim()%>";
		document.forms[0].E01DPDM25.value = "<%=msgRT.getE01PLPIPM().trim()%>";
		document.forms[0].E01DPDY25.value = "<%=msgRT.getE01PLPPLP().trim()%>";
		}else{
		document.forms[0].E01DPUB25.value = "";
		document.forms[0].E01DPDA25.value = "";
		document.forms[0].E01DPDM25.value = "";
		document.forms[0].E01DPDY25.value = "";
		}
	}
	break;
}
}


function goAction(op) {
	switch (op) {
	case  1: document.forms[0].opt.value = op;
	       document.forms[0].SCREEN.value = 210;
	       document.forms[0].submit();
		break; 
	case  2: document.forms[0].opt.value = op;
	       document.forms[0].SCREEN.value = 220;
	       document.forms[0].submit();
		break;   
	case  3: ok = confirm("Esta seguro que desea eliminar el registro seleccionado?");
	  if (ok) 
	       {
	       document.forms[0].opt.value = op;
	       document.forms[0].SCREEN.value = 230;
	       document.forms[0].submit();
	       }
		break;   

	}

}

function goActionProd(op) {

document.forms[0].optH.value = op;

   switch (op) {
    case 1 :{
    

	       document.forms[0].SCREEN.value = 410;
	       document.forms[0].submit();

            break;
            }
    case 2 :{

	       document.forms[0].SCREEN.value = 420;
	       document.forms[0].submit();

            break;
            }
    case 3 :{

	       document.forms[0].SCREEN.value = 420;
	       document.forms[0].submit();
    		break;
            }
    case 4 :{

    		break;
            }
   }  

}


function showCollItems(idx,RUT) {
var id = idx
  document.forms[0].RUT.value = RUT;
  document.forms[0].E01PLPRUT.value = RUT;
  document.forms[0].COLLCODW.value = id;
  var actTab= document.forms[0].CCOD.value;
  for ( var i=0; i<dataTable.rows.length; i++ ) {
       dataTable.rows[i].className="trnormal";
    }
   dataTable.rows[idx].className="trhighlight"; 
  if (actTab != "") {  
  	document.all["dataTable"+actTab].style.display="none";
  }

//  document.all["dataTable"+flg].style.display="";
//  document.forms[0].CCOD.value=flg;
//  adjustEquTables(headTable2, document.all["dataTable"+flg], dataDiv2,1,false);
//  showChecked("COLLITEM"+flg);    

	showChecked("COLLCOD");
	resizeDoc();
	window.onresize=resizeDoc;


}

function setItemInfo(flg,idx){
  var actTab= document.forms[0].CCOD.value;
  for ( var i=0; i<document.all["dataTable"+actTab].rows.length; i++ ) {
       document.all["dataTable"+actTab].rows[i].className="trnormal";
    }
   document.all["dataTable"+actTab].rows[idx].className="trhighlight"; 
  document.forms[0].ICOD.value = flg;
  document.forms[0].ROW.value = pos;

}
</script>
</head>
<body onload=javascript:init()>
<% 
    if ( !error.getERRNUM().equals("0")  ) {
        out.println("<SCRIPT Language=\"Javascript\">");
        error.setERRNUM("0");
        out.println("       showErrors()");
        out.println("</SCRIPT>");
    }   
        out.println("<SCRIPT> initMenu(); </SCRIPT>");


%>
<A NAME="Miscelaneos">


<H3 align="center">Propuesta de Cr&eacute;dito<img src="<%=request.getContextPath()%>/images/e_ibs.gif" align="left" name="EIBS_GIF" ALT="proposals_header_maintenance.jsp, EDP0720"></H3>

<% if(userPO.getHeader16().equals("1")) { %>
<P align="center">
<INPUT type="text" name="DSC1" size="35" maxlength="35" value="<% if(userPO.getHeader16().equals("1")){out.print("APERTURA NUEVA PROPUESTA");} %>" readonly>
</P>
<%} else {%>

<P align="center">
<INPUT type="text" name="DSC" size="35" maxlength="35" value="<%= msgRT.getE01PLPPSR()%>-<%= msgRT.getE01DESAC2()%>" readonly>
</P>

<%};%>


<form method="post" action="<%=request.getContextPath()%>/servlet/datapro.eibs.creditproposal.JSEDP0720" >
  <INPUT TYPE=HIDDEN NAME="SCREEN" VALUE="600">
  <INPUT TYPE=HIDDEN NAME="opt" VALUE="<%= userPO.getHeader16().trim()%>">
  <INPUT TYPE=HIDDEN NAME="optH" VALUE="">

  <input type=HIDDEN name="OPECOD" value="<%= msgRT.getE01DPWOPC().trim()%>">
  <input type=HIDDEN name="PARAM" value="<%= msgRT.getE01DPWPAR().trim()%>">
  <input type=HIDDEN name="TASK" value="<%= userPO.getHeader23().trim()%>">

  <input type=HIDDEN name="PRD" value="<%= userPO.getProdCode()%>"> 
  <input type=HIDDEN name="BNK" value="<%= userPO.getBank()%>"> 
  <input type=HIDDEN name="BRN" value="<%= userPO.getBranch()%>"> 
  <input type=HIDDEN name="RUT" value="<%= userPO.getHeader15().trim()%>"> 
  <input type=HIDDEN name="CUS" value="<%= userPO.getCusNum()%>"> 

  <input type=HIDDEN name="AGD" value="<%= msgRT.getE01PLPAGD().trim()%>">
  <input type=HIDDEN name="pos" value="<%= msgRT.getE01RECPOS().trim()%>">

	<INPUT TYPE=HIDDEN NAME="COLLCODW" VALUE=""> 
	<INPUT TYPE=HIDDEN NAME="CCOD" VALUE=""> 
	<INPUT TYPE=HIDDEN NAME="ICOD" VALUE=""> 
	<INPUT TYPE=HIDDEN NAME="ROW" VALUE="0"> 

  <input type=HIDDEN name="NPR" value="<%= msgRT.getE01PLPNPR().trim()%>">
  <input type=HIDDEN name="E01PLPRUT" value=""> 

<% if (userPO.getHeader16().equals("1" )) { %>
  <input type=HIDDEN name="E01DPPPXX" value="0001"> 
  <input type=HIDDEN name="E01DPPDXX" value="APERTURA PROPUESTA"> 
  <input type=HIDDEN name="E01PLPSRU" value="<%= msgRT.getE01PLPSRU().trim()%>">  
  <input type=HIDDEN name="E01PLPACT" value="<%= msgRT.getE01PLPACT().trim()%>"> 
<% }; %>
  <h4>Propuesta</h4>

 <table class="tableinfo">
    <tr > 
      <td width="802">
		<table cellspacing="0" cellpadding="2" width="100%" border="0">
			<tr id="trdark">
				<td width="15%">
				<div align="right">N&uacute;mero:</div>
				</td>
				<td width="40%">
				<INPUT type="text" name="E01PLPNPR" size="12" maxlength="12" value="<%= msgRT.getE01PLPNPR().trim()%>" readonly>
				Ref:
				<INPUT type="text" name="E01PLPRRE" size="12" maxlength="12" value="<%= msgRT.getE01PLPRRE().trim()%>" readonly>
				</td>
				<td width="15%">
				<div align="right">Fecha Apertura :</div>
				</td>
				<td width="30%">
				<INPUT type="text" name="E01PLPIPD" size="2" maxlength="2" value="<%= msgRT.getE01PLPIPD().trim()%>" readonly> 
				<INPUT type="text" name="E01PLPIPM" size="2" maxlength="2" value="<%= msgRT.getE01PLPIPM().trim()%>" readonly> 
				<INPUT type="text" name="E01PLPPLP" size="2" maxlength="2" value="<%= msgRT.getE01PLPPLP().trim()%>" readonly> 
				<A 	href="javascript:DatePicker(document.forms[0].E01PLPIPD,document.forms[0].E01PLPIPM,document.forms[0].E01PLPPLP)"><IMG
					src="<%=request.getContextPath()%>/images/calendar.gif" alt="help"
					align="absmiddle" border="0"> 
				</A>
				</td>
			</tr>
			<tr id="trdark">
				<td width="15%" align="right">Banco</td>
				<td width="40%">
				<% if(userPO.getHeader16().equals("x")) { %> 
				<SELECT name="E01PLPBNK">
				<%
                optList2.initRow();
                while (optList2.getNextRow()) {
                    if (optList2.getFlag().equals("")) {
                    		out.println(optList2.getRecord());
                    }        
                }                 
    			%>
				</SELECT> 
				<% } else { %> 
				<input type="text" name="E01PLPBNK" size="3" maxlength="35" value="<%= msgRT.getE01PLPBNK().trim()%>" readonly>
				<% };%>
				</td>
				<td width="20%" align="right">Sucursal de Apertura:</td>
				<td width="30%">
				<% if(!userPO.getHeader16().equals("1")) { %> 
				<SELECT name="E01PLPBRN" DISABLED>
					<%
                	optList11.initRow();
                	while (optList11.getNextRow()) {
                    	if (optList11.getFlag().equals("")) {
                    			out.println(optList11.getRecord());
    	                }        
        	        }                 
    				%>
				</SELECT> 
				<% } else { %> 
				<input type="text" name="E01PLPBRN" size="5" maxlength="35" value="<%= msgRT.getE01PLPBRN().trim()%>" readonly>
				<% }; %>
				</td>
			</tr>
          <tr id="trdark"> 
            <td width="15%"> 
              <div align="right">C&oacute;digo Ejecutivo:</div>
            </td>
            <td width="40%">
            	<input type="text" name="E01PLPEJE" size="5" maxlength="4" value="<%= msgRT.getE01PLPEJE().trim()%>" readonly> 
				<INPUT type="text" readonly name="E01XXXEJE" size="24" maxlength="24"  value="<%= msgRT.getE01XXXEJE().trim()%>">
            
            </td>
            <td width="15%" align="right">Sucursal de Desembolso</td>
            <td width="30%">
			
				<% if(!userPO.getHeader16().equals("")) { %>
					<SELECT name="E01PLPAGD" DISABLED>
					<%
                	optList11.initRow();
                	while (optList11.getNextRow()) {
                    	if (optList11.getFlag().equals("")) {
                    			out.println(optList11.getRecord());
    	                }        
        	        }                 
    				%>
					</SELECT>
				<% } else { %>
					<input type="text" name="E01PLPAGD" size="5" maxlength="35"
					value="<%= msgRT.getE01PLPAGD().trim()%>">
				<% }; %>
			
            </td>
            
           </tr>


		</table>
		</td>
    </tr>
  </table>
   <h4>Datos del Cliente</h4>
 <table class="tableinfo">
    <tr > 
      <td width="802"> 
        <table cellspacing="0" cellpadding="2" width="100%" border="0">
          <tr id="trdark"> 
            <td width="20%"> 
              <div align="right">N&uacute;mero Cliente :</div>
            </td>
            <td width="30%">
            
				<% if(userPO.getHeader16().equals("1")) { %>
		            <input type=TEXT name="E01CUSCUN" size=10 maxlength=9 onKeypress="enterInteger()" value="<%= msgRT.getE01CUSCUN().trim()%>" >
        		    <a href="javascript:GetCustomerDescId('E01CUSCUN','E01CUSNA1','')"><img src="<%=request.getContextPath()%>/images/1b.gif" alt=". . ." align="absmiddle" border="0" ></a> 
				<% } else { %>
					<input type="text" name="E01CUSCUN" size="27" maxlength="4" value="<%= msgRT.getE01CUSCUN().trim()%>" readonly>
				<% }; %>
            </td>
            <td width="20%"> 
              <div align="right">Nombre Cliente :</div>
            </td>
            <td width="30%">
               		<input type="text" name="E01CUSNA1" size="27"
					maxlength="4" value="<%= msgRT.getE01CUSNA1().trim()%>" readonly>
            </td>
          </tr>
          <tr id="trdark"> 
            <td width="20%"> 
              <div align="right">Clasificación:</div>
            </td>
            <td width="30%">
               		<input type="text" name="E01CUFCL1" size="27" maxlength="35" value="<%= msgRT.getE01CUFCL1().trim()%>" readonly>
			</td>
            <td width="20%" align="right">
			Interes:</td>
            <td width="30%">
            <INPUT type="TEXT" name="E01XXXRTE" size="10" maxlength="9" onkeypress="enterDecimal()" value="<%= msgRT.getE01XXXRTE().trim()%>" readonly >
			</td>
          </tr>
          <tr id="trdark"> 
            <td width="20%"> 
              <div align="right">Grupo:</div>
            </td>
            <td width="30%">
			<INPUT type="TEXT" name="E01PLPGRP" size="10" maxlength="9" onkeypress="enterInteger()" value="<%= msgRT.getE01PLPGRP().trim()%>" readonly >
			<A name="Miscelaneos0">
			<INPUT type="text" name="E01XXXGR1" size="27" maxlength="4" value="<%= msgRT.getE01XXXGR1().trim()%>" readonly></A></td>
            <td width="20%">
              <div align="right">Actividad Económica:</div>
			</td>
            <td width="30%">
			<INPUT type="TEXT" name="E01PLPBUC" size="4" maxlength="4" onkeypress="enterInteger()" readonly value="<%= msgRT.getE01PLPBUC().trim()%>">
       		<input type="text" name="E01XXXBUC" size="27" maxlength="4" value="<%= msgRT.getE01XXXBUC().trim()%>" readonly>
			
			</td>
          </tr>
          <tr id="trdark"> 
            <td width="20%"> 
              <div align="right">Fecha Constitución:</div>
            </td>
            <td width="30%">
				<INPUT type="text" name="E01CUSIDD" size="2" maxlength="2" value="<%= msgRT.getE01CUSIDD().trim()%>" readonly> 
				<INPUT type="text" name="E01CUSIDM" size="2" maxlength="2" value="<%= msgRT.getE01CUSIDM().trim()%>" readonly> 
				<INPUT type="text" name="E01CUSIDY" size="2" maxlength="2" value="<%= msgRT.getE01CUSIDY().trim()%>" readonly> 
            
			</td>
            <td width="20%" align="right">
			Fecha Vencimiento:</td>
            <td width="30%">
				<INPUT type="text" name="E01XXXVAD" size="2" maxlength="2" value="<%= msgRT.getE01XXXVAD().trim()%>" readonly> 
				<INPUT type="text" name="E01XXXVAM" size="2" maxlength="2" value="<%= msgRT.getE01XXXVAM().trim()%>" readonly> 
				<INPUT type="text" name="E01XXXVAY" size="2" maxlength="2" value="<%= msgRT.getE01XXXVAY().trim()%>" readonly> 
			</td>
          </tr>
        </table>
      </td>
    </tr>
  </table>

<A NAME="producto">
  
<CENTER>
<A HREF="#Miscelaneos">Misceláneos</A>  &nbsp; |&nbsp; 
<A HREF="#producto">Producto</A> &nbsp; |&nbsp; 
<A HREF="#recaudos">Recaudos</A> &nbsp; |&nbsp; 
<A HREF="#comentarios">Comentarios Analista</A> &nbsp; |&nbsp; 
</CENTER>
  
  

<%if (gaCodeList.getNoResult()) {%>

<TABLE class="tbenter" height="20%">
  <h4>Producto</h4>
	<TR>
		<TD ALIGN=CENTER VALIGN=MIDDLE>
		<H4 style="text-align: center">No existe ningun Producto creado para
		esta propuesta. <br>
		</h4>
		</TD>
	<tr>
		<td class=TDBKG width="20%">
		<div align="center"><a href="javascript:goActionProd(1)"><b>Nuevo</b></a></div>
		</td>
	</tr>
</TABLE>
<%} else {%>


<TABLE id="mainTable1" class="tableinfo">
	<TR>
		<TD NOWRAP valign="top" width="100%">

		<table class="tbenter" width=100% align=center>
  		<h4>Productos</h4>
		<tr>
			<% if (msgRT.getE01SWMPRD().trim().equals("2"))  { %>
			<td class=TDBKG width="20%">
			<div align="center"><a href="javascript:goActionProd(1)"><b>Nuevo</b></a></div>
			</td>
			<% } %>
			<% if(!userPO.getHeader16().equals("5")) { %>
			<td class=TDBKG width="20%">
			<div align="center"><a href="javascript:goActionProd(2)"><b>Modificar</b></a></div>
			<% } %>
			<td class=TDBKG width="20%">
			<div align="center"><a href="javascript:goActionProd(3)"><b>Consultar</b></a></div>
		</tr>
		</table>


	<TR>
	<TD NOWRAP width="100%">
		<TABLE id="headTable1">
			<tr id="trdark">
				<th align=CENTER nowrap width="5%">&nbsp;</th>
				<th align=CENTER nowrap width="5%">
				<div align="center">Cód.</div>
				</th>
				<th align=CENTER nowrap width="10%">
				<div align="center">Descripción</div>
				</th>
				<th align=CENTER nowrap width="15%">
				<div align="center">Tipo</div>
				</th>
				<th align=CENTER nowrap width="20%">
				<div align="right">Monto Solic.</div>
				</th>
				<th align=CENTER nowrap width="20%">
				<div align="right">Monto Aprobado.</div>
				</th>
				<th align=CENTER nowrap width="10%">
				<div align="right">Ruta</div>
				</th>
			</tr>
		</TABLE>
		<div id="dataDiv1" class="scbarcolor">
		<table id="dataTable">
			<%boolean firstTime = true;
			String gaChk = "";
			gaCodeList.initRow();
			int i = 0;

			EDP072101Help.initRow();

			while (gaCodeList.getNextRow()) {
				if (gaCodeList.getFlag().equals("")) {
					gaChk = (firstTime) ? "checked" : "";
					firstTime = false;

					EDP072101Help.getNextRow();
					datapro.eibs.beans.EDP072101Message msgList =
					(datapro.eibs.beans.EDP072101Message) EDP072101Help.getRecord();%>
				<TR>
					<TD ALIGN=LEFT NOWRAP width="5%" >
					<input type="radio" name="COLLCOD" value="<%=EDP072101Help.getCurrentRow()%>" onClick="showCollItems(this.value,'<%=msgList.getE01PLTRU0()%>')" <%=gaChk%> >
					</TD>
					<TD ALIGN=CENTER NOWRAP width="5%">
					<a href="javascript:radioClick('COLLCOD',<%=i%>)"  >
					<DIV NOWRAP><%=gaCodeList.getRecord(0)%> </DIV>
					</a></TD>
					<TD ALIGN=CENTER NOWRAP width="10%">
					<a href="javascript:radioClick('COLLCOD',<%=i%>)" >
					<DIV NOWRAP><%=gaCodeList.getRecord(1)%></DIV>
					</a></TD>
					<TD ALIGN=LEFT NOWRAP width="15%">
					<a href="javascript:radioClick('COLLCOD',<%=i%>)" >
					<DIV NOWRAP><%=gaCodeList.getRecord(2)%></DIV>
					</a></TD>
					<TD ALIGN=CENTER NOWRAP width="20%" >
					<a href="javascript:radioClick('COLLCOD',<%=i%>)" >
					<DIV NOWRAP><%=gaCodeList.getRecord(3)%></DIV>
					</a></TD>
					<TD ALIGN=CENTER NOWRAP width="20%" >
					<a href="javascript:radioClick('COLLCOD',<%=i%>)" >
					<DIV NOWRAP><%=gaCodeList.getRecord(4)%></DIV>
					</a></TD>
					<TD ALIGN=CENTER NOWRAP width="10%" >
					<a href="javascript:radioClick('COLLCOD',<%=i%>)" >
					<DIV NOWRAP><%=gaCodeList.getRecord(5)%></DIV>
					</a></TD>

				</TR>

			<%i++;
			}
			}%>
		</table>
		</div>
	</TD>
	</TR>
</TABLE>
<br>


<%if (!error.getERRNUM().equals("0")) {
	error.setERRNUM("0");
	out.println("<SCRIPT Language=\"Javascript\">");
	out.println("       showErrors()");
	out.println("</SCRIPT>");
	int row;
	String code = "";
	String flag = "";
	try {
		row = Integer.parseInt(request.getParameter("ROW"));
	} catch (Exception e) {
		row = 0;
	}
	gaList.setCurrentRow(row);
	flag = gaList.getFlag();
	code = gaList.getRecord(1);%> 
	<SCRIPT language="JavaScript">
     var collLen = document.forms[0].elements("COLLCOD").length;
     var colItm = document.forms[0].elements("COLLITEM<%=flag%>");
     var itemLen = colItm.length; 
     for(var i=0; i < collLen;i++) {
       if (document.forms[0].COLLCOD[i].value == "<%=flag%>") {
         document.forms[0].COLLCOD[i].checked = true;
         break;
       }
     }
     for(var i=0; i<itemLen;i++) {
       if (colItm[i].value == "<%=code%>") {
         colItm[i].checked = true;
         break;
       }
     }
   </SCRIPT> 
  <%} %> 
  <SCRIPT language="JavaScript">

     function resizeDoc() {
      var actvTb = document.forms[0].CCOD.value;
      var dataT = document.all["dataTable"+actvTb];
       adjustEquTables(headTable1, dataTable, dataDiv1,1,false);
    }
	showChecked("COLLCOD");
	resizeDoc();
	window.onresize=resizeDoc;
     
	</SCRIPT> 
<%}%>
  
  

<A NAME="recaudos">

<CENTER>
<A HREF="#Miscelaneos">Misceláneos</A>  &nbsp; |&nbsp; 
<A HREF="#producto">Producto</A> &nbsp; |&nbsp; 
<A HREF="#recaudos">Recaudos</A> &nbsp; |&nbsp; 
<A HREF="#comentarios">Comentarios Analista</A> &nbsp; |&nbsp; 
</CENTER>
  

<h4 align="left">Recaudo Documentos</h4>

	<TABLE  class="tableinfo" width="600" height="101"> 
		<TR id=trdark> 
			<TD ALIGN=LEFT colspan="3" NOWRAP width="10%">Check<b></b>
			</TD>
			<TD ALIGN="left" NOWRAP width="40%">
			<b></b></TD>
			<TD ALIGN=LEFT colspan="3" NOWRAP width="20%"><b></b>
			</TD>
		</TR>
		
	   <%
    int iR = 25;
    String name="";
      for ( int i=1; i<=iR; i++ ){
          if (i<10) name = "0" + i; else name= "" + i;
		if (!msgRT.getField("E01DPDX"+name).getString().trim().equals("")){
    %> 
		
		<TR id=trclear>       			
      		<TD ALIGN=LEFT NOWRAP>
      		<INPUT type="checkbox" name="E01DPUB<%= name %>"  value="<%= msgRT.getField("E01DPUB"+name).getString().trim()%>" <%if (!msgRT.getField("E01DPUB"+name).getString().trim().equals("")) out.print("checked");%> width="10%" onClick="asigDat(<%= name %>);"  <% if(userPO.getHeader16().equals("5")) {out.print("DISABLED");} %>  >
      		</TD>
			<TD ALIGN="left" NOWRAP>
			<INPUT type="text" maxlength="30" size="47" name="E01DPDX<%= name %>" value="<%= msgRT.getField("E01DPDX"+name).getString().trim()%>" readonly width="40%">
			</TD>
      		<TD ALIGN=LEFT NOWRAP width="20%">
			<INPUT type="text" maxlength="3" size="2" name="E01DPDA<%= name %>" value="<%= msgRT.getField("E01DPDA"+name).getString().trim()%>" readonly >
			<INPUT type="text" maxlength="3" size="2" name="E01DPDM<%= name %>" value="<%= msgRT.getField("E01DPDM"+name).getString().trim()%>" readonly >
			<INPUT type="text" maxlength="3" size="2" name="E01DPDY<%= name %>" value="<%= msgRT.getField("E01DPDY"+name).getString().trim()%>" readonly >
			</TD>
			<TD ALIGN="left" NOWRAP>
			<INPUT type="text" maxlength="15" size="15" name="NIVDOC<%= name %>"
			<% if (msgRT.getField("E01DPDT"+name).getString().trim().equals("1")) { %>
			 value="REQUERIDO"
			<% }; %>
			<% if (msgRT.getField("E01DPDT"+name).getString().trim().equals("2")) { %>
			 value="ADICIONAL"
			<% }; %>
			<% if (msgRT.getField("E01DPDT"+name).getString().trim().equals("3")) { %>
			 value="OPCIONAL"
			<% }; %>
			readonly width="10%">
			</TD>
		</TR>
    <% }; %> 
    <% }; %> 
		
	</TABLE>
  
  <A NAME="comentarios">
   
<CENTER>
<A HREF="#Miscelaneos">Misceláneos</A>  &nbsp; |&nbsp; 
<A HREF="#producto">Producto</A> &nbsp; |&nbsp; 
<A HREF="#recaudos">Recaudos</A> &nbsp; |&nbsp; 
</CENTER>
  
  
  
   <h4>Ubicación Geográfica</h4>
     <table class="tableinfo">
	<tr id="trdark">
		<td width="100%" align="center">
       		<input type="text" name="E01PLPUGE" size="5" maxlength="4" value="<%= msgRT.getE01PLPUGE().trim()%>" 
			<% if(userPO.getHeader16().equals("5")){out.print("readonly");}%>
       		>
       		
       		<% if(!userPO.getHeader16().equals("5")) {%>
			<A href="javascript:GetCodeDescCNOFC('E01PLPUGE','D01PLPUGE','27')"><IMG src="<%=request.getContextPath()%>/images/1b.gif" alt="Ayuda" align="bottom" border="0">
			</A>
			<% } %>
 			<INPUT type="text" name="D01PLPUGE" size="35" maxlength="35" readonly> 
		</td>
      </tr>
    </table>
   <h4>Análisis de Crédito</h4>
     <table class="tableinfo">
	<tr id="trdark">
		<td width="100%" align="center">
		<TEXTAREA name="E02DPAC99" rows="7" cols="70" value="<%= msgRTC.getE02DPAC99().trim()%>" 
		<% if(userPO.getHeader16().equals("5")){out.print("readonly");}%>
		>
		</TEXTAREA>
		</td>
      </tr>
    </table>
   <h4>Comentarios 
	<INPUT type="text" readonly name="E01XXXEJ1" size="35" maxlength="35"  value="<%= msgRT.getE01XXXEJ1().trim()%>">
   </h4>
     <table class="tableinfo">
	<tr id="trdark">
		<td width="100%" align="center">
		<TEXTAREA name="E01DPSCOM" rows="7" cols="70" value="<%= msgRT.getE01DPSCOM().trim()%>"
		<% if(userPO.getHeader16().equals("5")){out.print("readonly");}%>
		>
		</TEXTAREA>
		</td>
      </tr>
    </table>

	<table cellspacing="0" cellpadding="0" width="100%" border="0">
      <tr > 
        <td width="802">
		<table cellspacing="0" cellpadding="0" width="100%" border="0">
			<tr id="trclear">
				<td width="10%">
               <div align="right">Estado Propuesta:</div>
				</td>
				<td width="10%">
				<INPUT type="text" name="E01XXXEST" size="35" maxlength="35" value="<%= msgRT.getE01XXXEST().trim()%>" readonly>
				</td>
				<td nowrap width="10%" align="right">
               <div align="right">Monto Recomendado:</div>
				</td>
				<td nowrap width="20%">
				<INPUT type="text" name="E01DPSAMN" size="20" maxlength="20" onkeypress="enterDecimal()" value="<%= msgRT.getE01DPSAMN().trim()%>"
				<% if(userPO.getHeader16().equals("5")){out.print("readonly");}%>
				>
				</td>
				<td nowrap width="10%" align="right">
               <div align="right"></div>
				</td>
				<td nowrap width="20%">
				</td>
			</tr>
		</table>
		</td>
      </tr>
    </table>

   
<CENTER>
<A HREF="#Miscelaneos">Miscelaneos</A>  &nbsp; |&nbsp; 
<A HREF="#producto">Producto</A> &nbsp; |&nbsp; 
<A HREF="#recaudos">Recaudos</A> &nbsp; |&nbsp; 
<A HREF="#comentarios">Comentarios Analista</A> &nbsp; |&nbsp; 
</CENTER>
  

		<% if (!(userPO.getHeader16().equals("1" ))) { %>

		<h4>Seleccione Próxima Actividad:</h4>

	<TABLE id="tbenter" border="0" cellspacing="1" cellpadding="0">
	<TR id="trdark">
		<td width="30%">

		<SELECT name="available" size="5"
			<% if( userPO.getHeader16().equals("X")) {out.print("DISABLED");} %>>
			<%
           		optLP4.initRow();
           		while (optLP4.getNextRow()) {
           		if (optLP4.getFlag().equals("")) {
           			out.println(optLP4.getRecord());
           			}        
           		}

   			%>
		</SELECT>


		</td>
		<td width="10%">
		<INPUT type="button" value="Seleccionar" onclick="moveOver();"
		<% if(userPO.getHeader16().equals("X") ){out.print("DISABLED");} %>>								
		</td>
		<td width="60%" align="left" valign="middle">
			<INPUT type="text" name="E01DPPPXX" size="5" maxlength="5" value="<%= msgRT.getE01DPPPXX().trim()%>" readonly> 
			<INPUT type="text" name="E01DPPDXX" size="35" maxlength="35" value="<%= msgRT.getE01DPPDXX().trim()%>" readonly>
		</td>
		</tr>
	</TABLE>
		<% };%>
        
  <div align="center"> 
       <input id="EIBSBTN" type="button" name="Submit" value="Siguiente Actividad" onclick="goControlDoc('<%= userPO.getHeader16() %>')" <% if(userPO.getHeader16().equals("5")){out.print("DISABLED");} %>>
		<% if (!(userPO.getHeader16().equals("1" ))) { %>
       <INPUT id="EIBSBTN" type="button" name="Submit0" value="Salvar" onclick="goConfirm('<%= userPO.getHeader16() %>','<%= userPO.getHeader23() %>','<%= userPO.getHeader22() %>','<%= userPO.getHeader21() %>')" <% if(userPO.getHeader16().equals("5")){out.print("DISABLED");} %>>	
		<% };%>
       <INPUT id="EIBSBTN" type="button" name="Cancel" value="Cancelar" onclick="goCancel('<%= userPO.getHeader9().trim() %>')">
  </div>

  <SCRIPT language="JavaScript">

     function resizeDoc() {
      var actvTb = document.forms[0].CCOD.value;
      var dataT = document.all["dataTable"+actvTb];
       adjustEquTables(headTable1, dataTable, dataDiv1,1,false);
      }
//		showChecked("COLLCOD");
//		resizeDoc();
//		window.onresize=resizeDoc;
	</SCRIPT> 
          
          
  </form>
</body>
</html>
